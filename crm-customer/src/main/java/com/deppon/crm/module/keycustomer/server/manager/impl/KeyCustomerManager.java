package com.deppon.crm.module.keycustomer.server.manager.impl;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jfree.util.Log;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.bps.server.manager.IBpsWorkflowManager;
import com.deppon.crm.module.client.sync.impl.CustomerInfoLog;
import com.deppon.crm.module.common.file.util.ExcelExporter;
import com.deppon.crm.module.common.file.util.PropertiesUtil;
import com.deppon.crm.module.customer.bpsworkflow.util.BpsConstant;
import com.deppon.crm.module.customer.server.service.IAlterMemberService;
import com.deppon.crm.module.customer.server.service.IContactService;
import com.deppon.crm.module.customer.server.service.IContractService;
import com.deppon.crm.module.customer.server.service.IMemberService;
import com.deppon.crm.module.customer.server.utils.ContextUtil;
import com.deppon.crm.module.customer.shared.domain.Contact;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.domain.MemberCondition;
import com.deppon.crm.module.custrepeat.server.manager.IRepeatedCustManager;
import com.deppon.crm.module.keycustomer.server.manager.IKeyCustomerManager;
import com.deppon.crm.module.keycustomer.server.manager.KeyCustomerValidator;
import com.deppon.crm.module.keycustomer.server.service.IKeyCustomerService;
import com.deppon.crm.module.keycustomer.shared.domain.Constant;
import com.deppon.crm.module.keycustomer.shared.domain.KeyCustomer;
import com.deppon.crm.module.keycustomer.shared.domain.KeyCustomerSearchCondition;
import com.deppon.crm.module.keycustomer.shared.domain.KeyCustomerVO;
import com.deppon.crm.module.keycustomer.shared.domain.KeyCustomerWorkflowInfo;
import com.deppon.crm.module.keycustomer.shared.domain.KeyStatusVO;
import com.deppon.crm.module.sysmail.server.manager.ISysMailSendManager;
import com.deppon.crm.module.sysmail.server.manager.impl.SysMailSendManager;
import com.deppon.foss.framework.server.adapter.mail.MailInfo;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.shared.util.string.StringUtil;

/**
 * 
 * <p>
 * Description:大客户资格管理impl<br />
 * </p>
 * 
 * @title KeyCustomerManager.java
 * @package com.deppon.crm.module.keycustomer.server.manager.impl
 * @author 106138
 * @version 0.1 2014-3-4
 */
@Transactional
public class KeyCustomerManager implements IKeyCustomerManager {
	@SuppressWarnings("unused")
	// 日志记录
	private final Logger logger = Logger.getLogger(KeyCustomerManager.class);

	// 大客户service
	private IKeyCustomerService keyCustomerService;
	// 合同service
	private IContractService contractService;
	// bps工作流
	private IBpsWorkflowManager bpsWorkflowManager;
	// 修改会员service
	private IAlterMemberService alterMemberService;
	// 客户service
	private IMemberService memberService;
	// 联系人service
	private IContactService contactService;
	// 邮件发送manager
	private ISysMailSendManager sysMailSendManager;
	// 疑似重复manager
	private IRepeatedCustManager repeatedCustManager;

	/**
	 * 
	 * <p>
	 * Description:根据查询条件查询对应的大客户准入或退出列表<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-4
	 * @param condition
	 * @return List<KeyCustomerList>
	 */
	@Override
	public List<KeyCustomer> listKeyCustomerList(KeyCustomerSearchCondition condition) {
		// 1.校验查询条件是否有效,条件不能全部为空。
		KeyCustomerValidator.checkSearchCondition(condition);
		// 2.转换查询条件，若客户编码不为空,则只根据客户编码查询。

		// 若客户编码为空，联系人编码不为空，则根据联系人编码查询
		// 3.调用server查询数据
		return keyCustomerService.listKeyCustomerList(convertSearchCondition(condition));
	}

	/**
	 * 
	 * <p>
	 * Description:统计条数<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年3月26日
	 * @param condition
	 * @return
	 * 
	 */
	@Override
	public long countListKeyCustomerList(KeyCustomerSearchCondition condition) {
		// 统计条数 转换查询条件
		return keyCustomerService.countListKeyCustomerList(convertSearchCondition(condition));
	}

