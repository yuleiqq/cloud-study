package com.example.oauth2server.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


/**
 *  重写登录使用的filter， 兼容JSON 格式的请求
 */
public class MyAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported" + request.getMethod());
        }
        //说明是以json的形式传递参数
        if (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
            String username = null;
            String password = null;
            //将传入的json数据转换成map再通过get("key")获得
            try {
                Map<String, String> map = new ObjectMapper().readValue(request.getInputStream(),
                        Map.class);
                username = map.get("userName");
                password = map.get("passWord");
            } catch (IOException e) {
                e.printStackTrace();
            }

            //注意必须是AuthenticationException异常，不然框架无法监测到.
            if (username == null) {
                throw new AuthenticationServiceException("用户名不能为空!");

            }
            if (password == null) {
                throw new AuthenticationServiceException("密码不能为空!");

            }
            username = username.trim();
            UsernamePasswordAuthenticationToken authRequest =
                    new UsernamePasswordAuthenticationToken(username, password);
            setDetails(request, authRequest);

            return this.getAuthenticationManager().authenticate(authRequest);

        }
        else {
            //如果不是json格式调用框架本身
            return super.attemptAuthentication(request, response);
        }

    }
}