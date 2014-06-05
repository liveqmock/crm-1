package com.deppon.crm.module.customer.server.service;

import java.util.List;

import com.deppon.crm.module.customer.shared.domain.CustLabel;
import com.deppon.crm.module.customer.shared.domain.Label;

/**
 * 
 * <p>
 * 客户标签
 * </p>
 * @title ICustLabelService.java
 * @package com.deppon.crm.module.customer.server.service
 * @author 唐亮
 * @version 0.1 2013-4-13
 */
public interface ICustLabelService {
	/**
	 * 
	 * @Title: searchLabelByDeptId
	 *  <p>
	 * @Description: 通过部门Id查询客户Label标签
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-13
	 * @return List<Label>
	 * @throws
	 */
	public List<Label> searchLabelByDeptId(String deptId);
	
	/**
	 * 
	 * @Title: saveLabel
	 *  <p>
	 * @Description: 保存客户Label标签
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-13
	 * @return void
	 * @throws
	 */
	public void saveLabel(Label label);
	
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
	public void deleteLabel(String labelId);
	
	/**
	 * 
	 * @Title: saveCustLabel
	 *  <p>
	 * @Description: 保存客户CUSTLABEL标签
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-13
	 * @return void
	 * @throws
	 */
	public void saveCustLabel(List<CustLabel> custLabels);
	
	/**
	 * 
	 * @Title: deleteCustLabel
	 *  <p>
	 * @Description: 修改客户CUSTLABEL标签
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-13
	 * @return void
	 * @throws
	 */
	public void updateCustLabel(List<CustLabel> custLabels);
	
	/**
	 * 
	 * @Title: searchCustLabel
	 *  <p>
	 * @Description: 根据客户Id和客户类型查询客户custLabel标签
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-13
	 * @return List<CustLabel>
	 * @throws
	 */
	public List<CustLabel> searchCustLabel(String custId,String custType);
	/**
	 * 
	 * @Title: deleteCustLabel
	 *  <p>
	 * @Description: 根据客户Id和客户类型删除客户CustLabel标签
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-17
	 * @return void
	 * @throws
	 */
	public void deleteCustLabel(String custId,String custType);
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
	public void modifyLabel(Label label);
}
