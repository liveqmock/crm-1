package com.deppon.crm.module.scheduler.server.dao.impl;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.scheduler.server.dao.ICompareDataDao;
import com.deppon.crm.module.scheduler.shared.domain.CompareMember;
import com.deppon.crm.module.scheduler.shared.domain.CompareWorkflow;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

@SuppressWarnings("unchecked")
public class CompareDataDaoImpl extends iBatis3DaoImpl implements
		ICompareDataDao {

	@Override
	public Map<String,CompareMember> queryMember() {
		Map<String,CompareMember> list = (Map<String,CompareMember>) this
				.getSqlSession()
				.selectMap(
						"com.deppon.crm.module.scheduler.shared.domain.CompareData.queryMemberData","custNumber");
		return list;
	}

	@Override
	public Map<String,CompareMember> queryMemberByLinkman() {
		Map<String,CompareMember> list = (Map<String,CompareMember>) this
				.getSqlSession()
				.selectMap(
						"com.deppon.crm.module.scheduler.shared.domain.CompareData.queryLinkman","custNumber");
		return list;
	}

	@Override
	public Map<String,CompareMember> queryMemberByAccount() {
		Map<String,CompareMember> list = (Map<String,CompareMember>) this
				.getSqlSession()
				.selectMap(
						"com.deppon.crm.module.scheduler.shared.domain.CompareData.queryAccount","custNumber");
		return list;
	}

	@Override
	public Map<String,CompareMember> queryMemberByContract() {
		Map<String,CompareMember> list = (Map<String,CompareMember>) this
				.getSqlSession()
				.selectMap(
						"com.deppon.crm.module.scheduler.shared.domain.CompareData.queryContract","custNumber");
		return list;
	}

	@Override
	public Map<String, CompareWorkflow> queryContractWorkflow() {
		Map<String,CompareWorkflow> list = (Map<String,CompareWorkflow>) this
				.getSqlSession()
				.selectMap(
						"com.deppon.crm.module.scheduler.shared.domain.CompareData.queryContractWorkflow","bizNum");
		return list;
	}

	@Override
	public Map<String, CompareWorkflow> queryNormalRecomWorkflow() {
		Map<String,CompareWorkflow> list = (Map<String,CompareWorkflow>) this
				.getSqlSession()
				.selectMap(
						"com.deppon.crm.module.scheduler.shared.domain.CompareData.queryMuchRecomWorkflow","bizNum");
		return list;
	}

	@Override
	public Map<String, CompareWorkflow> queryMuchRecomWorkflow() {
		Map<String,CompareWorkflow> list = (Map<String,CompareWorkflow>) this
				.getSqlSession()
				.selectMap(
						"com.deppon.crm.module.scheduler.shared.domain.CompareData.queryNormalRecomWorkflow","bizNum");
		return list;
	}

}
