package com.example.cafe.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.cafe.payload.response;

@RestControllerAdvice
public class globalExceptionHandler {
	
	@ExceptionHandler(resourcenotfoundexception.class)
	public ResponseEntity<response> resourcenotfoundexceptionhandler(resourcenotfoundexception rnf){
		String message=rnf.getMessage();
		response res=new response(message,false);
		
		return new ResponseEntity<response>(res,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> methodargsnotvalidhandler(MethodArgumentNotValidException man){
		Map<String,String> res=new HashMap<>();
		man.getBindingResult().getAllErrors().forEach((err)->{
		String fname=	((FieldError)err).getField();
		String message=err.getDefaultMessage();
		res.put(fname, message);
		});
		
		return new ResponseEntity<Map<String,String>>(res,HttpStatus.BAD_REQUEST);
		
	}

}
