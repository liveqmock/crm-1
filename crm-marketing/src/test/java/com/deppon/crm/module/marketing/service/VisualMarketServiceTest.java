/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title PlanServiceTest.java
 * @package com.deppon.crm.module.marketing.service 
 * @author Administrator
 * @version 0.1 2012-3-29
 */
package com.deppon.crm.module.marketing.service;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.marketing.server.service.IVisualMarketService;
import com.deppon.crm.module.marketing.server.service.impl.VisualMarketServiceImpl;
import com.deppon.crm.module.marketing.shared.domain.visualMarket.CustMonthlyArriveIncome;
import com.deppon.crm.module.marketing.shared.domain.visualMarket.QueryCondition;
import com.deppon.crm.module.marketing.shared.domain.visualMarket.VisualMarketConstance;
import com.deppon.crm.module.marketing.utils.SpringTestHelper;
public class VisualMarketServiceTest {
    private IVisualMarketService vmservice;
    private QueryCondition queryCondition = new QueryCondition();
    @Before
    public void setUp() throws Exception {
        vmservice = (VisualMarketServiceImpl) SpringTestHelper.get().getBean(
                VisualMarketServiceImpl.class);
        //部门id
        queryCondition.setDeptId("11103");
        //设置查询标记客户
        queryCondition.setMapMakerStatus(VisualMarketConstance.MAP_MAKED);
    }
//    @Test
//    public void testHelfYearIncome(){
//        String custId="10699";
//        //测试searchHalfYearIncomeList（custid）
//        List<CustMonthlyArriveIncome> list=vmservice.searchHalfYearIncomeList(custId);
//        String status=vmservice.searchVisitStatusByCustId(custId, "dev");
//    }
    @Test
    public void searchVisitStatusByCustId(){
        String custId="10699";
        String status="";
        //定义参数
        String[] ids=new String[]{"23587","42597","62230"};
        //测试searchVisitStatusByCustId（String[] String）方法
        List list=vmservice.searchVisitStatusByCustId(ids, "mat");
        status=vmservice.searchVisitStatusByCustId(custId, "dev");
    }

//  /**
//   * <p>
//   * Description:这里写描述<br />
//   * </p>
//   * @author 苏玉军
//   * @version 0.1 2012-3-29
//   * @throws java.lang.Exception
//   * void
//   */
//  @After
//  public void tearDown() throws Exception {
//  }
//
//  /**
//   * Test method for {@link com.deppon.crm.module.marketing.server.service.impl.PlanService#searchDepartmentPlanList(java.lang.String, java.lang.String)}.
//   */
//  @Test
//  public void testSearchDepartmentPlanList() {
//      String execDeptId="1313888";
//      String planType="dev";
//      List<Plan> list =  planService.searchDepartmentPlanList(execDeptId, null, planType);
//      Assert.assertNotNull(list);
//  }
//
//  /**
//   * Test method for {@link com.deppon.crm.module.marketing.server.service.impl.PlanService#createPlan(com.deppon.crm.module.marketing.shared.domain.Plan)}.
//   */
//  @Test
//  public void testCreatePlan() {
//      Plan plan = DataUtilTest.getPlanData();
//      String s =planService.createPlan(plan);
//      Assert.assertNotNull(s);
//      
//  }
//
//  /**
//   * Test method for {@link com.deppon.crm.module.marketing.server.service.impl.PlanService#searchScheduleIdByPlanId(java.lang.String)}.
//   */
//  @Test
//  public void testSearchScheduleIdByPlanId() {
//      List<String> list = planService.searchScheduleIdByPlanId("408");
//      for(String str:list){
//          System.out.println(str);
//      }
//      Assert.assertEquals(0, list.size());
//  }
//
//  /**
//   * Test method for {@link com.deppon.crm.module.marketing.server.service.impl.PlanService#searchPlanByTheme(java.lang.String, java.util.Set)}.
//   */
//  @Test
//  public void testSearchPlanByTheme() {
//      //fail("Not yet implemented");
//  }
//
//  /**
//   * Test method for {@link com.deppon.crm.module.marketing.server.service.impl.PlanService#updatePlan(com.deppon.crm.module.marketing.shared.domain.Plan)}.
//   */
//  @Test
//  public void testUpdatePlan() {
//
//      try {
//          Plan plan = planService.getPlanById("400000712");
//          plan.setTopic("测试更新Plan");
//          boolean b=  planService.updatePlan(plan);
//          Assert.assertTrue(b);
//          plan= planService.getPlanById("400000712");
//          Assert.assertEquals("测试更新Plan", plan.getTopic());
//      } catch (GeneralException e) {
//          System.out.println(e.getMessage());
//      }
//  }
//
//  @Test
//  public void testGetMemberList(){
//      List<?> l1 = planService.getMemberList("1061", MarketingConstance.DEVELOP_TYPE);
//      Assert.assertNotNull(l1);
//      
//      List<?> l2 = planService.getMemberList("1092", MarketingConstance.MAINTAIN_TYPE);
//      Assert.assertNotNull(l2);
//      
//  }
//  
//  @Test
//  public void testSearchPlan(){
//      PlanDevelopCondition condition = new PlanDevelopCondition();
//      try {
//          Date s1 = DateUtils.setMonths(DateUtils.setDays(new Date(), 5), 3);
//          Date s2 = DateUtils.setMonths(DateUtils.setDays(new Date(), 5), 5);
//          condition.setPlanStartDate(s1);
//          condition.setPlanOverDate(s2);
//          condition.setType(MarketingConstance.DEVELOP_TYPE);
//          condition.setDevelopMode("1");
//          planService.searchPlan(condition, 0, 10);
//      } catch (GeneralException e) {
//          // TODO: handle exception
//      }
//      try {
//          Date s1 = DateUtils.setMonths(DateUtils.setDays(new Date(), 5), 2);
//          Date s2 = DateUtils.setMonths(DateUtils.setDays(new Date(), 5), 2);
//          condition.setPlanStartDate(s1);
//          condition.setPlanOverDate(s2);
//          condition.setPlanName("1");
//          condition.setType(MarketingConstance.DEVELOP_TYPE);
//          condition.setDevelopMode("1");
//          planService.searchPlan(condition, 0, 10);
//      } catch (GeneralException e) {
//          // TODO: handle exception
//      }
//  }
    
