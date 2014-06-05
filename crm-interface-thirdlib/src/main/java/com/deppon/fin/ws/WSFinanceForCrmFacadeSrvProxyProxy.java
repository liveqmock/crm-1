package com.deppon.fin.ws;

public class WSFinanceForCrmFacadeSrvProxyProxy implements com.deppon.fin.ws.WSFinanceForCrmFacadeSrvProxy {
  private String _endpoint = null;
  private com.deppon.fin.ws.WSFinanceForCrmFacadeSrvProxy wSFinanceForCrmFacadeSrvProxy = null;
  
  public WSFinanceForCrmFacadeSrvProxyProxy() {
    _initWSFinanceForCrmFacadeSrvProxyProxy();
  }
  
  public WSFinanceForCrmFacadeSrvProxyProxy(String endpoint) {
    _endpoint = endpoint;
    _initWSFinanceForCrmFacadeSrvProxyProxy();
  }
  
  private void _initWSFinanceForCrmFacadeSrvProxyProxy() {
    try {
      wSFinanceForCrmFacadeSrvProxy = (new com.deppon.fin.ws.WSFinanceForCrmFacadeSrvProxyServiceLocator()).getWSFinanceForCrmFacade();
      if (wSFinanceForCrmFacadeSrvProxy != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)wSFinanceForCrmFacadeSrvProxy)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)wSFinanceForCrmFacadeSrvProxy)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (wSFinanceForCrmFacadeSrvProxy != null)
      ((javax.xml.rpc.Stub)wSFinanceForCrmFacadeSrvProxy)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.deppon.fin.ws.WSFinanceForCrmFacadeSrvProxy getWSFinanceForCrmFacadeSrvProxy() {
    if (wSFinanceForCrmFacadeSrvProxy == null)
      _initWSFinanceForCrmFacadeSrvProxyProxy();
    return wSFinanceForCrmFacadeSrvProxy;
  }
  
  public java.lang.String callCreateCommonPaymentWorkFlow(java.lang.String paramcommon) throws java.rmi.RemoteException, com.deppon.fin.ws.WSInvokeException{
    if (wSFinanceForCrmFacadeSrvProxy == null)
      _initWSFinanceForCrmFacadeSrvProxyProxy();
    return wSFinanceForCrmFacadeSrvProxy.callCreateCommonPaymentWorkFlow(paramcommon);
  }
  
  public java.lang.String callCreateOnLinePaymentWorkFlow(java.lang.String paramonline) throws java.rmi.RemoteException, com.deppon.fin.ws.WSInvokeException{
    if (wSFinanceForCrmFacadeSrvProxy == null)
      _initWSFinanceForCrmFacadeSrvProxyProxy();
    return wSFinanceForCrmFacadeSrvProxy.callCreateOnLinePaymentWorkFlow(paramonline);
  }
  
  
}