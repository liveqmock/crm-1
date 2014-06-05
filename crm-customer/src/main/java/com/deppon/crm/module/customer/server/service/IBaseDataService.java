package com.deppon.crm.module.customer.server.service;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.organization.shared.domain.Department;
/**
 * 
 * <p>
 * Description:基础数据service<br />
 * </p>
 * @title IBaseDataService.java
 * @package com.deppon.crm.module.customer.server.service 
 * @author 106138
 * @version 0.1 2014年4月11日
 */
public interface IBaseDataService {

	/**
	 * <p>
	 * 通过开户省份查询开户城市信息<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-6-11
	 * @param bankprovince
	 * @return
	 * List<Map<String,String>>
	 */
	public List<Map<String,String>> getBankCityByBankProvinceId(String bankprovince);
	
	/**
	 * <p>
	 * 得到开户支行信息<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-6-11
	 * @return
	 * List<Map<String,String>>
	 */
	public List<Map<String,String>> getBankProvince();
	
	/**
	 * <p>
	 * 得到开户银行信息<br />
	 * </p>
	 * @author bxj
	 * @version 0.2 2012-6-11
	 * @return
	 * List<Map<String,String>>
	 */
	public List<Map<String,String>> getAccountBank();

	/**
	 * <p>
	 * Description:获取快递经理拥有的营业部数据权限<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2013-9-20
	 * @param standardCode
	 * @return
	 * List<String>
	 */
	public List<Department> searchAuthorityBusinessDept(String standardCode);

	/**
	 * <p>
	 * Description:根据名称分页查询快递经理对应营业部权限<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2013-9-20
	 * @param deptName
	 * @param start
	 * @param limit
	 * @return
	 * List<Department>
	 */
	public List<Department> searchAuthorityBusinessDeptByName(String deptName,
			int start, int limit);

	/**
	 * <p>
	 * Description:快递经理对应营业部权限总条数<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2013-9-20
	 * @param deptName
	 * @return
	 * int
	 */
	public int countAuthorityBusinessDeptByName(String deptName);

	/**
	 * <p>
	 * Description:根据营业部标杆编码查询点部<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2013-9-24
	 * @param standardCode
	 * @return
	 * List<Department>
	 */
	public List<Department> searchPointDepartment(String standardCode);
}
