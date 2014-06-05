package com.deppon.crm.module.scheduler.shared.domain;

import com.deppon.foss.framework.entity.BaseEntity;
/**
 * 
 * <p>
 * Description:ActionLog实体<br />
 * </p>
 * @title ActionLog.java
 * @package com.deppon.crm.module.scheduler.shared.domain 
 * @author 华龙
 * @version 0.1 2012-11-19
 */
public class ActionLog extends BaseEntity
{
  private String moduleName;
  private String requestUri;
  private Integer countRequest;
  private Integer reqdurationTimes;
  private Integer reqdurationAvgTimes;
  private Integer maxreqdurationTime;

  public String getModuleName()
  {
    return this.moduleName;
  }

  public void setModuleName(String moduleName) {
    this.moduleName = moduleName;
  }

  public String getRequestUri() {
    return this.requestUri;
  }

  public void setRequestUri(String requestUri) {
    this.requestUri = requestUri;
  }

  public Integer getCountRequest() {
    return this.countRequest;
  }

  public void setCountRequest(Integer countRequest) {
    this.countRequest = countRequest;
  }

public Integer getReqdurationTimes() {
	return reqdurationTimes;
}

public void setReqdurationTimes(Integer reqdurationTimes) {
	this.reqdurationTimes = reqdurationTimes;
}

public Integer getReqdurationAvgTimes() {
	return reqdurationAvgTimes;
}

public void setReqdurationAvgTimes(Integer reqdurationAvgTimes) {
	this.reqdurationAvgTimes = reqdurationAvgTimes;
}

public Integer getMaxreqdurationTime() {
	return maxreqdurationTime;
}

public void setMaxreqdurationTime(Integer maxreqdurationTime) {
	this.maxreqdurationTime = maxreqdurationTime;
}


}