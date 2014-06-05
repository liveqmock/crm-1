/**.
 * <p>
 * 存放全局变量（1）控制页面调用那个DATA.JS（2）定义字段名称的全局变量<br/>
 * </p>
 * @author  张斌
 * @时间    2012-03-27
 * </p>
 */

/**.
 * <p>
 * 规范如此要求，判断'file'设置TEST为true/false<br/>
 * </p>
 * @author  张斌
 * @时间    2012-03-27
 * </p>
 */
CONFIG = (function(){
	var constants = {
			'TEST':false
	};
	if(window.location.protocol === 'file:')
	{
		constants.TEST = true;
	}
	
	return {
		get:function(name) {return constants[name];}
	};
})();


/**.
 * <p>
 * 如果要修改name或者发现与后台定义的不一样，那么在这里修改一次就OK，不用再区代码里面找很多很多了<br/>
 * </p>
 * @author  张斌
 * @时间    2012-03-08
 * </p>
 */
var CONFIGNAME = (function() {
    var constants = {
    		//最后审批时间
            'lastApprovedTime':'lastApprovedTime',
    		//APP大对象
            //唯一标示
            'id':'id',
            //关联的理赔ID
            'recompenseId':'recompenseId',
            //理赔单号
            'recompenseNum':'recompenseNum',
            //工作流号
            'workflowId':'workflowId',
            //------------OAWorkflow----------------
            //工作流号
        	'workflowNum':'workflowNum',
        	//工作流类型
        	'workflowType':'workflowType',
        	//工作流状态
        	'workflowStatus':'workflowStatus',
        	//提交人
        	'commiter':'commiter',
        	//提交时间
        	'commitDate':'commitDate',
        	//审核人
        	'auditPeople':'auditPeople',
        	//审核时间
        	'auditDate':'auditDate',
        	//审核意见
        	'auditopinion':'auditopinion',
        	//关联理赔单
        	'recompenseId':'recompenseId',
        	//关联付款单信息
        	'paymentBill':'paymentBill',
            //----------------OAWorkflow----------
            //状态持续时间
            'statusDuration':'statusDuration',
            //运单对象
            'waybill':'waybill',
            //理赔类型
            'recompenseType':'recompenseType',
            //------------------waybill-----------------------------
            //运单ID
            'waybillId':'waybillId',
            //运单号
            'waybillNumber':'waybillNumber',
            //货物名称
            'goodsName':'goodsName',
            //运输类型
            'transType':'transType',
           //收货部门
            'receiveDept':'receiveDept',
            //保价金额
            'insurAmount':'insurAmount',
            //保价人
            'insured':'insured',
            //联系电话
            'telephone':'telephone',
            //始发站
            'startStation':'startStation',
            //目的站
            'endStation':'endStation',
            //包装
            'packaging':'packaging',
            //体、重、件
            'pwv':'pwv',
            //发货日期
            'sendDate':'sendDate',
            //出发客户ID
            'leaveCustomerId':'leaveCustomerId',
            //到达客户ID
            'arriveCustomerId':'arriveCustomerId',
            //--------------------waybill------------------------------------
            //创建时间
            'createDate':'createDate',
            //创建人
            'createUser':'createUser',
            //修改时间
            'modifyDate':'modifyDate',
            //修改人
            'modifyUser':'modifyUser',
            //理赔方式
            'recompenseMethod':'recompenseMethod',
            //客户对象
            'customer':'customer',
            //--------------------customer---------------------------------
            //联系人姓名
            'contactName':'contactName',
            //联系人手机
            'contactPhone':'contactPhone',
            //联系人电话
            'contactTel':'contactTel',
            //客户编号
            'customerNum':'customerNum',
            //客户名称
            'customerName':'customerName',
            
            
            //---------------------customer-----------------------------------
            //正常理赔的索赔方
            'claimParty':'claimParty',
            //公司名称
            'companyName':'companyName',
            //公司传真
            'companyFax':'companyFax',
            //公司联系电话
            'companyPhone':'companyPhone',
            //理赔金额
            'recompenseAmount':'recompenseAmount',
            //正常理赔金额
            'normalAmount':'normalAmount',
            //实际理赔金额
            'realAmount':'realAmount',
            //费用说明
            'costExplain':'costExplain',
            //出险类型
            'insurType':'insurType',
            //出险数量
            'insurQuantity':'insurQuantity',
            //所属区域ID
            'deptId':'deptId',
            //所属区域名称
            'deptName':'deptName',
            //出险日期
            'insurDate':'insurDate',
            //报案人
            'reportMan':'reportMan',
            //报案部门
            'reportDept':'reportDept',
            //付款单对象
            'paymentBill':'paymentBill',
            //------------------------------paymentBill--------------------------
          //所属财务部
        	'belongfinance':'belongfinance',
        	// 冲应收
        	'receivable':'receivable',
        	// 冲应金额
        	'receivableAmount':'receivableAmount',
        	// 冲到付
        	'freightCollect':'freightCollect',
        	// 冲到金额
        	'freightCollectAmount':'freightCollectAmount',
        	// 付款方式
        	'paymentType':'paymentType',
        	// 付款金额
        	'paymentAmount':'paymentAmount',
        	//银行账户信息
        	'bankAccount':'bankAccount',
        	//------bankAccount---
        	// 银行
        	'bankName':'bankName',
        	// 支银行
        	'branchName':'branchName',
        	// 开户名
        	'openName':'openName',
        	// 账号
        	'account':'account',
        	// 手机号
        	'mobile':'mobile',
        	// 省
        	'province':'province',
        	// 市
        	'city':'city',
        	
        	
            //--------------------------------paymentBill------------------------------
             //报案时间
            'reportDate':'reportDate',
            //状态
            'status':'status',
            //操作人
            'operator':'operator',
            //追偿信息对象
            'recalledCom':'recalledCom',
            //-------------------recalledCom-------------------
            // 追偿日期
        	'recoveryTime':'recoveryTime',
        	// 追偿部门id
        	'deptId':'deptId',
        	//追偿部门名称
        	'deptName':'deptName',
        	// 暂扣金额
        	'suspendedAmount':'suspendedAmount',
        	// 赔回金额
        	'compensateBackAmount':'compensateBackAmount',
        	// 赔回日期
        	'compensateBackTime':'compensateBackTime',
        	// 返回暂扣款
        	'returnDeductions':'returnDeductions',
        	// 考核奖励
        	'assessmentReward':'assessmentReward',
            //-----------------recalledCom----------------------
            //对赔信息对象
            'overpay':'overpay',
            //多赔标记
            'overpayFlag':'overpayFlag',
            //财务部门
            'finance':'finance',
            //冲账金额
            'balanceAmount':'balanceAmount',
            //list-----------------------------
            //出险信息LIST
            'issueItemList':'issueItemList',
            //------------------issueItemList------------------------
            //出险类型
            'insurType':'insurType',
            //出险数量
            'quality':'quality',
            //描述
            'description':'description',
            //---------------------issueItemList-------------------------
            // 货物交易List
            'goodsTransList':'goodsTransList',
            //----------------goodsTransList-------------------------------
            //货物名称
            'goodsName':'goodsName',
            //数量
            'quality':'quality',
            //单价
            'price':'price',
            //实际价值
            'realPrice':'realPrice',
            //出险类型
            'insurType':'insurType',
            //信息描述
            'description':'description',
            //理赔数量
            'number':'number',
            //理赔金额
            'amount':'amount',
            //是否需要理赔
            'isClaims':'isClaims',
            //---------------------goodsTransList--------------------------
            //附件清单list
            'attachmentList':'attachmentList',
            //-------------------attachmentList------------------------------
            //货物名称
            'attachName':'attachName',
            //附件地址
            'attachAddress':'attachAddress',
            //----------------------attachmentList-----------------------------
            //部门费用list
            'deptChargeList':'deptChargeList',
            //----------------------deptChargeList-------------------------------
            //金额
            'amount':'amount',
            //关联部门ID
            'deptId':'deptId',
            //关联部门名称
            'deptName':'deptName',
            //----------------------deptChargeList-------------------------------
            //责任部门list
            'responsibleDeptList':'responsibleDeptList',
            //---------------------responsibleDeptList----------------------------------
            //关联部门ID
            'deptId':'deptId',
            //关联部门名称
            'deptName':'deptName',
            //----------------------responsibleDeptList---------------------------------
            //奖罚明细List
            'awardItemList':'awardItemList',
            //-----------------------awardItemList---------------------------------------
            //奖罚类型
            'awardType':'awardType',
            //部门ID
            'deptId':'deptId',
            //部门名称
            'deptName':'deptName',
            //用户ID
            'userId':'userId',
            //用户工号
            'userNumber':'userNumber',
            //奖罚对象类型
            'awardTargetType':'awardTargetType',
           /* //奖罚对象
            'awardTarget':'awardTarget',*/
            //奖罚金额
            'amount':'amount',
            //--------------------------awardItemList-----------------------------------
            //消息提醒List
            'messageReminderList':'messageReminderList',
            //---------------------------messageReminderList---------------------------
            //提醒人姓名
            'userName':'userName',
            //提醒人所在部门名称
            'deptName':'deptName',
            //提醒人手机号
            'phoneNum':'phoneNum',
            //提醒人工号
            'userNumber':'userNumber',
            //提醒方式
            'reminderMethod':'reminderMethod',
          /*  //提醒人
            'reminderMan':'reminderMan',*/
            //---------------------messageReminderList--------------------------------
            //跟进信息list
            'messageList':'messageList',
            //----------------------messageList------------------------------------
            //用户id
            'userId':'userId',
            //用户名称
            'userName':'userName',
            //跟进内容
            'content':'content',
            //跟进时间
            'createTime':'createTime',
            //-------------------------messageList--------------------------------
            //多赔申请记录list
            'overpayList':'overpayList',
            //-----------------------overpayList-----------------------------------
            // 多赔金额
            'overpayAmount':'overpayAmount',
            //多赔后的总金额
            'totalAmount':'totalAmount',
            //应收账款是否收回
            'recoverYszk':'recoverYszk',
            //多赔部门会计
            'overpayAccountDept':'deptAccount',
            //多赔所属事业部门
            'overpayBu':'overpayBu',
            //多赔原因
            'overpayReason':'overpayReason',
            //多赔工作流号
            'workNumber':'workNumber',
            //------------------------overpayList----------------------------------
            //冲账列表List
            'balanceList':'balanceList',
            //--------------------------balanceList-----------------------------------
            //单据号
            'billNum':'billNum',
            //未核销金额
            'usableAmount':'usableAmount',
            //冲账金额
            'balanceAmount':'balanceAmount',
            //剩余金额
            'surplusAmount':'surplusAmount',
            //-----------------------------balanceList---------------------------------
            //--------------------------没有的------------------------
            //显示的客户信息--------------
            //客户等级
            'degree':'degree',
            //客户名称
            'custName':'custName',
            //客户编号
            'custNumber':'custNumber',
            //显示的客户信息--------------
            //索赔原因
            'recompenseReason':'recompenseReason',
            //退回原因
            'rejectReason':'rejectReason',
             //退回时间
            'rejectTime':'rejectTime',
            //客户ID
            'custId':'custId',
            //状态持续时间
            'statusSustainTime':'statusSustainTime',
            
            //---------------user对象------------------------
            //'':'',
            //处理人
            'modifyUserName':'modifyUserName',
            //报案部门名称
            'reportDeptName':'reportDeptName',
            //报案人姓名
            'reportManName':'reportManName',
           

            
            
            
            //------------------------------------------
            //上报结束时间
            'reportEndTime'	:'reportEndTime',
            //审批时间
            'approveTime'	:'approveStartTime',
            //审批结束时间
            'approveEndTime'	:'approveEndTime',
            //理赔状态
            'recompenseState':'recompenseState',
            //所属大区
            'belongArea':'belongArea',
            	//处理人
            'handlePerson':'modifyUser',
            //--------------condition中的字段-------------------
            //搜索条件中的上报开始时间
            'conReportStartTime':'reportStartTime',
//            //上报结束时间
//            'conReportEndTime':'reportEndTime',
            //理赔方式
            'conRecompenseMethod':'recompenseMethod',
            //理赔创建开始时间
            'conCreateStartTime':'createStartTime',
            //理赔创建结束时间
            'conCreateEndTime':'createEndTime',
            //运单号
            'conWaybillNum':'waybillNum',
            //客户编号
            'conCustNum':'custNum',
            //订单号、差错号
            'orderWaybillNum':'orderWaybillNum',
            //客户等级
            'conCustomerLevel':'customerLevel',
            

//--------------------------------------onlineApply 命名统一-------------------------//
// --------onlineApply
 'oly_recompenseMethod'	:'recompenseMethod',	//1.理赔方式
 'oly_waybillNumber'		:'waybillNumber',		//2.运单号/差错编号    
 'oly_customerNum'		:'customerNum',			//3.客户编码
 'oly_insurAmount'		:'insurAmount',			//4.保价金额
 'oly_customerId'		:'customerId',			//5.索赔人
 'oly_recompenseAmount'	:'recompenseAmount',	//6.索赔金额
 'oly_recompenseReason'	:'recompenseReason',	//7.索赔原由
 'oly_status'			:'status',				//8.处理状态
 'oly_createDate'		:'createDate',			//9.上报时间
 'oly_rejectReason'		:'rejectReason',		//10.退回原因
 'oly_rejectTime'		:'rejectTime'	,		//11.退回时间
     
//---------大区	
 'RegionalSett_dept'	:'dept',				//1.大区
 'RegionalSett_user'	:'user',				//2.用户

// ---------付款弹窗
 'quotation'			:'quotation',			//1.报价人
 'impactAccounts'		:'impactAccounts',		//2.冲应收账款
 'flushingAmount'		:'flushingAmount',		//3.冲应收账款金额
 'rushedToPayment'		:'rushedToPayment',		//4.冲到付款
 'rushedToPaymentAmount':'rushedToPaymentAmount',//5.冲到付款金额
 'depositBank'			:'depositBank',			//6.开户银行
 'depositAccount'		:'depositAccount',		//7.开户账户
 'bankWhereProvinces'	:'bankWhereProvinces',	//8.银行所在省份
 'bankCity'				:'bankCity',			//9.银行所在城市
 'bankBranchName'		:'bankBranchName',		//10.开户支行名称
//--------------------------------------end-----------------------------------------// 

    	////////////////////////////OA差错描述//////////////////////////////
    	//差错编号
    	'accidentCode':'accidentCode',
    	//差错名称
    	'accidentName':'accidentName',
//    	处理结果描述
    	'processResult':'processResult',
//    	处理对象
    	'processTarget':'processTarget',
//    	奖罚说明
    	'rewardPunishmentDescription':'rewardPunishmentDescription',
//    	奖罚金额
    	'money':'money',
//    	处理时间
    	'processDate':'processDate',
//    	处理人
    	'processPerson':'processPerson',
//    	差错类型（差错类型对应的关系）
    	'accidentType':'accidentType',
//    	差错描述
    	'accidentDescription':'accidentDescription',
//		运输类型    	
    	'transCode':'transCode'
    };

        return {
           get: function(name) { return constants[name]; }
       };
   })();
