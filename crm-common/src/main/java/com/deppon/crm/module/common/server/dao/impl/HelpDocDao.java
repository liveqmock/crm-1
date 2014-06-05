package com.deppon.crm.module.common.server.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.common.server.dao.IHelpDocDao;
import com.deppon.crm.module.common.shared.domain.HelpDoc;
import com.deppon.crm.module.common.shared.domain.HelpDocSearchCondition;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

public class HelpDocDao extends iBatis3DaoImpl implements IHelpDocDao {

	@Override
	public HelpDoc getHelpDocById(String helpId) {
		if (helpId != null && !"".equals(helpId)) {
			return (HelpDoc) getSqlSession()
					.selectOne(
							"com.deppon.crm.module.common.shared.domain.HelpDoc.getHelpDocById",
							helpId);
		}
		return null;
	}

	public HelpDoc getHelpDocByWindowNum(String windowNum) {
		if (windowNum != null && !"".equals(windowNum)) {
			return (HelpDoc) getSqlSession()
					.selectOne(
							"com.deppon.crm.module.common.shared.domain.HelpDoc.getHelpDocByWindowNum",
							windowNum);
		}
		return null;
	}

	@Override
	public void addHelpDoc(HelpDoc helpDoc) {
		if (helpDoc != null) {
			this.getSqlSession()
					.insert("com.deppon.crm.module.common.shared.domain.HelpDoc.addHelpDoc",
							helpDoc);
		}
	}

	@Override
	public void updateHelpDocById(HelpDoc helpDoc) {
		if (helpDoc != null && helpDoc.getId() != null
				&& !"".equals(helpDoc.getId())) {
			this.getSqlSession()
					.update("com.deppon.crm.module.common.shared.domain.HelpDoc.updateHelpDocById",
							helpDoc);
		}
	}

	@Override
	public void deleteHelpDocById(String helpId) {
		getSqlSession()
				.delete("com.deppon.crm.module.common.shared.domain.HelpDoc.deleteById",
						helpId);
	}

	@Override
	public List<HelpDoc> searchHelpDocByCondition(
			HelpDocSearchCondition condition) {
		if (condition.getLimit() == 0) {
			condition.setLimit(Integer.MAX_VALUE);
		}
		RowBounds bound = new RowBounds(condition.getStart(),
				condition.getLimit());
		List<HelpDoc> list = (List<HelpDoc>) getSqlSession()
				.selectList(
						"com.deppon.crm.module.common.shared.domain.HelpDoc.searchHelpDocByCondition",
						condition, bound);

		if (list == null) {
			list = new ArrayList<HelpDoc>();
		}
		return list;
	}

	@Override
	public int getCountByCondition(HelpDocSearchCondition condition) {
		int count = (Integer) getSqlSession()
				.selectOne(
						"com.deppon.crm.module.common.shared.domain.HelpDoc.getCountByCondition",
						condition);

		return count;
	}

}
