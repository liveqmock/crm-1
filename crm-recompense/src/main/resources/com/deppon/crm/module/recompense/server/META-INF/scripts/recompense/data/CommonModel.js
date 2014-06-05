/**
 * 差错信息Model
 */
Ext.define('AccidentModel', {
			extend : 'Ext.data.Model',
			fields : [{
						// 差错编号
						name : CONFIGNAME.get('accidentCode')
					}, {
						// 差错名称
						name : CONFIGNAME.get('accidentName')
					}, {
						// 处理结果描述
						name : CONFIGNAME.get('processResult')
					}, {
						// 处理对象
						name : CONFIGNAME.get('processTarget')
					}, {
						// 奖罚说明
						name : CONFIGNAME.get('rewardPunishmentDescription')
					}, {
						// 奖罚金额
						name : CONFIGNAME.get('money')
					}, {
						// 处理时间
						name : CONFIGNAME.get('processDate'),
						type : 'Date',
						convert : DpUtil.changeLongToDate
					}, {
						// 处理人
						name : CONFIGNAME.get('processPerson')
					}, {
						// 差错类型（差错类型对应的关系）
						name : CONFIGNAME.get('accidentType')
					}, {
						// 差错描述
						name : CONFIGNAME.get('accidentDescription')
					}]
		});

/**
 * .
 * <p> // * 运单信息MODEL<br/>
 * <p>
 * 
 * @returns {WaybillModel}
 */
Ext.define('WaybillModel', {
			extend : 'Ext.data.Model',
			fields : [{
						// id
						name : CONFIGNAME.get('id')
					}, {
						// 理赔ID
						name : CONFIGNAME.get('recompenseId')
					}, {
						// 运单号/差错编号
						name : CONFIGNAME.get('waybillNumber')
					}, {
						// 包装
						name : CONFIGNAME.get('packaging')
					}, {
						// 货物名称
						name : CONFIGNAME.get('goodsName')
					}, {
						// 运输类型
						name : CONFIGNAME.get('transType')
					}, {
						// 保价人
						name : CONFIGNAME.get('insured')
					}, {
						// 联系电话
						name : CONFIGNAME.get('telephone')
					}, {
						// 始发站
						name : CONFIGNAME.get('startStation')
					}, {
						// 目的站
						name : CONFIGNAME.get('endStation')
					}, {
						// 发货日期
						name : CONFIGNAME.get('sendDate'),
						type : 'Date',
						defaultValue : null,
						convert : DpUtil.changeLongToDate
					}, {
						// 件重体
						name : CONFIGNAME.get('pwv')
					}, {
						// 保价金额
						name : CONFIGNAME.get('insurAmount')
					}, {
						// 出发客户ID
						name : CONFIGNAME.get('leaveCustomerId')
					}, {
						// 到达客户ID
						name : CONFIGNAME.get('arriveCustomerId')
					}, {
						// 收货部门
						name : CONFIGNAME.get('receiveDept')
					}]
		});

/**
 * .
 * <p> // * 出险描述MODEL<br/>
 * <p>
 * 
 * @returns {IssueDescriptionModel}
 */
Ext.define('IssueDescriptionModel', {
			extend : 'Ext.data.Model',
			fields : [{
						// id
						name : CONFIGNAME.get('id')
					}, {
						// 理赔ID
						name : CONFIGNAME.get('recompenseId')
					}, {
						// 出险类型
						name : CONFIGNAME.get('insurType')
					}, {
						// 出险数量
						name : CONFIGNAME.get('quality')
					}, {
						// 描述
						name : CONFIGNAME.get('description')
					}]
		});

/**
 * .
 * <p> // * 理赔清单MODEL<br/>
 * <p>
 * 
 * @returns {RecompenseList}
 */
