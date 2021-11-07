package icu.rolin.easy.mapper;

import icu.rolin.easy.model.DO.Mail;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mybatis.caches.redis.RedisCache;

@Mapper
public interface MailMapper {

    @Insert("INSERT INTO mail (from_id,to_id,title,content,is_system,mail_type) VALUES (#{from_id}, #{to_id}, #{title}, #{content},0,#{mail_type})")
    Integer insertMailWithUser(Integer from_id, Integer to_id, String title, String content,  Integer mail_type);

    @Insert("INSERT INTO mail (to_id,title,content,is_system,mail_type) VALUES ( #{to_id}, #{title}, #{content},1,#{mail_type})")
    Integer insertMailWithSystem(Integer to_id, String title, String content, Integer mail_type);

    @Select("SELECT MAX(id) FROM mail")
    Integer getMaxMid();

}
