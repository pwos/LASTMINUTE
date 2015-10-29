package com.lastminute.interview;

import com.lastminute.interview.model.Sale;
import com.lastminute.interview.model.SaleItem;

public class SalePrinterImpl implements SalePrinter {

	@Override
	public void print(Sale sale) {
		for (SaleItem item : sale.getItems()) {
			System.out.println(item.getQuantity() + " " + item.getProduct().getName() + ": " + item.getTotalGross());
		}
		System.out.println("Sales Taxes: " + sale.getTotalTax());
		System.out.println("Total: " + sale.getTotalGross());
	}
	
}
