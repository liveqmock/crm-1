package com.deppon.crm.module.complaint.server.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.complaint.shared.domain.BasicBusScopeVO;
import com.deppon.crm.module.complaint.shared.domain.BasicBusType;
import com.deppon.crm.module.complaint.shared.domain.BasicInfo;
import com.deppon.crm.module.complaint.shared.domain.BasicLevel;

public class BasicInfoUtils {
	/**
	 * 
	 * <p>
	 * Description:将基础资料实体转换成BasciLevel<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-26
	 * @param busItem
	 * @param user
	 */
	public static BasicLevel busItemConvertToBasicLevel(BasicInfo basicInfo,User user){
		
		BasicLevel basicLevel = new BasicLevel();
		//设置业务项ID
		basicLevel.setId(basicInfo.getBusItemId());
		//设置业务项名称
		basicLevel.setBasciLevelName(basicInfo.getBusItemName());
		//设置创建时间
		basicLevel.setCreateTime(new Date());
		//设置创建人ID
		basicLevel.setCreateUserId(user.getId());
		//设置最后修改时间
		basicLevel.setLastUpdateTime(new Date());
		//设置最后修改人ID
		basicLevel.setLastModifyUserId(user.getId());
		//设置上报类型
		basicLevel.setType(basicInfo.getReportType());
		return basicLevel;
	}
	/**
	 * 
	 * <p>
	 * Description:将基础资料实体转换成BasciLevel用于更新<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-26
	 * @param busItem
	 * @param user
	 */
	public static BasicLevel basicInfoItemConvertToBasicLevelForUpdate(BasicInfo basicInfo,User user){
		BasicLevel basicLevel = new BasicLevel();
		//设置业务项ID
		basicLevel.setId(basicInfo.getBusItemId());
		//设置业务项名称
		basicLevel.setBasciLevelName(basicInfo.getBusItemName());
		//设置最后修改时间
		basicLevel.setLastUpdateTime(new Date());
		//设置最后修改人ID
		basicLevel.setLastModifyUserId(user.getId());
		//设置上报类型
		basicLevel.setType(basicInfo.getReportType());
		return basicLevel;
	}
	/**
	 * 
	 * <p>
	 * Description:将业务范围实体转换成BasciLevel<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-26
	 * @param busItem
	 * @param user
	 */
	public static BasicLevel busScopeVOItemConvertToBasicLevel(BasicBusScopeVO basicItem, User user){
		BasicLevel basicLevel = new BasicLevel();
		
		//设置业务项ID
		basicLevel.setId(basicItem.getBusItemId());
		//设置业务项名称
		basicLevel.setBasciLevelName(basicItem.getBusItemName());
		//设置创建时间
		basicLevel.setCreateTime(new Date());
		//设置创建人ID
		basicLevel.setCreateUserId(user.getId());
		//设置最后修改时间
		basicLevel.setLastUpdateTime(new Date());
		//设置最后修改人ID
		basicLevel.setLastModifyUserId(user.getId());
		//设置上报类型
		basicLevel.setType(basicItem.getReportType());
		
		return basicLevel;
	}
	/**
	 * 
	 * <p>
	 * Description:将业务范围实体转换成BasciLevel用于修改业务范围的上报类型<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-26
	 * @param busItem
	 * @param user
	 */
	public static BasicLevel busScopeVOConvertTOBasicLevelUpdByParentId(BasicBusScopeVO basicItem, User user){
		BasicLevel basicLevel = new BasicLevel();
		
		//设置ParentId
		basicLevel.setParentId(basicItem.getBusItemId());
		//设置上报类型
		basicLevel.setType(basicItem.getReportType());
		//设置最后修改时间
		basicLevel.setLastUpdateTime(new Date());
		//设置最后修改人ID
		basicLevel.setLastModifyUserId(user.getId());
		return basicLevel;
	}
	/**
	 * 
	 * <p>
	 * Description:将业务范围实体转换成BasciLevel用于新增业务范围<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-26
	 * @param busItem
	 * @param user
	 */
	public static BasicLevel busScopeConvertToBasicLevel(BasicBusScopeVO basicScope,User user){
		BasicLevel basicLevel = new BasicLevel();
		//设置业务范围ID
		basicLevel.setId(basicScope.getBusScopeId());
		//设置业务范围类型
		basicLevel.setBasciLevelName(basicScope.getBusScopeName());
		//设置业务项ID
		basicLevel.setParentId(basicScope.getBusItemId());
		//设置业务范围级别
		basicLevel.setLevel(BasicInfoConstants.BASICLEVELBUSSCOPE);
		//设置创建时间
		basicLevel.setCreateTime(new Date());
		//设置创建人ID
		basicLevel.setCreateUserId(user.getId());
		//设置最后修改时间
		basicLevel.setLastUpdateTime(new Date());
		//设置最后修改人ID
		basicLevel.setLastModifyUserId(user.getId());
		//设置上报类型
		basicLevel.setType(basicScope.getReportType());
		
		return basicLevel;
		
	}
	/**
	 * 
	 * <p>
	 * Description:将业务范围实体转换成业务类型用于新增业务类型<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-26
	 * @param busItem
	 * @param user
	 */
	public static BasicBusType convertBusType(BasicBusScopeVO busScope,BasicBusType busType,User user){
		//设置创建时间
		busType.setCreateTime(new Date());
		//设置创建人ID
		busType.setCreateUserId(user.getId());
		//设置修改时间
		busType.setLastUpdateTime(new Date());
		//设置修改人ID
		busType.setLastModifyUserId(user.getId());
		//设置业务范围ID
		busType.setLevelId(busScope.getBusScopeId());
		
		return busType;
	}

