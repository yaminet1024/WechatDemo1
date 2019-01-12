package cn.yami.wechat.demo.dao;

import cn.yami.wechat.demo.dto.Shops;
import cn.yami.wechat.demo.dto.SpikeShops;
import cn.yami.wechat.demo.entity.ShopVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ShopDao {

    @Select("select s.*,ss.stock_count,ss.start_date,ss.over_date,ss.spike_price from spike_shops ss left join shops s on ss.shops_id = s.id")
    List<ShopVo> listShopVo();

    @Select("select s.*,ss.stock_count,ss.start_date,ss.over_date,ss.spike_price from spike_shops ss left join shops s " +
            "on ss.shops_id = s.id where s.id = #{shopId}")

    ShopVo getShopVoByShopId(@Param("shopId") long shopId);

    @Update("update spike_shops set stock_count = stock_count - 1 where shops_id = #{shopId}")
    int reduceStock(SpikeShops shops);
}
