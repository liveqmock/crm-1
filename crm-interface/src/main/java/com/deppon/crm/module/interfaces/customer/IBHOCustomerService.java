package com.deppon.crm.module.interfaces.customer;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.deppon.crm.module.interfaces.customer.domain.ContactCustBindRequest;
import com.deppon.crm.module.interfaces.customer.domain.ContactCustBindResponse;
import com.deppon.crm.module.interfaces.customer.domain.QueryClaimInfoResponse;
import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.interfaces.customer.domain.Contact;
import com.deppon.crm.module.interfaces.customer.domain.Member;

/**
 * CRM向外提供的关于客户信息的一些相关操作服务
 * @author davidcun @2012-3-27
 */
@WebService
public interface IBHOCustomerService {

	/**
	 * 通过会员编码或者联系人Id查询客户信息
	 * <p>需求编号：BHO-7</p>
	 * @param customerCode 客户编码 ，如果已经传入联系人Id，那么此参数可以为"",或者为null
	 * @param contactId 联系人Id，如果已经传入客户编码，那么此参数可以为"",或者为null
	 * @return {@link Customer}     
	 * @Throws {@link CrmBusinessException} 业务异常信息
	 * @author davidcun 2012-3-27
	 *
	 */
	public Member queryCustomerById(String deptCode,String contactId) throws CrmBusinessException;
	
	/**
	 * @作者：罗典
	 * @描述：根据联系人ID查询联系人信息(包含会员基本信息)
	 * @时间：2012-10-11
	 * @param contactId 联系人Id
	 * @return Contact 联系人信息(包含会员基本信息)
	 * */
	public Contact queryContactByContactNum(String ContactNum) throws CrmBusinessException;
	
	/**
	 * @作者：吴根斌
	 * @描述：接收官网过来的用户联系人绑定/解绑信息
	 * @时间：2013-6-23
	 * @param contactCustBindRequest 
	 * @return ContactCustBindResponse
	 * */
	public ContactCustBindResponse bindContact(ContactCustBindRequest contactCustBindRequest)throws CrmBusinessException;
	
	/**
	 * @作者：吴根斌
	 * @描述：呼叫中心根据电话号码查询理赔信息
	 * @时间：2013-8-19
	 * @param QueryClaimInfoRequest 
	 * @return QueryClaimInfoResponse
	 * */
    public QueryClaimInfoResponse queryClaimInfos(String telephone,int limit,int start)throws CrmBusinessException;
}
