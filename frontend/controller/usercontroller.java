package com.example.cafe.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.cafe.payload.Fileresp;
import com.example.cafe.payload.response;
import com.example.cafe.payload.userdt;
import com.example.cafe.serv.fileserv;
import com.example.cafe.serv.userserv;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/kamat")
@CrossOrigin(origins = "http://localhost:3000")
public class usercontroller {
	
	@Autowired
	private userserv udao;
	
	@Autowired
	private fileserv fserv;
	
	@Value("${project.kamat}")
	private String logopath;
	
	@Value("${project.logo}")
	private String path;
	
	@PostMapping("/user")
	public ResponseEntity<userdt> createUser(@Valid @RequestBody userdt usdt){
		userdt crudt =this.udao.createuser(usdt);
		return new ResponseEntity<>(crudt,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/user/{id}")
	public ResponseEntity<userdt> updateUser(@Valid @RequestBody userdt udt,@PathVariable Integer id){
		userdt upddt=this.udao.updateuser(udt, id);
		
		return  ResponseEntity.ok(upddt);
		
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<response> deleteuser(@PathVariable Integer id){
		this.udao.deleteuser(id);
	
		return new  ResponseEntity<response>(new response("user deleted successfully", true),HttpStatus.OK);
		
	}

	@GetMapping("/users")
	public ResponseEntity<List<userdt>> getallusers(){
		
		
		
		return ResponseEntity.ok(this.udao.getusers());
		
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<userdt> getuser(@PathVariable Integer id){

		return ResponseEntity.ok(this.udao.getuser(id));
		
	}
	
	@PostMapping("/user/profilepic/{id}")
	public ResponseEntity<Fileresp> uploadprofile(@RequestParam("profimage") MultipartFile prof,@PathVariable Integer id) throws IOException{
		userdt udt=this.udao.getuser(id);
		String fname=this.fserv.uploadfile(path, prof);
		udt.setProfilepic(fname);
		System.out.println(udt.getProfilepic()+udt);
		userdt nudt=this.udao.updateuser(udt, id);
		System.out.println(nudt);
		
		return new ResponseEntity<Fileresp>(new Fileresp(nudt.getProfilepic(), "profile pic added successfully", nudt),HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/user/profile/{profilepic}",produces = MediaType.IMAGE_JPEG_VALUE)
	public void getprofile(@PathVariable("profilepic") String profilepic,HttpServletResponse resp) throws IOException {
		InputStream is=this.fserv.getres(path, profilepic);
		resp.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(is, resp.getOutputStream());
	}
	
	@GetMapping(value = "/logo/{logoname}",produces = MediaType.IMAGE_PNG_VALUE)
	public void servlogo(@PathVariable String logoname,HttpServletResponse resp) throws IOException {
		InputStream is=this.fserv.getres(logopath, logoname);
		resp.setContentType(MediaType.IMAGE_PNG_VALUE);
		StreamUtils.copy(is, resp.getOutputStream());
	}
	
	@DeleteMapping("/user/profile/delete/{prof}")
	public ResponseEntity<String> deletepic(@PathVariable String prof) throws IOException {
		
		String fpath=path+File.separator+prof;
		Path fullpath =Paths.get(fpath);
		String res=this.fserv.delpic(fullpath);
		return new ResponseEntity<String>(res,HttpStatus.OK);
	}

}
