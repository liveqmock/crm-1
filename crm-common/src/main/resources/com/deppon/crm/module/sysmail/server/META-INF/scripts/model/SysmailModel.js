/**
 * <p>
 * Description:邮件配置管理model<br />
 * </p>
 * @title SysmailModel.js
 * @author 石应华
 * @version 0.1 2013-9-23
 */
/**
 * 邮件组管理
 */
Ext.define('MailGroupModel', {
	extend : 'Ext.data.Model',
	fields : [{
        //id
        name:'id',
        type:'string'
    },{
		//邮件组名称
		name : 'groupName',
		type : 'string'
	}, {
		//邮件组编码
		name : 'groupCode',
		type : 'string'
	}]
});	

/**
 * 邮件接收人——邮箱账号
 */
Ext.define('AccGroResultModel', {
    extend : 'Ext.data.Model',
    fields : [{
        //收件人id
        name:'accountId',
        type:'string'
    },{
        //收件人名称
        name : 'receiverName',
        type : 'string'
    }, {
        //收件人邮件地址
        name : 'emailAddress',
        type : 'string'
    }, {
        //收件人工号
        name : 'empCode',
        type : 'string'
    },{
    	//收件人所属组ID
    	name : 'groupId',
    	type : 'string'
    },{
        //收件人所属组名称
        name : 'groupName',
        type : 'string'
    },{
        //收件人_邮件组关系id
        name : 'relationId',
        type : 'string'
    }]
}); 



	





   