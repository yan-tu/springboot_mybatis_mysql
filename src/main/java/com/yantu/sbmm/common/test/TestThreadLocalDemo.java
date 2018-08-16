package com.yantu.sbmm.common.test;

import java.util.Random;


/**
 * ThreadLocal为每一个线程变量值的副本，
 * 是Java中一种较为特殊的线程绑定机制，
 * 是每一个线程都可以独立地改变自己的副本，
 * 而不会和其它线程的副本冲突。
 * @author YanTu
 * 2018年8月2日
 */
public class TestThreadLocalDemo implements Runnable {
	
	//创建线程局部变量studentLocal，在后面你会发现用来保存Student对象
	private final static ThreadLocal<Student> studentLocal = new ThreadLocal<Student>();

	public static void main(String[] args) {
		TestThreadLocalDemo td = new TestThreadLocalDemo();
		Thread t1 = new Thread(td,"a");
		Thread t2 = new Thread(td,"b");
		t1.start();
		t2.start();
	}
	
	@Override
	public void run() {
		accessStudent();
	}

	public void accessStudent() {
		//获取当前线程的名字
		String currentThreadName = Thread.currentThread().getName();
		System.out.println(currentThreadName+" is running!");
		//产生一个随机数并打印
		Random random = new Random();
		int age = random.nextInt(100);
		System.out.println("thread "+currentThreadName+" set age to:"+age);
		//获取一个Student对象，并将随机数年龄插入到对象属性中
		getStudent().setAge(age);
		for(int i = 0; i < 5; i++){
			System.out.println("thread "+currentThreadName+" "+i+" read age is:"+getStudent().getAge());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//System.out.println("thread "+currentThreadName+" second read age is:"+getStudent().getAge());
	}

	protected Student getStudent() {
		//Student student = new Student();

		//获取本地线程变量并强转
		Student student = (Student) studentLocal.get();
		//线程首次执行此方法的时候,student为null
		if(null == student){
			//创建一个Student对象，并保存到本地线程变量studentLocal中去
			student = new Student();
			studentLocal.set(student);
		}
		return student;
	}
	
}
