--创建环境--待完善

--查询待生效合同
insert into t_cust_contract
  (fid, fcontractbegindate, fcontractenddate, fcontractstatus)
values
  (111111111111111, sysdate, sysdate + 10, '1');
  
update t_cust_contract
   set fcontractbegindate = sysdate,
       fcontractenddate   = to_date('2222 3 2', 'yyyy mm dd')
 where fid = '111111111111111';
commit;

insert into t_cust_contract
  (fid, fcontractbegindate, fcontractenddate, fcontractstatus)
values
  (111111111111112, to_date('2012 10 12', 'yyyy hh mm'), sysdate + 10, '1');
commit;

--查询30到期合同
insert into t_cust_contract
  (fid, fcontractbegindate, fcontractenddate, fcontractstatus)
values
  (111111111111113, sysdate, sysdate + 15, '1');
commit;

--批量删除无效合同
insert into t_cust_contract
  (fid, fcontractbegindate, fcontractenddate, fcontractstatus)
values
  (111111111111114, sysdate, sysdate + 15, '2');

insert into t_cust_contract
  (fid, fcontractbegindate, fcontractenddate, fcontractstatus)
values
  (111111111111115, sysdate, sysdate + 15, '2');

insert into t_cust_contractoperatorlog
  (fid, fcontractid, foperatortype, fapprovalstate, foperatordeptid)
values
  ('222222222222', '111111111111114', 'insert', '3', '12345');

insert into t_cust_contractoperatorlog
  (fid, fcontractid, foperatortype, fapprovalstate, foperatordeptid)
values
  ('222222222223', '111111111111115', 'insert', '3', '12345');

insert into t_cust_contract
  (fid, fcontractbegindate, fcontractenddate, fcontractstatus)
values
  (111111111111116, sysdate, sysdate + 15, '2');

insert into t_cust_contractoperatorlog
  (fid, fcontractid, foperatortype, fapprovalstate, foperatordeptid)
values
  ('222222222224', '111111111111114', 'obsolete', '3', '12345');
  
commit;

--删除无效合同
insert into t_cust_contract
  (fid, fcontractbegindate, fcontractenddate, fcontractstatus)
values
  (111111111111117, sysdate, sysdate + 15, '2');

insert into t_cust_contractoperatorlog
  (fid, fcontractid, foperatortype, fapprovalstate, foperatordeptid)
values
  ('222222222225', '111111111111117', 'insert', '3', '12345');
  
insert into t_cust_contract
  (fid, fcontractbegindate, fcontractenddate, fcontractstatus)
values
  (400356898, sysdate, sysdate + 15, '2');
  
insert into t_cust_contractoperatorlog
  (fid, fcontractid, foperatortype, fapprovalstate, foperatordeptid)
values
  ('222222222280', '400356898', 'insert', '3', '12345');
  
commit;
--检验结算额度
--SUM(NVL(T.FFPREPAYAMOUNT, 0)) + SUM(NVL(T.FARRIVEDAOUNT, 0)) - SUM(NVL(T.FREFUNDRABATE,
  --                                                                     0)) - SUM(NVL(T.FAGENTRECEIVEPAY,
    --                                                                                 0))
--大于三个月中发货金额最大那个月的两倍
insert
  into T_CRM_CUSTANALYSEBYDAY(fid,
                              FFPREPAYAMOUNT,
                              FARRIVEDAOUNT,
                              FREFUNDRABATE,
                              FAGENTRECEIVEPAY,
                              FCANALYSETYPE,
                              fyear,
                              fmonth) values(123457,
                                             200,
                                             300,
                                             100,
                                             100,
                                             1,
                                             2012,
                                             12);

insert into T_CRM_CUSTANALYSEBYDAY
  (fid,
   fcustnumber,
   FFPREPAYAMOUNT,
   FARRIVEDAOUNT,
   FREFUNDRABATE,
   FAGENTRECEIVEPAY,
   FCANALYSETYPE,
   fyear,
   fmonth)
values
  (123458, 123457, 200, 300, 100, 100, 1, 2012, 8);
insert into T_CRM_CUSTANALYSEBYDAY
  (fid,
   fcustnumber,
   FFPREPAYAMOUNT,
   FARRIVEDAOUNT,
   FREFUNDRABATE,
   FAGENTRECEIVEPAY,
   FCANALYSETYPE,
   fyear,
   fmonth)
values
  (123459, 123457, 200, 300, 100, 100, 1, 2012, 4);
insert into T_CRM_CUSTANALYSEBYDAY
  (fid,
   fcustnumber,
   FFPREPAYAMOUNT,
   FARRIVEDAOUNT,
   FREFUNDRABATE,
   FAGENTRECEIVEPAY,
   FCANALYSETYPE,
   fyear,
   fmonth)
values
  (123450, 123457, 200, 300, 100, 100, 1, 2012, 2);
COMMIT;
--没有发货   
insert into T_CRM_CUSTANALYSEBYDAY
  (fid,
   fcustnumber,
   FFPREPAYAMOUNT,
   FARRIVEDAOUNT,
   FREFUNDRABATE,
   FAGENTRECEIVEPAY)
