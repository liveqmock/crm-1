/**
 * 基础资料标签的公共方法
 */
function validateUpdateCustLabel(){
	Ext.getCmp('updateButton_id').setDisabled(false);
	var btnList = Ext.getCmp('btnList');
	if (btnList.items.length > 0 && !Ext.isEmpty(temp)) {
		var values = null;
		var flag = 0;
		for ( var i = 0; i < btnList.items.length; i++) {
			if (btnList.items.items[i].getText() == temp) {
				values = temp
			}
			if (btnList.items.items[i].isDisabled() == false) {
				flag += 1;
			}
		}
		if (flag == btnList.items.length) {
			//请选择一项
			MessageUtil.showMessage(i18n('i18n.label.haveNoChooseOne'));
			return false;
		}
		//请选中一项修改
		if (Ext.isEmpty(values)) {
			MessageUtil.showMessage(i18n('i18n.label.haveNoChooseOne'));
			return false;
		}
		return true;
	}else {
			//请选中一项修改
			MessageUtil.showMessage(i18n('i18n.label.haveNoChooseOne'));
			return false;
	};
};

//修改客户标签
function updateCustLabel() {
	var newLabelName = Ext.getCmp('updateLabel_id').getValue();//修改后的标签名字
	var oldLabelName = Ext.getCmp('oldLable_id').getValue();//修改前的标签名字
	var btnList = Ext.getCmp('btnList');
	//为空不让新增
	if(Ext.isEmpty(newLabelName)) {
		//标签名称不能为空
		MessageUtil.showErrorMes(i18n('i18n.label.labelNameIsNull'));
		return ;
	}
	if(newLabelName.indexOf(" ") > 0 ){
		//部门标签名字中间不能输入空格
		MessageUtil.showErrorMes(i18n('i18n.label.labelNameCanNotEnterASpace'));
		return ;
	}
	//大于六个字符不让新增
	if(newLabelName.length > 6) {
		//标签名称不能超过六个字符
		MessageUtil.showErrorMes(i18n('i18n.label.labelNameMaxLength'));
		return ;
	}
	if(oldLabelName == newLabelName){
		MessageUtil.showErrorMes(i18n('i18n.label.commitAfterUpdateCustLabel'));
		return ;
	}
	
	//新增的标签名字不能相同
	if(!Ext.isEmpty(btnList)) {
		for(var i=0;i<btnList.items.length;i++) {
			if( btnList.items.items[i].getText()== newLabelName) {
				//本部门客户标签名字不能相同
				MessageUtil.showErrorMes(i18n('i18n.label.deptLabelCanNotSame'));
				return ;
			}
		}
	}
	MessageUtil.showQuestionMes(i18n('i18n.label.updateCommonDeptLabel'), function(e) {
			Ext.getCmp('savaLableButtonId').setDisabled(true);
			if (e == 'yes') {// 点击是
				
				var updateCustLabelSuccess=function(result){
					//初始化界面从后台查询客户标签
					Ext.getCmp('updateLablesWin_id').close();
					MessageUtil.showInfoMes('修改成功');
					Ext.getCmp(deleteLabelId).setText(newLabelName);
					Ext.getCmp(deleteLabelId).setDisabled(false);
					
				}
				var updateCustLabelFail=function(result){
					if(!Ext.isEmpty(result.message)) {
						MessageUtil.showErrorMes(result.message);
					} else{
						MessageUtil.showErrorMes('修改成功');
					}
				}
				var params = {'label':{'deptId':currentUser.deptId,
					'labelName':newLabelName,'id':deleteLabelId}};
				labelData.updateCustLabel(params,updateCustLabelSuccess,updateCustLabelFail);
				
				
			} else {// 点击否
				Ext.getCmp('savaLableButtonId').setDisabled(false);
			}
		});
};

