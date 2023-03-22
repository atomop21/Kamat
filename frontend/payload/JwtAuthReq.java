package com.example.cafe.payload;

import lombok.Data;

@Data
public class JwtAuthReq {
	
	private String username;
	
	private String pass;

}
