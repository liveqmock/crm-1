
package com.deppon.interfaces.gift;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.4.2
 * 2012-07-11T15:45:32.093+08:00
 * Generated source version: 2.4.2
 */

@WebFault(name = "CrmBusiness", targetNamespace = "http://exception.common.client.module.crm.deppon.com")
public class CrmBusinessException extends Exception {
    
    private com.deppon.interfaces.gift.CrmBusiness crmBusiness;

    public CrmBusinessException() {
        super();
    }
    
    public CrmBusinessException(String message) {
        super(message);
    }
    
    public CrmBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public CrmBusinessException(String message, com.deppon.interfaces.gift.CrmBusiness crmBusiness) {
        super(message);
        this.crmBusiness = crmBusiness;
    }

    public CrmBusinessException(String message, com.deppon.interfaces.gift.CrmBusiness crmBusiness, Throwable cause) {
        super(message, cause);
        this.crmBusiness = crmBusiness;
    }

    public com.deppon.interfaces.gift.CrmBusiness getFaultInfo() {
        return this.crmBusiness;
    }
}
