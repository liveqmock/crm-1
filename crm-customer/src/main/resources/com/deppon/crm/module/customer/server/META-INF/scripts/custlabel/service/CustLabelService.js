var labelName = null;//标签名字
var labelId = null;//标签ID
var currentUser = null;//登录人
var custLabels = new Array();//标签数组
var custLabelModel = null;//客户标签model
var label = null;//基础资料标签
var status = null;//操作状态
/**
 * 初始化基础数据
 * status 状态
 * customerData 所传的客户数据
 */
function initBasicData(status,custType,custData,channelType,deptId){

	//传过来的客户 潜客 散客数据
	var custData = custData;
	var custType = custType; //客户类型
	status = status;//状态
	
	var labelList = null;
	var labelCount = null;
	
	var viewCustLabelSuccess=function(result){
		labelList = result.labelList;
		labelCount = result.labelCount;
		if(!Ext.isEmpty(custData.data.custLabels)){
			for (var i =0; i<custData.data.custLabels.length;i++) {
				custLabels.push(custData.data.custLabels[i]);
			}
		}
		//初始化界面从后台查询客户标签
		initCustLabels(labelList,labelCount,custData,custType,status,channelType,custLabels);
		//加载潜客 或者 散客 或者客户的数据
		loadData(status,custType,custData,channelType,custLabels);
	}
	var viewCustLabelFail=function(){
		MessageUtil.showErrorMes('加载失败');
	}
	var params = {};
	params.deptId = deptId;
	initCustLabelStore(params,viewCustLabelSuccess,viewCustLabelFail);
	getCurrentUser();
};
/**
 * 初始化 客户基础资料标签
 * @param params
 * @param viewCustLabelSuccess
 * @param viewCustLabelFail
 */
function initCustLabelStore(params,viewCustLabelSuccess,viewCustLabelFail) {
		DpUtil.requestAjax('searchLabel.action',params,viewCustLabelSuccess,viewCustLabelFail);
}
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
				Ext.Msg.alert(i18n('i18n.label.tips'),result.message);
			}
		},
		failure:function(response){
			var result = Ext.decode(response.responseText);
			Ext.Msg.alert(i18n('i18n.label.tips'),result.message);
		}
	});
};
/**
 * 重选部门之后，将原有的待选和已选标签清空掉
 */
function clearLabels(){
	//清除待选标签
	var btnList = Ext.getCmp('btnList');
	var btnCount = btnList.items.length;
	if(btnCount>1){
		for(var i=1;i<btnCount;i++){
			btnList.remove(btnList.items.items[1]);
		}
	}
	//清除已选标签
	var custLabelList = Ext.getCmp('CustLabelList');
	var custCount = custLabelList.items.length;
	if(custCount>1){
		for(var i=1;i<custCount;i++){
			custLabelList.remove(custLabelList.items.items[1]);
		}
	}
}
/**
 * 初始化界面从后台查询基础资料标签
 * labelList 标签库
 * labelCount 标签库总数量
 * custData 操作的数据源 潜散客 或者固定客
 * custType 客户类型
 * status 状态
 * custLabels 临时数组
 */
function initCustLabels(labelList,labelCount,custData,custType,status,channelType,custLabels){
	var btnList = Ext.getCmp('btnList');
	if(!labelCount > 0){
		return;
	}
	if(labelCount > 10) {
		//对不起,本部门客户标签加载超过10个！
		MessageUtil.showErrorMes(i18n('i18n.label.deptLabelBeyondMaxNumber'));
		return ;
	}
	for(var i=0;i<labelList.length;i++) {
		btnList.add(Ext.create('Ext.button.Button',{
			text:labelList[i].labelName,
			margin:'1 2',
			id:labelList[i].id,
			disabled:'view'==status||('new'==status &&'immediate'==channelType)?true:false,
			listeners:{
				click:function(ths,e,eOpts){
					  labelName = ths.getText();//标签名字
					  labelId = ths.getId();//标签ID
					 ths.setDisabled(true);
					// 将基础资料的标签选择到 散客 或者 潜客 固定客户里面去
					addLabelToCustLabel(labelName,labelId,custData,custType,status,custLabels);
				}
			}
		}))
		}
};

/**
 * 将基础资料的标签选择到 散客 或者 潜客 固定客户里面去
 *  * labelName 标签名字
 * labelId 标签ID
 * custData 操作的数据源 潜散客 或者固定客
 * custType 客户类型
 * status 状态
 * custLabels 临时数组
 */
