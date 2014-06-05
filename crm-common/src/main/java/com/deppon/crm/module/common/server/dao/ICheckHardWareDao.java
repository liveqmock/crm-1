/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ICheckHardWareDao.java
 * @package com.deppon.crm.module.common.server.dao 
 * @author ZhuPJ
 * @version 0.1 2012-7-11
 */
package com.deppon.crm.module.common.server.dao;

import java.util.Date;
import java.util.List;

import com.deppon.crm.module.common.shared.domain.HardWareInfo;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ICheckHardWareDao.java
 * @package com.deppon.crm.module.common.server.dao 
 * @author ZhuPJ
 * @version 0.1 2012-7-11
 */

public interface ICheckHardWareDao {
    /**
     * <p>
     * Description: 根据客户端提交信息查询硬件验证信息<br />
     * </p>
     * @author ZhuPJ
     * @version 0.1 2012-7-11
     * @param info
     * @return
     * HardWareInfo
     */
    public HardWareInfo getHardWareInfo(HardWareInfo info);

    /**
     * 
     * <p>
     * Description:查询所有客户端提交信息查询硬件验证信息<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-6-26
     * void
     */
    public List<HardWareInfo> getAllHardWareInfo();

    /**
     * 
     * <p>
     * Description:得到PC机硬件信息最后一次修改时间<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-6-27
     * void
     */
    public Date getLastModifyTime();
}
