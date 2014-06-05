package com.deppon.crm.module.marketing.server.utils;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.customer.server.utils.ValidateUtil;
import com.deppon.crm.module.marketing.shared.domain.visualMarket.QueryCondition;
import com.deppon.crm.module.marketing.shared.exception.PlanException;
import com.deppon.crm.module.marketing.shared.exception.PlanExceptionType;
import com.deppon.crm.module.marketing.shared.exception.VisualMarketException;
import com.deppon.crm.module.marketing.shared.exception.VisualMarketExceptionType;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.foss.framework.shared.util.string.StringUtil;

/**
 * <p>
 * Description:可视化营销查询校验类<br />
 * </p>
 * @title VisualMarketValidator.java
 * @package com.deppon.crm.module.marketing.server.utils 
 * @author 石应华
 * @version 0.1 2013-5-4
 */
public class VisualMarketValidator {
    
    /**
     * <p>
     * Description:验证查询条件为空的情况<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-5-23
     * @param condition
     * void
     */
    public static void checkCondition(QueryCondition condition){
        //验证查询条件为空，如果为空则抛出异常
        if(ValidateUtil.objectIsEmpty(condition)){
            //新建conditionMustNotNull异常
            VisualMarketException e = new VisualMarketException(VisualMarketExceptionType.conditionMustNotNull);
            // 抛出异常
            throw new GeneralException(e.getErrorCode(), e.getMessage(), e,new Object[] {}) {};
        }
    }
    
    /**
     * <p>
     * Description:校验通过客户id和客户类型是否为空<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-5-4
     * @param custId 客户id
     * @param custType 客户类型
     * void
     */
    public static void checkSearchCustById(String custId,String custType){
        //判断客户id是否为空
        if(StringUtil.isEmpty(custId)){
            //新建custIdNotNull异常
            VisualMarketException e = new VisualMarketException(VisualMarketExceptionType.custIdNotNull);
            // 抛出异常
            throw new GeneralException(e.getErrorCode(), e.getMessage(), e,new Object[] {}) {};
        }
      //判断客户类型是否为空
        if(StringUtil.isEmpty(custType)){
            //新建custTypeNotNull异常
            VisualMarketException e = new VisualMarketException(VisualMarketExceptionType.custTypeNotNull);
            // 抛出异常
            throw new GeneralException(e.getErrorCode(), e.getMessage(), e,new Object[] {}) {};
        }
    }
    
    /**
     * <p>
     * Description:校验可视化视图查询查询条件<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-5-6
     * @param condition 查询条件
     * void
     */
    public static void checkSearchCustMarketInfo(QueryCondition condition){
        //如果传入的查询条件为空，给出异常提示
        if (ValidateUtil.objectIsEmpty(condition)) {
            //新建conditionMustNotNull异常
            VisualMarketException e = new VisualMarketException(VisualMarketExceptionType.conditionMustNotNull);
            // 抛出异常
            throw new GeneralException(e.getErrorCode(), e.getMessage(), e,new Object[] {}) {};
        }
        //如果传入的部门ID为空，则给出异常提示
        if (StringUtils.isEmpty(condition.getDeptId())) {
            //新建deptIdMustNotNull异常
            VisualMarketException e = new VisualMarketException(VisualMarketExceptionType.deptIdMustNotNull);
            // 抛出异常
            throw new GeneralException(e.getErrorCode(), e.getMessage(), e,new Object[] {}) {};
        }
        //如果传入的客户类型为空,且客户编码为空，给出异常提示
        if ((VisualMarketUtils.isArryNull(condition.getCustDegree()) && 
                StringUtils.isEmpty(condition.getPsCustType())) && 
                StringUtils.isEmpty(condition.getCustNumber())) {
            //新建custTypeMustNotNull异常
            VisualMarketException e = new VisualMarketException(VisualMarketExceptionType.custTypeMustNotNull);
            // 抛出异常
            throw new GeneralException(e.getErrorCode(), e.getMessage(), e, new Object[] {}) {};
        }
        //如果传入的地图标记为空，或者长度为空，给出异常提示
        if (StringUtils.isEmpty(condition.getMapMakerStatus())) {
            VisualMarketException e = new VisualMarketException(VisualMarketExceptionType.markStatusMustNotNull);
            // 抛出异常
            throw new GeneralException(e.getErrorCode(), e.getMessage(), e,new Object[] {}) {};
        }
		//创建开始时间
		Date createBeginTime = condition.getCreateBeginTime();
		//创建结束时间
		Date createEndTime = condition.getCreateEndTime();
		//当前时间
		Date currentTime = new Date();
		//创建开始时间大于创建结束时间
		if(createBeginTime != null && createEndTime != null){
			if (PlanValidateUtils.compareToDate(createBeginTime, createEndTime) > 0) {
				//开始时间大于结束日期
	            VisualMarketException e = new VisualMarketException(VisualMarketExceptionType.createBeginTimeBiggerThanEndTime);
	            // 抛出异常
	            throw new GeneralException(e.getErrorCode(), e.getMessage(), e,new Object[] {}) {};
			}
			
			//创建结束时间大于当前时间
			if (PlanValidateUtils.compareToDate(createEndTime,currentTime) > 0) {
				//开始时间大于结束日期
	            VisualMarketException e = new VisualMarketException(VisualMarketExceptionType.createEndTimeBiggerThanCurrentTime);
	            // 抛出异常
	            throw new GeneralException(e.getErrorCode(), e.getMessage(), e,new Object[] {}) {};
			}
		}

		//当所选择的客户类型为潜散客时
		if("PC_CUSTOMER".equals(condition.getPsCustType()) || "SC_CUSTOMER".equals(condition.getPsCustType())){
			if(createBeginTime == null){
				//开始创建时间为空
	            VisualMarketException e = new VisualMarketException(VisualMarketExceptionType.createBeginTimeIsNull);
	            // 抛出异常
	            throw new GeneralException(e.getErrorCode(), e.getMessage(), e,new Object[] {}) {};
			}
			
			if(createEndTime == null){
				//开始创建时间为空
	            VisualMarketException e = new VisualMarketException(VisualMarketExceptionType.createEndTimeIsNull);
	            // 抛出异常
	            throw new GeneralException(e.getErrorCode(), e.getMessage(), e,new Object[] {}) {};
			}
			
			// 创建时间范围超过3个月
			if (ScheduleValiateUtils.getDaysBetweenDates(createBeginTime, createEndTime, 3)) {
	            VisualMarketException e = new VisualMarketException(VisualMarketExceptionType.createTimeCanNotExceedThreeMonth);
	            // 抛出异常
	            throw new GeneralException(e.getErrorCode(), e.getMessage(), e,new Object[] {}) {};
			}
		}
    }
    
    /**
     * <p>
     * Description:如果传入的部门ID为空，给出异常提示"部门ID为空，获取部门客户标签失败"<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-5-6
     * @param deptId 部门ID
     * void
     */
    public static void checkDeptId(String deptId){
        //验证部门ID是否为空，如果为空则抛出异常
        if (StringUtils.isEmpty(deptId)) {
            //新建deptIdMustNotNull异常
            VisualMarketException e = new VisualMarketException(VisualMarketExceptionType.deptIdMustNotNull);
            //抛出异常
            throw new GeneralException(e.getErrorCode(), e.getMessage(), e,new Object[] {}) {};
        }
    }
    
}
