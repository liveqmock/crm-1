-- Create table
create table T_CUST_COMP_SCENEREASON
(
  FID           NUMBER(10) not null,
  FSCENERESSON  NUMBER(10) not null,
  FPROCSTANDARD VARCHAR2(3000) not null,
  FUPDATEUSERID VARCHAR2(10) not null,
  FCREATEUSERID VARCHAR2(10) not null,
  FUPDATETIME   TIMESTAMP(6) not null,
  FCREATETIME   TIMESTAMP(6) not null,
  constraint T_CUST_COMP_SCENEREASON primary key (fid)
);
-- Add comments to the table 
comment on table T_CUST_COMP_SCENEREASON
  is '工单场景原因表';
-- Add comments to the columns 
comment on column T_CUST_COMP_SCENEREASON.FID
  is '主键ID';
comment on column T_CUST_COMP_SCENEREASON.FSCENERESSON
  is '外键基础资料表第五级场景原因Id';
comment on column T_CUST_COMP_SCENEREASON.FPROCSTANDARD
  is '处理标准';
comment on column T_CUST_COMP_SCENEREASON.FUPDATEUSERID
  is '最后一次修改人ID';
comment on column T_CUST_COMP_SCENEREASON.FCREATEUSERID
  is '创建人ID';
comment on column T_CUST_COMP_SCENEREASON.FUPDATETIME
  is '最后修改时间';
comment on column T_CUST_COMP_SCENEREASON.FCREATETIME
  is '创建时间';
