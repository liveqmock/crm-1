/**
 * Filename:	ComplaintManager.java
 * Description:
 * Copyright:	Copyright (c)2011
 * Company:		IBM
 * @author:		
 * @version:	
 * create at:	2012-2-9 上午10:33:23
 *
 * Modification History:
 * Date			Author			Version			Description
 * ------------------------------------------------------------------------
 * 2012-2-9	    
 */

package com.deppon.crm.module.complaint.server.manager.impl;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.deppon.crm.module.authorization.server.service.IUserService;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.complaint.IComplaintOperate;
import com.deppon.crm.module.client.order.IOaAccidentOperate;
import com.deppon.crm.module.client.order.IWaybillOperate;
import com.deppon.crm.module.client.order.domain.ComplaintInformInfo;
import com.deppon.crm.module.client.order.domain.FossWaybillInfo;
import com.deppon.crm.module.client.order.domain.WaybillInfo;
import com.deppon.crm.module.client.sms.ISmsInfoSender;
import com.deppon.crm.module.client.sms.domain.SmsInformation;
import com.deppon.crm.module.common.server.manager.IMessageManager;
import com.deppon.crm.module.common.server.util.Constant;
import com.deppon.crm.module.common.shared.domain.Message;
import com.deppon.crm.module.complaint.server.action.MessageSendingUtil;
import com.deppon.crm.module.complaint.server.manager.ComplaintRule;
import com.deppon.crm.module.complaint.server.manager.ComplaintValidate;
import com.deppon.crm.module.complaint.server.manager.IComplaintManager;
import com.deppon.crm.module.complaint.server.service.IBasciLevelService;
import com.deppon.crm.module.complaint.server.service.IBulletinService;
import com.deppon.crm.module.complaint.server.service.ICellphoneMessageInfoService;
import com.deppon.crm.module.complaint.server.service.IComplaintInfoService;
import com.deppon.crm.module.complaint.server.service.IComplaintService;
import com.deppon.crm.module.complaint.server.service.IFeedBackReasionService;
import com.deppon.crm.module.complaint.server.service.IProcResultService;
import com.deppon.crm.module.complaint.server.service.IResultService;
import com.deppon.crm.module.complaint.server.service.IWorkOrderService;
import com.deppon.crm.module.complaint.server.util.BasicInfoConstants;
import com.deppon.crm.module.complaint.server.util.BasicInfoUtils;
import com.deppon.crm.module.complaint.server.util.BasicInfoValidator;
import com.deppon.crm.module.complaint.server.util.ComplaintConstants;
import com.deppon.crm.module.complaint.server.util.Constants;
import com.deppon.crm.module.complaint.server.util.DateUtil;
import com.deppon.crm.module.complaint.server.util.PropertiesFactory;
import com.deppon.crm.module.complaint.server.util.PropertiesFile;
import com.deppon.crm.module.complaint.server.util.PropertiesHelper;
import com.deppon.crm.module.complaint.shared.domain.BasciLevel;
import com.deppon.crm.module.complaint.shared.domain.BasciLevelSearchCondition;
import com.deppon.crm.module.complaint.shared.domain.BasciLevelView;
import com.deppon.crm.module.complaint.shared.domain.BasicBusScopeVO;
import com.deppon.crm.module.complaint.shared.domain.BasicBusType;
import com.deppon.crm.module.complaint.shared.domain.BasicInfo;
import com.deppon.crm.module.complaint.shared.domain.BasicLevel;
import com.deppon.crm.module.complaint.shared.domain.BasicSearchCondition;
import com.deppon.crm.module.complaint.shared.domain.Bulletin;
import com.deppon.crm.module.complaint.shared.domain.CellphoneMessageInfo;
import com.deppon.crm.module.complaint.shared.domain.Complaint;
import com.deppon.crm.module.complaint.shared.domain.ComplaintInfo;
import com.deppon.crm.module.complaint.shared.domain.ComplaintSearchCondition;
import com.deppon.crm.module.complaint.shared.domain.FeedBackReasion;
import com.deppon.crm.module.complaint.shared.domain.ProcResult;
import com.deppon.crm.module.complaint.shared.domain.Result;
import com.deppon.crm.module.complaint.shared.domain.ResultSearchCondition;
import com.deppon.crm.module.complaint.shared.domain.TaskComplaintCount;
import com.deppon.crm.module.complaint.shared.domain.WorkOrder;
import com.deppon.crm.module.complaint.shared.exception.CompException;
import com.deppon.crm.module.complaint.shared.exception.ComplaintException;
import com.deppon.crm.module.complaint.shared.exception.ComplaintExceptionType;
import com.deppon.crm.module.customer.server.manager.IMemberManager;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.duty.server.service.IDutyService;
import com.deppon.crm.module.order.server.manager.IOrderManager;
import com.deppon.crm.module.order.shared.domain.Order;
import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.server.service.IEmployeeService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.cache.CacheManager;
import com.deppon.foss.framework.cache.ICache;
import com.deppon.foss.framework.entity.IRole;
import com.deppon.foss.framework.exception.BusinessException;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.shared.util.string.StringUtil;

/**
 * 
 * <p>
 * Description:这里写描述<br />
 * </p>
 * 
 * @title ComplaintManager.java
 * @package com.deppon.crm.module.complaint.server.manager.impl
 * @author ouy
 * @version 0.1 2012-4-11
 */

public class ComplaintManager implements IComplaintManager {
	static {
		// 获取属性文件实例
		// Properties文件类型
		// EREDBOS对应complaintInfo.properties属性文件
		PropertiesHelper pHelper = PropertiesFactory
				.getPropertiesHelper(PropertiesFile.COMPLAINTINFO);
		// 获取属性值
		perCount = Integer.parseInt(pHelper.getValue("perCount"));
	}
	// 接入工单的最大接入数
	private static final int LIMITCOUNT = 10;
	// 查询待办工单返回的工单列表key
	public static final String WAIT_COMPLAINT_LIST_KEY = "WAIT_COMPLAINT_LIST_KEY";
	// 查询待办工单返回的工单列表的总行数key
	public static final String WAIT_COMPLAINT_LIST_COUNT_KEY = "WAIT_COMPLAINT_LIST_COUNT_KEY";
	// 工单号反写失败，请重新点击提交按钮
	private static final String COMPLAINT_RETURN_FAILUE = "工单号反写失败，请重新点击提交按钮~";
	private static Integer perCount = 500;
	// 发送短信数
	private static Integer smsCount = 1000;
	// 工单列表
	private static String complaintListStr = "complaintList";
	// 日志记录
	private Logger log = Logger.getLogger(ComplaintManager.class);
	/**
	 * @author 张斌
	 * @time 2013-3-14
	 * @category 
	 *           增加责任service(退回上报人，退回处理人，退回提交人和上报除了状态为“前台已处理”的创建和修改都在在t_crm_duty表中进行记录
	 *           )
	 */
	private IDutyService dutyService;

	// 引用的services
	// 用户管理业务逻辑层
	/**
	 * @param dutyService
	 *            the dutyService to set
	 */
	public void setDutyService(IDutyService dutyService) {
		this.dutyService = dutyService;
	}

	private IUserService userService;
	// 工单实现类
	private IComplaintService complaintService;
	// 会员管理，会员对外的接口。
	private IMemberManager memberManager;
	// 订单管理
	private IOrderManager orderManager;
	// 通报对象
	private IBulletinService bulletinService;
	// 基础层级
	private IBasciLevelService basciLevelService;
	// 处理结果
	private IResultService resultService;
	// 退回原因
	private IFeedBackReasionService feedBackReasionService;
	// 保存操作记录
	private IWorkOrderService workOrderService;
	// 基础资料处理结果表
	private IProcResultService procResultService;
	// 作 者：张斌 最后修改时间：2011年10月20日 描 述：
	// T_ORG_EMPLOYEE员工表的Service的接口（查找符合条件的所有员工信息，并分页、查找符合条件的个数）
	private IEmployeeService employeeService;
	// 作 者：张斌 最后修改时间：2011年10月20日 描 述：
	// T_ORG_DEPARTMENT部门信息Service层接口（deptSeq的过滤，查询当前部门的子部门）
	private IDepartmentService departmentService;
	// 与ERP运单相关的操作的集合接口
	private IWaybillOperate waybillOperate;
	// 呼叫中心投诉相关操作
	private IComplaintOperate complaintOperate;
	// 短信通知Service
	private ICellphoneMessageInfoService cellphoneMessageInfoService;
	/* private Object _lock = new Object(); */
	// 消息管理
	private IMessageManager messageManager;
	// 关于理赔的差错信息查询相关接口
	private IOaAccidentOperate oaAccidentOperate;
	// 投诉通知信息service
	private IComplaintInfoService complaintInfoService;
	private ISmsInfoSender smsInfoSender;
	private static Integer SMS_SIZE = 500;

	// Properties文件静态工厂
	/**
	 * @Title: searchAPPListByOrder
	 * @Description: TODO(加载投诉上报界面初始化数据)
	 * @param @return 设定文件
	 * @return List 返回类型
	 * @throws
	 * @see <a href="package.html#section">查看流程图</a>
	 */
	public Complaint initPageData() {
		// 工单
		Complaint app = null;
		// app=complaintService.initComplaintApplication();
		// 返回工单
		return app;
	}

	/**
	 * @Title: searchAppByConditions
	 * @Description: TODO(根据投诉单号或联系方式实时查询投诉列表)
	 * @param @param app
	 * @param @return 设定文件
	 * @return ComplaintApplication 返回类型
	 * @throws
	 * @see <a href="package.html#section">查看流程图</a>
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Complaint searchAppByConditions(Complaint app) {
		// String number=app.getAppNumber();
		// String contract=app.getTeleNumber();
		// return service.searchAppByNumber(number);
		return null;
	}

	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Map searchComplaints(ComplaintSearchCondition condition) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		
		//全为 null 则条件不通过
		if(		//上报开始时间 和上报结束时间
				(null == condition.getReportTimeStart() || null==condition.getReportTimeEnd())
				&&//处理 开始时间 和处理结束时间
				(null == condition.getProcessBeginTime() || null==condition.getProcessEndTime())
				&&//回访开始时间 和 回访结束时间
				(null == condition.getVisitorTimeStart() || null==condition.getVisitorTimeEnd())
				&&//凭证号
				(null == condition.getBill() || "".equals(condition.getBill().trim()))
				&&//处理编号
				(null == condition.getDealbill() || "".equals(condition.getDealbill().trim()))
				&&//重复工单码
				(null == condition.getRecomcode() || "".equals(condition.getRecomcode().trim()))
		){
			map.put("complaintCount","0");
			return map;
		}
		
		return this.searchBaseComplaints(condition);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Map searchRepeatComplaints(ComplaintSearchCondition condition) {
		// TODO Auto-generated method stub
		//重复工单编码不存在
		if(null == condition.getRebindno() || "".equals(condition.getRebindno().trim())){
			ComplaintException e = new ComplaintException(ComplaintExceptionType.REBINDNO_ISNULL);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		return this.searchBaseComplaints(condition);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Map searchReportedComplaints(ComplaintSearchCondition condition) {
		// TODO Auto-generated method stu
		Map<String, Object> map = new HashMap<String, Object>();
		//验证 凭证号 或 联系电话必须存在一个
		if(
			(null == condition.getBill() || "".equals(condition.getBill().trim()))
			&&
			(null == condition.getPhone() || "".equals(condition.getPhone().trim()))
		){
			return map;
		}
		return this.searchBaseComplaints(condition);
	}
	
	/**
	 * 查询工单
	 * <p>
	 * Description:根据查询条件查询工单<br />
	 * </p>
	 * 
	 * @author ouy
	 * @version 0.1 2012-4-16
	 * @param condition
	 * @return Map
	 */
	private Map searchBaseComplaints(ComplaintSearchCondition condition) {
		// 返回结果MAP
		Map<String, Object> map = new HashMap<String, Object>();
		// 分页
		Integer limit = condition.getLimit();
		// 分页
		Integer start = condition.getStart();
		 
		// 判断是否是上报或退回查询
		// 是否是被绑定列表
		if (!Constants.TO_BE_BANGING_YES.equals(condition.getToBeBanging())) {
			// 单号不为空的话
			if (null != condition.getBill()
					&& !"".equals(condition.getBill().trim())) {
				// 获取单号
				String bill = condition.getBill();
				// 工单查询条件
				condition = new ComplaintSearchCondition();
				// 单号
				condition.setBill(bill);
				// 处理编号不为空的话
			} else if (null != condition.getDealbill()
					&& !"".equals(condition.getDealbill().trim())) {
				// 如果处理编号不为空
				String dealbill = condition.getDealbill();
				// 工单查询条件
				condition = new ComplaintSearchCondition();
				// 处理编号
				condition.setDealbill(dealbill);
				// 重复工单码不为空的话
			} else if (null != condition.getRecomcode()
					&& !"".equals(condition.getRecomcode().trim())) {
				// 重复工单码不为空的话
				String recomcode = condition.getRecomcode();
				// 工单查询条件
				condition = new ComplaintSearchCondition();
				// 重复工单码
				condition.setRecomcode(recomcode);
			}
		}
		// 处理状态不为空
		if (null != condition.getProstatus()
				&& !("".equals(condition.getProstatus().trim()))) {
			// 处理状态
			List<String> prostatus = new ArrayList<String>(
					Arrays.asList(condition.getProstatus().split(",")));
			condition.setProstatusList(prostatus);
			condition.setProstatus(null);
		}
		// 分页
		condition.setLimit(limit);
		// 分页
		condition.setStart(start);
		// 工单列表
		map.put(complaintListStr, complaintService.searchComplaints(condition));
		// 工单条数
		Integer count = complaintService.searchComplaintsCount(condition);
		// 工单条数
		map.put("complaintCount", count);
		// 返回工单
		return map;
	}

	/**
	 * 
	 * <p>
	 * Description:查询接入的工单<br />
	 * </p>
	 * 
	 * @author ouy
	 * @version 0.1 2012-5-18
	 * @param condition
	 * @return Map
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Map searchAccessComplaints(ComplaintSearchCondition condition,
			User user) {
		// 返回结果MAP
		Map<String, Object> map = new HashMap<String, Object>();
		// 用户id
		condition.setUserid(user.getEmpCode().getId());
		// 处理状态
		// 工单已被接入状态(工单处理)
		condition.setDealstatus(Constants.COMPLAINT_ACCESS_STATUS);
		// 工单待处理
		condition.setProstatus(Constants.COMPLAINT_STATUS_PENDING);
		// 锁定时间
		condition.setLockingtime(new Date());
		// 查询得到投诉或者异常
		List<Complaint> lockingCompList = complaintService
				.getAccessComplaints(condition);
		// 经理查询接入count
		Integer count = complaintService.getAccessComplaintsCount(condition);
		// 工单列表
		map.put(complaintListStr, lockingCompList);
		// 工单条数
		map.put("complaintCount", count);
		// 返回工单
		return map;
	}

	/**
	 * 
	 * <p>
	 * Description:通过id查询工单<br />
	 * </p>
	 * 
	 * @author ouy
	 * @version 0.1 2012-4-16
	 * @param id
	 * @return Complaint
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Complaint getComplaintById(String id) {
		// 根据ID查询工单信息
		return complaintService.getComplaintById(id);
	}

	/**
	 * 输入订单号或运单号带出客户信息
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author ouy
	 * @version 0.1 2012-4-11
	 * @param num
	 * @return Map<String,List>
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Map<String, Object> searchMembersByOWNum(String num,
			ComplaintSearchCondition condition) {
		// 返回结果MAP
		Map<String, Object> map = new HashMap<String, Object>();
		// 查询收货客户信息
		Member receiverMember = null;
		// 查询发货客户信息
		Member shipperMember = null;
		// 根据运单号查询
		Order order = null;
		// 运单信息
		WaybillInfo wbi = null;
		if (Character.isDigit(num.charAt(0))) {
			// 运单
			try {
				// ERP-14 通过运单查询收货客户编码和发货客户的编码，积分模块 WaybillInof.
				wbi = waybillOperate.queryWaybillCustomer(num + "");
				// 捕捉异常
			} catch (CrmBusinessException e) {
			}
		} else {
			// 通过订单号查询订单信息
			order = orderManager.getOrderWaybill(num);
		}
		if (null != order) {
			// 客户编码不为空
			if (null != order.getReceiverCustNumber()
					&& !"".equals(order.getReceiverCustNumber())) {
				// 通过客户编码查询客户信息
				receiverMember = memberManager.getMemberByCustNumber(order
						.getReceiverCustNumber());
			}
			// 查询发货客户信息
			if (null != order.getShipperNumber()
					&& !"".equals(order.getShipperNumber())) {
				// 通过客户编码查询客户信息
				shipperMember = memberManager.getMemberByCustNumber(order
						.getShipperNumber());
			}
			// 单号
			condition.setBill(num);
			// 收货客户
			map.put("receiverMember", receiverMember);
			// 发货客户
			map.put("shipperMember", shipperMember);
		} else if (null != wbi) {
			// 查询收货客户信息
			if (null != wbi.getReceiveCustomer()
					&& !"".equals(wbi.getReceiveCustomer())) {
				// 通过客户编码查询客户信息
				receiverMember = memberManager.getMemberByCustNumber(wbi
						.getReceiveCustomer());
			}
			// 查询发货客户信息
			if (null != wbi.getShipperCustomer()
					&& !"".equals(wbi.getShipperCustomer())) {
				// 通过客户编码查询客户信息
				shipperMember = memberManager.getMemberByCustNumber(wbi
						.getShipperCustomer());
			}
			// 单号
			condition.setBill(num);
		}
		// 收货客户
		map.put("receiverMember", receiverMember);
		// 发货客户
		map.put("shipperMember", shipperMember);
		// 结果集返回
		FossWaybillInfo fossWaybillInfo = complaintService.findWaybillByNO(num);

		String businessModel = getBusinessModel(order, fossWaybillInfo);
		map.put("businessModel", businessModel);
		return map;
	}

	private String getBusinessModel(Order order, FossWaybillInfo fossWaybillInfo) {
		// 如果运单号不为空，则输入的凭证号为运单号
		if (null != fossWaybillInfo) {
			// 如果运输类型为快递
			if (Constants.TRANS_EXPRESS.equals(fossWaybillInfo.getTranType())) {
				// 则业务模式为快递
				return Constants.BUSINESS_PMODEL_EXPRESS;
			} else if("".equals(fossWaybillInfo.getTranType())||fossWaybillInfo.getTranType()==null){
				// 零担
				return "";
			}else{
				return Constants.BUSINESS_MODEL_UNEXPRESS;
			}
			// 如果运单号不为空，则为有效的订单号
		} else if (null != order) {
			// 获得对应的运单号
			String orderNumber = order.getOrderNumber();
			// 获得第二个字母
			String sub = orderNumber.substring(1, 2);
			// 如果第二个为K
			if (sub.equals(Constants.FLAGEXPRESS)) {
				// 则为快递
				return Constants.BUSINESS_PMODEL_EXPRESS;

			} else {
				// 否则为零担
				return Constants.BUSINESS_MODEL_UNEXPRESS;
			}

		} else {

			return "";
		}
	}

	/**
	 * 
	 * <p>
	 * Description:通过电话得到联系人<br />
	 * </p>
	 * 
	 * @author ouy
	 * @version 0.1 2012-4-16
	 * @param phone
	 * @param condition
	 * @return Map<String,List>
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Map<String, Object> getContractByPhone(String phone,
			ComplaintSearchCondition condition) {
		// 结果集返回
		Map<String, Object> map = new HashMap<String, Object>();
		// 通过电话查询客户信息
		Member member = memberManager.getMemberByPhone(phone);
		if (null != member) {
			// 如果用户存在
			map.put("shipperMember", member);
		} else {
			// 通过手机查询客户信息
			List<Member> memList = memberManager.searchMemberByTel(phone);
			// 如果集合不为空
			if (null != memList && memList.size() > 0) {
				// 如果用户存在
				map.put("shipperMember", memList.get(0));
			}
		}
		// 返回member
		return map;
	}

	/**
	 * 
	 * <p>
	 * Description:得到重复工单码<br />
	 * </p>
	 * 
	 * @author ouy
	 * @version 0.1 2012-4-16
	 * @param owNum
	 * @param complaintId
	 * @param complaintType
	 * @return Map<String,String>
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Map<String, String> getRepeatedCode(String owNum,
			String complaintId, String complaintType) {
		// 结果集返回
		Map<String, String> map = new HashMap<String, String>();
		// 工单id
		map.put("complaintId", complaintId);
		// 根据ID查询工单信息
		Complaint complaint = complaintService.getComplaintById(complaintId);
		// 重复码
		String repeatedCode = "";
		// 工单校验
		ComplaintValidate cv = new ComplaintValidate();
		// 是否单号相同
		if (cv.isSameOwNumber(owNum, complaint)) {
			// 上报类型是否相同
			if (cv.isSameComplaitType(complaintType, complaint)) {
				// UNUSUAL
				if (Constants.COMPLAINT_REPORTTYE_EXCEPTION.equals(complaint
						.getReporttype().trim())) {
					// Y + 处理编号
					repeatedCode = Constants.COMPLAINT_REPEATCODE_EXCEPTION
							+ complaint.getDealbill();
					// COMPLAINT
				} else if (Constants.COMPLAINT_REPORTTYE_REPORT
						.equals(complaint.getReporttype().trim())) {
					// D + 处理编号
					repeatedCode = Constants.COMPLAINT_REPEATCODE_REPORT
							+ complaint.getDealbill();
				}
				// 重复工单码
				map.put("repeateCode", repeatedCode);
			}
		}
		// 重复工单码
		return map;
	}

	/**
	 * *
	 * <p>
	 * Description:查询当前用户待办工单<br />
	 * </p>
	 * 
	 * @author Weill
	 * @version 0.1 2012-4-12
	 * @param currentUser
	 * @return List<Complaint>
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Map<String, Object> searchWaitProccesComplaint(User currentUser,
			int start, int limit) {
		// 获取当前用户可处理的投诉状态列表,主要判断是否为投诉经理与品牌工关部
		Map<String, List<String>> status = this
				.getCurrentUserCanProccessStatus();
		// 返回结果集
		Map<String, Object> returnMap = new HashMap<String, Object>();
		List<String> commonStatus = status.get("commonStatus");
		// 获取当前用户可处理的投诉状态列表,主要判断是否为投诉经理与品牌工关部
		List<String> specialStatus = this.getSpecialStatus(currentUser);
		// status.get("specialStatus");
		List<String> feedbackStatus = status.get("feedbackStatus");
		// 员工编号
		String userCode = currentUser.getEmpCode().getEmpCode();
		// 员工id
		String userId = currentUser.getEmpCode().getId();
		// 查询当前用户待办工单
		List<Complaint> complaints = complaintService
				.searchWaitProccesComplaint(commonStatus, specialStatus,
						feedbackStatus, userCode, userId, start, limit);
		// 查询当前待办工单的总数
		Integer total = complaintService.searchWaitProccesComplaintCount(
				commonStatus, specialStatus, feedbackStatus, userCode, userId);
		// 查询待办工单返回的工单列表key
		returnMap.put(WAIT_COMPLAINT_LIST_KEY, complaints);
		// 查询待办工单返回的工单列表的总行数key
		returnMap.put(WAIT_COMPLAINT_LIST_COUNT_KEY, total);
		// 结果集返回
		return returnMap;
	}

	/**
	 * <p>
	 * Description:获取当前用户可处理的投诉状态列表,主要判断是否为投诉经理与品牌工关部<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2012-6-7
	 * @param user
	 * @return List<String>
	 */
	private List<String> getSpecialStatus(User user) {
		// 根据角色得到的可查询的特殊状态
		List<String> specialStatus = new ArrayList<String>();
		// 根据RoleID从缓存中读IRole对象
		List<IRole> roles = this.getRoleFromCache(user);
		// 功能id
		Collection<String> functionIds = null;
		// 不为空的话
		if (roles != null) {
			// 迭代角色信息
			for (IRole role : roles) {
				// 获取功能id
				functionIds = role.getFunctionIds();
				// 不为空的话
				if (null == functionIds) {
					// 继续执行下一个条件
					continue;
				}
				// 投诉升级
				if (functionIds.contains(Constants.COMPLAINT_FUNCTION_UPGRADED)) {
					// 投诉已升级权限ID
					specialStatus.add(Constants.COMPLAINT_STATUS_UPGRADED);
				}
				// 投诉待审批
				if (functionIds
						.contains(Constants.COMPLAINT_FUNCTION_WAIT_APPROVAL)) {
					// 投诉待审批
					specialStatus.add(Constants.COMPLAINT_STATUS_WAIT_APPROVAL);
				}
			}
		}
		// 结果集返回
		return specialStatus;
	}

