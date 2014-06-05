package com.deppon.crm.module.client.sync.domain;

import java.util.LinkedList;
import java.util.List;

import com.deppon.crm.module.client.sync.domain.dto.TCustAccountOperation;
import com.deppon.crm.module.client.sync.domain.dto.TCustContractOperation;
import com.deppon.crm.module.client.sync.domain.dto.TCustContractTaxOperation;
import com.deppon.crm.module.client.sync.domain.dto.TCustContractdeptOperation;
import com.deppon.crm.module.client.sync.domain.dto.TCustCustbasedataOperation;
import com.deppon.crm.module.client.sync.domain.dto.TCustCustlinkmanOperation;
import com.deppon.crm.module.client.sync.domain.dto.TCustPreferenceaddressOperation;
import com.deppon.crm.module.client.sync.domain.dto.TCustPreferentialOperation;
import com.deppon.crm.module.client.sync.domain.dto.TCustShuttleaddressOperation;

public class WaitingSendRequest {
	/**
	 * 0:全部相关表，先删除，再插入。
	 */
	private int pattern;
	private TCustCustbasedataOperation tCustCustbasedata;
	private List<TCustAccountOperation> tCustAccount = new LinkedList<TCustAccountOperation>();
	private List<TCustContractOperation> tCustContract = new LinkedList<TCustContractOperation>();
	private List<TCustCustlinkmanOperation> tCustCustlinkman = new LinkedList<TCustCustlinkmanOperation>();
	private List<TCustShuttleaddressOperation> tCustShuttleaddress = new LinkedList<TCustShuttleaddressOperation>();
	private List<TCustPreferenceaddressOperation> tCustPreferenceaddress = new LinkedList<TCustPreferenceaddressOperation>();
	private List<TCustContractdeptOperation> tCustContractdept = new LinkedList<TCustContractdeptOperation>();
	private List<TCustPreferentialOperation> tCustPreferential = new LinkedList<TCustPreferentialOperation>();
	private List<TCustContractTaxOperation> tCustContractTax = new LinkedList<TCustContractTaxOperation>();
	public int getPattern() {
		return pattern;
	}
	public void setPattern(int pattern) {
		this.pattern = pattern;
	}
	public TCustCustbasedataOperation gettCustCustbasedata() {
		return tCustCustbasedata;
	}
	public void settCustCustbasedata(TCustCustbasedataOperation tCustCustbasedata) {
		this.tCustCustbasedata = tCustCustbasedata;
	}
	public List<TCustAccountOperation> gettCustAccount() {
		return tCustAccount;
	}
	public void settCustAccount(List<TCustAccountOperation> tCustAccount) {
		this.tCustAccount = tCustAccount;
	}
	public List<TCustContractOperation> gettCustContract() {
		return tCustContract;
	}
	public void settCustContract(List<TCustContractOperation> tCustContract) {
		this.tCustContract = tCustContract;
	}
	public List<TCustCustlinkmanOperation> gettCustCustlinkman() {
		return tCustCustlinkman;
	}
	public void settCustCustlinkman(List<TCustCustlinkmanOperation> tCustCustlinkman) {
		this.tCustCustlinkman = tCustCustlinkman;
	}
	public List<TCustShuttleaddressOperation> gettCustShuttleaddress() {
		return tCustShuttleaddress;
	}
	public void settCustShuttleaddress(
			List<TCustShuttleaddressOperation> tCustShuttleaddress) {
		this.tCustShuttleaddress = tCustShuttleaddress;
	}
	public List<TCustPreferenceaddressOperation> gettCustPreferenceaddress() {
		return tCustPreferenceaddress;
	}
	public void settCustPreferenceaddress(
			List<TCustPreferenceaddressOperation> tCustPreferenceaddress) {
		this.tCustPreferenceaddress = tCustPreferenceaddress;
	}
	public List<TCustContractdeptOperation> gettCustContractdept() {
		return tCustContractdept;
	}
	public void settCustContractdept(
			List<TCustContractdeptOperation> tCustContractdept) {
		this.tCustContractdept = tCustContractdept;
	}
	public List<TCustPreferentialOperation> gettCustPreferential() {
		return tCustPreferential;
	}
	public void settCustPreferential(
			List<TCustPreferentialOperation> tCustPreferential) {
		this.tCustPreferential = tCustPreferential;
	}
	/**
	 * Description:tCustContractTax<br />
	 * @author CoCo
	 * @version 0.1 2013-11-15
	 */
	public List<TCustContractTaxOperation> gettCustContractTax() {
		return tCustContractTax;
	}
	/**
	 * Description:tCustContractTax<br />
	 * @author CoCo
	 * @version 0.1 2013-11-15
	 */
	public void settCustContractTax(List<TCustContractTaxOperation> tCustContractTax) {
		this.tCustContractTax = tCustContractTax;
	}
}