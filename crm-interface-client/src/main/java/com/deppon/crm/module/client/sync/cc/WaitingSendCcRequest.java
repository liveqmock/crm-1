package com.deppon.crm.module.client.sync.cc;

import java.util.LinkedList;
import java.util.List;

import com.deppon.crm.module.client.sync.cc.TCustAccountOperation;
import com.deppon.crm.module.client.sync.cc.TCustContractOperation;
import com.deppon.crm.module.client.sync.cc.TCustContractdeptOperation;
import com.deppon.crm.module.client.sync.cc.TCustCustbasedataOperation;
import com.deppon.crm.module.client.sync.cc.TCustCustlinkmanOperation;
import com.deppon.crm.module.client.sync.cc.TCustPreferenceaddressOperation;
import com.deppon.crm.module.client.sync.cc.TCustPreferentialOperation;
import com.deppon.crm.module.client.sync.cc.TCustShuttleaddressOperation;

public class WaitingSendCcRequest {
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
}