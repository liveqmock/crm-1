 /**虚拟职位-角色管理
 * @author 肖红叶
 * @version 0.1 2013-11-28
 */
var VirtualPositionToRoleData  = function(){
	
};

/**
 * 虚拟职位-角色查询结果列表的store
 * 20131128
 * 肖红叶
 */
Ext.define('VirtualPositionToRoleStore',{
    extend : 'Ext.data.Store',
    model : 'VPositionToRoleModel',
    pageSize : 20,
    proxy : {
        type : 'ajax',
        url : '../authorityDeploy/searchVirtualPositionToRoleList.action',
        actionMethods : 'POST',
        reader : {
            type : 'json',
            root : 'virtualPositionRoleList',
            totalProperty : 'totalCount'
        }
    }
});

/**
 * 角色查询结果列表的store
 * 20131128
 * 肖红叶
 */
Ext.define('RoleStore',{
    extend : 'Ext.data.Store',
    model : 'RoleModel',
    proxy : {
        type : 'ajax',
        url : '../authorityDeploy/searchRoleInfoList.action',
        actionMethods : 'POST',
        reader : {
            type : 'json',
            root : 'roleList'
        }
    }
});

/**
 * 虚拟职位查询结果列表的store
 */
Ext.define('VirtualPositionStore',{
    extend : 'Ext.data.Store',
    model : 'VirtualPositionModel',
    pageSize : 20,
    proxy : {
        type : 'ajax',
        url : '../authorityDeploy/searchUnmappedVPositionList.action',
        actionMethods : 'POST',
        reader : {
            type : 'json',
            root : 'vpositionList',
            totalProperty : 'totalCount'
        }
    }
});

/**
 * 保存虚拟职位-角色对应关系映射
 * @author 肖红叶
 * 20131128
 * @param {} param
 * @param {} successFn
 * @param {} failureFn
 */
VirtualPositionToRoleData.prototype.saveVPositionAndRoleRelationship = function(param,successFn,failureFn){
    var url = '../authorityDeploy/saveVPositionAndRoleRelationship.action';
    DpUtil.requestAjax(url,param,successFn,failureFn);
}

/**
 * 删除虚拟职位-角色对应关系映射
 * @author 肖红叶
 * 20131128
 * @param {} param
 * @param {} successFn
 * @param {} failureFn
 */
VirtualPositionToRoleData.prototype.deleteVPositionAndRoleRelationship = function(param,successFn,failureFn){
    var url = '../authorityDeploy/deleteVPositionAndRoleRelationship.action';
    DpUtil.requestAjax(url,param,successFn,failureFn);
}

/**
 * 刷新虚拟岗位所对应下面的所有用户的用户角色
 * @author 肖红叶
 * 20131128
 * @param {} param
 * @param {} successFn
 * @param {} failureFn
 */
VirtualPositionToRoleData.prototype.refreshUserRole = function(param,successFn,failureFn){
    var url = '../authorityDeploy/refreshUserRole.action';
    DpUtil.requestTimeLimitAjax(url,param,300000,successFn,failureFn);
}

/**
 * 修改某个关系时，查询出与这个虚拟岗位所映射的已分配角色列表
 * @author 肖红叶
 * 20131128
 * @param {} param
 * @param {} successFn
 * @param {} failureFn
 */
VirtualPositionToRoleData.prototype.searchRoleInfoList = function(param,successFn,failureFn){
    var url = '../authorityDeploy/searchRoleInfoList.action';
    DpUtil.requestAjax(url,param,successFn,failureFn);
}