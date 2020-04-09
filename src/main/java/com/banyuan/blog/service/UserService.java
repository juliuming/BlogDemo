package com.banyuan.blog.service;

import com.banyuan.blog.model.User;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;

public interface UserService {
    User getUserByUsername(String userName);

    String regist(String username, String password, String nickName, String gender, int age, String address,
                  String qq, String email) throws MessagingException;

    Boolean active(String username, String token, HttpServletResponse response);
}
