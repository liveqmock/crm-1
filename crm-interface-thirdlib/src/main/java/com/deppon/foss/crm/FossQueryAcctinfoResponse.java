
package com.deppon.foss.crm;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>FossQueryAcctinfoResponse complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="FossQueryAcctinfoResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fossQueryAcctinfoList" type="{http://www.deppon.com/esb/inteface/domain/crm}FossQueryAcctinfo" maxOccurs="unbounded"/>
 *         &lt;element name="totalNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FossQueryAcctinfoResponse", propOrder = {
    "fossQueryAcctinfoList",
    "totalNum"
})
public class FossQueryAcctinfoResponse {

    @XmlElement(required = true)
    protected List<FossQueryAcctinfo> fossQueryAcctinfoList;
    protected int totalNum;

    /**
     * Gets the value of the fossQueryAcctinfoList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fossQueryAcctinfoList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFossQueryAcctinfoList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FossQueryAcctinfo }
     * 
     * 
     */
    public List<FossQueryAcctinfo> getFossQueryAcctinfoList() {
        if (fossQueryAcctinfoList == null) {
            fossQueryAcctinfoList = new ArrayList<FossQueryAcctinfo>();
        }
        return this.fossQueryAcctinfoList;
    }

    /**
     * ��ȡtotalNum���Ե�ֵ��
     * 
     */
    public int getTotalNum() {
        return totalNum;
    }

    /**
     * ����totalNum���Ե�ֵ��
     * 
     */
    public void setTotalNum(int value) {
        this.totalNum = value;
    }

}
