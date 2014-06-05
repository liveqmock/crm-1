package com.deppon.crm.module.scheduler.server.job;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.order.IOrderOperate;
import com.deppon.crm.module.common.file.domain.exception.FileException;
import com.deppon.crm.module.common.file.domain.exception.FileExceptionType;
import com.deppon.crm.module.common.file.util.ExcelExporter;
import com.deppon.crm.module.common.file.util.FileUtil;
import com.deppon.crm.module.common.server.util.DataDictionaryUtil;
import com.deppon.crm.module.common.shared.domain.DataHeadTypeEnum;
import com.deppon.crm.module.order.server.service.IOrderService;
import com.deppon.crm.module.order.server.util.Constant;
import com.deppon.crm.module.order.shared.domain.Order;
import com.deppon.crm.module.order.shared.domain.OrderSearchCondition;
import com.deppon.crm.module.scheduler.server.service.ISchedulingControlService;
import com.deppon.crm.module.scheduler.server.service.impl.SchedulingControlService;
import com.deppon.ebm.OrderStatusReqInfo;
import com.deppon.erp.custAndOrder.ExceptionOrder;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.foss.framework.server.adapter.mail.MailInfo;
import com.deppon.foss.framework.server.adapter.mail.MailSenderService;
import com.deppon.foss.framework.server.components.jobgrid.GridJob;
/**
 * 
 * <p>
 * Description:定时器声明-订单状态任务<br />
 * </p>
 * @title OrderStatusMonitorJob.java
 * @package com.deppon.crm.module.scheduler.server.job 
 * @author 侯斌飞
 * @version 0.1 2013-2-19
 */
public class OrderStatusMonitorJob extends GridJob {
	private final String ERR_MSG="接口调用失败";
	private final String CREATE = "create";
	private final String MODIFY = "modify";
	private static final String EXCELNAME = "comparOrder.xlsx";
	
