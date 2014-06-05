/**
 * 
 * <p>
 * Description:可视化营销页面<br />
 * </p>
 * @title mapView.js
 * @author roy
 * @version 0.1 2013-4-12
 */
//是否全屏
var isFullScreen=false;
//标识四个查询条件的状态
var tradeTime='';
var monthGoodCounts='';
var createTime='';
var revisitTime='';
//传递到后台的排序类型
var btnSortType=null;
var btnSortMark=null;
//标记查询的客户类型，包括固定客户、潜客、散客
var showCustType = null;
//制定计划要用到的客户信息集合
var custInfoForMakePlan = [];
//客户信息list
var markCustInfos=null;
//记录当前查询页面显示客户标注的类型：客户类型视图viewTypeOfSelectResult = CUSTVIEW，回访类型视图viewTypeOfSelectResult = RETURNVISITVIEW
var viewTypeOfSelectResult = null;
//制定计划中要使用的是否营业部字段
var isBusinessDept = '0';
//记录当前登陆user的部门是否为营业部
var currentIsBusinessDept="";
//查询结果store
var customerMarketInfoStore=null;
//标记单击客户名称，是否会向后端发出查询客户符窗信息的请求
var clickToShowMarkCustDetail = true;
//标记单击详情，是否会向后端发出查询客户详情信息的请求
var clickToShowCustDetailInfoWindow = true;
//制定计划时需要使用的变量
var deptIdForMakePlan = '';
//标记查询的客户等级或是客户类型，用以切换到回访状态视图时查询客户浮窗信息
var custDegreeOrCustType = '';
//单击查询按钮时，需要往后台传递的参数，用以保证单击排序按钮时传向后台查询的参数是一致的
var searchConditionParam = null;
//设置一个全局变量，用以定义当用户点击“标注”按钮的时候，是否向后台发送请求
var clickToShowMarkForCustomer = true;
//设置一个全局变量，用以定义当用户点击“测距”按钮的时候，是否向后台发送请求
var clickToShowPathForCustomer = true;
//设置一个全局变量，用以计算加载查询列表的次数
var numberOfClickSearchButton = 0;
Ext.onReady(function(){
	//初始化提示条
    Ext.QuickTips.init();
	//加载数据字典
	var keys = [
	//地图标记
		'MAP_MAKER_STATUS',
		// 行业
 		'TRADE',
 		// 客户来源
 		'CUST_SOURCE',
 		// 商机状态
 		'BUSINESS_STATUS',
 		// 合作物流
 		'COOPERATION_LOGISTICS',
 		// 合作意向
 		'COOPERATION_INTENTION',
 		// 货量潜力
 		'CARGO_POTENTIAL',
 		// 开发状态
 		'DEVELOP_STATUS',
 		//公司规模
 		'FIRM_SIZE',
 		//公司性质
 		'COMP_NATURE',
 		//客户属性
 		'CUSTOMER_NATURE',
 		//客户性质
 		'CUSTOMER_TYPE',
 		//散客类型
 		'SCATTERCUST_TYPE',
 		//散客 账号性质
 		'ACCOUNT_NATURE_SC',
 		// 目标级别i18n('i18n.ChangeContactAffiliatedRelationView.nowLevelCustLevel'),会员等级
		'MEMBER_GRADE',
		// 客户潜力类型
		'CUST_POTENTIAL',
		// 信用等级
		'CREDIT_GRADE',
		// 地址类型
		'ADDRESS_TYPE',
		// 联系人类型 已取消
		// 物流决定权
		'LOGIST_DECI',
		// 付款方式
		'PAY_WAY',
		// 来源渠道',偏好渠道
		'PREFERENCE_CHANNEL',
		// 偏好服务
		'PREFERENCE_SERVICE',
		// 发票要求
		'BILL_REQUIRE',
		//账户性质
		'ACCOUNT_NATURE',
		//账户用途
		'ACCOUNT_USE',
		//性别
		'GENDER',
		//是否接受营销信息
		'MARKETINFO',
		//联系人证件类型
		'CARDTYPECON',
		//税务登记号证件类型
		'CARDTYPECUST',
		//证件类型新增和修改(大区总以上权限)
		'CARDTYPECON_NOTVIEW',
		//证件类型新增和修改
		'CARDTYPECON_AU'
	];
	//初始化数据字典
	initDataDictionary(keys);
	//初始化用户和部门信息
	initDeptAndUserInfo();
	//显示会员修改 内容描述
	new ModifyMemberControl().getModifyMember();
	//查询客户Form
	Ext.define('QueryCustomerForm',{
		extend:'SearchFormPanel',
		layout:{
			type:'table',
			columns:14
		},
		defaults:{
			labelWidth:78,
			labelAlign:'left'
		},
		items:[{
			fieldLabel : i18n('i18n.PotentialCustManagerView.deptName'), //部门
			name:'deptName',
			disabled:true,
			xtype:'combo',
			id:'deptId',
            maxLength:50,
			store:Ext.create('DeptStore',{id:'queryDeptStoreId',
				listeners:{
					beforeload:function(store, operation, eOpts){
						Ext.apply(operation,{
							params : {
								'deptName':Ext.getCmp("QueryCustomerFormId").getForm().findField("deptName").getRawValue()
								}
							}
						);	
					}
				}
			}),
			colspan:3,
			allowBlank :false,
			triggerAction : 'all',
			displayField:'deptName',
			valueField:'id',
			//现在没办法取到第一次的标杆编码
			standardCode:User.standardCode,//标杆编码
			forceSelection :true,
			hideTrigger:false,
			emptyText:i18n('i18n.visualMarket.inputKeyvalueOfDept'),
			pageSize: 10,
			minChars:2,
			queryDelay:800,
			listConfig: {
  	        	minWidth :300,
  	            getInnerTpl: function() {
  	            	 return '{deptName}';
  	            }
  	        },
			listeners:{
				change:function(){
					DButil.comboSelsct;
				},
				select:function(t,records){
					t.standardCode = records[0].get('standardCode');
					var form = Ext.getCmp('QueryCustomerFormId');
					form.searchCustLabel(form);
				}
			}
		},{ //客户
			fieldLabel:i18n('i18n.fiveKilometreMap.customerName'), //客户名称
			xtype : "membersearchcombox",
			labelWidth:60,
            maxLength:170,
			queryMode : "local",
			editable:false,
			id : "searchConditionCombo",
			setValueComeBack : function(memberRecord, addressRecord) {
				this.setValue(memberRecord.get('custNum'));
			},
			displayField : "custName",
			valueField : "custNum",
			name:'customerName',
			id:'customerName',
			maxLength : 170,
			width:175,
			colspan:2,
			maxLengthText : i18n('i18n.developPlan.maxLength')+170
		},{//地图标记
			xtype:'combo',
			name:'mapMaker',
			id:'mapMakerId',
			labelWidth:60,
			fieldLabel:i18n('i18n.visualMarket.mapMark'),
			queryModel:'local',
			colspan:6,
			width:130,
			value:'ALL',
			store:getDataDictionaryByName(DataDictionary,'MAP_MAKER_STATUS'),
			displayField:'codeDesc',
			valueField:'code',
			forceSelection :true,
			editable:false,
			listeners:{
				change:DButil.comboSelsct
			}
		},{//创建时间开始时间
			fieldLabel : i18n('i18n.developPlan.createStartTime'),
			colspan :2,
			labelWidth:60,
			xtype : 'datefield',
			width:155,
			format : 'Y-m-d',
			name : 'createBeginTime',
			id:'createBeginTime',
			maxValue:new Date()
		},{//创建时间结束时间
			fieldLabel : '-',
			labelWidth:5,
			width:100,
			colspan : 2,
			labelSeparator:'',
			xtype : 'datefield',
			format : 'Y-m-d',
			name : 'createEndTime',
			id:'createEndTime',
			maxValue:new Date()
		},{ //客户类型
			xtype:'checkboxgroup',
	        fieldLabel: i18n('i18n.MemberCustEditView.custType'),
	        id:'customerTypeId',
	        layout:'hbox',
	        width:470,
	        colspan:7,
	        items: [{ //普通客户
	        	boxLabel: i18n('i18n.visualMarket.normal'), 
	        	checked:true,
	        	name: 'customerType',
	        	id:'customerNORMAL',
	        	inputValue: 'NORMAL',
	        	listeners:{
	            	change:function(t){
	            		if(t.getValue()==true){
	            			Ext.getCmp("customerPC_CUSTOMER").setValue(false);
	        				Ext.getCmp("customerSC_CUSTOMER").setValue(false);
	        				
	        				Ext.getCmp("incomeProgressId").show();
	        				Ext.getCmp("extendedTimeId").show();
	        				Ext.getCmp("customTradeId").show();
	        				Ext.getCmp("goodsPotentialId").hide();
	        				Ext.getCmp("customerSourceId").hide();
	        				Ext.getCmp("monthIncomeId").hide();
	        				Ext.getCmp("goodsTimesId").hide();
	        				
	        				var queryFormPanel = Ext.getCmp('QueryCustomerFormId');
	        				var queryForm = queryFormPanel.getForm();	
	        				var goodsPotential=queryForm.findField('goodsPotentialId').getChecked();
		              		for(i=1;i<goodsPotential.length;i++){
		              			goodsPotential[i].setValue(false);
		              		}
		              		var customerSource=queryForm.findField('customerSourceId').getChecked();
		              		for(i=1;i<customerSource.length;i++){
		              			customerSource[i].setValue(false);
		              		}
		              		var monthIncome=queryForm.findField('monthIncomeId').getChecked();
		              		for(i=1;i<monthIncome.length;i++){
		              			monthIncome[i].setValue(false);
		              		}
		              		var goodsTimes=queryForm.findField('goodsTimesId').getChecked();
		              		for(i=1;i<goodsTimes.length;i++){
		              			goodsTimes[i].setValue(false);
		              		}
	        				
    						Ext.getCmp("monthIncomeUnlimitId").setValue(true);
	        				Ext.getCmp("goodsTimesUnlimitId").setValue(true);
	        				
	        				Ext.getCmp("goodsPotentialUnlimitId").setValue(true);
	        				Ext.getCmp("customerSourceUnlimitId").setValue(true);
	            		}
	            	}
	            }
	        },{ //黄金客户
	        	boxLabel: i18n('i18n.visualMarket.gold'), 
	        	checked:true, 
	        	name: 'customerType',
	        	id:'customerGOLD',
	        	inputValue: 'GOLD',
        		listeners:{
	            	change:function(t){
	            		if(t.getValue()==true){
	            			Ext.getCmp("customerPC_CUSTOMER").setValue(false);
	        				Ext.getCmp("customerSC_CUSTOMER").setValue(false);
	        				
	        				Ext.getCmp("incomeProgressId").show();
	        				Ext.getCmp("extendedTimeId").show();
	        				Ext.getCmp("customTradeId").show();
	        				Ext.getCmp("goodsPotentialId").hide();
	        				Ext.getCmp("customerSourceId").hide();
	        				Ext.getCmp("monthIncomeId").hide();
	        				Ext.getCmp("goodsTimesId").hide();
	        				
			        		var queryFormPanel = Ext.getCmp('QueryCustomerFormId');
			        		var queryForm = queryFormPanel.getForm();	
	        				var goodsPotential=queryForm.findField('goodsPotentialId').getChecked();
		              		for(i=1;i<goodsPotential.length;i++){
		              			goodsPotential[i].setValue(false);
		              		}
		              		var customerSource=queryForm.findField('customerSourceId').getChecked();
		              		for(i=1;i<customerSource.length;i++){
		              			customerSource[i].setValue(false);
		              		}
		              		var monthIncome=queryForm.findField('monthIncomeId').getChecked();
		              		for(i=1;i<monthIncome.length;i++){
		              			monthIncome[i].setValue(false);
		              		}
		              		var goodsTimes=queryForm.findField('goodsTimesId').getChecked();
		              		for(i=1;i<goodsTimes.length;i++){
		              			goodsTimes[i].setValue(false);
		              		}
	        				
    						Ext.getCmp("monthIncomeUnlimitId").setValue(true);
	        				Ext.getCmp("goodsTimesUnlimitId").setValue(true);
	        				
	        				Ext.getCmp("goodsPotentialUnlimitId").setValue(true);
	        				Ext.getCmp("customerSourceUnlimitId").setValue(true);
	            		}
	            	}
        		}
	        },{ //铂金客户
	        	boxLabel: i18n('i18n.visualMarket.plutnum'), 
	        	checked:true, 
	        	name: 'customerType',
	        	id:'customerPLATINUM', 
	        	inputValue: 'PLATINUM',
	        	listeners:{
	            	change:function(t){
	            		if(t.getValue()==true){
	            			Ext.getCmp("customerPC_CUSTOMER").setValue(false);
	        				Ext.getCmp("customerSC_CUSTOMER").setValue(false);
	        				
	        				Ext.getCmp("incomeProgressId").show();
	        				Ext.getCmp("extendedTimeId").show();
	        				Ext.getCmp("customTradeId").show();
	        				Ext.getCmp("goodsPotentialId").hide();
	        				Ext.getCmp("customerSourceId").hide();
	        				Ext.getCmp("monthIncomeId").hide();
	        				Ext.getCmp("goodsTimesId").hide();
	        				
        					var queryFormPanel = Ext.getCmp('QueryCustomerFormId');
        					var queryForm = queryFormPanel.getForm();	
	        				var goodsPotential=queryForm.findField('goodsPotentialId').getChecked();
		              		for(i=1;i<goodsPotential.length;i++){
		              				goodsPotential[i].setValue(false);
		              		}
		              		var customerSource=queryForm.findField('customerSourceId').getChecked();
		              		for(i=1;i<customerSource.length;i++){
		              				customerSource[i].setValue(false);
		              		}
		              		var monthIncome=queryForm.findField('monthIncomeId').getChecked();
		              		for(i=1;i<monthIncome.length;i++){
		              				monthIncome[i].setValue(false);
		              		}
		              		var goodsTimes=queryForm.findField('goodsTimesId').getChecked();
		              		for(i=1;i<goodsTimes.length;i++){
		              				goodsTimes[i].setValue(false);
		              		}
	        				
    						Ext.getCmp("monthIncomeUnlimitId").setValue(true);
	        				Ext.getCmp("goodsTimesUnlimitId").setValue(true);
	        				
	        				Ext.getCmp("goodsPotentialUnlimitId").setValue(true);
	        				Ext.getCmp("customerSourceUnlimitId").setValue(true);
	            		}
	            	}
	            }
	        },{ //钻石客户
	        	boxLabel: i18n('i18n.visualMarket.diamond'), 
	        	checked:true, 
	        	name: 'customerType',
	        	id:'customerDIAMOND', 
	        	inputValue: 'DIAMOND',
	        	listeners:{
	            	change:function(t){
	            		if(t.getValue()==true){
	            			Ext.getCmp("customerPC_CUSTOMER").setValue(false);
	        				Ext.getCmp("customerSC_CUSTOMER").setValue(false);
	        				
	        				Ext.getCmp("incomeProgressId").show();
	        				Ext.getCmp("extendedTimeId").show();
	        				Ext.getCmp("customTradeId").show();
	        				Ext.getCmp("goodsPotentialId").hide();
	        				Ext.getCmp("customerSourceId").hide();
	        				Ext.getCmp("monthIncomeId").hide();
	        				Ext.getCmp("goodsTimesId").hide();
	        				
        					var queryFormPanel = Ext.getCmp('QueryCustomerFormId');
        					var queryForm = queryFormPanel.getForm();	
	        				var goodsPotential=queryForm.findField('goodsPotentialId').getChecked();
		              		for(i=1;i<goodsPotential.length;i++){
		              			goodsPotential[i].setValue(false);
		              		}
		              		var customerSource=queryForm.findField('customerSourceId').getChecked();
		              		for(i=1;i<customerSource.length;i++){
		              			customerSource[i].setValue(false);
		              		}
		              		var monthIncome=queryForm.findField('monthIncomeId').getChecked();
		              		for(i=1;i<monthIncome.length;i++){
		              			monthIncome[i].setValue(false);
		              		}
		              		var goodsTimes=queryForm.findField('goodsTimesId').getChecked();
		              		for(i=1;i<goodsTimes.length;i++){
		              			goodsTimes[i].setValue(false);
		              		}
	        				
    						Ext.getCmp("monthIncomeUnlimitId").setValue(true);
	        				Ext.getCmp("goodsTimesUnlimitId").setValue(true);
	        				
	        				Ext.getCmp("goodsPotentialUnlimitId").setValue(true);
	        				Ext.getCmp("customerSourceUnlimitId").setValue(true);
	            		}
	            	}
	            } 
	        },{ //潜客
	        	boxLabel: i18n('i18n.visualMarket.potential'), 	
	        	name: 'customerType',
	        	id:'customerPC_CUSTOMER', 
	        	inputValue: 'PC_CUSTOMER',
	            listeners:{
	            	change:function(t){
	            		if(t.getValue()==true){
	            			Ext.getCmp("customerNORMAL").setValue(false);
	        				Ext.getCmp("customerGOLD").setValue(false);
	        				Ext.getCmp("customerPLATINUM").setValue(false);
	        				Ext.getCmp("customerDIAMOND").setValue(false);
	        				Ext.getCmp("customerSC_CUSTOMER").setValue(false);
	        				
	        				Ext.getCmp("incomeProgressId").hide();
	        				Ext.getCmp("extendedTimeId").hide();
	        				Ext.getCmp("customTradeId").hide();
	        				Ext.getCmp("goodsPotentialId").show();
	        				Ext.getCmp("customerSourceId").show();
	        				Ext.getCmp("monthIncomeId").hide();
	        				Ext.getCmp("goodsTimesId").hide();
	        				
        					var queryFormPanel = Ext.getCmp('QueryCustomerFormId');
        					var queryForm = queryFormPanel.getForm();	
		        			//收入恢复进度
			        		var incomeProgress=queryForm.findField('incomeProgressId').getChecked();
		              		for(i=1;i<incomeProgress.length;i++){
		              				incomeProgress[i].setValue(false);
		              		}
	        				//超期时长
		              		var extendedTime=queryForm.findField('extendedTimeId').getChecked();
		              		for(i=1;i<extendedTime.length;i++){
		              				extendedTime[i].setValue(false);
		              		}
		        			//客户行业
		              		var customTrade=queryForm.findField('customTradeId').getChecked();
		              		for(i=1;i<customTrade.length;i++){
		              				customTrade[i].setValue(false);
		              		}
		        			//货量潜力
	        				var goodsPotential=queryForm.findField('goodsPotentialId').getChecked();
		              		for(i=1;i<goodsPotential.length;i++){
		              				goodsPotential[i].setValue(false);
		              		}
		              		//客户来源
		              		var customerSource=queryForm.findField('customerSourceId').getChecked();
		              		for(i=1;i<customerSource.length;i++){
		              				customerSource[i].setValue(false);
		              		}
		              		//当月收入
		              		var monthIncome=queryForm.findField('monthIncomeId').getChecked();
		              		for(i=1;i<monthIncome.length;i++){
		              				monthIncome[i].setValue(false);
		              		}
		              		//发货票数
		              		var goodsTimes=queryForm.findField('goodsTimesId').getChecked();
		              		for(i=1;i<goodsTimes.length;i++){
		              				goodsTimes[i].setValue(false);
		              		}
    						Ext.getCmp("incomeProgressUnlimitId").setValue(true);
	        				Ext.getCmp("extendedTimeUnlimitId").setValue(true);
	        				Ext.getCmp("customTradeUnlimitId").setValue(true);
	
	        				Ext.getCmp("monthIncomeUnlimitId").setValue(true);
	        				Ext.getCmp("goodsTimesUnlimitId").setValue(true);
	        				//设置创建时间的初始值
	        				if(Ext.isEmpty(queryForm.findField('createBeginTime').getValue()) || 
	        						Ext.isEmpty(queryForm.findField('createEndTime').getValue())	
	        				){
	        					queryForm.findField('createBeginTime').setValue(new Date(new Date().setMonth(new Date().getMonth() - 3)));
		        				queryForm.findField('createEndTime').setValue(new Date());
	        				}
	            		}
	            	}
	            }
	        }, { //散客
	        	boxLabel: i18n('i18n.visualMarket.sccustomer'), 
	        	name: 'customerType',
	        	id:'customerSC_CUSTOMER', 
	        	inputValue: 'SC_CUSTOMER',
	            listeners:{
	            	change:function(t){
	            		if(t.getValue()==true){
	            			Ext.getCmp("customerNORMAL").setValue(false);
	        				Ext.getCmp("customerGOLD").setValue(false);
	        				Ext.getCmp("customerPLATINUM").setValue(false);
	        				Ext.getCmp("customerDIAMOND").setValue(false);
	        				Ext.getCmp("customerPC_CUSTOMER").setValue(false);
	        				
	        				Ext.getCmp("incomeProgressId").hide();
	        				Ext.getCmp("extendedTimeId").hide();
	        				Ext.getCmp("customTradeId").hide();
	        				Ext.getCmp("goodsPotentialId").hide();
	        				Ext.getCmp("customerSourceId").hide();
	        				Ext.getCmp("monthIncomeId").show();
	        				Ext.getCmp("goodsTimesId").show();
	        				
			        		var queryFormPanel = Ext.getCmp('QueryCustomerFormId');
			        		var queryForm = queryFormPanel.getForm();	
			        		//收入恢复进度
			        		var incomeProgress=queryForm.findField('incomeProgressId').getChecked();
		              		for(i=1;i<incomeProgress.length;i++){
		              				incomeProgress[i].setValue(false);
		              		}
	        				//超期时长
		              		var extendedTime=queryForm.findField('extendedTimeId').getChecked();
		              		for(i=1;i<extendedTime.length;i++){
		              				extendedTime[i].setValue(false);
		              		}
		        			//客户行业
		              		var customTrade=queryForm.findField('customTradeId').getChecked();
		              		for(i=1;i<customTrade.length;i++){
		              				customTrade[i].setValue(false);
		              		}
		        			//货量潜力
	        				var goodsPotential=queryForm.findField('goodsPotentialId').getChecked();
		              		for(i=1;i<goodsPotential.length;i++){
		              				goodsPotential[i].setValue(false);
		              		}
		              		//客户来源
		              		var customerSource=queryForm.findField('customerSourceId').getChecked();
		              		for(i=1;i<customerSource.length;i++){
		              				customerSource[i].setValue(false);
		              		}
	        				
    						Ext.getCmp("incomeProgressUnlimitId").setValue(true);
	        				Ext.getCmp("extendedTimeUnlimitId").setValue(true);
	        				Ext.getCmp("customTradeUnlimitId").setValue(true);
	
	        				Ext.getCmp("goodsPotentialUnlimitId").setValue(true);
	        				Ext.getCmp("customerSourceUnlimitId").setValue(true);
	        				
	        				//设置创建时间的初始值
	        				if(Ext.isEmpty(queryForm.findField('createBeginTime').getValue()) || 
	        						Ext.isEmpty(queryForm.findField('createEndTime').getValue())	
	        				){
	        					queryForm.findField('createBeginTime').setValue(new Date(new Date().setMonth(new Date().getMonth() - 3)));
		        				queryForm.findField('createEndTime').setValue(new Date());
	        				}
	            		}
	            	}
	           }
	        }]
		},{	//回访状态
			xtype:'checkboxgroup',
	        fieldLabel: i18n('i18n.visualMarket.returnvisitStatus'),
	        layout:'hbox',
	       	width:290,
	       	id:'reVisitStatusId',
	       	colspan:7,
	       	labelWidth:65,
	        items: [{ //不限
            	boxLabel: i18n('i18n.visualMarket.unlimit'),  
            	checked:true,	
            	name: 'reVisitStatus', 	
            	inputValue: '-1' ,
            	id:'revisitStatusUnlimitId',
            	listeners:{
	              	change:function(t){
	              		if(t.getValue()==true){
		              		var queryFormPanel = Ext.getCmp('QueryCustomerFormId');
		 					var queryForm = queryFormPanel.getForm();	
		              		var returnVisitStatus=queryForm.findField('reVisitStatusId').getChecked();
		              		
		              		for(i=1;i<returnVisitStatus.length;i++){
		              			returnVisitStatus[i].setValue(false);
		              		}
	              		}
	              	}
               }
	        },{ //未回访
            	boxLabel: i18n('i18n.visualMarket.unReturnvisit'), 
            	name: 'reVisitStatus', 
            	inputValue: '3',
            	listeners:{
	              	change:function(t){
	              		if(t.getValue()==true){
	              			Ext.getCmp('revisitStatusUnlimitId').setValue(false);
	              		}
	              	}
            	}
	        },{ //回访中
            	boxLabel: i18n('i18n.visualMarket.returnvisiting'), 
            	name: 'reVisitStatus', 
            	inputValue: '2',
            	listeners:{
	              	change:function(t){
	              		if(t.getValue()==true){
	              			Ext.getCmp('revisitStatusUnlimitId').setValue(false);
	              		}
	              	}
              	}	
             },{ //已回访
	        	boxLabel: i18n('i18n.visualMarket.returnvisited'), 
	        	name: 'reVisitStatus', 
	        	inputValue: '1',
	        	listeners:{
	              change:function(t){
	              		if(t.getValue()==true){
	              			Ext.getCmp('revisitStatusUnlimitId').setValue(false);
	              		}
	              }
	        	}	
	        }]	
		},{ //收入恢复进度
			xtype:'checkboxgroup',
	        fieldLabel: i18n('i18n.visualMarket.incomeProgress'),
			width:780,
	        colspan:14,
	        layout:'hbox',
	        id:'incomeProgressId',
	        items: [{ //不限
            	boxLabel: i18n('i18n.visualMarket.unlimit'), 
            	checked:true, 	
            	name: 'incomeProgress', 	
            	inputValue: '-1',
            	id:'incomeProgressUnlimitId',
	            listeners:{
	              	change:function(t){
	              		if(t.getValue()==true){
		              		var queryFormPanel = Ext.getCmp('QueryCustomerFormId');
	 						var queryForm = queryFormPanel.getForm();
		              		var incomeProgress=queryForm.findField('incomeProgressId').getChecked();
		              		for(i=1;i<incomeProgress.length;i++){
		              			incomeProgress[i].setValue(false);
		              		}
	              		}
	              	}
	            }	
	        },{ //0%及空
                boxLabel: i18n('i18n.visualMarket.zeropercent'), 
                name: 'incomeProgress', 
                inputValue: '-0',
                listeners:{
                    change:function(t){
                        if(t.getValue()==true){
                            Ext.getCmp('incomeProgressUnlimitId').setValue(false);
                        }
                    }
                }           
            },{ //20%以下
            	boxLabel: i18n('i18n.visualMarket.lessThanTwenty'), 
            	name: 'incomeProgress', 
            	inputValue: '0-20',
            	listeners:{
              		change:function(t){
	              		if(t.getValue()==true){
	              			Ext.getCmp('incomeProgressUnlimitId').setValue(false);
	              		}
              		}
            	}			
	        },{ //20%-50%
            	boxLabel: i18n('i18n.visualMarket.twentyToFifty'), 
            	name: 'incomeProgress', 
            	inputValue: '20-50',
	            listeners:{
              		change:function(t){
	              		if(t.getValue()==true){
	              			Ext.getCmp('incomeProgressUnlimitId').setValue(false);
	              		}
	              	}
	            }		
            },{ //50%-80%
            	boxLabel: i18n('i18n.visualMarket.fiftyToEighty'), 
            	name: 'incomeProgress', 
            	inputValue: '50-80',
	            listeners:{
              		change:function(t){
	              		if(t.getValue()==true){
	              			Ext.getCmp('incomeProgressUnlimitId').setValue(false);
	              		}
	              	}
	            }	
            },{ //80%-100%
            	boxLabel: i18n('i18n.visualMarket.eightyToHundred'), 
            	name: 'incomeProgress', 
            	inputValue: '80-100',
	            listeners:{
              		change:function(t){
	              		if(t.getValue()==true){
	              			Ext.getCmp('incomeProgressUnlimitId').setValue(false);
	              		}
	              	}
	            }		
            },{ //100%-120%
            	boxLabel: i18n('i18n.visualMarket.hundredExceedTwenty'), 
            	name: 'incomeProgress', 
            	inputValue: '100-120',
	            listeners:{
              		change:function(t){
	              		if(t.getValue()==true){
	              			Ext.getCmp('incomeProgressUnlimitId').setValue(false);
	              		}
	              	}
	            }	
            },{ //120%以上
            	boxLabel: i18n('i18n.visualMarket.moreThanHundredAndTwenty'), 
            	name: 'incomeProgress', 
            	inputValue: '120-',
            	listeners:{
              		change:function(t){
	              		if(t.getValue()==true){
	              			Ext.getCmp('incomeProgressUnlimitId').setValue(false);
	              		}
	              	}
            	}	
            }]
		},{ //超期时长
			xtype:'checkboxgroup',
		   	layout:'hbox',
	        colspan:14,
	        width:780,
	        fieldLabel: i18n('i18n.visualMarket.exceededTime'),
	        id:'extendedTimeId',
	        items: [{ //不限
	        	boxLabel: i18n('i18n.visualMarket.unlimit'), 
	        	checked:true, 	
	        	name: 'extendedTime', 	
	        	inputValue: '-1',
	        	id:'extendedTimeUnlimitId',
	            listeners:{
	              	change:function(t){
	              		if(t.getValue()==true){
		              		var queryFormPanel = Ext.getCmp('QueryCustomerFormId');
	 						var queryForm = queryFormPanel.getForm();
		              		var extendedTime=queryForm.findField('extendedTimeId').getChecked();
		              		for(i=1;i<extendedTime.length;i++){
		              			extendedTime[i].setValue(false);
		              		}
	              		}
	              	}
	             }	
	            },{ //0天
	            	boxLabel: i18n('i18n.visualMarket.unExceeded'), 
	            	name: 'extendedTime', 
	            	inputValue: '-0',
		            listeners:{
	              		change:function(t){
		              		if(t.getValue()==true){
		              			Ext.getCmp('extendedTimeUnlimitId').setValue(false);
		              		}
		              	}
	                }
	            },{ //1~5天
	            	boxLabel: i18n('i18n.visualMarket.oneToFive'), 
	            	name: 'extendedTime', 
	            	inputValue: '1-5', 
		            listeners:{
	              		change:function(t){
		              		if(t.getValue()==true){
		              			Ext.getCmp('extendedTimeUnlimitId').setValue(false);
		              		}
		              	}
		            } 
	            },{ //6~10天
	            	boxLabel: i18n('i18n.visualMarket.sixToTen'), 
	            	name: 'extendedTime', 
	            	inputValue: '6-10',
	            	listeners:{
	              		change:function(t){
		              		if(t.getValue()==true){
		              			Ext.getCmp('extendedTimeUnlimitId').setValue(false);
		              		}
		              	}
	            	}
	            },{ //11~15天
	            	boxLabel: i18n('i18n.visualMarket.elevenToFifteen'), 
	            	name: 'extendedTime', 
	            	inputValue: '11-15',
	            	listeners:{
	              		change:function(t){
		              		if(t.getValue()==true){
		              			Ext.getCmp('extendedTimeUnlimitId').setValue(false);
		              		}
		              	}
	            	}
	            },{ //16~20天
	            	boxLabel: i18n('i18n.visualMarket.sixteenToTwenty'), 
	            	name: 'extendedTime', 
	            	inputValue: '16-20',
	            	listeners:{
	              		change:function(t){
		              		if(t.getValue()==true){
		              			Ext.getCmp('extendedTimeUnlimitId').setValue(false);
		              		}
		              	}
	            	}
	            },{ //21~25天
	            	boxLabel: i18n('i18n.visualMarket.twentyOneToTwentyFive'), 
	            	name: 'extendedTime', 
	            	inputValue: '21-25',
	            	listeners:{
	              		change:function(t){
		              		if(t.getValue()==true){
		              			Ext.getCmp('extendedTimeUnlimitId').setValue(false);
		              		}
		              	}
	            	}
	            },{ //26~30天
	            	boxLabel: i18n('i18n.visualMarket.twentySixToThirty'), 
	            	name: 'extendedTime', 
	            	inputValue: '26-30',
	            	listeners:{
	              		change:function(t){
		              		if(t.getValue()==true){
		              			Ext.getCmp('extendedTimeUnlimitId').setValue(false);
		              		}
		              	}
	            	}
	            }
	        ]			
		},{   //客户行业
			xtype: 'checkboxgroup',
	        fieldLabel: i18n('i18n.MemberCustEditView.custIndustry'),
	        width:780,
	        layout:'hbox',
	        colspan:14,
	        id:'customTradeId',
	        items: [{ //不限
            	boxLabel: i18n('i18n.visualMarket.unlimit'),  
            	checked:true,	
            	name: 'customTrade', 	
            	inputValue: '-1' ,
            	id:'customTradeUnlimitId',
	            listeners:{
	              	change:function(t){
	              		if(t.getValue()==true){
		              		var queryFormPanel = Ext.getCmp('QueryCustomerFormId');
	 						var queryForm = queryFormPanel.getForm();
		              		var customTrade=queryForm.findField('customTradeId').getChecked();
		              		for(i=1;i<customTrade.length;i++){
		              			customTrade[i].setValue(false);
		              		}
	              		}
	              	}
	            }	
            },{ //服装纺织类
            	boxLabel: i18n('i18n.visualMarket.clothes'), 
            	name: 'customTrade', 
            	inputValue: 'CLOTH_SPIN',
            	listeners:{
              		change:function(t){
	              		if(t.getValue()==true){
	              			Ext.getCmp('customTradeUnlimitId').setValue(false);
	              		
	              		}
              		}
	            }
            },{ //工业电子
            	boxLabel: i18n('i18n.visualMarket.industryElec'), 
            	name: 'customTrade', 
            	inputValue: 'ITRONIC_FURNITURE',
            	listeners:{
              		change:function(t){
	              		if(t.getValue()==true){
	              			Ext.getCmp('customTradeUnlimitId').setValue(false);
	              		}
	              	}
            	}
            },{ //生活化妆品
            	boxLabel: i18n('i18n.visualMarket.dressUpGoods'), 
            	name: 'customTrade', 
            	inputValue: 'DAILYUSE_COSMETIC',
            	listeners:{
              		change:function(t){
	              		if(t.getValue()==true){
	              			Ext.getCmp('customTradeUnlimitId').setValue(false);
	              		}
	              	}
            	} 
            },{ //生活电器
            	boxLabel: i18n('i18n.visualMarket.lifeElec'), 
            	name: 'customTrade', 
            	inputValue: 'LIFE_ELECTRIC',
            	listeners:{
              		change:function(t){
	              		if(t.getValue()==true){
	              			Ext.getCmp('customTradeUnlimitId').setValue(false);
	              		}
	              	}
            	} 
            },{ //五金仪表类
            	boxLabel: i18n('i18n.visualMarket.goldElec'), 
            	name: 'customTrade', 
            	inputValue: 'IRCONWARE_INSTRUMENT',
            	listeners:{
              		change:function(t){
	              		if(t.getValue()==true){
	              			Ext.getCmp('customTradeUnlimitId').setValue(false);
	              		}
	              	}
            	} 
            },{ //食品药品
            	boxLabel: i18n('i18n.visualMarket.foodAndMedicine'), 
            	name: 'customTrade', 
            	inputValue: 'FOOD_DRUG',
            	listeners:{
              		change:function(t){
	              		if(t.getValue()==true){
	              			Ext.getCmp('customTradeUnlimitId').setValue(false);
	              		}
	              	}
            	} 
            },{ //易损易碎
            	boxLabel: i18n('i18n.visualMarket.fragile'), 
            	name: 'customTrade', 
            	inputValue: 'FRAGILE',
            	listeners:{
              		change:function(t){
	              		if(t.getValue()==true){
	              			Ext.getCmp('customTradeUnlimitId').setValue(false);
	              		}
	              	}
	            } 
            },{ //生活电子
            	boxLabel: i18n('i18n.visualMarket.lifeelectronic'), 
            	name: 'customTrade', 
            	inputValue: 'LIFEELECTRONIC',
            	listeners:{
              		change:function(t){
	              		if(t.getValue()==true){
	              			Ext.getCmp('customTradeUnlimitId').setValue(false);
	              		}
	              	}
	            } 
            },{ //塑料建材
            	boxLabel: i18n('i18n.visualMarket.plastic_build'), 
            	name: 'customTrade', 
            	inputValue: 'PLASTIC_BUILD',
            	listeners:{
              		change:function(t){
	              		if(t.getValue()==true){
	              			Ext.getCmp('customTradeUnlimitId').setValue(false);
	              		}
	              	}
	            } 
            },{ //家具
            	boxLabel: i18n('i18n.visualMarket.furniture'), 
            	name: 'customTrade', 
            	inputValue: 'FURNITURE',
            	listeners:{
              		change:function(t){
	              		if(t.getValue()==true){
	              			Ext.getCmp('customTradeUnlimitId').setValue(false);
	              		}
	              	}
	            } 
            },{ //其他
            	boxLabel: i18n('i18n.visualMarket.others'), 
            	name: 'customTrade', 
            	inputValue: 'OTHER',
            	listeners:{
              		change:function(t){
	              		if(t.getValue()==true){
	              			Ext.getCmp('customTradeUnlimitId').setValue(false);
	              		}
	              	}
            	} 
            }]
		},{   //货量潜力
			xtype: 'checkboxgroup',
	        fieldLabel: i18n('i18n.developSchedule.sendGoodsPontential'),
	        id:'goodsPotentialId',
	        width:780,
	        layout:'hbox',
	        colspan:14,
	        hidden:true,
	        items: [{ //不限
	        	boxLabel: i18n('i18n.visualMarket.unlimit'), 
	        	checked:true, 	
	        	name: 'goodsPotential', 	
	        	inputValue: '-1' ,
	        	id:'goodsPotentialUnlimitId',
	            listeners:{
	              	change:function(t){
	              		if(t.getValue()==true){
		              		var queryFormPanel = Ext.getCmp('QueryCustomerFormId');
	 						var queryForm = queryFormPanel.getForm();	
		              		var goodsPotential=queryForm.findField('goodsPotentialId').getChecked();
		              		for(i=1;i<goodsPotential.length;i++){
		              			goodsPotential[i].setValue(false);
		              		}
	              		}
	              	}
	            }	
	        },{ //600以下
	        	boxLabel: i18n('i18n.visualMarket.lessThanSixHundred'), 
	        	name: 'goodsPotential', 
	        	inputValue: '<600',
            	listeners:{
              		change:function(t){
	              		if(t.getValue()==true){
	              			Ext.getCmp('goodsPotentialUnlimitId').setValue(false);
	              		}
              		}
	            }  
	        },{ //600-3000
	        	boxLabel: i18n('i18n.visualMarket.sixHundredToThreeThousand'), 
	        	name: 'goodsPotential', 
	        	inputValue: '600-3000',
            	listeners:{
	              	change:function(t){
	              		if(t.getValue()==true){
	              			Ext.getCmp('goodsPotentialUnlimitId').setValue(false);
	              		}
	              	}
	            }  
	        },{ //3000-10000
	        	boxLabel: i18n('i18n.visualMarket.threeThousandToTenThousand'), 
	        	name: 'goodsPotential', 
	        	inputValue: '3000-10000',
            	listeners:{
	              	change:function(t){
	              		if(t.getValue()==true){
	              			Ext.getCmp('goodsPotentialUnlimitId').setValue(false);
	              		}
	              	}
	            }  
	        },{ //10000以上
	        	boxLabel: i18n('i18n.visualMarket.moreThanTenThousand'), 
	        	name: 'customTrade', 
	        	inputValue: '>10000',
            	listeners:{
	              	change:function(t){
	              		if(t.getValue()==true){
	              			Ext.getCmp('goodsPotentialUnlimitId').setValue(false);
	              		}
	              	}
	            }  
	        }]
		},{   //客户来源
			xtype: 'checkboxgroup',
	        fieldLabel: i18n('i18n.PotentialCustManagerView.custSource'),
	        width:780,
	        layout:'hbox',
	        colspan:14,
	        id:'customerSourceId',
	        hidden:true,
	        items: [{ //不限
	        	boxLabel: i18n('i18n.visualMarket.unlimit'),  
	        	checked:true,	
	        	name: 'customerSource', 	
	        	inputValue: '-1',
	        	id:'customerSourceUnlimitId',
	            listeners:{
	              	change:function(t){
	              		if(t.getValue()==true){
		              		var queryFormPanel = Ext.getCmp('QueryCustomerFormId');
	 						var queryForm = queryFormPanel.getForm();
		              		var customerSource=queryForm.findField('customerSourceId').getChecked();
		              		for(i=1;i<customerSource.length;i++){
		              			customerSource[i].setValue(false);
		              		}
	              		}
	              	}
	            } 
	        },{ //陌生来电
	        	boxLabel: i18n('i18n.marketEffectEvaluation.strangerCalls'), 
	        	name: 'customerSource', 
	        	inputValue: 'STRANGER_CALLS',
            	listeners:{
              		change:function(t){
	              		if(t.getValue()==true){
	              			Ext.getCmp('customerSourceUnlimitId').setValue(false);
	              		}
	              	}
	            }  
	        },{ //会展
	        	boxLabel: i18n('i18n.marketEffectEvaluation.exposend'), 
	        	name: 'customerSource', 
	        	inputValue: 'EXPOSANDCONFERENCES' ,
            	listeners:{
	              	change:function(t){
	              		if(t.getValue()==true){
	              			Ext.getCmp('customerSourceUnlimitId').setValue(false);
	              		}
	              	}
	            } 
	        },{ //派单
	        	boxLabel: i18n('i18n.marketEffectEvaluation.dispatchList'), 
	        	name: 'customerSource', 
	        	inputValue: 'DISPATCH_LIST',
            	listeners:{
	              	change:function(t){
	              		if(t.getValue()==true){
	              			Ext.getCmp('customerSourceUnlimitId').setValue(false);
	              		}
	              	}
	            }  
	        },{ //黄页
	        	boxLabel: i18n('i18n.marketEffectEvaluation.yellowPages'), 
	        	name: 'customerSource', 
	        	inputValue: 'YELLOW_PAGES' ,
            	listeners:{
	              	change:function(t){
	              		if(t.getValue()==true){
	              			Ext.getCmp('customerSourceUnlimitId').setValue(false);
	              		}
	              	}
	            } 
	        },{ //阿里巴巴
	        	boxLabel: i18n('i18n.marketEffectEvaluation.alibaba'), 
	        	name: 'customerSource', 
	        	inputValue: 'ALIBABA',
            	listeners:{
	              	change:function(t){
	              		if(t.getValue()==true){
	              			Ext.getCmp('customerSourceUnlimitId').setValue(false);
	              		}
	              	}
	             }  
	        },{//到达散客
	        	boxLabel: i18n('i18n.marketEffectEvaluation.arriveCust'), 
	        	name: 'customerSource', 
	        	inputValue: 'ARRIVE_CUSTOMER',
            	listeners:{
	              	change:function(t){
	              		if(t.getValue()==true){
	              			Ext.getCmp('customerSourceUnlimitId').setValue(false);
	              		}
	              	}
	            }  
	        },{ //订单潜客
	        	boxLabel: i18n('i18n.marketEffectEvaluation.orderCust'), 
	        	name: 'customerSource', 
	        	inputValue: 'ORDER_CUSTOMER',
            	listeners:{
	              	change:function(t){
	              		if(t.getValue()==true){
	              			Ext.getCmp('customerSourceUnlimitId').setValue(false);
	              		}
	              	}
	            }  
	        },{//其他
	        	boxLabel: i18n('i18n.visualMarket.others'), 
	        	name: 'customerSource', 
	        	inputValue: 'OTHER' ,
            	listeners:{
	              	change:function(t){
	              		if(t.getValue()==true){
	              			Ext.getCmp('customerSourceUnlimitId').setValue(false);
	              		}
	              	}
	            } 
	        }]
		},{   //当月收入
			xtype: 'checkboxgroup',
	        fieldLabel: i18n('i18n.visualMarket.monthlyIncome'),
	        width:780,
	        layout:'hbox',
	        colspan:14,
	        id:'monthIncomeId',
	        hidden:true,
	        items: [{ //不限
	        	boxLabel: i18n('i18n.visualMarket.unlimit'), 
	        	checked:true, 	
	        	name: 'monthIncome', 	
	        	inputValue: '-1', 
	        	id:'monthIncomeUnlimitId',
	            listeners:{
	              	change:function(t){
	              		if(t.getValue()==true){
		              		var queryFormPanel = Ext.getCmp('QueryCustomerFormId');
	 						var queryForm = queryFormPanel.getForm();	
		              		var monthIncome=queryForm.findField('monthIncomeId').getChecked();
		              		for(i=1;i<monthIncome.length;i++){
		              			monthIncome[i].setValue(false);
		              		}
	              		}
	              	}
	            } 
	        },{ //0及空
                boxLabel: i18n('i18n.visualMarket.zeroyuan'), 
                name: 'monthIncome', 
                inputValue: '-0' ,
                listeners:{
                    change:function(t){
                        if(t.getValue()==true){
                            Ext.getCmp('monthIncomeUnlimitId').setValue(false);
                        }
                    }
                }  
            },{ //0-200
	        	boxLabel: i18n('i18n.visualMarket.lessThanTwoHundred'), 
	        	name: 'monthIncome', 
	        	inputValue: '0-200' ,
            	listeners:{
	              	change:function(t){
	              		if(t.getValue()==true){
	              			Ext.getCmp('monthIncomeUnlimitId').setValue(false);
	              		}
	              	}
	            }  
	        },{//200-400
	        	boxLabel: i18n('i18n.visualMarket.twoHundredToFourHundred'), 
	        	name: 'monthIncome', 
	        	inputValue: '200-400' ,
            	listeners:{
	              	change:function(t){
	              		if(t.getValue()==true){
	              			Ext.getCmp('monthIncomeUnlimitId').setValue(false);
	              		}
	              	}
	             }  
	        },{ //400-600
	        	boxLabel: i18n('i18n.visualMarket.fourHundredToSixHundred'), 
	        	name: 'monthIncome', 
	        	inputValue: '400-600'  ,
            	listeners:{
	              	change:function(t){
	              		if(t.getValue()==true){
	              			Ext.getCmp('monthIncomeUnlimitId').setValue(false);
	              		}
	              	}
	            } 
	        },{ //600以上
	        	boxLabel: i18n('i18n.visualMarket.moreThanSixHundred'), 
	        	name: 'monthIncome', 
	        	inputValue: '600-'  ,
            	listeners:{
	              	change:function(t){
	              		if(t.getValue()==true){
	              			Ext.getCmp('monthIncomeUnlimitId').setValue(false);
	              		}
	              	}
	             } 
	        }]
		},{   //发货票数
			xtype: 'checkboxgroup',
	        fieldLabel: i18n('i18n.ScatterCustManagerView.beginShipVotes'),
	        width:780,
	        layout:'hbox',
	        colspan:14,
	        hidden:true,
	        id:'goodsTimesId',
	        items: [{ //不限
	        	boxLabel: i18n('i18n.visualMarket.unlimit'),  
	        	checked:true,	
	        	name: 'goodsTimes', 	
	        	inputValue: '-1' ,
	        	id:'goodsTimesUnlimitId',
	            listeners:{
	              	change:function(t){
	              		if(t.getValue()==true){
		              		var queryFormPanel = Ext.getCmp('QueryCustomerFormId');
	 						var queryForm = queryFormPanel.getForm();
		              		var goodsTimes=queryForm.findField('goodsTimesId').getChecked();
		              		for(i=1;i<goodsTimes.length;i++){
		              			goodsTimes[i].setValue(false);
		              		}
	              		}
	              	}
	            } 
	        },{ //0次及空
                boxLabel: i18n('i18n.visualMarket.zeroTime'), 
                name: 'goodsTimes', 
                inputValue: '-0' ,
                listeners:{
                    change:function(t){
                        if(t.getValue()==true){
                            Ext.getCmp('goodsTimesUnlimitId').setValue(false);
                        }
                    }
                }
            },{ //5次以下
	        	boxLabel: i18n('i18n.visualMarket.lessThanFive'), 
	        	name: 'goodsTimes', 
	        	inputValue: '1-5' ,
            	listeners:{
	              	change:function(t){
	              		if(t.getValue()==true){
	              			Ext.getCmp('goodsTimesUnlimitId').setValue(false);
	              		}
	              	}
	            }
	        },{ //6-10次
	        	boxLabel: i18n('i18n.visualMarket.sixToTenTimes'), 
	        	name: 'goodsTimes', 
	        	inputValue: '6-10' ,
            	listeners:{
	              	change:function(t){
	              		if(t.getValue()==true){
	              			Ext.getCmp('goodsTimesUnlimitId').setValue(false);
	              		}
	              	}
	            } 
	        },{//11-15次
	        	boxLabel: i18n('i18n.visualMarket.elevenToFifteenTimes'), 
	        	name: 'goodsTimes', 
	        	inputValue: '11-15'  ,
            	listeners:{
	              	change:function(t){
	              		if(t.getValue()==true){
	              			Ext.getCmp('goodsTimesUnlimitId').setValue(false);
	              		}
	              	}
	            }
	        },{ //16-20次
	        	boxLabel: i18n('i18n.visualMarket.sixteenToTwentyTimes'), 
	        	name: 'goodsTimes', 
	        	inputValue: '16-20'  ,
            	listeners:{
	              	change:function(t){
	              		if(t.getValue()==true){
	              			Ext.getCmp('goodsTimesUnlimitId').setValue(false);
	              		}
	              	}
	            }
	        },{ //21次以上
	        	boxLabel: i18n('i18n.visualMarket.moreThanTwentyOne'), 
	        	name: 'goodsTimes', 
	        	inputValue: '21-' ,
            	listeners:{
	              	change:function(t){
	              		if(t.getValue()==true){
	              			Ext.getCmp('goodsTimesUnlimitId').setValue(false);
	              		}
	              	}
	            } 
	        }]
		}],
		buttons:[{//查询按钮
			xtype:'button',
			text:i18n('i18n.developPlan.search'),
			id:'searchBtnId',
			disabled:true,
			listeners:{
				'click':function(){
					//判断所查询的部门是否为营业部级别
					if(isBusinessDept != '1'){
						MessageUtil.showErrorMes(i18n('i18n.visualMarket.selectDeptToBussinessLevel'));
						return false;
					}
					var queryFormPanel = Ext.getCmp('QueryCustomerFormId');
					var queryForm = queryFormPanel.getForm();
              		var customerType=queryForm.findField('customerTypeId').getChecked();
              	    var custNumber=queryForm.findField('customerName').getValue();
					//判断查询客户是否为空
					if(Ext.isEmpty(custNumber)&&customerType.length==0){
						MessageUtil.showErrorMes(i18n('i18n.visualMarket.canNotSearchWithNullCustType'));
						return;
					}
					//判断地方标记不能为空
					mapMakerStatus=queryForm.findField('mapMakerId').getValue();
						if(Ext.isEmpty(mapMakerStatus)){
						MessageUtil.showErrorMes(i18n('i18n.visualMarket.canNotSearchWithNullMark'));
						return;
					}	
					//判断所选的部门id是否合法
					var deptId=queryForm.findField('deptId').getValue();
					if(!/\d+/.test(deptId)){
						MessageUtil.showErrorMes(i18n('i18n.visualMarket.canNotSearchWithErrorDept'));
			 			Ext.getCmp('deptId').setValue(User.deptId);
			 			Ext.getCmp('deptId').standardCode=User.standardCode;
						return ;
					}
					//校验输入的起止创建时间是否合法
					if(!(queryForm.findField('createBeginTime').isValid()) || 
							!(queryForm.findField('createEndTime').isValid())){
						MessageUtil.showErrorMes(i18n('i18n.visualMarket.inputInvalidCreateTime'));
						return false;
					}
					
					//将所选的客户类型的inputvalue放置到一个数组中
					var custDegreeArray=new Array();
					for (var i = 0; i < customerType.length; i++){
					  	custDegreeArray.push(customerType[i].inputValue);
					}
					var isNormalCustomerOrNot = Ext.Array.contains(custDegreeArray,'SC_CUSTOMER')||Ext.Array.contains(custDegreeArray,'PC_CUSTOMER');
					var createEndTime = queryForm.findField('createEndTime').getValue();
					var createBeginTime = queryForm.findField('createBeginTime').getValue();
					var compareDays = DButil.compareTwoDate(createBeginTime,createEndTime);
					if(compareDays < 0){
						MessageUtil.showErrorMes(i18n('i18n.visualMarket.createBeginTimeBiggerThanEndTime'));
						return false;
					}
					if(isNormalCustomerOrNot){
						if(Ext.isEmpty(queryForm.findField('createBeginTime').getValue())){
							MessageUtil.showErrorMes(i18n('i18n.visualMarket.createBeginTimeIsNull'));
							return false;
						}
						if(Ext.isEmpty(queryForm.findField('createEndTime').getValue())){
							MessageUtil.showErrorMes(i18n('i18n.visualMarket.createEndTimeIsNull'));
							return false;
						}
						var createCompareTime = Ext.Date.add(new Date(createEndTime), Ext.Date.MONTH, -3);
						var days = DButil.compareTwoDate(createCompareTime,createBeginTime);
						if(days < 0){
							MessageUtil.showErrorMes(i18n('i18n.visualMarket.createTimeCanNotExceedThreeMonth'));
							return false;
						}
					}
					//把查询条件的获取抽取出来，存放到全局变量中
					searchConditionParam = getParams();
					var searchParamForMap = {
						'queryCondition.deptId' :searchConditionParam.queryCondition.deptId,
						'queryCondition.custNumber' :searchConditionParam.queryCondition.custNumber,
						'queryCondition.mapMakerStatus' :searchConditionParam.queryCondition.mapMakerStatus,
						'queryCondition.custDegree' :searchConditionParam.queryCondition.custDegree,
						'queryCondition.psCustType' :searchConditionParam.queryCondition.psCustType,
						'queryCondition.returnVisitStatus' :searchConditionParam.queryCondition.returnVisitStatus,
						'queryCondition.incomeRestoreRate' :searchConditionParam.queryCondition.incomeRestoreRate,
						'queryCondition.exceedTime' :searchConditionParam.queryCondition.exceedTime,
						'queryCondition.custTrade' :searchConditionParam.queryCondition.custTrade,
						'queryCondition.monthlyIncome' :searchConditionParam.queryCondition.monthlyIncome,
						'queryCondition.sendGoodsNumber' :searchConditionParam.queryCondition.sendGoodsNumber,
						'queryCondition.goodsPotential' :searchConditionParam.queryCondition.goodsPotential,
						'queryCondition.custResource' :searchConditionParam.queryCondition.custResource,
						'queryCondition.custLabel' :searchConditionParam.queryCondition.custLabel,
						'queryCondition.sortType' :'',
						'queryCondition.sortMark' :'',
						'queryCondition.createBeginTime':searchConditionParam.queryCondition.createBeginTime,
						'queryCondition.createEndTime':searchConditionParam.queryCondition.createEndTime
					};
					//按钮禁用三秒
					disBtn();
					//清除客户标注信息
					point.clearCustomerMarker();
					//出现
					point.clearAllOverlays();
					
					//右边查询列表load数据					            		
					if(!Ext.isEmpty(customerMarketInfoStore) && (customerMarketInfoStore.getCount() != 0)){
						customerMarketInfoStore.removeAll();
						Ext.getCmp('totalCountOfSelectResultId').setValue('<span style="color: #FF0000">'+
								0+'</span>');
					}
					//初始化排序条件
					Ext.getCmp("monthGoodCounts").setIcon("");
					Ext.getCmp("revisitTime").setIcon("");
					Ext.getCmp("tradeTime").setIcon("");
					Ext.getCmp("createTime").setIcon("");
					
					Ext.getCmp("monthGoodCounts").removeCls("sortCust-curent");
					Ext.getCmp("revisitTime").removeCls("sortCust-curent");
					Ext.getCmp("tradeTime").removeCls("sortCust-curent");
					Ext.getCmp("createTime").removeCls("sortCust-curent");
					
					Ext.getCmp("monthGoodCounts").addCls("sortCust");
					Ext.getCmp("revisitTime").addCls("sortCust");
					Ext.getCmp("tradeTime").addCls("sortCust");
					Ext.getCmp("createTime").addCls("sortCust");
					//初始化传到后台的排序条件
					btnSortType=null;
					btnSortMark=null;
					
					customerMarketInfoStore.loadPage(1);
			
					//加载地图时出现蒙层
					var myMask = new Ext.LoadMask(Ext.getBody(),{msg:"Loading..."}
					);
					myMask.show();
					//显示部门标注
					point.showDeptMarker(Ext.getCmp('deptId').standardCode);
					 var deptId=Ext.getCmp('deptId').getValue();
							
					//根据部门id去查询该部门的营销区域
					var paramDetails = {
						'deptId':deptId
					};
					var successFn = function(json) {	
				 		var region=json.region;
				 		if(!Ext.isEmpty(region)){
				 			//调用GIS显示营销区域
							poly.showPolygonById(region.regionId);					 		
				 		}
					}
					var failFn=function(){
						return;
					}
					//向后台发送请求得到营销区域,并显示
					visualMarketingData.prototype.searchRegionByDeptId(paramDetails,successFn,failFn);
					
					Ext.getCmp('customerDetailPanelId').expand(true);

			 		//加载地图中客户标注成功执行的方法
					var successFn = function(json) {	
				 		 markCustInfos =json.markCustInfos;
				 		var custAddr=changeMakerInfo(markCustInfos);
				 		//给记录客户等级或是客户类型的标记变量赋值，用以单击客户标注时弹出浮窗信息
				 		if(!Ext.isEmpty(markCustInfos) && markCustInfos.length > 0){
				 			custDegreeOrCustType = markCustInfos[0].custType;
				 		}
				 		//在地图上显示客户标注
				 		point.showCustomerByPoint(custAddr,showCustomerDetail);
				 		myMask.hide();
					}
					var failFn=function(json){
						//地图显示标注响应超时！
						if(Ext.isEmpty(json)){
							MessageUtil.showErrorMes(i18n('i18n.visualMarket.exceedTimeOfLoadCustMark'));
						}
						else{
							MessageUtil.showErrorMes(json.message);
						}
						myMask.hide();
					}
					//向后台发送请求或者已经标注的150个客户
					visualMarketingData.prototype.searchMarkCustInfoList(searchParamForMap,successFn,failFn);
					
					//点击查询清空查询排序按钮图标
					Ext.getCmp("revisitTime").setIcon("");
					Ext.getCmp("monthGoodCounts").setIcon("");
					Ext.getCmp("createTime").setIcon("");
					Ext.getCmp("tradeTime").setIcon("");

					
					//如果当前所选的部门是营业部且具有区长管理层的角色权限时，启用制定计划按钮
					if((isBusinessDept === '1') && (Ext.Array.contains(User.roleids,i18n('i18n.visualMarket.areaManagerRoleId'))
							)){
						Ext.getCmp('btnForMakePlanId').show();
					}
				}
			}
		},{//重置
			xtype:'button',
			text:i18n('i18n.MemberCustEditView.reset'),
			handler:function(){
				var queryFormPanel = Ext.getCmp('QueryCustomerFormId');
	 			var queryForm = queryFormPanel.getForm();
	 			queryForm.reset();
	 			//移除部门下拉框中的内容
	 			Ext.getCmp('deptId').store.removeAll();
	 			//初始化部门下拉框中的内容
				var deptModel=new DeptModel();
				deptModel.set("id",User.deptId);
				deptModel.set("deptName",User.deptName);
				deptModel.set("standardCode",User.standardCode);
				Ext.getCmp('deptId').store.add(deptModel);
	 			Ext.getCmp('deptId').setValue(User.deptId);
	 			Ext.getCmp('deptId').standardCode=User.standardCode;
	 			queryFormPanel.searchCustLabel(queryFormPanel);
			}
		}],
		//搜索客户标签
		searchCustLabel:function(me){
			var bq1=null;
			var customerLabelList=null;
			//获得部门id
			var deptId=Ext.getCmp('deptId').getValue();
			var paramDetails = {
				'deptId':deptId
			};
			//成功方法的执行函数
			var successFn = function(json) {
				customerLabelList=json.labelList;
				isBusinessDept=json.isbusinessDept;
				if(Ext.isEmpty(currentIsBusinessDept)){
					currentIsBusinessDept=json.isbusinessDept;
				}
				//如果是无选项存在则先删除
				if(!Ext.isEmpty(Ext.getCmp('displayCustomerLabel'))){
					me.remove('displayCustomerLabel');
				}
				//如果第一组标签存在则删除
				if(!Ext.isEmpty(Ext.getCmp('customerLabelGroup'))){
					me.remove('customerLabelGroup');
				}
				//如果第二组标签也存在，也删除
				if(!Ext.isEmpty(Ext.getCmp('customerLabelGroup1'))){
					me.remove('customerLabelGroup1');
				}
				//如果获得的客户标签为空 或者没有标签则显示为无选项
				if((Ext.isEmpty(customerLabelList)||customerLabelList.length==0)){
					var bq=Ext.create('Ext.form.field.Display',{
					 	colspan:14,
			        	labelWidth:78,
						labelAlign:'right',
						fieldLabel: i18n('i18n.visualMarket.customerLabel'),
						id:'displayCustomerLabel',
						value: i18n('i18n.visualMarket.nullCustomerLabel')
					});	
				}
				else{	
					var bq=Ext.create('Ext.form.CheckboxGroup',{
						fieldLabel: i18n('i18n.visualMarket.customerLabel'),
						id:'customerLabelGroup',
				        layout:'hbox',
				        width:800,
				        colspan:14,
				        labelWidth:78,
						labelAlign:'left'
					});
					//不限客户标签
					bq.add({
						boxLabel: i18n('i18n.visualMarket.unlimit'), 
						name: 'customerLabel', 
						inputValue:'-1',
						checked:true,
						id:'customerLabelUnlimitedId',
			            listeners:{
			              	change:function(t){
			              		if(t.getValue()==true){
				              		var queryFormPanel = Ext.getCmp('QueryCustomerFormId');
		 							var queryForm = queryFormPanel.getForm();
				              		var customerLabelGroup=queryForm.findField('customerLabelGroup').getChecked();
				              		for(i=1;i<customerLabelGroup.length;i++){
				              			customerLabelGroup[i].setValue(false);
				              		}
				              		if(!Ext.isEmpty(queryForm.findField('customerLabelGroup1'))){
				              			var customerLabelGroup1=queryForm.findField('customerLabelGroup1').getChecked();
				              			for(i=0;i<customerLabelGroup1.length;i++){
				              				customerLabelGroup1[i].setValue(false);
				              			}
				              		}	
			              		}
			              	}
			            } 
					});	
					//把客户标签分成两组 如果大于6个则折行
					if(customerLabelList.length<=6){
						for(var i=0;i<customerLabelList.length;i++){
							var d=customerLabelList[i];
							var chk = {boxLabel: d.labelName, name: 'customerLabel', inputValue:d.id};
							bq.add({
								boxLabel: d.labelName, 
								name: 'customerLabel', 
								inputValue:d.id,
				            	listeners:{
				              		change:function(t){
					              		if(t.getValue()==true){
					              			Ext.getCmp('customerLabelUnlimitedId').setValue(false);
					              		}
					              	}
					            } 
							});
		   				}
					}
					else{
						for(var i=0;i<6;i++){
							var d=customerLabelList[i];
							var chk = {boxLabel: d.labelName, name: 'customerLabel', inputValue:d.id};
							bq.add({
								boxLabel: d.labelName, 
								name: 'customerLabel', 
								inputValue:d.id,
				            	listeners:{
					              	change:function(t){
					              		if(t.getValue()==true){
					              			Ext.getCmp('customerLabelUnlimitedId').setValue(false);
					              		}
					              	}
					            }
							});
		   				}
						var bq1=Ext.create('Ext.form.CheckboxGroup',{
							fieldLabel: '&nbsp;',
							id:'customerLabelGroup1',
							labelSeparator:'',
					        layout:'hbox',
					        width:800,
					        colspan:14,
					        labelWidth:78,
							labelAlign:'left'
						});
						for(var i=6;i<customerLabelList.length;i++){
							var d=customerLabelList[i];
							var chk = {
								boxLabel: d.labelName, 
								name: 'customerLabel', 
								inputValue:d.id
							};
							bq1.add({
								boxLabel: d.labelName, 
								name: 'customerLabel', 
								inputValue:d.id,
				            	listeners:{
					              	change:function(t){
					              		if(t.getValue()==true){
					              			Ext.getCmp('customerLabelUnlimitedId').setValue(false);
					              		}
					              	}
					              }
							});
						}
				}
			}
			me.add(bq);
			if(!Ext.isEmpty(bq1)){
				me.add(bq1);
			}
			Ext.getCmp('searchBtnId').enable();
		};
		//失败显示后台传来的消息
		var failFn = function(result) {
			Ext.getCmp('searchBtnId').enable();
			if(Ext.isEmpty(result)){
				//抱歉，客户标签加载超时
				MessageUtil.showErrorMes(i18n('i18n.visualMarket.exceedTimeOfLoadCustLabel'));
			}
			else{
				MessageUtil.showErrorMes(result.message);
			}
		};
		//向后台发送请求
		visualMarketingData.prototype.findCustomerLabelByDeptId(paramDetails,successFn,failFn);	
	},
	initComponent:function(){
		var me=this;
		this.callParent();
			//设置默认部门
		var deptModel=new DeptModel();
		deptModel.set("id",User.deptId);
		deptModel.set("deptName",User.deptName);
		this.getForm().findField("deptName").store.add(deptModel);
		this.getForm().findField("deptName").setValue(User.deptId);
		this.searchCustLabel(me);
	}
	});
                	
	//客户查询结果Grid
	var selModelForGrid = Ext.create('Ext.selection.CheckboxModel');
    Ext.define('CRM.Marketing.CustomerDetail', {
     	extend:'SearchGridPanel',
        store: null,
        cls:'mapGrid',
        autoScroll:true,
        loadMask: true,
        viewConfig: {
           trackOver: false,
           stripeRows: false
        },
        selModel:selModelForGrid,//选择框
        columns:[{//全选
            text: i18n('i18n.visualMarket.fullySelect'),
            dataIndex: 'name',
            flex: 1,
            sortable: false,
            renderer:function(value,metaData,record){
            	var custName="";
				if(record.get('custName').length >= 11){
				    custName=record.get('custName').substr(0,10)+'...';
				}else{
				    custName= record.get('custName');
				} 
				
				//转换固定电话的显示格式
				var telephoneForShow = '';
				if(record.get('telephone').length > 12){
					telephoneForShow=record.get('telephone').substr(0,11)+'...';
				}else{
					telephoneForShow= record.get('telephone');
				}
				
				if(!Ext.isEmpty(record.get('custType'))&&record.get('custType')==='MEMBER'){
					//限制主联系人姓名最长显示长度
					var mainLinkManNameForView ='';
					mainLinkManNameForView = Ext.String.ellipsis(record.get('mainLinkManName'),10,false);
					
					//控制显示的表格中每行记录的高度
					Ext.getCmp('customerDetailId').removeCls('psCustomerHeightCss');
					Ext.getCmp('customerDetailId').addCls('fixCustomerHeightCss');
					 //当后端返回过来的月均收入为空时，则将aveMonthlyIncome置为0
					 if(Ext.isEmpty(record.get('aveMonthlyIncome'))){
						 record.set('aveMonthlyIncome','0'); 
					 }
				 
					 var  range ="";
					 var  modifyMake="";
					 //如果客户的经纬度都为空，即客户未标注 则不显示测距按钮
					 if(!Ext.isEmpty(record.get('longitude'))||!Ext.isEmpty(record.get('latitude'))){
					 	range='<a title = "测距"><img src="../scripts/marketing/map/images/ruler.png"  alt="测距" onclick="javascript:showCustToDeptDistance(\'{10}\',\'{11}\');"/></a>';
				 	 }
				 	 //如果当前登录的部门不是营业部 则没有修改地址及测距的权限
				 	 if(currentIsBusinessDept=="1"){
					 	modifyMake=	'<a  title = "标注"><img src="../scripts/marketing/map/images/suggestLabel.png"  alt="标注" onclick="javascript:isMakeCustomerByAddress(\'{6}\',\'{7}\',\'{8}\',\'{9}\',\'{12}\',\'{10}\',\'{11}\')"/></a>'+
					 	'<a   title = "修改地址"><img src="../scripts/marketing/map/images/modifyAddr.png"  alt="修改地址"  onclick="javascript:modifyAddr(\'{7}\',\'{8}\',\'{9}\',\'{6}\')" /></a>';
				 	 }
				 	 if(Ext.isEmpty(record.get('longitude')) || Ext.isEmpty(record.get('latitude'))){
				 		return	Ext.String.format(
								'<a title = "{13}">{0}</a>'+
								'&nbsp;&nbsp;&nbsp;\>\><a href = "javascript:viewCustDetail(\'{7}\',\'{8}\')">详情</a><br\>'+
								'<img src="../scripts/marketing/map/images/{5}.png"/>'+
								'月均收入<span style= "color:#ff0000">{4}</span>元<br\>'+
								'当月货量<span style= "color:#ff0000">{15}</span>千克<br\>'+
								'联系人：<a title = "{16}">{1}</a><br\>手机：{2}<br\>电话：<a title = "{3}">'+telephoneForShow+'</a>&nbsp;&nbsp;'	+range +modifyMake,
								//传入的参数列表
								custName,
								mainLinkManNameForView,
								record.get('mobile'),
								record.get('telephone'),
								record.get('aveMonthlyIncome'),
								record.get('custDegree'),
								record.get('address'),
								record.get('custId'),
								record.get('custType'),
								record.get('linkManId'),
								record.get('longitude'),
								record.get('latitude'),
								record.get('returnVisitStatus'),
								record.get('custName'),
								record.get('deptId'),
								record.get('monthlyGoodsNum'),
								record.get('mainLinkManName')
						);
				 	}
				 	else{
				 		return	Ext.String.format(
							'<a href = "javascript:showCustomerMarkDetail(\'{7}\',\'{8}\',\'{10}\',\'{11}\',\'{12}\',\'{5}\',\'{14}\')" title = "{13}">{0}</a>'+
							'&nbsp;&nbsp;&nbsp;\>\><a href = "javascript:viewCustDetail(\'{7}\',\'{8}\')">详情</a><br\>'+
							'<img src="../scripts/marketing/map/images/{5}.png"/>'+
							'月均收入<span style= "color:#ff0000">{4}</span>元<br\>'+
							'当月货量<span style= "color:#ff0000">{15}</span>千克<br\>'+
							'联系人：<a title = "{16}">{1}</a><br\>手机：{2}<br\>电话：<a title = "{3}">'+telephoneForShow+'</a>&nbsp;&nbsp;'	+range +modifyMake,
							//传入的参数列表
							custName,
							mainLinkManNameForView,
							record.get('mobile'),
							record.get('telephone'),
							record.get('aveMonthlyIncome'),
							record.get('custDegree'),
							record.get('address'),
							record.get('custId'),
							record.get('custType'),
							record.get('linkManId'),
							record.get('longitude'),
							record.get('latitude'),
							record.get('returnVisitStatus'),
							record.get('custName'),
							record.get('deptId'),
							record.get('monthlyGoodsNum'),
							record.get('mainLinkManName')
						);
				 	}
				}
				else {
					Ext.getCmp('customerDetailId').removeCls('fixCustomerHeightCss');
					Ext.getCmp('customerDetailId').addCls('psCustomerHeightCss');
					//限制联系人姓名最长显示长度
					var linkManNameForView ='';
					linkManNameForView = Ext.String.ellipsis(record.get('linkManName'),10,false);
					var  range ="";
					var  modifyMake="";
					//如果客户的经纬度都为空，即客户未标注 则不显示测距按钮
					if(!Ext.isEmpty(record.get('longitude'))||!Ext.isEmpty(record.get('latitude'))){
						range='<a title = "测距"><img src="../scripts/marketing/map/images/ruler.png"  alt="测距" onclick="javascript:showCustToDeptDistance(\'{7}\',\'{8}\');"/></a>';
				 	}
				 	//如果当前登录的部门不是营业部 则没有修改地址及测距的权限
				 	if(currentIsBusinessDept=="1"){
					 	modifyMake='<a  title = "标注"><img src="../scripts/marketing/map/images/suggestLabel.png"  alt="标注" onclick="javascript:isMakeCustomerByAddress(\'{4}\',\'{5}\',\'{6}\',\'{13}\',\'{9}\',\'{7}\',\'{8}\')"/></a>'+'<a title = "修改地址"><img src="../scripts/marketing/map/images/modifyAddr.png"  alt="修改地址"  onclick="javascript:modifyAddr(\'{5}\',\'{6}\',\'{13}\',\'{4}\')" /></a>';
				 	}
					if(Ext.isEmpty(record.get('longitude')) || Ext.isEmpty(record.get('latitude'))){
						return	Ext.String.format(
							'<a title = "{10}">{0}</a>'+
							'&nbsp;&nbsp;&nbsp;\>\><a href = "javascript:viewCustDetail(\'{5}\',\'{6}\')">详情</a><br\>'+
							'联系人：<a title = "{12}">{1}</a><br\>手机：{2}<br\>电话：<a title = "{3}">'+telephoneForShow+'</a>&nbsp;&nbsp;' +range
							+modifyMake,
							//传入的参数列表
							custName,
							linkManNameForView,
							record.get('mobile'),
							record.get('telephone'),
							record.get('address'),
							record.get('psCustId'),
							record.get('custType'),
							record.get('longitude'),
							record.get('latitude'),
							record.get('returnVisitStatus'),
							record.get('custName'),
							record.get('deptId'),
							record.get('linkManName'),
							record.get('linkManId')
						);	
					}
					else{
						return	Ext.String.format(
							'<a href = "javascript:showCustomerMarkDetail(\'{5}\',\'{6}\',\'{7}\',\'{8}\',\'{9}\',\'{6}\',\'{11}\')" title = "{10}">{0}</a>'+
							'&nbsp;&nbsp;&nbsp;\>\><a href = "javascript:viewCustDetail(\'{5}\',\'{6}\')">详情</a><br\>'+
							'联系人：<a title = "{12}">{1}</a><br\>手机：{2}<br\>电话：<a title = "{3}">'+telephoneForShow+'</a>&nbsp;&nbsp;' +range
							+modifyMake,
							//传入的参数列表
							custName,
							linkManNameForView,
							record.get('mobile'),
							record.get('telephone'),
							record.get('address'),
							record.get('psCustId'),
							record.get('custType'),
							record.get('longitude'),
							record.get('latitude'),
							record.get('returnVisitStatus'),
							record.get('custName'),
							record.get('deptId'),
							record.get('linkManName')
						);	
					}	
				}
			}
        }],
        //初始化方式  
		initComponent : function() {
			var me=this;
			customerMarketInfoStore = Ext.create('CRM.marketing.CustomerMarketInfoStore',{
				id:'CustomerMarketInfoStoreId'
			});	
			me.store=customerMarketInfoStore;
			customerMarketInfoStore.on('beforeload',storeBeforeLoadFunction);
			customerMarketInfoStore.on('load',function(){
				//统计加载查询列表次数的全局变量加1
				numberOfClickSearchButton = numberOfClickSearchButton + 1;
				//获得总数据条数
				if(!Ext.isEmpty(customerMarketInfoStore.totalCount)){
					Ext.getCmp('totalCountOfSelectResultId').setValue('<span style="color: #FF0000">'+
							customerMarketInfoStore.totalCount+'</span>');
				}
				else{
					Ext.getCmp('totalCountOfSelectResultId').setValue('<span style="color: #FF0000">'+
							0+'</span>');
				}
				//自动刷新一次列表，以解决首次进入页面的时候滚动条滚动到表格最后而记录显示不完整的问题
//				if(numberOfClickSearchButton === 1){
//					Ext.getCmp('pagingtoolbarForGridId').doRefresh();
//				}
				Ext.getCmp("customerDetailId").getSelectionModel().selectAll();
				Ext.getCmp("customerDetailId").getSelectionModel().deselectAll();
			});
			
			me.dockedItems = [{
				xtype:'pagingtoolbar',
				id:'pagingtoolbarForGridId',
				store:customerMarketInfoStore,
				dock:'bottom',
				cls:'pagingtoolbar',
				refreshText:'',
				displayInfo:false,
				autoScroll : true
			},{
	            xtype: 'toolbar',
	            dock: 'top',
	            items: [{//每页显示
            		text: i18n('i18n.authorization.roleGrid.page_count'),
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
	                       {'value':'10'},
	                       {'value':'15'},
	                       {'value':'20'},
	                       {'value':'25'},
	                       {'value':'30'},
	                       {'value':'35'},
	                       {'value':'40'},
	                       {'value':'45'},
	                       {'value':'50'}
	                   ]
	               }),
	               listeners:{
						select : {
				            fn: function(_field,_value){
				            	var pageSize = customerMarketInfoStore.pageSize;
				            	var newPageSize = parseInt(_field.value);
				            	if(pageSize!=newPageSize){
				            		customerMarketInfoStore.pageSize = newPageSize;
				            		customerMarketInfoStore.loadPage(1);
				            	}
				            }
				        }
	               }
		           }),{//条
						text: i18n('i18n.PotentialCustManagerView.SearchResultGrid.number'),
						xtype: 'tbtext'
		           },{//共
						text: i18n('i18n.visualMarket.sum'),
						xtype: 'tbtext'
		           },{//显示条数
						value: '<span style="color: #FF0000">0</span>',
						xtype: 'displayfield',
						fieldLabel:'',
						labelSeparator:'',
						width:50,
						labelWidth:0,
						id:'totalCountOfSelectResultId'
		           },{//条
						text: i18n('i18n.PotentialCustManagerView.SearchResultGrid.number'),
						xtype: 'tbtext'
		           },'->',{
		            	xtype:'button',
		                text:i18n('i18n.Cycle.makePlan'),
		                id:'btnForMakePlanId',
		                hidden:true,
		                listeners:{
		                	'click':function(th){
	        					//禁用制定计划按钮
	        					th.disable();
	        					//获取客户信息查询结果的表格
	        					var customerDetailGrid = Ext.getCmp("customerDetailId");
	        					//获取表格中选中的记录
	    						var selection = customerDetailGrid.getSelectionModel().getSelection();
	    						if(selection.length == 0){
	    							MessageUtil.showErrorMes(i18n('i18n.visualMarket.selectCustToMakePlan'));
	            					//启用制定计划按钮
	            					th.enable();
	    							return false;
	    						}
	    						//新建一个客户信息数组，用以存放选中的客户id
	    						var custIdList = new Array();
	    						//声明一个变量，用以定义客户类型
	    						var custType = '';
	    						if(selection[0].data.custType=='PC_CUSTOMER'||selection[0].data.custType == 'SC_CUSTOMER'){   							
	    							for(var i = 0;i<selection.length;i++){
	    								custIdList.push(selection[i].data.psCustId);
	    								custType = 'dev';
	    							}
	    						}
	    						else{
	    							for(var i = 0;i<selection.length;i++){
	    								custIdList.push(selection[i].data.custId);
	    								custType = 'mat';
	    							}
	    						}
	    						var successFn = function(json){
	    							th.enable();
	    							if(!Ext.isEmpty(json.totalCount) && (json.totalCount === selection.length)){
	    								MessageUtil.showErrorMes(i18n('i18n.visualMarket.returningStatusCanNotMakePlan'));
	                					//启用制定计划按钮
	//                					th.enable();
	    								return false;
	    							}
	    							//要制定计划的客户信息数组清空
	        						custInfoForMakePlan = [];
	        						//要制定计划的客户部门id
	        						deptIdForMakePlan = Ext.getCmp('deptId').getValue();
	        						if(selection[0].data.custType=='PC_CUSTOMER'||selection[0].data.custType == 'SC_CUSTOMER'){
	        							//创建制定开发计划的窗口
	        							var deliveryDevelopPopWindow=Ext.getCmp("deliveryDevelopPopWindow");//获取win
	        							if(!deliveryDevelopPopWindow){
	        								deliveryDevelopPopWindow=Ext.create('DeliveryDevelopPopWindow',{id:'deliveryDevelopPopWindow'});
	        							}
	
	        							deliveryDevelopPopWindow.show();
	        							//将潜散客id和客户类型放置到客户信息数组中
	        							for(var i = 0;i<selection.length;i++){
	        								//判断选中的客户是否在可制定计划的客户列表中
	        								if(Ext.Array.contains(json.custIdList,selection[i].data.psCustId)){
	        									custInfoForMakePlan.push(selection[i].data.psCustId);
	        								}
	        							}
	        							Ext.getCmp('searchResultGridId').store.loadPage(1);
	        							//清除已选表格的数据
	        							Ext.getCmp('ChooseCustomerGridId').store.removeAll();
	        							//重置计划信息panel
	        							Ext.getCmp("developPlanFromPanel").getForm().reset();
	        							
	        							
	        							var deptName=Ext.getCmp('QueryCustomerFormId').getForm().findField('deptName').getRawValue();
	        							var deptId=Ext.getCmp('QueryCustomerFormId').getForm().findField('deptName').getValue();
	        							
	        							Ext.getCmp("developPlanFromPanel").getForm().findField("execdeptid").setValue(deptName);
	        							Ext.getCmp("developPlanFromPanel").getForm().findField("execuserid").store.load({params:{executeDept:deptId}});
	        							
	        							if(!Ext.isEmpty(json.totalCount) && (json.totalCount > 0)){
	        								MessageUtil.showInfoMes(i18n('i18n.visualMarket.returningStatusCustCountOne')+'<span style="color: #FF0000">'
	        										+json.totalCount+'</span>'+i18n('i18n.visualMarket.returningStatusCustCountTwo'));
	        							}
	        						 }else{
	        							 //创建制定维护计划的window
	        							var deliveryMainTainPopWindow=Ext.getCmp("deliveryMainTainPopWindow");//获取win
	             						if(!deliveryMainTainPopWindow){
	             							deliveryMainTainPopWindow=Ext.create('DeliveryMainTainPopWindow',{id:'deliveryMainTainPopWindow'});
	             						}
	
	        							deliveryMainTainPopWindow.show();
	        						 	//将固定客户的联系人id、客户id、客户类型放置到客户信息数组中
	        						 	for(var i = 0;i<selection.length;i++){
	        						 		//判断选中的客户是否在可制定计划的客户列表中
	        						 		if(Ext.Array.contains(json.custIdList,selection[i].data.custId)){
	        						 			custInfoForMakePlan.push(selection[i].data.custId);
	        						 		}
	        							}
	        							Ext.getCmp('searchMaintainResultGridId').store.loadPage(1);
	        							//清除已选表格的数据
	        							Ext.getCmp('maintainChooseCustomerGridId').store.removeAll();
	        							//重置计划信息panel
	        							Ext.getCmp("planFromPanel").getForm().reset();
	
	        							
	        							var deptName=Ext.getCmp('QueryCustomerFormId').getForm().findField('deptName').getRawValue();
	        							var deptId=Ext.getCmp('QueryCustomerFormId').getForm().findField('deptName').getValue();
	        							
	        							Ext.getCmp("planFromPanel").getForm().findField("execdeptid").setValue(deptName);
	        							Ext.getCmp("planFromPanel").getForm().findField("execuserid").store.load({params:{executeDept:deptId}});
//	        							Ext.getCmp("planFromPanel").getForm().findField("execuserid").expand();
//	        							Ext.getCmp("planFromPanel").getForm().findField("execuserid").collapse();
//	        							Ext.getCmp("planFromPanel").getForm().findField("execuserid").reset();
	        							
	        							if(!Ext.isEmpty(json.totalCount) && (json.totalCount > 0)){
	        								MessageUtil.showInfoMes(i18n('i18n.visualMarket.returningStatusCustCountOne')+'<span style="color: #FF0000">'
	        										+json.totalCount+'</span>'+i18n('i18n.visualMarket.returningStatusCustCountTwo'));
	        							}
	        						 }
	    						};
	    						var failureFn = function(json){
	    							th.enable();
	    							//抱歉，制定计划响应超时！
	    							if(Ext.isEmpty(json)){
	    								MessageUtil.showErrorMes(i18n('i18n.visualMarket.exceedTimeOfMakePlan'));
	    							}
	    							else{
	    								MessageUtil.showErrorMes(json.message);
	    							}
	    						};
	    						var param={
	    								'custIdList':custIdList,
	    								'custType':custType
	    						};
	    						MakePlanView.prototype.searchCustListToCreatePlan(param, successFn, failureFn);
		                	}
       		 			}
		           }]	
			}];
			this.callParent();
			//如果当前登陆的用户具有营业部经理的角色，启用制定计划按钮
			if(Ext.Array.contains(User.roleids,i18n('i18n.visualMarket.deptManagerRoleId'))){
				Ext.getCmp('btnForMakePlanId').show();
			}
		}
    });
    
    /************************beforeLoad的方法******************************/
    function storeBeforeLoadFunction (querybackFreightStore,operation){
		var queryFormPanel = Ext.getCmp('QueryCustomerFormId');	
		var queryForm = queryFormPanel.getForm();
      	var customerType=queryForm.findField('customerTypeId').getChecked();
      	var custNumber=queryForm.findField('customerName').getValue();
		//判断查询客户是否为空
		if(Ext.isEmpty(custNumber)&&customerType.length==0){
			MessageUtil.showErrorMes(i18n('i18n.visualMarket.canNotSearchWithNullCustType'));
			return;
		}
		//判断地方标记不能为空
		mapMakerStatus=queryForm.findField('mapMakerId').getValue();
		if(Ext.isEmpty(mapMakerStatus)){
			MessageUtil.showErrorMes(i18n('i18n.visualMarket.canNotSearchWithNullMark'));
			return;		
		}	
		//查询显示已经标记的客户
		//获得查询条件	
		var deptId=queryForm.findField('deptId').getValue();
		if(!/\d+/.test(deptId)){
			MessageUtil.showErrorMes(i18n('i18n.visualMarket.canNotSearchWithErrorDept'));
 			Ext.getCmp('deptId').setValue(User.deptId);
 			Ext.getCmp('deptId').standardCode=User.standardCode;
			return false;
		}
		var params={
				'queryCondition.deptId' :searchConditionParam.queryCondition.deptId,
				'queryCondition.custNumber' :searchConditionParam.queryCondition.custNumber,
				'queryCondition.mapMakerStatus' :searchConditionParam.queryCondition.mapMakerStatus,
				'queryCondition.custDegree' :searchConditionParam.queryCondition.custDegree,
				'queryCondition.psCustType' :searchConditionParam.queryCondition.psCustType,
				'queryCondition.returnVisitStatus' :searchConditionParam.queryCondition.returnVisitStatus,
				'queryCondition.incomeRestoreRate' :searchConditionParam.queryCondition.incomeRestoreRate,
				'queryCondition.exceedTime' :searchConditionParam.queryCondition.exceedTime,
				'queryCondition.custTrade' :searchConditionParam.queryCondition.custTrade,
				'queryCondition.monthlyIncome' :searchConditionParam.queryCondition.monthlyIncome,
				'queryCondition.sendGoodsNumber' :searchConditionParam.queryCondition.sendGoodsNumber,
				'queryCondition.goodsPotential' :searchConditionParam.queryCondition.goodsPotential,
				'queryCondition.custResource' :searchConditionParam.queryCondition.custResource,
				'queryCondition.custLabel' :searchConditionParam.queryCondition.custLabel,
				'queryCondition.sortType' :btnSortType,
				'queryCondition.sortMark' :btnSortMark,
				'queryCondition.createBeginTime':searchConditionParam.queryCondition.createBeginTime,
				'queryCondition.createEndTime':searchConditionParam.queryCondition.createEndTime
		};
		Ext.apply(operation,{	
			params : params
		});
	}
    
    /*************************************************************/
    
    var time="DOWN";
    //查询结果按钮
	Ext.define('CRM.Marketing.CustomerBtn', {
		extend:'BasicPanel',
		layout:'column',
		defaults:{
			xtype: 'button',
			columnWidth:.25,
			labelAlign:'right'
		},
		items:[{//当月货量
			text:i18n('i18n.visualMarket.monthlyGoods'),
			iconAlign:'right',
			id:'monthGoodCounts',
			cls:'sortCust',
			listeners:{
				'click':function(t){
					//按钮禁用三秒
					disBtn();
					if(Ext.isEmpty(monthGoodCounts)){
						monthGoodCounts="DOWN";
					}
					this.removeCls('sortCust');
					this.addCls('sortCust-curent');
					if(monthGoodCounts==="DOWN"){
						t.setIcon("../scripts/marketing/map/images/down.png");	
						monthGoodCounts="UP";
						btnSortType="monthGoodCounts";
					 	btnSortMark="DESC";					 	
						
					}else{
						t.setIcon("../scripts/marketing/map/images/up.png");	
						monthGoodCounts="DOWN";
						btnSortType="monthGoodCounts";
					 	btnSortMark="ASC";
					}
					if(Ext.isEmpty(searchConditionParam)){
						Ext.getCmp("revisitTime").setIcon("");
						Ext.getCmp("tradeTime").setIcon("");
						Ext.getCmp("createTime").setIcon("");
						
						Ext.getCmp("revisitTime").removeCls("sortCust-curent");
						Ext.getCmp("tradeTime").removeCls("sortCust-curent");
						Ext.getCmp("createTime").removeCls("sortCust-curent");
						
						Ext.getCmp("revisitTime").addCls("sortCust");
						Ext.getCmp("tradeTime").addCls("sortCust");
						Ext.getCmp("createTime").addCls("sortCust");
						return false;
					}
					customerMarketInfoStore.loadPage(1);
					Ext.getCmp("revisitTime").setIcon("");
					Ext.getCmp("tradeTime").setIcon("");
					Ext.getCmp("createTime").setIcon("");
					
					Ext.getCmp("revisitTime").removeCls("sortCust-curent");
					Ext.getCmp("tradeTime").removeCls("sortCust-curent");
					Ext.getCmp("createTime").removeCls("sortCust-curent");
					
					Ext.getCmp("revisitTime").addCls("sortCust");
					Ext.getCmp("tradeTime").addCls("sortCust");
					Ext.getCmp("createTime").addCls("sortCust");
				}
			}
		},{//交易时间
			text:i18n('i18n.visualMarket.dealTime'),
			iconAlign:'right',
			id:'tradeTime',
			cls:'sortCust',
			listeners:{
				'click':function(t){
					//按钮禁用三秒
					disBtn();
					if(Ext.isEmpty(tradeTime)){
						tradeTime="DOWN";
					}
					this.removeCls('sortCust');
					this.addCls('sortCust-curent');
					if(tradeTime==="DOWN"){
						t.setIcon("../scripts/marketing/map/images/down.png");	
						tradeTime="UP";
						btnSortType="tradeTime";
					 	btnSortMark="DESC";
					 	
					}else{
						t.setIcon("../scripts/marketing/map/images/up.png");	
						tradeTime="DOWN";
						btnSortType="tradeTime";
					 	btnSortMark="ASC";
					}
					if(Ext.isEmpty(searchConditionParam)){
						Ext.getCmp("revisitTime").setIcon("");
						Ext.getCmp("monthGoodCounts").setIcon("");
						Ext.getCmp("createTime").setIcon("");
						
						Ext.getCmp("revisitTime").removeCls("sortCust-curent");
						Ext.getCmp("monthGoodCounts").removeCls("sortCust-curent");
						Ext.getCmp("createTime").removeCls("sortCust-curent");	
						
						Ext.getCmp("revisitTime").addCls("sortCust");
						Ext.getCmp("monthGoodCounts").addCls("sortCust");
						Ext.getCmp("createTime").addCls("sortCust");
						return false;
					}
					customerMarketInfoStore.loadPage(1);
					Ext.getCmp("revisitTime").setIcon("");
					Ext.getCmp("monthGoodCounts").setIcon("");
					Ext.getCmp("createTime").setIcon("");
					
					Ext.getCmp("revisitTime").removeCls("sortCust-curent");
					Ext.getCmp("monthGoodCounts").removeCls("sortCust-curent");
					Ext.getCmp("createTime").removeCls("sortCust-curent");	
					
					Ext.getCmp("revisitTime").addCls("sortCust");
					Ext.getCmp("monthGoodCounts").addCls("sortCust");
					Ext.getCmp("createTime").addCls("sortCust");
				}
			}
		},{//回访时间
			text:i18n('i18n.returnVisit.executorTime'),
			iconAlign:'right',
			cls:'sortCust',
			id:'revisitTime',
			listeners:{
				'click':function(t){
					//按钮禁用三秒
					disBtn();
					if(Ext.isEmpty(revisitTime)){
						revisitTime="DOWN";
					}
					this.removeCls('sortCust');
					this.addCls('sortCust-curent');
					if(revisitTime==="DOWN"){
						t.setIcon("../scripts/marketing/map/images/down.png");	
						revisitTime="UP";
						btnSortType="revisitTime";
					 	btnSortMark="DESC";
					}else{
						t.setIcon("../scripts/marketing/map/images/up.png");	
						revisitTime="DOWN";
						btnSortType="revisitTime";
					 	btnSortMark="ASC";
					}
					if(Ext.isEmpty(searchConditionParam)){
						Ext.getCmp("tradeTime").setIcon("");
						Ext.getCmp("monthGoodCounts").setIcon("");
						Ext.getCmp("createTime").setIcon("");	
						
						Ext.getCmp("tradeTime").removeCls("sortCust-curent");
						Ext.getCmp("monthGoodCounts").removeCls("sortCust-curent");
						Ext.getCmp("createTime").removeCls("sortCust-curent");	
						
						Ext.getCmp("tradeTime").addCls("sortCust");
						Ext.getCmp("monthGoodCounts").addCls("sortCust");
						Ext.getCmp("createTime").addCls("sortCust");
						return false;
					}
					customerMarketInfoStore.loadPage(1);
					Ext.getCmp("tradeTime").setIcon("");
					Ext.getCmp("monthGoodCounts").setIcon("");
					Ext.getCmp("createTime").setIcon("");	
					
					Ext.getCmp("tradeTime").removeCls("sortCust-curent");
					Ext.getCmp("monthGoodCounts").removeCls("sortCust-curent");
					Ext.getCmp("createTime").removeCls("sortCust-curent");	
					
					Ext.getCmp("tradeTime").addCls("sortCust");
					Ext.getCmp("monthGoodCounts").addCls("sortCust");
					Ext.getCmp("createTime").addCls("sortCust");	
				}
			}
		},{//创建时间
			text:i18n('i18n.developPlan.createStartTime'),
			iconAlign:'right',
			cls:'sortCust',
			id:'createTime',
			listeners:{
				'click':function(t){
					//按钮禁用三秒
					disBtn();
					if(Ext.isEmpty(createTime)){
						createTime="DOWN";
					}
					this.removeCls('sortCust');
					this.addCls('sortCust-curent');
					if(createTime==="DOWN"){
						t.setIcon("../scripts/marketing/map/images/down.png");	
						createTime="UP";
						btnSortType="createTime";
					 	btnSortMark="DESC";
					 	
					}else{
						t.setIcon("../scripts/marketing/map/images/up.png");	
						createTime="DOWN";
						btnSortType="createTime";
					 	btnSortMark="ASC";
					}
					if(Ext.isEmpty(searchConditionParam)){
						Ext.getCmp("tradeTime").setIcon("");
						Ext.getCmp("monthGoodCounts").setIcon("");
						Ext.getCmp("revisitTime").setIcon("");
						
						Ext.getCmp("tradeTime").removeCls("sortCust-curent");
						Ext.getCmp("monthGoodCounts").removeCls("sortCust-curent");
						Ext.getCmp("revisitTime").removeCls("sortCust-curent");
						
						Ext.getCmp("tradeTime").addCls("sortCust");
						Ext.getCmp("monthGoodCounts").addCls("sortCust");
						Ext.getCmp("revisitTime").addCls("sortCust");
						return false;
					}
					customerMarketInfoStore.loadPage(1);
					
					Ext.getCmp("tradeTime").setIcon("");
					Ext.getCmp("monthGoodCounts").setIcon("");
					Ext.getCmp("revisitTime").setIcon("");
					
					Ext.getCmp("tradeTime").removeCls("sortCust-curent");
					Ext.getCmp("monthGoodCounts").removeCls("sortCust-curent");
					Ext.getCmp("revisitTime").removeCls("sortCust-curent");
					
					Ext.getCmp("tradeTime").addCls("sortCust");
					Ext.getCmp("monthGoodCounts").addCls("sortCust");
					Ext.getCmp("revisitTime").addCls("sortCust");
				}
			}
		}]
	});
	
	// 页面展示出来
	var viewport=Ext.create('Ext.Viewport',{
		layout : 'border',
		items:[
			Ext.create('QueryCustomerForm',{		
			id:'QueryCustomerFormId',
			region:'north',
			height:205,
			maxHeight:205,
			minHeight:205,
			collapsible:true,
			collapseMode:'mini',
			split:true,
			listeners:{
				//退出全屏
				collapse:function(){
					Ext.getCmp("fullButton").setText(i18n('i18n.visualMarket.exitFullScreen'));
					Ext.getCmp('customerDetailPanelId').collapse(Ext.Component.DIRECTION_RIGHT,true);
					isFullScreen=true;
				},
				//全屏
				expand:function(){
					Ext.getCmp("fullButton").setText(i18n('i18n.visualMarket.fullScreen'));
					Ext.getCmp('customerDetailPanelId').expand();
					isFullScreen=false;
				}
			}
		}),{
			xtype:'panel',
			region:'center',
			layout:'border',
			cls:'mapToolbar',
			tbar:[{//客户类型视图
				xtype:'button',
				cls:'mapTab-curent',
				text:i18n('i18n.visualMarket.custTypeView'),
				id:'cutstViewBtn',
				disabled:true,
				handler:function(){
					//如果当前已经是客户类型视图，则不再发送加载标注请求
					if(Ext.isEmpty(viewTypeOfSelectResult) || viewTypeOfSelectResult === 'CUSTVIEW'){
						return false;
					}
					//按钮禁用三秒
					disBtn();
					//标识是客户类型视图
					viewTypeOfSelectResult = 'CUSTVIEW';
					point.clearCustomerMarker();
					showCustMarker();	
					this.addCls('mapTab');
					Ext.getCmp('cutstViewBtn').removeCls('mapTab');
					Ext.getCmp('cutstViewBtn').addCls('mapTab-curent');
					Ext.getCmp('revisitViewBtn').removeCls('mapTab-curent');
					Ext.getCmp('revisitViewBtn').addCls('mapTab');
				}
			},{//回访状态视图
				xtype:'button',
				text:i18n('i18n.visualMarket.returnVisitView'),
				cls:'mapTab',
				id:'revisitViewBtn',
				disabled:true,
				handler:function(){
					//如果当前已经是回访状态类型视图，则不再发送加载标注请求
					if(viewTypeOfSelectResult === 'RETURNVISITVIEW'){
						return false;
					}
					//按钮禁用三秒
					disBtn();
					viewTypeOfSelectResult = 'RETURNVISITVIEW';
					point.clearCustomerMarker();
					showCustMarker();	
					//标识是回访类型视图

					Ext.getCmp('cutstViewBtn').removeCls('mapTab-curent');
					Ext.getCmp('cutstViewBtn').addCls('mapTab');
					Ext.getCmp('revisitViewBtn').removeCls('mapTab');
					Ext.getCmp('revisitViewBtn').addCls('mapTab-curent');

				}
			},'->',{//测距
				xtype:'button',
				text:i18n('i18n.visualMarket.mearsureDistance'),
				cls:'mapToolranging',
				listeners:{
					'click':function(){
						if(!Ext.isEmpty(myDis)){
							myDis.open();
						}
					}
				}
			},{//打印按钮
				xtype:'button',
				text:i18n('i18n.returnVisit.print'),
				cls:'mapToolprint',
				id:'printBtnId',
				handler:function(){
					Ext.getCmp('customerDetailPanelId').collapse(Ext.Component.DIRECTION_RIGHT,false);
					var url		= '../marketing/printVisualMarket.action';
					window.open(url,'可视营销地图打印预览','width='+(window.screen.availWidth-10)+
							',height='+(window.screen.availHeight-30));
				}
			},{//全屏
				xtype:'button',
				text:i18n('i18n.visualMarket.fullScreen'),
				id:'fullButton',
				cls:'mapToolfullScreen',
				listeners:{
					'click':function(){
							this.removeCls('mapToolfullScreen');
							this.removeCls('mapToolexitFull');
						if(isFullScreen){
							Ext.getCmp('QueryCustomerFormId').expand();
							Ext.getCmp('customerDetailPanelId').expand();
							this.setText(i18n('i18n.visualMarket.fullScreen'));
							this.removeCls('mapToolexitFull');
							this.addCls('mapToolfullScreen');
							isFullScreen=false;
						}else{
							Ext.getCmp('QueryCustomerFormId').collapse(Ext.Component.DIRECTION_TOP,true);
							Ext.getCmp('customerDetailPanelId').collapse(Ext.Component.DIRECTION_RIGHT,true);
							this.setText(i18n('i18n.visualMarket.exitFullScreen'));
							this.removeCls('mapToolfullScreen');
							this.addCls('mapToolexitFull');
							isFullScreen=true;
						}
					}
				}
			}],
			items:[{
				xtype:'basicpanel',
				id:'idForPrint',
				region:'center',
			    html :"<div id='map' style='width:100%;height:100%'></div>",
				listeners : {
 				     afterrender: function() {
 				     	var cm= new CRM_BMap( 'map',{center : "北京市", zoom : 12 },function(map) {
 				     		window.myDis = new BMapLib.DistanceTool(map);
 				     		
							var fun =function(data){
								alert(data);
							}
							window.point = cm.PointFeature(map,{callbackFun:fun});
							window.poly = cm.PolygonFeature(map,{});
							//延迟三秒钟加载部门图标和部门区域
							setTimeout(function(){
								//显示部门图标
								point.showDeptMarker(Ext.getCmp('deptId').standardCode);
								var deptId=Ext.getCmp('deptId').getValue();
								
								//根据部门id去查询该部门的营销区域
								var paramDetails = {
									'deptId':deptId
								};
								var successFn = function(json) {
									//启用部门控件
									Ext.getCmp('deptId').enable();
							 		var region=json.region;
							 		if(!Ext.isEmpty(region)){
							 			//调用GIS显示营销区域
										poly.showPolygonById(region.regionId);					 		
							 		}
								}
								var failFn=function(){
									//启用部门控件
									Ext.getCmp('deptId').enable();
									return;
								}
								//向后台发送请求得到营销区域,并显示
								visualMarketingData.prototype.searchRegionByDeptId(paramDetails,successFn,failFn);
							},3000);
					 });
 				   }  
				}
			},{
				xtype:'panel',
				width:280,
				maxWidth:280,
				minWidth:280,
				split:true,
				region:'east',
				layout:'border',
				id:'customerDetailPanelId',
				collapsible:true,
				collapsed:true,
				collapseMode:'mini',
				items:[
				 	Ext.create('CRM.Marketing.CustomerBtn',{region:'north',height:25}),
				    Ext.create('CRM.Marketing.CustomerDetail',{region:'center',id:'customerDetailId'})
				]
			}]
        }]
	});
	
	/**
	 * 单击回访客户，弹出的回访窗口
	 */
	Ext.define('CreateReturnVisitWindow',{
		extend:'PopWindow',
		title:i18n('i18n.visualMarket.returnVisitCustomer'),
		alias : 'widget.basicwindow',
		width:820,
		height:750,
		modal:true,
		layout:'fit',
		closeAction:'hide',
		items:[Ext.create('CustomerReturnVisitPanel',{'id':'customerReturnVisitPanel'})],
		listeners:{
			hide:function(){
				document.body.scrollLeft=0;
				document.body.scrollTop=0;
				document.getElementsByTagName("html")[0].style.overflowY="hidden";
				document.getElementsByTagName("html")[0].style.overflowX="hidden";
				viewport.doLayout();
			}
		}
	});
});
	/**
	 *  修改客户地址
	 * */
