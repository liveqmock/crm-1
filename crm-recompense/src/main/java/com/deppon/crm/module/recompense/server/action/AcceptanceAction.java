/*
 * @author Rock
 */
package com.deppon.crm.module.recompense.server.action;

import java.util.List;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.recompense.server.manager.RecompenseManager;
import com.deppon.crm.module.recompense.shared.domain.OnlineApply;
import com.deppon.crm.module.recompense.shared.domain.UserRoleDeptRelation;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.message.IMessageBundle;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;

public class AcceptanceAction extends AbstractAction {
	//
	// -----------------只需要get的数据（JSON
	// 通过get方法将ACTION中的数据转换为JSON传给前台）---------------------------------------------
	private List<OnlineApply> onlineApplyList;
	// 总页数
	private Long totalCount;
	// BEAN获得国际化对象
	private IMessageBundle messageBundle; // IMessageBundle类名 messageBundle 实例化
	// manager对象
	private RecompenseManager recompenseManager;
	private String message; // 提示信息
	// 运单号
	private String waybillNum;
	// 分页
	private int limit;
	private int start;

	public List<OnlineApply> getOnlineApplyList() {
		return onlineApplyList;
	}

	private String refuseId; // 在线理赔拒绝

	private List<UserRoleDeptRelation> userRoleDeptRelationList = null;

	public List<UserRoleDeptRelation> getUserRoleDeptRelationList() {
		return userRoleDeptRelationList;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	// -----------------只需要set的数据(JSON
	// 通过set方法将前台传来的值设置到ACTION中)---------------------------------------------

	public void setMessageBundle(IMessageBundle messageBundle) {
		this.messageBundle = messageBundle;
	}

	public void setRecompenseManager(RecompenseManager recompenseManager) {
		this.recompenseManager = recompenseManager;
	}

	public void setRefuseId(String refuseId) { // 在线理赔拒绝
		this.refuseId = refuseId;
	}

	private String id;

	public void setId(String id) { // 在线理赔拒绝
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setWaybillNum(String waybillNum) {
		this.waybillNum = waybillNum;
	}

	private String deptId;

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	private String userId;

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public void setStart(int start) {
		this.start = start;
	}

	// ----------------------------------------set
	// -------------------------------------------
	public String searchOnlieReconpenseList() {
		onlineApplyList.add(new OnlineApply());
		return SUCCESS;
	}

	/**
	 * 
	 * @description 在线理赔申请根据运单号查询.
	 * @author 张斌
	 * @version 0.1 2012-4-20
	 * @param waybillNum
	 *            运单号
	 * @date 2012-4-20
	 * @return 在线理赔申请集合
	 * @update 2012-4-20
	 */
	@JSON
	public String searchOnlineApplyByWaybillNum() {
		User user = (User) UserContext.getCurrentUser();
		onlineApplyList = recompenseManager
				.searchOnlineApplyByCondition(waybillNum, user.getEmpCode()
						.getDeptId().getId(), start, limit);
		totalCount = recompenseManager.searchOnlineApplyCountByCondition(
				waybillNum, user.getEmpCode().getDeptId().getId());
		if (totalCount == 0) {
			totalCount = (long) 1;
		}
		return SUCCESS;
	}

	/**
	 * 
	 * <p>
	 * Description:拒绝理赔受理<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @return String
	 */
	@JSON
	public String refuseOnlineApply() { // 拒绝理赔受理
		User user=(User) UserContext.getCurrentUser();
		recompenseManager.refuseOnlineApply(refuseId,user);
		message = messageBundle.getMessage(getLocale(),
				"i18n.recompense.onlineApplyRefuseSuccess");
		return SUCCESS;
	}

	/**
	 * 
	 * <p>
	 * Description:大区点击查询<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @return String
	 */
	@JSON
	public String findUserRoleDeptRelationByUserId() { // 大区点击查询 不能用get
		userRoleDeptRelationList = recompenseManager
				.getUserRoleDeptRelationByUserId(userId);
		return SUCCESS;
	}

	/**
	 * 
	 * <p>
	 * Description:大区设置删除<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @return String
	 */
	@JSON
	public String deleteUserRoleDeptRelationById() { // 大区设置删除
		recompenseManager.deleteUserRoleDeptRelationById(id);
		message = messageBundle.getMessage(getLocale(),
				"i18n.recompense.areaDeleteDuccess");
		return SUCCESS;
	}

	/**
	 * 
	 * <p>
	 * Description:大区设置<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @return String
	 */
	@JSON
	public String insertUserRoleDepartment() { // 大区设置 userId roleId deptId
		recompenseManager.insertUserRoleDepartment(userId, null, deptId);
		return SUCCESS;
	}

	/**
	 * 
	 * <p>
	 * Description:查询大区<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @return String
	 */
	@JSON
	public String searchEmployeesByName() { // 查大区
		recompenseManager.getBigAreaList();
		return SUCCESS;
	}
}
