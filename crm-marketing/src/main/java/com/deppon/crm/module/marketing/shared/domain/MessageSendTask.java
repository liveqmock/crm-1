package com.deppon.crm.module.marketing.shared.domain;

import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;
/**   
 * <p>
 * Description:营销短信发送任务实体<br />
 * </p>
 * @title MessageSendTask.java
 * @package com.deppon.crm.module.marketing.shared.domain
 * @author ZhouYuan
 * @version 0.1 2013-09-27
 */
public class MessageSendTask extends BaseEntity{
	//文件路径
	private String filePath;
	//文件名称
	private String fileName;
	//文件状态 0未执行 1执行中 2执行成功 3执行失败
	private String status;
	//异常终止是否因为接口不通(0否,1是)
	private String intStatus;
	//文件读取到哪一行
	private int startRow;
	//短信内容
	private String msgContent;
	//任务开始时间
	private Date beginDate;
	//任务结束时间
	private Date endDate;
	//发送部门标杆编码
	private String sendDept;
	//发送员工
	private String sendUser;
	
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getIntStatus() {
		return intStatus;
	}
	public void setIntStatus(String intStatus) {
		this.intStatus = intStatus;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getSendDept() {
		return sendDept;
	}
	public void setSendDept(String sendDept) {
		this.sendDept = sendDept;
	}
	public String getSendUser() {
		return sendUser;
	}
	public void setSendUser(String sendUser) {
		this.sendUser = sendUser;
	}
	
}
