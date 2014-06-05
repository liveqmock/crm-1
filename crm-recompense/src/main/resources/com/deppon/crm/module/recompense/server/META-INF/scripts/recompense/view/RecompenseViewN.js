/**.
 * <p>
 * 将CommonOrderView中的元素组合成订单页面<br/>
 * </p>
 * @author  张斌
 * @时间    2012-03-08
 * </p>
 */

//设置Data层对象
var recompenseData=  (CONFIG.get("TEST"))? new RecompenseDataTestN():new RecompenseDataN();
/**.
 * <p>
 * 获取业务字典数据</br>
 * </p>
 * @author  张斌
 * @时间    2012-03-13
 */

Ext.onReady(function() {
	var keys = [
    		     //出险类型
                 'DANGER_TYPE',
                 //理赔类型
                 'RECOMPENSE_TYPE',
                 //客户等级
                 'MEMBER_GRADE'
    		];
	//初始化业务参数
	initDataDictionary(keys);
	initUser();
	initArea();
    Ext.QuickTips.init();  
	if(Ext.isEmpty(User)){
		MessageUtil.showErrorMes(i18n('i18n.recompense.noPermission'));
		return;
	};
	new RecompenseView();
	Ext.create('Depon.Lib.oDocHelper', {
		helpDoc:{							// 帮助实体：
			windowNum:'lpgl-lpcj-01'		// TODO:帮助文档的ID	belongModule(首字母缩写)+belongMenu（首字母缩写）+windowNum(弹窗编号)+首编时间戳
			,active:true					// 记录操作员操作，是否选择了”隐藏帮助“；
		}
	});
});
Ext.form.Field.prototype.msgTarget='side';



/**.
 * <p>
 * 理赔上报主页面</br>
 * </p>
 * @author  张斌
 * @时间    2012-04-06
 */
Ext.define('RecompenseView',{
	extend:'Ext.container.Viewport',
	layout: {
        type: 'vbox',
        align: 'stretch'
    },
    getItems:function(){
    	return [new RecompenseType(),new DangerInfo(),new RecompenseInfoTab(),new SaveButtonPanel(),
	    	Ext.create('Ext.form.Panel', {			// 新增容器for帮助文档
				id:'wrapForm'+'lpgl-lpcj-01',
				border : false,
				frame : true,
				height:100,
				items : [{
					id : 'id1'+'lpgl-lpcj-01',
					name : 'first',
					height:24
				}, {
					id : 'id2'+'lpgl-lpcj-01',
					name : 'last'
					,height:100									// height:160
				}]
			})
    	];
    },
    initComponent:function(){
		var me = this;
	    me.items = me.getItems();
	    var dataInsurType = new Array();
		   for(var i = 0;i<DataDictionary.DANGER_TYPE.length;i++){
			   if(DataDictionary.DANGER_TYPE[i].code =='PIECE_LOST'||DataDictionary.DANGER_TYPE[i].code =='TICKET_LOST'){
				  
			   }else{
				   dataInsurType.push(DataDictionary.DANGER_TYPE[i]);
			   }
		   }
		   //三个combox的数据都修改
		   Ext.getCmp(CONFIGNAME.get('insurType')).reset();
		   Ext.getCmp(CONFIGNAME.get('insurType')).getStore().removeAll(true);
		   Ext.getCmp(CONFIGNAME.get('insurType')).getStore().loadData(dataInsurType);
		   Ext.getCmp('recompenseListAdd').setDisabled(false);
		 Ext.getCmp('recompenseListUpdate').setDisabled(false);
		 Ext.getCmp('recompenseListDelete').setDisabled(false);
		 Ext.getCmp('tranceListAdd').setDisabled(false);
		 Ext.getCmp('tranceListUpdate').setDisabled(false);
		 Ext.getCmp('tranceListDelete').setDisabled(false);
		this.callParent();
	}
});
/**.
 * <p>
 * 按照规范写出底角的按钮panel</br>
 * </p>
 * @author  张斌
 * @时间    2012-04-06
 */
