/**
 * 始发网点与受理部门基础资料的model
 * 对应LadingstationDepartment对象
 * T_REL_LADINGSTATION_DEPARTMENT表
 */
Ext.define('LadingStatDepartModel',{
	extend:'Ext.data.Model',
	fields:[
	      {name:'id',type:'string'}/*逻辑主键*/   
	     ,{name:'ifReceive',type:'string'}/*是否接货*/
	     ,{name:'resource',type:'String'}/*订单来源*/
	     ,{name:'beginDepName',type:'String'}/*始发部门名称*/
	     ,{name:'acceptDepName',type:'String'}/*受理部门名称*/
	     ,{name:'beginLadingDeptN'}/*始发部门对象*/
	     ,{name:'acceptDeptN'}/*受理部门对象*/
	     ,{name:'createDate'}/*创建日期*/
	     ,{name:'createUserName'}/*创建人*/
	     ,{name:'modifyDate'}/*修改日期*/
	     ,{name:'modifyUserName'}/*修改人*/
	   ]
});

/**
 *  查询所有始发网点与受理部门基础资料的store
 */
Ext.define('LadingStatDepartStore',{
	extend:'Ext.data.Store'
	,model:'LadingStatDepartModel'
	,autoLoad:true
	,pageSize:25
	,proxy:{
		 type:'ajax'
		,api:{read:'searchLadingStatDeptByCondition.action'}
		,actionMethods:'POST'
		,reader:{
			 type:'json'
			,root:'landStatDepartList'
			,totalProperty:'totalCount'
		}
	}
});


/**
 * Department对应的model，用于始发网点
 */
Ext.define('DepartmentModel',{
	extend:'Ext.data.Model',
	fields:[
	     {name:'id',type:'String'}/*逻辑主键*/
	     ,{name:'deptCode',type:'String'}/*部门编号*/
	     ,{name:'deptName',type:'String'}/*部门名称*/
	     ,{name:'principal',type:'String'}/*部门负责人*/
	     ,{name:'phone',type:'String'}/*联系电话*/
	     ,{name:'fax',type:'String'}/*传真*/
	     ,{name:'parentCode'}/*上级部门实体*/
	     ,{name:'companyName',type:'String'}/*所属子公司*/
	     ,{name:'zipCode',type:'String'}/*邮政号码*/
	     ,{name:'address',type:'String'}/*部门地址*/
	     ,{name:'status',type:'Boolean'}/*状态*/
	     ,{name:'displayOrder',type:'Number'}/*显示顺序*/
	     ,{name:'standardCode',type:'String'}/*标杆编码*/
	   ]
});


/**
 *  始发网点的store
 */

Ext.define('StartNetStore',{
	 extend:'Ext.data.Store'
	,model:'DepartmentModel'
	,proxy:{
		type:'ajax'
		,url:'searchStartNetByDeptName.action'
		,actionMethods:'POST'
		,reader:{
			type:'json'
			,root:'startNetList'
		}
	}

});


/**
 *  
 *  订单来源的store
 *  orderSourceList
 */
Ext.define('LDOrderSourceStore',{
	extend:'Ext.data.Store'
	,fields: [
		{name:'code',type:'number',defaultValue:0}/*数据库存入后台的订单来源*/
		,{name:'codeDesc',type:'String'}/*显示的订单来源*/
	]
	,proxy:{
		type:'ajax'
		,url:'searchLDOrderSource.action'
		,actionMethods:'POST'
		,reader:{
			type:'json'
			,root:'orderSourceList'
		}
	}
});


/**
 *  
 *  是否接货的store
 *  orderSourceList
 */
Ext.define('IfReceiveStore',{
	extend:'Ext.data.Store'
	,fields: [
		{name:'code',type:'number',defaultValue:0}/*数据库存入后台的订单来源*/
		,{name:'codeDesc',type:'String'}/*显示的订单来源*/
	]
	,proxy:{
		type:'ajax'
		,url:'searchIfReceive.action'
		,actionMethods:'POST'
		,reader:{
			type:'json'
			,root:'receiveList'
		}
	}
});



/**
 * 始发网点与受理部门基础资料新增panel
 */
