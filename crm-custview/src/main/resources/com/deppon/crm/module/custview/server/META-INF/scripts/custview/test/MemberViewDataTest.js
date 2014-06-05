MemberViewDataTest = function() {};

MemberViewDataTest.prototype.fnInformationTopLeftbtmStore = function(){
	return Ext.create('Ext.data.Store', {
	    fields:['contactPerson', 'sex', 'post','integral','telephoneNumber','mobileNumber','address'],
	    data:[{ "contactPerson":'张三', "sex":"男", "post":"XXX", "integral":"1903", "telephoneNumber":"021", 
	        	'mobileNumber':"5151", "address":"上海" },
	          { "contactPerson":'张三', "sex":"女", "post":"XXX", "integral":"1490", "telephoneNumber":"021", 
	        	'mobileNumber':"5151", "address":"江西" }	]
	});
};
MemberViewDataTest.prototype.fnInformationTopRighCtnStore = function(){
	return Ext.create('Ext.data.Store', {
//		storeId:'InformationTopRighCtnStoreId',
		fields:['month','january','february','march','april','may','June'],
		data:[{ 'month':'发货量','january':'1月','february':'二月','march':'三月',
				'april':'四月','may':'五月','June':'六月'},
			  { 'month':'到货量','january':'1月','february':'二月','march':'三月',
				'april':'四月','may':'五月','June':'六月'}	]
	});
};

MemberViewDataTest.prototype.fnHistyComplaintStatisticsStore = function(){
	return 	Ext.create('Ext.data.Store', {
	    storeId:'HistyComplaintStatisticsStoreId',
	    fields:['scopeOfComplaint','insuranceClaims','agencyProblem','nonComplaints','costComplaint',
			       'Service','billingErrors','customerReminder','customerReason','internalComplaints',
			       'personalityProblem','Prescription','serviceError'],
		data:{'items':[{ "scopeOfComplaint":'张四', "insuranceClaims":"李四", "agencyProblem":"XXX", 
					"nonComplaints":"1534924903", "costComplaint":"021", 'Service':"5151", "billingErrors":"555kg",
					"customerReminder":"tz","customerReason":"tz","internalComplaints":"rgrt",
					"personalityProblem":"rthhsh","Prescription":"trhsh","serviceError":"vogergeglume" }        
	    ]},
	    proxy:{
	        type: 'memory',
	        reader: {
	            type: 'json',
	            root: 'items'
	        }
	    }
	});
};

Ext.create('Ext.data.Store', {
    storeId:'ARecentComplaintListStoreId',
    fields:['treatmentNumber','waybillNumber','complainant','mobileNumber','telephoneNumber',
		       'complaintTime','complaintsType','processingStatus'],
	data:{'items':[{'treatmentNumber':'处理编号','waybillNumber':'运单号','complainant':'投诉人',
					'mobileNumber':'手机号码','telephoneNumber':'固定电话','complaintTime':"投诉时间",
					'complaintsType':'投诉类型','processingStatus':'处理状态'}        
    ]},
    proxy:{
        type: 'memory',
        reader: {
            type: 'json',
            root: 'items'
        }
    }
});

MemberViewDataTest.prototype.fnHistoricalClaimsStatisticsStore = function(){
	return Ext.create('Ext.data.Store',{
		storeId:'HistoricalClaimsStatisticsStoreId',
		fields:['accidentType','lostGoods','damaged','wet','pollution','miscellaneous','total'],
		data:[{ 'accidentType':'出险类型','lostGoods':'丢货','damaged':'破损','wet':'潮湿',
				'pollution':'污染','miscellaneous':'其他','total':'合计'}]
	});
};

MemberViewDataTest.prototype.fnARecentClaimsListStore = function(){
	return Ext.create('Ext.data.Store',{
		storeId:'ARecentClaimsListStoreId',
		fields:['waybillNumber','startingSector','accidentType','claimTypes','claimAmount','theState'],
		data:[ {'waybillNumber':'运单号','startingSector':'出发部门','accidentType':'出险类型',
				'claimTypes':'理赔类型','claimAmount':'理赔金额','theState':'所处状态'},
			   {'waybillNumber':'运单号','startingSector':'出发部门','accidentType':'出险类型',
				'claimTypes':'理赔类型','claimAmount':'理赔金额','theState':'所处状态'}	]
	});
};

