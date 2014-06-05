/**
 * 一组疑似重复客户
 */
Ext.define('GroupRepeatCustGrid',{
	extend:'SearchGridPanel',
	id:'groupRepeatCustGridId',
	defaults:{align:'center'},
	autoScroll:true,
	listeners:{
		load:function(aa,bb){
			store.each(function(r){   
		     	if(r.get('status')=='TEMPORARY'){
		        	//给单元格涂色
		        	var cells = Ext.getCmp("dutyAllocationResultGridId").getView().getNodes()[girdcount].children;
		  			for(var i= 0;i<cells.length;i++){
		  				cells[i].style.backgroundColor='#28FF28';
		  			}
		        }
     		});
		}
	},
	initComponent:function(){ 
		var me = this;
		if(DpUtil.isEmpty(me.selModel)){
			me.selModel = new Ext.selection.CheckboxModel({mode:'MULTI'});
		}
		me.store = Ext.create('SearchGroupRepeatCustListStore',{
			'listeners':{
				beforeload:function(store,operation,e){		
					var selection = Ext.getCmp('mainRepeatCustGridId').getSelectionModel().getSelection();
					if(Ext.isEmpty(selection) || selection.length != 1){
						return false;
					}
					Ext.apply(operation,{
						params : {
							'searchCondition.mainCustId':selection[0].get('custId')
						}
					});
				},
				'load':function(){
					var grid = Ext.getCmp('groupRepeatCustGridId');
					grid.getStore().each(function(record,i){   
				     	if(record.get('isMainCust') =='1'){
				        	//给单元格涂色
				        	var cells = grid.getView().getNodes()[i].children;
				  			for(var i= 0;i<cells.length;i++){
				  				cells[i].style.backgroundColor='red';
				  			}
				        }
		     		});
				}
			}
		});
		
		me.columns = [
		    {//序号
		    	xtype:'rownumberer',width:40,
				header:i18n('i18n.custrepeat.serial_number')
		    },{//客户名称
				header:i18n('i18n.custrepeat.custName'),
				dataIndex:'custName',width:110
		    },{ //客户编码
				header :i18n('i18n.custrepeat.custCode'),
				dataIndex:'custCode',
				width:120
		    },{ //联系人姓名
				header :i18n('i18n.custrepeat.linkMainName'),
				dataIndex:'linkMainName',
				width:120
		    },{//所属部门
				header :i18n('i18n.custrepeat.deptName'),
				dataIndex:'deptName',width:150
		    },{//客户优先级
				header :i18n('i18n.custrepeat.custPriorityLevel'),
				dataIndex:'custGroup',width:150,renderer:function(value,metaData,record){
					if(record.get('isBigcustomer') == 1){
						return '大客户';
					}
					if(record.get('isContractCust') > 0){
						return '合同客户';
					}
					var html = rendererDictionary(value,DataDictionary.CUST_TYPE);
					if(value === 'RC_CUSTOMER'){//固定客户
						var custLevel = rendererDictionary(record.get('custLevel'),DataDictionary.MEMBER_GRADE);
						html = html + '('+custLevel+')';
					}
					return html;
				}
		    },{//近三个月发货金额
				header :i18n('i18n.custrepeat.deliveryMoneyTotal'),
				dataIndex:'deliveryMoneyTotal',width:120
		   	},{//银行账号
				header :i18n('i18n.custrepeat.bankAccount'),
				dataIndex:'bankAccount',width:100
		    },{//固定电话
				header :i18n('i18n.custrepeat.telephone'),
				dataIndex:'telephone',width:100
		    },
		    {//企业客户名称
				header :i18n('i18n.custrepeat.enterpriseCustName'),
				dataIndex:'custName',width:150,renderer:function(value,metaData,record){
					if(record.get('custType')=='ENTERPRISE'){return value;}
					return '';
				}
		    }
	    ];
		this.callParent(arguments);
   }
});

/**
 * 操作按钮面板
 */
