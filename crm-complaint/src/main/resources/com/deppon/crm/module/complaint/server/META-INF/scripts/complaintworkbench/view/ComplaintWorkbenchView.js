//待办工单界面
var complaintDataControl = (CONFIG.get("TEST"))? new ComplaintDataTest():new ComplaintData();

 //定义分页条以及相关的checkbox。
Ext.define('Ext.ux.PageComboResizer', {
	extend:Object
	  ,pageSizes: [5, 10, 15, 20, 25, 30, 50]
	  ,prefixText: i18n('i18n.complaint.pager_prefixText')//'每页显示 '
	  ,postfixText: i18n('i18n.complaint.pager_postfixText')//'记录'

	  ,constructor: function(config){
		 //复制config到当前对象
	    Ext.apply(this, config);
	    this.callParent(arguments);
	  }
	  ,init : function(pagingToolbar) {
	    var ps = this.pageSizes;
	    var combo = Ext.create('Ext.form.ComboBox',{
	      typeAhead: true
	      ,triggerAction: 'all'
	      ,lazyRender:true
	      ,mode: 'local'
	      ,width:45
	      ,store: ps
	      ,listeners: {
	    	  //定义combox被选择后的相关动作，重新设置页面。
		        select: function(c, r, s){
		          	pagingToolbar.store.pageSize =r[0].data.field1;
		          	pagingToolbar.store.loadPage(pagingToolbar.store.currentPage);
		        }
	      }
	    });
	    Ext.iterate(this.pageSizes, function(ps) {
	      if (ps==pagingToolbar.store.pageSize) {
	        combo.setValue (ps);
	        return;
	      }
	    });
		//将控件放到刷新控件的后面
	 	var inputIndex  =  pagingToolbar.items.indexOf(pagingToolbar.items.get('refresh'));
	    pagingToolbar.insert(++inputIndex,'-');
	    pagingToolbar.insert(++inputIndex, this.prefixText);
	    pagingToolbar.insert(++inputIndex, combo);
	    pagingToolbar.insert(++inputIndex, this.postfixText);
	    pagingToolbar.on({
	      beforedestroy: function(){
	        combo.destroy();
	      }
	    });
	  }
	});


/**
 * 待办工单总 panel（装载显示页数、待办工单列表和处理按钮的总panel）
 */
Ext.define('ComplaintPanel',{
	extend:'BasicPanel'
	,complaintGrid:null  //查询结果
	,complaintData:null//数据Data
	,layout:{type:'vbox',align:'stretch'}
	,items:null
	,autoHeight:true
	,initComponent:function(){
		var me = this;
		
		//初始化选中的selectModel
		var chooseSelModel=Ext.create('Ext.selection.CheckboxModel',{mode:'SINGLE',id:'chooseSelModelId'});	
		
		//待办工单列表
		me.complaintGrid = Ext.create('ComplaintGrid',{
			    id:'complaintGridId'
				,'complaintData':me.complaintData
				//,height:415
				,selModel:chooseSelModel  //设置selectmodel
			});
			
		me.complaintGrid.on('cellclick', function(thiz, row, col, record) {
					var fieldName=thiz.getHeaderCt().getHeaderAtIndex(col).dataIndex;
			   				if(fieldName=="recomcode"){//重复工单码
			   					var complaintRecord = record.data;
					   		    if(!DpUtil.isEmpty(complaintRecord.recomcode)){//加载重复工单码查询
									//查询重复工单 点击事件
									Ext.create('RepeatComplaintWindow',{
										'complaint':complaintRecord
									}).show();
								}
			   					
			   				}
							if (fieldName=='compman') {//显示来电人信息
								show_comp_detailsWin(record.get('fid'));/* 调用工单详情-弹出框 */
							}
				});										
	
		me.items = [{
					  xtype:'basicpanel'
					 ,flex:1
					 ,items:[me.complaintGrid]
					}];
		this.callParent(arguments);
	}
});




/**
 * 待办工单列表
 */
