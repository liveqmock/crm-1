package com.deppon.crm.module.client.workflow;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.workflow.domain.BackFreightInfo;
import com.deppon.crm.module.client.workflow.domain.MuchRecompenseInfo;
import com.deppon.crm.module.client.workflow.domain.NormalRecompenseInfo;
import com.deppon.crm.module.client.workflow.domain.ServiceRecoveryInfo;


/**
 * 理赔相关工作流申请
 * @author davidcun
 * @2012-3-14
 * description
 */
public interface IRecompenseApplyOperate {
	
	/**
	 * @作者：罗典
	 * @描述：退运费申请接口
	 * @时间：2012-11-19
	 * @参数：退运费工作流申请实体接口
	 * @返回：工作流号
	 * */
	public String applyBackFreight(BackFreightInfo backFreightInfo) throws  CrmBusinessException;
	/**
	 * @作者：罗典
	 * @描述：服务补救工作流申请接口
	 * @时间：2012-11-19
	 * @参数：服务补救工作流申请实体接口
	 * @返回：工作流号
	 * */
	public String applyServiceRecovery(ServiceRecoveryInfo serviceRecoveryInfo) throws  CrmBusinessException;
	
	/**
	 * @作者：罗典
	 * @描述：校验OA是否存在有效时效延误差错
	 * @时间：2012-11-19
	 * @参数：运单号
	 * @返回：是否存在有效时效延误差错
	 * */
	public boolean checkOaGoodDelayError(String waybillNum) throws  CrmBusinessException;
	
	/**
	 * @作者：罗典
	 * @描述：校验财务是否存在有效坏账信息
	 * @时间：2012-11-19
	 * @参数：运单号
	 * @返回：是否存在有效坏账
	 * */
	public boolean checkBadDebt(String waybillNum) throws  CrmBusinessException;
	
	/**
	 * @作者：罗典
	 * @描述：劳务费状态修改
	 * @时间：2012-12-4
	 * @参数：运单号,是否可支付（true代表可支付，false代表不可支付。如果退运费起草成功，会传入false。如果退运费审批失败，传入true。）
	 * @返回：
	 * */
	public boolean ServiceChargeStatusUpdate(String waybillNum,boolean status) throws  CrmBusinessException;
	
	/**
	 * <p>需求点：OA-1</p>
	 * 常规理赔工作流审批申请
	 * description:
	 * @author davidcun 2012-3-14
	 * @param  {@link NormalRecompenseInfo} 是常规理赔工作流需要的参数信息，
	 * @return {@link String} 表示返回的是OA系统生成的工作流编号
	 * @exception 
	 *
	 */
	public String applyNormalRecompense(NormalRecompenseInfo normalRecompenseInfo) throws CrmBusinessException;
	
	/**
	 * <p>需求点：OA-3</p>
	 * 多陪工作流审批申请
	 * description:
	 * @author davidcun 2012-3-14
	 * @param     muchRecompenseInfo {@link MuchRecompenseInfo}多陪审批信息
	 * @return      {@link String} 返回工作流编号
	 * @exception 
	 *
	 */
	public String applyMuchRecompense(MuchRecompenseInfo muchRecompenseInfo) throws CrmBusinessException;
	
}
