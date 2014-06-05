package com.deppon.crm.module.client.order.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.common.util.DataFormatUtil;
import com.deppon.crm.module.client.order.IOaAccidentOperate;
import com.deppon.crm.module.client.order.domain.ComplaintInformInfo;
import com.deppon.crm.module.client.order.domain.OaAccidentInfo;
import com.deppon.crm.module.client.order.domain.OaAccidentInfo.ErrorRewardPunishment;
import com.deppon.oa.error.ArrayOfString;
import com.deppon.oa.error.ErrorService;
import com.deppon.oa.task.TaskService;

public class OaAccidentOperateImpl implements IOaAccidentOperate {

	private ErrorService errorService;
	private TaskService taskService;
	
	private static Log log = LogFactory.getLog(OaAccidentOperateImpl.class);

	/**
	 * str[0]：差错编号!@#差错名称!@#处理结果描述!@#处理对象!@#奖罚说明!@#奖罚金额!@#处理时间!@#处理人!@#差错类型
	 * str[1]:.... str[2]:....
	 */
	@Override
	public List<OaAccidentInfo> queryAccidentByWaybillCode(String waybillNumber)
			throws CrmBusinessException {

		if (waybillNumber == null || "".equals(waybillNumber)) {
			throw new CrmBusinessException("0017");
		}

		ArrayOfString as = errorService.returnErrosInfor(waybillNumber);

		List<OaAccidentInfo> infos = new ArrayList<OaAccidentInfo>();
		Map<String, OaAccidentInfo> errors = new HashMap<String, OaAccidentInfo>();

		if (as == null || as.getString() == null || as.getString().size() == 0) {
			return infos;
		}
		List<String> resultStr = as.getString();
		
		for (String rs : resultStr) {
			if(rs == null || rs.equals("")){
				continue;
			}
			String[] st = rs.split("!@#");
			OaAccidentInfo info = errors.get(st[0]);
			if (info == null) {
				info = new OaAccidentInfo();
				info.setAccidentCode(convert(st[0]));
				info.setAccidentName(convert(st[1]));
				info.setAccidentType(Integer.parseInt(convert(st[8])));
				info.setWaybillNumber(waybillNumber);
				errors.put(st[0], info);
				infos.add(info);
			}
			
			ErrorRewardPunishment erp = new ErrorRewardPunishment();
			erp.setProcessResult(convert(st[2]));
			erp.setProcessTarget(convert(st[3]));
			erp.setRewardPunishmentDescription(convert(st[4]));
			erp.setMoney(DataFormatUtil.parseDouble(convert(st[5])));
			erp.setProcessDate(DataFormatUtil.parseDate(convert(st[6]),
					"yyyy-MM-dd HH:mm:ss"));
			erp.setProcessPerson(convert(st[7]));
			
			info.getRewardPunishments().add(erp);
		}

		return infos;
	}

	/**
	 * str[0]：差错名称
	 * str[1]：处理结果描述!@#处理对象!@#奖罚说明!@#奖罚金额!@#处理时间!@#处理人#@#处理结果描述!@#处理对象
	 * !@#奖罚说明!@#奖罚金额!@#处理时间!@#处理人
	 * str[2]：包装!@#保价人（即发货联系人）!@#联系方式!@#收货部门!@#目的站!@#发货日期
	 * !@#货物名称!@#件/重/体!@#保价金额!@#运单号!@#运输类型!@#始发站
	 */
	@Override
	public OaAccidentInfo queryAccidentByAccidentCode(String errorCode)
			throws CrmBusinessException {

		if (errorCode == null || "".equals(errorCode)) {
			throw new CrmBusinessException("0019");
		}

		ArrayOfString as = errorService.getErrorInfo(errorCode);

		List<String> results = as.getString();
		if (results.size() < 1) {
			return null;
		}
		OaAccidentInfo errorInfo = new OaAccidentInfo();
		errorInfo.setAccidentCode(errorCode);
		errorInfo.setAccidentName(convert(results.get(0)));

		String rewards = results.get(1);
		if (rewards != null && !"".equals(rewards)) {
			String[] reward = rewards.split("#@#");
			for (String string : reward) {
				// 处理结果描述!@#处理对象!@#奖罚说明!@#奖罚金额!@#处理时间!@#处理人
				String[] st = string.split("!@#");
				ErrorRewardPunishment erp = new ErrorRewardPunishment();
				erp.setProcessResult(convert(st[0]));
				erp.setProcessTarget(convert(st[1]));
				erp.setRewardPunishmentDescription(convert(st[2]));
				erp.setMoney(DataFormatUtil.parseDouble(convert(st[3])));
				erp.setProcessDate(DataFormatUtil.parseDate(convert(st[4]),
						"yyyy-MM-dd HH:mm:ss"));
				erp.setProcessPerson(convert(st[5]));
				errorInfo.getRewardPunishments().add(erp);
			}
		}
		// 货物信息
		String goodsInfo = results.get(2);
		if (goodsInfo != null && !goodsInfo.equals("")) {
			String[] infos = goodsInfo.split("!@#");
			errorInfo.setPackaging(convert(infos[0]));// 包装12
			errorInfo.setContactName(convert(infos[1]));// 保价人（即发货联系人）
			errorInfo.setContactType(convert(infos[2]));// 联系方式
			errorInfo.setReceivingDept(convert(infos[3]));// 收货部门
			errorInfo.setDestinationStation(convert(infos[4]));// 目的站
			errorInfo.setDateShiped(DataFormatUtil.parseDate(convert(infos[5]),
					"yyyy-MM-dd HH:mm:ss"));// 发货日期
			errorInfo.setCargoName(convert(infos[6]));// 货物名称
			errorInfo.setCargoWeight(convert(infos[7]));// 件/重/体
			errorInfo.setInsuredAmount(DataFormatUtil.parseDouble(convert(infos[8])));// 保价金额
			errorInfo.setWaybillNumber(convert(infos[9]));// 运单号
			errorInfo.setTransportType(convert(infos[10]));// 运输类型
			if (infos.length==12) {
				errorInfo.setStartStation(convert(infos[11]));// 始发站
			}
		}
		// str[2]：包装!@#保价人（即发货联系人）!@#联系方式!@#收货部门!@#目的站!@#发货日期
		// * !@#货物名称!@#件/重/体!@#保价金额!@#运单号!@#运输类型!@#始发站
		if (log.isDebugEnabled()) {
			log.debug(errorInfo);
		}
		return errorInfo;
	}

	@Override
	public boolean informComplaint(List<ComplaintInformInfo> informInfos)
			throws CrmBusinessException {
		boolean result = true;
		String temp = null;
		for (ComplaintInformInfo info : informInfos) {
			temp = taskService.sendMessageToPortal(info.getJobNumber(),
					String.format("您有待办投诉(%s)", info.getComplaintCount()));
			if (!"成功".equals(temp)) {
				result = false;
			}
		}
		return result;
	}
	
	private String convert(String source){
		if (source==null || "".equals(source) || "null".equalsIgnoreCase(source)) {
			return null;
		}
		return source.trim();
	}

	public ErrorService getErrorService() {
		return errorService;
	}

	public void setErrorService(ErrorService errorService) {
		this.errorService = errorService;
	}

	public TaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

}
