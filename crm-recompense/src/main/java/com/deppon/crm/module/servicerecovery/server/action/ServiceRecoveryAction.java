package com.deppon.crm.module.servicerecovery.server.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.crm.module.common.file.util.ExcelExporter;
import com.deppon.crm.module.common.file.util.FileUtil;
import com.deppon.crm.module.common.file.util.PropertiesUtil;
import com.deppon.crm.module.common.server.util.DataDictionaryUtil;
import com.deppon.crm.module.common.shared.domain.DataHeadTypeEnum;
import com.deppon.crm.module.customer.server.manager.impl.BaseDataManager;
import com.deppon.crm.module.organization.server.service.IEmployeeService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.crm.module.servicerecovery.server.manager.IServiceRecoveryManager;
import com.deppon.crm.module.servicerecovery.shared.domain.ServiceRecovery;
import com.deppon.crm.module.servicerecovery.shared.domain.ServiceRecoverySearchCondition;
import com.deppon.crm.module.servicerecovery.shared.domain.Waybill;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;

/**
 * 
 * <p>
 * Description:服务补救<br />
 * </p>
 * @title ServiceRecoveryAction.java
 * @package com.deppon.crm.module.servicerecovery.server.action 
 * @author roy
 * @version 0.1 2013-5-22
 */
public class ServiceRecoveryAction extends AbstractAction {

	// 服务补救Manager实例
	private IServiceRecoveryManager serviceRecoveryManager;
	private IEmployeeService employeeService;
	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public void setServiceRecoveryManager(
			IServiceRecoveryManager serviceRecoveryManager) {
		this.serviceRecoveryManager = serviceRecoveryManager;
	}
	// 服务补救实例
	private ServiceRecovery serviceRecovery;

	public void setServiceRecovery(ServiceRecovery serviceRecovery) {
		this.serviceRecovery = serviceRecovery;
	}

	public ServiceRecovery getServiceRecovery() {
		return serviceRecovery;
	}

	private List<FileInfo> fileInfoList;

	public List<FileInfo> getFileInfoList() {
		return fileInfoList;
	}

	public void setFileInfoList(List<FileInfo> fileInfoList) {
		this.fileInfoList = fileInfoList;
	}

	// 前端传给后端的数据
	// waybillNumber
	private String waybillNumber;

	public void setWaybillNumber(String waybillNumber) {
		this.waybillNumber = waybillNumber;
	}

	// 服务补救condition
	private ServiceRecoverySearchCondition serviceRecoverySearchCondition;

	public void setServiceRecoverySearchCondition(
			ServiceRecoverySearchCondition serviceRecoverySearchCondition) {
		this.serviceRecoverySearchCondition = serviceRecoverySearchCondition;
	}

	public ServiceRecoverySearchCondition getServiceRecoverySearchCondition() {
		return serviceRecoverySearchCondition;
	}

	// limit start
	private int limit;
	private int start;

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public void setStart(int start) {
		this.start = start;
	}

	// 后端查询并传给前端的数据
	// waybill
	private Waybill waybill;

	public Waybill getWaybill() {
		return waybill;
	}
	List<Department> currentUserDeptList ;
	public List<Department> getCurrentUserDeptList() {
		return currentUserDeptList;
	}
	public void setCurrentUserDeptList(List<Department> currentUserDeptList) {
		this.currentUserDeptList = currentUserDeptList;
	}
	// 查询出来的服务补救列表
	private List<ServiceRecovery> serviceRecoveriesList;

	public List<ServiceRecovery> getServiceRecoveriesList() {
		return serviceRecoveriesList;
	}

	// 查询出来的总数
	private Long totalCount;

	public Long getTotalCount() {
		return totalCount;
	}

	// 导出的文件
	private String filePath;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	// 文件名称
	private String fileName;

