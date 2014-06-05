/*
 * 单击短信发券按钮，弹出的短信发券页面
 * 肖红叶
 * 2012-11
 */

//定义一个 全局变量，用以记录手机列表中的记录数量
var telephoneCount = 0;
//定义一个全局变量，用以判断处理手机号列表的类别，分别是"批量导入"="BATCHIMPORT"、"清除无效"="CLEARINVALID"、"删除重复"="DELETEREPEAT"
var handleTelephone = null;
//手机号列表
var telephoneLists = new Array();
/**
 *优惠券信息显示form
 */
Ext.define('CouponInfoPanel',{
	extend:'BasicFormPanel', 
	title:i18n('i18n.coupon.couponInfo'),
	items:null,
	layout:{
		type:'table',
		columns:3
	},
	defaults:{
		labelWidth:80,
		labelAlign:'right',
		width:240,
		margin:'4 5 4 5'
	},
	defaultType:'textfield',
	initComponent:function(){			
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [
			{//营销计划名称
				fieldLabel:i18n('i18n.coupon.planName'),
				name:'planName',
				id:'planName',
				cls:'readonly ',
				readOnly:true
			},{//发券总数
				fieldLabel:i18n('i18n.coupon.couponSendTotal'),
				name:'couponQuantity',
				id:'couponQuantity',
				cls:'readonly ',
				readOnly:true
			},{//营销计划编码
				xtype:'hiddenfield',
				fieldLabel:'',
				labelWidth:1,
				width:20,
				name:'planNumber',
				id:'planNumber'
			},{//已发送数量
				fieldLabel:i18n('i18n.coupon.couponSendCount'),
				name:'sendedNum',
				id:'sendedNum',
				cls:'readonly ',
				readOnly:true
			},{//剩余数量
				fieldLabel:i18n('i18n.coupon.remainCount'),
				colspan:2,
				name:'balance',
				id:'balance',
				cls:'readonly ',
				readOnly:true
			},{//短信内容
				xtype:'textareafield',
				colspan:2,
				width:490,
				fieldLabel:i18n('i18n.coupon.msgContent'),
				name:'sms',
				id:'sms',
				cls:'readonly ',
				readOnly:true
			}
	    ];
	}
});

/**
 *手机号码输入form
 */
Ext.define('AddTelephonePanel',{
	extend:'SimpSearchPanel', 
	border:false,
	layout : {
		type : 'table',
		columns : 4
	},
	margin:'2 0 0 0',
	items:null,
	initComponent:function(){			
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [
	        {//手机号码
		    	xtype:'textfield',
				fieldLabel:i18n('i18n.coupon.telephone'),
				name:'imputTelephone',
				id:'imputTelephone',
				maxLength:11,
				minLength:8,
				regex:/(^1\d{10}$)|(^\d{8}$)/,
			    regexText:i18n('i18n.coupon.inputValidTelephone')
			},{//添加
				xtype:'button',
				text:i18n('i18n.coupon.add'),
				margin:'0 0 0 6',
				handler:function(){
					var telephone = Ext.getCmp('imputTelephone');
					var telephoneListGridStore = Ext.getCmp('telephoneListGridId').getStore();
					//校验输入的手机号码的有效性
					if(Ext.isEmpty(telephone.getValue())||!telephone.isValid()){
						MessageUtil.showErrorMes(i18n('i18n.coupon.inputValidTelephone'));;
						return false;
					}
					//在添加新的手机号之前先判断手机号列表中的手机号数量是否已经超过数量限制
					if(canInputTelephoneNumber()){
						return false;
					};
					//添加输入的手机号码到表格列表
               	 	var telephoneListGridModel = new BatchDealTelephoneModel();	 	
               	 	telephoneListGridModel.set("cellphone",telephone.getValue());
               	 	telephoneListGridModel.set("sendStatus",'0');
               	 	telephoneListGridModel.set("validity",'1');
               	 	telephoneListGridStore.add(telephoneListGridModel);
               	 	telephoneListGridModel.commit();
               	 	telephone.setValue('');
               	 	
               	 	//获取提示信息中的已添加手机号码数量
               	 	getTelephoneCount();
				}
			},{//下载导入模板
				xtype:'button',
				margin:'0 0 0 5',
				text:i18n('i18n.coupon.downloadTemplate'),
				handler:function(){
					MessageUtil.showQuestionMes(i18n('i18n.coupon.sureToDownloadTemplate'), function(e) {
	            		if (e == 'yes') {
	            			window.location.href = "../coupon/exportTemplateOfSendCoupon.action";
	            		}
					});		
				}
			},{//批量导入
				xtype:'button',
				margin:'0 0 0 5',
				text:i18n('i18n.coupon.uploadBatch'),
				handler:function(){
					//在批量导入之前先判断手机号列表中的手机号数量是否已经超过数量限制
					if(canInputTelephoneNumber()){
						return false;
					};
					var uploadTelephoneWin = Ext.getCmp("uploadTelephoneWinId");//获取批量上传手机号窗口
					if(!uploadTelephoneWin){
						uploadTelephoneWin = Ext.create('UploadTelephoneWin',{id:'uploadTelephoneWinId'});
					}					
					uploadTelephoneWin.show();
				}
			}
	    ];
	}
});

