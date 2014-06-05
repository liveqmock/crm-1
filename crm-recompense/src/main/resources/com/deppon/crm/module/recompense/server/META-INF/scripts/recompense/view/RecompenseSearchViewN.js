var recompenseData = (CONFIG.get("TEST")) ? new RecompenseDataTestN()
		: new RecompenseDataN();
var INITSEARCH = true;
var SearchError = false;
//var RecompenseInfoDataNew = false;
var SearchWorkFlow = false;
Ext.define('RecompenseSearchFrom', {
	extend : 'SearchFormPanel',
	height : 180,
//	flex:3,
	id : 'recompenseSearchFrom',
	initComponent : function() {
		var me = this;
		me.items = me.getItems();
		// me.fbar = me.getFbar();
		this.callParent();
		me.initPage();
	},
	initPage : function() {
		var	me=this;
		var condition = new Condition();
		me.getForm().loadRecord(condition);
		Ext.getCmp(CONFIGNAME.get('approveTime')).reset();
		Ext.getCmp(CONFIGNAME.get('approveEndTime')).reset();
		Ext.getCmp('conRecompenseMethod').getStore().add({'code':'ALL','codeDesc':i18n('i18n.recompense.all')});
		Ext.getCmp(CONFIGNAME.get('recompenseType')).getStore().add({'code':'ALL','codeDesc':i18n('i18n.recompense.all')});
		//Ext.getCmp(CONFIGNAME.get('recompenseState')).getStore().add({'code':'ALL','codeDesc':i18n('i18n.recompense.all')});
		//me.initComb('searchReportDept');
		//me.initComb(CONFIGNAME.get('belongArea'));
		Ext.getCmp('searchTransType').getStore().add({'code':'ALL','codeDesc':i18n('i18n.recompense.all')});
		Ext.getCmp('searchTransType').setValue('ALL');
		if (Ext.isEmpty(User)||Ext.isEmpty(User.roleids)||me.isArrayContaintChar('4',User.roleids)) {
				Ext.getCmp('conRecompenseMethod').setValue('ALL');
				Ext.getCmp(CONFIGNAME.get('recompenseType')).setValue('ALL');
				Ext.getCmp(CONFIGNAME.get('recompenseState')).setValue('ALL');
			}
		if (Ext.isEmpty(User)||Ext.isEmpty(User.roleids)||me.isArrayContaintChar('3',User.roleids)||me.isArrayContaintChar('5',User.roleids)) {
			Ext.getCmp('searchReportDept').disable(true);
			Ext.getCmp(CONFIGNAME.get('belongArea')).disable(true);
		}
	},
	isArrayContaintChar:function(char,array){
		for ( var i = 0; i < array.length; i++) {
			if (array[i]==char) {
				return true;
			}
		}
		return false;
	},
	initComb:function(combId){
		Ext.getCmp(combId).initData(User)
		/*grid.getStore().add({'id':User.deptId,'deptName':User.deptName});
		grid.setValue(grid.store.getAt(0).get('id'));*/
//		grid.store.on('load',function(th){
//			if (th.getCount()>0) {
//				grid.setValue(th.getAt(0).get("id"));
//			}
//		})
	},
	getItems : function() {
		return [ {
//			xtype : 'fieldset',
			// title : i18n('i18n.recompenseMonitoringN.searchCondition'),
			// margin:'3 0 10 3',
			layout : {
				type : 'table',
				columns : 3
			},
			defaults : {
				xtype : 'textfield',
				labelSeparator : '',
				labelWidth : 50,
				margin : '5 5 5 0',
				width : 255,
				colspan : 1,
				enableKeyEvents:true,
				listeners:{
					keypress:function(field,event){
						if(event.getKey() == Ext.EventObject.ENTER){
							Ext.getCmp('buttonSearchPanel').searchRecompense();
						}
					}
				}
			},
			items : [ 
				Ext.create('Ext.lookup.DeptLookup',
						   {
					   id :'searchReportDept',
					   maxLength:80,
					   colspan : 1,
					   labelSeparator : '',
						labelWidth : 50,
						margin : '5 5 5 0',
					   editable:false,
					   emptyText:'',
					   fieldLabel : i18n('i18n.recompense.reportDept'),
					   name : CONFIGNAME.get('reportDept'),//部门
					   width : 255
				}) 
			          /*{
				xtype : 'combo',
				id :'searchReportDept',
				name : CONFIGNAME.get('reportDept'),
				fieldLabel : i18n('i18n.recompense.reportDept'),
				displayField : 'deptName',
				valueField:'id',
				queryMode:'local',
				store : recompenseData.getReportDeptStore(),
		        listeners:{
		            	specialkey:function(th,e){
		            		var successFn = function(json){
		            			Ext.data.StoreManager.lookup('reportDeptStore').removeAll();
			     			    Ext.data.StoreManager.lookup('reportDeptStore').add(json.deptList);
			     			    Ext.getCmp('searchReportDept').expand();
			     		    };
			     		    var param  = {'deptName':e.target.value};
		            		DpUtil.keyPress(e,recompenseData.getDeptByName,param,3,successFn);
		            	}
		        }    
			}*/, {
				xtype : 'datefield',
				name : CONFIGNAME.get('conReportStartTime'),
				id : CONFIGNAME.get('conReportStartTime'),
				convert : DpUtil.changeLongToDate,
				value : new Date(),
				fieldLabel : i18n('i18n.recompense.reportTime')
			}, {
				xtype : 'datefield',
				id : CONFIGNAME.get('reportEndTime'),
				name : CONFIGNAME.get('reportEndTime'),
				value : new Date(),
				fieldLabel : i18n('i18n.recompense.to')
			}, {
				xtype : 'combo',
				id : 'conRecompenseMethod',
				name : CONFIGNAME.get('conRecompenseMethod'),
				fieldLabel : i18n('i18n.recompense.recompenseMethod'),
				displayField : 'codeDesc',
				valueField :'code',
				queryMode:'local',
				editable:false,
				store : DpUtil.getStore('conRecompenseMethod',null,['code','codeDesc'],DataDictionary.RECOMPENSE_WAY)
			}, {
				xtype : 'combo',
				id : CONFIGNAME.get('recompenseType'),
				name : CONFIGNAME.get('recompenseType'),
				fieldLabel : i18n('i18n.recompense.recompensetype'),
				displayField : 'codeDesc',
				valueField  : 'code',
				queryMode:'local',
				editable:false,
				store : DpUtil.getStore(CONFIGNAME.get('recompenseType'),null,['code','codeDesc'],DataDictionary.RECOMPENSE_TYPE)
			}, {
				xtype : 'combo',
				id : CONFIGNAME.get('recompenseState'),
				name : CONFIGNAME.get('recompenseState'),
				fieldLabel : i18n('i18n.recompense.handleStatus'),
				displayField : 'codeDesc',
				valueField  : 'code',
				queryMode:'local',
				editable:false,
				store : DpUtil.getStore(CONFIGNAME.get('recompenseState'),null,['code','codeDesc'],DataDictionary.RECOMPENSE_STATUS)
			},
			Ext.create('Ext.lookup.DeptLookup',
					   {
				   id : CONFIGNAME.get('belongArea'),
				   name : CONFIGNAME.get('belongArea'),
				   maxLength:80,
				   colspan : 1,
				   labelSeparator : '',
					labelWidth : 50,
					margin : '5 5 5 0',
				   editable:false,
				   emptyText:'',
				   fieldLabel : i18n('i18n.recompense.belongArea'),
				   width : 255
			})
			/* {
				xtype : 'combo',
				id : CONFIGNAME.get('belongArea'),
				name : CONFIGNAME.get('belongArea'),
				fieldLabel : i18n('i18n.recompense.belongArea'),
				displayField : 'deptName',
				valueField:'id',
				queryMode:'local',
				store : recompenseData.getRegionStore(),
				 listeners:{
		            	specialkey:function(th,e){
		            		var successFn = function(json){
		            			 Ext.data.StoreManager.lookup('regionStore').removeAll();
				     			 Ext.data.StoreManager.lookup('regionStore').add(json.deptList);
			     			    Ext.getCmp(CONFIGNAME.get('belongArea')).expand();
			     		    };
			     		    var param  = {'deptName':e.target.value};
		            		DpUtil.keyPress(e,recompenseData.getRegionByName,param,3,successFn);
		            	}
		        }    
			}*/, {
				xtype : 'datefield',
				id : CONFIGNAME.get('approveTime'),//审批时间
				name : CONFIGNAME.get('approveTime'),
				//value:null,
				fieldLabel :i18n('i18n.recompense.approveTime')
			}, {
				xtype : 'datefield',
				id : CONFIGNAME.get('approveEndTime'),
				name : CONFIGNAME.get('approveEndTime'),
				//value:null,
				fieldLabel : i18n('i18n.recompense.to')
			}, {
				id : CONFIGNAME.get('conCustNum'),
				name : CONFIGNAME.get('conCustNum'),
				fieldLabel : i18n('i18n.recompense.custNum')
			}, {
				//id : CONFIGNAME.get('custName'),
				name : CONFIGNAME.get('custName'),
				fieldLabel : i18n('i18n.recompense.custName')
			}, {
                fieldLabel : i18n('i18n.recompense.orderNumOrErroeNum'),
				xtype : 'textfield',
				id : CONFIGNAME.get('conWaybillNum'),
				name : CONFIGNAME.get('conWaybillNum')
			},{
				xtype:'combobox',
				id:'searchTransType',
				name:CONFIGNAME.get('transType'),
				fieldLabel:i18n('i18n.recompense.transType'),
				queryMode:'local',
				editable:false,
				store:DpUtil.getStore(CONFIGNAME.get('transType'),null,['code','codeDesc'],DataDictionary.TRANSPORT_TYPE),
				displayField:'codeDesc',
				valueField:'code'
			} ]
		} ];
	}
});

