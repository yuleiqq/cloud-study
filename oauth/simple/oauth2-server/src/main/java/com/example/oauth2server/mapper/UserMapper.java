package com.example.oauth2server.mapper;

import com.example.oauth2server.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @author: yulei
 * @create: 2022-12-02
 * @Version 1.0
 **/

@Component
public interface UserMapper {

    @Select("select id, username ,password ,enabled ,roles from users where username = #{ username } ")
    User findByUserName(@Param("username") String username);

}
