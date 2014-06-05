/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title AreaAddressAction.java
 * @package com.deppon.crm.module.common.server.action 
 * @author xiaoqiang
 * @version 0.1 2012-3-15
 */
package com.deppon.crm.module.common.server.action;

import java.util.List;

import com.deppon.crm.module.common.server.manager.impl.BankInfoManager;
import com.deppon.crm.module.common.shared.domain.BankView;
import com.deppon.foss.framework.server.web.action.AbstractAction;

/**
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title BankInfoAction.java
 * @package com.deppon.crm.module.common.server.action 
 * @author ouy
 * @version 0.1 2012-3-31
 */

public class BankInfoAction extends AbstractAction {
	private BankInfoManager bankInfoManager;
	private List<BankView> bankViewList;
	private BankView bankView;
	public String searchBankInfoByBankView(){
		bankViewList=bankInfoManager.getBankInfoByBV(bankView);
		return SUCCESS;
	}
	public void setBankInfoManager(BankInfoManager bankInfoManager) {
		this.bankInfoManager = bankInfoManager;
	}
	public List<BankView> getBankViewList() {
		return bankViewList;
	}
	public void setBankViewList(List<BankView> bankViewList) {
		this.bankViewList = bankViewList;
	}
	public BankView getBankView() {
		return bankView;
	}
	public void setBankView(BankView bankView) {
		this.bankView = bankView;
	}
}
