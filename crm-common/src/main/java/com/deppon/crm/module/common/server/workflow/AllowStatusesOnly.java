package com.deppon.crm.module.common.server.workflow;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.util.TextUtils;
import com.opensymphony.workflow.Condition;
import com.opensymphony.workflow.StoreException;
import com.opensymphony.workflow.spi.Step;
import com.opensymphony.workflow.spi.WorkflowEntry;
import com.opensymphony.workflow.spi.WorkflowStore;
import com.opensymphony.workflow.util.StatusCondition;

public class AllowStatusesOnly implements Condition {

	public boolean passesCondition(Map transientVars, Map args, PropertySet ps)
			throws StoreException {
		String statusesStr = (String) args.get("status");
		if (statusesStr != null && !"".equals(statusesStr)) {
			String[] statuses = statusesStr.split(",");
			for (String status : statuses) {
				if (hasStatus(transientVars, args, status)){
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean hasStatus(Map transientVars, Map args, String status) throws StoreException{
        int stepId = 0;
        Object stepIdVal = args.get("stepId");

        if (stepIdVal != null) {
            try {
                stepId = Integer.parseInt(stepIdVal.toString());
            } catch (Exception ex) {
            }
        }

        WorkflowEntry entry = (WorkflowEntry) transientVars.get("entry");
        WorkflowStore store = (WorkflowStore) transientVars.get("store");
        List currentSteps = store.findCurrentSteps(entry.getId());

        if (stepId == 0) {
            for (Iterator iterator = currentSteps.iterator();
                    iterator.hasNext();) {
                Step step = (Step) iterator.next();

                if (status.equals(step.getStatus())) {
                    return true;
                }
            }
        } else {
            for (Iterator iterator = currentSteps.iterator();
                    iterator.hasNext();) {
                Step step = (Step) iterator.next();

                if (stepId == step.getStepId()) {
                    if (status.equals(step.getStatus())) {
                        return true;
                    }
                }
            }
        }
        return false;
	}
}
