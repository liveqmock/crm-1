package com.deppon.crm.module.marketing.server.manager.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import com.deppon.bpms.module.shared.domain.ProcessInfo;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.bps.server.manager.impl.BpsWorkflowManager;
import com.deppon.bpmsapi.module.server.api.exception.BPMSException;
import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.esb.IEsbToFossAsyOperate;
import com.deppon.crm.module.client.esb.domain.MarketingActivityRequest;
import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.crm.module.common.file.manager.IFileManager;
import com.deppon.crm.module.marketing.bpsworkflow.util.BpsConstant;
import com.deppon.crm.module.marketing.server.manager.IClientBaseManager;
import com.deppon.crm.module.marketing.server.manager.IMarketActivityManager;
import com.deppon.crm.module.marketing.server.service.IMarketActivityService;
import com.deppon.crm.module.marketing.server.utils.MarketActivityUtils;
import com.deppon.crm.module.marketing.server.utils.MarketActivityValidator;
import com.deppon.crm.module.marketing.shared.domain.activity.ActivityClientBase;
import com.deppon.crm.module.marketing.shared.domain.activity.ActivityDept;
import com.deppon.crm.module.marketing.shared.domain.activity.ActivityExceptionHandle;
import com.deppon.crm.module.marketing.shared.domain.activity.ActivityOption;
import com.deppon.crm.module.marketing.shared.domain.activity.ClientBase;
import com.deppon.crm.module.marketing.shared.domain.activity.DeptTree;
import com.deppon.crm.module.marketing.shared.domain.activity.LineDept;
import com.deppon.crm.module.marketing.shared.domain.activity.MarketActivity;
import com.deppon.crm.module.marketing.shared.domain.activity.MarketActivityConstance;
import com.deppon.crm.module.marketing.shared.domain.activity.MarketActivityVO;
import com.deppon.crm.module.marketing.shared.domain.activity.Multiple;
import com.deppon.crm.module.marketing.shared.exception.MarketActivityException;
import com.deppon.crm.module.marketing.shared.exception.MarketActivityExceptionType;
import com.deppon.foss.framework.exception.GeneralException;

/**
 * <p>
 * Description:市场推广活动<br />
 * </p>
 * @title MarketActivityManager.java
 * @package com.deppon.crm.module.marketing.server.manager
 * @author ZhouYuan
 * @version 2013-04-19
 */
public class MarketActivityManager implements IMarketActivityManager{
	Logger log=Logger.getLogger(MarketActivityManager.class.getName());
	//市场推广活动SERVICE
	private IMarketActivityService marketActivityService;
	private IClientBaseManager clientBaseManager;
	private BpsWorkflowManager bpsWorkflowManager;
	private IFileManager fileManager;
	private IEsbToFossAsyOperate esbToFossAsyOperateImpl;
	public void setMarketActivityService(
			IMarketActivityService marketActivityService) {
		this.marketActivityService = marketActivityService;
	}
	
	public void setClientBaseManager(IClientBaseManager clientBaseManager) {
		this.clientBaseManager = clientBaseManager;
	}
	/**
	 * @return the bpsWorkflowManager
	 */
	public BpsWorkflowManager getBpsWorkflowManager() {
		return bpsWorkflowManager;
	}

	/**
	 * @param bpsWorkflowManager the bpsWorkflowManager to set
	 */
	public void setBpsWorkflowManager(BpsWorkflowManager bpsWorkflowManager) {
		this.bpsWorkflowManager = bpsWorkflowManager;
	}

	public void setFileManager(IFileManager fileManager) {
		this.fileManager = fileManager;
	}

	public IEsbToFossAsyOperate getEsbToFossAsyOperateImpl() {
		return esbToFossAsyOperateImpl;
	}

	public void setEsbToFossAsyOperateImpl(
			IEsbToFossAsyOperate esbToFossAsyOperateImpl) {
		this.esbToFossAsyOperateImpl = esbToFossAsyOperateImpl;
	}

