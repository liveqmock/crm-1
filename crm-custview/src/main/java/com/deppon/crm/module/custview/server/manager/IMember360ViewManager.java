package com.deppon.crm.module.custview.server.manager;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.customer.shared.domain.Account;
import com.deppon.crm.module.customer.shared.domain.ApproveData;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.ContractDetailView;
import com.deppon.crm.module.customer.shared.domain.CustCredit;
import com.deppon.crm.module.customer.shared.domain.MemberOperationLog;
import com.deppon.crm.module.custview.shared.domain.AccountView;
import com.deppon.crm.module.custview.shared.domain.ComplaintRecompenseView;
import com.deppon.crm.module.custview.shared.domain.ContactView;
import com.deppon.crm.module.custview.shared.domain.ContractView;
import com.deppon.crm.module.custview.shared.domain.CrossMapping;
import com.deppon.crm.module.custview.shared.domain.IntegratedCustDevView;
import com.deppon.crm.module.custview.shared.domain.LatelyTrade;
import com.deppon.crm.module.custview.shared.domain.MemberIntegratedInfoView;
import com.deppon.crm.module.custview.shared.domain.MemberPointsView;
import com.deppon.crm.module.custview.shared.domain.MemberView;
import com.deppon.crm.module.custview.shared.domain.OperationAnalysis;
import com.deppon.crm.module.custview.shared.domain.SearchFastCondition;
import com.deppon.crm.module.custview.shared.domain.TwelveMonth;
import com.deppon.crm.module.marketing.shared.WarnLostInfoFor360;
import com.deppon.crm.module.marketing.shared.domain.BusinessOpportunity;
import com.deppon.crm.module.order.shared.domain.Order;

/**
 * @description 客户360度视图manager接口
 * @author 安小虎
 * @version 0.1 2012-4-23
 * @date 2012-4-23
 */
public interface IMember360ViewManager {
	/**
	 * 
	 * @description 根据客户ID获得投诉分析数据.
	 * @author 安小虎
	 * @version 0.1 2012-4-25
	 * @param 客户ID
	 * @date 2012-7-20
	 * @return 投诉分析数据
	 * @update 2012-7-20 上午8:48:17
	 */
	List getComplaintStatisticsByCustId(String custId);
	
	/**
	 * 
	 * <p>
	 * Description:非高级查询
	 * 	1.如果没有填写客户编码，根据其他信息查询出客户编码再根据客户编码获取客户综合信息.
	 *  2.如果填写了客户编码，直接根据客户编码查询出客户综合信息
	 * </p>
	 * @author zouming
	 * @version 0.1 2012-10-29
	 * @param searchFastCondition
	 * @return
	 * MemberIntegratedInfoView
	 * @update 2012-10-29
	 */
	MemberIntegratedInfoView searchMemberInfoFast(SearchFastCondition searchFastCondition);

	/**
	 * 
	 * @description 高级查询 ，即先通过弹窗查询客户信息获取一个客户编码，再通过客户编码获取客户综合信息
	 * @author 安小虎
	 * @version 0.1 2012-4-23
	 * @param 客户编码
	 * @date 2012-4-23
	 * @return 客户360度信息对象
	 * @update 2012-4-23 下午4:44:45
	 */
	MemberIntegratedInfoView getMemberIntegratedInfo(String custNo,String comboVal);

	/**
	 * 
	 * @description 根据客户ID获取客户基本信息.
	 * @author 安小虎
	 * @version 0.1 2012-4-24
	 * @param 客户ID
	 * @date 2012-4-24
	 * @return 客户基本信息
	 * @update 2012-4-24 上午9:02:46
	 */
	MemberView getMemberBasicInfoByCustId(String custId);

	/**
	 * 
	 * @description 根据客户ID获取该客户所有联系人信息列表.
	 * @author 安小虎
	 * @version 0.1 2012-4-24
	 * @param 客户ID
	 * @date 2012-4-24
	 * @return 联系人信息集合
	 * @update 2012-4-24 上午9:48:39
	 */
	List<Contact> getAllContactInfoByCustId(String custId);

	/**
	 * 
	 * @description 根据联系人ID获得该联系人详细信息（包含接货地址信息列表）.
	 * @author 安小虎
	 * @version 0.1 2012-4-24
	 * @param 联系人ID
	 * @date 2012-4-24
	 * @return 联系人详细信息对象
	 * @update 2012-4-24 上午11:26:04
	 */
	ContactView getContactInfoById(String contactId);

