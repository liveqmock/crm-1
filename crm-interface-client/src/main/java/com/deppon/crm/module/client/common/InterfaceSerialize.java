package com.deppon.crm.module.client.common;

import java.util.List;

import com.deppon.crm.module.client.common.domain.InterfaceConfig;
/**
 * 接口详信息保存接口
 * @author davidcun @2012-4-10
 */
public interface InterfaceSerialize {

	/**
	 * 接口详细信息执行保存动作
	 * @param       
	 * @return      
	 * @Throws 
	 * @author davidcun 2012-4-10
	 *
	 */
	public void serialize(List<InterfaceConfig> interfaceDetails);
}
