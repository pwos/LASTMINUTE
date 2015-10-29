package com.lastminute.interview;

import static com.lastminute.interview.FactoryMethods.createProduct;
import static com.lastminute.interview.FactoryMethods.createSale;
import static com.lastminute.interview.FactoryMethods.createSaleItem;
import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.lastminute.interview.model.Sale;

public class TaxCalculatorImplTest {

	private TaxCalculatorImpl calculator;

	@Before
	public void setUp() {
		TaxationImpl taxation = new TaxationImpl();
		taxation.setImportTax(new BigDecimal("0.05"));
		taxation.setSaleTax(new BigDecimal("0.10"));
		taxation.setSaleTax("food", BigDecimal.ZERO);
		taxation.setSaleTax("book", BigDecimal.ZERO);
		taxation.setSaleTax("medical", BigDecimal.ZERO);
		
		calculator = new TaxCalculatorImpl();
		calculator.setTaxation(taxation);
	}

	//
	// Tests with data from the exercise:
	//
	
	@Test
	public void testCalculate_Input1() {
		Sale sale = createSale(null, null, 
				createSaleItem(createProduct("book", "book", false, new BigDecimal("12.49")), 1, null),
				createSaleItem(createProduct("music CD", "other", false, new BigDecimal("14.99")), 1, null),
				createSaleItem(createProduct("chocolate bar", "book", false, new BigDecimal("0.85")), 1, null)
			);
		
		calculator.calculate(sale);
		
		assertEquals("total tax", new BigDecimal("1.50"), sale.getTotalTax());
		assertEquals("total gross", new BigDecimal("29.83"), sale.getTotalGross());
		assertEquals("1 book gross price", new BigDecimal("12.49"), sale.getItems().get(0).getTotalGross());
		assertEquals("1 music CD gross price", new BigDecimal("16.49"), sale.getItems().get(1).getTotalGross());
		assertEquals("1 chocolate bar", new BigDecimal("0.85"), sale.getItems().get(2).getTotalGross());
	}

	@Test
	public void testCalculate_Input2() {
		Sale sale = createSale(null, null, 
				createSaleItem(createProduct("imported box of chocolates", "food", true, new BigDecimal("10.00")), 1, null),
				createSaleItem(createProduct("imported bottle of perfume", "other", true, new BigDecimal("47.50")), 1, null)
			);
		
		calculator.calculate(sale);
		
		assertEquals("total tax", new BigDecimal("7.65"), sale.getTotalTax());
		assertEquals("total gross", new BigDecimal("65.15"), sale.getTotalGross());
		assertEquals("1 imported box of chocolates", new BigDecimal("10.50"), sale.getItems().get(0).getTotalGross());
		assertEquals("1 imported bottle of perfume", new BigDecimal("54.65"), sale.getItems().get(1).getTotalGross());
	}
	
	@Test
	public void testCalculate_Input3() {
		Sale sale = createSale(null, null, 
				createSaleItem(createProduct("imported bottle of perfume", "other", true, new BigDecimal("27.99")), 1, null),
				createSaleItem(createProduct("bottle of perfume", "other", false, new BigDecimal("18.99")), 1, null),
				createSaleItem(createProduct("packet of headache pills", "medical", false, new BigDecimal("9.75")), 1, null),
				createSaleItem(createProduct("imported box of chocolates", "food", true, new BigDecimal("11.25")), 1, null)
			);
		
		calculator.calculate(sale);
		
		assertEquals("total tax", new BigDecimal("6.70"), sale.getTotalTax());
		assertEquals("total gross", new BigDecimal("74.68"), sale.getTotalGross());
		assertEquals("1 imported bottle of perfume", new BigDecimal("32.19"), sale.getItems().get(0).getTotalGross());
		assertEquals("1 bottle of perfume", new BigDecimal("20.89"), sale.getItems().get(1).getTotalGross());
		assertEquals("1 packet of headache pills", new BigDecimal("9.75"), sale.getItems().get(2).getTotalGross());
		assertEquals("1 imported box of chocolate", new BigDecimal("11.85"), sale.getItems().get(3).getTotalGross());
	}

	
	//
	// Basic tests:
	//
	
