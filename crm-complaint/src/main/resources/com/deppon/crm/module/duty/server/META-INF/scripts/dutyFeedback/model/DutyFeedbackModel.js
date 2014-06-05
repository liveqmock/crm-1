


/**
 * 工单责任反馈页面 集合 model
 */
Ext.define('DutyFeedbackModel',{
	extend:'Ext.data.Model',
	fields:[
		{name:'dutyId',type:'string'},/*工单责任id*/
		{name:'voucherNumber',type:'string'},/*凭证号*/
		{name:'dealNumber',type:'string'},/*处理编号*/
		{name:'caller',type:'string'},/*来电人*/
		{name:'reportType',type:'string'},/*上报类型*/
		{name:'surveyResult',type:'String'},/*调查结果*/
		{name:'reporter',type:'String'},/*上报人*/
		{name:'reportTime',type:'Date',defaultValue:null,convert:DpUtil.changeLongToDate},/*上报时间*/
		{name:'appDutyUserId',type:'String'},/*责任划分人ID*/
		{name:'appDutyUser',type:'String'},/*责任划分人*/
		{name:'appDutyTime',type:'Date',defaultValue:null,convert:DpUtil.changeLongToDate},/*责任划分时间*/
		{name:'dutyResultId',type:'String'},/*责任划分结果Id*/
		{name:'dutyPerId',type:'String'},/*责任人/部门ID*/
		{name:'divideType',type:'String'},/*划分类型*/
		{name:'dutyPerName',type:'String'},/*责任人/部门*/
		{name:'dutyDeadLine',type:'Date',defaultValue:null,convert:DpUtil.changeLongToDate},/*反馈时限*/
		{name:'dutyStatus',type:'String'},/*责任状态*/
		{name:'rebindNo',type:'string'},/*重复工单码*/
		{name:'complaintid',type:'string'},/*工单编号*/
		{name:'dutyDepartmentCC',type:'string'},/*部门类型 0 三个经营本部内的部门，1 特殊部门，-1 在 三个经营本部外的部门*/
		{name:'businessModel',type:'string'}/*业务模式*/
	]
});