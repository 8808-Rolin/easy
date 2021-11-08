package icu.rolin.easy.mapper;

import icu.rolin.easy.model.DO.Post;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mybatis.caches.redis.RedisCache;

import java.util.ArrayList;

@Mapper
@CacheNamespace(implementation = RedisCache.class)
public interface PostMapper {

    @Select("SELECT * FROM post")
    ArrayList<Post> getAllPosts();

    @Select("SELECT * FROM post WHERE post_type = #{postType}")
    ArrayList<Post> findPostByPostType(Integer postType);

    @Select("SELECT * FROM post WHERE a_id = #{aid} and post_type = #{postType}")
    ArrayList<Post> findPostsByA_id(Integer aid,Integer postType);

    @Insert("INSERT INTO post (a_id,u_id,title,content_id,tags,post_type) VALUES(#{a_id},#{u_id},#{title},#{content_id},#{tags},#{post_type})")
    Integer insertPost(Integer a_id, Integer u_id, String title, Integer content_id, String tags, Integer post_type);

    @Select("SELECT max(id) FROM post")
    Integer getTheLatestId();

}