	/**
	 * 
	 * @description 根据客户ID获取该客户所有帐户信息列表.
	 * @author 安小虎
	 * @version 0.1 2012-4-24
	 * @param 客户ID
	 * @date 2012-4-24
	 * @return 客户所有帐户信息集合
	 * @update 2012-4-24 下午2:42:30
	 */
	List<Account> getAccountListByCustId(String custId);

	/**
	 * 
	 * @description 根据帐户ID获取该帐户详细信息.
	 * @author 安小虎
	 * @version 0.1 2012-4-24
	 * @param 帐户ID
	 * @date 2012-4-24
	 * @return 帐户详细信息
	 * @update 2012-4-24 下午2:47:45
	 */
	AccountView getAccountInfoById(String accountId);

	/**
	 * 
	 * @description 根据客户ID获取该客户的所有合同信息列表.
	 * @author 安小虎
	 * @version 0.1 2012-4-24
	 * @param 客户ID
	 * @date 2012-4-24
	 * @return 合同信息列表
	 * @update 2012-4-24 上午11:48:45
	 */
	List<ContractDetailView> getContractListByCustId(String custId);

	/**
	 * 
	 * @description 根据合同ID获得合同详细信息（包含附件信息）.
	 * @author 安小虎
	 * @version 0.1 2012-4-24
	 * @param 合同ID
	 * @date 2012-4-24
	 * @return 合同详细信息
	 * @update 2012-4-24 上午11:51:42
	 */
	ContractView getContractInfoById(String contractId);

	/**
	 * 
	 * @description 根据客户ID获得客户开发/日程综合信息集合集.
	 * @author 安小虎
	 * @version 0.1 2012-4-24
	 * @param 客户ID
	 * @date 2012-4-24
	 * @return 维护信息集合集
	 * @update 2012-4-24 下午1:52:51
	 */
	IntegratedCustDevView getIntegratedCustDevByCustId(String custId);

	/**
	 * 
	 * @description 根据客户ID、起始时间获取订单信息列表(只获取订单有绑定联系人的记录列表).
	 * @author 安小虎
	 * @version 0.1 2012-4-24
	 * @param 客户ID
	 * @param 起始时间
	 * @param start
	 * @param limit
	 * @date 2012-4-24
	 * @return 订单集合
	 * @update 2012-4-24 下午2:13:46
	 */
	List<Order> getOrderListByCustId(String custId, Date startDate,
			Date endDate, int start, int limit);

	/**
	 * 
	 * @description 根据客户ID、起始时间获取订单信息记录数.
	 * @author 安小虎
	 * @version 0.1 2012-4-24
	 * @param 客户ID
	 * @param 起始时间
	 * @date 2012-4-24
	 * @return 订单记录数
	 * @update 2012-4-24 下午2:13:46
	 */
	Long getOrderCountByCustId(String custId, Date startDate, Date endDate);

	/**
	 * 
	 * @description 根据客户ID获取该客户所有联系人的积分情况列表;
	 *              该客户总积分及总可用积分，允许客户兑换积分状态（通过计算所有联系人积分获得）.
	 * @author 安小虎
	 * @version 0.1 2012-4-24
	 * @param none
	 * @date 2012-4-24
	 * @return none
	 * @update 2012-4-24 下午2:34:31
	 */
	MemberPointsView getMemberPointByCustId(String custId);

	/**
	 * 
	 * @description 根据客户编码获取该客户所有投诉信息列表（按投诉时间倒序排列）; 该客户所有理赔信息列表（按理赔时间倒序排列）.
	 * @author 安小虎
	 * @version 0.1 2012-4-24
	 * @param 客户ID
	 * @param 客户编码
	 * @date 2012-4-24
	 * @return 投诉理赔集合集
	 * @update 2012-4-24 下午2:30:45
	 */
	ComplaintRecompenseView getComplaintRecompense(String custId, String custNo);

	/**
	 * 
	 * @description 根据客户ID获得其最近一次交易记录.
	 * @author 安小虎
	 * @version 0.1 2012-4-26
	 * @param 客户ID
	 * @date 2012-4-26
	 * @return 交易记录
	 * @update 2012-4-26 上午9:32:40
	 */
	LatelyTrade getCustLatelyTrade(String custId);

	/**
	 * 
	 * @Title: getOperationAnalysisDetailListByCustId
	 * @Description: 統計明細
	 * @param @param custId
	 * @param @param startDate
	 * @param @param endDate
	 * @param @return 设定文件
	 * @return List<OperationAnalysis> 返回类型
	 * @throws
	 */
	List<OperationAnalysis> getOperationAnalysisDetailListByCustId(String  custType,
			String custNum,String custId, Date startDate, Date endDate);

