/**
 * 
 * @description ：服务补救详情界面
 * @author 邹明
 * @version 1.0
 * @date 2012-11-21 15:31
 * @update 2012-11-22 8:31
 * 
 */
Ext.onReady(function(){
	
	/*******************数据字典*******************************/
	var keys=[
	         'SPECIALDAY_TYPE' 
	    ];
	initDataDictionary(keys);//初始化数据字典
	var addOrUpdateWin;//声明弹窗
	
	/*******************新增界面*******************************/
	var addOrUpdateSpecialDayForm = Ext.create('PopTitleFormPanel',{
//	var addOrUpdateSpecialDayForm = Ext.create('Ext.form.Panel',{
		frame:true,
		defaults:{
			labelAlign:'left'
		},
		items:[{
			//'日期类型'
			xtype:'combo',
			name:'dateType',
			fieldLabel:'<span style="color:red">*</span>'+i18n('i18n.specialDay.DateType'),
			store:getDataDictionaryByName(DataDictionary,'SPECIALDAY_TYPE'),
			margin:'5 0 0 0',
			queryMode:'local',
			displayField:'codeDesc',
			valueField:'code',
			allowBlank:false,
			editable:false,
			blankText:i18n('i18n.specialDay.DateTypeCannotNull')//'日期类型不能为空',
		},{
			//'日期'
			xtype:'datefield',
			margin:'5 0 0 0',
			name:'specialDate',
			fieldLabel:'<span style="color:red">*</span>'+i18n('i18n.specialDay.Date'),
			allowBlank:false,
			blankText:i18n('i18n.specialDay.DateCannotNull')//'日期不能为空',
		},{
			xtype:'hiddenfield',
			name:'id'
		}],
		buttons:[{
			//提交
			text:i18n('i18n.util.btn.submit'),//'提交',
			margin:'0 28  0 0',
			handler:function(){
				
				//如果验证通不过不执行下面的动作
				if(!addOrUpdateSpecialDayForm.getForm().isValid()){
					return;
				}
				
				//如果日起类型为只读，执行更新操作；否则执行提交操作
				var param = {
					'id': addOrUpdateSpecialDayForm.getForm().findField('id').getValue(),
					'dateType': addOrUpdateSpecialDayForm.getForm().findField('dateType').getValue(),
					'specialDate': addOrUpdateSpecialDayForm.getForm().findField('specialDate').getValue()
				};
				
				//如果日期类型为只读，执行修改Action
				if(addOrUpdateSpecialDayForm.getForm().findField('specialDate').readOnly){
					var fnSuccess=function(json) {
						specialDayStore.loadPage(1);
						addOrUpdateWin.close();
						MessageUtil.showInfoMes (i18n('i18n.specialDay.modifySuccess'),"");
				    };
				    var fnFail=function(json) {
				    	if(Ext.isEmpty(json)){
//				    		MessageUtil.showErrorMes ("超时","")
				    	}else{
				    		MessageUtil.showErrorMes (json.message,"")
				    	}
				    };
				    SpecialDayData.prototype.updateSpecialDay (param,fnSuccess,fnFail);
				}else{//如果日起类型不为只读，执行新增Action
					var fnSuccess=function(json) {
						specialDayStore.loadPage(1);
						addOrUpdateWin.close();
						MessageUtil.showInfoMes (i18n('i18n.specialDay.addSuccess'),"");
				    };
				    var fnFail=function(json) {
				    	if(Ext.isEmpty(json)){
//				    		MessageUtil.showErrorMes ("超时","")
				    	}else{
				    		MessageUtil.showErrorMes (json.message,"")
				    	}
				    	
				    };
				    SpecialDayData.prototype.submitSpecialDay (param,fnSuccess,fnFail);
				}
			}
		},{
			//'重置',
			text:i18n('i18n.ladingstation.button.reset'),
			margin:'0 28 0 0',
			handler:function(){
				var addOrUpdateForm = addOrUpdateSpecialDayForm.getForm();
				var specialDateValue = addOrUpdateForm.findField('specialDate').getValue();
				addOrUpdateSpecialDayForm.getForm().reset();
				if(addOrUpdateForm.findField('specialDate').readOnly){
					addOrUpdateForm.findField('specialDate').setValue(specialDateValue);
				}
			}
		}]
		
	});	
	
	/*******************工作日查询form*******************************/
	var queryspecialDayForm = Ext.create('SearchFormPanel',{
		frame:true,
		margin:'10 10 0 10',
		layout:{
			type:'table',
			columns:3
		},
		defaultType:'datefield',
		dafaults:{
			labelAlign:'right'
		},
		items:[{
			//'日期类型'
			xtype:'combo',
			fieldLabel:i18n('i18n.specialDay.DateType'),
			name:'dateType',
			store:getDataDictionaryByName(DataDictionary,'SPECIALDAY_TYPE'),
			margin:'5 0 0 0',
			queryMode:'local',
			editable:false,
			displayField:'codeDesc',
			valueField:'code'
		},{
			//'起始日期'
			margin:'5 0 0 0',
			editable:false,
			name:'startDate',
			fieldLabel:i18n('i18n.specialDay.startDate')
		},{
			//'到'
			margin:'5 0 0 0',
			name:'endDate',
			editable:false,
			fieldLabel:i18n('i18n.helpdoc.to_date')
		}]
	});
	/********************中间按钮******************************/
	var formAndBtnPanel = Ext.create('NormalButtonPanel',{
		margin:'0 10 0 10',
		items:[{
			xtype:'leftbuttonpanel',
			defaultType:'button',
			items:[{
				//'新增',
				text:i18n('i18n.specialDay.add'),
				handler:function(){
					var formAdd = addOrUpdateSpecialDayForm.getForm();
					formAdd.reset();
					formAdd.findField('specialDate').setReadOnly(false);
					
					if(addOrUpdateWin==null){
						addOrUpdateWin = Ext.create('PopWindow',{
							width:350,
							title:i18n('i18n.specialDay.add'),//'新增',
							height:180,
							layout:'fit',
							closeAction:'hide',
							items:addOrUpdateSpecialDayForm
						});
					}else{
						addOrUpdateWin.setTitle(i18n('i18n.specialDay.add'));
					}
					addOrUpdateWin.show();
				}
			},{
				//修改',
				text:i18n('i18n.specialDay.modify'),
				handler:function(){
					var formUpdate = addOrUpdateSpecialDayForm.getForm();
					
					//在修改界面，日期类型设置为只读
					formUpdate.findField('specialDate').setReadOnly(true);
					
					//修改界面form读取信息
					var record = searchGridPanel.getSelectionModel().getSelection();
					if(record.length!=1){
						MessageUtil.showMessage(i18n('i18n.common.specialday.change.onlyOne'),'');
						return;
					}
					var param = {
							'id':record[0].get('id')
					};
					var fnSuccess = function(json){
						json.specialDay.specialDate = DpUtil.renderDate (json.specialDay.specialDate);
//						json.specialDay.dateType = rendererDictionary(
//												json.specialDay.dateType,DataDictionary.SPECIALDAY_TYPE);
						console.log(json.specialDay.dateType);
						var model = new SpecialDayModel(json.specialDay);
						formUpdate.loadRecord(model);
						
						if(addOrUpdateWin==null){
							addOrUpdateWin = Ext.create('PopWindow',{
								width:350,
								title:i18n('i18n.specialDay.modify'),//'修改',
								height:180,
								layout:'fit',
								closeAction:'hide',
								items:addOrUpdateSpecialDayForm
							});
						}else{
							addOrUpdateWin.setTitle(i18n('i18n.specialDay.modify'));
						}
						//只有选择了一行时才能弹出修改窗口
						if(searchGridPanel.getSelectionModel().getSelection().length==1){
							addOrUpdateWin.show();
						}
					}
					var fnFail = function(json){
						if(Ext.isEmpty(json)){
//				    		MessageUtil.showErrorMes ("超时","")
				    	}else{
				    		MessageUtil.showErrorMes (json.message,"")
				    	}
					}
					SpecialDayData.prototype.getSpecialDayById(param,fnSuccess,fnFail);
					
				}
			},{
				//删除',
				text:i18n('i18n.util.btn.delete'),
				handler:function(){
					
					var record = searchGridPanel.getSelectionModel().getSelection();
					if(record.length==0){
						MessageUtil.showMessage(i18n('i18n.common.specialday.delete.choice'),'');
						return;
					}
					
					var idArray = new Array();
					for ( var i = 0; i < record.length; i++) {
						idArray.push(record[i].get('id'));
					}
					var param = {
						'idArray':idArray
					};
					var fnSuccess = function(json){
							specialDayStore.loadPage(1);
					};
					var fnFail = function(json){
						if(Ext.isEmpty(json)){
//				    		MessageUtil.showErrorMes ("超时","")
				    	}else{
				    		MessageUtil.showErrorMes (json.message,"")
				    	}
					};
					MessageUtil.showQuestionMes (i18n('i18n.common.specialday.delete.sure1')+record.length+i18n('i18n.common.specialday.delete.sure2'),callBack,"");
					function callBack(id) { 
						if(id=='yes'){
							//在显示界面删掉选中行
							searchGridPanel.getStore().remove(record);
							//在数据库删除选中行
							SpecialDayData.prototype.deleteSpecialDayByIdArrayList(param,fnSuccess,fnFail);
						}
						if(id=='no'){
							return;
						}
					}
				}
			}]
		},{
			xtype:'middlebuttonpanel'
		},{
			xtype:'rightbuttonpanel',
			items:[{
				//'查询',
				xtype:'button',
				text:i18n('i18n.util.btn.search'),
				handler:function(){
					var startDate		=  queryspecialDayForm.getForm().findField('startDate').getValue(),//开始时间
						endDate			=  queryspecialDayForm.getForm().findField('endDate').getValue();//结束时间
					if(startDate<=endDate){//起始时间小于结束时间才能执行查询
						specialDayStore.loadPage(1);
					}else{
						MessageUtil.showMessage(i18n('i18n.specialDay.startTimeCannotMoreThanEndTime'));
						return;
					}
				}
			},{
				//'重置',
				xtype:'button',
				text:i18n('i18n.ladingstation.button.reset'),
				handler:function(){
					queryspecialDayForm.getForm().reset();
				}
			}]
		}]
	});

	/*****************底部grid表格**************************/
	//load之前注入参数
	specialDayStore.on('beforeload',function(store,operation){
		var dateType		=  queryspecialDayForm.getForm().findField('dateType').getValue(),//日期类型
			startDate		=  queryspecialDayForm.getForm().findField('startDate').getValue(),//开始时间
			endDate			=  queryspecialDayForm.getForm().findField('endDate').getValue();//结束时间
			var params={
					'dateType':dateType,
					'startDate':startDate,
					'endDate':endDate
			};
			Ext.apply(operation,{	
				params : params
			});
	});
	
	var searchGridPanel =Ext.create('SearchGridPanel',{
		store:specialDayStore,
		margin:'0 5 5 5',
		selModel:Ext.create('Ext.selection.CheckboxModel',{
		}),
		columns:[{
			//序号'
			text:i18n('i18n.specialDay.sequenceNumber'),xtype:'rownumberer',width:50
		},{
			//'日期'
			text:i18n('i18n.specialDay.Date'),dataIndex:'specialDate',flex:1,
			renderer:function(value){
				return DpUtil.renderDate (value);
			}
		},{
			//'星期'
			text:'星期',dataIndex:'specialDate',flex:1,
			renderer:function(value){
				var d = new Date(value).getDay();
				switch(d){
					case 0:d="日";break;
					case 1:d="一";break;
					case 2:d="二";break;
					case 3:d="三";break;
					case 4:d="四";break;
					case 5:d="五";break;
					case 6:d="六";break;
				}
				return '星期'+d;
			}
		},{
			//'日期类型'
			text:i18n('i18n.specialDay.DateType'),dataIndex:'dateType',flex:1,
			renderer:function(value){
				return rendererDictionary(value,DataDictionary.SPECIALDAY_TYPE);
			}
		}]
	});
	
	/******************整体布局*****************************/
	Ext.create('Ext.container.Viewport',{
		layout:'border',
		border:false,
		items:[{
			region:'north',
			border:false,
			items:queryspecialDayForm
		},{
			region:'center',
			layout:'border',
			border:false,
			items:[{
				region:'north',
				border:false,
				items:formAndBtnPanel
			},{
				region:'center',
				border:false,
				layout:'fit',
				items:searchGridPanel
			}]
		}]
	});
	
	
});