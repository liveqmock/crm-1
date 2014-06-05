package com.deppon.crm.module.client.fin.impl;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.fin.IDepartmentOperate;
import com.deppon.crm.module.client.fin.domain.FINServiceLocator;
import com.deppon.fin.dept.WSInvokeException;

public class DepartmentOperateImpl implements IDepartmentOperate {
	private static Log logger = LogFactory.getLog(DepartmentOperateImpl.class);
	private FINServiceLocator financeService;

	/**
	 * @作者：罗典
	 * @时间：2012-9-4
	 * @描述：根据部门标杆编码从财务部门获取子公司名称
	 * */
	@Override
	public String querySubCompanyNameByCode(String deptCode)
			throws CrmBusinessException {
//		logger.info("deptCode: "+deptCode);
		if (deptCode == null || deptCode.equals("")) {
			throw new CrmBusinessException("0023");
		}
		String subCompanyName = "";
		try {
			//调用接口
			subCompanyName = financeService.getWSFinanceWebserviceFacade()
					.getComNameByAdminNumber(deptCode);
		} catch (ServiceException e) {
			throw new CrmBusinessException("1002", deptCode + e.getMessage());
		} catch(WSInvokeException e) {
			if(e.getFaultReason()!=null&&!"".equals(e.getFaultReason())){
				String errorInfo = e.getFaultReason()+"";
				int i = errorInfo.lastIndexOf(":")+1;
				int y = errorInfo.length()-1;
				String errorMsg = errorInfo.substring(i,y) ;
				throw new CrmBusinessException("1002", errorMsg);
			}
			throw new CrmBusinessException("1002", e.getMessage());
		}
		catch (RemoteException e) {
			throw new CrmBusinessException("1002", deptCode + e.getMessage());
		} catch (Exception e) {
			throw new CrmBusinessException("1002", e.getMessage());
		}
		return subCompanyName;
	}

	public FINServiceLocator getFinanceService() {
		return financeService;
	}

	public void setFinanceService(FINServiceLocator financeService) {
		this.financeService = financeService;
	}



}
