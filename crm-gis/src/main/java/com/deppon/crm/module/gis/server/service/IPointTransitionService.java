package com.deppon.crm.module.gis.server.service;

import java.util.Map;

public interface IPointTransitionService {
	String transitionPointByOffset(String google);
	String googleConvertBaiduByWs(String google);
}
