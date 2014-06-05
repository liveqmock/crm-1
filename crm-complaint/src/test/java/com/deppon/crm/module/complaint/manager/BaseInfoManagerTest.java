/**
 * 
 */
package com.deppon.crm.module.complaint.manager;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.complaint.server.dao.IBaseInfoDao;
import com.deppon.crm.module.complaint.server.dao.impl.BaseInfoDaoImpl;
import com.deppon.crm.module.complaint.server.manager.IBaseInfoManager;
import com.deppon.crm.module.complaint.server.manager.impl.BaseInfoManagerImpl;
import com.deppon.crm.module.complaint.server.service.IBaseInfoService;
import com.deppon.crm.module.complaint.server.service.impl.BaseInfoServiceImpl;
import com.deppon.crm.module.complaint.shared.domain.BaseInfo;
import com.deppon.crm.module.complaint.shared.domain.BaseInfoSearchCondition;
import com.deppon.crm.module.complaint.util.UtilTest;
import com.deppon.foss.framework.server.context.UserContext;

import junit.framework.TestCase;

/**
 * @author Administrator
 *
 */
public class BaseInfoManagerTest extends TestCase{

	private BaseInfo baseInfo;
	private IBaseInfoManager baseInfoManager;
	private IBaseInfoService baseInfoService;
	private IBaseInfoDao baseInfoDao;
	private BaseInfoSearchCondition baseInfoSearchCondition;
	private List<BaseInfo> list;
	private User user;
	
	
	@Before
	public void setUp() throws Exception {
		//当managner对象不存在时创建
		if(null==baseInfoManager){
			baseInfoManager =(IBaseInfoManager)UtilTest.getAppContext().getBean("baseInfoManager");
		}
		//创建user对象
		if(null==user){
			user = new User();
			user.setId("150960");
			UserContext.setCurrentUser(user);
		}
		//创建Service对象
		if(null==baseInfoService){
			baseInfoService =(IBaseInfoService)UtilTest.getAppContext().getBean("baseInfoService");
		}
		//创建Dao对象
		if(null == baseInfoDao){
			baseInfoDao = (IBaseInfoDao)UtilTest.getAppContext().getBean("baseInfoDao");
		}
		baseInfoSearchCondition=new BaseInfoSearchCondition();
		//创建基础资料对象
		baseInfo = new BaseInfo();
		baseInfo.setTypeCode("COMPLAINT");
		baseInfo.setParentId(-1);
		baseInfo.setClassCode("COMPLAINT");
		baseInfo.setLevel(1);
		baseInfo.setLevelName("业务项");
		baseInfo.setBaseInfo("444aaaa");
		baseInfo.setIsLeaf(1);
	}
	
	
	@After
	public void tearDown() throws Exception {
		baseInfo=null;
	}
	/**
	 * 测试添加基础资料
	 */
	@Test
	public void testSaveBaseInfo(){
		//创建删除的List
		List<BaseInfo> baseInfoList = new ArrayList<BaseInfo>();
		//保存业务基础资料
		baseInfoManager.saveBaseInfo(baseInfo);
		//将该基础资料添加到List中
		baseInfoList.add(baseInfo);
		//对测试数据进行删除
		baseInfoDao.deleteBaseInfo(baseInfoList);
		//在List中移除基础资料对象
		baseInfoList.remove(baseInfo);
		baseInfo.setParentId(10);
		//添加业务类型，Level为3
		baseInfo.setLevel(3);
		baseInfo.setLevelName("业务类型");
		baseInfo.setBaseInfo("测试业务类型");
		//业务类型-处理语言
		baseInfo.setDeallan("444");
		//业务类型-反馈时限
		baseInfo.setFeedbackLimit("86400");
		//业务类型-处理时限
		baseInfo.setProcLimit("12600");
		//保存业务类型
		baseInfoManager.saveBaseInfo(baseInfo);
		baseInfoList.add(baseInfo);
		//删除业务类型
		baseInfoDao.deleteBaseType(baseInfoList);
		baseInfoDao.deleteBaseInfo(baseInfoList);
		baseInfoList.remove(baseInfo);
		baseInfo.setParentId(23);
		//保存业务场景，level为5
		baseInfo.setLevel(5);
		baseInfo.setLevelName("业务场景");
		baseInfo.setBaseInfo("测试业务场景");
		baseInfo.setParentId(4);
		//业务场景-场景原因
		baseInfo.setProcStandard("此处输入1000字");
		//保存业务场景
		baseInfoManager.saveBaseInfo(baseInfo);
		baseInfoList.add(baseInfo);
		//删除业务场景
		baseInfoDao.deleteBaseScene(baseInfoList);
		baseInfoDao.deleteBaseInfo(baseInfoList);
		baseInfoList.remove(baseInfo);
	}
	
