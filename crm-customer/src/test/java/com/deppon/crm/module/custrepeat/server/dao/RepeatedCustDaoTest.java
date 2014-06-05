package com.deppon.crm.module.custrepeat.server.dao;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.deppon.crm.module.customer.server.util.BeanUtil;
import com.deppon.crm.module.custrepeat.shared.domain.CustRepeatConstants;
import com.deppon.crm.module.custrepeat.shared.domain.RepeatedCustomer;
import com.deppon.crm.module.custrepeat.shared.domain.RepetitiveCustWorkFlowInfo;
import com.deppon.crm.module.custrepeat.shared.domain.SearchCondition;

import junit.framework.TestCase;

/**
 * 
 * <p>
 * Description:疑似重复客户DAO测试类<br />
 * </p>
 * @title RepeatedCustDaoTest.java
 * @package com.deppon.crm.module.custrepeat.server.dao 
 * @author 120750
 * @version 0.1 2014-3-5
 */
public class RepeatedCustDaoTest extends TestCase{
	//大客户管理dao
	private static IRepeatedCustDao repeatedCustDao;
	
	static{
		//通过测试类加载
		repeatedCustDao = BeanUtil.repeatedCustDao;
	}
	/**
	 * 
	 * <p>
	 * Description:测试删除疑似重复数据<br />
	 * </p>
	 * @author 120750
	 * @version 0.1 2014-3-5
	 * void
	 */
	@Test
	public void testDeleteRepeatCustList(){
		List<RepeatedCustomer> custList = new ArrayList<RepeatedCustomer>();
		RepeatedCustomer cust = new RepeatedCustomer();
		cust.setCustId("206036");
		custList.add(cust);
		repeatedCustDao.deleteRepeatCustList(custList);
	}
	
	/**
	 * 
	 * <p>
	 * Description:测试添加疑似重复数据 至 历史表<br />
	 * </p>
	 * @author 120750
	 * @version 0.1 2014-3-5
	 * void
	 */
	@Test
	public void testAddRepeatCustToHistory(){
		RepeatedCustomer cust = new RepeatedCustomer();
		cust.setCustId("196310");
		repeatedCustDao.addRepeatCustToHistory(cust);
	}
	
