package icu.rolin.easy.mapper;

import icu.rolin.easy.model.DO.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user")
    ArrayList<User> findAll();

    @Select("SELECT * FROM user WHERE id = #{id}")
    User findById(Integer id);

    @Select("SELECT count(*) FROM user WHERE student_number = #{account} and password = #{password}")
    Integer varifyLoginByStudent_number(String account,String password);

    @Select("SELECT count(*) FROM user WHERE phone = #{account} and password = #{password}")
    Integer varifyLoginByPhone_number(String account,String password);

    @Insert("INSERT INTO user (realname,username,student_number,college_id,password,email,phone,sex,birth,headImage) VALUES (#{realname},#{username},#{student_number},#{college_id},#{password},#{email},#{phone},#{sex},#{birth},#{headImage})")
    Integer insertUser(String realname,String username,String student_number,Integer college_id,String password,String email,String phone,Integer sex,String birth,String headImage);

    @Update("UPDATE user SET password = #{password} WHERE student_number = #{student_number} AND phone = #{phone} AND email = #{email}")
    Integer updatePassword(String password,String student_number,String phone,String email);

    @Select("SELECT count(studen_number,phone) FROM user WHERE student_number = #{student_number} or phone = #{phone}")
    Integer varifyAccount(String student_number,String phone);

    @Select("SELECT count(student_number) FROM user WHERE student_number = #{student_number}")
    Integer varifyStudent_number(String student_number);

    @Select("SELECT count(phone) FROM user WHERE phone = #{phone}")
    Integer varifyPhone(String phone);

}
