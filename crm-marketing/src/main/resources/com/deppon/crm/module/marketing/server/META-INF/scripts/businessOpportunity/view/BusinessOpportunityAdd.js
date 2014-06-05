/**.
 * <p>
 * 商机新增相关界面元素<br/>
 * <p>
 */

 /**
  * .
  * <p>
  * 商机新建window<br/>
  * </p>
  * 
  * @returns CRM.BO.BusinessOpportunityAdd.Window 商机新建window 的EXT对象
  * @author 张斌
  * @时间 2014-03-10
  */
Ext.define('CRM.BO.BusinessOpportunityAdd.Window',{
	extend:'PopWindow',
	width:790,
	height:350,
	title:i18n('i18n.businessOpportunity.addBo'),
	parent:null,
	closeAction:'hide',
	businessOpportunityAddForm:null,//新增商家的FORM
	layout:{
        type:'vbox',
        align:'stretch'
    },
    listeners:{
    	beforehide:function(win){
    		win.resetBo();
    	}
    },
	items:null,
	initComponent:function(){
		var me = this;
		me.items = me.getItems();
		me.fbar = me.getFbar();
		this.callParent();
	},
	getFbar:function(){
		var me = this;
		return [{
			xtype:'button',
			text:i18n('i18n.SearchMember.determine'),//确定
			scope:me,
			handler:me.commitBo
		},{
			xtype:'button',
			text:i18n('i18n.MemberCustEditView.reset'),//重置
			scope:me,
			handler:me.resetBo
		},{
			xtype:'button',
			text:i18n('i18n.memberView.cancel'),//取消
			handler:function(){
				me.hide();
			}
		}];
	},
	getItems:function(){
		var me = this;
		me.businessOpportunityAddForm = Ext.create('CRM.BO.BusinessOpportunityAdd.Form');
		return [me.businessOpportunityAddForm];
	},
	//重置商机信息
	resetBo:function(){
		var me = this;
		me.businessOpportunityAddForm.getForm().reset();
	},
	//提交商机信息
	commitBo:function(btn){
		var me = this;
        eachTextFieldTrim(me.businessOpportunityAddForm.getForm());
		if(me.businessOpportunityAddForm.getForm().isValid()){
			var businessOpportunity = new CRM.BO.BusinessOpportunity();//创建Model
			me.businessOpportunityAddForm.getForm().updateRecord(businessOpportunity);//获取数据
			var custId = me.businessOpportunityAddForm.getForm().findField('customerId').getValue();//获取客户ID
			var empId = me.businessOpportunityAddForm.getForm().findField('operatorId').getValue();//获取商机执行人empId
			businessOpportunity.set('customer',{'custId':custId});//设置值
			businessOpportunity.set('operator',{'id':empId});
			if(businessOpportunity.get('expectSuccessDate').getTime()<new Date().getTime()){
				MessageUtil.showMessage(i18n('i18n.businessOpportunity.expectSuccessDatenotSmallToday'));
				return;
			}
			var param = {'businessOpportunity':businessOpportunity.data};
			var successFn = function(json){
				btn.enable();
				MessageUtil.showInfoMes(i18n('i18n.businessOpportunity.addBoSuccess'));
				me.hide();
				var form = me.parent.up().down('form');
	    			if(form.getForm().isValid()){
	   		    		var beginTime = form.getForm().findField('beginTime').getValue();
	   		        	var endTime =  form.getForm().findField('endTime').getValue();
	   		        	if(((endTime.getTime()-beginTime.getTime())/(24*60*60*1000))>365){
	   		        	    MessageUtil.showMessage(i18n('i18n.businessOpportunity.not365'));//抱歉，查询时间不能大于365天
	   		        		return;
	   		        	}else if(((endTime.getTime()-beginTime.getTime())/(24*60*60*1000))<0){
	   		        	    MessageUtil.showMessage(i18n('i18n.businessOpportunity.notbeginmoreend'));//抱歉，起始时间不能大于终止时间
	   		        		return;
	   		        	}else{
	   		        		me.parent.up().down('grid').getStore().loadPage(1);
	   		        	}
	   		    	}
			};
			var failureFn = function(json){
				btn.enable();
			    MessageUtil.showErrorMes(json.message);
			};
			btn.disable();
            //修改请求方式，增加延迟时间
            Ext.Ajax.request({
		        url:'../marketing/createBusinessOpportunity.action',
                timeout: 180000,
		        jsonData:param,
		        success:function(response){
		            var result = Ext.decode(response.responseText);
		            if(result.success){
		                successFn(result);
		            }else{
		                failureFn(result);
		            }
		        },
		        failure:function(response){
		            var result = Ext.decode(response.responseText);
		            failureFn(result);
		        }
		    });
//			businessOpportunityData.addBo(param,successFn,failureFn);
		}
	}
});

