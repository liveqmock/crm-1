/**.
 * <p>
 * 根据规范写的出险描述Tab中的子Panel</br>
 * </p>
 * @author  张斌
 * @时间    2012-04-06
 */

Ext.define('RecompenseDescriptionPanel',{
	extend:'TabContentPanel',
	height:315,
	title : i18n('i18n.recompense.dangerDescription'),
	items:null,
	/**.
	 * <p>
	 * 添加出险描述的按钮的方法</br>
	 * </p>
	 * @author  张斌
	 * @时间    2012-04-06
	 */
	addFunction:function(){
		if(Ext.isEmpty(Ext.getCmp('dangerInfoWindow'))){
			new DangerInfoWindow().show();
		}else{
			Ext.getCmp('dangerInfoWindow').show();
		}
		Ext.getCmp('dangerInfoWindow').flag = 'add';
		Ext.getCmp('dangerInfoWindowInnerForm').getForm().reset();
		Ext.getCmp('dangerInfoFormInsurType').store.removeAll();
		Ext.getCmp('dangerInfoFormInsurType').getStore().loadRecords(Ext.getCmp(CONFIGNAME.get('insurType')).store.data.items);
	},
	/**.
	 * <p>
	 * 删除出险描述的按钮的方法</br>
	 * </p>
	 * @author  张斌
	 * @时间    2012-04-06
	 */
	deleteFunction:function(){
		var selections = Ext.getCmp('dangerInfoGrid').getSelectionModel( ).getSelection( );
		if(selections.length==0){
			return;
		}
		MessageUtil.showQuestionMes(i18n('i18n.recompense.isDeleteThese'),
			function(e){
				if (e == 'yes') {
		            Ext.getCmp('dangerInfoGrid').getStore().remove(selections);
				}
		});    
	},
	/**.
	 * <p>
	 * 修改出险描述的按钮的方法</br>
	 * </p>
	 * @author  张斌
	 * @时间    2012-04-06
	 */
	updateFunction:function(){
		var selections = Ext.getCmp('dangerInfoGrid').getSelectionModel().getSelection();
		if(selections.length!=1){
			MessageUtil.showMessage(i18n('i18n.recompense.pleaseSelectOneLine'));
			return ;
		}
		if(Ext.isEmpty(Ext.getCmp('dangerInfoWindow'))){
			new DangerInfoWindow().show();
		}else{
			Ext.getCmp('dangerInfoWindow').show();
		}
		Ext.getCmp('dangerInfoWindow').flag = 'update';
		Ext.getCmp('dangerInfoFormInsurType').store.removeAll();
		Ext.getCmp('dangerInfoFormInsurType').getStore().loadRecords(Ext.getCmp(CONFIGNAME.get('insurType')).store.data.items);
		Ext.getCmp('dangerInfoWindowInnerForm').getForm().loadRecord(selections[0]);
	},
	getItems:function(){
		var me  = this;
		return  [{
	        region: 'north',
	        xtype: 'panel',
	        height: 30,
	        items:new OperationButtonPanel({'addFunction':me.addFunction,region:'north',height:300,'deleteFucntion':me.deleteFunction
	    		,'updateFunction':me.updateFunction,'addId':'dangetInfoAdd','updateId':'dangetInfoUpdate','deleteId':'dangetInfoDelete'})
	    },{
	        region: 'center',
	        xtype: 'panel',
	        layout: 'fit',
	        items:[new DangerInfoGrid()]
	    }];
	},
	layout: 'border',
	initComponent:function(){
		var me = this;
		me.items = me.getItems();
		this.callParent();
}});
/**.
 * <p>
 * 根据规范写的货物托运清单Tab中的子Panel</br>
 * </p>
 * @author  张斌
 * @时间    2012-04-06
 */
Ext.define('GoodsTrancesListPanel',{
	extend:'TabContentPanel',
	items:null,
	height:315,
	title : i18n('i18n.recompense.goodsConsignmentList'),
	/**.
	 * <p>
	 * 添加货物托运清单的按钮的方法</br>
	 * </p>
	 * @author  张斌
	 * @时间    2012-04-06
	 */
	addFunction:function(){
		if(Ext.isEmpty(Ext.getCmp('goodTranceWindow'))){
			new GoodTranceWindow().show();
		}else{
			Ext.getCmp('goodTranceWindow').show();
		}
		Ext.getCmp('goodTranceWindow').flag = 'add';
		Ext.getCmp('goodTranceWindowInnerForm').getForm().reset();
	},
	/**.
	 * <p>
	 * 修改出险描述的按钮的方法</br>
	 * </p>
	 * @author  张斌
	 * @时间    2012-04-06
	 */
	updateFunction:function(){
		var selections = Ext.getCmp('goodsTranceGird').getSelectionModel().getSelection();
		if(selections.length!=1){
			MessageUtil.showMessage(i18n('i18n.recompense.pleaseSelectOneLine'));
			return ;
		}
		if(Ext.isEmpty(Ext.getCmp('goodTranceWindow'))){
			new GoodTranceWindow().show();
		}else{
			Ext.getCmp('goodTranceWindow').show();
		}
		Ext.getCmp('goodTranceWindow').flag = 'update';
		Ext.getCmp('goodTranceWindowInnerForm').getForm().loadRecord(selections[0]);
	},
	/**.
	 * <p>
	 * 删除货物托运清单的按钮的方法</br>
	 * </p>
	 * @author  张斌
	 * @时间    2012-04-06
	 */
	deleteFunction:function(){
		var me = this;
		var selections = Ext.getCmp('goodsTranceGird').getSelectionModel( ).getSelection();
		if(selections.length==0){
			return;
		}
		MessageUtil.showQuestionMes(i18n('i18n.recompense.isDeleteThese'),
			function(e){
				if (e == 'yes') {
					for(var i=0;i<selections.length;i++){
						Ext.getCmp('recompenseListGird').getStore().each(function(record){
							if(record.get('relationFlag')==selections[i].get('relationFlag')){
								Ext.getCmp('recompenseListGird').getStore().remove(record);
							}
						});
					}
					Ext.getCmp('goodsTranceGird').getStore().remove(selections);
					Ext.getCmp('goodsTrancesListTotalAmount').getTotalAmount();
				}
			});
	},
	layout:'border',
	getItems:function(){
		var me  = this;
		return [{
			xtype:'panel',
			region:'north',
			height:30,
			items:[new OperationButtonPanel({'addFunction':me.addFunction,
				'deleteFucntion':me.deleteFunction,'updateFunction':me.updateFunction,
					'buttonDisabled':false,'addId':'tranceListAdd','updateId':'tranceListUpdate','deleteId':'tranceListDelete'})]
			},{
				xtype:'panel',
				region:'center',
				items:[new GoodsTranceGird()],
				layout:'fit'
				
			},{
				xtype:'panel',
				region:'south',
				items:[new GoodsTrancesListTotalAmount()]
			}]
	},
	
	initComponent:function(){
		var me = this;
		me.items = me.getItems();
		this.callParent();
}});
//统计货物实际金额
Ext.define('GoodsTrancesListTotalAmount',{
	extend:'Ext.panel.Panel',
    items:[{
    	xtype:'displayfield',
    	width:500,
    	id:'goodsTrancesListTotalAmount',
    	//统计总金额信息
    	getTotalAmount:function(){
    		var me = this;
    		var totalCount = 0;
    		Ext.getCmp('goodsTranceGird').getStore().each(function(record){
    			totalCount = totalCount+record.get('realPrice');
    		});
    		me.setValue('<a  style="color:red;font-size:25px;">'+i18n('i18n.recompense.totalAmountCount')+totalCount+'</a>');
    	},
    	style:'float:right',
    	value:'<a  style="color:red;font-size:25px;">'+i18n('i18n.recompense.totalAmountCount')+'0'+'</a>'
    }]
});
/**.
 * <p>
 * 根据规范写的索赔清单Tab中的子Panel</br>
 * </p>
 * @author  张斌
 * @时间    2012-04-06
 */
Ext.define('RecompenseListPanel',{// 
	extend:'TabContentPanel',
	title : i18n('i18n.recompense.claimantList'),
	items:null,
	height:315,
	sutoScroll:true,
	/**.
	 * <p>
	 * 添加索赔清单的按钮的方法</br>
	 * </p>
	 * @author  张斌
	 * @时间    2012-04-06
	 */
	addFunction:function(){
		
		if(Ext.isEmpty(Ext.getCmp('recompenseListWindow'))){
			new RecompenseListWindow().show();
		}else{
			Ext.getCmp('recompenseListWindow').show();
		}
		Ext.getCmp('recompenseListWindow').flag = 'add';
		Ext.getCmp('recompenseListWindowInnerForm').getForm().reset();
		Ext.getCmp('recompenseListWindowInsurType').store.removeAll();
		Ext.getCmp('recompenseListWindowInsurType').getStore().loadRecords(Ext.getCmp(CONFIGNAME.get('insurType')).store.data.items);
		Ext.getCmp('recompenseListWindowGoodsName').store.removeAll();
		Ext.getCmp('goodsTranceGird').getStore().each(function(record){
			var recompenseList = new RecompenseList(record.data)
			Ext.getCmp('recompenseListWindowGoodsName').getStore().add(recompenseList);
		});
		Ext.getCmp('recompenseListWindowGoodsName').setReadOnly(false);
		Ext.getCmp('recompenseListWindowGoodsName').removeCls('readonly');
	},
	/**.
	 * <p>
	 * 删除索赔清单的按钮的方法</br>
	 * </p>
	 * @author  张斌
	 * @时间    2012-04-06
	 */
	deleteFunction:function(){
		var me= this;
		var selections = Ext.getCmp('recompenseListGird').getSelectionModel( ).getSelection();
		if(selections.length==0){
			return;
		}
		MessageUtil.showQuestionMes(i18n('i18n.recompense.isDeleteThese'),
			function(e){
				if (e == 'yes') {
					Ext.getCmp('recompenseListGird').getStore().remove(selections);
					Ext.getCmp('recompenseListTotalAmount').getTotalAmount();
				}
		});
	},
	/**.
	 * <p>
	 * 修改出险描述的按钮的方法</br>
	 * </p>
	 * @author  张斌
	 * @时间    2012-04-06
	 */
	updateFunction:function(){
		var selections = Ext.getCmp('recompenseListGird').getSelectionModel( ).getSelection();
		if(selections.length!=1){
			return;
		}
		if(Ext.isEmpty(Ext.getCmp('recompenseListWindow'))){
			new RecompenseListWindow().show();
		}else{
			Ext.getCmp('recompenseListWindow').show();
		}
		Ext.getCmp('recompenseListWindowGoodsName').setReadOnly(true);
		Ext.getCmp('recompenseListWindowGoodsName').addCls('readonly');
		var selections = Ext.getCmp('recompenseListGird').getSelectionModel().getSelection();
		if(selections.length!=1){
			MessageUtil.showMessage(i18n('i18n.recompense.pleaseSelectOneLine'));
			return ;
		}
		if(Ext.isEmpty(Ext.getCmp('recompenseListWindow'))){
			new RecompenseListWindow().show();
		}else{
			Ext.getCmp('recompenseListWindow').show();
		}
		Ext.getCmp('recompenseListWindow').flag = 'update';
		Ext.getCmp('recompenseListWindowInsurType').store.removeAll();
		Ext.getCmp('recompenseListWindowInsurType').getStore().loadRecords(Ext.getCmp(CONFIGNAME.get('insurType')).store.data.items);
		Ext.getCmp('recompenseListWindowGoodsName').store.removeAll();
		Ext.getCmp('goodsTranceGird').getStore().each(function(record){
			//:
			var recompenseList = new RecompenseList(record.data)
			Ext.getCmp('recompenseListWindowGoodsName').getStore().add(recompenseList);
		});
		Ext.getCmp('recompenseListWindowInnerForm').getForm().loadRecord(selections[0]);
	},
	layout:'border',
	getItems:function(){
		var me = this;
		return [{
			region:'north',
			height:30,
			items:[new OperationButtonPanel({'addFunction':me.addFunction,'deleteFucntion':me.deleteFunction,'updateFunction':me.updateFunction
				,'buttonDisabled':false,'addId':'recompenseListAdd','updateId':'recompenseListUpdate'
					,'deleteId':'recompenseListDelete'})]
		},{
			region:'center',
			layout:'fit',
			items:[new RecompenseListGird()]
		},{
			region:'south',
			items:[new RecompenseListTotalAmount()]
		}]
	},
	initComponent:function(){
		var me = this;
		me.items = me.getItems();
		this.callParent();
}});
//统计索赔清单索赔金额
Ext.define('RecompenseListTotalAmount',{
	extend:'Ext.panel.Panel',
    items:[{
    	xtype:'displayfield',
    	id:'recompenseListTotalAmount',
    	width:500,
    	//统计总金额信息
    	getTotalAmount:function(){
    		var me = this;
    		var totalCount = 0;
    		Ext.getCmp('recompenseListGird').getStore().each(function(record){
    			totalCount = totalCount+record.get('amount');
    		});
    		me.setValue('<a  style="color:red;font-size:25px;">'+i18n('i18n.recompense.totalAmountCount')+totalCount+'</a>');
    	},
    	style:'float:right',
    	value:'<a  style="color:red;font-size:25px;">'+i18n('i18n.recompense.totalAmountCount')+'0'+'</a>'
    }]
});
/**.
 * <p>
 * 根据规范写的上传附件Tab中的子Panel</br>
 * </p>
 * @author  张斌
 * @时间    2012-04-06
 */
Ext.define('AttachmentListPanel',{
	extend:'TabContentPanel',
	title : i18n('i18n.recompense.upLoadAttachment'),
	items:null,
	height:315,
	/**.
	 * <p>
	 * 添加附件按钮的方法</br>
	 * </p>
	 * @author  张斌
	 * @时间    2012-04-06
	 */
	addFunction:function(){
		if(Ext.isEmpty(Ext.getCmp('uploadAttachmentWin'))){
			new UploadAttachmentWin().show();
		}else{
			Ext.getCmp('uploadAttachmentWin').show();
		}
	},
	/**.
	 * <p>
	 * 删除附件按钮的方法</br>
	 * </p>
	 * @author  张斌
	 * @时间    2012-04-06
	 */
	deleteFunction:function(){
		var successFn = function(json){
			MessageUtil.showInfoMes(i18n('i18n.recompense.attachmentDeleteSuccess'));
			Ext.getCmp('attachmentGrid').getStore().remove(selections);
		};
		var failureFn = function(json){
			if(Ext.isEmpty(json)){
				MessageUtil.showErrorMes(i18n('i18n.recompense.serviceMoreTime'));
				//DpUtil.showMessage(i18n('i18n.recompense.serviceMoreTime'));
			}else{
				MessageUtil.showErrorMes(json.message);
			}
		};
		var selections = Ext.getCmp('attachmentGrid').getSelectionModel( ).getSelection();
		if(selections.length!=1){
			MessageUtil.showErrorMes(i18n('i18n.recompense.attachmentOnlyDelete'));
			return;
		}
		var params = {'fileInfo':{'id':selections[0].get('id'),'fileName':selections[0].get(CONFIGNAME.get('attachName')),'savePath':selections[0].get(CONFIGNAME.get('attachAddress'))}};
		recompenseData.deleteFileInfo(params,successFn,failureFn);
	},
	layout:'border',
	getItems:function(){
		var me = this;
		return [{
			region:'north',
			height:30,
			items:[new OperationButtonPanel({'addFunction':me.addFunction,'addId':'attachmentListAddId','deleteFucntion':me.deleteFunction,'deleteId':'attachmentListDeleteId'})]
		},{
			region:'center',
			layout:'fit',
			items:[new Attachment()]
		}]
	},
	initComponent:function(){
		var me = this;
		me.items = me.getItems();
		this.callParent();
}});
//上传附件弹出框
Ext.define('UploadAttachmentWin',{
	extend:'PopWindow',
	id:'uploadAttachmentWin',
	title:i18n('i18n.recompense.upLoadAttachment'),
	layout:{
		type:'vbox',
		align:'stretch'
	},
	operateType:null,
	recordExchangeRuleManagerData:null,
	width:400,
	height:120,
	parent:null,
	attachmentForm:null,  //附件form
	initComponent:function(){
		var me = this;
		me.items = [
		{
			xtype:'basicformpanel',
			flex:1,
			layout:{
				type : 'hbox'
			},
			defaults : {
				margins : '0 5 0 0'
			},
			items:[{
				xtype:'filefield',
				name:'file',
				fieldLabel:i18n('i18n.recompense.pleaseInputFile'),
				labelWidth:100,
				buttonText:i18n('i18n.recompense.browse'),
				flex:3
			}]
		}];
		me.fbar = me.getFbar();
		this.callParent();
	},
	getFbar:function(){
		var me = this;
		return [{
			text:i18n('i18n.recompense.updateload'),
			xtype:'button',
			scope:me,
			handler:me.uploadFile
		},{
			text:i18n('i18n.recompense.cancel'),
			xtype:'button',
			handler:function(){
				me.close();
			}
		}];
	},
	//文件上传
	uploadFile:function(){
		var me = this;
		var successFn = function(json){
			var fileInfo = json.fileInfoList[0];
			if(Ext.isEmpty(fileInfo)){
				if(Ext.isEmpty(json.message)){
					MessageUtil.showMessage(i18n('i18n.recompense.pleaseSelectAttachment'));
					return ;
				}
				var message = i18n(json.message);
				if(Ext.isEmpty(message)){
					MessageUtil.showErrorMes(json.message);
				}else{
					MessageUtil.showErrorMes(message);
				}
				return ;
			}
			MessageUtil.showInfoMes(i18n('i18n.recompense.successOnly'), i18n('i18n.recompense.file') + fileInfo.fileName + i18n('i18n.recompense.uploadFileSuccess'));
        	var fileInfoModel = new AttachList();
        	fileInfoModel.set(CONFIGNAME.get('attachName'),fileInfo.fileName);
        	fileInfoModel.set(CONFIGNAME.get('attachAddress'),fileInfo.savePath);
        	Ext.getCmp('attachmentGrid').getStore().add(fileInfoModel);
        	me.close();
		};
		var failureFn = function(json){
			if(Ext.isEmpty(json)){
				MessageUtil.showErrorMes(i18n('i18n.recompense.serviceMoreTime'));
				//DpUtil.showMessage(i18n('i18n.recompense.serviceMoreTime'));
			}else{
				MessageUtil.showErrorMes(json.message);
			}
		};
		var form = me.down('form');
		recompenseData.updateLoadFile(form,successFn,failureFn);
	}
});

/**.
 * <p>
 * 理赔上报主页面</br>
 * </p>
 * @author  张斌
 * @时间    2012-04-06
 */