/**
 * 搜索按钮panel
 */
Ext.define('ButtonSearchPanel', {
	extend : 'NormalButtonPanel',
	id:'buttonSearchPanel',
//	weight : 800,
//	height : 37,
	grid : null,
	searchCondition:null,
	initComponent : function() {
		var me = this;
		this.items = me.getItems();
		this.callParent();
	},
	/**
	 * <p>查看理赔详情<p>
	 * @author 张斌
	 * @date 2012-06-14
	 */
	detailRecompense:function(){
		var me = this;
		var record = me.grid.getSelectionModel().getSelection();
		if (record.length!=1) {
			MessageUtil.showMessage(i18n('i18n.recompense.havenotSelectOne'));
			return;
		}
		var succseeFn = function(json){
			SearchError = true;
			SearchWorkFlow = true;
			RecompenseInfoDataNew = true;
			if(Ext.isEmpty(Ext.getCmp('recompenseDetailsUI'))){
				new RecompenseDetailsUI({'record':json.app,'actionIds':json.actionIds,'flag':'button'}).show();
				document.getElementsByTagName("html")[0].style.overflowY="auto";
				document.getElementsByTagName("html")[0].style.overflowX="auto";
				viewport.doLayout();
			}else{
				Ext.getCmp('recompenseDetailsUI').record = json.app;
				Ext.getCmp('recompenseDetailsUI').workFlowList = json.oaWorkflowList;
				Ext.getCmp('recompenseDetailsUI').actionIds = json.actionIds;
				Ext.getCmp('recompenseDetailsUI').show();
				document.getElementsByTagName("html")[0].style.overflowY="auto";
				document.getElementsByTagName("html")[0].style.overflowX="auto";
				viewport.doLayout();
			}
		}
		var failureFn = function(json){
			if(Ext.isEmpty(json)){
				MessageUtil.showErrorMes(i18n('i18n.recompense.serviceMoreTime'));
				//DpUtil.showMessage(i18n('i18n.recompense.serviceMoreTime'));
			}else{
				MessageUtil.showErrorMes(json.message);
			}
		}
		var param = {'recompenseId':record[0].get('id')};
		recompenseData.searchRecompenseById(param,succseeFn,failureFn);
	},
	getItems : function() {
		var me = this;
		return [ {
			xtype : 'leftbuttonpanel',
			items : [ {
				xtype : 'button',
				text :i18n('i18n.recompense.detailInfo'),
				handler : function() {
					me.detailRecompense();
				}
			} ]
		}, {
			xtype : 'middlebuttonpanel'
		}, {
			xtype : 'rightbuttonpanel',
			items : [ {
				xtype : 'button',
				text :i18n('i18n.recompense.search'),
				handler : function() {
					me.searchRecompense();
				}
			}
//			, {
//				xtype : 'button',
//				text : i18n('i18n.recompense.reset'),
//				handler : function() {
//					me.resetForm();
//				}
//			} 
			]
		} ];
	},
	searchRecompense : function() {
		var me = this;
		//获取界面的值，用户判断查询条件逻辑
		var record = new Condition();
		if(!Ext.getCmp('recompenseSearchFrom').getForm().isValid()){
			MessageUtil.showErrorMes(i18n('i18n.recompense.pleaseInputCondiction'));
			return;
		}
		Ext.getCmp('recompenseSearchFrom').getForm().updateRecord(record);
		//设置上报部门的值
		var reportDept =Ext.getCmp('searchReportDept').getValueId();
		record.set(CONFIGNAME.get('reportDept'),reportDept);
		//设置所属区域的值
		var belongDept = Ext.getCmp(CONFIGNAME.get('belongArea')).getValueId();
		record.set(CONFIGNAME.get('belongArea'),belongDept);
		//上报日期
		var reportDate = record.get(CONFIGNAME.get('conReportStartTime'));
		//上报结束日期
		var reportEndDate = record.get(CONFIGNAME.get('reportEndTime'));
		//处理时间
		var approveTime = record.get(CONFIGNAME.get('approveTime'));
		//处理结束时间
		var approveEndTime = record.get(CONFIGNAME.get('approveEndTime'));
		var conWaybillNum = record.get(CONFIGNAME.get('conWaybillNum'));
		var conCustNum = record.get(CONFIGNAME.get('conCustNum'));
		if((!Ext.isEmpty(conWaybillNum))||(!Ext.isEmpty(conCustNum))){
			Ext.getCmp('pagingToolbar').moveFirst( );
		}else{
			if (me.validateDate(reportDate,reportEndDate,approveTime,approveEndTime)) {
        		Ext.getCmp('pagingToolbar').moveFirst( );
		    }
		}
	},
	validateDate:function(reDate,reEndDate,appTime,appEndTime){
		if ((Ext.isEmpty(reDate)||Ext.isEmpty(reEndDate))&&
				(Ext.isEmpty(appTime)||Ext.isEmpty(appEndTime))) {
			MessageUtil.showMessage(i18n('i18n.recompense.infomation.timeMustOne'));
			 return false;
		}
		//上报时间 上报结束数据不能超过31天
		if ((reEndDate-reDate)/(24*3600*1000)>31) {
			MessageUtil.showMessage(i18n('i18n.recompense.reportTimeMoreSearchCondition'));
			 return false;
		}
		if(reEndDate-reDate<0){
			MessageUtil.showMessage(i18n('i18n.recompense.reportTimeStartTimeMoreEndTime'));
			 return false;
		}
		//处理时间 处理结束时间不能超过31天
		if ((appEndTime-appTime)/(24*3600*1000)>31) {
			MessageUtil.showMessage(i18n('i18n.recompense.commitTimeMoreSearchCondition'));
			return false;
		}
		if(appEndTime-appTime<0){
			MessageUtil.showMessage(i18n('i18n.recompense.commitTimeStartTimeMoreEndTime'));
			return false;
		}
		return true;
	},
	resetForm : function() {
		Ext.getCmp('recompenseSearchFrom').getForm().reset();
	}
});


