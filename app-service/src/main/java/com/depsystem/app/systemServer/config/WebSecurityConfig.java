/**
 * @author JOJO
 * @class WebSecurityConfig
 * @date 2023/4/20
 * @apiNote
 */

package com.depsystem.app.systemServer.config;

import cn.hutool.json.JSONUtil;
<<<<<<< HEAD
import com.depsystem.app.systemServer.securityServer.securityFilter.AuthenticationServerImpl;
import com.depsystem.app.systemServer.securityServer.handler.AuthenticationsFailureHandler;
import com.depsystem.app.systemServer.securityServer.handler.AuthenticationsSuccessHandler;
=======
import com.depsystem.app.systemServer.securityServer.AuthenticationServerImpl;
import com.depsystem.app.systemServer.securityServer.handler.AuthenticationsFailureHandler;
import com.depsystem.app.systemServer.securityServer.handler.AuthenticationsSuccessHandler;
import com.depsystem.app.systemServer.securityServer.securityFilter.CaptchaVerifyFilter;
>>>>>>> 024d1f4 (最新版Spring boot + Vue3 + Spring Security 6 项目模板)
import com.depsystem.app.systemServer.util.ResponseResult;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
<<<<<<< HEAD
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
>>>>>>> 024d1f4 (最新版Spring boot + Vue3 + Spring Security 6 项目模板)
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
<<<<<<< HEAD
=======
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
>>>>>>> 024d1f4 (最新版Spring boot + Vue3 + Spring Security 6 项目模板)
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.swing.*;
import java.io.IOException;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Resource
    AuthenticationServerImpl authenticationServer;

<<<<<<< HEAD
=======
    @Autowired
    StringRedisTemplate stringRedisTemplate;

>>>>>>> 024d1f4 (最新版Spring boot + Vue3 + Spring Security 6 项目模板)
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        return  httpSecurity.userDetailsService(authenticationServer)
                .getSharedObject(AuthenticationManagerBuilder.class)
                .build();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(author ->
                        author
                                .requestMatchers("/captcha").permitAll()
<<<<<<< HEAD
                                .requestMatchers("/index").hasAnyRole("admin1")
                                .requestMatchers("/{name}/**").hasAnyRole("admin2","admin3")
=======
>>>>>>> 024d1f4 (最新版Spring boot + Vue3 + Spring Security 6 项目模板)
                                .anyRequest().authenticated()
                )
                .formLogin()
                .loginProcessingUrl("/api/login")
                .successHandler(new AuthenticationsSuccessHandler())
                .failureHandler(new AuthenticationsFailureHandler())
                .and()
                .logout()
                .logoutUrl("/api/logout")
                .and()
                .cors()
                .configurationSource(this.configurationSource())
                .and()
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(this::onAuthenticationFailure);
<<<<<<< HEAD
=======
        http.addFilterBefore(new CaptchaVerifyFilter(this::onAuthenticationFailure,stringRedisTemplate), UsernamePasswordAuthenticationFilter.class);
>>>>>>> 024d1f4 (最新版Spring boot + Vue3 + Spring Security 6 项目模板)
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 跨域配置
     */
    private CorsConfigurationSource configurationSource(){
        CorsConfiguration cors = new CorsConfiguration();
        cors.addAllowedOriginPattern("*");
        cors.addAllowedHeader("*");
        cors.addAllowedMethod("*");
        cors.setAllowCredentials(true);
        cors.addExposedHeader("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",cors);
        return source;
    }
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSONUtil.toJsonPrettyStr(ResponseResult.failed(401,exception.getMessage())));
    }
}
