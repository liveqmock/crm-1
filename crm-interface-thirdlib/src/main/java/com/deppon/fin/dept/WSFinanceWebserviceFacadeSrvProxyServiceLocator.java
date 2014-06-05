/**
 * WSFinanceWebserviceFacadeSrvProxyServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.deppon.fin.dept;

public class WSFinanceWebserviceFacadeSrvProxyServiceLocator extends org.apache.axis.client.Service implements com.deppon.fin.dept.WSFinanceWebserviceFacadeSrvProxyService {

    public WSFinanceWebserviceFacadeSrvProxyServiceLocator() {
    }


    public WSFinanceWebserviceFacadeSrvProxyServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WSFinanceWebserviceFacadeSrvProxyServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for WSFinanceWebserviceFacade
    private java.lang.String WSFinanceWebserviceFacade_address = "http://192.168.17.124:9888/ormrpc/services/WSFinanceWebserviceFacade";

    public java.lang.String getWSFinanceWebserviceFacadeAddress() {
        return WSFinanceWebserviceFacade_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WSFinanceWebserviceFacadeWSDDServiceName = "WSFinanceWebserviceFacade";

    public java.lang.String getWSFinanceWebserviceFacadeWSDDServiceName() {
        return WSFinanceWebserviceFacadeWSDDServiceName;
    }

    public void setWSFinanceWebserviceFacadeWSDDServiceName(java.lang.String name) {
        WSFinanceWebserviceFacadeWSDDServiceName = name;
    }

    public com.deppon.fin.dept.WSFinanceWebserviceFacadeSrvProxy getWSFinanceWebserviceFacade() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WSFinanceWebserviceFacade_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWSFinanceWebserviceFacade(endpoint);
    }

    public com.deppon.fin.dept.WSFinanceWebserviceFacadeSrvProxy getWSFinanceWebserviceFacade(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.deppon.fin.dept.WSFinanceWebserviceFacadeSoapBindingStub _stub = new com.deppon.fin.dept.WSFinanceWebserviceFacadeSoapBindingStub(portAddress, this);
            _stub.setPortName(getWSFinanceWebserviceFacadeWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWSFinanceWebserviceFacadeEndpointAddress(java.lang.String address) {
        WSFinanceWebserviceFacade_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.deppon.fin.dept.WSFinanceWebserviceFacadeSrvProxy.class.isAssignableFrom(serviceEndpointInterface)) {
                com.deppon.fin.dept.WSFinanceWebserviceFacadeSoapBindingStub _stub = new com.deppon.fin.dept.WSFinanceWebserviceFacadeSoapBindingStub(new java.net.URL(WSFinanceWebserviceFacade_address), this);
                _stub.setPortName(getWSFinanceWebserviceFacadeWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("WSFinanceWebserviceFacade".equals(inputPortName)) {
            return getWSFinanceWebserviceFacade();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://192.168.17.124:9888/ormrpc/services/WSFinanceWebserviceFacade", "WSFinanceWebserviceFacadeSrvProxyService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://192.168.17.124:9888/ormrpc/services/WSFinanceWebserviceFacade", "WSFinanceWebserviceFacade"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("WSFinanceWebserviceFacade".equals(portName)) {
            setWSFinanceWebserviceFacadeEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
