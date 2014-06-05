/**
*散客Data层
*/
Ext.define('ScatterUpgradeStoreTest',{
	extend:'Ext.data.Store',
	model:'UpGradeCustomerModel',
	autoLoad:true,
	proxy:{
		type:'memory',
		data:[{id:'1',belongDeptId:'1',fitId:'1',custId:'1',nowLevel:'1',remark:'1',status:'1', batch:'1',belongdeptName:'1',customerName:'1',contactName:'1', phone:'1',tel:'1',targetLevel:'1'}
				,{id:'2',belongDeptId:'2',fitId:'1',custId:'1',nowLevel:'1',remark:'1',status:'1', batch:'1',belongdeptName:'1',customerName:'1',contactName:'1', phone:'1',tel:'1',targetLevel:'1'}
				,{id:'3',belongDeptId:'3',fitId:'1',custId:'1',nowLevel:'1',remark:'1',status:'1', batch:'1',belongdeptName:'1',customerName:'1',contactName:'1', phone:'1',tel:'1',targetLevel:'1'}
				,{id:'4',belongDeptId:'4',fitId:'1',custId:'1',nowLevel:'1',remark:'1',status:'1', batch:'1',belongdeptName:'1',customerName:'1',contactName:'1', phone:'1',tel:'1',targetLevel:'1'}
				,{id:'5',belongDeptId:'5',fitId:'1',custId:'1',nowLevel:'1',remark:'1',status:'1', batch:'1',belongdeptName:'1',customerName:'1',contactName:'1', phone:'1',tel:'1',targetLevel:'1'}
				,{id:'6',belongDeptId:'6',fitId:'1',custId:'1',nowLevel:'1',remark:'1',status:'1', batch:'1',belongdeptName:'1',customerName:'1',contactName:'1', phone:'1',tel:'1',targetLevel:'1'}
				,{id:'7',belongDeptId:'7',fitId:'1',custId:'1',nowLevel:'1',remark:'1',status:'1', batch:'1',belongdeptName:'1',customerName:'1',contactName:'1', phone:'1',tel:'1',targetLevel:'1'}
				,{id:'8',belongDeptId:'8',fitId:'1',custId:'1',nowLevel:'1',remark:'1',status:'1', batch:'1',belongdeptName:'1',customerName:'1',contactName:'1', phone:'1',tel:'1',targetLevel:'1'}
				,{id:'9',belongDeptId:'9',fitId:'1',custId:'1',nowLevel:'1',remark:'1',status:'1', batch:'1',belongdeptName:'1',customerName:'1',contactName:'1', phone:'1',tel:'1',targetLevel:'1'}
				,{id:'10',belongDeptId:'10',fitId:'1',custId:'1',nowLevel:'1',remark:'1',status:'1', batch:'1',belongdeptName:'1',customerName:'1',contactName:'1', phone:'1',tel:'1',targetLevel:'1'}
				,{id:'11',belongDeptId:'11',fitId:'1',custId:'1',nowLevel:'1',remark:'1',status:'1', batch:'1',belongdeptName:'1',customerName:'1',contactName:'1', phone:'1',tel:'1',targetLevel:'1'}
				,{id:'12',belongDeptId:'12',fitId:'1',custId:'1',nowLevel:'1',remark:'1',status:'1', batch:'1',belongdeptName:'1',customerName:'1',contactName:'1', phone:'1',tel:'1',targetLevel:'1'}
				,{id:'13',belongDeptId:'13',fitId:'1',custId:'1',nowLevel:'1',remark:'1',status:'1', batch:'1',belongdeptName:'1',customerName:'1',contactName:'1', phone:'1',tel:'1',targetLevel:'1'}
				,{id:'14',belongDeptId:'14',fitId:'1',custId:'1',nowLevel:'1',remark:'1',status:'1', batch:'1',belongdeptName:'1',customerName:'1',contactName:'1', phone:'1',tel:'1',targetLevel:'1'}
				,{id:'15',belongDeptId:'15',fitId:'1',custId:'1',nowLevel:'1',remark:'1',status:'1', batch:'1',belongdeptName:'1',customerName:'1',contactName:'1', phone:'1',tel:'1',targetLevel:'1'}
		      ]
	}
});
//散客升级列表查询结果
Ext.define('ScatterUpgradeDataTest',{
	extend:'MemberCustBasicDataTest',
	scatterUpgradeStoreTest:null,
	scatterGradeStoreTest:null,
	statisticsTimeStoreTest:null,
	//散客升级列表查询结果store
	getScatterUpGradeCustStore: function() {
		return this.scatterUpgradeStoreTest;
	},
	//初始化散客升级列表查询结果store
	initScatterUpGradeCustStore: function() {
		if (this.scatterUpgradeStoreTest == null) {
			this.scatterUpgradeStoreTest = Ext.create('ScatterUpgradeStoreTest');
		}
		return this.scatterUpgradeStoreTest;
	},
	//散客升级列表查询条件 目前等级store  SCATTER-GRADE
	getScatterGradeStore : function() {
		if (this.scatterGradeStoreTest == null) {
			this.scatterGradeStoreTest = Ext.create('ScatterGradeStore')
		}
		return this.scatterGradeStoreTest;
	},
	//散客升级列表统计时间
	getStatisticsTimeStore:function(){
		if(this.statisticsTimeStoreTest==null){
			this.statisticsTimeStoreTest = Ext.create('StatisticsTimeStore');
		}
		return this.statisticsTimeStoreTest;
	}
});