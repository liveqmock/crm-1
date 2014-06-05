package com.deppon.crm.module.interfaces.esb;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.namespace.QName;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jms.core.JmsTemplate;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.common.util.Constant;
import com.deppon.crm.module.client.common.util.XmlJaxbMapper;
import com.deppon.crm.module.client.esb.domain.ESBClientHeader;
import com.deppon.crm.module.client.esb.trans.CrmToEsbRequestSender;
import com.deppon.crm.module.client.esb.trans.EsbUtil;
import com.deppon.crm.module.client.sync.domain.OrderRightInfo;
import com.deppon.crm.module.client.sync.domain.OrderRightRequest;
import com.deppon.crm.module.client.sync.domain.SiteReceiveRequest;
import com.deppon.crm.module.interfaces.foss.domain.HasUsedAmountInfo;
import com.deppon.crm.module.interfaces.foss.domain.ReceiveCreditUsedRequest;
import com.deppon.crm.module.interfaces.foss.domain.ReturnVoucherPaymentResultRequest;
import com.deppon.crm.module.interfaces.foss.domain.UpdateOrderRequest;
import com.deppon.crm.module.interfaces.foss.domain.domainInfo.MotorcadeInfo;
import com.deppon.crm.module.interfaces.foss.domain.domainInfo.SyncMotorcadeRequest;
import com.deppon.crm.module.interfaces.foss.domain.scatter.CreateScatterRequest;
import com.deppon.crm.module.interfaces.foss.domain.scatter.ScatterInfo;
import com.deppon.crm.module.interfaces.foss.domain.sync.CityInfo;
import com.deppon.crm.module.interfaces.foss.domain.sync.DepartmentInfo;
import com.deppon.crm.module.interfaces.foss.domain.sync.DistrictInfo;
import com.deppon.crm.module.interfaces.foss.domain.sync.OrganizationInfo;
import com.deppon.crm.module.interfaces.foss.domain.sync.SyncDistrictRequest;
import com.deppon.crm.module.interfaces.foss.domain.sync.SyncOrganizationRequest;
import com.deppon.crm.module.interfaces.foss.domain.sync.SyncSalesDepartmentRequest;
import com.deppon.crm.module.interfaces.ows.jmsdomain.CreateOwsCustomerRequest;
import com.deppon.crm.module.interfaces.uums.jms.PositionInfo;
import com.deppon.crm.module.interfaces.uums.jms.SendPositionRequest;
import com.ibm.mq.jms.JMSC;
import com.ibm.mq.jms.MQQueueConnectionFactory;

public class JmsTest {
	CrmToEsbRequestSender sender = new CrmToEsbRequestSender();
	private JmsTemplate template = new JmsTemplate();
	@SuppressWarnings("deprecation")
	@Before
	public void init() throws Exception {
		MQQueueConnectionFactory fac = new MQQueueConnectionFactory();
		fac.setTransportType(JMSC.MQJMS_TP_CLIENT_MQ_TCPIP);
		fac.setQueueManager("QM_APPT");
//		fac.setHostName("192.168.67.12");
//		fac.setPort(1428);
		fac.setHostName("192.168.17.141");//开发
		fac.setPort(3428);//开发
		fac.setChannel("CRM.CLIENT");
		template.setConnectionFactory(fac);
		sender.setTemplate(template);
		sender.setqName("QU_CRM_REQUEST_COM_IN");
	/*	ApplicationContext context = (ApplicationContext) new ClassPathXmlApplicationContext(
				"com/deppon/crm/module/interfaces/server/META-INF/spring_jms.xml");
		sender = (CrmToEsbRequestSender) context
				.getBean("crmToEsbInfoSender");*/
	}

