	/**
	 * 
	 * @description ：退运费所用的相关model
	 * @author 邹明
	 * @version 1.0
	 * @date 2012-11-15 15:32
	 * 
	 */

//退运费模型
Ext.define('QueryBackFreightModel',{
	extend:'Ext.data.Model',
	fields:[
	     {	name:'id'						},//0.服务补救ID
		{	name:'waybillNumber'			},//1.运单号
		{	name:'customerType'				},//2.客户类型
		{	name:'customerNum'				},//3.客户编号
		{	name:'customerName'				},//4.客户姓名
		{	name:'waybillAmount'			},//5.运单总额
		{	name:'reductionAmount'			},//6.减免总额
		{	name:'outboundTime'				},//7.出库时间？？签收时间
		{	name:'financeDept'				},//8.所属财政部门
		{	name:'operator'					},//9.操作人即经办人
		{	name:'operatorName'				},//9.操作人姓名
		{	name:'reductionType'			},//10.减免类型
		{	name:'totalPackage'				},//11.总件数
		{	name:'damagePackage'			},//12.受损件数
		{	name:'recoveryReason'			},//13.补救原因
		{	name:'applicant'				},//14.申请人
		{	name:'applicantName'			},//15.申请人姓名
		{	name:'applyDept'				},//16.申请部门
		{	name:'applyDeptName'			},//16.申请部门名字
		{	name:'applyTime'				},//17.申请时间
		{	name:'applyStatus'				},//18.申请状态
		{	name:'oaWorkflowNum'			},//19.OA工作流编号 
		{	name:'verifyTime'				},//20.审批时间 
		{	name:'verifier'					},//21.审批人
		{	name:'verifierName'				},//21.审批人姓名
		{	name:'subsidiary'				},//22.所属子公司
		{	name:'fileInfoList'				},//23.附件列表
		{	name:'tranType'					},//23.运输性质
		{	name:'financeDept'				},//23.附件列表
		{	name:'paymentType'				},//付款方式
		{	name:'applyAmount'				}//申请金额
		
	]
});

//grid下面paging里面的combox
Ext.define('GridComboModel', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'value'	},
    ]
});

//上传附件model
Ext.define('FileInfoModel',{
	extend:'Ext.data.Model',
	fields:[
			// 来源ID
			{name:'sourceId',type: 'string'},
			// 来源类型
			{name:'sourceType',type: 'string'},
			// 文件名称
			{name:'fileName',type: 'string'},
			// 文件类型
			{name:'fileType',type: 'string'},
			// 文件类型
			{name:'fileBusinessType',type: 'string'},
			// 保存路径
			{name:'savePath',type: 'string'},
			// 文件大小
			{name:'fileSize',type: 'number'}]
});

//////
Ext.define('WaybillModel', {
    extend: 'Ext.data.Model',
    fields : [{
		name :'id'						//1.id
//	},{
//		name :  'recompenseId'			//2.理赔ID
	},{
		name:'waybillNumber'			//3.运单号/差错编号
//	},{
//		name:'payType'					//4.付款方式
    },{
    	name:'senderCustomerName'					//5.发货人
    },{
    	name:'consigneeCustomerName'				//6.收货人
    },{
    	name:'totalCharge'				//7.纯运费（总费用）
    },{	   
    	name:'waybillAmount'			//8.开单金额
    },{
    	name:'laborRebate'				//9.劳务费
    },{
    	name:'publishCharge'			//10.公布价运费
    },{
    	name:'isSigned'					//11.是否签收	
    },{	   
    	name:'signedDate'				//12.签收时间
    },{
    	name:'senderCustomerNum'				//13.发货方编码
    },{
    	name:'consigneeCustomerNum'			//14.收货方客户编码	
    },{
    	name:'totalPackage'				//15.总件数
	},{
		name:'senderDeptId'				//16.发货部门ID	
	},{
		name:'consigneeDeptId'			//17.收货部门ID
	},{
		name:'paymentType'				//18.支付方式	
	},
	{	name:'tranType'					},//23.运输性质
	{	name:'subsidiary'				},//22.所属子公司
	{	name:'customerNum'				},//3.客户编号
	{	name:'customerName'				},//4.客户姓名
	{	name:'customerLevel'			},//客户等级
	{	name:'contactName'				},//联系人姓名
	{	name:'senderCustomerLevel'		},//23.发货客户级别
	{	name:'senderContactName'		},//23.发货客户联系人
	{	name:'consigneeCustomerLevel'	},//23.收货客户级别
	{	name:'consigneeContactName'		},//23.收货客户联系人
	{	name:'stowageDept'				},//23.配载部门
	{	name:'stowageDeptName'			},//23.配载部门名
	{	name:'stowageDeptStandardCode'	}//23.配载部门标杆编码
	]
});

//财务部门model
Ext.define('FinanceDeptModel',{
	extend:'Ext.data.Model',
	fields:[
	    {name:'deptName'	},//财务部门名字
	    {name:'id'},//ID
	    {name:'standardCode'}//财务部门编码
	]
});