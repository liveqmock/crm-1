package com.deppon.crm.module.marketing.shared.domain.activity;

import com.deppon.foss.framework.entity.BaseEntity;

public class LineDept extends BaseEntity {
		//对应业务表ID
		private String conditionId;
		//对应业务类型
		private String conditionType;
		//出发部门名称
		private String  leavedDeptName;
		//出发部门标杆编码
		private String leaveDeptCode;
		//到达部门名称
		private String  arriveDeptName;
		//到达部门标杆编码
		private String  arriveDeptCode;
		/**
		 * <p>
		 * Description:这里写描述<br />
		 * </p>
		 * @author 李春雨
		 * @version 0.1 2014-4-11
		 */
		public String getConditionId() {
			return conditionId;
		}
		/**
		 * <p>
		 * Description:这里写描述<br />
		 * </p>
		 * @author 李春雨
		 * @version 0.1 2014-4-11
		 * @param conditionId the conditionId to set
		 */
		public void setConditionId(String conditionId) {
			this.conditionId = conditionId;
		}
		/**
		 * <p>
		 * Description:这里写描述<br />
		 * </p>
		 * @author 李春雨
		 * @version 0.1 2014-4-11
		 */
		public String getConditionType() {
			return conditionType;
		}
		/**
		 * <p>
		 * Description:这里写描述<br />
		 * </p>
		 * @author 李春雨
		 * @version 0.1 2014-4-11
		 * @param conditionType the conditionType to set
		 */
		public void setConditionType(String conditionType) {
			this.conditionType = conditionType;
		}
		/**
		 * <p>
		 * Description:这里写描述<br />
		 * </p>
		 * @author 李春雨
		 * @version 0.1 2014-4-11
		 */
		public String getLeavedDeptName() {
			return leavedDeptName;
		}
		/**
		 * <p>
		 * Description:这里写描述<br />
		 * </p>
		 * @author 李春雨
		 * @version 0.1 2014-4-11
		 * @param leavedDeptName the leavedDeptName to set
		 */
		public void setLeavedDeptName(String leavedDeptName) {
			this.leavedDeptName = leavedDeptName;
		}
		/**
		 * <p>
		 * Description:这里写描述<br />
		 * </p>
		 * @author 李春雨
		 * @version 0.1 2014-4-11
		 */
		public String getLeaveDeptCode() {
			return leaveDeptCode;
		}
		/**
		 * <p>
		 * Description:这里写描述<br />
		 * </p>
		 * @author 李春雨
		 * @version 0.1 2014-4-11
		 * @param leaveDeptCode the leaveDeptCode to set
		 */
		public void setLeaveDeptCode(String leaveDeptCode) {
			this.leaveDeptCode = leaveDeptCode;
		}
		/**
		 * <p>
		 * Description:这里写描述<br />
		 * </p>
		 * @author 李春雨
		 * @version 0.1 2014-4-11
		 */
		public String getArriveDeptName() {
			return arriveDeptName;
		}
		/**
		 * <p>
		 * Description:这里写描述<br />
		 * </p>
		 * @author 李春雨
		 * @version 0.1 2014-4-11
		 * @param arriveDeptName the arriveDeptName to set
		 */
		public void setArriveDeptName(String arriveDeptName) {
			this.arriveDeptName = arriveDeptName;
		}
		/**
		 * <p>
		 * Description:这里写描述<br />
		 * </p>
		 * @author 李春雨
		 * @version 0.1 2014-4-11
		 */
		public String getArriveDeptCode() {
			return arriveDeptCode;
		}
		/**
		 * <p>
		 * Description:这里写描述<br />
		 * </p>
		 * @author 李春雨
		 * @version 0.1 2014-4-11
		 * @param arriveDeptCode the arriveDeptCode to set
		 */
		public void setArriveDeptCode(String arriveDeptCode) {
			this.arriveDeptCode = arriveDeptCode;
		}
}
