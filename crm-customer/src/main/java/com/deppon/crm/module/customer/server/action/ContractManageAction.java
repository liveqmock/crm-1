package com.deppon.crm.module.customer.server.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.crm.module.customer.server.manager.IBaseDataManager;
import com.deppon.crm.module.customer.server.manager.IContractManager;
import com.deppon.crm.module.customer.server.manager.IMemberManager;
import com.deppon.crm.module.customer.server.utils.ContextUtil;
import com.deppon.crm.module.customer.server.utils.ContractUtil;
import com.deppon.crm.module.customer.server.utils.ValidateUtil;
import com.deppon.crm.module.customer.shared.domain.ApproveData;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.Contract;
import com.deppon.crm.module.customer.shared.domain.ContractCondition;
import com.deppon.crm.module.customer.shared.domain.ContractDetailView;
import com.deppon.crm.module.customer.shared.domain.ContractMonthendDay;
import com.deppon.crm.module.customer.shared.domain.ContractView;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.Preferential;
import com.deppon.foss.framework.entity.IUser;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.web.action.AbstractAction;
/**
 * <p>
 * 合同管理action<br />
 * </p>
 * @title MemberDownGradeManageAction.java
 * @package com.deppon.crm.module.customer.server.action
 * @author 李学兴
 * @version 0.1 2012-3-29
 */

public class ContractManageAction extends AbstractAction {
	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = -7097468533044492330L;
	private IContractManager contractManager;//合同管理manage
	public IMemberManager memberManager;//会员管理manage
	public IBaseDataManager baseDataManager;
	//查询条件
	private ContractCondition contractCondition = new ContractCondition();
	//查询结果
	private List<ContractDetailView> contractList = new ArrayList<ContractDetailView>();
	//演示加载返回结果
	private ContractView contractView = new ContractView();
	//前台传入的条件查询潜客信息的start参数（用于分页）
	private int start;
	//前台传入的条件查询潜客信息的limit参数（用于分页）
	private int limit;
	//传入前台的通过条件查询潜客结果总数
	private Long  totalCount;
	//合同信息
	private ContractDetailView contract = new ContractDetailView();
	//操作类型
	private String handType;
	//工作流号(保存合同成功后返回)
	private String workFlowNum;
	//合同Id
	private String contractId;
	private List<String> contractIds;
	//修改的合同数据
	private List<ApproveData> approveDataList = new ArrayList<ApproveData>();
	//部门id
	private String deptId;
	//优惠 新增
	private Preferential addPreferential;
	//新增合同附件信息
	private List<FileInfo> fileInfoList = new ArrayList<FileInfo>();
	//合同附件信息
	private FileInfo fileInfo = new FileInfo();
	//校验合同是否是审批中状态
	private boolean checked;
	//合同客户信息
	private Member member = new Member();
	private String memberId;
//	private Double amount;//结算限额
	//合同月结天数
	private int debtDays;
	//合同月结天数查询结果list
	List<ContractMonthendDay> contractMonthendDaysList = new ArrayList<ContractMonthendDay>();
	//是否异地调货
	private boolean ifForeignGoods;
	//催款部门(标杆编码)
	private String dunningDeptCode;
	private List<ContractDetailView> contractSubjectList = new ArrayList<ContractDetailView>();
	//合同开始时间
	private Date contractEndDate;
	//合同结束时间
	private Date contractBeginDate;
	//客户编码
	private String custNumber;
	//价格版本时间
	private Date priceVersionDate;
	//快递版本时间
	private Date exPriceVersionDate;
	
	/**
	 * 税务添加@ chenaichun
	 */
	private String updFlag;
	/** 
	 * <p>
	 * Description:查询合同信息<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-4-11
	 * @return
	 * String
	 */
	public String searchContractList(){
		if (contractCondition.getContractendDate() != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(contractCondition.getContractendDate());
			cal.set(Calendar.HOUR_OF_DAY, 23); 
			cal.set(Calendar.MINUTE,59); 
			cal.set(Calendar.SECOND,59);
			contractCondition.setContractendDate(cal.getTime());
		}
		//适配器转换
		contractList = new ArrayList<ContractDetailView>();
		List<Contract> list = null;
		if (contractCondition.getInitSearch().equals("1")) {
			list = contractManager.searchContract(contractCondition, start, limit);
			totalCount = Long.valueOf(contractManager.countContract(contractCondition));
		}else{
			list = contractManager.searchInitContract(contractCondition, start, limit);
			totalCount = Long.valueOf(contractManager.countInitContract(contractCondition));
		}
		
		for (Contract contract : list) {
			contractList.add(ContractUtil.changeContractToContractDetailView(contract));
		}
		return SUCCESS;
	}
		/**
	 * 
	 @Discript 查询出所有通用合同月结天数
	 @author  唐亮
	 @date 2013-1-9
	 @return String
	 */
	public String searchAllCommonContractDeptDays(){	
		List<ContractMonthendDay> list = contractManager.searchAllContractDebtDays();
		for(ContractMonthendDay contractMonthendDay : list){			
			contractMonthendDaysList.add(contractMonthendDay);
		}
		return SUCCESS;
	}

