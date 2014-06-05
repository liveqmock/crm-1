package com.deppon.crm.module.client.sync.dao.impl;

import java.util.List;

import com.deppon.crm.module.client.sync.dao.ISearchMemberInfosDao;
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
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

/**
 * @author li
 * 客户主数据待发送日志表DAO 
 * */
public class SearchMemberInfosDaoImpl extends iBatis3DaoImpl implements ISearchMemberInfosDao  {
	private final String NAME_SPACE = "com.deppon.crm.module.client.sync.dao.impl.";
	/** 检索客户基础资料表 */
	private final String GET_MEMBER_BASE_INFO_BY_PRIMARY_KEY = NAME_SPACE + "TCustCustbasedataMapper.selectCustInfoByPrimaryKey";
	private final String GET_MEMBER_BASE_PROVINCE_BY_CODE_KEY = NAME_SPACE + "TCustCustbasedataMapper.selectProvinceCode";
	private final String GET_MEMBER_BASE_CITY_BY_CODE_KEY = NAME_SPACE + "TCustCustbasedataMapper.selectCityCode";
	private final String GET_MEMBER_BASE_PROVINCE_BY_NAME_KEY = NAME_SPACE + "TCustCustbasedataMapper.selectProvinceName";
	private final String GET_MEMBER_BASE_CITY_BY_NAME_KEY = NAME_SPACE + "TCustCustbasedataMapper.selectCityName";
	private final String GET_MEMBER_BASE_SEND_WDGH_INFO = NAME_SPACE+"TCustCustbasedataMapper.selectSendWdghInfo";
	/** 检索客户联系人表 */
	private final String GET_MEMBER_CONTACT_INFO_BY_PRIMARY_KEY = NAME_SPACE + "TCustCustlinkmanMapper.selectByPrimaryKey";
	private final String GET_MEMBER_CONTACT_INFO_BY_CUST_ID= NAME_SPACE + "TCustCustlinkmanMapper.selectByCustid";
	private final String GET_CUST_INFO_BY_MEMBER_CONTACT= NAME_SPACE + "TCustCustlinkmanMapper.searchCustomerInfo";
	/** 检索账户信息表 */
	private final String GET_ACCOUNT_INFO_BY_PRIMARY_KEY = NAME_SPACE + "TCustAccountMapper.selectByPrimaryKey";
	private final String GET_ACCOUNT_INFO_BY_CUST_ID = NAME_SPACE + "TCustAccountMapper.selectByBelongcustom";
	private final String GET_CUST_INFO_BY_ACCOUNT = NAME_SPACE + "TCustAccountMapper.searchCustomerInfo";
	/** 检索合同表 */
	private final String GET_CONTRACT_INFO_BY_PRIMARY_KEY = NAME_SPACE + "TCustContractMapper.selectContractInfoByPrimaryKey";
	private final String GET_CONTRACT_INFO_BY_CUST_ID = NAME_SPACE + "TCustContractMapper.selectByCustId";
	private final String GET_CUST_INFO_BY_CONTRACT_INFO = NAME_SPACE + "TCustContractMapper.searchCustomerInfo";
	/** 检索接送货地址 */
	private final String GET_SHUTTLE_ADDRESS_INFO_BY_PRIMARY_KEY = NAME_SPACE + "TCustShuttleaddressMapper.selectByPrimaryKeyJoinAddressName";
	private final String GET_SHUTTLE_ADDRESS_INFO_BY_CUST_ID = NAME_SPACE + "TCustShuttleaddressMapper.selectByMemberid";
	private final String GET_CUST_INFO_BY_SHUTTLE_ADDRESS = NAME_SPACE + "TCustShuttleaddressMapper.searchCustomerInfo";
	/** 联系人-接送货地址-relationship */
	private final String GET_PREFERENCE_ADDRESS_INFO_BY_PRIMARY_KEY = NAME_SPACE + "TCustPreferenceaddressMapper.selectByPrimaryKey";
	private final String GET_PREFERENCE_ADDRESS_INFO_BY_CUST_ID = NAME_SPACE + "TCustPreferenceaddressMapper.selectByCustID";
	private final String GET_CUST_INFO_BY_PREFERENCE_ADDRESS = NAME_SPACE + "TCustPreferenceaddressMapper.searchCustomerInfo";
	/** 合同-部门关系对应表 */
	private final String GET_PREFERENCE_CONTRACT_DEPT_BY_PRIMARY_KEY = NAME_SPACE + "TCustContractdeptMapper.selectContractDeptInfoByPrimaryKey";
	private final String GET_PREFERENCE_CONTRACT_DEPT_BY_CONTRACT_ID = NAME_SPACE + "TCustContractdeptMapper.selectByContractId";
	private final String GET_CUST_INFO_BY_PREFERENCE_CONTRACT_DEPT = NAME_SPACE + "TCustContractdeptMapper.searchCustomerInfo";
	/** 优惠信息 */
	private final String GET_PREFERENTIAL_BY_PRIMARY_KEY = NAME_SPACE + "TCustPreferentialMapper.selectByPrimaryKey";
	private final String GET_PREFERENTIAL_BY_CUST_ID = NAME_SPACE + "TCustPreferentialMapper.selectByContractId";
	private final String GET_CUST_INFO_BY_PREFERENTIAL = NAME_SPACE + "TCustPreferentialMapper.searchCustomerInfo";
	/**合同发票子公司*/
	private final String GET_CUST_INFO_BY_CONTRACT_CONTRACTTAX = NAME_SPACE + "TCustContractTaxMapper.searchCustomerInfo";
	private final String GET_CONTRACTTAX_INFO_BY_CONTRACT_ID = NAME_SPACE + "TCustContractTaxMapper.selectByContractId";
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.client.sync.dao.impl.ISearchMemberInfosDao#searchMemberInfo(java.lang.String)
	 */
	@Override
	public TCustCustbasedataOperation searchMemberInfo(String fid) {
		TCustCustbasedataOperation tCustCustbasedata = 
					(TCustCustbasedataOperation)this.getSqlSession().selectOne(GET_MEMBER_BASE_INFO_BY_PRIMARY_KEY, fid);;
		return tCustCustbasedata;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.client.sync.dao.impl.ISearchMemberInfosDao#searchContactInfoById(java.lang.String)
	 */
	@Override
	public TCustCustlinkman searchContactInfoById(String fid) {
		TCustCustlinkman tCustCustlinkman = (TCustCustlinkman)this.getSqlSession().selectOne(GET_MEMBER_CONTACT_INFO_BY_PRIMARY_KEY, fid);
		return tCustCustlinkman;
	}
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.client.sync.dao.impl.ISearchMemberInfosDao#searchContactInfoByCustomerId(java.lang.String)
	 */
	@Override
	public List<TCustCustlinkmanOperation> searchContactInfoByCustomerId(String memberId) {
		@SuppressWarnings("unchecked")
		List<TCustCustlinkmanOperation> tCustCustlinkmanOperationList =
				(List<TCustCustlinkmanOperation>)this.getSqlSession().selectList(GET_MEMBER_CONTACT_INFO_BY_CUST_ID, memberId);
		return tCustCustlinkmanOperationList;
	}
	
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.client.sync.dao.impl.ISearchMemberInfosDao#searchAccountInfoById(java.lang.String)
	 */
	@Override
	public TCustAccount searchAccountInfoById(String fid) {
		TCustAccount tCustCustAccount = (TCustAccount)this.getSqlSession().selectOne(GET_ACCOUNT_INFO_BY_PRIMARY_KEY, fid);
		return tCustCustAccount;
	}
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.client.sync.dao.impl.ISearchMemberInfosDao#searchAccountInfoByCustomId(java.lang.String)
	 */
	@Override
	public List<TCustAccountOperation> searchAccountInfoByCustomId(String customerId) {
		@SuppressWarnings("unchecked")
		List<TCustAccountOperation> tCustAccountOperationList = 
			(List<TCustAccountOperation>)this.getSqlSession().selectList(GET_ACCOUNT_INFO_BY_CUST_ID, customerId);
		return tCustAccountOperationList;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.client.sync.dao.impl.ISearchMemberInfosDao#searchContractInfoById(java.lang.String)
	 */
	@Override
	public TCustContract searchContractInfoById(String fid) {
		TCustContract tCustContract = (TCustContract)this.getSqlSession().selectOne(GET_CONTRACT_INFO_BY_PRIMARY_KEY, fid);
		return tCustContract;
	}
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.client.sync.dao.impl.ISearchMemberInfosDao#searchContractInfoByCustomerId(java.lang.String)
	 */
	@Override
	public List<TCustContractOperation> searchContractInfoByCustomerId(String customerId) {
		@SuppressWarnings("unchecked")
		List<TCustContractOperation> tCustContactInfoOperationList = 
			(List<TCustContractOperation>)this.getSqlSession().selectList(GET_CONTRACT_INFO_BY_CUST_ID, customerId);
		return tCustContactInfoOperationList;
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.client.sync.dao.impl.ISearchMemberInfosDao#searchShuttleAddressById(java.lang.String)
	 */
	@Override
	public TCustShuttleaddress searchShuttleAddressById(String fid) {
		TCustShuttleaddress tCustShuttleaddress = (TCustShuttleaddress)this.getSqlSession().selectOne(GET_SHUTTLE_ADDRESS_INFO_BY_PRIMARY_KEY, fid);
		return tCustShuttleaddress;
	}
	
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.client.sync.dao.impl.ISearchMemberInfosDao#searchShuttleAddressByCustomerId(java.lang.String)
	 */
	@Override
	public List<TCustShuttleaddressOperation> searchShuttleAddressByCustomerId(String memberId) {
		@SuppressWarnings("unchecked")
		List<TCustShuttleaddressOperation> tCustShuttleaddressOperationList = 
			(List<TCustShuttleaddressOperation>)this.getSqlSession().selectList(GET_SHUTTLE_ADDRESS_INFO_BY_CUST_ID, memberId);
		return tCustShuttleaddressOperationList;
	}
	
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.client.sync.dao.impl.ISearchMemberInfosDao#searchPreferenceAddressById(java.lang.String)
	 */
	@Override
	public TCustPreferenceaddress searchPreferenceAddressById(String fid) {
		TCustPreferenceaddress tCustPreferenceaddress = (TCustPreferenceaddress)this.getSqlSession().selectOne(GET_PREFERENCE_ADDRESS_INFO_BY_PRIMARY_KEY, fid);
		return tCustPreferenceaddress;
	}
	
	@Override
	public List<TCustPreferenceaddressOperation> searchPreferenceAddressByMemberId(String memberId) {
		@SuppressWarnings("unchecked")
		List<TCustPreferenceaddressOperation> tCustPreferenceaddressOperationList =
				(List<TCustPreferenceaddressOperation>)this.getSqlSession().selectList(GET_PREFERENCE_ADDRESS_INFO_BY_CUST_ID, memberId);
		return tCustPreferenceaddressOperationList;
	}
	
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.client.sync.dao.impl.ISearchMemberInfosDao#searchContractDeptById(java.lang.String)
	 */
	@Override
	public TCustContractdept searchContractDeptById(String fid) {
		TCustContractdept tCustContractdept = (TCustContractdept)this.getSqlSession().selectOne(GET_PREFERENCE_CONTRACT_DEPT_BY_PRIMARY_KEY, fid);
		return tCustContractdept;
	}
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.client.sync.dao.impl.ISearchMemberInfosDao#searchContractDeptByContractId(java.lang.String)
	 */
	@Override
	public List<TCustContractdeptOperation> searchContractDeptByContractId(String contractId) {
		@SuppressWarnings("unchecked")
		List<TCustContractdeptOperation> tCustContractdeptOperationList = 
				(List<TCustContractdeptOperation>)this.getSqlSession().selectList(GET_PREFERENCE_CONTRACT_DEPT_BY_CONTRACT_ID, contractId);
		return tCustContractdeptOperationList;
	}
	
	@Override
	public List<TCustContractTaxOperation> searchContractTaxByContractId(String contractId) {
		@SuppressWarnings("unchecked")
		List<TCustContractTaxOperation> tCustContractTaxOperationList = 
				(List<TCustContractTaxOperation>)this.getSqlSession().selectList(GET_CONTRACTTAX_INFO_BY_CONTRACT_ID, contractId);
		return tCustContractTaxOperationList;
	}
	
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.client.sync.dao.impl.ISearchMemberInfosDao#searchPreferentialById(java.lang.String)
	 */
	@Override
	public TCustPreferential searchPreferentialById(String fid) {
		TCustPreferential tCustPreferential = (TCustPreferential)this.getSqlSession().selectOne(GET_PREFERENTIAL_BY_PRIMARY_KEY, fid);
		return tCustPreferential;
	}
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.client.sync.dao.impl.ISearchMemberInfosDao#searchPreferentialByCustomerId(java.lang.String)
	 */
	@Override
	public List<TCustPreferentialOperation> searchPreferentialByContractId(String contractId) {
		@SuppressWarnings("unchecked")
		List<TCustPreferentialOperation> tCustPreferentialOperationList = 
			(List<TCustPreferentialOperation>)this.getSqlSession().selectList(GET_PREFERENTIAL_BY_CUST_ID, contractId);
		return tCustPreferentialOperationList;
	}

	@Override
	public TCustCustbasedata getCustomerInfoByAccount(String accountFid) {
		TCustCustbasedata custResult = (TCustCustbasedata)this.getSqlSession().selectOne(GET_CUST_INFO_BY_ACCOUNT, accountFid);
		return custResult;
	}

	@Override
	public TCustCustbasedata getCustomerInfoByContractDept (String contractDeptFid) {
		TCustCustbasedata custResult = (TCustCustbasedata)this.getSqlSession().selectOne(GET_CUST_INFO_BY_PREFERENCE_CONTRACT_DEPT, contractDeptFid);
		return custResult;
	}
	@Override
	public TCustCustbasedata getCustomerInfoByContractTax(String contractTaxFid) {
		TCustCustbasedata custResult = (TCustCustbasedata)this.getSqlSession().selectOne(GET_CUST_INFO_BY_CONTRACT_CONTRACTTAX, contractTaxFid);
		return custResult;
	}
	@Override
	public TCustCustbasedata getCustomerInfoByPreferenceAddress(String preferenceAddressFid) {
		TCustCustbasedata custResult = (TCustCustbasedata)this.getSqlSession().selectOne(GET_CUST_INFO_BY_PREFERENCE_ADDRESS, preferenceAddressFid);
		return custResult;
	}
	
	@Override
	public TCustCustbasedata getCustomerInfoByContract(String contractFid) {
		TCustCustbasedata custResult = (TCustCustbasedata)this.getSqlSession().selectOne(GET_CUST_INFO_BY_CONTRACT_INFO, contractFid);
		return custResult;
	}
	
	@Override
	public TCustCustbasedata getCustomerInfoByLinkMan(String linkManFid) {
		TCustCustbasedata custResult = (TCustCustbasedata)this.getSqlSession().selectOne(GET_CUST_INFO_BY_MEMBER_CONTACT, linkManFid);
		return custResult;
	}
	
	@Override
	public TCustCustbasedata getCustomerInfoByPreferential(String preferentialFid) {
		TCustCustbasedata custResult = (TCustCustbasedata)this.getSqlSession().selectOne(GET_CUST_INFO_BY_PREFERENTIAL, preferentialFid);
		return custResult;
	}
	
	@Override
	public TCustCustbasedata getCustomerInfoByShuttleAddress(String shuttleFid) {
		TCustCustbasedata custResult = (TCustCustbasedata)this.getSqlSession().selectOne(GET_CUST_INFO_BY_SHUTTLE_ADDRESS, shuttleFid);
		return custResult;
	}

	@Override
	public String getProvinceCode(String id) {
		return (String) this.getSqlSession().selectOne(GET_MEMBER_BASE_PROVINCE_BY_CODE_KEY, id);
	}

	@Override
	public String getCityCode(String id) {
		return (String) this.getSqlSession().selectOne(GET_MEMBER_BASE_CITY_BY_CODE_KEY, id);
	}

	@Override
	public String getProvinceName(String id) {
		return (String) this.getSqlSession().selectOne(GET_MEMBER_BASE_PROVINCE_BY_NAME_KEY, id);
	}

	@Override
	public String getCityName(String id) {
		return (String) this.getSqlSession().selectOne(GET_MEMBER_BASE_CITY_BY_NAME_KEY, id);
	}

	@Override
	public CustDetalInfo getSenderWdghInfo(String memberId) {
		return (CustDetalInfo) this.getSqlSession().selectOne(GET_MEMBER_BASE_SEND_WDGH_INFO,memberId);
	}
}