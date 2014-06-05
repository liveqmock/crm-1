package com.deppon.crm.module.customer.server.dao;

import java.util.List;

import com.deppon.crm.module.customer.shared.domain.CustLabel;
import com.deppon.crm.module.customer.shared.domain.Label;
/**
 * 
 * <p>
 * 客户标签manager
 * </p>
 * @title ICustLabelDao.java
 * @package com.deppon.crm.module.customer.server.dao
 * @author 唐亮
 * @version 0.1 2013-4-13
 */
public interface ICustLabelDao {
	/**
	 * 
	 * @Title: queryCustLabelByDeptId
	 *  <p>
	 * @Description: 通过部门Id查询客户标签
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-11
	 * @return CustLabel
	 * @throws
	 */
	public List<Label> queryLabelByDeptId(String deptId);
	
	/**
	 * 
	 * @Title: saveCustLabel
	 *  <p>
	 * @Description: 保存客户标签
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-11
	 * @return void
	 * @throws
	 */
	public void addLabel(Label label);
	
	/**
	 * 
	 * @Title: deleteCustLabel
	 *  <p>
	 * @Description: 通过标签Id删除客户标签
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-11
	 * @return void
	 * @throws
	 */
	public void deleteLabel(String labelId);
	

	/**
	 * 
	 * @Title: saveCustLabel
	 *  <p>
	 * @Description: 保存客户custLabel标签
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-11
	 * @return void
	 * @throws
	 */
	public void saveCustLabel(CustLabel custLabel);
	
	/**
	 * 
	 * @Title: deletePotentialLabel
	 *  <p>
	 * @Description:删除客户custLabel标签
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-11
	 * @return void
	 * @throws
	 */
	public void deleteCustLabel(String custId,String custType);
	
	/**
	 * 
	 * @Title: queryCustLabel
	 *  <p>
	 * @Description: 根据客户Id和客户类型查询客户custLabel标签
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-13
	 * @return void
	 * @throws
	 */
	public List<CustLabel> queryCustLabel(String custId,String custType);
	
	/**
	 * 
	 * @Title: deleteMemberCustLabel
	 *  <p>
	 * @Description: 根据标签Id删除固定客户、潜客、散客标签
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-16
	 * @return void
	 * @throws
	 */
	public void deleteCustLabelById(String labelId);
	
	/**
	 * 
	 * @Title: queryLabelById
	 *  <p>
	 * @Description: 通过LabelId查询对应的label标签
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-16
	 * @return void
	 * @throws
	 */
	public Label queryLabelById(String labelId);
	/**
	 * 
	 * @Title: updateLabelById
	 *  <p>
	 * @Description: 修改客户标签基础资料
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-7-3
	 * @return void
	 * @throws
	 */
	public void updateLabelById(Label label);
}
