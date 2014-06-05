/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CouponDaoImpl.java
 * @package com.deppon.crm.module.coupon.server.dao.impl 
 * @author ZhuPJ
 * @version 0.1 2012-11-12
 */
package com.deppon.crm.module.coupon.server.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.coupon.server.dao.ICouponDao;
import com.deppon.crm.module.coupon.shared.domain.Coupon;
import com.deppon.crm.module.coupon.shared.domain.CouponCellphoneMsgInfo;
import com.deppon.crm.module.coupon.shared.domain.CouponResultVO;
import com.deppon.crm.module.coupon.shared.domain.CouponSearchCondition;
import com.deppon.crm.module.coupon.shared.domain.SendCouponVO;
import com.deppon.crm.module.coupon.shared.domain.WaybillCoupon;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CouponDaoImpl.java
 * @package com.deppon.crm.module.coupon.server.dao.impl 
 * @author ZhuPJ
 * @version 0.1 2012-11-12
 */

public class CouponDaoImpl extends iBatis3DaoImpl implements ICouponDao {
	private final String NAMESPACE_COUPON="com.deppon.crm.module.coupon.shared.domain.Coupon.";
	//批量更新优惠券状态
	private final String UPDATE_COUPONSTATUS="updateCouponStatus";
	//更新单条优惠券状态
	private final String UPDATE_ONECOUPONSTATUS="updateOneCouponStatus";
	//根据优惠券编码查询 优惠券集合
	private final String SELECT_COUPONSBYNUMS="selectCouponsByNums";
	//根据优惠券编码查询 单条优惠券  
	private final String SELECT_COUPONSBYNUM="selectCouponsByNum";
	// 创建优惠券
	private final String INSERT_COUPON = "insertCoupon";
	// 更新优惠券信息
	private final String UPDATE_COUPON_INFO = "updateCoupon";
	//手机发券时查询显示内容
	private final String SEARCH_SEND_COPON_VO = "searchSendCouponVO";
	//创建优惠劵发送信息 
	private final String INSERT_COUPON_PHONE_MSG ="insertCouponCellphoneMsg";
	//根据优惠券状态 营销计划编码查询优惠券
	private final String SELECT_COUPONBYMPIANDSTATUS ="selectCouponByMarketPlanIdAndStatus";
	//根据优惠券编码更新优惠券状态 优惠券发送手机号码  发送时间
	private final String INSERT_COUPONBYCOUPONNUM = "updateCouponStatusPhoneSendTime";
	//根据归属部门 优惠券编码  优惠券状态 营销计划名称 发送时间 手机号 使用时间 查询优惠券信息
	private final String SELECT_COUPONBYMUTICONDITION = "selectCouponByMutiCondition";
	private final String COUNT_COUPONBYMUTICONDITION = "countCouponByMutiCondition";
	//根据发送标识查询为发送的短信
	private final String SELECT_SEND_COUPON_MSG = "searchSendCouponMsg";
	//根据ID更新发送短信状态
	private final String UPDATE_SEND_COUPON_MSG = "updateSendCouponMsg";
	//根据使用状态查询运单中间表
	private final String SEARCH_WAYBILL_COUPONBYSTATUS = "searchWaybillCouponBystatus";
	//根据ID更新运单中间表
	private final String UPDATE_WAYBILL_COUPONBYID = "updateWaybillCouponById";
	// 获取部门Seq
	private final String GET_DEPT_SEQ = "getDeptSeq";
	//存储过程 创建手动优惠券
	private final String CALLABLE_CREATE_COUPON_HAND ="createHandCouponBackground";
	//存储过程 自动券用于短信发券
	private final String CALLABLE_AUTO_SEND_COUPON_AUTO = "sendCouponMessageAuto";
	//存储过程   创建手动券(10000条以下)
	private final String CALLABLE_CREATE_HAND_COUPON_IMM = "createHandCouponImmediate";
	// 根据用户编码查询用户id
	private final String SEARCHUSERIDBYEMPCODE = "SearchUserIdByEmpcode";
	// 查询优惠券-部门查询
	private final String QUERYDEPTLISTBYNAME = "queryDeptListByName";
	// 查询优惠券-部门查询-条数
	private final String QUERYDEPTLISTBYNAMECOUNT = "queryDeptListByNameCount";


