package com.deppon.crm.module.client.common.dao;

import java.util.Map;

import com.deppon.crm.module.client.common.domain.InterfaceAddressConfig;

/**
 * @作者：罗典
 * @时间：2012-10-17
 * @描述：查询接口配置信息
 * */
public interface IInterfaceDao {
	/**
	 * @作者：罗典
	 * @时间：2012-10-17
	 * @描述：查询接口地址配置信息
	 * */
	public Map<String, InterfaceAddressConfig> getInterfaceAddress();

	/**
	 * @作者：罗典
	 * @时间：2012-10-17
	 * @描述：查询异常信息收件人配置信息
	 * */
	public String getEmialReceivers(String key);
}
