//合同管理 查询 界面
var memberManagerDataControl =  (CONFIG.get("TEST"))? new MemberManagerDataTest():new MemberManagerData();
//var memberManagerDataControl =  new MemberManagerData();

Ext.define('Ext.ux.PageComboResizer', {
	extend:Object,
	  pageSizes: [5, 10, 15, 20, 25, 30, 50],
	  prefixText: '每页显示 ',
	  postfixText: i18n('i18n.MemberManagerView.record'),

	  constructor: function(config){
	    Ext.apply(this, config);
	    this.callParent(arguments);
	  },
	  init : function(pagingToolbar) {
	    var ps = this.pageSizes;
	    var combo = Ext.create('Ext.form.ComboBox',{
	      typeAhead: true,
	      triggerAction: 'all',
	      lazyRender:true,
	      mode: 'local',
	      width:45,
	      store: ps,
	      listeners: {
	        select: function(c, r, s){
	     
	          	pagingToolbar.store.pageSize =r[0].data.field1;
	          	pagingToolbar.store.loadPage(1);
	        }
	      }
	    });
	    Ext.iterate(this.pageSizes, function(ps) {
	      if (ps==pagingToolbar.store.pageSize) {
	        combo.setValue (ps);
	        return;
	      }
	    });
		//将控件放到刷新控件的后面
	 	var inputIndex  =  pagingToolbar.items.indexOf(pagingToolbar.items.get('refresh'));
	    pagingToolbar.insert(++inputIndex,i18n('i18n.PotentialCustManagerView.searchEndTime'));
	    pagingToolbar.insert(++inputIndex, this.prefixText);
	    pagingToolbar.insert(++inputIndex, combo);
	    pagingToolbar.insert(++inputIndex, this.postfixText);
	    pagingToolbar.on({
	      beforedestroy: function(){
	        combo.destroy();
	      }
	    });

	  }
	});
Ext.override(Ext.grid.feature.Grouping,{
	//return matching preppedRecords
	getGroupRows: function(group, records, preppedRecords, fullWidth) {
	    var me = this,
	        children = group.children,
	        rows = group.rows = [],
	        view = me.view;
	    group.viewId = view.id;
	    Ext.Array.each(records, function(record, idx) {
	        if (Ext.Array.indexOf(children, record) != -1) {
	            rows.push(Ext.apply(preppedRecords[idx], {
	                depth: 1
	            }));
	        }
	    });
	    
        if('GOLD' == children[0].get('custGrade')){
        	group.color='#D8D8EB';
        }else if('PLATINUM' == children[0].get('custGrade')){
            group.color='#A6A6D2';
        }else if('DIAMOND' == children[0].get('custGrade')){
            group.color='#7373B9';
        }
        var groupName = group.name.split(',');
        var html='';
        Ext.each(groupName,function(str,i){
        	html = html+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+str;
        	if(2 == i){
        		html = html +'('+rows.length+')';
        	}
        });
        group.name = "<span style='display:inline-block;display:-moz-inline-box; width:200px;'>"+
        				html+"</span>";
//	    group.color = rows.length==1?'#00ff00':'#0000ff';
	    delete group.children;
	    group.fullWidth = fullWidth;
	    if (me.collapsedState[view.id + '-gp-' + group.name]) {
	        group.collapsedCls = me.collapsedCls;
	        group.hdCollapsedCls = me.hdCollapsedCls;
	    }

	    return group;
	}
})
Ext.define('MemberManagerPanel',{
	extend:'BasicPanel',
	parent:null,
	memberManagerConditionForm:null,//查询条件
	memberManagerResultGrid:null,  //查询结果
	memberManagerButtonPanel:null,//查询按钮面板
	memberManagerData:null,//数据Data
	
	layout:{
        type:'vbox',
        align:'stretch'
    },
	items:null,
	initComponent:function(){
		var me = this;
		var record = new SearchMemberConditionModel();
		me.memberManagerConditionForm = Ext.create('MemberManagerConditionForm',{'parent':me,'record':record,'memberManagerData':me.memberManagerData});
		me.memberManagerResultGrid = Ext.create('MemberManagerResultGrid',{'parent':me,'memberManagerData':me.memberManagerData});
		me.memberManagerButtonPanel = Ext.create('MemberManagerButtonPanel',{'parent':me,'memberManagerData':me.memberManagerData});
		me.formPanel = Ext.create('Ext.form.Panel', {
			id:'wrapForm'+'khgl-gdkhgl-01',					// 'wrapForm'+'khgl-qkgl-01'
			border : false,
			frame : true,
			//height:140,
			items : [{
				id : 'id1'+'khgl-gdkhgl-01',				// 'id1' + 'khgl-qkgl-01'
				name : 'first',
				height:24
			}, {
				id : 'id2'+'khgl-gdkhgl-01',				// 'id2' +'khgl-qkgl-01'
				name : 'last'
				,height:155					// height:160
			}]
		});
		me.items = [{
			xtype:'basicpanel',
			height:150,
			items:[me.memberManagerConditionForm]
		},{
			xtype:'basicpanel',
			height:36,
			items:[me.memberManagerButtonPanel]
		},{
			xtype:'basicpanel',
			flex:1,
			items:[me.memberManagerResultGrid]
		},{
			xtype:'basicpanel',
			height:100,
			items:[me.formPanel]
		}];
		this.callParent();
		me.memberManagerConditionForm.loadRecord(record);
		//默认部门
		DpUtil.defaultDept(me.memberManagerConditionForm,'deptId');
	}
});

