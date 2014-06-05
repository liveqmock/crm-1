package com.deppon.crm.module.report.server.manager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.deppon.crm.module.customer.shared.domain.CustCredit;

/**
 * <p>
 * Description: 信用较差客户报表<br/>
 * </p>
 * @title ICustCreditReportManager
 * @package com.deppon.crm.module.report.server.manager
 * @author andy
 * @version 1.0
 * @date 2014-03-25
 *
 */
public interface ICustCreditReportManager {

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
	List<CustCredit> getCustCreditListByConditions(String deptId, String date, int start,int limit) throws ParseException;
	
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
	int getCustCreditCountByConditions(String deptId, String date) throws ParseException;
	
	
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
	 * @throws ParseException
	 * 
	 */
	FileInputStream getExcel(String deptId, String date) throws ParseException, IOException;
}
