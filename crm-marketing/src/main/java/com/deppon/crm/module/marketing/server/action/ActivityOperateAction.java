package com.deppon.crm.module.marketing.server.action;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.crm.module.common.file.manager.impl.FileManager;
import com.deppon.crm.module.common.shared.domain.Detail;
import com.deppon.crm.module.login.server.action.UserInfo;
import com.deppon.crm.module.marketing.server.manager.IClientBaseManager;
import com.deppon.crm.module.marketing.server.manager.IMarketActivityManager;
import com.deppon.crm.module.marketing.server.manager.impl.MarketActivityManager;
import com.deppon.crm.module.marketing.shared.domain.activity.ActivityClientBase;
import com.deppon.crm.module.marketing.shared.domain.activity.ActivityOption;
import com.deppon.crm.module.marketing.shared.domain.activity.DeptTree;
import com.deppon.crm.module.marketing.shared.domain.activity.LineDept;
import com.deppon.crm.module.marketing.shared.domain.activity.MarketActivity;
import com.deppon.crm.module.marketing.shared.domain.activity.MarketActivityVO;
import com.deppon.crm.module.organization.server.manager.ISearchDeptAndEmployeeManager;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.SearchEmpAndDeptCondition;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;

public class ActivityOperateAction extends AbstractAction{
	
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;
	//活动所有信息实体
	private MarketActivity marketActivity ;
	private IClientBaseManager clientBaseManager;
	private IMarketActivityManager marketActivityManager;
	private ISearchDeptAndEmployeeManager searchDeptAndEmployeeManager;
	private FileManager fileManager;
	private List<MarketActivityVO> marketActivityVOList;
	private List<ActivityClientBase> activityClientBaseList;
	private List<Department> departmentList;
	private List<UserInfo> userInfoList;
	//是长推广活动实体list
	private List<MarketActivity> marketActivityList;
	//一级行业列表
	private List<String> trades;
	//部门标杆编码列表
	private List<String> deptCodes;
	//部门列表
	private List<LineDept> lineDepts;
	//部门树实体
	private List<DeptTree> deptTreeListChild;
	//优惠券抵扣类型与数量
	private List<ActivityOption> activityOptionList;
	//已加入到列表中客户群id集合
	private String activityClientIds[]; 
	//查询活动实体
	private MarketActivityVO marketActivityVO;
	//部门树实体
	private DeptTree deptTree;
	//文件实体
	private FileInfo fileInfo;
	//市场活动ID
	private String id;
	//总数
	private int totalCount ;
	//部门类型
	private String userDeptCharacterType;
	//查询实体
	private SearchEmpAndDeptCondition condition;
	//客户群实体
	private ActivityClientBase client;
	//线路类型
	private String lineRequest;
	//工作流号码
	private String workFlowNum;
	//数据字典实体
	private List<Detail> detailList;
	//客户群名称
	private String clientBaseName;
	//部门名称
	private String deptName;
	//部门负责人
	private String principal;
	//部门负责人
	private String marketActivityName;
	/**
	 * 
	 * <p>
	 * Description:市场推广活动条件查询<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-8
	 * @return
	 * String
	 */
	@JSON
	public String queryActivityMain(){
		marketActivityVOList = marketActivityManager.searchMarketActivityByMulCondition(marketActivityVO,getCurrentUser(),start, limit);
		totalCount = marketActivityManager.countMarketActivityByMulCondition(marketActivityVO, getCurrentUser());
		totalCount =(totalCount == 0) ? 1 : totalCount;
		return SUCCESS;
	}
	

