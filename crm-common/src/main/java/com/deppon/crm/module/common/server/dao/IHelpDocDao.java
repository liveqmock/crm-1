package com.deppon.crm.module.common.server.dao;

import java.util.List;

import com.deppon.crm.module.common.shared.domain.HelpDoc;
import com.deppon.crm.module.common.shared.domain.HelpDocSearchCondition;

public interface IHelpDocDao {

	public HelpDoc getHelpDocById(String helpId);

	public HelpDoc getHelpDocByWindowNum(String windowNum);

	public void addHelpDoc(HelpDoc helpDoc);

	public void updateHelpDocById(HelpDoc helpDoc);

	public void deleteHelpDocById(String helpId);

	public List<HelpDoc> searchHelpDocByCondition(
			HelpDocSearchCondition condition);

	public int getCountByCondition(HelpDocSearchCondition condition);

}
