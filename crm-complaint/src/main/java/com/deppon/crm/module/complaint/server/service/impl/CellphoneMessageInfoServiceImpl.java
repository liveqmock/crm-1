package com.deppon.crm.module.complaint.server.service.impl;

import java.util.List;

import com.deppon.crm.module.complaint.server.dao.ICellphoneMessageInfoDao;
import com.deppon.crm.module.complaint.server.service.ICellphoneMessageInfoService;
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
public class CellphoneMessageInfoServiceImpl implements
		ICellphoneMessageInfoService {
	/**
	 * 短信信息
	 * @param cellphoneMessageInfoDao
	 */
	ICellphoneMessageInfoDao cellphoneMessageInfoDao;
	// cellphoneMessageInfoDao get方法
	public ICellphoneMessageInfoDao getCellphoneMessageInfoDao() {
		return cellphoneMessageInfoDao;
	}
	// cellphoneMessageInfoDao set方法
	public void setCellphoneMessageInfoDao(
			ICellphoneMessageInfoDao cellphoneMessageInfoDao) {
		this.cellphoneMessageInfoDao = cellphoneMessageInfoDao;
	}
	/**
	 * 批量保存短信信息
	 * @param complaintInfoList
	 */
	@Override
	public void insertCellphoneMsgInfoList(
			List<CellphoneMessageInfo> cellphoneMessageInfoList) {
		// 批量保存短信信息
		cellphoneMessageInfoDao.insertCellphoneMsgInfoList(cellphoneMessageInfoList);
	}
	/**
	 * 根据id更新记录
	 * @param id
	 */
	@Override
	public void updateCellphoneMsgInfoInfo(
			CellphoneMessageInfo cellphoneMessageInfo) {
		cellphoneMessageInfoDao.updateCellphoneMsgInfoInfo(cellphoneMessageInfo);

	}
	
	/**
	 * 批量更新发送字段
	 * @param id
	 */
	@Override
	public void updateCellphoneMsgInfoInfoAll(
			List<String> ids) {
		cellphoneMessageInfoDao.updateCellphoneMsgInfoInfoAll(ids);

	}
	/**
	 * 查询未发送的记录
	 * @return
	 */
	@Override
	public List<CellphoneMessageInfo> inquireCellphoneMsgInfo(
			CellphoneMessageInfo cellphoneMessageInfo) {
		return cellphoneMessageInfoDao.inquireCellphoneMsgInfo(cellphoneMessageInfo);
	}
}
