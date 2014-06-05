package com.deppon.crm.module.complaint.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.complaint.server.dao.impl.CellphoneMessageInfoDaoImpl;
import com.deppon.crm.module.complaint.shared.domain.CellphoneMessageInfo;

import junit.framework.TestCase;

public class CellphoneMessageInfoDaoTest extends TestCase{
	private CellphoneMessageInfoDaoImpl cpmiDao;
	private static ApplicationContext ctx = null;
	String[] xmls = new String[]{"DaoBeanTest.xml"};
	@Before
	public void setUp() throws Exception {
		try{
			if(ctx ==null){
				ctx = new ClassPathXmlApplicationContext(xmls);
			}
			cpmiDao = ctx.getBean(CellphoneMessageInfoDaoImpl.class);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInsertCellphoneMsgInfoList(){
		List<CellphoneMessageInfo> cellphoneMessageInfoList = new ArrayList<CellphoneMessageInfo>();
		for(int i = 0;i < 10;i++){
			CellphoneMessageInfo cellphoneMsgInfo = new CellphoneMessageInfo();
			cellphoneMsgInfo.setPhoneNumber("13581860690"+Integer.toString(i));
			cellphoneMsgInfo.setMsgContent("你好！有什么可以帮忙？");
			cellphoneMsgInfo.setSendStandardDeptCode("11469");
			cellphoneMsgInfo.setSenderEmpCode("111111");
			cellphoneMessageInfoList.add(cellphoneMsgInfo);
		}		
		cpmiDao.insertCellphoneMsgInfoList(cellphoneMessageInfoList);
	}
	
	@Test
	public void testUpdateCellphoneMsgInfoInfo(){
		CellphoneMessageInfo cellphoneMsgInfo = new CellphoneMessageInfo();
		cellphoneMsgInfo.setPerCount(20);
		List<CellphoneMessageInfo> list = cpmiDao.inquireCellphoneMsgInfo(cellphoneMsgInfo);	
		CellphoneMessageInfo cellphoneMsgInfoResult = list.get(0);
		cpmiDao.updateCellphoneMsgInfoInfo(cellphoneMsgInfoResult);
	}
	
	@Test
	public void testInquireCellphoneMsgInfo(){
		CellphoneMessageInfo cellphoneMsgInfo = new CellphoneMessageInfo();
		List<CellphoneMessageInfo> list = cpmiDao.inquireCellphoneMsgInfo(cellphoneMsgInfo);
		System.out.println(list.size());
	}
	
	@Test
	public void testUpdateCellphoneMsgInfoInfoAll(){
		List<String> ids=new ArrayList<String>();
		ids.add("111");
		ids.add("222");
		ids.add("333");
		cpmiDao.updateCellphoneMsgInfoInfoAll(ids);
	}
}
