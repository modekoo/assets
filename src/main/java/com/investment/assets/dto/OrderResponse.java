package com.investment.assets.dto;

import lombok.Data;

@Data
public class OrderResponse {
    private String market;
    private String uuid;
    private String side;
    private String ord_type;
    private String price;
    private String state;
    private String created_at;
    private String volume;
    private String remaining_volume;
    private String reserved_fee;
    private String remaining_fee;
    private String paid_fee;
    private String locked;
    private String executed_volume;
    private String prevented_volume;
    private String prevented_locked;
    private String trades_count;

}
