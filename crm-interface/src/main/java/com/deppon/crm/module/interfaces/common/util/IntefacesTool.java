package com.deppon.crm.module.interfaces.common.util;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.common.util.JsonMapperUtil;
import com.deppon.crm.module.interfaces.cc.domain.CreateCallCenterCustomerRequest;
import com.deppon.crm.module.interfaces.cc.domain.CustomerNeed;
import com.deppon.crm.module.interfaces.cc.domain.GoodsPotential;
import com.deppon.crm.module.interfaces.cc.domain.ItemInfo;
import com.deppon.crm.module.interfaces.cc.domain.LinkmanInfoCreate;
import com.deppon.crm.module.interfaces.cc.domain.LinkmanInfoUpdate;
import com.deppon.crm.module.interfaces.cc.domain.MarketingInfoQueryDetailResponse;
import com.deppon.crm.module.interfaces.cc.domain.QuestionDetail;
import com.deppon.crm.module.interfaces.cc.domain.Questionnaire;
import com.deppon.crm.module.interfaces.cc.domain.RecallPlanRequest;
import com.deppon.crm.module.interfaces.cc.domain.UpdateCallCenterCustomerRequest;
import com.deppon.crm.module.client.customer.domain.RegisterInfo;
import com.deppon.crm.module.client.order.domain.WaybillRecompenseInfo;
import com.deppon.crm.module.client.workflow.domain.PaymentInfo;
import com.deppon.crm.module.common.server.util.DataDictionaryUtil;
import com.deppon.crm.module.common.shared.domain.AccountBank;
import com.deppon.crm.module.common.shared.domain.AccountBranch;
import com.deppon.crm.module.common.shared.domain.Area;
import com.deppon.crm.module.common.shared.domain.BankCity;
import com.deppon.crm.module.common.shared.domain.BankProvince;
import com.deppon.crm.module.common.shared.domain.DataHeadTypeEnum;
import com.deppon.crm.module.common.shared.domain.ExpressPointBusinessDept;
import com.deppon.crm.module.common.shared.domain.PilotCity;
import com.deppon.crm.module.interfaces.bank.domain.BankInfo;
import com.deppon.crm.module.interfaces.bank.domain.ProvinceCityInfo;
import com.deppon.crm.module.common.shared.domain.BussinessDept;
import com.deppon.crm.module.common.shared.domain.City;
import com.deppon.crm.module.common.shared.domain.Province;
import com.deppon.crm.module.complaint.shared.domain.Complaint;
import com.deppon.crm.module.coupon.shared.domain.CouponForInterface;
import com.deppon.crm.module.coupon.shared.domain.GoodsLine;
import com.deppon.crm.module.coupon.shared.domain.WaybillInfo;
import com.deppon.crm.module.customer.shared.domain.ChannelCustomer;
import com.deppon.crm.module.customer.shared.domain.ContractDebtDay;
import com.deppon.crm.module.interfaces.customer.domain.Account;
import com.deppon.crm.module.interfaces.customer.domain.ClaimInfo;
import com.deppon.crm.module.interfaces.customer.domain.Contact;
import com.deppon.crm.module.interfaces.customer.domain.ContactCustBindRequest;
import com.deppon.crm.module.interfaces.customer.domain.Member;
import com.deppon.crm.module.interfaces.foss.domain.AmountInfo;
import com.deppon.crm.module.interfaces.foss.domain.CommException;
import com.deppon.crm.module.interfaces.foss.domain.CouponWaybillInfo;
import com.deppon.crm.module.interfaces.foss.domain.HasUsedAmountInfo;
import com.deppon.crm.module.interfaces.foss.domain.OrderInfo;
import com.deppon.crm.module.interfaces.foss.domain.QueryClaimbillResponse;
import com.deppon.crm.module.interfaces.foss.domain.scatter.ScatterInfo;
import com.deppon.crm.module.interfaces.foss.domain.sync.DistrictInfo;
import com.deppon.crm.module.interfaces.foss.domain.sync.OrganizationInfo;
import com.deppon.crm.module.interfaces.order.domain.ComplaintOrderBindRequest;
import com.deppon.crm.module.interfaces.ows.domain.CreateNewCouponRequest;
import com.deppon.crm.module.interfaces.ows.domain.TakeGoodsArea;
import com.deppon.crm.module.interfaces.ows.jmsdomain.CreateOwsCustomerRequest;
import com.deppon.crm.module.interfaces.workflow.domain.OnlineApplyInfo;
import com.deppon.crm.module.interfaces.workflow.domain.RecompenseOnlineVerifyInfo;
import com.deppon.crm.module.marketing.shared.domain.CCPushPlanInfo;
import com.deppon.crm.module.marketing.shared.domain.CCQueryMarketingInfo;
import com.deppon.crm.module.marketing.shared.domain.CCQueryMarketingInfoDetail;
import com.deppon.crm.module.marketing.shared.domain.CCQueryMarketingRecord;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitOpinionVo;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitVolumePotentialVo;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionOption;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionnaireDto;
import com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionnaireInfo;
import com.deppon.crm.module.order.shared.domain.Order;
import com.deppon.crm.module.recompense.shared.domain.GetRecompenseByWayBill;
import com.deppon.crm.module.recompense.shared.domain.OnlineApply;
import com.deppon.crm.module.recompense.shared.domain.RecompenseForCC;
import com.deppon.foss.framework.cache.CacheManager;
import com.deppon.foss.framework.cache.ICache;
import com.deppon.foss.framework.server.cache.message.MessageCache;
import com.deppon.foss.framework.shared.util.Properties;
import com.deppon.foss.express.RalationDetail;
/**
 * @作者：罗典
 * @时间：2012-4-18
 * @描述：接口信息转换工具
 * */
public class IntefacesTool {

	//运输方式
	public static String Order_TRANSPORTMODE_PACKAGE="PACKAGE";
	/**
	 * 订单来源
	 */
	public static String ORDER_SOURCE_ONLINE = "ONLINE";
	public static String ORDER_SOURCE_CALLCENTER = "CALLCENTER";
	public static String ORDER_SOURCE_TAOBAO = "TAOBAO";
	public static String ORDER_SOURCE_ALIBABA = "ALIBABA";
	public static String ORDER_SOURCE_YOUSHANG = "YOUSHANG";
	public static String ORDER_SOURCE_SHANGCHENG = "SHANGCHENG";
	public static String ORDER_SOURCE_BUSINESS_DEPT = "BUSINESS_DEPT";
	public static String ORDER_SOURCE_QQSUDI="QQSUDI";
	public static String ORDER_SOURCE_SUNING="SUNING";
	public static String ORDER_SOURCE_WEIXIN = "WEIXIN";

	// 待分配
	public static final String ORDER_STATUS_WAIT_ALLOT = "WAIT_ALLOT";
	// 待受理
	public static final String ORDER_STATUS_WAIT_ACCEPT = "WAIT_ACCEPT";
	// 已受理
	public static final String ORDER_STATUS_ACCEPT = "ACCEPT";
	// 已拒绝
	public static final String ORDER_STATUS_REJECT = "REJECT";
	// 已撤销
	public static final String ORDER_STATUS_CANCEL = "CANCEL";
	// 已开单
	public static final String ORDER_STATUS_GOT = "GOT";
	// 运输中
	public static final String ORDER_STATUS_IN_TRANSIT = "IN_TRANSIT";
	// 签收成功
	public static final String ORDER_STATUS_SINGSUCCESS = "SIGNSUCCESS";
	// 签收异常
	public static final String ORDER_STATUS_SIGNFAIL = "SIGNFAIL";
	// 已约车
	public static final String ORDER_SATUTS_SHOUTCAR = "SHOUTCAR";
	// 已退回
	public static final String ORDER_SATUTS_GOBACK = "GOBACK";
	// 揽货失败
	public static final String ORDER_SATUTS_FAILGOT = "FAILGOT";
	// 接货中
	public static final String ORDER_SATUTS_RECEIPTING = "RECEIPTING";

	// 操作类型
	// 撤销
	public static final String ORDER_OPERATION_CANCEL = "CANCEL";
	// 签收
	public static final String ORDER_OPERATION_SING = "SING";
	// 出库
	public static final String ORDER_OPERATION_TRANSIT = "TRANSIT";
	// 运单作废
	public static final String ORDER_OPERATION_WAYBILL_CANCEL = "WAYBILL_CANCEL";
	// 开单
	public static final String ORDER_OPERATION_GOT = "GOT";
	// 开始接货
	public static final String ORDER_OPERATION_PICK_GOODS = "PICK_GOODS";
	// 已退回
	public static final String ORDER_OPERATION_GOBACK = "GOBACK";
	// 约车
	public static final String ORDER_OPERATION_BOOK_VEHICLE = "BOOK_VEHICLE";

	// 组织权限同步操作类型
	public static final String SYNC_ORDERRIGHT_NEW = "insert";// 新增
	public static final String SYNC_ORDERRIGHT_CANCEL = "delete";// 作废
	// 散客同步响应编码
	public static final String SCCUST_RESPONSE = "FOSS_ESB2FOSS_RECIEVE_NONFIXEDCUSTOMER";
	// 固定客户数据同步网点系统响应编码
	public static final String SUCCESS_WDGH_RESPONSE = "WDGH_ESB2WDGH_SYNC_CUSTINFO";
	// 推广活动
	public static final String MARKETING_ACTIVITY_RESPONSE="FOSS_ESB2FOSS_SEND_MARKETPROMOTION";
	/**
	 * 国家
	 */
	public static final String DISTRICT_NATION = "NATION";

	/**
	 * 省
	 */
	public static final String DISTRICT_PROVINCE = "DISTRICT_PROVINCE";

	/**
	 * 市
	 */
	public static final String DISTRICT_CITY = "CITY";
	/**
	 * 区县
	 */
	public static final String DISTRICT_COUNTY = "DISTRICT_COUNTY";

	public static final String ORDER_EXCEPTION_RECEIPTING = "只有已约车，接货中订单才能修改为接货中!";
	public static final String ORDER_EXCEPTION_GOBACK = "只有已约车，接货中订单才能修改为已退回!";
	public static final String ORDER_EXCEPTION_GOT = "只有接货中，已退回，已受理，已开单订单才能修改为已开单!";
	public static final String ORDER_EXCEPTION_ACCEPT = "只有待受理，已约车，已开单,接货中,正常签收，异常签收，已退回才能修改为已受理!";
	public static final String ORDER_EXCEPTION_INTRANSIT = "只有已开单，运输中订单才能修改为运输中!";
	public static final String ORDER_EXCEPTION_SINGSUCCESS = "只有已开单，运输中，正常签收，异常签收订单才能修改为签收成功!";
	public static final String ORDER_EXCEPTION_SIGNFAIL = "只有已开单，运输中，正常签收，异常签收订单才能修改为签收失败!";
	public static final String ORDER_EXCEPTION_SHOUTCAR = "只有已约车，已退回订单才能修改为已约车";
	public static final String ORDER_EXCEPTION_NOWAYBILL = "传入的运单号为空";
	public static final String ORDER_EXCEPTION_STATUSERROR = "无法解析的订单状态";

	/**
	 *
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 105681
	 * @version 0.1 2014-5-19
	 * @param codeInfo
	 * @return
	 * CCQueryMarketingInfo
	 */
	public static CCQueryMarketingInfo convertEnumTodesc(
			CCQueryMarketingInfo codeInfo) {
		List<CCQueryMarketingRecord> records = codeInfo.getRecords();
		for (int i = 0; i < records.size(); i++) {
			CCQueryMarketingRecord record = records.get(i);
			String marketingMode = DataDictionaryUtil.getCodeDesc(
					DataHeadTypeEnum.DEVELOP_WAY, record.getMarketingMode());
			record.setMarketingMode(marketingMode);
		}
		codeInfo.setRecords(records);
		return codeInfo;
	}

