/**
 * 
 * <p>
 * Description:邮件配置管理data<br />
 * </p>
 * @title SysmailData.js
 * @author 石应华
 * @version 0.1 2013-9-24
 */

var SysmailData  = function(){

};

/**
 * 邮件组store
 */
Ext.define('SysmailGroupStore',{
	extend:'Ext.data.Store',
	model:'MailGroupModel',
	pageSize:20,
	proxy:{
		type:'ajax',
		url:'../sysmail/searchAllMailGroup.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'mailGroupList',
			totalProperty : 'totalCount'
		}
	}
});

/**
 * 收件人store
 */
Ext.define('SysmailMailAccountStore',{
    extend:'Ext.data.Store',
    model:'AccGroResultModel',
    pageSize:20,
    proxy:{
        type:'ajax',
        url:'../sysmail/searchMailAccount.action',
        actionMethods:'POST',
        reader:{
            type:'json',
            root:'accGroResultList',
            totalProperty : 'totalCount'
        }
    }
});


/**
 * 移出收件人
 * @param {} param
 * @param {} successFn
 * @param {} failureFn
 */
SysmailData.prototype.removeMailAccounts = function(param,successFn,failureFn){
	var url='../sysmail/removeMailAccounts.action';
	DpUtil.requestAjax(url,param,successFn,failureFn);
}

/**
 * 删除收件人
 * @param {} param
 * @param {} successFn
 * @param {} failureFn
 */
SysmailData.prototype.deleteMailAccounts = function(param,successFn,failureFn){
    var url='../sysmail/deleteMailAccounts.action';
    DpUtil.requestAjax(url,param,successFn,failureFn);
}
/**
 * 划分收件人到分组
 * @param {} param
 * @param {} successFn
 * @param {} failureFn
 */
SysmailData.prototype.dividePeopleTo = function(param,successFn,failureFn){
    var url='../sysmail/dividePeopleTo.action';
    DpUtil.requestAjax(url,param,successFn,failureFn);
}
/**
 * 添加邮件组信息
 * @author 李春雨
 * @param {} param
 * @param {} successFn
 * @param {} failureFn
 */
SysmailData.prototype.addMailGroup = function(param,successFn,failureFn){
	var url = '../sysmail/addMailGroup.action';
	DpUtil.requestAjax(url,param,successFn,failureFn);
}
/**
 * 修改邮件组信息
 * @author 李春雨
 * @param {} param
 * @param {} successFn
 * @param {} failureFn
 */
SysmailData.prototype.updateMailGroup = function(param,successFn,failureFn){
	var url = '../sysmail/updateMailGroup.action';
	DpUtil.requestAjax(url,param,successFn,failureFn);
}
/**
 * 删除邮件组信息
 * @author 李春雨
 * @param {} param
 * @param {} successFn
 * @param {} failureFn
 */
SysmailData.prototype.deleteMailGroup = function(param,successFn,failureFn){
	var url = '../sysmail/deleteMailGroup.action';
	DpUtil.requestAjax(url,param,successFn,failureFn);
}
/**
 * 添加收件人信息
 * @author 李春雨
 * @param {} param
 * @param {} successFn
 * @param {} failureFn
 */
SysmailData.prototype.addMailPeople = function(param,successFn,failureFn){
	var url = '../sysmail/addMailPeople.action';
	DpUtil.requestAjax(url,param,successFn,failureFn);//DpUtil.requestJsonAjax 
}
/**
 * 修改收件人信息
 * @author 李春雨
 * @param {} param
 * @param {} successFn
 * @param {} failureFn
 */
SysmailData.prototype.updateMailPeople = function(param,successFn,failureFn){
	var url = '../sysmail/updateMailPeople.action';
	DpUtil.requestAjax(url,param,successFn,failureFn);//DpUtil.requestJsonAjax 
}