MarkCustomerDetailView = function(){
	
};
//初始化提示条
Ext.QuickTips.init();
/**
 * 查询单个客户的浮窗信息：包括回访信息页签、客户基本信息页签和近半年出发收入页签
 */
MarkCustomerDetailView.prototype.searchCustIntegrationInfoVO = function(param,successFn,failureFn){
	var url = '../marketing/searchCustIntegrationInfoVO.action';
	DButil.requestAjax(url,param,successFn,failureFn);
};

/**
 * 回访(客户)
 */
MarkCustomerDetailView.prototype.setRetureVisitByCust = function(param,successFn,failureFn){
	var url = '../marketing/initCreateReturnvisitPageByCust.action';
	DButil.requestAjax(url,param,successFn,failureFn);
};

/**
 * 回访信息页签中的model by肖红叶
 */
Ext.define('ReturnVisitTabModel', {
	extend: 'Ext.data.Model',
	fields: [{//回访内容
	    	name: 'problem'
	    },{//回访人
	    	name: 'marketingPerson'
	    },{//回访时间
	    	name: 'marketingDate',
	    	type: 'Date',
			defaultValue:null,
			convert: DButil.changeLongToDate
	    }    
	]
});

/**
 * 回访信息页签中的store
 */  
Ext.define('ReturnVisitTabStore', {
	extend:'Ext.data.Store',
    model: 'ReturnVisitTabModel',
	proxy:{
		type:'ajax',
		url:'../marketing/searchReturnVisitInfoList.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			root:'returnVisitInfoList'
		}
	}
});
	
/**
 * 回访信息页签中的grid
 */	
Ext.define('ReturnVisitTabGrid',{
	extend:'PopupGridPanel',
    store: Ext.create('ReturnVisitTabStore',{
		listeners:{
			beforeload:function(store, operation, eOpts){
				Ext.apply(operation,{
					params : {
						'custId':Ext.getCmp('custIdForReturnVisitPanel').getValue(),
						'custType':Ext.getCmp('custTypeForReturnVisitPanel').getValue()
						}
					}
				);	
			}
		}
    }),
    defaults:{
		align:'center'
	},
	autoScroll:true,
    columns:[{//序号
			text:i18n('i18n.Cycle.rownum'),
			xtype:'rownumberer',
			width:40
		},{//回访内容
			text:i18n('i18n.visualMarket.returnVisitContent'),
			dataIndex:'problem',
			flex:1,
			renderer:function(val){
				if(!Ext.isEmpty(val)){
					var content = val;
					if(val.length >= 21){
						val=val.substr(0,20)+'...';
					}else{
						val= val;
					} 
					return '<a title="'+content+'">'+val+'</a>';
				}
			}
		},{//回访时间
			text:i18n('i18n.returnVisit.executorTime'),
			dataIndex:'marketingDate',
			renderer : DButil.renderDate,
			width:100
		},{//回访人
			text:i18n('i18n.returnVisit.execUserId'),
			dataIndex:'marketingPerson',
			width:80
		}
    ]
})

/**
 * 交易信息页签中的model by肖红叶
 */
Ext.define('ArriveIncomeTabModel', {
	extend: 'Ext.data.Model',
	fields: [{//年份
	    	name: 'year'
	    },{//月份
	    	name: 'month'
	    },{//到达收入
	    	name: 'arriveIncome',
	    	type:'float'
	    },{//出发收入
	    	name: 'sendIncome',
	    	type:'float'
	    }    
	]
});

/**
 * 交易信息页签中的store by肖红叶
 */
Ext.define('ArriveIncomeTabStore',{
	extend:'Ext.data.Store',
	model:'ArriveIncomeTabModel'
});

/**
 * 交易信息页签中的近半年出发收入折现图 by肖红叶
 */
Ext.define('CustInfoTabChart', {
	extend:'Ext.chart.Chart',
    animate: true,
	initComponent:function(){
		var me = this;
		me.store = Ext.create('ArriveIncomeTabStore');
		me.axes = [{
	        type: 'Numeric',
	        position: 'left',
	        fields: ['sendIncome','arriveIncome'],
	        width:150,
	        label: {
	            renderer: Ext.util.Format.numberRenderer('00.00')
	        },
//	        grid: true,
	        grid:{
	        	odd:{
	        		opacity:1,
	        		fill:'#FFFF99',
	        		stroke:'#FF3300',
	        		'stroke-width':0.5
	        	},
	        	even:{
	        		opacity:0,
	        		stroke:'#6600CC',
	        		'stroke-width':0.5
	        	}
	        },
	        minimum: 0,
	        //近半年出发收入
	        title:i18n('i18n.visualMarket.arriveIncome')
	    },{
	        type: 'Category',
	        position: 'bottom',
	        grid: true,
	        label: {
	            renderer: function(val){
	            	return ' '+val+'月';
	            },
	            display:'rotate',
	            margin:'0 0 0 5'
	        },
	        fields: ['month']
	    }];
		me.series = [{
	        type: 'line',
	        title:i18n('i18n.visualMarket.arriveIncomeList'),
	        showInLegend:true,
	        style: {
	            stroke: '#00ff00'
	        },
	        highlight: {
	            size: 4,
	            radius: 0
	        },
	        axis: 'left',
	        xField: 'month',
	        yField: 'arriveIncome',
	        tips:{
	    	  trackMouse: true,
	    	  width: 160,
	    	  height: 45,
	    	  renderer: function(storeItem, item) {
	    	    this.setTitle(i18n('i18n.visualMarket.time')+storeItem.get('year')+i18n('i18n.visualMarket.year')+storeItem.get('month')+'月<br/>'
	    	    		+i18n('i18n.visualMarket.monthOfArriveIncome')+storeItem.get('arriveIncome')+i18n('i18n.visualMarket.yuan'));
	    	  }
	    	},
	        markerConfig: {
	            type: 'circle',
	            size: 4,
	            radius: 4,
	            'stroke-width': 0,
	            'fill': '#0f0'
	        }
	    },{
	        type: 'line',
	        title:i18n('i18n.visualMarket.sendIncomeList'),
	        showInLegend:true,
	        style: {
	            stroke: '#ff0000'
	        },
	        highlight: {
	            size: 4,
	            radius: 0
	        },
	        axis: 'left',
	        xField: 'month',
	        yField: 'sendIncome',
	        tips:{
	    	  trackMouse: true,
	    	  width: 160,
	    	  height: 45,
	    	  renderer: function(storeItem, item) {
	    	    this.setTitle(i18n('i18n.visualMarket.time')+storeItem.get('year')+i18n('i18n.visualMarket.year')+storeItem.get('month')+'月<br/>'
	    	    		+i18n('i18n.visualMarket.monthOfSendIncome')+storeItem.get('sendIncome')+i18n('i18n.visualMarket.yuan'));
	    	  }
	    	},
	        markerConfig: {
	            type: 'cross',
	            size: 4,
	            radius: 4,
	            'stroke-width': 0,
	            'fill': '#f00'
	        }
	    }
	    ];
		
		this.callParent();
	}
});

