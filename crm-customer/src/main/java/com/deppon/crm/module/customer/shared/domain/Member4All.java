package com.deppon.crm.module.customer.shared.domain;

import java.util.ArrayList;
import java.util.List;

import com.deppon.crm.module.customer.shared.domain.integral.ContactVary;

public class Member4All extends Member {
	private static final long serialVersionUID = 355045480670629282L;
	private ContactVary contactVary = new ContactVary();
	private List<Contract> contractList = new ArrayList<Contract>();
	/**
	 * <p>
	 * Description:contactVary<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public ContactVary getContactVary() {
		return contactVary;
	}
	/**
	 * <p>
	 * Description:contactVary<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContactVary(ContactVary contactVary) {
		this.contactVary = contactVary;
	}
	/**
	 * <p>
	 * Description:contractList<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public List<Contract> getContractList() {
		return contractList;
	}
	/**
	 * <p>
	 * Description:contractList<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public void setContractList(List<Contract> contractList) {
		this.contractList = contractList;
	}
	/**
	 * <p>
	 * Description:serialversionuid<br />
	 * </p>
	 * @author CoCo
	 * @version 0.1 2013-2-27
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