	/**
	 * 
	 * <p>
	 * Description:转换查询条件<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年3月26日
	 * @param condition
	 * @return KeyCustomerSearchCondition
	 */
	private KeyCustomerSearchCondition convertSearchCondition(KeyCustomerSearchCondition condition) {
		// 如果客户编码不为空，则转换查询条件为只根据客户编码查询
		// 组装查询条件
		KeyCustomerSearchCondition newCondition = new KeyCustomerSearchCondition();
		// 客户编码
		// 分页
		newCondition.setLimit(condition.getLimit());
		// 开始
		newCondition.setStart(condition.getStart());
		// 状态为有效
		newCondition.setListStatus("1");
		// 类型
		newCondition.setType(condition.getType());
		if (StringUtil.isNotEmpty(condition.getCustNum())) {
			newCondition.setCustNum(condition.getCustNum());
			return newCondition;
		} else if (StringUtil.isNotEmpty(condition.getContactNum())) {
			newCondition.setContactNum(condition.getContactNum());
			return newCondition;

		}
		condition.setListStatus("1");
		return condition;
	}

	/**
	 * 
	 * <p>
	 * Description:根据客户id查询客户近三个月发货金额<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-4
	 * @param custId
	 * @return String
	 */
	@Override
	public String findAmountOfSignMent(String custId) {
		// 客户id不为空
		if (StringUtil.isNotEmpty(custId)) {
			// 返回近三个月发货金额
			return contractService.getArrearaMountByCustId(custId);

		}
		return "";
	}

	/**
	 * 
	 * <p>
	 * Description:根据条件查询大客户的工作流历史<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-4
	 * @param condtion
	 * @return List<KeyCustomerWorkflowInfo>
	 */
	@Override
	public List<KeyCustomerWorkflowInfo> searchWorkflowList(KeyCustomerSearchCondition condition) {
		KeyCustomerValidator.checkSearchCondition(condition);
		// 2.转换查询条件，若客户编码不为空,则只根据客户编码查询。

		KeyCustomerSearchCondition newCondition = new KeyCustomerSearchCondition();
		// 状态为有效
		// 分页
		newCondition.setLimit(condition.getLimit());
		// 开始
		newCondition.setStart(condition.getStart());
		newCondition.setListStatus("1");
		if (StringUtil.isNotEmpty(condition.getCustNum())) {
			// 客户编码
			newCondition.setCustNum(condition.getCustNum());

		} else if (StringUtil.isNotEmpty(condition.getContactNum())) {
			// 客户编码
			newCondition.setContactNum(condition.getContactNum());
		} else {
			newCondition = condition;
		}

		// 若客户编码为空，联系人编码不为空，则根据联系人编码查询
		// 3.调用server查询数据
		return keyCustomerService.searchWorkflowList(newCondition);
	}

	public long countSearchWorkflowList(KeyCustomerSearchCondition condition) {
		KeyCustomerValidator.checkSearchCondition(condition);
		// 2.转换查询条件，若客户编码不为空,则只根据客户编码查询。
		KeyCustomerSearchCondition newCondition = new KeyCustomerSearchCondition();
		// 状态为有效
		newCondition.setListStatus("1");
		if (StringUtil.isNotEmpty(condition.getCustNum())) {
			// 客户编码
			newCondition.setCustNum(condition.getCustNum());
		} else if (StringUtil.isNotEmpty(condition.getContactNum())) {
			// 客户编码
			newCondition.setContactNum(condition.getContactNum());
		} else {
			newCondition = condition;
		}

		return keyCustomerService.countSearchWorkflowList(newCondition);
	}

	/**
	 * 
	 * <p>
	 * Description:根据客户id查询客户的审批历史<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-4
	 * @param custId
	 * @return List<KeyCustomerWorkflowInfo>
	 */
	@Override
	public List<KeyCustomerWorkflowInfo> findWorkflowListByCustId(String custId, int start, int limit) {
		// 根据客户id查询审批历史
		return keyCustomerService.findWorkflowListByCustId(custId, start, limit);
	}

	/**
	 * 
	 * <p>
	 * Description:据客户id查询客户的审批历史条数<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年3月26日
	 * @param custId
	 * @return
	 * 
	 */
	@Override
	public long countFindWorkflowListByCustId(String custId) {
		// 返回条数
		return keyCustomerService.countFindWorkflowListByCustId(custId);
	}

