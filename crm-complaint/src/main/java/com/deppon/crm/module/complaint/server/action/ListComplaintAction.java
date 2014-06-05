/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ComplaintReportAction.java
 * @package com.deppon.crm.module.complaint.server.action 
 * @author Administrator
 * @version 0.1 2012-4-10
 */
package com.deppon.crm.module.complaint.server.action;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.deppon.crm.module.authorization.shared.domain.Function;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.complaint.server.manager.IComplaintManager;
import com.deppon.crm.module.complaint.server.manager.impl.ComplaintManager;
import com.deppon.crm.module.complaint.server.util.Constants;
import com.deppon.crm.module.complaint.shared.domain.BasciLevel;
import com.deppon.crm.module.complaint.shared.domain.BasciLevelSearchCondition;
import com.deppon.crm.module.complaint.shared.domain.BasciLevelView;
import com.deppon.crm.module.complaint.shared.domain.BasicInfo;
import com.deppon.crm.module.complaint.shared.domain.BasicSearchCondition;
import com.deppon.crm.module.complaint.shared.domain.Bulletin;
import com.deppon.crm.module.complaint.shared.domain.Complaint;
import com.deppon.crm.module.complaint.shared.domain.ComplaintSearchCondition;
import com.deppon.crm.module.complaint.shared.domain.FeedBackReasion;
import com.deppon.crm.module.complaint.shared.domain.ProcResult;
import com.deppon.crm.module.complaint.shared.domain.Result;
import com.deppon.crm.module.complaint.shared.domain.TaskResult;
import com.deppon.crm.module.complaint.shared.domain.WorkOrder;
import com.deppon.crm.module.complaint.shared.exception.ComplaintException;
import com.deppon.crm.module.complaint.shared.exception.ComplaintExceptionType;
import com.deppon.crm.module.customer.server.manager.IAlterMemberManager;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.MemberCondition;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.cache.CacheManager;
import com.deppon.foss.framework.cache.ICache;
import com.deppon.foss.framework.entity.IFunction;
import com.deppon.foss.framework.entity.IRole;
import com.deppon.foss.framework.entity.IUser;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;
/**   
 * <p>
 * 工单上报管理<br />
 * </p>
 * @title ComplaintReportAction.java
 * @package com.deppon.crm.module.complaint.server.action 
 * @author justin.xu
 * @version 0.1 2012-4-10
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ListComplaintAction extends AbstractAction {
	//工单manager
	private IComplaintManager complaintManager;
	//工单上报查询条件
	private ComplaintSearchCondition complaintSearchCondition=new ComplaintSearchCondition();
	private int start;
	//前台传入的条件查询潜客信息的limit参数（用于分页）
	private int limit;
	//传入前台的通过条件查询潜客结果总数
	private Long totalCount;
	//最新工单上报列表
	private List<Complaint> complaintList;
	//操作记录集合    -  调查建议集合
	private List<WorkOrder> workOrderList;
	//接货人
	private Member receiverMember;
	//发货人
	private Member shipperMember;
	//工单上报
	private Complaint complaint;
	//重复投拆码
	private String repeatCode;
	//上报类型
	private String reportType;
	//部门名称
	private String deptName;
	//基础资料处理结果集合
	List<ProcResult> procResultList=null;
	//前台传入处理参数
	private Map<String,Object> parameterMap=null;
	//业务范围集合
	private List<BasciLevel>  basciLevelList = null;
	private String basciLevelId ;
	//部门工单管理集合
	private List<TaskResult> taskResultList=null;
	//部门集合
	private List<Department> departmentList=null;
	//通知对象集合
	private List<Bulletin> bulletinList=null;
	private List<Employee> employeeList=null;
	private List<FeedBackReasion> feedbackList=null;	
	//返回查询list结果
	private List searchResultList=null;
	private Result result;//处理结果对象
	//投诉
	private List<BasciLevelView> complaintBasciLevelList;	
	//异常
	private List<BasciLevelView> exceptionBasciLevelList;
	//业务范围
	private List<BasciLevel> listBasciLevel;
	private BasciLevelSearchCondition exceptionCondition;
	private BasciLevelSearchCondition complaintCondition;
	//基础资料
	private BasciLevel basciLevel;
	//查询修改处理结果
	private List<ProcResult> procResult;
	private boolean defaultSearchVerify;//是否默认查询工单
	//查询客户member
	private IAlterMemberManager alterMemberManager;
	//Member对象
	private Member member;
	//搜索member条件
	private MemberCondition searchCustCondition;
	// 处理结果ID，根据ID查找相关处理信息
	private String resultId;
	//处理结果基础资料列表
	private List<BasicInfo> basicInfoList;
	//处理结果基础资料查询主界面输入的查询条件
	private BasicSearchCondition bsc;
	//记录返回记录的数量
	private String recordCount;
	//投诉业务项列表
	private List<BasicInfo> complaintBusinessItemList;
	//异常业务项列表
	private List<BasicInfo> unusualBusinessItemList;
	//业务范围ID
	private String busScopeId; 
	//业务类型
	private String businessModel;
	//是否第一次加载
	private Boolean firstLoad;
	
	
	public Boolean getFirstLoad() {
		return firstLoad;
	}

	public void setFirstLoad(Boolean firstLoad) {
		this.firstLoad = firstLoad;
	}

	public String getBusinessModel() {
		return businessModel;
	}

	public void setBusinessModel(String businessModel) {
		this.businessModel = businessModel;
	}

	/**
	 * 查询投诉
	 * @return
	 */
	@JSON
	public String searchComplaintBasciLevel(){
		complaintBasciLevelList=complaintManager.searchComplaintLevelDataList(complaintCondition);
		return SUCCESS;
	}
	
	/**
	 * 查询异常
	 * @return
	 */
	@JSON
	public String searchExceptionBasciLevel(){
		exceptionBasciLevelList=complaintManager.searchExceptionLevelDataList(exceptionCondition);
		return SUCCESS;
	}
	
	public boolean getDefaultSearchVerify() {
		return defaultSearchVerify;
	}
	public void setDefaultSearchVerify(boolean defaultSearchVerify) {
		this.defaultSearchVerify = defaultSearchVerify;
	}
	/**
	 * 查询业务范围
	 * @return
	 */
	@JSON
	public String searchBasciLevel(){
		listBasciLevel=complaintManager.getFBasciLevlel(exceptionCondition);
		return SUCCESS;
	}
	
	/**
	 * 查询修改
	 * @return
	 */
	@JSON
	public String searchFoundationData(){
		Map<String,Object> map=complaintManager.searchFoundationData(basciLevel.getFid(), basciLevel.getType());
		procResult=(List<ProcResult>) map.get("ProcResult");
		return SUCCESS;
	}
	
	//投诉工单ID，根据工单ID查询退回原因时需要使用
	private String complaintId;	

	private List<Complaint> waitComplaints=null;
	
	//后台传入前台的查询结果map
	private Map<String,Object> searchResultMap=null;

	/**
	 * <p>
	 * Description:进入工单上报主界面,默认列表显示最新上报投拆，按创建时间降序<br />
	 * </p>
	 * @author justin.xu
	 * @version 0.1 2012-4-10
	 * @return
	 * String
	 */
	public String searchReportedComplaints(){
		// 分页
		complaintSearchCondition.setStart(start);
		complaintSearchCondition.setLimit(limit);
		//如果fid不为空，则不查询当前fid的记录	
		complaintSearchCondition.setToBeBanging("YES");	
		// 根据查询条件查询工单
		Map map=complaintManager.searchReportedComplaints(complaintSearchCondition);
		complaintList=(List<Complaint>)map.get("complaintList");
		totalCount=Long.valueOf(map.get("complaintCount").toString());
		return SUCCESS;
	}
	
	/**
	 * <p>
	 * Description:输入订单号或运单号带出客户信息<br />
	 * </p>
	 * @author justin.xu
	 * @version 0.1 2012-4-14
	 *  @return
	 * String
	 */
	public String searchMembersByOWNum(){
		// 输入运单号或订单号 带出客户信息
		Map map=complaintManager.searchMembersByOWNum(complaintSearchCondition.getBill(),complaintSearchCondition);
/*		complaintList=(List<Complaint>)map.get("complaintList");
		totalCount=(Integer)map.get("count");*/
		receiverMember=(Member)map.get("receiverMember");
		shipperMember=(Member)map.get("shipperMember");
		businessModel=(String)map.get("businessModel");
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:输入手机号带出客户信息<br />
	 * </p>
	 * @author justin.xu
	 * @version 0.1 2012-4-14
	 * @return
	 * String
	 */
	public String searchContractByPhone(){
		// 通过电话得到联系人
		Map map=complaintManager.getContractByPhone(complaintSearchCondition.getPhone(),complaintSearchCondition);
		shipperMember=(Member)map.get("shipperMember");
		return SUCCESS;
	}
	
	/**
	 * <p>
	 * Description:获得重复投诉码<br />
	 * </p>
	 * @author just.xu
	 * @version 0.1 2012-4-16
	 * @return
	 * String
	 */
	public String searchRepeatedCode(){
		// 得到重复工单码
		Map map=complaintManager.getRepeatedCode(complaint.getBill(), complaint.getFid().toString(), complaint.getReporttype());
		repeatCode=(String)map.get("repeateCode");
		return SUCCESS;
	}
	
	/**
	 * <p>
	 * Description:工单查询集合<br />
	 * </p>
	 * @author hpf
	 * @version 0.2 2012-4-10
	 * @return
	 * String
	 */
	public String searchCustomerComplaints(){
		// 分页
		complaintSearchCondition.setStart(start);
		complaintSearchCondition.setLimit(limit);
		// 根据查询条件查询工单
		Map map=complaintManager.searchComplaints(complaintSearchCondition);
		complaintList=(List<Complaint>)map.get("complaintList");
		totalCount=Long.valueOf(map.get("complaintCount").toString());
		return SUCCESS;
	}
	
	/**
	 * <p>
	 * Description:工单 之  调查建议集合<br />
	 * </p>
	 * @author hpf
	 * @version 0.2 2012-4-10
	 * @return
	 * String
	 */
	public String searchSurveySuggestList(){
		// 更具单据ID查询所有的调查意见
		this.workOrderList = complaintManager.getInvestigateSuggestionByCompId(new BigDecimal(this.complaintId));
		return SUCCESS;
	}
	
	/**
	 * <p>
	 * Description:查询基础资料处理结果根据层级编号<br />
	 * </p>
	 * @author hpf
	 * @version 0.2 2012-4-10
	 * @return
	 * String
	 */
	public String searchProcresultsByLevelId(){
		// 查询基础资料处理结果根据层级编号
		this.procResultList = complaintManager.searchProcresultsByLevelId(new BigDecimal(this.basciLevelId));
		return SUCCESS;
	}
	
	
	/**
	 * <p>
	 * Description:工单处理集合<br />
	 * </p>
	 * @author hpf
	 * @version 0.2 2012-4-27
	 * @return
	 * String
	 */
	public String searchProcessComplaints(){
		// 分页
		complaintSearchCondition.setStart(start);
		complaintSearchCondition.setLimit(limit);
		complaintSearchCondition.setIfInsert(1);
		complaintSearchCondition.setDealstatus(Constants.COMPLAINT_ACCESS_STATUS);
		complaintSearchCondition.setProstatus(Constants.COMPLAINT_STATUS_PENDING);
		// 获取用户信息
		User currentUser = (User) (UserContext.getCurrentUser());
		// 查询接入的工单
		Map map=complaintManager.searchAccessComplaints(complaintSearchCondition,currentUser);
		complaintList=(List<Complaint>)map.get("complaintList");
		totalCount=Long.valueOf(map.get("complaintCount").toString());
		return SUCCESS;
	}
	/**
	 * <p>
	 * Description:工单查询 重复工单集合<br />
	 * recomcode :重复工单码
	 * </p>
	 * @author hpf
	 * @version 0.2 2012-4-27
	 * @return
	 * String
	 */
	public String searchRepeatComplaintListByRecomcode(){
		// 分页
		complaintSearchCondition.setStart(start);
		complaintSearchCondition.setLimit(limit);
		// 根据查询条件查询工单
		Map map=complaintManager.searchRepeatComplaints(complaintSearchCondition);
		complaintList=(List<Complaint>)map.get("complaintList");
		totalCount=Long.valueOf(map.get("complaintCount").toString());
		return SUCCESS;
	}
	
	/**
	 * <p>
	 * Description:搜索当前用户最新上报退回投拆<br />
	 * </p>
	 * @author justin.xu
	 * @version 0.1 2012-4-18
	 * @return
	 * String
	 */
	public String searchReturnedComplaints(){
		// 分页
		complaintSearchCondition.setStart(start);
		complaintSearchCondition.setLimit(limit);
		// 获取用户信息
		User currentUser = (User) (UserContext.getCurrentUser());
		// 查询上报退回工单
		Map map=complaintManager.searchReturnedComplaints(complaintSearchCondition,currentUser);
		complaintList=(List<Complaint>)map.get("complaintList");
		totalCount=Long.valueOf(map.get("count").toString());
		return SUCCESS;
	}
	
	
	/**
	 * <p>
	 * Description:工单处理-根据上报类型查询最近优先顺序最高的三条工单<br />
	 * reportType 上报类型- r 投诉 e 异常
	 * </p>
	 * @author hpf
	 * @version 0.2 2012-4-19
	 * @return
	 * String
	 */
	@JSON
	public String searchComplaints3ByReportType(){
		this.parameterMap = new HashMap<String,Object>();
		this.parameterMap.put("reportType", reportType); // 上报类型
		this.parameterMap.put("sessionUser", UserContext.getCurrentUser()); // 当前session用户
		this.parameterMap.put("firstLoad", firstLoad);
		this.parameterMap.put("businessModel", businessModel);
		complaintList = complaintManager.getRecently3ComplaintsByReportType(parameterMap);
		return SUCCESS;
	}
	
	/**
	 * <p>
	 * Description:搜索部门工单管理列表<br />
	 * </p>
	 * @author justin.xu
	 * @version 0.1 2012-4-20
	 * @return
	 * String
	 */
	public String searchTaskComplaints(){
		// 分页
		complaintSearchCondition.setStart(start);
		complaintSearchCondition.setLimit(limit);
		// 获得用户信息
		User currentUser = (User) (UserContext.getCurrentUser());
		complaintSearchCondition.setUser(currentUser);
		// 搜索部门工单管理列表
		Map map=complaintManager.searchTaskComplaints(complaintSearchCondition);
		taskResultList=(List<TaskResult>)map.get("taskResultList");
		totalCount=Long.valueOf(map.get("taskResultCount").toString());
		return SUCCESS;
	}
	
	
	
	/**
	 * <p>
	 * Description:工单处理-查询业务范围集合<br />
	 * </p>
	 * @author hpf
	 * @version 0.2 2012-4-23
	 * @return
	 * String
	 */
	public String searchBusinessScopeList(){
		//feedback,basciLevelId
		// 查询业务范围
		this.basciLevelList = complaintManager.getFBasciLevlel(exceptionCondition);
		return SUCCESS;
	}
	
	/**
	 * <p>
	 * Description:工单处理-查询业务类型集合<br />
	 * </p>
	 * @author hpf
	 * @version 0.2 2012-4-23
	 * @return
	 * String
	 */
	public String searchBusinessTypeList(){
		// 业务类型查询
		this.basciLevelList = complaintManager.getChildBasciLevel(exceptionCondition);
		return SUCCESS;
	}
	
	/**
	 * <p>
	 * Description:搜索工单回访管理集合 <br />
	 * </p>
	 * @author Administrator
	 * @version 0.1 2012-4-24
	 * @return
	 * String
	 */
	public String searchVerifyComplaints(){
		// 获取用户信息
		User currentUser = (User) (UserContext.getCurrentUser());
		complaintSearchCondition.setStart(start);
		complaintSearchCondition.setLimit(limit);
		// 工单回访查询
		Map map=complaintManager.searchVerfiyComplaint(complaintSearchCondition, currentUser,defaultSearchVerify);
		complaintList=(List<Complaint>)map.get("compList");
		totalCount=Long.valueOf(map.get("count").toString());
		return SUCCESS;
	}
	/**
	 * <p>
	 * Description:工单处理-任务部门集合<br />
	 * </p>
	 * @author hpf
	 * @version 0.2 2012-4-25
	 * @return
	 * String
	 */
	public String searchDepartmentList(){
		this.departmentList = this.complaintManager.getAllDepartment(deptName);
		return SUCCESS;
	}
	/**
	 * <p>
	 * Description:工单处理集合 count<br />
	 * </p>
	 * @author hpf
	 * @version 0.2 2012-4-28
	 * @return
	 * String
	 */
	public String count_pendingComplaint(){
		complaintSearchCondition.setProstatus(Constants.COMPLAINT_STATUS_PENDING);
		complaintSearchCondition.setSearchLock(1);
		// 获取工单处理列表总数
		int count_pendingComplaint = this.complaintManager.getPendingComplaintCount(complaintSearchCondition);
		complaintSearchCondition.setBusinessModel(Constants.BUSINESS_PMODEL_EXPRESS);
		int count_pendingComplaint_express=this.complaintManager.getPendingComplaintCount(complaintSearchCondition);
		complaintSearchCondition.setBusinessModel(Constants.BUSINESS_MODEL_UNEXPRESS);
		int count_pendingComplaint_unExpress=this.complaintManager.getPendingComplaintCount(complaintSearchCondition);
		this.searchResultMap = new HashMap<String,Object>();
		this.searchResultMap.put("count",count_pendingComplaint);
		this.searchResultMap.put("count_express", count_pendingComplaint_express);
		this.searchResultMap.put("count_unExpress", count_pendingComplaint_unExpress);
		return SUCCESS;
	}
	
	
	/**
	 * <p>
	 * Description:工单处理-通知对象集合<br />
	 * deptId:部门编号
	 * </p>
	 * @author hpf
	 * @version 0.2 2012-4-25
	 * @return
	 * String
	 */
	public String searchEmployeeListByDeptId(){
		// 获取部门id
		String deptId = (String)parameterMap.get("deptId");
		// 根据部门ID获取部门下所有员工列表
		this.employeeList = this.complaintManager.getAllEmployeesByDepartmentId(deptId);
		return SUCCESS;
	}
	
	/**
	 * 根据投诉单号查询退回原因
	 * @return
	 */
	public String searchFeedbackReasonByComplaintId(){
		// 通过compid查退回原因记录列表
		feedbackList=complaintManager.searchFeedBackReasionByCompId(new BigDecimal(complaintId));
		return SUCCESS;
	}
	
	
	/**
	 * <p>
	 * Description:查询退回记录集合 通过工单编号<br />
	 * compId:工单编号
	 * </p>
	 * @author hpf
	 * @version 0.2 2012-4-28
	 * @return
	 * String
	 */
	public String searchReturnedRecordListByCompId(){
		// 工单编号
		int compId = this.complaintSearchCondition.getFid();
		// 查询退回记录集合 通过工单编号
		this.searchResultList = this.complaintManager.searchReturnedRecordListByCompId(new BigDecimal(compId));
		return SUCCESS;
	}
	/**
	 * <p>
	 * Description:查询通报对象集合  通过工单编号<br />
	 * compId:工单编号
	 * </p>
	 * @author hpf
	 * @version 0.2 2012-5-3
	 * @return
	 * String
	 */
	public String searchBulletinListToProByCompId(){
		// 工单编号
		int compId = this.complaintSearchCondition.getFid();
		//根据工单ID获取该工单相关的通知对象列表
		this.bulletinList = this.complaintManager.searchBulletinToProcByCompId(new BigDecimal(compId));
		return SUCCESS;
	}
	/**
	 * <p>
	 * Description:查询通报对象集合  通过工单编号<br />
	 * compId:工单编号
	 * </p>
	 * @author hpf
	 * @version 0.2 2012-5-3
	 * @return
	 * String
	 */
	public String searchBulletinListByCompId(){
		// 工单编号
		int compId = this.complaintSearchCondition.getFid();
		// 根据工单ID获取该工单相关的通知对象列表
		this.bulletinList = this.complaintManager.searchBulletinByCompId(new BigDecimal(compId));
		return SUCCESS;
	}
	/**
	 * <p>
	 * Description:查询处理结果集合 通过工单编号<br />
	 * compId:工单编号
	 * </p>
	 * @author hpf
	 * @version 0.2 2012-4-28
	 * @return
	 * String
	 */
	public String searchProcessResultListByCompId(){
		int compId = this.complaintSearchCondition.getFid();
		this.searchResultList = this.complaintManager.searchProcessResultListByCompId(new BigDecimal(compId));
		return SUCCESS;
	}
	
	/**
	 * <p>
	 * Description:查询处理结果集合 通过工单编号(被部门退回的处理结果)<br />
	 * compId:工单编号
	 * </p>
	 * @author hpf
	 * @version 0.2 2012-5-24
	 * @return
	 * String
	 */
	public String searchReturnResultListByCompId(){
		int compId = this.complaintSearchCondition.getFid();
		this.searchResultList = this.complaintManager.searchReturnResultInfoByCompId(new BigDecimal(compId));
		return SUCCESS;
	}
	
	/**
	 * 搜索待办工单
	 * @return
	 */
	public String searchWaitComplaints(){
		// 查询当前用户待办工单
		Map<String,Object> complaintsMap=complaintManager.searchWaitProccesComplaint((User) (UserContext.getCurrentUser()), start, limit);
		waitComplaints=(List<Complaint>)complaintsMap.get(ComplaintManager.WAIT_COMPLAINT_LIST_KEY);
		totalCount=Long.valueOf(complaintsMap.get(ComplaintManager.WAIT_COMPLAINT_LIST_COUNT_KEY).toString());
		return SUCCESS;
	}
	
	/**
	 * 根据处理结果id搜索处理结果
	 * @return
	 */
	public String inquireResultById(){
	// 根据Id查询任务部门处理结果
	 result=complaintManager.getResultById(new BigDecimal(complaintSearchCondition.getDeptId()));
		return SUCCESS;
	}
	/**
	 * <p>
	 * Description:根据条件查询Member对象)<br />
	 * </p>
	 * @author justin.xu
	 * @return
	 * String
	 */
	public String inquireMemberByCondition(){
		member=alterMemberManager.getMemberAllById(searchCustCondition);
		return SUCCESS;
	}
	/**
	 * 进入工单上报页面
	 * @return
	 */
	public String complaintReportIndex(){
		IUser user = UserContext.getCurrentUser();
		//得到所有菜单
		ICache<String, IFunction> functionCache = CacheManager.getInstance().getCache(IFunction.class.getName());
		//工单上报id
		String reportFid=null;
		for (IFunction func : functionCache.get().values()) {
			Function function = (Function) func;
			if(null!=function.getFunctionCode() && function.getFunctionCode().equals("01006001")){//工单上报菜单编码
				reportFid=function.getId();
				break;
			}
		}
		//得到用户菜单id集合
		ICache<String, IRole> roleCache = CacheManager.getInstance().getCache(IRole.class.getName());
		Set<String> functionIds = new HashSet<String>();
		for(String roleId : ((User) user).getRoleids()){
			IRole role = roleCache.get().get(roleId);
			functionIds.addAll(role.getFunctionIds());
		}
		//判断是否有此菜单
		if(null!=reportFid && !"".equals(reportFid)){
			if(functionIds.contains(reportFid)){
				return SUCCESS;
			}
		}
		return ERROR;
	}
	
	
	/**
	 * 校验工单接入是否过期
	 * @return
	 */
	public String validateComplaintAccessExpired(){
		complaintManager.isComplaintAccessExpired(complaintId, (User) (UserContext.getCurrentUser()));
		return SUCCESS;
	}

	/**
	 * 校验反馈登记是否过期
	 * @return
	 */
	public String validateFeedbackOverTime(){
		complaintManager.isComplaintFeedbackOverTime(resultId);
		return SUCCESS;
	}

	/**
	 * 新处理结果基础资料：查询主界面，返回基础资料查询结果列表
	 * xiaohongye
	 * @return
	 */
	@JSON
	public String searchBasicInfo(){
		Map<String, Object> map = complaintManager.searchBasicInfo(bsc, start, limit);
		basicInfoList=(List<BasicInfo>)map.get("basicInfos");
		if(basicInfoList == null || basicInfoList.size() == 0){
			recordCount = String.valueOf(0);
		}
		else{
			recordCount = String.valueOf(basicInfoList.size());
		}
		totalCount=Long.valueOf(map.get("totalCount").toString());
		return SUCCESS;
	}

	/**
	 *业务项设置页面：初始化业务项列表
	 * xiaohongye
	 * @return
	 */
	@JSON
	public String showBusItemByType(){
		Map<String,List<BasicInfo>> map = complaintManager.showBusItemByType();
		complaintBusinessItemList=(List<BasicInfo>)map.get("complaints");
		unusualBusinessItemList=(List<BasicInfo>)map.get("unusuals");
		return SUCCESS;
	}
	
	/**
	 *工单处理界面：根据业务范围id，查询业务类型列表
	 * xiaohongye
	 * @return
	 */
	@JSON
	public String searchBusTypeByBusScope(){
		basicInfoList = complaintManager.searchBusTypeByBusScope(busScopeId);
		return SUCCESS;
	}
	/**
	 *工单处理界面：修改来自官网的工单业务类型
	 * 杨伟
	 * @return
	 */
	@JSON
	public String changeBusinessModel(){
		parameterMap=new HashMap<String, Object>();
		parameterMap.put("changeSuccess", complaintManager.changeBusinessModel(complaint));
		return SUCCESS;
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
		if(null==totalCount || 0==totalCount){
			return 1L;
		}
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public ComplaintSearchCondition getComplaintSearchCondition() {
		return complaintSearchCondition;
	}

	public void setComplaintSearchCondition(
			ComplaintSearchCondition complaintSearchCondition) {
		this.complaintSearchCondition = complaintSearchCondition;
	}

	public void setComplaintManager(IComplaintManager complaintManager) {
		this.complaintManager = complaintManager;
	}

	public List<Complaint> getComplaintList() {
		return complaintList;
	}

	public void setComplaintList(List<Complaint> complaintList) {
		this.complaintList = complaintList;
	}


	public String getRepeatCode() {
		return repeatCode;
	}

	public void setRepeatCode(String repeatCode) {
		this.repeatCode = repeatCode;
	}

	public Member getReceiverMember() {
		return receiverMember;
	}

	public void setReceiverMember(Member receiverMember) {
		this.receiverMember = receiverMember;
	}

	public Member getShipperMember() {
		return shipperMember;
	}

	public void setShipperMember(Member shipperMember) {
		this.shipperMember = shipperMember;
	}

	public Complaint getComplaint() {
		return complaint;
	}

	public void setComplaint(Complaint complaint) {
		this.complaint = complaint;
	}
	
	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	
	public Map<String, Object> getParameterMap() {
		return parameterMap;
	}

	public void setParameterMap(Map<String, Object> parameterMap) {
		this.parameterMap = parameterMap;
	}

	public List<BasciLevel> getBasciLevelList() {
		return basciLevelList;
	}
	
	public void setBasciLevelList(List<BasciLevel> basciLevelList) {
		this.basciLevelList = basciLevelList;
	}

	public String getBasciLevelId() {
		return basciLevelId;
	}

	public void setBasciLevelId(String basciLevelId) {
		this.basciLevelId = basciLevelId;
	}
	
	public List<TaskResult> getTaskResultList() {
		return taskResultList;
	}

	public void setTaskResultList(List<TaskResult> taskResultList) {
		this.taskResultList = taskResultList;
	}

	public List<Department> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<Department> departmentList) {
		this.departmentList = departmentList;
	}

	public List<Bulletin> getBulletinList() {
		return bulletinList;
	}

	public void setBulletinList(List<Bulletin> bulletinList) {
		this.bulletinList = bulletinList;
	}

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}
	
	public String getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(String compliantId) {
		this.complaintId = compliantId;


	}

	public List<Complaint> getWaitComplaints() {
		return waitComplaints;
	}

	public void setWaitComplaints(List<Complaint> waitComplaints) {
		this.waitComplaints = waitComplaints;
	}	

	public Map<String, Object> getSearchResultMap() {
		return searchResultMap;
	}

	public void setSearchResultMap(Map<String, Object> searchResultMap) {
		this.searchResultMap = searchResultMap;
	}
	public List getSearchResultList() {
		return searchResultList;
	}
	public void setSearchResultList(List searchResultList) {
		this.searchResultList = searchResultList;
	}
	public List<FeedBackReasion> getFeedbackList() {
		return feedbackList;
	}
	public void setFeedbackList(List<FeedBackReasion> feedbackList) {
		this.feedbackList = feedbackList;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}
	public void setAlterMemberManager(IAlterMemberManager alterMemberManager) {
		this.alterMemberManager = alterMemberManager;
	}

	public List<ProcResult> getProcResult() {
		return procResult;
	}
	public void setProcResult(List<ProcResult> procResult) {
		this.procResult = procResult;
	}
	public List<ProcResult> getProcResultList() {
		return procResultList;
	}
	public void setProcResultList(List<ProcResult> procResultList) {
		this.procResultList = procResultList;
	}
	public List<BasciLevelView> getComplaintBasciLevelList() {
		return complaintBasciLevelList;
	}
	public List<BasciLevelView> getExceptionBasciLevelList() {
		return exceptionBasciLevelList;
	}
	public List<BasciLevel> getListBasciLevel() {
		return listBasciLevel;
	}
	public BasciLevelSearchCondition getExceptionCondition() {
		return exceptionCondition;
	}
	public BasciLevelSearchCondition getComplaintCondition() {
		return complaintCondition;
	}
	public void setExceptionCondition(BasciLevelSearchCondition exceptionCondition) {
		this.exceptionCondition = exceptionCondition;
	}
	public void setComplaintCondition(BasciLevelSearchCondition complaintCondition) {
		this.complaintCondition = complaintCondition;
	}
	public BasciLevel getBasciLevel() {
		return basciLevel;
	}
	public void setBasciLevel(BasciLevel basciLevel) {
		this.basciLevel = basciLevel;
	}

	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}

	public MemberCondition getSearchCustCondition() {
		return searchCustCondition;
	}

	public void setSearchCustCondition(MemberCondition searchCustCondition) {
		this.searchCustCondition = searchCustCondition;
	}

	public List<WorkOrder> getWorkOrderList() {
		return workOrderList;
	}

	public void setWorkOrderList(List<WorkOrder> workOrderList) {
		this.workOrderList = workOrderList;
	}

	public String getResultId() {
		return resultId;
	}

	public void setResultId(String resultId) {
		this.resultId = resultId;
	}
	public BasicSearchCondition getBsc() {
		return bsc;
	}
	public void setBsc(BasicSearchCondition bsc) {
		this.bsc = bsc;
	}
	public List<BasicInfo> getBasicInfoList() {
		return basicInfoList;
	}
	
	public String getRecordCount() {
		return recordCount;
	}
	public List<BasicInfo> getComplaintBusinessItemList() {
		return complaintBusinessItemList;
	}
	public List<BasicInfo> getUnusualBusinessItemList() {
		return unusualBusinessItemList;
	}
	
	public void setBusScopeId(String busScopeId) {
		this.busScopeId = busScopeId;
	}
}
