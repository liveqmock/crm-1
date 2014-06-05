package com.deppon.crm.module.uums.server.dao.Imp;

import java.util.Map;

import com.deppon.crm.module.uums.server.dao.IEmployeeDao;
import com.deppon.crm.module.uums.shared.domain.CompanyInfo;
import com.deppon.crm.module.uums.shared.domain.EmployeeInfo;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

public class EmployeeDaoImp extends iBatis3DaoImpl implements IEmployeeDao{
	private final String NAMESPACE="com.deppon.crm.module.uums.shared.domain.Employee.";
	private final String INSERTSQL="insert";
	private final String UPDATEByCodeSQL="UpdateByCode";
	private final String UPDATEByIdSQL="UpdateById";
	private final String DELETEByCodeSQL="DeleteByCode";
	private final String DELETEByIdSQL="DeleteById";
	private final String SELECTByCodeSQL="selectEmployeeByCode";
	private final String SELECTByIdSQL="selectEmployeeById";
	private final String REUSEByCodeSQL="ReturnByCode";
	private final String REUSEByIdSQL="ReturnByID";
	@Override
	public int insert(EmployeeInfo entity) {
		int res=this.getSqlSession().insert(NAMESPACE+INSERTSQL, entity);
		return res;
	}

	@Override
	public int updateById(EmployeeInfo entity, String employeeID) {
		int res=this.getSqlSession().update(NAMESPACE+UPDATEByIdSQL, entity);
		return res;
	}

	@Override
	public int updateByCode(EmployeeInfo entity, String employeeCode) {
		int res=this.getSqlSession().update(NAMESPACE+UPDATEByCodeSQL, entity);
		return res;
	}

	@Override
	public int deleteById(String employeeId) {
		int res=this.getSqlSession().delete(NAMESPACE+DELETEByIdSQL, employeeId);
		return res;	
	}

	@Override
	public int deleteByCode(Map<String,String> empMap) {
		int res=this.getSqlSession().delete(NAMESPACE+DELETEByCodeSQL, empMap);
		return res;	
	}

	@Override
	public EmployeeInfo searchById(String employeeId) {
		EmployeeInfo employeeInfo=(EmployeeInfo)this.getSqlSession().selectOne(NAMESPACE+SELECTByIdSQL, employeeId);
		return employeeInfo;
	}

	@Override
	public EmployeeInfo searchByCode(String employeeCode) {
		EmployeeInfo employeeInfo=(EmployeeInfo)this.getSqlSession().selectOne(NAMESPACE+SELECTByCodeSQL, employeeCode);
		return employeeInfo;
	}

	@Override
	public int reuseByCode(EmployeeInfo entity) {
		int res=this.getSqlSession().update(NAMESPACE+REUSEByCodeSQL,entity);
		return res;
	}

	@Override
	public int reuseById(String employeeId) {
		int res=this.getSqlSession().update(NAMESPACE+REUSEByIdSQL,employeeId);
		return res;
	}
}