	/**
	 * 测试修改基础资料
	 */
	@Test
	public void testUpdateBaseInfo(){
		List<BaseInfo> baseInfoList = new ArrayList<BaseInfo>();
		//保存基础资料
		baseInfoManager.saveBaseInfo(baseInfo);
		baseInfo.setBaseInfo("修改后的业务项");
		//修改业务基础资料
		baseInfoManager.updateBaseInfo(baseInfo);
		baseInfoList.add(baseInfo);
		//删除基础资料
		baseInfoDao.deleteBaseInfo(baseInfoList);
		
		baseInfo.setParentId(10);
		//添加业务类型，Level为3
		baseInfo.setLevel(3);
		baseInfo.setLevelName("业务类型");
		baseInfo.setBaseInfo("测试业务类型");
		//业务类型-处理语言
		baseInfo.setDeallan("444");
		//业务类型-反馈时限
		baseInfo.setFeedbackLimit("86400");
		//业务类型-处理时限
		baseInfo.setProcLimit("12600");
		//保存业务类型
		baseInfoManager.saveBaseInfo(baseInfo);
		//为基础资料名称赋新值
		baseInfo.setBaseInfo("修改业务类型");
		//修改业务类型
		baseInfoManager.updateBaseInfo(baseInfo);
		baseInfoList.add(baseInfo);
		baseInfoDao.deleteBaseType(baseInfoList);
		baseInfoDao.deleteBaseInfo(baseInfoList);
		
		baseInfo.setParentId(23);
		//保存业务场景，level为5
		baseInfo.setLevel(5);
		baseInfo.setLevelName("业务场景");
		baseInfo.setBaseInfo("测试业务场景");
		baseInfo.setParentId(4);
		//业务场景-场景原因
		baseInfo.setProcStandard("此处输入1000字");
		//保存业务场景
		baseInfoManager.saveBaseInfo(baseInfo);
		baseInfo.setBaseInfo("修改业务场景");
		baseInfoManager.updateBaseInfo(baseInfo);
		baseInfoList.add(baseInfo);
		//删除业务场景
		baseInfoDao.deleteBaseScene(baseInfoList);
		baseInfoDao.deleteBaseInfo(baseInfoList);
		baseInfoList.remove(baseInfo);
	}
	/**
	 * 测试删除基础资料
	 */
	@Test
	public void testDeleteBaseInfo(){
		//创建删除的List
		List<BaseInfo> baseInfoList = new ArrayList<BaseInfo>();
		//保存业务基础资料
		baseInfoManager.saveBaseInfo(baseInfo);
		//将该基础资料添加到List中
		baseInfoList.add(baseInfo);
		//对测试数据进行删除
		baseInfoManager.deleteBaseInfo(baseInfoList);
		//在List中移除基础资料对象
		baseInfoList.remove(baseInfo);
		baseInfo.setParentId(10);
		//添加业务类型，Level为3
		baseInfo.setLevel(3);
		baseInfo.setLevelName("业务类型");
		baseInfo.setBaseInfo("测试业务类型");
		//业务类型-处理语言
		baseInfo.setDeallan("444");
		//业务类型-反馈时限
		baseInfo.setFeedbackLimit("86400");
		//业务类型-处理时限
		baseInfo.setProcLimit("12600");
		//保存业务类型
		baseInfoManager.saveBaseInfo(baseInfo);
		baseInfoList.add(baseInfo);
		//删除业务类型
		baseInfoManager.deleteBaseInfo(baseInfoList);
		baseInfoList.remove(baseInfo);
		baseInfo.setParentId(23);
		//保存业务场景，level为5
		baseInfo.setLevel(5);
		baseInfo.setLevelName("业务场景");
		baseInfo.setBaseInfo("测试业务场景");
		baseInfo.setParentId(4);
		//业务场景-场景原因
		baseInfo.setProcStandard("此处输入1000字");
		//保存业务场景
		baseInfoManager.saveBaseInfo(baseInfo);
		baseInfoList.add(baseInfo);
		//删除业务场景
		baseInfoManager.deleteBaseInfo(baseInfoList);
		baseInfoList.remove(baseInfo);
	}
	/**
	 * 测试查询基础资料
	 */
	@Test
	public void testSearchChildBaseInfoByParentId(){
		//创建查询实体
		baseInfoSearchCondition = new BaseInfoSearchCondition();
		//为查询实体赋值
		baseInfoSearchCondition.setParentId(-1);
		baseInfoSearchCondition.setClassCode("COMPLAINT");
		baseInfoSearchCondition.setLevel(1);
		baseInfoSearchCondition.setTypeCode("COMPLAINT");
		baseInfoManager.searchChildBaseInfoByParentId(baseInfoSearchCondition);
	}
}
