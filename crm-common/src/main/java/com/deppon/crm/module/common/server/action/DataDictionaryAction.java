package com.deppon.crm.module.common.server.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.common.shared.domain.Detail;
import com.deppon.crm.module.common.shared.domain.Head;
import com.deppon.foss.framework.cache.CacheManager;
import com.deppon.foss.framework.cache.ICache;
import com.deppon.foss.framework.server.web.action.AbstractAction;
import com.deppon.foss.framework.server.web.message.IMessageBundle;
import com.deppon.foss.framework.server.web.result.json.annotation.JSON;

/**
 * 数据字典WEB接入层
 * 
 */
public class DataDictionaryAction extends AbstractAction {

	//数据key
	private static final String DATACODE = "code";
	
	//数据value
	private static final String DATAVALUE = "codeDesc";
	private static final String PARENTID = "parentId";
	private static final String ID = "id";
	private static final String CODESEQ = "codeSeq";
	private static final String STATUS = "status";
	
	// 注入国际化类messageBundle
	private IMessageBundle messageBundle;

	// 提示信息
	private String message;

	// 数据字典信息
	private Map<String,List<Map<String,Object>>> dataDictionary = new HashMap<String,List<Map<String,Object>>>();

	// 数据字典KEY列表
	private List<String> dataDictionaryKeys;

	/**
	 * 通过数据字典key,得到所有的数据字典数据
	 */
	@JSON
	@SuppressWarnings("unchecked")
	public String queryAllDataDictionaryByKeys() {
		if(dataDictionaryKeys==null||dataDictionaryKeys.size()<=0){
			message = messageBundle.getMessage(getLocale(),
					"i18n.common.keysIsNullOrKeysSizeIsZero");
		}else{
			for(String key : dataDictionaryKeys){
				ICache<String, Head> dataDictionaryCache = CacheManager.getInstance().getCache(Head.class.getName());
				Head keyHead = dataDictionaryCache.get(key);
				List<Map<String,Object>> detailDataList = new ArrayList<Map<String,Object>>();
				if(keyHead==null){
					dataDictionary.put(key, detailDataList);
				}else{
					for(Detail detail : keyHead.getDetailList()){
						Map<String,Object> detailMap = new HashMap<String,Object>();
						detailMap.put(DATACODE,detail.getCode());
						detailMap.put(DATAVALUE, detail.getCodeDesc());
						detailMap.put(PARENTID, detail.getParentId());
						detailMap.put(ID, detail.getId());
						detailMap.put(CODESEQ, String.valueOf(detail.getCodeSeq()));
						detailMap.put(STATUS, detail.getStatus());
						detailDataList.add(detailMap);
					}
					//处理被修改的二级行业，只需要有效的二级行业，二级行业修改code一直，codeDesc不一致
					//被删除的二级行业也进行加载，以便显示详情
					for (int i = 0; i < detailDataList.size(); i++) {
						for (int j = i+1; j < detailDataList.size(); j++) {
							if (detailDataList.get(i).get(DATACODE).equals(detailDataList.get(j).get(DATACODE))) {
								if (!(Boolean) detailDataList.get(i).get(STATUS)) {
									detailDataList.remove(i);
									continue;
								}else if (!(Boolean) detailDataList.get(j).get(STATUS)) {
									detailDataList.remove(j);
								}
							}
						}
					}
					
					dataDictionary.put(keyHead.getCodeType(), detailDataList);
				}
			}
		}
		return SUCCESS;
	}

	public String getMessage() {
		return message;
	}

	public void setMessageBundle(IMessageBundle messageBundle) {
		this.messageBundle = messageBundle;
	}

	public Map<String, List<Map<String, Object>>> getDataDictionary() {
		return dataDictionary;
	}

	public void setDataDictionaryKeys(List<String> dataDictionaryKeys) {
		this.dataDictionaryKeys = dataDictionaryKeys;
	}
}
