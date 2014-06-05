package com.deppon.crm.module.client.customer.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.xml.ws.Holder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.deppon.bho.customer.IUserWebServices;
import com.deppon.bho.customer.User;
import com.deppon.bi.arrived.CRMCusArrStaByNum;
import com.deppon.bi.leave.CRMCusStaByNum;
import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.common.exception.ErrorMessageCode;
import com.deppon.crm.module.client.common.util.Check;
import com.deppon.crm.module.client.common.util.ClientTool;
import com.deppon.crm.module.client.common.util.Constant;
import com.deppon.crm.module.client.common.util.NullOrEmptyValidator;
import com.deppon.crm.module.client.customer.ICustomerOperate;
import com.deppon.crm.module.client.customer.domain.AnalysisCustomerType;
import com.deppon.crm.module.client.customer.domain.CustomerCancel;
import com.deppon.crm.module.client.customer.domain.PathAnalysisInfo;
import com.deppon.crm.module.client.customer.domain.RegisterInfo;
import com.deppon.erp.custAndOrder.ERPBusinessException_Exception;
import com.deppon.erp.custAndOrder.ExceptionCust;
import com.deppon.erp.custAndOrder.ExceptionCustArray;
import com.deppon.erp.custAndOrder.IOrderAndCustService;
import com.deppon.erp.custAndOrder.StringArray;
import com.deppon.foss.crm.ClaimsPayBillGenerateRequest;
import com.deppon.foss.crm.ClaimsPayBillGenerateResponse;
import com.deppon.foss.crm.CommonException;
import com.deppon.foss.crm.CustomerService;
import com.deppon.foss.crm.ESBHeader;
import com.deppon.foss.crm.IsCustomerBlankOutList;
import com.deppon.foss.crm.IsCustomerBlankOutRequest;
import com.deppon.foss.crm.IsCustomerBlankOutResponse;

/**
 * 
 * @author davidcun @2012-5-2
 */
public class CustomerOperateImpl implements ICustomerOperate {
	private static Log log = LogFactory.getLog(CustomerOperateImpl.class);

	private IUserWebServices userWebServices;
	private CRMCusArrStaByNum crmCusArrStaByNum;
	private CRMCusStaByNum crmCusStaByNum;
	private IOrderAndCustService orderAndCustService;
	// FOSS提供给CRM服务
	private CustomerService customerService;
	
	
	/**
	 * @throws CommonException 
	 * @作者：吴根斌
	 * @时间：2013-5-9
	 * @描述：通过客户编码查询客户是否可作废
	 * @参数：客户编码列表
	 * @返回值： 客户作废信息列表
	 * */
	@Override
	public List<CustomerCancel> queryCustomerCancelOrNot(List<String> customerNumberList)
			throws CrmBusinessException{
		NullOrEmptyValidator.checkEmpty(customerNumberList, "customerNumberList");
		IsCustomerBlankOutRequest request=new IsCustomerBlankOutRequest();
		request.getCustomerCodeList().addAll(customerNumberList);
		ESBHeader esbHeader = new ESBHeader();
		esbHeader.setVersion("0.1");
		esbHeader.setRequestId(UUID.randomUUID().toString());
		esbHeader.setEsbServiceCode(Constant.CRM2ESB_ISCUSTOMERBLANKOUT);
		esbHeader.setBusinessId(customerNumberList.get(0));
		esbHeader.setSourceSystem("CRM");
		esbHeader.setExchangePattern(1);
		esbHeader.setMessageFormat("SOAP");
		Holder<ESBHeader> holder = new Holder<ESBHeader>(esbHeader);
		IsCustomerBlankOutResponse response=null;
		List<CustomerCancel> custCancel=null;
		try {
			 response=customerService.qureyIsCustomeBlankOut(request, holder);
			 List<IsCustomerBlankOutList> blankOutList=response.getResultInfoList();
			 custCancel=ClientTool.convertToCustCancel(blankOutList);
			
		} catch (CommonException e) {
			throw new CrmBusinessException("1005", e.getMessage());
		} catch (Exception e) {
			throw new CrmBusinessException("1005", e.getMessage());
		}
		return custCancel;
	}
	
