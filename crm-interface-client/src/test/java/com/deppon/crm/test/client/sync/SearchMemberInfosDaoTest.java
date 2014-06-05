package com.deppon.crm.test.client.sync;

import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.common.util.ClientTool;
import com.deppon.crm.module.client.sync.dao.ISearchMemberInfosDao;
import com.deppon.crm.module.client.sync.domain.WaitingSendRequest;
import com.deppon.crm.module.client.sync.domain.dto.TCustAccount;
import com.deppon.crm.module.client.sync.domain.dto.TCustAccountOperation;
import com.deppon.crm.module.client.sync.domain.dto.TCustContract;
import com.deppon.crm.module.client.sync.domain.dto.TCustContractOperation;
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
import com.deppon.crm.module.client.sync.foss.WaitingSendFossRequest;

public class SearchMemberInfosDaoTest extends TestCase {

	ClassPathXmlApplicationContext factory;
	ISearchMemberInfosDao searchMemberInfosDao;

	
	@Before
	protected void setUp() throws Exception {
		String resource = "com/deppon/crm/module/client/server/META-INF/spring.sync.api.xml";
		factory = new ClassPathXmlApplicationContext(resource);
		searchMemberInfosDao = (ISearchMemberInfosDao) factory
				.getBean("SearchMemberInfosDao");
	}

	
	 
	@After
	protected void tearDown() throws Exception {
	}

	@Test
	public void testSearchMemberInfo() {
		TCustCustbasedata tCustCustbasedata = searchMemberInfosDao
				.searchMemberInfo("40000504");
		assertEquals("111",tCustCustbasedata.getFcustname());
		assertEquals("DP01563", tCustCustbasedata.getFdeptidStandardcode());
	}

	/*@Test
	public void testSearchContactInfo() {
		String fid = "89";
		TCustCustlinkman tCustCustlinkman = searchMemberInfosDao.searchContactInfoById(fid);
		assertEquals(tCustCustlinkman.getFmobiletel(), "13812052801");
		
		TCustCustbasedata cust = searchMemberInfosDao.getCustomerInfoByLinkMan(tCustCustlinkman.getFid().toString());
		assertEquals(1776, cust.getFid().longValue());
		assertEquals("DP01563", cust.getFdeptidStandardcode());
	}*/

