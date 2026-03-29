package com.investment.assets.upbit.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "upbit")
@Data
public class ConfigBean {

    private String accessKey;
    private String secretKey;
    private String proxyUrl;

}