	@Test
	public void testCalculate_freeProducts() {
		Sale sale = createSale(null, null, 
				createSaleItem(createProduct("x", "book", false, new BigDecimal("0.00")), 1, null),
				createSaleItem(createProduct("y", "other", false, new BigDecimal("0.00")), 1, null),
				createSaleItem(createProduct("imported x", "book", false, new BigDecimal("0.00")), 1, null),
				createSaleItem(createProduct("imported y", "other", false, new BigDecimal("0.00")), 1, null)
			);
		
		calculator.calculate(sale);
		
		assertEquals("total tax", new BigDecimal("0.00"), sale.getTotalTax());
		assertEquals("total gross", new BigDecimal("0.00"), sale.getTotalGross());
		assertEquals("1 x gross price", new BigDecimal("0.00"), sale.getItems().get(0).getTotalGross());
		assertEquals("1 y gross price", new BigDecimal("0.00"), sale.getItems().get(1).getTotalGross());
		assertEquals("1 imported x gross price", new BigDecimal("0.00"), sale.getItems().get(2).getTotalGross());
		assertEquals("1 imported y gross price", new BigDecimal("0.00"), sale.getItems().get(3).getTotalGross());
	}
	
	@Test
	public void testCalculate_variousQuantity() {
		Sale sale = createSale(null, null, 
				// 0% sale tax, 0% import tax:
				createSaleItem(createProduct("x", "book", false, new BigDecimal("10.00")), 0, null),
				createSaleItem(createProduct("x", "book", false, new BigDecimal("10.00")), 1, null),
				createSaleItem(createProduct("x", "book", false, new BigDecimal("10.00")), 2, null),
				// 10% sale tax, 0% import tax:
				createSaleItem(createProduct("y", "other", false, new BigDecimal("10.00")), 0, null),
				createSaleItem(createProduct("y", "other", false, new BigDecimal("10.00")), 1, null),
				createSaleItem(createProduct("y", "other", false, new BigDecimal("10.00")), 2, null),
				// 0% sale tax, 5% import tax:
				createSaleItem(createProduct("imported x", "book", true, new BigDecimal("10.00")), 0, null),
				createSaleItem(createProduct("imported x", "book", true, new BigDecimal("10.00")), 1, null),
				createSaleItem(createProduct("imported x", "book", true, new BigDecimal("10.00")), 2, null),
				// 10% sale tax, 5% import tax:
				createSaleItem(createProduct("imported y", "other", true, new BigDecimal("10.00")), 0, null),
				createSaleItem(createProduct("imported y", "other", true, new BigDecimal("10.00")), 1, null),
				createSaleItem(createProduct("imported y", "other", true, new BigDecimal("10.00")), 2, null)
			);
		
		calculator.calculate(sale);
		
		assertEquals("0 x gross price", new BigDecimal("0.00"),  sale.getItems().get(0).getTotalGross());
		assertEquals("1 x gross price", new BigDecimal("10.00"), sale.getItems().get(1).getTotalGross());
		assertEquals("2 x gross price", new BigDecimal("20.00"),  sale.getItems().get(2).getTotalGross());
		
		assertEquals("0 y gross price", new BigDecimal("0.00"),  sale.getItems().get(3).getTotalGross());
		assertEquals("1 y gross price", new BigDecimal("11.00"), sale.getItems().get(4).getTotalGross());
		assertEquals("2 y gross price", new BigDecimal("22.00"),  sale.getItems().get(5).getTotalGross());

		assertEquals("0 imported x gross price", new BigDecimal("0.00"),  sale.getItems().get(6).getTotalGross());
		assertEquals("1 imported x gross price", new BigDecimal("10.50"), sale.getItems().get(7).getTotalGross());
		assertEquals("2 imported x gross price", new BigDecimal("21.00"),  sale.getItems().get(8).getTotalGross());

		assertEquals("0 imported y gross price", new BigDecimal("0.00"),  sale.getItems().get(9).getTotalGross());
		assertEquals("1 imported y gross price", new BigDecimal("11.50"), sale.getItems().get(10).getTotalGross());
		assertEquals("2 imported y gross price", new BigDecimal("23.00"),  sale.getItems().get(11).getTotalGross());
	}

}