	@Test
	public void syncExpressCityTest() throws CrmBusinessException{
		QName _QNAME = new QName(
				"http://www.deppon.com/ows/inteface/domain/",
				"syncExpressCityRequest");
		com.deppon.foss.express.SyncExpressCityRequest request=new com.deppon.foss.express.SyncExpressCityRequest();
		com.deppon.foss.express.CityInfo cityInfo= new com.deppon.foss.express.CityInfo();
		cityInfo.setId("135cb9e1-628c-4930-bb0a-7a28bbfb5f25");
		cityInfo.setActive("N");
		cityInfo.setName("天水市");
		cityInfo.setCode("620500");
		cityInfo.setIsPilot("Y");
		cityInfo.setHasAgent("N");
		cityInfo.setCreateTime(new Date(1377828099021l));
		cityInfo.setModifyTime(new Date(1377828525546l));
		cityInfo.setCreateUserCode("092444");
		cityInfo.setModifyUserCode("092444");
		cityInfo.setVersionNo(1377828525546l);
		request.getCitys().add(cityInfo);
		ESBClientHeader esbHeader = EsbUtil.createAsyncOutHeader(Constant.SYNC_EXPRESS_CITY,"1234");
		esbHeader.setBackServiceCode(Constant.SYNC_EXPRESS_CITY);
		esbHeader.setSourceSystem("FOSS");
		String ss = XmlJaxbMapper.writeValue(request, _QNAME);
		for (int i = 0; i < 6; i++) {
			sender.send(ss, esbHeader);
		}


	}

	/**
	 *
	 * <p>
	 * Description:官网客户信息同步<br />
	 * </p>
	 * @author 105681
	 * @version 0.1 2014-4-21
	 * @throws CrmBusinessException
	 * void
	 */
	@Test
	public void createOwsCustomerTest() throws CrmBusinessException {
		QName _CreateOwsCustomerRequest_QNAME = new QName(
				"http://www.deppon.com/crm/remote/ows/domain/entity",
				"createOwsCustomerRequest");
		CreateOwsCustomerRequest request = new CreateOwsCustomerRequest();
		request.setUserName("sj187649874681");
		request.setMobilephone("18764987468");
		request.setTelephone("031-45621301");
		request.setLinkmanName("er789");
		request.setProvinceCode("330000");
		request.setCityCode("310000-1");
		request.setAreaCode("310120");
		request.setProvinceName("上海");
		request.setCityName("上海市");
		request.setAreaName("奉贤区");
		request.setDetailAddress("鹅鹅鹅鹅鹅鹅饿");
		request.setEmail("zhangjianjun@deppon.com");
		request.setMobilephoneBU("15698541236");
		//request.setTelephoneBU("021-55121415");
		//request.setLinkmanNameBU("邓夫伟");
		request.setOperateType("U");
		ESBClientHeader esbHeader = EsbUtil.createAsyncOutHeader(
				"ESB_OWS2ESB_SEND_CUSTOMER", "15112");
		esbHeader.setBackServiceCode("CRM_ESB2CRM_SEND_CUSTOMER");
		esbHeader.setSourceSystem("OWS");
		String ss = XmlJaxbMapper.writeValue(request,
				_CreateOwsCustomerRequest_QNAME);
		sender.send(ss, esbHeader);
	}

	/**
	 *
	 * <p>
	 * Description:FOSS散客创建测试方法<br />
	 * </p>
	 * @author 105681
	 * @version 0.1 2014-4-21
	 * @throws CrmBusinessException
	 * void
	 */
	@Test
	public void createScatterTest() throws CrmBusinessException{
		QName _CreateScatterRequest_QNAME = new QName("http://www.deppon.com/esb/inteface/domain/crm", "createScatterRequest");
		CreateScatterRequest request=new CreateScatterRequest();
		ScatterInfo scatterInfo=new ScatterInfo();
		scatterInfo.setAreaCode("310118");
		scatterInfo.setBusinessType("LTT");
		scatterInfo.setCityCode("310000-1");
		scatterInfo.setCreateTime(new Date());
		scatterInfo.setCreateUser("092444");
		scatterInfo.setCustAttribute("LEAVE_CUSTOMER");
		scatterInfo.setCustName("张三李四");
		scatterInfo.setCustNumber("F2014042366870637");
		scatterInfo.setFossId("fossid");
		scatterInfo.setLinkmanAdress("上海市青浦区徐泾镇明珠路1018号");
		scatterInfo.setLinkmanName("zhangsan");
		scatterInfo.setMobilephone("13524061565");
		scatterInfo.setProvinceCode("310000");
		scatterInfo.setStandardCode("DP01430");
		scatterInfo.setTelephone("021-31350700");
		request.getScatterInfos().add(scatterInfo);
		ESBClientHeader esbHeader = EsbUtil.createAsyncOutHeader(Constant.SCATTER_CREATE,"15112");
		esbHeader.setBackServiceCode(Constant.SCATTER_CREATE);
		esbHeader.setSourceSystem("FOSS");
		String ss = XmlJaxbMapper.writeValue(request, _CreateScatterRequest_QNAME);
		sender.send(ss, esbHeader);
	}

