package com.deppon.crm.module.client.jobs;

import java.util.List;

import com.deppon.crm.module.client.jobs.impl.LogStatus;
/**
 * 日志状态处理类
 * @author davidcun @2012-5-4
 */
public interface ILogProcess<T> {
	
	
	/**
	 * 批量更新日志
	 * @param       
	 * @return      
	 * @Throws 
	 * @author davidcun 2012-5-4
	 *
	 */
	public void update(List<T> logs,LogStatus status);
	
	/**
	 * 更新单条记录
	 * @param       
	 * @return      
	 * @Throws 
	 * @author davidcun 2012-5-4
	 *
	 */
	public boolean update(T t,LogStatus status);
	
}
