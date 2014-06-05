package com.deppon.crm.module.customer.shared.domain.integral;

import java.util.List;

import com.deppon.crm.module.common.file.domain.FileInfo;
import com.deppon.crm.module.customer.shared.domain.ExamineRecord;
/**
 * 
 * <p>
 * 联系人变更审批视图<br />
 * </p>
 * @title ContactVaryExaminView.java
 * @package com.deppon.crm.module.customer.shared.domain.integral 
 * @author bxj
 * @version 0.2 2012-4-28
 */
public class ContactVaryExaminView {
	//联系人变更
	private ContactVary contactVary;
	//审批历史记录
	private List<ExamineRecord> examineRecordList ;
	//附件
	private List<FileInfo> fileInfoList;
	//审批人
	private String currentExaminer;
	/**
	 * <p>
	 * Description:contactVary<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public ContactVary getContactVary() {
		return contactVary;
	}
	/**
	 * <p>
	 * Description:contactVary<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContactVary(ContactVary contactVary) {
		this.contactVary = contactVary;
	}
	/**
	 * <p>
	 * Description:examineRecordList<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public List<ExamineRecord> getExamineRecordList() {
		return examineRecordList;
	}
	/**
	 * <p>
	 * Description:examineRecordList<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setExamineRecordList(List<ExamineRecord> examineRecordList) {
		this.examineRecordList = examineRecordList;
	}
	/**
	 * <p>
	 * Description:fileInfoList<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public List<FileInfo> getFileInfoList() {
		return fileInfoList;
	}
	/**
	 * <p>
	 * Description:fileInfoList<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setFileInfoList(List<FileInfo> fileInfoList) {
		this.fileInfoList = fileInfoList;
	}
	/**
	 * <p>
	 * Description:currentExaminer<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getCurrentExaminer() {
		return currentExaminer;
	}
	/**
	 * <p>
	 * Description:currentExaminer<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCurrentExaminer(String currentExaminer) {
		this.currentExaminer = currentExaminer;
	}
	
}
