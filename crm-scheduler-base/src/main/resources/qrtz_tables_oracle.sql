--
-- A hint submitted by a user: Oracle DB MUST be created as "shared" and the 
-- job_queue_processes parameter  must be greater than 2, otherwise a DB lock 
-- will happen.   However, these settings are pretty much standard after any
-- Oracle install, so most users need not worry about this.
--
-- Many other users (including the primary author of Quartz) have had success
-- runing in dedicated mode, so only consider the above as a hint ;-)
--

--delete from crm_qrtz_job_listeners;
--delete from crm_qrtz_trigger_listeners;
--delete from crm_qrtz_fired_triggers;
--delete from crm_qrtz_simple_triggers;
--delete from crm_qrtz_cron_triggers;
--delete from crm_qrtz_blob_triggers;
--delete from crm_qrtz_triggers;
--delete from crm_qrtz_job_details;
--delete from crm_qrtz_calendars;
--delete from crm_qrtz_paused_trigger_grps;
--delete from crm_qrtz_locks;
--delete from crm_qrtz_scheduler_state;

--delete from crm_qrtz_JOB_SCHEDULES;
--delete from crm_qrtz_JOB_PLANNINGS;
--delete from crm_qrtz_JOB_LOGGINGS;
--delete from crm_qrtz_JOB_WARNNINGS;
--delete from crm_qrtz_JOB_MESSAGES;

drop table crm_qrtz_calendars;
drop table crm_qrtz_fired_triggers;
drop table crm_qrtz_trigger_listeners;
drop table crm_qrtz_blob_triggers;
drop table crm_qrtz_cron_triggers;
drop table crm_qrtz_simple_triggers;
drop table crm_qrtz_triggers;
drop table crm_qrtz_job_listeners;
drop table crm_qrtz_job_details;
drop table crm_qrtz_paused_trigger_grps;
drop table crm_qrtz_locks;
drop table crm_qrtz_scheduler_state;

drop table crm_qrtz_JOB_SCHEDULES;
drop table crm_qrtz_JOB_PLANNINGS;
drop table crm_qrtz_JOB_LOGGINGS;
drop table crm_qrtz_JOB_WARNNINGS;
drop table crm_qrtz_JOB_MESSAGES;

CREATE TABLE crm_qrtz_JOB_WARNNINGS
  (
    ID VARCHAR2(50) NOT NULL,
    WARN_TYPE VARCHAR2(50),
    FAIL_TIME NUMBER,
    FAIL_COUNT NUMBER,
    EMAIL VARCHAR2(2000),
    MOBILE VARCHAR2(2000),
    JOB_GROUP VARCHAR2(50),
    JOB_NAME VARCHAR2(50),
    CONSTRAINT PRIMARYcrm_qrtz_JOB_WARNNINGS1 PRIMARY KEY (ID)
  );
  
CREATE TABLE crm_qrtz_JOB_MESSAGES
  (
    ID VARCHAR2(50) NOT NULL,
    EMAIL VARCHAR2(2000),
    MOBILE VARCHAR2(2000),
    SUBJECT VARCHAR2(2000),
    CONTENT VARCHAR2(2000),
    SEND NUMBER,
    CONSTRAINT PRIMARYcrm_qrtz_JOB_MESSAGES1 PRIMARY KEY (ID)
  );

  
CREATE TABLE crm_qrtz_JOB_SCHEDULES
  (
    ID VARCHAR2(50) NOT NULL,
    TRIGGER_GROUP VARCHAR2(50),
    TRIGGER_NAME VARCHAR2(50),
    JOB_GROUP VARCHAR2(50),
    JOB_NAME VARCHAR2(50),
    DESCRIPTION VARCHAR2(200),
    TRIGGER_TYPE NUMBER,
    TRIGGER_EXPRESSION VARCHAR2(30),
    JOB_CLASS VARCHAR2(500),
    JOB_DATA VARCHAR2(2000),
    CONSTRAINT PRIMARYcrm_qrtz_JOB_SCHEDULES1 PRIMARY KEY (ID)
  );

CREATE TABLE crm_qrtz_JOB_PLANNINGS
  (
    ID VARCHAR2(50) NOT NULL,
    INSTANCE_ID VARCHAR2(50) NOT NULL,
    SCOPE_TYPE NUMBER NOT NULL,
    SCOPE_NAME VARCHAR2(50) NOT NULL,
    ACCESS_RULE NUMBER NOT NULL,
    CONSTRAINT PRIMARYcrm_qrtz_JOB_PLANNINGS1 PRIMARY KEY (INSTANCE_ID, SCOPE_TYPE, SCOPE_NAME,
    ACCESS_RULE)
  );
    
