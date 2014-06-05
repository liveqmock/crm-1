package com.deppon.crm.module.custview.server.dao;

import java.util.Date;
import java.util.List;

import com.deppon.crm.module.custview.shared.domain.ComplaintStatistics;
import com.deppon.crm.module.custview.shared.domain.CrossMapping;
import com.deppon.crm.module.custview.shared.domain.LatelyTrade;
import com.deppon.crm.module.custview.shared.domain.OperationAnalysis;
import com.deppon.crm.module.custview.shared.domain.RecStatistics;

/**
 * @description 客户360视图分析DAO接口
 * @author 安小虎
 * @version 0.1 2012-4-25
 * @date 2012-4-25
 */

public interface IStatisticsDao {
	/**
	 * 
	 * @description 根据客户ID获得理赔分析数据.
	 * @author 安小虎
	 * @version 0.1 2012-4-25
	 * @param 客户ID
	 * @date 2012-4-25
	 * @return 理赔分析数据集合
	 * @update 2012-4-25 下午1:47:29
	 */
	List<RecStatistics> getRecStatisticsListByCustId(String custId);

	/**
	 * 
	 * @description 根据客户ID获得投诉分析数据.
	 * @author 安小虎
	 * @version 0.1 2012-4-25
	 * @param 客户ID
	 * @date 2012-4-25
	 * @return 投诉分析数据集合
	 * @update 2012-4-25 下午1:48:17
	 */
	List<ComplaintStatistics> getComplaintStatisticsListByCustId(String custId);

	/**
	 * 
	 * @description 根据客户ID获得投诉分析数据.
	 * @author 安小虎
	 * @version 0.1 2012-4-25
	 * @param 客户ID
	 * @date 2012-7-20
	 * @return 投诉分析数据
	 * @update 2012-7-20 上午8:48:17
	 */
	List getComplaintStatisticsByCustId(String custId);

	/**
	 * 
	 * @description 根据客户ID获得营运分析数据.
	 * @author 安小虎
	 * @version 0.1 2012-4-26
	 * @param 客户ID
	 * @date 2012-4-26
	 * @return 营运分析数据集合
	 * @update 2012-4-26 上午8:52:45
	 */
	List<OperationAnalysis> getOperationAnalysisListByCustId(String custType,String custId,
			Date startDate, Date endDate);

	/**
	 * 
	 * @description 根据客户ID获得其最近一次交易日期.
	 * @author 安小虎
	 * @version 0.1 2012-4-26
	 * @param 客户ID
	 * @date 2012-4-26
	 * @return none
	 * @update 2012-4-26 上午9:32:40
	 */
	LatelyTrade getCustLatelyTradeDateByCustId(String custId);

	/**
	 * <p>
	 * Description:根据客户编码获得快递营运分析数据
	 * </p>
	 * @author 106143
	 * @version 0.1 2014-4-3下午6:35:42
	 * @param custNo
	 * @param startDate
	 * @param endDate
	 * @return
	 * List<OperationAnalysis>
	 * @update 2014-4-3下午6:35:42
	 */
	List<OperationAnalysis> getOperationAnalysisListExpByCustNo(String custNo,
			Date startDate, Date endDate);

	/**
	 * <p>
	 * Description:根据外部系统ID（客户里面的erpId）获得交叉映射信息
	 * </p>
	 * @author 106143
	 * @version 0.1 2014-4-23下午2:56:41
	 * @param contactId
	 * @return
	 * List<CrossMapping>
	 * @update 2014-4-23下午2:56:41
	 */
	List<CrossMapping> getCrossMappingByErpId(String erpId);

	/**
	 * <p>
	 * Description:获取发到货周期表中 发到货金额数据 
	 * </p>
	 * @author 106143
	 * @version 0.1 2014-4-30上午9:53:49
	 * @param custId
	 * @param startDate
	 * @param endDate
	 * @return
	 * List<OperationAnalysis>
	 * @update 2014-4-30上午9:53:49
	 */
	List<OperationAnalysis> getLAMoneyByCondtion(String custId, Date startDate,
			Date endDate);
	
	/**
	 * <p>
	 * Description:获取发到货周期表中 发到货金额数据 
	 * </p>
	 * @author 106143
	 * @version 0.1 2014-4-30上午9:53:49
	 * @param custId
	 * @param startDate
	 * @param endDate
	 * @return
	 * List<OperationAnalysis>
	 * @update 2014-4-30上午9:53:49
	 */
	List<OperationAnalysis> getLAMoneyExpByCondtion(String custId, Date startDate,
			Date endDate);
}
