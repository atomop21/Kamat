package com.example.cafe.payload;

import com.example.cafe.entities.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JwtAuthResp {
	
	private String token;
	private userdt user;
	private Roledt userole;

}