	/**
	 * 
	 * <p>
	 * Description:根据客户id对大客户进行退出处理<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-4
	 * @param custId
	 *            void
	 */
	@Transactional
	@Override
	public void exitHadlle(String custId, String deptId) {
		// 1.校验是否为归属营业部经理操作
		User user = (User) UserContext.getCurrentUser();
		KeyCustomerValidator.checkIsBelongDept(deptId, user.getEmpCode().getDeptId().getId());
		// 2.校验是否还有大客户标记
		MemberCondition condition = new MemberCondition();
		condition.setMemberId(custId);
		// 根据客户id查询客户
		List<Member> list = alterMemberService.searchMemberByCondition(condition);
		// 新建客户
		Member member = null;
		// 返回结果不为空
		if (!CollectionUtils.isEmpty(list)) {
			// 得到第一条记录
			member = list.get(0);
		}
		// 判断有标记 所以传true,有标记才能退出处理
		KeyCustomerValidator.checkIsKeyCustomer(member, false);
		// 3.校验是否存在审批中的大客户保留工作流
		List<KeyCustomerWorkflowInfo> infos = keyCustomerService.findWorkflowListByCustId(custId, 0, 0);
		// 校验
		KeyCustomerValidator.checkCustWorkflowINfoStatus(infos);
		// 封装更新的会员实体
		Member m = new Member();
		m.setId(custId);
		m.setIsKeyCustomer(false);
		alterMemberService.updateMember(m);
		// 5.更新大客户列表数据为无效
		keyCustomerService.updateKeyListStatus(custId, Constant.NUMZERO);

	}

	/**
	 * 
	 * <p>
	 * Description:大客户准入或者保留工作流申请<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-4
	 * @param info
	 *            void
	 */
	@Transactional
	@Override
	public String processWorkFlow(KeyCustomerWorkflowInfo info) {
		// 1.校验表单元素
		KeyCustomerValidator.checkKeyCustomerWorkflowInfo(info);
		// 2.校验该客户所属部门为当前申请人所在部门
		User user = (User) UserContext.getCurrentUser();
		KeyCustomerValidator.checkIsBelongDept(info.getDeptId(), user.getEmpCode().getDeptId().getId());
		// 3.校验该客户已经存在大客户标记，若存在，提示已经存在大客户标记，无法重新申请
		MemberCondition condition = new MemberCondition();
		// 设置会员id
		condition.setMemberId(info.getCustId());
		// 查询客户
		List<Member> list = alterMemberService.searchMemberByCondition(condition);
		Member member = null;
		// 集合不为空
		if (!CollectionUtils.isEmpty(list)) {
			// 得到第一条记录---因为只取基本信息
			member = list.get(0);
		}
		// 校验是否为大客户
		if (Constant.IN.equals(info.getWorkFlowType())) {
			KeyCustomerValidator.checkIsKeyCustomer(member, true);
		} else {
			KeyCustomerValidator.checkIsKeyCustomer(member, false);
		}
		// 4.校验该客户是否存在审批中的大客户工作流，若存在提示该大客户准入保留申请工作流正在审批中
		List<KeyCustomerWorkflowInfo> infos = keyCustomerService.findWorkflowListByCustId(info.getCustId(), 0, 0);
		KeyCustomerValidator.checkCustWorkflowINfoStatus(infos);
		// 5.如果是特殊申请的大客户，则判断该客户是否在准入列表或者预退出列表中，若在则提示到相应页面进行申请
		if (info.getIsSpecialKeyCustomer()) {
			// 查询是否存在有效的大客户列表数据
			List<KeyCustomer> keyCustomers = keyCustomerService.findKeyCustomerListByCustId(info.getCustId());
			// 校验是否存在在大客户列表中
			KeyCustomerValidator.checkISInKeyCustomerList(keyCustomers);
		}
		// 6.调用bpsWorkflowManager获得工作流号
		String bizCode = bpsWorkflowManager.bizCode();
		// 7.保存工作流审批信息
		info.setBusino(bizCode);
		// 当前登录用户
		info.setCreateUser(user.getEmpCode().getId());
		// 修改人
		info.setModifyUser(user.getEmpCode().getId());
		// 创建时间
		info.setCreateDate(new Date());
		// 修改时间
		info.setModifyDate(new Date());
		// 设置工作流审批中
		info.setStatus(Constant.WORKFLOW_STATUS_IN_APPROVE);
		// 保存信息
		keyCustomerService.saveWorkflowInfo(info);
		// 8.根据大客户列表的数据为无效
		keyCustomerService.updateKeyListStatus(info.getCustId(), Constant.NUMZERO);
		// 9.调用bpsWorkflowManager生成工作流号。
		Map<String, String> map = bpsWorkflowManager.createWorkflow(BpsConstant.KEYCUSTOMER_PROCESSNAME, BpsConstant.PROCESS_INSTANCE_KEYCUSTOMER + bizCode,
				BpsConstant.PROCESS_INSTANCE_KEYCUSTOMER, bizCode);
		// 10.返回工作流号

		if (null != map) {
			// 返回工作流号
			return map.get("bizCode");
		}
		return "";

	}