	/**
	 * @作者：罗典
	 * @时间：2012-9-10
	 * @描述：根据联系人ID获取官网所有注册用户信息
	 * @参数：联系人ID
	 * @返回值： 官网注册用户信息集合
	 * */
	@Override
	public List<RegisterInfo> queryRegisterInfosByContactId(String contactId)
			throws CrmBusinessException {
		if(contactId == null || contactId.equals("")){
			throw new CrmBusinessException("1005","contactId could not be null");
		}
		List<User> userList = new ArrayList<User>();
		List<RegisterInfo> registerInfoLists = new ArrayList<RegisterInfo>();
		try{
			userList = userWebServices.getUsers(contactId);
			for(User user : userList){
				registerInfoLists.add(convetor(user));
			}
		}catch(Exception e){
			throw new CrmBusinessException("1005",e);
		}
		return registerInfoLists;
	}
	
	@Override
	public List<ExceptionCust> queryERPCustInfo(List<String> custNumList) throws CrmBusinessException {
		StringArray array = new StringArray();
		ExceptionCustArray custArray = new ExceptionCustArray();
		if(custNumList==null||custNumList.size()==0){
			return custArray.getItem();
		}
		array.getItem().addAll(custNumList);
		try {
			custArray = orderAndCustService.queryExceptionCust(array);
		} 
		catch(ERPBusinessException_Exception ex){
			throw new CrmBusinessException("1005",ex.getFaultInfo().getErrorCode());
		}
		catch (Exception e) {
			throw new CrmBusinessException("1005",e);
		}
		return custArray.getItem();
	}
	
	@Override
	public boolean bindToContact(String contactCode, String userId)
			throws CrmBusinessException {
		log.info("contactCode: "+contactCode+" userId: "+userId);
//		Check.notNullOrEmpty(userId, "CustomerOperateImpl.bindToContact method arguments userId can not be null or empty");
		try {
			String result = userWebServices
					.bandingCustCode(userId, contactCode);
			if ("success".equalsIgnoreCase(result)) {
				return true;
			}else{
				//对端系统如果不成功应该给我返回错误信息的，可是没有，所以只能返回false了
				return false;
			}
		} catch (Exception e) {
			throw new CrmBusinessException(ErrorMessageCode.EXCEPTION_SERVICE_INVOKE, e,e.getMessage());
		}
	}

	@Override
	public RegisterInfo queryRegisterUser(String username)
			throws CrmBusinessException {
		Check.notNullOrEmpty(username, String.format(
				"%s.queryRegisterUser arguments username Can not be null",
				CustomerOperateImpl.class));

		try {
			User user = userWebServices.getUser(username);

			return convetor(user);
		} catch (Exception e) {
			throw new CrmBusinessException("0020", "username: "+username+e);
		}

	}

	@Override
	public List<PathAnalysisInfo> queryPathAnalysis(String memberNumber,
			Date endDate, AnalysisCustomerType type)
			throws CrmBusinessException {
		Check.notNull(type, String.format(
				"%s.queryPathAnalysis arguments type Can not be null",
				CustomerOperateImpl.class));
		Check.notNull(endDate, String.format(
				"%s.queryPathAnalysis arguments endDate Can not be null",
				CustomerOperateImpl.class));
		Check.notNullOrEmpty(
				memberNumber,
				String.format(
						"%s.queryPathAnalysis arguments memberNumber Can not be null or empty",
						CustomerOperateImpl.class));
		List<PathAnalysisInfo> infos = new ArrayList<PathAnalysisInfo>();
		String result = null;
		if (type == AnalysisCustomerType.CUSTOMER_LEAVE) {
			result = crmCusStaByNum.newOperation(memberNumber, endDate);
		} else if (type == AnalysisCustomerType.CUSTOMER_ARRIVED) {
			result = crmCusArrStaByNum.newOperation(memberNumber, endDate);
		}
		if (log.isDebugEnabled()) {
			log.debug(String.format("customer analysis result is {%s}", result));
		}
		if (null == result || "".equals(result)) {
			return infos;
		}

		return parseToObj(result, memberNumber);
	}
	

	public IUserWebServices getUserWebServices() {
		return userWebServices;
	}

	public void setUserWebServices(IUserWebServices userWebServices) {
		this.userWebServices = userWebServices;
	}