	/**   
	 * <p>
	 * Description:改变优惠券状态<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-13
	 * @param couponCode 优惠券状态编码
	 * @param couponNums 优惠券编码
	 * @return int
	 */
	@Override
	public int updateCouponStatus(String couponCode, List<String> couponNums) {
		//创建更新条件MAP
		Map<String,Object > map=new HashMap<String,Object>();
		//更新条件-优惠券编码
		map.put("couponCode", couponCode);
		//更新条件-优惠券数量
		map.put("couponNums", couponNums);
		return this.getSqlSession().update(NAMESPACE_COUPON+UPDATE_COUPONSTATUS,map);
	}
	/**   
	 * <p>
	 * Description:根据 优惠券编码 批量查询优惠券<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-13
	 * @param couponNums 优惠券编码
	 * @return boolean
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Coupon> searchCouponsByNums(List<String> couponNums) {
		return this.getSqlSession().selectList(NAMESPACE_COUPON+
				SELECT_COUPONSBYNUMS,couponNums);
	}
	/**   
	 * <p>
	 * Description:根据 优惠券编码查询 单条优惠券<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-13
	 * @param couponNums 优惠券编码
	 * @return boolean
	 */
	@Override
	public Coupon getCouponByNum(String couponNum) {
		return (Coupon) this.getSqlSession().selectOne(NAMESPACE_COUPON+
				SELECT_COUPONSBYNUM,couponNum);
	}
	/**   
	 * <p>
	 * Description:根据 优惠券编码查询单条优惠券<br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2012-11-13
	 * @param couponNums 优惠券编码
	 * @return int
	 */
	@Override
	public int updateOneCouponStatus(String couponCode, String couponNum) {
		Map<String,Object > map=new HashMap<String,Object>();
		map.put("couponCode", couponCode);
		map.put("couponNum", couponNum);
		return this.getSqlSession().update(NAMESPACE_COUPON+UPDATE_ONECOUPONSTATUS,map);
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
		return this.getSqlSession().insert(NAMESPACE_COUPON+INSERT_COUPON, coupon);
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
		this.getSqlSession().update(NAMESPACE_COUPON+UPDATE_COUPON_INFO, coupon);
		return true;
	}

	/**
	 * <p>
	 * Description: 短信发券时查询所需字段<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-20
	 * @param marketPlanId
	 * @return SendCouponVO 手机发券前台显示
	 */
	@Override
	public SendCouponVO searchSendCouponVO(Map<String,String> searchCondition) {
		return (SendCouponVO)this.getSqlSession().selectOne(NAMESPACE_COUPON+SEARCH_SEND_COPON_VO, searchCondition);
	}
	/**
	 * <p>
	 * Description: 插入优惠券短信发送表<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-21
	 * @param marketPlanId
	 * @return couponPhoneMsg 优惠劵短信发送实体类
	 */
	@Override
	public int createCouponPhoneMsg(CouponCellphoneMsgInfo couponPhoneMsg) {
		
		return getSqlSession().insert(NAMESPACE_COUPON+INSERT_COUPON_PHONE_MSG,couponPhoneMsg);
		
	}
	/**
	 * <p>
	 * Description: 根据营销计划编码 优惠券类型查询优惠券<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-21
	 * @param coupon start limit
	 * @return Coupon
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Coupon> selectCouponByMarketPlanIdAndStatus(Coupon coupon,
			int start, int limit) {
		RowBounds rb = new RowBounds(start,limit);
		return getSqlSession().selectList(NAMESPACE_COUPON+SELECT_COUPONBYMPIANDSTATUS,coupon,rb);
	}
	/**
	 * 根据优惠券编码更新优惠券状态 优惠券发送手机号码  发送时间
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-21
	 * @param coupon
	 * @return int
	 */
	@Override
	public int updateCouponByCouponNumber(Coupon coupon) {
		return getSqlSession().insert(NAMESPACE_COUPON+INSERT_COUPONBYCOUPONNUM,coupon);
	}
	
