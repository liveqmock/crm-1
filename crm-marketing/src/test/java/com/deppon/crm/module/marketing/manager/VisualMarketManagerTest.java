package com.deppon.crm.module.marketing.manager;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.CustomerLocation;
import com.deppon.crm.module.marketing.server.manager.IVisualMarketManager;
import com.deppon.crm.module.marketing.server.manager.impl.VisualMarketManagerImpl;
import com.deppon.crm.module.marketing.shared.domain.visualMarket.CustMonthlyArriveIncome;
import com.deppon.crm.module.marketing.shared.domain.visualMarket.CustomerAddressInfo;
import com.deppon.crm.module.marketing.shared.domain.visualMarket.QueryCondition;
import com.deppon.crm.module.marketing.shared.domain.visualMarket.VisualMarketConstance;
import com.deppon.crm.module.marketing.utils.SpringTestHelper;



public class VisualMarketManagerTest extends TestCase{
    private JdbcTemplate jdbc;
    private IVisualMarketManager visualMarketManagerImpl
        =SpringTestHelper.get().getBean(VisualMarketManagerImpl.class);
    @Override
    public void setUp(){
        visualMarketManagerImpl=SpringTestHelper.get().getBean(VisualMarketManagerImpl.class);
        /*jdbc = (JdbcTemplate) SpringTestHelper.get().getBean(JdbcTemplate.class);
        //tearDown();
        jdbc.execute("insert into T_CUST_LABEL(" +
                "FID,FCREATETIME,FCREATEUSERID," +
                "FLASTUPDATETIME,FLASTMODIFYUSERID," +
                "FLABELNAME,FDEPTID)" +
                "VALUES(" +
                "1，sysdate,86301,sysdate,86301,'测试1',11333)");
        jdbc.execute("insert into T_CUST_LABEL(" +
                "FID,FCREATETIME,FCREATEUSERID," +
                "FLASTUPDATETIME,FLASTMODIFYUSERID," +
                "FLABELNAME,FDEPTID)" +
                "VALUES(" +
                "2，sysdate,86301,sysdate,86301,'测试2',11333)");
        jdbc.execute("INSERT INTO T_CUST_CUSTBASEDATA(FID,FCREATETIME,FCUSTNAME,FCUSTNUMBER,FDEGREE,FDEPTID,FTRADEID,FCONTACTID," +
                "FCUSTSTATUS,FCUSTTYPE,FISREDEEMPOINTS) VALUES(" +
                "-1,sysdate,'测试客户','S20130842','NORMAL',-1,'OTHER',-1,'0','ENTERPRISE',0)");        
        jdbc.execute("INSERT INTO T_CUST_CUSTLINKMAN(FID,FCREATETIME,FNAME,FISMAINLINKMAN,FNUMBER,FMOBILETEL,FOFFERTEL,FLINKMANTYPE)" +
                "VALUES(-1,sysdate,'测试联系人',1,20130424,'15324658452','13925851607','0,1,0,0,0')");
//      jdbc.execute("INSERT INTO T_CRM_CUSTVISUALMARKET(FID,FCUSTID,FRETURNSTATUS,FINCOMERESTORATE,FEXCEEDTIME,FMONTHLYINCOME,FMONTHLYGOODS," +
//              "FDEALTIME,FRETURNTIME,FSENDGOODSCYCLE,FDEPTID,FCREATETIME)VALUES(" +
//              "-1,-1,'1',80,7,8000,500,sysdate,sysdate,30,-1,sysdate");
        jdbc.execute("INSERT INTO T_CUST_PREFERENCEADDRESS(FID,FADDRESS,FLINKMANID,FADDRESSTYPE,FSTATUS,FISMAINADDRESS)VALUES(" +
                "-1,'测试偏好地址',-1,1,0,1)");
        jdbc.execute("INSERT INTO T_CRM_CUSTLOCATION(FID,FLAT,FLNG,FCUSTID,FCUSTTYPE,FCONTACTID)VALUES(" +
                "-1,29.994575,120.673718,-1,'MEMBER',-1)");
        jdbc.execute("INSERT INTO T_CRM_LADINGSTATION(FID,FDEPTNAME,FDEPTADDR,FCREATETIME,FCREATEUSERID,FLASTUPDATETIME,FLASTMODIFYUSERID," +
                "FORGID,FIFBUSSINESSDEPT,fisopen)VALUES(" +
                "-1,'测试网点部门','测试网点部门地址',sysdate,105873,sysdate,105873,-1,1,1)");
        jdbc.execute("INSERT INTO T_CUST_LABEL (FID,FLABELNAME,FDEPTID,fcreateuserid,fcreatetime)VALUES(" +
                "-1,'测试部门标签',-1,105873,sysdate)");*/
    }
/*  @After
    @Override
    public void tearDown(){
        jdbc.execute("delete from T_CUST_LABEL where fid = 1");
        jdbc.execute("delete from T_CUST_LABEL where fid = 2");
        jdbc.execute("delete from T_CUST_CUSTLINKMAN where fid = -1");
//      jdbc.execute("delete from T_CRM_CUSTVISUALMARKET where fcustid = -1");
        jdbc.execute("delete from T_CUST_CUSTBASEDATA where fid = -1");
        jdbc.execute("delete from T_CUST_PREFERENCEADDRESS where fid = -1");
        jdbc.execute("delete from T_CRM_CUSTLOCATION where fid = -1");
        jdbc.execute("delete from T_CRM_LADINGSTATION where fid = -1");
        jdbc.execute("delete from T_CUST_LABEL WHERE FID = -1");
        
    }   
    *//**
     * 
     * @Title: testSearchCustLabelByDeptId
     *  <p>
     * @Description: 根据登陆用户USER的部门id查询客户标签(测试方法)
     * </p>
     * @author 唐亮
     * @version 0.1 2013-4-19
     * @return void
     * @throws
     *//*
    @Test
    public void testSearchCustLabelByDeptId(){
        User user = new User();
        //模拟部门ID为空的情况
        try {
            visualMarketManager.searchCustLabelByDeptId(user, null);
            Assert.fail("部门ID为空，未按套路抛出异常,代码有问题");
        } catch (VisualMarketException e) {
            Assert.assertEquals(e.getErrorCode(),VisualMarketExceptionType.deptIdMustNotNull.getErrCode());
        }
        //模拟部门ID为营业部的情况
        Map<String, Object> map =  visualMarketManager.searchCustLabelByDeptId(user, "-1");
        Assert.assertNotNull(map);
        Assert.assertEquals("1", map.get("isBusinessDept"));
    }
    *//**
     * 
     * @Title: testSearchCustMarketInfoList
     *  <p>
     * @Description:查询客户信息列表测试方法
     * </p>
     * @author 唐亮
     * @version 0.1 2013-4-24
     * @return void
     * @throws
     *//*
    @Test
    public void testSearchCustMarketInfoList(){
        //模拟查询实体为空的情况
        try {
            visualMarketManager.searchCustMarketInfoList(null, null, 0, 0);
            Assert.fail("TL:查询实体为空，但未抛异常，请检查问题原因");
        } catch (VisualMarketException e) {
            Assert.assertEquals(e.getErrorCode(), VisualMarketExceptionType.conditionMustNotNull.getErrCode());
        }
        QueryCondition condition = new QueryCondition();
        //模拟部门ID为空的情况
        try {
            visualMarketManager.searchCustMarketInfoList(condition, null, 0, 0);
            Assert.fail("TL:部门ID为空，但未抛异常，请检查问题原因");
        } catch (VisualMarketException e) {
            Assert.assertEquals(e.getErrorCode(),VisualMarketExceptionType.deptIdMustNotNull.getErrCode());
        }
        //模拟客户类型和编码都为空的情况
        try {
            condition.setDeptId("-1");
            visualMarketManager.searchCustMarketInfoList(condition, null, 0, 0);
            Assert.fail("TL:客户类型和客户编码同时为空，但未抛异常，请检查问题原因");
        } catch (VisualMarketException e) {
            Assert.assertEquals(e.getErrorCode(), VisualMarketExceptionType.custTypeMustNotNull.getErrCode());
        }
        //模拟地图标记为空的情况
        try {
            condition.setPsCustType("MEMBER");
            visualMarketManager.searchCustMarketInfoList(condition, null, 1, 2);
        } catch (VisualMarketException e) {
            Assert.assertEquals(e.getErrorCode(), VisualMarketExceptionType.markStatusMustNotNull.getErrCode());
        }
        //模拟客户类型为固定客户的情况
        condition.setMapMakerStatus("ALL");
        
        List<CustomerMarketInfo> list = visualMarketManager.searchCustMarketInfoList(condition, null, 0, 20);
        Assert.assertNotNull(list);
        //模拟客户类型为固定客户，通过客户名称(实际是客户编码)查询的情况
        condition.setCustNumber("S20130842");
        list = visualMarketManager.searchCustMarketInfoList(condition, null, 0, 20);
        Assert.assertTrue(list != null && list.size()==1 && list.get(0).getCustNumber().equals("S20130842"));
        //
    }
    *//**
     * 
     * @Title: testCountCustMarketInfoList
     *  <p>
     * @Description: TODO(这里用一句话描述这个方法的作用)<br />
     * </p>
     * @author 唐亮
     * @version 0.1 2013-4-25
     * @return void
     * @throws
     *//*
    @Test
    public void testCountCustMarketInfoList(){
        
    }*/
    /**
     * 
     * @Title: testCountCustMarketInfoList
     *  <p>
     * @Description: TODO(这里用一句话描述这个方法的作用)<br />
     * </p>
     * @author ZHANGZW
     * @version 0.1 2013-5-3
     * @return void
     * @throws
     */
//    @Test
//    public void testvm(){
//        //定义参数
//        String custId="23587";
//        //得到用户近半年收入信息（不包括本月）
//        List<CustMonthlyArriveIncome> l=  visualMarketManagerImpl.searchHalfYearIncomeList(custId);
//        System.out.println(l);
//        //定义参数
//        List<String> list=new ArrayList<String>();
//        //添加客户Id
//        list.add("23587");
//        list.add("42597");
//        list.add("62230");
//        User user=new User();
//        //得到可制订计划的客户信息集合映射
//        Map map=visualMarketManagerImpl.searchCustsToCreatePlan(list, "mat", user);
//        System.out.println(map.get("ids"));
//        System.out.println(map.get("invalidCount"));
//    }
    
