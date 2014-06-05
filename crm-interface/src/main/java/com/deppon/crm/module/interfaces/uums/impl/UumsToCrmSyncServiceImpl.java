package com.deppon.crm.module.interfaces.uums.impl;

import java.util.Date;


import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;

import com.deppon.crm.module.client.common.util.Constant;
import com.deppon.crm.module.frameworkimpl.server.util.CryptoUtil;
import com.deppon.crm.module.interfaces.uums.UumsToCrmSyncService;
import com.deppon.crm.module.interfaces.uums.jms.AdminOrgInfo;
import com.deppon.crm.module.interfaces.uums.jms.EmployeeInfo;
import com.deppon.crm.module.interfaces.uums.jms.PositionInfo;
import com.deppon.crm.module.interfaces.uums.jms.SendAdminOrgProcessReult;
import com.deppon.crm.module.interfaces.uums.jms.SendAdminOrgRequest;
import com.deppon.crm.module.interfaces.uums.jms.SendAdminOrgResponse;
import com.deppon.crm.module.interfaces.uums.jms.SendEmployeeProcessReult;
import com.deppon.crm.module.interfaces.uums.jms.SendEmployeeRequest;
import com.deppon.crm.module.interfaces.uums.jms.SendEmployeeResponse;
import com.deppon.crm.module.interfaces.uums.jms.SendPositionProcessReult;
import com.deppon.crm.module.interfaces.uums.jms.SendPositionRequest;
import com.deppon.crm.module.interfaces.uums.jms.SendPositionResponse;
import com.deppon.crm.module.interfaces.uums.jms.SendUserInfoDeptAllProcessReult;
import com.deppon.crm.module.interfaces.uums.jms.SendUserInfoDeptAllRequest;
import com.deppon.crm.module.interfaces.uums.jms.SendUserInfoDeptAllResponse;
import com.deppon.crm.module.interfaces.uums.jms.UserInfoDeptAll;
import com.deppon.crm.module.interfaces.uums.ws.CommException;
import com.deppon.foss.framework.shared.util.classes.BeanUtils;
import com.deppon.foss.framework.shared.util.string.StringUtil;

public class UumsToCrmSyncServiceImpl implements UumsToCrmSyncService {

	private com.deppon.crm.module.uums.server.manager.impl.syncFacedManager syncFacedManager;
	private com.deppon.crm.module.uums.server.service.Imp.PositionSerive positionSerive;
	private Logger logger = Logger.getLogger(UumsToCrmSyncServiceImpl.class);
	
	@Override
	public SendPositionResponse syncPositionInfo(SendPositionRequest sendPositionRequest) throws CommException {
		//响应参数
		SendPositionResponse sendPositionResponse = new SendPositionResponse();
		List<SendPositionProcessReult> processResults = sendPositionResponse.getProcessResult();
		SendPositionProcessReult sendPositionProcessReult;
		//base模块 职位信息对象
		com.deppon.crm.module.uums.shared.domain.PostionInfo basePostionInfo;
		int sucessNumber = 0;//成功条数
		int failNumber = 0;//失败条数
		
		for (PositionInfo positionInfo : sendPositionRequest.getPositionInfo()) {
			sendPositionProcessReult = new SendPositionProcessReult();
			basePostionInfo = new com.deppon.crm.module.uums.shared.domain.PostionInfo();
			try {
			//对象赋值
			BeanUtils.copyProperties(positionInfo, basePostionInfo);
			basePostionInfo.setTheChangeId(positionInfo.getPositionChangeId());
			basePostionInfo.setTheMainCode(positionInfo.getPositionCode());
			basePostionInfo.setTheMainId(positionInfo.getId());
			basePostionInfo.setChangeType(positionInfo.getStatus());
			basePostionInfo.setChangeDate(convertToDate(positionInfo.getLastModifyTime()));
			basePostionInfo.setLastModifyTime(convertToDate(positionInfo.getLastModifyTime()));
			//调用业务模块
				int result = syncFacedManager.check(basePostionInfo);
			if (result == Constant.SUCCESS) {
				sucessNumber += 1;
				sendPositionProcessReult.setResult(true);
				}else {
				failNumber += 1;
				sendPositionProcessReult.setResult(false);
				sendPositionProcessReult.setReason(Constant.UUMS_EXCEPTION);
			 }
			}catch(CommException e){
				failNumber += 1;
				sendPositionProcessReult.setResult(false);
				sendPositionProcessReult.setReason(e.getMessage());
			}catch (Exception e) {
				logger.info("syncPositionInfo - message:",e);
				sendPositionProcessReult.setResult(false);
				failNumber += 1;
				sendPositionProcessReult.setReason(e.getMessage());
			}finally{
				sendPositionProcessReult.setPositionChangeId(positionInfo.getPositionChangeId());
				sendPositionProcessReult.setPositionCode(positionInfo.getPositionCode());
				sendPositionProcessReult.setPositionName(positionInfo.getPositionName());
				processResults.add(sendPositionProcessReult);
				//新增1 和修改2 删除3  status表示为1时，是已作废，说明要删除
				if (!StringUtil.isEmpty(positionInfo.getStatus()) && "1".equals(positionInfo.getStatus())) {
					sendPositionProcessReult.setChangeType(String.valueOf(Constant.DELETE));
				}else {
					com.deppon.crm.module.uums.shared.domain.PostionInfo demo =	positionSerive.searchById(positionInfo.getId());
					if (demo != null && !StringUtil.isEmpty(demo.getPositionName())) {
						sendPositionProcessReult.setChangeType(String.valueOf(Constant.UPDATE));
					}else {
						sendPositionProcessReult.setChangeType(String.valueOf(Constant.INSERT));
					}
				}
			}
		}
		sendPositionResponse.setSuccessCount(sucessNumber);
		sendPositionResponse.setFailedCount(failNumber);
		return sendPositionResponse;
	}

