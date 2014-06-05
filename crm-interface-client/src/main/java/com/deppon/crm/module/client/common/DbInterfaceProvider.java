package com.deppon.crm.module.client.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.client.common.domain.InterfaceConfig;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

@SuppressWarnings("unchecked")
public class DbInterfaceProvider extends iBatis3DaoImpl implements
		InterfaceProvider {
	private final String NAMESPACE = "com.deppon.crm.module.client.shared.domain.OrderLog.";
	private final String QUERY_INTERFACECONFIG = "queryInterfaceConfig";

	private Map<String, InterfaceConfig> iMap;

	@Override
	public Map<String, InterfaceConfig> load() {
		if (iMap == null || iMap.size()<1) {
			reLoad();
		}
		return iMap;
	}

	@Override
	public synchronized Map<String, InterfaceConfig> reLoad() {
		if (iMap!=null && iMap.size()>0) {
			return iMap;
		}
		List<InterfaceConfig> icList = (List<InterfaceConfig>) this
				.getSqlSession().selectList(NAMESPACE + QUERY_INTERFACECONFIG);
		if (iMap==null) {
			iMap = new HashMap<String, InterfaceConfig>();
		}
		for (InterfaceConfig ic : icList) {
			iMap.put(ic.getClassName(), ic);
		}
		return iMap;
	}
}
