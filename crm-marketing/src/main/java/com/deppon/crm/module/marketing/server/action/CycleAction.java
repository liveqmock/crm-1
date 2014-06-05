package com.deppon.crm.module.marketing.server.action;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.customer.shared.domain.ScatterCustomer;
import com.deppon.crm.module.marketing.server.manager.ICustomerGroupManager;
import com.deppon.crm.module.marketing.server.manager.IPlanManager;
import com.deppon.crm.module.marketing.server.manager.IScheduleManager;
import com.deppon.crm.module.marketing.server.manager.impl.CycleManager;
import com.deppon.crm.module.marketing.shared.domain.CustGroupDayAmount;
import com.deppon.crm.module.marketing.shared.domain.CustGroupDayDetail;
import com.deppon.crm.module.marketing.shared.domain.CustGroupDayDetailCondition;
import com.deppon.crm.module.marketing.shared.domain.CustomerGroup;
import com.deppon.crm.module.marketing.shared.domain.CustomerGroupDetail;
import com.deppon.crm.module.marketing.shared.domain.Day;
import com.deppon.crm.module.marketing.shared.domain.PathAnalysisInfo;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisit;
import com.deppon.crm.module.marketing.shared.domain.ScheduleQueryResultObject;
import com.deppon.crm.module.marketing.shared.domain.SearchCustomerCondition;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.message.IMessageBundle;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;

