package com.deppon.crm.module.customer.server.testutils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.crm.module.customer.server.manager.impl.ContractManager;
import com.deppon.crm.module.customer.server.utils.ContractUtil;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.Contract;
import com.deppon.crm.module.customer.shared.domain.ContractDept;
import com.deppon.crm.module.customer.shared.domain.ContractDeptView;
import com.deppon.crm.module.customer.shared.domain.ContractDetailView;
import com.deppon.crm.module.customer.shared.domain.ContractOperatorLog;
import com.deppon.crm.module.customer.shared.domain.ContractView;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.Preferential;

public class ContractUtilTest {
	
	/**
	 * 
	 * <p>
	 * Description:测试 得到合同近3个月的发货情况<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-25
	 * void
	 */
	@Test
	public void test_getArrearaMount(){
		List<Map<String,String>> moneysLIst = new ArrayList<Map<String,String>>();
		
		Calendar cal = Calendar.getInstance();
		
		Map<String,String> map = new HashMap<String,String>();
		map.put(Constant.year, cal.get(Calendar.YEAR)+"");
		int month = cal.get(Calendar.MONTH)+1;
		map.put(Constant.month, month<10?"0"+month:month+"");
		map.put(Constant.deliverMoney, cal.get(Calendar.YEAR)+""+(month<10?"0"+month:month+""));
		moneysLIst.add(map);
		 map = new HashMap<String,String>();
		  cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)-1);
		  map.put(Constant.year, cal.get(Calendar.YEAR)+"");
	      month = cal.get(Calendar.MONTH)+1;
		  map.put(Constant.month, month<10?"0"+month:month+"");
		  map.put(Constant.deliverMoney, cal.get(Calendar.YEAR)+""+(month<10?"0"+month:month+""));
		moneysLIst.add(map);
		
		 map = new HashMap<String,String>();
		 cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)-1);
		  map.put(Constant.year, cal.get(Calendar.YEAR)+"");
	      month = cal.get(Calendar.MONTH)+1;
		  map.put(Constant.month, month<10?"0"+month:month+"");
		  map.put(Constant.deliverMoney, cal.get(Calendar.YEAR)+""+(month<10?"0"+month:month+""));
		moneysLIst.add(map);
		
		 map = new HashMap<String,String>();
		 cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)-1);
		  map.put(Constant.year, cal.get(Calendar.YEAR)+"");
	      month = cal.get(Calendar.MONTH)+1;
		  map.put(Constant.month, month<10?"0"+month:month+"");
		  map.put(Constant.deliverMoney, cal.get(Calendar.YEAR)+""+(month<10?"0"+month:month+""));
		moneysLIst.add(map);
		
		
		String arrearaMonut = ContractUtil.getArrearaMount(moneysLIst);
		
		Assert.assertNotNull(arrearaMonut);
		
     }
	
	/**
	 * 
	 * <p>
	 * Description:测试获得三个月发货最大值<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-25
	 * void
	 */
	@Test
	public void test_getMaxAmount(){
		List<Double> moneysLIst = new ArrayList<Double>();
		moneysLIst.add(0.12234334);
		moneysLIst.add(1234546.12);
		moneysLIst.add(32543.12);
		moneysLIst.add(1234546.13);
		
		Double r_d = ContractUtil.getMaxAmount(moneysLIst);
		Assert.assertEquals(new Double(1234546.13),r_d);
	}
	
	/**
	 * 
	 * <p>
	 * Description:测试合同view转化为合同bean<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-25
	 * void
	 */
	@Test
	public void test_changeContractDetailViewToContract(){
		
		Contract contract = ContractUtil.changeContractDetailViewToContract(null);
		Assert.assertNull(contract);
		
		ContractDetailView view = ContractUtil.changeContractToContractDetailView(null);
		Assert.assertNull(view);
		
		ContractDetailView contractDetailView = new ContractDetailView();
		contractDetailView.setCustId("564312");
		contractDetailView.setDeptName("天津事业部");
		contractDetailView.setGoodsName("goodsName");
		
		List<ContractDeptView> objList = new ArrayList<ContractDeptView>();
		ContractDeptView contractDept = new ContractDeptView();
		contractDept.setId("122345");
		objList.add(contractDept);
		contractDetailView.setContractDepartList(objList);
		
		
		 contract = ContractUtil.changeContractDetailViewToContract(contractDetailView);
		Assert.assertNotNull(contract);
	}

	/**
	 * 
	 * <p>
	 * Description:测试把合同对象转换成合同view对象<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-25
	 * void
	 */
	@Test
	public void test_createContractView(){
		
		Contract contract = new Contract();
		contract.setId("12345");
		
		Member member = new Member();
		member.setCustName("客户姓名");
		member.setCustNumber("custNumber");
		Contact contact = new Contact();
		contact.setId("987634");
		member.setMainContact(contact);
		contract.setMember(member);

		List<ContractDept> objList = new ArrayList<ContractDept>();
		ContractDept contractDept = new ContractDept();
		contractDept.setId("122345");
		objList.add(contractDept);
		contract.setContractDepartList(objList);
		
		List<ContractOperatorLog> ccoList= new ArrayList<ContractOperatorLog>();
		ContractOperatorLog cco = new ContractOperatorLog();
		cco.setId("136589");
		
		List<FileInfo> fileInfos = new ArrayList<FileInfo>();
		
		cco.setFileInfos(fileInfos);
		ccoList.add(cco);
		contract.setContractWorkflowList(ccoList);
		
		ContractView contractView = ContractUtil.createContractView(contract);
		Assert.assertNotNull(contractView);

		
	}
	
	/**
	 * 
	 * <p>
	 * Description:测试把合同对象转换成合同view对象<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-25
	 * void
	 */
	@Test
	public void test_createContractView_scan(){
		
		Contract contract = new Contract();
		contract.setId("12345");
		
		Member member = new Member();
		member.setCustName("客户姓名");
		member.setCustNumber("custNumber");
//		Contact contact = new Contact();
//		contact.setId("987634");
//		member.setMainContact(contact);
		contract.setMember(member);

		List<ContractDept> objList = new ArrayList<ContractDept>();
		ContractDept contractDept = new ContractDept();
		contractDept.setId("122345");
		objList.add(contractDept);
		contract.setContractDepartList(objList);
		
		List<ContractOperatorLog> ccoList= new ArrayList<ContractOperatorLog>();
		ContractOperatorLog cco = new ContractOperatorLog();
		cco.setId("136589");
		
		List<FileInfo> fileInfos = new ArrayList<FileInfo>();
		
		cco.setFileInfos(fileInfos);
		ccoList.add(cco);
		contract.setContractWorkflowList(ccoList);
		
		ContractView contractView = ContractUtil.createContractView(contract);
		Assert.assertNotNull(contractView);

		
	}
	/**
	 * 
	 * <p>
	 * Description:<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-25
	 * void
	 */
	@Test
	public void test_addSearchTime(){
		
		List<String> months = new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH)+1;
		months.add(month<10?"0"+month:month+"");
		
		ContractUtil.addSearchTime(new ArrayList<String>(),months, new Date());
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:测试设置月结额度查询时间----除本月外的近3个月<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2012-12-25
	 * void
	 */
	@Test
	public void test_setSearchTime(){
		
		List<String> years = new ArrayList<String>();
		years.add("2012");
		List<String> months = new ArrayList<String>();
		months.add("2");
		ContractUtil.setSearchTime(years, months, 3);
		
		for (String string : months) {
			System.out.println(string);
		}
		
		for (String string : years) {
			System.out.println("years---"+string);
		}
	}
	
	/**
	 * @Description:判断新增合同信息是否需要OA工作流. <br />
	 */
    @Test
	public void testIsNewNotNeedNotOAWorkFlow() {
		//参数合同Contract对象
		Contract contract = new Contract();
		//合同优惠信息
		Preferential preferential = new Preferential();
		//true 不需要工作流   false 需要工作流
		boolean flag = false;
		
		//只有这种情况不需要走工作流。输入：优惠类型为：月发月送，结款方式无：输入:true 不需要工作流
		contract.setId("1");
		contract.setPayWay(Constant.CONTRACT_PAYWAY_NOT_MONTH_END);
		contract.setPreferentialType(Constant.CONTRACT_PREFERENTIALTYPE_MONTH_SEND);
		preferential.setReceivePriceRate(1d);
		preferential.setAgentgathRate(1d);
		preferential.setDeliveryPriceRate(1d);
		preferential.setInsuredPriceRate(1d);
		preferential.setChargeRebate(1d);
		contract.setPreferential(preferential);
//		ContractManager c = new ContractManager();
		flag = ContractUtil.isNewNotNeedNotOAWorkFlow(contract);
		Assert.assertEquals(true, flag == true);
		
		//输入：优惠类型为折扣,折扣满足折扣规则时，结款方式：月结和无
		//输入：优惠类型为月发月送，结款方式为月结  这几种都需要走工作流
		//输出：false 需要走工作流
		//纯零担 test1 月结
		contract.setPayWay(Constant.CONTRACT_PAYWAY_NOT_MONTH);//月结
		flag = ContractUtil.isNewNotNeedNotOAWorkFlow(contract);
		Assert.assertEquals(true, flag == false);
		//test2 价格折扣
		contract.setPayWay(Constant.CONTRACT_PAYWAY_NOT_MONTH_END);//非月结
		contract.setPreferentialType(Constant.CONTRACT_PREFERENTIALTYPE_PRICE_REBATE);
		flag = ContractUtil.isNewNotNeedNotOAWorkFlow(contract);
		Assert.assertEquals(true, flag == false);
		//零担无工作流，快递生产工作流
		//快递为纯月结
		contract.setPayWay(Constant.CONTRACT_PAYWAY_NOT_MONTH_END);
		contract.setExPayWay(Constant.CONTRACT_PAYWAY_NOT_MONTH);
		contract.setPreferentialType(Constant.CONTRACT_PREFERENTIALTYPE_NOT_PREFERENTIAL);
		flag = ContractUtil.isNewNotNeedNotOAWorkFlow(contract);
		Assert.assertEquals(true, flag == false);
		//快递为价格折扣
		contract.setExPayWay(Constant.CONTRACT_PAYWAY_NOT_MONTH_END);
		contract.setExPreferentialType(Constant.CONTRACT_PREFERENTIALTYPE_PRICE_REBATE);
		flag = ContractUtil.isNewNotNeedNotOAWorkFlow(contract);
		Assert.assertEquals(true, flag == false);
	}
    /**
	 * @Description:判断修改合同信息是否需要OA工作流.  
	 * 				未修改结算限额且优惠类型是月发月送返回true不需要走工作流<br />
	 * 				待完善
	 */
	@Test
	public void testIsUpdateNotNeedNotOAWorkFlow() {
		//参数合同Contract
		Contract contract = new Contract();
		//合同优惠信息
		Preferential preferential = new Preferential();
		//校验结果
		boolean flag = false;
		
		try {
			
			//1.只要改变了结算金额，就需要走工作流
			contract = new Contract();
			Contract oldContract = new Contract();
			oldContract.setArrearaMount(10d);
			contract.setOldContract(oldContract);
			contract.setPreferentialType(Constant.CONTRACT_PREFERENTIALTYPE_MONTH_SEND);
			preferential.setReceivePriceRate(1d);
			preferential.setAgentgathRate(1d);
			preferential.setDeliveryPriceRate(1d);
			preferential.setInsuredPriceRate(1d);
			preferential.setChargeRebate(1d);
			contract.setPreferential(preferential);
//			method.setAccessible(true);//true时不进行java语言访问检查
			flag = ContractUtil.isUpdateNotNeedNotOAWorkFlow(contract);
//			method.setAccessible(false);//false时进行检查
			Assert.assertEquals(true, flag == false);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
