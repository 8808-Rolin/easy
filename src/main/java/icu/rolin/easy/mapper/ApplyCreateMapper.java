package icu.rolin.easy.mapper;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mybatis.caches.redis.RedisCache;

@Mapper
@CacheNamespace(implementation = RedisCache.class)
public interface ApplyCreateMapper {

    @Insert("INSERT INTO apply_create (u_id, name, logo, intro, parent_organization, note, is_approved) VALUES(#{u_id}, #{name}, #{logo}, #{intro}, #{parent_organization}, #{note}, 0)")
    Integer insertCreatAssociationForm(Integer u_id,String name,String logo,String intro,Integer parent_organization,String note);

    @Select("SELECT max(id) from apply_create")
    Integer getLastInsertId();

}
