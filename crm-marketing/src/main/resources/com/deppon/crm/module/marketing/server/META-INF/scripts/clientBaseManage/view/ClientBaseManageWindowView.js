//线路部门树数据
var lineDeptTree=null;



/**
 * 客户群新增window
 */
Ext.define('ClientBaseModifyAndDetailWindow',{
	extend:'PopWindow',
	alias : 'widget.basicwindow',
	width:805,
	height:650,
	modal:true,
	lineType:'LEAVE_ARRIVE',//由于线路类型是两个控件 用这个属性来判断线路
	title:i18n('i18n.ClientBaseManegerView.addClientBase'),
	closeAction:'hide',
	items:null,
	layout : 'border',
	getItems:function(){
	 var me=this;
	 return [
	 	Ext.create('ClientBaseModifyAndDetailUpPanel',{  region:'north'}),
	 	{
              	//区域线路要求
                title : i18n('i18n.ClientBaseManegerView.regionLineRequset'),
                layout : 'border',
                region : 'center',
                items : [Ext.create('LineTreeForClientBase',{region:'west'}),{
                    region: 'center',
                    layout:{
                        type:'vbox',
                        align:'stretch'
                    },
                    items : [Ext.create('ClientBaseLineTreeGrid'),Ext.create('ClientBaseLineGrid')]
                }]
            }
	 	];
	},   
	listeners:{
		hide:function(){
			resetClientBaseWindow()
            document.body.scrollLeft=0;
            document.body.scrollTop=0;
            document.getElementsByTagName("html")[0].style.overflowY="hidden";
            document.getElementsByTagName("html")[0].style.overflowX="hidden";
			Ext.getCmp('clientBaseModifyAndDetailWindowId').down('button[name=save]').show();
			Ext.getCmp('clientBaseModifyAndDetailWindowId').down('button[name=save]').enable();
            Ext.getCmp('clientBaseModifyAndDetailWindowId').down('button[name=reset]').show();
            Ext.getCmp('clientBaseModifyAndDetailWindowId').down('button[name=cancel]').setText(i18n('i18n.memberView.cancel'));//取消
		},
		show:function(){
			if(myMask!=null){
				myMask.show();}
			Ext.getCmp('clientBaseLineTreeGridId').hide();
			Ext.getCmp('lineTreeForClientBaseId').hide();
		    Ext.getCmp('clientBaseLineGridId').show();
		}
		
	},getFbar:function(){
		var me = this;
		return [{
			xtype:'button',
			name:'save',
			text:i18n('i18n.SearchMember.determine'),//确定
			scope:me,
			handler:me.commitBo
		},{
			xtype:'button',
			name:'reset',
			text:i18n('i18n.MemberCustEditView.reset'),//重置
			scope:me,
			handler:me.resetBo
		},{
			xtype:'button',
			name:'cancel',
			text:i18n('i18n.memberView.cancel'),//取消
			handler:function(){
				me.hide();
			}
		}];
	},initComponent:function(){	
		var me=this;
		me.items = me.getItems();
		me.buttons = me.getFbar();
		this.callParent();
	},
	//重置客户群信息
	resetBo:function(){
			//标记是否改变
    	isChange=1;
		Ext.getCmp('ClientBaseModifyAndDetailUpPanelId').getForm().reset();
		Ext.getCmp('ClientBaseModifyAndDetailUpPanelId').getForm().reset();
		Ext.getCmp('clientBaseLineGridId').store.removeAll();
		//重置二级行业
		Ext.getCmp('secondTradesId').store.removeAll();
		//重置gird
		resetTitle();
		//重置两个combo
		Ext.getCmp('downBeginDeptOrCityCombox').reset()
		Ext.getCmp('downEndDeptOrCityCombox').reset()
		//重置树区域
		Ext.getCmp("clientBaseLineTreeGridId").getStore().removeAll();
		//去掉树的勾选项
		Ext.getCmp('lineTreeForClientBaseId').getRootNode( ).cascadeBy(function(node){
									node.set('checked', false);
								})
		//收起树
		Ext.getCmp('lineTreeForClientBaseId').collapseAll();
	},
	//提交客户群信息
	commitBo:function(){
		var me=this;
		var clientBaseUpPanel=Ext.getCmp('ClientBaseModifyAndDetailUpPanelId').getForm();
		var clientBaseName=clientBaseUpPanel.findField('clientBaseName').getValue();
		var startTime=clientBaseUpPanel.findField('clientCreateStartTime').getValue();
		var endTime=clientBaseUpPanel.findField('clientCreateEndTime').getValue();
		//多选项赋值
		var clientBaseInfo = new ClientBaseInfoModel(); 
		Ext.getCmp('ClientBaseModifyAndDetailUpPanelId').getForm().updateRecord(clientBaseInfo);
		//判断选择是线路还是 区域 进行GRIL的 赋值
		var param=new Array();
		//线路grid
		var clientBaseLineGrid=Ext.getCmp('clientBaseLineGridId');
		//区域树grid
		var ClientBaseLineTreeGrid=Ext.getCmp('clientBaseLineTreeGridId')
		var lineType= Ext.getCmp('clientBaseModifyAndDetailWindowId').lineType;
		clientBaseInfo.set('lineType',lineType)
		if(lineType=='LEAVE_ARRIVE'){
			clientBaseLineGrid.store.each(function(record){
				param.push(record.data)	
			});
		}else{
			ClientBaseLineTreeGrid.store.each(function(record){
				param.push(record.data)
			});
		}	
		clientBaseInfo.set('lineDept',param);
		//如果是修改操作本条客户群名称保存时不做重复验证
		if(me.flag=='modify'){
			clientBaseInfo.set('clientBaseName',clientBaseName)
			var id=Ext.getCmp('ClientBaseInfoListGridId').getSelectionModel().getSelection()[0].get('id')
			clientBaseInfo.set('id',id)
		
		}
		clientBaseInfo.commit();
		 if(!clientBaseUpPanel.isValid()){
          	return false;
                        };
		if(Ext.isEmpty(clientBaseName)){
			 MessageUtil.showMessage(i18n('i18n.ClientBaseManeger.clientBaseNameIsNull'));//客户群名称不能为空
			return false;
		}
		if(clientBaseName.length>40){
			 MessageUtil.showMessage(i18n('i18n.ClientBaseManeger.clientBaseNameLengthThen40'));//客户群名称长度不能大于40  
			return false;
		}
		//不能一个为空
		if(Ext.isEmpty(startTime)^Ext.isEmpty(endTime)){
			MessageUtil.showMessage(i18n('i18n.ClientBaseManeger.clientBaseTimeOneNull'));//客户创建的起始时间或结束时间不能为空！
			return false;
		}
		if(dateRangeNotValid(startTime,endTime)){
			MessageUtil.showMessage(i18n('i18n.ClientBaseManeger.startMoreThanEndTime'));//开始时间不能大于结束时间
			return false;
		}
		//验证通过后显示LoadMask
		var myMask = new Ext.LoadMask(Ext.getBody(), {msg:i18n('i18n.ClientBaseManeger.message.loadingWait')});//正在处理请稍后.....
		myMask.show();
		var successFn =function(json){
			myMask.hide()
			if(me.flag=='add'){
				MessageUtil.showInfoMes(i18n('i18n.ClientBaseManeger.message.clientBaseAddSuccess'));//客户群新增成功
			}else{
				MessageUtil.showInfoMes(i18n('i18n.ClientBaseManeger.message.clientBaseUpdateSuccess'));//客户群更新成功
			}
			Ext.getCmp('ClientBaseInfoListGridId').down('pagingtoolbar').moveFirst()
			me.down('button[name=save]').enable();
			me.hide();
			Ext.getCmp('clientBaseLineGridId').store.removeAll();
			  
		};
		var failureFn = function(json){
			myMask.hide()
			MessageUtil.showErrorMes(json.message);
			me.down('button[name=save]').enable();
			return false;
		};
		var param = {'clientBase':clientBaseInfo.data,'isChange':isChange};
		me.down('button[name=save]').disable();
		if(me.flag=='add'){
			ClientBaseStore.prototype.saveClientBase(param,successFn,failureFn);
		}else {
			ClientBaseStore.prototype.updateClientBase(param,successFn,failureFn);
		}
	}
});