	/**
	 * 
	 * <p>
	 * Description:获取当前用户可处理的投诉状态列表,主要判断是否为投诉经理与品牌工关部<br />
	 * </p>
	 * 
	 * @author Weill
	 * @version 0.1 2012-4-12
	 * @param user
	 * @return List<String>
	 */
	private Map<String, List<String>> getCurrentUserCanProccessStatus() {
		// 普通状态，无论什么角色都能查询的状态
		List<String> commonStatus = new ArrayList<String>();
		// 反馈状态
		List<String> feedbackStatus = new ArrayList<String>();
		// 投诉状态列表
		Map<String, List<String>> result = new HashMap<String, List<String>>();
		// 如果为空则为工单处理人查询
		// 审批退回
		commonStatus.add(Constants.COMPLAINT_STATUS_APPROVAL_RETURNED);
		// 升级退回
		/* commonStatus.add(Constants.COMPLAINT_STATUS_UPGRADED_RETURNED); */
		// 单个部门退回 完成处理
		commonStatus.add(Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_PROCESS);
		// 单个部门退回 待审批（退回层级同退回完成处理）
		commonStatus
				.add(Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_APPROVAL);
		// 单个部门退回 已升级
		commonStatus
				.add(Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_FROM_UPGRADE);
		// 所有部门退回，从处理过来的
		commonStatus
				.add(Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_PROCESS_ALL);
		// 所有部门退回，从审批过来的
		commonStatus
				.add(Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_APPROVAL_ALL);
		// 所有部门退回，从升级过来的
		commonStatus
				.add(Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_FROM_UPGRADE_ALL);
		// 工单申请延时,工单来自处理
		commonStatus.add(Constants.COMPLAINT_APPLY_DELAY_TO_PROCESS);
		// 工单申请延时,工单来自审批
		commonStatus.add(Constants.COMPLAINT_APPLY_DELAY_TO_APPROVAL);
		// 工单申请延时,工单来自升级
		commonStatus.add(Constants.COMPLAINT_APPLY_DELAY_TO_UPGRADE);
		// 反馈已解决
		feedbackStatus.add(Constants.COMPLAINT_PROCESS_STATUS_RESOVLE);
		// 反馈未解决
		feedbackStatus.add(Constants.COMPLAINT_PROCESS_STATUS_UNRESOVLE);
		// 普通状态，无论什么角色都能查询的状态
		result.put("commonStatus", commonStatus);
		// 反馈状态
		result.put("feedbackStatus", feedbackStatus);
		// 结果集返回
		return result;
	}

	@SuppressWarnings("unused")
	private boolean cleanWaitComplaint(String proStatus) {
		// 完成状态
		List<String> finishStats = new ArrayList<String>();
		// 后台已处理
		finishStats.add(Constants.COMPLAINT_STATUS_BACKGROUND);
		// 已核实
		finishStats.add(Constants.COMPLAINT_STATUS_VERIFIED);
		// 完成状态是否包含 proStatus
		if (finishStats.contains(proStatus)) {
			// 包含 proStatus 返回true
			return true;
		}
		// 不包含 proStatus 含糊false
		return false;
	}

	/**
	 * <p>
	 * Description:根据RoleID从缓存中读IRole对象<br />
	 * </p>
	 * 
	 * @author Weill
	 * @version 0.1 2012-5-8
	 * @param roleId
	 * @return IRole
	 */
	private List<IRole> getRoleFromCache(User user) {
		// 根据RoleID从缓存中读IRole对象
		ICache<String, IRole> roleCache = CacheManager.getInstance().getCache(
				IRole.class.getName());
		// 角色信息
		List<IRole> roles = new ArrayList<IRole>();
		// 迭代角色信息
		for (String roleId : user.getRoleids()) {
			// 放入集合
			roles.add(roleCache.get(roleId));
		}
		// 返回结果集
		return roles;
	}

	/**
	 * 外部接口
	 * 
	 * @Title: searchComplaintCustomersByDate
	 * @Description: TODO(根据月份，查询当月投诉客户)
	 * @param @param date "yyyy-MM"
	 * @param @return 设定文件
	 * @return List<String> 返回类型 customerCodes
	 * @throws
	 * @see <a href="package.html#section">查看流程图</a>
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public List<String> searchComplaintCustomersByDate(String date) {
		// 工单查询条件
		ComplaintSearchCondition condition = new ComplaintSearchCondition();
		// 时间参数
		condition.setMaintainDateFrom(date);
		StringBuffer dateTo = new StringBuffer(date);
		dateTo.append(" 23:59:59 ");
		condition.setMaintainDateTo(dateTo.toString());
		// 查询工单
		return complaintService.searchCustComplaints(condition);
	}

	/**
	 * @Title: searchTaskComplaints
	 * @Description: TODO(根据条件查询任务部门的投诉)
	 * @param @param condition
	 * @param @return 设定文件
	 * @return Map 返回类型
	 * @throws
	 * @see <a href="package.html#section">查看流程图</a>
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Map searchTaskComplaints(ComplaintSearchCondition condition) {
		// 结果集map
		Map<String, Object> map = new HashMap<String, Object>();
		// 查询条件加入待反馈
		List prostatusList = packageTaskProstatusList();
		// 查询条件加入已核实
		prostatusList.add(Constants.COMPLAINT_STATUS_VERIFIED);
		// 查询条件加入已到期
		prostatusList.add(Constants.COMPLAINT_PROCESS_STATUS_EXPIRED);
		// 查询条件加上已回访
		prostatusList.add(Constants.COMPLAINT_STATUS_REVISEIT);
		// 查询条件加入反馈已解决
		prostatusList.add(Constants.COMPLAINT_PROCESS_STATUS_RESOVLE);
		// 处理状态列表
		condition.setProstatusList(prostatusList);
		// 设置当前登陆用户可查询的任务部门数据权限
		User user = condition.getUser();
		// 员工id
		String deptId = user.getEmpCode().getDeptId().getId();
		// 员工id
		condition.setUserid(user.getEmpCode().getId());
		// 部门id
		condition.setDeptId(deptId);
		// 根据条件查询工单回访数据
		map.put("taskResultList",
				complaintService.searchTaskComplaints(condition));
		// 根据条件查询工单回访数据总数
		Integer count = complaintService.searchTaskComplaintsCount(condition);
		// 返回结果集
		map.put("taskResultCount", count);
		// 返回结果
		return map;
	}

	/**
	 * @Title: getRecently3ComplaintsByReportType Description:
	 *         根据上报类型查询最近优先顺序最高的三条工单
	 * @param parameterMap
	 *            ={reportType:'上报类型',sessionUser:'当前session用户'}
	 * @return List<Complaint>
	 * @throws
	 * @see <a href="package.html#section">查看流程图</a>
	 */
	// @Transactional(propagation = Propagation.REQUIRED)
	public List<Complaint> getRecently3ComplaintsByReportType(
			Map<String, Object> parameterMap) {
		// reportType由外部传入
		String reportType = (String) parameterMap.get("reportType");
		// 用户信息
		User user = (User) parameterMap.get("sessionUser");
		// 用户id
		parameterMap.put("userId", new BigDecimal(user.getEmpCode().getId()));
		// 限制条数
		parameterMap.put("limitCount", LIMITCOUNT);
		// 接入
		parameterMap.put("dealstatus", Constants.COMPLAINT_ACCESS_STATUS);
		// 工单待处理
		parameterMap.put("prostatus", Constants.COMPLAINT_STATUS_PENDING);
		// 查询时间
		parameterMap.put("requiredDate", new Date());

		// 查询未被接入或者超时的工单
		List<Complaint> lockingCompList = new ArrayList<Complaint>();
		synchronized (log) {
			// reportType:'上报类型',sessionUser:'当前session用户'
			lockingCompList = complaintService
					.getAccessComplaints(parameterMap);
			// 设置查询得到的投诉或者异常的锁定人、锁定时间、处理状态（dealstatus）
			if (lockingCompList != null) {
				for (Complaint complaint : lockingCompList) {
					// 用户id
					String userId = user.getEmpCode().getId();
					// 如果超时了，则重新锁定complaint
					if (complaint.getLockingTime() == null
							|| new Date().after(complaint.getLockingTime())) {
						// 锁定工单
						lockComplaint(complaint, userId);
						// 插入工单接入日志
						insertCompAccessLog(complaint, user);
					}
				}
			}
		}
		// 如果查询的是异常，还需要再查询是否已经有相同凭证号的投诉存在，要是存在，则需要置complaint的标志位sameBill为1
		if (Constants.COMPLAINT_REPORTTYE_EXCEPTION.equals(reportType)) {
			// 工单查询条件
			ComplaintSearchCondition condition = new ComplaintSearchCondition();
			// COMPLAINT
			condition.setReportType(Constants.COMPLAINT_REPORTTYE_REPORT);
			// 锁定工单列表
			for (Complaint exception : lockingCompList) {
				// 凭证号
				String bill = exception.getBill();
				// 凭证号 为空 或 等于0
				if (StringUtils.isEmpty(bill) || "0".equals(bill)) {
					// 凭证号为空、或为0时，无需检查 by zpj 0917
					continue;
				}
				// 凭证号
				condition.setBill(bill);
				// 取当前日期 的前一个月
				Calendar date = Calendar.getInstance();
				// 系统当前时间
				date.setTime(new Date());
				// 月份-1
				date.set(Calendar.MONTH, date.get(Calendar.MONTH) - 1);
				// 时间装换为long型
				Date reportTimeStart = date.getTime();
				// 取当前日期 的前一个月 作为查询条件
				condition.setReportTimeStart(reportTimeStart);
				// 判断凭证号重复时，调用count方法查询 by zpj 0917
				int count = complaintService.searchComplaintsCount(condition);
				// 如果count > 0
				if (count > 0) {
					exception.setSameBill(1);
				}
			}
		}
		// 结果集返回
		return lockingCompList;
	}

	/**
	 * 
	 * <p>
	 * 保存工单接入记录信息<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-8-22
	 * @param list
	 * @param user
	 *            void
	 */
	private void insertCompAccessLog(Complaint comp, User user) {
		// 结果集返回map
		Map<String, Object> map = new HashMap<String, Object>();
		long longAccessTime = 0;
		String accName = "";
		String accCode = "";
		if (comp.getFid() != null) {
			// 根据ID查询工单信息
			comp = complaintService.getComplaintById(comp.getFid().toString());
		}
		// 员工不为空的话
		if (user != null && user.getEmpCode() != null) {
			// 员工姓名
			accName = user.getEmpCode().getEmpName();
			// 运功工号
			accCode = user.getEmpCode().getEmpCode();
		}
		// 锁定时间不为空的话
		if (comp.getLockingTime() != null) {
			longAccessTime = comp.getLockingTime().getTime() - 60 * 60 * 1000;
		}
		map.put("accName", accName);
		map.put("accCode", accCode);
		map.put("complaintId", comp.getFid());
		map.put("accDate", new Date(longAccessTime));
		// 工单接入日志
		complaintService.insertCompAccessLog(map);
	}

	/**
	 * 设定接入锁定次数
	 * 
	 * @param complaint
	 * @param locker
	 */
	private Integer setNumberOfLock(Complaint complaint) {
		// 锁定次数
		Integer times = complaint.getNumberOfLock();
		// 如果次数为0
		if (times == null) {
			times = 0;
		}
		// 次数+1
		times = times + 1;
		// 锁定次数
		complaint.setNumberOfLock(times);
		// 返回结果
		return times;
	}

	/**
	 * 经理锁定工单
	 * 
	 * @param complaint
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void managerLockComplaint(Complaint complaint, User user) {
		// 用户id
		BigDecimal userId = new BigDecimal(user.getEmpCode().getId());
		// 根据ID查询工单信息
		complaint = complaintService.getComplaintById(String.valueOf(complaint
				.getFid()));
		// 如果已经被自己锁定，并且还未超时，则不需要做任何事情
		if (userId.equals(complaint.getLockingUserId())
				&& new Date().before(complaint.getLockingTime())) {
			// 返回空
			return;

		} else {
			// 否则无论是否有被别人锁定，都需要强制上锁
			lockComplaint(complaint, user.getEmpCode().getId());
			// 插入工单接入日志
			insertCompAccessLog(complaint, user);
		}
	}

	/**
	 * 锁定complaint
	 * 
	 * @param complaint
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	private void lockComplaint(Complaint complaint, String userId) {
		// 设定锁定时间
		Date lockDate = new Date();
		long lockTime = (lockDate.getTime() / 1000) + 60 * 60;
		lockDate.setTime(lockTime * 1000);
		// 锁定时间
		complaint.setLockingTime(lockDate);
		// 设定锁定人
		complaint.setLockingUserId(new BigDecimal(userId));
		// 设定处理状态
		complaint.setDealstatus(Constants.COMPLAINT_ACCESS_STATUS);
		// 更新时间
		complaint.setLastupdatetime(new Date());
		// 设定锁定次数
		setNumberOfLock(complaint);
		// 更新工单
		complaintService.updateComplaint(complaint);

	}

	/**
	 * @Title: returnReportor
	 *         <p>
	 *         Description: 退回工单上报人
	 *         </p>
	 *         void
	 */
	/* 退回上报人 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void returnReportor(Map map) {
		// 参数不为空的话
		if (null != map) {
			// 工单id
			String compId = (String) map.get("compId");
			// 退回原因
			String feedbackResaon = (String) map.get("feedbackreason");
			// 用户信息
			User user = (User) map.get("sessionUser");
			// Department department = user.getEmpCode().getDeptId();
			// 1. 待处理/审批退回/升级审批退回 -----退回上报人
			// 2. 待审批/已升级 -----使用下里面returnSubmitor() 退回投诉处理员
			Complaint complaint = complaintService.getComplaintById(compId);
			// 退回原因
			complaint.setFeedbackreason(feedbackResaon);
			// 退回人姓名
			complaint.setReturnman(user.getEmpCode().getEmpName());
			// 返回时间
			complaint.setReturntime(new Date(System.currentTimeMillis()));
			// 状态
			complaint.setProstatus(Constants.COMPLAINT_STATUS_REPORT_RETURNED);
			// 操作人id
			complaint.setOperatoerid(new BigDecimal(user.getEmpCode().getId()));
			// 操作人姓名
			complaint.setOperatoername(user.getEmpCode().getEmpName());
			// 处理状态
			complaint.setDealstatus("unlock");
			// 锁定用户id
			complaint.setLockingUserId(BigDecimal.ZERO);
			// 更新工单
			complaintService.updateComplaint(complaint);
			if (complaint.getProstatus() == null
					|| !Constants.COMPLAINT_STATUS_FRONTROUND.equals(complaint
							.getProstatus())) {
				dutyService.updateComplaint(complaint);
			}
			// 根据工单ID查询最后的工单操作记录
			WorkOrder oldWorkRecord = workOrderService
					.getLastWorkOrderRecordByComplaintId(compId);
			// 新增工单操作信息
			WorkOrder newWork = saveOrUpdateWorkOrder(complaint, oldWorkRecord,
					Constants.COMPLAINT_OPERAT_RETURN_REPORTOR, user);
			// 新增退回原因记录
			// 记录类型：退回记录
			this.saveFeedBackReason(complaint, user, feedbackResaon,
					Constants.COMPLAINT_RECORD_TYPE_RETURN,
					this.processReturnType(newWork));
			// feedBackReasion.setRecordtype(Constants.COMPLAINT_OPERAT_RETURN_REPORTOR);
			// 向待办事宜添加一条及时退回记录 justin.xu
			processRealTimeReturnComplaint(complaint);
		}
	}

	/**
	 * Description: 提交审批
	 * 
	 * @version 0.1 2012-4-18
	 * @param map
	 *            void
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void submitForApproval(Map map) {
		// 如果参数不为空的话
		if (null != map) {
			// 工单
			Complaint complaint = (Complaint) map.get("complaint");
			// 工单处理
			List<Result> results = (List<Result>) map.get("resultList");
			List<Bulletin> bulletinList = (List<Bulletin>) map
					.get("bulletinList");
			// 用户西你想
			User user = (User) map.get("sessionUser");
			// 处理限制
			String processLimit = (String) map.get("processLimit");
			// 退出限制
			String feedbackLimit = (String) map.get("feedbackLimit");
			// 工单id
			BigDecimal complaintId = complaint.getFid();
			// 工单id不为空的话
			if (null != complaintId) {
				// 校验工单当前状态是否可以处理
				if (ComplaintValidate.canSubmitApprovalComplaint(complaint)) {
					// 更新工单信息
					complaint
							.setProstatus(Constants.COMPLAINT_STATUS_WAIT_APPROVAL);
					// 员工id
					complaint.setOperatoerid(new BigDecimal(user.getEmpCode()
							.getId()));
					// 员工姓名
					complaint.setOperatoername(user.getEmpCode().getEmpName());
					// 工单处理（分配）人
					complaint.setDealmanid(Integer.parseInt(user.getEmpCode()
							.getId()));
					// 更新工单
					complaintService.updateComplaint(complaint);
					// 根据工单ID查询最后的工单操作记录
					WorkOrder oldWorkRecord = workOrderService
							.getLastWorkOrderRecordByComplaintId(complaintId
									.toString());
					// 新增工单操作信息
					saveOrUpdateWorkOrder(complaint, oldWorkRecord,
							Constants.COMPLAINT_OPERAT_SUBMIT_APPROVAL, user);
				}
				// 保存或更新工单任务部门处理结果
				processTaskResult(complaint, results, user, false);
				// bulletinList 不为空的话
				if (null != bulletinList && !bulletinList.isEmpty()) {
					// 新增通知对象
					saveBulletins(bulletinList, complaintId, user);
					// 保存短信通知
					saveCellphoneMsg(bulletinList, complaint, feedbackLimit,
							processLimit, user);
				}
			}
		}
	}

	/**
	 * 新建一个workorder
	 * 
	 * @param complaint
	 * @param actionType
	 * @param user
	 * @return
	 */
	private WorkOrder saveOrUpdateWorkOrder(Complaint complaint,
			WorkOrder oldWorkRecord, String actionType, User user) {
		// 系统当前时间
		Date currDate = new Date();
		// 操作记录
		WorkOrder workOrder = new WorkOrder();
		// 创建用户
		workOrder.setCreateUser(user.getEmpCode().getId());
		// 创建时间
		workOrder.setCreateDate(currDate);
		// 修改用户
		workOrder.setModifyUser(user.getEmpCode().getId());
		// 更新workorder的当前状态和operatorRecord
		updateWorkOrderCurStatusOper(complaint, actionType, workOrder);
		// oldWorkRecord不为空的话
		if (null != oldWorkRecord) {
			// 当前状态
			workOrder.setPreviousState(oldWorkRecord.getCurrentState());
		}
		// 工单id
		workOrder.setComplaintId(complaint.getFid());
		// 修改时间
		workOrder.setModifyDate(currDate);
		// 操作人id
		workOrder.setOperatorId(BigDecimal.valueOf(Long.parseLong(user
				.getEmpCode().getId())));
		// 操作人姓名
		workOrder.setOperatorName(user.getEmpCode().getEmpName());
		// 操作时间
		workOrder.setOperatorTime(currDate);
		// 建议
		workOrder.setSuggestion(complaint.getRecommendation());
		// 保存操作记录
		workOrderService.saveWorkOrder(workOrder);
		// 返回操作记录
		return workOrder;
	}

