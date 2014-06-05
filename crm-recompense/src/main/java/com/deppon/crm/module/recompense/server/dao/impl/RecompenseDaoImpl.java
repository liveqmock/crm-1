package com.deppon.crm.module.recompense.server.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.recompense.server.dao.RecompenseDao;
import com.deppon.crm.module.recompense.shared.domain.CellphoneMessageInfo;
import com.deppon.crm.module.recompense.shared.domain.RecSmsInformation;
import com.deppon.crm.module.recompense.shared.domain.RecompenseApplication;
import com.deppon.crm.module.recompense.shared.domain.RecompenseForCC;
import com.deppon.crm.module.recompense.shared.domain.RecompenseSearchCondition;
import com.deppon.crm.module.recompense.shared.domain.TodoReminder;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;
import com.deppon.foss.framework.shared.util.string.StringUtil;

import java.text.MessageFormat;
import com.deppon.crm.module.recompense.server.utils.Constants;

/**
 * @description dao层JDBC实现
 * @author 潘光均
 * @version 0.1 2012-1-30
 * @date 2012-1-30
 */
public class RecompenseDaoImpl extends iBatis3DaoImpl implements RecompenseDao {
	// 命名空间
	private static final String NAMESPACE = "com.deppon.crm.module.recompense.shared.domain.RecompenseApplication.";
	// 根据条件查询理赔
	private static final String SEARCH_RECOMPENSE_BYCONDITION = "searchRecompenseByCondition";
	// 根据运单号查询理赔
	private static final String SEARCH_RECOMPENSE_BYWAYBILLNUM = "searchRecompenseByWaybillNum";
	// 根据客户编码查询理赔
	private static final String SEARCH_RECOMPENSE_BYCUSTNUM = "searchRecompenseByCustNum";
	// 根据客户编码查询理赔
	private static final String SEARCH_RECOMPENSE_PAGE_BYCUSTNUM = "searchRecompensePageByCustNum";
	// 根据客户编码查询理赔数
	private static final String SEARCH_RECOMPENSE_COUNT_BYCUSTNUM = "getRecompenseCountByCustNum";
	// 根据条件获得理赔数
	private static final String GET_RECOMPENSECOUNT_BYCONDITION = "getRecompenseCountByCondition";
	// 根据id更新多赔
	private static final String UPDATE_RECOMPENSEOVERPAY_BYID = "updateRecompenseOverpayById";
	// 根据id获得理赔列表
	private static final String GET_RECOMPENSELIST_BYCUSTID = "getRecompenseListByCustId";
	private static final String UPDATE_PAYMENTID_BY_RECOMPENSENUM = "updatePaymentIdByRecompenseNum";
	private static final String FIND_PAYMENTID_BY_RECOMPENSENUM = "findPaymentIdByRecompenseNum";
	// 根据id获取通知对象信息
	private static final String GET_MESSAGE_MANAGER = "getMessageManager";
	private static final String GET_MESSAGE_COMMISSIONER = "getMessageCommissioner";
	private static final String GET_MESSAGE_AREAMANAGER = "getMessageAreaManager";
	private static final String GET_MESSAGE_GENERALMANAGER = "getMessageGeneralManager";
	private static final String GET_MESSAGE_DIRECTOR = "getMessageDirector";
	private static final String GET_MESSAGE_PRESIDENT = "getMessagePresident";
	
	private static final String GET_MESSAGE_ONLINE = "getMessageOnline";
	private static final String GET_MESSAGE_NOONLINE = "getMessageNoOnline";
	private static final String GET_EXPRESS_DELIVERY = "getExpressDelivery";
	private static final String SEARCH_RECOMPENSE_FOR_CC = "searchRecompenseForCC";
	private static final String COUNT_RECOMPENSE_FOR_CC = "countRecompenseForCC";
	private static final String GET_REC_BY_WFNO = "getRecByWfNo";

	/**
	 * @description 根据ID查询理赔单
	 * @author 潘光均
	 * @version 0.1
	 * @param id
	 *            理赔单ID
	 * @date 2012-2-2
	 * @return 查询结果
	 * @update 2012-2-2 下午14:38
	 */