	/**
	 * 
	 * <p>
	 * Description:初始化市场推广活动<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-11
	 * @return
	 * String
	 */
	@JSON
	public String initMarketActivity(){
		marketActivity = marketActivityManager.initMarketActivity(getCurrentUser());
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:保存市场推广活动主信息<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-8
	 * @return
	 * String
	 */
	@JSON
	public String saveMarketActivity(){
		workFlowNum = marketActivityManager.saveMarketActivity(marketActivity, getCurrentUser());
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:根据ID查询活动的所有信息<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-12
	 * @return
	 * String
	 */
	@JSON
	public String queryActivityAll(){
		marketActivity = marketActivityManager.searchMarketActivityById(id);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:保存文件信息到数据库中<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-13
	 * @return
	 * String
	 */
	@JSON
	public String saveFileInfo(){
		fileManager.saveFileInfo(fileInfo);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:删除文件（删除数据库中的数据，同时删除磁盘上的文件）<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-13
	 * @return
	 * String
	 */
	@JSON
	public String deleteFileInfo(){
		fileManager.deleteFileInfo(fileInfo);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:获得用户部门性质<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-14
	 * @return
	 * String
	 */
	@JSON
	public String gainUserDeptCharacter(){
		userDeptCharacterType = marketActivityManager.getUserDeptCharacter(getCurrentUser());
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:加载部门树<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-17
	 * @return
	 * String
	 */
	@JSON
	public String loadMarket(){
		deptTree = marketActivityManager.loadAuthTree(getCurrentUser().getId());
//		deptTreeListChild = deptTree.getChildren();
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:查询未使用的客户群<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-18
	 * @return
	 * String
	 */
	@JSON
	public String searchActivityClientByName(){
		activityClientBaseList = marketActivityManager.searchActivityClientByName(clientBaseName, getCurrentUser(),activityClientIds, start, limit);
		totalCount = marketActivityManager.countActivityClientByName(clientBaseName, getCurrentUser(), activityClientIds);
		totalCount = totalCount == 0? 1 : totalCount;
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:通过部门名称模糊查询<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-18
	 * @return
	 * String
	 */
	@JSON
	public String searchDeptListByName(){
		Map<String,Object> map = clientBaseManager.searchDeptListByName(deptName, start, limit);
		departmentList = (List<Department>) map.get("deptList");
		totalCount = (Integer) map.get("totalCount");
		totalCount = totalCount == 0? 1 : totalCount;
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:通过工号查询联系人<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-20
	 * @return
	 * String
	 */
	@JSON
	public String searchEmpByCondition(){
		condition.setDeptName("");
		condition.setEmpName("");
		if(condition.getEmpCode() != null){
			condition.setEmpCode(condition.getEmpCode()+"%");
		}
		userInfoList = searchDeptAndEmployeeManager.searchEmpByCondition(condition, start, limit);
		//Long转int
		Long count = searchDeptAndEmployeeManager.countEmpByCondition(condition);
		totalCount = count.intValue();
		totalCount = totalCount == 0 ? 1 : totalCount;
		return SUCCESS;
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:查询二级行业by一级行业List<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-20
	 * @return
	 * String
	 */
	@JSON
	public String searchSecondTradesByList(){
		detailList = clientBaseManager.searchSecondTradesByList(trades);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:保存线路信息<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-24
	 * @return
	 * String
	 */
	@JSON
	public String saveLineInformation(){
		marketActivityManager.insertLineDept(lineDepts, lineRequest, id);
		return SUCCESS;
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:修改市场推广活动客户群<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-29
	 * @return
	 * String
	 */
	@JSON
	public String updateMarketActivityClient(){
		marketActivityManager.updateMarketActivityClient(id, activityClientBaseList, getCurrentUser());
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:保存市场推广活动参与部门<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-29
	 * @return
	 * String
	 */
	@JSON
	public String saveActivityDept(){
		marketActivityManager.saveActivityDept(deptCodes, id, getCurrentUser());
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:加载线路树<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-1
	 * @return
	 * String
	 */
	@JSON
	public String loadDeliverOrArriveTree(){
		deptTree = marketActivityManager.loadDeliverOrArriveTree();
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:下发客户群<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-1
	 * @return
	 * String
	 */
	@JSON
	public String issuedClient(){
		marketActivityManager.createClientBasePlan(id, client, getCurrentUser());
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:加载该活动使用中的客户群<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-1
	 * @return
	 * String
	 */
	@JSON
	public String loadUsedClientBase(){
		activityClientBaseList = marketActivityManager.searchActivityClientBaseUsed(id);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:工作流号验证方法<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-5
	 * @return
	 * String
	 */
	@JSON
	public String workFlowNumVerify(){
		marketActivityManager.checkDiscountWorkflow(workFlowNum);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:营销计划创建时查询现在可用的市场推广活动<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-14
	 * @return
	 * String
	 */
	@JSON
	public String searchMarketActivityForMarketPlan(){
		marketActivityList = marketActivityManager.searchMarketActivityForMarketPlan(marketActivityName, getCurrentUser());
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:营销计划创建时查询市场推广活动下的优惠券抵扣类型与数量<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-14
	 * @return
	 * String
	 */
	@JSON
	public String searchActivityOptionByActivityIdAndType(){
		activityOptionList = marketActivityManager.searchActivityOptionByActivityIdAndType(id);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:验证名字是否重复<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-17
	 * @return
	 * String
	 */
	@JSON
	public String checkHaveSamNameMarketActivity(){
		marketActivityManager.checkHaveSamNameMarketActivity(marketActivityName);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:计算部门数量<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-21
	 * @return
	 * String
	 */
	@JSON
	public String countActivityDeptNum(){
		totalCount = marketActivityManager.countActivityDeptNum(deptCodes);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-5-5
	 * @return
	 * String
	 */
	@JSON
	public String saveMarketActivityAndDepts(){
		workFlowNum = marketActivityManager.saveMarketActivityAndDepts(marketActivity, lineDepts, lineRequest, deptCodes, getCurrentUser());
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:获取当前用户的部门负责人<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-5
	 * @return
	 * String
	 */
	@JSON
	public String intiPrincipal(){
		principal = getCurrentUser().getEmpCode().getDeptId().getPrincipal();
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:获取当前用户<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-11
	 * @return
	 * User
	 */
	private User getCurrentUser(){
		return (User) UserContext.getCurrentUser();
	}
	
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-7
	 */
	public MarketActivity getMarketActivity() {
		return marketActivity;
	}
	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-7
	 * @param marketActivity the marketActivity to set
	 */
	public void setMarketActivity(MarketActivity marketActivity) {
		this.marketActivity = marketActivity;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-11
	 * @param marketActivityManager the marketActivityManager to set
	 */
	public void setMarketActivityManager(MarketActivityManager marketActivityManager) {
		this.marketActivityManager = marketActivityManager;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-12
	 */
	public String getId() {
		return id;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-12
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-13
	 */
	public FileInfo getFileInfo() {
		return fileInfo;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-13
	 * @param fileInfo the fileInfo to set
	 */
	public void setFileInfo(FileInfo fileInfo) {
		this.fileInfo = fileInfo;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-13
	 * @param fileManager the fileManager to set
	 */
	public void setFileManager(FileManager fileManager) {
		this.fileManager = fileManager;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-14
	 */
	public MarketActivityVO getMarketActivityVO() {
		return marketActivityVO;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-14
	 * @param marketActivityVO the marketActivityVO to set
	 */
	public void setMarketActivityVO(MarketActivityVO marketActivityVO) {
		this.marketActivityVO = marketActivityVO;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-14
	 */
	public List<MarketActivityVO> getMarketActivityVOList() {
		return marketActivityVOList;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-14
	 * @param marketActivityVOList the marketActivityVOList to set
	 */
	public void setMarketActivityVOList(List<MarketActivityVO> marketActivityVOList) {
		this.marketActivityVOList = marketActivityVOList;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-14
	 */
	public Long getTotalCount() {
		return (long) totalCount;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-14
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-14
	 */
	public String getUserDeptCharacterType() {
		return userDeptCharacterType;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-14
	 * @param userDeptCharacterType the userDeptCharacterType to set
	 */
	public void setUserDeptCharacterType(String userDeptCharacterType) {
		this.userDeptCharacterType = userDeptCharacterType;
	}



	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-18
	 */
	public List<ActivityClientBase> getActivityClientBaseList() {
		return activityClientBaseList;
	}


	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-18
	 * @param activityClientBaseList the activityClientBaseList to set
	 */
	public void setActivityClientBaseList(
			List<ActivityClientBase> activityClientBaseList) {
		this.activityClientBaseList = activityClientBaseList;
	}


	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-18
	 */
	public String getClientBaseName() {
		return clientBaseName;
	}


	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-18
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
	 * @version 0.1 2014-3-18
	 * @param clientBaseManager the clientBaseManager to set
	 */
	public void setClientBaseManager(IClientBaseManager clientBaseManager) {
		this.clientBaseManager = clientBaseManager;
	}


	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-18
	 */
	public String getDeptName() {
		return deptName;
	}


	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-18
	 * @param deptName the deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}


	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-19
	 */
	public List<Department> getDepartmentList() {
		return departmentList;
	}


	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-19
	 * @param departmentList the departmentList to set
	 */
	public void setDepartmentList(List<Department> departmentList) {
		this.departmentList = departmentList;
	}


	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-20
	 */
	public List<UserInfo> getUserInfoList() {
		return userInfoList;
	}


	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-20
	 * @param userInfoList the userInfoList to set
	 */
	public void setUserInfoList(List<UserInfo> userInfoList) {
		this.userInfoList = userInfoList;
	}


	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-20
	 */
	public SearchEmpAndDeptCondition getCondition() {
		return condition;
	}


	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-20
	 * @param condition the condition to set
	 */
	public void setCondition(SearchEmpAndDeptCondition condition) {
		this.condition = condition;
	}


	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-20
	 * @param marketActivityManager the marketActivityManager to set
	 */
	public void setMarketActivityManager(
			IMarketActivityManager marketActivityManager) {
		this.marketActivityManager = marketActivityManager;
	}


	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-20
	 * @param searchDeptAndEmployeeManager the searchDeptAndEmployeeManager to set
	 */
	public void setSearchDeptAndEmployeeManager(
			ISearchDeptAndEmployeeManager searchDeptAndEmployeeManager) {
		this.searchDeptAndEmployeeManager = searchDeptAndEmployeeManager;
	}


	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-20
	 */
	public List<String> getTrades() {
		return trades;
	}


	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-20
	 * @param trades the trades to set
	 */
	public void setTrades(List<String> trades) {
		this.trades = trades;
	}


	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-21
	 */
	public List<Detail> getDetailList() {
		return detailList;
	}


	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-21
	 * @param detailList the detailList to set
	 */
	public void setDetailList(List<Detail> detailList) {
		this.detailList = detailList;
	}

	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-21
	 */
	public String[] getActivityClientIds() {
		return activityClientIds;
	}


	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-21
	 * @param activityClientIds the activityClientIds to set
	 */
	public void setActivityClientIds(String[] activityClientIds) {
		this.activityClientIds = activityClientIds;
	}


	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-24
	 */
	public List<LineDept> getLineDepts() {
		return lineDepts;
	}


	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-24
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
	 * @version 0.1 2014-3-24
	 */
	public String getLineRequest() {
		return lineRequest;
	}


	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-24
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
	 * @version 0.1 2014-3-25
	 */
	public DeptTree getDeptTree() {
		return deptTree;
	}


	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-25
	 * @param deptTree the deptTree to set
	 */
	public void setDeptTree(DeptTree deptTree) {
		this.deptTree = deptTree;
	}


	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-26
	 */
	public List<DeptTree> getDeptTreeListChild() {
		return deptTreeListChild;
	}


	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-26
	 * @param deptTreeListChild the deptTreeListChild to set
	 */
	public void setDeptTreeListChild(List<DeptTree> deptTreeListChild) {
		this.deptTreeListChild = deptTreeListChild;
	}


	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-29
	 */
	public List<String> getDeptCodes() {
		return deptCodes;
	}


	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-3-29
	 * @param deptCodes the deptCodes to set
	 */
	public void setDeptCodes(List<String> deptCodes) {
		this.deptCodes = deptCodes;
	}


	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-3
	 */
	public String getWorkFlowNum() {
		return workFlowNum;
	}


	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-3
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
	 * @version 0.1 2014-4-5
	 */
	public String getPrincipal() {
		return principal;
	}


	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-5
	 * @param principal the principal to set
	 */
	public void setPrincipal(String principal) {
		this.principal = principal;
	}


	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-5
	 */
	public ActivityClientBase getClient() {
		return client;
	}


	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-5
	 * @param client the client to set
	 */
	public void setClient(ActivityClientBase client) {
		this.client = client;
	}


	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-14
	 */
	public List<ActivityOption> getActivityOptionList() {
		return activityOptionList;
	}


	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-14
	 * @param activityOptionList the activityOptionList to set
	 */
	public void setActivityOptionList(List<ActivityOption> activityOptionList) {
		this.activityOptionList = activityOptionList;
	}


	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-14
	 */
	public List<MarketActivity> getMarketActivityList() {
		return marketActivityList;
	}


	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-14
	 * @param marketActivityList the marketActivityList to set
	 */
	public void setMarketActivityList(List<MarketActivity> marketActivityList) {
		this.marketActivityList = marketActivityList;
	}


	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-15
	 */
	public String getMarketActivityName() {
		return marketActivityName;
	}


	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 李春雨
	 * @version 0.1 2014-4-15
	 * @param marketActivityName the marketActivityName to set
	 */
	public void setMarketActivityName(String marketActivityName) {
		this.marketActivityName = marketActivityName;
	}

}
