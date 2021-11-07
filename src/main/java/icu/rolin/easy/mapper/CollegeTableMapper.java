package icu.rolin.easy.mapper;

import icu.rolin.easy.model.DO.College_Table;
import icu.rolin.easy.model.DO.User;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mybatis.caches.redis.RedisCache;

import java.util.ArrayList;

@Mapper
@CacheNamespace(implementation = RedisCache.class)
public interface CollegeTableMapper {

    // 通过学院ID查询到学院信息
    @Select("SELECT * FROM college_table WHERE id = #{id}")
    College_Table getCollegeById(Integer id);

    //查询所有的学院
    ArrayList<College_Table> getCollege();

}
