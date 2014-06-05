create or replace package PG_COU_COUPON is
   /*
   author : ZhouYuan
   time : 2012-12-12
   version : 1.0
   discribe : 用于手动生成优惠券,自动发送短信的存储过程
   */
  -- Author  : ADMINISTRATOR
  -- Created : 2012-12-12 10:54:54
  -- Purpose : 用于手动生成优惠券,自动发送短信的存储过程
  
   /*
   author : ZhouYuan
   time : 2012-12-12
   version : 1.0
   discribe : 用于生成1970年1月1日到当前的毫秒数
   */  
   function FN_DATE_TO_NUMBER( i_date in timestamp) return number;
   /*
   author : ZhouYuan
   time : 2012-12-12
   version : 1.0
   discribe : 用于生成优惠券编码的函数
   */  
   function FN_COU_CREATECOUPON_NUM return VARCHAR2;
   /*
   author : ZhouYuan
   time : 2012-12-12
   version : 1.0
   discribe : 用于自动任务生成手动优惠券
   */  
   procedure SP_BACKGROUND_CREATE_COUPON( i_coupontype T_COU_MARKETPLAN.FCOUPONTYPE%TYPE,o_flag out number);
   /*
   author : ZhouYuan
   time : 2012-12-12
   version : 1.0
   discribe : 生成自动券并生成相应的短信发送信息
   */ 
   procedure SP_AUTO_SEND_MSG( 
       i_senddeptcode T_COU_CELLPHONE_MSG_INFO.FSEND_STANDARD_DEPT_CODE%type,
       i_sendempcode T_COU_CELLPHONE_MSG_INFO.FSENDER_EMP_CODE%TYPE,
       o_flag out number);
   /*
   author : ZhouYuan
   time : 2012-12-12
   version : 1.0
   discribe : 创建手动券
   */ 
   procedure SP_CREATE_HAND_COUPON( 
              i_size in number,
              i_marketplanid in T_COU_MARKETPLAN.FID%TYPE,
              i_useruleid in T_COU_COUPONRULE.FID%TYPE,
              i_couponvalue in T_COU_COUPON.FVALUE%TYPE,
              o_flag out number);
end PG_COU_COUPON;
/
create or replace package body PG_COU_COUPON 

