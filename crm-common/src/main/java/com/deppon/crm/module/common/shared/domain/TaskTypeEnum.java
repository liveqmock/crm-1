package com.deppon.crm.module.common.shared.domain;

public enum TaskTypeEnum {
	
	//未受理订单
	NOACCEPT_ORDER("1"),
	
	//在线消息
	ONLINE_MESSAGE("2"),
	
	//理赔
	COMPENSATE("3"),
	
	//未读邮件
	EMAIL("4");
	
	
	
	//获得枚举的值
	public final String value;
	
	public String getValue() {
		return value;
	}

	private TaskTypeEnum(String value) {
		this.value = value;
	}
	
	
	//定义方法获得枚举对象
	  public static TaskTypeEnum getEnum(String i)
	    {
		  TaskTypeEnum taskment =null;
		  TaskTypeEnum[] list =TaskTypeEnum.values();
	       
	        for(int a=0;a<list.length;a++)
	        {
	           if(list[a].value.equals(i))
	           {
	        	   taskment =list[a];
	        	 // break;
	           }  
	        }
	        return taskment;
	    }
}
