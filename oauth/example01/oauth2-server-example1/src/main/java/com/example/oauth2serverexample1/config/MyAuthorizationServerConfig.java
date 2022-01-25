package com.example.oauth2serverexample1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;


@Profile("simple")
@Configuration
@EnableAuthorizationServer
public class MyAuthorizationServerConfig  extends AuthorizationServerConfigurerAdapter {

    @Autowired
    PasswordEncoder passwordEncoder;


    /**
     * 该处通过配置ClientDetailsService 来配置注册到该授权服务器的客户端clients信息。
     * 注意： 除非在下面的config(AuthorizationServerEndpointsConfigurer) 中指定了一个
     *  AuthenticationManager ，否则密码授权模式不可用
     *
     *  至少要配一个client  ，否则无法启动服务器
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        clients.inMemory()
                .withClient("client-for-server")
                .secret(passwordEncoder.encode("client-for-server"))
                .authorizedGrantTypes("authorization_code","implicit")
                .accessTokenValiditySeconds(7200)
                .refreshTokenValiditySeconds(72000)
                //重定向URL
                .redirectUris("http://localhost:8080/login/oauth2/code/authorizationserver")
                .additionalInformation()
                //该client 可以访问的资源服务器ID，每个资源服务器都有一个ID
                .resourceIds(ResourceServerConfig.RESOURCE_ID)
                //该client 拥有的权限，资源服务器可以依据该处定义的权限
                //对client 进行鉴权
                .authorities("ROLE_CLIENT")
                //该client 可以访问的资源的范围，资源服务器可以依据该处定义的范围对client进行鉴权
                .scopes("profile","email")
                //自动批准的范围（scope），自动批准的scope在批准页不需要显示，即 不需要用户确认批准。如果所有scope都
                //自动批准，则不显示批准页
                .autoApprove("profile");


    }

    /**
     * 配置授权服务器的安全，意味着 /oauth/token 端点  和  /oauth/authorize 端点都应该是安全的
     * 默认的设置覆盖了绝大多数需求，所以一般情况下不需要做任何事情
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        super.configure(security);
    }


    /**
     * 该方法是用来配置授权服务器特性的(Authorization Server endpoints) ,主要是一些非安全的特性，
     * 比如token存储，token 自定义、授权模式等
     * 默认不需要任何配置，如果需要密码授权，则需要提供一个AuthenticationManger
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        super.configure(endpoints);
    }
}



