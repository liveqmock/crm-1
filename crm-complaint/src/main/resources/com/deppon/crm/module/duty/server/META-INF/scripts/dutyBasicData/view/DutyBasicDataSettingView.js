/**
 *基础资料设置业务项、业务范围选择面板form
 */
Ext.define('DutyBasicDataSettingForm',{
	extend:'SearchFormPanel', 
	items:null,
	border:0,
	layout:{type:'table',columns:4},
	defaults:{
		labelWidth:60,allowBlank:false,margin:'0 5 5 0',
		blankText:i18n('i18n.DutyEditableResultView.notNull')//不能为空
	},
	isUpdate:false,/* 默认不是修改 */
	busItemStore:null,
	initComponent:function(){
		var me = this;
		if(me.isUpdate==false){
			//新增则加载 store
			me.busItemStore = Ext.create('Ext.data.Store',{model:'BusinessItemsModel'});
			me.loadBusItemList(ReportType.complaint);
		}
		me.items = me.getItems();
		this.callParent(arguments);
		if(me.isUpdate){
			Ext.getCmp('reportType').setReadOnly(true);
		}
		
	},
	loadBusItemList:function(reportType){
		var me=this;
		if(Ext.isEmpty(me.busItemStore)){return;}
		me.busItemStore.removeAll();
		var params = {
			'basicSearchCondition.reportType':reportType
		};
		//执行成功
		var successFn = function(response){
			me.busItemStore.loadData(response.basicInfoList);
		}
		
		//执行失败
		var failFn = function(response){
			MessageUtil.showErrorMes(response.message);
		}
		DutyUtil.requestAjax('../duty/searchBasicInfoList.action',params,successFn,failFn);
	},
	getItems:function(){
		var me = this;
		//业务项
		var busItem = null;
		//更新
		if(me.isUpdate && me.isUpdate==true){
			busItem = {
				xtype:'textfield',name:'busItemName',
				readOnly:true,
			    fieldLabel:'<span style="color:#ff0000">*</span>'+i18n('i18n.duty.result.busItem')//业务项
			};
		}else{
			busItem = {
			    	xtype:'combobox',width:200,
			    	fieldLabel:'<span style="color:#ff0000">*</span>'+i18n('i18n.duty.result.busItem'),//业务项
			    	queryMode:'local',store:me.busItemStore,
			    	displayField:'busItemName',valueField:'busItemId',
			    	forceSelection:true,name:'busItemId',id:'inputBusItemId'
			};
		};
		return [
			{ 
				xtype:'radiogroup',fieldLabel:i18n('i18n.duty.reportType')/*上报类型*/,width:180,
				id:'reportType',name:'reportType',items:[
					{
						name:'type',boxLabel:i18n('i18n.Duty.dutyType.complaint'),checked:true,id:'complaintReportTypeId',//投诉
						listeners:{
				    		change:function(field){
				    			if(me.isUpdate==false && field.getValue()){
				    				Ext.getCmp('inputBusItemId').reset();
				    				me.loadBusItemList(ReportType.complaint);
				    			}
				    		}
						}
					},
				    {
				    	name:'type',boxLabel:i18n('i18n.Duty.dutyType.unusual'),width:60,id:'unusualReportTypeId',//异常
				    	listeners:{
				    		change:function(field){
				    			if(me.isUpdate==false && field.getValue()){
				    				Ext.getCmp('inputBusItemId').reset();
				    				me.loadBusItemList(ReportType.unusual);
				    			}
				    		}
						}
				    }
				]
			},
		    busItem,
		    {
		    	xtype:'textfield',name:'busScopeName',
		    	fieldLabel:'<span style="color:#ff0000">*</span>'+i18n('i18n.duty.busScopeName')/*业务范围*/
			}
	    ];
	}
});	

/**
 * 基础资料设置增加、删除按钮Panel
 */