/**
 * 线路区域Grid
 */
Ext.define('ClientBaseLineGrid',{
    extend : 'SearchGridPanel',
    id:'clientBaseLineGridId',
    autoScroll : true,
    flex:1,
    selModel:Ext.create('Ext.selection.CheckboxModel',{mode:'SINGLE' ,allowDeselect:true}),
    store:Ext.create('UseWalkGoodsLineStore',{
    	 listeners:{
    	 	datachanged:function(store,eOpts){
    	 		Ext.getCmp('clientBaseLineGridId').columns[1].setText(i18n('i18n.ClientBaseManegerView.leaveRegion')+'('+store.getCount()+')');
    	 		Ext.getCmp('clientBaseLineGridId').columns[2].setText(i18n('i18n.ClientBaseManegerView.arriveRegion')+'('+store.getCount()+')')
    	 }}
    }),
    columns: [
//       { xtype:'rownumberer',width:40,header:'序号'},  
    //出发区域
       { header: i18n('i18n.ClientBaseManegerView.leaveRegion')+"(0)", dataIndex: 'leavedDeptName',sortable:false, flex: 1},
    //到达区域
       { header: i18n('i18n.ClientBaseManegerView.arriveRegion')+"(0)", dataIndex: 'arriveDeptName',sortable:false, flex: 1 }
    ],
    getTbar : function(){
            var me = this;
            return [{
            //	线路要求
            fieldLabel : i18n('i18n.ClientBaseManegerView.LineRequset'),
            id : 'lineGridSelectId',
            width:165,
            labelWidth:70,
            xtype : 'combo',
            editable:false,
            store:getDataDictionaryByName(DataDictionary,'LINE_TYPE'),
            queryMode:'local',
            displayField:'codeDesc',
            valueField:'code',
            value : 'LEAVE_ARRIVE',
            forceSelection :true,
            listeners:{
                change:DButil.comboSelsct,
                beforeselect:function(t){
                    oldValueUp=t.getValue();
                },
                select : function(t){
                    var me = this;
                    var oldValue = oldValueUp;
                    var newValue = t.getValue();
                    if(Ext.getCmp("clientBaseLineGridId").getStore().getCount()===0){
	                	exchangeLine(me)
                    }else{
                        t.setValue(oldValue);
                        //改变线路会清空数据,是否确认？
                        MessageUtil.showQuestionMes(i18n('i18n.ClientBaseManeger.message.willBeClean'), function(e){ 
                            if (e == 'yes') {
                               t.setValue(newValue);
                               Ext.getCmp("clientBaseLineGridId").getStore().removeAll();
                               exchangeLine(me)
                            }
                        });
                    }
                }
            }
        },{
            fieldLabel : i18n('i18n.ClientBaseManegerView.from'),//从
            width:125,
            labelWidth:20,
            xtype:'combobox',
            id:'downBeginDeptOrCityCombox',
            maxLength:50,
            store:Ext.create('WalkGoodDeptStore',{
                listeners:{
                    beforeload:function(store, operation, eOpts){
                    	if(!Ext.getCmp("downBeginDeptOrCityCombox").isValid()){ 
                            return false;
                        }
                        Ext.apply(operation,{
                            params : {
                                    'deptName':Ext.getCmp("downBeginDeptOrCityCombox").getRawValue()
                                }
                            }
                        );  
                    }
                }
            }),
            triggerAction : 'all',
            displayField:'deptName',
            valueField:'standardCode',
            forceSelection :true,
            hideTrigger:false,
            pageSize: 10,
            oldValue:null,
            minChars:2,
            queryDelay:800,
            listConfig: {
                minWidth :300,
                getInnerTpl: function() {
                     return '{deptName}';
                }
            },
            listeners:{
            	expand:function(combo){
				    	if(Ext.isEmpty(combo.getValue())){
				    		combo.getStore().load();
				    	}	
				    },
                change:function(combo){
                	Ext.getCmp("downBeginDeptOrCityCombox").expand();
                    if(Ext.isEmpty(combo.getValue())){
                        combo.setValue("");
                    }
                }
            }
        },{
            fieldLabel : i18n('i18n.ClientBaseManegerView.reach'),//到
            xtype:'combobox',
            id : 'downEndDeptOrCityCombox',
            width:125,
            labelWidth:20,
            maxLength:50,
            store:Ext.create('WalkGoodDeptStore',{
                listeners:{
                    beforeload:function(store, operation, eOpts){
                    	if(!Ext.getCmp("downEndDeptOrCityCombox").isValid()){ 
                            return false;
                        }
                        Ext.apply(operation,{
                            params : {
                                    'deptName':Ext.getCmp("downEndDeptOrCityCombox").getRawValue()
                                }
                            }
                        );  
                    }
                }
            }),
            triggerAction : 'all',
            displayField:'deptName',
            valueField:'standardCode',
            forceSelection :true,
            hideTrigger:false,
            pageSize: 10,
            minChars:2,
            queryDelay:800,
            listConfig: {
                minWidth :300,
                getInnerTpl: function() {
                     return '{deptName}';
                }
            },
            listeners:{
            	  expand:function(combo){
				    	if(Ext.isEmpty(combo.getValue())){
				    		combo.getStore().load();
				    	}	
				    },
                change:function(combo){
                	Ext.getCmp("downEndDeptOrCityCombox").expand();
                    if(Ext.isEmpty(combo.getValue())){
                        combo.setValue("");
                    }
                }
            }
        },{
            xtype:'button',
            text:i18n('i18n.ClientBaseManegerView.append'),
            handler:function(){
                var grid = Ext.getCmp('clientBaseLineGridId');
                var beginDept = Ext.getCmp("downBeginDeptOrCityCombox");
                var endDept = Ext.getCmp("downEndDeptOrCityCombox");
                //出发外场id
                var leaveDeptCode = beginDept.getValue() == null ? '' : beginDept.getValue();
                //出发外场名称
                var leavedDeptName = beginDept.getRawValue();
                //到达外场id
                var arriveDeptCode = endDept.getValue() == null ? '' : endDept.getValue();
                //到达外场名称
                var arriveDeptName = endDept.getRawValue();
                
                //如果都为空
                if(Ext.isEmpty(leaveDeptCode) && Ext.isEmpty(arriveDeptCode)){
                  MessageUtil.showMessage(i18n('i18n.ClientBaseManeger.message.bothNoNull'));//都不可为空
                  return false;
                }
                //是否重复添加
                var isSame = false;
                //遍历是否有相同的线路
                grid.store.each(function(obj){
                    if(obj.get("leaveDeptCode") == leaveDeptCode && 
                       obj.get("arriveDeptCode") == arriveDeptCode){
                      //相同的线路给出提示
                       MessageUtil.showMessage(i18n('i18n.ClientBaseManeger.message.lineExist'));//该线路已添加
                       isSame=true;
                  }
                });
                //如果有相同的线路返回
                if(isSame){
                    return false;
                }
                //标记是否改变
                isChange=1;
                if(grid.getStore().getCount()>=100){
	                    MessageUtil.showMessage(i18n('i18n.ClientBaseManeger.lineDeptMoreThan100'));//部门最多添加100条！
	                    return false;
                		}
                var lineDeptModel = new LineDeptModel();
                lineDeptModel.set('leavedDeptName',leavedDeptName);
                lineDeptModel.set('leaveDeptCode',leaveDeptCode);
                lineDeptModel.set('arriveDeptName',arriveDeptName);
                lineDeptModel.set('arriveDeptCode',arriveDeptCode);
                lineDeptModel.commit();
                grid.store.add(lineDeptModel);
                beginDept.reset();
                endDept.reset();
            }
        },{
            xtype:'button',
            text: i18n('i18n.ClientBaseManegerView.delete'),//删除
            name : 'delBtn',
            handler:function(){
                var lineGrid = Ext.getCmp('clientBaseLineGridId');
                var selection = lineGrid.getSelectionModel().getSelection();
                if(selection.length != 1){
                    MessageUtil.showMessage(i18n('i18n.ClientBaseManeger.message.onlyChoiceOne'));//请选择一条操作记录!
                    return false;
                };
                MessageUtil.showQuestionMes(i18n('i18n.MarketActivityManagerView.deleteThisSure'), function(e){ //您确定要删除吗？
                    if (e == 'yes') {
                    	//标记是否改变
                		isChange=1;
                        lineGrid.getStore().remove(selection);
                        MessageUtil.showInfoMes(i18n('i18n.MarketActivityManagerView.deleteSuccess'));  //删除成功
                    }
                });
            }
        }];
    },
    initComponent:function(){
        var me = this;
        me.tbar = me.getTbar();
        this.callParent();
    } 
});
/**
 * 线路树展示Grid
 */
