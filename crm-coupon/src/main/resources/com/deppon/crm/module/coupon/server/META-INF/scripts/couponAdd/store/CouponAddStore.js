/**
 * 营销计划
 */
CouponAddStore=function(){
    
}

/**
 * 新增营销计划-发券规则-自动返券金额
 */
Ext.define('AutoCouponCostStore',{
    extend:'Ext.data.Store',
    model:'AutoCouponCostModel'
});

/**
 * 新增营销计划-线路区域要求-发到货区域
 */
Ext.define('MakeCrossGoodsLineStore',{
    extend:'Ext.data.Store',
    model:'GoodsLineModel'
});

/**
 * 新增营销计划-线路区域要求-走货线路
 */
Ext.define('MakeWalkGoodsLineStore',{
    extend:'Ext.data.Store',
    model:'GoodsLineModel'
});

/**
 * 新增营销计划-线路区域要求（使用）-发到货区域
 */
Ext.define('UseCrossGoodsLineStore',{
    extend:'Ext.data.Store',
    model:'GoodsLineModel'
});

/**
 * 新增营销计划-线路区域要求（使用）-走货线路
 */
Ext.define('UseWalkGoodsLineStore',{
    extend:'Ext.data.Store',
    model:'GoodsLineModel'
});


/**
 * 营销计划类型
 */
Ext.define('CouponTypeStore',{
    extend:'Ext.data.Store',
    model:'CommonDictionaryModel',
    data:[
        {"code":"HANDCOUPON", "codeDesc":"手动发券"},
        {"code":"AUTOCOUPON", "codeDesc":"自动返券"}
    ]
});


/**
 * 线路区域要求
 */
Ext.define('RuleTypeStore',{
    extend:'Ext.data.Store',
    model:'CommonDictionaryModel',
    data:[
        {"code":"2", "codeDesc":"走货线路"},
        {"code":"3", "codeDesc":"发货区域"},
        {"code":"4", "codeDesc":"到货区域"}
    ]
});

/**
 * 查询走货部门store
 */
Ext.define('WalkGoodDeptStore', {
    extend : 'Ext.data.Store',
    model : 'DeptModel',
    pageSize : 10,
    proxy : {
        type : 'ajax',
        url : '../coupon/searchWalkGoodDept.action',
        actionMethods : 'post',
        reader : {
            type : 'json',
            root : 'executeDeptList',
            totalProperty : 'totalCount'
        }
    }
});

/**
 * 查询部门store
 */
Ext.define('DeptStore', {
    extend : 'Ext.data.Store',
    model : 'DeptModel',
    pageSize : 10,
    proxy : {
        type : 'ajax',
        url : '../coupon/searchDeptByDeptName.action',
        actionMethods : 'post',
        reader : {
            type : 'json',
            root : 'executeDeptList',
            totalProperty : 'totalCount'
        }
    }
});

/**
 * 查询发到货部门store
 */
Ext.define('StartEndGoodDeptStore', {
    extend : 'Ext.data.Store',
    model : 'DeptModel',
    pageSize : 10,
    proxy : {
        type : 'ajax',
        url : '../coupon/searchStartEndGoodDept.action',
        actionMethods : 'post',
        reader : {
            type : 'json',
            root : 'executeDeptList',
            totalProperty : 'totalCount'
        }
    }
});


/**
 * 可使用的市场推广活动store
 * auth：李春雨
 */
Ext.define('ActivityMainStore', {
    extend : 'Ext.data.Store',
    model : 'ActivityMainModel',
    proxy : {
        type : 'ajax',
        url : '../marketing/searchMarketActivityForMarketPlan.action',
        actionMethods : 'post',
        reader : {
            type : 'json',
            root : 'marketActivityList'
        }
    }
});

/**
 * 抵扣类型store
 * auth：李春雨
 */
Ext.define('ActivityOptionStore', {
    extend : 'Ext.data.Store',
    model : 'ActivityOptionModel',
    proxy : {
        type : 'ajax',
        url : '../marketing/searchActivityOptionByActivityIdAndType.action',
        actionMethods : 'post',
        reader : {
            type : 'json',
            root : 'activityOptionList'
        }
    },
    listeners : {  
        load : function(store,records){
           store.filterBy(function(record){
               record.set('code',record.get('type'));
               record.set('codeDesc',rendererDictionary(record.get('code'),DataDictionary.DISCOUNT_PRODUCTS));
               return true;
           });         
           
          }
       }
});

/**.
 * <p>
 * 保存优惠券
 * <p>
 * @param param  对应参数
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 张登
 * @时间 2012-4-6
 */
CouponAddStore.prototype.saveCoupon = function(param,successFn,failureFn){
    var url = '../coupon/saveCoupon.action';
    DpUtil.requestJsonAjax(url,param,successFn,failureFn);
};

/**.
 * <p>
 * 查询优惠券详情
 * <p>
 * @param param  对应参数
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 张登
 * @时间 2012-4-6
 */
CouponAddStore.prototype.searchMarketDetail = function(param,successFn,failureFn){
    var url = '../coupon/searchMarketDetail.action';
    DpUtil.requestAjax(url,param,successFn,failureFn);
};

/**.
 * <p>
 * 终止营销计划
 * <p>
 * @param param  对应参数
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 张登
 * @时间 2012-4-6
 */
CouponAddStore.prototype.stopMarketPlan = function(param,successFn,failureFn){
    var url = '../coupon/stopMarketPlan.action';
    DpUtil.requestAjax(url,param,successFn,failureFn);
};

/**.
 * <p>
 * 删除营销计划
 * <p>
 * @param param  对应参数
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 张登
 * @时间 2012-4-6
 */
CouponAddStore.prototype.deleteMarketPlan = function(param,successFn,failureFn){
    var url = '../coupon/deleteMarketPlan.action';
    DpUtil.requestAjax(url,param,successFn,failureFn);
};

/**.
 * <p>
 * 启用营销计划
 * <p>
 * @param param  对应参数
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 张登
 * @时间 2012-4-6
 */
CouponAddStore.prototype.startMarketPlan = function(param,successFn,failureFn){
    var url = '../coupon/startMarketPlan.action';
    DpUtil.requestAjax(url,param,successFn,failureFn);
};

/**.
 * <p>
 * 更新营销计划
 * <p>
 * @param param  对应参数
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 张登
 * @时间 2012-4-6
 */
CouponAddStore.prototype.updateMarketPlan = function(param,successFn,failureFn){
    var url = '../coupon/updateMarketPlan.action';
    DpUtil.requestJsonAjax(url,param,successFn,failureFn);
};


/**.
 * <p>
 * 营销计划创建时查询现在可用的市场推广活动
 * <p>
 * @param param  对应参数
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 张登
 * @时间 2012-4-6
 */
CouponAddStore.prototype.searchMarketActivityForMarketPlan = function(param,successFn,failureFn){
    var url = '../marketing/searchMarketActivityForMarketPlan.action';
    DpUtil.requestJsonAjax(url,param,successFn,failureFn);
};

/**.
 * <p>
 * 营销计划创建时查询现在可用的市场推广活动
 * <p>
 * @param param  对应参数
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 张登
 * @时间 2012-4-6
 */
CouponAddStore.prototype.searchActivityOptionByActivityIdAndType = function(param,successFn,failureFn){
    var url = '../marketing/searchActivityOptionByActivityIdAndType.action';
    DpUtil.requestJsonAjax(url,param,successFn,failureFn);
};