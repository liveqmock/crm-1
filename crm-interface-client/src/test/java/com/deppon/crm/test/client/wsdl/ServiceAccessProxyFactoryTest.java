/*package com.deppon.crm.test.client.wsdl;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServiceAccessProxyFactoryTest {

	public void test1(){
		
	}
	
	*//**
	 * 并发测试
	 * @throws InterruptedException 
	 *//*
	public void test2() throws InterruptedException{
		int count = 20;
		//HelloWorld helloWorld = ServiceAccessProxyFactory.getInstance().getProxyServiceInterface(HelloWorld.class);
		
//		helloWorld.say("test");
		
		CyclicBarrier barrier = new CyclicBarrier(count-10);
		
		ExecutorService executorService = Executors.newFixedThreadPool(count);
		
		for (int i = 0; i < count; i++) {
			executorService.execute(new Task(barrier));
		}
		
		Thread.sleep(10000);
		executorService.shutdown();
	}
	
	class Task implements Runnable{
		
		CyclicBarrier cyclicBarrier = null;
		public Task(CyclicBarrier cyclicBarrier) {
			this.cyclicBarrier = cyclicBarrier;
		}
		
		public void run() {
			try {
				cyclicBarrier.await();
//				String str = helloWorld.say(Thread.currentThread().getName());
//				System.out.println(str);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	
}
*/