Ext.define('RecompenseInfoTab',{
	extend:'NormalTabPanel',
	id:'recompenseInfoTab',
	flag:null,
	flex :1,
	getItems:function(){
		var me= this;
		var recompenseInfo = null;
		if(Ext.isEmpty(me.flag)){
			recompenseInfo = new RecompenseInfo();
		}else{
			recompenseInfo = new RecompenseInfo({'flag':me.flag});
		}
		var tab= [recompenseInfo,new RecompenseDescriptionPanel(),new GoodsTrancesListPanel(),new RecompenseListPanel(),new AttachmentListPanel()];
		if(me.flag=='online'){
			tab.push(new BankInfo());
		}
		return tab;
	},
	initComponent:function(){
		var me = this;
	    me.items = me.getItems();
		this.callParent();
	}
});
//弹出框
Ext.define('DangerInfoWindow',{
	extend:'PopWindow',
	title:i18n('i18n.recompense.dangerDescription'),
	id:'dangerInfoWindow',
	fbar:null,
	items:null,
	closeAction:'hide',
	layout: 'fit',
	initComponent:function(){
		var me = this;
		me.fbar = me.getFbar();
		me.items = me.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [new DangerInfoWindowInnerForm()];
	},
	/**.
	 * <p>
	 * 添加一条出险描述信息</br>
	 * </p>
	 * @author  张斌
	 * @时间    2012-04-09
	 */
	addDangerInfo:function(){
		var me = this;
		if(Ext.getCmp('dangerInfoWindowInnerForm').getForm().isValid( )){
			var issueDescriptionModel = new IssueDescriptionModel();
			Ext.getCmp('dangerInfoWindowInnerForm').getForm().updateRecord(issueDescriptionModel);
			Ext.getCmp('dangerInfoGrid').getStore().add(issueDescriptionModel);
			Ext.getCmp('dangerInfoWindowInnerForm').getForm().reset();
			me.hide();
		}
	},
	/**.
	 * <p>
	 * 修改一条出险描述信息</br>
	 * </p>
	 * @author  张斌
	 * @时间    2012-04-09
	 */
	updateDangerInfo:function(){
		var me = this;
		if(Ext.getCmp('dangerInfoWindowInnerForm').getForm().isValid( )){
			var issueDescriptionModel = new IssueDescriptionModel();
			Ext.getCmp('dangerInfoWindowInnerForm').getForm().updateRecord(issueDescriptionModel);
			var selection = Ext.getCmp('dangerInfoGrid').getSelectionModel().getSelection()[0];
			selection.set(CONFIGNAME.get('insurType'),issueDescriptionModel.get(CONFIGNAME.get('insurType')));
			selection.set(CONFIGNAME.get('quality'),issueDescriptionModel.get(CONFIGNAME.get('quality')));
			selection.set(CONFIGNAME.get('description'),issueDescriptionModel.get(CONFIGNAME.get('description')));
			me.hide();
		}
	},
	getFbar:function(){
		var me = this;
		return [{
			xtype:'button',
			text:i18n('i18n.recompense.determine'),
			handler:function(){
				if(me.flag=='add'){
					me.addDangerInfo();
				}else if(me.flag=='update'){
					me.updateDangerInfo();
				}
			}
			
		},{
			xtype:'button',
			text:i18n('i18n.recompense.cancel'),
			handler:function(){
				me.hide();
			}
		}];
	}
});

Ext.define('DangerInfoWindowInnerForm',{
	extend:'NoTitleFormPanel',
	id:'dangerInfoWindowInnerForm',
	items:null,
	initComponent:function(){
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{
			layout:{
				type:'table',
				columns:2
			},
			defaults:{
				labelWidth:60,
				width:200
			},
			items:[{
				fieldLabel:i18n('i18n.recompense.dangerType'),
				xtype: 'combo',
				id:'dangerInfoFormInsurType',
				store: DpUtil.getStore('dangerInfoFormInsurType',null,['code','codeDesc'],[]),
				displayField: 'codeDesc',
				valueField:'code',
				name:CONFIGNAME.get('insurType'),
				queryMode:'local',
				style:'margin-right:5px;',
				allowBlank:false,
				forceSelection: true,
				editable:false
			},{
				fieldLabel:i18n('i18n.recompense.number'),//出险数量
				xtype: 'numberfield',
				allowBlank:false,
				name:CONFIGNAME.get('quality'),
				decimalPrecision:0,
				maxValue:9999999999,
				minValue: 1
    		},{
    			fieldLabel:i18n('i18n.recompense.description'),
				xtype: 'textarea',
				emptyText : i18n('i18n.recompense.pleaseInput30'),
				minLength:30,
				maxLength:1200,
				
				name:CONFIGNAME.get('description'),
				width:400,
				colspan:2,
				allowBlank:false
    		}]		
		}];
	}
});
//弹出框
Ext.define('GoodTranceWindow',{
	extend:'PopWindow',
	title:i18n('i18n.recompense.goodsConsignmentList'),
	id:'goodTranceWindow',
	fbar:null,
	items:null,
	closeAction:'hide',
	layout: 'fit',
	initComponent:function(){
		var me = this;
		me.fbar = me.getFbar();
		me.items = me.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [new GoodTranceWindowInnerForm()];
	},
	/**.
	 * <p>
	 * 添加一条出险描述信息</br>
	 * </p>
	 * @author  张斌
	 * @时间    2012-04-09
	 */
	addDangerInfo:function(){
		var me = this;
		if(Ext.getCmp('goodTranceWindowInnerForm').getForm().isValid( )){
			var recompenseList = new RecompenseList();
			Ext.getCmp('goodTranceWindowInnerForm').getForm().updateRecord(recompenseList);
			recompenseList.set('relationFlag',new Date().getTime());
			Ext.getCmp('goodsTranceGird').getStore().add(recompenseList);
			Ext.getCmp('goodsTrancesListTotalAmount').getTotalAmount();
			Ext.getCmp('goodTranceWindowInnerForm').getForm().reset();
			me.hide();
		}
	},
	/**.
	 * <p>
	 * 修改一条出险描述信息</br>
	 * </p>
	 * @author  张斌
	 * @时间    2012-04-09
	 */
	updateDangerInfo:function(){
		var me = this;
		if(Ext.getCmp('goodTranceWindowInnerForm').getForm().isValid( )){
			var recompenseList = new RecompenseList();
			Ext.getCmp('goodTranceWindowInnerForm').getForm().updateRecord(recompenseList);
			var selection = Ext.getCmp('goodsTranceGird').getSelectionModel().getSelection()[0];
			selection.set(CONFIGNAME.get('goodsName'),recompenseList.get(CONFIGNAME.get('goodsName')));
			selection.set(CONFIGNAME.get('price'),recompenseList.get(CONFIGNAME.get('price')));
			selection.set(CONFIGNAME.get('quality'),recompenseList.get(CONFIGNAME.get('quality')));
			selection.set(CONFIGNAME.get('realPrice'),recompenseList.get(CONFIGNAME.get('realPrice')));
			Ext.getCmp('recompenseListGird').getStore().each(function(record){
				if(record.get('relationFlag')==selection.get('relationFlag')){
					record.set(CONFIGNAME.get('goodsName'),recompenseList.get(CONFIGNAME.get('goodsName')));
					record.set(CONFIGNAME.get('price'),recompenseList.get(CONFIGNAME.get('price')));
					record.set(CONFIGNAME.get('quality'),recompenseList.get(CONFIGNAME.get('quality')));
					record.set(CONFIGNAME.get('realPrice'),recompenseList.get(CONFIGNAME.get('realPrice')));
				}
			});
			Ext.getCmp('goodsTrancesListTotalAmount').getTotalAmount();
			me.hide();
		}
	},
	getFbar:function(){
		var me = this;
		return [{
			xtype:'button',
			text:i18n('i18n.recompense.determine'),
			handler:function(){
				if(me.flag=='add'){
					me.addDangerInfo();
				}else if(me.flag=='update'){
					me.updateDangerInfo();
				}
			}
			
		},{
			xtype:'button',
			text:i18n('i18n.recompense.cancel'),
			handler:function(){
				me.hide();
			}
		}];
	}
});

Ext.define('GoodTranceWindowInnerForm',{
	extend:'NoTitleFormPanel',
	id:'goodTranceWindowInnerForm',
	items:null,
	initComponent:function(){
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{
			layout:{
				type:'table',
				columns:2
			},
			defaults:{
				labelWidth:60,
				width:200
			},
			items:[{
    			fieldLabel:i18n('i18n.recompense.goodsName'),
				xtype: 'textfield',
				maxLength:80,
				name:CONFIGNAME.get('goodsName'),
				allowBlank:false
    		},{
    			fieldLabel:i18n('i18n.recompense.onlyPrice'),//单价
				xtype: 'numberfield',
				name:CONFIGNAME.get('price'),
				decimalPrecision:2,
				id:'goodTranceWindowInnerForm_Price',
				maxValue:99999999.99,
				minValue: 0.01,
				allowBlank:false,
				listeners:{
					change:function(th){
						var realPrice = 0;
						realPrice = th.getValue()*Ext.getCmp('goodTranceWindowInnerForm_Quality').getValue();
						Ext.getCmp('goodTranceWindowInnerForm_RealPrice').setValue(realPrice);
					}
				}
			},{
				fieldLabel:i18n('i18n.recompense.number'),//数量
				xtype: 'numberfield',
				allowBlank:false,
				name:CONFIGNAME.get('quality'),
				id:'goodTranceWindowInnerForm_Quality',
				maxValue:9999999999,
				minValue: 1,
				decimalPrecision:0,
				listeners:{
					change:function(th){
						var realPrice = 0;
						realPrice = th.getValue()*Ext.getCmp('goodTranceWindowInnerForm_Price').getValue();
						Ext.getCmp('goodTranceWindowInnerForm_RealPrice').setValue(realPrice);
					}
				}
    		},{
    			fieldLabel:i18n('i18n.recompense.realPrice'),//实际价值
				xtype: 'numberfield',
				readOnly:true,
				cls:'readonly',
				name:CONFIGNAME.get('realPrice'),
				id:'goodTranceWindowInnerForm_RealPrice',
				decimalPrecision:2,
				maxValue:9999999999.99,
				minValue: 0,
				allowBlank:false
			}]		
		}];
	}
});
//索赔清单弹出窗口
Ext.define('RecompenseListWindow',{
	extend:'PopWindow',
	title:i18n('i18n.recompense.claimantList'),
	id:'recompenseListWindow',
	fbar:null,
	items:null,
	closeAction:'hide',
	layout: 'fit',
	initComponent:function(){
		var me = this;
		me.fbar = me.getFbar();
		me.items = me.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [new RecompenseListWindowInnerForm()];
	},
	/**.
	 * <p>
	 * 添加一条索赔清单信息</br>
	 * </p>
	 * @author  张斌
	 * @时间    2012-04-09
	 */
	addDangerInfo:function(){
		var me = this;
		if(Ext.getCmp('recompenseListWindowInnerForm').getForm().isValid( )){
			var recompenseList = new RecompenseList();
			Ext.getCmp('recompenseListWindowInnerForm').getForm().updateRecord(recompenseList);
			if(recompenseList.get(CONFIGNAME.get('quality'))<recompenseList.get(CONFIGNAME.get('number'))){
				MessageUtil.showMessage(i18n('i18n.recomepnse.dangerNumSmallTranceNum'));
				return ;
			}
			//索赔清单中时候已经有了该货物的信息
			var isHave = false;
			Ext.getCmp('recompenseListGird').getStore().each(function(record){
				if(recompenseList.get('relationFlag')==record.get('relationFlag')){
					MessageUtil.showMessage(i18n('i18n.recompense.haveOneGoodInfo'));
					isHave = true;
					return;
				}
			});
			//如果是则不执行下面的操作
			if(isHave){
				return;
			}
			recompenseList.set(CONFIGNAME.get('isClaims'),true);
			Ext.getCmp('recompenseListGird').getStore().add(recompenseList);
			Ext.getCmp('recompenseListTotalAmount').getTotalAmount();
			Ext.getCmp('recompenseListWindowInnerForm').getForm().reset();
			me.hide();
		}
	},
	/**.
	 * <p>
	 * 修改一条索赔清单信息</br>
	 * </p>
	 * @author  张斌
	 * @时间    2012-04-09
	 */
	updateDangerInfo:function(){
		var me = this;
		if(Ext.getCmp('recompenseListWindowInnerForm').getForm().isValid()){
			var recompenseList = new RecompenseList();
			Ext.getCmp('recompenseListWindowInnerForm').getForm().updateRecord(recompenseList);
			if(recompenseList.get(CONFIGNAME.get('quality'))<recompenseList.get(CONFIGNAME.get('number'))){
				MessageUtil.showMessage(i18n('i18n.recomepnse.dangerNumSmallTranceNum'));
				return ;
			}
			var selection = Ext.getCmp('recompenseListGird').getSelectionModel().getSelection()[0];
			selection.set(CONFIGNAME.get('goodsName'),recompenseList.get(CONFIGNAME.get('goodsName')));
			selection.set(CONFIGNAME.get('insurType'),recompenseList.get(CONFIGNAME.get('insurType')));
			selection.set(CONFIGNAME.get('number'),recompenseList.get(CONFIGNAME.get('number')));
			selection.set(CONFIGNAME.get('amount'),recompenseList.get(CONFIGNAME.get('amount')));
			selection.set(CONFIGNAME.get('description'),recompenseList.get(CONFIGNAME.get('description')));
			selection.set(CONFIGNAME.get('price'),recompenseList.get(CONFIGNAME.get('price')));
			selection.set(CONFIGNAME.get('quality'),recompenseList.get(CONFIGNAME.get('quality')));
			selection.set(CONFIGNAME.get('realPrice'),recompenseList.get(CONFIGNAME.get('realPrice')));
			Ext.getCmp('recompenseListTotalAmount').getTotalAmount();
			me.hide();
		}
	},
	getFbar:function(){
		var me = this;
		return [{
			xtype:'button',
			id:'sureBtnId',
			text:i18n('i18n.recompense.determine'),
			handler:function(){
				if(me.flag=='add'){
					me.addDangerInfo();
				}else if(me.flag=='update'){
					me.updateDangerInfo();
				}
			}
			
		},{
			xtype:'button',
			text:i18n('i18n.recompense.cancel'),
			handler:function(){
				me.hide();
			}
		}];
	}
});

//索赔清单弹窗
Ext.define('RecompenseListWindowInnerForm',{
	extend:'NoTitleFormPanel',
	id:'recompenseListWindowInnerForm',
	items:null,
	initComponent:function(){
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{
			layout:{
				type:'table',
				columns:2
			},
			defaults:{
				labelWidth:60,
				width:200
			},
			items:[{
    			fieldLabel:i18n('i18n.recompense.goodsName'),
    			xtype: 'combo',
				maxLength:80,
				queryMode:'local',
				id:'recompenseListWindowGoodsName',
				name:CONFIGNAME.get('goodsName'),
				displayField: CONFIGNAME.get('goodsName'),
				valueField:CONFIGNAME.get('goodsName'),
				store:DpUtil.getStore('recompenseListWindowGoodsName','RecompenseList',null,[]),
				allowBlank:false,
				forceSelection: true,
				editable:false,
				listeners:{
					select:function(th,records){
						var recompenseList = new RecompenseList();
						Ext.getCmp('recompenseListWindowInnerForm').getForm().updateRecord(recompenseList);
						recompenseList.set(CONFIGNAME.get('goodsName'),records[0].get(CONFIGNAME.get('goodsName')));
						recompenseList.set(CONFIGNAME.get('price'),records[0].get(CONFIGNAME.get('price')));
						recompenseList.set(CONFIGNAME.get('quality'),records[0].get(CONFIGNAME.get('quality')));
						recompenseList.set(CONFIGNAME.get('realPrice'),records[0].get(CONFIGNAME.get('realPrice')));
						recompenseList.set('relationFlag',records[0].get('relationFlag'));
						Ext.getCmp('recompenseListWindowInnerForm').loadRecord(recompenseList);
					}
				}
    		},{
    			fieldLabel:i18n('i18n.recompense.onlyPrice'),
				xtype: 'numberfield',
				name:CONFIGNAME.get('price'),//单价
				decimalPrecision:2,
				readOnly:true,
				cls:'readonly',
				maxValue:99999999.99,
				minValue: 0.01
			},{
				fieldLabel:i18n('i18n.recompense.tranceNumber'),
				xtype: 'numberfield',
				name:CONFIGNAME.get('quality'),//数量
				decimalPrecision:0,
				readOnly:true,
				cls:'readonly',
				maxValue:9999999999,
				minValue: 1
    		},{
    			fieldLabel:i18n('i18n.recompense.realPrice'),
				xtype: 'numberfield',
				id:'realPriceId',
				readOnly:true,
				cls:'readonly',
				name:CONFIGNAME.get('realPrice'),//实际价值
				maxValue:9999999999.99,
				minValue: 0.01
			},{
				fieldLabel:i18n('i18n.recompense.dangerType'),//出现类型
				xtype: 'combo',
				id:'recompenseListWindowInsurType',
				store: DpUtil.getStore('recompenseListWindowInsurType',null,['code','codeDesc'],[]),
				displayField: 'codeDesc',
				valueField:'code',
				name:CONFIGNAME.get('insurType'),
				queryMode:'local',
				style:'margin-right:5px;',
				allowBlank:false,
				forceSelection: true,
				editable:false
			},{
				fieldLabel:i18n('i18n.recompense.dangerNumber'),//出现数量
				xtype: 'numberfield',
				allowBlank:false,
				name:CONFIGNAME.get('number'),
				decimalPrecision:0,
				maxValue:9999999999,
				minValue: 1
    		},{
    			fieldLabel:i18n('i18n.recompense.claimantAmount'),//索赔金额
				xtype: 'numberfield',
				allowBlank:false,
				decimalPrecision:2,
				name:CONFIGNAME.get('amount'),
				colspan:2,
				maxValue:99999999999.99,
				minValue: 0
			},{
    			fieldLabel:i18n('i18n.recompense.description'),//描述
				xtype: 'textarea',
				maxLength:1200,
				name:CONFIGNAME.get('description'),
				width:400,
				colspan:2,
				allowBlank:false
    		},{
				xtype: 'textfield',
				name:'relationFlag',
				colspan:1,
				hidden:true
			}]		
		}];
	}
});
/**.
 * <p>
 * 理赔类型FORM PANEL（当选择理赔类型为“异常签收理赔”和“丢货理赔”时，
 * 出险信息中的“运单号/差错编号”处显示为“运单号”
 * ；当选择理赔类型为“未开单理赔”时，出险信息中的“运单号/差错编号”处显示为“差错编号”）</br>
 * </p>
 * @author  张斌
 * @returns {RecompenseType}
 * @时间    2012-04-08
 */