Ext.define('ClientBaseLineTreeGrid',{
    extend : 'SearchGridPanel',
    id:'clientBaseLineTreeGridId',
    autoScroll : true,
    flex:1,
    selModel:Ext.create('Ext.selection.RowModel'),
    store:Ext.create('UseWalkGoodsLineStore',{
    	 listeners:{
    	 	datachanged:function(store,eOpts){
    	 		Ext.getCmp('clientBaseLineTreeGridId').columns[0].setText(i18n('i18n.ClientBaseManegerView.leaveRegion')+'('+store.getCount()+')');
    	 		Ext.getCmp('clientBaseLineTreeGridId').columns[1].setText(i18n('i18n.ClientBaseManegerView.arriveRegion')+'('+store.getCount()+')')
    	 }}
    }),
    columns: [
//       { xtype:'rownumberer',width:40,header:'序号'},
    	//出发区域
       { header: i18n('i18n.ClientBaseManegerView.leaveRegion')+"(0)", dataIndex: 'leavedDeptName',sortable:false, flex: 1},
       //到达区域
       { header: i18n('i18n.ClientBaseManegerView.arriveRegion')+"(0)", dataIndex: 'arriveDeptName',sortable:false, flex: 1 }
    ],
    initComponent:function(){
        var me = this;
        me.hide();
        this.callParent();
    } 
})
/**
 * 线路部门
 */
