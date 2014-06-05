Ext.onReady(function(){
	var warnLostCustDailyTotal = null;
	var keys =[
	   'COOPERATION_INTENTION',//意愿程度
	   'MEMBER_GRADE'//客户等级
	];
	var exportStatus=null;   //导出标志
	initDeptAndUserInfo();
	initDataDictionary(keys);
	Ext.define('SearchButtons',{
		extend:'NormalButtonPanel',
		items:null,
		initComponent:function(){
			var me=this;
			me.items=me.getItems();
			this.callParent();
		},
		getItems:function(){
			var me=this;
			return [{
				xtype:'leftbuttonpanel',
				width:320,
				items:[{
					xtype:'button',
					text:'导出',
					id:'exitApplyButtonId',
					handler:function(btn){
						if(exportStatus==null){
							MessageUtil.showMessage('请先查询，再导出！');
							return;
						}else if(exportStatus.data.length>0){
							//显示导出提示框
							Ext.MessageBox.show( {
								title:'导出当前查询结果',
							    msg:'数据导出中，请稍候！',
							    progressText : '导数据中...',
							    width : 300,
							    wait : true,
							    waitConfig : {
							        interval : 300
							    }
							});
							//发送导出数据的请求
							var toUrl='../marketingReport/exportWarnLostCustReportExcel.action'; //导出详细数据
							var dlUrl='../marketingReport/downloadDetail.action'; //下载文件
//							var deptid= Ext.getCmp('dept').getValue();
					        var beginTime= Ext.getCmp('startDate').getRawValue();
						    var endTime= Ext.getCmp('endDate').getRawValue();
						    //部门id
						    var deptid = null;
						    //部门级别
							var deptLevel = null;
							if(!Ext.isEmpty(Ext.getCmp('dept').getValue())){
								deptid = Ext.getCmp('dept').getValue();
								deptLevel = 'dep';
							}else if(!Ext.isEmpty(Ext.getCmp('smallDept').getValue())){
								deptid = Ext.getCmp('smallDept').getValue();
								deptLevel = 'area';
							}else if(!Ext.isEmpty(Ext.getCmp('bigDept').getValue())){
								deptid = Ext.getCmp('bigDept').getValue();
								deptLevel = 'bigarea';
							}else if(!Ext.isEmpty(Ext.getCmp('businessDept').getValue())){
								deptid = Ext.getCmp('businessDept').getValue();
								deptLevel = 'carrer';
							}else if(!Ext.isEmpty(Ext.getCmp('manageDept').getValue())){
								deptid = Ext.getCmp('manageDept').getValue();
								deptLevel = 'card';
							}else{
								deptid = '104';
							}
						    
						    Ext.Ajax.request({
							    url: toUrl,
							    params: {
							        deptid:deptid,                   //传递4个要添加的参数
							        beginTime:beginTime,
							        endTime:endTime,
							        deptLevel:deptLevel
							    },
							    success: function(response, opts) {
							        var obj = Ext.decode(response.responseText);
							        var downloadurl=obj.downloadurl;
							        if(downloadurl!=null){
							        	window.location.href=dlUrl+'?downloadurl='+downloadurl;
							        }else{
							        	MessageUtil.showMessage('导出文件失败！','');
							        }
							        console.dir(obj);
							        btn.setDisabled(true);
									setTimeout(function(){btn.setDisabled(false);},3000);  //按钮点击时，变灰，时间为3s 
									Ext.MessageBox.hide();
							    },
							    failure: function(response, opts) {
							    	MessageUtil.showMessage('连接失败','');
							        console.log('异常码：' + response.status);
							        btn.setDisabled(true);
									setTimeout(function(){btn.setDisabled(false);},3000);  //按钮点击时，变灰，时间为3s 
									Ext.MessageBox.hide();
							    }
							});
							
						}else{
						    MessageUtil.showMessage('查询结果为空，不能导出！');
							return;
						}
					}
				}]
			},{
				xtype : 'middlebuttonpanel'
			},{
				xtype:'rightbuttonpanel',
				items:[{
					xtype:'button',
					text:'查询',
					id:'searchButtonId',
					handler:function(){
						exportStatus=Ext.getCmp('searchGrid').getStore().load();
					}
				}]
			}]
		}
	});
	Ext.define('SearchGrid',{
		extend:'SearchGridPanel',
		store : null,
		flex:7,
		autoScroll:true,
		listeners:{
			//点击列排序时，总计行始终出现在表格最后一行
			sortchange:function(){
				var replaceModel;
				var count = this.store.getCount()-1;
				for(var i = 0;i <= count;i++){
					if(this.store.getAt(i).get("cadreName").indexOf("总计") >= 0){
						//获取总计行
						replaceModel = this.store.getAt(i);
						//移除排序后的总计行
						this.store.removeAt(i);
						//将移除的总计行添加到表格最后一列
						this.store.add(replaceModel);
					}
				}
			}
		},
		initComponent : function() {
			var me = this;
			me.columns = me.getColumns();
//			me.selModel = me.getSelModel();
			me.store = me.getGridStore();
			me.bbar = me.getBBar();
			this.callParent();
			me.store.on('beforeload',function(store, operation, eOpts){
				var form = Ext.getCmp('searchForm').getForm();
				var params = {};
				var deptid = null;
				var deptLevel = null;
				if(!Ext.isEmpty(Ext.getCmp('dept').getValue())){
					deptid = Ext.getCmp('dept').getValue();
					deptLevel = 'dep';
				}else if(!Ext.isEmpty(Ext.getCmp('smallDept').getValue())){
					deptid = Ext.getCmp('smallDept').getValue();
					deptLevel = 'area';
				}else if(!Ext.isEmpty(Ext.getCmp('bigDept').getValue())){
					deptid = Ext.getCmp('bigDept').getValue();
					deptLevel = 'bigarea';
				}else if(!Ext.isEmpty(Ext.getCmp('businessDept').getValue())){
					deptid = Ext.getCmp('businessDept').getValue();
					deptLevel = 'carrer';
				}else if(!Ext.isEmpty(Ext.getCmp('manageDept').getValue())){
					deptid = Ext.getCmp('manageDept').getValue();
					deptLevel = 'card';
				}else{
					deptid = '104';
				}
				params['deptid'] = deptid;
				params['deptLevel'] = deptLevel;
				params['beginTime'] = Ext.getCmp('startDate').getValue();
				params['endTime'] = Ext.getCmp('endDate').getValue();
				warnLostCustDailyTotal = null;
				var successFn = function(json){
					warnLostCustDailyTotal = json.warnLostCustDailyTotal;
					Ext.getCmp('searchButtonId').setDisabled(false);
				}
				var failFn = function(json){
					MessageUtil.showMessage(json.message);
				}
				var url = '../marketingReport/queryWarnLostCustDailyTotal.action';
				Ext.data.proxy.Server.prototype.timeout = 12000000;
				Ext.data.Connection.prototype.timeout = 12000000;
				Ext.getCmp('searchButtonId').setDisabled(true);
				MRUtil.requestJsonAjax(url,params,successFn,failFn);
				Ext.apply(operation,{
					params : params
				});
			});
			me.store.on('load',function(store){
				var addCountData = function(){
					if(warnLostCustDailyTotal){
						warnLostCustDailyTotal.cadreName = '总计';
						store.add(warnLostCustDailyTotal);
						window.clearInterval(scheduler); 
						Ext.getCmp('searchButtonId').setDisabled(false);
					}
				}
				var scheduler = window.setInterval(addCountData,1000); 
			});
		},
		//获取表头
		getColumns:function(){
			var me = this;
			return [{
				header:'经营本部',
				align:'center',
				dataIndex:'cadreName',
				renderer:function(val,metaData,record){
					if(!Ext.isEmpty(val)){
						if(val.indexOf("总计")!== -1){
							return	'<span style="color:#ff0000;font-weight:bold;">'+val+'</span>';
						}
						else{
							return val;
						}
					}				
				}
			},{
				header:'事业部',
				align:'center',
				dataIndex:'careerDeptName'
			},{
				header:'大区',
				align:'center',
				dataIndex:'bigAreaName'
			},{
				header:'营业区',
				align:'center',
				dataIndex:'areaName'
			},{
				header:'所属部门',
				align:'center',
				dataIndex:'deptName'
			},{
				header:'预警客户回访总数',
				align:'center',
				dataIndex:'returnVisitWarnLostCount'
			},{
				header:'预警客户总数',
				align:'center',
				dataIndex:'warnLostCount'
			},{
				header:'预警客户回访率',
				align:'center',
				dataIndex:'warnLostRTPercentage',
				renderer : function(value){
					return Ext.util.Format.number(value,'0.00')+"%";
				}
			},{
				header:'零担类预警客户回访数',
				align:'center',
				dataIndex:'lTTReturnVisitWarnLostCount'
			},{
				header:'零担类预警客户总数',
				width:120,
				align:'center',
				dataIndex:'lTTWarnLostCount'
			},{
				header:'零担类预警客户回访率',
				width:120,
				align:'center',
				dataIndex:'lTTWarnLostRTPercentage',
				renderer : function(value){
					return Ext.util.Format.number(value,'0.00')+"%";
				}
			},{
				header:'快递类预警客户回访数',
				width:120,
				align:'center',
				dataIndex:'erpressReturnVisitWarnLostCount'
			},{
				header:'快递类预警客户总数',
				width:120,
				align:'center',
				dataIndex:'expressWarnLostCount'
			},{
				header:'快递类预警客户回访率',
				width:120,
				align:'center',
				dataIndex:'expressWarnLostRTPercentage',
				renderer : function(value){
					return Ext.util.Format.number(value,'0.00')+"%";
				}
			},{
				header:'预警后流失客户数',
				width:120,
				align:'center',
				dataIndex:'lostAfterWarnCustCount'
			},{
				header:'预警客户总数',
				width:120,
				align:'center',
				dataIndex:'warnLostCount'
			},{
				header:'预警客户流失率',
				width:120,
				align:'center',
				dataIndex:'lostAfterWarnPre',
				renderer : function(value){
					return Ext.util.Format.number(value,'0.00')+"%";
				}
			},{
				header:'预警后流失零担客户数',
				width:120,
				align:'center',
				dataIndex:'lTTlostAfterWarnCustCount'
			},{
				header:'预警零担客户总数',
				width:120,
				align:'center',
				dataIndex:'lTTWarnLostCount'
			},{
				header:'预警零担客户流失率',
				width:120,
				align:'center',
				dataIndex:'lTTlostAfterWarnCustPercentage',
				renderer : function(value){
					return Ext.util.Format.number(value,'0.00')+"%";
				}
			},{
				header:'预警后流失快递客户数',
				width:120,
				align:'center',
				dataIndex:'expresslostAfterWarnCustCount'
			},{
				header:'预警快递客户总数',
				width:120,
				align:'center',
				dataIndex:'expressWarnLostCount'
			},{
				header:'预警快递客户流失率',
				width:120,
				align:'center',
				dataIndex:'expresslostAfterWarnCustPercentage',
				renderer : function(value){
					return Ext.util.Format.number(value,'0.00')+"%";
				}
			}]
		},
		//获取数据
		getGridStore:function(){
			return Ext.create('WarnLostCustDailyStore');
		},
		//获取bbar
		getBBar:function(){
			var me = this;
			return Ext.create('Ext.toolbar.Paging',{
				displayMsg : '共<font color=\"green\"> {2} </font>条记录,当前页记录索引<font color=\"green\"> {0} - {1}</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;',
				displayInfo : true,
				id:'pagingToolbar',
				store:me.store,
				items:[
		            '-',
		        	{
						text: '每页',
						xtype: 'tbtext'
					},Ext.create('Ext.form.ComboBox', {
		               width:          50,
		               value:          '20',
		               triggerAction:  'all',
		               forceSelection: true,
		               editable:       false,
		               name:           'comboItem',
		               displayField:   'value',
		               valueField:     'value',
		               queryMode:      'local',
		               readOnly:true,
		               cls:'readonly',
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
					            	var pageSize = Ext.getCmp('searchGrid').store.pageSize;
					            	var newPageSize = parseInt(_field.value);
					            	if(pageSize!=newPageSize){
					            		Ext.getCmp('searchGrid').store.pageSize = newPageSize;
					            		Ext.getCmp('pagingToolbar').moveFirst();
					            	}
					            }
					        }
		               }
		           }),{
						text: '条',
						xtype: 'tbtext'
		           }]
			});
		}
	});
	var SearchForm = Ext.create('SearchForm',{
		id:'searchForm',
		formType:'day'
	});
	var SearchButtons = Ext.create('SearchButtons',{
		id:'searchButtons'
	});
	var SearchGrid = Ext.create('SearchGrid',{
		id:'searchGrid'
	});
	var view = Ext.create('Ext.container.Viewport',{
		layout : {
			type : 'vbox',
			align : 'stretch'
		},
		items:[SearchForm,SearchButtons,SearchGrid]
	});
});