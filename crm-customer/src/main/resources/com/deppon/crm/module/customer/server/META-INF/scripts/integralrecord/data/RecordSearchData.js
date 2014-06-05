/**.
 * <p>
 * 积分模块中要用到的DATA<br/>
 * <p>
 * @author 潘光均
 * @时间 2012-3-8
 */

/**
 * .
 * <p>
 * 判断是否导入测试数据<br/>
 * <p>
 * 
 * @author 潘光均
 * @时间 2012-3-8
 */
(function() {
	var recordSearchDataTest = "../scripts/integralrecord/test/RecordSearchDataTest.js";
	if (CONFIG.get('TEST')) {
		document.write('<script type="text/javascript" src="'
				+ recordSearchDataTest + '"></script>');
	}
})();

RecordSearchData = function() {
};

/**
 * .
 * <p>
 * 查询条件combox获取测试用store<br/>
 * </p>
 * 
 * @returns states 一个data.store
 * @author 潘光均
 * @时间 2012-03-27
 */
RecordSearchData.prototype.getSearchConditionStore = function() {
	var conditions = Ext.create('Ext.data.Store', {
		fields : [ 'id', 'condition' ],
		data : [ {
			"id" : "1",
			"condition" : "固定客户编码"
		}, {
			"id" : "2",
			"condition" : "固定客户名称"
		}, {
			"id" : "3",
			"condition" : "联系人名称"
		}, {
			"id" : "4",
			"condition" : "联系人编码"
		} ]
	});
	return conditions;
};

/**
 * .
 * <p>
 * 礼品查询Model<br/>
 * </p>
 * @author 潘光均
 * @时间 2012-03-27
 */
Ext.define('GiftModel', {
	extend : 'Ext.data.Model',
	fields : [
	// 会员编码
	{name :CONFIGNAME.get('gifMemberNum')},
	// 会员名称
	{name :CONFIGNAME.get('gifMemberName')},
	// 联系人名称
	{name :CONFIGNAME.get('gifContactName')},
	// 工作流编号
	{name :CONFIGNAME.get('gifWorkflowNumber')},
	// 礼品名称
	{name :CONFIGNAME.get('gifGiftName')},
	// 礼品描述
	{name :CONFIGNAME.get('gifGiftDesc')},
	// 消费积分
	{name :CONFIGNAME.get('gifUsedRecord')},
	// 兑换数量
	{name :CONFIGNAME.get('gifExchangeNum')},
	// 发放日期
	{name :CONFIGNAME.get('gifInvalidTime')},
	// 发放状态
	{name :CONFIGNAME.get('gifIssueTime')},
	// 兑换人身份证号
	{name :CONFIGNAME.get('gifExchangerNo')}
	]
});

/**
 * .
 * <p>
 * 礼品查询store<br/>
 * </p>
 * @returns states 一个data.store
 * @author 潘光均
 * @时间 2012-03-27
 */
RecordSearchData.prototype.getGiftStore =function(){
	Ext.define('GiftStore', {
		extend : 'Ext.data.Store',
		storeId:'giftStore',
		model : 'GiftModel',
		pageSize : 30,
		proxy : {
			type : 'ajax',
			api : {
				read : 'searchGiftList.action'
			},
			actionMethods : 'POST',
			reader : {
				type : 'json',
				root : 'giftList',
				totalProperty : 'totalCount'
			}
		}
	});
	return new SearchRecordStore();
}


/**
 * .
 * <p>
 * 积分查询Model<br/>
 * </p>
 * 
 * @author 潘光均
 * @时间 2012-03-27
 */
Ext.define('RecordListModel', {
	extend : 'Ext.data.Model',
	fields : [
	// id
	{name :CONFIGNAME.get('id')},
	// 会员信息
	{name :CONFIGNAME.get('member')},
	// 本期可用积分
	{name :CONFIGNAME.get('currentUsableScore')
	},
	// 本期已用积分
	{name :CONFIGNAME.get('currentToUsesScore')
	},
	//本期 累计积分
	{name :CONFIGNAME.get('currentTotalScore')
	},
	// 累计积分
	{name :CONFIGNAME.get('totalRecord')
	},
	//是否仅允许主联系人兑换积分
	{name :CONFIGNAME.get('isOnlyMainContactExchange')
	},
	// 累计已用积分
	{name :CONFIGNAME.get('totalToUsesScore')
	},
	// 最后积分时间
	{name :CONFIGNAME.get('lastRecordTime'),
		type:'Date',
		convert:DpUtil.changeLongToDate
	}
	]
});


