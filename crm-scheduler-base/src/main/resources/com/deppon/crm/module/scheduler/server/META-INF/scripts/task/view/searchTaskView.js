var validator = {
	'reg_dy1':/^[1-9]\d*$/,
	/**
	 * 验证任务数据格式是否正确
	 * @param {String} value 要验证的任务数据
	 * @return {Boolean} true 通过、false 未通过
	 */
	'check_jobData':function(value){
		if(Ext.isEmpty(value.replace(/[\n\s]/g,''))){
			return true;
		}
		var jobDataArray = value.split('\n');
		var reg = /^[_A-z]+$/;
		for(var key in jobDataArray){
			var temp = Ext.String.trim(jobDataArray[key]);
			if(Ext.isEmpty(temp) || temp.indexOf('#') == 0 ){continue;}
			var tempArray = temp.split('=');
			if(tempArray.length!=2 || reg.test(Ext.String.trim(tempArray[0]))===false){
				return false;
			}
		}
		return true;
	},
//	/**
//	 * 表达式简单验证
//	 * @param {String} value 要验证的表达式
//	 * @return {Boolean} true 通过、false 未通过
//	 */
//	'check_expression':function(value){
//		var reg = /[\d\w*?,/#\-]+/;
//		return reg.test(value);
//	},
	/**
	 * 验证表单
	 * @param {Object} parm 要验证的数据集
	 * @return {Boolean} true 通过、false 未通过
	 */
	'checking':function(parm){
		if(this.check_jobData(parm['jobSchedule.jobData']) === false){
			/* 任务参数必须全为键值对形式，例如：name=张三~ */
			alert(i18n('i18n.scheduler.msg.format_jobData'));
			return false;
		}
		
//		if(this.check_expression(parm['jobSchedule.triggerExpression']) === false){
//			/* 触发器表达式格式有误~例如："0 15 10 ? * 6#3"~ */
//			alert(i18n('i18n.scheduler.msg.format_expression'));
//			return false;
//		}
		
		if(parm['jobWarnning.warnType'] === '2'){/* 预警时效  为 统计 */
			if(this.reg_dy1.test(parm['jobWarnning.failTime']) === false){
				/* 统计时间必须大于0~ */
				alert(i18n('i18n.scheduler.msg.format_failTime_dy1'));
				return false;
			}
			if(this.reg_dy1.test(parm['jobWarnning.failCount']) === false){
				/* 统计次数必须大于0~ */
				alert(i18n('i18n.scheduler.msg.format_failCount_dy1'));
				return false;
			}
		}
		return true;
	}
};
/**
 * @describe 任务查询界面
 * @author zouming
 * @createDate 2012-12-28
 * @updateDate  
 */
