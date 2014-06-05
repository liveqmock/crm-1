/**
 * 第一次加载才赋值
 */
isFirstLoad = true;

/**
	 *
	 * @description ：服务补救查询界面
	 * @author 邹明
	 * @version 1.0
	 * @date 2012-11-17 9:56
	 * @update 2012-12-19 15:16
	 */
	var serviceRecoveryDtailsWin;
	function showDetailWorkflow(url,params){
	 var width=1200,height=840;
	 var left=(screen.availWidth - width)/2;
     var top=(screen.availHeight - height)/2;
     if(Ext.isChrome){
     	window.open (url, "_blank", "width="+width+",height="+height+",top="+top+", left="+left+"");
     }else{
     	window.showModalDialog (url, params, 'dialogWidth:'+width+'px;dialogHeight:'+height+'px;dialogLeft='+left+'px";dialogTop='+top+'px');
     }
}
	Ext.onReady(function(){
		var keys = [
		    		'SERVICERECOVERY_REDUCTION_TYPE',//减免类别
		    		'SERVICE_RECOVERY_CUSTOMER_TYPE',//客户类别
		    		'SERVICE_RECOVERY_AAPLY_TYPE',//申请状态
		    		'MEMBER_GRADE'//客户等级
		    	];
		initDataDictionary(keys);
		initDeptAndUserInfo();//初始化部门和用户

		/*******************************顶部查询条件的form***************************/
		Ext.define('QueryForm',{
			extend:'SearchFormPanel',
			id:'queryFormId',
			frame:true,
			margin:'3 5 0 5',
			layout:{
				type:'table',
				columns:3
			},
			defaultType:'textfield',
			defaults:{
				width:250,
				labelAlign:'right'
			},
			items:[{
				xtype:'textarea',
				name:'waybillNums',
				height:81,
				width:280,
				labelWidth:60,
				regex:/^[\d, ]+$/,
				regexText:i18n('i18n.serviceRecovery.queryWaybillNumberLimit'),
				fieldLabel:i18n('i18n.serviceRecovery.waybillNumber'),//'运单号',
				emptyText:i18n('i18n.serviceRecovery.inputWaybillNumberInfo'),//'可输入十个运单号，用半角逗号隔开',
				listeners:{
					'change':function(me, newValue, oldValue, eOpts ){

						var ary = new Array();
						ary=newValue.split(",");
						//如果已输入10个单号，则显示出错
						if(ary.length==10){
							me.maxLength=newValue.length+1;
							me.maxLengthText = i18n('i18n.servicerecovery.waybilltomoreexception');
							me.doComponentLayout();
						}
					}
				},
				rowspan:4
			},{
				xtype:'combo',
				name:'reductionType',
				fieldLabel:i18n('i18n.serviceRecovery.reductionType'),//'减免类别',
				editable:false,
				store:getDataDictionaryByName(DataDictionary,'SERVICERECOVERY_REDUCTION_TYPE'),
				queryMode:'local',
				displayField:'codeDesc',
				valueField:'code'
			},{
				name:'customerType',		//客户类别
				xtype:'combo',
				fieldLabel:i18n('i18n.serviceRecovery.customerType'),
				store:getDataDictionaryByName(DataDictionary,'SERVICE_RECOVERY_CUSTOMER_TYPE'),
				queryMode:'local',
				editable:false,
				displayField:'codeDesc',
				valueField:'code'

			},{
				xtype     : 'datefield',
				name      : 'startDate',
				fieldLabel: i18n('i18n.serviceRecovery.applyTime'),//申请时间',
				value:new Date(new Date().getFullYear(),new Date().getMonth(),new Date().getDate()-31),
				maxValue:new Date(),
				editable: false
			},
			{
				xtype     : 'datefield',
				name      : 'endDate',
				fieldLabel: i18n('i18n.serviceRecovery.to'),//'到',
				labelSeparator:'',
				value:new Date(),
				maxValue:new Date(),
				editable: false
			},{
				xtype:'belongdeptcombox',
				name:'applyDept',
				margin:'0 0 0 35',
				width:215,
				editable:false,
				queryMode:'local',
				displayField:'deptName',
				valueField:'id',
				fieldLabel:i18n('i18n.serviceRecovery.applyDept')//'申请部门'
			},{
				xtype:'combo',
				fieldLabel:i18n('i18n.serviceRecovery.applyStatus'),//'申请状态',
				name:'applyStatus',
				store:getDataDictionaryByName(DataDictionary,'SERVICE_RECOVERY_AAPLY_TYPE'),
				queryMode:'local',
				editable:false,
				displayField:'codeDesc',
				valueField:'code'
			},{
				xtype:'combo',
				fieldLabel:i18n('i18n.serviceRecovery.tranType'),//'运输类型',
				name:'tranType',
				store:Ext.create('SearchFormTranTypeStore',{}),
				queryMode:'local',
				value:'全部',
				editable:false,
				displayField:'codeDesc',
				valueField:'code'
			}]
		});

		/***********************************/
		if(!serviceRecoveryDtailsWin){
			serviceRecoveryDtailsWin = Ext.create('PopWindow',{
				title : i18n('i18n.serviceRecovery.lookDetails'),//'查看详情',
				id : 'detailsPop',
				closeAction:'hide',
				items : detailsPopWinForm
			});
		}
		/****************************中间按钮***************************/
		Ext.define('ButtonDemoPanel',{
			extend:'NormalButtonPanel', //--第一步,定义一个主panel,继承NormalButtonPanel
			items:null,
			initComponent:function(){
				this.items = this.getItems();
				this.callParent();
			},
			getItems:function(){
				var me = this;
				return [{
					xtype:'leftbuttonpanel', //--第二步,定义一个位于左边的按钮容器,继承leftbuttonpanel
					items:[{
							xtype:'button',
							name:'createNoticeBtn',
							text:i18n('i18n.serviceRecovery.export'),//'导出',
							handler:function(){
								var url = "../servicerecovery/exportServiceRecoveryByCondition.action";
								var successFn=function(json){
									window.location.href = "../common/downLoad.action?fileName="+json.fileName+"&inputPath="+json.filePath;
								};
								var failFn=function(){
								};
								DpUtil.requestAjax(url,params,successFn,failFn);
								var waybillNum 		=  queryForm.findField('waybillNums').getValue(),//运单号
								reductionType	=  queryForm.findField('reductionType').getValue(),//减免类别
								customerType	=  queryForm.findField('customerType').getValue(),//客户类别
								applyStatus		=  queryForm.findField('applyStatus').getValue(),//申请状态
								applyDept		=  queryForm.findField('applyDept').getValue(),//申请部门
								startDate		=  queryForm.findField('startDate').getValue(),//开始时间
								endDate			=  queryForm.findField('endDate').getValue(),//结束时间
								waybillNums = new Array();
								if(!Ext.isEmpty(waybillNum)){
									 waybillNums = waybillNum.split(',');
								}

								if(reductionType == i18n('i18n.serviceRecovery.all')){
									reductionType=null;
								}
								if(applyStatus == i18n('i18n.serviceRecovery.all')){
									applyStatus=null;
								}
								if(customerType == i18n('i18n.serviceRecovery.all')){
									customerType=null;
								}

							}
						},{
							xtype:'button',
							id:'detailsBtnId',
							name:'detailsBtn',
							disabled:true,
							text:i18n('i18n.serviceRecovery.details'),//详情
							handler:function(){
							var record = Ext.getCmp("buttomGrid")
									.getSelectionModel().getSelection();
							if(record.length==1){
								var paramDetails = {
										'serviceRecovery' : {
											'id' : record[0].data.id
										}
									};
							}else{
								return;
							}

							var successFn = function(json) {
								// 将客户类型转换为中文
								json.serviceRecovery.customerType =
									DpUtil.changeDictionaryCodeToDescrip(
												json.serviceRecovery.customerType,
												DataDictionary.SERVICE_RECOVERY_CUSTOMER_TYPE);

								// 将签收时间格式转换
								json.serviceRecovery.outboundTime =ServiceRecoveryData.prototype.formatDate(json.serviceRecovery.outboundTime);

								// 将申请时间时间格式转换
								json.serviceRecovery.applyTime =ServiceRecoveryData.prototype.formatDate(json.serviceRecovery.applyTime);

								// 将审批时间时间格式转换
								json.serviceRecovery.verifyTime =ServiceRecoveryData.prototype.formatDate(json.serviceRecovery.verifyTime);

								//将减免类别转换为中文
								json.serviceRecovery.reductionType =
									DpUtil.changeDictionaryCodeToDescrip(json.serviceRecovery.reductionType,
																	DataDictionary.SERVICERECOVERY_REDUCTION_TYPE);
								//将申请状态转换为中文SERVICE_RECOVERY_AAPLY_TYPE
								json.serviceRecovery.applyStatus =
									DpUtil.changeDictionaryCodeToDescrip(json.serviceRecovery.applyStatus,
																	DataDictionary.SERVICE_RECOVERY_AAPLY_TYPE);

								//将客户等级转换为中文SERVICE_RECOVERY_AAPLY_TYPE
								json.serviceRecovery.customerLevel =
									DpUtil.changeDictionaryCodeToDescrip(json.serviceRecovery.customerLevel,
																	DataDictionary.MEMBER_GRADE);

								var queryServiceRecoveryModel=new QueryServiceRecoveryModel(json.serviceRecovery);

								detailsPopWinForm.loadRecord(queryServiceRecoveryModel);
								detailsFileGrid.store.removeAll();
								detailsFileGrid.store.add(json.serviceRecovery.fileInfoList);
								serviceRecoveryDtailsWin.show();
							};

							var failFn = function(result) {
								MessageUtil.showErrorMes( result.message);
							};
							var url = '../servicerecovery/findServiceRecoveryById.action';
							DpUtil.requestJsonAjax(
											url,
											paramDetails,
											successFn,
											failFn);
							}
						}]
					},{
						xtype:'middlebuttonpanel' //--定义一个位于中间的空容器，用于填充								中间空白部分,继承middlebuttonpanel
					},{
						xtype:'rightbuttonpanel', //--定义一个位于右边的按钮容器,继承								rightbuttonpanel
						items:[{
							xtype:'button',    //--向右部的按钮容器中，添加具体的按钮
							name:'serachNoticeBtn',
							text:i18n('i18n.serviceRecovery.searchBtn'),//'查询',
							handler:function(){
								Ext.getCmp('detailsBtnId')
								var waybillNums=new Array();
								waybillNums= queryForm.findField('waybillNums').getValue().split(",");
								var startDate		=  queryForm.findField('startDate').getValue(),//开始时间
									endDate			=  queryForm.findField('endDate').getValue();//结束时间

								//如果运单号刚好等于十一个并且第十一个不为空的情况下，提示运单号超过十个
								if(waybillNums.length==11&&(waybillNums[10]!=null&&""!=waybillNums[10])){
									MessageUtil.showMessage(i18n("i18n.servicerecovery.waybilltomoreexception"));
									return;
								}
								//如果运单号大于十一个，提示运单号超过十个
								if(waybillNums.length>11){
									MessageUtil.showMessage(i18n("i18n.servicerecovery.waybilltomoreexception"));
									return;
								}
								//申请时间时间的起始时间大于结束时间则不能查询，并弹出提示框
								if(startDate>endDate){
									MessageUtil.showMessage(i18n('i18n.serviceRecovery.startTimeCannotMoreThanEndTime'));
									return;
								}

								if(!Ext.isEmpty(waybillNums)){
									for (var i = 0; i < waybillNums.length; i++) {
										if(Ext.util.Format.trim(waybillNums[i]).length>10){
											MessageUtil.showErrorMes(i18n('i18n.serviceRecovery.waybillNumberLengthLimit'));
											return;
										}
									}
								}

								if(Ext.getCmp('queryFormId').getForm().isValid()){
									Ext.getCmp('BBar').moveFirst();
								}
							}
						},{
							xtype:'button',
							name:'createNoticeBtn',
							text:i18n('i18n.serviceRecovery.resetBtn'),//重置,
							handler:function(){
								var form = Ext.getCmp('queryFormId').getForm();
								var value = form.findField('applyDept').getValue();
									form.reset();
									form.findField('reductionType').setValue(i18n('i18n.serviceRecovery.all'));
									form.findField('customerType').setValue(i18n('i18n.serviceRecovery.all'));
									form.findField('applyStatus').setValue(i18n('i18n.serviceRecovery.all'));
									form.findField('applyDept').setValue(value);
							}
						}]
					}]
			}
		});
		/*******************************底部gridPanel****************************/

		var pluginExpanded = true;
		var queryFormPanel = Ext.create('QueryForm',{});
		queryForm = queryFormPanel.getForm();

		queryForm.findField('applyDept').setRawValue(User.deptName);
		queryForm.findField('applyDept').getStore().on('load',function(store){
			if(isFirstLoad && isFirstLoad===true){
				isFirstLoad = false;//只有第一次才 load 赋值
				queryForm.findField('applyDept').setValue(User.deptId);
			}
		});

		//给查询条件的下拉框增加一个全部
		queryForm.findField('reductionType').getStore().add({'code':'','codeDesc':i18n('i18n.serviceRecovery.all')});
		queryForm.findField('reductionType').setValue('');
		queryForm.findField('customerType').getStore().add({'code':'','codeDesc':i18n('i18n.serviceRecovery.all')});
		queryForm.findField('customerType').setValue('');
		queryForm.findField('applyStatus').getStore().add({'code':'','codeDesc':i18n('i18n.serviceRecovery.all')});
		queryForm.findField('applyStatus').setValue('');

		var queryServiceRecoveryStore = Ext.create('QueryServiceRecoveryStore',{
			listeners:{
				load:function(t,records){
					var value = 0;
					t.each(function(record){
						value+=record.get("reductionAmount");
					});
					Ext.getCmp('totalAmount').setValue(value);
				}
			}
		});

		var params;

		//load之前加载参数
		queryServiceRecoveryStore.on('beforeload',function(queryServiceRecoveryStore,operation){
			var waybillNum 		=  queryForm.findField('waybillNums').getValue(),//运单号
				reductionType	=  queryForm.findField('reductionType').getValue(),//减免类别
				customerType	=  queryForm.findField('customerType').getValue(),//客户类别
				applyStatus		=  queryForm.findField('applyStatus').getValue(),//申请状态
				applyDept		=  queryForm.findField('applyDept').getValue(),//申请部门
				startDate		=  queryForm.findField('startDate').getValue(),//开始时间
				endDate			=  queryForm.findField('endDate').getValue(),//结束时间
				tranType		=  queryForm.findField('tranType').getValue(),//结束时间
				waybillNums = new Array();
			if(!Ext.isEmpty(waybillNum)){
				 waybillNums = waybillNum.split(',');
			}
			if(reductionType == i18n('i18n.serviceRecovery.all')){
				reductionType=null;
			}
			if(customerType == i18n('i18n.serviceRecovery.all')){
				customerType=null;
			}
			if(applyStatus == i18n('i18n.serviceRecovery.all')){
				applyStatus=null;
			}
			var serviceRecoverySearchConditionWaybillNums = new Array();
			//如果运单号刚好等于是一个并且第十一个为空，则去掉第十一个,并去掉左右空格
			if(waybillNums.length==11&&(waybillNums[10]==null||""==waybillNums[10])){
				for ( var i = 0; i < waybillNums.length-1; i++) {
					serviceRecoverySearchConditionWaybillNums[i]=Ext.util.Format.trim(waybillNums[i]);
				}
			}else if(waybillNums.length<11){
				//运单号小于十个，去掉左右空格
				for ( var i = 0; i < waybillNums.length; i++) {
					serviceRecoverySearchConditionWaybillNums[i]=Ext.util.Format.trim(waybillNums[i]);
				}
			}
			if(waybillNum==""||waybillNum==null){
				//如果没有填写运单号，则把传回后台的运单号置为null
				serviceRecoverySearchConditionWaybillNums=null;
			}
			if(!Ext.isEmpty(serviceRecoverySearchConditionWaybillNums)){
				for (var i = 0; i < serviceRecoverySearchConditionWaybillNums.length; i++) {
					if(serviceRecoverySearchConditionWaybillNums[i].length>10){
						MessageUtil.showErrorMes(i18n('i18n.serviceRecovery.waybillNumberLengthLimit'));
						return;
					}
				}

			}
			if(tranType == '全部'){
				tranType = '';
			}
			if(endDate>=startDate){//最后更新时间的起始时间小于结束时间才能执行查询
				params={
						'serviceRecoverySearchCondition.waybillNumbers'	:serviceRecoverySearchConditionWaybillNums,
						'serviceRecoverySearchCondition.reductionType'	:reductionType,
						'serviceRecoverySearchCondition.customerType'	:customerType,
						'serviceRecoverySearchCondition.applyStatus'	:applyStatus,
						'serviceRecoverySearchCondition.applyDept'		:applyDept,
						'serviceRecoverySearchCondition.startDate'		:startDate,
						'serviceRecoverySearchCondition.endDate'		:endDate,
						'serviceRecoverySearchCondition.tranType'		:tranType
				}
				Ext.apply(operation,{
					params : params
				});
			}
		});
		var gridPanel = Ext.create('SearchGridPanel',{
			id:'buttomGrid',
			store:queryServiceRecoveryStore,
			selModel:Ext.create('Ext.selection.CheckboxModel',{}),//选择框
			columns:[

				{	text:i18n('i18n.serviceRecovery.sequenceNumber'),xtype:'rownumberer'		,width:40	},//1.序号

				{	text:i18n('i18n.serviceRecovery.waybillNumber')	,dataIndex:'waybillNumber'	,width:80	},//2.运单号

				{	text:i18n('i18n.serviceRecovery.tranType'),dataIndex:'tranType'	},//3.运输类型

				{	text:i18n('i18n.serviceRecovery.oaWorkflowNum'),dataIndex:'oaWorkflowNum', width:70,	
				renderer:function(v,s,r){
    		    	return r.get('workflowNo')?"<a href=javascript:showDetailWorkflow('../workflow/showDetailWorkflow.action?processInstId="+r.get('workflowNumEnc')+"&processDefName=com.deppon.bpms.module.crm.bpsdesign.financial.serviceRecovery')>"+r.get('workflowNo')+"</a>":v;
    		    	}			
				},//3.工作流号

				{	text:i18n('i18n.serviceRecovery.deptName')		,dataIndex:'applyDeptName'		,width:170	},//4.部门名称

				{	text:i18n('i18n.serviceRecovery.subsidiary')	,dataIndex:'subsidiary'		,width:170	},//5.所属子公司

				{	text:i18n('i18n.serviceRecovery.customerName')	,dataIndex:'customerName'	,width:150	},//6.客户名称

				{	//7.客户类别
					text:i18n('i18n.serviceRecovery.customerType')	,
					dataIndex:'customerType'	,
					width:70,
					renderer:function(value){
						return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.SERVICE_RECOVERY_CUSTOMER_TYPE);
					}
				},

				{text:i18n('i18n.serviceRecovery.reductionAmount'),dataIndex:'reductionAmount',width:70},//8.减免金额

				{//9.减免类别
					text:i18n('i18n.serviceRecovery.reductionType')	,
					dataIndex:'reductionType'	,
					width:90,
					renderer:function(value){
						return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.SERVICERECOVERY_REDUCTION_TYPE);
					}
				},

				{	text:i18n('i18n.serviceRecovery.waybillAmount')	,dataIndex:'waybillAmount',width:70	},//10.开单金额

				{	//11.签收时间
					text:i18n('i18n.serviceRecovery.outboundTime')	,
					dataIndex:'outboundTime',
					width:160.5,
					renderer:function(value){
						return ServiceRecoveryData.prototype.formatDate(value);
					}
				},

				{	text:i18n('i18n.serviceRecovery.damagePackage')	,dataIndex:'damagePackage',width:70	},///12.受损件数

				{	text:i18n('i18n.serviceRecovery.empName')		,dataIndex:'operatorName',width:70	},//13.经手人即操作人姓名

				{	//14.申请人
					text:i18n('i18n.serviceRecovery.applicant')		,
					dataIndex:'applicantName',
					width:70
				},

				{	//15.申请时间
					text:i18n('i18n.serviceRecovery.applyTime')		,dataIndex:'applyTime'	,width:160.5,
					renderer:function(value){
						return ServiceRecoveryData.prototype.formatDate(value);
					}
				},
				{	//16.申请状态
					text:i18n('i18n.serviceRecovery.applyStatus'),
					dataIndex:'applyStatus'	,
					width:70,
					renderer:function(value){
						return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.SERVICE_RECOVERY_AAPLY_TYPE);
					}
				},
				{text:i18n('i18n.serviceRecovery.verifier'),dataIndex:'verifierName',width:70	},//17.审批人

				{//18.审批时间
					text:i18n('i18n.serviceRecovery.verifyTime'),
					dataIndex:'verifyTime',
					width:160.5,
					renderer:function(value){
						return ServiceRecoveryData.prototype.formatDate(value);
					}
				}

			],
			bbar:Ext.create("Ext.toolbar.Paging", {
				id : "BBar",
				store : queryServiceRecoveryStore,
				displayMsg : i18n("i18n.serviceRecovery.displayMsg"),
				displayInfo : true,
				items : ["-", {
					text : i18n("i18n.serviceRecovery.pager_prefixText"),				//'每页'
					xtype : "tbtext"
				}, Ext.create("Ext.form.ComboBox", {
					id:'showNum',
					width : 40,
					value:'30',
					editable:false,
					triggerAction : "all",
					forceSelection : true,
					editable : false,
					name : "comboItem",
					displayField : "value",
					valueField : "value",
					queryMode : "local",
					store :valueStore,
					listeners : {
						select : {
							scope : this,
							fn : function(m, l) {
								var k = Ext.getCmp("buttomGrid").store.pageSize;
								var n = parseInt(m.value);
								if(k != n) {
									Ext.getCmp("buttomGrid").store.pageSize = n;
									Ext.getCmp("BBar").moveFirst();
								}
							}
						}
					}
				}), {
					text : i18n('i18n.serviceRecovery.ones')			//'条'
					,xtype : "tbtext"
				},{
					xtype : "tbtext",
					text:i18n('i18n.serviceRecovery.totalAmount')
				},{
					xtype:'numberfield',
					id:'totalAmount',
					value:null,
					width:50,
					hideTrigger: true,
					readOnly:true
				},{
					xtype : "tbtext",
					text:i18n('i18n.serviceRecovery.RMB')
				}]
			}),
			listeners:{
				'select':function(){
					Ext.getCmp('detailsBtnId').setDisabled(false);
				}
			}
		});

	/*******************************显示viewport****************************/
		Ext.create('Ext.container.Viewport', {
			width:750,
			layout: 'border',
			border:false,
			items:[{
				layout:'fit',
				region: 'north',
				border:false,
				items:queryFormPanel
			},{
				region: 'center',
				layout :'border',
				margin:'0 5 5 5',
				border:false,
				items:[{
					region: 'north',
					height:38,
					items:Ext.create('ButtonDemoPanel',{})
				},{
					region: 'center',
					layout:'fit',
					items:gridPanel
				}]
			}]

		});
	});