Ext.define('RecompenseType',{
	extend:'TitleFormPanel',
	id:'recompenseTypeForm',
	height:69,
	/**.
	 * <p>
	 * 当选择理赔类型为“异常签收理赔”和“丢货理赔”时，
	 * 出险信息中的“运单号/差错编号”处显示为“运单号”
	 * ；当选择理赔类型为“未开单理赔”时，出险信息中的“运单号/差错编号”处显示为“差错编号”,选择理赔类型为"未开单类理赔","快速理赔"禁用</br>
	 * </p>
	 * @author  张斌
	 * @时间    2012-04-08
	 */
	opratorFunction:function(th){
		if(th.oldValue!=th.getValue()){
			MessageUtil.showQuestionMes(i18n('i18n.recompense.updateRecompenseType'),
       			function(e){
       				if (e == 'yes') {
       					Ext.getCmp('recompenseInfoForm').getForm().reset();
       					Ext.getCmp('dangerInfoForm').getForm().reset();
       					Ext.getCmp('goodsTranceGird').getStore().removeAll();
       					Ext.getCmp('dangerInfoGrid').getStore().removeAll();
       					Ext.getCmp('goodsTranceGird').getStore().removeAll();
       					Ext.getCmp('goodsTrancesListTotalAmount').getTotalAmount();
       					Ext.getCmp('attachmentGrid').getStore().removeAll();
       					Ext.getCmp(CONFIGNAME.get('waybillNumber')).setValue('');
       					th.oldValue = th.getValue();
       					if(th.getValue()=='unbilled'){//未开单
                  			   Ext.getCmp(CONFIGNAME.get('waybillNumber')).emptyText = i18n('i18n.recompense.ErroeNum');
                 			   Ext.getCmp(CONFIGNAME.get('waybillNumber')).initField( );
                 			   //三个combox的数据都修改
                 			   Ext.getCmp(CONFIGNAME.get('insurType')).reset();
                 			   Ext.getCmp(CONFIGNAME.get('insurType')).getStore().removeAll(true);
                 			   Ext.getCmp(CONFIGNAME.get('insurType')).getStore().loadData(DataDictionary.DANGER_TYPE);
                 			   Ext.getCmp('fastRecompenseMethod_id').setDisabled(true);
                 			   Ext.getCmp('normalRadio').setValue(true);
                  			 }
                  			 if(th.getValue()=='lost_goods'){//丢货理赔
                  				var dataInsurType = new Array();
                 			   for(var i = 0;i<DataDictionary.DANGER_TYPE.length;i++){
                 				   if(DataDictionary.DANGER_TYPE[i].code =='PIECE_LOST'){
                 					   dataInsurType.push(DataDictionary.DANGER_TYPE[i]);
                 				   }
                 				   if(DataDictionary.DANGER_TYPE[i].code =='TICKET_LOST'){
                 					   dataInsurType.push(DataDictionary.DANGER_TYPE[i]);
                 				   }
                 			   }
                 			   //三个combox的数据都修改
                 			   Ext.getCmp(CONFIGNAME.get('insurType')).reset();
                 			   Ext.getCmp(CONFIGNAME.get('insurType')).getStore().removeAll(true);
                 			   Ext.getCmp(CONFIGNAME.get('insurType')).getStore().loadData(dataInsurType);
                 			   Ext.getCmp(CONFIGNAME.get('waybillNumber')).emptyText = i18n('i18n.recompense.orderNum');
               			       Ext.getCmp(CONFIGNAME.get('waybillNumber')).initField( );
               			       Ext.getCmp('fastRecompenseMethod_id').setDisabled(false);
                 		   }
                  			if(th.getValue()=='abnormal'){//异常签收
                  				var dataInsurType = new Array();
                 			   for(var i = 0;i<DataDictionary.DANGER_TYPE.length;i++){
                 				   if(DataDictionary.DANGER_TYPE[i].code =='PIECE_LOST'||DataDictionary.DANGER_TYPE[i].code =='TICKET_LOST'){
                 					  
                 				   }else{
                 					   dataInsurType.push(DataDictionary.DANGER_TYPE[i]);
                 				   }
                 			   }
                 			   //三个combox的数据都修改
                 			   Ext.getCmp(CONFIGNAME.get('insurType')).reset();
                 			   Ext.getCmp(CONFIGNAME.get('insurType')).getStore().removeAll(true);
                 			   Ext.getCmp(CONFIGNAME.get('insurType')).getStore().loadData(dataInsurType);
                 			   Ext.getCmp(CONFIGNAME.get('waybillNumber')).emptyText = i18n('i18n.recompense.orderNum');
               			       Ext.getCmp(CONFIGNAME.get('waybillNumber')).initField( );
               			       Ext.getCmp('fastRecompenseMethod_id').setDisabled(false);
                 			 }
       				}else{
       					Ext.getCmp(CONFIGNAME.get('recompenseType')).setValue(th.oldValue);
       				}
       			}); 
 		   }
 	   },
	getItems:function(){
		var me = this;
		return [{xtype: 'basicfiledset',
			title: i18n('i18n.recompense.recompenseType'),
			layout:{
				type:'table',
				columns:3
			},
			defaultType:'textfield',
			defaults:{
				labelSeparator:'',
				labelWidth:55,
				width:210
			},
		    items:[{
				colspan: 1,
				id:CONFIGNAME.get('recompenseType'),
				name: CONFIGNAME.get('recompenseType'),
		        fieldLabel:i18n('i18n.recompense.recompenseType*'),
		        xtype:'combo',
		        value:'abnormal',
		        oldValue:'abnormal',
	            store: DpUtil.getStore(CONFIGNAME.get('recompenseType'),null,['code','codeDesc'],DataDictionary.RECOMPENSE_TYPE),
	             queryMode: 'local',
	             forceSelection: true,
	             editable:false,
	             allowBlank:false,
	             displayField: 'codeDesc',
	             valueField: 'code',
	             listeners:{
              	   select:function(th,e,o){
              		   me.opratorFunction(th);
	               }
		        }
		    }]
	   }];
	},
    initComponent:function(){
    	var me = this;
        me.items = me.getItems();
		this.callParent();
    }
});
/**.
* <p>
* 出险信息界面//用于理赔上报和详细信息查看</br>
* </p>
* @author  张斌
* @retuens {DangerInfo}
* @时间    2012-04-11
*/
Ext.define('DangerInfo',{
	extend:'TitleFormPanel',
	id:'dangerInfoForm',
	height:186,
	waybillNumBlur:function(waybillNum){
		var me = this;
		var dangerTime = Ext.getCmp(CONFIGNAME.get('insurDate')).getValue();
		var dangerType = Ext.getCmp(CONFIGNAME.get('insurType')).getValue();
		var dangerNumber = Ext.getCmp(CONFIGNAME.get('insurQuantity')).getValue();
		var deptId = Ext.getCmp(CONFIGNAME.get('deptId')).getValue();
		var successFn = function(json){
			me.getForm().reset();
			json.waybill.transType= DpUtil.changeDictionaryCodeToDescrip(json.waybill.transType,DataDictionary.TRANSPORT_TYPE);
			var waybillModel = new WaybillModel(json.waybill);	
			me.getForm().loadRecord(waybillModel);
			var sendDate = null;
			if(!Ext.isEmpty(waybillModel.get(CONFIGNAME.get('sendDate')))){
				sendDate = waybillModel.get(CONFIGNAME.get('sendDate')).format('yyyy-MM-dd');
			}
			Ext.getCmp(CONFIGNAME.get('sendDate')).setValue(sendDate);
			Ext.getCmp(CONFIGNAME.get('insurDate')).setValue(dangerTime);
			Ext.getCmp(CONFIGNAME.get('insurType')).setValue(dangerType);
			Ext.getCmp(CONFIGNAME.get('insurQuantity')).setValue(dangerTime);
			Ext.getCmp(CONFIGNAME.get('deptId')).setValue(deptId);
		};
		var failureFn = function(json){
			if(Ext.isEmpty(json)){
				MessageUtil.showErrorMes(i18n('i18n.recompense.serviceMoreTime'));
				//DpUtil.showMessage(i18n('i18n.recompense.serviceMoreTime'));
			}else{
				MessageUtil.showErrorMes(json.message);
				me.getForm().reset();
				Ext.getCmp(CONFIGNAME.get('sendDate')).setValue(sendDate);
				Ext.getCmp(CONFIGNAME.get('insurDate')).setValue(dangerTime);
				Ext.getCmp(CONFIGNAME.get('insurType')).setValue(dangerType);
				Ext.getCmp(CONFIGNAME.get('insurQuantity')).setValue(dangerTime);
				Ext.getCmp(CONFIGNAME.get('deptId')).setValue(deptId);
			}
		};
		if(Ext.getCmp(CONFIGNAME.get('recompenseType')).isValid()){
			var recompenseType = Ext.getCmp(CONFIGNAME.get('recompenseType')).getValue();
			var param = {'recomSearchCondition':{'waybillNum':waybillNum,'recompenseType':recompenseType}};
			recompenseData.getWaybillByNum(param,successFn,failureFn);
		}else{
			MessageUtil.showMessage(i18n('i18n.recompense.pleaseSelectRecompenseType'));
		}
	},
	getItems:function(){
		var me = this;
		return [{xtype: 'basicfiledset',
		    title: i18n('i18n.recompense.dangerInfo'),
		    layout: {
		        type: 'table',
		        columns: 4
		    },
		    defaults:{
		    	labelSeparator:'',
		    	style:'margin-right:5px;',
		    	labelWidth:60,
		    	width:170
		    },
		    items:[{xtype: 'textfield',//凭证号
			    	id:CONFIGNAME.get('waybillNumber'),
			    	maxLength:15,
			    	name: CONFIGNAME.get('waybillNumber'),
			    	emptyText:i18n('i18n.recompense.orderNum'),
			        fieldLabel:i18n('i18n.recompense.orderNumOrErroeNum*'),
				    colspan: 1,
				    allowBlank:false,
			    	listeners:{
	                	blur:function(txt,eOpts ){
	                		var value  = txt.getValue();
	                		if(Ext.isEmpty(value)){
	                			MessageUtil.showMessage(i18n('i18n.recompense.pleaseInputWaybillNum'));
	                		}else{
	                			me.waybillNumBlur(value.trim());
	                		}
	                		
	                	}
	                }
				},{
					xtype: 'textfield',//货物名称
					id:CONFIGNAME.get('goodsName'),
					name: CONFIGNAME.get('goodsName'),
					colspan: 1,
			        fieldLabel:i18n('i18n.recompense.goodsName'),
			        cls:'readonly',
			        readOnly:true
				},{
					xtype: 'combo',//运输类型
					id:CONFIGNAME.get('transType'),
					name: CONFIGNAME.get('transType'),
					colspan: 1,
					fieldLabel:i18n('i18n.recompense.transType'),
					cls:'readonly',
			        readOnly:true,
			        displayField:'codeDesc',
			        valueField:'code',
			        queryMode:'local',
			        store:DpUtil.getStore(CONFIGNAME.get('transType'),null,['code','codeDesc'],DataDictionary.TRANSPORT_TYPE)
			        
			    },{
					xtype: 'textfield',//包装
					colspan: 1,
					id:CONFIGNAME.get('packaging'),
					name: CONFIGNAME.get('packaging'),
			        fieldLabel:i18n('i18n.recompense.packging'),
			        cls:'readonly',
			        readOnly:true
				  },{
					xtype: 'textfield',
					colspan: 1,
					id:CONFIGNAME.get('insured'),//保价人
					name: CONFIGNAME.get('insured'),
			        fieldLabel:i18n('i18n.recompense.insureMan'),
			        cls:'readonly',
			        readOnly:true
				  },{
					xtype: 'textfield',
					colspan: 1,
					id:CONFIGNAME.get('telephone'),//联系电话
					name: CONFIGNAME.get('telephone'),
			        fieldLabel:i18n('i18n.recompense.connectMobilePhone'),
			        cls:'readonly',
			        readOnly:true
				  },{
					xtype: 'textfield',
					colspan: 1,
					id:CONFIGNAME.get('startStation'),//始发站
					name: CONFIGNAME.get('startStation'),
			        fieldLabel:i18n('i18n.recompense.startStation'),
			        cls:'readonly',
			        readOnly:true
				  },{
					xtype: 'textfield',
					colspan: 1,
					id:CONFIGNAME.get('endStation'),
					name: CONFIGNAME.get('endStation'),//目的站
			        fieldLabel:i18n('i18n.recompense.endStation'),
			        cls:'readonly',
			        readOnly:true
				  },{
					xtype: 'datefield',
					colspan: 1,
					format:'Y-m-d',
					id:CONFIGNAME.get('sendDate'),
					name: CONFIGNAME.get('sendDate'),//发货日期
			        fieldLabel:i18n('i18n.recompense.sendGoodsTime'),
			        cls:'readonly',
			        readOnly:true
				  },{
					xtype: 'textfield',
					colspan: 1,
					id:CONFIGNAME.get('pwv'),
					name: CONFIGNAME.get('pwv'),//体件重
			        fieldLabel:i18n('i18n.recompense.numWeightVL'),
			        cls:'readonly',
			        readOnly:true
				  },{
					xtype: 'textfield',
					colspan: 1,
					id:CONFIGNAME.get('insurAmount'),
					name: CONFIGNAME.get('insurAmount'),//保价金额
			        fieldLabel:i18n('i18n.recompense.insureAmount'),
			        cls:'readonly',
			        readOnly:true
				  },{
					xtype: 'textfield',
					colspan: 1,
					id:CONFIGNAME.get('receiveDept'),
					name: CONFIGNAME.get('receiveDept'),//收货部门
			        fieldLabel:i18n('i18n.recompense.receiveDept'),
			        cls:'readonly',
			        readOnly:true
				  },{
					colspan: 1,
					readOnly:true,
					cls:'readonly',
					value:User.deptName,//报案部门
					name:CONFIGNAME.get('reportDeptName'),
			        fieldLabel:i18n('i18n.recompense.reportDept'),
			        xtype:'textfield'
				  },{
 					colspan: 1,
					value:User.empName,//报案人
					xtype:'textfield',
					name:CONFIGNAME.get('reportManName'),
			        fieldLabel:i18n('i18n.recompense.reportPerson'),
			        cls:'readonly',
			        readOnly:true
			    },{
					xtype: 'datefield',
					editable:false,
					readOnly:true,
					cls:'readonly',
					value:new Date(),
					format:'Y-m-d',
					id:CONFIGNAME.get('reportDate'),//报案时间
					name: CONFIGNAME.get('reportDate'),
			        fieldLabel:i18n('i18n.recompense.reportTime')
				},{
					xtype: 'datefield',
					editable:false,
					allowBlank:false,
					format:'Y-m-d',
					id:CONFIGNAME.get('insurDate'),
					name: CONFIGNAME.get('insurDate'),//出险时间
			        fieldLabel:i18n('i18n.recompense.dangerTime*'),
			        listeners:{
			        	select:function(th,value,eOpts ){
                		   if(value>Ext.getCmp(CONFIGNAME.get('reportDate')).getValue()){
                			   MessageUtil.showMessage(i18n('i18n.recompense.dangerDateEarlyReportDate'));
                			   th.reset();
                		   }
	                	}
		            }
				},{
					colspan: 1,
					id:CONFIGNAME.get('insurType'),
					name: CONFIGNAME.get('insurType'),//出险类型
			        fieldLabel:i18n('i18n.recompense.dangerType*'),
			        xtype:'combo',
		            store: DpUtil.getStore(CONFIGNAME.get('insurType'),null,['code','codeDesc'],DataDictionary.DANGER_TYPE),
		             queryMode: 'local',
		             editable:false,
		             allowBlank:false,
		             displayField: 'codeDesc',
		             valueField: 'code'
				  },{
	           	   xtype: 'numberfield',
					decimalPrecision:0,
					minValue:1,
					maxValue:9999999999,//出险数量
					allowBlank:false,
					id:CONFIGNAME.get('insurQuantity'),
					name: CONFIGNAME.get('insurQuantity'),
			        fieldLabel:i18n('i18n.recompense.dangerCount*')
	              },{
	            	colspan: 1,
	  				id:CONFIGNAME.get('deptId'),
	  				name: CONFIGNAME.get('deptId'),//所属大区
	  		        fieldLabel:i18n('i18n.recompense.belongArea*'),
	  		        xtype:'combo',
	  		        value:(Ext.isEmpty(Area))?'':Area.id,
	  	            store: DpUtil.getStore(CONFIGNAME.get('deptId'),null,['id','deptName'],(Ext.isEmpty(Area))?[]:[Area]),
	  	            queryMode: 'local',
	  	            forceSelection: true,
	  	            allowBlank:false,
	  	            valueField:'id',
	  	            displayField: 'deptName',
	  	            listeners:{
	  	            	select:function(th){
	  	            		Ext.getCmp('areaDeptName').setValue(th.getRawValue());
	  	            	}
			        } 
                 },{
					hidden:true,
					id:CONFIGNAME.get('arriveCustomerId'),
					xtype:'textfield',
					name: CONFIGNAME.get('arriveCustomerId')
			   },{
					hidden:true,
					id:CONFIGNAME.get('leaveCustomerId'),
					xtype:'textfield',
					name: CONFIGNAME.get('leaveCustomerId')
			  },{
					hidden:true,
					value:User.deptId,
					xtype:'textfield',
					name: CONFIGNAME.get('reportDept')
			  },{
					hidden:true,
					value:User.userId,
					xtype:'textfield',
					name:CONFIGNAME.get('reportMan')
			  },{
					hidden:true,
					xtype:'textfield',
					id:'areaDeptName',
					value:(Ext.isEmpty(Area))?'':Area.deptName,
					name:CONFIGNAME.get('deptName')
				  }]
	           
	    }];
	},
	initComponent:function(){
		var me = this;
	    me.items = me.getItems();
        var successFn = function(json){
        	Ext.data.StoreManager.lookup(CONFIGNAME.get('deptId')).removeAll();
        	Ext.data.StoreManager.lookup(CONFIGNAME.get('deptId')).add(json.deptList);
	    };
        var filureFn = function(json){
        	if(Ext.isEmpty(json)){
        		MessageUtil.showErrorMes(i18n('i18n.recompense.serviceMoreTime'));
				//DpUtil.showMessage(i18n('i18n.recompense.serviceMoreTime'));
			}else{
				MessageUtil.showErrorMes(json.message);
			}
        };
        //获取大区数据
	    recompenseData.searchAreas(null,successFn,filureFn);
		this.callParent();
	}
});
/**.
* <p>
* 理赔信息界面</br>
* </p>
* @author  张斌
* @retuens {RecompenseInfo}
* @时间    2012-04-11
*/
Ext.define('RecompenseInfo',{
	extend:'NoTitleFormPanel',
	title : i18n('i18n.recompense.recompenseInfo'),
	id:'recompenseInfoForm',
	height:315,
	style:'padding-left:10px;',
	layout: {
        type: 'table',
        columns: 3
    },
    defaults:{
    	labelSeparator:'',
    	style:'margin-right:5px;',
    	labelWidth:55,
    	width:230
    },
	getItems:function(){
		var me = this;
		return [
{
     	    		 xtype:'radiogroup',
    				 vertical:true,
    				 width:400,
    				 allowBlank:false,
    				 colspan: 3,
    				 id:CONFIGNAME.get('recompenseMethod'),
    				 name:CONFIGNAME.get('recompenseMethod'),
    				 blankText:i18n('i18n.recompense.pleaseSelectRecompenseMethod'),
    				 fieldLabel:i18n('i18n.recompense.recompenseMethod'),//理赔方式
    				 items:[{
    				 	 xtype:'radio',
    				     boxLabel:i18n('i18n.recompense.normalRecompense'),
    				     name:CONFIGNAME.get('recompenseMethod'),
    				     id:'normalRadio',
    				     checked:true,
    				     inputValue:'normal',
    				     listeners:{
    				    	 change:function(th,newValue,oldvalue){
    				    		 if(th.getValue()){
    				    			//@TODO 设置新增按钮和删除按钮可用
    				    			 Ext.getCmp('recompenseListAdd').setDisabled(false);
    			    				 Ext.getCmp('recompenseListUpdate').setDisabled(false);
    			    				 Ext.getCmp('recompenseListDelete').setDisabled(false);
    			    				 Ext.getCmp('tranceListAdd').setDisabled(false);
    			    				 Ext.getCmp('tranceListUpdate').setDisabled(false);
    			    				 Ext.getCmp('tranceListDelete').setDisabled(false);
                 		        }else{
                 		        	if(!Ext.isEmpty(Ext.getCmp('recompenseDetailsUI'))){
                 		        		if(Ext.getCmp('recompenseDetailsUI').record.recompenseMethod=='online'){
                 		        			Ext.getCmp('recompenseListGird').getStore().removeAll();
                 		        			Ext.getCmp('recompenseListTotalAmount').getTotalAmount();
	        								Ext.getCmp('goodsTranceGird').getStore().removeAll();
	        		       					Ext.getCmp('goodsTrancesListTotalAmount').getTotalAmount();
	        								Ext.getCmp('recompenseListAdd').setDisabled(true);
		       			    				 Ext.getCmp('recompenseListUpdate').setDisabled(true);
		       			    				 Ext.getCmp('recompenseListDelete').setDisabled(true);
		       			    				 Ext.getCmp('tranceListAdd').setDisabled(true);
		       			    				 Ext.getCmp('tranceListUpdate').setDisabled(true);
		       			    				 Ext.getCmp('tranceListDelete').setDisabled(true);
		       			    				 Ext.getCmp(CONFIGNAME.get('recompenseAmount')).setReadOnly(true);
		       			    				Ext.getCmp(CONFIGNAME.get('recompenseAmount')).addCls('readonly');
		       			    				Ext.getCmp(CONFIGNAME.get('claimParty')).setReadOnly(true);
                 		        			return;
                 		        		}
                 		        	}
                 		        	if(Ext.isEmpty(me.flag)){
                 		        		//setTimeout(function(){
                 		        			//如果是重置，不必再提问
                     		        		MessageUtil.showQuestionMes(i18n('i18n.recompense.fastRecompenseClear'),
            	        						function(e){
            	        							if (e == 'yes') {
            	        								Ext.getCmp('recompenseListGird').getStore().removeAll();
            	        								Ext.getCmp('recompenseListTotalAmount').getTotalAmount();
            	        								Ext.getCmp('goodsTranceGird').getStore().removeAll();
            	        		       					Ext.getCmp('goodsTrancesListTotalAmount').getTotalAmount();
            	        								Ext.getCmp('recompenseListAdd').setDisabled(true);
            		       			    				 Ext.getCmp('recompenseListUpdate').setDisabled(true);
            		       			    				 Ext.getCmp('recompenseListDelete').setDisabled(true);
            		       			    				 Ext.getCmp('tranceListAdd').setDisabled(true);
            		       			    				 Ext.getCmp('tranceListUpdate').setDisabled(true);
            		       			    				 Ext.getCmp('tranceListDelete').setDisabled(true);
            		       			    				 
            		       			    				 //
            		       			    				var recompenseAmount = Ext.getCmp(CONFIGNAME.get('recompenseAmount')).getValue();
            		       			    				var transType=Ext.getCmp(CONFIGNAME.get('transType')).getValue();
            		       			    				var fast_radio = Ext.getCmp('fastRecompenseMethod_id');
            		       			    				//当快递快速理赔是金额不能超过200
            		       			    				if(transType=='TRANS_EXPRESS' && recompenseAmount>200 && fast_radio.getValue()){
            		       			    					MessageUtil.showMessage(i18n('i18n.recompense.fastExpressAmount'));
            		       			    					fast_radio.setValue(false);
            		       			    				}else if(recompenseAmount > 1000 && fast_radio.getValue()){
            		       			    					MessageUtil.showMessage(i18n('i18n.recompense.more1000'));
            		       			    					fast_radio.setValue(false);
            		       			    				}
            	        							}else{
            	        								th.setValue(true);
            	        							}
            	        						});
                 		        	//	},1000);
                 		        	}
                 		        }
    				    	 }
    				     }
    				     },{
    				    	 xtype:'radio',
    					     boxLabel:i18n('i18n.recompense.fastRecompense'),
    					     name:CONFIGNAME.get('recompenseMethod'),
    					     id:'fastRecompenseMethod_id',
    					     inputValue:'fast',
    					     scope:me,
    					     handler:me.fastRecompense
    				     },{
    				    	 xtype:'radio',
    					     boxLabel:i18n('i18n.recompense.onlineRecompense'),
    					     name:CONFIGNAME.get('recompenseMethod'),
    					     id:'onlineRadio',
    					     inputValue:'online',
    					     disabled:true
    				     }]
       	    	   },{
       	    		   xtype:'radiogroup',
           	    	   allowBlank:false,
           	    	   
           	    	   colspan: 3,
           	    	   width:285,
           	    	   name:CONFIGNAME.get('claimParty'),
           	    	   id:CONFIGNAME.get('claimParty'),
           	    	   fieldLabel:i18n('i18n.recompense.recompenseMan*'),
           	    	   blankText:i18n('i18n.reconpense.pleaseSelectOneCust'),
       	    		   items:[{
       	    			   xtype:'radio',
       	    			   boxLabel:i18n('i18n.recompense.shipper'),//索赔方
       	    			   name:CONFIGNAME.get('claimParty'),
       	    			   inputValue:'1',
       	    			   listeners:{
    	                	   change:function(th,newValue,oldvalue){
    	                		   if(th.getValue()){
    	                				var custNumber = Ext.getCmp(CONFIGNAME.get('leaveCustomerId')).getValue();
    	                				if(Ext.isEmpty(custNumber)){
    	                					return;
    	                				}
    	                		        var successFn = function(json){
    	                		        	if(!Ext.isEmpty(json.member)){
    	                		        		Ext.getCmp(CONFIGNAME.get('degree')).setValue(DpUtil.changeDictionaryCodeToDescrip(json.member.degree,DataDictionary.MEMBER_GRADE));
        	                		        	Ext.getCmp(CONFIGNAME.get('custName')).setValue(json.member.custName);
        	                		        	Ext.getCmp(CONFIGNAME.get('custNumber')).setValue(json.member.custNumber);
        	                		        	Ext.getCmp(CONFIGNAME.get('custId')).setValue(json.member.id);
    	                		        	}else{
    	                		        		Ext.getCmp(CONFIGNAME.get('degree')).setValue(null);
        	                		        	Ext.getCmp(CONFIGNAME.get('custName')).setValue(null);
        	                		        	Ext.getCmp(CONFIGNAME.get('custNumber')).setValue(null);
        	                		        	Ext.getCmp(CONFIGNAME.get('custId')).setValue(null);
    	                		        	}
    	                		        };
    	                		        var failureFn = function(json){
    	                		        	if(Ext.isEmpty(json)){
    	                		        		MessageUtil.showErrorMes(i18n('i18n.recompense.serviceMoreTime'));
    	                						//DpUtil.showMessage(i18n('i18n.recompense.serviceMoreTime'));
    	                					}else{
    	                						MessageUtil.showErrorMes(json.message);
    	                					}
    	                		        };
    	                		        var param = {'custNumber':custNumber};
    	                		        recompenseData.getCustInfo(param,successFn,failureFn);
    	                		   }
    	                	   }
    		               }
       	    		   },{
       	    			   xtype:'radio',//收货方
       	    			   boxLabel:i18n('i18n.recompense.receivingParty'),
       	    			   name:CONFIGNAME.get('claimParty'),
       	    			   inputValue:'2',
       	    			   listeners:{
    	                	   change:function(th,newValue,oldvalue){
    	                		   if(th.getValue()){	  
    	                				var custNumber = Ext.getCmp(CONFIGNAME.get('arriveCustomerId')).getValue();
    	                				if(Ext.isEmpty(custNumber)){
    	                					return;
    	                				}
    	                		        var successFn = function(json){
    	                		        	if(!Ext.isEmpty(json.member)){
    	                		        		Ext.getCmp(CONFIGNAME.get('degree')).setValue(json.member.degree);
        	                		        	Ext.getCmp(CONFIGNAME.get('custName')).setValue(json.member.custName);
        	                		        	Ext.getCmp(CONFIGNAME.get('custNumber')).setValue(json.member.custNumber);
        	                		        	Ext.getCmp(CONFIGNAME.get('custId')).setValue(json.member.id);
    	                		        	}else{
    	                		        		Ext.getCmp(CONFIGNAME.get('degree')).setValue(null);
        	                		        	Ext.getCmp(CONFIGNAME.get('custName')).setValue(null);
        	                		        	Ext.getCmp(CONFIGNAME.get('custNumber')).setValue(null);
        	                		        	Ext.getCmp(CONFIGNAME.get('custId')).setValue(null);
    	                		        	}
    	                		        };
    	                		        var failureFn = function(json){
    	                		        	if(Ext.isEmpty(json)){
    	                		        		MessageUtil.showErrorMes(i18n('i18n.recompense.serviceMoreTime'));
    	                						//DpUtil.showMessage(i18n('i18n.recompense.serviceMoreTime'));
    	                					}else{
    	                						MessageUtil.showErrorMes(json.message);
    	                					}
    	                		        };
    	                		        var param = {'custNumber':custNumber};
    	                		        recompenseData.getCustInfo(param,successFn,failureFn);
    	                		   }
    	                	   }
    		               }
       	    		   }]
       	    	   },{
       	    		   fieldLabel:i18n('i18n.recompense.custNumber'),//客户编号
       	    		   id:CONFIGNAME.get('custNumber'),
       	    		   readOnly:true,
       	    		   cls:'readonly',
       	    		   xtype:'textfield',
       	    		   colspan: 1
       	    	   },{
       	    		   fieldLabel:i18n('i18n.recompense.custName'),//客户名称
       	    		   id:CONFIGNAME.get('custName'),
       	    		   readOnly:true,
       	    		   cls:'readonly',
       	    		   xtype:'textfield',
       	    		   colspan: 1
       	    	   },{
       	    		   fieldLabel:i18n('i18n.recompense.custLevel'),//客户等级
       	    		   id:CONFIGNAME.get('degree'),
       	    		   readOnly:true,
       	    		   cls:'readonly',
	      		        xtype:'combo',
	      	            store: DpUtil.getStore(CONFIGNAME.get('degree'),null,['code','codeDesc'],DataDictionary.MEMBER_GRADE),
	      	            queryMode: 'local',
	      	            //forceSelection: true,
	      	            valueField:'code',
	      	            displayField:'codeDesc',
	    	    		colspan: 1
       	    	   },{
       	    		   fieldLabel:i18n('i18n.recompense.claimAmount*'),//索赔金额
       	    		   id:CONFIGNAME.get('recompenseAmount'),
    	    	       name:CONFIGNAME.get('recompenseAmount'),
    				    xtype:'numberfield',
    				    decimalPrecision:2,
    				    colspan: 1,
    				    minValue:0,
    				    maxValue:9999999999.99,
    				    allowBlank:false,
    			    	blankText:i18n('i18n.recompense.pleaseInputClaimAmount'),
    			    	listeners:{
    			    	    change:function(th,newValue,oldValue){
    			    	    	me.changeRecompenseAmount(newValue,oldValue);
    			    	    }
    			    	}
    			   },{
    				   fieldLabel:i18n('i18n.recompense.recompensePerson*'),//索赔人
    				   id:CONFIGNAME.get('companyName'),
    	    	       name:CONFIGNAME.get('companyName'),
    				    allowBlank:false,
    				    maxLength:80,
    				    xtype:'textfield',
    				    colspan: 1,
    			    	blankText:i18n('i18n.recompense.pleaseInputRecompensePerson')
    			   },{
    				    fieldLabel:i18n('i18n.recompense.phone*'),//联系电话
    				    id:CONFIGNAME.get('companyPhone'),
    		    	    name:CONFIGNAME.get('companyPhone'),
    			    	allowBlank:false,
    			    	maxLength:50,
    			    	xtype:'textfield',
    			    	colspan: 1,
    			    	blankText:i18n('i18n.recompense.pleaseInputPhone')
    			   },{
    				   fieldLabel:i18n('i18n.recompense.fax'),//传真
    				   id:CONFIGNAME.get('companyFax'),
    	    	       name:CONFIGNAME.get('companyFax'),
    			    	colspan: 1,
    			    	maxLength:50,
    			    	xtype:'textfield'
    			   },{
       	    		   id:CONFIGNAME.get('custId'),
       	    		   hidden:true,
       	    		   xtype:'textfield'
       	    	   }
		        ];
	},
	initComponent:function()
	{
		var me = this;
		me.items=me.getItems();
		this.callParent();
	},
	//索赔金额校验事件
	changeRecompenseAmount:function(newValue,oldValue)
	{
		var recompenseType = Ext.getCmp('fastRecompenseMethod_id');
		
		var transType = "";
		if(Ext.isEmpty(Ext.getCmp(CONFIGNAME.get('transType')))) {
			//在线理赔查找方式
			transType=Ext.getCmp('processDangerInfoForm').getForm().findField('transType').getValue();
		}else{
			//理赔创建查找方式
			transType = Ext.getCmp(CONFIGNAME.get('transType')).getValue();
		}
		
		if(transType=='TRANS_EXPRESS'&& newValue>200 && recompenseType.getValue()){
			MessageUtil.showMessage(i18n('i18n.recompense.fastExpressAmount'));
			recompenseType.setValue(false);
		}else if(newValue > 1000 && recompenseType.getValue()){
			MessageUtil.showMessage(i18n('i18n.recompense.more1000'));
			recompenseType.setValue(false);
		}
	},
	//快速理赔校验事件
	fastRecompense:function(radio){
		var recompenseAmount = Ext.getCmp(CONFIGNAME.get('recompenseAmount')).getValue();
		var transType=Ext.getCmp(CONFIGNAME.get('transType')).getValue();
		var fast_radio = Ext.getCmp('fastRecompenseMethod_id');
		//当快递快速理赔是金额不能超过200
		if(transType=='TRANS_EXPRESS'&& recompenseAmount>200){
			MessageUtil.showMessage(i18n('i18n.recompense.fastExpressAmount'));
			fast_radio.setValue(false);
		}else if(recompenseAmount > 1000){
			MessageUtil.showMessage(i18n('i18n.recompense.more1000'));
			fast_radio.setValue(false);
		}
	}
});

