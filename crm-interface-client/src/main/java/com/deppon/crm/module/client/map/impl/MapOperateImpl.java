package com.deppon.crm.module.client.map.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.common.exception.ErrorMessageCode;
import com.deppon.crm.module.client.common.util.Check;
import com.deppon.crm.module.client.map.IMapOperate;
import com.deppon.crm.module.client.map.domain.AddressInfo;
import com.deppon.map.baidu.DeptByDistance;
import com.deppon.map.baidu.IMap2CrmService;
import com.deppon.map.baidu.MapWsException;

public class MapOperateImpl implements IMapOperate {

	private static Log log = LogFactory.getLog(MapOperateImpl.class);
	
	private IMap2CrmService map2CrmService;
	private final String MAPEXCEPTION_MSG = "地址无法解析"; 
	
	@Override
	@Deprecated
	public String findDeptCodeByAddress(AddressInfo addressInfo)
			throws CrmBusinessException {
		Check.notNull(addressInfo, String.format("%s arguments addressInfo can not be null", MapOperateImpl.class.getName()+"findDeptCodeByAddress"));
		
		return findDeptCodeByAddress(addressInfo.getAddress());
	}

	@Override
	public String findDeptCodeByAddress(String address)
			throws CrmBusinessException {
		Check.notNullOrEmpty(address, String.format("%s arguments cityName can not be null or empty", MapOperateImpl.class.getName()+"findDeptCodeByAddress"));
		try {
//			log.info("address: "+address);
			List<DeptByDistance> dps = map2CrmService.getFiveKmDepts(address).getItem();
			if (dps.size()<1) {
				if (log.isInfoEnabled()) {
					log.info(String.format("in %s can not find bussinessDept ", address));
				}
				return null;
			}
			if (log.isDebugEnabled()) {
				log.debug(dps.get(0).getDeptStandardcode());
			}
			return dps.get(0).getDeptStandardcode();
		} catch (MapWsException e) {
			if(e.getMessage() != null && e.getMessage().equals(MAPEXCEPTION_MSG)){
				log.error("address: "+address+e.getMessage());
				return null;
			}else{
				e.printStackTrace();
				throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_SERVICE_INVOKE, e,address+e.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_SERVICE_INVOKE, e,address+e.getMessage());
		}
	}

	public IMap2CrmService getMap2CrmService() {
		return map2CrmService;
	}

	public void setMap2CrmService(IMap2CrmService map2CrmService) {
		this.map2CrmService = map2CrmService;
	}
}
