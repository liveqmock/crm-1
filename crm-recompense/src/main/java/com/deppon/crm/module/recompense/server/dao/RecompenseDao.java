/**   
 * @title ApplicationCreateDao.java
 * @package com.deppon.crm.recompense.dao
 * @description 理赔创建dao接口
 * @author 潘光均
 * @update 2012-1-12 上午8:19:08
 * @version V1.0   
 */
package com.deppon.crm.module.recompense.server.dao;

import java.util.Date;
import java.util.List;

import com.deppon.crm.module.recompense.shared.domain.CellphoneMessageInfo;
import com.deppon.crm.module.recompense.shared.domain.RecSmsInformation;
import com.deppon.crm.module.recompense.shared.domain.RecompenseApplication;
import com.deppon.crm.module.recompense.shared.domain.RecompenseForCC;
import com.deppon.crm.module.recompense.shared.domain.RecompenseSearchCondition;
import com.deppon.crm.module.recompense.shared.domain.TodoReminder;

/**
 * @description 实现理赔创建的增删改查功能
 * @author 潘光均
 * @version 0.1 2012-1-12
 * @date 2012-1-12
 */

public interface RecompenseDao {

	/**
	 * @description 实现理赔处理理赔单的保存.
	 * @author 潘光均
	 * @version 0.1 2012-1-12
	 * @param app
	 *            :理赔处理需要保存的理赔处理单
	 * @date 2012-1-12
	 * @return 是否保存成功
	 * @update 2012-1-12 上午8:36:35
	 */
	public RecompenseApplication insertRecompenseApplication(
			RecompenseApplication app);

	/**
	 * @description 根据id查询理赔单.
	 * @author 潘光均
	 * @version 0.1 2012-1-13
	 * @param id
	 *            :查询的id
	 * @date 2012-1-13
	 * @return 理赔单数据
	 * @update 2012-1-13 上午9:31:51
	 */
	RecompenseApplication getRecompenseApplicationById(String id);


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
	 boolean updateRecompenseApplication(RecompenseApplication app);

	/**
	 * @description 根据id删除理赔单.
	 * @author 潘光均
	 * @version 0.1 2012-1-13
	 * @param id
	 *            :理赔单id
	 * @date 2012-1-13
	 * @return 是否删除成功
	 * @update 2012-1-13 下午3:29:34
	 */
	 boolean deleteRecompenseApplicationById(String id);

	/**
	 * @description 通过客户编码查询理赔信息.
	 * @author 潘光均
	 * @version 0.1 2012-2-14
	 * @param cusNum
	 *            :客户编码
	 * @date 2012-2-14
	 * @return List<RecompenseApplication>
	 * @update 2012-2-14 上午9:51:03
	 */
	List<RecompenseApplication> searchRecompenseByCustNum(String cusNum);

	/**
	 * @description 通过大区id和理赔单状态查询理赔信息.
	 * @author 潘光均
	 * @version 0.1 2012-2-14
	 * @param custId
	 *            :客户id recompenseId:当前理赔单id
	 * @date 2012-2-14
	 * @return List<RecompenseApplication>
	 * @update 2012-2-14 上午9:51:03
	 */
	List<RecompenseApplication> getRecompenseApplicationByCustomerId(
			String custId, String recompenseId, Date startTime, Date endTime);

	// 分页
	List<RecompenseApplication> getRecompenseApplicationByCustomerId(
			String custId, String recompenseId, int start, int limit);

	/**
	 * 
	 * @description 根据条件查询理赔.
	 * @author 安小虎
	 * @version 0.1 2012-3-31
	 * @param 理赔查询对象
	 * @date 2012-3-31
	 * @return 理赔对对象集合
	 * @update 2012-3-31 上午9:10:16
	 */
	List<RecompenseApplication> searchRecompenseByCondition(
			RecompenseSearchCondition condition);

	/**
	 * 
	 * @description 根据理赔查询条件得到满足条件的记录数.
	 * @author 安小虎
	 * @version 0.1 2012-4-10
	 * @param 理赔查询对象
	 * @date 2012-4-10
	 * @return int：满足条件的记录数
	 * @update 2012-4-10 下午4:22:27
	 */
	int getRecompenseCountByCondition(RecompenseSearchCondition condition);

	/**
	 * 
	 * @description 根据运单号查询理赔DAO接口.
	 * @author 安小虎
	 * @version 0.1 2012-4-1
	 * @param 运单号
	 * @date 2012-4-1
	 * @return 理赔对象集合
	 * @update 2012-4-1 下午2:56:36
	 */
	List<RecompenseApplication> searchRecompenseByWaybillNum(String waybillNum);

	/**
	 * 
	 * @description 修改理赔单中多赔ID.
	 * @author 安小虎
	 * @version 0.1 2012-4-20
	 * @param 理赔单ID
	 * @param 多赔单ID
	 * @date 2012-4-20
	 * @return boolean：成功与否
	 * @update 2012-4-20 下午1:47:54
	 */
	boolean updateRecompenseOverpayById(String recompenseId, String overPayId);

	/**
	 * 
	 * @description 根据客户ID获得客户所有理赔信息（理赔时间倒序）.
	 * @author 安小虎
	 * @version 0.1 2012-4-24
	 * @param 客户ID
	 * @date 2012-4-24
	 * @return 理赔集合
	 * @update 2012-4-24 下午3:27:44
	 */
	List<RecompenseApplication> getRecompenseListByCustId(String custId);

	/**
	 * 
	 * <p>
	 * Description:更新理赔状态<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param app
	 * @return boolean
	 */
	 boolean updateRecompenseStatusInfo(RecompenseApplication app);

