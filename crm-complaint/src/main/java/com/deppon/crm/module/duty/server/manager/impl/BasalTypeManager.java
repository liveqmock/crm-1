package com.deppon.crm.module.duty.server.manager.impl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.duty.shared.domain.ProcResult;
import com.deppon.crm.module.duty.server.manager.IBasalTypeManager;
import com.deppon.crm.module.duty.server.service.IBasicLevelService;
import com.deppon.crm.module.duty.server.service.IProcResultService;
import com.deppon.crm.module.duty.server.util.BasicInfoConstants;
import com.deppon.crm.module.duty.server.util.BasicInfoUtils;
import com.deppon.crm.module.duty.server.util.BasicInfoValidator;
import com.deppon.crm.module.duty.server.util.DutyConstants;
import com.deppon.crm.module.duty.shared.domain.BasicBusScopeVO;
import com.deppon.crm.module.duty.shared.domain.BasicBusType;
import com.deppon.crm.module.duty.shared.domain.BasicInfo;
import com.deppon.crm.module.duty.shared.domain.BasicLevel;
import com.deppon.crm.module.duty.shared.domain.BasicSearchCondition;

/**
 * 
 * <p>
 * Description:工单责任-基础类型 manager 实现<br />
 * </p>
 * @title BasalTypeManager.java
 * @package com.deppon.crm.module.duty.server.manager.impl 
 * @author 侯斌飞
 * @version 0.1 2013-2-26
 */
@Transactional
public class BasalTypeManager implements IBasalTypeManager {
	private IBasicLevelService basicLevelServiceDuty;
	private IProcResultService procResultServiceDuty;
	/**
	 * @param basicLevelServiceDuty the basicLevelServiceDuty to set
	 */
	public void setBasicLevelServiceDuty(IBasicLevelService basicLevelServiceDuty) {
		this.basicLevelServiceDuty = basicLevelServiceDuty;
	}
	/**
	 * @param procResultServiceDuty the procResultServiceDuty to set
	 */
	public void setProcResultServiceDuty(IProcResultService procResultServiceDuty) {
		this.procResultServiceDuty = procResultServiceDuty;
	}
	/**
	 * <p>
	 * Description: 新基础资料--查询基础资料数据<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-22
	 * @param bsc
	 * @param rb
	 */
	public Map<String,Object> searchBasicInfo(BasicSearchCondition bsc,int start,int limit){
		//查询基础资料总计
		int totalCount = basicLevelServiceDuty.searchCountBasicInfo(bsc);
		
		List<BasicInfo> basicInfos = null;
		if(totalCount > 0 ){
			//查询基础资料
			basicInfos = basicLevelServiceDuty.searchBasicInfo(bsc, start, limit);
		}
		Map<String,Object> retMap = new HashMap<String,Object>();
		retMap.put("totalCount", totalCount);
		retMap.put("basicInfos", basicInfos);
		return retMap;
	}
	
