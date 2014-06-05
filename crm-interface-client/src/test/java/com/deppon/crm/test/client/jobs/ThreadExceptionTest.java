/*package com.deppon.crm.test.client.jobs;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadExceptionTest {
	static ExecutorService executor = Executors.newFixedThreadPool(10);

	public static void main(String[] args) throws InterruptedException {
		runMe();
		
		Thread.sleep(10000);
	}
	public static void runMe(){
		Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println("----------"+t.getName());
				e.printStackTrace();
			}
		});
		executor.submit(new Task());
		executor.submit(new Task());
//		new Thread(new Task()).start();
	}
	public static class Task implements Runnable{

		@Override
		public void run() {
			throw new RuntimeException("asdfasdfsad");
		}
		
	}
}
*/