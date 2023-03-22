package com.example.cafe.serv.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.cafe.dao.orderdao;
import com.example.cafe.dao.proddao;
import com.example.cafe.dao.userdoa;
import com.example.cafe.entities.Cart;
import com.example.cafe.entities.Order;
import com.example.cafe.entities.Product;
import com.example.cafe.entities.User;
import com.example.cafe.exceptions.resourcenotfoundexception;
import com.example.cafe.payload.orderdto;
import com.example.cafe.payload.productdt;
import com.example.cafe.serv.orderserv;

@Service
public class orderservimple implements orderserv{
	
	@Autowired
	private proddao pdao;
	
	@Autowired
	private orderdao odao;
	
	@Autowired
	private userdoa udao;
	
	@Autowired
	private ModelMapper mmapper;

	@Override
	public orderdto createord(orderdto odt, Integer user_id, Integer prod_id) {
		// TODO Auto-generated method stub
		User user=this.udao.findById(user_id).orElseThrow(()-> new resourcenotfoundexception("user", "id",user_id));
		Product product=this.pdao.findById(prod_id).orElseThrow(()-> new resourcenotfoundexception("product", "id", prod_id));
		Order ord= this.mmapper.map(odt, Order.class);
		Date udate =new Date();
		ord.setOdate(new java.sql.Date(udate.getTime()));
		ord.setTotalamt(product.getPrice()*odt.getQuantity());
		ord.setProduct(product);
		ord.setUser(user);
		Order updord=this.odao.save(ord);
		return this.mmapper.map(updord, orderdto.class);
	}

	@Override
	public orderdto updateord(orderdto odt, Integer oid) {
		// TODO Auto-generated method stub
		Order ord=this.odao.findById(oid).orElseThrow(()-> new resourcenotfoundexception("order", "id", oid));
		ord.setQuantity(odt.getQuantity());
		Order updord=this.odao.save(ord);
		return this.mmapper.map(updord, orderdto.class);
	}

	@Override
	public void deleteord(Integer oid) {
		// TODO Auto-generated method stub
		Order delor=this.odao.findById(oid).orElseThrow(()-> new resourcenotfoundexception("order", "id",oid));
		this.odao.delete(delor);
		
	}

	@Override
	public List<orderdto> getallorders(Integer pageno, Integer pagesize, String s) {
		// TODO Auto-generated method stub
		PageRequest p= PageRequest.of(pageno, pagesize, Sort.by(s).ascending());
		Page<Order> pord=this.odao.findAll(p);
		List<Order> ords=pord.getContent();
		List<orderdto> odtos=ords.stream().map((ord)-> this.mmapper.map(ord, orderdto.class)).collect(Collectors.toList());
		return odtos;
	}

	@Override
	public orderdto getorder(Integer oid) {
		// TODO Auto-generated method stub
		Order ord=this.odao.findById(oid).orElseThrow(()->new resourcenotfoundexception("order", "id", oid));
		return this.mmapper.map(ord, orderdto.class);
	}

	@Override
	public List<orderdto> getordbyusr(Integer uid) {
		// TODO Auto-generated method stub
		User user=this.udao.findById(uid).orElseThrow(()->new resourcenotfoundexception("user", "id", uid));
		List<Order> usords=this.odao.findByUser(user);
		List<orderdto> uodto=usords.stream().map((ord)-> this.mmapper.map(ord, orderdto.class)).collect(Collectors.toList());
		return uodto;
	}

	@Override
	public List<orderdto> getordbyprod(Integer pid) {
		// TODO Auto-generated method stub
		Product prod=this.pdao.findById(pid).orElseThrow(()-> new resourcenotfoundexception("product", "id", pid));
		List<Order> pords=this.odao.findByProduct(prod);
		List<orderdto> podto=pords.stream().map((ord)-> this.mmapper.map(ord, orderdto.class)).collect(Collectors.toList());
		return podto;
	}

	@Override
	public List<orderdto> searchord(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<orderdto> checkord(List<Cart> cartitems, Integer uid) {
		// TODO Auto-generated method stub
		List<orderdto> ordlist=new ArrayList<>();
		User usr=udao.findById(uid).orElseThrow(()-> new resourcenotfoundexception("user", "id", uid));
		for(Cart c:cartitems) {
			Product prod=pdao.findById(c.getPid()).orElseThrow(()-> new resourcenotfoundexception("product", "pid", c.getPid()));
			Order ord=new Order();
			Date udate=new Date();
			ord.setOdate(new java.sql.Date(udate.getTime()));
			ord.setQuantity(c.getQuantity());
			ord.setUser(usr);
			ord.setProduct(prod);
			ord.setTotalamt(c.getQuantity()*c.getPrice());
			Order updord=this.odao.save(ord);
			orderdto odto=this.mmapper.map(updord, orderdto.class);
			
			ordlist.add(odto);
		}
		return ordlist;
	}

}
