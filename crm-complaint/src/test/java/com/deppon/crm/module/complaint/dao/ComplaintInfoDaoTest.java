package com.deppon.crm.module.complaint.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.complaint.server.dao.impl.ComplaintInfoDaoImpl;
import com.deppon.crm.module.complaint.shared.domain.ComplaintInfo;

public class ComplaintInfoDaoTest {
	private ComplaintInfoDaoImpl complaintInfoDao;
	private static ApplicationContext ctx = null;
	String[] xmls = new String[]{"DaoBeanTest.xml"};
	@Before
	public void setUp() throws Exception {
		try{
			if(ctx ==null){
				ctx = new ClassPathXmlApplicationContext(xmls);
			}
			complaintInfoDao = ctx.getBean(ComplaintInfoDaoImpl.class);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInsertComplaintInfoList(){
		List<ComplaintInfo> complaintInfoList = new ArrayList<ComplaintInfo>();
		
		for(int i = 0;i < 10;i++){
			ComplaintInfo complaintInfo = new ComplaintInfo();
			complaintInfo.setEmpCode("00"+Integer.toString(i));
			complaintInfo.setComplaintCount(3);
			complaintInfo.setIsSend("0");
			complaintInfo.setFailureCount(2);
			complaintInfo.setCreatetime(new Date());
			complaintInfoList.add(complaintInfo);
		}
		complaintInfoDao.insertComplaintInfoList(complaintInfoList);
	}
	
	@Test
	public void testInquireComplaintInfo(){
		ComplaintInfo complaintInfo = new ComplaintInfo();
		complaintInfo.setPerCount(10);
		List<ComplaintInfo> list = complaintInfoDao.inquireComplaintInfo(complaintInfo);
		System.out.println(list.size());
		complaintInfoDao.updateComplaintInfoInfo(list.get(0));
	}
}