	/**
	 * 更新workorder的当前状态和operatorRecord
	 * 
	 * @param complaint
	 * @param actionType
	 * @param workOrder
	 * @return
	 */
	private WorkOrder updateWorkOrderCurStatusOper(Complaint complaint,
			String actionType, WorkOrder workOrder) {
		// SUBMIT_APPROVAL
		if (Constants.COMPLAINT_OPERAT_SUBMIT_APPROVAL.equals(actionType)) {
			// 提交审批
			workOrder.setCurrentState(Constants.COMPLAINT_STATUS_WAIT_APPROVAL);
			// SUBMIT_APPROVAL
			workOrder
					.setOperatorType(Constants.COMPLAINT_OPERAT_SUBMIT_APPROVAL);
			// COMPLAINT_UPGRADE
		} else if (Constants.COMPLAINT_OPERAT_COMPLAINT_UPGRADE
				.equals(actionType)) {
			// 投诉升级
			workOrder.setCurrentState(Constants.COMPLAINT_STATUS_UPGRADED);
			// COMPLAINT_UPGRADE
			workOrder
					.setOperatorType(Constants.COMPLAINT_OPERAT_COMPLAINT_UPGRADE);
		} else if (Constants.COMPLAINT_OPERAT_RETURN_REPORTOR
				.equals(actionType)) {
			// 退回上报人
			workOrder
					.setCurrentState(Constants.COMPLAINT_STATUS_REPORT_RETURNED);
			// RETURN_REPORTOR
			workOrder
					.setOperatorType(Constants.COMPLAINT_OPERAT_RETURN_REPORTOR);
		} else if (Constants.COMPLAINT_OPERAT_RETURN_SUBMITOR
				.equals(actionType)) {
			// 退回提交人
			workOrder.setCurrentState(ComplaintRule
					.getReturnSubmitorStatus(complaint));
			// "RETURN_SUBMITOR"
			workOrder
					.setOperatorType(Constants.COMPLAINT_OPERAT_RETURN_SUBMITOR);
			// 操作类型：处理提交至反馈
		} else if (Constants.COMPLAINT_OPERATE_TYPE_FROM_PROCESS
				.equals(actionType)) {
			// 完成处理
			workOrder
					.setCurrentState(Constants.COMPLAINT_STATUS_WAITE_RESPONSE);
			// 操作类型：处理提交至反馈
			workOrder
					.setOperatorType(Constants.COMPLAINT_OPERATE_TYPE_FROM_PROCESS);
			// 操作类型：审批提交至反馈
		} else if (Constants.COMPLAINT_OPERATE_TYPE_FROM_APPROVAL
				.equals(actionType)) {
			// 审批同意
			workOrder
					.setCurrentState(Constants.COMPLAINT_STATUS_WAITE_RESPONSE);
			// 操作类型：审批提交至反馈
			workOrder
					.setOperatorType(Constants.COMPLAINT_OPERATE_TYPE_FROM_APPROVAL);
		} else if (Constants.COMPLAINT_OPERATE_TYPE_FROM_UPGRADE
				.equals(actionType)) {
			// 升级同意
			workOrder
					.setCurrentState(Constants.COMPLAINT_STATUS_WAITE_RESPONSE);
			// 操作类型：升级提交至反馈
			workOrder
					.setOperatorType(Constants.COMPLAINT_OPERATE_TYPE_FROM_UPGRADE);
			// BACKGROUND_PROCESS
		} else if (Constants.COMPLAINT_OPERAT_BACKGROUND_PROCESS
				.equals(actionType)) {
			// 后台处理
			workOrder.setCurrentState(Constants.COMPLAINT_STATUS_BACKGROUND);
			// BACKGROUND_PROCESS
			workOrder
					.setOperatorType(Constants.COMPLAINT_OPERAT_BACKGROUND_PROCESS);
			// 操作类型：延时申请来源于升级
		} else if (Constants.COMPLAINT_OPERATE_TYPE_DELAY_FROM_UPGRADE
				.equals(actionType)) {
			// 升级的延时申请
			workOrder.setCurrentState(complaint.getProstatus());
			// 操作类型：延时申请来源于升级
			workOrder
					.setOperatorType(Constants.COMPLAINT_OPERATE_TYPE_DELAY_FROM_UPGRADE);
			// 操作类型：延时申请来源于完成处理
		} else if (Constants.COMPLAINT_OPERATE_TYPE_DELAY_FROM_PROCESS
				.equals(actionType)) {
			// 完成处理的延时申请
			workOrder.setCurrentState(complaint.getProstatus());
			// 操作类型：延时申请来源于完成处理
			workOrder
					.setOperatorType(Constants.COMPLAINT_OPERATE_TYPE_DELAY_FROM_PROCESS);
			// 操作类型：延时申请来源于审批
		} else if (Constants.COMPLAINT_OPERATE_TYPE_DELAY_FROM_APPROVAL
				.equals(actionType)) {
			// 审批的延时申请
			workOrder.setCurrentState(complaint.getProstatus());
			// 操作类型：延时申请来源于审批
			workOrder
					.setOperatorType(Constants.COMPLAINT_OPERATE_TYPE_DELAY_FROM_APPROVAL);
			// 操作类型：部门退回来源于审批
		} else if (Constants.COMPLAINT_OPERATE_TYPE_RETURN_FROM_APPROVAL
				.equals(actionType)) {
			// 审批的部门退回
			workOrder.setCurrentState(complaint.getProstatus());
			// 操作类型：部门退回来源于审批
			workOrder
					.setOperatorType(Constants.COMPLAINT_OPERATE_TYPE_RETURN_FROM_APPROVAL);
			// 操作类型：部门退回来源于升级
		} else if (Constants.COMPLAINT_OPERATE_TYPE_RETURN_FROM_UPGRADE
				.equals(actionType)) {
			// 升级的部门退回
			workOrder.setCurrentState(complaint.getProstatus());
			// 操作类型：部门退回来源于升级
			workOrder
					.setOperatorType(Constants.COMPLAINT_OPERATE_TYPE_RETURN_FROM_UPGRADE);
			// 操作类型：部门退回来源于完成处理
		} else if (Constants.COMPLAINT_OPERATE_TYPE_RETURN_FROM_PROCESS
				.equals(actionType)) {
			// 完成处理的部门退回
			workOrder.setCurrentState(complaint.getProstatus());
			// 操作类型：部门退回来源于完成处理
			workOrder
					.setOperatorType(Constants.COMPLAINT_OPERATE_TYPE_RETURN_FROM_PROCESS);
		}
		// 返回操作记录
		return workOrder;
	}

	/* Refactor的私有方法 */
	private void saveTaskDepResults(Complaint complaint, List<Result> results,
			BigDecimal complaintId, User user) {
		// 处理数据
		Result result = null;
		// 处理数据长度不为0的话
		for (int i = 0; i < results.size(); i++) {
			// 迭代
			result = results.get(i);
			// 用户id
			result.setComplainid(complaintId);
			// 创建人id
			result.setCreateuserid(Integer.valueOf(user.getEmpCode().getId()));
			// 处理人工号
			result.setDealman(user.getEmpCode().getEmpCode());
			// 最后修改人工号
			result.setLastmodifyuserid(BigDecimal.valueOf(Long.parseLong(user
					.getEmpCode().getId())));
		}
		// 保存任务部门处理数据
		resultService.saveTaskDepartmentResult(results);
	}

	/* Refactor的私有方法 */
	private void saveBulletins(List<Bulletin> bulletinList,
			BigDecimal complaintId, User user) {
		// 通知对象列表为空，就返回空
		if (null == bulletinList || bulletinList.isEmpty()) {
			return;
		}
		// 通知对象
		Bulletin bulletin = null;
		// 通知对象长度不为0的话
		for (int i = 0; i < bulletinList.size(); i++) {
			// 迭代
			bulletin = bulletinList.get(i);
			// 用户id
			bulletin.setCompid(complaintId);
			// 创建人id
			bulletin.setCreateuserid(Integer
					.parseInt(user.getEmpCode().getId()));
			// 最后修改人工号
			bulletin.setLastmodifyuserid(BigDecimal.valueOf(Long.parseLong(user
					.getEmpCode().getId())));
		}
		// 保存通知对象
		bulletinService.saveBulletin(bulletinList);
	}

	// 删除原来的通知对象
	private void deleteOldBulletinsByCompId(BigDecimal complaintId) {
		// 根据工单Id删除原来的通知对象
		bulletinService.deleteOldBulletinsByCompId(complaintId);
	}

	/* 投诉升级 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void upgradeComplaint(Map map) {
		if (null != map) {
			// 工单
			Complaint complaint = (Complaint) map.get("complaint");
			// 用户
			User user = (User) map.get("sessionUser");
			// 工单id
			BigDecimal complaintId = complaint.getFid();
			// 工单id不为空的话
			if (null != complaintId) {
				// 校验工单当前状态是否允许升级
				if (ComplaintValidate.canSubmitApprovalComplaint(complaint)) {
					// 投诉已升级
					complaint.setProstatus(Constants.COMPLAINT_STATUS_UPGRADED);
					// 操作人id
					complaint.setOperatoerid(new BigDecimal(user.getEmpCode()
							.getId()));
					// 操作人姓名
					complaint.setOperatoername(user.getEmpCode().getEmpName());
					// 处理人id
					complaint.setDealmanid(Integer.parseInt(user.getEmpCode()
							.getId()));
					// 更新工单信息
					complaintService.updateComplaint(complaint);
					// 根据工单ID查询最后的工单操作记录
					WorkOrder oldWorkRecord = workOrderService
							.getLastWorkOrderRecordByComplaintId(complaintId
									.toString());
					// 新增工单操作信息
					saveOrUpdateWorkOrder(complaint, oldWorkRecord,
							Constants.COMPLAINT_OPERAT_COMPLAINT_UPGRADE, user);
				}
			}
		}
	}

	/* 工单处理--完成处理 */
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean finishedComplaintProccess(Map map) {
		if (null != map) {
			// 工单
			Complaint complaint = (Complaint) map.get("complaint");
			// 结果列表
			List<Result> results = (List) map.get("resultList");
			// 通知对象
			List<Bulletin> bulletinList = (List<Bulletin>) map
					.get("bulletinList");
			// 退回限制
			String feedbackLimit = (String) map.get("feedbackLimit");
			// 处理限制
			String processLimit = (String) map.get("processLimit");
			// 获取用户信息
			User user = (User) map.get("sessionUser");
			// 完成处理时，不修改锁定时间，锁定人
			complaint.setLockingTime(null);
			// 锁定用户id
			complaint.setLockingUserId(null);
			// 操作人id
			complaint.setOperatoerid(new BigDecimal(user.getEmpCode().getId()));
			// 操作用户姓名
			complaint.setOperatoername(user.getEmpCode().getEmpName());
			/**
			 * 调用oa接口
			 */
			if (null != results && results.size() > 0) {
				// 调用OA接口进行消息通知
				oaInterface(results);
			}
			// 根据工单ID查询最后的工单操作记录
			WorkOrder oldWorkRecord = workOrderService
					.getLastWorkOrderRecordByComplaintId(complaint.getFid()
							.toString());
			// 检查当前工单是否勾选最终反馈
			if (!ComplaintValidate.isFinalFeedback(complaint)) {
				// 如果没有选择最终反馈
				complaint
						.setDealstatus(Constants.COMPLAINT_RESOVLE_STATUS_UNRESOVLE);
				// 处理任务部门处理数据
				this.processTaskResult(complaint, results, user, false);
				// 新增通知对象
				saveBulletins(bulletinList, complaint.getFid(), user);
				// 保存通知短信
				saveCellphoneMsg(bulletinList, complaint, feedbackLimit,
						processLimit, user);
				// 新建一个workorder
				saveOrUpdateWorkOrder(complaint, oldWorkRecord,
						Constants.COMPLAINT_OPERATE_TYPE_FROM_PROCESS, user);
				// 待反馈
				complaint
						.setProstatus(Constants.COMPLAINT_STATUS_WAITE_RESPONSE);
			} else {
				// 后台已处理
				complaint.setProstatus(Constants.COMPLAINT_STATUS_BACKGROUND);
				// 已解决
				complaint
						.setDealstatus(Constants.COMPLAINT_RESOVLE_STATUS_RESOVLE);
				// 记录类型：反馈记录
				saveFeedBackReason(complaint, user,
						complaint.getFeedbackrecode(),
						Constants.COMPLAINT_RECORD_TYPE_FEEDBACK);
				// 新增记录
				saveOrUpdateWorkOrder(complaint, oldWorkRecord,
						Constants.COMPLAINT_OPERAT_BACKGROUND_PROCESS, user);
			}
			// 处理人id
			complaint.setDealmanid(Integer.parseInt(user.getEmpCode().getId()));
			// 更新工单
			complaintService.updateComplaint(complaint);
			// Result result = (Result) map.get("resultList");

			// 返回true
			return true;
		}
		// 返回false
		return false;
	}

	/**
	 * <p>
	 * Description: 调用OA接口进行消息通知 <br />
	 * </p>
	 * 
	 * @author ZhuPJ
	 * @version 0.1 2012-6-28
	 * @param results
	 *            void
	 */
	private void oaInterface(List<Result> results) {
		if (null != results && results.size() > 0) {
			// 工单信息列表
			List<ComplaintInfo> complaintInfoList = new ArrayList<ComplaintInfo>();
			for (Result re : results) {
				if (null != re.getTaskproperties()
						&& ("feedback_people".equals(re.getTaskproperties())
								|| "duty_people".equals(re.getTaskproperties()) || "duty_feedback_people"
									.equals(re.getTaskproperties()))) {
					// 所属个人
					if (Constants.COMPLAINT_PROCESSTYPE_EMPLOYEE.equals(re
							.getDealType())) {
						// 工单信息
						ComplaintInfo complaintInfo = new ComplaintInfo();
						// 根据用户ID获取工号
						// User u =
						// userService.getUserById(re.getPersonUserId());
						if (null != re.getTaskpartmentid()) {
							Employee e = employeeService.getEmpById(re
									.getTaskpartmentid().toString());
							// 用户工号
							complaintInfo.setEmpCode(e.getEmpCode());
						}
						// User u =
						// userService.getUserById(re.getTaskpartmentid().toString());
						// // 用户工号
						// complaintInfo.setEmpCode(e.getEmpCode());
						complaintInfo.setComplaintCount(1);
						complaintInfo.setIsSend("0");
						// 失败次数
						complaintInfo.setFailureCount(0);
						// 创建时间
						complaintInfo.setCreatetime(new Date());
						complaintInfoList.add(complaintInfo);
					} else {
						// 部门
						Department dep = new Department();
						dep.setId(re.getTaskpartmentid() + "");
						// 查找部门下所有员工
						List<Employee> empList = employeeService
								.getAllEmployeesByDeptId(dep);
						for (Employee employee : empList) {
							// 工单
							ComplaintInfo complaintInfo = new ComplaintInfo();
							// 员工工号
							complaintInfo.setEmpCode(employee.getEmpCode());
							complaintInfo.setComplaintCount(1);
							complaintInfo.setIsSend("0");
							// 失败次数
							complaintInfo.setFailureCount(0);
							// 创建时间
							complaintInfo.setCreatetime(new Date());
							// 工单列表
							complaintInfoList.add(complaintInfo);
						}
					}
				}
			}
			// 如果工单列表信息不为空的话
			if (null != complaintInfoList && complaintInfoList.size() > 0) {
				// 调用保存方法
				complaintInfoService.insertComplaintInfoList(complaintInfoList);
			}
		}
	}

	/* Refactor的私有方法 */
	private void saveFeedBackReason(Complaint complaint, User user,
			String feedback, String recordType) {
		// 此方法用于补充退回类型，增加subType类型
		this.saveFeedBackReason(complaint, user, feedback, recordType, "");
	}

	/**
	 * <p>
	 * Description: 此方法用于补充退回类型，增加subType类型<br />
	 * </p>
	 * 
	 * @author ZhuPJ
	 * @version 0.1 2012-7-16
	 * @param complaint
	 * @param user
	 * @param feedback
	 * @param recordType
	 * @param subtype
	 *            void
	 */
	private void saveFeedBackReason(Complaint complaint, User user,
			String feedback, String recordType, String subtype) {
		// 系统当前时间
		Date currDate = new Date(System.currentTimeMillis());
		// 退回原因/反馈记录
		FeedBackReasion feedBackRea = new FeedBackReasion();
		// 工单id
		feedBackRea.setComplaintid(complaint.getFid());
		feedBackRea.setWailbillcontent(feedback);
		// 运单号
		feedBackRea.setWailbillnunber(complaint.getBill());
		feedBackRea.setSubtype(subtype);
		// 记录类型
		feedBackRea.setRecordtype(recordType);
		// 加载记录时间
		feedBackRea.setRecordtime(currDate);
		// 创建用户
		feedBackRea.setCreateUser(user.getEmpCode().getId());
		// 修改用户
		feedBackRea.setModifyUser(user.getEmpCode().getId());
		// 创建时间
		feedBackRea.setCreateDate(currDate);
		// 修改时间
		feedBackRea.setModifyDate(currDate);
		// 记录人
		feedBackRea.setRecordman(user.getEmpCode().getEmpCode());
		// 记录人部门id
		feedBackRea.setRecordpartermentid(BigDecimal.valueOf(Long
				.parseLong(user.getEmpCode().getDeptId().getId())));
		// 记录人部门名字
		feedBackRea.setRecordpartmentname(user.getEmpCode().getDeptId()
				.getDeptName());
		// 反馈退回记录对象
		feedBackReasionService.saveFeedBackReasion(feedBackRea);
	}

	/**
	 * <p>
	 * Description: 根据WorkOrder状态设置退回子类型<br />
	 * </p>
	 * 
	 * @author ZhuPJ
	 * @version 0.1 2012-7-16
	 * @param workStatus
	 * @return String
	 */
	private String processReturnType(WorkOrder workStatus) {
		String code = "";
		// 投诉审批退回
		if (workStatus.getCurrentState().equals(
				Constants.COMPLAINT_STATUS_APPROVAL_RETURNED)) {
			// 投诉审批退回
			code = "_APPROVAL";
			// 投诉升级退回
		} else if (workStatus.getCurrentState().equals(
				Constants.COMPLAINT_STATUS_UPGRADED_RETURNED)) {
			// 投诉升级退回
			code = "_UPGRADED";
			// 退回上报人
		} else if (workStatus.getCurrentState().equals(
				Constants.COMPLAINT_OPERAT_RETURN_REPORTOR)) {
			// 退回上报人
			code = "_REPORTOR";
		}
		// 类型返回
		return code;
	}

	// 审批和投诉升级（同意）的完成处理
	// 审批退回完成处理、升级退回完成处理、普通工单完成处理
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean finishedComplaintProccess(Complaint complaint,
			List<Result> addResults, List<Result> delResult,
			List<Bulletin> bulletinList, User user) {
		// 根据ID查询工单信息
		Complaint dbComplaint = this.complaintService
				.getComplaintById(complaint.getFid() + "");
		// 检查工单是否可进行完成处理操作
		if (ComplaintValidate.canFinishProccessComplaint(dbComplaint)) {
			// 根据工单ID查询最后的工单操作记录
			WorkOrder oldWorkRecord = workOrderService
					.getLastWorkOrderRecordByComplaintId(complaint.getFid()
							.toString());
			String actionType = "";
			// 如果没有选择最终反馈
			if (!ComplaintValidate.isFinalFeedback(complaint)) {
				// 审批同意的完成处理 无需保存部门处理结果和通知对象
				if (!(ComplaintValidate.isWaitApproval(dbComplaint))) {
					// 调oa接口
					oaInterface(addResults);
					// 处理任务部门处理数据
					processTaskResult(dbComplaint, addResults, user, true);
					// 新增通知对象
					saveBulletins(bulletinList, dbComplaint.getFid(), user);
					// 删除原来的通知对象
					this.deleteOldBulletinsByCompId(dbComplaint.getFid());
					if (addResults != null && !addResults.isEmpty()) {
						for (Result r : addResults) {
							// 发送短信通知
							String processLimit = null;
							// 退回限制
							String feedbacklimit = null;
							processLimit = r.getProcesstimelimit().toString();
							feedbacklimit = r.getFeedtimelimit().toString();
							// 保存需要发送的短信通知到本地数据库
							saveCellphoneMsg(bulletinList, dbComplaint,
									feedbacklimit, processLimit, user);
						}
					}
				}
				// 工单待处理
				if (Constants.COMPLAINT_STATUS_PENDING.equals(dbComplaint
						.getProstatus())
				// 部分部门退回，从处理过来的
						|| Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_PROCESS
								.equals(dbComplaint.getProstatus())
						// 部分部门退回，从审批过来的
						|| Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_APPROVAL
								.equals(dbComplaint.getProstatus())
						// 部分部门退回，从升级过来的
						|| Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_FROM_UPGRADE
								.equals(dbComplaint.getProstatus())
						// 所有部门退回，从处理过来的
						|| Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_PROCESS_ALL
								.equals(dbComplaint.getProstatus())
						// 所有部门退回，从审批过来的
						|| Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_APPROVAL_ALL
								.equals(dbComplaint.getProstatus())
						// 所有部门退回，从升级过来的
						|| Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_FROM_UPGRADE_ALL
								.equals(dbComplaint.getProstatus())) {
					// 操作类型：处理提交至反馈
					actionType = Constants.COMPLAINT_OPERATE_TYPE_FROM_PROCESS;
				} else if (
				// 投诉待审批
				Constants.COMPLAINT_STATUS_WAIT_APPROVAL.equals(dbComplaint
						.getProstatus())
				// 投诉审批退回
						|| Constants.COMPLAINT_STATUS_APPROVAL_RETURNED
								.equals(dbComplaint.getProstatus())) {
					// 操作类型：审批提交至反馈
					actionType = Constants.COMPLAINT_OPERATE_TYPE_FROM_APPROVAL;
				} else if (
				// 投诉已升级
				Constants.COMPLAINT_STATUS_UPGRADED.equals(dbComplaint
						.getProstatus())
				// 投诉升级退回
						|| Constants.COMPLAINT_STATUS_UPGRADED_RETURNED
								.equals(dbComplaint.getProstatus())) {
					// 操作类型：升级提交至反馈
					actionType = Constants.COMPLAINT_OPERATE_TYPE_FROM_UPGRADE;
					// 保存记录升级同意的处理人ID
					complaint.setDealmanid(new Integer(user.getEmpCode()
							.getId()));
				}
			} else {
				// 后台处理
				actionType = Constants.COMPLAINT_OPERAT_BACKGROUND_PROCESS;
				// 解决情况已解决
				complaint
						.setDealstatus(Constants.COMPLAINT_RESOVLE_STATUS_RESOVLE);
			}
			// 操作人id
			complaint.setOperatoerid(new BigDecimal(user.getEmpCode().getId()));
			// 操作人姓名
			complaint.setOperatoername(user.getEmpCode().getEmpName());
			// 是最终反馈
			if (Constants.IF_FEED_BACK_YES.equals(complaint.getFeedback())) {
				// 后台已处理
				complaint.setProstatus(Constants.COMPLAINT_STATUS_BACKGROUND);
			} else {
				// 多任务部门情况下需要根据各部门的退回和延时情况，设置工单状态
				setComplaintProStatus(complaint);
			}
			// 新增工单操作信息
			saveOrUpdateWorkOrder(complaint, oldWorkRecord, actionType, user);
			// 更新工单信息
			complaintService.updateComplaint(complaint);
			// 保存反馈结果
			// 检查当前工单状态是否为待审批状态
			// 检查当前工单状态是否为已升级状态
			if (!(ComplaintValidate.isWaitApproval(dbComplaint)
					|| ComplaintValidate.isUpgrade(dbComplaint)
			// 投诉升级退回
			|| Constants.COMPLAINT_STATUS_UPGRADED_RETURNED.equals(dbComplaint
					.getProstatus()))
					|| ComplaintValidate.isFinalFeedback(complaint)) {
				// 记录类型：反馈记录
				saveFeedBackReason(complaint, user,
						complaint.getFeedbackrecode(),
						Constants.COMPLAINT_RECORD_TYPE_FEEDBACK);
			}
		}
		return true;
	}

