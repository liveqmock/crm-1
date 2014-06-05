var positionToVPData  = new PositionToVPData();
/**
 * 查询条件面板
 * 2013-11-28
 */
Ext.define('PositionToVPResearchPanel',{
    extend:'SearchFormPanel',
    layout : {
        type : 'table',
        columns : 8
    },
    defaultType : 'textfield',
    defaults : {
        margin:'0 5 0 0',
        labelWidth : 80,
        labelAlign: 'right',
        width : 120
    },
    getItems:function(){
        var me = this;
        return [{//虚拟职位名称输入框
            fieldLabel:i18n('i18n.positionToVP.vpositionName'),//虚拟职位名称
            name : 'vpositionSearchField',
            width:240,
            colspan:2
        },{
            xtype:'button',
            text:i18n('i18n.positionToVP.search'),//查询
            width:60,
            colspan:2,
            //虚拟职位名称查询
            handler : function(){
                var me = this;
                //传递参数为虚拟职位名称
                var virtualPositionName = Ext.String.trim(me.up('panel').down('textfield[name=vpositionSearchField]').getValue());
                var param = {
                    'virtualPositionName' : virtualPositionName
                }
                var store = me.up('viewport').down('panel[name=vpositionGridPanel]').getStore();
                store.on('beforeload',function(store,operation,e){
                    var params = null;
                    params={'virtualPositionName':virtualPositionName};
                    Ext.apply(operation,{
                        params : params
                    });
                }); 
                 me.up('viewport').down('panel[name=vpositionGridPanel]').getStore().loadPage(1);
            }
        },{//标准职位名称输入框
            fieldLabel:i18n('i18n.positionToVP.positionName'),//标准职位名称
            name : 'positionSearchField',
            width:280,
            labelWidth : 120,
            colspan:2
        },{
            xtype:'button',
            text:i18n('i18n.positionToVP.search'),//查询
            width:60,
            colspan:2,
            handler : function(){
                var me = this;
                //参数为标准职位名称
                var positionName = Ext.String.trim(me.up('panel').down('textfield[name=positionSearchField]').getValue());
                var param = {
                    'positionName' : positionName
                };
                var store = me.up('viewport').down('panel[name=vpositionGridPanel]').getStore();
                store.on('beforeload',function(store,operation,e){
                    var params = null;
                    params={'positionName':positionName};
                    Ext.apply(operation,{
                        params : params
                    });
                }); 
                me.up('viewport').down('panel[name=vpositionGridPanel]').getStore().loadPage(1);            
            }
        }];
    },
    initComponent:function(){
        var me = this;
        me.items = me.getItems();
        this.callParent();
    }
});

/**
 * 虚拟职位描述列表
 * 20131128
 */
Ext.define('VPositionGridPanel', {
    name : 'vpositionGridPanel',
    id : 'vpositionGridPanelId',
    extend:'SearchGridPanel',
    store : Ext.create('VPositionDataStore'),
    columnLines:true,
    plugins:null,

    selModel : Ext.create('Ext.selection.CheckboxModel',{
        mode:'SINGLE',
        allowDeselect:true,
        listeners : {
            select:function(records){
                var panel = Ext.getCmp('operationButtonPanelId');
                panel.down('button[name=updateButton]').enable();
                panel.down('button[name=deleteButton]').enable();
                panel.down('button[name=lookButton]').enable();
            },
        deselect:function(records){
                var panel = Ext.getCmp('operationButtonPanelId');
                panel.down('button[name=updateButton]').disable();
                panel.down('button[name=deleteButton]').disable();
                panel.down('button[name=lookButton]').disable();
            }
        }
        
    }),
    columns:null,
    viewConfig: { 
        forceFit:true 
    }, 
    initComponent:function(){
        var me = this;
        this.columns = [{
            text : i18n('i18n.positionToVP.vpositionName'),//虚拟职位
            flex:0.3,
            dataIndex : 'virtualPositionName'
        },{
            text : i18n('i18n.positionToVP.vpositionDesc'),//虚拟职位描述
            flex:0.7,
            dataIndex : 'desc',
            renderer:function(value){
                if(!Ext.isEmpty(value)){
                    return '<span data-qtip="'+value+'">'+value+'</span>';
                }
             }
        }];
        this.dockedItems=[{
            xtype:'pagingtoolbar',
            store:me.store,
            dock:'bottom',
            displayInfo : true,
            autoScroll : true,
            items:[{
                text: i18n('i18n.positionToVP.everyPage'),//每页
                xtype: 'tbtext'
            },Ext.create('Ext.form.ComboBox', {
               width:          window.screen.availWidth*0.0391,
               value:          '20',
               triggerAction:  'all',
               forceSelection: true,
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
                            var pageSize = me.store.pageSize;
                            var newPageSize = parseInt(_field.value);
                            if(pageSize!=newPageSize){
                                me.store.pageSize = newPageSize;
                                this.up('pagingtoolbar').moveFirst();
                            }
                        }
                    }
               }
           }),{
                text: i18n('i18n.positionToVP.item'),//条
                xtype: 'tbtext'
           }]
        }];
        this.callParent();
//        me.store.load();
    }
});


/**
 * 新增、修改等操作按钮区
 * 20131128
 */