	/**
	 * @作者：罗典
	 * @描述：订单状态更新接口
	 * @时间：2013-01-08
	 * */
	@Test
	public void updateOrderSatatusTest() throws CrmBusinessException  {
		QName _QNAME = new QName(
				"http://www.deppon.com/crm/inteface/foss/domain",
				"updateOrderRequest");
		UpdateOrderRequest updateOrderRequest = new UpdateOrderRequest();
		updateOrderRequest.setOrderNumber("AL01013297417");
		updateOrderRequest.setWaybillNumber("12345678");
		updateOrderRequest.setOprateUserNum("075586");
		updateOrderRequest.setOprateDeptCode("DP00505");
		updateOrderRequest.setDriverName("罗典");
		updateOrderRequest.setDriverMobile("13524074103");
		updateOrderRequest.setGoodsStatus("IN_TRANSIT");
		updateOrderRequest.setBackInfo("无反馈信息");
		ESBClientHeader esbHeader = EsbUtil.createAsyncOutHeader(Constant.MODIFYORDER,updateOrderRequest.getBackInfo());
		esbHeader.setBackServiceCode(Constant.MODIFYORDER);
		esbHeader.setSourceSystem("FOSS");
		String ss = XmlJaxbMapper.writeValue(updateOrderRequest, _QNAME);
		for (int i = 0; i < 7; i++) {
			sender.send(ss, esbHeader);
		}

	}

	/**
	 * @作者：罗典
	 * @描述：通知理赔支付状态接口
	 * @时间：2013-01-08
	 * */
	@Test
	public void ReturnVoucherPaymentResultRequestTest() throws CrmBusinessException  {
		QName _QNAME = new QName(
				"http://www.deppon.com/crm/inteface/foss/domain",
				"returnVoucherPaymentResultRequest");
		ReturnVoucherPaymentResultRequest returnVoucherPaymentResultRequest = new ReturnVoucherPaymentResultRequest();
		returnVoucherPaymentResultRequest.setDeptCode("deptCode");
		returnVoucherPaymentResultRequest.setAmount(new BigDecimal(1));
		returnVoucherPaymentResultRequest.setCustCode("custCode");
		returnVoucherPaymentResultRequest.setPaymentType("paymentType");
		returnVoucherPaymentResultRequest.setWaybillNum("59352997");
		returnVoucherPaymentResultRequest.setPayResult(false);
		returnVoucherPaymentResultRequest.setReason("reason");
		ESBClientHeader esbHeader = EsbUtil.createAsyncOutHeader(Constant.NOTIFY_CLAIMS_STATE,returnVoucherPaymentResultRequest.getWaybillNum());
		esbHeader.setBackServiceCode(Constant.NOTIFY_CLAIMS_STATE);
		String ss = XmlJaxbMapper.writeValue(returnVoucherPaymentResultRequest, _QNAME);
		esbHeader.setSourceSystem("FOSS");
		for (int i = 0; i <6; i++) {
			sender.send(ss, esbHeader);
		}
	}

