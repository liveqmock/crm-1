/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CustomerCallInValidateUtils.java
 * @package com.deppon.crm.module.marketing.server.utils 
 * @author SYJ
 * @version 0.1 2012-11-7
 */
package com.deppon.crm.module.marketing.server.utils;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.marketing.shared.domain.CustomerCallVO;
import com.deppon.crm.module.marketing.shared.domain.ReturnVisitOpinion;
import com.deppon.crm.module.marketing.shared.exception.CustomerCallInException;
import com.deppon.crm.module.marketing.shared.exception.CustomerCallInExceptionType;
import com.deppon.foss.framework.exception.GeneralException;

/**
 * <p>
 * 客户来电录入业务验证类<br />
 * </p>
 * 
 * @title CustomerCallInValidateUtils.java
 * @package com.deppon.crm.module.marketing.server.utils
 * @author 苏玉军
 * @version 0.1 2012-11-7
 */

public class CustomerCallInValidateUtils {
	/**
	 * 
	 * <p>
	 * 客户信息校验<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-11-8
	 * @param vo
	 *            客户信息vo
	 * @return boolean
	 */
	@SuppressWarnings("serial")
	public static boolean validateVo(CustomerCallVO vo) {
		// 客户信息不能为空
		if (vo == null) {
			//客户不存在异常
			CustomerCallInException e = new CustomerCallInException(
					CustomerCallInExceptionType.CUSTVO_ISNULL);
			//异常转换
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		// 联系人姓名不能为空
		if (StringUtils.isEmpty(vo.getCustLinkManName())) {
			//联系人不能为空
			CustomerCallInException e = new CustomerCallInException(
					CustomerCallInExceptionType.CUSTLINKMAN_NAME_ISNULL);
			//异常转换
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		// 联系人手机、联系人电话至少录入一个
		if (StringUtils.isEmpty(vo.getContactMobile())
				&& StringUtils.isEmpty(vo.getContactPhone())) {
			// 联系人手机、联系人电话至少录入一个
			CustomerCallInException e = new CustomerCallInException(
					CustomerCallInExceptionType.CUSTLINK_MOBILE_TEL_MUSTHAVEONE);
			//异常转换
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		return true;
	}

	/**
	 * 
	 * <p>
	 * 验证客户意见数据<br />
	 * </p>
	 * 
	 * @author 苏玉军
	 * @version 0.1 2012-11-8
	 * @param optionList
	 *            客户意见list
	 * @return boolean
	 */
	@SuppressWarnings("serial")
	public static boolean validateCustOptions(
			List<ReturnVisitOpinion> optionList) {
		// 客户意见是否有数据
		if (optionList == null || optionList.size() < 1) {
			// 客户意见为空
			CustomerCallInException e = new CustomerCallInException(
					CustomerCallInExceptionType.CUST_OPTION_ISNULL);
			//异常转换
			throw new GeneralException(e.getErrorCode(), e.getMessage(), e,
					new Object[] {}) {
			};
		}
		// 验证客户需求中的信息是否完整
		for (ReturnVisitOpinion option : optionList) {
			if (StringUtils.isEmpty(option.getOpinionType())
					|| StringUtils.isEmpty(option.getProblem())
					|| StringUtils.isEmpty(option.getSolution())) {
				//信息不完整
				CustomerCallInException e = new CustomerCallInException(
						CustomerCallInExceptionType.CUST_OPTION_INCOMPLETE);
				//异常转换
				throw new GeneralException(e.getErrorCode(),e.getMessage(),e,new Object[]{}) {
				};
			}
		}
		return true;
	}
	
	/**
	 * 
	 * <p>
	 * 验证客户ID和类型
	 * </p>
	 * @author 苏玉军
	 * @version 0.1 2013-1-28
	 * @param vo
	 * @return
	 * boolean
	 */
	@SuppressWarnings("serial")
	public static boolean validateCustIdAndType(CustomerCallVO vo){
		String custId = vo.getId();
		String custType = vo.getContactType();
		//验证客户id，不存在不容许查询
		if(StringUtils.isEmpty(custId)){
			//id为空，未选择客户
			CustomerCallInException e = new CustomerCallInException(
					CustomerCallInExceptionType.CUST_MUST_SELECT);
			//异常转换
			throw new GeneralException(e.getErrorCode(),e.getMessage(),e,
					new Object[] {}) {
			};
		}
		
		//验证客户类型，为空不容许查询
		if(StringUtils.isEmpty(custType)){
			//客户类型不知
			CustomerCallInException e = new CustomerCallInException(
					CustomerCallInExceptionType.CUST_UNKNOW_TYPE);
			//异常转换
			throw new GeneralException(e.getErrorCode(),e.getMessage(),e,
					new Object[] {}) {
			};
		}
		return true;
	}
}
