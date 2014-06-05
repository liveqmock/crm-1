package com.deppon.crm.module.interfaces.ows.domain;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the
 * com.deppon.crm.module.interfaces.ows.domain package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	private final static QName _CreateNewCouponResponse_QNAME = new QName(
			"http://www.deppon.com/crm/remote/ows/domain/entity",
			"CreateNewCouponResponse");
	private final static QName _CommonExceptionInfo_QNAME = new QName(
			"http://www.deppon.com/esb/exception", "commonExceptionInfo");
	private final static QName _EsbHeader_QNAME = new QName(
			"http://www.deppon.com/esb/header", "esbHeader");
	private final static QName _CreateNewCouponRequest_QNAME = new QName(
			"http://www.deppon.com/crm/remote/ows/domain/entity",
			"CreateNewCouponRequest");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package:
	 * com.deppon.crm.module.interfaces.ows.domain
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link StatusList }
	 * 
	 */
	public StatusList createStatusList() {
		return new StatusList();
	}

	/**
	 * Create an instance of {@link TakeGoodsArea }
	 * 
	 */
	public TakeGoodsArea createTakeGoodsArea() {
		return new TakeGoodsArea();
	}

	/**
	 * Create an instance of {@link StatusInfo }
	 * 
	 */
	public StatusInfo createStatusInfo() {
		return new StatusInfo();
	}

	/**
	 * Create an instance of {@link CommonExceptionInfo }
	 * 
	 */
	public CommonExceptionInfo createCommonExceptionInfo() {
		return new CommonExceptionInfo();
	}

	/**
	 * Create an instance of {@link ESBHeader }
	 * 
	 */
	public ESBHeader createESBHeader() {
		return new ESBHeader();
	}

	/**
	 * Create an instance of {@link CreateNewCouponRequest }
	 * 
	 */
	public CreateNewCouponRequest createCreateNewCouponRequest() {
		return new CreateNewCouponRequest();
	}

	/**
	 * Create an instance of {@link AuthInfo }
	 * 
	 */
	public AuthInfo createAuthInfo() {
		return new AuthInfo();
	}

	/**
	 * Create an instance of {@link CreateNewCouponResponse }
	 * 
	 */
	public CreateNewCouponResponse createCreateNewCouponResponse() {
		return new CreateNewCouponResponse();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link CreateNewCouponResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://www.deppon.com/crm/remote/ows/domain/entity", name = "CreateNewCouponResponse")
	public JAXBElement<CreateNewCouponResponse> createCreateNewCouponResponse(
			CreateNewCouponResponse value) {
		return new JAXBElement<CreateNewCouponResponse>(
				_CreateNewCouponResponse_QNAME, CreateNewCouponResponse.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link CommonExceptionInfo }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://www.deppon.com/esb/exception", name = "commonExceptionInfo")
	public JAXBElement<CommonExceptionInfo> createCommonExceptionInfo(
			CommonExceptionInfo value) {
		return new JAXBElement<CommonExceptionInfo>(_CommonExceptionInfo_QNAME,
				CommonExceptionInfo.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link ESBHeader }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://www.deppon.com/esb/header", name = "esbHeader")
	public JAXBElement<ESBHeader> createEsbHeader(ESBHeader value) {
		return new JAXBElement<ESBHeader>(_EsbHeader_QNAME, ESBHeader.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link CreateNewCouponRequest }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://www.deppon.com/crm/remote/ows/domain/entity", name = "CreateNewCouponRequest")
	public JAXBElement<CreateNewCouponRequest> createCreateNewCouponRequest(
			CreateNewCouponRequest value) {
		return new JAXBElement<CreateNewCouponRequest>(
				_CreateNewCouponRequest_QNAME, CreateNewCouponRequest.class,
				null, value);
	}

}
