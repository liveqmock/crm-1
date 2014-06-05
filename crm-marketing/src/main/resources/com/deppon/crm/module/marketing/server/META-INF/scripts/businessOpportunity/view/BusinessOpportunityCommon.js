/**.
 * <p>
 * 商机信息共用界面元素（上级查看和上级修改中的营销记录信息，联系人信息）<br/>
 * <p>
 */

/**.
 * <p>
 * 联系人信息panel</br>
 * </p>
 * @returns CRM.BO.BusinessOpportunityCommon.LinkManPanel 联系人信息panel
 * @author  张斌
 * @时间    2014-03-19
 */
Ext.define('CRM.BO.BusinessOpportunityCommon.LinkManPanel', {
	extend:'Ext.panel.Panel',
	autoScroll : true,
	border:false,
    layout:{
        type:'vbox',
        align:'stretch'
    },
	title:i18n('i18n.businessOpportunity.linkManInfo'),//联系人信息
	linkManGrid:null,//联系人信息表格
    /**.
    * <p>
    * 获取ITEMS属性
    * </br>
    * </p>
    * @author  张斌
    * @时间    2014-03-13
    */
    getItems:function(){
    	var me = this;
    	return  [me.linkManGrid];
    },
    initComponent:function(){
    	var me = this;
    	me.linkManGrid = Ext.create('CRM.BO.BusinessOpportunityCommon.LinkManGrid');
    	me.items = me.getItems();
    	this.callParent();
    }
});

/**
 * .
 * <p>
 * 联系人查询GIRD<br/>
 * </p>
 * 
 * @returns CRM.BO.BusinessOpportunityCommon.LinkManGrid 联系人信息查询GIRD 的EXT对象
 * @author 张斌
 * @时间 2014-03-19
 */
Ext.define('CRM.BO.BusinessOpportunityCommon.LinkManGrid',{
	extend:'SearchGridPanel',
	height:380,
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
 // 创建gird的columns
	 columns : [{
	        xtype: 'rownumberer',
	        width: 45,
	        text     : i18n('i18n.Cycle.rownum')//序号
	    },{
	        text     : i18n('i18n.developSchedule.linkManCode'),//联系人编码
	        dataIndex: 'number'
	    },{
	        text     : i18n('i18n.PotentialCustManagerView.contactName'),//联系人姓名
	        dataIndex: 'name'
	    },{
	    	text     : i18n('i18n.businessOpportunity.cardType'),//证件类型
	    	dataIndex: 'cardTypeCon',
	    	renderer:function(value){
	    		return DButil.rendererDictionary(value,DataDictionary.CARDTYPECON);//联系人证件类型
	    	}
	    },{
	        text     : i18n('i18n.ScatterCustManagerView.idNumber'),//身份证号
	        dataIndex: 'idCard'
	    },{
	        text     : i18n('i18n.MemberCustEditView.contactType'),//联系人类型
	        dataIndex: 'linkmanType',
	    	//显示联系人类型
	    	renderer:function(linkmanTypeString){
	    		var linkmanTypeValue = '';
	    		var linkmanTypeArray = linkmanTypeString.split(',');
	    		if(linkmanTypeArray != null){
	    			for(var i = 0; i < linkmanTypeArray.length; i++){
	    				if(linkmanTypeArray[i] == 'true' || linkmanTypeArray[i] == '1'){
	    					if(i != 0 && !DpUtil.isEmpty(linkmanTypeValue)){
	    						linkmanTypeValue += ',';
	    					}
	    					linkmanTypeValue +=  DButil.rendererDictionary(i,DataDictionary.LINKMANTYPE);
	    				}
	    			}
	    		}
	    		return linkmanTypeValue;
	    	}
	    },{
	        text     : i18n('i18n.MemberCustEditView.isMainContact'),//是否主联系人
	        dataIndex: 'isMainLinkMan',
	        renderer:function(value){
	        	if(value){
	        		return i18n('i18n.ChangeContactAffiliatedRelationView.yes');
	        	}else{
	        		return i18n('i18n.ChangeContactAffiliatedRelationView.no');
	        	}
	        }
	    },{
	        text     : i18n('i18n.MemberCustEditView.gender'),//性别
	        dataIndex: 'sex',
	        width:35,
	        renderer:function(value){
	        	return DButil.rendererDictionary(value,DataDictionary.GENDER);
	        }
	    },{
	        text     : i18n('i18n.MemberCustEditView.telephoneNo'),//固定电话
	        dataIndex: 'telPhone'
	    },{
	        text     : i18n('i18n.MemberCustEditView.mobileNo'),//手机号码
	        dataIndex: 'mobilePhone'
	    },{
	        text     : i18n('i18n.MemberCustEditView.logisticsDecision'),//物流决定权
	        dataIndex: 'decisionRight',
	        renderer:function(value){
	        	return DButil.rendererDictionary(value,DataDictionary.LOGIST_DECI);
	        	
	        }
	    },{
	        text     : i18n('i18n.MemberCustEditView.post'),//职务
	        dataIndex: 'duty'
	    },{
	        text     : i18n('i18n.MemberCustEditView.officeholdingDept'),//任职部门
	        dataIndex: 'dutyDept'
	    },{
	        text     : i18n('i18n.MemberCustEditView.birthDate'),//出生日期
	        dataIndex: 'bornDate',
	        renderer : function(value){
                if(Ext.isEmpty(value)){
                    return '';
                }
	        	var date = new Date(value);
	        	return date.format('yyyy-MM-dd');
	        }
	    },{
	        text     : i18n('i18n.MemberCustEditView.companyWay'),//获知公司途径
	        dataIndex: 'gainave'
	    }],
    initComponent:function(){
		var me = this;
		me.store = Ext.create('Ext.data.Store', {
		    model: 'CRM.BO.ContactModel'
		});
		this.callParent();
	}
});


