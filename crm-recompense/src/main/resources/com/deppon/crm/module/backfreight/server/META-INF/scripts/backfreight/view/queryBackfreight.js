/**
 * 第一次加载才赋值
 */	
isFirstLoad = true;
/**
	 * 
	 * @description ：退运费查询界面
	 * @author 邹明
	 * @version 1.0
	 * @date 2012-11-17 9:55
	 * @update2012-12-19 15:17
	 */

	var backFreightDetailsWin;
	Ext.onReady(function(){
		var keys = [
		            'MEMBER_GRADE',//客户等级
			        'BACKFREIGHT_TRANSPORT_TYPE',//运输性质  
		            'BACKFREIGHT_PAY_TYPE',//付款方式
		    		'backFreight_REDUCTION_TYPE',//减免类别
		    		'BACKFREIGHT_AAPLY_TYPE'//申请状态
		    	];
		initDataDictionary(keys);//初始化数据字典的
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
				id:'waybillNums',
				labelAlign:'right',
				height:102,
				labelWidth:60,
				width:280,
				regex:/^[\d, ]+$/,
				regexText:i18n('i18n.backFreight.queryWaybillNumberLimit'),
				fieldLabel:i18n('i18n.backFreight.waybillNumber'),//'运单号',
				emptyText:i18n('i18n.backFreight.inputWaybillNumberInfo'),//'可输入十个运单号，用半角逗号隔开',
				rowspan:4,
				listeners:{
					'change':function(me, newValue, oldValue, eOpts){
						var ary = new Array();
						ary=newValue.split(",");
						if(ary.length==10){
							me.maxLength=newValue.length+1;
							me.maxLengthText = i18n('i18n.backfreight.waybilltomoreexception');
							me.doComponentLayout();
						}
					}
				}
				
			},{
				name:'subsidiary',
				maxLength:100,
				maxLengthText:i18n('i18n.backFreight.subsidiaryLimit'),
				fieldLabel:i18n('i18n.backFreight.subsidiary'),//所属子公司',
				editable:false
			},{
				xtype:'combo',
				name:'tranType',		//运输性质BACKFREIGHT_TRANSPORT_TYPE
				fieldLabel:i18n('i18n.backFreight.tranType'),//'运输性质',
				store:getDataDictionaryByName(DataDictionary,'BACKFREIGHT_TRANSPORT_TYPE'),
				queryMode:'local',
				editable:false,
				displayField:'codeDesc',
				valueField:'code'
			},{
				xtype     : 'datefield',
				name      : 'startDate',
				fieldLabel: i18n('i18n.backFreight.applyTime'),//申请时间',
				maxValue:new Date(),
				value:new Date(new Date().getFullYear(),new Date().getMonth(),new Date().getDate()-31),
//				value:new Date(),
				allowBlank:false,
				blankText:i18n('i18n.backFreight.dateCanNotNull'),//'申请时间不能为空',
				editable: false
			},
			{	
				xtype     : 'datefield',
//				format: 'Y-m-d H:i:s',
				name      : 'endDate',
				fieldLabel: i18n('i18n.backFreight.to'),//'到',
				labelSeparator:'',
				maxValue:new Date(),
				value:new Date(),
				allowBlank:false,
				blankText:i18n('i18n.backFreight.dateCanNotNull'),
				editable: false
			},{
				xtype:'belongdeptcombox',
				name:'applyDept',
				width:215,
				margin:'0 0 0 35',
				editable:false,
				fieldLabel:i18n('i18n.backFreight.applyDept')//'申请部门'
			},{
				xtype:'combo',
				name:'paymentType',
				fieldLabel:i18n('i18n.backFreight.payType'),//'付款方式',//'付款方式,
				store:getDataDictionaryByName(DataDictionary,'BACKFREIGHT_PAY_TYPE'),
				queryMode:'local',
				editable:false,
				displayField:'codeDesc',
				valueField:'code'
				
			},{
				xtype:'combo',
				name:'applyStatus',
				fieldLabel:i18n('i18n.backFreight.applyStatus'),//'申请状态',
				store:getDataDictionaryByName(DataDictionary,'BACKFREIGHT_AAPLY_TYPE'),
				queryMode:'local',
				editable:false,
				displayField:'codeDesc',
				valueField:'code'
			}]
		});
		var params;
		/****************************中间按钮***************************/
		
		backFreightDetailsWin = Ext.create('PopWindow',{
			title : i18n('i18n.backFreight.lookDetails'),//'查看详情',
			id : 'detailsPop',
			closeAction:'hide',
			items : backFreightDetailsPopWinForm
		});
		
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
								name:'exportBtn',
								text:i18n('i18n.backFreight.export'),//'导出',
								handler:function(){
									var url = "../backfreight/exportBackFreightByCondition.action";
									var successFn=function(json){
										window.location.href = "../common/downLoad.action?fileName="+json.fileName+"&inputPath="+json.filePath;
									};
									var failFn=function(){
									};
									DpUtil.requestAjax(url,params,successFn,failFn);
								}
							},{
								xtype:'button',
								id:'detailsBtnId',
								name:'detailsBtn',
								disabled:true,
								text:i18n('i18n.backFreight.details'),//详情
								handler:function(){
									var record = Ext.getCmp("buttomGrid")
												.getSelectionModel().getSelection();
									if(record.length==1){
										var paramDetails = {
												'backFreightId' : record[0].data.id
										};
									}else{
										return;
									}
									
									
									var successFn = function(json) {
										// 将签收时间格式转换
										json.backFreight.outboundTime =BackFreightData.prototype.formatDate(json.backFreight.outboundTime);
										
										// 将申请时间格式转换
										json.backFreight.applyTime =BackFreightData.prototype.formatDate(json.backFreight.applyTime);
										
										// 将审批时间格式转换
										json.backFreight.verifyTime =BackFreightData.prototype.formatDate(json.backFreight.verifyTime);
										
										//转变申请状态的格式
										json.backFreight.applyStatus = 
											DpUtil.changeDictionaryCodeToDescrip(json.backFreight.applyStatus,
																			DataDictionary.BACKFREIGHT_AAPLY_TYPE);
										//转变运输方式的格式
										json.backFreight.tranType = 
											DpUtil.changeDictionaryCodeToDescrip(json.backFreight.tranType,
																			DataDictionary.BACKFREIGHT_TRANSPORT_TYPE);
										
										//转变客户等级的格式
										json.backFreight.customerLevel = 
											DpUtil.changeDictionaryCodeToDescrip(json.backFreight.customerLevel,
																			DataDictionary.MEMBER_GRADE);
										
										//转变付款方式的格式
										json.backFreight.paymentType = 
											DpUtil.changeDictionaryCodeToDescrip(json.backFreight.paymentType,
																			DataDictionary.BACKFREIGHT_PAY_TYPE);
										
										var queryBackFreightModel=new QueryBackFreightModel(json.backFreight);
										
										backFreightDetailsPopWinForm.loadRecord(queryBackFreightModel);
										detailsFileGrid.store.removeAll();
										detailsFileGrid.store.add(json.backFreight.fileInfoList);
										
										backFreightDetailsWin.show();
			
									};
									var failFn = function(result) {
											MessageUtil.showErrorMes( result.message);
									};
									BackFreightData.prototype.findBackFreightById(paramDetails,successFn,failFn);
								}
							}]
					},{
						xtype:'middlebuttonpanel' //--定义一个位于中间的空容器，用于填充								中间空白部分,继承middlebuttonpanel
					},{
						xtype:'rightbuttonpanel', //--定义一个位于右边的按钮容器,继承								rightbuttonpanel
						items:[{
							xtype:'button',    //--向右部的按钮容器中，添加具体的按钮
							name:'serachNoticeBtn',
							text:i18n('i18n.backFreight.searchBtn'),//'查询',
							handler:function(){
								Ext.getCmp('detailsBtnId').setDisabled(true);
								var waybillNums=new Array();
								var waybillValue = Ext.getCmp('queryFormId').getForm().findField('waybillNums').getValue();
								waybillNums= waybillValue.split(",");
								var startDate		=  queryForm.findField('startDate').getValue(),//开始时间
									endDate			=  queryForm.findField('endDate').getValue();//结束时间
								
								//如果运单号刚好等于十一个并且第十一个不为空的情况下，提示运单号超过十个
								if(waybillNums.length==11&&(waybillNums[10]!=null&&""!=waybillNums[10])){
									MessageUtil.showMessage(i18n("i18n.backfreight.waybilltomoreexception"));
									return;
								}
								
								//如果运单号大于十一个，提示运单号超过十个
								if(waybillNums.length>11){
									MessageUtil.showMessage(i18n("i18n.backfreight.waybilltomoreexception"));
									return;
								}
								//申请时间时间的起始时间大于结束时间则不能查询，并弹出提示框
								if(waybillValue==""||null==waybillValue){
									if(startDate>endDate){
										MessageUtil.showMessage(i18n('i18n.backFreight.startTimeCannotMoreThanEndTime'));
										return;
									}
								}
								
								//如果有运单号大于10位，提示错误
								if(!Ext.isEmpty(waybillNums)){
									for (var i = 0; i < waybillNums.length; i++) {
										if(Ext.util.Format.trim(waybillNums[i]).length>10){
											MessageUtil.showErrorMes(i18n('i18n.backFreight.WaybillNumberLengthLimit'));
											return;
										}
									}
								}
								
								if(Ext.getCmp('queryFormId').getForm().isValid()){
									Ext.getCmp("BBar").moveFirst();
								}
							}
						},{
							xtype:'button',
							name:'resetBtn',
							text:i18n('i18n.backFreight.resetBtn'),//重置,
							handler:function(){
								var form = Ext.getCmp('queryFormId').getForm();
								var value = form.findField('applyDept').getValue();
									form.reset();
									form.findField('tranType').setValue(i18n('i18n.backFreight.all'));
									form.findField('applyDept').setValue(value);
									form.findField('paymentType').setValue(i18n('i18n.backFreight.all'));
									form.findField('applyStatus').setValue(i18n('i18n.backFreight.all'));
							}
						}]
					}]
			}
		});
		/*******************************底部gridPanel****************************/
		
		var pluginExpanded = true;
		var queryFormPanel = Ext.create('QueryForm',{});
		queryForm = queryFormPanel.getForm();
		
		//给申请部门设置一个默认值
		queryForm.findField('applyDept').setRawValue(User.deptName);
		queryForm.findField('applyDept').getStore().on('load',function(store){
			if(isFirstLoad && isFirstLoad===true){
				isFirstLoad = false;//只有第一次才 load 赋值
				queryForm.findField('applyDept').setValue(User.deptId);
			}
		});

		//给查询条件的下拉框增加一个全部
		queryForm.findField('tranType').getStore().add({'code':'','codeDesc':i18n('i18n.backFreight.all')});
		queryForm.findField('tranType').setValue('');
		queryForm.findField('paymentType').getStore().add({'code':'','codeDesc':i18n('i18n.backFreight.all')});
		queryForm.findField('paymentType').setValue('');
		queryForm.findField('applyStatus').getStore().add({'code':'','codeDesc':i18n('i18n.backFreight.all')});
		queryForm.findField('applyStatus').setValue('');
		
		var querybackFreightStore = Ext.create('QueryBackFreightStore',{
			listeners:{
				load:function(me,records){
					var value = 0;
					me.each(function(record){
						value+=record.get('applyAmount');
					});
					Ext.getCmp('totalAmount').setValue(value);
				}
			}
		});
		
		querybackFreightStore.on('beforeload',function(querybackFreightStore,operation){
			var waybillNum 		= 	queryForm.findField('waybillNums').getValue(),//1.运单号
				subsidiary		= 	queryForm.findField('subsidiary').getValue(),//2.子公司
				tranType		= 	queryForm.findField('tranType').getValue(),//3.客户类别
				startDate		= 	queryForm.findField('startDate').getValue(),//4.开始时间
				endDate			=  	queryForm.findField('endDate').getValue(),//5.结束时间
				applyStatus		=  	queryForm.findField('applyStatus').getValue(),//6.申请状态
				applyDept		= 	queryForm.findField('applyDept').getValue(),//7.申请部门
				paymentType		=	queryForm.findField('paymentType').getValue(),//8.付款方式	
				waybillNums = new Array();
			var backFreightSearchConditionWaybillNums = new Array();
			
			if(!Ext.isEmpty(waybillNum)){
				 waybillNums = waybillNum.split(',');
			}
			
			//如果运单号刚好等于是一个并且第十一个为空，则去掉第十一个,并去掉左右空格
			if(waybillNums.length==11&&(waybillNums[10]==null||""==waybillNums[10])){
				for ( var i = 0; i < waybillNums.length-1; i++) {
					backFreightSearchConditionWaybillNums[i]=Ext.util.Format.trim(waybillNums[i]);
				}
			}else if(waybillNums.length<11){
				//运单号小于十个，去掉左右空格
				for ( var i = 0; i < waybillNums.length; i++) {
					backFreightSearchConditionWaybillNums[i]=Ext.util.Format.trim(waybillNums[i]);
				}
			}
			//如果运单填写为空，置为空后传给后台
			if(waybillNum==null||""==waybillNum){
				backFreightSearchConditionWaybillNums=null;
			}
			
			if(!Ext.isEmpty(backFreightSearchConditionWaybillNums)){
				for (var i = 0; i < backFreightSearchConditionWaybillNums.length; i++) {
					if(backFreightSearchConditionWaybillNums[i].length>10){
						MessageUtil.showErrorMes(i18n('i18n.backFreight.WaybillNumberLengthLimit'));
						return;
					}
				}
			}
			
			if(tranType == i18n('i18n.backFreight.all')){
				tranType=null;
			}
			if(applyStatus == i18n('i18n.backFreight.all')){
				applyStatus=null;
			}
			if(paymentType == i18n('i18n.backFreight.all')){
				paymentType=null;
			}
			Ext.apply(operation,{	
				params : {
					'backFreightSearchCondition.waybillNumbers'	:backFreightSearchConditionWaybillNums,
					'backFreightSearchCondition.subsidiary'		:subsidiary,
					'backFreightSearchCondition.tranType'		:tranType,
					'backFreightSearchCondition.startDate'		:startDate,
					'backFreightSearchCondition.endDate'		:endDate,
					'backFreightSearchCondition.applyStatus'	:applyStatus,
					'backFreightSearchCondition.applyDept'		:applyDept,
					'backFreightSearchCondition.paymentType'	:paymentType
				}
			});
		});
		
		var gridPanel = Ext.create('SearchGridPanel',{
			id:'buttomGrid',
			store:querybackFreightStore,
			selModel:Ext.create('Ext.selection.CheckboxModel',{mode:'single'}),//选择框
			columns:[
				
				{	text:i18n('i18n.backFreight.sequenceNumber'),xtype:'rownumberer'		,width:40	},//1.序号
				
				{	text:i18n('i18n.backFreight.waybillNumber')	,dataIndex:'waybillNumber'	,width:75	},//2.运单号
				
				{	text:i18n('i18n.backFreight.oaWorkflowNum'),dataIndex:'oaWorkflowNum'	,width:70	},//3.工作流号
				
				{	text:i18n('i18n.backFreight.deptName')		,dataIndex:'applyDeptName'	,width:160	},//4.部门名称
				
				{	text:i18n('i18n.backFreight.subsidiary')	,dataIndex:'subsidiary'		,width:170	},//5.所属子公司
				
				{	text:i18n('i18n.backFreight.customerName')	,dataIndex:'customerName'	,width:80	},//6.客户名称
				
				{	//6.运输性质
					text:i18n('i18n.backFreight.tranType')	,
					dataIndex:'tranType'	,
					width:80,
					renderer:function(value){
						return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.BACKFREIGHT_TRANSPORT_TYPE);
					}
				},
				
				{	//6.付款方式
					text:i18n('i18n.backFreight.payType')	,
					dataIndex:'paymentType'	,
					width:70,	
					renderer:function(value){
						return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.BACKFREIGHT_PAY_TYPE);
					}
				},
				
				{	text:i18n('i18n.backFreight.applyAmount'),dataIndex:'applyAmount',width:70	},//8.申请金额
				
				{	text:i18n('i18n.backFreight.freight'),dataIndex:'waybillAmount',width:70},//8.运费
				
				{	//11.签收时间
					text:i18n('i18n.backFreight.outboundTime')	,
					dataIndex:'outboundTime',
					width:160.5,
					renderer:function(value){
						return BackFreightData.prototype.formatDate(value);
					}	
				},
				
				{	//14.申请人
					text:i18n('i18n.backFreight.applicant')		,
					dataIndex:'applicantName',
					width:70
				},
				
				{	//15.申请时间
					text:i18n('i18n.backFreight.applyTime')		,
					dataIndex:'applyTime'	,
					width:160.5,
					renderer:function(value){
						return BackFreightData.prototype.formatDate(value);
					}		
				},
				{	//16.申请状态
					text:i18n('i18n.backFreight.applyStatus'),	
					dataIndex:'applyStatus'	,
					width:70,	
					renderer:function(value){
						return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.BACKFREIGHT_AAPLY_TYPE);
					}
				},
				
				//17.审批人
				{text:i18n('i18n.backFreight.verifier'),dataIndex:'verifierName',width:70	},//17.审批人
				
				{//18.审批时间
					text:i18n('i18n.backFreight.verifyTime'),
					dataIndex:'verifyTime',
					width:160.5,
					renderer:function(value){
						return BackFreightData.prototype.formatDate(value);
					}	
				}
			],
			bbar:Ext.create("Ext.toolbar.Paging", {
				id : "BBar",
				store : querybackFreightStore,
				displayMsg : i18n("i18n.backFreight.displayMsg"),
				displayInfo : true,
				items : ["-", {
					text : i18n("i18n.backFreight.pager_prefixText"),				//'每页'
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
									Ext.getCmp("BBar").moveFirst()
								}
							}
						}
					}
				}), {
					text : i18n('i18n.backFreight.ones')			//'条'
					,xtype : "tbtext"
				},"-",{
					xtype : "tbtext",
					text:i18n('i18n.backFreight.totalAmount')
				},{
					xtype:'numberfield',
					id:'totalAmount',
					value:null,
					width:40,
					hideTrigger: true,
					readOnly:true 
				},{
					xtype : "tbtext",
					text:i18n('i18n.backFreight.RMB')
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
