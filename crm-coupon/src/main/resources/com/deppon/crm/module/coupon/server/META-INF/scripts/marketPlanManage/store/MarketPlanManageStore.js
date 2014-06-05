/**
 *营销计划管理
 */
MarketPlanMangeStore = function(){
};

/**
 * 营销计划管理查询结果列表的store
 */
Ext.define('MarketPlanManageListStore',{
    extend:'Ext.data.Store',
    model:'MarketPlanManageListModel',
    pageSize:20,
    proxy:{
        type:'ajax',
        url:'../coupon/searchMarketPlan.action',
        actionMethods:'POST',
        reader:{
            type:'json',
            root:'listMarketplan',
            totalProperty : 'totalCount'
        }
    }
});

/**
 * 短信发券页面中批量处理手机号的store
 * 肖红叶
 */
Ext.define('BatchHandleTelephonesStore',{
    extend:'Ext.data.Store',
    model:'BatchDealTelephoneModel',
    data:null
});

/**
 * Description:根据营销计划ID查询短信发券页面中优惠券的基本信息
 * @author 肖红叶
 * @date 2012-11-26
 * @return
 */
MarketPlanMangeStore.prototype.searchSendCouponVOByMPI = function(param,successFn,failureFn){
    var url = '../coupon/searchSendCouponVOByMPI.action';
    CouponUtil.requestAjax(url,param,successFn,failureFn);
};

/**
 * Description: 短信发券页面，单击发送按钮，将发送的手机号写入优惠券信息中
 * @author 肖红叶
 * @date 2012-11-26
 * @return
 */
MarketPlanMangeStore.prototype.sendCouponByCellphones = function(param,successFn,failureFn){
    var url = '../coupon/sendCouponByCellphones.action';
    CouponUtil.requestJsonAjax(url,param,successFn,failureFn);
};

/**
 * Description: 短信发券页面中批量处理手机号,包括批量清除无效手机号码，批量删除重复号码
 * @author 肖红叶
 * @date 2012-11-26
 * @return
 */
MarketPlanMangeStore.prototype.batchHandleTelephones = function(param,successFn,failureFn){
    var url = '../coupon/batchHandleTelephones.action';
    CouponUtil.requestJsonAjax(url,param,successFn,failureFn);
};

/**
 * Description: 短信发券页面中批量处理手机号,包括批量清除无效手机号码，批量删除重复号码
 * @author 李春雨
 * @date 2014-05-04
 * @return
 */
MarketPlanMangeStore.prototype.queryActivityAll = function(param,successFn,failureFn){
    var url = '../marketing/queryActivityAll.action';
    CouponUtil.requestJsonAjax(url,param,successFn,failureFn);
};