	public RecompenseApplication getRecompenseApplicationById(String id) {
		if (null == id || "".equals(id)) {
			return null;
		}
		return (RecompenseApplication) this.getSqlSession().selectOne(
				NAMESPACE + "queryById", id);
	}

	/**
	 * @description 保存理赔单.
	 * @author 潘光均
	 * @version 0.1 2012-2-2
	 * @param app
	 *            需保存的理赔单
	 * @date 2012-2-2
	 * @return 是否保存成功
	 * @update 2012-2-2 下午16:19
	 */
	@Override
	public RecompenseApplication insertRecompenseApplication(
			RecompenseApplication app) {
		if (null == app) {
			return null;
		}
		if (null == app.getId() || "".equals(app.getId().trim())) {
			this.getSqlSession().insert(NAMESPACE + "insert", app);
			return app;
		}
		return null;
	}

	/**
	 * @description 实现理赔处理单的修改.
	 * @author 潘光均
	 * @version 0.1 2012-1-12
	 * @param app
	 *            :理赔处理需要保存的理赔处理单
	 * @date 2012-1-12
	 * @return 是否保存成功
	 * @update 2012-1-12 上午8:36:35
	 */
	@Override
	public boolean updateRecompenseApplication(RecompenseApplication app) {
		if (null == app) {
			return false;
		}
		if (null == app.getId()) {
			return false;
		}
		getSqlSession().update(NAMESPACE + "updateById", app);
		return true;
	}

	/**
	 * 
	 * <p>
	 * Description:更新理赔状态<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param app
	 * @return
	 * 
	 */
	@Override
	public boolean updateRecompenseStatusInfo(RecompenseApplication app) {
		if (null == app) {
			return false;
		}
		if (null == app.getId()) {
			return false;
		}
		getSqlSession().update(NAMESPACE + "updateRecompenseStatusInfo", app);
		return true;
	}

	/**
	 * 
	 * <p>
	 * Description:根据id删除理赔单<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param id
	 * @return
	 * 
	 */
	@Override
	public boolean deleteRecompenseApplicationById(String id) {
		if (null == id || "".equals(id)) {
			return false;
		}
		if (null == getRecompenseApplicationById(id)) {
			return false;
		}
		getSqlSession().delete(NAMESPACE + "deleteById", id);
		return true;
	}

	/**
	 * 
	 * <p>
	 * Description:通过客户编码查询理赔信息.<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param cusNum
	 * @return
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<RecompenseApplication> searchRecompenseByCustNum(String cusNum) {
		return this.getSqlSession().selectList(
				NAMESPACE + SEARCH_RECOMPENSE_BYCUSTNUM, cusNum);
	}

	/**
	 * 
	 * <p>
	 * Description:通过大区id和理赔单状态查询理赔信息<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param custId
	 * @param recompenseId
	 * @param startTime
	 * @param endTime
	 * @return
	 * 
	 */
	@Override
	public List<RecompenseApplication> getRecompenseApplicationByCustomerId(
			String custId, String recompenseId, Date startTime, Date endTime) {
		if (null == custId || null == recompenseId) {
			return new ArrayList<RecompenseApplication>();
		}
		Map map = new HashMap();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("custId", custId);
		map.put("recompesenId", recompenseId);
		return getSqlSession().selectList(
				NAMESPACE + "getRecompenseApplicationByCustomerId", map);
	}

	/**
	 * 
	 * <p>
	 * Description:通过大区id和理赔单状态查询理赔信息<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param custId
	 * @param recompenseId
	 * @param start
	 * @param limit
	 * @return
	 * 
	 */
	@Override
	public List<RecompenseApplication> getRecompenseApplicationByCustomerId(
			String custId, String recompenseId, int start, int limit) {
		// 设置分页
		RowBounds bound = new RowBounds(start, limit);
		if (null == custId || null == recompenseId) {
			return new ArrayList<RecompenseApplication>();
		}
		Map map = new HashMap();
		map.put("custId", custId);
		map.put("recompesenId", recompenseId);
		return getSqlSession().selectList(
				NAMESPACE + "getRecompenseApplicationByCustomerId", map, bound);
	}