/**
 * 批量导入手机号弹出框
 */
Ext.define('UploadTelephoneWin',{
	extend:'PopWindow',
	title:i18n('i18n.coupon.uploadBatchTel'),
	layout:{
		type:'vbox',
		align:'stretch'
	},
	width:530,
	height:120,
	//potentialCust:null,//potentialCust data层
	initComponent:function(){
		var me = this;
		me.items = [
		    {
			xtype:'basicformpanel',
			id:'fileUploadFile',
			flex:1,
			layout:{
				type : 'hbox'
			},
			defaults : {
				margins : '0 5 0 0'
			},
			items:[{//导入文件
				xtype:'filefield',
				name:'upload',
				id:'upload',
				fieldLabel:i18n('i18n.coupon.selectAttach'),
				labelWidth:80,
				buttonText:i18n('i18n.coupon.browse'),
				flex:3
			}]
		}];
		me.fbar = me.getFbar();
		this.callParent();
	},
	getFbar:function(){
		var me = this;
		return [{//上传
			text:i18n('i18n.coupon.upload'),
			xtype:'button',
			handler:function(){	
				//判断是否已经选择文件
				if(Ext.isEmpty(Ext.getCmp('upload').getValue())){
					MessageUtil.showErrorMes(i18n('i18n.coupon.selectFileToImport'));
					return false;
				}
				
				var form = Ext.getCmp('fileUploadFile');
				form.submit({
		                    url: '../coupon/uploadFile.action',
		                    waitTitle:i18n('i18n.coupon.uploadBatchTel'),
		                    waitMsg: i18n('i18n.coupon.uploadingTelephone'),		                    
		                    success: function(form, response) {
		                    	var result = response.result;
		                    	var couponCellphoneList = result.couponCellphoneList;
		                    	if(result.success){
		                    		if(couponCellphoneList!=null){
		                    			//将后台返回的手机号列表显示到列表中
		                    			var importTelephoneLists = new Array();
		                    			for(var i=0;i<couponCellphoneList.length;i++){
											importTelephoneLists.push(couponCellphoneList[i]);
										}
		                    			Ext.getCmp("telephoneListGridId").store.loadData(importTelephoneLists,true);
										me.close();
										MessageUtil.showInfoMes(i18n('i18n.coupon.successToBatchTel'));
										getTelephoneCount();
		                    		}
		                    		else{
		                    			MessageUtil.showErrorMes(i18n('i18n.coupon.batchImportFailure'));
		                    		}
		                    	}
		                    	else{
		                    		MessageUtil.showErrorMes(i18n('i18n.coupon.batchImportFailure'));
		                    	}	
		                    },
			                failure:function(form, response){
			                	var result = response.result;
			                 	if(!result.success){
			                       	MessageUtil.showErrorMes(result.message);
								}
			                }
		       });
			}
		},{
			text:i18n('i18n.coupon.cancel'),
			xtype:'button',
			handler:function(){
				me.close();
			}
		}];
	}
});

