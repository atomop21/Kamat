package com.example.cafe.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Getter
@Setter
@Entity
@Table(name = "role")
public class Role {
	
	@Id
	private int id;
	
	private String name;
	
	@OneToMany(mappedBy = "role",cascade = CascadeType.ALL)
	@JsonBackReference
	private List<User> user;
	
	

}
