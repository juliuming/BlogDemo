package com.banyuan.blog.config;

import com.banyuan.blog.dto.blogUserDetails;
import com.banyuan.blog.filter.JwtAhtenticationTokenFilter;
import com.banyuan.blog.model.User;
import com.banyuan.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //identify the urls needn't authentication
        String[] permittedUrls = UrlPermitListConfig.getPermittedUrls();
        for (String str:permittedUrls) {
            http.authorizeRequests()
                    .antMatchers(str)
                    .permitAll();
        }

        //identify the login page
        http.formLogin()
                .loginPage("login")
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
    public UserDetailsService userDetailsService(){
        return username->{
            User user = userService.getUserByUsername(username);
            return new blogUserDetails(user);
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAhtenticationTokenFilter jwtAhtenticationTokenFilter(){
        return new JwtAhtenticationTokenFilter();
    }
}
