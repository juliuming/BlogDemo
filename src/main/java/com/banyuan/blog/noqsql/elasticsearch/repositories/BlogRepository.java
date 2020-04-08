package com.banyuan.blog.noqsql.elasticsearch.repositories;

import com.banyuan.blog.noqsql.elasticsearch.document.EsBlog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;


public interface BlogRepository extends ElasticsearchRepository<EsBlog,Long> {
    Page<EsBlog> findByTitleOrTagOrContent(String title, String tags, String keywords, Pageable pageable);
}
