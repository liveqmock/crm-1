package com.deppon.crm.module.client.fin.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.rpc.ServiceException;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.common.util.ClientTool;
import com.deppon.crm.module.client.common.util.JsonMapperUtil;
import com.deppon.crm.module.client.common.util.NullOrEmptyValidator;
import com.deppon.crm.module.client.fin.IFINRecompenserOperate;
import com.deppon.crm.module.client.fin.domain.CashierAccountInfo;
import com.deppon.crm.module.client.fin.domain.FINBankNumberCrmFacadeLocator;
import com.deppon.crm.module.client.fin.domain.FINForCrmFacadeLocator;
import com.deppon.crm.module.client.fin.domain.NoBillingRecompenseInfo;
import com.deppon.fin.bankNum.WSInvokeException;


/**
 * @作者：罗典
 * @描述：从财务部获取出纳银行信息
 * @时间：2013-01-13
 * */
public class FINRecompenserOperateImpl implements IFINRecompenserOperate {
	FINBankNumberCrmFacadeLocator finBankNumFacade;
	FINForCrmFacadeLocator fINForCrmFacade;

	/**
	 * @作者：罗典
	 * @描述：根据员工编号获取出纳银行账号
	 * @时间：2013-01-13
	 * @参数：出纳员工工号
	 * @返回：出纳账号集合
	 * */
	@Override
	public List<CashierAccountInfo> searchCashierAccount(String personNumber)
			throws CrmBusinessException {
		NullOrEmptyValidator.checkEmpty(personNumber, "personNumber");
		List<CashierAccountInfo> lists = new ArrayList<CashierAccountInfo>();
		try {
			// 调用接口
			String[] cashierAccounts = finBankNumFacade.getWSFinBankNumberCrmFacade()
					.cashieraccount(personNumber);
			if("1".equals(cashierAccounts[0])){
				List<Object> objLists = new ArrayList<Object>();
				objLists = JsonMapperUtil.jsonStrToList(cashierAccounts[1], CashierAccountInfo.class);
				for(Object obj : objLists){
					lists.add((CashierAccountInfo)obj);
				}
				return lists;
			}else{
				throw new CrmBusinessException("1002",cashierAccounts[1]);
			}
		} catch (ServiceException e) {
			throw new CrmBusinessException("1002", personNumber
					+ e.getMessage());
		} catch (WSInvokeException e) {
			if(e.getFaultReason()!=null&&!"".equals(e.getFaultReason())){
				String errorInfo = e.getFaultReason()+"";
				int i = errorInfo.lastIndexOf(":")+1;
				int y = errorInfo.length()-1;
				String errorMsg = errorInfo.substring(i,y) ;
				throw new CrmBusinessException("1002", errorMsg);
			}
			throw new CrmBusinessException("1002", e.getMessage());
		} catch (RemoteException e) {
			throw new CrmBusinessException("1002", personNumber
					+ e.getMessage());
		}catch(CrmBusinessException e){
			throw e;
		} catch (Exception e) {
			throw new CrmBusinessException("1002", e.getMessage());
		}
	}

	/**
	 * @作者：罗典
	 * @描述：未开单理赔付款申请(费控)
	 * @时间：2013-01-13
	 * @参数：付款信息
	 * @返回：工作流号
	 * */
	@Override
	public String createNoBillingRecompense(NoBillingRecompenseInfo info)
			throws CrmBusinessException {
		NullOrEmptyValidator.checkEmpty(info, "NoBillingRecompenseInfo");
		String[] recompenseInfo = ClientTool.convertToStringArray(info);
		// 调用接口
		String[] result;
		try {
			result = fINForCrmFacade.getWSFinanceForCrmFacade()
					.callCrmPaymentWorkFlow(recompenseInfo);
			if("1".equals(result[0])){
				throw new CrmBusinessException("1002",result[1]);
			}
			return result[1];
		} catch (ServiceException e) {
			throw new CrmBusinessException("1002", info.getBillNum()
					+ e.getMessage());
		}  catch (WSInvokeException e) {
			if(e.getFaultReason()!=null&&!"".equals(e.getFaultReason())){
				String errorInfo = e.getFaultReason()+"";
				int i = errorInfo.lastIndexOf(":")+1;
				int y = errorInfo.length()-1;
				String errorMsg = errorInfo.substring(i,y) ;
				throw new CrmBusinessException("1002", errorMsg);
			}
			throw new CrmBusinessException("1002", e.getMessage());
		} catch (RemoteException e) {
			throw new CrmBusinessException("1002", info.getBillNum()
					+ e.getMessage());
		} catch(CrmBusinessException e){
			throw e;
		}
		catch (Exception e) {
			throw new CrmBusinessException("1002", e.getMessage());
		}
	}

	public FINBankNumberCrmFacadeLocator getFinBankNumFacade() {
		return finBankNumFacade;
	}

	public void setFinBankNumFacade(
			FINBankNumberCrmFacadeLocator finBankNumFacade) {
		this.finBankNumFacade = finBankNumFacade;
	}

	public FINForCrmFacadeLocator getfINForCrmFacade() {
		return fINForCrmFacade;
	}

	public void setfINForCrmFacade(FINForCrmFacadeLocator fINForCrmFacade) {
		this.fINForCrmFacade = fINForCrmFacade;
	}

}
