package org.kwok.security.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @description:
 * @author: guohao
 * @date: 2022/8/7
 */
// @Configuration
public class DefaultSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/actuator/**").permitAll() //不对 springboot-admin 监控的请求进行权限校验
                .antMatchers("/instances/**").permitAll() //不对 springboot-admin 监控的实例信息请求进行权限校验
                .anyRequest().authenticated()
                .and()
                .formLogin().defaultSuccessUrl("/")
                .and().logout();
    }
}