/**
 * .
 * <p>
 * 积分查询store<br/>
 * </p>
 * @returns states 一个data.store
 * @author 潘光均
 * @时间 2012-03-27
 */
RecordSearchData.prototype.getSearchRecordStore =function(){
Ext.define('SearchRecordStore', {
	extend : 'Ext.data.Store',
	model : 'RecordListModel',
	pageSize : 10,
	proxy : {
		type : 'ajax',
		api : {
			read : 'searchRecordList.action'
		},
		actionMethods : 'POST',
		reader : {
			type : 'json',
			root : 'ingegralList',
			totalProperty : 'totalCount'
		}
	}
});
return new SearchRecordStore();
}

/**
 * .
 * <p>
 * 联系人查询Model<br/>
 * </p>
 * @author 潘光均
 * @时间 2012-03-27
 */
Ext.define('ContactRecordModel', {
	extend : 'Ext.data.Model',
	fields : [
	// 联系人
	{name :CONFIGNAME.get('conContact')},
	// 本期可用积分
	{name :CONFIGNAME.get('conCurrentUsableScorePeriod')},
	// 本期已用积分
	{name :CONFIGNAME.get('conUsedReocordCurrentPeriod')},
	// 本期累计积分
	{name :CONFIGNAME.get('conTotalReocordCurrentPeriod')},
	// 累计积分
	{name :CONFIGNAME.get('conAllReocord')},
	// 累计已用积分
	{name :CONFIGNAME.get('conTotalUsedReocord')},
	// 最后积分时间
	{name :CONFIGNAME.get('conLastRecordTime'),
		type:'Date',
		convert:DpUtil.changeLongToDate
	}
	]
});
/**
 * .
 * <p>
 * 联系人积分store<br/>
 * </p>
 * @returns states 一个data.store
 * @author 潘光均
 * @时间 2012-03-27
 */
RecordSearchData.prototype.getContactRecordStore =function(){
	Ext.define('ContactRecordStore', {
		extend : 'Ext.data.Store',
		model : 'ContactRecordModel',
		pageSize : 10,
		proxy : {
			type : 'ajax',
			api : {
				read : 'searchContactRecordList.action'
			},
			actionMethods : 'POST',
			reader : {
				type : 'json',
				root : 'contactRecordList',
				totalProperty : 'totalCount'
			}
		}
	});
	return new ContactRecordStore();
}

/**
 * .
 * <p>
 * 礼品申请查询Model<br/>
 * </p>
 * @author 潘光均
 * @时间 2012-03-27
 */
Ext.define('PresentApplyModel', {
	extend : 'Ext.data.Model',
	fields : [
	// 联系人
	{name :CONFIGNAME.get('preContact')},
	// 礼品信息
	{name :CONFIGNAME.get('prePresentName')},
	// 兑换数量
	{name :CONFIGNAME.get('preExchangeNumber')},
	// 消费积分'
	{name :CONFIGNAME.get('preConsumeRecord')},
	// 发放日期
	{name :CONFIGNAME.get('preIssueTime')},
	// 兑换日期
	{name :CONFIGNAME.get('preExchangeTime')},
	// 是否发放
	{name :CONFIGNAME.get('preIsIssue')},
	// 兑换人身份证号
	{name :CONFIGNAME.get('preExchangerNo')},
	//工作流编码'
	{name :CONFIGNAME.get('preWorkflowNumber')}
	]
});
/**
 * .
 * <p>
 * 礼品申请查询store<br/>
 * </p>
 * @returns states 一个data.store
 * @author 潘光均
 * @时间 2012-03-27
 */