//删除客户标签
function deleteCustLabel() {
	Ext.getCmp('deleteButton_id').setDisabled(false);
	var btnList = Ext.getCmp('btnList');
	if (btnList.items.length > 0 && !Ext.isEmpty(temp)) {
		var values = null;
		var flag = 0;
		for ( var i = 0; i < btnList.items.length; i++) {
			if (btnList.items.items[i].getText() == temp) {
				values = temp
			}
			if (btnList.items.items[i].isDisabled() == false) {
				flag += 1;
			}
		}
		if (flag == btnList.items.length) {
			//请选择一项
			MessageUtil.showMessage(i18n('i18n.label.haveNoChooseOne'));
			return;
		}
		// 没选中不让删除
		if (Ext.isEmpty(values)) {
			MessageUtil.showMessage(i18n('i18n.label.haveNoChooseOne'));
			return;
		}
		MessageUtil.showQuestionMes(i18n('i18n.label.areYouSureDeleteLable'), function(e) {
			Ext.getCmp('deleteButton_id').setDisabled(false);
			if (e == 'yes') {// 点击是
				
				var labelId = null;
				var deleteCustLabelSuccess=function(result){
					//初始化界面从后台查询客户标签
					MessageUtil.showInfoMes('删除成功');
				}
				var deleteCustLabelFail=function(result){
					if(!Ext.isEmpty(result.message)) {
						MessageUtil.showErrorMes(result.message);
					} else{
						MessageUtil.showErrorMes('删除失败');
					}
				}
				var params = {};
				params.labelId = deleteLabelId;
				labelData.deleteCustLabelById(params,deleteCustLabelSuccess,deleteCustLabelFail);
				
				for ( var i = 0; i < btnList.items.length; i++) {
					if (btnList.items.items[i].getText() == temp) {
						btnList.remove(btnList.items.items[i]);
					}
				}
				if(Ext.getCmp('addButton_id').isVisible() == true) {
					Ext.getCmp('addButton_id').setDisabled(false);
				}
			} else {// 点击否
				Ext.getCmp(deleteLabelId).setDisabled(false);
				temp = null;
			}
		});
	} else {
		// 客户标签为空,直接点击删除 不让删除
		//请选中一项删除
		MessageUtil.showMessage(i18n('i18n.label.haveNoChooseOne'));
	}
};

/**
 * 新增标签
 */
function addCustLabel(form) {
	var btnList = Ext.getCmp('btnList');
	var labelName = form.findField('labelName').getValue();
	//标签ID
	var labelId = null;
	//验证新增的标签 业务验证
	if (validatorCustLabel(btnList,labelName)){
		Ext.getCmp('addButton_id').setDisabled(false);
		//超过十个 将新增按钮禁用
		if(btnList.items.length>9){
			Ext.getCmp('addButton_id').setDisabled(true);
		}
		return ;
	};
	
	var saveCustLabelSuccess=function(result){
		Ext.getCmp('addButton_id').setDisabled(false);
		if(Ext.isEmpty(result.labelId)) {
			//客户标签新增失败
			MessageUtil.showMessage(i18n('i18n.label.saveCustLabelFail'));
		}else {
			labelId = result.labelId;
			btnList.add(
					Ext.create('Ext.button.Button',{
						text:labelName,
						margin:'10 8',
						id:labelId,
						listeners:{
							click:function(ths,e,eOpts){
								temp = ths.getText();
								ths.setDisabled(true);
								deleteLabelId = ths.getId();
								//只让选中其中一个，点击了 显示灰色 是为了区分选中没选中
								for(var i=0;i<btnList.items.length;i++) {
										if(!(btnList.items.items[i].getText() == temp)
												&& !(btnList.items.items[i].isDisabled() == false)){
										btnList.items.items[i].setDisabled(false);
								}
									}
								}
							}
						}
					));
			MessageUtil.showInfoMes('新增成功');
		}
	}
	var saveCustLabelFail=function(){
		Ext.getCmp('addButton_id').setDisabled(false);
		if(!Ext.isEmpty(result.message)) {
			MessageUtil.showErrorMes(result.message);
		} else{
			MessageUtil.showErrorMes('新增失败');
		}
	}
	var params = {'label':{'deptId':currentUser.deptId,
					'labelName':labelName}};
	
	labelData.saveCustLabel(params,saveCustLabelSuccess,saveCustLabelFail);
	
	//超过十个 不让新增
	if(btnList.items.length==10){
		Ext.getCmp('addButton_id').setDisabled(true);
	}
};

