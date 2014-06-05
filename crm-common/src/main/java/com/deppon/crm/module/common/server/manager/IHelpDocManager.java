package com.deppon.crm.module.common.server.manager;

import java.util.List;

import com.deppon.crm.module.common.shared.domain.HelpDoc;
import com.deppon.crm.module.common.shared.domain.HelpDocSearchCondition;

public interface IHelpDocManager {

	public HelpDoc getHelpDocById(String helpId);

	public HelpDoc getHelpDocByWindowNum(String windowNum);

	public void addHelpDoc(HelpDoc helpDoc);

	public void updateHelpDocById(HelpDoc helpDoc);

	public void deleteHelpDocById(String helpId);

	public void deleteHelpDocByIds(String[] helpIds);

	public List<HelpDoc> searchHelpDocByCondition(
			HelpDocSearchCondition condition);

	public int getCountByCondition(HelpDocSearchCondition condition);

}
