package com.deppon.crm.module.common.server.manager.impl;

import java.util.List;

import com.deppon.crm.module.common.server.manager.IHelpDocManager;
import com.deppon.crm.module.common.server.service.IHelpDocService;
import com.deppon.crm.module.common.shared.domain.HelpDoc;
import com.deppon.crm.module.common.shared.domain.HelpDocSearchCondition;
import com.deppon.crm.module.common.shared.exception.HelpDocException;
import com.deppon.crm.module.common.shared.exception.HelpDocExceptionType;
import com.deppon.foss.framework.exception.GeneralException;

public class HelpDocManager implements IHelpDocManager {

	private IHelpDocService helpDocService;

	public IHelpDocService getHelpDocService() {
		return helpDocService;
	}

	public void setHelpDocService(IHelpDocService helpDocService) {
		this.helpDocService = helpDocService;
	}

	@Override
	public void addHelpDoc(HelpDoc helpDoc) {
		if (helpDoc != null && helpDoc.getWindowNum() != null) {
			HelpDoc oldHelp = helpDocService.getHelpDocByWindowNum(helpDoc
					.getWindowNum());
			if (oldHelp == null) {
				helpDocService.addHelpDoc(helpDoc);
			} else {
				HelpDocException he = new HelpDocException(
						HelpDocExceptionType.WINDOWNUM_EXISTED_EXCEPTION);
				throw new GeneralException(he.getErrorCode(), he.getMessage(),
						he, new Object[] {}) {
				};
			}
		} else {
			HelpDocException he = new HelpDocException(
					HelpDocExceptionType.HLEPDOC_EXCEPTION);
			throw new GeneralException(he.getErrorCode(), he.getMessage(), he,
					new Object[] {}) {
			};
		}
	}

	@Override
	public void updateHelpDocById(HelpDoc helpDoc) {
		if (helpDoc != null && helpDoc.getWindowNum() != null) {
			HelpDoc oldHelp = helpDocService.getHelpDocByWindowNum(helpDoc
					.getWindowNum());
			if (oldHelp == null
					|| (oldHelp != null && oldHelp.getId().equals(
							helpDoc.getId()))) {
				helpDocService.updateHelpDocById(helpDoc);
			} else {
				HelpDocException he = new HelpDocException(
						HelpDocExceptionType.WINDOWNUM_EXISTED_EXCEPTION);
				throw new GeneralException(he.getErrorCode(), he.getMessage(),
						he, new Object[] {}) {
				};
			}
		} else {
			HelpDocException he = new HelpDocException(
					HelpDocExceptionType.HLEPDOC_EXCEPTION);
			throw new GeneralException(he.getErrorCode(), he.getMessage(), he,
					new Object[] {}) {
			};
		}
	}

	@Override
	public void deleteHelpDocById(String helpId) {
		helpDocService.deleteHelpDocById(helpId);
	}

	@Override
	public void deleteHelpDocByIds(String[] helpIds) {
		for (String helpId : helpIds) {
			helpDocService.deleteHelpDocById(helpId);
		}
	}

	@Override
	public List<HelpDoc> searchHelpDocByCondition(
			HelpDocSearchCondition condition) {
		return helpDocService.searchHelpDocByCondition(condition);
	}

	@Override
	public HelpDoc getHelpDocById(String helpId) {
		return helpDocService.getHelpDocById(helpId);
	}

	@Override
	public HelpDoc getHelpDocByWindowNum(String windowNum) {
		return helpDocService.getHelpDocByWindowNum(windowNum);
	}

	@Override
	public int getCountByCondition(HelpDocSearchCondition condition) {
		return helpDocService.getCountByCondition(condition);
	}

}
