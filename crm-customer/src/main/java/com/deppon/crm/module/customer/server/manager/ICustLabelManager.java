package com.deppon.crm.module.customer.server.manager;

import java.util.List;

import com.deppon.crm.module.customer.shared.domain.CustLabel;
import com.deppon.crm.module.customer.shared.domain.Label;
/**
 * 
 * <p>
 * Description:客户标签<br />
 * </p>
 * @title ICustLabelManager.java
 * @package com.deppon.crm.module.customer.server.manager 
 * @author 106138
 * @version 0.1 2014年4月11日
 */
public interface ICustLabelManager {
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
	public void saveCustLabel(List<CustLabel> custLabels);
	
	/**
	 * 
	 * @Title: deleteCustLabel
	 *  <p>
	 * @Description:修改客户CustLabel标签
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-13
	 * @return void
	 * @throws
	 */
	public void updateCustLabel(List<CustLabel> custLabels,String custId,String custType,String deptId);
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
	public List<Label> searchLabel(String deptId);
	
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
	public String saveLabel(Label label);
	
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
	 * @Title: deleteCustLabel
	 *  <p>
	 * @Description: 删除客户custLabel标签
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-5-3
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
