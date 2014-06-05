/*
 * 在 优惠券查询页面，点击营销计划名称（放大镜）按钮，弹出的查询营销计划页面
 * 钟琼
 * 2012-11
 */
 
 /**
 * 封装传往后台的参数，查询营销计划页面
 * 钟琼
 * 2012-11-29
 */
var planNamePara = null;
var planNumberPara = null;
var marketStatusPara = null;
/**
 *营销计划查询信息显示Panel
 */
Ext.define('MarketPlanSearchPanel',{
	extend:'SearchFormPanel', 
	width:600,
	items:null,
	initComponent:function(){			
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [
		   {
        	xtype:'simpbgroundsearchpanel',
        	margin:'0 0 5 0',
			layout:{
				type:'table',
				columns:4
			},
			defaults:{
				labelWidth:85,
				labelAlign:'right',
				width:180,
			},
			defaultType:'textfield',
			items:[
			    {//营销计划编码
					fieldLabel:i18n('i18n.coupon.planNumber'),
					name:'planNumber',
					id:'planNumberSearch',
					labelWidth:80,
					maxLength : 20,
					maxLengthText : i18n('i18n.coupon.maxLength')+20,
					width:200,
				},{//营销计划名称
					fieldLabel:i18n('i18n.coupon.planName'),
					name:'planName',
					width:210,
					maxLength : 80,
					maxLengthText : i18n('i18n.coupon.maxLength')+80,
					id:'planNameSearch'					
				},{//营销计划状态
					xtype:'combobox',
					fieldLabel:i18n('i18n.coupon.marketStatus'),
					labelWidth:70,
					name:'marketStatus',
					id:'marketStatus',
					editable:false,
					width:170,
					value:'20',
					displayField: 'name',
					valueField: 'id',
					store : Ext.create('Ext.data.Store',{
	                   fields: ['id', 'name'],
					    data : [
					        {"id":"00", "name":"全部"},
					        {"id":"20", "name":"已启用"},
					        {"id":"30", "name":"已终止"},
					        {"id":"40", "name":"已结束"}
	                   ]
	               }),
				},{//查询
					xtype:'button',
					width:60,
					text:i18n('i18n.coupon.query'),
					margin:'0 0 0 6',
					name:'',
					id:'',
					handler: function () {
						if(!(Ext.getCmp('planNumberSearch').isValid())){
							return false;
						}
						if(!(Ext.getCmp('planNameSearch').isValid())){
							return false;
						}
						Ext.getCmp('MarketPlanListGridId').store.loadPage(1);
			        }
				}
			 ]
			}
	    ];
	}
});
/**
 *营销计划查询显示结果Grid
 */
