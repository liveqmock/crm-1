/**
 * 始发网点与受理部门基础资料总 panel（装载显示页数、关系列表和处理按钮的总panel）
 */
Ext.define('LadingStatDepartPanel',{
	extend:'BasicPanel'
	,ladingStatDepartGrid:null  //查询结果
	,layout:{type:'vbox',align:'stretch'}
	,items:null
	,bussinessDeptStore:null    //业务部门的store(始发网点和受理部门)
	,acceptDeptStore:null
	,ifReceiveStore:null        //是否接货
	,orderResourceStore:null    //订单来源store
	,initComponent:function(){
		var me = this;
		
		//初始化选中的selectModel
		var chooseSelModel=Ext.create('Ext.selection.CheckboxModel',{mode:'SINGLE',id:'chooseSelModelId',checkOnly:true,allowDeselect:true});	
	
		this.bussinessDeptStore=Ext.create('StartNetStore'
				,{ autoLoad:true
				  ,listeners:{
					beforeload:function(store, operation, eOpts){
						Ext.apply(operation,{
							params : {
										'startNetDeptName':Ext.getCmp('startNetCMD').getRawValue()
									 }
						});	
					}
				  }		
				});

		this.acceptDeptStore=Ext.create('StartNetStore'
				,{ autoLoad:true
				  ,listeners:{
					  beforeload:function(store, operation, eOpts){
						  Ext.apply(operation,{
							  params : {
								  		'startNetDeptName':Ext.getCmp('acceptDeptCMD').getRawValue()
							  		   }
						  });	
					  }				
				  }		
				});				
		
		//始发网点与受理部门基础资料列表
		me.ladingStatDepartGrid = Ext.create('LadingStatDepartGrid',{
			    id:'ladingStatDepartGridId'
			    ,flex:1
				,selModel:chooseSelModel  //设置selectmodel
			});
		
		me.items = me.getItems();
		this.callParent(arguments);
	}	
	
	,getItems:function(){
		var me=this;
		return [
				 {	    xtype:'basicsearchformpanel',
						layout:{
							type:'table',
							columns:3
						},
						defaultType:'combobox',
						defaults:{
							labelWidth:70,
							width:210
						},
						items:[
						 {
							fieldLabel:i18n('i18n.ladingstation.beginDept')
						   ,id:'startNetCMD'
						   ,xtype: 'combo'
						   ,store:me.bussinessDeptStore
						   ,displayField:'deptName'
						   ,valueField:'standardCode'
						   ,queryParam:'startNetDeptName'
						   ,typeAhead: false
						   ,hideTrigger:false 
						   ,minChars:1
						 }
						,{
							fieldLabel:i18n('i18n.ladingstation.acceptDept')
							,id:'acceptDeptCMD'
							,xtype: 'combo'
							,store:me.acceptDeptStore
							,displayField:'deptName'
							,valueField:'standardCode'
							,typeAhead: false
							,hideTrigger:false 
							,autoSelect:true
							,minChars:1							
						 }
						,{
							fieldLabel:i18n('i18n.ladingstation.orderSource')
							,id:'orderResourceCMD'
							,scope:me
							,store:getDataDictionaryByName(DataDictionary,'CONFIG_ORDER_SOURCE')
							,queryMode: 'local'
							,displayField:'codeDesc'
							,valueField:'code'									
						 }
						,{
							fieldLabel:i18n('i18n.ladingstation.ifReceive')
							,id:'ifReceivedCMD'
							,scope:me
							,store:getDataDictionaryByName(DataDictionary,'CONFIG_ORDER_RECEIVE_GOODS')
							,queryMode: 'local'
							,displayField:'codeDesc'
							,valueField:'code'									
						 }
						,{
							xtype:'datefield',name:'reportTimeStart',width:210,format:'Y-m-d',fieldLabel:i18n('i18n.big_small_city.createTime')
							,id:'dateStartCMD'
						}
						,{
							xtype:'datefield',name:'reportTimeEnd',width:210,fieldLabel:'--'
							,format:'Y-m-d'
							,id:'dateEndCMD'
						}]		
					}
				   ,{
						xtype:'basicpanel',
						layout:'fit',
						height:36,
						region:'center',
						items:[Ext.create('EditButtonPanel')]
				    }
				            
				   ,me.ladingStatDepartGrid];
		
	}
	
});


