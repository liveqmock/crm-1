/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CouponManagerImpl.java
 * @package com.deppon.crm.module.coupon.server.manager.impl 
 * @author ZhuPJ
 * @version 0.1 2012-11-12
 */
package com.deppon.crm.module.coupon.server.manager.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.authorization.server.service.IAuthorizeService;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.file.util.CreateExcel;
import com.deppon.crm.module.coupon.server.manager.ICouponManager;
import com.deppon.crm.module.coupon.server.service.ICouponService;
import com.deppon.crm.module.coupon.server.utils.CouponCellphoneUtils;
import com.deppon.crm.module.coupon.server.utils.CouponMsgUtil;
import com.deppon.crm.module.coupon.server.utils.CouponUtils;
import com.deppon.crm.module.coupon.server.utils.CouponValidator;
import com.deppon.crm.module.coupon.server.utils.ReadCellphonesByExcel;
import com.deppon.crm.module.coupon.shared.domain.ConditionType;
import com.deppon.crm.module.coupon.shared.domain.Coupon;
import com.deppon.crm.module.coupon.shared.domain.CouponCellphone;
import com.deppon.crm.module.coupon.shared.domain.CouponCellphoneMsgInfo;
import com.deppon.crm.module.coupon.shared.domain.CouponForInterface;
import com.deppon.crm.module.coupon.shared.domain.CouponResultVO;
import com.deppon.crm.module.coupon.shared.domain.CouponRule;
import com.deppon.crm.module.coupon.shared.domain.CouponSearchCondition;
import com.deppon.crm.module.coupon.shared.domain.CouponTypeVo;
import com.deppon.crm.module.coupon.shared.domain.HandMarketPlan;
import com.deppon.crm.module.coupon.shared.domain.MarketPlaneVO;
import com.deppon.crm.module.coupon.shared.domain.MarketingCouponConstance;
import com.deppon.crm.module.coupon.shared.domain.Marketplan;
import com.deppon.crm.module.coupon.shared.domain.RuleCouponAuto;
import com.deppon.crm.module.coupon.shared.domain.RuleCouponHand;
import com.deppon.crm.module.coupon.shared.domain.SendCouponVO;
import com.deppon.crm.module.coupon.shared.domain.WaybillCoupon;
import com.deppon.crm.module.coupon.shared.domain.WaybillInfo;
import com.deppon.crm.module.coupon.shared.exception.CouponExceptionType;
import com.deppon.crm.module.coupon.shared.exception.ExceptionUtils;
import com.deppon.crm.module.customer.server.manager.IAlterMemberManager;
import com.deppon.crm.module.customer.shared.domain.MemberCondition;
import com.deppon.crm.module.customer.shared.domain.MemberResult;
import com.deppon.crm.module.organization.server.manager.ISearchDeptAndEmployeeManager;
import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.DeptInfo;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CouponManagerImpl.java
 * @package com.deppon.crm.module.coupon.server.manager.impl 
 * @author ZhuPJ
 * @version 0.1 2012-11-12
 */
public class CouponManagerImpl implements ICouponManager {
	private final int RESEND_MAX_TIMES = 4;		// 短信重发最大次数
	private final int RESEND_MAX_INTERVAL = 60*10;// 短信重发最大间隔（秒）
	private ICouponService couponServiceImpl;

	// 客户模块
	private IAlterMemberManager alterMemberManager;
	// 公用模块-部门查询
	private ISearchDeptAndEmployeeManager searchDeptAndEmployeeManager;
	//授权部门查询service
	private IAuthorizeService authorizeService;
	// 部门Service
	private IDepartmentService departmentService;
	
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.coupon.server.manager.ICouponManager#checkCouponRule(java.lang.String, com.deppon.crm.module.coupon.shared.domain.WaybillInfo)
	 */
	@Override
	public String[] checkCouponRule(String couponNum, WaybillInfo wbInfo) {
		boolean b = false;	// 验证结果
		String msg = null;	// 结果信息：验证成功：返回抵扣金额，验证失败：返回错误信息
		String[] rs = new String[3]; 
		
		//检查优惠券编码是否为空
 		CouponValidator.checkObjectIsNotNull(couponNum, CouponExceptionType.couponNumberIsntExsit);
		// 根据优惠券编码查询优惠券信息
		Coupon coupon = couponServiceImpl.getCouponByNum(couponNum);
		// 检查优惠券是否为空
		CouponValidator.checkCouponIsNull(coupon);
		
		// 根据优惠券编码查询使用规则
		CouponRule rule = couponServiceImpl.getCouponRuleByMarketPlanId(coupon.getMarketPlanId());
		CouponValidator.checkCouponRuleIsNotNull(rule);
		//获得营销计划
		Marketplan marketplan = couponServiceImpl.searchMarketPlanDetail(coupon.getMarketPlanId());
		// 根据客户编码查找客户信息
		String custNum = wbInfo.getCustNumber();
		List<MemberResult> mr = null;
		if (StringUtils.isNotEmpty(custNum)){ 
			// 客户编码不为空，查询客户信息
			MemberCondition condition = new MemberCondition();
			condition.setCustNumber(custNum);
			condition.setLimit(-1);		// 不查询作废客户（客户模块要求定义-.-）
			mr = alterMemberManager.searchMember(condition);
		}
		// 根据运单信息，获得发/到货部门所属的大区/小区/外场数据
		// 发货部门信息
		List<DeptInfo> leaveDept = searchDeptAndEmployeeManager
				.searchDeptStructure(wbInfo.getLeaveDept());
		// 到货部门信息
		List<DeptInfo> arrivedDept = searchDeptAndEmployeeManager
				.searchDeptStructure(wbInfo.getArrivedDept());
		// 校验使用规则
		msg = CouponValidator.canUseCoupon(coupon, rule, wbInfo, mr, leaveDept, arrivedDept);
		// 验证返回结果，如果为数字，表示验证成功，反之失败
		Pattern pattern = Pattern.compile("[0-9]*"); 
		if (pattern.matcher(msg).matches()){
			b = true;
		}
			
		rs[0] = String.valueOf(b);
		rs[1] = msg;
		if( marketplan != null && StringUtils.isNotEmpty(marketplan.getDiscountType())){
			rs[2] = marketplan.getDiscountType();
		}
		// 返回校验结果
		return rs;
	}

	/**   
	 * <p>
	 * Description:根据条件，批量改变优惠券状态<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-13
	 * @param couponCode 优惠券状态编码
	 * @param couponNums 优惠券编码
	 * @return boolean
	 */
	@Transactional
	@Override
	public boolean updateCouponStatus(String couponCode, List<String> couponNums){
		boolean isUpdate = false;
		if(StringUtils.isNotEmpty(couponCode) && null != couponNums &&  0 != couponNums.size()){
		isUpdate = couponServiceImpl.updateCouponStatus(couponCode,couponNums);
		}
		return isUpdate;
	}
	/**   
	 * <p>
	 * Description:根据条件，改变单条优惠券状态<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-13
	 * @param couponCode 优惠券状态编码
	 * @param couponNum 优惠券编码
	 * @return boolean
	 */
	@Transactional
	@Override
	public boolean updateOneCouponStatu(String couponCode,String couponNum){
		boolean isUpdate = false;
		// 如果参数不为空，则跟新优惠券状态
		if(StringUtils.isNotEmpty(couponNum) && StringUtils.isNotEmpty(couponCode)){
			isUpdate = couponServiceImpl.updateOneCouponStatus(couponCode,couponNum);
		}
		return isUpdate;
	}
	/**   
	 * <p>
	 * Description:更改 营销计划状态<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-13
	 * @param marketplanCode 营销计划状态码，marketplanNums 营销计划编码
	 * @return boolean
	 */
	@Transactional
	@Override
	public boolean updateMarketPlanStatus(String marketplanCode,
			String marketplanNums) {
		//校验营销计划编码是否为空
		if(StringUtils.isEmpty(marketplanCode) || StringUtils.isEmpty(marketplanNums)){
			return false;
		}
		Marketplan marketplan = new Marketplan();
		marketplan.setPlanNumber(marketplanNums);
		marketplan.setMarketStatus(marketplanCode);
		return couponServiceImpl.updateMarketPlan(marketplan);
	}

	
	/**   
	 * <p>
	 * Description:根据 优惠券编码 查询优惠券集合<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-13
	 * @param couponNums 优惠券编码
	 * @return List<Coupon>
	 */
	@Override
	public List<Coupon> searchCouponsByNums(List<String> couponNums){
		List<Coupon> couponList = new ArrayList<Coupon>();
		// 如果参数不为空，则查询优惠券集合
		if(null != couponNums && 0 != couponNums.size() ){
			couponList = couponServiceImpl.searchCouponsByNums(couponNums);
		};
		return couponList;
	}
	
