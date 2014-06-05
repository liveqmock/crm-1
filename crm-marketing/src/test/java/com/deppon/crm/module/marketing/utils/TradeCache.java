package com.deppon.crm.module.marketing.utils;

import java.util.HashMap;
import java.util.Map;

import com.deppon.crm.module.common.shared.domain.Head;
import com.deppon.foss.framework.cache.ICache;

public class TradeCache implements ICache<String, Head> {
	private final String UUID = Head.class.getName();
	private Map<String, Head> map = new HashMap<String, Head>();
	@Override
	public String getUUID() {
		return UUID;
	}

	@Override
	public Head get(String key) {
		return map.get(key);
	}

	@Override
	public Map<String, Head> get() {
		return map;
	}

	public void put(String key, Head value) {
		map.put(key, value);
	}

	@Override
	public void invalid() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void invalid(String key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void invalidMulti(String... keys) {
		// TODO Auto-generated method stub
		
	}

}
