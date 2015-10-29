package com.lastminute.interview;

import com.lastminute.interview.model.Sale;
import com.lastminute.interview.model.SaleItem;

import dnl.utils.text.table.TextTable;


public class SaleStructuredPrinter implements SalePrinter {

	private static final String[] columns = {
		"CATEGORY",
		"NAME",
		"UNIT PRICE",
		"QUANTITY",
		"GROSS"
	};
	
	@Override
	public void print(Sale sale) {
		Object[][] data = new Object[sale.getItems().size()][];
		for (int i = 0; i < sale.getItems().size(); i++) {
			SaleItem item = sale.getItems().get(i);
			data[i] = new Object[columns.length];
			data[i][0] = item.getProduct().getCategory();
			data[i][1] = item.getProduct().getName();
			data[i][2] = item.getProduct().getPrice();
			data[i][3] = item.getQuantity();
			data[i][4] = item.getTotalGross();
		}
		
		TextTable textTable = new TextTable(columns, data);
		textTable.printTable();
		System.out.println("-------------------");
		System.out.println("TOTAL TAX: " + sale.getTotalTax());
		System.out.println("TOTAL:     " + sale.getTotalGross());
	}

}
