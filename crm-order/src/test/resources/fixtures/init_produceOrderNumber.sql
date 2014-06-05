delete from t_ord_ordernumberrule;
insert into t_ord_ordernumberrule(fid,fname,fresource,ftransportmode,fsign,fstatus,fcreatetime,fcreateuserid,fcreateuserempname)
values(SYS_GUID(),'苏宁易购','SUNING','JZKY','SN',1,sysdate,1,'系统管理员');
insert into t_ord_ordernumberrule(fid,fname,fresource,ftransportmode,fsign,fstatus,fcreatetime,fcreateuserid,fcreateuserempname)
values(SYS_GUID(),'苏宁易购','SUNING','PACKAGE','SNK',1,sysdate,1,'系统管理员');
insert into t_ord_ordernumberrule(fid,fname,fresource,ftransportmode,fsign,fstatus,fcreatetime,fcreateuserid,fcreateuserempname)
values(SYS_GUID(),'苏宁易购','SUNING','JZKH','SN',0,sysdate,1,'系统管理员');