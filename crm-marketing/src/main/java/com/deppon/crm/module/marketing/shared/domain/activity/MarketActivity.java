package com.deppon.crm.module.marketing.shared.domain.activity;

import java.util.Date;
import java.util.List;

import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.foss.framework.entity.BaseEntity;
/**   
 * <p>
 * Description:市场推广活动实体类<br />
 * </p>
 * @title MarketActivity.java
 * @package com.deppon.crm.module.marketing.shared.domain.activity 
 * @author ZhouYuan
 * @version 0.1 2014-03-05
 */
public class MarketActivity extends BaseEntity {


	/**
	 * 市场推广活动实体类
	 */
	private static final long serialVersionUID = 1L;
	//市场推广活动编码
	private String activityCode;
	//市场推广活动名称
	private String activityName;
	//活动类型
	private String activityType;
	//活动类别
	private String activityCategory;
	//申请人姓名
	private String proposerName;
	//申请人工号
	private String proposerCode;
	//活动开始时间
	private Date startTime;
	//结束时间
	private Date endTime;
	//活动主题
	private String topic;
	//活动内容
	private String content;
	//对外宣传语
	private String slogan;
	//申请事由
	private String applyReason;
	//第一个月目标
	private String target1;
	//第二个月目标
	private String target2;
	//第三个月目标
	private String target3;
	//区域营销活动工作流编码
	private String activityWorkflowNum;
	
