/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title FirstCharGetUtil.java
 * @package com.deppon.crm.module.customer.server.utils 
 * @author pgj
 * @version 0.1 2014-3-14
 */
package com.deppon.crm.module.customer.server.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * <p>
 * Description:这里写描述<br />
 * </p>
 * 
 * @title FirstCharGetUtil.java
 * @package com.deppon.crm.module.customer.server.utils
 * @author pgj
 * @version 0.1 2014-3-14
 */

public class FirstCharGetUtil {
	private static final Logger logger = Logger.getLogger(FirstCharGetUtil.class);

    //字母Z使用了两个标签，这里有２７个值
    //i, u, v都不做声母, 跟随前面的字母
    private char[] chartable =
            {
                '啊', '芭', '擦', '搭', '蛾', '发', '噶', '哈', '哈',
                '击', '喀', '垃', '妈', '拿', '哦', '啪', '期', '然',
                '撒', '塌', '塌', '塌', '挖', '昔', '压', '匝', '座'
            };

    private char[] alphatable =
            {
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
                'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 
                's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
            };


    private int[] table = new int[27];
    //初始化
    {
        for (int i = 0; i < 27; ++i) {
            table[i] = gbValue(chartable[i]);
        }
    }

    //主函数,输入字符,得到他的声母,
    //英文字母返回对应的大写字母
    //其他非简体汉字返回 '0'

    public char Char2Alpha(char ch) {

        if (ch >= 'a' && ch <= 'z')
            return (char) (ch - 'a' + 'A');
        if (ch >= 'A' && ch <= 'Z')
            return ch;
        int gb = gbValue(ch);
        if (gb < table[0])
            return '0';
        int i;
        for (i = 0; i < 26; ++i) {
            if (match(i, gb)) break;
        }
        if (i >= 26)
            return '0';
        else
            return alphatable[i];
    }

    //根据一个包含汉字的字符串返回一个汉字拼音首字母的字符串
    public String getFirstChar(String SourceStr){
        if (StringUtils.isEmpty(SourceStr)) {
			return null;
		}
        String Result = "";
        int StrLength = SourceStr.length();
        int i;
        try {
            for (i = 0; i < StrLength; i++) {
                Result += Char2Alpha(SourceStr.charAt(i));
            }
        } catch (Exception e) {
			logger.info("Exception", e);
            Result = "";
        }
        return Result;
    }

    private boolean match(int i, int gb) {
        
        if (gb < table[i])
            return false;
        int j = i + 1;

        //字母Z使用了两个标签
        while (j < 26 && (table[j] == table[i])) ++j;
        if (j == 26)
            return gb <= table[j];
        else
            return gb < table[j];
    }

    //取出汉字的编码
    private int gbValue(char ch) {
        
        String str = new String();
        str += ch;
        try {
            byte[] bytes = str.getBytes("GB2312");
            if (bytes.length < 2)
                return 0;
            return (bytes[0] << 8 & 0xff00) + (bytes[1] &
                    0xff);
        } catch (Exception e) {
			logger.info("Exception", e);
            return 0;
        }
    }
}
