package com.deppon.crm.module.customer.server.service.impl;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.customer.server.dao.IBaseDataDao;
import com.deppon.crm.module.customer.server.service.IBaseDataService;
import com.deppon.crm.module.organization.shared.domain.Department;

public class BaseDataService implements IBaseDataService{
	
	private IBaseDataDao baseDataDao;
	
	public void setBaseDataDao(IBaseDataDao baseDataDao) {
		this.baseDataDao = baseDataDao;
	}

	@Override
	public List<Map<String, String>> getBankCityByBankProvinceId(
			String bankprovince) {
		return baseDataDao.getBankCityByBankProvinceId(bankprovince);
	}

	@Override
	public List<Map<String, String>> getBankProvince() {
		return baseDataDao.getBankProvince();
	}

	@Override
	public List<Map<String, String>> getAccountBank() {
		return baseDataDao.getAccountBank();
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.service.IBaseDataService#searchAuthorityBusinessDept(java.lang.String)
	 */
	@Override
	public List<Department> searchAuthorityBusinessDept(String standardCode) {
		return baseDataDao.queryAuthrityBusnessDept(standardCode);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.service.IBaseDataService#searchAuthorityBusinessDeptByName(java.lang.String, int, int)
	 */
	@Override
	public List<Department> searchAuthorityBusinessDeptByName(String deptName,
			int start, int limit) {
		return baseDataDao.queryAuthorityBusinessDeptByName(deptName,
				start,limit);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.service.IBaseDataService#countAuthorityBusinessDeptByName(java.lang.String)
	 */
	@Override
	public int countAuthorityBusinessDeptByName(String deptName) {
		return baseDataDao.countAuthorityBusinessDeptByName(deptName);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.service.IBaseDataService#searchPointDepartment(java.lang.String)
	 */
	@Override
	public List<Department> searchPointDepartment(String standardCode) {
		return baseDataDao.queryPointDepartment(standardCode);
	}

	
}