/**
 * <p>搜索查询结果显示grid<p>
 * @author 潘光均
 * @date 2012-04-14
 */
Ext.define(	'RecompenseSearchGridPanel',{
					extend : 'SearchGridPanel', // --第一步,继承PopupGridPanel
					id:'recompenseSearchGridPanel',
					store : null,
					flex:7,
					record : null,
					autoScroll:true,
					initComponent : function() {
						var me = this;
						me.columns = me.getColumns();
						me.selModel =  me.getSelModel();
						me.bbar = me.getBBar();
						this.callParent();
					},
					listeners:{
						itemdblclick:function(){
							Ext.getCmp('buttonSearchPanel').detailRecompense();
						}
					},
					getColumns:function(){
						return [{
							xtype : 'rownumberer',
							width : 40,
							text : i18n('i18n.recompense.num')
							},{ // --第二步,规定表格title
							header : i18n('i18n.recompense.recompensetype'),
							dataIndex : CONFIGNAME.get('recompenseType'),
							 renderer : function(value){
					            	return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.RECOMPENSE_TYPE);
					            }
						}, {
							header : i18n('i18n.recompense.dangerType'),
							dataIndex : CONFIGNAME.get('insurType'),
							renderer : function(value){
								return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.DANGER_TYPE);
							}
						}, {
							header : i18n('i18n.recompense.orderNumOrErroeNum'),
							dataIndex :'waybill.waybillNumber'
						},{
							header : i18n('i18n.recompense.recompenseMethod'),
							dataIndex : CONFIGNAME.get('recompenseMethod'),
							 renderer : function(value){
					            	return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.RECOMPENSE_WAY);
					            }
						}, {
							header : i18n('i18n.recompense.reportDept'),
							dataIndex : CONFIGNAME.get('reportDeptName')
						}, {
							header : i18n('i18n.recompense.reportPerson'),
							dataIndex : CONFIGNAME.get('reportManName')
						}, {
							header : i18n('i18n.recompense.reportTime'),
							dataIndex : CONFIGNAME.get('reportDate'),
							renderer : function(value){
				            	return new Date(value).format('yyyy-MM-dd hh:mm:ss');
				            }
						}, {
							header : i18n('i18n.recompense.handleStatus'),
							dataIndex : CONFIGNAME.get('status'),
							 renderer : function(value){
					            	return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.RECOMPENSE_STATUS);
					            }
						}, {
							header : i18n('i18n.recompense.handleTime'),
							dataIndex : CONFIGNAME.get('modifyDate'),
							renderer : function(value){
								if (null!=value) {
									return new Date(value).format('yyyy-MM-dd hh:mm:ss');
								}
				            }
						}, {
							header : i18n('i18n.recompense.handlePerson'),
							dataIndex : CONFIGNAME.get('modifyUserName')
						}, {
							header : i18n('i18n.recompense.transType'),
							dataIndex :'waybill.transType',
							renderer : function(value){
					            	return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.TRANSPORT_TYPE);
					            }
						} ];
					},
					getSelModel : function() {
						return  Ext.create('Ext.selection.CheckboxModel', {
							mode : 'SINGLE'
						})
					},
					getBBar : function() {
						var me = this;
				    	return Ext.create('Ext.toolbar.Paging', {
							id : 'pagingToolbar',
							store : me.store,
							displayMsg : i18n('i18n.recompense.displayMsg'),
							displayInfo : true,
							items:[
					            '-',{
										text: i18n('i18n.recompense.page_count'),
										xtype: 'tbtext'
									},Ext.create('Ext.form.ComboBox', {
					                   width:          50,
					                   value:          '10',
					                   triggerAction:  'all',
					                   forceSelection: true,
					                   editable:       false,
					                   name:           'comboItem',
					                   displayField:   'value',
					                   valueField:     'value',
					                   queryMode:      'local',
					                   store : Ext.create('Ext.data.Store',{
					                       fields : ['value'],
					                       data   : [
					                           {'value':'10'},
					                           {'value':'15'},
					                           {'value':'20'},
					                           {'value':'25'},
					                           {'value':'40'},
					                           {'value':'100'}
					                       ]
					                   }),
					                   listeners:{
											select : {
												scope : this,
									            fn: function(_field,_value){
									            	var pageSize = Ext.getCmp('recompenseSearchGridPanel').store.pageSize;
									            	var newPageSize = parseInt(_field.value);
									            	if(pageSize!=newPageSize){
									            		Ext.getCmp('recompenseSearchGridPanel').store.pageSize = newPageSize;
									            		Ext.getCmp('pagingToolbar').moveFirst();
									            	}
									            }
									        }
					                   }
					               }),{
										text: i18n('i18n.recompense.pageNumber'),
										xtype: 'tbtext'
					               }]
						});
				    	}
})