function addLabelToCustLabel(labelName,labelId,custData,custType,status,custLabels) {
	if(Ext.isEmpty(labelName) || Ext.isEmpty(labelId)) {
		Ext.Msg.alert(i18n('i18n.label.tips'),i18n('i18n.label.parasAreNull'));
		return ;
	}
	var custLabelList = Ext.getCmp('CustLabelList');
	
	//封装数据
    custLabelModel = new CustLabelModel();
    label = new LabelModel();
    label.data.id = labelId;
    label.data.deptId = currentUser.deptId;
    label.data.labelName = labelName;
    custLabelModel.data.label = label.data;
    custLabelModel.data.custType=custType;
    
    if('update'==status) {
    	custLabelModel.data.custId = custData.data.id;
    }
    custLabels.push(custLabelModel.data);
	custLabelList.add(
		Ext.create('LabelPanel',{
		labelText:labelName,
		labelId:labelId,
		margin:'2 5',
		removeHanlder:function(){
			//删除的时候 干掉前台传给后台的参数
			//封装数据
			var panel= this.up('panel');
		    custLabelModel = new CustLabelModel();
		    label = new LabelModel();
		    label.data.id = panel.labelId;
		    label.data.labelName = panel.labelName;
		    label.data.deptId = currentUser.deptId;
		    custLabelModel.data.label = label.data;
		    custLabelModel.data.custType=custType;
		    for(var i=0;i<custLabels.length;i++){
		    	if(custLabels[i].label.id == panel.labelId){
		    		Ext.Array.remove(custLabels,custLabels[i]);
		    	}
		    }

			var me= this;
			Ext.getCmp(panel.labelId).setDisabled(false);
			Ext.getCmp('CustLabelList').remove(me.up('panel'));
		}
	}))
}

/**
 * 创建页面时加载数据
 */
function loadData(status,custType,custData,channelType,custLabels) {
	if(Ext.isEmpty(status) || Ext.isEmpty(custType) ||  Ext.isEmpty(custData)) {
		Ext.Msg.alert(i18n('i18n.label.tips'),i18n('i18n.label.parasAreNull'));
	}
	var custLabelList = Ext.getCmp('CustLabelList');
	//新增
	if('new' == status ||'add' == status) {
		return ;
	}
	if('update' == status ){
		if(custData.data.custLabels.length > 0) {
			for (var i=0; i<custData.data.custLabels.length;i ++) {
				if(!Ext.isEmpty(Ext.getCmp((custData.data.custLabels)[i].label.id))){
					Ext.getCmp((custData.data.custLabels)[i].label.id).setDisabled(true);
				} else{
					Ext.Array.remove(custLabels,(custData.data.custLabels)[i]);
					continue;
				}
				custLabelList.add(
						Ext.create('LabelPanel',{
						labelText:custData.data.custLabels[i].label.labelName,
						labelId:custData.data.custLabels[i].label.id,
						status:status,
						channelType:channelType,
						margin:'2 5',
						removeHanlder:function(){
							//删除的时候 干掉前台传给后台的参数
							//封装数据
							var panel= this.up('panel');
						    custLabelModel = new CustLabelModel();
						    label = new LabelModel();
						    label.data.id = panel.labelId;
						    label.data.labelName = panel.labelName;
						    label.data.deptId = currentUser.deptId;
						    custLabelModel.data.label = label.data;
						    custLabelModel.data.custType=custType;
						    for(var i=0;i<custLabels.length;i++){
						    	if(custLabels[i].label.id == panel.labelId){
						    		Ext.Array.remove(custLabels,custLabels[i]);
						    	}
						    }

							var me= this;
							Ext.getCmp(panel.labelId).setDisabled(false);
							Ext.getCmp('CustLabelList').remove(me.up('panel'));
						}
					}))
			}
		}
		
	}
	if('view' == status) {
		custLabels = custData.data.custLabels;
		if(custData.data.custLabels.length > 0) {
			for (var i=0; i<custData.data.custLabels.length;i ++) {
				custLabelList.add(
						Ext.create('LabelPanel',{
						labelText:custData.data.custLabels[i].label.labelName,
						labelId:custData.data.custLabels[i].label.id,
						status:status,
						margin:'2 5',
						disabled:true,
						removeHanlder:function(){
						}
					}));
			}
		}
	}
}