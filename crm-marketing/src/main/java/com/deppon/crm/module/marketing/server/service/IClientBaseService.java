package com.deppon.crm.module.marketing.server.service;

import java.util.List;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.shared.domain.Detail;
import com.deppon.crm.module.marketing.shared.domain.activity.ClientBase;
import com.deppon.crm.module.marketing.shared.domain.activity.LineDept;
import com.deppon.crm.module.marketing.shared.domain.activity.Multiple;
import com.deppon.crm.module.organization.shared.domain.Department;

public interface IClientBaseService {
	/**
	 * 
	 * 
	 * <p>
	 * 增加客户群<br/>
	 * </p>
	 * 
	 * @author lvchongxin
	 * @version 0.1 2014-3-5
	 * @param clientBase
	 * @return int
	 */
	int addClientBase(ClientBase clientBase);

	/**
	 * 
	 * 
	 * <p>
	 * 增加客户群多选项<br/>
	 * </p>
	 * 
	 * @author lvchongxin
	 * @version 0.1 2014-3-5
	 * @param clientMultiple
	 * @return boolean
	 */
	int addClientMultiple(Multiple multiple);

	/**
	 * 
	 * 
	 * <p>
	 * 增加部门出发到达线路<br/>
	 * </p>
	 * 
	 * @author lvchongxin
	 * @version 0.1 2014-3-5
	 * @param clientLineDept
	 * @return String
	 */
	int addLineDept(LineDept lineDept);

	/**
	 * 
	 * 
	 * <p>
	 * 查询客户群名称是否重复<br/>
	 * </p>
	 * 
	 * @author lvchongxin
	 * @version 0.1 2014-3-5
	 * @param clientBaseName
	 * @return boolean
	 */
	boolean searchClientBaseName(ClientBase clientBase);


	/**
	 * 
	 * 
	 * <p>
	 * 根据客户群Id查状态<br/>
	 * </p>
	 * 
	 * @author lvchongxin
	 * @version 0.1 2014-3-10
	 * @param
	 * @return
	 */
	 String searchClientBaseById(String clintBaseId);

	/**
	 * 
	 * 
	 * <p>
	 * 按条件查询客户群<br/>
	 * </p>
	 * 
	 * @author lvchongxin
	 * @version 0.1 2014-3-5
	 * @param condition
	 *            ,start,limit
	 * @return List<ClientBase>
	 */
	List<ClientBase> searchClientBase(ClientBase condition, int start,
			int limit, User user);

	/**
	 * 
	 * 
	 * <p>
	 * 按条件查询客户群总数<br/>
	 * </p>
	 * 
	 * @author lvchongxin
	 * @version 0.1 2014-3-8
	 * @param clientBase
	 * @return int
	 */
	int searchClientBaseCount(ClientBase condition, User user);

	/**
	 * 
	 * 
	 * <p>
	 * 查询创建人ID和部门负责人Id<br/>
	 * </p>
	 * 
	 * @author lvchongxin
	 * @version 0.1 2014-3-10
	 * @param
	 * @return
	 */
	List<String> searchAuthorityId(String clintBaseId);
	/**
	 * 
	 * 
	 * <p>
	 * 删除多选项ById<br/>
	 * </p>
	 * 
	 * @author lvchongxin
	 * @version 0.1 2014-3-11
	 * @param
	 * @return
	 */

	 boolean deleteMultipleById(String clintBaseId,String conditionType);

	/**
	 * 
	 * 
	 * <p>
	 * 删除部门ById<br/>
	 * </p>
	 * 
	 * @author lvchongxin
	 * @version 0.1 2014-3-11
	 * @param
	 * @return
	 */
	boolean deleteLineDeptById(String clintBaseId, String lineType);

	/**
	 * 
	 * 
	 * <p>
	 * 查询客户群详细<br/>
	 * </p>
	 * 
	 * @author lvchongxin
	 * @version 0.1 2014-3-10
	 * @param multiple
	 * @return List<Multiple>
	 */
	ClientBase searchClientBaseDetail(ClientBase clientBase);
	/**
	 * 
	 * 
	 * <p>
	 * 更新客户群<br/>
	 * </p>
	 * 
	 * @author lvchongxin
	 * @version 0.1 2014-3-11
	 * @param
	 * @return
	 */
	 boolean updateClientBase(ClientBase clientBase);
	 
		/**
		 * 
		 * 
		 * <p>
		 * 更新客户群<br/>
		 * </p>
		 * 
		 * @author lvchongxin
		 * @version 0.1 2014-3-11
		 * @param
		 * @return
		 */
		 boolean updateClientBaseStatus(ClientBase clientBase);
	/**
	 * 
	 * 
	 * <p>
	 * 查询二级行业by一级行业List<br/>
	 * </p>
	 * 
	 * @author lvchongxin
	 * @version 0.1 2014-3-13
	 * @param trades
	 * @return List<String>
	 */

	List<Detail> searchSecondTradesByList(List<String> trades);
	
	 /**
	  * 
	 * @Title: searchSecondTradesToString 
	 * @Description: TODO(查看详情用查询二级行业)
	 * @author lvchongxin 
	 * @param @param conditionId
	 * @param @param conditionType
	 * @param @return    设定文件 
	 * @return ClientBase    返回类型 
	 * @throws
	  */
	 List<String>  searchSecondTradesToString(String conditionId,String conditionType);

}
