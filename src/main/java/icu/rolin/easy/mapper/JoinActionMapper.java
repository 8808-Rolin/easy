package icu.rolin.easy.mapper;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mybatis.caches.redis.RedisCache;

@Mapper
@CacheNamespace(implementation = RedisCache.class)
public interface JoinActionMapper {

    @Insert("INSERT INTO join_action (act_id,u_id) VALUES(#{acid},#{uid})")
    Integer insertJoinActionForm(Integer acid,Integer uid);

    @Select("SELECT COUNT(u_id) FROM join_action WHERE act_id = #{acid} and u_id = #{uid}")
    Integer verifyUserJoinActionById(Integer acid,Integer uid);

}
