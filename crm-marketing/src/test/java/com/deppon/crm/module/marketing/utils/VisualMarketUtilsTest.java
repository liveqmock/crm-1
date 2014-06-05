package com.deppon.crm.module.marketing.utils;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

import com.deppon.crm.module.common.shared.domain.BussinessDept;
import com.deppon.crm.module.marketing.server.utils.VisualMarketUtils;
import com.deppon.crm.module.marketing.shared.domain.visualMarket.QueryCondition;
import com.deppon.crm.module.marketing.shared.domain.visualMarket.VisualMarketConstance;


/**
 * 
 * <p>
 * Description:测试工具类测试<br />
 * </p>
 * @title VisualMarketUtilsTest.java
 * @package com.deppon.crm.module.marketing.utils 
 * @author 石应华
 * @version 0.1 2013-5-24
 */
public class VisualMarketUtilsTest extends TestCase{
    
    /**
     * <p>
     * Description:测试回访状态是否查询当天新增客户
     * </p>
     * @author 石应华
     * @param strs 回访状态集合
     * @return boolean 是否查询当天新增客户   
     * @version
     * @date 2013-4-27 上午8:41:55
     */
    public void testIsReturnVisitStatusSearch(){
        VisualMarketUtils.isReturnVisitStatusSearch(new String[]{"-1"});
    }
    
    /**
     * 
     * <p>
     * Description:测试当月收入是否查询当天新增客户
     * </p>
     * @author 石应华
     * @param list 当月收入集合
     * @return boolean 是否查询当天新增客户
     * @version
     * @date 2013-4-24 下午5:20:12
     */
    public void testIsMonthlyIncomeSearch(){
        VisualMarketUtils.isMonthlyIncomeSearch(new String[]{"-1"});;
    }
    
    /**
     * 
     * <p>
     * Description:测试发货次数是否查询当天新增客户
     * </p>
     * @author 石应华
     * @param list 发货次数集合
     * @return boolean 是否查询当天新增客户
     * @version
     * @date 2013-4-24 下午5:20:23
     */
    public void testIsSendGoodsNumberSearch(){
        VisualMarketUtils.isSendGoodsNumberSearch(new String[]{"-1"});
    }
    /**
     * 
     * <p>
     * Description:测试收入进度是否查询当天新增客户
     * </p>
     * @author 石应华
     * @param list 收入进度集合
     * @return boolean 是否查询当天新增客户  
     * @version
     * @date 2013-4-24 下午5:20:27
     */
    public void testIsIncomeRestoreRateSearch(){
        VisualMarketUtils.isIncomeRestoreRateSearch(new String[]{"-1"});
    }
    /**
     * 
     * <p>
     * Description:测试超期时长是否查询当天新增客户
     * </p>
     * @author 石应华
     * @param list 超期时长集合
     * @return boolean 是否查询当天新增客户  
     * @version
     * @date 2013-4-24 下午5:20:30
     */
    public void testIsExceedTimeSearch(){
        Assert.assertTrue(VisualMarketUtils.isExceedTimeSearch(new String[]{"-1"}));
    }
    
    /**
     * <p>
     * Description:测试 字符串数组中是否包含指定字符串
     * </p>
     * @author 石应华
     * @param arrays 字符串数组
     * @param str 指定字符串
     * @return boolean 是否包含指定字符串   
     * @version
     * @date 2013-4-26 下午2:22:46
     */
    @Test
    public void testArraysContains(){
        Assert.assertTrue(VisualMarketUtils.arraysContains(new String[]{"a","b"},new String[]{"a"}));
        Assert.assertTrue(VisualMarketUtils.arraysContains(new String[]{"a","b"},"b"));
    }

    /**
     * 
     * <p>
     * Description:测试 集合是否为空
     * </p>
     * @author 石应华
     * @param list 判定集合
     * @return boolean 集合是否为空  
     * @version
     * @date 2013-4-24 下午6:10:32
     */
    public void testIsListNull(){
        Assert.assertTrue(VisualMarketUtils.isListNull(new ArrayList()));
    }
    
    /**
     * 
     * <p>
     * Description:测试 集合是否为空
     * </p>
     * @author 石应华
     * @param list 判定集合
     * @return boolean 集合是否为空  
     * @version
     * @date 2013-4-24 下午6:10:32
     */
    public void testIsArryNull(){
        Assert.assertTrue(VisualMarketUtils.isArryNull(new String[]{}));
    }
    
    /**
     * <p>
     * Description:测试数组转集合
     * </p>
     * @author 石应华
     * @param condition 查询条件
     * @return void    
     * @version
     * @date 2013-5-3 上午8:13:29
     */
    public void testArr2List(){
        QueryCondition condition = new QueryCondition();
        VisualMarketUtils.arr2List(condition);
    }
    
