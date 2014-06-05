package com.deppon.crm.module.marketing.server.utils;

import java.util.Arrays;
import java.util.List;

import com.deppon.crm.module.common.shared.domain.BussinessDept;
import com.deppon.crm.module.marketing.shared.domain.visualMarket.QueryCondition;
import com.deppon.crm.module.marketing.shared.domain.visualMarket.VisualMarketConstance;


/**
 * 
 * @ClassName: VisualMarketUtils 
 * @Description: 可视化营销工具类
 * @author 133586-石应华
 * @date 2013-4-24 下午5:12:05 
 *
 */
public class VisualMarketUtils {
    
    /**
     * <p>
     * Description:回访状态是否查询当天新增客户
     * </p>
     * @author 石应华
     * @param strs 回访状态集合
     * @return boolean 是否查询当天新增客户   
     * @version
     * @date 2013-4-27 上午8:41:55
     */
    public static boolean isReturnVisitStatusSearch(String[] strs){
        return arraysContains(strs,VisualMarketConstance.RETURN_VISIT);
    }
    
    /**
     * 
     * <p>
     * Description:当月收入是否查询当天新增客户
     * </p>
     * @author 石应华
     * @param list 当月收入集合
     * @return boolean 是否查询当天新增客户
     * @version
     * @date 2013-4-24 下午5:20:12
     */
    public static boolean isMonthlyIncomeSearch(String[] strs){
        return arraysContains(strs,VisualMarketConstance.MONTHLY_INCOME);
    }
    
    /**
     * 
     * <p>
     * Description:发货次数是否查询当天新增客户
     * </p>
     * @author 石应华
     * @param list 发货次数集合
     * @return boolean 是否查询当天新增客户
     * @version
     * @date 2013-4-24 下午5:20:23
     */
    public static boolean isSendGoodsNumberSearch(String[] strs){
        return arraysContains(strs,VisualMarketConstance.SEND_GOODS_NUM);
    }
    /**
     * 
     * <p>
     * Description:收入进度是否查询当天新增客户
     * </p>
     * @author 石应华
     * @param list 收入进度集合
     * @return boolean 是否查询当天新增客户  
     * @version
     * @date 2013-4-24 下午5:20:27
     */
    public static boolean isIncomeRestoreRateSearch(String[] strs){
        return arraysContains(strs,VisualMarketConstance.INCOME_RESTORE_RATE);
    }
    /**
     * 
     * <p>
     * Description:超期时长是否查询当天新增客户
     * </p>
     * @author 石应华
     * @param list 超期时长集合
     * @return boolean 是否查询当天新增客户  
     * @version
     * @date 2013-4-24 下午5:20:30
     */
    public static boolean isExceedTimeSearch(String[] strs){
        return arraysContains(strs,VisualMarketConstance.EXCEED_TIME);
    }
    
    /**
     * <p>
     * Description: 字符串数组中是否包含指定字符串
     * </p>
     * @author 石应华
     * @param arrays 字符串数组
     * @param str 指定字符串
     * @return boolean 是否包含指定字符串   
     * @version
     * @date 2013-4-26 下午2:22:46
     */
    public static boolean arraysContains(String[] arrays,String[] strs){
        if(arrays == null || arrays.length == 0){
            return true;
        }
        List list = Arrays.asList(arrays);
        for (int i = 0; i < strs.length; i++) {
            if(list.contains(strs[i])){
                return true;
            }
        }
        return false;
    }
    /**
     * <p>
     * Description: 字符串数组中是否包含指定字符串
     * </p>
     * @author 石应华
     * @param arrays 字符串数组
     * @param str 指定字符串
     * @return boolean 是否包含指定字符串   
     * @version
     * @date 2013-4-28 下午4:41:52
     */
    public static boolean arraysContains(String[] arrays,String str){
        return arraysContains(arrays,new String[]{str});
    }
    /**
     * 
     * <p>
     * Description: 集合是否为空
     * </p>
     * @author 石应华
     * @param list 判定集合
     * @return boolean 集合是否为空  
     * @version
     * @date 2013-4-24 下午6:10:32
     */
    public static boolean isListNull(List list){
        return list == null || list.size()==0;
    }
    