/**.
* <p>
* 出险描述Grid</br>
* </p>
* @retuens {DangerInfoGrid}
* @author  张斌
* @时间    2012-04-11
*/
Ext.define('DangerInfoGrid',{
	extend:'PopupGridPanel',
	id:'dangerInfoGrid',
	autoScroll:true,
	height:265,
    sortableColumns:false,
    enableColumnHide:false,
    enableColumnMove:false,
    selModel: Ext.create('Ext.selection.CheckboxModel'),
    getColumns:function(){
    	var me = this;
    	return [
    		    new Ext.grid.RowNumberer(),
    			{ header: i18n("i18n.recompense.dangerType"),dataIndex:CONFIGNAME.get('insurType'),flex:1,
    				renderer : function(value){
     	            	return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.DANGER_TYPE);
     	            }
    			},
    			{ header: i18n("i18n.recompense.number"),dataIndex:CONFIGNAME.get('quality'),flex:1},
    			{ header: i18n("i18n.recompense.description"),dataIndex:CONFIGNAME.get('description'),flex:3,
    		      renderer: function(value,metaData,record,colIndex,store,view) {  
				       if(!Ext.isEmpty(value)){
				    	   metaData.tdAttr = 'data-qtip="'+value+'"';  
					       return value; 
				       } 
				} }
    		];
    },
	initComponent:function()
	{
		var me = this;
		me.columns = me.getColumns();
		me.store = DpUtil.getStore(me.id,'IssueDescriptionModel',null,[]);
		this.callParent();
	}
});

/**.
* <p>
* 索赔清单Grid</br>
* </p>
* @retuens {RecompenseListGird}
* @author  张斌
* @时间    2012-04-11
*/
Ext.define('RecompenseListGird',{
	extend:'PopupGridPanel',
	 //选择框
    selModel: Ext.create('Ext.selection.CheckboxModel'),
	id:'recompenseListGird',
	autoScroll:true,
	height:265,
    sortableColumns:false,
    enableColumnHide:false,
    enableColumnMove:false,
    getColumns:function(){
    	var me = this;
    	return [
    		    new Ext.grid.RowNumberer(),
    		    { header: i18n("i18n.recompense.goodsName"),dataIndex:CONFIGNAME.get('goodsName'),flex:1},
    			{ header: i18n("i18n.recompense.dangerType"),dataIndex:CONFIGNAME.get('insurType'),flex:1,
    				renderer : function(value){
     	            	return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.DANGER_TYPE);
     	        }}, 
    			{ header: i18n("i18n.recompense.description"),dataIndex:CONFIGNAME.get('description'),flex:1},
				{ header: i18n("i18n.recompense.number"),dataIndex:CONFIGNAME.get('number'),flex:1},
    			{ header: i18n("i18n.recompense.claimantAmount"),dataIndex:CONFIGNAME.get('amount'),flex:1}
    			
    		];
    },
	initComponent:function()
	{
		var me = this;
		me.columns = me.getColumns();
		me.store = DpUtil.getStore(me.id,'RecompenseList',null,[]);
		this.callParent();
	}
});
/**.
* <p>
* 货物托运清单Grid</br>
* </p>
* @retuens {RecompenseListGird}
* @author  张斌
* @时间    2012-04-11
*/
Ext.define('GoodsTranceGird',{
	extend:'PopupGridPanel',
	 //选择框
    selModel: Ext.create('Ext.selection.CheckboxModel'),
	id:'goodsTranceGird',
	autoScroll:true,
	height:265,
    sortableColumns:false,
    enableColumnHide:false,
    enableColumnMove:false,
    readOnly:null,
    getColumns:function(){
    	var me = this;
    	return [
    		    new Ext.grid.RowNumberer(),
    		    { header: i18n("i18n.recompense.goodsName"),dataIndex:CONFIGNAME.get('goodsName'),flex:2},
    			{ header: i18n("i18n.recompense.onlyPrice"),dataIndex:CONFIGNAME.get('price'),flex:1},
				{ header: i18n("i18n.recompense.number"),dataIndex:CONFIGNAME.get('quality'),flex:1},
    			{ header: i18n("i18n.recompense.realPrice"),dataIndex:CONFIGNAME.get('realPrice'),flex:1}
    			
    		];
    },
	initComponent:function()
	{
		var me = this;
		me.columns = me.getColumns();
		me.store = DpUtil.getStore(me.id,'RecompenseList',null,[]);
		this.callParent();
	}
});
/**.
 * 获取上传附件信息表
 * @author  张斌
 * @时间    2012-02-14
 */
Ext.define('Attachment',{
	extend:'PopupGridPanel',
	id:'attachmentGrid',
	 //选择框
    selModel: Ext.create('Ext.selection.CheckboxModel'),
    sortableColumns:false,
    enableColumnHide:false,
    autoScroll:true,
    height:265,
    enableColumnMove:false,
	initComponent:function()
	{
		var me = this;
		me.columns= [
          new Ext.grid.RowNumberer(),
          {header: i18n("i18n.recompense.attachName"),dataIndex:CONFIGNAME.get('attachName'),flex:1},
          {header: i18n("i18n.recompense.downLoad"),flex:1,xtype:'templatecolumn',
        	  tpl:'<a href="../common/downLoad.action?fileName={attachName}&inputPath={attachAddress}">'+i18n("i18n.recompense.downLoadThisAttach")+'</a>'}
        ];
		me.store = DpUtil.getStore(me.id,'AttachList',null,[]);
		this.callParent();
	}
});



/**.
 * <p>
 * 按照规范写出底角的按钮panel</br>
 * </p>
 * @returns {OperationButtonPanel}
 * @author  张斌
 * @时间    2012-04-06
 */
Ext.define('OperationButtonPanel',{
	extend:'PopButtonPanel',
	items:null,
	addFunction:null,
	deleteFucntion:null,
	updateFunction:null,
	cls:'recompense',
	weight:800,
	height:40,
	initComponent:function(){
		var me = this;
		me.items = me.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		var addButton = {};
		var deleteButton = {};
		var updateButton = {};
		var items = new Array();
		if(!Ext.isEmpty(me.addFunction)){
			addButton = {xtype:'button',
					text:i18n('i18n.recompense.add'),
					id:me.addId,
					disabled:me.buttonDisabled,
					// 货物托运清单默认不可用
					handler:function(){
						me.addFunction();
					}};
			items.push(addButton);
		}
		if(!Ext.isEmpty(me.deleteFucntion)){
			deleteButton =  {xtype:'button',
					text:i18n('i18n.recompense.delete'),
					id:me.deleteId,
					disabled:me.buttonDisabled,
					handler:function(){
						me.	deleteFucntion();		
								}
					};
			items.push(deleteButton);
		}
		if(!Ext.isEmpty(me.updateFunction)){
			updateButton =  {xtype:'button',
					text:i18n('i18n.recompense.update'),
					id:me.updateId,
					disabled:me.buttonDisabled,
					handler:function(){
						me.updateFunction();		
								}
					};
			items.push(updateButton);
		}
		items.push({
			xtype:'middlebuttonpanel' 
		  });
		return [{
			xtype:'popleftbuttonpanel',
			items:items
		}];
	}
});


