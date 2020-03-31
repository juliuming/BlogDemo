package com.banyuan.blog;

import com.banyuan.blog.mapper.BlogMapper;
import com.banyuan.blog.mapper.UserMapper;
import com.banyuan.blog.model.Blog;
import com.banyuan.blog.model.User;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;


@SpringBootApplication
public class BlogApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(BlogApplication.class, args);
        UserMapper userMapper = context.getBean(UserMapper.class);
        BlogMapper blogMapper = context.getBean(BlogMapper.class);
        User user = userMapper.selectUserByName("Durant");
        List<Blog> list = blogMapper.selectBlogByUsername("Durant1988");
        System.out.println(user);
        System.out.println(list);
    }
}
