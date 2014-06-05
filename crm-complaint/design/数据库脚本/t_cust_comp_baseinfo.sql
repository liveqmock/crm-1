-- Create table
create table T_CUST_COMP_BASEINFO
(
  FID           NUMBER(10) not null,
  FPARENTID     NUMBER(10) not null,
  FLEVEL        NUMBER(1) not null,
  FLEVELNAME    VARCHAR2(100),
  FBASEINFO     VARCHAR2(150) not null,
  FCLASSCODE    VARCHAR2(50) not null,
  FTYPECODE     VARCHAR2(50) not null,
  FUPDATEUSERID VARCHAR2(10) not null,
  FUPDATETIME   TIMESTAMP(6) not null,
  FCREATEUSERID VARCHAR2(10) not null,
  FCREATETIME   TIMESTAMP(6) not null,
  FISLEAF       NUMBER(1),
  constraint PK_T_CUST_COMP_BASEINFO primary key  (fid)
);

-- Add comments to the table 
comment on table T_CUST_COMP_BASEINFO
  is '工单基础资料表';
-- Add comments to the columns 
comment on column T_CUST_COMP_BASEINFO.FID
  is '主键ID';
comment on column T_CUST_COMP_BASEINFO.FPARENTID
  is '父亲ID';
comment on column T_CUST_COMP_BASEINFO.FLEVEL
  is '类型级别，目前只分五级';
comment on column T_CUST_COMP_BASEINFO.FLEVELNAME
  is '级别对应的名称';
comment on column T_CUST_COMP_BASEINFO.FBASEINFO
  is '基础资料的类容';
comment on column T_CUST_COMP_BASEINFO.FCLASSCODE
  is '基础资料分类，目前只有工单和责任，默认为工单';
comment on column T_CUST_COMP_BASEINFO.FTYPECODE
  is '基础资料类型，分为投诉和异常，默认投诉';
comment on column T_CUST_COMP_BASEINFO.FUPDATEUSERID
  is '修改人ID';
comment on column T_CUST_COMP_BASEINFO.FUPDATETIME
  is '修改日期';
comment on column T_CUST_COMP_BASEINFO.FCREATEUSERID
  is '创建人id';
comment on column T_CUST_COMP_BASEINFO.FCREATETIME
  is '创建时间';
comment on column T_CUST_COMP_BASEINFO.FISLEAF
  is '当前节点是否为叶子节点，1为是，0为不是';
