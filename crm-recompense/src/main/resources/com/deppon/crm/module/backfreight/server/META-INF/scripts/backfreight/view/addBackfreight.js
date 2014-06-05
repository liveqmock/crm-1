/**
 * 
 * @description ：退运费申请界面
 * @author 邹明
 * @version 1.0
 * @date 2012-11-15 18:01
 * @update 2012-12-17 11:05
 */

Ext.onReady(function(){
	Ext.QuickTips.init();
	var keys = [
				'MEMBER_GRADE',//客户等级
	            'BACKFREIGHT_TRANSPORT_TYPE',//运输性质  
	    		'BACKFREIGHT_PAY_TYPE',//付款方式
	    		'BACKFREIGHT_AAPLY_TYPE'//申请状态
	    	];
    	initDataDictionary(keys);//初始化数据字典
    	
    	
	//附件表格
	var fileData=Ext.create('FileInfoData',{});
	var addbackFreightGird = Ext.create('PopupGridPanel',{
		width:616,
				margin:'0 0 0 85',
				height:255,
				colspan:3,
		store:fileData.getFileInfoStore(),
		columns:[
			{text:i18n('i18n.backFreight.sequenceNumber')	,	xtype:'rownumberer'		,	width:35	},//序号
			{text:i18n('i18n.backFreight.annexName')		,	dataIndex:'fileName'	,	width:528.5	},//'附件名称'
			{
				text:i18n('i18n.backFreight.operation'),//'操作'
				renderer:function(){
					return '<a href=javascript:>'+i18n('i18n.backFreight.delete')+'</a>';//删除
				},
				width:50	
			}
		],
		listeners:{
			cellclick:function(me,td,cellIndex,record,tr,rowIndex){
				if(cellIndex==2){
					attachmentSize = attachmentSize-record.get('fileSize');
					//如果点击某行的删除，则删除这条记录
					addbackFreightGird.store.remove(record);
				}
			}
		}
	});
	
	var attachmentSize=0;
	/**
	 * 上传附件Form
	 */
	Ext.define('UploadAttachmentForm',{
		extend:'NoTitleFormPanel',
		frame:true,
		border:false,
		flex:1,
		margins : '5 0 0 0',
		layout:{
			type : 'hbox'
		},
		defaults : {
			margins : '0 5 0 0'
		},
		status:null,
		contractData:null,
		fileInfo:null,
		initComponent:function(){
			var me = this;
			me.items = me.getItems();
			me.callParent();
		},
		getItems:function(){
			var me = this;
			return [{
				xtype:'filefield',
				name:'file',
				disabled:(me.status == 'VIEW'),
				fieldLabel:i18n('i18n.backFreight.fileUpload'),//附件上传
				labelWidth:60,
				emptyText:'至少添加一条附件信息',
				buttonText:i18n('i18n.backFreight.scan'),//'浏览',//i18n('i18n.ChangeContactAffiliatedRelationView.view'),
				flex:3,
				listeners:{
					//如果加入文件，则上传按钮设置为可点击，否则不可点击
					'change':function(me,value,eOpts){
						if(value!=null&&""!=value){
							Ext.getCmp('fileBtnId').setDisabled(false);
						}
					}
				}
			},{
				xtype:'button',
				id:'fileBtnId',
				disabled:(me.status == 'VIEW'),
				text:i18n('i18n.backFreight.upload'),//'上传',
				scope:me,
				disabled:true,
				handler:me.updateFile
			},{
				name:'type',
				xtype:'hiddenfield',
				value:'recompenceAttDir'
			},{
				name:'imageOrAttachment',
				xtype:'hiddenfield',
				value:'attachment'
			},{
				name:'attachmentSize',
				xtype:'hiddenfield'
			},{
				name:'maxSizeLimit',
				xtype:'hiddenfield',
				value:'30M'
			}];
		},
		updateFile:function(){
			var me = this;
			var form = me;
			//如果没有选择文件则提示请选择文件
			if(Ext.isEmpty(form.getForm().findField('file').getValue())){
				MessageUtil.showErrorMes('请选择文件进行上传！');
				Ext.getCmp('fileBtnId').disable();
				return;
			}
			var addr = new Array();
			addr = form.getForm().findField('file').getValue().split('.');
			var count = addr.length;
			//限制文件格式
			if(addr[count-1]!='jpg'&&addr[count-1]!='JPG'&&
					addr[count-1]!='jpeg'&&addr[count-1]!='JPEG'&&
					addr[count-1]!='gif'&&addr[count-1]!='GIF'&&
					addr[count-1]!='png'&&addr[count-1]!='PNG'&&
					addr[count-1]!='dmp'&&addr[count-1]!='DMP'&&
					addr[count-1]!='txt'&&addr[count-1]!='TXT'&&
					addr[count-1]!='pdf'&&addr[count-1]!='PDF'&&
					addr[count-1]!='doc'&&addr[count-1]!='DOC'&&
					addr[count-1]!='docx'&&addr[count-1]!='DOCX'&&
					addr[count-1]!='xls'&&addr[count-1]!='XLS'&&
					addr[count-1]!='xlsx'&&addr[count-1]!='XLSX'&&
					addr[count-1]!='rar'&&addr[count-1]!='RAR'){
				MessageUtil.showErrorMes('请上传正确的文件格式！');
				return;
			}
			
			//如果附件数超过十条，提示用户不能超过十条附件
			if(addbackFreightGird.getStore().data.length>9){
				Ext.getCmp('fileBtnId').disable();
				MessageUtil.showMessage(i18n('i18n.backfreight.attachmentmoreexception'),"");
				return;
			};
			
			me.getForm().findField('attachmentSize').setValue(attachmentSize);
			form.submit({
	                    url: '../common/fileUpload.action',
	                    waitMsg: i18n('i18n.backFreight.fileUploadIng'),//'文件上传中',
	                    success: function(form, response) {
	                    	//上传成功后上传按钮设置为不可点击
                    		Ext.getCmp('fileBtnId').setDisabled(true);
                    		
	                    	var result = response.result;
	                    	if(result.success){
	                    		var fileInfo;
	                    		
	                    		if(result.fileInfoList.length>0){
	                    			fileInfo = result.fileInfoList[0];
	                    			attachmentSize += fileInfo.fileSize;
	                    			me.getForm().findField('file').setValue();
	                    			var fileInfoRecord = Ext.create('FileInfoModel',fileInfo);//创建选择的上传附件记录
	                    			
	                    			addbackFreightGird.store.add(fileInfoRecord);//附件表格显示选择的附件
	                    			
	                    			MessageUtil.showInfoMes(i18n('i18n.backFreight.file') +
	                    					fileInfo.fileName + 
	                    					i18n('i18n.backFreight.upLoadSuccess'));
	                    		}
	                    		
							}else{
	                       		MessageUtil.showErrorMes( result.message);
							}
	                    },
	                 failure:function(form, response){
	                	Ext.getCmp('fileBtnId').setDisabled(true);
	                 	var result = response.result;
	                 	if(!result.success){
	                       	MessageUtil.showErrorMes(result.message);
						}
	                 }
	                });
		
		}
	});
	/*************************************/
	var  saveOrResetBtnPanel = Ext.create('PopButtonPanel',{
		items:[{
			xtype:'middlebuttonpanel'
		},{
			xtype:'poprightbuttonpanel',
			items:[{
//				//提交按钮
				text:i18n('i18n.backFreight.submitBtn'),
				xtype:'button',
				name:'submitBtn',
				handler:function(){
					var meBtn  = this;
					meBtn.setDisabled(true);
					var fileInfoList = new Array();
					var me = addbackFreightForm.getForm();
					
					if(!me.isValid()){
						meBtn.setDisabled(false);
						return;
					}
					if(addbackFreightGird.store.data.length<1){
						MessageUtil.showMessage('请添加附件至少一条');
						meBtn.setDisabled(false);
						return;
					}
					addbackFreightGird.getStore().each(function(record){
						fileInfoList.push(record.data);
					});
					var params={
							'backFreight'	:{
								'waybillNumber'		:me.findField('waybillNumber'	).getValue(),//1.运单号
								
								'tranType'			: me.findField('tranType').getValue(),//4.运输性质
									
								'customerNum'		:me.findField('customerNum'		).getValue(),//3.客户编号
								
								'customerLevel'		:me.findField('customerLevel').getValue(),//4.客户等级
					
								'customerName'		:me.findField('customerName'	).getValue(),//5.客户名称
								
								'paymentType'		:me.findField('paymentType').getValue(),//,//6.付款方式
																	
								'waybillAmount'		:me.findField('totalCharge'		).getValue(),//7.纯运费
								'outboundTime'		:Ext.Date.format(new Date(me.findField('signedDateTo').getValue()),'Y-m-d H:i:s'),//8.签收时间
								'applyAmount'		:me.findField('backFreight.applyAmount'	).getValue(),//9.申请金额
								'applyReason'		:me.findField('backFreight.applyReason'	).getValue(),//10.申请事由
								'subsidiary'		:me.findField('subsidiary'		).getValue(),//10.子公司
								'stowageDept'		:me.findField('stowageDept'		).getValue(),//配载部门
								'financeDept'		:me.findField('backFreight.financeDept'		).getValue(),//财务部门
								'contactName'		:me.findField('contactName'		).getValue(),//联系人
								'fileInfoList'		:fileInfoList//11.附件列表
							}
					};
					
					var successFn=function(json){
						attachmentSize = 0;
						meBtn.setDisabled(false);
						MessageUtil.showInfoMes(i18n('i18n.backFreight.backFreightAddSuccess'));
						addbackFreightForm.getForm().reset();
						addbackFreightGird.store.removeAll();
					};
					var failureFn=function(json){
						if(Ext.isEmpty(json)){
							MessageUtil.showErrorMes(i18n('i18n.backFreight.backFreightAddFail'));
						}else{
							meBtn.setDisabled(false);
							MessageUtil.showErrorMes(json.message);
						}
					};
					BackFreightData.prototype.submitBackFreight(params,successFn,failureFn);
					
					
				}
			},{
				//重置按钮
				xtype:'button',
				text:i18n('i18n.backFreight.resetBtn'),//'重置',
	//			name:'resetBtn',
				handler:function(){
					attachmentSize = 0;
					addbackFreightForm.getForm().reset();
					addbackFreightGird.store.removeAll();
				}
			}]
		}]
	});
	
	/**退运费新增整体from***************************************************/
	var addbackFreightForm=Ext.create('TitleFormPanel',{
		id:'addFormId',
		defaults: {//统一设置宽、居右
			margin:'5 5 0 5'
		},
		layout:{
			type:'table',
			columns:1
		},
		items:[{
				/*******************运单信息fieldset**************************/
			xtype:'basicfiledset',
			title:i18n('i18n.backFreight.waybillInfo'),//'运单信息'
			width:780,
//			height:160,
			layout:{
				type:'table',
				columns:3
			},
			defaultType:'textfield',
			defaults:{
				labelAlign:'right',
				width:225,
				labelWidth:75,
				margin:'5 5 5 5'
			},
			items:[{
				//运单号
				fieldLabel:'<span style="color:red;font-weight:bold">*</span>'+
					i18n('i18n.backFreight.waybillNumber'),
				name:'waybillNumber',
				id:'waybillNumber',
				regex:/^[\d ]+$/,
				minLength:8,
				mingLengthText:i18n('i18n.backFreight.waybillNumberMinLengthText'),//您好，运单号最少为8位',
				maxLength:10,
				maxLangthText:i18n('i18n.backFreight.waybillNumberMaxLengthText'),//'您好，运单号最长为10位',
				//regexText:i18n('i18n.backFreight.waybillLengthLimit'),
				allowBlank:false,
				blankText:i18n('i18n.backFreight.waybillNumberCannotNull'),
				emptyText:i18n('i18n.backFreight.inputNumber'),
				listeners: { 
					//失去焦点时，查询此运单的信息
					'blur': function(){  
						var me = this;
						//如果输入为空 不进行操作
						if(Ext.isEmpty(me.getValue())){
							
						return;
						}
						if(!me.isValid()){
							MessageUtil.showMessage(i18n('i18n.backFreight.waybillNumberIllegal'),"");
							var v = me.getValue();
							attachmentSize = 0;
							addbackFreightForm.getForm().reset();
							me.setValue(v);
							addbackFreightGird.store.removeAll();
							return;
						}
						var param={
								'waybillNumber' : Ext.util.Format.trim(me.getValue())
						};
						var successFn = function(json){
							var form = addbackFreightForm.getForm();
							//给隐藏域赋值，传回后台的时候对应的
							
							//转换签收时间格式
							var signedDateTo = json.waybillInfo.signedDate;
							json.waybillInfo.signedDate = BackFreightData.prototype.formatDate(signedDateTo);
							//转换客户等级格式
							var  customerLevelDesc = 
									DpUtil.changeDictionaryCodeToDescrip(
											json.waybillInfo.customerLevel,
												DataDictionary.MEMBER_GRADE);
							//转换运输性质格式
							var tranTypeDesc = 
								DpUtil.changeDictionaryCodeToDescrip(
										json.waybillInfo.tranType,
											DataDictionary.BACKFREIGHT_TRANSPORT_TYPE);
							//付款方式
							var paymentTypeDesc = 
								DpUtil.changeDictionaryCodeToDescrip(
										json.waybillInfo.paymentType,
											DataDictionary.BACKFREIGHT_PAY_TYPE);
							//运单model
							var waybillModel = new WaybillModel(json.waybillInfo);
							form.loadRecord(waybillModel);
							
							form.findField('signedDateTo').setValue(new Date(signedDateTo));
							form.findField('tranTypeDesc').setValue(tranTypeDesc);
							form.findField('customerLevelDesc').setValue(customerLevelDesc);
							form.findField('paymentTypeDesc').setValue(paymentTypeDesc);
							
							var applyAmount = form.findField('backFreight.applyAmount');
							if(json.waybillInfo.tranType=='ACCURATE_AIR'){
								applyAmount.setMaxValue(json.waybillInfo.totalCharge);
								applyAmount.maxText=i18n('i18n.backFreight.applyAmountAirLimit');
								applyAmount.doComponentLayout();
							}
							if(json.waybillInfo.tranType=='ACCURATE_CITY'){
								applyAmount.setMaxValue(3*json.waybillInfo.totalCharge);
								applyAmount.maxText=i18n('i18n.backFreight.applyAmountCityLimit');
								applyAmount.doComponentLayout();
							}
						};
						var failureFn = function(json){
							//addbackFreightForm.getForm().reset();
							if(Ext.isEmpty(json)){
								MessageUtil.showErrorMes(i18n('i18n.backFreight.searchWaybillMoreTime'));
							}else{
								MessageUtil.showErrorMes(json.message);
							}
							var v = me.getValue();
							attachmentSize = 0;
							addbackFreightForm.getForm().reset();
							me.setValue(v);
							addbackFreightGird.store.removeAll();
						};
						BackFreightData.prototype.searchWaybillByNum(param,successFn,failureFn);
					}
				}
			},{
				//运输性质
				fieldLabel:i18n('i18n.backFreight.tranType'),
				name:'tranTypeDesc',
				emptyText:i18n('i18n.backFreight.autoOutByWaybillNumber'),//'根据单号自动带出',
				readOnly:true
			},{
				//'客户名称'
				fieldLabel:i18n('i18n.backFreight.customerName'),
				name:'customerName',
				emptyText:i18n('i18n.backFreight.autoOutByWaybillNumber'),//'根据单号自动带出',
				readOnly:true
			},{
				fieldLabel:i18n('i18n.backFreight.customerNum'),//'客户编码',
				name:'customerNum',
				emptyText:i18n('i18n.backFreight.autoOutByWaybillNumber'),//'根据单号自动带出',
				readOnly:true
			},{
				fieldLabel:i18n('i18n.backFreight.customerLevel'),//'客户等级',
				name:'customerLevelDesc',
				emptyText:i18n('i18n.backFreight.autoOutByWaybillNumber'),//'根据单号自动带出',
				readOnly:true
			},{
				fieldLabel:i18n('i18n.backFreight.contactName'),//'联系人姓名',
				name:'contactName',
				emptyText:i18n('i18n.backFreight.autoOutByWaybillNumber'),//'根据单号自动带出',
				readOnly:true
			},{
				fieldLabel:i18n('i18n.backFreight.pureFreight'),//'纯运费',
				xtype:'numberfield',
				name:'totalCharge',
				allowBlank:false,
				blankText:i18n('i18n.backFreight.mustFillCheck'),//'必填项，请检查运单号是否正确。',
				emptyText:i18n('i18n.backFreight.autoOutByWaybillNumber'),//'根据单号自动带出',
				minValue:1,
				readOnly:true
			},{
				//TODO'付款方式'
				fieldLabel:i18n('i18n.backFreight.payType'),
				name:'paymentTypeDesc',
				allowBlank:false,
				blankText:i18n('i18n.backFreight.mustFillCheck'),//'必填项，请检查运单号是否正确。',
				emptyText:i18n('i18n.backFreight.autoOutByWaybillNumber'),//'根据单号自动带出',
				readOnly:true
			},{
				fieldLabel:i18n('i18n.backFreight.stowageDept'),//配载部门
				name:'stowageDeptName',
				allowBlank:false,
				blankText:i18n('i18n.backFreight.mustFillCheck'),//'必填项，请检查运单号是否正确。',
				emptyText:i18n('i18n.backFreight.autoOutByWaybillNumber'),//'根据单号自动带出'
				readOnly:true
			},{
				//签收时间
				fieldLabel:i18n('i18n.backFreight.outboundTime'),
				name:'signedDate',
				allowBlank:false,
				blankText:i18n('i18n.backFreight.mustFillCheck'),//'必填项，请检查运单号是否正确。',
				emptyText:i18n('i18n.backFreight.autoOutByWaybillNumber'),//'根据单号自动带出'
				readOnly:true
			},{
				xtype:'hiddenfield',
				name:'stowageDept'
			},{
		        xtype: 'hiddenfield',
		        name: 'tranType',
		        width:0
		    },{
		        xtype: 'hiddenfield',
		        name: 'customerLevel',
		        width:0
		    },{
		        xtype: 'hiddenfield',
		        name: 'paymentType',
		        width:0
		    },{
		    	xtype: 'hiddenfield',
		        name: 'signedDateTo',
		        width:0
		    }]
		},{
			xtype:'basicfiledset',//第二个fieldset
			title:i18n('i18n.backFreight.backFreightInfo'),
			width:780,
//			height:450,
			margin:'5 0 0 5',
			defaultType:'textfield',
			layout:{
				type:'table',
				columns:3
			},
			defaults:{
				margin:'5 0 0 0',
				labelAlign:'right',
				width:230,
				labelWidth:80
			},
			items:[{
				//申请金额
				xtype:'numberfield',
				fieldLabel:'<span style="color:red;font-weight:bold">*</span>'+
					i18n('i18n.backFreight.applyAmount'),
				name:'backFreight.applyAmount',
				regex:/^[0-9]*[1-9][0-9]*$/,
				regexText:i18n('i18n.backFreight.applyAmountMustPositiveInteger'),
				allowBlank:false,
				blankText:i18n('i18n.backFreight.applyAmountCannotNull'),//'减免金额不能为空',
				hideTrigger:true,
				minValue:1
			},{
				//当地财务部
				xtype:'queryCombox',
				name:'backFreight.financeDept',
				fieldLabel:'<span style="color:red;font-weight:bold">*</span>'+
					i18n('i18n.backFreight.financeDept'),
				store:Ext.create('FinanceDeptStore',{}),
				allowBlank:false,
				blankText:i18n('i18n.backFreight.financeDeptCannotNull'),
				editable:false,
				margin:'0 0 0 5',
//				labelWidth:60,
				displayField:'deptName',
				valueField:'id'
			},{xtype:'hiddenfield'},{
				//申请事由
				xtype:'textarea',
				fieldLabel:'<span style="color:red;font-weight:bold">*</span>'+
					i18n('i18n.backFreight.applyReason'),
				name:'backFreight.applyReason',
				layout:'fit',
				height:75,
				maxLength:300,
				maxLengthText:'申请事由最多能填写300个字',
				allowBlank:false,
				blankText:i18n('i18n.backFreight.recoveryReasonCannotNull'),//'补救原因不能为空',
				width:465,
				emptyText:i18n('i18n.backFreight.applyReasonMustFill'),//'必填项，详细说明申请事由，避免工作流被退回'
				colspan:2
			},{
				xtype:'hiddenfield',
				name:'subsidiary'
				
			},{	
				//附件上传
				margin:'5 0 0 15',
				xtype:'container',
				width:580,
				colspan:3,
				border:false,
				items:Ext.create('UploadAttachmentForm',{})
			},addbackFreightGird/*,{
				//附件表格
				xtype:'container',
				border:false,
				layout:'fit',
				margin:'0 0 0 85',
				height:255,
				items:,
				colspan:3
			}*/]
		},
		{	
			layout:'fit',
			border:false,
			items:saveOrResetBtnPanel
		}]
	});

	/*******************************显示viewport****************************/
	Ext.create('Ext.container.Viewport', {
		layout: 'border',
		border:false,
		items: [{
			layout:'fit',
			region: 'center',
			margin:'5 5 5 5',
			border:false,
			items:addbackFreightForm
		}]
	});
});
