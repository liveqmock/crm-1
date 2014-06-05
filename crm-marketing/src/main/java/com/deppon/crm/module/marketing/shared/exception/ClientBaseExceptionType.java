package com.deppon.crm.module.marketing.shared.exception;

public enum ClientBaseExceptionType {
	//客户群名称不能为空
	CLIENTBASE_ISNULL("i18n.ClientBaseManeger.clientBaseNameIsNull"),
	//客户群名称长度不得大于40
	CLIENTBASE_NAMELENGTHTHEN40("i18n.ClientBaseManeger.clientBaseNameLengthThen40"),
	//客户群名称已存在，请重新填写！
	CLIENTBASENAME_REPEAT("i18n.ClientBaseManeger.clientBaseNameRepeat"),
	//客户创建的起始时间或结束时间不能为空！
	CLIENTBASETIME_ONENULL("i18n.ClientBaseManeger.clientTimeOneNull"),
	//开始时间不能大于结束时间
	CLIENTBASE_STRATTHANENDTIME("i18n.ClientBaseManeger.startMoreThanEndTime"),
	//走货线路不能超过100条！
	CLIENTBASE_MORETHEN100("i18n.ClientBaseManeger.lineDeptMoreThan100"),
	//线路部门信息异常！
	CLIENTBASE_LINEDEPTEXCEPTION("i18n.ClientBaseManeger.lineDeptException"),
	//没有操作此条记录权限！
	CLIENTBASE_NOAUTHORITYMODIFY("i18n.ClientBaseManeger.NoAuthorityTomodify"),
	//删除异常
	CLIENTBASE_DELETEEXCEPTION("i18n.ClientBaseManeger.deleteException"),
	//只可操作未使用状态的客户群
	CLIENTBASE_ONELYUNUSED("i18n.ClientBaseManeger.onlyOperateUnusedClientBase"),
	//二级行业数据字段异常
	CLIENTBASE_DICTIONNARYEXCEPTION("i18n.ClientBaseManeger.secondTradeException");
	
	
	
	private String errCode;

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	
	private ClientBaseExceptionType(String errorCode) {
		this.errCode = errorCode;
	}
	
}
