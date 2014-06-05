package com.deppon.crm.module.interfaces.cc.impl;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.xml.ws.Holder;

import com.deppon.crm.module.authorization.server.service.IUserService;
import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.common.util.BeanUtils;
import com.deppon.crm.module.customer.server.manager.IMemberManager;
import com.deppon.crm.module.customer.shared.domain.ChannelCustomer;
import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.customer.shared.exception.CustomerException;
import com.deppon.crm.module.customer.shared.exception.MemberException;
import com.deppon.crm.module.interfaces.cc.ICallcenterToCrmService;
import com.deppon.crm.module.interfaces.cc.domain.BusinessOpportunityIfexistRequest;
import com.deppon.crm.module.interfaces.cc.domain.BusinessOpportunityIfexistResponse;
import com.deppon.crm.module.interfaces.cc.domain.CommException;
import com.deppon.crm.module.interfaces.cc.domain.CreateCallCenterCustomerRequest;
import com.deppon.crm.module.interfaces.cc.domain.CreateCallCenterCustomerResponse;
import com.deppon.crm.module.interfaces.cc.domain.ESBHeader;
import com.deppon.crm.module.interfaces.cc.domain.MarketingInfo;
import com.deppon.crm.module.interfaces.cc.domain.MarketingInfoAddRequest;
import com.deppon.crm.module.interfaces.cc.domain.MarketingInfoAddResponse;
import com.deppon.crm.module.interfaces.cc.domain.MarketingInfoQueryDetailRequest;
import com.deppon.crm.module.interfaces.cc.domain.MarketingInfoQueryDetailResponse;
import com.deppon.crm.module.interfaces.cc.domain.MarketingInfoQueryRequest;
import com.deppon.crm.module.interfaces.cc.domain.MarketingInfoQueryResponse;
import com.deppon.crm.module.interfaces.cc.domain.RecallPlanRequest;
import com.deppon.crm.module.interfaces.cc.domain.RecallPlanResponse;
import com.deppon.crm.module.interfaces.cc.domain.UpdateCallCenterCustomerRequest;
import com.deppon.crm.module.interfaces.cc.domain.UpdateCallCenterCustomerResponse;
import com.deppon.crm.module.interfaces.common.util.Constant;
import com.deppon.crm.module.interfaces.common.util.IntefacesTool;
import com.deppon.crm.module.marketing.server.manager.IBusinessOpportunityManager;
import com.deppon.crm.module.marketing.server.manager.ICustomerCallManager;
import com.deppon.crm.module.marketing.shared.domain.CCPushMarketingInfo;
import com.deppon.crm.module.marketing.shared.domain.CCPushPlanInfo;
import com.deppon.crm.module.marketing.shared.domain.CCQueryMarketingInfo;
import com.deppon.crm.module.marketing.shared.domain.CCQueryMarketingInfoDetail;
import com.deppon.crm.module.organization.server.service.IDepartmentService;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.foss.framework.server.context.UserContext;

public class CallcenterToCrmServiceImpl implements ICallcenterToCrmService {

	private ICustomerCallManager customerCallManager;
	// Description:固定客户的manager
	private IMemberManager memberManager;
	//部门管理service
	private IDepartmentService departmentService;
    //用户service
	private IUserService userService;
    //商机Manager
	private IBusinessOpportunityManager businessOpportunityManager;
	public IMemberManager getMemberManager() {
		return memberManager;
	}

	public void setMemberManager(IMemberManager memberManager) {
		this.memberManager = memberManager;
	}

	public IDepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public ICustomerCallManager getCustomerCallManager() {
		return customerCallManager;
	}

	public void setCustomerCallManager(ICustomerCallManager customerCallManager) {
		this.customerCallManager = customerCallManager;
	}

	public IBusinessOpportunityManager getBusinessOpportunityManager() {
		return businessOpportunityManager;
	}

	public void setBusinessOpportunityManager(
			IBusinessOpportunityManager businessOpportunityManager) {
		this.businessOpportunityManager = businessOpportunityManager;
	}


