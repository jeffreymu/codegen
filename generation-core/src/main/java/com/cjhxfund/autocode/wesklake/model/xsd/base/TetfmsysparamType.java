
package com.cjhxfund.autocode.wesklake.model.xsd.base;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>tetfmsysparamType complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="tetfmsysparamType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="param_value">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="300"/>
 *               &lt;enumeration value="1"/>
 *               &lt;enumeration value="{"ip":"127.0.0.1","port":"1521","user_name":"user","password":"1","service":"sid","exec_user":""}"/>
 *               &lt;enumeration value="{"ip":"smtp.qq.com","port":"25","addr":"****@**.com","user":"****@**.com","pwd":"******"}"/>
 *               &lt;enumeration value="{"host": "http://127.0.0.0:8888","user": "admin","pwd": "1"}"/>
 *               &lt;enumeration value="20"/>
 *               &lt;enumeration value="10"/>
 *               &lt;enumeration value="{"enable": "0","filepath": "�ۺ���Ϣ��ѯ-��Ԫ�ʲ���ѯ���.xls"}"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
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
@XmlType(name = "tetfmsysparamType", propOrder = {
    "id",
    "paramValue",
    "remark"
})
public class TetfmsysparamType {

    @XmlElement(required = true)
    protected String id;
    @XmlElement(name = "param_value", required = true)
    protected String paramValue;
    @XmlElement(required = true)
    protected String remark;

    /**
     * ��ȡid���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * ����id���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * ��ȡparamValue���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParamValue() {
        return paramValue;
    }

    /**
     * ����paramValue���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParamValue(String value) {
        this.paramValue = value;
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
