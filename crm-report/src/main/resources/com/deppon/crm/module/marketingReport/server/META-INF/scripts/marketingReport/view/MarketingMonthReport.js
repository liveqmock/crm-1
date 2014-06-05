/**
 * 废弃的代码？
 */
Ext.onReady(function(){
	var keys =[
	   'COOPERATION_INTENTION',//意愿程度
	   'MEMBER_GRADE'//客户等级
	];
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
					text:'你妹',
					id:'exitApplyButtonId',
					handler:function(){
					
					}
				}]
			},{
				xtype : 'middlebuttonpanel'
			},{
				xtype:'rightbuttonpanel',
				items:[{
					xtype:'button',
					text:'你姐',
					handler:function(){
					
					}
				},{
					xtype:'button',
					text:'你姨',
					handler:function(){
					
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
		initComponent : function() {
			var me = this;
			me.columns = me.getColumns();
			me.selModel = me.getSelModel();
			me.store = me.getGridStore();
			me.bbar = me.getBBar();
			this.callParent();
//			me.store.on('beforeload',function(store, operation, eOpts){
//				var form = Ext.getCmp('SearchForm').getForm();
//				var params = KcUtil.transform(form,'out');
//				params['condition.start'] = store.currentPage*store.pageSize-store.pageSize;
//				params['condition.limit'] = store.pageSize;
//				Ext.apply(operation,{
//					params : params
//				});
//			});
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
				dataIndex:'cadreName'
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
				header:'流失客户数',
				width:120,
				align:'center',
				dataIndex:'lostCust'
			},{
				header:'客户总数',
				width:120,
				align:'center',
				dataIndex:'totalCustMonthBeforeLastSend'
			},{
				header:'流失率',
				width:120,
				align:'center',
				dataIndex:'lostPercentage'
			},{
				header:'零担类流失客户数',
				width:120,
				align:'center',
				dataIndex:'lTTLostCust'
			},{
				header:'零担类客户总数',
				width:120,
				align:'center',
				dataIndex:'lTTCustMonthBeforeLastSend'
			},{
				header:'零担类客户流失率',
				width:120,
				align:'center',
				dataIndex:'lTTLostPercentage'
			},{
				header:'快递类流失客户数',
				width:120,
				align:'center',
				dataIndex:'expressLostCust'
			},{
				header:'快递类客户总数',
				width:120,
				align:'center',
				dataIndex:'expressCustMonthBeforeLastSend'
			},{
				header:'快递类客户流失率',
				width:120,
				align:'center',
				dataIndex:'expressLostPercentage'
			}]
		},
		//获取选择框
		getSelModel:function() {
			return  Ext.create('Ext.selection.CheckboxModel', {
				mode:'SINGLE'
			})
		},
		//获取数据
		getGridStore:function(){
			return null;
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
		formType:'month'
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