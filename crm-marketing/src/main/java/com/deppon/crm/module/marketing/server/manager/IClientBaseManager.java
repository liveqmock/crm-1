package com.deppon.crm.module.marketing.server.manager;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.shared.domain.Detail;
import com.deppon.crm.module.marketing.shared.domain.activity.ClientBase;

/**
 * <p>
 * Description:客户群<br />
 * </p>
 * 
 * @title IClientBaseManage.java
 * @package com.deppon.crm.module.marketing.server.manager
 * @author lvchongxin
 * @version 0.1 Mar 3, 2014
 */
public interface IClientBaseManager {
	/**
	 * 
	 * 
	 * <p>
	 * 通过部门名称模糊查询<br/>
	 * </p>
	 * 
	 * @author lvchongxin
	 * @version 0.1 2014-3-7
	 * @param deptName
	 *            ,start,limit
	 * @return Map<String,Object>
	 */
	Map<String, Object> searchDeptListByName(String deptName, int start,
			int limit);

	/**
	 * 
	 * 
	 * <p>
	 * 客户群新增<br/>
	 * </p>
	 * 
	 * @author lvchongxin
	 * @version 0.1 2014-3-5
	 * @param clientBase
	 *            ，user
	 * @return boolean
	 */
	boolean addClientBase(ClientBase clientBase, User user);

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
	Map<String, Object> searchClientBase(ClientBase condition, int start,
			int limit, User user);

	/**
	 * 
	 * 
	 * <p>
	 * 删除客户群ByID<br/>
	 * </p>
	 * 
	 * @author lvchongxin
	 * @version 0.1 2014-3-10
	 * @param clintBaseId
	 * @return boolean
	 */
	boolean deleteClientBaseById(String clintBaseId, User user);

	/**
	 * 
	 * 
	 * <p>
	 * 查询客户群详情<br/>
	 * </p>
	 * 
	 * @author lvchongxin
	 * @version 0.1 2014-3-10
	 * @param
	 * @return
	 */
	ClientBase searchClientBaseDetail(String clientBaseId);

	/**
	 * 
	 * 
	 * <p>
	 * 更新客户群<br/>
	 * </p>
	 * 
	 * @author lvchongxin
	 * @version 0.1 2014-3-11
	 * @param cilentBase
	 * @return boolean
	 */
	boolean updateClientBase(ClientBase cilentBase, User user, String isChange);

	/**
	 * 
	 * 
	 * <p>
	 * 更新客户群状态数量<br/>
	 * </p>
	 * 
	 * @author lvchongxin
	 * @version 0.1 2014-3-17
	 * @param clientBase
	 * @return boolean
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
	 * 
	 * <p>
	 * 检查客户群名称是否重复<br/>
	 * </p>
	 * 
	 * @author lvchongxin
	 * @version 0.1 2014-3-18
	 * @param
	 * @return
	 */
	boolean  checkClientBaseName(ClientBase clientBase);
	
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

