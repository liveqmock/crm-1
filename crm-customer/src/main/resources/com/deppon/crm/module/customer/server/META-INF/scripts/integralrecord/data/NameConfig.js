/**
 * 名称与实体的映射
 */
var CONFIGNAME = (function() {
    var constants = {
    		//id
    		'id':'id',
    		/**会员积分列表信息*/
//    		//会员编码
//    		'memberNum':'memberNum',
//    		//会员名称
//    		'memberName':'memberName',
//    		//联系人名称
//    		'contactName':'contactName',
//    		//联系人编码
//    		'contactNumber':'contactNumber',
    		//会员信息
    		'member':'member',
    		//本期可用积分
    		'currentUsableScore':'currentUsableScore',
    		//本期已用积分
    		'currentToUsesScore':'currentToUsesScore',
    		//本期累计积分
    		'currentTotalScore':'currentTotalScore',
    		//累计积分
    		'totalRecord':'totalScore',
    		//累计已用积分
    		'totalToUsesScore':'totalToUsesScore',
    		//最后积分时间
    		'lastRecordTime':'lastDateScore',
    		//是否允许联系人兑换积分
    		'isOnlyMainContactExchange':'isRedeempoints',
//    		//积分失效时间
//    		'invalidTime':'invalidTime',
    		
    		
    		/*************联系人积分********************/
    		
//    		//联系人编码
//    		'conContactNumber':'conContactNumber',
//    		//联系人编码
//    		'conContactName':'conContactNumber',
    		//联系人
    		'conContact':'contact'	,
    		//本期可用积分
    		'conCurrentUsableScorePeriod':'currentUsableScore',
    		//本期已用积分
    		'conUsedReocordCurrentPeriod':'currentToUsesScore',
    		//本期累计积分
    		'conTotalReocordCurrentPeriod':'currentTotalScore',
    		//累计积分(本客户所有累计积分)
    		'conAllReocord':'totalScore',
    		//累计已用积分
    		'conTotalUsedReocord':'totalToUsesScore',
//    		//失效积分
//    		'conInvalidReocord':'conInvalidReocord',
    		//最后积分时间
    		'conLastRecordTime':'lastDateScore',
//    		//积分失效时间
//    		'conInvalidTime':'lastDateScore',
    		
    		/***********礼品申请**************/
    		
//    		//联系人编码
//    		'preContactNumber':'preContactNumber',
//    		//联系人
//    		'preContactName':'preContactName',
    		//联系人
    		'preContact':'contact',
    		//礼品名称
    		'prePresentName':'gift',
    		//兑换数量
    		'preExchangeNumber':'convertNumber',
    		//消费积分'
    		'preConsumeRecord':'score',
    		//发放日期
    		'preIssueTime':'sendTime',
    		//兑换日期
    		'preExchangeTime':'convertTime',
    		//是否发放
    		'preIsIssue':'sendStatus',
    		//兑换人身份证号
    		'preExchangerNo':'convertIdCard',
    		//工作流编码'
    		'preWorkflowNumber':'workFlowId',
    		/*************** 积分运单*****************/
//    		//联系人编码
//    		'wayContactNumber':'wayContactNumber',
//    		//联系人
//    		'wayContactName':'wayContactName',
    		//联系人
    		'wayContact':'contact',
    		//运单号
    		'wayWaybillNumber':'number',
    		//出发部门
    		'wayStartStation':'leavedeptName',
    		//到达部门
    		'wayEndStation':'ladingstationName',
    		//运输类型
    		'wayTransType':'transType',
    		//付款方式
    		'wayPaymentType':'payMent',
    		//订单渠道
    		'wayChannelNumber':'ditch',
    		//总费用
    		'wayTotalPrice':'totalMoney',
    		//积分率
    		'wayIntegralRate':'rate',
    		//积分
    		'wayRecord':'score',
    		//积分角色
    		'wayReordRole':'role',
    		//积分描述
    		'wayRecordDesc':'pointdesc',
    		//积分日期
    		'waylastRecordTime':'integDate',
    		
    		/********************奖励积分********************/
//    		//联系人编码
//    		'awaContactNumber':'awaContactNumber',
//    		//联系人
//    		'awaContactName':'awaContactName',
    		//联系人信息
    		'awaContact':'contact',
    		//奖励规则
    		'awaRewardIntegral':'rewardIntegral'	,
    		//积分基数
    		'awaIntegralBasicNumber':'integralBasicNumber',
    		//积分奖励时间
    		'awaRewardDate':'createDate',
//    		//积分奖励日期
//    		'awaAwardDate':'awaAwardDate',
//    		//奖励类型
//    		'awaAwardType':'ruletype',
//    		//奖励名称
//    		'awaAwardName':'rulename',
//    		//积分失效时间
//    		'awaInvalidTime':'awaInvalidTime',
//    		//奖励基数
//    		'awaAwardRadix':'awaAwardRadix',
//    		//积分率
//    		'awaAwardRate':'presentName',
//    		//奖励积分
//    		'awaRecord':'awaRecord',
//    		//积分类型
//    		'awaRocordType':'awaRocordType',
//    		//描述
//    		'awaDesc':'awaDesc',
    		/*********************积分调整*************************/
//    		//联系人编码
//    		'adjContactNumber':'adjContactNumber',
//    		//联系人
//    		'adjContactName':'adjContactName',
    		//联系人信息
    		'adjcontact':'contact',
    		//积分调整日
    		'adjAdjustDate':'createDate',
    		//调整类型
    		'adjAdjustType':'adjustType',
    		//调整积分
    		'adjAdjustRecord':'score',
    		//调整去向
    		'adjAdjustGo':'contactTo',
    		//调整来源
    		'adjAdjustFrom':'contactFrom',
    		
    		/*******************客户礼品*******************/
    		//申请礼物
    		'custGifGift':'gift',
    		//联系人信息
    		'custGifContact':'contact',
    		//兑换数量
    		'custGifNum':'convertNumber',
    		//消费积分
    		'custUsedRecord':'score',
    		//工作流编号
    		'custGifWorkFlowId':'workFlowId',
    		//兑换时间
    		'custGifConvertTime':'convertTime',
    		//发放时间
    		'custGifSendTime':'sendTime',
    		//发放状态
    		'custGifSendStatus':'sendTime',
    		//发放状态
    		'custGifIsSend':'sendStatus',
    		//兑换人身份证号
    		'custGifExchangerNo':'convertIdCard',
    		
    		/*******************积分规则*******************/
    		//产品规则类型
    		'rulProductType':'product',
    		//渠道规则类型
    		'rulChannelType':'channeltype',
    		//始发城市
    		'rulStartStation':'leadept',
    		//到达城市
    		'rulEndStation':'arrdept',
    		//积分编号
    		'rulNumber':'number',
    		//积分率
    		'rulRate':'fraction',
    		//积分生效日期
    		'rulValideTime':'pointbegintime',
    		//积分结束生效日期
    		'rulValideInvalideTime':'pointendtime',
    		//积分描述
    		'rulRecordDesc':'pointdesc',
    		//创建人
    		'rulCreateUser':'cname',
    		//创建时间
    		'rulCreateTime':'createDate',
    		//修改人
    		'rulModifyUser':'mname',
    		//修改时间
    		'rulModifyDate':'modifyDate',

    		/**********************供选择奖品兑换************************/
    		//礼品名称
			'gifChooseName':'giftName',
			//礼品名称
			'gifGiftNumber':'giftNumber',
			//所需积分
			'gifChooseNeedRecord':'needPoints',
			//库存数量
			'gifChooseStoreNumber':'inventNumber',
			//礼品价值
			'gifChooseGiftValue':'giftValue',
			//实际价值
			'gifChooseRealValue':'realValue',
			//礼品描述
			'gifChooseGiftDesc':'giftDesc',

    		
    		/**********************奖品兑换************************/
    			//礼品名称
    			'excGiftName':'giftName',
    			//联系人编号
    			'excContactNo':'number',
    			//联系人名称
    			'excContactName':'name',
    			//兑换数量
    			'excExchangeNum':'convertNumber',
    			//消费积分
    			'excSpendRecord':'score',
    			// 兑换人身份证号
    			'excExchangerNo':'idCard'
    		};

        return {
           get: function(name) { return constants[name]; }
       };
   })();
