/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ICustomerGroupDao.java
 * @package com.deppon.crm.module.marketing.server.dao 
 * @author zhujunyong
 * @version 0.1 Apr 5, 2012
 */
package com.deppon.crm.module.marketing.server.dao;

import java.util.List;
import java.util.Map;

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
 * @title IMarketActivityDao.java
 * @package com.deppon.crm.module.marketing.server.dao 
 * @author ZhouYuan
 * @version 2013-04-19
 */

public interface IMarketActivityDao {
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
	int insertMarketActivity(MarketActivity ma);
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
	int insertMultiple(Multiple multiple);
	/**
	 * 
	 * <p>
	 * 新建市场推广活动走货线路<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param ma
	 * @return int
	 */
	int insertLineDept(LineDept lineDept);
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
	int insertOptions(ActivityOption option);
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
	int insertActivityDept(ActivityDept acDept);
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
	int updateMaketActivityById(MarketActivity ma);
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
	int deleteMultipleByType(Multiple multiple);
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
	int deleteMultipleById(String id);
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
	int deleteLineDeptByType(LineDept lineDept);
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
	int deleteLineDeptById(String id);
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
	int deleteLineDeptByIdList(List<LineDept> depts);
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
	int deleteOptionsByType(ActivityOption option);
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
	int deleteActivityDeptByActivityId(String acDept);
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
	int deleteActivityDeptById(String id);
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
	int deleteActivityDeptByIdList(List<ActivityDept> depts);
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
	 String searchAuthDeptCharacter(String id);
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
	 int insertActivityClient(ActivityClientBase clientBase);
//	 /**
//	 * 
//	 * <p>
//	 * 加载部门树<br />
//	 * </p>
//	 * 
//	 * @author ZhouYuan
//	 * @version 0.1 2014-3-6
//	 * @param clientBase
//	 * @return int
//	 */
//	 List<DeptTree> loadTreeRoot(String userId);
	/**
	 * 
	 * <p>
	 * 加载部门树<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param map
	 * @return List<DeptTree>
	 */
	 List<DeptTree> loadAuthTree( String userId);
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
	 List<MarketActivityVO> searchMarketActivityByMulCondition( MarketActivityVO mav,int start,int limit);
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
	 int countMarketActivityByMulCondition( MarketActivityVO mav );
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
	 MarketActivity searchMarketActivityByIdOrWorkFlowNum(MarketActivity ma);
	/**
	 * 
	 * <p>
	 * 根据市场推广活动ID 线路区域要求  业务类型加载出发区域到达区域树<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param MarketActivityVO
	 * @return List<MarketActivityVO>
	 */
	 List<DeptTree> loadDeliverOrArriveTree();
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
	 List<LineDept> searchLineDeptByTypeAndActivityId(Map<String,String> map);
	/**
	 * 
	 * <p>
	 * 根据市场推广活动ID查询参与活动营业部数<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param List<String>
	 * @return int
	 */
	 int countActivityDeptNum(List<String> deptCodes);
	/**
	 * 
	 * <p>
	 * 删除客户群关系<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param List<String>
	 * @return int
	 */
	 int deleteClientBaseByRelationId(Map<String,String> map);
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
	 int updateActivityClientById(ActivityClientBase acb);
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
	 List<ActivityClientBase> searchActivityClientByName(Map<String,Object> map,
			 int start,int limit);
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
	 int countActivityClientByName(Map<String,Object> map);
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
	  List<ActivityClientBase> searchActivityClientByMultipleCondition(Map<String,Object> map);
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
	  ActivityClientBase searchActivityClientByReId(Map<String,String> map);
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
	  List<ActivityClientBase> searchActivityClientByClientIds(List<ActivityClientBase> clientIds);
	/**
	 * 
	 * <p>
	 * 根据客户群ID列表查询客户群<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-17
	 * @param Map<String,String>
	 * @return int
	 */
	  int searchUpdateAuth(Map<String,String> map);
	/**
	 * 
	 * <p>
	 * 根据市场推广活动ID,用户ID查询部门列表<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-17
	 * @param Map<String,String>
	 * @return List<DeptTree>
	 */
	  List<DeptTree> searchTree(Map<String,String> map);
	/**
	 * 
	 * <p>
	 * 根据市场推广活动ID查询客户群<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-17
	 * @param Map<String,String>
	 * @return List<ActivityClientBase>
	 */
	  List<ActivityClientBase> searchActivityClientByActivityId(Map<String,String> map);
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
	  int countActivityDeptByActivityId(String activityId);
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
	  MarketActivity searchLatestMarketActivityByDeptId(String deptId);
	/**
	 * 
	 * <p>
	 * 查询未创建完的市场推广活动<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-17
	 * @param String
	 * @return List<MarketActivity>
	 */
	  List<MarketActivity> searchUselessOrCompleteActivities(String condition);
    /**
	 * 
	 * <p>
	 * 查询未创建完的市场推广活动<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-17
	 * @param String
	 * @return List<MarketActivity>
	 */
	  List<DeptTree> loadAuthTreeTier(String parentId);
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
	  int insertActivityException(ActivityExceptionHandle aeh);
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
	  int updateActivityExceptionById(ActivityExceptionHandle aeh);
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
	  List<ActivityExceptionHandle> searchActivityExeptionUnhandle(String handle);
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
	  boolean checkWorkflowIsRepeat(String workflowNum);
	/**
	 * 
	 * <p>
	 * 根据客户群状态查询待下发的客户群并生成相应的计划及日程<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-4-10
	 */
	  void createClientBaseDetail();
	/**
	 * 
	 * <p>
	 * 查询关联的市场推广活动的客户群数量<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-4-10
	 */
	  void countClientBaseDetail();
	/**
	 * 
	 * <p>
	 * 生成客户群明细报表<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-4-10
	 */
	  void createClientDetailReport();
	/**
	 * 
	 * <p>
	 * 营销计划创建时查询现在可用的市场推广活动<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-4-10
	 */
	  List<MarketActivity> searchMarketActivityForMarketPlan(Map<String,String> map);
	/**
	 * 
	 * <p>
	 * 营销计划创建时查询市场推广活动下的优惠券抵扣类型与数量<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-4-10
	 */
	  List<ActivityOption> searchActivityOptionByActivityIdAndType(Map<String,String> map);
	/**
	 * 
	 * <p>
	 * 计算同名的市场推广活动数量<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-4-10
	 */
	  int countMarketActivityByName(String activityName);
	/**
	 * 
	 * <p>
	 * 根据ID列表删除市场推广活动<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-4-10
	 */
	  int deleteMarketActivityByIds(List<MarketActivity> activites);
}
