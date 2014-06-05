/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title Province.java
 * @package com.deppon.crm.module.common.shared.domain 
 * @author xiaoqiang
 * @version 0.1 2012-3-15
 */
package com.deppon.crm.module.common.shared.domain;

import java.math.BigDecimal;
import java.util.List;

import com.deppon.foss.framework.entity.BaseEntity;

/**   
 * <p>
 * Description:省份基础资料<br />
 * </p>
 * @title Province.java
 * @package com.deppon.crm.module.common.shared.domain 
 * @author 毛建强
 * @version 0.1 2012-3-15
 */

public class Province extends BaseEntity {

	private static final long serialVersionUID = -6534422400997529595L;
	
	//主键ID
	private BigDecimal fid;
	public BigDecimal getFid() {
		return fid;
	}
	public void setFid(BigDecimal fid) {
		this.fid = fid;
	}
	
	//省份ID
	private BigDecimal provinceID;
	
	//名称
	private String name;
	//编码
	private String number;
	//拼音
	private String pinyin;
	//拼音简称
	private String pyjm;
	//状态
	private String status;
	
	private List<City> cityList;
	//最后修改人姓名
	private String lastModifyName;
	public List<City> getCityList() {
		return cityList;
	}
	public void setCityList(List<City> cityList) {
		this.cityList = cityList;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}

	public BigDecimal getProvinceID() {
		return provinceID;
	}
	public void setProvinceID(BigDecimal provinceID) {
		this.provinceID = provinceID;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getPinyin() {
		return pinyin;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	public String getPyjm() {
		return pyjm;
	}
	public void setPyjm(String pyjm) {
		this.pyjm = pyjm;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLastModifyName() {
		return lastModifyName;
	}
	public void setLastModifyName(String lastModifyName) {
		this.lastModifyName = lastModifyName;
	}
	
}