values
  (123456, 123456, 0, 0, 0, 0);
COMMIT;
--根据会员Id查询最后一次的合同信息

select * from t_cust_contract where fcustid = '535767';

--校验合同是否有工作流在审批，如果存在则不能进行任何操作
--1.待生效合同
insert into t_cust_contract
  (fid, fcontractbegindate, fcontractenddate, fcontractstatus)
values
  (111111111111118, sysdate + 5, sysdate + 30, '1');

insert into t_cust_contractoperatorlog
  (fid, fcontractid, foperatortype, fapprovalstate, foperatordeptid)
values
  ('222222222226', '111111111111118', 'insert', '3', '12345');

insert into t_cust_contract
  (fid, fcontractbegindate, fcontractenddate, fcontractstatus)
values
  (111111111111119, sysdate + 5, sysdate + 30, '1');
  
insert into t_cust_contractoperatorlog
  (fid, fcontractid, foperatortype, fapprovalstate, foperatordeptid)
values
  ('222222222227', '111111111111119', 'insert', '1', '12345');
commit;
insert into t_cust_contract
  (fid, fcontractbegindate, fcontractenddate, fcontractstatus)
values
  (111111111111120, sysdate + 5, sysdate + 30, '1');

insert into t_cust_contractoperatorlog
  (fid, fcontractid, foperatortype, fapprovalstate, foperatordeptid)
values
  ('222222222228', '111111111111120', 'insert', '2', '12345');
  


---2.非待生效合同
insert into t_cust_contract
  (fid, fcontractbegindate, fcontractenddate, fcontractstatus)
values
  (111111111111121, sysdate - 5, sysdate + 30, '0');

insert into t_cust_contractoperatorlog
  (fid, fcontractid, foperatortype, fapprovalstate, foperatordeptid)
values
  ('222222222228', '111111111111120', 'insert', '2', '12345');

insert into t_cust_contract
  (fid, fcontractbegindate, fcontractenddate, fcontractstatus)
values
  (111111111111122, sysdate - 5, sysdate + 30, '2');

insert into t_cust_contract
  (fid, fcontractbegindate, fcontractenddate, fcontractstatus)
values
  (111111111111123, sysdate - 5, sysdate + 30, '2');

insert into t_cust_contractoperatorlog
  (fid, fcontractid, foperatortype, fapprovalstate, foperatordeptid)
values
  ('222222222229', '111111111111123', 'update', '2', '12345');
 
 update t_cust_contractoperatorlog set flastupdatetime = sysdate where fid = 222222222229 ;
 update t_cust_contractoperatorlog set fapprovalstate = 3 where fid = 222222222229;
 update t_cust_contract set fcustid = '111111'where fid = 111111111111123;
 commit;
 
 insert into t_cust_contract(fid,
                       fcontractbegindate,
                       fcontractenddate,
                       fcontractstatus)
 values(111111111111124, sysdate - 5, sysdate + 30, '1');

insert into t_cust_contractoperatorlog
  (fid, fcontractid, foperatortype, fapprovalstate, foperatordeptid)
values
  ('222222222230', '111111111111124', 'update', '1', '12345');
commit;

insert into t_cust_contract
  (fid, fcontractbegindate, fcontractenddate, fcontractstatus)
values
  (111111111111125, sysdate - 5, sysdate + 30, '1');
 commit;
update t_cust_contractoperatorlog set flastupdatetime = sysdate where fid = 222222222229;
 update t_cust_contractoperatorlog set fapprovalstate = 3 where fid = 222222222229;
 update t_cust_contract set fcustid = '111111'where fid = 111111111111125;
commit;
 insert into t_cust_contractoperatorlog(fid,
                                  fcontractid,
                                  foperatortype,
                                  fapprovalstate,
                                  foperatordeptid)
 values('222222222231', '111111111111125', 'insert', '2', '12345');
commit;

insert into t_cust_contract
  (fid, fcontractbegindate, fcontractenddate, fcontractstatus)
values
  (111111111111126, sysdate - 5, sysdate + 30, '1');
insert into t_cust_contractoperatorlog
  (fid, fcontractid, foperatortype, fapprovalstate, foperatordeptid)
values
  ('222222222232', '111111111111126', 'insert', '1', '12345');
commit;

insert into t_cust_contract
  (fid, fcontractbegindate, fcontractenddate, fcontractstatus)
values
  (111111111111127, sysdate - 5, sysdate + 30, '1');
  
insert into t_cust_contract
  (fid, fcontractbegindate, fcontractenddate, fcontractstatus)
values
  (111111111111156, sysdate + 50000, sysdate + 300000, '1');
commit;
insert into t_cust_contractoperatorlog
  (fid, fcontractid, foperatortype, fapprovalstate, foperatordeptid)
values
  ('222222222233', '111111111111127', 'insert', '2', '12345');
update t_cust_contract set fcustid = '111111' where fid = 111111111111127;
commit;

--得到修改合同的信息
select *
  from t_cust_contract
 where fcontractstatus = '1'
   and fcontractbegindate > sysdate;
