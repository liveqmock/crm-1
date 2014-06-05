/**
 * 营销记录action，主要实现客户来电的新增与营销历史记录的查询
 * 肖红叶 2012-11
 */
package com.deppon.crm.module.marketing.server.action;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.shared.domain.BussinessDept;
import com.deppon.crm.module.marketing.server.manager.ICustomerCallManager;
import com.deppon.crm.module.marketing.shared.domain.CustomerCallVO;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitOpinion;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitOpinionVo;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitVolumePotential;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitVolumePotentialVo;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;

public class MarketRecordAction  extends AbstractAction {
	//客户来电Manager
	private ICustomerCallManager customerCallManager;
	//客户实体对象
	private CustomerCallVO customerCallVO;
	//客户来电录入页面的客户需求列表
	private List<ReturnVisitOpinion> customerDemandList;
	//客户来电录入页面的走货潜力列表
	private List<ReturnVisitVolumePotential> volPotentialList;
	//手机号码
	private String mobile;
	//固定电话
	private String contactPhone;
	//联系人姓名
	private String custLinkName;
	//城市
	private String city;
	//城市ID
	private String cityId;
	// 起始页
	private int start;
	// 每页显示条数
	private int limit;
	//总条数
	private Long totalCount;
	//营销历史记录查询页面中的客户需求
	private ReturnVisitOpinionVo returnVisitOpinionVo;
	//营销历史记录查询页面中的客户需求列表
	private List<ReturnVisitOpinionVo> customerDemandsList;
	//营销历史记录查询页面中的走货潜力列表
	private List<ReturnVisitVolumePotentialVo> sendGoodsPotentialList;
	
	//get、set方法
	/**
	 * @param customerCallManager : set the property customerCallManager
	 */
	public void setCustomerCallManager(ICustomerCallManager customerCallManager) {
		this.customerCallManager = customerCallManager;
	}
	/**
	 * @param customerDemandList : set the property customerDemandList
	 */
	public void setCustomerDemandList(List<ReturnVisitOpinion> customerDemandList) {
		this.customerDemandList = customerDemandList;
	}
	/**
	 * @param volPotentialList : set the property volPotentialList
	 */
	public void setVolPotentialList(
			List<ReturnVisitVolumePotential> volPotentialList) {
		this.volPotentialList = volPotentialList;
	}
	/**
	 * @return customerCallVO : get the property customerCallVO
	 */
	public CustomerCallVO getCustomerCallVO() {
		return customerCallVO;
	}
	/**
	 * @param customerCallVO : set the property customerCallVO
	 */
	public void setCustomerCallVO(CustomerCallVO customerCallVO) {
		this.customerCallVO = customerCallVO;
	}
	/**
	 * @param mobile : set the property mobile
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * @param custLinkName : set the property custLinkName
	 */
	public void setCustLinkName(String custLinkName) {
		this.custLinkName = custLinkName;
	}
	/**
	 * @param contactPhone : set the property contactPhone
	 */
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	/**
	 * @param city : set the property city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @return sendGoodsPotentialList : get the property sendGoodsPotentialList
	 */
	public List<ReturnVisitVolumePotentialVo> getSendGoodsPotentialList() {
		return sendGoodsPotentialList;
	}
	/**
	 * @return customerDemandsList : get the property customerDemandsList
	 */
	public List<ReturnVisitOpinionVo> getCustomerDemandsList() {
		return customerDemandsList;
	}
	/**
	 * @return returnVisitOpinionVo : get the property returnVisitOpinionVo
	 */
	public ReturnVisitOpinionVo getReturnVisitOpinionVo() {
		return returnVisitOpinionVo;
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
	 * @return cityId : get the property cityId
	 */
	public String getCityId() {
		return cityId;
	}

	/**
	 * Description:根据手机号和部门ID查询客户信息
	 * @author 肖红叶
	 * @version 2012-11-07
	 * @return customerCallVO
	 */
	@JSON
	public String queryCustomerByMobile(){
		User user=(User)UserContext.getCurrentUser();
		//调用manager方法
		customerCallVO=customerCallManager.queryCustomerByMobileAndDeptId(mobile,user.getEmpCode().getDeptId().getId());
		return SUCCESS;
	}
	
	
	/**
	 * Description:根据固话、联系人姓名和部门ID查询客户信息
	 * @author 肖红叶
	 * @version 2012-11-07
	 * @return customerCallVO
	 */
	@JSON
	public String queryCustomerByPhoneDeptIdName(){
		//当前用户
		User user=(User)UserContext.getCurrentUser();
		//调用manager方法
		customerCallVO=customerCallManager.queryCustomerByPhoneDeptIdName(contactPhone,custLinkName,
				user.getEmpCode().getDeptId().getId());
		return SUCCESS;
	}
	
	
	/**
	 * Description:根据当前登录用户查找部门所在城市和城市ID
	 * @author 肖红叶
	 * @version 2012-11-07
	 * @return customerCallVO
	 */
	@JSON
	public String queryDepartmentWithCurrentUser(){
		User user=(User)UserContext.getCurrentUser();
		//调用manager方法
		BussinessDept dept = customerCallManager.queryDepartmentWithCurrentUser(user);
		if(dept != null && dept.getCity() != null){
			//城市名称
			city =dept.getCity().getName();
			//城市ID
			cityId =dept.getCity().getId();
		}
		else{
			//城市名称
			city = null;
			//城市ID
			cityId = null;
		}
		return SUCCESS;
	}
	
	
	/**
	 * Description:保存营销记录,当营销类型为陌生来电时创建本部门下新的潜散客信息
	 * @author 肖红叶
	 * @version 2012-11
	 * @return
	 */
	@JSON
	public String saveMarketRecord(){
		//当前用户
		User user=(User)UserContext.getCurrentUser();
		//保存数据
		customerCallManager.processMarketData(customerCallVO,customerDemandList ,volPotentialList , user);
		return SUCCESS;
	}
	
	
	/**
	 * Description:营销历史记录之客户需求列表
	 * @author 肖红叶
	 * @version 2012-11
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@JSON
	public String queryCustomerDemands(){
		//当前用户
		User user=(User)UserContext.getCurrentUser();
		Map<String, Object> map = customerCallManager.queryRvOptionByMarketingIds(customerCallVO,  user, start, limit);
		//获取客户需求记录列表
		customerDemandsList = (List<ReturnVisitOpinionVo>)map.get("data");
		//获取分页总条数
		totalCount = Long.valueOf( map.get("count").toString());
		return SUCCESS;
	}
	
	/**
	 * Description:营销历史记录之走货潜力列表
	 * @author 肖红叶
	 * @version 2012-11
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@JSON
	public String querySendGoodsPotentials(){
		User user=(User)UserContext.getCurrentUser();
		Map<String, Object> map = customerCallManager.queryRvPotentialByMarketingIds(customerCallVO,  user, start, limit);
		//获取走货潜力记录列表
		sendGoodsPotentialList = (List<ReturnVisitVolumePotentialVo>)map.get("data");
		//获取分页总条数
		totalCount = Long.valueOf( map.get("count").toString());
		return SUCCESS;
	}
	
}
