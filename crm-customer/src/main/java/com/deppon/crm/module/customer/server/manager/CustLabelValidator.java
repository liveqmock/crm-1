package com.deppon.crm.module.customer.server.manager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.deppon.crm.module.customer.server.utils.ValidateUtil;
import com.deppon.crm.module.customer.shared.domain.CustLabel;
import com.deppon.crm.module.customer.shared.domain.Label;
import com.deppon.crm.module.customer.shared.exception.MemberException;
import com.deppon.crm.module.customer.shared.exception.MemberExceptionType;
/**
 * 
 * <p>
 * 客户标签校验类
 * </p>
 * @title CustLabelValidator.java
 * @package com.deppon.crm.module.customer.server.manager
 * @author 唐亮
 * @version 0.1 2013-4-17
 */
public class CustLabelValidator {
	/**
	 * 
	 * @Title: validateCustLabel
	 *  <p>
	 * @Description: 校验客户CustLabel是否合法
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-15
	 * @return boolean
	 * @throws
	 */
	public boolean validateCustLabel(List<CustLabel> custLabels){
		//校验传入的标签个数，超过十个则抛出异常提示
		if (custLabels.size()>10) {
			throw new MemberException(MemberExceptionType.Label_OutOf_Range_Error);
		}
		Set<String> set = new HashSet<String>();
		for(CustLabel custLabel:custLabels){
			//客户ID为空，则抛出遗产提示
			if (StringUtils.isEmpty(custLabel.getCustId())) {
				throw new MemberException(MemberExceptionType.CustId_Miss_Error);
			//客户类型为空，则抛出异常提示
			}else if(StringUtils.isEmpty(custLabel.getCustType())){
				throw new MemberException(MemberExceptionType.CustType_Miss_Error);
			//客户Label标签为空，则抛出异常提示
			}else if(ValidateUtil.objectIsEmpty(custLabel.getLabel())){
				throw new MemberException(MemberExceptionType.Label_Miss_Error);
			//客户标签内容ID为空，则抛出异常提示
			}else if(StringUtils.isEmpty(custLabel.getLabel().getId())){
				throw new MemberException(MemberExceptionType.LabelId_Miss_Error);
			//部门ID为空，则抛出异常提示
			}else if(StringUtils.isEmpty(custLabel.getLabel().getDeptId())){
				throw new MemberException(MemberExceptionType.LabelDeptId_Miss_Error);
			//Label标签的LabelName为空，则抛出异常提示
			}else if(StringUtils.isEmpty(custLabel.getLabel().getLabelName())){
				throw new MemberException(MemberExceptionType.LabelName_Miss_Error);
			}
			set.add(custLabel.getLabel().getLabelName());
		}
		//如果出现重复的Label名字，则抛出异常提示信息
		if (set.size()!=custLabels.size()) {
			throw new MemberException(MemberExceptionType.LabelName_Exist_Error);
		}
		return true;
	}
	/**
	 * 
	 * @Title: validateLabel
	 *  <p>
	 * @Description: 校验传入的客户Label标签是否合法
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-15
	 * @return boolean
	 * @throws
	 */
	public boolean validateLabelBasicData(Label label){
		if (ValidateUtil.objectIsEmpty(label)) {
		//传入的客户label标签为空，则抛出异常提示
			throw new MemberException(MemberExceptionType.Label_Miss_Error);
		}else if (StringUtils.isEmpty(label.getDeptId())) {
		//传入的部门ID为空，则抛出异常提示
			throw new MemberException(MemberExceptionType.LabelDeptId_Miss_Error);
		//传入的标签名字为空或者超过六个字长度，则抛出异常提示 
		}else if(StringUtils.isEmpty(label.getLabelName()
				)|| label.getLabelName().length() > 6){
			throw new MemberException(MemberExceptionType.LabelName_Miss_Error);
		}
		return true;
	}
	/**
	 * 
	 * @Title: compareLabel
	 *  <p>
	 * @Description: 将传入的Label标签和已存在的部门标签做对比
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-17
	 * @return boolean
	 * @throws
	 */
	public boolean compareLabel(Label label,List<Label> labels){
		//如果本部门已经存在的标签已达到10个，则抛出提示，不让新增
		if (labels != null && labels.size()>=10) {
			throw new MemberException(MemberExceptionType.DeptLabel_Full_Error);
		//如果查询到的结果不为空,且已存在标签个数为10个以下，则继续执行以下操作
		}else if(labels != null && labels.size()>0 && labels.size() < 10) {
			//遍历检查已存在的客户标签
			for(Label labelData : labels){
				//如果该标签名已经存在，则抛出异常提示
				if (labelData.getLabelName().equals(label.getLabelName())) {
					throw new MemberException(MemberExceptionType.LabelName_Exist_Error);
				}
			}
		}
		return true;
	}
	/**
	 * 
	 * @Title: checkLabelIsExist
	 *  <p>
	 * @Description: 校验传过来的数据中label是否在基础资料中
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-6-18
	 * @return boolean
	 * @throws
	 */
	public boolean checkLabelIsExist(List<Label> labelList,List<CustLabel> custLabels){
		List<String> stringList = new ArrayList<String>();
		for(int i = 0;i < custLabels.size();i++){
			stringList.add(custLabels.get(i).getLabel().getLabelName()+"、");
			for (int j = 0; j < labelList.size(); j++) {
				if (custLabels.get(i).getLabel().getId().equals(labelList.get(j).getId())) {
					stringList.remove(stringList.size()-1);
				}
			}
		}
		if (stringList.size() > 0) {
			String noticeString = "";
			for(int index = 0;index < stringList.size();index++){
				 noticeString += stringList.get(index);  
			}
			noticeString = noticeString.substring(0, noticeString.length()-1);
			throw new MemberException(MemberExceptionType.Label_source_Error, noticeString);
		}
		return true;
	}
}
