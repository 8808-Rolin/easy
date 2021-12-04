package icu.rolin.easy.mapper;

import icu.rolin.easy.model.DO.Apply_Commond;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mybatis.caches.redis.RedisCache;

import java.util.ArrayList;

@Mapper
@CacheNamespace(implementation = RedisCache.class)
public interface ApplyCommondMapper {

    @Select("SELECT * FROM apply_commond WHERE a_id = #{aid}")
    ArrayList<Apply_Commond> getAssociationApplyList(Integer aid);

    @Insert("INSERT INTO apply_commond (a_id, title, content_id) VALUES(#{aid}, #{title},#{contentid})")
    Integer insertAssApply(Integer aid, String title, Integer contentid);

    @Select("SELECT max(id) FROM apply_commond")
    Integer getTheLatestID();


}
