package com.deppon.crm.module.marketing.server.utils;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.deppon.crm.module.authorization.shared.domain.User;
import com.deppon.crm.module.marketing.shared.domain.activity.ActivityClientBase;
import com.deppon.crm.module.marketing.shared.domain.activity.ActivityDept;
import com.deppon.crm.module.marketing.shared.domain.activity.ActivityOption;
import com.deppon.crm.module.marketing.shared.domain.activity.LineDept;
import com.deppon.crm.module.marketing.shared.domain.activity.MarketActivity;
import com.deppon.crm.module.marketing.shared.domain.activity.MarketActivityConstance;
import com.deppon.crm.module.marketing.shared.domain.activity.MarketActivityVO;
import com.deppon.crm.module.marketing.shared.exception.MarketActivityException;
import com.deppon.crm.module.marketing.shared.exception.MarketActivityExceptionType;
import com.deppon.foss.framework.exception.GeneralException;

/**   
 * <p>
 * Description:市场推广活动校验类<br />
 * </p>
 * @title MarketActivityValidator.java
 * @package com.deppon.crm.module.marketing.server.utils 
 * @author ZhouYuan
 * @version 0.1 2014-03-07
 */
public class MarketActivityValidator {
	/**   
	 * <p>
	 * Description:校验用户是否为营销活动管理组或快递市场营销组<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-01-21
	 * @param MarketActivity 部门ID
	 */
	public static String checkUserAuthDept(User user){
		if(user != null && user.getAccessUris()!=null){
			//如果为营销活动管理组
			if( user.getAccessUris().contains(MarketActivityConstance.AUTH_LTT_SEARCHALL) ){
				//返回部门性质为营销活动管理组
				return MarketActivityConstance.DEPT_CHARACTER_LTTGROUP;
			//如果为快递市场营销组
			}else if(user.getAccessUris().contains(MarketActivityConstance.AUTH_EXPRESS_SEARCHALL)){
				//返回部门性质为快递市场营销组
				return MarketActivityConstance.DEPT_CHARACTER_EXPGROUP;
			}
		}
		//都不是返回null
		return null;
	}
	/**   
	 * <p>
	 * Description:校验部门性质是否为空<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-01-21
	 * @param String 部门性质
	 */
	@SuppressWarnings("serial")
	public static void checkDeptCharacter( String deptCharacter ){
		if( StringUtils.isEmpty(deptCharacter) ){
			//新建MarketActivityException
			MarketActivityException e1 = new MarketActivityException(MarketActivityExceptionType.deptCharacterIsEmpty);
			throw new GeneralException(e1.getErrorCode(), e1.getMessage(), e1,
					new Object[] {}) {
			};
		}
	}
	/**   
	 * <p>
	 * Description:校验市场推广活动主体<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-01-21
	 * @param MarketActivity 市场推广活动
	 */
	@SuppressWarnings("serial")
	public static void checkMarketActivity(MarketActivity ma,String deptCharacter){
		//如果该员工为营销活动管理小组或快递市场营销组,但所建活动类型不为全国市场推广活动
		if( (MarketActivityConstance.DEPT_CHARACTER_LTTGROUP.equals(deptCharacter)
				||MarketActivityConstance.DEPT_CHARACTER_EXPGROUP.equals(deptCharacter))
				&& !MarketActivityConstance.ACTIVITY_TYPE_NATION.equals(ma.getActivityType())){
			//新建MarketActivityException
			MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityTypeIsNationWide);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		//市场推广活动为空
		if( ma == null){			
			//新建MarketActivityException
			MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.marketActivityIsNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		//活动类型不能为空
		if( StringUtils.isEmpty(ma.getActivityType())){
			//新建MarketActivityException
			MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityTypeIsNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		//如果该员工为事业部或大区,但所建活动类型不为区域市场推广活动
		if((MarketActivityConstance.DEPT_CHARACTER_BIGREGION.equals(deptCharacter)
				||MarketActivityConstance.DEPT_CHARACTER_DIVISION.equals(deptCharacter))
				&& !MarketActivityConstance.ACTIVITY_TYPE_REGION.equals(ma.getActivityType())){
			//新建MarketActivityException
			MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityTypeIsRegion);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		//如果该员工为营销活动管理小组或事业部或大区,但所建活动类别不为零担
		if( (MarketActivityConstance.DEPT_CHARACTER_LTTGROUP.equals(deptCharacter)
				||MarketActivityConstance.DEPT_CHARACTER_BIGREGION.equals(deptCharacter)
				||MarketActivityConstance.DEPT_CHARACTER_DIVISION.equals(deptCharacter))
				&&!MarketActivityConstance.ACTIVITY_CATEGORY_LTT.equals(ma.getActivityCategory())){
			//新建MarketActivityException
			MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityCategoryIsExpress);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		//如果该员工为快递市场营销组,但所建活动类别不为快递
		if(MarketActivityConstance.DEPT_CHARACTER_EXPGROUP.equals(deptCharacter)
				&&!MarketActivityConstance.ACTIVITY_CATEGORY_EXPRESS.equals(ma.getActivityCategory())){
			//新建MarketActivityException
			MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityCategoryIsExpress);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		
		//申请人工号或姓名不能为空
		if( StringUtils.isEmpty(ma.getProposerCode())|| StringUtils.isEmpty(ma.getProposerName())){
			//新建MarketActivityException
			MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityProposerIsNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		//活动开始时间不能为空
		if( ma.getStartTime() == null ){
			//新建MarketActivityException
			MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityStartTimeIsNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		//活动结束时间不能为空
		if( ma.getEndTime() == null ){
			//新建MarketActivityException
			MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityEndTimeIsNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		//活动结束时间小于活动开始时间
		if( ma.getEndTime().getTime()-ma.getStartTime().getTime()<0){
			//新建MarketActivityException
			MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityEndTimeLessThanStartTime);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		//活动主题不能为空,不能大于200
		if( StringUtils.isEmpty(ma.getTopic()) || ma.getTopic().length()>200){
			//新建MarketActivityException
			MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityTopicIsIllegal);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		//活动内容不能为空,不能大于500
		if( StringUtils.isEmpty(ma.getContent()) || ma.getContent().length()>500){
			//新建MarketActivityException
			MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityContentIsIllegal);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		//对外宣传语不能为空,不能大于300
		if( StringUtils.isEmpty(ma.getSlogan()) || ma.getSlogan().length()>300){
			//新建MarketActivityException
			MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activitySloganIsIllegal);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		//申请事由不能为空
		if( StringUtils.isEmpty(ma.getApplyReason())||ma.getApplyReason().length()>50){
			//新建MarketActivityException
			MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityApplyReasonIsIllegal);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		//第一个月目标不能为空
		if( ma.getTarget1()==null||ma.getTarget1().length()>20){
			//新建MarketActivityException
			MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityFirstTargetIsIllegal);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		//申请物品信息不能超过10条
		if( ma.getMaterialInfo() != null && ma.getMaterialInfo().size()>10 ){
			//新建MarketActivityException
			MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityMaterialInfoBeyondMaxSize);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		//对接人不能为空
		if( StringUtils.isEmpty(ma.getContactsId()) 
				||StringUtils.isEmpty(ma.getContactsName())){
			//新建MarketActivityException
			MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityContactsIsNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		//联系电话不合法
		if( ma.getContactsTel() == null || !ma.getContactsTel().matches("^[0-9-]{1,20}$")){
			//新建MarketActivityException
			MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityContactsTelIsIllegal);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		//开单金额开始值或结束值为空
		if( StringUtils.isEmpty(ma.getMinBillAmt()) ^ StringUtils.isEmpty(ma.getMaxBillAmt())){
			//新建MarketActivityException
			MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityBillAmtIsNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		//开单金额开始值或结束值不合法
		if( !StringUtils.isEmpty(ma.getMinBillAmt()) && !StringUtils.isEmpty(ma.getMaxBillAmt()) ){
			if( !ma.getMinBillAmt().matches(MarketActivityConstance.NUMBER_REGEX)
					||!ma.getMaxBillAmt().matches(MarketActivityConstance.NUMBER_REGEX)){
				//新建MarketActivityException
				MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityBillAmtIsIllegal);
				throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
						new Object[] {}) {
				};
			}
			//开单金额最大值小于开始值
			if( Double.parseDouble(ma.getMaxBillAmt())-Double.parseDouble(ma.getMinBillAmt())<0){
				//新建MarketActivityException
				MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityMaxBillAmtLessThanMinBillAmt);
				throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
						new Object[] {}) {
				};
			}
		}
		//货物重量开始值或结束值为空
		if( StringUtils.isEmpty(ma.getMinCargoWeight()) ^ StringUtils.isEmpty(ma.getMaxCargoWeight())){
			//新建MarketActivityException
			MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityCargoWeightIsNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		//货物重量开始值或结束值不合法
		if( !StringUtils.isEmpty(ma.getMinCargoWeight()) && !StringUtils.isEmpty(ma.getMaxCargoWeight()) ){
			if( !ma.getMinCargoWeight().matches(MarketActivityConstance.NUMBER_REGEX)
					||!ma.getMaxCargoWeight().matches(MarketActivityConstance.NUMBER_REGEX)){
				//新建MarketActivityException
				MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityCargoWeightIsIllegal);
				throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
						new Object[] {}) {
				};
			}
			//货物重量最大值小于开始值
			if( Double.parseDouble(ma.getMaxCargoWeight())-Double.parseDouble(ma.getMinCargoWeight())<0){
				//新建MarketActivityException
				MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityMaxCargoWeightLessThanMinCargoWeight);
				throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
						new Object[] {}) {
				};
			}
		}
		//货物体积开始值或结束值为空
		if( StringUtils.isEmpty(ma.getMinCargoVolume()) ^ StringUtils.isEmpty(ma.getMaxCargoVolume())){
			//新建MarketActivityException
			MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityCargoVolumeIsNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		//货物体积开始值或结束值不合法
		if( !StringUtils.isEmpty(ma.getMinCargoVolume()) && !StringUtils.isEmpty(ma.getMaxCargoVolume()) ){
			if( !ma.getMinCargoVolume().matches(MarketActivityConstance.NUMBER_REGEX)
					||!ma.getMaxCargoVolume().matches(MarketActivityConstance.NUMBER_REGEX)){
				//新建MarketActivityException
				MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityCargoVolumeIsIllegal);
				throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
						new Object[] {}) {
				};
			}
			//货物体积最大值小于开始值
			if( Double.parseDouble(ma.getMaxCargoVolume())-Double.parseDouble(ma.getMinCargoVolume())<0){
				//新建MarketActivityException
				MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityMaxCargoVolumeLessThanMinCargoVolume);
				throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
						new Object[] {}) {
				};
			}
		}
