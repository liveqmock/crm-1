/**.
 * <p>
 * 商机管理所用获取数据js
 * <p>
 * @author 张斌
 * @时间 2014-3-07
 */
BusinessOpportunityData = function(){
};
/**.
 * <p>
 * 获取商机查询STORE<br/>
 * <p>
 * @returns CRM.BO.BusinessOpportunity.SearchStore  商机查询store
 * @author 张斌
 * @时间 2014-3-07
 */
BusinessOpportunityData.prototype.getBusinessOpportunityStore = function(){
	Ext.define('CRM.BO.BusinessOpportunity.SearchStore',{
		pageSize : 20,
		extend:'Ext.data.Store',
		model:'CRM.BO.BusinessOpportunity',
		proxy:{
			type:'ajax',
			url:'../marketing/searchBusinessOpportunityList.action',
			actionMethods:'POST',
			timeout:100000,
			reader:{
				type:'json',
				root:'businessOpportunityList',
				totalProperty : 'totalCount'
			}
		}
	});
	return new CRM.BO.BusinessOpportunity.SearchStore();
};
/**.
 * <p>
 * 获取商机日程查询STORE<br/>
 * <p>
 * @returns CRM.BO.BusinessOpportunity.ScheduleStore  商机日程查询store
 * @author 张斌
 * @时间 2014-3-19
 */
BusinessOpportunityData.prototype.getScheduleStore = function(){
	Ext.define('CRM.BO.BusinessOpportunity.ScheduleStore',{
		pageSize : 5000,
		extend:'Ext.data.Store',
		model:'CRM.BO.SearchSchedule',
		proxy:{
			type:'ajax',
			url:'../marketing/searchBusinessSchedule.action',
			actionMethods:'POST',
			timeout:100000,
			reader:{
				type:'json',
				root:'scheduleVOList',
				totalProperty : 'totalCount'
			}
		}
	});
	return new CRM.BO.BusinessOpportunity.ScheduleStore();
};
/**.
 * <p>
 * 获取商机责任人可选择的责任人STORE<br/>
 * <p>
 * @author 张斌
 * @时间 2014-3-11
 */
BusinessOpportunityData.prototype.getEmpByDeptId = function(){
	var param = {'executeDept':User.deptId};
	var url ='../marketing/queryEmployeesByDeptId.action';
	Ext.Ajax.request({
		url : url,
		async:false,
		jsonData:param,
		success:function(response){
			var result = Ext.decode(response.responseText);
			EMP=result.planeDraftList;
		},
		failure:function(response){
			var result = Ext.decode(response.responseText);
			MessageUtil.showMessage(result.message);
		}
	});
};

/**.
 * <p>
 * 校验当前登录人是否为本部门负责人<br/>
 * <p>
 * @author 张斌
 * @时间 2014-3-11
 */
BusinessOpportunityData.prototype.isMainPerson = function(){
	var param = {'deptId':User.deptId};
	var url ='../marketing/queryDeptById.action';
	Ext.Ajax.request({
		url : url,
		async:false,
		jsonData:param,
		success:function(response){
			var result = Ext.decode(response.responseText);
			var dept=result.dept;
			if(dept.principal==User.empCode){//当前登录人所在部门负责人的工号和当前登录人的工号相同
				isMainMan = true;
			}
		},
		failure:function(response){
			var result = Ext.decode(response.responseText);
			MessageUtil.showMessage(result.message);
		}
	});
};
/**.
 * <p>
 * 新增商机信息<br/>
 * <p>
 * @param param  查询条件
 * @param successFn 查询成功后的方法
 * @param  failureFn      查询失败后所回调方法
 * @author 张斌
 * @时间 2012-3-9
 */
BusinessOpportunityData.prototype.addBo = function(param,successFn,failureFn){
	 var url ='../marketing/createBusinessOpportunity.action';
	 DButil.requestJsonAjax(url,param,successFn,failureFn);
};
/**.
 * <p>
 * 查询联系人信息<br/>
 * <p>
 * @param param  查询条件
 * @param successFn 查询成功后的方法
 * @param  failureFn      查询失败后所回调方法
 * @author 张斌
 * @时间 2012-3-9
 */
BusinessOpportunityData.prototype.searchLinkMan = function(param,successFn,failureFn){
	 var url ='../customer/acquireMemberById.action';
	 DButil.requestJsonAjax(url,param,successFn,failureFn);
};
/**.
 * <p>
 * 修改商机信息<br/>
 * <p>
 * @param param  查询条件
 * @param successFn 查询成功后的方法
 * @param  failureFn      查询失败后所回调方法
 * @author 张斌
 * @时间 2012-3-9
 */
BusinessOpportunityData.prototype.updateBo = function(param,successFn,failureFn){
	 var url ='../marketing/saveBusinessOpportunity.action';
	 DButil.requestJsonAjax(url,param,successFn,failureFn);
};
/**.
 * <p>
 * 查看商机信息<br/>
 * <p>
 * @param param  查询条件
 * @param successFn 查询成功后的方法
 * @param  failureFn      查询失败后所回调方法
 * @author 张斌
 * @时间 2012-3-9
 */
BusinessOpportunityData.prototype.queryBo = function(param,successFn,failureFn){
	 var url ='../marketing/queryBusinessOpportunity.action';
	 DButil.requestJsonAjax(url,param,successFn,failureFn);
};

/**.
 * <p>
 * 修改商机日程信息<br/>
 * <p>
 * @param param  查询条件
 * @param successFn 查询成功后的方法
 * @param  failureFn      查询失败后所回调方法
 * @author 张斌
 * @时间 2012-3-9
 */
BusinessOpportunityData.prototype.updateBoSchedule = function(param,successFn,failureFn){
	 var url ='../marketing/updateDevelopSchedule.action';
	 DButil.requestJsonAjax(url,param,successFn,failureFn);
};
/**.
 * <p>
 * 删除商机日程信息<br/>
 * <p>
 * @param param  查询条件
 * @param successFn 查询成功后的方法
 * @param  failureFn      查询失败后所回调方法
 * @author 张斌
 * @时间 2012-3-9
 */
BusinessOpportunityData.prototype.deleteBoSchedule = function(param,successFn,failureFn){
	 var url ='../marketing/deleteDevelopSchedule.action';
	 DButil.requestJsonAjax(url,param,successFn,failureFn);
};

/**.
 * <p>
 * 新增商机日程信息<br/>
 * <p>
 * @param param  查询条件
 * @param successFn 查询成功后的方法
 * @param  failureFn      查询失败后所回调方法
 * @author 张斌
 * @时间 2012-3-9
 */
BusinessOpportunityData.prototype.addBoSchedule = function(param,successFn,failureFn){
	 var url ='../marketing/saveSchedule.action';
	 DButil.requestJsonAjax(url,param,successFn,failureFn);
};
