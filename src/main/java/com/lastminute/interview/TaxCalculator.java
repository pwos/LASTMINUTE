package com.lastminute.interview;

import com.lastminute.interview.model.Sale;


public interface TaxCalculator {
	
	/**
	 * Calculates taxes
	 * Result is stored in the sale object
	 * 
	 * @param sale
	 */
	void calculate(Sale sale);
}
