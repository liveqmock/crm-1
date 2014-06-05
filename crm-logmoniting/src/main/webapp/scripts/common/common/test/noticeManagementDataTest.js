/**
 * @class:oNoticeManagementDataTest 公告数据Data层测试数据
 * @param:{jP,fnS,fnF}  
 * 		jP 	:{}	json参数
 * 		fnS	:Fn	ajax成功回调函数
 * 		fnF	:Fn	ajax失败回调函数
 * @exception
 * @author Rock
 * 2012-09-22
 */

var oNoticeManagementDataTest = function(){};
oNotoNoticeManagementDataTest.prototype = {
	/**
	 * @method:新增公告
	 */
	addNotice: function(jP,fnS,fnF){
		var u = 'addNotice.action';				
		DpUtil.requestJsonAjax(u,jP,fnS,fnF);
	},
	/**
	 * @method:批量删除公告
	 */
	delNoticeA: function(jP,fnS,fnF){
		var u = 'delteNoticesByIdList.action';
		DpUtil.requestJsonAjax(u,jP,fnS,fnF);
	},
	topNotice:function(jP,fnS,fnF){
		var u = 'topNotice.action';
		DpUtil.requestJsonAjax(u,jP,fnS,fnF);
	},
	/**
	 * @method:删除单条公告
	 */
	delNoticeB: function(jP,fnS,fnF){
		var u = 'deleteNoticeById.action';
		DpUtil.requestJsonAjax(u,jP,fnS,fnF);
	},
	/**
	 * @method:修改公告/更新公告状态
	 */
	updNotice: function(jP,fnS,fnF){
		var u = 'updateNotice.action';
		DpUtil.requestJsonAjax(u,jP,fnS,fnF);
	},
	/**
	 * @method:暂不发布：批量修改显隐状态
	 */
	changeStatus: function(jP,fnS,fnF){
		var u = 'changeStatus.action';
		DpUtil.requestJsonAjax(u,jP,fnS,fnF);
	},
	// /**
	 // * @method:获取表格model 
	 // */
	// fnGetModel: function(){
		// var model = Ext.define('helpDocGridModel',{
			// extend: 'Ext.data.Model',
			// fields : [
			// {name:'id'}										// 公告id					// 
			// ,{name:'title'}									// 公告标题					// 必填
			// ,{name:'type'}									// 公告类别					// 必填
			// ,{name:'moduleBelong'}							// 所属模块					// 必填
			// ,{name:'content'}								// 公告内容					// 必填
			// ,{name:'active'}								// 是否发布					// 必填	BOOLEN
			// ,{name:'top'}									// 是否置顶					// 必填	BOOLEN
			// ,{name:'modifyDate'}							// 更新时间
			// ,{name:'visits'}								// 访问次数
			// ,{name:'createDate'}							// 创建时间
			// ,{name:'modifyUser'}							// 更新人
			// ,{name:'createUser'}							// 创建人
				// ]									// 最后修改时间
		// });
		// return model;
	// },
	/**
	 * @method:查询公告文档-1: 初始查询出所有的公告文档	后更改为获取表格的store，发送数据使用 beforeload
	 */
	searchNoticeA:function(){			
		Ext.define('searchDocAllStore',{
			extend:'Ext.data.Store',
			model:'noticeDocModel',
			autoLoad:true,
			pageSize:40,
			proxy:{
				type:'ajax',
				url:'searchNotices.action',
				actionMethods:'POST',
				reader:{
					type:'json',
					root:'noticeList'
					,totalProperty : 'totalCount'
				}
			}
		});
		return new searchDocAllStore();
	},
	/**
	 * @method:查询公告文档-2: 根据查询表单的查询条件查询
	 * @param:jP:oCondition
	 * @return: list
	 */
	searchNoticeB:function(jP,fnS,fnF){
		var u = 'searchNoticeB.action';
		DpUtil.requestJsonAjax(u,jP,fnS,fnF);
	},
	/**
	 * @method:查询公告文档-3: 根据公告id查询
	 * @param:jP:id
	 * @return: list
	 */
	searchNoticeC:function(jP,fnS,fnF){
		var u = 'searchNoticeC.action';
		DpUtil.requestJsonAjax(u,jP,fnS,fnF);
	},
	/**
	 * @method:获取所属模块combox的store		// 后修改为
	 * 										select:function(th){
	 * 											Ext.getCmp("belongMenuId").store.load({params:{'functionCode':th.getValue()}} );
	 * 										}，
	 * 									// 需要在这之前：	1.后者使用store	store : Ext.create('srchMenuStore')，		；
	 * 														2.然后前者在select事件触发 ，并将参数load进去。
	 */
	fnCreatModuBeyd:function(jP,fnS,fnF){
		var u = 'srchModu.action';
		Ext.define('moduBeydModel',{
			extend: 'Ext.data.Model',
			fields : [
				{name:'functionName'}
				,{name:'functionCode'}
				]
		});
		Ext.define('srchModuStore',{
			extend:'Ext.data.Store',
			model:'moduBeydModel',
			autoLoad:false,
			proxy:{
				type:'ajax',
				url:'../authorization/srchModu.action',					//	"/authorization"	————>	"../authorization"
				actionMethods:'POST',
				reader:{
					type:'json',
					root:'functionList'
					// ,totalProperty : 'totalCount'
				}
			}
		});
		return new srchModuStore();
	}
};
