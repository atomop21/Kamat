
package com.example.cafe.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
public class User implements UserDetails{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(nullable = false,length = 50)
	private String name;
	@Column(nullable = false,length=100)
	private  String email;
	@Column(nullable = false,length=255)
	private String pass;
	@Column(nullable = false,length=10)
	private long phone;
	@Column(nullable = false,length=10)
	private String gender;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	@JsonBackReference
	private List<Order> ord= new ArrayList<>();
	
	private String profilepic;
	
	@ManyToOne
	@JsonIgnore
	@JsonManagedReference
	private Role role;

	
	/**@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		List<GrantedAuthority> gauth=role.stream().map((rol)-> new SimpleGrantedAuthority(rol.getName())).collect(Collectors.toList());
		return gauth;
	}**/

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.pass;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		List<Role> roles =new ArrayList<>();
		 roles.add(role);
		 List<GrantedAuthority> gauth=roles.stream().map((rol)->new SimpleGrantedAuthority(rol.getName())).collect(Collectors.toList());
		return gauth;
	}

	
	
	
}
