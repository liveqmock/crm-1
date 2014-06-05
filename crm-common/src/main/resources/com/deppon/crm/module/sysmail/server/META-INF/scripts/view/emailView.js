//邮件管理数据访问对象
var sysmailData = new SysmailData();
//记录是否点击查询按钮
var isClickSearch = true;
//记录邮件组的id
var globalGroupId = null;
//收件人form
Ext.define('AllPeopleForm',{
  extend : 'Ext.form.Panel',
  defaults : {
    margin:'5 5 5 5',
    labelWidth : 70
  },
  getItems : function(){
      var items = [{
          xtype : 'textfield',
          name : 'receiverName',
          fieldLabel : i18n('i18n.sysMail.receiver'),//收件人姓名
          maxLength : 20,
          allowBlank : false
      },{
          xtype:'textfield',
          name : 'empCode',
          fieldLabel : i18n('i18n.sysMail.empCode'),//收件人工号
          regex:/^[\w]+$/,
          regexText:i18n('i18n.sysMail.onlyLetterAndNum'),//工号只能是字母和数字
          maxLength : 6
      },{
          xtype : 'textfield',
          name : 'emailAddress',
          fieldLabel:i18n('i18n.sysMail.emailAddress'),//邮件地址
          regex : /^[a-z]*([a-z0-9]*[-_]?[a-z0-9]+)*@([a-z0-9]*[-_]?[a-z0-9]+)+[\.][a-z]{2,3}([\.][a-z]{2})?$/i,
          regexText : i18n('i18n.sysMail.mailFormatError'),//邮箱格式错误
          allowBlank : false
      },{
          xtype:'combo',
          id:'comboId',
          name : 'groupId',
          store: Ext.getCmp('groupPanel').getStore(),
          displayField: 'groupName',
          valueField: 'id',
          forceSelection:true,
          fieldLabel : i18n('i18n.sysMail.belongGroup'),//所属分组
          defaultListConfig : { 
              loadMask: false
          }, 
          listeners : {
              change : function(field,newValue){
                  field.lastSelection = '';
                  if(Ext.isEmpty(newValue)){
                      field.setValue(null);
                  }
              }
          }
      }];
      return items;
  },
  initComponent : function(){
      var me = this;
      me.items = me.getItems();
      this.callParent();
  }
});

