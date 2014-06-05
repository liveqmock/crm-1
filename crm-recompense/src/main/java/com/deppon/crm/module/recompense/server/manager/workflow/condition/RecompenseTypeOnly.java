package com.deppon.crm.module.recompense.server.manager.workflow.condition;

import java.util.Map;

import com.deppon.crm.module.recompense.server.service.RecompenseService;
import com.deppon.crm.module.recompense.server.utils.Constants;
import com.deppon.crm.module.recompense.shared.domain.RecompenseApplication;
import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.Condition;
import com.opensymphony.workflow.WorkflowException;
/**
 * 
 * <p>
 * Description:理赔类型<br />
 * </p>
 * @title RecompenseTypeOnly.java
 * @package com.deppon.crm.module.recompense.server.manager.workflow.condition 
 * @author roy
 * @version 0.1 2013-1-21
 */
public class RecompenseTypeOnly implements Condition {
	//理赔service
	private RecompenseService recompenseService;
	/**
	 * 
	 * <p>
	 * Description:getRecompenseService<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @return
	 * RecompenseService
	 */
	public RecompenseService getRecompenseService() {
		return recompenseService;
	}
	/**
	 * 
	 * <p>
	 * Description:setRecompenseService<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param recompenseService
	 * void
	 */
	public void setRecompenseService(RecompenseService recompenseService) {
		this.recompenseService = recompenseService;
	}

	/**
	 * @Title: passesCondition
	 * @Description: 判断实际理赔的理赔方式和工作流节点的是否相同
	 * @param transientVars
	 *            工作流输入输出参数
	 * @param args
	 *            工作流输入参数
	 * @param ps
	 *            工作流持久化参数
	 * @return
	 * @throws WorkflowException
	 *             工作流异常
	 */
	@Override
	public boolean passesCondition(Map transientVars, Map args, PropertySet ps)
			throws WorkflowException {
		RecompenseApplication app = (RecompenseApplication) transientVars
				.get(Constants.RECOMPENSE_APPLICATION);
		if (app == null || app.getRecompenseMethod() == null) {
			String appId = ps.getString(Constants.RECOMPENSE_APPLICATION_ID);
			app = recompenseService.getRecompenseApplicationById(appId);
		}
		// 取得工作流定义指定的理赔方式
		String typesStr = (String) args.get("recompenseType");
		// 实际的理赔方式
		String appType = (String) app.getRecompenseMethod();
		// 该节点只接受指定的理赔方式的处理
		if (typesStr != null && appType != null && !"".equals(typesStr)) {
			String[] types = typesStr.split(",");
			for (String type : types) {
				if (type.equals(appType)) {
					return true;
				}
			}
		}
		return false;

	}
}