package com.deppon.crm.module.marketing.shared.domain;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;
/**   
 * <p>
 * Description:营销效果评估客户维护表<br />
 * </p>
 * @title CustMatAssess.java
 * @package com.deppon.crm.module.marketing.shared.domain
 * @author ZhouYuan
 * @version 2013-01-17
 */
public class CustMatAssess extends BaseEntity{
	//部门名称
	private String deptName;
	//部门ID
	private String deptId;
	//上级部门ID
	private String parentId;
	//上级部门名称
	private String parentName;
	//经营本部名称
	private String managerDeptName;
	//事业部名称
	private String orgDeptName;
	//大区名称
	private String bigDeptName;
	//小区名称
	private String smaDeptName;
	//营业部名字
	private String salDeptName;
	//上月投诉理赔客户数
	private String recompenseCustNum;
	//上月零担投诉理赔客户数
	private String lttCompCustNum;
	//上月快递投诉理赔客户数
	private String expCompCustNum;
	//本月回访数
	private String nowVisitNum;
	//本月零担回访数
	private String lttVisitNum;
	//本月快递回访数
	private String expVisitNum;
	//本月回访率
	private String nowVisitRate;
	//本月零担回访率
	private String lttVisitRate;
	//本月快递回访率
	private String expVisitRate;
	//流失客户
	private String lostCustNum;
	//流失率
	private String lostCustRate;
	//计划回访率
	private String planVisitRate;
	//流失回访率
	private String lostVisitRate;
	// 流失客户回访数
	private String lostCustVisitNum;
	//挽回客户数
	private String saveCustNum;
	//挽回客户率
	private String saveCustRate;
	//上上月固定客户发货数
	private String memberDeleviryNum;
	//经营本部id
	private String managerDeptId;
	//事业部id
	private String orgDeptId;
	//大区id
	private String bigDeptId;
	//小区id
	private String smaDeptId;
	//营业部id
	private String salDeptId;	
	//创建时间
	private Date createTime;
	//查询截止时间
	private Date queryTime;
	//查询截止日期
	private String queryTimeStr;
	
	
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getManagerDeptName() {
		return managerDeptName;
	}
	public void setManagerDeptName(String managerDeptName) {
		this.managerDeptName = managerDeptName;
	}
	public String getOrgDeptName() {
		return orgDeptName;
	}
	public void setOrgDeptName(String orgDeptName) {
		this.orgDeptName = orgDeptName;
	}
	public String getBigDeptName() {
		return bigDeptName;
	}
	public void setBigDeptName(String bigDeptName) {
		this.bigDeptName = bigDeptName;
	}
	public String getSmaDeptName() {
		return smaDeptName;
	}
	public void setSmaDeptName(String smaDeptName) {
		this.smaDeptName = smaDeptName;
	}
	public String getSalDeptName() {
		return salDeptName;
	}
	public void setSalDeptName(String salDeptName) {
		this.salDeptName = salDeptName;
	}
	public String getRecompenseCustNum() {
		return recompenseCustNum;
	}
	public void setRecompenseCustNum(String recompenseCustNum) {
		this.recompenseCustNum = recompenseCustNum;
	}
	public String getLttCompCustNum() {
		return lttCompCustNum;
	}
	public void setLttCompCustNum(String lttCompCustNum) {
		this.lttCompCustNum = lttCompCustNum;
	}
	public String getExpCompCustNum() {
		return expCompCustNum;
	}
	public void setExpCompCustNum(String expCompCustNum) {
		this.expCompCustNum = expCompCustNum;
	}
	public String getNowVisitNum() {
		return nowVisitNum;
	}
	public void setNowVisitNum(String nowVisitNum) {
		this.nowVisitNum = nowVisitNum;
	}
	public String getLttVisitNum() {
		return lttVisitNum;
	}
	public void setLttVisitNum(String lttVisitNum) {
		this.lttVisitNum = lttVisitNum;
	}
	public String getExpVisitNum() {
		return expVisitNum;
	}
	public void setExpVisitNum(String expVisitNum) {
		this.expVisitNum = expVisitNum;
	}
	public String getNowVisitRate() {
		return nowVisitRate;
	}
	public void setNowVisitRate(String nowVisitRate) {
		this.nowVisitRate = nowVisitRate;
	}
	public String getLttVisitRate() {
		return lttVisitRate;
	}
	public void setLttVisitRate(String lttVisitRate) {
		this.lttVisitRate = lttVisitRate;
	}
	public String getExpVisitRate() {
		return expVisitRate;
	}
	public void setExpVisitRate(String expVisitRate) {
		this.expVisitRate = expVisitRate;
	}
	public String getLostCustNum() {
		return lostCustNum;
	}
	public void setLostCustNum(String lostCustNum) {
		this.lostCustNum = lostCustNum;
	}
	public String getLostCustRate() {
		return lostCustRate;
	}
	public void setLostCustRate(String lostCustRate) {
		this.lostCustRate = lostCustRate;
	}
	public String getPlanVisitRate() {
		return planVisitRate;
	}
	public void setPlanVisitRate(String planVisitRate) {
		this.planVisitRate = planVisitRate;
	}
	public String getLostVisitRate() {
		return lostVisitRate;
	}
	public void setLostVisitRate(String lostVisitRate) {
		this.lostVisitRate = lostVisitRate;
	}
	public String getLostCustVisitNum() {
		return lostCustVisitNum;
	}
	public void setLostCustVisitNum(String lostCustVisitNum) {
		this.lostCustVisitNum = lostCustVisitNum;
	}
	public String getSaveCustNum() {
		return saveCustNum;
	}
	public void setSaveCustNum(String saveCustNum) {
		this.saveCustNum = saveCustNum;
	}
	public String getSaveCustRate() {
		return saveCustRate;
	}
	public void setSaveCustRate(String saveCustRate) {
		this.saveCustRate = saveCustRate;
	}
	public String getMemberDeleviryNum() {
		return memberDeleviryNum;
	}
	public void setMemberDeleviryNum(String memberDeleviryNum) {
		this.memberDeleviryNum = memberDeleviryNum;
	}
	public String getManagerDeptId() {
		return managerDeptId;
	}
	public void setManagerDeptId(String managerDeptId) {
		this.managerDeptId = managerDeptId;
	}
	public String getOrgDeptId() {
		return orgDeptId;
	}
	public void setOrgDeptId(String orgDeptId) {
		this.orgDeptId = orgDeptId;
	}
	public String getBigDeptId() {
		return bigDeptId;
	}
	public void setBigDeptId(String bigDeptId) {
		this.bigDeptId = bigDeptId;
	}
	public String getSmaDeptId() {
		return smaDeptId;
	}
	public void setSmaDeptId(String smaDeptId) {
		this.smaDeptId = smaDeptId;
	}
	public String getSalDeptId() {
		return salDeptId;
	}
	public void setSalDeptId(String salDeptId) {
		this.salDeptId = salDeptId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getQueryTime() {
		return queryTime;
	}
	public void setQueryTime(Date queryTime) {
		this.queryTime = queryTime;
	}
	public String getQueryTimeStr() {
		return queryTimeStr;
	}
	public void setQueryTimeStr(String queryTimeStr) {
		this.queryTimeStr = queryTimeStr;
	}
	
	
	
	
}
