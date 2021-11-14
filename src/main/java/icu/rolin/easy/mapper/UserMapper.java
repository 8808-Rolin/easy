package icu.rolin.easy.mapper;

import icu.rolin.easy.model.DO.User;
import org.apache.ibatis.annotations.*;
import org.mybatis.caches.redis.RedisCache;

import java.util.ArrayList;

@Mapper
@CacheNamespace(implementation = RedisCache.class)
public interface UserMapper {

    @Select("SELECT * FROM user")
    ArrayList<User> findAll();

    @Select("SELECT id,username,realname,student_number FROM user WHERE id = #{id}")
    User findSomeById(Integer id);

    @Select("SELECT * FROM user WHERE id = #{id}")
    User findById(Integer id);

    @Select("SELECT username FROM user WHERE id = #{id}")
    String getNameById(Integer id);

    @Select("SELECT level FROM user WHERE id = #{id}")
    Integer getLevelById(Integer id);

    @Select("SELECT count(*) FROM user WHERE student_number = #{account} and password = #{password}")
    Integer verifyLoginByStudent_number(String account, String password);

    @Select("SELECT count(*) FROM user WHERE phone = #{account} and password = #{password}")
    Integer verifyLoginByPhone_number(String account, String password);

    @Select("SELECT id FROM user WHERE student_number = #{student_number}")
    Integer findIdByNumber(String student_number);

    @Select("SELECT id FROM user WHERE phone = #{phone}")
    Integer findIdByPhone(String phone);

    @Insert("INSERT INTO user (realname,username,student_number,college_id,password,email,phone,sex,birth,user_avatar) VALUES (#{realname},#{username},#{student_number},#{college_id},#{password},#{email},#{phone},#{sex},#{birth},#{headImage})")
    Integer insertUser(String realname,String username,String student_number,Integer college_id,String password,String email,String phone,Integer sex,String birth,String headImage);

    @Update("UPDATE user SET password = #{password} WHERE student_number = #{student_number} AND phone = #{phone} AND email = #{email}")
    Integer updatePassword(String password,String student_number,String phone,String email);

    @Select("SELECT count(*) FROM user WHERE student_number = #{student_number} or phone = #{phone}")
    Integer verifyAccount(String student_number, String phone);

    @Select("SELECT count(student_number) FROM user WHERE student_number = #{student_number}")
    Integer verifyStudent_number(String student_number);

    @Select("SELECT count(phone) FROM user WHERE phone = #{phone}")
    Integer verifyPhone(String phone);

    @Select("SELECT level FROM user WHERE id = #{id}")
    Integer findLevelById(Integer id);

    @Select("SELECT is_open_zone FROM user WHERE id = #{id}")
    Integer isOpenZone(Integer id);

    @Update("UPDATE user SET username = #{username} WHERE id = #{id}")
    Integer updateUserName(String username,Integer id);

    @Update("UPDATE user SET intro = #{intro} WHERE id = #{id}")
    Integer updateUserIntro(String intro,Integer id);

    @Update("UPDATE user SET user_avatar = #{user_avatar} WHERE id = #{id}")
    Integer updateUserHeadImage(String user_avatar,Integer id);

    @Update("UPDATE user SET email = #{email} WHERE id = #{id}")
    Integer updateUserEmail(String email,Integer id);

    @Update("UPDATE user SET birth = #{birth} WHERE id = #{id}")
    Integer updateUserBirth(String birth,Integer id);

}
