(function(){
	var keys=[
		'COMPLAINT_LEVEL',/*投诉级别*/
		'COMPLAINT_RECORD_TYPE',/*类型*/
		'COMPLAINT_LINK'/*业务环节*/
	];
	//初始数据字典
	initDataDictionary(keys);
})();
/**
 * 调查建议结果表格
 */
Ext.define('ComplaintDetailSuggestGrid',{
	extend:'PopupGridPanel',
	title:i18n('i18n.ComplaintDetailView.surveySuggests'),
	defaults:{
		align:'center'
	},
	autoScroll:true,
	initComponent:function(){ 
		var me = this;
		var store = Ext.create('ComplaintDetailSuggestStore',{id:'ComplaintDetailSuggestStoreId'});
//		store.on('beforeload',function(store,operation,e){					
//		  
//			
//		});		
		me.store = store;
		me.columns = [
		    {//序号
		    	xtype:'rownumberer',
				header:i18n('i18n.Duty.resultDutyPanel.rownumberer'),
				width:40
		    },{//内容
				header:i18n('i18n.ComplaintDetailView.content'),
				flex:1,
				dataIndex:'suggestion'
		    },{ //操作人
				header :i18n('i18n.CommonView.operator'),
				dataIndex:'operatorName',
				width:100
		    },{//操作时间
				header :i18n('i18n.CommonView.operatingTime'),
				dataIndex:'operatorTime',
				renderer:DutyUtil.renderDateTime,
				width:120
		    }
	    ];
    this.callParent();
   }
});


/**
 * 处理结果列表
 */
Ext.define('ComplaintDetailDealResultGrid',{
	extend:'PopupGridPanel',
	title:i18n('i18n.ComplaintDetailView.processingResults'),
	defaults:{
		align:'center'
	},
	autoScroll:true,
	initComponent:function(){ 
		var me = this;
		var store = Ext.create('DutyProcessResultDetailStore',{id:'DutyProcessResultDetailStoreId'});
//		store.on('beforeload',function(store,operation,e){					
//			var searchParams = {
//					'complaintSearchCondition.fid':me.complaintId
//				};
//				Ext.apply(operation,{
//					params : searchParams
//				});
//			
//		});		
		me.store = store;
		me.columns = [
		    {//序号
		    	xtype:'rownumberer',
				header:i18n('i18n.Duty.resultDutyPanel.rownumberer'),
				width:40
		    },{//处理事项
				header:i18n('i18n.ComplaintDetailView.mattersDealtWith'),
				width:120,
				dataIndex:'dealmatters'
		    },{ //处理结果
				header :i18n('i18n.ComplaintDetailView.processingResults'),
				dataIndex:'results',
				width:120
		    },{//处理记录
				header :i18n('i18n.ComplaintDetailView.processingRecords'),
				dataIndex:'prorecord',
				flex:1
		    },{//反馈时限
				header:i18n('i18n.ComplaintDetailView.feedbackTimeLimit'),
				width:100,
				dataIndex:'feedtimelimit'
		    },{ //处理时限（小时）
				header :i18n('i18n.ComplaintDetailView.processingTimeHour'),
				dataIndex:'processtimelimit',
				width:120
		    }
	    ];
    this.callParent();
   }
});


/**
 * 操作记录结果列表
 */
Ext.define('ComplaintDetailOpeRecordGrid',{
	extend:'PopupGridPanel',
	title:i18n('i18n.ComplaintDetailView.operationalRecords'),
	defaults:{
		align:'center'
	},
	autoScroll:true,
	initComponent:function(){ 
		var me = this;
		var store = Ext.create('ComplaintDetailOpeRecordStore',{id:'ComplaintDetailOpeRecordStoreId'});
		me.store = store;
		me.columns = [
		    {//序号
		    	xtype:'rownumberer',
				header:i18n('i18n.Duty.resultDutyPanel.rownumberer'),
				width:40
		    },{//类型
				header:i18n('i18n.duty.result.dealType'),
				width:100,
				dataIndex:'recordtype',
				renderer:function(value){
					return DutyUtil.rendererDictionary(value,DataDictionary.COMPLAINT_RECORD_TYPE);
				}
		    },{ //解决情况
				header :i18n('i18n.ComplaintDetailView.solveCase'),
				dataIndex:'revisitresult',
				width:120
		    },{//反馈内容
				header :i18n('i18n.ComplaintDetailView.contentFeedback'),
				dataIndex:'wailbillcontent',
				flex:1
		    },{//客户满意度
				header:i18n('i18n.ComplaintDetailView.customerSatisfaction'),
				width:120,
				dataIndex:'custsatisfy'
		    },{ //反馈人
				header :i18n('i18n.Duty.DutyApproval.FeedbackManName'),
				dataIndex:'recordman',
				width:100
		    },{//组织
				header:i18n('i18n.ComplaintDetailView.organization'),
				width:100,
				dataIndex:'recordpartermentid'
		    },{ //时间
				header :i18n('i18n.ComplaintDetailView.time'),
				renderer:DutyUtil.renderDateTime,
				dataIndex:'recordtime',
				width:120
		    }
	    ];
    this.callParent();
   }
});

/**
 * 通知对象panel
 */
Ext.define('ComplaintDetailInforToPanel',{
	extend:'SimpSearchPanel', 
	items:null,
	border:0,
	layout:{
		type:'table',
		columns:2
	},
	defaults:{
		labelWidth:70,
		labelAlign:'right',
		margin:'5 5 0 0'
	},
	initComponent:function(){			
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [
		    {//通知对象
				xtype:'readonlytextarea',
				fieldLabel:i18n('i18n.DutyAllocationOperationView.infoemUser'),
				width:680,
				name:'tongZhiDuiXaingId',
				height:50,
				id:'tongZhiDuiXaingId'
			},{//最终反馈
				xtype:'checkbox',
				boxLabel:i18n('i18n.ComplaintDetailView.finalFeedback'),
				name:'zuiZhongFanKuiId',
				readOnly:true,
				width:100,
				id:'zuiZhongFanKuiId'
			}
	    ];
	}
});
/**
 * 工单详情-业务范围
 */
