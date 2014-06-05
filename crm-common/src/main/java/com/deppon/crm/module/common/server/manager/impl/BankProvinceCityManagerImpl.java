package com.deppon.crm.module.common.server.manager.impl;

import com.deppon.crm.module.common.server.manager.IBankProvinceCitymanager;
import com.deppon.crm.module.common.server.service.IBankProvinceCityService;
import com.deppon.crm.module.common.shared.domain.AccountBank;
import com.deppon.crm.module.common.shared.domain.AccountBranch;
import com.deppon.crm.module.common.shared.domain.BankCity;
import com.deppon.crm.module.common.shared.domain.BankProvince;

public class BankProvinceCityManagerImpl implements IBankProvinceCitymanager {
	private IBankProvinceCityService bankProvinceCityService;

	/**
	 * @return bankProvinceCityService : return the property
	 *         bankProvinceCityService.
	 */
	public IBankProvinceCityService getBankProvinceCityService() {
		return bankProvinceCityService;
	}

	/**
	 * @param bankProvinceCityService
	 *            : set the property bankProvinceCityService.
	 */
	public void setBankProvinceCityService(
			IBankProvinceCityService bankProvinceCityService) {
		this.bankProvinceCityService = bankProvinceCityService;
	}

	/**
	 * 
	 * <p>
	 * Description:根据Id查询银行城市信息（有效）<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-18
	 * @param id
	 * @return BankCity
	 */
	public BankCity getBankCityById(String id) {
		return getBankProvinceCityService().getBankCityById(id);
	}

	/**
	 * 
	 * <p>
	 * Description:根据Id查询银行城市信息<br />
	 * </p>
	 * 
	 * @author tangliang
	 * @version 0.1 2013-1-18
	 * @param id
	 * @return BankCity
	 */
	public BankCity getAllBankCityById(String id) {
		return getBankProvinceCityService().getAllBankCityById(id);
	}
	
	/**
	 * 
	 * <p>
	 * Description:根据id查询银行城市信息（有效）<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-18
	 * @param id
	 * @return BankProvince
	 */
	public BankProvince getBankProvinceById(String id) {
		return getBankProvinceCityService().getBankProvinceById(id);
	}

	/**
	 * 
	 * <p>
	 * Description:根据id查询银行城市信息<br />
	 * </p>
	 * 
	 * @author tangliang
	 * @version 0.1 2013-1-18
	 * @param id
	 * @return BankProvince
	 */
	public BankProvince getAllBankProvinceById(String id) {
		return getBankProvinceCityService().getAllBankProvinceById(id);
	}
	
	/**
	 * 
	 * <p>
	 * Description:根据id查询银行信息（有效）<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-18
	 * @param id
	 * @return AccountBank
	 */
	public AccountBank getAccountBankById(String id) {
		return getBankProvinceCityService().getAccountBankById(id);

	}

	/**
	 * 
	 * <p>
	 * Description:根据id查询银行信息<br />
	 * </p>
	 * 
	 * @author tangliang
	 * @version 0.1 2013-1-18
	 * @param id
	 * @return AccountBank
	 */
	public AccountBank getAllAccountBankById(String id) {
		return getBankProvinceCityService().getAllAccountBankById(id);

	}
	
	/**
	 * 
	 * <p>
	 * Description:根据id查询支行信息（有效）<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-18
	 * @param id
	 * @return AccountBranch
	 */

	public AccountBranch getAccountBranchById(String id) {
		return getBankProvinceCityService().getAccountBranchById(id);

	}

	/**
	 * 
	 * <p>
	 * Description:根据id查询支行信息<br />
	 * </p>
	 * 
	 * @author tangliang
	 * @version 0.1 2013-1-18
	 * @param id
	 * @return AccountBranch
	 */

	public AccountBranch getAllAccountBranchById(String id) {
		return getBankProvinceCityService().getAllAccountBranchById(id);

	}
	
	/**
	 * 
	 * <p>
	 * Description:根据Id查询银行城市信息<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-18
	 * @param id
	 * @return BankCity
	 */
	public BankCity getBankCityByCode(String code) {
		return getBankProvinceCityService().getBankCityByCode(code);
	}

	/**
	 * 
	 * <p>
	 * Description:根据id查询银行城市信息<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-18
	 * @param id
	 * @return BankProvince
	 */
	public BankProvince getBankProvinceByCode(String code) {
		return getBankProvinceCityService().getBankProvinceByCode(code);
	}

	/**
	 * 
	 * <p>
	 * Description:根据code查询银行信息<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-18
	 * @param id
	 * @return AccountBank
	 */
	public AccountBank getAccountBankByCode(String code) {
		return getBankProvinceCityService().getAccountBankByCode(code);

	}

	/**
	 * 
	 * <p>
	 * Description:根据code查询支行信息<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-18
	 * @param id
	 * @return AccountBranch
	 */

	public AccountBranch getAccountBranchByCode(String code) {
		return getBankProvinceCityService().getAccountBranchByCode(code);

	}

	@Override
	public BankCity getBankCityByName(String name) {
		return bankProvinceCityService.getBankCityByName(name);
	}

	@Override
	public BankProvince getBankProvinceByName(String name) {
		return bankProvinceCityService.getBankProvinceByName(name);
	}

	@Override
	public AccountBank getAccountBankByName(String name) {
		return bankProvinceCityService.getAccountBankByName(name);
	}

	@Override
	public AccountBranch getAccountBranchByName(String name) {
		return bankProvinceCityService.getAccountBranchByName(name);
	}

}
