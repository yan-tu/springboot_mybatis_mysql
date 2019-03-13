package com.yantu.sbmm.designpatterndemos.decorator;

import java.io.DataInputStream;
import java.io.FilterInputStream;
import java.io.InputStream;

public class DecoratorDemo {
	public static void main(String[] args) {
		//被装饰者
		InputStream inputStream = null;
		
		//装饰者
		FilterInputStream filterInputStream = new DataInputStream(inputStream);
		
		//DataInputStream <- FilterInputStream <-InputStream
		//DataInputStream(InputStream)
	}
}
