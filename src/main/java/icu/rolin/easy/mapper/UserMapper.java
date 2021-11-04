package icu.rolin.easy.mapper;

import icu.rolin.easy.model.DO.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
}