    /**
     * <p>
     * Description:查询潜散客户信息列表<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-5-9
     * void
     */
//    @Test
//    public void testSearchPCOrSCMarketInfoList(){
//        vmservice.searchPCOrSCMarketInfoList(queryCondition,0,10);
//    }
    
    /**
     * <p>
     * Description:查询固定客户信息列表<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-5-9
     * void
     */
    @Test
    public void testSearchMemberMarketInfoList(){
        vmservice.searchMemberMarketInfoList(queryCondition,0,10);
    }
    
    /**
     * <p>
     * Description:统计客户信息总条目数<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-5-9
     * void
     */
    @Test
    public void testCountCustMarketInfoList(){
        vmservice.countCustMarketInfoList(queryCondition);
    }
    
    /**
     * 
     * <p>
     * Description:统计潜散客信息总条目数<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-5-23
     * void
     */
//    @Test
//    public void testCountPSCustMarketInfoList(){
//        vmservice.countPSCustMarketInfoList(queryCondition);
//    }
//    
    /**
     * <p>
     * Description:通过客户id查询固定客户信息<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-5-9
     * void
     */
    @Test
    public void testSearchMemberById(){
        queryCondition.setCustId("17003");
        vmservice.searchMemberById(queryCondition);
    }
    
    /**
     * <p>
     * Description:通过客户id查询固定客户信息<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-5-9
     * void
     */
    @Test
    public void testSearchPSById(){
        queryCondition.setCustId("409876772");
//        vmservice.searchPSById(queryCondition);
    }
    
    /**
     * 
     * <p>
     * Description:根据部门ID查询该部门实体<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-5-23
     * void
     */
    @Test
    public void testGetBusDeptByDeptId(){
        vmservice.getBusDeptByDeptId("11103");
    }
    @Test
    public void testSearchMemberAddressByCustId(){
        vmservice.searchMemberAddressByCustId("874");
    }
}