Ext.define('LineTreeForClientBase',{
    extend : 'Ext.tree.Panel',
    id:'lineTreeForClientBaseId',
    //指定树的宽度
    width:320,
    height:300,
    //绑定一个store
	store:  Ext.create('DeptTreeStore',{
		model:'LineAreaTreeModel',
        proxy: {
                //内存方式
                type:'memory',
                //绑定数据
                data:lineDeptTree
        }
    }),
    //自动增加滚动条
    autoScroll:true,
    //动画效果
    animate:true,
    //使用vista风格的箭头图标，默认为false
    useArrows: true,
    //树节点是否可见
    rootVisible: false,
    //树对象增加一个边框
    frame: true,
    //定义树的标题
//    title: '线路要求',
    listeners:{
          checkchange:function(node,checked){
          	//标记是否改变
            isChange=1;
          	if(this.up('window').lineType=="LEAVE"){
	          	  if (checked) {
	          	  	if(Ext.getCmp("clientBaseLineTreeGridId").getStore().getCount()>=100){
	                    MessageUtil.showMessage(i18n('i18n.ClientBaseManeger.lineDeptMoreThan100'));
	                    node.set('checked', false);
	                    return false;
                		}
	                 Ext.getCmp('clientBaseLineTreeGridId').getStore().add({'leavedDeptName':node.data.text,'leaveDeptCode':node.raw.deptStandardCode});
	             }else {
	                    var index = Ext.getCmp('clientBaseLineTreeGridId').getStore().find('leaveDeptCode',node.raw.deptStandardCode,0,false,true,true);
	                    Ext.getCmp('clientBaseLineTreeGridId').getStore().removeAt(index);
	             }
	          		
          	}else{
          		  if (checked) {
          		  		if(Ext.getCmp("clientBaseLineTreeGridId").getStore().getCount()>=100){
		                    MessageUtil.showMessage(i18n('i18n.ClientBaseManeger.lineDeptMoreThan100'));
		                    node.set('checked', false);
		                    return false;
                		}
	                    Ext.getCmp('clientBaseLineTreeGridId').getStore().add({'arriveDeptName':node.data.text,'arriveDeptCode':node.raw.deptStandardCode});
	             }else {
	                    var index = Ext.getCmp('clientBaseLineTreeGridId').getStore().find('arriveDeptCode',node.raw.deptStandardCode,0,false,true,true);
	                    Ext.getCmp('clientBaseLineTreeGridId').getStore().removeAt(index);
	             }	
          		
          	}
            
        },
        scrollershow: function(scroller) {
    		if (scroller && scroller.scrollEl) {
    				scroller.clearManagedListeners(); 
    				scroller.mon(scroller.scrollEl, 'scroll', scroller.onElScroll, scroller); 
    		}
    	}

    },getTbar : function(){
            var me = this;
            return [{
            fieldLabel : i18n('i18n.ClientBaseManegerView.LineRequset'),//线路要求
            id : 'lineTreeSelectId',
            width:165,
            labelWidth:70,
            xtype : 'combo',
            store:getDataDictionaryByName(DataDictionary,'LINE_TYPE'),
            queryMode:'local',
            displayField:'codeDesc',
            valueField:'code',
            editable:false,
            forceSelection :true,
            listeners:{
                change:DButil.comboSelsct,
                beforeselect:function(t){
                    oldValueUp=t.getValue();
                },
                select : function(t){
                    var me = this;
                    var oldValue = oldValueUp;
                    var newValue = t.getValue();
                    if(Ext.getCmp("clientBaseLineTreeGridId").getStore().getCount()===0){
                	 	exchangeRegion(me)
                    }else{
                        t.setValue(oldValue);
                        //改变线路会清空数据,是否确认？
                        MessageUtil.showQuestionMes(i18n('i18n.ClientBaseManeger.message.willBeClean'), function(e){ 
                            if (e == 'yes') {
                               t.setValue(newValue);
                               Ext.getCmp("clientBaseLineTreeGridId").getStore().removeAll();
                               //获取到树并清空所有的勾选项
                               Ext.getCmp('lineTreeForClientBaseId').getRootNode( ).cascadeBy(function(node){
									node.set('checked', false);
								})
							   exchangeRegion(me)
                            }
                        });
                    }
                }
            }
        }];
    },
    initComponent:function(){
        var me = this;
        me.tbar = me.getTbar();
        me.hide();
        this.callParent();
    } 
});
/**
 * 多选项
 * 
 */
