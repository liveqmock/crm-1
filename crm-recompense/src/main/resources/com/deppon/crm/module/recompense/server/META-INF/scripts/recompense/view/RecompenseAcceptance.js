/*
 * 在线理赔受理
 * @author Rock( 13816470956 )
 */
var recompenseData = (CONFIG.get("TEST")) ? new RecompenseDataTestN() : new RecompenseDataN();
IsReset = true;
Ext.onReady(function() {
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget='side';
		var keys = [
	            'RECOMPENSE_STATUS',	            // 处理状态
	        	'RECOMPENSE_WAY',                   // 理赔方式 
	        	'RECOMPENSE_TYPE',                  //理赔类型
	        	'DANGER_TYPE',                       //出险类型
	        	'MEMBER_GRADE',						//客户等级
	        	'TRANSPORT_TYPE'					//运输类型
	        	];
	initDataDictionary(keys);                    	// 初始化数据字典
	initUser();
	initArea();
	Ext.define('RecompenseAcceptance', {			// 容器panel
		extend : 'Ext.container.Viewport',
		height : 520,
		autoScroll:true,
		getItems : function() {
			return [{
				autoScroll : true,
				layout : 'fit',
				border:false,
				items : [
				         new RecompenseSearchFrom(), 
				         new searchButtonPanel(), 
				         new gridPanel() ]
			}];
		},
		initComponent : function() {
			this.items = this.getItems();
			this.callParent();
		}
	});
	Ext.define('RecompenseSearchFrom', {
		extend : 'SearchFormPanel',
		height : 40,
		items : [{
			layout:{
				type:'table',
				columns:3
			},
			defaultType:'textfield',
			defaults:{
				margin : '0 0 10 10',
				labelWidth:70,
				width:250
			},items:[{
	            fieldLabel: i18n('i18n.recompense.orderNum'),
	            name: 'TextBox'
	            ,id : CONFIGNAME.get('waybillNumber')
				,listeners : {
	                specialkey : function(field, e) {
	                	if (e.getKey() == Ext.EventObject.ENTER) {
		        			 var ids = CONFIGNAME.get('waybillNumber'),valu ='';
		        			 if( typeof(Ext.getCmp( ids ).getValue()) != "undefined" ){
		        				 valu = Ext.getCmp( ids ).getValue();
		        			 };
		        			 var param = {'waybillNum':valu };
							 recompenseData.searchList(param,successFn,failureFn);
	                    }
	                }
	            }
	        },{
	   		 xtype : 'button',
	   		 width:70,
			 text :i18n('i18n.recompense.search'),
			 handler : function() {
				 Ext.getCmp('pagingToolbar').moveFirst();
			}
		 }]
		}]
	});
	Ext.define('searchButtonPanel', {				// 查看详情、拒绝、查询三个按钮的panel
		extend : 'NormalButtonPanel',
		id : 'Name',
		border : 0,
		items : [
		         {	xtype : 'leftbuttonpanel',
		        	items : [{
		        		xtype : 'button',
		        		text :i18n('i18n.recompense.detailInfo'), //查看详情
		        		handler : function() {
		        			var selections = Ext.getCmp('gridPanelId').getSelectionModel().getSelection();
		        			if(selections.length!=1){
		        				MessageUtil.showMessage(i18n('i18n.recompense.pleaseSelectOneLine'));
		        				return;
		        			}
		        			var onlineApplyStatus= selections[0].get('status');//获得在线理赔状态
		        			if(onlineApplyStatus=='Rejected'){ //如果是已经拒绝的在线理赔
				    		        				MessageUtil.showErrorMes(i18n('i18n.recompense.RejectedOnlineApp'));
				    		        				return;
				    		        			};
							if(onlineApplyStatus!='WaitAccept'){ //如果状态不为已拒绝或者待受理
				    		        MessageUtil.showErrorMes(i18n('i18n.recompense.err.waitaccept'));
				    		        	return;
				    		        			};
		        			var id = selections[0].get('id');
		        			var successFn = function(json){
		        				new OnlineRecompenseWindow({'record':json.app}).show();
		        				document.getElementsByTagName("html")[0].style.overflowY="auto";
								document.getElementsByTagName("html")[0].style.overflowX="auto";
								viewport.doLayout();
			        			Ext.getCmp('normalRadio').setDisabled(true);
			        			Ext.getCmp('fastRecompenseMethod_id').setDisabled(true);
			        			Ext.getCmp('onlineRadio').setDisabled(false);
			        			Ext.getCmp('onlineRadio').setValue(true);
		        			};
		        			var failureFn = function(json){
		        				if(Ext.isEmpty(json)){
		        					MessageUtil.showErrorMes(i18n('i18n.recompense.serviceMoreTime'));
		        				}else{
		        					MessageUtil.showErrorMes(json.message);
		        				}
		        			};
		        			var param = {'recompenseId':id};
		        			recompenseData.handleOnlineApply(param,successFn,failureFn);
		        		}
		        	}, {
		        		xtype : 'button',
		        		text :i18n('i18n.recompense.refuse'), //拒绝
		        		handler : function() {
		        			//先判断是否选择了一条数据
		        			var selections = Ext.getCmp('gridPanelId').getSelectionModel().getSelection();
		        			if(selections.length==1){
		        				//如果选择了一条，进行询问
		        				var onlineApplyStatus= selections[0].get('status');//获得在线理赔状态
				    		        			if(onlineApplyStatus=='Rejected'){ //如果是已经拒绝的在线理赔
				    		        				MessageUtil.showErrorMes(i18n('i18n.recompense.RejectedOnlineApp'));
				    		        				return;
				    		        			} else if (onlineApplyStatus=='WaitAccept'){
				    		        				MessageUtil.showQuestionMes(i18n('i18n.recompense.isSureToFefuseRecompense'),
							    						function(e){
							        				//点击是
							    							if (e == 'yes') {
							    	        					var onlineApplyId = '';
								    	        				var successFn = function(json){
								    	        					MessageUtil.showInfoMes(json.message);
								    		        				Ext.getCmp('pagingToolbar').moveFirst();
								    		        			},
								    		        				failureFn = function(json){
								    		        				if(Ext.isEmpty(json)){
								    		        					MessageUtil.showErrorMes(i18n('i18n.recompense.serviceMoreTime'));
								    		        					//DpUtil.showMessage(i18n('i18n.recompense.serviceMoreTime'));
								    		        				}else{
								    		        					MessageUtil.showErrorMes(json.message);
								    		        				}
								    		        			};
								    		        		
								    		        			onlineApplyId = selections[0].get('id');
									        					var params = {'refuseId':onlineApplyId};
											        			recompenseData.Refuse( params, successFn, failureFn );
			    							}
			        			});
				    		        				
				    		        				
				    		        			}else {
				    		        				MessageUtil.showErrorMes(i18n('i18n.recompense.err.waitaccept'));

				    		        			
				    		        			};
				    		        			
			        			
	        				}else{
	        					MessageUtil.showMessage(i18n('i18n.recompense.pleaseSelectOneLine'));
	        				}
		        		}
		        	}]
		         }, {
		        	 xtype : 'middlebuttonpanel'
		         }
		]
	});
	
	Ext.define('gridPanel', {					// 表格的panel
		extend : 'SearchGridPanel',
		id:'gridPanelId',
		height : 430,
		store:null,
		columns : [{
			xtype : 'rownumberer',
			width : 40,
			text : i18n('i18n.recompense.num')
			},{
			text : i18n('i18n.recompense.recompenseMethod'),
			dataIndex : 'recompenseMethod',
            renderer : function(value){
        	    return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.RECOMPENSE_WAY);
            },
			flex : 1
		}, {
			xtype : '',
			text : i18n('i18n.recompense.orderNum'),
			flex : 1,
			dataIndex : 'waybillNumber'
		}, {
			xtype : '',
			text :'受理部门',
			flex : 1,
			dataIndex : 'acceptDeptName'
		},{
			text : i18n('i18n.recompense.custNumber'),
			dataIndex : 'customerNum',
			xtype : '',
			format : '',
			flex : 1
		}, {
			text : i18n('i18n.recompense.insureAmount'),
			dataIndex : 'insurAmount',
			xtype : '',
			flex : 1,
			format : '0.000'
		}, {
			text : i18n('i18n.recompense.recompensePerson'),
			dataIndex : 'customerId',
			xtype : '',
			format : '0,000',
			flex : 1
		}, {
			text : i18n('i18n.recompense.claimAmount'),
			dataIndex : 'recompenseAmount',
			xtype : '',
			format : 'l',
			flex : 1
		}, {
			text : i18n('i18n.recompense.claimReason'),
			dataIndex : 'recompenseReason',
			flex : 1
		}, {
			text : i18n('i18n.recompense.handleStatus'),
			dataIndex : 'status',
            renderer : function(value){
        	    return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.RECOMPENSE_STATUS);
            },
			flex : 1
		}, {
			text : i18n('i18n.recompense.infoTime'),
			dataIndex : 'createDate',
			renderer:function(value){
				if(!Ext.isEmpty(value)){
					return new Date(value).format('yyyy-MM-dd hh:mm:ss');
				}else{
					return null;
				}
				
			},
			flex : 1
		}, {
			text : i18n('i18n.recompense.refuseBackReason'),
			dataIndex : 'rejectReason',
			flex : 1,
//			  增加这个函数，用于显示每行的提示信息  
            renderer: function(value,metaData,record,colIndex,store,view) {  
			       if(!Ext.isEmpty(value)){
			    	   metaData.tdAttr = 'data-qtip="'+value+'"';  
				       return value; 
			       } 
			}  
		}, {
			text : i18n('i18n.recompense.refuseBackTime'),
			renderer:function(value){
				if(!Ext.isEmpty(value)){
					return new Date(value).format('yyyy-MM-dd hh:mm:ss');
				}else{
					return null;
				}
				
			},
			dataIndex : 'rejectTime',
			flex : 1
		} ],
		getSelModel : function() {
			return  Ext.create('Ext.selection.CheckboxModel', {
				mode : 'SINGLE'
			})
		},						// 简单选择功能开启
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
		                   value:          '15',
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
						            	var pageSize = Ext.getCmp('gridPanelId').store.pageSize;
						            	var newPageSize = parseInt(_field.value);
						            	if(pageSize!=newPageSize){
						            		Ext.getCmp('gridPanelId').store.pageSize = newPageSize;
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
	    },
	    bbar : null,
		initComponent : function() {
			this.store = recompenseData.getStore();
			this.bbar = this.getBBar();
			this.selModel =  this.getSelModel();
			this.store.on('beforeload',function(th,operation){
				var waybillNum = Ext.getCmp(CONFIGNAME.get('waybillNumber')).getValue();
				if(!Ext.isEmpty(waybillNum)){
					waybillNum = waybillNum.trim();
				}
				var param = {'waybillNum':waybillNum};
				Ext.apply(operation,{
					params : param
				});
			});
			this.callParent();
//			this.getView().on('render', function(view) {
//				view.tip = Ext.create('Ext.tip.ToolTip', {
//					id:'tipId',
//					target: view.el,
//					delegate: view.itemSelector,
//					listeners: {
//						beforeshow: function updateTipBody(me) {
//							if(!Ext.isEmpty(view.getRecord(me.triggerElement).get('rejectReason'))){
//								me.update('退回原因:'
//										+ view.getRecord(me.triggerElement).get('rejectReason'));
//							}else{
//								return false;
//							}
//						}
//					}
//				});
//			});
		}
	});
	
	
	function changeInsurTypeStore(recompenseType){
		Ext.getCmp('insurType').clearValue();
		if(recompenseType=='unbilled'){
		   //三个combox的数据都修改
		   Ext.getCmp('insurType').getStore().removeAll(false);
		   Ext.getCmp('insurType').getStore().add(
				   {	 code:'BREAKED'		,codeDesc:'破损'	},
				   {	 code:'DAMP'		,codeDesc:'潮湿'	},
				   {	 code:'POLLUTE'		,codeDesc:'污染'	},
				   {	 code:'GOODS_LESS'	,codeDesc:'内物短少'	},
				   {	 code:'PIECE_LOST'	,codeDesc:'整件丢失'	},
				   {	 code:'TICKET_LOST'	,codeDesc:'整票丢失'	},
				   {	 code:'OTHER'		,codeDesc:'其它'	}
		   );
		}
		if(recompenseType=='lost_goods'){
			
		   //三个combox的数据都修改
		   Ext.getCmp('insurType').getStore().removeAll(false);
		   Ext.getCmp('insurType').getStore().add(
				   {	 code:'PIECE_LOST'		,codeDesc:'整件丢失'	},
				   {	 code:'TICKET_LOST'		,codeDesc:'整票丢失'	}
		   );
		}
		if(recompenseType=='abnormal'){				
		   //三个combox的数据都修改
		   Ext.getCmp('insurType').getStore().removeAll(false);
		   Ext.getCmp('insurType').getStore().add(
				   {	 code:'BREAKED'			,codeDesc:'破损'	},
				   {	 code:'DAMP'			,codeDesc:'潮湿'	},
				   {	 code:'POLLUTE'			,codeDesc:'污染'	},
				   {	 code:'GOODS_LESS'		,codeDesc:'内物短少'	},
				   {	 code:'FALSELY_CLAIM'	,codeDesc:'冒领'	},
				   {	 code:'OTHER'			,codeDesc:'其它'	}
		   );
			}
	}
	
	
	/**.
	 * <p>
	 * 在线理赔受理WINDOW</br>
	 * </p>
	 * @author  张斌
	 * @时间    2012-04-18
	 */
	Ext.define('OnlineRecompenseWindow',{
		extend:'PopWindow',
		title:i18n('i18n.recompense.processOnlineRecompense'),
		fbar:null,
		items:null,
		closeAction:'destroy',
		layout: 'fit',
		initComponent:function(){
			var me = this;
			me.fbar = me.getFbar();
			me.items = me.getItems();
			me.loadData();
			Ext.getCmp('recompenseListAdd').setDisabled(true);
			 Ext.getCmp('recompenseListUpdate').setDisabled(true);
			 Ext.getCmp('recompenseListDelete').setDisabled(true);
			 Ext.getCmp('tranceListAdd').setDisabled(true);
			 Ext.getCmp('tranceListUpdate').setDisabled(true);
			 Ext.getCmp('tranceListDelete').setDisabled(true);
			Ext.getCmp(CONFIGNAME.get('recompenseAmount')).setReadOnly(true);
			Ext.getCmp(CONFIGNAME.get('recompenseAmount')).addCls('readonly');
			Ext.getCmp(CONFIGNAME.get('claimParty')).setReadOnly(true);
			
			Ext.getCmp(CONFIGNAME.get('companyName')).setReadOnly(true);
			Ext.getCmp(CONFIGNAME.get('companyName')).addCls('readonly');
			Ext.getCmp(CONFIGNAME.get('companyPhone')).setReadOnly(true);
			Ext.getCmp(CONFIGNAME.get('companyPhone')).addCls('readonly');
			//Ext.getCmp('shipperRadio').setDisabled(true);
			//Ext.getCmp(CONFIGNAME.get('claimParty')).addCls('readonly');
			this.callParent();
		},
		listeners:{
			hide:function(){
				document.body.scrollLeft=0;
				document.body.scrollTop=0;
				document.getElementsByTagName("html")[0].style.overflowY="hidden";
				document.getElementsByTagName("html")[0].style.overflowX="hidden";
				viewport.doLayout();
			},
			beforehide:function(){//隐藏之前将所有数据清空
				IsReset = true;
				Ext.getCmp('showRecompenseTypeForm').getForm().reset();
				Ext.getCmp('processDangerInfoForm').getForm().reset();
				Ext.getCmp('recompenseInfoForm').getForm().reset();
				Ext.getCmp('bankInfoForm').getForm().reset();
			}

		},
		/**
		 * <P>
		 * 根据理赔类型设置insurType中的Store值
		 * </p>
		 * 
		 * @author 张斌
		 * @date 2012-04-18
		 */
		changeInsurTypeStoreData:function(recompenseType){
			changeInsurTypeStore(recompenseType);
		},
		/**.
		 * <p>
		 * 加载数据</br>
		 * </p>
		 * @author  张斌
		 * @时间    2012-04-18
		 */
		loadData:function(){
			var me = this;
			var recompenseApp = new RecompenseApp(me.record);
			var waybill = new WaybillModel(recompenseApp.get(CONFIGNAME.get('waybill')));
			Ext.getCmp('processDangerInfoForm').getForm().loadRecord(waybill);
			Ext.getCmp('processDangerInfoForm').getForm().loadRecord(recompenseApp);
			Ext.getCmp('onlineDeptName').setValue(Ext.isEmpty(Area)?'':Area.deptName);
			Ext.getCmp('onlineReportManId').setValue(User.userId);
			Ext.getCmp('onlineReportDeptId').setValue(User.deptId);
			Ext.getCmp('onlineDeptId').setValue(Ext.isEmpty(Area)?'':Area.id);
			Ext.getCmp('onlineReportPerson').setValue(User.empName);
			Ext.getCmp('onlineReportDept').setValue(User.deptName);
			Ext.getCmp('onlineReportDate').setValue(new Date());
			me.changeInsurTypeStoreData(recompenseApp.get('recompenseType'));
			
			Ext.getCmp('recompenseInfoForm').getForm().loadRecord(recompenseApp);
			Ext.getCmp('bankInfoForm').getForm().loadRecord(recompenseApp);//在线理赔显示账户信息
			Ext.getCmp('showRecompenseType').setValue(recompenseApp.get(CONFIGNAME.get('recompenseType')));
			//设置出现描述、理赔清单、货物托运清单、附件表格信息
			Ext.getCmp('dangerInfoGrid').getStore().loadData(recompenseApp.get(CONFIGNAME.get('issueItemList')));
			var goodsTransList = recompenseApp.get(CONFIGNAME.get('goodsTransList'));
			var goodsTranceList = new Array();
			var recompenseList = new Array();
			for(var i = 0;i<goodsTransList.length;i++){
				if(goodsTransList[i].isClaims){
					recompenseList.push(goodsTransList[i]);
				}else{
					goodsTranceList.push(goodsTransList[i]);
				}
			}
			Ext.getCmp('recompenseListGird').getStore().loadData(recompenseList);
			Ext.getCmp('goodsTranceGird').getStore().loadData(goodsTranceList);
			Ext.getCmp('attachmentGrid').getStore().loadData(recompenseApp.get(CONFIGNAME.get('attachmentList')));
		},
		getItems:function(){
			var me = this;
			var showRecompenseType = null;
			if(Ext.isEmpty(Ext.getCmp('showRecompenseTypeForm'))){
				showRecompenseType = new ShowRecompenseType();
			}else{
				showRecompenseType = Ext.getCmp('showRecompenseTypeForm');
			}
			var processDangerInfo = null;
			if(Ext.isEmpty(Ext.getCmp('processDangerInfoForm'))){
				processDangerInfo = new ProcessDangerInfo();
			}else{
				processDangerInfo = Ext.getCmp('processDangerInfoForm');
			}
			var recompenseInfoTab = null
			if(Ext.isEmpty(Ext.getCmp('recompenseInfoTab'))){
				//设置其flag以标记为在线理赔
				recompenseInfoTab = new RecompenseInfoTab({'width':750,'flag':'online'});
			}else{
				recompenseInfoTab = Ext.getCmp('recompenseInfoTab');
			}
			return [showRecompenseType,processDangerInfo,recompenseInfoTab];
		},
		/**.
		 * <p>
		 * 在线理赔受理</br>
		 * </p>
		 * @author  张斌
		 * @时间    2012-04-18
		 */
		createRecompense:function(){
			var me = this;
			var recompenseApp = new RecompenseApp(me.record);
			var me = this;
			if(!Ext.getCmp('processDangerInfoForm').getForm().isValid()){
				return;
			}
			if(!Ext.getCmp('recompenseInfoForm').getForm().isValid()){
				MessageUtil.showMessage(i18n('i18n.recompense.haveMoreRecompenseInfo'));
				return;
			}
			var dangerInfoArray = new Array();
			Ext.getCmp('dangerInfoGrid').getStore().each(function(record){
				dangerInfoArray.push(record.data);
			});
			if(dangerInfoArray.length==0){
				MessageUtil.showMessage(i18n('i18n.recompense.dangerInfoMustHaveOne'));
				return ;
			}
			var attachmentListArray = new Array();
			Ext.getCmp('attachmentGrid').getStore().each(function(record){
				attachmentListArray.push(record.data);
			});
			Ext.getCmp('processDangerInfoForm').getForm().updateRecord(recompenseApp);
			Ext.getCmp('showRecompenseTypeForm').getForm().updateRecord(recompenseApp);
			var custId = Ext.getCmp(CONFIGNAME.get('custId')).getValue();
			var custmer = {'id':custId};
			recompenseApp.set(CONFIGNAME.get('customer'),custmer);
			Ext.getCmp('recompenseInfoForm').getForm().updateRecord(recompenseApp);
			var successFn = function(json){
				Ext.getCmp('onlineApplyDetermine').setDisabled(false);
				me.hide();
				Ext.getCmp('pagingToolbar').moveFirst();
				MessageUtil.showInfoMes(json.message);
			}
			var failureFn = function(json){
				Ext.getCmp('onlineApplyDetermine').setDisabled(false);
				if(Ext.isEmpty(json)){
					MessageUtil.showErrorMes(i18n('i18n.recompense.serviceMoreTime'));
					//DpUtil.showMessage(i18n('i18n.recompense.serviceMoreTime'));
				}else{
					MessageUtil.showErrorMes(json.message);
				}
			}
			var params = {'recompenseView':{'issueItemAddList':dangerInfoArray,'app':recompenseApp.data,'attachmentAddList':attachmentListArray
				}}
			Ext.getCmp('onlineApplyDetermine').setDisabled(true);
			recompenseData.createRecompense(params,successFn,failureFn);
		},
		getFbar:function(){
			var me = this;
			return [{
				xtype:'button',
				text:i18n('i18n.recompense.determine'),
				id:'onlineApplyDetermine',
				handler:function(){
					/**
			   		 * 表单提交优化
			   		 */
			   		MessageUtil.showQuestionMes(
			   				'是否通过该理赔？',function(e){
			   			if (e == 'yes') {
			   				me.createRecompense();
			   			}else{
			   				return;
			   			}
			   		});
				}
			},{
				xtype:'button',
				text:i18n('i18n.recompense.cancel'),
				handler:function(){
					me.close();
				}
				
			}];
		}
	});
	/**.
	* <p>
	* 出险信息界面</br>
	* </p>
	* @author  张斌
	* @retuens {DangerInfo}
	* @时间    2012-04-11
	*/
	Ext.define('ProcessDangerInfo',{
		extend:'TitleFormPanel',
		id:'processDangerInfoForm',
		height:186,
		width:750,
		getItems:function(){
			var me = this;
			return [{xtype: 'basicfiledset',
			    title: i18n('i18n.recompense.dangerInfo'),
			    layout: {
			        type: 'table',
			        columns: 4
			    },
			    defaults:{
			    	labelSeparator:'',
			    	style:'margin-right:5px;',
			    	labelWidth:60,
			    	width:170
			    },
			    items:[{xtype: 'textfield',
				    	readOnly:true,
				    	cls:'readonly',
				    	name: CONFIGNAME.get('waybillNumber'),
				    	emptyText:i18n('i18n.recompense.orderNum'),
				        fieldLabel:i18n('i18n.recompense.orderNumOrErroeNum*'),
					    colspan: 1
					},{
						xtype: 'textfield',
						name: CONFIGNAME.get('goodsName'),
						colspan: 1,
				        fieldLabel:i18n('i18n.recompense.goodsName'),
				        cls:'readonly',
				        readOnly:true
					},{
						xtype: 'combo',
						name: CONFIGNAME.get('transType'),
						colspan: 1,
						fieldLabel:i18n('i18n.recompense.transType'),
						queryMode: 'local',
			            cls:'readonly',
			            readOnly:true,
			            store:DpUtil.getStore('transport',null,['code','codeDesc'],DataDictionary.TRANSPORT_TYPE),
			            displayField: 'codeDesc',
			            valueField: 'code'
				    },{
						xtype: 'textfield',
						colspan: 1,
						name: CONFIGNAME.get('packaging'),
				        fieldLabel:i18n('i18n.recompense.packging'),
				        cls:'readonly',
				        readOnly:true
					  },{
						xtype: 'textfield',
						colspan: 1,
						name: CONFIGNAME.get('insured'),
				        fieldLabel:i18n('i18n.recompense.insureMan'),
				        cls:'readonly',
				        readOnly:true
					  },{
						xtype: 'textfield',
						colspan: 1,
						name: CONFIGNAME.get('telephone'),
				        fieldLabel:i18n('i18n.recompense.connectMobilePhone'),
				        cls:'readonly',
				        readOnly:true
					  },{
						xtype: 'textfield',
						colspan: 1,
						name: CONFIGNAME.get('startStation'),
				        fieldLabel:i18n('i18n.recompense.startStation'),
				        cls:'readonly',
				        readOnly:true
					  },{
						xtype: 'textfield',
						colspan: 1,
						name: CONFIGNAME.get('endStation'),
				        fieldLabel:i18n('i18n.recompense.endStation'),
				        cls:'readonly',
				        readOnly:true
					  },{
						xtype: 'datefield',
						colspan: 1,
						format:'Y-m-d',
						name: CONFIGNAME.get('sendDate'),
				        fieldLabel:i18n('i18n.recompense.sendGoodsTime'),
				        cls:'readonly',
				        readOnly:true
					  },{
						xtype: 'textfield',
						colspan: 1,
						name: CONFIGNAME.get('pwv'),
				        fieldLabel:i18n('i18n.recompense.numWeightVL'),
				        cls:'readonly',
				        readOnly:true
					  },{
						xtype: 'textfield',
						colspan: 1,
						name: CONFIGNAME.get('insurAmount'),
				        fieldLabel:i18n('i18n.recompense.insureAmount'),
				        cls:'readonly',
				        readOnly:true
					  },{
						xtype: 'textfield',
						colspan: 1,
						name: CONFIGNAME.get('receiveDept'),
				        fieldLabel:i18n('i18n.recompense.receiveDept'),
				        cls:'readonly',
				        readOnly:true
					  },{
						colspan: 1,
						readOnly:true,
						cls:'readonly',
						value:User.deptName,
						id:'onlineReportDept',
				        fieldLabel:i18n('i18n.recompense.reportDept'),
				        xtype:'textfield'
					  },{
	 					colspan: 1,
						value:User.empName,
						xtype:'textfield',
						id:'onlineReportPerson',
				        fieldLabel:i18n('i18n.recompense.reportPerson'),
				        cls:'readonly',
				        readOnly:true
				    },{
						xtype: 'datefield',
						editable:false,
						cls:'readonly',
						readOnly:true,
						id:'onlineReportDate',
						value:new Date(),
						format:'Y-m-d',
						name: CONFIGNAME.get('reportDate'),
				        fieldLabel:i18n('i18n.recompense.reportTime')
					},{
						xtype: 'datefield',
						editable:false,
						allowBlank:false,
						format:'Y-m-d',
						id:'updateInsurDate_show',
						name: CONFIGNAME.get('insurDate'),
				        fieldLabel:i18n('i18n.recompense.dangerTime*'),
				        listeners:{
				        	select:function(th,value,eOpts ){
	                		   if(value>Ext.getCmp('onlineReportDate').getValue()){
	                			   MessageUtil.showMessage(i18n('i18n.recompense.dangerDateEarlyReportDate'));
	                			   th.reset();
	                		   }
		                	}
			            }
					},{
						colspan: 1,
						id:'insurType',
						name: CONFIGNAME.get('insurType'),
				        fieldLabel:i18n('i18n.recompense.dangerType*'),
				        xtype:'combo',
			            store: DpUtil.getStore('TRANSPORT_TYPE',null,['code','codeDesc'],DataDictionary.TRANSPORT_TYPE),
			            queryMode: 'local',
			            forceSelection: true,
			            editable:false,
			            allowBlank:false,
			            displayField: 'codeDesc',
			            valueField: 'code'
					  },{
		           	   xtype: 'numberfield',
						decimalPrecision:0,
						minValue:0,
						maxValue:9999999999,
						allowBlank:false,
						name: CONFIGNAME.get('insurQuantity'),
				        fieldLabel:i18n('i18n.recompense.dangerCount*')
		              },{
		            	colspan: 1,
		  				name: CONFIGNAME.get('deptId'),
		  		        fieldLabel:i18n('i18n.recompense.belongArea*'),
		  		        id:'onlineDeptId',
		  		        xtype:'combo',
		  		        value:Ext.isEmpty(Area)?null:Area.id,
		  	            store: DpUtil.getStore(CONFIGNAME.get('deptId'),null,['id','deptName'],Ext.isEmpty(Area)?[]:[Area]),
		  	            queryMode: 'local',
		  	            forceSelection: true,
		  	            allowBlank:false,
		  	            valueField:'id',
		  	            displayField: 'deptName',
		  	            listeners:{
		  	            	select:function(th){
		  	            		Ext.getCmp('onlineDeptName').setValue(th.getRawValue());
		  	            	}
				        } 
	                 },{
						hidden:true,
						xtype:'textfield',
						id:CONFIGNAME.get('arriveCustomerId'),
						name: CONFIGNAME.get('arriveCustomerId')
				   },{
						hidden:true,
						xtype:'textfield',
						id:CONFIGNAME.get('leaveCustomerId'),
						name: CONFIGNAME.get('leaveCustomerId')
				  },{
						hidden:true,
						value:User.deptId,
						id:'onlineReportDeptId',
						xtype:'textfield',
						name: CONFIGNAME.get('reportDept')
				  },{
						hidden:true,
						value:User.userId,
						id:'onlineReportManId',
						xtype:'textfield',
						name:CONFIGNAME.get('reportMan')
				  },{
						hidden:true,
						xtype:'textfield',
						id:'onlineDeptName',
						name:CONFIGNAME.get('deptName')
					  }]
		           
		    }];
		},
		initComponent:function(){
			var me = this;
		    me.items = me.getItems();
		    var successFn = function(json){
		    	Ext.data.StoreManager.lookup(CONFIGNAME.get('deptId')).removeAll();
	        	Ext.data.StoreManager.lookup(CONFIGNAME.get('deptId')).add(json.deptList);
		    };
	        var filureFn = function(json){
	        	if(Ext.isEmpty(json)){
	        		MessageUtil.showErrorMes(i18n('i18n.recompense.serviceMoreTime'));
					//DpUtil.showMessage(i18n('i18n.recompense.serviceMoreTime'));
				}else{
					MessageUtil.showErrorMes(json.message);
				}
	        };
	        //获取大区数据
		    recompenseData.searchAreas(null,successFn,filureFn);
			this.callParent();
		}
	});
	Ext.define('ShowRecompenseType',{
		extend:'TitleFormPanel',
		id:'showRecompenseTypeForm',
		height:69,
		getItems:function(){
			return [{xtype: 'basicfiledset',
				title: i18n('i18n.recompense.recompenseType'),
				layout:{
					type:'table',
					columns:3
				},
				defaultType:'textfield',
				defaults:{
					labelSeparator:'',
					labelWidth:55,
					width:210
				},
			    items:[{
					colspan: 1,
					id:'showRecompenseType',
					name: CONFIGNAME.get('recompenseType'),
			        fieldLabel:i18n('i18n.recompense.recompensetype*'),
			        xtype:'combo',
			        oldValue:'unbilled',
		            store: DpUtil.getStore(CONFIGNAME.get('recompenseType'),null,['code','codeDesc'],DataDictionary.RECOMPENSE_TYPE),
		             queryMode: 'local',
//		             cls:'readonly',
//		             readOnly:true,
		             displayField: 'codeDesc',
		             valueField: 'code',
		             listeners:{
		             	'beforerender':function(f){
							f.getStore().each(function(record){
								if(record.get('code')=='unbilled'){
									f.getStore().remove(record);//去掉未开单理赔
								}
							});							
						},
						'select':function(t){
							changeInsurTypeStore(t.getValue());
						}
		             }	
			    }]
			}];
		},
	    initComponent:function(){
	    	var me = this;
	        me.items = me.getItems();
			this.callParent();
	    }
	});
	
	
	
	
	var viewport = new RecompenseAcceptance();
	viewport.setAutoScroll(false);
	viewport.doLayout();

});	