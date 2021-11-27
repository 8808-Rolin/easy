package icu.rolin.easy.mapper;

import icu.rolin.easy.model.DO.Mail;
import org.apache.ibatis.annotations.*;
import org.mybatis.caches.redis.RedisCache;

import java.util.ArrayList;

@Mapper
public interface MailMapper {

    @Insert("INSERT INTO mail (from_id,to_id,title,content,is_system,mail_type) VALUES (#{from_id}, #{to_id}, #{title}, #{content},0,#{mail_type})")
    Integer insertMailWithUser(Integer from_id, Integer to_id, String title, String content,  Integer mail_type);

    @Insert("INSERT INTO mail (to_id,title,content,is_system,mail_type) VALUES ( #{to_id}, #{title}, #{content},1,#{mail_type})")
    Integer insertMailWithSystem(Integer to_id, String title, String content, Integer mail_type);

    @Select("SELECT MAX(id) FROM mail")
    Integer getMaxMid();

    @Update("UPDATE mail SET is_read = 2  WHERE to_id = #{uid}")
    Integer emptyMailById(Integer uid);

    @Select("SELECT * FROM mail WHERE to_id = #{toid} AND (is_read = 0 OR is_read = 1)")
    ArrayList<Mail> getMailsByTo_id(Integer toid);

    @Select("SELECT * FROM mail WHERE to_id = #{toid} AND (is_read = 0 OR is_read = 1) AND mail_type = 1")
    ArrayList<Mail> getAssMailsByTo_id(Integer toid);

    @Select("SELECT * FROM mail WHERE from_id = #{fromid} AND (is_read = 0 OR is_read = 1)")
    ArrayList<Mail> getMailsByFrom_id(Integer fromid);

    @Select("SELECT * FROM mail WHERE id = #{id}")
    Mail getContentById(Integer id);

    @Update("UPDATE mail SET is_read = 1 WHERE id =#{id}")
    Integer setMailRead(Integer id);

}