Ext.define('ComplaintGrid',{
	extend:'SearchGridPanel'
	,id : 'complaintGridId'
	,selModel : null //选中的selectModel
	,selType:'rowmodel'   //行模型选择
	,listeners: {
	    scrollershow: function(scroller) {
	    	if (scroller && scroller.scrollEl) {
	    			scroller.clearManagedListeners(); 		    			
	    			scroller.mon(scroller.scrollEl, 'scroll', scroller.onElScroll, scroller); 
		    }
		   }
	}		
	,loadCondition:{
		showPager:true // 显示分页
	    ,showCheckbox:true // 显示checkbox
	}  //查询条件
	,complaintData:null//数据Data
	
	,initComponent:function(){
		var me = this;
   		me.store=Ext.create('WaitComplaintStore');		
    			
   		me.columns = me.getColumns();
   		this.loadShowControl();//动态加载相关控件
		this.callParent();
		
	}
	,loadShowControl:function(){
		var me = this;
		if(me.loadCondition && me.loadCondition.showPager){
			me.dockedItems = me.getMyDockedItems();
			
		}
		if(me.loadCondition && me.loadCondition.showCheckbox){
			me.selModel = new Ext.selection.CheckboxModel({mode:'single'});
		}
	}

	
	,getColumns:function(){
		var me = this;
		return [
			{xtype:'rownumberer',header:i18n('i18n.complaint.serial_number'),width:40},
			{dataIndex:'businessModel',header:i18n('i18n.complaint.businessType')
				,renderer:function(value){
					return rendererDictionary(value,DataDictionary.COMPLAINT_BUSINESS_MODEL);
				}
			},
			{header:i18n('i18n.complaint.comp_bill'),align:'left',width:70,dataIndex:'bill'}  //凭证号
			,{header:i18n('i18n.complaint.comp_compman'),align:'left',width:70,dataIndex:'compman', 
				renderer:function(value){
				 	return '<a href="javascript:void(0);">'+value+'</a>';
				 }	
			}			
			,{header:i18n('i18n.complaint.comp_dealbill'),align:'left',width:80,dataIndex:'dealbill'}
			,{header:i18n('i18n.complaint.comp_recomcode'),align:'left',width:75,dataIndex:'recomcode',
				renderer:function(value){
				 	return '<a href="javascript:void(0);">'+value+'</a>';
				 }	
			}
			,{header:i18n('i18n.complaint.comp_reportType'),align:'left',width:60,dataIndex:'reporttype',
					renderer:function(value){
				  					return rendererDictionary(value,DataDictionary.REPORT_TYPE);
				  	}}
			,{header:i18n('i18n.complaint.comp_complaincust'),align:'left',width:60,dataIndex:'complaincust'}
			,{header:i18n('i18n.complaint.comp_complevel'),align:'left',width:60,dataIndex:'complevel',
				renderer:function(value){
			  		return rendererDictionary(value,DataDictionary.COMPLAINT_LEVEL);
				}	
			}
			,{header:i18n('i18n.complaint.comp_prostatus'),align:'left',width:90,dataIndex:'prostatus',
				renderer:function(value){
				  		return rendererDictionary(value,DataDictionary.COMPLAINT_PROCESS_STATUS);
				 }	
			}
			,{header:i18n('i18n.complaint.comp_dealman'),align:'left',width:55,dataIndex:'operatoername'}
			,{header:i18n('i18n.complaintVerifyTask.processTime'),align:'left',width:115,dataIndex:'lastupdatetime',
				renderer:function(value) {
									if(value != null){
										return Ext.Date.format(new Date(value), 'Y-m-d H:i');
									}else{
										return null;
									}
								}
			}
	
			
		];
	}
		
	//引用分页条
	,getMyDockedItems :function(){ 
		var me = this;
		return [{
			xtype : 'pagingtoolbar'
			,plugins:[Ext.create('Ext.ux.PageComboResizer')]
			,store : me.store
			,dock : 'bottom'
			,displayInfo : true
		}];
	}
});


