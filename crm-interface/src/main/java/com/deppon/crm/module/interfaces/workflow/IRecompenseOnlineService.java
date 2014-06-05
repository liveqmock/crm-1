package com.deppon.crm.module.interfaces.workflow;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebParam.Mode;
import javax.jws.WebService;
import javax.xml.ws.Holder;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.interfaces.workflow.domain.OnlineApplyInfo;
import com.deppon.crm.module.interfaces.workflow.domain.RecompenseOnlineQueryCondition;
import com.deppon.crm.module.interfaces.workflow.domain.RecompenseOnlineVerifyInfo;

/**
 * 在线理赔接口服务
 * @author davidcun @2012-3-22
 */
@WebService
public interface IRecompenseOnlineService {

	/**
	 * 在线理赔新增接口
	 * <p>需求编号：BHO-9</p>
	 * @author davidcun 2012-3-22
	 * @param  recompenseOnlineInfo {@link RecompenseOnlineInfo}      
	 * @return 返回理赔编号
	 * @Throws {@link CrmBusinessException} 业务信息
	 *
	 */
	public String createRecompenseOfOnline(@WebParam(name="createOnlineApplyInfo") OnlineApplyInfo onlineApplyInfo) throws CrmBusinessException;
	
	/**
	 * 更新理赔信息，理赔提交之后允许用户进行修改。特别是在付款失败后，可以进行修改。
	 * <p>需求编号：BHO-13</p>
	 * @param    recompenseOnlineInfo    
	 * @return   返回操作是否成功 
	 * @Throws   {@link CrmBusinessException}
	 * @author davidcun 2012-3-27
	 *
	 */
	public boolean updateRecompenseOfOnline(@WebParam(name="updateOnlineApplyInfo")OnlineApplyInfo onlineApplyInfo) throws CrmBusinessException;
	
	
	/**
	 * 根据在线理赔查询条件查询理赔相关信息，只需要查询如下信息即可：
	 * <p>运单号、理赔时间、理赔人、索赔金额、理赔状态</p>
	 * <p>需求编号：BHO-10、BHO-11</p>
	 * @param queryCondition {@link RecompenseOnlineQueryCondition} 在线理赔查询条件封装
	 * @return List<{@link RecompneseOnlineInfo}> 理赔信息 
	 * @Throws 
	 * @author davidcun 2012-3-28
	 *
	 */
	public List<OnlineApplyInfo> queryRecompenseOnlineInfos(@WebParam(name="queryCondition",mode=Mode.INOUT) Holder<RecompenseOnlineQueryCondition> queryCondition) throws CrmBusinessException;
	
	/**
	 * 根据在线理赔信息编码和网厅注册用户名查询在线理赔信息详情
	 * <p>需求编号：BHO-12</p>
	 * @param recompenseCode 在线理赔编号
	 * @param username 网厅注册用户名
	 * @return 理赔详细信息     
	 * @Throws {@link CrmBusinessException} 业务异常
	 * @author davidcun 2012-3-28
	 *
	 */
	public OnlineApplyInfo queryRecompenseOnlineEntity(@WebParam(name="waybillNum") String waybillNum,@WebParam(name="username") String username) throws CrmBusinessException;
	
	/**
	 * 取消在线理赔操作
	 * <p>需求编号：BHO-14</p>
	 * @param waybillNum 运单号
	 * @param username 网厅注册用户名     
	 * @return 操作是否成功     
	 * @Throws {@link CrmBusinessException} 业务操作异常
	 * @author davidcun 2012-3-28
	 *
	 */
	public boolean cancelRecompenseOnline(@WebParam(name="_waybillNum") String waybillNum,@WebParam(name="_username") String username) throws CrmBusinessException;
	
	
	/**
	 * <p>需求编号：BHO-8</p>
	 * 在险理赔验证某个运单单号是否可以理赔
	 * @param  orderNumber 运单号     
	 * @return 在线理赔验证信息，包括当前单号是否     
	 * @Throws {@link CrmBusinessException}
	 * @author davidcun 2012-4-7
	 *
	 */
	public RecompenseOnlineVerifyInfo getRecompenseOnlineVerifyInfo(@WebParam(name="waybillNumber") String waybillNumber) throws CrmBusinessException;
	
	/**
	 * <p>需求编号：BHO-15</p>
	 * 再次提交理赔信息，可能付款失败，然后需要再次提交付款相关信息
	 * @param       
	 * @return      
	 * @Throws 
	 * @author davidcun 2012-4-11
	 */

	public boolean paymentUpdate(@WebParam(name="paymentOnlineApplyInfo") OnlineApplyInfo onlineApplyInfo) throws CrmBusinessException;


}
