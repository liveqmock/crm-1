package com.deppon.crm.module.interfaces.stub;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.interfaces.customer.IBHOCustomerService;
import com.deppon.crm.module.interfaces.customer.domain.Contact;
import com.deppon.crm.module.interfaces.customer.domain.ContactCustBindRequest;
import com.deppon.crm.module.interfaces.customer.domain.ContactCustBindResponse;
import com.deppon.crm.module.interfaces.customer.domain.Member;
import com.deppon.crm.module.interfaces.customer.domain.QueryClaimInfoResponse;
/**
 * @作者：罗典
 * @时间：2012-4-13
 * @描述：客户测试实现类
 * */
@WebService(portName="CustomerServiceImplPort",serviceName="CustomerServiceImplService",endpointInterface="com.deppon.crm.module.interfaces.custormer.ICustomerService")
public class CustomerServiceTestImpl implements IBHOCustomerService {

	@Override
	public Member queryCustomerById(String memberCode, String contactCode)
			throws CrmBusinessException {
		Member member = new Member();
		member.setCustNumber("1");
		member.setDegree("1");
		member.setCustName("1");
		member.setTradeId("1");
		member.setCustType("1");
		member.setCustNumber("1");
		member.setDegree("1");
		member.setCustName("1");
		member.setTradeId("1");
		member.setCustType("1");
		member.setTaxregNumber("1");
		member.setCompanyProperty("1");
		member.setCustNature("1");
		member.setProvince("1");
		member.setProvinceId("1");
		member.setCity("1");
		member.setCityId("1");
		member.setRegistAddress("1");
		member.setIsSpecial(true);
		member.setIsRedeempoints(true);
		member.setIsImportCustor(true);
		member.setCustPotentialType("1");
		member.setIsAcceptMarket(true);
		member.setBrandWorth("1");
		member.setChannelSource("1");
		member.setChannel("1");
		member.setPreferenceService("1");
		member.setCompanyScop("1");
		member.setLastYearProfit(Double.valueOf("1"));
		member.setLastYearIncome(Double.valueOf("1"));
		member.setIsFocusPay(true);
		member.setFocusDeptId("1");
		member.setBillTitle("1");
		member.setIsParentCompany(true);
		member.setParentNumber("1");
		member.setParentCompanyId("1");
		member.setRegisterFund(Double.valueOf("1"));
		member.setProcRedit(Double.valueOf("1"));
		member.setRecommendCust("1");
		member.setRemark("1");
		member.setJshAddress("1");
		member.setSimpleName("1");
		member.setBussType("1");
		member.setIsTranGoods(true);
		member.setAreaId("1");
		member.setDeptId("1");
		member.setCustId("1");
		member.setContactId("1");
		member.setUpgradeSource("1");
		member.setCustStatus("1");
		member.setBecomeMemTime(new Date());
		member.setResponsibillity("1");
		member.setVersionNumber(Integer.valueOf("1"));
		member.setNextLevel("1");
		member.setLastChangTime(new Date());
		member.setSurplusMonthlyStatementMoney(Double.valueOf("1"));
		Contact contact = new Contact();
		contact.setId("1");
		contact.setCreateDate(new Date());
		contact.setCreateUser("1");
		contact.setModifyDate(new Date());
		contact.setModifyUser("1");
		contact.setNumber("1");
		contact.setName("1");
		contact.setIdCard("1");
		contact.setLinkmanType("1");
		contact.setIsMainLinkMan(true);
		contact.setSex("1");
		contact.setTelPhone("1");
		contact.setMobilePhone("1");
		contact.setDuty("1");
		contact.setDutyDept("1");
		contact.setBornDate(new Date());
		contact.setGainave("1");
		contact.setDecisionRight("1");
		contact.setNativePlace("1");
		contact.setPersonLove("1");
		contact.setFolk("1");
		contact.setEmail("1");
		contact.setQqNumber("1");
		contact.setMsn("1");
		contact.setWw("1");
		contact.setAlid("1");
		contact.setOnlineBusinessId("1");
		contact.setTaobId("1");
		contact.setYoushangId("1");
		contact.setAccountId("1");
		contact.setAcceptDeptid("1");
		contact.setStatus("1");
		contact.setDefaultId("1");
//		contact.setVersionId("1");
		contact.setCustId("1");
		List<Contact> contactList = new ArrayList<Contact>();
		member.setContactList(contactList);
		return member;
	}

	@Override
	public Contact queryContactByContactNum(String ContactNum)
			throws CrmBusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContactCustBindResponse bindContact(
			ContactCustBindRequest contactCustBindRequest)
			throws CrmBusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryClaimInfoResponse queryClaimInfos(String telephone, int pageNo,
			int pageItems) throws CrmBusinessException {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public QueryClaimInfoResponse queryClaimInfos() throws CrmBusinessException {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
