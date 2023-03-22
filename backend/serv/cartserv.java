package com.example.cafe.serv;

import java.util.List;

import com.example.cafe.entities.Cart;
import com.example.cafe.payload.productdt;

public interface cartserv {
	
	List<Cart> addprod(productdt pdto);
	List<Cart> deleteprod(Integer pid);
	List<Cart> increment(Cart c);
	List<Cart> decrement(Cart c);
	float checkout(List<Cart> cartitem);
}