	/**
	 * 
	 * @Title: getOperationAnalysisListByCustId
	 * @Description: 统计分析结果结构转换
	 * @param @param custId
	 * @param @param startDate
	 * @param @param endDate
	 * @param @return 设定文件
	 * @return List<TwelveMonth> 返回类型
	 * @throws
	 */
	List<TwelveMonth> getOperationAnalysisListByCustId(String custType,String custId,
			Date startDate, Date endDate);

	/**
	 * 
	 * @Title: getSummaryPlusListByCustId
	 * @Description: 发货量和到达货量统计
	 * @param @param custId
	 * @param @param startDate
	 * @param @param endDate
	 * @param @return 设定文件
	 * @return List<TwelveMonth> 返回类型
	 * @throws
	 */
	List<TwelveMonth> getSummaryPlusListByCustId(List<OperationAnalysis>oaList,List<OperationAnalysis>oaListExp);
	
	/**
	 * 
	 * <p>
	 * Description:获取 Excel 文件名 <br />
	 * </p>
	 * @author hpf
	 * @version 0.1 2013-1-29
	 * @param searchOrderDateFrom
	 * @param searchOrderDateTo
	 * @return
	 * String
	 */
	String getExcrlName(Date searchOrderDateFrom,Date searchOrderDateTo);
	
	/**
	 * 
	 * <p>
	 * Description: 创建 Excel<br />
	 * </p>
	 * @author hpf
	 * @version 0.1 2013-1-29
	 * @param twelveMonthList
	 * @param searchOrderDateFrom
	 * @param searchOrderDateTo
	 * @param custName
	 * @return
	 * InputStream
	 */
	InputStream createExcel(String custType,List<TwelveMonth> twelveMonthList,
			Date searchOrderDateFrom, Date searchOrderDateTo, String custName);

	/**
	 * <p>
	 * Description:根据客户ID查询客户变更记录
	 * </p>
	 * @author 106143
	 * @version 0.1 2014-4-1下午3:19:48
	 * @param custId
	 * @return
	 * List<MemberOperationLog>
	 * @update 2014-4-1下午3:19:48
	 */
	List<MemberOperationLog> searchMemberOperationLogList(String custId);

	/**
	 * <p>
	 * Description:根据工作流号查询变更详细信息
	 * </p>
	 * @author 106143
	 * @version 0.1 2014-4-1下午3:19:55
	 * @param custId
	 * @return
	 * List<ApproveData>
	 * @update 2014-4-1下午3:19:55
	 */
	List<ApproveData> searchApproveDataList(String custId);

	/**
	 * <p>
	 * Description:获取客户信用信息
	 * </p>
	 * @author 106143
	 * @version 0.1 2014-4-4下午4:11:45
	 * @param custId
	 * @return
	 * CustCredit
	 * @update 2014-4-4下午4:11:45
	 */
	CustCredit getCustCreditByCustId(String custId);

	/**
	 * <p>
	 * Description:查询流失预警信息
	 * </p>
	 * @author 106143
	 * @version 0.1 2014-4-4下午7:11:51
	 * @param custNum
	 * @return
	 * List<WarnLostCust>
	 * @update 2014-4-4下午7:11:51
	 */
	List<WarnLostInfoFor360> searchWarnLostCust(String custNum);

	/**
	 * <p>
	 * Description:查询客户某时间段内运单信息
	 * </p>
	 * @author 106143
	 * @version 0.1 2014-4-9上午9:39:50
	 * @param custId
	 * @param searchOrderDateFrom
	 * @param searchOrderDateTo
	 * @param start
	 * @param limit
	 * @return
	 * List<WaybillInfo>
	 * @update 2014-4-9上午9:39:50
	 */
	Map searchWaybillInfoList(String custId,
			Date searchOrderDateFrom, Date searchOrderDateTo, int page,
			int limit);

	/**
	 * <p>
	 * Description:查询最近三次商机信息
	 * </p>
	 * @author 106143
	 * @version 0.1 2014-4-21下午3:12:48
	 * @param custId
	 * @return
	 * List<BusinessOpportunity>
	 * @update 2014-4-21下午3:12:48
	 */
	List<BusinessOpportunity> searchBusinessOpportunityList(String custId);

	/**
	 * <p>
	 * Description:根据外部系统ID（客户里面的erpId）获得交叉映射信息
	 * </p>
	 * @author 106143
	 * @version 0.1 2014-4-23下午2:52:55
	 * @param contactId
	 * @return
	 * List<CrossMapping>
	 * @update 2014-4-23下午2:52:55
	 */
	List<CrossMapping> getCrossMappingByErpId(String erpId);

}