	/**
	 * 
	 * <p>
	 * Description:系统自动删除大客户标记<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-6 void
	 */
	@Transactional
	public void autoDeleteKeyCustomerFlag() {
		// 调用存储过程
		keyCustomerService.callStoredProcedure(Constant.AUTODELETEKEYCUSTOMER);

	}

	/**
	 * 
	 * <p>
	 * Description:系统自动生成待准入大客户列表-定时器使用<br /> 
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-6 void
	 */
	@Transactional
	public void createKeyCustomerInList() {
		// 1.封装查询条件
		KeyCustomerSearchCondition condition = new KeyCustomerSearchCondition();
		// 查询准入列表
		condition.setType(Constant.IN);
		// 有效
		condition.setListStatus("1");
		// 不限制条数
		condition.setStart(0);
		condition.setLimit(99999);
		// 2.查询未发起大客户准入列表的数据 不带条件
		List<KeyCustomer> list = keyCustomerService.listKeyCustomerList(condition);
		// 如果存在未发起准入申请的大客户准入列表
		// 发送邮件无论发送失败或者成功都继续执行下面的业务
		try {
			if (!CollectionUtils.isEmpty(list)) {
				// 获得当前时间
				Date fromDate = getDateMidnight(new Date(), -1);
				// 日期格式化
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				// 邮件名
				String subject = "前半个月未进行大客户准入申请的客户列表";
				// 内容
				String content = "领导好：附件是：" + sf.format(fromDate) + "前未进行准入申请的客户列表，请您及时查阅！";
				// 附件名
				String fileName = "待准入大客户列表.xlsx";
				// 导出excel
				String filePath = exportToExcel(list, fileName);
				// 封装邮件信息
				MailInfo mi = new MailInfo();
				// 设置标题
				mi.setSubject(subject);
				// 内容
				mi.setContent(content);
				// /附件
				File file = new File(filePath);
				File[] files = { file };
				mi.setAttachments(files);
				// 发送邮件
				sysMailSendManager.sendEmail(mi, Constant.KEYCUSTOMERMAILGROUP);
			}
		} catch (Exception e) {
			Log.info("大客户邮件发送失败", e);
		}
		// 调用存储过程生成数据
		keyCustomerService.callStoredProcedure(Constant.CREATEKEYINLIST);
	}

	/**
	 * 
	 * <p>
	 * Description:日期转换<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-11
	 * @param date
	 * @param addDay
	 * @return Date
	 * @throws ParseException
	 */
	private static Date getDateMidnight(Date date, int addDay) throws ParseException {
		// 日期装换
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(5, addDay);
		date = df.parse(df.format(cal.getTime()));
		return date;
	}

