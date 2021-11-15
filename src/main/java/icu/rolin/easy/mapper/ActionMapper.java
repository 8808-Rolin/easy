package icu.rolin.easy.mapper;

import icu.rolin.easy.model.DO.Action;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mybatis.caches.redis.RedisCache;

import java.util.ArrayList;

@Mapper
@CacheNamespace(implementation = RedisCache.class)
public interface ActionMapper {

    @Select("SELECT * FROM action WHERE a_id = #{aid}")
    ArrayList<Action> findByA_id(Integer aid);

    @Select("SELECT * FROM action WHERE id = #{id}")
    Action getDetailedAssActioonByAcId(Integer id);

    @Select("SELECT COUNT(*) FROM action WHERE a_id = #{aid} AND DATE(start_time) >= #{currentTime}")
    Integer getToBeHeldActionsNumber(Integer aid,String currentTime);
}