    /**
     * <p>
     * Description:测试处理查询不限的情况<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-5-6
     * @param condition 查询条件
     * void
     */
    public void testSearchAllOperation(){
        QueryCondition condition = new QueryCondition();
        condition.setMapMakerStatus(VisualMarketConstance.MARK_STATUS);
        Assert.assertTrue(VisualMarketUtils.isSearchTodayScatter(condition));
        condition.setReturnVisitStatus(new String[]{VisualMarketConstance.SEARCH_ALL});
        Assert.assertTrue(VisualMarketUtils.isSearchTodayScatter(condition));
        condition.setCustTrade(new String[]{VisualMarketConstance.SEARCH_ALL});
        Assert.assertTrue(VisualMarketUtils.isSearchTodayScatter(condition));
        condition.setCustLabel(new String[]{VisualMarketConstance.SEARCH_ALL});
        Assert.assertTrue(VisualMarketUtils.isSearchTodayScatter(condition));
        condition.setGoodsPotential(new String[]{VisualMarketConstance.SEARCH_ALL});
        Assert.assertTrue(VisualMarketUtils.isSearchTodayScatter(condition));
        condition.setCustResource(new String[]{VisualMarketConstance.SEARCH_ALL});
        Assert.assertTrue(VisualMarketUtils.isSearchTodayScatter(condition));
        condition.setExceedTime(new String[]{VisualMarketConstance.SEARCH_ALL});
        Assert.assertTrue(VisualMarketUtils.isSearchTodayScatter(condition));
        condition.setSendGoodsNumber(new String[]{VisualMarketConstance.SEARCH_ALL});
        Assert.assertTrue(VisualMarketUtils.isSearchTodayScatter(condition));
        condition.setMonthlyIncome(new String[]{VisualMarketConstance.SEARCH_ALL});
        Assert.assertTrue(VisualMarketUtils.isSearchTodayScatter(condition));
        condition.setIncomeRestoreRate(new String[]{VisualMarketConstance.SEARCH_ALL});
        Assert.assertTrue(VisualMarketUtils.isSearchTodayScatter(condition));
    }
    
    /**
     * <p>
     * Description:测试查询潜客时,清除散客特有查询条件<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-5-6
     * @param condition 查询条件
     * void
     */
    public void testClearScatterQueryCondition(){
        QueryCondition condition = new QueryCondition();
        VisualMarketUtils.clearScatterQueryCondition(condition);
    }
    /**
     * <p>
     * Description:测试查询散客时,清除潜客特有查询条件<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-5-6
     * @param condition 查询条件
     * void
     */
    public void testClearPotentialQueryCondition(){
        QueryCondition condition = new QueryCondition();
        VisualMarketUtils.clearPotentialQueryCondition(condition);
    }
    /**
     * <p>
     * Description:测试是否查询当天新增散客<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-5-6
     * @param condition 查询条件
     * @return
     * boolean true:查询当天新增客户；false:不查询当天新增客户
     */
    public void testIsSearchTodayScatter() {
        QueryCondition condition = new QueryCondition();
        condition.setMonthlyIncomeList(null);
        Assert.assertTrue(VisualMarketUtils.isSearchTodayScatter(condition));
        condition.setSendGoodsNumberList(null);
        Assert.assertTrue(VisualMarketUtils.isSearchTodayScatter(condition));
    }
    
    /**
     * <p>
     * Description:测试客户回访查询是否查询当天的<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-5-6
     * @param condition 查询条件
     * @return
     * boolean true:查询当天新增客户；false:不查询当天新增客户
     */
    public void testIsSearchReturnVisit() {
        QueryCondition condition = new QueryCondition();
        condition.setReturnVisitStatus(new String[]{VisualMarketConstance.RETURN_VISIT});
        Assert.assertTrue(VisualMarketUtils.isSearchTodayMember(condition));
        condition.setReturnVisitStatus(new String[]{"2"});
        Assert.assertFalse(VisualMarketUtils.isSearchTodayMember(condition));
        condition.setReturnVisitStatus(new String[]{"1"});
        Assert.assertFalse(VisualMarketUtils.isSearchTodayMember(condition));
    }
    
    /**
     * 
     * <p>
     * Description:测试判断是否查询当天新增固定客户<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-5-6
     * @param condition 查询条件
     * @return
     * boolean true:查询当天新增客户；false:不查询当天新增客户
     */
    public void testIsSearchTodayMember() {
        QueryCondition condition = new QueryCondition();
        condition.setReturnVisitStatus(new String[]{VisualMarketConstance.RETURN_VISIT});
        Assert.assertTrue(VisualMarketUtils.isSearchTodayMember(condition));
    }
    
    /**
     * <p>
     * Description:测试判断是否为营业部<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-5-6
     * @param deptId
     * @return
     * boolean
     */
    public void testIsBussinessDept(){
        BussinessDept dept = new BussinessDept();
        dept.setIfBussinessDept(true);
        Assert.assertTrue(VisualMarketUtils.isBussinessDept(dept));
    }
    /**
     * <p>
     * Description:测试分页参数设置<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-5-9
     * @param condition 查询条件
     * @param start 记录开始条数
     * @param limit 查询记录总计条数
     * void
     */
    public void testPagingParamSet(){
        QueryCondition condition = new QueryCondition();
        VisualMarketUtils.pagingParamSet(condition,3,28);
        Assert.assertEquals(condition.getEndRecord(),31);
    }
}
