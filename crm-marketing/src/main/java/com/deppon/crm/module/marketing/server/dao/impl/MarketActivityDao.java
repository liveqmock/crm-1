package com.deppon.crm.module.marketing.server.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.marketing.server.dao.IMarketActivityDao;
import com.deppon.crm.module.marketing.shared.domain.activity.ActivityClientBase;
import com.deppon.crm.module.marketing.shared.domain.activity.ActivityDept;
import com.deppon.crm.module.marketing.shared.domain.activity.ActivityExceptionHandle;
import com.deppon.crm.module.marketing.shared.domain.activity.ActivityOption;
import com.deppon.crm.module.marketing.shared.domain.activity.DeptTree;
import com.deppon.crm.module.marketing.shared.domain.activity.LineDept;
import com.deppon.crm.module.marketing.shared.domain.activity.MarketActivity;
import com.deppon.crm.module.marketing.shared.domain.activity.MarketActivityConstance;
import com.deppon.crm.module.marketing.shared.domain.activity.MarketActivityVO;
import com.deppon.crm.module.marketing.shared.domain.activity.Multiple;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**   
 * <p>
 * Description:区域划分<br />
 * </p>
 * @title MarketActivityDao.java
 * @package com.deppon.crm.module.marketing.server.dao 
 * @author ZhouYuan
 * @version 2013-04-19
 */
