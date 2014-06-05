MRUtil = function() {
};
/**
 * comboBox的change事件重写，使其达到可以删除，且只能选择下拉框里面的内容
 */
MRUtil.comboSelsct = function(combo){
		if(Ext.isEmpty(combo.getValue())){
			combo.setValue("");
	}
};
MRUtil.requestJsonAjax = function(url,params,successFn,failFn,async)
{
	if(async===undefined){
		async=true;
	};
	Ext.Ajax.request({
		url:url,
		jsonData:params,
		success:function(response){
			var result = Ext.decode(response.responseText);
			if(result.success){
				successFn(result);
			}else{
				failFn(result);
			}
		},
		failure:function(response){
			var result = Ext.decode(response.responseText);
			failFn(result);
		}
	});
};