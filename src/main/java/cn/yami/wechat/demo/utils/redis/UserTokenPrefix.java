package cn.yami.wechat.demo.utils.redis;

public class UserTokenPrefix extends BasePrefix {
    private static final int TOKEN_EXPIRE = 3600*24*2;
    public UserTokenPrefix(String prefix) {
        super(prefix);
    }

    public UserTokenPrefix(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static UserTokenPrefix token = new UserTokenPrefix(TOKEN_EXPIRE,"token");
}
