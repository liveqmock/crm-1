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
 * Description:运输类型<br />
 * </p>
 * @title RecompenseStatusOnly.java
 * @package com.deppon.crm.module.recompense.server.manager.workflow.condition 
 * @author roy
 * @version 0.1 2013-1-21
 */
public class TransTypeOnly implements Condition {
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
	 * 
	 * <p>
	 * Description:passesCondition<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-22
	 * @param transientVars
	 * @param args
	 * @param ps
	 * @return
	 * @throws WorkflowException
	 *
	 */
	@Override
	public boolean passesCondition(Map transientVars, Map args, PropertySet ps)
			throws WorkflowException {
		RecompenseApplication app = (RecompenseApplication) transientVars
				.get(Constants.RECOMPENSE_APPLICATION);
			String appId = ps.getString(Constants.RECOMPENSE_APPLICATION_ID);
			app = recompenseService.getRecompenseApplicationById(appId);
		
		// 取得工作流定义指定的理赔状态
		String transType = (String) args.get("transType");
		// 实际的理赔状态
		String apptransType= app.getWaybill().getTransType();
		// 该节点只接受指定的理赔状态的处理
		if(transType.equals(apptransType)){
			return false;
		}else{
			
			return true;
		}
	}
}