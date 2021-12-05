package icu.rolin.easy.mapper;

import icu.rolin.easy.model.DO.Association;
import org.apache.ibatis.annotations.*;
import org.mybatis.caches.redis.RedisCache;

import java.util.ArrayList;

/**
 *
 */
@Mapper
@CacheNamespace(implementation = RedisCache.class)
public interface AssociationMapper {

    @Select("SELECT id,name FROM association")
    ArrayList<Association> getAssOverview();

    //获取一个社团是否存在存在返回 1 不存在返回0
    @Select("SELECT COUNT(*) FROM association WHERE id=#{id}")
    Integer findAssIsExist(Integer id);

    //获取所有的社团信息
    @Select("SELECT * FROM association")
    ArrayList<Association> findAllAssociation();

    //根据社团id获取当前社团的所有信息
    @Select("SELECT * FROM association WHERE id = #{id}")
    Association findAssociationById(Integer id);



    @Select("SELECT name FROM association WHERE id = #{id}")
    String getAssociationNameById(Integer id);

    @Select("SELECT logo FROM association WHERE id = #{id}")
    String getAssociationLogoById(Integer id);


    // 判断此人是不是社团老大
    @Select("SELECT COUNT(*) FROM association WHERE id = #{id} AND leader_id = #{leaderid}")
    Integer verifyMemberGradge(Integer id, Integer leaderid);

    @Update("UPDATE association SET name = #{name} WHERE id = #{aid}")
    Integer updateNameById(String name,Integer aid);

    @Update("UPDATE association SET intro = #{name} WHERE id = #{aid}")
    Integer updateIntroById(String name,Integer aid);

    @Update("UPDATE association SET logo = #{name} WHERE id = #{aid}")
    Integer updateLogoById(String name,Integer aid);

    @Update("UPDATE association SET leader_id = #{uid} WHERE id = #{aid}")
    Integer updateLeaderById(Integer uid,Integer aid);

    @Insert("INSERT INTO association (leader_id,name,logo,intro,parent_organization) VALUES (#{uid},#{assName},#{logo},#{intro},#{po})")
    Integer insertByAll(Integer uid,String assName,String logo,String intro,String po);

    @Select("SELECT MAX(id) FROM association WHERE leader_id = #{leader}")
    Integer getMaxIDByLeader(Integer leader);

}
