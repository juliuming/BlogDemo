package com.banyuan.blog.noqsql.elasticsearch.service.impl;

import com.banyuan.blog.noqsql.elasticsearch.document.EsBlog;
import com.banyuan.blog.noqsql.elasticsearch.repositories.BlogRepository;
import com.banyuan.blog.noqsql.elasticsearch.service.EsBlogService;
import com.banyuan.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class EsBlogServiceImpl implements EsBlogService {

    @Autowired
    BlogService blogService;

    @Autowired
    BlogRepository blogRepository;

    //此方法有风险,需要认证权限,要进行角色权限认证
    @Override
    public int importAll() {
        List<EsBlog> esBlogList = blogService.getAllEsBlogs();
        Iterable<EsBlog> esBlogIterable = blogRepository.saveAll(esBlogList);
        Iterator<EsBlog> iterator = esBlogIterable.iterator();
        int result = 0;
        while (iterator.hasNext()) {
            result++;
            iterator.next();
        }
        return result;
    }


    //未完待续
    @Override
    public void delete(Long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public EsBlog create(Long id) {
        List<Long> list = new ArrayList<>();
        list.add(id);
        EsBlog result = null;
        List<EsBlog> esBlogList = blogService.getEsBlogsByIdList(list);
        if (esBlogList.size() > 0) {
            EsBlog esBlog = esBlogList.get(0);
            result = (EsBlog) blogRepository.save(esBlog);
        }
        return result;
    }

    @Override
    public List<EsBlog> create(List<Long> ids) {
        List<EsBlog> result = blogService.getEsBlogsByIdList(ids);
        if (result.size() > 0) {
            for (EsBlog esBlog : result) {
                blogRepository.save(esBlog);
            }
        }
        return result;
    }

    @Override
    public void delete(List<Long> ids) {
        if (ids.isEmpty()) {
            List<EsBlog> esBlogList = new ArrayList<>();
            for (Long id : ids) {
                EsBlog esBlog = new EsBlog();
                esBlog.setId(id);
                esBlogList.add(esBlog);
            }
            blogRepository.deleteAll(esBlogList);
        }
    }

    @Override
    public Page<EsBlog> search(String keyword, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return blogRepository.findByTitleOrTagOrContent(keyword, keyword, keyword, pageable);
    }
}
