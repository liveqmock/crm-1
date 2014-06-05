/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title MapDaoTest.java
 * @package com.deppon.crm.module.marketing.dao 
 * @author zhujunyong
 * @version 0.1 Mar 29, 2012
 */
package com.deppon.crm.module.marketing.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;

import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.marketing.server.dao.IVisualMarketDao;
import com.deppon.crm.module.marketing.server.dao.impl.VisualMarketDaoImpl;
import com.deppon.crm.module.marketing.server.utils.VisualMarketQueryMappers;
import com.deppon.crm.module.marketing.server.utils.VisualMarketUtils;
import com.deppon.crm.module.marketing.server.utils.VisualMarketValidator;
import com.deppon.crm.module.marketing.shared.domain.visualMarket.CustMonthlyArriveIncome;
import com.deppon.crm.module.marketing.shared.domain.visualMarket.CustomerMarketInfo;
import com.deppon.crm.module.marketing.shared.domain.visualMarket.QueryCondition;
import com.deppon.crm.module.marketing.utils.SpringTestHelper;

/**   
 * <p>
 * Description:物化视图刷新DAO<br />
 * </p>
 * @title MapDaoTest.java
 * @package com.deppon.crm.module.marketing.dao 
 * @author zhujunyong
 * @version 0.1 Mar 29, 2012
 */
public class visualMarketDaoTest {
    IVisualMarketDao CustomerMarketInfoDaoImpl = null;
    @Before
    public void setUp() throws Exception {
        CustomerMarketInfoDaoImpl = (VisualMarketDaoImpl)SpringTestHelper.get().getBean(VisualMarketDaoImpl.class);
    }
 
   @Test
    public void testSearchMemberMarketInfo(){
        QueryCondition queryCondition = new QueryCondition();
        queryCondition.setCustId("17003");
        queryCondition.setDeptId("10518");
        queryCondition.setSearchTodayCust(true);
        VisualMarketUtils.pagingParamSet(queryCondition,0,1);
        CustomerMarketInfo customerMarketInfo  = CustomerMarketInfoDaoImpl.searchMemberMarketInfo(queryCondition);
    }
    
    @Test
    public void testSearchPCOrSCMarketInfo(){
        QueryCondition queryCondition = new QueryCondition();
        queryCondition.setCustId("409876772");
        queryCondition.setDeptId("170219");
        //潜散客户类型
        //queryCondition.setPsCustType(Constant.CRM_POTENTIAL);
        queryCondition.setPsCustType(Constant.CRM_SCATTER);
        queryCondition.setSearchTodayCust(true);
        VisualMarketUtils.pagingParamSet(queryCondition,0,1);
       CustomerMarketInfo customerMarketInfo  = CustomerMarketInfoDaoImpl.searchPCOrSCMarketInfo(queryCondition);
    }

    @Test
    public void testSearchMemberMarketInfoList(){
        QueryCondition queryCondition = new QueryCondition();
        //客户编码
        //queryCondition.setCustNumber("000210");
        //客户等级
        List<String> custDegreeList = new ArrayList<String>();
        queryCondition.setCustDegree(new String[]{"GOLD","NORMAL"});
        //超期时常
        List<Map<String, String>> exceedTimeList = new ArrayList<Map<String, String>>();
        String[] strs1 = new String[]{"-0","6-10"};
        exceedTimeList.addAll(VisualMarketQueryMappers.getExceedTimeMap(strs1));
        queryCondition.setExceedTimeList(exceedTimeList);
        //收入恢复进度
        List<Map<String, String>> incomeRestoreRateList = new ArrayList<Map<String, String>>();
        String[] strs2 = new String[]{"0-20%","50%-80%","100%-120%"};
        incomeRestoreRateList.addAll(VisualMarketQueryMappers.getIncomeRestoreRateMap(strs2));
        queryCondition.setIncomeRestoreRateList(incomeRestoreRateList);
        //地图标记
        queryCondition.setMapMakerStatus("MAPMAKED");
        //排序
        //queryCondition.setSortType("tradeTime");
        //回访状态 1:已回访；2：已指派；3：未回访
        queryCondition.setReturnVisitStatus(new String[]{"1","2"});
        //所属行业(数字字符串集合)
        //List<String> custTradeList = new ArrayList<String>();
        //custTradeList.add("123");
        //queryCondition.setCustTradeList(custTradeList);
        //客户标签(数字字符串集合)
        //List<String> custLabelList = new ArrayList<String>();
        //custLabelList.add("123");
        //queryCondition.setCustLabelList(custLabelList);
        //部门id
        queryCondition.setDeptId("11103");
        fun(queryCondition);
        VisualMarketUtils.pagingParamSet(queryCondition, 0, 10);
        List list = CustomerMarketInfoDaoImpl.searchMemberMarketInfoList(queryCondition);
    }
    
