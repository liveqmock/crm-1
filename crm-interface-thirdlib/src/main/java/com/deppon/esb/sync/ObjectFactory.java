
package com.deppon.esb.sync;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.deppon.esb.sync package. 
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

    private final static QName _Sync_QNAME = new QName("http://sync.crm.deppon.com/", "sync");
    private final static QName _SyncResponse_QNAME = new QName("http://sync.crm.deppon.com/", "syncResponse");
    private final static QName _SyncData_QNAME = new QName("http://sync.crm.deppon.com/", "SyncData");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.deppon.esb.sync
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SyncData }
     * 
     */
    public SyncData createSyncData() {
        return new SyncData();
    }

    /**
     * Create an instance of {@link SyncResponse }
     * 
     */
    public SyncResponse createSyncResponse() {
        return new SyncResponse();
    }

    /**
     * Create an instance of {@link Sync }
     * 
     */
    public Sync createSync() {
        return new Sync();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sync }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sync.crm.deppon.com/", name = "sync")
    public JAXBElement<Sync> createSync(Sync value) {
        return new JAXBElement<Sync>(_Sync_QNAME, Sync.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SyncResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sync.crm.deppon.com/", name = "syncResponse")
    public JAXBElement<SyncResponse> createSyncResponse(SyncResponse value) {
        return new JAXBElement<SyncResponse>(_SyncResponse_QNAME, SyncResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SyncData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sync.crm.deppon.com/", name = "SyncData")
    public JAXBElement<SyncData> createSyncData(SyncData value) {
        return new JAXBElement<SyncData>(_SyncData_QNAME, SyncData.class, null, value);
    }

}