	private static Logger logger = Logger.getLogger(OrderStatusMonitorJob.class);
	
	
	public void orderStatusMonitoring() {
		IOrderOperate orderOperate = getBean("orderOperate",
				IOrderOperate.class);
		IOrderService orderService = getBean("orderService",
				IOrderService.class);
		ISchedulingControlService schedulingControlService = getBean("schedulingControlService",
				SchedulingControlService.class);
		MailSenderService mailSenderService = getBean("mailSenderService",
				MailSenderService.class);
		/**
		 * 订单数据对比
		 */
		String errorMessage = "";
		String path = com.deppon.crm.module.common.file.util.PropertiesUtil.getInstance()
				.getProperty("excelExportTemplatePath").trim();
		String filePath = path + "/" + EXCELNAME;// 组织文件路径
		ExcelExporter exporter = new ExcelExporter();
		XSSFWorkbook wb = exporter.getExcel07Wb(filePath);// 获得工作薄
		//创建查询条件
		OrderSearchCondition oscCreate = this.createMonitoringCommonCondition(CREATE);
		//生成日数据
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateData = "时间：从"+sf.format(oscCreate.getStartDate())+"到"+sf.format(oscCreate.getEndDate());
		String subject = "从"+sf.format(oscCreate.getStartDate())+"到"+sf.format(oscCreate.getEndDate())+"订单状态监控";
		String content = "各位领导：这是从"+sf.format(oscCreate.getStartDate())+"到"+sf.format(oscCreate.getEndDate())+"订单状态监控";
		//将时间数据写到excel中
		wb = exporter.setExcel07WbValue(wb, 0, 0, dateData);
		
		//获取创建的订单数据
		List<Order> listCRM = new ArrayList<Order>();
		//TODO 获得EBM接口数据//TODO建索引
		List<OrderStatusReqInfo> listEBM = new ArrayList<OrderStatusReqInfo>();
		oscCreate.setEndDate(oscCreate.getStartDate());
		for(int i = 0;i<24;i++){
			Date dateStart =  oscCreate.getEndDate();
			Date dateEnd =  new Date(dateStart.getTime());
			dateEnd.setHours(dateStart.getHours()+1);
			oscCreate.setStartDate(dateStart);
			oscCreate.setEndDate(dateEnd);
			//获取创建的订单数据EBM
			List<OrderStatusReqInfo> ordrListEbm = new ArrayList<OrderStatusReqInfo>();//替换
			try{
				ordrListEbm = orderOperate.queryEBMOrderStatus(oscCreate.getStartDate(), oscCreate.getEndDate());
			}catch(Exception e){
				errorMessage = "";
				errorMessage = errorMessage+"出错：获取创建时间从"+sf.format(oscCreate.getStartDate())+"到"
			+sf.format(oscCreate.getEndDate())+"数据接口出现异常，无法统计，尽请原谅";
				logger.info(errorMessage);
				e.printStackTrace();
				continue;
			}
			listEBM.addAll(ordrListEbm);
			//获取创建的订单数据
			List<Order> ordrList = orderService.searchOrders(oscCreate);
			listCRM.addAll(ordrList);
		}
		//写入EXCEL CRM创建订单个数
		wb = exporter.setExcel07WbValue(wb, 3, 0, listCRM.size());
		//写入EXCEL EBM创建订单个数
		wb = exporter.setExcel07WbValue(wb, 3, 1, listEBM.size());
		//生成比较数据
		List<List<Object>> createComparOrderData = this.orderCompareCreate(listCRM,listEBM);
		//写到excel中
		if(createComparOrderData.size()!=0&&createComparOrderData.get(0).size()!=0){
			wb = exporter.setExcel07List(wb, 5, 0, createComparOrderData);
		}
		
		
		/**
		 * CRM与EBM订单状态比较
		 */
		//修改查询条件
	    OrderSearchCondition oscModify = this.createMonitoringCommonCondition(MODIFY);
		List<Order> listCRMModify = new ArrayList<Order>();
  		//TODO获得EBM接口数据//TODO建索引
  		List<OrderStatusReqInfo> listEBMModify = new ArrayList<OrderStatusReqInfo>();
		oscModify.setEndModifyDate(oscModify.getStartModifyDate());
		for(int i = 0;i<24;i++){
			Date dateStart =  oscModify.getEndModifyDate();
			Date dateEnd =  new Date(dateStart.getTime());
			dateEnd.setHours(dateStart.getHours()+1);
			oscModify.setStartModifyDate(dateStart);
			oscModify.setEndModifyDate(dateEnd);
			//获取创建的订单数据
			List<Order> ordrList = orderService.searchOrders(oscModify);
			//获取修改的订单数据EBM
			List<OrderStatusReqInfo> ordrListEbm = new ArrayList();//替换
			try{
				ordrListEbm = orderOperate.queryEBMOrderStatus(oscModify.getStartModifyDate(), oscModify.getEndModifyDate());
			}catch(Exception e){
				errorMessage = "";
				errorMessage = errorMessage+"出错：获取最后修改时间从"+sf.format(oscModify.getStartModifyDate())+"到"
						+sf.format(oscModify.getEndModifyDate())+"数据接口出现异常，无法统计，尽请原谅";
				logger.info(errorMessage);
				e.printStackTrace();
				continue;
			}
			listCRMModify.addAll(ordrList);
			listEBMModify.addAll(ordrListEbm);
		}
  	    //写入EXCEL CRM修改订单个数
		wb = exporter.setExcel07WbValue(wb, 3, 3, listCRMModify.size());
		//写入EXCEL EBM修改订单个数
		wb = exporter.setExcel07WbValue(wb, 3, 4, listEBMModify.size());
        //生成比较数据
		List<List<Object>> modifyComparOrderData = this.orderCompareModify(listCRMModify, listEBMModify);
		//写到excel中
		if(modifyComparOrderData.size()!=0&&modifyComparOrderData.get(0).size()!=0){
			wb = exporter.setExcel07List(wb, 5, 2, modifyComparOrderData);
		}
		
		/**
		 * CRM与ERP订单状态比较
		 */
		//TODO 获得ERP接口数据//TODO建索引
		oscModify = this.createMonitoringCommonCondition(MODIFY);
		List<ExceptionOrder> listErpAll = new ArrayList<ExceptionOrder>();
		try {
			for(int i = 0;i<24;i++){
				Date dateStart =  oscModify.getEndModifyDate();
				Date dateEnd =  new Date(dateStart.getTime());
				dateEnd.setHours(dateStart.getHours()+1);
				oscModify.setStartModifyDate(dateStart);
				oscModify.setEndModifyDate(dateEnd);
				List<ExceptionOrder> listERP = orderOperate.queryERPOrderStatus(oscModify.getStartModifyDate(),oscModify.getEndModifyDate());
				listErpAll.addAll(listERP);
			}
		} catch (CrmBusinessException e) {
			logger.info(ERR_MSG, e);
			e.printStackTrace();
		}
	    List<String> orderNumList = new ArrayList<String>();
	    for(ExceptionOrder order:listErpAll){
	    	orderNumList.add(order.getOrderNum());
	    }
	    //TODO  根据订单号查询订单//TODO建索引
	    List<Order> listCRMSearch = new ArrayList<Order>();
	    for(String orderNum:orderNumList){
	    	Order order = orderService.getOrderByOrderNumber(orderNum);
	    	if(order!=null){
	    		listCRMSearch.add(orderService.getOrderByOrderNumber(orderNum));	    		
	    	}
	    }
	    //生成比较数据
	    List<List<Object>> comparOrderERPCRMData = this.orderCompareERP(listCRMSearch, listErpAll, orderNumList);
	    //写到excel中
  		if(comparOrderERPCRMData.size()!=0&&comparOrderERPCRMData.get(0).size()!=0){
  			wb = exporter.setExcel07List(wb, 3, 5, comparOrderERPCRMData);
  		}
	  	
		String createPath = com.deppon.crm.module.common.file.util.PropertiesUtil.getInstance()
				.getProperty("excelExportFilePath").trim();
		String fileName = FileUtil.createFileName(".xlsx");
		String realPath = exporter.createExcelDecrypt(wb, createPath, fileName);
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(realPath);
		} catch (FileNotFoundException e) {
			FileException fe = new FileException(
					FileExceptionType.FILE_EXCEPTION_FILENOTEXPORT);
			throw new GeneralException(fe.getErrorCode(), fe.getMessage(), fe,
					new Object[] {}) {
			};
		}
		
