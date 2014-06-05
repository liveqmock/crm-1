//公告Store	
var noticeStore = Ext.create('Ext.data.Store', {
    storeId:'simpsonsStore',
    model:'NoticeGridModel',
    proxy: {
    	type:'ajax',
	    url:'../common/searchIndexNotices.action',
	    actionMethods : 'post',
        reader: {
            type: 'json',
            root:'noticeList'
            	
        }
    }
});

//菜单store
var indexTreeStore = Ext.create('Ext.data.TreeStore', {
	proxy : {
		type : 'ajax',
		actionMethods : 'POST',
//		params:{'node':parent.window.tabPanelTitle.activeTab.id},
		url : '../authorization/loadUsualFunctionTree.action?isIndex=1',
		reader : {
			type : 'json'
		}
	}
});


//问题描述store
Ext.define('ProblemStore', {
	extend: 'Ext.data.Store',
	model: 'ProblemModel',
	proxy : {
		type : 'ajax',
		url : '../common/showProblemList.action',
		actionMethods : 'post',
		reader : {
			type : 'json',
			root : 'listProblem'
		}
	}
});

/**
 * 最新公告数据源
 * @param param
 * @param successFn
 * @param failFn
 */
function newNoticeData(param,successFn,failFn){
	var url='../common/searchIndexNotices.action';
	DpUtil.requestJsonAjax(url,param,successFn,failFn);
}

/**
 * 最新一条数据
 * @param param
 * @param successFn
 * @param failFn
 */
function searchIndexNewNotice(param,successFn,failFn){
	var url='../common/searchIndexNewNotice.action';
	DpUtil.requestJsonAjax(url,param,successFn,failFn);
}


/**
 * 根据公告ID获取公告信息
 * @param param
 * @param successFn
 * @param failFn
 */
function findNoticeById(param,successFn,failFn){
	var url='../common/findNoticeById.action';
	DpUtil.requestJsonAjax(url,param,successFn,failFn);
}
