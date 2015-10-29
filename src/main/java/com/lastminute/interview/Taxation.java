package com.lastminute.interview;

import java.math.BigDecimal;

import com.lastminute.interview.model.Product;

public interface Taxation {

	/**
	 * Returns a tax rate for given product.
	 * 
	 * @param product
	 * 
	 * @return tax rate
	 */
	BigDecimal getRate(Product product);
	
}
