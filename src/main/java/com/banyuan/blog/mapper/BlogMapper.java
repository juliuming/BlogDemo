package com.banyuan.blog.mapper;

import com.banyuan.blog.model.Blog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlogMapper {
    public Blog selectBlogById(int id);
    public List<Blog> selectBlogByUsername(String username);
    public List<Blog> selectBlogByNickName(String nickName);
    public int insertBlog(int userId,Blog blog);
}