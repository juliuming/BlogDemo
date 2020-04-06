package com.banyuan.blog.mapper;

import com.banyuan.blog.model.Blog;
import com.banyuan.blog.noqsql.elasticsearch.document.EsBlog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlogMapper {
    Blog selectBlogById(int id);
    List<Blog> selectBlogByUsername(String username);
    List<Blog> selectBlogByNickName(String nickName);
    int insertBlog(int userId,Blog blog);
    List<EsBlog> selectAllEsBlogs();
}