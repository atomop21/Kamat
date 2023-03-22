package com.example.cafe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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

import com.example.cafe.entities.Cart;
import com.example.cafe.entities.Product;
import com.example.cafe.entities.User;
import com.example.cafe.exceptions.resourcenotfoundexception;
import com.example.cafe.payload.orderdto;
import com.example.cafe.payload.response;
import com.example.cafe.serv.orderserv;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/kamat")
public class ordercontroller {
	
	@Autowired
	private orderserv oserv;

	@PostMapping("/user/{uid}/product/{pid}/order")
	public ResponseEntity<orderdto> createorder(@RequestBody orderdto odt,@PathVariable Integer uid,@PathVariable Integer pid){
		orderdto odto=this.oserv.createord(odt, uid, pid);
		return new  ResponseEntity<orderdto>(odto,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/order/{oid}")
	public ResponseEntity<orderdto> updateord(@RequestBody orderdto odt,@PathVariable Integer oid) {
		orderdto odto=this.oserv.updateord(odt, oid);
		return new ResponseEntity<orderdto>(odto,HttpStatus.OK);
	}
	
	@GetMapping("/orders")
	public ResponseEntity<List<orderdto>> allorders(
			@RequestParam(value="pageno",defaultValue ="0",required=false)Integer pageno,
			@RequestParam(value = "pagesize" ,defaultValue = "5",required = false)Integer pagesize,
			@RequestParam(value = "sortby" ,defaultValue = "oid",required = false)String s){
		List<orderdto> odto=this.oserv.getallorders(pageno,pagesize,s);
		return new ResponseEntity<List<orderdto>>(odto,HttpStatus.OK);
	}
	
	@GetMapping("order/{oid}")
	public ResponseEntity<orderdto> getorder(@PathVariable Integer oid){
		orderdto odto= this.oserv.getorder(oid);
		return new ResponseEntity<orderdto>(odto,HttpStatus.OK);
	}
	
	@GetMapping("/user/{uid}/order")
	public ResponseEntity<List<orderdto>> userords(@PathVariable Integer uid)
	{
		List<orderdto> uodto=this.oserv.getordbyusr(uid);
		return new ResponseEntity<List<orderdto>>(uodto,HttpStatus.OK);
	}
	
	@GetMapping("/product/{pid}/order")
	public ResponseEntity<List<orderdto>> prodord(@PathVariable Integer pid){
		List<orderdto> podto=this.oserv.getordbyprod(pid);
		return new ResponseEntity<List<orderdto>>(podto,HttpStatus.OK);
	}
	
	@DeleteMapping("/order/{oid}")
	public ResponseEntity<response> delord(@PathVariable Integer oid){
		this.oserv.deleteord(oid);
		return new ResponseEntity<response>(new response("order deleted successfully",true),HttpStatus.OK);
	}
	
	@PostMapping("/checkout/order/{uid}")
	public ResponseEntity<List<orderdto>> checkord(@RequestBody List<Cart> cartitems,@PathVariable Integer uid){
		List<orderdto> odto=this.oserv.checkord(cartitems, uid);
		return new ResponseEntity<List<orderdto>>(odto,HttpStatus.OK);
		
	}
}
