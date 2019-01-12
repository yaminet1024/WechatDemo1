package cn.yami.wechat.demo.utils.redis;

public interface KeyPrefix {
    //过期时间,0代表永不过期
    public int expireSeconds();
    public String getPrefix();
}
