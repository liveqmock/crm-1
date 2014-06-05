package com.deppon.crm.module.marketing.shared.domain.activity;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.deppon.foss.framework.entity.BaseEntity;


/**
 * @author lvchongxin
 *客户群表
 */
public class ClientBase extends BaseEntity {
	//客户群名称
	private String clientBaseName;
	//客户创建起始时间
	private Date clientCreateStartTime;
	//客户创建结束时间
	private Date clientCreateEndTime;
	//归属部门ID
	private String clientDeptId;
	//归属部门name
	private String clientDeptName;
	//归属部门负责人
	private String principalId;
	//创建用户name
	private String createUserId;
	//当前登录人部门ID
	private String currentDeptId;
	//线路类型
	private String lineType;
	//客户群状态
	private String clientBaseStatus;
	//客户群表编码
	private String clientBaseCode=MarketActivityConstance.ACTIVITY_RELATION_CLIENT;	
	//一级行业
	private String[] trades;
	//一级行业编码
	private String tradesCode=MarketActivityConstance.TYPE_TRADE;
	//二级行业
	private String[] secondTrades;
	//二级行业编码
	private String secondTradesCode=MarketActivityConstance.TYPE_SECOND_TRADE;
	
	//客户类型
	private String[]  clientTypes;
	//客户类型编码
	private String clientTypesCode=MarketActivityConstance.TYPE_CUST_TYPE;
	
	//货量潜力
	private String[] clientlatents;
	//货量潜力编码
	private String clientlatentsCode=MarketActivityConstance.TYPE_CUST_POTENTIAL;
	
	//客户等级
	private String[] clientGrades;
	//客户等级编码
	private String clientGradesCode=MarketActivityConstance.TYPE_CUST_GRADE;

	
	//客户来源
	private String[] clientSources;
	//客户来源编码
	private String clientSourcesCode=MarketActivityConstance.TYPE_CUST_SOURCE;

	
	//产品类型
	private String[] productTypes;
	//产品类型编码
	private String productTypesCode=MarketActivityConstance.TYPE_PRODUCT_TYPE;

	
	//客户属性
	private String[] clientPropertys;
	//客户属性编码
	private String clientPropertysCode=MarketActivityConstance.TYPE_CUST_CATEGORY;

	
	//提货方式
	private String[] takeMethods;
	//提货方式编码
	private String takeMethodsCode=MarketActivityConstance.TYPE_DELIVERY_MODE;

	
	//合作意向
	private String[] cooperateWills;
	//合作意向编码
	private String cooperateWillsCode=MarketActivityConstance.TYPE_COOPIN_TENTION;

	
	//客户群线路表
	private List<LineDept> lineDept;
	
	//市场推广活动ID
	private String activityId;
	