    @Test
    public void testSearchPCOrSCMarketInfoList(){
        QueryCondition queryCondition = new QueryCondition();
        //queryCondition.setPsCustType("abc");
        //根据标杆编码查询
        //queryCondition.setCustNumber("123");
        //潜散客户类型
        queryCondition.setPsCustType(Constant.CRM_POTENTIAL);
        //queryCondition.setPsCustType(Constant.CRM_SCATTER);
        //地图标记
        queryCondition.setMapMakerStatus("MAPMAKED");
        //根据部门id查询
        queryCondition.setDeptId("11101");
        //发货票数
        List<Map<String, String>> sendGoodsNumberList = new ArrayList<Map<String, String>>();
        String[] strs1 = new String[]{"0-5","5-10","20-"};
        sendGoodsNumberList.addAll(VisualMarketQueryMappers.getSendGoodsNumberMap(strs1));
        queryCondition.setSendGoodsNumberList(sendGoodsNumberList);
        //当月收入
        List<Map<String, String>> monthlyIncomeList = new ArrayList<Map<String, String>>();
        String[] strs2 = new String[]{"0-200","400-600","600-"};
        monthlyIncomeList.addAll(VisualMarketQueryMappers.getMonthlyIncomeMap(strs2));
        queryCondition.setMonthlyIncomeList(monthlyIncomeList);
        //回访状态
//        List<String> returnVisitStatusList = new ArrayList<String>();
//        returnVisitStatusList.add("1");
//        returnVisitStatusList.add("2");
//        queryCondition.setReturnVisitStatusList(returnVisitStatusList);
        queryCondition.setReturnVisitStatus(new String[]{"1","2"});
        //货量潜力
        queryCondition.setGoodsPotential(new String[]{"<600"});
        //客户来源
        queryCondition.setCustResource(new String[]{"other"});
        //客户标签
        queryCondition.setCustLabel(new String[]{"-1"});
        //排序
        //queryCondition.setSortType("tradeTime");
        //部门id
        queryCondition.setDeptId("11103");
//        fun(queryCondition);
        VisualMarketUtils.pagingParamSet(queryCondition, 0, 10);
       List list = CustomerMarketInfoDaoImpl.searchPCOrSCMarketInfoList(queryCondition);
    }
    
    @Test
    public void testCountPSCustMarketInfoList(){
        QueryCondition queryCondition = new QueryCondition();
        //根据标杆编码查询
        //queryCondition.setCustNumber("123");
        //潜散客户类型
        queryCondition.setPsCustType(Constant.CRM_POTENTIAL);
        //queryCondition.setPsCustType(Constant.CRM_SCATTER);
        //根据部门id查询
        queryCondition.setDeptId("11103");
        //地图标记
        queryCondition.setMapMakerStatus("MAPMAKED");
        //是否查询今天
        queryCondition.setSearchTodayCust(true);
        //客户标签
        //queryCondition.setCustLabel(new String[]{"-1"});
        //发货票数
        List<Map<String, String>> sendGoodsNumberList = new ArrayList<Map<String, String>>();
        String[] strs1 = new String[]{"0-5","6-10","21-"};
        sendGoodsNumberList.addAll(VisualMarketQueryMappers.getSendGoodsNumberMap(strs1));
        //queryCondition.setSendGoodsNumberList(sendGoodsNumberList);
        //当月收入
        List<Map<String, String>> monthlyIncomeList = new ArrayList<Map<String, String>>();
        String[] strs2 = new String[]{"0-200","400-600","600-"};
        monthlyIncomeList.addAll(VisualMarketQueryMappers.getMonthlyIncomeMap(strs2));
        //queryCondition.setMonthlyIncomeList(monthlyIncomeList);
        //当月收入
//        String[] strs2 = new String[]{"0-200","400-600","600-"};
//        queryCondition.setMonthlyIncomeList(VisualMarketQueryMappers.getMonthlyIncomeMap(strs2));
        CustomerMarketInfoDaoImpl.countPSCustMarketInfoList(queryCondition);
    }
    
