package com.investment.assets.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@Slf4j
public class TestService<T> {

    public void test(){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=utf-8");
        RequestEntity<Void> requestEntity = RequestEntity
                .get("http://naver.com")
                .headers(httpHeaders)
                .build();
        ResponseEntity<String> obj = restTemplate.exchange(requestEntity, String.class);

        log.debug(obj.getStatusCode().toString());
    }
}
