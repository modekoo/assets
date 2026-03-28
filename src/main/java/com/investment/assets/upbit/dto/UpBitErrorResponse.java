package com.investment.assets.upbit.dto;

import lombok.Data;

@Data
public class UpBitErrorResponse {
    private ErrorDetail error;

    @Data
    public static class ErrorDetail {
        private String name;     // 에러 코드 (예: insufficient_funds)
        private String message;  // 에러 메시지 (예: 주문가능한 잔고가 부족합니다.)
    }
}
