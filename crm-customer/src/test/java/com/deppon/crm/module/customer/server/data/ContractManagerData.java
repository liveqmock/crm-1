package com.deppon.crm.module.customer.server.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import com.deppon.crm.module.customer.shared.domain.Contract;
import com.deppon.crm.module.customer.shared.domain.Preferential;

public class ContractManagerData {
	
	/**
	 * 
	 * <p>
	 * Description:创建一个月结合同<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-3-1
	 * @return
	 * Contract
	 * @throws ParseException 
	 */
	public static Contract createContract(String payWayType)  {
		
		Contract contract = new Contract();
		
		
		contract.setPayWay(payWayType);
		contract.setArrearaMount(2000d);
	
		contract.setPreferentialType("MONTH_SEND");
		
		Calendar cal = Calendar.getInstance();
		
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)+1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		contract.setContractBeginDate(cal.getTime());
		
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)+3);
		cal.set(Calendar.DAY_OF_MONTH, 0);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			contract.setContractendDate(sdf.parse(cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DAY_OF_MONTH)));
		} catch (ParseException e) {
		}
		contract.setResultDate(17);
		contract.setApplication("客户要求临欠！");
	
		
		return contract;
	}
	
	/**
	 * 
	 * <p>
	 * Description:创建一个无优惠的折扣<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-3-1
	 * @return
	 * Contract
	 */
	public static Preferential createPreferential(){
		Preferential preferential = new Preferential();
		preferential.setChargeRebate(1d);
		preferential.setAgentgathRate(1d);
		preferential.setInsuredPriceRate(1d);
		preferential.setReceivePriceRate(1d);
		preferential.setDeliveryPriceRate(1d);
		
		return preferential;
	}

}
