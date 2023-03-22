package com.example.cafe.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.cafe.dao.userdoa;
import com.example.cafe.entities.User;
import com.example.cafe.exceptions.resourcenotfoundexception;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private userdoa udao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user=this.udao.findByEmail(username).orElseThrow(()->new resourcenotfoundexception("User", "email"+username,0 ));
		return user;
	}

}
