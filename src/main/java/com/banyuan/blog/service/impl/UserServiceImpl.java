package com.banyuan.blog.service.impl;

import com.banyuan.blog.dto.BlogUserDetails;
import com.banyuan.blog.mapper.UserMapper;
import com.banyuan.blog.model.User;
import com.banyuan.blog.service.EmailService;
import com.banyuan.blog.service.RedisService;
import com.banyuan.blog.service.UserService;
import com.banyuan.blog.utils.JwtUtils;
import com.banyuan.blog.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class UserServiceImpl implements UserService {
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private MyUtils myUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private EmailService emailService;

    @Override
    public User getUserByUsername(String userName) {
        return userMapper.selectUserByUsername(userName);
    }

    @Override
    public String regist(String username, String password, String nickName, String gender, int age, String address,
                         String qq, String email) throws MessagingException {
        String passwordEncoded = passwordEncoder.encode(password);
        int isSuccess = userMapper.insertUser(username, passwordEncoded, nickName, gender, age, address, qq, email);
        if (isSuccess == 1) {
            /*BlogUserDetails blogUserDetails = new BlogUserDetails(userMapper.selectUserByUsername(username));

            Cookie jwtCookie = new Cookie(tokenHeader, tokenHead + jwtUtils.generateToken(blogUserDetails));
            jwtCookie.setMaxAge(14 * 24 * 3600);
            response.addCookie(jwtCookie);*/
            String token = myUtils.generateVCode(6, true);
            redisService.set(username + "RegisterToken", token);
            String content =
                    String.format("<p>尊敬的用户你好,感谢注册半圆blog," +
                            "请点击<a href=\"http://localhost:8081/register/active?username=%s&token=%s\">" +
                            "激活</a>您的账号","juliuming",token);
            emailService.sendEmail(email, "半圆博客激活邮件", content);
            return "success";
        }
        return "failed";
    }

    @Override
    public Boolean active(String username, String token, HttpServletResponse response) {
        String activeToken = redisService.get(username + "RegisterToken");
        if (activeToken != null && activeToken.equals(token)) {
            userMapper.activate(username);
            BlogUserDetails blogUserDetails = new BlogUserDetails(userMapper.selectUserByUsername(username));
            Cookie jwtCookie = new Cookie(tokenHeader, tokenHead + jwtUtils.generateToken(blogUserDetails));
            jwtCookie.setMaxAge(14 * 24 * 3600);
            response.addCookie(jwtCookie);
            return true;
        } else {
            return false;
        }
    }
}
