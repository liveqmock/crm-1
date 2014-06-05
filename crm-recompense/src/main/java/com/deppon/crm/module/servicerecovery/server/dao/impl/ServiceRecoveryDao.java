package com.deppon.crm.module.servicerecovery.server.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.servicerecovery.server.dao.IServiceRecoveryDao;
import com.deppon.crm.module.servicerecovery.shared.domain.ServiceRecovery;
import com.deppon.crm.module.servicerecovery.shared.domain.ServiceRecoverySearchCondition;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**
 * 
 * @description：服务补救Dao实现类
 * @author 华龙
 * @version 1.0
 * @date 2012-10-29下午2:14:17
 */
public class ServiceRecoveryDao extends iBatis3DaoImpl implements
		IServiceRecoveryDao {
	private static final String NAMESPACE = "com.deppon.crm.module.servicerecovery.shared.domain.ServiceRecovery.";
	private static final String FIND_VALID_SERVICE_RECOVERY_BYNUM = "findValidServiceRecoveryByNum";
	private static final String ADD_SERVICE_RECOVERY = "addServiceRecovery";
	private static final String UPDATE_SERVICE_RECOVERY = "updateServiceRecovery";
	private static final String SEARCH_SERVICE_RECOVERY_BYCONDITION = "searchServiceRecoveryByCondition";
	private static final String COUNT_SERVICE_RECOVERY_BYCONDITION = "countServiceRecoveryByCondition";
	private static final String GET_SERVICE_RECOVERY_BYID = "getServiceRecoveryById";
	private static final String GET_SERVICE_RECOVERY_BYWORKFLOWNUM = "findServiceRecoveryByOaWorkflowNum";
	private static final String UPDATE_SERVICE_RECOVERY_WORK_FLOW = "updateServiceRecoveryWorkflowNum";
	private static final String GET_SERVICE_RECOVERY_BYWORKFLOWNO = "findServiceRecoveryByWorkflowNo";
	/**
	 * 
	 * @description ：根据运单号查询有效实体
	 * @author 华龙
	 * @version 1.0
	 * @param :String waybillNumber:运单号
	 * @date 2012-10-29 下午2:35:52
	 * @return Waybill 运单号
	 */
	@Override
	public ServiceRecovery findValidServiceRecoveryByNum(String waybillNumber) {
		if (waybillNumber == null || "".equals(waybillNumber)) {
			return null;
		}
		List<ServiceRecovery> list = this.getSqlSession().selectList(
				NAMESPACE + FIND_VALID_SERVICE_RECOVERY_BYNUM, waybillNumber);
		if (list.size() == 0) {
			return null;
		}
		return list.get(0);

	}

	/**
	 * 
	 * @description ：保存服务补救
	 * @author 华龙
	 * @version 1.0
	 * @param :ServiceRecovery serviceRecovery：服务补救实体
	 * @date 2012-10-29 下午2:36:02
	 * @return void
	 */
	@Override
	public ServiceRecovery addServiceRecovery(ServiceRecovery serviceRecovery) {
		if (null == serviceRecovery) {
			return null;
		}
		if (null == serviceRecovery.getId()
				|| "".equals(serviceRecovery.getId().trim())) {
			this.getSqlSession().insert(NAMESPACE + ADD_SERVICE_RECOVERY,
					serviceRecovery);
			return serviceRecovery;
		}
		return null;
	}

	/**
	 * 
	 * @description ：根据条件查询服务补救列表
	 * @author 华龙
	 * @version 1.0
	 * @param ServiceRecoverySearchCondition
	 *            condition : 服务补救搜索条件
	 * @date 2012-10-29 下午2:41:42
	 * @return List<ServiceRecovery> 服务补救列表
	 */
	@Override
	public List<ServiceRecovery> searchServiceRecoveryByCondition(
			ServiceRecoverySearchCondition condition) {
		RowBounds bounds = new RowBounds(condition.getStart(),
				condition.getLimit());
		List<ServiceRecovery> list = (List<ServiceRecovery>) getSqlSession()
				.selectList(NAMESPACE + SEARCH_SERVICE_RECOVERY_BYCONDITION,
						condition, bounds);

		return list;
	}

	/**
	 * 
	 * @description ：根据条件统计总条数
	 * @author 华龙
	 * @version 1.0
	 * @param ServiceRecoverySearchCondition
	 *            condition : 服务补救搜索条件
	 * @date 2012-10-29 下午2:47:47
	 * @return int 总条数
	 */
	@Override
	public int countServiceRecoveryByCondition(
			ServiceRecoverySearchCondition condition) {

		return (Integer) this.getSqlSession().selectOne(
				NAMESPACE + COUNT_SERVICE_RECOVERY_BYCONDITION, condition);
	}

	/**
	 * 
	 * @description ：根据条件导出服务补救
	 * @author 华龙
	 * @version 1.0
	 * @param ServiceRecoverySearchCondition
	 *            condition :服务补救搜索条件
	 * @date 2012-10-29 下午2:42:06
	 * @return List<ServiceRecovery> 服务补救列表
	 */
	@Override
	public List<ServiceRecovery> exportServiceRecoveryByCondition(
			ServiceRecoverySearchCondition condition) {
		List<ServiceRecovery> list = (List<ServiceRecovery>) getSqlSession()
				.selectList(NAMESPACE + SEARCH_SERVICE_RECOVERY_BYCONDITION,
						condition);
		return list;
	}

	/**
	 * 
	 * @description ：根据ID查询服务补救
	 * @author 华龙
	 * @version 1.0
	 * @param String
	 *            serviceRecoveryId :服务补救Id
	 * @date 2012-10-29 下午2:42:14
	 * @return ServiceRecovery 服务补救实体
	 */
	@Override
	public ServiceRecovery getServiceRecoveryById(String serviceRecoveryId) {
		if (null != serviceRecoveryId && !"".equals(serviceRecoveryId)) {
			return (ServiceRecovery) this.getSqlSession().selectOne(
					NAMESPACE + GET_SERVICE_RECOVERY_BYID, serviceRecoveryId);
		}
		return null;
	}

	/**
	 * @description ：根据运单查询服务补救
	 * @author 华龙
	 * @version 1.0
	 * @param String
	 *            serviceRecoveryId :服务补救Id
	 * @date 2012-10-29 下午2:42:14
	 * @return ServiceRecovery 服务补救实体
	 */
	@Override
	public ServiceRecovery getServiceRecoveryByOaWorkflowNum(String oaWorkFlowNum) {
		if (null != oaWorkFlowNum && !"".equals(oaWorkFlowNum)) {
			return (ServiceRecovery) this.getSqlSession().selectOne(
					NAMESPACE + GET_SERVICE_RECOVERY_BYWORKFLOWNUM, oaWorkFlowNum);
		}
		return null;
	}

	/**
	 * 
	 * @description ：根据运单查询服务补救
	 * @author 华龙
	 * @version 1.0
	 * @param String
	 *            Id :服务补救Id workflowNum:工作流号
	 * @date 2012-10-29 下午2:42:14
	 * @return
	 */

	@Override
	public void updateServiceRecoveryWorkflowNum(String id, String workflowNum, String workflowNo) {
		if (null != id && null != workflowNum && null != workflowNo) {
			Map map = new HashMap();
			map.put("id", id);
			map.put("workflowNum", workflowNum);
			map.put("workflowNo", workflowNo);
			getSqlSession().update(
					NAMESPACE + UPDATE_SERVICE_RECOVERY_WORK_FLOW, map);
		}
	}

	/**
	 * 
	 * @description ：更新服务补救
	 * @author 华龙
	 * @version 1.0
	 * @param String
	 *            Id :服务补救Id workflowNum:工作流号
	 * @date 2012-10-29 下午2:42:14
	 * @return
	 */
	@Override
	public void updateServiceRecovery(ServiceRecovery serviceRecovery) {
		if (null != serviceRecovery) {
			getSqlSession().update(NAMESPACE + UPDATE_SERVICE_RECOVERY,
					serviceRecovery);
		}
	}

	public void deleteServiceRecovery(String id) {
		getSqlSession().delete(NAMESPACE + "delteById", id);
	}
	
	
	
	@Override
	public ServiceRecovery findServiceRecoveryByWorkflowNo(String workflowNo) {
		if (null != workflowNo && !"".equals(workflowNo)) {
			return (ServiceRecovery) this.getSqlSession().selectOne(
					NAMESPACE + GET_SERVICE_RECOVERY_BYWORKFLOWNO, workflowNo);
		}
		return null;
	}

}
