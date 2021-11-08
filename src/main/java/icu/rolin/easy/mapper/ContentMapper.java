package icu.rolin.easy.mapper;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mybatis.caches.redis.RedisCache;

@Mapper
@CacheNamespace(implementation = RedisCache.class)
public interface ContentMapper {
    @Insert("INSERT INTO content (content) VALUES(#{content})")
    Integer insertPost_content(String content);

    @Select("SELECT max(id) FROM content")
    Integer getTheLatestID();
}
