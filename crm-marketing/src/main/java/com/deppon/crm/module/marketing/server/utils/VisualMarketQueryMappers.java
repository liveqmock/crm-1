package com.deppon.crm.module.marketing.server.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName: VisualMarketMappers 
 * @Description: 可视化营销查询条件范围映射表
 * 规则：(起,止]值采用左闭区间，右开区间
 * @author 133586-石应华
 * @date 2013-4-26 上午9:45:32 
 *
 */
public class VisualMarketQueryMappers {
    /**
     * 收入恢复进度等级映射表
     */
    private static final Map<String,Map<String,String>> INCOME_RESTORE_RATE_MAP = new HashMap<String, Map<String,String>>();
    static {
        //设置0%
        INCOME_RESTORE_RATE_MAP.put("-0",createMap(null,"0"));
        //设置0%-20%
        INCOME_RESTORE_RATE_MAP.put("0-20",createMap("0","20"));
        //设置20%-50%
        INCOME_RESTORE_RATE_MAP.put("20-50",createMap("20","50"));
        //设置50,80%
        INCOME_RESTORE_RATE_MAP.put("50-80",createMap("50","80"));
        //设置80%-100%
        INCOME_RESTORE_RATE_MAP.put("80-100",createMap("80","100"));
        //设置100%-120%
        INCOME_RESTORE_RATE_MAP.put("100-120",createMap("100","120"));
        //设置120%以上
        INCOME_RESTORE_RATE_MAP.put("120-",createMap("120",null));
    }
    
    /**
     * 超期时长等级映射表
     */
    private static final Map<String,Map<String,String>> EXCEED_TIME_MAP = new HashMap<String, Map<String,String>>();
    static {
        //设置0天及空
        EXCEED_TIME_MAP.put("-0",createMap(null,"0"));
        //设置1-5天
        EXCEED_TIME_MAP.put("1-5",createMap("0","5"));
        //设置6-10天
        EXCEED_TIME_MAP.put("6-10",createMap("5","10"));
        //设置11-15天
        EXCEED_TIME_MAP.put("11-15",createMap("10","15"));
        //设置16-20天
        EXCEED_TIME_MAP.put("16-20",createMap("15","20"));
        //设置21-25天
        EXCEED_TIME_MAP.put("21-25",createMap("20","25"));
        //设置26-30天
        EXCEED_TIME_MAP.put("26-30",createMap("25","30"));
    }
    /**
     * 当月收入等级映射表
     */
    private static final Map<String,Map<String,String>> MONTHLY_INCOME_MAP = new HashMap<String, Map<String,String>>();
    static {
        //0及空
        MONTHLY_INCOME_MAP.put("-0",createMap(null,"0"));
        //设置0-200
        MONTHLY_INCOME_MAP.put("0-200",createMap("0","200"));
        //设置200-400
        MONTHLY_INCOME_MAP.put("200-400",createMap("200","400"));
        //设置400-600
        MONTHLY_INCOME_MAP.put("400-600",createMap("400","600"));
        //设置600以上
        MONTHLY_INCOME_MAP.put("600-",createMap("600",null));
    }
    /**
     * 发货票数等级映射表
     */
    private static final Map<String,Map<String,String>> SEND_GOODS_NUMBER_MAP = new HashMap<String, Map<String,String>>();
    static {
        //设置0票
        SEND_GOODS_NUMBER_MAP.put("-0",createMap(null,"0"));
        //设置1-5票
        SEND_GOODS_NUMBER_MAP.put("1-5",createMap("0","5"));
        //设置6-10票
        SEND_GOODS_NUMBER_MAP.put("6-10",createMap("5","10"));
        //设置11-15票
        SEND_GOODS_NUMBER_MAP.put("11-15",createMap("10","15"));
        //设置16-20票
        SEND_GOODS_NUMBER_MAP.put("16-20",createMap("15","20"));
        //设置21票以上
        SEND_GOODS_NUMBER_MAP.put("21-",createMap("20",null));
    }
    
    /**
     * 
     * <p>
     * Description: 构造各阶段起止映射值
     * </p>
     * @author 石应华
     * @param start 起始值
     * @param end 结止值
     * @return Map<String,String> 起止映射值
     * @version
     * @date 2013-4-26 上午9:43:51
     */
    private static Map<String,String> createMap(String start,String end){
        //声明返回的map
        Map<String,String> map = new HashMap<String,String>();
        //设置起始值
        map.put("start", start);
        //设置结束值
        map.put("end", end);
        //返回结果
        return map;
    }
    
    /**
     * <p>
     * Description:根据传入条件得到相应等级映射表
     * </p>
     * @author 石应华
     * @param array 客户选择等级条件
     * @param map 等级映射表
     * @return List<Map<String,String>> 等级映射集合    
     * @version
     * @date 2013-4-26 上午10:20:08
     */
    private static List<Map<String,String>> getMapper(String[] array ,Map<String,Map<String,String>> map){
        //声明返回的等级映射集合    
        List<Map<String,String>> list = new ArrayList<Map<String,String>>();
        //根据传入条件得到相应等级映射表
        for (int i = 0; i < array.length; i++) {
            if(map.containsKey(array[i])){
                list.add(map.get(array[i]));
            }
        }
        //返回集合
        return list;
    }
    
    /**
     * 
     * <p>
     * Description:根据传入条件得到收入恢复进度等级映射表
     * </p>
     * @author 石应华
     * @param array 客户选择等级条件
     * @return List<Map<String,String>> 收入恢复进度等级映射集合   
     * @version
     * @date 2013-4-26 上午10:14:37
     */
    public static List<Map<String,String>> getIncomeRestoreRateMap(String[] array){
        return getMapper(array,INCOME_RESTORE_RATE_MAP);
    }
    /**
     * 
     * <p>
     * Description:根据传入条件得到超期时长等级映射表
     * </p>
     * @author 石应华
     * @param array 客户选择等级条件
     * @return List<Map<String,String>> 超期时长等级映射集合   
     * @version
     * @date 2013-4-26 上午10:14:37
     */
    public static List<Map<String,String>> getExceedTimeMap(String[] array){
        return getMapper(array,EXCEED_TIME_MAP);
    }
    /**
     * 
     * <p>
     * Description:根据传入条件得到当月收入等级映射表
     * </p>
     * @author 石应华
     * @param array 客户选择等级条件
     * @return List<Map<String,String>> 当月收入等级映射集合   
     * @version
     * @date 2013-4-26 上午10:14:37
     */
    public static List<Map<String,String>> getMonthlyIncomeMap(String[] array){
        return getMapper(array,MONTHLY_INCOME_MAP);
    }
    /**
     * 
     * <p>
     * Description:根据传入条件得到发货票数等级映射表
     * </p>
     * @author 石应华
     * @param array 客户选择等级条件
     * @return List<Map<String,String>> 发货票数等级映射集合   
     * @version
     * @date 2013-4-26 上午10:14:37
     */
    public static List<Map<String,String>> getSendGoodsNumberMap(String[] array){
        return getMapper(array,SEND_GOODS_NUMBER_MAP);
    }
}