select * from t_Cust_Contractoperatorlog where fcontractid = 400298379;
update t_cust_contract set fcustid = '11111' where fid = 111111111111112;
commit;
select * from t_org_employee where fempname = '魏征';

select * from t_cust_contract where fid = '400350585';
update t_cust_contract set fdeptid = '1' where fid = '400350585';
commit;

update t_cust_custbasedata set fdeptid = '1' where fid = 222;
commit;
update t_cust_custbasedata set fdeptid = '1' where fid = 567616;
select * from t_cust_contract where fcustid = 567616;

select * from t_cust_contract where fcustid = '100546';
update t_cust_custbasedata set fdeptid = '1' where fid = 100546;
update t_cust_contract set fcontractstatus = '1' where fid = '400303236';
update t_cust_contract
   set fcontractbegindate = sysdate + 10
 where fid = '400303236';
  commit;

select * from t_cust_contract where fcontractstatus = 0;
select *
  from t_cust_contractoperatorlog
 where fcontractid = 111111111111121;

insert into t_cust_contractoperatorlog
  (fid, fcontractid, foperatortype, fapprovalstate, foperatordeptid)
values
  ('2222222222255', '111111111111121', 'insert', '1', '12345');
commit;


--绑定合同
--1.待生效合同，合同有审批中的工作流
insert into t_cust_contract
  (fid, fcontractbegindate, fcontractenddate, fcontractstatus)
values
  (111111111111130, sysdate + 5, sysdate + 30, '0');

insert into t_cust_contractoperatorlog
  (fid, fcontractid, foperatortype, fapprovalstate, foperatordeptid)
values
  ('2222222222241', '111111111111130', 'insert', '1', '12345');

update t_cust_contract
   set fcontractbegindate = trunc(sysdate) + 1000,
       fcontractenddate   = trunc(sysdate) + 2000,
       fcontractstatus    = '1'
 where fid = '111111111111130';
commit;
--2.
insert into t_cust_contract
  (fid, fcontractbegindate, fcontractenddate, fcontractstatus)
values
  (111111111111131, sysdate + 5, sysdate + 30, '1');
update t_cust_contract
   set fcontractbegindate = trunc(sysdate) + 1000,
       fcontractenddate   = trunc(sysdate) + 2000,
       fcontractstatus    = '1'
 where fid = '111111111111131';
insert into t_cust_contractoperatorlog
  (fid, fcontractid, foperatortype, fapprovalstate, foperatordeptid)
values
  ('2222222222240', '111111111111131', 'insert', '2', '12345');

commit;

--合同状态为审批中
insert into t_cust_contract
  (fid, fcontractbegindate, fcontractenddate, fcontractstatus)
values
  (111111111111132, sysdate + 5, sysdate + 30, '0');
commit;

insert into t_cust_contract
  (fid, fcontractbegindate, fcontractenddate, fcontractstatus)
values
  (111111111111135, trunc(sysdate) - 50, trunc(sysdate) + 50, '1');
commit;


select *
  from t_cust_contract
 where fcustid = (select fid
                    from t_cust_custbasedata
                   where fcustnumber = '20110303-06048042');
                   
                
 select * from t_cust_contract where fid='1E19';   
select * from t_cust_contractoperatorlog where fcontractid='9999999';


select * from t_cust_contract where fid='400052389';
select * from t_cust_contractdept where fcontractid='400285276';
select * from t_cust_contractdept where fdeptId='1';
update t_cust_contractdept set fisdept = 0 where fid=400052389;

select * from t_org_department where fid = '1';

select * from t_cust_contract where fid='400285276';
select * from t_cust_contractoperatorlog where fcontractid='400285276';
update t_cust_contractoperatorlog set fapprovalstate = '2' where fid = 35056;
commit;

--合同绑定回调方法 

select * from t_cust_contractoperatorlog where fcontractid='400283211'; 
update t_cust_contract set fcontractbegindate = sysdate + 111111 where fid=400283211;
commit;
select * from t_cust_contract where fid='400283211';

select * from t_cust_contractdept where fid='400052451';

select * from t_org_department where fid='11088';
commit;

select * from t_cust_custbasedata where fid='1000001';
select * from t_cust_contract where fcustid='1000001';
insert into t_cust_contract
  (fid, fcontractbegindate, fcontractenddate, fcontractstatus,fcustid)
values
  (111111111111155, trunc(sysdate) + 50, trunc(sysdate)+100, '1',1000001);
delete from t_cust_contract where fid=111111111111155;
 commit;

select * from t_cust_contractoperatorlog where fid = '35136';
update t_cust_contractoperatorlog set fapprovalstate = '2' where fid=35122;
commit;

select * from t_cust_contractoperatorlog where foperatortype='changeSign' and fcontractid='400313773';
select * from t_cust_contract where fid='400313773';
commit;

update t_cust_contract
   set fcontractbegindate = to_date('2012-08-01', 'yyyy-mm-dd')
 where fid = '400359171';


update t_cust_contract set fcontractbegindate = to_date('2012-08-01','yyyy-mm-dd') where fid= '400359171';

select * from t_cust_contract where fid='400359171';

