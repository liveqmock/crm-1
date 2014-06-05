package com.deppon.crm.module.authorization.server.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.authorization.server.dao.IFunctionDao;
import com.deppon.crm.module.authorization.server.service.IFunctionService;
import com.deppon.crm.module.authorization.shared.domain.Function;
import com.deppon.crm.module.authorization.shared.exception.FunctionException;
import com.deppon.crm.module.authorization.shared.exception.FunctionExceptionType;
import com.deppon.crm.module.authorization.shared.exception.RoleException;
import com.deppon.crm.module.authorization.shared.exception.RoleExceptionType;
import com.deppon.foss.framework.entity.IUser;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.foss.framework.server.context.UserContext;
/**
 * 
 *作        者：张斌
 *最后修改时间：2011年10月20日
 *描        述： T_ORG_FUNCTION信息SERVICE层（修改功能对象（禁用/启用功能））
 */
@Transactional
public class FunctionService implements IFunctionService {

	private IFunctionDao functionDao;
	/**
	 * 修改功能对象
	 * @param function 待更新的页面元素权限信息
	 */
	@SuppressWarnings("serial")
	public void update(Function function) {
		if(function!=null){
			// 验证功能是否有效
			   if (function.getValidFlag() == null) {
					FunctionException e = new FunctionException(
							FunctionExceptionType.FunctionValidFlagNullError);
					throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
							e.getErrorArguments()) {
					};
			   }
				// 校验待修改的功能对象ID
				if (function.getId() == null || "".equals(function.getId())) {
					FunctionException e = new FunctionException(
							FunctionExceptionType.FunctionIdNull);
					throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
							e.getErrorArguments()) {
					};
				}
				if(function.getValidFlag()){
					Function functionParent=functionDao.getByCode(function.getParentCode().getFunctionCode());
					if(!functionParent.getValidFlag()){
						FunctionException e = new FunctionException(
								FunctionExceptionType.ParentEnd);
						throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
								e.getErrorArguments()) {
						};
					}
				}
				// 得到功能对象
				IUser user = UserContext.getCurrentUser();
				Function functionChild = new Function();
				function.setModifyDate(new Date());
				function.setModifyUser(user.getId());
				
				functionChild.setModifyDate(new Date());
				functionChild.setModifyUser(user.getId());
				functionChild.setParentCode(function);
				functionChild.setValidFlag(function.getValidFlag());
				if(function.getValidFlag()){
					function.setValidDate(new Date());
					function.setInvalidDate(null);
					functionChild.setValidDate(new Date());
					functionChild.setInvalidDate(null);
				}else{		
					function.setValidDate(null);
					function.setInvalidDate(new Date());
					functionChild.setValidDate(null);
					functionChild.setInvalidDate(new Date());	
				}
				functionDao.update(function);
				functionDao.updateChild(functionChild);
		}
			// 功能类型是否是模块功能、功能类型的功能页面元素集合为空或集合长度为0
//			if (updateFunction!=null ) {
//				if(updateFunction.getId()!=null){
//					 Function dbFunction = functionDao.getById(updateFunction.getId());
//					 if(updateFunction.getValidFlag()!=null){
//						 dbFunction.setValidFlag(updateFunction.getValidFlag());	
//							functionDao.update(dbFunction);
//					 }else{
//						 throw new FunctionException(
//							      FunctionExceptionType.FunctionValidFlagNullError);
//					 }
//		               
//				}else{
//					throw new FunctionException(
//						      FunctionExceptionType.FunctionIdNull);
//				}
//			}			
	}