/**
 * .
 * <p>
 * 客户需求结果表格GIRD<br/>
 * </p>
 * 
 * @returns CRM.BO.BusinessOpportunityCommon.CustomerDemandResultGrid客户需求信息查询GIRD 的EXT对象
 * @author 张斌
 * @时间 2014-03-24
 */
Ext.define('CRM.BO.BusinessOpportunityCommon.CustomerDemandResultGrid',{
    extend:'PopupGridPanel',
    region:'north',
    height:190,
    autoScroll:true,
    initComponent:function(){ 
        var me = this;
        var store =  Ext.create('SearchCustomerDemandGridStore');
        store.on('beforeload',function(store,operation,e){
            var searchParams = {
                    'customerCallVO.id':me.up('window').businessOpportunity.customer.custId,
                    'customerCallVO.contactType':'RC_CUSTOMER'
            };
            
            Ext.apply(operation,{
                params : searchParams
            });
        }); 
        
        me.store = store;
        me.columns = [
            {//序号
                xtype:'rownumberer',
                header:i18n('i18n.Cycle.rownum'),
                width:50
            },{//联系人姓名
                header:i18n('i18n.developSchedule.linkManName'),
                width:80,
                dataIndex:'custLinkManName'
            },{ //需求分类
                header :i18n('i18n.returnVisit.opinionType'),
                dataIndex:'opinionType',
                renderer:function(value){
                    return rendererDictionary(value,DataDictionary.CUSTOMER_IDEA);
                },
                width:80
            },{//需求及问题
                header :i18n('i18n.returnVisit.problem'),
                dataIndex:'problem',
                width:80
            },{//解决办法
                header :i18n('i18n.marketRecord.demandResolve'),
                dataIndex:'solution',
                flex:1
            },{//计划主题
                header :i18n('i18n.returnVisit.theme'),
                dataIndex:'topic',
                width:100
            },{//营销方式
                header :i18n('i18n.marketRecord.marketWay'),
                dataIndex:'marketingMethod',
                renderer:function(value){
                    return rendererDictionary(value,DataDictionary.DEVELOP_WAY);
                },
                width:120
           },{//营销人
                header :i18n('i18n.marketRecord.marketName'),
                dataIndex:'marketingPerson',
                width:80
            },{//营销时间
                header :i18n('i18n.marketRecord.marketTime'),
                dataIndex:'marketingDate',
                renderer : DButil.renderDate,
                width:90
            }
        ];
        me.dockedItems=[{
            xtype:'pagingtoolbar',
            cls:'pagingtoolbar',
            store:store,
            dock:'bottom',
            displayInfo : true,
            autoScroll : true,
            items:[{
                text: i18n('i18n.authorization.roleGrid.page_count'),
                xtype: 'tbtext'
            },Ext.create('Ext.form.ComboBox', {
               width:          window.screen.availWidth*0.0391,
               triggerAction:  'all',
               forceSelection: true,
               value:'20',
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
                            var pageSize = store.pageSize;
                            var newPageSize = parseInt(_field.value);
                            if(pageSize!=newPageSize){
                                store.pageSize = newPageSize;
                                this.up('pagingtoolbar').moveFirst();
                            }
                        }
                    }
               }
           }),{
                text: i18n('i18n.authorization.roleGrid.number'),
                xtype: 'tbtext'
           }]
	    }];
	    this.callParent();
   }
});



