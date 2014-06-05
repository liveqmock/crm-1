/**
 * 会员归属部门变更window
 */
Ext.define('ChangeMemberDeptWin',{
	extend:'PopWindow',
	title:i18n('i18n.ChangeMemberDeptView.t_deptChange'),
	items:null,
	fbar:null,
	width:570,
	height:210,
	layout:'fit',
	memberData:null, //data层  new MemberManagerData()
	changeMemberDeptRecord:null,    //会员部门变更信息 modle
	viewStatus:null,      //"NEW"为新增，"UPDATE"为修改，"VIEW"查看
	operateGrid:null,
	changeMemberDeptForm:null,
	initComponent:function(){
		var me = this;
		me.changeMemberDeptForm = Ext.create('ChangeMemberDeptForm',{'viewStatus':'NEW','changeMemberDeptRecord':me.changeMemberDeptRecord});
		me.items = [me.changeMemberDeptForm];
		me.fbar = me.getFbar();
		this.callParent();
	},
	getFbar:function(){
		var me = this;
		return [{
			xtype:'button',
			text:i18n('i18n.ManualRewardIntegralEditView.b_ensure'),
			hidden:('VIEW' == me.viewStatus)?true:false,
			scope:me,
			handler:me.commitChangeMemberDept
		},{
			xtype:'button',
			text:i18n('i18n.ManualRewardIntegralEditView.b_cance'),
			handler:function(){
				me.close();
			}
		}];
	},
	//确定 变更部门
	commitChangeMemberDept:function(button){
		var me = this;
		MessageUtil.showQuestionMes(i18n('i18n.contractEditView.createWorkflowNotice'),function(e){
			if('yes' == e){
				var form = me.down('form').getForm();
				var reason = form.findField('reason').getValue();
				if (!Ext.isEmpty(reason) && reason.length > 100){
					MessageUtil.showMessage(i18n('i18n.ChangeMemberDeptView.m_max100'))
					return;
				}
				if(!form.isValid()){
					MessageUtil.showMessage(i18n('i18n.ChangeMemberDeptView.m_error'))
					return;
				}
				button.setDisabled(true);
				form.updateRecord(me.changeMemberDeptRecord);
				var params = {};
				params.changeMemberDept = me.changeMemberDeptRecord.data;
				//回调函数
				var successFn = function(resulte){
					var workFlowNum = resulte.workFlowNum;
					MessageUtil.showInfoMes(i18n('i18n.ChangeMemberDeptView.m_deptChangeSucess')+workFlowNum);
					me.operateGrid.store.loadPage(1);
					me.close();
					button.setDisabled(false);
				};
				var failFn = function(resulte){
					MessageUtil.showErrorMes(resulte.message);
					button.setDisabled(false);
				};
				//确定变更部门操作
				me.memberData.changeMemberDept(params,successFn,failFn);
			}else{
				button.setDisabled(false);
			}
		},function(){
			button.setDisabled(false);
		});
	}
});
/**
 * 变更部门form
 */
Ext.define('ChangeMemberDeptForm',{
	extend:'BasicFormPanel',
	layout:{
		type:'table',
		columns:2
	},
	changeMemberDeptRecord:null,//会员部门变更数据 "ChangeMemberDeptModel"
	viewStatus:null,      //"NEW"为新增，"UPDATE"为修改，"VIEW"查看
	defaultType: 'dptextfield',
	initComponent:function(){
		var me = this;
		me.defaults = me.getDefaults();
		me.items = me.getItems();
		this.callParent();
		me.loadMemberChangeDeptDate();
	},
    getItems: function(){
    	var me = this;
	    return [{
	    	colspan:2,
	        fieldLabel: i18n('i18n.ScatterCustManagerView.memberNo'),
	        name: 'memberNumber'
	    },{
	        fieldLabel: i18n('i18n.ChangeMemberDeptView.d_currentlyDept'),
	        name: 'fromDeptName'
	    },{
			fieldLabel:'目标归属部门',
			name:'toDeptName'
		},{
	    	colspan:2,
			xtype:'textareafield',
			fieldLabel:i18n('i18n.ChangeMemberDeptView.d_changeReason'),
			name:'reason',
			maxLength : 100,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',100),
			width : 520,
			height:60,
	        readOnly:('VIEW' == me.viewStatus)?true:false
		},{
			xtype:'hiddenfield',
			name:'memberId'//会员Id
		},{
			xtype:'hiddenfield',
			name:'fromDeptId'//变更前部门Id
		},{
			xtype:'hiddenfield',
			name:'toDeptId'//变更后部门Id
		}];
	},
	getDefaults:function(){
		var me = this;
		return {
			labelWidth : 100,
			width : 260,
	        readOnly:true
		};
	},
	//加载变更部门数据
	loadMemberChangeDeptDate:function(){
		var me = this;
		if(me.changeMemberDeptRecord == null){
			me.changeMemberDeptRecord = Ext.create('ChangeMemberDeptModel');
		}
		if('NEW' == me.viewStatus){
			me.changeMemberDeptRecord.set('toDeptId',top.User.deptId);
			me.changeMemberDeptRecord.set('toDeptName',top.User.deptName);
		}
		me.loadRecord(me.changeMemberDeptRecord);
	}
});


