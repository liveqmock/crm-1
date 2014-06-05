package com.deppon.crm.module.common.server.action;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.file.action.FileUploadAction;
import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.crm.module.common.server.manager.ILadingstationDepartmentManager;
import com.deppon.crm.module.common.shared.domain.LadingstationDepartment;
import com.deppon.crm.module.common.shared.exception.LadingStationDepartmentException;
import com.deppon.crm.module.common.shared.exception.LadingStationDepartmentExceptionType;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.foss.framework.server.context.UserContext;


/**
 * 起始网点与受理部门关系的action
 * @author 邢悦
 *
 */
public class LadingStationDeptAction extends FileUploadAction {
	
	// 注入LadingstationDepartmentManager
	private ILadingstationDepartmentManager landStatDepartManager;	
	
	private List<LadingstationDepartment> landStatDepartList;
	
	/**
	 * 用于修改的LD对象的ID
	 */
	private String editLDId;
	
	/**
	 * 分页的起始和结束位
	 */
	private int start;
	
	private int limit;
	
	private Long totalCount;
	
	//始发网点的模糊查找
	private String startNetDeptName;
	
	//模糊查找出的始发网点集合
	private List<Department> startNetList;
	
	//始发网点ID
	private String startNetId;
	
	//受理部门ID
	private String acceptDeptId;
	
	//订单来源
	private String orderResource;
	
	//是否接货
	private String ifReceive;
	
	//始发网点受理部门关系的查询条件
	private LadingstationDepartment ldcmd;
	
	
	/**
	 * 根据条件查询起始网点与受理部门关系
	 * @return
	 */
	public String searchLadingStatDeptByCondition(){
		Map<String,Object> map=landStatDepartManager.searchByCondition(ldcmd, start, limit);
		landStatDepartList = (List<LadingstationDepartment>)map.get(ILadingstationDepartmentManager.LANDINGSTATIONDEPARTMENT_LIST);
		totalCount = Long.valueOf(map.get(ILadingstationDepartmentManager.LANDINGSTATIONDEPARTMENT_TOTAL_COUNT).toString());
		return SUCCESS;
	}

	/**
	 * 根据名字模糊查找起始网点
	 * @return
	 */
	public String searchStartNetByDeptName(){
		

		String passName="%";
		if(startNetDeptName!=null){
			passName=startNetDeptName+passName;
		}
		startNetList=landStatDepartManager.getDepartmentByDeptName(passName);
		
		return SUCCESS;
	}
	
	/**
	 * 新增始发网点与受理部门关系
	 * @return
	 */
	public String createLadingstationDepartment(){
		
		landStatDepartManager.createLadingstationDepartment(startNetId, acceptDeptId, orderResource, ifReceive, (User)(UserContext.getCurrentUser()));
		
		return SUCCESS;
	}
	
	/**
	 * 修改始发网点与受理部门关系
	 * @return
	 */
	public String editLadingstationDepartment(){
		LadingstationDepartment ld=new LadingstationDepartment();
		ld.setId(editLDId);
		ld.setIfReceive(ifReceive);
		ld.setResource(orderResource);
		Department beginLadingDept=new Department();
		Department acceptDept=new Department();
		beginLadingDept.setId(this.startNetId);
		acceptDept.setId(this.acceptDeptId);
		
		ld.setBeginLadingDeptN(beginLadingDept);
		ld.setAcceptDeptN(acceptDept);
		landStatDepartManager.edit(ld);
		return SUCCESS;
	}

	/**
	 * 批量导入始发网点与受理部门关系
	 * @return
	 */
	@SuppressWarnings("serial")
	public String importLadingstationDept(){
		String errorMessage=null;
		message=null;
		this.type="base";
			//文件上传
			fileUpload();
			
			if(isSuccess()){
				try{
					//如果上传没有错误才继续解析excel
					List<FileInfo> fileInfoList=this.getFileInfoList();
					FileInfo fileInfo=fileInfoList.get(0);
					String filePath=fileInfo.getSavePath();
					File importFile=new File(filePath);
					
					//这里进行业务校验
					errorMessage=landStatDepartManager.importLadingtationDepartments(importFile);
					
					//如果文件校验出现错误,则抛出信息
					LadingStationDepartmentException e = new LadingStationDepartmentException(LadingStationDepartmentExceptionType.FILE_CHECK_ERROR);
					throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
							new Object[] {}) {
					};					
					
				}catch(GeneralException e){
						message=messageBundle.getMessage(getLocale(),e.getErrorCode(),errorMessage);
					
				}
			}
	
		return SUCCESS;
	}
	
	/**
	 * 废除始发网点与受理部门关系
	 * @return
	 */
	public String invalidLadingstationDept(){
		landStatDepartManager.invalidLadingstationRelation(editLDId);
		return SUCCESS;
	}
	
	
	public void setLandStatDepartManager(
			ILadingstationDepartmentManager landStatDepartManager) {
		this.landStatDepartManager = landStatDepartManager;
	}



	public List<LadingstationDepartment> getLandStatDepartList() {
		return landStatDepartList;
	}



	public void setLandStatDepartList(
			List<LadingstationDepartment> landStatDepartList) {
		this.landStatDepartList = landStatDepartList;
	}



	public int getStart() {
		return start;
	}



	public void setStart(int start) {
		this.start = start;
	}



	public int getLimit() {
		return limit;
	}



	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getStartNetDeptName() {
		return startNetDeptName;
	}

	public void setStartNetDeptName(String startNetDeptName) {
		this.startNetDeptName = startNetDeptName;
	}

	public List<Department> getStartNetList() {
		return startNetList;
	}

	public void setStartNetList(List<Department> startNetList) {
		this.startNetList = startNetList;
	}

	public String getStartNetId() {
		return startNetId;
	}

	public void setStartNetId(String startNetId) {
		this.startNetId = startNetId;
	}

	public String getAcceptDeptId() {
		return acceptDeptId;
	}

	public void setAcceptDeptId(String acceptDeptId) {
		this.acceptDeptId = acceptDeptId;
	}

	public String getOrderResource() {
		return orderResource;
	}

	public void setOrderResource(String orderResource) {
		this.orderResource = orderResource;
	}

	public String getIfReceive() {
		return ifReceive;
	}

	public void setIfReceive(String ifReceive) {
		this.ifReceive = ifReceive;
	}


	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	//public void setMessageBundle(IMessageBundle messageBundle) {
	//	this.messageBundle = messageBundle;
	//}

	public String getEditLDId() {
		return editLDId;
	}

	public void setEditLDId(String editLDId) {
		this.editLDId = editLDId;
	}

	public LadingstationDepartment getLdcmd() {
		return ldcmd;
	}

	public void setLdcmd(LadingstationDepartment ldcmd) {
		this.ldcmd = ldcmd;
	}








	

	
	
}
