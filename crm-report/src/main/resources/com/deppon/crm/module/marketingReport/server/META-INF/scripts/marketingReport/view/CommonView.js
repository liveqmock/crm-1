//初始化登录用户权限信息
createAssessAuth();
/**
 *营销效果评估页面中查询条件输入面板form
 */
Ext.define('SearchForm',{
	extend:'SearchFormPanel', 
	items:null,
	formType:null,//用于判断是日报表还是月报表
	border:0,
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
		var date = new Date();
		Ext.getCmp('endDate').setValue(date);	
		if(this.formType=='month'){
			Ext.getCmp('startDate').setValue(
				Ext.Date.getFirstDateOfMonth(date));
		}else{
			date.setDate(date.getDate() - (date.getDay()?date.getDay():7) + 1);
			Ext.getCmp('startDate').setValue(date);
		}
	},
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
						MRUtil.comboSelsct(th);
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
						MRUtil.comboSelsct(th);
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
						MRUtil.comboSelsct(th);
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
						MRUtil.comboSelsct(th);
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
		    	colspan:2,
		    	forceSelection :true,
		    	hideTrigger:false,
		    	displayField:'deptName',
				valueField:'id',
				name:'dept',
				listeners:{
					change:function(th){
						MRUtil.comboSelsct(th);
					}
				},
				id:'dept'
			},{//起止时间
		    	xtype:'datefield',
		    	disabled:true,
		    	readOnly:true,
		    	fieldLabel:i18n('i18n.marketEffectEvaluation.startDate'),
		    	format:'Y-m-d',
				name:'startDate',
				id:'startDate'
				//设置起始时间的初始值为当前月份的第一天
				//value:Ext.Date.getFirstDateOfMonth(Ext.Date.add(new Date(),Ext.Date.DAY,-1))
			},{//起止时间到
				xtype:'datefield',
		    	fieldLabel:i18n('i18n.marketEffectEvaluation.endDate'),
		    	format:'Y-m-d',
		    	maxValue:new Date(),
		    	//为结束时间赋初值
		    	//value:Ext.Date.add(new Date(),Ext.Date.DAY,-1),
				name:'endDate',
				id:'endDate',
				listeners:{
					select:function(th,newValue,oldValue){
						if(this.up('panel').formType=='month'){
							//本月第一天
							Ext.getCmp('startDate').setValue(
								Ext.Date.getFirstDateOfMonth(newValue));
						}else{
							//本周一
							newValue.setDate(newValue.getDate() - (newValue.getDay()?newValue.getDay():7) + 1);
							Ext.getCmp('startDate').setValue(newValue);
						}
					}
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
	//当登录用户具有全国的查询权限
	if(Ext.Array.contains(User.roleids,'4008305555')){
		manageDept.enable();
		businessDept.enable();
		bigDept.enable();
		smallDept.enable();
		dept.enable();
		Ext.getCmp("manageDept").store.load();	
	}
	//当登录用户具有经营本部查询权限
	else if(AssessAuthority.assessAuthority==='400000147'){
		manageDept.disable();
		var manageDeptModel=new DeptListModel();
		manageDeptModel.set("id",User.deptId);
		manageDeptModel.set("deptName",User.deptName);
		var manageDeptStore = manageDept.getStore();
		manageDeptStore.add(manageDeptModel);
		manageDept.setValue(User.deptId);
		Ext.getCmp("businessDept").store.load({params:{'parentDeptId':User.deptId}});
	}
	//当登录用户具有某个事业部的查询权限
	else if(AssessAuthority.assessAuthority==='400000127'){ 
		manageDept.disable();
		businessDept.disable();
		if(!Ext.isEmpty(AssessAuthority.assessDept)){
			var manageDeptModel=new DeptListModel();
			manageDeptModel.set("id",AssessAuthority.assessDept.id);
			manageDeptModel.set("deptName",AssessAuthority.assessDept.deptName);
			var manageDeptStore = manageDept.getStore();
			manageDeptStore.add(manageDeptModel);
			manageDept.setValue(AssessAuthority.assessDept.id);
			var businessDeptModel=new DeptListModel();
			businessDeptModel.set("id",AssessAuthority.assessDept.childDept.id);
			businessDeptModel.set("deptName",AssessAuthority.assessDept.childDept.deptName);
			var businessDeptStore = businessDept.getStore();
			businessDeptStore.add(businessDeptModel);
			businessDept.setValue(User.deptId);
			
			Ext.getCmp("bigDept").store.load({params:{'parentDeptId':User.deptId}});
			Ext.getCmp("smallDept").store.load({params:{'parentDeptId':''}});
			Ext.getCmp("dept").store.load({params:{'parentDeptId':''}});
		}
		
	}
	//当登录用户具有某个大区的查询权限
	else if(AssessAuthority.assessAuthority==='400000146'){
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
				var bigDeptModel=new DeptListModel();
				bigDeptModel.set("id",AssessAuthority.assessDept.childDept.childDept.id);
				bigDeptModel.set("deptName",AssessAuthority.assessDept.childDept.childDept.deptName);
				var bigDeptStore = bigDept.getStore();
				bigDeptStore.add(bigDeptModel);
				bigDept.setValue(User.deptId);
				Ext.getCmp("smallDept").store.load({params:{'parentDeptId':User.deptId}});
				Ext.getCmp("dept").store.load({params:{'parentDeptId':''}});
			}
		}
		
	}
	//当登录用户具有某个小区的查询权限
	else if(AssessAuthority.assessAuthority==='400000145'){
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
					var smallDeptModel=new DeptListModel();
					smallDeptModel.set("id",AssessAuthority.assessDept.childDept.childDept.childDept.id);
					smallDeptModel.set("deptName",AssessAuthority.assessDept.childDept.childDept.childDept.
							deptName);
					var smallDeptStore = smallDept.getStore();
					smallDeptStore.add(smallDeptModel);
					smallDept.setValue(User.deptId);
					Ext.getCmp("dept").store.load({params:{'parentDeptId':User.deptId}});
				}
			}
		}		
	}
	//当登录用户具有某个营业部的查询权限
	else if(AssessAuthority.assessAuthority==='400000126'){
		manageDept.disable();
		businessDept.disable();
		bigDept.disable();
		smallDept.disable();
		dept.disable();
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
						var deptModel = new DeptListModel();
						deptModel.set("id",User.deptId);
						deptModel.set("deptName",User.deptName);
						dept.getStore().add(deptModel);
						dept.setValue(User.deptId);
					}
				}
			}
		}
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