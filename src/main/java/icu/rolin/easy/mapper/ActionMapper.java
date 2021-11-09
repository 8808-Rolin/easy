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
    ArrayList<Action> findAssActionsByA_id(Integer aid);

    @Select("SELECT * FROM action WHERE id = #{id}")
    Action getDetailedAssActioonByAcId(Integer id);

}
