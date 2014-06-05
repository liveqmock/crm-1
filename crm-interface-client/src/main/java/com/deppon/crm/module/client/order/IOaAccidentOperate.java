package com.deppon.crm.module.client.order;

import java.util.List;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.order.domain.ComplaintInformInfo;
import com.deppon.crm.module.client.order.domain.OaAccidentInfo;

/**
 * 关于理赔的差错信息查询相关接口
 * @author davidcun @2012-3-20
 */
public interface IOaAccidentOperate {

	/**
	 * <p>需求点：OA-5</p>
	 * 通过运单号查询所有差错信息
	 * @author davidcun 2012-3-20
	 * @param  运单号     
	 * @return 差错信息集合     
	 * @Throws 
	 *
	 */
	public List<OaAccidentInfo> queryAccidentByWaybillCode(String waybillNumber) throws CrmBusinessException;
	
	/**
	 * <p>需求点：OA-10</p>
	 * 通过差错编号查询差错信息（未开单的差错），可能返回空
	 * @author davidcun 2012-3-20
	 * @param   code 差错编号
	 * @return  差错信息数据
	 * @Throws  
	 *
	 */
	public OaAccidentInfo queryAccidentByAccidentCode(String errorCode) throws CrmBusinessException;
	
	/**
	 * <p>需求点：OA-7</p>
	 * 投诉通知消息，在指定人员的OA的待办事宜中提醒“待投诉反馈（投诉数量）”
	 * @param   informInfos 投诉通知消息集合    
	 * @return  操作是否是否   
	 * @Throws 
	 * @author davidcun 2012-4-6
	 *
	 */
	public boolean informComplaint(List<ComplaintInformInfo> informInfos) throws CrmBusinessException;
}
