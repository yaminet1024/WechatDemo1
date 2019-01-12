package cn.yami.wechat.demo.utils;

import org.springframework.util.DigestUtils;

import java.util.*;

public class MD5Util {

    private static final String salt = "Yami92742520YCP";
    public static final String MAP_KEY_OF_SALT = "SALT";
    public static final String MAP_KEY_OF_PASSWORD = "PASSWORD";

    private static String md5(String src){
        return DigestUtils.md5DigestAsHex(src.getBytes());
    }

    //返回一个键为密码，值为混淆参数的Map
    private static Map<String,String> getPass(String inputPass){
        int length = salt.length();
        Random random = new Random();
        String key = String.valueOf(salt.charAt(random.nextInt(length)) + salt.charAt(random.nextInt(length)));
        String pass = md5(key + inputPass);
        Map<String,String> result = new HashMap<>();
        result.put(MAP_KEY_OF_SALT,key);
        result.put(MAP_KEY_OF_PASSWORD,pass);
        return result;
    }

    public static String getEncrypt(String password,String salt){
        return md5(salt + password);
    }

//    public static void main(String[] args) {
//        Map<String,String> map = getPass("bad0f2c57a333ad0dc1d493f3389cc27");
//        System.out.println(map.get(MAP_KEY_OF_SALT) + " " + map.get(MAP_KEY_OF_PASSWORD));
//    }
}