// 货物名称
Ext.define('RecompenseList', {
			extend : 'Ext.data.Model',
			fields : [{
						// id
						name : CONFIGNAME.get('id')
					}, {
						// 理赔ID
						name : CONFIGNAME.get('recompenseId')
					}, {
						// 货物名称
						name : CONFIGNAME.get('goodsName')
					}, {
						// 数量
						name : CONFIGNAME.get('quality'),
						type : 'int'
					}, {
						// 单价
						name : CONFIGNAME.get('price'),
						type : 'float'
					}, {
						// 实际价值
						name : CONFIGNAME.get('realPrice'),
						type : 'float'
					}, {
						// 出险金额
						name : CONFIGNAME.get('amount'),
						type : 'float'
					}, {
						// 理赔数量
						name : CONFIGNAME.get('number'),
						type : 'int'
					}, {
						// 是否需要理赔
						name : CONFIGNAME.get('isClaims')
					}, {
						// 出险类型
						name : CONFIGNAME.get('insurType')
					}, {
						// 信息描述
						name : CONFIGNAME.get('description')
					}, {
						// 货物托运清单与理赔清单关联标志位
						name : 'relationFlag'
					}]
		});

/**
 * .
 * <p> // * 附件MODEL<br/>
 * <p>
 * 
 * @returns {AttachList}
 */
Ext.define('AttachList', {
			extend : 'Ext.data.Model',
			fields : [{
						// id
						name : CONFIGNAME.get('id')
					}, {
						// 理赔ID
						name : CONFIGNAME.get('recompenseId')
					}, {
						// 附件名称
						name : CONFIGNAME.get('attachName')
					}, {
						// 附件地址
						name : CONFIGNAME.get('attachAddress')
					}]
		});

/**
 * .
 * <p> // * 提交的APP MODEL<br/>
 * <p>
 * 
 * @returns {RecompenseApp}
 */