/**
 * 客户信息页签
 */
Ext.define('CustInfoTabPanel',{
	extend:'SearchFormPanel',
	items:null,
	width:450,
	height:180,
	border:0,
	layout:{
		type:'table',
		columns:3
	},
	defaults:{
		labelWidth:60,
		labelAlign:'right',
		width:120,
		align:'left',
		margin:'0 5 5 0'
	},
	defaultType:'displayfield',
	initComponent:function(){
		if(showCustType === 'MEMBER'){
			this.items = this.getCustItems();
		}
		else{
			this.items = this.getPSCustItems();
		}
		this.callParent();
	},
	getCustItems:function(){
		var me = this;
		return [
		    {//客户名称
		    	fieldLabel:i18n('i18n.Cycle.custName'),
		    	name:'custName',
		    	width:360,
		    	colspan:3,
		    	cls:'fontRed'
		    },{//客户类型或回访状态图片
		    	fieldLabel:'',
		    	name:'custDegree',
		    	labelSeparator:'',
		    	width:30
		    },{//月均收入
		    	fieldLabel:i18n('i18n.visualMarket.monthlyAverageIncome'),
		    	name:'aveMonthlyIncome',
		    	colspan:3,
		    	width:340,
		    	cls:'fontRed',
		    	margin:'0 5 5 -140'
		    },{//主联系人
		    	fieldLabel:i18n('i18n.visualMarket.mainLinkman'),
		    	name:'mainLinkManName',
		    	colspan:3,
		    	width:360
		    },{//手机
		    	fieldLabel:i18n('i18n.memberView.mobileNumber'),
		    	name:'mobile',
		    	width:180
		    },{//固定电话
		    	fieldLabel:i18n('i18n.memberView.telephoneNumber'),
		    	name:'telephone',
		    	colspan:2,
		    	width:180
		    },{//地址
		    	fieldLabel:i18n('i18n.PotentialCustEditView.address'),
		    	name:'address',
		    	colspan:3,
		    	width:360
		    },{//创建时间
		    	xtype:'displayfield',
		    	fieldLabel:i18n('i18n.developPlan.createStartTime'),
		    	name:'createTime',
		    	width:160,
		    	cls:'fontRed'
		    },{//最近一次回访时间
		    	xtype:'displayfield',
		    	fieldLabel:i18n('i18n.visualMarket.latestReturnVisitTime'),
		    	name:'returnVisitTime',
		    	labelWidth:100,
		    	width:190,
		    	colspan:2,
		    	cls:'fontRed'
		    },{//custId
		    	xtype:'hiddenfield',
		    	fieldLabel:'',
		    	name:'custId',
		    	id:'custIdForReturnVisitPanel'
		    },{//客户类型
		    	xtype:'hiddenfield',
		    	fieldLabel:'',
		    	name:'custType',
		    	id:'custTypeForReturnVisitPanel'
		    },{//主联系人id
		    	xtype:'hiddenfield',
		    	fieldLabel:'',
		    	name:'linkManId',
		    	id:'linkManIdForReturnVisitPanel'
		    }
		]
	},
	getPSCustItems:function(){
		var me = this;
		return [
		    {//客户名称
		    	fieldLabel:i18n('i18n.Cycle.custName'),
		    	name:'custName',
		    	width:360,
		    	colspan:3,
		    	cls:'fontRed'
		    },{//客户类型或回访状态图片
		    	fieldLabel:'',
		    	name:'custType',
		    	labelSeparator:'',
		    	width:30
		    },{//联系人姓名
		    	fieldLabel:i18n('i18n.developSchedule.linkManName'),
		    	name:'linkManName',
		    	labelWidth:80,
		    	colspan:2,
		    	width:320,
		    	margin:'0 5 5 -140'
		    },{//手机
		    	fieldLabel:i18n('i18n.memberView.mobileNumber'),
		    	name:'mobile',
		    	width:180
		    },{//固定电话
		    	fieldLabel:i18n('i18n.memberView.telephoneNumber'),
		    	name:'telephone',
		    	colspan:2,
		    	width:180
		    },{//地址
		    	fieldLabel:i18n('i18n.PotentialCustEditView.address'),
		    	name:'address',
		    	colspan:3,
		    	width:360
		    },{//创建时间
		    	xtype:'displayfield',
		    	fieldLabel:i18n('i18n.developPlan.createStartTime'),
		    	name:'createTime',
		    	width:160,
		    	cls:'fontRed'
		    },{//最近一次回访时间
		    	xtype:'displayfield',
		    	fieldLabel:i18n('i18n.visualMarket.latestReturnVisitTime'),
		    	name:'returnVisitTime',
		    	labelWidth:100,
		    	width:190,
		    	colspan:2,
		    	cls:'fontRed'
		    },{//custId
		    	xtype:'hiddenfield',
		    	fieldLabel:'',
		    	name:'psCustId',
		    	id:'custIdForReturnVisitPanel'
		    },{//客户类型
		    	fieldLabel:'',
		    	xtype:'hiddenfield',
		    	name:'custDegree',
		    	id:'custTypeForReturnVisitPanel'
		    },{//联系人id
		    	fieldLabel:'',
		    	xtype:'hiddenfield',
		    	name:'linkManId',
		    	id:'linkManIdForReturnVisitPanel'
		    } 
		]
	}
});