/*--------------------------------------------备用-------------------------------*/
	
	/**
	 * 得到指定ID的对象
	 */
	@SuppressWarnings("serial")
	@Transactional(readOnly = true)
	public Function queryById(String id) {
		if (id == null || "".equals(id)) {
			FunctionException e = new FunctionException(
					FunctionExceptionType.FunctionIdNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					e.getErrorArguments()) {
			};
		}
		return functionDao.getById(id);
	}
	/**
	 * 通过功能编码得到功能对象
	 * @param functionCode
	 * @return
	 */
	@SuppressWarnings("serial")
	public Function queryByFunctionCode(String functionCode) {
		if (functionCode == null || "".equals(functionCode)) {
			FunctionException e = new FunctionException(
					FunctionExceptionType.FunctionCodeNullError);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					e.getErrorArguments()) {
			};
		}
		return functionDao.getByCode(functionCode);
	}
	/**
	 * 查询所有功能对象
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Function> queryAll() {
		return functionDao.getAll();
	}
	/**
	 * 查询所有功能对象
	 * @param function 查询功能对象信息的条件
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Function> queryAll(Function function) {
		return functionDao.getAll(function);
	}
	/**
	 * 分页查询所有的功能对象
	 * @param function 查询功能对象信息的条件
	 * @param limit    分页最大记录数
	 * @param start	        分页开始记录数
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Function> queryAll(Function function, int limit, int start) {
		return functionDao.getAll(function, limit, start);
	}

	/**
	 * 通过角色ID查询功能对象集合
	 * @param roleId
	 * @return
	 */
	public List<Function> queryDirectChildFunctions(Function function) {
		List<Function> functions = functionDao
				.getDirectChildFunctions(function);
		return functions;
	}
	
	/**
	 * 通过角色ID查询功能对象集合
	 * @param roleId
	 * @return
	 */
	@SuppressWarnings("serial")
	public List<Function> queryAllFunctionByRoleId(String roleId) {
		if (roleId == null || "".equals(roleId)) {
			RoleException e = new RoleException(
					RoleExceptionType.RoleIdNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					e.getErrorArguments()) {
			};
		}
		List<Function> functions = functionDao.getAllByRoleId(roleId);
		return functions;
	}
	/**
	 * 通过角色ID查询功能对象ID集合
	 * @param roleId
	 * @return
	 */
	@SuppressWarnings("serial")
	public List<String> queryAllFunctionIdByRoleId(String roleId) {
		if (roleId == null || "".equals(roleId)) {
			RoleException e = new RoleException(
					RoleExceptionType.RoleIdNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					e.getErrorArguments()) {
			};
		}
		List<String> functions = functionDao.getAllIdByRoleId(roleId);
		return functions;
	}
	/**
	 * 通过ID获得当前ID所对应的所有的子节点
	 * @param id
	 * @return
	 */
	@SuppressWarnings("serial")
	public List<Function> queryAllChildFunctionById(String id) {
		if (id == null || "".equals(id)) {
			FunctionException e = new FunctionException(
					FunctionExceptionType.FunctionIdNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					e.getErrorArguments()) {
			};
		}
		List<Function> functions = (List<Function>) functionDao.getAllChildFunctionById(id);
		return functions;
	}

	/**
	 * 通过ID删除功能对象
	 * @param id 功能对象的ID
	 */
	@SuppressWarnings({ "serial" })
	public void remove(String id) {
		if (id == null || "".equals(id)) {
			FunctionException e = new FunctionException(
					FunctionExceptionType.FunctionIdNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					e.getErrorArguments()) {
			};
		}
		// 删除id对应的功能下的所有子功能
		functionDao.deleteByParentId(id);
		functionDao.deleteById(id);
	}

	/**
	 * 统计功能对象数量
	 * @param function 查询功能对象信息的条件
	 * @return
	 */
	public Long count(Function function) {
		return functionDao.count(function);
	}
	/**
	 * 保存功能对象
	 * 
	 * @param function 功能对象
	 */
	@SuppressWarnings("serial")
	public void save(Function function) {
		try {
			// 校验功能对象属性
			validateFunctionField(function);
			// 通过序列得到ID值,前把ID设置给待新增的功能对象
			function.setId(functionDao.getNewId());
		} catch (FunctionException e) {
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					e.getErrorArguments()) {
			};
		}
	
		try {
			// 设置功能对象的一些属性值
			setSaveFunctionFields(function, function.getParentCode());
			functionDao.insert(function);
			// 功能类型是否是模块功能、功能类型的功能页面元素集合为空或集合长度为0
			if (function.getFunctionType() != 3
					|| function.getFunctionPageElementList() == null
					|| function.getFunctionPageElementList().size() <= 0)
				return;
			if (function.getId() == null || "".equals(function.getId())) {
				throw new FunctionException(
						(FunctionExceptionType.FunctionIdNull));
			}
			int index = 1;
			for (Function functionPageElement : function
					.getFunctionPageElementList()) {
				// 插入页面元素功能对象
				insertFuntionPageElement(function, index, functionPageElement);
				index++;
			}
		} catch (FunctionException e) {
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					e.getErrorArguments()) {
			};
		}
	}

	/**
	 * 待新增的功能对象
	 * 
	 * @param function
	 *            页面上传的功能对象
	 * @param parentFunction
	 *            功能对象的父对象
	 */
	private void setSaveFunctionFields(Function function,
			Function parentFunction) {
		// 设置功能层次
		Integer parentFunctionLevel = parentFunction.getFunctionLevel();
		function.setFunctionLevel(parentFunctionLevel + 1);
		// 设置功能生效或失效时间
		setValidTime(function);
		// 设置功能路径序列
		function.setFunctionSeq(parentFunction.getFunctionSeq()+'/'
				+ function.getFunctionCode());
		// 设置功能编码
		String functionCode = getFunctionCode(function);
		if (functionCode == null) {
			throw new FunctionException(
					FunctionExceptionType.FunctionCodeCreateError);
		}
		function.setFunctionCode(functionCode);
	}
	/**
	 * 修改功能对象
	 * 
	 * @param function 功能对象
	 * @param createFunctionPageEletmens 创建的页面元素权限信息
	 * @param updateFunctionPageEletmens 待更新的页面元素权限信息
	 * @param deleteFunctionPageEletmens 删除的页面元素权限信息
	 */
	@SuppressWarnings("serial")
	public void update(Function function,
			List<Function> createFunctionPageEletmens,
			List<Function> updateFunctionPageEletmens,
			List<Function> deleteFunctionPageEletmens) {
		try {
			// 校验功能对象属性
			validateFunctionField(function);
			// 校验待修改的功能对象ID
			if (function.getId() == null || "".equals(function.getId())) {
				throw new FunctionException(
						FunctionExceptionType.FunctionIdNull);
			}
		} catch (FunctionException e) {
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					e.getErrorArguments()) {
			};
		}
		try {
			// 得到功能对象
			Function dbFunction = functionDao.getById(function.getId());
			dbFunction = setUpdateFunctionFields(dbFunction, function);
			functionDao.update(dbFunction);
			// 功能类型是否是模块功能、功能类型的功能页面元素集合为空或集合长度为0
			if (function.getFunctionType() != 3
					|| ((createFunctionPageEletmens == null || createFunctionPageEletmens
							.size() <= 0)
							&& (updateFunctionPageEletmens == null || updateFunctionPageEletmens
									.size() <= 0) && (deleteFunctionPageEletmens == null || deleteFunctionPageEletmens
							.size() <= 0)))
				return;
			if (null != deleteFunctionPageEletmens
					&& deleteFunctionPageEletmens.size() > 0) {
				functionDao.deleteFunctions(deleteFunctionPageEletmens);
			}
			if (null != updateFunctionPageEletmens
					&& updateFunctionPageEletmens.size() > 0) {
				List<String> functionIds = new ArrayList<String>();
				for (Function functionPageElement : updateFunctionPageEletmens) {
					functionIds.add(functionPageElement.getId());
				}
				List<Function> dbFunctions = functionDao.getByIds(functionIds);
				for (Function functionPageElement : updateFunctionPageEletmens) {
					Function oldFunction;
					for (Function oldFun : dbFunctions) {
						if (functionPageElement.getId().equals(oldFun.getId())) {
							oldFunction = oldFun;
							// 校验功能对象属性
							validateFunctionField(functionPageElement);
							// 设置功能对象的一些属性值
							setUpdateFunctionFields(oldFunction,
									functionPageElement);
							functionDao.update(oldFunction);
						}
					}
				}
			}
			if (null != createFunctionPageEletmens
					&& createFunctionPageEletmens.size() > 0) {
				Function createFunction = createFunctionPageEletmens.get(0);
				createFunction.setParentCode(dbFunction);
				//得到数据库中功能编码的最大值
				String functionCode = getFunctionCode(createFunction);
				int index = Integer.parseInt(functionCode.substring(10));
				for (Function functionPageElement : createFunctionPageEletmens) {
					// 插入页面元素功能对象
					insertFuntionPageElement(function, index,
							functionPageElement);
					index++;
				}
			}
		} catch (FunctionException e) {
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					e.getErrorArguments()) {
			};
		}

	}

	/**
	 * 设置待更新的功能对象
	 * 
	 * @param oldFunction
	 *            数据库中的功能对象
	 * @param newfunction
	 *            页面上传的功能对象
	 * @return
	 */
	private Function setUpdateFunctionFields(Function oldFunction,
			Function newfunction) {
		if (oldFunction.getCheck() == null
				|| !oldFunction.getCheck().equals(newfunction.getCheck())) {
			oldFunction.setCheck(newfunction.getCheck());
		}
		if (oldFunction.getValidFlag() == null
				|| !oldFunction.getValidFlag().equals(
						newfunction.getValidFlag())) {
			oldFunction.setValidFlag(newfunction.getValidFlag());
			// 设置功能生效或失效时间
			setValidTime(oldFunction);
		}
		if (oldFunction.getDisplayOrder() == null
				|| !oldFunction.getDisplayOrder().equals(
						newfunction.getDisplayOrder())) {
			oldFunction.setDisplayOrder(newfunction.getDisplayOrder());
		}
		if (oldFunction.getFunctionDesc() == null
				|| !oldFunction.getFunctionDesc().equals(
						newfunction.getFunctionDesc())) {
			oldFunction.setFunctionDesc(newfunction.getFunctionDesc());
		}
		if (oldFunction.getFunctionName() == null
				|| !oldFunction.getFunctionName().equals(
						newfunction.getFunctionName())) {
			oldFunction.setFunctionName(newfunction.getFunctionName());
		}
		if (oldFunction.getUri() == null
				|| !oldFunction.getUri().equals(newfunction.getUri())) {
			oldFunction.setUri(newfunction.getUri());
		}
		if (oldFunction.getFunctionType() == null
				|| !oldFunction.getFunctionType().equals(newfunction.getFunctionType())) {
			oldFunction.setFunctionType(newfunction.getFunctionType());
		}
		// 设置功能路径序列
		oldFunction.setFunctionSeq(newfunction.getParentCode().getFunctionSeq()+'/'
				+ newfunction.getFunctionCode());
		if (oldFunction.getParentCode().getFunctionCode() == null
				|| !oldFunction.getParentCode().getFunctionCode()
						.equals(newfunction.getParentCode().getFunctionCode())) {
			oldFunction.setParentCode(newfunction.getParentCode());
			// 设置功能层次
			Integer parentFunctionLevel = newfunction.getParentCode()
					.getFunctionLevel();
			oldFunction.setFunctionLevel(parentFunctionLevel + 1);
			// 设置功能编码
			String functionCode = getFunctionCode(newfunction);
			if (functionCode == null) {
				throw new FunctionException(
						FunctionExceptionType.FunctionCodeCreateError);
			}
			oldFunction.setFunctionCode(functionCode);
		}
		return oldFunction;
	}

	/**
	 * 验证功能对象属性
	 * 
	 * @param function
	 */
	private void validateFunctionField(Function function) {
		// 验证功能对象
		if (function == null) {
			throw new FunctionException(FunctionExceptionType.FunctionIsNull);
		}
		// 验证功能名
		if (function.getFunctionName() == null
				|| "".equals(function.getFunctionName())) {
			throw new FunctionException(
					FunctionExceptionType.FunctionNameIsNull);
		}
		// 验证功能入口URI
		if (function.getUri() == null
				|| "".equals(function.getUri())
				|| !function.getUri().matches(
						"^/(\\w+([\\w-_]*))+(/[\\w-_]*)?(.[\\w]*)(/[\\w-_]*)?")) {
			throw new FunctionException(
					FunctionExceptionType.FunctionURIIsError);
		}
		// 验证父功能对象
		if (function.getParentCode() == null) {
			throw new FunctionException(
					FunctionExceptionType.ParentFunctionNullError);
		}
		// 验证功能是否有效
		if (function.getValidFlag() == null) {
			throw new FunctionException(
					FunctionExceptionType.FunctionValidFlagNullError);
		}
		// 验证功能是否权限检查
		if (function.getCheck() == null) {
			throw new FunctionException(
					FunctionExceptionType.FunctionCheckNullError);
		}
		// 验证功能功能类型
		if (function.getFunctionType() == null) {
			throw new FunctionException(
					FunctionExceptionType.FunctionTypeNullError);
		}
	}

	// 设置功能生效或失效时间
	private void setValidTime(Function function) {
		if (function.getValidFlag() == true) {
			function.setValidDate(new Date());
			function.setInvalidDate(null);
		} else {
			function.setInvalidDate(new Date());
			function.setValidDate(null);
		}
	}

	// 插入页面元素功能对象
	private void insertFuntionPageElement(Function function, int index,
			Function functionPageElement) {
		// 设置功能的父功能
		functionPageElement.setParentCode(function);
		// 校验功能对象属性
		validateFunctionField(functionPageElement);
		// 通过序列得到ID值,前把ID设置给待新增的功能对象
		functionPageElement.setId(functionDao.getNewId());
		// 设置功能层次
		Integer parentFunctionLevel = function.getFunctionLevel();
		functionPageElement.setFunctionLevel(parentFunctionLevel + 1);
		// 设置功能生效或失效时间
		setValidTime(function);
		// 设置功能路径序列
		functionPageElement.setFunctionSeq(function.getFunctionSeq()+'/'
				+ function.getFunctionCode());
		// 设置功能编码
		String functionCode = function.getFunctionCode();
		StringBuilder sb = new StringBuilder();
		sb.append(functionCode);
		sb.append(leftPading(String.valueOf(index), "0", 3));
		functionPageElement.setFunctionCode(sb.toString());
		functionDao.insert(functionPageElement);
	}

	/**
	 * 生成功能编码 生成规则如下： 两位应用子系统，四位子系统模块，四位模块功能，三位功能页面元素
	 * 如：01到99应用子系统；001到999子系统模块；001到999模块功能；001到999功能页面元素； 例子：应用子系统编码：01
	 * 子系统模块编码：01001 模块功能:01001001 功能页面元素：01001001001；
	 */
	private String getFunctionCode(Function function) {
		StringBuilder sb = new StringBuilder();
		switch (function.getFunctionType()) {
		// 应用子系统编码
		case 1:
			sb.append("01");
			break;
		// 子系统模块编码
		case 2:
			sb.append(function.getParentCode().getFunctionCode());
			sb.append("0001");
			break;
		// 模块功能编码
		case 3:
			sb.append(function.getParentCode().getFunctionCode());
			sb.append("0001");
			break;
		// 功能页面元素编码
		case 4:
			sb.append(function.getParentCode().getFunctionCode());
			sb.append("001");
			break;
		}
		// 功能编码
		String functionCode = sb.toString();
		String code = createMaxFunctionCode(function);
		if(code!=null){
			functionCode = code;
		}
		return functionCode;
	}

	/**
	 * 查找数据库中的功能编码最大值
	 * @param function
	 * @return
	 */
	private String createMaxFunctionCode(Function function) {
		String functionCode = null;
		// 通过父功能编码得到所有子功能中编码值最大的功能对象
		Function maxFunction = functionDao.getMaxCodeFunctionByParentCode(function.getParentCode().getFunctionCode());
		if (maxFunction != null && maxFunction.getFunctionCode() != null) {
			String maxFunctionCode = maxFunction.getFunctionCode();
			int codeLength = maxFunctionCode.length();
			Long maxCode = Long.parseLong(maxFunctionCode);
			if (maxCode != null) {
				maxCode++;
				functionCode = leftPading(maxCode.toString(), "0", codeLength);
			}
		}
		return functionCode;
	}

	/**
	 * 字符串左边增加字符
	 * 
	 * @param strSrc
	 *            原始字符串
	 * @param flag
	 *            要补的字符
	 * @param strSrcLength
	 *            需要长度
	 * @return
	 */
	private static String leftPading(String strSrc, String flag,
			int strSrcLength) {
		String strReturn = "";
		String strtemp = "";
		int curLength = strSrc.trim().length();
		if (strSrc != null && curLength > strSrcLength) {
			strReturn = strSrc.trim().substring(0, strSrcLength);
		} else if (strSrc != null && curLength == strSrcLength) {
			strReturn = strSrc.trim();
		} else {
			for (int i = 0; i < (strSrcLength - curLength); i++) {
				strtemp = strtemp + flag;
			}
			strReturn = strtemp + strSrc.trim();
		}
		return strReturn;
	}

	public IFunctionDao getFunctionDao() {
		return functionDao;
	}

	public void setFunctionDao(IFunctionDao functionDao) {
		this.functionDao = functionDao;
	}

	public List<Function> getFunctionByParentCode(String parentCode) {
		return functionDao.getFunctionByParentCode(parentCode);
	}
}
