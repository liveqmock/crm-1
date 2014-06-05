/**.
 * <p>
 * 订单模块中获取擦测试数据<br/>
 * </p>
 * @author  张斌
 * @时间    2012-03-08
 * </p>
 */
OrderDataTest = function(){
	
};
/**.
 * <p>
 * combox获取测试用store。里面所有combox共用这个store<br/>
 * </p>
 * @returns states 一个data.store
 * @author  张斌
 * @时间    2012-03-08
 */
OrderDataTest.prototype.getTestStore= function(){
	var states = Ext.create('Ext.data.Store', {
	    fields: ['abbr', 'name'],
	    data : [
	        {"abbr":"AL", "name":"Alabama"},
	        {"abbr":"AK", "name":"Alaska"},
	        {"abbr":"AZ", "name":"Arizona"}
	        //...
	    ]
	});
	return states;
};