/**
 * WSLMSGiftApplyFacadeSrvProxyServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.deppon.lms.workflow;

public class WSLMSGiftApplyFacadeSrvProxyServiceLocator extends org.apache.axis.client.Service implements com.deppon.lms.workflow.WSLMSGiftApplyFacadeSrvProxyService {

    public WSLMSGiftApplyFacadeSrvProxyServiceLocator() {
    }


    public WSLMSGiftApplyFacadeSrvProxyServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WSLMSGiftApplyFacadeSrvProxyServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for WSLMSGiftApplyFacade
    private java.lang.String WSLMSGiftApplyFacade_address = "http://192.168.11.27:6888/ormrpc/services/WSLMSGiftApplyFacade";

    public java.lang.String getWSLMSGiftApplyFacadeAddress() {
        return WSLMSGiftApplyFacade_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WSLMSGiftApplyFacadeWSDDServiceName = "WSLMSGiftApplyFacade";

    public java.lang.String getWSLMSGiftApplyFacadeWSDDServiceName() {
        return WSLMSGiftApplyFacadeWSDDServiceName;
    }

    public void setWSLMSGiftApplyFacadeWSDDServiceName(java.lang.String name) {
        WSLMSGiftApplyFacadeWSDDServiceName = name;
    }

    public com.deppon.lms.workflow.WSLMSGiftApplyFacadeSrvProxy getWSLMSGiftApplyFacade() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WSLMSGiftApplyFacade_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWSLMSGiftApplyFacade(endpoint);
    }

    public com.deppon.lms.workflow.WSLMSGiftApplyFacadeSrvProxy getWSLMSGiftApplyFacade(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.deppon.lms.workflow.WSLMSGiftApplyFacadeSoapBindingStub _stub = new com.deppon.lms.workflow.WSLMSGiftApplyFacadeSoapBindingStub(portAddress, this);
            _stub.setPortName(getWSLMSGiftApplyFacadeWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWSLMSGiftApplyFacadeEndpointAddress(java.lang.String address) {
        WSLMSGiftApplyFacade_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.deppon.lms.workflow.WSLMSGiftApplyFacadeSrvProxy.class.isAssignableFrom(serviceEndpointInterface)) {
                com.deppon.lms.workflow.WSLMSGiftApplyFacadeSoapBindingStub _stub = new com.deppon.lms.workflow.WSLMSGiftApplyFacadeSoapBindingStub(new java.net.URL(WSLMSGiftApplyFacade_address), this);
                _stub.setPortName(getWSLMSGiftApplyFacadeWSDDServiceName());
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
        if ("WSLMSGiftApplyFacade".equals(inputPortName)) {
            return getWSLMSGiftApplyFacade();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://192.168.11.27:6888/ormrpc/services/WSLMSGiftApplyFacade", "WSLMSGiftApplyFacadeSrvProxyService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://192.168.11.27:6888/ormrpc/services/WSLMSGiftApplyFacade", "WSLMSGiftApplyFacade"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("WSLMSGiftApplyFacade".equals(portName)) {
            setWSLMSGiftApplyFacadeEndpointAddress(address);
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