    /**
     * <p>
     * Description:测试查询已在地图上标记的客户<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-5-9
     * void
     */
    @Test
    public void testSearchMarkCustInfoList(){
        QueryCondition queryCondition = new QueryCondition();
        //客户等级
        queryCondition.setCustDegree(new String[]{"GOLD","NORMAL"});
        //部门id
        queryCondition.setDeptId("11103");
        visualMarketManagerImpl.searchMarkCustInfoList(queryCondition, new User());
    }
    
    /**
     * <p>
     * Description:查询客户信息列表<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-5-9
     * void
     */
    @Test
    public void testSearchCustMarketInfoList(){
        QueryCondition queryCondition = new QueryCondition();
        //客户等级
        queryCondition.setCustDegree(new String[]{"GOLD","NORMAL"});
        //部门id
        queryCondition.setDeptId("11103");
        //设置查询标记客户
        queryCondition.setMapMakerStatus(VisualMarketConstance.MAP_MAKED);
        visualMarketManagerImpl.searchCustMarketInfoList(queryCondition,new User(),0,10);
    }
    
    /**
     * 
     * <p>
     * Description:通过客户id查询客户信息<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-5-9
     * void
     */
//    @Test
//    public void testSearchCustById(){
//        String custId = "17003";
//        String deptId = "11103";
//        String custType = Constant.CRM_POTENTIAL;
//        visualMarketManagerImpl.searchCustById(custId, custType,deptId);
//    }
    
