package icu.rolin.easy.mapper;

import icu.rolin.easy.model.DO.Join_Action;
import org.apache.ibatis.annotations.*;
import org.mybatis.caches.redis.RedisCache;

import java.util.ArrayList;

@Mapper
@CacheNamespace(implementation = RedisCache.class)
public interface JoinActionMapper {

    @Insert("INSERT INTO join_action (act_id,u_id) VALUES(#{acid},#{uid})")
    Integer insertJoinActionForm(Integer acid,Integer uid);

    @Select("SELECT COUNT(u_id) FROM join_action WHERE act_id = #{acid} and u_id = #{uid}")
    Integer verifyUserJoinActionById(Integer acid,Integer uid);

    @Select("SELECT * FROM join_action WHERE act_id = #{acid}")
    ArrayList<Join_Action> getActionPersonNumber(Integer acid);

    @Select("SELECT * FROM join_action WHERE u_id = #{uid}")
    ArrayList<Join_Action> findByUid(Integer uid);

    @Delete("DELETE FROM join_action WHERE u_id = #{uid} AND act_id = #{acid}")
    Integer removeUser(Integer acid , Integer uid);

}
