package com.deppon.crm.module.customer.server.manager.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.deppon.crm.module.common.server.manager.IDetailManager;
import com.deppon.crm.module.common.server.util.DataDictionaryUtil;
import com.deppon.crm.module.common.shared.domain.DataHeadTypeEnum;
import com.deppon.crm.module.common.shared.domain.Detail;
import com.deppon.crm.module.customer.server.manager.IDictionaryManager;
import com.deppon.crm.module.customer.server.utils.ContextUtil;
import com.deppon.crm.module.customer.server.utils.ValidateUtil;
import com.deppon.crm.module.customer.shared.domain.TradeView;
import com.deppon.crm.module.customer.shared.exception.DictionaryException;
import com.deppon.crm.module.customer.shared.exception.DictionnaryExcetionType;
import com.deppon.crm.module.frameworkimpl.server.cache.DataDictionaryCache;
import com.deppon.foss.framework.cache.CacheManager;
/**
 * 
 * <p>
 * Description:二级行业<br />
 * </p>
 * @title DictionaryManager.java
 * @package com.deppon.crm.module.customer.server.manager.impl 
 * @author 106138
 * @version 0.1 2014年4月11日
 */
public class DictionaryManager implements IDictionaryManager {
	//二级行业父级描述获取的key
	private final String PARENT_DESC = "PARENT_DESC";
	private IDetailManager detailManager;

	@Override
	public void updateSecondTrade(List<Detail> saveDetailLists,
			List<Detail> modifyDetailLists, List<Detail> delDetailLists){
		
		String codeDescs = getAllCodeDesc(saveDetailLists,modifyDetailLists);
		Map<String,Map<String,String>> exitsCodeDescMap = null;
		if(codeDescs.length()>0){
			exitsCodeDescMap = detailManager.queryMapCodeDesc(DataHeadTypeEnum.SECOND_TRADE.toString(),codeDescs ) ;
		}
        
		boolean isNeedRefresh = false;
		//新增客户二级行业
		if(saveDetailLists!=null && saveDetailLists.size()>0){
			for (Detail detail : saveDetailLists) {
				detail.setCodeDesc(detail.getCodeDesc().trim());
			    if(!ValidateUtil.objectIsEmpty(exitsCodeDescMap.get(detail.getCodeDesc()))){
			    	throw new DictionaryException(DictionnaryExcetionType.CODE_DESC_EXITS,detail.getCodeDesc(), exitsCodeDescMap.get(detail.getCodeDesc()).get(PARENT_DESC));
			    }
				detail.setCode(UUID.randomUUID().toString().toUpperCase());
				detail.setCreateUser(ContextUtil.getCurrentUser().getId());
				detail.setLanguage("zh_CN");
				detail.setCodeSeq(0);
				detail.setCodeType(DataHeadTypeEnum.SECOND_TRADE.toString());
				isNeedRefresh = true;
				detailManager.insertDetail(detail);
				
			}
		}
		// 作废客户二级行业
		if(delDetailLists!=null&& delDetailLists.size()>0){
			for (Detail detail : delDetailLists) {
				detail.setModifyUser(ContextUtil.getCurrentUser().getId());
				delSecondTrade(detail);
				isNeedRefresh = true;
			}
		}
		//修改二级行业
		if(modifyDetailLists!=null&&modifyDetailLists.size()>0){
			for (Detail detail : modifyDetailLists) {
				detail.setCodeDesc(detail.getCodeDesc().trim());
			    if(!ValidateUtil.objectIsEmpty(exitsCodeDescMap.get(detail.getCodeDesc()))){
			    	throw new DictionaryException(DictionnaryExcetionType.CODE_DESC_EXITS,detail.getCodeDesc(), exitsCodeDescMap.get(detail.getCodeDesc()).get(PARENT_DESC));
			    }
				detail.setModifyUser(ContextUtil.getCurrentUser().getId());
				delSecondTrade(detail);
//				detail.setCode(UUID.randomUUID().toString());
				isNeedRefresh = true;
				detailManager.insertDetail(detail);
			}
		}
		//手动刷新数据字典缓存
		if(isNeedRefresh){
			DataDictionaryCache dataDictionaryCache = (DataDictionaryCache) CacheManager.getInstance().getCache(DataDictionaryCache.UUID);
			dataDictionaryCache.refresh();
		}
	}
	
	/**
	 * 
	 * <p>
	 * Description:作废二级行业<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-7-19
	 * @param detail
	 * @return
	 * boolean
	 */
	private boolean delSecondTrade(Detail detail){
		detail.setInvaliTime(getInvaliTime());
		return detailManager.delDetail(detail);
	}

	/**
	 * 
	 * <p>
	 * Description:根据一级行业ID查询有效的二级行业信息<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-7-17
	 * @return
	 * String
	 */
	@Override
	public List<Detail> searchSecondTradeByParentId(String parentId,
			boolean status) {
		if (ValidateUtil.objectIsEmpty(parentId)) {
			throw new DictionaryException(
					DictionnaryExcetionType.CODE_DESC_IS_NULL);
		}
		List<Detail> detailList = null;
		if (status) {
			Detail detail = new Detail();
			detail.setParentId(parentId);
			detail.setStatus(status);
			detailList = detailManager.queryDetail(detail);
		} else {
			detailList = getValidSecondTradeInCache(parentId, false);
		}

		return detailList;
	}
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param detailManager
	 * void
	 */
	public void setDetailManager(IDetailManager detailManager) {
		this.detailManager = detailManager;
	}

