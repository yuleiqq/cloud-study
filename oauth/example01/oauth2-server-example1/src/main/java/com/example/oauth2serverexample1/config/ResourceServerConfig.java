package com.example.oauth2serverexample1.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;


/**
 * 资源服务器的职责 是对来自 OAuth 客户端的access_token进行鉴权。 一个资源服务器包含多个端点 （接口） ，
 * 一部分端点作为资源服务器的资源提供给OAuth的 client访问，另一部分端点不由资源服务器管理。由资源服务器管理的端点安全性配置在此类中，
 * 其余端点的安全性配置在 SecurityConfiguration 类中。 当请求中包含OAuth2 access_token 中 , Spring Security 会根据资源服务器
 * 配置进行过滤。 EnableResourceServer 会创建一个 WebSecurityConfigurerAdapter ,执行顺序（Order ） 是3 。
 * 在 SecurityConfiguration 类之前运行，优先级更高
 *
 */


@Profile("simple")
@EnableResourceServer
@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

 private static  final    Logger logger = LoggerFactory.getLogger(ResourceServerConfig.class);


    public static final String RESOURCE_ID = "authorizationserver";


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        super.configure(resources);
        resources.resourceId(RESOURCE_ID);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        logger.info("ResourceServerConfig 中配置HttpSecurity 对象执行 ");

        //只有 /me 端点作为资源服务器的资源
        http.requestMatchers().antMatchers("/me","/phone")
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated();

    }
}
