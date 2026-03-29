package com.investment.assets;

import com.google.gson.Gson;
import com.google.gson.internal.Primitives;
import com.google.gson.reflect.TypeToken;
import com.investment.assets.upbit.config.ConfigBean;
import com.investment.assets.upbit.dto.Order;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
class QueryStrTests {

	@Autowired
	ConfigBean configBean;

	@Test
	void queryStrTest(){
		String query = "markets=KRW-DOGE";
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("markets", "KRW-DOGE");

		String queryStr = queryMap.entrySet().stream().map(entry -> entry.getKey() + "=" + entry.getValue()).collect(Collectors.joining("&"));
		Assertions.assertEquals(query, queryStr);
	}

	@Test
	void queryStrTest2(){
		String query = "markets=KRW-DOGE";
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("market", "KRW-DOGE");  // 도지코인 마켓
		queryMap.put("side", "bid");         // bid = 매수, ask = 매도
		queryMap.put("volume", "10");        // 수량 (예: 도지 10개)
		queryMap.put("price", "100");        // 1개당 100원으로 매수
		queryMap.put("ord_type", "limit");   // 지정가 주문

		String queryStr = makeQueryStr(queryMap);
		Assertions.assertEquals(query, queryStr);
	}

	@Test
	void queryStrTestInOrder(){
		String query = "market=KRW-DOGE&side=bid&ord_type=price&price=5000";
		Order order = Order.builder()
				.buyOrder("KRW-DOGE", "5000")
				.build();
		String queryStr = makeQueryStr(order);

		Assertions.assertEquals(query, queryStr);
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
