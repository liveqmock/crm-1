//定义一个全局变量，用以标记问卷查询时的状态是否可以为全部,默认值为NORMAL,需要过滤掉特定状态时的值为SPECIAL；
var questionnaireStatusTypeForCombobox = 'NORMAL';
/**
 *问卷管理查询主页面的查询条件面板
 * 2014-3-6
 * 肖红叶 
 */
Ext.define('QuestionnaireQueryPanel',{
	extend:'SearchFormPanel', 
	items:null,
	border:0,
	layout:{
		type:'table',
		columns:3
	},
	defaults:{
		labelWidth:60,
		labelAlign:'right',
		width:210,
		margin:'0 5 5 0'
	},
	defaultType:'datefield',
	initComponent:function(){			
		this.items = this.getItems();
		this.callParent();
	},
	getItems:function(){
		var me = this;
		if(questionnaireStatusTypeForCombobox === 'SPECIAL'){
			return [{//问卷编号
				xtype:'textfield',
		    	fieldLabel : i18n('i18n.questionnaireManage.questionnaireCode'),
				name:'questionnaireCode',
				maxLength : 11,
				maxLengthText : '最多输入11位问卷编号'
		    },{//问卷名称
				xtype:'textfield',
		    	fieldLabel : i18n('i18n.questionnaireManage.questionnaireName'),
				name:'questionnaireName',
				maxLength : 20,
				maxLengthText : '最多输入20字'
		    },{//适用范围
		    	xtype:'combobox',
		    	fieldLabel:i18n('i18n.questionManage.searchPanel.questionRange'),
		    	//剔出朦层
				listConfig: {
	  		        loadMask:false
				},
				colspan:2,
		    	name:'useRange',
				queryModel:'local',
				store:getDataDictionaryByName(DataDictionary,'SURVEY_RANGE'),
				displayField:'codeDesc',
				valueField:'code',
				forceSelection :true,
				editable:true,
				listeners:{
					change:DButil.comboSelsct
				}
			},{//问卷状态
				xtype:'combobox',
		    	fieldLabel:i18n('i18n.questionnaireManage.questionnaireStatus'),
		    	//剔出朦层
				listConfig: {
	  		        loadMask:false
				},
		    	name:'questionnaireStatus',
				queryModel:'local',
				store : Ext.create('Ext.data.Store',{
					fields: [{
				    	name: 'code', 
				    	type: 'string'
				    },{
				    	name: 'codeDesc',  
				    	type: 'string'
				    }],
				    data:[{
				    	 code:'VALID',
				    	 codeDesc:'生效'
				    },{
				    	 code:'USING',
				    	 codeDesc:'使用中' 
				    }]
               }),
				displayField:'codeDesc',
				valueField:'code',
				forceSelection :true,
				editable:true,
				listeners:{
					change:DButil.comboSelsct
				}
			},{//开始创建时间
		    	xtype:'datefield',
		    	fieldLabel:i18n('i18n.questionManage.searchPanel.beginOfCreatDate'),
		    	format:'Y-m-d',
		    	maxValue:new Date(),
				name:'createStartDate',
				//设置起始时间的初始值为当前时间往前90天
				value:Ext.Date.add(new Date(),Ext.Date.DAY,-89)
			},{//结束创建时间
				xtype:'datefield',
		    	fieldLabel:i18n('i18n.questionManage.searchPanel.endOfCreatDate'),
		    	format:'Y-m-d',
		    	maxValue:new Date(),
		    	//为结束时间赋初值
		    	value:new Date(),
				name:'createEndDate'
			},{//开始有效时间
		    	xtype:'datefield',
		    	fieldLabel:i18n('i18n.questionnaireManage.validDate'),
		    	format:'Y-m-d',
				name:'validStartDate'
			},{//结束有效时间
				xtype:'datefield',
		    	fieldLabel:i18n('i18n.questionManage.searchPanel.endOfCreatDate'),
		    	format:'Y-m-d',
				name:'validEndDate'
			}];
		}else{
			return [{//问卷编号
				xtype:'textfield',
		    	fieldLabel : i18n('i18n.questionnaireManage.questionnaireCode'),
				name:'questionnaireCode',
				maxLength : 11,
				maxLengthText : '最多输入11位问卷编号'
		    },{//问卷名称
				xtype:'textfield',
		    	fieldLabel : i18n('i18n.questionnaireManage.questionnaireName'),
				name:'questionnaireName',
				maxLength : 20,
				maxLengthText : '最多输入20字'
		    },{//适用范围
		    	xtype:'combobox',
		    	fieldLabel:i18n('i18n.questionManage.searchPanel.questionRange'),
		    	//剔出朦层
				listConfig: {
	  		        loadMask:false
				},
				colspan:2,
		    	name:'useRange',
				queryModel:'local',
				store:getDataDictionaryByName(DataDictionary,'SURVEY_RANGE'),
				displayField:'codeDesc',
				valueField:'code',
				forceSelection :true,
				editable:true,
				listeners:{
					change:DButil.comboSelsct
				}
			},{//问卷状态
				xtype:'combobox',
		    	fieldLabel:i18n('i18n.questionnaireManage.questionnaireStatus'),
		    	//剔出朦层
				listConfig: {
	  		        loadMask:false
				},
		    	name:'questionnaireStatus',
				queryModel:'local',
				store:getDataDictionaryByName(DataDictionary,'SURVEY_STATUS'),
				displayField:'codeDesc',
				valueField:'code',
				forceSelection :true,
				editable:true,
				listeners:{
					change:DButil.comboSelsct
				}
			},{//开始创建时间
		    	xtype:'datefield',
		    	fieldLabel:i18n('i18n.questionManage.searchPanel.beginOfCreatDate'),
		    	format:'Y-m-d',
		    	maxValue:new Date(),
				name:'createStartDate',
				//设置起始时间的初始值为当前时间往前90天
				value:Ext.Date.add(new Date(),Ext.Date.DAY,-89)
			},{//结束创建时间
				xtype:'datefield',
		    	fieldLabel:i18n('i18n.questionManage.searchPanel.endOfCreatDate'),
		    	format:'Y-m-d',
		    	maxValue:new Date(),
		    	//为结束时间赋初值
		    	value:new Date(),
				name:'createEndDate'
			},{//开始有效时间
		    	xtype:'datefield',
		    	fieldLabel:i18n('i18n.questionnaireManage.validDate'),
		    	format:'Y-m-d',
				name:'validStartDate'
			},{//结束有效时间
				xtype:'datefield',
		    	fieldLabel:i18n('i18n.questionManage.searchPanel.endOfCreatDate'),
		    	format:'Y-m-d',
				name:'validEndDate'
			}];
		}
	}	
});

