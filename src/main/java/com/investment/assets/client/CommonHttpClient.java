package com.investment.assets.client;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;

public interface CommonHttpClient {

    default <T> T get(String url, String queryStr, ParameterizedTypeReference<T> responseType) {
        return null;
    }

    default <T> T post(String URL, HttpHeaders headers, Object body, Class<T> resultType) {
        return null;
    }

    default <T> T post(String URL, HttpHeaders headers, Object body, ParameterizedTypeReference<T> responseType) {
        return null;
    }
}
