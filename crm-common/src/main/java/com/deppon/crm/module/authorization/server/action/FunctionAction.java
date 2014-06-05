package com.deppon.crm.module.authorization.server.action;

import java.util.List;

import com.deppon.crm.module.authorization.server.service.IFunctionService;
import com.deppon.crm.module.authorization.shared.domain.Function;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.message.IMessageBundle;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;

/**
 * 功能管理WEB接入
 * 
 * @author ztjie
 * 
 */
public class FunctionAction extends AbstractAction {

	private static final long serialVersionUID = 5490905806474476603L;

	// 注入国际化类messageBundle
	private IMessageBundle messageBundle;

	// 注入functionService
	private IFunctionService functionService;

	// 提示信息
	private String message;

	// 分页最大记录数
	private int limit;

	// 分页开始记录数
	private int start;

	// 总记录数
	private Long totalCount;

	// 父功能对象
	private Function parentCode;

	// 功能对象列表
	private List<Function> functionList;

	// 功能对象
	private Function function;

	// 创建的页面元素权限信息
	private List<Function> createFunctionPageEletmens;
	
	// 判断是禁用还是启用
	private int flag;
	
	// 用来获取所属导航的code
	private String functionCode;
	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}

	/**
	 * 修改功能对象
	 * @return
	 */
	@JSON
	public String updateFunction() {
		String msg="";
		if(flag==1){
			 msg="i18n.authorization.startSuccess";
		}else{
			 msg="i18n.authorization.endSuccess";
		}
		functionService.update(function);
		message = messageBundle.getMessage(UserContext.getUserLocale(),msg);
		return "success";
	}
	
	/**
	 * 点击模块功能节点的时候，查找所有的该节点的叶子节点功能对象
	 * 
	 * @return
	 */
	@JSON
	public String findFunctionPageElementByParentCode() {
		//定义一个查询条件的功能对象
		Function function = new Function();
		//设置父功能编码
		function.setParentCode(parentCode);
		functionList = functionService.queryDirectChildFunctions(function);
		return SUCCESS;
	}
	
/*------------------------暂时没有用到---------------------------------------*/
	/**
	 * 保存功能对象
	 * 
	 * @return
	 */
	@JSON
	public String saveFunction() {
		function.setFunctionPageElementList(createFunctionPageEletmens);
		functionService.save(function);
		message = messageBundle.getMessage(UserContext.getUserLocale(),
				"i18n.authorization.saveSuccess");
		return SUCCESS;
	}

	/**
	 * 查找所有的功能对象
	 * 
	 * @return
	 */
	@JSON
	public String findAllFunction() {
		functionList = functionService.queryAll(function, limit, start);
		totalCount = functionService.count(function);
		return SUCCESS;
	}
	
	/**
	 * 删除功能对象
	 * 
	 * @return
	 */
	@JSON
	public String deleteFunction() {
		functionService.remove(function.getId());
		message = messageBundle.getMessage(getLocale(),
				"i18n.authorization.deleteSuccess");
		return SUCCESS;
	}
	

	public String searchModuleFunction() {
		Function f = new Function();
		f.setFunctionLevel(3);
		functionList = functionService.queryAll(f);

		return SUCCESS;
	}

	public String searchMenuFunction() {
		functionList = functionService.getFunctionByParentCode(functionCode);
		return SUCCESS;
	}
/*------------------------暂时没有用到---------------------------------------*/

	public List<Function> getFunctionList() {
		return functionList;
	}

	public void setFunctionList(List<Function> functionList) {
		this.functionList = functionList;
	}

	public Function getFunction() {
		return function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Function getParentCode() {
		return parentCode;
	}

	public void setParentCode(Function parentCode) {
		this.parentCode = parentCode;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setCreateFunctionPageEletmens(
			List<Function> createFunctionPageEletmens) {
		this.createFunctionPageEletmens = createFunctionPageEletmens;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public void setMessageBundle(IMessageBundle messageBundle) {
		this.messageBundle = messageBundle;
	}

	public void setFunctionService(IFunctionService functionService) {
		this.functionService = functionService;
	}
	
}
