Ext.define('RepetitveCustWorkFlowDetailForm',{
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
		    	columns:3    //列数
		    },
		    defaults:{
				width:280,
				lableWidth:80,
				readOnly : true
			},
	        
	        items :[{
					name:'customerCode',//客户编码
					fieldLabel:i18n('i18n.workflow.repetitivecust.customerCode')
				},{
					name: 'customerName',//客户名称
					fieldLabel: i18n('i18n.workflow.repetitivecust.customerName')
				},{
					name:'telphoneNum', //固定电话
					fieldLabel:i18n('i18n.workflow.repetitivecust.telphoneNum')
				},{
					name:'threeMonMoney',// 近三月发货金额
					fieldLabel:i18n('i18n.workflow.repetitivecust.threeMonMoney')
				},{
					name: 'customerLevel',//客户等级
					fieldLabel: i18n('i18n.workflow.repetitivecust.customerLevel')
				},{
					name:'deptName',// 所属部门
					fieldLabel:i18n('i18n.workflow.repetitivecust.deptName')
				},{
					name: 'areaName',//所属小区
					fieldLabel: i18n('i18n.workflow.repetitivecust.areaName')
				},{
					name:'regionName',// 所属大区
					fieldLabel:i18n('i18n.workflow.repetitivecust.regionName')				
				},{
					name:'bussDeptName',// 所属事业部
					fieldLabel:i18n('i18n.workflow.repetitivecust.bussDeptName')				
				},{
					name:'bigCust',// 是否是大客户
					fieldLabel:i18n('i18n.workflow.repetitivecust.bigCust')				
				},{
					name:'contract',// 有效合同信息
					fieldLabel:i18n('i18n.workflow.repetitivecust.contract')				
				},{
					name:'histryContract',// 历史合同信息
					fieldLabel:i18n('i18n.workflow.repetitivecust.histryContract')				
				}]
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
				name:'disposeOpinion',// 处理意见
				fieldLabel:i18n('i18n.workflow.repetitivecust.processSuggest'),
				width:820,
				colspan:3
			}]
	    }
	      
		]}	
		],
		getRenderFun: function(t){
		 	 var mask=new Ext.LoadMask(Ext.getBody(), {msg:"loading..." });
		 	 mask.show();
			 var param = {busino:getUrlVars('busino'),processDefName:getUrlVars('processDefName')};
				var successFn = function(json){
					var ci=json.repetitiveCustWorkFlowInfo;
					if(!ci){
						MessageUtil.showErrorMes(i18n('i18n.workflow.workflowNotExist'));
						mask.hide();
						return;
					}
					var form=t.getForm();
					
					form.findField("customerCode").setValue(ci.customerCode);						
					form.findField("customerName").setValue(ci.customerName);							
					form.findField("telphoneNum").setValue(ci.telphoneNum);				
					form.findField("threeMonMoney").setValue(ci.threeMonMoney);				
					form.findField("customerLevel").setValue(rendererDictionary(ci.customerLevel,DataDictionary.MEMBER_GRADE));				
					form.findField("deptName").setValue(ci.deptName);					
					form.findField("areaName").setValue(ci.areaName);					
					form.findField("regionName").setValue(ci.regionName);						
					form.findField("bussDeptName").setValue(ci.bussDeptName);
					form.findField("bigCust").setValue(ci.bigCust == 1 ? "是" : "否");					
					form.findField("contract").setValue(ci.contract > 0 ? "是" : "否");	
					form.findField("histryContract").setValue(ci.histryContract > 0 ? "是" : "否");				
					form.findField("disposeOpinion").setValue(ci.disposeOpinion);
					
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
			workflowData.findRepetitiveCustByWorkNo(param,successFn,failFn);
		}

});
