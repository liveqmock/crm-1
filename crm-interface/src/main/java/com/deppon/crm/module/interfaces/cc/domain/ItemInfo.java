
package com.deppon.crm.module.interfaces.cc.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ItemInfo complex type�� Java �ࡣ
 *
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 *
 * <pre>
 * &lt;complexType name="ItemInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sequence " type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="content" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ifchoosed" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="remark" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ItemInfo", propOrder = {
    "sequence",
    "content",
    "ifchoosed",
    "remark"
})
public class ItemInfo {

    @XmlElement(name = "sequence", required = true)
    protected String sequence;
    @XmlElement(required = true)
    protected String content;
    protected boolean ifchoosed;
    @XmlElement(required = true)
    protected String remark;



    public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	/**
     * ��ȡcontent���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getContent() {
        return content;
    }

    /**
     * ����content���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setContent(String value) {
        this.content = value;
    }

    /**
     * ��ȡifchoosed���Ե�ֵ��
     *
     */
    public boolean isIfchoosed() {
        return ifchoosed;
    }

    /**
     * ����ifchoosed���Ե�ֵ��
     *
     */
    public void setIfchoosed(boolean value) {
        this.ifchoosed = value;
    }

    /**
     * ��ȡremark���Ե�ֵ��
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getRemark() {
        return remark;
    }

    /**
     * ����remark���Ե�ֵ��
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setRemark(String value) {
        this.remark = value;
    }

}
