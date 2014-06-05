/**
*  获取传入参数的 value值
* @param {} name 参数
* @return {String}
*/
function queryString(name){
	 var reg = new RegExp(name+"=([0-9a-zA-Z%_\u4e00-\u9fa5\\.]+)");
	if(reg.test(location.search)){
		   return RegExp.$1;
     }
    return "";
}
//工单号id
var complaintId=decodeURIComponent(queryString('complaintId'));
		
Ext.onReady(function() {
		//页面需要加载的数据字典数组
		var keys=[
			    //来电 人类型
				'CALLER_TYPE',
				 //期望时限    
			    'EXPECTED_TIME_LIMIT',
				//工单来源
				'WORK_SINGLE_SOURCE',
				 //优先级别
				'PRIORITY_RATING',
				 //上报类型
				 'REPORT_TYPE',
				 //工单模块处理状态
				'COMPLAINT_PROCESS_STATUS',
			    //解决情况
				'RESOLVE_CASE',
			    'COMPLAINT_RECORD_TYPE',//工单模块反馈类型,
			    //客户满意度
			    'SATISFACTION_DEGREE',
			     // 业务类型
      			'COMPLAINT_BUSINESS_MODEL'
		];
	   //初始数据字典
		initDataDictionary(keys);
		
		Ext.define('jg',{
			extend:'TabContentPanel',
			padding:'10 0 0 0'
		});
		Ext.define('ComplaintPrintWindow',{				
			id:'complaintPrintWindowId',
			extend:'TabContentPanel',
			autoScroll:true
			,title:i18n('i18n.complaint.comp_title_details')
			,complaintBasicFormPanel:null/* 显示基本信息  formPanel */
			,surveySuggestGrid:null   /* 调查建议*/
			,baseHierarchyPanel:null	/*业务类型、业务范围、投诉级别*/
			,complaintFourPanel:null   //第四块
			,complaintBtnFivePanel:null     // 第五块
			,processResultPanel:null/*处理结果Panel */
			,processRecordPanel:null/*处理记录记录Panel */
			,width:905
			,items:null,
			buttons:null,
			initComponent:function(){
				this.buttons = this.getButtons();
				 var me = this;
		      	this.complaintBasicFormPanel = Ext.create('ComplaintBasicFormPanel',{
					'complaint':me.complaint,height:195,width:885
				});
				this.surveySuggestGrid = new Ext.create('SurveySuggestGrid',{'complaint':me.complaint,width:880});
				this.baseHierarchyPanel = Ext.create('BaseHierarchyPanel',{
					isEditable:false,'complaint':me.complaint,width:880,forceSelectionStatus:false
				});
				this.complaintFourPanel = Ext.create('ComplaintFourPanel',{width:880});
				
				{// 通知对象显示
					var searchParams = {
						complaintSearchCondition:{'fid':me.complaint.fid}
					};
					//执行成功
					var successFn = function(response){
						var bulletinValue = "";
						var bulletinList = response.bulletinList;
						for(var i=0;i<bulletinList.length;i++){
							bulletinValue +=  bulletinList[i].bulletinname + ',';
						}
						bulletinValue = bulletinValue.substring(0,bulletinValue.length-1);
						Ext.getCmp('bulletinId').setValue(bulletinValue);
					}
					
					//执行失败
					var failFn = function(response){
						MessageUtil.showErrorMes(response.message);
					}
					DpUtil.requestJsonAjax('searchBulletinListByCompId.action',searchParams,successFn,failFn);
				}
				
				this.processResultPanel = Ext.create('ProcessResultPanel',{
					'complaint':me.complaint,width:880
				});
				this.processRecordPanel = Ext.create('ProcessRecordPanel',{
					'complaint':me.complaint,width:880
				});
				
				this.items = this.getItems();
				
				this.callParent();
			},
	//		loadData:function(){},
			getItems:function(){
				return [	this.complaintBasicFormPanel
							,this.surveySuggestGrid
							,this.baseHierarchyPanel
							,this.processResultPanel
							,this.processRecordPanel
							,this.complaintFourPanel	];
			},
			getButtons:function(){
				var me = this;
				return [{
					text: i18n('i18n.memberView.printThisPage'),
					scope:me,
					handler: function(){
						this.setVisible(false);
						window.print();
					}
				}];
			}
		});
		
		Ext.define('PrintPanel', {				// 整合布局容器
			extend : 'Ext.container.Viewport',
			complaint : null,
			items:null,
			autoScroll:true,
			initComponent : function(){
				this.items = this.getItems();
				this.callParent();
			},
			getItems: function(){
				var ctn = Ext.create('ComplaintPrintWindow',{
					'complaint':this.complaint
				});
				return [ ctn ];
			}
		});
		
		var params = {
			fid:complaintId
		};
				
	     //执行成功
  	    var successFn = function(response){
			if(null==response.complaint){
				MessageUtil.showInfoMes(i18n(i18n('i18n.alert_message_dataNotExist'),function(){
					window.close();
				});
				return;
			}
			// 调用客户工单详情 弹出框
			Ext.create('PrintPanel',{
					'complaint':response.complaint
			}).show();
		}
		//执行失败
		var failFn = function(){
			MessageUtil.showErrorMes(i18n('i18n.alert_message_dataNotExist'));
		}
		//调用查询方法
		DpUtil.requestJsonAjax('searchComplaintAndOther.action',params,successFn,failFn);
})