//操作按钮面板
Ext.define('SendCouponByMsgButtonPanel',{
	extend:'NormalButtonPanel', 
	items:null,
	autoScroll:'false',
	initComponent:function(){
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [
		   {
			xtype:'leftbuttonpanel',
			width:350,
			items:[
			  {//删除
			  xtype:'button',
			  text:i18n('i18n.coupon.delete'),
			  handler:function(){
				//获得选中的数据
				var selection = Ext.getCmp('telephoneListGridId').getSelectionModel().getSelection();
	            var telephoneListGridStore = Ext.getCmp('telephoneListGridId').getStore();
	            if (selection.length>=1) {
	            	telephoneListGridStore.remove(selection);
        			//获得手机号码列表中记录条数，显示到提示信息中
        			getTelephoneCount();
	            }
	            else{
	            	MessageUtil.showErrorMes(i18n('i18n.coupon.selectTelToDelete'));
	            	return false;
	            }	            
			  }
			  },{//清除无效号码
			  text:i18n('i18n.coupon.deleteInvalidTel'),
			  xtype:'button',
			  handler:function(){
				  if(Ext.getCmp('telephoneListGridId').store.getCount()=== 0){
					  MessageUtil.showErrorMes(i18n('i18n.coupon.nullTelephoneList')); 
					  return false;
				  }
				  handleTelephone = 'CLEARINVALID';//设置手机号处理类型为清除无效手机号码
				  telephoneLists = new Array();//新建一个数组，用以存放手机号表格中的数据
				  Ext.getCmp('telephoneListGridId').store.each(function(record){
					telephoneLists.push(record.data);
				  });
				  var successFn = function(json){
						var couponCellphoneList=json.couponCellphoneList;//成功执行后返回到前台的手机号列表
						var invalidCount = Ext.getCmp("telephoneListGridId").store.getCount()-
							couponCellphoneList.length;
						if(invalidCount>0){
							MessageUtil.showQuestionMes(i18n('i18n.coupon.sureToClear')
									+invalidCount+i18n('i18n.coupon.sureToClearInvalid'), function(e) {
			            		if (e == 'yes') {
			            			Ext.getCmp("telephoneListGridId").store.removeAll();//清除手机号列表中的所有数据
									//将后台返回的手机号列表显示到列表中
//									for(var i=0;i<couponCellphoneList.length;i++){
//										var batchDealTelephoneModel=new BatchDealTelephoneModel();
//										batchDealTelephoneModel.set("cellphone",couponCellphoneList[i].cellphone);
//										batchDealTelephoneModel.set("validity",couponCellphoneList[i].validity);
//										batchDealTelephoneModel.set("sendStatus",couponCellphoneList[i].sendStatus);
//										Ext.getCmp("telephoneListGridId").store.add(batchDealTelephoneModel);
//										batchDealTelephoneModel.commit();
//									}
			            			Ext.getCmp("telephoneListGridId").store.add(json.couponCellphoneList);
									//获得手机号码列表中记录条数，显示到提示信息中
									getTelephoneCount();
									MessageUtil.showInfoMes(i18n('i18n.coupon.clear')+invalidCount+
											i18n('i18n.coupon.successToClearInvalid'));
			            		}
							});
						}
						else {
							MessageUtil.showErrorMes(i18n('i18n.coupon.noInvalidTelephone'));
						}
					}
					var param = {
						'handleTelephone':handleTelephone,
						'couponCellphoneList':telephoneLists
					};
					var failureFn=function(json){
						MessageUtil.showErrorMes(json.message);
					}
					MarketPlanMangeStore.prototype.batchHandleTelephones(param,successFn,failureFn);		  
			  }
			  },{//清除重复号码
			  text:i18n('i18n.coupon.deleteRepeatTel'),
			  xtype:'button',
			  handler:function(){
				  if(Ext.getCmp('telephoneListGridId').store.getCount()=== 0){
					  MessageUtil.showErrorMes(i18n('i18n.coupon.nullTelephoneList')); 
					  return false;
				  }
				  handleTelephone = 'DELETEREPEAT';//设置手机号处理类型为清除重复号码
				  telephoneLists = new Array();//新建一个数组，用以存放手机号表格中的数据
				  Ext.getCmp('telephoneListGridId').store.each(function(record){
					telephoneLists.push(record.data);
				  });
				  
				 var successFn = function(json){
					var couponCellphoneList=json.couponCellphoneList;//成功执行后返回到前台的手机号列表
					var repeatCount = Ext.getCmp("telephoneListGridId").store.getCount()-
						couponCellphoneList.length;
					if(repeatCount>0){
						MessageUtil.showQuestionMes(i18n('i18n.coupon.sureToDelete')+repeatCount+
								i18n('i18n.coupon.sureToDeleteRepeat'), function(e) {
		            		if (e == 'yes') {
		            			Ext.getCmp("telephoneListGridId").store.removeAll();//清除手机号列表中的所有数据
								//将后台返回的手机号列表显示到列表中
//								for(var i=0;i<couponCellphoneList.length;i++){
//									var batchDealTelephoneModel=new BatchDealTelephoneModel();
//									batchDealTelephoneModel.set("cellphone",couponCellphoneList[i].cellphone);
//									batchDealTelephoneModel.set("validity",couponCellphoneList[i].validity);
//									batchDealTelephoneModel.set("sendStatus",couponCellphoneList[i].sendStatus);
//									Ext.getCmp("telephoneListGridId").store.add(batchDealTelephoneModel);
//									batchDealTelephoneModel.commit();
//								}
								Ext.getCmp("telephoneListGridId").store.add(json.couponCellphoneList);
								//获得手机号码列表中记录条数，显示到提示信息中
								getTelephoneCount();
								MessageUtil.showInfoMes(i18n('i18n.coupon.delete')+repeatCount+
										i18n('i18n.coupon.successToDeleteRepeat'));
		            		}
		            	});
					}
					else {
						MessageUtil.showErrorMes(i18n('i18n.coupon.noRepeatTelephone'));
					}
				}
				var param = {
					'handleTelephone':handleTelephone,
					'couponCellphoneList':telephoneLists
				};
				var failureFn=function(json){
					MessageUtil.showErrorMes(json.message);
				}
				MarketPlanMangeStore.prototype.batchHandleTelephones(param,successFn,failureFn);
			
			    //获得手机号码列表中记录条数，显示到提示信息中
			    getTelephoneCount();
			  }
			  }]
		   },{
			 xtype:'middlebuttonpanel' 
		   }];
	}
});

