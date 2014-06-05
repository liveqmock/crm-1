//定义一个全局变量，用以标记当前登陆的用户角色
var dutyApprovalRole = null;
/**
 * 责任反馈记录表格
 */
Ext.define('DutyFeedbackRecordOperationGrid',{
	extend:'PopupGridPanel',
	title:i18n('i18n.Duty.DutyApproval.FeedbackRecord'),
	defaults:{
		align:'center'
	},
	autoScroll:true,
	initComponent:function(){ 
		var me = this;
		var store = Ext.create('DutyFeedbackRecordListStore',{id:'dutyFeedbackRecordListStoreId'});	
		store.on({
			load:function(store,records){
				store.each(function(record){
					if(record.get('status')== 'APPROVALING'){
						Ext.getCmp('modifyDutyAllocationBtn').enable();	
					}
				});
			}
		});
		me.store = store;
		me.columns = [
		    {//序号
		    	xtype:'rownumberer',
				header:i18n('i18n.duty.serial_number'),
				width:40
		    },
		    {//操作
				header:i18n('i18n.Duty.DutyApproval.Operation'),
				dataIndex:'',
				width:120,
				renderer:function(value, cellmeta, record, rowIndex, columnIndex,store){
					var disabled = true;
					
					var selection = Ext.getCmp('dutyApprovalResultGridId').getSelectionModel().getSelection();
					//被选中的审批记录的反馈ID
					var selectFeedbackId = Ext.String.trim(selection[0].get('feedbackId'));
					//验证是否为当前选中的审批记录
					if(!Ext.isEmpty(selectFeedbackId) &&　selectFeedbackId === record.get('feedbackId')){
						if(
							Ext.String.trim(record.get('status')) === 'WAITING_APPROVAL' //当前责任状态为 责任待审批
							&& !Ext.isEmpty(dutyApprovalRole) // 角色ID存在
							&& (
								dutyApprovalRole === i18n('i18n.Duty.DutyApproval.CallerRoleId') //400质检员
								|| dutyApprovalRole === i18n('i18n.Duty.DutyApproval.BusCallerRoleId') //事业部质检员
							)
						){disabled = false;}
					}
		    		
					var agreeStr = '<button type="button" onclick="callerAgree(\'{feedbackId}\',\'{detailId}\',\'{dutyId}\''
									+')" {disabled}>{i18n_Agree}</button>';
					var returnBackStr = '<button type="button" onclick="callerDisagree(\'{feedbackId}\',\'{detailId}\',\'{dutyId}\''
									+')" {disabled}>{i18n_ReturnBack}</button>';
									
					var temp = new Ext.Template(agreeStr +'&nbsp;&nbsp;'+ returnBackStr);
					return temp.applyTemplate({
						'feedbackId':record.get('feedbackId'),'detailId':record.get('detailId'),'dutyId':record.get('dutyId'),
						'i18n_Agree':i18n('i18n.Duty.DutyApproval.Agree'),
						'i18n_ReturnBack':i18n('i18n.Duty.DutyApproval.ReturnBack'),
						'disabled':disabled === true?'disabled="disabled"':''
					});
				}
		    },{//反馈人
				header:i18n('i18n.Duty.DutyApproval.FeedbackManName'),
				dataIndex:'feedUserName',
				width:80
		    },{ //反馈部门
				header :i18n('i18n.Duty.DutyApproval.FeedbackDeptName'),
				dataIndex:'feedDeptName',
				width:100
		    },{//反馈时间
				header :i18n('i18n.Duty.DutyApproval.FeedbackTimeStart'),
				dataIndex:'feedBackTime',
				renderer:DutyUtil.renderDateTime,
				width:140
		    },{//责任反馈内容
				header:i18n('i18n.Duty.DutyApproval.FeedbackContext'),
				dataIndex:'describe',
				renderer:function(value){
  					if(!Ext.isEmpty(value)){
 						return '<span data-qtip="'+value+'">'+value+'</span>';
 	            	}
  				},
				width:120
		    },{ //附件
				header :i18n('i18n.Duty.DutyApproval.Attachment'),
				dataIndex:'haveFeedAttach',
				width:100,
				renderer:function(value,node,record){
					if(value === 'Y'){
						var feedbackId = record.get('feedbackId');
						return '<button type="button" onclick="watchFeedbackAttatch(\''+feedbackId+'\')">'+
						i18n('i18n.Duty.DutyApproval.AttachmentDetail')+'</button>'; 
					}
					else if(value === 'N'){
						return i18n('i18n.Duty.DutyApproval.NoAttachment');
					}
					else{
						return i18n('i18n.Duty.DutyApproval.NoAttachment');
					}
				}
		    },{//责任状态
				header :i18n('i18n.Duty.DutyApproval.dutyStatus'),
				dataIndex:'status',
				width:100,
				renderer:function(value){
					return DutyUtil.rendererDictionary(value,DataDictionary.DUTY_RESULT_STATUS);
				}
		    },{ //呼叫中心审批人
				header :i18n('i18n.Duty.DutyApproval.CallerApprover'),
				dataIndex:'callCenUser',
				width:120
		    },{//呼叫中心审批意见
				header :i18n('i18n.Duty.DutyApproval.CallerApprovalOpinion'),
				dataIndex:'callCenDescision',
				width:120,
				renderer:function(value){
  					if(!Ext.isEmpty(value)){
 						return '<span data-qtip="'+value+'">'+value+'</span>';
 	            	}
  				}
		    },{//呼叫中心审批时间
				header :i18n('i18n.Duty.DutyApproval.CallerApprovalTime'),
				dataIndex:'callcenterTime',
				renderer:DutyUtil.renderDateTime,
				width:150
		    }
	    ];
    this.callParent();
   }
});