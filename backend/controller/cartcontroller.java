package com.example.cafe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cafe.entities.Cart;
import com.example.cafe.payload.productdt;
import com.example.cafe.serv.cartserv;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/kamat")
public class cartcontroller {
	
	@Autowired
	private cartserv cserv;
		
	@PostMapping("/addtocart")
	public ResponseEntity<List<Cart>> addtocart(@RequestBody productdt pdto){
		List<Cart> cartitm= this.cserv.addprod(pdto);
		return new ResponseEntity<List<Cart>>(cartitm,HttpStatus.OK);
	}
	
	@DeleteMapping("/remcart/{pid}")
	public ResponseEntity<List<Cart>> deletefromcart(@PathVariable Integer pid){
		List<Cart> cartitem=this.cserv.deleteprod(pid);
		return new ResponseEntity<List<Cart>>(cartitem,HttpStatus.OK);
	}
	
	@PostMapping("/cart/inc")
	public ResponseEntity<List<Cart>> inccart(@RequestBody Cart c){
		List<Cart> cartitems=this.cserv.increment(c);
		
		
		return new ResponseEntity<List<Cart>>(cartitems,HttpStatus.OK);
		
	}
	
	@PostMapping("/cart/dec")
	public ResponseEntity<List<Cart>> deccart(@RequestBody Cart c){
		List<Cart> cartitems=this.cserv.decrement(c);
		return new ResponseEntity<List<Cart>>(cartitems,HttpStatus.OK);
	}
	
	@PostMapping("/cart/checkout")
	public float gettotprice(@RequestBody List<Cart> cartitem){
		System.out.println(cartitem);
		float totprice=this.cserv.checkout(cartitem);
		return  totprice;
	}
	
}
