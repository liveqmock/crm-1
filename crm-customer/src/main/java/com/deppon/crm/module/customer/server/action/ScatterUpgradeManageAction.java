package com.deppon.crm.module.customer.server.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.customer.server.manager.IAlterMemberManager;
import com.deppon.crm.module.customer.server.manager.IBaseDataManager;
import com.deppon.crm.module.customer.server.manager.IMemberManager;
import com.deppon.crm.module.customer.shared.domain.Account;
import com.deppon.crm.module.customer.shared.domain.ApproveData;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.MemberCondition;
import com.deppon.crm.module.customer.shared.domain.MemberResult;
import com.deppon.crm.module.customer.shared.domain.PreferenceAddress;
import com.deppon.crm.module.customer.shared.domain.ShuttleAddress;
import com.deppon.crm.module.customer.shared.domain.UpGradeCustomer;
import com.deppon.crm.module.customer.shared.domain.UpGradeCustomerCondition;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.web.action.AbstractAction;
/**
 * <p>
 * 散客升级管理action<br />
 * </p>
 * @title ScatterUpgradeManager.java
 * @package com.deppon.crm.module.customer.server.action
 * @author 李学兴
 * @version 0.1 2012-3-21
 */

public class ScatterUpgradeManageAction extends AbstractAction {
	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = 1L;
	private IMemberManager memberManager;//会员manage
	private IAlterMemberManager alterMemberManager;//弹出框查会员manage
	private IBaseDataManager baseDataMemberManager;//账号省市区
    //会员信息
	private Member member = new Member();
	//传入前台的通过条件查询潜客结果
	private List<UpGradeCustomer> upGradeCustlist = new ArrayList<UpGradeCustomer>();
	//前台传入的条件查询潜客信息的start参数（用于分页）
	private int start;
	//前台传入的条件查询潜客信息的limit参数（用于分页）
	private int limit;
	//传入前台的通过条件查询潜客结果总数
	private Long totalCount;
	//散客升级列表查询条件
	private UpGradeCustomerCondition scatterUpGradeCondition = new UpGradeCustomerCondition();
	//散客升级列表客户
	private UpGradeCustomer upGradeCust = new UpGradeCustomer();
	//散客升级列表客户id
	private String upGradeCustId;
	//散客升级列表客户 备注信息
	private String remark;
	//会员信息弹出框查询条件
	private MemberCondition searchCustCondition = new MemberCondition();
    //会员信息列表
	private List<MemberResult> memberResultList = new ArrayList<MemberResult>();
	
	//税务登记号
	private String taxNum;
	//身份证号
	private String idNumber;
	private String custType;//公司，个人
	private Boolean isMainContact;//是否主联系人
	//手机号码
	private String mobile;
	//固定电话
	private String telNum;
	private String contactName;
	//证件类型
	private String cardType;
	//联系人编码
	private String linkManNumber;
	
	private Boolean exist;
	//银行账户 省，市，银行名称 list
	private List<Map<String,String>> list = new ArrayList<Map<String,String>>();
	//银行账户 省id
	private String bankprovince;
	//部门id
	private String deptId;
	//用于判断香港还是大陆的标识位
	private String location;
	

