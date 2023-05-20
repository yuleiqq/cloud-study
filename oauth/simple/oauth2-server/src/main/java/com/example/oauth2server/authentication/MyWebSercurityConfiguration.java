package com.example.oauth2server.authentication;

import com.example.oauth2server.filter.MyAuthenticationFilter;
import com.example.oauth2server.service.MyUserDetailService;
import com.example.oauth2server.utils.ResponeData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
@EnableWebSecurity(debug = true)
public class MyWebSercurityConfiguration  extends WebSecurityConfigurerAdapter {


    @Autowired
    MyUserDetailService userDetailService;

    /**
     * 密码没有加密
     * @return
     */
    @Bean
    PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) {

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        auth.authenticationProvider(daoAuthenticationProvider);

    }

//
//    /**
//     * 基于内存的用户定义
//     * @return
//     */
//    @Bean
//   public UserDetailsService userDetailsService(){
////        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
////        manager.createUser(User.withUsername("user").password("123").roles("USER").build());
//
//          JdbcUserDetailsManager manager = new JdbcUserDetailsManager();
//
//          manager.setDataSource(dataSource);
//
//        return manager;
//    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        // 禁用session机制
//        http.csrf().disable()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

            http.authorizeRequests().anyRequest()
                    .authenticated()
                    .and()
                    .formLogin()
                    .loginPage("http://127.0.0.1:5173/login")//登录页配置
                    .loginProcessingUrl("/doLogin") //登录处理的url
                    .permitAll()

                    .and().csrf().disable().cors();;

        //过滤器加在 UsernamePasswordAuthenticationFilter  前面
        http.addFilterAt( myAuthenticationFilter() , UsernamePasswordAuthenticationFilter.class);

    }

    /**
     * 配置不需要登录校验的接口或者页面
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {

        web.ignoring().antMatchers("/api/hello/**");

    }

    /**
     * 重写过滤器之后，需要定义其中的返回和校验逻辑
     * @return
     * @throws Exception
     */
    @Bean
    MyAuthenticationFilter myAuthenticationFilter() throws Exception{

        MyAuthenticationFilter filter = new MyAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManagerBean());
        filter.setFilterProcessesUrl("/api/doLogin");
        filter.setAuthenticationFailureHandler(new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                response.setContentType("application/json;charset=utf-8");

                ResponeData responeData = ResponeData.buildError("登录异常:"+ exception.getMessage());
                PrintWriter out = response.getWriter();
                out.write(String.valueOf(responeData));

            }
        });
        filter.setAuthenticationSuccessHandler(new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                HttpSession session = request.getSession();
                session.setAttribute("userName",authentication.getName());
                response.setContentType("application/json;charset=utf-8");

                ResponeData responeData = ResponeData.buildSuccess("登录成功!");
                PrintWriter out = response.getWriter();
                out.write(String.valueOf(responeData));
            }
        });
        return filter;

    }


    //认证管理器,oauth2需要，放入容器
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
