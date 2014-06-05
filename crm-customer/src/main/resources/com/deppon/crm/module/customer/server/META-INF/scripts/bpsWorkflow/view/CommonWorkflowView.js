/**
 * 公用工作流View
 */


/**
 * 顶部查询表单
 */
 function defineCommonWSQueryForm(type){
	Ext.define('CommonWSQueryForm',{
		extend:'SearchFormPanel',
		frame:true,
		margin:'3 0 0 0',
		layout:{
			type:'table',
			columns:3
		}
		,defaultType:'textfield',
		defaults:{
			width:210,
			labelWidth : 65,
			labelAlign:'right'	
		}
		,items:[],
		listeners:{
				'afterrender':function(t){
					t.add({
						fieldLabel :i18n('i18n.workflow.name'),
						name : 'type',
						xtype : 'combobox',
						value:'LTT_NEW',
						store :getDataDictionaryByName(DataDictionary,'CONTRACT_WORKFLOW_TYPE'),
						queryMode : 'local',
						forceSelection : true,
						displayField : 'codeDesc',
						valueField : 'code',
						editable:false
					},
					{
						name:'workflowNo',
						maxLength:50,
						fieldLabel:i18n('i18n.workflow.workflowNo'),
						regex: /^ICRM((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})(((0[13578]|1[02])(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)(0[1-9]|[12][0-9]|30))|(02(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))0229))\d{7}$/,
						regexText: i18n('i18n.workflow.workflowNoErrorInfo')
					},{
						fieldLabel : i18n('i18n.workflow.state'),
						name : 'state',
						xtype : 'combobox',
						store :getDataDictionaryByName(DataDictionary,'CLAIM_WORKFLOW_STATE'),
						queryMode : 'local',
						forceSelection : true,
						displayField : 'codeDesc',
						valueField : 'code',
						listeners:{
							beforerender:function(f){
								if(type==2){
									f.getStore().removeAt(2);//未审批列表去掉已同意、未同意
									f.getStore().removeAt(2);
								}else if(type==3){
									f.getStore().removeAt(0)//已审批表去掉未审批
								}
							}
						}
					});		
					if(type==2||type==3){
						t.add(Ext.create('Ext.lookup.EmployeeLookup',{
	                    	name:'userSearch',
	                    	fieldLabel: i18n('i18n.workflow.appler'),
	                    	emptyText:'',
	                    	width:210,
	                     	labelWidth:65,
	                    	editable:false
                     	}),Ext.create('Ext.lookup.DeptLookup',{
		            	   maxLength:80,
		            	   editable:false,
		            	   emptyText:'',
		            	   fieldLabel :i18n('i18n.workflow.department'),
		            	   name : 'deptName',
		            	   labelWidth:65,
		   				   width:200
            	 }));
				}
				t.add({
					xtype : 'fieldcontainer',
					colspan : 2,
					border : 0,
					width : 400,
					layout : 'column',
					defaultType : 'datefield',
					items : [{
						fieldLabel : i18n('i18n.workflow.applyDate'),
						labelWidth : 65,
						width : 210,
						format : 'Y-m-d',
						maxValue:new Date(),
						value:new Date(new Date().getFullYear(),new Date().getMonth(),new Date().getDate()-31),
						name : 'startTime',
						editable:false
					}, {
						xtype : 'displayfield',
						width : 10,
						value : '-'
					}, {
						xtype : 'datefield',
						name : 'endTime',
						width : 145,
						format : 'Y-m-d',
						maxValue : new Date(),
						value:new Date(),
						editable:false
					}]
				});
				}
			}
		});
 }	
/**
 * 底部GridPanel
 */
