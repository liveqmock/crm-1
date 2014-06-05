package com.deppon.crm.module.complaint.server.service;

import java.util.List;

import com.deppon.crm.module.complaint.shared.domain.CellphoneMessageInfo;

/**
 * 
 * <p>
 * Description:短信通知Service<br />
 * </p>
 * @title IComplaintService.java
 * @package com.deppon.crm.module.complaint.server.service 
 * @author 邢悦
 * @version 0.1 2012-6-30
 */
public interface ICellphoneMessageInfoService {
	/**
	 * 批量保存短信信息
	 * 
	 * @param complaintInfoList
	 */
	 void insertCellphoneMsgInfoList(List<CellphoneMessageInfo> cellphoneMessageInfoList);

	/**
	 * 根据id更新记录
	 * 
	 * @param id
	 */
	 void updateCellphoneMsgInfoInfo(CellphoneMessageInfo cellphoneMessageInfo);

	/**
	 * 查询未发送的记录
	 * 
	 * @return
	 */
	 List<CellphoneMessageInfo> inquireCellphoneMsgInfo(CellphoneMessageInfo cellphoneMessageInfo);
	 /**
	 * 批量更新发送字段
	 * @param id
	 */
	void updateCellphoneMsgInfoInfoAll(List<String> ids);
}
