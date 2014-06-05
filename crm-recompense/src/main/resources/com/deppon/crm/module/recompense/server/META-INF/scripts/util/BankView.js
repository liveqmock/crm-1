/**
 * 使用说明：xtype:'bankcombox' 使用 银行 面控件
 */
BankView = function(){};
/**
* 选择银行 model
*/
Ext.define('BankListModel',{
	extend:'Ext.data.Model',
	fields:['name','id']
});
Ext.define('BankListStore',{
	extend:'Ext.data.Store',
	autoLoad :false,
	model : 'BankListModel',
    proxy:{
		type:'ajax',
		api:{
			read:'../recompense/searchBankListByName.action'
		},
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'bankList',
			totalProperty:'total'
		}
	}
});
var BankList = [];


/**
* 选择支行 model
*/
Ext.define('BranchBankListModel',{
	extend:'Ext.data.Model',
	fields:['accountBranch','accountBranchId']
});
Ext.define('BranchBankListStore',{
	extend:'Ext.data.Store',
	autoLoad :false,
	model : 'BranchBankListModel',
    proxy:{
		type:'ajax',
		api:{
			read:'../recompense/searchBankInfoByBankView.action'
		},
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'bankViewList',
			totalProperty:'total'
		}
	}
});

/**
 * 银行省
 */
Ext.define('ProvinceModel',{
	extend:'Ext.data.Model',
	fields:['name','id']
});
Ext.define('ProvinceStore',{
	extend:'Ext.data.Store',
	autoLoad :false,
	model : 'ProvinceModel',
    proxy:{
		type:'ajax',
		api:{
			read:'../customer/searchBankProvince.action'
		},
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'list',
			totalProperty:'total'
		}
	}
});
/**
 * 银行市
 */
Ext.define('CityListModel',{
	extend:'Ext.data.Model',
	fields:['name','id']
});
Ext.define('CityListStore',{
	extend:'Ext.data.Store',
	autoLoad :false,
	model : 'CityListModel',
    proxy:{
		type:'ajax',
		api:{
			read:'../customer/searchBankCityByProvinceId.action'
		},
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'list',
			totalProperty:'total'
		}
	}
});
/**
 *获得银行信息控件
 */
Ext.define('BankCombox',{
	extend:'QueryCombox',   //继承common包中封装的
//	extend:'Ext.form.ComboBox',
	alias : 'widget.bankcombox',
	fieldLabel:'开户银行',
	displayField:'bankName',  //控件显示的属性值
	valueField:'bankId',      //提交的属性值
	name:'bank',
	minChars:2,//最小自动智能匹配查询长度
	queryParam :'bankName',
	queryMode:'remote',
	functionName :'default',
	forceSelection:true,
	pageSize:10,
	labelWidth:65,
	typeAhead: true,	//自动填充
	bankListStore:null,
	listConfig: {
     	minWidth :330   //下拉框显示宽度
//     	minHeight:300	  //下拉框显示高度
	},
	enableKeyEvents:true,
	initComponent:function(){
		var me = this;
		me.store = me.getBankListStore( {'bankName':me.queryParam,'combo':me,'pageSize':me.pageSize});
		this.callParent();
	},
	//当前用户权限可切换部门 
	getBankListStore:function(searchParams){
		var me = this;
		if(me.bankListStore == null){
			me.bankListStore= Ext.create('BankListStore'
			, {
				pageSize:searchParams.pageSize,
		        listeners:{
		        	beforeload:function(store, operation,eOpts){
		        		Ext.apply(operation,{
								params : {'bankName':searchParams.combo.getRawValue()}
							}
						)
		        	}
			
		        }
	    	}
	    	);
		}
	    return me.bankListStore;
	},
	listeners:{
		change:function(field, newValue) {
			if (Ext.isEmpty(newValue)) {
				field.setValue(null);
			}
		}
	}
});