/**.
* <p>
* 金额信息PANEL</br>
* </p>
* @retuens {AmountPanel}
* @author  张斌
* @时间    2012-04-11
*/
Ext.define('AmountPanel',{
	extend:'NoTitleFormPanel',
	id:'amountPanel',
	height:30,
	items:null,
	initComponent:function(){
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{
			height:30,
			layout:{
				type:'table',
				columns:3
			},
			defaultType:'textfield',
			defaults:{
				labelWidth:70,
				width:210
			},
			items:[{
   	    		   fieldLabel:i18n('i18n.recompense.normalRecompense'),//正常理赔金额
   	    		   id:CONFIGNAME.get('normalAmount'),
	    	       name:CONFIGNAME.get('normalAmount'),
				    xtype:'numberfield',
				    colspan: 1,
				    readOnly:true,
				    decimalPrecision:2,
				    cls:'readonly',
				    minValue:0,
				    maxValue:9999999999999999.99,
				    allowBlank:false,
				    listeners:{
				    	change:function(th){
				    		var totalAmount = th.getValue()+Ext.getCmp(CONFIGNAME.get('overpayAmount')).getValue();
				    		Ext.getCmp(CONFIGNAME.get('realAmount')).setValue(totalAmount);
				    	}
				    }
			   },{
   	    		   fieldLabel:i18n('i18n.recompense.moreAmount'),//多赔金额
   	    		   id:CONFIGNAME.get('overpayAmount'),
	    	       name:CONFIGNAME.get('overpayAmount'),
				    xtype:'numberfield',
				    readOnly:true,
				    cls:'readonly',
				    decimalPrecision:2,
				    colspan: 1,
				    minValue:0,
				    value:0,
				    maxValue:9999999999999999.99,
				    listeners:{
				    	change:function(th){
				    		var totalAmount = th.getValue()+Ext.getCmp(CONFIGNAME.get('normalAmount')).getValue();
				    		Ext.getCmp(CONFIGNAME.get('realAmount')).setValue(totalAmount);
				    	}
				    }
			   },{
   	    		   fieldLabel:i18n('i18n.recompense.realAmount'),//实际理赔金额
   	    		   id:CONFIGNAME.get('realAmount'),
	    	       name:CONFIGNAME.get('realAmount'),
				    xtype:'numberfield',
				    readOnly:true,
				    cls:'readonly',
				    decimalPrecision:2,
				    colspan: 1,
				    minValue:0,
				    maxValue:9999999999999999.99,
				    allowBlank:false,
				    listeners:{
				    	change:function(th){
				    		//实际金额得小于等于正常理赔与多赔金额之和
				    		var realAmount = th.getValue();
				    		if(Ext.isEmpty(realAmount)){
				    			return ;
				    		}
				    		var overpayAmount = Ext.getCmp(CONFIGNAME.get('overpayAmount')).getValue();
				    		if(Ext.isEmpty(overpayAmount)){
				    			return ;
				    		}
				    		var normalAmount = Ext.getCmp(CONFIGNAME.get('normalAmount')).getValue();
				    		if(Ext.isEmpty(normalAmount)){
				    			return ;
				    		}
				    		if(realAmount>(overpayAmount+normalAmount)){
				    			MessageUtil.showErrorMes(i18n('i18n.recompense.manager.realAmountSmallNormalAmountAndMoreAmount'));
				    			th.setValue(0);
				    		}
				    	}
				    }
			   }]		
		}];
	}
});

/**.
* <p>
* 入部门费用Grid</br>
* </p>
* @retuens {DeptChargeGird}
* @author  张斌
* @时间    2012-04-11
*/
Ext.define('DeptChargeGird',{
	extend:'PopupGridPanel',
	 //选择框
    selModel: Ext.create('Ext.selection.CheckboxModel'),
	id:'deptChargeGird',
    sortableColumns:false,
    autoScroll:true,
    enableColumnHide:false,
    enableColumnMove:false,
    listeners:{
    	/**.
    	* <p>
    	* 双击事件</br>
    	* </p>
    	* @author  张斌
    	* @时间    2012-06-15
    	*/
    	itemdblclick:function(){
    		//如果修改按钮可用，则双击进行修改
			if(!Ext.getCmp('deptChargeUpdate').disabled&&!Ext.getCmp('deptChargeUpdate').hidden){
				Ext.getCmp('deptChargeTitlePanel').updateDeptCharge();
			}
		}
    },
    getColumns:function(){
    	var me = this;
    	return [
    		    { header: i18n("i18n.recompense.dept"),dataIndex:CONFIGNAME.get('deptName'),flex:2},
    			{ header: i18n("i18n.recompense.amount"),dataIndex:CONFIGNAME.get('amount'),flex:1}
    		];
    },
	initComponent:function()
	{
		var me = this;
		me.columns = me.getColumns();
		me.store = DpUtil.getStore('deptChargeStore','DeptChargeModel',null,[]);
		this.callParent();
	}
});

/**.
* <p>
* 责任部门Grid</br>
* </p>
* @retuens {RecompenseListGird}
* @author  张斌
* @时间    2012-04-11
*/
Ext.define('ResponsibleDeptGird',{
	extend:'PopupGridPanel',
	 //选择框
    selModel: Ext.create('Ext.selection.CheckboxModel'),
	id:'responsibleDeptGird',
    sortableColumns:false,
    autoScroll:true,
    enableColumnHide:false,
    enableColumnMove:false,
    getColumns:function(){
    	var me = this;
    	return [
    		    { header: i18n("i18n.recompense.dept"),dataIndex:CONFIGNAME.get('deptName'),flex:2}
    			
    		];
    },
    listeners:{
    	/**.
    	* <p>
    	* 双击事件</br>
    	* </p>
    	* @author  张斌
    	* @时间    2012-06-15
    	*/
    	itemdblclick:function(){
    		//如果修改按钮可用，则双击进行修改
			if(!Ext.getCmp('responsibleDeptUpdate').disabled&&!Ext.getCmp('responsibleDeptUpdate').hidden){
				Ext.getCmp('responsibleDeptTitlePanel').updateResponsibleDept();
			}
		}
    },
	initComponent:function()
	{
		var me = this;
		me.columns = me.getColumns();
		me.store = DpUtil.getStore('responsibleDeptStore','ResponsibleDeptModel',null,[]);
		this.callParent();
	}
});


/**.
* <p>
* 奖罚明细Grid</br>
* </p>
* @retuens {AwardItemGird}
* @author  张斌
* @时间    2012-04-11
*/
Ext.define('AwardItemGird',{
	extend:'PopupGridPanel',
	 //选择框
    selModel:Ext.create('Ext.selection.CheckboxModel'),
	id:'awardItemGird',
    sortableColumns:false,
    autoScroll:true,
    enableColumnHide:false,
    enableColumnMove:false,
    listeners:{
    	/**.
    	* <p>
    	* 双击事件</br>
    	* </p>
    	* @author  张斌
    	* @时间    2012-06-15
    	*/
    	itemdblclick:function(){
    		//如果修改按钮可用，则双击进行修改
			if(!Ext.getCmp('awardItemUpdate').disabled&&!Ext.getCmp('awardItemUpdate').hidden){
				Ext.getCmp('awardItemTitlePanel').updateAwardItem();
			}
		}
    },
    getColumns:function(){
    	var me = this;
    	return [
    		    { header: i18n("i18n.recompense.awardType"),dataIndex:CONFIGNAME.get('awardType'),flex:1,
    				renderer : function(value){
     	            	return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.AWARD_TYPE);
     	        }},
    		    { header: i18n("i18n.recompense.dept"),dataIndex:CONFIGNAME.get('deptName'),flex:1},
    		    { header: i18n("i18n.recompense.amount"),dataIndex:CONFIGNAME.get('amount'),flex:1},
    		    { header: i18n("i18n.recompense.employee"),dataIndex:CONFIGNAME.get('userNumber'),flex:1},//TODO 
    		    { header: i18n("i18n.recompense.awardTargetType"),dataIndex:CONFIGNAME.get('awardTargetType'),flex:1,
    				renderer : function(value){
     	            	return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.AWARD_TARGET_TYPE);
     	        }}
    		];
    },
	initComponent:function()
	{
		var me = this;
		me.columns = me.getColumns();
		me.store = DpUtil.getStore('awardItemStore','AwardItemModel',null,[]);
		this.callParent();
	}
});

/**.
* <p>
* 消息提醒Grid</br>
* </p>
* @retuens {MessageReminderGird}
* @author  张斌
* @时间    2012-04-11
*/
Ext.define('MessageReminderGird',{
	extend:'PopupGridPanel',
	 //选择框
    selModel: Ext.create('Ext.selection.CheckboxModel'),
	id:'messageReminderGird',
    sortableColumns:false,
    autoScroll:true,
    enableColumnHide:false,
    enableColumnMove:false,
    getColumns:function(){
    	var me = this;
    	return [
    		    { header: i18n("i18n.recompense.userName"),dataIndex:CONFIGNAME.get('userName'),flex:1},
    		    { header: i18n("i18n.recompense.userNumber"),dataIndex:CONFIGNAME.get('userNumber'),flex:1},
    		    { header: i18n("i18n.recompense.connectMobilePhone"),dataIndex:CONFIGNAME.get('phoneNum'),flex:1}
    		];
    },
	initComponent:function()
	{
		var me = this;
		me.columns = me.getColumns();
		me.store = DpUtil.getStore('messageReminderStore','MessageReminderModel',null,[]);
		this.callParent();
	}
});

/**.
* <p>
* 追偿信息formpanel</br>
* </p>
* @retuens {RecalledComPanel}
* @author  张斌
* @时间    2012-04-11
*/
Ext.define('RecalledComPanel',{
	extend:'NoTitleFormPanel',
	items:null,
	id:'recalledComPanel',
	initComponent:function(){
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{
			layout:{
				type:'table',
				columns:3
			},
			defaultType:'textfield',
			defaults:{
				labelWidth:70,
				width:240
			},
	        items :[{
	        	name:CONFIGNAME.get('recoveryTime'),
	        	id:CONFIGNAME.get('recoveryTime'),//追偿时间
				editable:false,
				readOnly:true,
				cls:'readonly',
		        fieldLabel:i18n('i18n.recompense.recoveryTime'),
		        format:'Y-m-d',
		        xtype : 'datefield'
			},Ext.create('Ext.lookup.DeptLookup',
         		   {
				id:'recoveryDeptId',
  				name: CONFIGNAME.get('deptId'),//追偿部门
         	    maxLength:80,
         	   labelWidth:70,
				width:240,
         	     forceSelection: true,
         	     readOnly:true,
         	    colspan: 1,
				cls:'readonly',
				 editable:false,
		        fieldLabel:i18n('i18n.recompense.recoveryDept'),
         	   emptyText:''
          }),{
				fieldLabel:i18n('i18n.recompense.suspendedAmount'),
    		    id:CONFIGNAME.get('suspendedAmount'),
    	        name:CONFIGNAME.get('suspendedAmount'),//暂扣金额
			    xtype:'numberfield',
			    readOnly:true,
			    cls:'readonly',
			    decimalPrecision:2,
			    colspan: 1,
			    minValue:0,
			    maxValue:9999999999.99

			},{
				fieldLabel:i18n('i18n.recompense.compensateBackAmount'),
    		    id:CONFIGNAME.get('compensateBackAmount'),
    	        name:CONFIGNAME.get('compensateBackAmount'),
			    xtype:'numberfield',//赔回金额
			    decimalPrecision:2,
			    readOnly:true,
			    cls:'readonly',
			    colspan: 1,
			    minValue:0,
			    maxValue:9999999999.99
			},{
				name:CONFIGNAME.get('compensateBackTime'),
	        	id:CONFIGNAME.get('compensateBackTime'),//赔回日期
				editable:false,
				readOnly:true,
				cls:'readonly',
		        format:'Y-m-d',
		        xtype : 'datefield',
		        fieldLabel:i18n('i18n.recompense.compensateBackTime')
			},{
				fieldLabel:i18n('i18n.recompense.returnDeductions'),
    		    id:CONFIGNAME.get('returnDeductions'),
    	        name:CONFIGNAME.get('returnDeductions'),//返回暂扣款
			    xtype:'numberfield',
			    readOnly:true,
			    cls:'readonly',
			    decimalPrecision:0,
			    colspan: 1,
			    minValue:0,
			    maxValue:9999999999
			},{
				fieldLabel:i18n('i18n.recompense.assessmentReward'),
    		    id:CONFIGNAME.get('assessmentReward'),
    	        name:CONFIGNAME.get('assessmentReward'),//考核奖励
			    xtype:'numberfield',
			    readOnly:true,
			    cls:'readonly',
			    decimalPrecision:0,
			    colspan: 1,
			    minValue:0,
			    maxValue:9999999999
			},{
				id:'recalledComDeptName',
    	        name:CONFIGNAME.get('deptName'),
			    xtype:'textfield',
			    hidden:true,
			    colspan: 1
			}]	
		}];
	}
});


/**.
* <p>
* 跟进Grid</br>
* </p>
* @retuens {MessageGird}
* @author  张斌
* @时间    2012-04-12
*/
Ext.define('MessageGird',{
	extend:'PopupGridPanel',
	id:'messageGird',
    sortableColumns:false,
    autoScroll:true,
    enableColumnHide:false,
    enableColumnMove:false,
    getColumns:function(){
    	var me = this;
    	return [{
				xtype : 'rownumberer',
				width : 40,
				text : i18n('i18n.recompense.num')
				},
    		    { header: i18n("i18n.recompense.sendMessageMan"),dataIndex:CONFIGNAME.get('userName'),flex:1},
    		    { header: i18n("i18n.recompense.sendMessageTime"),dataIndex:CONFIGNAME.get('createTime'),flex:1,
    		    	renderer : function(value){
 	            	return new Date(value).format('yyyy-MM-dd hh:mm:ss');
 	            }},
    		    { header: i18n("i18n.recompense.sendMessageContent"),dataIndex:CONFIGNAME.get('content'),flex:2}		
    		];
    },
	initComponent:function()
	{
		var me = this;
		me.columns = me.getColumns();
		me.store = DpUtil.getStore('messageStore','MessageModel',null,[]);
		this.callParent();
		me.getView().on('render', function(view) {
		    view.tip = Ext.create('Ext.tip.ToolTip', {
		        target: view.el,
		        delegate: view.itemSelector,
		        trackMouse: true,
		        listeners: {
		            beforeshow: function updateTipBody(tip) {
		                tip.update(i18n('i18n.recompense.sendMessageContent')+':'
		                		+ view.getRecord(tip.triggerElement).get(CONFIGNAME.get('content')));
		            }
		        }
		    });
		});
	}
});



function showDetailWorkflow(url,params){
	 var width=1200,height=840;
	 var left=(screen.availWidth - width)/2;
     var top=(screen.availHeight - height)/2;
     if(Ext.isChrome){
     	window.open (url, "_blank", "width="+width+",height="+height+",top="+top+", left="+left+"");
     }else{
     	window.showModalDialog (url, params, 'dialogWidth:'+width+'px;dialogHeight:'+height+'px;dialogLeft='+left+'px";dialogTop='+top+'px');
     }
}

/**.
* <p>
* 工作流Grid</br>
* </p>
* @retuens {WorkFlowGird}
* @author  张斌
* @时间    2012-04-12
*/
Ext.define('WorkFlowGird',{
	extend:'PopupGridPanel',
	id:'workFlowGird',
    sortableColumns:false,
    enableColumnHide:false,
    autoScroll:true,
    enableColumnMove:false,
    getColumns:function(){
    	var me = this;
	    	return [{
				xtype : 'rownumberer',
				width : 40,
				text : i18n('i18n.recompense.num')
				},
    		    { header: i18n("i18n.recompense.workflowNum"),dataIndex:CONFIGNAME.get('workflowNum'),width:85,
    		    renderer:function(v,s,r){
    		    	return r.get('workflowNo')?("<a href=javascript:showDetailWorkflow('../workflow/showDetailWorkflow.action?processInstId="+r.get('workflowNumEnc')+"&processDefName="+(r.get('workflowType')==1?'com.deppon.bpms.module.crm.bpsdesign.claims.generalclaims':
    		    			r.get('workflowType')==2?'com.deppon.bpms.module.crm.bpsdesign.operate.multiReparation':'')+"');>"+r.get('workflowNo')+"</a>"):v;
    		    }},
    		    { header: i18n("i18n.recompense.type"),dataIndex:CONFIGNAME.get('workflowType'),flex:1,
    		    	renderer : function(value){
                          return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.RECOMPENSE_WORKFLOW_TYPE);
     	            }},
    		    { header: i18n("i18n.recompense.status"),dataIndex:CONFIGNAME.get('workflowStatus'),flex:2,
        		    	renderer : function(value){
                            return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.RECOMPENSE_WORKFLOW_STATUS);
       	            }},
    		    { header: i18n("i18n.recompense.auditPeople"),dataIndex:CONFIGNAME.get('auditPeople'),flex:2,
    		    	renderer : function(value){
    		    		if(!Ext.isEmpty(value)){
    		    			return value.empCode.empName;
    		    		}else{
    		    			return null;
    		    		}
     	            	
     	            }},
    		    { header: i18n("i18n.recompense.auditDate"),dataIndex:CONFIGNAME.get('auditDate'),width:90,
        		    	renderer : function(value){
        		    		return new Date(value).format('yyyy-MM-dd hh:mm:ss');
         	            }},
    		    { header: i18n("i18n.recompense.auditopinion"),dataIndex:CONFIGNAME.get('auditopinion'),flex:2,
    		  	  renderer: function(value,metaData,record,colIndex,store,view) {  
				       if(!Ext.isEmpty(value)){
				    	   metaData.tdAttr = 'data-qtip="'+value+'"';  
					       return value; 
				       } 
				}},
/*    		    { header: i18n("i18n.recompense.belongfinance"),dataIndex:CONFIGNAME.get('paymentBill'),width:85,
    		    	renderer : function(value){
    		    		if(!Ext.isEmpty(value)){
    		    			return value.belongfinance.financeName;
    		    		}else{
    		    			return null;
    		    		}
     	            	
     	            }},*///所属财务
    		    { header: i18n("i18n.recompense.commiter"),dataIndex:CONFIGNAME.get('commiter'),flex:2,
     	            	renderer : function(value){
 	            		if(!Ext.isEmpty(value)){
    		    			return value.empCode.empName;
    		    		}else{
    		    			return null;
    		    		}
 	            }},
    		    { header: i18n("i18n.recompense.commitDate"),dataIndex:CONFIGNAME.get('commitDate'),flex:2,
    		    	renderer : function(value){
    		    		return new Date(value).format('yyyy-MM-dd hh:mm:ss');
     	            }}/*,
    		    { header: i18n("i18n.recompense.bankAccount"),dataIndex:CONFIGNAME.get('paymentBill'),flex:2,
        		    	renderer : function(value){
        		    		if(!Ext.isEmpty(value)){
        		    			return value.bankAccount.bankName;
        		    		}else{
        		    			return null;
        		    		}
         	        }}*///开户银行
    		];
    },
	initComponent:function()
	{
		var me = this;
		me.columns = me.getColumns();
		me.store = DpUtil.getStore('workFlowStore','OAWorkFlowModel',null,[]);
		this.callParent();
	}
});







