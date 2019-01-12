package cn.yami.wechat.demo.dao;

import cn.yami.wechat.demo.dto.DemoUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.Date;

@Mapper
@Component
public interface DemoUserDAO {
    @Select("select * from user where id = #{id}")
    public DemoUser getById(@Param("id") long id);

    @Update("update user set login_count=login_count+1 where id = #{id}")
    int incLoginCount(@Param("id") long id);

    @Update("update user set last_login_date=#{date} where id = #{id}")
    int setLastLoginDate(@Param("id") long id, @Param("date") Date date);
}
