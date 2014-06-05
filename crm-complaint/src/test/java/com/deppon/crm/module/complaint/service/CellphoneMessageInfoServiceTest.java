package com.deppon.crm.module.complaint.service;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.complaint.server.manager.impl.ComplaintManager;
import com.deppon.crm.module.complaint.server.service.ICellphoneMessageInfoService;
import com.deppon.crm.module.complaint.server.service.impl.CellphoneMessageInfoServiceImpl;
import com.deppon.crm.module.complaint.shared.domain.CellphoneMessageInfo;
import com.deppon.crm.module.duty.util.TestUtil;

public class CellphoneMessageInfoServiceTest extends TestCase {
//	private static ApplicationContext ctx = null;
	private ICellphoneMessageInfoService cellphoneMessageInfoService = TestUtil.cellphoneMessageInfoService;
//	private ComplaintManager complaintManager;
	// String[] xmls = new String[]{"DaoBeanTest.xml"};
	String[] xmls = { 
			"com/deppon/crm/module/complaint/server/META-INF/springTest.xml",
			"classpath*:com/deppon/crm/module/**/server/META-INF/spring.xml"
		
			
//			"classpath*:com/deppon/crm/module/common/server/META-INF/spring.xml"
			};

//	@Before
//	public void setUp() throws Exception {
//		try {
//			if (ctx == null) {
//				ctx = new ClassPathXmlApplicationContext(xmls);
//			}
////			complaintManager = ctx.getBean(ComplaintManager.class);
//			cellphoneMessageInfoService = ctx.getBean(CellphoneMessageInfoServiceImpl.class);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	@Test
	public void testCellphoneMessageInfoService(){
		System.out.println(cellphoneMessageInfoService);
		CellphoneMessageInfo cellphoneMessageInfoCMD=new CellphoneMessageInfo();
		cellphoneMessageInfoCMD.setPerCount(1000);
		List<CellphoneMessageInfo> infos=cellphoneMessageInfoService.inquireCellphoneMsgInfo(cellphoneMessageInfoCMD);
	}
}