RecordSearchData.prototype.getPresentApplyStore =function(){
	Ext.define('PresentApplyStore', {
		extend : 'Ext.data.Store',
		model : 'PresentApplyModel',
		pageSize : 10,
		proxy : {
			type : 'ajax',
			api : {
				read : 'searchPresentApplyList.action'
			},
			actionMethods : 'POST',
			reader : {
				type : 'json',
				root : 'presentApplyList',
				totalProperty : 'totalCount'
			}
		}
	});
	return new PresentApplyStore();
}

/**
 * .
 * <p>
 *奖励积分查询Model<br/>
 * </p>
 * @author 潘光均
 * @时间 2012-03-27
 */
Ext.define('AwardRecordModel', {
	extend : 'Ext.data.Model',
	fields : [
	// 联系人
	{name :CONFIGNAME.get('awaContact')},
	// 奖励规则
	{name :CONFIGNAME.get('awaRewardIntegral')},
	//积分奖励时间
	{name :CONFIGNAME.get('awaRewardDate')},
	//积分基数
	{name :CONFIGNAME.get('awaIntegralBasicNumber')}
	]
});
/**
 * .
 * <p>
 * 奖励积分查询store<br/>
 * </p>
 * @returns states 一个data.store
 * @author 潘光均
 * @时间 2012-03-27
 */
RecordSearchData.prototype.getAwardRecordStore =function(){
	Ext.define('AwardRecordStore', {
		extend : 'Ext.data.Store',
		model : 'AwardRecordModel',
		pageSize : 10,
		proxy : {
			type : 'ajax',
			api : {
				read : 'searchAwardRecordList.action'
			},
			actionMethods : 'POST',
			reader : {
				type : 'json',
				root : 'awardRecordList',
				totalProperty : 'totalCount'
			}
		}
	});
	return new AwardRecordStore();
}
/**
 * .
 * <p>
 *调整积分查询Model<br/>
 * </p>
 * @author 潘光均
 * @时间 2012-03-27
 */
Ext.define('AdjustRecordModel', {
	extend : 'Ext.data.Model',
	fields : [
	          // 联系人
	          {name :CONFIGNAME.get('adjcontact')},
	          // 积分调整日
	          {name :CONFIGNAME.get('adjAdjustDate')},
	          //调整类型
	          {name :CONFIGNAME.get('adjAdjustType')},
	          //调整积分
	          {name :CONFIGNAME.get('adjAdjustRecord')},
	          //调整去向
	          {name :CONFIGNAME.get('adjAdjustGo')},
	          //调整来源
	          {name :CONFIGNAME.get('adjAdjustFrom')}
	          ]
});
/**
 * .
 * <p>
 * 调整积分查询store<br/>
 * </p>
 * @returns states 一个data.store
 * @author 潘光均
 * @时间 2012-03-27
 */
RecordSearchData.prototype.getAdjustRecordStore =function(){
	Ext.define('AdjustRecordStore', {
		extend : 'Ext.data.Store',
		model : 'AdjustRecordModel',
		pageSize : 10,
		proxy : {
			type : 'ajax',
			api : {
				read : 'searchAdjustRecordList.action'
			},
			actionMethods : 'POST',
			reader : {
				type : 'json',
				root : 'adjustRecordList',
				totalProperty : 'totalCount'
			}
		}
	});
	return new AdjustRecordStore();
}
/**
 * .
 * <p>
 积分运单查询Model<br/>
 * </p>
 * @author 潘光均
 * @时间 2012-03-27
 */
Ext.define('WaybillRecordModel', {
	extend : 'Ext.data.Model',
	fields : [
	          // 联系人
	          {name :CONFIGNAME.get('wayContact')},
	          //运单号
	          {name :CONFIGNAME.get('wayWaybillNumber')},
	          //出发部门
	          {name :CONFIGNAME.get('wayStartStation')},
	          //到达部门
	          {name :CONFIGNAME.get('wayEndStation')},
	          //运输类型
	          {name :CONFIGNAME.get('wayTransType')},
	          //付款方式
	          {name :CONFIGNAME.get('wayPaymentType')},
	          //订单渠道
	          {name :CONFIGNAME.get('wayChannelNumber')},
	          //总费用
	          {name :CONFIGNAME.get('wayTotalPrice')},
	          //积分率
	          {name :CONFIGNAME.get('wayIntegralRate')},
	          //积分
	          {name :CONFIGNAME.get('wayRecord')},
	          //积分角色
	          {name :CONFIGNAME.get('wayReordRole')},
	          //积分描述
	          {name :CONFIGNAME.get('wayRecordDesc')},
	          //积分日期
	          {name :CONFIGNAME.get('waylastRecordTime')}
	          ]
});
/**
 * .
 * <p>
 * 积分运单查询store<br/>
 * </p>
 * @returns states 一个data.store
 * @author 潘光均
 * @时间 2012-03-27
 */
