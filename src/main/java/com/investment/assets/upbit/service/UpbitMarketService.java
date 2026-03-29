package com.investment.assets.upbit.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.investment.assets.upbit.client.UpbitRestClient;
import com.investment.assets.upbit.config.ConfigBean;
import com.investment.assets.upbit.dto.MarketTickerDocument;
import com.investment.assets.upbit.dto.UpbitTickerResponse;
import com.investment.assets.upbit.kafka.producer.MarketTickerProducer;
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
    private final UpbitRestClient upbitRestClient;
    private final MarketTickerProducer marketTickerProducer;
    private final ConfigBean configBean;

    public void getTickerInfo(){
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("markets", "KRW-DOGE");
        List<UpbitTickerResponse> resultTickerList = upbitRestClient.get(configBean.getProxyUrl(),"/v1/ticker", queryMap
                , new ParameterizedTypeReference<List<UpbitTickerResponse>>(){});

        if (resultTickerList == null || resultTickerList.isEmpty()) {
            return;
            //todo throw new APIException....
        }

        MarketTickerDocument marketTickerDocument = MarketTickerDocument.from(resultTickerList.getFirst());
        marketTickerProducer.send(marketTickerDocument);

    }

    //util, or client

}
