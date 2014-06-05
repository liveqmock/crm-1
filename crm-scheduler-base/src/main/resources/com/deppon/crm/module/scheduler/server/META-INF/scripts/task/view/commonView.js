var mustFill = '<span style="color:red">*</span>';
var regs = {
	/* quartz 任务调度 */
//	'quartz':/^([\d*/\-,?]+\s){5}([\d*\-?#A-Z]+)$|^([\d*/\-,?A-Z]+\s){6}([\d*/\-]+)$/ ,
	'quartz':/^[\d*/\-#,?A-Z\s]+$/ ,
	'mobiles':/^([\s\n]*1\d{10};[\s\n]*)+$/, /* 手机号码集合*/
	'emails':/^([\s\n]*[A-z0-9_.\-]+\@[A-z0-9\-]+\.+[A-z0-9]{2,4};[\s\n]*)+$/ /* 电子邮件集合*/
};
Ext.define('AddFormPanel',{
	extend:'TitleFormPanel',
	defaultType:'basicfiledset',
	items:[{
		title:i18n('i18n.scheduler.title.triggerInfo')/*触发器信息*/,
		defaultType:'textfield',
		layout:{
			type:'table',
			columns:2
		},
		defaults:{
			labelWidth:80,
			width:300,
			allowBlank:false
		},
		items:[{
			fieldLabel:mustFill+i18n('i18n.scheduler.triggerName')/*触发器名称 */,
			name:'jobSchedule.triggerName'
		},{
			fieldLabel:mustFill+i18n('i18n.scheduler.triggerGroup')/*触发器分组*/,
			name:'jobSchedule.triggerGroup'
		},{
			fieldLabel:mustFill+i18n('i18n.scheduler.triggerType')/*触发器类型*/,
			name:'jobSchedule.triggerType',
			xtype:'combobox',
			store:{
				xtype:'store',
				fields: ['key', 'value'],
			    data : [
			        {'key':'1', 'value':'表达式'}
			    ]
			},
			queryMode: 'local',
		    displayField: 'value',
		    editable:false,
		    valueField: 'key'
		},{
			fieldLabel:mustFill+i18n('i18n.scheduler.triggerExpression')/*表达式*/,
			regex:regs.quartz,/* hpf quartz 正则 */
			regexText:i18n('i18n.scheduler.msg.format_expression'), /*触发器表达式格式有误*/
			name:'jobSchedule.triggerExpression'
		}]
	},{
		title:i18n('i18n.scheduler.title.jobInfo')/*任务信息*/,
		defaultType:'textfield',
		layout:{
			type:'table',
			columns:2
		},
		defaults:{
			labelWidth:80,
			allowBlank:false,
			width:300
		},
		items:[{
			fieldLabel:mustFill+i18n('i18n.scheduler.jobName')/*任务名称*/,
			name:'jobSchedule.jobName'
		},{
			fieldLabel:mustFill+i18n('i18n.scheduler.jobGroup')/*任务分组*/,
			name:'jobSchedule.jobGroup'
		},{
			fieldLabel:mustFill+i18n('i18n.scheduler.jobClass')/*任务类*/,
			width:600,colspan:'2',
			name:'jobSchedule.jobClass'
		},{
			fieldLabel:i18n('i18n.scheduler.jobData')/*任务参数*/,
			xtype:'textarea',allowBlank:true,
			name:'jobSchedule.jobData'
		},{
			fieldLabel:i18n('i18n.scheduler.description')/*任务描述*/,
			xtype:'textarea',allowBlank:true,
			name:'jobSchedule.description'
		}]
	},{
		title:i18n('i18n.scheduler.title.warningInfo')/*预警信息*/,
		defaultType:'textfield',
		layout:{
			type:'table',
			columns:2
		},
		defaults:{
			labelWidth:80,
			allowBlank:false,
			width:300
		},
		items:[{
			xtype:'combo',colspan:'2',
			name:'jobWarnning.warnType',
			fieldLabel:mustFill+i18n('i18n.scheduler.warnType')/*预警时效*/,
			listeners: {
		        change:function(field,newValue,oldValue){
		        	if(newValue===oldValue){return;}
		        	var form = field.findParentByType('form').getForm();
		        	var failTime = form.findField('jobWarnning.failTime');
		        	var failCount = form.findField('jobWarnning.failCount');
		        	if(newValue==='2'){
		        		failTime.setReadOnly(false);
		        		failCount.setReadOnly(false);
		        	}else{
		        		failTime.setReadOnly(true);
		        		failCount.setReadOnly(true);
		        	}
		        }
		    },
			store:Ext.create('Ext.data.Store',{
				fields:['name','id'],
				data:[{name:'即时',id:'1'},{name:'统计',id:'2'}]
			}),
			displayField:'name',
			valueField:'id',
			editable:false
		},{
			fieldLabel:mustFill+i18n('i18n.scheduler.failTime')/*统计时间*/,
			readOnly:true,allowBlank:true,
			name:'jobWarnning.failTime'
		},{
			fieldLabel:mustFill+i18n('i18n.scheduler.failCount')/*统计次数*/,
			readOnly:true,allowBlank:true,
			name:'jobWarnning.failCount'
		},{
			fieldLabel:i18n('i18n.scheduler.mobile')/*手机*/,
			regex:regs.mobiles,/* hpf 手机正则 */
			xtype:'textarea',allowBlank:true,
			regexText:i18n('i18n.scheduler.msg.format_mobile')/*手机格式错误,必须11位数字~*/,
			name:'jobWarnning.mobile'
		},{
			fieldLabel:mustFill+i18n('i18n.scheduler.email')/*邮箱*/,
			xtype:'textarea',
			regex:regs.emails,/* hpf 邮箱正则 */
			regexText:i18n('i18n.scheduler.msg.format_email')/*邮件格式错误~*/,
			name:'jobWarnning.email'
		}]
	}]
});

Ext.define('DetailsFormPanel',{
	extend:'TitleFormPanel',
	defaultType:'basicfiledset',
	items:[{
		title:i18n('i18n.scheduler.title.triggerInfo')/*触发器信息*/,
		defaultType:'textfield',
		layout:{
			type:'table',
			columns:2
		},
		defaults:{
			labelWidth:80,
			readOnly:true,
			cls:'readonly',
			width:300
		},
		items:[{
			fieldLabel:i18n('i18n.scheduler.triggerName')/*触发器名称*/,
			name:'jobSchedule.triggerName'
		},{
			fieldLabel:i18n('i18n.scheduler.triggerGroup')/*触发器分组*/,
			name:'jobSchedule.triggerGroup'
		},{
			fieldLabel:i18n('i18n.scheduler.triggerType')/*触发器类型*/,
			name:'jobSchedule.triggerType',
			xtype:'combobox',
			editable:false,
			store:{
				xtype:'store',
				fields: ['key', 'value'],
			    data : [
			        {'key':'1', 'value':'表达式'}
			    ]
			},
			queryMode: 'local',
		    displayField: 'value',
		    valueField: 'key'
		},{
			fieldLabel:i18n('i18n.scheduler.triggerExpression')/*触发器表达式*/,
			name:'jobSchedule.triggerExpression'
		},{
			fieldLabel:i18n('i18n.scheduler.previousFireTime')/*上次执行时间*/,
			name:'trigger.previousFireTime',
			format:'Y-m-d H:i:s',
			xtype :'datefield'
		},{
			fieldLabel:i18n('i18n.scheduler.nextFireTime')/*下次执行时间*/,
			name:'trigger.nextFireTime',
			format:'Y-m-d H:i:s',
			xtype :'datefield'
		},{
			fieldLabel:i18n('i18n.scheduler.triggerState')/*触发器状态*/,
			name:'triggerState',xtype:'combobox',
			store:triggerStateStore,
			queryMode: 'local',
		    displayField: 'value',
		    editable:false,
		    valueField:'key'
		}]
	},{
		title:i18n('i18n.scheduler.title.jobInfo')/*任务信息*/,
		defaultType:'textfield',
		layout:{
			type:'table',
			columns:2
		},
		defaults:{
			labelWidth:80,
			readOnly:true,
			cls:'readonly',
			width:300
		},
		items:[{
			fieldLabel:i18n('i18n.scheduler.jobName')/*任务名称*/,
			name:'jobSchedule.jobName'
		},{
			fieldLabel:i18n('i18n.scheduler.jobGroup')/*任务分组*/,
			name:'jobSchedule.jobGroup'
		},{
			fieldLabel:i18n('i18n.scheduler.jobClass')/*任务类*/,
			width:600,colspan:'2',
			name:'jobSchedule.jobClass'
		},{
			fieldLabel:i18n('i18n.scheduler.jobData')/*任务参数*/,
			xtype:'textarea',
			name:'jobSchedule.jobData'
		},{
			fieldLabel:i18n('i18n.scheduler.description')/*任务描述*/,
			xtype:'textarea',
			name:'jobSchedule.description'
		}]
	},{
		title:i18n('i18n.scheduler.title.warningInfo')/*预警信息*/,
		defaultType:'textfield',
		layout:{
			type:'table',
			columns:2
		},
		defaults:{
			labelWidth:80,
			readOnly:true,
			cls:'readonly',
			width:300
		},
		items:[{
			fieldLabel:i18n('i18n.scheduler.warnType')/*预警时效*/,
			xtype:'combo',colspan:'2',
			name:'jobWarnning.warnType',
			store:{
				xtype:'store',fields:['name','id'],
				data:[{name:'即时',id:'1'},{name:'统计',id:'2'}]
			},
			displayField:'name',
			valueField:'id',
			editable:false
		},{
			fieldLabel:i18n('i18n.scheduler.failTime')/*统计时间*/,
			name:'jobWarnning.failTime'
		},{
			fieldLabel:i18n('i18n.scheduler.failCount')/*统计次数*/,
			name:'jobWarnning.failCount'
		},{
			fieldLabel:i18n('i18n.scheduler.mobile')/*手机*/,
			xtype:'textarea',
			name:'jobWarnning.mobile'
		},{
			fieldLabel:i18n('i18n.scheduler.email')/*邮箱*/,
			xtype:'textarea',
			name:'jobWarnning.email'
		}]
	}]
});

Ext.define('ModifyFormPanel',{
	extend:'TitleFormPanel',
	defaultType:'basicfiledset',
	items:[{
		title:i18n('i18n.scheduler.title.triggerInfo')/*触发器信息*/,
		defaultType:'textfield',
		layout:{
			type:'table',
			columns:2
		},
		defaults:{
			labelWidth:80,
			width:300
		},
		items:[{
			fieldLabel:i18n('i18n.scheduler.triggerName')/*触发器名称*/,
			readOnly:true,name:'jobSchedule.triggerName'
		},{
			fieldLabel:i18n('i18n.scheduler.triggerGroup')/*触发器分组*/,
			readOnly:true,name:'jobSchedule.triggerGroup'
		},{
			fieldLabel:mustFill+i18n('i18n.scheduler.triggerType')/*触发器类型*/,
			name:'jobSchedule.triggerType',
			xtype:'combobox',
			store:{
				xtype:'store',
				fields: ['key', 'value'],
			    data : [
			        {'key':'1', 'value':'表达式'}
			    ]
			},
			queryMode: 'local',
		    displayField: 'value',
		    editable:false,
		    valueField: 'key'
		},{
			fieldLabel:mustFill+i18n('i18n.scheduler.triggerExpression')/*触发器表达式*/,
			regex:regs.quartz,/* hpf quartz 正则 */
			regexText:i18n('i18n.scheduler.msg.format_expression'), /*触发器表达式格式有误*/
			name:'jobSchedule.triggerExpression'
		},{
			fieldLabel:i18n('i18n.scheduler.previousFireTime')/*上次执行时间*/,
			name:'trigger.previousFireTime',
			readOnly:true,cls:'readonly',
			format:'Y-m-d H:i:s',
			xtype :'datefield'
		},{
			fieldLabel:i18n('i18n.scheduler.nextFireTime')/*下次执行时间*/,
			name:'trigger.nextFireTime',
			readOnly:true,cls:'readonly',
			format:'Y-m-d H:i:s',
			xtype :'datefield'
		},{
			fieldLabel:i18n('i18n.scheduler.triggerState')/*触发器状态*/,
			name:'triggerState',xtype:'combobox',
			store:triggerStateStore,cls:'readonly',
			queryMode: 'local',
		    displayField: 'value',
		    editable:false,
		    valueField:'key',
			readOnly:true
			
		}]
	},{
		title:i18n('i18n.scheduler.title.jobInfo')/*任务信息*/,
		defaultType:'textfield',
		layout:{
			type:'table',
			columns:2
		},
		defaults:{
			labelWidth:80,
			allowBlank:false,
			width:300
		},
		items:[{
			fieldLabel:i18n('i18n.scheduler.jobName')/*任务名称*/,
			readOnly:true,name:'jobSchedule.jobName'
		},{
			fieldLabel:i18n('i18n.scheduler.jobGroup')/*任务分组*/,
			readOnly:true,name:'jobSchedule.jobGroup'
		},{
			fieldLabel:mustFill+i18n('i18n.scheduler.jobClass')/*任务类*/,
			width:600,colspan:'2',
			name:'jobSchedule.jobClass'
		},{
			fieldLabel:i18n('i18n.scheduler.jobData')/*任务参数*/,
			xtype:'textarea',allowBlank:true,
			name:'jobSchedule.jobData'
		},{
			fieldLabel:i18n('i18n.scheduler.description')/*任务描述*/,
			xtype:'textarea',allowBlank:true,
			name:'jobSchedule.description'
		}]
	},{
		title:i18n('i18n.scheduler.title.warningInfo')/*预警信息*/,
		defaultType:'textfield',
		layout:{
			type:'table',
			columns:2
		},
		defaults:{
			labelWidth:80,
			allowBlank:false,
			width:300
		},
		items:[{
			xtype:'combo',colspan:'2',
			name:'jobWarnning.warnType',
			fieldLabel:mustFill+i18n('i18n.scheduler.warnType')/*预警时效*/,
			listeners: {
		        change:function(field,newValue,oldValue){
		        	if(newValue===oldValue){return;}
		        	var form = field.findParentByType('form').getForm();
		        	var failTime = form.findField('jobWarnning.failTime');
		        	var failCount = form.findField('jobWarnning.failCount');
		        	if(newValue==='2'){
		        		failTime.setReadOnly(false);
		        		failCount.setReadOnly(false);
		        	}else{
		        		failTime.setReadOnly(true);
		        		failCount.setReadOnly(true);
		        	}
		        }
		    },
			store:{
				xtype:'store',fields:['name','id'],
				data:[{name:'即时',id:'1'},{name:'统计',id:'2'}]
			},
			displayField:'name',
			valueField:'id',
			editable:false
		},{
			fieldLabel:mustFill+i18n('i18n.scheduler.failTime')/*统计时间*/,
			readOnly:true,allowBlank:true,
			name:'jobWarnning.failTime'
		},{
			fieldLabel:mustFill+i18n('i18n.scheduler.failCount')/*统计次数*/,
			readOnly:true,allowBlank:true,
			name:'jobWarnning.failCount'
		},{
			fieldLabel:i18n('i18n.scheduler.mobile')/*手机*/,
			regex:regs.mobiles,/* hpf 手机正则 */
			xtype:'textarea',allowBlank:true,
			regexText:i18n('i18n.scheduler.msg.format_mobile')/*手机格式错误,必须11位数字~*/,
			name:'jobWarnning.mobile'
		},{
			fieldLabel:mustFill+i18n('i18n.scheduler.email')/*邮箱*/,
			xtype:'textarea',
			regex:regs.emails,/* hpf 邮箱正则 */
			regexText:i18n('i18n.scheduler.msg.format_email')/*邮件格式错误~*/,
			name:'jobWarnning.email'
		}]
	}]
});