package com.lastminute.interview.model;

import java.math.BigDecimal;

public class SaleItem {
	
	private Product product;
	private int quantity;
	private BigDecimal totalGross;
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public BigDecimal getTotalGross() {
		return totalGross;
	}
	
	public void setTotalGross(BigDecimal totalPrice) {
		this.totalGross = totalPrice;
	}
	
}
