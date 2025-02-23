package org.kwok.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @description:
 * @author: Kwok
 * @date: 2025/2/23
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // 允许访问静态资源和登录页面
                .antMatchers("/login", "/assets/**", "/monitor/**", "/instances/**").permitAll()
                // 其他请求需要认证
                .anyRequest().authenticated()
                .and()
                .formLogin()
                // 自定义登录页面（可选）
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                // 禁用 CSRF 保护（仅在必要时禁用）
                .csrf().disable();

    }

}