	/**
	 * @作者：罗典
	 * @描述：网点同步接口(单向)
	 * @时间：2013-01-08
	 * */
	@Test
	public void receiveSiteSyncTest() throws CrmBusinessException  {
		QName _QNAME = new QName(
				"http://www.deppon.com/crm/inteface/foss/domain",
				"siteReceiveRequest");
		SiteReceiveRequest siteReceiveRequest = new SiteReceiveRequest();
		siteReceiveRequest.setDeptName("deptName");
		siteReceiveRequest.setDeptCode("deptCode");
		siteReceiveRequest.setDeptProvince("deptProvince");
		siteReceiveRequest.setDeptCity("deptCity");
		siteReceiveRequest.setIsAB(true);
		siteReceiveRequest.setDeptArea("deptArea");
		siteReceiveRequest.setDeptAddress("deptAddress");
		siteReceiveRequest.setDescript("descript");
		siteReceiveRequest.setContactWay("contactWay");
		siteReceiveRequest.setLeaveOutDept("leaveOutDept");
		siteReceiveRequest.setLeaveMiddleChange("leaveMiddleChange");
		siteReceiveRequest.setIsUsed(true);
		siteReceiveRequest.setSuperiorArea("superiorArea");
		siteReceiveRequest.setIsOpen(true);
		siteReceiveRequest.setOrganisationId("organisationId");
		siteReceiveRequest.setIsArrived(true);
		siteReceiveRequest.setIsLeave(true);
		siteReceiveRequest.setIsSendToHome(true);
		siteReceiveRequest.setIsGetBySelf(true);
		siteReceiveRequest.setIsOutSend(true);
		siteReceiveRequest.setIsCarTeam(true);
		siteReceiveRequest.setIsHasPDA(true);
		siteReceiveRequest.setStandardCode("standardCode");
		siteReceiveRequest.setHandType("handType");
		ESBClientHeader esbHeader = EsbUtil.createAsyncOutHeader(Constant.RECEIVE_SITE,siteReceiveRequest.getDeptCode());
		esbHeader.setBackServiceCode(Constant.RECEIVE_SITE);
		String ss = XmlJaxbMapper.writeValue(siteReceiveRequest, _QNAME);
		esbHeader.setSourceSystem("FOSS");
		sender.send(ss, esbHeader);
	}

	/**
	 * @作者：罗典
	 * @描述：权限同步接口(单向)
	 * @时间：2013-01-08
	 * */
	@Test
	public void orderRightSyncTest() throws CrmBusinessException  {
		QName _QNAME = new QName(
				"http://www.deppon.com/crm/inteface/foss/domain",
				"orderRightRequest");
		OrderRightInfo orderRightInfo = new OrderRightInfo();
		orderRightInfo.setOrderTeam("orderTeam");
		orderRightInfo.setDepartment("department");
		orderRightInfo.setOperateType("insert");
		orderRightInfo.setOperateTime(new Date());
		OrderRightRequest orderRightRequest = new OrderRightRequest();
		orderRightRequest.setOrderRightInfo(orderRightInfo);
		ESBClientHeader esbHeader = EsbUtil.createAsyncOutHeader(Constant.ORDER_RIGHT,orderRightInfo.getDepartment());
		esbHeader.setBackServiceCode(Constant.ORDER_RIGHT);
		String ss = XmlJaxbMapper.writeValue(orderRightRequest, _QNAME);
		esbHeader.setSourceSystem("FIN_BANK");
		sender.send(ss, esbHeader);
	}

	/**
	 * @作者：罗典
	 * @描述：合同月结天数同步接口
	 * @时间：2013-01-21
	 * */
	@Test
	public void receiveCreditUsedTest() throws CrmBusinessException{
		QName _QNAME = new QName(
				"http://www.deppon.com/crm/inteface/foss/domain",
				"receiveCreditUsedRequest");
		HasUsedAmountInfo hasUsedAmountInfo = new HasUsedAmountInfo();
		hasUsedAmountInfo.setCustNumber("002920");
		hasUsedAmountInfo.setCustName("威康电子（东莞）有限公司");
		hasUsedAmountInfo.setHasUsedAmount(new BigDecimal(1));
		 Calendar c =Calendar.getInstance();
//		  c.setTime(new Date());
		  c.set(2011, 2, 12);
		  Date myDate = c.getTime();
		hasUsedAmountInfo.setEarliestDebtDate(myDate);
		ReceiveCreditUsedRequest receiveCreditUsedRequest = new ReceiveCreditUsedRequest();
		receiveCreditUsedRequest.getHasUsedAmountList().add(hasUsedAmountInfo);
		ESBClientHeader esbHeader = EsbUtil.createAsyncOutHeader(Constant.RECEIVE_CREDITUSED,receiveCreditUsedRequest.getHasUsedAmountList().size()+"");
		esbHeader.setBackServiceCode(Constant.RECEIVE_CREDITUSED);
		String ss = XmlJaxbMapper.writeValue(receiveCreditUsedRequest, _QNAME);
		esbHeader.setSourceSystem("FOSS");
		sender.send(ss, esbHeader);
	}

