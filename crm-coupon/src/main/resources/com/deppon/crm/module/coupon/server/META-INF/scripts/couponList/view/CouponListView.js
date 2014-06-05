/**
 * 优惠券查询界面
 * 肖红叶
 * 2012-11
 */

//优惠券查询条件，用以进行优惠券“查询”和“导出全部”功能的实现
var conditionUnderDept = null;
var conditionPlanName = null;
var conditionCouponNumber = null;
var conditionSendtelPhone = null;
var conditionStatus = null;
var conditionUseTimeStart = null;
var conditionUseTimeEnd = null;
var conditionSendTimeStart = null;
var conditionSendTimeEnd = null;
var conditionplanNumber = null;

Ext.onReady(function(){	
	//初始化提示信息条
	Ext.QuickTips.init();
	
	var keys=[
  		// 行业
  		'TRADE',
  	  	//会员等级
  		'MEMBER_GRADE',
  		//费用类型
  		'COUPON_COST_TYPE',
  		//金额要求
  		'COUPON_AMOUNT_TYPE',
  		//产品类型
  		'FOSS_PRODUCT_TYPE',
		//增值费
		'COUPON_APPRECIATION_TYPE'
  	];
    //初始化业务参数
    initDataDictionary(keys);
    //初始化订单来源
    initOrderSources();
	//初始化用户部门等基本信息
	initDeptAndUserInfo();
	/**
	 *优惠券查询条件输入面板form
	 */
	Ext.define('CouponQueryConditionPanel',{
		extend:'SearchFormPanel', 
		items:null,
		border:0,
		layout:{
			type:'table',
			columns:4
		},
		defaults:{
			labelWidth:80,
			labelAlign:'right',
			width:240
		},
		defaultType:'textfield',
		initComponent:function(){			
			this.items = this.getItems();
			this.callParent();
		},
		getItems:function(){
			var me = this;
			return [
			    {//部门
			    	fieldLabel : i18n('i18n.coupon.department'),
					xtype:'combobox',
					id:'underDept',
					name : 'underDept',
					store:Ext.create('DeptStore',{
						listeners:{
							beforeload:function(store, operation){
								Ext.apply(operation,{
									params : {
											'deptName':Ext.getCmp("underDept").getRawValue()
										}
									}
								);	
							}
						}
					}),
					triggerAction : 'all',
					displayField:'deptName',
					valueField:'standardCode',
					emptyText:'请输入部门关键字',
					forceSelection :true,
					hideTrigger:false,
					pageSize: 10,
					minChars:2,
					queryDelay:800,
					listConfig: {
		  	        	minWidth :300,
		  	            getInnerTpl: function() {
		  	            	 return '{deptName}';
		  	            }
		  	        },
					listeners:{
						change:function(combo){
							if(Ext.isEmpty(combo.getValue())){
								combo.setValue("");
							}
						}
					}
			    },{//优惠券编码
					fieldLabel:i18n('i18n.coupon.couponNumber'),
					name:'couponNumber',
					id:'couponNumber',
					maxLength : 20,
					maxLengthText : i18n('i18n.coupon.maxLength')+20
				},{//优惠券状态
					xtype:'combo',
					store:Ext.create('StatusStore'),
					displayField:'codeDesc',
					editable:false,
					valueField:'code',
					value:'00',
					colspan:2,
					queryMode:'local',
					fieldLabel:i18n('i18n.coupon.status'),
					name:'status',
					id:'status'
				},{//营销计划名称
					xtype:'queryCombox',
					queryMode:'local',
					fieldLabel:i18n('i18n.coupon.planName'),
					name:'planName',
					editable:false,
					id:'planName',
					maxLength : 80,
					maxLengthText : i18n('i18n.coupon.maxLength')+80,
					listeners:{
						expand:{
							fn:function(){Ext.create('SearchMarketPlanByMessageWindow').show()}
						}
					}
				},{//发送时间
					xtype:'datefield',
					fieldLabel:i18n('i18n.coupon.sendTime'),
					format:'Y-m-d',
					editable:false,
					name:'sendTimeStart',
					id:'sendTimeStart'
				},{//发送时间到
					xtype:'datefield',
					fieldLabel:i18n('i18n.coupon.createEndTime'),
					labelSeparator:'',
					format:'Y-m-d',
					editable:false,
					name:'sendTimeEnd',
					id:'sendTimeEnd'
				},{//营销计划编码
					xtype:'hiddenfield',
					name:'planNumber',
					id:'planNumber',
					width:10,
					labelWidth:5
				},{//手机号码
					fieldLabel:i18n('i18n.coupon.telephone'),
					name:'sendtelPhone',
					maxLength :20,
					maxLengthText : i18n('i18n.coupon.maxLength')+20,
					regex : /^\d{0,20}$/,
					regexText:i18n('i18n.coupon.inputRightTel'),
					id:'sendtelPhone'
				},{//使用时间
					xtype:'datefield',
					fieldLabel:i18n('i18n.coupon.useTime'),
					format:'Y-m-d',
					editable:false,
					name:'useTimeStart',
					id:'useTimeStart'
				},{//使用时间到
					xtype:'datefield',
					fieldLabel:i18n('i18n.coupon.createEndTime'),
					format:'Y-m-d',
					labelSeparator:'',
					editable:false,
					name:'useTimeEnd',
					colspan:2,
					id:'useTimeEnd'
				}
		    ];
		}
	});
	
	
	//操作按钮面板
	Ext.define('CouponQueryButtonPanel',{
		extend:'NormalButtonPanel',
		border:false,
		initComponent:function(){
			this.items = this.getItems();
			this.callParent();
		},
		getItems:function(){
			var me = this;
			return [
			   {
				xtype:'leftbuttonpanel',
				width:330,
				items:[
				  {//查看使用规则
					  xtype:'button',
					  text:i18n('i18n.coupon.showUseRule'),
					  handler:function(){
						  //显示详细信息
						  showDetail();
					  }
				  }, {//重发短信
					  text:i18n('i18n.coupon.resendMsg'),
					  xtype:'button',
					  handler:function(btn){
						  btn.disable();
						  //获取选中的表格
						  var selection=Ext.getCmp('couponQueryResultGridId').getSelectionModel().getSelection();
						  
						  if (selection.length == 0) {
							btn.enable();
							MessageUtil.showErrorMes(i18n('i18n.coupon.selectRecord'));
							return false;
						  }
						  if (selection.length != 1) {
							btn.enable();
							MessageUtil.showErrorMes(i18n('i18n.coupon.selectOneRecord'));
							return false;
						  }
						  //判断优惠券类型是否为手动发券,只有当优惠券类型为手动发券时才可进行短信发券
						  if(selection[0].get("status")!='20'){
							  btn.enable();
							  MessageUtil.showErrorMes(i18n('i18n.coupon.onlySendedCoupon'));
							  return false;
						  }
						  
						  var successFn = function(json){
							  btn.enable();
							  MessageUtil.showInfoMes(i18n('i18n.coupon.successToResendMsg'));  
							}
						  var param = {
							'couponNumber':selection[0].get("couponNumber")
							};
							var failureFn=function(json){
								btn.enable();
								MessageUtil.showErrorMes(json.message);
							}
							CouponListStore.prototype.reSendCouponMsg(param,successFn,failureFn);
					  }
				  },{//导出全部
					  text:i18n('i18n.coupon.importAll'),
					  xtype:'button',
					  handler:function(btn){
						  btn.disable();
						  //判断是否可以进行导出操作
						  if( Ext.getCmp('couponQueryResultGridId').store.data.length == 0){
							  btn.enable();
							  MessageUtil.showErrorMes(i18n('i18n.coupon.canNotImportNullGrid'));
							  return false;
						  }
						  
						  //进度条
						  Ext.MessageBox.show({
								title:i18n('i18n.coupon.importAll'),
							    msg:i18n('i18n.coupon.importAlling'),
							    wait:true
						  });
						  var successFn = function(json){
							 window.location.href = "../coupon/exportCouponReportExcel.action?fileName="
								 +json.fileName+"&filePath="+json.filePath;
							 btn.enable();
							 //隐藏进度条
							 Ext.MessageBox.hide();
							 MessageUtil.showInfoMes(i18n('i18n.coupon.successToExportCouponList'));
						  }
						  var param = {
									'couponSearchCondition.underDept':conditionUnderDept,
									'couponSearchCondition.planName':conditionPlanName,
									'couponSearchCondition.couponNumber':conditionCouponNumber,
									'couponSearchCondition.sendtelPhone':conditionSendtelPhone,
									'couponSearchCondition.status':conditionStatus,
									'couponSearchCondition.useTimeStart':conditionUseTimeStart,
									'couponSearchCondition.useTimeEnd':conditionUseTimeEnd,
									'couponSearchCondition.sendTimeStart':conditionSendTimeStart,
									'couponSearchCondition.sendTimeEnd':conditionSendTimeEnd,
									'couponSearchCondition.planNumber':conditionplanNumber
							};
						 var failureFn=function(json){
							btn.enable();
							//隐藏进度条
							Ext.MessageBox.hide();
							if(Ext.isEmpty(json)){
								MessageUtil.showErrorMes('抱歉，导出超时！');
//								MessageUtil.showErrorMes(i18n('i18n.coupon.failureToExport'));
							}
							else{
								MessageUtil.showErrorMes(json.message);
							}

						 }
						 CouponListStore.prototype.creatCouponReportExcel(param,successFn,failureFn);							 			  
					  }
				  }]
			   },{
				 xtype:'middlebuttonpanel' 
			   },{
				xtype : 'rightbuttonpanel',
				items : [{//查询
					xtype : 'button',
					text : i18n('i18n.coupon.query'),
					handler:function(){						
						var sendTimeStart = Ext.getCmp('sendTimeStart').getValue();
						var sendTimeEnd = Ext.getCmp('sendTimeEnd').getValue();
						var useTimeStart = Ext.getCmp('useTimeStart').getValue();
						var useTimeEnd = Ext.getCmp('useTimeEnd').getValue();
						
						var sendTimeDays = CouponUtil.compareTwoDate(sendTimeStart,sendTimeEnd);
						var sendTimeDaysByMonth = CouponUtil.compareTwoDateByMonth(sendTimeStart,sendTimeEnd,1);
						var useTimeDays = CouponUtil.compareTwoDate(useTimeStart,useTimeEnd);
						var useTimeDaysByMonth = CouponUtil.compareTwoDateByMonth(useTimeStart,useTimeEnd,1);
						//营销计划名称为空时，使用时间范围与发送时间范围必填其一，且查询时间范围最长为一个月
						if((Ext.isEmpty(Ext.getCmp('planName').getValue())||
								Ext.isEmpty(Ext.getCmp('planName').getValue().trim()))&&
								(Ext.isEmpty(Ext.getCmp('sendtelPhone').getValue())||
								Ext.isEmpty(Ext.getCmp('sendtelPhone').getValue().trim()))&&
								(Ext.isEmpty(Ext.getCmp('couponNumber').getValue())||
								Ext.isEmpty(Ext.getCmp('couponNumber').getValue().trim()))){
							if((Ext.isEmpty(sendTimeStart)||Ext.isEmpty(sendTimeEnd))&&
									(Ext.isEmpty(useTimeStart)||Ext.isEmpty(useTimeEnd))){
								MessageUtil.showErrorMes(i18n('i18n.coupon.canNotQueryOfNullPlanName'));
								return false;
							}
							
							if(sendTimeDays < 0){
								MessageUtil.showErrorMes(i18n('i18n.coupon.sendTimeError'));
								return false;
							}
							
							
							if(sendTimeDaysByMonth){
								MessageUtil.showErrorMes(i18n('i18n.coupon.sendTimeExceedError'));
								return false;
							}
							
							if(useTimeDays < 0){
								MessageUtil.showErrorMes(i18n('i18n.coupon.useTimeError'));
								return false;
							}
							
							if(useTimeDaysByMonth){
								MessageUtil.showErrorMes(i18n('i18n.coupon.useTimeExceedError'));
								return false;
							}
						}
						if(!(Ext.getCmp('couponQueryConditionPanelId').getForm().isValid())){
							return false;
						}
						Ext.getCmp('couponQueryResultGridId').store.loadPage(1);
						
						
					}
				 },{//重置
				    text:i18n('i18n.coupon.reset'),
				    xtype:'button',
				    handler:function(){
					  //重置按钮功能实现区
				    	Ext.getCmp('couponQueryConditionPanelId').getForm().reset();	  
				  }
				 }]
			}];
		}
	});
	
	/**
	 * 优惠券查询结果表格
	 */
	Ext.define('CouponQueryResultGrid',{
		extend:'SearchGridPanel',
		defaults:{
			align:'center'
		},
		autoScroll:true,
		selModel:Ext.create('Ext.selection.CheckboxModel',{mode:'SIGLE',id:'selModelId'}),
		initComponent:function(){ 
			var me = this;
			var store = Ext.create('CouponQueryListStore',{id:'couponQueryListStoreId'});
			store.on('beforeload',function(store,operation,e){					
			  //封装查询条件传递给action
			  var couponQueryConditionForm = Ext.getCmp("couponQueryConditionPanelId");
			  //为优惠券查询条件的全局变量赋值
			  conditionUnderDept = couponQueryConditionForm.getForm().findField('underDept').getValue();
			  conditionPlanName = couponQueryConditionForm.getForm().findField('planName').getValue();
			  conditionCouponNumber = couponQueryConditionForm.getForm().findField('couponNumber').getValue().toUpperCase();
			  conditionSendtelPhone = couponQueryConditionForm.getForm().findField('sendtelPhone').getValue();
			  conditionStatus = couponQueryConditionForm.getForm().findField('status').getValue();
			  conditionUseTimeStart = couponQueryConditionForm.getForm().findField('useTimeStart').getValue();
			  conditionUseTimeEnd = couponQueryConditionForm.getForm().findField('useTimeEnd').getValue();
			  conditionSendTimeStart = couponQueryConditionForm.getForm().findField('sendTimeStart').getValue();
			  conditionSendTimeEnd = couponQueryConditionForm.getForm().findField('sendTimeEnd').getValue();
			  conditionplanNumber = couponQueryConditionForm.getForm().findField('planNumber').getValue();
			  var searchParams = {
						'couponSearchCondition.underDept':conditionUnderDept,
						'couponSearchCondition.planName':conditionPlanName,
						'couponSearchCondition.couponNumber':conditionCouponNumber,
						'couponSearchCondition.sendtelPhone':conditionSendtelPhone,
						'couponSearchCondition.status':conditionStatus,
						'couponSearchCondition.useTimeStart':conditionUseTimeStart,
						'couponSearchCondition.useTimeEnd':conditionUseTimeEnd,
						'couponSearchCondition.sendTimeStart':conditionSendTimeStart,
						'couponSearchCondition.sendTimeEnd':conditionSendTimeEnd,
						'couponSearchCondition.planNumber':conditionplanNumber
				};
				Ext.apply(operation,{
						params : searchParams
				});
			});		
			me.store = store;
			me.columns = [
			    {//序号
			    	xtype:'rownumberer',
					header:i18n('i18n.coupon.rownumberer'),
					width:40
			    },{//营销计划名称
					header:i18n('i18n.coupon.planName'),
					width:120,
					dataIndex:'planName',
					renderer:function(value){
						if(!Ext.isEmpty(value)){
							return '<span data-qtip="'+value+'">'+value+'</span>';
						}
					}
			    },{ //优惠券编码
					header :i18n('i18n.coupon.couponNumber'),
					dataIndex:'couponNumber',
					width:120,
					renderer:function(value){
						if(!Ext.isEmpty(value)){
							return '<span data-qtip="'+value+'">'+value+'</span>';
						}
					}
			    },{//优惠券金额
					header :i18n('i18n.coupon.useCouponValue'),
					dataIndex:'useCouponValue',
					width:80
			    },{//优惠券状态
					header :i18n('i18n.coupon.status'),
					dataIndex:'status',
					width:80,
					renderer:function(val){
						if(!Ext.isEmpty(val)){
							if(val=='10'){
								return '未发送';
							}
							else if(val=='20'){
								return '已发送';
							}
							else if(val=='30'){
								return '已使用';
							}
							else {
								return '已过期';
							}	
						}
					}
			    },{//生效日期
					header :i18n('i18n.coupon.autoBeginTime'),
					renderer : CouponUtil.renderDate,
					dataIndex:'begintime',
					width:90
			    },{//失效日期
					header :i18n('i18n.coupon.autoEndTime'),
					dataIndex:'endtime',
					renderer : CouponUtil.renderDate,
					width:90
			   },{//金额要求
					header :i18n('i18n.coupon.costMode'),
					dataIndex:'value',
					width:70
			    },{//来源运单号
			    	header :i18n('i18n.coupon.sourceWBNumber'),
					dataIndex:'sourceWBNumber',
					width:80,
					renderer:function(value){
						if(!Ext.isEmpty(value)){
							return '<span data-qtip="'+value+'">'+value+'</span>';
						}
					}
			    },{//来源运单金额
			    	header :i18n('i18n.coupon.sourceWBValue'),
					width:100,
					dataIndex:'sourceWBValue'
			    },{ //使用运单号
			    	header :i18n('i18n.coupon.useWBNumber'),
					dataIndex:'useWBNumber',
					width:80,
					renderer:function(value){
						if(!Ext.isEmpty(value)){
							return '<span data-qtip="'+value+'">'+value+'</span>';
						}
					}
			    },{//使用运单金额
			    	header :i18n('i18n.coupon.useWBValue'),
					dataIndex:'useWBValue',
					width:100
			    },{//发送手机号码
			    	header :i18n('i18n.coupon.sendtelPhone'),
					dataIndex:'sendtelPhone',
					width:100
			    },{//发送时间
			    	header :i18n('i18n.coupon.sendTime'),
					renderer : CouponUtil.renderDateDetail,
					dataIndex:'sendTime',
					width:140
			    },{//使用手机号码
			    	header :i18n('i18n.coupon.usetelPhone'),
					dataIndex:'usetelPhone',
					width:100
			   },{//使用时间
				    header :i18n('i18n.coupon.useTime'),
					renderer : CouponUtil.renderDate,
					dataIndex:'useTime',
					width:90
			    },{//优惠券归属部门
			    	header :i18n('i18n.coupon.underDept'),
					dataIndex:'underDeptName',
					width:140,
					renderer:function(value){
						if(!Ext.isEmpty(value)){
							return '<span data-qtip="'+value+'">'+value+'</span>';
						}
					}
			    }
		    ];
			me.dockedItems=[{
				xtype:'pagingtoolbar',
				cls:'pagingtoolbar',
				store:store,
				dock:'bottom',
				displayInfo : true,
				autoScroll : true,
				items:[{//每页显示
					text: i18n('i18n.coupon.performPerPage'),
					xtype: 'tbtext'
				},Ext.create('Ext.form.ComboBox', {
	               width:window.screen.availWidth*0.0391,
	               triggerAction:'all',
	               forceSelection: true,
	               value:'20',
	               editable:false,
	               name:'comboItem',
	               displayField:'value',
	               valueField:'value',
	               queryMode:'local',
	               store : Ext.create('Ext.data.Store',{
	                   fields : ['value'],
	                   data   : [
	                       {'value':'1'},
	                       {'value':'15'},
	                       {'value':'20'},
	                       {'value':'25'},
	                       {'value':'40'},
	                       {'value':'100'}
	                   ]
	               }),
	               listeners:{
						select : {
				            fn: function(_field,_value){
				            	var pageSize = store.pageSize;
				            	var newPageSize = parseInt(_field.value);
				            	if(pageSize!=newPageSize){
				            		store.pageSize = newPageSize;
				            		this.up('pagingtoolbar').moveFirst();
				            	}
				            }
				        }
	               }
	           }),{//条
					text: i18n('i18n.coupon.items'),
					xtype: 'tbtext'
	           }]
		}];
		//添加双击事件
		me.on('itemdblclick',function(th,record,item,index,e,eOpts){
			showDetail();
		});
	    this.callParent();
	   }
	});
	
	/**
	 * 优惠券查询界面整体布局panel
	 */
	Ext.define('CouponQueryPanel', {
		extend : 'Ext.container.Container',
		layout : 'border',
		items :[
			{//优惠券查询条件输入面板
				region:'north',
				layout:'fit',
				items:[Ext.create('CouponQueryConditionPanel',{id:'couponQueryConditionPanelId'})]
			},{
				xtype:'container',
				region:'center',
				layout:'border',
				items:[
					{//按钮面板
						
						xtype:'container',
						region:'north',
					    layout:'fit',
					    items:[Ext.create('CouponQueryButtonPanel',{id:'couponQueryButtonPanelId'})]
					},{//优惠券查询结果表格
						region:'center',
			    	    layout:'fit',
			    	    items:[ Ext.create('CouponQueryResultGrid',{id:'couponQueryResultGridId'})]
			        }	
			   ]
			}
		]
	});
	
	/*
	 * 单击营销计划名称（放大镜），弹出的营销计划查询窗口
	 */
	Ext.define('SearchMarketPlanByMessageWindow',{
		extend:'PopWindow',
		title:i18n('i18n.coupon.marketPlanSearch'),
		alias : 'widget.basicwindow',
		width:720,
		height:500,
		layout:'fit',
		closeAction:'hide',
		items:Ext.create('SearchMarketPlanByNamePanel')
	});

	/**
	 * 显示优惠券明细
	 */
	Ext.define('DetailCouponPopWindow',{
		extend:'PopWindow',
		alias : 'widget.basicwindow',
		width:825,
		height:350,
		modal:true,
		layout:'fit',
		closeAction:'hide',
        items:[{
        	layout:'border',
        	title:'优惠劵使用条件',
        	border:false,
        	items:[{
	        	xtype:'container',
            	flex:0.54,
            	region:'west',
            	border:false,
            	layout:'fit',
            	items:[{//自动返券左边的formPanel
            		id:'couponRuleFormPanel',//优惠券使用规则
            		defaultType : 'textfield',
            		xtype:'basicsearchformpanel',
            		bodyPadding:5,
     				defaults : {
     					labelWidth : 60,
     	                labelAlign: 'right',
     					width : 180
     				},
     		        layout : {
     					type : 'table',
     					columns : 5
     				},
     	            items:[{
     	                checked: true,
     	                fieldLabel: i18n('i18n.coupon.costMode'),
     	                xtype:'radio',
     	                width:75,
     	                id:'upcostMode',
     	                name: 'costMode',
     	                disabled:true,
     	                inputValue: '1',
     	                listeners:{
     	                	change:function(t,newValue,oldValue,e){
     	                		var form=Ext.getCmp("couponRuleFormPanel").getForm();
     	                		if(t.getValue()){//分级抵扣模式
//     	                			Ext.getCmp("upcostType").enable();//金额类型
//     	                			Ext.getCmp("upvalue").enable();//不低于/每满
     	                			Ext.getCmp("downcostType").setValue("").clearInvalid();//金额类型
     	                			Ext.getCmp("downvalue").setValue("").clearInvalid();//不低于/每满
     	                			form.findField("discount").setValue("").clearInvalid();//抵扣
//     	                			Ext.getCmp("downcostType").disable();//金额类型
//     	                			Ext.getCmp("downvalue").disable();//不低于/每满
//     	                			form.findField("discount").disable();//抵扣
     	                		}else{//严格抵扣
//     	                			Ext.getCmp("downcostType").enable();//金额类型
//     	                			Ext.getCmp("downvalue").enable();//不低于/每满
//     	                			form.findField("discount").enable();//抵扣
     	                			Ext.getCmp("upcostType").setValue("").clearInvalid();//金额类型
     	                			Ext.getCmp("upvalue").setValue("").clearInvalid();//不低于/每满
//     	                			Ext.getCmp("upcostType").disable();//金额类型
//     	                			Ext.getCmp("upvalue").disable();//不低于/每满
     	                		}
     	                	}
     	                }
     	            },{
     					hideLabel:true,
     					labelWidth:5,
     					xtype:'combobox',
     					name : 'upcostType',
     					id:'upcostType',
     					width:75,
     					disabled:true,
     					allowBlank:false,
     					editable:false,
     					queryMode: 'local',
     					displayField:'codeDesc',
     					valueField:'code',  
     					store:getDataDictionaryByName(DataDictionary,'COUPON_AMOUNT_TYPE')
     				},{
     					fieldLabel : i18n('i18n.coupon.upvalue'),
     					name : 'value',
     					id:'upvalue',
     					labelWidth:40,
     					width:115,
     					allowBlank:false,
     					disabled:true,
     					regex: /^[0-9]*[1-9][0-9]*$/,
     					regexText: i18n('i18n.coupon.onlyNumber'),
     					maxLength : 20,
     					maxLengthText : i18n('i18n.coupon.maxLength')+20
     				},{
     					xtype:'label',
     					colspan:2,
     					disabled:true,
     					text:' 元'
     				},{
     	                fieldLabel: '&nbsp;',
     	                labelSeparator:'',
     	                disabled:true,
     	                xtype:'radio',
     	                width:75,
     	                id:'downcostMode',
     	                name: 'costMode',
     	                inputValue: '2'
     	            },{
     					hideLabel:true,
     					labelWidth:5,
     					xtype:'combobox',
     					name : 'downcostType',
     					disabled:true,
     					id : 'downcostType',
     					width:75,
     					allowBlank:false,
     					disabled:true,
     					editable:false,
     					queryMode: 'local',
     					displayField:'codeDesc',
     					valueField:'code',  
     					store:getDataDictionaryByName(DataDictionary,'COUPON_AMOUNT_TYPE')
     				},{
     					fieldLabel : i18n('i18n.coupon.downvalue'),
     					name : 'value',
     					id : 'downvalue',
     					labelWidth:40,
     					width:115,
     					allowBlank:false,
     					regex: /^[0-9]*[1-9][0-9]*$/,
     					disabled:true,
     					regexText: i18n('i18n.coupon.onlyNumber'),
     					disabled:true,
     					maxLength : 20,
     					maxLengthText : i18n('i18n.coupon.maxLength')+20
     				},{
     					fieldLabel : i18n('i18n.coupon.discount'),
     					name : 'discount',
     					labelWidth:30,
     					width:90,
     					allowBlank:false,
     					disabled:true,
     					regex: /^[0-9]*[1-9][0-9]*$/,
     					regexText: i18n('i18n.coupon.onlyNumber'),
     					disabled:true,
     					maxLength : 20,
     					maxLengthText : i18n('i18n.coupon.maxLength')+20
     				},{
     					xtype:'label',
     					disabled:true,
     					text:' 元'
     				},{
     	                fieldLabel: i18n('i18n.coupon.costAddedMode'),
     	                xtype:'checkbox',
     	                width:75,
     	                name: 'costAddedMode',
     	                disabled:true,
     	                inputValue: '1',
     	                listeners:{
     	                	change:function(t,newValue,oldValue,e){
     	                		var form=Ext.getCmp("couponRuleFormPanel").getForm();
     	                		if(t.getValue()){
//	     	                		form.findField("costAddedType").enable();//增值费类型
//	     	                		form.findField("costAdded").enable();//增值费金额
     	                		}else{
     	                			form.findField("costAddedType").setValue("").clearInvalid();//增值费类型
	     	                		form.findField("costAdded").setValue("").clearInvalid();//增值费金额
//     	                			form.findField("costAddedType").disable();//增值费类型
//	     	                		form.findField("costAdded").disable();//增值费金额
     	                		}
     	                	}
     	                }
     	            },{
     					hideLabel:true,
     					labelWidth:5,
     					xtype:'combobox',
     					disabled:true,
     					name : 'costAddedType',
     					width:75,
     					disabled:true,
     					allowBlank:false,
     					editable:false,
     					queryMode: 'local',
     					displayField:'codeDesc',
     					valueField:'code',  
     					store:getDataDictionaryByName(DataDictionary,'COUPON_APPRECIATION_TYPE')
     				},{
     					fieldLabel : i18n('i18n.coupon.upvalue'),
     					name : 'costAdded',
     					labelWidth:40,
     					width:115,
     					disabled:true,
     					allowBlank:false,
     					regex: /^[0-9]*[1-9][0-9]*$/,
     					regexText: i18n('i18n.coupon.onlyNumber'),
     					disabled:true,
     					maxLength : 20,
     					maxLengthText : i18n('i18n.coupon.maxLength')+20
     				},{
     					xtype:'label',
     					colspan:2,
     					disabled:true,
     					text:' 元'
     				},{
		                xtype : 'datetimefield',
	                    name: 'begintime',
	                    value:new Date((new Date).getFullYear(),(new Date).getMonth(),(new Date).getDate(),0,0,0),
	                    defaultTime:[0,0,0],
	                    allowBlank:false,
	                    width:190,
	                    disabled:true,
	                    colspan:2,
	                    minValue:new Date(),
	                    fieldLabel : i18n('i18n.coupon.couponRuleTime'),
		                editable:false,
		                format: 'Y-m-d H:i'
		            },{
		                xtype : 'datetimefield',
	                    name: 'endtime',
//	                    value:new Date((new Date).getFullYear(),(new Date).getMonth(),(new Date).getDate(),23,59,59),
//	                    defaultTime:[23,59,59],
	                    width:215,
	                    allowBlank:false,
	                    disabled:true,
	                    colspan:3,
	                    minValue:new Date(),
	                    fieldLabel : i18n('i18n.coupon.go'),
		                editable:false,
		                format: 'Y-m-d H:i'
		            },{
     					fieldLabel : i18n('i18n.coupon.makeProductType'),
     					xtype:'combobox',
     					name : 'useProductType',
     					width:405,
     					colspan:5,
     					disabled:true,
     					editable:false,
     					queryMode: 'local',
     					multiSelect:true,
     					displayField:'codeDesc',
     					valueField:'code',  
     					store:getDataDictionaryByName(DataDictionary,'FOSS_PRODUCT_TYPE')
     				},{
     					fieldLabel : i18n('i18n.coupon.makeOrderSourceType'),
     					xtype:'combobox',
     					name : 'useOrderSourceType',
     					colspan:5,
     					disabled:true,
     					width:405,
     					editable:false,
     					queryMode: 'local',
     					multiSelect:true,
     					displayField:'codeDesc',
     					valueField:'code',  
     					store:getOrderResourcesStore()
     				},{
     					fieldLabel : i18n('i18n.coupon.makeCustomerLevelType'),
     					xtype:'combobox',
     					name : 'useCustomerLevelType',
     					colspan:5,
     					disabled:true,
     					width:405,
     					editable:false,
     					queryMode: 'local',
     					multiSelect:true,
     					displayField:'codeDesc',
     					valueField:'code',  
     					store:getDataDictionaryByName(DataDictionary,'MEMBER_GRADE')
     				},{
     					fieldLabel : i18n('i18n.coupon.makeCustomerTradeType'),
     					xtype:'combobox',
     					name : 'useCustomerTradeType',
     					colspan:5,
     					width:405,
     					disabled:true,
     					editable:false,
     					queryMode: 'local',
     					multiSelect:true,
     					displayField:'codeDesc',
     					valueField:'code',  
     					store:getDataDictionaryByName(DataDictionary,'TRADE')
     				}]
            	}]
	        },{
            	region:'center',
            	flex:0.46,
            	margins: '5 5',
            	layout: 'border',
            	xtype:'container',
            	items:[{
            		region:'north',
            		height:23,
            		xtype:'container',
            		layout : {
     					type : 'table',
     					columns : 3
     				},
     				defaultType : 'textfield',
     				defaults : {
     					labelWidth : 65,
     	                labelAlign: 'right',
     					width : 180
     				},
            		items:[{
						fieldLabel : '线路区域要求',
						xtype:'combobox',
						name : 'ruleType',
						id:'downruleType',
						width:220,
						labelWidth:80,
						editable:false,
						queryMode: 'local',
					    displayField: 'codeDesc',
					    valueField: 'code',
						store:Ext.create('RuleTypeStore'),
     					disabled:true
					}]
            	},{
            		region:'center',
            		layout: 'card',
            		id:'downCradPanel',
 					disabled:true,
            		xtype:'container',
            		margins: '5 5 0 5',
            		items: [{//走货线路
	            	    xtype:'popupgridpanel',
	            	    id:'useWalkGoodsLineGrid',
	            	    selModel:Ext.create('Ext.selection.CheckboxModel'),
	            	    store:Ext.create('UseCrossGoodsLineStore'),
    	                columns: [
	            	       { xtype:'rownumberer',width:40,header:'序号'},    
        	               { header: '<center>出发外场</center>', dataIndex: 'beginDeptOrCityName',sortable:false, flex: 0.5 },
        	               { header: '<center>到达外场</center>', dataIndex: 'endDeptOrCityName',sortable:false, flex: 0.5 }
        	            ]
	            	},{//发到货线路Grid
	            	    xtype:'popupgridpanel',
	            	    id:'useCrossGoodsLineGrid',
	            	    selModel:Ext.create('Ext.selection.CheckboxModel'),
	            	    store:Ext.create('UseWalkGoodsLineStore'),
    	                columns: [
	            	       { xtype:'rownumberer',width:40,header:'序号'},       
        	               { header: '<center>区域</center>', dataIndex: 'beginDeptOrCityName',sortable:false, flex: 1 }
        	            ]
	            	}]
            	}]
            }]
	    }]
	});
	
	var detailCouponWin=Ext.getCmp("detailCouponPopWindowId");//获取win
	if(!detailCouponWin){
		detailCouponWin=Ext.create('DetailCouponPopWindow',{
			id:'detailCouponPopWindowId'
		});
	}
	
	/*
	 * 新建一个viewport，用于显示优惠券查询界面
	 * 肖红叶
	 */
	var viewport=Ext.create('Ext.Viewport',{
		layout : 'fit',
		items:[
	       Ext.create('CouponQueryPanel')
	    ]
	});
	
	viewport.setAutoScroll(false);	
	viewport.doLayout();
	
});

