Ext.QuickTips.init();
/**
 * 处理记录 panel
 */
Ext.define('ProcessRecordPanel',{
	extend:'BasicVboxPanel'
	,height:145,complaint:null
	,processRecordGrid:null //处理记录列表
	,layout:'fit'
	,initComponent : function() {
		var me = this;
		me.processRecordGrid = Ext.create('ProcessRecordGrid',{'complaint':me.complaint});
		me.items = [me.processRecordGrid];
		me.callParent(arguments);
	}
});

/**
 * 客户工单之 处理记录列表
 */
Ext.define('ProcessRecordGrid',{
	extend:'PopupGridPanel'
	,height:145,title:i18n('i18n.complaint.process_record.title')
	,plugins:Ext.create('Ext.grid.plugin.CellEditing',{clicksToEdit:1})
	,complaint:null   // 工单信息
	,initComponent:function(){
		var me = this;
        this.tbar = [
                 {
                    text: i18n('i18n.complaint.processRecord.allRecord'),
                    handler: function(){
			            me.store.clearFilter(false);
                    }
                },{
                    text: i18n('i18n.complaint.processRecord.returnRecordApproval'),
                    handler:function(){me.filterField('recordtype','RECORD_RETURN_APPROVAL');}
                },{
                    text: i18n('i18n.complaint.processRecord.returnRecordUpgraded'),
                    handler:function(){me.filterField('recordtype','RECORD_RETURN_UPGRADED');}
                },{
                    text: i18n('i18n.complaint.processRecord.returnRecordReportor'),
                    handler:function(){me.filterField('recordtype','RECORD_RETURN_REPORTOR');}
                },
                {
                    text: i18n('i18n.complaint.processRecord.feedbackRecord'),
                    handler: function(){me.filterField('recordtype','RECORD_FEEDBACK');}
                }
            ];		
		Ext.applyIf(me,{
			'store':Ext.create('ProcessRecordStore',{autoLoad:false})
		});
		if(!DpUtil.isEmpty(me.complaint)){
			me.store.on('beforeload',function(store, operation, eOpts){
				var searchParams = {
					'complaintSearchCondition.fid':me.complaint.fid
				};
				Ext.apply(operation,{params : searchParams});
			});
			me.store.load();
		}
		me.columns = me.getColumns();
		this.callParent(arguments);
	}
	,getColumns:function(){
		var me = this;
		return [
			{xtype:'rownumberer',header:i18n('i18n.complaint.serial_number'),width:40},
			{header:i18n('i18n.complaint.process_record.wailbillNunber'),minWidth:100,dataIndex:'wailbillnunber',hidden:true}
			,{header:i18n('i18n.complaint.process_record.recordType'),minWidth:100,dataIndex:'recordtype',
				renderer:function(value){
					return rendererDictionary(value,DataDictionary.COMPLAINT_RECORD_TYPE);
				}
			}
			,{header:i18n('i18n.complaint.process_record.wailbillContent'),width:70,dataIndex:'revisitresult',
				renderer:function(value){
					return rendererDictionary(value,DataDictionary.RESOLVE_CASE);
				}
			}
			,{header:i18n('i18n.complaint.process_record.revisitresult'),flex:1,dataIndex:'wailbillcontent',renderer:function(val){
				return '<span data-qtip="'+val+'">'+val+'</span>';
			}
			}
			,{header:i18n('i18n.complaint.process_record.custsatisfy'),minWidth:100,dataIndex:'custsatisfy',
				renderer:function(value){
					return rendererDictionary(value,DataDictionary.SATISFACTION_DEGREE);
				}			
			}
			,{header:i18n('i18n.complaint.process_record.recordman'),width:60,dataIndex:'recordman'}
			,{header:i18n('i18n.complaint.process_record.recordpartmentname'),width:120,dataIndex:'recordpartmentname',renderer:function(val){
				return '<span data-qtip="'+val+'">'+val+'</span>';
			}}
			,{
				header:i18n('i18n.complaint.process_record.recordtime'),width:140,dataIndex:'recordtime'
				,renderer:function(value) {
					if(value != null){return Ext.Date.format(new Date(value),'Y-m-d H:i');
					}else{return null;}
				}
			}
		];
	},
	filterField:function(field,fieldValue){
		var me = this;
		var filters = [];
	    filters.push({ property: field, value: fieldValue,anyMatch:true });
		me.store.clearFilter(true);
		me.store.filter([{filterFn: function(item) { return item.get(field) == fieldValue; }}]);
	}
});