public class MarketActivityDao extends iBatis3DaoImpl implements IMarketActivityDao {
	//命名空间
	private static final String NAME_SPACE = "com.deppon.crm.module.marketing.shared.domain.MarketActivity.";
	//新增市场推广活动
	private static final String INSERTMARKETACTIVITY = "insertMarketActivity";
	//新增市场推广活动多选条件
	private static final String INSERTMULTIPLE = "insertMultiple";
	//新增市场推广活动走货线路
	private static final String INSERTLINEDEPT = "insertLineDept";
	//新建市场推广活动
	private static final String INSERTOPTIONS = "insertOptions";
	//新建市场推广活动参与部门
	private static final String INSERTACTIVITYDEPT = "insertActivityDept";
	//根据市场推广活动ID修改市场推广活动
	private static final String UPDATEMARKETACTIVITYBYID = "updateMaketActivityById";
	//根据关联ID关联类型数据类型删除多选条件
	private static final String DELETEMULTIPLEBYTYPE = "deleteMultipleByType";
	//根据ID删除多选条件
	private static final String DELETEMULTIPLEBYID = "deleteMultipleById";
	//根据关联表ID关联表类型删除线路区域
	private static final String DELETELINEDEPTBYTYPE = "deleteLineDeptByType";
	//根据ID删除线路区域
	private static final String DELETELINEDEPTBYID = "deleteLineDeptById";
	//根据ID列表删除线路区域
	private static final String DELDTELINEDEPTBYIDLIST = "deleteLineDeptByIdList";
	//根据关联表ID定义类型删除多选项
	private static final String DELETEOPTIONSBYTYPE = "deleteOptionsByType";
	//根据市场推广活动ID删除参与部门
	private static final String DELETEACTIVITYDEPTBYACTIVITYID = "deleteActivityDeptByActivityId";
	//根据ID删除参与部门
	private static final String DELETEACTIVITYDEPTBYID = "deleteActivityDeptById";
	//根据部门列表删除参与部门
	private static final String DELETEACTIVITYDEPTBYIDLIST = "deleteActivityDeptByIdList";
	//根据用户的归属部门查询部门性质
	private static final String SEARCHAUTHDEPTCHARACTER = "searchAuthDeptCharacter";
	//添加客户群关联表信息
	private static final String INSERTACTIVITYCLIENT = "insertActivityClient";
	//加载全部树
	private static final String LOADAUTHTREE = "loadAuthTree";
	//根据多种条件查询客户群
	private static final String SEARCHMARKETACTIVITYBYMULCONDITION = "searchMarketActivityByMulCondition";
	//根据多种条件查询客户群数量
	private static final String COUNTMARKETACTIVITYBYMULCONDITION = "countMarketActivityByMulCondition";
	//查询市场推广活动明细
	private static final String SEARCHMARKETACTIVITYBYIDORWORKFLOWNUM = "searchMarketActivityByIdOrWorkFlowNum";
	//根据出发到达类型 关联表类型 关联表ID查询发货区域或到货区域树
	private static final String LOADDELIVERORARRIVETREE = "loadDeliverOrArriveTree";
	//根据关联表类型 关联表ID 查询走货线路
	private static final String SEARCHLINEDEPTBYTYPEANDACTIVITYID = "searchLineDeptByTypeAndActivityId";
	//查询市场推广活动营业部数量
	private static final String COUNTACTIVITYDEPTNUM = "countActivityDeptNum";
	//保存市场推广活动的参与部门
	private static final String SAVEACTIVITYDEPT = "saveActivityDept";
	//根据关系ID删除市场推广活动关联客户群
	private static final String DELETECLIENTBASEBYRELATIONID = "deleteClientBaseByRelationId";
	//根据ID修改客户群与市场推广活动关系 
	private static final String UPDATEACTIVITYCLIENTBYID = "updateActivityClientById";
	//市场推广活动关联客户群时查询未使用的客户群
	private static final String SEARCHACTIVITYCLIENTBYNAME = "searchActivityClientByName";
	//市场推广活动关联客户群时查询未使用的客户群数
	private static final String COUNTACTIVITYCLIENTBYNAME = "countActivityClientByName";
	//根据市场推广ID,客户群状态,或客户群关联ID查询客户群
	private static final String SEARCHACTIVITYCLIENTBYMULTIPLECONDITION = "searchActivityClientByMultipleCondition";
	//根据客户群ID列表查询客户群
	private static final String SEARCHACTIVITYCLIENTBYCLIENTIDS = "searchActivityClientByClientIds";
	//查询市场推广活动修改及下发权限
	private static final String SEARCHUPDATEAUTH ="searchUpdateAuth";
	//查询市场推广活动部门树
	private static final String SEARCHTREE = "searchTree";
	//根据市场推广活动ID查询关联客户群
	private static final String SEARCHACTIVITYCLIENTBYACTIVITYID = "searchActivityClientByActivityId";
	//根据市场推广活动ID查询权限部门列表数
	private static final String COUNTACTIVITYDEPTBYACTIVITYID = "countActivityDeptByActivityId";
	//查询最新的市场推广活动
	private static final String SEARCHLATESTMARKETACTIVITYBYDEPTID = "searchLatestMarketActivityByDeptId";
	//查询中途关掉页面没有保存完整的市场推广活动
	private static final String SEARCHUSELESSACTIVITIES = "searchUselessActivities";
	//加载单层部门树
	private static final String LOADAUTHTREETIER = "loadAuthTreeTier";
	//新增市场推广活动异常信息
	private static final String INSERTACTIVITYEXCEPTION = "insertActivityException";
	//更新市场推广活动异常处理状态
	private static final String UPDATEACTIVITYEXCEPTIONBYID = "updateActivityExceptionById";
	//查询未处理的市场推广活动异常
	private static final String SEARCHACTIVITYEXCEPTIONUNHANDLE = "searchActivityExeptionUnhandle";
	//查询折扣工作流是否被使用
	private static final String CHECKWORKFLOWISREPEAT="checkWorkflowIsRepeat";
	//查询待下发的客户群并生成相应的计划
	private static final String CREATECLIENTBASEPLAN = "createClientBasePlan";
	//查询关联的市场推广活动的客户群数量
	private static final String COUNTCLIENTBASEDETAIL = "countClientBaseDetail";
	//生成客户群明细报表
	private static final String CREATECLIENTDETAILREPORT = "createClientDetailReport";
	//营销计划创建时查询现在可用的市场推广活动
	private static final String SEARCHMARKETACTIVITYFORMARKETPLAN = "searchMarketActivityForMarketPlan";
	//营销计划创建时查询市场推广活动下的优惠券抵扣类型与数量
	private static final String SEARCHACTIVITYOPTIONBYIDANDTYPE = "searchActivityOptionByActivityIdAndType";
	//
	private static final String COUNTMARKETACTIVITYBYNAME = "countMarketActivityByName";
	//根据ID列表删除市场推广活动
	private static final String DELETEMARKETACTIVITYBYIDS = "deleteMarketActivityByIds";
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
	public int insertMarketActivity(MarketActivity ma) {
		return getSqlSession().insert(NAME_SPACE+INSERTMARKETACTIVITY,ma);
	}
	/**
	 * 
	 * <p>
	 * 新建市场推广活动多选信息<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param ma
	 * @return int
	 */
	@Override
	public int insertMultiple(Multiple multiple) {
		return getSqlSession().insert(NAME_SPACE+INSERTMULTIPLE,multiple);
	}
	/**
	 * 
	 * <p>
	 * 新建市场推广活动多选信息<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param ma
	 * @return int
	 */
	@Override
	public int insertLineDept(LineDept lineDept) {
		return getSqlSession().insert(NAME_SPACE+INSERTLINEDEPT,lineDept);
	}
	/**
	 * 
	 * <p>
	 * 新建市场推广活动折扣优惠申请物品信息<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param ma
	 * @return int
	 */
	@Override
	public int insertOptions(ActivityOption option) {
		return getSqlSession().insert(NAME_SPACE+INSERTOPTIONS,option);
	}
	/**
	 * 
	 * <p>
	 * 新建市场推广活动参与部门<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param ma
	 * @return int
	 */
	@Override
	public int insertActivityDept(ActivityDept acDept) {
		return getSqlSession().insert(NAME_SPACE+INSERTACTIVITYDEPT,acDept);
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
		return getSqlSession().update(NAME_SPACE+UPDATEMARKETACTIVITYBYID,ma);
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
		return getSqlSession().delete(NAME_SPACE+DELETEMULTIPLEBYTYPE,multiple);
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
		return getSqlSession().delete(NAME_SPACE+DELETEMULTIPLEBYID,id);
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
		return getSqlSession().delete(NAME_SPACE+DELETELINEDEPTBYTYPE,lineDept);
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
		return getSqlSession().delete(NAME_SPACE+DELETELINEDEPTBYID,id);
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
		return getSqlSession().delete(NAME_SPACE+DELDTELINEDEPTBYIDLIST,depts);
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
		return getSqlSession().delete(NAME_SPACE+DELETEOPTIONSBYTYPE,option);
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
		return getSqlSession().delete(NAME_SPACE+DELETEACTIVITYDEPTBYACTIVITYID,activityId);
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
		return getSqlSession().delete(NAME_SPACE+DELETEACTIVITYDEPTBYID,id);
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
	public int deleteActivityDeptByIdList(List<ActivityDept> depts) {
		return getSqlSession().delete(NAME_SPACE+DELETEACTIVITYDEPTBYIDLIST,depts);
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
	 @Override
	 public String searchAuthDeptCharacter(String id){
		 return (String)getSqlSession().selectOne(NAME_SPACE+SEARCHAUTHDEPTCHARACTER,id);
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
	@Override
	public int insertActivityClient(ActivityClientBase clientBase) {
		return getSqlSession().insert(NAME_SPACE+INSERTACTIVITYCLIENT,clientBase);
	}
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
//	@Override
//	public List<DeptTree> loadTreeRoot(String userId) {
//		return (List<DeptTree>)getSqlSession().selectList(NAME_SPACE+LOADTREEROOT,userId) ;
//	}
	 /**
	 * 
	 * <p>
	 * 加载部门树<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param clientBase
	 * @return int
	 */
	@Override
	public List<DeptTree> loadAuthTree(String userId ) {
		return (List<DeptTree>)getSqlSession().selectList(NAME_SPACE+LOADAUTHTREE,userId);
	}
	 /**
	 * 
	 * <p>
	 * 根据多种条件查询市场推广活动<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param map
	 * @return List<DeptTree>
	 */
	@Override
	public List<MarketActivityVO> searchMarketActivityByMulCondition(MarketActivityVO mav,int start,int limit) {
		RowBounds rb = new RowBounds(start, limit);
		return (List<MarketActivityVO>) getSqlSession().selectList(NAME_SPACE+SEARCHMARKETACTIVITYBYMULCONDITION,mav,rb);
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
	public int countMarketActivityByMulCondition(MarketActivityVO mav) {
		return (Integer)getSqlSession().selectOne(NAME_SPACE+COUNTMARKETACTIVITYBYMULCONDITION,mav);
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
		return (MarketActivity)getSqlSession().selectOne(NAME_SPACE+SEARCHMARKETACTIVITYBYIDORWORKFLOWNUM,ma);
	}
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
	@Override
	public List<DeptTree> loadDeliverOrArriveTree() {
		return (List<DeptTree>)getSqlSession().selectList(NAME_SPACE+LOADDELIVERORARRIVETREE);
	}
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
	@Override
	public List<LineDept> searchLineDeptByTypeAndActivityId(
			Map<String, String> map) {
		return (List<LineDept>)getSqlSession().selectList(NAME_SPACE+SEARCHLINEDEPTBYTYPEANDACTIVITYID,map);
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
		return (Integer)getSqlSession().selectOne(NAME_SPACE+COUNTACTIVITYDEPTNUM,deptCodes);
	}
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
	@Override
	public int deleteClientBaseByRelationId(Map<String, String> map) {
		return getSqlSession().delete(NAME_SPACE+DELETECLIENTBASEBYRELATIONID,map);
	}
	/**
	 * 
	 * <p>
	 * 修改市场推广活动与客户群关系表的状态<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param List<String>
	 * @return int
	 */
	@Override
	public int updateActivityClientById(ActivityClientBase acb){
		return getSqlSession().update(NAME_SPACE+UPDATEACTIVITYCLIENTBYID,acb);
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
	public List<ActivityClientBase> searchActivityClientByName(Map<String,Object> map,int start,int limit){
		RowBounds rb = new RowBounds(start,limit);
		return (List<ActivityClientBase>)getSqlSession().selectList(NAME_SPACE+SEARCHACTIVITYCLIENTBYNAME,map,rb);
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
	public int countActivityClientByName(Map<String,Object> map){
		return (Integer)getSqlSession().selectOne(NAME_SPACE+COUNTACTIVITYCLIENTBYNAME,map);
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
	public List<ActivityClientBase> searchActivityClientByMultipleCondition(Map<String,Object> map){
		return (List<ActivityClientBase>)getSqlSession().selectList(NAME_SPACE+SEARCHACTIVITYCLIENTBYMULTIPLECONDITION,map);
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
	public ActivityClientBase searchActivityClientByReId(Map<String,String> map){
		return (ActivityClientBase)getSqlSession().selectOne(NAME_SPACE+SEARCHACTIVITYCLIENTBYMULTIPLECONDITION,map);
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
		return (List<ActivityClientBase>)getSqlSession().selectList(NAME_SPACE+SEARCHACTIVITYCLIENTBYCLIENTIDS,clientIds);
	}
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
	@Override
	public int searchUpdateAuth(Map<String,String> map){
		return (Integer)getSqlSession().selectOne(NAME_SPACE+SEARCHUPDATEAUTH,map);
	}
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
	@Override
	public List<DeptTree> searchTree(Map<String, String> map) {
		return (List<DeptTree>)getSqlSession().selectList(NAME_SPACE+SEARCHTREE,map);
	}
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
	@Override
	public List<ActivityClientBase> searchActivityClientByActivityId(
			Map<String, String> map) {
		return (List<ActivityClientBase>)getSqlSession().selectList(NAME_SPACE+SEARCHACTIVITYCLIENTBYACTIVITYID,map);
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
		return (Integer)getSqlSession().selectOne(NAME_SPACE+COUNTACTIVITYDEPTBYACTIVITYID,activityId);
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
	public MarketActivity searchLatestMarketActivityByDeptId(String deptId) {
		return (MarketActivity)getSqlSession().selectOne(NAME_SPACE+SEARCHLATESTMARKETACTIVITYBYDEPTID,deptId);
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
	@SuppressWarnings("unchecked")
	@Override
	public List<MarketActivity> searchUselessOrCompleteActivities(String condition) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("activitySourceCode", MarketActivityConstance.ACTIVITY_RELATION_ACTIVITY);
		map.put("fileSourceType", MarketActivityConstance.SOURCE_TYPE_ACTIVITY );
		map.put("condition", condition);
		return (List<MarketActivity>)getSqlSession().selectList(NAME_SPACE+SEARCHUSELESSACTIVITIES,map);
	}
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
	@Override
	public List<DeptTree> loadAuthTreeTier(String parentId) {
		return (List<DeptTree>)getSqlSession().selectList(NAME_SPACE+LOADAUTHTREETIER,parentId);
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
		return getSqlSession().insert(NAME_SPACE+INSERTACTIVITYEXCEPTION,aeh);
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
		return getSqlSession().update(NAME_SPACE+UPDATEACTIVITYEXCEPTIONBYID,aeh);
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
		return (List<ActivityExceptionHandle>)getSqlSession().selectList(NAME_SPACE+SEARCHACTIVITYEXCEPTIONUNHANDLE,handle);
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
		
		return (Integer)this.getSqlSession().selectOne(NAME_SPACE+CHECKWORKFLOWISREPEAT, workflowNum)>0?true:false;
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
		this.getSqlSession().selectOne(NAME_SPACE+CREATECLIENTBASEPLAN);
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
		this.getSqlSession().selectOne(NAME_SPACE+COUNTCLIENTBASEDETAIL);		
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
		this.getSqlSession().selectOne(NAME_SPACE+CREATECLIENTDETAILREPORT);
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
	public List<MarketActivity> searchMarketActivityForMarketPlan(Map<String,String> map) {
		return this.getSqlSession().selectList(NAME_SPACE+SEARCHMARKETACTIVITYFORMARKETPLAN,map);
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
			Map<String, String> map) {
		return (List<ActivityOption>)this.getSqlSession().selectList(NAME_SPACE+SEARCHACTIVITYOPTIONBYIDANDTYPE,map);
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
		return (Integer)this.getSqlSession().selectOne(NAME_SPACE+COUNTMARKETACTIVITYBYNAME,activityName);
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
	public int deleteMarketActivityByIds(List<MarketActivity> activites){
		return this.getSqlSession().delete(NAME_SPACE+DELETEMARKETACTIVITYBYIDS,activites);
	}
}
