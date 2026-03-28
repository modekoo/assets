package com.investment.assets.upbit.dto;

public record OrderResponse (
    String market,
    String uuid,
    String side,
    String ord_type,
    String price,
    String state,
    String created_at,
    String volume,
    String remaining_volume,
    String reserved_fee,
    String remaining_fee,
    String paid_fee,
    String locked,
    String executed_volume,
    String prevented_volume,
    String prevented_locked,
    String trades_count
) {}

