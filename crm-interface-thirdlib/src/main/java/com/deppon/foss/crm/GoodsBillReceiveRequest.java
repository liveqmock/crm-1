
package com.deppon.foss.crm;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * �ύ�ӻ�����Ϣ
 *
 * <p>GoodsBillReceiveRequest complex type�� Java �ࡣ
 *
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 *
 * <pre>
 * &lt;complexType name="GoodsBillReceiveRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="custName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custMobile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receiveProvince" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="receiveCity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="receiveCounty" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="receiveDetailAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="consigneeAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="goodsName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="weight" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="cubage" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="packing" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pieces" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="size" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="desc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="transProperty" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="deliverMode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ladingStationId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="goodsType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="usingVehicleDeptNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="usingVehicleDeptId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="firstPickTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="lastPickTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="vehDeptNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="vehDeptId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="orderNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="orderedTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="orderType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="orderOwnDeptId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="creatorNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="creatorId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="carType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GoodsBillReceiveRequest", propOrder = {
    "custName",
    "custMobile",
    "custTel",
    "receiveProvince",
    "receiveCity",
    "receiveCounty",
    "receiveDetailAddress",
    "consigneeAddress",
    "goodsName",
    "weight",
    "cubage",
    "packing",
    "pieces",
    "size",
    "desc",
    "transProperty",
    "deliverMode",
    "ladingStationId",
    "goodsType",
    "usingVehicleDeptNum",
    "usingVehicleDeptId",
    "firstPickTime",
    "lastPickTime",
    "vehDeptNum",
    "vehDeptId",
    "orderNumber",
    "orderedTime",
    "orderType",
    "orderOwnDeptId",
    "creatorNum",
    "creatorId",
    "carType",
	"memberType",
    "couponNumber",
    "waybillNumber",
    "paidMethod"
})
public class GoodsBillReceiveRequest {

	protected String memberType;
    protected String custName;
    protected String custMobile;
    protected String custTel;
    @XmlElement(required = true)
    protected String receiveProvince;
    @XmlElement(required = true)
    protected String receiveCity;
    @XmlElement(required = true)
    protected String receiveCounty;
    @XmlElement(required = true)
    protected String receiveDetailAddress;
    @XmlElement(required = true)
    protected String consigneeAddress;
    @XmlElement(required = true)
    protected String goodsName;
    protected double weight;
    protected double cubage;
    @XmlElement(required = true)
    protected String packing;
    protected int pieces;
    @XmlElement(required = true)
    protected String size;
    protected String desc;
    @XmlElement(required = true)
    protected String transProperty;
    @XmlElement(required = true)
    protected String deliverMode;
    @XmlElement(required = true)
    protected String ladingStationId;
    protected String goodsType;
    @XmlElement(required = true)
    protected String usingVehicleDeptNum;
    @XmlElement(required = true)
    protected String usingVehicleDeptId;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date firstPickTime;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date lastPickTime;
    @XmlElement(required = true)
    protected String vehDeptNum;
    @XmlElement(required = true)
    protected String vehDeptId;
    @XmlElement(required = true)
    protected String orderNumber;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date orderedTime;
    @XmlElement(required = true)
    protected String orderType;
    @XmlElement(required = true)
    protected String orderOwnDeptId;
    @XmlElement(required = true)
    protected String creatorNum;
    @XmlElement(required = true)
    protected String creatorId;
    @XmlElement(required = true)
    protected String carType;
    protected String couponNumber;
    protected String waybillNumber;
    protected String paidMethod;

    /**
     * ��ȡcustName���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCustName() {
        return custName;
    }

    /**
     * ����custName���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCustName(String value) {
        this.custName = value;
    }

    /**
     * ��ȡcustMobile���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCustMobile() {
        return custMobile;
    }

    /**
     * ����custMobile���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCustMobile(String value) {
        this.custMobile = value;
    }

    /**
     * ��ȡcustTel���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCustTel() {
        return custTel;
    }

    /**
     * ����custTel���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCustTel(String value) {
        this.custTel = value;
    }

    /**
     * ��ȡreceiveProvince���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getReceiveProvince() {
        return receiveProvince;
    }

    /**
     * ����receiveProvince���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setReceiveProvince(String value) {
        this.receiveProvince = value;
    }

    /**
     * ��ȡreceiveCity���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getReceiveCity() {
        return receiveCity;
    }

    /**
     * ����receiveCity���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setReceiveCity(String value) {
        this.receiveCity = value;
    }

    /**
     * ��ȡreceiveCounty���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getReceiveCounty() {
        return receiveCounty;
    }

    /**
     * ����receiveCounty���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setReceiveCounty(String value) {
        this.receiveCounty = value;
    }

    /**
     * ��ȡreceiveDetailAddress���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getReceiveDetailAddress() {
        return receiveDetailAddress;
    }

    /**
     * ����receiveDetailAddress���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setReceiveDetailAddress(String value) {
        this.receiveDetailAddress = value;
    }

    /**
     * ��ȡconsigneeAddress���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    /**
     * ����consigneeAddress���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setConsigneeAddress(String value) {
        this.consigneeAddress = value;
    }

    /**
     * ��ȡgoodsName���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * ����goodsName���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setGoodsName(String value) {
        this.goodsName = value;
    }

    /**
     * ��ȡweight���Ե�ֵ��
     *
     */
    public double getWeight() {
        return weight;
    }

