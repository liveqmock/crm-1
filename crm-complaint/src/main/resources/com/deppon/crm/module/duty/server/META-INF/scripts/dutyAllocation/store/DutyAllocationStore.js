/**
 * 工单责任划分查询结果Store
 */
DutyAllocationStore = function(){
	
};

Ext.define('ReceiveDutyStore',{
	extend:'Ext.data.Store',
	model:'DutySearchDetailModel',
	pageSize:20,
	autoLoad:true,
	proxy:{
		type:'ajax'
		,api:{read:'../duty/initReceiveDuty.action'}
		,actionMethods:'POST'
		,reader:{
			type:'json'
			,root:'receiveDutys'
			,totalProperty:'totalCount'
		}
	}
});
Ext.define('SearchReceiveDutyStore',{
	extend:'Ext.data.Store',
	model:'DutySearchDetailModel',
	pageSize:20,
	autoLoad:true,
	proxy:{
		type:'ajax'
		,api:{read:'../duty/searchDutyByManager.action'}
		,actionMethods:'POST'
		,reader:{
			type:'json'
			,root:'receiveDutys'
			,totalProperty:'totalCount'
		}
	}
});

/**
 * 查询业务范围集合- store
 */
Ext.define('DutyBusScopeStore',{
	extend:'Ext.data.Store',
	model:'DutyBasicLevelModel',
	autoLoad:false,
	proxy:{
		type:'ajax'
		,api:{read:'searchBusScopeByParentId.action'}
		,actionMethods:'POST'
		,reader:{
			type:'json',root:'basicLevelList'
		}
	}
});

/**
 * (只有有业务类型的业务向才能被查出)查询业务范围集合- store
 */
Ext.define('DutyBusScopeStoreOnly',{
	extend:'Ext.data.Store',
	model:'DutyBasicLevelModel',
	autoLoad:false,
	proxy:{
		type:'ajax'
		,api:{read:'searchBusScopeByParentIdOnly.action'}
		,actionMethods:'POST'
		,reader:{
			type:'json',root:'basicLevelList'
		}
	}
});


/**
 * 查询业务类型集合- store
 */
Ext.define('DutyBusTypeStore',{
	extend:'Ext.data.Store',
	model:'ProcResultModel',
	autoLoad:false,
	proxy:{
		type:'ajax'
		,api:{read:'searchProcResultList.action'}
		,actionMethods:'POST'
		,reader:{
			type:'json',root:'procResultList'
		}
	}
});


/**
 * 工单责任 result- store
 */
Ext.define('DutyResultStore',{
	extend:'Ext.data.Store',
	model:'DutyResultModel',
	autoLoad:false,
	proxy:{
		type:'ajax'
		,api:{read:'../duty/searchDutyResultList.action'}
		,actionMethods:'POST'
		,reader:{
			type:'json',root:'dutyResultList'
		}
	}/*,
	listeners:{
		load:function(store, records){
			Ext.each(records,function(record){
				if(Ext.isEmpty(record.get('old_dealType'))){old_dealType is null 表示是数据加载，则 old_dealType=divideType
					record.set('old_dealType',record.get('divideType'));
				}
				if(Ext.isEmpty(record.get('old_dutyPerId'))){old_dutyPerId is null 表示是数据加载，则 old_dutyPerId=dutyPerId
					record.set('old_dutyPerId',record.get('dutyPerId'));
				}
			});
		}
	}*///这个操作应该在后台进行，否则所有的都会进入到updateRecord中
});


/**
 * 工单责任 - 通知对象 store
 */
Ext.define('DutyBulletinStore',{
	extend:'Ext.data.Store',
	model:'DutyBulletinModel',
	autoLoad:false,
	proxy:{
		type:'ajax'
		,url:'searchInformUserList.action'
		,actionMethods:'POST'
		,reader:{
			type:'json',root:'informUserList'
		}
	}
});

/**
 * 上报类型
 * @type String
 */
ReportType = {
	'complaint':'COMPLAINT',
	'unusual':'UNUSUAL'
};




/**
 *  DepartmentStore 部门 store
 *  ,departmentList:null //部门集合
 */
Ext.define('DepartmentStore',{
	extend:'Ext.data.Store'
	,fields: [
		{name:'id',type:'String'}/*编号*/
		,{name:'deptName',type:'String'}/*部门名称*/
	]
	,pageSize:40
	,autoLoad:true
	,proxy:{
		type:'ajax'
		,url:'../complaint/searchDepartmentList.action'
		,actionMethods:'POST'
		,reader:{
			type:'json',root:'departmentList'
		}
	}
});


