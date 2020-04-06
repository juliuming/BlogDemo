package com.banyuan.blog.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;

@Configuration
public class ElasticSearchConfig {
    @Bean
    RestHighLevelClient client() {
        ClientConfiguration clientCoinfiguration = ClientConfiguration.builder()
                .connectedTo("localhost:9200").build();

        return RestClients.create(clientCoinfiguration).rest();
    }
}