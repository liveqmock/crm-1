package com.deppon.crm.module.common.server.manager.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.deppon.crm.module.common.server.manager.ICheckHardwareManager;
import com.deppon.crm.module.common.server.util.GenerateToken;
import com.deppon.crm.module.common.shared.domain.CheckResultInfo;
import com.deppon.crm.module.common.shared.domain.HardWareInfo;

public class CheckHardwareManagerMock implements ICheckHardwareManager {

	
	private Logger log = LogManager.getLogger(getClass());
	
	
	@Override
	public String checkHardwareInfo(HardWareInfo info,String s,String v,String version) {
		String token = GenerateToken.generateTokenByHardWareInfo(info);
		log.info(info);
		return "200_split_" + token;
	}

	@Override
	public CheckResultInfo checkHardWareToken(String token) {
		return null;
	}

}