Ext.define('ClientBaseModifyAndDetailUpPanel',{
	extend:'BasicFormPanel',
	id:'ClientBaseModifyAndDetailUpPanelId',
	items:null,
	height:175,
	border:2,
	layout:{
		type : 'table',
		columns : 3
	},
	defaults:{
		labelWidth:60,
		width : 250,
		labelAlign:'right',
		margin:'10 0 0 5'
	},
	initComponent:function(){			
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return[{
					xtype:'textfield',
			    	fieldLabel :'<span style="color:red;font-weight:bold" data-qtip="Required">*</span>'+ i18n('i18n.ClientBaseManegerView.clentBaseName'),//客户群名称
			    	labelWidth:74,
			    	width : 252,
					name:'clientBaseName',
					allowBlank:false,
					blankText : i18n('i18n.ClientBaseManeger.clientBaseNameIsNull'),
        			maxLength : 40,
					listeners:{
					blur:function(){
						if(this.up('window').flag!='detail'){
						var clientBaseName=	Ext.getCmp('ClientBaseModifyAndDetailUpPanelId').getForm().findField('clientBaseName').getValue();
						if(this.up('window').flag=='modify'){
							var id=Ext.getCmp('ClientBaseInfoListGridId').getSelectionModel().getSelection()[0].get('id');	
								var successFn = function(json){
								if(json.checkRepeate){
									 MessageUtil.showMessage( i18n('i18n.ClientBaseManeger.clientBaseNameRepeat'));//客户群名称重复
									 Ext.getCmp('ClientBaseModifyAndDetailUpPanelId').getForm().findField('clientBaseName').reset();
									 return false;
								}
								}
								var failureFn = function(json){
									MessageUtil.showErrorMes(json.message);
									return false;
								}
								 ClientBaseStore.prototype.checkClientBase({'clientBase.clientBaseName':clientBaseName,'clientBase.id':id},successFn,failureFn)
								
					}else{
						var successFn = function(json){
							if(json.checkRepeate){
								  MessageUtil.showMessage(i18n('i18n.ClientBaseManeger.clientBaseNameRepeat'));//客户群名称重复
								  Ext.getCmp('ClientBaseModifyAndDetailUpPanelId').getForm().findField('clientBaseName').reset();
								  return false;
							}
						}
						var failureFn = function(json){
							MessageUtil.showErrorMes(json.message);
							return false;
						}
						 ClientBaseStore.prototype.checkClientBase({'clientBase.clientBaseName':clientBaseName},successFn,failureFn)
							}
						}	
					},
					change:function(combo){
						if(combo.isValid()){
								change(combo)
						}else{
							Ext.QuickTips.destroy();  
						}	
						}
					}
					
				},
				{
					xtype:'combobox',
			    	fieldLabel:i18n('i18n.PotentialCustManagerView.custType'),//客户类型
			    	editable:false,
			    	//剔出朦层
	    			listConfig: {
	      		        loadMask:false
	    			},
			    	name:'clientTypes',
					queryModel:'local',
					store:getDataDictionaryByName(DataDictionary,'CUST_TYPE'),
					displayField:'codeDesc',
					valueField:'code',
					forceSelection :true,
					multiSelect:true,
					listeners:{
						change:function(combo){
							change(combo)
							if(Ext.Array.contains(combo.getValue(),'RC_CUSTOMER')){
								 Ext.getCmp('clientGradesId').enable();
							}else{
								Ext.getCmp('clientGradesId').reset();
								Ext.getCmp('clientGradesId').disable();
							}
						} 
					}
				},{
					xtype:'combo',
			    	fieldLabel:i18n('i18n.PotentialCustManagerView.memberGrade'),//客户等级
			    	editable:false,
			    	id:'clientGradesId',
			    	//剔出朦层
	    			listConfig: {
	      		        loadMask:false
	    			},
			    	name:'clientGrades',
					queryModel:'local',
					store:getDataDictionaryByName(DataDictionary,'MEMBER_GRADE'),
					displayField:'codeDesc',
					valueField:'code',
					forceSelection :true,
					multiSelect:true,
					listeners:{
						change:function(combo){
							change(combo)
						} 
					}	
				},
				{
					xtype:'combo',
			    	fieldLabel:i18n('i18n.PotentialCustManagerView.firstTrade'),//一级行业
			    	editable:false,
			    	//剔出朦层
	    			listConfig: {
	      		        loadMask:false
	    			},
			    	name:'trades',
					queryModel:'local',
					store:getDataDictionaryByName(DataDictionary,'TRADE'),
					displayField:'codeDesc',
					valueField:'code',
					forceSelection :true,
					multiSelect:true,
					listeners:{
						change:function(combo){
							change(combo)
							if(combo.up("window").flag!="detail"){
								 secondTrade = Ext.getCmp('ClientBaseModifyAndDetailUpPanelId').down('combo[name=secondTrades]');
		                    	 secondTrade.reset();
		                   		 secondTrade.getStore().load({  
			                        params: {  
			                            'tradesList' : combo.getValue()  
			                        }  
			                    });       
							} 			
						}
					}

				},
				{
					//客户行业 二级
				    xtype : 'combo',
				    fieldLabel:i18n('i18n.PotentialCustManagerView.secondTrade'),
				    name : 'secondTrades',
				    store: Ext.create('SecondTradeStore'),
				    id:'secondTradesId',
				    queryMode:'local',
				    multiSelect:true,
				    displayField:'codeDesc',
				    editable: false,
				    valueField:'code',
				    listConfig: {
				        loadMask:false
				    },
				    triggerAction: 'all',
				    forceSelection :true,
				    listeners: {
				       change:function(combo){
							change(combo)
						} 
				   }
				},
				{
					xtype:'combo',
			    	fieldLabel:i18n('i18n.PotentialCustManagerView.goodsPotential'),//货量潜力
			    	editable:false,
			    	colspan:3,
			    	//剔出朦层
	    			listConfig: {
	      		        loadMask:false
	    			},
			    	name:'clientlatents',
					queryModel:'local',
					store:getDataDictionaryByName(DataDictionary,'CARGO_POTENTIAL'),
					displayField:'codeDesc',
					valueField:'code',
					forceSelection :true,
					multiSelect:true,
					listeners:{
						change:function(combo){
							change(combo)
						} 
					}	
				},{
					xtype:'combo',
			    	fieldLabel:i18n('i18n.ScatterCustManagerView.custAttribute'),//客户属性
			    	editable:false,
			    	//剔出朦层
	    			listConfig: {
	      		        loadMask:false
	    			},
			    	name:'clientPropertys',
					queryModel:'local',
					store:getDataDictionaryByName(DataDictionary,'CUSTOMER_NATURE'),
					displayField:'codeDesc',
					valueField:'code',
					forceSelection :true,
					multiSelect:true,
					listeners:{
						change:function(combo){
							change(combo)
						} 
					}	
				},
				{
					xtype:'combo',
			    	fieldLabel:i18n('i18n.PotentialCustManagerView.custSource'),//客户来源
			    	editable:false,
			    	//剔出朦层
	    			listConfig: {
	      		        loadMask:false
	    			},
			    	name:'clientSources',
					queryModel:'local',
					store:getDataDictionaryByName(DataDictionary,'CUST_SOURCE'),
					displayField:'codeDesc',
					valueField:'code',
					forceSelection :true,
					multiSelect:true,
					listeners:{
						change:function(combo){
							change(combo)
						} 
					}	
				},
				{
					xtype:'combo',
			    	fieldLabel:i18n('i18n.ClientBaseManegerView.productTypes'),//产品类型
			    	editable:false,
			    	//剔出朦层
	    			listConfig: {
	      		        loadMask:false
	    			},
			    	name:'productTypes',
					queryModel:'local',
					store:getDataDictionaryByName(DataDictionary,'CLIENTBASE_PRODUCT_TYPE'),
					displayField:'codeDesc',
					valueField:'code',
					forceSelection :true,
					multiSelect:true,
					listeners:{
						change:function(combo){
							change(combo)
						} 
					}	
				},
				{
					xtype:'combo',
			    	fieldLabel:i18n('i18n.ClientBaseManegerView.takeMethods'),//提货方式
			    	editable:false,
			    	//剔出朦层
	    			listConfig: {
	      		        loadMask:false
	    			},
			    	name:'takeMethods',
					queryModel:'local',
					store:getDataDictionaryByName(DataDictionary,'CLIENTBASE_PICKUPGOODS'),
					displayField:'codeDesc',
					valueField:'code',
					forceSelection :true,
					multiSelect:true,
					listeners:{
						change:function(combo){
							change(combo)
						} 
					}	
				},
				{
					xtype:'combo',
			    	fieldLabel:i18n('i18n.developSchedule.cooperatePurpose'),//合作意向
			    	editable:false,
			    	//剔出朦层
	    			listConfig: {
	      		        loadMask:false
	    			},
	    			colspan: 2,
			    	name:'cooperateWills',
					queryModel:'local',
					store:getDataDictionaryByName(DataDictionary,'COOPERATION_INTENTION'),
					displayField:'codeDesc',
					valueField:'code',
					forceSelection :true,
					multiSelect:true,
					listeners:{
						change:function(combo){
							change(combo)
						} 
					}	
				},
				{
					fieldLabel: i18n('i18n.ClientBaseManegerView.clientCreateTime'),//客户创建时间
					labelWidth:80,
		            xtype:'datefield',
		            editable:false,
		            maxValue:new Date(),
		            format: 'Y-m-d',
		            name:'clientCreateStartTime'
	    			
				},
				{
					fieldLabel: '——',
		            xtype:'datefield',
		            labelSeparator : '',
		            labelWidth:30,
		            editable:false,
		            maxValue:new Date(),
		            format: 'Y-m-d',
		            name:'clientCreateEndTime'  
				}
				
				]
	}

});
/**
 * 发到货区域切换方法
 * @param {} me
 */
