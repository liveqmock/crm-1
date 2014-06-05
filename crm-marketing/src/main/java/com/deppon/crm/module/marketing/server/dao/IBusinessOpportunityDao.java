package com.deppon.crm.module.marketing.server.dao;

import java.util.List;

import com.deppon.crm.module.marketing.shared.domain.BoOperationLog;
import com.deppon.crm.module.marketing.shared.domain.BoReportCondition;
import com.deppon.crm.module.marketing.shared.domain.BoReportInfo;
import com.deppon.crm.module.marketing.shared.domain.BusinessOpportunity;
import com.deppon.crm.module.marketing.shared.domain.BusinessOpportunityCondition;

/**
 * Title: IBusinessOpportunityDao.java Description: 商机管理DAO
 * 
 * @author huangzhanming
 * @created 2014-3-24 下午3:39:32
 */
public interface IBusinessOpportunityDao {

	/**
	 * @discription 商机查询分页方法
	 * @author huangzhanming
	 * @created 2014-3-24 下午3:28:55
	 * @param boc
	 * @return
	 */
	List<BusinessOpportunity> queryBusinessOpportunityByCondition(
			BusinessOpportunityCondition boc);

	/**
	 * @discription 商机查询分页计数
	 * @author huangzhanming
	 * @created 2014-3-24 下午3:29:16
	 * @param boc
	 * @return
	 */
	long countBusinessOpportunityByCondition(BusinessOpportunityCondition boc);

	/**
	 * @discription 获取指定的商机信息
	 * @author huangzhanming
	 * @created 2014-3-24 下午3:30:13
	 * @param id
	 * @return
	 */
	BusinessOpportunity queryBusinessOpportunityById(String id);

	/**
	 * @discription 查询客户未关闭的商机
	 * @author huangzhanming
	 * @created 2014-3-24 下午3:30:43
	 * @param custId
	 * @param custNum
	 * @return
	 */
	List<BusinessOpportunity> queryActiveByCust(String custId, String custNum);

	/**
	 * @discription 创建商机
	 * @author huangzhanming
	 * @created 2014-3-24 下午3:31:12
	 * @param bo
	 * @return
	 */
	String createBusinessOpportunity(BusinessOpportunity bo);

	/**
	 * @discription 修改保存商机
	 * @author huangzhanming
	 * @created 2014-3-24 下午3:31:31
	 * @param bo
	 */
	void saveBusinessOpportunity(BusinessOpportunity bo);

	/**
	 * @discription 查询需要提醒的商机
	 * @author huangzhanming
	 * @created 2014-3-24 下午3:31:52
	 * @return
	 */
	List<BusinessOpportunity> queryBusinessOpportunityForRemind();

	/**
	 * @discription 更新需要提醒的商机提醒次数
	 * @author huangzhanming
	 * @created 2014-3-24 下午3:32:10
	 */
	void updateBusinessOpportunityInRemind();

	/**
	 * @discription 通过回访更新最后修改时间
	 * @author huangzhanming
	 * @created 2014-3-24 下午3:32:39
	 */
	void updateBusinessOpportunityLastTime();

	/**
	 * @discription 更新需要转化到进行中的商机
	 * @author huangzhanming
	 * @created 2014-3-24 下午3:32:39
	 */
	void updateBusinessOpportunityInOngoing();

	/**
	 * @discription 更新需要转化到超期进行中的商机
	 * @author huangzhanming
	 * @created 2014-3-24 下午3:32:39
	 */
	void updateBusinessOpportunityInExtended();

	/**
	 * @discription 更新需要转化到休眠状态的商机
	 * @author huangzhanming
	 * @created 2014-3-24 下午3:32:39
	 */
	void updateBusinessOpportunityInDormant();

	/**
	 * @discription 超期关闭
	 * @author huangzhanming
	 * @created 2014-3-24 下午3:32:39
	 */
	void updateBusinessOpportunityInExpireFailure();

	/**
	 * @discription 休眠关闭
	 * @author huangzhanming
	 * @created 2014-3-24 下午3:32:39
	 */
	void updateBusinessOpportunityInDormantFailure();

	/**
	 * @discription 更新状态转化到成功关闭的商机
	 * @author huangzhanming
	 * @created 2014-3-24 下午3:34:09
	 */
	void updateBusinessOpportunityInSuccess();

	/**
	 * @discription 保存阶段变更日志
	 * @author huangzhanming
	 * @created 2014-3-24 下午3:34:50
	 * @param logList
	 */
	void addBusinessOpportunityLog(List<BoOperationLog> logList);

	/**
	 * @discription 竞标投标到持续发货的阶段转变
	 * @author huangzhanming
	 * @created 2014-3-24 下午3:35:14
	 */
	void updateBusinessOpportunityStepDeliver();

	/**
	 * 
	 * @discription 竞标投标到持续发货的阶段转变日志
	 * @author huangzhanming
	 * @created 2014-3-24 下午3:36:15
	 */
	void addBusinessOpportunityStepDeliverLog();

	/**
	 * @discription 商机效果评估报表日报表
	 * @author huangzhanming
	 * @created 2014-3-25 下午2:01:03
	 * @return
	 */
	void calcBoReportForDaily();

	/**
	 * @discription 商机效果评估查询
	 * @author huangzhanming
	 * @created 2014-3-25 下午2:01:03
	 * @param brc
	 * @return
	 */
	List<BoReportInfo> queryBoReportByCondition(BoReportCondition brc);
}
