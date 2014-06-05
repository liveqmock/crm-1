package com.deppon.crm.test.client.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.BeforeClass;

import com.deppon.crm.module.client.complaint.IComplaintOperate;
import com.deppon.crm.module.client.customer.ICustomerOperate;
import com.deppon.crm.module.client.customer.dao.ISyncCallbackDao;
import com.deppon.crm.module.client.esb.IEsbToFossAsyOperate;
import com.deppon.crm.module.client.fin.IDepartmentOperate;
import com.deppon.crm.module.client.fin.IFINRecompenserOperate;
import com.deppon.crm.module.client.fin.IFINSelfserviceOperate;
import com.deppon.crm.module.client.map.IGisOperate;
import com.deppon.crm.module.client.map.IMapOperate;
import com.deppon.crm.module.client.order.IOaAccidentOperate;
import com.deppon.crm.module.client.order.IOrderOperate;
import com.deppon.crm.module.client.order.IWaybillOperate;
import com.deppon.crm.module.client.sms.ISmsInfoSender;
import com.deppon.crm.module.client.workflow.IContractApplyOperate;
import com.deppon.crm.module.client.workflow.IGiftApplyOperate;
import com.deppon.crm.module.client.workflow.IPaymentApplyOperate;
import com.deppon.crm.module.client.workflow.IRecompenseApplyOperate;
import com.deppon.crm.test.client.util.SpringHelper;
import com.deppon.foss.framework.server.adapter.mail.MailSenderService;
import com.deppon.oa.workflow.WorkFlowService;

/**
 * 测试超类
 * @author davidcun @2012-5-9
 */
public class OperaterTest {
	
	protected Log log = LogFactory.getLog(OperaterTest.class);

	protected static IContractApplyOperate contractApplyOperate;
	protected static IGiftApplyOperate giftApplyOperate;
	protected static IPaymentApplyOperate paymentApplyOperate;
	protected static IRecompenseApplyOperate recompenseApplyOperate;
	protected static IWaybillOperate waybillOperate;
	protected static IOrderOperate orderOperate;
	protected static IOaAccidentOperate oaAccidentOperate;
	protected static ICustomerOperate customerOperate;
	protected static ISmsInfoSender smsSender;
	protected static IOrderOperate orderOperate2;
	protected static IMapOperate mapOperate;
	protected static WorkFlowService workFlowService;
	protected static IComplaintOperate complaintOperate;
	protected static ISyncCallbackDao syncCallbackDao;
	protected static IDepartmentOperate departmentOperate;
	protected static ObjectMapper objectMapper = new ObjectMapper();
	protected static IEsbToFossAsyOperate esbToFossAsyOperate;
	protected static IFINRecompenserOperate fINBankNumberOperate;
	protected static IGisOperate gisOperate;
	protected static IFINSelfserviceOperate finSelfOperate;
	protected static MailSenderService mailSenderService;
	@BeforeClass
	public static void init(){
		mailSenderService=(MailSenderService)SpringHelper.getApplicationContext().getBean("mailSenderService");
		finSelfOperate=(IFINSelfserviceOperate)SpringHelper.getApplicationContext().getBean("fINSelfserviceOperate");
		gisOperate=(IGisOperate)SpringHelper.getApplicationContext().getBean("gisOperate");
		fINBankNumberOperate = (IFINRecompenserOperate)SpringHelper.getApplicationContext().getBean("fINRecompenserOperate");
		esbToFossAsyOperate = (IEsbToFossAsyOperate)SpringHelper.getApplicationContext().getBean("esbToFossAsyOperate");
		departmentOperate = (IDepartmentOperate)SpringHelper.getApplicationContext().getBean("departmentOperate");
		complaintOperate = (IComplaintOperate) SpringHelper.getApplicationContext().getBean("complaintOperate");
//		workFlowService = (WorkFlowService) SpringHelper.getApplicationContext().getBean("oaWorkFlowServiceWs");
		contractApplyOperate = (IContractApplyOperate) SpringHelper.getApplicationContext().getBean("contractApplyOperate");
		giftApplyOperate = (IGiftApplyOperate) SpringHelper.getApplicationContext().getBean("giftApplyOperate");
		paymentApplyOperate = (IPaymentApplyOperate) SpringHelper.getApplicationContext().getBean("paymentApplyOperate");
		recompenseApplyOperate =(IRecompenseApplyOperate) SpringHelper.getApplicationContext().getBean("recompenseApplyOperate");
    	waybillOperate = (IWaybillOperate) SpringHelper.getApplicationContext().getBean("waybillOperate");
//		orderOperate2 = (IOrderOperate) SpringHelper.getApplicationContext().getBean("orderOperate2");
		orderOperate = (IOrderOperate) SpringHelper.getApplicationContext().getBean("orderOperate");
		oaAccidentOperate = (IOaAccidentOperate) SpringHelper.getApplicationContext().getBean("oaAccidentOperate");
		customerOperate = (ICustomerOperate) SpringHelper.getApplicationContext().getBean("customerOperate");
		smsSender = (ISmsInfoSender) SpringHelper.getApplicationContext().getBean("smsInfoSender");
//		oaWorkFlowService = (OaWorkFlowServiceImpl) SpringHelper.getApplicationContext().getBean("oaWorkFlowService");
		mapOperate = (IMapOperate) SpringHelper.getApplicationContext().getBean("mapOperate");
//		syncCallbackDao = (ISyncCallbackDao)SpringHelper.getApplicationContext().getBean("syncCallbackDao");
	}
	public Date parse(String source,String pattern){
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			return format.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	public Date parse(String source){
		return parse(source, "yyyy-MM-dd");
	}
}
