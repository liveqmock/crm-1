/*package com.deppon.crm.test.client.jobs;

public class GCTest {

	public static void main(String[] args) throws InterruptedException {
		test();
		System.gc();
		
		Thread.sleep(10000);
	}
	
	public static void test(){
		Domain domain = new Domain();
	}
	
	public static class Domain{
		@Override
		protected void finalize() throws Throwable {
			super.finalize();
			System.out.println("---------------------");
		}
	}
}
*/