//新增收件人window
Ext.define('AllPeople',{
  extend : 'PopWindow',
  id : 'addPeople',
  width : 300,
  closeAction : 'hide',
  autoScroll : true,
  height : 200,
  setUpdate:function(update){
      this.setTitle(update===true?i18n('i18n.sysMail.updateLinkman'):i18n('i18n.sysMail.receiverAdd'));//修改收件人  收件人新增
      Ext.getCmp('comboId')[update===true?'hide':'show']();
      if(update===true){
           this.isUpdate = true;
           var data = Ext.getCmp('emailPlaneGirdId').getSelectionModel().getSelection()[0];
           this.child('form').getForm().loadRecord(data);
      }
      if(update===true){
           this.isUpdate = true;
           var data = Ext.getCmp('emailPlaneGirdId').getSelectionModel().getSelection()[0];
           this.child('form').getForm().loadRecord(data);
      }else{
           this.isUpdate = false;
      }
  },
  //更新收件人的方法
  updateMailPeople : function(){
      var successFn = function(json){
            MessageUtil.showInfoMes(i18n('i18n.sysMail.operateSuccess'));  //保存成功!
            Ext.getCmp('emailPlaneGirdId').store.loadPage(1);
            Ext.getCmp('peopleId').close();
        };
        //更新收件人的后台代码编写
        var mailPeopleDate = Ext.getCmp('emailPlaneGirdId').getSelectionModel().getSelection()[0];
        var form = this.child('form').getForm();
        var addParams = {
            //收件人属性
            'mailAccount.id': mailPeopleDate.data.accountId,
            'mailAccount.empCode' : form.findField('empCode').getValue(),
            'mailAccount.emailAddress' : form.findField('emailAddress').getValue(),
            'mailAccount.receiverName' : form.findField('receiverName').getValue()
        };
        //验证是否有修改
        
        //提交到后台代码
        sysmailData.updateMailPeople(addParams,successFn,failureFn);
  },
  //添加收件人的方法
  addMailPeople : function(){
      var successFn = function(json){
            MessageUtil.showInfoMes(i18n('i18n.sysMail.operateSuccess')); //保存成功!
            //刷新收件人grid
            Ext.getCmp('emailPlaneGirdId').store.loadPage(1);
            //添加成功后关闭窗口
            Ext.getCmp('peopleId').close();
        };
        //新增收件人的后台代码编写
        var temp = new AccGroResultModel();
        this.child('form').getForm().updateRecord(temp);
        temp.commit();
        var addParams = {
                //收件人属性
                'mailAccount.id':null,
                'accountGroup.mailGroupId' : temp.data.groupId,
                'mailAccount.empCode' : temp.data.empCode,
                'mailAccount.emailAddress' : temp.data.emailAddress,
                'mailAccount.receiverName' : temp.data.receiverName
                    };
          //提交到后台代码
        sysmailData.addMailPeople(addParams,successFn,failureFn);
  },
  listeners: {
      beforeshow : function () {
             this.child('form').getForm().reset();
      }
  },
  getBbar : function(me){
      var bbar = ['->',{  //添加按钮
          xtype : 'button', 
          text : i18n('i18n.sysMail.commit'),//提交
          handler : function(){
              var form = me.child('form').getForm();
              if(form.isValid()){
                    var String =form.findField('emailAddress').getValue().split('@');
                    if(String[0].length<3||String[0].length>18){
                        MessageUtil.showMessage(i18n('i18n.sysMail.lengthLimit'));//请在@前输入3~18位字符
                        return;
                     }
                    if(me.isUpdate){
                        me.updateMailPeople();
                    }else{
                        me.addMailPeople();
                    }
              }
          }
      },{
          xtype : 'button', //取消按钮
          text : i18n('i18n.sysMail.cancel'), //取消
          handler : function(){
              me.hide();
          }
      }];
      return bbar;
  },
  initComponent : function(){
      var me = this;
      Ext.applyIf(me,{
          items:[Ext.create('AllPeopleForm')],
          bbar:me.getBbar(me)
      });
      this.callParent(arguments);
  }
});


