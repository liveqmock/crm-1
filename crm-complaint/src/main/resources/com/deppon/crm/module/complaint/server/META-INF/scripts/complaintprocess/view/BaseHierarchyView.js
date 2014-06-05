/**
 * 基础层级模块 声明
 */
Ext.define('BaseHierarchyPanel',{
	extend:'NoTitleFormPanel'
	,complaint:null // 工单数据
	,isEditable:true  // 是否为可编辑的，默认是
	,forceSelectionStatus:true//设置一个combobox是否强制选中
	,isExist_processResult:true  //是否存在处理结果，默认存在
	,processResultStore:null   //处理结果Store
	,defaultType:'combobox',
	defaults:{width:230,labelAlign:'left',labelWidth:70}
	,businessScopeStore:null // 业务项 store
	,businessTypeStore:null // 业务范围store
	,basicBusinessTypeNewStore:null//业务类型store
//	,layout: {align:'middle',type:'hbox'}
	,layout:{
		type:'table',
		columns:3
	}
    ,initComponent : function() {
		var me = this;
		this.businessScopeStore = Ext.create('BusinessScopeStore',{
			autoLoad:true,listeners:{
				beforeload:function(store, operation, eOpts){
					var searchParams = {
						'exceptionCondition.type':me.complaint['reporttype']
					};
					if(me.isEditable && me.isEditable===true){/*若是可编辑 只查出有效的业务范围*/
						searchParams['exceptionCondition.feedBack']='1';
					}
					Ext.apply(operation,{params : searchParams});
				}
			}
		});
		this.businessTypeStore = Ext.create('BusinessTypeStore');
		
		this.basicBusinessTypeNewStore = Ext.create('BasicBusinessTypeNewStore');
		
		if(!me.isEditable || me.isEditable==false){
			Ext.apply(me.defaults,{'readOnly':true,'cls':'readonly'});
		}else{Ext.apply(me.defaults,{'readOnly':false,'cls':''});}
		
		Ext.apply(me,{
    		items:[
    			{
		        	fieldLabel:i18n('i18n.complaint.newBasicInfo.businessItems'),id:'form_paraBasciLevel'
		        	,name:'paraBasciLevel',editable:false
		        	,scope:me,store:me.businessScopeStore
		        	,forceSelection:me.forceSelectionStatus
		        	,queryMode: 'local',displayField:'bascilevelname',valueField:'fid'
		        	,allowBlank:!(me.isEditable && me.isEditable==true),blankText:i18n('i18n.complaint.newBasicInfo.selectBusItems')
		        	,enableKeyEvents:true
		        	,listeners:{
		        		scope:me,
		        		select:me.searchBusinessTypeEvent,
		        		blur:function(th){
		        			if(Ext.isEmpty(th.getValue())){
		        				Ext.getCmp('form_basciLevel').setValue('');/*清空业务范围*/
		        				Ext.getCmp('form_basciLevel').store.removeAll();/*清空业务范围*/
		        				Ext.getCmp('form_basicBusinessTypeNew').setValue('');/*清空业务类型*/
		        				Ext.getCmp('form_basicBusinessTypeNew').store.removeAll();
		        			}
		        		}
		        	}
		        }
		        ,{//业务范围
		        	fieldLabel:i18n('i18n.BaseHierarchyView.bascilevelname'),id:'form_basciLevel'
		        	,name:'basciLevel',editable:false
		        	,store:me.businessTypeStore
		        	,forceSelection:me.forceSelectionStatus
		        	,allowBlank:!(me.isEditable && me.isEditable==true),blankText:i18n('i18n.comp.msg.select_yewu_scope')
		        	,queryMode: 'local',displayField:'bascilevelname',valueField:'fid'
//		        	,listeners:{
//		        		scope:me,select:me.loadProcessResult
//		        	}
		        	,listeners:{
		        		scope:me,
						select:function(th,records,e){
							Ext.getCmp("form_basicBusinessTypeNew").store.load({params:{'busScopeId':th.getValue()}});
						},
						change:function(th,newValue,oldValue){
							var me = this;
							DpUtil.comboSelsct(th);
							if(Ext.isEmpty(newValue)){
								Ext.getCmp("form_basicBusinessTypeNew").store.removeAll();
								Ext.getCmp("form_basicBusinessTypeNew").setValue('');
							}
						}
					}
		        }//增加业务类型级联菜单，原业务范围更名为业务项，业务类型更名为业务范围 by肖红叶
		        ,{
		        	fieldLabel:i18n('i18n.BaseHierarchyView.basciType'),
		        	id:'form_basicBusinessTypeNew',
			        name:'basicBusinessTypeNew',
			        store:me.basicBusinessTypeNewStore,
			        forceSelection:me.forceSelectionStatus,editable:false,
			        allowBlank:!(me.isEditable && me.isEditable==true),
			        blankText:i18n('i18n.comp.msg.select_yewu_type'),
			        queryMode: 'local',
			        //剔出朦层
	    			listConfig: {
	      		        loadMask:false
	    			},
			        displayField:'busTypeName',
			        valueField:'busTypeId',
			        listeners:{
			        		scope:me,
			        		select:me.loadProcessResult
			        }
			    }
    		]
    	});
    	/* 上报类型为 投诉时 存在投诉级别和业务环节*/
    	if(me.complaint['reporttype']==i18n('i18n.complaint.fpririty_complaint')){
    		//增加业务环节下拉菜单 by 肖红叶
    		me.items[me.items.length] = {
	        	fieldLabel:i18n('i18n.complaint.newBasicInfo.complaintLink'),
	        	id:'form_complainLink',
	        	name:'complainLink',
	        	forceSelection:true,editable:false,
				store:getDataDictionaryByName(DataDictionary,'COMPLAINT_LINK'),
				queryMode:'local',
				allowBlank:!(me.isEditable && me.isEditable==true),
		        blankText:'业务环节不能为空',
				displayField:'codeDesc',
				valueField:'code'
	        };
    		
    		me.items[me.items.length] = {
	        	fieldLabel:i18n('i18n.BaseHierarchyView.complevel'),id:'form_complevel'
	        	,name:'complevel'
	        	,forceSelection:true,editable:false
	        	//调用了DataDictionary.js里面的根据名称得到对应数据字典数据的方法
				,store:getDataDictionaryByName(DataDictionary,'COMPLAINT_LEVEL')
				,queryMode:'local',displayField:'codeDesc',valueField:'code'
				,value:'level_one'
	        };
    	}
    	
		this.callParent(arguments);
		if(!DpUtil.isEmpty(me.complaint) && !DpUtil.isEmpty(me.complaint.parabasilevelid)
		&& me.complaint.parabasilevelid!=0){
			me.businessScopeStore.on('load',function(){
				//初始化业务项等级联菜单by肖红叶
				if(!Ext.isEmpty(me.complaint.busItemName)){
					if(!Ext.isEmpty(me.complaint.parabasilevelid)){
						Ext.getCmp('form_paraBasciLevel').setValue(me.complaint.parabasilevelid);
						Ext.getCmp('form_paraBasciLevel').setRawValue(me.complaint.busItemName);
					}
					else{
						Ext.getCmp('form_paraBasciLevel').setValue(me.complaint.busItemName);
						Ext.getCmp('form_paraBasciLevel').setRawValue(me.complaint.busItemName);
					}	
				}
			});
			
			if(me.complaint['reporttype']==i18n('i18n.complaint.fpririty_complaint')){
				//初始化投诉级别
				Ext.getCmp('form_complevel').setValue(me.complaint['complevel']);
				//初始化业务环节
				Ext.getCmp('form_complainLink').setValue(me.complaint['complainLink']);
			}

			//根据业务项ID查询业务范围列表
//			var params = {
//				'exceptionCondition':{'parentid':me.complaint.parabasilevelid}
//			};
//			if(me.isEditable && me.isEditable===true){/*若是可编辑 只查出有效的业务项*/
//				params.exceptionCondition['feedBack']='1';
//			}
//			//执行成功
//			var successFn = function(response){
//				me.businessTypeStore.loadData(response.basciLevelList);
//				if(!Ext.isEmpty(me.complaint.busScopeName)){
//					if(!Ext.isEmpty(me.complaint.basiclevelid)){
//						Ext.getCmp('form_basciLevel').setValue(me.complaint.basiclevelid);
//						Ext.getCmp('form_basciLevel').setRawValue(me.complaint.busScopeName);
//					}
//					else{
//						Ext.getCmp('form_basciLevel').setValue(me.complaint.busScopeName);
//						Ext.getCmp('form_basciLevel').setRawValue(me.complaint.busScopeName);
//					}
//				}
//			}
//			
//			//执行失败
//			var failFn = function(response){
//				MessageUtil.showErrorMes(response.message);
//			}
//			DpUtil.requestJsonAjax('searchBusinessTypeList.action',params,successFn,failFn);
			//初始化业务范围
			Ext.getCmp('form_basciLevel').store.removeAll();
			var basicBusinessScopeNewModel = new BusinessTypeForNewModel();	 	
			basicBusinessScopeNewModel.set('fid',me.complaint.basiclevelid);
			basicBusinessScopeNewModel.set('bascilevelname',me.complaint.busScopeName);
			Ext.getCmp("form_basciLevel").store.add(basicBusinessScopeNewModel);
			basicBusinessScopeNewModel.commit();
			
			if(!Ext.isEmpty(me.complaint.busScopeName)){
				if(!Ext.isEmpty(me.complaint.basiclevelid)){
					Ext.getCmp('form_basciLevel').setValue(me.complaint.basiclevelid);
					Ext.getCmp('form_basciLevel').setRawValue(me.complaint.busScopeName);
				}
				else{
					Ext.getCmp('form_basciLevel').setValue(me.complaint.busScopeName);
					Ext.getCmp('form_basciLevel').setRawValue(me.complaint.busScopeName);
				}
			}
			
			//根据业务范围ID查询业务类型列表
//			Ext.getCmp("form_basicBusinessTypeNew").store.load({params:{'busScopeId':me.complaint.basiclevelid}});
			Ext.getCmp("form_basicBusinessTypeNew").store.removeAll();
			var basicBusinessTypeNewModel = new BasicBusinessTypeNewModel();	 	
			basicBusinessTypeNewModel.set('busTypeId',me.complaint.busTypeId);
			basicBusinessTypeNewModel.set('busTypeName',me.complaint.busTypeName);
			Ext.getCmp("form_basicBusinessTypeNew").store.add(basicBusinessTypeNewModel);
			basicBusinessTypeNewModel.commit();
//			
			if(!Ext.isEmpty(me.complaint.busTypeName)){
				if(!Ext.isEmpty(me.complaint.busTypeId)){
					Ext.getCmp('form_basicBusinessTypeNew').setValue(me.complaint.busTypeId);
					Ext.getCmp('form_basicBusinessTypeNew').setRawValue(me.complaint.busTypeName);
				}
				else{
					Ext.getCmp('form_basicBusinessTypeNew').setValue(me.complaint.busTypeName);
					Ext.getCmp('form_basicBusinessTypeNew').setRawValue(me.complaint.busTypeName);
				}
			}
			
		}
	}
    //根据业务项查询业务范围
	,searchBusinessTypeEvent:function(field,event){
		var me = this;
		if(DpUtil.isEmpty(field.getValue())){
			MessageUtil.showMessage(i18n('i18n.BaseHierarchyView.msg.bascilevelname'));
			return;
		}
		Ext.getCmp('form_basciLevel').setValue('');/*清空业务范围*/
		Ext.getCmp('form_basicBusinessTypeNew').setValue('');/*清空业务类型*/
		Ext.getCmp('form_basicBusinessTypeNew').store.removeAll();
		var params = {
			'exceptionCondition':{'parentid':field.getValue()}
		};
		if(me.isEditable && me.isEditable===true){/*若是可编辑 只查出有效的业务项*/
			params.exceptionCondition['feedBack']='1';
		}
		//执行成功
		var successFn = function(response){
			me.businessTypeStore.loadData(response.basciLevelList);
		}
		
		//执行失败
		var failFn = function(response){
			MessageUtil.showErrorMes(response.message);
		}
		DpUtil.requestJsonAjax('searchBusinessTypeList.action',params,successFn,failFn);
	}
	,initBusinessType:function(basciLevelId){
		var me=this;
		var params = {
			'exceptionCondition':{'parentid':basciLevelId}
		};
		if(me.isEditable && me.isEditable===true){/*若是可编辑 只查出有效的业务范围*/
			params.exceptionCondition['feedBack']='1';
		}
		//执行成功
		var successFn = function(response){
			me.businessTypeStore.loadData(response.basciLevelList);
			//当store初始化成功之后，才执行set值操作。
			Ext.getCmp('form_basciLevel').setValue(me.complaint.basiclevelid);
		}
		
		//执行失败
		var failFn = function(response){
			MessageUtil.showErrorMes(response.message);
		}		
		
		DpUtil.requestJsonAjax('searchBusinessTypeList.action',params,successFn,failFn);
	}
	
	,loadProcessResult:function(field,record){
		//根据业务类型，返回相应的处理结果列表
		var me=this;
		//可编辑处理结果最大数据量
		var processResult_maxCount = 10;
		//总数不能大于10
		if(me.processResultStore.getCount()===processResult_maxCount){
			Ext.getCmp('processResult_addNew').setDisabled(true);
			return false;
		}
		
		if(!Ext.isEmpty(field)){
			me.processResultStore.add({
				'feedtimelimit':record[0].get('feedbackLimit')
				,'processtimelimit':record[0].get('procLimit')
				,'dealmatters':record[0].get('dealLanguage'),
				'leaveDeptName':me.complaint.leaveDeptName
				
			});
		}
	}
});
