// clean compile tomcat:run -f war-pom.xml
Ext.onReady(function() {
	//定义导出标志
	var exportStatus=null;
	/**
	 * 1定义表单输入面板
	 */
	Ext.define('WorsecustReportformPanel', {
				extend : 'SearchFormPanel',
				autoScroll : true,
				layout : {
					type : 'table',
					columns : 4
				},
				height : 80,
				initComponent : function() {
					var me = this;
					me.items = [{
								id : 'choosedept',
								xtype : 'deptlookup',
								fieldLabel : '选择部门',
								editable : false, // 非编辑状态
								margin : '20 0 10 0'
							}, {
								id : 'choosedate',
								xtype : 'datefield',
								fieldLabel : '选择时间',
								editable : false,
								format:'Y-m-d',
								margin : '20 10 10 10',
								value: new Date()
							}, {
								xtype : 'button',
								text : '查询',
								width : 75,
								margin : '20 20 10 20',
								handler : function(timeout) {
									var choosedept = Ext.getCmp('choosedept').getValue();
									var choosedate = Ext.getCmp('choosedate').getValue();
									var deptid = Ext.getCmp('choosedept').getValueId(); // 获取部门编码
									if (choosedept != '' && choosedate != null) { // 空值分别''，null
										exportStatus=Ext.getCmp('worsecustReportGridPanel').getStore().load(); // 加载数据
									} else {
										MessageUtil.showMessage('部门和时间均不能为空','');
									}
								}
							},{
								xtype : 'button',
								text : '导出',
								width : 75,
								margin : '20 20 10 20',
								handler : function(btn) {
									var choosedept = Ext.getCmp('choosedept').getValue();
									var choosedate = Ext.getCmp('choosedate').getRawValue();
									var deptid = Ext.getCmp('choosedept').getValueId(); // 获取部门编码
									var toUrl='../custReport/exportWorsecustReport.action';
									var AuthUrl='../custReport/verificationAuth.action';
									Ext.Ajax.request({
									    url: AuthUrl,
									    jsonData: {
									        deptid:deptid                //传递部门id
									    },
									    success: function(response, opts) {
									        var obj = Ext.decode(response.responseText);
									        var checkReportView = obj.checkReportView;
									        if(checkReportView == 1) {
									        	btn.setDisabled(true);
										        setTimeout(function(){btn.setDisabled(false);},3000);  //按钮点击时，变灰，时间为3s 
										        if (choosedept != '' && choosedate != null) { // 空值分别''，null
										            //验证成功时导出
													window.location.href=toUrl+'?deptid='+deptid+'&choosedate='+choosedate;
												} else {
													MessageUtil.showMessage('部门和时间均不能为空','');
												}
									        }
									    },
									    failure: function(response, opts) {
									    	var obj = Ext.decode(response.responseText);
									    	MessageUtil.showMessage('连接失败','');
									        console.log('异常码：' + response.status);
									    }
									});
								}
							}
					];
					this.callParent();
				}
			});
	/**
	 * 2定义内容 grid面板
	 */
	Ext.define('WorsecustReportGridPanel', {
		extend : 'SearchGridPanel',
		height:663,
		viewConfig: {forceFit:true}, //自动分配列宽
		loadMask : true,//显示加载提示
		initComponent : function() {
			var me = this;
			me.columns = me.getColumns();
			me.store = me.getWRStore();
			me.bbar = me.getBbar();
			this.callParent();
			me.store.on('beforeload', function(store, operation, eOpts) {
						var choosedept = Ext.getCmp('choosedept').getValue();
						var choosedate = Ext.getCmp('choosedate').getValue();
						var deptid = Ext.getCmp('choosedept').getValueId(); // 获取部门编码
						Ext.apply(operation, {
									params : {
										choosedept : choosedept,
										choosedate : choosedate,
										deptid : deptid
									}
								});
					});
		},
		getBbar : function() {
			var me = this;
			return Ext.create('Ext.toolbar.Paging', {
				displayMsg : '共<font color=\"green\"> {2} </font>条记录,当前页记录索引<font color=\"green\"> {0} - {1}</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;',
				displayInfo : true,
				id : 'pagingToolbar',
				store : me.store,
				items : ['-', {
							text : '每页',
							xtype : 'tbtext'
						}, Ext.create('Ext.form.ComboBox', {
							width : 50,
							value : '10',
							triggerAction : 'all',
							forceSelection : true,
							editable : false,
							name : 'comboItem',
							displayField : 'value',
							valueField : 'value',
							queryMode : 'local',
							store : Ext.create('Ext.data.Store', {
										fields : ['value'],
										data : [{
													'value' : '10'
												}, {
													'value' : '15'
												}, {
													'value' : '20'
												}, {
													'value' : '25'
												}, {
													'value' : '40'
												}, {
													'value' : '100'
												}]
									}),
							listeners : {
								select : {
									scope : this,
									fn : function(_field, _value) {
										var pageSize = Ext.getCmp('worsecustReportGridPanel').store.pageSize;
										var newPageSize = parseInt(_field.value);
										if (pageSize != newPageSize) {
											Ext.getCmp('worsecustReportGridPanel').store.pageSize = newPageSize;
											Ext.getCmp('pagingToolbar').moveFirst();
										}
									}
								}
							}
						}), {
							text : '条',
							xtype : 'tbtext'
						}]
			})
		},
		getColumns : function() {
			var me = this;
			return [{
						xtype : 'rownumberer'
					}, {
						text : '经营本部名称',
						dataIndex : 'managementDeptName'
					}, {
						text : '事业部名称',
						dataIndex : 'busDeptName'
					}, {
						text : '零担大区',
						dataIndex : 'bigAreaDeptName'
					}, {
						text : '所属营业区',
						dataIndex : 'areaDeptName'
					}, {
						text : '归属部门',
						dataIndex : 'attributionDeptName'
					}, {
						text : '责任部门',
						dataIndex : 'responsibilityDeptName'
					}, {
						text : '客户名称',
						dataIndex : 'custName'
					}, {
						text : '客户编码',
						dataIndex : 'custNumber'
					}, {
						text : '是否月结',
						dataIndex : 'isMonthlyCust',
						renderer:function(val){
							return val==1?val='月结客户':val='无'
						}
					}, {
						text : '月结合同规定还款时间',
						dataIndex : 'repaymentDate',
						width : 150
					}, {
						text : '临时欠款信用额度',
						dataIndex : 'overdraftCreditAmount',
						width : 140
					}, {
						text : '月结信用额度',
						dataIndex : 'monthCreditAmount'
					}, {
						text : '临时欠款信用额度余额',
						dataIndex : 'overdraftCreditBalanceAmount',
						width : 150
					}, {
						text : '月结信用额度余额',
						dataIndex : 'monthCreditBalanceAmount',
						width : 140
					}, {
						text : '临时欠款当期应收金额',
						dataIndex : 'overdrftRecivableAmount',
						width : 150
					}, {
						text : '月结当期应收金额',
						dataIndex : 'lttRecivableAmount',
						width : 140
					}, {
						text : '月结应收总额',
						dataIndex : 'monReceivableAmount'
					}, {
						text : '临欠应收总额',
						dataIndex : 'tdreceivableAmount'
					}, {
						text : '部门临欠额度',
						dataIndex : 'deptCredit'
					}, {
						text : '临时欠款信用额度使用率',
						dataIndex : 'overdraftCreditUserate',
						width : 165
					}, {
						text : '月结信用额度使用率',
						dataIndex : 'monthCreditUserate',
						width : 140
					}, {
						text : '部门应收总额',
						dataIndex : 'receivableAmount'
					},{
						text : '未还款类型一',
						dataIndex : 'norepaymentTypeOne'
					},{
						text : '未还款类型二',
						dataIndex : 'norepaymentTypeTwo'
					},{
						text : '未还款类型三',
						dataIndex : 'norepaymentTypeThree'
					},{
						text : '未还款类型四',
						dataIndex : 'norepaymentTypeFour'
					},{
						text : '未还款类型五',
						dataIndex : 'norepaymentTypeFive'
					} ]
		},
		getWRStore : function() {
			return new Ext.create('WorsecustReportStore', {
						storeId : 'worsecustReportStoreId'
					});
		}
	});
	
	/*
	 * 新建一个viewport，用于显示信用较差客户报表查询界面
	 * 
	 */
	var viewport=Ext.create('Ext.Viewport',{
		layout :{type: 'vbox',align : 'stretch'},
		items:[
				Ext.create('WorsecustReportformPanel', {
			   id : 'worsecustReportformPanel'
			   }), 
			   Ext.create('WorsecustReportGridPanel', {
			   id : 'worsecustReportGridPanel'
			   })
	    ]
	});
	viewport.setAutoScroll(false);	
	viewport.doLayout();
});
