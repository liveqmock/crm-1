package com.deppon.crm.module.backfreight.server.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.backfreight.server.manager.IBackFreightManager;
import com.deppon.crm.module.backfreight.shared.domain.BackFreight;
import com.deppon.crm.module.backfreight.shared.domain.BackFreightSearchCondition;
import com.deppon.crm.module.common.file.util.ExcelExporter;
import com.deppon.crm.module.common.file.util.FileUtil;
import com.deppon.crm.module.common.file.util.PropertiesUtil;
import com.deppon.crm.module.common.server.util.DataDictionaryUtil;
import com.deppon.crm.module.common.shared.domain.DataHeadTypeEnum;
import com.deppon.crm.module.servicerecovery.shared.domain.ServiceRecovery;
import com.deppon.crm.module.servicerecovery.shared.domain.Waybill;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;

/**
 * 
 * @description：退运费Action
 * @author 华龙
 * @version 1.0
 * @date 2012-10-29下午2:17:30
 */
public class BackFreightAction extends AbstractAction {
	
	
	//****set************
	//运单号
	private String waybillNumber;
	public void setWaybillNumber(String waybillNumber) {
		this.waybillNumber = waybillNumber;
	}
	//退运费ID
	private String backFreightId;
	
	public void setBackFreightId(String backFreightId) {
		this.backFreightId = backFreightId;
	}
	//****get************
	//运单信息
	private Waybill waybillInfo;
	
	public Waybill getWaybillInfo() {
		return waybillInfo;
	}
	
	//查询返回的退运费列表
	private List<BackFreight> backFreightList;
	//查询返回的退运费总数
	private Long totalCount;
	
	public List<BackFreight> getBackFreightList() {
		return backFreightList;
	}

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
	//****get与set************
	//退运费实体
	private BackFreight backFreight;
	
	public BackFreight getBackFreight() {
		return backFreight;
	}

	public void setBackFreight(BackFreight backFreight) {
		this.backFreight = backFreight;
	}
	
	//退运费condition
	private BackFreightSearchCondition backFreightSearchCondition;
	private int limit;
	private int start;
	
	public void setLimit(int limit) {
		this.limit = limit;
	}

	public void setStart(int start) {
		this.start = start;
	}
	
	public BackFreightSearchCondition getBackFreightSearchCondition() {
		return backFreightSearchCondition;
	}

	public void setBackFreightSearchCondition(
			BackFreightSearchCondition backFreightSearchCondition) {
		this.backFreightSearchCondition = backFreightSearchCondition;
	}
	//manager
	private IBackFreightManager backFreightManager;
	public void setBackFreightManager(IBackFreightManager backFreightManager) {
		this.backFreightManager = backFreightManager;
	}