	/**
	 * 
	 * <p>
	 * Description:把数据导出为Excel<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-11-19
	 * @param actionLoglist
	 * @param fileName
	 * @return String
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String exportToExcel(List<KeyCustomer> list, String fileName) {
		// 得到excel导出路径
		String createPath = PropertiesUtil.getInstance().getProperty("excelExportFilePath").trim();
		// 新建一个excel导出
		ExcelExporter exporter = new ExcelExporter();
		List objList = transList(list);
		exporter.exportExcel(getHeaders(), objList, "待准入大客户列表", createPath, fileName);
		// 得到实际路径
		String realPath = createPath + "/" + fileName;
		return realPath;
	}

	/**
	 * 
	 * <p>
	 * Description:设置Excel的列头<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-11-19
	 * @return List<String>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<String> getHeaders() {
		// 设置列头
		List headers = new ArrayList();
		headers.add("所属部门");
		headers.add("客户名称");
		headers.add("客户编码");
		headers.add("联系人姓名");
		headers.add("联系人编码");
		headers.add("近三个月发货金额");
		headers.add("信用预警次数");
		return headers;
	}

	/**
	 * 
	 * <p>
	 * Description:封装Excel数据<br />
	 * </p>
	 * 
	 * @author 华龙
	 * @version 0.1 2012-11-19
	 * @param actionLoglist
	 * @return List<List<Object>>
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<List<Object>> transList(List<KeyCustomer> list) {
		List objList = new ArrayList();
		for (KeyCustomer keycustomer : list) {
			List row = new ArrayList();
			// 所属部门
			row.add(keycustomer.getDeptName());
			// 客户名称
			row.add(keycustomer.getCustomerName());
			// 客户编码
			row.add(keycustomer.getCustomerNum());
			// 联系人姓名
			row.add(keycustomer.getContactName());
			// 联系人编码
			row.add(keycustomer.getContactNum());
			// 近三个月发货金额
			row.add(keycustomer.getAmountOfSignMent());
			// 信用预警次数
			row.add(keycustomer.getCreditTimes());
			objList.add(row);
		}
		return objList;
	}

	/**
	 * 
	 * <p>
	 * Description:系统自动生成待准入大客户列表---定时器使用<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-6 void
	 */
	@Transactional
	public void createKeyCustomerOutList() {
		// 调用存储过程
		keyCustomerService.callStoredProcedure(Constant.CREATEKEYOUTLIST);
	}

	/**
	 * 
	 * <p>
	 * Description:工作流审批调用<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-7
	 * @param busino
	 *            工作流号
	 * @param wkStatus
	 *            是否同意 true 同意
	 * @param wkMan
	 *            审批人id
	 * @param approveDate
	 *            审批日期
	 * @param approvalSuggestin
	 *            审批意见
	 * 
	 */

	@Override
	public void workflowApprove(String busino, Boolean wkStatus, String wkMan, Date approveDate, String approvalSuggestin) {

		// 1.根据工作流号查询对应的工作流信息
		KeyCustomerWorkflowInfo info = keyCustomerService.findWorkflowInfoByBusino(busino);
		// 1.更新大客户工作流审批信息
		if (wkStatus) {
			info.setStatus(Constant.WORKFLOW_STATUS_AGREED);
			// 审批不同意
		} else {
			info.setStatus(Constant.WORKFLOW_STATUS_UNAGREE);
		}
		// 2.设置更新字段 ---根据业务编码进行更新
		info.setApprovalSuggestin(approvalSuggestin);
		// 审批人
		info.setLastApprover(wkMan);
		// 审批日期
		info.setApprovalTime(approveDate);
		// 修改时
		info.setModifyDate(approveDate);
		// 修改人
		info.setModifyUser(wkMan);
		// 3.更加大客户审批状态
		keyCustomerService.updateKeyCustomerWorkflowInfo(info);
		// 4.如果是审批同意更新大客户状态
		if (wkStatus) {
			Member member = new Member();
			member.setId(info.getCustId());
			// 是大客户标记为1
			member.setIsKeyCustomer(true);
			alterMemberService.updateMember(member);
			// 检测是否存在大客户信息拓展
			KeyCustomerVO oldKeyCustomerVO = keyCustomerService.selectKeyCustomer(info.getCustId());
			if (null == oldKeyCustomerVO || "".equals(oldKeyCustomerVO.getIsSpecialCustomer())) {
				// 5.创建大客户信息拓展
				KeyCustomerVO keyCustomerVO = new KeyCustomerVO();
				keyCustomerVO.setCustId(info.getCustId());
				keyCustomerVO.setBeginKeyCustomerTime(approveDate);
				keyCustomerVO.setCreateDate(approveDate);
				keyCustomerVO.setCreateUser(ContextUtil.getCreateOrModifyUserId());
				keyCustomerVO.setModifyDate(approveDate);
				keyCustomerVO.setModifyUser(ContextUtil.getCreateOrModifyUserId());
				// 大客户是否特殊申请与工作流审批历史保持一致
				keyCustomerVO.setIsSpecialCustomer(info.getIsSpecialKeyCustomer());
				// 保存信息
				keyCustomerService.saveKeyCustomer(keyCustomerVO);
			} else {
				oldKeyCustomerVO.setModifyDate(approveDate);
				oldKeyCustomerVO.setModifyUser(ContextUtil.getCreateOrModifyUserId());
				// 大客户是否特殊申请与工作流审批历史保持一致
				oldKeyCustomerVO.setIsSpecialCustomer(info.getIsSpecialKeyCustomer());
				oldKeyCustomerVO.setBeginKeyCustomerTime(approveDate);
				// 执行更新操作
				keyCustomerService.updateKeyCustomer(oldKeyCustomerVO);

			}
			// 将是大客户状态同步到对应系统中去
			CustomerInfoLog.commit();
		} else {
			// 审批不同意
			// 如果工作流是保留申请，若不同意，则更新大客户状态
			if (info.getWorkFlowType().equals(Constant.OUT)) {
				Member member = new Member();
				member.setId(info.getCustId());
				// 是大客户标记为1
				member.setIsKeyCustomer(false);
				alterMemberService.updateMember(member);
				CustomerInfoLog.commit();
			}
		}

	}

