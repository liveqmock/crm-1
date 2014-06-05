package com.deppon.crm.module.marketing.server.manager.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.shared.domain.Detail;
import com.deppon.crm.module.marketing.server.manager.IClientBaseManager;
import com.deppon.crm.module.marketing.server.manager.IMarketActivityManager;
import com.deppon.crm.module.marketing.server.service.IClientBaseService;
import com.deppon.crm.module.marketing.server.utils.ClientBaseUtils;
import com.deppon.crm.module.marketing.server.utils.ClientBaseValidator;
import com.deppon.crm.module.marketing.server.utils.MarketActivityUtils;
import com.deppon.crm.module.marketing.server.utils.MarketActivityValidator;
import com.deppon.crm.module.marketing.shared.domain.activity.ClientBase;
import com.deppon.crm.module.marketing.shared.domain.activity.LineDept;
import com.deppon.crm.module.marketing.shared.domain.activity.MarketActivityConstance;
import com.deppon.crm.module.marketing.shared.domain.activity.Multiple;
import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.shared.domain.Department;

public class ClientBaseManager implements IClientBaseManager {

	// 客户群service
	private IClientBaseService clientBaseService;
	// 营销活动service
	private IMarketActivityManager marketActivityManager;
	// 部门Service
	private IDepartmentService departmentService;

		

	public IMarketActivityManager getMarketActivityManager() {
		return marketActivityManager;
	}

	public void setMarketActivityManager(
			IMarketActivityManager marketActivityManager) {
		this.marketActivityManager = marketActivityManager;
	}

	public void setClientBaseService(IClientBaseService clientBaseService) {
		this.clientBaseService = clientBaseService;
	}
	
	public IDepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	/**
	 * 
	 * <p>
	 * 客户群新增<br/>
	 * </p>
	 * 
	 * @author lvchongxin
	 * @version 0.1 2014-3-5
	 * @param clientBaseVO
	 * @return boolean
	 */
	@Transactional
	@Override
	public boolean addClientBase(ClientBase clientBase, User user) {

		// 效验用户是否有权限新建客户群
		marketActivityManager.getUserDeptCharacter(user);
		// 查询客户群名称是否重复
		boolean flag = checkClientBaseName(clientBase);
		clientBase.setCreateDate(new Date());
		// 验证新增条件必填是否为空，客户群名称是否重复
		ClientBaseValidator.checkAddCilentBase(clientBase, flag);
		// 设置创建人
		clientBase.setCreateUser(user.getEmpCode().getId());
		// 设置归属部门
		clientBase.setClientDeptId(user.getEmpCode().getDeptId().getId());
		// 设置客户群状态
		clientBase
				.setClientBaseStatus(MarketActivityConstance.CLIENTBASE_STATUS_UNUSED);
		// 插入客户群基础数据
		clientBaseService.addClientBase(clientBase);
		// 封装多选条件至实体
		List<Multiple> multiples = pottingMultiple(clientBase);
		// 判断多选条件是否全为空
		if (null != multiples && 0 < multiples.size()) {
			for (Multiple m : multiples) {
				clientBaseService.addClientMultiple(m);
			}
		}
		List<LineDept> ld = clientBase.getLineDept();
		// 判断线路部门是否为空
		if (null != ld && 0 < ld.size()) {
			// 批量增加
			for (LineDept l : ld) {
				// 设置业务类型
				l.setConditionType(MarketActivityConstance.ACTIVITY_RELATION_CLIENT);
				// 设置业务表ID
				l.setConditionId(clientBase.getId());
				// 设置创建人
				l.setCreateUser(clientBase.getCreateUser());
				l.setCreateDate(new Date());
				clientBaseService.addLineDept(l);
				// marketActivityService.insertLineDept(l);
			}
		}
		return true;

	}

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
	@Override
	public Map<String, Object> searchDeptListByName(String deptName, int start,
			int limit) {
		deptName="%"+deptName+"%";
		Map<String, Object> map = new HashMap<String, Object>();
		// 模糊查询部门列表
		List<Department> deptList = departmentService.queryOutFieldListByName(deptName,start,limit);
		// 模糊查询部门总数
		int totalCount =  departmentService.queryOutFieldListByCount(deptName);
		map.put("deptList", deptList);
		map.put("totalCount", totalCount);
		return map;
	}

