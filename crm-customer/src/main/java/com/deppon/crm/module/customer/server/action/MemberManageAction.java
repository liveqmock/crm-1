package com.deppon.crm.module.customer.server.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.common.server.util.DataDictionaryUtil;
import com.deppon.crm.module.common.shared.domain.DataHeadTypeEnum;
import com.deppon.crm.module.customer.server.manager.IAlterMemberManager;
import com.deppon.crm.module.customer.server.manager.IBaseDataManager;
import com.deppon.crm.module.customer.server.manager.IMemberManager;
import com.deppon.crm.module.customer.shared.domain.ApproveData;
import com.deppon.crm.module.customer.shared.domain.ChangeMemberDept;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.MemberCondition;
import com.deppon.crm.module.customer.shared.domain.MemberResult;
import com.deppon.foss.framework.server.web.action.AbstractAction;
/**
 * <p>
 * 会员管理action<br />
 * </p>
 * @title MemberManageAction.java
 * @package com.deppon.crm.module.customer.server.action
 * @author 李学兴
 * @version 0.1 2012-4-5
 */

public class MemberManageAction extends AbstractAction {
	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = -8048977635144090036L;
	private IAlterMemberManager alterMemberManager;//会员修改manage
	private IMemberManager memberManager;//会员manage
	private IBaseDataManager baseDataManager;
	//会员信息弹出框查询条件
	private MemberCondition searchCustCondition = new MemberCondition();
	//前台传入的条件查询潜客信息的start参数（用于分页）
	private int start;
	//前台传入的条件查询潜客信息的limit参数（用于分页）
	private int limit;
	//传入前台的通过条件查询潜客结果总数
	private Long totalCount;
    //会员信息列表 
	private List<MemberResult> memberResultList = new ArrayList<MemberResult>();
    //会员信息
	private Member member = new Member();
	private ChangeMemberDept changeMemberDept = new ChangeMemberDept();
	//会员变更部门审批工作流
	private String workFlowNum;
	//日志id
	private String logId;
	private List<ApproveData> approveDataList = new ArrayList<ApproveData> ();
	/**
	 * 
	 * <p>
	 * Description:查询会员信息<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-4-5
	 * @return
	 * String
	 */
	public String searchMemberList(){
		searchCustCondition.setStart(start);
		searchCustCondition.setLimit(limit);
		//添加数据权限
		String deptId = searchCustCondition.getDeptId();
		List<String> deptIds = baseDataManager.getDataAuthorityDepts(deptId);
		searchCustCondition.setDeptIds(deptIds);
		
		memberResultList = alterMemberManager.searchMember(searchCustCondition);
		totalCount = Long.valueOf(alterMemberManager.countMemberByCondition(searchCustCondition));
		for(int i = 0; i< memberResultList.size();i++){
			//TODO
			//会员编码
			String custNum =  memberResultList.get(i).getCustNum();
	        //会员名称
			String custName =  memberResultList.get(i).getCustName();
	        //会员等级 数据字典转换
			String custGrade =  DataDictionaryUtil.getCodeDesc(DataHeadTypeEnum.MEMBER_GRADE, memberResultList.get(i).getCustGrade());	
			if(custGrade == null){
				custGrade = "";
			}
			//客户性质
			String custGroupPSR = DataDictionaryUtil.getCodeDesc(DataHeadTypeEnum.CUST_TYPE, memberResultList.get(i).getCustGroupPSR());
			//客户类别
			String custCategory = DataDictionaryUtil.getCodeDesc(DataHeadTypeEnum.CUST_CATEGORY, memberResultList.get(i).getCustCategory());
			memberResultList.get(i).setCustGroup(getCustGroup(custNum,custName,custGrade,custGroupPSR,custCategory));
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
			String custGrade,String custGroupPSR,String custCategory) {
		String custNumView = "客户编码:" + custNum;
		String custGradeView = "客户等级:" + getNbsp(custGrade,2);
		String custNameView = "客户名称:" + custName;
		if(StringUtils.isEmpty(custGroupPSR)
				||StringUtils.isEmpty(custCategory)){
			return custNumView + "," + custGradeView + "," + custNameView;
		}
		String custGroupPSRView = "客户性质:"+getNbsp(custGroupPSR,2); 
		String custCategoryView = "客户类别:"+getNbsp(custCategory,2);
		return custNumView + "," + custGradeView + "," + custNameView+","+custGroupPSRView+","+custCategoryView;

	}
	
	private String getNbsp(String name,int length){
		return StringUtils.rightPad(name,length,' ').replaceAll(" ","&nbsp;");
	}
	
	
	
	/**
	 * 
	 * <p>
	 * Description:查询会员信息(订单模块使用)<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-6-21
	 * @return
	 * String SUCCESS
	 */
	public String searchMember4OrderList(){
		searchCustCondition.setStart(start);
		searchCustCondition.setLimit(limit);
		memberResultList = alterMemberManager.searchMember(searchCustCondition);
		totalCount = Long.valueOf(alterMemberManager.countMemberByCondition(searchCustCondition));
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
			memberResultList.get(i).setCustGroup(getCustGroup(custNum,custName,custGrade,null,null));
		}
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * Description:通过会员id获得会员信息"查看详情"<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-4-6
	 * @return
	 * String
	 */
	public String acquireMemberById(){
		member = alterMemberManager.getMemberAllById(searchCustCondition);
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * Description:散客升级<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-4-6
	 * @return
	 * String
	 */
	public String upgradeScatter(){
		memberManager.upgradeScatter(member);
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * Description:通过会员id获得作废会员信息<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-4-6
	 * @return
	 * String
	 */
	public String acquireInvalidMemberById(){
		member = alterMemberManager.getInvalidMemberAllById(searchCustCondition);
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * Description:通过会员id获得会员信息"修改操作"<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-4-6
	 * @return
	 * String
	 */
	public String acquireMember4UpdateById(){
		member = alterMemberManager.queryAlterMemberById(searchCustCondition.getMemberId());
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * Description:通过会员id获得会员信息<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-4-6
	 * @return
	 * String
	 */
	public String searchApproveDataByLogId(){
		approveDataList = alterMemberManager.searchApproveDataByLogId(logId);
		return SUCCESS;
	}

	/**
	 * 
	 * <p>
	 * Description:会员部门变更<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.1 2012-6-6
	 * @return
	 * String
	 */
	public String changeMemberDept(){
		workFlowNum = String.valueOf(memberManager.changeMemberDept(changeMemberDept));
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * Description:界面作废（逻辑删除）固定客户，删除潜散客<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.1 2012-9-10
	 * @return
	 * String
	 */
	public String deleteMember(){
		alterMemberManager.deleteMember(member);
		return SUCCESS;
	}
	
	/**
	 * 
	* @Title: createPotentialMember
	* @Description: 手动创建客户（默认潜客）
	* @author chenaichun 
	* @param @return    设定文件
	* @date 2014-3-20 上午11:43:48
	* @return String    返回类型
	* @throws
	* @update 2014-3-20 上午11:43:48
	 */
	public String createPotentialMember(){
		memberManager.createPotentialCustomer(this.member);
		return SUCCESS;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}

	public void setSearchCustCondition(MemberCondition searchCustCondition) {
		this.searchCustCondition = searchCustCondition;
	}

	public void setAlterMemberManager(IAlterMemberManager alterMemberManager) {
		this.alterMemberManager = alterMemberManager;
	}

	public MemberCondition getSearchCustCondition() {
		return searchCustCondition;
	}
	public Member getMember() {
		return member;
	}
	public List<MemberResult> getMemberResultList() {
		return memberResultList;
	}
	public void setMemberResultList(List<MemberResult> memberResultList) {
		this.memberResultList = memberResultList;
	}
	public ChangeMemberDept getChangeMemberDept() {
		return changeMemberDept;
	}
	public void setChangeMemberDept(ChangeMemberDept changeMemberDept) {
		this.changeMemberDept = changeMemberDept;
	}
	public void setMemberManager(IMemberManager memberManager) {
		this.memberManager = memberManager;
	}
	public String getWorkFlowNum() {
		return workFlowNum;
	}
	public void setBaseDataManager(IBaseDataManager baseDataManager) {
		this.baseDataManager = baseDataManager;
	}
	public void setLogId(String logId) {
		this.logId = logId;
	}
	public List<ApproveData> getApproveDataList() {
		return approveDataList;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	
}