//划分到window
Ext.define('DividePeople',{
  extend : 'PopWindow',
  id : 'dividePeople',
  closeAction : 'hide',
  autoScroll : true,
  title : i18n('i18n.sysMail.groupDivide'),//收件人分组划分
  layout : {
      type : 'table',
      columns:2
  },
  getItems : function(){
       var items = [{
              xtype:'combo',
              name : 'mailGroup',
              id:'mailGroupId',
              store: Ext.getCmp('groupPanel').getStore(),
              displayField: 'groupName',
              valueField: 'id',
              editable : false,
              defaultListConfig : { 
                  loadMask: false
              }, 
              fieldLabel : i18n('i18n.sysMail.belongGroup')//所属分组
          },{
              xtype : 'button',
              text : '+',
              margin:'-5 0 0 0',
              handler : function(){
                  if(Ext.isEmpty(Ext.getCmp('addGroup'))){
                      Ext.create('AllGroup',{id:'addGroup'}).show();
                  }else{
                      Ext.getCmp('addGroup').show();
                  }
              }
          }];
       return items;
  },
    getBbar : function(me){
        var bbar = ['->',{  //添加按钮
            xtype : 'button', 
            text : i18n('i18n.sysMail.commit'),//提交
            handler : function(){
                 var selection = Ext.getCmp('emailPlaneGirdId').getSelectionModel().getSelection();
                //封装收件人id
                var mailAccountIds = new Array();
                for (var i = 0; i < selection.length; i++) {
                    mailAccountIds[i] = selection[i].get('accountId');
                }
                //获得选中的邮件组
                var mailGroupId = Ext.getCmp('mailGroupId').getValue();
                if(Ext.isEmpty(mailGroupId)){
                    MessageUtil.showMessage(i18n('i18n.sysMail.selectGroup'));//请选择所属分组再进行操作~
                    return;
                }
                //发送请求添加到指定组
                var successFn = function(){
                    MessageUtil.showInfoMes(i18n('i18n.sysMail.operateSuccess'));//保存成功!
                    //刷新收件人grid
                    Ext.getCmp('emailPlaneGirdId').store.loadPage(1);
                    me.hide();
                };
                var parms = {
                    "mailAccountIds":mailAccountIds,
                    "mailGroupId":mailGroupId
                }
                //发送请求
                sysmailData.dividePeopleTo(parms,successFn,failureFn);
            }
        },{
          xtype : 'button', //取消按钮
          text : i18n('i18n.sysMail.cancel'), //取消
          handler : function(){
              me.hide();
          }
      }];
      return bbar;
  },
  listeners: {
      beforeshow : function () {
             this.child('combo').reset();
      }
  },
  initComponent : function(){
      var me = this;
      me.items = me.getItems();
      me.bbar = me.getBbar(me);
      this.callParent();
  }
});

//新增分组form
Ext.define('AllGroupForm',{
  extend : 'Ext.form.Panel',
  getItems : function(){
      var items = [{
          xtype : 'textfield',
          labelWidth:70,
          margin:'5 5 5 5',
          name : 'groupName',
          maxLength : 20,
          regex : /^([\u4E00-\u9FA5]|\w)*$/,
          regexText:i18n('i18n.sysMail.specificSymbol'),//不能包含特殊符号
          allowBlank : false,
          fieldLabel : i18n('i18n.sysMail.groupName')//分组名称
      },{
          xtype:'textfield',
          labelWidth:70,
          margin:'5 5 5 5',
          name : 'groupCode',
          maxLength : 20,
          regex : /^\w+$/i,
          regexText:i18n('i18n.sysMail.onlyLetterNumAndUnderline'),//只能包含字母数字和下划线
          allowBlank : false,
          fieldLabel : i18n('i18n.sysMail.groupCode')//分组编码
      }];
      return items;
  },
  initComponent : function(){
      var me = this;
      me.items = me.getItems();
      this.callParent();
  }
});


