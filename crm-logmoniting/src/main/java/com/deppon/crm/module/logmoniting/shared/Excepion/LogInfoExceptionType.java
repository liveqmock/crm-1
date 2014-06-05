package com.deppon.crm.module.logmoniting.shared.Excepion;

public enum LogInfoExceptionType {

		//保存对象有参数为空!
		LogInfo_isNull("i18n.log.logInfoisNull"),
		//对象为空
		Object_is_Null("i18n.log.ObjectIsNull"),
		//对象参数都为空
		ObjectParames_Are_Null("i18n.log.ObjectPramesAreNull"),
		//传递的统计开始时间和统计结束时间有误,请验证！
		BeginTime_Or_EndTime_Is_Error("i18n.log.beginTimeOrEndTimeIsError"),
		//超过查询范围
		Beyond_SelectTime("i18n.log.beyondSelectTime"),
		//查询条件:统计方式和级别优先级不能同时为空!
		LevelAndStatisticalMethodsCanNotBeEmpty("i18n.log.LevelAndStatisticalMethodsCanNotBeEmpty"),
		//查询条件:统计频率必须不能为空！
		StatisticalFrequencyIsEmpty("i18n.log.StatisticalFrequencyIsEmpty"),
		//基础action信息已经添加,请核实！
		BasicActionInfo_Has_AlreadyExist("i18n.log.BasicActionInfo_Has_AlreadyExist"),
		//请求次数或者请求时间不能为小数!
		TimeCount_And_RequestCount_CanNotAsDecimal("i18n.log.TimeCount_And_RequestCount_CanNotAsDecimal"),
		//请求次数或者请求时间不能小于零!
		TimeCount_And_RequestCount_CanNotBeforeZero("i18n.log.TimeCount_And_RequestCount_CanNotBeforeZero"),
		//浮动值百分比必须在0%至100%之间！
		TimeFloatAndRequestFloatMustBeZeroToOne("i18n.log.TimeFloatAndRequestFloatMustBeZeroToOne"),
		//查询条件存在为空！
		SearchConditionParam_iS_Null("i18n.log.SearchConditionParam_iS_Null"),
		//排序查询的结果小于指定的查询的结果,请核实验证！
		SortResult_IS_ERROR("i18n.log.SortResult_IS_ERROR"),
		//添加的异常编码对象中存在模块名或者异常编码或异常信息为空!
		ModuleName_ErrorCode_ExceptionInfo_isNull("i18n.log.ModuleName_ErrorCode_ExceptionInfo_isNull"),
		//异常编码已经存在,请核实！
		ErrorObject_HasAlreadyExsits("i18n.log.ErrorObject_HasAlreadyExsits"),
		//根据条件查询出的对象为空！
		SearchResultObject_isNull("i18n.log.searchResultObject_isNull"),
		Data_Lost_Error("i18n.log.Data_Lost_Error");
		
		private String errCode;

		private LogInfoExceptionType(String errCode) {
			this.errCode = errCode;
		}

		/**
		 * <p>
		 * Description:errCode<br />
		 * </p>
		 * @author CoCo
		 * @version 0.1 2013-2-27
		 */
		public String getErrCode() {
			return errCode;
		}

		/**
		 * <p>
		 * Description:errCode<br />
		 * </p>
		 * @author CoCo
		 * @version 0.1 2013-2-27
		 */
		public void setErrCode(String errCode) {
			this.errCode = errCode;
		}
}
