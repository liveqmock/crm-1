package com.deppon.crm.module.client.common;

import java.util.Map;

import com.deppon.crm.module.client.common.dao.IInterfaceDao;
import com.deppon.crm.module.client.common.domain.InterfaceAddressConfig;

/**
 * @作者：罗典
 * @描述：接口地址提供类
 * @时间：2012-10-17
 * */
public class InterfaceAddressConfigProvider {
	private IInterfaceDao interfaceDao;
	private Map<String, InterfaceAddressConfig> addressMap;

	public InterfaceAddressConfig getAddressMap(String code) {
		if (addressMap == null) {
			addressMap = interfaceDao.getInterfaceAddress();
		}
		InterfaceAddressConfig config = addressMap.get(code);
		if (config == null) {
			throw new RuntimeException(code + "is not found.......");
		}
		return config;
	}

	public InterfaceAddressConfig getEmailInfo(String address) {
		if (addressMap == null) {
			addressMap = interfaceDao.getInterfaceAddress();
		}
		for (InterfaceAddressConfig config : addressMap.values()) {
			if (config.getAddress().equals(address) || config.getCode().equals(address)) {
				return config;
			}
		}
		return null;
	}

	public IInterfaceDao getInterfaceDao() {
		return interfaceDao;
	}

	public void setInterfaceDao(IInterfaceDao interfaceDao) {
		this.interfaceDao = interfaceDao;
	}
}