/**
 * 回访信息页签中的grid
 */	
Ext.define('CRM.VisualMarket.MakerCustDetail', {
    extend:'Ext.tab.Panel',
    activeTab:0,
	initComponent : function(){
		var me = this;
		if(showCustType === 'MEMBER'){
			me.items= [{//客户信息页签
				title:i18n('i18n.SearchMember.memberInfo'),
				layout:'fit',
				height:185,
				items:[Ext.create('CustInfoTabPanel',{id:'custInfoTabPanelId'})]
			},{//交易信息页签
				title:i18n('i18n.visualMarket.dealInfo'),
				id:'dealInfoTabPanelForMemberId',
				disabled:true,
				height:185,
				layout:'border',
				items:[{//收入折线图
			    	xtype:'container',
			    	layout:'fit',
			    	region:'center',
			    	items:[Ext.create('CustInfoTabChart',{id:'custInfoTabChartId'})]
			    },{//
					xtype:'container',
					region:'south',
					height:25,
					layout:'hbox',
					items:[{
						xtype:'displayfield',
						fieldLabel:i18n('i18n.visualMarket.latestDealTime'),
						name:'dealTime',
						id:'dealTimeId',
						cls:'fontRed',
						flex:2
					},{
						xtype:'displayfield',
						fieldLabel:i18n('i18n.Cycle.sendGoodCycle'),
						name:'sendGoodsCycle',
						id:'sendGoodsCycleId',
						cls:'fontRed',
						flex:2
					},{
						xtype:'button',
						text:i18n('i18n.visualMarket.returnVisitCustomer'),
						id:'returnVisitForDealInfoBtn',
						hidden:true,
						flex:1,
						handler:function(){
							returnVisitBtnFunction();							
						}
					}]
			    }]
			},{//修改后回访信息页签
				title:i18n('i18n.returnVisit.customerInfo'),
				layout:'border',
				id:'returnVisitListTabPanelForMemberId',
				disabled:true,
				items:[{//回访信息列表
			    	xtype:'container',
			    	layout:'fit',
			    	height:150,
			    	region:'center',
			    	items:[Ext.create('ReturnVisitTabGrid',{id:'returnVisitTabGridId'})]
			    },{//回访客户按钮
					xtype:'container',
					region:'south',
					height:25,
					layout:'hbox',
					items:[{
						xtype:'label',
						flex:4
					},{//回访客户的按钮
						xtype:'button',
						text:i18n('i18n.visualMarket.returnVisitCustomer'),
						hidden:true,
						flex:1,
						id:'returnVisitForReturnVistBtn',
						handler:function(){
							returnVisitBtnFunction();
						}
					}]
			    }]
			}];
		}
		else{
			me.items= [{//客户信息页签
				title:i18n('i18n.SearchMember.memberInfo'),
				height:185,
				layout:'fit',
				items:[Ext.create('CustInfoTabPanel',{id:'custInfoTabPanelId'})]
			},{//修改后回访信息页签
				title:i18n('i18n.returnVisit.customerInfo'),
				id:'returnVisitListTabPanelForMemberId',
				disabled:true,
				height:185,
				layout:'border',
				items:[{//回访信息列表
			    	xtype:'container',
			    	layout:'fit',
			    	region:'center',
			    	items:[Ext.create('ReturnVisitTabGrid',{id:'returnVisitTabGridId'})]
			    },{//回访客户按钮
					xtype:'container',
					region:'south',
					height:25,
					layout:'hbox',
					items:[{
						xtype:'label',
						flex:4
					},{//回访客户的按钮
						xtype:'button',
						text:i18n('i18n.visualMarket.returnVisitCustomer'),
						hidden:true,
						flex:1,
						id:'returnVisitForReturnVistBtn',
						handler:function(){
							returnVisitBtnFunction();
						}
					}]
			    }]
			}];
		}
		this.callParent();
		//当登陆用户为营业员或者营业部经理时回访客户按钮可用
		if(!Ext.isEmpty(User)){
			if(Ext.Array.contains(User.roleids,i18n('i18n.visualMarket.salesManRoleId')) ||
					Ext.Array.contains(User.roleids,i18n('i18n.visualMarket.deptManagerRoleId'))){
				if(showCustType === 'MEMBER'){
					Ext.getCmp('returnVisitForDealInfoBtn').show();
					Ext.getCmp('returnVisitForReturnVistBtn').show();
				}
				else{
					Ext.getCmp('returnVisitForReturnVistBtn').show();
				}
			}
		}
	}
});

