/**
 * 用于处理提交数据
 * @param _action
 * @param _record
 * @param _store
 * @returns
 */
function processAction(_action, _record, _store) {
	var _create = new Array();
	var _update = new Array();
	var _delete = new Array();
	var removed = _store.getRemovedRecords();
	var updated = _store.getUpdatedRecords();
	var newed = _store.getNewRecords();
	Ext.each(removed, function(record) {
		_delete.push(record.data);
	});
	Ext.each(updated, function(record) {
		_update.push(record.data);
	});
	Ext.each(newed, function(record) {
		_create.push(record.data);
	});
	_action.set('updateFunctionPageEletmens', _update);
	_action.set('createFunctionPageEletmens', _create);
	_action.set('deleteFunctionPageEletmens', _delete);
	_action.set('function', _record.data);
	return _action;
}

/**
 * 对应FunctionAction的model
 */
Ext.define('FunctionAction', {
	extend : 'Ext.data.Model',
	fields : [ 'function', 'updateFunctionPageEletmens', 'createFunctionPageEletmens',
			'deleteFunctionPageEletmens', 'message' ],
	proxy : {
		type : 'ajax',
		api : {
			create : 'saveFunction.action',
			update : 'updateFunction.action'
		},
		writer : {
			type : 'json'
		},
		reader : {
			type : 'json'
		}
	}
});

/**
 * FunctionPageElementStore
 */
var FunctionPageElementStore = Ext.create('Ext.data.Store', {
	model : 'FunctionModel',
	storeId : 'FunctionPageElementStore',
	proxy : {
		url : 'findFunctionPageElementByParentCode.action',
		type : 'ajax',
		reader : {
			type : 'json',
			root : 'functionList'
		}
	}
});


var FunctionStore = Ext.create('Ext.data.Store', {
	pageSize : 10,
	model : 'FunctionModel',
	proxy : {
		type : 'ajax',
		actionMethods : 'POST',
		url : 'findAllFunction.action',
		reader : {
			type : 'json',
			root : 'functionList',
			totalProperty : 'totalCount'
		}
	}
});

/**
 *禁用与启用方法
 */
function startEnd(form,flag){
	if (form.isValid()) { 
		var new_record = form.getRecord();
		form.updateRecord(new_record);
		var record = {
			'id' : new_record.data.id,
			'validFlag' : new_record.data.validFlag,
			'parentCode':{'functionCode':new_record.data.parentCode.functionCode}
		};
		var array = {'function':record,'flag':flag};
    	Ext.Ajax.request({
		  url : 'updateFunction.action',
		  jsonData:array,
		  success : function(response) {
			  if(new_record.data.validFlag==true){
				  Ext.getCmp('toStartFunction_Button').setDisabled(true);
				  Ext.getCmp('toEndFunction_Button').setDisabled(false);
			  }else{
				  Ext.getCmp('toStartFunction_Button').setDisabled(false);
				  Ext.getCmp('toEndFunction_Button').setDisabled(true);
			  }
			  if(new_record.data.functionType==3){
				  FunctionPageElementStore.load({
						params : {
							'parentCode.functionCode' : new_record.data.functionCode
						}
				  });  
			  }
			  onRefreshTreeNodes(FunctionTreeStore, new_record.data.functionCode);
			  var json = Ext.decode(response.responseText);
//			  Ext.MessageBox.alert(i18n('i18n.authorization.message'),json.message);
			  MessageUtil.showMessage(json.message);
		   },
		   failure : function(response) {
			  var json = Ext.decode(response.responseText);
//			  Ext.MessageBox.alert(i18n('i18n.authorization.message'),json.message);
			  MessageUtil.showMessage(json.message);
		   }
    	});
    }
}

/**
 * 点击按钮，启用权限
 */
function toStartFunction() {
	var flag=1;
	var form = FunctionForm.getForm();
	form.findField("validFlag").setValue(true);
	startEnd(form,flag);
};

/**
 * 点击按钮，禁用权限
 */
function toEndFunction() {
	var flag=4;
	var form = FunctionForm.getForm();
	form.findField("validFlag").setValue(false);
	startEnd(form,flag);
};

/**
 * 定义功能表单
 */
