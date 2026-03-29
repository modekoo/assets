package com.investment.assets.upbit.kafka.producer;

import com.investment.assets.upbit.dto.MarketTickerDocument;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MarketTickerProducer {

    private static final String TOPIC = "market.ticker";

    private final KafkaTemplate<String, MarketTickerDocument> kafkaTemplate;

    public void send(MarketTickerDocument document) {
        kafkaTemplate.send(TOPIC, document.market(), document)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        log.error("Kafka send failed", ex);
                        return;
                    }

                    log.debug("Kafka sent: market={}, offset={}",
                            document.market(),
                            result.getRecordMetadata().offset());
                });
    }
}
