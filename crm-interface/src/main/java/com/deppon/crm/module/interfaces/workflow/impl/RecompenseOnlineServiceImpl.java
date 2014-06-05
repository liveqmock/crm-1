package com.deppon.crm.module.interfaces.workflow.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;
import javax.xml.ws.Holder;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.fin.IDepartmentOperate;
import com.deppon.crm.module.client.order.IWaybillOperate;
import com.deppon.crm.module.client.order.domain.WaybillRecompenseInfo;
import com.deppon.crm.module.interfaces.common.util.IntefacesTool;
import com.deppon.crm.module.interfaces.workflow.IRecompenseOnlineService;
import com.deppon.crm.module.interfaces.workflow.domain.OnlineApplyInfo;
import com.deppon.crm.module.interfaces.workflow.domain.RecompenseOnlineQueryCondition;
import com.deppon.crm.module.interfaces.workflow.domain.RecompenseOnlineVerifyInfo;
import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.recompense.server.manager.RecompenseManager;
import com.deppon.crm.module.recompense.shared.domain.OnlineApply;
import com.deppon.crm.module.recompense.shared.domain.RecompenseApplication;

@WebService(endpointInterface = "com.deppon.crm.module.interfaces.workflow.IRecompenseOnlineService")
public class RecompenseOnlineServiceImpl implements IRecompenseOnlineService {

	// 理赔模块
	RecompenseManager recompenseManager;
	// 运单
	IWaybillOperate waybillOperate;
	// 部门服务
	IDepartmentService departmentService;
	// 接口部门服务
	IDepartmentOperate departmentOperate;
	private static final String CommpanyName = "有限";
	/**
	 * @作者：罗典
	 * @描述：撤销理赔信息
	 * @时间：2012-4-21
	 * */
	@Override
	public boolean cancelRecompenseOnline(String waybillNumber, String username)
			throws CrmBusinessException {
		// 运单号空校验
		IntefacesTool.validateStringNull(waybillNumber, "0018"," waybillNumber: "+
		waybillNumber +" username:"+username);
		// 用户名空校验
		IntefacesTool.validateStringNull(username, "0019"," waybillNumber: "+
				waybillNumber +" username:"+username);
		return recompenseManager.cancelOnlineApply(username, waybillNumber);
	}

	/**
	 * @作者：罗典
	 * @描述：创建理赔
	 * @时间：2012-4-23
	 * */
	@Override
	public String createRecompenseOfOnline(OnlineApplyInfo onlineApplyInfo)
			throws CrmBusinessException {
		// 运单号空校验
		IntefacesTool.validateStringNull(onlineApplyInfo.getWaybillNumber(),
				"0017",onlineApplyInfo);
		// 获取理赔状态
		String recompenseStatus = recompenseManager
				.getOnlineApplyStatusByWaybillNum(onlineApplyInfo
						.getWaybillNumber());
		OnlineApply onlineApply = IntefacesTool
				.changeOnlineApplyInfoToOnlineApply(onlineApplyInfo);
		// onlineApply.setApplyDeptId("W10050234");
		// 将部门标杆编码转换为CRM部门ID
		if (onlineApply.getApplyDeptId() != null) {
			Department dept = departmentService
					.getDeptByStandardCode(onlineApply.getApplyDeptId());
			IntefacesTool.validateNull(dept, "0025",onlineApplyInfo);
			onlineApply.setApplyDeptId(dept.getId());
		}

		// 无理赔信息时保存理赔
		if (recompenseStatus == null || recompenseStatus.equals("")) {
			recompenseManager.createOnlineApply(onlineApply);
		}
		// 如果查询出作废和已拒绝的理赔信息则直接修改，且状态启用为未处理
		else if (recompenseStatus != null
				&& (recompenseStatus.equals("Invalid") || recompenseStatus
						.equals("Rejected"))) {
			onlineApply.setStatus("WaitAccept");
			recompenseManager.updateOnlineApply(onlineApply);
		}
		// 查询出未作废且不为已拒绝的理赔信息则不允许保存
		else {
			throw new CrmBusinessException("0020",onlineApplyInfo.getWaybillNumber());
		}
		return "true";
	}

