package com.deppon.crm.module.interfaces.ows.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for CreateNewCouponRequest complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="CreateNewCouponRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="deptCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="eventType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="effectiveTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="loseEffectivenessTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="couponMoney" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="amountDeductibleType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="amountType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fullAmountMinus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="gradingDeductions" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="feeType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="feeAmountRequired" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="levelCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tradeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lineAeaRequirements" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="takeGoodsArea" type="{http://www.deppon.com/crm/remote/ows/domain/entity}TakeGoodsArea" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="smsContent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="couponDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreateNewCouponRequest", namespace = "http://www.deppon.com/crm/remote/ows/domain/entity", propOrder = {
		"deptCode", "eventType", "effectiveTime", "loseEffectivenessTime",
		"couponMoney", "amountDeductibleType", "amountType", "fullAmountMinus",
		"gradingDeductions", "feeType", "feeAmountRequired", "productCode",
		"orderCode", "levelCode", "tradeCode", "lineAeaRequirements",
		"takeGoodsArea", "smsContent", "couponDesc" })
public class CreateNewCouponRequest {

	@XmlElement(required = true)
	protected String deptCode;
	@XmlElement(required = true)
	protected String eventType;
	@XmlElement(required = true)
	@XmlSchemaType(name = "dateTime")
	protected Date effectiveTime;
	@XmlElement(required = true)
	@XmlSchemaType(name = "dateTime")
	protected Date loseEffectivenessTime;
	@XmlElement(required = true)
	protected String couponMoney;
	@XmlElement(required = true)
	protected String amountDeductibleType;
	@XmlElement(required = true)
	protected String amountType;
	@XmlElement(required = true)
	protected String fullAmountMinus;
	protected String gradingDeductions;
	protected String feeType;
	protected String feeAmountRequired;
	protected String productCode;
	protected String orderCode;
	protected String levelCode;
	protected String tradeCode;
	protected String lineAeaRequirements;
	protected List<TakeGoodsArea> takeGoodsArea;
	protected String smsContent;
	protected String couponDesc;

	/**
	 * Gets the value of the deptCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDeptCode() {
		return deptCode;
	}

	/**
	 * Sets the value of the deptCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDeptCode(String value) {
		this.deptCode = value;
	}

	/**
	 * Gets the value of the eventType property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getEventType() {
		return eventType;
	}

	/**
	 * Sets the value of the eventType property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setEventType(String value) {
		this.eventType = value;
	}

	/**
	 * Gets the value of the effectiveTime property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public Date getEffectiveTime() {
		return effectiveTime;
	}

	/**
	 * Sets the value of the effectiveTime property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setEffectiveTime(Date value) {
		this.effectiveTime = value;
	}

	/**
	 * Gets the value of the loseEffectivenessTime property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public Date getLoseEffectivenessTime() {
		return loseEffectivenessTime;
	}

	/**
	 * Sets the value of the loseEffectivenessTime property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setLoseEffectivenessTime(Date value) {
		this.loseEffectivenessTime = value;
	}

	/**
	 * Gets the value of the couponMoney property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCouponMoney() {
		return couponMoney;
	}

	/**
	 * Sets the value of the couponMoney property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCouponMoney(String value) {
		this.couponMoney = value;
	}

	/**
	 * Gets the value of the amountDeductibleType property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAmountDeductibleType() {
		return amountDeductibleType;
	}

	/**
	 * Sets the value of the amountDeductibleType property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAmountDeductibleType(String value) {
		this.amountDeductibleType = value;
	}

	/**
	 * Gets the value of the amountType property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAmountType() {
		return amountType;
	}

	/**
	 * Sets the value of the amountType property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAmountType(String value) {
		this.amountType = value;
	}

	/**
	 * Gets the value of the fullAmountMinus property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getFullAmountMinus() {
		return fullAmountMinus;
	}

	/**
	 * Sets the value of the fullAmountMinus property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setFullAmountMinus(String value) {
		this.fullAmountMinus = value;
	}

	/**
	 * Gets the value of the gradingDeductions property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getGradingDeductions() {
		return gradingDeductions;
	}

	/**
	 * Sets the value of the gradingDeductions property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setGradingDeductions(String value) {
		this.gradingDeductions = value;
	}

	/**
	 * Gets the value of the feeType property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getFeeType() {
		return feeType;
	}

	/**
	 * Sets the value of the feeType property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setFeeType(String value) {
		this.feeType = value;
	}

	/**
	 * Gets the value of the feeAmountRequired property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getFeeAmountRequired() {
		return feeAmountRequired;
	}

	/**
	 * Sets the value of the feeAmountRequired property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setFeeAmountRequired(String value) {
		this.feeAmountRequired = value;
	}

	/**
	 * Gets the value of the productCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getProductCode() {
		return productCode;
	}

	/**
	 * Sets the value of the productCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setProductCode(String value) {
		this.productCode = value;
	}

	/**
	 * Gets the value of the orderCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOrderCode() {
		return orderCode;
	}

	/**
	 * Sets the value of the orderCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOrderCode(String value) {
		this.orderCode = value;
	}

	/**
	 * Gets the value of the levelCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLevelCode() {
		return levelCode;
	}

	/**
	 * Sets the value of the levelCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLevelCode(String value) {
		this.levelCode = value;
	}

	/**
	 * Gets the value of the tradeCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTradeCode() {
		return tradeCode;
	}

	/**
	 * Sets the value of the tradeCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTradeCode(String value) {
		this.tradeCode = value;
	}

	/**
	 * Gets the value of the lineAeaRequirements property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLineAeaRequirements() {
		return lineAeaRequirements;
	}

	/**
	 * Sets the value of the lineAeaRequirements property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLineAeaRequirements(String value) {
		this.lineAeaRequirements = value;
	}

	/**
	 * Gets the value of the takeGoodsArea property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the takeGoodsArea property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getTakeGoodsArea().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link TakeGoodsArea }
	 * 
	 * 
	 */
	public List<TakeGoodsArea> getTakeGoodsArea() {
		if (takeGoodsArea == null) {
			takeGoodsArea = new ArrayList<TakeGoodsArea>();
		}
		return this.takeGoodsArea;
	}

	/**
	 * Gets the value of the smsContent property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSmsContent() {
		return smsContent;
	}

	/**
	 * Sets the value of the smsContent property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSmsContent(String value) {
		this.smsContent = value;
	}

	/**
	 * Gets the value of the couponDesc property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCouponDesc() {
		return couponDesc;
	}

	/**
	 * Sets the value of the couponDesc property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCouponDesc(String value) {
		this.couponDesc = value;
	}

}
