package com.pragma.capability_mf.infrastructure.adapters.clientadapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.capability_mf.domain.enums.ErrorMessages;
import com.pragma.capability_mf.domain.exceptions.BusinessException;
import com.pragma.capability_mf.domain.model.Technology;
import com.pragma.capability_mf.domain.spi.TechnologyClientPort;
import io.rsocket.RSocket;
import io.rsocket.core.RSocketConnector;
import io.rsocket.transport.netty.client.TcpClientTransport;
import io.rsocket.util.DefaultPayload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.List;

@Service
@Slf4j
public class TechnologyClientAdapter implements TechnologyClientPort {
    private final RSocketRequester rsocketRequester;

    public TechnologyClientAdapter(RSocketRequester rsocketRequester) {
        this.rsocketRequester = rsocketRequester;
    }

    public Mono<List<Technology>> getAllTechnologiesById(List<Long> ids) {
        log.info("Recibiendo datos de RSocket...");
        return rsocketRequester
                .route("get.technologies")
                .data(ids)
                .retrieveMono(new ParameterizedTypeReference<List<Technology>>() {});
    }

}
