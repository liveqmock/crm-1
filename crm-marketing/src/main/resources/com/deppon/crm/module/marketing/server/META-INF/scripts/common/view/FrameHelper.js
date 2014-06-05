
/**.
 * <p>
 * 动态加载tabPanel
 * </p>
 * @author  张登
 * @时间    2012-03-19
 */
function initTabpanel(id,text,url){
    var tabs = Ext.getCmp('tabPanel');
    if (tabs) {
        var n = tabs.getComponent(id);
        //判断是否已经打开该面板
        if (!n) {   
            n = tabs.add({
                'id': id,
                'title': text,
                layout: 'fit',
                html: '<iframe id="'+id+'" src="'+url+'" style="height:100%;width:100%;" frameborder="0"></iframe>'
            });
        }
      tabs.setActiveTab(n);
    }
};


Ext
		.onReady(function() {
			var store = Ext.create('Ext.data.TreeStore', {
				root : {
					expanded : true,
					text : '123',
					children : [ {
						text : "客户开发",
						expanded : true,
						children : [ {
							id : '1',
							text : i18n('i18n.FrameHelper.developmentPlanManagement'),
							leaf : true
						}, {
							id : '2',
							text : i18n('i18n.FrameHelper.scheduleNew'),
							leaf : true
						}, {
							id : '3',
							text : i18n('i18n.FrameHelper.scheduleManagement'),
							leaf : true
						}, {
							id : '4',
							text : i18n('i18n.FrameHelper.ReturnVisitEntryInquires'),
							leaf : true
						}, {
							id : '5',
							text : i18n('i18n.FrameHelper.monitoringDevelopmentPlan'),
							leaf : true
						}, {
							id : '6',
							text : i18n('i18n.FrameHelper.maintenancePlanManagement'),
							leaf : true
						}, {
							id : '7',
							text : i18n('i18n.FrameHelper.maintenanceScheduleNew'),
							leaf : true
						},{
							id : '12',
							text : i18n('i18n.FrameHelper.theLeavePeriodicTable'),
							leaf : true
						},{
							id : '13',
							text : i18n('i18n.FrameHelper.theArrivalPeriodicTable'),
							leaf : true
						},{
							id : '11',
							text : i18n('i18n.FrameHelper.maintenanceScheduleManagement'),
							leaf : true
						},{
							id : '8',
							text : i18n('i18n.FrameHelper.monitoringMaintenancePlan'),
							leaf : true
						},{
							id : '9',
							text : i18n('i18n.FrameHelper.5kmMap'),
							leaf : true
						}, {
							id : '10',
							text : i18n('i18n.FrameHelper.customerGroupManagement'),
							leaf : true
						}]
					} ]
				}
			});
			var treePanel = Ext.create('Ext.tree.TreePanel', {
				id : 'treeID',
				height : 650,
				store : store,
				rootVisible : false,
				listeners : {
					itemclick : function(view, record) {
						if (record.get('leaf')) {
							if (record.raw.id == '1') {
								initTabpanel('developplanemanageTabId','开发计划管理','/crm-marketing/marketing/developplanmanage.action');
							}
						}
						if (record.get('leaf')) {
							if (record.raw.id == '2') {
								initTabpanel('developScheduleAddId','开发日程新增','/crm-marketing/marketing/developScheduleAdd.action');
							}
						}
						if (record.get('leaf')) {
							if (record.raw.id == '3') {
								initTabpanel('developschedulemanageTabId','开发日程管理','/crm-marketing/marketing/developschedulemanage.action');
							}
						}
						if (record.get('leaf')) {
							if (record.raw.id == '4') {
								initTabpanel('returnvisitTabId','回访录入查询','/crm-marketing/marketing/returnVisit.action');
							}
						}
						if (record.get('leaf')) {
							if (record.raw.id == '5') {
								initTabpanel('monitorManageId','监控开发计划','/crm-marketing/marketing/monitorManage.action');
							}
						}
						if (record.get('leaf')) {
							if (record.raw.id == '6') {
								initTabpanel('maintainpPlanManageId','维护计划管理','/crm-marketing/marketing/maintainpPlanManage.action');
							}
						}
						if (record.get('leaf')) {
							if (record.raw.id == '7') {
								initTabpanel('maintainpScheduleSaveId','维护日程新增','/crm-marketing/marketing/maintainpScheduleSave.action');
							}
						}
						if (record.get('leaf')) {
							if (record.raw.id == '8') {
								initTabpanel('maintainpMonitorManageId','监控维护计划','/crm-marketing/marketing/maintainpMonitorManage.action');
							}
						}
						if (record.get('leaf')) {
							if (record.raw.id == '9') {
								initTabpanel('mapManageId','五公里地图','/crm-marketing/marketing/map.action');
							}
						}
						if (record.get('leaf')) {
							if (record.raw.id == '10') {
								initTabpanel('customerGroupId','客户分组管理','/crm-marketing/marketing/customerGroup.action');
							}
						}
						if (record.get('leaf')) {
							if (record.raw.id == '11') {
								initTabpanel('maintainpScheduleManageId','维护日程管理','/crm-marketing/marketing/maintainpScheduleManage.action');
							}
						}
						if (record.get('leaf')) {
							if (record.raw.id == '12') {
								initTabpanel('deliveryCycleMonitorManageId','发货周期表','/crm-marketing/marketing/deliveryCycle.action');
							}
						}
						if (record.get('leaf')) {
							if (record.raw.id == '13') {
								initTabpanel('arrivalCycleMonitorManageId','到货周期表','/crm-marketing/marketing/arrivalCycle.action');
							}
						}
					}
				}
			});
			var tabPanel = Ext.create('Ext.tab.Panel', {
				id:'tabPanel',
				region : 'center',
				items : [ {
					title : i18n('i18n.FrameHelper.welcome'),
					id : 'welcom',
					html : 'hello!'
				} ]
			});
			Ext
					.create(
							'Ext.container.Viewport',
							{
								autoDestroy : true,
								renderto : Ext.getBody(),
								layout : 'border',
								items : [
										{
											region : 'north',
											xtype : 'panel',
											cls : 'banner',
											height : 81,
											layout : 'border',
											items : [
													{
														region : 'west',
														xtype : 'panel',
														border : false,

														html : '<div  style="margin-bottom:0%; margin-top:0%; width:300px;" >',
														items : []
													},
													{
														region : 'center',
														xtype : 'panel',
														border : false,
														items : [
																{
																	xtype : 'panel',
																	border : false,
																	width : 300,
																	html : '<div  style=" margin-top:37px;" >',
																	items : []
																}]
													} ]
										// bodyStyle : {
										// background : '#403b64'
										// },
										}, {
											region : 'west',
											xtype : 'panel',
											id : 'treelayoutID',
											collapsible : true,
											// border : '0 0 0 0',

											width : 177,
											minWidth : 177,
											maxWidth : 177,
											split : true,
											items : [ treePanel ]
										}, tabPanel ]
							});
		});


