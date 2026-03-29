package com.investment.assets;

import com.investment.assets.upbit.client.UpbitRestClient;
import com.investment.assets.upbit.config.ConfigBean;
import com.investment.assets.upbit.dto.UpbitTickerResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
class MarketInfoTests {

	@Autowired
	ConfigBean configBean;

	@Autowired
	UpbitRestClient upbitRestClient;

//	@Test
	void contextLoads() {
		log.debug("accessKey = {}", configBean.getAccessKey());
		log.debug("secretKey = {}", configBean.getSecretKey());
		log.debug("proxyUrl = {}", configBean.getProxyUrl());
	}

	@Nested
	class MarketInfoDOGE{

		Map<String, Object> queryMap;
		String url = configBean.getProxyUrl();

		@BeforeEach
		void setUpQuery() {
			queryMap = new HashMap<>();
			queryMap.put("markets", "KRW-DOGE");
		}

		@Test
		void callUpbitMarketInfoDOGE() throws Exception {

			List<UpbitTickerResponse> dogeInfoList = upbitRestClient.get(url, "/v1/ticker" ,queryMap, new ParameterizedTypeReference<List<UpbitTickerResponse>>() {});
			log.debug("dogeInfoList = {}" , dogeInfoList);
//			Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
		}
	}

}
