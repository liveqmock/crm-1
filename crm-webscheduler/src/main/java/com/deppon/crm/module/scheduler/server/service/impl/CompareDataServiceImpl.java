package com.deppon.crm.module.scheduler.server.service.impl;

import java.util.Map;

import com.deppon.crm.module.scheduler.server.dao.ICompareDataDao;
import com.deppon.crm.module.scheduler.server.service.ICompareDataService;
import com.deppon.crm.module.scheduler.shared.domain.CompareMember;
import com.deppon.crm.module.scheduler.shared.domain.CompareWorkflow;

/**
 * 
 * 
 * */
public class CompareDataServiceImpl implements ICompareDataService {
	private ICompareDataDao compareDataDao;

	/**
	 * @作者：罗典
	 * @描述：根据会员查询同步的客户信息
	 * @时间：2012-10-12
	 * @返回值：客户编码作为key的会员信息map
	 * */
	public Map<String, CompareMember> queryMember() {
		return compareDataDao.queryMember();
	}

	/**
	 * @作者：罗典
	 * @描述：根据联系人查询同步的客户信息
	 * @时间：2012-10-12
	 * @返回值：客户编码作为key的会员信息map
	 * */
	public Map<String, CompareMember> queryMemberByLinkman() {
		return compareDataDao.queryMemberByLinkman();
	}

	/**
	 * @作者：罗典
	 * @描述：根据账号查询同步的客户信息
	 * @时间：2012-10-12
	 * @返回值：客户编码作为key的会员信息map
	 * */
	public Map<String, CompareMember> queryMemberByAccount() {
		return compareDataDao.queryMemberByAccount();
	}

	/**
	 * @作者：罗典
	 * @描述：根据合同查询同步的客户信息
	 * @时间：2012-10-12
	 * @返回值：客户编码作为key的会员信息map
	 * */
	public Map<String, CompareMember> queryMemberByContract() {
		return compareDataDao.queryMemberByContract();
	}

	/**
	 * @作者：罗典
	 * @描述：查询前天生成的合同工作流信息
	 * @时间：2012-10-12
	 * @返回值：工作流号作为key的工作流信息
	 * */
	public Map<String, CompareWorkflow> queryContractWorkflow() {
		return compareDataDao.queryContractWorkflow();
	}

	/**
	 * @作者：罗典
	 * @描述：查询前天生成的正常理赔工作流信息
	 * @时间：2012-10-12
	 * @返回值：工作流号作为key的工作流信息
	 * */
	public Map<String, CompareWorkflow> queryNormalRecomWorkflow() {
		return compareDataDao.queryNormalRecomWorkflow();
	}

	/**
	 * @作者：罗典
	 * @描述：查询前天生成的多赔工作流信息
	 * @时间：2012-10-12
	 * @返回值：工作流号作为key的工作流信息
	 * */
	public Map<String, CompareWorkflow> queryMuchRecomWorkflow() {
		return compareDataDao.queryMuchRecomWorkflow();
	}

	public ICompareDataDao getCompareDataDao() {
		return compareDataDao;
	}

	public void setCompareDataDao(ICompareDataDao compareDataDao) {
		this.compareDataDao = compareDataDao;
	}

}
