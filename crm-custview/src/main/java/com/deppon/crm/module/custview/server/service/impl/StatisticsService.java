package com.deppon.crm.module.custview.server.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.deppon.crm.module.custview.server.dao.IStatisticsDao;
import com.deppon.crm.module.custview.server.service.IStatisticsService;
import com.deppon.crm.module.custview.shared.domain.ComplaintStatistics;
import com.deppon.crm.module.custview.shared.domain.CrossMapping;
import com.deppon.crm.module.custview.shared.domain.LatelyTrade;
import com.deppon.crm.module.custview.shared.domain.OperationAnalysis;
import com.deppon.crm.module.custview.shared.domain.RecStatistics;

/**
 * @description 360度视图分析service
 * @author 安小虎
 * @version 0.1 2012-4-25
 * @date 2012-4-25
 */

public class StatisticsService implements IStatisticsService {
	/*
	 * 客户360视图分析DAO接口
	 */
	private IStatisticsDao statisticsDao;
	
	/**
	 * @param statisticsDao : set the property statisticsDao.
	 */
	public void setStatisticsDao(IStatisticsDao statisticsDao) {
		this.statisticsDao = statisticsDao;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.custview.server.service.impl.IStatisticsService#getRecStatisticsListByCustId(java.lang.String)
	 */
	@Override
	public List<RecStatistics> getRecStatisticsListByCustId(String custId) {
		/*
		 * return 理赔分析数据集合
		 */
		return this.statisticsDao.getRecStatisticsListByCustId(custId);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.custview.server.service.impl.IStatisticsService#getComplaintStatisticsListByCustId(java.lang.String)
	 */
	@Override
	public List<ComplaintStatistics> getComplaintStatisticsListByCustId(
			String custId) {
		/*
		 * return 投诉分析数据集合
		 */
		return this.statisticsDao.getComplaintStatisticsListByCustId(custId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.custview.server.service.IStatisticsService#
	 * getOperationAnalysisListByCustId(java.lang.String)
	 */
	@Override
	public List<OperationAnalysis> getOperationAnalysisListByCustId(String custType,
			String custId, Date startDate, Date endDate) {
		/*
		 * return 营运分析数据集合
		 */
		 List<OperationAnalysis> list = this.statisticsDao.getOperationAnalysisListByCustId(custType,custId,
				startDate, endDate);
		 return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.custview.server.service.IStatisticsService#
	 * getCustLatelyTradeDate(java.lang.String)
	 */
	@Override
	public LatelyTrade getCustLatelyTradeDateByCustId(String custId) {
		/*
		 * return 根据客户ID获得其最近一次交易日期
		 */
		return this.statisticsDao.getCustLatelyTradeDateByCustId(custId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.custview.server.service.IStatisticsService#
	 * getComplaintStatisticsByCustId(java.lang.String)
	 */
	@Override
	public List getComplaintStatisticsByCustId(String custId) {
		/*
		 * return 投诉分析数据
		 */
		return this.statisticsDao.getComplaintStatisticsByCustId(custId);
	}

	/**
	 * <p>
	 * Description:根据客户编码获得营运分析数据.
	 * </p>
	 * @author 	106143
	 * @extends	@see com.deppon.crm.module.custview.server.service.IStatisticsService#getOperationAnalysisListExpByCustId(java.lang.String, java.util.Date, java.util.Date)
	 * @version 0.1 2014-4-3下午6:33:25
	 * @param custId
	 * @param startDate
	 * @param endDate
	 * @return
	 * @update 	2014-4-3下午6:33:25
	 */
	@Override
	public List<OperationAnalysis> getOperationAnalysisListExpByCustNo(
			String custNo, Date startDate, Date endDate) {
		return this.statisticsDao.getOperationAnalysisListExpByCustNo(custNo, startDate, endDate);
	}

	/**
	 * <p>
	 * Description:根据外部系统ID（客户里面的erpId）获得交叉映射信息
	 * </p>
	 * @author 	106143
	 * @extends	@see com.deppon.crm.module.custview.server.service.IStatisticsService#getCrossMappingByContactId(java.lang.String)
	 * @version 0.1 2014-4-23下午2:55:40
	 * @param contactId
	 * @return
	 * @update 	2014-4-23下午2:55:40
	 */
	@Override
	public List<CrossMapping> getCrossMappingByErpId(String erpId) {
		return this.statisticsDao.getCrossMappingByErpId(erpId);
	}

	/**
	 * <p>
	 * Description:获取发到货周期表中 发到货金额数据 
	 * </p>
	 * @author 	106143
	 * @extends	@see com.deppon.crm.module.custview.server.service.IStatisticsService#getLAMoneyByCondtion(java.lang.Object, java.lang.String, java.util.Date, java.util.Date)
	 * @version 0.1 2014-4-30上午9:52:18
	 * @param object
	 * @param custId
	 * @param startDate
	 * @param endDate
	 * @return
	 * @update 	2014-4-30上午9:52:18
	 */
	@Override
	public List<OperationAnalysis> getLAMoneyByCondtion(
			String custId, Date startDate, Date endDate) {
		return this.statisticsDao.getLAMoneyByCondtion(custId,startDate,endDate);
	}
	
	/**
	 * <p>
	 * Description:获取发到货周期表中 发到货金额数据 
	 * </p>
	 * @author 	106143
	 * @extends	@see com.deppon.crm.module.custview.server.service.IStatisticsService#getLAMoneyByCondtion(java.lang.Object, java.lang.String, java.util.Date, java.util.Date)
	 * @version 0.1 2014-4-30上午9:52:18
	 * @param object
	 * @param custId
	 * @param startDate
	 * @param endDate
	 * @return
	 * @update 	2014-4-30上午9:52:18
	 */
	@Override
	public List<OperationAnalysis> getLAMoneyExpByCondtion(
			String custId, Date startDate, Date endDate) {
		return this.statisticsDao.getLAMoneyExpByCondtion(custId,startDate,endDate);
	}

}