	/**
	 * @作者：罗典
	 * @描述：出发到达部门信息
	 * @时间：2013-01-21
	 * */
	@Test
	public void syncDeptOrganizea() throws CrmBusinessException{
		QName _QNAME = new QName(
				"http://www.deppon.com/ows/inteface/domain/",
				"SyncSalesDepartmentRequest");
		DepartmentInfo departmentInfo = new DepartmentInfo();
		departmentInfo.setId("cd320c3f-e727-417f-9551-c6cbaa7721e4");
		departmentInfo.setCode("353453452");
		departmentInfo.setName("虹桥大学1（待撤销）");
		departmentInfo.setPinyin("DP12471");
		departmentInfo.setLeave("Y");
		departmentInfo.setArrive("Y");
		departmentInfo.setStation("station");
		departmentInfo.setSlogans("slogans");
		departmentInfo.setUnifiedCode("DP09341");
//		departmentInfo.setOpenDate(XMLGregorianCalendar);
		departmentInfo.setMaxTempArrears(new BigDecimal(1));
		departmentInfo.setUsedTempArrears(new BigDecimal(1));
		departmentInfo.setBillingGroup("billingGroup");
		departmentInfo.setTransferCenter("transferCenter");
		departmentInfo.setPickupSelf("pickupSelf");
		departmentInfo.setDelivery("delivery");
		departmentInfo.setAirArrive("airArrive");
		departmentInfo.setTruckArrive("truckArrive");
		departmentInfo.setSinglePieceLimitKG(Double.valueOf("1"));
		departmentInfo.setSingleBillLimitKG(Double.valueOf("1"));
		departmentInfo.setSinglePieceLimitVOL(Double.valueOf("1"));
		departmentInfo.setSingleBillLimitVOL(Double.valueOf("1"));
		departmentInfo.setPickupAreaDesc("pickupAreaDesc");
		departmentInfo.setDeliveryAreaDesc("deliveryAreaDesc");
//		departmentInfo.setCreatTime(XMLGregorianCalendar);
//		departmentInfo.setModifyTime(XMLGregorianCalendar);
		departmentInfo.setActive("Y");
		departmentInfo.setCreateUserCode("createUserCode");
		departmentInfo.setModifyUserCode("modifyUserCode");
		departmentInfo.setDeliveryCoordinate("deliveryCoordinate");
		departmentInfo.setVersionNo(Double.valueOf("1"));
		departmentInfo.setCanCentralizedPickup("canCentralizedPickup");
		departmentInfo.setCanPaySerivceFee("canPaySerivceFee");
		departmentInfo.setCanReturnSignBill("canReturnSignBill");
		departmentInfo.setCanCashOnDelivery("canCashOnDelivery");
		departmentInfo.setCanAgentCollected("canAgentCollected");
		departmentInfo.setPickupExpressSelf("expressSelf");
		departmentInfo.setDeliveryExpress("diliveryExPRESS");
		departmentInfo.setCanPickupExpress("pickupexpress");
		departmentInfo.setCanReturnexpressSignBill("canReturnexpressSignBill");
		departmentInfo.setExpressPickupAreaDesc("expressPickup");
		departmentInfo.setExpressDeliveryAreaDesc("deliveryAR");
		SyncSalesDepartmentRequest syncSalesDepartmentRequest = new SyncSalesDepartmentRequest();
		syncSalesDepartmentRequest.getDepts().add(departmentInfo);
		ESBClientHeader esbHeader = EsbUtil.createAsyncOutHeader(Constant.SYNC_SALES_DEPARTMENT,syncSalesDepartmentRequest+"");
		esbHeader.setBackServiceCode(Constant.SYNC_SALES_DEPARTMENT);
		String ss = XmlJaxbMapper.writeValue(syncSalesDepartmentRequest, _QNAME);
		esbHeader.setSourceSystem("FOSS");
		sender.send(ss, esbHeader);
	}