Ext.define('CustRepeatOperateBtnPanel',{
	extend : 'BasicPanel',layout:'auto',
	id:'custRepeatOperateBtnPanelId',
	bbar:['->',
		{xtype:'button',text:i18n('i18n.custrepeat.btn_insert'),handler:function(btn){
			var win = Ext.getCmp('addCustomerWinId');
			if(Ext.isEmpty(win)){
				win = Ext.create('CustomerSearchWindow');
			}
			win.show();
		}},
		{xtype:'button',text:i18n('i18n.custrepeat.btn_delete'),handler:function(btn){
			var grid = Ext.getCmp('groupRepeatCustGridId');
			var selection = grid.getSelectionModel().getSelection();
			if(
				Ext.isEmpty(selection) || selection.length != 1 
				|| selection[0].get('isAdd') != 1){
				MessageUtil.showMessage(i18n('i18n.custrepeat.system.chooseNewCustForDelete'));return;
			}
			grid.getStore().remove(selection);
		}}
	]
});

/**
 * 疑似重复客户详情window
 */
Ext.define('CustRepeatDetailsWindow', {
    extend: 'PopWindow'
   //,y:5,x:1,
    ,width:820,height:500
    ,id:'custRepeatDetailsWindowId'
	,layout :{type: 'vbox',align : 'stretch'}
	,bbar:[
		{xtype:'button',text:i18n('i18n.custrepeat.btn_custDetails')/*客户详情*/,handler:function(btn){
			var me=this;
			var selection = Ext.getCmp('groupRepeatCustGridId').getSelectionModel().getSelection();
			if( Ext.isEmpty(selection) || selection.length != 1){
				MessageUtil.showMessage(i18n('i18n.custrepeat.system.chooseCustForOperate'));return;
			}
			var memberId = selection[0].get('custId');
		    if(DpUtil.isEmpty(memberId) || '0' == memberId){
				return;
			}
		    //调用客户GO 显示效果
		    CustviewUtil.openMemberWindow(memberId);
		}},'->',
		{
			xtype:'button',id:'confirmRepeatBtnId',
			text:i18n('i18n.custrepeat.btn_affirmRepeat'),handler:function(btn){
				var selection = Ext.getCmp('groupRepeatCustGridId').getSelectionModel().getSelection();
				if( Ext.isEmpty(selection) || selection.length < 2){
					MessageUtil.showMessage(i18n('i18n.custrepeat.system.chooseTwoCustForOperate'));return;
				}
				var disposeOpinion = Ext.getCmp('disposeOpinionId');
				var disposeOpinionValue = disposeOpinion.getValue();
				if(Ext.isEmpty(disposeOpinionValue)){
					MessageUtil.showMessage(i18n('i18n.custrepeat.system.writeProcessSuggest'));return;
				}
				if(false === disposeOpinion.isValid()){return;}
				var repeatCustList = new Array();
				Ext.each(selection,function(record){
					repeatCustList.push(record.data);
				});
				var params = {
					'repeatCustList':repeatCustList,
					'disposeOpinion':disposeOpinionValue
				};
				var successFn = function(response){
					processingMask.hide();/*隐藏遮罩层*/
					btn.setDisabled(false);
					MessageUtil.showInfoMes(i18n('i18n.custrepeat.msg.confirmRepeat_succeed'),function(){
						Ext.getCmp('custRepeatDetailsWindowId').close();
						Ext.getCmp('mainRepeatCustGridId').getStore().loadPage(1);
					});
				};
				var failFn = function(response){
					processingMask.hide();/*隐藏遮罩层*/
					btn.setDisabled(false);
				};
				var msg =i18n('i18n.custrepeat.system.creatRepeatWorkFlow');
				var chooseFn = function(e){
					if(e=='yes'){
						processingMask.show();/*显示遮罩层*/
						btn.setDisabled(true);
						DpUtil.ajaxRequest({
							'url':'../custrepeat/confirmRepeat.action',
							'successFn':successFn,'failFn':failFn,
							'jsonData':params,'timeout':5*60*1000
						});
						//DpUtil.requestJsonAjax('../custrepeat/confirmRepeat.action',params,successFn,failFn);
					}
				}
				MessageUtil.showQuestionMes(msg,chooseFn);
		}},
		{
			xtype:'button',id:'confirmNotRepeatBtnId',
			text:i18n('i18n.custrepeat.btn_notRepeat'),handler:function(btn){
				var store = Ext.getCmp('groupRepeatCustGridId').getStore();
				var repeatCustList = new Array();
				store.each(function(record){
					repeatCustList.push(record.data);
				});
				if( repeatCustList.length < 2){
					MessageUtil.showMessage(i18n('i18n.custrepeat.system.leastTwoCustForProcess'));return;
				}
				var params = {'repeatCustList':repeatCustList};
				var successFn = function(response){
					btn.setDisabled(false);
					Ext.getCmp('custRepeatDetailsWindowId').close();
					Ext.getCmp('mainRepeatCustGridId').getStore().loadPage(1);
				};
				var failFn = function(response){
					btn.setDisabled(false);
				};
				var msg =i18n('i18n.custrepeat.system.notRepeatOperate');
				var chooseFn = function(e){
					if(e=='yes'){
						btn.setDisabled(true);
						DpUtil.requestJsonAjax('../custrepeat/confirmNotRepeat.action',params,successFn,failFn);
					}
				}
				MessageUtil.showQuestionMes(msg,chooseFn);
				
		}},
		{
			xtype:'button',text:i18n('i18n.custrepeat.btn_close'),handler:function(btn){
				Ext.getCmp('custRepeatDetailsWindowId').close();
		}}
	],
	/**
	 * 设置是否只读操作
	 * @param {Boolean} bool true 可编辑、false or 其他 只读
	 */
	setReadOnly:function(bool){
		if(Ext.isEmpty(bool) || bool === false){
			Ext.getCmp('custRepeatOperateBtnPanelId').hide();
			Ext.getCmp('disposeOpinionId').hide();
			Ext.getCmp('confirmRepeatBtnId').hide();
			Ext.getCmp('confirmNotRepeatBtnId').hide();
			return;
		}
		Ext.getCmp('custRepeatOperateBtnPanelId').show();
		Ext.getCmp('disposeOpinionId').show();
		Ext.getCmp('confirmRepeatBtnId').show();
		Ext.getCmp('confirmNotRepeatBtnId').show();
	}
    ,initComponent: function() {
    	var me  = this;
        Ext.applyIf(me,{
        	items:[
        		Ext.create('CustRepeatOperateBtnPanel'),
        		Ext.create('GroupRepeatCustGrid',{'flex':1}),
        		{
        			xtype:'textareafield',margin:'5 0 0 0',
        			id:'disposeOpinionId',fieldLabel:i18n('i18n.custrepeat.disposeOpinion'),
        			maxLength : 300
        		}
        	]
        });
        me.callParent(arguments);
    }
    /* 添加滚动条 修改 begin */
	,'closeAction':'destroy'
	,listeners:{
		destroy:function(){
			if(viewport && viewport!=null){
				document.body.scrollLeft=0;
				document.body.scrollTop=0;
				document.getElementsByTagName("html")[0].style.overflowY="hidden";
				document.getElementsByTagName("html")[0].style.overflowX="hidden";
				viewport.doLayout();
			}
		},
		beforeclose:function(){
			Ext.getCmp('groupRepeatCustGridId').getStore().removeAll();
			Ext.getCmp('disposeOpinionId').setValue('');
		},
		show:function(win){
			//创建回调函数调用
			if(win.myCallBack){win.myCallBack();}
			if(viewport && viewport!=null){
				document.getElementsByTagName("html")[0].style.overflowY="auto";
				document.getElementsByTagName("html")[0].style.overflowX="hidden";
				viewport.doLayout();
			}
		}
	}
	/* 添加滚动条 修改 end */
});