	public String getFileName() {
		return fileName;
	}
	@JSON
	public String searchMyDataAuth(){
		currentUserDeptList = serviceRecoveryManager.searchMyDataAuth("default", start, limit);
		User user = (User) UserContext.getCurrentUser();
		if(currentUserDeptList!=null&&currentUserDeptList.size()!=0){
			Employee loginEmployee = employeeService.getEmpByCode(user.getEmpCode().getEmpCode());
			if("点部经理".equals(loginEmployee.getPosition())){
				currentUserDeptList.add(user.getEmpCode().getDeptId());
			}
		}else{
			currentUserDeptList = new ArrayList<Department>();
			currentUserDeptList.add(user.getEmpCode().getDeptId());
		}
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * Description:按运单号查询运单信息<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2012-11-3
	 * @return String
	 * @update 2012-11-3
	 */
	@JSON
	public String searchWayBillByNum() {
		//获取当前会话用户
		User user = (User) UserContext.getCurrentUser();
		//根据当前会话用户和运单号带出运单实体
		waybill = serviceRecoveryManager.findWaybillByNum(waybillNumber, user);
		return SUCCESS;
	}

	/**
	 * 
	 * <p>
	 * Description:根据填写信息新增服务补救<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2012-11-3
	 * @return String
	 * @update 2012-11-3
	 */
	public String addServiceRecoverySubmit() {
		//获取当前会话用户
		User user = (User) UserContext.getCurrentUser();
		//保存新增界面提交的服务补救信息
		serviceRecoveryManager.submitServiceRecovery(serviceRecovery, user);
		return SUCCESS;
	}

	/**
	 * 
	 * <p>
	 * Description:根据查询条件查询服务补救<br />
	 * </p>
	 * 
	 * @author zouming
	 * @version 0.1 2012-11-6
	 * @return String
	 * @update 2012-11-6
	 */
	@JSON
	public String searchServiceRecoveryByCondition() {
		serviceRecoverySearchCondition.setLimit(limit);
		//申请时间的起始时间
		serviceRecoverySearchCondition.setStart(start);
		//根据条件查询出服务补救的list
		serviceRecoveriesList = serviceRecoveryManager
				.searchServiceRecoveryByCondition(serviceRecoverySearchCondition);
		//根据条件统计出服务补救的总条数
		totalCount = Long.valueOf(serviceRecoveryManager
				.countServiceRecoveryByCondition(serviceRecoverySearchCondition));
		return SUCCESS;
	}

	/**
	 * 
	 * @Description: 查看服务补救
	 * @author huangzhanming
	 * @return
	 * @return String
	 * @date 2012-11-8 下午5:55:36
	 * @version V1.0
	 */
	@JSON
	public String findServiceRecoveryById() {
		//根据服务补救ID得到服务补救的实体
		serviceRecovery = serviceRecoveryManager
				.getServiceRecoveryById(serviceRecovery.getId());
		return SUCCESS;
	}

	/**
	 * 
	 * @Description: 导出文件action
	 * @author huangzhanming
	 * @return
	 * @return String
	 * @date 2012-11-8 上午10:41:15
	 * @version V1.0
	 */
	@JSON
	public String exportServiceRecoveryByCondition() {
		//根据服务补救的查询条件得到服务补救的list
		List<ServiceRecovery> sslist = serviceRecoveryManager
				.exportServiceRecoveryByCondition(serviceRecoverySearchCondition);
		//创建一个扩展名为xlsx的文件
		fileName = FileUtil.createFileName(".xlsx");
		//将list放入到指定文件名的文件中
		filePath = exportServiceRecoveryToExcel(sslist, fileName);
		return SUCCESS;
	}

	/**
	 * 
	 * @Description: 服务补救转为输出流
	 * @author huangzhanming
	 * @param sslist
	 * @return InputStream
	 * @date 2012-11-8 上午11:17:25
	 * @version V1.0
	 */
	public String exportServiceRecoveryToExcel(List<ServiceRecovery> sslist,
			String fileName) {
		String createPath = PropertiesUtil.getInstance()
				.getProperty("excelExportFilePath").trim();
		ExcelExporter exporter = new ExcelExporter();
		// 组织数据
		List<List<Object>> objList = transServiceRecoveryList(sslist);
		exporter.exportExcel(getHeaders(), objList, "ServiceRecovery",
				createPath, fileName);
		String realPath = createPath + "/" + fileName;
		return realPath;
	}

	/**
	 * 
	 * @Description: 转换服务补救数据为单元格数据
	 * @author huangzhanming
	 * @param sslist
	 * @return List<List<Object>>
	 * @date 2012-11-8 下午2:22:11
	 * @version V1.0
	 */
	public List<List<Object>> transServiceRecoveryList(
			List<ServiceRecovery> sslist) {
		List<List<Object>> objList = new ArrayList<List<Object>>();
		for (ServiceRecovery serviceRecovery : sslist) {
			List<Object> row = new ArrayList<Object>();
			row.add(serviceRecovery.getWaybillNumber());
			row.add(serviceRecovery.getOaWorkflowNum());
			row.add(serviceRecovery.getApplicantName());
			row.add(serviceRecovery.getSubsidiary());
			row.add(serviceRecovery.getCustomerName());
			row.add(serviceRecovery.getCustomerNum());
			
			if(serviceRecovery.getReductionType()!=null){
				String reductionType =  
						DataDictionaryUtil.getCodeDesc(
								DataHeadTypeEnum.SERVICERECOVERY_REDUCTION_TYPE, 
								serviceRecovery.getReductionType());	
				row.add(reductionType);
			}else{
				row.add("");
			}
			
			if(serviceRecovery.getCustomerType()!=null){
				String customerType =  
						DataDictionaryUtil.getCodeDesc(
								DataHeadTypeEnum.SERVICE_RECOVERY_CUSTOMER_TYPE, 
								serviceRecovery.getCustomerType());	
				row.add(customerType);
			}else{
				row.add("");
			}
			
			row.add(serviceRecovery.getReductionAmount());
			row.add(serviceRecovery.getWaybillAmount());
			
			Date obt = serviceRecovery.getOutboundTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			if(obt!=null){
				String obt01 = sdf.format(obt);
				row.add(obt01);
			}else{
				row.add("");
			}
			
			row.add(serviceRecovery.getDamagePackage());
			row.add(serviceRecovery.getOperatorName());
			row.add(serviceRecovery.getApplicantName());
			
			Date applyTime = serviceRecovery.getOutboundTime();
			if(applyTime!=null){
				String applyTime01 = sdf.format(applyTime);
				row.add(applyTime01);
			}else{
				row.add("");
			}
			
			if(serviceRecovery.getApplyStatus()!=null){
				String applyStatus =  
						DataDictionaryUtil.getCodeDesc(
								DataHeadTypeEnum.SERVICE_RECOVERY_AAPLY_TYPE, 
								serviceRecovery.getApplyStatus());	
				row.add(applyStatus);
			}else{
				row.add("");
			}
			row.add(serviceRecovery.getVerifyTime());
			row.add(serviceRecovery.getVerifierName());
			objList.add(row);
		}
		return objList;

	}

	/**
	 * 
	 * @Description: 生成表格行头
	 * @author huangzhanming
	 * @return List<String>
	 * @date 2012-11-8 下午2:28:50
	 * @version V1.0
	 */
	public List<String> getHeaders() {
		List<String> headers = new ArrayList<String>();
		headers.add("运单号");
		headers.add("工作流号");
		headers.add("部门名称");
		headers.add("所属子公司");
		headers.add("客户名称");
		headers.add("客户编码");
		headers.add("减免类别");
		headers.add("客户类型");
		headers.add("减免金额");
		headers.add("开单金额");
		headers.add("签收时间");
		headers.add("受损件数");
		headers.add("经手人");
		headers.add("申请人");
		headers.add("申请时间");
		headers.add("申请状态");
		headers.add("审批时间");
		headers.add("审批人");
		return headers;
	}

}
