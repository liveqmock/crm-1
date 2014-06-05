/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CustomerGroupService.java
 * @package com.deppon.crm.module.marketing.server.service.impl 
 * @author zhujunyong
 * @version 0.1 Apr 5, 2012
 */
package com.deppon.crm.module.marketing.server.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.marketing.server.dao.IMarketActivityDao;
import com.deppon.crm.module.marketing.server.dao.impl.MarketActivityDao;
import com.deppon.crm.module.marketing.server.service.IMarketActivityService;
import com.deppon.crm.module.marketing.server.utils.MarketActivityUtils;
import com.deppon.crm.module.marketing.shared.domain.activity.ActivityClientBase;
import com.deppon.crm.module.marketing.shared.domain.activity.ActivityDept;
import com.deppon.crm.module.marketing.shared.domain.activity.ActivityExceptionHandle;
import com.deppon.crm.module.marketing.shared.domain.activity.ActivityOption;
import com.deppon.crm.module.marketing.shared.domain.activity.DeptTree;
import com.deppon.crm.module.marketing.shared.domain.activity.LineDept;
import com.deppon.crm.module.marketing.shared.domain.activity.MarketActivity;
import com.deppon.crm.module.marketing.shared.domain.activity.MarketActivityVO;
import com.deppon.crm.module.marketing.shared.domain.activity.Multiple;

/**   
 * <p>
 * Description:市场推广活动<br />
 * </p>
 * @title MarketActivityService.java
 * @package com.deppon.crm.module.marketing.server.service.impl 
 * @author ZhouYuan
 * @version 2013-04-19
 */

public class MarketActivityService implements IMarketActivityService {
	//市场推广活动DAO
	private IMarketActivityDao marketActivityDao;

