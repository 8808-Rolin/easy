package icu.rolin.easy.mapper;

import icu.rolin.easy.model.DO.College_Table;
import icu.rolin.easy.model.DO.User;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mybatis.caches.redis.RedisCache;

@Mapper
@CacheNamespace(implementation = RedisCache.class)
public interface CollegeTableMapper {

    @Select("SELECT * FROM college_table WHERE id = #{id}")
    College_Table getCollegeById(Integer id);

}
