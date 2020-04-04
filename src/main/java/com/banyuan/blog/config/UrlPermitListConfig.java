package com.banyuan.blog.config;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Value
@Component
@ConfigurationProperties(prefix = "security.permittedurls")
public class UrlPermitListConfig {
    List<String> urls = new ArrayList<>();
}