	/**
	 * 
	 * <p>
	 * Description:校验并且获得大客户审批信息<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-8
	 * @param custId
	 *            void
	 */
	public KeyCustomerWorkflowInfo findCustomerInfo(String custId) {
		// 查询是否有审批中的大客户工作流，若存在提示无法申请
		List<KeyCustomerWorkflowInfo> infos = keyCustomerService.findWorkflowListByCustId(custId, 0, 0);
		KeyCustomerValidator.checkCustWorkflowINfoStatus(infos);
		MemberCondition condition = new MemberCondition();
		condition.setMemberId(custId);
		List<Member> list = alterMemberService.searchMemberByCondition(condition);
		Member member = null;
		if (CollectionUtils.isEmpty(list)) {
			member = null;
		} else {
			member = list.get(0);
		}
		// 封装工作流审批表单元素
		KeyCustomerWorkflowInfo info = new KeyCustomerWorkflowInfo();
		if (null != member) {
			info.setDeptId(member.getDeptId());
			info.setDeptName(member.getDeptName());
			info.setCustId(member.getCustId());
			// 信用预警次数
			if (null != member.getMemberExtend()) {
				info.setCreditTimes(member.getMemberExtend().getCreditTimes());
			}
			info.setCustomerName(member.getCustName());
			info.setCustomerNum(member.getCustNumber());
		}
		info.setCustDegree(info.getCustDegree());
		// 根据会员id获得联系人信息
		info.setAmountOfSignMent(contractService.getArrearaMountByCustId(custId));
		List<Contact> conList = contactService.getContactsByMemberId(custId);
		for (Contact contact : conList) {
			if (contact.getIsMainLinkMan()) {
				// 取主联系人信息
				info.setContactName(contact.getName());
				info.setContactNum(contact.getNumber());
			}

		}

		return info;

	}

