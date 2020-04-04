package com.banyuan.blog.mapper;

import com.banyuan.blog.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User selectUserByUsername(String username);

    String selectPasswordByUsername(String username);
}