/**
 * 问卷管理主页面查询结果表格
 * 2014-3-6
 * 肖红叶
 */
Ext.define('QuestionnaireInfoListGrid',{
	extend:'SearchGridPanel',   
	columns:null,
	store:null,
	defaults:{
		align:'center'
	},
	initComponent:function(){             
		var me = this;
		var store = Ext.create('QuestionnaireInfoListStore');
		store.on('beforeload',function(store,operation,e){
			var params = null;
			var questionnaireName = Ext.String.trim(searchParamsForWhole.questionnaireQueryCondi.questionnaireName);
			if(!Ext.isEmpty(searchParamsForWhole)){
				params={
					'questionnaireQueryCondi.questionnaireCode':searchParamsForWhole.questionnaireQueryCondi.questionnaireCode,
					'questionnaireQueryCondi.questionnaireName': questionnaireName,
					'questionnaireQueryCondi.useRange':searchParamsForWhole.questionnaireQueryCondi.useRange,
					'questionnaireQueryCondi.createStartDate':searchParamsForWhole.questionnaireQueryCondi.createStartDate,
					'questionnaireQueryCondi.createEndDate':searchParamsForWhole.questionnaireQueryCondi.createEndDate,
					'questionnaireQueryCondi.validStartDate':searchParamsForWhole.questionnaireQueryCondi.validStartDate,
					'questionnaireQueryCondi.validEndDate':searchParamsForWhole.questionnaireQueryCondi.validEndDate,
					'questionnaireQueryCondi.questionnaireStatus':searchParamsForWhole.questionnaireQueryCondi.questionnaireStatus,
					'questionnaireQueryCondi.questionnaireStatusType':searchParamsForWhole.questionnaireQueryCondi.questionnaireStatusType
				};
			} 
			
			Ext.apply(operation,{
				params : params
			});
		});
		me.store = store;
		me.columns = [{//序号
			xtype:'rownumberer',
			width:40,
			align:'center',
			header:i18n('i18n.questionnaireManage.rownumberer')
		},{//问卷编号 
			header : i18n('i18n.questionnaireManage.questionnaireCode'),
			width:100,
			dataIndex:'questionnaireCode',
			renderer:function(val,metaData,record){
				return val;
			}
		},{//问卷名称
			header : i18n('i18n.questionnaireManage.questionnaireName'),
			width:150,
			dataIndex:'questionnaireName',
			renderer:function(val,metaData,record){
				return val;				
			}
		},{//问卷描述
			header : i18n('i18n.questionnaireManage.questionnaireDesc'),
			width:150,
			dataIndex:'desc',
			renderer:function(val,metaData,record){
				if(!Ext.isEmpty(val)){
					var content = val;
					if(val.length >= 21){
						val=val.substr(0,20)+'...';
					}else{
						val= val;
					} 
					return '<a title="'+content+'">'+val+'</a>';
				}			
			}
		},{//适用范围 
			header : i18n('i18n.questionManage.searchPanel.questionRange'),
			width:100,
			dataIndex:'useRange',
			renderer:function(value){
				return rendererDictionary(value,DataDictionary.SURVEY_RANGE);
			}
		},{//问卷状态
			header : i18n('i18n.questionnaireManage.questionnaireStatus'),
			width:100,
			dataIndex:'status',
			renderer:function(value){
				return rendererDictionary(value,DataDictionary.SURVEY_STATUS);
			}
		},{//生效时间
			header : i18n('i18n.questionnaireManage.effectiveTime'),
			dataIndex : 'effectiveTime',
			align : 'center',
			renderer : DButil.renderDate
		},{//失效时间
			header : i18n('i18n.questionnaireManage.invalidTime'),
			dataIndex : 'invalidTime',
			align : 'center',
			renderer : DButil.renderDate
		},{//采用次数
			header : i18n('i18n.questionManage.resultGrid.frequency'),
			width:100,
			dataIndex:'useTimes',
			renderer:function(val,metaData,record){
				return val+i18n('i18n.questionnaireManage.times');			
			}
		},{//创建时间
			header : i18n('i18n.questionManage.searchPanel.beginOfCreatDate'),
			dataIndex : 'createDate',
			align : 'center',
			renderer : DButil.renderDate
		},{//创建人
			header : i18n('i18n.questionManage.resultGrid.creatorName'),
			width:100,
			dataIndex:'createUser',
			renderer:function(val,metaData,record){
				return val;			
			}
		},{//最后修改时间
			header : i18n('i18n.questionManage.resultGrid.modifyDate'),
			dataIndex : 'modifyDate',
			align : 'center',
			renderer : DButil.renderDate
		},{//最后修改人
			header : i18n('i18n.questionManage.resultGrid.modifierName'),
			width:100,
			dataIndex:'modifyUser',
			renderer:function(val,metaData,record){
				return val;			
			}
		}];
//		//添加双击事件
//        //加载问卷答案详情 盛诗庆
//        me.on('itemdblclick',function(th,record,item,index,e,eOpts){
//        	  
//			  //获得问卷基本信息面板
//			  var questionnaireDetailForm = Ext.getCmp('questionnaireDetailFormId').getForm();
//			  //重置查看详情页面
//			  questionnaireDetailForm.reset();
//			  Ext.getCmp('questionListGridId').store.removeAll();
//			  questionnaireDetailPopWindow.show();
//			  //加载问卷基本信息
//			  questionnaireDetailForm.loadRecord(record);
//			  //获得问卷id数组
//			  var idArray = new Array();
//			  idArray.push(record.get("id"));
//			  //为全局变量问卷id数组和问题id列表赋值
//			  questionnaireIdListForWhole = idArray;
//			  questionIdListForWhole = null;
//			  //加载查看详情中的问题信息列表
//			  Ext.getCmp('questionListGridId').store.load();
//        });
		me.dockedItems=[{
			xtype:'pagingtoolbar',
			cls:'pagingtoolbar',
			store:store,
			dock:'bottom',
			displayInfo : true,
			autoScroll : true,
			items:[{//每页显示
				text: i18n('i18n.PotentialCustManagerView.SearchResultGrid.page_count'),
				xtype: 'tbtext'
			},Ext.create('Ext.form.ComboBox', {
               width:window.screen.availWidth*0.0391,
               triggerAction:'all',
               forceSelection: true,
               value:'20',
               editable:false,
               name:'comboItem',
               displayField:'value',
               valueField:'value',
               queryMode:'local',
               store : Ext.create('Ext.data.Store',{
                   fields : ['value'],
                   data   : [
                       {'value':'5'},
                       {'value':'10'},
                       {'value':'15'},
                       {'value':'20'},
                       {'value':'25'},
                       {'value':'50'}
                   ]
               }),
               listeners:{
					select : {
			            fn: function(_field,_value){
			            	var pageSize = store.pageSize;
			            	var newPageSize = parseInt(_field.value);
			            	if(pageSize!=newPageSize){
			            		store.pageSize = newPageSize;
			            		this.up('pagingtoolbar').moveFirst();
			            	}
			            }
			        }
               }
           }),{//条
				text: i18n('i18n.PotentialCustManagerView.SearchResultGrid.number'),
				xtype: 'tbtext'
           }]
		}];
		this.callParent();
	}
});