	/**
	 * @作者：罗典
	 * @描述：修改在线理赔信息
	 * @时间：2012-4-23
	 * */
	@Override
	public boolean updateRecompenseOfOnline(OnlineApplyInfo onlineApplyInfo)
			throws CrmBusinessException {
		// 运单号空校验
		IntefacesTool.validateStringNull(onlineApplyInfo.getWaybillNumber(),
				"0017",onlineApplyInfo);
		// 获取理赔状态
		String recompenseStatus = recompenseManager
				.getOnlineApplyStatusByWaybillNum(onlineApplyInfo
						.getWaybillNumber());
		OnlineApply onlineApply = IntefacesTool
				.changeOnlineApplyInfoToOnlineApply(onlineApplyInfo);
		// 只能对已拒绝和未受理理赔信息进行修改
		if (recompenseStatus != null
				&& (recompenseStatus.equals("Rejected") || recompenseStatus
						.equals("WaitAccept"))) {
			return recompenseManager.updateOnlineApply(onlineApply);
		} else {
			throw new CrmBusinessException("0021",onlineApplyInfo.getWaybillNumber());
		}
	}

	/**
	 * @作者：罗典
	 * @描述：根据条件查询理赔信息
	 * @时间：2012-4-23
	 * */
	@Override
	public List<OnlineApplyInfo> queryRecompenseOnlineInfos(
			Holder<RecompenseOnlineQueryCondition> holder)
			throws CrmBusinessException {
		RecompenseOnlineQueryCondition queryCondition = holder.value;
		// 获取满足条件信息集合
		List<OnlineApply> onlineApplyList = recompenseManager
				.getOnlineApplyByCondition(queryCondition.getUsername(),
						queryCondition.getWaybillNumber(),
						queryCondition.getStartDate(),
						queryCondition.getEndDate(),
						queryCondition.getCurrentPage(),
						queryCondition.getPageSize());
		List<OnlineApplyInfo> onlineApplyInfoList = new ArrayList<OnlineApplyInfo>();
		// 获取满足条件信息总数
		int totalCount = recompenseManager.getOnlineApplyByConditionCount(
				queryCondition.getUsername(),
				queryCondition.getWaybillNumber(),
				queryCondition.getStartDate(), queryCondition.getEndDate());
		holder.value.setCount(totalCount);
		// 转换理赔集合信息
		for (OnlineApply onlineApply : onlineApplyList) {
			onlineApplyInfoList.add(IntefacesTool
					.changeOnlineApplyToOnlineApplyInfo(onlineApply));
			if (onlineApply.getApplyDeptId() != null) {
				Department dept = departmentService
						.queryById(onlineApply.getApplyDeptId());
				// 部门空校验
				IntefacesTool.validateNull(dept, "0025",holder);
				onlineApply.setApplyDeptId(dept.getDeptCode());
			}
		}
		return onlineApplyInfoList;
	}

	/**
	 * @作者：罗典
	 * @时间：2012-4-23
	 * @描述：根据理赔运单编号，用户名查询理赔信息
	 * */
	@Override
	public OnlineApplyInfo queryRecompenseOnlineEntity(String waybillNum,
			String username) throws CrmBusinessException {
		// 运单号空校验
		IntefacesTool.validateStringNull(waybillNum, "0017",null);
		// 用户名空校验
		IntefacesTool.validateStringNull(waybillNum, "0019",null);
		List<OnlineApply> onlineApplyList = recompenseManager
				.getOnlineApplyByOnlineUser(username, waybillNum);
		if (onlineApplyList != null && onlineApplyList.size() > 0) {
			return IntefacesTool
					.changeOnlineApplyToOnlineApplyInfo(onlineApplyList.get(0));

		}
		return null;
	}

