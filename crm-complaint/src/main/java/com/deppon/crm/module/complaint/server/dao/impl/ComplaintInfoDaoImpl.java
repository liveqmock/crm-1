package com.deppon.crm.module.complaint.server.dao.impl;

import java.util.List;

import com.deppon.crm.module.complaint.server.dao.IComplaintInfoDao;
import com.deppon.crm.module.complaint.shared.domain.ComplaintInfo;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

public class ComplaintInfoDaoImpl extends iBatis3DaoImpl implements IComplaintInfoDao {

	private static final String NAMESPACE_COMPLAINT = "com.deppon.crm.module.complaint.shared.domain.ComplaintInfo.";
	/**
	 * 批量保存通知信息
	 * 
	 * @param complaintInfoList
	 */
	@Override
	public void insertComplaintInfoList(List<ComplaintInfo> complaintInfoList) {
		for(ComplaintInfo complaintInfo:complaintInfoList){
			this.getSqlSession().insert(NAMESPACE_COMPLAINT+"insertComplaintInfoList",complaintInfo);
		}
	}

	/**
	 * 根据id更新记录
	 * 
	 * @param id
	 */
	@Override
	public void updateComplaintInfoInfo(ComplaintInfo complaintInfo) {
		this.getSqlSession().update(NAMESPACE_COMPLAINT+"updateComplaintInfoInfo",complaintInfo);
	}
	/**
	 * 查询未发送的记录
	 * 
	 * @return
	 */
	@Override
	public List<ComplaintInfo> inquireComplaintInfo(ComplaintInfo complaintInfo) {
		return this.getSqlSession().selectList(NAMESPACE_COMPLAINT+"inquireComplaintInfo",complaintInfo);
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-7-23
	 * @param ids
	 *
	 */
	@Override
	public void updateSusComplaintInfoList(List<String> ids) {
		 this.getSqlSession().selectList(
				NAMESPACE_COMPLAINT + "updateSusComplaintInfoList", ids);		
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-7-23
	 * @param ids
	 *
	 */
	@Override
	public void updateFailComplaintInfoList(List<String> ids) {
		 this.getSqlSession().selectList(
				NAMESPACE_COMPLAINT + "updateFailComplaintInfoList", ids);		
	}
}