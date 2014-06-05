package com.deppon.crm.module.client.common.dao.impl;

import java.util.Map;

import com.deppon.crm.module.client.common.dao.IInterfaceDao;
import com.deppon.crm.module.client.common.domain.InterfaceAddressConfig;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**
 * @作者：罗典
 * @时间：2012-10-17
 * @描述：查询接口配置信息
 * */
@SuppressWarnings("unchecked")
public class InterfaceDaoImpl extends iBatis3DaoImpl implements IInterfaceDao {
	// 接口配置命名空间
	private final String NAMESPACE = "com.deppon.crm.module.client.common.dao.IInterfaceDao.";
	// 查询所有地址配置
	private final String ADDRESS_CONFIG_ALL = "queryAll";
	// 查询所有地址配置
	private final String EMIAL_RECEIVERS = "queryEmailReceivers";

	/**
	 * @作者：罗典
	 * @时间：2012-10-17
	 * @描述：查询接口地址配置信息
	 * */
	@Override
	public Map<String, InterfaceAddressConfig> getInterfaceAddress() {
		return (Map<String, InterfaceAddressConfig>) this.getSqlSession()
				.selectMap(NAMESPACE + ADDRESS_CONFIG_ALL,
						"code");
	}

	/**
	 * @作者：罗典
	 * @时间：2012-12-1
	 * @描述：查询异常信息收件人配置信息
	 * */
	@Override
	public String getEmialReceivers(String key) {
		return (String)this.getSqlSession().selectOne(NAMESPACE+EMIAL_RECEIVERS,key);
	}

}
