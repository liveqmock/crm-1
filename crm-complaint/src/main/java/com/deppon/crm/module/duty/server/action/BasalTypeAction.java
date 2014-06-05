package com.deppon.crm.module.duty.server.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.duty.server.manager.IBasalTypeManager;
import com.deppon.crm.module.duty.shared.domain.BasicBusScopeVO;
import com.deppon.crm.module.duty.shared.domain.BasicBusType;
import com.deppon.crm.module.duty.shared.domain.BasicInfo;
import com.deppon.crm.module.duty.shared.domain.BasicLevel;
import com.deppon.crm.module.duty.shared.domain.BasicSearchCondition;
import com.deppon.crm.module.duty.shared.domain.ProcResult;
import com.deppon.foss.framework.server.context.UserContext;
import com.deppon.foss.framework.server.web.action.AbstractAction;

/**
 * 
 * <p>
 * Description:工单基础类型 action <br />
 * </p>
 * @title BasalTypeAction.java
 * @package com.deppon.crm.module.duty.server.action 
 * @author 侯斌飞
 * @version 0.1 2013-2-26
 */
public class BasalTypeAction extends AbstractAction {
	/* ------------------分页信息 begin ------------------- */
	private int start;
	//前台传入的条件查询潜客信息的limit参数（用于分页）
	private int limit;
	//传入前台的通过条件查询潜客结果总数
	private Long totalCount;
	//责任-基础类型 Manager
	private IBasalTypeManager basalTypeManager;
	//boolean 结果
	private Boolean boolResult;
	//查询条件
	private BasicSearchCondition basicSearchCondition;
	
	//业务信息集合
	private List<BasicInfo> basicInfoList;
	//基础资料实体
	private BasicBusScopeVO basicBusScopeVO;
	
	//业务类型基础数据
	private List<BasicBusType> busTypeList;
	//业务类型基础数据
	private List<ProcResult> procResultList;
	//基础资料表集合
	private List<BasicLevel> basicLevelList;
	
	
	/**
	 * @return basicLevelList : return the property basicLevelList.
	 */
	public List<BasicLevel> getBasicLevelList() {
		return basicLevelList;
	}

	/**
	 * @param basicLevelList : set the property basicLevelList.
	 */
	public void setBasicLevelList(List<BasicLevel> basicLevelList) {
		this.basicLevelList = basicLevelList;
	}

	/**
	 * @return procResultList : return the property procResultList.
	 */
	public List<ProcResult> getProcResultList() {
		return procResultList;
	}

	/**
	 * @param procResultList : set the property procResultList.
	 */
	public void setProcResultList(List<ProcResult> procResultList) {
		this.procResultList = procResultList;
	}

	/**
	 * @return busTypeList : return the property busTypeList.
	 */
	public List<BasicBusType> getBusTypeList() {
		return busTypeList;
	}

	/**
	 * @param busTypeList : set the property busTypeList.
	 */
	public void setBusTypeList(List<BasicBusType> busTypeList) {
		this.busTypeList = busTypeList;
	}

	/**
	 * @return basicBusScopeVO : return the property basicBusScopeVO.
	 */
	public BasicBusScopeVO getBasicBusScopeVO() {
		return basicBusScopeVO;
	}

	/**
	 * @param basicBusScopeVO : set the property basicBusScopeVO.
	 */
	public void setBasicBusScopeVO(BasicBusScopeVO basicBusScopeVO) {
		this.basicBusScopeVO = basicBusScopeVO;
	}

	/**
	 * @return basicSearchCondition : return the property basicSearchCondition.
	 */
	public BasicSearchCondition getBasicSearchCondition() {
		return basicSearchCondition;
	}

	/**
	 * @param basicSearchCondition : set the property basicSearchCondition.
	 */
	public void setBasicSearchCondition(BasicSearchCondition basicSearchCondition) {
		this.basicSearchCondition = basicSearchCondition;
	}

	/**
	 * @return totalCount : return the property totalCount.
	 */
	public Long getTotalCount() {
		return totalCount;
	}
	
	/**
	 * @return boolResult : return the property boolResult.
	 */
	public Boolean getBoolResult() {
		return boolResult;
	}
	

	/**
	 * @return basicInfoList : return the property basicInfoList.
	 */
	public List<BasicInfo> getBasicInfoList() {
		return basicInfoList;
	}

	/**
	 * @param basicInfoList : set the property basicInfoList.
	 */
	public void setBasicInfoList(List<BasicInfo> basicInfoList) {
		this.basicInfoList = basicInfoList;
	}

	/**
	 * @param start : set the property start.
	 */
	public void setStart(int start) {
		this.start = start;
	}

