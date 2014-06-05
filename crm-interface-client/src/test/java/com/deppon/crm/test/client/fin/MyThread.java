package com.deppon.crm.test.client.fin;

public class MyThread implements Runnable {

	@Override
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
