package com.deppon.crm.module.client.jobs;

/**
 * 当前只有一个任务在跑，为不支持多个Job同时运行
 * @author davidcun @2012-5-4
 */
public interface ILock {

	/**
	 * 判断当前是否有任务在运行
	 * @param       
	 * @return      
	 * @Throws 
	 * @author davidcun 2012-5-4
	 *
	 */
	public boolean isLock();
	
	/**
	 * 如果没有任务在运行，首先需要锁住任务，在运行真正的任务
	 * @param       
	 * @return      
	 * @Throws 
	 * @author davidcun 2012-5-4
	 *
	 */
	public void lock();

	/**
	 * 为当前任务解锁
	 * @param       
	 * @return      
	 * @Throws 
	 * @author davidcun 2012-5-4
	 *
	 */
	public void unLock();
}