Ext.define('RecompenseSearchView', {
	extend : 'Ext.container.Viewport',
	autoScroll:true,
	record : null,
	layout : {
		type : 'vbox',
		align : 'stretch'
	},
	isArrayContaintChar:function(char,array){
		for ( var i = 0; i < array.length; i++) {
			if (array[i]==char) {
				return true;
			}
		}
		return false;
	},
	initComponent : function() {
		var me = this;
		me.record = new Condition();
		me.items = me.getItems();
		Ext.getCmp('recompenseSearchFrom').getForm().updateRecord(me.record);
		this.callParent();
		this.addStoreListener();
	},
	produceSearchConditon:function(){
		var me = this;
		//获取界面的值，用户判断查询条件逻辑
		var record = new Condition();
		Ext.getCmp('recompenseSearchFrom').getForm().updateRecord(record);
		//设置上报部门的值
		var reportDept =Ext.getCmp('searchReportDept').getValueId();
		record.set(CONFIGNAME.get('reportDept'),reportDept);
		//设置所属区域的值
		var belongDept = Ext.getCmp(CONFIGNAME.get('belongArea')).getValueId();
		record.set(CONFIGNAME.get('belongArea'),belongDept);
		//运输类型
		var transType=Ext.getCmp('searchTransType').getValue();
		record.set(CONFIGNAME.get('transType'),transType);
		
		//定运单号
		var ordWay = record.get('orderWaybillNum');
		//差错号
		var errNum = record.get(CONFIGNAME.get('conWaybillNum'));
		//客编码
		var custNum = record.get(CONFIGNAME.get('conCustNum'));
		//客户姓名
		var custName = record.get(CONFIGNAME.get('custName'));
		//上报日期
		var reportDate = record.get(CONFIGNAME.get('conReportStartTime'));
		//上报结束日期
		var reportEndDate = record.get(CONFIGNAME.get('reportEndTime'));
		//处理时间
		var approveTime = record.get(CONFIGNAME.get('approveTime'));
		//处理结束时间
		var approveEndTime = record.get(CONFIGNAME.get('approveEndTime'));
		//判断定运单选择
		if (i18n('i18n.recompense.orderNum') == ordWay) {
			record.set(CONFIGNAME.get('conWaybillNum'), errNum);
		} else if (i18n('i18n.recompense.ErroeNum') == ordWay) {
			record.set(CONFIGNAME.get('errorNum'), errNum);
		}
		//如果凭证号不为空，则创建一个新的只有凭证号的查询条件
		if (!(Ext.isEmpty(errNum))) {
			record = new Condition();
			record.set(CONFIGNAME.get('conWaybillNum'), errNum);
			// Ext.data.StoreManager.lookup(me.storeId).on('load',function(th,records,successful,operation,eOpts
			// ){
			//如果客户编码存在则客户姓名，上报部门，所属区域查询条件失效
		} else if (!(Ext.isEmpty(custNum))) {
			record.set(CONFIGNAME.get('custName'), null);
			record.set(CONFIGNAME.get('reportDept'), null);
			record.set(CONFIGNAME.get('belongArea'), null);
			//如果按客户名称查询，则需要选择一个上报部门
		} else if (!(Ext.isEmpty(custName))&&me.isArrayContaintChar('4',User.roleids)) {
			if (Ext.isEmpty(reportDept)) {
				MessageUtil.showMessage(i18n('i18n.recompense.infomation.reportDeptNeed'));
				return;
			}
		}
		//查询条件构造
		return record;
		
	},
	addStoreListener:function(){
		var me = this;
		Ext.getCmp('recompenseSearchGridPanel').store.on('beforeload',function(store,operation,obj){
			var record = me.produceSearchConditon();
			var searchParams = DpUtil.produceSearchParams(record);
			Ext.apply(operation,{
				params : searchParams
			});
		});
	},
	getItems : function() {
		var me =this;
		var recompenseSearchFrom = new RecompenseSearchFrom();
		 var recompenseSearchStore =recompenseData.getRecompenseSearchStore();
		 var recompenseSearchGridPanel = new RecompenseSearchGridPanel({
			 'store' : recompenseSearchStore,
			 'record' : me.record
		 });
		 var buttonSearchPanel = new ButtonSearchPanel({
				'store' : recompenseSearchStore,
				'grid' : recompenseSearchGridPanel,
				'searchCondition':me.produceSearchConditon()
			});
		return [ recompenseSearchFrom, buttonSearchPanel,recompenseSearchGridPanel ];
	}
});



