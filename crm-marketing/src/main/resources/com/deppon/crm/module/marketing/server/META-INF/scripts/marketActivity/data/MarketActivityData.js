/**
 * 活动
 */
ActivityRecordData = function(){
};

/**
 * 文件信息store
 */
Ext.define('FileInfoStore', {
    extend : 'Ext.data.Store',
    model : 'FileInfoModel'
});

/**
 * 部门grid用store
 */
Ext.define('DeptStore',{
    extend : 'Ext.data.Store',
    model : 'DeptTreeModel'
});

/**
 * 二级行业store
 */
Ext.define('SecondTradesStore', {
    extend : 'Ext.data.Store',
    model : 'SecondTradeModel',
    proxy: {
        type : 'ajax',
        url : '../marketing/searchSecondTradesByList.action',
        actionMethods : 'post',
        reader : {
            type : 'json',
            root : 'detailList'
        }
    }
});


/**
 * 部门树store
 */
Ext.define('DeptTreeStore', {
    extend : 'Ext.data.TreeStore'
});

/**
 * 联系人store
 */
Ext.define('UserInfoStore',{
    extend : 'Ext.data.Store',
    model : 'UserInfo',
    pageSize : 10,
    proxy : {
        type : 'ajax',
        url : '../marketing/searchEmpByCondition.action',
        actionMethods : 'post',
        reader : {
            type : 'json',
            root : 'userInfoList',
            totalProperty : 'totalCount'
        }
    }
});

/**.
 * <p>
 * 查询活动的主信息
 * <p>
 * @author 李春雨
 * @时间 2014-3-18
 */
Ext.define('ActivityDataStore',{
    extend:'Ext.data.Store',
    model:'MarketActivityVO',
    pageSize:20,
    proxy:{
        type:'ajax',
        url:'../marketing/queryActivityMain.action',
        actionMethods:'POST',
        reader:{
            type:'json',
            root:'marketActivityVOList',
            totalProperty : 'totalCount'
        }
    }
});

/**.
 * <p>
 * 查询线路下拉列表
 * <p>
 * @author 李春雨
 * @时间 2014-3-18
 */
Ext.define('WalkGoodDeptStore',{
    extend : 'Ext.data.Store',
    model : 'DeptModel',
    pageSize : 10,
    proxy : {
        type : 'ajax',
        url : '../marketing/searchDeptListByName.action',
        actionMethods : 'post',
        reader : {
            type : 'json',
            root : 'departmentList',
            totalProperty : 'totalCount'
        }
    }
});

/**.
 * <p>
 * 查询客户群信息下拉列表
 * <p>
 * @author 李春雨
 * @时间 2014-3-18
 */
Ext.define('ActivityClientBaseStore',{
    extend:'Ext.data.Store',
    model:'ActivityClientBase',
    pageSize:10,
    proxy:{
        type:'ajax',
        url:'../marketing/searchActivityClientByName.action',
        actionMethods:'POST',
        reader:{
            type:'json',
            root:'activityClientBaseList',
            totalProperty : 'totalCount'
        }
    }
});

/**.
 * <p>
 * 查询该活动的客户群下拉列表
 * <p>
 * @author 李春雨
 * @时间 2014-3-18
 */
Ext.define('LoadUsedClientBaseStore',{
    extend:'Ext.data.Store',
    model:'ActivityClientBase',
    proxy:{
        type:'ajax',
        url:'../marketing/loadUsedClientBase.action',
        actionMethods:'POST',
        reader:{
            type:'json',
            root:'activityClientBaseList'
        }
    }
});

/**.
 * <p>
 * 物料优惠券以及折扣的store
 * <p>
 * @author 李春雨
 * @时间 2014-3-18
 */
Ext.define('ActivityOptionDataStore',{
    extend:'Ext.data.Store',
    model:'ActivityOptionModel'
});

/**.
 * <p>
 * 线路store
 * <p>
 * @author 李春雨
 * @时间 2014-3-18
 */
Ext.define('UseWalkGoodsLineStore',{
    extend:'Ext.data.Store',
    model:'LineDeptModel'
});

/**
 * 初始化市场推广活动
 * @param param  对应参数
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 李春雨
 * @时间 2013-03-18
 */
ActivityRecordData.prototype.initMarketActivity = function(param,successFn,failureFn){
    var url = '../marketing/initMarketActivity.action';
    DButil.requestAjax(url,param,successFn,failureFn);
};

/**
 * 获取部门的编码
 * @param param  对应参数
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 李春雨
 * @时间 2013-03-18
 */
ActivityRecordData.prototype.gainUserDeptCharacter = function(param,successFn,failureFn){
    var url = '../marketing/gainUserDeptCharacter.action';
    DButil.requestAjax(url,param,successFn,failureFn);
};

/**
 * 保存文件信息
 * @param param  对应参数
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 李春雨
 * @时间 2013-03-18
 */
