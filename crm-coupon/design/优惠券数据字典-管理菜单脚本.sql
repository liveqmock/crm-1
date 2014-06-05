-----------------------------------------------------------------------------
-----------------------管理菜单------------------------------------------------
insert into t_auth_function (FID, FFUNCTIONCODE, FFUNCTIONNAME, FURI, FFUNCTIONLEVEL, FPARENTCODE, FVALIDFLAG, FINVALIDDATE, FVALIDDATE, FDISPLAYORDER, FISCHECK, FFUNCTIONTYPE, FFUNCTIONDESC, FFUNCTIONSEQ, FCREATEUSERID, FCREATETIME, FLASTMODIFYUSERID, FLASTUPDATETIME)
values ((select max(t.fid)+1 from t_auth_function t), '01005300', '优惠券管理', '/coupon/coupon.action', 4, '01005', 1, sysdate, '', 5, 1, 3, '优惠券管理', '0/01/01005/01005300', 1, sysdate, 1, sysdate);

insert into t_auth_function (FID, FFUNCTIONCODE, FFUNCTIONNAME, FURI, FFUNCTIONLEVEL, FPARENTCODE, FVALIDFLAG, FINVALIDDATE, FVALIDDATE, FDISPLAYORDER, FISCHECK, FFUNCTIONTYPE, FFUNCTIONDESC, FFUNCTIONSEQ, FCREATEUSERID, FCREATETIME, FLASTMODIFYUSERID, FLASTUPDATETIME)
values ((select max(t.fid)+1 from t_auth_function t), '01005300003', '优惠券查询', '/coupon/couponList.action', 5, '01005300', 1, sysdate, '', 3, 1, 3, '优惠券查询', '0/01/01005/01005300/01005300003', 1, sysdate, 1, sysdate);

insert into t_auth_function (FID, FFUNCTIONCODE, FFUNCTIONNAME, FURI, FFUNCTIONLEVEL, FPARENTCODE, FVALIDFLAG, FINVALIDDATE, FVALIDDATE, FDISPLAYORDER, FISCHECK, FFUNCTIONTYPE, FFUNCTIONDESC, FFUNCTIONSEQ, FCREATEUSERID, FCREATETIME, FLASTMODIFYUSERID, FLASTUPDATETIME)
values ((select max(t.fid)+1 from t_auth_function t), '01005300002', '营销计划管理', '/coupon/couponManage.action', 5, '01005300', 1, sysdate, '', 2, 1, 3, '营销计划管理', '0/01/01005/01005300/01005300002', 1, sysdate, 1, sysdate);

insert into t_auth_function (FID, FFUNCTIONCODE, FFUNCTIONNAME, FURI, FFUNCTIONLEVEL, FPARENTCODE, FVALIDFLAG, FINVALIDDATE, FVALIDDATE, FDISPLAYORDER, FISCHECK, FFUNCTIONTYPE, FFUNCTIONDESC, FFUNCTIONSEQ, FCREATEUSERID, FCREATETIME, FLASTMODIFYUSERID, FLASTUPDATETIME)
values ((select max(t.fid)+1 from t_auth_function t), '01005300001', '营销计划新增', '/coupon/couponAdd.action', 5, '01005300', 1, sysdate, '', 1, 1, 3, '营销计划新增', '0/01/01005/01005300/01005300001', 1, sysdate, 1, sysdate);



-----------------------------------------------------------------------------
-----------------------数据字典-----------------------------------------------
iinsert into t_code_head (FID, FCODETYPE, FCODETYPEDESC, FCREATETIME, FCREATEUSERID, FLASTUPDATETIME, FLASTMODIFYUSERID)
values (seq_id_head.nextval, 'COUPON_COST_TYPE', '费用类型', sysdate, 1, sysdate, 1);


insert into t_code_detail (FID, FPARENTID, FCODETYPE, FCODE, FCODEDESC, FSTATUS, FCODESEQ, FLANGUAGE, FCREATETIME, FCREATEUSERID, FLASTUPDATETIME, FLASTMODIFYUSERID)
values (seq_id_detail.nextval, 0, 'COUPON_COST_TYPE', 'BILLING', '开单金额', 'A', 1, 'zh_CN', sysdate, 1, sysdate, 1);

insert into t_code_detail (FID, FPARENTID, FCODETYPE, FCODE, FCODEDESC, FSTATUS, FCODESEQ, FLANGUAGE, FCREATETIME, FCREATEUSERID, FLASTUPDATETIME, FLASTMODIFYUSERID)
values (seq_id_detail.nextval, 0, 'COUPON_COST_TYPE', 'FRT', '运费', 'A', 2, 'zh_CN', sysdate, 1, sysdate, 1);

