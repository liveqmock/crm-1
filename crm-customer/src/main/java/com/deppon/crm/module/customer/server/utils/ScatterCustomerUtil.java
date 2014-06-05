package com.deppon.crm.module.customer.server.utils;

import java.util.Date;
import java.util.List;

import com.deppon.crm.module.customer.shared.domain.CustLabel;
import com.deppon.crm.module.customer.shared.exception.MemberException;
import com.deppon.crm.module.customer.shared.exception.MemberExceptionType;

public class ScatterCustomerUtil {
	/**
	 * 
	 * @Title: setCustLabelData
	 *  <p>
	 * @Description: 将客户CustLabel标签的值补充进去
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-15
	 * @return List<CustLabel>
	 * @throws
	 */
	public static List<CustLabel> setCustLabelData(List<CustLabel> custLabels,String custId,String custType,String deptId){
		Date date = new Date();
		
		for(int i=0;i<custLabels.size();i++){
			//校验Label标签是否为空
			if (ValidateUtil.objectIsEmpty(custLabels.get(i).getLabel())) {
				throw new MemberException(MemberExceptionType.Label_Miss_Error);
			}
			//设置CustLabel客户ID
			custLabels.get(i).setCustId(custId);
			//设置CustLabel客户类型
			custLabels.get(i).setCustType(custType);
			//设置CustLabel创建时间
			custLabels.get(i).setCreateDate(date);
			//设置CustLabel创建人
			custLabels.get(i).setCreateUser(ContextUtil.getCreateOrModifyUserId());
			//设置CustLabel修改时间
			custLabels.get(i).setModifyDate(date);
			//设置CustLabel修改人
			custLabels.get(i).setModifyUser(ContextUtil.getCreateOrModifyUserId());
			//设置Label部门ID
			custLabels.get(i).getLabel().setDeptId(deptId);
			//设置Label创建时间
			custLabels.get(i).getLabel().setCreateDate(date);
			//设置Label修改时间
			custLabels.get(i).getLabel().setModifyDate(date);
			//设置Label修改人
			custLabels.get(i).getLabel().setModifyUser(ContextUtil.getCreateOrModifyUserId());
			//设置Label创建时间
			custLabels.get(i).getLabel().setCreateUser(ContextUtil.getCreateOrModifyUserId());
		}
		return custLabels;
	}
}
