
/**
 * 基础层级模块 声明
 */
Ext.define('BaseHierarchyROPanel',{
	extend:'BasicPanel',border:true
	,complaint:null // 工单数据
	,layout: {align:'middle',pack:'center',type:'hbox'}
	,defaultType:'textfield',defaults:{width:230,labelAlign:'left',labelWidth:70, readOnly:true}
	//,businessScopeStore:null // 业务范围 store
	//,businessTypeStore:null // 业务范类型store
    ,initComponent : function() {
		var me = this;
		//this.businessScopeStore = Ext.create('BusinessScopeStore',{autoLoad:true});
		//this.businessTypeStore = Ext.create('BusinessTypeStore',{});
		
		Ext.applyIf(me,{
    		items:[
    			{
		        	fieldLabel:'业务范围',id:'form_paraBasciLevel'
		        	,scope:me
		        	//,store:me.businessScopeStore
		        	//,queryMode: 'local'
		        	//,displayField:'bascilevelname',valueField:'fid'
		        	//,enableKeyEvents:true
		        	//,listeners:{
		        	//	scope:me,select:me.searchBusinessTypeEvent
		        	//}
		        }
		        ,{
		        	fieldLabel:'业务类型',id:'form_basciLevel'
		        	//,store:me.businessTypeStore
		        	//,queryMode: 'local',displayField:'bascilevelname',valueField:'fid'
		        }
		        ,{
		        	fieldLabel:'投诉级别',id:'form_complevel'
		        	//调用了DataDictionary.js里面的根据名称得到对应数据字典数据的方法
					//,store:getDataDictionaryByName(DataDictionary,'COMPLAINT_LEVEL')
					//,queryMode: 'local',displayField:'codeDesc',valueField:'code'
		        }
    		]
    	});
		this.callParent(arguments);
		if(!DpUtil.isEmpty(me.complaint)){
			Ext.getCmp('form_paraBasciLevel').setValue(me.complaint['paraBasciLevel']);
			Ext.getCmp('form_basciLevel').setValue(me.complaint['basciLevel']);
			Ext.getCmp('form_complevel').setValue(me.complaint['complevel']);
		}
	}

});