    /**
     * 
     * <p>
     * Description:统计客户信息总条数<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-5-9
     * void
     */
    @Test
    public void testCountCustMarketInfoList(){
        QueryCondition queryCondition = new QueryCondition();
        //客户等级
        queryCondition.setCustDegree(new String[]{"GOLD","NORMAL"});
        //部门id
        queryCondition.setDeptId("11103");
      //设置查询标记客户
        queryCondition.setMapMakerStatus(VisualMarketConstance.MAP_MAKED);
        visualMarketManagerImpl.countCustMarketInfoList(queryCondition);
    }
    public void testMarkLoaction(){
    	List<CustomerLocation> customerLocationList=new ArrayList<CustomerLocation>();
    	User user=new User();
    	CustomerLocation loca1=new CustomerLocation();
    	loca1.setCustomerId("46451");
    	loca1.setCustomerType("PC_CUSTOMER");
    	loca1.setLat("111");
    	loca1.setLng("45");
    	customerLocationList.add(loca1);
    	CustomerLocation loca2=new CustomerLocation();
    	loca2.setCustomerId("44569");
    	loca2.setCustomerType("SC_CUSTOMER");
    	loca2.setLat("11.4545");
    	loca2.setLng("45.1212");
    	customerLocationList.add(loca2);
    	CustomerLocation loca3=new CustomerLocation();
    	loca3.setCustomerId("4554");
    	loca3.setCustomerType("PERSONAL");
    	loca3.setLat("12.4545");
    	loca3.setLng("46.1212");
    	customerLocationList.add(loca3);
//    	visualMarketManagerImpl.markCustomerLocation(customerLocationList, user);
    }
    @Test
    public void testSearchCustomerAddressByCustId(){
    	CustomerAddressInfo addressInfo = new CustomerAddressInfo();
    	addressInfo = visualMarketManagerImpl.searchCustomerAddressByCustId("12524","MEMBER");
//    	System.out.println(addressInfo.getAddress()+";"+addressInfo.getCityName());
    }
}
