package com.example.cafe.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cafe.entities.Product;

public interface proddao extends JpaRepository<Product, Integer> {
	List<Product> findByCategoryContaining(String key);
	List<Product> findByPnameContaining(String key);
}