//处理工单按钮
Ext.define('solveOrderButtonPanel',
		{	
		extend:'NormalButtonPanel', 
		items:null,
		initComponent:function(){
			this.items = this.getItems();	
			this.callParent();	
		},
		getItems:function(){
			var me = this;	
			return [{
				xtype:'leftbuttonpanel',
				items:[{xtype:'button',text:i18n('i18n.complaint.process_complaint_title'),handler:function(){
					var selectedOrder = Ext.getCmp('complaintGridId').getSelectionModel();
					if (selectedOrder.getSelection().length > 0) {
						this.action = 'update';
						var record = selectedOrder.getSelection()[0];
						var params = {
								fid:record.get('fid')
							};
						//执行成功
						var successFn = function(response){
							if(('WAIT_APPROVAL'==response.complaint.prostatus) || ('UPGRADED'==response.complaint.prostatus)){
								// 调用待审批/已升级 弹出框弹出框
								response.otherComplint['complaint'] = response.complaint;
								Ext.create('waitApproveHaveUpdateWindow',{
											 id:'waitApproveHaveUpdateWindowId',
											'parent':me,
											'complaintData':response.otherComplint
								}).show();
							}else{
								var prostatus = record.get('prostatus');/*处理状态*/
								Ext.create('ComplaintWorkbenchProcessWindow',{
									id:'ComplaintWorkbenchProcessWindow'
									,'complaint':response.complaint
								}).show();
							}
						}
						
						//执行失败
						var failFn = function(){
							MessageUtil.showErrorMes(i18n('i18n.alert_message_dataNotExist'));
						}
						if((record.get('prostatus')=='WAIT_APPROVAL') || (record.get('prostatus')=='UPGRADED')){
							DpUtil.requestJsonAjax('searchComplaintAndOther.action',params,successFn,failFn);
						}else{
							DpUtil.requestJsonAjax('searchComplaintBasic.action',params,successFn,failFn);
						}
						
					} else {
						MessageUtil.showMessage(i18n('i18n.complaint.msg_data_select'));
					}
				}}]
			},{
				xtype:'middlebuttonpanel'
			}]
	}});
var viewport = null;/* 视图容器 */
Ext.onReady(function(){
	
	//页面需要加载的数据字典数组
	var keys=[
        //客户工单查询条件 
	    'COMPLAINT_FIELD'
		,'TASKPROPERTIES_TYPE'	/* 任务属性 */
		,'COMPLAINT_LEVEL'	/* 投诉级别 */
		,'COMPLAINT_LINK'	/* 业务环节 */
	    //上报类型
		,'REPORT_TYPE'
		//工单模块处理状态
		,'COMPLAINT_PROCESS_STATUS'
        //期望时限    
		,'EXPECTED_TIME_LIMIT'
	    //工单来源
	    ,'WORK_SINGLE_SOURCE'
	    //优先级别
	    ,'PRIORITY_RATING'
		,'COMPLAINT_RECORD_TYPE'//工单模块反馈类型
		,'RESOLVE_CASE'//解决情况
			    //客户满意度
		,'SATISFACTION_DEGREE'	
		,'PROCESS_TYPE'/*处理类型*/
         // 客户类型
      	,'CUSTOMER_TYPE'
  		// 目标级别',目前级别,客户等级',会员等级
  		,'MEMBER_GRADE'	
      	,'DELAY_APPLY'//延时天数
		  //来电 人类型
		 ,'CALLER_TYPE',
		  // 业务类型
      	'COMPLAINT_BUSINESS_MODEL'
  	]
	//初始数据字典
	initDataDictionary(keys);
	

	

	//初始化页面控件	
	viewport = Ext.create('Ext.container.Viewport', {
		layout : 'border','autoScroll':true,
		items : [
			 Ext.create('solveOrderButtonPanel',{region:'north'})
			,Ext.create('ComplaintPanel',{region:'center','complaintData':complaintDataControl})
			
		]
	});	
	viewport.setAutoScroll(false);
	viewport.doLayout();
	
});