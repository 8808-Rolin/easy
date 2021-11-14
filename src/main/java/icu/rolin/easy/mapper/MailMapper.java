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

    @Delete("DELETE FROM mail WHERE from_id = #{fromid}")
    Integer deleleMailById(Integer fromid);

    @Select("SELECT * FROM mail WHERE to_id = #{toid}")
    ArrayList<Mail> getMailsByTo_id(Integer toid);

    @Select("SELECT content FROM mail WHERE id = #{id}")
    String getContentById(Integer id);

}
