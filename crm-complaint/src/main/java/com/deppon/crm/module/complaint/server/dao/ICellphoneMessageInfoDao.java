package com.deppon.crm.module.complaint.server.dao;

import java.util.List;

import com.deppon.crm.module.complaint.shared.domain.CellphoneMessageInfo;

public interface ICellphoneMessageInfoDao {
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
	 * 批量设置短信已经发送
	 * 
	 * @param id
	 */
	void updateCellphoneMsgInfoInfoAll(List<String> ids);
}