    @Test
    public void testCountMemberCustMarketInfoList(){
        QueryCondition queryCondition = new QueryCondition();
        //根据标杆编码查询
        //queryCondition.setCustNumber("123");
        //根据部门id查询
        queryCondition.setDeptId("11103");
        //地图标记
        queryCondition.setMapMakerStatus("MAPMAKED");
        //是否查询今天
        queryCondition.setSearchTodayCust(true);
        //当月收入
//        String[] strs2 = new String[]{"0-200","400-600","600-"};
//        queryCondition.setMonthlyIncomeList(VisualMarketQueryMappers.getMonthlyIncomeMap(strs2));
        CustomerMarketInfoDaoImpl.countMemberCustMarketInfoList(queryCondition);
    }
    
    public void fun(QueryCondition condition){
        //校验可视化视图查询查询条件
        VisualMarketValidator.checkSearchCustMarketInfo(condition);
        //处理查询不限的情况
        VisualMarketUtils.searchAllOperation(condition);
        //数组转list
        VisualMarketUtils.arr2List(condition);
        
        //如果是根据客户编码唯一查询
        if(!StringUtils.isEmpty(condition.getCustNumber())){
            //设置不查询当天新增客户
            condition.setSearchTodayCust(false);
        }
        
        //如果客户类型是潜散客
        if (Constant.CRM_POTENTIAL.equals(condition.getPsCustType())
                ||Constant.CRM_SCATTER.equals(condition.getPsCustType())) {
            //如果是潜客
            if(Constant.CRM_POTENTIAL.equals(condition.getPsCustType())){
                //清除散客的查询条件
                VisualMarketUtils.clearScatterQueryCondition(condition);
                //设置要查询当天新增客户
                condition.setSearchTodayCust(true);
            //如果是散客
            }else{
                //清除潜客的查询条件
                VisualMarketUtils.clearPotentialQueryCondition(condition);
                //设置要查询当天新增客户
                condition.setSearchTodayCust(VisualMarketUtils.isSearchTodayScatter(condition));
            }
        //如果客户类型是会员
        }else {
            //设置是否查询当天新增客户
            condition.setSearchTodayCust(VisualMarketUtils.isSearchTodayMember(condition));
        }
    }
    private boolean isListNull(List list){
        return list == null || list.size()==0;
    }
    @Test
    public void testSearchHalfYearIncomeList(){
    	String custid="42597";
   	List<CustMonthlyArriveIncome> list=CustomerMarketInfoDaoImpl.searchHalfYearIncomeList(custid);
    }
   
    
    @Test
    public void testSearchMemberAddressByCustId(){
        CustomerMarketInfoDaoImpl.searchMemberAddressByCustId("874");
    }
    @Test
    public void testsearchHalfYearIncomeList(){
        CustomerMarketInfoDaoImpl.searchHalfYearIncomeList("152172");
    }
    @Test
    public void TestSearchVisitStatusByCustId(){
    	Map<String,Object> para=new HashMap<String,Object>();
    	String[] ids=new String[]{"62230","42597","23587 "};
    	para.put("ids", ids);
    	para.put("custType", "dev");
    	List<String> list=CustomerMarketInfoDaoImpl.searchVisitStatusByCustId(para);
    }
}
