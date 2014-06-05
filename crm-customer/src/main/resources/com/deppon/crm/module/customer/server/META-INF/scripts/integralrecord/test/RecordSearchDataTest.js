/**.
 * <p>
 * 理赔中获取测试数据<br/>
 * </p>
 * @author  潘光均
 * @时间    2012-03-27
 * </p>
 */
RecordSearchDataTest = function(){
	
};

RecordSearchDataTest.prototype.getSearchRecordStore =function(){
	var conditions = Ext.create('Ext.data.Store', {
	    fields: ['id', 'condition'],
	    data : [
	        {"id":"1", "condition":"会员编码"},
	        {"id":"2", "condition":"会员名称"},
	        {"id":"3", "condition":"手机号码"},
	        {"id":"4", "condition":"联系人编码"}
	    ]
	});
	return conditions;
};

/**.
 * <p>
 * 查询条件combox获取测试用storebr/>
 * </p>
 * @returns states 一个data.store
 * @author  潘光均
 * @时间    2012-03-27
 */
RecordSearchDataTest.prototype.getSearchConditionStore= function(){
	var conditions = Ext.create('Ext.data.Store', {
	    fields: ['id', 'condition'],
	    data : [
	        {"id":"1", "condition":"会员编码"},
	        {"id":"2", "condition":"会员名称"},
	        {"id":"3", "condition":"手机号码"},
	        {"id":"4", "condition":"联系人编码"}
	    ]
	});
	return conditions;
};
