package com.deppon.crm.module.report.server.action;

import java.io.FileInputStream;
import java.text.ParseException;
import java.util.List;

import com.deppon.crm.module.customer.server.manager.ICustCreditManager;
import com.deppon.crm.module.customer.server.utils.CustCreditUtil;
import com.deppon.crm.module.customer.shared.domain.CustCredit;
import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.report.server.manager.ICustCreditReportManager;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;
import com.deppon.foss.framework.server.web.action.AbstractAction;

/**
 *客户信用报表ACTION 
 *
 */
public class WorsecustReportAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	//查询的部门名称
	private String choosedept;  
	//查询的时间
	private String choosedate;  
	 //查询的部门id
	private String deptid; 
	//传入store的参数
	private List<CustCredit> custCreditList;   
	//导出文件的文件输入流
	private FileInputStream excelStream;  
	 //导出excel的文件名  
	private String fileName;                              
	private ICustCreditReportManager custCreditReportManager;
	private ICustCreditManager custCreditManager;
	private IDepartmentService departmentService;
	private int checkReportView;
	
	
	public int getCheckReportView() {
		return checkReportView;
	}
	public void setCheckReportView(int checkReportView) {
		this.checkReportView = checkReportView;
	}
	/**
	 * <p>
	 * Description:查询信用较差客户报表<br />
	 * </p>
	 * @title queryWorsecustReport
	 * @param deptid，choosedate，start，limit
	 * @author fbl
	 * @return custCreditList
	 * @exception ParseException
	 * @version 0.1 2014-3-27
	 */
	@JSON
	public String queryWorsecustReport(){
		try {
			//执行查询方法
			custCreditList = custCreditReportManager.getCustCreditListByConditions(deptid,choosedate, start, limit);
			//得到查询总数
			totalCount = Long.valueOf(custCreditReportManager.getCustCreditCountByConditions(deptid, choosedate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 验证是否有权限
	 * @return
	 * @throws Exception
	 */
	public String verificationAuth(){
		checkReportView = custCreditManager.custCreditAuthorization(deptid);
		return SUCCESS;
	}
	/**
	 * <p>
	 * Description:导出信用较差客户报表的excel<br />
	 * </p>
	 * @title exportWorsecustReport
	 * @param deptid,ddate
	 * @author fbl
	 * @return excelStream
	 * @exception ParseException，UnsupportedEncodingException
	 * @version 0.1 2014-3-27
	 * @throws Exception 
	 */
	public String exportWorsecustReport() throws Exception{
		try {
			String endDate = "";
			int day = CustCreditUtil.getDayForStr(choosedate, CustCreditUtil.DATE_FORMAT);
			if(day >= 16) {
				// 查询本月1号至15号的信用较差客户
				endDate = CustCreditUtil.dateFormat(CustCreditUtil.DATE_FORMAT, CustCreditUtil.getHalfMonthDate(choosedate, CustCreditUtil.DATE_FORMAT, 15));
			}else{
				// 查询上个月16号至上个月最后一天的信用较差客户
				endDate = CustCreditUtil.dateFormat(CustCreditUtil.DATE_FORMAT, CustCreditUtil.getEndDate(choosedate, CustCreditUtil.DATE_FORMAT));
			}
			Department dept = departmentService.getDepartmentById(deptid);
			//根据部门的名字和查询日期生成导出的文件名
			fileName = "信用较差客户明细表-"+dept.getDeptName()+"-"+endDate;
			//转换字符编码，解决导出文件名乱码的问题
			fileName = new String(fileName.getBytes("gb2312"), "iso8859-1");
			//导出文件
			excelStream=custCreditReportManager.getExcel(deptid,choosedate);
		} catch (ParseException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return SUCCESS;
	}
	/**
	 * Description 部门get方法
	 * @author fbl
	 */
	public String getChoosedept() {
		return choosedept;
	}
	/**
	 * Description 部门set方法
	 * @author fbl
	 */
	public void setChoosedept(String choosedept) {
		this.choosedept = choosedept;
	}
	/**
	 * Description 日期get方法
	 * @author fbl
	 */
	public String getChoosedate() {
		return choosedate;
	}
	/**
	 * Description 日期set方法
	 * @author fbl
	 */
	public void setChoosedate(String choosedate) {
		this.choosedate = choosedate;
	}
	/**
	 * Description 部门id的get方法
	 * @author fbl
	 */
	public String getDeptid() {
		return deptid;
	}
	/**
	 * Description 部门id的set方法
	 * @author fbl
	 */
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	/**
	 * Description 查询结果的get方法
	 * @author fbl
	 */
	public List<CustCredit> getCustCreditList() {
		return custCreditList;
	}
	/**
	 * Description 查询结果的set方法
	 * @author fbl
	 */
	public void setCustCreditList(List<CustCredit> custCreditList) {
		this.custCreditList = custCreditList;
	}
	/**
	 * Description 导出文件的get方法
	 * @author fbl
	 */
	public FileInputStream getExcelStream() {
		return excelStream;
	}
	/**
	 * Description 导出文件的set方法
	 * @author fbl
	 */
	public void setExcelStream(FileInputStream excelStream) {
		this.excelStream = excelStream;
	}
	/**
	 * Description 导出文件名的get方法
	 * @author fbl
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * Description 导出文件名的set方法
	 * @author fbl
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * Description 客户信用报表manager的set方法
	 * @author fbl
	 */
	public void setCustCreditReportManager(
			ICustCreditReportManager custCreditReportManager) {
		this.custCreditReportManager = custCreditReportManager;
	}
	/**
	 * Description 部门的Service 的set方法
	 * @author fbl
	 */
	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	public void setCustCreditManager(ICustCreditManager custCreditManager) {
		this.custCreditManager = custCreditManager;
	}
}
