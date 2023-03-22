package com.example.cafe.payload;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class userdt {
	private int id;
	@NotEmpty
	@Size(min=4,message = "username must be of atleast 4 characters")
	private String name;
	
	@NotEmpty
	@Email(message = "Email adddress not valid")
	private  String email;
	
	@NotEmpty
	@Pattern(regexp = "((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*+=]).{6,})",
    message = "password must contain atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit and password must be atleast 6 characters ")
	private String pass;
	
	@Digits(message="Number should contain 10 digits.", fraction = 0, integer = 10)
	private long phone;
	private String gender;
	
	private String profilepic;
	
	private Roledt role;
	
	public userdt(int id, String name, String email, String pass, long phone, String gender,String profilepic) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.pass = pass;
		this.phone = phone;
		this.gender = gender;
		this.profilepic=profilepic;
	}

	public userdt() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	@JsonIgnore
	public String getPass() {
		return pass;
	}

	@JsonProperty
	public void setPass(String pass) {
		this.pass = pass;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	
	
	public Roledt getRole() {
		return role;
	}

	public void setRole(Roledt roles) {
		this.role = roles;
	}
	
	

	public String getProfilepic() {
		return profilepic;
	}

	public void setProfilepic(String profilepic) {
		this.profilepic = profilepic;
	}

	
	
	

}
