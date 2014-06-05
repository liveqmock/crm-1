package com.deppon.crm.module.common.server.service.impl;

import java.util.List;

import com.deppon.crm.module.common.server.dao.IHelpDocDao;
import com.deppon.crm.module.common.server.service.IHelpDocService;
import com.deppon.crm.module.common.shared.domain.HelpDoc;
import com.deppon.crm.module.common.shared.domain.HelpDocSearchCondition;

public class HelpDocService implements IHelpDocService {

	private IHelpDocDao helpDocDao;

	public IHelpDocDao getHelpDocDao() {
		return helpDocDao;
	}

	public void setHelpDocDao(IHelpDocDao helpDocDao) {
		this.helpDocDao = helpDocDao;
	}

	@Override
	public void addHelpDoc(HelpDoc helpDoc) {
		helpDocDao.addHelpDoc(helpDoc);
	}

	@Override
	public void updateHelpDocById(HelpDoc helpDoc) {
		helpDocDao.updateHelpDocById(helpDoc);
	}

	@Override
	public void deleteHelpDocById(String helpId) {
		helpDocDao.deleteHelpDocById(helpId);
	}

	@Override
	public List<HelpDoc> searchHelpDocByCondition(
			HelpDocSearchCondition condition) {
		return helpDocDao.searchHelpDocByCondition(condition);
	}

	@Override
	public HelpDoc getHelpDocById(String helpId) {
		return helpDocDao.getHelpDocById(helpId);
	}

	@Override
	public HelpDoc getHelpDocByWindowNum(String windowNum) {
		return helpDocDao.getHelpDocByWindowNum(windowNum);
	}

	@Override
	public int getCountByCondition(HelpDocSearchCondition condition) {
		return helpDocDao.getCountByCondition(condition);
	}

}