//新增分组window
Ext.define('AllGroup',{
  extend : 'PopWindow',
  closeAction : 'hide',
  isUpdate : false,
  autoScroll : true,
  title : i18n('i18n.sysMail.groupAdd'),//分组新增
  getItems : function(){
     if(this.isUpdate){
         var items = [Ext.create('AllGroupForm',{id:'updateGroupForm'})];
     }else{
         var items = [Ext.create('AllGroupForm',{id:'addGroupForm'})];
     }
       return items;
  },
  updateMailGroup : function(){
    var successFn = function(json){
        //更新成功的操作
        MessageUtil.showInfoMes(i18n('i18n.sysMail.operateSuccess'));  //保存成功
        Ext.getCmp('updateGroupForm').getForm().updateRecord(mailGroupDate);
        mailGroupDate.commit();
        Ext.getCmp('updateGroup').close();
    };
    var mailGroupDate = Ext.getCmp('groupPanel').getSelectionModel().getSelection()[0];
//      Ext.getCmp('updateGroupForm').getForm().updateRecord(mailGroupDate);
    var form = Ext.getCmp('updateGroupForm').getForm();
    var addParams = {
                'mailGroup.groupName':form.findField('groupName').getValue(),
                'mailGroup.groupCode':form.findField('groupCode').getValue(),
                'mailGroup.id' : mailGroupDate.data.id
                };
      //提交到后台代码
    sysmailData.updateMailGroup(addParams,successFn,failureFn);
  },
  addGroup : function(){
    var successFn = function(json){
        MessageUtil.showInfoMes(i18n('i18n.sysMail.operateSuccess'));  //保存成功
        Ext.getCmp('groupPanel').getStore().load();
        Ext.getCmp('addGroup').close();
    };
    var mailGroupDate = new MailGroupModel();
    Ext.getCmp('addGroupForm').getForm().updateRecord(mailGroupDate);
    mailGroupDate.commit();
    var addParams = {
                'mailGroup.id':null,
                'mailGroup.groupName':mailGroupDate.data.groupName,
                'mailGroup.groupCode':mailGroupDate.data.groupCode
                };
      //提交到后台代码
    sysmailData.addMailGroup(addParams,successFn,failureFn);
  },
  getBbar : function(me){
      var bbar = ['->',{  
          xtype : 'button', 
          text : i18n('i18n.sysMail.commit'),//提交
          handler : function(){
            if(me.isUpdate){
                if(Ext.getCmp('updateGroupForm').getForm().isValid()){
                    me.updateMailGroup();
                }
            }else{
                if(Ext.getCmp('addGroupForm').getForm().isValid()){
                    me.addGroup();
                }
            }
          }
      },{
          xtype : 'button', 
          text : i18n('i18n.sysMail.cancel'), //取消
          handler : function(){
              me.hide();
          }
      }];
      return bbar;
  },
  listeners: {
      beforeshow: function () {
         if(this.isUpdate){
           var data = Ext.getCmp('groupPanel').getSelectionModel().getSelection()[0];
           Ext.getCmp('updateGroupForm').getForm().loadRecord(data);
         }else{
           Ext.getCmp('addGroupForm').getForm().reset();
         }
      }
  },
  initComponent : function(){
      var me = this;
      me.items = me.getItems();
      me.bbar = me.getBbar(me);
      this.callParent();
  }
});

