/**
 * 
 * @description ：服务补救详情界面
 * @author 邹明
 * @version 1.0
 * @date 2012-11-17 15:31
 * @update 2012-11-22 9:20
 */

/***********附件表格******************/
var detailsFileGrid = Ext.create('PopupGridPanel',{
		width:615,
		height:256,
		store:Ext.create('FileInfoData',{}).getFileInfoStore(),
		margin:'10 0 0 75',
		columns:[
		         //序号     
				{text:i18n('i18n.backFreight.sequenceNumber'),		xtype:'rownumberer',	width:35},
				//附件名称
				{text:i18n('i18n.backFreight.annexName'),	dataIndex:'fileName',	width:538},
				//下载
				{
					text:i18n('i18n.backFreight.download'),	
					renderer:function(){
						return '<a href="javascript:">'+
							i18n('i18n.backFreight.download')+'</a>'
					},	
					width:40
				}
		],
		listeners:{
			'cellclick':function(me, td, cellIndex, record, tr, rowIndex, e, eOpts ){
				if(cellIndex==2){
					window.location.href="../common/downLoad.action?fileName="+
							record.data.fileName+"&inputPath="+record.data.savePath;
				}
			}
		},	
		colspan:3
});


/**********详情form******************/
var backFreightDetailsPopWinForm = Ext.create('TitleFormPanel',{
	id:'detailsPopWinFormId',
	autoScroll:true,
//	frame:true,
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
			title:i18n('i18n.backFreight.waybillInfo'),//'运单信息'
			height:550,
			layout:{
				type:'table',
				columns:3
			},
			defaultType:'textfield',
			defaults:{
				labelAlign:'right',
				labelWidth:70,
				width:230,
				margin:'5 0 0 0'
			},
			items:[{
				fieldLabel:i18n('i18n.backFreight.waybillNumber'),//'运单号'	,
				name:'waybillNumber',
				readOnly:true	
			},{
				//运输性质
				fieldLabel:i18n('i18n.backFreight.tranType'),
				name:'tranType',
				readOnly:true
			},{
				fieldLabel:i18n('i18n.backFreight.customerName'),//'客户名称',
				name:'customerName',
				readOnly:true
			},{
				fieldLabel:i18n('i18n.backFreight.customerNum'),//'客户编码',
				name:'customerNum',
				readOnly:true
			},{
				fieldLabel:i18n('i18n.backFreight.customerLevel'),//'客户等级',
				name:'customerLevel',
				readOnly:true
			},{
				fieldLabel:i18n('i18n.backFreight.contactName'),//'联系人姓名',
				name:'contactName',
				readOnly:true
			},{
				fieldLabel:i18n('i18n.backFreight.freight'),//'运费',
				name:'waybillAmount',
				readOnly:true
			},{
				fieldLabel:i18n('i18n.backFreight.payType'),//'付款方式',
				name:'paymentType',
				readOnly:true
			},{
				fieldLabel:i18n('i18n.backFreight.stowageDept'),//配载部门
				name:'stowageDeptName',
				readOnly:true
			},{
				//申请金额
				fieldLabel:i18n('i18n.backFreight.applyAmount'),
				name:'applyAmount',
				readOnly:true
			},{
				//当地财务部
				fieldLabel:i18n('i18n.backFreight.financeDept'),
				name:'financeDeptName',
				readOnly:true
			},{
				fieldLabel:i18n('i18n.backFreight.outboundTime'),//'签收时间',
				name:'outboundTime',
				readOnly:true
			},{
				fieldLabel:i18n('i18n.backFreight.applicant'),//'申请人',
				name:'applicantName',
				readOnly:true
			},{
				fieldLabel:i18n('i18n.backFreight.applyTime'),//'申请时间',
				name:'applyTime',
				readOnly:true
			},{
				fieldLabel:i18n('i18n.backFreight.verifier'),//'审批人',
				name:'verifierName',
				readOnly:true
			},{
				fieldLabel:i18n('i18n.backFreight.verifyTime'),//'审批时间',
				name:'verifyTime',
				readOnly:true
			},{
				fieldLabel:i18n('i18n.backFreight.applyStatus'),//申请状态
				name:'applyStatus',
				readOnly:true,
				colspan:2
			},{
				xtype:'textarea',
				fieldLabel:i18n('i18n.backFreight.applyReason'),//'申请事由',
				name:'applyReason',
				width:690,
				readOnly:true,
				colspan:3
			},{
				xtype:'displayfield',
				name:'fileInfoList0',
				fieldLabel:i18n('i18n.backFreight.fileInfoList'),//'附件列表',
				width:'80',
				readOnly:true,
				colspan:3
			},detailsFileGrid
			]
		}],
		buttons:[{
			text:i18n('i18n.backFreight.closeBtn'),//'关闭',
			name:'cancel',
			width:40,
			handler:function(){
				Ext.getCmp('detailsPop').hide();
			}
		}]
});