	/**
	 * 
	 * 
	 * <p>
	 * 查询客户群<br/>
	 * </p>
	 * 
	 * @author lvchongxin
	 * @version 0.1 2014-3-10
	 * @param
	 * @return
	 */
	@Override
	public Map<String, Object> searchClientBase(ClientBase condition,
			int start, int limit, User user) {
		Map<String, Object> map = new HashMap<String, Object>();
		//设置部门属性（用于区分是否是营销活动管理小组和快递市场营销组）
		String deptCharacter = MarketActivityValidator.checkUserAuthDept(user);
		condition.setDeptCharacter(deptCharacter);
		//去除空格
		condition.setClientBaseName(condition.getClientBaseName().trim());
		// 不查询已作废客户群
		condition
				.setClientBaseStatus(MarketActivityConstance.CLIENTBASE_STATUS_CANCELLATION);
		List<ClientBase> clientBaseList = clientBaseService.searchClientBase(
				condition, start, limit, user);
		int totalCount = clientBaseService.searchClientBaseCount(condition,
				user);
		map.put("clientBaseList", clientBaseList);
		map.put("totalCount", totalCount);

		return map;
	}

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
	@Transactional
	@Override
	public boolean deleteClientBaseById(String clintBaseId, User user) {

		// 验证是否具有更新权限
		String status = clientBaseService.searchClientBaseById(clintBaseId);
		ClientBaseValidator.checkModiftyAuthority(
				clientBaseService.searchAuthorityId(clintBaseId), status, user);
		ClientBase clientBase = new ClientBase();
		clientBase.setId(clintBaseId);
		clientBase
				.setClientBaseStatus(MarketActivityConstance.CLIENTBASE_STATUS_CANCELLATION);
		clientBase.setModifyUser(user.getEmpCode().getId());
		clientBase.setModifyDate(new Date());
		ClientBaseValidator.checkIfdelete(clientBaseService.updateClientBaseStatus(clientBase));
		return true;
	}

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
	@Override
	public ClientBase searchClientBaseDetail(String clientBaseId) {
		// 创建查询对象
		ClientBase c = new ClientBase();
		// 设置查询客户群Id
		c.setId(clientBaseId);
		return clientBaseService.searchClientBaseDetail(c);
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
	 * @param cilentBase
	 * @return boolean
	 */
	@Override
	public boolean updateClientBase(ClientBase clientBase, User user,
			String isChange) {

		// 效验是否能修改此条客户群
		String status = clientBaseService.searchClientBaseById(clientBase
				.getId());
		ClientBaseValidator.checkModiftyAuthority(
				clientBaseService.searchAuthorityId(clientBase.getId()),
				status, user);
		// 查询客户群名称是否重复
		boolean flag = checkClientBaseName(clientBase);
		// 验证新增条件必填是否为空，客户群名称是否重复
		ClientBaseValidator.checkAddCilentBase(clientBase, flag);
		// 设置修改人
		clientBase.setModifyUser(user.getEmpCode().getId());
		// 设置修改的状态
		clientBase
				.setClientBaseStatus(MarketActivityConstance.CLIENTBASE_STATUS_UNUSED);
		// 设置修改时间
		clientBase.setModifyDate(new Date());
		// 更新客户群主表,如果主表更新成功则操作子表
		if (clientBaseService.updateClientBase(clientBase)) {
			// 封装更新参数
			String clientBaseId = clientBase.getId();
			// 更新部门线路
			List<LineDept> LineDept = clientBase.getLineDept();
			// 判断grid是否有修改
			if (MarketActivityConstance.CLIENTBASE_ISMODIFY.equals(isChange)) {
				// 删除部门线路
				clientBaseService.deleteLineDeptById(clientBaseId,
						MarketActivityConstance.ACTIVITY_RELATION_CLIENT);
				// 增加新的线路部门信息
				if (null != LineDept && 0 < LineDept.size()) {
					for (LineDept l : LineDept) {
						// 设置业务类型
						l.setConditionType(MarketActivityConstance.ACTIVITY_RELATION_CLIENT);
						// 设置业务表ID
						l.setConditionId(clientBase.getId());
						// 设置修改人
						l.setModifyUser(clientBase.getModifyUser());
						// 设置修改时间
						l.setModifyDate(new Date());
						clientBaseService.addLineDept(l);

					}
				}
			}
			// 封装多选信息
			List<Multiple> multiple = pottingMultiple(clientBase);
			// 删除客户群多选信息
			clientBaseService.deleteMultipleById(clientBaseId,
					MarketActivityConstance.ACTIVITY_RELATION_CLIENT);
			// 增加新的客户群多选信息
			if (null != multiple && 0 < multiple.size()) {
				for (Multiple m : multiple) {

					clientBaseService.addClientMultiple(m);
				}
			}

			return true;
		}
		return false;
	}

	/**
	 * 
	 * 
	 * <p>
	 * 更新客户群状态<br/>
	 * </p>
	 * 
	 * @author lvchongxin
	 * @version 0.1 2014-3-17
	 * @param clientBase
	 * @return boolean
	 */
	public boolean updateClientBaseStatus(ClientBase clientBase) {

		return clientBaseService.updateClientBaseStatus(clientBase);
	};

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
	public List<Detail> searchSecondTradesByList(List<String> trades) {
		if (null != trades && 0 < trades.size()) {
			// 验证是否失效并筛选失效期一个月内的二级行业
			return ClientBaseUtils.choiceSecondTreade(clientBaseService
					.searchSecondTradesByList(trades));

		}
		return null;
	};

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
	@Override
	public synchronized boolean checkClientBaseName(ClientBase clientBase) {
		// 排除已作废的客户群
		clientBase
				.setClientBaseStatus(MarketActivityConstance.CLIENTBASE_STATUS_CANCELLATION);
		// 客户群名称去空格
		String clientBaseName = clientBase.getClientBaseName().trim();
		clientBase.setClientBaseName(clientBaseName);
		return clientBaseService.searchClientBaseName(clientBase);
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
	public List<String> searchSecondTradesToString(String conditionId,
			String conditionType) {
		
		return clientBaseService.searchSecondTradesToString(conditionId, conditionType);
	}
	/**
	 * 
	 * 
	 * <p>
	 * 客户群多选项封装<br/>
	 * </p>
	 * 
	 * @author lvchongxin
	 * @version 0.1 2014-3-6
	 * @param clientBase
	 * @return List<Multiple>
	 */

	public List<Multiple> pottingMultiple(ClientBase clientBase) {

		// 一级行业
		List<Multiple> multiples = MarketActivityUtils
				.convertArrayToMultipleList(clientBase.getTrades(),
						MarketActivityConstance.ACTIVITY_RELATION_CLIENT,
						clientBase.getId(), MarketActivityConstance.TYPE_TRADE);
		// 二级行业
		if (null != clientBase.getSecondTrades()) {
			multiples.addAll(MarketActivityUtils.convertArrayToMultipleList(
					clientBase.getSecondTrades(),
					MarketActivityConstance.ACTIVITY_RELATION_CLIENT,
					clientBase.getId(),
					MarketActivityConstance.TYPE_SECOND_TRADE));
		}
		// 客户类型
		if (null != clientBase.getClientTypes()) {
			multiples
					.addAll(MarketActivityUtils.convertArrayToMultipleList(
							clientBase.getClientTypes(),
							MarketActivityConstance.ACTIVITY_RELATION_CLIENT,
							clientBase.getId(),
							MarketActivityConstance.TYPE_CUST_TYPE));
		}
		// 货量潜力
		if (null != clientBase.getClientlatents()) {
			multiples.addAll(MarketActivityUtils.convertArrayToMultipleList(
					clientBase.getClientlatents(),
					MarketActivityConstance.ACTIVITY_RELATION_CLIENT,
					clientBase.getId(),
					MarketActivityConstance.TYPE_CUST_POTENTIAL));
		}
		// 客户等级
		if (null != clientBase.getClientGrades()) {
			multiples
					.addAll(MarketActivityUtils.convertArrayToMultipleList(
							clientBase.getClientGrades(),
							MarketActivityConstance.ACTIVITY_RELATION_CLIENT,
							clientBase.getId(),
							MarketActivityConstance.TYPE_CUST_GRADE));
		}
		// 客户来源
		if (null != clientBase.getClientSources()) {
			multiples.addAll(MarketActivityUtils.convertArrayToMultipleList(
					clientBase.getClientSources(),
					MarketActivityConstance.ACTIVITY_RELATION_CLIENT,
					clientBase.getId(),
					MarketActivityConstance.TYPE_CUST_SOURCE));
		}
		// 产品类型
		if (null != clientBase.getProductTypes()) {
			multiples.addAll(MarketActivityUtils.convertArrayToMultipleList(
					clientBase.getProductTypes(),
					MarketActivityConstance.ACTIVITY_RELATION_CLIENT,
					clientBase.getId(),
					MarketActivityConstance.TYPE_PRODUCT_TYPE));
		}
		// 客户属性
		if (null != clientBase.getClientPropertys()) {
			multiples.addAll(MarketActivityUtils.convertArrayToMultipleList(
					clientBase.getClientPropertys(),
					MarketActivityConstance.ACTIVITY_RELATION_CLIENT,
					clientBase.getId(),
					MarketActivityConstance.TYPE_CUST_CATEGORY));
		}
		// 提货方式
		if (null != clientBase.getTakeMethods()) {
			multiples.addAll(MarketActivityUtils.convertArrayToMultipleList(
					clientBase.getTakeMethods(),
					MarketActivityConstance.ACTIVITY_RELATION_CLIENT,
					clientBase.getId(),
					MarketActivityConstance.TYPE_DELIVERY_MODE));
		}
		// 合作意向
		if (null != clientBase.getCooperateWills()) {
			multiples.addAll(MarketActivityUtils.convertArrayToMultipleList(
					clientBase.getCooperateWills(),
					MarketActivityConstance.ACTIVITY_RELATION_CLIENT,
					clientBase.getId(),
					MarketActivityConstance.TYPE_COOPIN_TENTION));
		}
		return multiples;
	}



}
