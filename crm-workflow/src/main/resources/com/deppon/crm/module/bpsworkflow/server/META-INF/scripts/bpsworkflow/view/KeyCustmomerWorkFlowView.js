Ext.define('BigCustomerDetailForm',{
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
					name:'applyType', //申请类型
					fieldLabel:i18n('i18n.workflow.contract.applyType')
				},{
					name:'deptId',// 所属部门
					fieldLabel:i18n('i18n.workflow.contract.applyPersonDept')
				},{
					name:'customerCode',//客户编码
					fieldLabel:i18n('i18n.workflow.contract.customerCode')
				},{
					name: 'customerName',//客户名称
					fieldLabel: i18n('i18n.workflow.contract.customerName')
				},{
					name : 'customerLevel',//客户等级
					fieldLabel: i18n('i18n.workflow.keycustomer.customerLevel')
				},{
					name:'contactCode',// 联系人编码
					fieldLabel:i18n('i18n.workflow.keycustomer.contactCode')				
				},{
					name:'contactName',// 联系人姓名
					fieldLabel:i18n('i18n.workflow.keycustomer.contactName')				
				},{
					name:'cooperate',// 意愿程度
					fieldLabel:i18n('i18n.workflow.keycustomer.intentionsLevel')				
				},{
					name:'isSpecialBigCustomer',// 是否特殊大客户
					fieldLabel:i18n('i18n.workflow.keycustomer.specialKeyCustomer')				
				},{
					name:'warningTime',// 客户信用预警次数
					fieldLabel:i18n('i18n.workflow.keycustomer.warningTime')				
				},{
					name:'amountOfConsignment',// 近三月发货金额
					fieldLabel:i18n('i18n.workflow.contract.amountOfConsignment')
				},{
					name:'custPotential',// 次月在德邦发货潜力
					fieldLabel:i18n('i18n.workflow.keycustomer.sendPotential')				
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
				name:'applyReason',// 申请事由
				fieldLabel:i18n('i18n.workflow.contract.applyReason'),
				width:820,
				colspan:3
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
					var ci=json.keyCustomerWorkflowInfo;
					if(!ci){
						MessageUtil.showErrorMes(i18n('i18n.workflow.workflowNotExist'));
						mask.hide();
						return;
					}
					var form=t.getForm();
					
					form.findField("applyType").setValue(rendererDictionary(ci.workFlowType,DataDictionary.KEYCUST_WORKFLOW_TYPE));//申请类型
					form.findField("deptId").setValue(ci.deptName);							//所属部门
					form.findField("customerCode").setValue(ci.customerNum);				//客户编码
					form.findField("customerName").setValue(ci.customerName);				//客户名称
					form.findField("customerLevel").setValue(rendererDictionary(ci.custDegree,DataDictionary.MEMBER_GRADE));//客户等级
					form.findField("contactCode").setValue(ci.contactNum);					//联系人编码
					form.findField("contactName").setValue(ci.contactName);					//联系人名称
					form.findField("cooperate").setValue(rendererDictionary(ci.cooperate,DataDictionary.COOPERATION_INTENTION));//意愿程度
					form.findField("isSpecialBigCustomer").setValue(true == ci.isSpecialKeyCustomer ? "是" : "否");//是否特殊大客户
					form.findField("warningTime").setValue(ci.creditTimes);					//客户信用预警次数
					form.findField("amountOfConsignment").setValue(ci.amountOfSignMent);	//近三月发货金额
					form.findField("custPotential").setValue(ci.custPotential);				//次月在德邦发货潜力
					form.findField("applyReason").setValue(ci.applicationReason);			//申请事由
					
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
			workflowData.findBigCustomerWorkflowInfoByWorkNo(param,successFn,failFn);
		}

});
