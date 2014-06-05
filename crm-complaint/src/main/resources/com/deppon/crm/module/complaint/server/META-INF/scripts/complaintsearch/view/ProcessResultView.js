Ext.QuickTips.init();
Ext.define('ProcessResultPanel',{
	extend:'BasicVboxPanel'
	,items : null
	,height:120,complaint:null //
	,isTask:false	//是否是部门工单页面的处理结果
	,isVerifyTask:false		//是否是回访页面的处理结果
	,processResultGrid:null //处理结果列表
	,layout:'fit'
	,initComponent : function() {
		var me = this;
		this.processResultGrid = Ext.create('ProcessResultGrid',{
			'complaint':me.complaint,'isTask':me.isTask,'isVerifyTask':me.isVerifyTask
		});
		me.items = [me.processResultGrid];
		me.callParent(arguments);
	}
});

/**
 * 客户工单之 处理结果列表
 */
Ext.define('ProcessResultGrid',{
	extend:'PopupGridPanel',id:'processResultGridId'
	,height:110,title:i18n('i18n.complaint.process_result.title')
	,complaint:null  //工单数据
	,isTask:false	//是否是部门工单页面的处理结果
	,isVerifyTask:null		//是否是回访页面的处理结果
	,initComponent:function(){
		var me = this;
		me.store = Ext.create('ProcessResultStore',{
			listeners:{
				beforeload:function(store, operation, eOpts){
					var searchParams = {
						'complaintSearchCondition.fid':me.complaint.fid
					};
					Ext.apply(operation,{params : searchParams});
				}
			}
		});
		me.columns = me.getColumns();
		if(me.isTask){
			me.columns=me.getTaskColumns();
		}else if(me.isVerifyTask){
			me.columns=me.getVerifyTaskColumns();
		}
		this.callParent(arguments);
	}
	,getColumns:function(){
		var me = this;
		return [
			{xtype:'rownumberer',header:i18n('i18n.complaint.serial_number'),width:40},
			{header:i18n('i18n.complaint.process_result.fid'),width:140,dataIndex:'fid',hidden:true}
			,{header:i18n('i18n.complaint.process_result.dealmatters'),width:140,dataIndex:'dealmatters',renderer:function(val){
				return '<span data-qtip="'+val+'">'+val+'</span>';
			}}
			,{header:i18n('i18n.complaint.process_result.results'),width:140,dataIndex:'results',renderer:function(val){
				return '<span data-qtip="'+val+'">'+val+'</span>';
			}}
			,{header:i18n('i18n.complaint.process_result.prorecord'),flex:1,dataIndex:'prorecord',renderer:function(val){
				return '<span data-qtip="'+val+'">'+val+'</span>';
			}}
			,{header:'反馈时限',align:'center',width:120,dataIndex:'feedtimelimit',renderer:function(val){
				if(!Ext.isEmpty(val)){
					if(parseInt(val)<60){
						return val+'分钟';
					}else{
						var s=parseInt(parseInt(val)/60);
						var y=parseInt(parseInt(val) % 60);
						return s+'小时'+y+'分钟';
					}
				}
				return val;
			}}
			,{header:i18n('i18n.complaint.process_result.processtimeLimit'),align:'center',width:120,dataIndex:'processtimelimit'}
		];
	}
	,getTaskColumns:function(){
		var me = this;
		return [
			{xtype:'rownumberer',header:i18n('i18n.complaint.serial_number'),width:40},
			{header:i18n('i18n.complaint.process_result.dealmatters'),width:140,dataIndex:'dealmatters',renderer:function(val){
				return '<span data-qtip="'+val+'">'+val+'</span>';
			}}
			,{header:i18n('i18n.complaint.process_result.results'),width:140,dataIndex:'results',renderer:function(val){
				return '<span data-qtip="'+val+'">'+val+'</span>';
			}}
			,{header:i18n('i18n.complaint.process_result.prorecord'),flex:1,dataIndex:'prorecord',renderer:function(val){
				return '<span data-qtip="'+val+'">'+val+'</span>';
			}}
			,{header:i18n('i18n.complaint.process_result.lastfeedtimelimit'),align:'center',width:120,dataIndex:'lastfeedtimelimit',renderer:function(val){
				if(!Ext.isEmpty(val)){
					var v=parseInt(val);
					if(v<60){
						return v+'分钟';
					}else{
						var h=parseInt(v/60);
						var m=parseInt(v%60);
						return h+'小时'+m+'分钟';
					}
				};
				return val;
			}}
			,{header:i18n('i18n.complaint.process_result.lastprocessltimeimit'),align:'center',width:120,dataIndex:'lastprocessltimeimit'}
		];
	}
	,getVerifyTaskColumns:function(){
		var me = this;
		return [
			{xtype:'rownumberer',header:i18n('i18n.complaint.serial_number'),width:40},
			{header:i18n('i18n.complaint.process_result.dealmatters'),width:140,dataIndex:'dealmatters',renderer:function(val){
				return '<span data-qtip="'+val+'">'+val+'</span>';
			}}
			,{header:i18n('i18n.complaint.process_result.results'),width:140,dataIndex:'results',renderer:function(val){
				return '<span data-qtip="'+val+'">'+val+'</span>';
			}}
			,{header:i18n('i18n.complaint.process_result.prorecord'),flex:1,dataIndex:'prorecord',renderer:function(val){
				return '<span data-qtip="'+val+'">'+val+'</span>';
			}}
			,{header:i18n('i18n.complaint.process_result.ifovertime'),align:'center',width:50,dataIndex:'ifovertime',
				renderer:function(value){
					if(null!=value){
						if("0"==value){return "否";
						}else if("1"==value){return "是";}
					}
					return "";
				}
			}
			,{header:i18n('i18n.complaint.process_result.ifmaturity'),align:'center',width:50,dataIndex:'ifmaturity',
				renderer:function(value){
					if(null!=value){
						if("0"==value){return "否";
						}else if("1"==value){return "是";}
					}
					return "";
				}
			}
		];
	}
});