/**.
 * <p>
 * 理赔查询主页面</br>
 * </p>
 * @author  潘光均
 * @时间    2012-03-19
 */
Ext.onReady(function() {
	var keys =[ 'DANGER_TYPE','RECOMPENSE_WAY',
	            //奖罚类型
	            'AWARD_TYPE',
	            //客户等级
	            'MEMBER_GRADE',
	            //消息提醒方式
	            'MESSAGE_REMIND',
	            //奖罚对象类型
	            'AWARD_TARGET_TYPE',
	            //工作流类型
	            'RECOMPENSE_WORKFLOW_TYPE',
	            //工作流状态
	            'RECOMPENSE_WORKFLOW_STATUS',
	            //付款方式
	            'PAY_METHOD',
	           'RECOMPENSE_TYPE',
	           'RECOMPENSE_STATUS',
	           //客户领款方式
	           'CUST_DROWMONEY_TYPE',
	           //账户性质
	           'ACCOUNT_PROPERTIES',
	           //运输类型
	           'TRANSPORT_TYPE'];
	initDataDictionary(keys);
	initUser();
	initArea();
	initAreas();
	initAllAreas();
    Ext.QuickTips.init();
    if(Ext.isEmpty(User)){
    	MessageUtil.showErrorMes(i18n('i18n.recompense.noPermission'));
		return;
	}
	viewport = new RecompenseSearchView();
	viewport.setAutoScroll(false);
	viewport.doLayout();

});
Ext.form.Field.prototype.msgTarget='side';


