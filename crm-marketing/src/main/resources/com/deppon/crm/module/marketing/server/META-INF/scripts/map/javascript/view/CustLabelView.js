Ext.define('CustLabelInfoForm',{
	extend:'BasicFormPanel',
	layout : {
		type : 'table',columns : 7//3
	},
	border:true,
	custData:null,
	status:null,
	custType:null,
	channelType:null,
	items:null,
	cls:'labelPanel',
//	hight:80,
//	width:680,
	title:i18n('i18n.visualMarket.customerLabel'),
    initComponent:function(){
	   this.items = this.getItems();
	   //加载数据 区分 是修改 新增 还是查看详情
	   this.callParent();
	   if(this.custType == 'PC_CUSTOMER') {
		   this.title = i18n('i18n.visualMarket.potentialCustLabel');
	   }
	   if(this.custType == 'SC_CUSTOMER') {
		   this.title = i18n('i18n.visualMarket.SCustLabel');
	   }
	   if(this.custType == 'MEMBER') {
		   this.title = i18n('i18n.visualMarket.custLabelNormal');
	   }
//	   loadData(this.status,this.custType,this.custData);
    },
    getItems:function() {
    	var me = this;
    	return [
    	       {	xtype:'panel',
					id:'btnList',
					border:false,
					layout:'column',
					channelType:me.channelType,
					columnWidth:0.15,
					margin:'5 8',
					hidden:'view'==me.status?true:false,
					colspan:7,
					items:[{
						fieldLabel:i18n('i18n.visualMarket.waitForSelectCustLabel'),
						xtype : 'displayfield',
						margin:'5 0 5 0',
						labelWidth:55
					}]
    	       },{
    	    	   xtype:'panel',border:false,
    	    	   colspan:7,
    	    	   hidden:'view'==me.status?true:false,
    	    	   html:'<div style="height:1px; width:650px; background-color:#4F4F4F;"></div>'
    		},{
    			xtype:'panel',
				id:'CustLabelList',
				border:false,
				layout:'column',
				columnWidth:0.15,
				margin:'10 8',
				colspan:7,
				items:[{
					fieldLabel:i18n('i18n.visualMarket.selectedCustLabel'),
					xtype : 'displayfield',
					margin:'5 0 1 0',
					labelWidth:55
				}]
				
    	}];
    }
});


/**
 * 有个小XX的 panel
 */
Ext.define('LabelPanel',{
	extend:'BasicPanel',
	labelText:null,
	labelId:null,
	cls:'checkedlabel',
	margin:'5 0 5 0',
	height:22,
	width:100,
	status:null,
	channelType:null,
	layout:{
		type:'hbox',
		pack:'end',
		align:'middle'
	},
	removeHanlder:null,
	initComponent:function(){
		var me = this;
		me.items = me.getItems();
		this.callParent();
	},
	getLabelText:function(){
		var me =this;
		return me.labelText;
	},
	getItems:function(){
		var me =this;
		return [{
			xtype:'label',
			text:me.labelText
		},{
			xtype:'button',
			disabled:'view'==me.status||('new'==status &&'immediate'==channelType)?true:false,
			handler:me.removeHanlder
		}];
	}
});