	/**
	 * 根据归属部门 优惠券编码  优惠券状态 营销计划名称 发送时间 手机号 使用时间 查询优惠券信息
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-22
	 * @param coupon
	 * @return boolean
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CouponResultVO> selectCouponByMutiCondition(CouponSearchCondition condition,
			int start, int limit){
		if (start >= 0 && limit > 0){
			// 优惠券查询
			RowBounds rb = new RowBounds(start,limit);
			return getSqlSession().selectList(NAMESPACE_COUPON+SELECT_COUPONBYMUTICONDITION,condition,rb);
		}else{
			// 优惠券导出
			return getSqlSession().selectList(NAMESPACE_COUPON+SELECT_COUPONBYMUTICONDITION,condition);
		}
	}
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.coupon.server.dao.ICouponDao#countCouponByMutiCondition(com.deppon.crm.module.coupon.shared.domain.CouponSearchCondition)
	 */
	@Override
	public int countCouponByMutiCondition(CouponSearchCondition condition) {
		return (Integer) getSqlSession().selectOne(NAMESPACE_COUPON+COUNT_COUPONBYMUTICONDITION,condition);
	}
	/**
	 * <p>
	 * Description: 根据发送标识查询未发送的短信<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-26
	 * @param sended
	 * @return List<CouponCellphoneMsgInfo> 未发送短信实体 
	 */
	@Override
	public List<CouponCellphoneMsgInfo> searchSendCouponMsg(String sended,int start,int limit){
		//分页
		RowBounds rb = new RowBounds(start,limit);
		return getSqlSession().selectList(NAMESPACE_COUPON+SELECT_SEND_COUPON_MSG,sended,rb);
	}
	/**
	 * <p>
	 * Description: 根据ID更新<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-26
	 * @param ccpmi
	 * @return int
	 */
	@Override
	public int updateSendCouponMsg(CouponCellphoneMsgInfo ccpmi){
		return getSqlSession().update(NAMESPACE_COUPON+UPDATE_SEND_COUPON_MSG,ccpmi);
	}
	/**
	 * <p>
	 * Description: 根据使用标识查询未使用的运单中间表<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-26
	 * @param status
	 * @return List<WaybillCoupon> 运单中间表
	 */
	@Override
	public List<WaybillCoupon> searchWaybillCouponByStatus(String status,int start,int limit){
		RowBounds rb = new RowBounds(start,limit);
		return getSqlSession().selectList(NAMESPACE_COUPON+SEARCH_WAYBILL_COUPONBYSTATUS,status,rb);
	}
	/**
	 * <p>
	 * Description: 根据ID更新运单中间表 <br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-11-26
	 * @param ccpmi
	 * @return int
	 */
	@Override
	public int updateWaybillCouponByStatus(WaybillCoupon wbCoupon){
		return getSqlSession().update(NAMESPACE_COUPON+UPDATE_WAYBILL_COUPONBYID,wbCoupon);
	}

	@Override
	public String getDeptSeq(String code){
		return (String) getSqlSession().selectOne(NAMESPACE_COUPON+GET_DEPT_SEQ, code);
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
	@Override
	public String callableCreateCouponHand(String couponType){
		Map<String,String> map = new HashMap<String,String>();
		map.put("i_coupontype", couponType);
		getSqlSession().selectOne(NAMESPACE_COUPON+CALLABLE_CREATE_COUPON_HAND,map);
		return map.get("o_flag");
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
	@Override
	public String callableSendMsgAuto(Date startdate,Date enddate,String empCode,String deptCode){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("i_startdate", startdate);
		map.put("i_enddate", enddate);
		map.put("i_senddeptcode", deptCode);
		map.put("i_sendempcode", empCode);
		getSqlSession().selectOne(NAMESPACE_COUPON+CALLABLE_AUTO_SEND_COUPON_AUTO,map);
		return (String)map.get("o_flag");
	}
	/**
	 * <p>
	 * Description: 存储过程调用创建手动优惠券 <br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2012-12-13
	 * @param empCode
	 * @param deptCode
	 * @return String
	 */
	@Override
	public String callableCreateHandCouponImm(int size, String marketPlanId ,
			String useRuleId,String couponValue){
		Map map = new HashMap();
		map.put("i_size", size);
		map.put("i_marketplanid", marketPlanId);
		map.put("i_useruleid", useRuleId);
		map.put("i_couponvalue", couponValue);
		getSqlSession().selectOne(NAMESPACE_COUPON+CALLABLE_CREATE_HAND_COUPON_IMM,map);
		return (String)map.get("o_flag");

	}
	/**
	 * <p>
	 * Description: 根据用户编码查询用户id <br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2013-02-25
	 * @param empCode
	 * @return String
	 */
	@Override
	public String searchUserIdByDeptSeq(String principal) {
		return (String) getSqlSession().selectOne(NAMESPACE_COUPON+SEARCHUSERIDBYEMPCODE,principal);
	}
	/**
	 * <p>
	 * Description: 查询优惠券-部门查询 <br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2013-02-25
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Department> queryDeptListByName(String userId, String deptName,
			String shiyebuName, String status, int start, int limit) {
		RowBounds rowBounds = new RowBounds(start,limit);
		Map<String,String> map = new HashMap<String,String>();
		map.put("userId", userId);
		map.put("deptName", deptName);
		map.put("shiyebuName", shiyebuName);
		map.put("status", status);
		return getSqlSession().selectList(NAMESPACE_COUPON+QUERYDEPTLISTBYNAME, map, rowBounds);
	}
	/**
	 * <p>
	 * Description: 查询优惠券-部门查询-条数 <br />
	 * </p>
	 * @author 钟琼
	 * @version 0.1 2013-02-25
	 */
	@Override
	public int counrQueryDeptListByName(String userId, String deptName,
			String shiyebuName, String status, int start, int limit) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("userId", userId);
		map.put("deptName", deptName);
		map.put("shiyebuName", shiyebuName);
		map.put("status", status);
		return (Integer) getSqlSession().selectOne(NAMESPACE_COUPON+QUERYDEPTLISTBYNAMECOUNT, map);
	}
}
