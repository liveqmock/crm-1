package com.deppon.crm.module.backfreight.server.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.backfreight.server.dao.IBackFreightDao;
import com.deppon.crm.module.backfreight.shared.domain.BackFreight;
import com.deppon.crm.module.backfreight.shared.domain.BackFreightSearchCondition;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**
 * 
 * @description：退运费dao接口实现类
 * @author 华龙
 * @version 1.0
 * @date 2012-10-29下午2:20:01
 */
public class BackFreightDao extends iBatis3DaoImpl implements IBackFreightDao {
	//命名空间
	private static final String NAMESPACE = "com.deppon.crm.module.backfreight.shared.domain.BackFreight.";
	//查询有效的退运费
	private static final String FIND_VALID_BACK_FREIGHT_BYNUM = "findValidBackFreightByNum";
	//增加退运费
	private static final String ADD_BACK_FREIGHT = "addBackFreight";
	//更新退运费
	private static final String UPDATE_BACK_FREIGHT = "updateBackFreight";
	//根据条件查询退运费
	private static final String SEARCH_BACK_FREIGHT_BYCONDITION = "searchBackFreightByCondition";
	//根据条件查询退运费条数
	private static final String COUNT_BACK_FREIGHT_BYCONDITION = "countBackFreightByCondition";
	//根据id查询退运费
	private static final String GET_BACK_FREIGHT_BYID = "getBackFreightById";
	//根据工作流号查询退运费
	private static final String GET_BACK_FREIGHT_BYWORKFLOWNUM = "findBackFreightByOaWorkflowNum";
	//更新退运费工作流号
	private static final String UPDATE_BACK_FREIGHT_WORK_FLOW = "updateBackFreightWorkflowNum";

	/**
	 * 
	 * @description ：根据运单号查询运单实体
	 * @author 华龙
	 * @version 1.0
	 * @param :String waybillNumber:运单号
	 * @date 2012-10-29 下午2:35:52
	 * @return Waybill 运单号
	 */
	public BackFreight findValidBackFreightByNum(String waybillNumber) {
		//判断退运费是否为空
		if (waybillNumber == null || "".equals(waybillNumber)) {
			return null;
		}
		List<BackFreight> ss = this.getSqlSession().selectList(
				NAMESPACE + FIND_VALID_BACK_FREIGHT_BYNUM, waybillNumber);
		if (ss.size() == 0) {
			return null;
		}
		return ss.get(0);

	}

	/**
	 * 
	 * @description ：保存退运费
	 * @author 华龙
	 * @version 1.0
	 * @param :BackFreight BackFreight：退运费实体
	 * @date 2012-10-29 下午2:36:02
	 * @return void
	 */
	public BackFreight addBackFreight(BackFreight backFreight) {
		if (null == backFreight) {
			return null;
		}
		if (null == backFreight.getId()
				|| "".equals(backFreight.getId().trim())) {
			this.getSqlSession().insert(NAMESPACE + ADD_BACK_FREIGHT,
					backFreight);
			return backFreight;
		}
		return null;
	}

	/**
	 * 
	 * @description ：根据条件查询退运费列表
	 * @author 华龙
	 * @version 1.0
	 * @param BackFreightSearchCondition
	 *            condition : 退运费搜索条件
	 * @date 2012-10-29 下午2:41:42
	 * @return List<BackFreight> 退运费列表
	 */

	public List<BackFreight> searchBackFreightByCondition(
			BackFreightSearchCondition condition) {
		//设置分页
		RowBounds bounds = new RowBounds(condition.getStart(),
				condition.getLimit());
		List<BackFreight> list = (List<BackFreight>) getSqlSession()
				.selectList(NAMESPACE + SEARCH_BACK_FREIGHT_BYCONDITION,
						condition, bounds);
		return list;
	}

	/**
	 * 
	 * @description ：根据条件统计总条数
	 * @author 华龙
	 * @version 1.0
	 * @param BackFreightSearchCondition
	 *            condition : 退运费搜索条件
	 * @date 2012-10-29 下午2:47:47
	 * @return int 总条数
	 */
	public int countBackFreightByCondition(BackFreightSearchCondition condition) {
		return (Integer) this.getSqlSession().selectOne(
				NAMESPACE + COUNT_BACK_FREIGHT_BYCONDITION, condition);
	}

	/**
	 * 
	 * @description ：根据条件导出退运费
	 * @author 华龙
	 * @version 1.0
	 * @param BackFreightSearchCondition
	 *            condition :退运费搜索条件
	 * @date 2012-10-29 下午2:42:06
	 * @return List<BackFreight> 退运费列表
	 */
	public List<BackFreight> exportBackFreightByCondition(
			BackFreightSearchCondition condition) {
		List<BackFreight> list = (List<BackFreight>) getSqlSession()
				.selectList(NAMESPACE + SEARCH_BACK_FREIGHT_BYCONDITION,
						condition);

		return list;
	}

	/**
	 * 
	 * @description ：根据ID查询退运费
	 * @author 华龙
	 * @version 1.0
	 * @param String
	 *            BackFreightId :退运费Id
	 * @date 2012-10-29 下午2:42:14
	 * @return BackFreight 退运费实体
	 */
	public BackFreight getBackFreightById(String backFreightId) {
		if (null != backFreightId && !"".equals(backFreightId)) {
			return (BackFreight) this.getSqlSession().selectOne(
					NAMESPACE + GET_BACK_FREIGHT_BYID, backFreightId);
		}
		return null;
	}

	/**
	 * 
	 * @description ：根据id更新工作流号
	 * @author 华龙
	 * @version 1.0
	 * @param String
	 *            BackFreightId :退运费Id String oaWorkflowNum :工作流号
	 * @date 2012-10-29 下午2:42:14
	 * @return BackFreight 退运费实体
	 */
	public void updateBackFreightWorkflowNum(String id, String workflowNum) {
		if (null != id && null != workflowNum) {
			//设置mao存放id和工作流号
			Map map = new HashMap();
			map.put("id", id);
			map.put("workflowNum", workflowNum);
			getSqlSession().update(NAMESPACE + UPDATE_BACK_FREIGHT_WORK_FLOW,
					map);
		}
	}

	@Override
	public BackFreight findBackFreightByOaWorkflowNum(String oaWorkflowNum) {
		if (null != oaWorkflowNum && !"".equals(oaWorkflowNum)) {
			return (BackFreight) this.getSqlSession().selectOne(
					NAMESPACE + GET_BACK_FREIGHT_BYWORKFLOWNUM, oaWorkflowNum);
		}
		return null;
	}

	/**
	 * 
	 * @description ：更新退运费
	 * @author 华龙
	 * @version 1.0
	 * @param BackFreight
	 *            backFreight :退运费实体
	 * @date 2012-10-29 下午2:42:14
	 * @return
	 */
	@Override
	public void updateBackFreight(BackFreight backFreight) {
		if (null != backFreight) {
			getSqlSession()
					.update(NAMESPACE + UPDATE_BACK_FREIGHT, backFreight);
		}
	}

	@Override
	public void deleteBackFreightById(String id) {
		if (null != id) {
			getSqlSession().update(NAMESPACE + "deleteById", id);
		}
	}
}
