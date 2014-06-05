package com.deppon.crm.module.recompense.server.ws;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.recompense.shared.domain.AccidentResult;
import com.deppon.crm.module.recompense.shared.domain.OAWorkflow;
import com.deppon.crm.module.recompense.shared.domain.RecompenseApplication;
import com.deppon.crm.module.recompense.shared.domain.Waybill;
/**
 * 
 * <p>
 * OA外部接口模拟类<br />
 * </p>
 * @title OAClientMock.java
 * @package com.deppon.crm.module.recompense.shared.ws 
 * @author bxj
 * @version 0.1 2012-2-21
 */
public class OAClientMock implements OAClient{
	
	public List<AccidentResult> accidentList;
	public Map<String, Waybill> wayBillMap;
	public Map<String, Waybill> unbilledMap;
	
	
	
	protected OAClientMock() {
		wayBillMap = new HashMap<String, Waybill>();
		unbilledMap = new HashMap<String, Waybill>();
		init();
	}
	// 1111,2222    异常签收 
	public boolean getAbnormalSignState(String number) {
		if("1111".equals(number)||"2222".equals(number)){
			return true;
		}
		return false;
	}

	public List<AccidentResult> getAbnormalSignMistake(String number) {
		// TODO Auto-generated method stub
		return null;
	}
	//3333 , 4444     丢货
	public boolean getLostGoodsState(String number) {
		if("3333".equals(number)||"4444".equals(number)){
			return true;
		}
		return false;
	}
	
	public List<AccidentResult> getLostGoodsMistake(String number) {
		// TODO Auto-generated method stub
		return null;
	}
	// 5555,6666   未开单
	public boolean getUnbilledState(String number) {
		if("5555".equals(number)||"6666".equals(number)){
			return true;
		}
		return false;
	}

	public List<AccidentResult> getUnbilledMistake(String number) {
		// TODO Auto-generated method stub
		return null;
	}

	public Waybill getWaybillByNum(String number) {
		return  wayBillMap.get(number);
	}

	public boolean getWaybillAbnormalSignState(String number) {
		// TODO Auto-generated method stub
		return false;
	}

	public Waybill getUnbilledWaybill(String number) {
		return unbilledMap.get(number);
	}
	
	
	//************************************假数据制造方法*************************
	public void init(){
  		wayBillMap.put("1111",createWaybill("1111","iphon4s","空运","上海派送中心",200d,"鲍相江","13588888888","成都","上海","木架","2/4/5",new Date()));
		wayBillMap.put("2222",createWaybill("2222","三地自行车","精准卡航","上海派送中心",200d,"鲍相江","13588888888","新疆","北京","木架","1/3/10",new Date()));
		wayBillMap.put("3333",createWaybill("3333","电视机","精准汽运","上海派送中心",200d,"鲍相江","13588888888","纽约","巴黎","木架","4/7/5",new Date()));
		wayBillMap.put("4444",createWaybill("4444","手电筒","海运","上海派送中心",200d,"鲍相江","13588888888","重庆","台北","木架","1/8/10",new Date()));
		unbilledMap.put("5555",createWaybill("5555","碗筷","空运","上海派送中心",200d,"鲍相江","13588888888","拉萨","昆明","木架","5/4/3",new Date()));
		unbilledMap.put("6666",createWaybill("6666","洗衣机","空运","上海派送中心",200d,"鲍相江","13588888888","上海","昆山","木架","6/3/8",new Date()));
	}
	
	public static Waybill createWaybill(String waybillNumber,
			String goodsName, String transType,
			String receiveDept,Double insurAmount,String insured,String telephone,
			String startStation,String endStation,String packaging,String pwv,Date sendDate){
		Waybill waybill = new Waybill();
		// waybill.setWaybillNumber(waybillNumber);
		// waybill.setGoodsName(goodsName);
		// waybill.setTransType(transType);
		// waybill.setReceiveDept(receiveDept);
		// waybill.setInsured(insured);
		// waybill.setTelephone(telephone);
		// waybill.setStartStation(startStation);
		// waybill.setPackaging(packaging);
		// waybill.setPwv(pwv);
		// waybill.setSendDate(sendDate);
		return waybill;
	}
	
	public static AccidentResult createAccidentResult(){
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.crm.module.recompense.server.ws.OAClient#submitPaymentApply
	 * (com.deppon.crm.module.recompense.shared.domain.RecompenseApplication)
	 */
	@Override
	public OAWorkflow submitPaymentApply(RecompenseApplication recompense) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}