/**.
* <p>
* 理赔处理中索赔清单带标题</br>
* </p>
* @retuens {RecompenseListTitlePanel}
* @author  张斌
* @时间    2012-04-12
*/
Ext.define('RecompenseListTitlePanel',{
	id:'recompenseListTitlePanel',
	extend:'BasicVboxPanel',
	items:null,
    flex:1,
    layout:{
		type:'vbox',
		align:'stretch'
	},
	initComponent:function(){
		this.items = [{
			xtype:'titlepanel',
			items:[{xtype: 'displayfield',
					value: i18n('i18n.recompense.claimantList'),
					width:100}]
		},{
			xtype:'basicpanel',
			flex:1,
			items:[new ShowRecompenseListGird()]
		}];
		this.callParent();
	}
});
/**.
* <p>
* 用于显示的索赔清单Grid</br>
* </p>
* @retuens {ShowRecompenseListGird}
* @author  张斌
* @时间    2012-04-20
*/
Ext.define('ShowRecompenseListGird',{
	extend:'PopupGridPanel',
	 //选择框
    selModel: Ext.create('Ext.selection.CheckboxModel'),
	id:'showRecompenseListGird',
    sortableColumns:false,
    enableColumnHide:false,
    autoScroll:true,
    enableColumnMove:false,
    getColumns:function(){
    	var me = this;
    	return [
    		    { header: i18n("i18n.recompense.goodsName"),dataIndex:CONFIGNAME.get('goodsName'),flex:1},
    			{ header: i18n("i18n.recompense.dangerType"),dataIndex:CONFIGNAME.get('insurType'),flex:1,
    				renderer : function(value){
     	            	return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.DANGER_TYPE);
     	        }}, 
    			{ header: i18n("i18n.recompense.description"),dataIndex:CONFIGNAME.get('description'),flex:1},
				{ header: i18n("i18n.recompense.number"),dataIndex:CONFIGNAME.get('number'),flex:1},
    			{ header: i18n("i18n.recompense.amount"),dataIndex:CONFIGNAME.get('amount'),flex:1}
    			
    		];
    },
	initComponent:function()
	{
		var me = this;
		me.columns = me.getColumns();
		me.store = DpUtil.getStore(me.id,'RecompenseList',null,[]);
		this.callParent();
		me.getView().on('render', function(view) {
		    view.tip = Ext.create('Ext.tip.ToolTip', {
		        target: view.el,
		        delegate: view.itemSelector,
		        trackMouse: true,
		        listeners: {
		            beforeshow: function updateTipBody(tip) {
		                tip.update(i18n('i18n.recompense.description')+':'+ view.getRecord(tip.triggerElement).get(CONFIGNAME.get('description')));
		            }
		        }
		    });
		});
	}
});
/**.
* <p>
* 理赔处理中出险描述表格</br>
* </p>
* @retuens {DangerInfoTitlePanel}
* @author  张斌
* @时间    2012-04-12
*/
Ext.define('DangerInfoTitlePanel',{
	id:'dangerInfoTitlePanel',
	extend:'BasicVboxPanel',
	items:null,
    flex:1,
    layout:{
		type:'vbox',
		align:'stretch'
	},
	initComponent:function(){
		this.items = [{
			xtype:'titlepanel',
			items:[{xtype: 'displayfield',
					value: i18n('i18n.recompense.dangerDescription'),
					width:100}]
		},{
			xtype:'basicpanel',
			flex:1,
			items:[new ShowDangerInfoGrid()]
		}];
		this.callParent();
	}
});
/**.
* <p>
* 用于显示的出险描述Grid</br>
* </p>
* @retuens {ShowDangerInfoGrid}
* @author  张斌
* @时间    2012-04-20
*/
Ext.define('ShowDangerInfoGrid',{
	extend:'PopupGridPanel',
	id:'showDangerInfoGrid',
    sortableColumns:false,
    autoScroll:true,
    enableColumnHide:false,
    selModel: Ext.create('Ext.selection.CheckboxModel'),
    enableColumnMove:false,
    getColumns:function(){
    	var me = this;
    	return [
    			{ header: i18n("i18n.recompense.dangerType"),dataIndex:CONFIGNAME.get('insurType'),flex:1,
    				renderer : function(value){
     	            	return DpUtil.changeDictionaryCodeToDescrip(value,DataDictionary.DANGER_TYPE);
     	            }
    			},
    			{ header: i18n("i18n.recompense.number"),dataIndex:CONFIGNAME.get('quality'),flex:1},
    			{ header: i18n("i18n.recompense.description"),dataIndex:CONFIGNAME.get('description'),flex:3}
    		];
    },
	initComponent:function()
	{
		var me = this;
		me.columns = me.getColumns();
		me.store = DpUtil.getStore(me.id,'IssueDescriptionModel',null,[]);
		this.callParent();
		me.getView().on('render', function(view) {
		    view.tip = Ext.create('Ext.tip.ToolTip', {
		        target: view.el,
		        delegate: view.itemSelector,
		        trackMouse: true,
		        listeners: {
		            beforeshow: function updateTipBody(tip) {
		                tip.update(i18n('i18n.recompense.description')+': '+ view.getRecord(tip.triggerElement).get(CONFIGNAME.get('description')));
		            }
		        }
		    });
		});
	}
});


/**.
* <p>
* 理赔处理中货物托运清单带标题</br>
* </p>
* @retuens {GoodsTranceTitlePanel}
* @author  张斌
* @时间    2012-04-12
*/
Ext.define('GoodsTranceTitlePanel',{
	id:'goodsTranceTitlePanel',
	extend:'BasicVboxPanel',
	items:null,
    flex:1,
    layout:{
		type:'vbox',
		align:'stretch'
	},
	initComponent:function(){
		this.items = [{
			xtype:'titlepanel',
			items:[{xtype: 'displayfield',
					value: i18n('i18n.recompense.goodsConsignmentList'),
					width:100}]
		},{
			xtype:'basicpanel',
			flex:1,
			items:[new ShowGoodsTranceGird()]
		}];
		this.callParent();
	}
});
/**.
* <p>
* 货物托运清单Grid</br>
* </p>
* @retuens {ShowGoodsTranceGird}
* @author  张斌
* @时间    2012-04-20
*/
Ext.define('ShowGoodsTranceGird',{
	extend:'PopupGridPanel',
	 //选择框
    selModel: Ext.create('Ext.selection.CheckboxModel'),
	id:'showGoodsTranceGird',
    sortableColumns:false,
    enableColumnHide:false,
    autoScroll:true,
    enableColumnMove:false,
    readOnly:null,
    getColumns:function(){
    	var me = this;
    	return [
    		    { header: i18n("i18n.recompense.goodsName"),dataIndex:CONFIGNAME.get('goodsName'),flex:2},
    			{ header: i18n("i18n.recompense.onlyPrice"),dataIndex:CONFIGNAME.get('price'),flex:1},
				{ header: i18n("i18n.recompense.number"),dataIndex:CONFIGNAME.get('quality'),flex:1},
    			{ header: i18n("i18n.recompense.realPrice"),dataIndex:CONFIGNAME.get('realPrice'),flex:1}
    			
    		];
    },
	initComponent:function()
	{
		var me = this;
		me.columns = me.getColumns();
		me.store = DpUtil.getStore(me.id,'RecompenseList',null,[]);
		this.callParent();
	}
});
/**.
* <p>
* 理赔处理中附件带标题</br>
* </p>
* @retuens {AttachmentTitlePanel}
* @author  张斌
* @时间    2012-04-12
*/
Ext.define('AttachmentTitlePanel',{
	extend:'BasicVboxPanel',
	id:'attachmentTitlePanel',
	items:null,
    flex:1,
    layout:{
		type:'vbox',
		align:'stretch'
	},
	initComponent:function(){
		this.items = [{
			xtype:'titlepanel',
			items:[{xtype: 'displayfield',
					value: i18n('i18n.recompense.attachment'),
					width:100}]
		},{
			xtype:'basicpanel',
			flex:1,
			items:[new ShowAttachment()]
		}];
		this.callParent();
	}
});
/**.
 * 获取上传附件信息表
 * @author  张斌
 * @时间    2012-02-14
 */
Ext.define('ShowAttachment',{
	extend:'PopupGridPanel',
	id:'showAttachmentGrid',
	 //选择框
    selModel: Ext.create('Ext.selection.CheckboxModel'),
    sortableColumns:false,
    enableColumnHide:false,
    autoScroll:true,
    enableColumnMove:false,
	initComponent:function()
	{
		var me = this;
		me.columns= [
          {header: i18n("i18n.recompense.attachName"),dataIndex:"attachName",flex:1},
          {header: i18n("i18n.recompense.downLoad"),flex:1,xtype:'templatecolumn',
        	  tpl:'<a href="../common/downLoad.action?fileName={attachName}&inputPath={attachAddress}">'+i18n("i18n.recompense.downLoadThisAttach")+'</a>'}
        ];
		me.store = DpUtil.getStore(me.id,'AttachList',null,[]);
		this.callParent();
	}
});
/**.
* <p>
* 理赔处理中工作流带标题</br>
* </p>
* @retuens {WorkFlowTitlePanel}
* @author  张斌
* @时间    2012-04-12
*/
Ext.define('WorkFlowTitlePanel',{
	extend:'BasicPanel',
	items:null,
    flex:1,
    layout:{
		type:'vbox',
		align:'stretch'
	},
	initComponent:function(){
		this.items = [{
			xtype:'titlepanel',
			items:[{xtype: 'displayfield',
					value: i18n('i18n.recompense.workflow'),
					width:100}]
		},{
			xtype:'basicpanel',
			flex:1,
			items:[new WorkFlowGird()]
		}];
		this.callParent();
	}
});

/**.
* <p>
* 理赔处理中跟进信息带标题</br>
* </p>
* @retuens {MessageTitlePanel}
* @author  张斌
* @时间    2012-04-12
*/
Ext.define('MessageTitlePanel',{
	extend:'BasicPanel',
	items:null,
    flex:1,
    layout:{
		type:'vbox',
		align:'stretch'
	},
	initComponent:function(){
		var me = this;
		this.items = [{
			xtype:'titlepanel',
			items:[{xtype: 'displayfield',
					value: i18n('i18n.recompense.sendMessage'),
					width:100}]
		},{
			xtype:'basicpanel',
			flex:1,
			items:[new MessageGird()]
		}];
		this.callParent();
	}
});



/**.
* <p>
* 理赔处理中入部门费用带标题</br>
* </p>
* @retuens {DeptChargeTitlePanel}
* @author  张斌
* @时间    2012-04-12
*/
Ext.define('DeptChargeTitlePanel',{
	extend:'BasicVboxPanel',
	id:'deptChargeTitlePanel',
	items : null,
	flex:1,
	layout:{
		type:'vbox',
		align:'stretch'
	},
	initComponent : function() {
		this.items = this.getItems();
		this.callParent();
	},
	addDeptCharge:function(){
		 if(Ext.isEmpty(Ext.getCmp('deptChargeWindow'))){
				new DeptChargeWindow().show();
			}else{
				Ext.getCmp('deptChargeWindow').show();
			}
			Ext.getCmp('deptChargeWindow').flag = 'add';
			Ext.getCmp('deptChargeWindowInnerForm').getForm().reset();
	},
    deleteDeptCharge:function(){
    	var selections = Ext.getCmp('deptChargeGird').getSelectionModel( ).getSelection();
		if(selections.length==0){
			return;
		}
		MessageUtil.showQuestionMes(i18n('i18n.recompense.isDeleteThese'),
			function(e){
				if (e == 'yes') {
					Ext.getCmp('deptChargeGird').getStore().remove(selections);
				}
		});
	},
	updateDeptCharge:function(){
		var selections = Ext.getCmp('deptChargeGird').getSelectionModel().getSelection();
		if(selections.length!=1){
			MessageUtil.showMessage(i18n('i18n.recompense.pleaseSelectOneLine'));
			return ;
		}
		if(Ext.isEmpty(Ext.getCmp('deptChargeWindow'))){
			new DeptChargeWindow().show();
		}else{
			Ext.getCmp('deptChargeWindow').show();
		}
		Ext.getCmp('deptChargeWindow').flag = 'update';
		Ext.getCmp('deptChargeWindowInnerForm').getForm().loadRecord(selections[0]);
		Ext.getCmp('deptChargeDept').setValueId(selections[0].get('deptId'));
		Ext.getCmp('deptChargeDept').setValue(selections[0].get('deptName'));
	},
	getItems : function() {
		var me = this;
		return [
		{
			xtype:'toppanel',
			items:[
			{
				xtype:'titlepanel',
				flex:1,
				items:[{
					xtype:'displayfield',
					value:i18n('i18n.recompense.deptCharge')
				}]
			},{
				xtype:'btnpanel',
				items:[{
					xtype:'button',
					id:'deptChargeAdd',
					disabled:true,
					text:i18n('i18n.recompense.add'),
					handler:function(){
						me.	addDeptCharge();		
				    }
				},{
					xtype:'button',
					id:'deptChargeUpdate',
					disabled:true,
					text:i18n('i18n.recompense.update'),
					handler:function(){
						me.	updateDeptCharge();	
				    }
				},{
					xtype:'button',
					id:'deptChargeDelete',
					disabled:true,
					text:i18n('i18n.recompense.delete'),
					handler:function(){
						me.	deleteDeptCharge();		
				    }
				}]
			}]
		},{
			xtype:'basicpanel',
			flex:1,
			items:[new DeptChargeGird()]
		}
	  ];
	}
});
/**.
* <p>
* 理赔处理中奖罚明细带标题</br>
* </p>
* @retuens {AwardItemTitlePanel}
* @author  张斌
* @时间    2012-04-12
*/
Ext.define('AwardItemTitlePanel',{
	id:'awardItemTitlePanel',
	extend:'BasicVboxPanel',
	items : null,
	flex:1,
	layout:{
		type:'vbox',
		align:'stretch'
	},
	initComponent : function() {
		this.items = this.getItems();
		this.callParent();
	},
	addAwardItem:function(){
	    if(Ext.isEmpty(Ext.getCmp('awardItemWindow'))){
			new AwardItemWindow().show();
		}else{
			Ext.getCmp('awardItemWindow').show();
		}
		Ext.getCmp('awardItemWindow').flag = 'add';
		Ext.getCmp('awardItemWindowInnerForm').getForm().reset();
	},
    deleteAwardItem:function(){
    	var selections = Ext.getCmp('awardItemGird').getSelectionModel( ).getSelection();
		if(selections.length==0){
			return;
		}
		MessageUtil.showQuestionMes(i18n('i18n.recompense.isDeleteThese'),
			function(e){
				if (e == 'yes') {
					Ext.getCmp('awardItemGird').getStore().remove(selections);
				}
		});
	},
	updateAwardItem:function(){
		var selections = Ext.getCmp('awardItemGird').getSelectionModel().getSelection();
		if(selections.length!=1){
			MessageUtil.showMessage(i18n('i18n.recompense.pleaseSelectOneLine'));
			return ;
		}
		if(Ext.isEmpty(Ext.getCmp('awardItemWindow'))){
			new AwardItemWindow().show();
		}else{
			Ext.getCmp('awardItemWindow').show();
		}
		Ext.getCmp('awardItemWindow').flag = 'update';
		Ext.getCmp('awardItemWindowInnerForm').getForm().loadRecord(selections[0]);
		Ext.getCmp('awardItemWindowDept').setValueId(selections[0].get('deptId'));
		if(selections[0].get(CONFIGNAME.get('awardTargetType'))=='DEPT'){
			Ext.getCmp('awardItemWindowDept').setValue(selections[0].get('deptName'));
			Ext.getCmp('awardItemWindowDept').setValueId(selections[0].get('deptId'));
			Ext.getCmp('awardItemWindowDept').setReadOnly(false);
			Ext.getCmp('awardItemWindowDept').removeCls('readonly');
		}else if(selections[0].get(CONFIGNAME.get('awardTargetType'))=='EMP'){//EMP
			Ext.getCmp('awardItemUser').setValue(selections[0].get('empName'));
			Ext.getCmp('awardItemUser').setValueId(selections[0].get(CONFIGNAME.get('userId')));
			Ext.getCmp('awardItemUser').empCode = selections[0].get(CONFIGNAME.get('userNumber'));
			Ext.getCmp('awardItemUser').setReadOnly(false);
			Ext.getCmp('awardItemUser').removeCls('readonly');
			
		}
	},
	getItems : function() {
		var me = this;
		return [
		{
			xtype:'toppanel',
			items:[
			{
				xtype:'titlepanel',
				flex:1,
				items:[{
					xtype:'displayfield',
					value:i18n('i18n.recompense.awardInfo')
				}]
			},{
				xtype:'btnpanel',
				items:[{
					xtype:'button',
					id:'awardItemAdd',
					disabled:true,
					text:i18n('i18n.recompense.add'),
					handler:function(){
						me.	addAwardItem();		
				    }
					},{
						xtype:'button',
						id:'awardItemUpdate',
						disabled:true,
						text:i18n('i18n.recompense.update'),
						handler:function(){
							me.	updateAwardItem();	
					    }
					},{
						xtype:'button',
						id:'awardItemDelete',
						disabled:true,
						text:i18n('i18n.recompense.delete'),
						handler:function(){
							me.	deleteAwardItem();		
					}
				}]
			}]
		},{
			xtype:'basicpanel',
			flex:1,
			items:[new AwardItemGird()]
		}
	  ];
	}
});


/**.
* <p>
* 理赔处理中责任部门带标题</br>
* </p>
* @retuens {ResponsibleDeptTitlePanel}
* @author  张斌
* @时间    2012-04-12
*/
Ext.define('ResponsibleDeptTitlePanel',{
	extend:'BasicVboxPanel',
	id:'responsibleDeptTitlePanel',
	items : null,
	flex:1,
	layout:{
		type:'vbox',
		align:'stretch'
	},
	initComponent : function() {
		this.items = this.getItems();
		this.callParent();
	},
	addResponsibleDept:function(){
		if(Ext.isEmpty(Ext.getCmp('responsibleDeptWindow'))){
			new ResponsibleDeptWindow().show();
		}else{
			Ext.getCmp('responsibleDeptWindow').show();
		}
		Ext.getCmp('responsibleDeptWindow').flag = 'add';
		Ext.getCmp('responsibleDeptWindowInnerForm').getForm().reset();
	},
    deleteResponsibleDept:function(){
    	var selections = Ext.getCmp('responsibleDeptGird').getSelectionModel( ).getSelection();
		if(selections.length==0){
			return;
		}
		MessageUtil.showQuestionMes(i18n('i18n.recompense.isDeleteThese'),
			function(e){
				if (e == 'yes') {
					Ext.getCmp('responsibleDeptGird').getStore().remove(selections);
				}
		});
	},
	updateResponsibleDept:function(){
		var selections = Ext.getCmp('responsibleDeptGird').getSelectionModel().getSelection();
		if(selections.length!=1){
			MessageUtil.showMessage(i18n('i18n.recompense.pleaseSelectOneLine'));
			return ;
		}
		if(Ext.isEmpty(Ext.getCmp('responsibleDeptWindow'))){
			new ResponsibleDeptWindow().show();
		}else{
			Ext.getCmp('responsibleDeptWindow').show();
		}
		Ext.getCmp('responsibleDeptWindow').flag = 'update';
		Ext.getCmp('responsibleDeptWindowInnerForm').getForm().loadRecord(selections[0]);
		Ext.getCmp('responsibleDeptDept').setValueId(selections[0].get('deptId'));
	},
	getItems : function() {
		var me = this;
		return [
		{
			xtype:'toppanel',
			items:[
			{
				xtype:'titlepanel',
				flex:1,
				items:[{
					xtype:'displayfield',
					value:i18n('i18n.recompense.responsibleDept')
				}]
			},{
				xtype:'btnpanel',
				items:[{
					xtype:'button',
					id:'responsibleDeptAdd',
					disabled:true,
					text:i18n('i18n.recompense.add'),
					handler:function(){
						me.	addResponsibleDept();		
				    }
				},{
					xtype:'button',
					id:'responsibleDeptUpdate',
					disabled:true,
					text:i18n('i18n.recompense.update'),
					handler:function(){
						me.	updateResponsibleDept();	
				    }
				},{
					xtype:'button',
					id:'responsibleDeptDelete',
					disabled:true,
					text:i18n('i18n.recompense.delete'),
					handler:function(){
						me.	deleteResponsibleDept();		
				    }
				}]
			}]
		},{
			xtype:'basicpanel',
			flex:1,
			items:[new ResponsibleDeptGird()]
		}
	  ];
	}
});

