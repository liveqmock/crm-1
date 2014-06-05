package com.deppon.crm.module.interfaces.stub;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.jws.WebService;
import javax.xml.ws.Holder;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.interfaces.workflow.IRecompenseOnlineService;
import com.deppon.crm.module.interfaces.workflow.domain.OnlineApplyInfo;
import com.deppon.crm.module.interfaces.workflow.domain.RecompenseOnlineQueryCondition;
import com.deppon.crm.module.interfaces.workflow.domain.RecompenseOnlineVerifyInfo;

@WebService(serviceName="RecompenseOnlineServiceImplService",portName="RecompenseOnlineServiceImplPort",endpointInterface="com.deppon.crm.module.interfaces.workflow.IRecompenseOnlineService")
public class RecompenseOnlineServiceTestImpl implements IRecompenseOnlineService {

	@Override
	public String createRecompenseOfOnline(OnlineApplyInfo onlineApplyInfo)
			throws CrmBusinessException {
		
		return UUID.randomUUID().toString();
	}

	@Override
	public boolean updateRecompenseOfOnline(OnlineApplyInfo onlineApplyInfo)
			throws CrmBusinessException {
		return true;
	}

	@Override
	public List<OnlineApplyInfo> queryRecompenseOnlineInfos(
			Holder<RecompenseOnlineQueryCondition> queryCondition)
			throws CrmBusinessException {
		queryCondition.value.setCount(1000);
		OnlineApplyInfo onlineApplyInfo = new OnlineApplyInfo();
		onlineApplyInfo.setRecompenseNumber("1");
		onlineApplyInfo.setClaimant("1");
		onlineApplyInfo.setRecompenseDate(new Date());
		onlineApplyInfo.setLastModifyTime(new Date());
		onlineApplyInfo.setPartA("1");
		onlineApplyInfo.setPartB("1");
		onlineApplyInfo.setPartAsign("1");
		onlineApplyInfo.setPartAsignDate(new Date());
		onlineApplyInfo.setPartBAsign("1");
		onlineApplyInfo.setPartBAsignDate(new Date());
		onlineApplyInfo.setRecompenseId("1");
		onlineApplyInfo.setWaybillNumber("1");
		onlineApplyInfo.setCustomerNum("1");
		onlineApplyInfo.setCustomerId("1");
		onlineApplyInfo.setRecompenseAmount(Double.valueOf("1"));
		onlineApplyInfo.setInsurAmount(Double.valueOf("1"));
		onlineApplyInfo.setRecompenseReason("1");
		onlineApplyInfo.setRejectReason("1");
		onlineApplyInfo.setApplyTime(new Date());
		onlineApplyInfo.setRejectTime(new Date());
		onlineApplyInfo.setIdCard("1");
		onlineApplyInfo.setBankName("1");
		onlineApplyInfo.setBranchName("1");
		onlineApplyInfo.setOpenName("1");
		onlineApplyInfo.setAccount("1");
		onlineApplyInfo.setMobile("1");
		onlineApplyInfo.setTelphone("1");
		onlineApplyInfo.setProvince("1");
		onlineApplyInfo.setCity("1");
		onlineApplyInfo.setCounty("1");
		onlineApplyInfo.setOnlineUser("1");
		onlineApplyInfo.setTransType("1");
		onlineApplyInfo.setStartStation("1");
		onlineApplyInfo.setEndStation("1");
		onlineApplyInfo.setSendDate(new Date());
		onlineApplyInfo.setApplyDeptId("1");
		onlineApplyInfo.setStatus("1");
		List<OnlineApplyInfo> oaiList = new ArrayList<OnlineApplyInfo>();
		for (int i = 0; i < 4; i++) {
			oaiList.add(onlineApplyInfo);
		}
		return oaiList;
	}

	@Override
	public OnlineApplyInfo queryRecompenseOnlineEntity(String recompenseCode,
			String username) throws CrmBusinessException {
		OnlineApplyInfo onlineApplyInfo = new OnlineApplyInfo();
		onlineApplyInfo.setRecompenseNumber("1");
		onlineApplyInfo.setClaimant("1");
		onlineApplyInfo.setRecompenseDate(new Date());
		onlineApplyInfo.setLastModifyTime(new Date());
		onlineApplyInfo.setPartA("1");
		onlineApplyInfo.setPartB("1");
		onlineApplyInfo.setPartAsign("1");
		onlineApplyInfo.setPartAsignDate(new Date());
		onlineApplyInfo.setPartBAsign("1");
		onlineApplyInfo.setPartBAsignDate(new Date());
		onlineApplyInfo.setRecompenseId("1");
		onlineApplyInfo.setWaybillNumber("1");
		onlineApplyInfo.setCustomerNum("1");
		onlineApplyInfo.setCustomerId("1");
		onlineApplyInfo.setRecompenseAmount(Double.valueOf("1"));
		onlineApplyInfo.setInsurAmount(Double.valueOf("1"));
		onlineApplyInfo.setRecompenseReason("1");
		onlineApplyInfo.setRejectReason("1");
		onlineApplyInfo.setApplyTime(new Date());
		onlineApplyInfo.setRejectTime(new Date());
		onlineApplyInfo.setIdCard("1");
		onlineApplyInfo.setBankName("1");
		onlineApplyInfo.setBranchName("1");
		onlineApplyInfo.setOpenName("1");
		onlineApplyInfo.setAccount("1");
		onlineApplyInfo.setMobile("1");
		onlineApplyInfo.setTelphone("1");
		onlineApplyInfo.setProvince("1");
		onlineApplyInfo.setCity("1");
		onlineApplyInfo.setCounty("1");
		onlineApplyInfo.setOnlineUser("1");
		onlineApplyInfo.setTransType("1");
		onlineApplyInfo.setStartStation("1");
		onlineApplyInfo.setEndStation("1");
		onlineApplyInfo.setSendDate(new Date());
		onlineApplyInfo.setApplyDeptId("1");
		onlineApplyInfo.setStatus("1");
		return onlineApplyInfo;
	}

	@Override
	public boolean cancelRecompenseOnline(String recompenseNumber,
			String username) throws CrmBusinessException {
		return true;
	}

	@Override
	public RecompenseOnlineVerifyInfo getRecompenseOnlineVerifyInfo(
			String waybillNumber) throws CrmBusinessException {
		RecompenseOnlineVerifyInfo recompenseOnlineVerifyInfo = new RecompenseOnlineVerifyInfo();
		recompenseOnlineVerifyInfo.setCanRecompense(true);
//		recompenseOnlineVerifyInfo.setShipperOnly(true);
		recompenseOnlineVerifyInfo.setShipperTelePhone("1");
		recompenseOnlineVerifyInfo.setShipperMobilePhone("1");
		recompenseOnlineVerifyInfo.setReceiveTelephone("1");
		recompenseOnlineVerifyInfo.setReceiveMobilePhone("1");
		recompenseOnlineVerifyInfo.setReceiveDept("1");
		recompenseOnlineVerifyInfo.setShipmentsDept("1");
		recompenseOnlineVerifyInfo.setInsurance(Double.valueOf(0));
		recompenseOnlineVerifyInfo.setStartStation("1");
		recompenseOnlineVerifyInfo.setDestinationStation("1");
		recompenseOnlineVerifyInfo.setShipmentsTime(new Date());
//		recompenseOnlineVerifyInfo.setCompanyName("1");
		recompenseOnlineVerifyInfo.setTransportType("1");
		return recompenseOnlineVerifyInfo;
	}

	@Override
	public boolean paymentUpdate(OnlineApplyInfo onlineApplyInfo) {
		return true;
	}

}