	/**
	 * 初始化月结天数
	 @Discript 
	 @author  唐亮
	 @date 2013-1-11
	 @return String
	 */
	public String initMonthEndDay(){
		List<ContractMonthendDay> list = contractManager.searchAllContractDebtDays();
		debtDays = list.get(0).getDefaultDebtDays();
		return SUCCESS;
	}

	/**
	 * 
	 * <p>
	 * Description:查询初始化合同信息<br />
	 * </p>
	 * @author 潘光均
	 * @version 0.2 2013-2-25
	 * @return
	 * String
	 */
	public String searchInitContractList(){
		//设置合同归属部门
		contractCondition.setDeptId(ContextUtil.getCurrentUserDeptId());
		//适配器转换
		contractList = new ArrayList<ContractDetailView>();
		List<Contract> list = null;
		if (contractCondition.getInitSearch().equals("1")) {
			list = contractManager.searchContract(contractCondition, start, limit);
			totalCount = Long.valueOf(contractManager.countContract(contractCondition));
		}else{
			list = contractManager.searchInitContract(contractCondition, start, limit);
			totalCount = Long.valueOf(contractManager.countInitContract(contractCondition));
		}
		
		for (Contract contract : list) {
			contractList.add(ContractUtil.changeContractToContractDetailView(contract));
		}
		return SUCCESS;
	}
	/**
	 * <p>
	 * Description:查询合同的所属子公司<br />
	 * </p>
	 * @author 李国文
	 * @version 0.1 2013-3-15
	 * @return
	 * String
	 */
	public String searchContractSubjectList() {
		//根据客户所在部门id查询部门所在的子公司
		String currentCompanyName = null;
		if (StringUtils.isEmpty(deptId)) {
			currentCompanyName = baseDataManager.getDeptByStandardCode(
					ContextUtil.getCurrentUserDept().getStandardCode()).getCompanyName();
		}else{
			currentCompanyName=baseDataManager.getDeptById(deptId).getCompanyName();
		}
		
		ContractDetailView contractDetailView = new ContractDetailView();
		if (!StringUtils.isEmpty(currentCompanyName)) {
			contractDetailView.setContractSubject(currentCompanyName);
			contractSubjectList.add(contractDetailView);
		}
		
		contractDetailView = new ContractDetailView();
		contractDetailView.setContractSubject(Constant.DEPPON_COMPANY);
		contractSubjectList.add(contractDetailView);
		return SUCCESS;
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:查看合同详情<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-4-12
	 * @return
	 * String
	 */
	public String viewContractDetail(){
		contractView = new ContractView();
		Contract contract = contractManager.lookDetailContractInfo(contractId);
		contractView = ContractUtil.createContractView(contract);
		
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:点击修改按钮查看合同详情<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-4-12
	 * @return
	 * String
	 */
	public String viewUpdateContractDetail(){
		Contract contract = contractManager.getUpdateContractInfo(contractId,handType);
		contract.setBeforeContractNum(contract.getContractNum());
		contract.setContractNum("");
		contractView = ContractUtil.createContractView(contract);
		member.setContactList(this.memberManager.getContactsByMemberId(contract.getMember().getId()));
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * Description:作废合同<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-4-11
	 * @return
	 * String
	 */
	public String invalidContract(){
		//handType:"STOP"终止，"DEL"删除
		if("STOP".equals(handType)){
			workFlowNum = contractManager.cancelContract(contractIds.get(0));
		}else {
			contractManager.deleteUneffectContract(contractIds.get(0));
		}
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * Description:绑定合同<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-4-11
	 * @return
	 * String
	 */
	public String boundContract(){
		// 当前用户
		IUser user = UserContext.getCurrentUser();
		String deptId = ((User) user).getEmpCode().getDeptId().getId();
		workFlowNum = contractManager.boundContract(contractId, deptId, fileInfoList);
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * Description:解除合同<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-4-11
	 * @return
	 * String
	 */
	public String relieveContract(){
		String deptId = ContextUtil.getCurrentUserDeptId();
		contractManager.removeContract(ContractUtil.changeContractDetailViewToContract(contract), deptId);
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * Description:合同归属部门变更<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-4-12
	 * @return
	 * String
	 */
	public String belongDeptChange(){
		workFlowNum = contractManager.changeContractOwner(contractId, deptId, fileInfoList);
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * Description:导出<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-4-12
	 * @return
	 * String
	 */
	public String expordContract(){
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:保存合同<br />
	 * </p>
	 * @author 李盛
	 * @修改：李学兴
	 * @version 0.1 2012-4-14
	 * @return
	 */
	public String saveContract(){
		contract.setFileInfoList(fileInfoList);
		Contract con = ContractUtil.changeContractDetailViewToContract(contract);
		Map<String,String> jsMap = createNewContract(con,fileInfoList,contract.getContractType());
		updFlag = jsMap.get("updFlag");
		workFlowNum = jsMap.get("workflowId");
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:改签合同信息<br />
	 * </p>
	 * @author 李盛
	 * @修改：李学兴
	 * @version 0.1 2012-4-14
	 * @return
	 */
	public String updateContract(){
		Contract con = ContractUtil.changeContractDetailViewToContract(contract);
		Map<String,String> jsMap = createNewContract(con,fileInfoList,contract.getContractType());
		updFlag = jsMap.get("updFlag");
		workFlowNum = jsMap.get("workflowId");
		return SUCCESS;
	}
	private Map<String,String> createNewContract(Contract contract, List<FileInfo> files, String contractType){
		contract.setContractDepartList(null);
		contract.setContractWorkflowList(null);
		return contractManager.createNewContract(dealEndTime(contract),files,contractType);
	}

	//处理合同截止时间为 23:59:59
	private Contract dealEndTime (Contract con){
		if (con != null && con.getContractendDate() != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(con.getContractendDate());
			cal.set(Calendar.HOUR_OF_DAY, 23); 
			cal.set(Calendar.MINUTE,59); 
			cal.set(Calendar.SECOND,59);
			con.setContractendDate(cal.getTime());
		}
		return con;
	}
	/**
	 * 
	 * <p>
	 * Description:校验合同是否是审批中<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.1 2012-7-04
	 * @return SUCCESS
	 * isChecked 校验审批中 结果,true不是审批中，false审批中
	 */
	public String contractIsAuditing(){
		checked = contractManager.isContractCanOperate(contractId,handType);
		return SUCCESS;
	}
	/**
	 * 
	 @Discript 校验合同月结天数修改按钮是否在可操作时间范围内
	 @author  唐亮
	 @date 2013-1-9
	 @return String
	 */
	public String monthEndBtnIsAble(){
		contractManager.checkCurrentTime();
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * Description:修改合同操作<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.1 2012-7-04
	 * @return SUCCESS
	 * isChecked 校验审批中 结果,true不是审批中，false审批中
	 */
	public String alterContractInfo(){
		Contract con = ContractUtil.changeContractDetailViewToContract(contract);
		con.setContractDepartList(null);
		con.setContractWorkflowList(null);
		Map<String,String> jsMap = contractManager.updateContract(con,contract.getContractType());
		updFlag = jsMap.get("updFlag");
		workFlowNum = jsMap.get("workflowId");
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * Description:修改特殊客户月发月送合同运费折扣操作<br />
	 * </p>
	 * @author 潘光均
	 * @version 0.1 2013-3-13
	 * @return SUCCESS
	 */
	public String modifyMonthSendRate(){
		Contract con = ContractUtil.changeContractDetailViewToContract(contract);
		contractManager.updateMonthSendRate(con);
		return SUCCESS;
	}
	/**
	 * 
	 * <p>n
	 * Description:修改合同操作<br/>
	 * </p>
	 * @author 李学兴
	 * @version 0.1 2012-7-04
	 * @return SUCCESS
	 * isChecked 校验审批中 结果,true不是审批中，false审批中
	 */
	public String acquireNewContract(){
		Contract contractInfo = contractManager.getNewContract(memberId);
		if (null==contractInfo) {
			return SUCCESS;
		}
		contractInfo.setBeforeContractNum(contractInfo.getContractNum());
		contractInfo.setContractNum("");
		contractView = ContractUtil.createContractView(contractInfo);
		return SUCCESS;
	}
	/**
	 * 
	 @Discript 修改合同月结天数操作(特殊)
	 @author  唐亮
	 @date 2013-1-9
	 @return String
	 */
	public String updateSpecialContractMonthEnd(){
		contractManager.updateContractDebtDaysById(contractId, debtDays);
		return SUCCESS;
	}
	/**
	 * 修改合同月结天数操作(通用)
	 @Discript 
	 @author  唐亮
	 @date 2013-1-9
	 @return String
	 */
	public String updateCommonContractMonthEnd(){
		contractManager.updateCommonContractMonthEndDayById(contractId, debtDays);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:点击修改按钮查看月发月送合同详情<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.2 2012-4-12
	 * @return
	 * String
	 */
	public String viewUpdateMonthSendDetail(){
		Contract contract = contractManager.getUpdateMonthSendInfo(contractId);
		contractView = ContractUtil.createContractView(contract);
		member.setContactList(this.memberManager.getContactsByMemberId(contract.getMember().getId()));
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * Description:判断是否超过近三个月的发货金额最高月的二倍<br />
	 * </p>
	 * @author 李学兴
	 * @version 0.1 2012-9-21
	 * @return SUCCESS
	 * checked true:结款额度小于最大发货量的2倍，反之则为false 
	 */
//	public String isNotOverMax3Amount(){
//		checked = false;//contractManager.checkAmountByCustId(memberId,amount);
//		return SUCCESS;
//	}
	/**
	 * 
	 * <p>
	 * Description:检测新建合同的生效 失效时间<br />
	 *  若此客户有生效的合同，且新建合同的生效日期小于原合同的失效日期，新建合同失效日期不等于原合同失效日期的
	 *  则checked 为false，并下发原合同信息
	 *  否则 checked 为 true，不下发合同信息
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-3-20
	 * @return
	 * String
	 */
	public String acquireCreateDate(){
		contract.setCustId(custNumber);//此此段传入的为客户编码
		contract.setContractBeginDate(contractBeginDate);
		contract.setContractendDate(contractEndDate);
		Contract contractInfo = contractManager.checkCreateContractDate(contract);
		if(!ValidateUtil.objectIsEmpty(contractInfo)){
	       checked=false;
	       contractBeginDate = contractInfo.getContractBeginDate();
	       contractEndDate = contractInfo.getContractendDate();
		}else{
			checked = true;
		}
		return SUCCESS;
	}
	/**
	 * Description:催款部门设置<br />
	 * @author 李国文
	 * @version 0.1 2013-5-8
	 * @return String
	 */
	public String modifyDuningDept() {
		contractManager.modifyDuningDept(dunningDeptCode, ifForeignGoods, contractId);
		return SUCCESS;
	}
	
	/**
	 * @Description:价格版本时间设置<br />
	 * @author CoCo
	 * @version 0.1 2013-8-7
	 * @return String
	 */
	public String modifyPriceVersionDate(){
		contractManager.modifyPriceVersionDate(priceVersionDate,exPriceVersionDate, contractId);
		return SUCCESS;
	}
	public String batchDeleteUneffectContract(){
		contractManager.batchDeleteUneffectContract(contractIds);
		return SUCCESS;
	}
	public void setContractList(List<ContractDetailView> contractList) {
		this.contractList = contractList;
	}
	public ContractDetailView getContract() {
		return contract;
	}
	public void setContract(ContractDetailView contract) {
		this.contract = contract;
	}
	public int getStart() {
		return start;
	}
	public int getLimit() {
		return limit;
	}
	public String getContractId() {
		return contractId;
	}
	public List<ApproveData> getApproveDataList() {
		return approveDataList;
	}
	public String getDeptId() {
		return deptId;
	}
	public Preferential getAddPreferential() {
		return addPreferential;
	}
	public List<FileInfo> getFileInfoList() {
		return fileInfoList;
	}
	public FileInfo getFileInfo() {
		return fileInfo;
	}
	public void setContractView(ContractView contractView) {
		this.contractView = contractView;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public ContractCondition getContractCondition() {
		return contractCondition;
	}
	public void setContractCondition(ContractCondition contractCondition) {
		this.contractCondition = contractCondition;
	}
	public void setContractManager(IContractManager contractManager) {
		this.contractManager = contractManager;
	}
	public ContractView getContractView() {
		return contractView;
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
	public String getWorkFlowNum() {
		return workFlowNum;
	}
	public void setWorkFlowNum(String workFlowNum) {
		this.workFlowNum = workFlowNum;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public void setApproveDataList(List<ApproveData> approveDataList) {
		this.approveDataList = approveDataList;
	}
	public void setAddPreferential(Preferential addPreferential) {
		this.addPreferential = addPreferential;
	}
	public void setFileInfoList(List<FileInfo> fileInfoList) {
		this.fileInfoList = fileInfoList;
	}
	public void setFileInfo(FileInfo fileInfo) {
		this.fileInfo = fileInfo;
	}
	public boolean isChecked() {
		return checked;
	}
	public Member getMember() {
		return member;
	}
	public void setMemberManager(IMemberManager memberManager) {
		this.memberManager = memberManager;
	}
	public void setHandType(String handType) {
		this.handType = handType;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public void setContractIds(List<String> contractIds) {
		this.contractIds = contractIds;
	}
	public List<ContractDetailView> getContractList() {
		return contractList;
	}
//	public void setAmount(Double amount) {
//		this.amount = amount;
//	}
	/**
	 * <p>
	 * Description:contractSubjectList<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-3-18
	 */
	public List<ContractDetailView> getContractSubjectList() {
		return contractSubjectList;
	}
	/**
	 * <p>
	 * Description:contractSubjectList<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-3-18
	 */
	public void setContractSubjectList(List<ContractDetailView> contractSubjectList) {
		this.contractSubjectList = contractSubjectList;
	}


	/**
	 * <p>
	 * Description:baseDataManager<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-3-15
	 */
	public void setBaseDataManager(IBaseDataManager baseDataManager) {
		this.baseDataManager = baseDataManager;
	}
	/**
	 * <p>
	 * Description:contractEndDate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-3-20
	 */
	public Date getContractEndDate() {
		return contractEndDate;
	}
	/**
	 * <p>
	 * Description:contractEndDate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-3-20
	 */
	public void setContractEndDate(Date contractEndDate) {
		this.contractEndDate = contractEndDate;
	}
	/**
	 * <p>
	 * Description:contractBeginDate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-3-20
	 */
	public Date getContractBeginDate() {
		return contractBeginDate;
	}
	/**
	 * <p>
	 * Description:contractBeginDate<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-3-20
	 */
	public void setContractBeginDate(Date contractBeginDate) {
		this.contractBeginDate = contractBeginDate;
	}
	/**
	 * <p>
	 * Description:custNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-3-20
	 */
	public String getCustNumber() {
		return custNumber;
	}
	/**
	 * <p>
	 * Description:custNumber<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-3-20
	 */
	public void setCustNumber(String custNumber) {
		this.custNumber = custNumber;
	}
	public List<ContractMonthendDay> getContractMonthendDaysList() {
		return contractMonthendDaysList;
	}
	public void setContractMonthendDaysList(
			List<ContractMonthendDay> contractMonthendDaysList) {
		this.contractMonthendDaysList = contractMonthendDaysList;
	}
	public int getDebtDays() {
		return debtDays;
	}
	public void setDebtDays(int debtDays) {
		this.debtDays = debtDays;
	}
	/**
	 * Description:ifForeignGoods<br />
	 * @author CoCo
	 * @version 0.1 2013-5-8
	 */
	public boolean isIfForeignGoods() {
		return ifForeignGoods;
	}
	/**
	 * Description:ifForeignGoods<br />
	 * @author CoCo
	 * @version 0.1 2013-5-8
	 */
	public void setIfForeignGoods(boolean ifForeignGoods) {
		this.ifForeignGoods = ifForeignGoods;
	}
	/**
	 * Description:dunningDeptCode<br />
	 * @author CoCo
	 * @version 0.1 2013-5-8
	 */
	public String getDunningDeptCode() {
		return dunningDeptCode;
	}
	/**
	 * Description:dunningDeptCode<br />
	 * @author CoCo
	 * @version 0.1 2013-5-8
	 */
	public void setDunningDeptCode(String dunningDeptCode) {
		this.dunningDeptCode = dunningDeptCode;
	}
	/**
	 * Description:priceVersionDate<br />
	 * @author CoCo
	 * @version 0.1 2013-8-6
	 */
	public Date getPriceVersionDate() {
		return priceVersionDate;
	}
	/**
	 * Description:priceVersionDate<br />
	 * @author CoCo
	 * @version 0.1 2013-8-6
	 */
	public void setPriceVersionDate(Date priceVersionDate) {
		this.priceVersionDate = priceVersionDate;
	}
	/**
	 * @param exPriceVersionDate : set the property exPriceVersionDate.
	 */
	public void setExPriceVersionDate(Date exPriceVersionDate) {
		this.exPriceVersionDate = exPriceVersionDate;
	}
	/**
	 *@author chenaichun
	 * @date 2013-12-4 下午4:06:46 
	 *@return the updFlag
	 */
	public String getUpdFlag() {
		return updFlag;
	}
	/**
	 *@author chenaichun
	 * @date 2013-12-4 下午4:06:46 
	 * @param updFlag the updFlag to set
	 */
	public void setUpdFlag(String updFlag) {
		this.updFlag = updFlag;
	}
	
}
