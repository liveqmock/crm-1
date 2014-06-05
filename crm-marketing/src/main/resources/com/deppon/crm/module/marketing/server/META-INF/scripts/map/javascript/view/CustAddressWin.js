/*
 * 固定客户，潜客，散客修改地址
 * */
Ext.define('CustAddressWin',{
	extend:'PopWindow',
	id:'custAddressWin_id',
	items:null,
	width:630,
	//y:30,//距离界面顶层 距离
	height:210,
	custId:null,//客户ID
	custType:null,//客户类型
	contactId:null,//主联系人ID
	oldAddress:null,//地址
	recode:null,
	title:i18n('i18n.custAddressWin.title'),
	layout : {
			type : 'table',columns : 3
	},
	listeners:{
		close:function(){
			if(!Ext.isEmpty(Ext.getCmp('areaaddresscombox_id'+'AreaTabWindow'))){
				Ext.getCmp('areaaddresscombox_id'+'AreaTabWindow').close();
			}
		    if(!Ext.isEmpty(Ext.getCmp('areaaddresscombox_id_spc'+'AreaTabWindow'))){
		    	Ext.getCmp('areaaddresscombox_id_spc'+'AreaTabWindow').close();
			}
		}
	},
	initComponent:function(){
		var me = this;
		/*if(me.custType=="MEMBER"){*/
			me.items =[{
				xtype:'panel',
				border:false,
				colspan:3,
				margin:'5 0 8 -11',
				width:630,
				html:'<div style="height:1px; width:628px; background-color:darkGray;"></div>'
			},{
		        xtype: 'textareafield',
		        id:'oldAddress_id',
		        fieldLabel: '客户原地址',
		        readOnly:true,
		        labelWidth:70,
		        width:580,
		        height:60,
		        colspan:3
			},{
				fieldLabel:i18n('i18n.custAddressWin.detailedAddress'),//
				xtype:'areaaddresscombox',
				id:'areaaddresscombox_id',
				name:'city',
				forceSelection:true,
				width:210,
				labelWidth:70,
				tabPanel:['commonPanel','provincePanel','cityPanel','areaPanel'],
				blankText:i18n('i18n.custAddressWin.choiseProviceAndCity'),
				emptyText:i18n('i18n.custAddressWin.proviceAndCity.isNull'),
				allowBlank:false,
				listeners:{
					change:function(combobox,newValue,oldValue){
						if(!Ext.isEmpty(newValue)){
							var array = combobox.getRawValue().split('-');	
							if(array.length == 2){
								Ext.getCmp('address_id').setValue(array[0]+'-'+array[1]+'-');
							}
							if(array.length == 3){
								Ext.getCmp('address_id').setValue(array[0]+'-'+array[1]+'-'+array[2]+'-');
							}
						}
					}
				}
		    },{
				fieldLabel:'---',
				id:'address_id',
				xtype:'dptextfield',
				labelWidth:15,
				colspan:2,
				width:350,
				labelSeparator:'',
				maxLength :100,	
				maxLengthText:i18n('i18n.custAddressWin.canNotBeyoundMaxSize'),
				name:'address',
				blankText:i18n('i18n.custAddressWin.addressInfoIsNull'),
				emptyText:i18n('i18n.custAddressWin.addressInfoIsNull')
		    }];
		/*}else{
			me.items =[{
				xtype:'panel',
				border:false,
				colspan:3,
				margin:'5 0 8 -11',
				width:630,
				html:'<div style="height:1px; width:628px; background-color:darkGray;"></div>'
			},{
		        xtype: 'textareafield',
		        id:'oldAddress_id',
		        fieldLabel: '客户原地址',
		        readOnly:true,
		        colspan:3,
		        width:580,
		        height:60,
		        labelWidth:70
			},{
				fieldLabel:i18n('i18n.custAddressWin.detailedAddress'),//
				xtype:'areaaddresscombox',
				id:'areaaddresscombox_id_spc',
				name:'city',
				forceSelection:true,
				width:210,
				labelWidth:70,
				tabPanel:['provincePanel','cityPanel'],
				blankText:i18n('i18n.custAddressWin.choiseProviceAndCity'),
				emptyText:i18n('i18n.custAddressWin.proviceAndCity.isNull'),
				allowBlank:false,
				listeners:{
					change:function(combobox,newValue,oldValue){
						if(!Ext.isEmpty(newValue)){
							var array = combobox.getRawValue().split('-');	
							if(array.length == 2){
								Ext.getCmp('address_id_spc').setValue(array[0]+'-'+array[1]+'-');
							}
							if(array.length == 3){
								Ext.getCmp('address_id_spc').setValue(array[0]+'-'+array[1]+'-'+array[2]+'-');
							}
						}
					}
				}
		    },{
				fieldLabel:'---',
				id:'address_id_spc',
				xtype:'dptextfield',
				labelWidth:15,
				colspan:2,
				width:350,
				labelSeparator:'',
				maxLength :100,	
				maxLengthText:i18n('i18n.custAddressWin.canNotBeyoundMaxSize'),
				name:'address',
				blankText:i18n('i18n.custAddressWin.addressInfoIsNull'),
				emptyText:i18n('i18n.custAddressWin.addressInfoIsNull')
		    }];
		}*/
	    me.buttons = me.getButtons();
		this.callParent();
		if(!Ext.isEmpty("oldAddress_id")){
			if(Ext.isEmpty(me.oldAddress)){
				Ext.getCmp('oldAddress_id').setValue("无");
			}else{
				Ext.getCmp('oldAddress_id').setValue(me.oldAddress);
			}
		}
	},
	getButtons:function(){
		var me = this;
		return[{
		   xtype : 'button',
			scope:me,
			colspan:2,
			text:i18n('i18n.custAddressWin.saveButton'), 
			width:70,
			handler:function(t) {
				saveCustAddress(t);
				
			}
	   },{
		   xtype : 'button',
			scope:me,
			margin:'5 0 5 5',
			text:i18n('i18n.custAddressWin.closeButton'),
			width:70,
			handler:function(){
				Ext.getCmp('custAddressWin_id').close();
				if(!Ext.isEmpty(Ext.getCmp('areaaddresscombox_id'+'AreaTabWindow'))){
					Ext.getCmp('areaaddresscombox_id'+'AreaTabWindow').close();
				}
			    if(!Ext.isEmpty(Ext.getCmp('areaaddresscombox_id_spc'+'AreaTabWindow'))){
			    	Ext.getCmp('areaaddresscombox_id_spc'+'AreaTabWindow').close();
				}
			}
	   }];
	}
});