/**
 * 按钮面板
 */
Ext.define('MemberManagerButtonPanel',{
	extend:'NormalButtonPanel',
	currentCity:null,
	memberManagerData:null,   //data层接口
	parent:null,
	initComponent:function(){
		this.items = this.getItems();
		//获得当前登录用户所在部门所在城市 成功方法
		var successFn = function(response){
			this.currentCity = response.currentCity;
		};
		//获得当前登录用户所在部门所在城市  失败方法
		var failFn = function(response){
			MessageUtil.showMessage(response.message);
		};
		memberManagerDataControl.acquireDeptCity({},successFn,failFn);
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'leftbuttonpanel',
			width:650,
			items:[
//			去掉客户360视图按钮
				{
				xtype:'button',
				text:i18n('i18n.MemberManagerView.addMemberInformation'),
				hidden:!isPermission('/customer/MemberManagerAddBtn.action'),
				scope:me,
				handler:me.createPotential
			},{
				text : i18n('i18n.PotentialCustManagerView.batchImport'),
				hidden:!isPermission('/customer/PotentialMassImportBtn.action'),
				xtype : 'button',
				scope : me,
				handler : me.batchImportPotential
			},{
				xtype:'button',
				text:i18n('i18n.MemberManagerView.maintenanceMemberInformation'),
				hidden:!isPermission('/customer/MemberManagerUBtn.action'),
				scope:me,
				handler:function(){
					me.maintainMemberInfo('update');
				}
			},{
				id:'Member_DeleteMemberBtn_id',
				xtype:'button',
				text:i18n('i18n.MemberManagerView.invilidMemberinfo'),//作废固定客户
				hidden:!isPermission('/customer/MemberManagerDBtn.action'),
				scope:me,
				handler:me.deleteMember
			}
			,{
				id:'Member_DeletePSBtn_id',
				xtype:'button',
				text:i18n('i18n.MemberManagerView.deletePSMemberinfo'),//删除潜散客
				hidden:!isPermission('/customer/MemberManagerDeletePSBtn.action'),
				scope:me,
				handler:me.deletePSMember
			},{
				id:'Member_DeptChangeBtn_id',
				xtype:'button',
				text:i18n('i18n.MemberManagerView.t_memDeptChange'),
				hidden:!isPermission('/customer/MemberManagerDeptChangeBtn.action'),
				scope:me,
				handler:me.changeMemberDept
			},{
				xtype:'button',
				text:i18n('i18n.MemberManagerView.t_viewMemDetail'),
				hidden:!isPermission('/customer/MemberManagerVBtn.action'),
				scope:me,
				handler:function(){
					me.maintainMemberInfo('view');
				}
			},{
				id:'Member_360ViewBtn_id',
				xtype:'button',
				text:'客户360视图',
				hidden:!isPermission('/customer/MemberManagerVBtn.action'),
				scope:me,
				handler:function(){
					var me = this;
					var grid = me.parent.memberManagerResultGrid;
					var selection=grid.getSelectionModel().getSelection();
					if (selection.length != 1) {
						MessageUtil.showMessage( i18n('i18n.PotentialCustManagerView.operation_message'));
						return;
					}
					CustviewUtil.openSimpleCustview(selection[0].get('custNum'));
				}
			}]
		},{
			xtype:'middlebuttonpanel'
		},{
			xtype:'rightbuttonpanel',
			width:150,
			items:[{
				xtype:'button',
				text:i18n('i18n.PotentialCustManagerView.search'),
				hidden:!isPermission('/customer/MemberManagerSBtn.action'),
				scope:me,
				handler:me.searchScatterMemberList
				}]
		}];
	},
	
	//潜客新增
	createPotential:function(){
		var newPotentialCust = Ext.create('MemberCustomerModel');
		//设置当前登录用户所在部门所在城市
		newPotentialCust.set('cityId',currentCity.id);
		newPotentialCust.set('provinceId',currentCity.provinceId);
		newPotentialCust.set('custGroup','PC_CUSTOMER');
		Ext.create('MemberCustEditWindow',{'member':newPotentialCust.data,
											'currentCity':currentCity,
												'y':30,//距离页面最顶端 像素距离
						   					   'viewStatus':'new',
						   					   'channelType':'addPotential',
						   					   'custStatus':null}).show();
	},
	//批量导入
	batchImportPotential:function(){
		Ext.create('UploadAttachmentWin',{'potentialCust':this.potentialCust}).show();
	},
	//变更会员归属部门
	changeMemberDept:function(){
		var me = this;
		var grid = me.parent.memberManagerResultGrid;
		var selection=grid.getSelectionModel().getSelection();
		if (selection.length != 1) {
			MessageUtil.showMessage( i18n('i18n.PotentialCustManagerView.operation_message'));
			return;
		}
		if(top.User.deptId == selection[0].get('deptId')){
			MessageUtil.showMessage( i18n('i18n.MemberManagerView.t_cannotChangeDept'));
			return;
		}
		//审批中的会员信息不能会员归属部门变更
		if ('1' == selection[0].get('status')) {
			MessageUtil.showMessage( i18n('i18n.MemberManagerView.t_memberInfoIsApply'));
			return;
		}
		//作废的客户不能 做 归属部门变更
		if ('2' == selection[0].get('status')) {
			MessageUtil.showMessage('作废的客户不能 做归属部门变更！');
			return;
		}
		Ext.create('ChangeMemberDeptWin',{'memberData':me.memberManagerData,
						'updateMemberParent':me.parent,
						'operateGrid':me.parent.memberManagerResultGrid,
						'changeMemberDeptRecord':me.member2ChangeMemberDept(selection[0]),
   					   'viewStatus':'NEW'}).show();
	},
	//会员查询
	searchScatterMemberList:function(){
		var me = this;
		if(!me.validateCondition()){
			MessageUtil.showMessage(i18n('i18n.MemberUpgradeView.message_notAllNull'));
			return;
		}
		if(!me.parent.memberManagerConditionForm.getForm().isValid()){
			MessageUtil.showMessage(i18n('i18n.MemberManagerView.pleaseCheckTheInputQueryFillInTheCorrect'));
			return;
		}
		me.memberManagerData.getMemberSearchStore().loadPage(1);
	},
	//校验是否查询条件都为空
	validateCondition:function(){
		var me = this;
		var form = me.parent.memberManagerConditionForm;
		var flag = false;
		form.getForm().getFields().each(function(field){
			if(!(DpUtil.isEmpty(field.getValue()))){
				flag = true;
			}
		});
		return flag;
	},
	//维护会员信息/产看详情；viewValue :产看详情"view"，护会员信息"update"
	maintainMemberInfo:function(viewValue){
		var me = this;
		var grid = me.parent.memberManagerResultGrid;
		var selection=grid.getSelectionModel().getSelection();
		if (selection.length != 1) {
			MessageUtil.showMessage( i18n('i18n.PotentialCustManagerView.operation_message'));
			return;
		}
//		if('update' == viewValue){
//			if(top.User.deptId != selection[0].get('deptId')){
//				MessageUtil.showMessage( i18n('i18n.MemberManagerView.t_cannotUpdateMember'));
//				return;
//			}
//		}
		//只有收银员可以修改作废客户。
		var aBtn = isPermission('/customer/accountRecognizedABtn.action');
		var uBtn = isPermission('/customer/accountRecognizedUBtn.action');
		var dBtn = isPermission('/customer/accountRecognizedDBtn.action');
		var isP = aBtn&&uBtn&&dBtn;
		if('2' == selection[0].get('status') && 'update' == viewValue && !isP){
			MessageUtil.showMessage('该客户已作废，只有收银员可以修改。');
			return;
		}
		me.getMemberEditWin(selection[0],me.memberManagerData,viewValue,me.parent);
	},
	member2ChangeMemberDept:function(memberRecord){
		var me = this;
		var changeMemberDept = Ext.create('ChangeMemberDeptModel');
		changeMemberDept.set('memberId',memberRecord.get('custId'));
		changeMemberDept.set('memberNumber',memberRecord.get('custNum'));
		changeMemberDept.set('fromDeptId',memberRecord.get('deptId'));
		changeMemberDept.set('fromDeptName',memberRecord.get('deptName'));
		changeMemberDept.set('toDeptId',memberRecord.get('toDeptId'));
		changeMemberDept.set('toDeptName',memberRecord.get('toDeptName'));
		return changeMemberDept;
	},
	//得到会员编辑界面
	getMemberEditWin:function(record,memberManagerData,viewStatus,updateMemberParent){
		//通过id重新查询成功回调函数
		var searchByIdSuccess = function(response){
			var memberInfo = response.member;
			//已作废客户 并且是收银员账号操作则 仅可以修改 会员账号信息
			var custStatus = null;
			if ('update' == viewStatus&&'2' == memberInfo.custStatus&&memberInfo.finOver) {
				MessageUtil.showErrorMes(i18n('i18n.customer.customerFinOver'));
				return;
			}
			//作废客户直接只能修改账户信息
			if('2' == memberInfo.custStatus && 'update' == viewStatus){
				viewStatus='view';
				custStatus = memberInfo.custStatus;
			}
			Ext.create('MemberCustEditWindow',{'member':memberInfo,
												'updateMemberParent':updateMemberParent,
												'y':30,//距离页面最顶端 像素距离
						   					   'viewStatus':viewStatus,
						   					   'custStatus':custStatus}).show();
		};
		//通过id重新查询失败调函数
		var searchByIdFail = function(response){
			MessageUtil.showErrorMes(response.message);
		};
		var params = {'searchCustCondition':{'memberId':record.get('custId'),
											'deptId':record.get('deptId')}};
		if('update' == viewStatus){
			if('2' == record.get('status')){//作废客户
				params.searchCustCondition.custStatus=record.get('status');
				memberManagerData.acquireInvalidMemberById(params,searchByIdSuccess,searchByIdFail);
			}else{
				memberManagerData.acquireMember4UpdateById(params,searchByIdSuccess,searchByIdFail);
			}
		}else if('view' == viewStatus){
			if('2' == record.get('status')){//作废客户
				memberManagerData.acquireInvalidMemberById(params,searchByIdSuccess,searchByIdFail);
			}else{
				memberManagerData.acquireMemberById(params,searchByIdSuccess,searchByIdFail);
			}
		}
	},
	//作废会员信息
	deleteMember:function(button){
		var me = this;
		var grid = me.parent.memberManagerResultGrid;
		var selection=grid.getSelectionModel().getSelection();
		if (selection.length != 1) {
			MessageUtil.showMessage( i18n('i18n.PotentialCustManagerView.operation_message'));
			return;
		}
		//只有归属部门才可作废会员deptId
		if(!me.isBelongDept(selection[0],true,'只有归属部门可以执行作废操作！')){return;}
		if('2' == selection[0].get('status')){MessageUtil.showMessage('该客户已作废！');return;}
		// 执行会员作废操作
		var delSuccess = function(response){
			grid.store.loadPage(1);
			MessageUtil.showInfoMes('操作成功！');
		};
		//通过id重新查询失败调函数
		var delFail = function(response){
			MessageUtil.showErrorMes( response.message);
		};
		var params = {'member':{'id':selection[0].get('custId'),'versionNumber':selection[0].get('versionNumber')}};
		MessageUtil.showQuestionMes('该客户作废后，不可恢复，请谨慎操作，是否确认作废？', function(e) {
			if (e == 'yes') {
				me.memberManagerData.deleteMember(params,delSuccess,delFail);
			}
		});
	},
	//删除潜散客
	deletePSMember:function(button){
		var me = this;
		var grid = me.parent.memberManagerResultGrid;
		var selection=grid.getSelectionModel().getSelection();
		if (selection.length != 1) {
			MessageUtil.showMessage( i18n('i18n.PotentialCustManagerView.operation_message'));
			return;
		}
		//只有归属部门才可作废会员deptId
		if(!me.isBelongDept(selection[0],true,'只有归属部门可以执行作废操作！')){return;}
		if('2' == selection[0].get('status')){MessageUtil.showMessage('该客户已作废！');return;}
		// 执行会员作废操作
		var delSuccess = function(response){
			grid.store.loadPage(1);
			MessageUtil.showInfoMes('操作成功！');
		};
		//通过id重新查询失败调函数
		var delFail = function(response){
			MessageUtil.showErrorMes( response.message);
		};
		var params = {'member':{'id':selection[0].get('custId'),'versionNumber':selection[0].get('versionNumber')}};
		MessageUtil.showQuestionMes('该客户作废后，不可恢复，请谨慎操作，是否确认作废？', function(e) {
			if (e == 'yes') {
				me.memberManagerData.deleteMember(params,delSuccess,delFail);
			}
		});
	},
	//判断客户是否在疑重列表中
	
	//校验是否归属部门flag：false表示判断不是归属部门呢，true表示判断是归属部门
	isBelongDept:function(record,flag,message){
		var me = this;
		if(!flag?(top.User.deptId == record.get('deptId')):(top.User.deptId != record.get('deptId'))){
			MessageUtil.showMessage(message);
			return false;
		}
		return true;
	},
	//校验是否是审批中
	isApplying:function(record,message){
		var me = this;
		if ('1' == record.get('status')) {
			MessageUtil.showMessage(message);
			return false;
		}
		return true;
	}
});

