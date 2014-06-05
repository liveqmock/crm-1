package com.deppon.crm.module.frameworkimpl.server.filter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * 
*******************************************
* <b style="font-family:微软雅黑"><small>Description:页面信息缓存</small></b>   </br>
* <b style="font-family:微软雅黑"><small>HISTORY</small></b></br>
* <b style="font-family:微软雅黑"><small> ID      DATE    PERSON     REASON</small></b><br>
********************************************
* <div style="font-family:微软雅黑,font-size:70%"> 
* 1  2012-2-16 钟庭杰  新增
* </div>  
********************************************
 */
public class I18nMsgAndPerPropCache {

	private static final I18nMsgAndPerPropCache INSTANCE = new I18nMsgAndPerPropCache();

	/**
	 * 保存所有缓存实例
	 */
	private final Map<String, Object> caches = new ConcurrentHashMap<String, Object>();

	/**
	 *  禁止从外部拿到实例
	  * 创建一个新的实例 PageMessageCache.
	  * @since 0.6
	 */
	private I18nMsgAndPerPropCache() {
	}

	public static I18nMsgAndPerPropCache getInstance() {
		return INSTANCE;
	}

	/**
	 * 注册页面国际化信息或页面权限配置信息
	 * 
	 * @param messageInfoId 页面国际化信息ID或页面权限信息ID
	 * @param messageInfo 页面国际化信息或页面权限配置信息
	 */
	public void registerMessageInfo(String messageInfoId,Object messageInfo) {
		if (caches.containsKey(messageInfoId)) {
			caches.put(messageInfoId, messageInfo);	
		}
	}

	/**
	 * 根据messageInfoId获取缓存实例
	 * getMessageInfo
	 * @param messageInfoId
	 * @return
	 * @return Object
	 * @since:
	 */
	public Object getMessageInfo(String messageInfoId) {
		Object messageInfo = caches.get(messageInfoId);
		return messageInfo;
	}
}