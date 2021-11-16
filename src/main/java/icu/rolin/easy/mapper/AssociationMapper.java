package icu.rolin.easy.mapper;

import icu.rolin.easy.model.DO.Association;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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

    @Update("UPDATE association SET name = #{name}, intro = #{intro}, logo = #{logo}, leader_id = #{leaderid} WHERE id = #{id}")
    Integer updateAssociationInfo(String name, String intro, String logo, Integer leaderid, Integer id);

    // 判断此人是不是社团老大
    @Select("SELECT COUNT(*) FROM association WHERE id = #{id} AND leader_id = #{leaderid}")
    Integer verifyMemberGradge(Integer id, Integer leaderid);

}
