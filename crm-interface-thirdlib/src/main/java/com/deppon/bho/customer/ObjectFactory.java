
package com.deppon.bho.customer;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.deppon.bho.customer package. 
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

    private final static QName _GetUserResponse_QNAME = new QName("http://ws.shared.User.module.myDeppon.deppon.com/", "getUserResponse");
    private final static QName _GetUsers_QNAME = new QName("http://ws.shared.User.module.myDeppon.deppon.com/", "getUsers");
    private final static QName _BandingCustCodeResponse_QNAME = new QName("http://ws.shared.User.module.myDeppon.deppon.com/", "bandingCustCodeResponse");
    private final static QName _GetUsersResponse_QNAME = new QName("http://ws.shared.User.module.myDeppon.deppon.com/", "getUsersResponse");
    private final static QName _BandingCustCode_QNAME = new QName("http://ws.shared.User.module.myDeppon.deppon.com/", "bandingCustCode");
    private final static QName _GetUser_QNAME = new QName("http://ws.shared.User.module.myDeppon.deppon.com/", "getUser");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.deppon.bho.customer
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetUsersResponse }
     * 
     */
    public GetUsersResponse createGetUsersResponse() {
        return new GetUsersResponse();
    }

    /**
     * Create an instance of {@link BandingCustCode }
     * 
     */
    public BandingCustCode createBandingCustCode() {
        return new BandingCustCode();
    }

    /**
     * Create an instance of {@link GetUsers }
     * 
     */
    public GetUsers createGetUsers() {
        return new GetUsers();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link GetUserResponse }
     * 
     */
    public GetUserResponse createGetUserResponse() {
        return new GetUserResponse();
    }

    /**
     * Create an instance of {@link GetUser }
     * 
     */
    public GetUser createGetUser() {
        return new GetUser();
    }

    /**
     * Create an instance of {@link BandingCustCodeResponse }
     * 
     */
    public BandingCustCodeResponse createBandingCustCodeResponse() {
        return new BandingCustCodeResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.shared.User.module.myDeppon.deppon.com/", name = "getUserResponse")
    public JAXBElement<GetUserResponse> createGetUserResponse(GetUserResponse value) {
        return new JAXBElement<GetUserResponse>(_GetUserResponse_QNAME, GetUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUsers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.shared.User.module.myDeppon.deppon.com/", name = "getUsers")
    public JAXBElement<GetUsers> createGetUsers(GetUsers value) {
        return new JAXBElement<GetUsers>(_GetUsers_QNAME, GetUsers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BandingCustCodeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.shared.User.module.myDeppon.deppon.com/", name = "bandingCustCodeResponse")
    public JAXBElement<BandingCustCodeResponse> createBandingCustCodeResponse(BandingCustCodeResponse value) {
        return new JAXBElement<BandingCustCodeResponse>(_BandingCustCodeResponse_QNAME, BandingCustCodeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUsersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.shared.User.module.myDeppon.deppon.com/", name = "getUsersResponse")
    public JAXBElement<GetUsersResponse> createGetUsersResponse(GetUsersResponse value) {
        return new JAXBElement<GetUsersResponse>(_GetUsersResponse_QNAME, GetUsersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BandingCustCode }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.shared.User.module.myDeppon.deppon.com/", name = "bandingCustCode")
    public JAXBElement<BandingCustCode> createBandingCustCode(BandingCustCode value) {
        return new JAXBElement<BandingCustCode>(_BandingCustCode_QNAME, BandingCustCode.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.shared.User.module.myDeppon.deppon.com/", name = "getUser")
    public JAXBElement<GetUser> createGetUser(GetUser value) {
        return new JAXBElement<GetUser>(_GetUser_QNAME, GetUser.class, null, value);
    }

}
