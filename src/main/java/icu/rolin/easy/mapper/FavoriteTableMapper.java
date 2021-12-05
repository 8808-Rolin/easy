package icu.rolin.easy.mapper;

import icu.rolin.easy.model.DO.Favorite_Table;
import org.apache.ibatis.annotations.*;
import org.mybatis.caches.redis.RedisCache;

import java.sql.Array;
import java.sql.Timestamp;
import java.util.ArrayList;

@Mapper
@CacheNamespace(implementation = RedisCache.class)
public interface FavoriteTableMapper {

    @Select("SELECT count(*) FROM favorite_table WHERE p_id = #{pid} and u_id = #{uid}")
    Integer isFavorite(Integer pid,Integer uid);

    @Insert("INSERT INTO favorite_table (p_id,u_id) VALUES(#{pid},#{uid})")
    Integer collectPost(Integer pid,Integer uid);

    @Delete("DELETE FROM favorite_table WHERE p_id = #{pid} and u_id = #{uid}")
    Integer deleteCollectPost(Integer pid,Integer uid);

    @Delete("DELETE FROM favorite_table WHERE p_id = #{pid}")
    Integer deleteCollectPostByPid(Integer pid);

    @Select("SELECT * FROM favorite_table WHERE p_id = #{pid}")
    ArrayList<Favorite_Table> getAllFavoriteTableByP_id(Integer pid);

    @Select("SELECT * FROM favorite_table WHERE u_id = #{uid}")
    ArrayList<Favorite_Table> getAllFavoriteTableByU_id(Integer uid);

    @Select("SELECT * FROM favorite_table WHERE u_id = #{uid} AND create_time < #{after}")
    ArrayList<Favorite_Table> getAllFavoriteTableByU_idAfter(Integer uid, Timestamp after);

}