/**
 * .
 * <p>
 * 商机新增FORM<br/>
 * </p>
 * @returns CRM.BO.BusinessOpportunitySearch.SearchForm 商机查询FORM 的EXT对象
 * @author 张斌
 * @时间 2014-03-7
 */
Ext.define('CRM.BO.BusinessOpportunityAdd.Form', {
		extend:'TitleFormPanel',
		height:300,
		layout:{
			type:'table',
			columns:3
		},
		defaultType:'textfield',
		defaults:{
			enableKeyEvents:true,
			labelSeparator:'',
			labelWidth:100,
			width:230
		},
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
			return [{
		                xtype: 'textfield',
	                    name: 'boName',
	                    allowBlank:false,
	                    maxLength:50,
	                    blankText:i18n('i18n.businessOpportunity.pleaseInputBoName'),//请输入
	                    fieldLabel: i18n('i18n.businessOpportunity.boName')//商机名称
	                },{
	 	       			xtype : "bomembersearchcombox",
	 	                maxLength:170,
	 	                allowBlank:false,
	 	       			queryMode : "local",
	 	       		    blankText:i18n('i18n.businessOpportunity.pleaseInputCustomerName'),//请输入
	 	       			editable:false,
	 	       			setValueComeBack : function(memberRecord, addressRecord) {
	 	       			    this.setValue(memberRecord.get('custId'));
 	       			        this.setRawValue(memberRecord.get('custName'));
	 	       				me.getForm().findField('customerNum').setValue(memberRecord.get('custNum'));
	 	       			    me.getForm().findField('custSecondType').setValue(
 	       						DButil.rendererDictionary(memberRecord.get('secondTrade'),DataDictionary.SECOND_TRADE));//二级行业
 	       				    me.getForm().findField('custFirstType').setValue(
 	       						DButil.rendererDictionary(memberRecord.get('trade'),DataDictionary.TRADE));//一级行业
	 	       				for(var i=0;i<addressRecord.length;i++){//获取改客户下的主联系人信息
	 	       					if(addressRecord[i].get('custId')==memberRecord.get('custId')//判断是相同客户
	 	       							&&addressRecord[i].get('isMainLinkMan')==true){//判断是主联系人
	 	       						//设置联系人信息
	 	       					    me.getForm().findField('contactName').setValue(addressRecord[i].get('contactName'));//联系人名称
	 	       					    me.getForm().findField('contactMobile').setValue(addressRecord[i].get('mobileNum'));//联系人手机
	 	       					    me.getForm().findField('contactPhone').setValue(addressRecord[i].get('telNum'));//联系人电话
	 	       					}
	 	       				}
	 	       			},
	 	       			displayField : "custName",
	 	       			valueField : "custId",
	 	       			name:'customerId',
	 	       			maxLength : 170,
	                    fieldLabel: i18n('i18n.businessOpportunity.customerName')//客户名称
		             },{
		            	xtype: 'textfield',
	                    name: 'customerNum',
	                    readOnly:true,
	                    fieldLabel: i18n('i18n.businessOpportunity.customerNum')//客户编号
		             },{
		            	xtype: 'textfield',
	                    name: 'contactName',
	                    readOnly:true,
	                    fieldLabel: i18n('i18n.businessOpportunity.contactName')//联系人名称
		             },{
		            	xtype: 'textfield',
	                    name: 'contactMobile',
	                    readOnly:true,
	                    fieldLabel: i18n('i18n.businessOpportunity.contactMobile')//联系人手机
		             },{
		            	xtype: 'textfield',
	                    name: 'contactPhone',
	                    readOnly:true,
	                    fieldLabel: i18n('i18n.businessOpportunity.contactPhone')//联系人电话
		             },{
		            	 xtype:'container',
		            	 width:230,
		            	 layout:{
		         			type:'hbox'
		         		},
		            	 items:[{
		            		    xtype: 'numberfield',
			                    name: 'expectDeliveryAmount',
			                    maxValue:9999999999,
			                    minValue:10000,
			                    step:1000,
			                    decimalPrecision:0,
			                    width:215,
			                    blankText:i18n('i18n.businessOpportunity.inputExpectDeliveryAmount'),//请输入
			                    allowBlank:false,
			                    fieldLabel: i18n('i18n.businessOpportunity.expectDeliveryAmount')//预计发货金额
				             }, {
				                 xtype: 'label',
				                 text: i18n('i18n.visualMarket.yuan'),
				                 width:10,
				                 margins: '0 0 0 2'
				             }]
		             },{
		                 xtype: 'combo',
		                 fieldLabel:i18n('i18n.businessOpportunity.operator'),//商机执行人
		                 name:'operatorId',
		                 blankText:i18n('i18n.businessOpportunity.inputOperator'),//请输入商机执行人
		                 store: Ext.create('Ext.data.Store', {
		            		model : 'CRM.BO.EmpModel',
		            		data:EMP
		            	 }),
		                 queryMode: 'local',
		                 triggerAction : 'all',
		                 displayField:'empName',
		                 valueField:'id',
		                 //剔出朦层
		                 listConfig: {
		                     loadMask:false
		                 },
		                 editable:false,
		                 forceSelection :true,
		                 allowBlank: false
		             },{
		            	 xtype:'container',
		            	 width:230,
		            	 layout:{
		         			type:'hbox'
		         		},
		            	 items:[{
				                xtype: 'numberfield',
			                    name: 'expectSuccessOdds',
			                    maxValue:100,
			                    allowBlank:false,
			                    decimalPrecision:0,
			                    negativeText:i18n('i18n.businessOpportunity.minValueText'),//该输入项的最小值是0
			                    blankText:i18n('i18n.businessOpportunity.inputExpectSuccessOdds'),//请输入预计成功几率
			                    minValue:0,
			                    width:215,
			                    fieldLabel: i18n('i18n.businessOpportunity.expectSuccessOdds')//预计成功几率
				             }, {
				                 xtype: 'label',
				                 text: '%',
				                 width:10,
				                 margins: '0 0 0 2'
				             }]
		             },{
		                xtype     : 'datefield',
	                    name      : 'expectSuccessDate',
	                    value:new Date((new Date).getFullYear(),(new Date).getMonth(),(new Date).getDate()+1),
	                    allowBlank:false,
	                    minValue:new Date((new Date).getFullYear(),(new Date).getMonth(),(new Date).getDate()+1),
	                    maxValue:new Date((new Date).getFullYear(),(new Date).getMonth()+6,(new Date).getDate()),
		                fieldLabel: i18n('i18n.businessOpportunity.expectSuccessDate'),//预计成功时间
		                editable:false,
		                format: 'Y-m-d',
		            },{
		                xtype     : 'textfield',
	                    name      : 'custFirstType',
		                fieldLabel: i18n('i18n.MemberCustEditView.custIndustry'),//客户行业
		                readOnly:true
		            },{
		                xtype: 'textfield',
	                    name: 'custSecondType',
	                    fieldLabel: '-',
	                    readOnly:true,
	                    labelWidth:6,
 						width:144
		             },{
		                xtype     : 'textarea',
	                    name:     'boDesc',
	                    maxLength:200,
	                    blankText:i18n('i18n.businessOpportunity.inputBoDesc'),//请输入商机描述
	                    allowBlank:false,
	                    colspan: 3,
	                    width:700,
	                    height:150,
		                fieldLabel: i18n('i18n.businessOpportunity.boDesc')//商机描述
		            }];
		},
		initComponent:function()
		{
			var me = this;
			me.items = me.getItems();
			this.callParent();
		}
});