
/**
 *营销记录
 */
MarketRecordData = function(){
};

/**
 * 保存页码的数据
 */
Ext.define('PageSizeStore',{
    extend:'Ext.data.Store',
    model:'PageSizeModel',
    data:[
          {value:'5'},
          {value:'10'},
          {value:'15'},
          {value:'20'},
          {value:'25'},
          {value:'40'},
          {value:'100'}
         ]
});

/**
 *  客户需求grid的Store
*/
Ext.define('CustomerDemandStore',{
    extend:'Ext.data.Store',
    model:'CustomerDemandModel',
    data:null
});

/**
 *  走货潜力grid的Store
*/
Ext.define('SendGoodsPontentialGridStore',{
    extend:'Ext.data.Store',
    model:'SendGoodsPontentialGridModel',
    data:null
});

/**
 *  客户基本信息form的Store
*/
Ext.define('CustomerBasicInfoStore',{
    extend:'Ext.data.Store',
    model:'CustomerBasicInfoModel',
    data:null
});


/**
 *  营销历史记录查询之客户需求grid的Store
*/
Ext.define('SearchCustomerDemandGridStore',{
    extend:'Ext.data.Store',
    model:'SearchCustomerDemandGridModel',
    pageSize:20,
    proxy:{
        type:'ajax',
        url:'../marketing/queryCustomerDemands.action',
        actionMethods:'POST',
        reader:{
            type:'json',
            root:'customerDemandsList',
            totalProperty : 'totalCount'
        }
    }
});

/**
 *  营销历史记录查询之走货潜力grid的Store
*/
Ext.define('SearchSendGoodsPontentialStore',{
    extend:'Ext.data.Store',
    model:'SearchSendGoodsPontentialModel',
    pageSize:20,
    proxy:{
        type:'ajax',
        url:'../marketing/querySendGoodsPotentials.action',
        actionMethods:'POST',
        reader:{
            type:'json',
            root:'sendGoodsPotentialList',
            totalProperty : 'totalCount'
        }
    }
});

/**
 *  营销历史记录查询之问卷结果grid的Store
 *  肖红叶
 *  20140324
*/
Ext.define('QuestionnaireResultStore',{
    extend:'Ext.data.Store',
    model:'QuestionnaireResultModel',
    pageSize:20,
    proxy:{
        type:'ajax',
        url:'../marketing/queryQuestionnaireListByCustid.action',
        actionMethods:'POST',
        reader:{
            type:'json',
            root:'questionnaireList',
            totalProperty : 'totalCount'
        }
    }
});

/**
 * Description:根据手机号和部门ID查询客户信息
 * @author 肖红叶
 * @date 2012-11-07
 * @return
 */
MarketRecordData.prototype.queryCustomerByMobile = function(param,successFn,failureFn){
    var url = '../marketing/queryCustomerByMobile.action';
    DButil.requestAjax(url,param,successFn,failureFn);
};

/**
 * Description:根据固话、联系人姓名、部门ID查询客户信息
 * @author 肖红叶
 * @date 2012-11-07
 * @return
 */
MarketRecordData.prototype.queryCustomerByPhoneDeptIdName = function(param,successFn,failureFn){
    var url = '../marketing/queryCustomerByPhoneDeptIdName.action';
    DButil.requestAjax(url,param,successFn,failureFn);
};


/**
 * Description:根据当前登录用户查找部门所在城市
 * @author 肖红叶
 * @date 2012-11-07
 * @return
 */
MarketRecordData.prototype.queryDepartmentWithCurrentUser = function(param,successFn,failureFn){
    var url = '../marketing/queryDepartmentWithCurrentUser.action';
    DButil.requestAjax(url,param,successFn,failureFn);
};


/**
 * Description:保存营销记录,当营销类型为陌生来电时创建本部门下新的潜散客信息
 * @author 肖红叶
 * @date 2012-11-09
 * @return
 */
MarketRecordData.prototype.saveMarketRecord = function(param,successFn,failureFn){
    var url = '../marketing/saveMarketRecord.action';
    DButil.requestJsonAjax(url,param,successFn,failureFn);
};
/**.
 * <p>
 * 根据问卷id查找问卷对应问题信息<br/>
 * <p>
 * @param param  对应参数
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 盛诗庆
 * @时间 2014-03-28
 */
MarketRecordData.prototype.searchQuestionnaireInfoList = function(param,successFn,failureFn){
	var url = '../marketing/searchQuestionnaireInfoList.action';
	DButil.requestAjax(url,param,successFn,failureFn);
};
/**.
 * <p>
 * 根据回访id查找问卷对应问题信息<br/>
 * <p>
 * @param param  对应参数
 * @param successFn 成功后的方法
 * @param  failureFn 失败后所回调方法
 * @author 盛诗庆
 * @时间 2014-03-28
 */
MarketRecordData.prototype.viewQuestionnaireByVisitId = function(param,successFn,failureFn){
	var url = '../marketing/viewQuestionnaireByVisitId.action';
	DButil.requestAjax(url,param,successFn,failureFn);
};
///**
// * Description:营销历史记录查询之客户意见列表
// * @author 肖红叶
// * @date 2012-11
// * @return
// */
//MarketRecordData.prototype.queryCustomerDemands = function(param,successFn,failureFn){
//  var url = '../marketing/queryCustomerDemands.action';
//  DButil.requestJsonAjax(url,param,successFn,failureFn);
//};
//
///**
// * Description:营销历史记录查询之走货潜力列表
// * @author 肖红叶
// * @date 2012-11
// * @return
// */
//MarketRecordData.prototype.querySendGoodsPotentials = function(param,successFn,failureFn){
//  var url = '../marketing/querySendGoodsPotentials.action';
//  DButil.requestJsonAjax(url,param,successFn,failureFn);
//};