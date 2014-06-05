package com.deppon.crm.module.marketing.server.action;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.marketing.server.manager.ICustomerGroupManager;
import com.deppon.crm.module.marketing.shared.domain.CustomerGroup;
import com.deppon.crm.module.marketing.shared.domain.CustomerGroupDetail;
import com.deppon.crm.module.marketing.shared.domain.Day;
import com.deppon.crm.module.organization.shared.domain.Employee;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;
import com.deppon.foss.framework.shared.util.string.StringUtil;

public class CustomerGroupAction extends AbstractAction {
	//客户分组
	private ICustomerGroupManager customerGroupManager;
	/**
	 * @param customerGroupManager : set the property customerGroupManager.
	 */
	public void setCustomerGroupManager(ICustomerGroupManager customerGroupManager) {
		this.customerGroupManager = customerGroupManager;
	}
	//分组列表
	private List<CustomerGroup> listCustomerGroup=new ArrayList<CustomerGroup>();
	/**
	 * @param listCustomerGroup : get the property listCustomerGroup.
	 */
	public List<CustomerGroup> getListCustomerGroup() {
		return listCustomerGroup;
	}
	/**
	 * @param listCustomerGroup : set the property listCustomerGroup.
	 */
	public void setListCustomerGroup(List<CustomerGroup> listCustomerGroup) {
		this.listCustomerGroup = listCustomerGroup;
	}
	
	/**
	 * 分组明细
	 */
	private List<CustomerGroupDetail> listCustomerGroupDetail;
	/**
	 * @return listCustomerGroupDetail : get the property listCustomerGroupDetail.
	 */
	public List<CustomerGroupDetail> getListCustomerGroupDetail() {
		return listCustomerGroupDetail;
	}
	/**
	 * @param listCustomerGroupDetail : set the property listCustomerGroupDetail.
	 */
	public void setListCustomerGroupDetail(
			List<CustomerGroupDetail> listCustomerGroupDetail) {
		this.listCustomerGroupDetail = listCustomerGroupDetail;
	}
	//分组
	private CustomerGroup customerGroup;
	/**
	 * @return customerGroup : get the property customerGroup.
	 */
	public CustomerGroup getCustomerGroup() {
		return customerGroup;
	}
	/**
	 * @param customerGroup : set the property customerGroup.
	 */
	public void setCustomerGroup(CustomerGroup customerGroup) {
		this.customerGroup = customerGroup;
	}
	//客户分组明细
	private CustomerGroupDetail customerGroupDetail;
	/**
	 * @return customerGroupDetail : set the property customerGroup.
	 */
	public CustomerGroupDetail getCustomerGroupDetail() {
		return customerGroupDetail;
	}
	/**
	 * @param customerGroupDetail : set the property customerGroup.
	 */
	public void setCustomerGroupDetail(CustomerGroupDetail customerGroupDetail) {
		this.customerGroupDetail = customerGroupDetail;
	}
	//客户分组id
	private String customerGroupId;
	/**
	 * @return customerGroupId : get the property customerGroupId.
	 */
	public String getCustomerGroupId() {
		return customerGroupId;
	}
	/**
	 * @param customerGroupId : set the property customerGroupId.
	 */
	public void setCustomerGroupId(String customerGroupId) {
		this.customerGroupId = customerGroupId;
	}
	
	//总条数
	private Long totalCount;
	/**
	 * @return totalCount : get the property totalCount.
	 */
	public Long getTotalCount() {
		return totalCount;
	}
	// 起始页
	private int start;
	/**
	 * @param start : get the property start.
	 */	
	public void setStart(int start) {
		this.start = start;
	}
	// 每页显示条数
	private int limit;
	/**
	 * @param limit : get the property limit.
	 */	
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	
 	//提示信息
	 private String message;
	 /**
	  * @return message : get the property message.
	  */	
	 public String getMessage() {
		 return message;
	 }
    //分配维护人Id
    private String userId;
    /**
     * @return userId : get the property userId.
     */
    public String getUserId() {
    	return userId;
    }
    /**
     * @param userId : get the property userId.
     */
    public void setUserId(String userId) {
    	this.userId = userId;
    }
    //部门ID
	private String deptId;
	/**
     * @return deptId : get the property deptId.
     */
	public String getDeptId() {
		return deptId;
	}
	/**
     * @param deptId : set the property deptId.
     */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	/**
	 * 员工列表
	 */
	private List<Employee> listEmployee;
	/**
     * @return listEmployee : set the property listEmployee.
     */
	public List<Employee> getListEmployee() {
		return listEmployee;
	}
	/**
     * @param listEmployee : get the property listEmployee.
     */
	public void setListEmployee(List<Employee> listEmployee) {
		this.listEmployee = listEmployee;
	}
	// 获取当前用户
 	private User getCurrentUser(){
 		return (User) UserContext.getCurrentUser();
 	}
 	
 	
 	