	/**
	 * 
	 * @param setMarketActivityDao : return the property :void
	 */
	public void setMarketActivityDao(MarketActivityDao marketActivityDao) {
		this.marketActivityDao = marketActivityDao;
	}
	/**
	 * 
	 * <p>
	 * 新建市场推广活动<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param ma
	 * @return int
	 */
	@Override
	public int insertMarketActivity(MarketActivity ma){
		return marketActivityDao.insertMarketActivity(ma);
	}
	/**
	 * 
	 * <p>
	 * 新建市场推广活动多选信息<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param multiple
	 * @return int
	 */
	@Override
	public int insertMultiple(Multiple multiple){
		return marketActivityDao.insertMultiple(multiple);
	}
	/**
	 * 
	 * <p>
	 * 新建市场推广活动多选信息<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param lineDept
	 * @return int
	 */
	@Override
	public int insertLineDept(LineDept lineDept){
		return marketActivityDao.insertLineDept(lineDept);
	}
	/**
	 * 
	 * <p>
	 * 新建市场推广活动折扣优惠申请物品信息<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param option
	 * @return int
	 */
	@Override
	public int insertOptions(ActivityOption option){
		return marketActivityDao.insertOptions(option);
	}
	/**
	 * 
	 * <p>
	 * 新建市场推广活动参与部门<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param acDept
	 * @return int
	 */
	@Override
	public int insertActivityDept(ActivityDept acDept){
		return marketActivityDao.insertActivityDept(acDept);
	}
	/**
	 * 
	 * <p>
	 * 修改市场推广活动<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param ma
	 * @return int
	 */
	@Override
	public int updateMaketActivityById(MarketActivity ma) {
		return marketActivityDao.updateMaketActivityById(ma);
	}
	/**
	 * 
	 * <p>
	 * 根据关联ID关联类型数据类型删除多选条件<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param multiple
	 * @return int
	 */
	@Override
	public int deleteMultipleByType(Multiple multiple) {
		return marketActivityDao.deleteMultipleByType(multiple);
	}
	/**
	 * 
	 * <p>
	 * 根据ID删除多选条件<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param Id
	 * @return int
	 */
	@Override
	public int deleteMultipleById(String id) {
		return marketActivityDao.deleteMultipleById(id);
	}
	/**
	 * 
	 * <p>
	 * 根据关联表ID关联表类型删除线路区域<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param lineDept
	 * @return int
	 */
	@Override
	public int deleteLineDeptByType(LineDept lineDept) {
		return marketActivityDao.deleteLineDeptByType(lineDept);
	}
	/**
	 * 
	 * <p>
	 * 根据ID删除线路区域<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param id
	 * @return int
	 */
	@Override
	public int deleteLineDeptById(String id) {
		return marketActivityDao.deleteLineDeptById(id);
	}
	/**
	 * 
	 * <p>
	 * 根据线路区域列表线路区域<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param id
	 * @return int
	 */
	@Override
	public int deleteLineDeptByIdList(List<LineDept> depts){
		return marketActivityDao.deleteLineDeptByIdList(depts);
	}
	/**
	 * 
	 * <p>
	 * 根据关联表ID定义类型删除多选项<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param id
	 * @return int
	 */
	@Override
	public int deleteOptionsByType(ActivityOption option) {
		return marketActivityDao.deleteOptionsByType(option);
	}	
	/**
	 * 
	 * <p>
	 * 根据市场推广活动ID删除参与部门<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param acDept
	 * @return int
	 */
	@Override
	public int deleteActivityDeptByActivityId(String activityId) {
		return marketActivityDao.deleteActivityDeptByActivityId(activityId);
	}
	/**
	 * 
	 * <p>
	 * 根据ID删除参与部门<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param acDept
	 * @return int
	 */
	@Override
	public int deleteActivityDeptById(String id) {
		return marketActivityDao.deleteActivityDeptById(id);
	}
	/**
	 * 
	 * <p>
	 * 根据ID列表删除参与部门<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param depts
	 * @return int
	 */
	@Override
	public int deleteActivityDeptByIdList(List<ActivityDept> depts){
		return marketActivityDao.deleteActivityDeptByIdList(depts);
	}
	/**
	 * 
	 * <p>
	 * 根据部门ID查询大区还是事业部<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param depts
	 * @return int
	 */
	 public String searchAuthDeptCharacter(String id){
		 return marketActivityDao.searchAuthDeptCharacter(id);
	 }
	 /**
	 * 
	 * <p>
	 * 添加客户群到市场推广活动关联表<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param clientBase
	 * @return int
	 */
	 public int insertActivityClient(ActivityClientBase clientBase){
		 return marketActivityDao.insertActivityClient(clientBase);
	 }
	 /**
	 * 
	 * <p>
	 * 加载部门树<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param userId
	 * @return List<DeptTree>
	 */
	@Override
	public List<DeptTree> loadAuthTree(String userId) {
		return marketActivityDao.loadAuthTree(userId);
	}
	/**
	 * 
	 * <p>
	 * 根据多种条件查询市场推广活动<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param MarketActivityVO
	 * @return List<MarketActivityVO>
	 */
	@Override
	public List<MarketActivityVO> searchMarketActivityByMulCondition( MarketActivityVO mav,int start,int limit){
	    return marketActivityDao.searchMarketActivityByMulCondition(mav, start, limit);
	}
	 /**
	 * 
	 * <p>
	 * 根据多种条件查询市场推广活动数<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param MarketActivityVO
	 * @return List<MarketActivityVO>
	 */
	@Override
	public int countMarketActivityByMulCondition( MarketActivityVO mav ){
		return marketActivityDao.countMarketActivityByMulCondition(mav);
	}
	/**
	 * 
	 * <p>
	 * 根据多种条件查询市场推广活动数<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param MarketActivityVO
	 * @return List<MarketActivityVO>
	 */
	@Override
	public MarketActivity searchMarketActivityByIdOrWorkFlowNum(MarketActivity ma){
		MarketActivity activity = marketActivityDao.searchMarketActivityByIdOrWorkFlowNum(ma);
		MarketActivityUtils.setMarketActivityStringArray(activity);
		return activity;
	}
	/**
	 * 
	 * <p>
	 * 加载出发区域或到达区域树<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param 市场推广活动ID 走货线路类型 
	 * @return List<DeptTree>
	 */
	@Override
	public List<DeptTree> loadDeliverOrArriveTree() {
		return marketActivityDao.loadDeliverOrArriveTree();
	}
	/**
	 * 
	 * <p>
	 * 根据市场推广活动ID 线路区域要求加载走货线路<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param MarketActivityVO
	 * @return List<LineDept>
	 */
	@Override
	public List<LineDept> searchLineDeptByTypeAndActivityId(String activityId,String conditionType){
		Map<String,String> map = new HashMap<String,String>();
		map.put("activityId", activityId);
		map.put("conditionType", conditionType);
		return marketActivityDao.searchLineDeptByTypeAndActivityId(map);
		
	}
	/**
	 * 
	 * <p>
	 * 根据市场推广活动ID查询参与活动营业部数<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param MarketActivityVO
	 * @return List<LineDept>
	 */
	@Override
	public int countActivityDeptNum(List<String> deptCodes) {
		return marketActivityDao.countActivityDeptNum(deptCodes);
	}
	/**
	 * 
	 * <p>
	 * 删除客户群关系<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-17
	 * @param relationId
	 * @param status
	 * @return int
	 */
	@Override
	public int deleteClientBaseByRelationId(String relationId,String status){
		Map<String,String> map = new HashMap<String,String>();
		map.put("relationId", relationId);
		map.put("status", status);
		return marketActivityDao.deleteClientBaseByRelationId(map);
	}
	/**
	 * 
	 * <p>
	 * 修改市场推广活动与客户群关系表的状态<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param ActivityClientBase
	 * @return int
	 */
	@Override
	public int updateActivityClientById(ActivityClientBase acb){
		return marketActivityDao.updateActivityClientById(acb);
	}
	/**
	 * 
	 * <p>
	 * 市场推广活动关联客户群时查询未使用的客户群<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-17
	 * @param Map<String,String>
	 * @return ActivityClientBase
	 */
	@Override
	public List<ActivityClientBase> searchActivityClientByName(String clientBaseName,User user,String clientBaseStatus,
			String deptCharacter,String[] clients,int start,int limit){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("clientBaseName", clientBaseName);
		map.put("userId", user.getId());
		map.put("clientBaseStatus", clientBaseStatus);
		map.put("clients", clients);
		map.put("deptCharacter", deptCharacter);
		map.put("deptId", user.getEmpCode().getDeptId().getId());
		return marketActivityDao.searchActivityClientByName(map, start, limit);
	}
	/**
	 * 
	 * <p>
	 * 市场推广活动关联客户群时查询未使用的客户群<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-17
	 * @param Map<String,String>
	 * @return ActivityClientBase
	 */
	@Override
	public int countActivityClientByName(String clientBaseName,User user,String clientBaseStatus,String deptCharacter,String[] clients){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("clientBaseName", clientBaseName);
		map.put("userId", user.getId());
		map.put("clientBaseStatus", clientBaseStatus);
		map.put("clients", clients);
		map.put("deptCharacter", deptCharacter);
		map.put("deptId", user.getEmpCode().getDeptId().getId());
		return marketActivityDao.countActivityClientByName(map);
	}
	/**
	 * 
	 * <p>
	 * 根据市场推广ID,客户群状态查询客户群<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-17
	 * @param Map<String,String>
	 * @return List<ActivityClientBase>
	 */
	@Override
	public List<ActivityClientBase> searchActivityClientByMultipleCondition(String activityId,String clientBaseStatus,List<ActivityClientBase> clients){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("activityId", activityId);
		map.put("clientBaseStatus", clientBaseStatus);
		map.put("clients", clients);
		return marketActivityDao.searchActivityClientByMultipleCondition(map);
	}
	/**
	 * 
	 * <p>
	 * 根据关联ID客户群<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-17
	 * @param Map<String,String>
	 * @return List<ActivityClientBase>
	 */
	@Override
	public ActivityClientBase searchActivityClientByReId(String id){
		Map<String,String> map = new HashMap<String,String>();
		map.put("id", id);
		return marketActivityDao.searchActivityClientByReId(map);
	}
	/**
	 * 
	 * <p>
	 * 根据客户群ID列表查询客户群<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-17
	 * @param List<String>
	 * @return List<ActivityClientBase>
	 */
	@Override
	public List<ActivityClientBase> searchActivityClientByClientIds(
			List<ActivityClientBase> clientIds) {
		return marketActivityDao.searchActivityClientByClientIds(clientIds);
	}
	/**
	 * 
	 * <p>
	 * 根据客户群ID列表查询客户群<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-17
	 * @param String acitivityId
	 * @param String userId
	 * @return int 1代表有权限 0代表没有权限
	 */
	@Override
	public int searchUpdateAuth(String activityId,String userId){
		Map<String,String> map = new HashMap<String,String>();
		map.put("activityId", activityId);
		map.put("userId", userId);
		return marketActivityDao.searchUpdateAuth(map);
	}
	/**
	 * 
	 * <p>
	 * 根据市场推广活动ID查询关联客户群<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-17
	 * @param String acitivityId
	 * @return int 1代表有权限 0代表没有权限
	 */
	@Override
	public List<ActivityClientBase> searchActivityClientByActivityId(
			String activityId) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("id", activityId);
		return marketActivityDao.searchActivityClientByActivityId(map);
	}
	/**
	 * 
	 * <p>
	 * 根据市场推广活动ID查询参与部门数<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-17
	 * @param String activityId
	 * @return int
	 */
	@Override
	public int countActivityDeptByActivityId(String activityId) {
		return marketActivityDao.countActivityDeptByActivityId(activityId);
	}
	/**
	 * 
	 * <p>
	 * 查询最新的全国市场推广活动根据部门ID<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-17
	 * @param String activityId
	 * @return int
	 */
	@Override
	public MarketActivity searchLatestMarketActivityByDeptId(String deptId) {
		return marketActivityDao.searchLatestMarketActivityByDeptId(deptId);
	}
	/**
	 * 
	 * <p>
	 * 查询未创建完的市场推广活动<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-17
	 * @return List<MarketActivity>
	 */
	@Override
	public List<MarketActivity> searchUselessOrCompleteActivities(String condition) {
		return marketActivityDao.searchUselessOrCompleteActivities(condition);
	}
	/**
	 * 
	 * <p>
	 * 新增市场推广活动异常信息<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-4-6
	 * @param ActivityExceptionHandle
	 */
	@Override
	public int insertActivityException(ActivityExceptionHandle aeh) {
		return marketActivityDao.insertActivityException(aeh);
	}
	/**
	 * 
	 * <p>
	 * 更新市场推广活动异常信息处理标识<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-4-6
	 * @param ActivityExceptionHandle
	 */
	@Override
	public int updateActivityExceptionById(ActivityExceptionHandle aeh) {
		return marketActivityDao.updateActivityExceptionById(aeh);
	}
	/**
	 * 
	 * <p>
	 * 根据市场推广活动异常信息是否被处理查询异常信息<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-4-6
	 * @param ActivityExceptionHandle
	 */
	@Override
	public List<ActivityExceptionHandle> searchActivityExeptionUnhandle(
			String handle) {
		return marketActivityDao.searchActivityExeptionUnhandle(handle);
	}
	/**
	 * 
	 * <p>
	 * 查询折扣工作流是否被使用<br />
	 * </p>
	 * 
	 * @author lvchongxin
	 * @version 0.1 2014-4-10
	 * @param workflowNum
	 */
	@Override
	public boolean checkWorkflowIsRepeat(String workflowNum) {
		return marketActivityDao.checkWorkflowIsRepeat(workflowNum);
	}
	/**
	 * 
	 * <p>
	 * 根据客户群状态查询待下发的客户群并生成相应的计划及日程<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-4-10
	 */
	@Override
	public void createClientBaseDetail() {
		marketActivityDao.createClientBaseDetail();
	}
	/**
	 * 
	 * <p>
	 * 查询关联的市场推广活动的客户群数量<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-4-10
	 */
	@Override
	public void countClientBaseDetail() {
		marketActivityDao.countClientBaseDetail();
	}
	/**
	 * 
	 * <p>
	 * 生成客户群明细报表<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-4-10
	 */
	@Override
	public void createClientDetailReport() {
		marketActivityDao.createClientDetailReport();
	}
	/**
	 * 
	 * <p>
	 * 营销计划创建时查询现在可用的市场推广活动<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-4-10
	 */
	@Override
	public List<MarketActivity> searchMarketActivityForMarketPlan(String activityName,String activityCategory) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("activityName", activityName);
		map.put("activityCategory", activityCategory);
		return marketActivityDao.searchMarketActivityForMarketPlan(map);
	}
	/**
	 * 
	 * <p>
	 * 营销计划创建时查询市场推广活动下的优惠券抵扣类型与数量<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-4-10
	 */
	@Override
	public List<ActivityOption> searchActivityOptionByActivityIdAndType(
			String activityId, String infoType) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("id", activityId);
		map.put("infoType", infoType);
		return marketActivityDao.searchActivityOptionByActivityIdAndType(map);
	}
	/**
	 * 
	 * <p>
	 * 计算同名的市场推广活动数量<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-4-10
	 */
	@Override
	public int countMarketActivityByName(String activityName) {
		return marketActivityDao.countMarketActivityByName(activityName);
	}
	/**
	 * 
	 * <p>
	 * 根据ID列表删除市场推广活动<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-4-10
	 */
	@Override
	public int deleteMarketActivityByIds(List<MarketActivity> activites) {
		return marketActivityDao.deleteMarketActivityByIds(activites);
	}
}