/**
 * 声明人员选择器store
 */
Ext.define('EmployeeStore',{
	extend : 'Ext.data.Store',
	fields: [
        {name: 'empId'},{name: 'empName'},
        {name: 'empCode'},{name: 'deptName'},
        {name: 'mobilePhone'},{name: 'position'}
    ],
	pageSize :40,
    proxy: {
        type: 'ajax',actionMethods : 'post',
        url : '../common/searchEmpByCondition.action',
        reader: {
            type: 'json',root: 'users'
        }
    }
});



/**
 * 基础信息,业务项 Store
 */
Ext.define('BusinessItemsStore',{
	extend:'Ext.data.Store',
	model:'BusinessItemsModel',
	autoLoad:false,
	proxy:{
		type:'ajax'
		,api:{read:'searchBasicInfoList.action'}
		,actionMethods:'POST'
		,reader:{
			type:'json'
			,root:'basicInfoList'
		}
	}
});

/**
 * (只有有业务类型的业务项才能被查出)基础信息,业务项 Store
 */
Ext.define('BusinessItemsStoreOnly',{
	extend:'Ext.data.Store',
	model:'BusinessItemsModel',
	autoLoad:false,
	proxy:{
		type:'ajax'
		,api:{read:'searchBasicInfoListOnly.action'}
		,actionMethods:'POST'
		,reader:{
			type:'json'
			,root:'basicInfoList'
		}
	}
});

DutyAllocationStore.prototype.receiveDuty = function(param,successFn,failureFn){
	var url = '../duty/receiveDuty.action';
	DpUtil.requestAjax(url,param,successFn,failureFn);
};
DutyAllocationStore.prototype.countUnreceiveDutyNum = function(param,successFn,failureFn){
	var url = '../duty/countUnreceiveDutyNum.action';
	DpUtil.requestAjax(url,param,successFn,failureFn);
};


var ActionFunction = {
	
	/**
	 * 查询责任详情
	 * @param {Object} params
	 * @param {Function} successFn
	 * @param {Function} failFn
	 */
	'duty_searchDutyById':function(params,successFn,failFn){
		var url = '../duty/searchDutyById.action';
		DutyUtil.requestAjax(url,params,successFn,failFn);
	}
	
};
//DutyAllocationStore.prototype.searchDutyByManager = function(param,successFn,failureFn){
//	var url = '../duty/searchDutyByManager.action';
//	DpUtil.requestAjax(url,param,successFn,failureFn);
//};
Ext.define('DutyAllocationResultStore',{
	extend:'Ext.data.Store',
	model:'DutySearchDetailModel'
});

/**
 * 责任划分 - 确认无责
 * @param {Object} param
 * @param {Function} successFn
 * @param {Function} failureFn
 */
DutyAllocationStore.prototype.confirmNotDuty=function(params,successFn,failFn){
	var url = '../duty/confirmNotDuty.action';
	DutyUtil.requestJsonAjax(url,params,successFn,failFn);
};
/**
 * 责任划分 - 暂存
 * @param {Object} param
 * @param {Function} successFn
 * @param {Function} failureFn
 */
DutyAllocationStore.prototype.temporarySave=function(params,successFn,failFn){
	var url = '../duty/temporarySave.action';
	DutyUtil.requestJsonAjax(url,params,successFn,failFn);
};

/**
 * 责任划分 - 提交
 * @param {Object} param
 * @param {Function} successFn
 * @param {Function} failureFn
 */
DutyAllocationStore.prototype.submitDutyDivide=function(params,successFn,failFn){
	var url = '../duty/submitDutyDivide.action';
	DutyUtil.requestJsonAjax(url,params,successFn,failFn);
};


/**
 * 责任划分 - 查询最新的处理经过
 * @param {Object} param
 * @param {Function} successFn
 * @param {Function} failureFn
 */
DutyAllocationStore.prototype.searchNewProcessPass=function(params,successFn,failFn){
	var url = '../duty/searchNewProcessPass.action';
	DutyUtil.requestAjax(url,params,successFn,failFn);
};


/**
 * 责任划分 - 得到反馈时限的默认值
 * @param {Object} param
 * @param {Function} successFn
 * @param {Function} failureFn
 */
DutyAllocationStore.prototype.serarchFeedbackTime=function(params,successFn,failFn){
	var url = '../duty/serarchFeedbackTime.action';
	DutyUtil.requestAjax(url,params,successFn,failFn);
};