/**
 * 上传附件弹出框
 */
Ext.define('UploadAttachmentWin',{
	extend:'PopWindow',
	title:i18n('i18n.PotentialCustManagerView.messageTip'),
	layout:{
		type:'vbox',
		align:'stretch'
	},
	width:530,
	height:150,
	potentialCust:null,//potentialCust data层
	initComponent:function(){
		var me = this;
		me.items = [
		{
			xtype:'titlepanel',
			defaultType:'displayfield',
			layout:'column',
			items:[{value: i18n('i18n.PotentialCustManagerView.pleaseInputDiverAttachment')},
			       {width:100},
			       {value:i18n('i18n.PotentialCustManagerView.pressHereDown')
						}]
		},
		{
			xtype:'basicformpanel',
			flex:1,
			layout:{
				type : 'hbox'
			},
			defaults : {
				margins : '0 5 0 0'
			},
			items:[{
				xtype:'filefield',
				name:'file',
				fieldLabel:'导入文件',
				labelWidth:60,
				buttonText:i18n('i18n.ChangeContactAffiliatedRelationView.view'),
				flex:3
			},{
				text:i18n('i18n.ContractEditView.contractId'),
				name:'sourceId',
				xtype:'hiddenfield'
			},{
				text:i18n('i18n.ChangeContactAffiliatedRelationView.sourceType'),
				name:'type',
				xtype:'hiddenfield',
				value:'EXCEL'
			}]
		}];
		me.fbar = me.getFbar();
		this.callParent();
	},
	getFbar:function(){
		var me = this;
		return [{
			text:i18n('i18n.ChangeContactAffiliatedRelationView.button_upload'),
			xtype:'button',
			scope:me,
			handler:me.uploadFile
		},{
			text:i18n('i18n.ManualRewardIntegralEditView.b_cance'),
			xtype:'button',
			handler:function(){
				me.close();
			}
		}];
	},
	//文件上传
	uploadFile:function(){
		var me = this;
		var form = me.down('form');
		form.submit({
                    url: 'importPentent.action',
                    waitMsg: i18n('i18n.PotentialCustManagerView.diverImportingPleaseWait'),
                    success: function(form, response) {
                    	var result = response.result;
                    	if(result.success){
                    		var message = result.message;
                    		var warnWin = null;
                    		me.close();
                    		warnWin = Ext.create('Ext.window.Window',{
                    			title:i18n('i18n.PotentialCustManagerView.theEndOfImport'),
                    			width:305,
                    			height:150,
                    			cls:'warningwin',
                    			layout:'fit',
                    			items:[{
	                    				xtype : 'textareafield',
	                    				value:message,
										readOnly : true
                    			}],
							    dockedItems: [{
								    xtype: 'toolbar',
								    dock: 'bottom',
								    ui: 'footer',
								    layout: {
						                pack: 'center'
						            },
								    items: [
								        { xtype: 'component', flex: 1 },
								        { xtype: 'button', text:'确定',
							    		handler:function(){warnWin.close();}
								        }]
								}]
                    		});
                    		warnWin.show();
						}else{
                       		MessageUtil.showMessage( result.message);
                       		me.close();
						}
                    },
                 failure:function(form, response){
                 	var result = response.result;
                 	if(!result.success){
                       	MessageUtil.showMessage( result.message);
					}
                 }
                });
	}
});

