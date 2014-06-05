package com.deppon.crm.module.recompense.server.action;

import java.util.List;

import com.deppon.crm.module.common.shared.domain.AccountBank;
import com.deppon.crm.module.common.shared.domain.BankView;
import com.deppon.crm.module.recompense.server.manager.IPaymentManager;
import com.deppon.crm.module.recompense.server.manager.impl.RecompenseManagerImpl;
import com.deppon.crm.module.recompense.shared.domain.Payment;
import com.deppon.crm.module.recompense.shared.domain.RecompenseView;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;

/**作者：zouming
 *创建时间：2013-1-4
 *最后修改时间：2013-1-4
 *描述：提交付款
 */
public class PaymentAction extends AbstractAction{
	private RecompenseManagerImpl recompenseManager;//理赔manager
	private IPaymentManager paymentManager;
	
	private List<BankView>bankViewList;//支行列表
	private Payment payment;//付款用例
	private List<AccountBank> bankList;//银行列表
	private List<Payment>paymentList;//收银员账户信息
	private BankView bankView;//支行信息
	
	private String employeeNum;//员工工号
	private String bankName;//输入的银行名称
	
	//理赔上报对象
	private RecompenseView recompenseView;

	//****set****//
	/**
	 * @param paymentManager : set the property paymentManager.
	 */
	public void setPaymentManager(IPaymentManager paymentManager) {
		this.paymentManager = paymentManager;
	}
	
	/**
	 * @param recompenseManager : set the property recompenseManager.
	 */
	public void setRecompenseManager(RecompenseManagerImpl recompenseManager) {
		this.recompenseManager = recompenseManager;
	}
	

	public void setEmployeeNum(String employeeNum) {
		this.employeeNum = employeeNum;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	public void setRecompenseView(RecompenseView recompenseView) {
		this.recompenseView = recompenseView;
	}
	
	public RecompenseView getRecompenseView() {
		return recompenseView;
	}
	
	public void setBankView(BankView bankView) {
		this.bankView = bankView;
	}

	public BankView getBankView() {
		return bankView;
	}

	//****get****//
	public List<AccountBank> getBankList() {
		return bankList;
	}
	
	public List<Payment> getPaymentList() {
		return paymentList;
	}

	public List<BankView> getBankViewList() {
		return bankViewList;
	}

	//***get与set********//	
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	/**
	 * 
	 * <p>
	 * Description:银行模糊查询<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-4下午3:50:12
	 * @return
	 * String
	 * @update 2013-1-4下午3:50:12
	 */
	@JSON
	public String searchBankListByName(){
		bankList = paymentManager.searchBankListByName(bankName);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:查询支行信息<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-12下午4:22:00
	 * @return
	 * String
	 * @update 2013-1-12下午4:22:00
	 */
	@JSON
	public String searchBankInfoByBankView(){
		bankViewList = recompenseManager.searchBankInfoByBankView(bankView);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:根据员工工号查询收银员帐号信息<br />
	 * </p>
	 * @author zouming
	 * @version 0.1 2013-1-7下午5:02:03
	 * @return
	 * String
	 * @update 2013-1-7下午5:02:03
	 */
	@JSON
	public String queryPaymentByEmployeeCode(){
		paymentList = recompenseManager.queryPaymentByEmployeeNum(employeeNum);
		return SUCCESS;
	}
}
