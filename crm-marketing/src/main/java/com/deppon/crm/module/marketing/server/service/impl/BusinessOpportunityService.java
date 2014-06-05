package com.deppon.crm.module.marketing.server.service.impl;

import java.util.List;

import com.deppon.crm.module.marketing.server.dao.IBusinessOpportunityDao;
import com.deppon.crm.module.marketing.server.service.IBusinessOpportunityService;
import com.deppon.crm.module.marketing.shared.domain.BoOperationLog;
import com.deppon.crm.module.marketing.shared.domain.BoReportCondition;
import com.deppon.crm.module.marketing.shared.domain.BoReportInfo;
import com.deppon.crm.module.marketing.shared.domain.BusinessOpportunity;
import com.deppon.crm.module.marketing.shared.domain.BusinessOpportunityCondition;

/**
 * 
 * Title: IBusinessOpportunityService.java Description: 商机管理Service
 * 
 * @author huangzhanming
 * @created 2014-3-25 上午11:24:36
 */
public class BusinessOpportunityService implements IBusinessOpportunityService {
	private IBusinessOpportunityDao businessOpportunityDao;

	public void setBusinessOpportunityDao(
			IBusinessOpportunityDao businessOpportunityDao) {
		this.businessOpportunityDao = businessOpportunityDao;
	}

	/**
	 * @discription 商机查询分页方法
	 * @author huangzhanming
	 * @created 2014-3-24 下午3:28:55
	 * @param boc
	 * @return
	 */
	@Override
	public List<BusinessOpportunity> queryBusinessOpportunityByCondition(
			BusinessOpportunityCondition boc) {
		return businessOpportunityDao.queryBusinessOpportunityByCondition(boc);
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
		return businessOpportunityDao.countBusinessOpportunityByCondition(boc);
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
		return businessOpportunityDao.queryBusinessOpportunityById(id);
	}

	/**
	 * @discription 查询客户未关闭的商机
	 * @author huangzhanming
	 * @created 2014-3-24 下午3:30:43
	 * @param custId
	 * @param custNum
	 * @return
	 */
	@Override
	public List<BusinessOpportunity> queryActiveByCust(String custId,
			String custNum) {
		return businessOpportunityDao.queryActiveByCust(custId, custNum);
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
		return businessOpportunityDao.createBusinessOpportunity(bo);
	}

	/**
	 * @discription 修改保存商机
	 * @author huangzhanming
	 * @created 2014-3-24 下午3:31:31
	 * @param bo
	 */
	@Override
	public void saveBusinessOpportunity(BusinessOpportunity bo) {
		businessOpportunityDao.saveBusinessOpportunity(bo);
	}

	/**
	 * @discription 查询需要提醒的商机
	 * @author huangzhanming
	 * @created 2014-3-24 下午3:31:52
	 * @return
	 */
	@Override
	public List<BusinessOpportunity> queryBusinessOpportunityForRemind() {
		return businessOpportunityDao.queryBusinessOpportunityForRemind();
	}

	/**
	 * @discription 更新需要提醒的商机提醒次数
	 * @author huangzhanming
	 * @created 2014-3-24 下午3:32:10
	 */
	@Override
	public void updateBusinessOpportunityInRemind() {
		businessOpportunityDao.updateBusinessOpportunityInRemind();
	}

	/**
	 * @discription 更新需要转化到休眠状态的商机
	 * @author huangzhanming
	 * @created 2014-3-24 下午3:32:39
	 */
	@Override
	public void updateBusinessOpportunityInDormant() {
		businessOpportunityDao.updateBusinessOpportunityLastTime();
		businessOpportunityDao.updateBusinessOpportunityInOngoing();
		businessOpportunityDao.updateBusinessOpportunityInExtended();
		businessOpportunityDao.updateBusinessOpportunityInDormant();
		businessOpportunityDao.updateBusinessOpportunityInDormantFailure();
		businessOpportunityDao.updateBusinessOpportunityInExpireFailure();
	}

	/**
	 * @discription 更新状态转化到成功关闭的商机
	 * @author huangzhanming
	 * @created 2014-3-24 下午3:34:09
	 */
	@Override
	public void updateBusinessOpportunityInSuccess() {
		businessOpportunityDao.updateBusinessOpportunityInSuccess();
	}

	/**
	 * @discription 保存阶段变更日志
	 * @author huangzhanming
	 * @created 2014-3-24 下午3:34:50
	 * @param logList
	 */
	@Override
	public void addBusinessOpportunityLog(List<BoOperationLog> logList) {
		businessOpportunityDao.addBusinessOpportunityLog(logList);
	}

	/**
	 * @discription 竞标投标到持续发货的阶段转变
	 * @author huangzhanming
	 * @created 2014-3-24 下午3:35:14
	 */
	@Override
	public void updateBusinessOpportunityStepDeliver() {
		businessOpportunityDao.updateBusinessOpportunityStepDeliver();
	}

	/**
	 * 
	 * @discription 竞标投标到持续发货的阶段转变日志
	 * @author huangzhanming
	 * @created 2014-3-24 下午3:36:15
	 */
	@Override
	public void addBusinessOpportunityStepDeliverLog() {
		businessOpportunityDao.addBusinessOpportunityStepDeliverLog();
	}

	/**
	 * @discription 商机效果评估查询
	 * @author huangzhanming
	 * @created 2014-3-25 下午2:01:03
	 * @param brc
	 * @return
	 */
	@Override
	public List<BoReportInfo> queryBoReportByCondition(BoReportCondition brc) {
		return businessOpportunityDao.queryBoReportByCondition(brc);
	}

	/**
	 * @discription 商机效果评估报表日报表
	 * @author huangzhanming
	 * @created 2014-3-25 下午2:01:03
	 * @return
	 */
	@Override
	public void calcBoReportForDaily() {
		businessOpportunityDao.calcBoReportForDaily();
	}

}