	/**
	 * @作者：罗典
	 * @描述：组织部门信息
	 * @时间：2013-01-21
	 * */
	@Test
	public void syncOrganizea() throws CrmBusinessException{
		QName _QNAME = new QName(
				"http://www.deppon.com/ows/inteface/domain/",
				"SyncOrganizationRequest");
		OrganizationInfo organizationInfo = new OrganizationInfo();
		organizationInfo.setId("cd320c3f-e727-417f-9551-c6cbaa7721e4");
		organizationInfo.setOrgCode("353453452");
		organizationInfo.setOrgName("虹桥大学1（待撤销）");
		organizationInfo.setUnifiedCode("DP09341");
		organizationInfo.setSubsidiaryCode("test001");
		organizationInfo.setStatus("1");
		organizationInfo.setDivisionCode("N");
		organizationInfo.setBigRegion("N");
		organizationInfo.setSmallRegion("N");
		organizationInfo.setAddress("N");
		organizationInfo.setDeptArea(Double.valueOf("1"));
		organizationInfo.setProvCode("360000");
		organizationInfo.setCityCode("360100");
		organizationInfo.setCountyCode("360124");
		organizationInfo.setSalesDepartment("N");
		organizationInfo.setTransferCenter("N");
		organizationInfo.setDoAirDispatch("N");
		organizationInfo.setTransDepartment("N");
		organizationInfo.setDispatchTeam("N");
		organizationInfo.setBillingGroup("N");
		organizationInfo.setTransTeam("N");
		organizationInfo.setIsDeliverSchedule("N");
		organizationInfo.setIsArrangeGoods("N");
		organizationInfo.setDeliverOutfield("N");
		organizationInfo.setArrangeOutfield("N");
		organizationInfo.setArrangeBizType("N");
		organizationInfo.setActive("N");
		organizationInfo.setAirDispatch("N");
		organizationInfo.setEntityFinance("N");
		organizationInfo.setDepCoordinate("N");
		organizationInfo.setDepTelephone("N");
		organizationInfo.setDepFax("N");
		organizationInfo.setIsEntityFinance("N");
		organizationInfo.setOrgSimpleName("N");
		organizationInfo.setFullPath("N");
		organizationInfo.setVersionNo(Double.valueOf("1"));
		organizationInfo.setUumsId("uumsId");
		organizationInfo.setUumsParentId("uumsParentId");
		organizationInfo.setIsLeaf("isLeaf");
		organizationInfo.setDisplayOrder("displayOrder");
		organizationInfo.setDeptLevel("deptLevel");
		organizationInfo.setDeptDesc("deptDesc");
		organizationInfo.setAreaCode("areaCode");
		organizationInfo.setDeptEmail("deptEmail");
		organizationInfo.setZipCode("zipCode");
		organizationInfo.setDeptAttribute("deptAttribute");
		organizationInfo.setCanceledSystems("canceledSystems");
		organizationInfo.setEhrDeptCode("ehrDeptCode");
		organizationInfo.setCountryRegion("countryRegion");
		organizationInfo.setOrgEnSimple("orgEnSimple");
		organizationInfo.setUumsIds("uumsIds");
		organizationInfo.setParentOrgCode("parentOrgCode");
		organizationInfo.setIsExpressRegion("Y");
		organizationInfo.setIsVirualLading("Y");
		SyncOrganizationRequest syncOrganizationRequest = new SyncOrganizationRequest();
		syncOrganizationRequest.getOrganizationInfo().add(organizationInfo);
		ESBClientHeader esbHeader = EsbUtil.createAsyncOutHeader(Constant.SYNC_ORGANIZAITION,syncOrganizationRequest+"");
		esbHeader.setBackServiceCode(Constant.SYNC_ORGANIZAITION);
		String ss = XmlJaxbMapper.writeValue(syncOrganizationRequest, _QNAME);
		esbHeader.setSourceSystem("FOSS");
		sender.send(ss, esbHeader);
	}

