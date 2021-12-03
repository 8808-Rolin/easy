package icu.rolin.easy.mapper;

import icu.rolin.easy.model.DO.Apply_Join_Association;
import org.apache.ibatis.annotations.*;
import org.mybatis.caches.redis.RedisCache;

import java.util.ArrayList;

@Mapper
@CacheNamespace(implementation = RedisCache.class)
public interface ApplyJoinAssociationMapper {

    @Insert("INSERT INTO apply_join_association (u_id,a_id,note,is_approved) VALUES(#{uid},#{aid},#{note},0)")
    Integer insertJoinAssForm(Integer uid,Integer aid,String note);

    @Select("SELECT * FROM apply_join_association WHERE a_id = #{aid}")
    ArrayList<Apply_Join_Association> getApplyJoinList(Integer aid);

    @Update("UPDATE apply_join_association SET is_approved = #{type} WHERE id = #{id}")
    Integer setJoinApplyStatus(Integer type,Integer id);

    @Select("SELECT * FROM apply_join_association WHERE id = #{id}")
    Apply_Join_Association findByID(Integer id);

    @Select("SELECT COUNT(*) FROM apply_join_association WHERE a_id = #{aid} AND u_id = #{uid} AND is_approved != 0")
    Integer countApproveNumberByIDID(Integer aid,Integer uid);

}
