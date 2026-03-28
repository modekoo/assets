package com.investment.assets;

import com.google.gson.Gson;
import com.google.gson.internal.Primitives;
import com.google.gson.reflect.TypeToken;
import com.investment.assets.config.ConfigBean;
import com.investment.assets.upbit.client.UpbitRestClient;
import com.investment.assets.upbit.dto.Ticker;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@SpringBootTest
class MarketInfoTests {

	@Autowired
	ConfigBean configBean;

	@Autowired
	UpbitRestClient upbitRestClient;

//	@Test
	void contextLoads() {
		log.debug("accessKey = {}", configBean.getAccessKey());
		log.debug("secretKey = {}", configBean.getSecretKey());
	}

	@Nested
	class MarketInfoDOGE{

		Map<String, Object> queryMap;
		String queryStr;
		String url = "http://158.180.83.196/v1/ticker?";

		@BeforeEach
		void setUpQuery() {
			queryMap = new HashMap<>();
			queryMap.put("markets", "KRW-DOGE");
			queryStr = makeQueryStr(queryMap);
		}

		@Test
		void callUpbitMarketInfoDOGE() throws Exception {

			List<Ticker> dogeInfoList = upbitRestClient.get(url, queryStr, new ParameterizedTypeReference<List<Ticker>>() {});
			log.debug("dogeInfoList = {}" , dogeInfoList);
//			Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
		}
	}

	private <T> String makeQueryStr(T queryObj){
		Gson gson = new Gson();
		Type type = new TypeToken<Map<String, Object>>(){}.getType();
		Map<String, Object> queryMap = gson.fromJson(gson.toJson(queryObj), type);
		return queryMap.entrySet().stream().map(entry -> entry.getKey() + "=" + entry.getValue()).collect(Collectors.joining("&"));
	}

	private <T> T parseObj(Object data, Class<T> classOFT){
		Gson gson = new Gson();
		String objStr = gson.toJson(data);
		Object obj = gson.fromJson(objStr, classOFT);
		return Primitives.wrap(classOFT).cast(obj);
	}

}
