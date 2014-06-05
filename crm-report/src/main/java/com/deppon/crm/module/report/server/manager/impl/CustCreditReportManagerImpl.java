package com.deppon.crm.module.report.server.manager.impl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.deppon.crm.module.customer.server.manager.ICustCreditManager;
import com.deppon.crm.module.customer.shared.domain.CustCredit;
import com.deppon.crm.module.report.server.manager.ICustCreditReportManager;

/**
 * <p>
 * Description: 信用较差客户报表<br/>
 * </p>
 * @title CustCreditReportManagerImpl
 * @package com.deppon.crm.module.report.server.manager.impl
 * @author andy
 * @version 1.0
 * @date 2014-03-25
 *
 */
public class CustCreditReportManagerImpl implements ICustCreditReportManager{
	/**
	 * 客户信用manager
	 */
	private ICustCreditManager custCreditManager;
	
	public ICustCreditManager getCustCreditManager() {
		return custCreditManager;
	}

	public void setCustCreditManager(ICustCreditManager custCreditManager) {
		this.custCreditManager = custCreditManager;
	}

	/**
	 * 
	 * <p>
	 * 信用较差客户报表查询<br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-25
	 * @param deptId 查询部门
	 * @param date 查询时间(半月报表)
	 * @param start
	 * @param limit
	 * @return List<CustCredit>
	 * @throws ParseException
	 * 
	 */
	@Override
	public List<CustCredit> getCustCreditListByConditions(String deptId,
			String date, int start,int limit) throws ParseException {
		return custCreditManager.getCustCreditListByConditions(deptId, date, start, limit);
	}
	
	/**
	 * 
	 * <p>
	 * 信用较差客户报表查询<br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-25
	 * @param deptId 查询部门
	 * @param date 查询时间(半月报表)
	 * @return int
	 * @throws ParseException
	 * 
	 */
	@Override
	public int getCustCreditCountByConditions(String deptId, String date) throws ParseException {
		return custCreditManager.getCustCreditListByConditions(deptId, date);
	}

	/**
	 * 
	 * <p>
	 * 信用较差客户报表导出<br />
	 * </p>
	 * @author andy
	 * @version 0.1 2014-3-25
	 * @param deptId 查询部门
	 * @param date 查询时间(半月报表)
	 * @return FileInputStream
	 * @throws ParseException, IOException
	 * 
	 */
	@Override
	public FileInputStream getExcel(String deptId, String date)
			throws ParseException, IOException {
		return custCreditManager.getExcel(deptId, date);
	}

}