/*
 * 添加手机号码的列表表格
 * 肖红叶
 * 2012-11
 */
Ext.define('TelephoneListGrid',{
	extend:'PopupGridPanel',
	defaults:{
		align:'center'
	},
	selModel : Ext.create('Ext.selection.CheckboxModel',{mode:'MULTI'}),
	autoScroll:true,
	initComponent:function(){ 
		var me = this;
		var store = Ext.create('BatchHandleTelephonesStore',{id:'batchHandleTelephonesStoreId'});	
		me.store = store;
		me.columns = [
		    {//序号
				xtype:'rownumberer',
				header:i18n('i18n.coupon.rownumberer'),
				width:50
		    },{//手机号码
				header:i18n('i18n.coupon.telephone'),
				flex:0.4,
				dataIndex:'cellphone'
		    },{ //是否有效
				header :i18n('i18n.coupon.isValid'),
				dataIndex:'validity',
				flex:0.3,
				renderer:function(val){
					if(val=='0'){
						return '无效';
					}
					else if(val=='1'){
						return '有效';
					}
				}
		    },{//发送状态
				header :i18n('i18n.coupon.sendStatus'),
				dataIndex:'sendStatus',
				flex:0.3,
				renderer:function(val){
					if(val=='0'){
						return '未发送';
					}
					else if(val=='1'){
						return '已发送';
					}
		    }}
	    ];
    this.callParent();
   }
});

