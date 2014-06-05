package com.deppon.crm.module.recompense.server.ws;


import java.util.List;

import com.deppon.crm.module.recompense.shared.domain.AccidentResult;
import com.deppon.crm.module.recompense.shared.domain.OAWorkflow;
import com.deppon.crm.module.recompense.shared.domain.RecompenseApplication;
import com.deppon.crm.module.recompense.shared.domain.Waybill;

/**
 * 描述:理赔外部接口
 * 
 * @author huangzhanming 创建日期 :2012-1-6
 */

public interface OAClient {


	// 是否有异常签收差错
	public boolean getAbnormalSignState(String number);

	// 异常签收差错处理信息
	public List<AccidentResult> getAbnormalSignMistake(String number);

	// 是否有丢货差错
	public boolean getLostGoodsState(String number);

	// 丢货差错处理信息
	public List<AccidentResult> getLostGoodsMistake(String number);

	// 是否有未开单差错
	public boolean getUnbilledState(String number);

	// 未开单差错信息
	public List<AccidentResult> getUnbilledMistake(String number);

	// 查询运单的信息
	public Waybill getWaybillByNum(String number);

	// 查询ERP运单是否异常签收
	public boolean getWaybillAbnormalSignState(String number);

	// 查询未开单的运单信息
	public Waybill getUnbilledWaybill(String number);

	// 提交付款申请
	OAWorkflow submitPaymentApply(RecompenseApplication recompense);

}
