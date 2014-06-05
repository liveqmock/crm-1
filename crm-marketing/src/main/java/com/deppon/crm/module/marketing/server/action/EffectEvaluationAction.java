package com.deppon.crm.module.marketing.server.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.file.util.PropertiesUtil;
import com.deppon.crm.module.marketing.server.manager.IMarketAssessManager;
import com.deppon.crm.module.marketing.shared.domain.AssessAuthority;
import com.deppon.crm.module.marketing.shared.domain.AssessDevEffect;
import com.deppon.crm.module.marketing.shared.domain.AssessMaintainEffect;
import com.deppon.crm.module.marketing.shared.domain.AssessQueryCondition;
import com.deppon.crm.module.marketing.shared.domain.CustDevAssess;
import com.deppon.crm.module.marketing.shared.domain.CustMatAssess;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;
/**
 * 营销效果评估
 * @author 肖红叶
 *
 */
public class EffectEvaluationAction extends AbstractAction{
/*
 * 变量声明区-----------------------------------------------------------------------------------------------------
 */
	//注入的manager
	private IMarketAssessManager marketAssessManager;	
	//下拉框中的部门下拉列表
	private List<Department> departmentList;
	//用户权限实体
	private AssessAuthority assessAuthority;
	//上一级部门ID
	private String parentDeptId;
	//返回结果总条数
	private Long totalCount;
	//起始页
	private int start;
	//每页显示条数
	private int limit;
	//营销效果评估查询条件中的第一个部门ID
	private String firstDeptId;
	//营销效果评估查询条件中的第二个部门ID
	private String secondDeptId;	
	//营销效果查询条件中的查询结束时间
	private Date endDate;
	//开发效果评估查询结果列表
	private List<CustDevAssess> custDevAssessList;
	//维护效果评估查询结果列表
	private List<CustMatAssess> custMatAssessList;
	//导出文件的文件流
    private InputStream inputStream;
    //文件名
  	private String fileName;
  	private String filePath;
  	//标记在服务器端生成文件是否已经成功
  	private boolean importSuccess;
  	private CustDevAssess custDevAssess;
  	private CustMatAssess custMatAssess;
  	private String loadFileName; 
  	//客户来源
  	private String custSource;
  	//业务类型
  	private String businessType;

	/*
	 * action方法实现区---------------------------------------------------------------------------------------------
	 */
	/**
	 * 根据登录用户权限，生成用户权限实体
	 * @param args
	 */
	@JSON
	public String createAssessAuth(){
		User user=(User)UserContext.getCurrentUser();
		assessAuthority = marketAssessManager.createAssessAuth(user);
		return SUCCESS;
	}
		
	/**
	 * 查询组织架构-所有经营本部
	 */
	@JSON
	public String searchmanagerDept(){
		departmentList = marketAssessManager.searchmanagerDept();
		return SUCCESS;
	}	

	/**
	 * 根据上一级部门parentDeptId查询子级部门
	 */
	@JSON
	public String searchDeptsByParentId(){
		departmentList = marketAssessManager.searchDeptsByParentId(parentDeptId);
		return SUCCESS;
	}

	/**
	 * 根据客户开发效果的查询条件，返回查询结果
	 */
	@SuppressWarnings("unchecked")
	@JSON
	public String queryDevEffectResultByCondition(){
		User user=(User)UserContext.getCurrentUser();
		List<String> deptIdList = new ArrayList<String>();
		deptIdList.add(firstDeptId);
		deptIdList.add(secondDeptId);
		Map<String, Object> map = marketAssessManager.searchCustDevByAuth(deptIdList, endDate, user,custSource,businessType ,start, limit);
		custDevAssessList = (List<CustDevAssess>) map.get("custDevAssessList");
		totalCount = Long.valueOf( map.get("totalCount").toString());
		return SUCCESS;
	}	
	
	/**
	 * 查询开发合计
	 */
	@JSON
	public String searchSumCustDevByAuth(){
		User user=(User)UserContext.getCurrentUser();
		List<String> deptIdList = new ArrayList<String>();
		deptIdList.add(firstDeptId);
		deptIdList.add(secondDeptId);
		custDevAssess=marketAssessManager.searchSumCustDevByAuth(deptIdList, endDate, user,custSource,businessType);
		return SUCCESS;
	}	
	
	/**
	 * 根据客户维护效果的查询条件，返回查询结果
	 */
	@SuppressWarnings("unchecked")
	@JSON
	public String queryMaintainEffectResultByCondition(){
		User user=(User)UserContext.getCurrentUser();
		List<String> deptIdList = new ArrayList<String>();
		deptIdList.add(firstDeptId);
		deptIdList.add(secondDeptId);
		Map<String, Object> map = marketAssessManager.searchCustMatByAuth(deptIdList, endDate, user, start, limit);
		custMatAssessList =(List<CustMatAssess>) map.get("custMatAssessList");
		totalCount = Long.valueOf( map.get("totalCount").toString());
		return SUCCESS;
	}
	
	/**
	 * 查询维护合计
	 */
	@JSON
	public String searchSumCustMatByAuth(){
		User user=(User)UserContext.getCurrentUser();
		List<String> deptIdList = new ArrayList<String>();
		deptIdList.add(firstDeptId);
		deptIdList.add(secondDeptId);
		custMatAssess = marketAssessManager.searchSumCustMatByAuth(deptIdList, endDate, user);
		return SUCCESS;
	}
	
	
	

