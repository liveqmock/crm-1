package com.deppon.crm.module.authorization.server.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.authorization.server.dao.IFunctionDao;
import com.deppon.crm.module.authorization.shared.domain.Function;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;
/**
 * 
 *作        者：钟庭杰
 *最后修改时间：2011年10月20日
 *描        述： T_ORG_FUNCTION信息DAO层实现
 */
public class FunctionDao extends iBatis3DaoImpl implements IFunctionDao {
	/**
	 * 查询所有功能
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Function> getAll() {
		List<Function> allFunctions = getSqlSession().selectList("com.deppon.crm.module.authorization.shared.domain.Function.getAll",new Function());
		return allFunctions;
	}
	/**
	 * 查询所有功能
	 * @param function 查询条件信息类
	 * @return 功能对象List
	 */
	@SuppressWarnings("unchecked")
	public List<Function> getAll(Function function) {
		List<Function> list = null;
		Function model = createParam(function);
		list = getSqlSession().selectList("com.deppon.crm.module.authorization.shared.domain.Function.getAll",model);
		return list;
	}

	public List<Function> getFunctionByParentCode(String parentCode) {
		List<Function> list = null;
		list = getSqlSession()
				.selectList(
						"com.deppon.crm.module.authorization.shared.domain.Function.getFunctionByParentCode",
						parentCode);
		return list;
	}

	/**
	 * 分页查询所有功能
	 * @param function 查询条件信息类
	 * @param limit 查询条数
	 * @param start 查询的起始位置
	 * @return 功能对象List
	 */
	@SuppressWarnings("unchecked")
	public List<Function> getAll(Function function, int limit, int start) {
		RowBounds rowBounds = new RowBounds(start, limit);
		List<Function> list = null;
		Function model = createParam(function);
		list = getSqlSession().selectList("com.deppon.crm.module.authorization.shared.domain.Function.getAll",model, rowBounds);
		return list;
	}
	/**
	 * 查找属于该功能的子功能
	 * @param function 功能查找信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Function> getDirectChildFunctions(Function function) {
		List<Function> list = null;
		Function model = createParam(function);
		list = getSqlSession().selectList("com.deppon.crm.module.authorization.shared.domain.Function.getDirectChildFunctions",model);
		return list;
	}
	
	/**
	 * 通过角色ID，得到功能对象集合
	 * @param roleId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Function> getAllByRoleId(String roleId) {
		List<Function> list = null;
		list = getSqlSession().selectList("com.deppon.crm.module.authorization.shared.domain.Function.getAllByRoleId",roleId);
		return list;
	}
	/**
	 * 通过角色ID，得到功能对象ID集合
	 * @param roleId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> getAllIdByRoleId(String roleId) {
		List<String> list = null;
		list = getSqlSession().selectList("com.deppon.crm.module.authorization.shared.domain.RoleFunction.getAllIdByRoleId",roleId);
		return list;
	}
	/**
	 * 更新功能信息
	 * @param function 功能信息
	 */
	public void update(Function function) {
		getSqlSession()
				.update("com.deppon.crm.module.authorization.shared.domain.Function.update",
						function);
	}
	
	
	/**
	 * 更新功能信息
	 * @param function 功能信息
	 */
	public void updateChild(Function functionChild) {
		getSqlSession()
				.update("com.deppon.crm.module.authorization.shared.domain.Function.updateChild",
						functionChild);
	}
	/**
	 * 通过自增长序列得到序列的值
	 * @return newId
	 */
	public String getNewId() {
		String newId = (String) getSqlSession().selectOne(
						"com.deppon.crm.module.authorization.shared.domain.Function.getNewId");
		return newId;
	}
	/**
	 * 保存功能信息
	 * @param function 功能信息
	 */
	public void insert(Function function) {
		getSqlSession()
				.insert("com.deppon.crm.module.authorization.shared.domain.Function.insert",
						function);
	}
	/**
	 * 通过ID，得到功能对象
	 * @param id
	 * @return
	 */
	public Function getById(String id) {
		Function function = (Function) getSqlSession()
				.selectOne(
						"com.deppon.crm.module.authorization.shared.domain.Function.getById",
						id);
		return function;
	}
	/**
	 * 通过功能编码得到功能对象
	 * @param functionCode
	 * @return
	 */
	public Function getByCode(String functionCode) {
		Function function = (Function) getSqlSession()
		.selectOne(
				"com.deppon.crm.module.authorization.shared.domain.Function.getByCode",
				functionCode);
		return function;
	}
	/**
	 * 功能一些功能ID得到一个功能对象的集合
	 * @param functionIds
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Function> getByIds(List<String> functionIds) {
		List<Function> functions = getSqlSession().selectList("com.deppon.crm.module.authorization.shared.domain.Function.getByIds",
				functionIds);
		return functions;
	}
	/**
	 * 得到功能树的根节点
	 */
	public Function getRoot() {
		return (Function) getSqlSession()
				.selectOne(
						"com.deppon.crm.module.authorization.shared.domain.Function.getRoot");
	}
	/**
	 * 统计功能的条数
	 * @param function
	 * @return
	 */
	public Long count(Function function) {
		Function model = createParam(function);
		Long count = (Long) getSqlSession()
				.selectOne(
						"com.deppon.crm.module.authorization.shared.domain.Function.count",
						model);
		return count;
	}
	/**
	 * 查询最后更新时间
	 * @return
	 */
	public Date getLastModifyTime() {
		Date lastModyfyTime = (Date) getSqlSession()
				.selectOne(
						"com.deppon.crm.module.authorization.shared.domain.Function.getLastModifyTime");
		return lastModyfyTime;
	}
	/**
	 * 通过URI得到当前URL定位的功能树下的所有功能
	 * @param URI
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Collection<Function> getAllChildFunctionByURI(String URI) {
		Collection<Function> collection = new ArrayList<Function>();
		collection = getSqlSession()
				.selectList(
						"com.deppon.crm.module.authorization.shared.domain.Function.getAllChildFunctionByURI",
						URI);
		return collection;
	}
	/**
	 * 通过功能编码得到当前功能编码定位的功能下的所有功能
	 * @param functionCode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Collection<Function> getAllChildFunctionByCode(String functionCode) {
		Collection<Function> collection = new ArrayList<Function>();
		collection = getSqlSession()
				.selectList(
						"com.deppon.crm.module.authorization.shared.domain.Function.getAllChildFunctionByCode",
						functionCode);
		return collection;
	}
	/**
	 * 通过ID获得当前ID所对应的所有的子节点
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Collection<Function> getAllChildFunctionById(String id) {
		Collection<Function> collection = new ArrayList<Function>();
		collection = getSqlSession()
				.selectList(
						"com.deppon.crm.module.authorization.shared.domain.Function.getAllChildFunctionById",
						id);
		return collection;
	}
	/**
	 * 通过父功能编码得到所有子功能中编码值最大的功能对象
	 * @param parentCode 父节点编码
	 */
	public Function getMaxCodeFunctionByParentCode(String parentCode) {
		Function function = (Function) getSqlSession()
				.selectOne(
						"com.deppon.crm.module.authorization.shared.domain.Function.getMaxCodeFunctionByParentCode",
						parentCode);
		return function;
	}
	/**
	 * 通过ID删除功能信息
	 * @param id
	 */
	public void deleteById(String id) {
		getSqlSession()
				.delete("com.deppon.crm.module.authorization.shared.domain.Function.deleteById",
						id);
	}
	/**
	 * 删除功能信息集合中的所有功能信息
	 * @param functionList 功能信息集合
	 */
	public void deleteFunctions(List<Function> functionList) {
		List<String> functionIds = new ArrayList<String>();
		for(Function function : functionList){
			functionIds.add(function.getId());
		}
		getSqlSession().delete("com.deppon.crm.module.authorization.shared.domain.Function.deleteByIds",functionIds);
	}
	/**
	 * 通过父功能ID删除父功能下的所有子功能
	 * @param parentId
	 */
	public void deleteByParentId(String parentId) {
		getSqlSession()
		.delete("com.deppon.crm.module.authorization.shared.domain.Function.deleteByParentId",
				parentId);
	}
	/**
	 * 通过ID删除功能信息
	 * @param id
	 */
	public void deleteByIds(List<String> functionIds) {
		getSqlSession()
				.delete("com.deppon.crm.module.authorization.shared.domain.Function.deleteByIds",
						functionIds);

	}
	
