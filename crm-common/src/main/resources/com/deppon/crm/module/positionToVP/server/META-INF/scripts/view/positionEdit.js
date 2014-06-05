/**
 * 标准职位对虚拟职位配置分配界面
 * @author 李春雨
 */
//定义data配置的全局变量
var positionToVPData = new PositionToVPData();
//定义全局变量保存右边store中的已分配标准岗位数据
var rightPositionData = new Array();
//数组包含元素方法如果包含则返回包含下标 不包含则返回-1
Array.prototype.contains = function(obj) {  
    var i = this.length;  
    while (i--) {  
        if (this[i] === obj) {  
            return i;  
        }  
    }  
    return -1;  
}
//定义改变颜色的renderer方法，改变value颜色并放入数组
function changeColor(value,metaData,record,rowIndex,colIndex ,store ,view ){
   if(!record.data.isLeft){
        //添加此元素
        if(rightPositionData.contains(record) == -1){
            rightPositionData.push(record);
        }
        record.data.isMove = true;
        return '<span style="color:red">'+value+'</span>';
    }else{
        return value;
    }
}
//改变数组中的值
function changeArray(value,metaData,record,rowIndex,colIndex ,store ,view ){
   if(record.data.isMove){
        //从数组中删除此元素
        var index=rightPositionData.contains(record);
        rightPositionData.splice(index,1);
        record.data.isMove = false;
        return value;
    }else{
        return value;
    }
}

/**
 * 上部虚拟职位信息表单填写界面
 * @author 李春雨
 */
Ext.define('VirtualPositionFrom',{
    name : 'virtualPositionFrom',
    extend:'TitleFormPanel',
    getItems:function(){
        var me = this;
        return [{
            xtype:'basicfiledset', 
            layout:{
                type:'table',
                columns:3
            },
            defaults:{
                labelWidth : 65,
                labelAlign: 'right',
                width : 240
            },
            items:[{
                xtype:'textfield',
                fieldLabel:i18n('i18n.positionToVP.virtualPosition'),//虚拟职位
                name: 'virtualPositionName',
                labelWidth:120,
                regex : /^([\u4E00-\u9FA5]|\w)*$/,
                regexText:i18n('i18n.positionToVP.invalidCharacter'),//存在非法字符
                colspan:1,
                maxLength : 20
            },{
                xtype : 'textfield',
                fieldLabel : i18n('i18n.positionToVP.positionDescription'),//职位描述
                name : 'desc',
                labelWidth : 120,
                width:400,
                maxLength : 100,
                colspan : 2
            }]      
        }];
    },
    initComponent:function(){
        this.items = this.getItems(); 
        this.callParent();
    }
}); 
    
/**
 *职位查询+候选区
 *20131126
 *@author 李春雨
 */
Ext.define('WholePositionSelectPanel',{
    extend:'TitleFormPanel', 
    name : 'wholePositionSelectPanel',
    title:i18n('i18n.positionToVP.mappingPosition'),//待映射标准职位
    layout:'border',
    items:null,
    initComponent:function(){
        this.items = this.getItems(); 
        this.callParent();
    },
    getItems:function(){
        var me = this;
        return [{
            xtype:'basicsearchformpanel', 
            name : 'serachFormPanel',
            region:'north',
            height : 40,
            layout:{
                type:'table',
                columns:2
            },
            items:[{
                xtype: 'textfield',
                fieldLabel: i18n('i18n.positionToVP.position'),//标准职位
                labelWidth:65,
                width:180
            },{
                xtype:'button',
                margin:'-5 0 0 10',
                text:i18n('i18n.positionToVP.search'),//查询
                width:50,
                handler : function(){
                    var me = this;
                    //传递参数为标准职位名称
                    var positionName = Ext.String.trim(me.up('form').down('field').getValue());
                    var param = {
                        'positionName' : positionName
                    };
                    //store加载完成后加载异动过来的数据
                    me.up('window').down('panel[name=wholePositionSelectPanel]').child('grid').getStore().load({params:{positionName:positionName},callback :function(){
                        for(var i = 0 ; i < rightPositionData.length ; i++){
                            me.up('window').down('panel[name=wholePositionSelectPanel]').child('grid').getStore().add(rightPositionData[i]);
                        }
                    }});
                    
                }
            }]      
        },{
            xtype:'popupgridpanel',
            id : 'wholePositionGridId',
            region:'center',
            selModel : Ext.create('Ext.selection.CheckboxModel',{mode:'MULTI'}),
            store : Ext.create('UndistributedPositionDataStore'),
            columns:[{
                text : i18n('i18n.positionToVP.positionName'),//标准职位名称
                flex:1,
                dataIndex : 'jobName',
                renderer : changeColor
            }]
        }];
    }
});

/**
 * 新增、修改虚拟岗位、角色映射关系弹出框的移动按钮面板
 * 20131126
 * @author 李春雨
 */
