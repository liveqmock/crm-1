package com.deppon.crm.module.report.server.manager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.deppon.crm.module.marketingReport.server.dao.impl.WarnLostCustReportDaoImp;
import com.deppon.crm.module.marketingReport.server.manager.impl.WarnLostCustReportManagerImp;
import com.deppon.crm.module.report.server.dao.impl.KeycustReportDao;
import com.deppon.crm.module.report.server.util.TestUtil;
import com.deppon.crm.module.report.server.utils.Constant;

import junit.framework.TestCase;

public class WarnLostManagerTest extends TestCase {
	private WarnLostCustReportManagerImp warnLostCustReportManager;
	private String deptid;
	Date BeginTime;
	Date EndTime;
	private Map<String,String> paramsMap;
	public void setUp() {
		warnLostCustReportManager = (WarnLostCustReportManagerImp) TestUtil
				.getBean("WarnLostCustReportManager");
		deptid = "11469";
		paramsMap = new HashMap<String, String>();
		/*paramsMap.put("abSignNormalCount", Constant.ABSINGNORMALCOUNT);
		paramsMap.put("recompenseCount", Constant.RECOMPENSECOUNT);
		paramsMap.put("custNum",custNum);*/
	}

	
	public void test_queryShipmentQuality() {
		Calendar cl= Calendar.getInstance();
		cl.add(Calendar.DATE, -7);
		BeginTime=cl.getTime();
		EndTime=new Date();
		System.out.println(BeginTime);
		System.out.println(EndTime);
		/*warnLostCustReportManager.getWarnCustReportDaily(deptid, BeginTime, EndTime);
		warnLostCustReportManager.getWarnCustReportMonthly(deptid, BeginTime, EndTime);
		warnLostCustReportManager.getWarnCustReportMonthlyBatch("13840", BeginTime, EndTime);*/
		warnLostCustReportManager.getWarnCustReportDailyBatch(deptid, BeginTime, EndTime, 0, 10, "dep");
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:导出日报表<br />
	 * @author xiaohongye
	 * @version V0.1 
	 * @Date 2014-5-17
	 */
	public void testExportWarnLostCustReportExcel(){
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		SimpleDateFormat dfpath = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		try {
			String ExcelName=df.format(new Date());
			String ExcelPath=dfpath.format(new Date());
			Date beginTime = dfpath.parse("2014-05-05");
			Date endTime = dfpath.parse("2014-05-11");
			String deptid = "319";
			String deptLevel = "bigarea";
			String filepath="/misc/crmfile/warnLostCustReport/";
			InputStream excelStream=warnLostCustReportManager.exportExcel(deptid, beginTime, endTime,deptLevel);
			File filedir=new File(filepath+ExcelPath);                //新建文件目录 
			File file=new File(filepath+ExcelPath+"//"+ExcelName);     //新建文件  
			if(!filedir.exists()){                                //如果不存在目录则创建
				//新建目录
				filedir.mkdirs();
				//新建文件
				file.createNewFile();
			}else{
				file.createNewFile();
			}
			FileOutputStream fos=new FileOutputStream(file);
			int bytesRead = 0;
			   byte[] buffer = new byte[8192];
			   while ((bytesRead = excelStream.read(buffer, 0, 8192)) != -1) {
			    fos.write(buffer, 0, bytesRead);
			   }
			   fos.close();
			   String downloadurl=file.getAbsolutePath();  //向前台返回文件的下载url
			   System.out.println(downloadurl);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
