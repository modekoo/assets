package com.investment.assets.service;

import org.springframework.stereotype.Service;

@Service
public interface AssertsRestTempleteService<T> {
    <T> T exchangeGET(String URL, Object param, Class<T> classOfT);
    <T> T exchangePOST(String URL, Object body, Class<T> classOfT);
}