Ext.define('OperationButtonPanel',{
    extend : 'NormalButtonPanel', 
    id : 'operationButtonPanelId',
    getItems:function(){
        var me = this;
        return [{
            xtype:'leftbuttonpanel',
            width:320,
            items:[{//新增按钮
                xtype:'button',
                text : i18n('i18n.positionToVP.add'),//新增
                handler : function(){
                    var positionAddWindow = Ext.getCmp('positionEditWindowId');
                    if(Ext.isEmpty(positionAddWindow)){
                        positionAddWindow = Ext.create('PositionEditWindow',{id:'positionEditWindowId'}).show();
                    }else{
                        positionAddWindow.show();
                    }
                    positionAddWindow.setTitle(i18n('i18n.positionToVP.positionToVPWin'));//'职位映射关系编辑界面'
                    positionAddWindow.setOperate('add',null);
               }
            }, {//修改按钮
                xtype : 'button',
                name : 'updateButton',
                text : '修改',//修改
                handler : function() {
                    var me = this;
                    var selection = me.up('viewport').down('panel[name=vpositionGridPanel]').getSelectionModel().getSelection();
                    if(selection.length != 1){
                        MessageUtil.showMessage(i18n('i18n.positionToVP.selectOnePace'));//请选择一条操作记录!
                        return;
                    }
                    var data = selection[0];
                    var positionUpdateWindow = Ext.getCmp('positionEditWindowId');
                    if(Ext.isEmpty(positionUpdateWindow)){
                        positionUpdateWindow = Ext.create('PositionEditWindow',{id:'positionEditWindowId'}).show();
                    }else{
                        positionUpdateWindow.show();
                    }
                    positionUpdateWindow.setTitle(i18n('i18n.positionToVP.positionToVPWin'));
                    positionUpdateWindow.setOperate('update',data);

                }
            }, {//删除按钮
                xtype : 'button',
                name : 'deleteButton',
                text : i18n('i18n.positionToVP.delete'),//删除
                handler : function() {
                    var me = this;
                    var selection = me.up('viewport').down('panel[name=vpositionGridPanel]').getSelectionModel().getSelection();
                    if(selection.length != 1){
                        MessageUtil.showMessage(i18n('i18n.positionToVP.selectOnePace'));//请选择一条操作记录!
                        return;
                    }
                    var data = selection[0];
                    //如果点击查询先判断是否可以删除可以则弹出提示框
                    var param = {
                        "virtualPosition.id":data.getId()
                    }
                    var successFn = function(){
                        
                    };
                    var failureFn = function(json){
                        MessageUtil.showErrorMes(json.message);
                        return;
                    };
                    positionToVPData.canDeleteVirtualPosition(param,successFn,failureFn);
                    //是否删除此虚拟岗位
                    MessageUtil.showQuestionMes(i18n('i18n.positionToVP.isDeleteVP'), function(e){ 
                    if (e == 'yes') {
                        //封装收件人id
                        var successFn = function(){
                            MessageUtil.showInfoMes(i18n('i18n.positionToVP.deleteSuccess'));  //删除成功
                            //刷新grid
                            me.up('viewport').down('panel[name=vpositionGridPanel]').getStore().loadPage(1);
                        };
                        var param = {
                            "virtualPosition.id":data.getId()
                        }
                        //发送请求
                        positionToVPData.deleteVirtualPositionById(param,successFn,failureFn);
                    }
                });
               }
            }, {//查看详情按钮
                xtype : 'button',
                name : 'lookButton',
                text : i18n('i18n.positionToVP.lookUp'),//查看详情
                handler : function() {
                    var me = this;
                    var selection = me.up('viewport').down('panel[name=vpositionGridPanel]').getSelectionModel().getSelection();
                    if(selection.length != 1){
                        MessageUtil.showMessage(i18n('i18n.positionToVP.selectOnePace'));//请选择一条操作记录! 
                        return;
                    }
                    var data = selection[0];
                    var positionLookWindow = Ext.getCmp('positionEditWindowId');
                    if(Ext.isEmpty(positionLookWindow)){
                        positionLookWindow = Ext.create('PositionEditWindow',{id:'positionEditWindowId'}).show();
                    }else{
                        positionLookWindow.show();
                    }
                    positionLookWindow.setTitle(i18n('i18n.positionToVP.positionToVPLook'));
                    positionLookWindow.setOperate('look',data);
                }
            }]
        },{
            xtype:'middlebuttonpanel' 
        }];
    },
    initComponent:function(){
        this.items = this.getItems();
        this.callParent();
    }
});


Ext.onReady(function(){
    /**
     *将界面显示到viewport中 
     */
    Ext.QuickTips.init();
    var viewport=Ext.create('Ext.Viewport',{
        layout : 'border',
        autoScroll:true,
        items:[Ext.create('PositionToVPResearchPanel',{
            region:'north'
        }),{
            xtype:'container',
            region:'center',
            layout:'border',
            items:[Ext.create('OperationButtonPanel',{
                region:'north'
            }),Ext.create('VPositionGridPanel',{
                region:'center'
            })
            ]
        }]
    });
     var panel = Ext.getCmp('operationButtonPanelId');
     panel.down('button[name=updateButton]').disable();
     panel.down('button[name=deleteButton]').disable();
     panel.down('button[name=lookButton]').disable();
    viewport.setAutoScroll(false);
    viewport.doLayout();
});


