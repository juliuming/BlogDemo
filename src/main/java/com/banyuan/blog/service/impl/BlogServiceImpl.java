package com.banyuan.blog.service.impl;

import com.banyuan.blog.mapper.BlogMapper;
import com.banyuan.blog.model.Blog;
import com.banyuan.blog.noqsql.elasticsearch.document.EsBlog;
import com.banyuan.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    BlogMapper blogMapper;

    @Override
    public List<EsBlog> getAllEsBlogs() {
        return blogMapper.selectAllEsBlogs();
    }


    @Override
    public List<Blog> showUserBlogs(String username) {
        return blogMapper.selectBlogByUsername(username);
    }

    @Override
    public int createAndSaveBlog(int userId, Blog blog) {
        int success = 0;
        try{
            success = blogMapper.insertBlog(userId,blog);
        }catch (Exception e){
            e.printStackTrace();
        }
        return success;
    }
}