function modifyAddr(custId,custType,contactId,oldAddress){
	if(!Ext.isEmpty(custId) && !Ext.isEmpty(custType)) {
		//如果是固定客户，首先需要查询此固定客户的地址
		var params = null;
		//如果是固定客户类型
//		if(/*custType == 'MEMBER' && */(Ext.isEmpty(oldAddress) || oldAddress === '')){
			//成功方法
			var doSuccessFn = function(result){
				oldAddress=result.custAddress;
				//修改地址的页面
				var custAddressWin = Ext.create('CustAddressWin',{custId:custId,custType:custType,contactId:contactId,oldAddress:oldAddress});
				custAddressWin.show();
			}
			//失败方法
			var doFailFn = function(result){
				oldAddress = "";
				//修改地址的页面
				var custAddressWin = Ext.create('CustAddressWin',{custId:custId,custType:custType,contactId:contactId,oldAddress:oldAddress});
				custAddressWin.show();
			}
			//封装参数
			params={
					'custId' :custId,
					'custType':'MEMBER'
			};
			//发送请求
			visualMarketingData.prototype.findCustomerAddressByCustId(params,doSuccessFn,doFailFn);
//		}
//		else{
//			//修改地址的页面
//			var custAddressWin = Ext.create('CustAddressWin',{custId:custId,custType:custType,contactId:contactId,oldAddress:oldAddress});
//			custAddressWin.show();
//		}
	}
}
/**
 * 单击保存客户标注后gis的回调函数，保存标记后坐标点
 * 
 * */