//弹出框，添加快速理赔的退回原因
Ext.define('ReturnBackResionWindow',{
	extend:'PopWindow',
	title:i18n('i18n.recompense.refuseBackReasonWrite'),
	id:'returnBackResionWindow',
	fbar:null,
	items:null,
	closeAction:'hide',
	width:450,
	height:175,
	layout: 'fit',
	listeners:{
		beforeshow:function(){
			Ext.getCmp('returenBackResion').reset();
		}
	},
	initComponent:function(){
		var me = this;
		me.fbar = me.getFbar();
		me.items = me.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		var returnInfo = {
				xtype : 'titlepanel',
				flex : 1,
				layout : {
					type : 'vbox',
					align : 'stretch'
				},
				items : [{
					xtype : 'textarea',
					id:'returenBackResion',
					maxLength:1000,
					flex : 3
				} ]
			};
		return [returnInfo];
	},
	/**.
	 * <p>
	 * 快速理赔退回</br>
	 * </p>
	 * @author  张斌
	 * @时间    2012-05-17
	 */
	returnBack:function(){
		   var me = this;
		   var id = Ext.getCmp('recompenseDetailsUI').record.id;
	       var workflowId = Ext.getCmp('recompenseDetailsUI').record.workflowId;
	       var recompenseMethod = Ext.getCmp('recompenseDetailsUI').record.recompenseMethod;
	       //退回原因
	       var returenBackResion = Ext.getCmp('returenBackResion').getValue();
	       if(!Ext.isEmpty(returenBackResion)){
	    	   if(Ext.isEmpty(returenBackResion.trim())){
	    		   MessageUtil.showMessage(i18n('i18n.recompense.pleaseInputReturnBackResion'));
	    		   return;
		       } 
	       }else{
	    	   MessageUtil.showMessage(i18n('i18n.recompense.pleaseInputReturnBackResion'));
	    	   return;
	       }
	       var param  = {'actionId':'270','recompenseView':{'app':{'id':id,'workflowId':workflowId,'recompenseMethod':recompenseMethod,'rejectReason':returenBackResion}}};
	       var successFn = function(json){
	    	   me.hide();
	    	   MessageUtil.showInfoMes(json.message);
	    	   DpUtil.refreshRecompenseInfo();
	       };
	       var failureFn = function(json){
	    	   if(Ext.isEmpty(json)){
	    		 MessageUtil.showErrorMes(i18n('i18n.recompense.serviceMoreTime'));
					//DpUtil.showMessage(i18n('i18n.recompense.serviceMoreTime'));
				}else{
					MessageUtil.showErrorMes(json.message);
				}
	       };
	       recompenseData.performAction(param,successFn,failureFn);	
	},
	getFbar:function(){
		var me = this;
		return [{
			xtype:'button',
			text:i18n('i18n.recompense.determine'),
			handler:function(){
				me.returnBack();
			}
		},{
			xtype:'button',
			text:i18n('i18n.recompense.cancel'),
			handler:function(){
				me.hide();
			}
		}];
	}
});

