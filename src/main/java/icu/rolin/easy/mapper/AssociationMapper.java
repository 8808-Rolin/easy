package icu.rolin.easy.mapper;

import icu.rolin.easy.model.DO.Association;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mybatis.caches.redis.RedisCache;

import java.util.ArrayList;

@Mapper
@CacheNamespace(implementation = RedisCache.class)
public interface AssociationMapper {

    @Select("SELECT * FROM association")
    ArrayList<Association> getAllAssociation();

    @Select("SELECT * FROM association WHERE id = #{id}")
    ArrayList<Association> findAllAssociationsById(Integer id);

}