function exchangeRegion(me){
	resetTitle();
	//标记是否改变
    isChange=1;
	//防止两个控件造成的线路类型不统一
	Ext.getCmp('clientBaseModifyAndDetailWindowId').lineType=me.getValue();
	if(me.getValue() == 'LEAVE_ARRIVE'){
		Ext.getCmp('lineTreeForClientBaseId').hide();
		Ext.getCmp('clientBaseLineTreeGridId').hide();
		Ext.getCmp('clientBaseLineGridId').show();
		Ext.getCmp('lineGridSelectId').setValue(me.getValue());
	}else{
		if(me.getValue()=='ARRIVE'){
	            		Ext.getCmp('clientBaseLineTreeGridId').columns[0].hide();
	            		Ext.getCmp('clientBaseLineTreeGridId').columns[1].show()
	            	}else{
	            		Ext.getCmp('clientBaseLineTreeGridId').columns[0].show()
	            		Ext.getCmp('clientBaseLineTreeGridId').columns[1].hide();  
	            	}
	}
	//收起所有选项
	Ext.getCmp('lineTreeForClientBaseId').collapseAll();
}

function exchangeLine(me){
	//标记是否改变
	resetTitle();
    isChange=1;
	Ext.getCmp('clientBaseModifyAndDetailWindowId').lineType=me.getValue();
    if(me.getValue() != 'LEAVE_ARRIVE'){
        Ext.getCmp('clientBaseLineGridId').hide();
        Ext.getCmp('lineTreeForClientBaseId').show();
        Ext.getCmp('clientBaseLineTreeGridId').show();
        Ext.getCmp('lineTreeSelectId').setValue(me.getValue());
        if(me.getValue()=='ARRIVE'){
	            		Ext.getCmp('clientBaseLineTreeGridId').columns[0].hide();
	            		Ext.getCmp('clientBaseLineTreeGridId').columns[1].show()
	            	}else{
	            		Ext.getCmp('clientBaseLineTreeGridId').columns[0].show()
	            		Ext.getCmp('clientBaseLineTreeGridId').columns[1].hide();  
	            	}
    }
}
function change(combo){
			if(Ext.isEmpty(combo.getValue())){
				combo.setValue("");
		}
			if(combo.getValue()!=""){
			Ext.QuickTips.init();  
            Ext.QuickTips.register({ 
                target : combo.el, 
                text : combo.getRawValue()
            	}) 
			}else{
				if(Ext.QuickTips.isEnabled()){
					Ext.QuickTips.unregister(combo.el)
				}
            	 
			}
	}			   
function resetTitle(){
	Ext.getCmp('clientBaseLineTreeGridId').columns[0].setText(i18n('i18n.ClientBaseManegerView.leaveRegion')+"(0)");
    Ext.getCmp('clientBaseLineTreeGridId').columns[1].setText(i18n('i18n.ClientBaseManegerView.arriveRegion')+"(0)");
    Ext.getCmp('clientBaseLineGridId').columns[1].setText(i18n('i18n.ClientBaseManegerView.leaveRegion')+"(0)");
    Ext.getCmp('clientBaseLineGridId').columns[2].setText(i18n('i18n.ClientBaseManegerView.arriveRegion')+"(0)");
}