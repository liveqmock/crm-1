package com.deppon.crm.module.client.sync.dao;

import java.util.List;

import com.deppon.crm.module.client.sync.domain.dto.TCustAccount;
import com.deppon.crm.module.client.sync.domain.dto.TCustAccountOperation;
import com.deppon.crm.module.client.sync.domain.dto.TCustContract;
import com.deppon.crm.module.client.sync.domain.dto.TCustContractOperation;
import com.deppon.crm.module.client.sync.domain.dto.TCustContractTaxOperation;
import com.deppon.crm.module.client.sync.domain.dto.TCustContractdept;
import com.deppon.crm.module.client.sync.domain.dto.TCustContractdeptOperation;
import com.deppon.crm.module.client.sync.domain.dto.TCustCustbasedata;
import com.deppon.crm.module.client.sync.domain.dto.TCustCustbasedataOperation;
import com.deppon.crm.module.client.sync.domain.dto.TCustCustlinkman;
import com.deppon.crm.module.client.sync.domain.dto.TCustCustlinkmanOperation;
import com.deppon.crm.module.client.sync.domain.dto.TCustPreferenceaddress;
import com.deppon.crm.module.client.sync.domain.dto.TCustPreferenceaddressOperation;
import com.deppon.crm.module.client.sync.domain.dto.TCustPreferential;
import com.deppon.crm.module.client.sync.domain.dto.TCustPreferentialOperation;
import com.deppon.crm.module.client.sync.domain.dto.TCustShuttleaddress;
import com.deppon.crm.module.client.sync.domain.dto.TCustShuttleaddressOperation;
import com.deppon.crm.module.client.sync.wdgh.CustDetalInfo;

public interface ISearchMemberInfosDao {

	/**
	 * 检索客户基础信息
	 * @param fid 主键
	 * @return
	 */
	public abstract TCustCustbasedataOperation searchMemberInfo(String fid);

	/**
	 * 检索联系人表
	 * @param fid 主键
	 * @return 
	 */
	public abstract TCustCustlinkman searchContactInfoById(String fid);

	/**
	 * 根据客户号检索全部的联系人信息
	 * @param customerId
	 * @return
	 */
	public abstract List<TCustCustlinkmanOperation> searchContactInfoByCustomerId(
			String memberId);

	/**
	 * 检索银行账户信息
	 * @param fid 主键
	 * @return
	 */
	public abstract TCustAccount searchAccountInfoById(String fid);

	/**
	 * 根据客户号检索全部的下属银行账户信息
	 * @param customerId
	 * @return
	 */
	public abstract List<TCustAccountOperation> searchAccountInfoByCustomId(
			String customerId);

	/**
	 * 检索合同信息
	 * @param fid 主键
	 * @return
	 */
	public abstract TCustContract searchContractInfoById(String fid);

	/**
	 * 根据客户号检索合同信息
	 * @param customerId
	 * @return
	 */
	public abstract List<TCustContractOperation> searchContractInfoByCustomerId(
			String customerId);

	/**
	 * 检索接送货地址
	 * @param fid 主键
	 * @return
	 */
	public abstract TCustShuttleaddress searchShuttleAddressById(String fid);

	/**
	 * 用客户基础信息表的fld检索客户的接送货地址列表
	 * @param fid 会员Id
	 * @return
	 */
	public abstract List<TCustShuttleaddressOperation> searchShuttleAddressByCustomerId(
			String memberId);

	/**
	 * 检索联系人-接送地址-关系
	 * @param fid 主键
	 * @return
	 */
	public abstract TCustPreferenceaddress searchPreferenceAddressById(
			String fid);

	/**
	 * 检索合同-部门关系对应
	 * @param fid 主键
	 * @return
	 */
	public abstract TCustContractdept searchContractDeptById(String fid);

	/**
	 * 根据合同Id检索全部的合同-部门关系对应信息
	 * @param contractId 合同Id
	 * @return
	 */
	public abstract List<TCustContractdeptOperation> searchContractDeptByContractId(
			String contractId);

	/**
	 * 根据合同Id检索全部的合同-合同发票标记子公司关系对应信息
	 * @param contractId 合同Id
	 * @return
	 */
	public abstract List<TCustContractTaxOperation> searchContractTaxByContractId(
			String contractId);
	
	/** 
	 * 检索优惠信息
	 * @param fid 主键
	 * @return
	 */
	public abstract TCustPreferential searchPreferentialById(String fid);

	/**
	 * 根据合同Id检索全部的优惠信息
	 * @param contractId 合同Id
	 * @return
	 */
	public abstract List<TCustPreferentialOperation> searchPreferentialByContractId(
			String contractId);

	/**
	 * 根据客户Id检索联系人-接送地址-关系数据
	 * @param memberId 会员Id
	 * @return
	 */
	public abstract List<TCustPreferenceaddressOperation> searchPreferenceAddressByMemberId(String memberId);


	/**
	 * 根据账户的Id取得对应的会员信息
	 * @param accountFid 账户表fid
	 * @return
	 */
	public abstract TCustCustbasedata getCustomerInfoByAccount(String accountFid);

	public abstract TCustCustbasedata getCustomerInfoByPreferenceAddress(String preferenceAddressFid);

	public abstract TCustCustbasedata getCustomerInfoByContractDept(String contractDeptFid);

	public abstract TCustCustbasedata getCustomerInfoByContract(String contractFid);

	public abstract TCustCustbasedata getCustomerInfoByLinkMan(String linkManFid);

	public abstract TCustCustbasedata getCustomerInfoByPreferential(String preferentialFid);

	public abstract TCustCustbasedata getCustomerInfoByShuttleAddress(String shuttleFid);

	/**
	 * @Description:发票子公司ID查询客户信息<br />
	 * @author CoCo
	 * @version 0.1 2013-11-15
	 * @param contractTaxFid
	 * @return
	 * TCustCustbasedata
	 */
	public abstract TCustCustbasedata getCustomerInfoByContractTax(String contractTaxFid);
	/**
	 * 根据ID获得Code
	 * @description 
	 * @author wugenbin_吴根斌
	 * @version 2013-5-24
	 * @param 
	 * @return
	 */
	public String getProvinceCode(String id);
	
	/**
	 * 根据ID获得Code
	 * @description 
	 * @author wugenbin_吴根斌
	 * @version 2013-5-24
	 * @param 
	 * @return
	 */
	public String getCityCode(String id);
	
	/**
	 * 
	 * @description 
	 * @author wugenbin_吴根斌
	 * @version 2013-5-24
	 * @param 
	 * @return
	 */
	public String getProvinceName(String id);
	/**
	 * 
	 * @description 
	 * @author wugenbin_吴根斌
	 * @version 2013-5-24
	 * @param 
	 * @return
	 */
	public String getCityName(String id);
	
	/**
	 * @author 
	 * @param memberId
	 * @return
	 */
	public CustDetalInfo getSenderWdghInfo(String memberId); 
	
}