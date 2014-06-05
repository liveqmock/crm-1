var ExceptionErrorCodeControl = Ext.create('SynchronizeLogData');
/**
 * 弹出 Exception主窗口
 */
Ext.define('UpdSynchronizeLogWindow',{
	extend:'PopWindow',
	title:i18n('i18n.Logcontrol.editWindow'),  //'编辑界面',
	record:null,
	height:280,
	width:550,
	initComponent:function(){
		var me = this;
		me.items = [Ext.create('EditSynLogInfo',{'parent':me,
												'record':me.record})];
		this.callParent();
	}
	});
/**
 * 编辑Exception主Panel（新增和修改）
 */
Ext.define('EditSynLogInfo',{
	extend:'BasicFormPanel',
	height:230,
	width:500,
	layout:'fit',
    items:null,
    record:null,//数据data
    fbar:null,
    initComponent:function(){
    	var me = this;
    	me.items = [{
    		xtype:'fieldset',
			defaults:{
				labelWidth:85,
				margin:'20 10 20 10',
				width:220
			},
			layout:{
			 	type:'table',    
			 	columns : 2        
			},
			defaultType:'dptextfield',
			items:[
					{
						xtype:'textfield',
						readOnly:true,
						fieldLabel:i18n('i18n.Logcontrol.tableName'),//表名TABLE_NAME
						name:'tableName'
					},{
						xtype:'textfield',
						fieldLabel:i18n('i18n.Logcontrol.keyWord'),//关键字KEY_WORD
						name:'keyWord',
						readOnly:true
					},{
						xtype:'combo',
						fieldLabel:i18n('i18n.Logcontrol.handleType'),//HANDLE_TYPE
						name:'handleType',
						store:{fields:['abbr','name'],
			    			data:[
			    			   {'abbr':'M','name':'M'},
			    			   {'abbr':'N','name':'N'}
			           	       ]
			    		
				    	},
				    	queryMode:'local',
				    	//value:'N',         //默认值
				    	forceSelection:true,
				    	displayField:'name',
						valueField:'abbr'
					},{
						xtype:'combo',
						fieldLabel:i18n('i18n.Logcontrol.synStatus'),//状态 STATUS
						name:'synStatus',
			        	store:{fields:['abbr','name'],
			        			data:[
			        			   {'abbr':'U','name':'U'},
			        			   {'abbr':'D','name':'D'}
			               	       ]
			        		
			        	},
			        	queryMode:'local',
			        	//value:'U',         //默认值
			        	forceSelection:true,
			        	displayField:'name',
						valueField:'abbr'
					}
			 ]
    	}];
    	me.fbar = me.getFbar();
     	this.callParent();
    },
    getFbar:function(){
		var me = this;
		return [{
			xtype:'button',
			text:i18n('i18n.Logcontrol.save'),//保存
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
		var editSynLogInfo = null;
		var param = null; 
			//修改
			//旧的数据
			var oldSynLogInfo = me.parent.record.data;
			//输入框中旧的数据
			var oldHandleType = oldSynLogInfo.handleType;
			var oldSynStatus = oldSynLogInfo.synStatus;
			//输入框中新数据
			var newSynLogInfo = new SynchronizeLogInfoModel(oldSynLogInfo);
			me.getForm().updateRecord(newSynLogInfo);
			//校验数据是否进行了修改
			if(newSynLogInfo.data.handleType==oldHandleType&&newSynLogInfo.data.synStatus==oldSynStatus){
				MessageUtil.showErrorMes(i18n('i18n.Logcontrol.noUpdate'));//数据未修改
				button.setDisabled(false);
				return;
			}
			if(!me.validateFormData(newSynLogInfo)){
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
				Ext.getCmp('SynLogButtonPanel_id').searchSynLogResult();
			}
			var failFn = function(result){
				if(!Ext.isEmpty(result.message)){
					MessageUtil.showErrorMes(result.message);
				}else{
					MessageUtil.showErrorMes(i18n('i18n.Logcontrol.save_failed'));//保存失败
				}
				button.setDisabled(false);
			}
    		param = {'synchronizeLogInfo':newSynLogInfo.data};//传递的参数
    		MessageUtil.showQuestionMes(i18n('i18n.Logcontrol.confireUpd'),function(e){//确认修改
    			if(e=='yes'){
    				ExceptionErrorCodeControl.updateSynLog(param,successFn,failFn);
    			}
    			button.setDisabled(false);
		});
	
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