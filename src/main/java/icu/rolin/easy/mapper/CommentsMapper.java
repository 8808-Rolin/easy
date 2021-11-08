package icu.rolin.easy.mapper;

import icu.rolin.easy.model.DO.Comments;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mybatis.caches.redis.RedisCache;

import java.sql.Timestamp;
import java.util.ArrayList;

@Mapper
@CacheNamespace(implementation = RedisCache.class)
public interface CommentsMapper {

    @Select("SELECT count(*) FROM comments WHERE p_id = #{pid}")
    Integer getCommentQuantitesByP_id(Integer pid);

    @Select("SELECT max(create_time) FROM comments")
    Timestamp getTheLatestComment();

}