//定义分页条以及相关的checkbox。
Ext.define('Ext.ux.PageComboResizer', {
	extend:Object
	  ,pageSizes: [5, 10, 15, 20, 25, 30, 50]
	  ,prefixText: i18n('i18n.util.pager_prefixText')
	  ,postfixText:i18n('i18n.util.pager_postfixText')

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
 * 关系列表
 */
Ext.define('LadingStatDepartGrid',{
	extend:'SearchGridPanel'
	,selModel : null //选中的selectModel
	,selType:'rowmodel'   //行模型选择
	,loadCondition:{
		showPager:true // 显示分页
	    ,showCheckbox:true // 显示checkbox
	}  //查询条件
	,searchParams:null     //外面传入的参数，用于查询始发网点关系
	,listeners: {
	    	scrollershow: function(scroller) {
		    	if (scroller && scroller.scrollEl) {
		    			scroller.clearManagedListeners(); 
		    			scroller.mon(scroller.scrollEl, 'scroll', scroller.onElScroll, scroller); 
		    	}
		    }
	}
	,initComponent:function(){
		var me = this;		
   		me.initLadingStatDepartmentStore();		
    			
   		me.columns = me.getColumns();
   		this.loadShowControl();//动态加载相关控件
		this.callParent();
		
	}
	//初始化始发网点的store
	,initLadingStatDepartmentStore:function(){
		var me = this;
   		me.store=Ext.create('LadingStatDepartStore');		
    	me.store.on('beforeload',function(th,operation){
    		Ext.apply(operation,{params : me.searchParams});
    	});
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
		     {header:i18n('i18n.ladingstation.lineId'),hidden:true,dataIndex:'id'}    
			,{header:i18n('i18n.ladingstation.beginDept'),width:180,dataIndex:'beginDepName'}
			,{header:i18n('i18n.ladingstation.ifReceive'),width:75,dataIndex:'ifReceive'
				,renderer:function(value) {
					return rendererDictionary(value,DataDictionary.CONFIG_ORDER_RECEIVE_GOODS);
				}
			}
			,{header:i18n('i18n.ladingstation.orderSource'),width:75,dataIndex:'resource'
				,renderer:function(value) {
					return rendererDictionary(value,DataDictionary.CONFIG_ORDER_SOURCE);
				}				
			}
			,{header:i18n('i18n.ladingstation.acceptDept'),width:180,dataIndex:'acceptDepName'}
			,{header:i18n('i18n.big_small_city.createTime'),width:115,dataIndex:'createDate'
			  ,renderer:function(value) {
					if(value != null){
						return Ext.Date.format(new Date(value), 'Y-m-d H:i');
					}else{
						return null;
					}
			  }
			 }
			,{header:i18n('i18n.big_small_city.createUName'),width:80,dataIndex:'createUserName'}
			,{header:i18n('i18n.big_small_city.updateTime'),width:115,dataIndex:'modifyDate'
				  ,renderer:function(value) {
						if(value != null){
							return Ext.Date.format(new Date(value), 'Y-m-d H:i');
						}else{
							return null;
						}
				  }
			 }
			,{header:i18n('i18n.big_small_city.updateUName'),width:80,dataIndex:'modifyUserName'}

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


//按钮组
Ext.define('EditButtonPanel',
		{	
		extend:'NormalButtonPanel', 
		items:null,
		initComponent:function(){
			this.items = this.getItems();	
			this.callParent();	
		}
	   ,getItems:function(){
			var me = this;	
			return [{
						xtype:'leftbuttonpanel',
						items:[
						        {xtype:'button',text:i18n('i18n.util.btn.add'),handler:me.createEvent}
						       ,{xtype:'button',text:i18n('i18n.util.btn.update'),handler:me.editEvent}
						       ,{xtype:'button',text:i18n('i18n.ladingstation.import'),handler:me.importEvent}
						       ,{xtype:'button',text:i18n('i18n.util.btn.delete'),handler:me.deleteEvent}
						       ]		
					}
					,{
						xtype:'middlebuttonpanel' 		
					 }
					,{
						xtype:'rightbuttonpanel',
						items:[ 							        
						        {
							 	   xtype:'button'  
							 	  ,text:i18n('i18n.util.btn.search')
							 	  ,handler:me.searchEvent
								}
						        
							   ,{
									   xtype:'button'  
									  ,text:i18n('i18n.ladingstation.button.reset')
									  ,handler:me.resetEvent
								}]	
						
					 }
					
					
					]
		}
	   ,createEvent:function(){
			Ext.create('CreateWindow').show();
		}
	   ,editEvent:function(){
				var selectedOrder = Ext.getCmp('ladingStatDepartGridId').getSelectionModel();
				if (selectedOrder.getSelection().length > 0) {
					//this.action = 'update';
					var ladingStatDept = selectedOrder.getSelection()[0].data;
					Ext.create( 'EditWindow'
							   ,{
								   'ladingStationDept': ladingStatDept 
							    }).show();
					
				} else {

					MessageUtil.showMessage(i18n('i18n.util.msg.data_no_select_one'));
				}		   
		   
	   }
	   ,importEvent:function(){
		   Ext.create('LDImportWindow').show();
	   }
	   ,deleteEvent:function(){
		   var selectedOrder = Ext.getCmp('ladingStatDepartGridId').getSelectionModel(); 
		   if (selectedOrder.getSelection().length > 0) {
			   var ladingStatDept = selectedOrder.getSelection()[0].data;
			   MessageUtil.showQuestionMes(i18n('i18n.ladingstation.del.confirm'),function(e){
				   if(e=='yes'){
					   processingMask.show();
						//执行成功
						var successFn = function(response){
							processingMask.hide();
							MessageUtil.showInfoMes(i18n('i18n.util.msg.delete_succeed'),function(){
								Ext.getCmp('ladingStatDepartGridId').store.load();
								});			
							
						}
						
						//执行失败
						var failFn = function(response){
							processingMask.hide();
							MessageUtil.showErrorMes(response.message);
						} 
						
						//提交
						DpUtil.requestJsonAjax(  'invalidLadingstationDepartment.action'
												    ,{ 
													  'editLDId':ladingStatDept.id
													 }
												    ,successFn
												    ,failFn);				  
						}
				   
			   });
		   }else{
			   MessageUtil.showMessage(i18n('i18n.util.msg.data_no_select_one'));
		   }
		   
	   }
		//查询
		,searchEvent:function(){
			var startNetCode=Ext.getCmp('startNetCMD').getValue();
			var acceptDeptCode=Ext.getCmp('acceptDeptCMD').getValue();
			var orderResourceCMD=Ext.getCmp('orderResourceCMD').getValue();
			var ifReceivedCMD=Ext.getCmp('ifReceivedCMD').getValue();
			var dateStartCMD=Ext.getCmp('dateStartCMD').getValue();
			var dateEndCMD=Ext.getCmp('dateEndCMD').getValue();
			if(dateEndCMD != null) {
				dateEndCMD=new Date(Date.parse(Ext.getCmp('dateEndCMD').getValue())+24*60*60*1000);
			}
			
			var searchParams = {
					 'ldcmd.beginDeptStandardCode':startNetCode
					,'ldcmd.acceptDeptStandCode':acceptDeptCode
					,'ldcmd.resource':orderResourceCMD
					,'ldcmd.ifReceive':ifReceivedCMD
					,'ldcmd.createDateBegin':dateStartCMD
					,'ldcmd.createDateEnd':dateEndCMD

				};		
			
			var ladingStatDepartGrid=Ext.getCmp('ladingStatDepartGridId');
			
	    	ladingStatDepartGrid.searchParams=searchParams;	
			
	    	ladingStatDepartGrid.store.loadPage(1);
			
		}
		//重置
		,resetEvent:function(){
			Ext.getCmp('startNetCMD').setValue(null);
			Ext.getCmp('acceptDeptCMD').setValue(null);
			Ext.getCmp('orderResourceCMD').setValue(null);
			Ext.getCmp('ifReceivedCMD').setValue(null);
			Ext.getCmp('dateStartCMD').setValue(null);
			Ext.getCmp('dateEndCMD').setValue(null);
			
		}	   
	   
		
		});

//‘始发网点与受理部门基础资料新增’的弹出框
Ext.define('CreateWindow', {
    extend:'PopWindow'
    ,id:'createWindowId'
	,width:540,height:150
	,layout: {
        type: 'vbox',
        pack: 'start',  
        align: 'stretchmax'  
	}
	,modal:true
	,title:i18n('i18n.ladingstation.create')
	,ladingStatDepartCrePanel:null  //选择相关基本信息的panel
	,getItems:function(){
		var me=this;
		return [me.ladingStatDepartCrePanel];
		
	}
	,initComponent:function(){

		var me=this;
		me.ladingStatDepartCrePanel=Ext.create('LadingStatDepartCrePanel');
		me.fbar = me.getFbar(); 
		this.items = this.getItems();
		this.callParent(arguments);
    }
	,getFbar:function(){  
		var me = this;
		return [ 
		        { 
		        	xtype:'button'
		        	,text:i18n('i18n.util.btn.submit')
		        	,scope:me
		        	,handler:me.saveEvent		
		        }
		        ,{
		        	xtype:'button'
		        	,text:i18n('i18n.util.btn.close')
		        	,scope:me
		        	,handler:me.closeWindow
		        }      
	]}
	//保存新增的关系
	,saveEvent:function(){
		var startNetId=Ext.getCmp('startNet').getValue();
		if(Ext.isEmpty(startNetId)){
			MessageUtil.showMessage(i18n('i18n.ladingstation.beginDept.select'));
			return;
		}
		
		var ifReceived=Ext.getCmp('ifReceived').getValue();
		if(ifReceived==null){
			MessageUtil.showMessage(i18n('i18n.ladingstation.ifReceive.select'));
			return;
		}
		
		var orderResource=Ext.getCmp('orderResource').getValue();
		if(orderResource==null){
			MessageUtil.showMessage(i18n('i18n.ladingstation.orderSource.select'));
			return;
		}		
		
		
		var acceptDeptId=Ext.getCmp('acceptDept').getValue();
		if(acceptDeptId==null){
			MessageUtil.showMessage(i18n('i18n.ladingstation.acceptDept.select'));
			return;
		}			

		//检查受理部门和始发网点是否是同一个
		if(startNetId==acceptDeptId){
			MessageUtil.showMessage(i18n('i18n.ladingstation.accept.begin.same'));
			return;
		}
		
		//执行成功
		var successFn = function(response){
			processingMask.hide();
			MessageUtil.showMessage(i18n('i18n.ladingstation.save.success'),function(){
				Ext.getCmp('createWindowId').close();
				Ext.getCmp('ladingStatDepartGridId').store.load();
				});			
			
		}
		
		//执行失败
		var failFn = function(response){
			processingMask.hide();
			MessageUtil.showErrorMes(response.message);
		}  		
		processingMask.show();
		//提交
		DpUtil.requestJsonAjax(   'createLadingstationDepartment.action'
								,{ 'startNetId':startNetId
								  ,'acceptDeptId':acceptDeptId
								  ,'orderResource':orderResource
								  ,'ifReceive':ifReceived
								 }
								,successFn
								,failFn);		
		
		
	}
	//关闭本window
	,closeWindow:function(){
		Ext.getCmp('createWindowId').close();
	}   
});


//‘始发网点与受理部门基础资料修改’的弹出框
Ext.define('EditWindow', {
    extend:'PopWindow'
    ,id:'editWindowId'
	,width:540,height:150
	,layout: {
        type: 'vbox',
        pack: 'start',  
        align: 'stretchmax'  
	}
	,ladingStationDept:null  //页面选中的ladingStationDepartment对象
	,modal:true
	,title:'始发网点与受理部门基础资料修改'
	,ladingStatDepartCrePanel:null  //选择相关基本信息的panel
	,getItems:function(){
		var me=this;
		return [me.ladingStatDepartCrePanel];
	}
	,initComponent:function(){

		var me=this;
		me.ladingStatDepartCrePanel=
				Ext.create('LadingStatDepartCrePanel',{
					ladingStationDept:me.ladingStationDept				
		});
		me.fbar = me.getFbar(); 
		this.items = this.getItems();
		this.callParent(arguments);
    }
	,getFbar:function(){  
		var me = this;
		return [ 
		        { 
		        	xtype:'button'
		        		,text:i18n('i18n.ladingstation.button.save')
		        			,scope:me
		        			,handler:me.editEvent		
		        }
		        ,{
		        	xtype:'button'
		        		,text:i18n('i18n.util.btn.close')
		        			,scope:me
		        			,handler:me.closeWindow
		        }
			]
	}
	//修改选中的关系
	,editEvent:function(){
		var me=this;
		var startNetId=Ext.getCmp('startNet').getValue();
		if(startNetId==null){
			MessageUtil.showMessage(i18n('i18n.ladingstation.beginDept.select'));
			return;
		}
		
		var ifReceived=Ext.getCmp('ifReceived').getValue();
		if(ifReceived==null){
			MessageUtil.showMessage(i18n('i18n.ladingstation.ifReceive.select'));
			return;
		}
		
		var orderResource=Ext.getCmp('orderResource').getValue();
		if(orderResource==null){
			MessageUtil.showMessage(i18n('i18n.ladingstation.orderSource.select'));
			return;
		}		
		
		
		var acceptDeptId=Ext.getCmp('acceptDept').getValue();
		if(acceptDeptId==null){
			MessageUtil.showMessage(i18n('i18n.ladingstation.acceptDept.select'));
			return;
		}			

		//检查受理部门和始发网点是否是同一个
		if(startNetId==acceptDeptId){
			MessageUtil.showMessage(i18n('i18n.ladingstation.accept.begin.same'));
			return;
		}
		
		//执行成功
		var successFn = function(response){
			processingMask.hide();
			MessageUtil.showMessage(i18n('i18n.ladingstation.modify.success'),function(){
				Ext.getCmp('editWindowId').close();
				Ext.getCmp('ladingStatDepartGridId').store.load();
				});			
			
		}
		
		//执行失败
		var failFn = function(response){
			processingMask.hide();
			MessageUtil.showErrorMes(response.message);
		}  		
		processingMask.show();
		//提交
		DpUtil.requestJsonAjax(  'editLadingstationDepartment.action'
								    ,{ 'startNetId':startNetId
									  ,'acceptDeptId':acceptDeptId
									  ,'orderResource':orderResource
									  ,'ifReceive':ifReceived
									  ,'editLDId':me.ladingStationDept.id
									 }
								    ,successFn
								    ,failFn);		
		
		
	}
	//关闭本window
	,closeWindow:function(){
		Ext.getCmp('editWindowId').close();
	}
});

/**
 * 始发网点关系维护导入文件的方法
 */
Ext.define('ImportLDFileForm',{
	extend:'NoTitleFormPanel'
	,id:'fileForm'
			
	,upLoadFile:function(){
		var form = Ext.getCmp('fileForm');
		var url = 'importLadingstationDept.action';
		form.submit({
	        url:url,
	        waitMsg: i18n('i18n.ladingstation.uploading'),
	        success: function(form, response) {
	        	var result = response.result;
				if(result.message=='null'){
					MessageUtil.showMessage(i18n('i18n.ladingstation.import.success'),function(){
						Ext.getCmp('ldImportWindowId').close();
	        			Ext.getCmp('ladingStatDepartGridId').store.load();
					});
				}else{

            		var warnWin = null;
            		warnWin = Ext.create('Ext.window.Window',{
            			title:i18n('i18n.ladingstation.import.titleOfEnd'),
            			width:450,
            			height:150,
            			layout:'fit',
            			cls:'warningwin',
            			items:[{
                				xtype : 'textareafield',
                				value:result.message,
								readOnly : true
            			}],
					    dockedItems: [{
						    xtype: 'toolbar',
						    dock: 'bottom',
						    ui: 'footer',
						    layout: {
				                pack: 'center'
				            },
						    items: [
						        { xtype: 'component', flex: 1 },
						        { xtype: 'button', text:i18n('i18n.ladingstation.import.buttonConfirm'),
					    		handler:function(){warnWin.close();}
						        }]
						}]
            		});
            		warnWin.show();					
					
				}
				

	        },
	        failure:function(form, response){
	        	MessageUtil.showMessage(i18n('i18n.ladingstation.file.upload.select'));
	        }
	    });		
		
		
	 }
	,getItems:function(){
		var fileField = Ext.create('Ext.form.field.File',{
    	     emptyText: i18n('i18n.ladingstation.file.upload.select')
    	    ,width:400
    	    ,readOnly:true
    	    ,labelWidth:50
    	    ,fieldLabel: i18n('i18n.ladingstation.file')
    	    ,name: 'file'
    	    ,allowBlank:false
    	    ,buttonText: i18n('i18n.ladingstation.button.upload')
    	});
		return [fileField];
	}
	,initComponent:function(){
		 var me = this;
		 me.items = me.getItems();
		 this.callParent();
	 }

});


//‘批量导入’的弹出框
Ext.define('LDImportWindow', {
    extend:'PopWindow'
    ,id:'ldImportWindowId'
	,width:450,height:120
	,layout: {
        type: 'vbox',
        pack: 'start',  
        align: 'stretchmax'  
	}
	,importLDFileForm:null //导入文件的form
	,modal:true
	,title:i18n('i18n.ladingstation.import.title')
	,getItems:function(){
		var me=this;
		me.importLDFileForm=Ext.create('ImportLDFileForm');
		
		return [me.importLDFileForm];	
	}
	,initComponent:function(){

		this.items = this.getItems();
		this.fbar = this.getFbar(); 
		this.callParent(arguments);
		
    }
	,getFbar:function(){
		var me = this;
		return [
		         {xtype:'button',text:i18n('i18n.ladingstation.import'),handler:me.importLDFileForm.upLoadFile}
		        ,{xtype:'button',text:i18n('i18n.util.btn.close'),handler:me.closeWindow}
		       ];
		
		
	}
	//关闭本window
	,closeWindow:function(){
		Ext.getCmp('ldImportWindowId').close();
	}
});
