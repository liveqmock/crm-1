package com.deppon.crm.module.client.jobs;

import java.util.List;

/**
 * 日志发送接口,保证记录时序到达，但是接受者必须支持重复发送
 * @author davidcun @2012-5-4
 */
public interface ILogSender<T>{

	/**
	 * 批量发送日志，考虑时序问题，如果单条失败全部失败
	 * @param       
	 * @return 发送失败的记录的list下标
	 * @Throws 
	 * @author davidcun 2012-5-4
	 *
	 */
	public int[] send(List<T> logs);
	
	/**
	 * 单条日志发送
	 * @param       
	 * @return      
	 * @Throws 
	 * @author davidcun 2012-5-4
	 *
	 */
	public boolean send(T t);
}
