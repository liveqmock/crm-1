

	Ext.onReady(function(){
		/*******************************顶部查询条件的form***************************/
		Ext.define('QueryForm',{
			extend:'SearchFormPanel',
			id:'queryFormId',
			frame:true,
			margin:'3 5 0 5',
			layout:{
				type:'table',
				columns:3
			},
			defaultType:'textfield',
			defaults:{
				width:250,
				labelAlign:'right'	
			},
			items:[{
				name:'triggerName',
				maxLength:100,
				fieldLabel:i18n('i18n.scheduler.triggerName')//触发器名称',
			},
			{
				name:'jobName',
				maxLength:100,
				fieldLabel:i18n('i18n.scheduler.jobName')//任务名称',
			},
			{
				name:'jobGroup',
				maxLength:100,
				fieldLabel:i18n('i18n.scheduler.jobGroup')//任务分组',
			},{
				xtype     : 'datetimefield',
				format: 'Y-m-d H:i:s',
				name      : 'startDate',
				maxValue:new Date(),
				fieldLabel: i18n('i18n.scheduler.searchtime'),//'时间范围',
				value:new Date(new Date().getFullYear(),new Date().getMonth(),new Date().getDate()-31),
				allowBlank:false,
				editable: false
			},
			{	
				xtype     : 'datetimefield',
				format: 'Y-m-d H:i:s',
				name      : 'endDate',
				fieldLabel: i18n('i18n.scheduler.to'),//'到',
				labelSeparator:'',
				maxValue:new Date(),
				value:new Date(),
				allowBlank:false,
				blankText:i18n('i18n.scheduler.dateCanNotNull'),
				editable: false
			}]
		});
		var params;
		var queryLogStore = Ext.create('QueryLogStore',{});
		/****************************中间按钮***************************/
		
		Ext.define('ButtonDemoPanel',{
			extend:'NormalButtonPanel', //--第一步,定义一个主panel,继承NormalButtonPanel
			items:null,
			initComponent:function(){
				this.items = this.getItems();
				this.callParent();
			},
			getItems:function(){
				var me = this;
				return [{
					 //--第二步,定义一个位于左边的按钮容器,继承leftbuttonpanel
				},{
					xtype:'middlebuttonpanel' //--定义一个位于中间的空容器，用于填充								中间空白部分,继承middlebuttonpanel
				},{
						xtype:'rightbuttonpanel', //--定义一个位于右边的按钮容器,继承								rightbuttonpanel
						items:[{
							xtype:'button',    //--向右部的按钮容器中，添加具体的按钮
							name:'serachLogBtn',
							text:i18n('i18n.scheduler.searchBtn'),//'查询',
							handler:function(){
								queryLogStore.load();
								
							}
						},{
							xtype:'button',    //--向右部的按钮容器中，添加具体的按钮
							name:'ResetLogBtn',
							text:i18n('i18n.scheduler.resetBtn'),//'重置',
							handler:function(){}
						}]
				}]
			}
		});
		/*******************************底部gridPanel****************************/
		
		var pluginExpanded = true;
		var logwin;
		var queryFormPanel = Ext.create('QueryForm',{region: 'north'});
		queryForm = queryFormPanel.getForm();
		
		queryLogStore.on('beforeload',function(queryLogStore,operation){
			var  	triggerName	= 	queryForm.findField('triggerName').getValue(),//1.触发器名称
				jobName		= 	queryForm.findField('jobName').getValue(),//2.任务名称
				jobGroup		= 	queryForm.findField('jobGroup').getValue(),//3.任务分组
				startDate		= 	queryForm.findField('startDate').getValue(),//4.开始时间
				endDate			=  	queryForm.findField('endDate').getValue();//5.结束时间
				
			
			Ext.apply(operation,{	
				params : {
					'logSearchCondition.triggerName'	:triggerName,
					'logSearchCondition.jobName'		:jobName,
					'logSearchCondition.jobGroup'		:jobGroup,
					'logSearchCondition.startDate'		:startDate,
					'logSearchCondition.endDate'		:endDate
					
				}
			});
		});
		var gridPanel = Ext.create('SearchGridPanel',{
			id:'buttomGrid',region: 'center',
			store:queryLogStore,
			selModel:Ext.create('Ext.selection.CheckboxModel',{mode:'single'}),//选择框
			columns:[
				
				{text:i18n('i18n.scheduler.sequenceNumber'),xtype:'rownumberer',width:40	},//1.序号
				{text:i18n('i18n.scheduler.schedulerName'),dataIndex:'instanceId',width:100},//2.实例名
				{text:i18n('i18n.scheduler.triggerName'),dataIndex:'triggerName',width:100},//3.触发器名称
				{text:i18n('i18n.scheduler.triggerGroup'),dataIndex:'triggerGroup',width:160	},//4.触发器分组
				{text:i18n('i18n.scheduler.jobName')	,dataIndex:'jobName',width:140},//5.任务名称
				{text:i18n('i18n.scheduler.jobGroup'),dataIndex:'jobGroup',width:80},//6.任务分组
				{	
					text:i18n('i18n.scheduler.starttime'),dataIndex:'firedTime',width:120,
					renderer:function(value){
						if(Ext.isEmpty(value)){return null;}
						return Ext.Date.format(new Date(value),'Y-m-d H:i');
					}
				},//7.开始时间
				{text:i18n('i18n.scheduler.jobstatus'),dataIndex:'jobAction',width:100},//8.任务状态
				{	//9.异常信息
					text:i18n('i18n.scheduler.exceptioninfo'),
					dataIndex:'errorMessage',flex:1,
					renderer:function(value){
						return '<a href="javasctipt:void(0)">'+value+'</a>';
					}
				}
			],
			listeners:{
				'cellclick':function(thiz, row, col, record){
					var fieldName=thiz.getHeaderCt().getHeaderAtIndex(col).dataIndex;
					if(fieldName=='errorMessage' && !DpUtil.isEmpty(record.get('errorMessage'))){
						var form = Ext.create('NoTitleFormPanel',{
							items:[{xtype:'displayfield',name:'errorMessage',labelWidth:0}]
						});
						form.loadRecord(record);
						if(!logwin){
							logwin = Ext.create('PopWindow',{
								title:i18n('i18n.scheduler.title.exception')/*异常信息*/,
								closeAction:'hide',
								width:700,
								height:600,
								layout:'fit',
								items:form,
								buttons:[{text:i18n('i18n.scheduler.close')/*关闭*/,handler:function(){logwin.close();}}]
							});
						}
						logwin.show();
					}
				}
			},
			bbar:Ext.create("Ext.toolbar.Paging", {
				id : "BBar",
				store : queryLogStore,
				displayInfo : true,
				items : ["-", {
					text : i18n("i18n.scheduler.pager_prefixText"),				//'每页'
					xtype : "tbtext"
				}, Ext.create("Ext.form.ComboBox", {
					id:'showNum',
					width : 40,
					value:'30',
					editable:false,
					triggerAction : "all",
					forceSelection : true,
					editable : false,
					name : "comboItem",
					displayField : "value",
					valueField : "value",
					queryMode : "local",
					store :Ext.create('Ext.data.Store',{
						fields:['value'],
						data:[{
							value:'10'
						},{
							value:'20'
						},{
							value:'30'
						},{
							value:'40'
						},{
							value:'50'
						}]
					}),
					listeners : {
						select : {
							scope : this,
							fn : function(m, l) {
								var k = Ext.getCmp("buttomGrid").store.pageSize;
								var n = parseInt(m.value);
								if(k != n) {
									Ext.getCmp("buttomGrid").store.pageSize = n;
									Ext.getCmp("BBar").moveFirst()
								}
							}
						}
					}
				}), {
					text : i18n('i18n.scheduler.ones')			//'条'
					,xtype : "tbtext"
				},"-",{
					xtype : "tbtext",
					text:i18n('i18n.scheduler.totalAmount')
				}]
			})
		});
	/*******************************显示viewport****************************/
		Ext.create('Ext.container.Viewport', {
			width:750,
			layout: 'border',
			border:false,
			items:[queryFormPanel,{
				region: 'center',
				layout :'border',
				margin:'0 5 5 5',
				border:false,
				items:[
				Ext.create('ButtonDemoPanel',{region: 'north',height:38}),gridPanel]
			}]
		});
	});
