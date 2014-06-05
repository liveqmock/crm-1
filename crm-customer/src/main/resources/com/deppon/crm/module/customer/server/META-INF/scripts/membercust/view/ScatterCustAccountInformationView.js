/**
 * 散客升级页面之散客账号信息
 */
Ext.define('ScatterCustAccountWin',{
	extend:'PopWindow',
	title:i18n('i18n.ScatterCustManagerView.custAccountInfo'),
	fbar:null,
	accountPanel:null,
	height:200,
	width:810,
	memberCustEditWindow:null,
	scatterAccountData:null,
	initComponent:function(){
		var me = this;
		var accountGrid = Ext.create('AccountGrid');
		accountGrid.store.removeAll();
		for(var i = 0; i < me.scatterAccountData.length; i++){
			var accountModel = Ext.create('AccountModel',me.scatterAccountData[i]);
			accountGrid.store.insert(0,accountModel);
		}
		
		me.items = [accountGrid,
		            {html:i18n('i18n.ScatterCustManagerView.custSomeOtherWarning')}];
		me.fbar = me.getFbar();
		this.callParent();
	},
	getFbar:function(){
		var me = this;
		return[
		       {
		    	   text:i18n('i18n.MemberCustEditView.ensure'),
		    	   xtype:'button',
		    	   handler:function(){
		    		   var accountSelection = me.down('gridpanel').getSelectionModel().getSelection();
		    		   if (accountSelection.length != 1) {
		    				MessageUtil.showMessage(i18n('i18n.ScatterUpgradeView.chooseInfoAccount'));
		    				return;
		    			}else{		    	
		    				var isExistSameAccount = false;
		    				//校验账号是否已经存在
		    				memberCustControl.getAccountStore().each(function(result){
		    					if(accountSelection[0].get('bankAccount')==result.get('bankAccount')){
		    						isExistSameAccount = true;
		    						}
		    				});
		    				if(isExistSameAccount){
		    					MessageUtil.showMessage(i18n('i18n.MemberCustEditView.accountRepeat'));
		    					me.close();
		    				}else{
		    					memberCustControl.getAccountStore().insert(0,accountSelection[0]);	
    		    				me.close();
		    				}			
		    			}
		    	   }
		       },{
		    	   text:i18n('i18n.ManualRewardIntegralEditView.b_cance'),
		    	   xtype:'button',
		    	   handler:function(){
		    		   if(!Ext.isEmpty(me.memberCustEditWindow)){
		    			   me.memberCustEditWindow.down('tabpanel').setActiveTab(me.memberCustEditWindow.down('basicpanel').down('tabcontentpanel'));
		    		   }
		    		   me.close();
		    	   }
		       }
		       ];
	
	}
});
Ext.define('AccountGrid',{
	extend:'PopupGridPanel',
	height:100,
	autoScroll :true,
	selModel : Ext.create('Ext.selection.CheckboxModel'),
	columns : [{text:i18n('i18n.MemberCustEditView.openBank'),dataIndex:'bank'},
		         {text:i18n('i18n.MemberCustEditView.openName'),dataIndex:'countName'},
		         {text:i18n('i18n.MemberCustEditView.accountNo'),dataIndex:'bankAccount',width:150},
		         {text:i18n('i18n.MemberCustEditView.accountProvince'),dataIndex:'bankProvinceName'},
		         {text:i18n('i18n.ScatterUpgradeView.subBankName'),dataIndex:'subBankname'},
		         {text:i18n('i18n.ModifyMemberView.financialContactID'),dataIndex:'financeLinkmanId',hidden:true},
		         {text:i18n('i18n.PotentialCustManagerView.creator'),dataIndex:'createUser',hidden:true},
		         {text:i18n('i18n.ScatterUpgradeView.modifyDate'),dataIndex:'modifyDate',hidden:true},
		         {text:i18n('i18n.ScatterUpgradeView.createDate'),dataIndex:'createDate',hidden:true},
		         {text:i18n('i18n.RecordExchangeRuleManagerView.modiflyUser'),dataIndex:'modifyUser',hidden:true},  
		         {text:i18n('i18n.MemberCustEditView.accountPurpose'),dataIndex:'accountUse',renderer:function(value){
		        	return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.ACCOUNT_USE);
		         }},
		         {text:i18n('i18n.MemberCustEditView.accountContactName'),dataIndex:'financeLinkman'},
		         {text:i18n('i18n.MemberCustEditView.contactPhone'),dataIndex:'linkManMobile'},
		         {text:i18n('i18n.MemberCustEditView.contactTel'),dataIndex:'linkManPhone'},
		         {text:i18n('i18n.MemberCustEditView.isAcquiesceAccount'),dataIndex:'isdefaultaccount',renderer:DpUtil.changeBooleanToDescrip},
		         {text:i18n('i18n.MemberCustEditView.accountNature'),dataIndex:'accountNature',renderer:function(value){
		        	 return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.ACCOUNT_NATURE);
		         }},
		         {text:i18n('i18n.MemberCustEditView.CRAccount'),dataIndex:'relation'}, 
		         {text:i18n('i18n.MemberCustEditView.accountCity'),dataIndex:'bankCityName',hidden:true},
		         {text:i18n('i18n.MemberCustEditView.accountProvinceid'),dataIndex:'bankProvinceId',hidden:true},
		         {text:i18n('i18n.MemberCustEditView.accountCityid'),dataIndex:'bankCityId',hidden:true}]	
});