package com.deppon.crm.module.common.server.util;

import com.deppon.crm.module.common.shared.domain.HardWareInfo;
import com.deppon.crm.module.common.shared.domain.LadingstationDepartment;

import junit.framework.TestCase;

public class GenerateTokenTest extends TestCase{
	public void testGenerateTokenByHardWareInfo(){
		HardWareInfo info = new HardWareInfo();
		info.setCPUNumber("JONE");
		info.setHostName("localhost");
		info.setMACAddress("anywhere");
		info.setFalg(false);
		GenerateToken.generateTokenByHardWareInfo(info);
	}
	
	public void testGenerateErrorHTML(){
		String message = "hello";
		GenerateToken.generateErrorHTML(message);
	}
	public void testGetTimeToken(){
		String message = "hello";
		GenerateToken.getTimeToken(message);
	}
	public void testLadingStationDeptReaderPojo(){
		LadingstationDepartment ld = new LadingstationDepartment();
		LadingstationDepartment obj = new LadingstationDepartment();
		 String errorMessage = "errorMessage";
		 Integer lineNo = 1;
		LadingStationDeptReaderPojo ladingStationDeptReaderPojo = new LadingStationDeptReaderPojo();
		ladingStationDeptReaderPojo = new LadingStationDeptReaderPojo(ld);
		ladingStationDeptReaderPojo = new LadingStationDeptReaderPojo(ld, errorMessage);
		ladingStationDeptReaderPojo.setLd(ld);
		ladingStationDeptReaderPojo.getLd();
		ladingStationDeptReaderPojo.setErrorMessage(errorMessage);
		ladingStationDeptReaderPojo.getErrorMessage();
		ladingStationDeptReaderPojo.setLineNo(lineNo);
		ladingStationDeptReaderPojo.getLineNo();
		ladingStationDeptReaderPojo.equals(null);
		ladingStationDeptReaderPojo.equals(obj);
		
		LadingStationDeptReaderPojo obiPojo = new LadingStationDeptReaderPojo();
		ladingStationDeptReaderPojo.equals(obiPojo);
		obiPojo.setLd(obj);
		ladingStationDeptReaderPojo.equals(obiPojo);
	}
}
