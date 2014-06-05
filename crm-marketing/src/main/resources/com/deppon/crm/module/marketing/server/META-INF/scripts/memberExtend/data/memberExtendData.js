/**.
 * <p>
 * 开发阶段管理所用获取数据js
 * <p>
 * @author 张斌
 * @时间 2014-3-27
 */
MemberExtendData = function(){
};

/**.
 * <p>
 * 查询客户信息<br/>
 * <p>
 * @param param  查询条件
 * @param successFn 查询成功后的方法
 * @param  failureFn      查询失败后所回调方法
 * @author 张斌
 * @时间 2012-3-27
 */
MemberExtendData.prototype.searchMemberExtend = function(param,successFn,failureFn){
	 var url ='../marketing/searchMemberExtend.action';
	 DButil.requestJsonAjax(url,param,successFn,failureFn);
};
/**.
 * <p>
 * 修改客户信息<br/>
 * <p>
 * @param param  查询条件
 * @param successFn 查询成功后的方法
 * @param  failureFn      查询失败后所回调方法
 * @author 张斌
 * @时间 2012-3-27
 */
MemberExtendData.prototype.updateMemberExtend = function(param,successFn,failureFn){
	 var url ='../marketing/updateMemberExtend.action';
	 DButil.requestJsonAjax(url,param,successFn,failureFn);
};

/**.
 * <p>
 * 根据条件查询日程
 * <p>
 * @author 张登
 * @时间 2012-3-26
 */
Ext.define('MemberExtendScheDuleStore',{
	extend:'Ext.data.Store',
	model:'ScheDuleModel',
	pageSize:20,
	proxy:{
		type:'ajax',
		url:'../marketing/searchCustomerListForMemberExtend.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'scheduleList',
			totalProperty : 'totalCount'
		}
	}
});