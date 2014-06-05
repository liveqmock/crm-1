package com.deppon.crm.module.marketing.utils;

import junit.framework.TestCase;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;

import com.deppon.crm.module.customer.server.utils.ValidateUtil;
import com.deppon.crm.module.marketing.server.utils.VisualMarketUtils;
import com.deppon.crm.module.marketing.server.utils.VisualMarketValidator;
import com.deppon.crm.module.marketing.shared.domain.visualMarket.QueryCondition;
import com.deppon.crm.module.marketing.shared.exception.VisualMarketException;
import com.deppon.crm.module.marketing.shared.exception.VisualMarketExceptionType;
import com.deppon.foss.framework.exception.GeneralException;
import com.deppon.foss.framework.shared.util.string.StringUtil;

/**
 * 
 * <p>
 * Description:可视化营销查询校验类<br />
 * </p>
 * @title VisualMarketValidator.java
 * @package com.deppon.crm.module.marketing.server.utils 
 * @author 石应华
 * @version 0.1 2013-5-4
 */
public class VisualMarketValidatorTest extends TestCase{
    
    /**
     * <p>
     * Description:验证查询条件为空的情况<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-5-23
     * @param condition
     * void
     */
    public void testCheckCondition(){
        QueryCondition condition = new QueryCondition();
        VisualMarketValidator.checkCondition(condition);
    }
    
    /**
     * 
     * <p>
     * Description:校验通过客户id和客户类型是否为空<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-5-4
     * @param custId 客户id
     * @param custType 客户类型
     * void
     */
    public void testCheckSearchCustById(){
        VisualMarketValidator.checkSearchCustById("110111","MEMENBER");
    }
    
    /**
     * 
     * <p>
     * Description:校验可视化视图查询查询条件<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-5-6
     * @param condition 查询条件
     * void
     */
    public void testCheckSearchCustMarketInfo(){
        QueryCondition condition = new QueryCondition();
        condition.setDeptId("11103");
        condition.setCustDegree(new String[]{"NORMAL"});
        condition.setMapMakerStatus("MARDED");
        VisualMarketValidator.checkSearchCustMarketInfo(condition);
    }
    
    /**
     * 
     * <p>
     * Description:如果传入的部门ID为空，给出异常提示"部门ID为空，获取部门客户标签失败"<br />
     * </p>
     * @author 石应华
     * @version 0.1 2013-5-6
     * @param deptId 部门ID
     * void
     */
    public void testCheckDeptId(){
        VisualMarketValidator.checkDeptId("11101");
    }
    
}
