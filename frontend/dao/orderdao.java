package com.example.cafe.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cafe.entities.Order;
import com.example.cafe.entities.Product;
import com.example.cafe.entities.User;
import com.example.cafe.payload.orderdto;

public interface orderdao  extends JpaRepository<Order, Integer>{
	
	List<Order> findByUser(User user);
	List<Order> findByProduct(Product product);
	
}
