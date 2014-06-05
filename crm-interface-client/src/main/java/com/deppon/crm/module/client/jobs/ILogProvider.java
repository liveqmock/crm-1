package com.deppon.crm.module.client.jobs;

import java.util.List;
import java.util.Map;
/**
 * 
 * @author davidcun @2012-5-30
 */
public interface ILogProvider<T> {

	public Map<String, List<T>> load();
	
	public List<T> loadList();

	public List<T> loadList(int size);
	
	/**
	 * 清除已经多次加载的却还是重复加载的数据
	 * @param       
	 * @return      
	 * @Throws 
	 * @author davidcun 2012-5-30
	 *
	 */
	public void clearCache();
}