Ext.define('RecompenseApp', {
    extend: 'Ext.data.Model',
    fields : [{
    	//id
		name : CONFIGNAME.get('id')
	},{
		//理赔单号
		name :  CONFIGNAME.get('recompenseNum')
	},{
		//创建时间
		name :  CONFIGNAME.get('createDate'),
		type: 'Date',
		 defaultValue:null,
		 convert: DpUtil.changeLongToDate
    },{
    	//创建人
    	name : CONFIGNAME.get('createUser')
	},{
    	//修改时间
    	name : CONFIGNAME.get('modifyDate'),
    	type: 'Date',
		 defaultValue:null,
		 convert: DpUtil.changeLongToDate
	},{
    	//修改人
    	name : CONFIGNAME.get('modifyUser')
	},{
		//状态持续时间
    	name:'duration',
    	type:'int'
	},{
    	//工作流号
    	name : CONFIGNAME.get('workflowId'),
    	 defaultValue:null
	},{
    	//运单对象
    	name : CONFIGNAME.get('waybill')
	},{
    	//凭证号
    	name : 'waybill.waybillNumber'
	},{
    	//理赔类型
    	name : CONFIGNAME.get('recompenseType')
	},{
    	//理赔方式
    	name : CONFIGNAME.get('recompenseMethod')
	},{
    	//客户对象
    	name : CONFIGNAME.get('customer')
	},{
		//正常理赔的索赔方
    	name : CONFIGNAME.get('claimParty')
	},{
    	//公司名称
    	name : CONFIGNAME.get('companyName')
	},{
    	//公司传真
    	name : CONFIGNAME.get('companyFax')
	},{
    	//公司联系电话
    	name : CONFIGNAME.get('companyPhone')
	},{
    	//理赔金额(索赔金额)
    	name : CONFIGNAME.get('recompenseAmount'),
    	type:'float'
	},{
    	//正常理赔金额（理赔金额）
    	name : CONFIGNAME.get('normalAmount'),
    	type:'float'
	},{
    	//实际理赔金额
    	name : CONFIGNAME.get('realAmount'),
    	type:'float'
	},{
    	//费用说明
    	name : CONFIGNAME.get('costExplain')
	},{
    	//出险类型
    	name : CONFIGNAME.get('insurType')
	},{
    	//出险数量
    	name : CONFIGNAME.get('insurQuantity')
	},{
    	//所属区域
    	name : CONFIGNAME.get('deptId')
	},{
    	//所属区域名称
    	name : CONFIGNAME.get('deptName')
	},{
    	//报案人
    	name : CONFIGNAME.get('reportMan')
	},{
		//出险日期
    	name : CONFIGNAME.get('insurDate'),
    	type: 'Date',
		 defaultValue:null,
		 convert: DpUtil.changeLongToDate
	},{
    	//报案部门
    	name : CONFIGNAME.get('reportDept')
	},{
    	//报案时间
    	name : CONFIGNAME.get('reportDate'),
    	type: 'Date',
		 defaultValue:null,
		 convert: DpUtil.changeLongToDate
	},{
    	//付款单对象
    	name : CONFIGNAME.get('paymentBill'),
    	defaultValue:{}
	},{
    	//状态
    	name : CONFIGNAME.get('status')
	},{
    	//操作人
    	name : CONFIGNAME.get('operator')
	},{
    	//追偿信息对象
    	name : CONFIGNAME.get('recalledCom'),
    	defaultValue:{}
	},{
    	//多陪信息对象
    	name : CONFIGNAME.get('overpay'),
    	defaultValue:{}
	},{
    	//多赔标记
    	name : CONFIGNAME.get('overpayFlag')
	},{
    	//冲账金额
    	name : CONFIGNAME.get('balanceAmount'),
    	type:'float'
	},{
    	//财务部门对象
    	name : CONFIGNAME.get('finance'),
    	defaultValue:{}
	},{
    	//最后审批时间
    	name : CONFIGNAME.get('lastApprovedTime'),
    	type: 'Date',
		 defaultValue:null,
		 convert: DpUtil.changeLongToDate
	},{
    	//处理人
    	name : CONFIGNAME.get('modifyUserName')
	},{
		//报案部门名称
    	name : CONFIGNAME.get('reportDeptName')
	},{
		//报案人姓名
    	name : CONFIGNAME.get('reportManName')
	},{
		//收银员所在部门
    	name : CONFIGNAME.get('cashierDept')
	},{
		//金额确认部门
    	name : CONFIGNAME.get('confirmAmountDept')
	},{
		name : 'waybill.transType'
        },
    {name:'openName',type:'string'},/*开户名*/
    {name:'account',type:'string'}/*开户账号*/
	],
	 hasMany: [
	           {name: CONFIGNAME.get('issueItemList')},
	           {name: CONFIGNAME.get('goodsTransList')},
	           {name: CONFIGNAME.get('attachmentList')},
	           {name: CONFIGNAME.get('deptChargeList')},
	           {name: CONFIGNAME.get('responsibleDeptList')},
	           {name: CONFIGNAME.get('awardItemList')},
	           {name: CONFIGNAME.get('messageReminderList')},
	           {name: CONFIGNAME.get('messageList')},
	           {name: CONFIGNAME.get('overpayList')},
	           {name: CONFIGNAME.get('balanceList')}
	       ]
});
/**.
 * <p>
 * 
 * @returns {OverPayModel}
 */
Ext.define('OverPayModel', {
			extend : 'Ext.data.Model',
			fields : [{
						// id
						name : CONFIGNAME.get('id')
					}, {
						// 理赔ID
						name : CONFIGNAME.get('recompenseId')
					}, {
						// 多赔金额
						name : CONFIGNAME.get('overpayAmount'),
						type : 'float'
					}, {
						// 多赔后的总金额
						name : CONFIGNAME.get('totalAmount'),
						type : 'float'
					}, {
						// //应收账款是否收回
						name : CONFIGNAME.get('recoverYszk')
					}, {
						// 多赔部门会计
						name : CONFIGNAME.get('overpayAccountDept'),
						defaultValue : {}
					}, {
						// 多赔所属事业部门
						name : CONFIGNAME.get('overpayBu'),
						defaultValue : {}
					}, {
						// 多赔原因
						name : CONFIGNAME.get('overpayReason')
					}, {
						// //多赔工作流号
						name : CONFIGNAME.get('workNumber'),
						defaultValue : null
					},{
						name:'workflowNo'//CRM工作流号
					}]
		});

/**
 * .
 * <p>
 * 入部门费用 MODEL<br/>
 * <p>
 * 
 * @returns {DeptChargeModel}
 */
