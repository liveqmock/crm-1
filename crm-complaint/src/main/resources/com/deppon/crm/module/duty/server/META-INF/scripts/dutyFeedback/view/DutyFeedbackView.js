/**
 * 工单责任反馈页面
 */
var viewport = null;
Ext.onReady(function(){	
	//初始化提示信息条
	Ext.QuickTips.init();
	initDeptAndUserInfo();
	//处理按钮面板
	Ext.define('DutyFeedbackButtonPanel',{
		extend:'NormalButtonPanel',
		border:false,
		initComponent:function(){
			this.items = this.getItems();
			this.callParent();
		},
		getItems:function(){
			var me = this;
			return [
			   {
				xtype:'leftbuttonpanel',
				width:200,
				items:[
				  {//处理
					  xtype:'button',
					  text:i18n('i18n.DutyFeedbackView.handle'),
					  handler:function(btn){
						//显示责任反馈详情操作窗口
					  	loadDutyFeedbackDetailWindow(btn);
					  }
				  }
				]
			   },{
				 xtype:'middlebuttonpanel' 
			   }];
		}
	});
	
	/**
	 * 工单责任反馈待划分责任结果列表
	 */
	Ext.define('DutyFeedBackResultGrid',{
		extend:'SearchGridPanel',
		defaults:{align:'center'},
		autoScroll:true,
		selModel:Ext.create('Ext.selection.CheckboxModel',{mode:'single'}),
		initComponent:function(){ 
			var me = this;
			me.store = Ext.create('DutyFeedbackPagerStore',{autoLoad:true});
			me.columns = [
			    {xtype:'rownumberer',header:i18n('i18n.Duty.resultDutyPanel.rownumberer'),width:40},//序号
			    {header :i18n('i18n.duty.businessModel'),
					dataIndex:'businessModel',
					width:80,
					renderer:function(v){
						return rendererDictionary(v,DataDictionary.COMPLAINT_BUSINESS_MODEL);
					}},
			    {header:i18n('i18n.Duty.resultDutyPanel.voucherNumber'),width:100,dataIndex:'voucherNumber'},//凭证号
			    {header :i18n('i18n.Duty.resultDutyPanel.dealNumber'),dataIndex:'dealNumber',width:120},//处理编号
			    {
			    	header:i18n('i18n.duty.reportType'),dataIndex:'reportType',width:100,//上报类型
			    	renderer:function(value){
			    		return rendererDictionary(value,DataDictionary.REPORT_TYPE);
			    	}
			    },
			    {header :i18n('i18n.Duty.resultDutyPanel.rebindNo'),dataIndex:'rebindNo',width:120},//重复工单码
			    {header :i18n('i18n.Duty.resultDutyPanel.caller'),dataIndex:'caller',width:100},//来电人
			    {header :i18n('i18n.DutyFeedbackView.dutyDept'),dataIndex:'dutyPerName',width:120},//责任部门
			    {
			    	header:i18n('i18n.duty.result.feedtimeLimit'),dataIndex:'dutyDeadLine',width:120,//反馈时限
					renderer:DutyUtil.renderDateTime
			    },
			    {
			    	header :i18n('i18n.Duty.DutyApproval.dutyStatus'),dataIndex:'dutyStatus',width:100,//责任状态
			    	renderer:function(value){
						return DutyUtil.rendererDictionary(value,DataDictionary.DUTY_RESULT_STATUS);
					}
			    },
			    {
			    	header :i18n('i18n.duty.result.surveyResult'),dataIndex:'surveyResult',width:100,//调查结果
			    	renderer:function(value){
						return DutyUtil.rendererDictionary(value,DataDictionary.DUTY_SURVEY_RESULT);
					}
			    },
			    {
			    	header :i18n('i18n.Duty.DutyApproval.DutyAllocationMan'),dataIndex:'appDutyUser',width:100//责任划分人
			    },
			    {
			    	header :i18n('i18n.Duty.DutyApproval.DutyAllocationTime'),dataIndex:'appDutyTime',width:100,//责任划分时间
			    	renderer:DutyUtil.renderDateTime
			    },
			    {
			    	header :i18n('i18n.Duty.resultDutyPanel.reporter'),dataIndex:'reporter',width:100//上报人
			    },
			    {
			    	header :i18n('i18n.Duty.resultDutyPanel.reportTime'),dataIndex:'reportTime',width:150,//上报时间
			    	renderer:DutyUtil.renderDateTime
			    }
		    ];
			me.dockedItems = me.getMyDockedItems();
		    this.callParent(arguments);
		    me.on('itemdblclick', function(view, record) {
		    	loadDutyFeedbackDetailWindow(null);
		    });
	   },
	   	//分页条
		getMyDockedItems :function(){ 
			var me = this;
			return [{
				xtype : 'pagingtoolbar'
				,plugins:[Ext.create('Ext.ux.PageComboResizer')]
				,store : me.store,dock : 'bottom',displayInfo : true
			}];
		}
	});
	
	/*
	 * 新建一个viewport，用于显示工单责任反馈界面
	 * 肖红叶
	 */
	viewport = Ext.create('Ext.Viewport',{
		layout : 'border',
		items:[
	        {//责任反馈处理按钮
	        	xtype:'container',
				region:'north',
				layout:'fit',
				items:[Ext.create('DutyFeedbackButtonPanel',{id:'dutyFeedbackButtonPanelId'})]
			},{//责任反馈界面待反馈工单责任列表
				xtype:'container',
				region:'center',
				layout:'fit',
				items:[Ext.create('DutyFeedBackResultGrid',{id:'dutyFeedBackResultGridId'})]
			}
	    ]
	});
	
	viewport.setAutoScroll(false);	
	viewport.doLayout();
});

