package com.example.cafe.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cafe.entities.User;

public interface userdoa extends JpaRepository<User, Integer>{

	Optional<User> findByEmail(String email);
}
