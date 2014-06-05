package com.deppon.crm.module.interfaces.customer.impl;

import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.common.util.XmlJaxbMapper;
import com.deppon.crm.module.client.customer.domain.RegisterInfo;
import com.deppon.crm.module.client.sync.domain.OrderRightRequest;
import com.deppon.crm.module.customer.server.manager.IAlterMemberManager;
import com.deppon.crm.module.customer.server.manager.IContactManager;
import com.deppon.crm.module.interfaces.customer.domain.Contact;
import com.deppon.crm.module.interfaces.customer.domain.Member;
import com.deppon.crm.module.interfaces.common.util.IntefacesTool;
import com.deppon.crm.module.interfaces.customer.IBHOCustomerService;
import com.deppon.crm.module.interfaces.customer.domain.ClaimInfo;
import com.deppon.crm.module.interfaces.customer.domain.ContactCustBindRequest;
import com.deppon.crm.module.interfaces.customer.domain.ContactCustBindResponse;
import com.deppon.crm.module.interfaces.customer.domain.QueryClaimInfoResponse;
import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.recompense.server.manager.RecompenseManager;
import com.deppon.crm.module.recompense.shared.domain.RecompenseForCC;

@WebService(endpointInterface="com.deppon.crm.module.interfaces.customer.IBHOCustomerService")
public class CustomerServiceImpl implements IBHOCustomerService {
	
	// 会员manager
	IAlterMemberManager alterMemberManager;
	// 部门服务
	IDepartmentService departmentService;
	//绑定/解绑
	IContactManager contactManager;
	//根据电话号码查询理赔信息
	RecompenseManager recompenseManager;

	/**
	 * @作者：罗典
	 * @描述：根据联系人ID查询联系人信息(包含会员基本信息)
	 * @时间：2012-10-11
	 * @param contactId 联系人Id
	 * @return Contact 联系人信息(包含会员基本信息)
	 * */
	@Override
	public Contact queryContactByContactNum(String ContactNum) throws CrmBusinessException {
		IntefacesTool.validateStringNull(ContactNum, "0024");
		com.deppon.crm.module.customer.shared.domain.Contact contact = alterMemberManager
				.getContactByNum(ContactNum);
		Contact icontact=new Contact();
		BeanUtils.copyProperties(contact, icontact);
		return icontact;
	}
	
	/**
	 * @作者：吴根斌
	 * @描述：接收官网过来的用户联系人绑定/解绑信息
	 * @时间：2013-6-23
	 * @param contactCustBindRequest 
	 * @return ContactCustBindResponse
	 * */
	@Override
	public ContactCustBindResponse bindContact(
			ContactCustBindRequest contactCustBindRequest)throws CrmBusinessException {
		ContactCustBindResponse response = new ContactCustBindResponse();
		
		try {
			IntefacesTool.validateStringNull(
					contactCustBindRequest.getUserName(), "1003", "userName");
			IntefacesTool.validateStringNull(
					contactCustBindRequest.getCusCode(), "1003", "cusCode");
			IntefacesTool.validateStringNull(
					contactCustBindRequest.getOperateType(), "1003",
					"operateType");
			RegisterInfo info = IntefacesTool
					.changeToRegisterInfo(contactCustBindRequest);
			contactManager.boundContactForOnline(info,
					contactCustBindRequest.getOperateType());
			response.setIsSuccess(true);
		} catch (CrmBusinessException e) {
			response.setIsSuccess(false);
			response.setFailReason(e.getMessage());
		}
		return response;
	}
	/**
	 * @作者：吴根斌
	 * @描述：CC查询根据手机号码查询理赔信息
	 * @时间：2013-8-19
	 * */
	@Override
	public QueryClaimInfoResponse queryClaimInfos(String telephone,int limit,int start) throws CrmBusinessException {
		IntefacesTool.validateStringNull(telephone, "1003", "telephone");
		Map<String, Object> result = recompenseManager
				.searchRecompenseHistoryList(telephone, limit, start);  
		int totalCount = (Integer) result.get("count");
		List<RecompenseForCC> claims = (List<RecompenseForCC>) result
				.get("list");
		List<ClaimInfo> claimInfos = IntefacesTool
				.changeClaimsToClaimInfos(claims);
		QueryClaimInfoResponse response = new QueryClaimInfoResponse();
		response.setTotalCount(totalCount);
		response.getClamInfos().addAll(claimInfos);
		return response;
	}
	/**
	 * @作者: 罗典
	 * @时间：2012-4-24
	 * @描述：根据联系人编码查询会员信息， 包含联系人信息及是否月结合同信息(存入是否允许联系人兑换积分字段中返回
	 **/
	@Override
	public Member queryCustomerById(String deptCode,String contactId)
			throws CrmBusinessException {
		// 联系人Id为空----return null;
		if (StringUtils.isEmpty(contactId)) {
			return null;
		}
		// 部门ID
		String deptId = null;
		// 根据部门编码查询部门Id
		if (deptCode != null && !deptCode.equals("")) {
			Department dept = departmentService.getDeptByStandardCode(deptCode);
			if(dept != null){
				deptId = dept.getId();
			}else{
				throw new CrmBusinessException("0025"," deptCode: " + deptCode);
			}
		}
		com.deppon.crm.module.customer.shared.domain.Member member = alterMemberManager.getMemberBylinkmanId(contactId, deptId);
		Member imember=IntefacesTool.convertToIMember(member);
		return imember;
	}

	public IAlterMemberManager getAlterMemberManager() {
		return alterMemberManager;
	}

	public void setAlterMemberManager(IAlterMemberManager alterMemberManager) {
		this.alterMemberManager = alterMemberManager;
	}

	public IDepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public IContactManager getContactManager() {
		return contactManager;
	}

	public void setContactManager(IContactManager contactManager) {
		this.contactManager = contactManager;
	}

	public RecompenseManager getRecompenseManager() {
		return recompenseManager;
	}

	public void setRecompenseManager(RecompenseManager recompenseManager) {
		this.recompenseManager = recompenseManager;
	}

	
	
}
