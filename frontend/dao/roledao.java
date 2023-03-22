package com.example.cafe.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cafe.entities.Role;

public interface roledao extends JpaRepository<Role, Integer> {
	
}
