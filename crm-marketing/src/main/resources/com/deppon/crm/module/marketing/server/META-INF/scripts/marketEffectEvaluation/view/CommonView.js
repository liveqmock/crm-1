//初始化登录用户权限信息
createAssessAuth();

/**
 *营销效果评估页面中查询条件输入面板form
 */
Ext.define('EffectEvaluationQueryPanel',{
	extend:'SearchFormPanel', 
	items:null,
	border:0,
	//页面类型，用以定义当前页面是属于开发维护效果评估页面还是维护效果评估页面
	pageOfType:null,
	layout:{
		type:'table',
		columns:3
	},
	defaults:{
		labelWidth:60,
		labelAlign:'right',
		width:250,
		margin:'0 5 5 0'
	},
	defaultType:'combobox',
	initComponent:function(){			
		this.items = this.getItems();
		this.callParent();
		initCombobox();
		//在开发效果评估页面显示业务类别和客户来源查询条件
		if(this.pageOfType === 'DEV'){
			this.getForm().findField('custSource').show();
			this.getForm().findField('businessType').show();
		}
		//在维护效果评估页面隐藏业务类别和客户来源查询条件
		else{
			this.getForm().findField('custSource').hide();
			this.getForm().findField('businessType').hide();
		}
	},
//	listeners:{
//		afterrender:function(th){
//			alert(this.pageOfType+'xixixi');
//		}
//	},
	getItems:function(){
		var me = this;
		return [
		    {//经营本部
		    	fieldLabel : i18n('i18n.marketEffectEvaluation.manageDept'),
		    	store: Ext.create('ManageDeptStore'),
		    	queryMode:'local',
		    	forceSelection :true,
		    	displayField:'deptName',
				valueField:'id',
				name:'manageDept',
				listeners:{
					select:function(th,records,e){
						Ext.getCmp("businessDept").store.load({params:{'parentDeptId':th.getValue()}});
						Ext.getCmp("bigDept").store.removeAll();
						Ext.getCmp("smallDept").store.removeAll();
						Ext.getCmp("dept").store.removeAll();
						
						Ext.getCmp("bigDept").setValue('');
						Ext.getCmp("smallDept").setValue('');
						Ext.getCmp("dept").setValue('');
					},
					change:function(th,newValue,oldValue){
						DButil.comboSelsct(th);
						if(Ext.isEmpty(newValue)){
							Ext.getCmp("businessDept").store.removeAll();
							Ext.getCmp("bigDept").store.removeAll();
							Ext.getCmp("smallDept").store.removeAll();
							Ext.getCmp("dept").store.removeAll();
							
							Ext.getCmp("businessDept").setValue('');
							Ext.getCmp("bigDept").setValue('');
							Ext.getCmp("smallDept").setValue('');
							Ext.getCmp("dept").setValue('');
						}
					}
				},
				id:'manageDept'
		    },{//事业部
		    	fieldLabel:i18n('i18n.marketEffectEvaluation.businessDept'),
		    	//剔出朦层
    			listConfig: {
      		        loadMask:false
    			},
		    	queryMode:'local',
		    	forceSelection :true,
		    	store:Ext.create('SelectDeptBySuperiorDeptStore'),
		    	displayField:'deptName',
				valueField:'id',
				name:'businessDept',
				id:'businessDept',
				listeners:{
					select:function(th,records,e){
						Ext.getCmp("bigDept").store.load({params:{'parentDeptId':th.getValue()}});
						Ext.getCmp("smallDept").store.removeAll();
						Ext.getCmp("dept").store.removeAll();
						
						Ext.getCmp("smallDept").setValue('');
						Ext.getCmp("dept").setValue('');
					},
					change:function(th,newValue,oldValue){
						DButil.comboSelsct(th);
						if(Ext.isEmpty(newValue)){
							Ext.getCmp("bigDept").store.removeAll();
							Ext.getCmp("smallDept").store.removeAll();
							Ext.getCmp("dept").store.removeAll();
							
							Ext.getCmp("bigDept").setValue('');
							Ext.getCmp("smallDept").setValue('');
							Ext.getCmp("dept").setValue('');
						}
					}
				}
			},{//大区
				fieldLabel:i18n('i18n.marketEffectEvaluation.bigDept'),
				store:Ext.create('SelectDeptBySuperiorDeptStore'),
		    	forceSelection :true,
		    	//剔出朦层
    			listConfig: {
      		        loadMask:false
    			},
		    	queryMode:'local',
		    	displayField:'deptName',
				valueField:'id',
				name:'bigDept',
				id:'bigDept',
				listeners:{
					select:function(th,records,e){
						Ext.getCmp("smallDept").store.load({params:{'parentDeptId':th.getValue()}});
						Ext.getCmp("dept").store.removeAll();

						Ext.getCmp("dept").setValue('');					
					},
					change:function(th,newValue,oldValue){
						DButil.comboSelsct(th);
						if(Ext.isEmpty(newValue)){
							Ext.getCmp("smallDept").store.removeAll();
							Ext.getCmp("dept").store.removeAll();
							
							Ext.getCmp("smallDept").setValue('');
							Ext.getCmp("dept").setValue('');
						}
					}
				}
			},{//小区
				fieldLabel:i18n('i18n.marketEffectEvaluation.smallDept'),
				store:Ext.create('SelectDeptBySuperiorDeptStore'),
				//剔出朦层
    			listConfig: {
      		        loadMask:false
    			},
    			triggerAction : 'all',
		    	forceSelection :true,
		    	hideTrigger:false,
		    	queryMode:'local',
//		    	editable:false,
		    	displayField:'deptName',
				valueField:'id',
				listeners:{
					select:function(th,records,e){
						Ext.getCmp("dept").store.load({params:{'parentDeptId':th.getValue()}});
					},
					change:function(th,newValue,oldValue){
						DButil.comboSelsct(th);
						if(Ext.isEmpty(newValue)){
							Ext.getCmp("dept").store.removeAll();
							Ext.getCmp("dept").setValue('');
						}
					}
				},
				name:'smallDept',
				id:'smallDept'
			},{//营业部
				fieldLabel:i18n('i18n.marketEffectEvaluation.dept'),
				store:Ext.create('SelectDeptBySuperiorDeptStore'),
				//剔出朦层
    			listConfig: {
      		        loadMask:false
    			},
		    	queryMode:'local',
//		    	colspan:2,
		    	forceSelection :true,
		    	hideTrigger:false,
		    	displayField:'deptName',
				valueField:'id',
				name:'dept',
				listeners:{
					change:function(th){
						DButil.comboSelsct(th);
					}
				},
				id:'dept'
			},{//客户来源
				xtype:'combobox',
		    	fieldLabel:i18n('i18n.PotentialCustManagerView.custSource'),
		    	//剔出朦层
				listConfig: {
	  		        loadMask:false
				},
				hidden:true,
		    	name:'custSource',
				queryModel:'local',
				store : Ext.create('Ext.data.Store',{
					fields: [{
				    	name: 'code', 
				    	type: 'string'
				    },{
				    	name: 'codeDesc',  
				    	type: 'string'
				    }],
				    data:[{
				    	 code:'EXPOSANDCONFERENCES',
				    	 codeDesc:'会展'
				    },{
				    	 code:'YELLOW_PAGES',
				    	 codeDesc:'黄页' 
				    },{
				    	 code:'ALIBABA',
				    	 codeDesc:'阿里巴巴' 
				    },{
				    	 code:'DISPATCH_LIST',
				    	 codeDesc:'派单' 
				    },{
				    	 code:'STRANGER_CALLS',
				    	 codeDesc:'陌生来电' 
				    },{
				    	 code:'ORDER_CUSTOMER',
				    	 codeDesc:'订单潜客' 
				    },{
				    	 code:'ONLINEHALL',
				    	 codeDesc:'官方网站' 
				    },{
				    	 code:'CALLCENTER',
				    	 codeDesc:'400电话' 
				    },{
				    	 code:'ARRIVE_CUSTOMER',
				    	 codeDesc:'到达散客' 
				    },{
				    	 code:'OTHER',
				    	 codeDesc:'其他' 
				    }]
               }),
               displayField:'codeDesc',
               valueField:'code',
               forceSelection :true,
               editable:true,
               listeners:{
            	   change:DButil.comboSelsct
               }
			},{//起止时间
		    	xtype:'datefield',
		    	disabled:true,
		    	readOnly:true,
		    	fieldLabel:i18n('i18n.marketEffectEvaluation.startDate'),
		    	format:'Y-m-d',
				name:'startDate',
				id:'startDate',
				//设置起始时间的初始值为当前月份的第一天
				value:Ext.Date.getFirstDateOfMonth(Ext.Date.add(new Date(),Ext.Date.DAY,-1))
			},{//起止时间到
				xtype:'datefield',
		    	fieldLabel:i18n('i18n.marketEffectEvaluation.endDate'),
		    	format:'Y-m-d',
		    	maxValue:Ext.Date.add(new Date(),Ext.Date.DAY,-1),
		    	//为结束时间赋初值
		    	value:Ext.Date.add(new Date(),Ext.Date.DAY,-1),
				name:'endDate',
				id:'endDate',
				listeners:{
					change:function(th,newValue,oldValue){
						if(newValue!==oldValue){
							//如果结束时间改变，起始时间做相应改变
							Ext.getCmp('startDate').setValue(Ext.Date.getFirstDateOfMonth(new Date(newValue)));
						}
					}
				}
			},{//业务类别
				xtype:'combobox',
		    	fieldLabel:i18n('i18n.PotentialCustManagerView.businessType'),
		    	//剔出朦层
				listConfig: {
	  		        loadMask:false
				},
		    	name:'businessType',
				queryModel:'local',
				value:'ALL',
				store : Ext.create('Ext.data.Store',{
					fields: [{
				    	name: 'code', 
				    	type: 'string'
				    },{
				    	name: 'codeDesc',  
				    	type: 'string'
				    }],
				    data:[{
				    	 code:'LTT',
				    	 codeDesc:'零担'
				    },{
				    	 code:'EXPRESS',
				    	 codeDesc:'快递' 
				    },{
				    	 code:'ALL',
				    	 codeDesc:'全部' 
				    }]
               }),
               hidden:true,
               displayField:'codeDesc',
               valueField:'code',
               forceSelection :true,
               editable:false,
               listeners:{
            	   change:DButil.comboSelsct
               }
			}
	    ];
	}	
});

