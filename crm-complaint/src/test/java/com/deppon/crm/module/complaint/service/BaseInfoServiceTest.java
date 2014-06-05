package com.deppon.crm.module.complaint.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.complaint.server.dao.IBaseInfoDao;
import com.deppon.crm.module.complaint.server.dao.impl.BaseInfoDaoImpl;
import com.deppon.crm.module.complaint.server.service.IBaseInfoService;
import com.deppon.crm.module.complaint.shared.domain.BaseInfo;
import com.deppon.crm.module.complaint.shared.domain.BaseInfoSearchCondition;
import com.deppon.crm.module.complaint.util.UtilTest;

/**
 * 工单基础资料 单元测试
 * @author 侯斌飞
 *
 */
public class BaseInfoServiceTest {
	private IBaseInfoService baseInfoService;
	private BaseInfo baseInfo;
	private IBaseInfoDao baseInfoDao;
	private BaseInfoSearchCondition baseInfoSearchCondition;
	@Before
	public void setUp() throws Exception {
		if(null==baseInfoService){
			baseInfoService =(IBaseInfoService)UtilTest.getAppContext().getBean("baseInfoService");
		}
		//当dao对象为空时创建dao对象
		if(null == baseInfoDao){
			baseInfoDao = (IBaseInfoDao)UtilTest.getAppContext().getBean("baseInfoDao");
		}
		//创建查询条件对象
		baseInfoSearchCondition=new BaseInfoSearchCondition();
		//创建基础资料对象
		baseInfo = new BaseInfo();
		baseInfo.setTypeCode("COMPLAINT");
		baseInfo.setParentId(-1);
		baseInfo.setClassCode("COMPLAINT");
		baseInfo.setLevel(1);
		baseInfo.setLevelName("业务项");
		baseInfo.setBaseInfo("444aaaa");
		baseInfo.setCreateUser("150960");
	}
	@After
	public void tearDown() throws Exception {
		//清空数据
		baseInfo = null;
	}
	
	
	@Test
	public void testSaveBaseInfo(){
		//保存基础资料，此方法可测试业务项，业务范围，业务场景
		baseInfoDao.saveBaseInfo(baseInfo);
		List<BaseInfo> baseInfoList = new ArrayList<BaseInfo>();
		baseInfoList.add(baseInfo);
		//删除基础资料
		baseInfoService.deleteBaseInfo(baseInfoList);
		baseInfoList.remove(baseInfo);
		baseInfoList = null;
	}
	
	@Test
	public void testSaveBaseType(){
		baseInfo.setLevel(3);
		baseInfo.setLevelName("业务类型");
		baseInfo.setDeallan("444");
		baseInfo.setFeedbackLimit("86400");
		baseInfo.setProcLimit("12600");
		//添加业务类型
		baseInfoService.saveBaseType(baseInfo);
		List<BaseInfo> baseInfoList = new ArrayList<BaseInfo>();
		baseInfoList.add(baseInfo);
		//删除业务类型
		baseInfoService.deleteBaseType(baseInfoList);
		baseInfoList.remove(baseInfo);
		baseInfoList = null;
	}
	
	@Test
	public void testSaveBaseScene(){
		baseInfo.setLevel(5);
		baseInfo.setLevelName("场景原因");
		baseInfo.setParentId(4);
		baseInfo.setBaseInfo("aaaaaa");
		baseInfo.setProcStandard("此处输入1000字");
		//添加场景原因
		baseInfoService.saveBaseScene(baseInfo);
		List<BaseInfo> baseInfoList = new ArrayList<BaseInfo>();
		baseInfoList.add(baseInfo);
		//删除场景原因
		baseInfoService.deleteBaseScene(baseInfoList);
		baseInfoList.remove(baseInfo);
		baseInfoList = null;
	}
	
	@Test
	public void testUpdateBaseInfo(){
		baseInfo.setBaseInfo("444aaaa");
		baseInfoService.saveBaseInfo(baseInfo);
		baseInfo.setBaseInfo("baseInfo");
		baseInfoService.updateBaseInfo(baseInfo);
		List<BaseInfo> baseInfoList = new ArrayList<BaseInfo>();
		baseInfoList.add(baseInfo);
		//删除基础资料
		baseInfoService.deleteBaseInfo(baseInfoList);
		baseInfoList.remove(baseInfo);
		baseInfoList = null;
		
	}
	
	@Test
	public void testUpdateBaseType(){
	
		baseInfo.setLevel(3);
		baseInfo.setLevelName("业务类型");
		baseInfo.setDeallan("444");
		baseInfo.setFeedbackLimit("86400");
		baseInfo.setProcLimit("12600");
		//添加业务类型
		baseInfoService.saveBaseType(baseInfo);
		baseInfo.setDeallan("测试业务类型");
		baseInfo.setFeedbackLimit("4444444");
		baseInfo.setProcLimit("555555");
		baseInfoService.updateBaseType(baseInfo);
		List<BaseInfo> baseInfoList = new ArrayList<BaseInfo>();
		baseInfoList.add(baseInfo);
		//删除业务类型
		baseInfoService.deleteBaseType(baseInfoList);
		baseInfoList.remove(baseInfo);
		baseInfoList = null;
	}
	
	@Test
	public void testUpdateBaseScene(){
		baseInfo.setLevel(5);
		baseInfo.setLevelName("场景原因");
		baseInfo.setParentId(4);
		baseInfo.setBaseInfo("aaaaaa");
		baseInfo.setProcStandard("此处输入1000字");
		//添加业务类型
		baseInfoService.saveBaseScene(baseInfo);
		baseInfo.setBaseInfo("场景原因XXXX");
		baseInfo.setProcStandard("此处输入1000字,ceshi");
		baseInfoService.updateBaseScene(baseInfo);
		List<BaseInfo> baseInfoList = new ArrayList<BaseInfo>();
		baseInfoList.add(baseInfo);
		//删除场景原因
		baseInfoService.deleteBaseScene(baseInfoList);
		baseInfoList.remove(baseInfo);
		baseInfoList = null;
	}
}
