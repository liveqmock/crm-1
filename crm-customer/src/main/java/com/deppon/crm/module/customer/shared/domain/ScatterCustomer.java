package com.deppon.crm.module.customer.shared.domain;

import java.util.List;

import org.apache.log4j.Logger;


/**
 * The Class ScatterCustomer.
 *
 * @description : 散客信息
 * @author : 罗典
 * @createTime : 2012-2-27 14:29
 */
public class ScatterCustomer extends PotentialCustomer {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8568796007228180515L;
	private final Logger logger = Logger.getLogger(ScatterCustomer.class);

	
	// 身份证号
	/** The id number. */
	private String idNumber;
	// 公司规模
	/** The company size. */
	private String companySize;
	// 公司性质
	/** The company nature. */
	private String companyNature;
	// 税务登记号
	/** The taxregist no. */
	private String taxregistNo;
	// 客户性质
	/** The cust property. */
	private String custProperty;
	// 客户属性
	/** The cust nature. */
	private String custNature;
	// 备注
	/** The remark. */
	private String remark;
	// 出发部门
	/** The lea dept id. */
	private String leaDeptId;
	// 出发部门名称
	/** The lea dept name. */
	private String leaDeptName;
	// 到达部门名称
	/** The arr dept name. */
	private String arrDeptName;
	// 到达部门
	/** The arr dept id. */
	private String arrDeptId;
	// 维护人
	/** The prehu man. */
	private String prehuMan;
	
	//散客类型：普通散客"ORDINARY_SCATTER" 临代散客"FOSS_SCATTER"
	/** The scatter cust type. */
	private String scatterCustType;

	//临欠额度
	/** The velocity amount. */
	private int velocityAmount;
	//散客编码
	/** The scatter num. */
	private String scatterNum;
	//散客当前最近的操作日志
	// 财务是否完结
	private boolean finOver;
	//客户标签
	private List<CustLabel> custLabels;
	//潜客渠道来源
	private String potenSource;
	
	public List<CustLabel> getCustLabels() {
		return custLabels;
	}

	public void setCustLabels(List<CustLabel> custLabels) {
		this.custLabels = custLabels;
	}
	/**
	 * Gets the velocity amount.
	 *
	 * @return the velocity amount
	 */
	public int getVelocityAmount() {
		return velocityAmount;
	}

	/**
	 * Gets the scatter num.
	 *
	 * @return the scatter num
	 */
	public String getScatterNum() {
		return scatterNum;
	}

	/**
	 * Sets the scatter num.
	 *
	 * @param scatterNum the new scatter num
	 */
	public void setScatterNum(String scatterNum) {
		this.scatterNum = scatterNum;
	}

	/**
	 * Sets the velocity amount.
	 *
	 * @param velocityAmount the new velocity amount
	 */
	public void setVelocityAmount(int velocityAmount) {
		this.velocityAmount = velocityAmount;
	}

	/**
	 * Gets the scatter cust type.
	 *
	 * @return the scatter cust type
	 */
	public String getScatterCustType() {
		return scatterCustType;
	}

	/**
	 * Sets the scatter cust type.
	 *
	 * @param scatterCustType the new scatter cust type
	 */
	public void setScatterCustType(String scatterCustType) {
		this.scatterCustType = scatterCustType;
	}

	/**
	 * Instantiates a new scatter customer.
	 */
	public ScatterCustomer() {
		super();
	}


	/**
	 * Gets the id number.
	 *
	 * @return the id number
	 */
	public String getIdNumber() {
		return idNumber;
	}