	@Override
	public SendEmployeeResponse syncEmployeeInfo(SendEmployeeRequest sendEmployeeRequest) throws CommException {
		// 响应参数
		SendEmployeeResponse sendEmployeeResponse = new SendEmployeeResponse();
		List<SendEmployeeProcessReult> processResults = sendEmployeeResponse.getProcessResult();
		SendEmployeeProcessReult sendEmployeeProcessReult;
		// base模块 职员信息对象
		com.deppon.crm.module.uums.shared.domain.EmployeeInfo baseEmployeeInfo;
		int sucessNumber = 0;// 成功条数
		int failNumber = 0;// 失败条数
		for (EmployeeInfo employeeInfo : sendEmployeeRequest.getEmployeeInfo()) {
			sendEmployeeProcessReult = new SendEmployeeProcessReult();
			baseEmployeeInfo = new com.deppon.crm.module.uums.shared.domain.EmployeeInfo();
			try {
			// 对象赋值
			BeanUtils.copyProperties(employeeInfo, baseEmployeeInfo);
			baseEmployeeInfo.setTheChangeId(employeeInfo.getEmployeeChangeId());
			baseEmployeeInfo.setTheMainCode(employeeInfo.getEmployeeCode());
			baseEmployeeInfo.setTheMainId(null);
			baseEmployeeInfo.setChangeType(employeeInfo.getChangeType());
			baseEmployeeInfo.setChangeDate(convertToDate(employeeInfo.getChangeDate()));
			baseEmployeeInfo.setBirthday(convertToDate(employeeInfo.getBirthday()));
			baseEmployeeInfo.setEntryDate(convertToDate(employeeInfo.getEntryDate()));
			baseEmployeeInfo.setDepartureDate(convertToDate(employeeInfo.getDepartureDate()));
				// 调用业务模块
			int result = syncFacedManager.check(baseEmployeeInfo);
			if (result == Constant.SUCCESS) {
				sucessNumber += 1;
				sendEmployeeProcessReult.setResult(true);
			} else {
				failNumber += 1;
				sendEmployeeProcessReult.setResult(false);
				sendEmployeeProcessReult.setReason(Constant.UUMS_EXCEPTION);
				}
			} catch(CommException e){
				failNumber += 1;
				sendEmployeeProcessReult.setResult(false);
				sendEmployeeProcessReult.setReason(e.getMessage());
			}
			catch (Exception e) {
				logger.info("syncEmployeeInfo - message:",e);
				failNumber += 1;
				sendEmployeeProcessReult.setResult(false);
				sendEmployeeProcessReult.setReason(e.getMessage());
			} finally {
				sendEmployeeProcessReult.setChangeType(employeeInfo.getChangeType());
				sendEmployeeProcessReult.setEmployeeChangeId(employeeInfo.getEmployeeChangeId());
				sendEmployeeProcessReult.setEmployeeCode(employeeInfo.getEmployeeCode());
				processResults.add(sendEmployeeProcessReult);
			}
		}
		sendEmployeeResponse.setSuccessCount(sucessNumber);
		sendEmployeeResponse.setFailedCount(failNumber);
		return sendEmployeeResponse;
	}

