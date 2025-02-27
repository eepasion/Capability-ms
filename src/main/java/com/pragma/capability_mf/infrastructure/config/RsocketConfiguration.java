package com.pragma.capability_mf.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.capability_mf.infrastructure.config.auth.rsocket.ApiKeyRSocketInterceptor;
import io.rsocket.SocketAcceptor;
import io.rsocket.core.RSocketServer;
import io.rsocket.transport.netty.server.CloseableChannel;
import io.rsocket.transport.netty.server.TcpServerTransport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import org.springframework.messaging.rsocket.annotation.support.RSocketMessageHandler;
import org.springframework.util.MimeType;
import reactor.util.retry.Retry;

import java.time.Duration;

@Configuration
public class RsocketConfiguration {

    @Bean
    public RSocketRequester rSocketRequester(RSocketRequester.Builder builder,
                                             @Value("${security.api-key}") String apiKey) {
        return builder
                .setupMetadata(apiKey, MimeType.valueOf("application/text"))
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

    @Bean
    public CloseableChannel rSocketServer(RSocketMessageHandler messageHandler, ApiKeyRSocketInterceptor apiKeyRSocketInterceptor) {
        SocketAcceptor responder = messageHandler.responder();

        return RSocketServer.create((setupPayload, sendingSocket) ->
                apiKeyRSocketInterceptor.accept(setupPayload, sendingSocket)
                        .flatMap(authenticatedSocket -> responder.accept(setupPayload, authenticatedSocket))
        ).bindNow(TcpServerTransport.create("localhost", 7001));
    }
}


