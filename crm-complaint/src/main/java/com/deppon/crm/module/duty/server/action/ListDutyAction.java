package com.deppon.crm.module.duty.server.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.crm.module.duty.server.manager.IDutyManager;
import com.deppon.crm.module.duty.server.util.DutyConstants;
import com.deppon.crm.module.duty.shared.domain.Duty;
import com.deppon.crm.module.duty.shared.domain.DutyBulletin;
import com.deppon.crm.module.duty.shared.domain.DutyDept;
import com.deppon.crm.module.duty.shared.domain.DutyFeedback;
import com.deppon.crm.module.duty.shared.domain.DutyQueryResult;
import com.deppon.crm.module.duty.shared.domain.DutyResult;
import com.deppon.crm.module.duty.shared.domain.FeedAttach;
import com.deppon.crm.module.duty.shared.domain.InformUser;
import com.deppon.crm.module.duty.shared.domain.QueryDutyCondition;
import com.deppon.crm.module.duty.shared.domain.SearchDutyCondition;
import com.deppon.crm.module.duty.shared.domain.SearchDutyResultVO;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;

/**
 * 
 * <p>
 * Description:工单责任 action list<br />
 * </p>
 * @title ListCompltDutyAction.java
 * @package com.deppon.crm.module.complaint.server.action 
 * @author 侯斌飞
 * @version 0.1 2013-2-23
 */
public class ListDutyAction extends AbstractAction {
	/* ------------------属性声明 begin ------------------- */
	private int start;
	//前台传入的条件查询潜客信息的limit参数（用于分页）
	private int limit;
	//传入前台的通过条件查询潜客结果总数
	//当前登录用户
	private User user;
	private Long totalCount;	
	//责任 Manager
	private IDutyManager dutyManager;
	// 工单责任查询条件
	private QueryDutyCondition queryDutyCondition;
	//接入工单查询条件
	private SearchDutyCondition searchDutyCondition;
	// 工单责任查询结果集
	private List<DutyQueryResult> dutyList;
	// 工单划分-处理结果
	private List<DutyResult> dutyResultList;
	//通知对象
	private List<InformUser> informUserList;
	
	//附件集合
	private List<FeedAttach> feedAttachList;
	//责任反馈集合
	private List<SearchDutyResultVO> searchDutyResultVOList;
	//接入工单页面查询结果
	private List<Duty> receiveDutys;
	//未接入的工单数
	int unreceiveDutyNum;
	//责任ID
	private String dutyId;
	//责任反馈记录
	private List<DutyFeedback> dutyFeedbackList;
	// 责任id
	private String dutyResultId;
	// 工单责任查询结果集
	private List<DutyQueryResult> dutyDetailList;
	//部门集合
	private List<Department> departmentList;
	//工单责任特殊部门集合
	private List<DutyDept>  dutyDeptList;
	
	
	private Map<String,Integer> numMap;
	
	private Integer type;//区分快递和零担
	
