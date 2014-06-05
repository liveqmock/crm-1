/**
 * ajax请求
 */
var requestJsonAjax = function(url, params, successFn, failFn, async) {
	Ext.Ajax.request({
				url : url,
				jsonData : params,
				async:async?true:false,
				success : function(response) {
					var result = Ext.decode(response.responseText);
					if (result.success) {
						successFn(result);
					} else {
						failFn(result);
					}
				},
				failure : function(response) {
					var result = Ext.decode(response.responseText);
					failFn(result);
				}
			});
};
Ext.onReady(function() {
	var custNumParams;// 用于导出文件时上传的参数
	var currentMonth = new Date().getMonth();
	var firstMonth = (currentMonth - 2) < 1
			? (currentMonth + 10)
			: (currentMonth - 2);
	firstMonth = firstMonth + '月';
	var secondMonth = (currentMonth - 1) < 1
			? (currentMonth + 11)
			: (currentMonth - 1);
	secondMonth = secondMonth + '月';
	var thirdMonth = currentMonth < 1 ? 12 : (currentMonth);
	thirdMonth = thirdMonth + '月';
	/**
	 * 查询表单
	 */
	Ext.define('SearchFrom', {
				extend : 'SearchFormPanel',
				layout : {
					type : 'table',
					columns : 4
				},
				height : 70,
				initComponent : function() {
					var me = this;
					me.items = me.getItems();
					me.bbar = me.getBbar();
					this.callParent();
				},
				getItems : function() {
					var me = this;
					return [{
								xtype : 'textfield',
								id : 'searchFormCustNum',
								maxLength : 100,
								alowBlank : false,
								fieldLabel : '客户编码',
								labelWidth : 60
							}]
				},
				getBbar : function() {
					var me = this;
					return [{
						xtype : 'button',
						text : '导出',
						handler : function() {
							if (!custNumParams) {
								MessageUtil.showMessage('请先查询出一个客户的信息,然后再进行导出');
								return;
							}
							var chooseFn = function(e) {
								var isAddMarketing;
								if (e == 'yes') {
									isAddMarketing = true;
								} else {
									isAddMarketing = false;
								}
								var url = '../custReport/downReportFile.action';
								window.location.href = url + '?custNum='
										+ custNumParams + '&isAddMarketing='
										+ isAddMarketing;
							}
							var msg = '您好,是否将该营销活动信息加入走货报告？'; 
							MessageUtil.showQuestionMes(msg,chooseFn);
						}
					}, {
						xtype : 'button',
						text : '查询',
						handler : function(btn) {
							var custNum = Ext.getCmp('searchFormCustNum')
									.getValue();
							if (!custNum) {
								MessageUtil.showMessage('您好,请输入客户编码!');
								return;
							}
							btn.setDisabled(true);
							setTimeout(function() {
										btn.setDisabled(false);
									}, 3000);
							var url = '../custReport/checkCustNum.action';
							var params = {
								custNum : custNum
							};
							var successFn = function() {
								custNumParams = custNum;
								Ext.data.StoreManager
										.lookup('shipmentAmountStoreId').load();
								Ext.data.StoreManager
										.lookup('productAmountStoreId').load();
								Ext.data.StoreManager
										.lookup('roadAmountStoreId').load();
								Ext.data.StoreManager
										.lookup('shipmentAgingStoreId').load();
								Ext.data.StoreManager
										.lookup('shipmentQualityStoreId')
										.load();
								Ext.data.StoreManager
										.lookup('shipmentQualityForChartStoreId')
										.load();
							}
							var failFn = function(json) {
								custNumParams = null;
								MessageUtil.showErrorMes(json
										? json.message
										: '未知异常');
							}
							requestJsonAjax(url, params, successFn, failFn, false);
						}
					}, {
						xtype : 'button',
						text : '重置',
						handler : function() {
							Ext.getCmp('SearchForm').getForm().reset();
						}
					}]
				}
			});
	/**
	 * 近三个月走货量表格
	 */
	Ext.define('ShipmentAmountGrid', {
				extend : 'SearchGridPanel',
				autoScroll : true,
				height : 200,
				initComponent : function() {
					var me = this;
					me.columns = me.getColumns();
					me.store = me.getGridStore();
					this.callParent();
					me.store.on('beforeload',
							function(store, operation, eOpts) {
								var custNum = Ext.getCmp('searchFormCustNum')
										.getValue();
								Ext.apply(operation, {
											params : {
												custNum : custNum
											}
										});
							});
				},
				getColumns : function() {
					var me = this;
					return [{
								header : '月份',
								align : 'center',
								width : 101,
								dataIndex : 'month'
							}, {
								header : '票数',
								align : 'center',
								dataIndex : 'deliverycount'
							}, {
								header : '重量',
								align : 'center',
								dataIndex : 'weight'
							}, {
								header : '金额',
								align : 'center',
								dataIndex : 'amount'
							}]
				},
				getGridStore : function() {
					if (Ext.data.StoreManager.lookup('shipmentAmountStoreId')) {
						return Ext.data.StoreManager
								.lookup('shipmentAmountStoreId');
					}
					return Ext.create('ShipmentAmountStore', {
								storeId : 'shipmentAmountStoreId'
							});
				}
			});
	/**
	 * 近三个月走货量图表的chart
	 */
	Ext.define('ShipmentAmountChart', {
				extend : 'Ext.chart.Chart',
				style : 'background:#fff',
				animate : true,
				shadow : true,
				height : 173,
				theme : 'Category1',
				// legend: {
				// position: 'right'
				// },
				initComponent : function() {
					var me = this;
					me.axes = me.getAxes();
					me.series = me.getSeries();
					this.callParent();
				},
				getAxes : function() {
					var me = this;
					var fieldsName;
					if (me.title == '票数') {
						fieldsName = 'deliverycount';
					} else if (me.title == '重量') {
						fieldsName = 'weight';
					} else if (me.title == '金额') {
						fieldsName = 'amount';
					}
					return [{
								type : 'Numeric',
								minimum : 0,
								position : 'left',
								fields : [fieldsName],
								// title: 'Y轴',
								minorTickSteps : 1,
								grid : {
									odd : {
										opacity : 1,
										fill : '#ddd',
										stroke : '#fff',
										'stroke-width' : 0.5
									}
								}
							}, {
								type : 'Category',
								position : 'bottom',
								fields : ['month']
								// title: 'X轴'
						}]
				},
				getSeries : function() {
					var me = this;
					var fieldsName;
					if (me.title == '票数') {
						fieldsName = 'deliverycount';
					} else if (me.title == '重量') {
						fieldsName = 'weight';
					} else if (me.title == '金额') {
						fieldsName = 'amount';
					}
					return [{
						type : 'line',
						highlight : {
							size : 7,
							radius : 7
						},
						axis : 'left',
						style : {
							stroke : '#f00',
							'stroke-width' : 1,
							fill : '#fff',
							opacity : 1
						},
						xField : 'month',
						yField : fieldsName,
						markerConfig : {
							type : 'circle',
							size : 4,
							radius : 4,
							fill : 'f00',// 圆点颜色
							'stroke-width' : 0
						}
					}]
				}
			});
	/**
	 * 近三个月走货量图表的tab
	 */
	Ext.define('ShipmentAmountTab', {
				extend : 'Ext.tab.Panel',
				initComponent : function() {
					var me = this;
					me.items = me.getItems();
					this.callParent();
				},
				getItems : function() {
					return [Ext.create('ShipmentAmountChart', {
								store : Ext.data.StoreManager
										.lookup('shipmentAmountStoreId'),
								title : '票数'
							}), Ext.create('ShipmentAmountChart', {
								store : Ext.data.StoreManager
										.lookup('shipmentAmountStoreId'),
								title : '重量'
							}), Ext.create('ShipmentAmountChart', {
								store : Ext.data.StoreManager
										.lookup('shipmentAmountStoreId'),
								title : '金额'
							})]
				}
			});
	/**
	 * 近三个月走货量表格及图表的panel
	 */
	Ext.define('ShipmentAmountPanle', {
				extend : 'Ext.panel.Panel',
				layout : 'hbox',
				defaults : {
					flex : 1
				},
				title : '大客户走货报告',
				items : [Ext.create('ShipmentAmountGrid'),
						Ext.create('ShipmentAmountTab')]
			});
	/**
	 * 近三个月产品货量grid
	 */
	Ext.define('ProductAmountGrid', {
				extend : 'SearchGridPanel',
				autoScroll : true,
				initComponent : function() {
					var me = this;
					me.columns = me.getColumns();
					me.store = me.getGridStore();
					this.callParent();
					me.store.on('beforeload',
							function(store, operation, eOpts) {
								var custNum = Ext.getCmp('searchFormCustNum')
										.getValue();
								Ext.apply(operation, {
											params : {
												custNum : custNum
											}
										});
							});
				},
				getColumns : function() {
					var me = this;
					return [{
								header : '',
								columns : [{
											header : '分组',
											width : 120,
											dataIndex : 'grouping'
										}]
							}, {
								header : firstMonth,
								align : 'center',
								columns : [{
											header : '票数',
											width : 50,
											dataIndex : 'firstMonthProductAmount',
											renderer : function(obj) {
												if (obj && obj.deliverycount) {
													return obj.deliverycount
												}
												return 0;
											}
										}, {
											header : '重量',
											width : 50,
											dataIndex : 'firstMonthProductAmount',
											renderer : function(obj) {
												if (obj && obj.weight) {
													return obj.weight
												}
												return 0;
											}
										}, {
											header : '金额',
											width : 50,
											dataIndex : 'firstMonthProductAmount',
											renderer : function(obj) {
												if (obj && obj.amount) {
													return obj.amount
												}
												return 0;
											}
										}, {
											header : '重量占比',
											width : 75,
											dataIndex : 'firstMonthProductAmount',
											renderer : function(obj) {
												if (obj && obj.weightRate) {
													return obj.weightRate
												}
												return 0;
											}
										}]
							}, {
								header : secondMonth,
								align : 'center',
								dataIndex : 'a',
								columns : [{
											header : '票数',
											width : 50,
											dataIndex : 'secondMonthProductAmount',
											renderer : function(obj) {
												if (obj && obj.deliverycount) {
													return obj.deliverycount
												}
												return 0;
											}
										}, {
											header : '重量',
											width : 50,
											dataIndex : 'secondMonthProductAmount',
											renderer : function(obj) {
												if (obj && obj.weight) {
													return obj.weight
												}
												return 0;
											}
										}, {
											header : '金额',
											width : 50,
											dataIndex : 'secondMonthProductAmount',
											renderer : function(obj) {
												if (obj && obj.amount) {
													return obj.amount
												}
												return 0;
											}
										}, {
											header : '重量占比',
											width : 75,
											dataIndex : 'secondMonthProductAmount',
											renderer : function(obj) {
												if (obj && obj.weightRate) {
													return obj.weightRate
												}
												return 0;
											}
										}]
							}, {
								header : thirdMonth,
								align : 'center',
								dataIndex : 'a',
								columns : [{
											header : '票数',
											width : 50,
											dataIndex : 'thirdMonthProductAmount',
											renderer : function(obj) {
												if (obj && obj.deliverycount) {
													return obj.deliverycount
												}
												return 0;
											}
										}, {
											header : '重量',
											width : 50,
											dataIndex : 'thirdMonthProductAmount',
											renderer : function(obj) {
												if (obj && obj.weight) {
													return obj.weight
												}
												return 0;
											}
										}, {
											header : '金额',
											width : 50,
											dataIndex : 'thirdMonthProductAmount',
											renderer : function(obj) {
												if (obj && obj.amount) {
													return obj.amount
												}
												return 0;
											}
										}, {
											header : '重量占比',
											width : 80,
											dataIndex : 'thirdMonthProductAmount',
											renderer : function(obj) {
												if (obj && obj.weightRate) {
													return obj.weightRate
												}
												return 0;
											}
										}]
							}]
				},
				getGridStore : function() {
					return new Ext.create('ProductAmountStore', {
								storeId : 'productAmountStoreId'
							});
				}
			});
	/**
	 * 近三个月线路货量表格
	 */
	Ext.define('RoadAmountGrid', {
				extend : 'SearchGridPanel',
				autoScroll : true,
				initComponent : function() {
					var me = this;
					me.columns = me.getColumns();
					me.store = me.getGridStore();
					this.callParent();
					me.store.on('beforeload',
							function(store, operation, eOpts) {
								var custNum = Ext.getCmp('searchFormCustNum')
										.getValue();
								Ext.apply(operation, {
											params : {
												custNum : custNum
											}
										});
							});
				},
				getColumns : function() {
					var me = this;
					return [{
								header : '',
								columns : [{
											header : '分组',
											width : 120,
											dataIndex : 'grouping'
										}]
							}, {
								header : firstMonth,
								align : 'center',
								dataIndex : 'a',
								columns : [{
											header : '票数',
											width : 50,
											dataIndex : 'firstMonthRoadAmount',
											renderer : function(obj) {
												if (obj && obj.deliverycount) {
													return obj.deliverycount
												}
												return 0;
											}
										}, {
											header : '重量',
											width : 50,
											dataIndex : 'firstMonthRoadAmount',
											renderer : function(obj) {
												if (obj && obj.weight) {
													return obj.weight
												}
												return 0;
											}
										}, {
											header : '金额',
											width : 50,
											dataIndex : 'firstMonthRoadAmount',
											renderer : function(obj) {
												if (obj && obj.amount) {
													return obj.amount
												}
												return 0;
											}
										}, {
											header : '重量占比',
											width : 75,
											dataIndex : 'firstMonthRoadAmount',
											renderer : function(obj) {
												if (obj && obj.weightRate) {
													return obj.weightRate
												}
												return 0;
											}
										}]
							}, {
								header : secondMonth,
								align : 'center',
								dataIndex : 'a',
								columns : [{
											header : '票数',
											width : 50,
											dataIndex : 'secondMonthRoadAmount',
											renderer : function(obj) {
												if (obj && obj.deliverycount) {
													return obj.deliverycount
												}
												return 0;
											}
										}, {
											header : '重量',
											width : 50,
											dataIndex : 'secondMonthRoadAmount',
											renderer : function(obj) {
												if (obj && obj.weight) {
													return obj.weight
												}
												return 0;
											}
										}, {
											header : '金额',
											width : 50,
											dataIndex : 'secondMonthRoadAmount',
											renderer : function(obj) {
												if (obj && obj.amount) {
													return obj.amount
												}
												return 0;
											}
										}, {
											header : '重量占比',
											width : 75,
											dataIndex : 'secondMonthRoadAmount',
											renderer : function(obj) {
												if (obj && obj.weightRate) {
													return obj.weightRate
												}
												return 0;
											}
										}]
							}, {
								header : thirdMonth,
								align : 'center',
								dataIndex : 'a',
								columns : [{
											header : '票数',
											width : 50,
											dataIndex : 'thirdMonthRoadAmount',
											renderer : function(obj) {
												if (obj && obj.deliverycount) {
													return obj.deliverycount
												}
												return 0;
											}
										}, {
											header : '重量',
											width : 50,
											dataIndex : 'thirdMonthRoadAmount',
											renderer : function(obj) {
												if (obj && obj.weight) {
													return obj.weight
												}
												return 0;
											}
										}, {
											header : '金额',
											width : 50,
											dataIndex : 'thirdMonthRoadAmount',
											renderer : function(obj) {
												if (obj && obj.amount) {
													return obj.amount
												}
												return 0;
											}
										}, {
											header : '重量占比',
											width : 80,
											dataIndex : 'thirdMonthRoadAmount',
											renderer : function(obj) {
												if (obj && obj.weightRate) {
													return obj.weightRate
												}
												return 0;
											}
										}]
							}]
				},
				getGridStore : function() {
					return new Ext.create('RoadAmountStore', {
								storeId : 'roadAmountStoreId'
							});;
				}
			});
	/**
	 * 近三个月走货时效
	 */
	Ext.define('ShipmentAgingPanel', {
				extend : 'SearchGridPanel',
				autoScroll : true,
				initComponent : function() {
					var me = this;
					me.columns = me.getColumns();
					me.store = me.getGridStore();
					this.callParent();
					me.store.on('beforeload',
							function(store, operation, eOpts) {
								var custNum = Ext.getCmp('searchFormCustNum')
										.getValue();
								Ext.apply(operation, {
											params : {
												custNum : custNum
											}
										});
							});
				},
				getColumns : function() {
					var me = this;
					return [{
								header : '',
								columns : [{
											header : '分组',
											width : 120,
											dataIndex : 'grouping'
										}]
							}, {
								header : '精准卡航',
								align : 'center',
								dataIndex : 'a',
								columns : [{
											header : firstMonth,
											width : 50,
											dataIndex : 'firstAgingKH',
											renderer : function(v) {
												return v ? v : 0;

											}
										}, {
											header : secondMonth,
											dataIndex : 'secondAgingKH',
											width : 50,
											renderer : function(v) {
												return v ? v : 0;

											}
										}, {
											header : thirdMonth,
											dataIndex : 'thirdAgingKH',
											width : 50,
											renderer : function(v) {
												return v ? v : 0;

											}
										}]
							}, {
								header : '精准城运',
								align : 'center',
								dataIndex : 'a',
								columns : [{
											header : firstMonth,
											dataIndex : 'firstAgingCY',
											width : 50,
											renderer : function(v) {
												return v ? v : 0;

											}
										}, {
											header : secondMonth,
											dataIndex : 'secondAgingCY',
											width : 50,
											renderer : function(v) {
												return v ? v : 0;

											}
										}, {
											header : thirdMonth,
											dataIndex : 'thirdAgingCY',
											width : 50,
											renderer : function(v) {
												return v ? v : 0;

											}
										}]
							}, {
								header : '精准空运',
								align : 'center',
								dataIndex : 'a',
								columns : [{
											header : firstMonth,
											dataIndex : 'firstAgingKY',
											width : 50,
											renderer : function(v) {
												return v ? v : 0;

											}
										}, {
											header : secondMonth,
											dataIndex : 'secondAgingKY',
											width : 50,
											renderer : function(v) {
												return v ? v : 0;

											}
										}, {
											header : thirdMonth,
											dataIndex : 'thirdAgingKY',
											width : 50,
											renderer : function(v) {
												return v ? v : 0;

											}
										}]
							}, {
								header : '精准汽运(长)',
								align : 'center',
								dataIndex : 'a',
								columns : [{
											header : firstMonth,
											dataIndex : 'firstAgingQYC',
											width : 50,
											renderer : function(v) {
												return v ? v : 0;

											}
										}, {
											header : secondMonth,
											dataIndex : 'secondAgingQYC',
											width : 50,
											renderer : function(v) {
												return v ? v : 0;

											}
										}, {
											header : thirdMonth,
											dataIndex : 'thirdAgingQYC',
											width : 50,
											renderer : function(v) {
												return v ? v : 0;

											}
										}]
							}, {
								header : '精准汽运(短)',
								align : 'center',
								dataIndex : 'a',
								columns : [{
											header : firstMonth,
											dataIndex : 'firstAgingQYD',
											width : 50,
											renderer : function(v) {
												return v ? v : 0;

											}
										}, {
											header : secondMonth,
											dataIndex : 'secondAgingQYD',
											width : 50,
											renderer : function(v) {
												return v ? v : 0;

											}
										}, {
											header : thirdMonth,
											dataIndex : 'thirdAgingQYD',
											width : 50,
											renderer : function(v) {
												return v ? v : 0;

											}
										}]
							}, {
								header : '经济快递',
								align : 'center',
								dataIndex : 'a',
								columns : [{
											header : firstMonth,
											dataIndex : 'firstAgingKD',
											width : 50,
											renderer : function(v) {
												return v ? v : 0;

											}
										}, {
											header : secondMonth,
											dataIndex : 'secondAgingKD',
											width : 50,
											renderer : function(v) {
												return v ? v : 0;

											}
										}, {
											header : thirdMonth,
											dataIndex : 'thirdAgingKD',
											width : 50,
											renderer : function(v) {
												return v ? v : 0;

											}
										}]
							}
							
//							, {
//								header : '整车',
//								align : 'center',
//								dataIndex : 'a',
//								columns : [{
//											header : firstMonth,
//											dataIndex : 'firstAgingZC',
//											width : 50,
//											renderer : function(v) {
//												return v ? v : 0;
//
//											}
//										}, {
//											header : secondMonth,
//											dataIndex : 'secondAgingZC',
//											width : 50,
//											renderer : function(v) {
//												return v ? v : 0;
//
//											}
//										}, {
//											header : thirdMonth,
//											dataIndex : 'thirdAgingZC',
//											width : 50,
//											renderer : function(v) {
//												return v ? v : 0;
//
//											}
//										}]
//							}
							]
				},
				getGridStore : function() {
					return Ext.create('ShipmentAgingStore', {
								storeId : 'shipmentAgingStoreId'
							});;
				}

			});
	/**
	 * 近三个月走货质量表格
	 */
	Ext.define('ShipmentQualityGrid', {
				extend : 'SearchGridPanel',
				autoScroll : true,
				height : 200,
				initComponent : function() {
					var me = this;
					me.columns = me.getColumns();
					me.store = me.getGridStore();
					this.callParent();
					me.store.on('beforeload',
							function(store, operation, eOpts) {
								var custNum = Ext.getCmp('searchFormCustNum')
										.getValue();
								Ext.apply(operation, {
											params : {
												custNum : custNum
											}
										});
							});
				},
				getColumns : function() {
					var me = this;
					return [{
								header : '分组',
								align : 'center',
								width : 120,
								dataIndex : 'grouping'

							}, {
								header : firstMonth,
								align : 'center',
								width : 93,
								dataIndex : 'firstQuality',
								renderer : function(val) {
									if (val) {
										return val;
									}
									return 0;
								}
							}, {
								header : secondMonth,
								width : 93,
								align : 'center',
								dataIndex : 'secondQuality',
								renderer : function(val) {
									if (val) {
										return val;
									}
									return 0;
								}
							}, {
								header : thirdMonth,
								width : 95,
								align : 'center',
								dataIndex : 'thirdQuality',
								renderer : function(val) {
									if (val) {
										return val;
									}
									return 0;
								}
							}]
				},
				getGridStore : function() {
					return Ext.create('ShipmentQualityStore', {
								storeId : 'shipmentQualityStoreId'
							});
				}
			});
	/**
	 * 近三个月走货质量图表
	 */
	Ext.define('ShipmentQualityChart', {
				extend : 'Ext.chart.Chart',
				style : 'background:#fff',
				animate : true,
				shadow : true,
				height : 173,
				theme : 'Category1',
				// legend: {
				// position: 'right'
				// },
				axes : [{
							type : 'Numeric',
							minimum : 0,
							position : 'left',
							fields : ['abSignNormalCount', 'recompenseCount'],
							// title: 'Y轴',
							minorTickSteps : 1,
							grid : {
								odd : {
									opacity : 1,
									fill : '#ddd',
									stroke : '#bbb',
									'stroke-width' : 0.5
								}
							}
						}, {
							type : 'Category',
							position : 'bottom',
							fields : ['month']
							// title: 'X轴'
					}],
				series : [{
					type : 'line',
					highlight : {
						size : 7,
						radius : 7
					},
					axis : 'left',
					smooth : true,
					xField : 'month',
					yField : ['abSignNormalCount'],
					markerConfig : {
						type : 'circle',
						size : 4,
						radius : 4,
						'stroke-width' : 0
					},
					tips : {
						trackMouse : true,
						width : 50,
						style : 'background:#ffffff',
						border : false,
						height : 28,
						renderer : function(storeItem, item) {
							return this.setTitle(storeItem
									.get('abSignNormalCount') ? storeItem
									.get('abSignNormalCount') : '0')
						}
					}
				}, {
					type : 'line',
					highlight : {
						size : 7,
						radius : 7
					},
					axis : 'left',
					smooth : true,
					// fill: true,
					xField : 'month',
					yField : ['recompenseCount'],
					markerConfig : {
						type : 'circle',
						size : 4,
						radius : 4,
						'stroke-width' : 0
					},
					tips : {
						trackMouse : true,
						width : 50,
						style : 'background:#ffffff',
						border : false,
						height : 28,
						renderer : function(storeItem, item) {
							return this.setTitle(storeItem
									.get('recompenseCount') ? storeItem
									.get('recompenseCount') : '0')
						}
					}
				}]
			});
	/**
	 * 近三个月走货质量panel
	 */
	Ext.define('ShipmentQualityPanle', {
		extend : 'Ext.panel.Panel',
		layout : 'hbox',
		defaults : {
			flex : 1
		},
		items : [Ext.create('ShipmentQualityGrid'),
				Ext.create('ShipmentQualityChart', {
							store : Ext.create('ShipmentQualityForChartStore',
									{
										storeId : 'shipmentQualityForChartStoreId',
										listeners : {
											beforeload : function(store,
													operation, eOpts) {
												var custNum = Ext
														.getCmp('searchFormCustNum')
														.getValue();
												Ext.apply(operation, {
															params : {
																custNum : custNum
															}
														});
											}
										}
									})
						})]
	});
	// 查询表格
	var searchForm = Ext.create('SearchFrom', {
				id:'SearchForm',
				width : '100%',
				height : 70
			});
	// 近三个月走货量
	var shipmentAmountPanle = Ext.create('ShipmentAmountPanle', {
				height : 225,
				width : '100%',
				title : '近三个月走货量'
			});
	// 近三个月产品货量
	var productAmountGrid = Ext.create('ProductAmountGrid', {
				height : 240,
				width : '100%',
				title : '近三个月产品货量'

			});
	// 近三个月线路货量
	var roadAmountGrid = Ext.create('RoadAmountGrid', {
				height : 240,
				width : '100%',
				title : '近三个月线路货量'
			});
	// 近三个月走货时效
	var shipmentAgingPanel = Ext.create('ShipmentAgingPanel', {
				height : 240,
				width : '100%',
				title : '近三个月走时效'
			});
	// 近三个月走货质量
	var shipmentQualityPanle = Ext.create('ShipmentQualityPanle', {
				height : 225,
				width : '100%',
				title : '近三个月走货质量'
			});
	var view = Ext.create('Ext.container.Viewport', {
				autoScroll : true,
				layout : 'column',
				width : 400,
				items : [searchForm, shipmentAmountPanle, productAmountGrid,
						roadAmountGrid, shipmentAgingPanel,
						shipmentQualityPanle]
			});
});