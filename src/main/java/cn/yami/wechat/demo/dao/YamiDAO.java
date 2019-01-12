package cn.yami.wechat.demo.dao;

import cn.yami.wechat.demo.dto.Yami;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface YamiDAO {
    Yami queryMyInfo();
}
