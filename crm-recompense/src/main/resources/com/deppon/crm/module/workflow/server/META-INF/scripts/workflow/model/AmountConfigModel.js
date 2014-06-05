/**
 * 审批金额配置Model
 */
Ext.define('AmountConfigModel', {		 
    extend: 'Ext.data.Model',
	fields :[ {
				name : 'id'			 
			},{
				name : 'mcName'
			},{
				name : 'mcDefiniTionName'
			},{
				name : 'currentApproStepName'
			},{
				name : 'currentApproStepNo'
			},{
				name : 'targetApproStepName'
			},{
				name : 'targetApproStepNo'
			},{
				name : 'minAmount'
			},{
				name : 'maxAmount'
			}
	  	]
});

Ext.define('ActivityModel', {		 
    extend: 'Ext.data.Model',
	 fields :[{
        name : 'activityDefId'
    }, {
        name : 'activityName'
    }]
});
