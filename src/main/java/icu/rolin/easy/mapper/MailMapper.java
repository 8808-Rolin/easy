package icu.rolin.easy.mapper;

import icu.rolin.easy.model.DO.Mail;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.caches.redis.RedisCache;

@Mapper
public interface MailMapper {

    @Insert("INSERT INTO mail (from_id,to_id,title,content,is_system,mail_type) VALUES (#{from_id}, #{to_id}, #{title}, #{content},#{is_system},#{mail_type})")
    Integer insertMail(Integer from_id, Integer to_id, String title, String content, Integer is_system, Integer mail_type);

}
