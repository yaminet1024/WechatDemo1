package cn.yami.wechat.demo.utils;

import org.thymeleaf.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorUtil {

    //正则表达式，以1开头的10位数字
    private static final Pattern mobile_pattern = Pattern.compile("1\\d{10}");

    public static boolean isMobile(String str){
        if (StringUtils.isEmpty(str)){
            return false;
        }
        Matcher m = mobile_pattern.matcher(str);
        return m.matches();
    }
}