function showDetail(){
	//获取选中的表格
	  var records=Ext.getCmp('couponQueryResultGridId').getSelectionModel().getSelection();
	  if (records.length == 0) {
		MessageUtil.showErrorMes(i18n('i18n.coupon.selectRecord'));
		return false;
	  }
	  if (records.length != 1) {
		MessageUtil.showMessage('一次只能选择一条记录！');
		return false;
	  }
	  if(Ext.isEmpty(records[0].get("marketPlanId"))){
			MessageUtil.showMessage("营销计划为空！");
			return false;
		}
	  var successFn = function(json){
	      if(!Ext.isEmpty(json.marketPlaneVO.couponRule)){//使用条件
				couponRuleId=json.marketPlaneVO.couponRule.id;
				var couponRuleForm=Ext.getCmp("couponRuleFormPanel").getForm();//使用规则
				couponRuleForm.reset();
				var costMode=null;// 分级抵扣模式
				var costType=null// 金额类型	默认为“1” 运费，“2”开单金额
				var value=null;//// 使用金额
				var discount=null;// 抵扣金额  选择分级抵扣模式 时 使用
				if(json.marketPlaneVO.couponRule.costMode==="1"){//默认选择分级抵扣模式
					Ext.getCmp("upcostMode").setValue(true);
					Ext.getCmp("upcostType").setValue(json.marketPlaneVO.couponRule.costType);
					Ext.getCmp("upvalue").setValue(json.marketPlaneVO.couponRule.value);
				}else{
					Ext.getCmp("downcostMode").setValue(true);
					Ext.getCmp("downvalue").setValue(json.marketPlaneVO.couponRule.value);
					Ext.getCmp("downcostType").setValue(json.marketPlaneVO.couponRule.costType);
					couponRuleForm.findField('discount').setValue(json.marketPlaneVO.couponRule.discount);
				}
				if(json.marketPlaneVO.couponRule.costAddedMode==="1"){// 增值费要求	默认为“0”没选中，“1”选中
					couponRuleForm.findField('costAddedMode').setValue(true);
					couponRuleForm.findField('costAddedType').setValue(json.marketPlaneVO.couponRule.costAddedType);
					couponRuleForm.findField('costAdded').setValue(json.marketPlaneVO.couponRule.costAdded);
				}
				couponRuleForm.findField('begintime').setValue(CouponUtil.changeLongToDate(json.marketPlaneVO.couponRule.begintime));
				couponRuleForm.findField('endtime').setValue(CouponUtil.changeLongToDate(json.marketPlaneVO.couponRule.endtime));
				//产品类型的store增加经济快递
				var useProductTypeStoreByAdd = couponRuleForm.findField('useProductType').store;
				if(!Ext.isEmpty(json.couponTypeVo.useProductType)){
					if(useProductTypeStoreByAdd.find('code','PACKAGE') === -1){
						useProductTypeStoreByAdd.add({
							code:'PACKAGE',
							codeDesc:'经济快递'
						});
					}
					couponRuleForm.findField('useProductType').setValue(json.couponTypeVo.useProductType);	
				}
				if(!Ext.isEmpty(json.couponTypeVo.useOrderSourceType)){
					couponRuleForm.findField('useOrderSourceType').setValue(json.couponTypeVo.useOrderSourceType);	
				}
				if(!Ext.isEmpty(json.couponTypeVo.useCustomerLevelType)){
					couponRuleForm.findField('useCustomerLevelType').setValue(json.couponTypeVo.useCustomerLevelType);	
				}
				if(!Ext.isEmpty(json.couponTypeVo.useCustomerTradeType)){
					couponRuleForm.findField('useCustomerTradeType').setValue(json.couponTypeVo.useCustomerTradeType);	
				}
				//每次清理之前的数据
				Ext.getCmp("useWalkGoodsLineGrid").store.removeAll();
				Ext.getCmp("useCrossGoodsLineGrid").store.removeAll();
				Ext.getCmp("downruleType").setValue("");
				var goodsLines=json.marketPlaneVO.couponRule.goodsLines;
				var downregdemand=null;
				for(var i=0;i<goodsLines.length;i++){
					if(i===0){//第一次的时候加载费用类型，只加载一次
						downregdemand=goodsLines[i].regdemand;
						Ext.getCmp("downruleType").setValue(downregdemand);
					}
					var goodsLineModel=new GoodsLineModel();
					goodsLineModel.set("beginDeptOrCityId",goodsLines[i].beginDeptOrCityId);
					goodsLineModel.set("beginDeptOrCityName",goodsLines[i].beginDeptOrCityName);
					goodsLineModel.set("endDeptOrCityId",goodsLines[i].endDeptOrCityId);
					goodsLineModel.set("endDeptOrCityName",goodsLines[i].endDeptOrCityName);
					goodsLineModel.commit();
					if(downregdemand===GOOD_LINE_AREA){
						Ext.getCmp("useWalkGoodsLineGrid").store.add(goodsLineModel);
					}else{
						Ext.getCmp("useCrossGoodsLineGrid").store.add(goodsLineModel);
					}
				}
				Ext.getCmp("detailCouponPopWindowId").show();
				if(!Ext.isEmpty(downregdemand)){
					if(downregdemand===GOOD_LINE_LEAVE || downregdemand===GOOD_LINE_ARRIVED){//如果是3、4激活发到货Grid  2是激活走货线路
						Ext.getCmp("downCradPanel").getLayout().setActiveItem(1);
					}else{
						Ext.getCmp("downCradPanel").getLayout().setActiveItem(0);
					}
				}
			}else{
				MessageUtil.showMessage("使用规则为空~！");
			}
		};
		var failureFn = function(json){
			MessageUtil.showErrorMes(json.message);
		};
		var param={
			'marketPlanId':records[0].get("marketPlanId")
		};
		CouponListStore.prototype.searchCouponRuleByMarketPlanId(param, successFn, failureFn);
}