Ext.define('MoveButtonPanel',{
    name : 'moveButtonPanel',
    extend :'PopRightButtonPanel',
    buttonAlign : 'center',
    layout : 'column',
    items : [ {
        columnWidth : 1,
        height : 0,
        xtype : 'container',
        style : 'padding-top:60px;border:none',
        border : false,
        width : '100%',
        hide:true
    },{//选择全部
        columnWidth : 1,
        xtype: 'container',
        style : 'padding-top:60px;border:none',
        border : false,
        width : '100%',
        items : [ {
            xtype : 'button',
            text : '&gt;&gt;',
            width : '80%',
            handler : function(){
                var me = this;
                var leftGrid = me.up('window').down('panel[name=wholePositionSelectPanel]').child('grid');
                var rightGrid = me.up('window').down('panel[name=selectedPositionPanel]').child('grid');
                rightGrid.getStore().add(leftGrid.getStore().data.items);
                leftGrid.getStore().removeAll();
            }
        } ]
    }, {//选择选中的那
        columnWidth : 1,
        border : false,
        width : '100%',
        xtype: 'container',
        style : 'padding-top:20px;border:none',
        items : [ {
            xtype : 'button',
            text : '&nbsp;&gt;&nbsp;',
            width : '80%',
            handler : function(){
                var me = this;
                var leftGrid = me.up('window').down('panel[name=wholePositionSelectPanel]').child('grid');
                var rightGrid = me.up('window').down('panel[name=selectedPositionPanel]').child('grid');
                var selections = leftGrid.getSelectionModel().getSelection();
                if(selections.length>100){
                    MessageUtil.showMessage(i18n('i18n.positionToVP.max100'));//每次移动最多为100条
                    return;
                }
                rightGrid.getStore().add(selections);
                leftGrid.getStore().remove(selections);
            }
        } ]
    }, {//取消选中的
        columnWidth : 1,
        border : false,
        width : '100%',
        xtype: 'container',
        style : 'padding-top:20px;border:none',
        items : [ {
            xtype : 'button',
            text : '&nbsp;&lt;&nbsp;',
            width : '80%',
            handler : function(){
                var me = this;
                var leftGrid = me.up('window').down('panel[name=wholePositionSelectPanel]').child('grid');
                var rightGrid = me.up('window').down('panel[name=selectedPositionPanel]').child('grid');
                var selections = rightGrid.getSelectionModel().getSelection();
                if(selections.length>100){
                    MessageUtil.showMessage(i18n('i18n.positionToVP.max100'));//每次移动最多为100条
                    return;
                }
                rightGrid.getStore().remove(selections);
                leftGrid.getStore().add(selections);
            }
        } ]
    }, {//取消所有选中的
        columnWidth : 1,
        border : false,
        width : '100%',
        xtype: 'container',
        style : 'padding-top:20px;border:none',
        items : [ {
            xtype : 'button',
            text : '&lt;&lt;',
            width : '80%',
            handler : function(){
                var me = this;
                var leftGrid = me.up('window').down('panel[name=wholePositionSelectPanel]').child('grid');
                var rightGrid = me.up('window').down('panel[name=selectedPositionPanel]').child('grid');
                leftGrid.getStore().add(rightGrid.getStore().data.items);
                rightGrid.getStore().removeAll();
            }
        } ]
    }]
});
    
/**
 *已映射标准职位区
 *20131126
 *@author 李春雨
 */
Ext.define('SelectedPositionPanel',{
    extend:'TitleFormPanel', 
    name : 'selectedPositionPanel',
    title:i18n('i18n.positionToVP.mappedPosition'),//已映射标准职位
    layout:'fit',
    items:null,
    initComponent:function(){
        this.items = this.getItems(); 
        this.callParent();
    },
    getItems:function(){
        var me = this;
        return [{
            xtype:'popupgridpanel',
            id : 'selectedPositionGridId',
            region:'center',
            selModel : Ext.create('Ext.selection.CheckboxModel',{mode:'MULTI'}),
            store : Ext.create('DistributionPositionDataStore'),
            columns:[{
                text : i18n('i18n.positionToVP.positionName'),//标准职位名称
                flex:1,
                dataIndex : 'jobName',
                renderer : changeArray
            }]
        }];
    }
});

/**
 *标准岗位对虚拟岗位的编辑界面
 *20131126
 *@author 李春雨
 */
