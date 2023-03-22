package com.example.cafe.entities;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "kamat_order")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int oid;
	private Date odate;
	private int quantity;
	private float totalamt;
	
	@ManyToOne
	@JsonIgnore
	@JsonManagedReference
	private User user;
	
	@ManyToOne
	@JsonIgnore
	@JsonManagedReference
	private Product product;
	

	
}
