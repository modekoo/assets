package com.investment.assets.upbit.dto;

import lombok.Data;

@Data
public class Account {
    private String currency;
    private String balance;
    private String locked;
    private String avg_buy_price;
    private boolean avg_buy_price_modified;
}
