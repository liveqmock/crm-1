/**
 * 
 * @description ：服务补救详情界面
 * @author 邹明
 * @version 1.0
 * @date 2012-11-07 17:45
 * @update 2012-11-22 8:44
 */

/***********附件表格******************/
var detailsFileGrid = Ext.create('PopupGridPanel',{
		id:'detailsFileGridId',
		width:605,
		height:256,
		store:Ext.create('FileInfoData',{}).getFileInfoStore(),
		margin:'10 0 0 84',
		columns:[
		         //序号     
				{text:i18n('i18n.serviceRecovery.sequenceNumber'),		xtype:'rownumberer',	width:35},
				//附件名称
				{text:i18n('i18n.serviceRecovery.annexName'),	dataIndex:'fileName',	width:526.2},
				{
					text:i18n('i18n.serviceRecovery.download'),//'下载',
					renderer:function(){
						return '<a href=javascript:>'+
						i18n('i18n.serviceRecovery.download')+'</a>'	
					},
					width:42
				}
			],
			listeners:{
				cellclick:function(me,td,cellIndex,record,tr,rowIndex){
					if(cellIndex==2){
						//如果点击某行的下载，则下载这条记录
						var url="../common/downLoad.action?fileName="+
							record.data.fileName+"&inputPath="+record.data.savePath;
						window.location.href = url;
					}
				}
			},
			colspan:3
});


/**********详情form******************/
var detailsPopWinForm = Ext.create('TitleFormPanel',{
	autoScroll:true,
	height:600,
	defaultType:'textfield',
	defaults:{
		labelAlign:'right'
	},
	layout:{
		type:'table',
		columns:1
	},
	items:[{
		xtype:'basicfiledset',
		title:i18n('i18n.serviceRecovery.waybillInfo'),//'运单信息'
		height:560,
		layout:{
			type:'table',
			columns:3
		},
		defaultType:'textfield',
		defaults:{
			labelAlign:'right',
			labelWidth:80,
			width:230,
			margin:'5 0 0 0'
		},
		items:[{
			fieldLabel:i18n('i18n.serviceRecovery.waybillNumber'),//'运单号'	,
			name:'waybillNumber',
			readOnly:true	
		},{
			fieldLabel:i18n('i18n.serviceRecovery.customerType'),//'客户类别',
			name:'customerType',
			readOnly:true
		},{
			fieldLabel:i18n('i18n.serviceRecovery.customerName'),//'客户名称',
			name:'customerName',
			readOnly:true
		},{
			fieldLabel:i18n('i18n.serviceRecovery.customerNum'),//'客户编码',
			name:'customerNum',
			readOnly:true
		},{
			fieldLabel:i18n('i18n.serviceRecovery.customerLevel'),//'客户等级',
			name:'customerLevel',
			readOnly:true
		},{
			fieldLabel:i18n('i18n.serviceRecovery.contactName'),//'客户联系人',
			name:'contactName',
			readOnly:true
		},{
			fieldLabel:i18n('i18n.serviceRecovery.waybillAmount'),//'开单金额',
			name:'waybillAmount',
			readOnly:true
		},{
			fieldLabel:i18n('i18n.serviceRecovery.outboundTime'),//'签收时间',
			name:'outboundTime',
			readOnly:true
		},{
			fieldLabel:i18n('i18n.serviceRecovery.reductionAmount'),//'减免金额',
			name:'reductionAmount',
			readOnly:true
		},{
			fieldLabel:i18n('i18n.serviceRecovery.reductionType'),//'减免类别',
			name:'reductionType',
			readOnly:true
		},{
			fieldLabel:i18n('i18n.serviceRecovery.totalPackage'),//'总件数'	,
			name:'totalPackage',
			readOnly:true
		},{
			id:'breakDown',
			name:'damagePackage',
			fieldLabel:i18n('i18n.serviceRecovery.damagePackage'),//'受损件数',	
			readOnly:true
		},{
			fieldLabel:i18n('i18n.serviceRecovery.empName'),//'经手人',
			name:'operatorName',
			readOnly:true
		},{
			fieldLabel:i18n('i18n.serviceRecovery.applicant'),//'申请人',
			name:'applicantName',
			readOnly:true
		},{
			fieldLabel:i18n('i18n.serviceRecovery.applyTime'),//'申请时间',
			name:'applyTime',
			readOnly:true
		},{
			fieldLabel:i18n('i18n.serviceRecovery.verifier'),//'审批人',
			name:'verifierName',
			readOnly:true
		},{
			fieldLabel:i18n('i18n.serviceRecovery.verifyTime'),//'审批时间',
			name:'verifyTime',
			readOnly:true
		},{
			fieldLabel:i18n('i18n.serviceRecovery.verifyStatus'),//'审批状态',
			name:'applyStatus',
			readOnly:true
		},{
			xtype:'textarea',
			fieldLabel:i18n('i18n.serviceRecovery.applayReason'),//'申请原因',
			name:'recoveryReason',
			readOnly:true,
			width:690,
			colspan:3
		},{
			xtype:'displayfield',
			name:'fileInfoList0',
			fieldLabel:i18n('i18n.serviceRecovery.fileInfoList'),//'附件列表',
			width:'80',
			readOnly:true,
			colspan:3
		},
		detailsFileGrid
		]
	}],
	buttons:[{
		text:i18n('i18n.serviceRecovery.closeBtn'),//'关闭',
		name:'cancel',
		width:40,
		handler:function(){
			Ext.getCmp('detailsPop').close();
		}
	}]
});
