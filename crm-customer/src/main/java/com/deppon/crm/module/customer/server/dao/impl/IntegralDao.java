package com.deppon.crm.module.customer.server.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.deppon.crm.module.customer.server.dao.IIntegralDao;
import com.deppon.crm.module.customer.server.utils.ContextUtil;
import com.deppon.crm.module.customer.server.utils.DataUtil;
import com.deppon.crm.module.customer.server.utils.SqlUtil;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.deppon.crm.module.customer.shared.domain.integral.AdjustIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.GiftConvertHoldRecode;
import com.deppon.crm.module.customer.shared.domain.integral.HandRewardIntegral;
import com.deppon.crm.module.customer.shared.domain.integral.IntegralConvertGift;
import com.deppon.crm.module.customer.shared.domain.integral.IntegralConvertGiftCondition;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

public class IntegralDao extends iBatis3DaoImpl implements IIntegralDao{
	
	private final static String NAME_SPACE = "com.deppon.crm.module.customer.shared.domain.integral.Integral.";

	@Override
	public void insertAdjustIntegral(AdjustIntegral integral) {
		Map<String,Object> map = DataUtil.changeObjectToMap(integral); 
		map.put("type",Constant.AdjustIntegral);
		integral.setType(Constant.AdjustIntegral);
		integral.setId(insertIntegral(map));
	}

	@Override
	public void insertHandRewardIntegral(HandRewardIntegral integral) {
		Map<String,Object> map = DataUtil.changeObjectToMap(integral); 
		map.put("type",Constant.HandRewardIntegral);
		integral.setType(Constant.HandRewardIntegral);
		integral.setId(insertIntegral(map));
	}

	@Override
	public void insertIntegralConvertGift(IntegralConvertGift integral) {
		integral.setDeptId(ContextUtil.getCurrentUserDeptId());
		Map<String,Object> map = DataUtil.changeObjectToMap(integral); 
		map.put("type",Constant.IntegralConvertGift);
		integral.setType(Constant.IntegralConvertGift);
		integral.setId(insertIntegral(map));
	}
	
	private String insertIntegral(Map<String,Object> map){
		this.getSqlSession().insert(NAME_SPACE+"insertIntegral",map);
		return (String) map.get("id");
	}
	
	private void updateIntegral(Map<String,Object> map){
		this.getSqlSession().insert(NAME_SPACE+"updateIntegral",map);
	}
	
	@Override
	public void updateIntegralConvertGift(IntegralConvertGift integral) {
		Map<String,Object> map = DataUtil.changeObjectToMap(integral); 
		updateIntegral(map);
	}

	@Override
	public void updateHandRewardIntegral(HandRewardIntegral integral) {
		Map<String,Object> map = DataUtil.changeObjectToMap(integral); 
		updateIntegral(map);
	}