/**.
* <p>
* 理赔处理中消息系统带标题</br>
* </p>
* @retuens {MessageReminderTitlePanel}
* @author  张斌
* @时间    2012-04-12
*/
Ext.define('MessageReminderTitlePanel',{
	id:'messageReminderTitlePanel',
	extend:'BasicVboxPanel',
	items : null,
	flex:1,
	layout:{
		type:'vbox',
		align:'stretch'
	},
	initComponent : function() {
		this.items = this.getItems();
		this.callParent();
	},
	addMessageReminder:function(){
		if(Ext.isEmpty(Ext.getCmp('messageReminderEmployeeGirdWindow'))){
			new EmployeeLookUpWindow({
				'ischeckbox':true,
				'id':'messageReminderEmployeeGirdWindow',
				'fbar':[{
					xtype:'button',
					text:i18n('i18n.recompense.determine'),
					handler:function(){
						var selections = Ext.getCmp('EmployeeLookUpGridId').getSelectionModel().getSelection();
						for(var i = 0;i<selections.length;i++){
							var messageReminderModel  = new MessageReminderModel();
							messageReminderModel.set(CONFIGNAME.get('userNumber'),selections[i].get('empCode'));
							messageReminderModel.set(CONFIGNAME.get('phoneNum'),selections[i].get('mobilePhone'));
							messageReminderModel.set(CONFIGNAME.get('deptName'),selections[i].get('deptName'));
							messageReminderModel.set(CONFIGNAME.get('deptId'),selections[i].get('deptId'));
							messageReminderModel.set(CONFIGNAME.get('userName'),selections[i].get('empName'));
							Ext.getCmp('messageReminderGird').getStore().add(messageReminderModel);
						}
					}
				},{
					xtype:'button',
					text:i18n('i18n.recompense.cancel'),
					handler:function(){
						Ext.getCmp('messageReminderEmployeeGirdWindow').destroy( );
					}
				}]
			}).show();
		}else{
			Ext.getCmp('messageReminderEmployeeGirdWindow').show();
		}
		
	},
    deleteMessageReminder:function(){
    	var selections = Ext.getCmp('messageReminderGird').getSelectionModel( ).getSelection();
		if(selections.length==0){
			return;
		}
		MessageUtil.showQuestionMes(i18n('i18n.recompense.isDeleteThese'),
			function(e){
				if (e == 'yes') {
					Ext.getCmp('messageReminderGird').getStore().remove(selections);
				}
		});
	},
	getItems : function() {
		var me = this;
		return [
		{
			xtype:'toppanel',
			items:[
			{
				xtype:'titlepanel',
				flex:1,
				items:[{
					xtype:'displayfield',
					value:i18n('i18n.recompense.sendMessageNotice')
				}]
			},{
				xtype:'btnpanel',
				items:[{
					xtype:'button',
					id:'messageReminderAdd',
					disabled:true,
					text:i18n('i18n.recompense.add'),
					handler:function(){
						me.	addMessageReminder();		
				    }
				},{
					xtype:'button',
					id:'messageReminderDelete',
					disabled:true,
					text:i18n('i18n.recompense.delete'),
					handler:function(){
						me.deleteMessageReminder();		
				    }
				}]
			}]
		},{
			xtype:'basicpanel',
			flex:1,
			items:[new MessageReminderGird()]
		}
	  ];
	}
});



/**.
* <p>
* 跟进弹窗跟进信息带标题</br>
* </p>
* @retuens {MessageSendTitlePanel}
* @author  张斌
* @时间    2012-04-12
*/
Ext.define('MessageSendTitlePanel',{
	extend:'BasicPanel',
	items:null,
    flex:1,
    layout:{
		type:'vbox',
		align:'stretch'
	},
	initComponent:function(){
		var me = this;
		this.items = [{
			xtype:'titlepanel',
			items:[{xtype: 'displayfield',
					value: i18n('i18n.recompense.sendMessage'),
					width:100,height:40}]
		},{
			xtype:'basicpanel',
			flex:1,
			items:[new MessageSendGird()]
		}];
		this.callParent();
	}
});
/**.
 * <p>
 * 跟进信息录入PANEL</br>
 * </p>
 * @author  张斌
 * @时间    2012-04-13
 */
Ext.define('MessageAreaPanel', {
	extend : 'BasicFormPanel',
	id:'messageAreaPanel',
	width:400,
	height:100,
	items : null,
	layout:{
		type:'vbox',
		align:'stretch'
	},
	initComponent : function() {
		this.items = this.getItems();
		this.callParent();
	},
	getItems : function() {
		var me = this;
		return [{
			xtype:'titlepanel',
			items:[{xtype: 'displayfield',
					value: i18n('i18n.recompense.sendMessage'),
					width:100}]
		},{
			xtype:'basicpanel',
			flex:1,
			items:[{xtype : 'textareafield',
				    id:'messageContent',
				   allowBlank : false}]
		}
		];
	}
});
/**.
* <p>
* 跟进弹窗Grid</br>
* </p>
* @retuens {MessageSendGird}
* @author  张斌
* @时间    2012-04-12
*/
Ext.define('MessageSendGird',{
	extend:'PopupGridPanel',
	id:'messageSendGird',
	width:400,
	height:200,
    sortableColumns:false,
    autoScroll:true,
    enableColumnHide:false,
    enableColumnMove:false,
    getColumns:function(){
    	var me = this;
    	return [
    		    { header: i18n("i18n.recompense.sendMessageMan"),dataIndex:CONFIGNAME.get('userName'),flex:1},
    		    { header: i18n("i18n.recompense.sendMessageTime"),dataIndex:CONFIGNAME.get('createTime'),flex:1,
    		    	renderer : function(value){
 	            	return new Date(value).format('yyyy-MM-dd hh:mm:ss');
 	            }},
    		    { header: i18n("i18n.recompense.sendMessageContent"),dataIndex:CONFIGNAME.get('content'),flex:2}		
    		];
    },
	initComponent:function()
	{
		var me = this;
		me.columns = me.getColumns();
		me.store = DpUtil.getStore('messageSendStore','MessageModel',null,[]);
		this.callParent();
		me.getView().on('render', function(view) {
		    view.tip = Ext.create('Ext.tip.ToolTip', {
		        target: view.el,
		        delegate: view.itemSelector,
		        trackMouse: true,
		        listeners: {
		            beforeshow: function updateTipBody(tip) {
		                tip.update(i18n('i18n.recompense.sendMessageContent')+':'+ view.getRecord(tip.triggerElement).get(CONFIGNAME.get('content')));
		            }
		        }
		    });
		});
	}
});
/**.
 * <p>
 * 弹出跟进信息窗口</br>
 * </p>
 * @author  张斌
 * @时间    2012-04-13
 */
Ext.define('MessageSendWindow',{
	extend:'PopWindow',
	title:i18n('i18n.recompense.messageInfoSend'),
	id:'messageSendWindow',
	fbar:null,
	items:null,
	closeAction:'hide',
	layout: 'fit',
	initComponent:function(){
		var me = this;
		me.fbar = me.getFbar();
		me.items = me.getItems();
		this.callParent();
	},
	listeners:{
		beforehide:function(){
			Ext.getCmp('messageContent').reset();
		},
		beforeshow:function(){
			Ext.getCmp('messageSendGird').getStore().removeAll();
			var messageSendList = Ext.getCmp('recompenseDetailsUI').record.messageList;
			if(!Ext.isEmpty(messageSendList)){
				Ext.getCmp('messageSendGird').getStore().add(messageSendList);
			}
		}
	},
	getItems:function(){
		var me = this;
		return [new MessageAreaPanel(),new MessageSendGird()];
	},
	/**.
	 * <p>
	 * 保存跟进信息</br>
	 * </p>
	 * @author  张斌
	 * @时间    2012-04-09
	 */
	saveMessage:function(){
		var me= this;
		if(Ext.getCmp('messageAreaPanel').getForm().isValid()){
			var messageModel = new MessageModel();
			var content = Ext.getCmp('messageContent').getValue();
			messageModel.set(CONFIGNAME.get('content'),content);
			messageModel.set(CONFIGNAME.get('recompenseId'),Ext.getCmp('recompenseDetailsUI').record.id);
			var succseeFn = function(json){
				Ext.getCmp('saveMessageSendInfo').setDisabled(false);
				Ext.getCmp('messageContent').reset();
				MessageUtil.showInfoMes(json.message);
				Ext.getCmp('messageSendGird').getStore().removeAll();
				Ext.getCmp('messageGird').getStore().removeAll();
				Ext.getCmp('messageGird').getStore().add(json.messageList);
				Ext.getCmp('messageSendGird').getStore().add(json.messageList);
				Ext.getCmp('recompenseDetailsUI').record.messageList = json.messageList;
			}
            var failureFn = function(json){
            	Ext.getCmp('saveMessageSendInfo').setDisabled(false);
            	if(Ext.isEmpty(json)){
            		MessageUtil.showErrorMes(i18n('i18n.recompense.serviceMoreTime'));
    				//DpUtil.showMessage(i18n('i18n.recompense.serviceMoreTime'));
    			}else{
    				MessageUtil.showErrorMes(json.message);
    			}
			}
            var deptId = Ext.getCmp('recompenseDetailsUI').record.deptId;
            var reportDept = Ext.getCmp('recompenseDetailsUI').record.reportDept;
            var params = {'msg':messageModel.data,'recompenseView':{'app':{'deptId':deptId,'reportDept':reportDept}}};
            Ext.getCmp('saveMessageSendInfo').setDisabled(true);
            recompenseData.sendMessage(params,succseeFn,failureFn);
		}
	},
	getFbar:function(){
		var me = this;
		return [{
			xtype:'button',
			id:'saveMessageSendInfo',
			text:i18n('i18n.recompense.save'),
			handler:function(){
					me.saveMessage();
			}
		},{
			xtype:'button',
			text:i18n('i18n.recompense.cancel'),
			handler:function(){
					me.hide();
			}
		}];
	}
});



/**.
 * <p>
 * 入部门费用弹窗WINDOW用于修改和添加</br>
 * </p>
 * @author  张斌
 * @时间    2012-04-13
 */
Ext.define('DeptChargeWindow',{
	extend:'PopWindow',
	title:i18n('i18n.recompense.deptChargeAddUpdateWindow'),
	id:'deptChargeWindow',
	fbar:null,
	items:null,
	closeAction:'hide',
	layout: 'fit',
	initComponent:function(){
		var me = this;
		me.fbar = me.getFbar();
		me.items = me.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [new DeptChargeWindowInnerForm()];
	},
	/**.
	 * <p>
	 * 添加一条入部门费用信息</br>
	 * </p>
	 * @author  张斌
	 * @时间    2012-04-13
	 */
	addDeptCharge:function(){
		if(Ext.getCmp('deptChargeWindowInnerForm').getForm().isValid( )){
			var deptChargeModel = new DeptChargeModel();
			Ext.getCmp('deptChargeWindowInnerForm').getForm().updateRecord(deptChargeModel);
			deptChargeModel.set(CONFIGNAME.get('deptId'),Ext.getCmp('deptChargeDept').getValueId());
			Ext.getCmp('deptChargeGird').getStore().add(deptChargeModel);
			Ext.getCmp('deptChargeWindowInnerForm').getForm().reset();
		}
	},
	/**.
	 * <p>
	 * 修改一条入部门费用信息</br>
	 * </p>
	 * @author  张斌
	 * @时间    2012-04-13
	 */
	updateDeptCharge:function(){
		var me = this;
		if(Ext.getCmp('deptChargeWindowInnerForm').getForm().isValid( )){
			var deptChargeModel = new DeptChargeModel();
			Ext.getCmp('deptChargeWindowInnerForm').getForm().updateRecord(deptChargeModel);
			deptChargeModel.set(CONFIGNAME.get('deptId'),Ext.getCmp('deptChargeDept').getValueId());
			var selection = Ext.getCmp('deptChargeGird').getSelectionModel().getSelection()[0];
			selection.set(CONFIGNAME.get('amount'),deptChargeModel.get(CONFIGNAME.get('amount')));
			selection.set(CONFIGNAME.get('deptName'),deptChargeModel.get(CONFIGNAME.get('deptName')));
			selection.set(CONFIGNAME.get('deptId'),deptChargeModel.get(CONFIGNAME.get('deptId')));
			me.hide();
		}
	},
	getFbar:function(){
		var me = this;
		return [{
			xtype:'button',
			text:i18n('i18n.recompense.determine'),
			handler:function(){
				if(me.flag=='add'){
					me.addDeptCharge();
				}else if(me.flag=='update'){
					me.updateDeptCharge();
				}
			}
			
		},{
			xtype:'button',
			text:i18n('i18n.recompense.cancel'),
			handler:function(){
				me.hide();
			}
		}];
	}
});

Ext.define('DeptChargeWindowInnerForm',{
	extend:'NoTitleFormPanel',
	id:'deptChargeWindowInnerForm',
	items:null,
	initComponent:function(){
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{
			layout:{
				type:'table',
				columns:2
			},
			defaults:{
				labelWidth:60,
				width:200
			},
			items:[
               Ext.create('Ext.lookup.DeptLookup',
            		   {
            	   id:'deptChargeDept',
            	   maxLength:80,
            	   allowBlank:false,
            	   editable:false,
            	   emptyText:'',
            	   fieldLabel :i18n('i18n.recompense.dept'),
            	   name : CONFIGNAME.get('deptName'),//部门
            	   labelWidth:60,
   				   width:200
             })  ,{
				fieldLabel:i18n('i18n.recompense.amount'),//入部门费用
				xtype: 'numberfield',
				allowBlank:false,
				name:CONFIGNAME.get('amount'),
				decimalPrecision:2,
				maxValue:99999999999999.99,
				minValue: 0.01
    		}]		
		}];
	}
});



/**.
 * <p>
 * 责任部门弹窗WINDOW用于修改和添加</br>
 * </p>
 * @author  张斌
 * @时间    2012-04-13
 */
Ext.define('ResponsibleDeptWindow',{
	extend:'PopWindow',
	title:i18n('i18n.recompense.responsibleDeptWindow'),
	id:'responsibleDeptWindow',
	fbar:null,
	items:null,
	closeAction:'hide',
	layout: 'fit',
	initComponent:function(){
		var me = this;
		me.fbar = me.getFbar();
		me.items = me.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [new ResponsibleDeptWindowInnerForm()];
	},
	/**.
	 * <p>
	 * 添加一条责任部门信息</br>
	 * </p>
	 * @author  张斌
	 * @时间    2012-04-13
	 */
	addResponsibleDept:function(){
		if(Ext.getCmp('responsibleDeptWindowInnerForm').getForm().isValid( )){
			var responsibleDeptModel = new ResponsibleDeptModel();
			responsibleDeptModel.set(CONFIGNAME.get('deptId'),Ext.getCmp('responsibleDeptDept').getValueId());
			Ext.getCmp('responsibleDeptWindowInnerForm').getForm().updateRecord(responsibleDeptModel);
			Ext.getCmp('responsibleDeptGird').getStore().add(responsibleDeptModel);
			Ext.getCmp('responsibleDeptWindowInnerForm').getForm().reset();
		}
	},
	/**.
	 * <p>
	 * 修改一条责任部门信息</br>
	 * </p>
	 * @author  张斌
	 * @时间    2012-04-13
	 */
	updateResponsibleDept:function(){
		var me = this;
		if(Ext.getCmp('responsibleDeptWindowInnerForm').getForm().isValid( )){
			var selection = Ext.getCmp('responsibleDeptGird').getSelectionModel().getSelection()[0];
			selection.set(CONFIGNAME.get('deptId'),Ext.getCmp('responsibleDeptDept').getValueId());
			selection.set(CONFIGNAME.get('deptName'),Ext.getCmp('responsibleDeptDept').getValue());
			me.hide();
		}
	},
	getFbar:function(){
		var me = this;
		return [{
			xtype:'button',
			text:i18n('i18n.recompense.determine'),
			handler:function(){
				if(me.flag=='add'){
					me.addResponsibleDept();
				}else if(me.flag=='update'){
					me.updateResponsibleDept();
				}
			}
		},{
			xtype:'button',
			text:i18n('i18n.recompense.cancel'),
			handler:function(){
				me.hide();
			}
		}];
	}
});
/**.
 * <p>
 * 责任部门WINDOW用于修改和添加</br>
 * </p>
 * @author  张斌
 * @时间    2012-04-13
 */
Ext.define('ResponsibleDeptWindowInnerForm',{
	extend:'NoTitleFormPanel',
	id:'responsibleDeptWindowInnerForm',
	items:null,
	initComponent:function(){
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{
			layout:{
				type:'table',
				columns:2
			},
			defaults:{
				labelWidth:60,
				width:200
			},
			items:[
				Ext.create('Ext.lookup.DeptLookup',
						   {
					   id :'responsibleDeptDept',
					   maxLength:80,
					   allowBlank:false,
					   editable:false,
					   emptyText:'',
					   fieldLabel :i18n('i18n.recompense.dept'),
					   name : CONFIGNAME.get('deptName'),//部门
					   labelWidth:60,
					   width:200
				})  ]		
		}];
	}
});

/**.
 * <p>
 * 奖罚明细用弹窗WINDOW用于修改和添加</br>
 * </p>
 * @author  张斌
 * @时间    2012-04-13
 */