	/**   
	 * <p>
	 * Description:根据 优惠券编码查询 单条优惠券<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-13
	 * @param couponNum 优惠券编码
	 * @return Coupon
	 */
	@Override
	public Coupon getCouponByNum(String couponNum){
		Coupon coupon = new Coupon();
		if(StringUtils.isNotEmpty(couponNum)){
			coupon = couponServiceImpl.getCouponByNum(couponNum);
		}
		return coupon;
	}
	/**   
	 * <p>
	 * Description:新增营销计划<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-14
	 * @param marketPlaneVO 新增营销计划-发券规则VO
	 */
	@Transactional
	@Override
	public boolean createMarketPlanCoupon(MarketPlaneVO marketPlaneVO,User user) {
		String marketplanId; // 营销计划ID
		String createUser = user.getEmpCode().getId(); // 操作人Id
		String createUserName = user.getEmpCode().getEmpName();// 操作人姓名
		String[] checkList = new String[]{createUser};
		//检查操作人是否为空
		CouponValidator.checkObjectIsNotNull(checkList,CouponExceptionType.operatorUserIsNull);
		//设置创建人为当前用户ID
		marketPlaneVO.getMarketplan().setCreateUser(createUser);
		//设置修改人为当前用户ID
		marketPlaneVO.getMarketplan().setModifyUser(createUser);
		//设置修改人姓名为当前用户姓名
		marketPlaneVO.getMarketplan().setModifyUserName(createUserName);
		/**
		 * 查看该用户权限是否包含零担或快递权限
		 * 然后将相应的业务类型给营销计划实体赋值
		 */
		marketPlaneVO.getMarketplan().setBusType(CouponUtils.userBusinessType(user));
		// 验证前台传参是否符合规则
		CouponValidator.checkMarketPlaneVOCanCreate(marketPlaneVO);
		// 保存营销计划时：点击保存，则计划状态为未启用；
		String marketStatus = marketPlaneVO.getMarketplan().getMarketStatus(); // 计划状态
		if(StringUtils.isEmpty(marketStatus)){  // 如果计划状态为空，则置为“未启用”
		marketPlaneVO.getMarketplan().setMarketStatus(
				MarketingCouponConstance.MARKETPLAN_NOUSE);
		}
		// 新增营销计划
		couponServiceImpl.addMarketplanVOByMarketplan(marketPlaneVO.getMarketplan());
		//获得营销计划ID
		marketplanId = marketPlaneVO.getMarketplan().getId(); // 营销计划id
		//获得优惠券类型
		String couponType = marketPlaneVO.getMarketplan().getCouponType(); // 优惠券类型
		
		// 新增手动发券规则
		if(MarketingCouponConstance.COUPON_SENDHAND.equals(couponType) && 
				null != marketPlaneVO.getRuleCouponHand()){
			// 营销计划id
			marketPlaneVO.getRuleCouponHand().setMarketPlanId(marketplanId); 
			// 未生成优惠券
			marketPlaneVO.getRuleCouponHand().setTypeId(MarketingCouponConstance.COUPON_NOCREATE); 
			if(MarketingCouponConstance.COUPON_NOWHAND < 
					Integer.parseInt(marketPlaneVO.getRuleCouponHand().getCouponQuantity())){
				// 定时器生成优惠券
				marketPlaneVO.getRuleCouponHand().setTypeId(MarketingCouponConstance.COUPON_LARGERHAND); 
			}
			// 新增
			couponServiceImpl.addMarketplanVOByRuleCouponHand(marketPlaneVO.getRuleCouponHand()); 
		}
		
		// 新增自动发券规则
		if(MarketingCouponConstance.COUPON_SENDAUTO.equals(couponType) && 
				null != marketPlaneVO.getRuleCouponAuto()){
			// 营销计划id
			marketPlaneVO.getRuleCouponAuto().setMarketPlanId(marketplanId);
			// 发券类型
			marketPlaneVO.getRuleCouponAuto().setTypeId(couponType); 
			// 新增
			couponServiceImpl.addMarketplanVOByRuleCouponAuto(marketPlaneVO.getRuleCouponAuto()); 
		}
		
		// 新增优惠券使用条件
		marketPlaneVO.getCouponRule().setMarketPlanId(marketplanId);
		couponServiceImpl.addMarketplanVOByRuleCouponRule(marketPlaneVO.getCouponRule());  // 新增
		return true;
	}
	/**   
	 * <p>
	 * Description:新增营销计划-发券规则-启用营销计划规则<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-14
	 * @param marketPlaneVO 新增营销计划-发券规则VO
	 * @return boolean
	 */
	@Override
	public boolean startMarketPlanCoupon(MarketPlaneVO marketPlaneVO,User user){
		// 把启用状态置为 已启用
		marketPlaneVO.getMarketplan().setMarketStatus(MarketingCouponConstance.MARKETPLAN_USING);
		// 保存营销计划
		this.createMarketPlanCoupon(marketPlaneVO,user);
		// 获取优惠券类型
		String couponType = marketPlaneVO.getMarketplan().getCouponType();
		// 如果是手动发券 且 发券数量小于10000 条 则立即生成
		if(MarketingCouponConstance.COUPON_SENDHAND.equals(couponType)){
			RuleCouponHand ruleCouponHand = new RuleCouponHand();
			// 营销计划id
			String marketPlanId = marketPlaneVO.getMarketplan().getId();
			ruleCouponHand.setMarketPlanId(marketPlanId);
			if(MarketingCouponConstance.COUPON_NOWHAND >= 
					Integer.parseInt(marketPlaneVO.getRuleCouponHand().getCouponQuantity())){
				//优惠券数量
				int size = Integer.parseInt(marketPlaneVO.getRuleCouponHand().getCouponQuantity());
				// 优惠券金额
				String couponValue = marketPlaneVO.getRuleCouponHand().getCouponValue();
				// 使用规则id
				String useRuleId = marketPlaneVO.getCouponRule().getId();
				// 生成优惠券
				this.createHandCoupon(size, marketPlanId, useRuleId, couponValue);
				ruleCouponHand.setTypeId(MarketingCouponConstance.COUPON_CREATED);
				this.updateCouponHand(ruleCouponHand);
			}
		}
		return true;
	}
	/**   
	 * <p>
	 * Description:查询营销计划<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-17
	 * @param marketplan 营销计划实体
	 * @param start
	 * @param limit
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String,Object> searchMarketPlan(User user,Marketplan marketplan,int start,int limit){
		List<Marketplan> listMarketPlan;
		
		//判断是否有相应的手动券或自动券权限
		marketplan.setBusType(CouponUtils.userBusinessType(user));
		// 营销计划查询 时间范围最大为一年，或业务类型是否为空
		CouponValidator.checkMarketPlaneCanSearch(marketplan);		
		// 返回的结果map
		Map<String,Object> map = new HashMap<String,Object>();
		// 查询营销计划列表
		listMarketPlan = couponServiceImpl.searchMarketPlan(marketplan,start,limit);
		// 查询营销计划条数
		String totalCount = couponServiceImpl.searchMarketPlanTotalCount(marketplan);
		// 将结果进行封装
		map.put("listMarketPlan", listMarketPlan);
		map.put("totalCount", totalCount);
		return map;
	 }
	 
	 /**   
	 * <p>
	 * Description:查询营销计划详情<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-17
	 * @param marketplanId 营销计划ID
	 * @return MarketPlaneVO
	 */
	@Override
	 public MarketPlaneVO searchMarketPlanDetail(String marketplanId){
		 MarketPlaneVO marketPlaneVO = new MarketPlaneVO();
		 if(StringUtils.isNotEmpty(marketplanId)){
			 // 查询营销计划
			 Marketplan marketPlane = couponServiceImpl.searchMarketPlanDetail(marketplanId);
			 if(null != marketPlane){
				 marketPlaneVO.setMarketplan(marketPlane);
				 String couponType = marketPlane.getCouponType();
				 // 查询手动发券信息
				 if(MarketingCouponConstance.COUPON_SENDHAND.equals(couponType)){
					 //查询手动优惠券使用规则
					 RuleCouponHand ruleCouponHand = couponServiceImpl.searchRuleCouponHand(marketplanId);
					 //设置营销计划VO的使用规则
					 marketPlaneVO.setRuleCouponHand(ruleCouponHand);
				 }
				 // 查询自动发券信息
				 if(MarketingCouponConstance.COUPON_SENDAUTO.equals(couponType)){
					 //查询自动优惠券使用规则
					 RuleCouponAuto ruleCouponAuto = couponServiceImpl.searchRuleCouponAuto(marketplanId);
					//设置营销计划VO的使用规则
					 marketPlaneVO.setRuleCouponAuto(ruleCouponAuto);
				 }
				// 查询优惠券使用条件
				CouponRule couponRule = couponServiceImpl.getCouponRuleByMarketPlanId(marketplanId);
				 marketPlaneVO.setCouponRule(couponRule);
			 }
		 }
		 return marketPlaneVO;
	 }
	 
