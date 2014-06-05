/*
 * 营销短信页面
 * 肖红叶
 * 2013-09-27
 */

//定义全局变量，用以记录要查看发送详情的任务id
var taskId = null;
Ext.onReady(function(){
	//短信内容+发送对象文件PANEL
    Ext.define('ContentAndSendObjectPanel',{
		extend:'TitleFormPanel', 
		layout:'border',
		height:260,
		items:null,
		initComponent:function(){
			this.items = this.getItems();
			this.callParent();
		},
		getItems:function(){
			var me = this;
			return [{
				xtype:'basicfiledset',
				name:'smsContentFieldSet',
				region:'center',
				title:i18n('i18n.messageSend.messageContext'),
				layout:'fit',
				items:[{
					xtype:'textareafield',
		            name:'smsText',
		            fieldLabel: '',
		            labelSeparator:'',
		            labelWidth:10,
		            maxLength:140,
		            maxLengthText:i18n('i18n.messageSend.contextCanNotExceedLimit'),
		            allowBlank:false,
		            blankText:i18n('i18n.messageSend.contextCanNotBeBlank')
				}
				]
			},{//发送对象
				xtype:'basicfiledset',
				region:'south',
				title:i18n('i18n.messageSend.messageSendTo'),
				height:70,
				layout:'fit',
				items:[{
					xtype:'basicformpanel',
					id:'fileUploadFormId',
					border:false,
			    	layout : {
			    		type : 'table',
			    		columns : 4
			    	},
			    	margin:'2 0 0 0',
			    	items:[{//文件导入
		    	        	xtype:'filefield',
							name:'file',
							fieldLabel:i18n('i18n.messageSend.telephones'),
							buttonText:i18n('i18n.messageSend.selectFileToSend'),
							width:550,
							allowBlank:false,
							blankText:i18n('i18n.messageSend.telephonesCanNotBeNull'),
							colspan:3
		    			},{//下载导入模板
		    				xtype:'button',
		    				margin:'0 0 2 50',
		    				text:i18n('i18n.messageSend.downloadTemplate'),
		    				handler:function(){
		    					window.open('../common/templateDownLoad.action?propKey=MarketMessageTemplate');
			    					
		    				}
		    		},{//属于的模块类型
		    			name:'type',
		    			xtype:'hiddenfield',
		    			value:'marketing'
		    		},{//上传的类型是否为附件
		    			name:'imageOrAttachment',
		    			xtype:'hiddenfield',
		    			value:'attachment'
		    		},{//最大可允许上传的文件大小
		    			name:'maxSizeLimit',
		    			xtype:'hiddenfield',
		    			value:'650K'
		    		}]
				}
				]
			}];
		}
	});
    
    //查看发送详情、发送等按钮面板
    Ext.define('SendDetailButtonPanel',{
		extend:'NormalButtonPanel', 
		region:'north',
		items:null,
		initComponent:function(){
			this.items = this.getItems();
			this.callParent();
		},
		getItems:function(){
			var me = this;
			return [{
				xtype:'leftbuttonpanel',
				width:200,
				items : [{//查看发送详情
					xtype:'button',
					disabled:true,
					id:'sendDetailButtonId',
					text:i18n('i18n.messageSend.detail'),
					handler:function(btn){
						//获取选中的文件列表
						var selection=Ext.getCmp('fillInfoListGridId').getSelectionModel().getSelection();
						//判断是否选中行
						if (selection.length == 0) {
							MessageUtil.showErrorMes(i18n('i18n.developPlan.choice'));
							return false;
						}
						if (selection.length != 1) {
							MessageUtil.showErrorMes(i18n('i18n.developPlan.chooseOne'));
							return false;
						}
						if(Ext.isEmpty(selection[0].data) || Ext.isEmpty(selection[0].data.status) ||
								selection[0].data.status === '0' || 
								selection[0].data.status === '1'){
							MessageUtil.showErrorMes(i18n('i18n.messageSend.invalidStatusToSearch'));
							return false;
						}
						if(Ext.isEmpty(selection[0].data.id) || selection[0].data.id === ''){
							MessageUtil.showErrorMes(i18n('i18n.messageSend.nullTaskId'));
							return false;
						}
						var sendDetailInfoWin = Ext.getCmp("sendDetailInfoWinId");//获取手机号详情窗口
						if(!sendDetailInfoWin){
							sendDetailInfoWin = Ext.create('SendDetailInfoWin',{id:'sendDetailInfoWinId'});
						}
						Ext.getCmp('sendDetailInfoGridId').store.removeAll();
						sendDetailInfoWin.show();
						taskId = selection[0].data.id;
						Ext.getCmp('sendDetailInfoGridId').store.loadPage(1);						
					}
				}]
			},{
				xtype:'middlebuttonpanel'
			},{
				xtype : 'rightbuttonpanel',
				items : [{//发送按钮
					xtype:'button',
					text:i18n('i18n.messageSend.send'),
					handler:function(){
						//获取短信内容+发送对象文件PANEL
						var contentAndSendObjectPanel = Ext.getCmp('contentAndSendObjectPanelId').getForm();
						//校验输入的短信内容和发送对象是否合法
						if(!contentAndSendObjectPanel.isValid()){
							return false;
						}
						//获取短信内容
						var smsText = contentAndSendObjectPanel.findField('smsText').getValue();
						//获取短信发送对象的form
						var fileUploadForm = Ext.getCmp('fileUploadFormId');
						MessageUtil.showQuestionMes(i18n('i18n.messageSend.isSureToSend'), function(e) {
		            		if (e == 'yes') {
		            			fileUploadForm.submit({
						            url:'../common/fileUpload.action',
						            waitMsg:i18n('i18n.messageSend.sending'),
						            success:function(form,response) {
						            	var result = response.result;
						            	if(!result.success){
						            		MessageUtil.showErrorMes(result.message);
						            		return false;
										}
										var fileInfo = result.fileInfoList[0];
										var successFn = function(json){
											contentAndSendObjectPanel.reset();
											Ext.getCmp('fillInfoListGridId').store.loadPage(1);
							            	MessageUtil.showInfoMes(i18n('i18n.messageSend.sendSuccess'));
										}
					        			var failureFn=function(json){
					        				if(!Ext.isEmpty(json)){
					        					MessageUtil.showErrorMes(json.message);
					        				}
					        			}
					        			var param = {
					        					'messageSendTask.filePath':fileInfo.savePath,//上传文件到服务器后的路径
					        					'messageSendTask.fileName':fileInfo.fileName,//上传文件的文件名
					        					'messageSendTask.msgContent':smsText//发送的短信内容
					        			}
					        			MarketingMessageStore.prototype.saveMessageFile(param,successFn,failureFn);
						           },
						           failure:function(form, response){
						             	var result = response.result;
						             	if(!result.success){
						                   	MessageUtil.showErrorMes(result.message);
										}
						           }
						       });
		            		}
						});
						
					}
				}]
			}];
		}
	});
    
    
    /**
	 * 文件发送列表
	 * 肖红叶
	 * 2013-09-28
	 */
	Ext.define('FillInfoListGrid',{
		extend:'SearchGridPanel',   
		columns:null,
		store:null,
		defaults:{
			align:'center'
		},
		selModel : Ext.create('Ext.selection.CheckboxModel',{
			mode:'SINGLE',
			allowDeselect:true,
			listeners:{
				select:function(th,record,index){
					if(Ext.isEmpty(record) || Ext.isEmpty(record.data) || 
							Ext.isEmpty(record.data.status) || record.data.status === '0' ||
							 record.data.status === '1'){
						Ext.getCmp('sendDetailButtonId').disable();
					}
					else if(record.data.status === '2' || record.data.status === '3'){
						Ext.getCmp('sendDetailButtonId').enable();
					}
				},
				deselect:function(th,record,index){
					Ext.getCmp('sendDetailButtonId').disable();
				}
			}
		}),
		region:'center',
		initComponent:function(){             
			var me = this;
			var me = this;
			var store =  Ext.create('FileInfoStore',{'id':'fileInfoStoreId'});
			store.loadPage(1);
			me.store = store;
			me.columns = [{//文件名称
				header : i18n('i18n.messageSend.fileName'),
				width:220,
				dataIndex:'fileName'
			},{//文件发送状态
				header : i18n('i18n.messageSend.fileSendStatus'),
				flex:1,
				dataIndex:'status',
				renderer:function(val){
					if(!Ext.isEmpty(val)){
						if(val === i18n('i18n.messageSend.zero')){
							return i18n('i18n.messageSend.notExecute');
						}
						else if(val === i18n('i18n.messageSend.one')){
							return i18n('i18n.messageSend.executing');
						}
						else if(val === i18n('i18n.messageSend.two')){
							return i18n('i18n.messageSend.executeSuccess');
						}
						else if(val === i18n('i18n.messageSend.three')){
							return i18n('i18n.messageSend.executeFail');
						}
					}
				}
			},{//开始时间
				header : i18n('i18n.messageSend.beginDate'),
				width:220,
				renderer : DButil.renderDateDetail,
				dataIndex:'beginDate'
			},{//结束时间
				header : i18n('i18n.messageSend.endDate'),
				width:220,
				renderer : DButil.renderDateDetail,
				dataIndex:'endDate'
			}];
			me.dockedItems=[{
				xtype:'pagingtoolbar',
				cls:'pagingtoolbar',
				store:store,
				dock:'bottom',
				displayInfo : true,
				autoScroll : true,
				items:[{
					text: i18n('i18n.messageSend.perPage'),
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
					text: i18n('i18n.messageSend.item'),
					xtype: 'tbtext'
	           }]
			}];
			this.callParent();
		}
	});
    /**
	 *将界面显示到viewport中 
	 *肖红叶
	 *20130927
	 */
	var viewport=Ext.create('Ext.Viewport',{
		layout : 'border',
		autoScroll:true,
		items:[Ext.create('ContentAndSendObjectPanel',{
			region:'north',
			id:'contentAndSendObjectPanelId'
		}),{
			xtype:'container',
			region:'center',
			layout:'border',
			items:[Ext.create('SendDetailButtonPanel'),
			       Ext.create('FillInfoListGrid',{id:'fillInfoListGridId'})
			]
		}]
	});
	viewport.setAutoScroll(false);
	viewport.doLayout();
	
	/*
	 * 单击查看发送详情按钮，弹出的手机号详情列表
	 * 肖红叶
	 * 2013-09-27
	 */
	Ext.define('SendDetailInfoGrid',{
		extend:'PopupGridPanel',
		defaults:{
			align:'center'
		},
		autoScroll:true,
		initComponent:function(){ 
			var me = this;
			var store =  Ext.create('SendDetailInfoStore',{'id':'sendDetailInfoStoreId'});
			store.on('beforeload',function(store,operation,e){
				var params = {
					'taskId':taskId
				}
				Ext.apply(operation,{
					params : params
				});
			});
			me.store = store;
			me.columns = [
			    {//序号
					xtype:'rownumberer',
					header:i18n('i18n.Cycle.rownum'),
					width:50
			    },{//手机号码
					header:i18n('i18n.messageSend.tel'),
					flex:0.5,
					dataIndex:'phone'
			    },{ //发送状态
					header :i18n('i18n.messageSend.sendStatus'),
					dataIndex:'status',
					width:100,
					renderer:function(val){
						if(!Ext.isEmpty(val)){
							if(val === i18n('i18n.messageSend.zero')){
								return i18n('i18n.messageSend.notSend');
							}
							else if(val === i18n('i18n.messageSend.one')){
								return i18n('i18n.messageSend.sendSuccess');
							}
							else if(val === i18n('i18n.messageSend.two')){
								return i18n('i18n.messageSend.sendFail');
							}
						}
					}
			    },{//是否有效
					header:i18n('i18n.messageSend.isValid'),
					width:100,
					dataIndex:'valid',
					renderer:function(val){
						if(!Ext.isEmpty(val)){
							if(val === i18n('i18n.messageSend.zero')){
								return i18n('i18n.messageSend.invalid');
							}
							else if(val === i18n('i18n.messageSend.one')){
								return i18n('i18n.messageSend.valid');
							}
						}
					}
			    },{ //发送时间
					header :i18n('i18n.messageSend.sendDate'),
					dataIndex:'sendDate',
					flex:0.5,
					renderer : DButil.renderDateDetail
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
					text: i18n('i18n.messageSend.perPage'),
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
					text: i18n('i18n.messageSend.item'),
					xtype: 'tbtext'
	           }]
		}];
	    this.callParent();
	   }
	});
	
	
	/**
	 * 单击查看发送详情按钮，弹出的手机号详情窗口
	 */
	Ext.define('SendDetailInfoWin',{
		extend:'PopWindow',
		title:i18n('i18n.messageSend.sendDetail'),
		layout:'fit',
		width:720,
		height:500,
		closeAction:'hide',
		items:[Ext.create('SendDetailInfoGrid',{id:'sendDetailInfoGridId'})],
		buttons:[{
			xtype:'button',
			text:i18n('i18n.DevelopManageView.close'),
			handler:function(){
				Ext.getCmp("sendDetailInfoWinId").hide();
			}
		}]
	});
});