//		//开展部门不能为空
//		if( ma.getActivityDepts() == null || ma.getActivityDepts().size()<=0){
//			//新建MarketActivityException
//			MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityDeptsIsNull);
//			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
//					new Object[] {}) {
//			};
//		}
		//优惠方式不能为空
		if( StringUtils.isEmpty(ma.getPreferType())){
			//新建MarketActivityException
			MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityPreferTypeIsNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		if( MarketActivityConstance.OPTION_COUPON.equals(ma.getPreferType())){
			//优惠券信息不能为空
			if( ma.getCouponInfo() == null || ma.getCouponInfo().size()<=0 ){
				//新建MarketActivityException
				MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityCouponInfoIsNull);
				throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
						new Object[] {}) {
				};
			}
			if(  ma.getCouponInfo().size()>10){
				//新建MarketActivityException
				MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityCouponInfoIsIllegal);
				throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
						new Object[] {}) {
				};
			}
			//抵扣类型或优惠券数量不合法
			for( ActivityOption o : ma.getCouponInfo()){
				if( !MarketActivityConstance.ListPreferType.contains(o.getType())
						|| o.getValue() == null || !o.getValue().matches("\\d{1,20}")){
					//新建MarketActivityException
					MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityCouponTypeOrNumIsIllegal);
					throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
							new Object[] {}) {
					};
				}
			}
			//使用条件不能为空
			if( StringUtils.isEmpty(ma.getUseRule()) || ma.getUseRule().length()>100){
				//新建MarketActivityException
				MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityUseRuleIsIllegal);
				throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
						new Object[] {}) {
				};
			}
			//生成条件不能为空
			if( StringUtils.isEmpty(ma.getCreateRule()) || ma.getCreateRule().length()>100){
				//新建MarketActivityException
				MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityCreateRuleIsIllegal);
				throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
						new Object[] {}) {
				};
			}
		}
		if( MarketActivityConstance.OPTION_DISCOUNT.equals(ma.getPreferType())){
			//折扣信息不能为空
			if( ma.getDiscountInfo() == null || ma.getDiscountInfo().size()<=0){
				//新建MarketActivityException
				MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityDiscountInfoIsNull);
				throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
						new Object[] {}) {
				};
			}
			if(  ma.getDiscountInfo().size()>10 ){
				//新建MarketActivityException
				MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityDiscountInfoIsIllegal);
				throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
						new Object[] {}) {
				};
			}
			//优惠类型或折扣不合法
			for( ActivityOption o : ma.getDiscountInfo()){
				if( !MarketActivityConstance.ListPreferType.contains(o.getType())
						|| o.getValue() == null || !o.getValue().matches(MarketActivityConstance.DISCOUNT_REGEX)){
					//新建MarketActivityException
					MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityDiscountTypeOrNumIsIllegal);
					throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
							new Object[] {}) {
					};
				}
			}
			if( MarketActivityConstance.ACTIVITY_TYPE_REGION.equals(ma.getActivityType())
					&& StringUtils.isEmpty(ma.getWorkFlowNum())){
				//新建MarketActivityException
				MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityWorkFlowNumIsNull);
				throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
						new Object[] {}) {
				};
			}
		}