MemberViewDataTest.prototype.fnARecentClaimsListStore = function(){
	return Ext.create('Ext.data.Store',{
		storeId:'ARecentClaimsListStoreId',
		fields:['waybillNumber','startingSector','accidentType','claimTypes','claimAmount','theState'],
		data:[ {'waybillNumber':'拜访时间','startingSector':'出发部门','accidentType':'出险类型',
				'claimTypes':'理赔类型','claimAmount':'理赔金额','theState':'所处状态'},
			   {'waybillNumber':'运单号','startingSector':'出发部门','accidentType':'出险类型',
				'claimTypes':'理赔类型','claimAmount':'理赔金额','theState':'所处状态'}	]
	});
};

MemberViewDataTest.prototype.fnInformationMidBtmStore = function(){
	return Ext.create('Ext.data.Store',{
//		storeId:'InformationMidBtmStoreID',
		fields:['visitingTime','investigators','accessObject','customerRequirType',
		        'customerRequirDescrip','solution','customerAdvice'],
		data:[ {'visitingTime':'拜访时间','investigators':'拜访人员','accessObject':'拜访对象',
				'customerRequirType':'客户需求类型','customerRequirDescrip':'客户需求描述',
				'solution':'解决办法','customerAdvice':'客户建议'}	]
	});
};

MemberViewDataTest.prototype.fnInformationBtmStore = function(){
	return Ext.create('Ext.data.Store',{
		storeId:'InformationBtmStoreId',
		fields:['auditNumber','paymentType','preferentialType','effectiveDate','expirationDate',
		        'monthlyAmount','protocol','contactMobilePhone'],
		data:[ {'auditNumber':'审核编号','paymentType':'付款方式','preferentialType':'优惠类型',
				'effectiveDate':'生效日期','expirationDate':'失效日期','monthlyAmount':'月结额度',
				'protocol':'协议联系人','contactMobilePhone':'联系人手机'}	]
	});
};

Ext.create('Ext.data.Store', {
    storeId:'ContactInforTopStoreId',
    fields:['orderNumber', 'waybillNumber', 'orderSources','shipper','goodsNames','number','weight','volume'],
    data:{'items':[
        { 	"orderNumber":'张三', "waybillNumber":"李四", "orderSources":"XXX", "shipper":"1534924903", "goodsNames":"021", 'number':"5151", "weight":"555kg",
        	"volume":"tz"},
        { 	"orderNumber":'张三', "waybillNumber":"李四", "orderSources":"XXX", "shipper":"1534924903", "goodsNames":"021", 'number':"5151", "weight":"555kg",
	        "volume":"tz" }
    ]},
    proxy: {
        type: 'memory',
        reader: {
            type: 'json',
            root: 'items'
        }
    }
});

Ext.create('Ext.data.Store', {
    storeId:'FinancialInforStoreId',
    fields:['planMan', 'execute', 'contact','mobile','phoneNum','maintenance','progrutionTime','progctionTime','prograTime'],
    data:{'items':[
        { 	'planMan': '张三',"execute":"李四","contact":"XXX","mobile":"1534924903",
        	'phoneNum':'021-13489472','maintenance':'','progrutionTime':'2012-03-02','prograTime':'fuck' }
    ]},
    proxy: {
        type: 'memory',
        reader: {
            type: 'json',
            root: 'items'
        }
    }
});

Ext.create('Ext.data.Store', {
    storeId:'ContInforStoreId',
    fields:['planMan', 'execute', 'contact','mobile','phoneNum','maintenance','programExecutionTime'],
    // 序号、计划制定人、执行人、联系人姓名、手机号码、固定电话、维护方式、日程执行时间
    data:{'items':[
        { 	'planMan': '张三',"execute":"李四","contact":"XXX","mobile":"1534924903",
        	'phoneNum':'021-13489472','maintenance':'','programExecutionTime':'2012-03-02' }
    ]},
    proxy: {
        type: 'memory',
        reader: {
            type: 'json',
            root: 'items'
        }
    }
});

Ext.create('Ext.data.Store', {
    storeId:'PlanScheduleDetailsStoreId',
    fields:['planMan', 'execute', 'contact','mobile','phoneNum','maintenance','programExecutionTime'],
    // 序号、计划制定人、执行人、联系人姓名、手机号码、固定电话、维护方式、日程执行时间
    data:{'items':[
        { 	'planMan': '张三',"execute":"李四","contact":"XXX","mobile":"1534924903",
        	'phoneNum':'021-13489472','maintenance':'','programExecutionTime':'2012-03-02' }
    ]},
    proxy: {
        type: 'memory',
        reader: {
            type: 'json',
            root: 'items'
        }
    }
});