ActivityRecordData.prototype.saveFileInfo = function(param,successFn,failureFn){
    var url = '../marketing/saveFileInfo.action';
    DButil.requestJsonAjax(url,param,successFn,failureFn);
};

/**
 * 保存市场推广活动所有信息
 * @param param  对应参数
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 李春雨
 * @时间 2013-03-18
 */
ActivityRecordData.prototype.saveMarketActivity = function(param,successFn,failureFn){
    var url = '../marketing/saveMarketActivity.action';
    DButil.requestJsonAjax(url,param,successFn,failureFn);
};

/**
 * 删除文件信息
 * @param param  对应参数
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 李春雨
 * @时间 2013-03-18
 */
ActivityRecordData.prototype.deleteFileInfo = function(param,successFn,failureFn){
    var url = '../marketing/deleteFileInfo.action';
    DButil.requestJsonAjax(url,param,successFn,failureFn);
};

/**
 * 查询推广活动的所有信息
 * @param param  对应参数
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 李春雨
 * @时间 2013-03-18
 */
ActivityRecordData.prototype.queryActivityAll = function(param,successFn,failureFn){
    var url = '../marketing/queryActivityAll.action';
    DButil.requestJsonAjax(url,param,successFn,failureFn);
};

/**
 * 保存线路信息
 * @param param  对应参数
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 李春雨
 * @时间 2013-03-18
 */
ActivityRecordData.prototype.saveLineInformation = function(param,successFn,failureFn){
    var url = '../marketing/saveLineInformation.action';
    DButil.requestJsonAjax(url,param,successFn,failureFn);
};


/**
 * 根据Id查询客户群
 * @param param  对应参数
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 李春雨
 * @时间 2013-03-18
 */
ActivityRecordData.prototype.searchClientBaseById = function(param,successFn,failureFn){
    var url = '../marketing/searchClientBaseById.action';
    DButil.requestAjax(url,param,successFn,failureFn);
};


/**
 * 保存参与部门信息
 * @param param  对应参数
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 李春雨
 * @时间 2013-03-18
 */
ActivityRecordData.prototype.saveActivityDept = function(param,successFn,failureFn){
    var url = '../marketing/saveActivityDept.action';
    DButil.requestAjax(url,param,successFn,failureFn);
};


/**
 * 修改市场推广活动客户群
 * @param param  对应参数
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 李春雨
 * @时间 2013-03-18
 */
ActivityRecordData.prototype.updateMarketActivityClient = function(param,successFn,failureFn){
    var url = '../marketing/updateMarketActivityClient.action';
    DButil.requestJsonAjax(url,param,successFn,failureFn);
};

/**
 * 下发客户群操作
 * @param param  对应参数
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 李春雨
 * @时间 2013-03-18
 */
ActivityRecordData.prototype.issuedClient = function(param,successFn,failureFn){
    var url = '../marketing/issuedClient.action';
    DButil.requestAjax(url,param,successFn,failureFn);
};

/**
 * 工作流验证操作
 * @param param  对应参数
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 李春雨
 * @时间 2013-03-18
 */
ActivityRecordData.prototype.workFlowNumVerify = function(param,successFn,failureFn){
    var url = '../marketing/workFlowNumVerify.action';
    DButil.requestJsonAjax(url,param,successFn,failureFn);
};

/**
 * 加载部门树数据
 * @param param  对应参数
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 李春雨
 * @时间 2013-03-18
 */
ActivityRecordData.prototype.loadMarket = function(param,successFn,failureFn){
    var url = '../marketing/loadMarket.action';
    DButil.requestJsonAjax(url,param,successFn,failureFn);
};

/**
 * 加载线路树数据
 * @param param  对应参数
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 李春雨
 * @时间 2013-03-18
 */
ActivityRecordData.prototype.loadDeliverOrArriveTree = function(param,successFn,failureFn){
    var url = '../marketing/loadDeliverOrArriveTree.action';
    DButil.requestJsonAjax(url,param,successFn,failureFn);
};

/**
 * 验证活动名称是否重复
 * @param param  对应参数
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 李春雨
 * @时间 2013-04-17
 */
ActivityRecordData.prototype.checkHaveSamNameMarketActivity = function(param,successFn,failureFn){
    var url = '../marketing/checkHaveSamNameMarketActivity.action';
    DButil.requestJsonAjax(url,param,successFn,failureFn);
};

/**
 * 计算部门数量
 * @param param  对应参数
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 李春雨
 * @时间 2013-04-17
 */
ActivityRecordData.prototype.countActivityDeptNum = function(param,successFn,failureFn){
    var url = '../marketing/countActivityDeptNum.action';
    DButil.requestAjax(url,param,successFn,failureFn);
};

/**
 * 一次性保存所有的活动信息
 * @param param  对应参数
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 李春雨
 * @时间 2013-05-05
 */
ActivityRecordData.prototype.saveMarketActivityAndDepts = function(param,successFn,failureFn){
    var url = '../marketing/saveMarketActivityAndDepts.action';
    DButil.requestJsonAjax(url,param,successFn,failureFn);
};