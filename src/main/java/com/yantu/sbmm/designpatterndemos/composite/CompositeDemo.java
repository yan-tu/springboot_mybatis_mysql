package com.yantu.sbmm.designpatterndemos.composite;

import java.util.ArrayList;
import java.util.Collection;
/**
 * 组合模式
 * @author YanTu
 * @date:2018年8月28日
 */
public class CompositeDemo {
	private static interface A{
		void save();
	}
	
	private static class AImpl implements A{

		@Override
		public void save() {
			System.out.println("save()");
		}
		
	}
	 
	//也可以不用实现A
	private static class CompositeA implements A{
		
		private Collection<A> list = new ArrayList<A>();

		@Override
		public void save() {
			for(A a : list){
				a.save();
			}
		}
		
	}
}
