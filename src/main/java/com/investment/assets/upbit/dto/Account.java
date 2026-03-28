package com.investment.assets.upbit.dto;

import lombok.Data;

public record Account (
    String currency,
    String balance,
    String locked,
    String avg_buy_price,
    boolean avg_buy_price_modified
){}