	/**
	 * <p>
	 * Description:多任务部门情况下需要根据各部门的退回和延时情况，设置工单状态<br />
	 * </p>
	 * 
	 * @author admin
	 * @version 0.1 2012-5-28
	 * @param complaint
	 *            void
	 */
	private void setComplaintProStatus(Complaint complaint) {
		// 处理结果表查询条件
		ResultSearchCondition resultCondition = new ResultSearchCondition();
		// 工单id
		resultCondition.setComplainid(complaint.getFid());
		// 根据条件查询
		List<Result> resultList = resultService
				.getResultByCondition(resultCondition);
		// 判断是否有部门延时
		boolean hasDelay = false;
		// 判断是否有部门退回
		boolean hasReturn = false;
		for (Result result : resultList) {
			// 判断是否仍然有部门申请延时
			if (Constants.IF_DELAY_APPLICATION_EFFECTIVE_NO.equals(result
					.getDelay())) {
				hasDelay = true;
			}
			// 判断是否仍然有部门退回
			if (Constants.COMPLAINT_TASK_STATUS_RETURNED.equals(result
					.getStat()) // &&
								// !Constants.COMPLAINT_TASK_IS_BACKED.equals(result.getIsBack())
			) {
				hasReturn = true;
			}
		}
		// 根据工单ID查询最后的工单操作记录
		WorkOrder workOrder = workOrderService
				.getLastWorkOrderRecordByComplaintId(complaint.getFid()
						.toString());
		// 操作类型
		String actionType = workOrder.getOperatorType();
		// 有部门延时没有部门退回
		if (hasDelay && !hasReturn) {
			// 工单来源于普通完成处理
			if (verifyFromProcess(actionType)) {
				// 工单申请延时,工单来自处理
				complaint
						.setProstatus(Constants.COMPLAINT_APPLY_DELAY_TO_PROCESS);
				// 工单来源于经理审批
			} else if (verifyFromApproval(actionType)) {
				// 工单申请延时,工单来自审批
				complaint
						.setProstatus(Constants.COMPLAINT_APPLY_DELAY_TO_APPROVAL);
				// 工单来源于升级
			} else if (verifyFromUpgrade(actionType)) {
				// 工单申请延时,工单来自升级
				complaint
						.setProstatus(Constants.COMPLAINT_APPLY_DELAY_TO_UPGRADE);
			}
		}
		// 有部门退回没有部门延时
		if (hasReturn && !hasDelay) {
			// 工单来源于普通完成处理
			if (verifyFromProcess(actionType)) {
				// 部分部门退回，从处理过来的
				complaint
						.setProstatus(Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_PROCESS);
				// 工单来源于经理审批
			} else if (verifyFromApproval(actionType)) {
				// 部分部门退回，从审批过来的
				complaint
						.setProstatus(Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_APPROVAL);
				// 工单来源于升级
			} else if (verifyFromUpgrade(actionType)) {
				// 部分部门退回，从升级过来的
				complaint
						.setProstatus(Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_FROM_UPGRADE);
			}
		}
		// 既没有部门退回也没有部门延时
		if (!hasDelay && !hasReturn) {
			if (!Constants.COMPLAINT_PROCESS_STATUS_UNRESOVLE.equals(complaint
					.getProstatus())
			// 反馈已解决
					&& !Constants.COMPLAINT_PROCESS_STATUS_RESOVLE
							.equals(complaint.getProstatus())) {
				// 待反馈
				complaint
						.setProstatus(Constants.COMPLAINT_STATUS_WAITE_RESPONSE);
			}
		}
	}

	/**
	 * 工单来源于普通完成处理<br />
	 * 
	 * @return boolean
	 */
	private boolean verifyFromProcess(String actionType) {
		// 操作类型：延时申请来源于完成处理
		// 操作类型：部门退回来源于完成处理
		// 操作类型：处理提交至反馈
		return Constants.COMPLAINT_OPERATE_TYPE_DELAY_FROM_PROCESS
				.equals(actionType)
				|| Constants.COMPLAINT_OPERATE_TYPE_RETURN_FROM_PROCESS
						.equals(actionType)
				|| Constants.COMPLAINT_OPERATE_TYPE_FROM_PROCESS
						.equals(actionType);
	}

	/**
	 * 工单来源于经理审批
	 * 
	 * @return boolean
	 */
	private boolean verifyFromApproval(String actionType) {
		// 操作类型：延时申请来源于审批
		// 操作类型：部门退回来源于审批
		// 操作类型：审批提交至反馈
		return Constants.COMPLAINT_OPERATE_TYPE_DELAY_FROM_APPROVAL
				.equals(actionType)
				|| Constants.COMPLAINT_OPERATE_TYPE_RETURN_FROM_APPROVAL
						.equals(actionType)
				|| Constants.COMPLAINT_OPERATE_TYPE_FROM_APPROVAL
						.equals(actionType);
	}

	/**
	 * 工单来源于升级
	 * 
	 * @return boolean
	 */
	private boolean verifyFromUpgrade(String actionType) {
		// 操作类型：延时申请来源于升级
		// 操作类型：部门退回来源于升级
		// 操作类型：升级提交至反馈
		return Constants.COMPLAINT_OPERATE_TYPE_DELAY_FROM_UPGRADE
				.equals(actionType)
				|| Constants.COMPLAINT_OPERATE_TYPE_RETURN_FROM_UPGRADE
						.equals(actionType)
				|| Constants.COMPLAINT_OPERATE_TYPE_FROM_UPGRADE
						.equals(actionType);
	}

	/**
	 * <p>
	 * Description: 更新工单任务部门处理数据（增加、删除、更新）<br />
	 * </p>
	 * 
	 * @author admin
	 * @version 0.1 2012-4-28
	 * @param complaint
	 * @param results
	 * @param user
	 *            void
	 */
	private void processTaskResult(Complaint complaint, List<Result> results,
			User user, boolean isfromWaitComplaint) {
		// 旧的result集合
		List<Result> oldResults = new ArrayList<Result>();
		// 旧的部门id集合
		Map<BigDecimal, Result> oldDepartIdMap = new HashMap<BigDecimal, Result>();
		if (null != results) {
			// 处理结果表查询条件
			ResultSearchCondition condition = new ResultSearchCondition();
			// 工单id
			condition.setComplainid(complaint.getFid());
			if (isfromWaitComplaint) {
				// 查找出该complaint中状态为已退回的Result
				condition.setStat(Constants.COMPLAINT_TASK_STATUS_RETURNED);
			}
			oldResults = resultService.getResultByCondition(condition);
			// 装填旧的部门ID到集合中
			for (Result old : oldResults) {
				oldDepartIdMap.put(old.getTaskpartmentid(), old);
			}
		}
		List<Result> updateResults = new ArrayList<Result>();
		List<Result> addResults = new ArrayList<Result>();

		// 过滤出新增的任务处理结果
		List<Result> tempAddList = new ArrayList<Result>();
		if (null != results && results.size() > 0) {
			for (Result currResult : results) {
				// 新增数据没有fid主键
				if (currResult.getFid() == null
						|| "0".equals(currResult.getFid().toString().trim())) {
					BigDecimal curDeptId = currResult.getTaskpartmentid();
					String curDealType = currResult.getDealType();
					// 判断该条result是否是新的(即判断该result的部门是否已经有对应的已分配的result)
					boolean isNewResult = true;
					Result oldResult = null;
					if (oldDepartIdMap.containsKey(curDeptId)) {
						oldResult = oldDepartIdMap.get(currResult
								.getTaskpartmentid());
						if (curDealType.equals(oldResult.getDealType())) {
							isNewResult = false;
						}
					}

					// 判断这个新增的部门result是否已经存在
					if (isNewResult) {
						// 如果不存在，则作为新增的加入列表
						addResults.add(currResult);
						// temp delete by zhouyuan
						// tempAddList.add(currResult);
					} else {
						// 如果该部门已经存在,则把id等信息保存到新的result中，用来让系统做update
						currResult.setFid(oldResult.getFid());
						currResult.setIsBack(oldResult.getIsBack());
						/*
						 * oldResult.setProcesstimelimit(currResult.
						 * getProcesstimelimit());
						 * oldResult.setFeedtimelimit(currResult
						 * .getFeedtimelimit());
						 * oldResult.setDealmatters(currResult
						 * .getDealmatters());
						 * oldResult.setDealType(currResult.getDealType());
						 * oldResult
						 * .setTaskproperties(currResult.getTaskproperties());
						 * oldResult.setIfovertime(null);
						 * results.remove(currResult); results.add(oldResult);
						 */

					}
				}
			}
		}
		// results只剩下修改过的原有数据
		results.removeAll(tempAddList);
		List<Result> tempResults = new ArrayList<Result>();
		if (null != oldResults && oldResults.size() > 0) {
			for (Result currResult : results) {
				for (Result oldResult : oldResults) {
					if (currResult.getFid().equals(oldResult.getFid())) {
						// 数据库现有保存的数据列表去掉还保留的原有数据,剩下需删除数据
						/* oldResults.remove(oldResult); */
						tempResults.add(oldResult);
						// 需更新的原有数据
						updateResults.add(currResult);
						break;
					}
				}
			}
			// 数据库中已有的数据列表去掉当前仍然保留的数据,剩下需删除的数据
			oldResults.removeAll(tempResults);
		}

		// 任务部门处理结果新增保存
		if (addResults != null) {
			saveTaskDepResults(complaint, addResults, complaint.getFid(), user);
		}
		// 任务部门处理结果更新
		if (null != updateResults && updateResults.size() > 0) {
			// 迭代
			for (Result result : updateResults) {
				result.setStat(null);
				// 通过id查询
				Result old = this.resultService.getResultById(result.getFid());
				if (null != old
						&& !(old.getTaskpartmentid().equals(BigDecimal.ZERO))
						&& (!old.getTaskpartmentid().equals(
								result.getTaskpartmentid()))) {
					result.setIsBack("clear");
				}
				// 根据主键ID更新处理结果
				resultService.updateResult(result);

			}
		}
		// 任务部门处理结果删除
		if (null != oldResults && oldResults.size() > 0) {
			// 迭代
			for (Result result : oldResults) {
				// 根据主键ID删除处理结果
				resultService.deleteResult(result);
			}
		}
	}

	// 待办退回提交人
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean returnSubmitor(Complaint complaint, User user,
			String feedbackReason) {
		// 根据ID查询工单信息
		Complaint oldComplaint = complaintService.getComplaintById(complaint
				.getFid() + "");
		// 根据工单ID查询最后的工单操作记录
		WorkOrder oldWorkRecord = workOrderService
				.getLastWorkOrderRecordByComplaintId(oldComplaint.getFid()
						.toString());
		// 新增工单操作信息
		WorkOrder newWorkord = saveOrUpdateWorkOrder(complaint, oldWorkRecord,
				Constants.COMPLAINT_OPERAT_RETURN_SUBMITOR, user);
		/*
		 * Description:获取退回提交人操作之后，工单状态，有两种： 1)审批退回;2)升级退回
		 */
		complaint.setProstatus(ComplaintRule
				.getReturnSubmitorStatus(oldComplaint));
		// 操作人id
		complaint.setOperatoerid(new BigDecimal(user.getEmpCode().getId()));
		// 操作人姓名
		complaint.setOperatoername(user.getEmpCode().getEmpName());
		// 更新工单信息
		complaintService.updateComplaint(complaint);
		if (complaint.getProstatus() == null
				|| !Constants.COMPLAINT_STATUS_FRONTROUND.equals(complaint
						.getProstatus())) {
			dutyService.updateComplaint(complaint);
		}
		// 此方法用于补充退回类型，增加subType类型
		// 记录类型：退回记录
		saveFeedBackReason(complaint, user, feedbackReason,
				Constants.COMPLAINT_RECORD_TYPE_RETURN,
				this.processReturnType(newWorkord));
		// 返回true
		return true;
	}