//单击客户名称，弹出客户浮窗信息
function showCustomerMarkDetail(custId,custType,longitude,latitude,returnVisitStatus,custDegree,deptId){
	//首先判断该客户是否已标注，若未标注，则弹出提示信息
	if(Ext.isEmpty(longitude) || Ext.isEmpty(latitude)){
		MessageUtil.showErrorMes(i18n('i18n.visualMarket.noMarkCustomer'));	
	}
	else if(!clickToShowMarkCustDetail){
//		MessageUtil.showErrorMes('请不要频繁单击进行查询！数据正在查询，请稍候......');
//		return false;
	}
	else{
		clickToShowMarkCustDetail = false;
		setTimeout(function(){
			clickToShowMarkCustDetail = true;
		},1000);
		//设置查询的客户类型：固定客户，潜客，散客
		showCustType = custType;
		//若弹出的浮窗div被手动关闭，则重新创建一个div
		if(Ext.isEmpty(document.getElementById("markCustDetailPanelID"))){
			//销毁已经存在的浮窗panel，重新渲染
			if(!Ext.isEmpty(Ext.getCmp('makerCustDetailId'))){
				Ext.getCmp('makerCustDetailId').destroy();
			}
			var div = document.createElement('div');
			div.id = 'markCustDetailPanelID';
			document.body.appendChild(div);
		}
		//如果客户浮窗panel不存在，则重新创建客户浮窗的panel
		if(Ext.isEmpty(Ext.getCmp('makerCustDetailId'))){
	    	Ext.create('CRM.VisualMarket.MakerCustDetail',{
	    		id:'makerCustDetailId',
	    		renderTo:'markCustDetailPanelID'
	    	});
			//初始化浮窗信息显示
			Ext.getCmp("custInfoTabPanelId").getForm().reset();
			if(showCustType === 'MEMBER'){
				Ext.getCmp("custInfoTabChartId").store.removeAll();
//				Ext.getCmp("custInfoTabChartId").update();
				Ext.getCmp("custInfoTabChartId").doComponentLayout();
				//清空上月发货周期和最近一次交易时间
				Ext.getCmp('dealTimeId').setValue('');
				Ext.getCmp('sendGoodsCycleId').setValue('');
			}
			Ext.getCmp("returnVisitTabGridId").store.removeAll();
			Ext.getCmp('makerCustDetailId').setActiveTab(0);
	    }
		else{
			if(showCustType === 'MEMBER'){
				//销毁客户浮窗panel，以解决再次点击客户名称时弹出的交易信息重叠的情况
				Ext.getCmp("makerCustDetailId").destroy();
				//新创建一个客户浮窗panel
		    	Ext.create('CRM.VisualMarket.MakerCustDetail',{
		    		id:'makerCustDetailId',
		    		renderTo:'markCustDetailPanelID'
		    	});
				//初始化浮窗信息显示
				Ext.getCmp("custInfoTabPanelId").getForm().reset();
				Ext.getCmp("returnVisitTabGridId").store.removeAll();
				Ext.getCmp('makerCustDetailId').setActiveTab(0);
				Ext.getCmp("custInfoTabChartId").store.removeAll();
				//清空上月发货周期和最近一次交易时间
				Ext.getCmp('dealTimeId').setValue('');
				Ext.getCmp('sendGoodsCycleId').setValue('');
			}
			else{
				//初始化浮窗信息显示
				Ext.getCmp("custInfoTabPanelId").getForm().reset();
				Ext.getCmp("returnVisitTabGridId").store.removeAll();
				Ext.getCmp('makerCustDetailId').setActiveTab(0);
			}
		}
		
		//根据客户类型和客户id查询浮窗信息，包括客户基本信息、回访信息和近半年收入信息
		var successFn = function(json){
			//客户基本信息panel
			var custInfoTabPanel = Ext.getCmp("custInfoTabPanelId").getForm();
			if(!Ext.isEmpty(json.customerMarketInfo)){
				var custNameString = json.customerMarketInfo.custName;
				if(!Ext.isEmpty(custNameString) && custNameString.length > 40){
					json.customerMarketInfo.custName = '<a title = "'+custNameString+'">'+Ext.String.ellipsis(custNameString,40,false) +'</a>'+'>>'+
					'<a href = "javascript:viewCustDetail(\''+custId+'\',\''+custType+'\')">'+i18n('i18n.visualMarket.detail')+'</a>';
				}
				else{
					json.customerMarketInfo.custName = custNameString +'>>'+
					'<a href = "javascript:viewCustDetail(\''+custId+'\',\''+custType+'\')">'+i18n('i18n.visualMarket.detail')+'</a>';
				}
				if(!Ext.isEmpty(json.customerMarketInfo.address) && json.customerMarketInfo.address.length > 40){
					json.customerMarketInfo.address = '<a title = "'+json.customerMarketInfo.address+'">'+Ext.String.ellipsis(json.customerMarketInfo.address,40,false) +'</a>';
				}
				if(!Ext.isEmpty(json.customerMarketInfo.mainLinkManName) && json.customerMarketInfo.mainLinkManName.length > 15){
					json.customerMarketInfo.mainLinkManName = '<a title = "'+json.customerMarketInfo.mainLinkManName+'">'+Ext.String.ellipsis(json.customerMarketInfo.mainLinkManName,15,false) +'</a>';
				}
				if(!Ext.isEmpty(json.customerMarketInfo.linkManName) && json.customerMarketInfo.linkManName.length > 15){
					json.customerMarketInfo.linkManName = '<a title = "'+json.customerMarketInfo.linkManName+'">'+Ext.String.ellipsis(json.customerMarketInfo.linkManName,15,false) +'</a>';
				}
				//限制固定电话的显示长度
				if(!Ext.isEmpty(json.customerMarketInfo.telephone) && json.customerMarketInfo.telephone.length > 15){
					json.customerMarketInfo.telephone = '<a title = "'+json.customerMarketInfo.telephone+'">'+Ext.String.ellipsis(json.customerMarketInfo.telephone,15,false) +'</a>';
				}
				
				if(json.customerMarketInfo.custType === 'MEMBER'){
					json.customerMarketInfo.custDegree = '<img src="../scripts/marketing/map/images/'+json.customerMarketInfo.custDegree+'.png"/>';
		            //当后端返回过来的月均收入为空时，则将aveMonthlyIncome置为0
					 if(Ext.isEmpty(json.customerMarketInfo.aveMonthlyIncome)){
						 json.customerMarketInfo.aveMonthlyIncome = '0'; 
					 }
					 json.customerMarketInfo.aveMonthlyIncome = json.customerMarketInfo.aveMonthlyIncome + i18n('i18n.visualMarket.yuan');
				}
				else{
					json.customerMarketInfo.custDegree = json.customerMarketInfo.custType;
					json.customerMarketInfo.custType = '<img src="../scripts/marketing/map/images/'+json.customerMarketInfo.custType+'.png"/>';
				}
				//格式化显示最近一次回访时间
				if(!Ext.isEmpty(json.customerMarketInfo.returnVisitTime)){
					json.customerMarketInfo.returnVisitTime = Ext.Date.format(new Date(json.customerMarketInfo.returnVisitTime), 'Y-m-d');
				}
				//格式化显示创建时间
				if(!Ext.isEmpty(json.customerMarketInfo.createTime)){
					json.customerMarketInfo.createTime = Ext.Date.format(new Date(json.customerMarketInfo.createTime), 'Y-m-d');
				}
			}
			custInfoTabPanel.loadRecord(new CRM.marketing.CustomerMarketInfoModel(json.customerMarketInfo));
			
			
			//如果当前的视图为客户类型试图时，则在弹出的页签中显示回访状态的图标
			if(Ext.isEmpty(viewTypeOfSelectResult) || viewTypeOfSelectResult === 'CUSTVIEW'){
				if(Ext.isEmpty(json.customerMarketInfo.returnVisitStatus)){
					json.customerMarketInfo.returnVisitStatus = '3';
				}
				if(json.customerMarketInfo.custType === 'MEMBER'){
					custInfoTabPanel.findField('custDegree').setValue('<img src="../scripts/marketing/map/images/'+json.customerMarketInfo.returnVisitStatus+'.png"/>');
				}
				else{
					custInfoTabPanel.findField('custType').setValue('<img src="../scripts/marketing/map/images/'+json.customerMarketInfo.returnVisitStatus+'.png"/>');
				}
			}
			custInfoTabPanel.findField('createTime').setValue(json.customerMarketInfo.createTime);
			custInfoTabPanel.findField('returnVisitTime').setValue(json.customerMarketInfo.returnVisitTime);
			if(json.customerMarketInfo.custType === 'MEMBER'){
				// 近半年出发收入
				var halfYearIncomeList = json.halfYearIncomeList;
				//将后端返回至前端的出发收入或到达收入中的空值置为零
				if(!Ext.isEmpty(halfYearIncomeList) && halfYearIncomeList.length > 0){
					for(var i = 0;i < halfYearIncomeList.length;i++){
						if(Ext.isEmpty(halfYearIncomeList[i].arriveIncome)){
							halfYearIncomeList[i].arriveIncome = 0;
						}
						if(Ext.isEmpty(halfYearIncomeList[i].sendIncome)){
							halfYearIncomeList[i].sendIncome = 0;
						}
					}
				}
				Ext.getCmp("custInfoTabChartId").store.loadData(halfYearIncomeList);

				//判断是否需要设置纵坐标的最大值
				var setMaximumOfIncomeList = true;
				if(!Ext.isEmpty(halfYearIncomeList) && halfYearIncomeList.length > 0){
					for(var i = 0;i < halfYearIncomeList.length;i++){
						if(halfYearIncomeList[i].arriveIncome !== 0 || halfYearIncomeList[i].sendIncome !== 0){
							setMaximumOfIncomeList = false;
							break;
						}
					}
				}
				if(setMaximumOfIncomeList){
					Ext.getCmp("custInfoTabChartId").axes.map.left.maximum = 100;
				}
//				Ext.getCmp("custInfoTabChartId").initComponent();
//				Ext.getCmp("custInfoTabChartId").doComponentLayout();

				//格式化显示最近一次交易时间
				if(!Ext.isEmpty(json.customerMarketInfo.dealTime)){
					json.customerMarketInfo.dealTime = Ext.Date.format(new Date(json.customerMarketInfo.dealTime), 'Y-m-d');
					Ext.getCmp('dealTimeId').setValue(json.customerMarketInfo.dealTime);
				}
				Ext.getCmp('sendGoodsCycleId').setValue(json.customerMarketInfo.sendGoodsCycle+i18n('i18n.visualMarket.dayPerTime'));
				Ext.getCmp('dealInfoTabPanelForMemberId').enable();
			}
			// 回访页签数据
			Ext.getCmp('returnVisitListTabPanelForMemberId').enable();
			Ext.getCmp("returnVisitTabGridId").store.loadData(json.rvOpinionList);
		};
		var failureFn = function(json){
			if(Ext.isEmpty(json)){
				MessageUtil.showErrorMes(i18n('i18n.visualMarket.exceedTimeOfSearchCustDetail'));
			}
			else{
				MessageUtil.showErrorMes(json.message);
			}
		};
		var param={
			'customerMarketInfo.custType':custType,
			'customerMarketInfo.custId':custId,
			'customerMarketInfo.psCustId':custId,
			'customerMarketInfo.deptId':deptId
		};
		MarkCustomerDetailView.prototype.searchCustIntegrationInfoVO(param, successFn, failureFn);		
		//判断按照哪种视图方式显示客户标注并将客户浮窗信息显示在百度地图中
		if(Ext.isEmpty(viewTypeOfSelectResult) || viewTypeOfSelectResult === 'CUSTVIEW'){
			point.showCustMarkerWindow(custId,document.getElementById("markCustDetailPanelID"),longitude,latitude,custDegree);
		}
		else{
			point.showCustMarkerWindow(custId,document.getElementById("markCustDetailPanelID"),longitude,latitude,returnVisitStatus);
		}
	}

}


