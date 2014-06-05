/**
 * 订单基础资料维护
 */
Ext.onReady(function() {
	Ext.QuickTips.init();
	/**
	 * 重写TreeStore load bug
	 */
	Ext.override(Ext.data.TreeStore,{
		load: function(options) {
			 options = options || {};
        options.params = options.params || {};

        var me = this,
            node = options.node || me.tree.getRootNode(),
            root;

        // If there is not a node it means the user hasnt defined a rootnode yet. In this case lets just
        // create one for them.
        if (!node) {
            node = me.setRootNode({
                expanded: true
            }, true);
        }

        if (me.clearOnLoad) {
            if(me.clearRemovedOnLoad) {
                // clear from the removed array any nodes that were descendants of the node being reloaded so that they do not get saved on next sync.
                me.clearRemoved(node);
            }
            // temporarily remove the onNodeRemove event listener so that when removeAll is called, the removed nodes do not get added to the removed array
            me.tree.un('remove', me.onNodeRemove, me);
            // remove all the nodes
            node.removeAll(false);
            // reattach the onNodeRemove listener
            me.tree.on('remove', me.onNodeRemove, me);
        }

        Ext.applyIf(options, {
            node: node
        });
        options.params[me.nodeParam] = node ? node.getId() : 'root';

        if (node) {
            node.set('loading', true);
        }

        return me.callParent([options]);

		}
	});

	Ext.override(Ext.form.field.Text,{
		setFieldLabel: function(label){
	        this.getEl().parent().first().first().dom.innerHTML=label;
		}
	});
	
	
	Ext.define('DpTextArea',{
	extend:'Ext.form.TextArea', 
	alias : 'widget.dptextarea',
	//扩展getValue() 方法 去掉前后空格  用于提交
	getValue:function(){
		var val = this.callParent(arguments);
        return Ext.isEmpty(val)?val:val.trim();//return val;
    },
    //扩展getRawValue() 方法 去掉前后空格  用于表单验证
    getRawValue: function() {
    	var val = this.callParent();
        return Ext.isEmpty(val)?val:val.trim();//return val;
    }
});
	
	function createField(labelName,baseInfo,deallan,feedbackLimit,procLimit,procStandard,title){
		var form =popupForm.getForm();
		var baseInfoField=form.findField('baseInfo');
		var deallanField=form.findField('deallan');
		var feedbackLimitField=form.findField('feedbackLimit');
		var procLimitField=form.findField('procLimit');
		var procStandardField=form.findField('procStandard');
		baseInfoField.setFieldLabel(labelName);
		baseInfoField.allowBlank = baseInfo?false:true;
		deallanField.allowBlank = deallan?false:true;
		feedbackLimitField.allowBlank = feedbackLimit?false:true;
		procLimitField.allowBlank = procLimit?false:true;
		procStandardField.allowBlank = procStandard?false:true;
		baseInfo?baseInfoField.show():baseInfoField.hide();
		deallan?deallanField.show():deallanField.hide();
		feedbackLimit?feedbackLimitField.show():feedbackLimitField.hide();
		procLimit?procLimitField.show():procLimitField.hide();
		procStandard?procStandardField.show():procStandardField.hide();
		Ext.getCmp('OperatePopupWindow').setTitle(title);
	}
	
	/**
	 * 树顶部查询表单
	 */
	Ext.define('TreeSearchForm', {
    extend: 'SearchFormPanel',
    margin:'0 0 0 3',
    border:false,
    layout: {
        align: 'stretchmax',type: 'hbox'
    },
    padding:'3 5',
	items : [{
			xtype : 'textfield',
			width : 200,
			name:'queryName',
			margin:'0 2 0 0'
		}, {
			xtype : 'button',
			text : i18n('i18n.baseInfo.query'),
			handler :function(btn){
				if(treePanel.getSelectionModel().getCount()==0){
					MessageUtil.showMessage(i18n('i18n.baseInfo.pleaseSelectOneQuery'));
					return false;				
				}
				baseInfoStore.load({params:{
					queryName:btn.findParentByType('form').getForm().findField('queryName').getValue(),
					id:commonGridPanel.parentId
				}});
			}
		}]    
	});
	
	/**
	 * 基础资料布局左边 五级树形菜单
	 */
	Ext.define('TreeBaseInfoPanel', {
	    extend: 'Ext.tree.Panel',
	    border:false,
	    margin:false,
		height:'100%',
		cls:'normaltree',
		store: Ext.create("BaseIntoTreeStore"),
		rootVisible: false,       
		layoutConfig : {
			// 展开折叠是否有动画效果
			animate : true
		},
		// 监听事件
	    listeners: {
		    'load':function(s,r,d){
		    		if(commonGridPanel.parentId==0&&d[0]){
			    		commonGridPanel.columns[3].hide();
		    			commonGridPanel.columns[4].hide();
		    			commonGridPanel.columns[5].hide();
		    			commonGridPanel.columns[6].hide();
		    			commonGridPanel.columns[2].setText(i18n('i18n.baseInfo.businessItem'));
//		    			commonGridPanel.setTitle(d[0].raw.entity.typeCode=='COMPLAINT'?'投诉业务项设置':'异常业务项设置');
		    			commonGridPanel.setLevel(1);
		    			commonGridPanel.setParentId(d[0].raw.id);
		    			commonGridPanel.setClassCode(d[0].raw.entity.classCode);
		    			commonGridPanel.setTypeCode(d[0].raw.entity.typeCode);
		    			baseInfoStore.load({params:{id:d[0].get('id')}});
		    		}
				},	    
	    	itemclick : function (node,record,item,index,e){
	    		switch(record.raw.entity.level+1){
	    			//业务项
	    			case 1:
	    			commonGridPanel.columns[3].hide();
	    			commonGridPanel.columns[4].hide();
	    			commonGridPanel.columns[5].hide();
	    			commonGridPanel.columns[6].hide();
	    			commonGridPanel.columns[2].setText(i18n('i18n.baseInfo.businessItem'));
//	    			commonGridPanel.setTitle(record.raw.entity.typeCode=='COMPLAINT'?'投诉业务项设置':'异常业务项设置');
	    			break;
	    			//业务范围
	    			case 2:
	    			commonGridPanel.columns[3].hide();
	    			commonGridPanel.columns[4].hide();
	    			commonGridPanel.columns[5].hide();
	    			commonGridPanel.columns[6].hide();
	    			commonGridPanel.columns[2].setText(i18n('i18n.baseInfo.businessScope'));
//	    			commonGridPanel.setTitle(record.get('text')+'->业务范围设置');
	    			break;
	    			//业务类型
	    			case 3:
	    			commonGridPanel.columns[3].show();
	    			commonGridPanel.columns[4].show();
	    			commonGridPanel.columns[5].show();
	    			commonGridPanel.columns[6].hide();
	    			commonGridPanel.columns[2].setText(i18n('i18n.baseInfo.businessType'));
//	    			commonGridPanel.setTitle(record.parentNode.get('text')+'->'+record.get('text')+'->业务类型设置');
	    			break;
	    			//业务场景
	    			case 4:
	    			commonGridPanel.columns[3].hide();
	    			commonGridPanel.columns[4].hide();
	    			commonGridPanel.columns[5].hide();
	    			commonGridPanel.columns[6].hide();
	    			commonGridPanel.columns[2].setText(i18n('i18n.baseInfo.businessScene'));
//	    			commonGridPanel.setTitle(record.parentNode.parentNode.get('text')+'->'+record.parentNode.get('text')+'->'+record.get('text')+'->业务场景设置');
	    			break;
	    			//场景原因
	    			case 5:
	    			commonGridPanel.columns[3].hide();
	    			commonGridPanel.columns[4].hide();
	    			commonGridPanel.columns[5].hide();
	    			commonGridPanel.columns[6].show();
	    			commonGridPanel.columns[2].setText(i18n('i18n.baseInfo.businessSceneResson'));
//	    			commonGridPanel.setTitle(record.parentNode.parentNode.parentNode.get('text')+'->'+record.parentNode.parentNode.get('text')+'->'+record.parentNode.get('text')+'->'+record.get('text')+'->业务场景设置');
	    			break;
	    		}
	    		if(record.raw.entity.level!=5){
		    		commonGridPanel.setLevel(record.raw.entity.level+1);
		    		commonGridPanel.setParentId(record.raw.id);	
		    		commonGridPanel.setClassCode(record.raw.entity.classCode);
	    			commonGridPanel.setTypeCode(record.raw.entity.typeCode);
		    		baseInfoStore.load({params:{id:record.get('id')}});
	    		}
	    		
				/*if(record.data.id!='root'){
					dept=record;
					Ext.getCmp('UserGrid_pagingToolbar').moveFirst();
				}*/
	    	},
			scrollershow: function(scroller) {
		    	if (scroller && scroller.scrollEl) {
					scroller.clearManagedListeners(); 
					scroller.mon(scroller.scrollEl, 'scroll', scroller.onElScroll, scroller); 
				}
			}
	    }
	});
	
	var baseInfoStore = Ext.create('BaseInfoStore');
	/**
	 * 右边Grid
	 */
	Ext.define('CommonGridPanel',{
			extend:'SearchGridPanel',
			store:baseInfoStore,
//			title:'',
			border:true,
			enableColumnMove:false,
			margin:'0 0 0 3',
			level:0,
			parentId:0,
			classCode:'',
			typeCode:'',
			flex:1,
			selModel:Ext.create('Ext.selection.CheckboxModel'),//选择框
			columns:[{
				xtype : 'rownumberer',width : 40,text : i18n('i18n.baseInfo.number')
			},{
				text:i18n('i18n.baseInfo.businessItem'),dataIndex:'baseInfo',flex:1,
				renderer: function(value,metaData,record,colIndex,store,view) {  
				       if(!Ext.isEmpty(value)){
				    	   metaData.tdAttr = 'data-qtip="'+value+'"';  
					       return value; 
				       } 
				}  
			},{
				text:i18n('i18n.baseInfo.deallan'),dataIndex:'deallan',flex:1,hidden:true,
				renderer: function(value,metaData,record,colIndex,store,view) {  
				       if(!Ext.isEmpty(value)){
				    	   metaData.tdAttr = 'data-qtip="'+value+'"';  
					       return value; 
				       } 
				}  
			},{
				text:i18n('i18n.baseInfo.feedbackLimit'),dataIndex:'feedbackLimit',flex:1,hidden:true
			},{
				text:i18n('i18n.baseInfo.procLimit'),dataIndex:'procLimit',flex:1,hidden:true
			},{
				text:i18n('i18n.baseInfo.procStandard'),dataIndex:'procStandard',flex:1,hidden:true,
				renderer: function(value,metaData,record,colIndex,store,view) {  
				       if(!Ext.isEmpty(value)){
				    	   metaData.tdAttr = 'data-qtip="'+value+'"';  
					       return value; 
				       } 
				}  
			}],
			setLevel:function(v){
				this.level=v;//右边当前level
			},
			setParentId:function(v){
				this.parentId=v;//右边当前parentId
			},
			setClassCode:function(v){
				this.classCode=v;
			},
			setTypeCode:function(v){
				this.typeCode=v;
			},
			viewConfig:{
				forceFit:true
			},
			tbar : [{
				xtype:'button',   
				text:i18n('i18n.baseInfo.add'),
				handler:function(){
					
					var title,form = popupForm.getForm();
					form.reset();
					if(!Ext.getCmp('OperatePopupWindow')){
						 Ext.create('OperatePopupWindow').show();
					}else{
						Ext.getCmp('OperatePopupWindow').show();
					}
					switch(commonGridPanel.level){
						case 1://业务项
							createField(i18n('i18n.baseInfo.businessItem'),true,false,false,false,false,i18n('i18n.baseInfo.addBusinessItem'))
							break;
						case 2://业务范围
							createField(i18n('i18n.baseInfo.businessScope'),true,false,false,false,false,i18n('i18n.baseInfo.addBusinessScope'))
							break;
						case 3://业务类型
							createField(i18n('i18n.baseInfo.businessType'),true,true,true,true,false,i18n('i18n.baseInfo.addBusinessType'))
							break;	
						case 4://业务场景
							createField(i18n('i18n.baseInfo.businessScene'),true,false,false,false,false,i18n('i18n.baseInfo.addBusinessScene'))
							break;	
						case 5://场景原因
							createField(i18n('i18n.baseInfo.businessSceneResson'),true,false,false,false,true,i18n('i18n.baseInfo.addBusinessSceneResson'))
							break;	
					}
				}
			},{
				xtype:'button',   
				text:i18n('i18n.baseInfo.update'),
				handler:function(){
					var selection = commonGridPanel.getSelectionModel().getSelection();
					if (selection.length!=1) {
						MessageUtil.showMessage(i18n('i18n.baseInfo.pleaseSelectOne'));
						return false;
					}
					var record=selection[0];
					var title,form = popupForm.getForm();
					form.reset();
					form.findField('id').setValue(record.get('id'));
					 if(!Ext.getCmp('OperatePopupWindow')){
						 Ext.create('OperatePopupWindow').show();
					}else{
						Ext.getCmp('OperatePopupWindow').show();
					}
					switch(commonGridPanel.level){
						case 1://业务项
							form.findField('baseInfo').setValue(record.get('baseInfo'));
							createField(i18n('i18n.baseInfo.businessItem'),true,false,false,false,false,i18n('i18n.baseInfo.updateBusinessItem'))
							break;
						case 2://业务范围
							form.findField('baseInfo').setValue(record.get('baseInfo'));
							createField(i18n('i18n.baseInfo.businessScope'),true,false,false,false,false,i18n('i18n.baseInfo.updateBusinessScope'))
							break;
						case 3://业务类型
							form.findField('baseInfo').setValue(record.get('baseInfo'));
							form.findField('deallan').setValue(record.get('deallan'));
							form.findField('feedbackLimit').setValue(record.get('feedbackLimit'));
							form.findField('procLimit').setValue(record.get('procLimit'));
							createField(i18n('i18n.baseInfo.businessType'),true,true,true,true,false,i18n('i18n.baseInfo.updateBusinessType'))
							break;	
						case 4://业务场景
							form.findField('baseInfo').setValue(record.get('baseInfo'));
							createField(i18n('i18n.baseInfo.businessScene'),true,false,false,false,false,i18n('i18n.baseInfo.updateBusinessScene'))
							break;	
						case 5://场景原因
							form.findField('baseInfo').setValue(record.get('baseInfo'));
							form.findField('procStandard').setValue(record.get('procStandard'));
							createField(i18n('i18n.baseInfo.businessSceneResson'),true,false,false,false,true,i18n('i18n.baseInfo.updateBusinessSceneResson'))
							break;	
					}
				}
			},{
				xtype:'button',   
				text:i18n('i18n.baseInfo.delete'),
				handler:function(){
					var record = commonGridPanel.getSelectionModel().getSelection();
					if (record.length==0) {
						MessageUtil.showMessage(i18n('i18n.baseInfo.pleaseSelectDeleteItem'));
						return false;
					}
					successFn = function(json){
						MessageUtil.showInfoMes(i18n('i18n.baseInfo.deleteSuccess'),function(){
							commonGridPanel.getStore().load({params:{id:commonGridPanel.parentId}});
							var node=treePanel.getStore().getNodeById(commonGridPanel.parentId);
							treePanel.getStore().load({
								       node : node
						   	  });
						});
						
					},
					failureFn = function(json){
						if(json.message){
							MessageUtil.showErrorMes(json.message);	
						}else{
							MessageUtil.showErrorMes(i18n('i18n.baseInfo.fail'));
						}
					};
					var dp=[];
					for(var i=0;i<record.length;i++){
						dp.push(record[i].data);
					}
					var params = {list:dp};
					MessageUtil.showQuestionMes(i18n('i18n.baseInfo.deleteSure'),function(e){
						if(e == 'yes'){
							BaseInfoData.deleteBaseInfo(params, successFn, failureFn );
						}
					});
			}
			}]
		});
	
	//新增/修改表单
	Ext.define('PopupForm',{	
		extend:'NoTitleFormPanel',		
		items:[{
				layout:{
					type:'table',
					columns:1
				},
				defaults:{
					labelWidth:90,
					width:300,
					xtype:'dptextfield'
				},
				items:[{
					fieldLabel :i18n('i18n.baseInfo.businessItem'),
					name : 'baseInfo',
					maxLength : 50
				},{
					fieldLabel :i18n('i18n.baseInfo.deallan'),
					name : 'deallan',
					xtype:'dptextarea',
					maxLength : 200
				},{
					fieldLabel :i18n('i18n.baseInfo.feedbackLimit'),
					xtype:'numberfield',
					minValue:1,
					name:'feedbackLimit',
					maxValue:9999999999
				},{
					fieldLabel :i18n('i18n.baseInfo.procLimit'),
					xtype:'numberfield',
					minValue:1,
					name:'procLimit',
					maxValue:9999999999
				},{
					fieldLabel :i18n('i18n.baseInfo.procStandard'),
					name : 'procStandard',
					xtype:'dptextarea',
					maxLength : 1000
				},{
					name:'id',
					xtype:'hiddenfield'
				}]		
			}]
	});
	var popupForm=Ext.create('PopupForm');		
		
	//弹出的新增/修改窗口
	Ext.define('OperatePopupWindow',{				
		extend:'PopWindow',
		resizable:false,
		id:'OperatePopupWindow',
		fbar:[{
				xtype:'button',
				text:i18n('i18n.baseInfo.sure'),	// 确定
				handler:function(t){
					var form =popupForm.getForm();
					if(!form.isValid()){
						return;
					}; 			
					t.setDisabled(true);
					var params=form.getValues();
					params.level=commonGridPanel.level;
					params.parentId=commonGridPanel.parentId;
					params.classCode=commonGridPanel.classCode;
					params.typeCode=commonGridPanel.typeCode;
					if(Ext.getCmp('OperatePopupWindow').title.substring(0,2)==i18n('i18n.baseInfo.add')){
							 var fnSuccess = function(json){
							  	 t.setDisabled(false);
								 MessageUtil.showInfoMes(i18n('i18n.baseInfo.saveSuccess'),function(){
								 	commonGridPanel.getStore().load({params:{id:commonGridPanel.parentId}});
									var node=treePanel.getStore().getNodeById(commonGridPanel.parentId);
								 	treePanel.getStore().load({
								       node : node
							   	   });
								 });	
								 Ext.getCmp('OperatePopupWindow').hide();
								 
							},
							fnFailure = function(json){
								t.setDisabled(false);
								if(json.message){
									MessageUtil.showErrorMes(json.message);	
								}else{
									MessageUtil.showErrorMes(i18n('i18n.baseInfo.fail'));	
								}
							};
							BaseInfoData.addBaseInfo({baseInfo:params},fnSuccess,fnFailure);
					}else{
							var fnSuccess = function(json){
								t.setDisabled(false);
								 MessageUtil.showInfoMes(i18n('i18n.baseInfo.saveSuccess'),function(){
							 		 commonGridPanel.getStore().load({params:{id:commonGridPanel.parentId}});
									 treePanel.getStore().load({
								       node : treePanel.getStore().getNodeById(commonGridPanel.parentId)
							   	 	 });
								 });	
								 Ext.getCmp('OperatePopupWindow').hide();
							},
							fnFailure = function(json){
								t.setDisabled(false);
								if(json.message){
									MessageUtil.showErrorMes(json.message);	
								}else{
									MessageUtil.showErrorMes(i18n('i18n.baseInfo.fail'));	
								}
							};
							BaseInfoData.updateBaseInfo({baseInfo:params},fnSuccess,fnFailure);
					}
				}
			},{
				xtype:'button',
				text:i18n('i18n.baseInfo.cancle'),	// 取消
				handler:function(){
					Ext.getCmp('OperatePopupWindow').hide();
			}
		}],
		items:[popupForm],
		closeAction:'hide',
		layout: 'fit'
	});

	var commonGridPanel= Ext.create("CommonGridPanel",{flex: 1});
	
	var treePanel= Ext.create("TreeBaseInfoPanel");
	/**
	 *将界面显示到viewport中 
	 */
	Ext.create('Ext.container.Viewport',{
		layout: {
	        align: 'stretch',type: 'hbox'
	    },
        items: [
            {
                xtype: 'panel',
                width: 240,
                layout:'fit',
                items: [
                      treePanel
                ]
            },{
                xtype: 'panel',
                flex:1,
                border:false,
                layout: {
                    align: 'stretch',type: 'vbox'
                },
                items: [
                      Ext.create("TreeSearchForm",{height:30}),
                      commonGridPanel
                ]
            }
                 
        ]
	});
});