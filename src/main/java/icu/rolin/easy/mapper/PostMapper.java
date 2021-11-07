package icu.rolin.easy.mapper;

import icu.rolin.easy.model.DO.Post;
import org.apache.ibatis.annotations.CacheNamespace;
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

}