Ext.define('LadingStatDepartCrePanel',{
	extend:'BasicPanel'
//	,height:205
	,items:null
	,bussinessDeptStore:null    //业务部门的store(始发网点和受理部门)
	,acceptDeptStore:null
	,ifReceiveStore:null        //是否接货
	,orderResourceStore:null    //订单来源store
	,ladingStationDept:null     //传入的ladingStationDepartment对象,编辑的时候会传入
	,initComponent:function(){
		var me = this;
		
		this.bussinessDeptStore=Ext.create('StartNetStore'
				,{ autoLoad:false
				  ,listeners:{
					beforeload:function(store, operation, eOpts){
						Ext.apply(operation,{
							params : {
										'startNetDeptName':Ext.getCmp('startNet').getRawValue()
									 }
						});	
					}
				  }		
				});

		this.acceptDeptStore=Ext.create('StartNetStore'
				,{ autoLoad:false
				  ,listeners:{
					  beforeload:function(store, operation, eOpts){
						  Ext.apply(operation,{
							  params : {
								  		'startNetDeptName':Ext.getCmp('acceptDept').getRawValue()
							  		   }
						  });	
					  }				
				  }		
				});		
	
		me.items = me.getItems();		
		
		this.callParent(arguments);
		
		//如果是编辑，则装填数据
		if(me.ladingStationDept!=null){
			me.initEditData();
		}
		
	}
	,getItems:function(){
		var me = this;
		return [
				{
					xtype:'basicformpanel', 
					layout:{
						type:'table',
						columns:2
					},
					defaultType:'combobox',
					defaults:{
						labelWidth:70,
						width:250
					},
		        
		        items:[
			   			//1
						 { fieldLabel:i18n('i18n.ladingstation.beginDept')
						  ,id:'startNet'
						  ,xtype: 'combo'
						  ,allowBlank : false
						  ,store:me.bussinessDeptStore
						  ,displayField:'deptName'
						  ,valueField:'id'
						  ,queryParam:'startNetDeptName'
						  ,typeAhead: false
						  ,hideTrigger:false 
						  ,minChars:1
						 }
						 
						,{ fieldLabel:i18n('i18n.ladingstation.ifReceive')
						  ,id:'ifReceived'
						  ,scope:me
						  ,store:getDataDictionaryByName(DataDictionary,'CONFIG_ORDER_RECEIVE_GOODS')
						  ,queryMode: 'local'
						  ,displayField:'codeDesc'
						  ,valueField:'code'

						  }
						
						
						//2
						,{ fieldLabel:i18n('i18n.ladingstation.orderSource')
						  ,id:'orderResource'
						  ,scope:me
						  ,store:getDataDictionaryByName(DataDictionary,'CONFIG_ORDER_SOURCE')
						  ,queryMode: 'local'
						  ,displayField:'codeDesc'
						  ,valueField:'code'							
						  }
						
						,{ fieldLabel:i18n('i18n.ladingstation.acceptDept')
						  ,id:'acceptDept'
						  ,xtype: 'combo'
						  ,store:me.acceptDeptStore
						  ,displayField:'deptName'
						  ,valueField:'id'
						  ,typeAhead: false
						  ,hideTrigger:false 
						  ,minChars:1
						  ,allowBlank : false
						  ,autoSelect:true
						 }		               
		               
		               
		               ]
				}
		];
	}
	,initEditData:function(){
		//若是有ladingStationDepartment对象传入且有关联的始发以及受理部门等内容，则把值set进去
		var me=this;
		if(me.ladingStationDept!=null&&me.ladingStationDept.acceptDeptN!=null){
			var acceptDept=me.ladingStationDept.acceptDeptN;
			var acceptDeptModel=new DepartmentModel(
					{
						 id:acceptDept.id
						,deptName:acceptDept.deptName			
					});
			me.acceptDeptStore.add(acceptDeptModel);
			Ext.getCmp('acceptDept').setValue(acceptDept.id);			
		}
		
		if(me.ladingStationDept!=null&&me.ladingStationDept.beginLadingDeptN!=null){		
			var startNet=me.ladingStationDept.beginLadingDeptN;
			var startNetModel=new DepartmentModel({
							 id:startNet.id
							,deptName:startNet.deptName
							});
			me.bussinessDeptStore.add(startNetModel);
			Ext.getCmp('startNet').setValue(startNet.id);	
		}
		
		if(me.ladingStationDept!=null&&me.ladingStationDept.ifReceive!=null){
			Ext.getCmp('ifReceived').setValue(me.ladingStationDept.ifReceive);
		}
		
		if(me.ladingStationDept!=null&&me.ladingStationDept.resource!=null){
			Ext.getCmp('orderResource').setValue(me.ladingStationDept.resource);
		}		
			
	}
	
});