is
   /*
   author : ZhouYuan
   time : 2012-12-12
   version : 1.0
   discribe : 用于生成1970年1月1日到当前的毫秒数
   */  
   function FN_DATE_TO_NUMBER( i_date in timestamp)
   return number
   is
    l_number number; --返回值,返回计算结果
   begin
    l_number := sysdate - to_date('1970-01-01','yyyy-mm-dd'); --计算毫秒数
    return l_number;
   end FN_DATE_TO_NUMBER;
   
   
   
    /*
    author : ZhouYuan
    time : 2012-12-12
    version : 1.0
    discribe : 用于生成优惠券编码的函数
    */  
   function FN_COU_CREATECOUPON_NUM 
   return VARCHAR2
   is
    TYPE rang_array IS TABLE OF VARCHAR2(1) INDEX BY BINARY_INTEGER; --定义一个table类型
    char_array rang_array; --定义上述自定义table类型的变量
    org_message T_COU_COUPON_ORG_MESSAGE%ROWTYPE; 
    v_current VARCHAR2(12); --用于存放计算couponnum的中间8位数
    v_couponnum VARCHAR2(25);--用于存放生成的优惠券编码
    v_verify number := 0; --计算校验位
    v_temp VARCHAR2(1); --临时数据,用于交换
    l_flag number := 0; --判断优惠券编码是否大于初始值,大于0,小于1
    e_nomorecoupon exception; --异常类 用于抛出异常:优惠券生成上限,不能生成更多优惠券
    pragma exception_init(e_nomorecoupon,-21069); --绑定异常编码
    pragma autonomous_transaction;
  begin
         --查询优惠券信息表,查询初始值,当前值,和时间
         SELECT * INTO org_message FROM T_COU_COUPON_ORG_MESSAGE WHERE ROWNUM = 1 FOR UPDATE; 
         --如果表中的时间小于当前时间更新表中信息
         if to_char(org_message.ORG_DATE,'yymmdd') < to_char(sysdate,'yymmdd') then
            update T_COU_COUPON_ORG_MESSAGE set ORG_DATE=sysdate,ORG_NUMBER=substr(FN_DATE_TO_NUMBER(sysdate),-8,8),ORG_CURRENT=substr(FN_DATE_TO_NUMBER(sysdate),-8,8)
            where rownum =1 RETURNING ORG_DATE,ORG_NUMBER,ORG_CURRENT INTO org_message;
            --重新查询一次
            --SELECT * INTO org_message FROM T_COU_COUPON_ORG_MESSAGE WHERE ROWNUM = 1;
         end if;
         --如果当前数字小于初始值,将标志位置1
         if org_message.org_number > org_message.org_current then
            l_flag := 1;
         end if;
          --将当前值加一个随机的20位数字
          v_current := trunc(org_message.ORG_CURRENT+DBMS_RANDOM.VALUE(1,20));
          --如果计算结果大于8位数字则截取后8位数字
          if length(v_current) >= 8 then
              v_current := to_char(substr( v_current ,-8,8),'00000000');
          else
              --如果不足8位,则在其前补0
              v_current := to_char(v_current ,'00000000');
          end if;
              --去掉其前后空格
              v_current := trim(v_current);
         --判断是否还能生成更多优惠券
         if (org_message.org_number < v_current and l_flag = 0)or(org_message.org_number > v_current and l_flag = 1)then
          for v_i in 1 .. length(v_current) loop
               char_array(v_i) := SUBSTR(v_current,v_i,1);
               --计算校验位
               v_verify := v_verify + SUBSTR(v_current,v_i,1); 
      
          end loop;
           --对生成的优惠券信息做4次交换
           v_temp := char_array(7);
           char_array(7) := char_array(2);
           char_array(2) := v_temp;
           v_temp := char_array(6);
           char_array(6) := char_array(2);
           char_array(2) := v_temp; 
           v_temp := char_array(5);
           char_array(5) := char_array(7);
           char_array(7) := v_temp; 
           v_temp := char_array(1);
           char_array(1) := char_array(4);
           char_array(4) := v_temp;
  
           v_couponnum := '';
           --拼装优惠券编码
           for v_i in 1 .. 8 loop
               v_couponnum := v_couponnum || char_array(v_i);
           end loop;
           v_couponnum := to_char(sysdate,'yymmdd')||v_couponnum||substr(v_verify,-1,1);
           update t_cou_coupon_org_message set ORG_CURRENT = v_current;
           commit;
           return v_couponnum;
           else 
                 raise e_nomorecoupon; 
           end if;
           exception
                 when no_data_found then --没有找到数据插入
                    INSERT INTO T_COU_COUPON_ORG_MESSAGE( ORG_DATE,ORG_NUMBER,ORG_CURRENT ) 
                           VALUES( SYSDATE,PG_COU_COUPON.FN_DATE_TO_NUMBER(SYSDATE),
                                   PG_COU_COUPON.FN_DATE_TO_NUMBER(SYSDATE));
                    COMMIT;
                    when e_nomorecoupon then
                         ROLLBACK;
                         raise e_nomorecoupon;
                 when others then
                    ROLLBACK;
  end FN_COU_CREATECOUPON_NUM;
    /*
    author : ZhouYuan
    time : 2012-12-12
    version : 1.0
    discribe : 用于发送短信生成短信发送信息
    */  
  function fn_create_send_msg(
       i_headmsg in T_COU_COUPONRULE.Fsms%type, --优惠券用户自定义信息
       i_couponvalue in T_COU_COUPON.Fvalue%type, --优惠券面值
       i_couponnumber in T_COU_COUPON.Fnumber%type, --优惠券编码
       i_endtime in T_COU_COUPONRULE.Fendtime%type) --使用截止时间
  return varchar2
  is
        v_msg varchar2(500);
        begin
        --生成短信完整信息
        v_msg := i_headmsg||' '||i_couponvalue||' 元优惠券,编码: '||i_couponnumber||' ,有效期至 '||
               to_char(i_endtime,'yy')||'年'||to_char(i_endtime,'mm')||'月'||to_char(i_endtime,'dd')||'日'||' ，用券详情请咨询当地营业部!';
        return v_msg;
  end;
  
    /*
    author : ZhouYuan
    time : 2012-12-12
    version : 1.0
    discribe : 用于创建优惠券
    */  
    procedure sp_create_coupon(
              i_size in number,--创建的条数
              i_marketplanid in T_COU_COUPON.Fmarketplanid%TYPE, --营销计划ID
              i_typeid in T_COU_COUPON.Ftypeid%TYPE, --优惠券类型
              i_createruleid in T_COU_COUPON.Fcreateruleid%type, --创建规则
              i_useruleid in T_COU_COUPON.Fuseruleid%type, --使用规则
              i_status in T_COU_COUPON.Fstatus%type, --优惠券状态
              i_underdept in T_COU_COUPON.Funderdept%type, --优惠券归属部门
              i_sendtelphone in T_COU_COUPON.Fsendtelphone%type, --发送手机号
              i_usetelphone in T_COU_COUPON.Fusetelphone%type, --使用手机号
              i_sourcewbnumber in T_COU_COUPON.Fsourcewbnumber%type, --来源运单编码
              i_sourcewbvalue in T_COU_COUPON.Fsourcewbvalue%type, --来源运单金额
              i_usewbnumber in T_COU_COUPON.Fusewbnumber%type, --使用运单金额
              i_usewbvalue in T_COU_COUPON.Fusewbvalue%type, --使用运单金额
              i_couponvalue in T_COU_COUPON.Fvalue%type, --优惠券金额
              o_flag out number)
    is
     -- v_couponnumber T_COU_COUPON.FNUMBER%TYPE;--生成优惠券编码 
              type coupon_table_type is table of 
                   T_COU_COUPON%ROWTYPE index by binary_integer; --创建一个索引表类型为T_COU_COUPON
              coupon_table coupon_table_type;      
    begin     
              o_flag := 0;
              for v_i in 1 .. i_size loop
                 coupon_table(v_i).fid := SEQ_ID_COUPON.NEXTVAL;
                 coupon_table(v_i).fnumber :=  PG_COU_COUPON.FN_COU_CREATECOUPON_NUM;
                 coupon_table(v_i).fmarketplanid := i_marketplanid;
                 coupon_table(v_i).ftypeid := i_typeid;
                 coupon_table(v_i).fcreateruleid := i_createruleid;
                 coupon_table(v_i).fuseruleid := i_useruleid;
                 coupon_table(v_i).fstatus := i_status;
                 coupon_table(v_i).funderdept := i_underdept;
                 coupon_table(v_i).fsendtelphone := i_sendtelphone;
                 coupon_table(v_i).fusetelphone := i_usetelphone;
                 coupon_table(v_i).fsourcewbnumber := i_sourcewbnumber;
                 coupon_table(v_i).fsourcewbvalue := i_sourcewbvalue;
                 coupon_table(v_i).fusewbnumber := i_usewbnumber;
                 coupon_table(v_i).fusewbvalue := i_usewbnumber;
                 coupon_table(v_i).fvalue := i_couponvalue;
                 coupon_table(v_i).fsmstimes := 0;
                 coupon_table(v_i).fsendtime := sysdate;
                 coupon_table(v_i).fcreatetime := sysdate;
              end loop;

              forall v_i in coupon_table.first .. coupon_table.last 
                     insert into t_cou_coupon values coupon_table(v_i);

              o_flag := 1;
    exception
         when others then 
              ROLLBACK;

    end sp_create_coupon; 
    /*
    author : ZhouYuan
    time : 2012-12-12
    version : 1.0
    discribe : 用于发送短信
    */ 
  procedure sp_create_send_msg(
       i_couponnumber in T_COU_CELLPHONE_MSG_INFO.Fcounpon_Number%type,--优惠券编码
       i_phonenumber in T_COU_CELLPHONE_MSG_INFO.Fphone_Number%type, --发送手机号
       i_msgcontent in T_COU_CELLPHONE_MSG_INFO.Fmsgcontent%type, --发送短信内容
       i_sendstandarddept in T_COU_CELLPHONE_MSG_INFO.Fsend_Standard_Dept_Code%type, --发送部门
       i_senderempcode in T_COU_CELLPHONE_MSG_INFO.Fsender_Emp_Code%type, --发送柜员
       i_sended in T_COU_CELLPHONE_MSG_INFO.Fis_Sended%type --发送标识
       )
   is 
   begin
       insert into T_COU_CELLPHONE_MSG_INFO(
              FID,
              FCOUNPON_NUMBER,
              FPHONE_NUMBER,
              FMSGCONTENT,
              FSEND_STANDARD_DEPT_CODE,
              FSENDER_EMP_CODE,
              FIS_SENDED,
              FCREATETIME
        )VALUES(
              SEQ_T_COU_PHONE_MSG_INFO.NEXTVAL,
              i_couponnumber,
              i_phonenumber,
              i_msgcontent,
              i_sendstandarddept,
              i_senderempcode,
              i_sended,
              sysdate    
        );
    end; 
    
    /*
    author : ZhouYuan
    time : 2012-12-12
    version : 1.0
    discribe : 用于自动券生成发送短信
    */ 
    procedure sp_auto_send_msg(
       i_senddeptcode T_COU_CELLPHONE_MSG_INFO.FSEND_STANDARD_DEPT_CODE%type, --发送部门编码
       i_sendempcode T_COU_CELLPHONE_MSG_INFO.Fsender_Emp_Code%type, --发送柜员名称
       o_flag out number
    )
    is
    type record_waybill_type IS RECORD( 
        wbid T_COU_WAYBILL_COUPON.Fid%type, --运单ID
        waybillnumber T_COU_WAYBILL_COUPON.Fwaybillnumber%type, --运单编码
        marketplanid T_COU_MARKETPLAN.Fid%type, --营销计划ID
        marketplannumber T_COU_WAYBILL_COUPON.Fmarketplannumber%type, --营销计划编码
        coupontype T_COU_MARKETPLAN.Fcoupontype%type, --优惠券类型
        underdept T_COU_WAYBILL_COUPON.Funderdept%type, --优惠券归属部门
        sendtelphone T_COU_WAYBILL_COUPON.Fsendtelphone%type, --优惠券发送手机
        wbvalue T_COU_WAYBILL_COUPON.Fwbvalue%type, --运单开单金额
        couponvalue T_COU_WAYBILL_COUPON.Fvalue%type, --优惠券金额
        status T_COU_WAYBILL_COUPON.Fstatus%type, --运单状态
        createtime T_COU_WAYBILL_COUPON.Fcreatetime%type, --运单创建时间
        createruleid T_COU_RULECOUPONAUTO.Fid%type, --优惠券创建规则
        useruleid T_COU_COUPONRULE.Fid%type, --优惠券使用规则
        msgcontent T_COU_COUPONRULE.Fsms%type, --用户自定义信息
        begintime T_COU_COUPONRULE.Fbegintime%type, --使用开始时间
        endtime T_COU_COUPONRULE.Fendtime%type); --使用结束时间
        
        record_waybill record_waybill_type;
        
        type cursor_type is ref cursor return record_waybill_type; --定义游标用于查询运单中间表
        my_cursor cursor_type ;
        --创建优惠券索引表
        type coupon_table_type is table of T_COU_COUPON%ROWTYPE index by binary_integer;
        coupon_table coupon_table_type;
        --创建短信发送索引表
        type send_msg_table_type is table of T_COU_CELLPHONE_MSG_INFO%ROWTYPE index by binary_integer;
        send_msg_table send_msg_table_type;
        
        type wbid_table_type is table of T_COU_WAYBILL_COUPON.FID%TYPE index by binary_integer;
        wbid_table wbid_table_type;
        
        record_send_msg T_COU_CELLPHONE_MSG_INFO%ROWTYPE; --定义record类型,用于存放短信发送信息
       -- v_couponnumber T_COU_COUPON.Fnumber%type; --用于存放优惠券编码
        v_i number := 1;
   begin
   o_flag := 0;
   --查询运单中间表
   open my_cursor for SELECT
           B.FID wbid,
           B.FWAYBILLNUMBER waybillnumber,
           M.FID marketplanid,
           B.FMARKETPLANNUMBER marketplannumber,
           M.FCOUPONTYPE coupontype,
           B.FUNDERDEPT underdept,
           B.FSENDTELPHONE sendtelphone,
           B.FWBVALUE wbvalue,
           B.FVALUE couponvalue,
           B.FSTATUS status,
           B.FCREATETIME createtime,
           RU.FID createruleid,
           COU.FID useruleid,
           COU.FSMS msgcontent,
           COU.FBEGINTIME begintime,
           COU.FENDTIME endtime
        FROM
           T_COU_WAYBILL_COUPON B
        JOIN T_COU_MARKETPLAN M 
        ON B.FMARKETPLANNUMBER = M.FPLANNUMBER
        JOIN T_COU_COUPONRULE COU
        ON M.FID = COU.FMARKETPLANID
        JOIN T_COU_RULECOUPONAUTO RU
        ON COU.FMARKETPLANID = RU.FMARKETPLANID
        WHERE
           B.FSTATUS = '0'
        ORDER BY
          B.FMARKETPLANNUMBER;
      
    loop
          --读取查询信息
          fetch my_cursor into record_waybill;
          exit when my_cursor%notfound; 
              coupon_table(v_i).FID := SEQ_ID_COUPON.NEXTVAL;
              coupon_table(v_i).FMARKETPLANID := record_waybill.marketplanid;
              coupon_table(v_i).FNUMBER := FN_COU_CREATECOUPON_NUM;
              coupon_table(v_i).FTYPEID := 'AUTOCOUPON';
              coupon_table(v_i).FCREATERULEID := record_waybill.createruleid;
              coupon_table(v_i).FUSERULEID := record_waybill.useruleid;
              coupon_table(v_i).FSTATUS :='20';
              coupon_table(v_i).FUNDERDEPT := record_waybill.underdept;
              coupon_table(v_i).FSENDTELPHONE := record_waybill.sendtelphone;
              coupon_table(v_i).FSOURCEWBNUMBER := record_waybill.waybillnumber;
              coupon_table(v_i).FSOURCEWBVALUE :=  record_waybill.wbvalue;
              coupon_table(v_i).fsmstimes := 0;
              coupon_table(v_i).fsendtime := sysdate;
              coupon_table(v_i).fcreatetime := sysdate;
              coupon_table(v_i).FVALUE := record_waybill.couponvalue;
              
              
              send_msg_table(v_i).FID := SEQ_T_COU_PHONE_MSG_INFO.NEXTVAL;
              send_msg_table(v_i).FCOUNPON_NUMBER := coupon_table(v_i).FNUMBER;
              send_msg_table(v_i).FPHONE_NUMBER := record_waybill.sendtelphone;
              send_msg_table(v_i).FMSGCONTENT := fn_create_send_msg(
                                                 record_waybill.msgcontent, --优惠券用户自定义信息
                                                 record_waybill.couponvalue, --优惠券面值
                                                 coupon_table(v_i).FNUMBER, --优惠券编码
                                                 record_waybill.endtime);
              send_msg_table(v_i).FSEND_STANDARD_DEPT_CODE := i_senddeptcode;
              send_msg_table(v_i).FSENDER_EMP_CODE := i_sendempcode;
              send_msg_table(v_i).FIS_SENDED := '0';
              send_msg_table(v_i).FCREATETIME := sysdate;   
              
              wbid_table(v_i) := record_waybill.wbid; 
              v_i := v_i+1;
              if coupon_table.count= 50000 then
                 forall l_i in coupon_table.first .. coupon_table.last 
                        insert into t_cou_coupon values coupon_table(l_i);
                 forall l_j in send_msg_table.first .. send_msg_table.last
                        insert into t_cou_cellphone_msg_info values send_msg_table(l_j);
                 forall l_t in  wbid_table.first .. wbid_table.last
                        update T_COU_WAYBILL_COUPON set FSTATUS = '1' where FID = wbid_table(l_t); 
                 commit;
                 coupon_table.delete;
                 send_msg_table.delete;
                 wbid_table.delete;
              end if;                              
      
         end loop;
         forall l_i in coupon_table.first .. coupon_table.last 
                        insert into t_cou_coupon values coupon_table(l_i);
         forall l_j in send_msg_table.first .. send_msg_table.last
                        insert into t_cou_cellphone_msg_info values send_msg_table(l_j);
         forall l_t in  wbid_table.first .. wbid_table.last
                        update T_COU_WAYBILL_COUPON set FSTATUS = '1' where FID = wbid_table(l_t); 
         commit;
         coupon_table.delete;
         send_msg_table.delete;
         wbid_table.delete;
         close my_cursor;
         o_flag := 1;
    exception
         when others then 
              ROLLBACK;
    end sp_auto_send_msg;
    
    /*
    author : ZhouYuan
    time : 2012-12-12
    version : 1.0
    discribe : 用于创建手动优惠券
    */ 
    
    procedure sp_background_create_coupon( i_coupontype T_COU_MARKETPLAN.FCOUPONTYPE%type,o_flag out number)
