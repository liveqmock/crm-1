 
/** 
 * 待审批/已升级 弹出框
 */
Ext.define('waitApproveHaveUpdateWindow', {
    extend:'PopWindow'
    ,y:0
    ,x:0
	,'complaintData':{
		complaint:null //客户工单信息
		,bulletinList:null   //通报对象
		,paraBasciLevel:null   //业务范围
		,basciLevel:null   //业务类型
	}
	,complaintBasicFormPanel:null/* 显示基本信息  formPanel */
	,baseInfoLevelPanel:null	/*业务类型、业务范围、投诉级别*/
	,processResultPanel:null/*处理结果Panel */
	,complaintFourPanel:null   //第四块
	,surveySuggestGrid:null   /* 调查建议*/
	
	,width:820,height:622
    ,layout: {align:'stretch',type:'vbox'},modal:true
	,title:i18n('i18n.complaint.waitOrder.process.title')
    ,initComponent: function() {
    	//画出工单信息页面。
        var me = this;
        //工单内容panel
      	this.complaintBasicFormPanel = Ext.create('ComplaintBasicFormPanel',{
			'complaint':me.complaintData.complaint
		});
		this.baseInfoLevelPanel = Ext.create('BaseInfoLevelPanel',{
				isEditable:false,'complaint':me.complaintData.complaint,padding:'0 0 0 5',forceSelectionStatus:false
		});
		//处理结果组
		this.processResultPanel = Ext.create('ProcessResultPanel',{
			'complaint':me.complaintData.complaint
		});		
		this.complaintFourPanel = Ext.create('ComplaintHiddenFourPanel');
		{// 通知对象显示
			var searchParams = {
				complaintSearchCondition:{'fid':me.complaintData.complaint.fid}
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
		this.surveySuggestGrid = new Ext.create('SurveySuggestGrid',{'complaint':me.complaintData.complaint,id:'waitApproveComplaintDetaiTwoPanel'});
		
		Ext.applyIf(me,{
	        	buttons:[
					{text:i18n('i18n.complaint.returnToSubmittor')
        	    		,handler:function(){
        	    			Ext.create('ReturnToSubmittorWindow',{complaint:me.complaintData.complaint,windowId:'waitApproveHaveUpdateWindowId'}).show();
        	    	 	}
        	    	 },{
        	    	 	text:i18n('i18n.complaint.btn.finish_process'),handler:function (){
        	    			//待审批
        	    			if((me.complaintData.complaint.prostatus=='WAIT_APPROVAL')){  
        	    				var complaintModel=new ComplaintModel();
        	    				
        	    				var params = {};
        	    				params.complaint=me.complaintData.complaint;
        	    				
        	    				var operateSuccess = function(response) {
        	    					Ext.getCmp('waitApproveHaveUpdateWindowId').close();
									// 调用客户工单详情 弹出框
        	    					MessageUtil.showInfoMes(i18n("i18n.waitApproveHaveUpdate.operate.success"));
        	    					Ext.getCmp('complaintGridId').store.load();
        	    				}
        	    				
								var operateFail = function(response) {
									MessageUtil.showErrorMes(response.message);
								};
        	    				
        	    				var finishProcessWithApproveUrl='finishProcessWithApprove.action';
								DpUtil.requestJsonAjax(finishProcessWithApproveUrl,params,operateSuccess,operateFail);
        	    			}else{ //已升级
        	    				//Ext.create('UpgratedReturnWindow',{complaint:me.complaintData.complaint}).show();
//        	    				var me = this;
								var compWBFinishProcessWindow = Ext.create('CompWBFinishProcessWindow',{
						    		id:'finishProcessWindowId'
									,complaint:me.complaintData.complaint
									,parent:me
									,myClose:function(){
										this.close();//关闭本窗口
										Ext.getCmp('waitApproveHaveUpdateWindowId').close();
										MessageUtil.showInfoMes(i18n("i18n.waitApproveHaveUpdate.operate.success"));
										Ext.getCmp('complaintGridId').store.load();
									}
									,action_url:'finishProcessWithUpgraded.action'
								}).show();
								compWBFinishProcessWindow.setWidth(750);compWBFinishProcessWindow.setHeight(530);
        	    			}
        	    		 }
        	    	 }
					,{text:i18n('i18n.complaintReport.btn_close'),handler:function(){me.close();}}
				]
				,items:[
						me.complaintBasicFormPanel
						,me.baseInfoLevelPanel
						,me.processResultPanel
						,me.complaintFourPanel
						,me.surveySuggestGrid						
				]
	        });
	        me.callParent(arguments);
	        //只有待审批状态下才显示调查建议
			if((me.complaintData.complaint.prostatus=='UPGRADED')){    
				Ext.getCmp('waitApproveComplaintDetaiTwoPanel').hidden=true;
			}
	    }
	    /* 添加滚动条 修改 begin */
		,'closeAction':'destroy'
		,listeners:{
			destroy:function(){
				if(viewport && viewport!=null){
					document.body.scrollLeft=0;
					document.body.scrollTop=0;
					document.getElementsByTagName("html")[0].style.overflowY="hidden";
					document.getElementsByTagName("html")[0].style.overflowX="hidden";
					viewport.doLayout();
				}
			},
			show:function(){
				if(viewport && viewport!=null){
					document.getElementsByTagName("html")[0].style.overflowY="auto";
					document.getElementsByTagName("html")[0].style.overflowX="auto";
					viewport.doLayout();
				}
			}
		}
		/* 添加滚动条 修改 end */	     
});
