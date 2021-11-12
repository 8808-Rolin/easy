package icu.rolin.easy.mapper;

import org.apache.ibatis.annotations.*;
import org.mybatis.caches.redis.RedisCache;

@Mapper
@CacheNamespace(implementation = RedisCache.class)
public interface FavoriteTableMapper {

    @Select("SELECT count(*) FROM favorite_table WHERE p_id = #{pid} and u_id = #{uid}")
    Integer isFavorite(Integer pid,Integer uid);

    @Insert("INSERT INTO favorite_table (p_id,u_id) VALUES(#{pid},#{uid})")
    Integer collectPost(Integer pid,Integer uid);

}
