package cn.yami.wechat.demo.utils;

import java.util.UUID;

public class UUIDUtil {
    public static String UUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
