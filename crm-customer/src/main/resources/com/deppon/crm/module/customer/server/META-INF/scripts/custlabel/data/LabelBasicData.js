//基础资料标签Model
Ext.define('LabelModel',{
	extend:'Ext.data.Model',
	fields:[{name:'labelName',type:'string'},
	        {name:'deptId',type:'string'},
	        {name:'id',type:'string'}
	        ]
});
//基础资料标签Data
Ext.define('LabelData',{
	custLabelStore:null,
	//客户标签store
	getCustLabelStore:function(){
		return this.custLabelStore;
	},
	//初始化客户标签
	initCustLabelStore:function(params,viewCustLabelSuccess,viewCustLabelFail) {
	 DpUtil.requestJsonAjaxSync('searchLabel.action',params,viewCustLabelSuccess,viewCustLabelFail);
	},
	//保存部门的某一个客户标签
	saveCustLabel:function(params,saveCustLabelSuccess,saveCustLabelFail){
		 DpUtil.requestJsonAjax('saveLabel.action',params,saveCustLabelSuccess,saveCustLabelFail);
	},
	//删除部门标签
	deleteCustLabelById:function(params,deleteCustLabelSuccess,deleteCustLabelFail){
		 DpUtil.requestAjax('deleteCustLabel.action',params,deleteCustLabelSuccess,deleteCustLabelFail);
	},
	updateCustLabel:function(params,updateCustLabelSuccess,updateCustLabelFail){
		 DpUtil.requestJsonAjax('updateCustLabel.action',params,updateCustLabelSuccess,updateCustLabelFail);
	}
});


//客户标签Model
Ext.define('CustLabelModel',{
	extend:'Ext.data.Model',
	fields:[
	        {name:'custId',type:'string'},
	        {name:'custType',type:'string'},
	        'label'
	        ]
});