    /**
     * 
     * <p>
     * Description: 集合是否为空
     * </p>
     * @author 石应华
     * @param list 判定集合
     * @return boolean 集合是否为空  
     * @version
     * @date 2013-4-24 下午6:10:32
     */
    public static boolean isArryNull(Object[] objs){
        return objs == null || objs.length ==0;
    }
    
    /**
     * <p>
     * Description:数组转集合
     * </p>
     * @author 石应华
     * @param condition 查询条件
     * @return void    
     * @version
     * @date 2013-5-3 上午8:13:29
     */
    public static void arr2List(QueryCondition condition){
        if (null != condition) {
            //处理收入恢复进度
            if (null != condition.getIncomeRestoreRate()) {
                condition.setIncomeRestoreRateList(VisualMarketQueryMappers.getIncomeRestoreRateMap(condition.getIncomeRestoreRate()));
            }
            //处理超期时常
            if (null != condition.getExceedTime()) {
                condition.setExceedTimeList(VisualMarketQueryMappers.getExceedTimeMap(condition
                        .getExceedTime()));
            }
            //处理月收入
            if (null != condition.getMonthlyIncome()) {
                condition.setMonthlyIncomeList(VisualMarketQueryMappers.getMonthlyIncomeMap(condition.getMonthlyIncome()));
            }
            //处理发货数量
            if (null != condition.getSendGoodsNumber()) {
                condition.setSendGoodsNumberList(
                VisualMarketQueryMappers.getSendGoodsNumberMap(condition.getSendGoodsNumber()));
            }
        }
    }
    
    /**
     * <p>
     * Description:处理查询不限的情况<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-5-6
     * @param condition 查询条件
     * void
     */
    public static void searchAllOperation(QueryCondition condition){
      //如果传入参数为ALL则设置apMakerStatus为null即不用这个查询条件
        if(VisualMarketConstance.MARK_STATUS.equals(condition.getMapMakerStatus())){
            condition.setMapMakerStatus(null);
        }
        //如果回访状态为-1则returnVisitStatus为null即不用这个查询条件
        if(VisualMarketUtils.arraysContains(condition.getReturnVisitStatus(),VisualMarketConstance.SEARCH_ALL)){
            condition.setReturnVisitStatus(null);
        }
        //如果所属行业为-1则CustTrade为null即不用这个查询条件
        if(VisualMarketUtils.arraysContains(condition.getCustTrade(),VisualMarketConstance.SEARCH_ALL)){
            condition.setCustTrade(null);
        }
        //如果客户标签为-1则CustLabel为null即不用这个查询条件
        if(VisualMarketUtils.arraysContains(condition.getCustLabel(), VisualMarketConstance.SEARCH_ALL)){
            condition.setCustLabel(null);
        }
        //如果货量潜力为-1则GoodsPotential为null即不用这个查询条件
        if(VisualMarketUtils.arraysContains(condition.getGoodsPotential(), VisualMarketConstance.SEARCH_ALL)){
            condition.setGoodsPotential(null);
        }
        //如果客户来源为-1则CustResource为null即不用这个查询条件
        if(VisualMarketUtils.arraysContains(condition.getCustResource(), VisualMarketConstance.SEARCH_ALL)){
            condition.setCustResource(null);
        }
        //超期时长
        if(VisualMarketUtils.arraysContains(condition.getExceedTime(),VisualMarketConstance.SEARCH_ALL)){
            condition.setExceedTime(null);
        }
        //发货票数
        if(VisualMarketUtils.arraysContains(condition.getSendGoodsNumber(),VisualMarketConstance.SEARCH_ALL)){
            condition.setSendGoodsNumber(null);
        }
        //当月收入
        if(VisualMarketUtils.arraysContains(condition.getMonthlyIncome(),VisualMarketConstance.SEARCH_ALL)){
            condition.setMonthlyIncome(null);
        }
        //收入恢复进度
        if(VisualMarketUtils.arraysContains(condition.getIncomeRestoreRate(),VisualMarketConstance.SEARCH_ALL)){
            condition.setIncomeRestoreRate(null);
        }
    }
    
