package cn.yami.wechat.demo.controller;


import cn.yami.wechat.demo.dto.LoginInfo;
import cn.yami.wechat.demo.entity.Result;
import cn.yami.wechat.demo.enums.CodeMessage;
import cn.yami.wechat.demo.service.DemoUserService;
import cn.yami.wechat.demo.utils.ValidatorUtil;
import com.sun.org.apache.bcel.internal.classfile.Code;
import io.netty.util.internal.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    DemoUserService demoUserService;

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String login(){
        return "login";
}

    @RequestMapping(value = "/do_login",method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> toLogin(HttpServletResponse response, @Valid LoginInfo loginInfo){
        logger.info(loginInfo.toString());
        boolean isLogin = demoUserService.login(response,loginInfo);
        logger.info(Boolean.toString(isLogin));
        return Result.success(true);
    }
}