 	/**
 	 * 新增
 	 */
 	List<CustomerGroupDetail> createdListDetail=null;
 	/**
 	 * 修改
 	 */
	List<CustomerGroupDetail> deletedListDetail=null;
	/**
 	 * 删除用户
 	 */
	List<CustomerGroupDetail> updatedListDetail=null;
	
	/**
	 * @return createdListDetail : return the property createdListDetail.
	 */
	public List<CustomerGroupDetail> getCreatedListDetail() {
		return createdListDetail;
	}
	/**
	 * @param createdListDetail : set the property createdListDetail.
	 */
	public void setCreatedListDetail(List<CustomerGroupDetail> createdListDetail) {
		this.createdListDetail = createdListDetail;
	}
	/**
	 * @return deletedListDetail : return the property deletedListDetail.
	 */
	public List<CustomerGroupDetail> getDeletedListDetail() {
		return deletedListDetail;
	}
	/**
	 * @param deletedListDetail : set the property deletedListDetail.
	 */
	public void setDeletedListDetail(List<CustomerGroupDetail> deletedListDetail) {
		this.deletedListDetail = deletedListDetail;
	}
	/**
	 * @return updatedListDetail : return the property updatedListDetail.
	 */
	public List<CustomerGroupDetail> getUpdatedListDetail() {
		return updatedListDetail;
	}
	/**
	 * @param updatedListDetail : set the property updatedListDetail.
	 */
	public void setUpdatedListDetail(List<CustomerGroupDetail> updatedListDetail) {
		this.updatedListDetail = updatedListDetail;
	}
	/**
	 * 查询分组列表
	 * @return SUCCESS
	 */
 	@JSON
	public String serachCustomerGroupList(){
 		//部门ID
 		String deptId = this.getCurrentUser().getEmpCode().getDeptId().getId();
 		//根据部门查询分组客户
		listCustomerGroup=customerGroupManager.getCustomerGroupList(deptId);
		return SUCCESS;
	}
 	
 	/**
	 * 查询分配维护人
	 * @return
	 */
 	@JSON
 	public String serachPrehumanByDeptId(){
 		if(StringUtils.isBlank(deptId)){
 			//当前用户部门
 			deptId = this.getCurrentUser().getEmpCode().getDeptId().getId();	
 		}
 		//根据部门查询维护人
 		listEmployee=customerGroupManager.getPrehumanByDeptId(deptId);
 		return SUCCESS;
 	}
 	
 	/**
	 * 根据分组取维护人ID
	 * @return
	 */
 	@JSON
 	public String searchPrehumanByGroupId(){
 		if(StringUtils.isBlank(deptId)){
 			//查询部门id
 			deptId = this.getCurrentUser().getEmpCode().getDeptId().getId();	
 		}
 		//查询分组结果
 		List<String> l=customerGroupManager.getPrehumanByGroupId(deptId,customerGroupId);
 		if(l.size()!=0 && !l.isEmpty()){
 			this.setUserId(l.get(0));
 		}
 		return SUCCESS;
 	}
 	
 	/**
	 * 根据维护人取分组ID
	 * @return
	 */
 	@JSON
 	public String serachCustomerGroupByPrehuman(){
 		if(StringUtils.isBlank(deptId)){
 			//当前部门
 			deptId = this.getCurrentUser().getEmpCode().getDeptId().getId();	
 		}
 		//查询分组结果
 		List<String> l=customerGroupManager.getCustomerGroupByPrehuman(deptId,userId);
 		if(l.size()!=0 && !l.isEmpty()){
 			this.setCustomerGroupId(l.get(0));
 		}
 		return SUCCESS;
 	}
 	
 	
	/**
	 * 新增分组
	 * @return
	 */
 	@JSON
	public String saveCustomerGroup(){
 		//创建分组
		customerGroupManager.createCustomerGroup(customerGroup.getGroupName(),this.getCurrentUser());
		return SUCCESS;
	}
	
