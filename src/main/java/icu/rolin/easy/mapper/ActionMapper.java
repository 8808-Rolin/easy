package icu.rolin.easy.mapper;

import icu.rolin.easy.model.DO.Action;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mybatis.caches.redis.RedisCache;

import java.util.ArrayList;

@Mapper
@CacheNamespace(implementation = RedisCache.class)
public interface ActionMapper {

    @Select("SELECT * FROM action WHERE a_id = #{aid}")
    ArrayList<Action> findByA_id(Integer aid);

    @Select("SELECT * FROM action WHERE id = #{id}")
    Action getDetailedAssActionByAcId(Integer id);

    @Select("SELECT COUNT(*) FROM action WHERE a_id = #{aid} AND DATE(start_time) >= #{currentTime}")
    Integer getToBeHeldActionsNumber(Integer aid,String currentTime);

    @Insert("INSERT INTO action (a_id, title, content_id, position, start_time, end_time) VALUES(#{aid}, #{title}, #{contentid}, #{position}, #{startTime}, #{endTime})")
    Integer releaseAction(Integer aid,String title,Integer contentid,String position,String startTime,String endTime);

    @Select("SELECT MAX(id) FROM action")
    Integer getTheLatestId();

    @Select("SELECT * FROM action WHERE a_id=#{aid} AND start_time > NOW() ORDER BY start_time DESC LIMIT 1")
    Action findByAidNext(Integer aid);



}