	@Override
	public SendUserInfoDeptAllResponse syncUserInfoDeptAll(
			SendUserInfoDeptAllRequest sendUserInfoDeptAllRequest)
			throws CommException {
		// 响应参数
		SendUserInfoDeptAllResponse sendUserInfoDeptAllResponse = new SendUserInfoDeptAllResponse();
		List<SendUserInfoDeptAllProcessReult> processResults = sendUserInfoDeptAllResponse.getProcessResult();
		SendUserInfoDeptAllProcessReult sendUserInfoDeptAllProcessReult;
		// base模块用户信息对象
		com.deppon.crm.module.uums.shared.domain.UserInfo baseUserInfo;
		int sucessNumber = 0;// 成功条数
		int failNumber = 0;// 失败条数
		for (UserInfoDeptAll userInfoDeptAll : sendUserInfoDeptAllRequest.getUserInfo()) {
			sendUserInfoDeptAllProcessReult = new SendUserInfoDeptAllProcessReult();
			baseUserInfo = new com.deppon.crm.module.uums.shared.domain.UserInfo();
			try {
			// 对象赋值
			BeanUtils.copyProperties(userInfoDeptAll, baseUserInfo);
			//用户密码加密
			baseUserInfo.setDesPassword(CryptoUtil.digestByMD5(userInfoDeptAll.getDesPassword()));
			baseUserInfo.setTheChangeId(userInfoDeptAll.getAccountChangeId());
			baseUserInfo.setTheMainCode(userInfoDeptAll.getWorkCode());
			baseUserInfo.setTheMainId(null);
			baseUserInfo.setChangeDate(convertToDate(userInfoDeptAll.getChangeDate()));
			baseUserInfo.setChangeType(userInfoDeptAll.getChangeType());
			baseUserInfo.setValidDate(convertToDate(userInfoDeptAll.getValidDate()));
			baseUserInfo.setInvalidDate(convertToDate(userInfoDeptAll.getInvalidDate()));
			baseUserInfo.setLastModifyTime(convertToDate(userInfoDeptAll.getLastModifyTime()));
			//boolean类型值转化
			baseUserInfo.setTemp(userInfoDeptAll.isIsTemp());
			baseUserInfo.setActive(userInfoDeptAll.isIsActive());
			// 调用业务模块
			int result = syncFacedManager.check(baseUserInfo);
			if (result == Constant.SUCCESS) {
				sucessNumber += 1;
				sendUserInfoDeptAllProcessReult.setResult(true);
			} else {
				failNumber += 1;
				sendUserInfoDeptAllProcessReult.setResult(false);
				sendUserInfoDeptAllProcessReult.setReason(Constant.UUMS_EXCEPTION);
				}
			} catch(CommException e){
				failNumber += 1;
				sendUserInfoDeptAllProcessReult.setResult(false);
				sendUserInfoDeptAllProcessReult.setReason(e.getMessage());
			} catch (Exception e) {
				logger.info("syncUserInfoDeptAll - message:",e);
				failNumber += 1;
				sendUserInfoDeptAllProcessReult.setResult(false);
				sendUserInfoDeptAllProcessReult.setReason(e.getMessage());
			} finally {
				sendUserInfoDeptAllProcessReult.setAccountChangeId(userInfoDeptAll.getAccountChangeId());
				sendUserInfoDeptAllProcessReult.setChangeType(userInfoDeptAll.getChangeType());
				sendUserInfoDeptAllProcessReult.setEmpCode(userInfoDeptAll.getEmpCode());
				processResults.add(sendUserInfoDeptAllProcessReult);
			}
		}
		sendUserInfoDeptAllResponse.setSuccessCount(sucessNumber);
		sendUserInfoDeptAllResponse.setFailedCount(failNumber);
		return sendUserInfoDeptAllResponse;
	}