	/**
	 * 
	 * <p>
	 * Description:查询散客升级列表信息<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-3-27
	 * @return
	 * String
	 */
	public String searchScatterUpgradeList(){
		totalCount = Long.valueOf(memberManager.getCountUpGradeCustomerByUpGradeCustomerCondition(scatterUpGradeCondition));
		upGradeCustlist = memberManager.searchUpGradeCustomerList(scatterUpGradeCondition,start,limit);
		return SUCCESS;
	}
	/**
	 * 
	 * @Title: validateHKTaxIsExist
	 *  <p>
	 * @Description: 实时校验商业登记号的合法性<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-1-25
	 * @return String
	 * @throws
	 */
	public String validateHKTaxIsExist(){
		exist = memberManager.checkTaxregNumberCanUseForSpecialMember(location, taxNum);
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * Description:待升级散客增加备注<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-3-27
	 * @return
	 * String SUCCESS
	 */
	public String addRemark2UpgradeScatter(){
		memberManager.addUpGradeCustomerRemark(upGradeCustId,remark);
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * Description:删除(作废)散客信息<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-9-13
	 * @return
	 * String SUCCESS
	 */
	public String deleteScatterUpgrade(){
		memberManager.deleteScatterUpgradeById(upGradeCustId);
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * Description:通过id查询升级散客信息<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-3-27
	 * @return 
	 * String SUCCESS
	 */
	public String searchScatterUpgradeById(){
		upGradeCust = memberManager.getUpGradeCustomerById(upGradeCustId);
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * Description:获得银行省份<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-6-11
	 * @return
	 * String
	 */
	public String searchBankProvince(){
		list = baseDataMemberManager.getBankProvince();
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * Description:获得银行省份id获得银行城市<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-6-11
	 * @return
	 * String
	 */
	public String searchBankCityByProvinceId(){
		list = baseDataMemberManager.getBankCityByBankProvinceId(bankprovince);
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * Description:获得银行名称<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-6-11
	 * @return
	 * String
	 */
	public String searchBankName(){
		list = baseDataMemberManager.getAccountBank();
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * Description:创建会员信息<br />
	 * </p>
	 * @author 李盛
	 * @version 0.1 2012-3-24
	 * @return
	 * String
	 */
	public String saveMember(){
		this.memberManager.createMember(member,upGradeCust);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:根据散客升级记录查询会员<br />
	 * </p>
	 * @author 李盛
	 * @version 0.1 2012-3-29
	 * @return
	 * String
	 */
	public String findMemberFromScatter(){
		String deptId = ((User)UserContext.getCurrentUser()).getEmpCode().getDeptId().getId();
		this.member = this.memberManager.initMember(this.upGradeCust);
		this.member.setDeptId(deptId);
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * Description:实时验证税务登记号是否唯一<br />
	 * </p>
	 * @author 李盛
	 * @version 0.1 2012-4-7
	 * @return
	 * String
	 */
	public String validateTaxIsExsist(){
		exist = this.memberManager.checkTaxregNumberCanUse(this.taxNum);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:实时验证手机号码是否唯一<br />
	 * </p>
	 * @author 李盛
	 * @version 0.1 2012-4-7
	 * @return
	 * String
	 */
	public String validateMobileIsExsist(){
		exist = this.memberManager.checkMobilePhoneCanUse(this.mobile);
		return SUCCESS;
	}

	/**
	 * 
	 * <p>
	 * Description:实时验证联系人姓名加固话是否唯一<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.1 2012-07-19
	 * @return
	 * String SUCCESS exist：false为可增加该联系人
	 */
	public String validateTelAndNameIsExsist(){
		exist = this.memberManager.checkTelAndNameCanUse(this.telNum,this.contactName);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:实时验证身份证号是否唯一<br />
	 * </p>
	 * @author 李盛
	 * @version 0.1 2012-4-7
	 * @return
	 * String
	 */
	public String validateIdNumberIsExsist(){
		if(!StringUtils.isEmpty(idNumber)){
			exist = this.memberManager.checkIdCardCanUse(this.idNumber,custType,isMainContact,cardType);
		}
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:实时验证证件号码是否合法<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.1 2012-4-7
	 * @return
	 * String
	 */
	public String validateIdNumberIsLegal(){
		exist = this.memberManager.checkIdCardIsLegal(this.idNumber,cardType);
		return SUCCESS;
	}

	/**
	 * 
	 * <p>
	 * Description:实时验证联系人编码是否重复<br /> exist 为true说明可用
	 * </p>
	 * @author 李学兴
	 * @version 0.1 2012-4-7
	 * @return
	 * String
	 */
	public String validateLinkManNumberCanUse(){
		exist = memberManager.checkLinkManNumberCanUse(linkManNumber);
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * Description:保存特殊会员<br />
	 * </p>
	 * @author 李盛
	 * @version 0.1 2012-4-7
	 * @return
	 * String
	 */
	public String saveSpecialMember(){
		workFlowNum = this.memberManager.createSpecialMember(this.member);
		return SUCCESS;
	}
	
	//会员相关数据修改信息
	private List<ApproveData> alterMemberApproveData;
	//会员修改时新增的联系人信息
	private List<Contact> alterAddLinkmanData;
	//会员修改时新增的偏好地址信息
	private List<PreferenceAddress> alterAddAddressHobbyData;
	//会员修改时新增的账号信息
	private List<Account> alterAddAccountData;
	//会员修改时新增的接送货地址信息
	private List<ShuttleAddress> alterAddAddressData;
	//会员修改时相关数据删除记录
	private List<ApproveData> alterDeleteMemberData;
	//修改会员ID
	private String memberId;
	//工作流号
	private String workFlowNum;
	/**
	 * 
	 * <p>
	 * Description:修改会员信息<br />
	 * </p>
	 * @author 李盛
	 * @version 0.1 2012-4-7
	 * @return
	 * String
	 */
	@SuppressWarnings("rawtypes")
	public String alterMember(){
		Map<String, List> addPojoMap = new HashMap<String,List>();
		addPojoMap.put("Contact", alterAddLinkmanData);
		addPojoMap.put("PreferenceAddress", alterAddAddressHobbyData);
		addPojoMap.put("Account", alterAddAccountData);
		addPojoMap.put("ShuttleAddress", alterAddAddressData);

		workFlowNum = this.alterMemberManager.alterMember(alterMemberApproveData, addPojoMap, alterDeleteMemberData, memberId,member);
		return SUCCESS;
	}
	
	public Member getMember() {
		return member;
	}


	public void setMember(Member member) {
		this.member = member;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public List<UpGradeCustomer> getUpGradeCustlist() {
		return upGradeCustlist;
	}
	public void setUpGradeCustlist(List<UpGradeCustomer> upGradeCustlist) {
		this.upGradeCustlist = upGradeCustlist;
	}
	public UpGradeCustomer getUpGradeCust() {
		return upGradeCust;
	}
	public void setUpGradeCust(UpGradeCustomer upGradeCust) {
		this.upGradeCust = upGradeCust;
	}
	public String getUpGradeCustId() {
		return upGradeCustId;
	}
	public void setUpGradeCustId(String upGradeCustId) {
		this.upGradeCustId = upGradeCustId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setMemberManager(IMemberManager memberManager) {
		this.memberManager = memberManager;
	}
	public UpGradeCustomerCondition getScatterUpGradeCondition() {
		return scatterUpGradeCondition;
	}
	public void setScatterUpGradeCondition(
			UpGradeCustomerCondition scatterUpGradeCondition) {
		this.scatterUpGradeCondition = scatterUpGradeCondition;
	}
	public MemberCondition getSearchCustCondition() {
		return searchCustCondition;
	}
	public void setSearchCustCondition(MemberCondition searchCustCondition) {
		this.searchCustCondition = searchCustCondition;
	}
	public void setAlterMemberManager(IAlterMemberManager alterMemberManager) {
		this.alterMemberManager = alterMemberManager;
	}
	public String getTaxNum() {
		return taxNum;
	}
	public void setTaxNum(String taxNum) {
		this.taxNum = taxNum;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public void setAlterMemberApproveData(List<ApproveData> alterMemberApproveData) {
		this.alterMemberApproveData = alterMemberApproveData;
	}
	public void setAlterDeleteMemberData(List<ApproveData> alterDeleteMemberData) {
		this.alterDeleteMemberData = alterDeleteMemberData;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public void setAlterAddLinkmanData(List<Contact> alterAddLinkmanData) {
		this.alterAddLinkmanData = alterAddLinkmanData;
	}
	public void setAlterAddAddressHobbyData(
			List<PreferenceAddress> alterAddAddressHobbyData) {
		this.alterAddAddressHobbyData = alterAddAddressHobbyData;
	}
	public void setAlterAddAccountData(List<Account> alterAddAccountData) {
		this.alterAddAccountData = alterAddAccountData;
	}
	public void setAlterAddAddressData(List<ShuttleAddress> alterAddAddressData) {
		this.alterAddAddressData = alterAddAddressData;
	}
	public List<MemberResult> getMemberResultList() {
		return memberResultList;
	}
	public String getWorkFlowNum() {
		return workFlowNum;
	}
	public void setCustType(String custType) {
		this.custType = custType;
	}
	public void setIsMainContact(Boolean isMainContact) {
		this.isMainContact = isMainContact;
	}
	public Boolean getExist() {
		return exist;
	}
	public List<Map<String, String>> getList() {
		return list;
	}
	public void setBankprovince(String bankprovince) {
		this.bankprovince = bankprovince;
	}
	public void setBaseDataMemberManager(IBaseDataManager baseDataMemberManager) {
		this.baseDataMemberManager = baseDataMemberManager;
	}
	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public void setLinkManNumber(String linkManNumber) {
		this.linkManNumber = linkManNumber;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}	
}
