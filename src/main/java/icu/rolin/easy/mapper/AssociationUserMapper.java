package icu.rolin.easy.mapper;

import icu.rolin.easy.model.DO.Association_User;
import org.apache.ibatis.annotations.*;
import org.mybatis.caches.redis.RedisCache;

import java.util.ArrayList;

/**
 * E
 */
@Mapper
@CacheNamespace(implementation = RedisCache.class)
public interface AssociationUserMapper {

    @Select("SELECT * FROM association_user WHERE a_id = #{aid}")
    ArrayList<Association_User> findAllMembersByAID(Integer aid);

    @Select("SELECT * FROM association_user")
    ArrayList<Association_User> getAllAssociation_users();

    @Select("SELECT * FROM association_user WHERE u_id = #{uid} AND a_id = #{aid}")
    Association_User findAssociation_userById(Integer uid,Integer aid);

    // 用户进入了啥子社团
    @Select("SELECT * FROM association_user WHERE u_id = #{uid}")
    ArrayList<Association_User> getAllUserJoinAssociations(Integer uid);

    /**
     * 查询某个用户是否加入了某个社团
     * @param aid 社团ID
     * @param uid 用户ID
     * @return 返回一个整数，0代表未加入，1代表已加入，大于1可能表示数据库有问题
     */
    @Select("SELECT COUNT(*) FROM association_user WHERE u_id = #{uid} AND a_id = #{aid}")
    Integer getUserIsJoinAssociation(Integer aid,Integer uid);


    /**
     * 查询一个用户是否是某社团的一个管理员，如果是则返回1，否返回0，如果不存在则返回null
     * @param aid 社团ID
     * @param uid 用户ID
     * @return 返回一个整数
     */
    @Select("SELECT COUNT(*) FROM association_user WHERE u_id = #{uid} AND a_id = #{aid} AND is_admin = 1")
    Integer findUserIsAdminByUidAid(Integer aid,Integer uid);


    @Select("SELECT COUNT(u_id) FROM association_user WHERE a_id = #{aid}")
    Integer getTheAssociationMembers(Integer aid);

    @Delete("DELETE FROM association_user WHERE a_id = #{aid} AND u_id = #{uid}")
    Integer deleteUserByAidUid(Integer aid, Integer uid);

    @Select("SELECT * FROM association_user WHERE a_id = #{aid} ORDER BY create_time DESC LIMIT 1")
    Association_User findByAidDescTime(Integer aid);

    @Insert("INSERT INTO association_user (a_id,u_id,is_admin) VALUES (#{aid},#{uid},0)")
    Integer insertUserToAss(Integer aid,Integer uid);


}
