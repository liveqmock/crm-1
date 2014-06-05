/**
 * WSFinBankNumberCrmFacadeSrvProxyServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.deppon.fin.bankNum;

public class WSFinBankNumberCrmFacadeSrvProxyServiceLocator extends org.apache.axis.client.Service implements com.deppon.fin.bankNum.WSFinBankNumberCrmFacadeSrvProxyService {

    public WSFinBankNumberCrmFacadeSrvProxyServiceLocator() {
    }


    public WSFinBankNumberCrmFacadeSrvProxyServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WSFinBankNumberCrmFacadeSrvProxyServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for WSFinBankNumberCrmFacade
    private java.lang.String WSFinBankNumberCrmFacade_address = "http://192.168.11.164:6888/ormrpc/services/WSFinBankNumberCrmFacade";

    public java.lang.String getWSFinBankNumberCrmFacadeAddress() {
        return WSFinBankNumberCrmFacade_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WSFinBankNumberCrmFacadeWSDDServiceName = "WSFinBankNumberCrmFacade";

    public java.lang.String getWSFinBankNumberCrmFacadeWSDDServiceName() {
        return WSFinBankNumberCrmFacadeWSDDServiceName;
    }

    public void setWSFinBankNumberCrmFacadeWSDDServiceName(java.lang.String name) {
        WSFinBankNumberCrmFacadeWSDDServiceName = name;
    }

    public com.deppon.fin.bankNum.WSFinBankNumberCrmFacadeSrvProxy getWSFinBankNumberCrmFacade() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WSFinBankNumberCrmFacade_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWSFinBankNumberCrmFacade(endpoint);
    }

    public com.deppon.fin.bankNum.WSFinBankNumberCrmFacadeSrvProxy getWSFinBankNumberCrmFacade(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.deppon.fin.bankNum.WSFinBankNumberCrmFacadeSoapBindingStub _stub = new com.deppon.fin.bankNum.WSFinBankNumberCrmFacadeSoapBindingStub(portAddress, this);
            _stub.setPortName(getWSFinBankNumberCrmFacadeWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWSFinBankNumberCrmFacadeEndpointAddress(java.lang.String address) {
        WSFinBankNumberCrmFacade_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.deppon.fin.bankNum.WSFinBankNumberCrmFacadeSrvProxy.class.isAssignableFrom(serviceEndpointInterface)) {
                com.deppon.fin.bankNum.WSFinBankNumberCrmFacadeSoapBindingStub _stub = new com.deppon.fin.bankNum.WSFinBankNumberCrmFacadeSoapBindingStub(new java.net.URL(WSFinBankNumberCrmFacade_address), this);
                _stub.setPortName(getWSFinBankNumberCrmFacadeWSDDServiceName());
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
        if ("WSFinBankNumberCrmFacade".equals(inputPortName)) {
            return getWSFinBankNumberCrmFacade();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://127.0.0.1:6888/ormrpc/services/WSFinBankNumberCrmFacade", "WSFinBankNumberCrmFacadeSrvProxyService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://127.0.0.1:6888/ormrpc/services/WSFinBankNumberCrmFacade", "WSFinBankNumberCrmFacade"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("WSFinBankNumberCrmFacade".equals(portName)) {
            setWSFinBankNumberCrmFacadeEndpointAddress(address);
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
