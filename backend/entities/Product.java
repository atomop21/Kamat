package com.example.cafe.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;


@Data
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pid;
	@Column(nullable = false,length = 50)
	private String pname;
	@Column(nullable = false,length = 50)
	private String category;
	
	private String image;
	
	@OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
	@JsonBackReference
	private List<Order> ord =new ArrayList<>();
	private float price;

	

}