	/**
	 * 
	 * <p>
	 * Description:查询理赔信息<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param condition
	 * @return
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<RecompenseApplication> searchRecompenseByCondition(
			RecompenseSearchCondition condition) {
		RowBounds bounds = new RowBounds(condition.getStart(),
				condition.getLimit());
		return this.getSqlSession().selectList(
				NAMESPACE + SEARCH_RECOMPENSE_BYCONDITION, condition, bounds);
	}

	/**
	 * 
	 * <p>
	 * Description:根据客户编号查询理赔<<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param condition
	 * @return
	 * 
	 */
	public List<RecompenseApplication> searchRecompensePageByCustNum(
			RecompenseSearchCondition condition) {
		RowBounds bounds = new RowBounds(condition.getStart(),
				condition.getLimit());
		return this.getSqlSession()
				.selectList(NAMESPACE + SEARCH_RECOMPENSE_PAGE_BYCUSTNUM,
						condition, bounds);
	}

	/**
	 * 
	 * <p>
	 * Description:根据客户编号查询理赔<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param condition
	 * @return
	 * 
	 */
	public int getRecompenseCountByCustNum(RecompenseSearchCondition condition) {
		return (Integer) this.getSqlSession().selectOne(
				NAMESPACE + SEARCH_RECOMPENSE_COUNT_BYCUSTNUM, condition);
	}

	/**
	 * 
	 * <p>
	 * Description:根据运单号查询理赔DAO接口<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param waybillNum
	 * @return
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<RecompenseApplication> searchRecompenseByWaybillNum(
			String waybillNum) {
		return this.getSqlSession().selectList(
				NAMESPACE + SEARCH_RECOMPENSE_BYWAYBILLNUM, waybillNum);
	}

	/**
	 * 
	 * <p>
	 * Description:根据理赔查询条件得到满足条件的记录数<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param condition
	 * @return
	 * 
	 */
	@Override
	public int getRecompenseCountByCondition(RecompenseSearchCondition condition) {
		return (Integer) this.getSqlSession().selectOne(
				NAMESPACE + GET_RECOMPENSECOUNT_BYCONDITION, condition);
	}

	/**
	 * 
	 * <p>
	 * Description:修改理赔单中多赔ID<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param recompenseId
	 * @param overPayId
	 * @return
	 * 
	 */
	@Override
	public boolean updateRecompenseOverpayById(String recompenseId,
			String overPayId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("recompenseId", recompenseId);
		map.put("overPayId", overPayId);
		int result = this.getSqlSession().update(
				NAMESPACE + UPDATE_RECOMPENSEOVERPAY_BYID, map);
		return result > 0 ? true : false;
	}

	/**
	 * 
	 * <p>
	 * Description:根据客户ID获得客户所有理赔信息（理赔时间倒序<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param custId
	 * @return
	 * 
	 */
	@Override
	public List<RecompenseApplication> getRecompenseListByCustId(String custId) {
		return this.getSqlSession().selectList(
				NAMESPACE + GET_RECOMPENSELIST_BYCUSTID, custId);
	}

	/**
	 * 
	 * <p>
	 * Description:生成待办提醒<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param status
	 * @return
	 * 
	 */
	public List<TodoReminder> generateTodoReminder(String status) {
		return getSqlSession().selectList(NAMESPACE + "generateTodoReminder",
				status);
	}

	/**
	 * 
	 * <p>
	 * Description:根据理赔号更新付款单id<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2013-1-5
	 * @param recompenseNum
	 * @param id
	 *            void
	 */
	public void updatePaymentIdByRecompenseNum(String recompenseNum, String id) {
		if (null != recompenseNum && null != id) {
			Map map = new HashMap();
			map.put("recompenseNum", recompenseNum);
			map.put("id", id);
			getSqlSession().update(
					NAMESPACE + UPDATE_PAYMENTID_BY_RECOMPENSENUM, map);
		}

	}

