/**.
 * <p>
 * 写有开发阶段管理相关model<br/>
 * <p>
 */



/**.
 * <p>
 * 客户业务信息MODEL<br/>
 * <p>
 * @returns {CRM.ME.MemberExtend}
 */
Ext.define('CRM.ME.MemberExtend', {
    extend: 'Ext.data.Model',
    fields : [{
		    //客户ID
			name : 'custId'
		},{
    	    //货量潜力
			name : 'volumePotential'
		},{
    	    //合作意向
			name : 'coopIntention'
		},{
			//继续营销
			name : 'isContinueMark'
		},{
			//最近走货情况
			name : 'recentGoods'
		},{
			//客户需求
			name : 'custNeed'
		},{
			//合作物流公司
			name : 'recentCoop'
		},{
			//合作物流公司(备注)
			name : 'otherRecentCoop'
		},{
			//开发阶段
			name : 'developmentPhase'
		},{
			//适合我司承运
			name : 'isAccord'
		},{
			//适合我司承运备注
			name : 'accordMark'
		},{
			//是否季节性客户
			name :"isSeason"
		},{
			//客户预警状态
			name :"warnStatus "
		},{
			//信用预警次数
			name :" creditTimes"
		},{
			//是否信用较差客户 1 是 0 否
			name :"isPoorCredit"
		},{
			//最后一次信用预警时间
			name :"lastWarnTime"
		},{
			//预发货时间开始时间
			name :"preDeliverytBeginTime"
		},{
			//预发货时间结束时间
			name :"preDeliverytEndTime"
		},{
			//流失原因
			name :"lostCause"
		},{
			//其他流失原因
			name :"otherLostCause"
		},{
			//标记季节性客户次数
			name :"seasonTims"
		}]
});



/**.
 * <p>
 * 客户基础信息MODEL<br/>
 * <p>
 * @returns {CRM.ME.Member}
 */
Ext.define('CRM.ME.Member', {
    extend: 'Ext.data.Model',
    fields : [{
		    //客户ID
			name : 'id'
		},{
    	    //客户编码
			name : 'custNumber'
		},{
    	    //所属部门
			name : 'deptName'
		},{
			//客户名称
			name : 'custName'
		},{
			//客户类型
			name : 'custType'
		},{
			//客户行业
			name : 'tradeId'
		},{
			//二级行业
			name : 'secondTrade'
		},{
			//潜客来源
			name : 'potenSource'
		},{
			//联系人
			name : 'mainContact'
		}]
});