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
    @Select("SELECT is_admin FROM association_user WHERE u_id = #{uid} AND a_id = #{aid}")
    Integer findUserIsAdminByUidAid(Integer aid,Integer uid);


}