function markCustomerLocation (obj,p){
	//首先判断gis回传过来的对象和坐标是否为空
	if(Ext.isEmpty(obj) || Ext.isEmpty(p)){
		//该客户地址不够详细，请完善地址后再进行标注！
//		MessageUtil.showErrorMes(i18n('i18n.visualMarket.canNotMarkWithShortAddr'));
		MessageUtil.showQuestionMes(i18n('i18n.visualMarket.mendCustAddrOrNot'),function(e){
			if (e == 'yes') {
				if (Ext.isEmpty(Ext.getCmp('CustAddressWin'))){
					modifyAddr(obj.custId,obj.custType,obj.contactId,obj.address);		
				}	
			}else{
				this.close();
				return;
			}
		});
		return false;
	}
	var params={
		'location.customerId':obj.custId,
		'location.customerType':obj.custType,
		'location.lat':p.lat,
		'location.lng':p.lng,
		'location.linkmanId':obj.contactId
		};	
	var delSuccessFn = function(result){
		point.clearPoiMarker();
		MessageUtil.showInfoMes(i18n('i18n.visualMarket.markSuccess'));
		customerMarketInfoStore.load();
		if(Ext.isEmpty(markCustInfos)){
			markCustInfos = new Array();
		}
		//改变保存在全局变量中的相应客户的经度、维度值
		//判断标注的客户是否是未曾在地图上显示过的客户，如果是则在全局变量markCustInfos中增加一个客户信息
		var isAddNewMarkOrNot = true;
		for(var i = 0;i < markCustInfos.length ; i++){
			if(markCustInfos[i].custId === obj.custId){
				markCustInfos[i].longitude = p.lng;
				markCustInfos[i].latitude = p.lat;
				isAddNewMarkOrNot = false;
				break;
			}
		}
		if(isAddNewMarkOrNot){
			if(!Ext.isEmpty(obj) && obj.custType == 'MEMBER'){
				markCustInfos.push({
					'longitude':p.lng,
					'latitude':p.lat,
					'custId':obj.custId,
					'custDegree':obj.custDegree,
					'deptId':obj.deptId,
					'psCustId':'',
					'custType':obj.custType,
					'returnVisitStatus':obj.returnVisitStatus
				});
				custDegreeOrCustType = obj.custDegree;
			}
			else{
				markCustInfos.push({
					'longitude':p.lng,
					'latitude':p.lat,
					'custId':'',
					'custDegree':'',
					'deptId':obj.deptId,
					'psCustId':obj.custId,
					'custType':obj.custType,
					'returnVisitStatus':obj.returnVisitStatus
				});
				custDegreeOrCustType = obj.custType;
			}
		}
	};
	//保存失败回调函数
	var delFailFn = function(result){
		point.clearPoiMarker();
		if(Ext.isEmpty(result)){
			MessageUtil.showErrorMes(i18n('i18n.visualMarket.exceedTimeOfMarkCust'));
		}
		else{
			MessageUtil.showErrorMes(result.message);
		}
	};	
	mapData.prototype.markCustomerLocation(params,delSuccessFn,delFailFn);
}

