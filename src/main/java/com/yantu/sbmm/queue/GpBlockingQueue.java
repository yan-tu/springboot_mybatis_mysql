package com.yantu.sbmm.queue;

import java.util.ArrayList;
import java.util.List;

public class GpBlockingQueue {
	//队列存储的介质：ArrayList
	List<String> list = new ArrayList<String>();
	//整出一个同步锁
	private Object lock = new Object();
	
	//存储数据
	public void put(){
		//存储数据时保证线程是安全的
		synchronized (lock) {
			for(int i = 0; i< 10; i++){
				//每隔一秒钟再进行存储
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				list.add("Jack");
				System.out.println("线程"+Thread.currentThread().getName()+"添加第"+i+"个元素");

				//添加到某个元素时，通知其他线程来取数据
				//当前线程要释放锁，其他线程才能获得锁
				if(list.size() == 6){
					//数据已经添加了6个，你可以来取了
					lock.notify();// 发出通知，但是不释放锁
					System.out.println("线程"+Thread.currentThread().getName()+"发出通知");
				}
			}
		}
	}
	
	//取出数据
	public void get(){
		//wait使线程进入等待的状态并且释放锁
		synchronized (lock) {
			//进入等待状态，释放锁
			System.out.println("线程"+Thread.currentThread().getName()+"进入等待状态");
			try {
				lock.wait();
				//其他线程锁释放的时候才能取数据
				System.out.println("线程"+Thread.currentThread().getName()+"重新被唤醒");
				for (String s : list) {
					System.out.println("取出某个元素"+s);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		GpBlockingQueue gpBlockingQueue =  new GpBlockingQueue();
		new Thread(new Runnable(){

			@Override
			public void run() {
				gpBlockingQueue.get();
			}
			
		},"T2").start();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		new Thread(new Runnable(){

			@Override
			public void run() {
				gpBlockingQueue.put();
			}
			
		},"T1").start();
		

	}
}
