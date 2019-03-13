package com.yantu.sbmm.queue;

public class Demo {
	public synchronized void run1(){
		System.out.println("run1执行好了，发出通知");
//		this.notify();//通知其他线程，wait池中的线程 run2，run3（随机唤醒一个）
		this.notifyAll();//唤醒wait池中所有线程
	}
	public synchronized void run2(){
		System.out.println("刚刚进入run2方法");
		try {
			this.wait();//将当前线程放入等待池中，并释放锁
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("run2执行好了，发出通知");
	}
	public synchronized void run3(){
		System.out.println("刚刚进入run3方法");
		try {
			this.wait();//将当前线程放入等待池中，并释放锁
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("run3执行好了，发出通知");
	}
	
	public static void main(String[] args) {
		Demo demo = new Demo();
		new Thread(new Runnable(){

			@Override
			public void run() {
				demo.run2();
			}
			
		}).start();
		new Thread(new Runnable(){
			
			@Override
			public void run() {
				demo.run3();
			}
			
		}).start();
		new Thread(new Runnable(){
			
			@Override
			public void run() {
				demo.run1();
			}
			
		}).start();
	}
}
