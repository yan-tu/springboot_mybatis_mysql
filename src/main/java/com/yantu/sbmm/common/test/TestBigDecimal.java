package com.yantu.sbmm.common.test;

import java.math.BigDecimal;

public class TestBigDecimal {
	public static void main(String[] args) {
		BigDecimal a = new BigDecimal(0);
		BigDecimal b = new BigDecimal(1);
		a=a.add(b);
		System.out.println(a);
	}
}