Ext.onReady(function(){
    
    /**
     * 定义操作按钮面板
     */
    Ext.define('RightButtonPanel',{
        extend:'BasicPanel', 
        height:30,
        layout:{
            type:'table',
            columns:5
        },
        items:[{
            xtype:'button',
            margin:'0 5 0 15',
            text:i18n('i18n.sysMail.addLinkman'),//新增收件人
            handler:function(){
                var people = Ext.getCmp('peopleId')
                if(Ext.isEmpty(people)){
                    people = Ext.create('AllPeople',{id:'peopleId',isUpdate:false}).show();
                }else{
                    people.show();
                }
                
                people.setUpdate(false);
            }
        },{
            xtype:'button',
            margin:'0 5 0 0',
            text:i18n('i18n.sysMail.updateLinkman'),//修改收件人
            handler:function(){
                //获取选中记录
                var selection = Ext.getCmp('emailPlaneGirdId').getSelectionModel().getSelection();
                if(selection.length != 1){
                    MessageUtil.showMessage(i18n('i18n.sysMail.selectOne'));//请选择一条操作记录
                    return;
                }
               
                var people = Ext.getCmp('peopleId')
                if(Ext.isEmpty(people)){
                    people = Ext.create('AllPeople',{id:'peopleId'}).show();
                }else{
                    people.show();
                }
                people.setUpdate(true);
            }
        },{ 
            xtype:'button',
            margin:'0 5 0 0',
            text:i18n('i18n.sysMail.removeLinkman'),//移除收件人
            handler:function(){
                //获取选中记录
                var selection = Ext.getCmp('emailPlaneGirdId').getSelectionModel().getSelection();
                if(selection.length == 0){
                    MessageUtil.showMessage(i18n('i18n.sysMail.selectRecord'));//请选择操作记录~
                    return;
                }
                var relationIds = new Array();
                for (var i = 0; i < selection.length; i++) {
                    if(!Ext.isEmpty(selection[i].get('relationId'))){
                        relationIds[i] = selection[i].get('relationId');
                    }else{
                        selection.splice(i--,1);
                    }
                }
                if(relationIds.length == 0){
                    MessageUtil.showInfoMes(i18n('i18n.sysMail.choiceDividedLinkman'));
                    return;
                }
                 //删除弹出提示，是否删除
                MessageUtil.showQuestionMes((i18n('i18n.sysMail.sureRemove')), function(e){ //确定要移除收件人吗?
                    if (e == 'yes') {
                        //封装收件人id
                        var successFn = function(){
                            MessageUtil.showInfoMes(i18n('i18n.sysMail.operateSuccess'));  //保存成功!
                            //刷新收件人grid
                            Ext.getCmp('emailPlaneGirdId').store.loadPage(1);
                        };
                        var parms = {
                            "relationIds":relationIds
                        }
                        //发送请求
                        sysmailData.removeMailAccounts(parms,successFn,failureFn);
                    }
                });
            }
        },{
            xtype:'button',
            margin:'0 5 0 0',
            text:i18n('i18n.sysMail.deleteLinkman'),//删除收件人
            handler:function(){
                //获取选中记录
                var selection = Ext.getCmp('emailPlaneGirdId').getSelectionModel().getSelection();
                if(selection.length == 0){
                    MessageUtil.showMessage(i18n('i18n.sysMail.selectRecord'));//请选择操作记录~
                    return;
                }
                //删除弹出提示，是否删除
                MessageUtil.showQuestionMes((i18n('i18n.sysMail.sureDelete')), function(e){ //是否删除
                    if (e == 'yes') {
                        //封装收件人id
                        var mailAccountIds = new Array();
                        for (var i = 0; i < selection.length; i++) {
                            mailAccountIds[i] = selection[i].get('accountId');
                        }
                        var successFn = function(){
                            MessageUtil.showInfoMes(i18n('i18n.sysMail.deleteSuccess'));     //数据删除成功
                            //刷新收件人grid
                            Ext.getCmp('emailPlaneGirdId').store.loadPage(1);
                        };
                        var parms = {
                            "mailAccountIds":mailAccountIds
                        }
                        //发送请求
                        sysmailData.deleteMailAccounts(parms,successFn,failureFn);
                    }
                });
                
            }
        },{
            xtype:'button',
            margin:'0 5 0 0',
            text:i18n('i18n.sysMail.divide'),//划分到...
            handler:function(){
                 //获得已选中记录
                var selection = Ext.getCmp('emailPlaneGirdId').getSelectionModel().getSelection();
                if(selection.length == 0){
                    MessageUtil.showMessage(i18n('i18n.sysMail.selectMore'));//请选择至少一个收件人再进行操作~
                    return;
                }
                if(Ext.isEmpty(Ext.getCmp('dividePeople'))){
                    Ext.create('DividePeople').show();
                }else{
                    Ext.getCmp('dividePeople').show();
                }
            }
        }]
    });
    
    /**
     * 定义操作按钮面板
     */
    Ext.define('LeftButtonPanel',{
        extend:'BasicPanel', 
        layout:{
            type:'table',
            columns:4
        },
        items:[{
            xtype:'button',
            margin:'0 5 5 15',
            text:i18n('i18n.sysMail.addGroup'),//新增分组
            handler:function(){
                 if(Ext.isEmpty(Ext.getCmp('addGroup'))){
                     Ext.create('AllGroup',{id:'addGroup'}).show();
                 }else{
                     Ext.getCmp('addGroup').show();
                 }
                
            }
        },{ 
            xtype:'button',
            margin:'0 5 5 5',
            text:i18n('i18n.sysMail.deleteGroup'),//删除分组
            handler:function(){
                 var selection = Ext.getCmp('groupPanel').getSelectionModel().getSelection();
                 if(selection.length != 1){
                     MessageUtil.showMessage(i18n('i18n.sysMail.selectOne'));//请选择一条操作记录
                     return;
                 }
                 MessageUtil.showQuestionMes(i18n('i18n.sysMail.sureDelete'),//是否删除
                            function(e){
                            if (e == 'yes') {
                                var successFn = function(json){
                                    MessageUtil.showInfoMes(i18n('i18n.sysMail.deleteSuccess'));//数据删除成功
                                    Ext.getCmp('groupPanel').getStore().remove(selection[0]);
                                    Ext.getCmp('emailPlaneGirdId').store.loadPage(1);
                                };
                                var addParams = {
                                        'mailGroupId':selection[0].data.id
                                        };
                                sysmailData.deleteMailGroup(addParams,successFn,failureFn);
                            }
                            }
                        )
            }
        },{
            xtype:'button',
            margin:'0 5 5 5',
            text:i18n('i18n.sysMail.updateGroup'),//修改分组
            handler:function(){
                 var selection = Ext.getCmp('groupPanel').getSelectionModel().getSelection();
                 if(selection.length != 1){
                     MessageUtil.showMessage(i18n('i18n.sysMail.selectOne'));//请选择一条操作记录
                     return;
                 }
                 if(Ext.isEmpty(Ext.getCmp('updateGroup'))){
                     //修改分组
                     Ext.create('AllGroup',{title:i18n('i18n.sysMail.updateGroup'),id:'updateGroup',isUpdate:true}).show();
                 }else{
                     Ext.getCmp('updateGroup').show();
                 }
            }
        }]
    });
             
    /**
     * email配置grid
     */
    Ext.define('EmailGridPanel', {
        extend:'SearchGridPanel',
        cls:'market',
        columnLines:true,
        plugins:null,
        selModel : Ext.create('Ext.selection.CheckboxModel',{mode:'MULTI'}),
        store :null,
        columns:[{
                xtype:'rownumberer',
                width:40,
                header:i18n('i18n.sysMail.sequenceNum')//序号
            },{
                header : i18n('i18n.sysMail.receiverName'),//收件人姓名
                dataIndex : 'receiverName',
                width:80
            },{
                header : i18n('i18n.sysMail.receiverCode'),//收件人工号
                dataIndex : 'empCode',
                width:80
            },{
                header:i18n('i18n.sysMail.emailAddress'),//邮件地址
                dataIndex : 'emailAddress',
                width:160
            },{
                header:i18n('i18n.sysMail.belongGroup'),//所属分组
                dataIndex : 'groupName',
                flex:1
            }],
        viewConfig: { 
            forceFit:true 
        }, 
        initComponent:function(){
            var me = this;
            var store = Ext.create('SysmailMailAccountStore');
            store.on('beforeload',function(store,operation,e){
                var params = null;
                if(isClickSearch){
                    var searchStr = null;
                    if(!Ext.isEmpty(Ext.getCmp('searchFieldId'))){
                        searchStr = Ext.getCmp('searchFieldId').getValue()
                    }
                    params={'mailGroupId':"",'searchString':searchStr};
                }else{
                    params={'mailGroupId':globalGroupId};
                }
                Ext.apply(operation,{
                    params : params
                });
            }); 
            me.store = store;
            store.load({params:{'mailGroupId':"",'searchString':""}});
            this.dockedItems=[{
                xtype:'pagingtoolbar',
                dock:'bottom',
                store:store,
                displayInfo : true,
                autoScroll : true
            }];
            this.callParent();
        }
    });
    
    
    /**
     * 左边上部查询panel
     */
    Ext.define('RightSearchPanel',{
        extend:'BasicPanel',
        layout:'border',
        margin:'3 0 3 0',
        items:[{
            xtype:'basicpanel',
            region:'north',
            layout:'column',
            margin:'0 0 3 0',
            items:[{
                margin:'0 5 0 15',
                xtype:'textfield',
                id:'searchFieldId',
                columnWidth:.5
            },{
                xtype:'button',
                columnWidth:.15,
                text:i18n('i18n.sysMail.query'),//查询
                handler:function(){
                    isClickSearch = true;
                    //刷新收件人grid
                    Ext.getCmp('emailPlaneGirdId').store.loadPage(1);
                }
            }]
        },{
            xtype:'basicpanel',
            region:'center',
            layout:'fit',
            items:[Ext.create('RightButtonPanel')]
        }]
    });
    
    /**
     * 左边下部邮件组panel
     */
    Ext.define('GroupPanel',{
        extend:'SearchGridPanel',
        id: 'groupPanel',
        store :null,
        cls:'market',
        columnLines:true,
        plugins:null,
        columns:[{
                dataIndex : 'groupName',
                flex:1,
                header:i18n('i18n.sysMail.groupName')//分组名称
            },{
                dataIndex : 'groupCode',
                header:i18n('i18n.sysMail.groupCode')//分组编号
            }],
        viewConfig: { 
            forceFit:true,
            loadMask: false
        }, listeners:{
            itemclick:function(th,record,item,index,e,eOpts){
                isClickSearch = false;
                globalGroupId = Ext.getCmp('groupPanel').getSelectionModel().getSelection()[0].get("id");
                //刷新收件人grid
                Ext.getCmp('emailPlaneGirdId').store.loadPage(1);
            }
        },
        initComponent:function(){
            var me = this;
            var mailGroupStore = Ext.create('SysmailGroupStore');
            mailGroupStore.load();
            me.store = mailGroupStore;
            this.callParent();
        }
    });
    
    /**
     * 分组列表
     */
    Ext.define('LeftPanel',{
        extend:'BasicPanel',
        border:true,
        layout:'border',
        width:300,
        items:[{
            xtype:'basicpanel',
            margin:'3 3 3 3',
            region:'north',
            layout:'fit',
            items:[Ext.create('LeftButtonPanel')]
        },{
            margin:'3 3 3 3',
            xtype:'basicpanel',
            region:'center',
            layout:'fit',
            items:[Ext.create('GroupPanel')]
        }]
    });
    
    
    /**
     *定义整个界面布局 
     */
    Ext.define('EmailPanel',{
        extend:'BasicPanel',
        layout:'border',
        border:true,
        items:null,
//        buttonBar:null,
//        html:'xxi'
        initComponent:function(){
            var me = this;
            //创建store
            
            //创建grid
            var customerDevelopGridPanel = Ext.create('EmailGridPanel',{id:'emailPlaneGirdId'});
            
            //界面布局模块
            //分north和center
            //north为查询form和button
            //center为显示的grid
            me.items =[{
                xtype:'basicpanel',
                region:'west',
                layout:'fit',
                items:[Ext.create('LeftPanel')]
            },{
                xtype:'basicpanel',
                region:'center',
                layout:'border',
                items:[{
                    xtype:'basicpanel',
                    region:'north',
                    height:68,
                    layout:'border',
                    items:[{
                        xtype:'basicpanel',
                        region:'center',
                        layout:'fit',
                        items:[ Ext.create('RightSearchPanel')]
                    }]
                },{
                    xtype:'basicpanel',
                    region:'center',
                    layout:'fit',
                    items:[customerDevelopGridPanel]
                }]
            }];
            this.callParent();
        }
    });
    
    /**
     *将界面显示到viewport中 
     */
    var viewport=Ext.create('Ext.Viewport',{
        layout : 'border',
        autoScroll:true,
        items:[Ext.create('EmailPanel',{region:'center'})]
    });
    viewport.setAutoScroll(false);
    viewport.doLayout();
    
});
/**
 * 失败提示信息
 * @param {} json 失败信息
 */
var failureFn = function(json){
    MessageUtil.showErrorMes(json.message);
}