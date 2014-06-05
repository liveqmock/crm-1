package com.deppon.crm.module.customer.server.service.impl;

import java.util.List;


import com.deppon.crm.module.customer.server.dao.ICustLabelDao;
import com.deppon.crm.module.customer.server.service.ICustLabelService;
import com.deppon.crm.module.customer.shared.domain.CustLabel;
import com.deppon.crm.module.customer.shared.domain.Label;
/**
 * 
 * <p>
 * 客户标签service
 * </p>
 * @title ICustLabelService.java
 * @package com.deppon.crm.module.customer.server.service
 * @author 唐亮
 * @version 0.1 2013-4-13
 */
public class CustLabelService implements ICustLabelService{
	private ICustLabelDao custLabelDao;
	public void setCustLabelDao(ICustLabelDao custLabelDao) {
		this.custLabelDao = custLabelDao;
	}
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
	@Override
	public List<Label> searchLabelByDeptId(String deptId) {
		return custLabelDao.queryLabelByDeptId(deptId);
	}
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
	@Override
	public void saveLabel(Label label) {
		custLabelDao.addLabel(label);
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
		//删除本部门客户标签
		custLabelDao.deleteLabel(labelId);
		//删除客户用到的相应custLabel标签
		custLabelDao.deleteCustLabelById(labelId);
		
	}
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
	@Override
	public void saveCustLabel(List<CustLabel> custLabels) {
		for(CustLabel custLabel:custLabels){
			custLabelDao.saveCustLabel(custLabel);
		}
	}
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
	@Override
	public void updateCustLabel(List<CustLabel> custLabels) {
		//先将原来的标签删除
		custLabelDao.deleteCustLabel(custLabels.get(0).getCustId(),
				custLabels.get(0).getCustType());
		//将传入的标签保存	
		for(CustLabel custLabel:custLabels){
			
			custLabelDao.saveCustLabel(custLabel);
		}
	}
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
	@Override
	public List<CustLabel> searchCustLabel(String custId, String custType) {
		return custLabelDao.queryCustLabel(custId, custType);
	}
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
	@Override
	public void deleteCustLabel(String custId, String custType) {
		custLabelDao.deleteCustLabel(custId, custType);
	}
	
	/* (非 Javadoc)
	* <p>Title: modifyLabel</p>
	* <p>Description: </p>
	* @param label
	* @see com.deppon.crm.module.customer.server.service.ICustLabelService#modifyLabel(com.deppon.crm.module.customer.shared.domain.Label)
	*/
	@Override
	public void modifyLabel(Label label) {
		custLabelDao.updateLabelById(label);
	}
}
