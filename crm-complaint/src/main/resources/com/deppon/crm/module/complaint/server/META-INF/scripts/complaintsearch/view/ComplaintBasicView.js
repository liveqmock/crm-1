 
/**
 * 客户工单查询详情 基本信息 panel
 */ 
Ext.define('ComplaintBasicFormPanel',{
	extend:'SearchFormPanel'
	,height:195
	//处理参数，用于是否加载相关事件
	,complaint:null// 客户工单基本信息
	,layout:{type:'table',columns:10}
	,defaultType:'textfield'
	,defaults:{labelWidth:60,readOnly:true,cls:'readonly'}
	,initComponent:function(){
		var me = this;
		me.items = me.getItems();
		this.callParent(arguments);
		me.getForm().loadRecord(new ComplaintModel(me.complaint));
		
		//期望时限由原先的分钟、小时或天数转化为时间节点显示格式
		if(me.complaint.tilimitcycle=='DAY'){
			me.getForm().findField("timelimit").setValue(Ext.Date.add(new Date(me.complaint.reporttime), 
					Ext.Date.DAY,me.complaint.timelimit));			
		}
		else if(me.complaint.tilimitcycle=='HOUR'){
			me.getForm().findField("timelimit").setValue(Ext.Date.add(new Date(me.complaint.reporttime), 
					Ext.Date.HOUR,me.complaint.timelimit));
		}
		else {
			me.getForm().findField("timelimit").setValue(Ext.Date.add(new Date(me.complaint.reporttime), 
					Ext.Date.MINUTE,me.complaint.timelimit));
		}
	
	}
	,getItems:function(){
		var me = this;
		return [
			//1
			{fieldLabel:i18n('i18n.complaint.comp_bill'),name:'bill',width:170,colspan:2,labelWidth:65}
			,{xtype:'button',text:'GO',width:45,margin:'0 0 5 1',
				handler:function(){
					var billValue=me.complaint.bill;
					if(!DpUtil.isEmpty(billValue) && isNaN(billValue.substr(0,1))){
						openOrderViewWindow(billValue);

					}else{
						MessageUtil.showMessage(i18n('i18n.complaintReport.validate.must_be_orderNo'));
					}
				}				
			}
			,{fieldLabel:i18n('i18n.complaint.comp_tel'),name:'tel',colspan:2,width:200}
			
			,{fieldLabel:i18n('i18n.complaint.comp_compman')/*来电人*/,name:'compman',colspan:2,width:150}
			,{/*来电人类型*/
				name:'sendorreceive',width:50,xtype : 'combobox',
				displayField:'codeDesc',valueField:'code',
				//调用了DataDictionary.js里面的根据名称得到对应数据字典数据的方法
				store:getDataDictionaryByName(DataDictionary,'CALLER_TYPE')	
			}
			,{/*上报类型*/
				fieldLabel:i18n('i18n.complaint.comp_reportType'),name:'reporttype',colspan:2,width:150,
				xtype : 'combobox',
				displayField:'codeDesc',
			    valueField:'code',
				//调用了DataDictionary.js里面的根据名称得到对应数据字典数据的方法
				store:getDataDictionaryByName(DataDictionary,'REPORT_TYPE')	
			}
			//2
			,{fieldLabel:i18n('i18n.complaint.comp_complaincust'),name:'complaincust',width:170,colspan:2,labelWidth:65}
			,{xtype:'button',text:'GO',width:45,margin:'0 0 5 1',scope:me,handler:function(){
				me.showComplaincustInfo(me.complaint.complainid);
			}}
			,{fieldLabel:i18n('i18n.complaint.comp_custLevel'),name:'custlevel',colspan:2,width:200,
			  readOnly:true,
			  xtype : 'combobox',
		      mode:           'local',
			  triggerAction:  'all',
			  forceSelection:true,
			  editable:false,//不能编辑
			  displayField:'codeDesc',
			  valueField:'code',
			//调用了DataDictionary.js里面的根据名称得到对应数据字典数据的方法
			 store:getDataDictionaryByName(DataDictionary,'MEMBER_GRADE')	
			}
			,{fieldLabel:i18n('i18n.complaint.comp_custType')/*客户类型*/,name:'custtype',colspan:3,width:200,
				readOnly:true,
				xtype : 'combobox',
				mode:           'local',
				triggerAction:  'all',
				forceSelection:true,
				editable:false,//不能编辑
				displayField:'codeDesc',
			    valueField:'code',
				//调用了DataDictionary.js里面的根据名称得到对应数据字典数据的方法
				store:getDataDictionaryByName(DataDictionary,'CUSTOMER_TYPE')
			}
			,{fieldLabel:i18n('i18n.complaint.comp_complaintSource'),name:'complaintsource',colspan:2,width:150,
				xtype : 'combobox',
				id:'BaseFormComplaintSource',
				displayField:'codeDesc',
			    valueField:'code',
				//调用了DataDictionary.js里面的根据名称得到对应数据字典数据的方法
				store:getDataDictionaryByName(DataDictionary,'WORK_SINGLE_SOURCE')	
			}
			//3
			,{fieldLabel:i18n('i18n.complaint.comp_relatcus'),name:'relatcus',width:170,colspan:2,labelWidth:65}
			,{xtype:'button',text:'GO',width:45,margin:'0 0 5 1',scope:me,handler:function(){
				me.showComplaincustInfo(me.complaint.relatcusid);
			}}
			,{fieldLabel:i18n('i18n.complaint.comp_relatcusLevel'),name:'relatcuslevel',colspan:2,width:200,
				readOnly:true,
				xtype : 'combobox',
				mode: 'local',
			   triggerAction:  'all',
			  forceSelection:true,
			   editable:false,//不能编辑
			  displayField:'codeDesc',
			  valueField:'code',
			//调用了DataDictionary.js里面的根据名称得到对应数据字典数据的方法
			 store:getDataDictionaryByName(DataDictionary,'MEMBER_GRADE')
			},
			/*
			 * 描述：期望时限由原先的分钟转化为时间节点显示格式
			 * 修改时间：2012-10-26
			 * 肖红叶
			 */
			{
				fieldLabel:i18n('i18n.complaint.comp_timeLimit')/*期望时限*/,
				name:'timelimit',
				colspan:3,
				width:200,
				format:'Y-m-d H:i',
				xtype :'datefield'
			},
//			{
//				fieldLabel:i18n('i18n.complaint.comp_timeLimit')/*期望时限*/,
//				name:'timelimit',
//				colspan:2,
//				width:150,
//				
//				renderer:function(value){
//					
//				}
//			},{
//				name:'tilimitcycle',width:50,
//				xtype : 'combobox',
//				displayField:'codeDesc',
//			    valueField:'code',
//				//调用了DataDictionary.js里面的根据名称得到对应数据字典数据的方法
//				store:getDataDictionaryByName(DataDictionary,'EXPECTED_TIME_LIMIT')				
//			},
			{
				fieldLabel:i18n('i18n.complaint.comp_pririty'),name:'pririty',colspan:2,width:150,
				xtype : 'combobox',
				displayField:'codeDesc',
			    valueField:'code',
				//调用了DataDictionary.js里面的根据名称得到对应数据字典数据的方法
				store:getDataDictionaryByName(DataDictionary,'PRIORITY_RATING')						
			}
			
			//4
			,{fieldLabel:i18n('i18n.complaint.comp_recomcode'),name:'recomcode',width:170,colspan:3,labelWidth:65}
			
			,{fieldLabel:i18n('i18n.complaint.comp_dealbill'),name:'dealbill',colspan:2,width:200}
			,{
				fieldLabel:i18n('i18n.complaint.comp_reporttime')/*上报时间*/,name:'reporttime',colspan:3,width:200
				,format:'Y-m-d H:i',xtype :'datefield'
			}
			,{fieldLabel:i18n('i18n.complaint.comp_reporter'),name:'reporter',colspan:2,width:150}
			,{fieldLabel:i18n('i18n.complaint.businessType'),name:'businessModel',colspan:10,xtype:'combo',queryMode: 'local',width:170,labelWidth:65,id:'baseFormBusinessModel',
			displayField: 'codeDesc',valueField: 'code',editable:false,store:getDataDictionaryByName(DataDictionary,'COMPLAINT_BUSINESS_MODEL')}
			//5
			,{xtype:'textareafield',fieldLabel:i18n('i18n.complaint.comp_reportContent'),name:'reportcontent',colspan:5,height:40,width:420,labelWidth:65}
			,{xtype:'textareafield',fieldLabel:i18n('i18n.complaint.comp_custRequire'),name:'custrequire',colspan:5,height:40,width:350}
			
		];
	}
	//显示客户信息
	,showComplaincustInfo:function(memberId){
	    var me=this;
	    if(DpUtil.isEmpty(memberId) || '0' == memberId){
			return;
		}
	    //调用客户GO 显示效果
	    CustviewUtil.openMemberWindow(memberId);
	}
});
