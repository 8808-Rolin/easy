package icu.rolin.easy.mapper;

import icu.rolin.easy.model.DO.Post;
import org.apache.ibatis.annotations.*;
import org.mybatis.caches.redis.RedisCache;

import java.util.ArrayList;

@Mapper
@CacheNamespace(implementation = RedisCache.class)
public interface PostMapper {

    @Select("SELECT * FROM post")
    ArrayList<Post> getAllPosts();

    @Select("SELECT * FROM post WHERE id = #{id}")
    Post findPostById(Integer id);

    @Select("SELECT * FROM post WHERE post_type = #{postType}")
    ArrayList<Post> findPostByPostType(Integer postType);

    @Select("SELECT * FROM post WHERE post_type = #{postType} AND a_id = 0")
    ArrayList<Post> findPostByPostTypeWithIndex(Integer postType);

    @Select("SELECT * FROM post WHERE a_id = #{aid} and post_type = #{post_type}")
    ArrayList<Post> findPostsByAidType(Integer aid,Integer post_type);

    @Select("SELECT * FROM post WHERE a_id = #{aid} and post_type != #{post_type} ")
    ArrayList<Post> findPostsByAidExType(Integer aid,Integer post_type);

    @Insert("INSERT INTO post (a_id,u_id,title,content_id,tags,post_type) VALUES(#{a_id},#{u_id},#{title},#{content_id},#{tags},#{post_type})")
    Integer savePost(Integer a_id, Integer u_id, String title, Integer content_id, String tags, Integer post_type);

    @Select("SELECT max(id) FROM post")
    Integer getTheLatestId();

    @Delete("DELETE FROM post WHERE id = #{id}")
    Integer deletePostById(Integer id);


    @Select("SELECT * FROM post WHERE u_id = #{id}")
    ArrayList<Post> getPostsByU_id(Integer id);

    @Select("SELECT content_id FROM post WHERE id=#{pid}")
    Integer findContentById(Integer pid);

    @Select("SELECT COUNT(*) FROM post WHERE a_id = #{aid}")
    Integer getTheAssociationPostNumber(Integer aid);

    @Select("SELECT COUNT(*) FROM post WHERE a_id = #{aid} AND u_id = #{uid}")
    Integer findMatchWithPidUid(Integer uid,Integer aid);

}
