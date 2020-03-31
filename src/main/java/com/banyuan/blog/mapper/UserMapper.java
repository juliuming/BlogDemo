package com.banyuan.blog.mapper;

import com.banyuan.blog.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    public User selectUserByName(String name);
}