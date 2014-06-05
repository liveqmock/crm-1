package com.deppon.crm.module.client.complaint.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.deppon.cc.complaint.ReceiveWorkOrderIdServicePortType;
import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.common.exception.ErrorMessageCode;
import com.deppon.crm.module.client.common.util.NullOrEmptyValidator;
import com.deppon.crm.module.client.complaint.IComplaintOperate;

public class ComplaintOperateImpl implements IComplaintOperate {

	private ReceiveWorkOrderIdServicePortType complaintService;
	private ReceiveWorkOrderIdServicePortType hfComplaintService;
	
	private static Log log = LogFactory.getLog(ComplaintOperateImpl.class);
	@Override
	public boolean bindComplaintId2ServiceNumber(String serviceNumber,
			String complaintId) throws CrmBusinessException {
//		log.info("serviceNumber: "+serviceNumber+" complaintId: "+complaintId);
		String result = complaintService.receiveWorkOrderNumber(serviceNumber, complaintId);
		if (result==null || "".equals(result)) {
			return false;
		}
		String[] rs = result.split("!#!");
		if ("Y".equalsIgnoreCase(rs[0])) {
			return true;
		}else{
			throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_SERVICE_INVOKE,rs[1]);
		}
	}

	@Override
	public boolean bindHfComplaintId2ServiceNumber(String serviceNumber,
			String complaintId) throws CrmBusinessException {
		NullOrEmptyValidator.checkNull(serviceNumber,"serviceNumber");
		NullOrEmptyValidator.checkNull(complaintId,"complaintId");
		String result = hfComplaintService.receiveWorkOrderNumber(serviceNumber, complaintId);
		if (StringUtils.isEmpty(result)) {
			return false;
		}
		String[] rs = result.split("!#!");
		if ("Y".equalsIgnoreCase(rs[0])) {
			return true;
		}else{
			throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_SERVICE_INVOKE,rs[1]);
		}
	}

	public ReceiveWorkOrderIdServicePortType getComplaintService() {
		return complaintService;
	}

	public void setComplaintService(
			ReceiveWorkOrderIdServicePortType complaintService) {
		this.complaintService = complaintService;
	}

	/**
	 * Description:hfComplaintService<br />
	 * @author CoCo
	 * @version 0.1 2013-11-11
	 */
	public ReceiveWorkOrderIdServicePortType getHfComplaintService() {
		return hfComplaintService;
	}

	/**
	 * Description:hfComplaintService<br />
	 * @author CoCo
	 * @version 0.1 2013-11-11
	 */
	public void setHfComplaintService(
			ReceiveWorkOrderIdServicePortType hfComplaintService) {
		this.hfComplaintService = hfComplaintService;
	}
	
}
