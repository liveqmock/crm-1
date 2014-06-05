package com.deppon.crm.module.client.jobs.impl;

import java.util.concurrent.atomic.AtomicBoolean;

import com.deppon.crm.module.client.jobs.ILock;

/**
 * 当前内存锁表，不能处理集群的情况
 * @author davidcun @2012-5-4
 */
public class MemryLock implements ILock {

	private AtomicBoolean flag = new AtomicBoolean(false);
	
	@Override
	public boolean isLock() {
		return flag.get();
	}

	@Override
	public void lock() {
		synchronized (MemryLock.class) {
			flag.set(true);
		}
	}

	@Override
	public void unLock() {
		synchronized (MemryLock.class) {
			flag.set(false);
		}
	}

}
