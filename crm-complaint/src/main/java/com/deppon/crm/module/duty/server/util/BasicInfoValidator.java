package com.deppon.crm.module.duty.server.util;

import java.util.List;

import com.deppon.crm.module.duty.shared.domain.BasicBusScopeVO;
import com.deppon.crm.module.duty.shared.domain.BasicBusType;
import com.deppon.crm.module.duty.shared.domain.BasicInfo;
import com.deppon.crm.module.duty.shared.exception.DutyException;
import com.deppon.crm.module.duty.shared.exception.DutyExceptionType;
import com.deppon.foss.framework.exception.GeneralException;

public class BasicInfoValidator {
	/**
	 * 
	 * <p>
	 * Description:查看业务项数据是否合法<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-22
	 * @param busItem
	 * @param user
	 */
	public static void checkBusItem(BasicInfo basicInfo){
		//检查业务项名称是否为空
		if( null == basicInfo.getBusItemName() || "".equals(basicInfo.getBusItemName())){
			DutyException e = new DutyException(DutyExceptionType.basicInfoBusItemCanNotNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		//检查上报类型是否为投诉或异常
		if( !BasicInfoConstants.BASICCOMPTYPECOMPAINT.equals(basicInfo.getReportType())&&
				!BasicInfoConstants.BASICCOMPTYPEUNUSUAL.endsWith(basicInfo.getReportType())){
			DutyException e = new DutyException(DutyExceptionType.basicInfoReprotTypeCanNotNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
	}
	/**
	 * 
	 * <p>
	 * Description:查看业务项数据是否合法在修改时<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-22
	 * @param busItem
	 * @param user
	 */
	public static void checkBusItemForUpdate(BasicInfo basicInfo){
		//业务项ID不能为空
		if(	basicInfo.getBusItemId() == null || 
				"".equals(basicInfo.getBusItemId())){
			DutyException e = new DutyException(DutyExceptionType.basicInfoBusItemIdCanNotNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		checkBusItem(basicInfo);
	}
	/**
	 * @
	 * <p>
	 * Description:新增或修改业务范围,业务类型时的查询校验<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-22
	 * @param busItem
	 * @param user
	 */
	public static void checkBasicBusScopeVOBySearch(BasicBusScopeVO bbsVO){
		//业务项ID不能为空
		if( null == bbsVO.getBusItemId() || "".equals(bbsVO.getBusItemId())){
			DutyException e = new DutyException(DutyExceptionType.basicBusScopeVOBusItemCanNotNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
//		//业务范围名称不能为空
//		if( null == bbsVO.getBusScopeName() || "".equals(bbsVO.getBusScopeName())){
//			DutyException e = new DutyException(DutyExceptionType.basicBusScopeVOBusScopeNameCanNotNull);
//			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
//					new Object[] {}) {
//			};
//		}
//		//上报类型不合法
//		if( !BasicInfoConstants.BASICCOMPTYPECOMPAINT.equals(bbsVO.getReportType()) && 
//				!BasicInfoConstants.BASICCOMPTYPEUNUSUAL.equals(bbsVO.getReportType())){
//			DutyException e = new DutyException(DutyExceptionType.newBasicBusItemTypeIllegal);
//			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
//					new Object[] {}) {
//			};
//		}
	}
	/**
	 * @
	 * <p>
	 * Description:新增业务项校验业务项列表数据<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-22
	 * @param busItem
	 * @param user
	 */
	public static void checkSaveBasicItem(List<BasicInfo> basicItems){
		//检查新增业务项列表 业务项列表不能为空
		if( basicItems == null || basicItems.size() == 0){
			DutyException e = new DutyException(DutyExceptionType.basicBusItemsCanotNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		for(BasicInfo basItem : basicItems){
			//业务项不能为空
			if( basItem == null ){
				DutyException e = new DutyException(DutyExceptionType.newBasicBusItemCanotNull);
				throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
						new Object[] {}) {
				};
			}
			//业务项名称不能为空
			if( null == basItem.getBusItemName()|| "".equals(basItem.getBusItemName())){
				DutyException e = new DutyException(DutyExceptionType.newBasicBusItemNameCanotNull);
				throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
						new Object[] {}) {
				};
			}
			//上报类型不合法
			if( !BasicInfoConstants.BASICCOMPTYPECOMPAINT.equals(basItem.getReportType()) && !BasicInfoConstants.BASICCOMPTYPEUNUSUAL.equals(basItem.getReportType())){
				DutyException e = new DutyException(DutyExceptionType.newBasicBusItemTypeIllegal);
				throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
						new Object[] {}) {
				};
			}
		}
	}
	/**
	 * 
	 * <p>
	 * Description:检查ID列表是否为空<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-26
	 * @param busItem
	 * @param user
	 */
	public static void checkIdList(List<String> ids){
		//检查ID列表是否为空
		if( ids == null || ids.size() == 0 ){
			DutyException e = new DutyException(DutyExceptionType.newBasicBusItemidsIsNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
	}
	/**
	 * @
	 * <p>
	 * Description:检查要插入的业务范围是否合法<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-26
	 * @param busItem
	 * @param user
	 */
	public static void checkBusScopeVOInsert(BasicBusScopeVO bbv){
		//检查BusItemId不能为空
		if(bbv.getBusItemId()==null || "".equals(bbv.getBusItemId())){
			DutyException e = new DutyException(DutyExceptionType.basicInfoBusItemIdCanNotNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		//当业务范围ID为空 检查业务范围名称不能为空
		if( (bbv.getBusScopeId()==null || "".equals(bbv.getBusScopeId())) && 
				(bbv.getBusScopeName()==null || "".equals(bbv.getBusScopeName()))){
			DutyException e = new DutyException(DutyExceptionType.basicBusScopeVOBusScopeNameCanNotNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		List<BasicBusType> busTypes = bbv.getBusTypes();
		//当业务类型不为空时
		if( busTypes !=null && busTypes.size() != 0){
			for( BasicBusType busType : busTypes){
				//业务类型不能为空
				if( busType == null ){
					DutyException e = new DutyException(DutyExceptionType.newBasicBusTypeIsNull);
					throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
							new Object[] {}) {
					};
				}
				//业务类型 业务类型名称为空
				if( busType.getBusType() ==null || "".equals(busType.getBusType())){
					DutyException e = new DutyException(DutyExceptionType.newBasicBusTypeNameIsNull);
					throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
							new Object[] {}) {
					};
				}
				//如果反馈时间不为空 反馈时间不能为负数
				if( busType.getFeedbackLimit()!= null && !"".equals(busType.getFeedbackLimit()) 
						&& !busType.getFeedbackLimit().matches("^\\d\\d*$")){
					DutyException e = new DutyException(DutyExceptionType.newBasicBusTypeFeedbackIsIllegal);
					throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
							new Object[] {}) {
					};
				}
				//如果处理时间不为空 处理时间不能为负数
				if( busType.getProcLimit()!= null && !"".equals(busType.getProcLimit()) 
						&& !busType.getProcLimit().matches("^\\d\\d*$")){
					DutyException e = new DutyException(DutyExceptionType.newBasicBusTypeProcIsIllegal);
					throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
							new Object[] {}) {
					};
				}
			}
		}
	}
	/**
	 * 
	 * <p>
	 * Description:检查要插入的业务类型是否合法<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-2-26
	 * @param busItem
	 * @param user
	 */
	public static void checkBusTypeSave(BasicBusType busType){
		if( busType == null ){
			//业务类型不能为空
			DutyException e = new DutyException(DutyExceptionType.newBasicBusTypeIsNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		//业务类型名称不能为空
		if( busType.getBusType() == null || "".equals(busType.getBusType())){
			DutyException e = new DutyException(DutyExceptionType.newBasicBusTypeNameIsNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		//处理语言不能为空
		if( busType.getDealLanguage() == null || "".equals(busType.getDealLanguage())){
			DutyException e = new DutyException(DutyExceptionType.newBasicBusTypeDealLanIsNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		//反馈时限不合法
		if( busType.getFeedbackLimit() == null || !busType.getFeedbackLimit().matches("^\\d\\d*$")){
			DutyException e = new DutyException(DutyExceptionType.newBasicBusTypeFeedbackIsIllegal);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		//处理时限不合法
		if( busType.getProcLimit() == null || !busType.getProcLimit().matches("^\\d\\d*$")){
			DutyException e = new DutyException(DutyExceptionType.newBasicBusTypeProcIsIllegal);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
	}
}
