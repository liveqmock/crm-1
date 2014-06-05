package com.deppon.foss.framework.server.components.jobgrid.dao.jdbc;

public interface JDBCContants {
	// 任务表
	String TABLE_JOB_SCHEDULE = "CRM_QRTZ_JOB_SCHEDULES";
	// 日志表
	String TABLE_JOB_LOGGING = "CRM_QRTZ_JOB_LOGGINGS";
	// 规则表
	String TABLE_JOB_PLANNING = "CRM_QRTZ_JOB_PLANNINGS";
	// 预警时效表
	String TABLE_JOB_WARNNING = "CRM_QRTZ_JOB_WARNNINGS";
	// 消息表
	String TABLE_JOB_MESSAGE = "CRM_QRTZ_JOB_MESSAGES";
	//
	String COL_INSTANCE_ID = "INSTANCE_ID";
	String COL_SCOPE_TYPE = "SCOPE_TYPE";
	String COL_SCOPE_NAME = "SCOPE_NAME";
	String COL_ACCESS_RULE = "ACCESS_RULE";

	String COL_ID = "ID";
	String COL_DESCRIPTION = "DESCRIPTION";
	String COL_TRIGGER_STATE = "TRIGGER_STATE";
	String COL_TRIGGER_TYPE = "TRIGGER_TYPE";
	String COL_TRIGGER_EXPRESSION = "TRIGGER_EXPRESSION";
	String COL_JOB_CLASS = "JOB_CLASS";
	String COL_JOB_DATA = "JOB_DATA";
	// String COL_CLUSTER_NAME = "CLUSTER_NAME";
	String COL_INSTANCE_NAME = "INSTANCE_NAME";
	String COL_TRIGGER_NAME = "TRIGGER_NAME";
	String COL_TRIGGER_GROUP = "TRIGGER_GROUP";
	String COL_JOB_NAME = "JOB_NAME";
	String COL_JOB_GROUP = "JOB_GROUP";
	String COL_JOB_ACTION = "JOB_ACTION";
	String COL_FIRED_TIME = "FIRED_TIME";
	String COL_ERROR_MESSAGE = "ERROR_MESSAGE";
	String COL_FLOW_UUID = "FLOW_UUID";

	String COL_WARN_TYPE = "WARN_TYPE";
	String COL_FAIL_TIME = "FAIL_TIME";
	String COL_FAIL_COUNT = "FAIL_COUNT";
	String COL_EMAIL = "EMAIL";
	String COL_MOBILE = "MOBILE";
	String COL_SUBJECT = "SUBJECT";
	String COL_CONTENT = "CONTENT";
	String COL_SEND = "SEND";

	int TRIGGER_TYPE_SIMPLE = 0;
	int TRIGGER_TYPE_CRON = 1;

