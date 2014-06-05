/**
 * 提交方法
 * 
 * @returns {Boolean}
 */
function _submit() {
	var pwdtext = document.getElementById('pwd');
	if (check()) {
		// var md5 = hex_md5(pwdtext.value);
		// if (pwdtext != "" && pwdtext != null && pwdtext != undefined) {
		// pwdtext.value = md5;
		// }
		// document.getElementById('login').submit();
		Ext.Ajax.request({
			url : 'loginAction.action',
			params : {
				'loginName' : document.getElementById('loginName').value,
				'password' : document.getElementById('pwd').value,
				'loginDate': new Date()
			},
			success : function(response, opts) {
				var result = Ext.decode(response.responseText);
				if(result.success&&!result.isException){
					Ext.util.Cookies.set('loginName',document.getElementById('loginName').value);
					window.location = 'index.action';
				}else{
					document.getElementById('markmes').innerHTML = result.message;	
					document.getElementById('pwd').value = '';
				}
			},
			failure : function(response, opts) {
				var result = Ext.decode(response.responseText);
				if(result.success&&!result.isException){
					window.location = window.location.pathname;
				}else{
					
					document.getElementById('markmes').innerHTML = result.message;
				}
			}
		});
	}
	return false;
}

function eraseUsername() {
//	document.getElementById('loginName').value = '';
//	document.getElementById('pwd').value = '';
	document.getElementById("markmes").innerHTML="";
	usernamefirst = true;
}

function erasepwd() {
//	document.getElementById('pwd').value = '';
	pwdfirst = true;
}

/**
 * 验证方法
 * 
 * @returns {Boolean}
 */
function check() {
	var logName = document.getElementById('loginName').value;
	var logPwd = document.getElementById('pwd').value;
	var message = null;
	var flag = true;
	if (logName == "" || logName == null || logName == undefined) {
		document.getElementById('markmes').innerHTML="";
		message = i18n('i18n.login.UserNameIsNullException');
//		document.getElementById('loginName').value = i18n('i18n.login.username');
		flag = false;
	}
	if (logPwd == '' || logPwd == null || logPwd == undefined) {
		document.getElementById('markmes').innerHTML="";
		if (message != null) {
			message = message + ','
					+ i18n('i18n.login.LoginPasswordIsNullException');
		} else {
			message = i18n('i18n.login.LoginPasswordIsNullException');
		}
//		document.getElementById('pwd').value = i18n('i18n.login.password');
		flag = false;
	}
	if (!flag) {
		document.getElementById('markmes').innerHTML = message;
	}
	return flag;
}

/**
 * 初始化
 */
Ext.onReady(function() {
	document.title = i18n('i18n.login.page_title');
	document.getElementById('loginName').value = Ext.util.Cookies.get( 'loginName');
//	document.getElementById('loginName').value = i18n('i18n.login.username');
//	document.getElementById('pwd').value = i18n('i18n.login.password');
//	document.getElementById('logo').innerHTML = i18n('i18n.login.logo')
//			+ '&copy; 2011';
});