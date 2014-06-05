package com.deppon.crm.module.servicerecovery.server.action;

import java.util.List;

import com.deppon.crm.module.servicerecovery.server.manager.IFinanceDeptManager;
import com.deppon.crm.module.servicerecovery.shared.domain.FinanceDept;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;

/**
 * 
 * <p>
 * Description:查询财务部<br />
 * </p>
 * @title FinanceDeptAction.java
 * @package com.deppon.crm.module.servicerecovery.server.action 
 * @author roy
 * @version 0.1 2013-5-22
 */
public class FinanceDeptAction extends AbstractAction {
	//查询财务部门manager
	private IFinanceDeptManager financeDeptManager;
	private List<FinanceDept> financeDeptList;

	public void setFinanceDeptList(List<FinanceDept> financeDeptList) {
		this.financeDeptList = financeDeptList;
	}

	public void setFinanceDeptManager(IFinanceDeptManager financeDeptManager) {
		this.financeDeptManager = financeDeptManager;
	}
	/**
	 * 
	 * <p>
	 * Description:查询财务部<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-5-22
	 * @return
	 * String
	 */
	@JSON
	public String searchFinanceDept() {
		//得到财务部门的list
		financeDeptList = financeDeptManager.searchFinanceDeptList();
		return SUCCESS;
	}

	public List<FinanceDept> getFinanceDeptList() {
		return financeDeptList;
	}

}
