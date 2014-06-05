package com.deppon.crm.module.customer.server.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.customer.server.dao.IBaseDataDao;
import com.deppon.crm.module.customer.server.utils.ContextUtil;
import com.deppon.crm.module.organization.shared.domain.Department;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;
import com.deppon.foss.framework.shared.util.string.StringUtil;

public class BaseDataDao extends iBatis3DaoImpl implements IBaseDataDao{
	
	private final static String NAME_SPACE = "com.deppon.crm.module.customer.server.dao.impl.BaseData.";
	
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.dao.IBaseDataDao#queryPointDepartment(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getBankCityByBankProvinceId(String provinceId) {
		return this.getSqlSession().selectList(NAME_SPACE+"getBankCityByBankProvinceId",provinceId);
	}
	
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.dao.IBaseDataDao#queryPointDepartment(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getBankProvince() {
		return this.getSqlSession().selectList(NAME_SPACE+"getBankProvince");
	}
	
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.dao.IBaseDataDao#queryPointDepartment(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> getAccountBank() {
		return this.getSqlSession().selectList(NAME_SPACE+"getAccountBank");
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.dao.IBaseDataDao#queryAuthrityBusnessDept(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Department> queryAuthrityBusnessDept(String standardCode) {
		Department dept = new Department();
		dept.setStandardCode(standardCode);
		return this.getSqlSession().selectList(NAME_SPACE+"queryAuthorityBusinessDept",dept);
	}
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.dao.IBaseDataDao#queryAuthorityBusinessDeptByName(java.lang.String, int, int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Department> queryAuthorityBusinessDeptByName(String deptName,
			int start, int limit) {
		Department dept = new Department();
		dept.setDeptName(deptName);
		dept.setStandardCode(ContextUtil.getCurrentUserDept().getStandardCode());
		return this.getSqlSession().selectList(
				NAME_SPACE + "queryAuthorityBusinessDept",dept,
				new RowBounds(start, limit));
	}
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.dao.IBaseDataDao#countAuthorityBusinessDeptByName(java.lang.String)
	 */
	@Override
	public int countAuthorityBusinessDeptByName(String deptName) {
		Department dept = new Department();
		dept.setDeptName(deptName);
		dept.setStandardCode(ContextUtil.getCurrentUserDept().getStandardCode());
		return (Integer)this.getSqlSession().selectOne(
				NAME_SPACE + "countAuthorityBusinessDept",dept);
	}
	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.dao.IBaseDataDao#queryPointDepartment(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Department> queryPointDepartment(String standardCode) {
		Department dept = new Department();
		dept.setStandardCode(standardCode);
		if(StringUtil.isEmpty(standardCode)){
		dept.setStandardCode(ContextUtil.getCurrentUserDept().getStandardCode());
		}
		return this.getSqlSession().selectList(
				NAME_SPACE + "queryPointDepartment",dept);
	}
}
