package com.deppon.foss.waybill;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.6.2
 * 2013-03-25T14:05:53.406+08:00
 * Generated source version: 2.6.2
 * 
 */
@WebServiceClient(name = "CustomerService", 
                  wsdlLocation = "file:/E:/waybill/QueryWayBillService.wsdl",
                  targetNamespace = "http://www.deppon.com/foss/waybillService") 
public class CustomerService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://www.deppon.com/foss/waybillService", "CustomerService");
    public final static QName WaybillServiceSOAP = new QName("http://www.deppon.com/foss/waybillService", "WaybillServiceSOAP");
    static {
        URL url = null;
        try {
            url = new URL("file:/E:/waybill/QueryWayBillService.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(CustomerService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/E:/waybill/QueryWayBillService.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public CustomerService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public CustomerService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public CustomerService() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns WaybillService
     */
    @WebEndpoint(name = "WaybillServiceSOAP")
    public WaybillService getWaybillServiceSOAP() {
        return super.getPort(WaybillServiceSOAP, WaybillService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WaybillService
     */
    @WebEndpoint(name = "WaybillServiceSOAP")
    public WaybillService getWaybillServiceSOAP(WebServiceFeature... features) {
        return super.getPort(WaybillServiceSOAP, WaybillService.class, features);
    }

}