/**
 *手机号列表下的提示信息form
 */
var totalNumber='<span style="color: #FF0000">1000</span>';
var count='<span style="color: #FF0000">'+telephoneCount+'</span>';
Ext.define('PromptInfoPanel',{
	extend:'Ext.container.Container', 
	layout : {
		type : 'table',
		columns : 5
	},
	items:[
	    {
			xtype:'hidden',
			width:150
		},{
			xtype:'displayfield',	
			fieldLabel:i18n('i18n.coupon.countPerTime')+totalNumber+i18n('i18n.coupon.items'),
			labelSeparator:'',
			align:'left',
			labelWidth:150,
			name:'',
			id:''	
		},{
			xtype:'label',
			text:'/',
			width:10
		},{
			xtype:'displayfield',
			fieldLabel:'',
			value:i18n('i18n.coupon.haveAdded')+count+i18n('i18n.coupon.items'),
			labelSeparator:'',
			width:100,
			name:'telephoneCount',
			id:'telephoneCount',
			align:'left'
		},{
			xtype:'hidden',
			width:150
		}
	]
});

/**
 * 短信发券页面整体布局panel
 */
Ext.define('SendCouponByMsgPanel', {
	extend : 'BasicPanel',
	layout : 'border',
	items :[
	  {//优惠券信息
		region:'north',
		layout:'fit',
		items:[Ext.create('CouponInfoPanel',{id:'couponInfoPanelId'})]
	  },{//发送对象
		layout:'border',
		title:i18n('i18n.coupon.sendTo'),
		region:'center',
		items:[
		  {
			//手机号码输入form
			xtype:'container',//container可以去除panel的边框
			layout:'fit',
			region:'north',
			items:[Ext.create('AddTelephonePanel',{id:'addTelephonePanelId'})]	  
		  },{
			xtype : 'container',
			layout:'border',
			region:'center',
			items:[
		       {//按钮操作区
		    	 xtype : 'container',
				 layout:'fit',
				 region:'north',
				 items:[Ext.create('SendCouponByMsgButtonPanel',{id:'sendCouponByMsgButtonPanelId'})],
				 height:37 
		       },{//提示信息区
	    	    xtype : 'container',
				layout:'fit',
				region:'south',
				height:30,
				items:[Ext.create('PromptInfoPanel',{id:'promptInfoPanelId'})]
		       },{//手机号码列表区
	    	    xtype : 'container',
				layout:'fit',
				region:'center',
				items:[Ext.create('TelephoneListGrid',{id:'telephoneListGridId'})]
		       }
			]
		  }      
		]
	  }
	]
});

/**
 * 获得手机号码列表中记录条数，显示到提示信息中
 */
function getTelephoneCount(){
	var telephoneListGridStore = Ext.getCmp('telephoneListGridId').getStore();
	telephoneCount = telephoneListGridStore.data.length;
	Ext.getCmp('telephoneCount').setValue(i18n('i18n.coupon.haveAdded')
			+'<span style="color: #FF0000">'+telephoneCount+'</span>'+i18n('i18n.coupon.items'));
}

/**
 * 在添加和批量导入手机号之前验证表格中的手机号条数是否已经超过限制
 * @returns {Boolean}
 */
function canInputTelephoneNumber(){
	//balance，用以表示优惠券剩余未发放数量
	var balance = parseInt(Ext.getCmp('balance').getValue());
	var canNotInputTelephoneNumber = false;
	var telephoneListGridStore = Ext.getCmp('telephoneListGridId').getStore();
	if( telephoneListGridStore.data.length >= balance){
		MessageUtil.showMessage(i18n('i18n.coupon.numberCanNotExceedRemainNo'));
		canNotInputTelephoneNumber = true;
	}
	if(telephoneListGridStore.data.length >= 1000){
		MessageUtil.showErrorMes(i18n('i18n.coupon.numberCanNotExceed'));
		canNotInputTelephoneNumber = true;
	}
	return canNotInputTelephoneNumber;
}