Ext.define('DeptChargeModel', {
			extend : 'Ext.data.Model',
			fields : [{
						// id
						name : CONFIGNAME.get('id')
					}, {
						// 理赔ID
						name : CONFIGNAME.get('recompenseId')
					}, {
						// 金额
						name : CONFIGNAME.get('amount'),
						type : 'float'
					}, {
						// 部门ID
						name : CONFIGNAME.get('deptId')
					}, {
						// //部门NAME
						name : CONFIGNAME.get('deptName')
					}]
		});

/**
 * .
 * <p>
 * 责任部门 MODEL<br/>
 * <p>
 * 
 * @returns {ResponsibleDeptModel}
 */
Ext.define('ResponsibleDeptModel', {
			extend : 'Ext.data.Model',
			fields : [{
						// id
						name : CONFIGNAME.get('id')
					}, {
						// 理赔ID
						name : CONFIGNAME.get('recompenseId')
					}, {
						// 部门ID
						name : CONFIGNAME.get('deptId')
					}, {
						// //部门NAME
						name : CONFIGNAME.get('deptName')
					}]
		});

/**
 * .
 * <p>
 * 奖罚明细MODEL<br/>
 * <p>
 * 
 * @returns {AwardItemModel}
 */
Ext.define('AwardItemModel', {
			extend : 'Ext.data.Model',
			fields : [{
						// id
						name : CONFIGNAME.get('id')
					}, {
						// 理赔ID
						name : CONFIGNAME.get('recompenseId')
					}, {
						// 奖罚类型
						name : CONFIGNAME.get('awardType')
					}, {
						// //部门Id
						name : CONFIGNAME.get('deptId')
					}, {
						// 奖罚金额
						name : CONFIGNAME.get('amount'),
						type : 'float'
					}, {
						// 部门Name
						name : CONFIGNAME.get('deptName')
					}, {
						// 职员ID
						name : CONFIGNAME.get('userId')
					}, {
						// 职员工号
						name : CONFIGNAME.get('userNumber')
					}, {
						// 职员姓名
						name : 'empName'
					}, {
						// 奖罚对象类型
						name : CONFIGNAME.get('awardTargetType')
					}]
		});

/**
 * .
 * <p>
 * 消息提醒 MODEL<br/>
 * <p>
 * 
 * @returns {MessageReminderModel}
 */
Ext.define('MessageReminderModel', {
			extend : 'Ext.data.Model',
			fields : [{
						// id
						name : CONFIGNAME.get('id')
					}, {
						// 理赔ID
						name : CONFIGNAME.get('recompenseId')
					}, {
						// 消息提醒人工号
						name : CONFIGNAME.get('userNumber')
					}, {
						// 消息提醒人手机号
						name : CONFIGNAME.get('phoneNum')
					}, {
						// 消息提醒人所在部门
						name : CONFIGNAME.get('deptName')
					}, {
						// 消息提醒人所在部门ID
						name : CONFIGNAME.get('deptId')
					}, {
						// 消息提醒人姓名
						name : CONFIGNAME.get('userName')
					}]
		});

/**
 * .
 * <p>
 * 追偿信息MODEL<br/>
 * <p>
 * 
 * @returns {RecalledComModel}
 */
Ext.define('RecalledComModel', {
			extend : 'Ext.data.Model',
			fields : [{
						// id
						name : CONFIGNAME.get('id')
					}, {
						// 理赔ID
						name : CONFIGNAME.get('recompenseId')
					}, {
						// 追偿日期
						name : CONFIGNAME.get('recoveryTime'),
						type : 'Date',
						defaultValue : null,
						convert : DpUtil.changeLongToDate
					}, {
						// 追偿部门ID
						name : CONFIGNAME.get('deptId'),
						type : 'int',
						defaultValue : -1
					}, {
						// 追偿部门name
						name : CONFIGNAME.get('deptName')
					}, {
						// 暂扣金额
						name : CONFIGNAME.get('suspendedAmount'),
						type : 'float'
					}, {
						// 赔回金额
						name : CONFIGNAME.get('compensateBackAmount'),
						type : 'float'
					}, {
						// 赔回日期
						name : CONFIGNAME.get('compensateBackTime'),
						type : 'Date',
						defaultValue : null,
						convert : DpUtil.changeLongToDate
					}, {
						// 返回暂扣款
						name : CONFIGNAME.get('returnDeductions'),
						type : 'float'
					}, {
						// 考核奖励
						name : CONFIGNAME.get('assessmentReward'),
						type : 'float'
					}]
		});
