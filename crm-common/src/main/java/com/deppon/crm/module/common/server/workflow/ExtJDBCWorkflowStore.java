package com.deppon.crm.module.common.server.workflow;

import java.util.Properties;

import javax.sql.DataSource;

import com.opensymphony.workflow.spi.jdbc.JDBCWorkflowStore;

public class ExtJDBCWorkflowStore extends JDBCWorkflowStore {
	protected Properties jdbcTemplateProperties;

	public Properties getJdbcTemplateProperties() {
		return this.jdbcTemplateProperties;
	}

	public void setJdbcTemplateProperties(Properties jdbcTemplateProperties) {
		this.jdbcTemplateProperties = jdbcTemplateProperties;
	}

	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}

	public void init() {
		entrySequence = jdbcTemplateProperties.getProperty("entry.sequence");
		stepSequence = jdbcTemplateProperties.getProperty("step.sequence");
		entryTable = jdbcTemplateProperties.getProperty("entry.table",
				"OS_WFENTRY");
		entryId = jdbcTemplateProperties.getProperty("entry.id", "ID");
		entryName = jdbcTemplateProperties.getProperty("entry.name", "NAME");
		entryState = jdbcTemplateProperties.getProperty("entry.state", "STATE");
		historyTable = jdbcTemplateProperties.getProperty("history.table",
				"OS_HISTORYSTEP");
		currentTable = jdbcTemplateProperties.getProperty("current.table",
				"OS_CURRENTSTEP");
		currentPrevTable = jdbcTemplateProperties.getProperty(
				"currentPrev.table", "OS_CURRENTSTEP_PREV");
		historyPrevTable = jdbcTemplateProperties.getProperty(
				"historyPrev.table", "OS_HISTORYSTEP_PREV");
		stepId = jdbcTemplateProperties.getProperty("step.id", "ID");
		stepEntryId = jdbcTemplateProperties.getProperty("step.entryId",
				"ENTRY_ID");
		stepStepId = jdbcTemplateProperties.getProperty("step.stepId",
				"STEP_ID");
		stepActionId = jdbcTemplateProperties.getProperty("step.actionId",
				"ACTION_ID");
		stepOwner = jdbcTemplateProperties.getProperty("step.owner", "OWNER");
		stepCaller = jdbcTemplateProperties
				.getProperty("step.caller", "CALLER");
		stepStartDate = jdbcTemplateProperties.getProperty("step.startDate",
				"START_DATE");
		stepFinishDate = jdbcTemplateProperties.getProperty("step.finishDate",
				"FINISH_DATE");
		stepDueDate = jdbcTemplateProperties.getProperty("step.dueDate",
				"DUE_DATE");
		stepStatus = jdbcTemplateProperties
				.getProperty("step.status", "STATUS");
		stepPreviousId = jdbcTemplateProperties.getProperty("step.previousId",
				"PREVIOUS_ID");
	}
}