	String COL_ROW_NUMBER = "COL_ROW_NUMBER";
	String ANY_CONDITION = "ANY_CONDITION";
	String CONDITION_AND = " AND ";
	//日志 新增 sql
	String INSERT_JOB_LOGGING = "INSERT INTO " + TABLE_JOB_LOGGING + "("
			+ COL_ID + ", " + COL_INSTANCE_ID + ", " + COL_TRIGGER_GROUP + ", "
			+ COL_TRIGGER_NAME + ", " + COL_JOB_GROUP + ", " + COL_JOB_NAME
			+ ", " + COL_FIRED_TIME + ", " + COL_JOB_ACTION + ", "
			+ COL_FLOW_UUID + ", " + COL_ERROR_MESSAGE
			+ ") VALUES (?,?,?,?,?,?,?,?,?,?)";
	// 任务新增 sql
	String INSRET_JOB_SCHEDULE = "INSERT INTO " + TABLE_JOB_SCHEDULE + "("
			+ COL_ID + ", " + COL_TRIGGER_GROUP + ", " + COL_TRIGGER_NAME
			+ ", " + COL_JOB_GROUP + ", " + COL_JOB_NAME + ", "
			+ COL_DESCRIPTION + ", " + COL_TRIGGER_TYPE + ", "
			+ COL_TRIGGER_EXPRESSION + ", " + COL_JOB_CLASS + ", "
			+ COL_JOB_DATA + ") VALUES (?,?,?,?,?,?,?,?,?,?)";
	//预警时效新增 sql
	String INSRET_JOB_WARNNING = "INSERT INTO " + TABLE_JOB_WARNNING + "("
			+ COL_ID + ", " + COL_JOB_GROUP + ", " + COL_JOB_NAME + ", "
			+ COL_WARN_TYPE + ", " + COL_FAIL_TIME + ", " + COL_FAIL_COUNT
			+ ", " + COL_EMAIL + ", " + COL_MOBILE
			+ ") VALUES (?,?,?,?,?,?,?,?)";
    //日志查询 by job
	String QUERY_LOGGING_BY_JOB = "SELECT " + COL_INSTANCE_ID + ", "
			+ COL_TRIGGER_GROUP + ", " + COL_TRIGGER_NAME + ", "
			+ COL_JOB_GROUP + ", " + COL_JOB_NAME + ", " + COL_FIRED_TIME
			+ ", " + COL_JOB_ACTION + ", " + COL_FLOW_UUID + ", "
			+ COL_ERROR_MESSAGE + " FROM " + TABLE_JOB_LOGGING + " WHERE "
			+ COL_INSTANCE_ID + "= ?" + " AND " + COL_JOB_NAME + "= ?";
    // 日志查询 by trigger
	String QUERY_LOGGING_BY_TRIGGER = "SELECT " + COL_INSTANCE_ID + ", "
			+ COL_TRIGGER_GROUP + ", " + COL_TRIGGER_NAME + ", "
			+ COL_JOB_GROUP + ", " + COL_JOB_NAME + ", " + COL_FIRED_TIME
			+ ", " + COL_JOB_ACTION + ", " + COL_FLOW_UUID + ", "
			+ COL_ERROR_MESSAGE + " FROM " + TABLE_JOB_LOGGING + " WHERE "
			+ COL_INSTANCE_ID + "= ?" + " AND " + COL_TRIGGER_NAME + "= ?";
	//更新任务 by 主键
	String UPDATE_SCHEDULE_BY_PK = "UPDATE " + TABLE_JOB_SCHEDULE + " SET "
			+ COL_TRIGGER_GROUP + " = ? ," + COL_TRIGGER_NAME + " = ? ,"
			+ COL_JOB_GROUP + " = ? ," + COL_JOB_NAME + " = ? ,"
			+ COL_DESCRIPTION + " = ? ," + COL_TRIGGER_TYPE + " = ? ,"
			+ COL_TRIGGER_EXPRESSION + " = ? ," + COL_JOB_CLASS + " = ? ,"
			+ COL_JOB_DATA + " = ? " + " WHERE " + COL_ID + " = ?";
	//查询任务 by cluster
	String QUERY_SCHEDULE_BY_CLUSTER = "SELECT " + COL_ID + ", "
			+ COL_TRIGGER_GROUP + ", " + COL_TRIGGER_NAME + ", "
			+ COL_JOB_GROUP + ", " + COL_JOB_NAME + ", " + COL_DESCRIPTION
			+ ", " + COL_TRIGGER_TYPE + ", " + COL_TRIGGER_EXPRESSION + ", "
			+ COL_JOB_CLASS + ", " + COL_JOB_DATA + " FROM "
			+ TABLE_JOB_SCHEDULE
	/*
	 * + " WHERE " + COL_CLUSTER_NAME + "= ?"
	 */;
	//删除任务 by job
	String DELETE_SCHEDLE_BY_JOB = "DELETE " + " FROM " + TABLE_JOB_SCHEDULE
			+ " WHERE " + COL_JOB_GROUP + " = ? AND " + COL_JOB_NAME + " = ?";
	//删除任务 by trigger
	String DELETE_SCHEDLE_BY_TRIGGER = "DELETE " + " FROM "
			+ TABLE_JOB_SCHEDULE + " WHERE " + COL_TRIGGER_GROUP + " = ? AND "
			+ COL_TRIGGER_NAME + " = ?";
	//查询规则 by cluster
	String QUERY_PLANNING_BY_CLUSTER = "SELECT " + COL_ID + ", "
			+ COL_INSTANCE_ID + ", " + COL_SCOPE_TYPE + ", " + COL_SCOPE_NAME
			+ ", " + COL_ACCESS_RULE + " FROM " + TABLE_JOB_PLANNING
			+ " WHERE " + COL_INSTANCE_ID + " = ?";
	//规则新增 sql
	String INSERT_JOB_PLANNING = "INSERT INTO " + TABLE_JOB_PLANNING + "("
			+ COL_ID + ", " + COL_INSTANCE_ID + ", " + COL_SCOPE_TYPE + ", "
			+ COL_SCOPE_NAME + ", " + COL_ACCESS_RULE + ") VALUES (?,?,?,?,?)";
	//规则更新 sql
	String UPDATE_JOB_PLANNING = "UPDATE " + TABLE_JOB_PLANNING + " SET "
			+ COL_INSTANCE_ID + " = ? ," + COL_SCOPE_TYPE + " = ? ,"
			+ COL_SCOPE_NAME + " = ? ," + COL_ACCESS_RULE + " = ? " + " WHERE "
			+ COL_ID + " = ?";
	//规则查询详情 by id
	String QUERY_PLANNING_BY_ID = "SELECT " + COL_ID + ", " + COL_INSTANCE_ID
			+ ", " + COL_SCOPE_TYPE + ", " + COL_SCOPE_NAME + ", "
			+ COL_ACCESS_RULE + " FROM " + TABLE_JOB_PLANNING + " WHERE "
			+ COL_ID + " = ?";
	//规则删除 by id
	String DELETE_PLANNING_BY_ID = "DELETE " + " FROM " + TABLE_JOB_PLANNING
			+ " WHERE " + COL_ID + " = ?";
	//规则查询 by condition
	String QUERY_PLANNING_BY_CONDITION = "SELECT * FROM (" + "SELECT " + COL_ID
			+ ", " + COL_INSTANCE_ID + ", " + COL_SCOPE_TYPE + ", "
			+ COL_SCOPE_NAME + ", " + COL_ACCESS_RULE + ", " + COL_ROW_NUMBER
			+ " FROM " + TABLE_JOB_PLANNING + " WHERE 1=1 " + ANY_CONDITION
			+ " ) RNT " + " WHERE RN BETWEEN ? AND ? ";
	//规则Count查询 by condition 
	String QUERY_PLANNING_COUNT_BY_CONDITION = "SELECT COUNT(" + COL_ID
			+ ") FROM " + TABLE_JOB_PLANNING + " WHERE 1=1 " + ANY_CONDITION;
	//日志查询 by condition
	String QUERY_LOGGING_BY_CONDITION = "SELECT * FROM (" + " SELECT "
			+ COL_ID
			+ ", "
			+ COL_INSTANCE_ID
			+ ", "
			+ COL_TRIGGER_GROUP
			+ ", "
			+ COL_TRIGGER_NAME
			+ ", "
			+ COL_JOB_GROUP
			+ ", "
			+ COL_JOB_NAME
			+ ", "
			+ COL_FIRED_TIME
			+ ", "
			+ COL_JOB_ACTION
			+ ", "
			+ COL_FLOW_UUID
			+ ", "
			+ COL_ERROR_MESSAGE
			+ ", "
			+ COL_ROW_NUMBER
			+ " FROM "
			+ TABLE_JOB_LOGGING
			+ " WHERE "
			+ COL_FIRED_TIME
			+ " BETWEEN TO_DATE(?,'YYYY-MM-DD HH24:MI:SS') AND TO_DATE(?,'YYYY-MM-DD HH24:MI:SS') "
			+ ANY_CONDITION + " ) RNT " + " WHERE RN BETWEEN ? AND ? ";
	//日志Count查询 by condition
	String QUERY_LOGGING_COUNT_BY_CONDITION = "SELECT count("
			+ COL_ID
			+ ") FROM "
			+ TABLE_JOB_LOGGING
			+ " WHERE "
			+ COL_FIRED_TIME
			+ " BETWEEN TO_DATE(?,'YYYY-MM-DD HH24:MI:SS') AND TO_DATE(?,'YYYY-MM-DD HH24:MI:SS') "
			+ ANY_CONDITION;
	