	/**
	 * @param limit : set the property limit.
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}

	/**
	 * @param basalTypeManager : set the property basalTypeManager.
	 */
	public void setBasalTypeManager(IBasalTypeManager basalTypeManager) {
		this.basalTypeManager = basalTypeManager;
	}

	/**
	 * 
	 * <p>
	 * Description:保存基础信息-业务项<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2013-2-27
	 * @return
	 * String
	 */
	public String saveBusItemList(){
		User currentUser = (User) (UserContext.getCurrentUser());
		boolResult = basalTypeManager.operateBusItem(basicInfoList,currentUser);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:删除基础数据集合  by Ids<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2013-2-27
	 * @return
	 * String
	 */
	public String deleteBusItemByIds(){
		boolResult = basalTypeManager.deleteBusItemByIds(basicInfoList);
		return SUCCESS;
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:查询基础信息集合<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2013-2-27
	 * @return
	 * String
	 */
	public String searchBasicInfoList(){
		basicInfoList = basalTypeManager.searchBusItemByType(basicSearchCondition.getReportType());
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:（只有有业务类型的业务向才会被查出）查询基础信息集合<br />
	 * </p>
	 * @author 张斌
	 * @version 0.1 2013-4-19
	 * @return
	 * String
	 */
	public String searchBasicInfoListOnly(){
		basicInfoList = basalTypeManager.searchBusItemByTypeOnly(basicSearchCondition.getReportType());
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:业务信息集合分页<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2013-3-5
	 * @return
	 * String
	 */
	@SuppressWarnings("unchecked")
	public String searchBasicInfoPager(){
		Map<String,Object> map = basalTypeManager.searchBasicInfo(basicSearchCondition, start, limit);
		basicInfoList = (List<BasicInfo>)map.get("basicInfos");
		totalCount = Long.valueOf(map.get("totalCount").toString());
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:业务信息集合Counet<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2013-3-6
	 * @return
	 * String
	 */
	public String searchBasicInfoCount(){
		totalCount = Long.valueOf(basalTypeManager.countBasicInfoByCondition(basicSearchCondition));
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:集合分页页面-删除业务类型、业务范围<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2013-3-5
	 * @return
	 * String
	 */
	public String deleteBusTypeByIds(){
		boolResult = basalTypeManager.deleteBusTypeByIds(basicInfoList);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:基础资料设置页面-保存<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2013-3-6
	 * @return
	 * String
	 */
	public String saveBasicBusScopeVO (){
		User currentUser = (User) (UserContext.getCurrentUser());
		boolResult = basalTypeManager.operateBasicBusInfo(basicBusScopeVO, currentUser);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:删除自出资料页面-业务类型集合<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2013-3-6
	 * @return
	 * String
	 */
	public String deleteBusTypeByIdsInBusScopeVO(){
		boolResult = basalTypeManager.deleteBusTypeByIdsInBusScopeVO(busTypeList);
		return SUCCESS;
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:基础资料设置-业务类型集合<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2013-3-6
	 * @return
	 * String
	 */
	public String searchBasicBusScopeVOSimple(){
		basicBusScopeVO = basalTypeManager.searchBasicBusScopeVOSimple(basicBusScopeVO);
		return SUCCESS;
	}
	/**
	 * 
	 * <p>
	 * Description:根据业务项查询业务范围集合<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2013-3-11
	 * @return
	 * String
	 */
	public String searchBusScopeByParentId(){
		String id = basicSearchCondition.getId();
		if(id==null || "".equals(id.trim())){
			return SUCCESS;
		}
		basicLevelList = basalTypeManager.selectBusScope(new BigDecimal(id));
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:(只有有业务类型的业务范围才能被查出)根据业务项查询业务范围集合<br />
	 * </p>
	 * @author 张斌
	 * @version 0.1 2013-4-19
	 * @return
	 * String
	 */
	public String searchBusScopeByParentIdOnly(){
		String id = basicSearchCondition.getId();
		if(id==null || "".equals(id.trim())){
			return SUCCESS;
		}
		basicLevelList = basalTypeManager.selectBusScopeOnly(new BigDecimal(id));
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>
	 * Description:查询业务类型集合<br />
	 * </p>
	 * @author 侯斌飞
	 * @version 0.1 2013-3-11
	 * @return
	 * String
	 */
	public String searchProcResultList(){
		String id = basicSearchCondition.getId();
		if(id==null || "".equals(id.trim())){
			return SUCCESS;
		}
		procResultList = basalTypeManager.getProcResultByLevelId(new BigDecimal(id));
		return SUCCESS;
	}
}
