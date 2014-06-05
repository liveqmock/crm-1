/**
 * 流失预警客户明细报表
 */
Ext.onReady(function(){
	var keys =[
	   'COOPERATION_INTENTION',//意愿程度
	   'MEMBER_GRADE',//客户等级
       'LOSS_REASON'//流失原因
	];
	var exportStatus=null;      //导出标志
    Ext.QuickTips.init();
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
							//显示提示框
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
							var toUrl='../marketingReport/exportWarnLostCustDetail.action'; //导出详细数据
							var dlUrl='../marketingReport/downloadDetail.action'; //下载文件
							var deptid= Ext.getCmp('dept').getValue();
					        var beginTime= Ext.getCmp('startDate').getRawValue();
						    var endTime= Ext.getCmp('endDate').getRawValue();
							Ext.Ajax.request({
									    url: toUrl,
									    params: {
									        deptid:deptid,                   //传递3个要添加的参数
									        beginTime:beginTime,
									        endTime:endTime
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
					handler:function(){
						if(Ext.isEmpty(Ext.getCmp('dept').getValue())){
							MessageUtil.showMessage('请选择至具体的营业部');
							return;
						}
						Ext.Ajax.timeout = 1200000;
						exportStatus=Ext.getCmp('searchGrid').getStore().load();
					}
				}]
			}]
		}
	});
	Ext.define('SearchGrid',{
		extend:'SearchGridPanel',
		store : null,
		flex:9,
		autoScroll:true,
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
				params['deptid'] = Ext.getCmp('dept').getValue();
				params['beginTime'] = Ext.getCmp('startDate').getValue();
				params['endTime'] = Ext.getCmp('endDate').getValue();
				Ext.apply(operation,{
					params : params
				});
			});
		},
		//获取表头
		getColumns:function(){
			var me = this;
			return [{
				xtype:'rownumberer',
				width:40,
				align:'center',
				text:'序号'
			},{
				header:'经营本部',
				align:'center',
				dataIndex:'cadreStandardName'
			},{
				header:'事业部',
				align:'center',
				dataIndex:'careerDeptStandardName'
			},{
				header:'大区',
				align:'center',
				dataIndex:'bigAreaStandardName'
			},{
				header:'营业区',
				align:'center',
				dataIndex:'areaStandardName'
			},{
				header:'所属部门',
				align:'center',
				dataIndex:'deptStandardName'
			},{
				header:'客户编码',
				width:120,
				align:'center',
				dataIndex:'custNumber'
			},{
				header:'客户等级',
				width:120,
				align:'center',
				dataIndex:'custLevelDesc'
			},{
				header:'客户名称',
				width:120,
				align:'center',
				dataIndex:'custName'
			},{
				header:'客户类别',
				width:120,
				align:'center',
				dataIndex:'custCategoryDesc'
			},{
				header:'预警状态',
				width:120,
				align:'center',
				dataIndex:'warnStatusDesc'
			},{
				header:'预发货时限',
				width:120,
				align:'center',
				dataIndex:'preDeliverytendTime',
				renderer:function(val,metaData ,record ){ 
					if(Ext.isEmpty(val)){
					return null;
					}
					return Ext.Date.format(new Date(record.get('preDeliverytBiegnTime')),'Y-m-d')+"~"+Ext.Date.format(new Date(val),'Y-m-d');
					}
			},{
				header:'是否为季节性',
				width:120,
				align:'center',
				dataIndex:'isSeasonDesc'
			},{
				header:'标记为季节性客户的次数',
				width:120,
				align:'center',
				dataIndex:'seasonTime'
			},{
				header:'预警时间',
				width:120,
				align:'center',
				dataIndex:'createTime',
				renderer:function(val){
					if(Ext.isEmpty(val)){
						return null;
					}
					return Ext.Date.format(new Date(val),'Y-m-d');
				}
			},{
				header:'平均发货时间间隔（天）',
				width:120,
				align:'center',
				dataIndex:'intervalNendTime'
			},{
				header:'最近一次发货时间',
				width:120,
				align:'center',
				dataIndex:'lastsendTime',
				renderer:function(val){
					if(Ext.isEmpty(val)){
						return null;
					}
					return Ext.Date.format(new Date(val),'Y-m-d');
				}
			},{
				header:'最近一次发货金额（元）',
				width:120,
				align:'center',
				dataIndex:'lastsendMoney'
			},{
				header:'流失原因',
				width:120,
				align:'center',
				dataIndex:'lostCause',
                renderer:function(value){
                    return rendererDictionary(value,DataDictionary.LOSS_REASON);
                }
			},{
				header:'回访次数',
				width:120,
				align:'center',
				dataIndex:'returnTimes'
			},{
				header:'流失原因备注',
				width:120,
				align:'center',
				dataIndex:'lostCustRemark',
                renderer:function(value){
                    if(!Ext.isEmpty(value)){
                        return '<span data-qtip="'+value+'">'+value+'</span>';
                    }
                }
			},{
				header:'预警信息变更时间',
				width:120,
				align:'center',
				dataIndex:'warnStatusChangeTime',
				renderer:function(val){
					if(Ext.isEmpty(val)){
						return null;
					}
					return Ext.Date.format(new Date(val),'Y-m-d');
				}
			},{
				header:'预警信息变更人',
				width:120,
				align:'center',
				dataIndex:'warnStatusChangeUser'
			},{
				header:'近90天总发货金额（元）',
				width:120,
				align:'center',
				dataIndex:'suminRecent90day'
			}]
		},
		//获取选择框
//		getSelModel:function() {
//			return  Ext.create('Ext.selection.CheckboxModel', {
//				mode:'SINGLE'
//			})
//		},
		//获取数据
		getGridStore:function(){
			return Ext.create('WarnLostCustStore');
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