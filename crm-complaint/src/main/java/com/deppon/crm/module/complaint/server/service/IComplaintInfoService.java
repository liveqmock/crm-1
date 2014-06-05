package com.deppon.crm.module.complaint.server.service;

import java.util.List;

import com.deppon.crm.module.complaint.shared.domain.ComplaintInfo;

/**
 * 
 * <p>
 * Description:投诉通知信息实现类<br />
 * </p>
 * @title IComplaintService.java
 * @package com.deppon.crm.module.complaint.server.service 
 * @author justin.xu
 * @version 0.1 2012-6-30
 */
public interface IComplaintInfoService {
	/**
	 * 批量保存通知信息
	 * @param complaintInfoList
	 */
	 void insertComplaintInfoList(List<ComplaintInfo> complaintInfoList);
	/**
	 * 根据id更新记录
	 * @param id
	 */
	 void updateComplaintInfoInfo(ComplaintInfo complaintInfo);
	
	/**
	 * 查询未发送的记录
	 * @return
	 */
	 List<ComplaintInfo> inquireComplaintInfo(ComplaintInfo complaintInfo);
	 
	 /**
		 * 
		 * <p>
		 * Description:这里写描述<br />
		 * </p>
		 * 
		 * @author roy
		 * @version 0.1 2013-7-23
		 * @param complaintInfo
		 *            void
		 */
		void updateSusComplaintInfoList(List<String> ids);
		/**
		 * 
		 * <p>
		 * Description:这里写描述<br />
		 * </p>
		 * @author roy
		 * @version 0.1 2013-7-23
		 * @param complaintInfos
		 * void
		 */
		void updateFailComplaintInfoList(List<String> ids);
}
