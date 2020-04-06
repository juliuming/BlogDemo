package com.banyuan.blog.noqsql.elasticsearch.repositories;

import com.banyuan.blog.model.Blog;
import com.github.pagehelper.Page;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BlogRepository extends ElasticsearchRepository {
    Page<Blog> findByTitleOrTagsOrKeywords(String title,String tags,String keywords);
}
