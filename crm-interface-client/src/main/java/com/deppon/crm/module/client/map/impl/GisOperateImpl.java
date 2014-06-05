package com.deppon.crm.module.client.map.impl;

import java.util.List;
import java.util.UUID;

import javax.xml.ws.Holder;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.common.exception.ErrorMessageCode;
import com.deppon.crm.module.client.common.util.ClientTool;
import com.deppon.crm.module.client.common.util.Constant;
import com.deppon.crm.module.client.common.util.NullOrEmptyValidator;
import com.deppon.crm.module.client.map.IGisOperate;
import com.deppon.crm.module.client.map.domain.CoordinateDetail;
import com.deppon.esb.esbtogis.CommonException;
import com.deppon.esb.esbtogis.GisService;
import com.deppon.esb.esbtogis.QueryDeptCoordinateRequest;
import com.deppon.esb.esbtogis.QueryDeptCoordinateResponse;
import com.deppon.esb.esbtogis.ESBHeader;

public class GisOperateImpl implements IGisOperate {

	private GisService gisService;
    
	@Override
	public List<CoordinateDetail> queryCoordinate(
			List<String> deptCode)
			throws CrmBusinessException {
		try {
			NullOrEmptyValidator.checkEmpty(deptCode, "deptCode");
			QueryDeptCoordinateRequest request=new QueryDeptCoordinateRequest();
			request.getDeptCode().addAll(deptCode);
			NullOrEmptyValidator.checkEmpty(request, "request");
			ESBHeader esbHeader = new ESBHeader();
			String businessId="";
			if(request!=null &&request.getDeptCode()!=null){
				businessId=request.getDeptCode().get(0);
			}
			esbHeader.setBusinessId(businessId);
			esbHeader.setVersion("0.1");
			esbHeader.setRequestId(UUID.randomUUID().toString());
			esbHeader.setSourceSystem("CRM");
			esbHeader.setExchangePattern(1);
			esbHeader.setMessageFormat("SOAP");
			esbHeader.setEsbServiceCode(Constant.ESB_GIS_DPETCOORDINATES);
			Holder<ESBHeader> holder = new Holder<ESBHeader>(esbHeader);
			QueryDeptCoordinateResponse response = gisService.queryCoordinate(request,holder);
			List<CoordinateDetail> details=ClientTool.convertGistoCoordinate(response);
			return details;
		}
		catch(CommonException e){
			e.printStackTrace();
			throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_SERVICE_INVOKE,e.getFaultInfo().getDetailedInfo());
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_SERVICE_INVOKE,e.getMessage());
		}
	}
	
	public GisService getGisService() {
		return gisService;
	}

	public void setGisService(GisService gisService) {
		this.gisService = gisService;
	}
}