Ext.create('Ext.data.Store', {
    storeId:'MaintenanceRecordsStoreId',
    fields:['Executor', 'contact', 'mobile','phoneNum','classification','needsAndProblems','solvingMethod','maintenanceSty','maintenanceTime'],
    // 执行人、联系人姓名、手机号码、固定电话、需求分类、需求及问题、需求问题解决方法、具体维护方式、维护时间
    data:{'items':[
        { 	"Executor": '张三',"contact":"李四","mobile":"XXX","phoneNum":"1534924903",
        	"classification":"021",'needsAndProblems':"","solvingMethod":"51561",
        	"maintenanceSty":'egw','maintenanceTime':'gwehwh' }
    ]},
    proxy: {
        type: 'memory',
        reader: {
            type: 'json',
            root: 'items'
        }
    }
});

Ext.create('Ext.data.Store', {
    storeId:'OrderInforGridStoreId',
    fields:['orderNumber', 'waybillNumber', 'orderSources','shipper','goodsNames','number','weight','volume','orderStatus','originatingNetwork','acceptingDepartment'],
    data:{'items':[
        { 	"orderNumber":'张三', "waybillNumber":"李四", "orderSources":"XXX", "shipper":"1534924903", "goodsNames":"021", 'number':"5151", "weight":"555kg",
        	"volume":"tz", "orderStatus":"状态", "originatingNetwork":"上海", "acceptingDepartment":"受理" }
    ]},
    proxy: {
        type: 'memory',
        reader: {
            type: 'json',
            root: 'items'
        }
    }
});


Ext.create('Ext.data.Store', {
    storeId:'IntegrationGridStoreId',
    fields:['orderNumber', 'waybillNumber', 'orderSources','shipper','goodsNames','number','weight','volume'],
    data:{'items':[
        { 	"orderNumber":'张三', "waybillNumber":"李四", "orderSources":"XXX", "shipper":"1534924903", "goodsNames":"021", 'number':"5151", "weight":"555kg",
        	"volume":"tz" }
    ]},
    proxy: {
        type: 'memory',
        reader: {
            type: 'json',
            root: 'items'
        }
    }
});

Ext.create('Ext.data.Store', {
    storeId:'ComplaintGridStoreId',
    fields:['orderNumber', 'waybillNumber', 'orderSources','shipper','goodsNames','number','weight','volume'],
    data:{'items':[
        { 	"orderNumber":'张三', "waybillNumber":"李四", "orderSources":"XXX", "shipper":"1534924903", "goodsNames":"021", 'number':"5151", "weight":"555kg",
        	"volume":"tz","volme":"tz" },
        { 	"orderNumber":'张三', "waybillNumber":"李四", "orderSources":"XXX", "shipper":"1534924903", "goodsNames":"021", 'number':"5151", "weight":"555kg",
	        	"volume":"tz","volme":"tz" }
    ]},
    proxy: {
        type: 'memory',
        reader: {
            type: 'json',
            root: 'items'
        }
    }
});

Ext.create('Ext.data.Store', {
    storeId:'ClaimsGridStoreId',
    fields:['orderNumber', 'waybillNumber', 'orderSources','shipper','goodsNames','number','weight','volume'],
    data:{'items':[
        { 	"orderNumber":'张三', "waybillNumber":"李四", "orderSources":"XXX", "shipper":"1534924903", "goodsNames":"021", 'number':"5151", "weight":"555kg",
        	"volume":"tz","volme":"tz" },
        { 	"orderNumber":'张三', "waybillNumber":"李四", "orderSources":"XXX", "shipper":"1534924903", "goodsNames":"021", 'number':"5151", "weight":"555kg",
	        	"volume":"tz" }
    ]},
    proxy: {
        type: 'memory',
        reader: {
            type: 'json',
            root: 'items'
        }
    }
});

Ext.create('Ext.data.Store', {
    storeId:'ClaimsGridStoreId',
    fields:['orderNumber', 'waybillNumber', 'orderSources','shipper','goodsNames','number','weight','volume'],
    data:{'items':[
        { 	"orderNumber":'张三', "waybillNumber":"李四", "orderSources":"XXX", "shipper":"1534924903", "goodsNames":"021", 'number':"5151", "weight":"555kg",
        	"volume":"tz","volme":"tz" },
        { 	"orderNumber":'张三', "waybillNumber":"李四", "orderSources":"XXX", "shipper":"1534924903", "goodsNames":"021", 'number':"5151", "weight":"555kg",
	        	"volume":"tz" }
    ]},
    proxy: {
        type: 'memory',
        reader: {
            type: 'json',
            root: 'items'
        }
    }
});