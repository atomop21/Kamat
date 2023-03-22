package com.example.cafe.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class productdt {
	
	private int pid;
	@NotBlank()
	@Size(min=3 ,message = "product name cannot be empty")
	private String pname;
	@NotBlank()
	@Size(min=3 ,message = "product category cannot be empty")
	@Pattern(regexp = "^[A-Z]{2,}", message = "category should be in uppercase letters")
	private String category;
	@NotNull(message = "price cannot be Empty")
	private float price;
	private String image;
	
	public productdt(int pid, String pname, String category, float price, String image) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.category = category;
		this.price = price;
		this.image = image;
	}

	public productdt() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "productdt [pid=" + pid + ", pname=" + pname + ", category=" + category + ", price=" + price + ", image="
				+ image + "]";
	}
	
	
	

}
