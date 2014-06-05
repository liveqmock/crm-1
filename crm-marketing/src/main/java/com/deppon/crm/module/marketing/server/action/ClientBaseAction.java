package com.deppon.crm.module.marketing.server.action;

import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.common.shared.domain.Detail;
import com.deppon.crm.module.marketing.server.manager.IClientBaseManager;
import com.deppon.crm.module.marketing.server.manager.IMarketActivityManager;
import com.deppon.crm.module.marketing.shared.domain.activity.ClientBase;
import com.deppon.crm.module.marketing.shared.domain.activity.DeptTree;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;
/**
 * 
 * <p>
 * 客户群action<br/>
 * </p>
 * 
 * @author lvchongxin
 * @version 0.1 2014-3-14
 * @param 
 * @return 
 */
 
public class ClientBaseAction extends AbstractAction {


	private static final long serialVersionUID = 1L;
	//客户群Manager
	private IClientBaseManager clientBaseManager;
	//客户群查询实体
	private ClientBase clientBase;
	//客户群查询结果集
	private List<ClientBase> clientBaseList;
	//客户群名称
	private String clientBaseName; 
	//一级行业List
	private List<String> tradesList;
	//客户群Id
	private String clientBaseId;
	//起始页
	private int start;
	//每页显示条数
	private int limit;
	//总行数
	private Long totalCount;
	//客户群名称是否重复
	private boolean checkRepeate;
	//返回的二级行业list
	private List<Detail> detailList;
	//标记gird是否修改
	private String isChange;
	//筛选二级行业ID
	private String conditionId;
	//筛选二级行业类型
	private String conditionType;
	//查看详情查询二级行业
	private List<String> secondTrade;
	
	public ClientBase getClientBase() {
		return clientBase;
	}

	public void setClientBase(ClientBase clientBase) {
		this.clientBase = clientBase;
	}


	public void setClientBaseManager(IClientBaseManager clientBaseManager) {
		this.clientBaseManager = clientBaseManager;
	}


	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	
	public List<String> getTradesList() {
		return tradesList;
	}

	public void setTradesList(List<String> tradesList) {
		this.tradesList = tradesList;
	}
	

	public List<Detail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<Detail> detailList) {
		this.detailList = detailList;
	}

	public List<ClientBase> getClientBaseList() {
		return clientBaseList;
	}

	public void setClientBaseList(List<ClientBase> clientBaseList) {
		this.clientBaseList = clientBaseList;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public String getClientBaseName() {
		return clientBaseName;
	}

	public void setClientBaseName(String clientBaseName) {
		this.clientBaseName = clientBaseName;
	}

	public boolean isCheckRepeate() {
		return checkRepeate;
	}

	public void setCheckRepeate(boolean checkRepeate) {
		this.checkRepeate = checkRepeate;
	}

	public String getClientBaseId() {
		return clientBaseId;
	}

	public void setClientBaseId(String clientBaseId) {
		this.clientBaseId = clientBaseId;
	}
	
	public String getIsChange() {
		return isChange;
	}

	public void setIsChange(String isChange) {
		this.isChange = isChange;
	}
	
	public String getConditionId() {
		return conditionId;
	}

	public void setConditionId(String conditionId) {
		this.conditionId = conditionId;
	}

	public String getConditionType() {
		return conditionType;
	}

	public void setConditionType(String conditionType) {
		this.conditionType = conditionType;
	}

	public List<String> getSecondTrade() {
		return secondTrade;
	}

	public void setSecondTrade(List<String> secondTrade) {
		this.secondTrade = secondTrade;
	}

	/**
	 * 
	 * 
	 * <p>
	 * 查询客户群方法<br/>
	 * </p>
	 * 
	 * @author lvchongxin
	 * @version 0.1 2014-3-18
	 * @param 
	 * @return
	 */
	public String searchClientBase(){
		Map<String,Object> map = clientBaseManager.searchClientBase(clientBase, start, limit, getCurrentUser());
		clientBaseList=(List<ClientBase>) map.get("clientBaseList");
		totalCount=Long.valueOf(map.get("totalCount").toString());
		totalCount =(totalCount == 0) ? 1 : totalCount;
		return SUCCESS;
	}
	
	/**
	 * 
	 * 
	 * <p>
	 * 检查客户群名称是否重复<br/>
	 * </p>
	 * 
	 * @author lvchongxin
	 * @version 0.1 2014-3-18
	 * @param 
	 * @return
	 */
	@JSON
	public String checkClientBase(){
		//设置返回参数
		checkRepeate=clientBaseManager.checkClientBaseName(clientBase);
		
		return SUCCESS;
	}
	/**
	 * 
	 * 
	 * <p>
	 * 查看客户群详情<br/>
	 * </p>
	 * 
	 * @author lvchongxin
	 * @version 0.1 2014-3-19
	 * @param 
	 * @return
	 */
	@JSON
	public String searchClientBaseById(){
		clientBase=clientBaseManager.searchClientBaseDetail(clientBase.getId());
		return SUCCESS;
		
	}
	
	/**
	 * 
	 * 
	 * <p>
	 * 删除客户群<br/>
	 * </p>
	 * 
	 * @author lvchongxin
	 * @version 0.1 2014-3-19
	 * @param 
	 * @return
	 */
	@JSON
	public String deleteClientBaseById(){
		clientBaseManager.deleteClientBaseById(clientBase.getId(), getCurrentUser());
		return SUCCESS;
	}
	
	/**
	 * 
	 * 
	 * <p>
	 * 保存客户群<br/>
	 * </p>
	 * 
	 * @author lvchongxin
	 * @version 0.1 2014-3-19
	 * @param 
	 * @return
	 */
	
	@JSON
	public String saveClientBase(){
		clientBaseManager.addClientBase(clientBase, getCurrentUser());
		return SUCCESS;
	}
	/**
	 * 
	 * 
	 * <p>
	 * 更新客户群<br/>
	 * </p>
	 * 
	 * @author lvchongxin
	 * @version 0.1 2014-3-19
	 * @param 
	 * @return
	 */
	@JSON
	public String updateClientBase(){
		clientBaseManager.updateClientBase(clientBase, getCurrentUser(),isChange);
		return SUCCESS;
	}
	
	/**
	 * 
	 * 
	 * <p>
	 * 根据一级行业查二级行业<br/>
	 * </p>
	 * 
	 * @author lvchongxin
	 * @version 0.1 2014-3-19
	 * @param 
	 * @return
	 */
	@JSON
	public String searchSecondTradesByList(){
		detailList=clientBaseManager.searchSecondTradesByList(tradesList);
		return SUCCESS;
	}
	/**
	 * 
	 * 
	 * <p>
	 * 查询详情用查二级行业<br/>
	 * </p>
	 * 
	 * @author lvchongxin
	 * @version 0.1 2014-5-12
	 * @param 
	 * @return
	 */
	@JSON
	public String searchSecondTradesToString(){
		secondTrade=clientBaseManager.searchSecondTradesToString(conditionId, conditionType);
		return SUCCESS;
	}
	/**
	 * 
	 * 
	 * <p>
	 * 得到当前用户<br/>
	 * </p>
	 * 
	 * @author lvchongxin
	 * @version 0.1 2014-4-4
	 * @param 
	 * @return
	 */
	private User getCurrentUser(){
		return (User) UserContext.getCurrentUser();
	}
}
