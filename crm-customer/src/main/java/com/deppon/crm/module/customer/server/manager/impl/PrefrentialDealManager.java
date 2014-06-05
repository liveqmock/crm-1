package com.deppon.crm.module.customer.server.manager.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.deppon.crm.module.customer.server.manager.IPrefrentialDealManager;
import com.deppon.crm.module.customer.server.manager.PrefrentialValidator;
import com.deppon.crm.module.customer.server.service.IPrefrentialDealService;
import com.deppon.crm.module.customer.server.utils.ContextUtil;
import com.deppon.crm.module.customer.server.utils.ContractUtil;
import com.deppon.crm.module.customer.server.utils.ValidateUtil;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.PrefrentialDeal;
import com.deppon.crm.module.customer.shared.domain.PrefrentialDealItem;
import com.deppon.crm.module.customer.shared.exception.ContractException;
import com.deppon.crm.module.customer.shared.exception.ContractExceptionType;
/**
 * 
 * <p>
 * Description:优惠方案manager管理<br />
 * </p>
 * @title PrefrentialDealManager.java
 * @package com.deppon.crm.module.customer.server.manager.impl 
 * @author 106138
 * @version 0.1 2014年4月17日
 */
@Transactional
public class PrefrentialDealManager implements IPrefrentialDealManager{
	//日志
	private Logger log = Logger.getLogger(PrefrentialDealManager.class);
	//校验
	private PrefrentialValidator prefrentialValidator;
	//service
	private IPrefrentialDealService prefrentialDealService;
	
	public void setPrefrentialValidator(PrefrentialValidator prefrentialValidator) {
		this.prefrentialValidator = prefrentialValidator;
	}
	public void setPrefrentialDealService(
			IPrefrentialDealService prefrentialDealService) {
		this.prefrentialDealService = prefrentialDealService;
	}

