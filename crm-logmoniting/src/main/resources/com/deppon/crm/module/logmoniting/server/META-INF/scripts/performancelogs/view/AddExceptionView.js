var ExceptionErrorCodeControl = Ext.create('ExceptionBasicData');
/**
 * 弹出 Exception主窗口
 */
Ext.define('AddExceptionWindow',{
	extend:'PopWindow',
	title:i18n('i18n.Logcontrol.editWindow'),  //'编辑界面',
	handType:null,
	record:null,
	height:280,
	width:400,
	initComponent:function(){
		var me = this;
		me.items = [Ext.create('EditExceptionInfo',{'parent':me,
												'handType':me.handType,
												'record':me.record})];
		this.callParent();
	}
	});
/**
 * 编辑Exception主Panel（新增和修改）
 */
Ext.define('EditExceptionInfo',{
	extend:'BasicFormPanel',
	height:230,
	width:350,
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
				margin:'20 0 0 0',
				width:300
			},
			layout:{
			 	type:'table',    
			 	columns : 1        
			},
			defaultType:'dptextfield',
			items:[
					{
						xtype:'textfield',
						fieldLabel:i18n('i18n.Logcontrol.moduleName'),//模块名称',
						allowBlank:false,
						name:'moduleName'
					},{
						xtype:'textfield',
						fieldLabel:i18n('i18n.Logcontrol.errorCode'),//'异常编码',
						readOnly:me.handType=='new'?false:true,
						allowBlank:false,
						name:'errorCode',
					},{
						xtype:'textarea',
						fieldLabel:i18n('i18n.Logcontrol.exceptionInfo'),//fieldLabel:'异常信息',
						allowBlank:false,
						name:'exceptionInfo',
						height:	60,
						preventScrollbars:false
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
			text:i18n('i18n.Logcontrol.save'),//保存
			disabled:('view' == me.viewStatus)?true:false,
			scope:me,
			handler:function(button){
				me.commitAction(button);
			}
		},{
			xtype:'button',
			text:i18n('i18n.Logcontrol.b_cance'),//取消
			handler:function(){
				me.parent.close();
			}
		}];
	},
	commitAction:function(button){
		var me = this;
		var editExceptionInfo = null;
		var param = null;
		//新增
		if('new'==me.handType){
			var successFn = function(result){
				if(!Ext.isEmpty(result.message)){
					MessageUtil.showInfoMes(result.message);
				}else{
					MessageUtil.showInfoMes(i18n('i18n.Logcontrol.svae_success'));
				}
				me.parent.close();
				//重新执行查询命令
				Ext.getCmp('ExceptionButtonPanel_id').searchExceptionResult();
			}
			var failFn = function(result){
				if(!Ext.isEmpty(result.message)){
					MessageUtil.showErrorMes(result.message);
				}else{
					MessageUtil.showErrorMes(i18n('i18n.Logcontrol.save_failed'));
				}
				button.setDisabled(false);
			}
			editExceptionInfo = new SearchExceptionSetModel()
			me.getForm().updateRecord(editExceptionInfo);
			if(!me.validateFormData(editExceptionInfo)){
				return;
			}
			
			button.setDisabled(true);
			param = {'exceptionErrorCode':editExceptionInfo.data};
			MessageUtil.showQuestionMes(i18n('i18n.Logcontrol.isConfirmInsert'),function(e){//确认新增
				if(e=='yes'){
					ExceptionErrorCodeControl.addExceptionErrorCode(param,successFn,failFn);
				}
				button.setDisabled(false);
			});
		}else if('update'==me.handType){  
			//修改
			//旧的数据
			var oldExceptionInfo = me.parent.record.data;
			//输入框中旧的数据
			var oldModuleName = oldExceptionInfo.moduleName;
			var oldErrorCode = oldExceptionInfo.errorCode;
			var oleExceptionName = oldExceptionInfo.exceptionInfo;
			//输入框中新数据
			var newExceptionInfo = new ExceptionResultModel(oldExceptionInfo);
			me.getForm().updateRecord(newExceptionInfo);
			//校验数据是否进行了修改
			if(newExceptionInfo.data.moduleName==oldModuleName&&newExceptionInfo.data.exceptionInfo==oleExceptionName&&newExceptionInfo.data.errorCode==oldErrorCode){
				MessageUtil.showErrorMes(i18n('i18n.Logcontrol.noUpdate'));//数据未修改
				button.setDisabled(false);
				return;
			}
			if(!me.validateFormData(newExceptionInfo)){
				return;
			}
			button.setDisabled(true);
			var successFn = function(result){
				if(!Ext.isEmpty(result.message)){
					MessageUtil.showInfoMes(result.message);
				}else{
					MessageUtil.showInfoMes(i18n('i18n.Logcontrol.svae_success'));//保存成功
				}
				me.parent.close();
				//重新执行查询命令
				Ext.getCmp('ExceptionButtonPanel_id').searchExceptionResult();
			}
			var failFn = function(result){
				if(!Ext.isEmpty(result.message)){
					MessageUtil.showErrorMes(result.message);
				}else{
					MessageUtil.showErrorMes(i18n('i18n.Logcontrol.save_failed'));//保存失败
				}
				button.setDisabled(false);
			}
    		param = {'exceptionErrorCode':newExceptionInfo.data};
    		MessageUtil.showQuestionMes(i18n('i18n.Logcontrol.isConfirmUpdate'),function(e){//确认修改
    			if(e=='yes'){
    				ExceptionErrorCodeControl.updateExceptionErrorCode(param,successFn,failFn);
    			}
    			button.setDisabled(false);
		});
	}
},
	//提交前校验数据是否为空（新增和修改的输入框不能为空）
	validateFormData:function(record){
		var me = this;
		//校验表单是否合法
		if(!me.getForm().isValid()){
			MessageUtil.showErrorMes(i18n('i18n.Logcontrol.m_must_fillForm'));//请确认数据是否完整
			return false;
		}
		return true;
	}
});