/**
 * 是否重新标注
 * @param {} address
 * @param {} custId
 * @param {} custType
 * @param {} contactId
 * @param {} returnVisitStatus
 * @param {} longitude 经度
 * @param {} latitude 纬度
 */
function isMakeCustomerByAddress(address,custId,custType,contactId,returnVisitStatus,longitude,latitude){
    if(!Ext.isEmpty(longitude)&&!Ext.isEmpty(latitude)){
        MessageUtil.showQuestionMes(i18n('i18n.visualMarket.isRemark'),function(e){
           if (e == 'no') {
                //去掉在地图上的标注选择点
                point.clearPoiMarker();
                this.close();
                return;
           }else{
                makeCustomerByAddress(address,custId,custType,contactId,returnVisitStatus)
           }
        });
    }else{
        makeCustomerByAddress(address,custId,custType,contactId,returnVisitStatus)
    }
}

/**
 * 根据地址显示客户位置并标记
 * 
 * */
function makeCustomerByAddress(address,custId,custType,contactId,returnVisitStatus){
	//首先禁用用户恶意连续点击“标注”图标，禁用此按钮两秒钟
	setTimeout(function(){
		clickToShowMarkForCustomer = true;   		        		
	},2000);
	
	if(clickToShowMarkForCustomer){
		clickToShowMarkForCustomer = false;
		point.clearPoiMarker();
		
		//找到列表
		var customerDetailGrid = Ext.getCmp("customerDetailId");
		//获取表格中选中的记录
		var selection = customerDetailGrid.getSelectionModel().getSelection();
		
		
		
		//如果为空则返回
		if(Ext.isEmpty(selection[0])){
			return ;
		}
		//待标注的客户的城市名称
		var cityName = '';
		//成功方法
		var doSuccessFn = function(result){
			address=result.custAddress;
			cityName = result.cityName;
			//如果点击yes则弹出
			if(Ext.isEmpty(address)){
				MessageUtil.showQuestionMes(i18n('i18n.visualMarket.mendCustAddrOrNot'),function(e){
					if (e == 'yes') {
						if (Ext.isEmpty(Ext.getCmp('CustAddressWin'))){
						modifyAddr(custId,custType,contactId,address);		
					}	
					}else{
						this.close();
						return;
					}
				});
			}else{
				//判断当前是何种视图
				var type = '';
				var customerDetailGrid = Ext.getCmp("customerDetailId");
				//获取表格中选中的记录
				var selection = customerDetailGrid.getSelectionModel().getSelection();
				if(Ext.isEmpty(viewTypeOfSelectResult) || viewTypeOfSelectResult === 'CUSTVIEW'){
					if(custType === 'MEMBER'){
						type = selection[0].get('custDegree');
					}
					else{
						type = custType;	
					}
				}
				else{
					type = returnVisitStatus;
				}

				var obj={
					address:address,
					custId:custId,
					custType:custType,
					contactId:contactId,
					type:type,
					callback:showCustomerDetail,
					returnVisitStatus:selection[0].get('returnVisitStatus'),//回访状态
					custDegree:selection[0].get('custDegree'),//固定客户客户等级
					deptId:selection[0].get('deptId'),//部门id
					city:cityName//城市名称
				}
				point.showCustomerByAddress(obj ,markCustomerLocation);
			}
		}
		//失败方法
		var doFailFn = function(result){
			
		}
		
		var params = null;
		//封装参数
		params={
				'custId' :custId,
				'custType' :custType
		};
		//发送请求
		visualMarketingData.prototype.findCustomerAddressByCustId(params,doSuccessFn,doFailFn);
	}
}
/**
 * 获得查询的参数
 * */
