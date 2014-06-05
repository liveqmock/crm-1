package com.deppon.crm.module.customer.workflow;

import java.util.Map;

import com.deppon.crm.module.customer.server.manager.ICustLabelManager;
import com.deppon.crm.module.customer.shared.domain.Constant;
import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.FunctionProvider;
import com.opensymphony.workflow.WorkflowException;
/**
 * 
 * <p>
 * 删除客户标签（当固定客户归属部门表更工作流同意并审批完成以后，删除掉该客户原有标签）
 * </p>
 * @title deleteCustLabelsFunction.java
 * @package com.deppon.crm.module.customer.workflow
 * @author 唐亮
 * @version 0.1 2013-5-3
 */
public class DeleteCustLabelsFunction implements FunctionProvider{
	//客户标签manager
	private ICustLabelManager custLabelManager;
	//设置客户标签manager
	public void setCustLabelManager(ICustLabelManager custLabelManager) {
		this.custLabelManager = custLabelManager;
	}
	
	/**
	 * 
	 * <p>
	 * Description:删除客户标签<br />
	 * </p>
	 * @author pgj
	 * @version 0.1 2014-4-11
	 * @param transientVars
	 * @param args
	 * @param ps
	 * @return
	 *  
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Map transientVars, Map args, PropertySet ps)
			throws WorkflowException {
		//获得id
		String appId = ps.getString("appId");
		//调用删除方法进行删除
		custLabelManager.deleteCustLabel(appId, Constant.MEMBERTYPE_IN_CUSTLABEL);
	}
	
}