	 /**   
	 * <p>
	 * Description:根据营销计划id查询使用规则<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-18
	 * @param marketplanId 营销计划ID
	 * @return CouponRule
	 */
	@Override
	public CouponRule getCouponRuleByMarketPlanId(String marketPlanId){
		if(StringUtils.isNotEmpty(marketPlanId)){
			return couponServiceImpl.getCouponRuleByMarketPlanId(marketPlanId);
		}
		// 根据优惠券id查询使用规则
		return null;
	}
	/**   
	 * <p>
	 * Description:查看详情界面-启用营销计划<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-26
	 * @param marketPlanId 营销计划id,user
	 * @return boolean
	 */
	@Transactional
	@Override
	public boolean startMarketPlanByDetail(MarketPlaneVO marketPlaneVO,User user) {
		String marketPlanId = marketPlaneVO.getMarketplan().getId();
		// 根据营销计划id查询营销计划
		Marketplan marketPlanExit = couponServiceImpl.searchCouponTypeByMarketPlanId(marketPlanId);
		//校验营销计划是否可以启用
		CouponValidator.checkCanStart(marketPlanExit);
		//设置营销计划状态为启用
		marketPlaneVO.getMarketplan().setMarketStatus(MarketingCouponConstance.MARKETPLAN_USING);
		this.updateMarketPlan(marketPlaneVO, user);
		// 获取优惠券类型
		String couponType = marketPlaneVO.getMarketplan().getCouponType();
		// 如果是手动发券 且 发券数量小于10000 条 则立即生成
		if(MarketingCouponConstance.COUPON_SENDHAND.equals(couponType)){
			RuleCouponHand ruleCouponHand = new RuleCouponHand();
			//设置手动券营销计划的使用规则
			ruleCouponHand.setMarketPlanId(marketPlanId);
			if(MarketingCouponConstance.COUPON_NOWHAND >= 
					Integer.parseInt(marketPlaneVO.getRuleCouponHand().getCouponQuantity())){
				//优惠券数量
				int size = Integer.parseInt(marketPlaneVO.getRuleCouponHand().getCouponQuantity());
				// 优惠券金额
				String couponValue = marketPlaneVO.getRuleCouponHand().getCouponValue();
				// 使用规则id
				String useRuleId = marketPlaneVO.getCouponRule().getId();
				// 生成优惠券
				this.createHandCoupon(size, marketPlanId, useRuleId, couponValue);
				//设置营销计划的优惠券创建标识为已创建
				ruleCouponHand.setTypeId(MarketingCouponConstance.COUPON_CREATED);
				this.updateCouponHand(ruleCouponHand);
			}
			
		}
		return true;
	}
	/**   
	 * <p>
	 * Description:终止营销计划<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-26
	 * @param marketPlanId 营销计划id,user
	 * @return boolean
	 */
	@Transactional
	@Override
	public boolean stopMarketPlan(String marketPlanId,User user) {
		Marketplan marketplan = new Marketplan();
		// 操作人id
		String empCode = user.getEmpCode().getId(); 
		// 操作人 姓名
		String createUserName = user.getEmpCode().getEmpName(); 
		// 根据营销计划id查询营销计划
		Marketplan marketPlanExit = couponServiceImpl.searchCouponTypeByMarketPlanId(marketPlanId);
		if(null != marketPlanExit){
			// 如果 营销计划状态不为  已启用时
			if(!MarketingCouponConstance.MARKETPLAN_USING.equals(marketPlanExit.getMarketStatus())){
				ExceptionUtils.createCouponException(CouponExceptionType.marketPlanStateCanNotOperate);
			}
			 // 营销计划id
			marketplan.setId(marketPlanId);  
			// 营销计划状态
			marketplan.setMarketStatus(MarketingCouponConstance.MARKETPLAN_STOP); 
			// 修改人id
			marketplan.setModifyUser(empCode); 
			// 修改人姓名
			marketplan.setModifyUserName(createUserName); 
			// 修改时间
			marketplan.setModifyDate(new Date()); 
		}
		return couponServiceImpl.updateMarketPlan(marketplan);
	}
	/**   
	 * <p>
	 * Description:删除营销计划<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-26
	 * @param marketPlanId 营销计划id
	 * @return boolean
	 */
	@Transactional
	@Override
	public boolean deleteMarketPlan(String marketPlanId) {
		Marketplan marketPlanExit = couponServiceImpl.searchCouponTypeByMarketPlanId(marketPlanId);
		if(null != marketPlanExit){
			// 如果 营销计划状态不为  未启用时
			if(!MarketingCouponConstance.MARKETPLAN_NOUSE.equals(marketPlanExit.getMarketStatus())){
				ExceptionUtils.createCouponException(CouponExceptionType.marketPlanStateCanNotOperate);
			}
			//获得营销计划的手动券使用规则
			RuleCouponHand couponHand = couponServiceImpl.searchRuleCouponHand(marketPlanId);
			//获得营销计划的自动券使用规则
			RuleCouponAuto couponAuto = couponServiceImpl.searchOnlyRuleCouponAuto(marketPlanId);
			//优惠券创建规则
			CouponRule couponRule = couponServiceImpl.getCouponRuleByMarketPlanId(marketPlanId);
			//删除营销计划
			couponServiceImpl.deleteMarketPlan(marketPlanId);
			//是手动券时
			if(MarketingCouponConstance.COUPON_SENDHAND.equals(marketPlanExit.getCouponType())){
				couponServiceImpl.deleteCouponHand(marketPlanId,couponHand.getId());
			}
			//是自动券时
			if(MarketingCouponConstance.COUPON_SENDAUTO.equals(marketPlanExit.getCouponType())){
				couponServiceImpl.deleteCouponAuto(marketPlanId,couponAuto.getId());
			}
			//删除优惠券规则
			couponServiceImpl.deleteCouponRule(marketPlanId,couponRule.getId());
		}
		return true;
	}
	/**   
	 * <p>
	 * Description:查看详情界面-更新营销计划<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-20
	 * @param marketPlaneVO 营销计划 页面 VO,user
	 * @return boolean
	 */
	@Transactional
	@Override
	public boolean updateMarketPlan(MarketPlaneVO marketPlaneVO,User user) {
		//营销计划ID
		String marktPlanId = marketPlaneVO.getMarketplan().getId();
		//当前用户ID
		String empCode = user.getEmpCode().getId();
		//当前用户姓名
		String empName = user.getEmpCode().getEmpName();
		//根据营销计划ID查找对应的营销计划
		Marketplan marketPlan = couponServiceImpl.searchCouponTypeByMarketPlanId(marktPlanId);
		if(null != marketPlan){
			//优惠券类型
			String couponType = marketPlan.getCouponType();
			//营销计划ID
			String marketPlanId = marketPlan.getId();
			//营销计划编码
			String marketPlanNumber = marketPlan.getPlanNumber();
			/**
			 * 查看该用户权限是否包含零担或快递权限
			 * 然后将相应的业务类型给营销计划实体赋值
			 */
			marketPlaneVO.getMarketplan().setBusType(CouponUtils.userBusinessType(user));
			//校验营销计划是否可以更新
			CouponValidator.checkMarketPlaneVOCanUpdate(marketPlaneVO,marketPlan);
			// 更新营销计划
			marketPlaneVO.getMarketplan().setPlanNumber(marketPlanNumber);
			//设置营销计划修改人ID
			marketPlaneVO.getMarketplan().setModifyUser(empCode);
			//设置营销计划修改人姓名
			marketPlaneVO.getMarketplan().setModifyUserName(empName);
			//设置修改时间
			marketPlaneVO.getMarketplan().setModifyDate(new Date());
			couponServiceImpl.updateMarketPlan(marketPlaneVO.getMarketplan());
			// 更新手动发券规则
			if(MarketingCouponConstance.COUPON_SENDHAND.equals(couponType)){
				//设置手动券规则关联的营销计划
				marketPlaneVO.getRuleCouponHand().setMarketPlanId(marketPlanId);
				//如果创建优惠券数目大于10000条
				if(MarketingCouponConstance.COUPON_NOWHAND < 
						Integer.parseInt(marketPlaneVO.getRuleCouponHand().getCouponQuantity())){
					// 定时器生成优惠券
					marketPlaneVO.getRuleCouponHand().setTypeId(MarketingCouponConstance.COUPON_LARGERHAND); 
				}
				//更新优惠券手动规则
				couponServiceImpl.updateCouponHand(marketPlaneVO.getRuleCouponHand());
			}
			// 更新自动发券规则
			if(MarketingCouponConstance.COUPON_SENDAUTO.equals(couponType)){
				//设置自动券关联的营销计划ID
				marketPlaneVO.getRuleCouponAuto().setMarketPlanId(marketPlanId);
				//更新优惠券使用规则
				couponServiceImpl.updateCouponAuto(marketPlaneVO.getRuleCouponAuto());
			}
			// 更新使用规则
			marketPlaneVO.getCouponRule().setMarketPlanId(marketPlanId);
			couponServiceImpl.updateCouponRule(marketPlaneVO.getCouponRule());
		}
		return true;
	}
	/**
	 * <p>
	 * Description: 批量创建优惠券<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-11-19
	 * @param coupon
	 * @return boolean
	 */
	@Override
	public int createCoupon(Coupon coupon) {
		return this.couponServiceImpl.createCoupon(coupon);
	}