	/**
	 * 
	 * @Title: createPrefrentialDeal
	 *  <p>
	 * @Description: 新月发月送优惠方案增加<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-9
	 * @return void
	 * @throws
	 */
	@Override
	public void createPrefrentialDeal(PrefrentialDeal prefrentialDeal) {
		prefrentialValidator.validatorCreatePrefrentialDeal(prefrentialDeal);
		prefrentialValidator.validateAmountMix(prefrentialDeal.getDealItems());
		//设置创建人为当前用户
		prefrentialDeal.setCreateUser(ContextUtil.getCreateOrModifyUserId());
		String id=prefrentialDealService.getDealId();
		//获得自动生成的方案编码
		String dealNumber = produceDealNumber(id);
		prefrentialDeal.setId(id);
		prefrentialDeal.setDealNumber(dealNumber);
		//设置创建日期
		Date nowTime = new Date();
		prefrentialDeal.setCreateDate(nowTime);
		prefrentialDeal.setEndTime(ContractUtil.getEndTime(prefrentialDeal.getEndTime()));
		//如果开始时间就是当天，则立即生效，否则设为待生效
		if(prefrentialDeal.getBeginTime().equals(ContractUtil.getNowTime(null))){
			//tobeEndDeal即需要作废的方案
			PrefrentialDeal tobeEndDeal = new PrefrentialDeal();
			tobeEndDeal.setPreferType(prefrentialDeal.getPreferType());
			tobeEndDeal.setStatus(Constant.CONTRACT_STATUS_EFFECT);
			List<PrefrentialDeal> dealList = searchAllPrefrentialDeal(tobeEndDeal,0, Integer.MAX_VALUE);
			for(PrefrentialDeal deal:dealList){
				deal.setEndTime(new Date(nowTime.getTime()-1000));
				deal.setStatus(Constant.CONTRACT_STATUS_UNEFFECT);
				deal.setModifyUser(ContextUtil.getCreateOrModifyUserId());
				deal.setModifyDate(nowTime);
				prefrentialDealService.endEffectiveDeal(deal);
			}
			prefrentialDeal.setStatus(Constant.CONTRACT_STATUS_EFFECT);
			prefrentialDeal.setBeginTime(nowTime);
		}else {
			prefrentialDeal.setStatus(Constant.CONTRACT_STATUS_WAIT_EFFECT);
		}
		prefrentialDealService.createPrefrentialDeal(prefrentialDeal);		
	}
	/**
	 * 
	 * @Title: updatePrefrentialDeal
	 *  <p>
	 * @Description: 新月发月送折扣方案修改<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-9
	 * @return void
	 * @throws
	 */
	@Override
	public void updatePrefrentialDeal(PrefrentialDeal prefrentialDeal) {
		//校验传入的对象是否为空，如果为空的话，则抛出异常提示
		if(ValidateUtil.objectIsEmpty(prefrentialDeal)){
			throw new ContractException(ContractExceptionType.NULLDEALERROR);
		}
		PrefrentialDeal deal = prefrentialDealService.searchPrefrentialDealById(prefrentialDeal.getId());
		//校验数据是否已经被修改
		if(!ValidateUtil.objectIsEmpty(prefrentialDeal)){
			List<PrefrentialDealItem> itemList = prefrentialDeal.getDealItems();
			List<PrefrentialDealItem> itemListDataBase = deal.getDealItems();
			prefrentialValidator.validateRateChange(itemListDataBase, itemList);
			//校验折扣率是否被修改
			if (prefrentialValidator.validateRateIsChanged(itemListDataBase, itemList)) {
				throw new ContractException(ContractExceptionType.RATEISNOTCHANGED);
			}
			prefrentialValidator.validateAmountMix(itemList);
		}
		//设置最后修改人
		prefrentialDeal.setModifyUser(ContextUtil.getCreateOrModifyUserId());
		if(!deal.getStatus().equals(Constant.CONTRACT_STATUS_EFFECT)){
			throw new ContractException(ContractExceptionType.ONLYCANMODIFYEFFECTCONTRACT);
		}
		//tobeEndDeal即需要作废的方案
		PrefrentialDeal tobeEndDeal = new PrefrentialDeal();
		Date nowTime = new Date();
		tobeEndDeal.setEndTime(new Date(nowTime.getTime()-1000));
		tobeEndDeal.setStatus(Constant.CONTRACT_STATUS_UNEFFECT);
		tobeEndDeal.setModifyUser(ContextUtil.getCreateOrModifyUserId());
		tobeEndDeal.setModifyDate(nowTime);
		tobeEndDeal.setId(prefrentialDeal.getId());
		//将原来的有效基础资料作废掉
		prefrentialDealService.endEffectiveDeal(tobeEndDeal);
		
		prefrentialDeal.setBeginTime(nowTime);
		String id=prefrentialDealService.getDealId();
		//重新生成生成的方案编码
		String dealNumber = produceDealNumber(id);
		prefrentialDeal.setId(id);
		prefrentialDeal.setDealNumber(dealNumber);
		//设置创建人为当前用户
		prefrentialDeal.setCreateUser(ContextUtil.getCreateOrModifyUserId());
		//设置创建时间
		prefrentialDeal.setCreateDate(nowTime);
		prefrentialDeal.setEndTime(ContractUtil.getEndTime(prefrentialDeal.getEndTime()));
		//直接生效
		prefrentialDeal.setStatus(Constant.CONTRACT_STATUS_EFFECT);
		prefrentialDealService.createPrefrentialDeal(prefrentialDeal);
	
	}
	/**
	 * 
	 * @Title: activePrefrentialDeal
	 *  <p>
	 * @Description: 新月发月送折扣方案激活<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-9
	 * @return void
	 * @throws
	 */
	@Override
	public void activePrefrentialDeal(PrefrentialDeal prefrentialDeal) {
		if (null==prefrentialDeal || StringUtils.isEmpty(prefrentialDeal.getId())) {
			throw new ContractException(ContractExceptionType.NULLIDERROR);
		}
		PrefrentialDeal deal = prefrentialDealService.searchPrefrentialDealById(prefrentialDeal.getId());
		if (!deal.getStatus().equals(Constant.CONTRACT_STATUS_WAIT_EFFECT)) {
			throw new ContractException(ContractExceptionType.DEALCANNOTACTIVE);		
		}
		
		PrefrentialDeal searchDeal = new PrefrentialDeal();
		searchDeal.setStatus(Constant.CONTRACT_STATUS_EFFECT);
		searchDeal.setPreferType(prefrentialDeal.getPreferType());
		
		List<PrefrentialDeal> deals = prefrentialDealService
				.searchAllPrefrentialDeal(searchDeal, 0, Integer.MAX_VALUE);		
		if (null==deals || 0 >= deals.size()) {
			log.warn("have no effect deal");
		}else if (deals.size()>1) {
			log.warn("have  more than one effect deal");
		}
		
		Date nowTime = new Date();
		deal.setModifyUser(ContextUtil.getCreateOrModifyUserId());
		deal.setStatus(Constant.CONTRACT_STATUS_EFFECT);
		deal.setBeginTime(nowTime);
		deal.setModifyDate(nowTime);
		
		//tobeEndDeal即需要作废的方案
		if (null!=deals && 0 < deals.size()) {
			PrefrentialDeal tobeEndDeal = new PrefrentialDeal();
			tobeEndDeal.setEndTime(new Date(nowTime.getTime()-1000));
			tobeEndDeal.setStatus(Constant.CONTRACT_STATUS_UNEFFECT);
			tobeEndDeal.setModifyUser(ContextUtil.getCreateOrModifyUserId());
			tobeEndDeal.setModifyDate(nowTime);
			tobeEndDeal.setId(deals.get(0).getId());
			prefrentialDealService.endEffectiveDeal(tobeEndDeal);
		}
		
		prefrentialDealService.activePrefrentialDeal(deal);
		} 
	/**
	 * 
	 * @Title: searchPrefrentialDealById
	 *  <p>
	 * @Description: 通过Id查找新月发月送折扣方案<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-9
	 * @return PrefrentialDeal
	 * @throws
	 */
	@Override
	public PrefrentialDeal searchPrefrentialDealById(String id) {
		PrefrentialDeal prefrentialDeal = null;
		if (StringUtils.isEmpty(id)) {
			throw new ContractException(ContractExceptionType.NULLIDERROR);
		}
		prefrentialDeal = prefrentialDealService.searchPrefrentialDealById(id);
		return prefrentialDeal;
	}
	/**
	 * 
	 * @Title: endPrefrentialDeal
	 *  <p>
	 * @Description: 新月发月送折扣方案失效<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-9
	 * @return void
	 * @throws
	 */
	@Override
	public void endPrefrentialDeal(String id) {
		PrefrentialDeal prefrentialDeal = null;
		if (StringUtils.isEmpty(id)) {
			throw new ContractException(ContractExceptionType.NULLIDERROR);
		}
		prefrentialDeal = prefrentialDealService.searchPrefrentialDealById(id);
		if (!prefrentialDeal.getStatus().equals(Constant.CONTRACT_STATUS_EFFECT)) {
			throw new ContractException(ContractExceptionType.NOTEFFECTDEALERROR);
		}
		prefrentialDeal.setId(id);
		prefrentialDeal.setModifyUser(ContextUtil.getCreateOrModifyUserId());
		prefrentialDeal.setStatus(Constant.CONTRACT_STATUS_UNEFFECT);
		prefrentialDealService.endPrefrentialDeal(prefrentialDeal);
		}
	/**
	 * 
	 * @Title: searchAllPrefrentialDeal
	 *  <p>
	 * @Description: 初始化所有折扣方案<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-9
	 * @return List<PrefrentialDeal>
	 * @throws
	 */
	@Override
	public List<PrefrentialDeal> searchAllPrefrentialDeal(PrefrentialDeal prefrentialDeal,int start, int limit) {
		return prefrentialDealService.searchAllPrefrentialDeal(prefrentialDeal,start,limit);
	}
	/**
	 * 
	 * @Title: deletePrefrentialDeal
	 *  <p>
	 * @Description: 删除新月发月送折扣方案<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-9
	 * @return void
	 * @throws
	 */
	@Override
	public void deletePrefrentialDeal(String id) {
		if (StringUtils.isEmpty(id)) {
			throw new ContractException(ContractExceptionType.NULLIDERROR);
		}
		PrefrentialDeal deal = prefrentialDealService.searchPrefrentialDealById(id);
		//只能删除状态为待生效的方案
		if (!deal.getStatus().equals(Constant.CONTRACT_STATUS_WAIT_EFFECT)) {
			throw new ContractException(ContractExceptionType.DEALSTATUSERROR);	
		}
		prefrentialDealService.deletePrefrentialDeal(id);
		}
	/**
	 * 
	 * @Title: createPrefrentialDeal
	 *  <p>
	 * @Description: 根据条件查询月发月送方案<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-9
	 * @return  List<PrefrentialDeal>
	 * @throws
	 */
	@Override
	public List<PrefrentialDeal> searchEffectPrefrentialDeal(
			PrefrentialDeal prefrentialDeal) {
		
		List<PrefrentialDeal> dealList = null;
		if(!ValidateUtil.objectIsEmpty(prefrentialDeal)){
			dealList = prefrentialDealService.getEffectPrefrentialDeal(prefrentialDeal);
		}
		return dealList;
	}
	/**
	 * 
	 * @Title: countPrefrentialDeal
	 *  <p>
	 * @Description: 查询月发月送新优惠方案的总条数<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-14
	 * @return int
	 * @throws
	 */
	@Override
	public int countPrefrentialDeal(PrefrentialDeal prefrentialDeal) {
		return prefrentialDealService.countPrefrentialDeal(prefrentialDeal);
	}
	private String produceDealNumber(String num){
		if(num.length()>=6){
			return num.substring(0,6);
		}else {
			return StringUtils.leftPad(num, 6,'0');
			}
		}
	/**
	 * 
	 * @Title: prefrentialdealAutoWork
	 *  <p>
	 * @Description: 该方法用于定时器调用
	 * 				失效应该今日失效的优惠方案，
	 * 				生效应该今日生效的优惠方案<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-15
	 * @return void
	 * @throws
	 */
	@Override
	public void prefrentialdealAutoWork() {
		//先更新月发越折
		PrefrentialDeal deal = new PrefrentialDeal();
		deal.setPreferType(Constant.CONTRACT_PREFERENTIALTYPE_MONTH_REBATE);
		List<PrefrentialDeal> dealList = searchAllPrefrentialDeal(deal,0, Integer.MAX_VALUE);
		if (ValidateUtil.objectIsEmpty(dealList)) {
			log.info("NO data is found");
		}else{
			effectDeal(dealList);
		}
		//更新月发月送
		deal = new PrefrentialDeal();
		deal.setPreferType(Constant.CONTRACT_PREFERENTIALTYPE_MONTH_SEND);
		dealList = searchAllPrefrentialDeal(deal,0, Integer.MAX_VALUE);
		if (ValidateUtil.objectIsEmpty(dealList)) {
			log.info("NO data is found");
		}else{
			effectDeal(dealList);
		}

	}
	/**
	 * 
	 * <p>
	 * Description:更新方案的有效与无效<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2013-10-8
	 * @param dealList
	 * void
	 */
	private void effectDeal(List<PrefrentialDeal> dealList) {

		PrefrentialDeal toEndDeal= null;
		PrefrentialDeal effectDeal = null;
		for(PrefrentialDeal prefrentialDeal : dealList){
			if ( prefrentialDeal.getBeginTime().before(new Date())
					&&prefrentialDeal.getStatus().equals(Constant.CONTRACT_STATUS_WAIT_EFFECT)) {
				effectDeal=prefrentialDeal;
			}
			if ( prefrentialDeal.getBeginTime().before(new Date())&&prefrentialDeal.getEndTime().after(new Date())
					&&prefrentialDeal.getStatus().equals(Constant.CONTRACT_STATUS_EFFECT)) {
				toEndDeal=prefrentialDeal;
			}
			if (!prefrentialDeal.getEndTime().after(ContractUtil.getNowTime(null))
					&&prefrentialDeal.getStatus().equals(Constant.CONTRACT_STATUS_EFFECT)) {
				Date date = new Date();
				prefrentialDeal.setEndTime(date);
				prefrentialDeal.setStatus(Constant.CONTRACT_STATUS_UNEFFECT);
				prefrentialDeal.setModifyUser(ContextUtil.getCreateOrModifyUserId());
				prefrentialDeal.setModifyDate(date);
				prefrentialDealService.updatePrefrentialDeal(prefrentialDeal);
			}
		}
		if (null!=toEndDeal && null!=effectDeal) {
			PrefrentialDeal tobeEndDeal = new PrefrentialDeal();
			tobeEndDeal.setEndTime(new Date(effectDeal.getBeginTime().getTime()-1000));
			tobeEndDeal.setStatus(Constant.CONTRACT_STATUS_UNEFFECT);
			tobeEndDeal.setModifyUser(ContextUtil.getCreateOrModifyUserId());
			tobeEndDeal.setModifyDate(effectDeal.getBeginTime());
			tobeEndDeal.setId(toEndDeal.getId());
			effectDeal.setModifyUser(ContextUtil.getCreateOrModifyUserId());
			effectDeal.setModifyDate(effectDeal.getBeginTime());
			effectDeal.setStatus(Constant.CONTRACT_STATUS_EFFECT);
			//失效掉当前正有效的方案
			prefrentialDealService.endEffectiveDeal(tobeEndDeal);
			//生效今日应当生效的方案
			prefrentialDealService.activePrefrentialDeal(effectDeal);		
		}
	}
}