is
   type record_hand_message is RECORD(
        marketplanid T_COU_MARKETPLAN.FID%TYPE,--营销计划ID 
        savetime T_COU_MARKETPLAN.FSAVETIME%TYPE,--保存时间
        createdate T_COU_MARKETPLAN.FCREATETIME%TYPE,--创建时间
        createuser T_COU_MARKETPLAN.FCREATEUSERID%TYPE,--创建人
        modifydate T_COU_MARKETPLAN.FLASTUPDATTIME%TYPE,--修改时间
        modifyuser T_COU_MARKETPLAN.FLASTMODIFYUSERID%TYPE,--修改人
        modifyusername T_COU_MARKETPLAN.FMODIFYUSERNAME%TYPE,--修改人姓名
        plannumber T_COU_MARKETPLAN.FPLANNUMBER%TYPE,--营销计划编码
        planname T_COU_MARKETPLAN.FPLANNAME%TYPE,--营销计划名称
        marketstatus T_COU_MARKETPLAN.FSTATUS%TYPE,--营销计划状态
        coupontype T_COU_MARKETPLAN.FCOUPONTYPE%TYPE,--优惠券类型
        createruleid T_COU_RULECOUPONHAND.FID%TYPE,--创建规则
        typeid T_COU_RULECOUPONHAND.FTYPEID%TYPE,--创建规则ID
        couponvalue T_COU_RULECOUPONHAND.FVALUE%TYPE,--优惠券面值
        useruleid T_COU_COUPONRULE.FID%TYPE,--使用规则ID
        quantity T_COU_RULECOUPONHAND.FQUANTITY%TYPE,--优惠券总值
        couponcreatenum number);--要创建的数目
   record_hand record_hand_message;
   type cursor_hand_message_type is ref cursor;
   cursor_hand_message cursor_hand_message_type;

    type coupon_table_type is table of 
                   T_COU_COUPON%ROWTYPE index by binary_integer; --创建一个索引表类型为T_COU_COUPON
    coupon_table coupon_table_type;    