/**
 * .
 * <p>
 * 走货潜力结果表格GIRD<br/>
 * </p>
 * 
 * @returns CRM.BO.BusinessOpportunityCommon.SendGoodsPontentialResultGrid走货潜力结果信息查询GIRD 的EXT对象
 * @author 张斌
 * @时间 2014-03-24
 */
Ext.define('CRM.BO.BusinessOpportunityCommon.SendGoodsPontentialResultGrid',{
    extend:'PopupGridPanel', 
    region:'center',
    autoScroll:true,
    initComponent:function(){             
        var me = this;
        var store =  Ext.create('SearchSendGoodsPontentialStore',{'id':'sendGoodsPontentialStoreId'});
        //在store加载之前传递参数进行数据筛选
        store.on('beforeload',function(store,operation,e){
            var searchParams = {
                'customerCallVO.id':me.up('window').businessOpportunity.customer.custId,
                'customerCallVO.contactType':'RC_CUSTOMER'
            };
            Ext.apply(operation,{
                params : searchParams
            });
        }); 
        
        me.store = store;
        me.columns = [
          {//序号
            xtype:'rownumberer',
            header:i18n('i18n.Cycle.rownum'),
            width:50,
            align:'center'
          },{    //联系人姓名
            header :i18n('i18n.developSchedule.linkManName'),
            dataIndex:'custlinkManName',
            width:80
          },{   //始发城市 
            header :i18n('i18n.returnVisit.comeFromCity'),
            dataIndex:'comeFromCity',
            width:80
          },{    //到达城市
            header :i18n('i18n.returnVisit.comeToCity'),
            dataIndex:'comeToCity',
            width:80
          },{//货量潜力
            header :i18n('i18n.returnVisit.volumePotential'),
            width:120,
            dataIndex:'volumePotential',
            renderer:function(value){
                return rendererDictionary(value,DataDictionary.CARGO_POTENTIAL);
            }
            
          },{//合作物流公司
            header :i18n('i18n.returnVisit.companyId'),
            dataIndex:'companyId',
            width:100,
            renderer:function(value){
                return rendererDictionary(value,DataDictionary.COOPERATION_LOGISTICS);
            }
          },{//合作意向
			header : i18n('i18n.developSchedule.cooperatePurpose'),
			dataIndex : 'cooperate',
			align : 'center',
			width : 90,
			sortable : true,
			renderer: function(value){
				return DpUtil.rendererDictionary(value,DataDictionary.COOPERATION_INTENTION); 
			}
		},{//适合我司承运
			header : i18n('i18n.businessOpportunity.accord'),
			dataIndex : 'accord',
			align : 'center',
			width : 90,
			sortable : true,
			renderer: function(value){
				if(value=='1'){
					return i18n('i18n.ChangeContactAffiliatedRelationView.yes');
				}else if(value=='0'){
					return i18n('i18n.ChangeContactAffiliatedRelationView.no');
				}else{
					return value;
				}
			}
		},{//是否已有线路
            header :i18n('i18n.marketRecord.isExistRoad'),
            dataIndex:'alreadyHaveLine',
            renderer:function(val){
                //如果返回值为YES，则设置显示值为是
                if(val=='YES'){
                    return i18n('i18n.ChangeContactAffiliatedRelationView.yes');
                }else if(val == 'NO'){//如果返回值为NO，则设置显示值为是否
                    return i18n('i18n.ChangeContactAffiliatedRelationView.no');
                }else{
                    return '';
                }
            },
            width:100
          },{//备注
            header :i18n('i18n.returnVisit.remark'),
            dataIndex:'remark',
            width:150
          },{    //营销方式
            header :i18n('i18n.marketRecord.marketWay'),
            dataIndex:'marketingMethod',
            renderer:function(value){
                return rendererDictionary(value,DataDictionary.DEVELOP_WAY);
            },
            width:120
          },{//营销人
            header :i18n('i18n.marketRecord.marketName'),
            dataIndex:'marketingPerson',
            width:80
          },{//营销时间
            header :i18n('i18n.marketRecord.marketTime'),
            dataIndex:'marketingDate',
            renderer : DButil.renderDate,
            width:100
          }
        ];
        me.dockedItems=[{
            xtype:'pagingtoolbar',
            cls:'pagingtoolbar',
            store:store,
            dock:'bottom',
            displayInfo : true,
            autoScroll : true,
            items:[{
                text: i18n('i18n.authorization.roleGrid.page_count'),
                xtype: 'tbtext'
            },Ext.create('Ext.form.ComboBox', {
               width:          window.screen.availWidth*0.0391,
               triggerAction:  'all',
               forceSelection: true,
               value:'20',
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
                            var pageSize = store.pageSize;
                            var newPageSize = parseInt(_field.value);
                            if(pageSize!=newPageSize){
                                store.pageSize = newPageSize;
                                this.up('pagingtoolbar').moveFirst();
                            }
                        }
                    }
               }
           }),{
                text: i18n('i18n.authorization.roleGrid.number'),
                xtype: 'tbtext'
           }]
        }];
        this.callParent();
    }
});
 

