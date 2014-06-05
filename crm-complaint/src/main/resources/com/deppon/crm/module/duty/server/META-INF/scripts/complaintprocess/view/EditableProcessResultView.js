
/**
 * 处理结果 panel 
 */
Ext.define('EditableProcessResultPanel',{
	extend:'BasicVboxPanel'
	,parent:null //
	,complaint:null  // 工单信息
	,bulletinStore:null  //通知对象store
	,grid_MaxCount:10  // 处理结果最大行数
	,editableProcessResultGrid:null //处理结果列表
	,is_loadProcessResult:false  // 是否加载处理结果数据
	,is_loadALLResultStore:false //全部的处理结果
	,msg_dealmatters_isNull:i18n('i18n.comp.msg.dealmatters_isNull')/*请填写处理事项*/
	,msg_ftaskDeptName_isNull:i18n('i18n.comp.msg.ftaskDeptName_isNull')/*请填写任务部门*/
	,msg_no_process_big_feedtime:i18n('i18n.comp.finishProcess.msg.no_process_big_feedtime')/*处理时限应大于反馈时限*/
	,msg_processAndFeed_no_0:i18n('i18n.comp.finishProcess.msg.no_greater_than_0')/*抱歉，处理时限 或 反馈时限应大于0...*/
	,initComponent : function() {
		var me = this;
		this.editableProcessResultGrid = Ext.create('EditableProcessResultGrid',{
			id:'editableProcessResultGrid',parent:me,complaint:me.complaint
			,'is_loadALLResultStore':me.is_loadALLResultStore
			,'is_loadProcessResult':me.is_loadProcessResult
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
				{xtype:'titlepanel',flex:1,items:[{xtype:'displayfield',value:i18n('i18n.complaint.process_result.title')}]}
				,{
					xtype:'btnpanel',defaultType:'button',items:[
						{scope:me,text:i18n('i18n.complaint.btn.add'),id:'processResult_add',handler:me.addRowEvent}
						,{scope:me,text:i18n('i18n.complaint.btn.delete'),handler:me.deleteRowEvent}
					]
				}
			]
		} 
		,{
			xtype:'basicpanel',flex:1
			,items:[me.editableProcessResultGrid]
		}
	  ];
	}
	,deleteRowEvent:function(){
		var me = this;
		var selectionList = me.editableProcessResultGrid.getSelectionModel().getSelection();
       
		if(DpUtil.isEmpty(selectionList)){
			MessageUtil.showMessage(i18n('i18n.comp.processResult.msg.no_select'));return;
		}
		var panelStore = me.editableProcessResultGrid.getStore();
		for(var i=0;i<selectionList.length;i++){
			var record = selectionList[i];
			var old_jobCode = record.get('jobCode');
			me.editableProcessResultGrid.delete_bulletinByDealName({/* 删除不应该存在的 通知对象 */
				'old_ftaskDeptName':record.get('old_ftaskDeptName')
				,'bulletinStore':me.bulletinStore
				,'old_dealType':record.get('old_dealType')
				,'jobCode':old_jobCode
			});
			panelStore.remove(selectionList[i]);
		}
        if(panelStore.getCount()<me.grid_MaxCount){Ext.getCmp('processResult_add').setDisabled(false);}
	}
	,addRowEvent:function(){
		var me = this;
		var panelStore = me.editableProcessResultGrid.getStore();
		//总数不能大于10
		if(panelStore.getCount()==me.grid_MaxCount){Ext.getCmp('processResult_add').setDisabled(true);return;}
		panelStore.add(new ProcessResultModel({}));
		if(panelStore.getCount()==me.grid_MaxCount){Ext.getCmp('processResult_add').setDisabled(true);}
	}
});

/**
 * 客户工单之 处理记录列表
 */
