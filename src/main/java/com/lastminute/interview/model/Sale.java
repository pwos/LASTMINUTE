package com.lastminute.interview.model;

import java.math.BigDecimal;
import java.util.List;

public class Sale {

	private List<SaleItem> items;
	private BigDecimal totalTax;
	private BigDecimal totalGross;
	
	public List<SaleItem> getItems() {
		return items;
	}
	
	public void setItems(List<SaleItem> items) {
		this.items = items;
	}
	
	public BigDecimal getTotalTax() {
		return totalTax;
	}
	
	public void setTotalTax(BigDecimal totalTax) {
		this.totalTax = totalTax;
	}
	
	public BigDecimal getTotalGross() {
		return totalGross;
	}
	
	public void setTotalGross(BigDecimal totalGross) {
		this.totalGross = totalGross;
	}

}