	/**
	 * 修改分组
	 * @return
	 */
 	@JSON
	public String updateCustomerGroup(){
 		//更新分组
		customerGroupManager.updateCustomerGroup(customerGroup.getGroupName(), customerGroup.getId(),this.getCurrentUser());
		return SUCCESS;
	}
	
	/**
	 * 删除分组
	 * @return
	 */
 	@JSON
	public String deleteCustomerGroup(){
 		//删除分组
		customerGroupManager.deleteCustomerGroup(customerGroupId,this.getCurrentUser());
		return SUCCESS;
	}
	
	
	/**
	 * 客户分组，点击管理某个分组后，展示该分组下的具体客户信息
	 * @return
	 * @throws InvocationTargetException 
	 * @throws NoSuchMethodException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalArgumentException 
	 * @throws SecurityException 
	 */
 	@JSON
	public String queryCustomerGroupDetail() throws SecurityException, IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException{
 		//查询客户分组明细
		listCustomerGroupDetail=customerGroupManager.getCustomerGroupDetailList(customerGroupId,customerGroupDetail.getQueryDate(),null);
		//循环处理客户分组明细信息
//		for(CustomerGroupDetail detail : listCustomerGroupDetail){
//			//发货次数为0
//			if (detail.getSendGoodCount() == 0) {
//				//周期设置为0
//				detail.setSendGoodCycle("0");
//				continue;
//			}
//			//发货周期计算
//			int cycle = 30 / detail.getSendGoodCount();
//			//设置
//			detail.setSendGoodCycle(String.valueOf(cycle));
//		}
		// 进行对象转换
//		if (listCustomerGroupDetail != null) {
//			//数据转换
//			listCustomerGroupDetail = dataConvert(listCustomerGroupDetail);
//		}
		return SUCCESS;
	}
	
	/**
	 * 客户分组，点击管理某个分组后，按用户录入的搜索条件，搜索符合条件的用户信息
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
	public String queryCustomerGroupDetailListByCondition() throws SecurityException, IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException{
		Map<String, Object> map = new HashMap<String, Object>();
		//部门
		String deptId = this.getCurrentUser().getEmpCode().getDeptId().getId();
		//设置部门ID
		customerGroupDetail.setDeptId(deptId);
		//部门名称
		customerGroupDetail.setDeptName(this.getCurrentUser().getEmpCode().getDeptId().getDeptName());
		//根据条件查询分组信息
		map = customerGroupManager.getCustomerGroupDetailListByCondition(customerGroupDetail,start,limit);
		//查询结果
		listCustomerGroupDetail=(List<CustomerGroupDetail>) map.get("data");
		//循环处理
//		for(CustomerGroupDetail detail : listCustomerGroupDetail){
//			//发货次数为0
//			if (detail.getSendGoodCount() == 0) {
//				//设置发货周期为0
//				detail.setSendGoodCycle("0");
//				continue;
//			}
//			//计算发货周期
//			int cycle = 30 / detail.getSendGoodCount();
//			//设置发货周期
//			detail.setSendGoodCycle(String.valueOf(cycle));
//		}
		// 进行对象转换
//		if (listCustomerGroupDetail != null) {
//			//装换
//			listCustomerGroupDetail = dataConvert(listCustomerGroupDetail);
//		}
		//总记录数
		totalCount=Long.valueOf( map.get("count").toString());
		return SUCCESS;
	}
	
	/**
	 * 保存分组
	 * @return
	 */
	@JSON
	public String saveAllCustomerGroup(){		
		//保存客户分组
		customerGroupManager.saveCustomerGroup(customerGroupId, deletedListDetail, updatedListDetail, createdListDetail,this.getCurrentUser());
		return SUCCESS;
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
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public List<CustomerGroupDetail> dataConvert(
			List<CustomerGroupDetail> details) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
		//循环处理分组数据
		for (CustomerGroupDetail d : details) {
			//日发货金额
			List<String> dayAmount = d.getDayAmount();
			//类对象
	    	Class cl = Class.forName(Day.class.getName());
	    	//day 实例
			Day obj = (Day) cl.newInstance();
			//设置日期值
			for (int i = 0; i <dayAmount.size(); i++) {
				Method method1 = cl.getMethod("setDay"+(i+1), String.class);
				Object result = method1.invoke(obj, dayAmount.get(i));
			}
			d.setDays(obj);
		}
		return details;
	}
}