insert into t_code_detail (FID, FPARENTID, FCODETYPE, FCODE, FCODEDESC, FSTATUS, FCODESEQ, FLANGUAGE, FCREATETIME, FCREATEUSERID, FLASTUPDATETIME, FLASTMODIFYUSERID)
values (seq_id_detail.nextval, 0, 'COUPON_COST_TYPE', 'BZ', '包装费', 'A', 3, 'zh_CN', sysdate, 1, sysdate, 1);

insert into t_code_detail (FID, FPARENTID, FCODETYPE, FCODE, FCODEDESC, FSTATUS, FCODESEQ, FLANGUAGE, FCREATETIME, FCREATEUSERID, FLASTUPDATETIME, FLASTMODIFYUSERID)
values (seq_id_detail.nextval, 0, 'COUPON_COST_TYPE', 'BF', '保价费', 'A', 4, 'zh_CN', sysdate, 1, sysdate, 1);

insert into t_code_detail (FID, FPARENTID, FCODETYPE, FCODE, FCODEDESC, FSTATUS, FCODESEQ, FLANGUAGE, FCREATETIME, FCREATEUSERID, FLASTUPDATETIME, FLASTMODIFYUSERID)
values (seq_id_detail.nextval, 0, 'COUPON_COST_TYPE', 'HK', '代收费', 'A', 5, 'zh_CN', sysdate, 1, sysdate, 1);

insert into t_code_detail (FID, FPARENTID, FCODETYPE, FCODE, FCODEDESC, FSTATUS, FCODESEQ, FLANGUAGE, FCREATETIME, FCREATEUSERID, FLASTUPDATETIME, FLASTMODIFYUSERID)
values (seq_id_detail.nextval, 0, 'COUPON_COST_TYPE', 'SH', '送货费', 'A', 6, 'zh_CN', sysdate, 1, sysdate, 1);

insert into t_code_detail (FID, FPARENTID, FCODETYPE, FCODE, FCODEDESC, FSTATUS, FCODESEQ, FLANGUAGE, FCREATETIME, FCREATEUSERID, FLASTUPDATETIME, FLASTMODIFYUSERID)
values (seq_id_detail.nextval, 0, 'COUPON_COST_TYPE', 'JH', '接货费', 'A', 7, 'zh_CN', sysdate, 1, sysdate, 1);





insert into t_code_head (FID, FCODETYPE, FCODETYPEDESC, FCREATETIME, FCREATEUSERID, FLASTUPDATETIME, FLASTMODIFYUSERID)
values (seq_id_head.nextval, 'FOSS_PRODUCT_TYPE', '产品类型', sysdate, 1, sysdate, 1);

insert into t_code_detail (FID, FPARENTID, FCODETYPE, FCODE, FCODEDESC, FSTATUS, FCODESEQ, FLANGUAGE, FCREATETIME, FCREATEUSERID, FLASTUPDATETIME, FLASTMODIFYUSERID)
values (seq_id_detail.nextval, 0, 'FOSS_PRODUCT_TYPE', 'FLF', '精准卡航', 'A', 1, 'zh_CN', sysdate, 1, sysdate, 1);

insert into t_code_detail (FID, FPARENTID, FCODETYPE, FCODE, FCODEDESC, FSTATUS, FCODESEQ, FLANGUAGE, FCREATETIME, FCREATEUSERID, FLASTUPDATETIME, FLASTMODIFYUSERID)
values (seq_id_detail.nextval, 0, 'FOSS_PRODUCT_TYPE', 'FSF', '精准城运', 'A', 2, 'zh_CN', sysdate, 1, sysdate, 1);

insert into t_code_detail (FID, FPARENTID, FCODETYPE, FCODE, FCODEDESC, FSTATUS, FCODESEQ, FLANGUAGE, FCREATETIME, FCREATEUSERID, FLASTUPDATETIME, FLASTMODIFYUSERID)
values (seq_id_detail.nextval, 0, 'FOSS_PRODUCT_TYPE', 'LRF', '精准汽运(长途)', 'A', 3, 'zh_CN', sysdate, 1, sysdate, 1);

insert into t_code_detail (FID, FPARENTID, FCODETYPE, FCODE, FCODEDESC, FSTATUS, FCODESEQ, FLANGUAGE, FCREATETIME, FCREATEUSERID, FLASTUPDATETIME, FLASTMODIFYUSERID)
values (seq_id_detail.nextval, 0, 'FOSS_PRODUCT_TYPE', 'SRF', '精准汽运(短途)', 'A', 4, 'zh_CN', sysdate, 1, sysdate, 1);

insert into t_code_detail (FID, FPARENTID, FCODETYPE, FCODE, FCODEDESC, FSTATUS, FCODESEQ, FLANGUAGE, FCREATETIME, FCREATEUSERID, FLASTUPDATETIME, FLASTMODIFYUSERID)
values (seq_id_detail.nextval, 0, 'FOSS_PRODUCT_TYPE', 'PLF', '汽运偏线', 'A', 5, 'zh_CN', sysdate, 1, sysdate, 1);

