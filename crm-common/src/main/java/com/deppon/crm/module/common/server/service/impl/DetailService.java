package com.deppon.crm.module.common.server.service.impl;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.common.server.dao.IDetailDao;
import com.deppon.crm.module.common.server.service.IDetailService;
import com.deppon.crm.module.common.shared.domain.Detail;

public class DetailService implements IDetailService {
	
	private IDetailDao detailDao;

	@Override
	public void insertDetail(Detail detail) {
		detailDao.insertDetail(detail);
	}

	@Override
	public boolean updateDetail(Detail detail) {
		return detailDao.updateDetail(detail);
	}

	@Override
	public Detail queryDetailById(String id) {
		return detailDao.getDetailById(id);
	}
	/***
	 * 
	 * <p>
	 * Description:根据codeType以及描述信息查询,查询描述以及父级描述<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-7-20
	 * @param codeType
	 * @param codeDescs
	 * @return
	 * Map<String,String>
	 */
	public Map<String, Map<String, String>> queryMapCodeDesc(String codeType,String codeDescs){
		return detailDao.queryMapCodeDesc(codeType, codeDescs);
	}

	@Override
	public List<Detail> queryDetail(Detail detail) {
		return detailDao.getDetailByCondition(detail);
	}

	public void setDetailDao(IDetailDao detailDao) {
		this.detailDao = detailDao;
	}

	@Override
	public boolean delDetail(Detail detail) {
		return detailDao.delDetail(detail);
	}

	@Override
	public boolean updateDetailnew(Detail detail) {
		return detailDao.updateDetailnew(detail);
	}

	@Override
	public boolean deleteDetail(Detail detail) {
		return detailDao.deleteDetail(detail);
	}

	
	
}
