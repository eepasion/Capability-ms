package com.pragma.capability_mf.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import reactor.util.retry.Retry;

import java.time.Duration;

@Configuration
public class RsocketConfiguration {

    @Bean
    public RSocketRequester rSocketRequester(RSocketRequester.Builder builder) {
        return builder
                .rsocketConnector(connector ->
                        connector.reconnect(Retry.fixedDelay(2, Duration.ofSeconds(2)))
                                .dataMimeType("application/json"))
                .tcp("localhost", 7000);
    }

    @Bean
    public RSocketStrategies rSocketStrategies() {
        ObjectMapper objectMapper = new ObjectMapper();
        return RSocketStrategies.builder()
                .encoders(encoders -> encoders.add(new Jackson2JsonEncoder(objectMapper)))
                .decoders(decoders -> decoders.add(new Jackson2JsonDecoder(objectMapper)))
                .build();
    }
}