	/**
	 * 申请物品信息
	 */
	//申请物品信息
	private List<ActivityOption> materialInfo;
	//申请物品数据字典
	private String materialCode = MarketActivityConstance.OPTION_APPLYGOODS;
	//对接人
	private String contactsId;
	//对接人姓名
	private String contactsName;
	//对接人联系电话
	private String contactsTel;
	/**
	 * 活动参与条件
	 */
	//客户一级行业行业
	private String[] custTrade;
	//客户一级行业Multiple实体
	private List<Multiple> custTradeMul;
	//客户行业数据字典
	private String custTradeCode = MarketActivityConstance.TYPE_TRADE ;
	//客户二级行业
	private String[] secondTrade;
	//客户二级行业Multiple实体
	private List<Multiple> secondTradeMul;
	//客户二级行业数据字典
	private String secondTradeCode = MarketActivityConstance.TYPE_SECOND_TRADE;
	//产品类型
	private String[] productType;
	//产品类型Multiple实体
	private List<Multiple> productTypeMul;
	//产品类型数据字典
	private String productTypeCode = MarketActivityConstance.TYPE_PRODUCT_TYPE;
	//开单品名
	private String itemNames;
	//开单金额起始值
	private String minBillAmt;
	//开单金额结束值
	private String maxBillAmt;
	//货物重量起始值
	private String minCargoWeight;
	//货物重量结束值
	private String maxCargoWeight;
	//货物体积起始值
	private String minCargoVolume;
	//货物体积结束值
	private String maxCargoVolume;
	//订单来源
	private String[] orderSource;
	//订单来源Multiple实体
	private List<Multiple> orderSourceMul;
	//订单来源数据字典 
	private String orderSourceCode = MarketActivityConstance.TYPE_ORDER_SOURCE;
	//开展部门
	private List<ActivityDept> activityDepts;
	//开展部门数量
	private String deptNum;
	//线路区域要求
	private String lineRequest;
	//线路区域列表
	private List<LineDept> lineDepts;
	/**
	 * 折扣优惠信息
	 */
	//优惠类型
	private String preferType;
	//折扣工作流号
	private String workFlowNum;
	//折扣列表
	private List<ActivityOption> discountInfo;
	//折扣优惠数据字典
	private String discountCode = MarketActivityConstance.OPTION_DISCOUNT;
	//优惠券列表
	private List<ActivityOption> couponInfo;
	//优惠券数据字典
	private String couponCode = MarketActivityConstance.OPTION_COUPON;
	//优惠券使用条件
	private String useRule;
	//优惠券生成条件
	private String createRule;
	/**
	 * 客户群列表
	 */
	private List<ActivityClientBase> clientBase;
	//上传文件列表
	private List<FileInfo> files;
	//文件类型
	private String fileSourceType = MarketActivityConstance.SOURCE_TYPE_ACTIVITY ;
	//市场推广活动状态
	private String activityStatus;
	//市场推广活动归属部门
	private String deptId;
	//市场推广活动数据字典
	private String activitySourceCode = MarketActivityConstance.ACTIVITY_RELATION_ACTIVITY;
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getActivityCode() {
		return activityCode;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param activityCode the activityCode to set
	 */
	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getActivityName() {
		return activityName;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param activityName the activityName to set
	 */
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getActivityType() {
		return activityType;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param activityType the activityType to set
	 */
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getActivityCategory() {
		return activityCategory;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param activityCategory the activityCategory to set
	 */
	public void setActivityCategory(String activityCategory) {
		this.activityCategory = activityCategory;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getProposerName() {
		return proposerName;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param proposerName the proposerName to set
	 */
	public void setProposerName(String proposerName) {
		this.proposerName = proposerName;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getProposerCode() {
		return proposerCode;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param proposerCode the proposerCode to set
	 */
	public void setProposerCode(String proposerCode) {
		this.proposerCode = proposerCode;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public Date getStartTime() {
		return startTime;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public Date getEndTime() {
		return endTime;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getTopic() {
		return topic;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param topic the topic to set
	 */
	public void setTopic(String topic) {
		this.topic = topic;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getContent() {
		return content;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getSlogan() {
		return slogan;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param slogan the slogan to set
	 */
	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getApplyReason() {
		return applyReason;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param applyReason the applyReason to set
	 */
	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getTarget1() {
		return target1;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param target1 the target1 to set
	 */
	public void setTarget1(String target1) {
		this.target1 = target1;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getTarget2() {
		return target2;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param target2 the target2 to set
	 */
	public void setTarget2(String target2) {
		this.target2 = target2;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getTarget3() {
		return target3;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param target3 the target3 to set
	 */
	public void setTarget3(String target3) {
		this.target3 = target3;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getActivityWorkflowNum() {
		return activityWorkflowNum;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param activityWorkflowNum the activityWorkflowNum to set
	 */
	public void setActivityWorkflowNum(String activityWorkflowNum) {
		this.activityWorkflowNum = activityWorkflowNum;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public List<ActivityOption> getMaterialInfo() {
		return materialInfo;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param materialInfo the materialInfo to set
	 */
	public void setMaterialInfo(List<ActivityOption> materialInfo) {
		this.materialInfo = materialInfo;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getMaterialCode() {
		return materialCode;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param materialCode the materialCode to set
	 */
	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getContactsId() {
		return contactsId;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param contactsId the contactsId to set
	 */
	public void setContactsId(String contactsId) {
		this.contactsId = contactsId;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getContactsName() {
		return contactsName;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param contactsName the contactsName to set
	 */
	public void setContactsName(String contactsName) {
		this.contactsName = contactsName;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getContactsTel() {
		return contactsTel;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param contactsTel the contactsTel to set
	 */
	public void setContactsTel(String contactsTel) {
		this.contactsTel = contactsTel;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String[] getCustTrade() {
		return custTrade;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param custTrade the custTrade to set
	 */
	public void setCustTrade(String[] custTrade) {
		this.custTrade = custTrade;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public List<Multiple> getCustTradeMul() {
		return custTradeMul;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param custTradeMul the custTradeMul to set
	 */
	public void setCustTradeMul(List<Multiple> custTradeMul) {
		this.custTradeMul = custTradeMul;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getCustTradeCode() {
		return custTradeCode;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param custTradeCode the custTradeCode to set
	 */
	public void setCustTradeCode(String custTradeCode) {
		this.custTradeCode = custTradeCode;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String[] getSecondTrade() {
		return secondTrade;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param secondTrade the secondTrade to set
	 */
	public void setSecondTrade(String[] secondTrade) {
		this.secondTrade = secondTrade;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public List<Multiple> getSecondTradeMul() {
		return secondTradeMul;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param secondTradeMul the secondTradeMul to set
	 */
	public void setSecondTradeMul(List<Multiple> secondTradeMul) {
		this.secondTradeMul = secondTradeMul;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getSecondTradeCode() {
		return secondTradeCode;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param secondTradeCode the secondTradeCode to set
	 */
	public void setSecondTradeCode(String secondTradeCode) {
		this.secondTradeCode = secondTradeCode;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String[] getProductType() {
		return productType;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param productType the productType to set
	 */
	public void setProductType(String[] productType) {
		this.productType = productType;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public List<Multiple> getProductTypeMul() {
		return productTypeMul;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param productTypeMul the productTypeMul to set
	 */
	public void setProductTypeMul(List<Multiple> productTypeMul) {
		this.productTypeMul = productTypeMul;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getProductTypeCode() {
		return productTypeCode;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param productTypeCode the productTypeCode to set
	 */
	public void setProductTypeCode(String productTypeCode) {
		this.productTypeCode = productTypeCode;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getItemNames() {
		return itemNames;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param itemNames the itemNames to set
	 */
	public void setItemNames(String itemNames) {
		this.itemNames = itemNames;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getMinBillAmt() {
		return minBillAmt;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param minBillAmt the minBillAmt to set
	 */
	public void setMinBillAmt(String minBillAmt) {
		this.minBillAmt = minBillAmt;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getMaxBillAmt() {
		return maxBillAmt;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param maxBillAmt the maxBillAmt to set
	 */
	public void setMaxBillAmt(String maxBillAmt) {
		this.maxBillAmt = maxBillAmt;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getMinCargoWeight() {
		return minCargoWeight;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param minCargoWeight the minCargoWeight to set
	 */
	public void setMinCargoWeight(String minCargoWeight) {
		this.minCargoWeight = minCargoWeight;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getMaxCargoWeight() {
		return maxCargoWeight;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param maxCargoWeight the maxCargoWeight to set
	 */
	public void setMaxCargoWeight(String maxCargoWeight) {
		this.maxCargoWeight = maxCargoWeight;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getMinCargoVolume() {
		return minCargoVolume;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param minCargoVolume the minCargoVolume to set
	 */
	public void setMinCargoVolume(String minCargoVolume) {
		this.minCargoVolume = minCargoVolume;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getMaxCargoVolume() {
		return maxCargoVolume;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param maxCargoVolume the maxCargoVolume to set
	 */
	public void setMaxCargoVolume(String maxCargoVolume) {
		this.maxCargoVolume = maxCargoVolume;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String[] getOrderSource() {
		return orderSource;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param orderSource the orderSource to set
	 */
	public void setOrderSource(String[] orderSource) {
		this.orderSource = orderSource;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public List<Multiple> getOrderSourceMul() {
		return orderSourceMul;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param orderSourceMul the orderSourceMul to set
	 */
	public void setOrderSourceMul(List<Multiple> orderSourceMul) {
		this.orderSourceMul = orderSourceMul;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getOrderSourceCode() {
		return orderSourceCode;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param orderSourceCode the orderSourceCode to set
	 */
	public void setOrderSourceCode(String orderSourceCode) {
		this.orderSourceCode = orderSourceCode;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public List<ActivityDept> getActivityDepts() {
		return activityDepts;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param activityDepts the activityDepts to set
	 */
	public void setActivityDepts(List<ActivityDept> activityDepts) {
		this.activityDepts = activityDepts;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getDeptNum() {
		return deptNum;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param deptNum the deptNum to set
	 */
	public void setDeptNum(String deptNum) {
		this.deptNum = deptNum;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getLineRequest() {
		return lineRequest;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param lineRequest the lineRequest to set
	 */
	public void setLineRequest(String lineRequest) {
		this.lineRequest = lineRequest;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public List<LineDept> getLineDepts() {
		return lineDepts;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param lineDepts the lineDepts to set
	 */
	public void setLineDepts(List<LineDept> lineDepts) {
		this.lineDepts = lineDepts;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getPreferType() {
		return preferType;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param preferType the preferType to set
	 */
	public void setPreferType(String preferType) {
		this.preferType = preferType;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getWorkFlowNum() {
		return workFlowNum;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param workFlowNum the workFlowNum to set
	 */
	public void setWorkFlowNum(String workFlowNum) {
		this.workFlowNum = workFlowNum;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public List<ActivityOption> getDiscountInfo() {
		return discountInfo;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param discountInfo the discountInfo to set
	 */
	public void setDiscountInfo(List<ActivityOption> discountInfo) {
		this.discountInfo = discountInfo;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getDiscountCode() {
		return discountCode;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param discountCode the discountCode to set
	 */
	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public List<ActivityOption> getCouponInfo() {
		return couponInfo;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param couponInfo the couponInfo to set
	 */
	public void setCouponInfo(List<ActivityOption> couponInfo) {
		this.couponInfo = couponInfo;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getCouponCode() {
		return couponCode;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param couponCode the couponCode to set
	 */
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getUseRule() {
		return useRule;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param useRule the useRule to set
	 */
	public void setUseRule(String useRule) {
		this.useRule = useRule;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getCreateRule() {
		return createRule;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param createRule the createRule to set
	 */
	public void setCreateRule(String createRule) {
		this.createRule = createRule;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public List<ActivityClientBase> getClientBase() {
		return clientBase;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param clientBase the clientBase to set
	 */
	public void setClientBase(List<ActivityClientBase> clientBase) {
		this.clientBase = clientBase;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public List<FileInfo> getFiles() {
		return files;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param files the files to set
	 */
	public void setFiles(List<FileInfo> files) {
		this.files = files;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getFileSourceType() {
		return fileSourceType;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param fileSourceType the fileSourceType to set
	 */
	public void setFileSourceType(String fileSourceType) {
		this.fileSourceType = fileSourceType;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getActivityStatus() {
		return activityStatus;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param activityStatus the activityStatus to set
	 */
	public void setActivityStatus(String activityStatus) {
		this.activityStatus = activityStatus;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getDeptId() {
		return deptId;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param deptId the deptId to set
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 */
	public String getActivitySourceCode() {
		return activitySourceCode;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-11
	 * @param activitySourceCode the activitySourceCode to set
	 */
	public void setActivitySourceCode(String activitySourceCode) {
		this.activitySourceCode = activitySourceCode;
	}
	
}
