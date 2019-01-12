package cn.yami.wechat.demo.service;

import cn.yami.wechat.demo.dao.DemoUserDAO;
import cn.yami.wechat.demo.dto.DemoUser;
import cn.yami.wechat.demo.dto.LoginInfo;
import cn.yami.wechat.demo.enums.CodeMessage;
import cn.yami.wechat.demo.exception.GlobalException;
import cn.yami.wechat.demo.utils.MD5Util;
import cn.yami.wechat.demo.utils.UUIDUtil;
import cn.yami.wechat.demo.utils.redis.UserTokenPrefix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Service
public class DemoUserService {

    public static final String COOKIE_NAME_TOKEN = "token";

    @Autowired
    DemoUserDAO demoUserDAO;

    @Autowired
    RedisService redisService;

    public DemoUser getById(long id){
        return demoUserDAO.getById(id);
    }

    public boolean login(HttpServletResponse response,LoginInfo loginInfo) {
        String mobile = loginInfo.getMobile();
        String password = loginInfo.getPassword();
        DemoUser demoUser = getById(Long.parseLong(mobile));
        if (demoUser == null){
            throw new GlobalException(CodeMessage.MOBILE_NOT_EXIST);
        }
        String encryptPass = MD5Util.getEncrypt(password,demoUser.getSalt());
        if (!demoUser.getPassword().equals(encryptPass)){
            throw new GlobalException(CodeMessage.PASSWORD_ERROR);
        }
        demoUserDAO.incLoginCount(Long.parseLong(mobile));
        demoUserDAO.setLastLoginDate(Long.parseLong(mobile),new Date());
        String token = UUIDUtil.UUID();
        addCookie(response,token,demoUser);
        return true;
    }

    public DemoUser getByToken(HttpServletResponse response,String token) {
        if (StringUtils.isEmpty(token)){
            return null;
        }
        //延长有效期
        DemoUser user = redisService.get(UserTokenPrefix.token,token,DemoUser.class);
        assert user!=null;
        addCookie(response,token,user);
        return user;
    }


    private void addCookie(HttpServletResponse response,String token,DemoUser demoUser){
        redisService.set(UserTokenPrefix.token,token,demoUser);
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN,token);
        cookie.setMaxAge(UserTokenPrefix.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