	/**
	 * <p>
	 * Description: 新基础资料--查询基础资料数据个数<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-06
	 * @param bsc
	 * @param rb
	 */
	public Integer countBasicInfoByCondition(BasicSearchCondition bsc){
		//查询基础资料总计
		int totalCount = basicLevelServiceDuty.searchCountBasicInfo(bsc);
		return totalCount;
	}
	/**
	 * <p>
	 * Description: 新基础资料--查询出业务范围及对应的业务类型用于修改基础资料页面<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param BasicBusScopeVO
	 */
	public List<BasicBusScopeVO> searchBasicBusScopeVO(BasicBusScopeVO bbs){
		//检查查询条件字段是否合法
		BasicInfoValidator.checkBasicBusScopeVOBySearch(bbs);
		//调用相应的查询
		return basicLevelServiceDuty.searchBasicBusScopeVO(bbs);
	}
	/**
	 * <p>
	 * Description: 新基础资料--新增业务项<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param basicItems
	 * @param user
	 */
	public boolean operateBusItem(List<BasicInfo> basicItems,User user){
		
		//检查业务项列表是否合法
		BasicInfoValidator.checkSaveBasicItem(basicItems);
		//查询所有的业务项
		List<BasicInfo> orgBusItems = basicLevelServiceDuty
				.searchBusItemByType(null);
		//调用方法得到需要修改或需要新增的业务项集合
		List<BasicLevel> basicLevels = BasicInfoUtils
				.getDelOrUpdItemListFromBusItemList(basicItems,orgBusItems,user);
		//循环业务项集合
		for( BasicLevel basLev : basicLevels){
			if( basLev.getId() == null || "".equals(basLev.getId() )){
				basicLevelServiceDuty.saveBasicLevel(basLev);
			}else{
				basicLevelServiceDuty.updateBasicLevelById(basLev);
			}
		}
		return true;
	}
	/**
	 * <p>
	 * Description: 新基础资料--业务项修改,业务范围新增与修改,业务类型新增修改<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param basicBusScope
	 * @param user
	 */
	public boolean operateBasicBusInfo(BasicBusScopeVO basicBusScope,User user){
		//检查实体是否合法
		BasicInfoValidator.checkBusScopeVOInsert(basicBusScope);
		//如果业务项名称修改过
		if( (null != basicBusScope.getBusItemName() && 
				!"".equals(basicBusScope.getBusItemName()))
			||(null != basicBusScope.getReportType() && 
					!"".equals(basicBusScope.getReportType()))){
			//将basicBusScope转换为basicLevel
			BasicLevel busItem = BasicInfoUtils.busScopeVOItemConvertToBasicLevel(basicBusScope, user);
			//更新数据库
			basicLevelServiceDuty.updateBasicLevelById(busItem);
		}
		//如果业务类型修改过
		if( null != basicBusScope.getReportType() &&
				! "".equals(basicBusScope.getReportType())){
			BasicLevel busItem = BasicInfoUtils.busScopeVOConvertTOBasicLevelUpdByParentId(basicBusScope, user);
			basicLevelServiceDuty.updateBasicLevelByParentId(busItem);
		}
		//如果业务范围ID为空且业务范围名称不为空就新增业务范围
		if( null != basicBusScope.getBusScopeName() && 
				!"".equals(basicBusScope.getBusScopeName()) && 
				( null == basicBusScope.getBusScopeId() || 
				  "".equals(basicBusScope.getBusScopeId()))
				){
			BasicLevel basLev = BasicInfoUtils.busScopeConvertToBasicLevel(basicBusScope,user);
			//将业务范围插入到数据库中
			basicLevelServiceDuty.saveBasicLevel(basLev);
			//更新basicBusScope中的busScopeId字段
			basicBusScope.setBusScopeId(basLev.getId());
	    //当业务范围ID不为空且业务范围名称不为空就修改业务范围
		}else if( null != basicBusScope.getBusScopeName() && 
					!"".equals(basicBusScope.getBusScopeName()) && 
					null != basicBusScope.getBusScopeId() &&
					!"".equals(basicBusScope.getBusScopeId())){
			BasicLevel basLev = BasicInfoUtils.busScopeConvertToBasicLevel(basicBusScope,user);
			//将业务范围更新到数据库中
			basicLevelServiceDuty.updateBasicLevelById(basLev);	
		}
		//业务类型列表不为空
		if( basicBusScope.getBusTypes()!= null 
				&& basicBusScope.getBusTypes().size()!=0){
			for( BasicBusType busType : basicBusScope.getBusTypes()){
				//将BusType中的数据补齐
				busType = BasicInfoUtils.convertBusType(basicBusScope, busType, user);
				//如果业务类型ID为空则插入到数据库中
				if( null == busType.getId() || "".equals(busType.getId())){
//					BasicInfoValidator.checkBusTypeSave(busType);
					procResultServiceDuty.saveBusType(busType);
				}else{
					procResultServiceDuty.updateBusType(busType);
				}
			}
		}
		return true;
	}
	
