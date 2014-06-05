/**
 * WSFinanceForCrmFacadeSrvProxy.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.deppon.fin.payment;

public interface WSFinanceForCrmFacadeSrvProxy extends java.rmi.Remote {
    public java.lang.String callCreateOnLinePaymentWorkFlow(java.lang.String paramonline) throws java.rmi.RemoteException, com.deppon.fin.payment.WSInvokeException;
    public java.lang.String[] callCreateCommonPaymentWorkFlow(java.lang.String[] param) throws java.rmi.RemoteException, com.deppon.fin.payment.WSInvokeException;
    public java.lang.String[] callCrmPaymentWorkFlow(java.lang.String[] param) throws java.rmi.RemoteException, com.deppon.fin.payment.WSInvokeException;
}
