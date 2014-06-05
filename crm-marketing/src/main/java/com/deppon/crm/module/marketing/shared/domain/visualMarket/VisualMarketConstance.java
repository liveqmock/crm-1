/**
 * <p>
 * Description:可视化营销中需要使用的常量<br />
 * </p>
 * @title VisualMarketConstance.java
 * @package com.deppon.crm.module.marketing.shared.domain.visualMarket
 * @author xiaohongye
 * @version V0.1 
 * @Date 2013-4-19
 */
package com.deppon.crm.module.marketing.shared.domain.visualMarket;

/**
 * <p>
 * Description:可视化营销中需要使用的常量<br />
 * </p>
 * @title VisualMarketConstance.java
 * @package com.deppon.crm.module.marketing.shared.domain.visualMarket
 * @author xiaohongye
 * @version V0.1 
 * @Date 2013-4-19
 */
public class VisualMarketConstance {
	/**
	 * 客户类型
	 */
    //固定客户
	public static final String CUST_MEMBER = "MEMBER";
	//潜客
	public static final String CUST_PC_CUSTOMER = "PC_CUSTOMER";
	//散客
	public static final String CUST_SC_CUSTOMER = "SC_CUSTOMER";
	
	/**
	 * 查询条件约定常量
	 */
    //查询记录开始条数
    public static final int FIRST_COL = 0;
    //查询记录条数
    public static final int RECORD_NUM = 150;
    //标记客户已标记
    public static final String MAP_MAKED = "MAPMAKED";
    //标记客户未标记
    public static final String MAP_UNMAKER = "UNMAKER";
    //查询所有
    public static final String SEARCH_ALL = "-1";
    //地图标记查询所有
    public static final String MARK_STATUS = "ALL";
    //回访状态
    public static final String RETURN_VISIT = "3";
    //当月收入
    public static final String[] MONTHLY_INCOME = new String[]{"-1","0-200"};
    //发货次数
    public static final String[] SEND_GOODS_NUM = new String[]{"-1","0-5"};
    //收入进度
    public static final String[] INCOME_RESTORE_RATE = new String[]{"-1","0-20%"};
    //超期时长
    public static final String[] EXCEED_TIME = new String[]{"-1","0-7"};
    //根据客户id查询客户起始记录条数
    public static final int PAGING_START = 0;
    //根据客户id查询客户记录总条数
    public static final int PAGING_LIMIT = 1;
    //查询单个客户回访信息条数
    public static final int PAGING_RETURN_VISIT = 10;
    
    /**
     * 可视化营销查询条件范围映射常量
     */
    //可视化营销0
    public static final int VISUAL_MARKET_ZERO = 0;

}
