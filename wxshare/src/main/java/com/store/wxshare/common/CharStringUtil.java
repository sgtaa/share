package com.store.wxshare.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author suguotai
 * @Description //TODO 文字处理工具类
 * @Date
 * @Param
 * @return
 **/
public class CharStringUtil {
    /**
     * 通过正则表达式获取括号内的内容
     *
     * @param strname
     * @return
     */
    public static String getPatternString(String strname) {
        String name = null;
        // 从内容上截取路径各类括号("()","（）","{}","[]")内的内容数组
        Pattern pattern = Pattern.compile("(?<=\\()[^\\)]+|(?<=\\（)[^\\）]+|(?<=\\[)[^\\]]+|(?<=\\{)[^\\}]+");
        Matcher matcher = pattern.matcher(strname);
        while (matcher.find()) {
            name = matcher.group();
        }
        return name;
    };
}
