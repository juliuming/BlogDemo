package com.banyuan.blog;

import com.banyuan.blog.dao.UserMapper;
import com.banyuan.blog.model.User;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
@MapperScan("com.banyuan.blog.dao")
public class BlogApplication {

    public static void main(String[] args) {
       ApplicationContext context = SpringApplication.run(BlogApplication.class, args);
       UserMapper userMapper = context.getBean(UserMapper.class);
       User user = userMapper.selectUserByName("Durant");
       System.out.println(user);
    }

}
