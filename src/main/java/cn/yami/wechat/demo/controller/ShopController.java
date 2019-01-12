package cn.yami.wechat.demo.controller;

import cn.yami.wechat.demo.dto.DemoUser;
import cn.yami.wechat.demo.dto.OrderInfo;
import cn.yami.wechat.demo.dto.SpikeOrder;
import cn.yami.wechat.demo.entity.ShopVo;
import cn.yami.wechat.demo.enums.CodeMessage;
import cn.yami.wechat.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value = "/shop")
public class ShopController {

    @Autowired
    RedisService redisService;

    @Autowired
    DemoUserService demoUserService;

    @Autowired
    ShopService shopService;

    @Autowired
    OrderService orderService;

    @Autowired
    SpikeService spikeService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String getShopList(Model model,DemoUser demoUser){
        model.addAttribute("user",demoUser);
        List<ShopVo> shopVoList = shopService.listShopVo();
        model.addAttribute("shopList",shopVoList);
        return "shop_list";
    }

    @RequestMapping(value = "/detail/{id}",method = RequestMethod.GET)
    public String detail(Model model, DemoUser demoUser, @PathVariable("id") long shopId){
        model.addAttribute("user",demoUser);
        ShopVo shopVo = shopService.getShopVoByShopId(shopId);
        model.addAttribute("shop",shopVo);

        long start = shopVo.getStartDate().getTime();
        long over = shopVo.getOverDate().getTime();
        long now = System.currentTimeMillis();

        int status = 0;
        int remainSeconds = 0;

        if (now < start){
            status = 0;
            remainSeconds = (int) ((start-now)/1000);
        }else if (now>over){
            status = 2;
            remainSeconds = -1;
        }else {
            status = 1;
            remainSeconds = 0;
        }

        model.addAttribute("spikeStatus",status);
        model.addAttribute("remain",remainSeconds);
        return "detail";
    }

    @RequestMapping(value = "/spike")
    public String spike(Model model,DemoUser user,@RequestParam("shopId") long shopId){
        model.addAttribute("user",user);
        if (user == null){
            return "login";
        }
        ShopVo shopVo = shopService.getShopVoByShopId(shopId);
        int stock = shopVo.getStockCount();
        if (stock<=0){
            model.addAttribute("errmsg", CodeMessage.SPIKE_OVER.getResultMessage());
            return "fail";
        }
        SpikeOrder order = orderService.getSpikeOrderByUserId(user.getId(),shopId);
        if (order != null){
            model.addAttribute("errmsg", CodeMessage.REPEAT_SPIKE.getResultMessage());
            return "fail";
        }
        OrderInfo info  = spikeService.spike(user,shopVo);
        model.addAttribute("orderInfo",info);
        model.addAttribute("shop",shopVo);
        return "order_detail";

    }




}
