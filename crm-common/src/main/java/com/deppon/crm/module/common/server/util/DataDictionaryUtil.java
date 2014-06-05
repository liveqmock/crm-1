package com.deppon.crm.module.common.server.util;

import java.util.ArrayList;
import java.util.List;

import com.deppon.crm.module.common.shared.domain.DataHeadTypeEnum;
import com.deppon.crm.module.common.shared.domain.Detail;
import com.deppon.crm.module.common.shared.domain.Head;
import com.deppon.crm.module.common.shared.exception.DataDictionaryException;
import com.deppon.crm.module.common.shared.exception.DataDictionaryExceptionType;
import com.deppon.foss.framework.cache.CacheManager;
import com.deppon.foss.framework.cache.ICache;

/**
 * @作者：罗典
 * @时间：2012-3-12
 * @功能：数据字典转换工具
 * */
public class DataDictionaryUtil {
	/**
	 * @作者：罗典
	 * @时间：2012-3-12
	 * @功能：通过类型和code获得类型描述
	 * */
	public static String getCode(DataHeadTypeEnum type, String value) {
		if (value == null || value.trim().equals("")) {
			return value;
		}
		List<Detail> detailList = new ArrayList<Detail>();
		detailList = getDetailList(type);
		for (Detail detail : detailList) {
			if (detail.getCodeDesc().equals(value)) {
				return detail.getCode();
			}
		}
		throw new DataDictionaryException(DataDictionaryExceptionType.HasNoValue.getErrCode(),new Object[]{type.getValue(),value});	
	}

	/**
	 * @作者：罗典
	 * @时间：2012-3-12
	 * @功能：通过类型和描述获得类型code
	 * */
	public static String getCodeDesc(DataHeadTypeEnum type, String code) {
		if (code == null || code.trim().equals("")) {
			return code;
		}
		List<Detail> detailList = new ArrayList<Detail>();
		detailList = getDetailList(type);
		for (Detail detail : detailList) {
			if (detail.getCode().equals(code)) {
				return detail.getCodeDesc();
			}
		}
		throw new DataDictionaryException(DataDictionaryExceptionType.HasNoCode.getErrCode(),new Object[]{type.getValue(),code});	
	}

	/**
	 * @作者：罗典
	 * @时间：2012-3-12
	 * @功能：通过类型获取下拉框列表集合
	 * */
	public static List<Detail> getDetailList(DataHeadTypeEnum type) {
		ICache<String, Head> dataDictionaryCache = CacheManager.getInstance()
				.getCache(Head.class.getName());
		List<Detail> dList = null;
		if (null!=dataDictionaryCache.get(type.toString())) {
			return dList=dataDictionaryCache.get(type.toString()).getDetailList();
		}
		return dList;
	}
	
}
