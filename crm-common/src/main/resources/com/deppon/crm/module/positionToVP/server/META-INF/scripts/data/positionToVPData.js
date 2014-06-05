 /**
 * 
 * <p>
 * Description:标准岗位对虚拟岗位信息配置管理data<br />
 * </p>
 * @title positionToVPData.js
 * @author 李春雨
 * @version 0.1 2013-11-26
 */
var PositionToVPData  = function(){

};

/**
 * 虚拟职位信息
 */
Ext.define('VPositionDataStore',{
    extend : 'Ext.data.Store',
    model : 'VPositionModel',
    autoLoad : true,
    pageSize : 20,
    proxy : {
        type : 'ajax',
        url : '../authorityDeploy/searchAllVPosition.action',
        actionMethods : 'POST',
        reader : {
            type : 'json',
            root : 'vpositionList',
            totalProperty : 'totalCount'
        }
    }
});

/**
 * 未分配标准职位信息
 */
Ext.define('UndistributedPositionDataStore',{
    extend : 'Ext.data.Store',
    model : 'PositionModel',
    autoLoad : false,
    proxy : {
        type : 'ajax',
        url : '../authorityDeploy/staPositionWaitToMapWithVP.action',
        actionMethods : 'POST',
        reader : {
            type : 'json',
            root : 'ehrPositionList'
        }
    },
    listeners : {
        load : function(store,records,uccessful,eOpts){
            var girdcount = 0;
            var hasModel = new Array();
            store.each(function(record){
                //selectedPositionGridId
                var rightGrid = Ext.getCmp('selectedPositionGridId');
                //如果在右边grid中能找到数据则删除此元素
                if(rightGrid.getStore().find('pkEhrPosition',record.data.pkEhrPosition,0,false,true,true) != -1){
                    hasModel.push(record);
                    var temp;
                }
            });
            store.remove(hasModel);
        }
    }
})

/**
 * 已分配标准职位信息
 */
Ext.define('DistributionPositionDataStore',{
    extend : 'Ext.data.Store',
    model : 'PositionModel',
    autoLoad : false,
    proxy : {
        type : 'ajax',
        url : '../authorityDeploy/staPositionHaveMappedWithVP.action',
        actionMethods : 'POST',
        reader : {
            type : 'json',
            root : 'ehrPositionList'
        }
    },
    listeners : {
        load : function(store,records,uccessful,eOpts){
            store.each(function(record){
                record.data.isLeft = false;
            });
        }
    }
})




/**
 * 新增虚拟岗位
 * @author 李春雨
 * @param {} param
 * @param {} successFn
 * @param {} failureFn
 */
PositionToVPData.prototype.addVirtualPosition = function(param,successFn,failureFn){
    var url = '../authorityDeploy/addVirtualPosition.action';
    DpUtil.requestAjax(url,param,successFn,failureFn);
}

/**
 * 删除虚拟岗位信息
 * @author 李春雨
 * @param {} param
 * @param {} successFn
 * @param {} failureFn
 */
PositionToVPData.prototype.deleteVirtualPositionById = function(param,successFn,failureFn){
    var url = '../authorityDeploy/deleteVirtualPositionById.action';
    DpUtil.requestAjax(url,param,successFn,failureFn);
}

/**
 * 判断是否可以删除虚拟岗位
 * @author 李春雨
 * @param {} param
 * @param {} successFn
 * @param {} failureFn
 */
PositionToVPData.prototype.canDeleteVirtualPosition = function(param,successFn,failureFn){
    var url = '../authorityDeploy/canDeleteVirtualPosition.action';
    DpUtil.requestAjax(url,param,successFn,failureFn);
}

/**
 * 虚拟职位”为文本框，可手动输入，输入完毕后进行自动校验，
 * 若与现有虚拟职位名称重复则提示“已存在该职位，请核实”
 * @author 李春雨
 * @param {} param
 * @param {} successFn
 * @param {} failureFn
 */
PositionToVPData.prototype.isExistVirtualPositionName = function(param,successFn,failureFn){
    var url = '../authorityDeploy/isExistVirtualPositionName.action';
    DpUtil.requestAjax(url,param,successFn,failureFn);
}

/**
 * 修改虚拟岗位信息
 * @author 李春雨
 * @param {} param
 * @param {} successFn
 * @param {} failureFn
 */
PositionToVPData.prototype.updateVirtualPosition = function(param,successFn,failureFn){
    var url = '../authorityDeploy/updateVirtualPosition.action';
    DpUtil.requestAjax(url,param,successFn,failureFn);
}