/**
 * 查询条件
 */
Ext.define('MemberManagerConditionForm',{
	extend:'SearchFormPanel',
	id:'memberManagerConditionFormId',
	parent:null,
	record:null,
	layout:{
		type:'table',
		columns:3
	},
	defaultType:'dptextfield',
	memberManagerData:null,
	initComponent:function(){
		var me = this;
		me.defaults = me.getDefaultsContainer();
		me.items = me.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'belongdeptcombox',
			fieldLabel:i18n('i18n.ScatterUpgradeView.belongdeptName'),
			maxLength:50,
			functionName :'MemberFunction',
			name:'deptId'
		},{
			fieldLabel:i18n('i18n.MemberCustEditView.custNo'),
			maxLength:40,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',40),
			name:'custNumber'
		},{
			fieldLabel:i18n('i18n.PotentialCustManagerView.customerName'),
			maxLength:80,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',80),
			name:'custName'
		},{
			fieldLabel:i18n('i18n.MemberCustEditView.custLevel'),
			name:'custGrad',
			maxLength:10,
			xtype : 'combobox',
			store:me.memberManagerData.getMemberGradeStore(),
			queryMode:'local',
            forceSelection:true,
			displayField:'codeDesc',
			valueField:'code'
		},{
			fieldLabel:i18n('i18n.MemberCustEditView.contactNo'),
			maxLength:40,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',40),
			name:'linkManNumber'
		},{
			fieldLabel:i18n('i18n.ManualRewardIntegralEditView.name'),
			maxLength:80,
			maxLengthText:i18n('i18n.ManualRewardIntegralEditView.m_maxLength',80),
			name:'linkManName'
		},{
			fieldLabel:i18n('i18n.MemberCustEditView.mobileNo'),
//			maxLength:11,
			regex : DpUtil.linkWayLimit('M'),
			regexText:i18n('i18n.MemberCustEditView.pleaseInputCorrectMobilePhone'),
			name:'mobile'
		},{
			fieldLabel:i18n('i18n.MemberCustEditView.telephoneNo'),
			regex : DpUtil.linkWayLimit('T'),
			regexText:i18n('i18n.MemberManagerView.pleaseEnterTheCorrectPhoneNumber'),
//			maxLength:16,
			name:'telePhone'
		},{
			name:'custSource',
			xtype:'combo',
			fieldLabel:i18n('i18n.PotentialCustManagerView.custSource'),
			queryMode:'local',
			displayField:'codeDesc',
			valueField:'code',
			store:me.memberManagerData.getCustSourceStore(),
			forceSelection:true,
			listeners:{
				change:DpUtil.changeCombText
			}
		},{
			fieldLabel:i18n('i18n.MemberCustEditView.isKeyCustomer'),//是否大客户
			xtype:'combo',
			name:'isKeyCustomer',
			queryMode:'local',
			displayField:'name',
			valueField:'value',
			store:me.memberManagerData.getIsKeyCustomerStore(),
			forceSelection:true,
			listeners:{
				change:DpUtil.changeCombText
			}
		},{
			fieldLabel:i18n('i18n.MemberCustEditView.custGroup'),//客户性质（潜散客、固客）
			name:'custGroup',
			xtype:'dpcombobox',
			store:me.memberManagerData.getCustGroupStore(),
			displayField:'codeDesc',
			valueField:'code',
			queryMode:'local',
			forceSelection:true,
			listeners:{
				change:DpUtil.changeCombText
			}
		},{
			fieldLabel:i18n('i18n.MemberCustEditView.custCategory'),//客户类别（零担快递）
			name:'custCategory',
			xtype:'dpcombobox',
			store:me.memberManagerData.getCustCategoryStore(),
			displayField:'codeDesc',
			valueField:'code',
			queryMode:'local',
			forceSelection:true,
			listeners:{
				change:DpUtil.changeCombText
			}
		}
		,{
			xtype:'customertrade',//行业
			width:500,
			colspan:2,
			trade_labelWidth:70,
			trade_width:250,
			userType:'SEARCH',
			trade_name:'tradeId',
			trade_margin:'-10 0 0 -10',
			trade_fieldname:i18n('i18n.MemberCustEditView.custIndustry'),
			second_trade_labelWidth:70,
			second_trade_width:250,
			second_trade_margin:'-10 0 0 0',
			second_trade_name:'secondTrade',
			second_trade_fieldname:i18n('i18n.secondTrade.secondTrade')
		}
		];
	},
	//增加监听事件
	getDefaultsContainer:function(){
		var me = this;
		return {
			labelWidth : 70,
			width : 250,
			enableKeyEvents:true,
			listeners:{
				scope : me,
				keypress : me.keypressEvent,
				change : me.changeEvent
			}
		};
	},
	//监听按键事件
	keypressEvent:function(field,event){
		var me = this;
		if(event.getKey() == Ext.EventObject.ENTER){
			me.parent.memberManagerButtonPanel.searchScatterMemberList();
    	}
	},
	//监听change事件
	changeEvent:function(field,newValue){
		var me = this;
		//如果是数据字典或所属部门组件 则显示只选
		if(('belongdeptcombox' == field.getXType() || 'combobox' == field.getXType()) 
			&& Ext.isEmpty(newValue)){
			field.setValue(null);
		}else{
			if(field.getName()=='mobile'){
				DpUtil.autoChangeMobileLength(field,newValue);
			}
		}
	}
});

