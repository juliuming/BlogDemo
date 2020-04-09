package com.banyuan.blog.mapper;

import com.banyuan.blog.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User selectUserByUsername(String username);

    String selectPasswordByUsername(String username);

    int insertUser(String username,String password,String nickName,String gender,int age,String address,
                   String qq,String email);

    int activate(String username);
}