	public boolean createHandCoupon(int size, String marketPlanId,
			String useRuleId, String couponValue) {
		String flag = couponServiceImpl.callableCreateHandCouponImm(size, marketPlanId,
				useRuleId, couponValue);
		return CouponValidator.checkCallableFlag(flag);
	}
	/**
	 * <p>
	 * Description: 更新优惠券信息<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-11-21
	 * @param coupon
	 * @return boolean
	 */
	@Override
	public boolean updateCoupon(Coupon coupon) {
		return this.couponServiceImpl.updateCoupon(coupon);
	}

	/**   
	 * <p>
	 * Description:根据营销计划查询优惠券数量,剩余数量,活动结束时间,活动开始时间,短信内容,营销计划编码,营销计划名称提供前台显示<br/>
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-21
	 * @param marketPlanId
	 * @return SendCouponVO 短信发券前台显示
	 */
	@Override
	public SendCouponVO searchSendCouponVOByMPI(String planNumber) {
		//创建查询条件的MAP
		Map<String,String> searchCondition = new HashMap<String,String>();
		//营销计划编码 
		searchCondition.put("planNumber", planNumber);
		//优惠券状态查询
		searchCondition.put("status", MarketingCouponConstance.COUPON_NOSEND);
		//调用查询,返回查询结果
		SendCouponVO sendCouponVO =  couponServiceImpl.searchSendCouponVO(searchCondition);
		//检查发券的有效性,如果为手动发券时
		CouponValidator.checkSendCouponVO(sendCouponVO);
		return sendCouponVO;
	}

	/**   
	 * <p>
	 * Description:将需要发送的信息存入到优惠劵手机发送表中<br/>
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-21
	 * @param planNumber phones user
	 * @return SendCouponVO 短信发券前台显示
	 */
	@Override
	public boolean sendCouponByCellphones(String planNumber,
			List<CouponCellphone> phones,User user) {
		//创建查询条件的MAP
		Map<String,String> searchCondition = new HashMap<String,String>();
		//根据营销计划编码 
		searchCondition.put("planNumber", planNumber);
		//优惠券状态查询
		searchCondition.put("status", MarketingCouponConstance.COUPON_NOSEND);
		////调用查询,返回查询结果
		SendCouponVO sendCouponVO = couponServiceImpl.searchSendCouponVO(searchCondition);
		//校验数据有效性
		CouponValidator.checkSendCouponVO(sendCouponVO);
		
		//根据营销计划ID和优惠券状态查询未使用的优惠券
		
		//创建查询条件
		Coupon condition = new Coupon();
		//设置营销计划ID
		condition.setMarketPlanId(String.valueOf(sendCouponVO.getMarketPlanId()));
		//设置优惠券状态
		condition.setStatus(MarketingCouponConstance.MARKETPLAN_NOUSE);
		//根据上述条件查询未使用的优惠券
		List<Coupon> coupons = couponServiceImpl.selectCouponByMarketPlanIdAndStatus(condition, 0, phones.size());
		//验证优惠券和手机号码
		CouponValidator.checkCouponCountAndPhoneCount(coupons, phones);
		//调用发送函数发送优惠券
		sendCouponMsg(sendCouponVO,phones,coupons,user);
		return true;
	}