	/**
	 * <p>
	 * Description: 新基础资料--删除业务类型<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param id
	 */
	public boolean deleteBusTypeByIds(List<BasicInfo> basicInfos){
		//获得要删除列表的业务类型ID列表
		List<String> busTypeIds = BasicInfoUtils
				.getBusTypeIdsFromBasicInfo(basicInfos);
		//检查业务类型ID列表是否为空
		BasicInfoValidator.checkIdList(busTypeIds);
		//调用删除业务类型方法
		procResultServiceDuty.deleteBusTypeById(busTypeIds);
		//获取要删除列表的业务范围ID列表
		List<String> busScopeIds = BasicInfoUtils
				.getBusScopeIdsFromBasicInfo(basicInfos);
		//检查业务范围ID列表是否为空
		BasicInfoValidator.checkIdList(busScopeIds);
		//循环删除没有业务类型的业务范围
		for( String busScopeId : busScopeIds ){
			basicLevelServiceDuty.deleteBusScopeById(busScopeId);
		}
		return true;
	}
	/**
	 * <p>
	 * Description: 新基础资料--删除业务类型在操作基础资料页面中使用<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param id
	 */
	public boolean deleteBusTypeByIdsInBusScopeVO(List<BasicBusType> busTypes){
		//获得要删除列表的业务类型ID列表
		List<String> busTypeIds = BasicInfoUtils
				.getBusTypeIdsFromBasicBusScopeVO(busTypes);
		//检查业务类型ID列表是否为空
		BasicInfoValidator.checkIdList(busTypeIds);
		//调用删除业务类型方法
		procResultServiceDuty.deleteBusTypeById(busTypeIds);
		return true;
	}
	/**
	 * <p>
	 * Description: 新基础资料--删除业务项<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param id
	 */
	public boolean deleteBusItemByIds(List<BasicInfo> basicInfos){
		//获得要删除业务项ID列表
		List<String> busItemIds = BasicInfoUtils
				.getBusItemIdsFromBasiInfo(basicInfos);
		//检查该列表是否为空
		BasicInfoValidator.checkIdList(busItemIds);
		//调用相应方法删除业务项
		basicLevelServiceDuty.deleteBusItemByIds(busItemIds);
		//调用方法删除这些业务项包含的所有业务类型
		procResultServiceDuty.deleteBusTypeByBusItem(busItemIds);
		return true;
	}
	/**
	 * <p>
	 * Description: 新基础资料--根据上报类型查询业务项<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param BasicBusScopeVO
	 * @param BasicBusScopeVO
	 */
	public List<BasicInfo> searchBusItemByType(String reportType){
		return basicLevelServiceDuty.searchBusItemByType(reportType);
	}
	/**
	 * <p>
	 * Description: 新基础资料--删除添加业务项页面用于显示已存在的业务项<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param BasicBusScopeVO
	 * @param BasicBusScopeVO
	 */
	public Map<String,List<BasicInfo>> showBusItemByType(){
		Map<String,List<BasicInfo>> map = new HashMap<String,List<BasicInfo>>();
		//查询投诉的业务项
		List<BasicInfo> complaints = basicLevelServiceDuty.searchBusItemByType(
				BasicInfoConstants.BASICCOMPTYPECOMPAINT);
		//查询异常的业务项
		List<BasicInfo> unusuals = basicLevelServiceDuty.searchBusItemByType(
				BasicInfoConstants.BASICCOMPTYPEUNUSUAL);
		map.put("complaints", complaints);
		map.put("unusuals", unusuals);
		return map;
	}
	/**
	 * <p>
	 * Description: 新基础资料--根据业务项ID查询业务范围<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-23
	 * @param busItemId
	 */
	public List<BasicInfo> searchBusScopeByBusItemId(String busItemId){
		return basicLevelServiceDuty.searchBusScopeByBusItemId(busItemId);
	}
	/**
	 * <p>
	 * Description: 新基础资料根据业务范围ID查询业务类型<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-25
	 * @param busScopeId
	 * @return List<BasicBusType> 
	 */
	public List<BasicInfo> searchBusTypeByBusScope(String busScopeId){
		return procResultServiceDuty.searchBusTypeByBusScope(busScopeId);
	}
	/**
	 * <p>
	 * Description: 新基础资料--根据ID修改业务项<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-25
	 * @param basicInfo
	 * @return List<BasicBusType> 
	 */
	public boolean updateBusItemById(BasicInfo basicInfo,User user){
		//检查业务项数据是否合法
		BasicInfoValidator.checkBusItemForUpdate(basicInfo);
		//将基础资料字段转换为BasicLevel用于更新
		BasicLevel basLev = BasicInfoUtils
				.basicInfoItemConvertToBasicLevelForUpdate(basicInfo,user);
		//更新业务项
		basicLevelServiceDuty.updateBasicLevelById(basLev);
		return true;
		
	}
	/**
	 * <p>
	 * Description: 新基础资料--查询出业务范围及对应的业务类型用于修改基础资料页面单个<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-06
	 * @param bsc
	 * @param rb
	 */
	@Override
	public BasicBusScopeVO searchBasicBusScopeVOSimple(BasicBusScopeVO bbs) {
		List<BasicBusScopeVO> basicBusScopeVOList = basicLevelServiceDuty.searchBasicBusScopeVO(bbs);
		if(CollectionUtils.isEmpty(basicBusScopeVOList)){
			return null;
		}else{
			return basicBusScopeVOList.get(DutyConstants.ZERO);
		}
	}
	/**
	 * 
	 * <p>
	 * Description:根据业务范围ID查询基础资料<br />
	 * </p>
	 * @author ouy
	 * @version 0.1 2012-4-25
	 * @param basciLevelId
	 * @return
	 * List<ProcResult>
	 */
	@Override
	public List<ProcResult> getProcResultByLevelId(BigDecimal basciLevelId) {
		if(basciLevelId==null){
			List<ProcResult> procResults = new ArrayList<ProcResult>();
			return procResults;
		}else{
			return procResultServiceDuty.getProcResultByLevelId(basciLevelId);
		}
		
	}
	/**
	 * <p>
	 * Description: 查询业务范围<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-3-11
	 * @param busItemId
	 */
	@Override
	public List<BasicLevel> selectBusScope(BigDecimal parentid) {
		if(parentid==null){
			List<BasicLevel> basicInfos = new ArrayList<BasicLevel>();
			return basicInfos;
		}else{
			return basicLevelServiceDuty.selectBusScope(parentid);
		}
		
	}
	/**
	 * <p>
	 * Description: 只查询有业务类型的业务范围<br />
	 * </p>
	 * @author zhangbin
	 * @version 0.1 2013-4-19
	 * @param busItemId
	 */
	@Override
	public List<BasicLevel> selectBusScopeOnly(BigDecimal parentid) {
		if(parentid==null){
			List<BasicLevel> basicInfos = new ArrayList<BasicLevel>();
			return basicInfos;
		}else{
			return basicLevelServiceDuty.selectBusScopeOnly(parentid);
		}
		
	}
	/**
	 * <p>
	 * Description: (只有有业务类型才能查出的业务项)新基础资料--根据上报类型查询业务项<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-4-19
	 * @param reportType
	 */
	@Override
	public List<BasicInfo> searchBusItemByTypeOnly(String reportType) {
		return basicLevelServiceDuty.searchBusItemByTypeOnly(reportType);
	}

}