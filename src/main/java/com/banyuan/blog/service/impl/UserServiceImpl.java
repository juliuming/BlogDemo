package com.banyuan.blog.service.impl;

import com.banyuan.blog.mapper.UserMapper;
import com.banyuan.blog.model.User;
import com.banyuan.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserByUsername(String userName) {
        return userMapper.selectUserByUsername(userName);
    }
}