	/**
	 * <p>
	 * Description: 批量导入手机号码,不对号码有效性进行校验,只做标记<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-20
	 * @param filePath
	 * @return
	 * List<CouponCellphone> 返回列表,包括合法和非法的手机号码
	 */
	@Override
	public List<CouponCellphone> batchImportCellphones(File file,String fileName) {
		//调用解析手机号excel文件的函数
		return ReadCellphonesByExcel.getMoblies(file,fileName);
	}

	/**
	 * <p>
	 * Description: 清除无效手机号码实现类<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-20
	 * @param filePath
	 * @return
	 * List<CouponCellphone> 返回列表,包括合法和非法的手机号码
	 */
	@Override
	public List<CouponCellphone> cleanInvalidCellphones(List<CouponCellphone> phones) {
		//调用清除无效号码的函数
		return CouponCellphoneUtils.cleanInvalid(phones);
	}
	/**
	 * <p>
	 * Description: 删除重复手机号码实体类 <br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-20
	 * @param filePath
	 * @return
	 * List<CouponCellphone> 返回列表,包括合法和非法的手机号码
	 */
	@Override
	public List<CouponCellphone> deleteRepeatCellphones(List<CouponCellphone> phones) {
		//调用删除重复号码的函数
		return CouponCellphoneUtils.deleteRepeat(phones);
	}
	/**   
	 * <p>
	 * Description:查询营销计划时，解析前台使用条件信息<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-2６
	 * @param Map<String,List<ConditionType>>
	 * @return couponTypeVo
	 */
	@Override
	public CouponTypeVo matchMarketPlanByConditionType(
			List<ConditionType> conditionTypeCreateList,List<ConditionType> conditionTypeUseList) {
		 // 返回实体对象
		CouponTypeVo couponTypeVo = new CouponTypeVo();
		//产品类型
		String makeProductTypeId = MarketingCouponConstance.BIND_TYPE_PRODUCT;
		//订单来源
		String makeOrderSourceTypeId = MarketingCouponConstance.BIND_TYPE_ORDER;
		//客户等级
		String makeCustomerLevelTypeId = MarketingCouponConstance.BIND_TYPE_LEVEL;
		//客户行业
		String makeCustomerTradeTypeId = MarketingCouponConstance.BIND_TYPE_TRADE;
		String[] bufTypesId = {makeProductTypeId,makeOrderSourceTypeId,makeCustomerLevelTypeId,makeCustomerTradeTypeId};
	//  检查非空
		if(null != conditionTypeCreateList && 0 < conditionTypeCreateList.size()){ 
			/***************制定规则 - 开始*****************/		
			//产品类型
			StringBuffer bufMakeProductTypes = new StringBuffer();
			//订单来源
			StringBuffer bufMakeOrderSourceTypes = new StringBuffer();
			//客户等级
			StringBuffer bufMakeCustomerLevelTypes = new StringBuffer();
			//客户行业
			StringBuffer bufMakeCustomerTradeTypes = new StringBuffer();
			// 调用方法，解析参数
			this.setListConditionType(bufTypesId,conditionTypeCreateList, bufMakeProductTypes, 
					bufMakeOrderSourceTypes, bufMakeCustomerLevelTypes,bufMakeCustomerTradeTypes);
			// 组装参数
			couponTypeVo.setMakeProductType(bufMakeProductTypes.toString().split("#")); //产品类型
			couponTypeVo.setMakeOrderSourceType(bufMakeOrderSourceTypes.toString().split("#")); //订单来源
			couponTypeVo.setMakeCustomerLevelType(bufMakeCustomerLevelTypes.toString().split("#"));//客户等级
			couponTypeVo.setMakeCustomerTradeType(bufMakeCustomerTradeTypes.toString().split("#"));//客户行业
			/***************制定规则 - 结束*****************/
		}
		if(null != conditionTypeUseList && 0 < conditionTypeUseList.size()){
			/***************使用规则 - 开始*****************/		
			//产品类型
			StringBuffer bufUseProductTypesUse = new StringBuffer();
			//订单来源
			StringBuffer bufUseOrderSourceTypesUse = new StringBuffer();
			//客户等级
			StringBuffer bufUseCustomerLevelTypesUse = new StringBuffer();
			//客户行业
			StringBuffer bufUseCustomerTradeTypesUse = new StringBuffer();
			// 调用方法，解析参数
			this.setListConditionType(bufTypesId,conditionTypeUseList, bufUseProductTypesUse, 
					bufUseOrderSourceTypesUse, bufUseCustomerLevelTypesUse,bufUseCustomerTradeTypesUse);
			/**
			 *  组装参数
			 */
			//产品类型
			couponTypeVo.setUseProductType(bufUseProductTypesUse.toString().split("#"));
			//订单来源
			couponTypeVo.setUseOrderSourceType(bufUseOrderSourceTypesUse.toString().split("#")); 
			//客户等级
			couponTypeVo.setUseCustomerLevelType(bufUseCustomerLevelTypesUse.toString().split("#"));
			//客户行业
			couponTypeVo.setUseCustomerTradeType(bufUseCustomerTradeTypesUse.toString().split("#")); 
			/***************使用规则 - 结束*****************/
		}
		return couponTypeVo;
	}
	/**   
	 * <p>
	 * Description:查询营销计划时，解析前台使用条件信息<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-26
	 * @param MbufTypesId 使用条件id，listConditionType 使用条件集合，buf1 产品类型，buf2 订单来源，buf3 客户等级，buf4 客户行业
	 * @return 
	 */
	public void setListConditionType(String[] bufTypesId,List<ConditionType> listConditionType,StringBuffer buf1,
			StringBuffer buf2,StringBuffer buf3,StringBuffer buf4){
		// 循环取出数组格式的-使用条件
		for(ConditionType conditionType : listConditionType){
			// 如果为  产品类型
			if(bufTypesId[0].equals(conditionType.getType())){
				buf1.append(conditionType.getValue());
				buf1.append("#");
				continue;
			}
			// 如果为  订单来源
			if(bufTypesId[1].equals(conditionType.getType())){
				buf2.append(conditionType.getValue());
				buf2.append("#");
				continue;
			}
			// 如果为 客户等级
			if(bufTypesId[2].equals(conditionType.getType())){
				buf3.append(conditionType.getValue());
				buf3.append("#");
				continue;
			}
			// 如果为 客户行业
			if(bufTypesId[3].equals(conditionType.getType())){
				buf4.append(conditionType.getValue());
				buf4.append("#");
				continue;
			}
		}
	}
	/**   
	 * <p>
	 * Description:保存营销计划时，封装前台使用条件信息<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-22
	 * @param couponTypeVos
	 * @return Map<String,List<ConditionType>> 
	 */
	@Override
	public Map<String, List<ConditionType>> matchMarketPlanByConditionType(CouponTypeVo couponTypeVos) {
		// 作为返回结果的 集合
		Map<String, List<ConditionType>> map = new HashMap<String, List<ConditionType>>();
		// 创建规则的-使用条件
		List<ConditionType> conditionTypeCreateList = new ArrayList<ConditionType>();
		// 使用规则的-使用条件
		List<ConditionType> conditionTypeUseList = new ArrayList<ConditionType>();
		// 标志-创建类型
		String createType = MarketingCouponConstance.CONDITIONTYPE_COUPONSEND;
		// 标志-使用类型
		String useType = MarketingCouponConstance.CONDITIONTYPE_COUPONUSE;
		
		//产品类型
		String makeProductTypeId = MarketingCouponConstance.BIND_TYPE_PRODUCT;
		//订单来源
		String makeOrderSourceTypeId = MarketingCouponConstance.BIND_TYPE_ORDER;
		//客户等级
		String makeCustomerLevelTypeId = MarketingCouponConstance.BIND_TYPE_LEVEL;
		//客户行业
		String makeCustomerTradeTypeId = MarketingCouponConstance.BIND_TYPE_TRADE;
		
        /***************制定规则 - 开始*****************/
		//产品类型
		String[] makeProductTypes = couponTypeVos.getMakeProductType();
		//订单来源
		String[] makeOrderSourceTypes = couponTypeVos.getMakeOrderSourceType();
		//客户等级
		String[] makeCustomerLevelTypes = couponTypeVos.getMakeCustomerLevelType();
		//客户行业
		String[] makeCustomerTradeTypes = couponTypeVos.getMakeCustomerTradeType();
		//产品类型
		this.setCouponTypeVo(makeProductTypes, createType, makeProductTypeId, conditionTypeCreateList);
		//订单来源
		this.setCouponTypeVo(makeOrderSourceTypes, createType, makeOrderSourceTypeId, conditionTypeCreateList);
		//客户等级
		this.setCouponTypeVo(makeCustomerLevelTypes, createType, makeCustomerLevelTypeId, conditionTypeCreateList);
		//订单来源
		this.setCouponTypeVo(makeCustomerTradeTypes, createType, makeCustomerTradeTypeId, conditionTypeCreateList);
		/***************制定规则 - 结束*****************/
		
		/***************使用规则 - 开始*****************/
		//产品类型
		String[] useProductTypesUse = couponTypeVos.getUseProductType();
		//订单来源
		String[] useOrderSourceTypesUse = couponTypeVos.getUseOrderSourceType();
		//客户等级
		String[] useCustomerLevelTypesUse = couponTypeVos.getUseCustomerLevelType();
		//客户行业
		String[] useCustomerTradeTypesUse = couponTypeVos.getUseCustomerTradeType();
		
		//产品类型
		this.setCouponTypeVo(useProductTypesUse, useType, makeProductTypeId, conditionTypeUseList);
		//订单来源
		this.setCouponTypeVo(useOrderSourceTypesUse, useType, makeOrderSourceTypeId, conditionTypeUseList);
		//客户等级
		this.setCouponTypeVo(useCustomerLevelTypesUse, useType, makeCustomerLevelTypeId, conditionTypeUseList);
		//订单来源
		this.setCouponTypeVo(useCustomerTradeTypesUse, useType, makeCustomerTradeTypeId, conditionTypeUseList);

		/***************使用规则 - 结束*****************/
		map.put("create", conditionTypeCreateList);
		map.put("use", conditionTypeUseList);
		return map;
	}
	/**   
	 * <p>
	 * Description:查询营销计划时，封装后台使用条件信息<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-26
	 * @param conditionTypeList 使用条件list，useType 使类型，conditionTypeId 类型id，list 封装后的list
	 * @return 
	 */
	public void setCouponTypeVo(String[] conditionTypeList,String useType,
			String conditionTypeId,List<ConditionType> list) {
		 // 校验参数是否为空
		if(CouponValidator.checkAryIsNotNull(conditionTypeList)){
			// 循环取出使用条件
			for(String value : conditionTypeList){
				// 使用条件
				ConditionType conditionType = new ConditionType();
				// 规则类型
				conditionType.setRuleType(useType);
				// 类型id
				conditionType.setType(conditionTypeId);
				// 金额
				conditionType.setValue(value);
				// 封装结果
				list.add(conditionType);
			}
		}
	}
	
