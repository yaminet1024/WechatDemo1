package cn.yami.wechat.demo.dao;

import cn.yami.wechat.demo.dto.OrderInfo;
import cn.yami.wechat.demo.dto.SpikeOrder;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface OrderDao {

    @Select("select * from spike_order where user_id = #{userId} and shops_id = #{shopId}")
    SpikeOrder getSpikeOrderByUserId(@Param("userId") long id, @Param("shopId") long shopId);

    @Insert("insert into order_info(user_id,shops_id,shops_name,shops_count,shops_price,order_channel,status,create_date)" +
            " values(#{userId},#{shopId},#{shopName},#{shopCount},#{shopPrice},#{orderChannel},#{status},#{createDate})")
    @SelectKey(keyColumn = "id",keyProperty = "id",resultType = long.class,before = false,statement = "select last_insert_id()")
    long insertOrder(OrderInfo orderInfo);

    @Insert("insert into spike_order(user_id,order_id,shops_id) values(#{userId},#{orderId},#{shopId})")
    int insertSpikeOrder(SpikeOrder order);
}