	// 行政区域
	@Test
	public void testSyncDistrictRequest() throws CrmBusinessException{
		QName _QNAME = new QName(
				"http://www.deppon.com/ows/inteface/domain/",
				"SyncDistrictRequest");
		DistrictInfo districtInfo = new DistrictInfo();
		districtInfo.setId("id");
		districtInfo.setCode("code");
		districtInfo.setName("name");
		districtInfo.setSimpleName("simpleName");
		districtInfo.setAvailableName("availableName");
		districtInfo.setParentDistrictName("parentDistrictName");
		districtInfo.setVirtualDistrictId("virtualDistrictId");
		districtInfo.setDegree("degree");
		districtInfo.setParentDistrictCode("parentDistrictCode");
		districtInfo.setCreateTime(new Date());
		districtInfo.setModifyTime(new Date());
		districtInfo.setActive("active");
		districtInfo.setCreateUserCode("createUserCode");
		districtInfo.setModifyUserCode("modifyUserCode");
		districtInfo.setRegionSuffix("regionSuffix");
		districtInfo.setVersionNo(Double.valueOf("1"));
		districtInfo.setPinyin("pinyin");
		districtInfo.setPinyinAbbr("pinyinAbbr");
		SyncDistrictRequest syncDistrictRequest = new SyncDistrictRequest();
		syncDistrictRequest.getDistrictInfo().add(districtInfo);
		ESBClientHeader esbHeader = EsbUtil.createAsyncOutHeader(Constant.SYNC_DISTRICT,syncDistrictRequest+"");
		esbHeader.setBackServiceCode(Constant.SYNC_DISTRICT);
		String ss = XmlJaxbMapper.writeValue(syncDistrictRequest, _QNAME);
		esbHeader.setSourceSystem("FOSS");
		sender.send(ss, esbHeader);

	}


	// 车队信息
	@Test
	public void testSyncMotorcadeInfo() throws CrmBusinessException{
		QName _QNAME = new QName(
				"http://www.deppon.com/crm/inteface/domain/",
				"SyncMotorcadeRequest");
		SyncMotorcadeRequest districtInfo = new SyncMotorcadeRequest();

		MotorcadeInfo motorcadeInfo = new MotorcadeInfo();
		motorcadeInfo.setId("DP11719");
		motorcadeInfo.setIsTopMotorcade("Y");
		motorcadeInfo.setUnifiedCode("DP11719");
		motorcadeInfo.setModifyTime(new Date());
		districtInfo.getMotorcadeInfo().add(motorcadeInfo);

		ESBClientHeader esbHeader = EsbUtil.createAsyncOutHeader(Constant.SYNC_MOTORCADEINFO,districtInfo+"");
		esbHeader.setBackServiceCode(Constant.SYNC_MOTORCADEINFO);
		String ss = XmlJaxbMapper.writeValue(districtInfo, _QNAME);
		esbHeader.setSourceSystem("FOSS");
		System.out.println(ss);
		sender.send(ss, esbHeader);


	}

	//UUMS职位信息
	@Test
	public void testSyncPositionInfo() throws CrmBusinessException {
		QName _SendPositionRequest_QNAME = new QName(
				"http://www.deppon.com/uums/inteface/domain/usermanagement",
				"SendPositionRequest");
		SendPositionRequest sendPositionRequest = new SendPositionRequest();
		List<PositionInfo> list = new ArrayList<PositionInfo>();
		PositionInfo positionInfo = new PositionInfo();
		positionInfo.setId("1");
		positionInfo.setPositionName("小文");
		list.add(positionInfo);
		sendPositionRequest.getPositionInfo().addAll(list);

		ESBClientHeader esbHeader = EsbUtil.createAsyncOutHeader(
				Constant.POSITION_TO_CRM, sendPositionRequest + "");
		esbHeader.setBackServiceCode(Constant.POSITION_TO_CRM);
		String ss = XmlJaxbMapper.writeValue(sendPositionRequest,
				_SendPositionRequest_QNAME);
		esbHeader.setSourceSystem("UUMS");
		System.out.println(ss);
		sender.send(ss, esbHeader);
	}
}