/**
 * 会员查询结果
 */
Ext.define('MemberManagerResultGrid',{
	extend:'SearchGridPanel',
	parent:null,
	memberManagerData:null,//数据Data
	autoScorll:true,
	initComponent:function(){
		var me = this;
		me.listeners = me.getMylistener();
		me.store = me.memberManagerData.initMemberSearchStore(me.beforeLoadScatterFn);
		me.selModel = Ext.create('Ext.selection.CheckboxModel',{'mode':'SINGLE',
			listeners: {
	            selectionchange: function(sm, selections) {
	            	
	            	if(selections.length == 1){
	            		var custGroupPSR = selections[0].data.custGroupPSR;
	            		if(custGroupPSR == 'RC_CUSTOMER'){
	            			Ext.getCmp('Member_DeletePSBtn_id').setDisabled(true);//删除潜散客按钮
	            			Ext.getCmp('Member_DeleteMemberBtn_id').setDisabled(false);//作废固定客户按钮
	            			Ext.getCmp('Member_360ViewBtn_id').setDisabled(false);//删除潜散客按钮
	            			Ext.getCmp('Member_DeptChangeBtn_id').setDisabled(false);
	            		}else{
	            			Ext.getCmp('Member_DeletePSBtn_id').setDisabled(false);//删除潜散客按钮
	            			Ext.getCmp('Member_DeleteMemberBtn_id').setDisabled(true);//作废固定客户按钮
	            			Ext.getCmp('Member_360ViewBtn_id').setDisabled(true);//删除潜散客按钮
	            			Ext.getCmp('Member_DeptChangeBtn_id').setDisabled(false);
	            			if(custGroupPSR == 'PC_CUSTOMER'){
	            				Ext.getCmp('Member_DeptChangeBtn_id').setDisabled(true);//潜客不允许变更归属部门
	            			}
	            		}
	            		
            		}
	            }
	        }
			
		});//只允许单选
		me.dockedItems = me.getMyDockedItems();
		me.columns = me.getColumns();
		this.callParent();
	},
	features: [{
       ftype: 'groupingsummary',
       startCollapsed:true,
       groupHeaderTpl: '<div style="background-color:{color}">{name}</div>',
       hideGroupedHeader: true,
       enableGroupingMenu: false
    }],
    getMylistener:function(){
    	var me = this;
    	return {
		   scope:me,
		   itemdblclick : function(grid,record){
		   		me.parent.memberManagerButtonPanel.getMemberEditWin(record,me.memberManagerData,'view',me.parent);
		   },
		   scrollershow: function(scroller) {
		    	if (scroller && scroller.scrollEl) {
					scroller.clearManagedListeners(); 
					scroller.mon(scroller.scrollEl, 'scroll', scroller.onElScroll, scroller); 
				}
			}
		};
    },
	getColumns:function(){
		var me = this;
		return [{
			header:i18n('i18n.MemberManagerView.customerGrouping'),
			width:900,
			dataIndex:'custGroup'
		},{
			header:i18n('i18n.ChangeContactAffiliatedRelationView.custId'),
			hidden:true,
			dataIndex:'custId'
		},{
			header:i18n('i18n.MemberCustEditView.custNo'),
			dataIndex:'custNum',
			hidden:true
		},{
			header:i18n('i18n.PotentialCustManagerView.customerName'),
			dataIndex:'custName',
			hidden:true
		},{
			header:i18n('i18n.MemberCustEditView.custLevel'),
			dataIndex:'custGrade',
			hidden:true
		},{
			header:i18n('i18n.MemberCustEditView.contactNo'),
			dataIndex:'contactNum',
			width:160
//			flex: 1 
		},{
			header:i18n('i18n.PotentialCustManagerView.contactName'),
			dataIndex:'contactName',
			width:150
//			flex: 1 
		},{
			header:i18n('i18n.MemberCustEditView.mobileNo'),
			dataIndex:'mobileNum',
			width:150
//			flex: 1 
		},{
			header : i18n('i18n.MemberCustEditView.telephoneNo'),
			dataIndex:'telNum',
			width:150
//			flex: 1 
		},{
			header : i18n('i18n.ScatterUpgradeView.belongDeptId'),
			hidden:true,
			dataIndex:'deptId'
		},{
			header : i18n('i18n.ScatterUpgradeView.belongdeptName'),
			dataIndex:'deptName',
			width:180
//			flex: 1 
		},{
			header : i18n('i18n.MemberManagerView.approvalStatus'),
			hidden:true,
			dataIndex:'status'
		},{
			header : i18n('i18n.MemberCustEditView.isKeyCustomer'),
			hidden:true,
			dataIndex:'isKeyCustomer'
		},{
			header : i18n('i18n.MemberCustEditView.custGroup'),//客户性质
			dataIndex:'custGroupPSR',
			hidden:true
//			flex: 1 
		},{
			header : i18n('i18n.MemberCustEditView.custCategory'),//客户类别
			hidden:true,
			dataIndex:'custCategory'
		}];
	},
	//分页条
	getMyDockedItems :function(){ 
		var me = this;
		return [ {
			xtype : 'pagingtoolbar',
			plugins:[Ext.create('Ext.ux.PageComboResizer')],
			store : me.store,
			dock : 'bottom',
			displayInfo : true
		} ];
	},
	//beforeLoad方法
	beforeLoadScatterFn:function(store, operation, eOpts){
		var searchConditionForm = Ext.getCmp('memberManagerConditionFormId');
		if(searchConditionForm!=null){
			searchConditionForm.getForm().updateRecord(searchConditionForm.record);
			//设置请求参数
			var searchCustCondition = searchConditionForm.record.data;
			var searchParams = {
					// 会员所属部门ID
					'searchCustCondition.deptId':searchCustCondition.deptId,
					// 会员客户编码
					'searchCustCondition.custNumber':searchCustCondition.custNumber,
					// 客户名称（企业或个人）
					'searchCustCondition.custName':searchCustCondition.custName,
					// 客户等级
					'searchCustCondition.custGrad':searchCustCondition.custGrad,
					// 客户等级
					'searchCustCondition.custSource':searchCustCondition.custSource,
					// 联系人编码
					'searchCustCondition.linkManNumber':searchCustCondition.linkManNumber,
					// 联系人姓名
					'searchCustCondition.linkManName':searchCustCondition.linkManName,
					// 手机号码(联系人手机号)
					'searchCustCondition.mobile':searchCustCondition.mobile,
					//固定电话(联系人固定电话)
					'searchCustCondition.telePhone':searchCustCondition.telePhone,
					//是否大客户
					'searchCustCondition.fisKeyCustomer':searchCustCondition.isKeyCustomer,
				     //客户性质（潜散客、固定客户）
					'searchCustCondition.custGroup':searchCustCondition.custGroup,
					//客户类别(零担快递)
					'searchCustCondition.custCategory':searchCustCondition.custCategory,
					//客户类别(零担快递)
					'searchCustCondition.tradeId':searchCustCondition.tradeId,
					//客户类别(零担快递)
					'searchCustCondition.secondTrade':searchCustCondition.secondTrade
					
			};
			Ext.apply(operation,{
				params : searchParams
				}
			);	
		}
	}
});

