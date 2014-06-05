package com.deppon.crm.module.common.server.service.impl;

import com.deppon.crm.module.common.server.dao.IHardWareTokenDao;
import com.deppon.crm.module.common.server.service.IHardWareTokenService;
import com.deppon.crm.module.common.shared.domain.HardWareToken;

public class HardWareTokenService implements IHardWareTokenService {

	private IHardWareTokenDao iHardWareTokenDao = null;
	public IHardWareTokenDao getiHardWareTokenDao() {
		return iHardWareTokenDao;
	}

	public void setiHardWareTokenDao(IHardWareTokenDao iHardWareTokenDao) {
		this.iHardWareTokenDao = iHardWareTokenDao;
	}

	@Override
	public HardWareToken save(HardWareToken token) {
		return iHardWareTokenDao.save(token);
	}

	@Override
	public HardWareToken getHardWareTokenByToken(String token) {
		return iHardWareTokenDao.getHardWareTokenByToken(token);
	}

	@Override
	public HardWareToken getHardWareTokenById(String id) {
		return iHardWareTokenDao.getHardWareTokenById(id);
	}

	@Override
	public boolean update(HardWareToken token) {

		return iHardWareTokenDao.update(token);
	}

}
