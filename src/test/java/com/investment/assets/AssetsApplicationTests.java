package com.investment.assets;

import com.google.gson.Gson;
import com.google.gson.internal.Primitives;
import com.google.gson.reflect.TypeToken;
import com.investment.assets.client.UpbitAuthentication;
import com.investment.assets.config.ConfigBean;
import com.investment.assets.dto.*;
import com.investment.assets.exception.RestTemplateResponseErrorHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@SpringBootTest
class AssetsApplicationTests {

	@Autowired
	ConfigBean configBean;

	@Autowired
	UpbitAuthentication upbitAuthentication;

//	@Test
	void contextLoads() {
		log.debug("accessKey = {}", configBean.getAccessKey());
		log.debug("secretKey = {}", configBean.getSecretKey());
	}


	@Test
	void callUpbitAccountInfo() throws Exception {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-Type" , "application/json; charset=utf-8");
		httpHeaders.add("Authorization", upbitAuthentication.setAuthentication());

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
		RequestEntity<Void> requestEntity = RequestEntity
				.get("http://158.180.83.196/v1/accounts")
				.headers(httpHeaders)
				.build();
		ResponseEntity<List<Account>> result = restTemplate.exchange(requestEntity, new ParameterizedTypeReference<List<Account>>() {});
		log.debug("status = {}", result.getStatusCode());
		if(result.getStatusCode().is2xxSuccessful())
			log.debug("body = {}", result.getBody());

		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	void callUpbitMarketalAll() throws Exception {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-Type" , "application/json; charset=utf-8");
		httpHeaders.add("Authorization", upbitAuthentication.setAuthentication());

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
		RequestEntity<Void> requestEntity = RequestEntity
				.get("http://158.180.83.196/v1/market/all")
				.headers(httpHeaders)
				.build();
		ResponseEntity<List<Market>> result = restTemplate.exchange(requestEntity, new ParameterizedTypeReference<List<Market>>() {});
		log.debug("status = {}", result.getStatusCode());
		if(result.getStatusCode().is2xxSuccessful())
			log.debug("body = {}", result.getBody());

		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Nested
	class MarketInfoDOGE{

		Map<String, Object> queryMap;
		String queryStr;

		@BeforeEach
		void setUpQuery() {
			queryMap = new HashMap<>();
			queryMap.put("markets", "KRW-DOGE");
			queryStr = makeQueryStr(queryMap);
		}

		@Test
		void callUpbitMarketInfoDOGE() throws Exception {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.add("Content-Type" , "application/json; charset=utf-8");
			httpHeaders.add("Authorization", upbitAuthentication.setAuthentication(queryStr));

			RestTemplate restTemplate = new RestTemplate();
			restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
			RequestEntity<Void> requestEntity = RequestEntity
					.get("http://158.180.83.196/v1/ticker?" + queryStr)
					.headers(httpHeaders)
					.build();
			ResponseEntity<List<Ticker>> result = restTemplate.exchange(requestEntity, new ParameterizedTypeReference<List<Ticker>>() {});
			log.debug("status = {}", result.getStatusCode());
			if(result.getStatusCode().is2xxSuccessful())
				log.debug("body = {}", result.getBody());

			Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
		}
	}

	@Nested
	class MarketBuyDOGE{
		String queryStr;
		Order order;

		@BeforeEach
		void setUpQuery() {
			order = Order.builder()
					.buyOrder("KRW-DOGE", "5000")
					.build();
			queryStr = makeQueryStr(order);
		}

		@Test
		void callUpbitBuyDOGE() throws Exception {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.add("Content-Type" , "application/json; charset=utf-8");
			httpHeaders.add("Authorization", upbitAuthentication.setAuthentication(queryStr));

			RestTemplate restTemplate = new RestTemplate();
			restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
			RequestEntity<Void> requestEntity = RequestEntity
					.post("http://158.180.83.196/v1/orders?" + queryStr)
					.headers(httpHeaders)
					.build();

			ResponseEntity<Object> result = restTemplate.exchange(requestEntity, Object.class);
			log.debug("status = {}", result.getStatusCode());
			log.debug("body = {}", result.getBody());

			if(result.getStatusCode().is2xxSuccessful()){
				OrderResponse orderResponse =  parseObj(result.getBody(), OrderResponse.class);
				log.debug("orderResponse = {}", orderResponse);
			}
			else{
				UpBitErrorResponse upBitErrorResponse = parseObj(result.getBody(), UpBitErrorResponse.class);
				log.debug("upBitErrorResponse = {}", upBitErrorResponse);
			}
			Assertions.assertEquals(HttpStatus.CREATED, result.getStatusCode());
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
