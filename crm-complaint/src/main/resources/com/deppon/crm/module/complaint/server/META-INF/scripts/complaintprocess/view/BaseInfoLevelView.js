/**
 * 基础层级模块 声明
 */
 Ext.define('levelComboBox',{
	extend:'Ext.form.ComboBox',
	alias : ['widget.levelcombobox'],
	extValue:null,
	setExtValue:function(v){
		var me = this;
		me.extValue=v;
	},
	getValue: function() {
		var me = this;
		value = me.value;
		if(value==undefined){
			return me.extValue;
		}
		return value;
	}
});
 
Ext.define('BaseInfoLevelPanel',{
	extend:'NoTitleFormPanel'
	,complaint:null // 工单数据
	,isEditable:true  // 是否为可编辑的，默认是
	,forceSelectionStatus:true//设置一个combobox是否强制选中
	,isExist_processResult:true  //是否存在处理结果，默认存在
	,addProcessResult:false
	,processResultStore:null   //处理结果Store
	,defaultType:'combobox',
	defaults:{width:230,labelAlign:'left',labelWidth:70,editable:false}
	,businessItemStore:null // 业务项 store
	,businessScopeStore:null // 业务范围store
	,businessTypeStore:null//业务类型store
	,businessSceneStore:null//业务场景store
	,businessSceneRessonStore:null//场景原因store
	,layout:{
		type:'table',
		columns:3
	}
    ,initComponent : function() {
		var me = this;
		//业务项
		me.businessItemStore = Ext.create('BaseInfoLevelStore');
		me.businessItemStore.load({params:{
								'condition.classCode':'COMPLAINT',
								'condition.typeCode':me.complaint['reporttype'],
								'condition.level':1
								}
						});		
		//业务范围					
		me.businessScopeStore = Ext.create('BaseInfoLevelStore');
		//业务类型
		me.businessTypeStore = Ext.create('BaseInfoLevelStore');
		//业务场景
		me.businessSceneStore = Ext.create('BaseInfoLevelStore');
		//场景原因
		me.businessSceneRessonStore = Ext.create('BaseInfoLevelStore');
		
		if(!me.isEditable || me.isEditable==false){
			Ext.apply(me.defaults,{'readOnly':true,'cls':'readonly'});
		}else{Ext.apply(me.defaults,{'readOnly':false,'cls':''});}
		
		Ext.apply(me,{
    		items:[
    			{//业务项
    				xtype:'levelcombobox',
		        	fieldLabel:i18n('i18n.complaint.newBasicInfo.businessItems')
		        	,name:'paraBasciLevel'
		        	,scope:me
		        	,store:me.businessItemStore
		        	,forceSelection:me.forceSelectionStatus
		        	,queryMode: 'local'
		        	,displayField:'baseInfo'
		        	,valueField:'id'
		        	,listConfig: {
	      		        loadMask:false
	    			}
		        	,allowBlank:!(me.isEditable && me.isEditable==true),blankText:i18n('i18n.complaint.newBasicInfo.selectBusItems')
		        	,listeners:{
		        		scope:me,
		        		select:function(f,e){
							var form =me.getForm();
							form.findField('basciLevel').clearValue();//清空业务范围
							form.findField('basicBusinessType').clearValue();//清空业务类型
							form.findField('basicBusinessType').getStore().removeAll();
							form.findField('busScene').clearValue();//清空业务场景
							form.findField('busScene').getStore().removeAll();
							form.findField('sceneResson').clearValue();//清空场景原因
							form.findField('sceneResson').getStore().removeAll();
							form.findField('sceneResson').allowBlank=true;						
							var params = {
									'condition.parentId':f.getValue()
							};
							me.businessScopeStore.load({params:params});
						}
		        	}
		        }
		        ,{//业务范围
		        	xtype:'levelcombobox',
		        	fieldLabel:i18n('i18n.BaseHierarchyView.bascilevelname')
		        	,name:'basciLevel'
		        	,store:me.businessScopeStore
		        	,forceSelection:me.forceSelectionStatus
		        	,allowBlank:!(me.isEditable && me.isEditable==true),blankText:i18n('i18n.comp.msg.select_yewu_scope')
		        	,queryMode: 'local'
		        	,displayField:'baseInfo'
		        	,valueField:'id',
	    			listConfig: {
	      		        loadMask:false
	    			},
		        	listeners:{
		        		scope:me,
						select:function(f,records,e){
							var form =me.getForm();
							form.findField('basicBusinessType').setValue('');//清空业务类型
							form.findField('busScene').clearValue();//清空业务场景
							form.findField('busScene').getStore().removeAll();
							form.findField('sceneResson').clearValue();//清空场景原因
							form.findField('sceneResson').getStore().removeAll();
							form.findField('sceneResson').allowBlank=true;
							var params = {
										'condition.parentId':f.getValue()
								};
							me.businessTypeStore.load({params:params});
						}
					}
		        }//业务类型
		        ,{
		        	xtype:'levelcombobox',
		        	fieldLabel:i18n('i18n.BaseHierarchyView.basciType'),
			        name:'basicBusinessType',
			        store:me.businessTypeStore,
			        forceSelection:me.forceSelectionStatus,
			        allowBlank:!(me.isEditable && me.isEditable==true),
			        blankText:i18n('i18n.comp.msg.select_yewu_type'),
			        queryMode: 'local',
			        displayField:'baseInfo',
		        	valueField:'id',
	    			listConfig: {
	      		        loadMask:false
	    			},
			        listeners:{
			        		scope:me,
			        		select:function(f,record){
								var me=this;
								var form =me.getForm();
								form.findField('busScene').setValue('');//清空业务场景
								form.findField('sceneResson').clearValue();//清空场景原因
								form.findField('sceneResson').getStore().removeAll();
								form.findField('sceneResson').allowBlank=true;
								var params = {
											'condition.parentId':f.getValue()
									};
								me.businessSceneStore.load({params:params});
								
								//根据业务类型，返回相应的处理结果列表								
								//可编辑处理结果最大数据量
								var processResult_maxCount = 10;
								//总数不能大于10
								if(me.processResultStore.getCount()===processResult_maxCount){
									Ext.getCmp('processResult_addNew').setDisabled(true);
									return false;
								}
								
								if(!Ext.isEmpty(f)){
									me.processResultStore.add({
										'feedtimelimit':record[0].get('feedbackLimit')
										,'processtimelimit':record[0].get('procLimit')
										,'dealmatters':record[0].get('deallan'),
										'leaveDeptName':me.complaint.leaveDeptName
										
									});
								}
							}
			        }
			    },{//业务场景
			    	xtype:'levelcombobox',
		        	fieldLabel:i18n('i18n.baseInfo.businessScene')
		        	,name:'busScene'
		        	,store:me.businessSceneStore
		        	,forceSelection:me.forceSelectionStatus
		        	,queryMode: 'local'
		        	,displayField:'baseInfo'
		        	,valueField:'id',
	    			listConfig: {
	      		        loadMask:false
	    			},
	    			 tpl:Ext.create('Ext.XTemplate',
						'<ul><tpl for=".">',
						'<li role="option" class="x-boundlist-item"><div data-qtip="{baseInfo}">{baseInfo:this.shortBaseInfo}</div></li>',
						'</tpl></ul>'
					),
		        	listeners:{
		        		scope:me,
		        		beforerender:function(t){
						  	var dp = t.tpl;
							dp.shortBaseInfo = function(v){		
								return v?v.substring(0,12):v;			
							};
						 },
						select:function(f,records,e){
							var form =me.getForm();
							form.findField('sceneResson').setValue('');//清空场景原因
							form.findField('sceneResson').allowBlank=false;
							var params = {
										'condition.parentId':f.getValue()
								};
							me.businessSceneRessonStore.load({params:params});
						}
					}
		        },{//场景原因
		        	xtype:'levelcombobox',
		        	fieldLabel:i18n('i18n.baseInfo.businessSceneResson')
		        	,name:'sceneResson'
		        	,store:me.businessSceneRessonStore
		        	,forceSelection:me.forceSelectionStatus
		        	,queryMode: 'local'
		        	,displayField:'baseInfo'
		        	,valueField:'id',
	    			listConfig: {
	      		        loadMask:false
	    			},
	    			 tpl:Ext.create('Ext.XTemplate',
						'<ul><tpl for=".">',
						'<li role="option" class="x-boundlist-item"><div data-qtip="{baseInfo}">{baseInfo:this.shortBaseInfo}</div></li>',
						'</tpl></ul>'
					),
	    			 listeners:{
			        		scope:me,
			        		beforerender:function(t){
							  	var dp = t.tpl;
								dp.shortBaseInfo = function(v){		
									return v?v.substring(0,12):v;			
								};
						 	},
			        		select:function(f,record){
			        			if(f.getValue()&&me.processResultStore.getCount()>0){
			        				me.processResultStore.getAt(me.processResultStore.getCount()-1).set('procStandard',record[0].get('procStandard'))
			        			}
			        		}
	    			 }
		        }
    		]
    	});
    	/* 上报类型为 投诉时 存在投诉级别和业务环节*/
    	if(me.complaint['reporttype']==i18n('i18n.complaint.fpririty_complaint')){
    		//增加业务环节下拉菜单 by 肖红叶
    		me.items[me.items.length] = {
	        	fieldLabel:i18n('i18n.complaint.newBasicInfo.complaintLink'),
	        	name:'complainLink',
	        	forceSelection:true,
				store:getDataDictionaryByName(DataDictionary,'COMPLAINT_LINK'),
				queryMode:'local',
				allowBlank:!(me.isEditable && me.isEditable==true),
		        blankText:i18n('i18n.baseInfo.complainLinkBankText'),
				displayField:'codeDesc',
				valueField:'code'
	        };
    		
    		me.items[me.items.length] = {
	        	fieldLabel:i18n('i18n.BaseHierarchyView.complevel')
	        	,name:'complevel'
	        	,forceSelection:true
	        	//调用了DataDictionary.js里面的根据名称得到对应数据字典数据的方法
				,store:getDataDictionaryByName(DataDictionary,'COMPLAINT_LEVEL')
				,queryMode:'local',displayField:'codeDesc',valueField:'code'
				,value:'level_one'
	        };
    	}
    	
		this.callParent(arguments);
		if(!DpUtil.isEmpty(me.complaint) && !DpUtil.isEmpty(me.complaint.parabasilevelid)
		&& me.complaint.parabasilevelid!=0){
			var form =me.getForm();
			
			//初始化业务项
			me.businessItemStore.load({params:{
								'condition.classCode':'COMPLAINT',
								'condition.typeCode':me.complaint['reporttype'],
								'condition.level':1
								},
								callback:function(a,b,c){
									if(!Ext.isEmpty(me.complaint.busItemName)){
										form.findField('paraBasciLevel').setExtValue(me.complaint.parabasilevelid);
										form.findField('paraBasciLevel').setRawValue(me.complaint.busItemName);
									}
								}
							});
			
			if(me.complaint['reporttype']==i18n('i18n.complaint.fpririty_complaint')){
				//初始化投诉级别
				form.findField('complevel').setValue(me.complaint['complevel']);
				//初始化业务环节
				form.findField('complainLink').setValue(me.complaint['complainLink']);
			}

			//初始化业务范围
			if(!Ext.isEmpty(me.complaint.busScopeName)){
				form.findField('basciLevel').setExtValue(me.complaint.basiclevelid);
				form.findField('basciLevel').setRawValue(me.complaint.busScopeName);
			}
			
			//初始化业务类型
			
			if(!Ext.isEmpty(me.complaint.busTypeName)){
				form.findField('basicBusinessType').setExtValue(me.complaint.busTypeId);
				form.findField('basicBusinessType').setRawValue(me.complaint.busTypeName);
			}
			
			//初始化业务场景
			if(!Ext.isEmpty(me.complaint.busSceneName)){
				form.findField('busScene').setExtValue(me.complaint.busSceneId);
				form.findField('busScene').setRawValue(me.complaint.busSceneName);
			}
			
			//初始化场景原因
			if(!Ext.isEmpty(me.complaint.sceneRessonName)){
				form.findField('sceneResson').setExtValue(me.complaint.sceneRessonId);
				form.findField('sceneResson').setRawValue(me.complaint.sceneRessonName);
			}
			
		}
		if(me.addProcessResult){
				//默认带出一条数据
				initDeptAndUserInfo();
				me.processResultStore.add({
					'feedtimelimit':86400
					,'processtimelimit':1464
					,'dealmatters':'0'
					,'dealType':'employee'
					,'ftaskDeptName':User.empName
					,'taskpartmentid':User.empId
					,'leaveDeptName':me.complaint.leaveDeptName				
				});
			}
	}
});