function getParams(){
	var queryFormPanel = Ext.getCmp('QueryCustomerFormId');
	var queryForm = queryFormPanel.getForm();
	var customerType=queryForm.findField('customerTypeId').getChecked();
    var custNumber=queryForm.findField('customerName').getValue();
	mapMakerStatus=queryForm.findField('mapMakerId').getValue();
				
	//查询显示已经标记的客户
	//获得查询条件	
	var deptId=queryForm.findField('deptId').getValue();			
	//固定客户编码
	var custNumber=queryForm.findField('customerName').getValue();
	//地图标记列表
	mapMakerStatus=queryForm.findField('mapMakerId').getValue();

	var custCheckgroup=queryForm.findField('customerTypeId').getChecked();
	//客户等级
	var custDegreeArray=new Array();
	for (var i = 0; i < custCheckgroup.length; i++){
	  	custDegreeArray.push(custCheckgroup[i].inputValue);
	  	
	}
	if(Ext.Array.contains(custDegreeArray,'SC_CUSTOMER')||Ext.Array.contains(custDegreeArray,'PC_CUSTOMER')){
		//非固定客户客户类型
		var psCustType=custDegreeArray;
	}
	else{
		//固定客户类型列表(客户等级)
		var custDegree=custDegreeArray;
	}
	//回访状态
	var returnVisitStatus=queryForm.findField('reVisitStatusId').getChecked();
	var returnVisitStatusArray=new Array();
	for (var i = 0; i < returnVisitStatus.length; i++){
  		returnVisitStatusArray.push(returnVisitStatus[i].inputValue);
	}
	//收入恢复进度
	var incomeRestoreRate=queryForm.findField('incomeProgressId').getChecked();
	var incomeRestoreRateArray=new Array();
	for (var i = 0; i < incomeRestoreRate.length; i++){
		incomeRestoreRateArray.push(incomeRestoreRate[i].inputValue);
	}
	//超期时长
	var exceedTime=queryForm.findField('extendedTimeId').getChecked();
	var exceedTimeArray=new Array();
	for (var i = 0; i < exceedTime.length; i++){
	  	exceedTimeArray.push(exceedTime[i].inputValue);
	}
	//客户行业
	var custTrade=queryForm.findField('customTradeId').getChecked();
	var custTradeArray=new Array();
	for (var i = 0; i < custTrade.length; i++){
	  	custTradeArray.push(custTrade[i].inputValue);
	}
	//当月收入
	var  monthlyIncome=queryForm.findField('monthIncomeId').getChecked();
	var monthlyIncomeArray=new Array();
	for (var i = 0; i < monthlyIncome.length; i++){
	  	monthlyIncomeArray.push(monthlyIncome[i].inputValue);
	}
	//发货票数
	var sendGoodsNumber=queryForm.findField('goodsTimesId').getChecked();
	var sendGoodsNumberArray=new Array();
	for (var i = 0; i < sendGoodsNumber.length; i++){
	  	sendGoodsNumberArray.push(sendGoodsNumber[i].inputValue);
	}
	//货量潜力
	var goodsPotential=queryForm.findField('goodsPotentialId').getChecked();
	var goodsPotentialArray=new Array();
	for (var i = 0; i < goodsPotential.length; i++){
	  	goodsPotentialArray.push(goodsPotential[i].inputValue);
	}
	//客户来源
	var custResource=queryForm.findField('customerSourceId').getChecked();
	 
	var custResourceArray=new Array();
	for (var i = 0; i < custResource.length; i++){
	  	custResourceArray.push(custResource[i].inputValue);
	}
				 
	//客户标签id
	var custLabel=new Array();
	var customerLabelGroup=null;
	var customerLabelGroup1=null;
	if(!Ext.isEmpty(queryForm.findField('displayCustomerLabel'))){
		customerLabelGroup=null;
	}else if(Ext.isEmpty(queryForm.findField('customerLabelGroup1'))){
		customerLabelGroup=queryForm.findField('customerLabelGroup').getChecked();
	}else{
		customerLabelGroup=queryForm.findField('customerLabelGroup').getChecked();
		customerLabelGroup1=queryForm.findField('customerLabelGroup1').getChecked()
	}
	var custLabelArray=new Array();
	if(Ext.isEmpty(queryForm.findField('displayCustomerLabel'))){
		for (var i = 0; i < customerLabelGroup.length; i++){
			custLabelArray.push(customerLabelGroup[i].inputValue);
		}
		if(!Ext.isEmpty(queryForm.findField('customerLabelGroup1'))){
		   for (var i = 0; i < customerLabelGroup1.length; i++){
			  custLabelArray.push(customerLabelGroup1[i].inputValue);
		   }
		}
	}
				 
	sortType=btnSortType;
	sortMark=btnSortMark;
	
	//创建时间
	var createBeginTime=queryForm.findField('createBeginTime').getValue();
	var createEndTime=queryForm.findField('createEndTime').getValue();
	var paramDetails = {'queryCondition':{'deptId'	:deptId,
		'custNumber':custNumber,
		'mapMakerStatus':mapMakerStatus,
		'custDegree':custDegree,
		'psCustType':psCustType,
		'returnVisitStatus':returnVisitStatusArray,
		'incomeRestoreRate':incomeRestoreRateArray,
		'exceedTime':exceedTimeArray,
		'custTrade':custTradeArray,
		'monthlyIncome':monthlyIncomeArray,
		'sendGoodsNumber':sendGoodsNumberArray,
		'goodsPotential':goodsPotentialArray,
		'custResource':custResourceArray,
		'custLabel':custLabelArray,
		'sortType':sortType,
		'sortMark':sortMark,
		'createBeginTime':createBeginTime,
		'createEndTime':createEndTime}
	};
	return paramDetails;
}
/**
 * 根据查询结果转换得
 * 
 * **/