	private List<PathAnalysisInfo> parseToObj(String source, String memberNumber) {
		List<PathAnalysisInfo> infos = new ArrayList<PathAnalysisInfo>();
		String[] cs = source.split(";");
		for (String string : cs) {
			String[] des = string.split(",");
			if (log.isDebugEnabled()) {
				log.debug("current des size is "+des.length);
				log.debug("result is {"+string+"}");
			}
			PathAnalysisInfo info = new PathAnalysisInfo();
			info.setMemberNumber(memberNumber);
			info.setCity(des[0]);
			info.setLastMonthAmount(Double.valueOf(des[1]));
			info.setLastMonthSameTimeAmount(Double.valueOf(des[2]));
			info.setLastMonthvolume(Double.valueOf(Double.valueOf(des[3])));
			info.setLastMonthVotes(Integer.valueOf(des[4]));
			info.setLastMonthTruckVotes(Integer.valueOf(des[5]));
			info.setLastMonthCityVotes(Integer.valueOf(des[6]));
			info.setLastMonthlongTruckVotes(Integer.valueOf(des[7]));
			info.setLastMonthshortTruckVotes(Integer.valueOf(des[8]));
			info.setLastAirTransVotes(Integer.valueOf(des[9]));
			info.setCurrentMonthSameTimeAmount(Double.valueOf(des[10]));
			info.setCurrentMonthvolume(Double.valueOf(des[11]));
			info.setCurrentMonthvotes(Integer.valueOf(des[12]));
			info.setCurrentMonthTruckVotes(Integer.valueOf(des[13]));
			info.setCurrentMonthCityVotes(Integer.valueOf(des[14]));
			info.setCurrentMonthlongTruckVotes(Integer.valueOf(des[15]));
			info.setCurrentMonthshortTruckVotes(Integer.valueOf(des[16]));
			infos.add(info);
		}

		return infos;

	}

	private RegisterInfo convetor(User user) {
		RegisterInfo info = new RegisterInfo();
		if (user == null) {
			return null;
		}
		info.setAddress(user.getAddress());
		info.setArea(user.getArea());
		info.setCity(user.getCity());
		info.setCreateDate(user.getCreateDate());
		info.setCreateUser(user.getCreateUser());
		info.setCusCode(user.getCusCode());
		info.setDeptId(user.getDeptId());
		info.setEmail(user.getEmail());
		info.setFixedPhone(user.getFixedPhone());
		info.setGender(user.getGender());
		info.setId(user.getId());
		info.setLastLoginTime(user.getLastLoginTime());
		info.setLastUpdateTime(user.getLastUpdateTime());
		info.setModifyDate(user.getModifyDate());
		info.setModifyUser(user.getModifyUser());
		info.setNewPwd(user.getNewPwd());
		info.setPassword(user.getPassword());
		info.setProcity(user.getProcity());
		info.setProvince(user.getProvince());
		info.setRealName(user.getRealName());
		info.setRefundPaymentOrder(user.getRefundPaymentOrder());
		info.setRegiterTime(user.getRegiterTime());
		info.setStatus(user.getStatus());
		info.setTelephone(user.getTelephone());
		info.setTransportingOrder(user.getTransportingOrder());
		info.setUserName(user.getUserName());
		info.setCustsource(ClientTool.convertUserSource(user.getCustsource()));
		return info;
	}

	public CRMCusArrStaByNum getCrmCusArrStaByNum() {
		return crmCusArrStaByNum;
	}

	public void setCrmCusArrStaByNum(CRMCusArrStaByNum crmCusArrStaByNum) {
		this.crmCusArrStaByNum = crmCusArrStaByNum;
	}

	public CRMCusStaByNum getCrmCusStaByNum() {
		return crmCusStaByNum;
	}

	public void setCrmCusStaByNum(CRMCusStaByNum crmCusStaByNum) {
		this.crmCusStaByNum = crmCusStaByNum;
	}

	public IOrderAndCustService getOrderAndCustService() {
		return orderAndCustService;
	}

	public void setOrderAndCustService(IOrderAndCustService orderAndCustService) {
		this.orderAndCustService = orderAndCustService;
	}

	public CustomerService getCustomerService() {
		return customerService;
	}
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	

}