/**
 * .
 * <p>
 * 跟进信息 MODEL<br/>
 * <p>
 * 
 * @returns {MessageModel}
 */
Ext.define('MessageModel', {
			extend : 'Ext.data.Model',
			fields : [{
						// id
						name : CONFIGNAME.get('id')
					}, {
						// 跟进人ID
						name : CONFIGNAME.get('userId')
					}, {
						// 跟进人姓名
						name : CONFIGNAME.get('userName')
					}, {
						// 跟进内容
						name : CONFIGNAME.get('content')
					}, {
						// 跟进时间
						name : CONFIGNAME.get('createTime'),
						type : 'Date',
						defaultValue : null,
						convert : DpUtil.changeLongToDate
					}]
		});
/**
 * .
 * <p>
 * 账户信息 MODEL<br/>
 * <p>
 * 
 * @returns {BankAccountModel}
 */
Ext.define('BankAccountModel', {
			extend : 'Ext.data.Model',
			fields : [{
						// id
						name : CONFIGNAME.get('id')
					}, {
						// 开户名
						name : CONFIGNAME.get('openName')
					}, {
						// 银行
						name : CONFIGNAME.get('bankName')
					}, {
						// 支银行
						name : CONFIGNAME.get('branchName')
					}, {
						// 账号
						name : CONFIGNAME.get('account')
					}, {
						// 手机号
						name : CONFIGNAME.get('mobile')
					}, {
						// 省
						name : CONFIGNAME.get('province')
					}, {
						// 市
						name : CONFIGNAME.get('city')
					}]
		});
/**
 * .
 * <p>
 * 付款 MODEL<br/>
 * <p>
 * 
 * @returns {PaymentBillModel}
 */
Ext.define('PaymentBillModel', {
			extend : 'Ext.data.Model',
			fields : [{
						// id
						name : CONFIGNAME.get('id')
					}, {
						// 理赔ID
						name : CONFIGNAME.get('recompenseId')
					}, {
						// 所属财务部
						name : CONFIGNAME.get('belongfinance'),
						defaultValue : {}
					}, {
						// 冲应收
						name : CONFIGNAME.get('receivable')
					}, {
						// 冲应金额
						name : CONFIGNAME.get('receivableAmount'),
						type : 'float'
					}, {
						// 冲到付
						name : CONFIGNAME.get('freightCollect')
					}, {
						// 冲到金额
						name : CONFIGNAME.get('freightCollectAmount'),
						type : 'float'
					}, {
						// 付款方式
						name : CONFIGNAME.get('paymentType')
					}, {
						// 付款金额
						name : CONFIGNAME.get('paymentAmount'),
						type : 'float'
					}, {
						// 银行账户信息
						name : CONFIGNAME.get('bankAccount'),
						defaultValue : {}
					}]
		});

/**
 * .
 * <p>
 * 银行账户信息 MODEL<br/>
 * <p>
 * 
 * @returns {BankAccountModel}
 */
Ext.define('BankAccountModel', {
			extend : 'Ext.data.Model',
			fields : [{
						// id
						name : CONFIGNAME.get('id')
					}, {
						// 银行
						name : CONFIGNAME.get('bankName')
					}, {
						// 支银行
						name : CONFIGNAME.get('branchName')
					}, {
						// 开户名
						name : CONFIGNAME.get('openName')
					}, {
						// 账号
						name : CONFIGNAME.get('account')
					}, {
						// 手机号
						name : CONFIGNAME.get('mobile')
					}, {
						// 省
						name : CONFIGNAME.get('province')
					}, {
						// 市
						name : CONFIGNAME.get('city')
					}]
		});

/**
 * .
 * <p>
 * OA工作流MODEL<br/>
 * <p>
 * 
 * @returns {OAWorkFlowModel}
 */