	/**
	 *
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 105681
	 * @version 0.1 2014-4-15
	 * @param request
	 * @return
	 * ChannelCustomer
	 */
	public static ChannelCustomer convertToChannelCustomer(CreateOwsCustomerRequest request){
		ChannelCustomer owsCustomer=new ChannelCustomer();
		owsCustomer.setCustName(request.getUserName());
        owsCustomer.setMobilePhone(request.getMobilephone());
        owsCustomer.setTelPhone(request.getTelephone());
        owsCustomer.setLinkManName(request.getLinkmanName());
        owsCustomer.setProvince(request.getProvinceCode());
        owsCustomer.setCity(request.getCityCode());
        owsCustomer.setArea(request.getAreaCode());
        owsCustomer.setProvinceName(request.getProvinceName());
        owsCustomer.setCityName(request.getCityName());
        owsCustomer.setAreaName(request.getAreaName());
        owsCustomer.setAddress(request.getDetailAddress());
        owsCustomer.setEmail(request.getEmail());
        owsCustomer.setBeforeMobilePhone(request.getMobilephoneBU());
        owsCustomer.setBeforeLinkManName(request.getLinkmanNameBU());
        owsCustomer.setBeforeTelPhone(request.getTelephoneBU());
		return owsCustomer;
	}

	/**
	 *
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 105681
	 * @version 0.1 2014-4-15
	 * @param scatterInfo
	 * @return
	 * ChannelCustomer
	 */
	public static ChannelCustomer convertToChannelCustomer(
			ScatterInfo scatterInfo, String provinceName, String cityName,
			String areaName) {
		ChannelCustomer customer = new ChannelCustomer();
		customer.setErpId(scatterInfo.getFossId());
		customer.setCustName(scatterInfo.getCustName());
		customer.setCustNumber(scatterInfo.getCustNumber());
		customer.setLinkManName(scatterInfo.getLinkmanName());
		customer.setMobilePhone(scatterInfo.getMobilephone());
		customer.setTelPhone(scatterInfo.getTelephone());
		customer.setAddress(scatterInfo.getLinkmanAdress());
		customer.setCustNature(scatterInfo.getCustAttribute());
		customer.setCreateUser(scatterInfo.getCreateUser());
		customer.setCreateDate(scatterInfo.getCreateTime());
		customer.setCustCategory(scatterInfo.getBusinessType());
		customer.setDeptStandCode(scatterInfo.getStandardCode());
		customer.setProvince(scatterInfo.getProvinceCode());
		customer.setCity(scatterInfo.getCityCode());
		customer.setArea(scatterInfo.getAreaCode());
		customer.setProvinceName(provinceName);
		customer.setCityName(cityName);
		customer.setAreaName(areaName);
		return customer;
	}

	/**
	 *
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 105681
	 * @version 0.1 2014-4-14
	 * @param request
	 * @return
	 * CCPushPlanInfo
	 */
	public static CCPushPlanInfo convertToCCPushPlanInfo(RecallPlanRequest request){
		CCPushPlanInfo ccPlan=new CCPushPlanInfo();
		ccPlan.setCustNumber(request.getCustNumber());
		ccPlan.setPlanStartTime(request.getBeginTime());
		ccPlan.setPlanEndTime(request.getEndTime());
        ccPlan.setPlanTheme(request.getPlanSubject());
        ccPlan.setExecuteDept(request.getExecuteDept());
        ccPlan.setCreatorCode(request.getCreator());
        ccPlan.setRemark(request.getRemark());
        ccPlan.setProjectType(request.getProjectType());
		return ccPlan;
	}


