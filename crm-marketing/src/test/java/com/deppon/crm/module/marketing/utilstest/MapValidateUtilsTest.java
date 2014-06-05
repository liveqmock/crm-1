/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title MapValidateUtilsTest.java
 * @package com.deppon.crm.module.marketing.utilstest 
 * @author ZhuPJ
 * @version 0.1 2012-12-5
 */
package com.deppon.crm.module.marketing.utilstest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.deppon.crm.module.marketing.server.utils.MapValidateUtils;
import com.deppon.crm.module.marketing.shared.domain.CustomerLocation;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title MapValidateUtilsTest.java
 * @package com.deppon.crm.module.marketing.utilstest 
 * @author ZhuPJ
 * @version 0.1 2012-12-5
 */

public class MapValidateUtilsTest {
	@Test
	public void setCanSaveLocationInfo()  {
		try {
			List<CustomerLocation> customerLocationList = new ArrayList<CustomerLocation>();
			
			MapValidateUtils.canSaveLocationInfo(customerLocationList);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
