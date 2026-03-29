package com.investment.assets.upbit.client;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

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

    private <T> String makeQueryStr(T queryObj){
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, Object>>(){}.getType();
        Map<String, Object> queryMap = gson.fromJson(gson.toJson(queryObj), type);
        return queryMap.entrySet().stream()
                .map(entry -> URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8)
                        + "=" +
                        URLEncoder.encode(String.valueOf(entry.getValue()), StandardCharsets.UTF_8))
                .collect(Collectors.joining("&"));
    }

    @Override
    public <T> T get(String url, String path, Map<String, Object> queryMap, ParameterizedTypeReference<T> responseType) {
        //url = "http://158.180.83.196/v1/ticker"

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(url + path);

        queryMap.forEach(builder::queryParam);
        String uri = builder.toUriString();

        String queryStr = makeQueryStr(queryMap);

        RequestEntity<Void> requestEntity = RequestEntity
                .get(uri)
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
