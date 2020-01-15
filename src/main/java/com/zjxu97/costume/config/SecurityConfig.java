package com.zjxu97.costume.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author zjxu97
 * @date 2020/1/16 00:25
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //使用内存用户储存
        auth.userDetailsService(userDetailsService).passwordEncoder(new LocalPasswordEncoder());
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().permitAll();
    }
    /*
    // 全局认证：
    http.authorizeRequests().anyRequest().authenticated()
                .and().formLogin().and().httpBasic();
     */


    /**
     * 存储的密码本身永远不会被解码。
     */
    static class LocalPasswordEncoder implements PasswordEncoder {
        /**
         * @param password 对提交的密码进行加密
         * @return String
         */
        @Override
        public String encode(CharSequence password) {
            return "25d55ad283aa400af464c76d713c07ad";
        }

        /**
         * @param rawPassword     人为输入的密码
         * @param encodedPassword 被加密的的数据库中真正的密码
         * @return boolean
         */
        @Override
        public boolean matches(CharSequence rawPassword, String encodedPassword) {
            String rawPasswordString = this.encode(rawPassword);
            return rawPasswordString.equals(encodedPassword);
        }
    }
}
