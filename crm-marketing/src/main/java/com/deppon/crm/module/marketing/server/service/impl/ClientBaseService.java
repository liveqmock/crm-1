package com.deppon.crm.module.marketing.server.service.impl;

import java.util.List;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.shared.domain.Detail;
import com.deppon.crm.module.marketing.server.dao.IClientBaseDao;
import com.deppon.crm.module.marketing.server.service.IClientBaseService;
import com.deppon.crm.module.marketing.shared.domain.activity.ClientBase;
import com.deppon.crm.module.marketing.shared.domain.activity.LineDept;
import com.deppon.crm.module.marketing.shared.domain.activity.Multiple;
import com.deppon.crm.module.organization.shared.domain.Department;

public class ClientBaseService implements IClientBaseService {
	// 客户群DAO
	private IClientBaseDao ClientBaseDao;

	public IClientBaseDao getClientBaseDao() {
		return ClientBaseDao;
	}

	public void setClientBaseDao(IClientBaseDao clientBaseDao) {
		ClientBaseDao = clientBaseDao;
	}

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

	@Override
	public int addClientBase(ClientBase clientBase) {

		return ClientBaseDao.addClientBase(clientBase);
	}

	/**
	 * 
	 * 
	 * <p>
	 * 增加客户群多选项<br/>
	 * </p>
	 * 
	 * @author lvchongxin
	 * @version 0.1 2014-3-5
	 * @param clientBase
	 * @return boolean
	 */

	@Override
	public int addClientMultiple(Multiple multiple) {
		return ClientBaseDao.addClientMultiple(multiple);
	}

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
	@Override
	public int addLineDept(LineDept lineDept) {
		return ClientBaseDao.addLineDept(lineDept);
	}

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
	@Override
	public boolean searchClientBaseName(ClientBase clientBase) {

		return ClientBaseDao.searchClientBaseName(clientBase);
	}

	/**
	 * 
	 * 
	 * <p>
	 * 按条件查询客户群<br/>
	 * </p>
	 * 
	 * @author lvchongxin
	 * @version 0.1 2014-3-8
	 * @param clientBase
	 * @return List<ClientBase>
	 */

	@Override
	public List<ClientBase> searchClientBase(ClientBase condition, int start,
			int limit, User user) {

		return ClientBaseDao.searchClientBase(condition, start, limit, user);
	}

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
	public String searchClientBaseById(String clintBaseId) {
		return ClientBaseDao.searchClientBaseById(clintBaseId);
	};

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
	public int searchClientBaseCount(ClientBase condition, User user) {
		return ClientBaseDao.searchClientBaseCount(condition, user);
	};

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
	@Override
	public List<String> searchAuthorityId(String clintBaseId) {

		return ClientBaseDao.searchAuthorityId(clintBaseId);
	}

	/**
	 * 
	 * 
	 * <p>
	 * 删除多选项Byid<br/>
	 * </p>
	 * 
	 * @author lvchongxin
	 * @version 0.1 2014-3-11
	 * @param
	 * @return
	 */
	@Override
	public boolean deleteMultipleById(String clintBaseId,String conditionType) {

		return ClientBaseDao.deleteMultipleById(clintBaseId, conditionType);
	}

	/**
	 * 
	 * 
	 * <p>
	 * 删除部门线路ByID<br/>
	 * </p>
	 * 
	 * @author lvchongxin
	 * @version 0.1 2014-3-11
	 * @param
	 * @return
	 */
	@Override
	public boolean deleteLineDeptById(String clintBaseId, String lineType) {

		return ClientBaseDao.deleteLineDeptById(clintBaseId, lineType);
	}

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
	public ClientBase searchClientBaseDetail(ClientBase clientBase) {

		return ClientBaseDao.searchClientBaseDetail(clientBase);
	};

	// /**
	// *
	// *
	// * <p>
	// * 按条件查询线路部门<br/>
	// * </p>
	// *
	// * @author lvchongxin
	// * @version 0.1 2014-3-11
	// * @param lineDept
	// * @return List<LineDept>
	// */
	// @Override
	// public List<LineDept> searchLineDeptByCondition(LineDept lineDept) {
	//
	// return ClientBaseDao.searchLineDeptByCondition(lineDept);
	// }

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

	@Override
	public boolean updateClientBase(ClientBase clientBase) {

		return ClientBaseDao.updateClientBase(clientBase);
	}

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

	@Override
	public boolean updateClientBaseStatus(ClientBase clientBase) {

		return ClientBaseDao.updateClientBaseStatus(clientBase);
	}


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
	@Override
	public List<Detail> searchSecondTradesByList(List<String> trades) {

		return ClientBaseDao.searchSecondTradesByList(trades);
	}
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
	@Override
	public List<String> searchSecondTradesToString(String conditionId,
			String conditionType) {		
		return ClientBaseDao.searchSecondTradesToString(conditionId, conditionType);
	}
}
