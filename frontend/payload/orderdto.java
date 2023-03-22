package com.example.cafe.payload;

import java.util.Date;

import com.example.cafe.entities.Product;
import com.example.cafe.entities.User;

import lombok.Data;

@Data
public class orderdto {
	
	private int oid;
	private Date odate;
	private int quantity;
	private float totalamt;
	private User user;
	private Product product;
	
}
