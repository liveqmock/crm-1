/**
 * 基础层级模块 声明
 */
Ext.define('BaseHierarchyPanelNew',{
	extend:'BasicPanel'
	,complaint:null // 工单数据
	,isEditable:true  // 是否为可编辑的，默认是
	,isExist_processResult:true  //是否存在处理结果，默认存在
	,processResultStore:null   //处理结果Store
	,defaultType:'combobox',defaults:{width:230,labelAlign:'left',labelWidth:70}
	,businessScopeStore:null // 业务项store
	,businessTypeStore:null // 业务范范围store
	,basicBusinessTypeNewStore:null//业务类型store
//	,layout: {align:'middle',type:'hbox'}
	,layout:{
		type:'table',
		columns:3,
		margin:'5 5 5 5'
	}
    ,initComponent : function() {
		var me = this;
		this.businessScopeStore = Ext.create('BusinessScopeStore',{
			autoLoad:true
			,listeners:{
				beforeload:function(store, operation, eOpts){
					var searchParams = {
						'exceptionCondition.type':me.complaint['reporttype']
					};
					Ext.apply(operation,{params : searchParams});
				}
			}
		});
		this.businessTypeStore = Ext.create('BusinessTypeStore',{});
		this.basicBusinessTypeNewStore = Ext.create('BasicBusinessTypeNewStore');
		if(!me.isEditable || me.isEditable==false){
			Ext.apply(me.defaults,{'readOnly':true,'cls':'readonly'});
		}else{Ext.apply(me.defaults,{'readOnly':false,'cls':''});}
		Ext.apply(me,{
    		items:[
    			{
		        	fieldLabel:i18n('i18n.complaint.newBasicInfo.businessItems'),id:'form_paraBasciLevelNew'
		        	,scope:me,store:me.businessScopeStore
		        	,forceSelection:false
		        	,queryMode: 'local',displayField:'bascilevelname',valueField:'fid'
		        	,enableKeyEvents:true
		        	,listeners:{
		        		scope:me,
		        		select:me.searchBusinessTypeEvent,
		        		blur:function(th){
		        			if(Ext.isEmpty(th.getValue())){
		        				Ext.getCmp('form_basciLevelNew').setValue('');/*清空业务范围*/
		        				Ext.getCmp('form_basciLevelNew').store.removeAll();/*清空业务范围*/
		        				Ext.getCmp('form_basicBusinessTypeNewForNew').setValue('');/*清空业务类型*/
		        				Ext.getCmp('form_basicBusinessTypeNewForNew').store.removeAll();
		        			}
		        		}
		        	}
		        }
		        ,{
		        	fieldLabel:i18n('i18n.BaseHierarchyView.bascilevelname'),id:'form_basciLevelNew'
		        	,store:me.businessTypeStore
		        	,forceSelection:false
		        	,queryMode: 'local',displayField:'bascilevelname',valueField:'fid'
		        	,listeners:{
		        		scope:me,select:me.loadProcessResult
		        	}
		        }//增加业务类型级联菜单，原业务范围更名为业务项，业务类型更名为业务范围 by肖红叶
		        ,{
		        	fieldLabel:i18n('i18n.BaseHierarchyView.basciType'),
		        	id:'form_basicBusinessTypeNewForNew',
			        name:'basicBusinessTypeNewForNew',
			        store:me.basicBusinessTypeNewStore,
			        forceSelection:false,
			        allowBlank:!(me.isEditable && me.isEditable==true),
			        blankText:i18n('i18n.comp.msg.select_yewu_type'),
			        queryMode: 'local',
			        //剔出朦层
	    			listConfig: {
	      		        loadMask:false
	    			},
			        displayField:'busTypeName',
			        valueField:'busTypeId'
			    }
    		]
    	});
    	/* 上报类型为 投诉时 存在投诉级别 */
    	if(me.complaint['reporttype']==i18n('i18n.complaint.fpririty_complaint')){
    		//增加投诉环节下拉菜单 by 肖红叶
    		me.items[me.items.length] = {
	        	fieldLabel:i18n('i18n.complaint.newBasicInfo.complaintLink'),
	        	id:'form_complainLinkNew',
	        	name:'complainLinkNew',
	        	forceSelection:true,
				store:getDataDictionaryByName(DataDictionary,'COMPLAINT_LINK'),
				allowBlank:!(me.isEditable && me.isEditable==true),
		        blankText:'业务环节不能为空',
				queryMode:'local',
				displayField:'codeDesc',
				valueField:'code'
	        };
    		me.items[me.items.length] = {
	        	fieldLabel:i18n('i18n.BaseHierarchyView.complevel'),id:'form_complevelNew'
	        	,forceSelection:true
	        	//调用了DataDictionary.js里面的根据名称得到对应数据字典数据的方法
				,store:getDataDictionaryByName(DataDictionary,'COMPLAINT_LEVEL')
				,queryMode: 'local',displayField:'codeDesc',valueField:'code'
				,value:'level_one'
	        };
    	}
    	
		this.callParent(arguments);
		if(!DpUtil.isEmpty(me.complaint) && !DpUtil.isEmpty(me.complaint.parabasilevelid)
		&& me.complaint.parabasilevelid!=0){
			//初始化业务项
			me.businessScopeStore.on('load',function(){
				if(!Ext.isEmpty(me.complaint.busItemName)){
					if(!Ext.isEmpty(me.complaint.parabasilevelid)){
						Ext.getCmp('form_paraBasciLevelNew').setValue(me.complaint.parabasilevelid);
						Ext.getCmp('form_paraBasciLevelNew').setRawValue(me.complaint.busItemName);
					}
					else{
						Ext.getCmp('form_paraBasciLevelNew').setValue(me.complaint.busItemName);
						Ext.getCmp('form_paraBasciLevelNew').setRawValue(me.complaint.busItemName);
					}	
				}
			});
			if(me.complaint['reporttype']==i18n('i18n.complaint.fpririty_complaint')){
				//初始化投诉级别
				Ext.getCmp('form_complevelNew').setValue(me.complaint['complevel']);
				//初始化业务环节
				Ext.getCmp('form_complainLinkNew').setValue(me.complaint['complainLink']);
			}
			
			//根据业务项ID查询业务范围列表
			var params = {
				'exceptionCondition':{'parentid':me.complaint.parabasilevelid}
			};
			if(me.isEditable && me.isEditable===true){/*若是可编辑 只查出有效的业务项*/
				params.exceptionCondition['feedBack']='1';
			}
			//执行成功
			var successFn = function(response){
				me.businessTypeStore.loadData(response.basciLevelList);
				if(!Ext.isEmpty(me.complaint.busScopeName)){
					if(!Ext.isEmpty(me.complaint.basiclevelid)){
						Ext.getCmp('form_basciLevelNew').setValue(me.complaint.basiclevelid);
						Ext.getCmp('form_basciLevelNew').setRawValue(me.complaint.busScopeName);
					}
					else{
						Ext.getCmp('form_basciLevelNew').setValue(me.complaint.busScopeName);
						Ext.getCmp('form_basciLevelNew').setRawValue(me.complaint.busScopeName);
					}
				}
			}
			//执行失败
			var failFn = function(response){
				MessageUtil.showErrorMes(response.message);
			}
			DpUtil.requestJsonAjax('searchBusinessTypeList.action',params,successFn,failFn);
			
			//根据业务范围ID查询业务类型列表
			Ext.getCmp("form_basicBusinessTypeNewForNew").store.load({params:{'busScopeId':me.complaint.basiclevelid}});
			if(!Ext.isEmpty(me.complaint.busTypeName)){
				if(!Ext.isEmpty(me.complaint.busTypeId)){
					Ext.getCmp('form_basicBusinessTypeNewForNew').setValue(me.complaint.busTypeId);
					Ext.getCmp('form_basicBusinessTypeNewForNew').setRawValue(me.complaint.busTypeName);
				}
				else{
					Ext.getCmp('form_basicBusinessTypeNewForNew').setValue(me.complaint.busTypeName);
					Ext.getCmp('form_basicBusinessTypeNewForNew').setRawValue(me.complaint.busTypeName);
				}
			}
			
		}
	}
	,searchBusinessTypeEvent:function(field,event){
		var me = this;
		if(DpUtil.isEmpty(field.getValue())){
			MessageUtil.showMessage(i18n('i18n.BaseHierarchyView.msg.bascilevelname'));
			return;
		}
		Ext.getCmp('form_basciLevelNew').setValue('');/*清空业务类型*/
		var params = {
			'basciLevelId':field.getValue()
		};
		
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
		var params = {'basciLevelId':basciLevelId};

		//执行成功
		var successFn = function(response){
			me.businessTypeStore.loadData(response.basciLevelList);
			//当store初始化成功之后，才执行set值操作。
			Ext.getCmp('form_basciLevelNew').setValue(me.complaint.basiclevelid);
		}
		
		//执行失败
		var failFn = function(response){
			MessageUtil.showErrorMes(response.message);
		}		
		
		DpUtil.requestJsonAjax('searchBusinessTypeList.action',params,successFn,failFn);
	}
	
	,loadProcessResult:function(field){
		var me=this;
		//处理结果不存在
		if(!(me.isExist_processResult && me.isExist_processResult===true)){return;}
		//处理结果store 不存在
		if(!(me.processResultStore && me.processResultStore !=null)){return;}
		var params = {'basciLevelId':field.getValue()};
		//可编辑处理结果最大数据量
		var processResult_maxCount = 10;
		//执行成功
		var successFn = function(response){
			var procResultList = response.procResultList;
			if(DpUtil.isEmpty(procResultList)){return;}
			for(var i=0;i<procResultList.length;i++){
				var procResult = procResultList[i];
				//总数不能大于10
				if(me.processResultStore.getCount()==processResult_maxCount){
					Ext.getCmp('processResult_addNew').setDisabled(true);
					break;
				}
				me.processResultStore.add({
					'feedtimelimit':procResult.feedbacklimit
					,'processtimelimit':procResult.proclimit
					,'dealmatters':procResult.deallan,
					'leaveDept':me.complaint.leaveDeptName
				});
			}
		}
		
		//执行失败
		var failFn = function(response){return;}		
		DpUtil.requestJsonAjax('searchProcresultsByLevelId.action',params,successFn,failFn);
	}
});
