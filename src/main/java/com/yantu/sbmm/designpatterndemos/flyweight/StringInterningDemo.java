package com.yantu.sbmm.designpatterndemos.flyweight;

public class StringInterningDemo {
	public static void main(String[] args) {
		String value= new String("Hello");//在Heap创建
		String newValue=value.intern();//放置常量池
	}
}
