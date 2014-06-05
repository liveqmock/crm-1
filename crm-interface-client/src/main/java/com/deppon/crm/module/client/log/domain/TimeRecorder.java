package com.deppon.crm.module.client.log.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TimeRecorder {

	public static Map<String,Date> timeMap=new HashMap<String,Date>();

	public static Map<String, Date> getTimeMap() {
		return timeMap;
	}

	public static void setTimeMap(Map<String, Date> timeMap) {
		TimeRecorder.timeMap = timeMap;
	}
}
