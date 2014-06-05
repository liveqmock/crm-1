Ext.onReady(function(){
	//添加工单责任部门按钮panel
	Ext.define('DutyDeptAddAndDelPanel',{
	extend:'NormalButtonPanel',
	border:false,
	initComponent:function(){
		this.items = this.getItems();
		this.callParent(arguments);
	},
	getItems:function(){
		var me = this;
		return [
		   {
			xtype:'leftbuttonpanel',
			width:430,
			items:[
			  {//增加按钮
				  xtype:'button',
				  text:i18n('i18n.duty.add'),//增加
				  handler:function(){
				  	//获取工单特殊部门Grid的Store
				  	var store = Ext.getCmp('dutyDeptManageGridId').getStore();
				  	//创建一个工单责任部门Model，用于向前台传递
				  	var model = new DutyDeptModel();
				  	//创建一个部门选择器
					Ext.create('DeptLookUpWindow',{
						title:i18n('i18n.duty.Department.win_title'),//部门选择
						listeners:{
							select:function(win,record){//选择一条部门信息
								model.set('deptId' , record.get('id'));  //初始化部门id
								model.set('standardCode' , record.get('standardCode'));//初始化部门标杆编码
								model.set('deptName' , record.get('deptName'));//初始化部门名称
								var successFn = function(response){
									store.add(model);//成功，将部门添加到工单特殊部门Grid的Store中
								};
								var failureFn = function(response){//失败方法
									if(!Ext.isEmpty(response)){
										MessageUtil.showMessage(response.message);//提示错误信息
									}else{
										MessageUtil.showMessage(i18n('i18n.dutydept.dutyaddfaild'));//添加失败
									}
								};
								//调用添加部门方法
								DutyDeptOperateStore.prototype.saveDutyDept({
									'dutyDept':{							//将数据赋值给dutyDept，传到后台
										'deptId': model.get('deptId'),
										'standardCode': model.get('standardCode'),
										'deptName': model.get('deptName')
									}
								}, successFn, failureFn);
							win.close();
							}
						}
					}).show();
					
				  }
			  },
			 
			  {//删除按钮
				  xtype:'button',
				  text:i18n('i18n.duty.delete'),//增加
				  handler:function(){
					var me = this;
					//获取工单特殊责任部门Grid
					var grid = Ext.getCmp('dutyDeptManageGridId');
					//获取选择的记录
					var selectionList = grid.getSelectionModel().getSelection();
					//获取工单特殊责任部门Grid的Store
					var panelStore = grid.getStore();
					//校验选择记录是否为空
					if(Ext.isEmpty(selectionList)){
						MessageUtil.showMessage(i18n('i18n.dutydept.pleasechoose'));return;
					}
					//成功，将Store中数据清除
					var successFn = function(response){
						for(var i=0;i<selectionList.length;i++){
							panelStore.remove(selectionList[i]);
						}
					};
					//失败
					var failureFn = function(response){
						if(!Ext.isEmpty(response)){
							MessageUtil.showMessage(response.message);//提示错误信息
						}else{
							MessageUtil.showMessage(i18n('i18n.dutydept.dutydeptisnull'));
						}
					};
					//创建部门LIST，赋值传到前台
					var dutyDeptList = [];
					for(var i=0;i<selectionList.length;i++){
						dutyDeptList.push(selectionList[i].data);
					}
					var params = {list:dutyDeptList};
					DutyDeptOperateStore.prototype.deleteDutyDept({
						'dutyDeptList' : dutyDeptList   //为工单特殊责任部门赋值
					}, successFn, failureFn);
				  }
			  }
			]
		   },{xtype:'middlebuttonpanel'}
		   ];
	}
});
		//创建工单责任部门Grid
		Ext.define('DutyDeptManageGrid',{
		extend:'PopupGridPanel',
		defaults:{align:'center'},
		autoScroll:true,
		selModel:Ext.create('Ext.selection.CheckboxModel'),
		initComponent:function(){ 
			var me = this;
			//创建部门Store
			me.store = Ext.create('DutyDeptStore');
			me.columns = [
			    {//序号
			    	xtype:'rownumberer',
					header:i18n('i18n.dutydept.rownumberid'),
					width:40
			    },{//部门名称
					header:i18n('i18n.dutydept.deptname'),
					dataIndex:'deptName',
					flex : 1
			    },{ //部门编码
					header :i18n('i18n.dutydept.deptcode'),
					hidden : true,	//将部门标杆编码隐藏掉。不在页面上显示
					dataIndex:'standardCode',
					flex : 1
			    }
			   
		    ];
		    this.callParent();
	   }
	});
	//创建Viewport
	var viewport=Ext.create('Ext.Viewport',{
		layout : 'border',
		items:[
	        {
	        	xtype:'container',
				region:'north',
				layout:'fit',
				items:[Ext.create('DutyDeptAddAndDelPanel',{id:'dutyDeptAddAndDelPanelId'})]
			},
			{
				xtype:'container',
				region:'center',
				layout:'fit',
				items:[Ext.create('DutyDeptManageGrid',{id:'dutyDeptManageGridId'})]
			}
	    ]
	   
	});
	 viewport.setAutoScroll(true);	
	 viewport.doLayout();
	
});