	@Override
	public SendAdminOrgResponse syncAdminOrgInfo(
			SendAdminOrgRequest sendAdminOrgRequest) throws CommException {
		// 响应参数
		SendAdminOrgResponse sendAdminOrgResponse = new SendAdminOrgResponse();
		List<SendAdminOrgProcessReult> processResults = sendAdminOrgResponse.getProcessResult();
		SendAdminOrgProcessReult sendAdminOrgProcessReult;
		// base模块 职位信息对象
		com.deppon.crm.module.uums.shared.domain.OrgInfo baseAdminOrgInfo;
		int sucessNumber = 0;// 成功条数
		int failNumber = 0;// 失败条数

		for (AdminOrgInfo adminOrgInfo : sendAdminOrgRequest.getAdminOrgInfo()) {
			sendAdminOrgProcessReult = new SendAdminOrgProcessReult();
			baseAdminOrgInfo = new com.deppon.crm.module.uums.shared.domain.OrgInfo();
			// 对象赋值
			try {
			BeanUtils.copyProperties(adminOrgInfo, baseAdminOrgInfo);
			baseAdminOrgInfo.setTheChangeId(adminOrgInfo.getOrgChangeId());
			baseAdminOrgInfo.setTheMainCode(adminOrgInfo.getOrgCode());
			baseAdminOrgInfo.setTheMainId(adminOrgInfo.getOrgId());
			baseAdminOrgInfo.setChangeDate(convertToDate(adminOrgInfo.getChangeDate()));
			baseAdminOrgInfo.setChangeType(adminOrgInfo.getChangeType());
			baseAdminOrgInfo.setValidDate(convertToDate(adminOrgInfo.getValidDate()));
			baseAdminOrgInfo.setInvalidDate(convertToDate(adminOrgInfo.getInvalidDate()));
			//boolean类型值转化
			baseAdminOrgInfo.setCareerDept(adminOrgInfo.isIsCareerDept());
			baseAdminOrgInfo.setBigArea(adminOrgInfo.isIsBigArea());
			baseAdminOrgInfo.setSmallArea(adminOrgInfo.isIsSmallArea());
			baseAdminOrgInfo.setLeaf(adminOrgInfo.isIsLeaf());
				// 调用业务模块
			int result = syncFacedManager.check(baseAdminOrgInfo);
			if (result == Constant.SUCCESS) {
				sucessNumber += 1;
				sendAdminOrgProcessReult.setResult(true);
			} else {
				failNumber += 1;
				sendAdminOrgProcessReult.setResult(false);
				sendAdminOrgProcessReult.setReason(Constant.UUMS_EXCEPTION);
				}
			}catch(CommException e){
				failNumber += 1;
				sendAdminOrgProcessReult.setResult(false);
				sendAdminOrgProcessReult.setReason(e.getMessage());
			}catch (Exception e) {
				logger.info("syncAdminOrgInfo - message:",e);
				failNumber += 1;
				sendAdminOrgProcessReult.setResult(false);
				sendAdminOrgProcessReult.setReason(e.getMessage());
			} finally {
				sendAdminOrgProcessReult.setChangeType(adminOrgInfo.getChangeType());
				sendAdminOrgProcessReult.setOrgBenchmarkCode(adminOrgInfo.getOrgBenchmarkCode());
				sendAdminOrgProcessReult.setOrgChangeId(adminOrgInfo.getOrgChangeId());
				sendAdminOrgProcessReult.setOrgName(adminOrgInfo.getOrgName());
				processResults.add(sendAdminOrgProcessReult);
			}
		}
		sendAdminOrgResponse.setSuccessCount(sucessNumber);
		sendAdminOrgResponse.setFailedCount(failNumber);
		return sendAdminOrgResponse;
	}
	
	/**
	 * Description:syncFacedManager<br />
	 * @author CoCo
	 * @version 0.1 2013-12-5
	 */
	public com.deppon.crm.module.uums.server.manager.impl.syncFacedManager getSyncFacedManager() {
		return syncFacedManager;
	}

	/**
	 * Description:syncFacedManager<br />
	 * @author CoCo
	 * @version 0.1 2013-12-5
	 */
	public void setSyncFacedManager(
			com.deppon.crm.module.uums.server.manager.impl.syncFacedManager syncFacedManager) {
		this.syncFacedManager = syncFacedManager;
	}
	
	
	/**
	 * Description:positionSerive<br />
	 * @author CoCo
	 * @version 0.1 2013-12-9
	 */
	public com.deppon.crm.module.uums.server.service.Imp.PositionSerive getPositionSerive() {
		return positionSerive;
	}

	/**
	 * Description:positionSerive<br />
	 * @author CoCo
	 * @version 0.1 2013-12-9
	 */
	public void setPositionSerive(
			com.deppon.crm.module.uums.server.service.Imp.PositionSerive positionSerive) {
		this.positionSerive = positionSerive;
	}

	/**
	 * @Description:接口 XMLGregorianCalendar 转换 Date<br />
	 * @author CoCo
	 * @version 0.1 2013-12-9
	 * @param cal
	 * @return
	 * Date
	 */
	public  static Date convertToDate(XMLGregorianCalendar cal){
		Date date;
		if (cal == null) {
			date = null;
		}else {
			 GregorianCalendar ca = cal.toGregorianCalendar();
			 date = ca.getTime();
		}
        return date;
    }
}
