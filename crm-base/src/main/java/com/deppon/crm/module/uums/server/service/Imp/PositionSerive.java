package com.deppon.crm.module.uums.server.service.Imp;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.uums.server.dao.IPositionDao;
import com.deppon.crm.module.uums.server.service.IPositionService;
import com.deppon.crm.module.uums.shared.domain.PostionInfo;

public class PositionSerive implements IPositionService{
	private IPositionDao positionDao;
	
	public IPositionDao getPositionDao() {
		return positionDao;
	}

	public void setPositionDao(IPositionDao positionDao) {
		this.positionDao = positionDao;
	}

	@Override
	public int insert(PostionInfo entity) {
		int res= positionDao.insert(entity);
		return res;
	}

	@Override
	public int updateById(PostionInfo entity, String positionId) {
		int res= positionDao.updateById(entity,positionId);
		return res;
	}

	@Override
	public int updateByCode(PostionInfo entity, String positionCode) {
		int res= positionDao.updateByCode(entity,positionCode);
		return res;
	}

	@Override
	public int deleteByCode(String positonCode) {
		if(StringUtils.isEmpty(positonCode)){
			return 1;
		}
		int res= positionDao.deleteByCode(positonCode);
		return res;
	}

	@Override
	public int deleteById(String positonId) {
		if(StringUtils.isEmpty(positonId)){
			return 1;
		}
		int res= positionDao.deleteById(positonId);
		return res;
	}

	@Override
	public PostionInfo searchById(String positionId) {
		PostionInfo res= positionDao.searchById(positionId);
		return res;
	}

	@Override
	public List<PostionInfo> searchByName(String positionName) {
		if(StringUtils.isEmpty(positionName)){
			return null;
		}
		List<PostionInfo> res= positionDao.searchByName(positionName);
		return res;
	}

	@Override
	public PostionInfo searchByCode(String positionCode) {
		if(StringUtils.isEmpty(positionCode)){
			return null;
		}
		PostionInfo res=positionDao.searchByCode(positionCode);
		return res;
	}
}
