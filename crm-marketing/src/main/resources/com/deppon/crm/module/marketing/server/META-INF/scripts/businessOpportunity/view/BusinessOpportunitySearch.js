/**.
 * <p>
 * 商机查询相关界面元素<br/>
 * <p>
 */


var businessOpportunityData = new BusinessOpportunityData();
var EMP = null;//上级负责人选项
var isMainMan = false;//是否本部门负责人
/**
 * .
 * <p>
 * 商家管理主页面</br>
 * </p>
 * @author 张斌
 * @时间 2014-03-7
 */
 Ext.onReady(function() {
    if(Ext.toolbar.Paging){//防止翻页空间出现英文
        Ext.apply(Ext.PagingToolbar.prototype, {
           beforePageText : "第",
           afterPageText  : "页,共 {0} 页",
           firstText      : "第一页",
           prevText       : "上一页",
           nextText       : "下一页",
           lastText       : "最后页",
           refreshText    : "刷新",
           displayMsg     : "显示 {0} - {1}条，共 {2} 条",
           emptyMsg       : '没有数据'
        });
     }
	var keys=[
	  		// 商机阶段
	  		'BUSINESS_OPPORTUNITY_STEP',
    		//商机状态
    		'BUSINESS_OPPORTUNITY_STATUS',
    		//一级行业
    		'TRADE',
    		//二级行业
    		'SECOND_TRADE',
    		//合作意向
    		'COOPERATION_INTENTION',
    		//开发阶段
    		'DEVELOP_STAGE',
    		//业务类型（零担、快递、零担快）
    		'CUST_CATEGORY',
    		//客户来源
    		'CUST_SOURCE',
    		//关闭原因
    		'BO_CLOSE_REASON',
    		//'日程状态'
    		'SCHEDULE_STATUS',
    		//联系人证件类型
    		'CARDTYPECON',
    		//性别
    		'GENDER',
    		//物流决定权
    		'LOGIST_DECI',
    		//联系人类型
    		'LINKMANTYPE',
    		//营销方式
    		'DEVELOP_WAY'
	  	];
	 //初始化业务参数
	 initDataDictionary(keys);
	 DataDictionary.LINKMANTYPE.push({code:'0',codeDesc:i18n('i18n.MemberCustEditView.financeContact')});//财务联系人
	 DataDictionary.LINKMANTYPE.push({code:'1',codeDesc:i18n('i18n.MemberCustEditView.businessContact')});//业务联系人
	 DataDictionary.LINKMANTYPE.push({code:'2',codeDesc:i18n('i18n.MemberCustEditView.deliverContact')});//发货联系人
	 DataDictionary.LINKMANTYPE.push({code:'3',codeDesc:i18n('i18n.MemberCustEditView.receiveContact')});//收货联系人
	 DataDictionary.LINKMANTYPE.push({code:'4',codeDesc:i18n('i18n.MemberCustEditView.agreementContact')});//协议联系人
	 initDeptAndUserInfo();//获取当前登录人信息
	 businessOpportunityData.getEmpByDeptId();//获取当前可以选择的商机负责人
	 businessOpportunityData.isMainPerson();//判断当前登录人是否本部门负责人
	 Ext.QuickTips.init(); 
 	 new CRM.BO.BusinessOpportunityViewport();
 });
 /**
  * .
  * <p>
  * 商机查询FORM<br/>
  * </p>
  * @returns CRM.BO.BusinessOpportunitySearch.SearchForm 商机查询FORM 的EXT对象
  * @author 张斌
  * @时间 2014-03-7
  */
 Ext.define('CRM.BO.BusinessOpportunitySearch.SearchForm', {
 		extend:'SearchFormPanel',
 		height:130,
 		region : 'north',
 		/**
 		 * .
 		 * <p>
 		 * 获取FORM ITEMS属性 </br>
 		 * </p>
 		 * @author 张斌
 		 * @时间 2014-03-07
 		 */
 		getItems:function(){
 			var me = this;
 			var all = {code:'',codeDesc:i18n('i18n.businessOpportunity.all')};
 			var storeStep = getDataDictionaryByName(DataDictionary,'BUSINESS_OPPORTUNITY_STEP');
 			var storeStatus = getDataDictionaryByName(DataDictionary,'BUSINESS_OPPORTUNITY_STATUS');
 			storeStep.add(all);
 			storeStatus.add(all);
 			return [{
 	            	xtype: 'fieldcontainer',
 		            defaultType: 'textfield',
 		            layout:{
 						type:'table',
 						columns:3
 					},
 					defaultType:'textfield',
 					defaults:{
 						enableKeyEvents:true,
 						labelSeparator:'',
 						labelWidth:100,
 						width:230,
 						listeners:{
 							scope : me,						// 作用域修改为整个form
 							keypress : me.keypressEvent
 						}
 					},
 		            items: [{
 		                xtype: 'textfield',
 	                    name: 'boName',
 	                    maxLength:50,
 	                    fieldLabel: i18n('i18n.businessOpportunity.boName')//商机名称
 	                },{
 	                    xtype: 'textfield',
 	                    name: 'boNumber',
                        maxLength:20,
 	                    fieldLabel: i18n('i18n.businessOpportunity.boNumber')//商机编码
 	                },{
//	 	       			xtype : "membersearchcombox",
//	 	                maxLength:170,
//	 	       			queryMode : "local",
//	 	       			editable:false,
//	 	       			setValueComeBack : function(memberRecord, addressRecord) {
//	 	       				this.setValue(memberRecord.get('custId'));
//	 	       			    this.setRawValue(memberRecord.get('custName'));
//	 	       			},
//	 	       			displayField : "custName",
//	 	       			valueField : "custId",
	 	       			name:'customerId',
	 	       			maxLength : 170,
 	                    fieldLabel: i18n('i18n.businessOpportunity.customerName')//客户名称
 		             },{
 		            	xtype: 'textfield',
 	                    name: 'customerNum',
 	                    maxLength:40,
 	                    fieldLabel: i18n('i18n.businessOpportunity.customerNum')//客户编号
 		             },{
 		            	xtype: 'textfield',
 	                    name: 'contactName',
 	                    maxLength:40,
 	                    fieldLabel: i18n('i18n.businessOpportunity.contactName')//联系人名称
 		             },{
 		            	xtype: 'textfield',
 	                    name: 'contactMobile',
 	                    regex:/(^1\d{10}$)|(^\d{8}$)/,
 	      		        regexText:i18n('i18n.marketRecord.marketRecordErrorTelephone'),
 	                    fieldLabel: i18n('i18n.businessOpportunity.contactMobile')//联系人手机
 		             },{
 		            	xtype: 'textfield',
 	                    name: 'contactPhone',
 	                    regex:/^\d{3}[\d\*-\/]{4,37}$/,
 	      			    regexText:i18n('i18n.marketRecord.marketRecordErrorMobile'),
 	                    fieldLabel: i18n('i18n.businessOpportunity.contactPhone')//联系人电话
 		             },{
 	                    xtype:          'combo',
 	                    mode:           'local',
 	                    value:'',
 	                    triggerAction:  'all',
 	                    editable:false,
 	                    forceSelection: true,
 	                    fieldLabel:     i18n('i18n.businessOpportunity.boStatus'),//商机状态
 	                    name:'boStatus',
 	                    displayField:   'codeDesc',
 	                    valueField:     'code',
 	                    queryMode: 'local',
 	                    store:storeStatus
 	                },{
 	                    xtype:          'combo',
 	                    mode:           'local',
 	                    value:'',
 	                    triggerAction:  'all',
 	                    editable:false,
 	                    forceSelection: true,
 	                    fieldLabel:     i18n('i18n.businessOpportunity.boStep'),//商机阶段
 	                    name:'boStep',
 	                    displayField:   'codeDesc',
 	                    valueField:     'code',
 	                    queryMode: 'local',
 	                    store:storeStep
 	                },{
 		                xtype     : 'datefield',
 	                    name      : 'beginTime',
 	                    value:new Date((new Date).getFullYear(),(new Date).getMonth(),(new Date).getDate()-30),
 		                fieldLabel: i18n('i18n.businessOpportunity.createTime'),//创建时间
 		                editable:false,
 		                format: 'Y-m-d'
 		            }, {
 		                xtype     : 'datefield',
 	                    name:      'endTime',
 	                    maxValue:new Date(),
 		                fieldLabel: '-',
 		                value:new Date(),
 		                editable:       false,
 		                format: 'Y-m-d',
 		                labelSeparator:'',
 						labelWidth:6,
 						width:144
 		            }]
 			}];
 		},
 		//提供回车查询
 		keypressEvent:function(field,event){
 			var me = this;
 			if(event.getKey() == Ext.EventObject.ENTER){
                eachTextFieldTrim(me.getForm());
 				if(me.getForm().isValid()){
		    		var beginTime = me.getForm().findField('beginTime').getValue();
		        	var endTime =  me.getForm().findField('endTime').getValue();
		        	if(((endTime.getTime()-beginTime.getTime())/(24*60*60*1000))>365){
		        		MessageUtil.showMessage(i18n('i18n.businessOpportunity.not365'));
		        		return;
		        	}else if(((endTime.getTime()-beginTime.getTime())/(24*60*60*1000))<0){
		        		MessageUtil.showMessage(i18n('i18n.businessOpportunity.notbeginmoreend'));
		        		return;
		        	}else{
		        		me.up().down('grid').getStore().loadPage(1);
		        	}
		    	}
 				
 		    }
 		},
 		initComponent:function()
 		{
 			var me = this;
 			me.items = me.getItems();
 			this.callParent();
 		}
 });
 /**
  * .
  * <p>
  * 商机查询按钮设置<br/>
  * </p>
  * @returns CRM.BO.BusinessOpportunitySearch.Button 商机查询按钮
  * @author 张斌
  * @时间 2014-03-8
  */
 Ext.define('CRM.BO.BusinessOpportunitySearch.Button',{
 	extend:'NormalButtonPanel',
 	items:null,
 	height:35,
 	region : 'center',
 	addWin:null,//新增商机窗口
 	shiwWin:null,//查看商机详情win
 	updateWin:null,//修改上级详情win
 	initComponent:function(){
 		this.items = this.getItems();
 		this.callParent();
 	},
 	/**
	 * .
	 * <p>
	 * 获取新增 </br>
	 * </p>
	 * @author 张斌
	 * @时间 2014-03-08
	 */
 	getAddWin:function(){
 		var me  = this;
 		if(Ext.isEmpty(me.addWin)){
 			me.addWin = new CRM.BO.BusinessOpportunityAdd.Window({'parent':me});
 		}
 		return me.addWin;
 	},
 	/**
	 * .
	 * <p>
	 * 获取查看详情win </br>
	 * </p>
	 * @author 张斌
	 * @时间 2014-03-12
	 */
 	getShowWin:function(){
 		var me  = this;
 		if(Ext.isEmpty(me.shiwWin)){
 			me.shiwWin = new CRM.BO.BusinessOpportunityView.Window({'parent':me});
 		}
 		return me.shiwWin;
 	},
 	/**
	 * .
	 * <p>
	 * 获取修改win </br>
	 * </p>
	 * @author 张斌
	 * @时间 2014-03-14
	 */
 	getUpdateWin:function(){
 		var me  = this;
 		if(Ext.isEmpty(me.updateWin)){
 			me.updateWin = new CRM.BO.BusinessOpportunityUpdate.Window({'parent':me});
 		}
 		return me.updateWin;
 	},
 	/**
	 * .
	 * <p>
	 * 获取按钮元素 ITEMS属性 </br>
	 * </p>
	 * @author 张斌
	 * @时间 2014-03-08
	 */
 	getItems:function(){
 		var me = this;
 		return [{
 			xtype:'leftbuttonpanel', 
 			items:[{
 				xtype:'button',
 				hidden:!isMainMan,
 				text:i18n('i18n.businessOpportunity.add'),//'新建'
 				handler:function(){
 					me.getAddWin().show();
 				}
 			},{
 				xtype:'button',
 				text:i18n('i18n.questionManage.searchButton.obtainDetail'),//查看详情
 				handler:function(){
 					var selections = me.up().down('grid').getSelectionModel( ).getSelection( );//获取选中数据
 					var showWin = me.getShowWin();//获取弹出窗口
 					if(selections.length!=1){
 						MessageUtil.showMessage(i18n('i18n.businessOpportunity.selectOne'));
 						return;
 					};
 					var successFn = function(json){
 						showWin.businessOpportunity = json.businessOpportunity;
 						showWin.show();
 					};
 					var failureFn = function(json){
 					    MessageUtil.showErrorMes(json.message);
 					};
 					var param = {'businessOpportunityId':selections[0].get('id')};//构建请求数据
 					businessOpportunityData.queryBo(param,successFn,failureFn);
 				}
 			},{
 				xtype:'button',
 				text:i18n('i18n.questionManage.searchButton.modifyQuestion'),//修改
 				handler:function(){
 					var selections = me.up().down('grid').getSelectionModel( ).getSelection( );//获取选中数据
 					var updateWin = null;
 					if(Ext.isEmpty(me.updateWin)){
 						updateWin = me.getUpdateWin();//获取弹出窗口
 						updateWin.show();
 						updateWin.hide();
 					}else{
 						updateWin = me.getUpdateWin();//获取弹出窗口
 					}
 					if(selections.length!=1){
 						MessageUtil.showMessage(i18n('i18n.businessOpportunity.selectOneUpdate'));//选择一条进行修改
 						return;
 					};
 					var isOperator = false;//是否为该商机的负责人
 					if(selections[0].get('operator').id==User.empId){
 						isOperator = true;
 					}
 					if(selections[0].get('boStatus')=='FAILURE'||selections[0].get('boStatus')=='SUCCESS'){//关闭成功和失败状态不能修改上级
 						MessageUtil.showMessage(i18n('i18n.businessOpportunity.statusNotUpdate'));//'抱歉，该状态商机不可以修改'
 						return;
 					}
 					//如果该商机不是当前登录人所在部门创建的，则没有权限修改
 					if(selections[0].get('deptId')!=User.deptId){
 						MessageUtil.showMessage(i18n('i18n.businessOpportunity.notDeptnoAuthoritycont'));//提示没有权限
 						return;
 					};
 					if(!isOperator&&!isMainMan){//既不是当前登录人所在部门的负责人，也不是该商机的负责人，则没有权限修改该商机
 						MessageUtil.showMessage(i18n('i18n.businessOpportunity.noAuthoritycontUpdate'));//提示没有权限
 						return;
 					}
 					var successFn = function(json){
 						updateWin.businessOpportunity = json.businessOpportunity;
 						updateWin.isOperator = isOperator;
 						updateWin.isMainMan = isMainMan;
 						updateWin.show();
 					};
 					var failureFn = function(json){
 					    MessageUtil.showErrorMes(json.message);
 					};
 					var param = {'businessOpportunityId':selections[0].get('id')};//构建请求数据
 					businessOpportunityData.queryBo(param,successFn,failureFn);
 				}
 			}]
 		},{
 			xtype:'middlebuttonpanel'
 		},{
 			xtype:'rightbuttonpanel',
 			width:260,
 			items:[{
 	            xtype:'button',
 	            text: i18n('i18n.developPlan.search'),//查询
 	            handler:function(){
 	            	var me = this;
 	            	var form = me.up().up().up().down('form');
                    eachTextFieldTrim(form.getForm());
 	    			if(form.getForm().isValid()){
 	   		    		var beginTime = form.getForm().findField('beginTime').getValue();
 	   		        	var endTime =  form.getForm().findField('endTime').getValue();
 	   		        	if(((endTime.getTime()-beginTime.getTime())/(24*60*60*1000))>365){
 	   		        	    MessageUtil.showMessage(i18n('i18n.businessOpportunity.not365'));
 	   		        		return;
 	   		        	}else if(((endTime.getTime()-beginTime.getTime())/(24*60*60*1000))<0){
 	   		        	    MessageUtil.showMessage(i18n('i18n.businessOpportunity.notbeginmoreend'));
 	   		        		return;
 	   		        	}else{
 	   		        	    me.up().up().up().down('grid').getStore().loadPage(1);
 	   		        	}
 	   		    	}
 	    				
 	            	
 	            }
 	        },{
                xtype:'button',
                text: i18n('i18n.developPlan.reset'),//重置
                handler:function(){
                    me.up().down('form').getForm().reset();
                }
            }]
 		}];
 	}
 });
 
 /**
  * .
  * <p>
  * 商机查询GIRD<br/>
  * </p>
  * 
  * @returns CRM.BO.BusinessOpportunitySearch.Grid 商机查询GIRD 的EXT对象
  * @author 张斌
  * @时间 2014-03-9
  */
 Ext.define('CRM.BO.BusinessOpportunitySearch.Grid',{
 	extend:'SearchGridPanel',
 	region : 'south',
 	height:'100%',
 	model:'SINGLE',
 	listeners: {
     	scrollershow: function(scroller) {
     		if (scroller && scroller.scrollEl) {
     				scroller.clearManagedListeners(); 
     				scroller.mon(scroller.scrollEl, 'scroll', scroller.onElScroll, scroller); 
     		}
     	}
     },
    sortableColumns:true,//不能排序
    enableColumnHide:false,//不能隐藏
    enableColumnMove:false,//不能移动
 	getSelModel:function(){
 		var me = this;
 		return Ext.create('Ext.selection.CheckboxModel',{
 				mode:me.model
 			});
 	},
     getBBar:function(){
     	var me = this;
     	return Ext.create('Ext.toolbar.Paging', {
 			store : me.store,
 			displayMsg : i18n('i18n.PotentialCustManagerView.SearchResultGrid.displayMsg'),
 			displayInfo : true
 		});
     },
  // 创建gird的columns
	 columns : [{
	        xtype: 'rownumberer',
	        width: 35,
	        text     : i18n('i18n.Cycle.rownum')//序号
	    },{
	        text     : i18n('i18n.businessOpportunity.boName'),//商机名称
	        dataIndex: 'boName'
	    },{
	        text     : i18n('i18n.businessOpportunity.boNumber'),//商机编码
	        dataIndex: 'boNumber'
	    },{
	        text     : i18n('i18n.businessOpportunity.customerName'),//客户名称
	        dataIndex: 'customer',
	        renderer : function(value){
                if(!Ext.isEmpty(value)){
                	return value.custName;
                }else{
                	return '';
                }
            }
	    },{
	        text     : i18n('i18n.businessOpportunity.customerNum'),//客户编码
	        dataIndex: 'customer',
	        renderer : function(value){
                if(!Ext.isEmpty(value)){
                	return value.custNumber;
                }else{
                	return '';
                }
            }
	    },{
	        text     : i18n('i18n.businessOpportunity.contactName'),//联系人名称
	        dataIndex: 'customer',
	        renderer : function(value){
                if(!Ext.isEmpty(value)){
                	return value.mainLinkmanName;
                }else{
                	return '';
                }
            }
	    },{
	        text     : i18n('i18n.businessOpportunity.contactMobile'),//联系人手机
	        dataIndex: 'customer',
	        renderer : function(value){
                if(!Ext.isEmpty(value)){
                	return value.mainLinkmanMobile;
                }else{
                	return '';
                }
            }
	    },{
	        text     : i18n('i18n.businessOpportunity.contactPhone'),//联系人电话
	        dataIndex: 'customer',
	        renderer : function(value){
                if(!Ext.isEmpty(value)){
                	return value.mainLinkmanPhone;
                }else{
                	return '';
                }
            }
	    },{
	        text     : i18n('i18n.businessOpportunity.boStatus'),//商机状态
	        dataIndex: 'boStatus',
	        renderer : function(value){
	        	return DpUtil.rendererDictionary(value,DataDictionary.BUSINESS_OPPORTUNITY_STATUS); 
            }
	    },{
	        text     : i18n('i18n.businessOpportunity.boStep'),//商机阶段
	        dataIndex: 'boStep',
	        renderer : function(value){
	        	return DpUtil.rendererDictionary(value,DataDictionary.BUSINESS_OPPORTUNITY_STEP); 
            }
	    },{
	        text     : i18n('i18n.businessOpportunity.createTime'),//商机创建时间
	        dataIndex: 'createTime',
	        renderer : function(value){
            	var date = new Date(value);
            	return date.format('yyyy-MM-dd');
            }
	    },{
            text : i18n('i18n.businessOpportView.deptName'),//商机创建部门
            dataIndex : 'deptName'
        },{
	        text     : i18n('i18n.businessOpportunity.operator'),//商机执行人
	        dataIndex: 'operator',
	        renderer : function(value){
                if(!Ext.isEmpty(value)){
                	return value.empName;
                }else{
                	return '';
                }
            }
	    },{
	        text     : i18n('i18n.businessOpportunity.expectSuccessDate'),//预计成功时间
	        dataIndex: 'expectSuccessDate',
	        renderer : function(value){
            	var date = new Date(value);
            	return date.format('yyyy-MM-dd');
            }
	    }],
     initComponent:function(){
 		var me = this;
 		me.store = businessOpportunityData.getBusinessOpportunityStore();
 		me.selModel = me.getSelModel();
// 		me.bbar = me.getBBar();
        //分页控件修改 auth：李春雨
        this.dockedItems=[{
            xtype:'pagingtoolbar',
            store:me.store,
            dock:'bottom',
            displayInfo : true,
            autoScroll : true,
            items:[{
                text: i18n('i18n.MarketActivityManagerView.page'),//每页
                xtype: 'tbtext'
            },Ext.create('Ext.form.ComboBox', {
               width:          window.screen.availWidth*0.0391,
               value:          '20',
               triggerAction:  'all',
               forceSelection: true,
               editable:       false,
               name:           'comboItem',
               displayField:   'value',
               valueField:     'value',
               queryMode:      'local',
               store : Ext.create('Ext.data.Store',{
                   fields : ['value'],
                   data   : [
                       {'value':'10'},
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
                            var pageSize = me.store.pageSize;
                            var newPageSize = parseInt(_field.value);
                            if(pageSize!=newPageSize){
                                me.store.pageSize = newPageSize;
                                this.up('pagingtoolbar').moveFirst();
                            }
                        }
                    }
               }
           }),{
                text: i18n('i18n.MarketActivityManagerView.item'),//条
                xtype: 'tbtext'
           }]
        }];
 		// 增加store的beforeload方法
 		me.store.on('beforeload',function(boStore,operation,e){
 			var form = me.up().down('form').getForm();
 			var boName = form.findField('boName').getValue();//商机名称
 			var boNumber = form.findField('boNumber').getValue();// 商机编码
 			var customerId = form.findField('customerId').getValue();// 客户Id
 			var customerNum = form.findField('customerNum').getValue();// 客户编号
 			var contactName = form.findField('contactName').getValue();// 联系人姓名
 			var contactPhone = form.findField('contactPhone').getValue();// 联系人电话
 			var contactMobile = form.findField('contactMobile').getValue();// 联系人手机
 			var boStatus = form.findField('boStatus').getValue();// 商机状态
 			var boStep = form.findField('boStep').getValue();// 商机阶段
 			var beginTime = form.findField('beginTime').getValue();// 开始时间
 			var endTime = form.findField('endTime').getValue();// 结束时间
 			var searchParams = {'businessOpportunityCondition.boName':boName,'businessOpportunityCondition.boNumber':boNumber
 					,'businessOpportunityCondition.customerName':customerId,'businessOpportunityCondition.customerNum':customerNum
 					,'businessOpportunityCondition.contactName':contactName,'businessOpportunityCondition.contactName':contactName
 					,'businessOpportunityCondition.contactPhone':contactPhone,'businessOpportunityCondition.contactMobile':contactMobile
 					,'businessOpportunityCondition.boStatus':boStatus,'businessOpportunityCondition.boStep':boStep
 					,'businessOpportunityCondition.beginTime':beginTime,'businessOpportunityCondition.endTime':endTime};
   			Ext.apply(operation,{
 				params : searchParams
 			});
   		});
 		this.callParent();
 	}
 });
 
 
 /**
  * .
  * <p>
  * 商机查询界面</br>
  * </p>
  * @author 张斌
  * @时间 2014-03-7
  */
Ext.define('CRM.BO.BusinessOpportunityViewport',{
	extend:'Ext.container.Viewport',
	autoDestroy : true,
	layout:'border',
	//去掉自动的滚动条， 防止因点击修改条数而窜位 auth:李春雨
//  autoScroll:true,
 	/**
	 * .
	 * <p>
	 * 获取商机界面 </br>
	 * </p>
	 * @author 张斌
	 * @时间 2014-03-08
	 */
	getItems:function(){
		var me = this;
		return [new CRM.BO.BusinessOpportunitySearch.SearchForm(),new CRM.BO.BusinessOpportunitySearch.Button(),new CRM.BO.BusinessOpportunitySearch.Grid()];
	},
	initComponent:function(){
		var me = this;
		me.items = me.getItems();
        this.callParent();
	}
});

//设置form下的所有文本组件去空格
function eachTextFieldTrim(form){
    var i = 0;
    var xtype = null;
    for(i=0;i<form.getFields().length;i++){
        xtype = form.getFields().items[i].xtype;
        if(xtype != 'textfield' && xtype != 'textareafield' && xtype != 'textarea')
            continue;
        form.getFields().items[i].setValue(Ext.String.trim(form.getFields().items[i].getValue()))
    }
}