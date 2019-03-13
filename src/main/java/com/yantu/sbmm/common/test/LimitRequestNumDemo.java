package com.yantu.sbmm.common.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 限制最大并发数
 * 参考：https://blog.csdn.net/manzhizhen/article/details/81413014
 * @author YanTu
 * @date:2018年9月12日
 */
public class LimitRequestNumDemo {
	private static int MAX_REQUEST_NO = 100;//最大并发数
	
	private Object lock = new Object();
	
    private static Semaphore semaphore = new Semaphore(MAX_REQUEST_NO);
    
    /**
     * 方式一:通过信号量控制并发数
     * @author:YanTu
     * @return
     * @date:2018年9月12日
     */
    public static Integer methodA() {
        if(!semaphore.tryAcquire()) {
            return null;
        }
 
        try {
        // TODO 方法中的业务逻辑
        } finally {
            semaphore.release();
        }
		return null;
    }
    
    /**
     * 方式二：通过线程池控制
     */
    private final static ExecutorService pool = new ThreadPoolExecutor(100, 100, 1, TimeUnit.MINUTES, new SynchronousQueue<>());
    
    public static Integer methodAWrapper() {
        try {
            Future<Integer> future = pool.submit(() -> methodB());
            return future.get();
        } catch (Exception e) {
          return null;
        }
    }
 
    public static Integer methodB() {
        // TODO 方法中的业务逻辑
    	return null;
    }

    
    /**
     * 方式三：通过计数器来实现
     */
    private static AtomicInteger counter = new AtomicInteger(0);
    
    public static Integer methodC() {
 
        int value = counter.incrementAndGet();
        if(value > 100) {
            return null;
        }
 
        try {
            // TODO 方法中的业务逻辑
        } finally {
            counter.decrementAndGet();
        }
        return null;
    }

    /**
     * 方式四：阻塞队列
     */
    private static BlockingQueue<Integer> reqQueue = new ArrayBlockingQueue<>(100);
    
    public static Integer methodD() {
 
        if(!reqQueue.offer(MAX_REQUEST_NO)) {
            return null;
        }
 
        try {
            // TODO 方法中的业务逻辑
        } finally {
            reqQueue.poll();
        }
        return null;
    }

    /**
     * 通过线程方法控制wait，notify
     * 
     */
    private int num; 
    public Integer methodE(){
    	synchronized (lock) {
    		//int activeCount = Thread.activeCount();//当前活跃线程数
    		if(num >= MAX_REQUEST_NO){ 
    			try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    		}else{
    			lock.notify();
    		}
		}
    	num++;
    	
    	return null;
    }
    
    //其他思路：
    //给所有的线程加上同步（同一个锁），主线程在启动其他所有线程后wait()。
    //每个线程运行结束后，执行notify()方法。设定一个值为其他线程数量的int计数器count，写一个while循环，循环条件为count-->0，循环内容为wait()。则所有线程运行结束后正好while循环结束。
    //致命缺陷：如果几个线程同时运行结束，有可能在主线程还没运行时，已经执行了好几次notify()方法。如果这样的话，while循环就永远不会结束了，主线程一直wait()下去
    
    public static void main(String[] args) {
    	for(int i=1;i<=10;i++){
    		new Thread(new Runnable(){
    			
    			@Override
    			public void run() {
    			}
    			
    		},"Thread-demo").start();
    		
    	}
    	System.out.println("活跃线程数:"+Thread.activeCount());
	}
}
