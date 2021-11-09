package icu.rolin.easy.mapper;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.caches.redis.RedisCache;

@Mapper
@CacheNamespace(implementation = RedisCache.class)
public interface ApplyJoinAssociationMapper {

    @Insert("INSERT INTO apply_join_association (u_id,a_id,note,is_approved) VALUES(#{uid},#{aid},#{note},0)")
    Integer insertJoinAssForm(Integer uid,Integer aid,String note);

}
