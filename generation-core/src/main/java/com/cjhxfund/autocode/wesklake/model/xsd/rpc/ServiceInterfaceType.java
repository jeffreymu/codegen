
package com.cjhxfund.autocode.wesklake.model.xsd.rpc;

import javax.xml.bind.annotation.*;


/**
 * <p>ServiceInterfaceType complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="ServiceInterfaceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Summary" type="{}SummaryType"/>
 *         &lt;element name="InParams" type="{}InParamsType"/>
 *         &lt;element name="OutParams" type="{}OutParamsType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceInterfaceType", propOrder = {
    "summary",
    "inParams",
    "outParams"
})
@XmlRootElement(name = "ServiceInterface")
public class ServiceInterfaceType {

    @XmlElement(name = "Summary", required = true)
    protected SummaryType summary;
    @XmlElement(name = "InParams", required = true)
    protected InParamsType inParams;
    @XmlElement(name = "OutParams", required = true)
    protected OutParamsType outParams;

    /**
     * ��ȡsummary���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link SummaryType }
     *     
     */
    public SummaryType getSummary() {
        return summary;
    }

    /**
     * ����summary���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link SummaryType }
     *     
     */
    public void setSummary(SummaryType value) {
        this.summary = value;
    }

    /**
     * ��ȡinParams���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link InParamsType }
     *     
     */
    public InParamsType getInParams() {
        return inParams;
    }

    /**
     * ����inParams���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link InParamsType }
     *     
     */
    public void setInParams(InParamsType value) {
        this.inParams = value;
    }

    /**
     * ��ȡoutParams���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link OutParamsType }
     *     
     */
    public OutParamsType getOutParams() {
        return outParams;
    }

    /**
     * ����outParams���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link OutParamsType }
     *     
     */
    public void setOutParams(OutParamsType value) {
        this.outParams = value;
    }

}
