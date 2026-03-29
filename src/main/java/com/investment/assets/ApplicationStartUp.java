package com.investment.assets;

import com.investment.assets.upbit.config.ConfigBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApplicationStartUp implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    ConfigBean configBean;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.debug("accessKey = {}", configBean.getAccessKey());
        log.debug("secretKey = {}", configBean.getSecretKey());
    }
}
