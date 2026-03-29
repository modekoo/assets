package com.investment.assets.upbit.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpbitTickerResponse(
        @JsonProperty("market")
        String market,

        @JsonProperty("trade_date")
        String tradeDate,

        @JsonProperty("trade_time")
        String tradeTime,

        @JsonProperty("trade_date_kst")
        String tradeDateKst,

        @JsonProperty("trade_time_kst")
        String tradeTimeKst,

        @JsonProperty("trade_timestamp")
        long tradeTimestamp,

        @JsonProperty("opening_price")
        String openingPrice,

        @JsonProperty("high_price")
        String highPrice,

        @JsonProperty("low_price")
        String lowPrice,

        @JsonProperty("trade_price")
        String tradePrice,

        @JsonProperty("prev_closing_price")
        String prevClosingPrice,

        @JsonProperty("change")
        String change,

        @JsonProperty("change_price")
        long changePrice,

        @JsonProperty("change_rate")
        double changeRate,

        @JsonProperty("signed_change_price")
        String signedChangePrice,

        @JsonProperty("signed_change_rate")
        String signedChangeRate,

        @JsonProperty("trade_volume")
        String tradeVolume,

        @JsonProperty("acc_trade_price")
        double accTradePrice,

        @JsonProperty("acc_trade_price_24h")
        double accTradePrice24h,

        @JsonProperty("acc_trade_volume")
        double accTradeVolume,

        @JsonProperty("acc_trade_volume_24h")
        double accTradeVolume24h,

        @JsonProperty("highest_52_week_price")
        long highest52WeekPrice,

        @JsonProperty("highest_52_week_date")
        String highest52WeekDate,

        @JsonProperty("lowest_52_week_price")
        long lowest52WeekPrice,

        @JsonProperty("lowest_52_week_date")
        String lowest52WeekDate,

        @JsonProperty("timestamp")
        long timestamp
) {}