Ext.define('SaveButtonPanel',{
	extend:'PopButtonPanel',
	items:null,
	//2012-7-17
	//张斌
	//加上margin使其离底部有一定距离，而不影响别的页面布局，修改UAT CRM-2226
	margin:'0 0 50 0',
	weight:400,
	initComponent:function(){
		this.items = this.getItems();
		this.callParent();
	},
	/**.
	 * <p>
	 * 理赔上报提交操作</br>
	 * </p>
	 * @author  张斌
	 * @时间    2012-04-06
	 */
	saveRecompense:function(savebtn){
		var me = this;
		if(!Ext.getCmp('recompenseTypeForm').getForm().isValid()){
			return;
		}
		if(!Ext.getCmp('dangerInfoForm').getForm().isValid()){
			return;
		}
		if(!Ext.getCmp('recompenseInfoForm').getForm().isValid()){
			MessageUtil.showMessage(i18n('i18n.recompense.haveMoreRecompenseInfo'));
			return;
		}
		var dangerInfoArray = new Array();
		Ext.getCmp('dangerInfoGrid').getStore().each(function(record){
			dangerInfoArray.push(record.data);
		});
		if(dangerInfoArray.length==0){
			MessageUtil.showMessage(i18n('i18n.recompense.dangerInfoMustHaveOne'));
			return ;
		}
		var recompenseListArray = new Array();
		Ext.getCmp('goodsTranceGird').getStore().each(function(record){
			recompenseListArray.push(record.data);
		});
		var recompenseMethod = Ext.getCmp(CONFIGNAME.get('recompenseMethod')).getValue().recompenseMethod;
		//判断索赔金额，与索赔清单总金额是否相等
		//张斌
		//2012-07-17
		//start
		if(recompenseMethod=='normal'){
			var recompenseListTotalCount = 0;
			Ext.getCmp('recompenseListGird').getStore().each(function(record){
				recompenseListTotalCount = recompenseListTotalCount+record.get('amount');
			});
			var claimAmount = Ext.getCmp(CONFIGNAME.get('recompenseAmount')).getValue();
			if(Ext.isEmpty(claimAmount)){
				claimAmount = 0;
			}
			if(claimAmount!=recompenseListTotalCount){
				MessageUtil.showMessage(i18n('i18n.recomepnse.recompenseTotalAmountCountEquelClaimAmount'));
				return ;
			}
		}
		//end
		if(recompenseMethod=='normal'){
			var length = recompenseListArray.length
			if(length==0){
				MessageUtil.showMessage(i18n('i18n.recompense.tranceListHaveOne'));
				return ;
			}else{
				Ext.getCmp('recompenseListGird').getStore().each(function(record){
					recompenseListArray.push(record.data);
				});
				if(recompenseListArray.length == length){
					MessageUtil.showMessage(i18n('i18n.recompense.recompenseListHaveOne'));
					return ;
				}
			}
		}else if(recompenseMethod=='fast'){
			var claimAmount = Ext.getCmp(CONFIGNAME.get('recompenseAmount')).getValue();
			var insurAmount = Ext.getCmp(CONFIGNAME.get('insurAmount')).getValue();
			if(Ext.isEmpty(claimAmount)){
				claimAmount = 0
			}
			if(Ext.isEmpty(insurAmount)){
				insurAmount = 0
			}
			if(claimAmount>insurAmount){
				MessageUtil.showMessage(i18n('i18n.recompense.fastAmountCompare'));
				return ;
			}
			
		}
		var attachmentListArray = new Array();
		Ext.getCmp('attachmentGrid').getStore().each(function(record){
			attachmentListArray.push(record.data);
		});
		var waybill = new WaybillModel();
		Ext.getCmp('dangerInfoForm').getForm().updateRecord(waybill);
		//去掉空格
		waybill.set(CONFIGNAME.get('waybillNumber'),waybill.get(CONFIGNAME.get('waybillNumber')).trim());
		//获取FROM中的数据放到RecompenseApp对象中
		var recompenseApp = new RecompenseApp();
		Ext.getCmp('dangerInfoForm').getForm().updateRecord(recompenseApp);
		recompenseApp.set(CONFIGNAME.get('waybill'),waybill.data);
		//获取客户ID
		var custId = Ext.getCmp(CONFIGNAME.get('custId')).getValue();
		var custmer = {'id':custId};
		recompenseApp.set(CONFIGNAME.get('customer'),custmer);
		Ext.getCmp('recompenseTypeForm').getForm().updateRecord(recompenseApp);
		Ext.getCmp('recompenseInfoForm').getForm().updateRecord(recompenseApp);
		savebtn.setDisabled(true);
		//上报成功之后按钮启用，重置界面，显示信息
		var successFn = function(json){
			savebtn.setDisabled(false);
			me.resetRecompense();
			MessageUtil.showInfoMes(json.message);
		}
		//上报失败之后按钮启用，显示信息
		var failureFn = function(json){
			savebtn.setDisabled(false);
			if(Ext.isEmpty(json)){
				MessageUtil.showErrorMes(i18n('i18n.recompense.serviceMoreTime'));
				//DpUtil.showMessage(i18n('i18n.recompense.serviceMoreTime'));
			}else{
				MessageUtil.showErrorMes(json.message);
			}
		}
		//如果为快速理赔，将索赔金额设置到正常理赔金额中
		if(recompenseMethod=='fast'){
			recompenseApp.set(CONFIGNAME.get('normalAmount'),recompenseApp.get(CONFIGNAME.get('recompenseAmount')));
			recompenseApp.set(CONFIGNAME.get('realAmount'),recompenseApp.get(CONFIGNAME.get('recompenseAmount')));
		}
		if(recompenseMethod=='normal'){
			recompenseApp.set(CONFIGNAME.get('costExplain'),i18n('i18n.recomepnse.defaultFeeInfo'));
		}
		var params = {'recompenseView':{'issueItemAddList':dangerInfoArray,'app':recompenseApp.data
			,'goodsTransAddList':recompenseListArray,'attachmentAddList':attachmentListArray}};
		
//		MessageUtil.showQuestionMes(i18n('i18n.recompense.isSureCommitRecompense'),function(e){
//			if (e == 'yes') {
				recompenseData.createRecompense(params,successFn,failureFn);
//			}
//		});
	},
	/**.
	 * <p>
	 * 理赔上报数据重置操作</br>
	 * </p>
	 * @author  张斌
	 * @时间    2012-04-06
	 */
	resetRecompense:function(){
		Ext.getCmp('recompenseInfoForm').getForm().reset();
		Ext.getCmp('dangerInfoForm').getForm().reset();
		Ext.getCmp('recompenseTypeForm').getForm().reset();
	    //设置OLDVALUE
		Ext.getCmp(CONFIGNAME.get('recompenseType')).oldValue = 'abnormal';
		Ext.getCmp('goodsTranceGird').getStore().removeAll();
		Ext.getCmp('goodsTrancesListTotalAmount').getTotalAmount();
		Ext.getCmp('dangerInfoGrid').getStore().removeAll();
		Ext.getCmp('recompenseListGird').getStore().removeAll();
		Ext.getCmp('recompenseListTotalAmount').getTotalAmount();
		Ext.getCmp('attachmentGrid').getStore().removeAll();
		Ext.getCmp(CONFIGNAME.get('waybillNumber')).emptyText = i18n('i18n.recompense.orderNum');
	   Ext.getCmp(CONFIGNAME.get('waybillNumber')).initField( );
	   Ext.getCmp(CONFIGNAME.get('waybillNumber')).setValue('');
	   Ext.getCmp(CONFIGNAME.get('insurType')).reset();
	   Ext.getCmp(CONFIGNAME.get('insurType')).getStore().removeAll(true);
	   Ext.getCmp(CONFIGNAME.get('insurType')).getStore().loadData(DataDictionary.DANGER_TYPE);
		 Ext.getCmp('fastRecompenseMethod_id').setDisabled(false);
	},
	getItems:function(){
		var me = this;
		return [{
			xtype:'middlebuttonpanel' 
		},{
			xtype:'poprightbuttonpanel',
			items:[{ 
				xtype:'button',
				text:i18n('i18n.recompense.save'),
				handler:function(){
					var btn = this;
						/**
						 * 表单提交优化
						 */
						MessageUtil.showQuestionMes(i18n('i18n.recompense.isSureCommitRecompense'),function(e){
							if (e == 'yes') {
								me.saveRecompense(btn);
							}else{
								return;
							}
						});
					}
				},{ 
				xtype:'button',
				text:i18n('i18n.recompense.reset'),
				handler:function(){
					me.resetRecompense();
				}
				}]
		}];
	}
});