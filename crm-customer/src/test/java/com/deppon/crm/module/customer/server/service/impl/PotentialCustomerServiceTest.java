//package com.deppon.crm.module.customer.server.service.impl;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import junit.framework.Assert;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import com.deppon.crm.module.customer.server.util.BeanUtil;
//import com.deppon.crm.module.customer.server.util.SpringTestHelper;
//import com.deppon.crm.module.customer.shared.domain.PotentialCustomer;
//
//public class PotentialCustomerServiceTest extends BeanUtil{
//	
//	@Before
//	public void setUp() throws Exception{
//		clearData();
//	}
//	
//	@After
//	public void tearDown() throws Exception{
//		//clearData();
//	}
//	
//	/**
//	 * 
//	 * <p>
//	 * Description:测试修改潜客信息<br />
//	 * </p>
//	 * @author 王明明
//	 * @version 0.1 2012-12-18
//	 * void
//	 * @throws Exception 
//	 */
//	@Test
//	public void test_updatePotentialCustomer() throws Exception{
//		//初始化数据
//		initUpgradePotentialCustomer();
//		PotentialCustomer pc = new PotentialCustomer();
//		pc.setId("1");
//		pc.setCustName("客户姓名1");
//		pc.setTrade("计算机软件1");
//		pc.setCustbase("客户来源1");
//		pc.setMemberNo("1111111");
//		pc.setLinkManName("联系人姓名1");
//				
//		pc.setLinkManMobile("13825695845");
//		pc.setLinkManPhone("02136985215");
//		pc.setPost("开发经理1");
//		pc.setBussinesState("商机无限1");
//		pc.setCityId("3");	
//		pc.setCity("上海市");
//		pc.setAddress("上海市青浦区明珠路1080号");
//		
//		
//		potentialCustomerService.updatePotentialCustomer(pc);
//		pc.setCustType("PC_CUSTOMER");
//		
//		
//		PotentialCustomer r_pc = potentialCustomerService.getPotentialCustomer(pc.getId());
//		assertEquals(pc,r_pc);
//	
//	}
//	
//	/**
//	 * 
//	 * <p>
//	 * Description:测试潜客升级操作,并验证操作数据是否正确<br />
//	 * </p>
//	 * @author 王明明
//	 * @version 0.1 2012-12-18
//	 * @throws Exception
//	 * void
//	 */
//
//	public void test_upgradePotentialCustomer() throws Exception{
//		//初始化数据
//		initUpgradePotentialCustomer();
//		PotentialCustomer pc = new PotentialCustomer();
//		pc.setId("1");
//		pc.setCustName("客户姓名1");
//		pc.setTrade("计算机软件1");
//		pc.setCustbase("客户来源1");
//		pc.setMemberNo("1111111");
//		pc.setLinkManName("联系人姓名1");
//		
//		pc.setLinkManMobile("13825695845");
//		pc.setLinkManPhone("02136985215");
//		pc.setPost("开发经理1");
//		pc.setBussinesState("商机无限1");
//		pc.setCityId("3");
//		
//		pc.setCity("上海市");
//		pc.setAddress("上海市青浦区明珠路1080号");
//		pc.setCustType("SC_CUSTOMER");
//		pc.setIsCancel(0);
//		potentialCustomerService.upgradePotentialCustomer(pc, "12345678");
//		// 查询潜客信息
//		PotentialCustomer r_pc = potentialCustomerService.getPotentialCustomer(pc.getId());
//		assertEquals(pc,r_pc);
//		Assert.assertEquals(pc.getInfoState(), r_pc.getInfoState());
//		Assert.assertEquals(pc.getScatterId(), r_pc.getScatterId());
//		
//	}
//	
//	
//	
//	/**
//	 * 
//	 * <p>
//	 * Description:测试保存潜客信息，并验证保存数据是否正确<br />
//	 * </p>
//	 * @author 王明明
//	 * @version 0.1 2012-12-18
//	 * void
//	 */
//	public void test_createPotentialCustomer(){
//		PotentialCustomer pc = new PotentialCustomer();
//		pc.setCustName("客户姓名");
//		pc.setTrade("计算机软件");
//		pc.setCustbase("客户来源");
//		pc.setMemberNo("111111");
//		pc.setLinkManName("联系人姓名");
//		
//		pc.setLinkManMobile("13825695841");
//		pc.setLinkManPhone("02136985214");
//		pc.setPost("开发经理");
//		pc.setBussinesState("商机无限");
//		pc.setCityId("3");
//		
//		pc.setCity("上海市");
//		pc.setAddress("上海市青浦区明珠路1080号");
//		pc.setCustType("PC_CUSTOMER");
//		// 调用保存
//		potentialCustomerService.createPotentialCustomer(pc);
//		Assert.assertNotNull(pc.getId());//验证保存潜客ID不为null
//
//		// 调用查询
//		PotentialCustomer r_pc = potentialCustomerService.getPotentialCustomer(pc.getId());
//		//验证数据插入是否正确
//		assertEquals(pc,r_pc);
//		
//		potentialCustomerService.deletePotentialCustomer(pc.getId());
//
//	}
//	
//	
//    /**
//     * 初始化测试 潜客升级操作数据
//     * @throws Exception 
//     * */
//	private void initUpgradePotentialCustomer() throws Exception{
//		List<String> sqlList = new ArrayList<String>();
//		sqlList.add("insert into t_cust_potentialscatter(FID,FCUSTNAME,FTRADE,fcustbase,fmemberno,flinkmanname," +
//				                  "flinkmanmobile,flinkmanphone,FOFFICE,FBUSINESSSTATE,fcity,faddress,fcustType,fiscancel)"+
//                             " values(1,'客户姓名','计算机软件','客户来源','111111','联系人姓名',"+
//                                   "'13825695841','02136985214','开发经理','商机无限',3,'上海市青浦区明珠路1080号','PC_CUSTOMER',0)");
//		
//		SpringTestHelper.executeBatch(sqlList);
//	}
//	
//	//验证数据插入是否正确
//	private void assertEquals(PotentialCustomer pc,PotentialCustomer r_pc){
//		Assert.assertEquals(pc.getCustName(), r_pc.getCustName());
//		Assert.assertEquals(pc.getTrade(), r_pc.getTrade());
//		Assert.assertEquals(pc.getCustbase(), r_pc.getCustbase());
//		Assert.assertEquals(pc.getMemberNo(), r_pc.getMemberNo());
//		Assert.assertEquals(pc.getLinkManName(), r_pc.getLinkManName());
//		
//		Assert.assertEquals(pc.getLinkManMobile(), r_pc.getLinkManMobile());
//		Assert.assertEquals(pc.getLinkManPhone(), r_pc.getLinkManPhone());
//		Assert.assertEquals(pc.getPost(), r_pc.getPost());
//		Assert.assertEquals(pc.getBussinesState(), r_pc.getBussinesState());
//		Assert.assertEquals(pc.getCity(), r_pc.getCity());
//		
//		Assert.assertEquals(pc.getCity(), r_pc.getCity());
//		Assert.assertEquals(pc.getAddress(), r_pc.getAddress());
//		Assert.assertEquals(pc.getCustType(), r_pc.getCustType());
//		Assert.assertEquals(pc.getIsCancel(), r_pc.getIsCancel());
//		
//		Assert.assertNull( r_pc.getDeptName());
//	}
//	
//	/**
//	 * 
//	 * <p>
//	 * Description:清除初始化的数据<br />
//	 * </p>
//	 * @author 王明明
//	 * @version 0.1 2012-12-18
//	 * @throws Exception
//	 * void
//	 */
//	private void clearData() throws Exception{
//		List<String> sqlList = new ArrayList<String>();
//		sqlList.add("delete t_cust_potentialscatter where FID=1");
//		SpringTestHelper.executeBatch(sqlList);
//	}
//
//}
