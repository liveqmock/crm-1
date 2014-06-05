
package com.deppon.map.baidu;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.deppon.map.baidu package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _MapWs_QNAME = new QName("http://service.crm/", "MapWs");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.deppon.map.baidu
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DeptByDistance }
     * 
     */
    public DeptByDistance createDeptByDistance() {
        return new DeptByDistance();
    }

    /**
     * Create an instance of {@link MapWs }
     * 
     */
    public MapWs createMapWs() {
        return new MapWs();
    }

    /**
     * Create an instance of {@link DeptByDistanceArray }
     * 
     */
    public DeptByDistanceArray createDeptByDistanceArray() {
        return new DeptByDistanceArray();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MapWs }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.crm/", name = "MapWs")
    public JAXBElement<MapWs> createMapWs(MapWs value) {
        return new JAXBElement<MapWs>(_MapWs_QNAME, MapWs.class, null, value);
    }

}
