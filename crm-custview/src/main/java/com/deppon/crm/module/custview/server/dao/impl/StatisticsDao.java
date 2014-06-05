package com.deppon.crm.module.custview.server.dao.impl;

/**
 * 时间类
 */
import java.util.Date;
/**
 * HashMap
 */
import java.util.HashMap;
/**
 * List
 */
import java.util.List;
/**
 * Map
 */
import java.util.Map;
/**
 * 客户360视图分析DAO 接口
 */
import com.deppon.crm.module.custview.server.dao.IStatisticsDao;
/**
 * 投诉统计.POJO
 */
import com.deppon.crm.module.custview.shared.domain.ComplaintStatistics;
import com.deppon.crm.module.custview.shared.domain.CrossMapping;
import com.deppon.crm.module.custview.shared.domain.LatelyTrade;
/**
 * 营运分析.POJO
 */
import com.deppon.crm.module.custview.shared.domain.OperationAnalysis;
/**
 * 理赔统计.POJO
 */
import com.deppon.crm.module.custview.shared.domain.RecStatistics;
import com.deppon.crm.module.custview.shared.util.CustviewUtil;
/**
 * Description:ibatisDao实现类
 */
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**
 * @description 客户360视图分析DAO
 * @author 安小虎
 * @version 0.1 2012-4-25
 * @date 2012-4-25
 */
public class StatisticsDao extends iBatis3DaoImpl implements IStatisticsDao {
	/**
	 * domain 包路径
	 */
	private static final String NAMESPACE = "com.deppon.crm.module.custview.shared.domain.";

	/**
	 * 根据客户ID获得理赔分析数据.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<RecStatistics> getRecStatisticsListByCustId(String custId) {
		//return 根据客户ID获得理赔分析数据.
		return this.getSqlSession().selectList(
				NAMESPACE + "getRecStatisticsListByCustId", custId);
	}

	/**
	 * 投诉分析数据集合
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ComplaintStatistics> getComplaintStatisticsListByCustId(
			String custId) {
		//return 投诉分析数据集合
		return this.getSqlSession().selectList(
				NAMESPACE + "getComplaintStatisticsListByCustId", custId);
	}

	/**
	 * 根据客户ID获得营运分析数据.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<OperationAnalysis> getOperationAnalysisListByCustId(String custType,
			String custId, Date startDate, Date endDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("custId", custId);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		if("EXPRESS".equals(custType)){
			//return 根据客户编码获得快递营运分析数据.
			return this.getOperationAnalysisListExpByCustNo(custId, startDate, endDate);
			
		}else {
			//return 根据客户ID获得零担营运分析数据.
			return this.getSqlSession().selectList(
					NAMESPACE + "getOperationAnalysisListByCustId", map);
		}
	}
	//getOperationAnalysisListExpByCustId
	/**
	 * <p>
	 * Description:根据客户编码获得快递营运分析数据.
	 * </p>
	 * @author 	106143
	 * @extends	@see com.deppon.crm.module.custview.server.dao.IStatisticsDao#getOperationAnalysisListExpByCustNo(java.lang.String, java.util.Date, java.util.Date)
	 * @version 0.1 2014-4-3下午6:36:11
	 * @param custNo
	 * @param startDate
	 * @param endDate
	 * @return
	 * @update 	2014-4-3下午6:36:11
	 */
	@Override
	public List<OperationAnalysis> getOperationAnalysisListExpByCustNo(
			String custNo, Date startDate, Date endDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("custNo", custNo);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		//return 根据客户ID获得营运分析数据.
		return this.getSqlSession().selectList(
				NAMESPACE + "getOperationAnalysisListExpByCustId", map);
	}
	/**
	 * 根据客户ID获得其最近一次交易日期.
	 */
	@Override
	public LatelyTrade getCustLatelyTradeDateByCustId(String custId) {
		//return 根据客户ID获得其最近一次交易日期.
		return (LatelyTrade)this.getSqlSession().selectOne(
				NAMESPACE + "getCustLatelyTradeDateByCustId", custId);
	}

	/**
	 * 投诉分析数据
	 */
	@Override
	public List getComplaintStatisticsByCustId(String custId) {
		//return 投诉分析数据
		return this.getSqlSession().selectList(
				NAMESPACE + "getComplaintStatisticsByCustId", custId);
	}

	/**
	 * <p>
	 * Description:根据外部系统ID（客户里面的erpId）获得交叉映射信息
	 * </p>
	 * @author 	106143
	 * @extends	@see com.deppon.crm.module.custview.server.dao.IStatisticsDao#getCrossMappingByContactId(java.lang.String)
	 * @version 0.1 2014-4-23下午2:56:56
	 * @param contactId
	 * @return
	 * @update 	2014-4-23下午2:56:56
	 */
	@Override
	public List<CrossMapping> getCrossMappingByErpId(String custId) {
		return this.getSqlSession().selectList(NAMESPACE
				+"getCrossMappingByErpId",custId);
	}

	/**
	 * <p>
	 * Description:获取发到货周期表中 发到货金额数据 
	 * </p>
	 * @author 	106143
	 * @extends	@see com.deppon.crm.module.custview.server.dao.IStatisticsDao#getLAMoneyByCondtion(java.lang.String, java.util.Date, java.util.Date)
	 * @version 0.1 2014-4-30上午9:54:23
	 * @param custId
	 * @param startDate
	 * @param endDate
	 * @return
	 * @update 	2014-4-30上午9:54:23
	 */
	@Override
	public List<OperationAnalysis> getLAMoneyByCondtion(String custId,
			Date startDate, Date endDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("custId", custId);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		//return 根据客户ID获得营运分析数据.
		List<OperationAnalysis>listL =  this.getSqlSession().selectList(
				NAMESPACE + "getLMoneyByCondtion", map);
		List<OperationAnalysis>listA =  this.getSqlSession().selectList(
				NAMESPACE + "getAMoneyByCondtion", map);
		return CustviewUtil.assembleOperationAnalysis(listL,listA);
	}
	
	/**
	 * <p>
	 * Description:获取发到货周期表中 快递发到货金额数据 
	 * </p>
	 * @author 	106143
	 * @extends	@see com.deppon.crm.module.custview.server.dao.IStatisticsDao#getLAMoneyByCondtion(java.lang.String, java.util.Date, java.util.Date)
	 * @version 0.1 2014-4-30上午9:54:23
	 * @param custId
	 * @param startDate
	 * @param endDate
	 * @return
	 * @update 	2014-4-30上午9:54:23
	 */
	@Override
	public List<OperationAnalysis> getLAMoneyExpByCondtion(String custId,
			Date startDate, Date endDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("custId", custId);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		//return 根据客户ID获得营运分析数据.
		List<OperationAnalysis>listL =  this.getSqlSession().selectList(
				NAMESPACE + "getLMoneyExpByCondtion", map);
		List<OperationAnalysis>listA =  this.getSqlSession().selectList(
				NAMESPACE + "getAMoneyExpByCondtion", map);
		return CustviewUtil.assembleOperationAnalysis(listL,listA);
	}
}
