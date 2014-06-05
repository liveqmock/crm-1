 package com.deppon.crm.module.keycustomer.server.manager;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.deppon.crm.module.customer.shared.domain.Member;
import com.deppon.crm.module.keycustomer.shared.domain.Constant;
import com.deppon.crm.module.keycustomer.shared.domain.KeyCustomer;
import com.deppon.crm.module.keycustomer.shared.domain.KeyCustomerSearchCondition;
import com.deppon.crm.module.keycustomer.shared.domain.KeyCustomerWorkflowInfo;
import com.deppon.crm.module.keycustomer.shared.exception.KeyCustomerException;
import com.deppon.crm.module.keycustomer.shared.exception.KeyCustomerExceptionType;
import com.deppon.foss.framework.shared.util.classes.BeanUtils;
import com.deppon.foss.framework.shared.util.string.StringUtil;

/**
 * 
 * <p>
 * Description:大客户校验类<br />
 * </p>
 * 
 * @title KeyCustomerValidator.java
 * @package com.deppon.crm.module.keycustomer.server.manager
 * @author 106138
 * @version 0.1 2014-3-6
 */ 
public class KeyCustomerValidator {
	/**
	 * 
	 * <p>
	 * Description:校验查询条件<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-7
	 * @param condition
	 * @return boolean
	 */
	public static boolean checkSearchCondition(
			KeyCustomerSearchCondition condition) {
		if (null == condition) {
			throw new KeyCustomerException(
					KeyCustomerExceptionType.NULLSEARCHCONDITION);
		}
		if(StringUtil.isNotEmpty(condition.getCustNum())||
				StringUtil.isNotEmpty(condition.getContactNum())){
			return true;
		}
		if (StringUtil.isEmpty(condition.getCustNum())
				&& StringUtil.isEmpty(condition.getBelongDept())) {
			throw new KeyCustomerException(
					KeyCustomerExceptionType.ERRORSEARCHCONDITION);
		}
		if (StringUtil.isEmpty(condition.getContactNum())
				&& StringUtil.isEmpty(condition.getBelongDept())) {
			throw new KeyCustomerException(
					KeyCustomerExceptionType.ERRORSEARCHCONDITION);
		}

		return true;
	}

	/**
	 * 
	 * <p>
	 * Description:大客户工作流表单元素判断<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-7
	 * @param info
	 *            void
	 */
	public static void checkKeyCustomerWorkflowInfo(KeyCustomerWorkflowInfo info) {
		if(null==info){
			throw new KeyCustomerException(
					KeyCustomerExceptionType.WORKFLOWTYPEISNULL);
		}
		// 判断工作流类型是否为空
		if (StringUtil.isEmpty(info.getWorkFlowType())) {
			throw new KeyCustomerException(
					KeyCustomerExceptionType.WORKFLOWTYPEISNULL);
		}
		// 校验是否大客户是否为空
		if (null==info.getIsSpecialKeyCustomer()) {
			throw new KeyCustomerException(
					KeyCustomerExceptionType.SPECIALISNULL);
		}
		// 校验填写的表单严肃是否为空
		if (StringUtil.isEmpty(info.getApplicationReason()))
				 {
			throw new KeyCustomerException(KeyCustomerExceptionType.INFOISNULl);
		}
	}

	/**
	 * 
	 * <p>
	 * Description:当前操作人的职位是否归属部门经理<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014-3-7
	 * @param deptId
	 * @param id
	 *            void
	 */
	public static void checkIsBelongDept(String deptId, String id) {
		// 如果归属部门id与当前登录部门id不一样 无法进行申请
		if (!deptId.equals(id)) {
			throw new KeyCustomerException(
					KeyCustomerExceptionType.NOTDEPTBELONG);
		}
	}
/**
 * 
 * <p>
 * Description:校验是否存在审批中的大客户工作流<br />
 * </p>
 * @author 106138
 * @version 0.1 2014-3-7
 * @param infos
 * void
 */
	public static void checkCustWorkflowINfoStatus(
			List<KeyCustomerWorkflowInfo> infos) {
		//循环遍历，
		for (KeyCustomerWorkflowInfo info : infos) {
			//如果存在审批中的工作流，则跑出异常
			if(info.getStatus().equals(Constant.WORKFLOW_STATUS_IN_APPROVE)){
				throw new KeyCustomerException(
						KeyCustomerExceptionType.WORKFLOW__IN_APPROVE);
				
			}
		}
	}
	/**
	 * 
	 * <p>
	 * Description:校验该客户是否已经是大客户了<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014-3-7
	 * @param member
	 * void
	 * @param status 
	 */
	public static void checkIsKeyCustomer(Member member, boolean status) {
		//如果是否大客户为真 
		if(status){
			if(null!=member&&member.getIsKeyCustomer()){
				throw new KeyCustomerException(KeyCustomerExceptionType.NOW_IS_KEYCUSTOMER);
			}
		}else{
			if(null!=member&&!member.getIsKeyCustomer()){
				throw new KeyCustomerException(KeyCustomerExceptionType.NOW_IS_NOTKEYCUSTOMER);
			}
		}
		
	}
	/**
	 * 
	 * <p>
	 * Description:校验当前客户是否存在于对应的大客户列表中，若存在跑出异常<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014-3-7
	 * @param keyCustomers
	 * void
	 */
	public static void checkISInKeyCustomerList(List<KeyCustomer> keyCustomers) {
		//如果list获得的第一个元素不为空，则证明存在于大客户列表中
		if(CollectionUtils.isNotEmpty(keyCustomers)){
			throw new KeyCustomerException(KeyCustomerExceptionType.NOW_IN_KEYCUSTOMERLIST);
			
		}
	}

}