public class CycleAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	// 动态生成表格列头
	private List<ColumnModel> columModel;
	//列表
	private List<CustomerGroupDetail> cycleList;
	//发到货周期表Manager
	private CycleManager manager;
	//分组查询条件
	private CustomerGroupDetail condition;
	// 分页要用参数
	private int start;
	//每页多少行
	private int limit;
	// 总行数
	private Long totalCount;
	//回访记录
	private List<ReturnVisit> returnList;
	//线路分析
	private List<PathAnalysisInfo> pathAnalysisList;	
	//BEAN获得国际化对象
 	private IMessageBundle messageBundle;
 	//客户分组manager
 	private ICustomerGroupManager groupManager;
 	//弹出制定计划和日程的参数实体
 	private SearchCustomerCondition searchCustomerCondition;
 	//返回弹出框列表信息
 	private List<CustomerVo> customerInfoList;
 	//散客信息列表
 	private List<ScatterCustomer> scatterCustomerList; 
 	//计划manager
 	private IPlanManager planManager;
 	//日程Manager
 	private IScheduleManager scheduleManager;
 	//返回弹出框列表信息
 	private List<ScheduleQueryResultObject> scheduleList;	
 	//客户分组
 	private List<CustomerGroup>  group;	
 	//发到货周期表查询金额详情查询条件实体
 	private CustGroupDayDetailCondition custGroupDayDetailCondition;
	//查询类型
 	private String cycleType;
 	//部门ID
 	private String deptId;
	//客户分组-客户列表详情-查看每天的数据
	private List<CustGroupDayDetail> custGroupDayDetailList;

	/**
	 * Description:查询回访列表
	 * @author 毛建强
	 * @version 0.1 2012-4-18
	 * @param
	 * @return
	 * @throws InvocationTargetException 
	 * @throws NoSuchMethodException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalArgumentException 
	 * @throws SecurityException 
	 */
	@SuppressWarnings("unchecked")
	@JSON
	public String queryCycleList() throws SecurityException, IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		//结果定义
		Map<String, Object> map = new HashMap<String, Object>();
		//分页查询
		map = manager.searchCycleList(condition, start, limit,getCurrentUser());
		//获得查询数据
		cycleList = (List<CustomerGroupDetail>) map.get("data");
		//总数
		totalCount = Long.valueOf( map.get("count").toString());
		return SUCCESS;
	}

	/**
	 * Description:获得发到货周期表中查询结果的动态表头
	 * @author 肖红叶
	 * @version 0.1 2013-11-27
	 * @param
	 * @return
	 */
	@JSON
	public String queryCycleListHeader() {
		//列模型
		columModel = getTabelHeader(condition);
		return SUCCESS;
	}
	
	/**
	 * Description:获得发到货周期表中查看金额明细的动态表头
	 * @author 肖红叶
	 * @version 0.1 2013-11-27
	 * @param
	 * @return
	 */
	@JSON
	public String queryAmountDetailHeader() {
		//列模型
		columModel = getAmountDetailHeader(condition);
		return SUCCESS;
	}

	/**
	 * Description:查询发到货周期表中的月发到货金额明细<br />
	 * @author xiaohongye
	 * @throws InvocationTargetException 
	 * @throws NoSuchMethodException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalArgumentException 
	 * @throws SecurityException 
	 * @Date 2013-11-29
	 */
	@JSON
	public String searchAmountDetailByMonthDay() throws SecurityException, IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException{
		List<CustGroupDayDetail> details = manager.searchCustomerGroupDetailDayAmount(custGroupDayDetailCondition);
		 //迭代
		for (CustGroupDayDetail d : details) {
			d.setQueryMonthCycle(d.getQueryMonthCycle() + "天/次");
			//月金额
			List<CustGroupDayAmount> dayAmount = d.getAmountList();
			//获得Day 类对象
	    	@SuppressWarnings("rawtypes")
			Class dayClass = Class.forName(Day.class.getName());
	    	//获得实例
			Day days = (Day) dayClass.newInstance();
			//循环设置天数
			for (int i = 0; i <dayAmount.size(); i++) {
				@SuppressWarnings("unchecked")
				Method setDay = dayClass.getMethod("setDay"+(i+1), String.class);
				//调用
				setDay.invoke(days, dayAmount.get(i).getAmount());
			}
			//设置天集合中
			d.setDays(days);
		}
		custGroupDayDetailList = details;
		return SUCCESS;
	}
	
	
	/**
	 * Description:查询回访记录
	 * @author 毛建强
	 * @version 0.1 2012-4-20
	 * @param 
	 * @return
	 */
	@JSON
	public String searchReturnVisitRecords(){
		//判断条件是否为null
		if(condition!=null){
			//查询营销信息
			returnList = manager.searchReturnVisitRecords(condition.getCustId());
		}
		return SUCCESS;
	}
	/**
	 * Description:线路分析
	 * @author 毛建强
	 * @version 0.1 2012-4-21
	 * @param 
	 * @return
	 */
	@JSON
	public String searPathAnalysisInfos(){
		//查询线路
		pathAnalysisList = manager.searPathAnalysisInfos(condition.getCustNumber(), condition.getQueryDate(),cycleType,condition.getSendGoodsType());
		return SUCCESS;
	}
	/**
	 * Description:查询回访列表
	 * 
	 * @author 毛建强
	 * @version 0.1 2012-4-18
	 * @param
	 * @return
	 * @throws InvocationTargetException 
	 * @throws NoSuchMethodException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalArgumentException 
	 * @throws SecurityException 
	 */
	@SuppressWarnings("unchecked")
	@JSON
	public String queryCycleByGroupIdList() throws SecurityException, IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		//结果定义
		Map<String, Object> map = new HashMap<String, Object>();
		//分页查询
		map = manager.searchCycleList(condition, start, limit,getCurrentUser());
		//获取data数据
		cycleList =  (List<CustomerGroupDetail>) map.get("data");
		//总记录数
		totalCount = Long.valueOf( map.get("count").toString());
		return SUCCESS;
	}
	
	/**
	 * Description:获取发到货周期表查询结果的动态列头<br />
	 * @author 毛建强
	 * @version 0.1 2012-4-18
	 * @param 查询日期
	 * @return
	 */
	private List<ColumnModel> getTabelHeader(CustomerGroupDetail condition) {
		// 获取查询日期所在的月份
		Calendar cal = Calendar.getInstance();
		//设置时间
		cal.setTime(condition.getQueryDate());
		cal.get(Calendar.MONTH);
		// 查询月份
		int queryMonth = cal.get(Calendar.MONTH) + 1;
		// 查询月份上个月
		int lastQueryMonth = queryMonth - 1;
		// 上两个月
		int lastSecondQueryMonth = queryMonth - 2;
		// 获取上个月月份和上两个月月份
		if (queryMonth == 1) {
			//如果查询月份为1月，则上月为12月
			lastQueryMonth = 12;
			//上上月为11月
			lastSecondQueryMonth = 11;
		} else if (queryMonth == 2) {
			lastSecondQueryMonth = 12;
		}
		//列
		List<ColumnModel> columns = new ArrayList<ColumnModel>();
		//查询类型不为空
		if(condition.getFcAnalyseType()!=null){
			//行号
			ColumnModel rownumcolumn = new ColumnModel();
			//列号
			rownumcolumn.setXtype("rownumberer");
			//国际化
			rownumcolumn.setHeader(messageBundle.getMessage(getLocale(),"i18n.Cycle.rownum"));
			//列宽
			rownumcolumn.setWidth(40);
			//加入
			columns.add(rownumcolumn);
		}
		
		/**
		 * 设置剩余列 列名 国际化 宽度
		 */
		ColumnModel column1 = new ColumnModel();
		//设置列 --部门名称
		column1.setDataIndex("deptName");
		//国际化
		column1.setHeader(messageBundle.getMessage(getLocale(),"i18n.MonitorPlan.departName"));
		//列宽
		column1.setWidth(120);
		//添加到列
		columns.add(column1);
		//设置列
		ColumnModel column2 = new ColumnModel();
		//客户编码
		column2.setDataIndex("custNumber");
		//国际化
		column2.setHeader(messageBundle.getMessage(getLocale(),"i18n.Cycle.custId"));
		//列宽
		column2.setWidth(100);
		//列添加
		columns.add(column2);
		//定义列
		ColumnModel column3 = new ColumnModel();
		//客户名称
		column3.setDataIndex("custName");
		//列标题
		column3.setHeader(messageBundle.getMessage(getLocale(),"i18n.Cycle.custName"));
		//列宽
		column3.setWidth(180);
		//添加列
		columns.add(column3);
		//用户列
		ColumnModel column4 = new ColumnModel();
		//维护人名称
		column4.setDataIndex("empName");
		//维护人列头
		column4.setHeader(messageBundle.getMessage(getLocale(),"i18n.Cycle.userId"));
		//列宽
		column4.setWidth(80);
		//添加列集合
		columns.add(column4);
		//发货周期列
		ColumnModel column5 = new ColumnModel();
		//发货周期
		column5.setDataIndex("sendGoodCycle");
		//线路分析不空
		if(condition.getFcAnalyseType()!=null && condition.getFcAnalyseType().equals("2")){
			//到货周期
			/*column5.setHeader(lastQueryMonth+messageBundle.getMessage(getLocale(),"i18n.Cycle.monthArriveCycle"));*/
			column5.setHeader(messageBundle.getMessage(getLocale(),"i18n.Cycle.arriveGoodCycle"));
		}else{
			//发货周期
			/*column5.setHeader(lastQueryMonth+messageBundle.getMessage(getLocale(),"i18n.Cycle.monthCycle"));*/
			column5.setHeader(messageBundle.getMessage(getLocale(),"i18n.Cycle.sendGoodCycle"));
		}
		//列宽
		column5.setWidth(100);
		//添加
		columns.add(column5);
		//上上月金额
		ColumnModel column8 = new ColumnModel();
		//列索引
		column8.setDataIndex("month2Amount");
		if(condition.getFcAnalyseType()!=null && condition.getFcAnalyseType().equals("2")){
			//到货金额
			column8.setHeader(lastSecondQueryMonth+messageBundle.getMessage(getLocale(),"i18n.Cycle.monthArriveMoney"));
		}else{
			//发货金额
			column8.setHeader(lastSecondQueryMonth+messageBundle.getMessage(getLocale(),"i18n.Cycle.monthMoney"));
		}
		//列宽
		column8.setWidth(100);
		//添加
		columns.add(column8);
		//上月金额列
		ColumnModel column7 = new ColumnModel();
		//上月金额
		column7.setDataIndex("month1Amount");
		//查询类型不空
		if(condition.getFcAnalyseType()!=null && condition.getFcAnalyseType().equals("2")){
			//到货金额
			column7.setHeader(lastQueryMonth+messageBundle.getMessage(getLocale(),"i18n.Cycle.monthArriveMoney"));
		}else{
			//发货金额
			column7.setHeader(lastQueryMonth+messageBundle.getMessage(getLocale(),"i18n.Cycle.monthMoney"));
		}
		//列宽
		column7.setWidth(100);
		columns.add(column7);
		
		//当月金额列定义
		ColumnModel column6 = new ColumnModel();
		////当月金额列
		column6.setDataIndex("monthAmount");
		//查询类型不空
		if(condition.getFcAnalyseType()!=null && condition.getFcAnalyseType().equals("2")){
			//当月到达
			column6.setHeader(queryMonth+messageBundle.getMessage(getLocale(),"i18n.Cycle.monthArriveMoney"));
		}else{
			//当月发货
			column6.setHeader(queryMonth+messageBundle.getMessage(getLocale(),"i18n.Cycle.monthMoney"));
		}
		//列宽
		column6.setWidth(100);
		//添加
		columns.add(column6);
		return columns;
	}
	
	/**
	 * Description:获取查看金额明细的动态列头<br />
	 * @author 肖红叶
	 * @version 0.1 2013-11-27
	 * @return
	 */
	private List<ColumnModel> getAmountDetailHeader(CustomerGroupDetail condition) {
		// 获取查询日期所在的月份
		Calendar cal = Calendar.getInstance();
		//设置时间
		cal.setTime(condition.getQueryDate());
		//天数
		int days = cal.get(Calendar.DAY_OF_MONTH);
		// 查询月份
		int queryMonth = cal.get(Calendar.MONTH) + 1;
		//列
		List<ColumnModel> columns = new ArrayList<ColumnModel>();
		
		/**
		 * 序号列
		 */
		ColumnModel rownumcolumn = new ColumnModel();
		//列号
		rownumcolumn.setXtype("rownumberer");
		//国际化
		rownumcolumn.setHeader(messageBundle.getMessage(getLocale(),"i18n.Cycle.rownum"));
		//列宽
		rownumcolumn.setWidth(40);
		//加入
		columns.add(rownumcolumn);
		
		/**
		 * 部门名称列
		 */
		ColumnModel column1 = new ColumnModel();
		//设置列 --部门名称
		column1.setDataIndex("deptName");
		//国际化
		column1.setHeader(messageBundle.getMessage(getLocale(),"i18n.MonitorPlan.departName"));
		//列宽
		column1.setWidth(120);
		//添加到列
		columns.add(column1);
		
		/**
		 * 客户编码列
		 */
		ColumnModel column2 = new ColumnModel();
		//客户编码
		column2.setDataIndex("custNumber");
		//国际化
		column2.setHeader(messageBundle.getMessage(getLocale(),"i18n.Cycle.custId"));
		//列宽
		column2.setWidth(100);
		//列添加
		columns.add(column2);

		/**
		 * 客户名称列
		 */
		//定义列
		ColumnModel column3 = new ColumnModel();
		column3.setDataIndex("custName");
		//列标题
		column3.setHeader(messageBundle.getMessage(getLocale(),"i18n.Cycle.custName"));
		//列宽
		column3.setWidth(180);
		//添加列
		columns.add(column3);
		
		/**
		 * 本月发到货周期
		 */
		ColumnModel column4 = new ColumnModel();
		column4.setDataIndex("queryMonthCycle");
		//列标题
		if("1".equals(condition.getFcAnalyseType())){
			column4.setHeader(queryMonth + messageBundle.getMessage(getLocale(),"i18n.Cycle.monthCycle"));			
		}else{
			column4.setHeader(queryMonth + messageBundle.getMessage(getLocale(),"i18n.Cycle.monthArriveCycle"));
		}
		//列宽
		column4.setWidth(100);
		//添加列
		columns.add(column4);
		
		/**
		 * 金额明细列
		 */
		String month = messageBundle.getMessage(getLocale(),"i18n.Cycle.month");
		//国际化
		String day = messageBundle.getMessage(getLocale(),"i18n.Cycle.day");
		//循环设置
		for(int i=1;i<=days;i++){
			//列定义
			ColumnModel dayCcolumn = new ColumnModel();
			//列索引
			dayCcolumn.setDataIndex("days.day"+i);
			//列标头
			dayCcolumn.setHeader(queryMonth+month+String.valueOf(i)+day);
			//列宽
			dayCcolumn.setWidth(80);
			//添加到列集合
			columns.add(dayCcolumn);
		}
		return columns;
	}
	
	/**
	 * Description:进行数据转换，转换成前台也卖弄对应实体
	 * @author 毛建强
	 * @version 0.1 2012-4-19
	 * @param
	 * @return
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<CustomerGroupDetail> dataConvert(
			List<CustomerGroupDetail> details) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
		//迭代
		for (CustomerGroupDetail d : details) {
			//月金额
			List<String> dayAmount = d.getDayAmount();
			//获得Day 类对象
	    	Class cl = Class.forName(Day.class.getName());
	    	//获得实例
			Day obj = (Day) cl.newInstance();
			//循环设置天数
			for (int i = 0; i <dayAmount.size(); i++) {
				Method method1 = cl.getMethod("setDay"+(i+1), String.class);
				//调用
				@SuppressWarnings("unused")
				Object result = method1.invoke(obj, dayAmount.get(i));
			}
			//设置天集合中
			d.setDays(obj);
		}
		//返回结果
		return details;
	}
	
	/**
	 * Description:获取客户分组列表
	 * @author 毛建强
	 * @version 0.1 2012-4-26
	 * @param 
	 * @return
	 */
	@JSON
	public String searchCustomerGroupList(){
		//部门ID为空
		if(StringUtils.isBlank(deptId)){
			//当前登录用户部门
			deptId=getCurrentUserDeptId();
		}
		//查询分组
		group = groupManager.getCustomerGroupList(deptId);
		return SUCCESS;
	}
	
	/**
	 * 
	 * Description:获取弹出框数据
	 * @author 毛建强
	 * @version 0.1 2012-4-27
	 * @param 
	 * @return
	 */
	@JSON
	@SuppressWarnings("unchecked")
	public String searchDevelopPopDataList(){
		//分页查询
		Map<String, Object> map =planManager.searchCustomerList4Plan(searchCustomerCondition,start,limit,getCurrentUser());
		//获得客户
		this.customerInfoList =(List<CustomerVo>) map.get("members");
		//总数
		totalCount = Long.valueOf( map.get("count").toString());
		return SUCCESS;
	}
	
	/**
	 * Description:获取弹出框数据
	 * @author 毛建强
	 * @version 0.1 2012-4-27
	 * @param 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String searchSchedulePopDataList(){
		//分页查询
		Map<String, Object> map =scheduleManager.searchCustomerList4Plan(searchCustomerCondition,start,limit,getCurrentUser());
		//列表
		scheduleList = (List<ScheduleQueryResultObject>) map.get("list");
		//总数
		totalCount = Long.valueOf( map.get("count").toString());
		return SUCCESS;
	}
	
	/**
	 * @return columModel : get the property columModel
	 */
	public List<ColumnModel> getColumModel() {
		return columModel;
	}
	/**
	 * @param columModel : set the property columModel
	 */
	public void setColumModel(List<ColumnModel> columModel) {
		this.columModel = columModel;
	}
	/**
	 * @return getCycleList : get the property getCycleList
	 */
	public List<CustomerGroupDetail> getCycleList() {
		return cycleList;
	}
	/**
	 * @param manager : set the property manager
	 */
	public void setManager(CycleManager manager) {
		this.manager = manager;
	}

	/**
	 * @param start : set the property start
	 */
	public void setStart(int start) {
		this.start = start;
	}
	/**
	 * @param limit : set the property limit
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}
	/**
	 * @return totalCount : get the property totalCount
	 */
	public Long getTotalCount() {
		return totalCount;
	}

	/**
	 * @param totalCount : set the property totalCount
	 */
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	/**
	 * @param condition : set the property condition
	 */
	public void setCondition(CustomerGroupDetail condition) {
		this.condition = condition;
	}
	/**
	 * @param condition : set the property condition
	 */
	public CustomerGroupDetail getCondition() {
		return condition;
	}
	/**
	 * @return returnList : get the property returnList
	 */
	public List<ReturnVisit> getReturnList() {
		return returnList;
	}
	/**
	 * @return pathAnalysisList : get the property pathAnalysisList
	 */
	public List<PathAnalysisInfo> getPathAnalysisList() {
		return pathAnalysisList;
	}
	/**
	 * @param groupManager : set the property groupManager
	 */
	public void setGroupManager(ICustomerGroupManager groupManager) {
		this.groupManager = groupManager;
	}
	/**
	 * @return group : get the property group
	 */
	public List<CustomerGroup> getGroup() {
		return group;
	}
	/**
	 * @param group : set the property group
	 */
	public void setGroup(List<CustomerGroup> group) {
		this.group = group;
	}
	/**
	 * @return searchCustomerCondition : get the property searchCustomerCondition
	 */
	public SearchCustomerCondition getSearchCustomerCondition() {
		return searchCustomerCondition;
	}
	/**
	 * @param searchCustomerCondition : set the property searchCustomerCondition
	 */
	public void setSearchCustomerCondition(SearchCustomerCondition searchCustomerCondition) {
		this.searchCustomerCondition = searchCustomerCondition;
	}
	/**
	 * @return customerInfoList : get the property customerInfoList
	 */
	public List<CustomerVo> getCustomerInfoList() {
		return customerInfoList;
	}
	/**
	 * @return scatterCustomerList : get the property scatterCustomerList
	 */
	public List<ScatterCustomer> getScatterCustomerList() {
		return scatterCustomerList;
	}
	/**
	 * @param planManager : get the property planManager
	 */
	public void setPlanManager(IPlanManager planManager) {
		this.planManager = planManager;
	}
	/**
	 * @return scheduleList : get the property scheduleList
	 */
	public List<ScheduleQueryResultObject> getScheduleList() {
		return scheduleList;
	}
	/**
	 * @param scheduleList : get the property scheduleList
	 */
	public void setScheduleList(List<ScheduleQueryResultObject> scheduleList) {
		this.scheduleList = scheduleList;
	}
	/**
	 * @param scheduleManager : get the property scheduleManager
	 */
	public void setScheduleManager(IScheduleManager scheduleManager) {
		this.scheduleManager = scheduleManager;
	}
 	/**
 	 * 消息绑定器
 	 * @param messageBundle
 	 * @version V0.1 
 	 * @Date 2013-12-7
 	 */
 	public void setMessageBundle(IMessageBundle messageBundle) {
 		this.messageBundle = messageBundle;
 	}
	/**
	 * @param custGroupDayDetailCondition the custGroupDayDetailCondition to get
	 */
	public CustGroupDayDetailCondition getCustGroupDayDetailCondition() {
		return custGroupDayDetailCondition;
	}

	/**
	 * @param custGroupDayDetailCondition the custGroupDayDetailCondition to set
	 */
	public void setCustGroupDayDetailCondition(
			CustGroupDayDetailCondition custGroupDayDetailCondition) {
		this.custGroupDayDetailCondition = custGroupDayDetailCondition;
	}
	/**
	 *  获取当前用户
	 * @return
	 * @version V0.1 
	 * @Date 2013-12-6
	 */
	private User getCurrentUser(){
		return (User) UserContext.getCurrentUser();
	}
	
	/**
	 * 获取当前用户所属部门
	 * @return
	 * @version V0.1 
	 * @Date 2013-12-6
	 */
	private String getCurrentUserDeptId(){
		if((getCurrentUser() != null) && (getCurrentUser().getEmpCode() != null)){
			return getCurrentUser().getEmpCode().getDeptId().getId();
		}
		else{
			return null;
		}
	}
 	//设置类型
	public void setCycleType(String cycleType) {
		this.cycleType = cycleType;
	}
	//查询部门ID
	public String getDeptId() {
		return deptId;
	}
	//set deptid
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	/**
	 * @param custGroupDayDetailList the custGroupDayDetailList to get
	 */
	public List<CustGroupDayDetail> getCustGroupDayDetailList() {
		return custGroupDayDetailList;
	}

	/**
	 * @param custGroupDayDetailList the custGroupDayDetailList to set
	 */
	public void setCustGroupDayDetailList(
			List<CustGroupDayDetail> custGroupDayDetailList) {
		this.custGroupDayDetailList = custGroupDayDetailList;
	}
}
