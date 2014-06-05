package com.deppon.crm.module.sync.shared;

import com.deppon.foss.framework.exception.BusinessException;

public class SyncException extends BusinessException{
	public SyncException (){
		super();
	}
	public SyncException(String msg){
		super(msg);
	}
}
