package com.deppon.crm.module.workflow.shared.domain;

import java.util.List;

import com.deppon.crm.module.common.file.domain.FileInfo;

/**
 * 
 * <p>
 * Description:工作流审批模型<br />
 * </p>
 * @title WorkflowApprove.java
 * @package com.deppon.crm.module.workflow.shared.domain 
 * @author liuHuan
 * @version 0.1 2013-8-12
 */
public class WorkflowApprove {

		//工作流号
		private long processInstId;
		
		//审批类型
		private String approveOperateType;
		
		//审批意见 
		private String approveOpinion;
		
		//工作项id
		private long workItemId;
		
		//上传附件对象
		private List<FileInfo> fileInfoList;
		
		private String processInstIdEnc;
		
		private String workItemIdEnc;
		
		private String processDefName;

		public long getProcessInstId() {
			return processInstId;
		}

		public void setProcessInstId(long processInstId) {
			this.processInstId = processInstId;
		}

		public String getApproveOperateType() {
			return approveOperateType;
		}

		public void setApproveOperateType(String approveOperateType) {
			this.approveOperateType = approveOperateType;
		}

		public String getApproveOpinion() {
			return approveOpinion;
		}

		public void setApproveOpinion(String approveOpinion) {
			this.approveOpinion = approveOpinion;
		}

		public long getWorkItemId() {
			return workItemId;
		}

		public void setWorkItemId(long workItemId) {
			this.workItemId = workItemId;
		}

		public List<FileInfo> getFileInfoList() {
			return fileInfoList;
		}

		public void setFileInfoList(List<FileInfo> fileInfoList) {
			this.fileInfoList = fileInfoList;
		}

		public String getProcessInstIdEnc() {
			return processInstIdEnc;
		}

		public void setProcessInstIdEnc(String processInstIdEnc) {
			this.processInstIdEnc = processInstIdEnc;
		}

		public String getWorkItemIdEnc() {
			return workItemIdEnc;
		}

		public void setWorkItemIdEnc(String workItemIdEnc) {
			this.workItemIdEnc = workItemIdEnc;
		}

		public String getProcessDefName() {
			return processDefName;
		}

		public void setProcessDefName(String processDefName) {
			this.processDefName = processDefName;
		}
		
		
		
		
}
