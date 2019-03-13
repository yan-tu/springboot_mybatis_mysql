package com.yantu.sbmm.common.test.polymtest;
/**
 * 继承链中的调用顺序：this.show(O)、super.show(O)、this.show((super)O)、super.show((super)O)
 * @author YanTu
 * @date:2018年9月11日
 */
public class Test {
	public static void main(String[] args) {
		A a1 = new A();
		A a2 = new B();
		B b = new B();
		C c = new C();
		D d = new D();
		System.out.println("A a1 = new A();");
		System.out.println("A a2 = new B();");
		System.out.println("B b = new B();");
		System.out.println("C c = new C();");
		System.out.println("D d = new D();");
		System.out.println("-------------------------");
		

		System.out.println("1--a1.show(b):" + a1.show(b));
		System.out.println("2--a1.show(c):" + a1.show(c));
		System.out.println("3--a1.show(d):" + a1.show(d));
		System.out.println("4--a2.show(b):" + a2.show(b));
		System.out.println("5--a2.show(c):" + a2.show(c));
		System.out.println("6--a2.show(d):" + a2.show(d));
		System.out.println("7--b.show(b):" + b.show(b));
		System.out.println("8--b.show(c):" + b.show(c));
		System.out.println("9--b.show(d):" + b.show(d));
	}
}
