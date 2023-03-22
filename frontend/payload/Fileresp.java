package com.example.cafe.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Fileresp {
	
	private String img;
	private String message;
	private productdt pdt;
	private userdt user;
	
	
	
	public Fileresp(String img, String message, userdt udt) {
		super();
		this.img = img;
		this.message = message;
		this.user = udt;
	}


	public Fileresp(String img, String message, productdt pdt) {
		super();
		this.img = img;
		this.message = message;
		this.pdt = pdt;
	}
	
	
	
	
}