	/**
	 *
	 * <p>
	 * Description:查询营销信息接口<br />
	 * </p>
	 * @author 105681
	 * @version 0.1 2014-4-9
	 * MarketingInfoQueryResponse
	 * 20091001-120743 400391165  400367462
	 */
	@Override
	public MarketingInfoQueryResponse queryMarketingInfo(
			Holder<ESBHeader> esbHeader, MarketingInfoQueryRequest request)
			throws CommException {
		ESBHeader header = esbHeader.value;
		header.setResponseId(UUID.randomUUID().toString());
		header.setResultCode(1);
		header.setEsbServiceCode(com.deppon.crm.module.client.common.util.Constant.CUSTMARKET_QUERY);
		MarketingInfoQueryResponse response = new MarketingInfoQueryResponse();
		try {
			IntefacesTool.validateccNull(request, Constant.PARAMS_LOSE);
			IntefacesTool.validateccNull(request.getCustNumber(),
					Constant.PARAMS_LOSE);
			CCQueryMarketingInfo ccInfo = customerCallManager
					.queryMarketingInfoToCC(request.getCustNumber(),
							request.getStart() * request.getLimit(),
							request.getLimit());
			CCQueryMarketingInfo ccInfo1 = IntefacesTool
					.convertEnumTodesc(ccInfo);
			List<MarketingInfo> marketingInfos = (List<MarketingInfo>) BeanUtils
					.copyProperties(ccInfo1.getRecords(), MarketingInfo.class);
			response.setCount(ccInfo1.getCount());
			response.getMarketInfos().addAll(marketingInfos);
		}
		catch(GeneralException e){
			e.printStackTrace();
			header.setResultCode(0);
			String errorMsg = IntefacesTool.getMessage("marketing",
					e.getErrorCode());
			throw new CommException(errorMsg,e.getCause());
		}
		catch (CrmBusinessException e) {
			e.printStackTrace();
			header.setResultCode(0);
			String errorMsg = IntefacesTool.getMessage("marketing",
    				e.getErrorCode());
			throw new CommException(errorMsg, e.getCause());
		}catch (CommException e) {
			e.printStackTrace();
			header.setResultCode(0);
			throw new CommException(e.getMessage(), e.getCause());
		}catch (Exception e) {
			e.printStackTrace();
			header.setResultCode(0);
			throw new CommException(e.getMessage(), e.getCause());
		}
		return response;
	}


	/**
	 *
	 * <p>
	 * Description:营销信息创建接口<br />
	 * </p>
	 * @author 105681
	 * @version 0.1 2014-4-9
	 * MarketingInfoAddResponse
	 */
	@Override
	public MarketingInfoAddResponse sendMarketingInfo(
			Holder<ESBHeader> esbHeader, MarketingInfoAddRequest request)
			throws CommException {
		ESBHeader header = esbHeader.value;
		header.setResponseId(UUID.randomUUID().toString());
		header.setResultCode(1);
		header.setEsbServiceCode(com.deppon.crm.module.client.common.util.Constant.CUSTMARKET_TO_CRM);
		MarketingInfoAddResponse response=new MarketingInfoAddResponse();
		boolean result=false;
        try{
        	IntefacesTool.validateccNull(request,Constant.PARAMS_LOSE);
    		IntefacesTool.validateccNull(request.getCustNumber(),Constant.PARAMS_LOSE);
    		IntefacesTool.validateccNull(request.getMarketPerson(),Constant.PARAMS_LOSE);
    		IntefacesTool.validateccNull(request.getMarketDept(),Constant.PARAMS_LOSE);
        	Member member = memberManager.getMemberByCustNumber(request.getCustNumber());
        	IntefacesTool.validateccNull(member,"custNumer not exist");
    		Department dept = departmentService.getDeptByStandardCode(request.getMarketDept());
    		IntefacesTool.validateccNull(dept,"marketDept not exist");
    		User user = userService.findByLoginName(request.getMarketPerson());
    		IntefacesTool.validateccNull(user,"marketPerson not exist");
    		CCPushMarketingInfo ccPushMarketingInfo=new CCPushMarketingInfo();
    		org.springframework.beans.BeanUtils.copyProperties(request, ccPushMarketingInfo);
    		result=customerCallManager.addCCMarketingInfo(ccPushMarketingInfo);
    		response.setIfSuccess(result);
        }catch(GeneralException e){
        	header.setResultCode(0);
        	response.setIfSuccess(result);
        	String errorMsg = IntefacesTool.getMessage("marketing",
    				e.getErrorCode());
        	response.setErrorMsg(errorMsg);
        }catch(CommException e){
        	header.setResultCode(0);
            response.setIfSuccess(result);
            response.setErrorMsg(e.getMessage());
        }catch(Exception e){
        	header.setResultCode(0);
            response.setIfSuccess(result);
            response.setErrorMsg(e.getMessage());
        }
		return response;
	}
	/**
	 *
	 * <p>
	 * Description:CC客户信息修改接口<br />
	 * </p>
	 * @author 105681
	 * @version 0.1 2014-4-10
	 * MarketingInfoAddResponse
	 */
	@Override
	public UpdateCallCenterCustomerResponse updateCustomerInfo(
			Holder<ESBHeader> esbHeader, UpdateCallCenterCustomerRequest request)
			throws CommException {
		ESBHeader header = esbHeader.value;
		header.setResponseId(UUID.randomUUID().toString());
		header.setResultCode(1);
		header.setEsbServiceCode(com.deppon.crm.module.client.common.util.Constant.CUSTINFOUPDATE_TO_CRM);
		UpdateCallCenterCustomerResponse response=new UpdateCallCenterCustomerResponse();
		try{
			IntefacesTool.validateccNull(request,Constant.PARAMS_LOSE);
			IntefacesTool.validateccNull(request.getCustNumber(),Constant.PARAMS_LOSE);
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
			ChannelCustomer channelCustomer=IntefacesTool.convertToChannelCustomerForUpdate(request);
			channelCustomer.setChannelSource("CALLCENTER");
			String errorMsg=memberManager.updateChannelCustomer(channelCustomer);
			if(null==errorMsg){
				response.setIfSuccess("Y");
			}else{
				response.setIfSuccess("N");
	            response.setErrorMsg(errorMsg);
			}
		}
		catch(CustomerException e){
			header.setResultCode(0);
            response.setIfSuccess("N");
            String errorMsg = IntefacesTool.getMessage("customer",
    				e.getErrorCode());
            response.setErrorMsg(errorMsg);
            e.printStackTrace();
		}catch(MemberException e){
			header.setResultCode(0);
            response.setIfSuccess("N");
            String errorMsg = IntefacesTool.getMessage("customer",
    				e.getErrorCode());
            response.setErrorMsg(errorMsg);
            e.printStackTrace();
		}catch(CommException e){
			header.setResultCode(0);
            response.setIfSuccess("N");
            response.setErrorMsg(e.getMessage());
            e.printStackTrace();
		}catch(Exception e){
			header.setResultCode(0);
            response.setIfSuccess("N");
            response.setErrorMsg(e.getMessage());
            e.printStackTrace();
		}
		return response;
	}


