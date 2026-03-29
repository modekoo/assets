package com.investment.assets.upbit.dto;

import java.math.BigDecimal;

public record MarketTickerDocument(
        String market,
        Long timestamp,
        BigDecimal tradePrice,
        BigDecimal openingPrice,
        BigDecimal highPrice,
        BigDecimal lowPrice,
        BigDecimal tradeVolume,
        String change,
        BigDecimal changeRate
)
{
    public static MarketTickerDocument from(UpbitTickerResponse r) {
        return new MarketTickerDocument(
                r.market(),
                r.timestamp(),
                new BigDecimal(r.tradePrice()),
                new BigDecimal(r.openingPrice()),
                new BigDecimal(r.highPrice()),
                new BigDecimal(r.lowPrice()),
                new BigDecimal(r.tradeVolume()),
                r.change(),
                new BigDecimal(r.changeRate())
        );
    }
}