/**
 * initCombobox，根据用户初始化开发和维护查询条件中combobox的默认显示
 */
function initCombobox(){
	//取出查询表单中各个条件
	var manageDept = Ext.getCmp('manageDept');
	var businessDept = Ext.getCmp('businessDept');
	var bigDept = Ext.getCmp('bigDept');
	var smallDept = Ext.getCmp('smallDept');
	var dept = Ext.getCmp('dept');
	//当登录用户具有某个经营本部的查询权限
	if(AssessAuthority.assessAuthority==='400000127'){ 
		manageDept.disable();
		if(!Ext.isEmpty(AssessAuthority.assessDept)){
			var manageDeptModel=new DeptListModel();
			manageDeptModel.set("id",AssessAuthority.assessDept.id);
			manageDeptModel.set("deptName",AssessAuthority.assessDept.deptName);
			var manageDeptStore = manageDept.getStore();
			manageDeptStore.add(manageDeptModel);
			
			manageDept.setValue(AssessAuthority.assessDept.id);
			Ext.getCmp("businessDept").store.load({params:{'parentDeptId':AssessAuthority.assessDept.id}});
			Ext.getCmp("bigDept").store.load({params:{'parentDeptId':''}});
			Ext.getCmp("smallDept").store.load({params:{'parentDeptId':''}});
			Ext.getCmp("dept").store.load({params:{'parentDeptId':''}});
		}
		
	}
	//当登录用户具有某个事业部的查询权限
	else if(AssessAuthority.assessAuthority==='400000146'){
		manageDept.disable();
		businessDept.disable();
		
		if(!Ext.isEmpty(AssessAuthority.assessDept)){
			var manageDeptModel=new DeptListModel();
			manageDeptModel.set("id",AssessAuthority.assessDept.id);
			manageDeptModel.set("deptName",AssessAuthority.assessDept.deptName);
			var manageDeptStore = manageDept.getStore();
			manageDeptStore.add(manageDeptModel);
			
			manageDept.setValue(AssessAuthority.assessDept.id);
			if(!Ext.isEmpty(AssessAuthority.assessDept.childDept)){
				var businessDeptModel=new DeptListModel();
				businessDeptModel.set("id",AssessAuthority.assessDept.childDept.id);
				businessDeptModel.set("deptName",AssessAuthority.assessDept.childDept.deptName);
				var businessDeptStore = businessDept.getStore();
				businessDeptStore.add(businessDeptModel);
				
				businessDept.setValue(AssessAuthority.assessDept.childDept.id);
				businessDept.setRawValue(AssessAuthority.assessDept.childDept.deptName);
				Ext.getCmp("bigDept").store.load({params:{'parentDeptId':AssessAuthority.assessDept.
					childDept.id}});
				Ext.getCmp("smallDept").store.load({params:{'parentDeptId':''}});
				Ext.getCmp("dept").store.load({params:{'parentDeptId':''}});
			}
		}
		
	}
	//当登录用户具有某个大区的查询权限
	else if(AssessAuthority.assessAuthority==='400000145'){
		manageDept.disable();
		businessDept.disable();
		bigDept.disable();
		
		if(!Ext.isEmpty(AssessAuthority.assessDept)){
			var manageDeptModel=new DeptListModel();
			manageDeptModel.set("id",AssessAuthority.assessDept.id);
			manageDeptModel.set("deptName",AssessAuthority.assessDept.deptName);
			var manageDeptStore = manageDept.getStore();
			manageDeptStore.add(manageDeptModel);
			
			manageDept.setValue(AssessAuthority.assessDept.id);
			if(!Ext.isEmpty(AssessAuthority.assessDept.childDept)){
				var businessDeptModel=new DeptListModel();
				businessDeptModel.set("id",AssessAuthority.assessDept.childDept.id);
				businessDeptModel.set("deptName",AssessAuthority.assessDept.childDept.deptName);
				var businessDeptStore = businessDept.getStore();
				businessDeptStore.add(businessDeptModel);
				
				businessDept.setValue(AssessAuthority.assessDept.childDept.id);
				businessDept.setRawValue(AssessAuthority.assessDept.childDept.deptName);
				
				
				if(!Ext.isEmpty(AssessAuthority.assessDept.childDept.childDept)){
					var bigDeptModel=new DeptListModel();
					bigDeptModel.set("id",AssessAuthority.assessDept.childDept.childDept.id);
					bigDeptModel.set("deptName",AssessAuthority.assessDept.childDept.childDept.deptName);
					var bigDeptStore = bigDept.getStore();
					bigDeptStore.add(bigDeptModel);

					bigDept.setValue(AssessAuthority.assessDept.childDept.childDept.id);
					bigDept.setRawValue(AssessAuthority.assessDept.childDept.childDept.deptName);

					Ext.getCmp("smallDept").store.load({params:{'parentDeptId':AssessAuthority.
						assessDept.childDept.childDept.id}});
					Ext.getCmp("dept").store.load({params:{'parentDeptId':''}});
				}
			}
		}		
	}
	//当登录用户具有某个小区的查询权限
	else if(AssessAuthority.assessAuthority==='400000126'){
		manageDept.disable();
		businessDept.disable();
		bigDept.disable();
		smallDept.disable();
		if(!Ext.isEmpty(AssessAuthority.assessDept)){
			var manageDeptModel=new DeptListModel();
			manageDeptModel.set("id",AssessAuthority.assessDept.id);
			manageDeptModel.set("deptName",AssessAuthority.assessDept.deptName);
			var manageDeptStore = manageDept.getStore();
			manageDeptStore.add(manageDeptModel);
			
			manageDept.setValue(AssessAuthority.assessDept.id);
			if(!Ext.isEmpty(AssessAuthority.assessDept.childDept)){
				var businessDeptModel=new DeptListModel();
				businessDeptModel.set("id",AssessAuthority.assessDept.childDept.id);
				businessDeptModel.set("deptName",AssessAuthority.assessDept.childDept.deptName);
				var businessDeptStore = businessDept.getStore();
				businessDeptStore.add(businessDeptModel);
				
				businessDept.setValue(AssessAuthority.assessDept.childDept.id);
				businessDept.setRawValue(AssessAuthority.assessDept.childDept.deptName);
				
				
				if(!Ext.isEmpty(AssessAuthority.assessDept.childDept.childDept)){
					var bigDeptModel=new DeptListModel();
					bigDeptModel.set("id",AssessAuthority.assessDept.childDept.childDept.id);
					bigDeptModel.set("deptName",AssessAuthority.assessDept.childDept.childDept.deptName);
					var bigDeptStore = bigDept.getStore();
					bigDeptStore.add(bigDeptModel);

					bigDept.setValue(AssessAuthority.assessDept.childDept.childDept.id);
					bigDept.setRawValue(AssessAuthority.assessDept.childDept.childDept.deptName);

					if(!Ext.isEmpty(AssessAuthority.assessDept.childDept.childDept.childDept)){
						var smallDeptModel=new DeptListModel();
						smallDeptModel.set("id",AssessAuthority.assessDept.childDept.childDept.childDept.id);
						smallDeptModel.set("deptName",AssessAuthority.assessDept.childDept.childDept.childDept.
								deptName);
						var smallDeptStore = smallDept.getStore();
						smallDeptStore.add(smallDeptModel);					
						
						smallDept.setValue(AssessAuthority.assessDept.childDept.childDept.childDept.id);
						smallDept.setRawValue(AssessAuthority.assessDept.childDept.childDept.childDept.
								deptName);
						Ext.getCmp("dept").store.load({params:{'parentDeptId':AssessAuthority.assessDept.
							childDept.childDept.childDept.id}});
					}
				}
			}
		}
	}
	//当登录用户具有全国的查询权限
	else if(AssessAuthority.assessAuthority==='400000147'){
		manageDept.enable();
		businessDept.enable();
		bigDept.enable();
		smallDept.enable();
		dept.enable();
		Ext.getCmp("manageDept").store.load();	
	}
	//当非法用户进入查询页面
	else{
		manageDept.disable();
		businessDept.disable();
		bigDept.disable();
		smallDept.disable();
		dept.disable();
	}
}