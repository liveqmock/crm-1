/**
 * 执行人store
 */
Ext.define('UserStore', {
	extend : 'Ext.data.Store',
	model : 'UserModel',
	proxy : {
		type : 'ajax',
		url : '../marketing/queryEmployeesByDeptId.action',
		actionMethods : 'post',
		reader : {
			type : 'json',
			root : 'planeDraftList'
		}
	}
});
/**
 * 部门store
 */
Ext.define('DeptStore', {
	extend : 'Ext.data.Store',
	model : 'DeptModel',
//	autoLoad:true,
	pageSize : 10,
	proxy : {
		type : 'ajax',
		url : '../marketing/queryDeptListByDeptName.action',
		actionMethods : 'post',
		reader : {
			type : 'json',
			root : 'executeDeptList',
			totalProperty : 'totalCount'
		}
	}
});
/**
 * 计划管理主页面查询结果store
 */
Ext.define('DevelopPlaneGirdStore', {
	extend : 'Ext.data.Store',
	model : 'DevelopPlaneGirdModel',
	pageSize : 20,
	proxy : {
		type : 'ajax',
		url : '../marketing/searchDevelopPlane.action',
		actionMethods : 'post',
		reader : {
			type : 'json',
			root : 'planList',
			totalProperty : 'totalCount'
		}
	}

});
/**
 * 日程管理查询结果列表store
 */
Ext.define('DevelopScheduleGirdStore', {
	extend : 'Ext.data.Store',
	model : 'DevelopScheduleGirdModel',
	pageSize : 20,
	proxy : {
		type : 'ajax',
		url : '../marketing/searchDevelopSchedule.action',
		actionMethods : 'post',
		reader : {
			type : 'json',
			root : 'scheduleVOList',
			totalProperty : 'totalCount'
		}
	}
});

/**
 * 计划名称store
 */
Ext.define('DevelopName', {
	extend : 'Ext.data.Store',
	model : 'DevelopNameModel',
	//autoLoad : true,
	proxy : {
		type : 'ajax',
		url : '../marketing/searchPlaneTopic.action',
		actionMethods : 'post',
		reader : {
			type : 'json',
			root : 'planNameList'
		}
	}
});

/**
 * 初始化订单来源
 */
var orderResources = null;
function initOrderSources(){
    Ext.Ajax.request({
        url: '../order/allResources.action',
        async:false,
        success: function(res){
            var data = Ext.decode(res.responseText);
            if(data.isException){
                MessageUtil.showErrorMes(data.message,function(){
                    e.record.reject();
                });
            }else{
                orderResources = data.resources;
            }
        },
        failure: function(){
            MessageUtil.showErrorMes("加载订单来源失败");
        }
    })
};
//获得订单来源的Store
function getOrderResourcesStore(){
    if(!Ext.isEmpty(orderResources)){
        var json={
            fields: [{
                name: 'codeDesc', mapping: 'name'
            },{
                name: 'code', mapping: 'resource'
            }],
            data : orderResources
        };
        return Ext.create('Ext.data.Store',json);
    }
    else{
        return null;
    }
};