/**
 * 根据问题类型转换文字显示
 * @param optionType
 * @returns
 * 肖红叶
 * 20140313
 */
function optionInfoConvert(optionType){
	var result = optionType;
	if(optionType == i18n('i18n.questionnaireManage.radio')){
		result = i18n('i18n.questionnaireManage.radioOfChinese');
	}else if(optionType == i18n('i18n.questionnaireManage.multipleChoice')){
		result = i18n('i18n.questionnaireManage.multipleChoiceOfChinese');
	}else if(optionType == i18n('i18n.questionnaireManage.questionTypeOfAnswer')){
		result = i18n('i18n.questionnaireManage.questionTypeOfAnswerOfChinese');
	}
	return result;
}

/**
 * 根据问题类型转换页面表单按钮显示
 * @param optionType
 * @returns
 * 肖红叶
 * 20140313
 */
function optionDisplayConvert(optionType){
	var result = '';
	if(optionType == i18n('i18n.questionnaireManage.radio')){
		result = '<input name="" type="radio" value="" disabled="disabled"/>';
	}else if(optionType == i18n('i18n.questionnaireManage.multipleChoice')){
		result = '<input type="checkbox" name="" value="" disabled="disabled"/>';
	}
	return result;
}
/**
 * 根据选项类型,是否被选中显示选项
 * @param optionType
 * @returns
 * 肖红叶
 * 20140313
 */
