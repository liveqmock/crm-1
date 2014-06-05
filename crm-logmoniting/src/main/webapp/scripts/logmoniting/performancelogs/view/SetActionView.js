//电子邮件地址群校验正则表达式【暂时未用，但保留，将来有用】
var regs = {'email':/^([\s\n]*[A-z0-9_.\-]+\@[A-z0-9\-]+\.+[A-z0-9]{2,4};[\s\n]*)+$/ /* 电子邮件集合*/};
var logActionControl = Ext.create('LogBasicData');
/**
 * Action设置主窗口
 */
Ext.define('SetActionWindow',{
	extend:'PopWindow',
	title:i18n('i18n.Logcontrol.actionSet'),
	handType:null,
	record:null,
	height:300,
	width:470,
	initComponent:function(){
		var me = this;
		me.items = [Ext.create('SetActionPanel',{'parent':me,
												'handType':me.handType,
												'record':me.record})];
		this.callParent();
	}
	});
/**
 * Action设置主Panel
 */
Ext.define('SetActionPanel',{
	extend:'BasicFormPanel',
	height:255,
	width:440,
	handType:null,
	layout:'fit',
    items:null,
    record:null,//数据data
    fbar:null,
    initComponent:function(){
    	var me = this;
    	me.items = [{
    		xtype:'fieldset',
			defaults:{
				labelWidth:70,
				margin:'5 0 0 0',
				width:200
			},
			layout:{
			 	type:'table',    
			 	columns : 2        
			},
			defaultType:'dptextfield',
			items:[
					{
						xtype:'textfield',
						fieldLabel:i18n('i18n.Logcontrol.modulename'),
						allowBlank:false,
						name:'modulename'
					},{
						xtype:'textfield',
						fieldLabel:i18n('i18n.Logcontrol.actionName'),
						allowBlank:false,
						name:'actionName'
					},{
						xtype:'dpnumberfield',
						fieldLabel:i18n('i18n.Logcontrol.baseCount'),
						hideTrigger:true,
						allowBlank:false,
						mouseWheelEnabled:false,
						name:'baseCount'
					},{
						xtype:'dpnumberfield',
						fieldLabel:i18n('i18n.Logcontrol.baseTime'),
						hideTrigger:true,
						allowBlank:false,
						mouseWheelEnabled:false,
						name:'baseTime'
					},{
						xtype:'dpnumberfield',
						fieldLabel:i18n('i18n.Logcontrol.countFloat'),
						allowBlank:false,
						mouseWheelEnabled:false,
						maxText:'该项最大值输入值为0.99',
						maxValue:0.99,
						minValue:0,
						hideTrigger:true,
						name:'countFloat'
					},{
						xtype:'dpnumberfield',
						fieldLabel:i18n('i18n.Logcontrol.timeFloat'),
						hideTrigger:true,
						mouseWheelEnabled:false,
						maxText:'该项最大值输入值为0.99',
						maxValue:0.99,
						minValue:0,
						allowBlank:false,
						name:'timeFloat'
					},{
						xtype:'textfield',
						fieldLabel:i18n('i18n.Logcontrol.url'),
						colspan:2,
						allowBlank:false,
						name:'url'
					},{
					    xtype: 'fieldcontainer',
					    layout:'column',
					    colspan:2,
					    margin:'5 0 0 30',
					    defaultType: 'checkboxfield',
					    items: [
					        {
					            boxLabel  : i18n('i18n.Logcontrol.blackList'),
					            name      : 'blackList',
//					            margin:'0 0 0 0',
					            inputValue: '1',
					            id        : 'blackList_id',
					            listeners:{
					            	change:function(item){
					            		var emailAdress = Ext.getCmp('mailPerson_id');
					            		if(item.value){
					            			emailAdress.setValue('温馨提示：通知邮件将发至CRM全体开发人员');
					            		}else{
					            			emailAdress.setValue('温馨提示：未加入黑名单则不发送邮件');
					            		}
					            	}
					            }
					        }, {
					            boxLabel  : i18n('i18n.Logcontrol.invalid'),
					            name      : 'invalid',
					            margin:'0 0 0 50',
					            inputValue: '2',
					            id        : 'invalid_id'
					            /*此功能暂时不用，但保留
					            //启用预警时，才允许输入让通知人员邮件，否则就不让输入
					            listeners:{
					            	change:function(item){
					            		var emailAdress = Ext.getCmp('mailPerson_id');
					            		if(item.value){
					            			emailAdress.setReadOnly(false);
					            		}else if(!item.value){
					            			emailAdress.reset();
					            			emailAdress.setReadOnly(true);
					            		}
					            	}
					            }*/
					        }]
					},{
						xtype:'textarea',
						colspan:2,
						fieldLabel:i18n('i18n.Logcontrol.mailPerson'),
//						regex:regs.email,
//						regexText:i18n('i18n.Logcontrol.pleaseInfoRightEmail'),
						width:400,
						readOnly:true,
						value:'温馨提示：未加入黑名单则不发送邮件',
						id:'mailPerson_id',
						name:'mailPerson'
					}
			 ]
    	}]
    	me.fbar = me.getFbar();
    	this.callParent();
    },
    getFbar:function(){
		var me = this;
		return [{
			xtype:'button',
			text:i18n('i18n.Logcontrol.save'),
			disabled:('view' == me.viewStatus)?true:false,
			scope:me,
			handler:function(button){
				me.commitAction(button);
			}
		},{
			xtype:'button',
			text:i18n('i18n.Logcontrol.b_cance'),
			handler:function(){
				me.parent.close();
			}
		}];
	},
	commitAction:function(button){
		var me = this;
		var basicLoginfo =new ActionSetModel();
		me.getForm().updateRecord(basicLoginfo);
		//校验数据是否合法、完整
		if(!me.validateFormData(basicLoginfo)){
			return;
		}
		button.setDisabled(true);
		var successFn = function(result){
			if(!Ext.isEmpty(result.message)){
				MessageUtil.showInfoMes(result.message);
			}else{
				MessageUtil.showInfoMes(i18n('i18n.Logcontrol.svae_success'));
			}
			me.parent.close();
			//重新执行查询命令
//			Ext.getCmp('LogManagerButtonPanel_id').searchActionResult();
		}
		var failFn = function(result){
			if(!Ext.isEmpty(result.message)){
				MessageUtil.showErrorMes(result.message);
			}else{
				MessageUtil.showErrorMes(i18n('i18n.Logcontrol.save_failed'));
			}
			button.setDisabled(false);
		}
		var param={'basicLoginfo':basicLoginfo.data};
		if('new'==me.handType){
			logActionControl.setlogAction(param,successFn,failFn);
		}else if('update'==me.handType){
			param.basicLoginfo.id = me.record.data.id;
			logActionControl.updateAction(param,successFn,failFn);
		}
	},
	//提交前校验数据是否合法
	validateFormData:function(record){
		var me = this;
		//校验表单是否合法
		if(!me.getForm().isValid()){
			MessageUtil.showErrorMes(i18n('i18n.Logcontrol.m_must_fillForm'));
			return false;
		//当启用预警时，通知人员邮箱地址不允许为空
		}else if(record.get('invalid')&&Ext.isEmpty(record.get('mailPerson'))){
			MessageUtil.showErrorMes(i18n('i18n.Logcontrol.email_must_fill'));
			return false;
		}
		return true;
	}
});