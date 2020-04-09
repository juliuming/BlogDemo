package com.banyuan.blog.config;

import com.banyuan.blog.Handler.LoginAuthenticationFailureHander;
import com.banyuan.blog.Handler.LoginAuthenticationSuccessHandler;
import com.banyuan.blog.dto.BlogUserDetails;
import com.banyuan.blog.filter.JwtAhtenticationTokenFilter;
import com.banyuan.blog.model.User;
import com.banyuan.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;

    @Autowired
    UrlPermitListConfig urlPermitListConfig;

    @Autowired
    LoginAuthenticationSuccessHandler successHandler;

    @Autowired
    LoginAuthenticationFailureHander failureHander;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //identify the urls needn't authentication
        List<String> permittedUrls = urlPermitListConfig.getUrls();
        for (String str : permittedUrls) {
            http.authorizeRequests()
                    .antMatchers(str)
                    .permitAll();
        }

        //identify the login page
        http
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests().
                anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/index")
                .successHandler(successHandler)
                .failureHandler(failureHander)
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .permitAll();

        //clear session after log out and do other things
        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/index")
                .invalidateHttpSession(true)
                .deleteCookies(); //config cookies to clear

        http.addFilterBefore(jwtAhtenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            User user = userService.getUserByUsername(username);
            if (user != null && user.getStatus()!=0) {
                return new BlogUserDetails(user);
            }
            throw new UsernameNotFoundException("username not found");
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAhtenticationTokenFilter jwtAhtenticationTokenFilter() {
        return new JwtAhtenticationTokenFilter();
    }
}