	/**
	 * 
	 * <p>
	 * Description:测试添加疑似重复数据<br />
	 * </p>
	 * @author 120750
	 * @version 0.1 2014-3-5
	 * void
	 */
	@Test
	public void testAddRepeatCust(){
		RepeatedCustomer cust = new RepeatedCustomer();
		cust.setCustId(new SimpleDateFormat("yyMMddhhmm:ss").format(new Date()));
		cust.setIsMainCust(1);
		cust.setDeliveryMoneyTotal(200.1);
		try{
			repeatedCustDao.addRepeatCust(cust);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * <p>
	 * Description:测试测试查询客户信息<br />
	 * </p>
	 * @author 120750
	 * @version 0.1 2014-3-5
	 * void
	 */
	@Test
	public void testSearchCustomerList(){
		SearchCondition searchCondition = new SearchCondition();
		searchCondition.setCustCode("724470");
		repeatedCustDao.searchCustomerList(searchCondition);
	}
	
	/**
	 * 
	 * <p>
	 * Description:测试当前客户是否存在疑似重复列表中<br />
	 * </p>
	 * @author 120750
	 * @version 0.1 2014-3-5
	 * void
	 */
	@Test
	public void testIsCustExistsRepeat(){
		//存在的
		repeatedCustDao.isCustExistsRepeat("724470");
		//不存在的
		repeatedCustDao.isCustExistsRepeat("20011825");
	}
	
	/**
	 * 
	 * <p>
	 * Description:测试定时器：清空疑似重复状态表<br />
	 * </p>
	 * @author 120750
	 * @version 0.1 2014-3-5
	 * void
	 */
	@Test
	public void testClearALLRepeatCustMark(){
		System.out.println("开始时间："+new Date());
		repeatedCustDao.clearALLRepeatCustMark();
		System.out.println("结束时间："+new Date());
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:测试定时器：赛选疑似重复数据组<br />
	 * </p>
	 * @author 120750
	 * @version 0.1 2014-3-5
	 * void
	 */
	@Test
	public void testDisposeRepeatCustData(){
//		repeatedCustDao.disposeRepeatCustData();
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:测试：根据客户编号删除已标记的疑似客户<br />
	 * </p>
	 * @author 120750
	 * @version 0.1 2014-3-5
	 * void
	 */
	@Test
	public void testDeleteMarkCustByCustId(){
		repeatedCustDao.deleteMarkCustByCustId("710593");
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:测试：疑似客户查询<br />
	 * </p>
	 * @author 120750
	 * @version 0.1 2014-3-5
	 * void
	 */
	@Test
	public void testSearchRepeatCustList(){
		SearchCondition sc = new SearchCondition();
		sc.setDeptId("24242");
		sc.setDeptLevel(CustRepeatConstants.DEPT_ORDINARY);
		repeatedCustDao.searchRepeatCustList(sc);
		
		// 测试分页
		sc.setStart(0);
		sc.setLimit(20);
		repeatedCustDao.searchRepeatCustList(sc);
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:测试：标记客户<br />
	 * </p>
	 * @author 120750
	 * @version 0.1 2014-3-5
	 * void
	 */
	@Test
	public void testMarkCustRepeat(){
		repeatedCustDao.markCustRepeat("710593");
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:测试：疑似客户分页Count<br />
	 * </p>
	 * @author 120750
	 * @version 0.1 2014-3-5
	 * void
	 */
	@Test
	public void testSearchRepeatCustCount(){
		SearchCondition sc = new SearchCondition();
		sc.setDeptId("24242");
		sc.setDeptLevel(CustRepeatConstants.DEPT_ORDINARY);
		repeatedCustDao.searchRepeatCustCount(sc);
	}
	/**
	 * 保存工作流
	 */
	@Test
	public void testSaveWorkFlowInfo(){
		RepetitiveCustWorkFlowInfo info = new RepetitiveCustWorkFlowInfo();
		repeatedCustDao.saveWorkFlowInfo(info);
	}
	/**
	 * 查询工作流
	 */
	@Test
	public void testFindRepetitiveCustWorkFlowInfoByWorkNo(){
		repeatedCustDao.findRepetitiveCustWorkFlowInfoByWorkNo("ICRM201403280000001");
	}
	/**
	 * 更新工作流状态
	 */
	@Test
	public void testUpdateRepetitiveCustWorkFlowInfo(){
		RepetitiveCustWorkFlowInfo info = new RepetitiveCustWorkFlowInfo();
		info.setRepetitveCustWorkFlowStatus("1");
		info.setRepetitveCustWorkFlowId("ICRM201403280000001");
		repeatedCustDao.updateRepetitiveCustWorkFlowInfo(info);
	}
	@Test
	public void testSearchRepeatCustDeptList(){
		SearchCondition searchCondition = new SearchCondition();
		searchCondition.setDeptType("DEPT_EXPRESS");
		searchCondition.setStart(1);
		searchCondition.setLimit(5);
		searchCondition.setCurrtDeptName("上海闵行区浦江镇营业部");
		searchCondition.setDeptName("上海闵行区浦江镇营业部");
		repeatedCustDao.searchRepeatCustDeptList(searchCondition);
	}
	
	@Test
	public void testSearchRepeatCustDeptCount(){
		SearchCondition searchCondition = new SearchCondition();
		searchCondition.setDeptType("DEPT_EXPRESS");
		searchCondition.setStart(1);
		searchCondition.setLimit(5);
		searchCondition.setCurrtDeptName("上海闵行区浦江镇营业部");
		searchCondition.setDeptName("上海闵行区浦江镇营业部");
		repeatedCustDao.searchRepeatCustDeptCount(searchCondition);
	}
	
	@Test
	public void testUpdateRepeatCustStatus(){
		RepeatedCustomer cust = new RepeatedCustomer();
		cust.setCustId("472464");
		cust.setMainCustId("8333");
		repeatedCustDao.updateRepeatCustStatus(cust);
		
		cust.setCustId("3300");
		cust.setMainCustId("3300");
		cust.setIsMainCust(1);
		repeatedCustDao.updateRepeatCustStatus(cust);
	}
	
	@Test
	public void testSelectCustIsMarked(){
		repeatedCustDao.selectCustIsMarked("400103112");
		
		repeatedCustDao.selectCustIsMarked("");
	}
	
	@Test
	public void testSearchWorkFlowNoByCustId(){
		repeatedCustDao.searchWorkFlowNoByCustId("683642");
		
		repeatedCustDao.searchWorkFlowNoByCustId("");
	}
}
