
//描述：更多公告界面查询条件里面所属模块的Store
var mokuaiStore = Ext.create('Ext.data.Store',{
						model:'ComboMokuaiModel',
						proxy: {
					    	type:'ajax',
						    url:'../authorization/srchModu.action',
						    actionMethods : 'post',
					        reader: {
					            type: 'json',
					            root:'functionList'
					        }
					    }
					});


//描述：更多公告界面grid底部选择显示行数的Store
var valueStore = Ext.create("Ext.data.Store", {
					model :'GridComboModel',
					data : [{
						value:'30'
					}, {
						value:'40'
					}, {
						value:'50'
					}]
				});

//描述：更多公告界面grid显示的Store
var gridStore = Ext.create('Ext.data.Store',{
					pageSize:30,
					model:'NoticeGridModel',
					proxy: {
				    	type:'ajax',
					    url:'../common/searchMoreNotices.action',
					    actionMethods : 'post',
				        reader: {
				            type: 'json',
				            root:'noticeList',
				            totalProperty:'totalCount'
				        }
				    }
				});

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