//单击客户标注，弹出客户浮窗信息
function showCustomerDetail(custId,custType,longitude,latitude,deptId,callbackFun){
	//定义一个查询客户类型，用以转化gis传过来的客户等级
	var selectCustType = '';
	if(Ext.isEmpty(longitude) || Ext.isEmpty(latitude)){
		MessageUtil.showErrorMes(i18n('i18n.visualMarket.noMarkCustomer'));	
		return false;
	}
	else if(!clickToShowMarkCustDetail){
//		return false;
	}
	else{
		clickToShowMarkCustDetail = false;
		setTimeout(function(){
			clickToShowMarkCustDetail = true;
		},1000);
		if(Ext.isEmpty(custDegreeOrCustType) && (!Ext.isEmpty(searchConditionParam))){
			if(!Ext.isEmpty(searchConditionParam.queryCondition.psCustType)){
				custDegreeOrCustType = searchConditionParam.queryCondition.psCustType[0];
			}
			
		}
		if((custDegreeOrCustType !== 'PC_CUSTOMER') && (custDegreeOrCustType !==  'SC_CUSTOMER')){
			showCustType = 'MEMBER';
			selectCustType = 'MEMBER';
		}
		else{
			showCustType = custDegreeOrCustType;
			selectCustType = custDegreeOrCustType;
		}
		//若弹出的浮窗div被手动关闭，则重新创建一个div
		if(Ext.isEmpty(document.getElementById("markCustDetailPanelID"))){
			//销毁已经存在的浮窗panel，重新渲染
			if(!Ext.isEmpty(Ext.getCmp('makerCustDetailId'))){
				Ext.getCmp('makerCustDetailId').destroy();
			}
			var div = document.createElement('div');
			div.id = 'markCustDetailPanelID';
			document.body.appendChild(div);
		}
		//如果客户浮窗panel不存在，则重新创建客户浮窗的panel
		if(Ext.isEmpty(Ext.getCmp('makerCustDetailId'))){
	    	Ext.create('CRM.VisualMarket.MakerCustDetail',{
	    		id:'makerCustDetailId',
	    		renderTo:'markCustDetailPanelID'
	    	});
			//初始化浮窗信息显示
			Ext.getCmp("custInfoTabPanelId").getForm().reset();
			if(showCustType === 'MEMBER'){
				Ext.getCmp("custInfoTabChartId").store.removeAll();
				//清空上月发货周期和最近一次交易时间
				Ext.getCmp('dealTimeId').setValue('');
				Ext.getCmp('sendGoodsCycleId').setValue('');
			}
			Ext.getCmp("returnVisitTabGridId").store.removeAll();
			Ext.getCmp('makerCustDetailId').setActiveTab(0);
	    }
		else{
			if(showCustType === 'MEMBER'){
				//销毁客户浮窗panel，以解决再次点击客户标注时弹出的交易信息重叠的情况
				Ext.getCmp("makerCustDetailId").destroy();
				//新创建一个客户浮窗panel
		    	Ext.create('CRM.VisualMarket.MakerCustDetail',{
		    		id:'makerCustDetailId',
		    		renderTo:'markCustDetailPanelID'
		    	});
				//初始化浮窗信息显示
				Ext.getCmp("custInfoTabPanelId").getForm().reset();
				Ext.getCmp("returnVisitTabGridId").store.removeAll();
				Ext.getCmp('makerCustDetailId').setActiveTab(0);
				Ext.getCmp("custInfoTabChartId").store.removeAll();
				//清空上月发货周期和最近一次交易时间
				Ext.getCmp('dealTimeId').setValue('');
				Ext.getCmp('sendGoodsCycleId').setValue('');
			}
			else{
				//初始化浮窗信息显示
				Ext.getCmp("custInfoTabPanelId").getForm().reset();
				Ext.getCmp("returnVisitTabGridId").store.removeAll();
				Ext.getCmp('makerCustDetailId').setActiveTab(0);
			}
		}
		
		//根据客户类型和客户id查询浮窗信息，包括客户基本信息、回访信息和近半年收入信息
		var successFn = function(json){
			//客户基本信息panel
			var custInfoTabPanel = Ext.getCmp("custInfoTabPanelId").getForm();
			if(!Ext.isEmpty(json.customerMarketInfo)){
				var custNameString = json.customerMarketInfo.custName;
				if(!Ext.isEmpty(custNameString) && custNameString.length > 40){
					json.customerMarketInfo.custName = '<a title = "'+custNameString+'">'+Ext.String.ellipsis(custNameString,40,false) +'</a>'+'>>'+
					'<a href = "javascript:viewCustDetail(\''+custId+'\',\''+selectCustType+'\')">'+i18n('i18n.visualMarket.detail')+'</a>';
				}
				else{
					json.customerMarketInfo.custName = custNameString +'>>'+
					'<a href = "javascript:viewCustDetail(\''+custId+'\',\''+selectCustType+'\')">'+i18n('i18n.visualMarket.detail')+'</a>';
				}
				if(!Ext.isEmpty(json.customerMarketInfo.address) && json.customerMarketInfo.address.length > 40){
					json.customerMarketInfo.address = '<a title = "'+json.customerMarketInfo.address+'">'+Ext.String.ellipsis(json.customerMarketInfo.address,40,false) +'</a>';
				}
				if(!Ext.isEmpty(json.customerMarketInfo.mainLinkManName) && json.customerMarketInfo.mainLinkManName.length > 15){
					json.customerMarketInfo.mainLinkManName = '<a title = "'+json.customerMarketInfo.mainLinkManName+'">'+Ext.String.ellipsis(json.customerMarketInfo.mainLinkManName,15,false) +'</a>';
				}
				if(!Ext.isEmpty(json.customerMarketInfo.linkManName) && json.customerMarketInfo.linkManName.length > 15){
					json.customerMarketInfo.linkManName = '<a title = "'+json.customerMarketInfo.linkManName+'">'+Ext.String.ellipsis(json.customerMarketInfo.linkManName,15,false) +'</a>';
				}
				//限制固定电话的显示长度
				if(!Ext.isEmpty(json.customerMarketInfo.telephone) && json.customerMarketInfo.telephone.length > 15){
					json.customerMarketInfo.telephone = '<a title = "'+json.customerMarketInfo.telephone+'">'+Ext.String.ellipsis(json.customerMarketInfo.telephone,15,false) +'</a>';
				}
				if(json.customerMarketInfo.custType === 'MEMBER'){
					json.customerMarketInfo.custDegree = '<img src="../scripts/marketing/map/images/'+json.customerMarketInfo.custDegree+'.png"/>';
					//当后端返回过来的月均收入为空时，则将aveMonthlyIncome置为0
					 if(Ext.isEmpty(json.customerMarketInfo.aveMonthlyIncome)){
						 json.customerMarketInfo.aveMonthlyIncome = '0'; 
					 }
					 json.customerMarketInfo.aveMonthlyIncome = json.customerMarketInfo.aveMonthlyIncome + i18n('i18n.visualMarket.yuan');
				}
				else{
					json.customerMarketInfo.custDegree = json.customerMarketInfo.custType;
					json.customerMarketInfo.custType = '<img src="../scripts/marketing/map/images/'+json.customerMarketInfo.custType+'.png"/>';
				}
				//格式化显示最近一次回访时间
				if(!Ext.isEmpty(json.customerMarketInfo.returnVisitTime)){
					json.customerMarketInfo.returnVisitTime = Ext.Date.format(new Date(json.customerMarketInfo.returnVisitTime), 'Y-m-d');
				}
				//格式化显示创建时间
				if(!Ext.isEmpty(json.customerMarketInfo.createTime)){
					json.customerMarketInfo.createTime = Ext.Date.format(new Date(json.customerMarketInfo.createTime), 'Y-m-d');
				}
			}
			custInfoTabPanel.loadRecord(new CRM.marketing.CustomerMarketInfoModel(json.customerMarketInfo));
			
			//如果当前的视图为客户类型试图时，则在弹出的页签中显示回访状态的图标
			if(Ext.isEmpty(viewTypeOfSelectResult) || viewTypeOfSelectResult === 'CUSTVIEW'){
//				if(!Ext.isEmpty(custInfoTabPanel.findField('custType'))){
//					if(Ext.isEmpty(json.customerMarketInfo.returnVisitStatus)){
//						json.customerMarketInfo.returnVisitStatus = '3';
//					}
//					custInfoTabPanel.findField('custType').setValue('<img src="../scripts/marketing/map/images/'+json.customerMarketInfo.returnVisitStatus+'.png"/>');
//				}
//				if(!Ext.isEmpty(custInfoTabPanel.findField('custDegree'))){
//					custInfoTabPanel.findField('custDegree').setValue('<img src="../scripts/marketing/map/images/'+json.customerMarketInfo.returnVisitStatus+'.png"/>');
//				}
				if(Ext.isEmpty(json.customerMarketInfo.returnVisitStatus)){
					json.customerMarketInfo.returnVisitStatus = '3';
				}
				if(json.customerMarketInfo.custType === 'MEMBER'){
					custInfoTabPanel.findField('custDegree').setValue('<img src="../scripts/marketing/map/images/'+json.customerMarketInfo.returnVisitStatus+'.png"/>');
				}
				else{
					custInfoTabPanel.findField('custType').setValue('<img src="../scripts/marketing/map/images/'+json.customerMarketInfo.returnVisitStatus+'.png"/>');
				}
			}
			custInfoTabPanel.findField('createTime').setValue(json.customerMarketInfo.createTime);
			custInfoTabPanel.findField('returnVisitTime').setValue(json.customerMarketInfo.returnVisitTime);
			if(json.customerMarketInfo.custType === 'MEMBER'){
				// 近半年出发收入
				var halfYearIncomeList = json.halfYearIncomeList;
				//将后端返回至前端的出发收入或到达收入中的空值置为零
				if(!Ext.isEmpty(halfYearIncomeList) && halfYearIncomeList.length > 0){
					for(var i = 0;i < halfYearIncomeList.length;i++){
						if(Ext.isEmpty(halfYearIncomeList[i].arriveIncome)){
							halfYearIncomeList[i].arriveIncome = 0;
						}
						if(Ext.isEmpty(halfYearIncomeList[i].sendIncome)){
							halfYearIncomeList[i].sendIncome = 0;
						}
					}
				}
				Ext.getCmp("custInfoTabChartId").store.loadData(halfYearIncomeList);

				//判断是否需要设置纵坐标的最大值
				var setMaximumOfIncomeList = true;
				if(!Ext.isEmpty(halfYearIncomeList) && halfYearIncomeList.length > 0){
					for(var i = 0;i < halfYearIncomeList.length;i++){
						if(halfYearIncomeList[i].arriveIncome !== 0 || halfYearIncomeList[i].sendIncome !== 0){
							setMaximumOfIncomeList = false;
							break;
						}
					}
				}
				if(setMaximumOfIncomeList){
					Ext.getCmp("custInfoTabChartId").axes.map.left.maximum = 100;
				}
				Ext.getCmp("custInfoTabChartId").doComponentLayout();
				//格式化显示最近一次交易时间
				if(!Ext.isEmpty(json.customerMarketInfo.dealTime)){
					json.customerMarketInfo.dealTime = Ext.Date.format(new Date(json.customerMarketInfo.dealTime), 'Y-m-d');
					Ext.getCmp('dealTimeId').setValue(json.customerMarketInfo.dealTime);
				}
				Ext.getCmp('sendGoodsCycleId').setValue(json.customerMarketInfo.sendGoodsCycle+i18n('i18n.visualMarket.dayPerTime'));
				Ext.getCmp('dealInfoTabPanelForMemberId').enable();
			}
			// 回访页签数据
			Ext.getCmp('returnVisitListTabPanelForMemberId').enable();
			Ext.getCmp("returnVisitTabGridId").store.loadData(json.rvOpinionList);
		};
		var failureFn = function(json){
			if(Ext.isEmpty(json)){
				MessageUtil.showErrorMes(i18n('i18n.visualMarket.exceedTimeOfSearchCustDetail'));
			}
			else{
				MessageUtil.showErrorMes(json.message);
			}
		};
		var param={
			'customerMarketInfo.custType':selectCustType,
			'customerMarketInfo.custId':custId,
			'customerMarketInfo.psCustId':custId,
			'customerMarketInfo.deptId':deptId
		};
		MarkCustomerDetailView.prototype.searchCustIntegrationInfoVO(param, successFn, failureFn);
		
		
		var htmlToRender = document.getElementById("markCustDetailPanelID");
		//回调gis的方法，用以标签的显示
		eval(callbackFun)(htmlToRender);
	}
}

