/**
 *
 * @description ：服务补救新增界面
 * @author 邹明
 * @version 1.0
 * @date 2012-11-20 8:37
 * @update 2012-12-17 10:55
 */

Ext.onReady(function(){
	Ext.QuickTips.init();
	var keys = [
	    		'SERVICERECOVERY_REDUCTION_TYPE',//减免类别
	    		'SERVICE_RECOVERY_CUSTOMER_TYPE',//客户类别
	    		'MEMBER_GRADE'//客户等级
    	];
    	initDataDictionary(keys);//初始化数据字典


	//附件表格
	var fileData=Ext.create('FileInfoData',{});
	var addServiceRecoveryGird = Ext.create('InnerGridPanel',{
		store:fileData.getFileInfoStore(),
		columns:[
			{text:i18n('i18n.serviceRecovery.sequenceNumber')	,	xtype:'rownumberer'		,	width:35	},//序号
			{text:i18n('i18n.serviceRecovery.annexName')		,	dataIndex:'fileName'	,	width:528	},//'附件名称'
			{
				text:i18n('i18n.serviceRecovery.operation'),//'操作'
				renderer:function(){
					return '<a href=javascript:>'+i18n('i18n.serviceRecovery.delete')+'</a>';//删除
				},
				width:50
			}
		],
		listeners:{
			cellclick:function(me,td,cellIndex,record,tr,rowIndex){
				if(cellIndex==2){
					attachmentSize = attachmentSize-record.get('fileSize');
					//如果点击某行的删除，则删除这条记录
					addServiceRecoveryGird.store.remove(record);
				}
			}
		}
	});

	var attachmentSize = 0;
	/**
	 * 上传附件Form
	 */
	Ext.define('UploadAttachmentForm',{
		extend:'NoTitleFormPanel',
		frame:true,
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
				border : false,
				name:'file',
				disabled:(me.status == 'VIEW'),
				fieldLabel:i18n('i18n.serviceRecovery.fileUpload'),//附件上传
				labelWidth:60,
				buttonText:i18n('i18n.serviceRecovery.scan'),//'浏览',
				emptyText:i18n('i18n.serviceRecovery.fileInfoUploadLimit'),
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
				text:i18n('i18n.serviceRecovery.upload'),//'上传',
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
			if(addServiceRecoveryGird.store.data.length>9){
				Ext.getCmp('fileBtnId').disable();
				MessageUtil.showMessage(i18n('i18n.servicerecovery.attachmentmoreexception'),"");
				return;
			};

			me.getForm().findField('attachmentSize').setValue(attachmentSize);
			form.submit({
	                    url: '../common/fileUpload.action',
	                    waitMsg: i18n('i18n.serviceRecovery.fileUploadIng'),//'文件上传中',
	                    success: function(form, response) {
	                    	var result = response.result;
	                    	if(result.success){
	                    		//上传成功后上传按钮设置为不可点击
	                    		Ext.getCmp('fileBtnId').setDisabled(true);

	                    		var fileInfo;
	                    		me.getForm().findField('file').setValue();

	                    		if(result.fileInfoList.length>0){
	                    			fileInfo = result.fileInfoList[0];
	                    			attachmentSize += fileInfo.fileSize;
		                    		//创建选择的上传附件记录
		                    		var fileInfoRecord = Ext.create('FileInfoModel',fileInfo);
		                    			//上面每成功增加一条记录，下面表格里面都要动态的显示出来
		                    			addServiceRecoveryGird.store.add(fileInfoRecord);
		                        	MessageUtil.showInfoMes(i18n('i18n.serviceRecovery.file') +
		                        			fileInfo.fileName +
		                        			i18n('i18n.serviceRecovery.upLoadSuccess'));
	                    		}else{
	                    			MessageUtil.showMessage('文件过大上传超时');
	                    			return;
	                    		}

							}else{
	                       		MessageUtil.showErrorMes( result.message);
							}
	                    },
		                 failure:function(form, response){
		                 	var result = response.result;
		                 	Ext.getCmp('fileBtnId').setDisabled(true);
		                 	if(!result.success){
		                       	MessageUtil.showErrorMes(result.message);
							}
		                 }
	                });

		}
	});
	/******************/
	var btnPanel = Ext.create('PopButtonPanel',{
		border:false,
//		frame:true,
		items:[{
			xtype:'middlebuttonpanel'
		},{
			xtype:'poprightbuttonpanel',
			items:[{
				//提交按钮
				xtype:'button',
				text:i18n('i18n.serviceRecovery.submitBtn'),
				name:'submitBtn',
				handler:function(){
					var meBtn = this;
					meBtn.setDisabled(true);
					var fileInfoList = new Array();
					var me = addServiceRecoveryForm.getForm();

					if(me.findField('serviceRecovery.reductionType').getValue()=='GOODS_DAMAGED'&&
							addServiceRecoveryGird.store.data.length==0){
						MessageUtil.showMessage(i18n('i18n.servicerecovery.damageattchmentnotnullexception'),"");
						meBtn.setDisabled(false);
						return;
					}
					if(!me.isValid()){
						meBtn.setDisabled(false);
						return;
					}
					addServiceRecoveryGird.getStore().each(function(record){
						fileInfoList.push(record.data);
					});

					var params={
							'serviceRecovery'	:{
								'waybillNumber'		:me.findField('waybillNumber'	).getValue(),//1.运单号
								'customerType'		:me.findField('customerType'	).getValue(),//2.客户类别
								'waybillAmount'		:me.findField('waybillAmount'	).getValue(),//3.开单金额
								'customerName'		:me.findField('customerName'	).getValue(),//4.客户名称
								'customerNum'		:me.findField('customerNum'	).getValue(),//4.客户编码
								'customerLevel'		:me.findField('customerLevel'	).getValue(),//4.客户等级
								'contactName'		:me.findField('contactName'	).getValue(),//4.联系人名称
								'outboundTime'		:Ext.Date.format(new Date(me.findField('signedDateTo'		).getValue()),'Y-m-d H:i:s'),//5.签收时间
								'subsidiary'		:me.findField('subsidiary'		).getValue(),//5.所属子公司
								'reductionAmount'	:me.findField('serviceRecovery.reductionAmount'	).getValue(),//6.减免金额
								'operator'			:Ext.getCmp('empName').getValueId(),//7.经手人
								'reductionType'		:me.findField('serviceRecovery.reductionType'	).getValue(),//8.减免类型
								'financeDept'		:me.findField('serviceRecovery.financeDept'		).getValue(),//9.财务部
								'totalPackage'		:me.findField('totalPackage'	).getValue(),//10.总件数
								'damagePackage'		:me.findField('serviceRecovery.damagePackage'	).getValue(),//11.受损件数
								'recoveryReason'	:me.findField('serviceRecovery.recoveryReason'	).getValue(),//12.补救原因
								'fileInfoList'		:fileInfoList,
								'tranType'			:me.findField('tranType').getValue(),//运输方式
								'party'				:me.findField('party').getValue()//系统判断收发货方,快递才用到这个字段
							}
					};

					var successFn=function(json){
						attachmentSize = 0;
						meBtn.setDisabled(false);
						MessageUtil.showInfoMes(i18n('i18n.serviceRecovery.serviceRecoveryAddSuccess'));
						addServiceRecoveryForm.getForm().reset();
						addServiceRecoveryGird.store.removeAll();
					};
					var failureFn=function(json){
						if(Ext.isEmpty(json)){
							MessageUtil.showErrorMes(i18n('i18n.serviceRecovery.serviceRecoveryAddFail'));
						}else{
							meBtn.setDisabled(false);
							MessageUtil.showErrorMes(json.message);
						}
					};
					ServiceRecoveryData.prototype.addServiceRecoverySubmit(params,successFn,failureFn);
				}
			},{
				//重置按钮
				xtype:'button',
				text:i18n('i18n.serviceRecovery.resetBtn'),//'重置',
				name:'resetBtn',
				handler:function(){
					attachmentSize = 0;
					addServiceRecoveryForm.getForm().reset();
					addServiceRecoveryGird.store.removeAll();
					Ext.getCmp('breakDown').setReadOnly(true);
				}
			}]
		}]
	});

	/**新增服务补救整体from***************************************************/

	var waybillRecord;
	var senderCustomerLevelDesc;
	var consigneeCustomerLevelDesc;
	var addServiceRecoveryForm=Ext.create('TitleFormPanel',{
		id:'addFormId',
		layout:{
			type:'table',
			columns:1
		},
		defaults: {//统一设置宽、居右
			margin:'5 5 5 5'
		},
		items:[{
			xtype:'basicfiledset',
			title:i18n('i18n.serviceRecovery.waybillInfo'),
			width:780,
//			height:135,
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
					i18n('i18n.serviceRecovery.waybillNumber'),
				name:'waybillNumber',
				id:'waybillNumber',
				allowBlank:false,
				regex:/^[\d ]+$/,
				minLength:8,
				minLengthText:i18n('i18n.serviceRecovery.waybillNumberMinLengthText'),
				maxLength:10,
				maxLengthText:i18n('i18n.serviceRecovery.waybillNumberMaxLengthText'),
				regexText:i18n('i18n.serviceRecovery.waybillLengthLimit'),
				blankText:i18n('i18n.serviceRecovery.waybillNumberCannotNull'),
				emptyText:i18n('i18n.serviceRecovery.inputNumber'),
				listeners: {
					//失去焦点时，查询此运单的信息
					'blur': function(){
						var form = addServiceRecoveryForm.getForm();
						//在重新填写信息的时候将受损件数设置为不可编辑
						Ext.getCmp('breakDown').setReadOnly(true);

						var me = this;
						var customerName ;
						var customerNum;
						var customerLevel;
						var customerLevelDesc;
						var contactName;

						customerName 		= form.findField('customerName').getValue();
						customerNum			= form.findField('customerNum').getValue();
						customerLevel 		= form.findField('customerLevel').getValue();
						customerLevelDesc 	= form.findField('customerLevelDesc').getValue();
						contactName 		= form.findField('contactName').getValue();
						if(Ext.isEmpty(me.getValue())){
							return;
						}
						if(!me.isValid()){
							var v = me.getValue();
							attachmentSize = 0;
							addServiceRecoveryForm.getForm().reset();
							me.setValue(v);
							addServiceRecoveryGird.store.removeAll();
							Ext.getCmp('breakDown').setReadOnly(true);
							MessageUtil.showMessage(i18n('i18n.serviceRecovery.waybillNumberIllegal'),"");
							return;
						}
						var param={
								'waybillNumber' : Ext.util.Format.trim(me.getValue())
						};
						var successFn = function(json){
							//转换日期格式
							Ext.getCmp('addViewCustomerType').setValue('');
							var signedDateTo = json.waybill.signedDate;
							json.waybill.signedDate = ServiceRecoveryData.prototype.formatDate(json.waybill.signedDate);

							//转换客户等级格式
							senderCustomerLevelDesc =
									DpUtil.changeDictionaryCodeToDescrip(
												json.waybill.senderCustomerLevel,
												DataDictionary.MEMBER_GRADE);
							consigneeCustomerLevelDesc =
									DpUtil.changeDictionaryCodeToDescrip(
												json.waybill.consigneeCustomerLevel,
												DataDictionary.MEMBER_GRADE);

							//将JSON数据转换成model数据
							waybillRecord = new WaybillModel(json.waybill);

							//带出运单信息
							form.loadRecord(waybillRecord);
							form.findField('signedDateTo').setValue(new Date(signedDateTo));

							form.findField('customerName').setValue(customerName);
							form.findField('customerNum').setValue(customerNum);
							form.findField('customerLevel').setValue(customerLevel);
//							form.findField('customerLevelDesc').setValue(senderCustomerLevelDesc);
							form.findField('contactName').setValue(contactName);

							//设置受损件数的最大值为总件数
							form.findField('serviceRecovery.damagePackage').setMaxValue(json.waybill.totalPackage);
							var amount = json.waybill.waybillAmount;
							if(json.waybill.tranType=='快递'){
								if(0<amount&&amount<=30){
									form.findField('serviceRecovery.reductionAmount').setMaxValue(amount);
								}else{
									form.findField('serviceRecovery.reductionAmount').setMaxValue(30);
								}
								if(Ext.getCmp('serviceRecovery.reductionType.id').getStore()
													.getCount()==3){
									Ext.getCmp('serviceRecovery.reductionType.id')
										.getStore().add({'codeDesc':'时效延误','code':'timeDelay'});
								}
							}else{
								if(0<amount&&amount<=100){
									form.findField('serviceRecovery.reductionAmount').setMaxValue(amount);
								}
								if(amount>100){
									form.findField('serviceRecovery.reductionAmount').setMaxValue(100);
								}
								if(Ext.getCmp('serviceRecovery.reductionType.id').getStore()
													.getCount()==4){
									Ext.getCmp('serviceRecovery.reductionType.id')
										.getStore().removeAt(3);
									Ext.getCmp('serviceRecovery.reductionType.id').setValue('');
								}
							}
						};
						var failureFn = function(json){
							if(Ext.isEmpty(json)){
								DpUtil.showMessage(i18n('i18n.servicerecovery.waybillnotexistexception'));
							}else{
								MessageUtil.showErrorMes(json.message);
							}
							var v = me.getValue();
							attachmentSize = 0;
							addServiceRecoveryForm.getForm().reset();
							me.setValue(v);
							addServiceRecoveryGird.store.removeAll();
							Ext.getCmp('breakDown').setReadOnly(true);
						};

						ServiceRecoveryData.prototype.searchWaybillByNum(param,successFn,failureFn);
					}
				}
			},{
				//客户类别
				xtype:'combo',
				fieldLabel:'<span style="color:red;font-weight:bold">*</span>'+i18n('i18n.serviceRecovery.customerType'),
				name:'customerType',
				allowBlank:false,
				id:'addViewCustomerType',
				blankText:i18n('i18n.serviceRecovery.customerTypeCannotNull'),//'客户类别不能为空',
				store : getDataDictionaryByName(DataDictionary,'SERVICE_RECOVERY_CUSTOMER_TYPE'),
				queryMode:'local',
				displayField:'codeDesc',
				valueField:'code',
				value:null,
				editable:false,
				listeners:{
					"select":function( combo, records,eOpts ){
						var form = addServiceRecoveryForm.getForm();
						var record = records[0].get('codeDesc');
						//如果选择客户类别为发货方，带出发货方姓名
						if(record==i18n('i18n.serviceRecovery.sender')){
							form.findField('customerName').setValue(waybillRecord.get('senderCustomerName'));
							form.findField('customerNum').setValue(waybillRecord.get('senderCustomerNum'));
							form.findField('customerLevel').setValue(waybillRecord.get('senderCustomerLevel'));
							form.findField('customerLevelDesc').setValue(senderCustomerLevelDesc);
							form.findField('contactName').setValue(waybillRecord.get('senderContactName'));
						}
//						//如果为收货方，带出收货方姓名
						if(record==i18n('i18n.serviceRecovery.consignee')){
							form.findField('customerName').setValue(waybillRecord.get('consigneeCustomerName'));
							form.findField('customerNum').setValue(waybillRecord.get('consigneeCustomerNum'));
							form.findField('customerLevel').setValue(waybillRecord.get('consigneeCustomerLevel'));
							form.findField('customerLevelDesc').setValue(consigneeCustomerLevelDesc);
							form.findField('contactName').setValue(waybillRecord.get('consigneeContactName'));
						}
					}
				}
			},{
				//客户名称
				fieldLabel:i18n('i18n.serviceRecovery.customerName'),
				name:'customerName',
				emptyText:i18n('i18n.serviceRecovery.autoOutByWaybillNumber'),//'根据单号自动带出',
				readOnly:true
			},{
				fieldLabel:i18n('i18n.serviceRecovery.customerNum'),//'客户编码',
				name:'customerNum',
				emptyText:i18n('i18n.serviceRecovery.autoOutByWaybillNumber'),//'根据单号自动带出',
				readOnly:true
			},{
				fieldLabel:i18n('i18n.serviceRecovery.customerLevel'),//'客户等级',
				name:'customerLevelDesc',
				emptyText:i18n('i18n.serviceRecovery.autoOutByWaybillNumber'),//'根据单号自动带出',
				readOnly:true
			},{
				fieldLabel:i18n('i18n.serviceRecovery.contactName'),//'联系人姓名',
				name:'contactName',
				emptyText:i18n('i18n.serviceRecovery.autoOutByWaybillNumber'),//'根据单号自动带出',
				readOnly:true
			},{
				//开单金额
				fieldLabel:i18n('i18n.serviceRecovery.waybillAmount'),
				allowBlank:false,
				blankText:i18n('i18n.serviceRecovery.mustFillCheck'),
				name:'waybillAmount',
				emptyText:i18n('i18n.serviceRecovery.autoOutByWaybillNumber'),//'根据单号自动带出',
				readOnly:true
			},{
				//签收时间
				fieldLabel:i18n('i18n.serviceRecovery.outboundTime'),
				allowBlank:false,
				blankText:i18n('i18n.serviceRecovery.mustFillCheck'),
				name:'signedDate',
				emptyText:i18n('i18n.serviceRecovery.autoOutByWaybillNumber'),//'根据单号自动带出'
				readOnly:true
			},{
				fieldLabel:i18n('i18n.serviceRecovery.tranType'),//'运输类型',
				name:'tranType',
				emptyText:i18n('i18n.serviceRecovery.autoOutByWaybillNumber'),//'根据单号自动带出',
				readOnly:true
			},{
				xtype:'hiddenfield',
				name:'subsidiary'
			},{
				xtype:'hiddenfield',//客户等级隐藏域
				name:'customerLevel'
			},{
				xtype:'hiddenfield',
				name:'signedDateTo'
			},{xtype:'hiddenfield',
				name:'party'
			}]
		},{
			xtype:'fieldset',//第二个fieldset
			title:i18n('i18n.serviceRecovery.servicerecoveryInfo'),
			width:780,
//			height:480,
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
				//减免金额
				fieldLabel:'<span style="color:red;font-weight:bold">*</span>'+
					i18n('i18n.serviceRecovery.reductionAmount'),
				name:'serviceRecovery.reductionAmount',
				allowBlank:false,
				hideTrigger: true,
				blankText:i18n('i18n.serviceRecovery.reductionAmountCannotNull'),//'减免金额不能为空',
//				maxText:i18n('i18n.serviceRecovery.reductionAmountLimit'),
//				minText:i18n('i18n.serviceRecovery.reductionAmountLimit'),
				xtype:'numberfield',
				minValue:0.1
			},{
				//经手人即申请人
				xtype:'employeelookup',
				id : 'empName',
				name :'serviceRecovery.operator',
			  	maxLength:80,
			  	editable:false,
			 	emptyText:'',
			 	margin:'0 0 0 2',
			 	allowBlank:false,
			 	blankText:i18n('i18n.serviceRecovery.empNameCannotNull'),//'经手人不能为空',
			 	fieldLabel : '<span style="color:red;font-weight:bold">*</span>'+i18n('i18n.serviceRecovery.empName')//i18n('i18n.recompense.belongArea'),
			},{
				//减免类别
				xtype:'combo',
				fieldLabel:'<span style="color:red;font-weight:bold">*</span>'+
					i18n('i18n.serviceRecovery.reductionType'),
				name:'serviceRecovery.reductionType',
				id:'serviceRecovery.reductionType.id',
				allowBlank:false,
				editable:false,
				blankText:i18n('i18n.serviceRecovery.reductionTypeCannotNull'),//'减免类别不能为空',
				store : getDataDictionaryByName(DataDictionary,'SERVICERECOVERY_REDUCTION_TYPE'),
				queryMode:'local',
				displayField:'codeDesc',
				valueField:'code',
				colspan:2,
				listeners: {
					select:function(th,record){
						//当选择货物受损时，受损件数变为可编辑状态,且设置为不可为空，数量不能大于总件数
						if(this.getValue()=='GOODS_DAMAGED'){
							var breakDownInput = Ext.getCmp('breakDown');
							breakDownInput.setReadOnly(false);
							breakDownInput.allowBlank=false;
							breakDownInput.maxText=i18n('i18n.servicerecovery.damagepackagemoreexception');
							breakDownInput.blankText=i18n('i18n.servicerecovery.damagepackageisnullexception');
							breakDownInput.doComponentLayout();
							var totalPackage_show=Ext.getCmp('totalPackage_show');
							totalPackage_show.setValue(Ext.getCmp('totalPackage').getValue());
						}else{//当选择其他时，受损件数变为不可编辑状态，且设置可为空。
							var breakDownInput = Ext.getCmp('breakDown');
							breakDownInput.reset();
							breakDownInput.setReadOnly(true);
							breakDownInput.allowBlank=true;
							breakDownInput.doComponentLayout();
							var totalPackage_show=Ext.getCmp('totalPackage_show');
							totalPackage_show.setValue('');
						}
					}

				}
			},{
				//当地财务部
				xtype:'queryCombox',
				name:'serviceRecovery.financeDept',
				fieldLabel:'<span style="color:red;font-weight:bold">*</span>'+
					i18n('i18n.serviceRecovery.financeDept'),
				store:Ext.create('FinanceDeptStore',{}),
				allowBlank:false,
				blankText:i18n('i18n.serviceRecovery.financeDeptCannotNull'),
				editable:false,
				displayField:'deptName',
				valueField:'id'
			},{
				//总件数
				fieldLabel:i18n('i18n.serviceRecovery.totalPackage'),
				name:'totalPackage_show',
				id:'totalPackage_show',
				xtype:'numberfield',
				value:'',
				margin:'0 0 0 2',
				decimalPrecision:0,
				readOnly:true
			},{
				//受损件数
				id:'breakDown',
				name:'serviceRecovery.damagePackage',
				fieldLabel:i18n('i18n.serviceRecovery.damagePackage'),
				emptyText:i18n('i18n.serviceRecovery.damagePackageEdit'),//'只有选择货物受损时才能编辑',
				xtype:'numberfield',
				hideTrigger:true,
				decimalPrecision:0,
				minValue:1,
				readOnly:true
			},{
				//补救原因
				xtype:'textarea',
				fieldLabel:'<span style="color:red;font-weight:bold">*</span>'+
					i18n('i18n.serviceRecovery.recoveryReason'),
				name:'serviceRecovery.recoveryReason',
				layout:'fit',
				allowBlank:false,
				blankText:i18n('i18n.serviceRecovery.recoveryReasonCannotNull'),//'补救原因不能为空',
				height:75,
				maxLength:300,
				width:463,
				colspan:3
			},{
				//附件上传
				margin:'5 0 0 15',
				xtype:'container',
				width:578,
				colspan:3,
				items:Ext.create('UploadAttachmentForm',{})
			},{
				//附件表格
				xtype:'container',
				border:false,
				layout:'fit',
				margin:'0 0 0 85',
				width:615,
				height:255,
				items:[addServiceRecoveryGird],
				colspan:3
			},{
				//总件数
				xtype:'hiddenfield',
				fieldLabel:i18n('i18n.serviceRecovery.totalPackage'),
				name:'totalPackage',
				id:'totalPackage',
				margin:'0 0 0 2',
				decimalPrecision:0,
				emptyText:i18n('i18n.serviceRecovery.autoOutByWaybillNumber'),//'根据单号自动带出',
				allowBlank:false,
				blankText:i18n('i18n.serviceRecovery.mustFillCheck'),
				readOnly:true
			}]
		},
		{
			layout:'fit',
			margin:'-5 -5 0 0',
			border:false,
			items:btnPanel
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
			items:addServiceRecoveryForm
		}]
	});

});
