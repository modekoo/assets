package com.investment.assets.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
@Data
public class ConfigBean {

    private String accessKey;
    private String secretKey;

}