    /**
     * ����weight���Ե�ֵ��
     *
     */
    public void setWeight(double value) {
        this.weight = value;
    }

    /**
     * ��ȡcubage���Ե�ֵ��
     *
     */
    public double getCubage() {
        return cubage;
    }

    /**
     * ����cubage���Ե�ֵ��
     *
     */
    public void setCubage(double value) {
        this.cubage = value;
    }

    /**
     * ��ȡpacking���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPacking() {
        return packing;
    }

    /**
     * ����packing���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPacking(String value) {
        this.packing = value;
    }

    /**
     * ��ȡpieces���Ե�ֵ��
     *
     */
    public int getPieces() {
        return pieces;
    }

    /**
     * ����pieces���Ե�ֵ��
     *
     */
    public void setPieces(int value) {
        this.pieces = value;
    }

    /**
     * ��ȡsize���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSize() {
        return size;
    }

    /**
     * ����size���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSize(String value) {
        this.size = value;
    }

    /**
     * ��ȡdesc���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDesc() {
        return desc;
    }

    /**
     * ����desc���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDesc(String value) {
        this.desc = value;
    }

    /**
     * ��ȡtransProperty���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTransProperty() {
        return transProperty;
    }

    /**
     * ����transProperty���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTransProperty(String value) {
        this.transProperty = value;
    }

    /**
     * ��ȡdeliverMode���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDeliverMode() {
        return deliverMode;
    }

    /**
     * ����deliverMode���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDeliverMode(String value) {
        this.deliverMode = value;
    }

    /**
     * ��ȡladingStationId���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getLadingStationId() {
        return ladingStationId;
    }

    /**
     * ����ladingStationId���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setLadingStationId(String value) {
        this.ladingStationId = value;
    }

    /**
     * ��ȡgoodsType���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getGoodsType() {
        return goodsType;
    }

    /**
     * ����goodsType���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setGoodsType(String value) {
        this.goodsType = value;
    }

    /**
     * ��ȡusingVehicleDeptNum���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getUsingVehicleDeptNum() {
        return usingVehicleDeptNum;
    }

    /**
     * ����usingVehicleDeptNum���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setUsingVehicleDeptNum(String value) {
        this.usingVehicleDeptNum = value;
    }

    /**
     * ��ȡusingVehicleDeptId���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getUsingVehicleDeptId() {
        return usingVehicleDeptId;
    }

    /**
     * ����usingVehicleDeptId���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setUsingVehicleDeptId(String value) {
        this.usingVehicleDeptId = value;
    }

    /**
     * ��ȡfirstPickTime���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public Date getFirstPickTime() {
        return firstPickTime;
    }

    /**
     * ����firstPickTime���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setFirstPickTime(Date value) {
        this.firstPickTime = value;
    }

    /**
     * ��ȡlastPickTime���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public Date getLastPickTime() {
        return lastPickTime;
    }

    /**
     * ����lastPickTime���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setLastPickTime(Date value) {
        this.lastPickTime = value;
    }

    /**
     * ��ȡvehDeptNum���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getVehDeptNum() {
        return vehDeptNum;
    }

    /**
     * ����vehDeptNum���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setVehDeptNum(String value) {
        this.vehDeptNum = value;
    }

    /**
     * ��ȡvehDeptId���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getVehDeptId() {
        return vehDeptId;
    }

    /**
     * ����vehDeptId���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setVehDeptId(String value) {
        this.vehDeptId = value;
    }

    /**
     * ��ȡorderNumber���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * ����orderNumber���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setOrderNumber(String value) {
        this.orderNumber = value;
    }

    /**
     * ��ȡorderedTime���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public Date getOrderedTime() {
        return orderedTime;
    }

    /**
     * ����orderedTime���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setOrderedTime(Date value) {
        this.orderedTime = value;
    }

    /**
     * ��ȡorderType���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * ����orderType���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setOrderType(String value) {
        this.orderType = value;
    }

    /**
     * ��ȡorderOwnDeptId���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getOrderOwnDeptId() {
        return orderOwnDeptId;
    }

    /**
     * ����orderOwnDeptId���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setOrderOwnDeptId(String value) {
        this.orderOwnDeptId = value;
    }

    /**
     * ��ȡcreatorNum���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCreatorNum() {
        return creatorNum;
    }

    /**
     * ����creatorNum���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCreatorNum(String value) {
        this.creatorNum = value;
    }

    /**
     * ��ȡcreatorId���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCreatorId() {
        return creatorId;
    }

    /**
     * ����creatorId���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCreatorId(String value) {
        this.creatorId = value;
    }

    /**
     * ��ȡcarType���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCarType() {
        return carType;
    }

    /**
     * ����carType���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCarType(String value) {
        this.carType = value;
    }

	public String getCouponNumber() {
		return couponNumber;
	}

	public void setCouponNumber(String couponNumber) {
		this.couponNumber = couponNumber;
	}

	public String getWaybillNumber() {
		return waybillNumber;
	}

	public void setWaybillNumber(String waybillNumber) {
		this.waybillNumber = waybillNumber;
	}

    	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

	public String getPaidMethod() {
		return paidMethod;
	}

	public void setPaidMethod(String paidMethod) {
		this.paidMethod = paidMethod;
	}

}