Ext.define('PositionEditWindow',{
    extend:'PopWindow',
    data : null,
    isUpdate : null,
    cls:'popup',
    width:820,
    height:500,
    modal:true,
    layout:'border',
    title:i18n('i18n.positionToVP.positionToVPWin'),//职位映射关系编辑界面
    closeAction:'hide',
    items:[Ext.create('VirtualPositionFrom',{
        region:'north'
    }),{
        xtype:'container',
        region:'center',
        layout:'border',
        items:[Ext.create('WholePositionSelectPanel',{
            region:'west',
            width:370
        }),{
            xtype:'container',
            region:'center',
            layout:'border',
            items:[Ext.create('MoveButtonPanel',{
                region:'west',
                width:50
            }),Ext.create('SelectedPositionPanel',{
                region:'center'
            })]
        }]
    }],
    buttons:[{//提交按钮
        text:i18n('i18n.positionToVP.commit'),
        name : 'commit',
        handler:function(){
            var me = this;
            me.disable();
            //获取store
            if(!me.up('window').down('form').getForm().isValid()){
                MessageUtil.showMessage(i18n('i18n.positionToVP.formatNotCorrect'));//信息格式输入不正确!
                me.enable();
                return;
            }
            var virtualPositionName = me.up('window').down('field[name=virtualPositionName]').getValue();
            var desc = me.up('window').down('field[name=desc]').getValue();
            var store = me.up('window').down('panel[name=selectedPositionPanel]').child('grid').getStore();
            var positionIDs = new Array();
            
            store.each(function(record){
                positionIDs.push(record.data.pkEhrPosition);
            });
            var successFn = function(json){
                MessageUtil.showInfoMes(i18n('i18n.positionToVP.saveSuccess'));//保存成功
                me.enable();
                //关闭窗口
                me.up('window').hide();
                //刷新store
                Ext.getCmp('vpositionGridPanelId').getStore().loadPage(1);
            };
            var failureFn = function(json){
                MessageUtil.showErrorMes(json.message);
                me.enable();
                return;
            };
            //判断是更新还是修改进行相应操作
            if(me.up('window').isUpdate == 'add'){
                var params = {
                    'virtualPosition.id' : '',
                    'virtualPosition.virtualPositionName' : virtualPositionName,
                    'virtualPosition.desc' : desc,
                    'positionIDs' : positionIDs
                };
                
                positionToVPData.addVirtualPosition(params,successFn,failureFn);
            }
           if(me.up('window').isUpdate.substring(0,6) == 'update'){
                var id = me.up('window').isUpdate.substring(6);
                var params = {
                    'virtualPosition.id' : id,
                    'virtualPosition.virtualPositionName' : virtualPositionName,
                    'virtualPosition.desc' : desc,
                    'positionIDs' : positionIDs
                };
                
                positionToVPData.updateVirtualPosition(params,successFn,failureFn);
           }
            
        }
    },{//取消按钮
        text:i18n('i18n.positionToVP.cancel'),//取消
        name : 'cancel',
        handler:function(){
            this.up('window').hide();
        }
    }],
    listeners:{
        //隐藏界面前 清空里面所有的数据
        hide : function(){
            var me = this;
            me.down('form').getForm().reset();
            me.down('panel[name=wholePositionSelectPanel]').child('grid').getStore().removeAll();
            me.down('panel[name=selectedPositionPanel]').child('grid').getStore().removeAll();
            me.down('panel[name=serachFormPanel]').child('field').setValue('');
            //清空数组中数据
            rightPositionData.splice(0);
        }
    },
    setOperate : function(operate,data){
        var me = this;
        //添加按钮的逻辑处理
        if(operate == 'add'){
            me.data = null;
            me.isUpdate = 'add';
            me.down('fieldset').enable();
            me.down('button[name=commit]').show();
            me.down('button[name=cancel]').show();
            me.down('panel[name=serachFormPanel]').show();
            me.down('panel[name=moveButtonPanel]').show();
            me.down('panel[name=wholePositionSelectPanel]').child('grid').getStore().load();
        }
        //修改按钮的逻辑处理  获取数据  查询
        if(operate == 'update'){
            me.data = data;
            me.isUpdate = 'update'+data.getId();
            me.down('fieldset').enable();
            me.down('button[name=commit]').show();
            me.down('button[name=cancel]').show();
            me.down('panel[name=serachFormPanel]').show();
            me.down('panel[name=moveButtonPanel]').show();
            var vpID = data.getId();
            //加载数据到form中
            me.down('form').loadRecord(data);
            //刷新待分配标准职位信息
            me.down('panel[name=wholePositionSelectPanel]').child('grid').getStore().load();
            //刷新已分配标准职位信息根据虚拟职位的ID
            me.down('panel[name=selectedPositionPanel]').child('grid').getStore().load({params:{vpID:vpID}});
        }
        //查看详情的逻辑处理   取消所有的按钮
        if(operate == 'look'){
            me.down('fieldset').disable();
            me.down('button[name=commit]').hide();
            me.down('button[name=cancel]').hide();
            me.down('panel[name=serachFormPanel]').hide();
            me.down('panel[name=moveButtonPanel]').hide();
//            me.down('panel[name=wholePositionSelectPanel]').hide();
            var vpID = data.getId();
            me.down('form').loadRecord(data);
            me.down('panel[name=selectedPositionPanel]').child('grid').getStore().load({params:{vpID:vpID}});
        }
    }
});