	@Override
	public void updateAdjustIntegral(AdjustIntegral integral) {
		Map<String,Object> map = DataUtil.changeObjectToMap(integral); 
		updateIntegral(map);
	}
	//TODO 分页都未进行
	@Override
	public long countSearchIntegralConvertGifts(IntegralConvertGift integral) {
		return (Long) this.getSqlSession().selectOne(NAME_SPACE+"countSearchIntegralConvertGifts",integral);
	}
	//TODO 分页都未进行	
	@Override
	public long countSearchHandRewardIntegrals(HandRewardIntegral integral) {
		return (Long) this.getSqlSession().selectOne(NAME_SPACE+"countSearchHandRewardIntegrals",integral);
	}
	//TODO 分页都未进行	
	@Override
	public long countSearchAdjustIntegrals(AdjustIntegral integral) {
		return (Long) this.getSqlSession().selectOne(NAME_SPACE+"countSearchAdjustIntegrals",integral);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<IntegralConvertGift> searchIntegralConvertGifts(
			IntegralConvertGift integral, int start, int limit) {
		RowBounds rowBounds = new RowBounds(start,limit);
		return this.getSqlSession().selectList(NAME_SPACE+"searchIntegralConvertGifts",integral,rowBounds);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HandRewardIntegral> searchHandRewardIntegrals(
			HandRewardIntegral integral, int start, int limit) {
		RowBounds rowBounds = new RowBounds(start,limit);
		return this.getSqlSession().selectList(NAME_SPACE+"searchHandRewardIntegrals",integral,rowBounds);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AdjustIntegral> searchAdjustIntegrals(AdjustIntegral integral,
			int start, int limit) {
		RowBounds rowBounds = new RowBounds(start,limit);
		return this.getSqlSession().selectList(NAME_SPACE+"searchAdjustIntegrals",integral,rowBounds);
	}

	@Override
	public IntegralConvertGift getIntegralConvertGiftById(String id) {
		return (IntegralConvertGift) this.getSqlSession().selectOne(NAME_SPACE+"getIntegralConvertGiftById", id);
	}

	@Override
	public HandRewardIntegral getHandRewardIntegralById(String id) {
		return (HandRewardIntegral) this.getSqlSession().selectOne(NAME_SPACE+"getHandRewardIntegralById", id);
	}

	@Override
	public AdjustIntegral getAdjustIntegralById(String id) {
		return (AdjustIntegral) this.getSqlSession().selectOne(NAME_SPACE+"getAdjustIntegralById", id);

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<HandRewardIntegral> searchHandRewardIntegralForContactId(
			List<String> contactIdList, int start, int limit) {
		RowBounds row = new RowBounds(start,limit);
		String contactIds = SqlUtil.getInSql(contactIdList);
		Map map = new HashMap();
		map.put("contactIdList", contactIds);
		return this.getSqlSession().selectList(NAME_SPACE+"searchHandRewardIntegralForContactId",map,row);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public long countSearchHandRewardIntegralForContactId(
			List<String> contactIdList) {
		String contactIds = SqlUtil.getInSql(contactIdList);
		Map map = new HashMap();
		map.put("contactIdList", contactIds);
		return  (Long) this.getSqlSession().selectOne(NAME_SPACE+"countSearchHandRewardIntegralForContactId",map);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<IntegralConvertGift> searchIntegralConvertGiftForContactId(
			List<String> contactIdList, int start, int limit) {
		RowBounds row = new RowBounds(start,limit);
		String contactIds = SqlUtil.getInSql(contactIdList);
		Map map = new HashMap();
		map.put("contactIdList", contactIds);
		return this.getSqlSession().selectList(NAME_SPACE+"searchIntegralConvertGiftForContactId",map,row);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public long countSearchIntegralConvertGiftForContactId(
			List<String> contactIdList) {
		String contactIds = SqlUtil.getInSql(contactIdList);
		Map map = new HashMap();
		map.put("contactIdList", contactIds);
		return  (Long) this.getSqlSession().selectOne(NAME_SPACE+"countSearchIntegralConvertGiftForContactId",map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AdjustIntegral> searchAdjustIntegralForContactId(
			List<String> contactIdList, int start, int limit) {
		RowBounds row = new RowBounds(start,limit);
		String contactIds = SqlUtil.getInSql(contactIdList);
		@SuppressWarnings("rawtypes")
		Map map = new HashMap();
		map.put("contactIdList", contactIds);
		return this.getSqlSession().selectList(NAME_SPACE+"searchAdjustIntegralForContactId",map,row);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public long countSearchAdjustIntegralForContactId(List<String> contactIdList) {
		String contactIds = SqlUtil.getInSql(contactIdList);
		Map map = new HashMap();
		map.put("contactIdList", contactIds);
		return  (Long) this.getSqlSession().selectOne(NAME_SPACE+"countSearchAdjustIntegralForContactId",map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IntegralConvertGift> searchIntegralConvertGift(
			IntegralConvertGiftCondition condition,int start,int limit) {
		RowBounds row = new RowBounds(start,limit);
		return this.getSqlSession().selectList(NAME_SPACE+"searchIntegralConvertGiftWithJoin",condition,row);
	}

	@Override
	public long countSearchIntegralConvertGift(
			IntegralConvertGiftCondition condition) {
		return  (Long) this.getSqlSession().selectOne(NAME_SPACE+"countSearchIntegralConvertGiftWithJoin",condition);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.dao.IIntegralDao#getTemporaryGiftBill(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<GiftConvertHoldRecode> getTemporaryGiftBill(String userId) {
		return this.getSqlSession().selectList(NAME_SPACE+"searchTemporaryGiftBill",userId);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.dao.IIntegralDao#batchDeleteTemporaryGiftBill(java.util.List)
	 */
	@Override
	public void batchDeleteTemporaryGiftBill(String deptId) {
		this.getSqlSession().selectList(NAME_SPACE+"batchDeleteTemporaryGiftBillByDeptId",deptId);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.dao.IIntegralDao#batchDeleteTemporaryGiftBill(java.util.List)
	 */
	@Override
	public void batchDeleteTemporaryGiftBill(List<String> ids) {
		this.getSqlSession().selectList(NAME_SPACE+"batchDeleteTemporaryGiftBill",ids);
	}

	/* (non-Javadoc)
	 * @see com.deppon.crm.module.customer.server.dao.IIntegralDao#addTemporySaveGift(com.deppon.crm.module.customer.shared.domain.integral.GiftConvertHoldRecode)
	 */
	@Override
	public void addTemporySaveGift(GiftConvertHoldRecode giftConvertHoldRecode) {
		this.getSqlSession().selectList(NAME_SPACE+"addTemporaryGift",giftConvertHoldRecode);
	}

}
