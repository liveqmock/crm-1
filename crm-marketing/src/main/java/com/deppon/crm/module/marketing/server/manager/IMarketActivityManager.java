package com.deppon.crm.module.marketing.server.manager;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.marketing.shared.domain.activity.ActivityClientBase;
import com.deppon.crm.module.marketing.shared.domain.activity.ActivityOption;
import com.deppon.crm.module.marketing.shared.domain.activity.DeptTree;
import com.deppon.crm.module.marketing.shared.domain.activity.LineDept;
import com.deppon.crm.module.marketing.shared.domain.activity.MarketActivity;
import com.deppon.crm.module.marketing.shared.domain.activity.MarketActivityVO;


/**   
 * <p>
 * Description:市场推广活动<br />
 * </p>
 * @title IMarketActivityManager.java
 * @package com.deppon.crm.module.marketing.server.manager
 * @author ZhouYuan
 * @version 2014-03-05
 */
public interface IMarketActivityManager {
	/**
	 * 
	 * <p>
	 * 初始化市场推广活动<br />
	 * 
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param ma,user
	 * @return int
	 */
	MarketActivity initMarketActivity(User user);
	/**
	 * 
	 * <p>
	 * 新增线路区域<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param acDept
	 * @return int
	 */
	void insertLineDept(List<LineDept> lineDepts,String lineRequestt,String activityId);
	/**
	 * 
	 * <p>
	 * 保存市场推广活动、参与部门、走货线路<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param ma
	 * @return int
	 */
	String saveMarketActivityAndDepts(MarketActivity ma,List<LineDept> lineDepts,
			String lineRequest,List<String> deptCodes,User user);
	/**
	 * 
	 * <p>
	 * 保存市场推广活动<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param ma
	 * @return int
	 */
	String saveMarketActivity(MarketActivity ma,User user);
	/**
	 * 
	 * <p>
	 * 获得用户部门性质<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param ma
	 * @return int
	 */
	String getUserDeptCharacter(User user);
	/**
	 * 
	 * <p>
	 * 加载参与部门树<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param userId
	 * @return List<DeptTree>
	 */
	 DeptTree loadAuthTree(String userId);
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
	 List<MarketActivityVO> searchMarketActivityByMulCondition( MarketActivityVO mav,User user,int start,int limit);
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
	 int countMarketActivityByMulCondition( MarketActivityVO mav,User user );
	 /**
	 * 
	 * <p>
	 * 根据ID查询市场推广活动返回全部实体<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param MarketActivity
	 * @return MarketActivity
	 */
	 MarketActivity searchMarketActivityById(String id);
	 /**
	 * 
	 * <p>
	 * 根据区域市场推广活动编码查询市场推广活动返回全部实体<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param MarketActivity
	 * @return MarketActivity
	 */
	 MarketActivity searchMarketActivityByWorkflowNum(String activityWorkFlowNum);
	 /**
	 * 
	 * <p>
	 * 加载出发区域或到达区域树<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param userId
	 * @return List<DeptTree>
	 */
	 DeptTree loadDeliverOrArriveTree();
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
	 List<LineDept> searchLineDeptByTypeAndActivityId(String activityId,String lineRequest,String conditionType);
	 /**
	 * 
	 * <p>
	 * 保存市场推广活动参与部门<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param List<String>
	 * @return int
	 */
	 int saveActivityDept(List<String> deptCodes,String activityId,User user);
	/**
	 * 
	 * <p>
	 * 计算市场推广活动参与部门的营业部数<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-4-21
	 * @param List<String>
	 * @return int
	 */
	 int countActivityDeptNum(List<String> deptCodes);
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
	 int updateActivityClientById(ActivityClientBase acb,User user);
	/**
	 * 
	 * <p>
	 * 市场推广活动关联客户群时查询未使用的客户群<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-17
	 * @param String客户群名称
	 * @param User 用户实体
	 * @param 已选中的客户群列表
	 * @param 分页开始
	 * @param 每页最大值
	 * @return ActivityClientBase
	 */
	 List<ActivityClientBase> searchActivityClientByName(String clientBaseName,User user,
			 String[] clients,int start,int limit);
	/**
	 * 
	 * <p>
	 * 市场推广活动关联客户群时查询未使用的客户群<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-17
	 * @param String客户群名称
	 * @param User 用户实体
	 * @param 已选中的客户群列表
	 * @return ActivityClientBase
	 */
	 int countActivityClientByName(String clientBaseName,User user,String[] clients);
	/**
	 * 
	 * <p>
	 * 修改及下发市场推广活动权限<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-17
	 * @param Map<String,String>
	 * @return ActivityClientBase
	 */
	 void checkUpdateAuth(String activityId,User user);
	/**
	 * 
	 * <p>
	 * 校验客户群下发权限<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-17
	 * @param String activityId
	 * @param User user
	 * @return int
	 */
	 ActivityClientBase checkCreateClientBasePlanAuth(String activityId,User user,ActivityClientBase client);
	/**
	 * 
	 * <p>
	 * 修改市场推广活动客户群<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-17
	 * @param Map<String,String>
	 * @return ActivityClientBase
	 */
	 void updateMarketActivityClient(String activityId,List<ActivityClientBase> clients,User user);
    /**
	 * 
	 * <p>
	 * 客户群下发<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-17
	 * @param String activityId
	 * @param List<ActivityClientBase> clients
	 * @User user
	 * @return ActivityClientBase
	 */
	  void createClientBasePlan(String activityId,ActivityClientBase client,User user);
	 /**
	  * 生成区域营销工作流
	  * <p>
	  *	Description: TODO
	  * </p> 
	  * @author LiuY
	  * @date 2014-3-22
	  * @param marketActivity
	  * @return
	  * String
	  */
	 String processWorkFlow(MarketActivity marketActivity);
	 /**
	  * 区域营销工作流审批
	  * <p>
	  *	Description: TODO
	  * </p> 
	  * @author LiuY
	  * @date 2014-3-22
	  * @param busino
	  * @param wkStatus
	  * @param wkMan
	  * @param approveDate
	  * @param approvalSuggestin
	  * void
	  */
	 void workflowApprove(String busino,Boolean wkStatus,
				String wkMan,Date approveDate,String approvalSuggestin,User user);
	/**
	 * 
	 * <p>
	 * 根据部门ID查询该部门正在开展的全国市场推广活动<br />
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
	 * 删除未创建完成的市场推广活动<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-17
	 */
	  void updateCompleteOrDeleteUselessActivities();
	/**
	 * 
	 * <p>
	 * 下发客户群查询该市场推广活动包含的所有已使用但未下发的客户群<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-17
	 * @param Map<String,String>
	 * @return ActivityClientBase
	 */
	  List<ActivityClientBase> searchActivityClientBaseUsed(String activityId);
//	/**
//	 * 
//	 * <p>
//	 * 出现异常将异常信息插入到异常表中<br />
//	 * </p>
//	 * 
//	 * @author ZhouYuan
//	 * @version 0.1 2014-4-6
//	 * @param String activityId
//	 * @param String exceptionType
//	 * @param String handle
//	 */
//	  void insertActivityException(String acitivityId,String exceptionType);
//	/**
//	 * 
//	 * <p>
//	 * 处理异常<br />
//	 * </p>
//	 * 
//	 * @author ZhouYuan
//	 * @version 0.1 2014-4-6
//	 */
//	  void handleActivityException();
	/**
	 * 
	 * <p>
	 * 推送折扣信息到FOSS<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-4-6
	 * @param String activityId
	 * @param String exceptionType
	 * @param String handle
	 */
	public void marketActivityDiscountInfoToFoss(MarketActivity ma,String fossStatus);
	/**
	 * 
	 * <p>
	 * 验证折扣工作流号是否可用<br />
	 * </p>
	 * 
	 * @author lvhcongxin
	 * @version 0.1 2014-4-10
	 * @param String workflowNum
	 */
	void checkDiscountWorkflow(String workflowNum);
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
	  List<MarketActivity> searchMarketActivityForMarketPlan(String activityName,User user);
	/**
	 * 
	 * <p>
	 * 营销计划创建时查询市场推广活动下的优惠券抵扣类型与数量<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-4-10
	 */
	  List<ActivityOption> searchActivityOptionByActivityIdAndType(String activityId);
	/**
	 * 
	 * <p>
	 * 计算同名的市场推广活动数量<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-4-10
	 */
	  void checkHaveSamNameMarketActivity(String activityName);
}


