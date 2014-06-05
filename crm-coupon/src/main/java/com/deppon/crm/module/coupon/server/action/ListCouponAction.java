/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ListCouponAction.java
 * @package com.deppon.crm.module.coupon.server.action 
 * @author ZhuPJ
 * @version 0.1 2012-11-12
 */
package com.deppon.crm.module.coupon.server.action;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.coupon.server.manager.ICouponManager;
import com.deppon.crm.module.coupon.server.utils.CouponUtils;
import com.deppon.crm.module.coupon.shared.domain.CouponResultVO;
import com.deppon.crm.module.coupon.shared.domain.CouponSearchCondition;
import com.deppon.crm.module.coupon.shared.domain.CouponTypeVo;
import com.deppon.crm.module.coupon.shared.domain.MarketPlaneVO;
import com.deppon.crm.module.coupon.shared.domain.MarketingCouponConstance;
import com.deppon.crm.module.coupon.shared.domain.Marketplan;
import com.deppon.crm.module.coupon.shared.domain.SendCouponVO;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;

/**
 * <p>
 * Description: 优惠券Action（查询操作）<br />
 * </p>
 * 
 * @title ListCouponAction.java
 * @package com.deppon.crm.module.coupon.server.action
 * @author ZhuPJ
 * @version 0.1 2012-11-12
 */

public class ListCouponAction extends AbstractAction {
	// 优惠券Manage
	private ICouponManager couponManagerImpl;
	// 营销计划管理
	private Marketplan marketplan;
	// 营销计划Id
	private String marketPlanId;
	// 优惠券Vo
	private MarketPlaneVO marketPlaneVO;
	// 优惠券产品、订单来源、客户等级、客户行业类型
	private CouponTypeVo couponTypeVo;
	// 优惠券查询条件实体
	private CouponSearchCondition couponSearchCondition;
	// 优惠券查询列表
	private List<CouponResultVO> couponResults;
	// 起始页
	private int start;
	// 总条数
	private Long totalCount;
	// 每页显示条数
	private int limit;
	private List<Marketplan> listMarketplan;
	// 短信发券页面中根据营销计划id返回的优惠券基本信息实体
	private SendCouponVO couponBasicInfo;
	// 营销计划ID
	private String marketPlanID;
	//部门
	private List<Department> executeDeptList;
	//部门名称
	private String deptName;

	/**
	 * 营销计划管理：查询营销计划
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@JSON
	public String searchMarketPlan() {
		User user = (User) UserContext.getCurrentUser();
		Map<String, Object> map = couponManagerImpl.searchMarketPlan(
				user,marketplan, start, limit);
		listMarketplan = (List<Marketplan>) map.get("listMarketPlan");
		totalCount = Long.valueOf(map.get("totalCount").toString());
		return SUCCESS;
	}

	/**
	 * 查询修改营销计划
	 * @return
	 */
	@JSON
	public String searchMarketDetail() {
		marketPlaneVO = couponManagerImpl.searchMarketPlanDetail(marketPlanId);
		if(null!=marketPlaneVO){
			if (marketPlaneVO.getMarketplan().getCouponType()
					.equals(MarketingCouponConstance.COUPON_SENDHAND)) {// 手动发券
				if (marketPlaneVO.getCouponRule() != null) {
					couponTypeVo = couponManagerImpl
							.matchMarketPlanByConditionType(null, marketPlaneVO
									.getCouponRule().getConditionTypes());
				}
			} else {
				couponTypeVo = couponManagerImpl.matchMarketPlanByConditionType(
						marketPlaneVO.getRuleCouponAuto().getConditionTypes(),
						marketPlaneVO.getCouponRule().getConditionTypes());
			}
		}
		//处理发到货线路要求数据，给发货线路名赋值，以便显示
        CouponUtils.goodsLineOperation(marketPlaneVO);
        return SUCCESS;
	}

	/**
	 * 优惠券查询列表 
	 * 肖红叶 
	 * 2012-11-23
	 */
	@SuppressWarnings("unchecked")
	@JSON
	public String searchCouponByCondition() {
		User user = (User) UserContext.getCurrentUser();
		Map<String, Object> map = couponManagerImpl.searchCouponByCondition(
				couponSearchCondition, start, limit, user);
		couponResults = (List<CouponResultVO>) map.get("list");
		totalCount = Long.valueOf(map.get("count").toString());
		return SUCCESS;
	}
	