	/**
	 *
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 105681
	 * @version 0.1 2014-4-12
	 * @param esbHeader
	 * @param request
	 * @return
	 * @throws CommException
	 */
	@Override
	public RecallPlanResponse sendRecallPlan(Holder<ESBHeader> esbHeader,
			RecallPlanRequest request) throws CommException {
		ESBHeader header = esbHeader.value;
		header.setResponseId(UUID.randomUUID().toString());
		header.setResultCode(1);
		header.setEsbServiceCode(com.deppon.crm.module.client.common.util.Constant.CUSTMARKETTASK_TO_CRM);
		RecallPlanResponse response=new RecallPlanResponse();
		try{
			IntefacesTool.validateccNull(request,Constant.PARAMS_LOSE);
			IntefacesTool.validateccNull(request.getCustNumber(),"custNumber is null");
			Member member = memberManager.getMemberByCustNumber(request.getCustNumber());
        	IntefacesTool.validateccNull(member,"custNumer not exist");
			Department dept = departmentService.getDeptByStandardCode(request.getExecuteDept());
    		IntefacesTool.validateccNull(dept,"executeDept not exist");
    		User user = userService.findByLoginName(request.getCreator());
    		IntefacesTool.validateccNull(user,"creator not exist");
			CCPushPlanInfo ccPushPlanInfo=IntefacesTool.convertToCCPushPlanInfo(request);
			String tip=customerCallManager.addCCPlanInfo(ccPushPlanInfo);
            response.setIsSuccess(true);
            response.setErrorMsg(tip);
		}catch(GeneralException e){
			header.setResultCode(0);
			response.setIsSuccess(false);
			String errorMsg = IntefacesTool.getMessage("marketing",
					e.getErrorCode());
			response.setErrorMsg(errorMsg);
		}catch(CommException e){
			header.setResultCode(0);
			response.setIsSuccess(false);
			response.setErrorMsg(e.getMessage());
		}
		return response;
	}

