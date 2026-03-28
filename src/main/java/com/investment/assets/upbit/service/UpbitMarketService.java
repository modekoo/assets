package com.investment.assets.upbit.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.investment.assets.upbit.client.UpbitRestClient;
import com.investment.assets.upbit.dto.Ticker;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UpbitMarketService {
    final UpbitRestClient upbitRestClient;

    public List<Ticker> getCoinInfo(){
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("markets", "KRW-DOGE");
        String queryStr = makeQueryStr(queryMap);
        return upbitRestClient.get("http://158.180.83.196/v1/ticker?", queryStr
                , new ParameterizedTypeReference<List<Ticker>>(){});
    }

    //util, or client
    private <T> String makeQueryStr(T queryObj){
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, Object>>(){}.getType();
        Map<String, Object> queryMap = gson.fromJson(gson.toJson(queryObj), type);
        return queryMap.entrySet().stream().map(entry -> entry.getKey() + "=" + entry.getValue()).collect(Collectors.joining("&"));
    }
}
