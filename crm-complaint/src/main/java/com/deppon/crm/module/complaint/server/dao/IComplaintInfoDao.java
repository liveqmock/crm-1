package com.deppon.crm.module.complaint.server.dao;

import java.util.List;
import com.deppon.crm.module.complaint.shared.domain.ComplaintInfo;

public interface IComplaintInfoDao {
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
		 * @author roy
		 * @version 0.1 2013-7-23
		 * @param ids
		 * void
		 */
		void updateSusComplaintInfoList(List<String> ids);
		/**
		 * 
		 * <p>
		 * Description:这里写描述<br />
		 * </p>
		 * @author roy
		 * @version 0.1 2013-7-23
		 * @param ids
		 * void
		 */
		void updateFailComplaintInfoList(List<String> ids);
}
