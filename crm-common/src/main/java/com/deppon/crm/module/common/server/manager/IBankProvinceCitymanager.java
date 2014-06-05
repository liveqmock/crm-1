package com.deppon.crm.module.common.server.manager;

import com.deppon.crm.module.common.shared.domain.AccountBank;
import com.deppon.crm.module.common.shared.domain.AccountBranch;
import com.deppon.crm.module.common.shared.domain.BankCity;
import com.deppon.crm.module.common.shared.domain.BankProvince;

public interface IBankProvinceCitymanager {
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
	public BankCity getBankCityById(String id);

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
	public BankCity getAllBankCityById(String id);
	
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
	public BankProvince getBankProvinceById(String id);

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
	public BankProvince getAllBankProvinceById(String id);
	
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
	public AccountBank getAccountBankById(String id);

	/**
	 * 
	 * <p>
	 * Description:根据id查询银行信息<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-18
	 * @param id
	 * @return AccountBank
	 */
	public AccountBank getAllAccountBankById(String id);
	
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

	public AccountBranch getAccountBranchById(String id);
	
	/**
	 * 
	 * <p>
	 * Description:根据id查询支行信息<br />
	 * </p>
	 * 
	 * @author roy
	 * @version 0.1 2013-1-18
	 * @param id
	 * @return AccountBranch
	 */

	public AccountBranch getAllAccountBranchById(String id);
	
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
	public BankCity getBankCityByCode(String code);

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
	public BankProvince getBankProvinceByCode(String code);

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
	public AccountBank getAccountBankByCode(String code);

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

	public AccountBranch getAccountBranchByCode(String code);
	
	/**
	 * 
	 * <p>
	 * Description:根据name查询银行城市信息<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-18
	 * @param id
	 * @return
	 * BankCity
	 */
	public BankCity getBankCityByName(String name);
	/**
	 * 
	 * <p>
	 * Description:根据name查询银行城市信息<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-18
	 * @param id
	 * @return
	 * BankProvince
	 */
	public BankProvince getBankProvinceByName(String name);
	/**
	 * 
	 * <p>
	 * Description:根据name查询银行信息<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-18
	 * @param id
	 * @return
	 * AccountBank
	 */
	public AccountBank getAccountBankByName(String name);
	/**
	 * 
	 * <p>
	 * Description:根据name查询支行信息<br />
	 * </p>
	 * @author roy
	 * @version 0.1 2013-1-18
	 * @param id
	 * @return
	 * AccountBranch
	 */

	
	public AccountBranch getAccountBranchByName(String name);

}