RecordSearchData.prototype.getWaybillRecordStore =function(){
	Ext.define('WaybillRecordStore', {
		extend : 'Ext.data.Store',
		model : 'WaybillRecordModel',
		pageSize : 10,
		proxy : {
			type : 'ajax',
			api : {
				read : 'searchWaybillRecordList.action'
			},
			actionMethods : 'POST',
			reader : {
				type : 'json',
				root : 'waybillRecordList',
				totalProperty : 'totalCount'
			}
		}
	});
	return new WaybillRecordStore();
}

/**
 * .
 * <p>
 * 产品规则store<br/>
 * </p>
 * @returns states 一个data.store
 * @author 潘光均
 * @时间 2012-03-27
 */
RecordSearchData.prototype.getProductRuleStore =function(){
	Ext.define('ProductRuleStore', {
		extend : 'Ext.data.Store',
		model : 'IntegralRuleModel',
		autoLoad:true,
		pageSize : 10,
		proxy : {
			type : 'ajax',
			api : {
				read : 'searchProductRuleList.action'
			},
			actionMethods : 'POST',
			reader : {
				type : 'json',
				root : 'productRuleList',
				totalProperty : 'totalCount'
			}
		}
	});
	return new ProductRuleStore();
}



/**
 * .
 * <p>
 * 渠道规则store<br/>
 * </p>
 * @returns states 一个data.store
 * @author 潘光均
 * @时间 2012-03-27
 */
RecordSearchData.prototype.getChannelRuleStore =function(){
	Ext.define('ChannelRuleStore', {
		extend : 'Ext.data.Store',
		model : 'IntegralRuleModel',
		autoLoad:true,
		pageSize : 10,
		proxy : {
			type : 'ajax',
			api : {
				read : 'searchChannelRuleList.action'
			},
			actionMethods : 'POST',
			reader : {
				type : 'json',
				root : 'channelRuleList',
				totalProperty : 'totalCount'
			}
		}
	});
	return new ChannelRuleStore();
}


/**
 * .
 * <p>
 路线规则Model<br/>
 * </p>
 * @author 潘光均
 * @时间 2012-03-27
 */
Ext.define('IntegralRuleModel', {
	extend : 'Ext.data.Model',
	fields : [
	          //id
	          {name :CONFIGNAME.get('id')},
	          // 产品规则类型
	          {name :CONFIGNAME.get('rulProductType')},
	          // 渠道规则类型
	          {name :CONFIGNAME.get('rulChannelType')},
	          // 始发城市
	          {name :CONFIGNAME.get('rulStartStation')},
	          // 始发城市name
	          {name :'leadeptName'},
	          //到达城市
	          {name :CONFIGNAME.get('rulEndStation')},
	          //到达城市name
	          {name :'arrdeptName'},
	          //积分编号
	          {name :CONFIGNAME.get('rulNumber')},
	          //积分率
	          {name :CONFIGNAME.get('rulRate')},
	          //积分生效日期
	          {name :CONFIGNAME.get('rulValideTime'),
	        	type:'Date',
	        	convert: DpUtil.changeLongToDate
	          },
	          //积分结束生效日期
	          {name :CONFIGNAME.get('rulValideInvalideTime'),
	        	 type:'Date',
		        convert: DpUtil.changeLongToDate
		       },
	          //积分描述
	          {name :CONFIGNAME.get('rulRecordDesc')},
	          //创建人
	          {name :CONFIGNAME.get('rulCreateUser')},
	          //创建时间
	          {name :CONFIGNAME.get('rulCreateTime')},
	          //修改人
	          {name :CONFIGNAME.get('rulModifyUser')},
	          //修改时间
	          {name :CONFIGNAME.get('rulModifyDate')}
	          ]
});

