package com.example.cafe;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.cafe.dao.roledao;
import com.example.cafe.entities.Role;

@SpringBootApplication
public class CafeApplication implements CommandLineRunner{
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private roledao rdao;

	public static void main(String[] args) {
		SpringApplication.run(CafeApplication.class, args);
	}
	
	@Bean
	public ModelMapper mmap() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		try {
			Role rol1=new Role();
			rol1.setId(1);
			rol1.setName("ROLE_ADMIN");
			
			Role rol2=new Role();
			rol2.setId(2);
			rol2.setName("ROLE_NORMAL");
			
			List<Role> rol =List.of(rol1,rol2);
			this.rdao.saveAll(rol);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
