/** 
 * 查询详情弹出框 声明
 */
Ext.define('RepeatComplaintWindow', {
    extend: 'PopWindow'
	,width:820,height:300,x:1,y:5,autoScroll:true
    ,layout:'fit',modal:true
    ,complaint:null //工单信息
    ,repeatComplaintGrid:null //重复工单 grid
	,title:i18n('i18n.complaint.comp_recomcode_title')
    ,initComponent: function() {
        var me = this;
        this.repeatComplaintGrid = Ext.create('RepeatComplaintGrid',{
        	'complaint':me.complaint
        });
        Ext.applyIf(me,{
        	items:[me.repeatComplaintGrid]
        	,buttons:[
        		{text:i18n('i18n.complaintReport.btn_close'),handler:function(){me.close();}}
        	]
        })
        this.callParent(arguments);
    }
});


/**
 * 客户工单 重复工单查询结果
 */
Ext.define('RepeatComplaintGrid',{
	extend:'PopupGridPanel'
	,complaint:null // 工单信息
	,initComponent:function(){
		var me = this;
		me.store = Ext.create('RepeatComplaintStore',{
			listeners:{
				beforeload:function(store, operation, eOpts){
					//如果重复工单码为空，取重复绑定码
					var recomcode=me.complaint['recomcode'];
					/*if(DpUtil.isEmpty(me.complaint['recomcode'])){
						recomcode=me.complaint['rebindno'];
					}*/
					var searchParams = {
						//'complaintSearchCondition.recomcode':recomcode
						'complaintSearchCondition.rebindno':recomcode
					};
					Ext.apply(operation,{params:searchParams});	
				}
			}
			
		});
		me.dockedItems = me.getMyDockedItems();
		me.columns = me.getColumns();
		me.callParent(arguments);
		me.on('cellclick', function(thiz, row, col, record) {
			var fieldName=thiz.getHeaderCt().getHeaderAtIndex(col).dataIndex;
			if (fieldName == 'compman') {//显示来电人信息
				show_comp_detailsWin(record.get('fid'));/* 调用工单详情-弹出框 */
			}
		});
	}
	,getColumns:function(){
		var me = this;
		return [
			{xtype:'rownumberer',header:i18n('i18n.complaint.serial_number'),width:40}
			,{header:i18n('i18n.complaint.businessType'),dataIndex:'businessModel',
				renderer:function(value){
					return rendererDictionary(value,DataDictionary.COMPLAINT_BUSINESS_MODEL);
				}
			}
			,{header:i18n('i18n.complaint.comp_bill'),width:100,dataIndex:'bill'}
			,{header:i18n('i18n.complaint.comp_dealbill'),width:120,dataIndex:'dealbill'}
			,{header:i18n('i18n.complaint.comp_recomcode'),width:120,dataIndex:'recomcode'}
			
			,{
				header:i18n('i18n.complaint.comp_reportType'),width:80,dataIndex:'reporttype'
				,renderer:function(value){
					return rendererDictionary(value,DataDictionary.REPORT_TYPE);
				}
			}
			,{header:i18n('i18n.complaint.comp_complaincust'),width:80,dataIndex:'complaincust'}
			,{
				header:i18n('i18n.complaint.comp_compman'),width:50,dataIndex:'compman'
				,renderer:function(value){return '<a href="javascript:;">'+value+'</a>';}
			}
			,{
				header:i18n('i18n.complaint.comp_pririty'),width:80,dataIndex:'pririty'
				,renderer:function(value){
					return rendererDictionary(value,DataDictionary.PRIORITY_RATING);
				}
			}
			,{
				header:i18n('i18n.complaint.comp_prostatus'),width:80,dataIndex:'prostatus'
				,renderer:function(value){
					return rendererDictionary(value,DataDictionary.COMPLAINT_PROCESS_STATUS);
				}
			}
			,{header:i18n('i18n.complaint.comp_reporter'),width:80,dataIndex:'reporter'}
			,{
				header:i18n('i18n.complaint.comp_reporttime'),width:120,dataIndex:'reporttime'
				,renderer:function(value) {
					if(value != null){return Ext.Date.format(new Date(value),'Y-m-d H:i');
					}else{return null;}
				}
			}
		];
	}
		
	//分页条
	,getMyDockedItems :function(){
		var me = this;
		return [{
			xtype : 'pagingtoolbar'
			,plugins:[Ext.create('Ext.ux.PageComboResizer')]
			,store : me.store,dock : 'bottom',displayInfo :true
		}];
	}
});