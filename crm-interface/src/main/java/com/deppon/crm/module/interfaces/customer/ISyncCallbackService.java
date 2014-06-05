package com.deppon.crm.module.interfaces.customer;

import javax.jws.WebService;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;

/**
 * @作者：罗典
 * @时间：2012-7-23
 * @描述 数据同步错误接收回调接口
 */
@WebService
public interface ISyncCallbackService {
	/**
	 * 数据同步错误处理
	 * 
	 * @param requestId
	 *            数据同步请求标识（ESBJsonRequest.requestId）
	 * @param targetSystem
	 *            数据接收方（如果多个系统出错会分成多条发送）
	 * @param level
	 *            错误级别（100：业务异常；300：请求后端服务超时；500：网络错误；900：请求不匹配需改程序；）
	 * @param errorMsg
	 *            错误信息
	 */
	public void handle(String requestId, String targetSystem, int level,
			String errorMsg) throws CrmBusinessException;
}
