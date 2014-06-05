package com.deppon.crm.module.complaint.server.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.complaint.server.dao.ICellphoneMessageInfoDao;
import com.deppon.crm.module.complaint.shared.domain.CellphoneMessageInfo;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

public class CellphoneMessageInfoDaoImpl extends iBatis3DaoImpl implements ICellphoneMessageInfoDao {
	// 命名空间
	private static final String NAMESPACE_COMPLAINT = "com.deppon.crm.module.complaint.shared.domain.CellphoneMessageInfo.";
	// 插入短信列表
	private static final String SQL_INSERT_CELL_PHONE_MSG_INFO_LIST="insertCellphoneMsgInfoList";
	// 查询短信列表
	private static final String SQL_INQUIRE_CELL_PHONE_MSG_INFO="inquireCellphoneMsgInfo";
	// 更新
	private static final String SQL_UPDATE_CELL_PHONE_MSG_INFO="updateCellphoneMsgInfo";
	
	//批量更新
	private static final String SQL_UPDATE_CELL_PHONE_MSG_INFO_ALL = "updateCellphoneMsgInfoAll";
	/**
	 * 批量保存短信信息
	 * @param complaintInfoList
	 */
	@Override
	public void insertCellphoneMsgInfoList(
			List<CellphoneMessageInfo> cellphoneMessageInfoList) {
		// 批量保存短信信息
		for(CellphoneMessageInfo info:cellphoneMessageInfoList){
			this.getSqlSession().insert(NAMESPACE_COMPLAINT+SQL_INSERT_CELL_PHONE_MSG_INFO_LIST,info);
		}
	}
	/**
	 * 根据id更新记录
	 * @param id
	 */
	@Override
	public void updateCellphoneMsgInfoInfo(
			CellphoneMessageInfo cellphoneMessageInfo) {
		//  根据id更新记录
		this.getSqlSession().update(NAMESPACE_COMPLAINT+SQL_UPDATE_CELL_PHONE_MSG_INFO,cellphoneMessageInfo);
	}
	
	/**
	 * 根据id更新记录
	 * @param id
	 */
	@Override
	public void updateCellphoneMsgInfoInfoAll(
			List<String> ids) {
		Map<String,List> map = new HashMap<String,List>();
		map.put("ids", ids);
		//  根据id更新记录
		this.getSqlSession().update(NAMESPACE_COMPLAINT+SQL_UPDATE_CELL_PHONE_MSG_INFO_ALL,map);
	}
	/**
	 * 查询未发送的记录
	 * @return
	 */
	@Override
	public List<CellphoneMessageInfo> inquireCellphoneMsgInfo(
			CellphoneMessageInfo cellphoneMessageInfo) {
		Integer count=cellphoneMessageInfo.getPerCount();
		if(count==null){
			count=50;
		}
		// 分页限制
		RowBounds bounds = new RowBounds(0,count);
		// 查询未发送的记录
		return this.getSqlSession().selectList(NAMESPACE_COMPLAINT+SQL_INQUIRE_CELL_PHONE_MSG_INFO,cellphoneMessageInfo,bounds);
	}
}
