package com.banyuan.blog.service.impl;

import com.banyuan.blog.dto.BlogUserDetails;
import com.banyuan.blog.mapper.UserMapper;
import com.banyuan.blog.model.User;
import com.banyuan.blog.service.UserService;
import com.banyuan.blog.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    public User getUserByUsername(String userName) {
        return userMapper.selectUserByUsername(userName);
    }

    @Override
    public String logIn(String username, String password) {
        String token;
        if (username == null) {
            return null;
        } else {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (userDetails != null && passwordEncoder.matches(password, userDetails.getPassword())) {
                System.out.println("success");
                token = jwtUtils.generateToken(userDetails);
                return token;
            } else {
                System.out.println("failed");
                return null;
            }
        }
    }
}
