package icu.rolin.easy.mapper;

import icu.rolin.easy.model.DO.Content;
import org.apache.ibatis.annotations.*;
import org.mybatis.caches.redis.RedisCache;

@Mapper
@CacheNamespace(implementation = RedisCache.class)
public interface ContentMapper {
    @Insert("INSERT INTO content (content) VALUES(#{content})")
    Integer savePost_content(String content);

    @Select("SELECT max(id) FROM content")
    Integer getTheLatestID();

    @Select("SELECT * FROM content WHERE id = #{id}")
    Content getContentByID(Integer id);


    @Delete("DELETE FROM content WHERE id = #{cid}")
    Integer deleteById(Integer cid);

    @Select("SELECT content FROM content WHERE id = #{id}")
    String findContentById(Integer id);

    @Update("UPDATE content SET content = #{content} WHERE id = ${id}")
    Integer updateContentById(Integer id,String content);


}