function optionDisplayConvert(optionType,isSelected){
	var result = '';
	if(optionType == i18n('i18n.questionnaireManage.radio')){
		if(isSelected == 1){//如果被选中
			result = '<input name="" type="radio" value="" disabled="disabled" checked = true/>';
		}else{
			result = '<input name="" type="radio" value="" disabled="disabled"/>';
		}
	}else if(optionType == i18n('i18n.questionnaireManage.multipleChoice')){
		if(isSelected == 1){//如果被选中
			result = '<input name="" type="checkbox" value="" disabled="disabled" checked = true/>';
		}else{
			result = '<input name="" type="checkbox" value="" disabled="disabled"/>';
		}
	}
	return result;
}

/**
 * 如果问卷选择题的其他选项被选中，则显示其他选项输入文本框
 * * 肖红叶
 * 20140418
 */
function operateElseOptionText(name,optionId){
	var option = document.getElementsByName(name);
	var length = option.length;
	
	if(!Ext.isEmpty(Ext.getCmp(optionId + 'Else'))){
		if(option[length-1].checked){
			Ext.getCmp(optionId + 'Else').setValue('');
			Ext.getCmp(optionId + 'Else').show();
		}
		else{
			Ext.getCmp(optionId + 'Else').setValue('');
			Ext.getCmp(optionId + 'Else').hide();
		}
	}
}

/**
 * 根据问题类型、问题id和选项id显示适当的表单符号
 * @param optionType
 * @returns
 * 肖红叶
 * 20140322
 */