/**
 * .
 * <p>
 * 回访历史记录<br/>
 * </p>
 * 
 * @returns CRM.BO.BusinessOpportunityCommon.MarketHistoryResultPanelOfReturnVisit回访历史记录 的EXT对象
 * @author 张斌
 * @时间 2014-03-24
 */
Ext.define('CRM.BO.BusinessOpportunityCommon.MarketHistoryResultPanelOfReturnVisit', {
    extend : 'BasicPanel',
    layout : 'border',
    border:false,
    autoScroll:true,
    title:i18n('i18n.Cycle.returnVisitLog'),
    customerDemandResultGrid:null,//客户需求grid
    sendGoodsPontentialResultGrid:null,//走货潜力grid
    initComponent:function(){             
        var me = this;
        me.customerDemandResultGrid = Ext.create('CRM.BO.BusinessOpportunityCommon.CustomerDemandResultGrid');
        me.sendGoodsPontentialResultGrid = Ext.create('CRM.BO.BusinessOpportunityCommon.SendGoodsPontentialResultGrid');
        me.items = [me.customerDemandResultGrid,me.sendGoodsPontentialResultGrid]
        this.callParent();
    }
});

/**
 * .
 * <p>
 * 问卷结果表格GIRD<br/>
 * </p>
 * 
 * @returns CRM.BO.BusinessOpportunityCommon.QuestionnaireResultGridForMarketRecord问卷信息查询GIRD 的EXT对象
 * @author 张斌
 * @时间 2014-03-24
 */