Ext.define('DutyBasicDataSetAddAndDelPanel',{
	extend:'TopPanel',
	margin:'5 5 0 5',
	items : null,
	initComponent : function() {
		this.items = this.getItems();
		this.callParent(arguments);
	},
	getItems : function() {
		var me = this;
		return [{
			xtype:'btnpanel',  
			defaultType:'button',
			items:[{//增加
				text:i18n('i18n.duty.add'),
				handler:function(){
					//增加按钮
					var gridStore = Ext.getCmp('dutyBasicDataSettingGridId').getStore();
					gridStore.add(new BasicInfoSettingModel({
						'reworpusType':'nothing'
					}));
				}
			   },{//删除
				text:i18n('i18n.duty.delete'),
				handler:function(){
					//删除按钮
					var grid = Ext.getCmp('dutyBasicDataSettingGridId');
					var selection = grid.getSelectionModel().getSelection();
					if(Ext.isEmpty(selection) || selection.length==0){
						MessageUtil.showMessage(i18n('i18n.DutyBasicDataSettingView.selectDate'));return;//请选择数据
					}
					MessageUtil.showQuestionMes(i18n('i18n.DutyFeedbackView.youSureDeleteSelected')+selection.length+i18n('i18n.DutyBasicDataSettingView.tiaoRecord'),function(e){//您确定删除所选的//条记录
						if(e != 'yes'){return;}
						
						var parmArray = new Array();
						var selectionArray = new Array();
						Ext.each(selection,function(record){
							if(Ext.isEmpty(record.get('levelId')) || '0' == record.get('levelId')){
								//删除前端新增的
								grid.getStore().remove(record);
							}else{
								parmArray.push(record.data);
								selectionArray.push(record);
							}
						});
						if(parmArray.length == 0){return;}
				
						var params = {'busTypeList':parmArray};
						var successFn = function(){
							//加载 分页集合
							Ext.each(selectionArray,function(record){
								//删除前端新增的
								grid.getStore().remove(record);
							});
							MessageUtil.showMessage(i18n('i18n.DutyBasicDataSettingView.deleteDataSuccess'));//删除数据成功
						};
						var failFn = function(){
							MessageUtil.showMessage(i18n('i18n.DutyBasicDataSettingView.deleteDataFailure'));//删除数据失败
						};
						ActionFunction.basicBusType_deleteBusTypeByIds(params,successFn,failFn);
					});
		      	}
			}]
		}]; 
	}
});

/**
 * 基础资料设置列表
 */
Ext.define('DutyBasicDataSettingGrid',{
	extend:'SearchGridPanel',
	defaults:{align:'center'},autoScroll:true,
	initComponent:function(){ 
		var me = this;
		me.columns = [
		    {xtype:'rownumberer',header:i18n('i18n.Duty.resultDutyPanel.rownumberer'),width:40},/*序号*/
		    {header:i18n('i18n.duty.busTypeName')/*业务类型*/,width:100,dataIndex:'busType',editor:{
		    	xtype:'textfield',regex:/^\S+$|^\S.+\S$/ /*不能输入前后空格*/
		    }},/*业务类型*/
		    {header:i18n('i18n.duty.dealLanguage')/*处理语言*/,flex:1,dataIndex:'dealLanguage',editor:{xtype:'textfield',maxLength:300,regex : new RegExp('^[^"]{0,}$'),regexText:i18n('i18n.Duty.pleaseNotInputDoubleYin')}
		    ,renderer:function(value) {
				if(!Ext.isEmpty(value)){
					var val = '<span data-qtip="'+value+'">'+value+'</span>';
					return val;
				}
			}},/*处理语言*/
		    {
		    	header:i18n('i18n.duty.reworpusType')/*奖罚类型*/,width:100,dataIndex:'reworpusType',editor:{
		    		xtype:'combobox',forceSelection:true
	                ,store:{
	                	xtype:'store',fields:['code','codeDesc'],
	                	data:ReworpusType.list
	                }
	                ,queryMode:'local',displayField:'codeDesc',valueField:'code'
		    	},
		    	renderer:function(value,metaData,record){
					return ReworpusType.getReworpusTypeName(value);
				}
		    },/*奖罚类型*/
		    {header:i18n('i18n.duty.feedbackLimit')/*反馈期限(天)*/,width:120,dataIndex:'feedbackLimit',editor:{
		    	xtype:'numberfield',decimalPrecision:1,minValue:1,
		        maxValue:100,//必须为数字,切1~100之内
		    }}/*反馈期限*/
	    ];
	    
		this.callParent(arguments);
   }
});

/**
 * 单击”基础资料设置“按钮出现的设置页面整体布局panel
 */