Ext.define('OAWorkFlowModel', {
			extend : 'Ext.data.Model',
			fields : [{
						// id
						name : CONFIGNAME.get('id')
					}, {
						// 工作流号
						name : CONFIGNAME.get('workflowNum')
					}, {
						name : CONFIGNAME.get('workflowNo')
					}, {//加密后的工作流号
						name : CONFIGNAME.get('workflowNumEnc')
					}, {
						// 工作流类型
						name : CONFIGNAME.get('workflowType')
					}, {
						// 工作流状态
						name : CONFIGNAME.get('workflowStatus')
					}, {
						// 提交人
						name : CONFIGNAME.get('commiter'),
						defaultValue : {}
					}, {
						// 提交时间
						name : CONFIGNAME.get('commitDate'),
						type : 'Date',
						defaultValue : null,
						convert : DpUtil.changeLongToDate
					}, {
						// 审核人
						name : CONFIGNAME.get('auditPeople')
					}, {
						// 审核时间
						name : CONFIGNAME.get('auditDate')
					}, {
						// 审核意见
						name : CONFIGNAME.get('auditopinion')
					}, {
						// 关联理赔单
						name : CONFIGNAME.get('recompenseId')
					}, {
						// 关联付款单信息
						name : CONFIGNAME.get('paymentBill'),
						defaultValue : {}
					}]
		});
/*
 * 理赔受理搜索框后端返回数据 Model @author rock
 */
Ext.define('OnlineApplyModel', {
			extend : 'Ext.data.Model',
			fields : [{
				name : CONFIGNAME.get('id')
					// 1.id
				}, {
				name : CONFIGNAME.get('oly_recompenseMethod')
					// 1.理赔方式
				}, {
				name : CONFIGNAME.get('oly_waybillNumber')
					// 2.运单号/差错编号
				}, {
				name : CONFIGNAME.get('oly_customerNum')
					// 3.客户编号
				}, {
				name : CONFIGNAME.get('oly_insurAmount')
					// 4.保价金额
				}, {
				name : CONFIGNAME.get('oly_customerId')
					// 5.索赔人
				}, {
				name : CONFIGNAME.get('oly_recompenseAmount')
					// 6.索赔金额
				}, {
				name : CONFIGNAME.get('oly_recompenseReason')
					// 7.索赔原由
				}, {
				name : CONFIGNAME.get('oly_status')
					// 8.处理状态
				}, {
				name : CONFIGNAME.get('oly_createDate')
					// 9.上报时间
				}, {
				name : CONFIGNAME.get('oly_rejectReason')
					// 10.退回原因
				}, {
				name : CONFIGNAME.get('oly_rejectTime')
					// 11.退回时间
				},{
				name:'acceptDate',type:'date'
					//受理时间
				},{
				name:'acceptDays'
					//受理天数
				},{
				name:'acceptDeptName'
					//受理部门名字
				},{
				name:'belongsAreaName'
					//所属区域名字
				},{
				name:'transType'
					//运输类型
				},{
					//受理部门ID
				name:'applyDeptId'}]
		});
Ext.define('Condition', {
			extend : 'Ext.data.Model',
			id : 'searchCondition',
			fields : [{
						name : CONFIGNAME.get('reportDept')
					}, {
						name : CONFIGNAME.get('conReportStartTime'),
						type : 'date',
						convert : DpUtil.changeLongToDate,
						defaultValue : (new Date().setDate(new Date().getDate()
								- 31))
					}, {
						name : CONFIGNAME.get('reportEndTime'),
						type : 'date',
						convert : DpUtil.changeLongToDate,
						defaultValue : new Date()
					}, {
						name : CONFIGNAME.get('conRecompenseMethod')
					}, {
						name : CONFIGNAME.get('recompenseType')
					}, {
						name : CONFIGNAME.get('recompenseState')
					}, {
						name : CONFIGNAME.get('belongArea')
					}, {
						name : CONFIGNAME.get('approveTime'),
						type : 'date',
						convert : DpUtil.changeLongToDate
					}, {
						name : CONFIGNAME.get('approveEndTime'),
						type : 'date',
						convert : DpUtil.changeLongToDate
					}, {
						name : CONFIGNAME.get('conCustNum')
					}, {
						name : CONFIGNAME.get('custName')
					}, {
						name : CONFIGNAME.get('conCustomerLevel')
					}, {
						name : CONFIGNAME.get('orderWaybillNum'),
						defaultValue : i18n('i18n.recompense.orderNum')
					}, {
						name : CONFIGNAME.get('conWaybillNum')
					}, {
						name : CONFIGNAME.get('errorNum')
					},{
						name : 'beginDuration'
					},{
						name : 'endDuration'
					},{
						name : 'recompenseAmount'
					},{
						name : 'realAmount'
					}]
		});

