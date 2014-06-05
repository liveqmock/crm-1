//信用较差客户报表Model
Ext.define('WorsecustReportModel',{
	extend:'Ext.data.Model',
	fields:[  {name: 'managementDeptName'            //经营本部名称
		    },{name: 'busDeptName'                   //事业部名称
		    },{name: 'bigAreaDeptName'              //零担大区
			},{name: 'areaDeptName'                 //所属营业区
			},{name: 'attributionDeptName'          //归属部门
			},{name: 'responsibilityDeptName'       //责任部门
			},{name: 'custName'                      //客户名称
			},{name: 'custNumber'                   //客户编码
			},{name: 'isMonthlyCust'                //是否月结(1-是,0-否)
			},{name: 'repaymentDate'                //月结合同规定还款时间
			},{name: 'overdraftCreditAmount'        //临时欠款信用额度
			},{name: 'monthCreditAmount'            //月结信用额度
			},{name: 'overdraftCreditBalanceAmount'   //临时欠款信用额度余额
			},{name: 'monthCreditBalanceAmount'       //月结信用额度余额
			},{name: 'overdrftRecivableAmount'        //临时欠款当期应收金额
			},{name: 'lttRecivableAmount'             //月结当期应收金额
			},{name: 'monReceivableAmount'            //月结应收总额
			},{name: 'tdreceivableAmount'             //临欠应收总额
			},{name: 'deptCredit'                     //部门临欠额度
			},{name: 'overdraftCreditUserate'         //临时欠款信用额度使用率
			},{name: 'monthCreditUserate'             //月结信用额度使用率
			},{name: 'receivableAmount'               //部门应收总额
		    },{name: 'norepaymentTypeOne'               //未还款类型一
		    },{name: 'norepaymentTypeTwo'               //未还款类型二
		    },{name: 'norepaymentTypeThree'             //未还款类型三
		    },{name: 'norepaymentTypeFour'              //未还款类型四
		    },{name: 'norepaymentTypeFive'              //未还款类型
		    }]
});
