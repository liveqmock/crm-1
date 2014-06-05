package com.deppon.crm.module.client.order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.order.domain.CustomerWaybillInfo;
import com.deppon.crm.module.client.order.domain.FossWaybillInfo;
import com.deppon.crm.module.client.order.domain.WaybillInfo;
import com.deppon.crm.module.client.order.domain.WaybillRecompenseInfo;

/** 
 * 与ERP运单相关的操作的集合接口
 * @author davidcun @2012-3-21
 */
public interface IWaybillOperate {


	/**
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 105681
	 * @version 0.1 2014-4-15
	 * @param custNum
	 * @param beginTime
	 * @param endTime
	 * @param page
	 * @param size
	 * @return
	 * @throws CrmBusinessException
	 * CustomerWaybillInfo
	 */
	public CustomerWaybillInfo queryCustomerWaybillInfo(String custNum,
			Date beginTime, Date endTime, int currentPage, int pageSize)
			throws CrmBusinessException;

	/**
	 * @作者：罗典
	 * @描述：根据运单号查询FOSS运单详情
	 * @时间：2012-11-7
	 * @参数：运单号
	 * @返回值：FOSS运单详情
	 * */
	public FossWaybillInfo queryWaybillInfo(String waybillNum)throws CrmBusinessException;

	/**
	 * <p>需求编号：ERP-10</p>
	 * 查询客户发到货金额信息，时时创建会员信息
	 * @param mobilePhone 客户手机
	 * @param customerName 客户姓名
	 * @param customerTelephone 客户固定电话
	 * @return
	 * @Throws
	 * @author davidcun 2012-4-9
	 *
	 */
	public List<WaybillInfo> queryWaybillMoney(String mobilePhone,String customerName,String customerTelephone) throws CrmBusinessException;

	/**
	 * <p>需求编号：ERP-11</p>
	 * 根据运单号查询 未签收的运单信息，定运单关联
	 * @author davidcun 2012-3-24
	 * @param  运单号
	 * @return  运单信息
	 * @Throws
	 *
	 */
	public WaybillInfo queryNotClosedWaybill(String waybillNumber) throws CrmBusinessException;

	/**
	 * <p>需求编号：ERP-12</p>
	 * 理赔上报的时候需要查询运单信息
	 * @param   waybillNumber 运单号
	 * @return  运单信息
	 * @Throws
	 * @author davidcun 2012-4-9
	 *
	 */
	public WaybillInfo queryWaybillRecompense(String waybillNumber) throws CrmBusinessException;

	/**
	 * <p>需求编号：ERP-9</p>
	 * 调用ERP的接口把运单和订单关联信息传递给ERP系统，如果知道新的运单
	 * @param  orderNumber 订单号
	 * @param waybillNumber 运单号
	 * @return 操作成功与否
	 * @Throws
	 * @author davidcun 2012-3-24
	 *
	 */
	public boolean linkWaybill(String orderNumber,String newWaybillNumber,String oldWaybillNumber) throws CrmBusinessException;

	/**
	 * ERP-13
	 * 在线理赔用的ERP信息
	 * @param
	 * @return
	 * @Throws
	 * @author davidcun 2012-4-24
	 */
	public WaybillRecompenseInfo queryWaybillOnlineRecompense(String waybillNumber) throws CrmBusinessException;

	/**
	 * ERP-14
	 * 通过运单查询收货客户编码和发货客户的编码，积分模块
	 * WaybillInof.
	 * @param
	 * @return
	 * @Throws
	 * @author davidcun 2012-5-3
	 *
	 */
	public WaybillInfo queryWaybillCustomer(String waybillNumber) throws CrmBusinessException;

	/**
	 * 查询客户近三月发货最高金额
	 * @param
	 * @return
	 * @Throws
	 * @author davidcun 2012-5-31
	 *
	 */
	public abstract BigDecimal queryNear3MonthAmount(String custNumber)
			throws CrmBusinessException;

}
