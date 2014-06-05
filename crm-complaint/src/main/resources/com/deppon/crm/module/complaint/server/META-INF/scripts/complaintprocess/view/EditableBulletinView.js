
/**
 * 编辑通报对象 panel
 */
Ext.define('EditableBulletinPanel',{
	extend:'BasicVboxPanel'
	,isAutoLoad:false  // 是否自动加载数据
	,complaint:null    // 工单信息
	,editableBulletinGrid:null //通知对象列表
	,initComponent : function() {
		var me = this;
		this.editableBulletinGrid = Ext.create('EditableBulletinGrid',{
			'isAutoLoad':me.isAutoLoad,complaint:me.complaint
		});
		this.items = this.getItems();
		this.callParent(arguments);
	}
	,getItems : function() {
		var me = this;
		return [
		{ 
			xtype:'toppanel',   
			items:[
				{xtype:'titlepanel',flex:1,items:[{xtype:'displayfield',value:i18n('i18n.complaint.comp_bulletin')}]}
				,{
					xtype:'btnpanel',defaultType:'button',items:[
						{scope:me,text:i18n('i18n.complaint.btn.add'),handler:me.addRowEvent}
						,{scope:me,text:i18n('i18n.complaint.btn.delete'),handler:me.deleteRowEvent}
					]
				}
			]
		}
		,{
			xtype:'basicpanel',flex:1
			,items:[me.editableBulletinGrid]
		}
	  ];
	}
	,is_existBulletin:function(my_record,dealType){
		var me = this;
		var bulletinStore = me.editableBulletinGrid.getStore();
		var flag = false;
		bulletinStore.each(function(record){
			if(
				record.get('bulletinid')== my_record.get('empId')
				&& record.get('dealType') == dealType
			){
				record.set('is_manual_add',1);/*表示为：手动添加*/
				flag = true;return false;	
			}
		});
		return flag;
	}
	,addRowEvent:function(){
		var me = this;
		var bulletinStore = me.editableBulletinGrid.getStore();
		Ext.create('EmployeeLookUpWindow',{
			title:i18n('i18n.comp.Employee.win_title'),
			listeners:{
    			select:function(win,record){
    				var dealType = 'employee';
    				if(!me.is_existBulletin(record,dealType)){
    					bulletinStore.add({
							'bulletinid':record.get('empId')
							,'bulletinJobId':record.get('empCode')
							,'bulletinname':record.get('empName')
							,'position':record.get('position')
							,'dealType':dealType
							,'bulletinTel':record.get('mobilePhone')
							,'bulletinDeptName':record.get('deptName')
							,'is_manual_add':1/*表示为：手动添加*/
							,'jobCode':"" /*手动添加用户，默认无任务Code*/
						});
    				}
	        		win.close();
    			}
    		}
		}).show();
	}
	,deleteRowEvent:function(){
		var me = this;
		var selectionList = me.editableBulletinGrid.getSelectionModel().getSelection();
		if(DpUtil.isEmpty(selectionList)){
			MessageUtil.showMessage(i18n('i18n.comp.Bulletin.msg.no_select'));return;
		}
		var panelStore = me.editableBulletinGrid.getStore();
		for(var i=0;i<selectionList.length;i++){
			panelStore.remove(selectionList[i]);
		}
	}
});

/**
 * 客户工单之 通知对象可编辑列表
 */
Ext.define('EditableBulletinGrid',{
	extend:'PopupGridPanel'
	,isAutoLoad:false  // 是否自动加载数据
	,complaint:null    // 工单信息
	,'columns':[
		{xtype:'rownumberer',header:i18n('i18n.complaint.serial_number'),width:40},
		{header:i18n('i18n.complaint.bulletin.name'),width:140,dataIndex:'bulletinname'}
		,{header:i18n('i18n.complaint.bulletin.position')/*职位*/,width:140,dataIndex:'position'}
		,{header:i18n('i18n.complaint.bulletin.jobId'),width:140,dataIndex:'bulletinJobId'}
		,{header:i18n('i18n.complaint.bulletin.tel'),width:140,dataIndex:'bulletinTel'}
		,{header:i18n('i18n.complaint.bulletin.deptName'),flex:1,dataIndex:'bulletinDeptName'}
	]
	,store:Ext.create('BulletinStore',{autoLoad:false})
	,initComponent : function() {
		var me = this;
		if(DpUtil.isEmpty(me.selModel)){
			me.selModel = new Ext.selection.CheckboxModel();
		}
		if(!DpUtil.isEmpty(me.store)){me.store.removeAll(false);}
		if(!DpUtil.isEmpty(me.complaint) && me.isAutoLoad===true){
			me.store.on('beforeload',function(store, operation, eOpts){
				var searchParams = {
					'complaintSearchCondition.fid':me.complaint.fid
				};
				Ext.apply(operation,{params : searchParams});
			});
			me.store.load();
		}
		this.callParent(arguments);
	}
});