	/**
	 * 构造查询参数
	 * @param function
	 * @return
	 */
	private Function createParam(Function function) {
		Function model = new Function();
		if (function != null) {
			if (function.getId() != null && !"".equals(function.getId())) {
				String id = "%" + function.getId() + "%";
				model.setId(id);
			}
			if (function.getFunctionCode() != null
					&& !"".equals(function.getFunctionCode())) {
				String functionCode = "%" + function.getFunctionCode() + "%";
				model.setFunctionCode(functionCode);
			}
			if (function.getFunctionName() != null
					&& !"".equals(function.getFunctionName())) {
				String functionName = "%" + function.getFunctionName() + "%";
				model.setFunctionName(functionName);
			}
			if (function.getUri() != null && !"".equals(function.getUri())) {
				String URI = "%" + function.getUri() + "%";
				model.setUri(URI);
			}
			model.setFunctionLevel(function.getFunctionLevel());
			model.setParentCode(function.getParentCode());
			model.setInvalidDate(function.getInvalidDate());
			model.setValidDate(function.getValidDate());
			model.setDisplayOrder(function.getDisplayOrder());
			model.setCheck(function.getCheck());
			model.setFunctionType(function.getFunctionType());
			if (function.getFunctionDesc() != null
					&& !"".equals(function.getFunctionDesc())) {
				String functionDesc = "%" + function.getFunctionDesc() + "%";
				model.setFunctionDesc(functionDesc);
			}
			if (function.getFunctionSeq() != null
					&& !"".equals(function.getFunctionSeq())) {
				String functionSeq = "%" + function.getFunctionSeq() + "%";
				model.setFunctionSeq(functionSeq);
			}
		}
		return model;
	}
	
	/**
	 * 通过用户ID，得到功能对象ID集合
	 * @param roleId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> getAllIdByUserId(String userId) {
		List<String> list = null;
		list = getSqlSession().selectList("com.deppon.crm.module.authorization.shared.domain.RoleFunction.getAllIdByUserId",userId);
		return list;
	}

}