/**
 * .
 * <p>
 * 线路规则store<br/>
 * </p>
 * @returns states 一个data.store
 * @author 潘光均
 * @时间 2012-03-27
 */
RecordSearchData.prototype.getWayRuleStore =function(){
	Ext.define('WayRuleStore', {
		extend : 'Ext.data.Store',
		model : 'IntegralRuleModel',
		autoLoad:true,
		pageSize : 10,
		proxy : {
			type : 'ajax',
			api : {
				read : 'searchWayRuleList.action'
			},
			actionMethods : 'POST',
			reader : {
				type : 'json',
				root : 'wayRuleList',
				totalProperty : 'totalCount'
			}
		}
	});
	return new WayRuleStore();
}

/**
 * .
 * <p>
 * 积分兑换礼品Model<br/>
 * </p>
 * 
 * @author 潘光均
 * @时间 2012-03-27
 */
Ext.define('GiftExchangeModel', {
	extend : 'Ext.data.Model',
	fields : [
	//礼品id
	{name :CONFIGNAME.get('id')},
//	// 礼品名称
//	{name :CONFIGNAME.get('excGiftName')},
//	// 礼品名称
//	{name :CONFIGNAME.get('gifGiftNumber')},
	// 礼品
	{name :CONFIGNAME.get('custGifGift')},
//	// 联系人编码
//	{name :CONFIGNAME.get('excContactNo')},
//	//联系人
//	{name :CONFIGNAME.get('excContactName')},
	//兑换数量
	{name :CONFIGNAME.get('excExchangeNum')},
	// 消费积分
	{name :CONFIGNAME.get('excSpendRecord')},
	// 联系人
	{name :CONFIGNAME.get('conContact')}
	]
});


/**
 * .
 * <p>
客户礼品Model<br/>
 * </p>
 * @author 潘光均
 * @时间 2012-03-27
 */
Ext.define('CustGiftModel', {
	extend : 'Ext.data.Model',
	fields : [
	          //id
	          {name :CONFIGNAME.get('id')},
	          //礼品
	          {name :CONFIGNAME.get('custGifGift')},
	          // 联系人
	          {name :CONFIGNAME.get('custGifContact')},
	          //兑换数量
	          {name :CONFIGNAME.get('custGifNum')},
	          //消费积分
	          {name :CONFIGNAME.get('custUsedRecord')},
	          //工作流编号
	          {name :CONFIGNAME.get('custGifWorkFlowId')},
	          //兑换时间
	          {name :CONFIGNAME.get('custGifConvertTime')},
	          //发放时间
	          {name :CONFIGNAME.get('custGifSendTime')},
	          //发放状态
	          {name :CONFIGNAME.get('custGifSendStatus')},
	          //是否发放
	          {name :CONFIGNAME.get('custGifIsSend')},
	          //兑换人身份证号
	          {name :CONFIGNAME.get('custGifExchangerNo')}
	          ]
});

/**
 * .
 * <p>
 * 渠道规则store<br/>
 * </p>
 * @returns states 一个data.store
 * @author 潘光均
 * @时间 2012-03-27
 */
RecordSearchData.prototype.getCustGiftStore =function(){
	Ext.define('CustGiftStore', {
		extend : 'Ext.data.Store',
		model : 'CustGiftModel',
		pageSize : 10,
		proxy : {
			type : 'ajax',
			api : {
				read : 'searchCustGiftLists.action'
			},
			actionMethods : 'POST',
			reader : {
				type : 'json',
				root : 'custGiftList',
				totalProperty : 'totalCount'
			}
		}
	});
	return new CustGiftStore();
}



/**
 * .
 * <p>
供选择礼品Model<br/>
 * </p>
 * @author 潘光均
 * @时间 2012-03-27
 */
