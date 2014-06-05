package com.deppon.crm.module.complaint.server.service.impl;

import java.util.List;

import com.deppon.crm.module.complaint.server.dao.IComplaintInfoDao;
import com.deppon.crm.module.complaint.server.service.IComplaintInfoService;
import com.deppon.crm.module.complaint.shared.domain.ComplaintInfo;

/**
 * @title ComplaintInfoServiceImpl.java
 * @package com.deppon.crm.module.complaint.server.service.impl 
 * @author justin.xu
 * @version 0.1 2012-6-20
 */
public class ComplaintInfoServiceImpl implements IComplaintInfoService{
	private IComplaintInfoDao  complaintInfoDao;
	/**
	 * 批量保存通知信息
	 * @param complaintInfoList
	 */
	@Override
	public void insertComplaintInfoList(List<ComplaintInfo> complaintInfoList) {
		complaintInfoDao.insertComplaintInfoList(complaintInfoList);
	}
	/**
	 * 根据id更新记录
	 * @param id
	 */
	@Override
	public void updateComplaintInfoInfo(ComplaintInfo complaintInfo) {
		complaintInfoDao.updateComplaintInfoInfo(complaintInfo);
	}
	/**
	 * 查询未发送的记录
	 * @return
	 */
	@Override
	public List<ComplaintInfo> inquireComplaintInfo(ComplaintInfo complaintInfo) {
		return complaintInfoDao.inquireComplaintInfo(complaintInfo);
	}

	public void setComplaintInfoDao(IComplaintInfoDao complaintInfoDao) {
		this.complaintInfoDao = complaintInfoDao;
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-7-23
	 * @param complaintInfos
	 * 
	 */
	@Override
	public void updateSusComplaintInfoList(List<String> ids) {
		complaintInfoDao.updateSusComplaintInfoList(ids);
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-7-23
	 * @param complaintInfos
	 * 
	 */
	@Override
	public void updateFailComplaintInfoList(List<String> ids) {
		complaintInfoDao.updateFailComplaintInfoList(ids);
	}
	
}