	/**
	 * <p>
	 * Description: 通过条件查询优惠券<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-11-22
	 * @param condition
	 * @return Map
	 */
	@Override
	public Map<String, Object> searchCouponByCondition(CouponSearchCondition condition,
			int start, int limit, User user) {
		// 根据条件查询优惠券
		/**
		 * 1.通过手机号码或优惠券编码查询时，忽视其它查询条件，进行精确查询，
		 * 		并可查询全局优惠券信息（含非本部门的优惠券信息、以及无归属部门的优惠券信息）；
		 * 2.两个都填写时，优先按优惠券编码查询，忽视手机号码。
		 * 3.使用时间范围查询，指在所选时间范围内使用的优惠券；
		 * 4.当营销计划名称为空时，使用时间范围与发送时间范围必填其一，且查询时间范围最长为一个月；
		 * 5.当营销计划名称不为空时，使用时间范围与发送时间范围，不作限制；
		 */
		CouponValidator.canSearchCoupon(condition);
		//设置用户ID
		condition.setUsrId(user.getId());
		//设置部门标杆编码
		condition.setUsrDept(user.getEmpCode().getDeptId().getStandardCode());
		Department department=departmentService.getDeptByStandardCode(condition.getUnderDept());
		//如果权限包含手动券查询权限
		if(user.getAccessUris().contains(MarketingCouponConstance.HANDCOUPONFUNCTION)){
			condition.setUsrDept(MarketingCouponConstance.MARKETING_DEPT_CODE);
		}
		//调用service方法
		return couponServiceImpl.searchCouponByCondition(condition,department, start, limit);
	}

	/**
	 * <p>
	 * Description: 导出查询优惠券 excel报表<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-23
	 * @param title
	 * @param coupons
	 * @param fileName
	 * @param filePath
	 * @return
	 * boolean
	 */
	@Override
	@Transactional
	public boolean creatCouponReportExcel(CouponSearchCondition condition, String fileName, String filePath,User user) {
		//设置查询条件中的userid
		condition.setUsrId(user.getId());
		//设置查询条件中的用户所在部门
		condition.setUsrDept(user.getEmpCode().getDeptId().getStandardCode());

		//校验是否可以查询优惠券
		CouponValidator.canSearchCoupon(condition);
		//如果权限包含手动券查询权限
		if(user.getAccessUris().contains(MarketingCouponConstance.HANDCOUPONFUNCTION)){
				condition.setUsrDept(MarketingCouponConstance.MARKETING_DEPT_CODE);
		}
		//查询记录结果数
		int count = couponServiceImpl.countCouponByMutiCondition(condition);
		//检查查询优惠券数量是否大于20000条
		CouponValidator.checkOutputExcelCouponNum(count);
		//寻找到符合条件的优惠券
		List<CouponResultVO> coupons = couponServiceImpl.searchCouponResultVOByMutiCondition(condition);
		
		
		for(CouponResultVO couponRes : coupons){
			//转换Status
			couponRes.setStatus(
					MarketingCouponConstance.couponStatusStr.
					get(couponRes.getStatus()));
		}
		//filePath包括文件路径
		//fileName只包括该文件的正确文件名
		CreateExcel.createExcel(MarketingCouponConstance.couponTitle, coupons, fileName, filePath);
		return true;
	}	
	/**
	 * <p>
	 * Description: 批量将短信发券将需要发送的任务插入到短信发券表中<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-23
	 * @param sendCouponVO
	 * @param phones
	 * @param coupons
	 * @param user
	 * @return
	 * boolean
	 */
	@Override
	public boolean sendCouponMsg(SendCouponVO sendCouponVO,
			List<CouponCellphone> phones,List<Coupon> coupons,User user){
		//获得发送人编号
		String empCode = user.getEmpCode().getEmpCode();
		//允许发送的最大短信条数
		CouponValidator.checkMaxSendCouponNumber(phones);
		for(int i = 0 ; i < phones.size() ;i++){
			//根据查询实体 电话号码 优惠券编码 操作人编码 封装CouponCellphoneMsgInfo
			CouponCellphone phone = phones.get(i);
			//校验手机号的有效性,如有无效号码抛出异常 
			CouponValidator.checkCellphoneValid(phone);
			//获得优惠券
			Coupon coupon = coupons.get(i);
			CouponCellphoneMsgInfo ccmi = CouponMsgUtil.createCouponMsg(sendCouponVO, phones.get(i), 
					coupons.get(i).getCouponNumber(),
					empCode,MarketingCouponConstance.COUPON_MSG_SEND_DEPT);
			//将数据插入到待发短信表中
			couponServiceImpl.createCouponPhoneMsg(ccmi);
			//将优惠券状态设置为已发送
			coupon.setStatus(MarketingCouponConstance.COUPON_SENDING);
			//设置优惠券实体中优惠券发送号码
			coupon.setSendtelPhone(phone.getCellphone());
			//更新优惠券中发送状态和发送号码
			couponServiceImpl.updateCoupon(coupon);
		}
		return true;
	}
	/**
	 * <p>
	 * Description: 短信发券将需要发送的任务插入到短信发券表中<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-29
	 * @param ccmi
	 * @param user
	 * @return
	 * boolean
	 */
	@Deprecated
	public boolean sendCouponMsgOne(CouponCellphoneMsgInfo ccmi){
		//创建优惠券短信信息
		return couponServiceImpl.createCouponPhoneMsg(ccmi);
	}
	/**   
	 * <p>
	 * Description:更新所有已启用的营销计划<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-27
	 * @param 
	 * @return boolean
	 */
	@Transactional
	@Override
	public boolean updateMarketPlanForSchedual() {
		return couponServiceImpl.updateMarketPlanForSchedual();
	}

	
	/**
	 * <p>
	 * Description: 优惠券重发-待发短信处理 <br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-11-27
	 * @param coupon
	 * @param smsMsg
	 * @param user
	 * @return boolean
	 */
	private boolean sendCouponMsg(Coupon coupon, String smsMsg, Date endTime, User user){
		//用户的empcode
		String empCode = user.getEmpCode().getEmpCode();
		//封装发送短信实体
		CouponCellphoneMsgInfo ccmi = CouponMsgUtil.createCouponMsg(coupon, smsMsg, endTime, empCode);
		//调用发送短信方法
		couponServiceImpl.createCouponPhoneMsg(ccmi);
		return true;
	}

