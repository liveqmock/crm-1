package com.deppon.crm.module.customer.workflow;

import java.util.Map;

import com.deppon.crm.module.customer.server.manager.IMemberManager;
import com.deppon.crm.module.customer.server.utils.ContextUtil;
import com.deppon.crm.module.customer.server.utils.RightsHelper;
import com.deppon.crm.module.customer.shared.domain.ExamineRecord;
import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.FunctionProvider;
import com.opensymphony.workflow.WorkflowException;
import com.opensymphony.workflow.spi.WorkflowEntry;
/**
 * 
 * <p>
 * Description:插入审批记录<br />
 * </p>
 * @title WriteExaminRecordFunction.java
 * @package com.deppon.crm.module.customer.workflow 
 * @author 106138
 * @version 0.1 2014年4月11日
 */
public class WriteExaminRecordFunction implements FunctionProvider{
	/**
	 * 会员manager
	 */
	private IMemberManager memberManager;
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author 106138
	 * @version 0.1 2014年4月11日
	 * @param memberManager
	 * void
	 */
	public void setMemberManager(IMemberManager memberManager) {
		this.memberManager = memberManager;
	}

	/**
	 * 
	 * <p>
	 * Description:保存审批意见<br />
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
		String rigths = (String) args.get("rights");
		String rigthsMessage = RightsHelper.getRightsByKey(rigths);
		String opinion = (String) transientVars.get("opinion");
		
		String resultStr = (String) args.get("examineResult");
		
		boolean result = Boolean.valueOf(resultStr);
		
		WorkflowEntry entry = (WorkflowEntry) transientVars.get("entry");
	    long wkid = entry.getId();//当前工作流ID

	    String disposeUserId = ContextUtil.getCurrentUserEmpId();
	    
		ExamineRecord record = new ExamineRecord();
		record.setRights(rigthsMessage);
		record.setResult(result);
		record.setOpinion(opinion);
		record.setWorkFlowId(wkid);
		record.setDisposeUserId(disposeUserId);
		//TODO 改成User对象
		memberManager.saveExamineRecord(record);
	}
	
}
