package com.deppon.crm.module.customer.server.manager.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.customer.server.manager.CustLabelValidator;
import com.deppon.crm.module.customer.server.manager.ICustLabelManager;
import com.deppon.crm.module.customer.server.service.ICustLabelService;
import com.deppon.crm.module.customer.server.utils.ContextUtil;
import com.deppon.crm.module.customer.server.utils.ScatterCustomerUtil;
import com.deppon.crm.module.customer.shared.domain.CustLabel;
import com.deppon.crm.module.customer.shared.domain.Label;
import com.deppon.crm.module.customer.shared.exception.MemberException;
import com.deppon.crm.module.customer.shared.exception.MemberExceptionType;
/**
 * 客户标签manager
 * @title CustLabelManager.java
 * @package com.deppon.crm.module.customer.server.manager.impl
 * @author 唐亮
 * @version 0.1 
 * @date 2013-4-13
 * @updateRecord 
 * 			R1：修改时间：2013-4-15；     修改人：唐亮；   修改描述：添加校验部门标签是否达到饱和的校验 
 */
public class CustLabelManager implements ICustLabelManager{
	private ICustLabelService custLabelService;
	private CustLabelValidator custLabelValidator;
	
	/**
	 * 
	 * Description:custLabelService<br />
	 * @author 唐亮
	 * @version 0.1 2013-7-8
	 */
	public void setCustLabelService(ICustLabelService custLabelService) {
		this.custLabelService = custLabelService;
	}

	/**
	 * 
	 * Description:custLabelValidator<br />
	 * @author 唐亮
	 * @version 0.1 2013-7-8
	 */
	public void setCustLabelValidator(CustLabelValidator custLabelValidator) {
		this.custLabelValidator = custLabelValidator;
	}

	/**
	 * 
	 * @Title: saveCustLabel
	 *  <p>
	 * @Description: 保存客户CustLabel标签
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-13
	 * @return void
	 * @throws
	 */
	@Override
	public void saveCustLabel(List<CustLabel> custLabels) {
		//当客户标签不为空的时候，执行保存操作
		if (CollectionUtils.isNotEmpty(custLabels)) {
			//校验传入的custLabels是否合法
			custLabelValidator.validateCustLabel(custLabels);
			//校验传入的客户标签基础资料是否和本部门的基础资料一致（防止并发问题）
			List<Label> labelList = 
					custLabelService.searchLabelByDeptId(custLabels.get(0).getLabel().getDeptId());
			custLabelValidator.checkLabelIsExist(labelList, custLabels);
			custLabelService.saveCustLabel(custLabels);
		}
	}
	/**
	 * 
	 * @Title: updateCustLabel
	 *  <p>
	 * @Description:修改客户CustLabel标签
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-13
	 * @return void
	 * @throws
	 */
	@Override
	public void updateCustLabel(List<CustLabel> custLabels,String custId,String custType,String deptId) {
		//判断客户id不能为空，
		if (StringUtils.isEmpty(custId)) {
			throw new MemberException(MemberExceptionType.CustId_Miss_Error);
		//客户类型不能为空
		}else if(StringUtils.isEmpty(custType)){
			throw new MemberException(MemberExceptionType.CustType_Miss_Error);
		//部门ID不能为空
		}else if(StringUtils.isEmpty(deptId)){
			throw new MemberException(MemberExceptionType.LabelDeptId_Miss_Error);
		//当传入的客户标签为空时，则判定为删除所有标签
		}else if (custLabels == null) {
			custLabelService.deleteCustLabel(custId, custType);
		//当传入的标签不为空的时，则执行修改操作
		}else if (CollectionUtils.isNotEmpty(custLabels)) {
			List<CustLabel> cusLabelList 
				= ScatterCustomerUtil.setCustLabelData(custLabels, custId, custType, deptId);
			custLabelValidator.validateCustLabel(cusLabelList);
			//校验传入的客户标签基础资料是否和本部门的基础资料一致（防止并发问题）
			List<Label> labelList = 
					custLabelService.searchLabelByDeptId(custLabels.get(0).getLabel().getDeptId());
			custLabelValidator.checkLabelIsExist(labelList, custLabels);
			custLabelService.updateCustLabel(cusLabelList);
		}
	}
	/**
	 * 
	 * @Title: searchLabel
	 *  <p>
	 * @Description: 查询本部门的Label
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-13
	 * @return void
	 * @throws
	 */
	@Override
	public List<Label> searchLabel(String deptId) {
		//如果传入的部门ID为空，则抛出异常提示
		if (StringUtils.isEmpty(deptId)) {
			throw new MemberException(MemberExceptionType.LabelDeptId_Miss_Error);
		}
		return custLabelService.searchLabelByDeptId(deptId);
	}
	
	/**
	 * @Title: saveLabel
	 * @Description: 保存客户Label标签
	 * @author 唐亮
	 * @date 2013-4-13
	 * @param label 客户标签基础资料
	 * @return string
	 * @updateRecord 
	 * 			R1：修改时间：2013-4-15；     修改人：唐亮；   修改描述：添加校验部门标签是否达到饱和的校验 
	 * @version 0.2
	 * @throws
	 */
	@Override
	public String saveLabel(Label label) {
		//校验传入的客户Label标签是否合法
		custLabelValidator.validateLabelBasicData(label);
		Date date = new Date();
		//拿到该部门已经存在的客户标签
		List<Label> labels = searchLabel(label.getDeptId());
		//将传入的Label和已存在的部门Label标签做对比
		//主要校验部门标签是否已经达到十个、标签名是否有重复
		custLabelValidator.compareLabel(label, labels);
		//设置创建时间
		label.setCreateDate(date);
		//设置创建人
		label.setCreateUser(ContextUtil.getCreateOrModifyUserId());
		//设置最后修改时间
		label.setModifyDate(date);
		//设置最后修改人
		label.setModifyUser(ContextUtil.getCreateOrModifyUserId());
		//执行修改方法
		custLabelService.saveLabel(label);
		//返回标签ID
		return label.getId();
	}
	
	/**
	 * 
	 * @Title: deleteLabel
	 *  <p>
	 * @Description: 删除客户Label标签
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-13
	 * @return void
	 * @throws
	 */
	@Override
	public void deleteLabel(String labelId) {
		if (StringUtils.isEmpty(labelId)) {
			throw new MemberException(MemberExceptionType.LabelId_Miss_Error);
		}
		//根据labelId删除部门标签
		custLabelService.deleteLabel(labelId);
	}

	/**
	 * 
	 * @Title: deleteCustLabel
	 *  <p>
	 * @Description: 删除客户custLabel标签
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-5-3
	 * @return void
	 * @throws
	 */
	@Override
	public void deleteCustLabel(String custId,String custType) {
		if (custId == null) {
			throw new MemberException(MemberExceptionType.CustId_Miss_Error);
		}
		if (custType == null) {
			throw new MemberException(MemberExceptionType.CustType_Miss_Error);
		}
		custLabelService.deleteCustLabel(custId, custType);
	}
	/**
	 * 
	 * @Title: modifyLabel
	 *  <p>
	 * @Description: 修改客户标签基础资料
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-7-3
	 * @return void
	 * @throws
	 */
	@Override
	public void modifyLabel(Label label) {
		//校验传入的Label是否合法
		custLabelValidator.validateLabelBasicData(label);
		if (label.getId() == null) {
			throw new MemberException(MemberExceptionType.Label_Id_Error);
		}
		label.setModifyDate(new Date());
		label.setModifyUser(ContextUtil.getCreateOrModifyUserId());
		custLabelService.modifyLabel(label);
	}
}
