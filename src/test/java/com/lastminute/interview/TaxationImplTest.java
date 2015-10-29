package com.lastminute.interview;

import static com.lastminute.interview.FactoryMethods.createProduct;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class TaxationImplTest {

	private TaxationImpl taxation;
	
	@Before
	public void setUp() {
		taxation = new TaxationImpl();
	}

	
	@Test
	public void testGetRate_onlySaleTax() {
		taxation.setSaleTax(new BigDecimal("0.01"));
		taxation.setSaleTax("food", new BigDecimal("0.02"));
		taxation.setSaleTax("book", new BigDecimal("0.03"));
		
		assertEquals(new BigDecimal("0.01"), taxation.getRate(createProduct("Pills", "medical", false, null)));
		assertEquals(new BigDecimal("0.01"), taxation.getRate(createProduct("Pills", "medical", true, null)));
		assertEquals(new BigDecimal("0.02"), taxation.getRate(createProduct("Bread", "food", false, null)));
		assertEquals(new BigDecimal("0.02"), taxation.getRate(createProduct("Bread", "food", true, null)));
		assertEquals(new BigDecimal("0.03"), taxation.getRate(createProduct("Math", "book", false, null)));
		assertEquals(new BigDecimal("0.03"), taxation.getRate(createProduct("Math", "book", true, null)));
	}
	
	@Test
	public void testGetRate_onlyImportTax() {
		TaxationImpl taxation = new TaxationImpl();
		taxation.setImportTax(new BigDecimal("0.01"));
		taxation.setImportTax("food", new BigDecimal("0.02"));
		taxation.setImportTax("book", new BigDecimal("0.03"));
		
		assertEquals(new BigDecimal("0"), 	 taxation.getRate(createProduct("Pills", "medical", false, null)));
		assertEquals(new BigDecimal("0.01"), taxation.getRate(createProduct("Pills", "medical", true, null)));
		assertEquals(new BigDecimal("0"), 	 taxation.getRate(createProduct("Bread", "food", false, null)));
		assertEquals(new BigDecimal("0.02"), taxation.getRate(createProduct("Bread", "food", true, null)));
		assertEquals(new BigDecimal("0"), 	 taxation.getRate(createProduct("Math", "book", false, null)));
		assertEquals(new BigDecimal("0.03"), taxation.getRate(createProduct("Math", "book", true, null)));
	}
	
	@Test
	public void testGetRate_saleAndImportTax() {
		TaxationImpl taxation = new TaxationImpl();
		taxation.setSaleTax(new BigDecimal("0.01"));
		taxation.setSaleTax("food", new BigDecimal("0.02"));
		taxation.setSaleTax("book", new BigDecimal("0.03"));
		taxation.setImportTax(new BigDecimal("0.10"));
		taxation.setImportTax("food", new BigDecimal("0.20"));
		taxation.setImportTax("book", new BigDecimal("0.30"));
		
		assertEquals(new BigDecimal("0.01"), taxation.getRate(createProduct("Pills", "medical", false, null)));
		assertEquals(new BigDecimal("0.11"), taxation.getRate(createProduct("Pills", "medical", true, null))); //0.01 + 0.10
		assertEquals(new BigDecimal("0.02"), taxation.getRate(createProduct("Bread", "food", false, null)));
		assertEquals(new BigDecimal("0.22"), taxation.getRate(createProduct("Bread", "food", true, null))); //0.02 + 0.20
		assertEquals(new BigDecimal("0.03"), taxation.getRate(createProduct("Math", "book", false, null)));
		assertEquals(new BigDecimal("0.33"), taxation.getRate(createProduct("Math", "book", true, null))); //0.03 + 0.30
	}
}
