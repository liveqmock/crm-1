/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title City.java
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
 * Description:城市<br />
 * </p>
 * @title City.java
 * @package com.deppon.crm.module.common.shared.domain 
 * @author 毛建强
 * @version 0.1 2012-3-15
 */

public class City extends BaseEntity{

	private static final long serialVersionUID = 4730524775944673173L;
	
	//主键ID
		private BigDecimal fid;
		
		//城市ID
		private BigDecimal cityID;
		
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
		//城市区号
		private String cityNumber;
		//是否直辖市
		private Integer isDirCity;
		//省份
		private Province province;
		//省份
		private Integer provinceId;
		//省份名称
		private String provinceName;
		//最后修改人名字
		private String lastModifyName;
		private List<Area> areaList;
		
		public List<Area> getAreaList() {
			return areaList;
		}
		public BigDecimal getCityID() {
			return cityID;
		}
		public void setCityID(BigDecimal cityID) {
			this.cityID = cityID;
		}
		public void setAreaList(List<Area> areaList) {
			this.areaList = areaList;
		}
		public BigDecimal getFid() {
			return fid;
		}
		public void setFid(BigDecimal fid) {
			this.fid = fid;
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
		public String getCityNumber() {
			return cityNumber;
		}
		public void setCityNumber(String cityNumber) {
			this.cityNumber = cityNumber;
		}
		public String getProvinceName() {
			return provinceName;
		}
		public void setProvinceName(String provinceName) {
			this.provinceName = provinceName;
		}
		public Integer getIsDirCity() {
			return isDirCity;
		}
		public void setIsDirCity(Integer isDirCity) {
			this.isDirCity = isDirCity;
		}
		public Integer getProvinceId() {
			return provinceId;
		}
		public void setProvinceId(Integer provinceId) {
			this.provinceId = provinceId;
		}
		public Province getProvince() {
			return province;
		}
		public void setProvince(Province province) {
			this.province = province;
		}
		public String getLastModifyName() {
			return lastModifyName;
		}
		public void setLastModifyName(String lastModifyName) {
			this.lastModifyName = lastModifyName;
		}
		
}
