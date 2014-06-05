package com.deppon.crm.module.marketing.server.manager;

import java.util.List;

import com.deppon.crm.module.marketing.shared.domain.BoReportCondition;
import com.deppon.crm.module.marketing.shared.domain.BoReportInfo;
import com.deppon.crm.module.marketing.shared.domain.BusinessOpportunity;
import com.deppon.crm.module.marketing.shared.domain.BusinessOpportunityCondition;

/**
 * Title: IBusinessOpportunityManager.java Description: 商机管理Manager
 * 
 * @author huangzhanming
 * @created 2014-3-25 下午2:04:39
 */
public interface IBusinessOpportunityManager {

	/**
	 * @discription 商机查询分页
	 * @author huangzhanming
	 * @created 2014-3-25 下午2:04:16
	 * @param boc
	 * @return
	 */
	List<BusinessOpportunity> queryBusinessOpportunityByCondition(
			BusinessOpportunityCondition boc);

	/**
	 * @discription 商机查询分页计数
	 * @author huangzhanming
	 * @created 2014-3-25 下午2:04:14
	 * @param boc
	 * @return
	 */
	long countBusinessOpportunityByCondition(BusinessOpportunityCondition boc);

	/**
	 * @discription 获取指定商机
	 * @author huangzhanming
	 * @created 2014-3-25 下午2:04:10
	 * @param id
	 * @return
	 */
	BusinessOpportunity queryBusinessOpportunityById(String id);

	/**
	 * @discription 创建商机
	 * @author huangzhanming
	 * @created 2014-3-25 下午2:04:08
	 * @param bo
	 */
	void createBusinessOpportunity(BusinessOpportunity bo);

	/**
	 * @discription 修改保存商机
	 * @author huangzhanming
	 * @created 2014-3-25 下午2:04:05
	 * @param bo
	 */
	void saveBusinessOpportunity(BusinessOpportunity bo);

	/**
	 * @discription 提醒商机待办事宜
	 * @author huangzhanming
	 * @created 2014-3-25 下午2:04:02
	 */
	void execBusinessOpportunityReminder();

	/**
	 * @discription 商机转入休眠
	 * @author huangzhanming
	 * @created 2014-3-25 下午2:03:51
	 */
	void execBusinessOpportunityToDormant();

	/**
	 * @discription 商机成功关闭
	 * @author huangzhanming
	 * @created 2014-3-25 下午2:03:48
	 */
	void execBusinessOpportunityInSuccess();

	/**
	 * @discription 商机竞标投标转持续发货
	 * @author huangzhanming
	 * @created 2014-3-25 下午2:02:36
	 */
	void execBusinessOpportunityToStepDeliver();

	/**
	 * @discription 查询客户当前未关闭的商机
	 * @author huangzhanming
	 * @created 2014-3-25 下午2:01:03
	 * @param custId
	 * @return
	 */
	List<BusinessOpportunity> queryBusinessOpportunityActiveByCustId(
			String custId);

	/**
	 * @discription 查询客户当前未关闭的商机
	 * @author huangzhanming
	 * @created 2014-3-25 下午2:01:03
	 * @param custNum
	 * @return
	 */
	List<BusinessOpportunity> queryBusinessOpportunityActiveByCustNum(
			String custNum);

	/**
	 * @discription 是否存在查询客户当前未关闭的商机
	 * @author huangzhanming
	 * @created 2014-3-25 下午2:01:03
	 * @param custId
	 * @return
	 */
	boolean isExistedActiveByCustId(String custId);

	/**
	 * @discription 是否存在查询客户当前未关闭的商机
	 * @author huangzhanming
	 * @created 2014-3-25 下午2:01:03
	 * @param custId
	 * @return
	 */
	boolean isExistedActiveByCustNum(String custNum);

	/**
	 * @discription 商机效果评估查询
	 * @author huangzhanming
	 * @created 2014-3-25 下午2:01:03
	 * @param brc
	 * @return
	 */
	List<BoReportInfo> queryBoReportByCondition(BoReportCondition brc);

	/**
	 * @discription 查询客户当前未关闭的商机
	 * @author huangzhanming
	 * @created 2014-3-25 下午2:01:03
	 * @param custId
	 * @return
	 */
	List<BusinessOpportunity> queryBusinessOpportunityByCustId(String custId);

	/**
	 * @discription 商机效果评估报表日报表
	 * @author huangzhanming
	 * @created 2014-3-25 下午2:01:03
	 * @return
	 */
	void calcBoReportForDaily();
}
