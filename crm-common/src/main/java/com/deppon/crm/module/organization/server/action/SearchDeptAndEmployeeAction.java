package com.deppon.crm.module.organization.server.action;

import java.util.ArrayList;
import java.util.List;

import com.deppon.crm.module.login.server.action.UserInfo;
import com.deppon.crm.module.organization.server.manager.ISearchDeptAndEmployeeManager;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.crm.module.organization.shared.domain.SearchEmpAndDeptCondition;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.message.IMessageBundle;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;
import com.deppon.foss.framework.shared.util.string.StringUtil;
/**.
 * 
 *<p>作 者：张斌 最后修改时间：2012年5月23日 描 述：
 * 通过条件查询部门和职员相关信息
 * </p>
 */
public class SearchDeptAndEmployeeAction extends AbstractAction{
	//-----------------只需要get的数据（JSON 通过get方法将ACTION中的数据转换为JSON传给前台）---------------------------------------------
	//查询出的部门列表
	private List<Department> depts;
	public List<Department> getDepts() {
		return depts;
	}
	//查询出的职员列表
    public List<UserInfo> users;
	public List<UserInfo> getUsers() {
		return users;
	}
	//查询总个数
	public Long totalCount;
	public Long getTotalCount() {
		return totalCount;
	}
	//-----------------只需要set的数据(JSON 通过set方法将前台传来的值设置到ACTION中)---------------------------------------------
	//manager层
	private ISearchDeptAndEmployeeManager searchDeptAndEmployeeManager;
	public void setSearchDeptAndEmployeeManager(
			ISearchDeptAndEmployeeManager searchDeptAndEmployeeManager) {
		this.searchDeptAndEmployeeManager = searchDeptAndEmployeeManager;
	}
	//部门名称
	private String deptName;
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	//查询职员的条件
	private SearchEmpAndDeptCondition searchEmpAndDeptCondition;
	public SearchEmpAndDeptCondition getSearchEmpAndDeptCondition() {
		return searchEmpAndDeptCondition;
	}
	public void setSearchEmpAndDeptCondition(
			SearchEmpAndDeptCondition searchEmpAndDeptCondition) {
		this.searchEmpAndDeptCondition = searchEmpAndDeptCondition;
	}
	//分页参数
	private int limit;
	private int start;
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public void setStart(int start) {
		this.start = start;
	}
	// 注入国际化类messageBundle
	private IMessageBundle messageBundle;
	
	/**
	 * @param messageBundle the messageBundle to set
	 */
	public void setMessageBundle(IMessageBundle messageBundle) {
		this.messageBundle = messageBundle;
	}
	//-----------------需要get和set的数据---------------------------------------------
	/**.
	 * <p>
	 * 根据部门名称查询部门列表<br/>
	 * 方法名：searchDeptByDeptName
	 * </p>
	 * @author 张斌
	 * @时间 2012-5-23
	 * @since JDK1.6
	 */
	@JSON
	public String searchDeptByDeptName() {
		if(StringUtil.isEmpty(deptName)){
			depts = new ArrayList<Department>();
			totalCount = (long)1;
		}else{
			depts = searchDeptAndEmployeeManager.searchDeptByName(deptName,start,limit);
			totalCount = searchDeptAndEmployeeManager.countDeptByName(deptName);
		}
		if(totalCount==null||totalCount==0){
			totalCount=(long)1;
		}
		return SUCCESS;
	}
	
	/**.
	 * <p>
	 * 根据部门名称查询职员列表<br/>
	 * 方法名：searchEmpByCondition
	 * </p>
	 * @author 张斌
	 * @时间 2012-5-23
	 * @since JDK1.6
	 */
	@JSON
	public String searchEmpByCondition() {
		users = searchDeptAndEmployeeManager.searchEmpByCondition(searchEmpAndDeptCondition,start,limit);
		totalCount = searchDeptAndEmployeeManager.countEmpByCondition(searchEmpAndDeptCondition);
		if(totalCount==null||totalCount==0){
			totalCount=(long)1;
		}
		return SUCCESS;
	}
	
}
