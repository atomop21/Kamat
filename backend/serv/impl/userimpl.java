package com.example.cafe.serv.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.cafe.dao.roledao;
import com.example.cafe.dao.userdoa;
import com.example.cafe.entities.Role;
import com.example.cafe.entities.User;
import com.example.cafe.exceptions.resourcenotfoundexception;
import com.example.cafe.payload.userdt;
import com.example.cafe.serv.userserv;

@Service
public class userimpl implements userserv {
	
	@Autowired
	private userdoa udao;
	
	@Autowired
	private roledao rdao;
	
	@Autowired
	private ModelMapper mmaper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public userdt createuser(userdt us) {
		// TODO Auto-generated method stub
		User u=this.dtotouser(us);
		User savus=this.udao.save(u);
		return this.usertodt(savus);
	}

	@Override
	public userdt updateuser(userdt usd, Integer id) {
		// TODO Auto-generated method stub
		User us=udao.findById(id).orElseThrow(()-> new resourcenotfoundexception("user","id",id));
		
		us.setName(usd.getName());
		us.setEmail(usd.getEmail());
		us.setPass(usd.getPass());
		us.setPhone(usd.getPhone());
		us.setGender(usd.getGender());
		us.setProfilepic(usd.getProfilepic());
		User updus=this.udao.save(us);
		userdt upddt=this.usertodt(updus);
		return upddt;
		
	}

	@Override
	public userdt getuser(Integer id) {
		// TODO Auto-generated method stub
		User us=this.udao.findById(id).orElseThrow(()-> new resourcenotfoundexception("user", "id", id));
		return this.usertodt(us);
	}

	@Override
	public List<userdt> getusers() {
		// TODO Auto-generated method stub
		List<User> usrs= this.udao.findAll();
		List<userdt> usrdt = usrs.stream().map(usr-> this.usertodt(usr)).collect(Collectors.toList());
		return usrdt;
	}

	@Override
	public void deleteuser(Integer id) {
		// TODO Auto-generated method stub
		User us= this.udao.findById(id).orElseThrow(()-> new resourcenotfoundexception("user", "id", id));
		
		this.udao.delete(us);
	}
	
	private User dtotouser(userdt udt) {
		User usr=this.mmaper.map(udt, User.class);
		return usr;
	}
	
	public userdt usertodt(User us) {
		userdt dt=this.mmaper.map(us, userdt.class); 
		return dt;
		
		
	}

	@Override
	public userdt regnewuser(userdt us) {
		// TODO Auto-generated method stub
		System.out.println(us.getPass()+us.getEmail()+us.getName());
		User usr=this.mmaper.map(us, User.class);
		usr.setPass(this.passwordEncoder.encode(usr.getPassword()));
		
		Role r=this.rdao.findById(2).get();
		
		usr.setRole(r);
		User nusr=this.udao.save(usr);
		
		return this.mmaper.map(nusr, userdt.class);
	}

}