Ext.define('ComplaintDetailBusinessDetailPanel',{
	extend:'SearchFormPanel', 
	items:null,
	border:0,
	layout:{
		type:'table',
		columns:3
	},
	defaults:{
		labelWidth:70,
		labelAlign:'right',
		readOnly:true,
		cls:'readonly',
		margin:'5 5 0 0'
	},
	initComponent:function(){			
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [
		     {//业务项
				xtype:'textfield',
				fieldLabel:i18n('i18n.duty.result.busItem'),
				name:'busItemName',
				width:230
			},{//业务范围
				xtype:'textfield',
				fieldLabel:i18n('i18n.duty.busScopeName'),
				name:'busScopeName',
				width:230
			},{//业务类型
				xtype:'textfield',
				fieldLabel:i18n('i18n.duty.result.busType'),
				name:'busTypeName',
				width:230
			},{//业务场景
				xtype:'textfield',
				fieldLabel:'业务场景',
				name:'busSceneName',
				width:230
			},{//场景原因
				xtype:'textfield',
				fieldLabel:'场景原因',
				name:'sceneRessonName',
				width:230
			},{//业务环节
				xtype:'combobox',
				fieldLabel:i18n('i18n.duty.result.businessLink'),
				name:'complainLink',
				width:230,
				mode:'local',
				displayField:'codeDesc',valueField:'code',forceSelection:true,
				store:getDataDictionaryByName(DataDictionary,'COMPLAINT_LINK')
			},{//投诉级别
				xtype:'combobox',
				fieldLabel:i18n('i18n.ComplaintDetailView.complaintsLevel'),
				name:'complevel',
				width:230,
				mode:'local',
				triggerAction:'all',forceSelection:true,
				editable:false,/*不能编辑*/displayField:'codeDesc',valueField:'code',
				store:getDataDictionaryByName(DataDictionary,'COMPLAINT_LEVEL')
			}
	    ];
	}
});
/**
 * 工单详情页面整体布局panel
 */
Ext.define('DutyComplaintDetailPanel', {
	extend : 'BasicPanel',
	layout : 'border',
	items :[
	  {//工单详情基本工单信息
		xtype:'container',
		region:'north',
		layout:'fit',
		items:[{
			xtype:'container',
			region:'north',
			layout:'fit',
			items:[Ext.create('DutyComplaintDetailBasicInfoPanel',{id:'dutyDetailBasicInfoPanelId'})]	
		}]
	  },{
		xtype:'container',
		layout:'border',
		title:'',
		region:'center',
		items:[
		  {
			//调查建议
			xtype:'container',//container可以去除panel的边框
			layout:'fit',
			region:'north',
			height:120,
			items:[Ext.create('ComplaintDetailSuggestGrid',{id:'complaintDetailSuggestGridId'})]	  
		  },{
			xtype : 'container',
			layout:'border',
			region:'center',
			items:[
		       {//级联菜单
		    	 xtype : 'container',
				 layout:'fit',
				 region:'north',
				 items:[Ext.create('ComplaintDetailBusinessDetailPanel',{id:'complaintDetailBusinessPanelId'})]
		       },{//处理结果列表
	    	    xtype : 'container',
				layout:'fit',
				region:'center',
				height:140,
				items:[Ext.create('ComplaintDetailDealResultGrid',{id:'complaintDetailDealResultGridId'})]
		       }
			]
		  },{
				//操作记录列表
				xtype:'container',//container可以去除panel的边框
				layout:'fit',
				height:140,
				region:'south',
				items:[Ext.create('ComplaintDetailOpeRecordGrid',{id:'complaintDetailOpeRecordGridId'})]	  
		  }      
		]
	  },{//通知对象
  	    xtype : 'container',
		layout:'fit',
		region:'south',
		items:[Ext.create('ComplaintDetailInforToPanel',{id:'complaintDetailInforToPanelId'})]
       }
	]
});	


/**
 * 单击查看工单详情按钮，弹出的工单详情窗口
 * xiaohongye
*/
Ext.define('DutyComplaintDetailWindow',{
	extend:'PopWindow',
	title:i18n('i18n.ComplaintDetailView.complaintDetails'),
	alias : 'widget.basicwindow',
	width:810,
	height:800,
	modal:true,
	layout:'fit',
	closeAction:'hide',
	items:[Ext.create('DutyComplaintDetailPanel',{'id':'dutyComplaintDetailPanelId'})],
	listeners:{
		hide:function(){
			document.body.scrollLeft=0;
			document.body.scrollTop=0;
			document.getElementsByTagName("html")[0].style.overflowY="hidden";
			document.getElementsByTagName("html")[0].style.overflowX="hidden";
		}
	},
	buttons: [{//关闭
			xtype:'button',
			text:i18n('i18n.Duty.DutyApproval.Close'),
			handler:function(){
				dutyComplaintDetailWindow.close();
			}
	}]
});

var dutyComplaintDetailWindow = Ext.getCmp("dutyComplaintDetailWindowId");//获取工单详情窗口
if(!dutyComplaintDetailWindow){
	dutyComplaintDetailWindow = Ext.create('DutyComplaintDetailWindow',{id:'dutyComplaintDetailWindowId'});
}
	
	