//		if( ma.getClientBase() == null || ma.getClientBase().size()<=0){
//			//新建MarketActivityException
//			MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityClientBaseIsNull);
//			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
//					new Object[] {}) {
//			};
//		}
//		if( ma.getFiles() == null || ma.getFiles().size()<=0){
//			//新建MarketActivityException
//			MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityFilesIsNull);
//			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
//					new Object[] {}) {
//			};
//		}
	}
	/**   
	 * <p>
	 * Description:校验市场推广活动保存是否成功<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-01-21
	 * @param int
	 */
	@SuppressWarnings("serial")
	public static void checkSaveMarketActivitySuccess( int updateCount ){
		if( updateCount <= 0 ){
			//新建MarketActivityException
			MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activitySaveFail);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
	}
	/**   
	 * <p>
	 * Description:校验走货线路是否合法<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-01-21
	 * @param int
	 */
	@SuppressWarnings("serial")
	public static void checkLineDeptIsIllegal(List<LineDept> lineDepts,String lineRequest){
		if( lineDepts != null && lineDepts.size()>100){
			//新建MarketActivityException
			MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activitySaveLineDeptLeaveDeptBeyondMaxSize);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		for( LineDept line : lineDepts ){
			if( MarketActivityConstance.LINE_TYPE_LEAVE.equals(lineRequest)){
				//出发区域为空或者到达区域为空
				if( StringUtils.isEmpty(line.getLeaveDeptCode())
						||!StringUtils.isEmpty(line.getArriveDeptCode())){
					//新建MarketActivityException
					MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activitySaveLineDeptLeaveDeptIsIllegal);
					throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
							new Object[] {}) {
					};
				}
			}else if( MarketActivityConstance.LINE_TYPE_ARRIVE.equals(lineRequest) ){
				//出发区域为空或者到达区域为空
				if( !StringUtils.isEmpty(line.getLeaveDeptCode())
						||StringUtils.isEmpty(line.getArriveDeptCode())){
					//新建MarketActivityException
					MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activitySaveLineDeptArriveDeptIsIllegal);
					throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
							new Object[] {}) {
					};
				}
			}else if( MarketActivityConstance.LINE_TYPE_LEAVE_ARRIVE.equals(lineRequest)){
				//出发区域为空且到达区域为空
				if( StringUtils.isEmpty(line.getLeaveDeptCode())
						&&StringUtils.isEmpty(line.getArriveDeptCode())){
					//新建MarketActivityException
					MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activitySaveLineDeptLevOrArrDeptIsIllegal);
					throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
							new Object[] {}) {
					};
				}
			}
		}
	}
	/**   
	 * <p>
	 * Description:校验客户群是否合法<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2013-01-21
	 * @param List<ActivityClientBase>
	 */
	@SuppressWarnings("serial")
	public static void checkActivityClient(List<ActivityClientBase> clientBases,List<ActivityClientBase> searchClients){
		//如果前台传的客户群列表为空
		if( clientBases==null || clientBases.size()<=0 ){
			//新建MarketActivityException
			MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityClientBaseIsNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		if( clientBases.size()>10 ){
			//新建MarketActivityException
			MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityClientBaseIsIllegal);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		//如果查询的客户群数量与前台传的客户群数量不一致,则有客户群已被删除
		if( searchClients == null || searchClients.size()!= clientBases.size()){
			//新建MarketActivityException
			MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityClientBaseIsDelete);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		//如果查询出来的客户群状态不为未使用
		for( ActivityClientBase acb :searchClients ){
			if( !MarketActivityConstance.CLIENTBASE_STATUS_UNUSED.equals(acb.getClientBaseStatus())){
				//新建MarketActivityException
				MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityClientBaseStatusIsIllegal);
				throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
						new Object[] {}) {
				};
			}
			if( StringUtils.isEmpty( acb.getClientBaseId())){
				//新建MarketActivityException
				MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityClientBaseIdIsNull);
				throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
						new Object[] {}) {
				};
			}
		}
	}
	/**   
	 * <p>
	 * Description:校验是否有权限修改推广活动<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2014-3-18
	 * @param int auth
	 */
	@SuppressWarnings("serial")
	public static void checkUpdateAuth(List<MarketActivityVO> activities,User user){
		//如果查询出来的市场推广活动不为1条
		if( activities == null || activities.size()!= 1 ){
			//新建MarketActivityException
			MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityUpdateAuthIsIllegal);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}else{
			MarketActivityVO mav = activities.get(0);
			if( mav == null ){
				//新建MarketActivityException
				MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityUpdateAuthIsIllegal);
				throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
						new Object[] {}) {
				};

			}
			//如果该用户不是该客户群的创建人,且 该用户不属于该市场推广活动的创建部门或者该客户不是该部门的负责人
			if(  !user.getEmpCode().getDeptId().getId().equals(mav.getDeptId())||
					(!user.getEmpCode().getId().equals(mav.getCreateUser())&&
					!user.getEmpCode().getEmpCode().equals(user.getEmpCode().getDeptId().getPrincipal()))){
				//新建MarketActivityException
				MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityUpdateAuthIsIllegal);
				throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
						new Object[] {}) {
				};
			}
			//区域市场推广活动不能被修改
		    if( MarketActivityConstance.ACTIVITY_TYPE_REGION.
					equals(mav.getActivityType())){
				//新建MarketActivityException
				MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.updateActivityMarketActivityIsRegion);
				throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
						new Object[] {}) {
				};
			//市场推广活动状态不为已制定,折扣已生效,下发完成	
			}else if(  !MarketActivityConstance.ACTIVITY_STATUS_ESTABLISHED.equals(mav.getActivityStatus())
						&& !MarketActivityConstance.ACTIVITY_STATUS_EXECUTED.equals(mav.getActivityStatus())
						&& !MarketActivityConstance.ACTIVITY_STATUS_PLANCREATED.equals(mav.getActivityStatus())){
				//新建MarketActivityException
				MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.updateActivityMarketActivityStatusIsIllegal);
				throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
						new Object[] {}) {
				};
			}
		}
	}
	/**   
	 * <p>
	 * Description:校验是否有权限下发客户群<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2014-3-18
	 * @param int auth
	 */
	@SuppressWarnings("serial")
	public static void checkCreateClientBasePlanAuth(List<MarketActivityVO> activities,ActivityClientBase client,User user){
		if( activities == null || activities.size()!=1 ){
			//新建MarketActivityException
			MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityUpdateAuthIsIllegal);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}else{
			MarketActivityVO mav = activities.get(0);
			if( mav == null ){
				//新建MarketActivityException
				MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityUpdateAuthIsIllegal);
				throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
						new Object[] {}) {
				};			
			}
			//如果该用户不是该客户群的创建人,且 该用户不属于该市场推广活动的创建部门或者该客户不是该部门的负责人
			if(  !user.getEmpCode().getDeptId().getId().equals(mav.getDeptId())||
					(!user.getEmpCode().getId().equals(mav.getCreateUser())&&
					!user.getEmpCode().getEmpCode().equals(user.getEmpCode().getDeptId().getPrincipal()))){
				//新建MarketActivityException
				MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityUpdateAuthIsIllegal);
				throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
						new Object[] {}) {
				};
			}
			//全国市场推广活动,状态不为已制定\折扣已生效 区域市场推广活动状态不为审批通过\折扣已生效	
			if( (MarketActivityConstance.ACTIVITY_TYPE_NATION.equals(mav.getActivityType())
					&& !MarketActivityConstance.ACTIVITY_STATUS_ESTABLISHED.equals(mav.getActivityStatus()))
					&&(MarketActivityConstance.ACTIVITY_TYPE_REGION.endsWith(mav.getActivityType())
							&& !MarketActivityConstance.ACTIVITY_STATUS_PROCESSED.equals(mav.getActivityStatus()))
							&& !MarketActivityConstance.ACTIVITY_STATUS_EXECUTED.equals(mav.getActivityStatus())){
				//新建MarketActivityException
				MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.updateActivityMarketActivityStatusIsIllegal);
				throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
						new Object[] {}) {
				};
			}
			if( !MarketActivityConstance.CLIENTBASE_STATUS_USEING.equals( client.getClientBaseStatus()) ){
				//新建MarketActivityException
				MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.createClientBasePlanClientStatusIsIllegal);
				throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
						new Object[] {}) {
				};
			}
			//计划开始时间或结束时间为空
			if( client.getPlanStartTime() == null || client.getPlanEndTime() == null){
				MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityClientBasePlanTimeIsNull);
				throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
						new Object[] {}) {
				};
			}
			//计划开始时间小于结束时间
			if( client.getPlanEndTime().getTime()-client.getPlanStartTime().getTime()<0){
				MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityClientBasePlanTimeIsIllegal);
				throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
						new Object[] {}) {
				};
			}
			//校验客户数量是不是为空
			if( client.getClientNum()== null){
				MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.marketActivityClientNumIsNull);
				throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
						new Object[] {}) {
				};
			}
			//校验客户数量是不是为0
			if( "0".equals(client.getClientNum())){
				MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.marketActivityClientNumIsZero);
				throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
						new Object[] {}) {
				};
			}
		}
	}
	/**   
	 * <p>
	 * Description:校验是否有客户群是否可以删除<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2014-3-18
	 * @param int auth
	 */
	@SuppressWarnings("serial")
	public static void checkDeleteActivityClientBase( List<ActivityClientBase> clients){
		if( clients != null && clients.size()>0){
			for( ActivityClientBase client : clients ){
				if( !MarketActivityConstance.CLIENTBASE_STATUS_USEING.equals(client.getClientBaseStatus())){
					//新建MarketActivityException
					MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityClientBaseStatusCanNotDelete);
					throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
							new Object[] {}) {
					};
				}
			}
		}
	}
	/**   
	 * <p>
	 * Description:校验客户群是否添加或删除成功<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2014-3-18
	 * @param int auth
	 */
	@SuppressWarnings("serial")
	public static void checkActivityClientBaseAddOrDelStatus( boolean status){	
		if( status != true ){
			//新建MarketActivityException
			MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityClientBaseAddOrDelFail);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		
	}
	/**   
	 * <p>
	 * Description:校验市场推广活动参与部门是否为空<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2014-3-18
	 * @param int deptNum
	 */
	@SuppressWarnings("serial")
	public static void checkActivityDept(int deptNum){
		if( deptNum <=0 ){
			//新建MarketActivityException
			MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityDeptsIsNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
	}
	/**   
	 * <p>
	 * Description:校验市场推广活动参与部门是否合法<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2014-3-18
	 * @param int deptNum
	 */
	@SuppressWarnings("serial")
	public static void checkActivityDeptActivityIdAndDeptCode(List<ActivityDept> acDepts){
		//部门列表不能为空
		if( acDepts == null || acDepts.size()<=0){
			//新建MarketActivityException
			MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityDeptsIsNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		for( ActivityDept dept : acDepts ){
			//市场推广活动ID不能为空
			if( StringUtils.isEmpty(dept.getActivityId())){
				//新建MarketActivityException
				MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityDeptsActivityIdIsNull);
				throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
						new Object[] {}) {
				};
			}
			//市场推广活动部门标杆编码不能为空
			if( StringUtils.isEmpty(dept.getDeptStandardCode())){
				//新建MarketActivityException
				MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityDeptsCodeIsNull);
				throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
						new Object[] {}) {
				};
			}
		}
	}
	/**   
	 * <p>
	 * Description:校验前台传入的客户群是否为空<br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2014-3-18
	 * @param int deptNum
	 */
	@SuppressWarnings("serial")
	public static void checkUpdateClientIsNull(List<ActivityClientBase> clients){
		//如果前台传的客户群列表为空
		if( clients==null || clients.size()<=0 ){
			//新建MarketActivityException
			MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.activityClientBaseIsNull);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
	}
	/**   
	 * <p>
	 * Description:校验活动名称是否重复 <br />
	 * </p>
	 * @author ZhouYuan
	 * @version 0.1 2014-4-15
	 * @param String activityName
	 */
	@SuppressWarnings("serial")
	public static void checkHaveSamNameActivity(int count){
		if( count > 0 ){
			//新建MarketActivityException
			MarketActivityException e = new MarketActivityException(MarketActivityExceptionType.haveSamNameMarketActivity);
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
	}
	@SuppressWarnings("serial")
	public static void checkWorkFlowIfApprove(String flag){
		MarketActivityException e;
		if(MarketActivityConstance.WORKFLOW_STATUS_NONE.equals(flag)){
			 e = new MarketActivityException(MarketActivityExceptionType.discountWorkflowIsNone);//OA无对应的折扣工作流，请核对
		}else if(MarketActivityConstance.WORKFLOW_STATUS_REPEAT.equals(flag)){
			e = new MarketActivityException(MarketActivityExceptionType.discountWorkflowRepeat);//该工作流已被其他市场推广活动使用，不可重复！
		}else if(MarketActivityConstance.WORKFLOW_STATUS_PASS.equals(flag)){
			return;
		}else{
			e = new MarketActivityException(MarketActivityExceptionType.discountWorkflowUnavailable);//该价格折扣工作流尚未审批通过！
		}
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
	
	}
	
}
