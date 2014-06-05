package com.deppon.crm.module.customer.server.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deppon.crm.module.customer.server.dao.ICustLabelDao;
import com.deppon.crm.module.customer.shared.domain.CustLabel;
import com.deppon.crm.module.customer.shared.domain.Label;
import com.deppon.foss.framework.server.components.dataaccess.ibatis.iBatis3DaoImpl;

public class CustLabelDao extends iBatis3DaoImpl implements ICustLabelDao{
	private static final String NAMESPACE_CUSTLABEL = "com.deppon.crm.module.customer.shared.domain.CustLabel.";
	//通过部门id查询标签
	private static final String QUERY_LABEL_BY_DEPTID = "queryLabelByDeptId";
	//通过标签Id删除标签
	private static final String DELETE_LABEL_BY_ID = "deleteLabelById";
	//通过标签Id查询标签
	private static final String QUERY_LABEL_BY_ID = "queryLabelById";
	//保存标签
	private static final String SAVE_LABEL = "saveLabel";
	//保存潜客客户标签
	private static final String SAVE_CUSTLABEL = "saveCustLabel";
	//删除潜客客户标签
	private static final String DELETE_CUSTLABEL = "deleteCustLabel";
	//通过客户ID和客户类型查询custLabel标签
	private static final String QUERY_CUSTLABEL = "queryCustLabel";
	//通过LabelId删除custLabel标签
	private static final String DELETE_CUSTLABEL_BYLABELID = "deleteCustLabelByLabelId";
	//通过LabelId修改custLabel标签
	private static final String UPDATE_LABEL_BY_ID = "updateLabelById";
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
	@SuppressWarnings("unchecked")
	@Override
	public List<Label> queryLabelByDeptId(String deptId) {
		return this.getSqlSession().selectList(NAMESPACE_CUSTLABEL+QUERY_LABEL_BY_DEPTID, deptId);
	}
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
	@Override
	public void addLabel(Label label) {
		this.getSqlSession().insert(NAMESPACE_CUSTLABEL+SAVE_LABEL, label);
	}
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
	@Override
	public void deleteLabel(String labelId) {
		this.getSqlSession().delete(NAMESPACE_CUSTLABEL+DELETE_LABEL_BY_ID, labelId);
	}
	/**
	 * 
	 * @Title: saveCustLabel
	 *  <p>
	 * @Description: 保存客户CUSTLABEL标签
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-11
	 * @return void
	 * @throws
	 */
	@Override
	public void saveCustLabel(CustLabel custLabel) {
		this.getSqlSession().insert(NAMESPACE_CUSTLABEL+SAVE_CUSTLABEL, custLabel);
	}
	/**
	 * 
	 * @Title: deletePotentialLabel
	 *  <p>
	 * @Description:删除客户CUSTLABEL标签
	 * </p>
	 * @author 唐亮
	 * @version 0.1 2013-4-11
	 * @return void
	 * @throws
	 */
	@Override
	public void deleteCustLabel(String custId,String custType) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("custId", custId);
		map.put("custType", custType);
		this.getSqlSession().delete(NAMESPACE_CUSTLABEL+DELETE_CUSTLABEL, map);
	}
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
	@SuppressWarnings("unchecked")
	@Override
	public List<CustLabel> queryCustLabel(String custId, String custType) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("custId", custId);
		map.put("custType", custType);
		return this.getSqlSession().selectList(NAMESPACE_CUSTLABEL+QUERY_CUSTLABEL, map);
	}
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
	@Override
	public void deleteCustLabelById(String labelId) {
		this.getSqlSession().delete(NAMESPACE_CUSTLABEL+DELETE_CUSTLABEL_BYLABELID, labelId);
	}
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
	@Override
	public Label queryLabelById(String labelId) {
		return  (Label) this.getSqlSession().selectOne(NAMESPACE_CUSTLABEL+QUERY_LABEL_BY_ID, labelId);
	}
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
	@Override
	public void updateLabelById(Label label) {
		this.getSqlSession().update(NAMESPACE_CUSTLABEL+UPDATE_LABEL_BY_ID,label);
	}
}