	/**
	 * @Title: returnToProcess
	 * @Description: (任务部门退回处理人)
	 * @param @param complaintId,resultId,feedbackContent,user 设定文件
	 *        （工单ID，处理结果ID，反馈结果，当前用户）
	 * @return void 返回类型
	 * @throws
	 * @see <a href="package.html#section">查看流程图</a>
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void returnToProcess(String complaintId, String resultId,
			String feedbackContent, User user) {
		// 根据ID查询工单信息
		Complaint complaint = complaintService.getComplaintById(complaintId);
		BigDecimal bResultId = new BigDecimal(resultId);
		Result result = resultService.getResultById(bResultId);
		// 通过工单id查处理结果
		List<Result> resultList = resultService
				.searchResultById(new BigDecimal(complaintId));
		// 工单验证
		ComplaintValidate cv = new ComplaintValidate();
		// 工单是否可以退回（反馈过的工单不能退回）
		if (cv.isExistComplaint(complaint) && cv.isExistResult(result)
				&& ComplaintValidate.canComplainStatuCanReturn(complaint)) {
			// 判断是否存在工单
			// 判断是否存在任务部门处理结果
			// 判断是否可退回工单
			List<WorkOrder> workOrderList = workOrderService
					.getWorkOrderByComplaintId(new BigDecimal(complaintId));
			// 取得最近一次的操作记录
			if (resultList != null && resultList.size() > 0
					&& workOrderList != null && workOrderList.size() > 0) {
				String actionType = "";
				WorkOrder workOrder = workOrderList.get(0);
				if (cv.isAllTaskReturned(resultList, bResultId)) {
					// 所有任务部门都已退回工单，可以更新工单状态和任务部门处理状态
					// 根据工单操作记录判断工单是从（审批，升级，处理）哪个节点流入反馈的
					if (workOrder.getOperatorType().equals(
							Constants.COMPLAINT_OPERATE_TYPE_FROM_PROCESS)) {
						// 如果是从审批或处理流入反馈的工单，则退回后统一流入处理节点
						// 更新工单状态为单个部门退回，从处理过来的
						complaint
								.setProstatus(Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_PROCESS_ALL);
						actionType = Constants.COMPLAINT_OPERATE_TYPE_RETURN_FROM_PROCESS;
					} else if (workOrder.getOperatorType().equals(
							Constants.COMPLAINT_OPERATE_TYPE_FROM_APPROVAL)) {
						// 更新工单状态为单个部门退回，从审批过来的
						complaint
								.setProstatus(Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_APPROVAL_ALL);
						actionType = Constants.COMPLAINT_OPERATE_TYPE_RETURN_FROM_APPROVAL;
					} else {
						// 如果从升级流入反馈的工单，则退回后流入升级节点
						// 更新工单状态为单个部门退回，从升级过来的
						complaint
								.setProstatus(Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_FROM_UPGRADE_ALL);
						actionType = Constants.COMPLAINT_OPERATE_TYPE_RETURN_FROM_UPGRADE;
					}
				} else {// 部分退回
						// 根据工单操作记录判断工单是从（审批，升级，处理）哪个节点流入反馈的

					if (workOrder.getOperatorType().equals(
							Constants.COMPLAINT_OPERATE_TYPE_FROM_PROCESS)) {
						// 如果是从审批或处理流入反馈的工单，则退回后统一流入处理节点
						// 更新工单状态为多个部门退回，从处理过来的
						complaint
								.setProstatus(Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_PROCESS);
						actionType = Constants.COMPLAINT_OPERATE_TYPE_RETURN_FROM_PROCESS;
					} else if (workOrder.getOperatorType().equals(
							Constants.COMPLAINT_OPERATE_TYPE_FROM_APPROVAL)) {
						// 更新工单状态为多个部门退回，从审批过来的
						complaint
								.setProstatus(Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_APPROVAL);
						actionType = Constants.COMPLAINT_OPERATE_TYPE_RETURN_FROM_APPROVAL;
					} else {
						// 如果从升级流入反馈的工单，则退回后流入升级节点
						// 更新工单状态为多个部门退回，从升级过来的
						complaint
								.setProstatus(Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_FROM_UPGRADE);
						actionType = Constants.COMPLAINT_OPERATE_TYPE_RETURN_FROM_UPGRADE;
					}
					// 不是所有部门都已退回工单，不可更新工单状态，只能更新任务部门处理状态
				}
				// 更新任务部门处理状态为已退回
				result.setStat(Constants.COMPLAINT_TASK_STATUS_RETURNED);
				// 设置任务部门已退回过处理人状态
				result.setIsBack(Constants.COMPLAINT_TASK_IS_BACKED);

				// 生成反馈记录实体
				FeedBackReasion feedback = new FeedBackReasion();
				// 工单id
				feedback.setComplaintid(new BigDecimal(complaintId));
				// 运单号
				feedback.setWailbillnunber(complaint.getBill());
				feedback.setWailbillcontent(feedbackContent);
				// 记录人
				feedback.setRecordman(user.getEmpCode().getEmpCode());
				// 记录部门id
				feedback.setRecordpartermentid(new BigDecimal(user.getEmpCode()
						.getDeptId().getId()));
				// 记录部门名字
				feedback.setRecordpartmentname(user.getEmpCode().getDeptId()
						.getDeptName());
				// 记录时间
				feedback.setRecordtime(new Date());
				// 记录类型
				feedback.setRecordtype(Constants.COMPLAINT_RECORD_TYPE_RETURN);
				// 创建时间
				feedback.setCreateDate(new Date());
				// 创建用户
				feedback.setCreateUser(user.getEmpCode().getEmpCode());
				// 修改时间
				feedback.setModifyDate(new Date());
				// 修改用户
				feedback.setModifyUser(user.getEmpCode().getEmpCode());
				// 保存反馈实体
				feedBackReasionService.saveFeedBackReasion(feedback);
				// 保存任务部门处理结果实体
				resultService.updateResult(result);
				// 保存投诉实体
				complaint.setOperatoerid(new BigDecimal(user.getEmpCode()
						.getId()));
				// 员工姓名
				complaint.setOperatoername(user.getEmpCode().getEmpName());
				// 根据工单ID查询最后的工单操作记录
				WorkOrder oldWorkRecord = workOrderService
						.getLastWorkOrderRecordByComplaintId(complaintId);
				// 新增工单操作信息
				saveOrUpdateWorkOrder(complaint, oldWorkRecord, actionType,
						user);
				// 更新工单信息
				complaintService.updateComplaint(complaint);
				if (complaint.getProstatus() == null
						|| !Constants.COMPLAINT_STATUS_FRONTROUND
								.equals(complaint.getProstatus())) {
					dutyService.updateComplaint(complaint);
				}
			}
		}
	}

	/**
	 * @Title: passFeedback
	 * @Description: TODO(任务部门反馈登记)
	 * @param @param complaintId,resultId,feedbackContent,resolveCode,user 设定文件
	 *        （工单ID，处理结果ID，反馈结果，解决情况，当前用户）
	 * @return void 返回类型
	 * @throws
	 * @see <a href="package.html#section">查看流程图</a>
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void passFeedback(String complaintId, String resultId,
			String feedbackContent, String resolveCode, User user) {
		// 根据ID查询工单信息
		Complaint complaint = complaintService.getComplaintById(complaintId);
		// 通过id查询处理结果
		Result result = resultService.getResultById(new BigDecimal(resultId));
		// 工单验证
		ComplaintValidate cv = new ComplaintValidate();
		// 能否反馈（超出时限无法反馈）
		ComplaintValidate.canPassFeedback(result);
		// 工单是否已经存在
		if (cv.isExistComplaint(complaint)) {
			// 判断是否存在工单
			if (cv.isExistResult(result)) {
				// 判断是否存在任务部门处理结果
				// 如果未解决
				if (resolveCode
						.equals(Constants.COMPLAINT_RESOVLE_STATUS_UNRESOVLE)) {
					// 反馈未解决
					complaint
							.setProstatus(Constants.COMPLAINT_PROCESS_STATUS_UNRESOVLE);
				} else {
					// 反馈已解决
					complaint
							.setProstatus(Constants.COMPLAINT_PROCESS_STATUS_RESOVLE);
				}
				// 处理状态
				complaint.setDealstatus(resolveCode);
				StringBuffer prorecord = new StringBuffer("");
				prorecord.append(user.getEmpCode().getEmpCode()).append(" ")
						.append(DateUtil.getCurrentDateTime());
				// 员工工号
				result.setResults(user.getEmpCode().getEmpCode());
				result.setProrecord(prorecord.toString());
				// 处理人
				result.setDealman(user.getEmpCode().getEmpCode());
				// 处理时间
				result.setDealtime(new Date());
				// 退回原因
				FeedBackReasion feedback = new FeedBackReasion();
				// 工单id
				feedback.setComplaintid(new BigDecimal(complaintId));
				// 运单号
				feedback.setWailbillnunber(complaint.getBill());
				feedback.setWailbillcontent(feedbackContent);
				// 回访结果
				feedback.setRevisitresult(resolveCode);
				// 记录人
				feedback.setRecordman(user.getEmpCode().getEmpCode());
				// 记录部门id
				feedback.setRecordpartermentid(new BigDecimal(user.getEmpCode()
						.getDeptId().getId()));
				// 记录部门名字
				feedback.setRecordpartmentname(user.getEmpCode().getDeptId()
						.getDeptName());
				// 记录时间
				feedback.setRecordtime(new Date());
				// 记录类型
				feedback.setRecordtype(Constants.COMPLAINT_RECORD_TYPE_FEEDBACK);
				// 创建时间
				feedback.setCreateDate(new Date());
				// 创建用户
				feedback.setCreateUser(user.getEmpCode().getEmpCode());
				// 修改时间
				feedback.setModifyDate(new Date());
				// 修改用户
				feedback.setModifyUser(user.getEmpCode().getEmpCode());
				// 保存反馈实体
				feedBackReasionService.saveFeedBackReasion(feedback);
				// 保存任务部门处理结果实体
				resultService.updateResult(result);
				// 保存投诉实体
				complaintService.updateComplaint(complaint);
			}
		}
	}

	/**
	 * @Title: delayApplication
	 * @Description: TODO(任务部门延时申请)
	 * @param @param complaintId,resultId,feedbackContent,resolveCode,user 设定文件
	 *        （工单ID，处理结果ID，反馈结果，申请延时时间，当前用户）
	 * @return void 返回类型
	 * @throws
	 * @see <a href="package.html#section">查看流程图</a>
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delayApplication(String complaintId, String resultId,
			String feedbackContent, String applyCode, User user) {
		// :根据ID查询工单信息
		Complaint complaint = complaintService.getComplaintById(complaintId);
		// 通过id查询
		Result result = resultService.getResultById(new BigDecimal(resultId));
		// 工单验证
		ComplaintValidate cv = new ComplaintValidate();
		// 工单是否已经存在
		if (cv.isExistComplaint(complaint)) {
			// 判断是否存在工单,及是否可以做延时申请
			if (cv.isExistResult(result) && cv.isNotDuplicatedDelay(result)) {
				// 更新工单任务部门处理结果的申请延时数
				result.setAppdelay(new BigDecimal(applyCode));
				// 更新工单任务部门处理结果的申请延时为未生效
				result.setDelay(Constants.IF_DELAY_APPLICATION_EFFECTIVE_NO);
				// 延时申请原因
				result.setPostponeReason(feedbackContent);
				// 更新工单的解决状态为未解决
				complaint.setDealstatus(Constants.COMPLAINT_RESOVLE_STATUS_UNRESOVLE);
				// 根据工单ID查询最后的工单操作记录
				WorkOrder oldWorkRecord = workOrderService.getLastWorkOrderRecordByComplaintId(complaintId);
				String actionType = "";
				
				//操作类型
				String operatorType = oldWorkRecord.getOperatorType();
				// 操作类型：处理提交至反馈
				if (
						Constants.COMPLAINT_OPERATE_TYPE_FROM_PROCESS.equals(operatorType)
						// 操作类型：延时申请来源于完成处理
						|| Constants.COMPLAINT_OPERATE_TYPE_DELAY_FROM_PROCESS.equals(operatorType) 
						// 操作类型：部门退回来源于完成处理
						|| Constants.COMPLAINT_OPERATE_TYPE_RETURN_FROM_PROCESS.equals(operatorType)) {
					// 如果是从审批或处理流入反馈的工单，则退回后统一流入处理节点
					// 更新工单状态为单个部门退回，从处理过来的
					complaint.setProstatus(Constants.COMPLAINT_APPLY_DELAY_TO_PROCESS);
					// 操作类型：延时申请来源于完成处理
					actionType = Constants.COMPLAINT_OPERATE_TYPE_DELAY_FROM_PROCESS;
				}
				// 操作类型：审批提交至反馈
				else if (
						Constants.COMPLAINT_OPERATE_TYPE_FROM_APPROVAL.equals(operatorType)
						// 操作类型：延时申请来源于审批
						|| Constants.COMPLAINT_OPERATE_TYPE_DELAY_FROM_APPROVAL.equals(operatorType)
						// 操作类型：部门退回来源于审批
						|| Constants.COMPLAINT_OPERATE_TYPE_RETURN_FROM_APPROVAL.equals(operatorType)) {
					// 更新工单状态为单个部门退回，从审批过来的
					complaint.setProstatus(Constants.COMPLAINT_APPLY_DELAY_TO_APPROVAL);
					// 操作类型：延时申请来源于审批
					actionType = Constants.COMPLAINT_OPERATE_TYPE_DELAY_FROM_APPROVAL;
				}
				// 操作类型：升级提交至反馈
				else if (
						Constants.COMPLAINT_OPERATE_TYPE_FROM_UPGRADE.equals(operatorType)
						// 操作类型：延时申请来源于升级
						|| Constants.COMPLAINT_OPERATE_TYPE_DELAY_FROM_UPGRADE.equals(operatorType)
						// 操作类型：延时申请来源于升级
						|| Constants.COMPLAINT_OPERATE_TYPE_RETURN_FROM_UPGRADE.equals(operatorType)) {
					// 如果从升级流入反馈的工单，则退回后流入升级节点
					// 更新工单状态为单个部门退回，从升级过来的
					complaint.setProstatus(Constants.COMPLAINT_APPLY_DELAY_TO_UPGRADE);
					actionType = Constants.COMPLAINT_OPERATE_TYPE_DELAY_FROM_UPGRADE;
				}
				// 新增工单操作信息
				saveOrUpdateWorkOrder(complaint, oldWorkRecord, actionType,
						user);
				// toDo保存处理结果实体
				resultService.updateResult(result);
				// 保存工单实体
				complaintService.updateComplaint(complaint);
				// 保存申请延时记录
				this.saveFeedBackReason(complaint, user, feedbackContent,
						Constants.FEED_BACK_REASION_TYPE_DELAY);
			}
		}
	}

	/**
	 * 
	 * <p>
	 * 维护处理编号表的值<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-8-29
	 * @return String
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	private String generateDealBill() {
		// 维护处理编号表的值
		return complaintService.getDealbill();
	}

	/**
	 * 
	 * <p>
	 * Description:工单上报提交<br />
	 * </p>
	 * 
	 * @author ouy
	 * @version 0.1 2012-6-15
	 * @param complaint
	 *            要上报的工单
	 * @param mainFid
	 *            背绑定的工单id
	 * @param user
	 * @param serviceId
	 * @return boolean
	 */
	@SuppressWarnings("serial")
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean submitComplaint(Complaint complaint,User user) {
		if(complaint != null && StringUtils.isEmpty(complaint.getBusinessModel())) {
			//请选择业务模式
			BusinessException error = new ComplaintException(
					ComplaintExceptionType.COMPLAINT_ERROR_CHOSE_BUSINESS_TYPE);
			throw new GeneralException(error.getErrorCode(),
					error.getMessage(), error, error.getErrorArguments()) {
			};
		}
		
		Integer compId = null;
		// 如果凭证号为运单号的查询出出发部门id及出发部门名字
		if (!StringUtil.isEmpty(complaint.getBill())) {
			FossWaybillInfo waybill = complaintService
					.findWaybillByNO(complaint.getBill());
			if (null != waybill) {
				// 查询出对应的部门
				Department leaveDept = departmentService
						.getDeptByStandardCode(waybill.getReceiveDeptNumber());
				// 设置出发部门id
				complaint.setLeaveDept(leaveDept.getId());
				// 设置出发部门名字
				complaint.setLeaveDeptName(leaveDept.getDeptName());
				if (null == complaint.getCompmanid()) {
					Member member = memberManager.getMemberByCustNumber(waybill
							.getSenderNumber());
					if (null != member) {
						complaint.setCustlevel(member.getDegree());
					}
				}
			}
		}

		// 创建时间
		complaint.setCreatetime(new Date());
		// 最后更新时间
		complaint.setLastupdatetime(new Date());
		// 这个地方需要修改
		/* complaint.setDealbill(complaintService.getDealbill()); */
		complaint.setDealbill(generateDealBill());
		// 报告时间
		complaint.setReporttime(new Date());
		// 创建用户
		complaint.setCreateuserid(new BigDecimal(user.getEmpCode().getId()));
		// 最后修改用户
		complaint
				.setLastmodifyuserid(new BigDecimal(user.getEmpCode().getId()));
		if (Constants.IF_FEED_BACK_YES.equals(complaint.getFeedback().trim())) {
			// 最终状态 前台已处理
			complaint.setProstatus(Constants.COMPLAINT_STATUS_FRONTROUND);
			// 已解决
			complaint.setDealstatus(Constants.COMPLAINT_RESOVLE_STATUS_RESOVLE);
		} else {
			// 待处理
			complaint.setProstatus(Constants.COMPLAINT_STATUS_PENDING);
			// 未解决
			complaint
					.setDealstatus(Constants.COMPLAINT_RESOVLE_STATUS_UNRESOVLE);
		}
		Complaint compMain = null;
		if (null == complaint.getMainFid() || "".equals(complaint.getMainFid().trim())) {
			// 重复工单码
			complaint.setRecomcode("");
			// 重复绑定码
			complaint.setRebindno("");
			// 保存工单
			compId = complaintService.saveComplaint(complaint);
			if (complaint.getProstatus() == null
					|| !Constants.COMPLAINT_STATUS_FRONTROUND.equals(complaint
							.getProstatus())) {
				dutyService.complaintsInsert(complaint);
			}
		} else {
			// 根据ID查询工单信息
			compMain = complaintService.getComplaintById(complaint.getMainFid().trim());
			// 是否可以绑定（单号相同，上报类型相同可以绑定）
			if (ComplaintValidate.ifComplainBinding(compMain, complaint)) {
				if (null == compMain.getRebindno()
						|| "".equals(compMain.getRebindno())) {
					// 主记录不显示重复工单码，只生成重复绑定码
					compMain.setRebindno(complaint.getRebindno());
					// 更新工单
					complaintService.updateComplaint(compMain);
					if (complaint.getProstatus() == null
							|| !Constants.COMPLAINT_STATUS_FRONTROUND
									.equals(complaint.getProstatus())) {
						dutyService.updateComplaint(complaint);
					}
				}
				// 保存代码
				compId = complaintService.saveComplaint(complaint);
				if (complaint.getProstatus() == null
						|| !Constants.COMPLAINT_STATUS_FRONTROUND
								.equals(complaint.getProstatus())) {
					dutyService.complaintsInsert(complaint);
				}

			}
		}

		// 将工单Id和服务编号关系保存到表中
		if (compId != null) {
			String strCompId = compId.toString();
			// 新增工单Id与呼叫中心ServiceId关系
			complaintService.addNewRelation(strCompId, complaint.getServiceId(),
					user.getLoginName());
		}

		// 工单号反写操作
		if (	
				null != complaint.getServiceId() && !("".equals(complaint.getServiceId().trim()))
				&& 
				null != complaint.getChannel() && !"".equals(complaint.getChannel().trim())
		) {
			try {
				boolean flag = false ;
				//去掉空格
				complaint.setChannel(complaint.getChannel().trim());
				complaint.setServiceId(complaint.getServiceId().trim());
				//苏州
				if(complaint.getChannel().equals(Constants.CC_SU_ZHOU)){
					flag = complaintOperate.bindComplaintId2ServiceNumber(
							complaint.getServiceId(), compId + "");
				}
				//合肥
				if(complaint.getChannel().equals(Constants.CC_HE_FEI)){
					flag = complaintOperate.bindHfComplaintId2ServiceNumber(
							complaint.getServiceId(), compId + "");
				}
				if (!flag) {
					// 工单号反写失败，请重新点击提交按钮
					throw new CompException(COMPLAINT_RETURN_FAILUE,new Object[] {}) {};
				}
				// 捕捉异常
			} catch (CrmBusinessException e) {
				throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
						null) {
				};
			}
		}
		return true;
	}

	/**
	 * <p>
	 * Description:查询上报退回工单页面<br />
	 * </p>
	 * 
	 * @author ouy
	 * @version 0.1 2012-4-20
	 * @param condition
	 * @return List<Complaint>
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Map<String, Object> searchReturnedComplaints(
			ComplaintSearchCondition condition, User user) {
		// 返回结果集map
		Map<String, Object> map = new HashMap<String, Object>();
		// 查询处理状态时退回的工单
		condition.setProstatus(Constants.COMPLAINT_STATUS_REPORT_RETURNED);
		// 用户
		condition.setUserid(user.getEmpCode().getId());
		// 工单回访查询页面
		List<Complaint> complaintList = this.getComplaintService()
				.getComplaintResultList(condition);
		// 查询工单条数
		Integer count = this.getComplaintService().getComplaintResultCount(
				condition);
		// 工单列表
		map.put(complaintListStr, complaintList);
		// 条数
		map.put("count", count);
		// 返回结果集
		return map;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean submitComplaint(Complaint complaint, String complaintId,
			String repeatedCode, User user) {
		if(complaint != null && StringUtils.isEmpty(complaint.getBusinessModel())) {
			//请选择业务模式
			BusinessException error = new ComplaintException(
					ComplaintExceptionType.COMPLAINT_ERROR_CHOSE_BUSINESS_TYPE);
			throw new GeneralException(error.getErrorCode(),
					error.getMessage(), error, error.getErrorArguments()) {
			};
		}
		
		// 根据ID查询工单信息
		Complaint cp = this.complaintService.getComplaintById(complaint
				.getFid().toString());
		// 重复工单码
		String oldRecomcode = cp.getRecomcode();
		// 重复绑定码
		String oldRebindno = cp.getRebindno();
		// 处理编号
		String oldDealbill = cp.getDealbill();
		// 上报类型
		String oldReportType = cp.getReporttype();
		// 是否有主记录
		if (null != complaintId && !"".equals(complaintId.trim())) {
			// 查出要绑定的主记录
			Complaint compMain = this.getComplaintService().getComplaintById(
					complaintId);
			// 验证是否可以相互绑定
			ComplaintValidate.ifComplainBinding(compMain, complaint);
			// 是否是主记录
			if (null == compMain.getRebindno()
					|| "".equals(compMain.getRebindno().trim())) {
				// 重复绑定码
				compMain.setRebindno(repeatedCode);
				// 最后修改人
				compMain.setLastmodifyuserid(new BigDecimal(user.getEmpCode()
						.getId()));
				// 更新工单
				complaintService.updateComplaint(compMain);
			}
			// 是否更改了绑定
			if (compMain.getRebindno().trim()
					.equals(complaint.getRebindno().trim())) {
				// 判断是否是主记录
				/* if(null!=oldRecomcode && !"".equals(oldRecomcode.trim()) */
				// 是否是主记录
				if (null != oldRebindno
						&& !"".equals(oldRebindno.trim())
						&& (oldRebindno.trim().substring(1,
								oldRebindno.trim().length())
								.equals(oldDealbill))) {
					// 工单查询条件
					ComplaintSearchCondition condition = new ComplaintSearchCondition();
					// 重复绑定码
					condition.setRebindno(oldRebindno);
					// 查询工单
					List<Complaint> compList = complaintService
							.searchComplaints(condition);
					/**
					 * 凭证号是否更改 更改就直接解除关系
					 */
					if (!ComplaintValidate.stringComparasion(
							complaint.getBill(), cp.getBill())
							|| !oldReportType.trim().equals(
									complaint.getReporttype().trim())) {
						// 直接解除关系
						for (Complaint comp : compList) {
							if (!comp.getFid().equals(complaint.getFid())) {
								// 清楚重复绑定码
								comp.setRebindno("clear");
								// 清楚重重复工单吗
								comp.setRecomcode("clear");
								// 最后修改人
								comp.setLastmodifyuserid(new BigDecimal(user
										.getEmpCode().getId()));
								// 更新工单
								complaintService.updateComplaint(comp);
							}
						}
					} else {
						for (Complaint comp : compList) {
							// 重复绑定码
							comp.setRebindno(repeatedCode);
							// 重复工单码
							comp.setRecomcode(repeatedCode);
							// 最后修改人
							comp.setLastmodifyuserid(new BigDecimal(user
									.getEmpCode().getId()));
							// 更新工单
							complaintService.updateComplaint(comp);
						}
					}
					// 字符串比较，包含过滤null功能
				} else if (!ComplaintValidate.stringComparasion(
						complaint.getBill(), cp.getBill())) {
					// 清楚重复绑定码
					complaint.setRebindno("clear");
					// 清楚重重复工单吗
					complaint.setRecomcode("clear");
				} else {
					// 重复绑定码
					complaint.setRebindno(repeatedCode);
					// 重复工单码
					complaint.setRecomcode(repeatedCode);
				}
			}
		} else {
			/**
			 * 是不是主记录 1. 主记录：凭证号、上报类型 发生变更时，解绑所有相关工单 2. 非主记录： 凭证号、上报类型
			 * 发生变更时，解绑自身
			 */
			/**
			 * 没有主记录， 凭证号是否更改,上报类型是否更改，为主记录， 更改就直接解除关系
			 */
			/**
			 * if(null!=oldRecomcode && !"".equals(oldRecomcode.trim())
			 * &&(oldRebindno.trim().substring(1,
			 * oldRebindno.trim().length()).equals(oldDealbill)) ||
			 * !oldReportType.trim().equals(complaint.getReporttype().trim())){
			 */
			// 是否是主机录
			if (null != oldRebindno
					&& !"".equals(oldRebindno.trim())
					&& (oldRebindno.trim().substring(1,
							oldRebindno.trim().length()).equals(oldDealbill))) {
				// 工单查询条件
				ComplaintSearchCondition condition = new ComplaintSearchCondition();
				// 重复绑定码
				condition.setRebindno(oldRebindno);
				// 查询工单
				List<Complaint> compList = complaintService
						.searchComplaints(condition);
				/*
				 * complaint.setRebindno("clear");
				 * complaint.setRecomcode("clear");
				 */
				/**
				 * if(!ComplaintValidate.stringComparasion(complaint.getBill(),
				 * cp.getBill())){
				 */
				// 字符串比较，包含过滤null功能
				if (!ComplaintValidate.stringComparasion(complaint.getBill(),
						cp.getBill())
						|| !oldReportType.trim().equals(
								complaint.getReporttype().trim())) {
					// 直接解除关系
					for (Complaint comp : compList) {
						// if(!(comp.getFid().equals(complaint.getFid()))){
						// 重复绑定码
						comp.setRebindno("clear");
						// 重复工单码
						comp.setRecomcode("clear");
						// 最后修改人
						comp.setLastmodifyuserid(new BigDecimal(user
								.getEmpCode().getId()));
						// 更新工单
						complaintService.updateComplaint(comp);
						// }
					}
				}
				// 不是主记录，更改了凭证号和上报类型
			} else if (!ComplaintValidate.stringComparasion(
					complaint.getBill(), cp.getBill())
					|| !oldReportType.trim().equals(
							complaint.getReporttype().trim())) {
				// 重复绑定码
				complaint.setRebindno("clear");
				// 重复工单码
				complaint.setRecomcode("clear");
			}
		}
		// 最后修改人
		complaint
				.setLastmodifyuserid(new BigDecimal(user.getEmpCode().getId()));
		// 是否退回
		if (Constants.IF_FEED_BACK_YES.equals(complaint.getFeedback().trim())) {
			// 最终状态 前台已处理
			complaint.setProstatus(Constants.COMPLAINT_STATUS_FRONTROUND);
			// 已解决
			complaint.setDealstatus(Constants.COMPLAINT_RESOVLE_STATUS_RESOVLE);
		} else {
			// 待处理
			complaint.setProstatus(Constants.COMPLAINT_STATUS_PENDING);
			// 未解决
			complaint
					.setDealstatus(Constants.COMPLAINT_RESOVLE_STATUS_UNRESOVLE);
		}
		// 更新工单
		complaintService.updateComplaint(complaint);
		if (complaint.getProstatus() == null
				|| !Constants.COMPLAINT_STATUS_FRONTROUND.equals(complaint
						.getProstatus())) {
			dutyService.updateComplaint(complaint);
		}
		// 返回结果
		return true;
	}

	/**
	 * <p>
	 * Description:申请延时审批，同意或者不同意都调用此方法，在状态中设置对应状态<br />
	 * </p>
	 * 
	 * @author Weill
	 * @version 0.1 2012-4-22
	 * @param complaint
	 * @return boolean
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean applyMoreTimeApproval(Complaint complaint,
			List<Result> results, String agreeFlag, User user,
			String returnReason) {
		// 根据ID查询工单信息
		Complaint oldComplaint = complaintService.getComplaintById(complaint
				.getFid() + "");
		// 检查当前工单是否能进行延时审批
		if (ComplaintValidate.canApplyMoreTimeApproval(oldComplaint)) {
			// COMPLAINT
			if (Constants.IF_DELAY_APPLICATION_EFFECTIVE_YES.equals(agreeFlag)) {
				// 同意延时
				for (Result result : results) {
					// 0
					BigDecimal timeLimit = BigDecimal.ZERO;
					// 通过Result Fid获取处理结果数据
					if (StringUtils.isNotEmpty(result.getFid() + "")) {
						// 通过id查询处理结果
						Result rs = this.resultService.getResultById(result
								.getFid());
						// 处理限制不为空的话
						if (rs.getProcesstimelimit() != null) {
							timeLimit = timeLimit.add(rs.getProcesstimelimit());
						}
					}
					/*
					 * result.setProcesstimelimit(timeLimit.add(new
					 * BigDecimal(24).multiply(result.getAppdelay())));
					 */
					result.setProcesstimelimit(timeLimit.add(result
							.getAppdelay()));
					// 1
					result.setDelay(Constants.IF_DELAY_APPLICATION_EFFECTIVE_YES);
					// 根据主键ID更新处理结果
					resultService.updateResult(result);
				}
			} else {// 拒绝延时
				for (Result result : results) {
					// 2
					result.setDelay(Constants.IF_DELAY_APPLICATION_EFFECTIVE_REFUSE);
					// 根据主键ID更新处理结果
					resultService.updateResult(result);
				}
				// 保存退回原因/ 反馈结果
				this.saveFeedBackReason(oldComplaint, user, returnReason,
						Constants.COMPLAINT_RECORD_TYPE_RETURN);
			}
			// 多任务部门情况下需要根据各部门的退回和延时情况，设置工单状态
			setComplaintProStatus(complaint);
			// 更新工单
			complaintService.updateComplaint(complaint);
			// 返回true
			return true;
		}
		// 返回false
		return false;
	}

	/**
	 * <p>
	 * Description:工单回访查询<br />
	 * </p>
	 * 
	 * @author ouy
	 * @version 0.1 2012-4-23
	 * @return Complaint
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Map<String, Object> searchVerfiyComplaint(
			ComplaintSearchCondition condition, User user, Boolean ifDedult) {
		// 已解决 ,已经到期
		condition.setProstatus("'" + Constants.COMPLAINT_PROCESS_STATUS_RESOVLE
				+ "','" + Constants.COMPLAINT_PROCESS_STATUS_EXPIRED + "','"
				+ Constants.COMPLAINT_STATUS_REVISEIT + "'");
		// 加不等于已核实
		if (ifDedult) {
			// 处理人id
			condition.setDealmanid(Integer.parseInt(user.getEmpCode().getId()));
		}
		// 回访
		List<Complaint> compList = this.getComplaintService()
				.getVeryfiyComplaints(condition);
		// 回访
		Integer count = this.getComplaintService().getVeryfiyComplaintsCount(
				condition);
		// 返回结果map
		Map<String, Object> map = new HashMap<String, Object>();
		// 工单列表
		map.put("compList", compList);
		// 条数
		map.put("count", count);
		// 结果返回
		return map;
	}

	/**
	 * 
	 * <p>
	 * Description:工单回访处理：点击回访登记<br />
	 * </p>
	 * 
	 * @author ouy
	 * @version 0.1 2012-6-15
	 * @param complaint
	 *            回访的工单
	 * @param feedBackReasion
	 *            退回原因记录
	 * @param user
	 *            当前用户
	 * @return boolean
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean submitVerfiyComplaint(Complaint complaint,
			FeedBackReasion feedBackReasion, User user) {
		// 处理状态
		if (ComplaintValidate.isFinalFeedback(complaint)) {
			// 已核实
			complaint.setProstatus(Constants.COMPLAINT_STATUS_VERIFIED);
		} else {
			// 已回访
			complaint.setProstatus(Constants.COMPLAINT_STATUS_REVISEIT);
		}
		// 更新工单
		complaintService.updateComplaint(complaint);
		// 查询条件
		ResultSearchCondition condition = new ResultSearchCondition();
		// 工单id
		condition.setComplainid(complaint.getFid());
		condition.setTaskpartmentid(new BigDecimal(user.getEmpCode()
				.getDeptId().getId()));
		// 根据条件查询处理结果
		List<Result> resultList = resultService.getResultByCondition(condition);
		// 工单id
		feedBackReasion.setComplaintid(complaint.getFid());
		// 创建人
		feedBackReasion.setCreateUser(user.getEmpCode().getId());
		// 修改人
		feedBackReasion.setModifyUser(user.getEmpCode().getId());
		// 记录人
		feedBackReasion.setRecordman(user.getEmpCode().getEmpCode());
		// 记录时间
		feedBackReasion.setRecordtime(new Date());
		// 运单号
		feedBackReasion.setWailbillnunber(complaint.getBill());
		// 创建时间
		feedBackReasion.setCreateDate(new Date());
		// 修改时间
		feedBackReasion.setModifyDate(new Date());
		// 记录类型
		feedBackReasion.setRecordtype(Constants.FEED_BACK_REASION_TYPE_VERYFIY);
		// 记录部门id
		feedBackReasion.setRecordpartermentid(new BigDecimal(user.getEmpCode()
				.getDeptId().getId()));
		// 记录部门名字
		feedBackReasion.setRecordpartmentname(user.getEmpCode().getDeptId()
				.getDeptName());
		// 反馈退回记录对象
		feedBackReasionService.saveFeedBackReasion(feedBackReasion);
		if (null != resultList && resultList.size() > 0) {
			Result result = resultList.get(0);
			// 处理时间
			Date now = new Date();
			// 单位：小时
			String unit = "hour";
			// 处理时限
			boolean overTime = ComplaintValidate.isOverTime(now,
					result.getDealtime(), result.getProcesstimelimit(), unit);
			// 判断是否任务部门在反馈时限内反馈
			if (overTime) {
				// 超时
				result.setIfovertime(Constants.RESULT_IF_OVER_TIME_YES);
				// 0
				result.setIfmaturity(Constants.RESULT_MATURITY_NO);
			} else {
				// 0
				result.setIfovertime(Constants.RESULT_IF_OVER_TIME_NO);
				// 任务部门操作是否到期
				result.setIfmaturity(Constants.RESULT_MATURITY_YES);
			}
			// 根据主键ID更新处理结果
			resultService.updateResult(result);
		}
		// 返回结果
		return true;
	}

	/**
	 * <p>
	 * Description: 根据工单ID获取该工单相关的通知对象列表<br />
	 * </p>
	 * 
	 * @author admin
	 * @version 0.1 2012-4-25
	 * @param compId
	 * @return List<Bulletin>
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Bulletin> searchBulletinByCompId(BigDecimal compId) {
		// 通过工单id查询 通知对象
		return this.bulletinService.searchBulletinByCompId(compId);
	}

	/**
	 * <p>
	 * Description: 根据工单ID获取该工单相关的通知对象列表<br />
	 * </p>
	 * 
	 * @author admin
	 * @version 0.1 2012-4-25
	 * @param compId
	 * @return List<Bulletin>
	 */
	@Override
	public List<Bulletin> searchBulletinToProcByCompId(BigDecimal compId) {
		// 通过工单id查通报对象(仅显示)
		return this.bulletinService.searchBulletinToProcByCompId(compId);
	}

	/**
	 * @Title: getMostRecentlyComplaintByCustCode
	 * @Description: TODO(根据客户编码获得客户最近一次投诉记录)
	 * @param @param custCode
	 * @param @return 设定文件
	 * @return Complaint 返回类型
	 * @throws
	 * @see <a href="package.html#section">查看流程图</a>
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Complaint getMostRecentlyComplaintByCustCode(String custCode) {
		// 查询条件
		ComplaintSearchCondition condition = new ComplaintSearchCondition();
		// 工单id
		condition.setComplainid(custCode);
		// 查询工单
		List<Complaint> list = complaintService
				.getAllComplaintByCustCode(condition);
		// 工单
		Complaint complaint = null;
		if (list != null && list.size() > 0) {
			complaint = list.get(0);
		}
		// 返回工单
		return complaint;
	}

	/**
	 * @Title: getAllComplaintByCustCode
	 * @Description: TODO(根据客户编码获取该客户所有投诉信息列表--按投诉时间倒序排列)
	 * @param @param custCode
	 * @param @return 设定文件
	 * @return List<Complaint> 返回类型
	 * @throws
	 * @see <a href="package.html#section">查看流程图</a>
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Complaint> getAllComplaintByCustCode(String custCode) {
		// 查询工单
		ComplaintSearchCondition condition = new ComplaintSearchCondition();
		// 工单id
		condition.setComplainid(custCode);
		// 根据客户ID获得客户最近一次投诉记录
		return complaintService.getAllComplaintByCustCode(condition);
	}

	/**
	 * 
	 * <p>
	 * Description:查询业务范围<br />
	 * </p>
	 * 
	 * @author ouy
	 * @version 0.1 2012-4-23
	 * @return List<BasciLevel>
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<BasciLevel> getFBasciLevlel(BasciLevelSearchCondition condition) {
		// 如果最终反馈为否(0)，查询有效的记录，如果最终反馈为是,查询所有的记录
		if (null != condition && null != condition.getFeedBack()
				&& "1".equals(condition.getFeedBack())) {
			condition.setUseYn("1");
		}
		// 查询业务范围
		return basciLevelService.searchFBasciLevel(condition);
	}

	/**
	 * <p>
	 * Description:业务类型查询<br />
	 * </p>
	 * 
	 * @author ouy
	 * @version 0.1 2012-4-23
	 * @return List<BasciLevel>
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<BasciLevel> getChildBasciLevel(
			BasciLevelSearchCondition condition) {
		// 如果最终反馈为否(0)，查询有效的记录，如果最终反馈为是,查询所有的记录
		if (null != condition && null != condition.getFeedBack()
				&& "1".equals(condition.getFeedBack())) {
			condition.setUseYn("1");
		}
		// 通过id查基础资料层级
		return basciLevelService.searchBasciLevelById(condition);
	}

	/**
	 * 
	 * <p>
	 * Description: 通过compid查退回原因记录列表<br />
	 * </p>
	 * 
	 * @author admin
	 * @version 0.1 2012-4-24
	 * @param compId
	 * @return List<FeedBackReasion>
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public List<FeedBackReasion> searchFeedBackReasionByCompId(BigDecimal compId) {
		// 通过compid查退回原因
		return feedBackReasionService.searchFeedBackReasionByCompId(compId);
	}

	/**
	 * <p>
	 * Description: 根据条件查询部门处理结果列表<br />
	 * </p>
	 * 
	 * @author admin
	 * @version 0.1 2012-4-24
	 * @param condition
	 * @return List<Result>
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Result> getResultByCondition(ResultSearchCondition condition) {
		// 查询部门处理记录
		return resultService.getResultByCondition(condition);
	}

	/**
	 * 
	 * <p>
	 * Description:查询投诉 业务范围
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2012-7-10
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<BasciLevelView> searchComplaintLevelDataList(
			BasciLevelSearchCondition condition) {
		// 查询查询上报类型为投诉的上报
		condition.setType(Constants.COMPLAINT_REPORTTYE_REPORT);
		// 基础资料（ 层级）查询
		return this.getBasciLevelService().getFoundationDataList(condition);
	}

	/**
	 * 
	 * <p>
	 * Description:查询异常 业务范围
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2012-7-10
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<BasciLevelView> searchExceptionLevelDataList(
			BasciLevelSearchCondition condition) {
		// 查询上报类型为异常的上报
		condition.setType(Constants.COMPLAINT_REPORTTYE_EXCEPTION);
		// 基础资料（ 层级）查询
		return this.getBasciLevelService().getFoundationDataList(condition);
	}

	/**
	 * <p>
	 * Description: 获取所有部门列表<br />
	 * </p>
	 * 
	 * @author admin
	 * @version 0.1 2012-4-25
	 * @return List<Department>
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Department> getAllDepartment(String deptName) {
		if (null != deptName && !"".equals(deptName)) {
			// 根据部门名称模糊查找部门对象
			return this.departmentService.getDepartmentByDeptName("%"
					+ deptName + "%");
		} else {
			// 根据部门名称模糊查找部门对象
			return this.departmentService.getDepartmentByDeptName("%");
		}
	}

	/**
	 * <p>
	 * Description: <br />
	 * </p>
	 * 
	 * @author admin
	 * @version 0.1 2012-4-25
	 * @return Integer
	 */
	private Integer saveFoundationData(List<ProcResult> procResultList,
			BasciLevel basciLevel, BigDecimal fBasciLevelId, User user) {
		basciLevel.setParentid(fBasciLevelId);
		// 保存基本层级
		int basciLevelId = basciLevelService.saveBasciLevel(basciLevel);
		for (ProcResult procResult : procResultList) {
			procResult.setLevelid(new BigDecimal(basciLevelId));
			// 创建用户
			procResult
					.setCreateuserid(new BigDecimal(user.getEmpCode().getId()));
			// 最后修改用户
			procResult.setLastmodifyuserid(new BigDecimal(user.getEmpCode()
					.getId()));
			// 保存
			procResultService.savaProcResult(procResult);
		}
		return basciLevelId;
	}

	/**
	 * 
	 * <p>
	 * Description:点击业务范围设置保存<br />
	 * 传进来的时候要给basciLevel 设置上报类型 方便我后台保存
	 * </p>
	 * 
	 * @author ouy
	 * @version 0.1 2012-4-26
	 * @param reportbasciLevel
	 * @param exceptionBasciLevel
	 * @param user
	 *            void
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void setUpParentBasciLevel(List<BasciLevel> reportbasciLevel,
			List<BasciLevel> exceptionBasciLevel, User user) {
		// TODO Auto-generated method stub
		if (null != reportbasciLevel && reportbasciLevel.size() > 0) {
			for (BasciLevel basciLevel : reportbasciLevel) {
				// 创建用户
				basciLevel.setCreateuserid(Integer.parseInt(user.getEmpCode()
						.getId()));
				// 最后修改人
				basciLevel.setLastmodifyuserid(new BigDecimal(user.getEmpCode()
						.getId()));
				// 类型：工单
				basciLevel.setType(Constants.COMPLAINT_REPORTTYE_REPORT);
				// 保存基本层级
				basciLevelService.saveBasciLevel(basciLevel);
			}
		}
		if (null != exceptionBasciLevel && exceptionBasciLevel.size() > 0) {
			for (BasciLevel basciLevel : exceptionBasciLevel) {
				// 创建用户
				basciLevel.setCreateuserid(Integer.parseInt(user.getEmpCode()
						.getId()));
				// 最后修改人
				basciLevel.setLastmodifyuserid(new BigDecimal(user.getEmpCode()
						.getId()));
				// 类型：异常
				basciLevel.setType(Constants.COMPLAINT_REPORTTYE_EXCEPTION);
				// 保存基本层级
				basciLevelService.saveBasciLevel(basciLevel);
			}
		}
	}

	/**
	 * <p>
	 * Description:点击查询或者修改页面<br />
	 * </p>
	 * 
	 * @author ouy
	 * @version 0.1 2012-4-25
	 * @param childId
	 *            业务类型id
	 * @param reportType
	 *            上报类型
	 * @return Map<String,Object>
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Map<String, Object> searchFoundationData(BigDecimal childId,
			String reportType) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 根据id查询
		List<ProcResult> ProcResult = procResultService
				.getProcResultByLevelId(childId);
		// 将查询结果放入map中
		map.put("ProcResult", ProcResult);
		// 返回结果集
		return map;
	}

	private void updateFoundationData(List<ProcResult> procResultList,
			BasciLevel childBasciLevel, String reportType, User user) {
		// 根据id查询
		List<ProcResult> oldPRList = procResultService
				.getProcResultByLevelId(childBasciLevel.getFid());
		// 更新基础等级
		basciLevelService.updateBasciLevel(childBasciLevel);
		// 迭代
		for (ProcResult procResult : procResultList) {
			// id不为空且 不等于0
			if (null != procResult.getFid()
					&& !procResult.getFid().equals(BigDecimal.ZERO)) {
				// 迭代
				for (ProcResult oldProcResult : oldPRList) {
					// id不相等的话
					if (oldProcResult.getFid().equals(procResult.getFid())) {
						// 修改基础资料处理结果
						procResult.setLastmodifyuserid(new BigDecimal(user
								.getEmpCode().getId()));
						// 修改ProcResult
						procResultService.updateProcResult(procResult);
					}
				}
			} else {
				// 等级id
				procResult.setLevelid(childBasciLevel.getFid());
				// 创建用户
				procResult.setCreateuserid(new BigDecimal(user.getEmpCode()
						.getId()));
				// 最后修改用户
				procResult.setLastmodifyuserid(new BigDecimal(user.getEmpCode()
						.getId()));
				// 保存
				procResultService.savaProcResult(procResult);
			}

		}
		// 删除基础资料处理结果
		/**
		 * 在新列表找不到 老的id 就删除
		 */
		if (null != oldPRList && oldPRList.size() != 0) {
			// 迭代oldPRList
			for (ProcResult oldProcResult : oldPRList) {
				// 是否删除标志
				boolean ifDelete = true;
				// 迭代 procResultList
				for (ProcResult procResult : procResultList) {
					// id不为空的话
					if (null != procResult.getFid()) {
						// id不相等
						if (oldProcResult.getFid().equals(procResult.getFid())) {
							// 删除
							ifDelete = false;
						}
					}
				}
				// 如果删除标志位true
				if (ifDelete) {
					// 删除
					procResultService.deleteProcResultById(oldProcResult
							.getFid());
				}
			}
		}
	}

	/**
	 * <p>
	 * Description: 查询处理结果集合 通过工单编号<br />
	 * compId : 工单编号
	 * </p>
	 * 
	 * @author hpf
	 * @version 0.1 2012-4-28
	 * @param condition
	 * @return List<Result>
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Result> searchProcessResultListByCompId(BigDecimal compId) {
		// 查询反馈
		return resultService.searchResultInfoByCompId(compId);
	}

	/**
	 * <p>
	 * Description: 查询退回记录集合 通过工单编号<br />
	 * compId : 工单编号
	 * </p>
	 * 
	 * @author hpf
	 * @version 0.1 2012-4-28
	 * @param condition
	 * @return List<Result>
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<FeedBackReasion> searchReturnedRecordListByCompId(
			BigDecimal compId) {
		// 通过compid查退回原因
		return feedBackReasionService.searchFeedBackReasionByCompId(compId);
	}

	/**
	 * <p>
	 * Description:保存修改<br />
	 * </p>
	 * 
	 * @author ouy
	 * @version 0.1 2012-4-26
	 * @param procResultList
	 * @param childBasciLevel
	 * @param fBasciLevelId
	 *            业务范围id
	 * @param reportType
	 * @param user
	 *            void
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveOrUpdateFoundationData(List<ProcResult> procResultList,
			BasciLevel childBasciLevel, BigDecimal fBasciLevelId,
			String reportType, User user) {
		if (null != childBasciLevel.getFid()
				&& !childBasciLevel.getFid().equals(BigDecimal.ZERO)) {
			updateFoundationData(procResultList, childBasciLevel, reportType,
					user);
		} else {
			saveFoundationData(procResultList, childBasciLevel, fBasciLevelId,
					user);
		}
	}

	/**
	 * <p>
	 * Description: 根据部门ID获取部门下所有员工列表<br />
	 * </p>
	 * 
	 * @author admin
	 * @version 0.1 2012-4-25
	 * @param deptId
	 * @return List<Employee>
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Employee> getAllEmployeesByDepartmentId(String deptId) {
		Department dept = new Department();
		// 部门id
		dept.setId(deptId);
		// 查找部门下所有员工
		return this.employeeService.getAllEmployeesByDeptId(dept);
	}

	/**
	 * <p>
	 * Description: 获取工单处理列表总数<br />
	 * </p>
	 * 
	 * @author admin
	 * @version 0.1 2012-4-28
	 * @param condition
	 * @return int
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public int getPendingComplaintCount(ComplaintSearchCondition condition) {
		// 锁定时间
		condition.setLockingtime(new Date());
		// 查询待办工单数
		Integer count = complaintService.getPendingComplaintsCount(condition);
		// 工单数不为空的话
		if (null != count) {
			return count.intValue();
		}
		return 0;
	}

	/**
	 * @Title: expireComplaint
	 * @Description: TODO(扫描工单，将超过处理时限的工单设置为已到期)
	 * @param 设定文件
	 * @return void 返回类型
	 * @throws
	 * @see <a href="package.html#section">查看流程图</a>
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void expireComplaint() {
		complaintService.complaintTimeoutScheduler();
		
		/*// TODO Auto-generated method stub
		ComplaintSearchCondition condition = new ComplaintSearchCondition();
		List prostatusList = new ArrayList();
		// 查询条件加入待反馈
		prostatusList.add(Constants.COMPLAINT_STATUS_WAITE_RESPONSE);
		// 查询条件加入部分部门退回，从处理过来的
		prostatusList
				.add(Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_PROCESS);
		// 查询条件加入部分部门退回，从审批过来的
		prostatusList
				.add(Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_APPROVAL);
		// 查询条件加入部分部门退回，从升级过来的
		prostatusList
				.add(Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_FROM_UPGRADE);

		// 查询条件加入工单申请延时,工单来自处理
		prostatusList.add(Constants.COMPLAINT_APPLY_DELAY_TO_PROCESS);
		// 查询条件加入工单申请延时,工单来自审批
		prostatusList.add(Constants.COMPLAINT_APPLY_DELAY_TO_APPROVAL);
		// 查询条件加入工单申请延时,工单来自升级
		prostatusList.add(Constants.COMPLAINT_APPLY_DELAY_TO_UPGRADE);

		// 查询条件加入反馈未解决
		prostatusList.add(Constants.COMPLAINT_PROCESS_STATUS_UNRESOVLE);
		condition.setProstatusList(prostatusList);
		try {
			// 工单查询
			List<Complaint> complaintList = complaintService
					.searchComplaints(condition);
			// 查询待反馈，反馈未解决的工单列表记录
			if (complaintList != null && complaintList.size() > 0) {
				Iterator iter = complaintList.iterator();
				while (iter.hasNext()) {
					Complaint complaint = (Complaint) iter.next();
					// 工单id
					BigDecimal complaintId = complaint.getFid();
					List<Result> allResultList = resultService
							.searchResultById(complaintId);
					// 根据单条工单ID查询任务部门处理结果记录
					if (allResultList != null && allResultList.size() > 0) {
						int allResultSize = allResultList.size();
						// 取得任务部门结果记录条数
						List<Result> expireResultList = resultService
								.getExpiredResultById(complaintId);
						// 根据处理时限超时条件获取所有处理超时的任务结果处理记录列表
						List<Result> expireFeedbackResultList = resultService
								.getExpiredFeedbackResultById(complaintId);
						// 根据反馈时限超时条件获取所有反馈超时的任务结果记录列表
						int expireResultSize = expireResultList.size();
						// 取得处理超时的任务部门结果记录条数
						if (allResultSize == expireResultSize) {
							// 判断是否所有任务部门处理超时
							complaint
									.setProstatus(Constants.COMPLAINT_PROCESS_STATUS_EXPIRED);
							// 设置工单处理状态为已到期
							complaintService.updateComplaint(complaint);
							// 更新工单实体
						}
						int expireFeedbackResultSize = expireFeedbackResultList
								.size();
						// 取得反馈超时的任务部门结果记录条数
						if (expireFeedbackResultSize > 0) {
							// 当存在任务部门反馈超时
							for (int i = 0; i < expireFeedbackResultList.size(); i++) {
								Result result = (Result) expireFeedbackResultList
										.get(i);
								result.setFeedtimelimit(BigDecimal.ZERO);
								// 更新任务部门反馈时限为0
								resultService.updateResult(result);
								// 更新任务部门处理结果实体
							}
							complaint
									.setDealstatus(Constants.COMPLAINT_RESOVLE_STATUS_UNRESOVLE);
							// 更新工单解决情况为未解决
							complaintService.updateComplaint(complaint);
							// 更新工单实体
						}
					}
				}
			}
		} catch (Exception e) {
		}*/
	}

	/**
	 * 
	 * <p>
	 * Description:发送投诉通知信息<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2012-7-2
	 * @param condition
	 * @return Map<String,Object>
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void processSendComplaintInfo() {
		boolean result = true;
		// :投诉通知信息类
		ComplaintInfo complaintInfoCnd = new ComplaintInfo();
		complaintInfoCnd.setPerCount(perCount);
		// 查询未发送的记录
		List<ComplaintInfo> complaintInfoList = complaintInfoService
				.inquireComplaintInfo(complaintInfoCnd);
		// 定义更新的结果集
		List<String> susComplaintInfoIds = new ArrayList<String>();
		List<String> failComplaintInfoIds = new ArrayList<String>();

		// 查询未发送的记录 不为空的话
		if (null != complaintInfoList && complaintInfoList.size() > 0) {
			// 迭代 未发送的记录
			for (ComplaintInfo complaintInfo : complaintInfoList) {
				// 投诉通知消息封装类
				List<ComplaintInformInfo> informInfos = new ArrayList<ComplaintInformInfo>();
				ComplaintInformInfo cfi = new ComplaintInformInfo();
				// 员工工号
				cfi.setJobNumber(complaintInfo.getEmpCode());
				cfi.setComplaintCount(complaintInfo.getComplaintCount());
				informInfos.add(cfi);
				try {
					// 投诉通知消息，在指定人员的OA的待办事宜中提醒“待投诉反馈（投诉数量）”
					result = oaAccidentOperate.informComplaint(informInfos);
				} catch (Exception e) {
					result = false;
				}
				if (result) {// 发送成功
					susComplaintInfoIds.add(complaintInfo.getId() + "");

				} else {// 发送失败，向失败次数加1

					failComplaintInfoIds.add(complaintInfo.getId() + "");

				}
				// 根据id更新记录
			}
			if (null != failComplaintInfoIds && failComplaintInfoIds.size() > 0) {
				complaintInfoService
						.updateFailComplaintInfoList(failComplaintInfoIds);
			}

			if (null != susComplaintInfoIds && susComplaintInfoIds.size() > 0) {
				complaintInfoService
						.updateSusComplaintInfoList(susComplaintInfoIds);
			}
		}
	}

	/**
	 * <p>
	 * Description:处理工单模块的待办事项信息<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2012-6-6
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void processComplaintMessage() {
		// 处理上报退回待办信息
		processReturnComplaintMessage();
		// 处理待办工单待办信息
		processWaitProccesComplaintMessage();
		// 处理部门工单待办信息
		processTaskComplaintMessage();
		// 处理回访待办信息
		processVerifyTaskMessage();
	}

	/**
	 * 
	 * <p>
	 * Description:处理上报退回及时待办信息<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2012-7-2
	 * @param complaint
	 * @return void
	 */
	private void processRealTimeReturnComplaint(Complaint complaint) {
		if (null != complaint && null != complaint.getCreateuserid()) {
			// 根据employeeId得到userid
			Integer userId = complaintService
					.inquireUserIdByEmployeeId(complaint.getCreateuserid()
							.toString());
			if (null != userId) {
				Message message = new Message();
				message.setTaskcount(1);
				// 用户
				message.setUserid(userId);
				// 操作类型：申请延时
				message.setCreateUser(Constants.COMPLAINT_ADMIN);
				// 创建时间
				message.setCreateDate(new Date());
				String todoContent = MessageFormat.format(
						Constants.RETURNCOMPLAINT_TODO_MESSAGE,
						new Object[] { complaint.getDealbill() });
				message.setIshaveinfo(todoContent);
				message.setTasktype(Constant.COMPLAINT_REALTIME_REPORT_RETURN);
				// 保存最新类型
				messageManager.addMessage(message);
			}
		}
	}

	/**
	 * 
	 * <p>
	 * Description:处理上报退回待办信息<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2012-7-2
	 * @param
	 * @return void
	 */
	private void processReturnComplaintMessage() {
		// 删除已有类型
		messageManager.deleteMessageByType(Constant.COMPLAINT_REPORT_RETURN);
		ComplaintSearchCondition condition = new ComplaintSearchCondition();
		// 工单上报退回
		condition.setProstatus(Constants.COMPLAINT_STATUS_REPORT_RETURNED);
		// 上报退回待办信息
		List<Message> list = complaintService
				.inquireAllUserComplaintResultCount(condition);
		if (null != list && list.size() > 0) {
			for (Message message : list) {
				// 操作类型：申请延时
				message.setCreateUser(Constants.COMPLAINT_ADMIN);
				// 创建时间
				message.setCreateDate(new Date());
				// 工单功能类型
				message.setTasktype(Constant.COMPLAINT_REPORT_RETURN);
				// 保存最新类型
				messageManager.addMessage(message);
			}
		}
	}

	/**
	 * 
	 * <p>
	 * Description:处理部门工单待办信息<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2012-7-2
	 * @param
	 * @return void
	 */
	private void processTaskComplaintMessage() {
		// 删除已有类型
		messageManager.deleteMessageByType(Constant.COMPLAINT_TASK);
		// 工单查询条件
		ComplaintSearchCondition condition = new ComplaintSearchCondition();
		// 封装部门状态查询条件List
		List prostatusList = packageTaskProstatusList();
		condition.setProstatusList(prostatusList);
		// 部门工单待办信息
		List<TaskComplaintCount> list = complaintService
				.inquireAllTaskComplaintsCount(condition);
		// 部门工单待办信息 不为空的话
		if (null != list && list.size() > 0) {
			Message message = null;
			for (TaskComplaintCount taskComplaintCount : list) {
				message = new Message();
				// 操作类型：申请延时
				message.setCreateUser(Constants.COMPLAINT_ADMIN);
				// 创建时间
				message.setCreateDate(new Date());
				message.setTasktype(Constant.COMPLAINT_TASK);
				message.setTaskcount(taskComplaintCount.getTaskcount());
				// 部门id
				message.setDeptId(taskComplaintCount.getProcessId());
				// 保存最新类型
				messageManager.addMessage(message);
			}
		}
		// 部门工单个人待办信息
		List<TaskComplaintCount> userlist = complaintService
				.inquireAllUserTaskComplaintsCount(condition);
		// 部门工单个人待办信息 不为空的话
		if (null != userlist && userlist.size() > 0) {
			Message message = null;
			for (TaskComplaintCount userTaskComplaintCount : userlist) {
				message = new Message();
				// 操作类型：申请延时
				message.setCreateUser(Constants.COMPLAINT_ADMIN);
				// 创建时间
				message.setCreateDate(new Date());
				message.setTasktype(Constant.COMPLAINT_TASK);
				message.setTaskcount(userTaskComplaintCount.getTaskcount());
				// 用户id
				message.setUserid(userTaskComplaintCount.getProcessId());
				// 保存最新类型
				messageManager.addMessage(message);
			}
		}
	}

	/**
	 * 
	 * <p>
	 * Description:封装部门状态查询条件List<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2012-7-2
	 * @param
	 * @return void
	 */
	private List packageTaskProstatusList() {
		List prostatusList = new ArrayList();
		// 查询条件加入待反馈
		prostatusList.add(Constants.COMPLAINT_STATUS_WAITE_RESPONSE);
		// 查询条件加入部分部门退回，从处理过来的
		prostatusList
				.add(Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_PROCESS);
		// 查询条件加入部分部门退回，从审批过来的
		prostatusList
				.add(Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_APPROVAL);
		// 查询条件加入部分部门退回，从升级过来的
		prostatusList
				.add(Constants.COMPLAINT_DEPT_STATUS_DEPT_RETURNED_FROM_UPGRADE);
		// 查询条件加入工单申请延时,工单来自处理
		prostatusList.add(Constants.COMPLAINT_APPLY_DELAY_TO_PROCESS);
		// 查询条件加入工单申请延时,工单来自审批
		prostatusList.add(Constants.COMPLAINT_APPLY_DELAY_TO_APPROVAL);
		// 查询条件加入工单申请延时,工单来自升级
		prostatusList.add(Constants.COMPLAINT_APPLY_DELAY_TO_UPGRADE);
		// 查询条件加入反馈未解决
		prostatusList.add(Constants.COMPLAINT_PROCESS_STATUS_UNRESOVLE);
		// 返回结果集
		return prostatusList;
	}

	/**
	 * 
	 * <p>
	 * Description:处理待办工单待办信息<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2012-7-2
	 * @param
	 * @return void
	 */
	private void processWaitProccesComplaintMessage() {
		// 删除已有类型
		messageManager.deleteMessageByType(Constant.COMPLAINT_WORKBENCH);
		// 取当前用户可处理的投诉状态列表,主要判断是否为投诉经理与品牌工关部
		Map<String, List<String>> status = this
				.getCurrentUserCanProccessStatus();
		Map<String, Object> returnMap = new HashMap<String, Object>();
		List<String> commonStatus = status.get("commonStatus");
		List<String> feedbackStatus = status.get("feedbackStatus");
		List<String> specialStatus = new ArrayList<String>();
		// 投诉升级
		specialStatus.add(Constants.COMPLAINT_STATUS_UPGRADED);
		// 投诉待审批
		specialStatus.add(Constants.COMPLAINT_STATUS_WAIT_APPROVAL);
		// 待办工单待办信息
		List<Message> list = complaintService.inquireAllUserWaitProcessCount(
				commonStatus, specialStatus, feedbackStatus);
		// 待办工单待办信息 不为空的话
		if (null != list && list.size() > 0) {
			for (Message message : list) {
				// 操作类型：申请延时
				message.setCreateUser(Constants.COMPLAINT_ADMIN);
				// 创建时间
				message.setCreateDate(new Date());
				// COMPLAINT_WORKBENCH
				message.setTasktype(Constant.COMPLAINT_WORKBENCH);
				// 保存最新类型
				messageManager.addMessage(message);
			}
		}
	}

	/**
	 * 
	 * <p>
	 * Description:处理工单回访待办信息<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2012-7-2
	 * @param
	 * @return void
	 */
	private void processVerifyTaskMessage() {
		// 删除已有类型
		messageManager.deleteMessageByType(Constant.COMPLAINT_VERIFY);
		// 查询条件
		ComplaintSearchCondition condition = new ComplaintSearchCondition();
		// 反馈已解决
		condition.setProstatus("'" + Constants.COMPLAINT_PROCESS_STATUS_RESOVLE
				+ "','" + Constants.COMPLAINT_PROCESS_STATUS_EXPIRED + "','"
				+ Constants.COMPLAINT_STATUS_REVISEIT + "'");
		// 工单回访待办信息
		List<Message> list = complaintService
				.inquireAllUserVeryfiyComplaintsCount(condition);
		if (null != list && list.size() > 0) {
			for (Message message : list) {
				// 操作类型：申请延时
				message.setCreateUser(Constants.COMPLAINT_ADMIN);
				// 创建时间
				message.setCreateDate(new Date());
				message.setTasktype(Constant.COMPLAINT_VERIFY);
				// 保存最新类型
				messageManager.addMessage(message);
			}
		}
	}

	/**
	 * <p>
	 * Description: 查询基础资料处理结果根据层级编号<br />
	 * basciLevelId : 层级编号
	 * </p>
	 * 
	 * @author hpf
	 * @version 0.1 2012-5-8
	 * @param condition
	 * @return List<ProcResult>
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<ProcResult> searchProcresultsByLevelId(BigDecimal basciLevelId) {
		// 根据id查询
		return procResultService.getProcResultByLevelId(basciLevelId);
	}

	/**
	 * @Title: getResultById
	 * @Description: TODO(根据Id查询任务部门处理结果)
	 * @param @param resultId
	 * @param @return 设定文件
	 * @return Result 返回类型
	 * @throws
	 * @see <a href="package.html#section">查看流程图</a>
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Result getResultById(BigDecimal resultId) {
		// TODO Auto-generated method stub
		return resultService.getResultById(resultId);
	}

	/**
	 * 
	 * <p>
	 * Description:待办工单 回访结果查询<br />
	 * </p>
	 * 
	 * @author ouy
	 * @version 0.1 2012-5-24
	 * @param compId
	 * @return List<Result>
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Result> searchReturnResultInfoByCompId(BigDecimal compId) {
		// 待办工单 回访结果查询
		return resultService.searchReturnResultInfoByCompId(compId);
	}

	/**
	 * 
	 * <p>
	 * Description:提供给外部（360）的接口<br />
	 * </p>
	 * 
	 * @author ouy
	 * @version 0.1 2012-6-12
	 * @param condition
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String, Object> searchComplaintIncludeSatisfy(
			ComplaintSearchCondition condition) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		// 给360提供接口
		List<Complaint> list = complaintService
				.getComplaintIncludeSatisfy(condition);
		Integer count = complaintService.getComplaintResultCount(condition);
		map.put(complaintListStr, list);
		map.put("count", count);
		return map;
	}

	/**
	 * <p>
	 * Description: 删除业务类型数据<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2012-7-10
	 * @param basciLevelList
	 *            void
	 */
	@Override
	public void deleteBasciLevelType(List<BasciLevelView> basciLevelList) {
		for (BasciLevelView basciLevelView : basciLevelList) {
			// 根据id删除业务类型
			basciLevelService.deleteBasicTypeById(basciLevelView.getChildId());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.crm.module.complaint.server.manager.IComplaintManager#
	 * deleteBasciLevel(java.util.List)
	 */
	/**
	 * <p>
	 * Description: 删除业务范围数据<br />
	 * </p>
	 * 
	 * @author ZhuPJ
	 * @version 0.1 2012-7-6
	 * @param basciLevelList
	 *            void
	 */
	@Override
	public void deleteBasciLevel(BasciLevel basciLevel) {
		// 删除时限数据
		/* procResultService.deleteProcResultByParentId(ids); */
		// 删除类型数据
		basciLevelService.deleteBasciLevelById(basciLevel.getFid().intValue());
	}

	/**
	 * <p>
	 * Description:根据单据ID查询所有的调查建议<br />
	 * </p>
	 * 
	 * @author 邢悦
	 * @version 0.1 2012-7-18
	 * @param condition
	 * @return List<WorkOrder>
	 */
	public List<WorkOrder> getInvestigateSuggestionByCompId(
			BigDecimal complaintId) {
		// 根据工单ID获得所有调查建议的操作记录
		return workOrderService.getAllSuggestionsByComplaintId(complaintId);
	}

	/**
	 * <p>
	 * Description:校验工单是否超时（用于工单接入做处理的时候）<br/>
	 * </p>
	 * 
	 * @author 邢悦
	 * @version 0.1 2012-8-1
	 * @param List
	 *            <WorkOrder>
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void isComplaintAccessExpired(String complaintId, User user) {
		Date now = new Date();
		// 根据ID查询工单信息
		Complaint complaint = complaintService.getComplaintById(complaintId);
		// 锁定用户
		BigDecimal lockUserId = complaint.getLockingUserId();
		// 员工id
		BigDecimal userIdBig = new BigDecimal(user.getEmpCode().getId());
		// 工单锁定时间
		Date lockingTime = complaint.getLockingTime();
		if (!now.after(lockingTime)) {
			// 在锁定时间内
			if (userIdBig.equals(lockUserId)) {
				// 如果锁定人是自己，则没被别人接入，
				return;
			}
		} else {
			// 在锁定时间外
			return;
		}
		// 工单接入超时
		BusinessException error = new ComplaintException(
				ComplaintExceptionType.COMPLAINT_ACCESS_EXPIRED);
		throw new GeneralException(error.getErrorCode(), error.getMessage(),
				error, error.getErrorArguments()) {
		};

	}

	/**
	 * <p>
	 * Description:保存需要发送的短信通知到本地数据库 <br/>
	 * </p>
	 * 
	 * @author
	 * @version 0.1 2012-8-1
	 * @param void
	 */
	private void saveCellphoneMsg(List<Bulletin> bulletinList,
			Complaint complaint, String feedbackLimit, String processLimit,
			User currentUser) {
		// 所有工单都需要发短信
		List<CellphoneMessageInfo> cellphoneMessageInfoList = MessageSendingUtil
				.convertToMsgInfo(complaint, bulletinList, feedbackLimit,
						processLimit, currentUser.getEmpCode().getEmpCode());
		// 批量保存短信信息
		cellphoneMessageInfoService
				.insertCellphoneMsgInfoList(cellphoneMessageInfoList);
	}

	/**
	 * <p>
	 * Description:处理短信的方法（供定时器调用）-旧<br/>
	 * </p>
	 * 
	 * @author
	 * @version 0.1 2012-8-1
	 * @param void
	 */
	@Transactional
	public void processcellphoneMsg() {
		// 短信处理接口-新，当接口更新后，可以使用此代码，同时废弃旧代码
		this.processcellphoneMsgNew();
		// this.processcellphoneMsgNew();
		// boolean result=true;
		// // 通知对象短信通知临时表
		// CellphoneMessageInfo cellphoneMessageInfoCMD=new
		// CellphoneMessageInfo();
		// cellphoneMessageInfoCMD.setPerCount(smsCount);
		// //查询未发送的记录
		// List<CellphoneMessageInfo>
		// infos=cellphoneMessageInfoService.inquireCellphoneMsgInfo(cellphoneMessageInfoCMD);
		// List<SmsInfo> smsInfos=MessageSendingUtil.convertToSmsInfos(infos);
		// try {
		// //发送短信
		// MessageSendingUtil.sendMessage(smsSender, smsInfos);
		// } catch (CrmBusinessException e) {
		// log.error("Complaint sms err: \n\t"+e.getMessage());
		// result=false;
		// }
		// //如果短信发送成功，则更新这些短信的状态为已发送
		// if(result){
		// for(CellphoneMessageInfo info:infos){
		// info.setIsSended(1);
		// // 根据id更新记录
		// cellphoneMessageInfoService.updateCellphoneMsgInfoInfo(info);
		// }
		// }
	}

	/**
	 * <p>
	 * Description: 短信处理接口-新<br />
	 * </p>
	 * 
	 * @author ZhuPJ
	 * @version 0.1 2012-11-5 void
	 */
	// 新短信功能注释
	@Transactional
	public void processcellphoneMsgNew() {
		/**
		 * 短信批量发送处理。 每次从数据库中查询smsCount 条短信
		 */
		boolean result = true;
		CellphoneMessageInfo cellphoneMessageInfoCMD = new CellphoneMessageInfo();
		cellphoneMessageInfoCMD.setPerCount(smsCount);
		List<CellphoneMessageInfo> infos = cellphoneMessageInfoService
				.inquireCellphoneMsgInfo(cellphoneMessageInfoCMD);

		// 短信发送，每次仅能发送SMS_SIZE 条
		int q = infos.size() / SMS_SIZE; // 商
		int r = infos.size() % SMS_SIZE; // 余数
		CellphoneMessageInfo[] cmi = null;
		/**
		 * 根据SMS_SIZE处理短信。 通过商、余数进行批量处理。
		 */
		for (int j = 0; j <= q; j++) {
			if (j != q) {
				// 每次处理SMS_SIZE条短信（未处理短信数 > SMS_SIZE时）
				cmi = new CellphoneMessageInfo[SMS_SIZE];
				System.arraycopy(infos.toArray(), j * SMS_SIZE, cmi, 0,
						SMS_SIZE);
			} else {
				// 每次处理r条短信（未处理短信数 < SMS_SIZE 时）
				cmi = new CellphoneMessageInfo[r];
				System.arraycopy(infos.toArray(), j * SMS_SIZE, cmi, 0, r);
			}
			List<SmsInformation> smsInfos = MessageSendingUtil
					.convertToSmsInformation(cmi);
			try {
				// 发送短信
				MessageSendingUtil.sendMessage(smsInfoSender, smsInfos);
			} catch (CrmBusinessException e) {
				log.error("Complaint sms err: \n\t" + e.getMessage());
				result = false;
			}
			// 如果短信发送成功，则更新这些短信的状态为已发送
			if (result) {
				List<String> ids = new ArrayList<String>();
				for (CellphoneMessageInfo info : cmi) {
					ids.add(info.getId());
				}
				if (!CollectionUtils.isEmpty(ids)) {
					cellphoneMessageInfoService
							.updateCellphoneMsgInfoInfoAll(ids);
				}
			}
		}
	}

	/**
	 * <p>
	 * Description:校验反馈登记是否过期（true：未过期，false：过期）<br />
	 * </p>
	 * 
	 * @author ZhuPJ
	 * @version 0.1 2012-10-12
	 * @param complaintId
	 * @param user
	 *            void
	 */
	@Override
	public void isComplaintFeedbackOverTime(String resultId) {
		// 校验反馈登记是否过期
		Result result = resultService.getResultById(new BigDecimal(resultId));
		// 超过反馈期的抛出异常
		ComplaintValidate.canPassFeedback(result);
	}

	// complaintOperate get方法
	public IComplaintOperate getComplaintOperate() {
		return complaintOperate;
	}

	// waybillOperate get方法
	public IWaybillOperate getWaybillOperate() {
		return waybillOperate;
	}

	// waybillOperate set方法
	public void setWaybillOperate(IWaybillOperate waybillOperate) {
		this.waybillOperate = waybillOperate;
	}

	// complaintOperate set方法
	public void setComplaintOperate(IComplaintOperate complaintOperate) {
		this.complaintOperate = complaintOperate;
	}

	// messageManager set方法
	public void setMessageManager(IMessageManager messageManager) {
		this.messageManager = messageManager;
	}

	// oaAccidentOperate get方法
	public IOaAccidentOperate getOaAccidentOperate() {
		return oaAccidentOperate;
	}

	// oaAccidentOperate set方法
	public void setOaAccidentOperate(IOaAccidentOperate oaAccidentOperate) {
		this.oaAccidentOperate = oaAccidentOperate;
	}

	// complaintInfoService set方法
	public void setComplaintInfoService(
			IComplaintInfoService complaintInfoService) {
		this.complaintInfoService = complaintInfoService;
	}

	// cellphoneMessageInfoService get方法
	public ICellphoneMessageInfoService getCellphoneMessageInfoService() {
		return cellphoneMessageInfoService;
	}

	// cellphoneMessageInfoService set方法
	public void setCellphoneMessageInfoService(
			ICellphoneMessageInfoService cellphoneMessageInfoService) {
		this.cellphoneMessageInfoService = cellphoneMessageInfoService;
	}

	// smsSender get方法
	/**
	 * @return smsInfoSender : return the property smsInfoSender.
	 */
	public ISmsInfoSender getSmsInfoSender() {
		return smsInfoSender;
	}

	// smsSender set方法
	/**
	 * @param smsInfoSender
	 *            : set the property smsInfoSender.
	 */
	public void setSmsInfoSender(ISmsInfoSender smsInfoSender) {
		this.smsInfoSender = smsInfoSender;
	}

	/**
	 * <p>
	 * Description: 新基础资料--查询基础资料数据<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-2-22
	 * @param bsc
	 * @param rb
	 */
	public Map<String, Object> searchBasicInfo(BasicSearchCondition bsc,
			int start, int limit) {
		// 查询基础资料总计
		int totalCount = basciLevelService.searchCountBasicInfo(bsc);

		List<BasicInfo> basicInfos = null;
		if (totalCount > 0) {
			// 查询基础资料
			basicInfos = basciLevelService.searchBasicInfo(bsc, start, limit);
		}
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("totalCount", totalCount);
		retMap.put("basicInfos", basicInfos);
		return retMap;
	}

	/**
	 * <p>
	 * Description: 新基础资料--查询出业务范围及对应的业务类型用于修改基础资料页面<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param BasicBusScopeVO
	 */
	public List<BasicBusScopeVO> searchBasicBusScopeVO(BasicBusScopeVO bbs) {
		// 检查查询条件字段是否合法
		BasicInfoValidator.checkBasicBusScopeVOBySearch(bbs);
		// 调用相应的查询
		return basciLevelService.searchBasicBusScopeVO(bbs);
	}

	/**
	 * <p>
	 * Description: 新基础资料--新增业务项<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param basicItems
	 * @param user
	 */
	public boolean operateBusItem(List<BasicInfo> basicItems, User user) {

		// 检查业务项列表是否合法
		BasicInfoValidator.checkSaveBasicItem(basicItems);
		// 查询所有的业务项
		List<BasicInfo> orgBusItems = basciLevelService
				.searchBusItemByType(null);
		// 调用方法得到需要修改或需要新增的业务项集合
		List<BasicLevel> basicLevels = BasicInfoUtils
				.getDelOrUpdItemListFromBusItemList(basicItems, orgBusItems,
						user);
		// 循环业务项集合
		for (BasicLevel basLev : basicLevels) {
			if (basLev.getId() == null || "".equals(basLev.getId())) {
				basciLevelService.saveBasicLevel(basLev);
			} else {
				basciLevelService.updateBasicLevelById(basLev);
			}
		}
		return true;
	}

	/**
	 * <p>
	 * Description: 新基础资料--业务项修改,业务范围新增与修改,业务类型新增修改<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param basicBusScope
	 * @param user
	 */
	public boolean operateBasicBusInfo(BasicBusScopeVO basicBusScope, User user) {
		// 检查实体是否合法
		BasicInfoValidator.checkBusScopeVOInsert(basicBusScope);
		// 如果业务项名称修改过
		if ((null != basicBusScope.getBusItemName() && !"".equals(basicBusScope
				.getBusItemName()))
				|| (null != basicBusScope.getReportType() && !""
						.equals(basicBusScope.getReportType()))) {
			// 将basicBusScope转换为basicLevel
			BasicLevel busItem = BasicInfoUtils
					.busScopeVOItemConvertToBasicLevel(basicBusScope, user);
			// 更新数据库
			basciLevelService.updateBasicLevelById(busItem);
		}
		// 如果业务类型修改过
		if (null != basicBusScope.getReportType()
				&& !"".equals(basicBusScope.getReportType())) {
			BasicLevel busItem = BasicInfoUtils
					.busScopeVOConvertTOBasicLevelUpdByParentId(basicBusScope,
							user);
			basciLevelService.updateBasicLevelByParentId(busItem);
		}
		// 如果业务范围ID为空且业务范围名称不为空就新增业务范围
		if (null != basicBusScope.getBusScopeName()
				&& !"".equals(basicBusScope.getBusScopeName())
				&& (null == basicBusScope.getBusScopeId() || ""
						.equals(basicBusScope.getBusScopeId()))) {
			BasicLevel basLev = BasicInfoUtils.busScopeConvertToBasicLevel(
					basicBusScope, user);
			// 将业务范围插入到数据库中
			basciLevelService.saveBasicLevel(basLev);
			// 更新basicBusScope中的busScopeId字段
			basicBusScope.setBusScopeId(basLev.getId());
			// 当业务范围ID不为空且业务范围名称不为空就修改业务范围
		} else if (null != basicBusScope.getBusScopeName()
				&& !"".equals(basicBusScope.getBusScopeName())
				&& null != basicBusScope.getBusScopeId()
				&& !"".equals(basicBusScope.getBusScopeId())) {
			BasicLevel basLev = BasicInfoUtils.busScopeConvertToBasicLevel(
					basicBusScope, user);
			// 将业务范围更新到数据库中
			basciLevelService.updateBasicLevelById(basLev);
		}
		// 业务类型列表不为空
		if (basicBusScope.getBusTypes() != null
				&& basicBusScope.getBusTypes().size() != 0) {
			for (BasicBusType busType : basicBusScope.getBusTypes()) {
				// 将BusType中的数据补齐
				busType = BasicInfoUtils.convertBusType(basicBusScope, busType,
						user);
				// 如果业务类型ID为空则插入到数据库中
				if (null == busType.getId() || "".equals(busType.getId())) {
					// BasicInfoValidator.checkBusTypeSave(busType);
					procResultService.saveBusType(busType);
				} else {
					procResultService.updateBusType(busType);
				}
			}
		}
		return true;
	}

	// complaintService get方法
	public IComplaintService getComplaintService() {
		return complaintService;
	}

	// complaintService set方法
	public void setComplaintService(IComplaintService complaintService) {
		this.complaintService = complaintService;
	}

	// feedBackReasionService get方法
	public IFeedBackReasionService getFeedBackReasionService() {
		return feedBackReasionService;
	}

	// feedBackReasionService set方法
	public void setFeedBackReasionService(
			IFeedBackReasionService feedBackReasionService) {
		this.feedBackReasionService = feedBackReasionService;
	}

	// memberManager get方法
	public IMemberManager getMemberManager() {
		return memberManager;
	}

	// memberManager set方法
	public void setMemberManager(IMemberManager memberManager) {
		this.memberManager = memberManager;
	}

	// orderManager get方法
	public IOrderManager getOrderManager() {
		return orderManager;
	}

	// orderManager set方法
	public void setOrderManager(IOrderManager orderManager) {
		this.orderManager = orderManager;
	}

	// employeeService get方法
	public IEmployeeService getEmployeeService() {
		return employeeService;
	}

	// employeeService set方法
	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	// departmentService get方法
	public IDepartmentService getDepartmentService() {
		return departmentService;
	}

	// departmentService set方法
	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	// procResultService get方法
	public IProcResultService getProcResultService() {
		return procResultService;
	}

	// procResultService set方法
	public void setProcResultService(IProcResultService procResultService) {
		this.procResultService = procResultService;
	}

	// userService get方法
	public IUserService getUserService() {
		return userService;
	}

	// userService set方法
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	// bulletinService get方法
	public IBulletinService getBulletinService() {
		return bulletinService;
	}

	// bulletinService set方法
	public void setBulletinService(IBulletinService bulletinService) {
		this.bulletinService = bulletinService;
	}

	// basciLevelService get方法
	public IBasciLevelService getBasciLevelService() {
		return basciLevelService;
	}

	// basciLevelService set方法
	public void setBasciLevelService(IBasciLevelService basciLevelService) {
		this.basciLevelService = basciLevelService;
	}

	// resultService get方法
	public IResultService getResultService() {
		return resultService;
	}

	// resultService set方法
	public void setResultService(IResultService resultService) {
		this.resultService = resultService;
	}

	// workOrderService get方法
	public IWorkOrderService getWorkOrderService() {
		return workOrderService;
	}

	// workOrderService set方法
	public void setWorkOrderService(IWorkOrderService workOrderService) {
		this.workOrderService = workOrderService;
	}

	/**
	 * <p>
	 * Description: 新基础资料--删除业务类型<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param id
	 */
	public boolean deleteBusTypeByIds(List<BasicInfo> basicInfos) {
		// 获得要删除列表的业务类型ID列表
		List<String> busTypeIds = BasicInfoUtils
				.getBusTypeIdsFromBasicInfo(basicInfos);
		// 检查业务类型ID列表是否为空
		BasicInfoValidator.checkIdList(busTypeIds);
		// 调用删除业务类型方法
		procResultService.deleteBusTypeById(busTypeIds);
		// 获取要删除列表的业务范围ID列表
		List<String> busScopeIds = BasicInfoUtils
				.getBusScopeIdsFromBasicInfo(basicInfos);
		// 检查业务范围ID列表是否为空
		BasicInfoValidator.checkIdList(busScopeIds);
		// 循环删除没有业务类型的业务范围
		for (String busScopeId : busScopeIds) {
			basciLevelService.deleteBusScopeById(busScopeId);
		}
		return true;
	}

	/**
	 * <p>
	 * Description: 新基础资料--删除业务类型在操作基础资料页面中使用<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param id
	 */
	public boolean deleteBusTypeByIdsInBusScopeVO(List<BasicBusType> busTypes) {
		// 获得要删除列表的业务类型ID列表
		List<String> busTypeIds = BasicInfoUtils
				.getBusTypeIdsFromBasicBusScopeVO(busTypes);
		// 检查业务类型ID列表是否为空
		BasicInfoValidator.checkIdList(busTypeIds);
		// 调用删除业务类型方法
		procResultService.deleteBusTypeById(busTypeIds);
		return true;
	}

	/**
	 * <p>
	 * Description: 新基础资料--删除业务项<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param id
	 */
	public boolean deleteBusItemByIds(List<BasicInfo> basicInfos) {
		// 获得要删除业务项ID列表
		List<String> busItemIds = BasicInfoUtils
				.getBusItemIdsFromBasiInfo(basicInfos);
		// 检查该列表是否为空
		BasicInfoValidator.checkIdList(busItemIds);
		// 调用相应方法删除业务项
		basciLevelService.deleteBusItemByIds(busItemIds);
		// 调用方法删除这些业务项包含的所有业务类型
		procResultService.deleteBusTypeByBusItem(busItemIds);
		return true;
	}

	/**
	 * <p>
	 * Description: 新基础资料--根据上报类型查询业务项<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param BasicBusScopeVO
	 * @param BasicBusScopeVO
	 */
	public List<BasicInfo> searchBusItemByType(String reportType) {
		return basciLevelService.searchBusItemByType(reportType);
	}

	/**
	 * <p>
	 * Description: 新基础资料--删除添加业务项页面用于显示已存在的业务项<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param BasicBusScopeVO
	 * @param BasicBusScopeVO
	 */
	public Map<String, List<BasicInfo>> showBusItemByType() {
		Map<String, List<BasicInfo>> map = new HashMap<String, List<BasicInfo>>();
		// 查询投诉的业务项
		List<BasicInfo> complaints = basciLevelService
				.searchBusItemByType(BasicInfoConstants.BASICCOMPTYPECOMPAINT);
		// 查询异常的业务项
		List<BasicInfo> unusuals = basciLevelService
				.searchBusItemByType(BasicInfoConstants.BASICCOMPTYPEUNUSUAL);
		map.put("complaints", complaints);
		map.put("unusuals", unusuals);
		return map;
	}

	/**
	 * <p>
	 * Description: 新基础资料--根据业务项ID查询业务范围<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param busItemId
	 */
	public List<BasicInfo> searchBusScopeByBusItemId(String busItemId) {
		return basciLevelService.searchBusScopeByBusItemId(busItemId);
	}

	/**
	 * <p>
	 * Description: 新基础资料根据业务范围ID查询业务类型<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-2-25
	 * @param busScopeId
	 * @return List<BasicBusType>
	 */
	public List<BasicInfo> searchBusTypeByBusScope(String busScopeId) {
		return procResultService.searchBusTypeByBusScope(busScopeId);
	}

	/**
	 * <p>
	 * Description: 新基础资料--根据ID修改业务项<br />
	 * </p>
	 * 
	 * @author ZhouYuan
	 * @version 0.1 2013-2-25
	 * @param basicInfo
	 * @return List<BasicBusType>
	 */
	public boolean updateBusItemById(BasicInfo basicInfo, User user) {
		// 检查业务项数据是否合法
		BasicInfoValidator.checkBusItemForUpdate(basicInfo);
		// 将基础资料字段转换为BasicLevel用于更新
		BasicLevel basLev = BasicInfoUtils
				.basicInfoItemConvertToBasicLevelForUpdate(basicInfo, user);
		// 更新业务项
		basciLevelService.updateBasicLevelById(basLev);
		return true;

	}

	/**
	 * 
	 * <p>
	 * Description:官网投诉工单流入CRM提供保存接口<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-7-5
	 * @param complaint
	 * @return 需要传入参数 来电人 及类型 sendorreceive 来电人 --compman 联系电话 tel 凭证号 bill 上报内容
	 *         reportcontent 客户要求 custrequire
	 * 
	 * 
	 */
	@Override
	public boolean submitComplaintForOnline(Complaint complaint) {
		Integer compId = null;
		FossWaybillInfo waybill = null;
		// 如果凭证号为运单号的查询出出发部门id及出发部门名字
		if (!StringUtil.isEmpty(complaint.getBill())) {
			waybill = complaintService.findWaybillByNO(complaint.getBill());
			if (null != waybill) {
				// 查询出对应的部门
				Department leaveDept = departmentService
						.getDeptByStandardCode(waybill.getReceiveDeptNumber());
				// 设置出发部门id
				complaint.setLeaveDept(leaveDept.getId());
				// 设置出发部门名字
				complaint.setLeaveDeptName(leaveDept.getDeptName());
				if (null == complaint.getCompmanid()) {
					Member member = memberManager.getMemberByCustNumber(waybill
							.getSenderNumber());
					if (null != member) {
						complaint.setCustlevel(member.getDegree());
					}
				}
			}
		}
		Order order = orderManager.getOrderByNumber(complaint.getBill());
		String businessModel = getBusinessModel(order, waybill);
		if(StringUtil.isEmpty(businessModel)){
			businessModel=Constants.BUSINESS_MODEL_UNEXPRESS;
		}
		// TODO 是否有默认值
		complaint.setBusinessModel(businessModel);
		// 创建时间
		complaint.setCreatetime(new Date());
		// 最后更新时间
		complaint.setLastupdatetime(new Date());
		// 处理编号
		complaint.setDealbill(generateDealBill());
		// TODO
		// 报告人
		complaint.setReporter("系统管理员");
		// TODO
		// 上报人ID
		complaint.setReporterid(BigDecimal.valueOf(86301));
		// TODO
		// 工单来源
		complaint.setComplaintsource("NETWORK");
		// 优先级
		complaint.setPririty("NORMAL");
		// TODO
		complaint.setFeedback("0");
		// 工单类型
		complaint.setReporttype("COMPLAINT");
		complaint.setTilimitcycle("MINUTE");
		complaint.setTimelimit(BigDecimal.valueOf(0));
		// 报告时间
		complaint.setReporttime(new Date());
		// 创建用户
		complaint.setCreateuserid(BigDecimal.valueOf(86301));
		// 最后修改用户
		complaint.setLastmodifyuserid(BigDecimal.valueOf(86301));
		// 待处理
		complaint.setProstatus(Constants.COMPLAINT_STATUS_PENDING);
		// 未解决
		complaint.setDealstatus(Constants.COMPLAINT_RESOVLE_STATUS_UNRESOVLE);
		// 重复工单码
		complaint.setRecomcode("");
		// 重复绑定码
		complaint.setRebindno("");

		// 保存工单
		compId = complaintService.saveComplaint(complaint);
		return true;
	}

	/**
	 * 工单处理，当出发部门不为任务部门时，生成短信通知出发部门经理和副经理维护客户以及生成一条待办事宜到出发部门
	 * 
	 * @param resultList
	 *            处理结果
	 * @param complaint
	 *            工单信息
	 */
	@Override
	public void dealComplaint(List<Result> resultList, Complaint complaint) {
		boolean isDeal = true;
		// 出发部门
		Complaint comp = complaintService.getComplaintById(complaint.getFid()
				.toString());
		if (comp != null) {
			// 工单类型为异常时，不通知出发部门
			if (Constants.COMPLAINT_REPORTTYE_EXCEPTION.equals(complaint
					.getReporttype().trim())) {
				return;
			}
		}
		String leaveDept = comp.getLeaveDept() == null ? "0" : comp
				.getLeaveDept();
		BigDecimal leaveDeptId = new BigDecimal(leaveDept);
		String leaveDeptName = comp.getLeaveDeptName() == null ? "" : comp
				.getLeaveDeptName();

		User user = (User) UserContext.getCurrentUser();
		String senderEmpCode = user.getEmpCode().getEmpCode();
		// 单号
		String bill = complaint.getBill();
		// 来电人
		String compMan = complaint.getCompman();

		if (resultList != null) {
			for (int i = 0; i < resultList.size(); i++) {
				Result result = (Result) resultList.get(i);
				if (result != null && complaint != null) {
					// 任务部门
					BigDecimal taskpartmentid = result.getTaskpartmentid() == null ? new BigDecimal(
							0) : result.getTaskpartmentid();
					String taskDeptName = result.getFtaskDeptName() == null ? ""
							: result.getFtaskDeptName();

					if ((taskpartmentid.compareTo(leaveDeptId) == 0 && taskDeptName
							.equals(leaveDeptName))
							|| Integer.parseInt(leaveDeptId.toString()) == 0
							|| "".equals(leaveDeptName)) {
						isDeal = false;
					}
				}
			}

			if (isDeal) {
				// 判断：当出发部门不为任务部门时，生成短信通知出发部门经理和副经理维护客户
				processSendMsg(leaveDeptId, leaveDeptName, bill, compMan,
						senderEmpCode);
				// 判断：当出发部门不为任务部门时，生成一条待办事项到出发部门
				// addTodoMessage(bill, leaveDeptId.toString());

				Result rs = new Result();
				rs.setCreatetime(new Date());
				rs.setLastupdatetime(new Date());
				// 用户id
				rs.setComplainid(complaint.getFid());
				// 创建人id
				rs.setCreateuserid(Integer.valueOf(user.getEmpCode().getId()));
				// 处理人工号
				rs.setDealman(user.getEmpCode().getEmpCode());
				// 最后修改人工号
				rs.setLastmodifyuserid(BigDecimal.valueOf(Long.parseLong(user
						.getEmpCode().getId())));
				rs.setDealmatters(resultList.get(0).getDealmatters());
				rs.setTaskpartmentid(leaveDeptId);
				rs.setTaskproperties("feedback_people");
				rs.setFeedtimelimit(resultList.get(0).getFeedtimelimit());
				rs.setProcesstimelimit(resultList.get(0).getProcesstimelimit());
				rs.setFtaskDeptName(leaveDeptName);
				rs.setDealType("department");
				rs.setJobCode(new Date().getTime() + "");
				rs.setDeptType("1"); // 出发部门
				resultService.saveLeaveDepartmentResult(rs);
			}
		}
	}

	/**
	 * 工单处理，当出发部门不为任务部门时，生成短信通知出发部门经理和副经理维护客户
	 * 
	 * @param leaveDeptId
	 *            出发部门Id
	 * @param leaveDeptName
	 *            出发部门名称
	 * @param bill
	 *            单号
	 * @param compMan
	 *            来电人姓名
	 * @param senderEmpCode
	 *            发送人
	 */
	private void processSendMsg(BigDecimal leaveDeptId, String leaveDeptName,
			String bill, String compMan, String senderEmpCode) {
		List<CellphoneMessageInfo> managerList = complaintService
				.findManagerByDeptId(leaveDeptId.toString());
		List<CellphoneMessageInfo> assistantManagerList = complaintService
				.findAssistantManagerByDeptId(leaveDeptId.toString());
		managerList.addAll(assistantManagerList);
		List<CellphoneMessageInfo> infos = new ArrayList<CellphoneMessageInfo>();
		for (int i = 0; i < managerList.size(); i++) {
			CellphoneMessageInfo msgInfo = (CellphoneMessageInfo) managerList
					.get(i);
			if (msgInfo != null) {
				String msgContent = MessageFormat
						.format(ComplaintConstants.MESSAGE_CUSTOMER_MAINTENANCE_NOTICE_CONTENT,
								new Object[] { leaveDeptName, bill, compMan });
				msgInfo.setMsgContent(msgContent);
				msgInfo.setSenderEmpCode(senderEmpCode);
				infos.add(msgInfo);
			}
		}

		// 短信发送，每次仅能发送SMS_SIZE 条
		int q = infos.size() / SMS_SIZE; // 商
		int r = infos.size() % SMS_SIZE; // 余数
		CellphoneMessageInfo[] cmi = null;
		/**
		 * 根据SMS_SIZE处理短信。 通过商、余数进行批量处理。
		 */
		for (int j = 0; j <= q; j++) {
			if (j != q) {
				// 每次处理SMS_SIZE条短信（未处理短信数 > SMS_SIZE时）
				cmi = new CellphoneMessageInfo[SMS_SIZE];
				System.arraycopy(infos.toArray(), j * SMS_SIZE, cmi, 0,
						SMS_SIZE);
			} else {
				// 每次处理r条短信（未处理短信数 < SMS_SIZE 时）
				cmi = new CellphoneMessageInfo[r];
				System.arraycopy(infos.toArray(), j * SMS_SIZE, cmi, 0, r);
			}
			List<SmsInformation> smsInfos = MessageSendingUtil
					.convertToSmsInformation(cmi);
			try {
				// 发送短信
				MessageSendingUtil.sendMessage(smsInfoSender, smsInfos);
			} catch (CrmBusinessException e) {
				log.error("Complaint sms err: \n\t" + e.getMessage());

				BusinessException error = new ComplaintException(
						ComplaintExceptionType.COMPLAINT_MESSAGE_fail);
				throw new GeneralException(error.getErrorCode(),
						error.getMessage(), error, error.getErrorArguments()) {
				};
			}
		}
	}

	private void addTodoMessage(String bill, String leaveDeptId) {
		if (leaveDeptId != null && !"".equals(leaveDeptId)) {
			List<String> userIdList = complaintService
					.findUserIdByDeptId(leaveDeptId);
			for (int i = 0; i < userIdList.size(); i++) {
				String userId = (String) userIdList.get(i);
				String todoContent = MessageFormat.format(
						ComplaintConstants.TODO_MESSAGE, new Object[] { bill });
				com.deppon.crm.module.common.shared.domain.Message message = new Message();
				message.setTasktype(ComplaintConstants.TODO_TASK_TYPE);
				message.setIshaveinfo(todoContent);
				message.setTaskcount(1);

				message.setUserid(Integer.parseInt(userId));
				// 生成代办
				complaintService.addTodoMessage(message);
			}
		}
	}

	/**
	 * 修改来自官网的工单的业务类型
	 */
	
	public Boolean changeBusinessModel(Complaint complaint) {
		return	complaintService.changeBusinessModel(complaint);

	}
}