Ext.define('MarketPlanListGrid',{
	extend:'PopupGridPanel',
	defaults:{
		align:'center'
	},
	selModel : Ext.create('Ext.selection.CheckboxModel',{mode:'MULTI'}),
	autoScroll:true,
	initComponent:function(){ 
		var me = this;
		var store = Ext.create('CouponQueryMarketPlanListStore',{id:'couponQueryMarketPlanListStoreId'});
		store.on('beforeload',function(store,operation,e){	
			
			//封装查询条件传递给action
			var marketPlanSearchPanelForm = Ext.getCmp("MarketPlanSearchPanelId");
			planNamePara = marketPlanSearchPanelForm.getForm().findField('planNameSearch').getValue();
			planNumberPara = marketPlanSearchPanelForm.getForm().findField('planNumberSearch').getValue();
			if(Ext.isEmpty(planNumberPara)){
				marketStatusPara = marketPlanSearchPanelForm.getForm().findField('marketStatus').getValue();
			}
			var searchParams = {
				'marketplan.planName':planNamePara,
				'marketplan.planNumber':planNumberPara,
				'marketplan.marketStatus':marketStatusPara
			};
			Ext.apply(operation,{
					params : searchParams
			});
		});
		
		me.store = store;
		me.columns = [
		    {//序号
				xtype:'rownumberer',
				header:i18n('i18n.coupon.rownumberer'),
				width:50
		    },{//营销计划编码
				header:i18n('i18n.coupon.planNumber'),
				flex:0.4,
				dataIndex:'planNumber'
		    },{ //营销计划编码名称
				header :i18n('i18n.coupon.planName'),
				dataIndex:'planName',
				flex:0.3
		    },{//优惠券类型
				header :i18n('i18n.coupon.couponType'),
				dataIndex:'couponType',
				flex:0.3,
				renderer:function(val){
					if(val===COUPON_SENDHAND){
						return '手动发券';
					}else if(val===COUPON_SENDAUTO){
						return '自动发券';
					}else{
						return '';
					}
				}
		    },{//活动开始时间
		    	header :i18n('i18n.coupon.activityBeginTime'),
				dataIndex:'activityBeginTime',
				renderer : CouponUtil.renderDate,
				flex:0.3,
		    },{//活动结束时间
		    	header :i18n('i18n.coupon.activityEndTime'),
				dataIndex:'activityEndTime',
				renderer : CouponUtil.renderDate,
				flex:0.3,
		    },{//启用状态
				header :i18n('i18n.coupon.marketStatus'),
				dataIndex:'marketStatus',
				flex:0.3,
				renderer:function(val){
					if(val===MARKETPLAN_NOUSE){
						return '未启用';
					}else if(val===MARKETPLAN_USING){
						return '已启用';
					}else if(val===MARKETPLAN_STOP){
						return '已终止';
					}else if(val===MARKETPLAN_FINISH){
						return '已结束';
					}else{
						return '';
					}
				}
		    }
	    ];
		me.listeners = {
				'itemdblclick':function(me, record){
					Ext.getCmp('couponQueryConditionPanelId').getForm().findField('planName').setValue(record.data.planName);
					Ext.getCmp('couponQueryConditionPanelId').getForm().findField('planNumber').setValue(record.data.planNumber);
					me.up('basicpanel').up('basicwindow').close();
//					me.store.removeAll();
				}
		};
		me.dockedItems=[{
			xtype:'pagingtoolbar',
			cls:'pagingtoolbar',
			store:store,
			dock:'bottom',
			displayInfo : true,
			autoScroll : true,
			items:[{//每页显示
				text: i18n('i18n.coupon.performPerPage'),
				xtype: 'tbtext'
			},Ext.create('Ext.form.ComboBox', {
               width:window.screen.availWidth*0.0391,
               triggerAction:'all',
               forceSelection: true,
               value:'20',
               editable:false,
               name:'comboItem',
               displayField:'value',
               valueField:'value',
               queryMode:'local',
               store : Ext.create('Ext.data.Store',{
                   fields : ['value'],
                   data   : [
                       {'value':'1'},
                       {'value':'15'},
                       {'value':'20'},
                       {'value':'25'},
                       {'value':'40'},
                       {'value':'100'}
                   ]
               }),
               listeners:{
					select : {
			            fn: function(_field,_value){
			            	var pageSize = store.pageSize;
			            	var newPageSize = parseInt(_field.value);
			            	if(pageSize!=newPageSize){
			            		store.pageSize = newPageSize;
			            		this.up('pagingtoolbar').moveFirst();
			            	}
			            }
			        }
               }
           }),{//条
				text: i18n('i18n.coupon.items'),
				xtype: 'tbtext'
           }]
	}];
    this.callParent();
   }
});
/**
 * 营销计划查询页面整体布局panel
 * 钟琼 2012-11-29
 */
Ext.define('SearchMarketPlanByNamePanel', {
	extend : 'BasicPanel',
	layout : 'border',
	items :[
	  {//营销计划查询信息信息
		xtype : 'basicpanel',
		region:'north',
		layout:'fit',
		items:[Ext.create('MarketPlanSearchPanel',{id:'MarketPlanSearchPanelId'})],
		height:46
	  },{//结果显示列表
		xtype : 'basicpanel',
		layout:'border',
		region:'center',
		items:[{
			xtype : 'container',
			layout:'border',
			region:'center',
			items:[{//营销计划列表区
	    	    xtype : 'container',
				layout:'fit',
				region:'center',
				items:Ext.create('MarketPlanListGrid',{id:'MarketPlanListGridId'
				})
		       }
			]
		  }      
		]
	  }]
});