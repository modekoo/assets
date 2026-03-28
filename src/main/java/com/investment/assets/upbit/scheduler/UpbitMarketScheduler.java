package com.investment.assets.upbit.scheduler;

import com.investment.assets.upbit.service.UpbitMarketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class UpbitMarketScheduler {

    private final UpbitMarketService upbitMarketService;

    @Scheduled(fixedDelay = 5000)   //ms
    public void getCoinInfoJob(){
        log.debug(upbitMarketService.getCoinInfo().toString());
    }

}
