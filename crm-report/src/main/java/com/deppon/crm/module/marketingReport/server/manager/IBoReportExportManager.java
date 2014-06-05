package com.deppon.crm.module.marketingReport.server.manager;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import com.deppon.crm.module.marketing.shared.domain.BoReportCondition;
import com.deppon.crm.module.marketing.shared.domain.BoReportInfo;

/**
 * @description 客户360度视图manager接口
 * @author 安小虎
 * @version 0.1 2012-4-23
 * @date 2012-4-23
 */
public interface IBoReportExportManager {

	
	/**
	 * 
	 * <p>
	 * Description:获取 Excel 文件名 <br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-1-29
	 * @param searchOrderDateFrom
	 * @param searchOrderDateTo
	 * @return
	 * String
	 */
	String getExcrlName(Date searchOrderDateFrom,Date searchOrderDateTo,String deptName);
	
	/**
	 * 
	 * <p>
	 * Description: 创建 Excel<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-1-29
	 * @param boReportInfoList
	 * @param searchOrderDateFrom
	 * @param searchOrderDateTo
	 * @return
	 * InputStream
	 */
	String createExcel(List<BoReportInfo> boReportInfoList,
			BoReportCondition brc);
	
	/**
	 * 
	 * <p>
	 * Description: 创建 Excel<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-1-29
	 * @param realPath
	 * @return
	 * InputStream
	 */
	InputStream getExcel(String realPath);


	



	


}