Ext.define('EditableProcessResultGrid',{
	extend:'PopupGridPanel'
	,parent:null //
	,complaint:null  // 工单信息
	,is_loadProcessResult:false  // 是否加载处理结果数据
	,is_loadALLResultStore:false //全部的处理结果
	,deptName:null // 选中的部门名称
	,no_return_resultArray:new Array() //要是加载部分的处理结果，则先查下所有的
	,initComponent:function(){
		var me = this;
		
		if(!DpUtil.isEmpty(me.store)){me.store.removeAll(false);}
		if(DpUtil.isEmpty(me.selModel)){
			me.selModel = new Ext.selection.CheckboxModel();
		}
		
		me.store = Ext.create(me.is_loadALLResultStore?'ProcessResultStore':'ReturnProcessResultStore',{autoLoad:false});
		if(!DpUtil.isEmpty(me.complaint) && me.is_loadProcessResult){
			function beforeload(store, operation, eOpts){
				var searchParams = {
					'complaintSearchCondition.fid':me.complaint.fid
				};
				Ext.apply(operation,{params : searchParams});
			}
			me.store.on('beforeload',beforeload);
			me.store.load();
			
			if(!me.is_loadALLResultStore || me.is_loadALLResultStore==false){
				//要是加载部分的处理结果，则先查下所有的
				var all_store = Ext.create('ProcessResultStore',{autoLoad:false});
				all_store.on('beforeload',beforeload);
				all_store.on('load',function(store){
					store.each(function(record){
						if(record.get('stat')!=='TASK_RETURNED'){
							me.no_return_resultArray.push(Ext.clone(record.data));
						}
					});
				});
				all_store.load();
			}
		}
		me.columns = me.getColumns();
		me.callParent(arguments);
	}
	,plugins:Ext.create('Ext.grid.plugin.CellEditing',{clicksToEdit:1})
	,getColumns:function(){
		var me = this;
		return [
			{xtype:'rownumberer',header:i18n('i18n.complaint.serial_number'),width:40},
			{
				header:i18n('i18n.complaint.process_result.dealmatters')
				,width:140,dataIndex:'dealmatters',editor:{
					xtype:'textfield',listeners:{
					change:function(combo,newValue,oldValue){
						if(newValue.length>200){combo.setValue(oldValue);}
					}}
				}
			}
			,{
				header:i18n('i18n.comp.processResult.dealType')
				,width:80,dataIndex:'dealType',editor:{
					xtype:'combobox',forceSelection:true
	                ,store:getDataDictionaryByName(DataDictionary,'PROCESS_TYPE')
	                
	                ,queryMode:'local',displayField:'codeDesc',valueField:'code'
	                
					,listeners:{scope:me,select:function(combo,records,eOpts){
						var selection = me.getSelectionModel().getSelection()[0];
						selection.set('ftaskDeptName','');
					}}
				}
				,renderer:function(value,metaData,record){
					return rendererDictionary(value,DataDictionary.PROCESS_TYPE);
				}
			}
			,{
				header:i18n('i18n.complaint.process_result.task_department')
				,width:160,dataIndex:'ftaskDeptName'
				,editor:{
					xtype:'textfield',readOnly:true
					,listeners:{scope:me,focus:function(obj){
						var selection = me.getSelectionModel().getSelection()[0];
						var bulletinStore = me.parent.bulletinStore;/*处理对象*/
						var dealType = selection.get('dealType');
						if(DpUtil.isEmpty(dealType)){
							MessageUtil.showMessage(i18n('i18n.comp.processResult.msg.no_dealType_select'));return;
						}
						if(dealType==='department'){
							me.deptLookUpWindow(selection,bulletinStore);/*调用部门处理*/
						}else{
							me.employeeLookUpWindow(selection,bulletinStore);/*调用个人处理*/
						}
					}}
				}
			}
			,{
				header:i18n('i18n.complaint.process_result.task_attribute')
				,width:100,dataIndex:'taskproperties',editor:{
					xtype:'combobox',forceSelection:true
	                ,store:getDataDictionaryByName(DataDictionary,'TASKPROPERTIES_TYPE')
	                ,queryMode:'local',displayField:'codeDesc',valueField:'code'
				}
				,renderer:function(value){
					return rendererDictionary(value,DataDictionary.TASKPROPERTIES_TYPE);
				}
			}
			,{
				header:i18n('i18n.complaint.process_result.feedtimeLimit')
				,width:110,dataIndex:'feedtimelimit',editor:{
					xtype:'numberfield',minValue:0
				}
			}
			,{
				header:i18n('i18n.complaint.process_result.processtimeLimit')
				,flex:1,dataIndex:'processtimelimit',editor:{
					xtype:'numberfield',minValue:0
				}
			}
		];
	}
	
	//判断重复的通知对象
	,is_existBulletin:function(employee,dealType){
		var me = this;
		var bulletinStore = me.parent.bulletinStore;/*处理对象*/
		var flag = false;
		bulletinStore.each(function(record){
			if(
				(
					/* 之前若为个人,则根据个人删除*/
					(dealType==='employee' && record.get('bulletinid') == employee['empId'])
					/* 之前若为部门,则根据部门删除*/
					|| (dealType==='department' && record.get('bulletinid') == employee['id'])
				)
				&& record.get('dealType') === dealType
			){flag = true;return false;}
		});
		return flag;
	}
	//未退回的处理数据 是否存在当前数据
	,is_exist_noreturn_result:function(dealType,processId){
		var me = this;
		var flag = false;
		Ext.each(me.no_return_resultArray,function(opt){
			if(opt['dealType']==dealType && opt['taskpartmentid']==processId){
				flag = true;return false;	
			}
		});
		return flag;
	}
	//判断resultStore 是否存在当前数据
	,is_existResult:function(dealType,processId){
		var me = this;
		var flag = false;
		/*处理结果store*/
		me.getStore().each(function(record){
			if(record.get('dealType')==dealType && record.get('taskpartmentid')==processId){
				flag = true;return false;	
			}
		});
		return flag;
	}
	/**
	 * 删除于当前类型、类型值相同的 通知对象
	 * @param {object} opt{old_ftaskDeptName:'',bulletinStore:'',old_ftaskDeptName:''}
	 */
	,delete_bulletinByDealName:function(opt){
			opt['bulletinStore'].each(function(bulletin_record){
				var code = bulletin_record.get('jobCode');
				if (code == opt['jobCode']){
					opt['bulletinStore'].remove(bulletin_record);
				}
				
/*				if(
					bulletin_record.get('is_manual_add')===0
					&& (
						 之前若为个人,则根据个人删除
						(opt['old_dealType']==='employee' && bulletin_record.get('bulletinname') == opt['old_ftaskDeptName'])
						 之前若为部门,则根据部门删除
						|| (opt['old_dealType']==='department' && bulletin_record.get('bulletinDeptName') == opt['old_ftaskDeptName'])
					)
					&& bulletin_record.get('dealType') === opt['old_dealType'] 只删除当前类型的通知对象
				){opt['bulletinStore'].remove(bulletin_record);}
*/
			});
	}
	,deptLookUpWindow:function(selection,bulletinStore){
		var me = this;
		var dealType = selection.get('dealType');
		var old_dealType = selection.get('old_dealType');
		Ext.create('DeptLookUpWindow',{
			title:i18n('i18n.comp.Department.win_title'),
			listeners:{
    			select:function(win,record){
    				var jobCode = "";
    				if(
    					!DpUtil.isEmpty(old_dealType)
    					&& dealType===old_dealType/*修改之前和修改之后的值相等*/
    					&& selection.get('taskpartmentid') == record.get('id')
    				){/*若选中的 和当前的一致 则不做任何处理*/return;}
    				
    				if(me.is_existResult(dealType,record.get('id'))){
    					MessageUtil.showMessage(i18n('i18n.comp.Department.msg.chfu_select'));
    					return;
    				}
    				//未退回处理结果是否存在验证
    				if(me.is_exist_noreturn_result(dealType,record.get('id'))){
    					MessageUtil.showMessage(i18n('i18n.comp.Department.msg.noReturn_exist'));
    					return;
    				}
    				var old_ftaskDeptName = selection.get('old_ftaskDeptName');
    				var old_dealType = selection.get('old_dealType');
    				var old_jobCode = selection.get('jobCode');
    				
    				me.delete_bulletinByDealName({/* 删除不应该存在的 通知对象 */
    					'old_ftaskDeptName':old_ftaskDeptName
    					,'bulletinStore':bulletinStore
    					,'old_dealType':old_dealType
    					,'jobCode':old_jobCode
    				});
    				if (selection.get('jobCode') == null || selection.get('jobCode') == ""){
    					// 任务编码为空时使用新编码
    					jobCode = new Date().getTime()
    				}else{
    					jobCode = selection.get('jobCode');
    				}
    				me.fn_loadBulletin(record,dealType,jobCode);
    				selection.set('taskpartmentid',record.get('id'));
    				selection.set('ftaskDeptName',record.get('deptName'));
    				selection.set('old_dealType',selection.get('dealType'));
    				selection.set('old_ftaskDeptName',selection.get('ftaskDeptName'));
    				selection.set('jobCode',jobCode);
	        		win.close();
    			}
    		}
		}).show();
	}
	,employeeLookUpWindow:function(selection,bulletinStore){
		var me = this;
		var dealType = selection.get('dealType');
		var old_dealType = selection.get('old_dealType');
		Ext.create('EmployeeLookUpWindow',{
			title:i18n('i18n.comp.Employee.win_title'),
			listeners:{
    			select:function(win,record){
    				if(
    					!DpUtil.isEmpty(old_dealType)
    					&& dealType===old_dealType/*修改之前和修改之后的值相等*/
    					&& selection.get('taskpartmentid') == record.get('empId')
    				){/*若选中的 和当前的一致 则不做任何处理*/return;}
    				
    				if(me.is_existResult(dealType,record.get('empId'))){
    					MessageUtil.showMessage(i18n('i18n.comp.Employee.msg.chfu_select'));return;
    				}
    				//未退回处理结果是否存在验证
    				if(me.is_exist_noreturn_result(dealType,record.get('empId'))){
    					MessageUtil.showMessage(i18n('i18n.comp.Employee.msg.'));return;
    				}
    				var old_ftaskDeptName = selection.get('old_ftaskDeptName');
    				var old_dealType = selection.get('old_dealType');
    				var old_jobCode = selection.get('jobCode');
    				
    				me.delete_bulletinByDealName({/* 删除不应该存在的 通知对象 */
    					'old_ftaskDeptName':old_ftaskDeptName
    					,'bulletinStore':bulletinStore
    					,'old_dealType':old_dealType
    					,'jobCode':old_jobCode
    				});
    				if (selection.get('jobCode') == null || selection.get('jobCode') == ""){
    					// 任务编码为空时使用新编码
    					jobCode = new Date().getTime()
    				}else{
    					jobCode = selection.get('jobCode');
    				}
    				if(!me.is_existBulletin(record.data,dealType,jobCode)){
    					bulletinStore.add({
							'bulletinid':record.get('empId')
							,'bulletinJobId':record.get('empCode')
							,'bulletinname':record.get('empName')
							,'position':record.get('position')
							,'bulletinTel':record.get('mobilePhone')
							,'dealType':dealType
							,'bulletinDeptName':record.get('deptName')
							,'jobCode':jobCode
						});
    				}
    				selection.set('taskpartmentid',record.get('empId'));
    				selection.set('ftaskDeptName',record.get('empName'));
    				selection.set('old_dealType',selection.get('dealType'));
    				selection.set('old_ftaskDeptName',selection.get('ftaskDeptName'));
    				selection.set('jobCode',jobCode);
    				selection.set('personUserId',record.get('userId'));
    				//selection.set('old_dealType',selection.get('dealType'));
    				//selection.set('old_ftaskDeptName',selection.get('ftaskDeptName'));
	        		win.close();
    			}
    		}
		}).show();
	}
	,fn_loadBulletin:function(dept_record,dealType,jobCode){
		var me = this;
		var bulletinStore = me.parent.bulletinStore;
		var params = {'parameterMap':{'deptId':dept_record.get('id')}};
		//执行成功
		var successFn = function(response){
			for(var i=0;i<response.employeeList.length;i++){
				var employee = response.employeeList[i];
				if(!me.is_existBulletin(employee,dealType)){
					bulletinStore.add({
						'bulletinid':employee['id']
						,'bulletinJobId':employee['empCode']
						,'bulletinname':employee['empName']
						,'position':employee['position']
						,'bulletinTel':employee['mobileNumber']
						,'bulletinDeptName':dept_record.get('deptName')
						,'dealType':dealType
						,'jobCode':jobCode
					});
				}
			}
		}
		
		//执行失败
		var failFn = function(response){
			MessageUtil.showErrorMes(response.message);
		}
		DpUtil.requestJsonAjax('searchEmployeeListByDeptId.action',params,successFn,failFn);
	}
});

