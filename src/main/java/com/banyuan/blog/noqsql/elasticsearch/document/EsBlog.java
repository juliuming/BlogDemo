package com.banyuan.blog.noqsql.elasticsearch.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

@Data
@Document(indexName = "blogs", type = "blog", shards = 5, replicas = 0)
public class EsBlog implements Serializable {
    private static final long serialVersionUID = -1L;

    @Id
    private Long id;
    @Field(analyzer = "ik_max_word", type = FieldType.Text)
    private String title;
    private String tag;
    private String content;
    private String auther;
    @Field(type = FieldType.Date)
    private Date time;
}
