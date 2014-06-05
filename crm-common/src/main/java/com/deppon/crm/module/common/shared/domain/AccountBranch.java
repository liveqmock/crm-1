/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title Area.java
 * @package com.deppon.crm.module.common.shared.domain 
 * @author xiaoqiang
 * @version 0.1 2012-3-15
 */
package com.deppon.crm.module.common.shared.domain;


import java.util.Date;

import com.deppon.foss.framework.entity.BaseEntity;
/**
 * <p>
 * Description:银行分支<br />
 * </p>
 * @title AccountBranch.java
 * @package com.deppon.crm.module.common.shared.domain 
 * @author ouy
 * @version 0.1 2012-3-30
 */
public class AccountBranch extends BaseEntity {
	//主键
	private Integer fId;
	//创建时间
	private Date createTime;
	//创建用户id
	private Integer createUserId;
	//最后修改时间
	private Date lastUpdateTime;
	//最后修改人
	private Integer lastModifyUserId;
	//银行id
	private String bankId;
	//编码
	private String code;
	//姓名
	private String name;
	//省份id
	private String provinceId;
	//城市
	private String cityId;
	/**
	 * @description:配合银企添加属性。对应myBatis文件没有添加相应的配置信息
	 * @author 李国文
	 * @date 2012-12-04
	 */
	//是否禁用。1禁用，0有效
	private int cancel ;

	public int getCancel() {
		return cancel;
	}
	public void setCancel(int cancel) {
		this.cancel = cancel;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public Integer getLastModifyUserId() {
		return lastModifyUserId;
	}
	public void setLastModifyUserId(Integer lastModifyUserId) {
		this.lastModifyUserId = lastModifyUserId;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public Integer getfId() {
		return fId;
	}
	public void setfId(Integer fId) {
		this.fId = fId;
	}
}