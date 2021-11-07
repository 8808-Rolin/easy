package icu.rolin.easy.mapper;

import icu.rolin.easy.model.DO.Association_User;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mybatis.caches.redis.RedisCache;

import java.util.ArrayList;

@Mapper
@CacheNamespace(implementation = RedisCache.class)
public interface AssociationUserMapper {

    @Select("SELECT * FROM association_user WHERE id = #{aid}")
    public ArrayList<Association_User> findAllMembersByAID(Integer aid);

}
