package com.lastminute.interview;

import java.math.BigDecimal;
import java.util.Arrays;

import com.lastminute.interview.model.Product;
import com.lastminute.interview.model.Sale;
import com.lastminute.interview.model.SaleItem;

public class Main {
	
	enum Category {
		BOOK,
		FOOD,
		MEDICAL,
		OTHER
	}
	
	public static void main(String[] args) {
		TaxationImpl taxation = new TaxationImpl();
		taxation.setImportTax(new BigDecimal("0.05"));
		taxation.setSaleTax(new BigDecimal("0.10"));
		taxation.setSaleTax(Category.BOOK.name(), BigDecimal.ZERO);
		taxation.setSaleTax(Category.FOOD.name(), BigDecimal.ZERO);
		taxation.setSaleTax(Category.MEDICAL.name(), BigDecimal.ZERO);
		
		System.out.println("*** EXAMPLE 1 ***");
		calc(taxation,
				create("book", Category.BOOK, false, 12.49, 1),
				create("music CD", Category.OTHER, false, 14.99, 1),
				create("chocolate", Category.FOOD, false, 0.85, 1)
		);
		
		System.out.println("*** EXAMPLE 2 ***");
		calc(taxation,
				create("imported box of chocolates", Category.FOOD, true, 10.0, 1),
				create("imported bottle of perfume", Category.OTHER, true, 47.5, 1)
		);
		
		System.out.println("*** EXAMPLE 3 ***");
		calc(taxation,
				create("imported bottle of perfume", Category.OTHER, true, 27.99, 1),
				create("bottle of perfume", Category.OTHER, false, 18.99, 1),
				create("headache pills", Category.MEDICAL, false, 9.75, 1),
				create("imported box of chocolates", Category.FOOD, true, 11.25, 1)
		);
	}

	private static void calc(Taxation taxation, SaleItem ... items) {
		Sale sale = new Sale();
		sale.setItems(Arrays.asList(items));
		
		TaxCalculatorImpl calculator = new TaxCalculatorImpl();
		calculator.setTaxation(taxation);
		calculator.calculate(sale);
		
//		new SalePrinterImpl().print(sale);
		new SaleStructuredPrinter().print(sale);
		System.out.println();
	}
	
	private static SaleItem create(String name, Category category, boolean imported, double price, int quantity) {
		Product product = new Product();
		product.setName(name);
		product.setCategory(category.name());
		product.setImported(imported);
		product.setPrice(BigDecimal.valueOf(price));
		
		SaleItem item = new SaleItem();
		item.setProduct(product);
		item.setQuantity(quantity);
		return item;
	}
}
