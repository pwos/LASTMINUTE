package com.lastminute.interview;

import java.math.BigDecimal;
import java.util.Arrays;

import com.lastminute.interview.model.Product;
import com.lastminute.interview.model.Sale;
import com.lastminute.interview.model.SaleItem;

class FactoryMethods {
	
	static Product createProduct(String name, String category, boolean imported, BigDecimal price) {
		Product product = new Product();
		product.setName(name);
		product.setCategory(category);
		product.setImported(imported);
		product.setPrice(price);
		return product;
	}
	
	static SaleItem createSaleItem(Product product, int quantity, BigDecimal totalGross) {
		SaleItem saleItem = new SaleItem();
		saleItem.setProduct(product);
		saleItem.setQuantity(quantity);
		saleItem.setTotalGross(totalGross);
		return saleItem;
	}

	static Sale createSale(BigDecimal totalTax, BigDecimal totalGross, SaleItem ...items) {
		Sale sale = new Sale();
		sale.setTotalGross(totalGross);
		sale.setTotalTax(totalTax);
		sale.setItems(Arrays.asList(items));
		return sale;
	}
	
}
