package com.deppon.crm.module.customer.server.utils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.opensymphony.workflow.StoreException;
import com.opensymphony.workflow.WorkflowContext;
import com.opensymphony.workflow.spi.Step;
import com.opensymphony.workflow.spi.WorkflowEntry;
import com.opensymphony.workflow.spi.WorkflowStore;

/**
 * 
 * <p>
 * Description:工作流工具类<br />
 * </p>
 * 
 * @title WorkFlowUtil.java
 * @package com.deppon.crm.module.customer.server.utils
 * @author 106138
 * @version 0.1 2014年4月11日
 */
public class WorkFlowUtil {
	/**
	 * 
	 * <p>
	 * Description:获得用户<br />
	 * </p>
	 * 
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param transientVars
	 * @param args
	 * @return
	 * @throws StoreException
	 *             String
	 */
	@SuppressWarnings("rawtypes")
	public static String getOwner(Map transientVars, Map args) throws StoreException {
		// 获得entry
		WorkflowEntry entry = (WorkflowEntry) transientVars.get("entry");
		// 获得store
		WorkflowStore store = (WorkflowStore) transientVars.get("store");
		// 获得step
		String stepIdVal = (String) args.get("stepId");
		// 判断是否为空
		Assert.notNull(entry, "transientVars must have WorkflowContext");
		// 判断是否我空
		Assert.notNull(store, "transientVars must have WorkflowEntry");
		// setstepId=0
		try {
			int stepId = 0;
			// 如果不为空
			if (stepIdVal != null) {
				// id为stepIdVal转换后的id
				stepId = Integer.parseInt(stepIdVal);
			}
			// 当期步骤为entry的id
			List currentSteps = store.findCurrentSteps(entry.getId());
			// setstepId=0
			if (stepId == 0) {
				// 遍历
				for (Iterator iterator = currentSteps.iterator(); iterator.hasNext();) {
					// 获得step对象
					Step step = (Step) iterator.next();
					// 返回
					if (step.getOwner() != null) {
						return step.getOwner();
					}
				}
				// 不为空
			} else {
				// 遍历
				for (Iterator iterator = currentSteps.iterator(); iterator.hasNext();) {
					// 获得step对象
					Step step = (Step) iterator.next();
					// **
					if (stepId == step.getStepId()) {
						if (step.getOwner() != null) {
							return step.getOwner();
						}
					}
				}
			}
		} catch (Exception ex) {
		}
		return null;
	}

}