/**
 * 初始化界面从后台查询客户标签
 */
function initCustLabels(labelList,labelCount){
	var btnList = Ext.getCmp('btnList');
	if(!labelCount > 0){
		return;
	}
	if(labelCount > 10) {
		//对不起,本部门客户标签加载超过10个！
		MessageUtil.showErrorMes(i18n('i18n.label.deptLabelBeyondMaxNumber'));
		return ;
	}
	if(labelCount == 10) {
		Ext.getCmp('addButton_id').setDisabled(true);
	}
	for(var i=0;i<labelList.length;i++) {
		btnList.add(Ext.create('Ext.button.Button',{
			text:labelList[i].labelName,
			margin:'10 8',
			id:labelList[i].id,
			listeners:{
				click:function(ths,e,eOpts){
					temp = ths.getText();
					deleteLabelId = ths.getId();
					ths.setDisabled(true);
					//只让选中其中一个，点击了 显示灰色 是为了区分选中没选中
					for ( var i = 0; i < btnList.items.length; i++) {
						if (!(btnList.items.items[i].getText() == temp) && 
								!(btnList.items.items[i].isDisabled() == false)) {
							btnList.items.items[i].setDisabled(false);
						}
					}
				}
			}
			}))
		}
};

/**
 * 初始化基础数据
 */
function initBasicData(){
	var labelList = null;
	var labelCount = null;
	
	var viewCustLabelSuccess=function(result){
		labelList = result.labelList;
		labelCount = result.labelCount;
		//初始化界面从后台查询客户标签
		initCustLabels(labelList,labelCount);
	}
	var viewCustLabelFail=function(){
		MessageUtil.showErrorMes('加载失败');
	}
	var params = {};
	params.deptId = top.User.deptId;
	labelData.initCustLabelStore(params,viewCustLabelSuccess,viewCustLabelFail);
	getCurrentUser();
};

/**
 * 验证新增的标签
 */
function validatorCustLabel(btnList,labelName) {
	//为空不让新增
	if(Ext.isEmpty(labelName)) {
		//标签名称不能为空
		MessageUtil.showErrorMes(i18n('i18n.label.labelNameIsNull'));
		return true;
	}
	if(labelName.indexOf(" ") > 0 ){
		//部门标签名字中间不能输入空格
		MessageUtil.showErrorMes(i18n('i18n.label.labelNameCanNotEnterASpace'));
		return true;
	}
	//大于六个字符不让新增
	if(labelName.length > 6) {
		//标签名称不能超过六个字符
		MessageUtil.showErrorMes(i18n('i18n.label.labelNameMaxLength'));
		return true;
	}
	//超过十个 不让新增
	if(btnList.items.length>9){
		MessageUtil.showErrorMes(i18n('i18n.label.deptLabelMaxNumber'));
		Ext.getCmp('addButton_id').setDisabled(true);
		return true;
	}
	//新增的标签名字不能相同
	if(!Ext.isEmpty(btnList)) {
		for(var i=0;i<btnList.items.length;i++) {
			if( btnList.items.items[i].getText()== labelName) {
				//本部门客户标签名字不能相同
				MessageUtil.showErrorMes(i18n('i18n.label.deptLabelCanNotSame'));
				return true;
			}
		}
	}
};

/**
 * 得到当前用户
 */
function getCurrentUser(){
	Ext.Ajax.request({
		url:'../common/queryUserInfo.action',
		async:false,
		jsonData:{},
		success:function(response){
			var result = Ext.decode(response.responseText);
			if(result.success){
				currentUser = result.user;
			}else{
				Ext.Msg.alert(i18n('i18n.label.tips'),result.message)
			}
		},
		failure:function(response){
			var result = Ext.decode(response.responseText);
			Ext.Msg.alert(i18n('i18n.label.tips'),result.message)
		}
	});
};