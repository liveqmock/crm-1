/**
 * 工单基础资料Model
 */
Ext.define('BaseInfoModel', {		 
    extend: 'Ext.data.Model',
	fields :[ {
				name : 'id'			 
			},{
				name : 'level'
			},{
				name : 'baseInfo'
			},{
				name : 'deallan'
			},{
				name : 'feedbackLimit'
			},{
				name : 'procLimit'
			},{
				name : 'procStandard'
			},{
				name : 'typeCode'
			},{
				name : 'classCode'
			},{
				name : 'parentId'
			},{
				name : 'isLeaf'
			}
	  	]
});