Ext.define('AwardItemWindow',{
	extend:'PopWindow',
	title:i18n('i18n.recompense.awardItemWindow'),
	id:'awardItemWindow',
	fbar:null,
	items:null,
	closeAction:'hide',
	layout: 'fit',
	listeners:{
		beforehide:function(){
			Ext.getCmp('awardItemWindowDept').setReadOnly(true);
			Ext.getCmp('awardItemWindowDept').addCls('readonly');
			Ext.getCmp('awardItemUser').setReadOnly(true);
			Ext.getCmp('awardItemUser').addCls('readonly');
		}
	},
	initComponent:function(){
		var me = this;
		me.fbar = me.getFbar();
		me.items = me.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [new AwardItemWindowInnerForm()];
	},
	/**.
	 * <p>
	 * 添加一条奖罚明细信息</br>
	 * </p>
	 * @author  张斌
	 * @时间    2012-04-13
	 */
	addAwardItem:function(){
		if(Ext.getCmp('awardItemWindowInnerForm').getForm().isValid( )){
			var awardItemModel = new AwardItemModel();
			Ext.getCmp('awardItemWindowInnerForm').getForm().updateRecord(awardItemModel);
			awardItemModel.set('deptName',Ext.getCmp('awardItemWindowDept').getValue());
			awardItemModel.set('deptId',Ext.getCmp('awardItemWindowDept').getValueId());
			if(!Ext.isEmpty(Ext.getCmp('awardItemUser').empCode)){
				awardItemModel.set(CONFIGNAME.get('userNumber'),Ext.getCmp('awardItemUser').empCode);
			}
			awardItemModel.set(CONFIGNAME.get('userId'),Ext.getCmp('awardItemUser').getValueId());
			awardItemModel.set('empName',Ext.getCmp('awardItemUser').getValue());
			Ext.getCmp('awardItemGird').getStore().add(awardItemModel);
			Ext.getCmp('awardItemWindowInnerForm').getForm().reset();
		}
	},
	/**.
	 * <p>
	 * 修改一条奖罚明细信息</br>
	 * </p>
	 * @author  张斌
	 * @时间    2012-04-13
	 */
	updateAwardItem:function(){
		var me = this;
		if(Ext.getCmp('awardItemWindowInnerForm').getForm().isValid( )){
			var awardItemModel = new AwardItemModel();
			Ext.getCmp('awardItemWindowInnerForm').getForm().updateRecord(awardItemModel);
			var selection = Ext.getCmp('awardItemGird').getSelectionModel().getSelection()[0];
			selection.set(CONFIGNAME.get('awardType'),awardItemModel.get(CONFIGNAME.get('awardType')));
			selection.set(CONFIGNAME.get('awardTargetType'),awardItemModel.get(CONFIGNAME.get('awardTargetType')));
			selection.set(CONFIGNAME.get('amount'),awardItemModel.get(CONFIGNAME.get('amount')));
			selection.set(CONFIGNAME.get('deptName'),Ext.getCmp('awardItemWindowDept').getValue());
			selection.set(CONFIGNAME.get('deptId'),Ext.getCmp('awardItemWindowDept').getValueId());
			selection.set(CONFIGNAME.get('userNumber'),Ext.getCmp('awardItemUser').empCode);
			selection.set(CONFIGNAME.get('userId'),Ext.getCmp('awardItemUser').getValueId());
			selection.set('empName',Ext.getCmp('awardItemUser').getValue());
			me.hide();
		}
	},
	getFbar:function(){
		var me = this;
		return [{
			xtype:'button',
			text:i18n('i18n.recompense.determine'),
			handler:function(){
				if(me.flag=='add'){
					me.addAwardItem();
				}else if(me.flag=='update'){
					me.updateAwardItem();
				}
			}
			
		},{
			xtype:'button',
			text:i18n('i18n.recompense.cancel'),
			handler:function(){
				me.hide();
			}
		}];
	}
});
/**.
 * <p>
 * 奖罚明细弹窗WINDOW</br>
 * </p>
 * @author  张斌
 * @时间    2012-04-13
 */
Ext.define('AwardItemWindowInnerForm',{
	extend:'NoTitleFormPanel',
	id:'awardItemWindowInnerForm',
	items:null,
	initComponent:function(){
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{
			layout:{
				type:'table',
				columns:2
			},
			defaults:{
				labelWidth:60,
				width:200
			},
			items:[{
    			fieldLabel:i18n('i18n.recompense.awardType'),//奖罚类型
    			xtype: 'combo',
				maxLength:80,
				queryMode:'local',
				id:CONFIGNAME.get('awardType'),
				name:CONFIGNAME.get('awardType'),
				displayField:'codeDesc',
				valueField:'code',
				store:DpUtil.getStore('awardItemAwardType',null,['code','codeDesc'],DataDictionary.AWARD_TYPE),
				allowBlank:false,
				forceSelection: true,
				editable:false
    		},{
    			fieldLabel:i18n('i18n.recompense.awardTargetType'),//奖罚对象类型
    			xtype: 'combo',
				maxLength:80,
				queryMode:'local',
				id:CONFIGNAME.get('awardTargetType'),
				name:CONFIGNAME.get('awardTargetType'),
				displayField:'codeDesc',
				valueField:'code',
				store:DpUtil.getStore(CONFIGNAME.get('awardTargetType'),null,['code','codeDesc'],DataDictionary.AWARD_TARGET_TYPE),
				allowBlank:false,
				forceSelection: true,
				editable:false,
				listeners:{
					change:function(th){
						if(th.getValue()=='DEPT'){
							Ext.getCmp('awardItemWindowDept').setReadOnly(false);
							Ext.getCmp('awardItemWindowDept').removeCls('readonly');
							Ext.getCmp('awardItemUser').setReadOnly(true);
							Ext.getCmp('awardItemUser').addCls('readonly');
							Ext.getCmp('awardItemUser').empCode=null;
							Ext.getCmp('awardItemUser').reset();
							Ext.getCmp('awardItemWindowDept').allowBlank = false;
							Ext.getCmp('awardItemUser').allowBlank = true;
							Ext.getCmp('awardItemUser').doComponentLayout();
						}else{
							Ext.getCmp('awardItemUser').setReadOnly(false);
							Ext.getCmp('awardItemUser').removeCls('readonly');
							Ext.getCmp('awardItemUser').allowBlank = false;
							Ext.getCmp('awardItemWindowDept').allowBlank = true;
							Ext.getCmp('awardItemWindowDept').setReadOnly(true);
							Ext.getCmp('awardItemWindowDept').addCls('readonly');
							Ext.getCmp('awardItemWindowDept').reset();
						}
					}
				}
    		},
    		Ext.create('Ext.lookup.EmployeeLookup',{
            	id:'awardItemUser',
            	name:'empName',
            	fieldLabel: i18n('i18n.recompense.employee'),
            	emptyText:'',
            	width:200,
            	maxLength:80,
            	readOnly:true,
            	cls:'readonly',
             	labelWidth:60,
            	editable:false,
            	listeners:{
			        select:function(th,record){
			        	th.empCode = record.get('empCode');
			        }
				}
            }),
    		Ext.create('Ext.lookup.DeptLookup',
         		   {
         	   id:'awardItemWindowDept',
         	   maxLength:80,
         	   editable:false,
         	   emptyText:'',
         	   readOnly:true,
         	   cls:'readonly',
         	   fieldLabel :i18n('i18n.recompense.dept'),
         	   name : CONFIGNAME.get('deptName'),//部门
         	   labelWidth:60,
			   width:200
          }),{
				xtype: 'numberfield',
				fieldLabel:i18n('i18n.recompense.amount'),//奖罚金额
				name:CONFIGNAME.get('amount'),
				colspan:1,
				decimalPrecision:2,
				allowBlank:false,
				minValue:0.01,
				maxValue:99999999999999.99
			}]		
		}];
	}
});













/**.
 * <p>
 * 多赔弹窗WINDOW</br>
 * </p>
 * @author  张斌
 * @时间    2012-04-13
 */
Ext.define('PaymentBillWindow',{
	extend:'PopWindow',
	title:i18n('i18n.recompense.paymentBill'),
	id:'paymentBillWindow',
	fbar:null,
	items:null,
	closeAction:'hide',
	layout: 'fit',
	listeners:{
		beforeshow:function(){
			//先清除原有数据
			Ext.getCmp('paymentBillWindowInnerForm').getForm().reset();
			//从grid选择的一条数据中获取数据初始化多陪WINDOW
			var selections  = Ext.getCmp('recompenseSearchGridPanel').getSelectionModel().getSelection();
			if(selections.length!=1){
				MessageUtil.showMessage(i18n('i18n.recompense.pleaseSelectOneLine'));
				return;
			}
			var recompenseId = selections[0].get('id');
			var normalAmount = selections[0].get(CONFIGNAME.get('normalAmount'));
			var wayBillNum = selections[0].get(CONFIGNAME.get('waybill')).waybillNumber;
			Ext.getCmp('payBillWaybillNumber').setValue(wayBillNum);
			Ext.getCmp('paymentBillAmount').setValue(normalAmount);
			Ext.getCmp('paymentBillWindowInnerForm').initBus();
		}
	},
	initComponent:function(){
		var me = this;
		me.fbar = me.getFbar();
		me.items = me.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [new PaymentBillWindowInnerForm()];
	},
	/**.
	 * <p>
	 * 保存多陪数据</br>
	 * </p>
	 * @author  张斌
	 * @时间    2012-04-13
	 */
	savePaymentBill:function(saveBtn){
		var me = this;
		if(Ext.getCmp('paymentBillWindowInnerForm').getForm().isValid()){
			var overPayModel = new OverPayModel();
			Ext.getCmp('paymentBillWindowInnerForm').getForm().updateRecord(overPayModel);
			//设置所属事业部
			overPayModel.set(CONFIGNAME.get('overpayBu'),{'id':Ext.getCmp('overPayBuDeptId').getValue(),'standardCode':Ext.getCmp(CONFIGNAME.get('overpayBu')).getValue(),'deptName':Ext.getCmp(CONFIGNAME.get('overpayBu')).getRawValue()});
			//设置会计部门overPayBuDeptId
			overPayModel.set(CONFIGNAME.get('overpayAccountDept'),{'id':Ext.getCmp(CONFIGNAME.get('overpayAccountDept')).getUserId(),'empCode':{'empCode':Ext.getCmp(CONFIGNAME.get('overpayAccountDept')).empCode
				,'empName':Ext.getCmp(CONFIGNAME.get('overpayAccountDept')).getValue()}
				});
			saveBtn.setDisabled(true);
			var successFn = function(json){
				saveBtn.setDisabled(false);
				MessageUtil.showInfoMes(json.message);
				DpUtil.refreshRecompenseInfo();
				me.hide();
			};
			var failureFn = function(json){
				saveBtn.setDisabled(false);
				if(Ext.isEmpty(json)){
					MessageUtil.showErrorMes(i18n('i18n.recompense.serviceMoreTime'));
					//DpUtil.showMessage(i18n('i18n.recompense.serviceMoreTime'));
				}else{
					MessageUtil.showErrorMes(json.message);
				}
			};
			var id = Ext.getCmp('recompenseDetailsUI').record.id;
		    var workflowId = Ext.getCmp('recompenseDetailsUI').record.workflowId;
		    var recompenseMethod = Ext.getCmp('recompenseDetailsUI').record.recompenseMethod;
		    var payBillWaybillNumber = Ext.getCmp('recompenseDetailsUI').record.waybill.waybillNumber
		    var param  = {'actionId':me.actionId,'recompenseView':{'app':{'id':id,'workflowId':workflowId,
		    	'recompenseMethod':recompenseMethod,'overpay':overPayModel.data,'waybill':{'waybillNumber':payBillWaybillNumber}
		    	}}};
			recompenseData.performAction(param,successFn,failureFn);
		}
	},
	getFbar:function(){
		var me = this;
		return [{
			xtype:'button',
			text:i18n('i18n.recompense.determine'),
			handler:function(){
				var btn = this;
				/**
				 * 表单提交优化
				 */
				MessageUtil.showQuestionMes('提交多赔将产生审批工作流，是否确定提交?'/*i18n('i18n.recompense.confirmProcess')*/,function(e){
					if (e == 'yes') {
						me.savePaymentBill(btn);
					}else{
						return;
					}
				});
			}
			
		},{
			xtype:'button',
			text:i18n('i18n.recompense.cancel'),
			handler:function(){
				me.hide();
			}
			
		}];
	}
});
/**.
 * <p>
 * 付款弹出界面FROM</br>
 * </p>
 * @author  张斌
 * @时间    2012-06-05
 */
Ext.define('PaymentBillWindowInnerForm',{
	extend:'NoTitleFormPanel',
	id:'paymentBillWindowInnerForm',
	items:null,
	initComponent:function(){
		var me = this;	
		this.items = this.getItems();
		var successFn = function(json){
        	Ext.data.StoreManager.lookup(CONFIGNAME.get('overpayBu')).add(json.bizDeptList);
        	me.initBus();
	    };
        var failureFn = function(json){
        	if(Ext.isEmpty(json)){
        		MessageUtil.showErrorMes(i18n('i18n.recompense.serviceMoreTime'));
				//DpUtil.showMessage(i18n('i18n.recompense.serviceMoreTime'));
			}else{
				MessageUtil.showErrorMes(json.message);
			}
        };
        //获取大区数据
	    recompenseData.queryAllBusiness(null,successFn,failureFn);
		this.callParent();
	},
	/**.
	 * <p>
	 * 设置默认事业部</br>
	 * </p>
	 * @author  张斌
	 * @时间    2012-06-05
	 */
	initBus:function(){
		var me = this;
		var successFn = function(json){
			if(!Ext.isEmpty(json.dept)){
				Ext.getCmp(CONFIGNAME.get('overpayBu')).setValue(json.dept.standardCode);
				Ext.getCmp('overPayBuDeptId').setValue(json.dept.id);
			}
		};
		var failureFn = function(json){
			if(Ext.isEmpty(json)){
				MessageUtil.showErrorMes(i18n('i18n.recompense.serviceMoreTime'));
				//DpUtil.showMessage(i18n('i18n.recompense.serviceMoreTime'));
			}else{
				MessageUtil.showErrorMes(json.message);
			}
		};
		//获取大区数据
	    recompenseData.initBus(null,successFn,failureFn);
	},
	getItems:function(){
		var me = this;
		return [{
			layout:{
				type:'table',
				columns:3
			},
			defaults:{
				labelWidth:60,
				width:200
			},
			items:[{xtype: 'textfield',
		    	maxLength:15,
		    	readOnly:true,
		    	cls:'readonly',
		    	id:'payBillWaybillNumber',//多赔凭证号
		    	name:CONFIGNAME.get('waybillNumber'),
		        fieldLabel:i18n('i18n.recompense.orderNumOrErroeNum'),
			    colspan: 1
			},{
				colspan: 1,
				readOnly:true,
				cls:'readonly',
				value:User.deptName,
				fieldLabel:i18n('i18n.recompense.applyForDepartment'),
		        xtype:'textfield'
			},{
				colspan: 1,
				value:User.empName,
				xtype:'textfield',
				cls:'readonly',
		        fieldLabel:i18n('i18n.recompense.applyForPerson'),
		        readOnly:true
		    },{
				xtype: 'numberfield',
				fieldLabel:i18n('i18n.recompense.recompenseAmount'),
				id:'paymentBillAmount',//理赔金额
				decimalPrecision:2,
				minValue:0,
				maxValue:9999999999999999.99,
				name:CONFIGNAME.get('normalAmount'),
				colspan:1,
				cls:'readonly',
				readOnly:true
			},{
				xtype: 'numberfield',
				id:'paymentBillAmountOverpay',
				fieldLabel:i18n('i18n.recompense.moreAmount'),//多赔金额
				name:CONFIGNAME.get('overpayAmount'),
				colspan:1,
				decimalPrecision:2,
				allowBlank:false,
				minValue:0,
				maxValue:9999999999999999.99,
				listeners:{
					change:function(th){
						var totalAmount = th.getValue()+Ext.getCmp('paymentBillAmount').getValue();
						Ext.getCmp('paymentBillTotalAmount').setValue(totalAmount);
					}
				}
			},{
				xtype: 'textfield',
				fieldLabel:i18n('i18n.recompense.totolCountAmount'),//总金额
				id:'paymentBillTotalAmount',
				name:CONFIGNAME.get('totalAmount'),
				cls:'readonly',
				readOnly:true
			},{
    		   xtype:'radiogroup',
   	    	   allowBlank:false,
   	    	   colspan: 1,
   	    	   id:CONFIGNAME.get('recoverYszk'),
   	    	   defaults:{
   	    		 name:CONFIGNAME.get('recoverYszk')
   	    	   },
   	    	   fieldLabel:i18n('i18n.recompense.accountsReceivableWhetherRecycling'),
    		   items:[{
    			   xtype:'radio',
    			   boxLabel:i18n('i18n.recompense.yes'),
    			   inputValue:true
    		   },{
    			   xtype:'radio',
    			   boxLabel:i18n('i18n.recompense.no'),
    			   inputValue:false
    		   }]
    	   },
    	   Ext.create('Ext.lookup.EmployeeLookup',{//职员
           	id:CONFIGNAME.get('overpayAccountDept'),
           	name:CONFIGNAME.get('overpayAccountDept'),
           	fieldLabel:i18n('i18n.recompense.sectorAccounting'),
           	emptyText:'',
           	maxLength:80,
           	width:200,
            labelWidth:60,
           	editable:false,
           	listeners:{
			        select:function(th,record){
			        	th.empCode = record.get('empCode');
			        }
				}
           }),{
				fieldLabel:i18n('i18n.recompense.businessesOwnedByTheDepartment'),
   			    xtype: 'combo',
				maxLength:80,
				queryMode:'local',
				id:CONFIGNAME.get('overpayBu'),
				name:CONFIGNAME.get('overpayBu'),
				valueField:'standardCode',
				displayField:CONFIGNAME.get('deptName'),
				store:DpUtil.getStore(CONFIGNAME.get('overpayBu'),null,['id','standardCode','deptName'],[]),
				//forceSelection: true,
				allowBlank:false,
				editable:false,
				listeners:{
					select:function(th,records){
	            		Ext.getCmp('overPayBuDeptId').setValue(records[0].get('id'));
	            	}
				}
			},{
				fieldLabel:i18n('i18n.recompense.reasonForApplication'),
   			    xtype: 'textareafield',
   			    colspan: 3,
   			    name:CONFIGNAME.get('overpayReason'),
   			    allowBlank:false,
				maxLength:1200,
				width:400
			},{
   			    xtype: 'textareafield',
   			    hidden:true,
   			    id:'overPayRecompenseId',
   			    name:CONFIGNAME.get('recompenseId')
			},{
   			    xtype: 'textareafield',
   			    hidden:true,
   			    id:'overPayBuDeptId'
			}]		
		}];
	}
});

/**
 * 账户信息
 */
Ext.define('BankInfo',{
	extend:'NoTitleFormPanel',
	title : i18n('i18n.recompense.bankInfo.accountInfo'),
	id:'bankInfoForm',
	height:315,
	style:'padding-left:10px;',
	layout: {
        type: 'table',
        columns: 3
    },
    defaults:{
    	labelSeparator:'',
    	style:'margin-right:5px;',
    	labelWidth:55,
    	readOnly:true,
        cls:'readonly'
    },
	getItems:function(){
		var me = this;
		return  [
					{
    				   fieldLabel:i18n('i18n.recompense.bankInfo.openName'),//开户名
    	    	       name:'openName',
    			       xtype:'textfield'
    			   },{
    				   fieldLabel:i18n('i18n.recompense.bankInfo.account'),//银行账号
    	    	       name:'account',
    	    	       colspan:2,
    			       xtype:'textfield'
    			   },{
    				   fieldLabel:i18n('i18n.recompense.bankInfo.bankName'),//开户银行
    	    	       name:'bankName',
    	    	       colspan:3,
    			       xtype:'textfield'
    			   },{
    				   fieldLabel:i18n('i18n.recompense.bankInfo.branchName'),//开户支行
    	    	       name:'branchName',
    	    	       width:430,
    	    	       colspan:3,
    			       xtype:'textfield'
    			   }
		        ];
	},
	initComponent:function()
	{
		var me = this;
		me.items=me.getItems();
		this.callParent();
	}
});