    /**
     * <p>
     * Description:查询潜客时,清除散客特有查询条件<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-5-6
     * @param condition 查询条件
     * void
     */
    public static void clearScatterQueryCondition(QueryCondition condition){
        //清除月收入
        condition.setMonthlyIncomeList(null);
        //清除发货数量
        condition.setSendGoodsNumberList(null);
    }
    /**
     * <p>
     * Description:查询散客时,清除潜客特有查询条件<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-5-6
     * @param condition 查询条件
     * void
     */
    public static void clearPotentialQueryCondition(QueryCondition condition){
        //清除发货潜量
        condition.setGoodsPotential(null);
        //清除客户来源
        condition.setCustResource(null);
    }
    /**
     * <p>
     * Description:是否查询当天新增散客<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-5-6
     * @param condition 查询条件
     * @return
     * boolean true:查询当天新增客户；false:不查询当天新增客户
     */
    public static boolean isSearchTodayScatter(QueryCondition condition) {
        boolean isMonthlyIncome = false;
        boolean isSendGoodsNumber = false;
        //当月收入是否查询当天客户
        if(VisualMarketUtils.isListNull(condition.getMonthlyIncomeList()) && isSearchReturnVisit(condition)){
            isMonthlyIncome = true;
        }else{
            isMonthlyIncome = VisualMarketUtils.isMonthlyIncomeSearch(condition.getMonthlyIncome());
        }
        //发货次数是否查询当天客户
        if(isMonthlyIncome && VisualMarketUtils.isListNull(condition.getSendGoodsNumberList())){
            isSendGoodsNumber = true;
        }else{
            isSendGoodsNumber = VisualMarketUtils.isSendGoodsNumberSearch(condition.getSendGoodsNumber());
        }
        return isMonthlyIncome & isSendGoodsNumber;
        
    }
    
    /**
     * <p>
     * Description:客户回访查询是否查询当天的<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-5-6
     * @param condition 查询条件
     * @return
     * boolean true:查询当天新增客户；false:不查询当天新增客户
     */
    public static boolean isSearchReturnVisit(QueryCondition condition) {
        if(condition.getReturnVisitStatus() != null && condition.getReturnVisitStatus().length > 0){
            return isReturnVisitStatusSearch(condition.getReturnVisitStatus());
        }else{
            return true;
        }
    }
    
    /**
     * 
     * <p>
     * Description:判断是否查询当天新增固定客户<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-5-6
     * @param condition 查询条件
     * @return
     * boolean true:查询当天新增客户；false:不查询当天新增客户
     */
    public static boolean isSearchTodayMember(QueryCondition condition) {
        //如果客户回访不查询当天新增客户则返回false
        if(!isSearchReturnVisit(condition)){
            return false;
        }
        boolean isIncomeRestoreRate = false;
        boolean isExceedTime = false;
        //判断收入进度
        if(VisualMarketUtils.isListNull(condition.getIncomeRestoreRateList())){
            isIncomeRestoreRate = true;
        }else{
            isIncomeRestoreRate = VisualMarketUtils.isIncomeRestoreRateSearch(condition.getIncomeRestoreRate());
        }
        //超期时长
        if(VisualMarketUtils.isListNull(condition.getExceedTimeList())){
            isExceedTime = true;
        }else{
            isExceedTime = VisualMarketUtils.isExceedTimeSearch(condition.getExceedTime());
        }
        return isIncomeRestoreRate & isExceedTime;
        
    }
    
    /**
     * <p>
     * Description:判断是否为营业部<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-5-6
     * @param deptId
     * @return
     * boolean
     */
    public static boolean isBussinessDept(BussinessDept dept){
        //判断是否营业部
        if (null != dept && dept.getIfBussinessDept() != null && dept.getIfBussinessDept()) {
            return true;
        }
        return false;
    }
    /**
     * <p>
     * Description:分页参数设置<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-5-9
     * @param condition 查询条件
     * @param start 记录开始条数
     * @param limit 查询记录总计条数
     * void
     */
    public static void pagingParamSet(QueryCondition condition,int start, int limit){
        //设置查询记录总计条数
        condition.setLimitRecord(limit);
        //设置记录开始条数
        condition.setStartRecord(start);
        //设置结束记录条数
        condition.setEndRecord(start+limit);
    }
}