	//部门性质
	private String deptCharacter;

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getClientBaseName() {
		return clientBaseName;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param clientBaseName the clientBaseName to set
	 */
	public void setClientBaseName(String clientBaseName) {
		this.clientBaseName = clientBaseName;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public Date getClientCreateStartTime() {
		return clientCreateStartTime;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param clientCreateStartTime the clientCreateStartTime to set
	 */
	public void setClientCreateStartTime(Date clientCreateStartTime) {
		this.clientCreateStartTime = clientCreateStartTime;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public Date getClientCreateEndTime() {
		return clientCreateEndTime;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param clientCreateEndTime the clientCreateEndTime to set
	 */
	public void setClientCreateEndTime(Date clientCreateEndTime) {
		this.clientCreateEndTime = clientCreateEndTime;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getClientDeptId() {
		return clientDeptId;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param clientDeptId the clientDeptId to set
	 */
	public void setClientDeptId(String clientDeptId) {
		this.clientDeptId = clientDeptId;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getClientDeptName() {
		return clientDeptName;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param clientDeptName the clientDeptName to set
	 */
	public void setClientDeptName(String clientDeptName) {
		this.clientDeptName = clientDeptName;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getPrincipalId() {
		return principalId;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param principalId the principalId to set
	 */
	public void setPrincipalId(String principalId) {
		this.principalId = principalId;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param createUserId the createUserId to set
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getLineType() {
		return lineType;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param lineType the lineType to set
	 */
	public void setLineType(String lineType) {
		this.lineType = lineType;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getClientBaseStatus() {
		return clientBaseStatus;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param clientBaseStatus the clientBaseStatus to set
	 */
	public void setClientBaseStatus(String clientBaseStatus) {
		this.clientBaseStatus = clientBaseStatus;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getClientBaseCode() {
		return clientBaseCode;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param clientBaseCode the clientBaseCode to set
	 */
	public void setClientBaseCode(String clientBaseCode) {
		this.clientBaseCode = clientBaseCode;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String[] getTrades() {
		return trades;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param trades the trades to set
	 */
	public void setTrades(String[] trades) {
		this.trades = trades;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getTradesCode() {
		return tradesCode;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param tradesCode the tradesCode to set
	 */
	public void setTradesCode(String tradesCode) {
		this.tradesCode = tradesCode;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String[] getSecondTrades() {
		return secondTrades;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param secondTrades the secondTrades to set
	 */
	public void setSecondTrades(String[] secondTrades) {
		this.secondTrades = secondTrades;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getSecondTradesCode() {
		return secondTradesCode;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param secondTradesCode the secondTradesCode to set
	 */
	public void setSecondTradesCode(String secondTradesCode) {
		this.secondTradesCode = secondTradesCode;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String[] getClientTypes() {
		return clientTypes;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param clientTypes the clientTypes to set
	 */
	public void setClientTypes(String[] clientTypes) {
		this.clientTypes = clientTypes;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getClientTypesCode() {
		return clientTypesCode;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param clientTypesCode the clientTypesCode to set
	 */
	public void setClientTypesCode(String clientTypesCode) {
		this.clientTypesCode = clientTypesCode;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String[] getClientlatents() {
		return clientlatents;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param clientlatents the clientlatents to set
	 */
	public void setClientlatents(String[] clientlatents) {
		this.clientlatents = clientlatents;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getClientlatentsCode() {
		return clientlatentsCode;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param clientlatentsCode the clientlatentsCode to set
	 */
	public void setClientlatentsCode(String clientlatentsCode) {
		this.clientlatentsCode = clientlatentsCode;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String[] getClientGrades() {
		return clientGrades;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param clientGrades the clientGrades to set
	 */
	public void setClientGrades(String[] clientGrades) {
		this.clientGrades = clientGrades;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getClientGradesCode() {
		return clientGradesCode;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param clientGradesCode the clientGradesCode to set
	 */
	public void setClientGradesCode(String clientGradesCode) {
		this.clientGradesCode = clientGradesCode;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String[] getClientSources() {
		return clientSources;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param clientSources the clientSources to set
	 */
	public void setClientSources(String[] clientSources) {
		this.clientSources = clientSources;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getClientSourcesCode() {
		return clientSourcesCode;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param clientSourcesCode the clientSourcesCode to set
	 */
	public void setClientSourcesCode(String clientSourcesCode) {
		this.clientSourcesCode = clientSourcesCode;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String[] getProductTypes() {
		return productTypes;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param productTypes the productTypes to set
	 */
	public void setProductTypes(String[] productTypes) {
		this.productTypes = productTypes;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getProductTypesCode() {
		return productTypesCode;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param productTypesCode the productTypesCode to set
	 */
	public void setProductTypesCode(String productTypesCode) {
		this.productTypesCode = productTypesCode;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String[] getClientPropertys() {
		return clientPropertys;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param clientPropertys the clientPropertys to set
	 */
	public void setClientPropertys(String[] clientPropertys) {
		this.clientPropertys = clientPropertys;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getClientPropertysCode() {
		return clientPropertysCode;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param clientPropertysCode the clientPropertysCode to set
	 */
	public void setClientPropertysCode(String clientPropertysCode) {
		this.clientPropertysCode = clientPropertysCode;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String[] getTakeMethods() {
		return takeMethods;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param takeMethods the takeMethods to set
	 */
	public void setTakeMethods(String[] takeMethods) {
		this.takeMethods = takeMethods;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getTakeMethodsCode() {
		return takeMethodsCode;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param takeMethodsCode the takeMethodsCode to set
	 */
	public void setTakeMethodsCode(String takeMethodsCode) {
		this.takeMethodsCode = takeMethodsCode;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String[] getCooperateWills() {
		return cooperateWills;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param cooperateWills the cooperateWills to set
	 */
	public void setCooperateWills(String[] cooperateWills) {
		this.cooperateWills = cooperateWills;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getCooperateWillsCode() {
		return cooperateWillsCode;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param cooperateWillsCode the cooperateWillsCode to set
	 */
	public void setCooperateWillsCode(String cooperateWillsCode) {
		this.cooperateWillsCode = cooperateWillsCode;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public List<LineDept> getLineDept() {
		return lineDept;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param lineDept the lineDept to set
	 */
	public void setLineDept(List<LineDept> lineDept) {
		this.lineDept = lineDept;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getActivityId() {
		return activityId;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param activityId the activityId to set
	 */
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getDeptCharacter() {
		return deptCharacter;
	}

	public void setDeptCharacter(String deptCharacter) {
		this.deptCharacter = deptCharacter;
	}

	public String getCurrentDeptId() {
		return currentDeptId;
	}

	public void setCurrentDeptId(String currentDeptId) {
		this.currentDeptId = currentDeptId;
	}
	
	
}
