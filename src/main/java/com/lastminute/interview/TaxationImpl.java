package com.lastminute.interview;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.lastminute.interview.model.Product;

/**
 * A simple implementation of {@code Taxation} which allows to have:
 * 
 * <li>default sell and import tax</li>
 * <li>sell and import tax by {@code Product} category</li>
 */
public class TaxationImpl implements Taxation {

	private BigDecimal saleTax = BigDecimal.ZERO;
	private BigDecimal importTax = BigDecimal.ZERO;
	private final Map<String, BigDecimal> saleTaxTable = new HashMap<>();
	private final Map<String, BigDecimal> importTaxTable = new HashMap<>();

	/**
	 * Sets the default Sale Tax 
	 * 
	 * @param taxRate
	 */
	public void setSaleTax(BigDecimal taxRate) {
		validateTaxRate(taxRate);
		saleTax = taxRate;
	}
	
	/**
	 * Sets the default Import Tax
	 * 
	 * @param taxRate
	 */
	public void setImportTax(BigDecimal taxRate) {
		validateTaxRate(taxRate);
		importTax = taxRate;
	}

	/**
	 * Sets the Sale Tax by product category
	 * 
	 * @param category
	 * @param taxRate
	 */
	public void setSaleTax(String category, BigDecimal taxRate) {
		validateTaxRate(taxRate);
		saleTaxTable.put(category, taxRate);
	}

	/**
	 * Sets the Import Tax by product category
	 * 
	 * @param category
	 * @param taxRate
	 */
	public void setImportTax(String category, BigDecimal taxRate) {
		validateTaxRate(taxRate);
		importTaxTable.put(category, taxRate);
	}
	
	@Override
	public BigDecimal getRate(Product product) {
		BigDecimal taxRate = getRateByCategory(saleTax, saleTaxTable, product.getCategory());
		if (product.isImported()) {
			taxRate = taxRate.add( getRateByCategory(importTax, importTaxTable, product.getCategory()) );
		}
		return taxRate;
	}
	
	private BigDecimal getRateByCategory(BigDecimal defaultRate, Map<String, BigDecimal> table, String category) {
		BigDecimal newRate = table.get(category);
		return (newRate != null)? newRate : defaultRate;
	}
	
	private void validateTaxRate(BigDecimal taxRate) {
		if (taxRate == null) {
			throw new IllegalArgumentException("Tax rate can't be null");
		}
		if (taxRate.signum() < 0) {
			throw new IllegalArgumentException("Tax rate must be greater or equal to zero");
		}
	}

}