	/**
	 *
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 105681
	 * @version 0.1 2014-4-14
	 * @param marketingDetail
	 * @return
	 * MarketingInfoQueryDetailResponse
	 */
	public static MarketingInfoQueryDetailResponse convertToMarketingInfoQueryDetailResponse(
			CCQueryMarketingInfoDetail marketingDetail) {
		MarketingInfoQueryDetailResponse response = new MarketingInfoQueryDetailResponse();
		List<CustomerNeed> customerNeeds = new ArrayList<CustomerNeed>();
		List<GoodsPotential> goodsPotentials = new ArrayList<GoodsPotential>();
		List<Questionnaire> questionnaires = new ArrayList<Questionnaire>();
		for (ReturnVisitOpinionVo opinion : marketingDetail.getOpinions()) {
			CustomerNeed cn = new CustomerNeed();
			cn.setNeedsType(DataDictionaryUtil.getCodeDesc(
					DataHeadTypeEnum.CUSTOMER_IDEA, opinion.getOpinionType()));
			cn.setNeedsAndQuestion(opinion.getProblem());
			cn.setSolution(opinion.getSolution());
			customerNeeds.add(cn);
		}
		if (0 != customerNeeds.size()) {
			response.getCustomerNeed().addAll(customerNeeds);
		}

		for (ReturnVisitVolumePotentialVo potential : marketingDetail
				.getPotentials()) {
			GoodsPotential goodsPotential = new GoodsPotential();
			goodsPotential.setStartCity(potential.getComeFromCity());
			goodsPotential.setArrivalCity(potential.getComeToCity());
			goodsPotential.setPotential(potential.getVolumePotential());
			goodsPotential.setCoLogCompany(DataDictionaryUtil.getCodeDesc(
					DataHeadTypeEnum.COOPERATION_LOGISTICS, potential.getCompanyId()));
			if (null != potential.getAlreadyHaveLine()
					&& "" != potential.getAlreadyHaveLine()) {
				if ("YES".equals(potential.getAlreadyHaveLine())) {
					goodsPotential.setIfhasLine(true);
				} else if ("NO".equals(potential.getAlreadyHaveLine())) {
					goodsPotential.setIfhasLine(false);
				}
			}
			goodsPotential.setColIntention(DataDictionaryUtil.getCodeDesc(
					DataHeadTypeEnum.COOPERATION_INTENTION, potential.getCooperate()));
			if (null != potential.getAccord() && "" != potential.getAccord()) {
				if ("1".equals(potential.getAccord())) {
					goodsPotential.setIfOurCarriage(true);
				} else if ("0".equals(potential.getAccord())) {
					goodsPotential.setIfOurCarriage(false);
				}
			}
			goodsPotential.setRemark(potential.getAccordMark());
			goodsPotentials.add(goodsPotential);
		}

		if (0 != goodsPotentials.size()) {
			response.getGoodsPotential().addAll(goodsPotentials);
		}
		for (QuestionnaireDto dto : marketingDetail.getDto()) {
			QuestionnaireInfo questionnaireInfo = dto.getQuestionnaire();
			Questionnaire questionnaire=null;
			if(null!=questionnaireInfo){
				questionnaire=new Questionnaire();
				questionnaire.setNumber(questionnaireInfo.getQuestionnaireCode());
				questionnaire.setName(questionnaireInfo.getQuestionnaireName());
				questionnaire.setUsingRange(questionnaireInfo.getUseRange());
				questionnaire
						.setEffectiveDate(questionnaireInfo.getEffectiveTime());
				questionnaire.setExpiryDate(questionnaireInfo.getInvalidTime());
				questionnaire.setUseTimes(questionnaireInfo.getUseTimes());
				questionnaire.setInstructions(questionnaireInfo.getDesc());
				questionnaire.setState(DataDictionaryUtil.getCodeDesc(
						DataHeadTypeEnum.SURVEY_STATUS, questionnaireInfo.getStatus()));
				questionnaire.setCreateTime(questionnaireInfo.getCreateDate());
				questionnaire.setCreator(questionnaireInfo.getCreateUserId());
				questionnaire.setLastModifyTime(questionnaireInfo.getModifyDate());
				questionnaire.setModifier(questionnaireInfo.getLastModifyUserId());
				List<QuestionDetail> questionDetails = new ArrayList<QuestionDetail>();
				for (com.deppon.crm.module.marketing.shared.domain.questionnaire.QuestionDetail qDetail : dto
						.getQuestionList()) {
					QuestionDetail questionDetail = new QuestionDetail();
					questionDetail.setSequence(qDetail.getQuestionSeq() + "");
					questionDetail.setDescription(qDetail.getQuestionContent());
					questionDetail.setType(qDetail.getQuestionType());
					List<ItemInfo> itemInfos = new ArrayList<ItemInfo>();
					for (QuestionOption option : qDetail.getOptions()) {
						ItemInfo itemInfo = new ItemInfo();
						itemInfo.setSequence(option.getOptionSeq() + "");
						itemInfo.setContent(option.getOptionDes());
						if (null != option.getIsSelected()
								&& "" != option.getIsSelected()) {
							if ("1".equals(option.getIsSelected())) {
								itemInfo.setIfchoosed(true);
							} else if ("0".equals(option.getIsSelected())) {
								itemInfo.setIfchoosed(false);
							}
						}
						itemInfo.setRemark(option.getAnswer());
						itemInfos.add(itemInfo);
					}
					questionDetail.getItemInfos().addAll(itemInfos);
					questionDetails.add(questionDetail);
				}
				if(0!=questionDetails.size()){
					questionnaire.getQuestionDetails().addAll(questionDetails);
				}
			}
			if(null!=questionnaire){
				questionnaires.add(questionnaire);
			}
		}
		if (0 != questionnaires.size()) {
			response.getQuestionnaire().addAll(questionnaires);
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
	 * @param request
	 * @return
	 * ChannelCustomer
	 */
	public static ChannelCustomer convertToChannelCustomerForCreate(
			CreateCallCenterCustomerRequest request) {
		ChannelCustomer cust = new ChannelCustomer();
		cust.setCustNumber(request.getCustNumber());
		cust.setCustType(request.getCustType());
		cust.setCustName(request.getCustName());
		cust.setDeptStandCode(request.getStdeptcode());
		cust.setAddress(request.getPickAddress());
		cust.setCustCategory(request.getCustCategory());
		List<com.deppon.crm.module.customer.shared.domain.Contact> contactList = new ArrayList<com.deppon.crm.module.customer.shared.domain.Contact>();
		for (LinkmanInfoCreate linkman : request.getLinkmanInfos()) {
			com.deppon.crm.module.customer.shared.domain.Contact contact = new com.deppon.crm.module.customer.shared.domain.Contact();
			if("".equals(linkman.getName())||null==linkman.getName()){
				contact.setName(com.deppon.crm.module.customer.shared.domain.Constant.CONTACT_NONAME);
			}else{
				contact.setName(linkman.getName());
			}
			contact.setMobilePhone(linkman.getCellphone());
			contact.setEmail(linkman.getEmail());
			contact.setFfax(linkman.getFax());
			contact.setTelPhone(linkman.getTelephone());
			contactList.add(contact);
		}
		cust.setContactList(contactList);
		return cust;
	}
	/**
	 *
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 105681
	 * @version 0.1 2014-4-10
	 * @param request
	 * @return
	 * ChannelCustomer
	 */
	public static ChannelCustomer convertToChannelCustomerForUpdate(
			UpdateCallCenterCustomerRequest request) {
		ChannelCustomer cust = new ChannelCustomer();
		cust.setCustNumber(request.getCustNumber());
		cust.setCustType(request.getCustType());
		cust.setCustName(request.getCustName());
		//CC传的是名称
		cust.setDeptStandCode(request.getStdeptcode());
		cust.setAddress(request.getPickAddress());
		cust.setCustCategory(request.getCustCategory());

		List<com.deppon.crm.module.customer.shared.domain.Contact> contactList = new ArrayList<com.deppon.crm.module.customer.shared.domain.Contact>();
		for (LinkmanInfoUpdate linkman : request.getLinkmanInfos()) {
			com.deppon.crm.module.customer.shared.domain.Contact contact = new com.deppon.crm.module.customer.shared.domain.Contact();
			contact.setName(linkman.getName());
			contact.setMobilePhone(linkman.getCellphone());
			contact.setEmail(linkman.getEmail());
			contact.setFfax(linkman.getFax());
			contact.setTelPhone(linkman.getTelephone());
			contact.setId(linkman.getCrmid());
			contactList.add(contact);
		}
		if(contactList.size()!=0){
			cust.setContactList(contactList);
		}
		return cust;
	}

	/**
	 * @description
	 * @author wugenbin_吴根斌
	 * @version 2013-7-9
	 * @param
	 * @return
	 */
	public static RegisterInfo changeToRegisterInfo(ContactCustBindRequest request){
		RegisterInfo info=new RegisterInfo();
		info.setUserName(request.getUserName());
		//info.setCustsource(IntefacesTool.changeOrderResourceCodeToString(request.getCustsource()));
		info.setCusCode(request.getCusCode());
		info.setTelephone(request.getTelephone());
		info.setFixedPhone(request.getFixedPhone());
		StringBuffer contactAddress= new StringBuffer("");
		if (!StringUtils.isEmpty(request.getProvince())) {
			contactAddress.append(request.getProvince());
		}
		if (!StringUtils.isEmpty(request.getCity())) {
			contactAddress.append(request.getCity());
		}
		if (!StringUtils.isEmpty(request.getArea())) {
			contactAddress.append(request.getArea());
		}
		if (!StringUtils.isEmpty(request.getAddress())) {
			contactAddress.append(request.getAddress());
		}
		request.setAddress(contactAddress.toString());
		info.setAddress(request.getAddress());
		info.setCreateUser("86301");
		info.setCustsource("ONLINE");
		info.setCreateDate(new Date());
		info.setRealName(request.getRealName());
		return info;
	}
	public static com.deppon.crm.module.interfaces.order.domain.Order orderTOinterfaceOrder(Order order){
		if(null==order){
			return null;
		}
		com.deppon.crm.module.interfaces.order.domain.Order interfaceOrder=new com.deppon.crm.module.interfaces.order.domain.Order();
		interfaceOrder.setCreateDate(order.getCreateDate());
		interfaceOrder.setCreateUser(order.getCreateUser());
		interfaceOrder.setId(order.getId());
		interfaceOrder.setModifyDate(order.getModifyDate());
		interfaceOrder.setModifyUser(order.getModifyUser());
		interfaceOrder.setHastenCount(order.getHastenCount());
		interfaceOrder.setLastHastenTime(order.getLastHastenTime());
		interfaceOrder.setContactManId(order.getContactManId());
		interfaceOrder.setOrderNumber(order.getOrderNumber());
		interfaceOrder.setChannelNumber(order.getChannelNumber());
		interfaceOrder.setShipperId(order.getShipperId());
		interfaceOrder.setShipperNumber(order.getShipperNumber());
		interfaceOrder.setShipperName(order.getShipperName());
		interfaceOrder.setContactName(order.getContactName());
		interfaceOrder.setContactMobile(order.getContactMobile());
		interfaceOrder.setContactPhone(order.getContactPhone());
		interfaceOrder.setContactProvince(order.getContactProvince());
		interfaceOrder.setContactCity(order.getContactCity());
		interfaceOrder.setContactArea(order.getContactArea());
		interfaceOrder.setContactAddress(order.getContactAddress());
		interfaceOrder.setIsReceiveGoods(order.getIsReceiveGoods());
		interfaceOrder.setBeginAcceptTime(order.getBeginAcceptTime());
		interfaceOrder.setEndAcceptTime(order.getEndAcceptTime());
		interfaceOrder.setReceiverCustId(order.getReceiverCustId());
		interfaceOrder.setReceiverCustNumber(order.getReceiverCustNumber());
		interfaceOrder.setReceiverCustcompany(order.getReceiverCustcompany());
		interfaceOrder.setReceiverCustName(order.getReceiverCustName());
		interfaceOrder.setReceiverCustMobile(order.getReceiverCustMobile());
		interfaceOrder.setReceiverCustPhone(order.getReceiverCustPhone());
		interfaceOrder.setReceiverCustProvince(order.getReceiverCustProvince());
		interfaceOrder.setReceiverCustCity(order.getReceiverCustCity());
		interfaceOrder.setReceiverCustArea(order.getReceiverCustArea());
		interfaceOrder.setReceiverCustAddress(order.getReceiverCustAddress());
		interfaceOrder.setIsSendmessage(order.getIsSendmessage());
		interfaceOrder.setReceivingToPoint(order.getReceivingToPoint());
		interfaceOrder.setReceivingToPointName(order.getReceivingToPointName());
		interfaceOrder.setTransportMode(order.getTransportMode());
		interfaceOrder.setGoodsName(order.getGoodsName());
		interfaceOrder.setPacking(order.getPacking());
		interfaceOrder.setGoodsType(order.getGoodsType());
		interfaceOrder.setGoodsNumber(order.getGoodsNumber());
		interfaceOrder.setTotalWeight(order.getTotalWeight());
		interfaceOrder.setTotalVolume(order.getTotalVolume());
		interfaceOrder.setPaymentType(order.getPaymentType());
		interfaceOrder.setChannelType(order.getChannelType());
		interfaceOrder.setChannelCustId(order.getChannelCustId());
		interfaceOrder.setDeliveryMode(order.getDeliveryMode());
		interfaceOrder.setReciveLoanType(order.getReciveLoanType());
		interfaceOrder.setReviceMoneyAmount(order.getReviceMoneyAmount());
		interfaceOrder.setInsuredAmount(order.getInsuredAmount());
		interfaceOrder.setOrderTime(order.getOrderTime());
		interfaceOrder.setStartStation(order.getStartStation());
		interfaceOrder.setStartStationName(order.getStartStationName());
		interfaceOrder.setAcceptDept(order.getAcceptDept());
		interfaceOrder.setAcceptDeptName(order.getAcceptDeptName());
		interfaceOrder.setOrderStatus(order.getOrderStatus());
		interfaceOrder.setDealPerson(order.getDealPerson());
		interfaceOrder.setOrderAcceptTime(order.getOrderAcceptTime());
		interfaceOrder.setReceiver(order.getReceiver());
		interfaceOrder.setAccepterPersonMobile(order.getAccepterPersonMobile());
		interfaceOrder.setWaybillNumber(order.getWaybillNumber());
		interfaceOrder.setResource(order.getResource());
		interfaceOrder.setTransNote(order.getTransNote());
		interfaceOrder.setReturnBillType(order.getReturnBillType());
		interfaceOrder.setOrderPerson(order.getOrderPerson());
		interfaceOrder.setFeedbackInfo(order.getFeedbackInfo());
		interfaceOrder.setOnlineName(order.getOnlineName());
		interfaceOrder.setMemberType(order.getMemberType());
		interfaceOrder.setCouponNumber(order.getCouponNumber());
		return interfaceOrder;
	}

	/**
	 * 把接口订单信息转换为订单实体
	 * @param interfaceOrder
	 * @return
	 */
	public static Order interfaceOrderToOrder(com.deppon.crm.module.interfaces.order.domain.Order interfaceOrder){
		Order order=new Order();
		order.setCreateDate(interfaceOrder.getCreateDate());
		order.setCreateUser(interfaceOrder.getCreateUser());
		order.setId(interfaceOrder.getId());
		order.setModifyDate(interfaceOrder.getModifyDate());
		order.setModifyUser(interfaceOrder.getModifyUser());
		order.setHastenCount(interfaceOrder.getHastenCount());
		order.setLastHastenTime(interfaceOrder.getLastHastenTime());
		order.setContactManId(interfaceOrder.getContactManId());
		order.setOrderNumber(interfaceOrder.getOrderNumber());
		order.setChannelNumber(interfaceOrder.getChannelNumber());
		order.setShipperId(interfaceOrder.getShipperId());
		order.setShipperNumber(interfaceOrder.getShipperNumber());
		order.setShipperName(interfaceOrder.getShipperName());
		order.setContactName(interfaceOrder.getContactName());
		order.setContactMobile(interfaceOrder.getContactMobile());
		order.setContactPhone(interfaceOrder.getContactPhone());
		order.setContactProvince(interfaceOrder.getContactProvince());
		order.setContactCity(interfaceOrder.getContactCity());
		order.setContactArea(interfaceOrder.getContactArea());
		order.setContactAddress(interfaceOrder.getContactAddress());
		order.setIsReceiveGoods(interfaceOrder.isIsReceiveGoods());
		order.setBeginAcceptTime(interfaceOrder.getBeginAcceptTime());
		order.setEndAcceptTime(interfaceOrder.getEndAcceptTime());
		order.setReceiverCustId(interfaceOrder.getReceiverCustId());
		order.setReceiverCustNumber(interfaceOrder.getReceiverCustNumber());
		order.setReceiverCustcompany(interfaceOrder.getReceiverCustcompany());
		order.setReceiverCustName(interfaceOrder.getReceiverCustName());
		order.setReceiverCustMobile(interfaceOrder.getReceiverCustMobile());
		order.setReceiverCustPhone(interfaceOrder.getReceiverCustPhone());
		order.setReceiverCustProvince(interfaceOrder.getReceiverCustProvince());
		order.setReceiverCustCity(interfaceOrder.getReceiverCustCity());
		order.setReceiverCustArea(interfaceOrder.getReceiverCustArea());
		order.setReceiverCustAddress(interfaceOrder.getReceiverCustAddress());
		order.setIsSendmessage(interfaceOrder.isIsSendmessage());
		order.setReceivingToPoint(interfaceOrder.getReceivingToPoint());
		order.setReceivingToPointName(interfaceOrder.getReceivingToPointName());
		order.setTransportMode(interfaceOrder.getTransportMode());
		order.setGoodsName(interfaceOrder.getGoodsName());
		order.setPacking(interfaceOrder.getPacking());
		order.setGoodsType(interfaceOrder.getGoodsType());
		order.setGoodsNumber(interfaceOrder.getGoodsNumber());
		order.setTotalWeight(interfaceOrder.getTotalWeight());
		order.setTotalVolume(interfaceOrder.getTotalVolume());
		order.setPaymentType(interfaceOrder.getPaymentType());
		order.setChannelType(interfaceOrder.getChannelType());
		order.setChannelCustId(interfaceOrder.getChannelCustId());
		order.setDeliveryMode(interfaceOrder.getDeliveryMode());
		order.setReciveLoanType(interfaceOrder.getReciveLoanType());
		order.setReviceMoneyAmount(interfaceOrder.getReviceMoneyAmount());
		order.setInsuredAmount(interfaceOrder.getInsuredAmount());
		order.setOrderTime(interfaceOrder.getOrderTime());
		order.setStartStation(interfaceOrder.getStartStation());
		order.setStartStationName(interfaceOrder.getStartStationName());
		order.setAcceptDept(interfaceOrder.getAcceptDept());
		order.setAcceptDeptName(interfaceOrder.getAcceptDeptName());
		order.setOrderStatus(interfaceOrder.getOrderStatus());
		order.setDealPerson(interfaceOrder.getDealPerson());
		order.setOrderAcceptTime(interfaceOrder.getOrderAcceptTime());
		order.setReceiver(interfaceOrder.getReceiver());
		order.setAccepterPersonMobile(interfaceOrder.getAccepterPersonMobile());
		order.setWaybillNumber(interfaceOrder.getWaybillNumber());
		order.setResource(interfaceOrder.getResource());
		order.setTransNote(interfaceOrder.getTransNote());
		order.setReturnBillType(interfaceOrder.getReturnBillType());
		order.setOrderPerson(interfaceOrder.getOrderPerson());
		order.setFeedbackInfo(interfaceOrder.getFeedbackInfo());
		order.setOnlineName(interfaceOrder.getOnlineName());
		order.setMemberType(interfaceOrder.getMemberType());
		order.setCouponNumber(interfaceOrder.getCouponNumber());
		return order;
	}

	public static List<com.deppon.crm.module.interfaces.order.domain.Order> OrderListToInterfaceOrderList(List<Order> orderList){
		List<com.deppon.crm.module.interfaces.order.domain.Order> interfaceOrderList = new ArrayList<com.deppon.crm.module.interfaces.order.domain.Order>();
		for(Order order:orderList){
			com.deppon.crm.module.interfaces.order.domain.Order interfaceOrder=new com.deppon.crm.module.interfaces.order.domain.Order();
			interfaceOrder.setCreateDate(order.getCreateDate());
			interfaceOrder.setCreateUser(order.getCreateUser());
			interfaceOrder.setId(order.getId());
			interfaceOrder.setModifyDate(order.getModifyDate());
			interfaceOrder.setModifyUser(order.getModifyUser());
			interfaceOrder.setHastenCount(order.getHastenCount());
			interfaceOrder.setLastHastenTime(order.getLastHastenTime());
			interfaceOrder.setContactManId(order.getContactManId());
			interfaceOrder.setOrderNumber(order.getOrderNumber());
			interfaceOrder.setChannelNumber(order.getChannelNumber());
			interfaceOrder.setShipperId(order.getShipperId());
			interfaceOrder.setShipperNumber(order.getShipperNumber());
			interfaceOrder.setShipperName(order.getShipperName());
			interfaceOrder.setContactName(order.getContactName());
			interfaceOrder.setContactMobile(order.getContactMobile());
			interfaceOrder.setContactPhone(order.getContactPhone());
			interfaceOrder.setContactProvince(order.getContactProvince());
			interfaceOrder.setContactCity(order.getContactCity());
			interfaceOrder.setContactArea(order.getContactArea());
			interfaceOrder.setContactAddress(order.getContactAddress());
			interfaceOrder.setIsReceiveGoods(order.getIsReceiveGoods());
			interfaceOrder.setBeginAcceptTime(order.getBeginAcceptTime());
			interfaceOrder.setEndAcceptTime(order.getEndAcceptTime());
			interfaceOrder.setReceiverCustId(order.getReceiverCustId());
			interfaceOrder.setReceiverCustNumber(order.getReceiverCustNumber());
			interfaceOrder.setReceiverCustcompany(order.getReceiverCustcompany());
			interfaceOrder.setReceiverCustName(order.getReceiverCustName());
			interfaceOrder.setReceiverCustMobile(order.getReceiverCustMobile());
			interfaceOrder.setReceiverCustPhone(order.getReceiverCustPhone());
			interfaceOrder.setReceiverCustProvince(order.getReceiverCustProvince());
			interfaceOrder.setReceiverCustCity(order.getReceiverCustCity());
			interfaceOrder.setReceiverCustArea(order.getReceiverCustArea());
			interfaceOrder.setReceiverCustAddress(order.getReceiverCustAddress());
			interfaceOrder.setIsSendmessage(order.getIsSendmessage());
			interfaceOrder.setReceivingToPoint(order.getReceivingToPoint());
			interfaceOrder.setReceivingToPointName(order.getReceivingToPointName());
			interfaceOrder.setTransportMode(order.getTransportMode());
			interfaceOrder.setGoodsName(order.getGoodsName());
			interfaceOrder.setPacking(order.getPacking());
			interfaceOrder.setGoodsType(order.getGoodsType());
			interfaceOrder.setGoodsNumber(order.getGoodsNumber());
			interfaceOrder.setTotalWeight(order.getTotalWeight());
			interfaceOrder.setTotalVolume(order.getTotalVolume());
			interfaceOrder.setPaymentType(order.getPaymentType());
			interfaceOrder.setChannelType(order.getChannelType());
			interfaceOrder.setChannelCustId(order.getChannelCustId());
			interfaceOrder.setDeliveryMode(order.getDeliveryMode());
			interfaceOrder.setReciveLoanType(order.getReciveLoanType());
			interfaceOrder.setReviceMoneyAmount(order.getReviceMoneyAmount());
			interfaceOrder.setInsuredAmount(order.getInsuredAmount());
			interfaceOrder.setOrderTime(order.getOrderTime());
			interfaceOrder.setStartStation(order.getStartStation());
			interfaceOrder.setStartStationName(order.getStartStationName());
			interfaceOrder.setAcceptDept(order.getAcceptDept());
			interfaceOrder.setAcceptDeptName(order.getAcceptDeptName());
			interfaceOrder.setOrderStatus(order.getOrderStatus());
			interfaceOrder.setDealPerson(order.getDealPerson());
			interfaceOrder.setOrderAcceptTime(order.getOrderAcceptTime());
			interfaceOrder.setReceiver(order.getReceiver());
			interfaceOrder.setAccepterPersonMobile(order.getAccepterPersonMobile());
			interfaceOrder.setWaybillNumber(order.getWaybillNumber());
			interfaceOrder.setResource(order.getResource());
			interfaceOrder.setTransNote(order.getTransNote());
			interfaceOrder.setReturnBillType(order.getReturnBillType());
			interfaceOrder.setOrderPerson(order.getOrderPerson());
			interfaceOrder.setFeedbackInfo(order.getFeedbackInfo());
			interfaceOrder.setOnlineName(order.getOnlineName());
			interfaceOrder.setMemberType(order.getMemberType());
			interfaceOrder.setCouponNumber(order.getCouponNumber());
			interfaceOrderList.add(interfaceOrder);
		}
		return interfaceOrderList;
	}

	public static Member convertToIMember(
			com.deppon.crm.module.customer.shared.domain.Member member) {
		Member imember = new Member();
		if (member == null) {
			return null;
		}
		// 主联系人
		Contact imainContact = new Contact();
		com.deppon.crm.module.customer.shared.domain.Contact mainContact = member
				.getMainContact();
		if (mainContact != null) {
			BeanUtils.copyProperties(mainContact, imainContact);
		}
		imember.setMainContact(imainContact);
		// 账号信息
		List<Account> iaccountList = new ArrayList<Account>();
		List<com.deppon.crm.module.customer.shared.domain.Account> accountList = member
				.getAccountList();
		if (null != accountList) {
			for (com.deppon.crm.module.customer.shared.domain.Account account : accountList) {
				Account iaccount = new Account();
				BeanUtils.copyProperties(account, iaccount);
				iaccountList.add(iaccount);
			}
		}
		imember.setAccountList(iaccountList);
		//联系人列表
		List<Contact> icontactList=new ArrayList<Contact>();
		List<com.deppon.crm.module.customer.shared.domain.Contact> contactList=member.getContactList();
		if(null!=contactList){
			for(com.deppon.crm.module.customer.shared.domain.Contact contact:contactList){
				Contact icontact=new Contact();
				BeanUtils.copyProperties(contact, icontact);
				icontactList.add(icontact);
			}
		}
		imember.setContactList(icontactList);
		imember.setId(member.getId());
		imember.setCreateDate(member.getCreateDate());
		imember.setCreateUser(member.getCreateUser());
		imember.setModifyDate(member.getModifyDate());
		imember.setModifyUser(member.getModifyUser());
		imember.setCustNumber(member.getCustNumber());
		imember.setDegree(member.getDegree());
		imember.setCustName(member.getCustName());
		imember.setTradeId(member.getTradeId());
		imember.setCustType(member.getCustType());
		imember.setTaxregNumber(member.getTaxregNumber());
		imember.setCompanyProperty(member.getCompanyProperty());
		imember.setCustNature(member.getCustNature());
		imember.setProvince(member.getProvince());
		imember.setProvinceId(member.getProvinceId());
		imember.setCity(member.getCity());
		imember.setCityId(member.getCityId());
		imember.setRegistAddress(member.getRegistAddress());
		imember.setIsSpecial(member.getIsSpecial());
		imember.setIsRedeempoints(member.getIsRedeempoints());
		imember.setIsImportCustor(member.getIsImportCustor());
		imember.setCustPotentialType(member.getCustPotentialType());
		imember.setIsAcceptMarket(member.getIsAcceptMarket());
		imember.setBrandWorth(member.getBrandWorth());
		imember.setChannelSource(member.getChannelSource());
		imember.setChannel(member.getChannel());
		imember.setPreferenceService(member.getPreferenceService());
		imember.setCompanyScop(member.getCompanyScop());
		imember.setLastYearProfit(member.getLastYearProfit());
		imember.setLastYearIncome(member.getLastYearIncome());
		imember.setIsFocusPay(member.getIsFocusPay());
		imember.setFocusDeptId(member.getFocusDeptId());
		imember.setFocusDeptName(member.getFocusDeptName());
		imember.setBillTitle(member.getBillTitle());
		imember.setIsParentCompany(member.getIsParentCompany());
		imember.setParentNumber(member.getParentNumber());
		imember.setParentCompanyId(member.getParentCompanyId());
		imember.setRegisterFund(member.getRegisterFund());
		imember.setProcRedit(member.getProcRedit());
		imember.setRecommendCust(member.getRecommendCust());
		imember.setRemark(member.getRemark());
		imember.setJshAddress(member.getJshAddress());
		imember.setSimpleName(member.getSimpleName());
		imember.setBussType(member.getBussType());
		imember.setIsTranGoods(member.getIsTranGoods());
		imember.setAreaId(member.getAreaId());
		imember.setDeptId(member.getDeptId());
		imember.setDeptName(member.getDeptName());
		imember.setCustId(member.getCustId());
		imember.setContactId(member.getContactId());
		imember.setUpgradeSource(member.getUpgradeSource());
		imember.setCustStatus(member.getCustStatus());
		imember.setBecomeMemTime(member.getBecomeMemTime());
		imember.setResponsibillity(member.getResponsibillity());
		imember.setVersionNumber(member.getVersionNumber());
		imember.setNextLevel(member.getNextLevel());
		imember.setLastChangTime(member.getLastChangTime());
		imember.setSurplusMonthlyStatementMoney(member.getSurplusMonthlyStatementMoney());
		imember.setFinOver(member.isFinOver());
		imember.setSecondTrade(member.getSecondTrade());
		imember.setPotenSource(member.getPotenSource());
		imember.setCustCategory(member.getCustCategory());
		imember.setIsExPayWay(member.getIsExPayWay());
		return imember;
	}
	/**
	 * @description
	 * @author wugenbin_吴根斌
	 * @version 2013-8-12
	 * @param
	 * @return
	 */
	public static ExpressPointBusinessDept changeRalationDetailToExpressPointBusinessDept(
			RalationDetail ralationDetail) {
		ExpressPointBusinessDept expressPointBusinessDept=new ExpressPointBusinessDept();
		expressPointBusinessDept.setId(ralationDetail.getId());
		expressPointBusinessDept.setBusinessDeptCode(ralationDetail.getSaleDeptStandardNumber());
		expressPointBusinessDept.setBusinessDeptName(ralationDetail.getSaleDeptName());
		expressPointBusinessDept.setExpressPointCode(ralationDetail.getNewExDeptStandardNumber());
		expressPointBusinessDept.setExpressPointName(ralationDetail.getNewExDeptName());
		return expressPointBusinessDept;
	}

	/**
	 *
	 * @description 把官网过来的订单来源编码转换为字符串形式表示的订单来源
	 * @author wugenbin_吴根斌
	 * @version 2013-7-9
	 * @param
	 * @return
	 */
	public static String changeOrderResourceCodeToString(String code) {
		String resource = "";
		if ("0".equals(code)) {
			resource = "ONLINE";
		} else if ("1".equals(code)) {
			resource = "TAOBAO";
		} else if ("2".equals(code)) {
			resource = "CALLCENTER";
		} else if ("3".equals(code)) {
			resource = "ALIBABA";
		} else if ("4".equals(code)) {
			resource = "YOUSHANG";
		} else if ("5".equals(code)) {
			resource = "SHANGCHENG";
		} else if ("7".equals(code)) {
			resource = "QQSUDI";
		}else{
			resource=code;
		}
		return resource;
	}

	/**
	 *
	 * @description 判断订单是否来自渠道或者官网
	 * @author wugenbin_吴根斌
	 * @version 2013-7-9
	 * @param
	 * @return
	 */
	public static boolean validateComefromUIPOrNot(String code){
		boolean flag = false;
		if ("0".equals(code)) {
			flag = true;
		} else if ("1".equals(code)) {
			flag = true;
		} else if ("2".equals(code)) {
			flag = true;
		} else if ("3".equals(code)) {
			flag = true;
		} else if ("4".equals(code)) {
			flag = true;
		} else if ("5".equals(code)) {
			flag = true;
		} else if ("7".equals(code)) {
			flag = true;
		}
		return flag;
	}
	/**
	 * @作者：罗典
	 * @时间：2012-4-18
	 * @描述：渠道订单类型由值转换为中文
	 * */
	public static String changeOrderSourceValueToDesc(String value) {
		if (value.equals(ORDER_SOURCE_ONLINE)) {
			return "网上营业厅";
		} else if (value.equals(ORDER_SOURCE_CALLCENTER)) {
			return "呼叫中心";
		} else if (value.equals(ORDER_SOURCE_TAOBAO)) {
			return "淘宝网";
		} else if (value.equals(ORDER_SOURCE_ALIBABA)) {
			return "阿里巴巴网";
		} else if (value.equals(ORDER_SOURCE_YOUSHANG)) {
			return "金蝶友商网";
		} else if (value.equals(ORDER_SOURCE_SHANGCHENG)) {
			return "淘宝商城网";
		} else if (value.equals(ORDER_SOURCE_BUSINESS_DEPT)) {
			return "营业部";
		} else if(value.equals(ORDER_SOURCE_QQSUDI)){
			return "QQ速递";
		}else if(value.equals(ORDER_SOURCE_SUNING)){
			return "苏宁易购";
		}else if (value.endsWith(ORDER_SOURCE_WEIXIN)) {
			return "微信";
		} else {
			return "未知渠道";
		}

	}

	public static List<ClaimInfo> changeClaimsToClaimInfos(List<RecompenseForCC> claims){
		List<ClaimInfo> claimInfos=new ArrayList<ClaimInfo>();
		for(RecompenseForCC claim:claims){
			ClaimInfo claimInfo=new ClaimInfo();
			claimInfo.setClaimNumber(claim.getWaybillNumber());
			claimInfo.setClaimType(claim.getRecompenseType());
			claimInfo.setClaimWay(claim.getRecompenseMethod());
			claimInfo.setMoney(claim.getRecompenseAmount());
			claimInfo.setReportDept(claim.getReportDept());
			claimInfo.setReportTime(claim.getReportDate());
			claimInfo.setStatus(claim.getStatus());
			claimInfos.add(claimInfo);
		}
		return claimInfos;
	}
	/**
	 * @作者：罗典
	 * @时间：2012-4-18
	 * @描述：将ERP传入状态转变为订单状态值
	 * */
	public static String getOrderStatus(int status) {
		if (status == 1) {
			return ORDER_SATUTS_SHOUTCAR;
		} else if (status == 2 || status == 3) {
			return ORDER_SATUTS_RECEIPTING;
		} else if (status == 5 || status == 6) {
			return ORDER_SATUTS_GOBACK;
		} else if (status == 7) {
			return ORDER_STATUS_GOT;
		} else if (status == 8) {
			return ORDER_STATUS_ACCEPT;
		} else if (status == 9) {
			return ORDER_STATUS_IN_TRANSIT;
		} else if (status == 11) {
			return ORDER_STATUS_SINGSUCCESS;
		} else if (status == 12) {
			return ORDER_STATUS_SIGNFAIL;
		} else {
			return "";
		}

	}

	/**
	 * @作者：罗典
	 * @时间：2012-4-18
	 * @描述：将ERP传入状态转变为订单状态值
	 * */
	public static String getOrderStatusDesc(String status) {
		if (status.equals(ORDER_STATUS_WAIT_ALLOT)) {
			return "待分配";
		} else if (status.equals(ORDER_STATUS_WAIT_ACCEPT)) {
			return "待受理";
		} else if (status.equals(ORDER_STATUS_ACCEPT)) {
			return "已受理";
		} else if (status.equals(ORDER_STATUS_GOT)) {
			return "已开单";
		} else if (status.equals(ORDER_STATUS_IN_TRANSIT)) {
			return "运输中";
		} else if (status.equals(ORDER_STATUS_SINGSUCCESS)) {
			return "签收成功";
		} else if (status.equals(ORDER_STATUS_SIGNFAIL)) {
			return "签收异常";
		} else if (status.equals(ORDER_SATUTS_SHOUTCAR)) {
			return "已约车";
		} else if (status.equals(ORDER_SATUTS_GOBACK)) {
			return "已退回";
		} else if (status.equals(ORDER_SATUTS_FAILGOT)) {
			return "揽货失败";
		} else if (status.equals(ORDER_SATUTS_RECEIPTING)) {
			return "接货中";
		} else {
			return "其它";
		}

	}


	public static PilotCity changeCityInfoToPilotCity(com.deppon.foss.express.CityInfo cityInfo){
		PilotCity pilotCity=new PilotCity();
		pilotCity.setId(cityInfo.getId());
		pilotCity.setCityCode(cityInfo.getCode());
		pilotCity.setCityName(cityInfo.getName());
		pilotCity.setCreateDate(new Date());
		pilotCity.setModifyDate(new Date());
		pilotCity.setHasAgent(cityInfo.getHasAgent());
		pilotCity.setIsPilot(cityInfo.getIsPilot());
		pilotCity.setStatus(isTrue(cityInfo.getActive()));
		return pilotCity;
	}
	/**
	 * @作者：罗典
	 * @描述：将OnlineApplyInfo转换为OnlineApply
	 * */
	public static OnlineApply changeOnlineApplyInfoToOnlineApply(
			OnlineApplyInfo onlineApplyInfo) {
		OnlineApply onlineApply = new OnlineApply();
		onlineApply.setApplyPart(onlineApplyInfo.getRecompensePerson());
		onlineApply.setCreateDate(onlineApplyInfo.getRecompenseDate());
		onlineApply.setModifyDate(onlineApplyInfo.getLastModifyTime());
		onlineApply.setWaybillNumber(onlineApplyInfo.getWaybillNumber());
		onlineApply.setCustomerNum(onlineApplyInfo.getCustomerNum());
		onlineApply.setCustomerId(onlineApplyInfo.getClaimant());
		onlineApply.setRecompenseAmount(onlineApplyInfo.getRecompenseAmount());
		onlineApply.setInsurAmount(onlineApplyInfo.getInsurAmount());
		onlineApply.setRecompenseReason(onlineApplyInfo.getRecompenseReason());
		onlineApply.setRejectReason(onlineApplyInfo.getRejectReason());
		onlineApply.setApplyTime(onlineApplyInfo.getApplyTime());
		onlineApply.setRejectTime(onlineApplyInfo.getRejectTime());
		onlineApply.setIdCard(onlineApplyInfo.getIdCard());
		onlineApply.setBankName(onlineApplyInfo.getBankName());
		onlineApply.setBranchName(onlineApplyInfo.getBranchName());
		onlineApply.setOpenName(onlineApplyInfo.getOpenName());
		onlineApply.setAccount(onlineApplyInfo.getAccount());
		onlineApply.setMobile(onlineApplyInfo.getMobile());
		onlineApply.setTelphone(onlineApplyInfo.getTelphone());
		onlineApply.setProvince(onlineApplyInfo.getProvince());
		onlineApply.setCity(onlineApplyInfo.getCity());
		onlineApply.setCounty(onlineApplyInfo.getCounty());
		onlineApply.setOnlineUser(onlineApplyInfo.getOnlineUser());
		onlineApply.setTransType(onlineApplyInfo.getTransType());
		onlineApply.setStartStation(onlineApplyInfo.getStartStation());
		onlineApply.setEndStation(onlineApplyInfo.getEndStation());
		onlineApply.setSendDate(onlineApplyInfo.getSendDate());
		onlineApply.setApplyDeptId(onlineApplyInfo.getApplyDeptId());
		onlineApply.setStatus(onlineApplyInfo.getStatus());
		onlineApply.setRecompenseId(onlineApplyInfo.getRecompenseId());
		onlineApply.setPartA(onlineApplyInfo.getPartA());
		onlineApply.setPartAsign(onlineApplyInfo.getPartAsign());
		onlineApply.setPartAsignDate(onlineApplyInfo.getPartAsignDate());
		onlineApply.setPartB(onlineApplyInfo.getPartB());
		onlineApply.setPartBAsign(onlineApplyInfo.getPartBAsign());
		onlineApply.setPartBAsignDate(onlineApplyInfo.getPartBAsignDate());
		onlineApply.setFrontImage(onlineApplyInfo.getOverPICsrc());
		onlineApply.setBackImage(onlineApplyInfo.getBackPICsrc());
		onlineApply.setBankCode(onlineApplyInfo.getBankCode());
		onlineApply.setBranchCode(onlineApplyInfo.getBranchCode());
		onlineApply.setProvinceCode(onlineApplyInfo.getProvinceCode());
		onlineApply.setCityCode(onlineApplyInfo.getCityCode());
		return onlineApply;
	}

	/**
	 * @作者：罗典
	 * @描述：将OnlineApplyInfo转换为OnlineApply
	 * */
	public static OnlineApplyInfo changeOnlineApplyToOnlineApplyInfo(
			OnlineApply onlineApply) {
		OnlineApplyInfo onlineApplyInfo = new OnlineApplyInfo();
		onlineApplyInfo.setRecompenseNumber(onlineApply.getRecompenseId());
		onlineApplyInfo.setClaimant(onlineApply.getCustomerId());
		onlineApplyInfo.setRecompenseDate(onlineApply.getCreateDate());
		onlineApplyInfo.setLastModifyTime(onlineApply.getModifyDate());
		onlineApplyInfo.setPartA(onlineApply.getPartA());
		onlineApplyInfo.setPartB(onlineApply.getPartB());
		onlineApplyInfo.setPartAsign(onlineApply.getPartAsign());
		onlineApplyInfo.setPartAsignDate(onlineApply.getPartAsignDate());
		onlineApplyInfo.setPartBAsign(onlineApply.getPartBAsign());
		onlineApplyInfo.setPartBAsignDate(onlineApply.getPartBAsignDate());
		onlineApplyInfo.setRecompenseId(onlineApply.getRecompenseId());
		onlineApplyInfo.setWaybillNumber(onlineApply.getWaybillNumber());
		onlineApplyInfo.setCustomerNum(onlineApply.getCustomerNum());
		onlineApplyInfo.setCustomerId(onlineApply.getCustomerId());
		onlineApplyInfo.setRecompenseAmount(onlineApply.getRecompenseAmount());
		onlineApplyInfo.setInsurAmount(onlineApply.getInsurAmount());
		onlineApplyInfo.setRecompenseReason(onlineApply.getRecompenseReason());
		onlineApplyInfo.setRejectReason(onlineApply.getRejectReason());
		onlineApplyInfo.setApplyTime(onlineApply.getApplyTime());
		onlineApplyInfo.setRejectTime(onlineApply.getRejectTime());
		onlineApplyInfo.setIdCard(onlineApply.getIdCard());
		onlineApplyInfo.setBankName(onlineApply.getBankName());
		onlineApplyInfo.setBranchName(onlineApply.getBranchName());
		onlineApplyInfo.setOpenName(onlineApply.getOpenName());
		onlineApplyInfo.setAccount(onlineApply.getAccount());
		onlineApplyInfo.setMobile(onlineApply.getMobile());
		onlineApplyInfo.setTelphone(onlineApply.getTelphone());
		onlineApplyInfo.setProvince(onlineApply.getProvince());
		onlineApplyInfo.setCity(onlineApply.getCity());
		onlineApplyInfo.setCounty(onlineApply.getCounty());
		onlineApplyInfo.setOnlineUser(onlineApply.getOnlineUser());
		onlineApplyInfo.setTransType(onlineApply.getTransType());
		onlineApplyInfo.setStartStation(onlineApply.getStartStation());
		onlineApplyInfo.setEndStation(onlineApply.getEndStation());
		onlineApplyInfo.setSendDate(onlineApply.getSendDate());
		onlineApplyInfo.setApplyDeptId(onlineApply.getApplyDeptId());
		onlineApplyInfo.setStatus(onlineApply.getStatus());
		onlineApplyInfo.setOverPICsrc(onlineApply.getFrontImage());
		onlineApplyInfo.setBackPICsrc(onlineApply.getBackImage());
		return onlineApplyInfo;
	}

	/**
	 * @作者：罗典
	 * @时间：2012-4-25
	 * @描述：将onlineApplyInfo转化成paymentInfo
	 * */
	public static PaymentInfo changeOnlineApplyInfoToPaymentInfo(
			OnlineApplyInfo onlineApplyInfo) {
		PaymentInfo paymentInfo = new PaymentInfo();
		// 申请人工号，申请人姓名
		// paymentInfo
		// .setApplyPersonNumber(onlineApplyInfo.getApplyPersonNumber());
		// paymentInfo.setPayee(onlineApplyInfo.getPayee());
		paymentInfo.setPayeeMobilePhone(onlineApplyInfo.getMobile());
		paymentInfo.setProvince(onlineApplyInfo.getProvince());
		paymentInfo.setCity(onlineApplyInfo.getCity());
		paymentInfo.setBank(onlineApplyInfo.getBankName());
		paymentInfo.setSubbranch(onlineApplyInfo.getBranchName());
		paymentInfo.setAccountNumber(onlineApplyInfo.getAccount());
		// 总金额
		// paymentInfo.setAmountMoney(onlineApplyInfo.getAmountMoney());
		// 支付方式
		// paymentInfo.setPayWay(onlineApplyInfo.getPayWay());
		paymentInfo.setWaybillNumber(onlineApplyInfo.getWaybillNumber());
		// 差错编号
		// paymentInfo.setErrorNumber(onlineApplyInfo.getErrorNumber());
		paymentInfo.setPartA(onlineApplyInfo.getPartA());
		paymentInfo.setPartB(onlineApplyInfo.getPartB());
		paymentInfo.setShipmentsDate(onlineApplyInfo.getSendDate());
		paymentInfo.setStartStation(onlineApplyInfo.getStartStation());
		paymentInfo.setDestination(onlineApplyInfo.getEndStation());
		paymentInfo.setRecompenseMoney(onlineApplyInfo.getRecompenseAmount());
		// 理赔金额大写
		// paymentInfo.setRecompenseMoneyText(onlineApplyInfo
		// .getRecompenseMoneyText());
		paymentInfo.setAccountName(onlineApplyInfo.getOpenName());
		paymentInfo.setPartAsign(onlineApplyInfo.getPartAsign());
		paymentInfo.setPartAsignDate(onlineApplyInfo.getPartAsignDate());
		paymentInfo.setPartBAsign(onlineApplyInfo.getPartBAsign());
		paymentInfo.setPartBAsignDate(onlineApplyInfo.getPartBAsignDate());
		paymentInfo.setIdentityCard(onlineApplyInfo.getIdCard());
		return paymentInfo;
	}

	/**
	 * @作者：罗典
	 * @时间：2012-4-25
	 * @描述：将waybillRecompense转换为RecompenseOnlineVerifyInfo
	 * */
	public static RecompenseOnlineVerifyInfo changewaybillToVerifyInfo(
			WaybillRecompenseInfo wr) {
		RecompenseOnlineVerifyInfo recompenseOnlineVerifyInfo = new RecompenseOnlineVerifyInfo();
		recompenseOnlineVerifyInfo
				.setShipperTelePhone(wr.getShipperTelePhone());
		recompenseOnlineVerifyInfo.setShipperMobilePhone(wr
				.getShipperMobilePhone());
		recompenseOnlineVerifyInfo
				.setReceiveTelephone(wr.getReceiveTelephone());
		recompenseOnlineVerifyInfo.setReceiveMobilePhone(wr
				.getReceiveMobilePhone());
		recompenseOnlineVerifyInfo.setReceiveDept(wr.getReceiveDept());
		recompenseOnlineVerifyInfo.setShipmentsDept(wr.getShipmentsDept());
		recompenseOnlineVerifyInfo.setInsurance(wr.getInsurance());
		recompenseOnlineVerifyInfo.setStartStation(wr.getStartStation());
		recompenseOnlineVerifyInfo.setDestinationStation(wr
				.getDestinationStation());
		recompenseOnlineVerifyInfo.setShipmentsTime(wr.getShipmentsTime());
		recompenseOnlineVerifyInfo.setTransportType(wr.getTransportType());
		return recompenseOnlineVerifyInfo;
	};

	/**
	 * @作者：罗典
	 * @时间：2012-12-25
	 * @描述：将BankInfo转换为AccountBank
	 */
	public static AccountBank convertToAccountBank(BankInfo bankInfo) {
		AccountBank accountBank = new AccountBank();
		accountBank.setLastUpdateTime(bankInfo.getLastUpdateTime());
		accountBank.setName(bankInfo.getBankName());
		accountBank.setCode(bankInfo.getBankId());
		accountBank.setCancel(1);
		accountBank.setLastModifyUserId(86301);
		accountBank.setCreateUserId(86301);
		if (bankInfo.isIsCancel()) {
			accountBank.setCancel(0);
		}
		return accountBank;
	}

	/**
	 * @作者：罗典
	 * @时间：2012-12-25
	 * @描述：将BankInfo转换为AccountBank
	 */
	public static AccountBranch convertToAccountBranch(BankInfo bankInfo) {
		AccountBranch accountBranch = new AccountBranch();
		accountBranch.setLastUpdateTime(bankInfo.getLastUpdateTime());
		accountBranch.setCode(bankInfo.getBankId());
		accountBranch.setName(bankInfo.getBankName());
		accountBranch.setCancel(1);
		accountBranch.setCreateUserId(86301);
		accountBranch.setLastModifyUserId(86301);
		if (bankInfo.isIsCancel()) {
			accountBranch.setCancel(0);
		}
		return accountBranch;
	}

	/**
	 * @作者：罗典
	 * @时间：2012-12-25
	 * @描述：将ProvinceCityInfo转换成BankProvince
	 */
	public static BankProvince convertToBankProvince(
			ProvinceCityInfo provinceCityInfo) {
		BankProvince bankProvince = new BankProvince();
		bankProvince.setCode(provinceCityInfo.getProvinceCityId());
		bankProvince.setName(provinceCityInfo.getProvinceCityName());
		bankProvince.setLastUpdateTime(provinceCityInfo.getLastUpdateTime());
		bankProvince.setCreateUserId("86301");
		bankProvince.setLastModifyUserId("86301");
		bankProvince.setCancel("1");
		if (provinceCityInfo.isIsCancel()) {
			bankProvince.setCancel("0");
		}
		return bankProvince;
	}

	/**
	 * @作者：罗典
	 * @时间：2012-12-25
	 * @描述：将ProvinceCityInfo转换成BankProvince
	 */
	public static BankCity convertToBankCity(
			ProvinceCityInfo provinceCityInfo) {
		BankCity bankCity = new BankCity();
		bankCity.setCode(provinceCityInfo.getProvinceCityId());
		bankCity.setName(provinceCityInfo.getProvinceCityName());
		bankCity.setLastUpdateTime(provinceCityInfo.getLastUpdateTime());
		bankCity.setCreateUserId("86301");
		bankCity.setLastModifyUserId("86301");
		bankCity.setCancel("1");
		if (provinceCityInfo.isIsCancel()) {
			bankCity.setCancel("0");
		}
		return bankCity;
	}
	/**
	 * @作者：罗典
	 * @描述：转化合同月结天数信息
	 * @时间：2013-01-21
	 * */
	public static ContractDebtDay convertToContractDebtDay(
			HasUsedAmountInfo info) {
		ContractDebtDay contractDebtDay = new ContractDebtDay();
		contractDebtDay.setCustNum(info.getCustNumber());
		contractDebtDay.setCustName(info.getCustName());
		contractDebtDay.setUsedAmount(validataBigDecimal(
				info.getHasUsedAmount()).doubleValue());
		contractDebtDay.setLongestDebtDate(info.getEarliestDebtDate());
		contractDebtDay.setCreateDate(new Date());
		contractDebtDay.setModifyDate(new Date());
		return contractDebtDay;
	}

	/**
	 * @作者：罗典
	 * @时间：2012-5-9
	 * @描述：字符串为空null校验
	 * */
	public static void checkNull(Object obj, String errorInfo)
			throws CommException {
		if (obj == null) {
			throw new CommException(errorInfo + " " + null);
		}
	}

	/**
	 * @作者：罗典
	 * @时间：2012-5-9
	 * @描述：字符串为空null校验
	 * */
	public static void validateStringNull(Object object, String errorCode)
			throws CrmBusinessException {
		if (object == null
				|| (object instanceof String && object.toString().equals(""))) {
			throw new CrmBusinessException(errorCode);
		}
	}

	/**
	 * @作者：罗典
	 * @时间：2012-7-29
	 * @描述：业务异常信息封装
	 * */
	public static void validateNull(Object object, String errorCode,
			Object printObj) throws CrmBusinessException {
		if (object == null) {
			if (printObj != null) {
				throw new CrmBusinessException(errorCode,
						JsonMapperUtil.writeValue(printObj));
			} else {
				throw new CrmBusinessException(errorCode);
			}
		}
	}

	/**
	 * @throws CommException
	 * @作者：吴根斌
	 * @时间：2014-4-8
	 * @描述：空null校验
	 * */
	public static void validateccNull(Object object, String errorMsg)
			throws com.deppon.crm.module.interfaces.cc.domain.CommException {
		if (object == null
				|| (object instanceof String && object.toString().equals(""))) {
			throw new com.deppon.crm.module.interfaces.cc.domain.CommException(
					errorMsg);
		}
	}

	/**
	 * @作者：罗典
	 * @时间：2012-5-9
	 * @描述：字符串为空null校验
	 * */
	public static void validateStringNull(Object object, String errorCode,
			Object printObj) throws CrmBusinessException {
		if (object == null
				|| (object instanceof String && object.toString().equals(""))) {
			if (printObj != null) {
				throw new CrmBusinessException(errorCode,
						JsonMapperUtil.writeValue(printObj));
			}
			throw new CrmBusinessException(errorCode);
		}
	}

	/**
	 * @作者：罗典
	 * @时间：2012-8-7
	 * @描述：将各模块i18n转换为汉字
	 * @参数： locale 传模块名，如order ，key为i18nkey
	 * */
	@SuppressWarnings("unchecked")
	public static String getMessage(String moudleName, String key) {
		Locale locale = Locale.CHINA;
		Object[] args = null;
		ICache<String, Properties> cache = CacheManager.getInstance().getCache(
				MessageCache.UUID);
		String moduleName = moudleName;// "order";
		Properties properties = cache.get(locale.toString());
		if (properties != null) {
			String value = properties.getProperty(key, key);
			if (!value.equals(key)) {
				// 格式化
				return (args == null || args.length == 0) ? value
						: MessageFormat.format(value, args);
			} else {
				properties = null;
			}
		}

		properties = cache.get(moduleName + "_" + locale.getLanguage());
		if (properties != null) {
			String value = properties.getProperty(key, key);
			if (!value.equals(key)) {
				// 格式化
				return (args == null || args.length == 0) ? value
						: MessageFormat.format(value, args);
			} else {
				properties = null;
			}
		}

		properties = cache.get(moduleName);
		if (properties != null) {
			String value = properties.getProperty(key, key);
			if (!value.equals(key)) {
				// 格式化
				return (args == null || args.length == 0) ? value
						: MessageFormat.format(value, args);
			} else {
				properties = null;
			}
		}

		// 当前模块下没有国际化信息则从框架扩展模块获取
		properties = cache.get("frameworkimpl" + "_" + locale);
		if (properties != null) {
			String value = properties.getProperty(key, key);
			if (!value.equals(key)) {
				// 格式化
				return (args == null || args.length == 0) ? value
						: MessageFormat.format(value, args);
			} else {
				properties = null;
			}
		}

		properties = cache.get("frameworkimpl" + "_" + locale.getLanguage());
		if (properties != null) {
			String value = properties.getProperty(key, key);
			if (!value.equals(key)) {
				// 格式化
				return (args == null || args.length == 0) ? value
						: MessageFormat.format(value, args);
			} else {
				properties = null;
			}
		}

		properties = cache.get("frameworkimpl");
		if (properties != null) {
			String value = properties.getProperty(key, key);
			if (!value.equals(key)) {
				// 格式化
				return (args == null || args.length == 0) ? value
						: MessageFormat.format(value, args);
			} else {
				properties = null;
			}
		}

		// 没有国际化信息返回key
		return key;


	}

	/**
	 * @作者：罗典
	 * @描述：将查询订单列表转化为orderInfo
	 * @时间：2012-11-10
	 * */
	public static OrderInfo convertToOrderInfo(Order order) {
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setOrderStatus(order.getOrderStatus());
		orderInfo.setResource(order.getResource());
		/*
		 * 转换诚信通阿里巴巴订单类型为ALIBABACXT
		 * */
		if(com.deppon.crm.module.order.server.util.Constant.ORDER_ALIBABA_CXT
				.equals(order.getMemberType())&&
				com.deppon.crm.module.order.server.util.Constant.ORDER_SOURCE_ALIBABA
				.equals(order.getResource())){
			orderInfo.setResource(order.getResource()+order.getMemberType());//ALIBABA+CXT
		}
		orderInfo.setOrderNumber(order.getOrderNumber());
		orderInfo.setShipperName(order.getShipperName());
		orderInfo.setContactName(order.getContactName());
		orderInfo.setContactMobile(order.getContactMobile());
		orderInfo.setContactPhone(order.getContactPhone());
		orderInfo.setContactAddress(order.getContactAddress());
		orderInfo.setGoodsName(order.getGoodsName());
		orderInfo.setTransportMode(order.getTransportMode());
		orderInfo.setStartStation(order.getStartStation());
		orderInfo.setLadingStation(order.getReceivingToPoint());
		orderInfo.setLadingStationName(order.getReceivingToPointName());
		orderInfo.setWaybillNumber(order.getWaybillNumber());
		return orderInfo;
	}

	/**
	 * @作者：罗典
	 * @描述：将查询订单列表转化为orderInfo
	 * @时间：2012-11-10
	 * */
	public static com.deppon.crm.module.interfaces.foss.domain.Order convertToOrder(
			Order crmOrder) {
		com.deppon.crm.module.interfaces.foss.domain.Order order = new com.deppon.crm.module.interfaces.foss.domain.Order();
		order.setHastenCount(validataInteger(crmOrder.getHastenCount()));
		order.setLastHastenTime(crmOrder.getLastHastenTime());
		order.setContactManId(crmOrder.getContactManId());
		order.setOrderNumber(crmOrder.getOrderNumber());
		order.setChannelNumber(crmOrder.getChannelNumber());
		order.setShipperId(crmOrder.getShipperId());
		order.setShipperNumber(crmOrder.getShipperNumber());
		order.setShipperName(crmOrder.getShipperName());
		order.setContactName(crmOrder.getContactName());
		order.setContactMobile(crmOrder.getContactMobile());
		order.setContactPhone(crmOrder.getContactPhone());
		order.setContactProvince(crmOrder.getContactProvince());
		order.setContactCity(crmOrder.getContactCity());
		order.setContactArea(crmOrder.getContactArea());
		order.setContactAddress(crmOrder.getContactAddress());
		order.setIsReceiveGoods(validataBoolean(crmOrder.getIsReceiveGoods()));
		order.setBeginAcceptTime(crmOrder.getBeginAcceptTime());
		order.setEndAcceptTime(crmOrder.getEndAcceptTime());
		order.setReceiverCustId(crmOrder.getReceiverCustId());
		order.setReceiverCustNumber(crmOrder.getReceiverCustNumber());
		order.setReceiverCustcompany(crmOrder.getReceiverCustcompany());
		order.setReceiverCustName(crmOrder.getReceiverCustName());
		order.setReceiverCustMobile(crmOrder.getReceiverCustMobile());
		order.setReceiverCustPhone(crmOrder.getReceiverCustPhone());
		order.setReceiverCustProvince(crmOrder.getReceiverCustProvince());
		order.setReceiverCustCity(crmOrder.getReceiverCustCity());
		order.setReceiverCustArea(crmOrder.getReceiverCustArea());
		order.setReceiverCustAddress(crmOrder.getReceiverCustAddress());
		order.setIsSendmessage(validataBoolean(crmOrder.getIsSendmessage()));
		order.setReceivingToPoint(crmOrder.getReceivingToPoint());
		order.setReceivingToPointName(crmOrder.getReceivingToPointName());
		order.setTransportMode(crmOrder.getTransportMode());
		order.setGoodsName(crmOrder.getGoodsName());
		order.setPacking(crmOrder.getPacking());
		order.setGoodsType(crmOrder.getGoodsType());
		order.setGoodsNumber(validataInteger(crmOrder.getGoodsNumber()));
		order.setTotalWeight(validataDouble(crmOrder.getTotalWeight()));
		order.setTotalVolume(validataDouble(crmOrder.getTotalVolume()));
		order.setPaymentType(crmOrder.getPaymentType());
		order.setChannelType(crmOrder.getChannelType());
		order.setChannelCustId(crmOrder.getChannelCustId());
		order.setDeliveryMode(crmOrder.getDeliveryMode());
		order.setReciveLoanType(crmOrder.getReciveLoanType());
		order.setReviceMoneyAmount(new BigDecimal(validataDouble(crmOrder
				.getReviceMoneyAmount())));
		order.setInsuredAmount(BigDecimal.valueOf(validataDouble(crmOrder
				.getInsuredAmount())));
		order.setOrderTime(crmOrder.getOrderTime());
		order.setStartStation(crmOrder.getStartStation());
		order.setStartStationName(crmOrder.getStartStationName());
		order.setAcceptDept(crmOrder.getAcceptDept());
		order.setAcceptDeptName(crmOrder.getAcceptDeptName());
		order.setOrderStatus(crmOrder.getOrderStatus());
		order.setDealPerson(crmOrder.getDealPerson());
		order.setOrderAcceptTime(crmOrder.getOrderAcceptTime());
		order.setReceiver(crmOrder.getReceiver());
		order.setAccepterPersonMobile(crmOrder.getAccepterPersonMobile());
		order.setWaybillNumber(crmOrder.getWaybillNumber());
		order.setResource(crmOrder.getResource());
		order.setCouponNumber(crmOrder.getCouponNumber());
		/*
		 * 转换诚信通阿里巴巴订单类型为ALIBABACXT
		 * */
		if(com.deppon.crm.module.order.server.util.Constant.ORDER_ALIBABA_CXT
				.equals(crmOrder.getMemberType())&&
				com.deppon.crm.module.order.server.util.Constant.ORDER_SOURCE_ALIBABA
				.equals(crmOrder.getResource())){
			order.setResource(crmOrder.getResource()+crmOrder.getMemberType());//ALIBABA+CXT
		}
		order.setTransNote(crmOrder.getTransNote());
		order.setReturnBillType(crmOrder.getReturnBillType());
		order.setOrderPerson(crmOrder.getOrderPerson());
		order.setFeedbackInfo(crmOrder.getFeedbackInfo());
		order.setOnlineName(crmOrder.getOnlineName());
		order.setMemberType(crmOrder.getMemberType());
		return order;
	}

	/**
	 * @作者：罗典
	 * @描述：将FOSS优惠券信息转换为优惠券识别的优惠券信息
	 * @时间：2012-11-10
	 * */

	public static WaybillInfo convertToWaybillInfo(
			CouponWaybillInfo couponWaybillInfo) {
		WaybillInfo waybillInfo = new WaybillInfo();
		if (couponWaybillInfo.getWaybillNumber() == null) {
			return waybillInfo;
		}
		waybillInfo.setWaybillNumber(couponWaybillInfo.getWaybillNumber());
		waybillInfo.setOrderNumber(couponWaybillInfo.getOrderNumber());
		waybillInfo.setOrderSource(couponWaybillInfo.getOrderSource());
		waybillInfo.setProduceType(couponWaybillInfo.getProduceType());
		waybillInfo.setTotalAmount(couponWaybillInfo.getTotalAmount());
		waybillInfo.setLeaveMobile(couponWaybillInfo.getLeaveMobile());
		waybillInfo.setLeavePhone(couponWaybillInfo.getLeavePhone());
		waybillInfo.setCustNumber(couponWaybillInfo.getCustNumber());
		waybillInfo.setLeaveDept(couponWaybillInfo.getLeaveDept());
		waybillInfo.setArrivedDept(couponWaybillInfo.getArrivedDept());
		waybillInfo.setLeaveOutDept(couponWaybillInfo.getLeaveOutDept());
		waybillInfo.setArrivedOutDept(couponWaybillInfo.getArrivedOutDept());
		List<AmountInfo> amountList = couponWaybillInfo.getAmountList();
		List<com.deppon.crm.module.coupon.shared.domain.AmountInfo> infoLists = new ArrayList<com.deppon.crm.module.coupon.shared.domain.AmountInfo>();
		for (AmountInfo amountInfo : amountList) {
			com.deppon.crm.module.coupon.shared.domain.AmountInfo info = new com.deppon.crm.module.coupon.shared.domain.AmountInfo();
			info.setValuationAmonut(amountInfo.getValuationAmonut());
			info.setValuationCode(amountInfo.getValuationCode());
			infoLists.add(info);
		}
		waybillInfo.setAmountList(infoLists);
		return waybillInfo;
	}

	/**
	 * @作者：吴根斌
	 * @描述：把GetRecompenseByWayBill转换为QueryClaimbillResponse
	 * @时间：2013-4-23
	 * */
	public static QueryClaimbillResponse convertToClaimbillResponse(
			GetRecompenseByWayBill claim) {
        QueryClaimbillResponse response=new QueryClaimbillResponse();
        response.setCustNum(claim.getCustNumber());
        response.setCustName(claim.getCustName());
        response.setWaybillNum(claim.getWaybillNumber());
        response.setRiskType(claim.getInsurType());
        response.setRiskTime(claim.getInsurDate());
        response.setRiskInfo(claim.getIssueDesc());
        response.setReportCaseTime(claim.getReportDate());
        response.setClaimSum(validataBigDecimal(new BigDecimal(validataDouble(claim.getRecompenseAmount()))));
        response.setActualPaySum(validataBigDecimal(new BigDecimal(validataDouble(claim.getRealAmount()))));
        response.setReceivingDeptName(claim.getReceiveDept());
        response.setStatus(claim.getStatus());
        response.setDealingPeople(claim.getHandledMan());
        response.setDealingTime(claim.getHandledTime());
        response.setFinalApproval(claim.getLastApprovedMan());
        response.setFinalApprovalOpinion(claim.getLastApprovedOpinion());
        response.setIndeptCharge(claim.getDeptCharge());
        response.setInCompanyCharge(new BigDecimal(validataStringDouble(claim.getInCompanyCharge())));
		response.setOtherCharge(new BigDecimal(validataStringDouble(claim.getOtherCharge())));
		return response;
	}

	/**
	 * @作者：罗典
	 * @描述：根据组织request转换BussinessDept
	 * @时间：2013-2-2
	 * */
	public static BussinessDept convertToBussinessDept(
			OrganizationInfo organizationInfo) {
		BussinessDept bussinessDept = new BussinessDept();
		// bussinessDept.setId("id");
		bussinessDept.setCreateDate(new Date());
		bussinessDept.setCreateUser("86301");
		bussinessDept.setModifyDate(new Date());
		bussinessDept.setModifyUser("86301");
		bussinessDept.setDeptName(organizationInfo.getOrgName());
		bussinessDept.setDeptCode(organizationInfo.getUnifiedCode());
		bussinessDept.setIfHashGoodsType(false);
		bussinessDept.setDeptAddress(organizationInfo.getAddress());
		bussinessDept.setDeptContext(organizationInfo.getDeptDesc());
		bussinessDept.setContactMethod(organizationInfo.getDepTelephone());
		bussinessDept
				.setIfOutField(isTrue(organizationInfo.getTransferCenter()));
		bussinessDept.setIfTransit(false);
		// bussinessDept.setIfEnable(false);
		// bussinessDept.setBelongArea(Department);
		bussinessDept.setIfOpen(isTrue(organizationInfo.getActive()));
		// bussinessDept.setOrganizeId(Integer.valueOf("1"));
		bussinessDept.setIfArrive(false);
		bussinessDept.setIfLeave(false);
		bussinessDept.setIfHomeDelivery(false);
		bussinessDept.setIfSelfDelivery(false);
		bussinessDept.setIfOutward(false);
		bussinessDept.setIfVehicleTeam(isTrue(organizationInfo
				.getTransDepartment()));
		bussinessDept.setIfHavePDA(false);
		bussinessDept.setErpId(organizationInfo.getId());
		bussinessDept.setIsExpressRegion(isTrue(organizationInfo.getIsExpressRegion()));
		bussinessDept.setIfDivision(isTrue(organizationInfo.getDivision()));
		bussinessDept.setIfBigRegion(isTrue(organizationInfo.getBigRegion()));
		bussinessDept.setIfSmallRegion(isTrue(organizationInfo.getSmallRegion()));
		bussinessDept.setDepCoordinate(organizationInfo.getDepCoordinate());
		return bussinessDept;
	}

	/**
	 * @作者：罗典
	 * @描述：转换省份信息
	 * @时间：2013-2-2
	 * */
	public static Province convertToProvince(DistrictInfo districtInfo) {
		Province province = new Province();
		// province.setId("id");
		province.setCreateDate(new Date());
		province.setCreateUser("86301");
		province.setModifyDate(new Date());
		province.setModifyUser("86301");
		// province.setFid(new BigDecimal(1));
		// province.setProvinceID(new BigDecimal(1));
		province.setName(districtInfo.getName());
		province.setNumber(districtInfo.getCode());
		province.setPinyin(districtInfo.getPinyin());
		province.setPyjm(districtInfo.getPinyinAbbr());
		province.setStatus(isTrue(districtInfo.getActive()) == true ? "1" : "0");
		province.setLastModifyName("FOSS");
		return province;
	}

	/**
	 * @作者：罗典
	 * @描述：转换城市信息
	 * @时间：2013-2-2
	 * */
	public static City convertToCity(DistrictInfo districtInfo) {
		City city = new City();
		// city.setId("id");
		city.setCreateDate(new Date());
		city.setCreateUser("86301");
		city.setModifyDate(new Date());
		city.setModifyUser("86301");
		// city.setFid(new BigDecimal(1));
		// city.setCityID(new BigDecimal(1));
		city.setName(districtInfo.getName());
		city.setNumber(districtInfo.getCode());
		city.setPinyin(districtInfo.getPinyin());
		city.setPyjm(districtInfo.getPinyinAbbr());
		city.setStatus(isTrue(districtInfo.getActive()) == true ? "1" : "0");
		// city.setCityNumber("");
		// city.setIsDirCity(Integer.valueOf("1"));
		// city.setProvince(Province);
		// city.setProvinceId(Integer.valueOf("1"));
		// city.setProvinceName("provinceName");
		city.setLastModifyName("86301");
		return city;
	}

	/**
	 * @作者：罗典
	 * @描述：转换区域信息
	 * @时间：2013-2-2
	 * */
	public static Area convertToArea(DistrictInfo districtInfo) {
		Area area = new Area();
		// area.setId("id");
		area.setCreateDate(new Date());
		area.setCreateUser("86301");
		area.setModifyDate(new Date());
		area.setModifyUser("86301");
		// area.setFid(new BigDecimal(1));
		// area.setAreaID(new BigDecimal(1));
		// area.setIsCityLevel("isCityLevel");
		area.setLastModifyName("86301");
		// area.setCityName("cityName");
		// area.setCityId(Integer.valueOf("1"));
		// area.setProvinceId(Integer.valueOf("1"));
		area.setName(districtInfo.getName());
		area.setNumber(districtInfo.getCode());
		area.setStatus(isTrue(districtInfo.getActive()) == true ? "1" : "0");
		// area.setCity(City);
		return area;
	}
	/**
	 * @作者：王明明
	 * @描述：转换理赔工单
	 * @时间：2013-7-10
	 * */
	public static Complaint converToComplaint(ComplaintOrderBindRequest complaintOrder){
		Complaint complaint = new Complaint ();

		complaint.setSendorreceive(complaintOrder.getSenderOrConsignee());

		complaint.setCompman(complaintOrder.getName());//来电人
		complaint.setTel(complaintOrder.getPhone());
		complaint.setBill(complaintOrder.getBillnumber());
		complaint.setReportcontent(complaintOrder.getContent());
		complaint.setCustrequire(complaintOrder.getClientRequest());

		return  complaint;
	}


	/**
	 * @作者：罗典
	 * @描述：过滤BigDecimal为空
	 * @时间：2012-11-6
	 * */
	public static BigDecimal validataBigDecimal(BigDecimal bigDecimal) {
		if (bigDecimal == null) {
			return new BigDecimal(0);
		}
		return bigDecimal;
	}

	/**
	 * @作者：吴根斌
	 * @描述：过滤String为空
	 * @时间：2013-04-23
	 * */
	public static String validataStringDouble(String doubble) {
		if (doubble == ""||doubble==null) {
			return new String("0");
		}
		return doubble;
	}
	/**
	 * @作者：罗典
	 * @描述：过滤Double为空
	 * @时间：2012-11-6
	 * */
	public static Double validataDouble(Double doubble) {
		if (doubble == null) {
			return new Double(0);
		}
		return doubble.doubleValue();
	}

	/**
	 * @作者：罗典
	 * @描述：过滤Boolean为空
	 * @时间：2012-11-6
	 * */
	public static Boolean validataBoolean(Boolean booleann) {
		if (booleann == null) {
			return false;
		}
		return booleann.booleanValue();
	}

	/**
	 * @作者：罗典
	 * @描述：过滤Integer为空
	 * @时间：2012-11-6
	 * */
	public static Integer validataInteger(Integer integer) {
		if (integer == null) {
			return new Integer(0);
		}
		return integer.intValue();
	}

	/**
	 * @作者：罗典
	 * @描述：判断FOSS是否
	 * @时间：2013-02-01
	 * */
	public static boolean isTrue(String flag) {
		if ("Y".equals(flag)) {
			return true;
		}
		return false;
	}


	/**
	 * @作者：WMM
	 * @描述: 转换接口优惠对象为新建优惠券对象
	 * @时间: 2013-09-06
	 * */
  public static CouponForInterface convertToCouponForInterface(CreateNewCouponRequest request){

	  CouponForInterface coupon = new CouponForInterface();
	  coupon.setDeptStandardCode(request.getDeptCode());//部门标杆编码
	  coupon.setActivityType(request.getEventType());//活动类型
	  coupon.setBeginTime(request.getEffectiveTime());//优惠券生效时间
	  coupon.setEndTime(request.getLoseEffectivenessTime());//优惠券失效时间
	  coupon.setCouponValue(request.getCouponMoney());	//优惠券金额

	  coupon.setCostMode(request.getAmountDeductibleType());	//金额抵扣类型    默认为“2” 分级抵扣模式，“1”严格抵扣模式
	  coupon.setCostType(request.getAmountType());//金额类型      默认为“1” 运费，“2”开单金额
	  coupon.setValue(request.getFullAmountMinus());//满减金额
	  coupon.setDiscount(request.getGradingDeductions());	// 分级抵扣金额
	  coupon.setCostAddedType(request.getFeeType());//增值费类型	包装、保价、代收、送货、接货费

	  coupon.setCostAdded(request.getFeeAmountRequired());//增值费金额
	  coupon.setProductType(request.getProductCode());//产品类型
	  coupon.setOrderType(request.getOrderCode());	//订单来源
	  coupon.setCustLevel(request.getLevelCode());//客户等级
	  coupon.setCustTrade(request.getTradeCode());//客户行业

	  coupon.setRegdemand(request.getLineAeaRequirements());	//线路区域要求 	(1、2、3、4)空、走货线路、发货区域、到达区域


	  List<GoodsLine> goodsLines = new ArrayList<GoodsLine>();
	  GoodsLine goodsLine ;
	  for (TakeGoodsArea goodsArea : request.getTakeGoodsArea()) {
		  goodsLine = new GoodsLine();
		  goodsLine.setBeginDeptOrCityId(goodsArea.getDeliveryCode());
		  goodsLine.setBeginDeptOrCityName(goodsArea.getDeliveryName());
		  goodsLine.setEndDeptOrCityId(goodsArea.getReceivedCode());
		  goodsLine.setEndDeptOrCityName(goodsArea.getReceivedName());
		  goodsLines.add(goodsLine);
	}
	  coupon.setGoodsLines(goodsLines);//短信内容

	  coupon.setSmsContent(request.getSmsContent());//短信内容
	  coupon.setDescribe(request.getCouponDesc());	// 优惠券描述

	  return coupon;
  }


}