Ext.define('GiftChooseModel', {
	extend : 'Ext.data.Model',
	fields : [
	          //id
	          {name :CONFIGNAME.get('id')},
	          //礼品名称
	          {name :CONFIGNAME.get('gifChooseName')},
		      //礼品编码
		      {name :CONFIGNAME.get('gifGiftNumber')},
	          // 所需积分
	          {name :CONFIGNAME.get('gifChooseNeedRecord')},
	          //库存数量
	          {name :CONFIGNAME.get('gifChooseStoreNumber')},
	          //礼品价值
	          {name :CONFIGNAME.get('gifChooseGiftValue')},
	          //实际价值
	          {name :CONFIGNAME.get('gifChooseRealValue')},
	          //礼品描述
	          {name :CONFIGNAME.get('gifChooseGiftDesc')}
	          ]
});

/**
 * .
 * <p>
 * 渠道规则store<br/>
 * </p>
 * @returns states 一个data.store
 * @author 潘光均
 * @时间 2012-03-27
 */
RecordSearchData.prototype.getGiftChooseStore =function(){
	Ext.define('GiftChooseStore', {
		extend : 'Ext.data.Store',
		model : 'GiftChooseModel',
		pageSize : 10,
		proxy : {
			type : 'ajax',
			api : {
				read : 'searchGiftChooseList.action'
			},
			actionMethods : 'POST',
			reader : {
				type : 'json',
				root : 'giftChooseList',
				totalProperty : 'totalCount'
			}
		}
	});
	return new GiftChooseStore();
}





/**
 * 删除积分规则
 */
RecordSearchData.prototype.deleteRecordRule=function(param,successFn,failureFn){
	var url = '../customer/deleteRecordRuleById.action';
	DpUtil.requestJsonAjax(url,param,successFn,failureFn);
};
/**
 *校验输入的规则编号是否存在
 */
RecordSearchData.prototype.getRecordRuleByNumber=function(param,successFn,failureFn){
	var url = '../customer/getRecordRuleByNumber.action';
	DpUtil.requestJsonAjax(url,param,successFn,failureFn);
};
/**
 *创建积分规则
 */
RecordSearchData.prototype.createRecordRule=function(param,successFn,failureFn){
	var url = '../customer/createRecordRule.action';
	DpUtil.requestJsonAjax(url,param,successFn,failureFn);
};
/**
 *修改积分规则
 */
RecordSearchData.prototype.modifyRecordRule=function(param,successFn,failureFn){
	var url = '../customer/modifyRecordRule.action';
	DpUtil.requestJsonAjax(url,param,successFn,failureFn);
};
/**
 *发送礼品
 */
RecordSearchData.prototype.confirmSendGift=function(param,successFn,failureFn){
	var url = '../customer/confirmSendGift.action';
	DpUtil.requestJsonAjax(url,param,successFn,failureFn);
};
/**
 *恢复库存礼品
 */
RecordSearchData.prototype.revertStoreGift=function(param,successFn,failureFn){
	var url = '../customer/revertStoreGift.action';
	DpUtil.requestJsonAjax(url,param,successFn,failureFn);
};

/**
 *查询乐客户积分信息
 */
RecordSearchData.prototype.searchContactRecordInfo=function(param,successFn,failureFn){
	var url = '../customer/searchContactRecordInfo.action';
	DpUtil.requestJsonAjax(url,param,successFn,failureFn);
};
/**
 *暂存礼品申请清单
 */
RecordSearchData.prototype.temporarySaveGiftBill=function(param,successFn,failureFn){
	var url = '../customer/temporarySaveGiftBill.action';
	DpUtil.requestJsonAjax(url,param,successFn,failureFn);
};
/**
 *校验礼品清单是否可以生成
 */
RecordSearchData.prototype.checkGiftBillAviable=function(param,successFn,failureFn){
	var url = '../customer/checkGiftBillAviable.action';
	DpUtil.requestJsonAjax(url,param,successFn,failureFn);
};
/**
 *保存礼品申请清单
 */
RecordSearchData.prototype.saveGiftBill=function(param,successFn,failureFn){
	var url = '../customer/saveGiftBill.action';
	DpUtil.requestJsonAjax(url,param,successFn,failureFn);
};
/**
 *获取暂存清单
 */
RecordSearchData.prototype.searchTemporaryGiftBill=function(param,successFn,failureFn){
	var url = '../customer/searchTemporaryGiftBill.action';
	DpUtil.requestJsonAjax(url,param,successFn,failureFn);
};