	/**
	 * 
	 * <p>
	 * Description:将从基础资料实体中得到业务项ID<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-26
	 * @param busItem
	 * @param user
	 */
	public static List<String> getBusItemIdsFromBasiInfo(List<BasicInfo> basicInfos){
		List<String> ids = new ArrayList<String>();
		//循环基础资料实体列表
		for( BasicInfo basicInfo : basicInfos ){
			//如果基础资料实体中业务项ID不为空则添加到业务项ID列表中
			if( basicInfo !=null && basicInfo.getBusItemId() != null &&
					!"".equals(basicInfo.getBusItemId())){
				ids.add(basicInfo.getBusItemId());
			}
		}
		return ids;
	}
	/**
	 * 
	 * <p>
	 * Description:将从基础资料实体中得到业务类型ID<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-26
	 * @param busItem
	 * @param user
	 */
	public static List<String> getBusTypeIdsFromBasicInfo(List<BasicInfo> basicInfos){
		List<String> ids = new ArrayList<String>();
		//循环基础资料实体列表
		for( BasicInfo basicInfo : basicInfos ){
			//如果基础资料实体中业务类型ID不为空则添加到业务类型ID列表中
			if( basicInfo !=null && basicInfo.getBusTypeId() != null &&
					!"".equals(basicInfo.getBusTypeId())){
				ids.add(basicInfo.getBusTypeId());
			}
		}
		return ids;
	}
	/**
	 * 
	 * <p>
	 * Description:将从业务范围实体中得到业务类型ID<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-26
	 * @param busItem
	 * @param user
	 */
	public static List<String> getBusTypeIdsFromBasicBusScopeVO(List<BasicBusType> busTypes){
		List<String> ids = new ArrayList<String>();
		//循环业务类型实体列表
		for( BasicBusType busType : busTypes ){
			//如果基础资料实体中业务类型ID不为空则添加到业务类型ID列表中
			if( busType !=null && busType.getId() != null &&
					!"".equals(busType.getId())){
				ids.add(busType.getId());
			}
		}
		return ids;
	}
	/**
	 * 
	 * <p>
	 * Description:将从基础资料实体中得到业务类型ID<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-26
	 * @param busItem
	 * @param user
	 */
	public static List<String> getBusScopeIdsFromBasicInfo(List<BasicInfo> basicInfos){
		List<String> ids = new ArrayList<String>();
		//循环基础资料实体列表
		for( BasicInfo basicInfo : basicInfos ){
			//当基础资料中业务范围ID不为空且业务范围ID
			//集合中不包含该ID将该ID字段添加到业务范围ID集合中
			if( basicInfo!=null && basicInfo.getBusScopeId() !=null && 
					!"".equals(basicInfo.getBusScopeId()) &&
					!ids.contains(basicInfo.getBusScopeId())){
				ids.add(basicInfo.getBusScopeId());
			}
		}
		return ids;
	}
	/**
	 * 
	 * <p>
	 * Description:返回修改过和新增的业务项列表<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-26
	 * @param busItem
	 * @param user
	 */
	public static List<BasicLevel> getDelOrUpdItemListFromBusItemList(List<BasicInfo> newBusItems, List<BasicInfo> orgBusItems,User user){
		//创建要更新的BasicLevel集合
		List<BasicLevel> basicLevels = new ArrayList<BasicLevel>();
		
		BasicLevel basicLevel = null;
		//原始列表中的元素
		BasicInfo orgBusItem = null;
		for( BasicInfo busItem : newBusItems ){
			//如果新的业务项ID为空则是需要新增的字段
			if( busItem.getBusItemId() == null || "".equals(busItem.getBusItemId())){
				//将基础资料实体转换成业务级别实体 
				basicLevel = BasicInfoUtils
						.busItemConvertToBasicLevel(busItem,user);
				//将其添加到要更新或要修改的集合中
				basicLevels.add(basicLevel);
			}else{
				//循环原始列表如果ID相同,业务名称不同或者上报类型不同则为修改过的业务项
				for( int i = 0 ; i < orgBusItems.size();i++ ){					
					orgBusItem = orgBusItems.get(i);
					if( busItem.getBusItemId().equals(orgBusItem.getBusItemId())
							&& (!busItem.getBusItemName().equals(orgBusItem.getBusItemName())
									||!busItem.getReportType().equals(orgBusItem.getReportType()))){
						//将基础资料实体转换成业务级别实体 
						basicLevel = BasicInfoUtils
								.busItemConvertToBasicLevel(busItem,user);
						//将其添加到要更新或要修改的集合中
						basicLevels.add(basicLevel);
						break;
					}
				}
			}
		}
		return basicLevels;
	}

}
