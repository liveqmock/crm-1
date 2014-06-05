package com.deppon.crm.module.authorization.server.service;

import java.util.List;

import com.deppon.crm.module.authorization.shared.domain.Function;
import com.deppon.foss.framework.service.IService;

/**
 * 功能查询业务逻辑
 * 
 * @author钟庭杰
 * 
 */
public interface IFunctionService extends IService {
	/**
	 * 修改功能对象
	 * @param function 功能对象
	 */
	void update(Function function);
	
	/**
	 * 得到指定ID的对象
	 */
	Function queryById(String id);
	
	/**
	 * 通过功能编码得到功能对象
	 * @param functionCode
	 * @return
	 */
	Function queryByFunctionCode(String functionCode);
	
	/**
	 * 查询所有功能对象
	 * @return
	 */
	List<Function> queryAll();

	/**
	 * 查询所有功能对象
	 * @param function 查询功能对象信息的条件
	 * @return
	 */
	List<Function> queryAll(Function function);

	/**
	 * 分页查询所有的功能对象
	 * @param function 查询功能对象信息的条件
	 * @param limit    分页最大记录数
	 * @param start	        分页开始记录数
	 * @return
	 */
	List<Function> queryAll(Function function, int limit, int start);
	
	/**
	 * 通过ID获得当前ID所对应的所有的子节点
	 * @param id
	 * @return
	 */
	List<Function>  queryAllChildFunctionById(String id);

	/**
	 * 保存功能对象
	 * 
	 * @param function 功能对象
	 */
	void save(Function function);

	/**
	 * 修改功能对象
	 * 
	 * @param function 功能对象
	 * @param createFunctionPageEletmens 创建的页面元素权限信息
	 * @param updateFunctionPageEletmens 待更新的页面元素权限信息
	 * @param deleteFunctionPageEletmens 删除的页面元素权限信息
	 */
	void update(Function function, List<Function> createFunctionPageEletmens,
			List<Function> updateFunctionPageEletmens, List<Function> deleteFunctionPageEletmens);

	/**
	 * 通过ID删除功能对象
	 * @param id 功能对象的ID
	 */
	void remove(String id);

	/**
	 * 统计功能对象数量
	 * @param function 查询功能对象信息的条件
	 * @return
	 */
	Long count(Function function);

	/**
	 * 查找属于该功能的子功能
	 * @param function 查询功能对象信息的条件
	 * 			功能对象信息条件：functionType  待查询功能的功能类型不为条件指定的值
	 * 							 parentCode   父功能的条件信息
	 * @return
	 */
	List<Function> queryDirectChildFunctions(Function function);

	/**
	 * 通过角色ID查询功能对象集合
	 * @param roleId
	 * @return
	 */
	List<Function> queryAllFunctionByRoleId(String roleId);

	/**
	 * 通过角色ID查询功能对象ID集合
	 * @param roleId
	 * @return
	 */
	List<String> queryAllFunctionIdByRoleId(String roleId);

	List<Function> getFunctionByParentCode(String parentCode);

}