	/**
	 * <p>
	 * Description: 重发优惠券<br />
	 * </p>
	 * @author ZhuPJ
	 * @version 0.1 2012-11-27
	 * @return boolean
	 */
	@Override
	public boolean reSendCouponMsg(String couponNumber, User user) {
		// 根据优惠券编码获取优惠券信息
		Coupon coupon = couponServiceImpl.getCouponByNum(couponNumber);
		/**
		 * 仅对已发送状态优惠券
		 * 仅可重发5次
		 * 间隔10分钟以上
		 */
		CouponValidator.checkReSendCoupon(coupon, RESEND_MAX_TIMES, RESEND_MAX_INTERVAL);
		CouponRule rule = couponServiceImpl.getCouponRuleByMarketPlanId(coupon.getMarketPlanId());
		
		// 验证通过，更新优惠券信息
		coupon.setSendTime(new Date()); // 更新发送时间
		coupon.setSmstimes(1);			// 更新发送次数（1为加1,0为不变）
		couponServiceImpl.updateCoupon(coupon);
		
		// 保存短信待发
		this.sendCouponMsg(coupon, rule.getSmsContent(), rule.getEndtime(), user);
		return true;
	}
	/**
	 * <p>
	 * Description: 查询未发送的短信信息<br/>
	 * </p> 
	 * @author ZhouYuan
	 * @version 0.1 2012-11-28
	 * @param send
	 * @param start
	 * @param limit
	 * @return List<CouponCellphoneMsgInfo>
	 */
	public List<CouponCellphoneMsgInfo> searchSendCouponMsg(String send,int start, int limit){
		//查询未发送的短信信息
		return couponServiceImpl.searchSendCouponMsg(send,start,limit);
	}
	/**
	 * <p>
	 * Description: 更新已发送短信在短信发送表中的状态<br/>
	 * </p> 
	 * @author ZhouYuan
	 * @version 0.1 2012-11-28
	 * @param msgs
	 * @param start
	 * @param limit
	 * @return List<CouponCellphoneMsgInfo>
	 */
	@Transactional
	public  boolean updateSendCouponMsg(List<CouponCellphoneMsgInfo> msgs,String send){
		//循环List更新短信发送状态
		for( CouponCellphoneMsgInfo couponCellphone : msgs ){
			//将该条信息置为已发送
			couponCellphone.setSended(send);
			//更新数据库将该条记录更新为已发送
			couponServiceImpl.updateSendCouponMsg(couponCellphone);
		}
		return true;
	}


	/**
	 * <p>
	 * Description: 根据部门名称模糊查询部门<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-28
	 * @param deptIds
	 * @return
	 * List<Department>
	 */
	@Override
	public Map<String, Object> queryDeptListByDeptName(User user, String deptName,
			int start, int limit) {
		// 是否是营销活动管理小组
		String isMarketingDept = "00";// 否
		//创建返回实体
		Map<String,Object> map = new HashMap<String, Object>();
		//获得用户ID
		String userId = user.getId();
		// 如果登陆用户是 营销活动管理小组，就去查所有事业部
		if(checkMarketingDept(user,MarketingCouponConstance.MARKETING_DEPT_CODE)){
			isMarketingDept = "11";// 是
		}
		//获得部门列表
		List<Department> executeDeplist =couponServiceImpl.queryDeptListByName(userId,deptName,MarketingCouponConstance.SHIYEBU_NAME,isMarketingDept,start,limit);
		//计算总计值
		int totalcount = couponServiceImpl.counrQueryDeptListByName(userId,deptName,MarketingCouponConstance.SHIYEBU_NAME,isMarketingDept,start,limit);
		//封装返回实体的部门列表
		map.put("executeDeplist", executeDeplist);
		//封装返回实体的总计值
		map.put("totalcount", totalcount);
		return map;
	}
	/**
	 * <p>
	 * Description: 每天检查更新所有状态（不包括已过期）优惠券 状态是否过期，<br/>
	 *  过期的把状态置为：已过期<br/>
	 * </p> 
	 * @author 钟琼
	 * @version 0.1 2012-11-29
	 */
	@Transactional
	@Override
	public boolean updateCouponStatesByOverdueForSchedual() {
		return couponServiceImpl.updateCouponStatesByOverdueForSchedual();
		
	}
	 /**   
	 * <p>
	 * Description:查询使用规则<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-29
	 * @param marketplanId 营销计划ID
	 * @return MarketPlaneVO
	 */
	@Override
	 public MarketPlaneVO searchCouponRuleByMarketPlanIdDetail(String marketplanId){
		MarketPlaneVO marketPlaneVO = new MarketPlaneVO();
		if(StringUtils.isNotEmpty(marketplanId)){
			// 查询优惠券使用条件
			CouponRule couponRule = couponServiceImpl.getCouponRuleByMarketPlanId(marketplanId);
			marketPlaneVO.setCouponRule(couponRule);
		}
		 return marketPlaneVO;
	 }
	/**
	 * <p>
	 * Description: 根据使用标识查询未使用的运单中间表<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-29
	 * @param status
	 * @return List<WaybillCoupon> 运单中间表
	 */
	@Override
	public List<WaybillCoupon> searchWaybillCouponByStatus(String status,int start,int limit){
		//根据使用标识查询未使用的运单中间表
		return couponServiceImpl.searchWaybillCouponByStatus(status,start,limit);
	}
	/**
	 * <p>
	 * Description: 根据优惠券类型和优惠券类型ID查询手动发券营销计划<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-12-05
	 * @param couponType
	 * @param typeId
	 * @return HandMarketPlan 手动发券营销计划 
	 */
	@Override
	public HandMarketPlan searchHandMarketPlanByCouponTypeAndTypeId(String couponType,String typeId){
		//根据优惠券类型和优惠券类型ID查询手动发券营销计划
		return couponServiceImpl.searchHandMarketPlanByCouponTypeAndTypeId(couponType, typeId);
	}
	/**
	 * @作者：钟琼
	 * @时间：2012-12-4
	 * @描述：通过部门名称模糊查询所有部门
	 * @参数：deptName 部门名字
	 * @返回值：Map<String,Object> 部门集合
	 * */
	@Override
	public Map<String,Object> queryDeptListByName(String deptName,int start,int limit){
		//校验部门名称是否为空
		if(StringUtils.isEmpty(deptName)){
			return null;
		}
		//创建需要返回的MAP对象
		Map<String,Object> map = new HashMap<String,Object>();
		//查询部门列表
		List<Department> deptList = departmentService.queryDeptListByName(deptName,start,limit);
		//查询部门个数
		int totalcount = departmentService.queryDeptListByNameCount(deptName);
		//设置部门列表
		map.put("executeDeplist", deptList);
		//设置部门数
		map.put("totalcount", totalcount);
		return map;
	}
	/**
	 * @作者：钟琼
	 * @时间：2012-12-4
	 * @描述：通过部门名称模糊查询所有外场
	 * @参数：deptName 部门名字
	 * @返回值：Map<String,Object> 部门集合
	 * */
	@Override
	public Map<String,Object> queryOutDeptListByName(String deptName,int start,int limit){
		//校验部门名称是否为空
		if(StringUtils.isEmpty(deptName)){
			return null;
		}
		//创建需要返回的MAP对象
		Map<String,Object> map = new HashMap<String,Object>();
		//查询部门列表
		List<Department> deptList = departmentService.queryOutFieldListByName(deptName,start,limit);
		//查询部门个数
		int totalcount = departmentService.queryOutFieldListByCount(deptName);
		//设置部门列表
		map.put("executeDeplist", deptList);
		//设置部门数
		map.put("totalcount", totalcount);
		return map;
	}
	/**
	 * <p>
	 * Description: 根据ID更新运单中间表 <br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-29
	 * @param ccpmi
	 * @return boolean
	 */
	@Override
	@Deprecated
	public boolean updateWaybillCouponByStatus(WaybillCoupon wbCoupon){
		//根据ID更新运单中间表
		return couponServiceImpl.updateWaybillCouponByStatus(wbCoupon);
	}
	
