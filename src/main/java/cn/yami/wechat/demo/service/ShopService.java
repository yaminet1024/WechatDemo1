package cn.yami.wechat.demo.service;

import cn.yami.wechat.demo.dao.ShopDao;
import cn.yami.wechat.demo.dto.Shops;
import cn.yami.wechat.demo.dto.SpikeShops;
import cn.yami.wechat.demo.entity.ShopVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {

    @Autowired
    ShopDao shopDao;

    public List<ShopVo> listShopVo(){
        return shopDao.listShopVo();
    }

    public ShopVo getShopVoByShopId(long shopId) {
        return shopDao.getShopVoByShopId(shopId);
    }

    public void reduceStock(ShopVo shopVo) {
        SpikeShops spikeShops = new SpikeShops();
        spikeShops.setShopId(shopVo.getId());
        shopDao.reduceStock(spikeShops);
    }
}
