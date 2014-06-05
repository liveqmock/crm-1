package com.deppon.crm.module.client.complaint;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;

/**
 * 呼叫中心投诉相关操作
 * @author davidcun @2012-5-26
 */
public interface IComplaintOperate {

	/**
	 * CRM反写投诉(工单)编号到呼叫中心与服务编码进行关联
	 * @param       
	 * @return      
	 * @Throws 
	 * @author davidcun 2012-5-26
	 *
	 */
	public boolean bindComplaintId2ServiceNumber(String serviceNumber,String complaintId) throws CrmBusinessException;

	/**
	 * @Description:CRM反写投诉(工单)编号到合肥呼叫中心与服务编码进行关联<br />
	 * @author CoCo
	 * @version 0.1 2013-11-11
	 * @param serviceNumber
	 * @param complaintId
	 * @return boolean
	 * @throws CrmBusinessException
	 */
	public boolean bindHfComplaintId2ServiceNumber(String serviceNumber,String complaintId) throws CrmBusinessException;
}
