package com.lastminute.interview;

import java.math.BigDecimal;

import com.lastminute.interview.model.Sale;
import com.lastminute.interview.model.SaleItem;

public class TaxCalculatorImpl implements TaxCalculator {

	private Taxation taxation;

	public Taxation getTaxation() {
		return taxation;
	}
	
	public void setTaxation(Taxation taxation) {
		this.taxation = taxation;
	}

	@Override
	public void calculate(Sale sale) {
		BigDecimal totalTax = BigDecimal.ZERO;
		BigDecimal totalGross = BigDecimal.ZERO;
		
		for (SaleItem item : sale.getItems()) {
			// net   = unit price * quantity
			// tax   = net * taxRate (rounded up to 0.05)
			// gross = net + tax
			BigDecimal taxRate = taxation.getRate(item.getProduct());
			BigDecimal itemNet = item.getProduct().getPrice().multiply( BigDecimal.valueOf(item.getQuantity()) );
			BigDecimal itemTax = MathUtils.roundUp(itemNet.multiply(taxRate), BigDecimal.valueOf(0.05));
			BigDecimal itemGross = itemNet.add(itemTax);
			
			totalTax = totalTax.add(itemTax);
			totalGross = totalGross.add(itemGross);

			item.setTotalGross(itemGross);
		}
		
		sale.setTotalTax(totalTax);
		sale.setTotalGross(totalGross);
	}
	
}
