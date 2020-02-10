package com.yantu.sbmm.common.util;

import org.junit.jupiter.api.Test;

/**
 * 进制工具类
 * @author YanTu
 * 2018年6月19日
 */
public class ScaleUtils {
	
	public static void main(String[] args) {
		System.out.println(decimalToBinary(10));
	}
	
	/**
	 * 十进制转二进制
	 */
	public static String decimalToBinary(Integer d){
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 32; i++) {
			int t = (d & 0x80000000>>>i)>>>(31-i);
			sb.append(t);
		}
		return sb.toString();
	}
	
	/**
	 * 与（&）预算
	 */
	@Test
	public void withOperation(){
		int a=6,b=10;
		System.out.println("a="+a+",binary:"+decimalToBinary(a));
		System.out.println("b="+b+",binary:"+decimalToBinary(b));
		System.out.println("-------------------位运算-------------------");
		System.out.println("[与运算规则：两个数都转为二进制，然后从高位开始比较，如果两个数都为1则为1，否则为0]");
		System.out.println("a&b="+(a&b)+",binary:"+decimalToBinary(a&b)+"\n");//与
		
		System.out.println("[或运算规则：两个数都转为二进制，然后从高位开始比较，两个数只要有一个为1则为1，否则就为0]");
		System.out.println("a|b="+(a|b)+",binary:"+decimalToBinary(a|b)+"\n");//或
		
		System.out.println("[非运算规则：如果位为0，结果是1，如果位为1，结果是0]");
		System.out.println("~a="+(~a)+",binary:"+decimalToBinary(~a)+"\n");//非
		
		System.out.println("[异或运算规则：两个数转为二进制，然后从高位开始比较，如果相同则为0，不相同则为1]");
		System.out.println("a^b="+(a^b)+",binary:"+decimalToBinary(a^b)+"\n");//异或
	}
}