begin
   o_flag := 0;
   --查询营销计划信息
   open cursor_hand_message for SELECT
          M.FID marketplanid,
          M.FSAVETIME savetime,
          M.FCREATETIME createdate,
          M.FCREATEUSERID createuser,
          M.FLASTUPDATTIME modifydate,
          M.FLASTMODIFYUSERID modifyuser,
          M.FMODIFYUSERNAME modifyusername,
          M.FPLANNUMBER plannumber,
          M.FPLANNAME planname,
          M.FSTATUS marketstatus,
          M.FCOUPONTYPE coupontype,
          R.FID createruleid,
          R.FTYPEID typeid,
          R.FVALUE couponvalue,
          COU.FID useruleid,
          NVL(R.FQUANTITY,0) quantity,
          NVL((NVL(R.FQUANTITY,0) - NVL(CCN.COUPONCREATENUM,0)),0) couponcreatenum
            FROM T_COU_MARKETPLAN M
            JOIN T_COU_RULECOUPONHAND R 
            ON M.FID = R.FMARKETPLANID
            JOIN T_COU_COUPONRULE COU
            ON R.FMARKETPLANID = COU.FMARKETPLANID
            LEFT JOIN
            (SELECT NVL(COUNT(1),0) COUPONCREATENUM,FMARKETPLANID FMARKETPLANID 
              FROM T_COU_COUPON
              GROUP BY FMARKETPLANID
             )  CCN
             ON M.FID=CCN.FMARKETPLANID
           WHERE M.FCOUPONTYPE = i_coupontype
          AND R.FTYPEID IN ('NOCREATE','FORSCHEDUAL' )
         -- AND ROWNUM = 1
          AND NVL((NVL(R.FQUANTITY,0) - NVL(CCN.COUPONCREATENUM,0)),0)>0;
       loop
           --读取查询信息
           fetch cursor_hand_message into record_hand;
           exit when cursor_hand_message%notfound;
           sp_create_coupon(

                     record_hand.couponcreatenum ,
                     record_hand.marketplanid ,
                     'HANDCOUPON',
                     record_hand.createruleid,
                     record_hand.useruleid ,
                     '10',
                     null,
                     null,
                     null,
                     null,
                     null,
                     null,
                     null,
                     record_hand.couponvalue,
                     o_flag );  
           update t_cou_rulecouponhand set FTYPEID = 'CREATED' where FMARKETPLANID = record_hand.marketplanid;
           commit; 
       end loop;
       close cursor_hand_message;
       o_flag := 1;
       exception
         when others then 
              ROLLBACK;
    end sp_background_create_coupon;
  
   /*
   author : ZhouYuan
   time : 2012-12-12
   version : 1.0
   discribe : 创建手动券
   */ 
    procedure sp_create_hand_coupon( 
    
              
              i_size in number,--创建的条数
              i_marketplanid in T_COU_MARKETPLAN.FID%TYPE,--营销计划ID
              i_useruleid in T_COU_COUPONRULE.FID%TYPE,--使用规则ID
              i_couponvalue in T_COU_COUPON.FVALUE%TYPE,--优惠券面值
              o_flag out number)
    is   
             -- v_couponnumber T_COU_COUPON.FNUMBER%TYPE;--生成优惠券编码 
              type coupon_table_type is table of 
                   T_COU_COUPON%ROWTYPE index by binary_integer; --创建一个索引表类型为T_COU_COUPON
              coupon_table coupon_table_type;  
              v_times number;    
    begin     
              sp_create_coupon(
                     i_size ,
                     i_marketplanid ,
                     'HANDCOUPON',
                     null,
                     i_useruleid ,
                     '10',
                     null,
                     null,
                     null,
                     null,
                     null,
                     null,
                     null,
                     i_couponvalue ,
                     o_flag );
                     commit;
    end;
    
   end;
/