	/**
	 * 查看使用规则
	 * @return
	 */
	@JSON
	public String searchCouponRuleByMarketPlanId(){
		marketPlaneVO=couponManagerImpl.searchCouponRuleByMarketPlanIdDetail(marketPlanId);
		if (marketPlaneVO != null && marketPlaneVO.getCouponRule() != null) {
			couponTypeVo = couponManagerImpl
					.matchMarketPlanByConditionType(null, marketPlaneVO
							.getCouponRule().getConditionTypes());
		}
		return SUCCESS;
	}
	
	/**
	 * 查询发到货部门
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@JSON
	public String searchStartEndGoodDept(){
		String deptNames ="%"+deptName+"%";
		Map<String, Object> map = couponManagerImpl.queryDeptListByName(deptNames, start, limit);
		executeDeptList=(List<Department>)map.get("executeDeplist");
		totalCount=Long.valueOf(map.get("totalcount").toString());
		return SUCCESS;
	}
	
	/**
	 * 查询走货线路（外场）
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@JSON
	public String searchWalkGoodDept(){
		String deptNames ="%"+deptName+"%";
		Map<String, Object> map = couponManagerImpl.queryOutDeptListByName(deptNames, start, limit);
		executeDeptList=(List<Department>)map.get("executeDeplist");
		totalCount=Long.valueOf(map.get("totalcount").toString());
		return SUCCESS;
	}
	
	/**
	 * 查询部门
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@JSON
	public String searchDeptByDeptName(){
		User user = (User) UserContext.getCurrentUser();
		Map<String, Object> map = couponManagerImpl.queryDeptListByDeptName(user, deptName, start, limit);
		executeDeptList=(List<Department>)map.get("executeDeplist");
		totalCount=Long.valueOf(map.get("totalcount").toString());
		return SUCCESS;
	}
	
	/**
	 * 根据营销计划ID查询短信发券页面中优惠券的基本信息
	 *  xiaohongye 
	 *  2012-11
	 */
	@JSON
	public String searchSendCouponVOByMPI() {
		couponBasicInfo = couponManagerImpl
				.searchSendCouponVOByMPI(marketPlanID);
		return SUCCESS;
	}
	
	/**
	 * set、get方法区
	 * @param couponManagerImpl
	 */
	public void setCouponManagerImpl(ICouponManager couponManagerImpl) {
		this.couponManagerImpl = couponManagerImpl;
	}

	public Marketplan getMarketplan() {
		return marketplan;
	}

	public void setMarketplan(Marketplan marketplan) {
		this.marketplan = marketplan;
	}

	public String getMarketPlanId() {
		return marketPlanId;
	}

	public void setMarketPlanId(String marketPlanId) {
		this.marketPlanId = marketPlanId;
	}
	
	public void setMarketPlaneVO(MarketPlaneVO marketPlaneVO) {
		this.marketPlaneVO = marketPlaneVO;
	}

	public MarketPlaneVO getMarketPlaneVO() {
		return marketPlaneVO;
	}

	public void setCouponTypeVo(CouponTypeVo couponTypeVo) {
		this.couponTypeVo = couponTypeVo;
	}

	public CouponTypeVo getCouponTypeVo() {
		return couponTypeVo;
	}

	public CouponSearchCondition getCouponSearchCondition() {
		return couponSearchCondition;
	}

	public void setCouponSearchCondition(
			CouponSearchCondition couponSearchCondition) {
		this.couponSearchCondition = couponSearchCondition;
	}
	
	public List<CouponResultVO> getCouponResults() {
		return couponResults;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public List<Marketplan> getListMarketplan() {
		return listMarketplan;
	}

	public void setCouponBasicInfo(SendCouponVO couponBasicInfo) {
		this.couponBasicInfo = couponBasicInfo;
	}

	public SendCouponVO getCouponBasicInfo() {
		return couponBasicInfo;
	}

	public void setMarketPlanID(String marketPlanID) {
		this.marketPlanID = marketPlanID;
	}
	
	public List<Department> getExecuteDeptList() {
		return executeDeptList;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
}
