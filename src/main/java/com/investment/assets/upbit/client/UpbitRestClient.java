package com.investment.assets.upbit.client;

import com.investment.assets.client.CommonHttpClient;
import com.investment.assets.exception.RestTemplateResponseErrorHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpbitRestClient implements CommonHttpClient {

    private final UpbitAuthProvider upbitAuthProvider;

    private final RestTemplate restTemplate;

    private HttpHeaders setHeader(String queryStr){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type" , "application/json; charset=utf-8");
        httpHeaders.add("Authorization", upbitAuthProvider.setAuthentication(queryStr));
        return httpHeaders;
    }

    @Override
    public <T> T get(String url, String queryStr, ParameterizedTypeReference<T> responseType) {
        //url = "http://158.180.83.196/v1/ticker?"
        RequestEntity<Void> requestEntity = RequestEntity
                .get(url + queryStr)
                .headers(setHeader(queryStr))
                .build();
        ResponseEntity<T> result = restTemplate.exchange(requestEntity, responseType);
        log.debug("status = {}", result.getStatusCode());
        if(result.getStatusCode().is2xxSuccessful())
            log.debug("body = {}", result.getBody());

        return result.getBody();
    }

    @Override
    public Object post(String URL, HttpHeaders headers, Object body, Class resultType) {
        return null;
    }
}