Ext.define('DutyBasicDataSettingPanel', {
	extend : 'BasicPanel',
	layout : 'border',
	isUpdate:false,/*默认为false 表示不是更新*/
	initComponent:function(){
		var me = this;
		me.items = me.getItems();
		this.callParent(arguments);
	},
	getItems:function(){
		var me = this;
		return [
		  {//业务项、业务范围选择
			xtype:'container',
			region:'north',
			layout:'fit',
			items:[Ext.create('DutyBasicDataSettingForm',{
				id:'dutyBasicDataSettingFormId',
				isUpdate:me.isUpdate
			})]
		  },{
			xtype:'container',
			layout:'border',
			region:'center',
			items:[{//按钮
				xtype:'container',//container可以去除panel的边框
				layout:'fit',
				region:'north',
				height:45,
				items:[Ext.create('DutyBasicDataSetAddAndDelPanel',{id:'dutyBasicDataSetAddAndDelPanelId'})]	  
			  },{//基础资料设置列表
				xtype : 'container',
				layout:'fit',region:'center',
				items:[Ext.create('DutyBasicDataSettingGrid',{
					id:'dutyBasicDataSettingGridId',
					store:Ext.create('BasicInfoSettingStore'),
					selModel:Ext.create('Ext.selection.CheckboxModel'),
		    	   	plugins:Ext.create('Ext.grid.plugin.CellEditing', {clicksToEdit :2})
				})]
			  }]
		  }
		];
	}
});


/**
 * 单击基础资料设置按钮，弹出的基础资料设置窗口
 * xiaohongye
*/
Ext.define('DutyBasicDataSettingWindows',{
	extend:'PopWindow',
	title:i18n('i18n.DutyBasicDataSettingView.basicSet'),//基础资料设置
	width:700,height:500,
	modal:true,layout:'fit',
	isUpdate:false,/* 默认 不是修改 */
	listeners:{
		hide:function(){
			document.body.scrollLeft=0;
			document.body.scrollTop=0;
			document.getElementsByTagName("html")[0].style.overflowY="hidden";
			document.getElementsByTagName("html")[0].style.overflowX="hidden";
		}
	},
	initComponent:function(){
		var me = this;
		me.buttons = me.getButtons();
		this.callParent(arguments);
	},
	getButtons:function(){
		var me = this;
		return  [{
				xtype:'button',
				text:i18n('i18n.duty.submit')/*提交*/,
				handler:function(){			
					var form = Ext.getCmp('dutyBasicDataSettingFormId').getForm();
					if(!form.isValid()){return;}
					
					var basicBusScopeVO = {
						'busScopeName':form.findField('busScopeName').getValue()
					};
					if(Ext.getCmp('unusualReportTypeId').getValue()){
						basicBusScopeVO['reportType'] = ReportType.unusual;
					}else{
						basicBusScopeVO['reportType'] = ReportType.complaint;
					}
					
					//更新要处理的数据
					if(me.isUpdate && me.isUpdate==true){
						var selection  = Ext.getCmp('dutyBasicDataResultGridId').getSelectionModel().getSelection();
						basicInfo = Ext.clone(selection[0].data);
						basicBusScopeVO['busItemId'] = basicInfo['busItemId'];/*业务项*/
						basicBusScopeVO['busScopeId'] = basicInfo['busScopeId'];/*业务范围*/
						basicBusScopeVO['busItemName'] = form.findField('busItemName').getValue()/*业务项名称*/
					}else{/*新增数据*/
						basicBusScopeVO['busItemId'] = form.findField('busItemId').getValue()/*业务项*/
					}
					
					var gridStore = Ext.getCmp('dutyBasicDataSettingGridId').getStore();
					
					if(gridStore.getCount()>0){/*基础类型存在*/
						var busTypes = new Array();
						var check_result = {'flag':true};
						gridStore.each(function(record){
							if(Ext.isEmpty(record.get('busType'))){
								check_result={'flag':false,'msg':i18n('i18n.DutyBasicDataSettingView.typeOfBusinessNotNull')};return;//业务类型不能为空
							}
							busTypes.push(record.data);
						});
						
						if(check_result.flag == false){//验证表格数据是否为空
				    		MessageUtil.showMessage(check_result.msg);return;
				    	}
				    	basicBusScopeVO['busTypes'] = busTypes;
					}
					var successFn = function(){
			    		me.close();
						MessageUtil.showInfoMes(i18n('i18n.DutyBasicDataSettingView.saveSuccess'));//保存成功
			    	};
			    	var failFn = function(){};
			    	ActionFunction.basicBusScopeVO_saveBasicBusScopeVO({'basicBusScopeVO':basicBusScopeVO},successFn,failFn);
				}
		},{
			xtype:'button',text:i18n('i18n.duty.cancel')/*取消*/,
			handler:function(){me.close();}
		}];
	}
});