	/* ------------------action方法实现区 begin ------------------- */	
	/**
	 * 
	 * <p>
	 * Description:工单责任 -查询<br />
	 * </p>
	 * @title queryDutyList.java
	 * @author 钟琼
	 * @version 0.1 2013-3-7
	 */
	@SuppressWarnings("unchecked")
	public String queryDutyList(){
		Map<String,Object> map = dutyManager.queryDutyList(queryDutyCondition,start,limit);
		dutyList = (List<DutyQueryResult>) map.get("dutyList");
		totalCount = Long.valueOf(map.get("totalCount").toString());
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * 工单责任审批查询列表<br />
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-3-11
	 * @return
	 * String
	 */
	@SuppressWarnings("unchecked")
	public String queryDutyApprovalList(){
		user = (User) UserContext.getCurrentUser();
		Map<String,Object> map = dutyManager.queryDutyApprovalList(queryDutyCondition,start,limit,user);
		dutyList = (List<DutyQueryResult>) map.get("data");
		totalCount = Long.valueOf(map.get("totalCount").toString());
		return SUCCESS;
	}
	
	/**
	 * <p>
	 * Description: 计算未接入的工单责任数<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-3-9
	 * @param bsc
	 * @param rb
	 */
	@JSON
	public String countUnreceiveDutyNum(){
		//获得当前登录人
		User currentUser = (User) (UserContext.getCurrentUser());
		//查询未被接入的工单数
		numMap= dutyManager.countUnreceiveDuty(currentUser);
		return SUCCESS;
	}
	/**
	 * <p>
	 * Description: 进入员工工单接入页面<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-3-9
	 * @param bsc
	 * @param rb
	 */
	public String initReceiveDuty(){
		//获得当前登录人
		User currentUser = (User) (UserContext.getCurrentUser());
		//调用初始化
		Map<String,Object> map = dutyManager.initReceiveDuty(currentUser);
		//设置未接入工单数
		totalCount = Long.valueOf(map.get("totalCount").toString());
		//设置已接入工单列表
		receiveDutys = (List<Duty>)map.get("receiveDuty");
		return SUCCESS;		
	}
	/**
	 * <p>
	 * Description: 员工接入工单<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-3-9
	 * @param bsc
	 * @param rb
	 */
	@JSON
	public String receiveDuty(){
		//获得当前登录人
		User currentUser = (User) (UserContext.getCurrentUser());
		//接入工单
		Map<String,Object> map = dutyManager.receiveDuty(currentUser,type);
		
		totalCount = (Long)map.get("totalCount");
		receiveDutys = (List<Duty>)map.get("receiveDuty");		
		return SUCCESS;
	}
	/**
	 * <p>
	 * Description:根据工单责任ID，查询责任反馈记录列表<br />
	 * @author xiaohongye
	 * @return
	 * @version V0.1 
	 * @Date 2013-3-14
	 */
	@JSON
	public String searchDutyFeedbackByDutyId(){
		Map<String,Object> searchParam = new HashMap<String,Object>();
		searchParam.put("dutyId", dutyId);
		dutyFeedbackList = dutyManager.searchDutyFeedbackByDutyId(searchParam);
		return SUCCESS;
	}
	
	/**
	 * <p>
	 * Description:根据工单责任ID，查询责任待反馈的反馈记录列表<br />
	 * @author hpf
	 * @return
	 * @version V0.1 
	 * @Date 2014-01-16
	 */
	@JSON
	public String searchApprovalDutyFeedbackByDutyId(){
		Map<String,Object> searchParam = new HashMap<String,Object>();
		searchParam.put("dutyId", dutyId);
		searchParam.put("boolDutyApproval", true);
		dutyFeedbackList = dutyManager.searchDutyFeedbackByDutyId(searchParam);
		return SUCCESS;
	}
	
	/**
	 * <p>
	 * Description: 员工接入工单<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-3-9
	 * @param bsc
	 * @param rb
	 */
	@JSON
	public String searchDutyByManager(){
		//获得当前登录人
		User currentUser = (User) (UserContext.getCurrentUser());
		//接入工单
		Map<String,Object> map = dutyManager.searchDutyByCondition(currentUser, searchDutyCondition, start, limit);
		
		totalCount = Long.valueOf(map.get("totalCount").toString());
		receiveDutys = (List<Duty>)map.get("receiveDuty");
		
		return SUCCESS;
	}
	/**
	 * <p>
	 * Description: 工单查询-展开表格<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2013-3-14
	 */
	public String queryDutyDetailList(){
		dutyDetailList = dutyManager.queryDutyDetailList(dutyResultId);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:责任划分 - 查询处理结果数据<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2013-3-15
	 * @return
	 * String
	 */
	public String searchDutyResultList(){
		List<DutyResult> dutyResults = dutyManager.searchDutyResults(searchDutyCondition.getId());
		if(CollectionUtils.isEmpty(dutyResults)){
			this.dutyResultList = dutyResults;
		}else{
			for(DutyResult record : dutyResults){
				record.setOld_dealType(record.getDivideType());
				record.setOld_dutyPerId(record.getDutyPerId());
			}
			this.dutyResultList = dutyResults;
		}
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:责任划分 - 查询通知对象数据<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2013-3-15
	 * @return
	 * String
	 */
	public String searchInformUserList(){
		this.informUserList = dutyManager.searchInformUsers(searchDutyCondition.getId());
		return SUCCESS;
	}
	
	
	/**
	 * <p>
	 * Description: 责任反馈分页<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2013-3-9
	 * @param 
	 * @param 
	 */
	@SuppressWarnings("unchecked")
	@JSON
	public String searchDutyFeedbackPager(){
		//获得当前登录人
		User currentUser = (User) (UserContext.getCurrentUser());
		//责任反馈
		Map<String,Object> map = dutyManager.searchDutyFeedBackMain(currentUser,start,limit);
		totalCount = Long.valueOf(map.get("totalCount").toString());
		searchDutyResultVOList = (List<SearchDutyResultVO>)map.get("list");
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:查询反馈附件集合<br />
	 * </p>
	 * @author Administrator
	 * @version 0.1 2013-3-26
	 * @return
	 * String
	 */
	public String searchFeedAttachList(){
		feedAttachList = dutyManager.searchFeedBackFileInfo(searchDutyCondition.getId());
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:工单责任-查询统计员所在部门<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2013-3-28
	 * @return
	 * String
	 */
	public String searchActuaryDeptList(){
		this.departmentList = dutyManager.searchStatisticiansDept();
		return SUCCESS;
	}
	/**
	 * <p>
	 *	Description: 查询工单责任特殊部门
	 * </p> 
	 * @author LiuY
	 * @date 2014-1-9
	 * @return
	 * String
	 */
	public String searchDutyDeptList(){
		this.dutyDeptList = dutyManager.searchDutyDepts();
		return SUCCESS;
	}
	
	/* ------------------get、set方法区 begin ------------------- */
	
	/**
	 * @param start : set the property start.
	 */
	public void setStart(int start) {
		this.start = start;
	}
	

	/**
	 * @param limit : set the property limit.
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}
	/**
	 * @param totalCount : set the property totalCount.
	 */
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
	/**
	 * @param dutyManager : set the property dutyManager.
	 */
	public void setDutyManager(IDutyManager dutyManager) {
		this.dutyManager = dutyManager;
	}
	public int getUnreceiveDutyNum() {
		return unreceiveDutyNum;
	}
	/**
	 * @param dutyFeedbackList the dutyFeedbackList to get
	 */
	public List<DutyFeedback> getDutyFeedbackList() {
		return dutyFeedbackList;
	}
	/**
	 * @param dutyFeedbackList the dutyFeedbackList to set
	 */
	public void setDutyFeedbackList(List<DutyFeedback> dutyFeedbackList) {
		this.dutyFeedbackList = dutyFeedbackList;
	}

	public void setSearchDutyCondition(SearchDutyCondition searchDutyCondition) {
		this.searchDutyCondition = searchDutyCondition;
	}

	public SearchDutyCondition getSearchDutyCondition() {
		return searchDutyCondition;
	}

	public QueryDutyCondition getQueryDutyCondition() {
		return queryDutyCondition;
	}
	public List<DutyQueryResult> getDutyList() {
		return dutyList;
	}
	
	public List<Duty> getReceiveDutys() {
		return receiveDutys;
	}
	public void setQueryDutyCondition(QueryDutyCondition queryDutyCondition) {
		this.queryDutyCondition = queryDutyCondition;
	}
	/**
	 * @return the dutyResultId
	 */
	public String getDutyResultId() {
		return dutyResultId;
	}
	/**
	 * @param dutyResultId the dutyResultId to set
	 */
	public void setDutyResultId(String dutyResultId) {
		this.dutyResultId = dutyResultId;
	}
	/**
	 * @return feedAttachList : return the property feedAttachList.
	 */
	public List<FeedAttach> getFeedAttachList() {
		return feedAttachList;
	}

	/**
	 * @param feedAttachList : set the property feedAttachList.
	 */
	public void setFeedAttachList(List<FeedAttach> feedAttachList) {
		this.feedAttachList = feedAttachList;
	}

	/**
	 * @return the dutyDetailList
	 */
	public List<DutyQueryResult> getDutyDetailList() {
		return dutyDetailList;
	}

	/**
	 * @return dutyResultList : return the property dutyResultList.
	 */
	public List<DutyResult> getDutyResultList() {
		return dutyResultList;
	}

	/**
	 * @param dutyResultList : set the property dutyResultList.
	 */
	public void setDutyResultList(List<DutyResult> dutyResultList) {
		this.dutyResultList = dutyResultList;
	}

	/**
	 * @return informUserList : return the property informUserList.
	 */
	public List<InformUser> getInformUserList() {
		return informUserList;
	}

	/**
	 * @param informUserList : set the property informUserList.
	 */
	public void setInformUserList(List<InformUser> informUserList) {
		this.informUserList = informUserList;
	}
	/**
	 * @param dutyId the dutyId to get
	 */
	public String getDutyId() {
		return dutyId;
	}

	/**
	 * @param dutyId the dutyId to set
	 */
	public void setDutyId(String dutyId) {
		this.dutyId = dutyId;
	}

	/**
	 * @return searchDutyResultVOList : return the property searchDutyResultVOList.
	 */
	public List<SearchDutyResultVO> getSearchDutyResultVOList() {
		return searchDutyResultVOList;
	}

	/**
	 * @param searchDutyResultVOList : set the property searchDutyResultVOList.
	 */
	public void setSearchDutyResultVOList(
			List<SearchDutyResultVO> searchDutyResultVOList) {
		this.searchDutyResultVOList = searchDutyResultVOList;
	}

	/**
	 * @return departmentList : return the property departmentList.
	 */
	public List<Department> getDepartmentList() {
		return departmentList;
	}

	/**
	 * @param departmentList : set the property departmentList.
	 */
	public void setDepartmentList(List<Department> departmentList) {
		this.departmentList = departmentList;
	}

	public Map<String, Integer> getNumMap() {
		return numMap;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	
	/**
	 * @return the dutyDeptList
	 */
	public List<DutyDept> getDutyDeptList() {
		return dutyDeptList;
	}

	
}
