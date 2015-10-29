package com.lastminute.interview;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class MathUtilsTest {

	@Test
	public void testRoundUp_0() {
		assertEquals(new BigDecimal("0.0"), MathUtils.roundUp(BigDecimal.valueOf(0.0), BigDecimal.valueOf(0.0)));;
		assertEquals(new BigDecimal("0.01"), MathUtils.roundUp(BigDecimal.valueOf(0.01), BigDecimal.valueOf(0.0)));;
	}
	
	@Test
	public void testRoundUp_0_01() {
		assertEquals(new BigDecimal("0.00"), MathUtils.roundUp(BigDecimal.valueOf(0.0), BigDecimal.valueOf(0.01)));;
		assertEquals(new BigDecimal("0.01"), MathUtils.roundUp(BigDecimal.valueOf(0.01), BigDecimal.valueOf(0.01)));;
		assertEquals(new BigDecimal("0.02"), MathUtils.roundUp(BigDecimal.valueOf(0.02), BigDecimal.valueOf(0.01)));;
	}

	@Test
	public void testRoundUp_0_05() {
		assertEquals(new BigDecimal("0.00"), MathUtils.roundUp(BigDecimal.valueOf(0.0), BigDecimal.valueOf(0.05)));;
		assertEquals(new BigDecimal("0.05"), MathUtils.roundUp(BigDecimal.valueOf(0.01), BigDecimal.valueOf(0.05)));;
		assertEquals(new BigDecimal("0.05"), MathUtils.roundUp(BigDecimal.valueOf(0.05), BigDecimal.valueOf(0.05)));;
		assertEquals(new BigDecimal("0.10"), MathUtils.roundUp(BigDecimal.valueOf(0.06), BigDecimal.valueOf(0.05)));;
		assertEquals(new BigDecimal("0.10"), MathUtils.roundUp(BigDecimal.valueOf(0.10), BigDecimal.valueOf(0.05)));;
	}
}