	/**
	 * @作者：罗典
	 * @时间：2012-4-23
	 * @描述：判断是否能够理赔
	 * */
	@Override
	public RecompenseOnlineVerifyInfo getRecompenseOnlineVerifyInfo(
			String waybillNumber) throws CrmBusinessException {
		// 运单号空校验
		IntefacesTool.validateStringNull(waybillNumber, "0017",null);
		// 获取理赔状态
		String recompenseStatus = recompenseManager
				.getOnlineApplyStatusByWaybillNum(waybillNumber);
		// 已存在此运单理赔则返回异常信息
		if (recompenseStatus != null && !recompenseStatus.equals("Invalid")
				&& !recompenseStatus.equals("Rejected")) {
			throw new CrmBusinessException("0020",waybillNumber);
		}
		RecompenseApplication recompense = recompenseManager
				.getRecompenseApplicationByVoucherNo(waybillNumber);
		if(recompense != null){
			throw new CrmBusinessException("0020",waybillNumber);
		}
		//判断是否有上报差错或异常签收
		if(!recompenseManager.canCreateOnlineApply(waybillNumber)){
			throw new CrmBusinessException("0038",waybillNumber);
		}
		// 获取运单信息
		WaybillRecompenseInfo wr = waybillOperate
				.queryWaybillOnlineRecompense(waybillNumber);
		
		// 运单信息空校验
		IntefacesTool.validateNull(wr, "0028",waybillNumber);
		RecompenseOnlineVerifyInfo recompenseOnlineVerifyInfo = IntefacesTool
				.changewaybillToVerifyInfo(wr);
		if(wr.getShipmentsDept()!=null && !wr.getShipmentsDept().equals("")){
			// 发货部门子公司
			recompenseOnlineVerifyInfo.setShipCompanyName(
					departmentOperate.querySubCompanyNameByCode(wr.getShipmentsDept()));
		}
		if (wr.getReceiveDept() != null && !wr.getReceiveDept().equals("")
				&& !wr.getTransportType().equals("PLF")
				&& !wr.getTransportType().equals("AF")
				&&!wr.getTransportType().equals("PACKAGE")) {
			String subCompanyName = departmentOperate.querySubCompanyNameByCode(wr.getReceiveDept());
			// 收货部门子公司
			//子公司名字不包含 ‘有限' 就返回 Null
			if (!StringUtils.isEmpty(subCompanyName) && subCompanyName.indexOf(CommpanyName) > 0) {
				recompenseOnlineVerifyInfo.setReceiveCompanyName(subCompanyName);
			}else {
				recompenseOnlineVerifyInfo.setReceiveCompanyName(null);
			}
		}
		if (wr.getTransportType().equals("PACKAGE")) {
			String subCompanyName = departmentOperate
					.querySubCompanyNameByCode(wr.getReceiveDept());
			//快递：子公司名字不包含 ‘有限' 就返回 PACKAGE
			if (!StringUtils.isEmpty(subCompanyName) && subCompanyName.indexOf(CommpanyName) > 0) {
				recompenseOnlineVerifyInfo.setReceiveCompanyName(subCompanyName);
			}else {
				recompenseOnlineVerifyInfo.setReceiveCompanyName("PACKAGE");
			}
		}
		// 如果运输方式是汽运偏线，则赋值子公司名为"PLF"
		if (wr.getTransportType().equals("PLF")) {
			recompenseOnlineVerifyInfo.setReceiveCompanyName("PLF");
		}
		// 如果运输方式是汽运偏线，则赋值子公司名为"PLF"
		if (wr.getTransportType().equals("AF")) {
			recompenseOnlineVerifyInfo.setReceiveCompanyName("AF");
		}
		
		// 设置理赔公司
		//recompenseOnlineVerifyInfo.setCompanyName("德邦物流股份有限公司");
		// TODO 调用财务接口
		// 设置为可以理赔
		recompenseOnlineVerifyInfo.setCanRecompense(true);
		return recompenseOnlineVerifyInfo;
	}

	/**
	 * @throws CrmBusinessException
	 * @作者：罗典
	 * @时间：2012-4-23
	 * @描述：理赔再次付款申请
	 * */
	@Override
	public boolean paymentUpdate(OnlineApplyInfo onlineApplyInfo)
			throws CrmBusinessException {
		// 运单空校验
		IntefacesTool.validateStringNull(onlineApplyInfo.getWaybillNumber(),
				"0017",onlineApplyInfo);
		// 获取理赔状态
		String recompenseStatus = recompenseManager
				.getOnlineApplyStatusByWaybillNum(onlineApplyInfo
						.getWaybillNumber());
		OnlineApply onlineApply = IntefacesTool
				.changeOnlineApplyInfoToOnlineApply(onlineApplyInfo);
		// 只有付款失败的理赔信息才能再次提交
		if (recompenseStatus != null
				&& recompenseStatus.equals("OnlinePayFailed")) {
			return recompenseManager.repayOnlineApply(onlineApply);
		} else {
			throw new CrmBusinessException("0022",onlineApplyInfo.getWaybillNumber());
		}
	}

	public RecompenseManager getRecompenseManager() {
		return recompenseManager;
	}

	public void setRecompenseManager(RecompenseManager recompenseManager) {
		this.recompenseManager = recompenseManager;
	}

	public IWaybillOperate getWaybillOperate() {
		return waybillOperate;
	}

	public void setWaybillOperate(IWaybillOperate waybillOperate) {
		this.waybillOperate = waybillOperate;
	}

	public IDepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public IDepartmentOperate getDepartmentOperate() {
		return departmentOperate;
	}

	public void setDepartmentOperate(IDepartmentOperate departmentOperate) {
		this.departmentOperate = departmentOperate;
	}

}
