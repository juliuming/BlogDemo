package com.banyuan.blog.service;

import com.banyuan.blog.model.User;

public interface UserService {
    User getUserByUsername(String userName);
}