	/**
	 * 
	 * <p>
	 * 初始化市场推广活动<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-6
	 * @param acDept
	 * @return int
	 */
	@Override
	public MarketActivity initMarketActivity(User user) {
		//获得用户的部门性质,并校验
		getUserDeptCharacter(user);
		//新建空市场推广活动并返回市场推广活动ID
		MarketActivity ma = new MarketActivity();
		//设置创建人
		ma.setCreateUser(user.getEmpCode().getId());
		//设置创建时间
		ma.setCreateDate(new Date());
		//保存空的市场推广活动到表中
		marketActivityService.insertMarketActivity(ma);	
		return ma;
	}
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
	@Transactional
	@Override
	public void insertLineDept(List<LineDept> lineDepts,String lineRequest,String activityId) {
		//校验参数是否合法
		MarketActivityValidator.checkLineDeptIsIllegal(lineDepts, lineRequest);
		//新建走货线路实体用于删除原有的走货线路
		LineDept line = new LineDept();
		//设置市场推广活动ID
		line.setConditionId(activityId);
		//设置关联类型为市场推广活动
		line.setConditionType(MarketActivityConstance.ACTIVITY_RELATION_ACTIVITY);
		//删除原来的走货线路
		marketActivityService.deleteLineDeptByType(line);
		//新建市场推广活动实体用于修改线路区域要求
		MarketActivity ma = new MarketActivity();
		//设置市场推广活动ID
		ma.setId(activityId);
		//设置走货线路
		ma.setLineRequest(lineRequest);
		//修改线路区域要求
		marketActivityService.updateMaketActivityById(ma);
		//调用转换工具类,列表中的每个实体添加关联类型为市场推广活动
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("conditionType", MarketActivityConstance.ACTIVITY_RELATION_ACTIVITY);
		map.put("conditionId", activityId);
		MarketActivityUtils.addValueToObject(lineDepts,map);
		//循环结果集将线路区域保存到线路区域表中
		for( LineDept lineDept : lineDepts ){
			//进行插入操作
			marketActivityService.insertLineDept(lineDept);
		}
	}
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
	@Transactional
	@Override
	public String saveMarketActivityAndDepts(MarketActivity ma,List<LineDept> lineDepts,
			String lineRequest,List<String> deptCodes,User user){
		//保存参与部门
		saveActivityDept(deptCodes, ma.getId(), user);
		if( lineDepts!=null && lineDepts.size()>0){
			//保存走货线路
			insertLineDept(lineDepts, lineRequest, ma.getId());
		}
		//保存市场推广互动
		String workFlowNum = saveMarketActivity(ma, user);
		return workFlowNum;
	}
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
	@Transactional
	@Override
	public String saveMarketActivity(MarketActivity ma,User user) {
		String discountWorkFlowNum = null;
		//获得市场推广活动部门性质
		String deptCharacter = getUserDeptCharacter(user);		
		//校验用户权限及字段合法性
		MarketActivityValidator.checkMarketActivity(ma, deptCharacter);
		//校验市场推广活动名称是否重复
		checkHaveSamNameMarketActivity(ma.getActivityName());
		//查询客户群在数据库中的最新状态
		List<ActivityClientBase> clientBase = marketActivityService.searchActivityClientByClientIds(ma.getClientBase());
		//校验查询出的客户群是否跟传入值一致
		MarketActivityValidator.checkActivityClient(ma.getClientBase(), clientBase);
		//获得参与部门数
		int deptNum = marketActivityService.countActivityDeptByActivityId(ma.getId());
		//校验参与部门是否为空
		MarketActivityValidator.checkActivityDept(deptNum);
		//设置市场推广活动前两位
		ma.setActivityCode(MarketActivityUtils.createActivityCode(ma));
		//设置创建部门
		ma.setDeptId(user.getEmpCode().getDeptId().getId());
		//设置创建时间
		ma.setCreateDate(new Date());
		//设置创建人ID
		ma.setCreateUser(user.getEmpCode().getId());
		//设置市场推广活动状态为已制定
		ma.setActivityStatus(MarketActivityConstance.ACTIVITY_STATUS_ESTABLISHED);
		//保存市场推广活动
		int updateCount = marketActivityService.updateMaketActivityById(ma);
		//检查是否保存成功
		MarketActivityValidator.checkSaveMarketActivitySuccess(updateCount);
		//转换一级行业列表为多选实体
		List<Multiple> trade = MarketActivityUtils.convertArrayToMultipleList(ma.getCustTrade(),MarketActivityConstance.ACTIVITY_RELATION_ACTIVITY,
				ma.getId(),MarketActivityConstance.TYPE_TRADE);
		//转换二级行业列表为多选实体
		List<Multiple> secondTrade = MarketActivityUtils.convertArrayToMultipleList(ma.getSecondTrade(),MarketActivityConstance.ACTIVITY_RELATION_ACTIVITY,
				ma.getId(),MarketActivityConstance.TYPE_SECOND_TRADE);
		//转换产品类型为多选实体
		List<Multiple> productType = MarketActivityUtils.convertArrayToMultipleList(ma.getProductType(),MarketActivityConstance.ACTIVITY_RELATION_ACTIVITY,
				ma.getId(),MarketActivityConstance.TYPE_PRODUCT_TYPE);
		//转换订单来源为多选实体
		List<Multiple> orderSource = MarketActivityUtils.convertArrayToMultipleList(ma.getOrderSource(),MarketActivityConstance.ACTIVITY_RELATION_ACTIVITY,
				ma.getId(),MarketActivityConstance.TYPE_ORDER_SOURCE);
		//保存一级行业
		for( Multiple m : trade ){
			marketActivityService.insertMultiple(m);
		}
		//保存二级行业
		for( Multiple m : secondTrade ){
			marketActivityService.insertMultiple(m);
		}
		//保存产品类型
		for( Multiple m : productType ){
			marketActivityService.insertMultiple(m);
		}
		//保存订单来源 
		for( Multiple m : orderSource ){
			marketActivityService.insertMultiple(m);
		}
		
		//建立设置列表
//		Map<String,Object> setType = new HashMap<String,Object>();
//		//添加活动ID
//		setType.put("activityId", ma.getId());
//		//设置活动ID
//		MarketActivityUtils.addValueToObject(depts, setType);
//		for( ActivityDept d : depts ){
//			marketActivityService.insertActivityDept(d);
//		}
		//建立设置列表
		Map<String,Object> setType = new HashMap<String,Object>();
		//添加活动ID
		setType.put("activityId", ma.getId());
		//如果抵扣类型为优惠券
		if( MarketActivityConstance.OPTION_COUPON.equals(ma.getPreferType())){
			List<ActivityOption> couponInfo = ma.getCouponInfo();
			setType.put("infoType", MarketActivityConstance.OPTION_COUPON);
			MarketActivityUtils.addValueToObject(couponInfo, setType);
			for( ActivityOption opt : couponInfo ){
				marketActivityService.insertOptions(opt);
			}
		//如果抵扣类型为折扣
		}else if( MarketActivityConstance.OPTION_DISCOUNT.equals(ma.getPreferType())){
			List<ActivityOption> discountInfo = ma.getDiscountInfo();
			setType.put("infoType", MarketActivityConstance.OPTION_DISCOUNT);
			MarketActivityUtils.addValueToObject(discountInfo, setType);
			for( ActivityOption opt : discountInfo ){
				marketActivityService.insertOptions(opt);
			}
		}
		//如果申请物不为空
		List<ActivityOption> applyGoods = ma.getMaterialInfo();
		if( applyGoods != null && applyGoods.size() >0 ){
			setType = new HashMap<String,Object>();
			setType.put("activityId", ma.getId());
			setType.put("infoType", MarketActivityConstance.OPTION_APPLYGOODS);
			MarketActivityUtils.addValueToObject(applyGoods, setType);
			for( ActivityOption opt : applyGoods ){
				marketActivityService.insertOptions(opt);
			}
		}
		/**
		 * 保存客户群
		 */
		addActivityClientBaseList(ma.getId(),clientBase,user);
		MarketActivity newActivity = new MarketActivity();
		newActivity.setId(ma.getId());
		/**
		 * 如果是区域市场推广活动申请区域市场推广活动审批工作流
		 */
		if( MarketActivityConstance.ACTIVITY_TYPE_REGION.equals(ma.getActivityType())){		
			newActivity.setActivityStatus(MarketActivityConstance.ACTIVITY_STATUS_PROCESSES);
			//调用生成工作流方法
			processWorkFlow(newActivity);
			discountWorkFlowNum = newActivity.getActivityWorkflowNum();
		}
		/**
		 * 如果是全国市场推广活动且包含价格折扣,将价格折扣信息推送到FOSS
		 */
		if( MarketActivityConstance.ACTIVITY_TYPE_NATION.equals(ma.getActivityType())
				&& MarketActivityConstance.OPTION_DISCOUNT.equals(ma.getPreferType())){
			MarketActivity discountMa = searchMarketActivityById(ma.getId());
			marketActivityDiscountInfoToFoss(discountMa,MarketActivityConstance.FOSS_ACTIVITY_STATUS_Y);
			//修改市场推广活动状态为折扣已生效
			newActivity.setActivityStatus(MarketActivityConstance.ACTIVITY_STATUS_EXECUTED);
			marketActivityService.updateMaketActivityById(newActivity);
		}
		return discountWorkFlowNum;
	}
	
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
	@Override
	public String getUserDeptCharacter(User user) {
		//获得部门ID
		String deptId = user.getEmpCode().getDeptId().getId();
		//获得部门性质
		String deptCharacter = MarketActivityValidator.checkUserAuthDept(user);
		//如果部门不为营销活动管理组或快递市场营销组
		if( StringUtils.isEmpty( deptCharacter )){
			//查询是否是大区或事业部 
			deptCharacter = marketActivityService.searchAuthDeptCharacter(deptId);
		}
		//校验如果既不是营销活动管理组或快递市场营销组也不为大区和事业部
		MarketActivityValidator.checkDeptCharacter(deptCharacter);
		return deptCharacter;
	}
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
	@Override
	public DeptTree loadAuthTree(String userId) {
		List<DeptTree> deptList = marketActivityService.loadAuthTree(userId);
		MarketActivityUtils.setCannotSelectDept(deptList);
		DeptTree root = MarketActivityUtils.organizedDeptTree(deptList);
		return root;
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
	public List<MarketActivityVO> searchMarketActivityByMulCondition( MarketActivityVO mav,User user,int start,int limit){	
		mav.setCreateUser(user.getId());
		String deptCharacter = MarketActivityValidator.checkUserAuthDept(user);
		mav.setDeptCharacter(deptCharacter);
		mav.setDeptId(user.getEmpCode().getDeptId().getId());
		return marketActivityService.searchMarketActivityByMulCondition(mav, start, limit);
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
	public int countMarketActivityByMulCondition( MarketActivityVO mav ,User user){
		mav.setCreateUser(user.getId());
		String deptCharacter = MarketActivityValidator.checkUserAuthDept(user);
		mav.setDeptCharacter(deptCharacter);
		mav.setDeptId(user.getEmpCode().getDeptId().getId());
		return marketActivityService.countMarketActivityByMulCondition(mav);
	}
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
	@Override
	public MarketActivity searchMarketActivityById(String id){
		MarketActivity ma = new MarketActivity();
		ma.setId(id);
		return marketActivityService.searchMarketActivityByIdOrWorkFlowNum(ma);
	}
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
	@Override
	public MarketActivity searchMarketActivityByWorkflowNum(String activityWorkFlowNum){
		MarketActivity ma = new MarketActivity();
		ma.setActivityWorkflowNum(activityWorkFlowNum);
		return marketActivityService.searchMarketActivityByIdOrWorkFlowNum(ma);
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
	public DeptTree loadDeliverOrArriveTree(){
		 //如果线路区域要求为出发区域或到达区域
		 List<DeptTree> deptList =  marketActivityService.loadDeliverOrArriveTree();
		 MarketActivityUtils.setCannotSelectDept(deptList);
		 DeptTree root = MarketActivityUtils.organizedDeptTree(deptList);
		 return root;
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
	public List<LineDept> searchLineDeptByTypeAndActivityId(String activityId,
			String lineRequest,String conditionType) {
		//如果线路区域要求为走货线路
		if( MarketActivityConstance.LINE_TYPE_LEAVE_ARRIVE.equals(lineRequest)){
			return marketActivityService.searchLineDeptByTypeAndActivityId(activityId, conditionType);
		}
		return null;
	}
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
	@Override
	@Transactional
	public int saveActivityDept(List<String> deptCodes, String activityId,
			User user) {
		//参与部门中营业部的数量
		int deptNum = 0;
		//获得并校验客户权限
		getUserDeptCharacter(user);		
		//将参与部门编码转换为参与部门实体		
		List<ActivityDept> activityDepts = MarketActivityUtils.covertDeptCodesToActivityDept(deptCodes, activityId);
		//校验市场推广活动部门是否合法
		MarketActivityValidator.checkActivityDeptActivityIdAndDeptCode(activityDepts);
		//根据市场推广活动id删除已保存的参与部门
		marketActivityService.deleteActivityDeptByActivityId(activityId);
		if( activityDepts.size()>0 ){
			for( ActivityDept dept : activityDepts){
				marketActivityService.insertActivityDept(dept);
			}
			deptNum = marketActivityService.countActivityDeptNum(deptCodes);
		}
		return deptNum;
	}
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
	@Override
	public int countActivityDeptNum(List<String> deptCodes) {
		return marketActivityService.countActivityDeptNum(deptCodes);
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
	 @Transactional
	 @Override
	 public int updateActivityClientById(ActivityClientBase acb,User user){
		 //设置修改时间
		 acb.setModifyDate(new Date());
		 //设置修改人
		 acb.setModifyUser(user.getEmpCode().getId());
		 return marketActivityService.updateActivityClientById(acb);
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
	 public List<ActivityClientBase> searchActivityClientByName(String clientBaseName,User user,String[] clients,
			 int start,int limit){
		 String deptCharacter = MarketActivityValidator.checkUserAuthDept(user);
		 return marketActivityService.searchActivityClientByName(clientBaseName, user,
				 MarketActivityConstance.CLIENTBASE_STATUS_UNUSED,deptCharacter,clients, start, limit);
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
	 public int countActivityClientByName(String clientBaseName,User user,String[] clients){
		 String deptCharacter = MarketActivityValidator.checkUserAuthDept(user);
		 return marketActivityService.countActivityClientByName(clientBaseName, user, 
				 MarketActivityConstance.CLIENTBASE_STATUS_UNUSED,deptCharacter,clients);
	 }
	/**
	 * 
	 * <p>
	 * 获得修改市场推广活动权限<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-17
	 * @param Map<String,String>
	 * @return ActivityClientBase
	 */
	 @Override
	 public void checkUpdateAuth(String activityId,User user){
		 //查询市场推广活动最新状态
		 List<MarketActivityVO> mas = searchMarketActivityVOByActivityId(activityId,user);
		 //校验是否有权对该市场推广活动进行修改
		 MarketActivityValidator.checkUpdateAuth(mas,user);
	 } 
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
	 @Override
	 public ActivityClientBase checkCreateClientBasePlanAuth(String activityId,User user,ActivityClientBase client){
		//查询市场推广活动最新状态
		 List<MarketActivityVO> mas = searchMarketActivityVOByActivityId(activityId,user);
		//查询要下发的客户群
		 ActivityClientBase acClient = marketActivityService.searchActivityClientByReId(client.getId());
		 acClient.setPlanStartTime(client.getPlanStartTime());
		 acClient.setPlanEndTime(client.getPlanEndTime());
		 //校验要下发的客户群和市场推广活动
		 MarketActivityValidator.checkCreateClientBasePlanAuth(mas, acClient, user);
		 return acClient;
	 } 
	/**
	 * 
	 * <p>
	 * 修改市场推广活动客户群<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-17
	 * @param String activityId
	 * @param List<ActivityClientBase> clients
	 * @User user
	 * @return ActivityClientBase
	 */
	 @Transactional
	 @Override
	 public void updateMarketActivityClient(String activityId,List<ActivityClientBase> clients,User user){
		 //获得并校验是否有权做修改
		 checkUpdateAuth(activityId,user);
		 //校验前台传入的客户群列表是否为空
		 MarketActivityValidator.checkUpdateClientIsNull(clients);
		 //获得该市场推广活动全部关联客户群
		 List<ActivityClientBase> searchClients = marketActivityService.searchActivityClientByMultipleCondition(activityId,null, null);
		 //获得删除列表
		 List<ActivityClientBase> delList = MarketActivityUtils.getDeleteActivityClientList(clients, searchClients);
		 //校验是否可以被删除
		 MarketActivityValidator.checkDeleteActivityClientBase(delList);
		 //获得添加实体
		 List<ActivityClientBase> addList = MarketActivityUtils.getInsertActivityClientList(clients, searchClients);
		
		 //新建客户群实体
		 ClientBase newClient =  null;
		 //新建删除状态
		 boolean delStatus = false;
		 //循环删除列表
		 for( ActivityClientBase delClient :delList ){
			 //给客户群实体赋值
			 newClient = new ClientBase();
			 //设置客户群ID
			 newClient.setId(delClient.getClientBaseId());
			 //设置客户群ID为未使用
			 newClient.setClientBaseStatus(MarketActivityConstance.CLIENTBASE_STATUS_UNUSED);
			 //设置修改时间
			 newClient.setModifyDate(new Date());
			 //设置修改人
			 newClient.setModifyUser(user.getEmpCode().getId());
			 //修改关联客户群为未使用
			 delStatus = clientBaseManager.updateClientBaseStatus(newClient);
			 //校验是否删除成功
			 MarketActivityValidator.checkActivityClientBaseAddOrDelStatus(delStatus);
			 //删除关联关系
			 marketActivityService.deleteClientBaseByRelationId(delClient.getId(), MarketActivityConstance.CLIENTBASE_STATUS_USEING);
		 }
		 if( addList.size()>0){
			 //根据新增实体查询数据库获得客户群最新状态
			 List<ActivityClientBase> searchAddList = marketActivityService.searchActivityClientByClientIds(addList);
			 //校验新增客户群是否合法
			 MarketActivityValidator.checkActivityClient(addList,searchAddList);		 
			 //添加客户群
			 addActivityClientBaseList(activityId,searchAddList,user);
		 }
		 //设置市场推广活动查询实体
		 MarketActivity searchCondition = new MarketActivity();
		 //设置市场推广活动ID
		 searchCondition.setId(activityId);
		 //新建修改实体
		 MarketActivity updateM = new MarketActivity();
		 //查询市场推广活动
		 MarketActivity ma = marketActivityService.searchMarketActivityByIdOrWorkFlowNum(searchCondition);
		 //设置市场推广活动ID用于修改市场推广活动状态
		 updateM.setId(ma.getId());
		 //获得市场推广活动下一个状态
		 String status = MarketActivityUtils.getMarketActivityChangeClinetStatus(ma);
		 //设置市场推广活动状态
		 updateM.setActivityStatus(status);
		 //修改市场推广活动状态
		 marketActivityService.updateMaketActivityById(updateM);
	 }
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
	 @Transactional
	 @Override
	 public void createClientBasePlan(String activityId,ActivityClientBase client,User user){
		//校验客户群下发权限并获得客户群最新状态
		 ActivityClientBase searchClient = checkCreateClientBasePlanAuth(activityId, user, client);
		 //创建客户群实体
		 ClientBase templeClient =  new ClientBase();
		 //添加客户群ID
		 templeClient.setId( client.getClientBaseId());
		 //将客户群状态改为下发中
		 templeClient.setClientBaseStatus(MarketActivityConstance.CLIENTBASE_STATUS_ISSUED_IN);
		 templeClient.setModifyDate(new Date());
		 templeClient.setModifyUser(user.getEmpCode().getId());
		 //修改客户群状态
		 boolean updateClient = clientBaseManager.updateClientBaseStatus(templeClient);
		 //校验是否修改成功
		 MarketActivityValidator.checkActivityClientBaseAddOrDelStatus(updateClient);
		 //修改关联表中客户群状态为下发中
		 searchClient.setClientBaseStatus(MarketActivityConstance.CLIENTBASE_STATUS_ISSUED_IN);
		 //添加修改时间
		 searchClient.setModifyDate(new Date());
		 //添加修改人
		 searchClient.setModifyUser(user.getEmpCode().getId());
		 //修改关联表类型为下发中
		 marketActivityService.updateActivityClientById(searchClient);
		 
	 }
	 /**
	 * 
	 * <p>
	 * 添加客户群到市场推广活动<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-17
	 * @param String activityId
	 * @param List<ActivityClientBase> clients
	 * @param User user
	 */
	 private void addActivityClientBaseList(String activityId,List<ActivityClientBase> clients,User user){
		/**
		 * 保存客户群
		 */	
		Map<String,Object> setType = new HashMap<String,Object>();
		setType.put("activityId", activityId);
		setType.put("clientBaseStatus", MarketActivityConstance.CLIENTBASE_STATUS_USEING);
		MarketActivityUtils.addValueToObject(clients, setType);
		ClientBase templeClient = null;
		boolean addClient = false;
		for( ActivityClientBase client : clients ){
			templeClient = new ClientBase();
			templeClient.setId( client.getClientBaseId());
			templeClient.setClientBaseStatus(MarketActivityConstance.CLIENTBASE_STATUS_USEING);
			templeClient.setModifyDate(new Date());
			templeClient.setModifyUser(user.getEmpCode().getId());
			addClient = clientBaseManager.updateClientBaseStatus(templeClient);
			MarketActivityValidator.checkActivityClientBaseAddOrDelStatus(addClient);
			marketActivityService.insertActivityClient(client);
		}
	 }
	/**
	 * 
	 * <p>
	 * 根据市场推广活动ID,用户ID查询市场推广活动<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-17
	 * @param Map<String,String>
	 * @return ActivityClientBase
	 */
	 private List<MarketActivityVO> searchMarketActivityVOByActivityId(String activityId,User user){
		 MarketActivityVO maVo = new MarketActivityVO();
		 String deptCharacter = MarketActivityValidator.checkUserAuthDept(user);
		 maVo.setDeptCharacter(deptCharacter);
		 //根据市场推广活动ID查询活动信息
		 maVo.setId(activityId);
		 //根据市场推广活动创建人查询
		 maVo.setCreateUser(user.getId());
		 maVo.setDeptId(user.getEmpCode().getDeptId().getId());
		 return marketActivityService.searchMarketActivityByMulCondition(maVo, 0, 20);
	 }

	/**
	 * 生成区域营销工作流
	 */
	@Override
	public String processWorkFlow(MarketActivity marketActivity) {
		//生成工作流号
		String bizCode = bpsWorkflowManager.bizCode();
		//调用业务模块保存工作流详情方法
		//修改市场推广活动状态为审批中		
		//将工作流赋值给营销活动实体
		marketActivity.setActivityWorkflowNum(bizCode);
		marketActivityService.updateMaketActivityById(marketActivity);
		//调用生成工作流方法
		Map<String, String> map = bpsWorkflowManager.createWorkflow(
				BpsConstant.MARKETING_PROCESSNAME,
				BpsConstant.PROCESS_AREA_MARKETING + bizCode, 
				BpsConstant.PROCESS_AREA_MARKETING_DESCRIPTION, bizCode);
		if(null != map){
			return map.get("bizCode");
		}
		return "";
	}

	/**
	 * 区域营销工作流审批
	 */
	@Transactional
	@Override
	public void workflowApprove(String busino, Boolean wkStatus, String wkMan,
			Date approveDate, String approvalSuggestin,User user) {
		//查询区域营销工作流详情，根据工作流号busino查询
		MarketActivity marketActivity = searchMarketActivityByWorkflowNum(busino);
		MarketActivity updateActivity = new MarketActivity();
		updateActivity.setId(marketActivity.getId());	
		//同意
		if(wkStatus){
			//设置工作流状态为审批通过
			updateActivity.setActivityStatus(MarketActivityConstance.ACTIVITY_STATUS_PROCESSED);
			//修改工作流状态为审批通过
			marketActivityService.updateMaketActivityById(updateActivity);
			//如果包含价格折扣
			if( MarketActivityConstance.OPTION_DISCOUNT.equals(marketActivity.getPreferType()) ){
				marketActivityDiscountInfoToFoss(marketActivity,MarketActivityConstance.FOSS_ACTIVITY_STATUS_Y);
				//将价格折扣推送到FOSS
				//修改市场推广活动状态为折扣已生效
				updateActivity.setActivityStatus(MarketActivityConstance.ACTIVITY_STATUS_EXECUTED);
				marketActivityService.updateMaketActivityById(updateActivity);
			}
		//不同意
		}else{
			//设置活动状态为审批失败
			updateActivity.setActivityStatus(MarketActivityConstance.ACTIVITY_STATUS_DISAGREE);
			//修改活动状态为审批失败
			marketActivityService.updateMaketActivityById(updateActivity);
			//查询市场推广活动包含的所有客户群
			List<ActivityClientBase> clients = marketActivityService.searchActivityClientByActivityId(marketActivity.getId());
			//建立客户群实体引用,用于修改客户群状态
			ClientBase c = null;
			//循环查询到的全部客户群
			for( ActivityClientBase client : clients){
				//新建客户群实体
				c = new ClientBase();
				//设置客户群ID
				c.setId(client.getClientBaseId());
				//设置客户群状态
				c.setClientBaseStatus(MarketActivityConstance.CLIENTBASE_STATUS_UNUSED);
				c.setModifyDate(new Date());
				c.setModifyUser(user.getEmpCode().getId());
				//更新客户群状态
				if(clientBaseManager.updateClientBaseStatus(c)){
					//设置市场推广活动关联表中的状态为未使用
					client.setClientBaseStatus(MarketActivityConstance.CLIENTBASE_STATUS_UNUSED);
					//设置修改时间
					client.setModifyDate(new Date());
					//设置修改人ID
					client.setModifyUser(user.getEmpCode().getId());
					//更新市场推广活动关联客户群
					marketActivityService.updateActivityClientById(client);
				}
			}
		}
	}
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
	@Override
	public MarketActivity searchLatestMarketActivityByDeptId(String deptId) {
		return marketActivityService.searchLatestMarketActivityByDeptId(deptId);
	}	
	/**
	 * 
	 * <p>
	 * 删除未创建完成的市场推广活动<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2014-3-17
	 */
	@Transactional
	@Override
	public void updateCompleteOrDeleteUselessActivities() {
		//查询未保存完整的市场推广活动
		List<MarketActivity> activities = 
				marketActivityService.searchUselessOrCompleteActivities(MarketActivityConstance.SEARCH_USELESS_ACTIVITY);
		ClientBase client = null;
		MarketActivity updateActivity = new MarketActivity();
		//判断是否有未保存完的市场推广活动,如果没有则返回
		if( !activities.isEmpty()){
			//循环市场推广活动
			for( MarketActivity ma : activities ){
				if( ma.getClientBase()!=null && ma.getClientBase().size()>0){
					client = new ClientBase();
					//循环列表
					for( ActivityClientBase aClient : ma.getClientBase() ){
						client.setId(aClient.getClientBaseId());
						client.setClientBaseStatus(
								MarketActivityConstance.CLIENTBASE_STATUS_UNUSED);
						client.setModifyDate(new Date());
						client.setModifyUser(MarketActivityConstance.SYS_ADMIN_ID);
						clientBaseManager.updateClientBaseStatus(client);
						//删除市场推广活动关联客户群
						marketActivityService.deleteClientBaseByRelationId(
								aClient.getId(), MarketActivityConstance.CLIENTBASE_STATUS_USEING);
					}		
				}
				//如果走货线路不为空 则根据ID删除关联走货线路
				if( ma.getLineDepts()!=null && ma.getLineDepts().size()>0){
					marketActivityService.deleteLineDeptByIdList(ma.getLineDepts());
				}
				//如果上传附件不为空
				if( ma.getFiles()!= null && ma.getFiles().size()>0){
					//循环列表
					for(FileInfo file : ma.getFiles() ){
						//删除附件
						fileManager.deleteFileInfo(file.getId());
					}
				}
			}
			marketActivityService.deleteMarketActivityByIds(activities);
		}
		activities = marketActivityService.searchUselessOrCompleteActivities(MarketActivityConstance.SEARCH_COMPLETE_ACTIVITY);
		if( activities != null && activities.size()>0){
			for( MarketActivity ma : activities ){
				updateActivity.setId(ma.getId());
				updateActivity.setActivityStatus(MarketActivityConstance.ACTIVITY_STATUS_TERMINATED);
				marketActivityService.updateMaketActivityById(updateActivity);
				//如果是价格折扣则推送信息到FOSS改变活动状态
				if( MarketActivityConstance.OPTION_DISCOUNT.equals(ma.getPreferType())){
					MarketActivity interActivity = searchMarketActivityById(ma.getId());
					marketActivityDiscountInfoToFoss(interActivity,MarketActivityConstance.FOSS_ACTIVITY_STATUS_N);
				}
				if( ma.getClientBase()!=null && ma.getClientBase().size()>0){
					client = new ClientBase();
					//循环列表
					for( ActivityClientBase aClient : ma.getClientBase() ){
						client.setId(aClient.getClientBaseId());
						client.setClientBaseStatus(
								MarketActivityConstance.CLIENTBASE_STATUS_UNUSED);
						client.setModifyDate(new Date());
						client.setModifyUser(MarketActivityConstance.SYS_ADMIN_ID);
						clientBaseManager.updateClientBaseStatus(client);
					}		
				}
			}
		}
	}
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
	public List<ActivityClientBase> searchActivityClientBaseUsed(String activityId){
		return marketActivityService.searchActivityClientByMultipleCondition(activityId,MarketActivityConstance.CLIENTBASE_STATUS_USEING , null);
	}
	
//	/**
//	 * 
//	 * <p>
//	 * 更新市场推广活动异常信息处理标识<br />
//	 * </p>
//	 * 
//	 * @author ZhouYuan
//	 * @version 0.1 2014-4-6
//	 * @param ActivityExceptionHandle
//	 */
//	@Override
//	public void insertActivityException(String activityId,String exceptionType) {
//		ActivityExceptionHandle aeh = new ActivityExceptionHandle();
//		aeh.setActivityId(activityId);
//		aeh.setExceptionType(exceptionType);
//		aeh.setHandle(MarketActivityConstance.ACTIVITY_EXCEPTION_UNHANDLE);
//		marketActivityService.insertActivityException(aeh);
//	}
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
//	@Override
//	public void handleActivityException() {
//		List<ActivityExceptionHandle> unhandles = marketActivityService
//				.searchActivityExeptionUnhandle(MarketActivityConstance.ACTIVITY_EXCEPTION_UNHANDLE);
//		MarketActivity searchCondition = new MarketActivity();
//		MarketActivity ma = null;
//		MarketActivity updateM = new MarketActivity();
//		String status = null;
//		if( unhandles != null && unhandles.size()>0){
//			for( ActivityExceptionHandle handle : unhandles ){
//				searchCondition.setId(handle.getActivityId());
//				ma = marketActivityService.searchMarketActivityByIdOrWorkFlowNum(searchCondition);
//				if( ma != null && MarketActivityConstance.ACTIVITY_EXCEPTION_NOWORKFLOW.equals(handle.getExceptionType())
//						&& MarketActivityConstance.ACTIVITY_TYPE_REGION.equals(ma.getActivityType())){
//					updateM.setId(ma.getId());
//					updateM.setActivityStatus(MarketActivityConstance.ACTIVITY_STATUS_PROCESSES);
//					processWorkFlow(updateM);
//					handle.setHandle(MarketActivityConstance.ACTIVITY_EXCEPTION_HANDLED);
//					marketActivityService.updateActivityExceptionById(handle);
//				}else if( ma != null && MarketActivityConstance.ACTIVITY_EXCEPTION_NODISCOUNT.equals(handle.getExceptionType())){
//					/**
//					 * 如果市场推广活动为折扣,且市场推广活动为区域市场推广活动,且活动状态为审批通过或下发完成,推送折扣信息到FOSS
//					 * 如果市场推广活动为折扣,且市场推广活动为全国市场推广活动,且活动状态为已制定或下发完成,推送折扣信息到FOSS
//					 */
//					if( MarketActivityConstance.OPTION_DISCOUNT.equals(ma.getPreferType())
//							&& ((MarketActivityConstance.ACTIVITY_TYPE_REGION.equals(ma.getActivityType())
//									&&MarketActivityConstance.REGIONDISSTATUS.contains(ma.getActivityStatus()))
//									||(MarketActivityConstance.ACTIVITY_TYPE_NATION.equals(ma.getActivityType())
//											&&MarketActivityConstance.NATIONDISSTATUS.contains(ma.getActivityStatus())))){
//						marketActivityDiscountInfoToFoss(ma,MarketActivityConstance.FOSS_ACTIVITY_STATUS_Y);
//						updateM.setId(ma.getId());
//						status = MarketActivityUtils.getMarketActivityExceptionStatus(ma);
//						updateM.setActivityStatus(status);
//						marketActivityService.updateMaketActivityById(updateM);
//						handle.setHandle(MarketActivityConstance.ACTIVITY_EXCEPTION_HANDLED);
//						marketActivityService.updateActivityExceptionById(handle);
//						
//					}
//				}
//			}
//		}			
//	} 
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
	 * @throws CrmBusinessException 
	 */
	@Override
	public void marketActivityDiscountInfoToFoss(MarketActivity ma,String fossStatus){
		MarketingActivityRequest mar = MarketActivityUtils.converActivityToRequest(ma,fossStatus);
		try {
			esbToFossAsyOperateImpl.synMarketingActivity(mar);
		} catch (CrmBusinessException e) {
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
						private static final long serialVersionUID = 1L;
			};
		}
		
	} 
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
	@Override
	public void checkDiscountWorkflow(String workflowNum) {
		ProcessInfo pi=new ProcessInfo();
		String flag;
		if(workflowNum==null||!workflowNum.matches("[0-9]+")){
			flag=MarketActivityConstance.WORKFLOW_STATUS_NONE;
		}else{
			//查询工作流状态
			try{
				pi= bpsWorkflowManager.searchProcessInfoByProcessInstId(workflowNum);
				flag=pi.getCondition();
			}catch(BPMSException e){
				pi.setCondition(flag=MarketActivityConstance.WORKFLOW_STATUS_NONE);
			}
		}
		//判断工作流是否可用
		if(null!=pi&&MarketActivityConstance.WORKFLOW_STATUS_PASS.equals(flag)){
			if(marketActivityService.checkWorkflowIsRepeat(workflowNum)){
				flag=MarketActivityConstance.WORKFLOW_STATUS_REPEAT;
			}
		}
		MarketActivityValidator.checkWorkFlowIfApprove(flag);
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
		marketActivityService.createClientBaseDetail();
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
		marketActivityService.countClientBaseDetail();
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
		marketActivityService.createClientDetailReport();
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
	public List<MarketActivity> searchMarketActivityForMarketPlan(String activityName,User user) {
		String activityCategory = MarketActivityUtils.getMarketPlanAuth(user);
		return marketActivityService.searchMarketActivityForMarketPlan(activityName,activityCategory);
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
			String activityId) {
		return marketActivityService.searchActivityOptionByActivityIdAndType(
				activityId, MarketActivityConstance.OPTION_COUPON);
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
	public void checkHaveSamNameMarketActivity(String activityName) {
		//查询同名的市场推广活动数
		int count = marketActivityService.countMarketActivityByName(activityName);
		//校验数量是否为0
		MarketActivityValidator.checkHaveSamNameActivity(count);
	}

	

		 
}