function defineSearchGridPanel(store,isDetail){	
	Ext.define('CommonSearchGridPanel',{
			extend:'SearchGridPanel',
			store:store,
			selModel:Ext.create('Ext.selection.CheckboxModel',{mode:'single'}),//选择框
			columns:[{
				text:i18n('i18n.workflow.workflowNo'),dataIndex:'busino',width:150,
				renderer:function(v,s,r){
					return	isDetail?"<a href=javascript:showDetailWorkflow('showDetailWorkflow.action?processInstId="+r.get('processinstidEnc')+"&workItemId="+r.get('workItemIdEnc')+"&processDefName="+r.get('processDefName')+"&busino="+r.get('busiNoEnc')+"');>"+v+"</a>":
					"<a  href=javascript:showDetailWorkflow('doApproveWorkflow.action?processInstId="+r.get('processinstidEnc')+"&workItemId="+r.get('workItemIdEnc')+"&processDefName="+r.get('processDefName')+"&busino="+r.get('busiNoEnc')+"');>"+v+"</a>"
				}
			},{
				text:i18n('i18n.workflow.name'),dataIndex:'processCHName',width:100
			},{
				text: i18n('i18n.workflow.state'),dataIndex:'condition',width:100,
				renderer:function(v){
					return rendererDictionary(v,DataDictionary.CLAIM_WORKFLOW_STATE);
				}
			},{
				text:i18n('i18n.workflow.appler'),dataIndex:'appler',width:80
			},{
				text:i18n('i18n.workflow.applyDate'),dataIndex:'appdate',width:140,renderer:DpUtil.renderTime
			},{
				text:i18n('i18n.workflow.apppost'),dataIndex:'apppost',width:100
			},{
				text:i18n('i18n.workflow.department'),dataIndex:'department',width:150
			}],
			viewConfig:{
				forceFit:true
			},
			dockedItems:[{
				xtype:'pagingtoolbar',
				store:store,
				dock:'bottom',
				displayInfo : true,
				autoScroll : true
			}]
		});
}
	

/**
 * 弹出框GridPanel
 */
function definePopupGridPanel(store){	
	Ext.define('CommonPopupGridPanel',{
			extend:'PopupGridPanel',
			title:i18n('i18n.workflow.historyStep'),
//			cls:'workFlowGrid',
//			height:280,
			store:store,
			columns:[{
				text:i18n('i18n.workflow.approveDate'),dataIndex:'approvedate',width:150,renderer:DpUtil.renderTime
			},{
				text:i18n('i18n.workflow.approvePost'),dataIndex:'busino',width:170
			},{
				text:i18n('i18n.workflow.approver'),dataIndex:'approver',width:130
			},{
				text:i18n('i18n.workflow.isagree'),dataIndex:'isagree',width:100,
				renderer:function(v){
					return rendererDictionary(v,DataDictionary.APPROVE_REUSLT);
				}
			},{
				text:i18n('i18n.workflow.approvever'),dataIndex:'approvever',flex:1,
				renderer: function(value,metaData,record,colIndex,store,view) {  
				       if(!Ext.isEmpty(value)){
				    	   metaData.tdAttr = 'data-qtip="'+value+'"';  
					       return value; 
				       } 
				}  
			}],viewConfig:{
				forceFit:true
			}
		});
}

/**
 * 弹出框详情表单
 */
