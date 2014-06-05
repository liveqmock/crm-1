package com.deppon.fin.selfservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.7.3
 * 2013-04-25T21:25:54.343+08:00
 * Generated source version: 2.7.3
 * 
 */
@WebService(targetNamespace = "http://www.deppon.com/fssc/remote/crm", name = "CrmService")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface CrmService {

    @WebResult(name = "generate_claimsapbillResponse", targetNamespace = "http://www.deppon.com/fssc/remote/crm/domain", partName = "generate_claimsapbillResponse")
    @WebMethod(operationName = "generate_claimsapbill")
    public GenerateClaimsapbillResponse generateClaimsapbill(
        @WebParam(partName = "generate_claimsapbillRequest", name = "generate_claimsapbillRequest", targetNamespace = "http://www.deppon.com/fssc/remote/crm/domain")
        GenerateClaimsapbillRequest generateClaimsapbillRequest
    );

    @WebResult(name = "query_cashieraccountResponse", targetNamespace = "http://www.deppon.com/fssc/remote/crm/domain", partName = "query_cashieraccountResponse")
    @WebMethod(operationName = "query_cashieraccount")
    public QueryCashieraccountResponse queryCashieraccount(
        @WebParam(partName = "query_cashieraccountRequest", name = "query_cashieraccountRequest", targetNamespace = "http://www.deppon.com/fssc/remote/crm/domain")
        QueryCashieraccountRequest queryCashieraccountRequest
    );
}