Ext.define('Department', {
			extend : 'Ext.data.Model',
			id : 'department',
			fields : [{
						name : 'id'
					}, {
						name : 'deptName'
					}]
		});
Ext.define('OnlineApplyCondition', {
			extend : 'Ext.data.Model',
			id : 'onlineApplyConditionId',
			fields : [{//运单号
						name : 'waybillNumber'
					}, {//上报时间
						name : 'reportDate',
						type:'date'
					},{//上报时间到
						name : 'reportDateTo',
						type:'date'
					},{//受理天数
						name : 'acceptTime'
					},{//受理天数到
						name : 'acceptTimeTo'
					},{//状态
						name : 'acceptStatus'
					},{//受理部门
						name : 'acceptDept'
					},{//所属区域
						name : 'belongsArea'
					},{//索赔金额
						name : 'claimAmount'
					},{//运输类型
						name:'transType'
					}]
		});
/**
 * 银行查询条件的Model
 */
Ext.define('BankSearchConditionModel', {
			extend : 'Ext.data.Model',
			fields : ['province', 'city', 'accountBank', 'accountBranch',
					'provinceId', 'cityId', 'accountBankId', 'accountBranchId']
		});

/**
 * 索赔Model
 */
Ext.define('ClaimModel', {
			extend : 'Ext.data.Model',
			fields : [{
						name : 'id'
					}, {
						name : 'waybillNumber'// 凭证号
					}, {
						name : 'claimer'// 索赔方
					}, {
						name : 'linkMan'// 联系人
					}, {
						name : 'linkMobile'// 联系方式
					}, {
						name : 'insuranceValue'// 保价金额
					}, {
						name : 'claimPerson'// 索赔人
					}, {
						name : 'claimAmount'// 索赔金额
					}, {
						name : 'claimRequire'// 客户索赔要求
					}, {
						name : 'tranferCount'// 流转次数
					}, {
						name : 'firstRefuseTime'// 第一次拒绝时间
						,type: 'date',convert: DpUtil.changeLongToDate,defaultValue:null
					}, {
						name : 'claimStatus'// 索赔状态
					}, {
						name : 'reportDept'// 报案部门
					}, {
						name : 'reportDeptName'// 报案部门
					},{
						name : 'reporter'// 报案人
					},{
						name : 'reporterName'// 报案人
					},{
						name : 'processDept'// 当前处理部门
					},{
						name : 'processDeptName'// 当前处理部门
					},{
						name : 'processor'// 当前处理部门
					},{
						name : 'processorName'// 当前处理部门
					},{
						name : 'createDate'// 创建时间
					},{
						name : 'claimTime'// 索赔时间节点
					},{
						name : 'createUser'// 创建人
					}, {
						name : 'receiveDept' //收货部门
					}, {
						name : 'arrivedDept' //到达部门
					}, {
						name : 'shipper'  //到达收货人
					}, {
						name : 'consignee'//出发发货人

					}, {
						name : 'shipperPhone'  //到达收货人联系方式
					}, {
						name : 'consigneePhone'  //出发发货人联系方式
					},{
						name:'transType'//运输类型
					},{
						name:'showTime'//显示时间
					}]
		});

/**
 * 索赔操作日志
 */
