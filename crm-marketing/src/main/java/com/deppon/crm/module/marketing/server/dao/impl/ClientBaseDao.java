package com.deppon.crm.module.marketing.server.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.shared.domain.Detail;
import com.deppon.crm.module.marketing.server.dao.IClientBaseDao;
import com.deppon.crm.module.marketing.shared.domain.activity.ClientBase;
import com.deppon.crm.module.marketing.shared.domain.activity.LineDept;
import com.deppon.crm.module.marketing.shared.domain.activity.Multiple;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

public class ClientBaseDao extends iBatis3DaoImpl implements IClientBaseDao {
	// 命名空间
	private static final String NAMESPACE_MESSAGESEND = "com.deppon.crm.module.marketing.shared.domain.activity.ClientBase.";
	// 查询客户群列表
	private static final String SEARCHCLIENTBASENAMEISREPAAT = "searchClientBaseNameIsRepaat";
	// 插入客户群基础数据
	private static final String INSERTCLIENTBASE = "insertClientBase";
	// 插入客户群多选信息
	private static final String INSERTCLIENTMULTIPLE = "insertClientMultiple";
	// 插入线路部门
	private static final String INSERLINEDPET = "inserLineDpet";
	// 查询客户群
	private static final String SEARCHCLIENTBASE = "searchClientBase";
	// 查询客户群总数
	private static final String SEARCHCLIENTBASECOUNT = "searchClientBaseCount";
	// 查询客户群创建人ID和部门负责人ID
	private static final String SEARCHAUTHORITYID = "searchAuthorityId";
	// 根据客户群ID查状态
	private static final String SEARCHCLIENTBASESTATUSBYID = "searchClientBaseStatusById";
	// 删除部门BY客户群ID
	private static final String DELETELINEDEPTBYID = "deleteLineDeptById";
	// 删除多选项BY客户群Id
	private static final String DELETEMULTIPLEBYID = "deleteMultipleById";
	// 更新客户群
	private static final String UPDATECLIENTBASE = "updateClientBase";
	// 更新客户群
	private static final String UPDATECLIENTBASESTSATUS = "updateClientBaseStatus";
	// 查询客户群详情
	private static final String SEARCHCLIENTBASEDETAIL = "searchClientBaseDetail";
	// 根据一级行业LIst查询二级行业
	private static final String SEARCHSECONDTRADESBYLIST = "searchSecondTradesByList";
	//查看详情用查询二级行业
	private static final String SEARCHSECONDTRADESTOSTRING="searchSecondTradesToString";
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

		return this.getSqlSession().insert(
				NAMESPACE_MESSAGESEND + INSERTCLIENTBASE, clientBase);
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
	 * @param clientMultiple
	 * @return int
	 */
	@Override
	public int addClientMultiple(Multiple multiple) {
		return this.getSqlSession().insert(
				NAMESPACE_MESSAGESEND + INSERTCLIENTMULTIPLE, multiple);
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
	 * @return int
	 */
	@Override
	public int addLineDept(LineDept lineDept) {
		return this.getSqlSession().insert(
				NAMESPACE_MESSAGESEND + INSERLINEDPET, lineDept);
	}

	/**
	 * 
	 * 
	 * <p>
	 * 查询现有客户群名称<br/>
	 * </p>
	 * 
	 * @author lvchongxin
	 * @version 0.1 2014-3-6
	 * @param
	 * @return boolean
	 */
	@Override
	public boolean searchClientBaseName(ClientBase clientBase) {
	
		return (Integer) this.getSqlSession().selectOne(
				NAMESPACE_MESSAGESEND + SEARCHCLIENTBASENAMEISREPAAT, clientBase) > 0 ? true
				: false;
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
		RowBounds rowBounds = new RowBounds(start, limit);
		// 设置当前操作用户
		condition.setCreateUser(user.getId());
		condition.setCurrentDeptId(user.getEmpCode().getDeptId().getId());
		return this.getSqlSession().selectList(
				NAMESPACE_MESSAGESEND + SEARCHCLIENTBASE, condition, rowBounds);
	}

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

		// 设置当前操作用户
		condition.setCreateUser(user.getId());
		return (Integer) this.getSqlSession().selectOne(
				NAMESPACE_MESSAGESEND + SEARCHCLIENTBASECOUNT, condition);

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
		HashMap<String, String> authorityId = (HashMap<String, String>) this
				.getSqlSession().selectOne(
						NAMESPACE_MESSAGESEND + SEARCHAUTHORITYID, clintBaseId);
		List<String> list = new ArrayList();
		// 封装转化为LIST
		if (authorityId != null) {
			list.add(String.valueOf(authorityId.get("USERID")));
			list.add(String.valueOf(authorityId.get("FPRINCIPALID")));
		}
		return list;
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
	@Override
	public String searchClientBaseById(String clintBaseId) {

		return (String) this.getSqlSession()
				.selectOne(NAMESPACE_MESSAGESEND + SEARCHCLIENTBASESTATUSBYID,
						clintBaseId);
	}

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

	@Override
	public boolean deleteMultipleById(String clintBaseId,String conditionType) {
		Map<String, String> map = new HashMap();
		map.put("clintBaseId", clintBaseId);
		map.put("conditionType", conditionType);
		return this.getSqlSession().delete(
				NAMESPACE_MESSAGESEND + DELETEMULTIPLEBYID, map) > 0 ? true
				: false;
	}

	/**
	 * 
	 * 
	 * <p>
	 * 删除部门线路ById<br/>
	 * </p>
	 * 
	 * @author lvchongxin
	 * @version 0.1 2014-3-11
	 * @param
	 * @return
	 */
	@Override
	public boolean deleteLineDeptById(String clintBaseId, String lineType) {
		Map<String, String> map = new HashMap();
		map.put("clintBaseId", clintBaseId);
		map.put("lineType", lineType);
		return this.getSqlSession().delete(
				NAMESPACE_MESSAGESEND + DELETELINEDEPTBYID, map) > 0 ? true
				: false;
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
	public boolean updateClientBase(ClientBase clientBase) {

		return this.getSqlSession().update(
				NAMESPACE_MESSAGESEND + UPDATECLIENTBASE, clientBase) > 0 ? true
				: false;
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

		return this.getSqlSession().update(
				NAMESPACE_MESSAGESEND + UPDATECLIENTBASESTSATUS, clientBase) > 0 ? true
				: false;
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
	 * @param clientBase
	 * @return clientBase
	 */
	@Override
	public ClientBase searchClientBaseDetail(ClientBase clientBase) {

		return (ClientBase) this.getSqlSession().selectOne(
				NAMESPACE_MESSAGESEND + SEARCHCLIENTBASEDETAIL, clientBase);
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

		return this.getSqlSession().selectList(
				NAMESPACE_MESSAGESEND + SEARCHSECONDTRADESBYLIST, trades);
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
		Map<String, String> map = new HashMap();
		map.put("conditionId", conditionId);
		map.put("conditionType", conditionType);
		return this.getSqlSession().selectList(NAMESPACE_MESSAGESEND+SEARCHSECONDTRADESTOSTRING, map);
	}
}
