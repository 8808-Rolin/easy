package icu.rolin.easy.mapper;

import icu.rolin.easy.model.DO.Comments;
import org.apache.ibatis.annotations.*;
import org.mybatis.caches.redis.RedisCache;

import java.sql.Timestamp;
import java.util.ArrayList;

@Mapper
@CacheNamespace(implementation = RedisCache.class)
public interface CommentsMapper {
    //获取回复数量
    @Select("SELECT count(*) FROM comments WHERE p_id = #{pid}")
    Integer countCommentsByPid(Integer pid);

    @Select("SELECT max(create_time) FROM comments")
    Timestamp getTheLatestComment();

    //通过PID查询最后发布的回复
    @Select("SELECT u_id,create_time FROM comments WHERE p_id = #{pid} ORDER BY create_time DESC LIMIT 1")
    Comments getLastCommentWithPost(Integer pid);

    @Insert("INSERT INTO comments (p_id,u_id,content) VALUES(#{pid},#{uid},#{content})")
    Integer addComment(Integer pid,Integer uid,String content);

    @Delete("DELETE FROM comments WHERE id = #{id}")
    Integer deleteCommentById(Integer id);

}
