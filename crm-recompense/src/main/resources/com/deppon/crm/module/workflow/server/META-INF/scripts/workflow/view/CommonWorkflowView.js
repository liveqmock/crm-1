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
						store :getDataDictionaryByName(DataDictionary,'REC_WORKFLOW_TYPE'),
						queryMode : 'local',
						forceSelection : true,
						displayField : 'codeDesc',
						valueField : 'code',
						editable:false,
						value:'1'
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
					return	isDetail?"<a href=javascript:showDetailWorkflow('showDetailWorkflow.action?processInstId="+r.get('processinstidEnc')+"&processDefName="+r.get('processDefName')+"');>"+v+"</a>":
					"<a  href=javascript:showDetailWorkflow('doApproveWorkflow.action?processInstId="+r.get('processinstidEnc')+"&workItemId="+r.get('workItemIdEnc')+"&processDefName="+r.get('processDefName')+"');>"+v+"</a>"
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
			cls:'workFlowGrid',
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
				text:i18n('i18n.workflow.approvever'),dataIndex:'approvever',width:190,
				renderer: function(value,metaData,record,colIndex,store,view) {  
				       if(!Ext.isEmpty(value)){
				    	   metaData.tdAttr = 'data-qtip="'+value+'"';  
					       return value; 
				       } 
				}  
			}],
			viewConfig:{
				forceFit:true
			}
		});
}

/**
 * 常规理赔弹出框详情表单
 */