	/**
	 *
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 105681
	 * @version 0.1 2014-4-10
	 * @param esbHeader
	 * @param request
	 * @return
	 * @throws CommException
	 */
	@Override
	public CreateCallCenterCustomerResponse createCustomerInfo(
			Holder<ESBHeader> esbHeader, CreateCallCenterCustomerRequest request)
			throws CommException {
		ESBHeader header = esbHeader.value;
		header.setResponseId(UUID.randomUUID().toString());
		header.setResultCode(1);
		header.setEsbServiceCode(com.deppon.crm.module.client.common.util.Constant.CUSTINFO_TO_CRM);
		CreateCallCenterCustomerResponse response=new CreateCallCenterCustomerResponse();
		try{
			IntefacesTool.validateccNull(request,Constant.PARAMS_LOSE);
            ChannelCustomer channelCustomer=IntefacesTool.convertToChannelCustomerForCreate(request);
            channelCustomer.setChannelSource("CALLCENTER");
            Map<String,String> resultMap=memberManager.createChannelCutomer(channelCustomer);
            String result=resultMap.get("message");
            if("success".equals(result)){
            	response.setIfSuccess("Y");
            }else{
            	response.setIfSuccess("N");
            	response.setErrorMsg("CC customer is aready crm customer, save it fail!");
            }

		}catch(CustomerException e){
			header.setResultCode(0);
            response.setIfSuccess("N");
            String errorMsg = IntefacesTool.getMessage("customer",
    				e.getErrorCode());
            response.setErrorMsg(errorMsg);
		}catch(MemberException e){
			header.setResultCode(0);
            response.setIfSuccess("N");
            String errorMsg = IntefacesTool.getMessage("customer",
    				e.getErrorCode());
            response.setErrorMsg(errorMsg);
		}catch(CommException e){
			header.setResultCode(0);
            response.setIfSuccess("N");
            response.setErrorMsg(e.getMessage());
		}catch(Exception e){
			header.setResultCode(0);
            response.setIfSuccess("N");
            response.setErrorMsg(e.getMessage());
		}
		return response;
	}

	/**
	 *
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 105681
	 * @version 0.1 2014-4-10
	 * @param esbHeader
	 * @param request
	 * @return
	 * @throws CommException
	 */
	@Override
	public BusinessOpportunityIfexistResponse validateBusinessOpportunity(
			Holder<ESBHeader> esbHeader,
			BusinessOpportunityIfexistRequest request) throws CommException {
		ESBHeader header = esbHeader.value;
		header.setResponseId(UUID.randomUUID().toString());
		header.setResultCode(1);
		header.setEsbServiceCode(com.deppon.crm.module.client.common.util.Constant.BUSINESSOPPORTUNITY_VALIDATE_IFEXIST);
		BusinessOpportunityIfexistResponse response=new BusinessOpportunityIfexistResponse();
        try{
        	IntefacesTool.validateccNull(request,Constant.PARAMS_LOSE);
        	IntefacesTool.validateccNull(request.getCustNumber(),"custNumber not exist");
        	boolean result=businessOpportunityManager.isExistedActiveByCustNum(request.getCustNumber());
        	response.setIfHasBusinessOpportunity(result);
        }catch(CommException e){
        	header.setResultCode(0);
            response.setIfHasBusinessOpportunity(false);
            response.setErrorMessage(e.getMessage());
        }
		return  response;
	}

	/**
	 *
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 105681
	 * @version 0.1 2014-4-14
	 * @param esbHeader
	 * @param request
	 * @return
	 * @throws CommException
	 */
	@Override
	public MarketingInfoQueryDetailResponse queryMarketingDetailInfo(
			Holder<ESBHeader> esbHeader, MarketingInfoQueryDetailRequest request)
			throws CommException {
		ESBHeader header = esbHeader.value;
		header.setResponseId(UUID.randomUUID().toString());
		header.setResultCode(1);
		header.setEsbServiceCode(com.deppon.crm.module.client.common.util.Constant.CUSTMARKETDETAIL_QUERY);
		MarketingInfoQueryDetailResponse response = new MarketingInfoQueryDetailResponse();
		try {
			IntefacesTool.validateccNull(request, Constant.PARAMS_LOSE);
			IntefacesTool.validateccNull(request.getRecallId(),
					Constant.PARAMS_LOSE);
			CCQueryMarketingInfoDetail marketingDetail = customerCallManager
					.queryMarketingInfoDetailToCC(request.getCustId(),
							request.getRecallId());
			response=IntefacesTool.convertToMarketingInfoQueryDetailResponse(marketingDetail);
		} catch(GeneralException e){
			String errorMsg = IntefacesTool.getMessage("marketing",
    				e.getErrorCode());
			throw new CommException(errorMsg, e.getCause());
		}catch (CommException e) {
			e.printStackTrace();
			throw new CommException(e.getMessage(), e.getCause());
		}
		return response;
	}

}