Ext.onReady(function(){
	var searchForm = Ext.create('SearchFormPanel',{
		defaultType:'textfield',
		layout:{
			type:'table',
			columns:3
		},
		items:[{
			fieldLabel:i18n('i18n.scheduler.jobGroup')/*任务分组*/,
			name:'jobGroup'
		},{
			fieldLabel:i18n('i18n.scheduler.jobName')/*任务名称*/,
			name:'jobName'
		},{
			fieldLabel:i18n('i18n.scheduler.triggerName')/*触发器名称*/,
			name:'triggerName'
		}]
	});
	
	var detailsWin,
		addWin,
		modifyWin;
	var btnPanel =Ext.create('NormalButtonPanel',{
		items:[{
				xtype:'leftbuttonpanel',
				defaultType:'button',
				items:[{
					text:i18n('i18n.scheduler.btn.process')/*查看处理*/,
					handler:function(){
						r = gridPanel.getSelectionModel().getSelection();
						if(r.length!=1){
							MessageUtil.showMessage(i18n('i18n.scheduler.msg_data_select')/*请选择操作数据~*/);
							return;
						}
						
						function fun_params(){
							return {
								'condition.id':gridPanel.getSelectionModel().getSelection()[0].get('jobSchedule.id')
							};
						}
						if(Ext.isEmpty(detailsWin)){
							detailsWin = Ext.create('PopWindow',{
								title:i18n('i18n.scheduler.title.jobDetails')/*任务详情*/,
								width:670,
								height:600,
								closeAction:'hide',
								items:Ext.create('DetailsFormPanel',{id:'detailstaskId'}),
								buttons:[{
									text:i18n('i18n.scheduler.btn.start')/*启动*/,
									handler:function(){
										var successFn = function(json){
											MessageUtil.showMessage(i18n('i18n.scheduler.msg.start_succeed')/*启动成功~*/);
										};
										var failFn = function(json){
											MessageUtil.showMessage(i18n('i18n.scheduler.msg.start_fail')/*启动失败~*/);
										};
										taskData.prototype.startupTask(fun_params(),successFn,failFn);  
									}
								},{
									text:i18n('i18n.scheduler.btn.stop')/*停止*/,
									handler:function(){
										var successFn = function(json){
											MessageUtil.showMessage(i18n('i18n.scheduler.msg.stop_succeed')/*停止成功~*/);
										};
										var failFn = function(json){
											MessageUtil.showMessage(i18n('i18n.scheduler.msg.start_fail')/*停止失败~*/);
										};
										taskData.prototype.stopTask(fun_params(),successFn,failFn);  
									}
								},{
									text:i18n('i18n.scheduler.btn.execute')/*执行*/,
									handler:function(){
										var successFn = function(json){
											MessageUtil.showMessage(i18n('i18n.scheduler.msg.execute_succeed')/*执行成功~*/);
										};
										var failFn = function(json){
											MessageUtil.showMessage(i18n('i18n.scheduler.msg.execute_fail')/*执行失败~*/);
										};
										taskData.prototype.executeTask(fun_params(),successFn,failFn);  
									}
								},{
									text:i18n('i18n.scheduler.close')/*关闭*/,
									handler:function(){
										detailsWin.close();
									}
								}]
							});
						}
						Ext.getCmp('detailstaskId').loadRecord(r[0]);
						detailsWin.show();
					}
				},{
					text:i18n('i18n.scheduler.add')/*新增*/,
					handler:function(){
						addWin = Ext.create('PopWindow',{
							title:i18n('i18n.scheduler.title.jobAdd')/*任务新增*/,
							width:670,
							height:550,
							items:Ext.create('AddFormPanel',{id:'addFormPanelId'}),
							buttons:[{
								text:i18n('i18n.scheduler.save')/*保存*/,
								handler:function(){
									var form = Ext.getCmp('addFormPanelId').getForm();
									var warnType = form.findField('jobWarnning.warnType');
									var failTime = form.findField('jobWarnning.failTime');
									var failCount = form.findField('jobWarnning.failCount');
									if(!form.isValid()){
										return;
									}
									var params = {
											'jobSchedule.triggerName':form.findField('jobSchedule.triggerName').getValue(),
											'jobSchedule.triggerGroup':form.findField('jobSchedule.triggerGroup').getValue(),
											'jobSchedule.triggerType':form.findField('jobSchedule.triggerType').getValue(),
											'jobSchedule.triggerExpression':form.findField('jobSchedule.triggerExpression').getValue(),
											'jobSchedule.jobName':form.findField('jobSchedule.jobName').getValue(),
											'jobSchedule.jobGroup':form.findField('jobSchedule.jobGroup').getValue(),
											'jobSchedule.jobClass':form.findField('jobSchedule.jobClass').getValue(),
											'jobSchedule.jobData':form.findField('jobSchedule.jobData').getValue(),
											'jobSchedule.description':form.findField('jobSchedule.description').getValue(),
											'jobWarnning.warnType':warnType.getValue(),
											'jobWarnning.failTime':failTime.getValue(),
											'jobWarnning.failCount':failCount.getValue(),
											'jobWarnning.mobile':form.findField('jobWarnning.mobile').getValue(),
											'jobWarnning.email':form.findField('jobWarnning.email').getValue()
									};
									
									if(warnType.getValue() === '1'){/* 即时的 取消统计参数 */
										delete params['jobWarnning.failTime'];
										delete params['jobWarnning.failCount'];
									}
									/* 验证表单数据  */
									if(validator.checking(params) === false){return;}
									
									var successFn = function(json){
										gridPanel.getStore().load();
										addWin.close();
										MessageUtil.showMessage(i18n('i18n.scheduler.msg.add_succeed')/*新增成功~*/);
									};
									var failFn = function(json){
										MessageUtil.showMessage(i18n('i18n.scheduler.msg.add_fail')/*新增失败~*/);
									};
									taskData.prototype.submitTask(params,successFn,failFn);       
								}
							},{
								text:i18n('i18n.scheduler.cancel')/*取消*/,
								handler:function(){
									addWin.close();
								}
							}]
						});
						addWin.show();
					}
				},{
					text:i18n('i18n.scheduler.update')/*修改*/,
					handler:function(){
						r = gridPanel.getSelectionModel().getSelection();
						if(r.length!=1){
							MessageUtil.showMessage(i18n('i18n.scheduler.msg_data_select')/*请选择操作数据~*/);
							return;
						}
						if(Ext.isEmpty(modifyWin)){
							modifyWin = Ext.create('PopWindow',{
								title:i18n('i18n.scheduler.title.jobUpdate')/*任务修改*/,
								width:670,
								height:600,
								closeAction:'hide',
								items:Ext.create('ModifyFormPanel',{id:'modifyTaskId'}),
								buttons:[{
									text:i18n('i18n.scheduler.delete')/*删除*/,
									handler:function(){
										var params={
												'condition.id':r[0].get('jobSchedule.id')
										};
										var successFn = function(json){
											gridPanel.getStore().remove(r);
											modifyWin.close();
											MessageUtil.showInfoMes(i18n('i18n.scheduler.msg.delete_succeed')/*删除成功~*/);
										};
										var failFn = function(json){
											MessageUtil.showInfoMes(i18n('i18n.scheduler.msg.delete_fail')/*删除失败~*/);
										};
										MessageUtil.showQuestionMes (i18n('i18n.scheduler.msg.confirm_delete')/*您是否确定删除？*/,function(e){
											if(e==='yes'){
												taskData.prototype.deleteTask(params,successFn,failFn);
											}
										},null);
									}
								},{
									text:i18n('i18n.scheduler.save')/*保存*/,
									handler:function(){
										var form = Ext.getCmp('modifyTaskId').getForm();
										if(!form.isValid()){
											return;
										}
										var params={
												'jobSchedule.id':r[0].get('jobSchedule.id'),
												'jobSchedule.triggerName':form.findField('jobSchedule.triggerName').getValue(),
												'jobSchedule.triggerGroup':form.findField('jobSchedule.triggerGroup').getValue(),
												'jobSchedule.triggerType':form.findField('jobSchedule.triggerType').getValue(),
												'jobSchedule.triggerExpression':form.findField('jobSchedule.triggerExpression').getValue(),
												'jobSchedule.jobName':form.findField('jobSchedule.jobName').getValue(),
												'jobSchedule.jobGroup':form.findField('jobSchedule.jobGroup').getValue(),
												'jobSchedule.jobClass':form.findField('jobSchedule.jobClass').getValue(),
												'jobSchedule.jobData':form.findField('jobSchedule.jobData').getValue(),
												'jobSchedule.description':form.findField('jobSchedule.description').getValue(),
												'jobWarnning.warnType':form.findField('jobWarnning.warnType').getValue(),
												'jobWarnning.failTime':form.findField('jobWarnning.failTime').getValue(),
												'jobWarnning.failCount':form.findField('jobWarnning.failCount').getValue(),
												'jobWarnning.mobile':form.findField('jobWarnning.mobile').getValue(),
												'jobWarnning.email':form.findField('jobWarnning.email').getValue()
										};
										
										/* 验证表单数据  */
										if(validator.checking(params) === false){return;}
										
										var successFn = function(json){
											gridPanel.getStore().load();
											modifyWin.close();
											MessageUtil.showInfoMes(i18n('i18n.scheduler.msg.update_succeed')/*修改成功~*/);
										};
										var failFn = function(json){
											MessageUtil.showInfoMes(i18n('i18n.scheduler.msg.update_fail')/*修改失败~*/);
										};
										taskData.prototype.modifyTask(params,successFn,failFn);
									}
								},{
									text:i18n('i18n.scheduler.cancel')/*取消*/,
									handler:function(){
										modifyWin.close();
									}
								}]
							});
						}
						Ext.getCmp('modifyTaskId').loadRecord(r[0]);
						modifyWin.show();
					}
				}]
			},{
				xtype:'middlebuttonpanel'
			},{
				xtype:'rightbuttonpanel',
				items:[{
					xtype:'button',
					text:i18n('i18n.scheduler.search')/*查询*/,
					handler:function(){
						taskStore.load();
					}
				}]
		}]
	});
	var taskStore = Ext.create('TaskStore',{});
	var gridPanel = Ext.create('SearchGridPanel',{
		store:taskStore,
		border:false,
		selModel:Ext.create('Ext.selection.CheckboxModel',{}),
		columns:[
		{xtype:'rownumberer',header:i18n('i18n.scheduler.serial'/*序号*/),width:40},
		{
			text:i18n('i18n.scheduler.triggerName')/*触发器名称*/,dataIndex:'jobSchedule.triggerName',width:120
		},{
			text:i18n('i18n.scheduler.triggerGroup')/*触发器分组*/,dataIndex:'jobSchedule.triggerGroup',width:120
		},{
			text:i18n('i18n.scheduler.triggerType')/*触发器类型*/,dataIndex:'jobSchedule.triggerType',width:120,
			renderer:function(value){
				return value==='1'?'表达式':'表达式';
			}
		},{
			text:i18n('i18n.scheduler.triggerState')/*状态*/,dataIndex:'triggerState',width:60,
			renderer:function(value){
				return triggerStateStore.getStateValue(value);
			}
		},{
			text:i18n('i18n.scheduler.triggerExpression')/*表达式*/,dataIndex:'jobSchedule.triggerExpression',width:120
		},{
			text:i18n('i18n.scheduler.jobName')/*任务名称*/,dataIndex:'jobSchedule.jobName',width:100
		},{
			text:i18n('i18n.scheduler.jobGroup')/*任务分组*/,dataIndex:'jobSchedule.jobGroup',width:100
		},{
			text:i18n('i18n.scheduler.jobClass')/*任务类*/,dataIndex:'jobSchedule.jobClass',width:300
		}],
		bbar:Ext.create('Ext.toolbar.Paging',{
			id:'toolbarPaging',
			store:taskStore,
//			displayMsg:'',
			displayInfo:true,
			items:[
			    '-',
			    {
			    	xtype:'tbtext',
			    	text:i18n('i18n.pager.prefixText')/*本页显示*/
			    },{
			    	xtype:'combo',
			    	store:Ext.create('Ext.data.Store',{
			    		fields:['value'],
			    		data:[{
			    			value:'10'
			    		},{
			    			value:'20'
			    		},{
			    			value:'30'
			    		},{
			    			value:'40'
			    		},{
			    			value:'50'
			    		}]
			    	}),
			    	width:50,
			    	value:'10',
			    	editable:false,
			    	displayField:'value',
			    	valueField:'value',
			    	listeners:{
			    		select:{
			    			scope:this,
			    			fn:function( me, records, eOpts){
				    			var pageSize = gridPanel.getStore().pageSize;
				    			var meValue = parseInt(me.value);
				    			if(meValue!=pageSize){
				    				gridPanel.getStore().pageSize = meValue;
				    				Ext.getCmp('toolbarPaging').moveFirst();
				    			}
				    		}
			    		}
			    	}
			    },{
			    	xtype:'tbtext',
			    	text:'条'
			    }
			]
		})
	});
	taskStore.on('beforeload',function(taskStore,operation){
		var form = searchForm.getForm();
		if(!form.isValid()){
			return;
		}
		var params = {
				'condition.jobName' : form.findField('jobName').getValue(),
				'condition.jobGroup' : form.findField('jobGroup').getValue(),
				'condition.triggerName' : form.findField('triggerName').getValue()
		};
		Ext.apply(operation,{
				params:params
		});
	});
	Ext.create('Ext.container.Viewport',{
		layout:'border',
		border:false,
		items:[{
			margin:'5 0 0 0',
			region:'north',
			border:false,
			items:searchForm
		},{
			region:'center',
			layout:'border',
			border:false,
			items:[{
				region:'north',
				border:false,
				items:btnPanel
			},{
				region:'center',
				border:false,
				layout:'fit',
				items:gridPanel
			}]
		}]
	});
});