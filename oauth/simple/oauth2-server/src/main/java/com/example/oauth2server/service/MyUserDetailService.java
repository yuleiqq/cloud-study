package com.example.oauth2server.service;

import com.example.oauth2server.mapper.UserMapper;
import com.example.oauth2server.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author: yulei
 * @create: 2022-12-02
 * @Version 1.0
 **/

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //从数据库尝试读取该用户
        User user = userMapper.findByUserName(username);
        //用户不存在，抛出异常
        if(user == null){
            throw new UsernameNotFoundException("用户不存在!");
        }
        //将数据库形式的roles  解析为UserDetails 的权限集

        user.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRoles()));


        return user;
    }
}
