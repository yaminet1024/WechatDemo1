package cn.yami.wechat.demo.controller;

import cn.yami.wechat.demo.dao.YamiDAO;
import cn.yami.wechat.demo.dto.Yami;
import cn.yami.wechat.demo.service.RedisService;
import cn.yami.wechat.demo.utils.redis.UserKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping(value = "/yami")
public class TestController {

    @Autowired
    YamiDAO yamiDAO;

    @Autowired
    RedisService redisService;

    @ResponseBody
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(){
        return "Hello,I'm Yami.";
    }

    @RequestMapping(value = "/info",method = RequestMethod.GET,produces = "application/json;character=UTF-8")
    @ResponseBody
    public Yami getMyInfo(){
        Yami yami = yamiDAO.queryMyInfo();
        return yami;
    }


    @RequestMapping(value = "/info/get",method = RequestMethod.GET,produces = "application/json;character=UTF-8")
    @ResponseBody
    public String getFromRedis(){
        String redisTest = redisService.get(UserKey.getById,""+1,String.class);
        return redisTest;
    }

    @RequestMapping(value = "/info/set",method = RequestMethod.GET,produces = "application/json;character=UTF-8")
    @ResponseBody
    public String setInfoInRedis(){
        Boolean res = redisService.set(UserKey.getById,"key2","Hello Redis");
        String str = redisService.get(UserKey.getById,"key2",String.class);
        return str;
    }
}