	/**
	 * 
	 * <p>
	 * Description:校验是否满足条件<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-8
	 * @param custId
	 * @return
	 * 
	 */
	@Override
	public KeyStatusVO checkKeyStatusVO(String custId) {
		KeyStatusVO vo = new KeyStatusVO();
		MemberCondition condition = new MemberCondition();
		condition.setMemberId(custId);
		List<Member> list = alterMemberService.searchMemberByCondition(condition);
		Member member = null;
		if (!CollectionUtils.isEmpty(list)) {
			member = list.get(0);
		}
		if (null != member) {
			// 是否大客户
			vo.setFisKeyCutomer(member.getIsKeyCustomer());
			if ("1".equals(member.getCustStatus())) {
				vo.setfIsInCustomerapproved(true);
			}

		}
		// 查询是否有审批中的大客户工作流，若存在提示无法申请
		List<KeyCustomerWorkflowInfo> infos = keyCustomerService.findWorkflowListByCustId(custId, 0, 0);
		// 如果存在审批中的工作流，则跑出异常
		// 循环遍历，
		for (KeyCustomerWorkflowInfo info : infos) {
			// 如果存在审批中的工作流
			if (info.getStatus().equals(Constant.WORKFLOW_STATUS_IN_APPROVE)) {
				vo.setFisapproved(true);

			}
		}
		// 是否存在于大客户审批列表中
		List<KeyCustomer> keyCustomers = keyCustomerService.findKeyCustomerListByCustId(custId);
		if (!CollectionUtils.isEmpty(keyCustomers)) {
			if (Constant.IN.equals(keyCustomers.get(0).getType())) {
				vo.setFisListIn(true);
			}
			if (Constant.OUT.equals(keyCustomers.get(0).getType())) {
				vo.setFisListOut(true);
			}

		}
		if (StringUtil.isNotEmpty(custId)) {
			String arrearaMount = contractService.getArrearaMountByCustId(custId);
			String[] spitAmount = arrearaMount.split("\\\\");
			Boolean flag = true;
			for (String amount : spitAmount) {
				int amountInt = Integer.parseInt(amount);
				if (amountInt < 10000) {
					flag = false;
					break;
				}

			}
			vo.setFisAmountAchieve(flag);
			vo.setAmountOfSignMent(arrearaMount);

		}
		// 是否在疑似客户列表中
		vo.setFisCustExistsRepeat(repeatedCustManager.isCustExistsRepeat(custId));

		return vo;
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年3月26日
	 * @return IBpsWorkflowManager
	 */
	public IBpsWorkflowManager getBpsWorkflowManager() {
		return bpsWorkflowManager;
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年3月26日
	 * @param bpsWorkflowManager
	 *            void
	 */
	public void setBpsWorkflowManager(IBpsWorkflowManager bpsWorkflowManager) {
		this.bpsWorkflowManager = bpsWorkflowManager;
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年3月26日
	 * @return IAlterMemberService
	 */
	public IAlterMemberService getAlterMemberService() {
		return alterMemberService;
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年3月26日
	 * @param alterMemberService
	 *            void
	 */
	public void setAlterMemberService(IAlterMemberService alterMemberService) {
		this.alterMemberService = alterMemberService;
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年3月26日
	 * @return IContractService
	 */
	public IContractService getContractService() {
		return contractService;
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年3月26日
	 * @param contractService
	 *            void
	 */
	public void setContractService(IContractService contractService) {
		this.contractService = contractService;
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年3月26日
	 * @return IMemberService
	 */
	public IMemberService getMemberService() {
		return memberService;
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年3月26日
	 * @param memberService
	 *            void
	 */
	public void setMemberService(IMemberService memberService) {
		this.memberService = memberService;
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年3月26日
	 * @return IKeyCustomerService
	 */
	public IKeyCustomerService getKeyCustomerService() {
		return keyCustomerService;
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年3月26日
	 * @param keyCustomerService
	 *            void
	 */
	public void setKeyCustomerService(IKeyCustomerService keyCustomerService) {
		this.keyCustomerService = keyCustomerService;
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年3月26日
	 * @return ContactService
	 */
	public IContactService getContactService() {
		return contactService;
	}

	public ISysMailSendManager getSysMailSendManager() {
		return sysMailSendManager;
	}

	public void setSysMailSendManager(ISysMailSendManager sysMailSendManager) {
		this.sysMailSendManager = sysMailSendManager;
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年3月26日
	 * @param sysMailSendManager
	 *            void
	 */
	public void setSysMailSendManager(SysMailSendManager sysMailSendManager) {
		this.sysMailSendManager = sysMailSendManager;
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年3月26日
	 * @param bizCode
	 * @return
	 * 
	 */
	@Override
	public KeyCustomerWorkflowInfo findWorkflowInfoByBusino(String bizCode) {
		return keyCustomerService.findWorkflowInfoByBusino(bizCode);
	}

	public void setContactService(IContactService contactService) {
		this.contactService = contactService;
	}

	/**
	 * <p>
	 * Description:repeatedCustManager<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月17日
	 */
	public IRepeatedCustManager getRepeatedCustManager() {
		return repeatedCustManager;
	}

	/**
	 * <p>
	 * Description:repeatedCustManager<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月17日
	 */
	public void setRepeatedCustManager(IRepeatedCustManager repeatedCustManager) {
		this.repeatedCustManager = repeatedCustManager;
	}
}
