package com.investment.assets.upbit.dto;

import lombok.Builder;
import lombok.Getter;

//구매 들어갈 때 개선해봅시다.
@Getter
@Builder
public class Order {
    private String market;
    private String side;
    private String volume;
    private String ord_type;
    private String price;

    public enum Side{
        bid("price"),
        ask("market");

        private final String orderType;

        Side(String orderType){
            this.orderType = orderType;
        }
    }

    public static class OrderBuilder {
        public OrderBuilder buyOrder(String market, String price) {
            this.market = market;
            this.side = Side.bid.name();
            this.ord_type = Side.bid.orderType;
            this.price = price;
            return this;
        }

        public OrderBuilder sellOrder(String market, String volume) {
            this.market = market;
            this.side = Side.ask.name();
            this.ord_type = Side.ask.orderType;
            this.volume = volume;
            return this;
        }
    }
}

