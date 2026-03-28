package com.investment.assets.upbit.dto;

public record UpBitErrorResponse (
    ErrorDetail error
){
    public record ErrorDetail (
        String name,     // 에러 코드 (예: insufficient_funds)
        String message  // 에러 메시지 (예: 주문가능한 잔고가 부족합니다.)
    ){}
}
