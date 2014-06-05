package com.deppon.crm.module.scheduler.server.service;

import java.util.Map;

import com.deppon.crm.module.scheduler.shared.domain.CompareMember;
import com.deppon.crm.module.scheduler.shared.domain.CompareWorkflow;

public interface ICompareDataService {
	/**
	 * @作者：罗典
	 * @描述：根据会员查询同步的客户信息
	 * @时间：2012-10-12
	 * @返回值：客户编码作为key的会员信息map
	 * */
	public Map<String, CompareMember> queryMember();

	/**
	 * @作者：罗典
	 * @描述：根据联系人查询同步的客户信息
	 * @时间：2012-10-12
	 * @返回值：客户编码作为key的会员信息map
	 * */
	public Map<String, CompareMember> queryMemberByLinkman();

	/**
	 * @作者：罗典
	 * @描述：根据账号查询同步的客户信息
	 * @时间：2012-10-12
	 * @返回值：客户编码作为key的会员信息map
	 * */
	public Map<String, CompareMember> queryMemberByAccount();

	/**
	 * @作者：罗典
	 * @描述：根据合同查询同步的客户信息
	 * @时间：2012-10-12
	 * @返回值：客户编码作为key的会员信息map
	 * */
	public Map<String, CompareMember> queryMemberByContract();

	/**
	 * @作者：罗典
	 * @描述：查询前天生成的合同工作流信息
	 * @时间：2012-10-12
	 * @返回值：工作流号作为key的工作流信息
	 * */
	public Map<String, CompareWorkflow> queryContractWorkflow();

	/**
	 * @作者：罗典
	 * @描述：查询前天生成的正常理赔工作流信息
	 * @时间：2012-10-12
	 * @返回值：工作流号作为key的工作流信息
	 * */
	public Map<String, CompareWorkflow> queryNormalRecomWorkflow();

	/**
	 * @作者：罗典
	 * @描述：查询前天生成的多赔工作流信息
	 * @时间：2012-10-12
	 * @返回值：工作流号作为key的工作流信息
	 * */
	public Map<String, CompareWorkflow> queryMuchRecomWorkflow();

}