Ext.onReady(function(){
	var params =[
      			// 目标级别i18n('i18n.ChangeContactAffiliatedRelationView.nowLevelCustLevel'),会员等级
      			'MEMBER_GRADE',
      			// 客户行业
      			'TRADE',
      			// 客户属性
      			'CUSTOMER_NATURE',
      			// 公司性质
      			'COMP_NATURE',
      			// 客户类型
      			'CUSTOMER_TYPE',
      			// 客户潜力类型
      			'COOPERATION_INTENTION',
//      			'CUST_POTENTIAL',
      			// 上一年公司规模
      			'FIRM_SIZE',
      			//客户来源
      			'CUST_SOURCE',
      			// 信用等级
      			'CREDIT_GRADE',
      			// 地址类型
      			'ADDRESS_TYPE',
      			//潜力类型潜力
      			'CARGO_POTENTIAL',
      			// 联系人类型 已取消
      			// 物流决定权
      			'LOGIST_DECI',
      			// 付款方式
      			'PAY_WAY',
      			// 来源渠道',偏好渠道
      			'PREFERENCE_CHANNEL',
      			// 偏好服务
      			'PREFERENCE_SERVICE',
      			// 发票要求
      			'BILL_REQUIRE',
      			//账户性质
      			'ACCOUNT_NATURE',
      			//账户用途
      			'ACCOUNT_USE',
      			//性别
      			'GENDER',
      			//是否接受营销信息
      			'MARKETINFO',
      			//联系人证件类型
      			'CARDTYPECON',
      			//客户类别
      			'CUST_CATEGORY',
      			//税务登记号证件类型
      			'CARDTYPECUST',
      			//证件类型新增和修改(大区总以上权限)
      			'CARDTYPECON_NOTVIEW',
      			//证件类型新增和修改
      			'CARDTYPECON_AU',
      			//客户性质（潜散客固客
      			'CUST_TYPE',
      			'OPERATE_TYPE'
      			];
    initDataDictionary(params);
  //初始化提示
    Ext.QuickTips.init();
    Ext.tip.QuickTipManager.init();
	//初始化自定义的数据字典--联系人类型
	memberManagerDataControl.initSelfDefineLinkmanType(DataDictionary);
	//显示会员修改 内容描述
	new ModifyMemberControl().getModifyMember();
	Ext.create('Ext.container.Viewport',{
		layout:'fit',
//			autoScroll:true,
		items:[Ext.create('MemberManagerPanel',{'memberManagerData':memberManagerDataControl})]
	});
	Ext.create('Depon.Lib.oDocHelper', {
		helpDoc:{				// 帮助实体：
			windowNum:'khgl-gdkhgl-01'	// TODO:帮助文档的ID	belongModule(首字母缩写)+belongMenu（首字母缩写）+windowNum(弹窗编号)+首编时间戳
			,active:true			// 记录操作员操作，是否选择了”隐藏帮助“；
		}
	});
});


