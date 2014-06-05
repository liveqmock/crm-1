package com.deppon.crm.module.marketing.server.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.marketing.server.dao.IBusinessOpportunityDao;
import com.deppon.crm.module.marketing.shared.domain.BoOperationLog;
import com.deppon.crm.module.marketing.shared.domain.BoReportCondition;
import com.deppon.crm.module.marketing.shared.domain.BoReportInfo;
import com.deppon.crm.module.marketing.shared.domain.BusinessOpportunity;
import com.deppon.crm.module.marketing.shared.domain.BusinessOpportunityCondition;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**
 * Title: IBusinessOpportunityDao.java Description: 商机管理DAO
 * 
 * @author huangzhanming
 * @created 2014-3-24 下午3:39:32
 */
public class BusinessOpportunityDao extends iBatis3DaoImpl implements
		IBusinessOpportunityDao {
	private static final String NAMESPACE_BUSINESSOPPORTUNITY = "com.deppon.crm.module.marketing.shared.domain.BusinessOpportunity.";
	private static final String QUERY_BY_CONDITION = "queryBusinessOpportunityByCondition";
	private static final String COUNT_BY_CONDITION = "countBusinessOpportunityByCondition";
	private static final String QUERY_BY_ID = "queryBusinessOpportunityById";
	private static final String QUERY_ACTIVE_BY_CUST = "queryBusinessOpportunityActiveByCust";
	private static final String INSERT = "insertBusinessOpportunity";
	private static final String UPDATE_BY_ID = "updateBusinessOpportunityById";

	private static final String QUERY_FOR_REMIND = "queryBusinessOpportunityForRemind";
	private static final String UPDATE_IN_REMIND = "updateBusinessOpportunityInRemind";

	private static final String UPDATE_BO_LASTTIME = "updateBusinessOpportunityLastTime";
	private static final String UPDATE_IN_ONGOING = "updateBusinessOpportunityInOngoing";
	private static final String UPDATE_IN_EXTENDED = "updateBusinessOpportunityInExtended";
	private static final String UPDATE_IN_DORMANT = "updateBusinessOpportunityInDormant";
	private static final String UPDATE_IN_EXPIRE_FAILURE = "updateBusinessOpportunityInExpireFailure";
	private static final String UPDATE_IN_DORMANT_FAILURE = "updateBusinessOpportunityInDormantFailure";
	private static final String UPDATE_IN_SUCCESS = "updateBusinessOpportunityInSuccess";
	private static final String UPDATE_IN_STEP_DELIVER = "updateBusinessOpportunityStepDeliver";

	private static final String ADD_OPERATION_LOG = "insertOperationLog";
	private static final String ADD_OPERATION_LOG_STEP_DELIVER = "insertOperationLogStepDeliver";

	private static final String QUERY_REPORT_BY_CONDITION = "queryBoReportByCondition";
	private static final String CALC_REPORT_FOR_DAILY = "calcBoReportForDaily";

	/**
	 * @discription 商机查询分页方法
	 * @author huangzhanming
	 * @created 2014-3-24 下午3:28:55
	 * @param boc
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BusinessOpportunity> queryBusinessOpportunityByCondition(
			BusinessOpportunityCondition boc) {
		// if (boc.getStart() == 0) {
		// boc.setStart(1);
		// }
		return (List<BusinessOpportunity>) this.getSqlSession().selectList(
				NAMESPACE_BUSINESSOPPORTUNITY + QUERY_BY_CONDITION, boc);
	}

	/**
	 * @discription 商机查询分页计数
	 * @author huangzhanming
	 * @created 2014-3-24 下午3:29:16
	 * @param boc
	 * @return
	 */
	@Override
	public long countBusinessOpportunityByCondition(
			BusinessOpportunityCondition boc) {
		return (Long) this.getSqlSession().selectOne(
				NAMESPACE_BUSINESSOPPORTUNITY + COUNT_BY_CONDITION, boc);
	}

	/**
	 * @discription 获取指定的商机信息
	 * @author huangzhanming
	 * @created 2014-3-24 下午3:30:13
	 * @param id
	 * @return
	 */
	@Override
	public BusinessOpportunity queryBusinessOpportunityById(String id) {
		return (BusinessOpportunity) this.getSqlSession().selectOne(
				NAMESPACE_BUSINESSOPPORTUNITY + QUERY_BY_ID, id);
	}

	/**
	 * @discription 查询客户未关闭的商机
	 * @author huangzhanming
	 * @created 2014-3-24 下午3:30:43
	 * @param custId
	 * @param custNum
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BusinessOpportunity> queryActiveByCust(String custId,
			String custNum) {
		if (StringUtils.isEmpty(custId) && StringUtils.isEmpty(custNum)) {
			return new ArrayList<BusinessOpportunity>();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("custId", custId);
		map.put("custNum", custNum);
		return (List<BusinessOpportunity>) this.getSqlSession().selectList(
				NAMESPACE_BUSINESSOPPORTUNITY + QUERY_ACTIVE_BY_CUST, map);
	}

	/**
	 * @discription 创建商机
	 * @author huangzhanming
	 * @created 2014-3-24 下午3:31:12
	 * @param bo
	 * @return
	 */
	@Override
	public String createBusinessOpportunity(BusinessOpportunity bo) {
		this.getSqlSession().insert(NAMESPACE_BUSINESSOPPORTUNITY + INSERT, bo);
		return bo.getId();
	}

	/**
	 * @discription 修改保存商机
	 * @author huangzhanming
	 * @created 2014-3-24 下午3:31:31
	 * @param bo
	 */
	@Override
	public void saveBusinessOpportunity(BusinessOpportunity bo) {
		this.getSqlSession().update(
				NAMESPACE_BUSINESSOPPORTUNITY + UPDATE_BY_ID, bo);
	}

	/**
	 * @discription 查询需要提醒的商机
	 * @author huangzhanming
	 * @created 2014-3-24 下午3:31:52
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BusinessOpportunity> queryBusinessOpportunityForRemind() {
		return (List<BusinessOpportunity>) this.getSqlSession().selectList(
				NAMESPACE_BUSINESSOPPORTUNITY + QUERY_FOR_REMIND);
	}

	/**
	 * @discription 通过回访更新最后修改时间
	 * @author huangzhanming
	 * @created 2014-3-24 下午3:32:39
	 */
	@Override
	public void updateBusinessOpportunityLastTime() {
		this.getSqlSession().update(
				NAMESPACE_BUSINESSOPPORTUNITY + UPDATE_BO_LASTTIME);
	}

	/**
	 * @discription 更新需要提醒的商机提醒次数
	 * @author huangzhanming
	 * @created 2014-3-24 下午3:32:10
	 */
	@Override
	public void updateBusinessOpportunityInRemind() {
		this.getSqlSession().update(
				NAMESPACE_BUSINESSOPPORTUNITY + UPDATE_IN_REMIND);
	}

	/**
	 * @discription 更新需要转化到进行中的商机
	 * @author huangzhanming
	 * @created 2014-3-24 下午3:32:39
	 */
	@Override
	public void updateBusinessOpportunityInOngoing() {
		this.getSqlSession().update(
				NAMESPACE_BUSINESSOPPORTUNITY + UPDATE_IN_ONGOING);
	}

	/**
	 * @discription 更新需要转化到超期进行中的商机
	 * @author huangzhanming
	 * @created 2014-3-24 下午3:32:39
	 */
	@Override
	public void updateBusinessOpportunityInExtended() {
		this.getSqlSession().update(
				NAMESPACE_BUSINESSOPPORTUNITY + UPDATE_IN_EXTENDED);
	}

	/**
	 * @discription 更新需要转化到休眠状态的商机
	 * @author huangzhanming
	 * @created 2014-3-24 下午3:32:39
	 */
	@Override
	public void updateBusinessOpportunityInDormant() {
		this.getSqlSession().update(
				NAMESPACE_BUSINESSOPPORTUNITY + UPDATE_IN_DORMANT);
	}

	/**
	 * @discription 超期关闭
	 * @author huangzhanming
	 * @created 2014-3-24 下午3:32:39
	 */
	@Override
	public void updateBusinessOpportunityInExpireFailure() {
		this.getSqlSession().update(
				NAMESPACE_BUSINESSOPPORTUNITY + UPDATE_IN_EXPIRE_FAILURE);
	}

	/**
	 * @discription 休眠关闭
	 * @author huangzhanming
	 * @created 2014-3-24 下午3:32:39
	 */
	@Override
	public void updateBusinessOpportunityInDormantFailure() {
		this.getSqlSession().update(
				NAMESPACE_BUSINESSOPPORTUNITY + UPDATE_IN_DORMANT_FAILURE);
	}

	/**
	 * @discription 更新状态转化到成功关闭的商机
	 * @author huangzhanming
	 * @created 2014-3-24 下午3:34:09
	 */
	@Override
	public void updateBusinessOpportunityInSuccess() {
		this.getSqlSession().update(
				NAMESPACE_BUSINESSOPPORTUNITY + UPDATE_IN_SUCCESS);
	}

	/**
	 * @discription 保存阶段变更日志
	 * @author huangzhanming
	 * @created 2014-3-24 下午3:34:50
	 * @param logList
	 */
	@Override
	public void addBusinessOpportunityLog(List<BoOperationLog> logList) {
		for (BoOperationLog boOperationLog : logList) {
			this.getSqlSession().insert(
					NAMESPACE_BUSINESSOPPORTUNITY + ADD_OPERATION_LOG,
					boOperationLog);
		}
	}

	/**
	 * @discription 竞标投标到持续发货的阶段转变
	 * @author huangzhanming
	 * @created 2014-3-24 下午3:35:14
	 */
	@Override
	public void updateBusinessOpportunityStepDeliver() {
		this.getSqlSession().update(
				NAMESPACE_BUSINESSOPPORTUNITY + UPDATE_IN_STEP_DELIVER);
	}

	/**
	 * 
	 * @discription 竞标投标到持续发货的阶段转变日志
	 * @author huangzhanming
	 * @created 2014-3-24 下午3:36:15
	 */
	@Override
	public void addBusinessOpportunityStepDeliverLog() {
		this.getSqlSession().insert(
				NAMESPACE_BUSINESSOPPORTUNITY + ADD_OPERATION_LOG_STEP_DELIVER);
	}

	/**
	 * @discription 商机效果评估查询
	 * @author huangzhanming
	 * @created 2014-3-25 下午2:01:03
	 * @param brc
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BoReportInfo> queryBoReportByCondition(BoReportCondition brc) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deptId", brc.getDeptId());
		map.put("beginTime", brc.getBeginTime());
		map.put("endTime", brc.getEndTime());
		this.getSqlSession().selectOne(
				NAMESPACE_BUSINESSOPPORTUNITY + QUERY_REPORT_BY_CONDITION, map);
		return (List<BoReportInfo>) map.get("BoReportInfos");
		// return (List<BoReportInfo>) this.getSqlSession().selectList(
		// NAMESPACE_BUSINESSOPPORTUNITY + QUERY_REPORT_BY_CONDITION);
	}

	/**
	 * @discription 商机效果评估报表日报表
	 * @author huangzhanming
	 * @created 2014-3-25 下午2:01:03
	 * @return
	 */
	@Override
	public void calcBoReportForDaily() {
		this.getSqlSession().selectOne(
				NAMESPACE_BUSINESSOPPORTUNITY + CALC_REPORT_FOR_DAILY);
	}

}
