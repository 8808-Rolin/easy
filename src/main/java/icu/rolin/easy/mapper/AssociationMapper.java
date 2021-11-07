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

    @Select("SELECT id,name FROM association")
    ArrayList<Association> getAssOverview();

    //获取一个社团是否存在存在返回 1 不存在返回0
    @Select("SELECT COUNT(*) FROM association WHERE id=#{id}")
    Integer findAssIsExist(Integer id);


}
