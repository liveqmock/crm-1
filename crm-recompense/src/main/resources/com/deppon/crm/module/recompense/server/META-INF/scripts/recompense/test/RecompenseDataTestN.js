/**.
 * <p>
 * 理赔中获取测试数据<br/>
 * </p>
 * @author  张斌
 * @时间    2012-03-27
 * </p>
 */
RecompenseDataTestN = function(){
	
};


/**.
 * <p>
 * combox获取测试用store。里面所有combox共用这个store<br/>
 * </p>
 * @returns states 一个data.store
 * @author  张斌
 * @时间    2012-03-27
 */
RecompenseDataTestN.prototype.getTestStore= function(){
	var states = Ext.create('Ext.data.Store', {
	    fields: ['id', 'deptName'],
	    data : [
	        {"id":"AL", "deptName":"Alabama"},
	        {"id":"AK", "deptName":"Alaska"},
	        {"id":"AZ", "deptName":"Arizona"}
	        //...
	    ]
	});
	return states;
};

/**.
 * <p>
 * 理赔查询store<br/>
 * </p>
 * @returns states 一个data.store
 * @author  潘光均
 * @时间    2012-03-27
 */
RecompenseDataTestN.prototype.getRecompenseSearchStore=function(){
	var states = Ext.create('Ext.data.Store', {
		   fields: ['id', 'deptName'],
		    data : [
		        {"id":"AL", "deptName":"Alabama"},
		        {"id":"AK", "deptName":"Alaska"},
		        {"id":"AZ", "deptName":"Arizona"}
	        //...
	    ]
	});
	return states;
};
/**.
 * <p>
 * 上报部门store<br/>
 * </p>
 * @returns states 一个data.store
 * @author  潘光均
 * @时间    2012-03-27
 */
RecompenseDataTestN.prototype.getReportDeptStore=function(){
	var states = Ext.create('Ext.data.Store', {
		   fields: ['id', 'deptName'],
		    data : [
		        {"id":"AL", "deptName":"Alabama"},
		        {"id":"AK", "deptName":"Alaska"},
		        {"id":"AZ", "deptName":"Arizona"}
	        //...
	    ]
	});
	return states;
};
/**.
 * <p>
 * 理赔查询store<br/>
 * </p>
 * @returns states 一个data.store
 * @author  潘光均
 * @时间    2012-03-27
 */
RecompenseDataTestN.prototype.getRegionStore=function(){
	var states = Ext.create('Ext.data.Store', {
		   fields: ['id', 'deptName'],
		    data : [
		        {"id":"AL", "deptName":"Alabama"},
		        {"id":"AK", "deptName":"Alaska"},
		        {"id":"AZ", "deptName":"Arizona"}
	        //...
	    ]
	});
	return states;
};
RecompenseDataTestN.prototype.getUser=function(successFn){
	return {'user':{'id':'1','name':'zhangsan'}};
};


/**.
 * <p>通过部门名称查询部门 </p>
 * @returns states 一个data.store
 * @author  潘光均
 * @时间    2012-03-27
 */
RecompenseDataN.prototype.getDeptByName=function(param,successFn,failureFn){
	return {'deptList':{{'id':'1','deptName':'德邦物流'},{'id':'2','deptName':'facebook'}}};
}