		// 给相关开发员发邮件
		String _email = schedulingControlService
				.searchValueByKey("T_CRM_ORDERSTATUSMONITOR");
		if (_email != null && !"".equals(_email)) {
			String[] _to = _email.split(";");
			try {
				MailInfo mi = new MailInfo();
				mi.setFrom(mailSenderService.getUserName());//谁发
				mi.setTo(_to);//发给谁
				mi.setSubject(subject);
				mi.setContent(content);
				File file  = new File(createPath + "/" + fileName);
				File[] files = {file};
				mi.setAttachments(files);
				mailSenderService.sendExtranetMail(mi);
			} catch (Exception e1) {
				logger.info("邮件发送异常",e1);
			}

		}
		//return inputStream;
	}
	
	private List<List<Object>> orderCompareCreate(List<Order> crm,List<OrderStatusReqInfo> ebm){
		    Map<String,String> mapCRM  = new HashMap<String,String>();
		    Set<String> setCRM  = new HashSet<String>();
		    Map<String,String> mapEBM  = new HashMap<String,String>();
		    Set<String> setEBM  = new HashSet<String>();
		    //电商订单来源集合
		    List<String> resourceList = new ArrayList<String>();
		    resourceList.add(Constant.ORDER_SOURCE_TAOBAO);//淘宝
		    resourceList.add(Constant.ORDER_SOURCE_ALIBABA);//阿里
		    resourceList.add(Constant.ORDER_SOURCE_YOUSHANG);//金蝶友商
		    resourceList.add(Constant.ORDER_SOURCE_SHANGCHENG);//淘宝商城
		    //获取CRM的值
		    for(Order order:crm){
		    	if(resourceList.contains(order.getResource())){
		    		mapCRM.put(order.getOrderNumber(), order.getResource());
			    	setCRM.add(order.getOrderNumber());
		    	}
		    }
		    //获取EBM的值
		    for(OrderStatusReqInfo order:ebm){
		    	mapEBM.put(order.getOrdernum(), order.getSource());
		    	setEBM.add(order.getOrdernum());
		    }
		    //生成的数据
		    List<List<Object>> listList = new ArrayList<List<Object>>();
		    Set<String> setErrorData  = new HashSet<String>();
		    for(String orderNum:setCRM){
		    	if(mapEBM.get(orderNum)==null&&!setErrorData.contains(orderNum)){
		    		List<Object> list = new ArrayList<Object>();
		    		list.add(orderNum+"/"+DataDictionaryUtil.getCodeDesc(
		    				DataHeadTypeEnum.ORDER_SOURCE,mapCRM.get(orderNum)));
		    		list.add("该订单在CRM中存在，在EBM中不存在");
		    		listList.add(list);
		    		setErrorData.add(orderNum);
		    	}
		    }
		    for(String orderNum:setEBM){
		    	if(mapCRM.get(orderNum)==null&&!setErrorData.contains(orderNum)){
		    		List<Object> list = new ArrayList<Object>();
		    		list.add(orderNum+"/"+sourceConvertor(mapEBM.get(orderNum)));
		    		list.add("该订单在EBM中存在，在CRM中不存在");
		    		listList.add(list);
		    		setErrorData.add(orderNum);
		    	}
		    	
		    }
			return listList;
	}
	
	private static String sourceConvertor(String ebmSrc){
		if(ebmSrc==null || "".equals(ebmSrc)){
			return "";
		}
		if(ebmSrc.equals("0")){
			return "网上营业厅";
		}
		if(ebmSrc.equals("1")){
			return "淘宝网";
		}
		if(ebmSrc.equals("2")){
			return "呼叫中心";
		}
		if(ebmSrc.equals("3")){
			return "阿里巴巴网";
		}
		if(ebmSrc.equals("4")){
			return "金蝶友商网";
		}
		if(ebmSrc.equals("5")){
			return "淘宝商城";
		}
		return "";
	}
	
	private static String statusConvertor(String status){
		/*
		 * 待受理,已受理,未分配,已撤销,已拒绝,正常签收
		 * 异常签收,揽货失败,已开单,已约车,接货中,已退回
		 */
		if(status == null || status.equals("")){
			return "";
		}
		if(status.equals("1")){
			return "待受理";
		}
		if(status.equals("2")){
			return "已受理";
		}
		if(status.equals("3")){
			return "未分配";
		}
		if(status.equals("4")){
			return "已撤销";
		}
		if(status.equals("5")){
			return "已拒绝";
		}
		if(status.equals("6")){
			return "正常签收";
		}
		if(status.equals("7")){
			return "异常签收";
		}
		if(status.equals("11")){
			return "揽货失败";
		}
		if(status.equals("22")){
			return "已开单";
		}
		if(status.equals("31")){
			return "已约车";
		}
		if(status.equals("32")){
			return "接货中";
		}
		if(status.equals("33")){
			return "已退回";
		}
		return "";
	}
	private List<List<Object>> orderCompareModify(List<Order> crm,List<OrderStatusReqInfo> ebm){
		IOrderService orderService = getBean("orderService",
				IOrderService.class);
		
		Map<String,String> mapCRM  = new HashMap<String,String>();
	    Set<String> setCRM  = new HashSet<String>();
	    Map<String,String> mapEBM  = new HashMap<String,String>();
	    Set<String> setEBM  = new HashSet<String>();
	    List<List<Object>> listList = new ArrayList<List<Object>>();
	    List<String> resourceList = new ArrayList<String>();
	    resourceList.add(Constant.ORDER_SOURCE_TAOBAO);
	    resourceList.add(Constant.ORDER_SOURCE_ALIBABA);
	    resourceList.add(Constant.ORDER_SOURCE_YOUSHANG);
	    resourceList.add(Constant.ORDER_SOURCE_SHANGCHENG);
	    for(Order order:crm){
	    	if(resourceList.contains(order.getResource())){
	    		mapCRM.put(order.getOrderNumber(), order.getOrderStatus());
		    	setCRM.add(order.getOrderNumber());
	    	}
	    }
	    for(OrderStatusReqInfo order:ebm){
	    	mapEBM.put(order.getOrdernum(), order.getOrderstate());
	    	setEBM.add(order.getOrdernum());
	    }
	    Set<String> setErrorData  = new HashSet<String>();
	    for(String orderNum:setCRM){
	    	if(mapEBM.get(orderNum)==null){
	    		//TODO 从EBM获取订单状态
	    		//Order searchOrder = new Order();
	    		OrderStatusReqInfo searchOrder = null;
	    		for(OrderStatusReqInfo order:ebm){
	    			if(order.getOrdernum().equals(orderNum)){
	    				searchOrder = order;
	    			}
	    		
		    		if(searchOrder!=null && !searchOrder.getOrderstate().equals(mapCRM.get(orderNum))&&
		    				!setErrorData.contains(orderNum)){
		    			List<Object> list = new ArrayList<Object>();
		    			list.add(orderNum);
		    			list.add("该订单在CRM中状态为"
		    			+DataDictionaryUtil.getCodeDesc(DataHeadTypeEnum.ORDER_STATUS,mapCRM.get(orderNum))
		    			+"，在EBM中状态为"
		    			+statusConvertor(searchOrder.getOrderstate()));
		    			setErrorData.add(orderNum);
	    			}
	    		}
	    	}else{
	    		if(!mapEBM.get(orderNum).equals(mapCRM.get(orderNum))&&
	    				!setErrorData.contains(orderNum)){
	    			List<Object> list = new ArrayList<Object>();
	    			list.add(orderNum);
	    			list.add("该订单在CRM中状态为"
	    	    			+DataDictionaryUtil.getCodeDesc(DataHeadTypeEnum.ORDER_STATUS,mapCRM.get(orderNum))
	    	    			+"，在EBM中状态为"
	    	    			+statusConvertor(mapEBM.get(orderNum)));
	    	    			setErrorData.add(orderNum);
	    			setErrorData.add(orderNum);
	    		}
	    	}
	    }
	    for(String orderNum:setEBM){
	    	if(mapEBM.get(orderNum)==null){
	    		//创建查询条件
	    		OrderSearchCondition oscOrderNum = new OrderSearchCondition();
	    		oscOrderNum.setOrderNum(orderNum);
	    		List<Order> searchOrderList = orderService.searchOrders(oscOrderNum);
	    		if(!searchOrderList.get(0).getOrderStatus().equals(mapEBM.get(orderNum))&&
	    				!setErrorData.contains(orderNum)){
	    			List<Object> list = new ArrayList<Object>();
	    			list.add(orderNum);
	    			list.add("该订单在CRM中状态为"
	    	    			+DataDictionaryUtil.getCodeDesc(DataHeadTypeEnum.ORDER_STATUS,searchOrderList.get(0).getOrderStatus())
	    	    			+"，在EBM中状态为"
	    	    			+statusConvertor(mapEBM.get(orderNum)));
	    	    			setErrorData.add(orderNum);
	    			setErrorData.add(orderNum);
	    		}
	    	}else{
	    		if(!mapEBM.get(orderNum).equals(mapCRM.get(orderNum))&&
	    				!setErrorData.contains(orderNum)){
	    			List<Object> list = new ArrayList<Object>();
	    			list.add(orderNum);
	    			list.add("该订单在CRM中状态为"
	    	    			+DataDictionaryUtil.getCodeDesc(DataHeadTypeEnum.ORDER_STATUS,mapCRM.get(orderNum))
	    	    			+"，在EBM中状态为"
	    	    			+statusConvertor(mapEBM.get(orderNum)));
	    	    			setErrorData.add(orderNum);
	    			setErrorData.add(orderNum);
	    		}
	    	}
	    }
	    return listList;
	}
	private List<List<Object>> orderCompareERP(List<Order> crm,List<ExceptionOrder> erp,List<String> orderNumList){
	    Map<String,String> mapCRM  = new HashMap<String,String>();
	    Map<String,Integer> mapERP  = new HashMap<String,Integer>();
	    List<List<Object>> listList = new ArrayList<List<Object>>();
	    for(Order order:crm){
	    	mapCRM.put(order.getOrderNumber(), order.getOrderStatus());
	    }
	    for(ExceptionOrder order:erp){
	    	mapERP.put(order.getOrderNum(), order.getOrderStatus());
	    }
	    List<Object> list = new ArrayList<Object>();
	    for(String orderNum:orderNumList){
	    	if(mapCRM.get(orderNum)!=null && !mapCRM.get(orderNum).equals(mapERP.get(orderNum))){
	    		list.add(orderNum);
	    		list.add("该订单在CRM中状态为"
    	    			+DataDictionaryUtil.getCodeDesc(DataHeadTypeEnum.ORDER_STATUS,mapCRM.get(orderNum))
    	    			+"，在ERP中状态要修改为"
    	    			+mapERP.get(orderNum).toString());
	    	}
	    }	   
	    listList.add(list);
	    return listList;
	}
	private OrderSearchCondition createMonitoringCommonCondition(String mode){
		// 1公用条件
		OrderSearchCondition osc = new OrderSearchCondition();
		//时间（从昨天的零时零分零秒到今天的零时零分零秒）
		Date toDate = new Date();
		Date fromDate = new Date();
		fromDate.setDate(toDate.getDate()-1);
		// 组织数据日期
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String toDateStr = sf.format(toDate);
		String fromDateStr = sf.format(fromDate);
		//取代时分秒，就是当天的零时零分零秒
		try {
			toDate = sf.parse(toDateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fromDate = sf.parse(fromDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(mode.equals(CREATE)){
			osc.setEndDate(toDate);
			osc.setStartDate(fromDate);
		}else if(mode.equals(MODIFY)){
			osc.setEndModifyDate(toDate);
			osc.setStartModifyDate(fromDate);
		}
		return osc;
	}

	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		System.out.println("begin time:"+new Date() +":OrderStatusMonitorJob");
//		this.orderStatusMonitoring();
		System.out.println("end time:"+new Date() + ":OrderStatusMonitorJob");
	}
}
