
/**
 * 
 * <p>
 * Description:规则管理 Store<br />
 * </p>
 * @title PlanningGridStore
 * @author 侯斌飞
 * @version 0.1 2012-12-26
 */
Ext.define('PlanningGridStore',{
	extend:'Ext.data.Store'
	,model:'PlanningModel'
	,pageSize:10
	,proxy:{
		type:'ajax'
		,api:{read:'ruleSearchListPager.action'}
		,actionMethods:'POST'
		,reader:{
			type:'json'
			,root:'pnList'
			,totalProperty:'totalCount'
		}
	}
});

/**
 * 
 * <p>
 * Description:规则管理 Data<br />
 * </p>
 * @title PlanningData
 * @author 侯斌飞
 * @version 0.1 2012-12-26
 */
Ext.define('PlanningData', {
	resultStore:null
	//客户工单查询结果 store
	,getSearchStore: function() {
		return this.resultStore;
	}
	//客户工单查询结果  store 初始化
	,initSearchStore: function(beforeLoadFn) {
		if(this.resultStore == null){
			if(beforeLoadFn != null){
				this.resultStore = Ext.create('PlanningGridStore',{
					listeners:{
						beforeload:beforeLoadFn
						,load:function(store,records,successful){
							if(!store || store.getCount()==0){
								MessageUtil.showMessage(i18n('i18n.scheduler.msg.no_select_data'));
							}
						}
					}
				});
			}else{
				this.resultStore = Ext.create('PlanningGridStore');
			}
		}
		return this.resultStore;
	}
});

/**
 * 
 * <p>
 * Description:规则 删除<br />
 * </p>
 * @title PlanningData
 * @author 侯斌飞
 * @version 0.1 2012-12-26
 */
PlanningData.planningDelete=function(params,successFn,failFn){
	DpUtil.requestAjax('ruleDelete.action',params,successFn,failFn);
}

/**
 * 
 * <p>
 * Description:规则 新增<br />
 * </p>
 * @title PlanningData
 * @author 侯斌飞
 * @version 0.1 2012-12-26
 */
PlanningData.planningSave=function(params,successFn,failFn){
	DpUtil.requestAjax('ruleAdd.action',params,successFn,failFn);
}

/**
 * 
 * <p>
 * Description:规则 修改<br />
 * </p>
 * @title PlanningData
 * @author 侯斌飞
 * @version 0.1 2012-12-26
 */
PlanningData.planningUpdate=function(params,successFn,failFn){
	DpUtil.requestAjax('ruleUpdate.action',params,successFn,failFn);
}