Ext.define('CommonDetailPopForm',{
	extend:'PopTitleFormPanel',
	autoScroll : true,
	frame:true,
	cls:'reanOnlyPanel',
	margin:'3 0 3 0',
	items:[{
		xtype:'container',
		defaultType:'displayfield',
		defaults:{
			width:250,
			labelAlign:'right',
			readOnly : true
		},
		layout:{
			type:'table',
			columns:3
		},		
		items:[{
			name:'transportOrErrorCode',
			fieldLabel:i18n('i18n.workflow.transportOrErrorCode')
		},{
			name:'haulType',
			fieldLabel:i18n('i18n.workflow.haulType')
			
		},{
			name:'startingStation',
			fieldLabel:i18n('i18n.workflow.startingStation')
			
		},{
			name:'insuredUnits',
			fieldLabel:i18n('i18n.workflow.insuredUnits')
			
		},{
			name:'contactPhone',
			fieldLabel:i18n('i18n.workflow.contactPhone')
			
		},{
			name:'receivingDept',
			fieldLabel:i18n('i18n.workflow.receivingDept')
			
		},{
			name:'goodsName',
			fieldLabel:i18n('i18n.workflow.goodsName')
			
		},{
			name:'goodsAttribute',
			fieldLabel:i18n('i18n.workflow.goodsAttribute')
			
		},{
			name:'insuredAmount',
			fieldLabel:i18n('i18n.workflow.insuredAmount')
			
		},{
			name:'targetDept',
			fieldLabel:i18n('i18n.workflow.targetDept')
			
		},{
			name: 'sendingDate',
			fieldLabel: i18n('i18n.workflow.sendingDate'),
			editable: false
		},
		{	
			name: 'dangerDate',
			fieldLabel: i18n('i18n.workflow.dangerDate'),
			editable: false
		},{
			name:'area',
			fieldLabel:i18n('i18n.workflow.area')
			
		},{
			name:'claimsType',
			fieldLabel:i18n('i18n.workflow.claimsType')
			
		},{
			name:'offsetType',
			fieldLabel:i18n('i18n.workflow.offsetType')
			
		},{
			name:'caseReporterName',
			fieldLabel:i18n('i18n.workflow.caseReporterName')
			
		},{
			name:'reportDept',
			fieldLabel:i18n('i18n.workflow.reportDept')
			
		},{	
			name: 'reportDate',
			fieldLabel: i18n('i18n.workflow.reportDate'),
			editable: false
		},{
			name:'handler',
			fieldLabel:i18n('i18n.workflow.handler')
			
		},{	
			name: 'handleDate',
			fieldLabel: i18n('i18n.workflow.handleDate'),
			editable: false
		},{
			name:'claimAmount',
			fieldLabel:i18n('i18n.workflow.claimAmount')
			
		},{
			name:'normalAmount',
			fieldLabel:i18n('i18n.workflow.normalAmount')		
		},{
			name:'actualClaimsAmount',
			fieldLabel:i18n('i18n.workflow.actualClaimsAmount')		
		},{
			name:'toCompanyCost',
			fieldLabel:i18n('i18n.workflow.toCompanyCost')
		}
		]},{
			xtype:'container',
			defaultType:'displayfield',
			defaults:{
				width:750,
				labelAlign:'right',	
				readOnly : true
			},
			layout:{
				type:'table',
				columns:1
			},		
			items:[{
					name:'toDeptCost',
					fieldLabel:i18n('i18n.workflow.toDeptCost')
				  },{
					name:'issueItem',
					fieldLabel:i18n('i18n.workflow.issueItem')
				  },{
				  	xtype:'textfield',
					name:'otherCost',
					fieldLabel:i18n('i18n.workflow.otherCost')
				  },{
					name:'awardItem',
					fieldLabel:i18n('i18n.workflow.awardItem')
				  },{
					name:'responsibileDept',
					fieldLabel:i18n('i18n.workflow.responsibileDept')
				  },{
					name:'fileName',
					fieldLabel:i18n('i18n.workflow.fileName')
				}]
		}],
		getRenderFun: function(t){
		 	 var mask=new Ext.LoadMask(Ext.getBody(), {msg:"loading..." });
		 	 mask.show();
			 var param = {processInstId:getUrlVars('processInstId')};
				var successFn = function(json){
					var nc=json.normalClaim,dcl=json.deptChargeList,iil=json.issueItemList,rdl=json.responsibleDeptList,ail=json.awardItemList;
					if(!nc){
						MessageUtil.showErrorMes(i18n('i18n.workflow.workflowNotExist'));
						mask.hide();
						return;
					}
					var form=t.getForm();
					form.findField("transportOrErrorCode").setValue(nc.transportOrErrorCode);
					form.findField("haulType").setValue(rendererDictionary(nc.haulType,DataDictionary.TRANSPORT_TYPE));
					form.findField("startingStation").setValue(nc.startingStation);
					form.findField("insuredUnits").setValue(nc.insuredUnits);
					form.findField("contactPhone").setValue(nc.contactPhone);
					form.findField("receivingDept").setValue(nc.receivingDept);
					form.findField("goodsName").setValue(nc.goodsName);
					form.findField("goodsAttribute").setValue(nc.goodsAttribute);
					form.findField("insuredAmount").setValue(nc.insuredAmount);
					form.findField("targetDept").setValue(nc.targetDept);
					form.findField("sendingDate").setValue(DpUtil.renderDate(nc.sendingDate));
					form.findField("dangerDate").setValue(DpUtil.renderDate(nc.dangerDate));
					form.findField("area").setValue(nc.area);
					form.findField("claimsType").setValue(nc.claimsType);
					form.findField("offsetType").setValue(nc.offsetType);
					form.findField("caseReporterName").setValue(nc.caseReporterName);
					form.findField("reportDept").setValue(nc.reportDeptName);
					form.findField("reportDate").setValue(DpUtil.renderTime(nc.reportDate));
					form.findField("handler").setValue(nc.handler);
					form.findField("handleDate").setValue(DpUtil.renderTime(nc.handleDate));
					form.findField("claimAmount").setValue(nc.claimAmount);
					form.findField("normalAmount").setValue(nc.normalAmount);
					form.findField("actualClaimsAmount").setValue(nc.actualClaimsAmount);
					form.findField("toCompanyCost").setValue(nc.toCompanyCost);
					form.findField("otherCost").setValue(nc.otherCost);
					if(nc.fileInfoList&&nc.fileInfoList.length>0){
						 var v='';
						for(var i=0;i<nc.fileInfoList.length;i++){
							v=v+"<a target='_blank'  href='../common/downLoad.action?fileName="+nc.fileInfoList[i].fileName+"&inputPath="+nc.fileInfoList[i].savePath+"'>"+nc.fileInfoList[i].fileName+"</a>&nbsp;&nbsp;"
						}
						form.findField("fileName").setValue(v);
					}else{
						 form.findField("fileName").setValue(i18n('i18n.workflow.none')); 
					}
					//入部门费用
					var dcStr='';
					for(var i=0;i<dcl.length;i++){
					 	dcStr=dcStr+(i>0?'<br/>':'')+ dcl[i].deptName+dcl[i].amount+i18n('i18n.workflow.unit');
					}
					form.findField("toDeptCost").setValue(dcStr);
					//出险信息
					var iiStr='';
					for(var i=0;i<iil.length;i++){
					 	iiStr=iiStr+(i>0?'<br/>':'')+iil[i].description+'';
					}
					form.findField("issueItem").setValue(iiStr);
					
					//奖罚说明
					var aiStr='';
					for(var i=0;i<ail.length;i++){
					 	aiStr=aiStr+(i>0?'<br/>':'')+rendererDictionary(ail[i].awardType,DataDictionary.AWARD_TYPE)
					 	+rendererDictionary(ail[i].awardTargetType,DataDictionary.AWARD_TARGET_TYPE)+':'
					 	+ (ail[i].deptName?ail[i].deptName:'')+(ail[i].userNumber?i18n('i18n.workflow.empNum')+'('+ail[i].userNumber+')':'')+ail[i].amount+i18n('i18n.workflow.unit');
					}
					form.findField("awardItem").setValue(aiStr);
					
					//责任部门
					var rdStr='';
					for(var i=0;i<rdl.length;i++){
					 	rdStr=rdStr+(i>0?'<br/>':'')+rdl[i].deptName;
					}
					form.findField("responsibileDept").setValue(rdStr);
					
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
			workflowData.getNormalClaimByProNum(param,successFn,failFn);
		}

});



/**
 * 服务补救弹出框详情表单
 */
Ext.define('CommonServiceDetailPopForm',{
	extend:'PopTitleFormPanel',
	autoScroll : true,
	frame:true,
	cls:'reanOnlyPanel',
	margin:'3 0 3 0',
	items:[{
		xtype:'container',
		defaultType:'displayfield',
		defaults:{
			width:250,
			labelAlign:'right',
			readOnly : true
		},
		layout:{
			type:'table',
			columns:2
		},		
		items:[{
			name:'applicantName',
			fieldLabel:i18n('i18n.workflow.appler')
		},{
			name:'position',
			fieldLabel:i18n('i18n.workflow.apppost')
			
		},{
			name:'financeDeptName',
			fieldLabel:i18n('i18n.workflow.financeDeptName')
			
		},{
			name:'subsidiary',
			fieldLabel:i18n('i18n.workflow.subsidiary')
			
		},{
			name:'waybillNumber',
			fieldLabel:i18n('i18n.workflow.waybillNumber')
			
		},{
			name:'reductionAmount',
			fieldLabel:i18n('i18n.workflow.reductionAmount')
			
		},{
			name:'reductionName',
			fieldLabel:i18n('i18n.workflow.reductionName')
			
		},{
			name:'operatorName',
			fieldLabel:i18n('i18n.workflow.operatorName')
			
		},{
			name:'recoveryReason',
			fieldLabel:i18n('i18n.workflow.recoveryReason'),
			colspan:2
		  },{
			name:'fileName',
			fieldLabel:i18n('i18n.workflow.fileName'),
			colspan:2
		}
		]}],
		getRenderFun: function(t){
		 	 var mask=new Ext.LoadMask(Ext.getBody(), {msg:"loading..." });
		 	 mask.show();
			 var param = {processInstId:getUrlVars('processInstId')};
				var successFn = function(json){
					var serviceRecovery=json.serviceRecovery;
					if(!serviceRecovery){
						MessageUtil.showErrorMes(i18n('i18n.workflow.workflowNotExist'));
						mask.hide();
						return;
					}
					var form=t.getForm();
					var sr={};
					sr.data=serviceRecovery;
					form.loadRecord(sr);
					form.findField("reductionAmount").setValue(serviceRecovery.reductionAmount?(serviceRecovery.reductionAmount+i18n('i18n.workflow.unit')):'');
					form.findField("reductionName").setValue(rendererDictionary(serviceRecovery.reductionType,DataDictionary.SERVICERECOVERY_REDUCTION_TYPE));
					if(serviceRecovery.fileInfoList&&serviceRecovery.fileInfoList.length>0){
						var v='';
						for(var i=0;i<serviceRecovery.fileInfoList.length;i++){
							v=v+"<a target='_blank'  href='../common/downLoad.action?fileName="+serviceRecovery.fileInfoList[i].fileName+"&inputPath="+serviceRecovery.fileInfoList[i].savePath+"'>"+serviceRecovery.fileInfoList[i].fileName+"</a>&nbsp;&nbsp;"
						}
						form.findField("fileName").setValue(v);
					}else{
						 form.findField("fileName").setValue(i18n('i18n.workflow.none')); 
					}
					mask.hide();
				};
				var failFn = function(json){
					mask.hide();
					MessageUtil.showErrorMes(i18n('i18n.workflow.getWorkflowDetailFail'));
				};
			workflowData.getServiceByProNum(param,successFn,failFn);
		}

});


/**
 * 多赔弹出框详情表单
 */
Ext.define('CommonOverpayDetailPopForm',{
	extend:'PopTitleFormPanel',
	autoScroll : true,
	frame:true,
	cls:'reanOnlyPanel',
	margin:'3 0 3 0',
	items:[{
		xtype:'container',
		defaultType:'displayfield',
		defaults:{
			width:250,
			labelAlign:'right',
			readOnly : true
		},
		layout:{
			type:'table',
			columns:2
		},		
		items:[{
			name:'crateUserName',
			fieldLabel:i18n('i18n.workflow.appler')
		},{
			name:'transTypeName',
			fieldLabel:i18n('i18n.workflow.haulType')
			
		},{
			name:'waybillNumber',
			fieldLabel:i18n('i18n.workflow.overPayNumber')
			
		},{
			name:'overpayAmount',
			fieldLabel:i18n('i18n.workflow.overpayAmount')
			
		},{
			name:'totalAmount',
			fieldLabel:i18n('i18n.workflow.totalAmount')
			
		},{
			name:'recoverYszk',
			fieldLabel:i18n('i18n.workflow.recoverYszk')
			
		},{
			name:'deptAccountName',
			fieldLabel:i18n('i18n.workflow.deptAccountName')
			
		},{
			name:'deptName',
			fieldLabel:i18n('i18n.workflow.overPayDeptName')
			
		},{
			name:'overpayReason',
			fieldLabel:i18n('i18n.workflow.recoveryReason'),
			colspan:2
		  },{
			name:'fileName',
			fieldLabel:i18n('i18n.workflow.fileName'),
			colspan:2
		}
		]}],
		getRenderFun: function(t){
		 	 var mask=new Ext.LoadMask(Ext.getBody(), {msg:"loading..." });
		 	 mask.show();
			 var param = {processInstId:getUrlVars('processInstId')};
				var successFn = function(json){
					var overpay=json.overpay,fileList=json.overpayFileList;
					if(!overpay){
						MessageUtil.showErrorMes(i18n('i18n.workflow.workflowNotExist'));
						mask.hide();
						return;
					}
					var form=t.getForm();
					var op={};
					op.data=overpay;
					form.loadRecord(op);
					form.findField("overpayAmount").setValue(overpay.overpayAmount?(overpay.overpayAmount+i18n('i18n.workflow.unit')):'');
					form.findField("totalAmount").setValue(overpay.totalAmount?(overpay.totalAmount+i18n('i18n.workflow.unit')):'');
					form.findField("transTypeName").setValue(rendererDictionary(overpay.transType,DataDictionary.TRANSPORT_TYPE));
					form.findField("recoverYszk").setValue(overpay.recoverYszk?i18n('i18n.workflow.yes'):i18n('i18n.workflow.no'));
					if(overpay.deptAccount&&overpay.deptAccount.empCode){
						form.findField("deptAccountName").setValue(overpay.deptAccount.empCode.empName);
					}
					form.findField("deptName").setValue(overpay.overpayBu?overpay.overpayBu.deptName:'');
					if(fileList&&fileList.length>0){
						var v='';
						for(var i=0;i<fileList.length;i++){
							v=v+"<a target='_blank'  href='../common/downLoad.action?fileName="+fileList[i].fileName+"&inputPath="+fileList[i].savePath+"'>"+fileList[i].fileName+"</a>&nbsp;&nbsp;"
						}
						form.findField("fileName").setValue(v);
					}else{
						 form.findField("fileName").setValue(i18n('i18n.workflow.none')); 
					}
					mask.hide();
				};
				var failFn = function(json){
					mask.hide();
					MessageUtil.showErrorMes(i18n('i18n.workflow.getWorkflowDetailFail'));
				};
			workflowData.getOverpayByProNum(param,successFn,failFn);
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