	/**   
	 * <p>
	 * Description:更新手动发券规则<br/>
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-20
	 * @param ruleCouponHand 自动发券
	 * @return boolean
	 */
	@Transactional
	@Override
	public boolean updateCouponHand(RuleCouponHand ruleCouponHand){
		return couponServiceImpl.updateCouponHand(ruleCouponHand);
	}
	/**
	 * <p>
	 * Description: 存储过程调用创建优惠券 <br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-12-13
	 * @param couponType
	 * @return String
	 */
	public String callableCreateCouponHand(String couponType){
		//存储过程调用创建优惠券
		return couponServiceImpl.callableCreateCouponHand(couponType);
	}
	/**
	 * <p>
	 * Description: 存储过程调用自动券短信发送 <br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-12-13
	 * @param empCode
	 * @param deptCode
	 * @return String
	 */
	public String callableSendMsgAuto(Date startdate,Date enddate,String empCode,String deptCode){
		return couponServiceImpl.callableSendMsgAuto(startdate,enddate,empCode, deptCode);
	}
	/**
	 * <p>
	 * Description: 生产新点优惠券 <br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-09-05
	 * @param CouponForInterface
	 * @return String
	 */
	@Transactional
	@Override
	public String createCouponForInterface(CouponForInterface cfi){
		//校验接口参数是否正确
		CouponValidator.checkCouponForInterfaceParam(cfi);
		//查询现在可以使用的营销计划ID,手动券ID，使用条件ID，优惠券金额
		Coupon coupon = couponServiceImpl.
				searchMarketPlanByDeptStandardCodeAndActivityType(cfi);
		//校验是否有可以使用的营销计划
		//没有正在执行的营销计划为true,有正在执行的营销计划为false
		boolean activeMPStatus = CouponValidator.
				checkInterfaceMarketPlanIsActive(coupon);
		//没有正在执行的营销计划即创建相应的营销计划
		if( activeMPStatus == true ){
			//查询营销计划名称
			String marketPlanName = couponServiceImpl.
					searchMPNameMarketPlanByDeptStandardCodeAndActivityType(cfi);			
			//将优惠券接口实体转换为marketplanVO
			MarketPlaneVO marketplanVO = CouponUtils.
					convertCouponForInterfaceToMarketPlaneVO(cfi, marketPlanName);
			// 验证接口传值是否符合规则
			CouponValidator.checkInterfaceMarketPlanCanCreate(marketplanVO);
			
			// 新增营销计划
			couponServiceImpl.addMarketplanVOByMarketplan(marketplanVO.getMarketplan());
			//获得营销计划ID
			String marketPlanId = marketplanVO.getMarketplan().getId();
			//将营销计划ID插入到手动券规则中
			marketplanVO.getRuleCouponHand().setMarketPlanId(marketPlanId);
			//插入手动券规则
			couponServiceImpl.addMarketplanVOByRuleCouponHand(marketplanVO.getRuleCouponHand()); 
			// 新增优惠券使用条件
			marketplanVO.getCouponRule().setMarketPlanId(marketPlanId);
			//创建优惠券使用规则
			couponServiceImpl.addMarketplanVOByRuleCouponRule(marketplanVO.getCouponRule());
			//创建优惠券实体
			coupon = new Coupon();
			//设置营销计划ID
			coupon.setMarketPlanId(marketPlanId);
			//优惠券创建规则
			coupon.setCreateruleId(marketplanVO.getRuleCouponHand().getId());
			//优惠券使用规则
			coupon.setUseruleId(marketplanVO.getCouponRule().getId());
			//优惠券金额
			coupon.setUseCouponValue(cfi.getCouponValue());
		}
		//优惠券状态为未发送
		coupon.setStatus(MarketingCouponConstance.COUPON_NOSEND);
		//优惠券类型为手动券
		coupon.setTypeId(MarketingCouponConstance.COUPON_SENDHAND);
		//创建优惠券
		couponServiceImpl.createCoupon(coupon);
		//返回优惠券编码
		return coupon.getCouponNumber();
		
	}
	/**
	 * <p>
	 * Description: 检查是否是 营销活动管理小组 <br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-12-13
	 * @param empCode
	 * @param deptCode
	 * @return String
	 */
	public boolean checkMarketingDept(User user,String deptCode){
		if(null == user){
			return false;
		}
		if(null == user.getEmpCode()){
			return false;
		}
		if(null == user.getEmpCode().getDeptId()){
			return false;
		}
		if(StringUtils.isEmpty(user.getEmpCode().getDeptId().getStandardCode())){
			return false;
		}
		return user.getEmpCode().getDeptId().getStandardCode().equals(deptCode);
	}
	
	
	public void setCouponServiceImpl(ICouponService couponServiceImpl) {
		this.couponServiceImpl = couponServiceImpl;
	}
	public void setAlterMemberManager(IAlterMemberManager alterMemberManager) {
		this.alterMemberManager = alterMemberManager;
	}
	public void setAuthorizeService(IAuthorizeService authorizeService) {
		this.authorizeService = authorizeService;
	}
	public void setSearchDeptAndEmployeeManager(
			ISearchDeptAndEmployeeManager searchDeptAndEmployeeManager) {
		this.searchDeptAndEmployeeManager = searchDeptAndEmployeeManager;
	}
	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}
}
