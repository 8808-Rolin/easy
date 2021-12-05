package icu.rolin.easy.mapper;

import icu.rolin.easy.model.DO.Apply_Create;
import org.apache.ibatis.annotations.*;
import org.mybatis.caches.redis.RedisCache;

import java.util.ArrayList;

@Mapper
@CacheNamespace(implementation = RedisCache.class)
public interface ApplyCreateMapper {

    @Insert("INSERT INTO apply_create (u_id, name, logo, intro, parent_organization, note, is_approved) VALUES(#{u_id}, #{name}, #{logo}, #{intro}, #{parent_organization}, #{note}, 0)")
    Integer insertCreatAssociationForm(Apply_Create apply_create);

    @Select("SELECT max(id) from apply_create")
    Integer getLastInsertId();

    @Select("SELECT * from apply_create")
    ArrayList<Apply_Create> findAll();

    @Select("SELECT * from apply_create WHERE id = #{id}")
    Apply_Create findById(Integer id);

    @Update("UPDATE apply_create SET is_approved = #{status} WHERE id = #{id}")
    Integer updateApprovedById(Integer id,Integer status);



}
