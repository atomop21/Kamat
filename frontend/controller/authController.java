package com.example.cafe.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cafe.entities.User;
import com.example.cafe.payload.JwtAuthReq;
import com.example.cafe.payload.JwtAuthResp;
import com.example.cafe.payload.Roledt;
import com.example.cafe.payload.userdt;
import com.example.cafe.security.CustomUserDetailService;
import com.example.cafe.security.JwtTokenHelper;
import com.example.cafe.serv.userserv;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/kamat")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class authController {
	
	@Autowired
	private final JwtTokenHelper helper;
	
	@Autowired
	private userserv userv;
	
	@Autowired
	private ModelMapper mmaper;
	
	@Autowired
	private final CustomUserDetailService userDetailsService;
	
	@Autowired
	private final AuthenticationManager authenticationManager;
	
	@PostMapping("/auth/login")
	public ResponseEntity<JwtAuthResp> createtoken(@RequestBody JwtAuthReq req) throws Exception{
		try {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPass()));
		}catch (BadCredentialsException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("Bad Credentials");
		}
		
		 UserDetails userDetails = this.userDetailsService.loadUserByUsername(req.getUsername());
		 
		 
		 String token=this.helper.generateToken(userDetails);
		 System.out.println(token);
		 JwtAuthResp resp=new JwtAuthResp();
		 Roledt rdt=new Roledt();
		 userdt udt=this.mmaper.map((User)userDetails, userdt.class);
		 resp.setToken(token);
		 resp.setUser(udt);
		 resp.setUserole(udt.getRole());
		 return new ResponseEntity<JwtAuthResp>(resp,HttpStatus.OK);
		
	}
	
	@PostMapping("/register")
	public ResponseEntity<JwtAuthResp> reguser(@Valid @RequestBody userdt udt){
		userdt rudt=this.userv.regnewuser(udt);
		UserDetails details=this.userDetailsService.loadUserByUsername(rudt.getEmail());
		System.out.println(rudt.getEmail());
		String token=this.helper.generateToken(details);
		JwtAuthResp resp=new JwtAuthResp();
		resp.setToken(token);
		resp.setUser(rudt);
		resp.setUserole(rudt.getRole());
		return new  ResponseEntity<JwtAuthResp>( resp,HttpStatus.CREATED);
	}

	
}
