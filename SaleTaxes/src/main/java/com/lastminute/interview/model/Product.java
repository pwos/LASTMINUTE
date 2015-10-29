package com.lastminute.interview.model;

import java.math.BigDecimal;

public class Product {
	
	private String name;
	private String category;
	private boolean imported;
	private BigDecimal price;

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public boolean isImported() {
		return imported;
	}
	
	public void setImported(boolean imported) {
		this.imported = imported;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
}