	/**
	 * 开发：导出权限下所有数据到服务器端生成一个Excel报表文件
	 * xiaohongye
	 */
	@JSON
	public String creatDevMarketEffectReportExcel(){
		filePath = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			fileName = new String((sdf.format(new Date())+"MarketingReport"+System.currentTimeMillis()+".xlsx").getBytes(),"UTF-8");
			filePath = PropertiesUtil.getInstance().getProperty("excelExportFilePath");
//			filePath = "D:";
		} catch (UnsupportedEncodingException e) {
			//导出失败的提示
		}
		User user=(User)UserContext.getCurrentUser();
		importSuccess = marketAssessManager.outputDevAssessExcelByAuth(fileName, filePath, endDate, user,custSource,businessType);	
		return SUCCESS;
	}
	
	/**
	 * 维护：导出权限下所有数据到服务器端生成一个Excel报表文件
	 * xiaohongye
	 */
	public String creatMaintainMarketEffectReportExcel(){
		filePath = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			fileName = new String((sdf.format(new Date())+"MarketingReport"+System.currentTimeMillis()+".xlsx").getBytes(),"UTF-8");
			filePath = PropertiesUtil.getInstance().getProperty("excelExportFilePath");
//			filePath = "D:";
		} catch (UnsupportedEncodingException e) {
			//导出失败的提示
		}
		User user=(User)UserContext.getCurrentUser();
		importSuccess = marketAssessManager.outputMatAssessExcelByAuth(fileName, filePath, endDate, user);	
		return SUCCESS;
	}
	
	/**
	 * 开发：导出当前查询条件下所有数据到服务器端生成一个Excel报表文件
	 * xiaohongye
	 */
	@JSON
	public String creatDevReportExcelByCondition(){
		filePath = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			fileName = new String((sdf.format(new Date())+"MarketingReport"+System.currentTimeMillis()+".xlsx").getBytes(),"UTF-8");
			filePath = PropertiesUtil.getInstance().getProperty("excelExportFilePath");
//			filePath = "D:";
		} catch (UnsupportedEncodingException e) {
			//导出失败的提示
		}
		User user=(User)UserContext.getCurrentUser();
		List<String> deptIdList = new ArrayList<String>();
		deptIdList.add(firstDeptId);
		deptIdList.add(secondDeptId);
		importSuccess = marketAssessManager.outputDevAssessExcelByPage(fileName, filePath, deptIdList, endDate, user,custSource,businessType);	
		return SUCCESS;
	}
	
	/**
	 * 维护：导出当前查询条件下所有数据到服务器端生成一个Excel报表文件
	 * xiaohongye
	 */
	@JSON
	public String creatMaintainReportExcelByCondition(){
		filePath = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			fileName = new String((sdf.format(new Date())+"MarketingReport"+System.currentTimeMillis()+".xlsx").getBytes(),"UTF-8");
			filePath = PropertiesUtil.getInstance().getProperty("excelExportFilePath");
//			filePath = "D:";
		} catch (UnsupportedEncodingException e) {
			//导出失败的提示
		}
		User user=(User)UserContext.getCurrentUser();
		List<String> deptIdList = new ArrayList<String>();
		deptIdList.add(firstDeptId);
		deptIdList.add(secondDeptId);
		importSuccess = marketAssessManager.outputMatAssessExcelByPage(fileName, filePath, deptIdList, endDate, user);	
		return SUCCESS;
	}
	
	/**
	 * 将成功导出的服务器端生成的excel报表文件下载到本地
	 * xiaohongye
	 */
	public String exportMarketEffectReportExcel(){
		filePath = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			loadFileName = sdf.format(new Date())+"MarketingReport.xlsx";
			filePath = PropertiesUtil.getInstance().getProperty("excelExportFilePath");
//			filePath = "D:";
			inputStream = new FileInputStream(new File(filePath, fileName));
		} catch (FileNotFoundException e) {
			//导出失败的提示
		}
		return SUCCESS;
	}
	
	/*
	 * get、set方法区----------------------------------------------------------------------------------------------
	 */
	public void setMarketAssessManager(IMarketAssessManager marketAssessManager) {
		this.marketAssessManager = marketAssessManager;
	}
	public List<Department> getDepartmentList() {
		return departmentList;
	}	
	public AssessAuthority getAssessAuthority() {
		return assessAuthority;
	}
	public void setParentDeptId(String parentDeptId) {
		this.parentDeptId = parentDeptId;
	}	
	public void setStart(int start) {
		this.start = start;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setFirstDeptId(String firstDeptId) {
		this.firstDeptId = firstDeptId;
	}
	public void setSecondDeptId(String secondDeptId) {
		this.secondDeptId = secondDeptId;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public List<CustDevAssess> getCustDevAssessList() {
		return custDevAssessList;
	}
	public List<CustMatAssess> getCustMatAssessList() {
		return custMatAssessList;
	}
	
	public InputStream getInputStream() {
		return inputStream;
	}
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public boolean isImportSuccess() {
		return importSuccess;
	}	
	public CustDevAssess getCustDevAssess() {
		return custDevAssess;
	}	
	public CustMatAssess getCustMatAssess() {
		return custMatAssess;
	}
	public String getLoadFileName() {
		return loadFileName;
	}
	/**
	 * @param custSource the custSource to set
	 */
	public void setCustSource(String custSource) {
		this.custSource = custSource;
	}

	/**
	 * @param businessType the businessType to set
	 */
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
}
