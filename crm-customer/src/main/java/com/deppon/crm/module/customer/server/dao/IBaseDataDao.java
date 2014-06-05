package com.deppon.crm.module.customer.server.dao;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.organization.shared.domain.Department;

/**   
 * <p>
 * Description:crm 银行身份城市 基础资料的dao<br />
 * </p>
 * @title IBaseDataDao.java
 * @package com.deppon.crm.module.customer.server.dao 
 * @author 李国文
 * @version 0.1 2013-2-26
 */

public interface IBaseDataDao {
	
	/**
	 * 
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
	 * 
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
	 * 
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
	 * Description:根据点部标杆编码获取对应的营业部id<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2013-9-20
	 * @param standardCode
	 * @return
	 * List<String>
	 */
	public List<Department> queryAuthrityBusnessDept(String standardCode);

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
	public List<Department> queryAuthorityBusinessDeptByName(String deptName,
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
	public List<Department> queryPointDepartment(String standardCode);
}