Ext.define('CommonDetailPopForm',{
	extend:'PopTitleFormPanel',
	frame:true,
	cls:'reanOnlyPanel',
	margin:'3 0 3 0',
	items:[{
		xtype:'container',
		defaultType:'displayfield',
		width:900,
		defaults:{
			width:900,
			
			readOnly : true
		},
		layout:{
			type:'table',
			columns:1
		},		
		items:[{
			xtype:'basicfiledset', //	     '基本信息',
	        columnWidth: 0.5,
	        defaultType: 'displayfield',
	        layout:{
		    	type:'table',
		    	columns:2
		    },
		    defaults:{
				width:280,
				lableWidth:80,
				
				readOnly : true
			},
	        
	        items :[ {
				name:'applyPersonCode',//申请人工号
				fieldLabel:i18n('i18n.workflow.contract.applyPersonCode')
			},{
				name:'applyPersonName',// 申请人姓名
				fieldLabel:i18n('i18n.workflow.contract.applyPersonName')
			},{
				name:'divisionCode', // 所属事业部
				fieldLabel:i18n('i18n.workflow.contract.divisionCode')

			},{
				name:'applyPersonDept',// 所属部门
				fieldLabel:i18n('i18n.workflow.contract.applyPersonDept')
			},{
				name:'applyType', //申请类型
				fieldLabel:i18n('i18n.workflow.contract.applyType')
			}]
	    },{
	        xtype:'basicfiledset',
	        columnWidth: 0.5,
//	        title: '合同信息',
	        defaultType: 'displayfield',
	        layout:{
		    	type:'table',
		    	columns:3
		    },
		    defaults:{
				width:280,
				lableWidth:80,
				
				readOnly : true
			},
	        items :[  {
	        	id:'contractNumber',
				name:'contractNumber',// 合同序号
				fieldLabel:i18n('i18n.workflow.contract.contractNumber')
			},{
				name:'subsidiary',// 所属子公司
				fieldLabel:i18n('i18n.workflow.contract.subsidiary')
			},{
				name:'contractStartDate',//合同到期日期
				fieldLabel:i18n('i18n.workflow.contract.contractStartDate')
			},{
				name:'contractEndDate',// 合同到期日期
				fieldLabel:i18n('i18n.workflow.contract.contractEndDate')	
			},{
				name:'customerCode',//客户编码
				fieldLabel:i18n('i18n.workflow.contract.customerCode')
			},{
				name: 'customerName',//客户名称
				fieldLabel: i18n('i18n.workflow.contract.customerName')
			},{	
				name: 'customerAllName',// 客户全称
				fieldLabel: i18n('i18n.workflow.contract.customerAllName')
			},{
				name:'settleAccountsDate',// 结款日期
				fieldLabel:i18n('i18n.workflow.contract.settleAccountsDate')
			},{
				name:'balanceAccount',// 结算限额
				fieldLabel:i18n('i18n.workflow.contract.balanceAccount')
				
			},{
				name:'contactTel',// 联系人电话
				fieldLabel:i18n('i18n.workflow.contract.contactTel')				
			},{
				name:'contactPhone',// 联系人手机
				fieldLabel:i18n('i18n.workflow.contract.contactPhone')				
			},{
				name:'signCompany',//签署合同公司
				fieldLabel:i18n('i18n.workflow.contract.company')				
			},{	
				name: 'protocolContactName',// 协议联系人
				fieldLabel: i18n('i18n.workflow.contract.protocolContactName')
			},{
				name:'invoiceType',//发票标记
				fieldLabel:i18n('i18n.workflow.contract.taxflag')
				
			}]
	    },{
	    	xtype:'basicfiledset',
	        columnWidth: 0.5,
	        title: '零担',
	        defaultType: 'displayfield',
	        layout:{
		    	type:'table',
		    	columns:3
		    },
		    defaults:{
				width:280,
				lableWidth:80,
				
				readOnly : true
			},
	        items :[{
				name:'amountOfConsignment',// 近三月发货金额
				fieldLabel:i18n('i18n.workflow.contract.amountOfConsignment')
				
			},{
				name:'nodeSectionType',// 结款方式
				fieldLabel:i18n('i18n.workflow.contract.nodeSectionType')		
			},{
				name:'preferentialType',// 优惠类型
				fieldLabel:i18n('i18n.workflow.contract.preferentialType')		
			},{
				name:'freightDiscount',// 运费折扣
				fieldLabel:i18n('i18n.workflow.contract.freightDiscount')
			},{
				name:'generationRateDiscount',// 代收费率折扣
				fieldLabel:i18n('i18n.workflow.contract.generationRateDiscount')
			},{
				name:'chargeRateDiscount',// 保价费率折扣
				fieldLabel:i18n('i18n.workflow.contract.chargeRateDiscount')
			},{
				name:'cargoFeeDiscount',// 接货费
				fieldLabel:i18n('i18n.workflow.contract.cargoFeeDiscount')
			},{
				name:'deliveryFeeDiscount',// 送货费折扣
				fieldLabel:i18n('i18n.workflow.contract.deliveryFeeDiscount')
			},{
				id:'priceVersionDateId',
				name:'priceVersionDate',// 价格版本
				format:'Y-m-d H:i:s',
				fieldLabel:i18n('i18n.workflow.contract.priceVersionDate')
			}]
	    },{
	    	xtype:'basicfiledset',
	        columnWidth: 0.5,
	        title: '快递',
	        defaultType: 'displayfield',
	        layout:{
		    	type:'table',
		    	columns:3
		    },
		    defaults:{
				width:280,
				lableWidth:80,
				
				readOnly : true
			},
	        items :[ {
				name:'expAmountOfConsignment',// 近三月发货金额
				fieldLabel:i18n('i18n.workflow.contract.amountOfConsignment')
				
			},{
				name:'expNodeSectionType',// 结款方式
				fieldLabel:i18n('i18n.workflow.contract.nodeSectionType')		
			},{
				name:'expressPreferentialType',// 优惠类型
				fieldLabel:i18n('i18n.workflow.contract.preferentialType')		
			},{
				name:'expFreightDiscount',// 运费折扣
				fieldLabel:i18n('i18n.workflow.contract.freightDiscount')
			},{
				name:'expWxpgenerationRateDiscount',// 即日退代收折扣
				fieldLabel:i18n('i18n.workflow.contract.excollectionOfRatesDiscount')
			},{
				name:'expChargeRateDiscount',// 保价费率折扣
				fieldLabel:i18n('i18n.workflow.contract.chargeRateDiscount')
			},{
				id:'expPriceVersiondateId',
				name:'expPriceVersiondate',// 价格版本
				format:'Y-m-d H:i:s',
				fieldLabel:i18n('i18n.workflow.contract.priceVersionDate')
			} ]
	    },{
	    	xtype:'displayfield',
	    	id:'modifyDisplayField',
	    	value:'合同信息修改如下:',
	        hidden:true	
	    },{ id:'contractInfo',
	    	xtype:'basicfiledset',
	        columnWidth: 0.5,
	        defaultType: 'displayfield',
	        hidden:true,	
	        layout:{
		    	type:'table',
		    	columns:3
		    },
		    defaults:{
				width:280,
				lableWidth:80,
				
				readOnly : true
			},
	        items :[{
				name:'newContractNumber',// 合同序号
				fieldLabel:i18n('i18n.workflow.contract.contractNumber')		
			},{
				name:'newBalanceAccount',// 结算限额
				fieldLabel:i18n('i18n.workflow.contract.balanceAccount')
			},{
				name:'newSignCompany',//签署合同公司
				fieldLabel:i18n('i18n.workflow.contract.company')	
			},{
				name:'newInvoiceType',//发票标记
				fieldLabel:i18n('i18n.workflow.contract.taxflag')
			}			
			]
	    },{ id:'newLTTInfo',
	    	xtype:'basicfiledset',
	        columnWidth: 0.5,
	        title: '零担',
	        defaultType: 'displayfield',
	        hidden:true,	
	        layout:{
		    	type:'table',
		    	columns:3
		    },
		    defaults:{
				width:280,
				lableWidth:80,
				
				readOnly : true
			},
	        items :[{
				name:'newPreferentialType',// 优惠类型
				fieldLabel:i18n('i18n.workflow.contract.preferentialType')		
			},{
				name:'newFreightDiscount',// 运费折扣
				fieldLabel:i18n('i18n.workflow.contract.freightDiscount')
			},{
				name:'newGenerationRateDiscount',// 代收费率折扣
				fieldLabel:i18n('i18n.workflow.contract.generationRateDiscount')
			},{
				name:'newChargeRateDiscount',// 保价费率折扣
				fieldLabel:i18n('i18n.workflow.contract.chargeRateDiscount')
			},{
				name:'newCargoFeeDiscount',// 接货费折扣
				fieldLabel:i18n('i18n.workflow.contract.cargoFeeDiscount')
			},{
				name:'newDeliveryFeeDiscount',// 送货费折扣
				fieldLabel:i18n('i18n.workflow.contract.deliveryFeeDiscount')
			},{
				name:'newPriceVersionDate',// 价格版本
				format:'Y-m-d H:i:s',
				fieldLabel:i18n('i18n.workflow.contract.priceVersionDate')
			}]
	    },{	
	    	 id:'newExpInfo',
	    	xtype:'basicfiledset',
	        columnWidth: 0.5,
	        title: '快递',
	        hidden:true,
	        defaultType: 'displayfield',
	        layout:{
		    	type:'table',
		    	columns:3
		    },
		    defaults:{
				width:280,
				lableWidth:80,
				
				readOnly : true
			},
	        items :[ {
				name:'expNewPreferentialType',// 优惠类型
				fieldLabel:i18n('i18n.workflow.contract.preferentialType')		
			},{
				name:'expNewFreightDiscount',// 运费折扣
				fieldLabel:i18n('i18n.workflow.contract.freightDiscount')
			},{
				name:'expNewDiscount',// 即日退代收折扣
				fieldLabel:i18n('i18n.workflow.contract.excollectionOfRatesDiscount')
			},{
				name:'expNewChargeRateDiscount',// 保价费率折扣
				fieldLabel:i18n('i18n.workflow.contract.chargeRateDiscount')
			},{
				name:'newExpPriceVersionDate',// 价格版本
				format:'Y-m-d H:i:s',
				fieldLabel:i18n('i18n.workflow.contract.priceVersionDate')
			} ]
	    },{	//其他信息
	    	xtype:'basicfiledset',
	        columnWidth: 0.5,
	        defaultType: 'displayfield',
	        height:100,
	        layout:{
		    	type:'table',
		    	columns:3
		    },
		    defaults:{
				width:280,
				lableWidth:80,
				
				readOnly : true
			},
	        items :[{
				name:'applyReason',// 申请事由
				fieldLabel:i18n('i18n.workflow.contract.applyReason'),
				width:820,
				colspan:3
			},{
			name:'filelInfo',// 已上传附件
			fieldLabel:i18n('i18n.workflow.contract.filelsit'),
			width:900
			}
			]
	    }
	      
		]}	
		],
		getRenderFun: function(t){
		 	 var mask=new Ext.LoadMask(Ext.getBody(), {msg:"loading..." });
		 	 mask.show();
			 var param = {busino:getUrlVars('busino'),processDefName:getUrlVars('processDefName')};
				var successFn = function(json){
					var ci=json.contractWorkflowInfo;
					if(!ci){
						MessageUtil.showErrorMes(i18n('i18n.workflow.workflowNotExist'));
						mask.hide();
						return;
					}
					var form=t.getForm();
					if(ci.applyType=='UPDATE'||ci.applyType=='EX_UPDATE'){
						var modifyField=Ext.getCmp('modifyDisplayField');
						var newLTTInfo=Ext.getCmp('newLTTInfo');
						var newExpInfo=Ext.getCmp('newExpInfo');
						var contractInfo=Ext.getCmp('contractInfo');
						Ext.getCmp("contractNumber").setFieldLabel('原合同序号');
						form.findField("contractNumber").setValue(ci.oldContractNumber);
						form.findField("newContractNumber").setValue(ci.contractNumber);
						form.findField("newBalanceAccount").setValue(ci.newBalanceAccount);
						Ext.getCmp("priceVersionDateId").hide();
						Ext.getCmp("expPriceVersiondateId").hide();
						modifyField.show();
						newLTTInfo.show();
						newExpInfo.show();
						contractInfo.show();
						form.findField("applyType").setValue("合同修改");
//						form.findField("newPriceVersionDate").setValue(ci.newPriceVersionDate);
//						form.findField("expPriceVersiondate").setValue(ci.expPriceVersiondate);



					}else if(ci.applyType=='NEW'||ci.applyType=='EX_NEW'){
						form.findField("applyType").setValue("合同申请");
						form.findField("contractNumber").setValue(ci.contractNumber);
					}else if(ci.applyType=='CANCEL'||ci.applyType=='EX_CANCEL'){
						form.findField("applyType").setValue("月结合同终止");
						form.findField("contractNumber").setValue(ci.contractNumber);
						form.findField("filelInfo").hide();
					}
					form.findField("applyPersonCode").setValue(ci.applyPersonCode);
					form.findField("applyPersonName").setValue(ci.applyPersonName);
					form.findField("divisionCode").setValue(ci.divisionCode);
					form.findField("applyPersonDept").setValue(ci.applyPersonDept);
					/**合同信息*/
					form.findField("subsidiary").setValue(ci.subsidiary);
					form.findField("contractStartDate").setValue(DpUtil.renderDate(ci.contractStartDate));
					form.findField("contractEndDate").setValue(DpUtil.renderDate(ci.contractEndDate));
					form.findField("customerCode").setValue(ci.customerCode);
					form.findField("customerName").setValue(ci.customerName);
					form.findField("customerAllName").setValue(ci.customerAllName);
					form.findField("settleAccountsDate").setValue(ci.settleAccountsDate);
					form.findField("balanceAccount").setValue(ci.balanceAccount);
					form.findField("contactTel").setValue(ci.contactTel);
					form.findField("contactPhone").setValue(ci.contactPhone);
					form.findField("protocolContactName").setValue(ci.protocolContactName);
					form.findField("invoiceType").setValue(ci.invoiceType);
					form.findField("signCompany").setValue(ci.signCompany);
					form.findField("invoiceType").setValue(ci.invoiceTypeName);
					/**零担优惠信息*/
					form.findField("amountOfConsignment").setValue(ci.amountOfConsignment);
					form.findField("nodeSectionType").setValue(ci.nodeSectionType);
					form.findField("preferentialType").setValue(ci.preferentialType);
					form.findField("freightDiscount").setValue(ci.freightDiscount);
					form.findField("generationRateDiscount").setValue(ci.generationRateDiscount);
					form.findField("chargeRateDiscount").setValue(ci.chargeRateDiscount);
					form.findField("cargoFeeDiscount").setValue(ci.cargoFeeDiscount);
					form.findField("deliveryFeeDiscount").setValue(ci.deliveryFeeDiscount);
					form.findField("priceVersionDate").setValue(DpUtil.renderTime(ci.priceVersionDate));
					/**快递优惠信息*/
					form.findField("expAmountOfConsignment").setValue(ci.expAmountOfConsignMent);
					form.findField("expNodeSectionType").setValue(ci.expNodesectionType);
					form.findField("expressPreferentialType").setValue(ci.expressPreferentialType);
					form.findField("expFreightDiscount").setValue(ci.expFreightDiscount);
					form.findField("expWxpgenerationRateDiscount").setValue(ci.expWxpgenerationRateDiscount);
					form.findField("expChargeRateDiscount").setValue(ci.expChargeRateDiscount);
					form.findField("expPriceVersiondate").setValue(DpUtil.renderTime(ci.expPriceVersiondate));
					/**修改后的零担条款*/
					form.findField("newSignCompany").setValue(ci.newSignCompany);
					form.findField("newInvoiceType").setValue(ci.newInvoiceTypeName);
					
					form.findField("newPreferentialType").setValue(ci.newPreferentialType);
					form.findField("newFreightDiscount").setValue(ci.newFreightDiscount);
					form.findField("newGenerationRateDiscount").setValue(ci.newGenerationRateDiscount);
					form.findField("newChargeRateDiscount").setValue(ci.newChargeRateDiscount);
					form.findField("newCargoFeeDiscount").setValue(ci.newCargoFeeDiscount);
					form.findField("newDeliveryFeeDiscount").setValue(ci.newDeliveryFeeDiscount);
					form.findField("newPriceVersionDate").setValue(DpUtil.renderTime(ci.priceVersionDate));
					/**修改后的快递条款*/
					form.findField("expNewPreferentialType").setValue(ci.expNewPreferentialType);
					form.findField("expNewFreightDiscount").setValue(ci.expNewFreightDiscount);
					form.findField("expNewDiscount").setValue(ci.expNewDiscount);
					form.findField("expNewChargeRateDiscount").setValue(ci.expNewChargeRateDiscount);
					form.findField("newExpPriceVersionDate").setValue(DpUtil.renderTime(ci.expPriceVersiondate));
					/**其他信息*/
					form.findField("applyReason").setValue(ci.applyReason);
					if(ci.file&&ci.file.length>0){
						 var v='';
						for(var i=0;i<ci.file.length;i++){
							v=v+"<a target='_blank'  href='../common/downLoad.action?fileName="+ci.file[i].fileName+"&inputPath="+ci.file[i].savePath+"'>"+ci.file[i].fileName+"</a>&nbsp;&nbsp;"
						}
						form.findField("filelInfo").setValue(v);
					}else{
						 form.findField("filelInfo").setValue(i18n('i18n.workflow.none')); 
					}
					
					
					//处理空值
					var fv=form.getValues(),i,v;
					for (i in fv) {
			            v = fv[i];
			            if (Ext.isString(v)&&!Ext.String.trim(v)) {
			               form.findField(i).setValue(i18n('i18n.workflow.none')); 
			            }
       				}
					mask.hide();
				};
				var failFn = function(json){
					mask.hide();
					MessageUtil.showErrorMes(i18n('i18n.workflow.getWorkflowDetailFail'));
				};
			workflowData.findContractWorkflowInfoByWorkNo(param,successFn,failFn);
		}

});


/**
 * 工作流弹窗
 */
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

function getUrlVars(v){  
    var params = {}, hash;  
    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');  
    for(var i = 0; i < hashes.length; i++){  
        hash = hashes[i].split('=');  
        params[hash[0]] = hash[1];  
    }  
    return params[v];  
}  
/**
 * 附件下载
 * @param fileName
 * @param filePath
 */
function downLoadFile(fileName,filePath){
	window.location.href="../common/downLoad.action?fileName="+fileName+"&inputPath="+filePath;
}