//回访客户按钮功能实现区
function returnVisitBtnFunction(){
	var successFn = function(json){
		var win=Ext.getCmp("CreateDevelopPopWindowId");//获取win
		if(!win){
			win=Ext.create('CreateReturnVisitWindow',{id:'CreateDevelopPopWindowId'});
		}
		
		//清空走货潜力表格
		Ext.getCmp('sendGoodsPontentialGrid').store.removeAll();
		//清空客户意见表格
		Ext.getCmp('customerOpinionGrid').store.removeAll();
		//清空跟踪时间和跟踪方式
		Ext.getCmp('schedule').setValue(null).disable();
		Ext.getCmp("ifparent").setValue('0');
		Ext.getCmp("way").reset();
		
	    var initData=new InitDataModel(json.returnVisit);
    	var customerInfoFormPanel = Ext.getCmp("customerInfoFormPanel").getForm();
    	customerInfoFormPanel.loadRecord(initData);
	 	var ScheduleMakeForm =  Ext.getCmp("scheduleMakeForm").getForm();
	 	ScheduleMakeForm.loadRecord(initData);
    	Ext.getCmp('scheType').setValue(scheduleType);
		win.show();
		
		document.getElementsByTagName("html")[0].style.overflowY="auto";
		document.getElementsByTagName("html")[0].style.overflowX="auto";
    };
	var failureFn = function(json){
		if(Ext.isEmpty(json)){
			MessageUtil.showErrorMes(i18n('i18n.visualMarket.exceedTimeOfLoadingData'));
		}
		else{
			MessageUtil.showErrorMes(json.message);
		}
	};
	
	
	//设置回访类型
	if(showCustType === 'MEMBER'){
		var scheduleType=MAINTAIN_TYPE;
		if(Ext.isEmpty(Ext.getCmp('linkManIdForReturnVisitPanel').getValue())){
			MessageUtil.showErrorMes(i18n('i18n.visualMarket.errorOfNullLinkmanId'));
			return false;
		}
		MarkCustomerDetailView.prototype.setRetureVisitByCust({
			'returnVisit.linkManId':Ext.getCmp('linkManIdForReturnVisitPanel').getValue(),
			'returnVisit.scheType':scheduleType,//维护
			'returnVisit.memberId':Ext.getCmp('custIdForReturnVisitPanel').getValue(),
		}, successFn, failureFn);
	}
	else{
		var scheduleType=DEVELOP_TYPE;
		if(Ext.isEmpty(Ext.getCmp('custIdForReturnVisitPanel').getValue())){
			MessageUtil.showErrorMes(i18n('i18n.visualMarket.errorOfNullPsCustId'));
			return false;
		}
		MarkCustomerDetailView.prototype.setRetureVisitByCust({
			'returnVisit.linkManId':Ext.getCmp('linkManIdForReturnVisitPanel').getValue(),
			'returnVisit.scheType':scheduleType,//开发
			'returnVisit.memberId':Ext.getCmp('custIdForReturnVisitPanel').getValue(),
		}, successFn, failureFn);
	}
}