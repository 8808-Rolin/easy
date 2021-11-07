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

    //获取UID和名字数据的社团信息
    @Select("SELECT id,name FROM association")
    public ArrayList<Association> getAssOverview();

}