	/**
	 * 
	 * <p>
	 * Description:根据客户编号查询理赔<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param condition
	 * @return List<RecompenseApplication>
	 */
	 List<RecompenseApplication> searchRecompensePageByCustNum(
			RecompenseSearchCondition condition);

	/**
	 * 
	 * <p>
	 * Description:根据客户编码查询理赔数<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param condition
	 * @return int
	 */
	 int getRecompenseCountByCustNum(RecompenseSearchCondition condition);
	/**
	 * 
	 * <p>
	 * Description:根据理赔号更新付款单id<br />
	 * </p>
	 * @author 华龙
	 * @version 0.1 2013-1-5
	 * @param recompenseNum
	 * @param id
	 * void
	 */
	public void updatePaymentIdByRecompenseNum(String recompenseNum, String id);
	/**
	 * 
	 * <p>
	 * Description:根据理赔号查询付款单id<br />
	 * </p>
	 * @author 华龙
	 * @version 0.1 2013-1-5
	 * @param recompenseNum
	 * @param id
	 * void
	 */
	public String findPaymentIdByRecompenseNum(String recompenseNum);

	/**
	 * 
	 * <p>
	 * Description:生成待办提醒<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param status
	 * @return List<TodoReminder>
	 */
	 List<TodoReminder> generateTodoReminder(String status);
	
	 /**
	  * 
	  * <p>
	  * Description:初始化有财务作废及到付客户未完成的理赔单数据<br />
	  * </p>
	  * @author roy
	  * @version 0.1 2013-6-4
	  * @return
	  * List<RecompenseApplication>
	  */
	 List<RecompenseApplication> findInvalidCustRecompenseList();

	public void updateCustomerInfo(String recompenseId, String leaveCustomerId,
			String arriveCustomerId);
	
	/**
	 * 理赔监控，获取短信接收人(经理)
	 * @param recompenseIdList 理赔ID集合
	 * @return 返回理赔监控短信通知对象 RecSmsInformation
	 */
	public List<RecSmsInformation> getMessageReceiverByManager(List<String> recompenseIdList);

	/**
	 * 理赔监控，获取短信接收人(理赔专员)
	 * @param recompenseIdList 理赔ID集合
	 * @return 返回理赔监控短信通知对象 RecSmsInformation
	 */
	public List<RecSmsInformation> getMessageReceiverByCommissioner(List<String> recompenseIdList);

	/**
	 * 理赔监控，获取短信接收人(区域经理)
	 * @param recompenseIdList 理赔ID集合
	 * @return 返回理赔监控短信通知对象 RecSmsInformation
	 */
	public List<RecSmsInformation> getMessageReceiverByAreaManager(List<String> recompenseIdList);

	/**
	 * 理赔监控，获取短信接收人(大区总经理)
	 * @param recompenseIdList 理赔ID集合
	 * @return 返回理赔监控短信通知对象 RecSmsInformation
	 */
	public List<RecSmsInformation> getMessageReceiverByGeneralManager(List<String> recompenseIdList);

	/**
	 * 理赔监控，获取短信接收人(事业部办公室主任)
	 * @param recompenseIdList 理赔ID集合
	 * @return 返回理赔监控短信通知对象 RecSmsInformation
	 */
	public List<RecSmsInformation> getMessageReceiverByDirector(List<String> recompenseIdList);

	/**
	 * 理赔监控，获取短信接收人(事业部总裁)
	 * @param recompenseIdList 理赔ID集合
	 * @return 返回理赔监控短信通知对象 RecSmsInformation
	 */
	public List<RecSmsInformation> getMessageReceiverByPresident(List<String> recompenseIdList);
	
	/**
	 * 理赔类型值为“在线理赔”时，时间为当前时间-创建时间（客户官网提交的时间）
	 * 理赔监控短信发送模板，获取处理天数
	 * @param recompenseNum 运单号
	 * @return 返回处理天数
	 */
	public String getRecDurationOnline(String recompenseNum);
	
	/**
	 * 当理赔类型值为非“在线理赔”时，时间为当前时间-索赔时间（索赔录入时间）
	 * 理赔监控短信发送模板，获取处理天数
	 * @param recompenseNum 运单号
	 * @return 返回处理天数
	 */
	public List<RecSmsInformation> getRecDurationNoOnline(String recompenseNum);
	
	/**
	 * 获取快递业务管理部负责人信息
	 * @param deptCode 快递业务管理部部门编号
	 * @return
	 */
	CellphoneMessageInfo findExpressDelivery(String deptCode);
	
	/**
	 * 
	 * <p>
	 * Description:<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-8-19
	 * @param phone
	 *            电话号码
	 * @param limit
	 *            限制
	 * @param start
	 *            开始
	 * @return List<RecompenseForCC>
	 */
	List<RecompenseForCC> searchRecompenseHistoryList(String phone, int limit,
			int start);
	/**
	 * 
	 * <p>
	 * Description:统计条数<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-8-19
	 * @param phone
	 * @return
	 * int
	 */
	int countRecompenseHistory(String phone);
	/**
	 * 在线理赔监控，获取短信接收人
	 * @param recompenseIdList 理赔ID集合
	 * @return 返回理赔监控短信通知对象 RecSmsInformation
	 */
	List<RecSmsInformation> getMessageReceiverForOnline(List<String> waybillnumberList,String noticeTypes);
	
	/**
	 * 
	 * <p>
	 * Description:根据部门ID查询部门经理ID
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-11-13下午3:58:04
	 * void
	 * @update 2013-11-13下午3:58:04
	 */
	String getManagerIdByDeptId(String applyDeptId);
	
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
	RecompenseApplication getRecompense(String workflowNo);
}