	String QUERY_VETOED_LOGGING_COUNT_BY_NAME = "SELECT count("
			+ COL_ID
			+ ") FROM "
			+ TABLE_JOB_LOGGING
			+ " WHERE "
			+ COL_JOB_GROUP
			+ " = ? AND "
			+ COL_JOB_NAME
			+ " = ? AND "
			+ COL_JOB_ACTION
			+ " = ? AND "
			+ COL_FIRED_TIME
			+ " BETWEEN TO_DATE(?,'YYYY-MM-DD HH24:MI:SS') AND TO_DATE(?,'YYYY-MM-DD HH24:MI:SS') ";
	//日志查询 by id
	String QUERY_LOGGING_BY_ID = "SELECT " + COL_ID + ", " + COL_INSTANCE_ID
			+ ", " + COL_TRIGGER_GROUP + ", " + COL_TRIGGER_NAME + ", "
			+ COL_JOB_GROUP + ", " + COL_JOB_NAME + ", " + COL_FIRED_TIME
			+ ", " + COL_JOB_ACTION + ", " + COL_FLOW_UUID + ", "
			+ COL_ERROR_MESSAGE + " FROM " + TABLE_JOB_LOGGING + " WHERE "
			+ COL_ID + " =? ";
	//任务查询 by condition
	String QUERY_SCHEDULE_BY_CONDITION = "SELECT * FROM (" + " SELECT "
			+ COL_ID + ", " + COL_TRIGGER_GROUP + ", " + COL_TRIGGER_NAME
			+ ", " + COL_JOB_GROUP + ", " + COL_JOB_NAME + ", "
			+ COL_DESCRIPTION + ", " + COL_TRIGGER_TYPE + ", "
			+ COL_TRIGGER_EXPRESSION + ", " + COL_JOB_CLASS + ", "
			+ COL_JOB_DATA + ", " + COL_ROW_NUMBER + " FROM "
			+ TABLE_JOB_SCHEDULE + " WHERE 1=1 " + ANY_CONDITION + " ) RNT "
			+ " WHERE RN BETWEEN ? AND ? ";
	//任务查询 by name
	String QUERY_SCHEDULE_BY_NAME = "SELECT " + COL_ID + ", "
			+ COL_TRIGGER_GROUP + ", " + COL_TRIGGER_NAME + ", "
			+ COL_JOB_GROUP + ", " + COL_JOB_NAME + ", " + COL_DESCRIPTION
			+ ", " + COL_TRIGGER_TYPE + ", " + COL_TRIGGER_EXPRESSION + ", "
			+ COL_JOB_CLASS + ", " + COL_JOB_DATA + " FROM "
			+ TABLE_JOB_SCHEDULE + " WHERE " + COL_JOB_GROUP + " = ? AND "
			+ COL_JOB_NAME + " = ?";
	//任务Count查询 by condition
	String QUERY_SCHEDULE_COUNT_BY_CONDITION = "SELECT COUNT(" + COL_ID
			+ ") FROM " + TABLE_JOB_SCHEDULE + " WHERE 1=1 " + ANY_CONDITION;
	//任务查询详情 by id
	String QUERY_SCHEDULE_BY_ID = "SELECT " + COL_ID + ", " + COL_TRIGGER_GROUP
			+ ", " + COL_TRIGGER_NAME + ", " + COL_JOB_GROUP + ", "
			+ COL_JOB_NAME + ", " + COL_DESCRIPTION + ", " + COL_TRIGGER_TYPE
			+ ", " + COL_TRIGGER_EXPRESSION + ", " + COL_JOB_CLASS + ", "
			+ COL_JOB_DATA + " FROM " + TABLE_JOB_SCHEDULE + " WHERE " + COL_ID
			+ " =? ";
	//任务删除 by id
	String DELETE_SCHEDLE_BY_ID = "DELETE " + " FROM " + TABLE_JOB_SCHEDULE
			+ " WHERE " + COL_ID + " =? ";
	//预警时效删除 by name
	String DELETE_WARNNING_BY_NAME = "DELETE " + " FROM " + TABLE_JOB_WARNNING
			+ " WHERE " + COL_JOB_GROUP + " = ? AND " + COL_JOB_NAME + " = ?";
	//预警时效查询 by name
	String QUERY_WARNNING_BY_NAME = "SELECT " + COL_ID + ", " + COL_JOB_GROUP
			+ ", " + COL_JOB_NAME + ", " + COL_WARN_TYPE + ", " + COL_FAIL_TIME
			+ ", " + COL_FAIL_COUNT + ", " + COL_EMAIL + ", " + COL_MOBILE
			+ " FROM " + TABLE_JOB_WARNNING + " WHERE " + COL_JOB_GROUP
			+ " = ? AND " + COL_JOB_NAME + " = ?";
	//消息新增
	String INSRET_JOB_MESSAGE = "INSERT INTO " + TABLE_JOB_MESSAGE + "("
			+ COL_ID + ", " + COL_EMAIL + ", " + COL_MOBILE + ", "
			+ COL_SUBJECT + ", " + COL_CONTENT + ", " + COL_SEND
			+ ") VALUES (?,?,?,?,?,?)";
	//消息更新
	String UPDATE_JOB_MESSAGE = "UPDATE " + TABLE_JOB_MESSAGE + " SET "
			+ COL_SEND + " = ? " + " WHERE " + COL_ID + " = ?";
	//消息查询全部
	String QUERY_ALL_MESSAGE = "SELECT " + COL_ID + ", " + COL_EMAIL + ", "
			+ COL_MOBILE + ", " + COL_SUBJECT + ", " + COL_CONTENT + ", "
			+ COL_SEND + " FROM " + TABLE_JOB_MESSAGE + " WHERE " + COL_SEND
			+ "= ?";

}
