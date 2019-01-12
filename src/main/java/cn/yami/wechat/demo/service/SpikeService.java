package cn.yami.wechat.demo.service;

import cn.yami.wechat.demo.dto.DemoUser;
import cn.yami.wechat.demo.dto.OrderInfo;
import cn.yami.wechat.demo.dto.Shops;
import cn.yami.wechat.demo.entity.ShopVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SpikeService {

    @Autowired
    ShopService shopService;

    @Autowired
    OrderService orderService;

    @Transactional
    public OrderInfo spike(DemoUser user, ShopVo shopVo) {
        shopService.reduceStock(shopVo);
        return orderService.createOrder(user,shopVo);
    }
}