function  changeMakerInfo(markCustInfos){
	var CusttAddr=new Array();
	//如果传入的类型为客户 则设置客户类型
	if(Ext.isEmpty(viewTypeOfSelectResult) || viewTypeOfSelectResult === 'CUSTVIEW'){
		//如果不为空执行这个方法
		if(!Ext.isEmpty(markCustInfos)){
			//循环遍历
			for( i=0;i<markCustInfos.length;i++){
				//如果是固定客户的话就把固定客户id传进去
				if(markCustInfos[i].custType=='MEMBER'){
					CusttAddr.push({'longitude':markCustInfos[i].longitude,'latitude':markCustInfos[i].latitude,
						'custId':markCustInfos[i].custId,'type':markCustInfos[i].custDegree,'deptId':markCustInfos[i].deptId				
					});
				//如果是潜散客则潜散客id
				}else{
					CusttAddr.push({'longitude':markCustInfos[i].longitude,'latitude':markCustInfos[i].latitude,
						'custId':markCustInfos[i].psCustId,'type':markCustInfos[i].custType,'deptId':markCustInfos[i].deptId
					});	
				}
			}
		}
	//如果传入的类型为回访 则设置回访类型
	}else if(viewTypeOfSelectResult === 'RETURNVISITVIEW'){
		//如果不为空执行这个方法
		if(!Ext.isEmpty(markCustInfos)){
			//循环遍历
			for( i=0;i<markCustInfos.length;i++){
				//如果是固定客户的话就把固定客户id传进去
				if(markCustInfos[i].custType=='MEMBER'){
					CusttAddr.push({'longitude':markCustInfos[i].longitude,'latitude':markCustInfos[i].latitude,
						'custId':markCustInfos[i].custId,'type':markCustInfos[i].returnVisitStatus,'deptId':markCustInfos[i].deptId
				});
				//如果是潜散客则潜散客id
				}else{
					CusttAddr.push({'longitude':markCustInfos[i].longitude,'latitude':markCustInfos[i].latitude,
						'custId':markCustInfos[i].psCustId,'type':markCustInfos[i].returnVisitStatus,'deptId':markCustInfos[i].deptId
					});
				}
			}
		}
	}
	return CusttAddr;
}

