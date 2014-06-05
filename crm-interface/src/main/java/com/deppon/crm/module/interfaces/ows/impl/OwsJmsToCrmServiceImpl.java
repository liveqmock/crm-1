/**
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title OwsJmsToCrmServiceImpl.java
 * @package com.deppon.crm.module.interfaces.ows.impl
 * @author 105681
 * @version 0.1 2014-4-15
 */
package com.deppon.crm.module.interfaces.ows.impl;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.authorization.shared.exception.UserException;
import com.deppon.crm.module.client.customer.domain.RegisterInfo;
import com.deppon.crm.module.common.server.service.IAreaAddressService;
import com.deppon.crm.module.common.server.service.ICityService;
import com.deppon.crm.module.common.server.service.IProvinceService;
import com.deppon.crm.module.common.shared.domain.Area;
import com.deppon.crm.module.common.shared.domain.City;
import com.deppon.crm.module.common.shared.domain.Province;
import com.deppon.crm.module.customer.server.manager.IContactManager;
import com.deppon.crm.module.customer.server.manager.IMemberManager;
import com.deppon.crm.module.customer.shared.domain.ChannelCustomer;
import com.deppon.crm.module.customer.shared.exception.CustomerException;
import com.deppon.crm.module.customer.shared.exception.MemberException;
import com.deppon.crm.module.customer.shared.exception.WorkFlowException;
import com.deppon.crm.module.interfaces.cc.domain.CommException;
import com.deppon.crm.module.interfaces.common.util.Constant;
import com.deppon.crm.module.interfaces.common.util.IntefacesTool;
import com.deppon.crm.module.interfaces.ows.IOwsJmsToCrmService;
import com.deppon.crm.module.interfaces.ows.domain.CommonExceptionInfo_Exception;
import com.deppon.crm.module.interfaces.ows.jmsdomain.CreateOwsCustomerRequest;
import com.deppon.crm.module.interfaces.ows.jmsdomain.CreateOwsCustomerResponse;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.entity.IUser;
import com.deppon.foss.framework.server.context.UserContext;

/**
 * <p>
 * Description:<br />
 * </p>
 * @title OwsJmsToCrmServiceImpl.java
 * @package com.deppon.crm.module.interfaces.ows.impl
 * @author 105681
 * @version 0.1 2014-4-15
 */

public class OwsJmsToCrmServiceImpl implements IOwsJmsToCrmService{

