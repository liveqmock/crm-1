/**.
 * <p>
 * 存放全局变量（1）控制页面调用那个DATA.JS（2）定义字段名称的全局变量<br/>
 * </p>
 * @author  张斌
 * @时间    2012-03-27
 * </p>
 */

/**.
 * <p>
 * 规范如此要求，判断'file'设置TEST为true/false<br/>
 * </p>
 * @author  张斌
 * @时间    2012-03-27
 * </p>
 */
CONFIG = (function(){
	var constants = {
			'TEST':false
	};
	if(window.location.protocol === 'file:')
	{
		constants.TEST = true;
	}
	
	return {
		get:function(name) {return constants[name];}
	};
})();