var FunctionForm = Ext.create('Ext.form.FormPanel', {
	height:210,
    frame:true,
    bodyStyle:'padding:5px 10px 0',
    fieldDefaults: {
        msgTarget: 'side',
        labelAlign:'left',
        labelWidth: window.screen.availWidth*0.0686
    },
    defaultType: 'textfield',
    defaults: {
    	columnWidth:.5,
    	margin:'5 10 5 10',
        anchor: '100%'
    },
	layout:'column',
	tbar: [{
		    id:"toStartFunction_Button",
		    disabled:true,
			text : i18n('i18n.authorization.start',null),
			hidden:!isPermission('/authorization/updateFunction.action'),
			handler : toStartFunction,
			scope : this
		},{
			id:"toEndFunction_Button",
			disabled:true,
			text : i18n('i18n.authorization.end',null),
			hidden:!isPermission('/authorization/updateFunction.action'),
			handler : toEndFunction,
			scope : this
	}],
    items: [{
			name: 'functionName',
			allowBlank : false,
			blankText : i18n('error.module.authorization.FunctionNameIsNullException'),
	        fieldLabel: i18n('i18n.authorization.Function.functionName'),
	        xtype : 'textfield'
		},{
			name: 'uri',
			regex : new RegExp('^/(\\w+([\\w-_]*))+(/[\\w-_]*)?(.[\\w]*)(/[\\w-_]*)?'),
			regexText : i18n('error.module.authorization.FunctionURIIsErrorException'),
			allowBlank : false,
			blankText : i18n('error.module.authorization.FunctionURIIsErrorException'),
	        fieldLabel: i18n('i18n.authorization.Function.URI'),
	        xtype : 'textfield'
		},{
	        xtype: 'combo',
	        allowBlank : false,
	        blankText : i18n('error.module.authorization.ParentFunctionNullErrorException'),
	        fieldLabel: i18n('i18n.authorization.Function.parentCode'),
	        store: FunctionStore,
			displayField: 'functionName',
			valueField: 'functionCode',
			name: 'parentCode.functionCode',
			queryParam:'function.functionName',
			minChars:1,
	        typeAhead: false,
	        hideTrigger:false,
	        listConfig: {
	        	//minWidth : 400,
	            getInnerTpl: function() {
	                 return '{functionName}';//&nbsp{[function temp(functionType){return functionType == 1?"应用子系统":(functionType == 2?"子系统模块");}(values.functionType)]}';
	            }
	        },
	        pageSize: 10
		},{
			name: 'validFlag',
			allowBlank : false,
			blankText : i18n('error.module.authorization.FunctionValidFlagNullErrorException'),
			editable : false,
			fieldLabel: i18n('i18n.authorization.Function.validFlag'),
			xtype:'combo',
		    store: Ext.create('Ext.data.Store', {
					    fields: ['name', 'value'],
					    data : [
					        {'name':i18n('i18n.authorization.yes'),  'value':true},
					        {'name':i18n('i18n.authorization.no'),  'value':false}
					    ]
					}),
		    queryMode: 'local',
		    displayField: 'name',
		    valueField: 'value'
		},{
			name: 'check',
			allowBlank : false,
			blankText : i18n('error.module.authorization.FunctionCheckNullErrorException'),
			editable : false,
			fieldLabel: i18n('i18n.authorization.Function.check'),
			xtype:'combo',
		    store: Ext.create('Ext.data.Store', {
					    fields: ['name', 'value'],
					    data : [
					        {'name':i18n('i18n.authorization.yes'),  'value':true},
					        {'name':i18n('i18n.authorization.no'),  'value':false}
					    ]
					}),
		    queryMode: 'local',
		    displayField: 'name',
		    valueField: 'value'
		},{
			name: 'functionType',
			allowBlank : false,
			blankText : i18n('error.module.authorization.FunctionTypeNullErrorException'),
			editable : false,
			fieldLabel: i18n('i18n.authorization.Function.functionType'),
			xtype:'combo',
		    store: Ext.create('Ext.data.Store', {
					    fields: ['name', 'value'],
					    data : [
					        {'name':i18n('i18n.authorization.Function.functionType_app'), 'value':1},
					        {'name':i18n('i18n.authorization.Function.functionType_module'), 'value':2},
					        {'name':i18n('i18n.authorization.Function.functionType_function'), 'value':3}
					    ]
					}),
		    queryMode: 'local',
		    displayField: 'name',
		    valueField: 'value'
		},{
			name: 'functionDesc',
			columnWidth:1,
	        fieldLabel: i18n('i18n.authorization.Function.functionDesc'),
	        xtype : 'textarea'
	}]
});