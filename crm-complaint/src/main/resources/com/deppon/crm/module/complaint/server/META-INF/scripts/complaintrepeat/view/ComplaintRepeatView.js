/**
 * 查询重复工单弹出框 声明
 */
Ext.define('ComplaintRepeatWindow',{
	extend:'Ext.window.Window',
	'parent':null,'complaintData':null,
	complaintRepeatGridPanel:null,/* 显示 重复工单 GridPanel */
	width:900,height:650,
    layout: {align: 'stretch',type:'hbox'},
	title:'重复工单列表页'
	,items:null,
	initComponent:function(){
		var me = this;
		var record = Ext.create('ComplaintResultModel');
		
		this.complaintRepeatGridPanel = Ext.create('ComplaintRepeatGridPanel',{
			'parent':me,'complaintData':me.complaintData,'record':record
		});
		this.items = [this.complaintRepeatGridPanel];
		this.callParent();
		me.complaintLookFormPanel.getForm().loadRecord(new ComplaintResultModel(me.conplaint));
	}
});


/**
 * 重复工单查询结果 列表
 */
Ext.define('ComplaintRepeatGridPanel',{
	extend:'SearchGridPanel',
	id:'complaintRepeatGridId',
	parent:null,flex:1,
	complaintData:null,//数据Data
	initComponent:function(){
		var me = this;
		me.columns = me.getColumns();
		me.store = me.complaintData.initComplaintSearchStore(me.beforeLoadScatterFn);
		this.callParent();
	},
	getColumns:function(){
		var me = this;
		return [
			{header:'序号',dataIndex:'fid'},
			{header:i18n('i18n.complaint.businessType'),dataIndex:'businessModel',
				renderer:function(value){
					return rendererDictionary(value,DataDictionary.COMPLAINT_BUSINESS_MODEL);
				}
			}
			,{header:'来电人',align:'center',width:70,dataIndex:'bill'}
			,{header:'凭证号',align:'center',width:80,dataIndex:'dealbill'}
			,{header:'处理编号',align:'center',width:90,dataIndex:'recomcode'}
			,{header:'重复工单码',align:'center',width:80,dataIndex:'reporttype'}
			,{header:'上报类型',align:'center',width:80,dataIndex:'compman'}/*不确定*/
			,{header:'来电客户',align:'center',width:80,dataIndex:'pririty'}
			,{header:'优先级别',align:'center',width:80,dataIndex:'complevel'}
			,{header:'处理状态',align:'center',width:80,dataIndex:'prostatus'}
			,{header:'上报人',align:'center',width:80,dataIndex:'feedbacktime'}/*没有*/
			,{header:'上报时间',align:'center',width:80,dataIndex:'timelimit'}/*不确定*/
		];
	},
	//beforeLoad方法
	beforeLoadScatterFn:function(store, operation, eOpts){
		var searchConditionForm = Ext.getCmp('complaintConditionFormId');
		if(searchConditionForm!=null){
			searchConditionForm.getForm().updateRecord(searchConditionForm.record);
			//设置请求参数
			var searchCustCondition = searchConditionForm.record.data;
			
			var searchParams = {};
			searchParams['complaintSearchCondition.'+searchCustCondition.fieldName]
			= searchCustCondition.fieldValue;
			
			Ext.apply(operation,{
					params : searchParams
				}
			);	
		}
	}
});