	/**
	 * 
	 * <p>
	 * Description:根据理赔号查询付款单id<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2013-1-5
	 * @param recompenseNum
	 * @param id
	 *            void
	 */
	@Override
	public String findPaymentIdByRecompenseNum(String recompenseNum) {
		if (null != recompenseNum) {
			return (String) getSqlSession().selectOne(
					NAMESPACE + FIND_PAYMENTID_BY_RECOMPENSENUM, recompenseNum);
		}
		return null;
	}

	/**
	 * 
	 * <p>
	 * Description:初始化有财务作废及到付客户未完成的理赔单数据<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-6-4
	 * @return
	 * 
	 */
	@Override
	public List<RecompenseApplication> findInvalidCustRecompenseList() {
		return getSqlSession().selectList(
				NAMESPACE + "findInvalidCustRecompenseList");

	}

	@Override
	public void updateCustomerInfo(String recompenseId, String leaveCustomerId,
			String arriveCustomerId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("recompenseId", recompenseId);
		map.put("leaveCustomerId", leaveCustomerId);
		map.put("arriveCustomerId", arriveCustomerId);
		getSqlSession().update(NAMESPACE + "updateCustomerInfo", map);

	}
	
	/**
	 * 理赔监控，获取短信接收人(经理)
	 * @param recompenseIdList 理赔ID集合
	 * @return 返回理赔监控短信通知对象 RecSmsInformation
	 */
	@Override
	public List<RecSmsInformation> getMessageReceiverByManager(List<String> recompenseIdList) {
		return this.getSqlSession().selectList(NAMESPACE + GET_MESSAGE_MANAGER, recompenseIdList);
	}
	
	/**
	 * 理赔监控，获取短信接收人(理赔专员)
	 * @param recompenseIdList 理赔ID集合
	 * @return 返回理赔监控短信通知对象 RecSmsInformation
	 */
	@Override
	public List<RecSmsInformation> getMessageReceiverByCommissioner(List<String> recompenseIdList) {
		return this.getSqlSession().selectList(NAMESPACE + GET_MESSAGE_COMMISSIONER, recompenseIdList);
	}
	
	/**
	 * 理赔监控，获取短信接收人(区域经理)
	 * @param recompenseIdList 理赔ID集合
	 * @return 返回理赔监控短信通知对象 RecSmsInformation
	 */
	@Override
	public List<RecSmsInformation> getMessageReceiverByAreaManager(List<String> recompenseIdList) {
		return this.getSqlSession().selectList(NAMESPACE + GET_MESSAGE_AREAMANAGER, recompenseIdList);
	}
	
	/**
	 * 理赔监控，获取短信接收人(大区总经理)
	 * @param recompenseIdList 理赔ID集合
	 * @return 返回理赔监控短信通知对象 RecSmsInformation
	 */
	@Override
	public List<RecSmsInformation> getMessageReceiverByGeneralManager(List<String> recompenseIdList) {
		return this.getSqlSession().selectList(NAMESPACE + GET_MESSAGE_GENERALMANAGER, recompenseIdList);
	}
	
	/**
	 * 理赔监控，获取短信接收人(事业部办公室主任)
	 * @param recompenseIdList 理赔ID集合
	 * @return 返回理赔监控短信通知对象 RecSmsInformation
	 */
	@Override
	public List<RecSmsInformation> getMessageReceiverByDirector(List<String> recompenseIdList) {
		return this.getSqlSession().selectList(NAMESPACE + GET_MESSAGE_DIRECTOR, recompenseIdList);
	}
	
	/**
	 * 理赔监控，获取短信接收人(事业部总裁)
	 * @param recompenseIdList 理赔ID集合
	 * @return 返回理赔监控短信通知对象 RecSmsInformation
	 */
	@Override
	public List<RecSmsInformation> getMessageReceiverByPresident(List<String> recompenseIdList) {
		return this.getSqlSession().selectList(NAMESPACE + GET_MESSAGE_PRESIDENT, recompenseIdList);
	}
	
	/**
	 * 当理赔类型值为非“在线理赔”时，时间为当前时间-索赔时间（索赔录入时间）
	 * 理赔监控短信发送模板，获取处理天数
	 * @param recompenseNum 运单号
	 * @return 返回处理天数
	 */
	@Override
	public List<RecSmsInformation> getRecDurationNoOnline(String recompenseNum) {
		return this.getSqlSession().selectList(NAMESPACE + GET_MESSAGE_NOONLINE, recompenseNum);
	}
	