function optionFormDisplayConvert(optionType,questionId,optionId){
	var result = '';
	if(optionType == i18n('i18n.questionnaireManage.radio')){
		result = '<input name="'+questionId+'" type="radio" value="'+optionId+'"  onClick="operateElseOptionText(this.name,this.value)"/>';
	}else if(optionType == i18n('i18n.questionnaireManage.multipleChoice')){
		result = '<input type="checkbox" name="'+questionId+'" value="'+optionId+'"  onClick="operateElseOptionText(this.name,this.value)"/>';
	}
	
	return result;
}

/**
 * 根据问题选项编号转换英文显示
 * @param optionType
 * @returns
 * 肖红叶
 * 20140313
 */
function optionCodeConvert(num){
	var result = '';
	if(num == 1){
		result = i18n('i18n.questionnaireManage.optionA');
	}else if(num == 2){
		result = i18n('i18n.questionnaireManage.optionB');
	}else if(num == 3){
		result = i18n('i18n.questionnaireManage.optionC');
	}else if(num == 4){
		result = i18n('i18n.questionnaireManage.optionD');
	}else if(num == 5){
		result = i18n('i18n.questionnaireManage.optionE');
	}else if(num == 6){
		result = i18n('i18n.questionnaireManage.optionF');
	}else if(num == 7){
		result = i18n('i18n.questionnaireManage.optionG');
	}else if(num == 8){
		result = i18n('i18n.questionnaireManage.optionH');
	}
	return result;
}

/**
 * 校验时间范围是否完整
 * 肖红叶
 * 20140311
 * @param dateOne
 * @param dateTwo
 * @returns {Boolean}
 */
function dateRangeNotWhole(dateOne,dateTwo){
	var result = false;
	if(Ext.isEmpty(dateOne) && !Ext.isEmpty(dateTwo)){
		result = true;
	}
	return result;
}



/**
 * 校验时间范围是否合法
 * 肖红叶
 * 20140311
 * @param dateOne
 * @param dateTwo
 */
function dateRangeNotValid(dateStart,dateEnd,number,range){
	var result = false;
	if(!Ext.isEmpty(dateStart) && !Ext.isEmpty(dateEnd)){
		if(range == 'DAY'){
			var createCompareTime = Ext.Date.add(new Date(dateStart), Ext.Date.DAY, number);
		}
		else if(range == 'MONTH'){
			var createCompareTime = Ext.Date.add(new Date(dateStart), Ext.Date.MONTH, number);
		}else{
			var createCompareTime = Ext.Date.add(new Date(dateStart), Ext.Date.YEAR, number);
		}
		
		if(DButil.compareTwoDate(dateStart,dateEnd) < 0){
			result = true;
		}else if(DButil.compareTwoDate(createCompareTime,dateEnd) > 0){
			result = true;
		}
	}
	return result;
}

/**
 * 获得问卷查询条件
 * 肖红叶
 * 20140311
 */
function queryConditionParam(form){
	//问卷编号
	var questionnaireCode = form.findField('questionnaireCode').getValue();
	//问卷编号去除空格
	if(!Ext.isEmpty(questionnaireCode)){
		questionnaireCode = Ext.String.trim(questionnaireCode);
	}
	//问卷名称
	var questionnaireName = form.findField('questionnaireName').getValue();
	//适用范围
	var useRange = form.findField('useRange').getValue();
	//开始创建时间
	var createStartDate = form.findField('createStartDate').getValue();
	//结束创建时间
	var createEndDate = form.findField('createEndDate').getValue();
	//开始有效时间
	var validStartDate = form.findField('validStartDate').getValue();
	//结束有效时间
	var validEndDate = form.findField('validEndDate').getValue();
	//问卷状态
	var questionnaireStatus = form.findField('questionnaireStatus').getValue();
	//封装查询条件传递给action
	var searchParams = {'questionnaireQueryCondi' : {
		'questionnaireCode':questionnaireCode,
		'questionnaireName':questionnaireName,
		'useRange':useRange,
		'createStartDate':createStartDate,
		'createEndDate':createEndDate,
		'validStartDate':validStartDate,
		'validEndDate':validEndDate,
		'questionnaireStatus':questionnaireStatus,
		'questionnaireStatusType':questionnaireStatusTypeForCombobox
	}};
	return searchParams;
}