	/*@Test
	public final void testSearchContactInfoByCustomerId() {
		String memberId = "1776";
		List<TCustCustlinkmanOperation> result = searchMemberInfosDao.searchContactInfoByCustomerId(memberId);
		assertEquals(1, result.size());
	}

	/*@Test
	public final void testSearchAccountInfoById() {
		String fid = "13063";
		TCustAccount result = searchMemberInfosDao.searchAccountInfoById(fid);
		assertEquals("BANK OF CHINA", result.getFbank());

		TCustCustbasedata cust = searchMemberInfosDao.getCustomerInfoByAccount(result.getFid().toString());
		assertEquals(1776, cust.getFid().longValue());
		assertEquals("DP01563", cust.getFdeptidStandardcode());
	}

	@Test
	public final void testSearchAccountInfoByCustomId() {
		String memberId ="1776";
		List<TCustAccountOperation> result = searchMemberInfosDao.searchAccountInfoByCustomId(memberId);
		assertEquals(1, result.size());
	}*/
/*
	@Test
	public final void testSearchContractInfoById() {
		String fid = "400275494";
		TCustContract result = searchMemberInfosDao.searchContractInfoById(fid);
		TCustCustbasedata cust = searchMemberInfosDao.getCustomerInfoByContract(result.getFid().toString());
	}
*/
	@Test
	public final void testSearchContractInfoByCustomerId() {
		String customerId = "93110";
		List<TCustContractOperation> result = 
				searchMemberInfosDao.searchContractInfoByCustomerId(customerId);
		assertNotNull(result);
	}
	
	
	@Test
	public void testConvert() throws CrmBusinessException{
		WaitingSendRequest waitingSendRequest = getWaitingSendRequest("12524",0);
		
		
		WaitingSendFossRequest request = ClientTool.converToWaitingSendFossRequest(waitingSendRequest);
		System.out.println(request.gettCustCustbasedata().getFcustname());
	}
	
	
private WaitingSendRequest getWaitingSendRequest(String memberNumber, int pattern) throws CrmBusinessException {
		
		// 从数据库中检索出各表的待发送记录，并组装成WaitingSendRequest对象
		WaitingSendRequest waitingSendData = new WaitingSendRequest();
		waitingSendData.setPattern(pattern);
		switch (pattern) {
		case 0:
			// 客户基础信息表
			TCustCustbasedataOperation tcc = searchMemberInfosDao.searchMemberInfo(memberNumber);
			/*// 根据客户实体获取省市Code
			String pCode = searchMemberInfosDao.getProvinceCode(tcc.getFprovinceid().toString());
			String cCode = searchMemberInfosDao.getCityCode(tcc.getFcityid().toString());
			String pName=searchMemberInfosDao.getProvinceName(tcc.getFprovinceid().toString());
			String cName=searchMemberInfosDao.getCityName(tcc.getFcityid().toString());
			tcc.setFprovinceid(new BigDecimal(pCode));
			tcc.setFcityid(new BigDecimal(cCode));
			tcc.setFprovince(pName);
			tcc.setFcity(cName);*/
			waitingSendData.settCustCustbasedata(tcc);
			String custBaseDatafid = waitingSendData.gettCustCustbasedata().getFid().toString();
			// 客户账户表
			List<TCustAccountOperation> accountInfoList = searchMemberInfosDao.searchAccountInfoByCustomId(custBaseDatafid);
			if (accountInfoList != null && accountInfoList.size() > 0) waitingSendData.gettCustAccount().addAll(accountInfoList);
			// 客户接送货地址表
			List<TCustShuttleaddressOperation> shuttleAddressInfoList = searchMemberInfosDao.searchShuttleAddressByCustomerId(custBaseDatafid);
			if (shuttleAddressInfoList != null &&  shuttleAddressInfoList.size() > 0) waitingSendData.gettCustShuttleaddress().addAll(shuttleAddressInfoList);
			// 客户联系人表
			List<TCustCustlinkmanOperation> contactInfoList = searchMemberInfosDao.searchContactInfoByCustomerId(custBaseDatafid);
			if (contactInfoList != null && contactInfoList.size() > 0) waitingSendData.gettCustCustlinkman().addAll(contactInfoList);
			// 客户合同表
			List<TCustContractOperation> contractInfoList = searchMemberInfosDao.searchContractInfoByCustomerId(custBaseDatafid);
			if (contractInfoList != null && contractInfoList.size() > 0) waitingSendData.gettCustContract().addAll(contractInfoList);
			// 客户合同-部门关联表
			// 客户合同优惠表
			for (TCustContractOperation contract_element : waitingSendData.gettCustContract()) {
				String contractFid = contract_element.getFid().toString();
				List<TCustContractdeptOperation> contractDeptList = searchMemberInfosDao.searchContractDeptByContractId(contractFid);
				if (contractDeptList != null && contractDeptList.size() > 0) waitingSendData.gettCustContractdept().addAll(contractDeptList);
				List<TCustPreferentialOperation> preferentialInfoList = searchMemberInfosDao.searchPreferentialByContractId(contractFid);
				waitingSendData.gettCustPreferential().addAll(preferentialInfoList);
			}
			// 客户联系人接送货关联表
			List<TCustPreferenceaddressOperation> preferenceAddressInfoList = searchMemberInfosDao.searchPreferenceAddressByMemberId(custBaseDatafid);
			if (preferenceAddressInfoList != null && preferenceAddressInfoList.size() > 0) waitingSendData.gettCustPreferenceaddress().addAll(preferenceAddressInfoList);
			
			// 检索需要同步的数据对象，并序列化为JSON
			return waitingSendData;
		default:
			return null;
		}
	}
	
	
/*
	@Test
	public final void testSearchShuttleAddressById() {
		String fid = "15024";
		TCustShuttleaddress result = searchMemberInfosDao.searchShuttleAddressById(fid);
		assertEquals("甘肃省-平凉市-崆峒区-tr",		result.getFaddress());
		
		TCustCustbasedata cust = searchMemberInfosDao.getCustomerInfoByShuttleAddress(result.getFid().toString());
		assertEquals("DP01563", cust.getFdeptidStandardcode());
	}

	@Test
	public final void testSearchShuttleAddressByCustomerId() {
		String memberId = "1776";
		List<TCustShuttleaddressOperation> result = searchMemberInfosDao.searchShuttleAddressByCustomerId(memberId);
		assertEquals(1, result.size());
		assertEquals("安徽省-宿州市-埇桥区-lk个", result.get(0).getFaddress());
	}

	@Test
	public final void testSearchPreferenceAddressById() {
		String fid = "1773";
		TCustPreferenceaddress result = searchMemberInfosDao.searchPreferenceAddressById(fid);
		assertEquals(15005,result.getFshuttleaddressid().intValue());
		
		TCustCustbasedata cust = searchMemberInfosDao.getCustomerInfoByPreferenceAddress(result.getFid().toString());
		assertEquals(1776, cust.getFid().longValue());
		assertEquals("DP01563", cust.getFdeptidStandardcode());
	}
	
	@Test
	public final void testSearchPreferenceAddressByMemberId() {
		String memberId = "1776";
		List<TCustPreferenceaddressOperation> result = searchMemberInfosDao.searchPreferenceAddressByMemberId(memberId);
		assertEquals(1, result.size());
	}

	@Test
	public final void testSearchContractDeptById() {
		String fid = "505";
		TCustContractdept result = searchMemberInfosDao.searchContractDeptById(fid);
		assertEquals("赵斌", result.getFapprovalman());
		assertEquals("DP08817", result.getFdeptidStandardcode());

		TCustCustbasedata cust = searchMemberInfosDao.getCustomerInfoByContractDept(result.getFid().toString());
		assertEquals(641, cust.getFid().longValue());
		assertEquals("DP01563", cust.getFdeptidStandardcode());
	}

	@Test
	public final void testSearchContractDeptByContractId() {
		String contractId = "545";
		List<TCustContractdeptOperation> result = searchMemberInfosDao.searchContractDeptByContractId(contractId );
		assertEquals(5, result.size());
	}

	@Test
	public final void testSearchPreferentialById() {
		String fid = "452";
		TCustPreferential result = searchMemberInfosDao.searchPreferentialById(fid);
		assertEquals(0.8900, result.getFchargerebate().doubleValue());
		
		TCustCustbasedata cust = searchMemberInfosDao.getCustomerInfoByPreferential(result.getFid().toString());
		assertEquals("DP01563", cust.getFdeptidStandardcode());
		
	}

	@Test
	public final void testSearchPreferentialByContractId() {
		String contractId = "545";
		List<TCustPreferentialOperation> result = 
				searchMemberInfosDao.searchPreferentialByContractId(contractId);
		assertEquals(2, result.size());
	}
	
	@Test
	public final void testGetCustomerInfoByPreferential() {
		String preferentiaFid = "283";
		TCustCustbasedata result = searchMemberInfosDao.getCustomerInfoByPreferential(preferentiaFid);
		assertEquals(641, result.getFid().longValue());
	}
	
	@Test
	public final void testGetCustomerInfoByShuttleAddress() {
		String shuttleFid = "15005";
		TCustCustbasedata result = searchMemberInfosDao.getCustomerInfoByShuttleAddress(shuttleFid);
		assertEquals(1776, result.getFid().longValue());
	}*/
}