Ext.define('CRM.BO.BusinessOpportunityCommon.QuestionnaireResultGridForMarketRecord',{
    extend:'PopupGridPanel', 
    region:'center',
    autoScroll:true,
    defaults:{
        align:'center'
    },
    initComponent:function(){             
        var me = this;
        var store =  Ext.create('QuestionnaireResultStore');
        //在store加载之前传递参数进行数据筛选
        store.on('beforeload',function(store,operation,e){
            var searchParams = {
            		'custId':me.up('window').businessOpportunity.customer.custId
            };
            Ext.apply(operation,{
                params : searchParams
            });
        }); 
        
        me.store = store;
        me.columns = [
          {//序号
            xtype:'rownumberer',
            header:i18n('i18n.Cycle.rownum'),
            width:50,
            align:'center'
          },{    //联系人姓名
            header :i18n('i18n.developSchedule.linkManName'),
            dataIndex:'custlinkManName',
            width:80
          },{   //问卷编码 
            header :i18n('i18n.businessOpportunity.questionNumber'),
            dataIndex:'questionnaireCode',
            width:100
          },{    //问卷名称
            header :i18n('i18n.businessOpportunity.questionName'),
            dataIndex:'questionnaireName',
            width:120
          },{//问卷描述
            header :i18n('i18n.businessOpportunity.questionDesc'),
            width:150,
            dataIndex:'questionnaireDesc'
            
          },{//计划主题
            header :i18n('i18n.returnVisit.theme'),
            dataIndex:'topic',
            width:120
          },{    //营销方式
            header :i18n('i18n.marketRecord.marketWay'),
            dataIndex:'marketingMethod',
            renderer:function(value){
                return rendererDictionary(value,DataDictionary.DEVELOP_WAY);
            },
            width:120
          },{//营销人
            header :i18n('i18n.marketRecord.marketName'),
            dataIndex:'marketingPerson',
            width:80
          },{//营销时间
            header :i18n('i18n.marketRecord.marketTime'),
            dataIndex:'marketingDate',
            renderer : DButil.renderDate,
            width:100
          }
        ];
        me.dockedItems=[{
            xtype:'pagingtoolbar',
            cls:'pagingtoolbar',
            store:store,
            dock:'bottom',
            displayInfo : true,
            autoScroll : true,
            items:[{
                text: i18n('i18n.authorization.roleGrid.page_count'),
                xtype: 'tbtext'
            },Ext.create('Ext.form.ComboBox', {
               width:          window.screen.availWidth*0.0391,
               triggerAction:  'all',
               forceSelection: true,
               value:'20',
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
                            var pageSize = store.pageSize;
                            var newPageSize = parseInt(_field.value);
                            if(pageSize!=newPageSize){
                                store.pageSize = newPageSize;
                                this.up('pagingtoolbar').moveFirst();
                            }
                        }
                    }
               }
           }),{
                text: i18n('i18n.authorization.roleGrid.number'),
                xtype: 'tbtext'
           }]
        }];
        this.callParent();
    }
});
/**
 * .
 * <p>
 * 回访and问卷历史记录<br/>
 * </p>
 * 
 * @returns CRM.BO.BusinessOpportunityCommon.MarketHistoryResultPanel回访na问卷历史记录 的EXT对象
 * @author 张斌
 * @时间 2014-03-24
 */
Ext.define('CRM.BO.BusinessOpportunityCommon.MarketHistoryResultPanel', {
    extend : 'NormalTabPanel',
    width:790,
	height:415,
    border:false,
    autoScroll:true,
    title:i18n('i18n.businessOpportunity.marktingRecordInfo'),//营销记录信息
    marketHistoryResultPanelOfReturnVisit:null,//回访panel
    questionnaireResultGridForMarketRecord:null,//问卷grid
    initComponent:function(){             
        var me = this;
        me.marketHistoryResultPanelOfReturnVisit = Ext.create('CRM.BO.BusinessOpportunityCommon.MarketHistoryResultPanelOfReturnVisit');
        me.questionnaireResultGridForMarketRecord = Ext.create('CRM.BO.BusinessOpportunityCommon.QuestionnaireResultGridForMarketRecord');
        me.items = [me.marketHistoryResultPanelOfReturnVisit,{//问卷结果也签
    		xtype:'panel',
    		title:i18n('i18n.businessOpportunity.questionResult'),
    		layout:'fit',
    		items:[me.questionnaireResultGridForMarketRecord]
    	}]
        this.callParent();
    }
});