	// Description:固定客户的manager
	private IMemberManager memberManager;
	// 行政城市服务
	private ICityService cityService;
	// 行政省份服务
	private IProvinceService provinceService;
	// 行政区域服务
	private IAreaAddressService areaAddressService;
	//用户联系人绑定解绑模块
	IContactManager contactManager;
	/**
	 * <p>
	 * Description:官网创建客户<br />
	 * </p>
	 * @author 105681
	 * @version 0.1 2014-4-15
	 * @param request
	 * @return
	 * @throws CommonExceptionInfo_Exception
	 */
	@Override
	public CreateOwsCustomerResponse createOwsCustomer(
			CreateOwsCustomerRequest request)
			throws CommonExceptionInfo_Exception,RuntimeException{
		CreateOwsCustomerResponse response=new CreateOwsCustomerResponse();
		Province province = null;
		//获取组织省份消息
		if (request.getProvinceCode() != null
				&& !"".equals(request.getProvinceCode())) {
			province = provinceService.queryVaildProvinceByNum(request
					.getProvinceCode());
			request.setProvinceCode(province.getId());
		}
		// 获取组织城市消息
		City city = null;
		if (request.getCityCode() != null && !"".equals(request.getCityCode())) {
			city = cityService.queryVaildCityByNum(request.getCityCode());
			request.setCityCode(city.getId());
		}
		// 获取组织区县消息
		Area area = null;
		if (request.getAreaCode() != null && !"".equals(request.getAreaCode())) {
			area = areaAddressService
					.queryVaildAreaByNum(request.getAreaCode());
			request.setAreaCode(area.getFid().toString());
		}
		ChannelCustomer customer=IntefacesTool.convertToChannelCustomer(request);
		try {
			IntefacesTool.validateccNull(request, Constant.PARAMS_LOSE);
			IntefacesTool.validateccNull(request.getUserName(),"userName is null");
			customer.setChannelSource(com.deppon.crm.module.customer.shared.domain.Constant.CUST_SOURCE_OWS);
			String userName=request.getUserName();
			RegisterInfo registerInfo = new RegisterInfo();
			registerInfo.setUserName(userName);
			registerInfo.setCustsource("ONLINE");
			List<RegisterInfo> infos = contactManager
					.queryRegisterInfo(registerInfo);
			String operateType="I";
			if(null!=infos){
				for(RegisterInfo register:infos){
					if((null!=register.getCusCode())&&!("".equals(register.getCusCode()))){
						operateType="U";
						break;
					}
				}
			}
			if("I".equals(operateType)){
				Map<String,String> result=memberManager.createChannelCutomer(customer);
				String user=result.get("userName");
				String contactId=result.get("contactId");
				response.setIfSuccess(true);
				response.setLinkmanId(contactId);
				response.setUserName(user);
			}else if("U".equals(operateType)){
				User user=new User();
				Employee e=new Employee();
				e.setId("86301");
				e.setEmpName("系统管理员");
				e.setEmpCode("000000");
				Department dept = new Department();
				dept.setId("104");
				dept.setDeptName("总裁");
				dept.setStandardCode("DP00003");
				e.setDeptId(dept);
				user.setEmpCode(e);
				UserContext.setCurrentUser(user);
				String error=memberManager.updateChannelCustomer(customer);
				if(null==error){
					response.setUserName(request.getUserName());
					response.setLinkmanId(infos.get(0).getCusCode());
					response.setIfSuccess(true);
				}else{
					response.setUserName(request.getUserName());
					response.setLinkmanId(error);
					response.setIfSuccess(false);
				}
			}
		}
		catch(CustomerException e){
			response.setUserName(request.getUserName());
			String errorMsg = IntefacesTool.getMessage("customer",
					e.getErrorCode());
			response.setIfSuccess(false);
			response.setLinkmanId(errorMsg);
			e.printStackTrace();
		}
		catch(MemberException e){
			response.setUserName(request.getUserName());
			String errorMsg = IntefacesTool.getMessage("customer",
					e.getErrorCode());
			response.setIfSuccess(false);
			response.setLinkmanId(errorMsg);
			e.printStackTrace();
		}
		catch(UserException e){
			response.setUserName(request.getUserName());
			String errorMsg = IntefacesTool.getMessage("customer",
					e.getErrorCode());
			response.setIfSuccess(false);
			response.setLinkmanId(errorMsg);
			e.printStackTrace();
		}
		catch(WorkFlowException e){
			response.setUserName(request.getUserName());
			String errorMsg = IntefacesTool.getMessage("customer",
					e.getErrorCode());
			response.setIfSuccess(false);
			response.setLinkmanId(errorMsg);
			e.printStackTrace();
		}
		catch(IllegalArgumentException e){
			response.setUserName(request.getUserName());
			response.setIfSuccess(false);
			response.setLinkmanId(e.getMessage());
			e.printStackTrace();
		}
		catch(CommException e){
			response.setUserName(request.getUserName());
			response.setIfSuccess(false);
			response.setLinkmanId(e.getMessage());
			e.printStackTrace();
		}
		catch (Exception e) {
			response.setUserName(request.getUserName());
			response.setIfSuccess(false);
			response.setLinkmanId(e.getMessage());
			e.printStackTrace();
		}
		return response;
	}


	public IMemberManager getMemberManager() {
		return memberManager;
	}
	public void setMemberManager(IMemberManager memberManager) {
		this.memberManager = memberManager;
	}
	public ICityService getCityService() {
		return cityService;
	}
	public void setCityService(ICityService cityService) {
		this.cityService = cityService;
	}
	public IProvinceService getProvinceService() {
		return provinceService;
	}
	public void setProvinceService(IProvinceService provinceService) {
		this.provinceService = provinceService;
	}
	public IAreaAddressService getAreaAddressService() {
		return areaAddressService;
	}
	public void setAreaAddressService(IAreaAddressService areaAddressService) {
		this.areaAddressService = areaAddressService;
	}


	/**
	 * @return contactManager : return the property contactManager.
	 */
	public IContactManager getContactManager() {
		return contactManager;
	}


	/**
	 * @param contactManager : set the property contactManager.
	 */
	public void setContactManager(IContactManager contactManager) {
		this.contactManager = contactManager;
	}

}
