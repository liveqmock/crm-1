/**
 * .
 * <p>
 * 规范如此要求，判断'file'设置TEST为true/false<br/>
 * </p>
 * 
 * @author 毛建强
 * @时间 2012-03-24
 *     </p>
 */
CONFIG = (function() {
	var constants = {
		'TEST' : false
	};
	if (window.location.protocol === 'file:') {
		constants.TEST = true;
	}
	return {
		get : function(name) {
			return constants[name];
		}
	};
})();

/**
 * 全局变量定义 
 */

/**
 * 开发类型
 */

var DEVELOP_TYPE="dev";//类型-开发

var MAINTAIN_TYPE="mat";//类型-维护

/**
 * 客户类型
 */
var POTENTIAL_CUSTOMER="PC_CUSTOMER";//潜客
var SCATTER_CUSTOMER="SC_CUSTOMER";//散客


/**
 * 发货周期表类型
 */
var CYCLE_DELIVERY="1";//发货
var CYCLE_ARRIVAL="2";//到货