insert into t_code_detail (FID, FPARENTID, FCODETYPE, FCODE, FCODEDESC, FSTATUS, FCODESEQ, FLANGUAGE, FCREATETIME, FCREATEUSERID, FLASTUPDATETIME, FLASTMODIFYUSERID)
values (seq_id_detail.nextval, 0, 'FOSS_PRODUCT_TYPE', 'FV', '整车', 'A', 6, 'zh_CN', sysdate, 1, sysdate, 1);


insert into t_code_detail (FID, FPARENTID, FCODETYPE, FCODE, FCODEDESC, FSTATUS, FCODESEQ, FLANGUAGE, FCREATETIME, FCREATEUSERID, FLASTUPDATETIME, FLASTMODIFYUSERID)
values (seq_id_detail.nextval, 0, 'FOSS_PRODUCT_TYPE', 'AF', '精准空运', 'A', 7, 'zh_CN', sysdate, 1, sysdate, 1);


insert into t_code_head (FID, FCODETYPE, FCODETYPEDESC, FCREATETIME, FCREATEUSERID, FLASTUPDATETIME, FLASTMODIFYUSERID)
values (seq_id_head.nextval, 'COUPON_AMOUNT_TYPE', '金额要求', sysdate, 1, sysdate, 1);

insert into t_code_detail (FID, FPARENTID, FCODETYPE, FCODE, FCODEDESC, FSTATUS, FCODESEQ, FLANGUAGE, FCREATETIME, FCREATEUSERID, FLASTUPDATETIME, FLASTMODIFYUSERID)
values (seq_id_detail.nextval, 0, 'COUPON_AMOUNT_TYPE', 'FRT', '运费', 'A', 1, 'zh_CN', sysdate, 1, sysdate, 1);

insert into t_code_detail (FID, FPARENTID, FCODETYPE, FCODE, FCODEDESC, FSTATUS, FCODESEQ, FLANGUAGE, FCREATETIME, FCREATEUSERID, FLASTUPDATETIME, FLASTMODIFYUSERID)
values (seq_id_detail.nextval, 0, 'COUPON_AMOUNT_TYPE', 'BILLING', '开单金额', 'A', 2, 'zh_CN', sysdate, 1, sysdate, 1);




insert into t_code_head (FID, FCODETYPE, FCODETYPEDESC, FCREATETIME, FCREATEUSERID, FLASTUPDATETIME, FLASTMODIFYUSERID)
values (seq_id_head.nextval, 'COUPON_APPRECIATION_TYPE', '增值费', sysdate, 1, sysdate, 1);

insert into t_code_detail (FID, FPARENTID, FCODETYPE, FCODE, FCODEDESC, FSTATUS, FCODESEQ, FLANGUAGE, FCREATETIME, FCREATEUSERID, FLASTUPDATETIME, FLASTMODIFYUSERID)
values (seq_id_detail.nextval, 0, 'COUPON_APPRECIATION_TYPE', 'BZ', '包装费', 'A', 1, 'zh_CN', sysdate, 1, sysdate, 1);

insert into t_code_detail (FID, FPARENTID, FCODETYPE, FCODE, FCODEDESC, FSTATUS, FCODESEQ, FLANGUAGE, FCREATETIME, FCREATEUSERID, FLASTUPDATETIME, FLASTMODIFYUSERID)
values (seq_id_detail.nextval, 0, 'COUPON_APPRECIATION_TYPE', 'BF', '保价费', 'A', 2, 'zh_CN', sysdate, 1, sysdate, 1);

insert into t_code_detail (FID, FPARENTID, FCODETYPE, FCODE, FCODEDESC, FSTATUS, FCODESEQ, FLANGUAGE, FCREATETIME, FCREATEUSERID, FLASTUPDATETIME, FLASTMODIFYUSERID)
values (seq_id_detail.nextval, 0, 'COUPON_APPRECIATION_TYPE', 'HK', '代收费', 'A', 3, 'zh_CN', sysdate, 1, sysdate, 1);

insert into t_code_detail (FID, FPARENTID, FCODETYPE, FCODE, FCODEDESC, FSTATUS, FCODESEQ, FLANGUAGE, FCREATETIME, FCREATEUSERID, FLASTUPDATETIME, FLASTMODIFYUSERID)
values (seq_id_detail.nextval, 0, 'COUPON_APPRECIATION_TYPE', 'SH', '送货费', 'A', 4, 'zh_CN', sysdate, 1, sysdate, 1);

insert into t_code_detail (FID, FPARENTID, FCODETYPE, FCODE, FCODEDESC, FSTATUS, FCODESEQ, FLANGUAGE, FCREATETIME, FCREATEUSERID, FLASTUPDATETIME, FLASTMODIFYUSERID)
values (seq_id_detail.nextval, 0, 'COUPON_APPRECIATION_TYPE', 'JH', '接货费', 'A', 5, 'zh_CN', sysdate, 1, sysdate, 1);