	/**
	 * 
	 * <p>
	 * Description:获取当前时间1个月之后的时间<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-7-19
	 * @return
	 * Date
	 */
	private Date getInvaliTime(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)+1);
		
		return calendar.getTime();
	}

	/**
	 * <p>
	 * Description:根据parentId查询未超过失效时间的二级行业<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-7-19
	 * @param parentId
	 * @return
	 * List<Detail>
	 */
	@Override
	public List<Detail> searchMonthValidSecondTrade(String parentId) {
		if(ValidateUtil.objectIsEmpty(parentId)){	throw new DictionaryException(DictionnaryExcetionType.CODE_DESC_IS_NULL); }
		return getValidSecondTradeInCache(parentId,true);
	}

	/***
	 * 
	 * <p>
	 * Description:根据名称查询二级行业信息<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-7-20
	 * @return
	 * List<Detail>
	 */
	@Override
	public List<TradeView> searchDictionary(String secondTradeName) {
		List<TradeView> tradeVieList = new ArrayList<TradeView>();
		if(ValidateUtil.objectIsEmpty(secondTradeName)){throw new DictionaryException(DictionnaryExcetionType.CODE_DESC_IS_NULL);}
		   Detail detail = new Detail();
		   detail.setCodeDesc(secondTradeName);
		   detail.setCodeType(DataHeadTypeEnum.SECOND_TRADE.toString());
		   detail.setStatus(true);
		   List<Detail> secondTradeList = detailManager.queryDetail(detail);
		   TradeView tradeView;
		   for (Detail detail2 : secondTradeList) {
			   tradeView = getTradeViewByExists(detail2.getParentId(),tradeVieList);
			   if(tradeView!=null){
				   tradeView.getSecondTrade().add(detail2);
			   }else{
				   tradeView = getTradeViewByCache(detail2.getParentId());
				   tradeView.getSecondTrade().add(detail2);
				   tradeVieList.add(tradeView);
			   }  
		}    
		
		return tradeVieList;
	}

	/***
	 * 
	 * <p>
	 * Description:根据二级行业parentId，从系统缓存中获取一个行业view  <br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-7-20
	 * @param id
	 * @param tradeViewList
	 * @return
	 * TradeView
	 */
	private TradeView getTradeViewByCache(String id){
		if(ValidateUtil.objectIsEmpty(id)){throw new DictionaryException(DictionnaryExcetionType.CODE_DESC_IS_NULL);}

		// 从缓存中查询一级行业detail ,创建一个view
	  List<Detail> detailList =DataDictionaryUtil.getDetailList(DataHeadTypeEnum.TRADE);
	  for (Detail detail : detailList) {
		if(id.equals(detail.getId())){
			TradeView tradeView = new TradeView();
			tradeView.setId(id);
			tradeView.setCodeDesc(detail.getCodeDesc());
			tradeView.setCodeSeq(detail.getCodeSeq());
			tradeView.setCodeType(detail.getCodeType());
			tradeView.setLanguage(detail.getLanguage());
			tradeView.setCode(detail.getCode());
           return tradeView;
		}
	}
	  return null;
	}
	
	/***
	 * 
	 * <p>
	 * Description:根据二级行业parentId，从现有List集合中获取一个行业view <br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-7-20
	 * @param id
	 * @param tradeViewList
	 * @return
	 * TradeView
	 */
	private TradeView getTradeViewByExists(String id,List<TradeView> tradeViewList){
		if(ValidateUtil.objectIsEmpty(id)){throw new DictionaryException(DictionnaryExcetionType.CODE_DESC_IS_NULL);}

		//从现有集合中查找一级行业view
		for (TradeView tradeView : tradeViewList) {
			if(id.equals(tradeView.getId())){
				return tradeView;
			}
		}
	  return null;
	} 
	
	/**
	 * 
	 * <p>
	 * Description:获取所有保存/修改集合中的 二级行业描述信息，拼接字符串形式<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-7-20
	 * @param saveList
	 * @param updateList
	 * @return
	 * String
	 */
	private String getAllCodeDesc(List<Detail> saveList,List<Detail>updateList){
		StringBuilder sb = new StringBuilder();
		for (Detail detail : saveList) {
			if(ValidateUtil.objectIsEmpty(detail.getCodeDesc())){
		    	throw new DictionaryException(DictionnaryExcetionType.CODE_DESC_IS_NULL);
			}
			sb.append("'").append(detail.getCodeDesc().trim()).append("',");
		}
		
		for (Detail detail : updateList) {
			if(ValidateUtil.objectIsEmpty(detail.getCodeDesc())){
		    	throw new DictionaryException(DictionnaryExcetionType.CODE_DESC_IS_NULL);
			}
			sb.append("'").append(detail.getCodeDesc().trim()).append("',");
		}
		if(sb.length()>0){
		  sb.deleteCharAt(sb.length()-1);
		}
		return sb.toString();
	}
	/**
	 * 
	 * <p>
	 * Description:从缓存中根据parentId 查询二级行业信息<br />
	 * </p>
	 * @author 王明明
	 * @version 0.1 2013-7-20
	 * @param parentId 一级行业ID
	 * @param isInvalid 是否包含失效的二级行业信息，如果包含，则返回的结果集中包含未到过期时间的二级行业，否则只包含有效的二级行业信息
	 * @return
	 * List<Detail>
	 */
	private List<Detail> getValidSecondTradeInCache(String parentId,boolean isInvalid){
		 List<Detail> resultLists = new ArrayList<Detail>();
			List<Detail> detailLists = DataDictionaryUtil.getDetailList(DataHeadTypeEnum.SECOND_TRADE);
			if (null!=detailLists&&0<detailLists.size()) {
				for (Detail detail : detailLists) {
					if(parentId .equals (detail.getParentId())){
						if(detail.getStatus()){
							resultLists.add(detail);
						}else if(isInvalid&&detail.getInvaliTime().after(new Date())){
							resultLists.add(detail);
						}	
					}
				}	
			}
			  return resultLists;
	}
}
