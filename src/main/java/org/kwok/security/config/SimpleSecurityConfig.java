package org.kwok.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @description:
 * @author: Kwok
 * @date: 2021/7/6
 */
@Configuration
@Profile("auth-mem")
public class SimpleSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .roles("admin")
                .password("$2a$10$EvmYfZ9xWFwmbDdMuVFqQOLuAu6q.r/c.hFXEHAh25S.bXlz.7jXS")
                .and()
                .withUser("user")
                .roles("user")
                .password("$2a$10$WRoVSVE1KUghDnJpg7ruU.DZWJlZPgXeJCXV498jjhVKz/8DID2Yu");
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        //return NoOpPasswordEncoder.getInstance();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return encoder.encode(rawPassword.toString());
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encoder.matches(rawPassword, encodedPassword);
            }
        };
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web.ignoring().mvcMatchers("/test/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);

        http.authorizeRequests()//开启登录配置
                .antMatchers("/hello").hasRole("admin")//表示访问 /hello 这个接口，需要具备 admin 这个角色
                .anyRequest().authenticated()//表示剩余的其他接口，登录之后就能访问
                .and()
                .formLogin().defaultSuccessUrl("/")
                //定义登录页面，未登录时，访问一个需要登录之后才能访问的接口，会自动跳转到该页面
                // .loginPage("/login")
                // //登录处理接口
                // .loginProcessingUrl("/login")
                // //定义登录时，用户名的 key，默认为 username
                // .usernameParameter("username")
                // //定义登录时，用户密码的 key，默认为 password
                // .passwordParameter("password")
                // //登录成功的处理器
                // .successHandler(new AuthenticationSuccessHandler() {
                //     @Override
                //     public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                //         resp.setContentType("application/json;charset=utf-8");
                //         PrintWriter out = resp.getWriter();
                //         out.write("success");
                //         out.flush();
                //     }
                // })
                // .failureHandler(new AuthenticationFailureHandler() {
                //     @Override
                //     public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException exception) throws IOException, ServletException {
                //         resp.setContentType("application/json;charset=utf-8");
                //         PrintWriter out = resp.getWriter();
                //         out.write("fail");
                //         out.flush();
                //     }
                // })
                // .permitAll()//和表单登录相关的接口统统都直接通过
                .and()
                .logout()
                .logoutUrl("/logout")
                // .logoutSuccessHandler(new LogoutSuccessHandler() {
                //     @Override
                //     public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                //         resp.setContentType("application/json;charset=utf-8");
                //         PrintWriter out = resp.getWriter();
                //         out.write("logout success");
                //         out.flush();
                //     }
                // })
                // .permitAll()
                .and()
                .httpBasic()  //支持 Basic Authorization
                .and()
                .csrf().disable()
                .sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false)
                .sessionRegistry(sessionRegistry());

    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

}
