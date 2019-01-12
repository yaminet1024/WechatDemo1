package cn.yami.wechat.demo.service;

import cn.yami.wechat.demo.dao.OrderDao;
import cn.yami.wechat.demo.dto.DemoUser;
import cn.yami.wechat.demo.dto.OrderInfo;
import cn.yami.wechat.demo.dto.SpikeOrder;
import cn.yami.wechat.demo.entity.ShopVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class OrderService {

    @Autowired
    OrderDao orderDao;

    public SpikeOrder getSpikeOrderByUserId(long id, long shopId) {
        return orderDao.getSpikeOrderByUserId(id,shopId);
    }

    @Transactional
    public OrderInfo createOrder(DemoUser user, ShopVo shopVo) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCreateDate(new Date());
        orderInfo.setShopCount(1);
        orderInfo.setShopId(shopVo.getId());
        orderInfo.setShopName(shopVo.getShopName());
        orderInfo.setShopPrice(shopVo.getShopPrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setStatus(0);
        orderInfo.setUserId(user.getId());
        long orderId = orderDao.insertOrder(orderInfo);
        SpikeOrder order = new SpikeOrder();
        order.setShopId(shopVo.getId());
        order.setOrderId(orderId);
        order.setUserId(user.getId());
        orderDao.insertSpikeOrder(order);
        return orderInfo;
    }
}
