
/**
 * 基础层级模块 声明
 */
Ext.define('BaseHierarchyPanel',{
	extend:'NoTitleFormPanel'
	,complaint:null // 工单数据
	,isEditable:true  // 是否为可编辑的，默认是
	,isExist_processResult:true  //是否存在处理结果，默认存在
	,processResultStore:null   //处理结果Store
	,defaultType:'combobox',defaults:{width:230,labelAlign:'left',labelWidth:70}
	,businessScopeStore:null // 业务范围 store
	,businessTypeStore:null // 业务范类型store
	,layout: {align:'middle',type:'hbox'}
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
		
		if(!me.isEditable || me.isEditable==false){
			Ext.apply(me.defaults,{'readOnly':true,'cls':'readonly'});
		}else{Ext.apply(me.defaults,{'readOnly':false,'cls':''});}
		
		Ext.apply(me,{
    		items:[
    			{
		        	fieldLabel:i18n('i18n.BaseHierarchyView.bascilevelname'),id:'form_paraBasciLevel'
		        	,name:'paraBasciLevel'
		        	,scope:me,store:me.businessScopeStore
		        	,forceSelection:true
		        	,queryMode: 'local',displayField:'bascilevelname',valueField:'fid'
		        	,allowBlank:!(me.isEditable && me.isEditable==true),blankText:i18n('i18n.comp.msg.select_yewu_scope')
		        	,enableKeyEvents:true
		        	,listeners:{
		        		scope:me,select:me.searchBusinessTypeEvent
		        	}
		        }
		        ,{
		        	fieldLabel:i18n('i18n.BaseHierarchyView.basciType'),id:'form_basciLevel'
		        	,name:'basciLevel'
		        	,store:me.businessTypeStore
		        	,forceSelection:true
		        	,allowBlank:!(me.isEditable && me.isEditable==true),blankText:i18n('i18n.comp.msg.select_yewu_type')
		        	,queryMode: 'local',displayField:'bascilevelname',valueField:'fid'
		        	,listeners:{
		        		scope:me,select:me.loadProcessResult
		        	}
		        }
    		]
    	});
    	/* 上报类型为 投诉时 存在投诉级别 */
    	if(me.complaint['reporttype']==i18n('i18n.complaint.fpririty_complaint')){
    		me.items[me.items.length] = {
	        	fieldLabel:i18n('i18n.BaseHierarchyView.complevel'),id:'form_complevel'
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
			me.businessScopeStore.on('load',function(){
				Ext.getCmp('form_paraBasciLevel').setValue(me.complaint.parabasilevelid);
			});
			//初始化业务类型
			this.initBusinessType(me.complaint.parabasilevelid);
			if(me.complaint['reporttype']==i18n('i18n.complaint.fpririty_complaint')){
				//初始化投诉级别
				Ext.getCmp('form_complevel').setValue(me.complaint['complevel']);
			}
		}
	}
	,searchBusinessTypeEvent:function(field,event){
		var me = this;
		if(DpUtil.isEmpty(field.getValue())){
			MessageUtil.showMessage(i18n('i18n.BaseHierarchyView.msg.bascilevelname'));
			return;
		}
		Ext.getCmp('form_basciLevel').setValue('');/*清空业务类型*/
		var params = {
			'exceptionCondition':{'parentid':field.getValue()}
		};
		if(me.isEditable && me.isEditable===true){/*若是可编辑 只查出有效的业务范围*/
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
					Ext.getCmp('processResult_add').setDisabled(true);
					break;
				}
				me.processResultStore.add({
					'feedtimelimit':procResult.feedbacklimit
					,'processtimelimit':procResult.proclimit
					,'dealmatters':procResult.deallan
				});
			}
		};
		
		//执行失败
		var failFn = function(response){return;};	
		DpUtil.requestJsonAjax('searchProcresultsByLevelId.action',params,successFn,failFn);
	}
});
