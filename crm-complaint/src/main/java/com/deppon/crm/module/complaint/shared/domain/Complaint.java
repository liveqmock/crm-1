package com.deppon.crm.module.complaint.shared.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Complaint {
    private BigDecimal fid;
    /**
     * 是否为同单号投诉(只适合异常)
     */
    private Integer sameBill;
    /**
     * 创建时间
     */
    private Date createtime;
    /**
     * 创建人
     */
    private BigDecimal createuserid;
    /**
     * 最后修改时间
     */
    private Date lastupdatetime;
    /**
     * 最后修改人
     */
    private BigDecimal lastmodifyuserid;
   /**
    * 单号，即 凭证号
    */
    private String bill;
   /**
    * 处理编号
    */
    private String dealbill;
   /**
    * 重复工单绑定码,即重复投拆绑定
    */
    private String rebindno;
//重复投诉码
    private String recomcode;
	// 上报类型
	/**
	 * r 为投诉 e 为异常
	 */
	private String reporttype;
	// 业务模式 快递 EXPRESS 零担UNEXPRESS
	private String businessModel;
	// 投诉客户ID
	private BigDecimal complainid;
	// 投诉客户
	private String complaincust;
	// 投诉人ID
	private BigDecimal compmanid;
	// 来电人
	private String compman;
	// 客户等级
	private String custlevel;
	// 客户类型
	private String custtype;
	// 相关客户ID
	private BigDecimal relatcusid;
	// 相关客户
	private String relatcus;
	// 相关客户等级
	private String relatcuslevel;
	// 相关客户类型
	private String relatcustype;
	// 期望时限8
	private BigDecimal timelimit;
	// 期望时限周期
	private String tilimitcycle;
	// 最终反馈
	private String feedback;
	// 上报人ID
	private BigDecimal reporterid;
	// 上报人
	private String reporter;
	// 上报时间
	private Date reporttime;
	// 上报内容
	private String reportcontent;
	// 客户要求
	private String custrequire;
	// 投诉方式
	private String requireway;
	// 优先级别
	private String pririty;
	// 联系电话
	private String tel;
	// 处理状态
	private String prostatus;
	// 解决状态
	private String dealstatus;
	// 退回人ID
	private BigDecimal returnid;
	// 退回人
	private String returnman;
	// 退回时间
	private Date returntime;
	// 调查建议(非持久化字段，只用于传值，调查建议会被保存在WorkOrder的suggestion中)
	private String recommendation;
	// 反馈记录
	private String feedbackrecode;
	// 退回原因

	private String feedbackreason;
	// 投诉（异常）范围ID
	private BigDecimal compabnormalid;
	// 投诉类型ID
	private BigDecimal comptypeid;
	// 投诉级别
	private String complevel;
	// 提交审批人ID
	private BigDecimal comapproverid;
	// 提交审批人
	private String comapprover;
	// 提交审批时间
	private Date comapprovertime;
	// 申请延时
	private BigDecimal approdelay;
	// 客户满意度
	private String cussatisfaction;
	// 操作人ID
	private BigDecimal operatoerid;
	// 操作人名称
	private String operatoername;
	// 业务项
	private BigDecimal parabasilevelid;
	// 业务范围
	private BigDecimal basiclevelid;

    //反馈部门名称
    private String recordpartmentname;
    //反馈时间
    private Date recordTime;
    //处理记录
    private List<WorkOrder> workOrderlist;
    /**
     * 回访新加的字段
     */
    //是否超时
    private String ifovertime;
    //是否到期
    private String iflimittime;
    //处理人
    private String dealman;
    //处理时间
    private Date dealtime;
    /**
     * 工单来源
     */
    private String complaintsource;
    //处理时限
    private BigDecimal processltimeimit;
    //处理结果
    private String results;
    //反馈部门名称
    private String deptname;
    //反馈时限
    private BigDecimal feedtimelimit;
    
    private BasciLevel basicLevel;
    //锁定用户Id（employee id不是userid）
    private BigDecimal lockingUserId;
    //锁定时间
    private Date lockingTime;
    //新加处理人id（回访查询）
    private Integer dealmanid;
    
    //新加字段  发货人，收货人
    private String  sendorreceive;
    
    //当前的延时申请数目
    private String postponeCount;
    
    //当前被打回的部门个数
    private String returnBackCount;
    
    //锁定次数
    private Integer numberOfLock;
    /**
     * 新基础资料新增字段
     */
    //业务项名称
    private String busItemName;
    //业务范围名称
    private String busScopeName;
    //投诉环节
    private String complainLink;
    //业务类型名称
    private String busTypeName;
    //业务类型ID
    private String busTypeId;
    //出发部门
    private String leaveDept;
    //到达部门
    private String leaveDeptName;
    //业务场景 Id
    private String busSceneId;
    //业务场景名称
    private String busSceneName;
    //场景原因 Id
    private String sceneRessonId;
    //场景原因名称
    private String sceneRessonName;
    //服务编码，CC 传过来的
    private String serviceId;
    //
    private String mainFid;
    //CC来源
    private String channel;
    /**
     * 责任ID（为了将工单数据插入的责任表中）
     * 张斌
     * 2013-3-14
     */
    private String dutyId;
    
    private List<Result> resultList;
    // 重写equals方法
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj instanceof Complaint) {
			Complaint other = (Complaint) obj;
			if (this.fid != null && other.getFid() != null) {
				return other.getFid().equals(this.fid);
			}
		}
		return false;
	}

	/**
	 * set get方法
	 */
	// basicLevel get方法
	public BasciLevel getBasicLevel() {
		return basicLevel;
	}

	// basicLevel set方法
	public void setBasicLevel(BasciLevel basicLevel) {
		this.basicLevel = basicLevel;
	}

	// resultList get方法
	public List<Result> getResultList() {
		return resultList;
	}

	// resultList set方法
	public void setResultList(List<Result> resultList) {
		this.resultList = resultList;
	}

	// fid get方法
	public BigDecimal getFid() {
		return fid;
	}

	// fid set方法
	public void setFid(BigDecimal fid) {
		this.fid = fid;
	}

	// createtime get方法
	public Date getCreatetime() {
		return createtime;
	}

	// createtime set方法
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	// createuserid get方法
	public BigDecimal getCreateuserid() {
		return createuserid;
	}

	// createuserid set方法
	public void setCreateuserid(BigDecimal createuserid) {
		this.createuserid = createuserid;
	}

	// lastupdatetime get方法
	public Date getLastupdatetime() {
		return lastupdatetime;
	}

	// lastupdatetime set方法
	public void setLastupdatetime(Date lastupdatetime) {
		this.lastupdatetime = lastupdatetime;
	}

	// lastmodifyuserid get方法
	public BigDecimal getLastmodifyuserid() {
		return lastmodifyuserid;
	}

	// lastmodifyuserid set方法
	public void setLastmodifyuserid(BigDecimal lastmodifyuserid) {
		this.lastmodifyuserid = lastmodifyuserid;
	}

	// bill get方法
	public String getBill() {
		return bill;
	}

	// bill set方法
	public void setBill(String bill) {
		this.bill = bill;
	}

	// dealbill get方法
	public String getDealbill() {
		return dealbill;
	}

	// dealbill set方法
	public void setDealbill(String dealbill) {
		this.dealbill = dealbill;
	}

	// rebindno get方法
	public String getRebindno() {
		return rebindno;
	}

	// rebindno set方法
	public void setRebindno(String rebindno) {
		this.rebindno = rebindno;
	}

	// recomcode get方法
	public String getRecomcode() {
		return recomcode;
	}

	// recomcode set方法
	public void setRecomcode(String recomcode) {
		this.recomcode = recomcode;
	}

	// reporttype get方法
	public String getReporttype() {
		return reporttype;
	}

	// reporttype set方法
	public void setReporttype(String reporttype) {
		this.reporttype = reporttype;
	}

	// complainid get方法
	public BigDecimal getComplainid() {
		return complainid;
	}

	// complainid set方法
	public void setComplainid(BigDecimal complainid) {
		this.complainid = complainid;
	}

	// complaincust get方法
	public String getComplaincust() {
		return complaincust;
	}

	// complaincust set方法
	public void setComplaincust(String complaincust) {
		this.complaincust = complaincust;
	}

	// compmanid get方法
	public BigDecimal getCompmanid() {
		return compmanid;
	}

	// compmanid set方法
	public void setCompmanid(BigDecimal compmanid) {
		this.compmanid = compmanid;
	}

	// compman get方法
	public String getCompman() {
		return compman;
	}

	// compman set方法
	public void setCompman(String compman) {
		this.compman = compman;
	}

	// custlevel get方法
	public String getCustlevel() {
		return custlevel;
	}

	// custlevel set方法
	public void setCustlevel(String custlevel) {
		this.custlevel = custlevel;
	}

	// custtype get方法
	public String getCusttype() {
		return custtype;
	}

	// custtype set方法
	public void setCusttype(String custtype) {
		this.custtype = custtype;
	}

	// relatcusid get方法
	public BigDecimal getRelatcusid() {
		return relatcusid;
	}

	// relatcusid set方法
	public void setRelatcusid(BigDecimal relatcusid) {
		this.relatcusid = relatcusid;
	}

	// relatcus get方法
	public String getRelatcus() {
		return relatcus;
	}

	// relatcus set方法
	public void setRelatcus(String relatcus) {
		this.relatcus = relatcus;
	}

	// timelimit get方法
	public BigDecimal getTimelimit() {
		return timelimit;
	}

	// timelimit set方法
	public void setTimelimit(BigDecimal timelimit) {
		this.timelimit = timelimit;
	}

	// tilimitcycle get方法
	public String getTilimitcycle() {
		return tilimitcycle;
	}

	// tilimitcycle set方法
	public void setTilimitcycle(String tilimitcycle) {
		this.tilimitcycle = tilimitcycle;
	}

	// feedback get方法
	public String getFeedback() {
		return feedback;
	}

	// feedback set方法
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	// reporterid get方法
	public BigDecimal getReporterid() {
		return reporterid;
	}

	// reporterid set方法
	public void setReporterid(BigDecimal reporterid) {
		this.reporterid = reporterid;
	}

	// reporter get方法
	public String getReporter() {
		return reporter;
	}

	// reporter set方法
	public void setReporter(String reporter) {
		this.reporter = reporter;
	}

	// reporttime get方法
	public Date getReporttime() {
		return reporttime;
	}

	// reporttime set方法
	public void setReporttime(Date reporttime) {
		this.reporttime = reporttime;
	}

	// reportcontent get方法
	public String getReportcontent() {
		return reportcontent;
	}

	// reportcontent set方法
	public void setReportcontent(String reportcontent) {
		this.reportcontent = reportcontent;
	}

	// custrequire get方法
	public String getCustrequire() {
		return custrequire;
	}

	// custrequire set方法
	public void setCustrequire(String custrequire) {
		this.custrequire = custrequire;
	}

	// requireway get方法
	public String getRequireway() {
		return requireway;
	}

	// requireway set方法
	public void setRequireway(String requireway) {
		this.requireway = requireway;
	}

	// pririty get方法
	public String getPririty() {
		return pririty;
	}

	// pririty set方法
	public void setPririty(String pririty) {
		this.pririty = pririty;
	}

	// tel get方法
	public String getTel() {
		return tel;
	}

	// tel set方法
	public void setTel(String tel) {
		this.tel = tel;
	}

	// prostatus get方法
	public String getProstatus() {
		return prostatus;
	}

	// prostatus set方法
	public void setProstatus(String prostatus) {
		this.prostatus = prostatus;
	}

	// dealstatus get方法
	public String getDealstatus() {
		return dealstatus;
	}

	// dealstatus set方法
	public void setDealstatus(String dealstatus) {
		this.dealstatus = dealstatus;
	}

	// returnid get方法
	public BigDecimal getReturnid() {
		return returnid;
	}

	// returnid set方法
	public void setReturnid(BigDecimal returnid) {
		this.returnid = returnid;
	}

	// returnman get方法
	public String getReturnman() {
		return returnman;
	}

	// returnman set方法
	public void setReturnman(String returnman) {
		this.returnman = returnman;
	}

	// returntime get方法
	public Date getReturntime() {
		return returntime;
	}

	// returntime set方法
	public void setReturntime(Date returntime) {
		this.returntime = returntime;
	}

	// recommendation get方法
	public String getRecommendation() {
		return recommendation;
	}

	// recommendation set方法
	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}

	// feedbackrecode get方法
	public String getFeedbackrecode() {
		return feedbackrecode;
	}

	// feedbackrecode set方法
	public void setFeedbackrecode(String feedbackrecode) {
		this.feedbackrecode = feedbackrecode;
	}

	// feedbackreason get方法
	public String getFeedbackreason() {
		return feedbackreason;
	}

	// feedbackreason set方法
	public void setFeedbackreason(String feedbackreason) {
		this.feedbackreason = feedbackreason;
	}

	// compabnormalid get方法
	public BigDecimal getCompabnormalid() {
		return compabnormalid;
	}

	// compabnormalid set方法
	public void setCompabnormalid(BigDecimal compabnormalid) {
		this.compabnormalid = compabnormalid;
	}

	// comptypeid get方法
	public BigDecimal getComptypeid() {
		return comptypeid;
	}

	// comptypeid set方法
	public void setComptypeid(BigDecimal comptypeid) {
		this.comptypeid = comptypeid;
	}

	// complevel get方法
	public String getComplevel() {
		return complevel;
	}

	// complevel set方法
	public void setComplevel(String complevel) {
		this.complevel = complevel;
	}

	// comapproverid get方法
	public BigDecimal getComapproverid() {
		return comapproverid;
	}

	// comapproverid set方法
	public void setComapproverid(BigDecimal comapproverid) {
		this.comapproverid = comapproverid;
	}

	// comapprover get方法
	public String getComapprover() {
		return comapprover;
	}

	// comapprover set方法
	public void setComapprover(String comapprover) {
		this.comapprover = comapprover;
	}

	// comapprovertime get方法
	public Date getComapprovertime() {
		return comapprovertime;
	}

	// comapprovertime set方法
	public void setComapprovertime(Date comapprovertime) {
		this.comapprovertime = comapprovertime;
	}

	// approdelay get方法
	public BigDecimal getApprodelay() {
		return approdelay;
	}

	// approdelay set方法
	public void setApprodelay(BigDecimal approdelay) {
		this.approdelay = approdelay;
	}

	// cussatisfaction get方法
	public String getCussatisfaction() {
		return cussatisfaction;
	}

	// cussatisfaction set方法
	public void setCussatisfaction(String cussatisfaction) {
		this.cussatisfaction = cussatisfaction;
	}

	// operatoerid get方法
	public BigDecimal getOperatoerid() {
		return operatoerid;
	}

	// operatoerid set方法
	public void setOperatoerid(BigDecimal operatoerid) {
		this.operatoerid = operatoerid;
	}

	// operatoername get方法
	public String getOperatoername() {
		return operatoername;
	}

	// operatoername set方法
	public void setOperatoername(String operatoername) {
		this.operatoername = operatoername;
	}

	// parabasilevelid get方法
	public BigDecimal getParabasilevelid() {
		return parabasilevelid;
	}

	// parabasilevelid set方法
	public void setParabasilevelid(BigDecimal parabasilevelid) {
		this.parabasilevelid = parabasilevelid;
	}

	// basiclevelid get方法
	public BigDecimal getBasiclevelid() {
		return basiclevelid;
	}

	// basiclevelid set方法
	public void setBasiclevelid(BigDecimal basiclevelid) {
		this.basiclevelid = basiclevelid;
	}

	// recordpartmentname get方法
	public String getRecordpartmentname() {
		return recordpartmentname;
	}

	// recordpartmentname set方法
	public void setRecordpartmentname(String recordpartmentname) {
		this.recordpartmentname = recordpartmentname;
	}

	// recordTime get方法
	public Date getRecordTime() {
		return recordTime;
	}

	// recordTime set方法
	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	// workOrderlist get方法
	public List<WorkOrder> getWorkOrderlist() {
		return workOrderlist;
	}

	// workOrderlist set方法
	public void setWorkOrderlist(List<WorkOrder> workOrderlist) {
		this.workOrderlist = workOrderlist;
	}

	// ifovertime get方法
	public String getIfovertime() {
		return ifovertime;
	}

	// ifovertime set方法
	public void setIfovertime(String ifovertime) {
		this.ifovertime = ifovertime;
	}

	// iflimittime get方法
	public String getIflimittime() {
		return iflimittime;
	}

	// iflimittime set方法
	public void setIflimittime(String iflimittime) {
		this.iflimittime = iflimittime;
	}

	// dealtime get方法
	public Date getDealtime() {
		return dealtime;
	}

	// dealtime set方法
	public void setDealtime(Date dealtime) {
		this.dealtime = dealtime;
	}

	// dealman get方法
	public String getDealman() {
		return dealman;
	}

	// dealman set方法
	public void setDealman(String dealman) {
		this.dealman = dealman;
	}

	// complaintsource get方法
	public String getComplaintsource() {
		return complaintsource;
	}

	// complaintsource set方法
	public void setComplaintsource(String complaintsource) {
		this.complaintsource = complaintsource;
	}

	// relatcuslevel get方法
	public String getRelatcuslevel() {
		return relatcuslevel;
	}

	// relatcuslevel set方法
	public void setRelatcuslevel(String relatcuslevel) {
		this.relatcuslevel = relatcuslevel;
	}

	// relatcustype get方法
	public String getRelatcustype() {
		return relatcustype;
	}

	// relatcustype set方法
	public void setRelatcustype(String relatcustype) {
		this.relatcustype = relatcustype;
	}

	// processltimeimit get方法
	public BigDecimal getProcessltimeimit() {
		return processltimeimit;
	}

	// processltimeimit set方法
	public void setProcessltimeimit(BigDecimal processltimeimit) {
		this.processltimeimit = processltimeimit;
	}

	// results get方法
	public String getResults() {
		return results;
	}

	// results set方法
	public void setResults(String results) {
		this.results = results;
	}

	// deptname get方法
	public String getDeptname() {
		return deptname;
	}

	// deptname set方法
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	// feedtimelimit get方法
	public BigDecimal getFeedtimelimit() {
		return feedtimelimit;
	}

	// feedtimelimit set方法
	public void setFeedtimelimit(BigDecimal feedtimelimit) {
		this.feedtimelimit = feedtimelimit;
	}

	// lockingUserId get方法
	public BigDecimal getLockingUserId() {
		return lockingUserId;
	}

	// lockingUserId set方法
	public void setLockingUserId(BigDecimal lockingUserId) {
		this.lockingUserId = lockingUserId;
	}

	// lockingTime get方法
	public Date getLockingTime() {
		return lockingTime;
	}

	// lockingTime set方法
	public void setLockingTime(Date lockingTime) {
		this.lockingTime = lockingTime;
	}

	// dealmanid get方法
	public Integer getDealmanid() {
		return dealmanid;
	}

	// dealmanid set方法
	public void setDealmanid(Integer dealmanid) {
		this.dealmanid = dealmanid;
	}

	// sameBill get方法
	public Integer getSameBill() {
		return sameBill;
	}

	// sameBill set方法
	public void setSameBill(Integer sameBill) {
		this.sameBill = sameBill;
	}

	// sendorreceive get方法
	public String getSendorreceive() {
		return sendorreceive;
	}

	// sendorreceive set方法
	public void setSendorreceive(String sendorreceive) {
		this.sendorreceive = sendorreceive;
	}

	// postponeCount get方法
	public String getPostponeCount() {
		return postponeCount;
	}

	// postponeCount set方法
	public void setPostponeCount(String postponeCount) {
		this.postponeCount = postponeCount;
	}

	// returnBackCount get方法
	public String getReturnBackCount() {
		return returnBackCount;
	}

	// returnBackCount set方法
	public void setReturnBackCount(String returnBackCount) {
		this.returnBackCount = returnBackCount;
	}

	// numberOfLock get方法
	public Integer getNumberOfLock() {
		return numberOfLock;
	}

	// numberOfLock set方法
	public void setNumberOfLock(Integer numberOfLock) {
		this.numberOfLock = numberOfLock;
	}

	/**
	 * @return the dutyId
	 */
	public String getDutyId() {
		return dutyId;
	}

	/**
	 * @param dutyId
	 *            the dutyId to set
	 */
	public void setDutyId(String dutyId) {
		this.dutyId = dutyId;
	}

	public String getBusItemName() {
		return busItemName;
	}

	public void setBusItemName(String busItemName) {
		this.busItemName = busItemName;
	}

	public String getBusScopeName() {
		return busScopeName;
	}

	public void setBusScopeName(String busScopeName) {
		this.busScopeName = busScopeName;
	}

	public String getComplainLink() {
		return complainLink;
	}

	public void setComplainLink(String complainLink) {
		this.complainLink = complainLink;
	}

	public String getBusTypeName() {
		return busTypeName;
	}

	public void setBusTypeName(String busTypeName) {
		this.busTypeName = busTypeName;
	}

	public String getBusTypeId() {
		return busTypeId;
	}

	public void setBusTypeId(String busTypeId) {
		this.busTypeId = busTypeId;
	}

	/**
	 * <p>
	 * Description:leaveDept<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-7-10
	 */
	public String getLeaveDept() {
		return leaveDept;
	}

	/**
	 * <p>
	 * Description:leaveDept<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-7-10
	 */
	public void setLeaveDept(String leaveDept) {
		this.leaveDept = leaveDept;
	}

	/**
	 * <p>
	 * Description:leaveDeptName<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-7-10
	 */
	public String getLeaveDeptName() {
		return leaveDeptName;
	}

	/**
	 * <p>
	 * Description:leaveDeptName<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-7-10
	 */
	public void setLeaveDeptName(String leaveDeptName) {
		this.leaveDeptName = leaveDeptName;
	}

	public String getBusinessModel() {
		return businessModel;
	}

	public void setBusinessModel(String businessModel) {
		this.businessModel = businessModel;
	}
	
	/**
	 * @return the busSceneId
	 */
	public String getBusSceneId() {
		return busSceneId;
	}
	
	/**
	 * @param busSceneId the busSceneId to set
	 */
	public void setBusSceneId(String busSceneId) {
		this.busSceneId = busSceneId;
	}
	/**
	 * @return the busSceneName
	 */
	public String getBusSceneName() {
		return busSceneName;
	}
	/**
	 * @param busSceneName the busSceneName to set
	 */
	public void setBusSceneName(String busSceneName) {
		this.busSceneName = busSceneName;
	}
	/**
	 * @return the sceneRessonId
	 */
	public String getSceneRessonId() {
		return sceneRessonId;
	}
	/**
	 * @param sceneRessonId the sceneRessonId to set
	 */
	public void setSceneRessonId(String sceneRessonId) {
		this.sceneRessonId = sceneRessonId;
	}
	/**
	 * @return the sceneRessonName
	 */
	public String getSceneRessonName() {
		return sceneRessonName;
	}
	/**
	 * @param sceneRessonName the sceneRessonName to set
	 */
	public void setSceneRessonName(String sceneRessonName) {
		this.sceneRessonName = sceneRessonName;
	}
	/**
	 * @return the serviceId
	 */
	public String getServiceId() {
		return serviceId;
	}
	/**
	 * @param serviceId the serviceId to set
	 */
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	/**
	 * @return the mainFid
	 */
	public String getMainFid() {
		return mainFid;
	}
	/**
	 * @param mainFid the mainFid to set
	 */
	public void setMainFid(String mainFid) {
		this.mainFid = mainFid;
	}
	/**
	 * @return the channel
	 */
	public String getChannel() {
		return channel;
	}
	/**
	 * @param channel the channel to set
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}
	
}