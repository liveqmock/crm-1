package com.deppon.crm.module.client.customer;

import java.util.Date;
import java.util.List;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.customer.domain.AnalysisCustomerType;
import com.deppon.crm.module.client.customer.domain.CustomerCancel;
import com.deppon.crm.module.client.customer.domain.PathAnalysisInfo;
import com.deppon.crm.module.client.customer.domain.RegisterInfo;
import com.deppon.erp.custAndOrder.ExceptionCust;
import com.deppon.foss.crm.ClaimsPayBillGenerateRequest;

/**
 * 接口组提供的与外部交互的客户相关的操作接口
 * @author davidcun @2012-3-27
 */
public interface ICustomerOperate {
	
	/**
	 * @作者：吴根斌
	 * @时间：2013-5-9
	 * @描述：通过客户编码查询客户是否可作废
	 * @参数：客户编码列表
	 * @返回值： 客户作废信息列表
	 * */
	public List<CustomerCancel> queryCustomerCancelOrNot(List<String> customerNumberList) throws CrmBusinessException;
	/**
	 * @作者：罗典
	 * @时间：2012-9-10
	 * @描述：根据联系人ID获取官网所有注册用户信息
	 * @参数：联系人ID
	 * @返回值： 官网注册用户信息集合
	 * */
	public List<RegisterInfo> queryRegisterInfosByContactId(String contactId) throws CrmBusinessException;
	
	/**
	 * @作者：罗典
	 * @时间：2012-8-30
	 * @描述：根据会员编号集合获取ERP会员基础信息
	 * @参数：会员编码集合
	 * @返回值：会员需校验的基础信息
	 * */
	
	public List<ExceptionCust> queryERPCustInfo(List<String> custNumList) throws CrmBusinessException;
	
	/**
	 * <p>需求编号：BHO-6</p>
	 * 此接口功能为CRM系统绑定联系人编码功能点提供，将会与网厅进行交互
	 * 在调用此接口之前已经对业务逻辑进行了校验， 包括手机验证码的校验在确定可以进行
	 * 绑定动作之后才可以调用此接口，由接口参数决定，此接口功能不做业务逻辑判断
	 * @param contactCode 联系人编码
	 * @param userId 注册用户ID
	 * @return 
	 * @Throws 
	 * @author davidcun 2012-3-27
	 *
	 */
	public boolean bindToContact(String contactCode,String userId) throws CrmBusinessException;
	
	/**
	 * <p>需求编号：BHO-5</p>
	 * 查询网厅注册用户信息
	 * @param username 网厅注册用名      
	 * @return {@link RegisterInfo} 注册用户信息
	 * @Throws 
	 * @author davidcun 2012-4-7
	 *
	 */
	public RegisterInfo queryRegisterUser(String username) throws CrmBusinessException;
	
	/**
	 * <p>需求编号：BI-1</p>
	 * 通过会员编码到报表中心查询客户线路分信息
	 * @param       
	 * @return      
	 * @Throws 
	 * @author davidcun 2012-4-13
	 *
	 */
	public List<PathAnalysisInfo> queryPathAnalysis(String memberNumber, Date endDate,
			AnalysisCustomerType type) throws CrmBusinessException;
}