	/**
	 * 理赔类型值为“在线理赔”时，时间为当前时间-创建时间（客户官网提交的时间）
	 * 理赔监控短信发送模板，获取处理天数
	 * @param recompenseNum 运单号
	 * @return 返回处理天数
	 */
	@Override
	public String getRecDurationOnline(String recompenseNum) {
		return (String)this.getSqlSession().selectOne(NAMESPACE + GET_MESSAGE_ONLINE, recompenseNum);
	}
	
	/**
	 * 获取快递业务管理部负责人信息
	 * @param deptCode 快递业务管理部部门编号
	 * @return
	 */
	@Override
	public CellphoneMessageInfo findExpressDelivery(String deptCode) {
		return (CellphoneMessageInfo)this.getSqlSession().selectOne(NAMESPACE + GET_EXPRESS_DELIVERY, deptCode);
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-8-19
	 * @param phone
	 * @param limit
	 * @param start
	 * @return
	 * 
	 */
	@Override
	public List<RecompenseForCC> searchRecompenseHistoryList(String phone,
			int limit, int start) {

		RowBounds bounds = new RowBounds(start,
				limit);		
		return this.getSqlSession()
				.selectList(NAMESPACE + SEARCH_RECOMPENSE_FOR_CC,
						phone, bounds);
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-8-19
	 * @param phone
	 * @return
	 *
	 */
	@Override
	public int countRecompenseHistory(String phone) {
		return (Integer) this.getSqlSession()
				.selectOne(NAMESPACE + COUNT_RECOMPENSE_FOR_CC,
						phone);		
	}
	
	@Override
	public List<RecSmsInformation> getMessageReceiverForOnline(
			List<String> waybillnumberList,String noticeTypes) {
		List<RecSmsInformation> list = new ArrayList<RecSmsInformation>();
		if(StringUtil.isEmpty(noticeTypes)||waybillnumberList==null||waybillnumberList.size()==0){
			return list;
		}
		if(noticeTypes.contains(Constants.MESSAGE_RECEIVER_MANAGER_ONLINE)){
			list.addAll(this.getSqlSession().
					selectList(NAMESPACE + "getMessageManagerForOnline", waybillnumberList));
		}
		if(noticeTypes.contains(Constants.MESSAGE_RECEIVER_AREAMANAGER__ONLINE)){
			list.addAll(this.getSqlSession().
					selectList(NAMESPACE + "getMessageAreaManagerForOnline", waybillnumberList));
		}
		if(noticeTypes.contains(Constants.MESSAGE_RECEIVER_GENERALMANAGER__ONLINE)){
			list.addAll(this.getSqlSession().
					selectList(NAMESPACE + "getMessageGeneralManagerForOnline", waybillnumberList));
		}
		return list;
	}
	
	/**
	 * <p>
	 * Description:根据部门ID查询部门经理ID
	 * </p>
	 * @author 	zouming
	 * @extends	@see com.deppon.crm.module.recompense.server.dao.RecompenseDao#getManagerIdByDeptId(java.lang.String)
	 * @version 0.1 2013-11-13下午3:58:40
	 * @param applyDeptId
	 * @return
	 * @update 	2013-11-13下午3:58:40
	 */
	@Override
	public String getManagerIdByDeptId(String applyDeptId) {
		return (String)this.getSqlSession()
				.selectOne(NAMESPACE + "getManagerIdByDeptId",
						applyDeptId);
	}
	
	/**
	 * 
	 * @description 根据流程号查询理赔信息
	 * @author andy
	 * @version 0.1 2014-1-10
	 * @param 流程号
	 * @date 2014-1-10
	 * @return RecompenseApplication
	 * @update 2014-1-10
	 */
	@Override
	public RecompenseApplication getRecompense(String workflowNo) {
		if (StringUtil.isEmpty(workflowNo)) {
			return null;
		}
		return (RecompenseApplication) this.getSqlSession().selectOne(
				NAMESPACE + GET_REC_BY_WFNO, workflowNo);
	}
	
}
