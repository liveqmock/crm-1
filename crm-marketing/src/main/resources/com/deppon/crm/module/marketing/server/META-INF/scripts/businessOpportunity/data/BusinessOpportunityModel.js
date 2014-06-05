/**.
 * <p>
 * 写有商机管理的相关model<br/>
 * <p>
 */



/**.
 * <p>
 * 商机信息MODEL<br/>
 * <p>
 * @returns {CRM.BO.BusinessOpportunity}
 */
Ext.define('CRM.BO.BusinessOpportunity', {
    extend: 'Ext.data.Model',
    fields : [{
		    //唯一标示
			name : 'id'
		},{
    	    //招投标项目
			name : 'isBidProject'
		},{
    	    //预计发货金额
			name : 'expectDeliveryAmount'
		},{
			//预计成功时间 
			name : 'expectSuccessDate',
			type: 'Date',
			defaultValue:null,
			convert: DpUtil.changeLongToDate
		},{
			//预计成功几率
			name : 'expectSuccessOdds'
		},{
			//商机状态
			name : 'boStatus'
		},{
			//商机确认
			name : 'isBOConfirm'
		},{
			//商机名称
			name : 'boName'
		},{
			//商机执行人
			name : 'operator'
		},{
			//商机描述
			name : 'boDesc'
		},{
			//商机阶段
			name : 'boStep'
		},{
			//商机创建时间
			name : 'createTime',
			type: 'Date',
			defaultValue:null,
			convert: DpUtil.changeLongToDate
		},{
			//商机创建人
			name : 'creator'
		},{
			//商机编码
			name : 'boNumber'
		},{
			//客户需求简介
			name : 'customerDemandDesc'
		},{
			//客户相关信息
			name : 'customer'
		},{
			//竞争情况信息
			name : 'competitiveInfo'
		},{
			//解决方案简述
			name : 'solutionDesc'
		},{
			//关闭原因描述
			name : 'closeReasonDesc'
		},{
			//关闭原因键值
			name : 'closeReasonCode'
		},{
			//最后修改时间
			name : 'modifyTime',
			type: 'Date',
			defaultValue:null,
			convert: DpUtil.changeLongToDate
		},{
			//最后修改人
			name : 'modifier'
		},{
			//所属部门ID
			name : 'deptId'
		},{
            //创建部门名称 auth:李春雨
            name : 'deptName'
        }]
});


/**.
 * <p>
 * 商机查询信息MODEL<br/>
 * <p>
 * @returns {CRM.BO.BusinessOpportunityCondition}
 */
Ext.define('CRM.BO.BusinessOpportunityCondition', {
    extend: 'Ext.data.Model',
    fields : [{
			//客户名称
			name : 'customerName'
		},{
			//客户编号
			name : 'customerNum'
		},{
			//联系人姓名
			name : 'contactName'
		},{
			//联系人电话
			name : 'contactPhone'
		},{
			//联系人手机
			name : 'contactMobile'
		},{
			//商机状态
			name : 'boStatus'
		},{
			//商机名称
			name : 'boName'
		},{
			//商机阶段
			name : 'boStep'
		},{
			//商机编码
			name : 'boNumber'
		},{
			//开始时间
			name : 'beginTime',
			type: 'Date',
			defaultValue:null,
			convert: DpUtil.changeLongToDate
		},{
			//结束时间
			name : 'endTime',
			type: 'Date',
			defaultValue:null,
			convert: DpUtil.changeLongToDate
		}]
});

/**.
 * <p>
 * 商机责任人MODEL<br/>
 * <p>
 * @returns {CRM.BO.BusinessOpportunityCondition}
 */
Ext.define('CRM.BO.EmpModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		// id
		name : 'id',
		type : 'string'
	}, {
		// 姓名
		name : 'empName',
		type : 'string'
	} ]
});

/**.
 * <p>
 * 商机日程信息MODEL<br/>
 * <p>
 * @returns {CRM.BO.SearchSchedule}
 */
Ext.define('CRM.BO.SearchSchedule', {
    extend: 'Ext.data.Model',
    fields : [{
		    //唯一标示(暂时兼日程主题)
			name : 'id'
		},{
			//日程状态
			name : 'scheduleStatus'
		},{
			//客户名称
			name : 'custName'
		},{
			//联系人名称
			name : 'linkManName'
		},{
			//联系人手机
			name : 'linkManMobeilPhone'
		},{
			//联系人电话
			name : 'linkManTelePhone'
		},{
			//日程时间
			name : 'scheduleDate',
			type: 'Date',
			defaultValue:null,
			convert: DpUtil.changeLongToDate
		},{
			//日程类型
			name : 'scheduleType',
		},{
			//开始时间
			name : 'createStartTime',
			type: 'Date',
			defaultValue:null,
			convert: DpUtil.changeLongToDate
		},{
			//结束时间
			name : 'createEndTime',
			type: 'Date',
			defaultValue:null,
			convert: DpUtil.changeLongToDate
		},{
			//日程来源
			name : 'comFrom'
		},{
			//日程来源ID
			name : 'comFromId'
		},{
			//日程主题
			name : 'marketRemark'
		}]
});

//会员联系人Model
Ext.define('CRM.BO.ContactModel',{
	extend:'Ext.data.Model',
	fields:[
	        {name:'id',type:'string'},
	        // 联系人编码
	        {name:'number',type:'string'}, 
	        // 联系人姓名
	        {name:'name',type:'string'},
	        //证件类型
	        {name:'cardTypeCon',type:'string'},
	        // 身份证号
	        {name:'idCard',type:'string'},
	        // 联系人类型
	        {name:'linkmanType',type:'string'},
	        // 是否主联系人
	        {name:'isMainLinkMan',type:'boolean'},
	        // 性别 
	        {name:'sex',type:'string'},
	        // 固定电话
	        {name:'telPhone',type:'string'},
	        // 手机号码
	        {name:'mobilePhone',type:'string'},
	        // 职务
	        {name:'duty',type:'string'},
	        // 任职部门
	        {name:'dutyDept',type:'string'},
	        // 出生日期
	        {name:'bornDate',type:'date',convert: DpUtil.changeLongToDate},
	        // 获知公司途径
	        {name:'gainave',type:'string'},
	        // 物流决定权 
	        {name:'decisionRight',type:'string'}]
});