Ext.define('ClaimOperateLogGridModel', {
			extend : 'Ext.data.Model',
			fields : [{
						name : 'operatorContent'// 操作日志内容
					}, {
						name : 'operatorTime'// 操作时间
						,type: 'date',convert: DpUtil.changeLongToDate,defaultValue:null
					}, {
						name : 'operator'// 操作人
					}, 
					{
						name : 'operatorName'//操作人名字
					},{
						name : 'operatorDept'// 操作部门
					},
					{
						name : 'operatorDeptName'// 操作部门名字
					},{
						name : 'claimId'// 关联的索赔Id
					}]
		});

/**
 * 索赔跟进信息日志
 */
Ext.define('ClaimFollowUpGridModel', {
			extend : 'Ext.data.Model',
			fields : [{
						name : 'followUserId'// 跟进人id
					}, {
						name : 'followUser'// 跟进人
					}, {
						name : 'followDept'// 跟进部门
					},
					 {
						name : 'followDeptName'// 跟进部门
					},
					{
						name : 'content'// 跟进内容
					}, {
						name : 'createTime'// 跟进时间
						,type: 'date',convert: DpUtil.changeLongToDate,defaultValue:null
					}, {
						name : 'claimId'// 关联的索赔Id
					}]
		});
		
Ext.define('DeptModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		// id
		name : 'id',
		type : 'string'
	}, {
		// 姓名
		name : 'deptName',
		type : 'string'
	} ]
});

/**
 * 提交付款Model
 */
Ext.define('PaymentModel',{
	extend :'Ext.data.Model',
	fields:[{
		name:'id'//创建人
	},{
		name:'createUserId'//创建人Id
	},{
		name:'createTime'//创建时间
	},{
		name:'recompenseId'//理赔Id
	},{
		name:'paymentMode'//领款方式
	},{
		name:'accountProp'//账号性质
	},{
		name:'openName'//开户名
	},{
		name:'bankName'//银行名字
	},{
		name:'account'//账号
	},{
		name:'province'//省
	},{
		name:'city'//市
	},{
		name:'branchName'//支行
	},{
		name:'mobile'//手机
	},{
		name:'paymentStatus'//付款状态
	},{
		name:'applyTime'//申请时间
	}]
});
/**
 * 银行查询条件的Model
 */
Ext.define('BankSearchConditionModel',{
	extend:'Ext.data.Model',
	fields:['province','city','accountBank','accountBranch',
	        'provinceId','cityId','accountBankId','accountBranchId']
});

/**
 * 
 * <p>
 * Description:付款记录 model<br />
 * </p>
 * @title PayRecordModel
 * @author 侯斌飞
 * @version 0.1 2012-12-31
 */
Ext.define('PayRecordModel',{
	extend:'Ext.data.Model'
	,fields:[
	     {name:'fid',type:'number',defaultValue:0},/*编号*/
	     {name:'paymentMode',type:'string'},/*客户领款方式*/
	     {name:'accountProp',type:'string'},/*账户性质*/
	     {name:'bankName',type:'string'},/*开户银行*/
	     {name:'branchName',type:'string'},/*开户支行*/
	     {name:'openName',type:'string'},/*开户名*/
	     {name:'account',type:'string'},/*开户账号*/
	     {name:'mobile',type:'string'},/*手机号码*/
	     {name:'applyTime'},/*申请时间*/
	     {name:'paymentStatus',type:'string'}/*付款状态*/
	   ]
});
/**
 * 理赔监控理赔金额model
 */
Ext.define('RecompenseAmountModel',{
	extend:'Ext.data.Model',
	fields:[{
		name:'limit',type:'string'
	},{
		name:'amount',type:'string'
	}]
});
/**
 * 理赔监控理赔金额store
 */
Ext.define('RecompenseAmountStore',{
	extend:'Ext.data.Store',
	model:'RecompenseAmountModel',
	data : [
         {'limit': '0-1000', 'amount': '0-1000'},
         {'limit': '1000-3000', 'amount': '1000-3000'},
         {'limit': '3000-5000', 'amount': '3000-5000'},
         {'limit': '5000-10000', 'amount': '5000-10000'},
         {'limit': '10000-', 'amount': '10000以上'},
         {'limit':'ALL','amount':'全部'}
     ]
});
