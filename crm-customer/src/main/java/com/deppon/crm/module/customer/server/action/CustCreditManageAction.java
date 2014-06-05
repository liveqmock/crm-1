/**
 * @Title: CustCreditManageAction.java
 * @Package com.deppon.crm.module.customer.server.action
 * @Description:{todo}
 * @author: 唐亮
 * @date: 2014-4-1 上午10:37:59
 * @version V1.0
 */
package com.deppon.crm.module.customer.server.action;

import java.util.ArrayList;
import java.util.List;

import com.deppon.crm.module.customer.server.manager.ICustCreditManager;
import com.deppon.crm.module.customer.server.utils.CustCreditUtil;
import com.deppon.crm.module.customer.shared.domain.CustCreditConfig;
import com.deppon.crm.module.customer.shared.domain.CustCreditDetail;
import com.deppon.foss.framework.server.web.action.AbstractAction;

/**
 * <p>
 * </p>
 * @title CustCreditManageAction.java
 * @package com.deppon.crm.module.customer.server.action
 * @author 唐亮
 * @version 0.1 2014-4-1
 */
public class CustCreditManageAction extends AbstractAction{
	/**
	* @Fields serialVersionUID
	*/
	private static final long serialVersionUID = 1L;
	
	private ICustCreditManager custCreditManager;
	
	//传入前台的信用较差客户配置信息
	private List<CustCreditDetail> custCreditInfo = new ArrayList<CustCreditDetail>();
	//后台用信用较差客户配置信息
	private CustCreditConfig custCreditConfig =  new CustCreditConfig();
	/**
	 * 
	 * Description:custCreditManager<br />
	 * @author 唐亮
	 * @version 0.1 2014-4-1
	 */
	public void setCustCreditManager(ICustCreditManager custCreditManager) {
		this.custCreditManager = custCreditManager;
	}
	
	/**
	 * 
	 * @Title: searchMemberCredit
	 *  <p>
	 * @Description: 获取信用较差客户配置信息<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2014-4-1
	 * @return List<String>
	 * @throws
	 */
	public String searchCustCredit(){
		//获取信用较差客户配置信息
		custCreditConfig =  custCreditManager.getCustCreditConfig();
		//数据接口改造
		custCreditInfo = CustCreditUtil.changeCustCredit(custCreditConfig);
		return SUCCESS;
	}
	/**
	 * 
	 * @Title: updateCustCredit
	 *  <p>
	 * @Description: 更新信用较差客户配置信息<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2014-4-2
	 * @return String
	 * @throws
	 */
	public String updateCustCredit(){
		custCreditManager.updateCustCreditConfig(custCreditConfig);
		return SUCCESS;
	}
	
	/**
	 * 
	 * Description:custCreditInfo<br />
	 * @author 唐亮
	 * @version 0.1 2014-4-2
	 */
	
	public List<CustCreditDetail> getCustCreditInfo() {
		return custCreditInfo;
	}
	
	/**
	 * 
	 * Description:custCreditInfo<br />
	 * @author 唐亮
	 * @version 0.1 2014-4-2
	 */
	public void setCustCreditInfo(List<CustCreditDetail> custCreditInfo) {
		this.custCreditInfo = custCreditInfo;
	}

	/**
	 * 
	 * Description:custCreditConfig<br />
	 * @author 唐亮
	 * @version 0.1 2014-4-2
	 */
	public CustCreditConfig getCustCreditConfig() {
		return custCreditConfig;
	}

	/**
	 * 
	 * Description:custCreditConfig<br />
	 * @author 唐亮
	 * @version 0.1 2014-4-2
	 */
	public void setCustCreditConfig(CustCreditConfig custCreditConfig) {
		this.custCreditConfig = custCreditConfig;
	}
	
}