/**
 * 加载弹出框-责任反馈详情
 * @param {Botton} btn 操作按钮
 */
function loadDutyFeedbackDetailWindow(btn){
	//显示责任反馈详情操作窗口
  	var dutyFeedbackDetailWindow = Ext.getCmp("dutyFeedbackDetailWindowId");
	if(!dutyFeedbackDetailWindow){
		dutyFeedbackDetailWindow = Ext.create('DutyFeedbackDetailWindow',{id:'dutyFeedbackDetailWindowId'});
	}
	//加载数据
	CommonStore.prototype.loadDataToDutyDetail(btn,'dutyFeedBackResultGridId',function(json,record){
		dutyFeedbackDetailWindow.show();
		Ext.getCmp('feedback_dutyClaimBtn').setDisabled(true);/*责任认领-不可用*/
		var feedbackBtn = Ext.getCmp('feedback_feedbackBtn');
		feedbackBtn.setDisabled(true);/*责任反馈-不可用*/
		
		if(processBTNIsValid(json.dutyResult) === true){//按钮可用
			Ext.getCmp('feedback_dutyClaimBtn').setDisabled(false);/*责任认领*/
			
			var dutyDepartmentCC = Ext.String.trim(record.get('dutyDepartmentCC'));
			if(Ext.isEmpty(dutyDepartmentCC)){return;}
			if(dutyDepartmentCC === '0' || dutyDepartmentCC === '1'){
				//只有特殊部门 或者 三个经营本部 的部门或个人才可以反馈
				feedbackBtn.setDisabled(false);/*责任反馈*/
			}
		}
	});
};

/**
 * 判断处理按钮是否可用
 * true 可用，false 不可用
 */
function processBTNIsValid(resultList){
	var flag = false;
	for(var i=0;i<resultList.length;i++){
		var result = resultList[i];
		if(
			result.dutyStatus==='WAITING_FEEDBACK'/*责任待反馈*/ ||
			result.dutyStatus==='DUTY_TRUNING_BACK'/*责任退回*/){
			
			if(result.divideType=='department' && result.dutyPerId==User.deptId){
				flag = true;break;
			}else if(result.divideType=='employee'&&result.dutyPerId==User.empId){
				flag = true;break;
			}
		
		}
	}
	return flag;
};

