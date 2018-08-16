package com.yantu.sbmm.common.demo;

public class VolatileDemo {
	//volatile解决的是内存可见性问题（通过缓存一致性）、指令重排序（乱序执行）
	//但volatile并不能解决原子性问题
	private static volatile VolatileDemo demo;
	
	public static VolatileDemo getInstance(){
		if(demo == null){
			demo = new VolatileDemo();
		}
		return demo;
	}
	
	//会执行一个lock指令（缓存锁）
	public static void main(String[] args) {
		VolatileDemo.getInstance();
	}
}
