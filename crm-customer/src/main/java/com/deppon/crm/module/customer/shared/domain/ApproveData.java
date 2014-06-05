package com.deppon.crm.module.customer.shared.domain;

import java.io.Serializable;

/**
 * @作者：罗典
 * @时间：2012-3-23
 * @说明：审批数据
 * */
public class ApproveData implements Serializable{
	private String id;
	// 实体类名
	private String className;
	// 实体类ID
	private String classId;
	// 字段名
	private String fieldName;
	// 新值
	private String newValue;
	// 旧值
	private String oldValue;
	// 工作流id
	private String workFlowId;
	// 操作类型 1为新增，2为修改,3为删除，4为绑定（合同），5为归属部门变更（合同）
	private Integer handleType;
	//改变的对象的名称
	private String changerName;
	// 会员操作记录id
	private String memberOperationLogId;
	/**
	 * <p>
	 * Description:id<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getId() {
		return id;
	}
	/**
	 * <p>
	 * Description:id<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * <p>
	 * Description:className<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getClassName() {
		return className;
	}
	/**
	 * <p>
	 * Description:className<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setClassName(String className) {
		this.className = className;
	}
	/**
	 * <p>
	 * Description:classId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getClassId() {
		return classId;
	}
	/**
	 * <p>
	 * Description:classId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setClassId(String classId) {
		this.classId = classId;
	}
	/**
	 * <p>
	 * Description:fieldName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getFieldName() {
		return fieldName;
	}
	/**
	 * <p>
	 * Description:fieldName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	/**
	 * <p>
	 * Description:newValue<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getNewValue() {
		if(newValue == null){
			return "";
		}
		return newValue;
	}
	/**
	 * <p>
	 * Description:newValue<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}
	/**
	 * <p>
	 * Description:oldValue<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getOldValue() {
		return oldValue;
	}
	/**
	 * <p>
	 * Description:oldValue<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}
	/**
	 * <p>
	 * Description:workFlowId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getWorkFlowId() {
		return workFlowId;
	}
	/**
	 * <p>
	 * Description:workFlowId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setWorkFlowId(String workFlowId) {
		this.workFlowId = workFlowId;
	}
	/**
	 * <p>
	 * Description:handleType<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public Integer getHandleType() {
		return handleType;
	}
	/**
	 * <p>
	 * Description:handleType<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setHandleType(Integer handleType) {
		this.handleType = handleType;
	}
	/**
	 * <p>
	 * Description:changerName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getChangerName() {
		return changerName;
	}
	/**
	 * <p>
	 * Description:changerName<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setChangerName(String changerName) {
		this.changerName = changerName;
	}
	/**
	 * <p>
	 * Description:memberOperationLogId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getMemberOperationLogId() {
		return memberOperationLogId;
	}
	/**
	 * <p>
	 * Description:memberOperationLogId<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setMemberOperationLogId(String memberOperationLogId) {
		this.memberOperationLogId = memberOperationLogId;
	}
	
}
