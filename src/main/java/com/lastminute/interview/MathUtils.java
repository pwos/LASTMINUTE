package com.lastminute.interview;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathUtils {
	
	public static BigDecimal roundUp(BigDecimal value, BigDecimal to) {
		if (to.signum() != 0) {
			BigDecimal divided = value.divide(to, 0, RoundingMode.UP);
			value = divided.multiply(to);
		}
		return value;
	}
	
}