	/**
	 * 
	 * <p>
	 * Description:根据运单号查询出运单信息<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2012-11-12
	 * @return
	 * String
	 * @update 2012-11-12
	 */
	@JSON
	public String searchWaybillByNum() {
		User user = (User) UserContext.getCurrentUser();
		waybillInfo = backFreightManager.findWaybillByNum(waybillNumber,user);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2012-11-13下午6:16:38
	 * @return
	 * String
	 * @update 2012-11-13下午6:16:38
	 */
	@JSON
	public String exportBackFreightByCondition() {
		//根据条件查询到要导出的退运费明细
		List<BackFreight> sslist = backFreightManager
				.exportBackFreightByCondition(backFreightSearchCondition);
		//文件类型
		fileName = FileUtil.createFileName(".xlsx");
		//文件路径
		filePath = exportBackFreightToExcel(sslist, fileName);
		return SUCCESS;
	}

	public String exportBackFreightToExcel(List<BackFreight> sslist,
			String fileName) {
		//创建文件路径
		String createPath = PropertiesUtil.getInstance()
				.getProperty("excelExportFilePath").trim();
		ExcelExporter exporter = new ExcelExporter();
		// 组织数据
		List<List<Object>> objList = transBackFreightList(sslist);
		exporter.exportExcel(getHeaders(), objList, "BackFreight",
				createPath, fileName);
		String realPath = createPath + "/" + fileName;
		return realPath;
	}
	
	/**
	 * 
	 * @Description: 转换退运费数据为单元格数据
	 * @author zouming
	 * @param sslist
	 * @return List<List<Object>>
	 * @date 2012-11-8 下午2:22:11
	 * @version V1.0
	 */
	public List<List<Object>> transBackFreightList(
			List<BackFreight> sslist) {
		List<List<Object>> objList = new ArrayList<List<Object>>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		//生成Excel数据
		for (BackFreight backFreight : sslist) {
			List<Object> row = new ArrayList<Object>();
			
			row.add(backFreight.getWaybillNumber());
			row.add(backFreight.getOaWorkflowNum());
			row.add(backFreight.getApplyDeptName());
			row.add(backFreight.getSubsidiary());
			row.add(backFreight.getCustomerName());
			
			if(backFreight.getTranType()!=null){
				String tranType =  
						DataDictionaryUtil.getCodeDesc(
								DataHeadTypeEnum.BACKFREIGHT_TRANSPORT_TYPE, 
								backFreight.getTranType());	
				row.add(tranType);
			}else{
				row.add("");
			}
			//通过数据字典转换
			if(backFreight.getPaymentType()!=null){
				String paymentType =  
						DataDictionaryUtil.getCodeDesc(
								DataHeadTypeEnum.BACKFREIGHT_PAY_TYPE, 
								backFreight.getPaymentType());	
				row.add(paymentType);
			}else{
				row.add("");
			}
			
			row.add(backFreight.getApplyAmount());
			//纯运费
			row.add(backFreight.getWaybillAmount());
			if(backFreight.getOutboundTime()!=null){
				row.add(sdf.format(backFreight.getOutboundTime()));
			}else{
				row.add("");
			}
			row.add(backFreight.getApplicantName());
			if(backFreight.getApplyTime()!=null){
				row.add(sdf.format(backFreight.getApplyTime()));
			}else{
				row.add("");
			}
			
			if(backFreight.getApplyStatus()!=null){
				String applyStatus =  
						DataDictionaryUtil.getCodeDesc(
								DataHeadTypeEnum.BACKFREIGHT_AAPLY_TYPE,  //审批状态
								backFreight.getApplyStatus());	
				row.add(applyStatus);
			}else{
				row.add("");
			}
			
			row.add(backFreight.getVerifierName());
			if(backFreight.getVerifyTime()!=null){
				row.add(sdf.format(backFreight.getVerifyTime()));
			}else{
				row.add("");
			}
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
		headers.add("运输性质");
		headers.add("付款方式");
		headers.add("申请金额");
		headers.add("运费");
		headers.add("签收时间");
		headers.add("申请人");
		headers.add("申请时间");
		headers.add("申请状态");
		headers.add("审批人");
		headers.add("审批时间");
		return headers;
	}
	/**
	 * 
	 * <p>
	 * Description:根据页面信息新增退运费<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2012-11-12下午2:55:56
	 * @return
	 * String
	 * @update 2012-11-12下午2:55:56
	 */
	@JSON
	public String submitBackFreight() {
		User user = (User) UserContext.getCurrentUser();
		//提交退运费
		backFreightManager.submitBackFreight(backFreight,user);
		return SUCCESS;
	}

	/**
	 * 
	 * <p>
	 * Description:根据用户输入条件查询退运费信息<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2012-11-12下午2:55:38
	 * @return
	 * String
	 * @update 2012-11-12下午2:55:38
	 */
	@JSON
	public String searchBackFreightByCondition() {
		//每页条数及限制
		backFreightSearchCondition.setLimit(limit);
		backFreightSearchCondition.setStart(start);
		
		backFreightList = backFreightManager.searchBackFreightByCondition(backFreightSearchCondition);
		totalCount = Long.valueOf(backFreightManager.countBackFreightByCondition(backFreightSearchCondition));
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:根据退运费ID查询退运费信息<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2012-11-12下午4:50:40
	 * @return
	 * String
	 * @update 2012-11-12下午4:50:40
	 */
	@JSON
	public String findBackFreightById(){
		backFreight = backFreightManager.getBackFreightById(backFreightId);
		return SUCCESS;
		
	}
}