CREATE TABLE crm_qrtz_JOB_LOGGINGS
  (
    ID VARCHAR2(50) NOT NULL,
    INSTANCE_ID VARCHAR2(50),
    TRIGGER_GROUP VARCHAR2(50),
    TRIGGER_NAME VARCHAR2(50),
    JOB_GROUP VARCHAR2(50),
    JOB_NAME VARCHAR2(50),
    FIRED_TIME DATE,
    JOB_ACTION VARCHAR2(20),
    ERROR_MESSAGE VARCHAR2(500),
    FLOW_UUID VARCHAR2(36),
    CONSTRAINT PRIMARYcrm_qrtz_JOB_LOGGINGS1 PRIMARY KEY (ID)
  );

CREATE TABLE crm_qrtz_job_details
  (
    JOB_NAME  VARCHAR2(200) NOT NULL,
    JOB_GROUP VARCHAR2(200) NOT NULL,
    DESCRIPTION VARCHAR2(250) NULL,
    JOB_CLASS_NAME   VARCHAR2(250) NOT NULL, 
    IS_DURABLE VARCHAR2(1) NOT NULL,
    IS_VOLATILE VARCHAR2(1) NOT NULL,
    IS_STATEFUL VARCHAR2(1) NOT NULL,
    REQUESTS_RECOVERY VARCHAR2(1) NOT NULL,
    JOB_DATA BLOB NULL,
    PRIMARY KEY (JOB_NAME,JOB_GROUP)
);
CREATE TABLE crm_qrtz_job_listeners
  (
    JOB_NAME  VARCHAR2(200) NOT NULL, 
    JOB_GROUP VARCHAR2(200) NOT NULL,
    JOB_LISTENER VARCHAR2(200) NOT NULL,
    PRIMARY KEY (JOB_NAME,JOB_GROUP,JOB_LISTENER),
    FOREIGN KEY (JOB_NAME,JOB_GROUP) 
  REFERENCES crm_qrtz_JOB_DETAILS(JOB_NAME,JOB_GROUP)
);
CREATE TABLE crm_qrtz_triggers
  (
    TRIGGER_NAME VARCHAR2(200) NOT NULL,
    TRIGGER_GROUP VARCHAR2(200) NOT NULL,
    JOB_NAME  VARCHAR2(200) NOT NULL, 
    JOB_GROUP VARCHAR2(200) NOT NULL,
    IS_VOLATILE VARCHAR2(1) NOT NULL,
    DESCRIPTION VARCHAR2(250) NULL,
    NEXT_FIRE_TIME NUMBER(13) NULL,
    PREV_FIRE_TIME NUMBER(13) NULL,
    PRIORITY NUMBER(13) NULL,
    TRIGGER_STATE VARCHAR2(16) NOT NULL,
    TRIGGER_TYPE VARCHAR2(8) NOT NULL,
    START_TIME NUMBER(13) NOT NULL,
    END_TIME NUMBER(13) NULL,
    CALENDAR_NAME VARCHAR2(200) NULL,
    MISFIRE_INSTR NUMBER(2) NULL,
    JOB_DATA BLOB NULL,
    PRIMARY KEY (TRIGGER_NAME,TRIGGER_GROUP),
    FOREIGN KEY (JOB_NAME,JOB_GROUP) 
  REFERENCES crm_qrtz_JOB_DETAILS(JOB_NAME,JOB_GROUP) 
);
CREATE TABLE crm_qrtz_simple_triggers
  (
    TRIGGER_NAME VARCHAR2(200) NOT NULL,
    TRIGGER_GROUP VARCHAR2(200) NOT NULL,
    REPEAT_COUNT NUMBER(7) NOT NULL,
    REPEAT_INTERVAL NUMBER(12) NOT NULL,
    TIMES_TRIGGERED NUMBER(10) NOT NULL,
    PRIMARY KEY (TRIGGER_NAME,TRIGGER_GROUP),
    FOREIGN KEY (TRIGGER_NAME,TRIGGER_GROUP) 
  REFERENCES crm_qrtz_TRIGGERS(TRIGGER_NAME,TRIGGER_GROUP)
);
CREATE TABLE crm_qrtz_cron_triggers
  (
    TRIGGER_NAME VARCHAR2(200) NOT NULL,
    TRIGGER_GROUP VARCHAR2(200) NOT NULL,
    CRON_EXPRESSION VARCHAR2(120) NOT NULL,
    TIME_ZONE_ID VARCHAR2(80),
    PRIMARY KEY (TRIGGER_NAME,TRIGGER_GROUP),
    FOREIGN KEY (TRIGGER_NAME,TRIGGER_GROUP) 
  REFERENCES crm_qrtz_TRIGGERS(TRIGGER_NAME,TRIGGER_GROUP)
);
CREATE TABLE crm_qrtz_blob_triggers
  (
    TRIGGER_NAME VARCHAR2(200) NOT NULL,
    TRIGGER_GROUP VARCHAR2(200) NOT NULL,
    BLOB_DATA BLOB NULL,
    PRIMARY KEY (TRIGGER_NAME,TRIGGER_GROUP),
    FOREIGN KEY (TRIGGER_NAME,TRIGGER_GROUP) 
        REFERENCES crm_qrtz_TRIGGERS(TRIGGER_NAME,TRIGGER_GROUP)
);
CREATE TABLE crm_qrtz_trigger_listeners
  (
    TRIGGER_NAME  VARCHAR2(200) NOT NULL, 
    TRIGGER_GROUP VARCHAR2(200) NOT NULL,
    TRIGGER_LISTENER VARCHAR2(200) NOT NULL,
    PRIMARY KEY (TRIGGER_NAME,TRIGGER_GROUP,TRIGGER_LISTENER),
    FOREIGN KEY (TRIGGER_NAME,TRIGGER_GROUP) 
  REFERENCES crm_qrtz_TRIGGERS(TRIGGER_NAME,TRIGGER_GROUP)
);
CREATE TABLE crm_qrtz_calendars
  (
    CALENDAR_NAME  VARCHAR2(200) NOT NULL, 
    CALENDAR BLOB NOT NULL,
    PRIMARY KEY (CALENDAR_NAME)
);
CREATE TABLE crm_qrtz_paused_trigger_grps
  (
    TRIGGER_GROUP  VARCHAR2(200) NOT NULL, 
    PRIMARY KEY (TRIGGER_GROUP)
);
CREATE TABLE crm_qrtz_fired_triggers 
  (
    ENTRY_ID VARCHAR2(95) NOT NULL,
    TRIGGER_NAME VARCHAR2(200) NOT NULL,
    TRIGGER_GROUP VARCHAR2(200) NOT NULL,
    IS_VOLATILE VARCHAR2(1) NOT NULL,
    INSTANCE_NAME VARCHAR2(200) NOT NULL,
    FIRED_TIME NUMBER(13) NOT NULL,
    PRIORITY NUMBER(13) NOT NULL,
    STATE VARCHAR2(16) NOT NULL,
    JOB_NAME VARCHAR2(200) NULL,
    JOB_GROUP VARCHAR2(200) NULL,
    IS_STATEFUL VARCHAR2(1) NULL,
    REQUESTS_RECOVERY VARCHAR2(1) NULL,
    PRIMARY KEY (ENTRY_ID)
);
CREATE TABLE crm_qrtz_scheduler_state 
  (
    INSTANCE_NAME VARCHAR2(200) NOT NULL,
    LAST_CHECKIN_TIME NUMBER(13) NOT NULL,
    CHECKIN_INTERVAL NUMBER(13) NOT NULL,
    PRIMARY KEY (INSTANCE_NAME)
);
CREATE TABLE crm_qrtz_locks
  (
    LOCK_NAME  VARCHAR2(40) NOT NULL, 
    PRIMARY KEY (LOCK_NAME)
);
INSERT INTO crm_qrtz_locks values('TRIGGER_ACCESS');
INSERT INTO crm_qrtz_locks values('JOB_ACCESS');
INSERT INTO crm_qrtz_locks values('CALENDAR_ACCESS');
INSERT INTO crm_qrtz_locks values('STATE_ACCESS');
INSERT INTO crm_qrtz_locks values('MISFIRE_ACCESS');
create index idx_crm_qrtz_j_req_recovery on crm_qrtz_job_details(REQUESTS_RECOVERY);
create index idx_crm_qrtz_t_next_fire_time on crm_qrtz_triggers(NEXT_FIRE_TIME);
create index idx_crm_qrtz_t_state on crm_qrtz_triggers(TRIGGER_STATE);
create index idx_crm_qrtz_t_nft_st on crm_qrtz_triggers(NEXT_FIRE_TIME,TRIGGER_STATE);
create index idx_crm_qrtz_t_volatile on crm_qrtz_triggers(IS_VOLATILE);
create index idx_crm_qrtz_ft_trig_name on crm_qrtz_fired_triggers(TRIGGER_NAME);
create index idx_crm_qrtz_ft_trig_group on crm_qrtz_fired_triggers(TRIGGER_GROUP);
create index idx_crm_qrtz_ft_trig_nm_gp on crm_qrtz_fired_triggers(TRIGGER_NAME,TRIGGER_GROUP);
create index idx_crm_qrtz_ft_trig_volatile on crm_qrtz_fired_triggers(IS_VOLATILE);
create index idx_crm_qrtz_ft_trig_inst_name on crm_qrtz_fired_triggers(INSTANCE_NAME);
create index idx_crm_qrtz_ft_job_name on crm_qrtz_fired_triggers(JOB_NAME);
create index idx_crm_qrtz_ft_job_group on crm_qrtz_fired_triggers(JOB_GROUP);
create index idx_crm_qrtz_ft_job_stateful on crm_qrtz_fired_triggers(IS_STATEFUL);
create index idx_c_qrtz_ft_job_req_recovery on crm_qrtz_fired_triggers(REQUESTS_RECOVERY);



commit;
