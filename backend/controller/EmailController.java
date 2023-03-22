package com.example.cafe.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cafe.serv.emailserv;


@RestController
@RequestMapping("/kamat")
@CrossOrigin(origins = "http://localhost:3000")
public class EmailController {
	
	@Autowired
	private emailserv eserv;
	
	
	@PostMapping("/sendotp")
	public ResponseEntity<Integer> sendOtp(@RequestParam("email") String email) {
		
		Random random=new Random();
		int otp=random.nextInt(999999);
		
		String subject="verify your kamat Account";
		String message="Hi Thank you for Registering "+otp+" is your verification code";
		String to=email;
		
		Boolean f=this.eserv.sendEmail(to, subject, message);
		
		
		return new ResponseEntity<Integer>(otp,HttpStatus.OK);
		
	}

}
