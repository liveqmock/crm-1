/**
 * WSFinanceForCrmFacadeSrvProxyServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.deppon.fin.payment;

public class WSFinanceForCrmFacadeSrvProxyServiceLocator extends org.apache.axis.client.Service implements com.deppon.fin.payment.WSFinanceForCrmFacadeSrvProxyService {

    public WSFinanceForCrmFacadeSrvProxyServiceLocator() {
    }


    public WSFinanceForCrmFacadeSrvProxyServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WSFinanceForCrmFacadeSrvProxyServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for WSFinanceForCrmFacade
    private java.lang.String WSFinanceForCrmFacade_address = "http://192.168.11.164:6888/ormrpc/services/WSFinanceForCrmFacade";

    public java.lang.String getWSFinanceForCrmFacadeAddress() {
        return WSFinanceForCrmFacade_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WSFinanceForCrmFacadeWSDDServiceName = "WSFinanceForCrmFacade";

    public java.lang.String getWSFinanceForCrmFacadeWSDDServiceName() {
        return WSFinanceForCrmFacadeWSDDServiceName;
    }

    public void setWSFinanceForCrmFacadeWSDDServiceName(java.lang.String name) {
        WSFinanceForCrmFacadeWSDDServiceName = name;
    }

    public com.deppon.fin.payment.WSFinanceForCrmFacadeSrvProxy getWSFinanceForCrmFacade() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WSFinanceForCrmFacade_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWSFinanceForCrmFacade(endpoint);
    }

    public com.deppon.fin.payment.WSFinanceForCrmFacadeSrvProxy getWSFinanceForCrmFacade(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.deppon.fin.payment.WSFinanceForCrmFacadeSoapBindingStub _stub = new com.deppon.fin.payment.WSFinanceForCrmFacadeSoapBindingStub(portAddress, this);
            _stub.setPortName(getWSFinanceForCrmFacadeWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWSFinanceForCrmFacadeEndpointAddress(java.lang.String address) {
        WSFinanceForCrmFacade_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.deppon.fin.payment.WSFinanceForCrmFacadeSrvProxy.class.isAssignableFrom(serviceEndpointInterface)) {
                com.deppon.fin.payment.WSFinanceForCrmFacadeSoapBindingStub _stub = new com.deppon.fin.payment.WSFinanceForCrmFacadeSoapBindingStub(new java.net.URL(WSFinanceForCrmFacade_address), this);
                _stub.setPortName(getWSFinanceForCrmFacadeWSDDServiceName());
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
        if ("WSFinanceForCrmFacade".equals(inputPortName)) {
            return getWSFinanceForCrmFacade();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://127.0.0.1:6888/ormrpc/services/WSFinanceForCrmFacade", "WSFinanceForCrmFacadeSrvProxyService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://127.0.0.1:6888/ormrpc/services/WSFinanceForCrmFacade", "WSFinanceForCrmFacade"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("WSFinanceForCrmFacade".equals(portName)) {
            setWSFinanceForCrmFacadeEndpointAddress(address);
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
