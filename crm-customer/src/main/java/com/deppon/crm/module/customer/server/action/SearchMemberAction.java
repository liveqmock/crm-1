package com.deppon.crm.module.customer.server.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.common.server.util.DataDictionaryUtil;
import com.deppon.crm.module.common.shared.domain.DataHeadTypeEnum;
import com.deppon.crm.module.customer.server.manager.IAlterMemberManager;
import com.deppon.crm.module.customer.server.manager.IBaseDataManager;
import com.deppon.crm.module.customer.server.utils.ContextUtil;
import com.deppon.crm.module.customer.server.utils.ExpressAuthDeptUtil;
import com.deppon.crm.module.customer.shared.domain.MemberCondition;
import com.deppon.crm.module.customer.shared.domain.MemberResult;
import com.deppon.crm.module.customer.shared.domain.PreferenceAddress;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.server.web.action.AbstractAction;
/**
 * <p>
 * F7弹出框查询会员，联系人信息action<br />
 * </p>
 * @title SearchMemberAction.java
 * @package com.deppon.crm.module.customer.server.action
 * @author 李学兴
 * @version 0.1 2012-4-10
 */

public class SearchMemberAction extends AbstractAction {
	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = 2407721490754440507L;
	//会员信息弹出框查询条件
	private MemberCondition searchCustCondition = new MemberCondition();
    //会员信息列表
	private List<MemberResult> memberResultList = new ArrayList<MemberResult>();
	private IAlterMemberManager alterMemberManager;//弹出框查会员manage
	
	private IBaseDataManager baseDataManager;
	//联系人偏好地址
	private List<PreferenceAddress> preferenceAddressList;
	public List<PreferenceAddress> getPreferenceAddressList() {
		return preferenceAddressList;
	}
	//联系人ID
	private String contactId;
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	//地址类型
	private String addressType;
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	/**
	 * 
	 * <p>
	 * Description:F7 会员信息弹出框查询<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-4-1
	 * @return
	 * String
	 */
	public String searchMemberInfoList(){
		searchCustCondition.setLimit(-1);//-1标识不用分页
		//添加数据权限
		String deptId = searchCustCondition.getDeptId();
		List<String> deptIds = baseDataManager.getDataAuthorityDepts(deptId);
		searchCustCondition.setDeptIds(deptIds);
		List<MemberResult> memberResultList = alterMemberManager.searchMember(searchCustCondition);
		this.memberResultList = searchMemberList(memberResultList);
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * Description:F7 会员信息弹出框查询提供给360查询<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-4-1
	 * @return
	 * String
	 */
	public String searchMemberInfoListFor360(){
		searchCustCondition.setLimit(-1);//-1标识不用分页
		//添加数据权限
		String deptId = searchCustCondition.getDeptId();
		List<String> deptIds = baseDataManager.getDataAuthorityDepts(deptId);
		searchCustCondition.setDeptIds(deptIds);
		//客户360不用查询作废客户的信息
		List<MemberResult> memberResultList = alterMemberManager.searchMemberListFor360(searchCustCondition);
		this.memberResultList = searchMemberList(memberResultList);
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * Description:F7 会员信息弹出框查询结果转化成分组格式<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-4-1
	 * @return List<MemberResult>
	 */
	private  List<MemberResult>  searchMemberList(List<MemberResult> memberResultList){
		for(int i = 0; i< memberResultList.size();i++){
			//会员编码
			String custNum =  memberResultList.get(i).getCustNum();
	        //会员名称
			String custName =  memberResultList.get(i).getCustName();
	        //会员等级 数据字典转换
			String custGrade =  DataDictionaryUtil.getCodeDesc(DataHeadTypeEnum.MEMBER_GRADE, memberResultList.get(i).getCustGrade());	
			if(custGrade == null){
				custGrade = "";
			}
			memberResultList.get(i).setCustGroup(getCustGroup(custNum,custName,custGrade));
		}
		return memberResultList;
	}
	/**
	 * 
	 * <p>
	 * Description:F7 会员信息弹出框查询合同使用<br />
	 * </p>
	 * @author 潘光均
	 * @version 0.2 2012-4-1
	 * @return
	 * String
	 */
	@SuppressWarnings("static-access")
	public String searchMemberInfoListWithAuth(){
		searchCustCondition.setLimit(-1);//-1标识不用分页
		//添加数据权限
		
		String deptId = searchCustCondition.getDeptId();
		/**
		 * @description:添加快递经理拥有的营业部权限 TODO 客户查询权限重写
		 * @author pgj
		 * @date 2013-09-19
		 */
		List<Department> departs = baseDataManager
				.getAuthorityBusinessDept(ContextUtil
						.getCurrentUserDept().getStandardCode());
		List<String> deptIds = ExpressAuthDeptUtil.getAuthDept(
				departs);
		if (CollectionUtils.isEmpty(deptIds)) {
			deptIds = baseDataManager.getDataAuthorityDepts(deptId);
		}
		
		searchCustCondition.setDeptIds(deptIds);
		
		memberResultList = alterMemberManager.searchMemberWithAuth(searchCustCondition);
		for(int i = 0; i< memberResultList.size();i++){
			//会员编码
			String custNum =  memberResultList.get(i).getCustNum();
	        //会员名称
			String custName =  memberResultList.get(i).getCustName();
	        //会员等级 数据字典转换
			String custGrade =  DataDictionaryUtil.getCodeDesc(DataHeadTypeEnum.MEMBER_GRADE, memberResultList.get(i).getCustGrade());	
			if(custGrade == null){
				custGrade = "";
			}
			memberResultList.get(i).setCustGroup(getCustGroup(custNum,custName,custGrade));
		}
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * 会员管理界面的custGrade生成<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-7-5
	 * @param custNum
	 * @param custName
	 * @param custGrade
	 * @return
	 * String
	 */
	private String getCustGroup(String custNum, String custName,
			String custGrade) {
		String custNumView = "客户编码:" + custNum;
		String custGradeView = "客户等级:" + getNbsp(custGrade,15);
		String custNameView = "客户名称:" + custName;
		return custNumView + "," + custGradeView + "," + custNameView;
	}
	private String getNbsp(String name,int length){
		return StringUtils.rightPad(name,length,' ').replaceAll(" ","&nbsp;");
	}
	/**
	 * 
	 * <p>
	 * 根据联系人id查询联系人下的偏好地址<br />
	 * </p>
	 * @author 张斌
	 * @version 0.2 2012-6-16
	 * @return
	 * String
	 */
	public String searchAddressInfoList(){
		//@TODO 增加方法根据联系人ID查询联系人的偏好地址
		preferenceAddressList=alterMemberManager.searchPreferenceAddressByContactId(contactId,addressType);
		return SUCCESS;
	}
	public MemberCondition getSearchCustCondition() {
		return searchCustCondition;
	}
	public void setSearchCustCondition(MemberCondition searchCustCondition) {
		this.searchCustCondition = searchCustCondition;
	}
	public List<MemberResult> getMemberResultList() {
		return memberResultList;
	}
	public void setAlterMemberManager(IAlterMemberManager alterMemberManager) {
		this.alterMemberManager = alterMemberManager;
	}
	public void setBaseDataManager(IBaseDataManager baseDataManager) {
		this.baseDataManager = baseDataManager;
	}

	
}
