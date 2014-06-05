package com.deppon.crm.module.customer.server.manager;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.deppon.crm.module.customer.server.utils.ContractUtil;
import com.deppon.crm.module.customer.server.utils.ValidateUtil;
import com.deppon.crm.module.customer.shared.domain.PrefrentialDeal;
import com.deppon.crm.module.customer.shared.domain.PrefrentialDealItem;
import com.deppon.crm.module.customer.shared.exception.ContractException;
import com.deppon.crm.module.customer.shared.exception.ContractExceptionType;
/**
 * 
 * <p>
 * Description:新月发月送折扣方案新增业务逻辑验证<br />
 * </p>
 * @title PrefrentialValidator.java
 * @package com.deppon.crm.module.customer.server.manager 
 * @author 106138
 * @version 0.1 2014年4月11日
 */
public class PrefrentialValidator {
	/**
	 * 
	 * @Title: validatorCreatePrefrentialDeal
	 *  <p>
	 * @Description: 新月发月送折扣方案新增业务逻辑验证<br />
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-3-9
	 * @return boolean
	 * @throws
	 */
	private IPrefrentialDealManager prefrentialDealManager;
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param prefrentialDealManager
	 * void
	 */
	public void setPrefrentialDealManager(
			IPrefrentialDealManager prefrentialDealManager) {
		this.prefrentialDealManager = prefrentialDealManager;
	}
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param prefrentialDeal
	 * @return
	 * boolean
	 */
	public boolean validatorCreatePrefrentialDeal(PrefrentialDeal prefrentialDeal){
		if(ValidateUtil.objectIsEmpty(prefrentialDeal)){
			throw new ContractException(ContractExceptionType.NULLDEALERROR);
		}
		/**
		 * 构造查询条件，根据折扣类型查询折扣方案
		 */
		PrefrentialDeal searcjDeal = new PrefrentialDeal();
		searcjDeal.setPreferType(prefrentialDeal.getPreferType());
		
		List<PrefrentialDeal> dealList = prefrentialDealManager.searchAllPrefrentialDeal(searcjDeal,0, Integer.MAX_VALUE);
		List<PrefrentialDealItem> dealItemsList = prefrentialDeal.getDealItems();
		if(ValidateUtil.objectIsEmpty(dealItemsList)){
			throw new ContractException(ContractExceptionType.NULLDEALERROR);
		}
		//得到优惠方案List的长度
		int countItems = dealItemsList.size();
		Set<String> set = new HashSet<String>();
		//遍历一个优惠方案里面各个等级基础资料
		PrefrentialDealItem item = null;
		for(int i = 0;i < countItems;i++){
			//当前等级的方案基础资料
			item = dealItemsList.get(i);
			
			set.add(item.getDegree());
			//每一行的最下金额必须小于最大金额
			if(item.getMinAmount()
					>= item.getMaxAmount() ){
				throw new ContractException(ContractExceptionType.MINAMOUNTOUTOFRANGE);
			//生效时间不得小于当前时间和失效时间
			}else if(prefrentialDeal.getBeginTime().before( ContractUtil.getNowTime(null)) 
				|| prefrentialDeal.getBeginTime().after( prefrentialDeal.getEndTime())){
				throw new ContractException(ContractExceptionType.BEGINTIMEOUTOFRANGE);
			}
		}
		//等级不允许相同
		 if(set.size() != countItems){
			 throw new ContractException(ContractExceptionType.DGREEEXIST);
		}
		 //不能新增两套生效时间一模一样的方案
		 if(!ValidateUtil.objectIsEmpty(dealList)){
			 for(PrefrentialDeal deal:dealList){
				 //通过ContractUtil.getNowTime()方法将数据库查出的开始时间设为零时零分零秒
				 //再比较两个方案的生效时间是否一样
				 if (ContractUtil.getNowTime(deal.getBeginTime()).equals(prefrentialDeal.getBeginTime())) {
					 throw new ContractException(ContractExceptionType.PLANEXIST);
				 }
			 }
		 }
		return true;
	}
	 /**
	  * 
	  * @Title: validateRateNotChange
	  *  <p>
	  * @Description: 校验是否只修改了折扣率<br />
	  * </p>
	  * @author 唐亮
	  * @version 0.1 2013-3-15
	  * @return boolean
	  * @throws
	  */
	 public boolean validateRateChange(List<PrefrentialDealItem> beforeList,List<PrefrentialDealItem> afterList){
		 if (ValidateUtil.objectIsEmpty(beforeList) || ValidateUtil.objectIsEmpty(afterList)) {
			throw new ContractException(ContractExceptionType.NULLDEALERROR);
		}
		 for(int i=0;i<beforeList.size();i++){
			 for(int j=0;j<afterList.size();j++){
				  if(beforeList.get(i).getId().equals(afterList.get(j).getId())){
					 if(!beforeList.get(i).getDegree().equals(afterList.get(j).getDegree())){
						throw new ContractException(ContractExceptionType.CANNOTUPDATEDEGREE);
					 }else if (beforeList.get(i).getMinAmount() != afterList.get(j).getMinAmount()) {
						throw new ContractException(ContractExceptionType.CANNOTUPDATEMIN);
					}else if (beforeList.get(i).getMaxAmount() != afterList.get(j).getMaxAmount()) {
						throw new ContractException(ContractExceptionType.CANNOTUPDATEMAX);
					}else if (!beforeList.get(i).getItemDesc().equals(afterList.get(j).getItemDesc())){
						throw new ContractException(ContractExceptionType.CANNOTUPDATEDES);
					}
				 }
			 }
		 }
		 return true;
	 }
	 /**
	  * 
	  * @Title: validateRateIsChanged
	  *  <p>
	  * @Description: 校验基础资料的折扣率是否有被修改过<br />
	  * </p>
	  * @author 唐亮
	  * @version 0.1 2013-3-18
	  * @return boolean
	  * @throws
	  */
	 public boolean validateRateIsChanged(List<PrefrentialDealItem> beforeList,List<PrefrentialDealItem> afterList){
		 if (ValidateUtil.objectIsEmpty(beforeList) || ValidateUtil.objectIsEmpty(afterList)) {
				throw new ContractException(ContractExceptionType.NULLDEALERROR);
			}
		 for(int i=0;i<beforeList.size();i++){
			 for(int j=0;j<afterList.size();j++){
				 if(beforeList.get(i).getId().equals(afterList.get(j).getId())
						 && beforeList.get(i).getRate() != afterList.get(j).getRate()){
					 return false;
				 }
			 	}
			 }
		 return true;
	 }
	 /**
	  * 
	  * @Title: validateAmountMix
	  *  <p>
	  * @Description: 校验基础资料最大最小金额是否交叉<br />
	  * </p>
	  * @author 唐亮
	  * @version 0.1 2013-3-15
	  * @return boolean
	  * @throws
	  */
	public boolean validateAmountMix(List<PrefrentialDealItem> itemList){
		if(ValidateUtil.objectIsEmpty(itemList)){
			throw new ContractException(ContractExceptionType.NULLDEALERROR);
		}
		for(int i = 0;i < itemList.size();i++ ){
			for(int j = i+1;j < itemList.size();j++){
				if(itemList.get(i).getDegree().equals(itemList.get(j).getDegree())){
					 throw new ContractException(ContractExceptionType.DGREEEXIST);
				}
				if((itemList.get(i).getMinAmount() >= itemList.get(j).getMinAmount()
						&&itemList.get(i).getMaxAmount() >= itemList.get(j).getMaxAmount()
						&&itemList.get(i).getMinAmount() <= itemList.get(j).getMaxAmount())
						||(itemList.get(i).getMinAmount() <= itemList.get(j).getMinAmount()
						&&itemList.get(i).getMaxAmount() <= itemList.get(j).getMaxAmount()
						&&itemList.get(i).getMaxAmount() >= itemList.get(j).getMinAmount())
						||(itemList.get(i).getMinAmount() >= itemList.get(j).getMinAmount()
						&&itemList.get(i).getMaxAmount() <= itemList.get(j).getMaxAmount())
						||(itemList.get(i).getMinAmount() <= itemList.get(j).getMinAmount()
						&&itemList.get(i).getMaxAmount() >= itemList.get(j).getMaxAmount())){
					throw new ContractException(ContractExceptionType.CANNOTMIXAMOUNT);
				}
			}
		}
		return true;	
	}
}
