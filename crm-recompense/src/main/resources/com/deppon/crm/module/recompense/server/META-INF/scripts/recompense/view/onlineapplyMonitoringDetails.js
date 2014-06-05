/*
 * 在线理赔受理
 * @author Rock( 13816470956 )
 */
var recompenseData = (CONFIG.get("TEST")) ? new RecompenseDataTestN() : new RecompenseDataN();
IsReset = true;
Ext.onReady(function() {
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget='side';
	initUser();
	initArea();
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
			Ext.getCmp('dangetInfoDelete').setDisabled(true);
			Ext.getCmp('dangetInfoUpdate').setDisabled(true);
			Ext.getCmp('dangetInfoAdd').setDisabled(true);
			Ext.getCmp('dangetInfoAdd').setDisabled(true);
			Ext.getCmp('attachmentListAddId').setDisabled(true);
			Ext.getCmp('attachmentListDeleteId').setDisabled(true);
			return [showRecompenseType,processDangerInfo,recompenseInfoTab];
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
						readOnly:true,
						editable:false,
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
			            readOnly:true,
			            displayField: 'codeDesc',
			            valueField: 'code'
					  },{
		           	   xtype: 'numberfield',
						decimalPrecision:0,
						minValue:0,
						maxValue:9999999999,
						readOnly:true,
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
		  	            readOnly:true,
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
		             cls:'readonly',
		             readOnly:true,
		             displayField: 'codeDesc',
		             valueField: 'code'
			    }]
			}];
		},
	    initComponent:function(){
	    	var me = this;
	        me.items = me.getItems();
			this.callParent();
	    }
	});
});	