	/**
	 * <p>
	 * Description:idNumber<br />
	 * </p>.
	 *
	 * @param idNumber the new id number
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	/**
	 * <p>
	 * Description:companySize<br />
	 * </p>.
	 *
	 * @return the company size
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getCompanySize() {
		return companySize;
	}

	/**
	 * <p>
	 * Description:companySize<br />
	 * </p>.
	 *
	 * @param companySize the new company size
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCompanySize(String companySize) {
		this.companySize = companySize;
	}

	/**
	 * <p>
	 * Description:companyNature<br />
	 * </p>.
	 *
	 * @return the company nature
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getCompanyNature() {
		return companyNature;
	}


	/**
	 * <p>
	 * Description:companyNature<br />
	 * </p>.
	 *
	 * @param companyNature the new company nature
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCompanyNature(String companyNature) {
		this.companyNature = companyNature;
	}

	/**
	 * <p>
	 * Description:taxregistNo<br />
	 * </p>.
	 *
	 * @return the taxregist no
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getTaxregistNo() {
		return taxregistNo;
	}

	/**
	 * <p>
	 * Description:taxregistNo<br />
	 * </p>.
	 *
	 * @param taxregistNo the new taxregist no
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setTaxregistNo(String taxregistNo) {
		this.taxregistNo = taxregistNo;
	}

	/**
	 * <p>
	 * Description:custProperty<br />
	 * </p>.
	 *
	 * @return the cust property
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getCustProperty() {
		return custProperty;
	}


	/**
	 * <p>
	 * Description:custProperty<br />
	 * </p>.
	 *
	 * @param custProperty the new cust property
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCustProperty(String custProperty) {
		this.custProperty = custProperty;
	}


	/**
	 * <p>
	 * Description:custNature<br />
	 * </p>.
	 *
	 * @return the cust nature
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getCustNature() {
		return custNature;
	}


	/**
	 * <p>
	 * Description:custNature<br />
	 * </p>.
	 *
	 * @param custNature the new cust nature
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setCustNature(String custNature) {
		this.custNature = custNature;
	}

	/**
	 * <p>
	 * Description:remark<br />
	 * </p>.
	 *
	 * @return the remark
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * <p>
	 * Description:remark<br />
	 * </p>.
	 *
	 * @param remark the new remark
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}


	/**
	 * <p>
	 * Description:leaDeptId<br />
	 * </p>.
	 *
	 * @return the lea dept id
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getLeaDeptId() {
		return leaDeptId;
	}


	/**
	 * <p>
	 * Description:leaDeptId<br />
	 * </p>.
	 *
	 * @param leaDeptId the new lea dept id
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setLeaDeptId(String leaDeptId) {
		this.leaDeptId = leaDeptId;
	}


	/**
	 * <p>
	 * Description:leaDeptName<br />
	 * </p>.
	 *
	 * @return the lea dept name
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getLeaDeptName() {
		return leaDeptName;
	}


	/**
	 * <p>
	 * Description:leaDeptName<br />
	 * </p>.
	 *
	 * @param leaDeptName the new lea dept name
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setLeaDeptName(String leaDeptName) {
		this.leaDeptName = leaDeptName;
	}


	/**
	 * <p>
	 * Description:arrDeptName<br />
	 * </p>.
	 *
	 * @return the arr dept name
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getArrDeptName() {
		return arrDeptName;
	}

	/**
	 * <p>
	 * Description:arrDeptName<br />
	 * </p>.
	 *
	 * @param arrDeptName the new arr dept name
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setArrDeptName(String arrDeptName) {
		this.arrDeptName = arrDeptName;
	}

	/**
	 * <p>
	 * Description:arrDeptId<br />
	 * </p>.
	 *
	 * @return the arr dept id
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getArrDeptId() {
		return arrDeptId;
	}


	/**
	 * <p>
	 * Description:arrDeptId<br />
	 * </p>.
	 *
	 * @param arrDeptId the new arr dept id
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setArrDeptId(String arrDeptId) {
		this.arrDeptId = arrDeptId;
	}


	/**
	 * <p>
	 * Description:prehuMan<br />
	 * </p>.
	 *
	 * @return the prehu man
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public String getPrehuMan() {
		return prehuMan;
	}

	/**
	 * <p>
	 * Description:prehuMan<br />
	 * </p>.
	 *
	 * @param prehuMan the new prehu man
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setPrehuMan(String prehuMan) {
		this.prehuMan = prehuMan;
	}

	public boolean isFinOver() {
		return finOver;
	}

	public void setFinOver(boolean finOver) {
		this.finOver = finOver;
	}

	/**
	 *@user pgj
	 *2013-8-2上午10:34:07
	 * @return potenSource : return the property potenSource.
	 */
	public String getPotenSource() {
		return potenSource;
	}

	/**
	 * @param potenSource : set the property potenSource.
	 */
	public void setPotenSource(String potenSource) {
		this.potenSource = potenSource;
	}

	public Logger getLogger() {
		return logger;
	}
}
