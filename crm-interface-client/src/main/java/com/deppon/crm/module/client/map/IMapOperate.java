package com.deppon.crm.module.client.map;

import com.deppon.crm.module.client.common.exception.CrmBusinessException;
import com.deppon.crm.module.client.map.domain.AddressInfo;


/**
 * 通过地址信息返回地址最近营业部门编码
 * @author davidcun @2012-5-14
 */
public interface IMapOperate {

	/**
	 * 返回地址最近的营业部门编码
	 * @param       
	 * @return      
	 * @Throws 
	 * @author davidcun 2012-5-14
	 *
	 */
	@Deprecated
	public String findDeptCodeByAddress(AddressInfo addressInfo) throws CrmBusinessException;

	/**
	 * 
	 * @param address 具体的地址，比如：   上海市青浦区徐泾镇 明珠路1018号
	 * @return   返回标杆编码   
	 * @Throws 
	 * @author davidcun 2012-5-16
	 *
	 */
	public String findDeptCodeByAddress(String address) throws CrmBusinessException;
}
