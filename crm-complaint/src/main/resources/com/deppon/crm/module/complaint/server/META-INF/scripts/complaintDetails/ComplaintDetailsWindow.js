/* 弹出详情 */
function show_comp_detailsWin(cmpId,callBack){
	var compDetailsWin = Ext.create('ComplaintDetailsWindow',{
		'compId':cmpId,
		'myCallBack':callBack
	}); 
	compDetailsWin.show();
	return compDetailsWin;
}
/** 
 * 查询详情弹出框 声明
 */
Ext.define('ComplaintDetailsWindow', {
    extend: 'PopWindow'
    ,y:5,x:1,width:820,height:785
    ,id:'complaintDetailsWindowId'
	,title:i18n('i18n.complaint.comp_title_details')
	,compId:''//工单ID
    ,initComponent: function() {
    	var me  = this;
    	var url = '../complaint/complaintDetails.action?compId='+me.compId;
        Ext.applyIf(me,{
        	buttons:[
				{text:i18n('i18n.complaint.btn_print')/*打印*/,handler:function(){
					var url='/crm/complaint/complaintDetailIndex.action?'+
									'complaintId='+me.compId;;
					var splashWin = window.top.open(url,'x','');

				}}
				,{text:i18n('i18n.complaintVerifyTask.backVisitRegister'),id:'verifyTaskRegisterId'
	    			,hidden:true,
	    			handler:function(btn){
	    				//工单回访专用 - 回访登记来源 ComplaintVerifyTaskView.js
						compVerifyRegister_btnClick(btn);
					}
				}, 
					
				{text:i18n('i18n.complaintTask.btnReturnToProcess')/*退回处理*/,id:'btnReturnToProcess',
					hidden: true
				},
					  		
				{text:i18n('i18n.complaintTask.btnFeedbackRegister')/*反馈登记*/,
					 id:'btnFeedbackRegister',hidden : true
				},
				{text:i18n('i18n.complaintTask.btnApplyDelay')/*申请延时*/,id:'btnApplyDelay',
					hidden : true
				}
				
				,{text:i18n('i18n.complaintReport.btn_close'),handler:function(){me.destroy();}}
			]
        	,html:'<iframe src="'+url+'" style="height:100%;width:100%;" frameborder="0"></iframe>'
        });
        me.callParent(arguments);
        
        
        
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
		show:function(win){
			//创建回调函数调用
			if(win.myCallBack){win.myCallBack();}
			if(viewport && viewport!=null){
				document.getElementsByTagName("html")[0].style.overflowY="auto";
				document.getElementsByTagName("html")[0].style.overflowX="hidden";
				viewport.doLayout();
			}
		}
	}
	/* 添加滚动条 修改 end */
});