function saveCustAddress(t) {
	var value = null;
	if(!Ext.isEmpty(Ext.getCmp('address_id_spc'))){
		value = Ext.getCmp('address_id_spc').getValue();
		if(value.length > 100){
			MessageUtil.showErrorMes('地址长度不能超过100字符！');
			return ;
		}
	}
	if(!Ext.isEmpty(Ext.getCmp('address_id'))){
		value = Ext.getCmp('address_id').getValue();
		if(value.length > 100){
			MessageUtil.showErrorMes('地址长度不能超过100字符！');
			return ;
		}
	}
	t.disable();
	var myMask = new Ext.LoadMask(Ext.getBody(), {msg:"正在保存处理中..."});
	myMask.show();
	var custAddressWin = Ext.getCmp('custAddressWin_id');
	var custId = custAddressWin.custId;//传入的客户ID
	var custType = custAddressWin.custType;//传入的客户类型
	var contactId = custAddressWin.contactId;//传入的主联系人ID
	
	var address = null;
	var areaaddresscombox = null;//省份城市控件
//	if('MEMBER' == custType) {
		address = Ext.getCmp('address_id').value;
		areaaddresscombox = Ext.getCmp('areaaddresscombox_id')
//	}else {
//		address = Ext.getCmp('address_id_spc').value;
//		areaaddresscombox = Ext.getCmp('areaaddresscombox_id_spc')
//	}
	var provinceAndCityName = areaaddresscombox.rawValue;//省份城市名字
	var provinceAndCityId = areaaddresscombox.value;//省份城市ID
	
	if(Ext.isEmpty(custId)){
		myMask.hide();
		t.enable();
		MessageUtil.showErrorMes(i18n('i18n.custAddressWin.paramIsNull'));
		return false;
	}
	if(Ext.isEmpty(custType)){
		myMask.hide();
		t.enable();
		MessageUtil.showErrorMes(i18n('i18n.custAddressWin.custTypeIsNull'));
		return false;
	}
	if(Ext.isEmpty(address)) {
		myMask.hide();
		t.enable();
		MessageUtil.showErrorMes(i18n('i18n.custAddressWin.addressInfoIsNull'));
		return false;
	}
	if(Ext.isEmpty(provinceAndCityName) || Ext.isEmpty(provinceAndCityId)) {
		t.enable();
		myMask.hide();
		MessageUtil.showErrorMes(i18n('i18n.custAddressWin.proviceAndCity.isNull'));
		return false;
	}
	if(!Ext.isEmpty(provinceAndCityName) || !Ext.isEmpty(provinceAndCityId)) {
		provinceAndCityName = provinceAndCityName.split('-');
		provinceAndCityId = provinceAndCityId.split('-');
	}
//	if('MEMBER' == custType){
		if(Ext.isEmpty(provinceAndCityName[2]) || Ext.isEmpty(provinceAndCityId[2])){
			t.enable();
			myMask.hide();
			//修改固定客户地址,地址省市区必须选择到区
			MessageUtil.showErrorMes(i18n('i18n.visualMarket.selectAddressToArea'));
			return false;
		}
	/*}else{
		if(Ext.isEmpty(provinceAndCityName[1]) || Ext.isEmpty(provinceAndCityId[1])){
			t.enable();
			myMask.hide();
			//潜客或者散客,地址省市区必须选择到区
			MessageUtil.showErrorMes('潜客或者散客修改地址,请选择到城市！');
			return false;
		}
	}*/
	//ajax
	var updateCustAddressSuccess=function(result){
		t.enable();
		MessageUtil.showInfoMes(i18n('i18n.custAddressWin.saveSucess')); 
		Ext.getCmp('custAddressWin_id').close();
		myMask.hide();
		
		//获取客户信息查询结果的表格
		var customerDetailResultGrid = Ext.getCmp("customerDetailId");
		//获取表格中选中的记录
		var selection = customerDetailResultGrid.getSelectionModel().getSelection();
		if(selection.length != 0){
			selection[0].set("address",result.custAddress.address);
		}	

	}
	var updateCustAddressFail=function(result){
		t.enable();
		myMask.hide();
		if(!Ext.isEmpty(result)){
			MessageUtil.showErrorMes(result.message);
		}
	}
	var params = null;
	if(provinceAndCityName.length==2 && provinceAndCityId.length==2) {
		params = {'custAddress':{'custId':custId,
			'custType':custType,'address':address,'contactId':contactId,
			'provinceId':provinceAndCityId[0],'cityId':provinceAndCityId[1],
			'provinceName':provinceAndCityName[0],'cityName':provinceAndCityName[1]}};
	}
	if(provinceAndCityName.length==3 && provinceAndCityId.length==3) {
		params = {'custAddress':{'custId':custId,
			'custType':custType,'address':address,'contactId':contactId,
			'provinceId':provinceAndCityId[0],'cityId':provinceAndCityId[1],
			'provinceName':provinceAndCityName[0],'cityName':provinceAndCityName[1],
			'areaName':provinceAndCityName[2],'areaId':provinceAndCityId[2]}};
	}
	//请求方法
	updateCustAddress(params,updateCustAddressSuccess,updateCustAddressFail);
};

function updateCustAddress(params,updateCustAddressSuccess,updateCustAddressFail){
	DButil.requestJsonAjax('../customer/updateCustAddress.action',params,updateCustAddressSuccess,updateCustAddressFail);
};