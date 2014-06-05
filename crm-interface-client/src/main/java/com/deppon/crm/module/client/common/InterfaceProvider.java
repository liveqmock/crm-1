package com.deppon.crm.module.client.common;

import java.util.Map;

import com.deppon.crm.module.client.common.domain.InterfaceConfig;

public interface InterfaceProvider {

	
	public Map<String, InterfaceConfig> load();

	public Map<String, InterfaceConfig> reLoad();

}