/**
 * 展示客户标记的方法
 * */
function showCustMarker(){
	//从全局变量里取值
	var custAddr=changeMakerInfo(markCustInfos);
	window.point.showCustomerByPoint(custAddr,showCustomerDetail);
}
/**
 * 
 * 测最短行车距离
 * 
 */
function  showCustToDeptDistance(lng,lat){
	//首先禁用用户恶意连续点击“测距”图标，禁用此按钮1秒钟
	setTimeout(function(){
		clickToShowPathForCustomer = true;   		        		
	},1000);
	if(clickToShowPathForCustomer){
		clickToShowPathForCustomer = false;
		function showDistance(dis){
			if(Ext.isEmpty(dis)){
				MessageUtil.showErrorMes(i18n('i18n.visualMarket.canNotFindPath'));
				return;
			}
			MessageUtil.showInfoMes(dis/1000+i18n('i18n.visualMarket.kiloMetre'));
		}
		var standardCode= Ext.getCmp('deptId').standardCode;
		point.showCustToDeptDistance(lng,lat,standardCode,showDistance);
	}
}
/**
 * 按钮禁用3秒防止恶意发送请求
 * 
 */
function disBtn(){
	Ext.getCmp('searchBtnId').disable();
	Ext.getCmp('monthGoodCounts').disable();
	Ext.getCmp('revisitTime').disable();
	Ext.getCmp('tradeTime').disable();
	Ext.getCmp('createTime').disable();
	Ext.getCmp('cutstViewBtn').disable();
	Ext.getCmp('revisitViewBtn').disable();
	
	setTimeout(function(){
		Ext.getCmp('searchBtnId').enable();
		//如果查询时的查询条件为空
		if(!Ext.isEmpty(searchConditionParam) && !Ext.isEmpty(searchConditionParam.queryCondition)
				 && !Ext.isEmpty(searchConditionParam.queryCondition.psCustType)
				&& ((searchConditionParam.queryCondition.psCustType[0] === 'SC_CUSTOMER') || 
						(searchConditionParam.queryCondition.psCustType[0] === 'PC_CUSTOMER'))){
			Ext.getCmp("monthGoodCounts").disable();
			Ext.getCmp("tradeTime").disable();
		}
		else {
			Ext.getCmp("monthGoodCounts").enable();
			Ext.getCmp("tradeTime").enable();
		}
		Ext.getCmp('revisitTime').enable();
		Ext.getCmp('createTime').enable();
		Ext.getCmp('cutstViewBtn').enable();
		Ext.getCmp('revisitViewBtn').enable();      		        		
	},3000);
}